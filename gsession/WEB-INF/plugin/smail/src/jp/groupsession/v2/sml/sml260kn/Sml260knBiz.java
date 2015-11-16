package jp.groupsession.v2.sml.sml260kn;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmlAccountDao;
import jp.groupsession.v2.sml.dao.SmlAccountDiskDao;
import jp.groupsession.v2.sml.dao.SmlAccountSortDao;
import jp.groupsession.v2.sml.dao.SmlAccountUserDao;
import jp.groupsession.v2.sml.model.SmlAccountDiskModel;
import jp.groupsession.v2.sml.model.SmlAccountModel;
import jp.groupsession.v2.sml.sml260.SmailCsvModel;
import jp.groupsession.v2.sml.sml260.SmailCsvReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ショートメール アカウントインポート確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml260knBiz {

    /** Loggingインスタンス */
    private static Log log__ = LogFactory.getLog(Sml260knBiz.class);


    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param tempDir テンポラリディレクトリパス
     * @throws Exception 実行時例外
     */
    public void setInitData(Connection con, Sml260knParamModel paramMdl, String tempDir)
    throws Exception {

        log__.debug("START");

        SmlCommonBiz sBiz = new SmlCommonBiz();

        //取込ファイル名を設定する
        List<LabelValueBean> fileLabel = sBiz.getFileCombo(tempDir);
        paramMdl.setSml260knFileName(fileLabel.get(0).getLabel());

        //取込アカウント情報を設定する
        Sml260knDao dao = new Sml260knDao(con);
        List<SmailCsvModel> accountList = getSmailList(tempDir);
        List<Sml260knUseUsrModel> modelList = new ArrayList<Sml260knUseUsrModel>();
        Sml260knUseUsrModel model = null;
        List<String> useUsrIds = null;

        for (int i = 0; i < accountList.size(); i++) {
            useUsrIds = new ArrayList<String>();
            model = new Sml260knUseUsrModel();
            model.setAccountName(accountList.get(i).getAccountName());
            useUsrIds.add(accountList.get(i).getUser1());
            useUsrIds.add(accountList.get(i).getUser2());
            useUsrIds.add(accountList.get(i).getUser3());
            useUsrIds.add(accountList.get(i).getUser4());
            useUsrIds.add(accountList.get(i).getUser5());
            model.setUserNameList(dao.getUseUserNameList(useUsrIds));
            modelList.add(model);
        }

        paramMdl.setSml260knUseUserList(modelList);

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
    public List<SmailCsvModel> addAccount(Connection con, RequestModel reqMdl,
                            String tempDir,
                            MlCountMtController mtCon, int sessionUserSid) throws Exception {
        log__.debug("START");

        List<SmailCsvModel> accountList = getSmailList(tempDir);
        Sml260knDao dao = new Sml260knDao(con);
        SmlAccountDao accountDao = new SmlAccountDao(con);
        SmlAccountUserDao accountUserDao = new SmlAccountUserDao(con);
        SmlAccountSortDao accountSortDao = new SmlAccountSortDao(con);
        SmlAccountDiskDao accountDiskDao = new SmlAccountDiskDao(con);

        for (SmailCsvModel accountData : accountList) {
            //アカウント情報の登録
            SmlAccountModel accountModel = new SmlAccountModel();

            int wacSaiSid = (int) mtCon.getSaibanNumber(GSConstSmail.SAIBAN_SML_SID,
                    GSConstSmail.SBNSID_SUB_ACCOUNT,
                    sessionUserSid);

            accountModel.setSacSid(wacSaiSid);
            accountModel.setSacName(accountData.getAccountName());
            accountModel.setSacType(GSConstSmail.SAC_TYPE_USER);
            accountModel.setSacBiko(accountData.getBiko());
            accountModel.setSacSendMailtype(Integer.parseInt(accountData.getSndMailType()));
            accountModel.setSacJkbn(GSConstSmail.SAC_JKBN_NORMAL);


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

            //アカウントディスク使用量を登録する
            SmlAccountDiskModel acntDiskMdl = new SmlAccountDiskModel();
            acntDiskMdl.setSacSid(wacSaiSid);
            acntDiskMdl.setSdsSize(0);
            accountDiskDao.insert(acntDiskMdl);
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
    public List<SmailCsvModel> getSmailList(String tempDir) throws Exception {

        SmlCommonBiz sBiz = new SmlCommonBiz();
        List<Cmn110FileModel> fileDataList = sBiz.getFileData(tempDir);
        String fullPath = tempDir + fileDataList.get(0).getSaveFileName();
        SmailCsvReader csvReader = new SmailCsvReader();
        csvReader.readCsvFile(fullPath);

        return csvReader.getSmailList();
    }
}
