package jp.groupsession.v2.sml.sml270;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmlAccountSortDao;
import jp.groupsession.v2.sml.model.AccountDataModel;
import jp.groupsession.v2.sml.model.SmlAccountSortModel;
import jp.groupsession.v2.sml.model.SmlAdminModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ショートメール アカウントの管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml270Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml270Biz.class);
    /** 並び順分割文字列 */
    private static final String SORT_SEPARATE = ":";

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param reqMdl RequestModel
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Connection con,
                             Sml270ParamModel paramMdl,
                             int userSid,
                             RequestModel reqMdl) throws SQLException {

        //アカウント情報を取得する
        Sml270Dao dao = new Sml270Dao(con);
        List<AccountDataModel> accountList = dao.getAccountList(userSid);

        //アカウント並び順再登録
        SmlAccountSortDao sortDao = new SmlAccountSortDao(con);
        sortDao.deleteAllSort(userSid);
        SmlAccountSortModel mdl = null;
        for (int i = 0; i < accountList.size(); i++) {
            AccountDataModel aMdl = accountList.get(i);
            mdl = new SmlAccountSortModel();
            mdl.setSacSid(aMdl.getAccountSid());
            mdl.setUsrSid(userSid);
            mdl.setSasSort(i);
            sortDao.insert(mdl);
        }

        List<Sml270AccountDataDspModel> acDspList = new ArrayList<Sml270AccountDataDspModel>();
        Sml270AccountDataDspModel acDatadspMdl = null;
        int count = 0;
        accountList = dao.getAccountList(userSid);
        for (AccountDataModel acDataMdl : accountList) {
            acDatadspMdl = new Sml270AccountDataDspModel();

            acDatadspMdl.setAccountSid(acDataMdl.getAccountSid());
            acDatadspMdl.setAccountName(acDataMdl.getAccountName());
            acDatadspMdl.setAccountType(acDataMdl.getAccountType());
            acDatadspMdl.setAccountBiko(
                    StringUtilHtml.transToHTmlPlusAmparsant(acDataMdl.getAccountBiko()));
            acDatadspMdl.setAccountSort((int) acDataMdl.getAccountSort());
            acDatadspMdl.setAcValue(
                    __getRadioValueStr(acDatadspMdl.getAccountSid(),
                                       acDatadspMdl.getAccountSort(),
                                       count));

            count++;
            acDspList.add(acDatadspMdl);
        }

        paramMdl.setAccountList(acDspList);

        //チェックされている並び順がNULLの場合、初期値設定
        if (StringUtil.isNullZeroString(paramMdl.getSml270sortAccount())
            && accountList.size() > 0) {

            Sml270AccountDataDspModel addspMdl = acDspList.get(0);
            paramMdl.setSml270sortAccount(
                    __getRadioValueStr(addspMdl.getAccountSid(), addspMdl.getAccountSort(), 0));
        }

        //非管理者でもアカウントを登録できるかどうか
        SmlCommonBiz cmnBiz = new SmlCommonBiz(con, reqMdl);
        SmlAdminModel samMdl = cmnBiz.getSmailAdminConf(userSid, con);

        if (samMdl.getSmaAcntMake() == GSConstSmail.KANRI_USER_ONLY) {
            paramMdl.setSml270MakeAcntHnt(GSConstSmail.ACCOUNT_ADD_NG);
        }

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
    public void updateSort(Connection con, Sml270ParamModel paramMdl, int userSid, int changeKbn)
        throws SQLException {

        //画面表示全キーリスト取得
        String[] keyList = paramMdl.getSml270sortLabel();
        if (keyList == null || keyList.length < 1) {
            return;
        }

        //ラジオ選択値取得
        String selectedKey = paramMdl.getSml270sortAccount();
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

        //選択された項目のフィルターSID + ソート順
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

        //アカウント並び順の入替を行う
        SmlAccountSortDao sortDao = new SmlAccountSortDao(con);
        sortDao.delete(motoSid, userSid);
        sortDao.delete(sakiSid, userSid);

        SmlAccountSortModel sortModel = new SmlAccountSortModel();
        sortModel.setUsrSid(userSid);

        sortModel.setSacSid(motoSid);
        sortModel.setSasSort(target);
        sortDao.insert(sortModel);

        sortModel.setSacSid(sakiSid);
        sortModel.setSasSort(motoSort);
        sortDao.insert(sortModel);

        //新しいキーを設定
        paramMdl.setSml270sortAccount(__getRadioValueStr(motoSid, sakiSort, target));
    }
}
