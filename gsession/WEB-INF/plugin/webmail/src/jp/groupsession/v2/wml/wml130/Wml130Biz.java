package jp.groupsession.v2.wml.wml130;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.dao.base.WmlFilterDao;
import jp.groupsession.v2.wml.dao.base.WmlFilterFwaddressDao;
import jp.groupsession.v2.wml.model.base.AccountDataModel;
import jp.groupsession.v2.wml.wml110.Wml110Dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール フィルタ設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml130Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml130Biz.class);
    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = ":";

    /**
     * <br>[機  能] フィルターの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    public void deleteFilter(Connection con, Wml130ParamModel paramMdl) throws SQLException {
        boolean commitFlg = false;

        try {
            //フィルターを削除する
            WmlFilterDao wfDao = new WmlFilterDao(con);
            wfDao.delete(paramMdl.getWmlEditFilterId());

            //フィルター_転送先メールアドレスを削除する
            WmlFilterFwaddressDao wfFwadrDao = new WmlFilterFwaddressDao(con);
            wfFwadrDao.delete(paramMdl.getWmlEditFilterId());

            //フィルター条件を削除する
            Wml130Dao dao = new Wml130Dao(con);
            dao.deleteFilterCondition(paramMdl.getWmlEditFilterId());

            //フィルター条件を削除する
            dao.deleteFilterSortCondition(paramMdl.getWmlEditFilterId());

            //コミット実行
            commitFlg = true;
        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
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
                             Wml130ParamModel paramMdl,
                             int userSid,
                             RequestModel reqMdl) throws SQLException {

        WmlBiz wmlBiz = new WmlBiz();
        Wml110Dao wml110dao = new Wml110Dao(con);

        //アカウントリストを取得
        List<AccountDataModel> adMdlList = wml110dao.getAccountList(userSid);
        paramMdl.setAcntList(wmlBiz.getAcntCombo(reqMdl, adMdlList));

        //フィルター情報を取得
        int dspCnt = paramMdl.getDspCount();
        if (dspCnt == 0 && adMdlList.size() != 0) {
            paramMdl.setWml130accountSid(adMdlList.get(0).getAccountSid());
            paramMdl.setDspCount(1);
        }

        Wml130Dao wml130dao = new Wml130Dao(con);
        int selectAccountNum = paramMdl.getWml130accountSid();
        List<Wml130FilterDataModel> fdMdlList = wml130dao.getFlterList(selectAccountNum, userSid);
        Wml130FilterDataModel wfdDspMdl = null;

        int count = 0;
        //フィルター情報を画面表示用に加工する
        List<Wml130FilterDataModel> filDspList = new ArrayList<Wml130FilterDataModel>();

        for (Wml130FilterDataModel wfdSetMdl : fdMdlList) {
            wfdDspMdl = new Wml130FilterDataModel();
            wfdDspMdl.setFilterSid(wfdSetMdl.getFilterSid());
            wfdDspMdl.setFilterName(wfdSetMdl.getFilterName());
            wfdDspMdl.setFilterSort(wfdSetMdl.getFilterSort());
            wfdDspMdl.setFilValue(
                    __getRadioValueStr(wfdSetMdl.getFilterSid(), wfdSetMdl.getFilterSort(), count));
            count++;
            filDspList.add(wfdDspMdl);
        }
        paramMdl.setFilList(filDspList);

        //チェックされている並び順がNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getWml130SortRadio())
            && fdMdlList.size() > 0) {

            Wml130FilterDataModel wldMdl = fdMdlList.get(0);
            paramMdl.setWml130SortRadio(
                    __getRadioValueStr(wldMdl.getFilterSid(), wldMdl.getFilterSort(), 0));
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
    public void updateSort(Connection con, Wml130ParamModel paramMdl, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getWml130sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getWml130SortRadio();
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

        //選択された項目のフィルターSID + ソート順
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
        for (String allKey : keyList) {

            String[] allKeyList = allKey.split(SORT_SEPARATE);
            int allKeyDispNum = Integer.parseInt(allKeyList[2]);

            if (allKeyDispNum == target) {
                sakiSid = Integer.parseInt(allKeyList[0]);
                sakiSort = Integer.parseInt(allKeyList[1]);
                break;
            }
        }

        if (sakiSid == -1 || sakiSort == -1) {
            return;
        }

        //順序入れ替え
        Wml130Dao dao = new Wml130Dao(con);
        dao.updateSort(motoSid, motoSort, sakiSid, sakiSort);

        //新しいキーを設定
        paramMdl.setWml130SortRadio(__getRadioValueStr(motoSid, sakiSort, target));
    }

}
