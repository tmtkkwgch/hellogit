package jp.groupsession.v2.sml.sml310;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.dao.SmlAccountDao;
import jp.groupsession.v2.sml.dao.SmlLabelDao;
import jp.groupsession.v2.sml.dao.SmlJmeisLabelDao;
import jp.groupsession.v2.sml.dao.SmlLabelSortDao;
import jp.groupsession.v2.sml.dao.SmlSmeisLabelDao;
import jp.groupsession.v2.sml.dao.SmlWmeisLabelDao;
import jp.groupsession.v2.sml.model.AccountDataModel;
import jp.groupsession.v2.sml.model.LabelDataModel;
import jp.groupsession.v2.sml.model.SmlAccountModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <br>[機  能] ショートメール ラベルの管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml310Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml310Biz.class);
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
    public void deleteLabel(Connection con, Sml310ParamModel paramMdl) throws SQLException {

        boolean commitFlg = false;

        try {
            //ラベルを削除する
            SmlLabelDao slDao = new SmlLabelDao(con);
            slDao.delete(paramMdl.getSmlEditLabelId());

            //メール - ラベルを削除する
            SmlJmeisLabelDao jDao = new SmlJmeisLabelDao(con);
            jDao.delete(paramMdl.getSmlEditLabelId());
            SmlSmeisLabelDao sDao = new SmlSmeisLabelDao(con);
            sDao.delete(paramMdl.getSmlEditLabelId());
            SmlWmeisLabelDao wDao = new SmlWmeisLabelDao(con);
            wDao.delete(paramMdl.getSmlEditLabelId());

            //メール情報表示順を削除する
            SmlLabelSortDao mailSortDao = new SmlLabelSortDao(con);
            mailSortDao.deleteMailSortOfLabel(paramMdl.getSmlEditLabelId());

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
                             Sml310ParamModel paramMdl,
                             int userSid,
                             RequestModel reqMdl) throws SQLException {

        List<AccountDataModel> adMdlList = new ArrayList<AccountDataModel>();
        List<LabelDataModel> ldMdlList = new ArrayList<LabelDataModel>();
        Sml310Dao dao = new Sml310Dao(con);

//        SmlCommonBiz comonBiz = new SmlCommonBiz(con, reqMdl);
//        //アカウントリストを取得
//        adMdlList = dao.getAccountList(userSid);
//        paramMdl.setAcntList(comonBiz.getAcntCombo(reqMdl, adMdlList));
        //アカウント名取得
        int selectSacSid = paramMdl.getSmlAccountSid();
        SmlAccountDao sacDao = new SmlAccountDao(con);
        SmlAccountModel sacMdl = sacDao.select(selectSacSid);
        paramMdl.setSml310accountName(sacMdl.getSacName());

        //ラベルリストを取得
        int dspCnt = paramMdl.getDspCount();
        if (dspCnt == 0 && adMdlList.size() != 0) {
            paramMdl.setSmlAccountSid(adMdlList.get(0).getAccountSid());
            paramMdl.setDspCount(1);
        }

        int selectAccountNum = paramMdl.getSmlAccountSid();
        ldMdlList = dao.getLabelList(selectAccountNum);
        LabelDataModel sldDspMdl = null;

        int count = 0;
        //ラベルを画面表示用に加工する
        List<LabelDataModel> lbList = new ArrayList<LabelDataModel>();

        for (LabelDataModel sldSetMdl : ldMdlList) {
            sldDspMdl = new LabelDataModel();
            sldDspMdl.setLabelSid(sldSetMdl.getLabelSid());
            sldDspMdl.setLabelName(sldSetMdl.getLabelName());
            sldDspMdl.setLabelOrder(sldSetMdl.getLabelOrder());
            sldDspMdl.setLbValue(
                    __getRadioValueStr(sldSetMdl.getLabelSid(), sldSetMdl.getLabelOrder(), count));
            count++;
            lbList.add(sldDspMdl);
        }
        paramMdl.setLbnList(lbList);

        //チェックされている並び順がNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getSml310SortRadio())
            && ldMdlList.size() > 0) {

            LabelDataModel sldMdl = ldMdlList.get(0);
            paramMdl.setSml310SortRadio(
                    __getRadioValueStr(sldMdl.getLabelSid(), sldMdl.getLabelOrder(), 0));
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
    public void updateSort(Connection con, Sml310ParamModel paramMdl, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getSml310sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getSml310SortRadio();
        if (StringUtil.isNullZeroString(selectedKey)) {
            return;
        }

        String[] selectKeyList = selectedKey.split(SORT_SEPARATE);

        //画面表示順
        int selectedKeyDispNum = Integer.parseInt(selectKeyList[2]);
        log__.debug("画面表示順 = " + selectedKeyDispNum);

        //画面の最初に表示されている項目 + 順位を上げる
        if (selectedKeyDispNum == 0 && changeKbn == GSConstSmail.SORT_UP) {
            return;
        //画面の最後に表示されている項目 + 順位を下げる
        } else if (selectedKeyDispNum == keyList.length - 1
                && changeKbn == GSConstSmail.SORT_DOWN) {
            return;
        }

        //選択された項目のラベルSID + ソート順
        int motoSid = Integer.parseInt(selectKeyList[0]);
        int motoSort = Integer.parseInt(selectKeyList[1]);

        int sakiSid = -1;
        int sakiSort = -1;
        int target = selectedKeyDispNum;

        if (changeKbn == GSConstSmail.SORT_UP) {
            target -= 1;
        } else if (changeKbn == GSConstSmail.SORT_DOWN) {
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
        Sml310Dao dao = new Sml310Dao(con);
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort);

        int newSort = sakiSort;

        //新しいキーを設定
        paramMdl.setSml310SortRadio(__getRadioValueStr(motoSid, newSort, target));
    }
}
