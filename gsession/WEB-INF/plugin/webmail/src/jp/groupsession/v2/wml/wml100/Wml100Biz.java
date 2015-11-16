package jp.groupsession.v2.wml.wml100;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.dao.base.WmlAccountSortDao;
import jp.groupsession.v2.wml.dao.base.WmlAdmConfDao;
import jp.groupsession.v2.wml.model.base.AccountDataModel;
import jp.groupsession.v2.wml.model.base.WmlAccountSortModel;
import jp.groupsession.v2.wml.model.base.WmlAdmConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール アカウントの管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml100Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml100Biz.class);
    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = ":";

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Connection con,
                             Wml100ParamModel paramMdl,
                             int userSid) throws SQLException {

        //アカウント情報を取得する
        Wml100Dao dao = new Wml100Dao(con);
        List<AccountDataModel> accountList = dao.getAccountList(userSid);
        List<Wml100AccountDataDspModel> acDspList = new ArrayList<Wml100AccountDataDspModel>();
        Wml100AccountDataDspModel acDatadspMdl = null;
        int count = 0;

        for (AccountDataModel acDataMdl : accountList) {
            acDatadspMdl = new Wml100AccountDataDspModel();

            acDatadspMdl.setAccountSid(acDataMdl.getAccountSid());
            acDatadspMdl.setAccountName(acDataMdl.getAccountName());
            acDatadspMdl.setAccountType(acDataMdl.getAccountType());
            acDatadspMdl.setAccountAddress(acDataMdl.getAccountAddress());
            acDatadspMdl.setAccountBiko(acDataMdl.getAccountBiko());
            acDatadspMdl.setAccountSort((int) acDataMdl.getAccountSort());
            acDatadspMdl.setAcValue(
                    __getRadioValueStr(acDatadspMdl.getAccountSid(),
                                       acDatadspMdl.getAccountSort(),
                                       count));
            if (acDataMdl.getReceiveDate() != null) {
                String dateString = UDateUtil.getSlashYYMD(acDataMdl.getReceiveDate());
                dateString += " " + UDateUtil.getSeparateHMS(acDataMdl.getReceiveDate());
                acDatadspMdl.setReceiveDate(dateString);
            }

            //アカウント使用ユーザかを判定
            WmlBiz wmlBiz = new WmlBiz();
            List<Integer> useUserList
                = wmlBiz.getAccountUserList(con, acDataMdl.getAccountSid(), false);
            acDatadspMdl.setAccountUseUser(useUserList.contains(userSid));

            count++;
            acDspList.add(acDatadspMdl);
        }
        paramMdl.setAccountList(acDspList);

        //チェックされている並び順がNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getWml100sortAccount())
            && accountList.size() > 0) {

            Wml100AccountDataDspModel addspMdl = acDspList.get(0);
            paramMdl.setWml100sortAccount(
                    __getRadioValueStr(addspMdl.getAccountSid(), addspMdl.getAccountSort(), 0));
        }

        //非管理者でもアカウントを登録できるかどうか
        WmlAdmConfDao wacDao = new WmlAdmConfDao(con);
        WmlAdmConfModel wacMdl = new WmlAdmConfModel();
        wacMdl = wacDao.selectAdmData();
        paramMdl.setWml100MakeAcntHnt(wacMdl.getWadAcntMake());
    }

    /**
     * <br>[機  能] radioにセットする値(文字列)を取得する
     * <br>[解  説] 「アカウントSID-表示順-画面上の表示順」のフォーマットの文字列を返す
     * <br>[備  考]
     * @param lbSid アカウントSID
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
     * @param userSid ユーザSID
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(Connection con, Wml100ParamModel paramMdl, int userSid, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getWml100sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getWml100sortAccount();
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

        Wml100Dao dao = new Wml100Dao(con);
        WmlAccountSortDao sortDao = new WmlAccountSortDao(con);

        List<WmlAccountSortModel> bfSortList = new ArrayList<WmlAccountSortModel>();
        List<WmlAccountSortModel> sortList = new ArrayList<WmlAccountSortModel>();
        WmlAccountSortModel sortMdl = null;
        boolean updateFlg = false;
        int sakiSort = -1;

        WmlBiz wmlBiz = new WmlBiz();
        boolean proxyUserFlg = wmlBiz.isProxyUserAllowed(con);

        List<Integer> sids = new ArrayList<Integer>();
        List<Integer> selSids = new ArrayList<Integer>();
        sids = dao.getAccountSidList(userSid, proxyUserFlg);

        bfSortList = sortDao.select(userSid, sids);

        //使用可能なアカウント
        if (bfSortList != null && !bfSortList.isEmpty()) {
            for (WmlAccountSortModel sMdl : bfSortList) {
                if (sMdl != null) {
                    selSids.add(sMdl.getWacSid());
                }
            }
        }

        sortList.addAll(sortDao.select(userSid, sids));

        //使用できるが、順番が指定されていないアカウント
        if (sids != null && !sids.isEmpty()) {
            for (int id : sids) {
                if (selSids.indexOf(id) == -1) {
                    sortMdl = new WmlAccountSortModel();
                    sortMdl.setWacSid(id);
                    sortMdl.setUsrSid(userSid);
                    sortList.add(sortMdl);
                }
            }
        }

        if (sortList != null && !sortList.isEmpty()) {
            for (int i = 0; i < sortList.size(); i++) {
                if (sortList.get(i) != null) {
                    sortList.get(i).setWasSort(i + 1);
                }
            }

            for (int i = 0; i < sortList.size(); i++) {
                sortMdl = sortList.get(i);
                if (sortMdl.getWacSid() == Integer.parseInt(selectKeyList[0])) {

                    if (changeKbn == GSConstWebmail.SORT_UP) {
                        if (i != 0) {
                            long order = sortMdl.getWasSort();
                            if (order > 1) {
                                sortList.get(i).setWasSort(order - 1);
                                sakiSort = Integer.valueOf(String.valueOf(order - 1));
                                if (sortList.get(i - 1) != null) {
                                    sortList.get(i - 1).setWasSort(order);
                                }
                                updateFlg = true;
                            }
                        }
                    } else if (changeKbn == GSConstWebmail.SORT_DOWN) {

                        if (i != sortList.size()) {
                            long order = sortMdl.getWasSort();
                            if (order < sortList.size()) {
                                sortList.get(i).setWasSort(order + 1);
                                sakiSort = Integer.valueOf(String.valueOf(order + 1));
                                if (sortList.get(i + 1) != null) {
                                    sortList.get(i + 1).setWasSort(order);
                                }
                                updateFlg = true;
                            }
                        }
                    }
                }
            }

            if (updateFlg) {
                sortDao.delete(userSid);
                for (WmlAccountSortModel sMdl : sortList) {
                    sortDao.insert(sMdl);
                }

                //新しいキーを設定
                paramMdl.setWml100sortAccount(__getRadioValueStr(
                        Integer.parseInt(selectKeyList[0]), sakiSort, sakiSort - 1));
            }
        } else {
            List<Integer> accountSidList = dao.getAccountSidList(userSid, proxyUserFlg);
            if (accountSidList != null && !accountSidList.isEmpty()) {
                sortDao.delete(userSid);
                WmlAccountSortModel mdl = null;
                for (int i = 0; i < accountSidList.size(); i++) {
                    mdl = new WmlAccountSortModel();
                    mdl.setWacSid(accountSidList.get(i));
                    mdl.setUsrSid(userSid);
                    mdl.setWasSort(i + 1);
                    sortDao.insert(mdl);
                }
            }
        }
    }
}
