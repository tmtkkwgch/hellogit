package jp.groupsession.v2.cir.cir170kn;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.biz.CirCommonBiz;
import jp.groupsession.v2.cir.cir170.CircularCsvModel;
import jp.groupsession.v2.cir.cir170.CircularCsvReader;
import jp.groupsession.v2.cir.dao.CirAccountDao;
import jp.groupsession.v2.cir.dao.CirAccountSortDao;
import jp.groupsession.v2.cir.dao.CirAccountUserDao;
import jp.groupsession.v2.cir.model.CirAccountModel;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 回覧板 アカウントインポート確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir170knBiz {

    /** Loggingインスタンス */
    private static Log log__ = LogFactory.getLog(Cir170knBiz.class);


    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param tempDir テンポラリディレクトリパス
     * @throws Exception 実行時例外
     */
    public void setInitData(Connection con, Cir170knParamModel paramMdl, String tempDir)
    throws Exception {

        log__.debug("START");

        CirCommonBiz sBiz = new CirCommonBiz();

        //取込ファイル名を設定する
        List<LabelValueBean> fileLabel = sBiz.getFileCombo(tempDir);
        paramMdl.setCir170knFileName(fileLabel.get(0).getLabel());

        //取込アカウント情報を設定する
        Cir170knDao dao = new Cir170knDao(con);
        List<CircularCsvModel> accountList = getCircularList(tempDir);
        List<Cir170knUseUsrModel> modelList = new ArrayList<Cir170knUseUsrModel>();
        Cir170knUseUsrModel model = null;
        List<String> useUsrIds = null;

        for (int i = 0; i < accountList.size(); i++) {
            useUsrIds = new ArrayList<String>();
            model = new Cir170knUseUsrModel();
            model.setAccountName(accountList.get(i).getAccountName());
            useUsrIds.add(accountList.get(i).getUser1());
            useUsrIds.add(accountList.get(i).getUser2());
            useUsrIds.add(accountList.get(i).getUser3());
            useUsrIds.add(accountList.get(i).getUser4());
            useUsrIds.add(accountList.get(i).getUser5());
            model.setUserNameList(dao.getUseUserNameList(useUsrIds));
            modelList.add(model);
        }

        paramMdl.setCir170knUseUserList(modelList);

        log__.debug("End");
    }

    /**
     * <br>[機  能] アカウント情報の登録を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param tempDir テンポラリディレクトリ
     * @param mtCon 採番コントローラ
     * @param sessionUserSid セッションユーザSID
     * @throws Exception 実行時例外
     * @return List
     */
    public List<CircularCsvModel> addAccount(Connection con, RequestModel reqMdl,
                            String tempDir,
                            MlCountMtController mtCon, int sessionUserSid) throws Exception {
        log__.debug("START");

        List<CircularCsvModel> accountList = getCircularList(tempDir);
        Cir170knDao dao = new Cir170knDao(con);
        CirAccountDao accountDao = new CirAccountDao(con);
        CirAccountUserDao accountUserDao = new CirAccountUserDao(con);
        CirAccountSortDao accountSortDao = new CirAccountSortDao(con);
//        CirAccountDiskDao accountDiskDao = new CirAccountDiskDao(con);

        for (CircularCsvModel accountData : accountList) {
            //アカウント情報の登録
            CirAccountModel accountModel = new CirAccountModel();

            int wacSaiSid = (int) mtCon.getSaibanNumber(GSConstCircular.SBNSID_CIRCULAR,
                    GSConstCircular.SBNSID_SUB_ACCOUNT,
                    sessionUserSid);

            accountModel.setCacSid(wacSaiSid);
            accountModel.setCacName(accountData.getAccountName());
            accountModel.setCacType(GSConstCircular.CAC_TYPE_USER);
            accountModel.setCacBiko(accountData.getBiko());
            accountModel.setCacJkbn(GSConstCircular.CAC_JKBN_NORMAL);
            accountModel.setCacTheme(0);
            accountModel.setCacMemoKbn(GSConstCircular.CIR_INIT_MEMO_CHANGE_NO);
            accountModel.setCacMemoDay(GSConstCircular.CIR_INIT_MEMO_ONEWEEK);
            accountModel.setCacKouKbn(GSConstCircular.CIR_INIT_SAKI_PUBLIC);
            accountModel.setCacInitKbn(GSConstCircular.CIR_INIT_KBN_NOSET);
            accountModel.setCacInitKbn(GSConstCircular.CIR_INIT_KBN_SET);

            accountDao.insertAccount(accountModel, -1);

            //アカウント使用者情報を取得する
            List<String> usrDataList = new ArrayList<String>();
            List<String> useUsrIdList = new ArrayList<String>();
            useUsrIdList.add(accountData.getUser1());
            useUsrIdList.add(accountData.getUser2());
            useUsrIdList.add(accountData.getUser3());
            useUsrIdList.add(accountData.getUser4());
            useUsrIdList.add(accountData.getUser5());
            usrDataList = dao.getUseUserSidList(useUsrIdList);

            //アカウント使用者を登録する
            String[] usrSids =
                (String[]) usrDataList.toArray(new String[usrDataList.size()]);
            accountUserDao.insert(wacSaiSid, 0, usrSids);

            //アカウントソートの並び順を登録する
            for (int m = 0; m < usrSids.length; m++) {
                accountSortDao.insertAccountSort(wacSaiSid, Integer.parseInt(usrSids[m]));
            }

//            //アカウントディスク使用量を登録する
//            CirAccountDiskModel acntDiskMdl = new CirAccountDiskModel();
//            acntDiskMdl.setCacSid(wacSaiSid);
//            acntDiskMdl.setSdsSize(0);
//            accountDiskDao.insert(acntDiskMdl);
        }
        return accountList;
    }
    /**
     * <br>[機  能] CSVファイルからアカウント情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリ
     * @return アカウント情報一覧
     * @throws Exception 実行時例外
     */
    public List<CircularCsvModel> getCircularList(String tempDir) throws Exception {

        CirCommonBiz sBiz = new CirCommonBiz();
        List<Cmn110FileModel> fileDataList = sBiz.getFileData(tempDir);
        String fullPath = tempDir + fileDataList.get(0).getSaveFileName();
        CircularCsvReader csvReader = new CircularCsvReader();
        csvReader.readCsvFile(fullPath);

        return csvReader.getCircularList();
    }
}
