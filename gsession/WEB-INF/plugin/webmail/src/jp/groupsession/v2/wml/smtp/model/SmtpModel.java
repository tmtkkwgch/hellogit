package jp.groupsession.v2.wml.smtp.model;

import java.io.Serializable;
import java.util.List;

import jp.co.sjts.util.Encoding;
import jp.groupsession.v2.cmn.model.base.WmlMailFileModel;

/**
 * <br>[機  能] メール送信時に必要な情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class SmtpModel implements Serializable {

    /** 送信サーバ */
    private String sendServer__ = null;
    /** 送信サーバ　ポート */
    private int sendPort__ = 25;
    /** SMTP認証 */
    private boolean smtpAuth__ = false;
    /** 送信サーバ ユーザ */
    private String sendUser__ = null;
    /** 送信サーバ パスワード */
    private String sendPassword__ = null;
    /** 文字コード */
    private String encode__ = Encoding.ISO_2022_JP;

    /** SSL */
    private boolean ssl__ = false;
    /** POP before SMTP */
    private boolean popBeforeSmtp__ = false;
    /** POPサーバ */
    private String popServer__ = null;
    /** POPサーバ ポート */
    private int popServerPort__ = 110;
    /** POPサーバ ユーザ */
    private String popServerUser__ = null;
    /** POPサーバ パスワード */
    private String popServerPassword__ = null;
    /** POPサーバ SSL */
    private boolean popServerSsl__ = false;

    /** 添付ファイル情報 */
    private List<WmlMailFileModel> tempFileList__ = null;

    /**
     * <p>popServer を取得します。
     * @return popServer
     */
    public String getPopServer() {
        return popServer__;
    }
    /**
     * <p>popServer をセットします。
     * @param popServer popServer
     */
    public void setPopServer(String popServer) {
        popServer__ = popServer;
    }
    /**
     * <p>popServerPassword を取得します。
     * @return popServerPassword
     */
    public String getPopServerPassword() {
        return popServerPassword__;
    }
    /**
     * <p>popServerPassword をセットします。
     * @param popServerPassword popServerPassword
     */
    public void setPopServerPassword(String popServerPassword) {
        popServerPassword__ = popServerPassword;
    }
    /**
     * <p>popServerPort を取得します。
     * @return popServerPort
     */
    public int getPopServerPort() {
        return popServerPort__;
    }
    /**
     * <p>popServerPort をセットします。
     * @param popServerPort popServerPort
     */
    public void setPopServerPort(int popServerPort) {
        popServerPort__ = popServerPort;
    }
    /**
     * <p>popServerUser を取得します。
     * @return popServerUser
     */
    public String getPopServerUser() {
        return popServerUser__;
    }
    /**
     * <p>popServerUser をセットします。
     * @param popServerUser popServerUser
     */
    public void setPopServerUser(String popServerUser) {
        popServerUser__ = popServerUser;
    }
    /**
     * <p>popBeforeSmtp を取得します。
     * @return popBeforeSmtp
     */
    public boolean isPopBeforeSmtp() {
        return popBeforeSmtp__;
    }
    /**
     * <p>popBeforeSmtp をセットします。
     * @param popBeforeSmtp popBeforeSmtp
     */
    public void setPopBeforeSmtp(boolean popBeforeSmtp) {
        popBeforeSmtp__ = popBeforeSmtp;
    }
    /**
     * <p>sendPassword を取得します。
     * @return sendPassword
     */
    public String getSendPassword() {
        return sendPassword__;
    }
    /**
     * <p>sendPassword をセットします。
     * @param sendPassword sendPassword
     */
    public void setSendPassword(String sendPassword) {
        sendPassword__ = sendPassword;
    }
    /**
     * <p>sendPort を取得します。
     * @return sendPort
     */
    public int getSendPort() {
        return sendPort__;
    }
    /**
     * <p>sendPort をセットします。
     * @param sendPort sendPort
     */
    public void setSendPort(int sendPort) {
        sendPort__ = sendPort;
    }
    /**
     * <p>sendServer を取得します。
     * @return sendServer
     */
    public String getSendServer() {
        return sendServer__;
    }
    /**
     * <p>sendServer をセットします。
     * @param sendServer sendServer
     */
    public void setSendServer(String sendServer) {
        sendServer__ = sendServer;
    }
    /**
     * <p>sendUser を取得します。
     * @return sendUser
     */
    public String getSendUser() {
        return sendUser__;
    }
    /**
     * <p>sendUser をセットします。
     * @param sendUser sendUser
     */
    public void setSendUser(String sendUser) {
        sendUser__ = sendUser;
    }
    /**
     * <p>smtpAuth を取得します。
     * @return smtpAuth
     */
    public boolean isSmtpAuth() {
        return smtpAuth__;
    }
    /**
     * <p>smtpAuth をセットします。
     * @param smtpAuth smtpAuth
     */
    public void setSmtpAuth(boolean smtpAuth) {
        smtpAuth__ = smtpAuth;
    }
    /**
     * <p>encode を取得します。
     * @return encode
     */
    public String getEncode() {
        return encode__;
    }
    /**
     * <p>encode をセットします。
     * @param encode encode
     */
    public void setEncode(String encode) {
        encode__ = encode;
    }
    /**
     * <p>ssl を取得します。
     * @return ssl
     */
    public boolean isSsl() {
        return ssl__;
    }
    /**
     * <p>ssl をセットします。
     * @param ssl ssl
     */
    public void setSsl(boolean ssl) {
        ssl__ = ssl;
    }
    /**
     * <p>popServerSsl を取得します。
     * @return popServerSsl
     */
    public boolean isPopServerSsl() {
        return popServerSsl__;
    }
    /**
     * <p>popServerSsl をセットします。
     * @param popServerSsl popServerSsl
     */
    public void setPopServerSsl(boolean popServerSsl) {
        popServerSsl__ = popServerSsl;
    }
    /**
     * <p>tempFileList を取得します。
     * @return tempFileList
     */
    public List<WmlMailFileModel> getTempFileList() {
        return tempFileList__;
    }
    /**
     * <p>tempFileList をセットします。
     * @param tempFileList tempFileList
     */
    public void setTempFileList(List<WmlMailFileModel> tempFileList) {
        tempFileList__ = tempFileList;
    }
}
