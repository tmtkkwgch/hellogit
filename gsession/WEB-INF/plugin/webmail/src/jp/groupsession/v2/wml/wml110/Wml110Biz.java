package jp.groupsession.v2.wml.wml110;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.dao.base.WmlLabelDao;
import jp.groupsession.v2.wml.dao.base.WmlLabelRelationDao;
import jp.groupsession.v2.wml.dao.base.WmlMaildataSortDao;
import jp.groupsession.v2.wml.model.base.AccountDataModel;
import jp.groupsession.v2.wml.model.base.LabelDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール ラベルの管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml110Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml110Biz.class);
    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = ":";

    /**
     * <br>[機  能] ラベルの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    public void deleteLabel(Connection con, Wml110ParamModel paramMdl) throws SQLException {

        boolean commitFlg = false;

        try {
            //ラベルを削除する
            WmlLabelDao wlDao = new WmlLabelDao(con);
            wlDao.delete(paramMdl.getWmlEditLabelId());

            //メール - ラベルを削除する
            WmlLabelRelationDao wlrDao = new WmlLabelRelationDao(con);
            wlrDao.delete(paramMdl.getWmlEditLabelId());

            //メール情報表示順を削除する
            WmlMaildataSortDao mailSortDao = new WmlMaildataSortDao(con);
            mailSortDao.deleteMailSortOfLabel(paramMdl.getWmlEditLabelId());

            //コミット実行
            con.commit();
            commitFlg = true;
        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (!commitFlg) {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Connection con,
                             Wml110ParamModel paramMdl,
                             int userSid,
                             RequestModel reqMdl) throws SQLException {

        List<AccountDataModel> adMdlList = new ArrayList<AccountDataModel>();
        List<LabelDataModel> ldMdlList = new ArrayList<LabelDataModel>();
        Wml110Dao dao = new Wml110Dao(con);

        WmlBiz comonBiz = new WmlBiz();

        //アカウントリストを取得
        adMdlList = dao.getAccountList(userSid);
        paramMdl.setAcntList(comonBiz.getAcntCombo(reqMdl, adMdlList));

        //ラベルリストを取得
        int dspCnt = paramMdl.getDspCount();
        if (dspCnt == 0 && adMdlList.size() != 0) {
            paramMdl.setWml110accountSid(adMdlList.get(0).getAccountSid());
            paramMdl.setDspCount(1);
        }

        int selectAccountNum = paramMdl.getWml110accountSid();
        ldMdlList = dao.getLabelList(selectAccountNum);
        LabelDataModel wldDspMdl = null;

        int count = 0;
        //ラベルを画面表示用に加工する
        List<LabelDataModel> lbList = new ArrayList<LabelDataModel>();

        for (LabelDataModel wldSetMdl : ldMdlList) {
            wldDspMdl = new LabelDataModel();
            wldDspMdl.setLabelSid(wldSetMdl.getLabelSid());
            wldDspMdl.setLabelName(wldSetMdl.getLabelName());
            wldDspMdl.setLabelOrder(wldSetMdl.getLabelOrder());
            wldDspMdl.setLbValue(
                    __getRadioValueStr(wldSetMdl.getLabelSid(), wldSetMdl.getLabelOrder(), count));
            count++;
            lbList.add(wldDspMdl);
        }
        paramMdl.setLbnList(lbList);

        //チェックされている並び順がNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getWml110SortRadio())
            && ldMdlList.size() > 0) {

            LabelDataModel wldMdl = ldMdlList.get(0);
            paramMdl.setWml110SortRadio(
                    __getRadioValueStr(wldMdl.getLabelSid(), wldMdl.getLabelOrder(), 0));
        }
    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「ラベルSID-表示順-画面上の表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param lbSid ラベルSID
     * @param lbSort 表示順
     * @param index 画面上の表示順
     * @return String radioにセットする値
     */
    private String __getRadioValueStr(int lbSid, int lbSort, int index) {

        String sort = lbSid + SORT_SEPARATE
                    + lbSort + SORT_SEPARATE
                    + index;
        return sort;
    }

    /**
     * <br>[機  能] 順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Wml110ParamModel paramMdl, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getWml110sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getWml110SortRadio();
        if (StringUtil.isNullZeroString(selectedKey)) {
            return;
        }

        String[] selectKeyList = selectedKey.split(SORT_SEPARATE);

        //画面表示順
        int selectedKeyDispNum = Integer.parseInt(selectKeyList[2]);
        log__.debug("画面表示順 = " + selectedKeyDispNum);

        //画面の最初に表示されている項目 + 順位を上げる
        if (selectedKeyDispNum == 0 && changeKbn == GSConstWebmail.SORT_UP) {
            return;
        //画面の最後に表示されている項目 + 順位を下げる
        } else if (selectedKeyDispNum == keyList.length - 1
                && changeKbn == GSConstWebmail.SORT_DOWN) {
            return;
        }

        //選択された項目のラベルSID + ソート順
        int motoSid = Integer.parseInt(selectKeyList[0]);
        int motoSort = Integer.parseInt(selectKeyList[1]);

        int sakiSid = -1;
        int sakiSort = -1;
        int target = selectedKeyDispNum;

        if (changeKbn == GSConstWebmail.SORT_UP) {
            target -= 1;
        } else if (changeKbn == GSConstWebmail.SORT_DOWN) {
            target += 1;
        }

        //画面表示全キーから入れ替え先のデータを探す
        TreeMap<Integer, String> sortMap = new TreeMap<Integer, String>();
        for (String allKey : keyList) {

            String[] allKeyList = allKey.split(SORT_SEPARATE);
            int allKeyDispNum = Integer.parseInt(allKeyList[2]);

            if (allKeyDispNum == target) {
                sakiSid = Integer.parseInt(allKeyList[0]);
                sakiSort = Integer.parseInt(allKeyList[1]);
            } else if (allKeyDispNum > target) {
                sortMap.put(allKeyDispNum, allKey);
            }
        }

        if (sakiSid == -1 || sakiSort == -1) {
            return;
        }

        //順序入れ替え
        if (motoSort == sakiSort) {
            motoSort++;
        }
        Wml110Dao dao = new Wml110Dao(con);
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort);

        int newSort = sakiSort;

        //新しいキーを設定
        paramMdl.setWml110SortRadio(__getRadioValueStr(motoSid, newSort, target));
    }
}
