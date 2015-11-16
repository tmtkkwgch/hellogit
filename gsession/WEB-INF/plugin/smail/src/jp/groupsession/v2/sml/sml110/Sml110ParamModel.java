package jp.groupsession.v2.sml.sml110;

import java.util.List;

import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.sml100.Sml100ParamModel;

/**
 * <br>[機  能] ショートメール 管理者設定 転送設定画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml110ParamModel extends Sml100ParamModel {
    /** 転送設定 */
    private String sml110MailForward__ = null;
    /** SMTPサーバ */
    private String sml110SmtpUrl__ = null;
    /** SMTPサーバ認証ユーザ */
    private String sml110SmtpUser__ = null;
    /** SMTPサーバ認証パスワード */
    private String sml110SmtpPass__ = null;
    /** 転送メールfromアドレス */
    private String sml110FromAddress__ = null;
    /** SMTPポート */
    private String sml110SmtpPort__ = null;
    /** 転送先制限 区分 制限しない=0 制限する=1 */
    private String sml110FwLmtKbn__ = null;
    /** 転送先制限 テキストエリア */
    private String sml110FwlmtTextArea__ = null;
    /** 転送先制限  */
    private List<Sml110FwCheckModel> sml110FwCheckList__ = null;
    /** チェックボタン押下フラグ  */
    private boolean sml110CheckFlg__ = GSConstSmail.FW_CHECK_OFF;
    /** SSL使用フラグ  */
    private int sml110SslFlg__ = GSConstSmail.SSL_NOTUSE;
    /**
     * <p>sml110MailForward を取得します。
     * @return sml110MailForward
     */
    public String getSml110MailForward() {
        return sml110MailForward__;
    }
    /**
     * <p>sml110MailForward をセットします。
     * @param sml110MailForward sml110MailForward
     */
    public void setSml110MailForward(String sml110MailForward) {
        sml110MailForward__ = sml110MailForward;
    }
    /**
     * <p>sml110SmtpUrl を取得します。
     * @return sml110SmtpUrl
     */
    public String getSml110SmtpUrl() {
        return sml110SmtpUrl__;
    }
    /**
     * <p>sml110SmtpUrl をセットします。
     * @param sml110SmtpUrl sml110SmtpUrl
     */
    public void setSml110SmtpUrl(String sml110SmtpUrl) {
        sml110SmtpUrl__ = sml110SmtpUrl;
    }
    /**
     * <p>sml110SmtpUser を取得します。
     * @return sml110SmtpUser
     */
    public String getSml110SmtpUser() {
        return sml110SmtpUser__;
    }
    /**
     * <p>sml110SmtpUser をセットします。
     * @param sml110SmtpUser sml110SmtpUser
     */
    public void setSml110SmtpUser(String sml110SmtpUser) {
        sml110SmtpUser__ = sml110SmtpUser;
    }
    /**
     * <p>sml110SmtpPass を取得します。
     * @return sml110SmtpPass
     */
    public String getSml110SmtpPass() {
        return sml110SmtpPass__;
    }
    /**
     * <p>sml110SmtpPass をセットします。
     * @param sml110SmtpPass sml110SmtpPass
     */
    public void setSml110SmtpPass(String sml110SmtpPass) {
        sml110SmtpPass__ = sml110SmtpPass;
    }
    /**
     * <p>sml110FromAddress を取得します。
     * @return sml110FromAddress
     */
    public String getSml110FromAddress() {
        return sml110FromAddress__;
    }
    /**
     * <p>sml110FromAddress をセットします。
     * @param sml110FromAddress sml110FromAddress
     */
    public void setSml110FromAddress(String sml110FromAddress) {
        sml110FromAddress__ = sml110FromAddress;
    }
    /**
     * <p>sml110SmtpPort を取得します。
     * @return sml110SmtpPort
     */
    public String getSml110SmtpPort() {
        return sml110SmtpPort__;
    }
    /**
     * <p>sml110SmtpPort をセットします。
     * @param sml110SmtpPort sml110SmtpPort
     */
    public void setSml110SmtpPort(String sml110SmtpPort) {
        sml110SmtpPort__ = sml110SmtpPort;
    }
    /**
     * <p>sml110FwLmtKbn を取得します。
     * @return sml110FwLmtKbn
     */
    public String getSml110FwLmtKbn() {
        return sml110FwLmtKbn__;
    }
    /**
     * <p>sml110FwLmtKbn をセットします。
     * @param sml110FwLmtKbn sml110FwLmtKbn
     */
    public void setSml110FwLmtKbn(String sml110FwLmtKbn) {
        sml110FwLmtKbn__ = sml110FwLmtKbn;
    }
    /**
     * <p>sml110FwlmtTextArea を取得します。
     * @return sml110FwlmtTextArea
     */
    public String getSml110FwlmtTextArea() {
        return sml110FwlmtTextArea__;
    }
    /**
     * <p>sml110FwlmtTextArea をセットします。
     * @param sml110FwlmtTextArea sml110FwlmtTextArea
     */
    public void setSml110FwlmtTextArea(String sml110FwlmtTextArea) {
        sml110FwlmtTextArea__ = sml110FwlmtTextArea;
    }
    /**
     * <p>sml110FwCheckList を取得します。
     * @return sml110FwCheckList
     */
    public List<Sml110FwCheckModel> getSml110FwCheckList() {
        return sml110FwCheckList__;
    }
    /**
     * <p>sml110FwCheckList をセットします。
     * @param sml110FwCheckList sml110FwCheckList
     */
    public void setSml110FwCheckList(List<Sml110FwCheckModel> sml110FwCheckList) {
        sml110FwCheckList__ = sml110FwCheckList;
    }
    /**
     * <p>sml110CheckFlg を取得します。
     * @return sml110CheckFlg
     */
    public boolean isSml110CheckFlg() {
        return sml110CheckFlg__;
    }
    /**
     * <p>sml110CheckFlg をセットします。
     * @param sml110CheckFlg sml110CheckFlg
     */
    public void setSml110CheckFlg(boolean sml110CheckFlg) {
        sml110CheckFlg__ = sml110CheckFlg;
    }
    /**
     * <p>sml110SslFlg を取得します。
     * @return sml110SslFlg
     */
    public int getSml110SslFlg() {
        return sml110SslFlg__;
    }
    /**
     * <p>sml110SslFlg をセットします。
     * @param sml110SslFlg sml110SslFlg
     */
    public void setSml110SslFlg(int sml110SslFlg) {
        sml110SslFlg__ = sml110SslFlg;
    }

}