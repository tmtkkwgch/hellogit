package jp.groupsession.v2.wml.model;

import java.io.Serializable;

import jp.co.sjts.util.Encoding;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.wml.biz.WmlBiz;

/**
 * <br>[機  能] メール受信サーバの接続情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class WmlReceiveServerModel implements Serializable {

    /** サーバ */
    private String host__ = null;
    /** ポート */
    private int port__ = -1;
    /** ユーザ名 */
    private String user__ = null;
    /** ユーザのパスワード */
    private String password__ = null;
    /** SSL使用フラグ */
    private boolean ssl__ = false;
    /** アカウントSID */
    private int wacSid__ = 0;
    /** アカウント名 */
    private String accountName__ = null;
    /** アカウント文字列 */
    private String accountString__ = null;
    /** アカウントメールアドレス */
    private String accountMailAddress__ = null;
    /** ディスク容量制限 */
    private boolean diskLimit__ = false;
    /** ディスク容量制限 制限サイズ */
    private int diskLimitSize__ = 0;
    /** 一度に受信できるメールの最大件数 */
    private int maxReceiveMailCount__ = 0;

    /** 受信したメッセージを削除 */
    private boolean delReceive__ = false;
    /** 受信済みメールを受信 */
    private boolean reReceive__ = false;
    /** APOP使用 */
    private boolean apop__ = false;

    /** 受信サーバ接続タイムアウト */
    private int receiveConnectTimeout__ = GSConstWebmail.RECEIVE_CONNECTTIMEOUT_DEFAULT;
    /** メール受信タイムアウト */
    private int receiveTimeout__ = GSConstWebmail.RECEIVE_TIMEOUT_DEFAULT;
    /** メール受信 受信サイズ比較基準時間(分) */
    private int receiveRcvsvrCheckTime__ = GSConstWebmail.RECEIVE_RCVSVR_CHECKTIME_DEFAULT;
    /** メール本文の最大文字数 */
    private int mailBodyLimit__ = GSConstWebmail.MAILBODY_LIMIT_DEFAULT;

    //メール転送時に使用
    /** 送信サーバ */
    private String sendServer__ = null;
    /** 送信サーバ ポート */
    private int sendServerPort__ = 0;
    /** 送信サーバ SSL */
    private boolean sendServerSsl__ = false;
    /** 送信サーバ 認証 */
    private boolean sendServerAuth__ = false;
    /** 送信サーバ ユーザ */
    private String sendServerUser__ = null;
    /** 送信サーバ パスワード */
    private String sendServerPassword__ = null;
    /** 送信サーバ POPToSmtp */
    private boolean popToSmtp__ = false;
    /** 送信サーバ 送信文字コード */
    private String sendEncode__ = Encoding.ISO_2022_JP;

    /**
     * <p>host を取得します。
     * @return host
     */
    public String getHost() {
        return host__;
    }
    /**
     * <p>host をセットします。
     * @param host host
     */
    public void setHost(String host) {
        host__ = host;
    }
    /**
     * <p>password を取得します。
     * @return password
     */
    public String getPassword() {
        return password__;
    }
    /**
     * <p>password をセットします。
     * @param password password
     */
    public void setPassword(String password) {
        password__ = password;
    }
    /**
     * <p>port を取得します。
     * @return port
     */
    public int getPort() {
        return port__;
    }
    /**
     * <p>port をセットします。
     * @param port port
     */
    public void setPort(int port) {
        port__ = port;
    }
    /**
     * <p>user を取得します。
     * @return user
     */
    public String getUser() {
        return user__;
    }
    /**
     * <p>user をセットします。
     * @param user user
     */
    public void setUser(String user) {
        user__ = user;
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
     * <p>apop を取得します。
     * @return apop
     */
    public boolean isApop() {
        return apop__;
    }
    /**
     * <p>apop をセットします。
     * @param apop apop
     */
    public void setApop(boolean apop) {
        apop__ = apop;
    }
    /**
     * <p>delReceive を取得します。
     * @return delReceive
     */
    public boolean isDelReceive() {
        return delReceive__;
    }
    /**
     * <p>delReceive をセットします。
     * @param delReceive delReceive
     */
    public void setDelReceive(boolean delReceive) {
        delReceive__ = delReceive;
    }
    /**
     * <p>reReceive を取得します。
     * @return reReceive
     */
    public boolean isReReceive() {
        return reReceive__;
    }
    /**
     * <p>reReceive をセットします。
     * @param reReceive reReceive
     */
    public void setReReceive(boolean reReceive) {
        reReceive__ = reReceive;
    }
    /**
     * <p>wacSid を取得します。
     * @return wacSid
     */
    public int getWacSid() {
        return wacSid__;
    }
    /**
     * <p>wacSid をセットします。
     * @param wacSid wacSid
     */
    public void setWacSid(int wacSid) {
        wacSid__ = wacSid;
    }
    /**
     * <p>accountString を取得します。
     * @return accountString
     */
    public String getAccountString() {
        return accountString__;
    }
    /**
     * <p>accountString をセットします。
     * @param accountString accountString
     */
    public void setAccountString(String accountString) {
        accountString__ = accountString;
    }
    /**
     * <p>accountName を取得します。
     * @return accountName
     */
    public String getAccountName() {
        return accountName__;
    }
    /**
     * <p>accountName をセットします。
     * @param accountName accountName
     */
    public void setAccountName(String accountName) {
        accountName__ = accountName;
    }
    /**
     * <p>accountMailAddress を取得します。
     * @return accountMailAddress
     */
    public String getAccountMailAddress() {
        return accountMailAddress__;
    }
    /**
     * <p>accountMailAddress をセットします。
     * @param accountMailAddress accountMailAddress
     */
    public void setAccountMailAddress(String accountMailAddress) {
        accountMailAddress__ = accountMailAddress;
    }
    /**
     * <p>sendEncode を取得します。
     * @return sendEncode
     */
    public String getSendEncode() {
        return sendEncode__;
    }
    /**
     * <p>sendEncode をセットします。
     * @param sendEncode sendEncode
     */
    public void setSendEncode(String sendEncode) {
        sendEncode__ = sendEncode;
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
     * <p>sendServerAuth を取得します。
     * @return sendServerAuth
     */
    public boolean isSendServerAuth() {
        return sendServerAuth__;
    }
    /**
     * <p>sendServerAuth をセットします。
     * @param sendServerAuth sendServerAuth
     */
    public void setSendServerAuth(boolean sendServerAuth) {
        sendServerAuth__ = sendServerAuth;
    }
    /**
     * <p>sendServerPassword を取得します。
     * @return sendServerPassword
     */
    public String getSendServerPassword() {
        return sendServerPassword__;
    }
    /**
     * <p>sendServerPassword をセットします。
     * @param sendServerPassword sendServerPassword
     */
    public void setSendServerPassword(String sendServerPassword) {
        sendServerPassword__ = sendServerPassword;
    }
    /**
     * <p>sendServerPort を取得します。
     * @return sendServerPort
     */
    public int getSendServerPort() {
        return sendServerPort__;
    }
    /**
     * <p>sendServerPort をセットします。
     * @param sendServerPort sendServerPort
     */
    public void setSendServerPort(int sendServerPort) {
        sendServerPort__ = sendServerPort;
    }
    /**
     * <p>sendServerSsl を取得します。
     * @return sendServerSsl
     */
    public boolean isSendServerSsl() {
        return sendServerSsl__;
    }
    /**
     * <p>sendServerSsl をセットします。
     * @param sendServerSsl sendServerSsl
     */
    public void setSendServerSsl(boolean sendServerSsl) {
        sendServerSsl__ = sendServerSsl;
    }
    /**
     * <p>sendServerUser を取得します。
     * @return sendServerUser
     */
    public String getSendServerUser() {
        return sendServerUser__;
    }
    /**
     * <p>sendServerUser をセットします。
     * @param sendServerUser sendServerUser
     */
    public void setSendServerUser(String sendServerUser) {
        sendServerUser__ = sendServerUser;
    }
    /**
     * <p>popToSmtp を取得します。
     * @return popToSmtp
     */
    public boolean isPopToSmtp() {
        return popToSmtp__;
    }
    /**
     * <p>popToSmtp をセットします。
     * @param popToSmtp popToSmtp
     */
    public void setPopToSmtp(boolean popToSmtp) {
        popToSmtp__ = popToSmtp;
    }
    /**
     * <p>maxReceiveMailCount を取得します。
     * @return maxReceiveMailCount
     */
    public int getMaxReceiveMailCount() {
        return maxReceiveMailCount__;
    }
    /**
     * <p>maxReceiveMailCount をセットします。
     * @param maxReceiveMailCount maxReceiveMailCount
     */
    public void setMaxReceiveMailCount(int maxReceiveMailCount) {
        maxReceiveMailCount__ = maxReceiveMailCount;
    }
    /**
     * @return diskLimit
     */
    public boolean isDiskLimit() {
        return diskLimit__;
    }
    /**
     * @param diskLimit 設定する diskLimit
     */
    public void setDiskLimit(boolean diskLimit) {
        diskLimit__ = diskLimit;
    }
    /**
     * <p>receiveConnectTimeout を取得します。
     * @return receiveConnectTimeout
     */
    public int getReceiveConnectTimeout() {
        return receiveConnectTimeout__;
    }
    /**
     * <p>receiveConnectTimeout をセットします。
     * @param receiveConnectTimeout receiveConnectTimeout
     */
    public void setReceiveConnectTimeout(int receiveConnectTimeout) {
        receiveConnectTimeout__ = receiveConnectTimeout;
    }
    /**
     * <p>receiveRcvsvrCheckTime を取得します。
     * @return receiveRcvsvrCheckTime
     */
    public int getReceiveRcvsvrCheckTime() {
        return receiveRcvsvrCheckTime__;
    }
    /**
     * <p>receiveRcvsvrCheckTime をセットします。
     * @param receiveRcvsvrCheckTime receiveRcvsvrCheckTime
     */
    public void setReceiveRcvsvrCheckTime(int receiveRcvsvrCheckTime) {
        receiveRcvsvrCheckTime__ = receiveRcvsvrCheckTime;
    }
    /**
     * <p>receiveTimeout を取得します。
     * @return receiveTimeout
     */
    public int getReceiveTimeout() {
        return receiveTimeout__;
    }
    /**
     * <p>receiveTimeout をセットします。
     * @param receiveTimeout receiveTimeout
     */
    public void setReceiveTimeout(int receiveTimeout) {
        receiveTimeout__ = receiveTimeout;
    }
    /**
     * @return diskLimitSize
     */
    public int getDiskLimitSize() {
        return diskLimitSize__;
    }
    /**
     * @param diskLimitSize 設定する diskLimitSize
     */
    public void setDiskLimitSize(int diskLimitSize) {
        diskLimitSize__ = diskLimitSize;
    }
    /**
     * <p>mailBodyLimit を取得します。
     * @return mailBodyLimit
     */
    public int getMailBodyLimit() {
        return mailBodyLimit__;
    }
    /**
     * <p>mailBodyLimit をセットします。
     * @param mailBodyLimit mailBodyLimit
     */
    public void setMailBodyLimit(int mailBodyLimit) {
        mailBodyLimit__ = mailBodyLimit;
    }

    /**
     * ディスク容量制限 制限サイズをbyte換算して返す
     * @return ディスク容量制限 制限サイズ
     */
    public long getDiskLimitSizeForByte() {
        return WmlBiz.getDiskLimitSizeForByte(diskLimitSize__);
    }
}
