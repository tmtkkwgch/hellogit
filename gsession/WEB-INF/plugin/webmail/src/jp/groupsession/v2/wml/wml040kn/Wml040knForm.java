package jp.groupsession.v2.wml.wml040kn;

import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.model.WmlReceiveServerModel;
import jp.groupsession.v2.wml.pop3.Pop3Server;
import jp.groupsession.v2.wml.smtp.WmlSmtpSender;
import jp.groupsession.v2.wml.smtp.model.SmtpModel;
import jp.groupsession.v2.wml.wml040.Wml040Form;


/**
 * <br>[機  能] WEBメール アカウント登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml040knForm extends Wml040Form {

    /** 備考 表示用 */
    private String wml040knBiko__ = null;
    /** 署名 表示用 */
    private String wml040knSign__ = null;
    /** テーマ 表示用 */
    private String wml040knTheme__ = null;
    /** 引用符 表示用 */
    private String wml040knQuotes__ = null;

    /**
     * <p>wml040knBiko を取得します。
     * @return wml040knBiko
     */
    public String getWml040knBiko() {
        return wml040knBiko__;
    }
    /**
     * <p>wml040knBiko をセットします。
     * @param wml040knBiko wml040knBiko
     */
    public void setWml040knBiko(String wml040knBiko) {
        wml040knBiko__ = wml040knBiko;
    }
    /**
     * <p>wml040knSign を取得します。
     * @return wml040knSign
     */
    public String getWml040knSign() {
        return wml040knSign__;
    }
    /**
     * <p>wml040knSign をセットします。
     * @param wml040knSign wml040knSign
     */
    public void setWml040knSign(String wml040knSign) {
        wml040knSign__ = wml040knSign;
    }
    /**
     * <p>wml040knTheme を取得します。
     * @return wml040knTheme
     */
    public String getWml040knTheme() {
        return wml040knTheme__;
    }
    /**
     * <p>wml040knTheme をセットします。
     * @param wml040knTheme wml040knTheme
     */
    public void setWml040knTheme(String wml040knTheme) {
        wml040knTheme__ = wml040knTheme;
    }
    /**
     * <p>wml040knQuotes を取得します。
     * @return wml040knQuotes
     */
    public String getWml040knQuotes() {
        return wml040knQuotes__;
    }
    /**
     * <p>wml040knQuotes をセットします。
     * @param wml040knQuotes wml040knQuotes
     */
    public void setWml040knQuotes(String wml040knQuotes) {
        wml040knQuotes__ = wml040knQuotes;
    }

    /**
     * <br>[機  能] メール受信サーバの接続情報チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションルートパス
     * @return true: 正常 false:不正
     * @throws Exception 受信サーバまたは送信サーバの接続close時に例外発生
     */
    public boolean checkReceiveConnect(String appRootPath) throws Exception {
        //メール受信サーバの接続テスト
        WmlReceiveServerModel receiveMdl = new WmlReceiveServerModel();
        receiveMdl.setHost(getWml040receiveServer());
        receiveMdl.setPort(Integer.parseInt(getWml040receiveServerPort()));
        receiveMdl.setUser(getWml040receiveServerUser());
        receiveMdl.setPassword(getWml040receiveServerPassword());
        receiveMdl.setSsl(getWml040receiveServerSsl() == RECEIVE_SSL_USE);
        receiveMdl.setReceiveConnectTimeout(
                WmlBiz.getConfValue(appRootPath,
                        GSConstWebmail.MAILCONF_RECEIVE_CONNECTTIMEOUT,
                        GSConstWebmail.RECEIVE_CONNECTTIMEOUT_DEFAULT));
        receiveMdl.setReceiveTimeout(
                WmlBiz.getConfValue(appRootPath,
                        GSConstWebmail.MAILCONF_RECEIVE_TIMEOUT,
                        GSConstWebmail.RECEIVE_TIMEOUT_DEFAULT));

        Pop3Server receiveServer = new Pop3Server();
        return receiveServer.checkPopServer(receiveMdl);
    }

    /**
     * <br>[機  能] メール送信サーバの接続情報チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @return true: 正常 false:不正
     * @throws Exception 受信サーバまたは送信サーバの接続close時に例外発生
     */
    public boolean checkSendConnect() throws Exception {

        boolean result = false;
        WmlSmtpSender sender = null;
        try {
            SmtpModel smtpData = new SmtpModel();
            smtpData.setSendServer(getWml040sendServer());
            smtpData.setSendPort(Integer.parseInt(getWml040sendServerPort()));
            if (!StringUtil.isNullZeroString(getWml040sendServerUser())) {
                smtpData.setSendUser(getWml040sendServerUser());
                smtpData.setSendPassword(getWml040sendServerPassword());
                smtpData.setSmtpAuth(true);
            } else {
                smtpData.setSmtpAuth(false);
            }

            smtpData.setPopBeforeSmtp(getWml040popBSmtp() == POPBSMTP_ON);
            if (smtpData.isPopBeforeSmtp()) {
                smtpData.setPopServer(getWml040receiveServer());
                smtpData.setPopServerPort(Integer.parseInt(getWml040receiveServerPort()));
                smtpData.setPopServerUser(getWml040receiveServerUser());
                smtpData.setPopServerPassword(getWml040receiveServerPassword());
                smtpData.setPopServerSsl(getWml040receiveServerSsl() == SEND_SSL_USE);
            }
            smtpData.setSsl(getWml040sendServerSsl() == SEND_SSL_USE);

            sender = new WmlSmtpSender();
            sender.connect(smtpData);
            result = true;
        } catch (Exception e) {
            result = false;
        } finally {
            if (sender != null) {
                sender.disConnect();
            }
        }

        return result;
    }
}
