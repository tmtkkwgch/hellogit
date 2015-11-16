package jp.groupsession.v2.wml.wml190;

import java.util.List;

import jp.groupsession.v2.wml.wml020.Wml020ParamModel;
import jp.groupsession.v2.wml.wml040.Wml040Form;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] WEBメール 個人設定 アカウント編集画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml190ParamModel extends Wml020ParamModel {
    /** アカウント名 */
    private String wml190name__ = null;
    /** メール受信サーバ */
    private String wml190receiveServer__ = null;
    /** メール受信サーバ ポート番号 */
    private String wml190receiveServerPort__ = null;
    /** メール受信サーバ SSL */
    private int wml190receiveServerSsl__ = Wml040Form.RECEIVE_SSL_NOTUSE;
    /** メール受信サーバの種類 */
    private int wml190receiveServerType__ = Wml040Form.RSERVERTYPE_POP;
    /** メール受信サーバ ユーザID */
    private String wml190receiveServerUser__ = null;
    /** メール受信サーバ パスワード */
    private String wml190receiveServerPassword__ = null;
    /** メール送信サーバ */
    private String wml190sendServer__ = null;
    /** メール送信サーバ名 ポート番号 */
    private String wml190sendServerPort__ = null;
    /** メール受信サーバ SSL */
    private int wml190sendServerSsl__ = Wml040Form.SEND_SSL_NOTUSE;
    /** SMTP認証ON/OFF */
    private int wml190smtpAuth__ = Wml040Form.SMTPAUTH_OFF;
    /** メール送信サーバ ユーザ名 */
    private String wml190sendServerUser__ = null;
    /** メール送信サーバ名 パスワード */
    private String wml190sendServerPassword__ = null;
    /** 署名 */
    private int wml190sign__ = 0;
    /** 自動TO */
    private String wml190autoTo__ = null;
    /** 自動CC */
    private String wml190autoCc__ = null;
    /** 自動BCC */
    private String wml190autoBcc__ = null;
    /** テーマ */
    private int wml190theme__ = 0;
    /** 引用符 */
    private int wml190quotes__ = 0;
    /** 代理人 */
    private String[] wml190proxyUser__ = null;

    /** 初期表示フラグ */
    private int wml190initFlg__ = 0;
    /** サーバ情報設定許可 */
    private int wml190settingServer__ = 0;

    /** 代理人 許可 */
    private boolean wml190proxyUserFlg__ = false;
    /** 代理人 ユーザ(選択用) */
    private String[] wml190proxyUserSelect__  = null;
    /** 代理人 ユーザ(未選択 選択用) */
    private String[] wml190proxyUserNoSelect__ = null;

    /** テーマ 一覧 */
    private List<LabelValueBean> wml190themeList__ = null;
    /** 引用符 一覧 */
    private List<LabelValueBean> wml190quotesList__ = null;
    /** 署名 一覧 */
    private List<LabelValueBean> wml190signList__ = null;

    /** 代理人 グループ */
    private String wml190proxyUserGroup__ = null;
    /** 代理人 グループコンボ */
    private List<LabelValueBean> proxyUserGroupCombo__ = null;
    /** 代理人 ユーザ(選択用) コンボ */
    private List<LabelValueBean> proxyUserSelectCombo__  = null;
    /** 代理人 ユーザ(未選択 選択用) コンボ */
    private List<LabelValueBean> proxyUserNoSelectCombo__  = null;

    /**
     * <p>wml190autoBcc を取得します。
     * @return wml190autoBcc
     */
    public String getWml190autoBcc() {
        return wml190autoBcc__;
    }
    /**
     * <p>wml190autoBcc をセットします。
     * @param wml190autoBcc wml190autoBcc
     */
    public void setWml190autoBcc(String wml190autoBcc) {
        wml190autoBcc__ = wml190autoBcc;
    }
    /**
     * <p>wml190autoCc を取得します。
     * @return wml190autoCc
     */
    public String getWml190autoCc() {
        return wml190autoCc__;
    }
    /**
     * <p>wml190autoCc をセットします。
     * @param wml190autoCc wml190autoCc
     */
    public void setWml190autoCc(String wml190autoCc) {
        wml190autoCc__ = wml190autoCc;
    }
    /**
     * <p>wml190autoTo を取得します。
     * @return wml190autoTo
     */
    public String getWml190autoTo() {
        return wml190autoTo__;
    }
    /**
     * <p>wml190autoTo をセットします。
     * @param wml190autoTo wml190autoTo
     */
    public void setWml190autoTo(String wml190autoTo) {
        wml190autoTo__ = wml190autoTo;
    }
    /**
     * <p>wml190name を取得します。
     * @return wml190name
     */
    public String getWml190name() {
        return wml190name__;
    }
    /**
     * <p>wml190name をセットします。
     * @param wml190name wml190name
     */
    public void setWml190name(String wml190name) {
        wml190name__ = wml190name;
    }
    /**
     * <p>wml190receiveServer を取得します。
     * @return wml190receiveServer
     */
    public String getWml190receiveServer() {
        return wml190receiveServer__;
    }
    /**
     * <p>wml190receiveServer をセットします。
     * @param wml190receiveServer wml190receiveServer
     */
    public void setWml190receiveServer(String wml190receiveServer) {
        wml190receiveServer__ = wml190receiveServer;
    }
    /**
     * <p>wml190receiveServerPort を取得します。
     * @return wml190receiveServerPort
     */
    public String getWml190receiveServerPort() {
        return wml190receiveServerPort__;
    }
    /**
     * <p>wml190receiveServerPort をセットします。
     * @param wml190receiveServerPort wml190receiveServerPort
     */
    public void setWml190receiveServerPort(String wml190receiveServerPort) {
        wml190receiveServerPort__ = wml190receiveServerPort;
    }
    /**
     * <p>wml190receiveServerSsl を取得します。
     * @return wml190receiveServerSsl
     */
    public int getWml190receiveServerSsl() {
        return wml190receiveServerSsl__;
    }
    /**
     * <p>wml190receiveServerSsl をセットします。
     * @param wml190receiveServerSsl wml190receiveServerSsl
     */
    public void setWml190receiveServerSsl(int wml190receiveServerSsl) {
        wml190receiveServerSsl__ = wml190receiveServerSsl;
    }
    /**
     * <p>wml190receiveServerType を取得します。
     * @return wml190receiveServerType
     */
    public int getWml190receiveServerType() {
        return wml190receiveServerType__;
    }
    /**
     * <p>wml190receiveServerType をセットします。
     * @param wml190receiveServerType wml190receiveServerType
     */
    public void setWml190receiveServerType(int wml190receiveServerType) {
        wml190receiveServerType__ = wml190receiveServerType;
    }
    /**
     * <p>wml190receiveServerUser を取得します。
     * @return wml190receiveServerUser
     */
    public String getWml190receiveServerUser() {
        return wml190receiveServerUser__;
    }
    /**
     * <p>wml190receiveServerUser をセットします。
     * @param wml190receiveServerUser wml190receiveServerUser
     */
    public void setWml190receiveServerUser(String wml190receiveServerUser) {
        wml190receiveServerUser__ = wml190receiveServerUser;
    }
    /**
     * <p>wml190receiveServerPassword を取得します。
     * @return wml190receiveServerPassword
     */
    public String getWml190receiveServerPassword() {
        return wml190receiveServerPassword__;
    }
    /**
     * <p>wml190receiveServerPassword をセットします。
     * @param wml190receiveServerPassword wml190receiveServerPassword
     */
    public void setWml190receiveServerPassword(String wml190receiveServerPassword) {
        wml190receiveServerPassword__ = wml190receiveServerPassword;
    }
    /**
     * <p>wml190sendServer を取得します。
     * @return wml190sendServer
     */
    public String getWml190sendServer() {
        return wml190sendServer__;
    }
    /**
     * <p>wml190sendServer をセットします。
     * @param wml190sendServer wml190sendServer
     */
    public void setWml190sendServer(String wml190sendServer) {
        wml190sendServer__ = wml190sendServer;
    }
    /**
     * <p>wml190sendServerPort を取得します。
     * @return wml190sendServerPort
     */
    public String getWml190sendServerPort() {
        return wml190sendServerPort__;
    }
    /**
     * <p>wml190sendServerPort をセットします。
     * @param wml190sendServerPort wml190sendServerPort
     */
    public void setWml190sendServerPort(String wml190sendServerPort) {
        wml190sendServerPort__ = wml190sendServerPort;
    }
    /**
     * <p>wml190sendServerSsl を取得します。
     * @return wml190sendServerSsl
     */
    public int getWml190sendServerSsl() {
        return wml190sendServerSsl__;
    }
    /**
     * <p>wml190smtpAuth を取得します。
     * @return wml190smtpAuth
     */
    public int getWml190smtpAuth() {
        return wml190smtpAuth__;
    }
    /**
     * <p>wml190smtpAuth をセットします。
     * @param wml190smtpAuth wml190smtpAuth
     */
    public void setWml190smtpAuth(int wml190smtpAuth) {
        wml190smtpAuth__ = wml190smtpAuth;
    }
    /**
     * <p>wml190sendServerSsl をセットします。
     * @param wml190sendServerSsl wml190sendServerSsl
     */
    public void setWml190sendServerSsl(int wml190sendServerSsl) {
        wml190sendServerSsl__ = wml190sendServerSsl;
    }
    /**
     * <p>wml190sendServerUser を取得します。
     * @return wml190sendServerUser
     */
    public String getWml190sendServerUser() {
        return wml190sendServerUser__;
    }
    /**
     * <p>wml190sendServerUser をセットします。
     * @param wml190sendServerUser wml190sendServerUser
     */
    public void setWml190sendServerUser(String wml190sendServerUser) {
        wml190sendServerUser__ = wml190sendServerUser;
    }
    /**
     * <p>wml190sendServerPassword を取得します。
     * @return wml190sendServerPassword
     */
    public String getWml190sendServerPassword() {
        return wml190sendServerPassword__;
    }
    /**
     * <p>wml190sendServerPassword をセットします。
     * @param wml190sendServerPassword wml190sendServerPassword
     */
    public void setWml190sendServerPassword(String wml190sendServerPassword) {
        wml190sendServerPassword__ = wml190sendServerPassword;
    }
    /**
     * <p>wml190sign を取得します。
     * @return wml190sign
     */
    public int getWml190sign() {
        return wml190sign__;
    }
    /**
     * <p>wml190sign をセットします。
     * @param wml190sign wml190sign
     */
    public void setWml190sign(int wml190sign) {
        wml190sign__ = wml190sign;
    }
    /**
     * <p>wml190initFlg を取得します。
     * @return wml190initFlg
     */
    public int getWml190initFlg() {
        return wml190initFlg__;
    }
    /**
     * <p>wml190initFlg をセットします。
     * @param wml190initFlg wml190initFlg
     */
    public void setWml190initFlg(int wml190initFlg) {
        wml190initFlg__ = wml190initFlg;
    }
    /**
     * <p>wml190settingServer を取得します。
     * @return wml190settingServer
     */
    public int getWml190settingServer() {
        return wml190settingServer__;
    }
    /**
     * <p>wml190settingServer をセットします。
     * @param wml190settingServer wml190settingServer
     */
    public void setWml190settingServer(int wml190settingServer) {
        wml190settingServer__ = wml190settingServer;
    }
    /**
     * <p>wml190theme を取得します。
     * @return wml190theme
     */
    public int getWml190theme() {
        return wml190theme__;
    }
    /**
     * <p>wml190theme をセットします。
     * @param wml190theme wml190theme
     */
    public void setWml190theme(int wml190theme) {
        wml190theme__ = wml190theme;
    }
    /**
     * <p>wml190quotes を取得します。
     * @return wml190quotes
     */
    public int getWml190quotes() {
        return wml190quotes__;
    }
    /**
     * <p>wml190quotes をセットします。
     * @param wml190quotes wml190quotes
     */
    public void setWml190quotes(int wml190quotes) {
        wml190quotes__ = wml190quotes;
    }
    /**
     * <p>wml190themeList を取得します。
     * @return wml190themeList
     */
    public List<LabelValueBean> getWml190themeList() {
        return wml190themeList__;
    }
    /**
     * <p>wml190themeList をセットします。
     * @param wml190themeList wml190themeList
     */
    public void setWml190themeList(List<LabelValueBean> wml190themeList) {
        wml190themeList__ = wml190themeList;
    }
    /**
     * <p>wml190quotesList を取得します。
     * @return wml190quotesList
     */
    public List<LabelValueBean> getWml190quotesList() {
        return wml190quotesList__;
    }
    /**
     * <p>wml190quotesList をセットします。
     * @param wml190quotesList wml190quotesList
     */
    public void setWml190quotesList(List<LabelValueBean> wml190quotesList) {
        wml190quotesList__ = wml190quotesList;
    }
    /**
     * <p>wml190signList を取得します。
     * @return wml190signList
     */
    public List<LabelValueBean> getWml190signList() {
        return wml190signList__;
    }
    /**
     * <p>wml190signList をセットします。
     * @param wml190signList wml190signList
     */
    public void setWml190signList(List<LabelValueBean> wml190signList) {
        wml190signList__ = wml190signList;
    }
    /**
     * <p>wml190proxyUser を取得します。
     * @return wml190proxyUser
     */
    public String[] getWml190proxyUser() {
        return wml190proxyUser__;
    }
    /**
     * <p>wml190proxyUser をセットします。
     * @param wml190proxyUser wml190proxyUser
     */
    public void setWml190proxyUser(String[] wml190proxyUser) {
        wml190proxyUser__ = wml190proxyUser;
    }
    /**
     * <p>wml190proxyUserFlg を取得します。
     * @return wml190proxyUserFlg
     */
    public boolean isWml190proxyUserFlg() {
        return wml190proxyUserFlg__;
    }
    /**
     * <p>wml190proxyUserFlg をセットします。
     * @param wml190proxyUserFlg wml190proxyUserFlg
     */
    public void setWml190proxyUserFlg(boolean wml190proxyUserFlg) {
        wml190proxyUserFlg__ = wml190proxyUserFlg;
    }
    /**
     * <p>wml190proxyUserSelect を取得します。
     * @return wml190proxyUserSelect
     */
    public String[] getWml190proxyUserSelect() {
        return wml190proxyUserSelect__;
    }
    /**
     * <p>wml190proxyUserSelect をセットします。
     * @param wml190proxyUserSelect wml190proxyUserSelect
     */
    public void setWml190proxyUserSelect(String[] wml190proxyUserSelect) {
        wml190proxyUserSelect__ = wml190proxyUserSelect;
    }
    /**
     * <p>wml190proxyUserNoSelect を取得します。
     * @return wml190proxyUserNoSelect
     */
    public String[] getWml190proxyUserNoSelect() {
        return wml190proxyUserNoSelect__;
    }
    /**
     * <p>wml190proxyUserNoSelect をセットします。
     * @param wml190proxyUserNoSelect wml190proxyUserNoSelect
     */
    public void setWml190proxyUserNoSelect(String[] wml190proxyUserNoSelect) {
        wml190proxyUserNoSelect__ = wml190proxyUserNoSelect;
    }
    /**
     * <p>wml190proxyUserGroup を取得します。
     * @return wml190proxyUserGroup
     */
    public String getWml190proxyUserGroup() {
        return wml190proxyUserGroup__;
    }
    /**
     * <p>wml190proxyUserGroup をセットします。
     * @param wml190proxyUserGroup wml190proxyUserGroup
     */
    public void setWml190proxyUserGroup(String wml190proxyUserGroup) {
        wml190proxyUserGroup__ = wml190proxyUserGroup;
    }
    /**
     * <p>proxyUserGroupCombo を取得します。
     * @return proxyUserGroupCombo
     */
    public List<LabelValueBean> getProxyUserGroupCombo() {
        return proxyUserGroupCombo__;
    }
    /**
     * <p>proxyUserGroupCombo をセットします。
     * @param proxyUserGroupCombo proxyUserGroupCombo
     */
    public void setProxyUserGroupCombo(List<LabelValueBean> proxyUserGroupCombo) {
        proxyUserGroupCombo__ = proxyUserGroupCombo;
    }
    /**
     * <p>proxyUserSelectCombo を取得します。
     * @return proxyUserSelectCombo
     */
    public List<LabelValueBean> getProxyUserSelectCombo() {
        return proxyUserSelectCombo__;
    }
    /**
     * <p>proxyUserSelectCombo をセットします。
     * @param proxyUserSelectCombo proxyUserSelectCombo
     */
    public void setProxyUserSelectCombo(List<LabelValueBean> proxyUserSelectCombo) {
        proxyUserSelectCombo__ = proxyUserSelectCombo;
    }
    /**
     * <p>proxyUserNoSelectCombo を取得します。
     * @return proxyUserNoSelectCombo
     */
    public List<LabelValueBean> getProxyUserNoSelectCombo() {
        return proxyUserNoSelectCombo__;
    }
    /**
     * <p>proxyUserNoSelectCombo をセットします。
     * @param proxyUserNoSelectCombo proxyUserNoSelectCombo
     */
    public void setProxyUserNoSelectCombo(
            List<LabelValueBean> proxyUserNoSelectCombo) {
        proxyUserNoSelectCombo__ = proxyUserNoSelectCombo;
    }
}