package jp.groupsession.v2.wml.wml190kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.dao.base.WmlAccountDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountSignDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountSortDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountUserProxyDao;
import jp.groupsession.v2.wml.dao.base.WmlAdmConfDao;
import jp.groupsession.v2.wml.model.base.WmlAccountModel;
import jp.groupsession.v2.wml.model.base.WmlAccountSignModel;
import jp.groupsession.v2.wml.model.base.WmlAdmConfModel;
import jp.groupsession.v2.wml.wml040.Wml040Biz;
import jp.groupsession.v2.wml.wml040.Wml040SignModel;
import jp.groupsession.v2.wml.wml040kn.Wml040knBiz;
import jp.groupsession.v2.wml.wml190.Wml190Biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール 個人設定 アカウント編集確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml190knBiz extends Wml190Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml190knBiz.class);

    /**
     * <br>[機  能] 初期表示設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param tempRootDir テンポラリルートディレクトリ
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException 署名情報の読み込みに失敗
     */
    public void setInitData(Connection con, Wml190knParamModel paramMdl,
                                    RequestModel reqMdl, String tempRootDir)
    throws SQLException, IOToolsException {

        //署名を設定
        Wml040Biz biz040 = new Wml040Biz();
        paramMdl.setWml190signList(biz040.createSignCombo(reqMdl, tempRootDir));

        //サーバ情報設定許可を設定
        paramMdl.setWml190settingServer(getSettingServer(con));

        //テーマ(表示用)を設定
        Wml040knBiz biz040kn = new Wml040knBiz();
        paramMdl.setWml190knTheme(
                biz040kn.getThemeName(con, reqMdl, paramMdl.getWml190theme()));

        //引用符(表示用)を設定
        paramMdl.setWml190knQuotes(
                WmlBiz.getViewMailQuotes(paramMdl.getWml190quotes(), reqMdl));

        //代理人を設定
        WmlBiz wmlBiz = new WmlBiz();
        paramMdl.setWml190proxyUserFlg(wmlBiz.isProxyUserAllowed(con));
        if (paramMdl.isWml190proxyUserFlg()) {
            _setProxyUserCombo(con, paramMdl, reqMdl);
        }
    }

    /**
     * <br>[機  能] アカウント情報の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param mtCon 採番コントローラ
     * @param tempRootDir テンポラリルートディレクトリ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 署名情報の読み込みに失敗
     */
    public void entryAccountData(Connection con, Wml190knParamModel paramMdl,
                                RequestModel reqMdl,
                                MlCountMtController mtCon,
                                String tempRootDir)
    throws SQLException, IOToolsException {

       log__.debug("START");

       int wacSid = paramMdl.getWmlAccountSid();

       //アカウント情報の登録
       WmlAccountModel accountModel = new WmlAccountModel();

       accountModel.setWacSid(wacSid);
       accountModel.setUsrSid(reqMdl.getSmodel().getUsrsid());
       accountModel.setWacType(GSConstWebmail.WAC_TYPE_NORMAL);
       accountModel.setWacName(paramMdl.getWml190name());
       accountModel.setWacAutoto(paramMdl.getWml190autoTo());
       accountModel.setWacAutocc(paramMdl.getWml190autoCc());
       accountModel.setWacAutobcc(paramMdl.getWml190autoBcc());
       accountModel.setWacTheme(paramMdl.getWml190theme());
       accountModel.setWacQuotes(paramMdl.getWml190quotes());

       WmlAdmConfDao adminDao = new WmlAdmConfDao(con);
       WmlAdmConfModel adminMdl = adminDao.selectAdmData();
       int settingServer = adminMdl.getWadSettingServer();

       if (settingServer == GSConstWebmail.WAD_SETTING_SERVER_YES) {
           accountModel.setWacReceiveHost(paramMdl.getWml190receiveServer());
           accountModel.setWacReceivePort(Integer.parseInt(paramMdl.getWml190receiveServerPort()));
           accountModel.setWacReceiveUser(paramMdl.getWml190receiveServerUser());
           accountModel.setWacReceivePass(paramMdl.getWml190receiveServerPassword());
           accountModel.setWacReceiveSsl(GSConstWebmail.WAC_RECEIVE_SSL_NOTUSE);
           if (paramMdl.getWml190receiveServerSsl() == GSConstWebmail.WAC_RECEIVE_SSL_USE) {
               accountModel.setWacReceiveSsl(GSConstWebmail.WAC_RECEIVE_SSL_USE);
           }

           accountModel.setWacSendHost(paramMdl.getWml190sendServer());
           accountModel.setWacSendPort(Integer.parseInt(paramMdl.getWml190sendServerPort()));
           accountModel.setWacSendSsl(GSConstWebmail.WAC_SEND_SSL_NOTUSE);
           if (paramMdl.getWml190sendServerSsl() == GSConstWebmail.WAC_SEND_SSL_USE) {
               accountModel.setWacSendSsl(GSConstWebmail.WAC_SEND_SSL_USE);
           }

           accountModel.setWacSmtpAuth(GSConstWebmail.WAC_SMTPAUTH_NO);
           if (paramMdl.getWml190smtpAuth() == GSConstWebmail.WAC_SMTPAUTH_YES) {
               accountModel.setWacSmtpAuth(GSConstWebmail.WAC_SMTPAUTH_YES);
               accountModel.setWacSendUser(paramMdl.getWml190sendServerUser());
               accountModel.setWacSendPass(paramMdl.getWml190sendServerPassword());
           }
       }


       WmlAccountDao accountDao = new WmlAccountDao(con);
       accountDao.updateAccountEdit(accountModel, paramMdl.getWmlAccountMode(),
                                                   settingServer);

       //アカウント署名
       WmlAccountSignDao signDao = new WmlAccountSignDao(con);

       //既存の署名情報を削除する
       signDao.delete(accountModel.getWacSid());

       //署名情報を登録する
       Wml040Biz biz040 = new Wml040Biz();
       Wml040SignModel signData = biz040.loadSignModel(reqMdl, tempRootDir);
       for (WmlAccountSignModel signDetail : signData.getSignList()) {
           signDetail.setWacSid(accountModel.getWacSid());
           if (signDetail.getWsiNo() == 1) {
               signDetail.setWsiDef(GSConstWebmail.WSI_DEF_DEFAULT);
           } else {
               signDetail.setWsiDef(GSConstWebmail.WSI_DEF_NORMAL);
           }
           signDao.insert(signDetail);
       }


       //代理人が許可されているかを取得
       WmlBiz wmlBiz = new WmlBiz();
       boolean proxyUserAllowed = wmlBiz.isProxyUserAllowed(con);
       WmlAccountSortDao accountSortDao = new WmlAccountSortDao(con);

       //既存の代理人を削除する
       if (proxyUserAllowed) {
           String[] proxyUser = paramMdl.getWml190proxyUser();

           WmlAccountUserProxyDao proxyUserDao = new WmlAccountUserProxyDao(con);
           proxyUserDao.deleteProxyUser(wacSid);

           accountSortDao.delAccountSortProxyUser(wacSid, proxyUser);
           if (proxyUser != null && proxyUser.length > 0) {
               accountSortDao.updateAccountSortUsr(wacSid, proxyUser, wacSid);
               proxyUserDao.insertProxyUser(accountModel.getWacSid(), proxyUser);
           }
       }

    }
}
