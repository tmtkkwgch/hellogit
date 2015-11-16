package jp.groupsession.v2.anp.anp080;

import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.anp.AnpiValidateUtil;
import jp.groupsession.v2.anp.GSConstAnpi;
import jp.groupsession.v2.anp.anp070.Anp070Form;
import jp.groupsession.v2.anp.model.AnpLabelValueModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 管理者設定・基本設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp080Form extends Anp070Form {

    /** 遷移元(1:メッセージ配信) */
    private String anp080BackScreen__;

    /** 返信基本URL */
    private String anp080BaseUrl__;
    /** 返信基本URL(自動取得値保持用) */
    private String anp080SvBaseUrlAuto__;

    /** 基本URL設定区分 */
    private int anp080UrlSetKbn__;
    /** 送信メールアドレス */
    private String anp080SendMail__;
    /** メール送信サーバ */
    private String anp080SendHost__;
    /** メール送信サーバ ポート番号 */
    private String anp080SendPort__;
    /** メール送信サーバ SSLフラグ */
    private int anp080SendSsl__;
    /** メール送信サーバ SMTP認証 */
    private String anp080SmtpAuth__;
    /** メール送信サーバ ユーザ */
    private String anp080SendUser__;
    /** メール送信サーバ パスワード */
    private String anp080SendPass__;

    /** 安否確認管理者グループコンボボックス選択SID */
    private String anp080SelectGroupSid__ = null;
    /** 安否確認管理者ユーザSIDリスト */
    private String[] anp080AdmUserList__ = null;

    /** 安否確認管理者グループコンボボックスリスト */
    private List<AnpLabelValueModel> anp080GroupLabel__ = null;
    /** 安否確認管理者ユーザリスト (追加済み 左)*/
    private List <LabelValueBean> anp080AdmUserLabel__ = null;
    /** 安否確認管理者グループ所属ユーザリスト (追加用 右)*/
    private List <LabelValueBean> anp080BelongLabel__ = null;

    /** 安否確認管理者ユーザ選択SID (追加済み 左)*/
    private String[] anp080SelectAdmUserSid__ = null;
    /** 安否確認管理者グループ所属ユーザ選択SID （追加用 右）*/
    private String[] anp080SelectBelongSid__ = null;

    /**
     * <p>anp080BackScreen を取得します。
     * @return anp080BackScreen
     */
    public String getAnp080BackScreen() {
        return anp080BackScreen__;
    }
    /**
     * <p>anp080BackScreen をセットします。
     * @param anp080BackScreen anp080BackScreen
     */
    public void setAnp080BackScreen(String anp080BackScreen) {
        anp080BackScreen__ = anp080BackScreen;
    }
    /**
     * <p>anp080BaseUrl を取得します。
     * @return anp080BaseUrl
     */
    public String getAnp080BaseUrl() {
        return anp080BaseUrl__;
    }
    /**
     * <p>anp080BaseUrl をセットします。
     * @param anp080BaseUrl anp080BaseUrl
     */
    public void setAnp080BaseUrl(String anp080BaseUrl) {
        anp080BaseUrl__ = anp080BaseUrl;
    }
    /**
     * <p>anp080SvBaseUrlAuto を取得します。
     * @return anp080SvBaseUrlAuto
     */
    public String getAnp080SvBaseUrlAuto() {
        return anp080SvBaseUrlAuto__;
    }
    /**
     * <p>anp080SvBaseUrlAuto をセットします。
     * @param anp080SvBaseUrlAuto anp080SvBaseUrlAuto
     */
    public void setAnp080SvBaseUrlAuto(String anp080SvBaseUrlAuto) {
        anp080SvBaseUrlAuto__ = anp080SvBaseUrlAuto;
    }
    /**
     * <p>anp080UrlSetKbn を取得します。
     * @return anp080UrlSetKbn
     */
    public int getAnp080UrlSetKbn() {
        return anp080UrlSetKbn__;
    }
    /**
     * <p>anp080UrlSetKbn をセットします。
     * @param anp080UrlSetKbn anp080UrlSetKbn
     */
    public void setAnp080UrlSetKbn(int anp080UrlSetKbn) {
        anp080UrlSetKbn__ = anp080UrlSetKbn;
    }
    /**
     * <p>anp080SendMail を取得します。
     * @return anp080SendMail
     */
    public String getAnp080SendMail() {
        return anp080SendMail__;
    }
    /**
     * <p>anp080SendMail をセットします。
     * @param anp080SendMail anp080SendMail
     */
    public void setAnp080SendMail(String anp080SendMail) {
        anp080SendMail__ = anp080SendMail;
    }
    /**
     * <p>anp080SendHost を取得します。
     * @return anp080SendHost
     */
    public String getAnp080SendHost() {
        return anp080SendHost__;
    }
    /**
     * <p>anp080SendHost をセットします。
     * @param anp080SendHost anp080SendHost
     */
    public void setAnp080SendHost(String anp080SendHost) {
        anp080SendHost__ = anp080SendHost;
    }
    /**
     * <p>anp080SendPort を取得します。
     * @return anp080SendPort
     */
    public String getAnp080SendPort() {
        return anp080SendPort__;
    }
    /**
     * <p>anp080SendPort をセットします。
     * @param anp080SendPort anp080SendPort
     */
    public void setAnp080SendPort(String anp080SendPort) {
        anp080SendPort__ = anp080SendPort;
    }
    /**
     * <p>anp080SendSsl を取得します。
     * @return anp080SendSsl
     */
    public int getAnp080SendSsl() {
        return anp080SendSsl__;
    }
    /**
     * <p>anp080SendSsl をセットします。
     * @param anp080SendSsl anp080SendSsl
     */
    public void setAnp080SendSsl(int anp080SendSsl) {
        anp080SendSsl__ = anp080SendSsl;
    }
    /**
     * <p>anp080SmtpAuth を取得します。
     * @return anp080SmtpAuth
     */
    public String getAnp080SmtpAuth() {
        return anp080SmtpAuth__;
    }
    /**
     * <p>anp080SmtpAuth をセットします。
     * @param anp080SmtpAuth anp080SmtpAuth
     */
    public void setAnp080SmtpAuth(String anp080SmtpAuth) {
        anp080SmtpAuth__ = anp080SmtpAuth;
    }
    /**
     * <p>anp080SendUser を取得します。
     * @return anp080SendUser
     */
    public String getAnp080SendUser() {
        return anp080SendUser__;
    }
    /**
     * <p>anp080SendUser をセットします。
     * @param anp080SendUser anp080SendUser
     */
    public void setAnp080SendUser(String anp080SendUser) {
        anp080SendUser__ = anp080SendUser;
    }
    /**
     * <p>anp080SendPass を取得します。
     * @return anp080SendPass
     */
    public String getAnp080SendPass() {
        return anp080SendPass__;
    }
    /**
     * <p>anp080SendPass をセットします。
     * @param anp080SendPass anp080SendPass
     */
    public void setAnp080SendPass(String anp080SendPass) {
        anp080SendPass__ = anp080SendPass;
    }
    /**
     * <p>anp080SelectGroupSid を取得します。
     * @return anp080SelectGroupSid
     */
    public String getAnp080SelectGroupSid() {
        return anp080SelectGroupSid__;
    }
    /**
     * <p>anp080SelectGroupSid をセットします。
     * @param anp080SelectGroupSid anp080SelectGroupSid
     */
    public void setAnp080SelectGroupSid(String anp080SelectGroupSid) {
        anp080SelectGroupSid__ = anp080SelectGroupSid;
    }
    /**
     * <p>anp080AdmUserList を取得します。
     * @return anp080AdmUserList
     */
    public String[] getAnp080AdmUserList() {
        return anp080AdmUserList__;
    }
    /**
     * <p>anp080AdmUserList をセットします。
     * @param anp080AdmUserList anp080AdmUserList
     */
    public void setAnp080AdmUserList(String[] anp080AdmUserList) {
        anp080AdmUserList__ = anp080AdmUserList;
    }
    /**
     * <p>anp080SelectAdmUserSid を取得します。
     * @return anp080SelectAdmUserSid
     */
    public String[] getAnp080SelectAdmUserSid() {
        return anp080SelectAdmUserSid__;
    }
    /**
     * <p>anp080SelectAdmUserSid をセットします。
     * @param anp080SelectAdmUserSid anp080SelectAdmUserSid
     */
    public void setAnp080SelectAdmUserSid(String[] anp080SelectAdmUserSid) {
        anp080SelectAdmUserSid__ = anp080SelectAdmUserSid;
    }
    /**
     * <p>anp080SelectBelongSid を取得します。
     * @return anp080SelectBelongSid
     */
    public String[] getAnp080SelectBelongSid() {
        return anp080SelectBelongSid__;
    }
    /**
     * <p>anp080SelectBelongSid をセットします。
     * @param anp080SelectBelongSid anp080SelectBelongSid
     */
    public void setAnp080SelectBelongSid(String[] anp080SelectBelongSid) {
        anp080SelectBelongSid__ = anp080SelectBelongSid;
    }
    /**
     * <p>anp080GroupLabel を取得します。
     * @return anp080GroupLabel
     */
    public List<AnpLabelValueModel> getAnp080GroupLabel() {
        return anp080GroupLabel__;
    }
    /**
     * <p>anp080GroupLabel をセットします。
     * @param anp080GroupLabel anp080GroupLabel
     */
    public void setAnp080GroupLabel(List<AnpLabelValueModel> anp080GroupLabel) {
        anp080GroupLabel__ = anp080GroupLabel;
    }
    /**
     * <p>anp080AdmUserLabel を取得します。
     * @return anp080AdmUserLabel
     */
    public List<LabelValueBean> getAnp080AdmUserLabel() {
        return anp080AdmUserLabel__;
    }
    /**
     * <p>anp080AdmUserLabel をセットします。
     * @param anp080AdmUserLabel anp080AdmUserLabel
     */
    public void setAnp080AdmUserLabel(List<LabelValueBean> anp080AdmUserLabel) {
        anp080AdmUserLabel__ = anp080AdmUserLabel;
    }
    /**
     * <p>anp080BelongLabel を取得します。
     * @return anp080BelongLabel
     */
    public List<LabelValueBean> getAnp080BelongLabel() {
        return anp080BelongLabel__;
    }
    /**
     * <p>anp080BelongLabel をセットします。
     * @param anp080BelongLabel anp080BelongLabel
     */
    public void setAnp080BelongLabel(List<LabelValueBean> anp080BelongLabel) {
        anp080BelongLabel__ = anp080BelongLabel;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return エラー
     */
    public ActionErrors validateAnp080(RequestModel reqMdl) {
        ActionErrors errors = new ActionErrors();

        GsMessage gsMsg = new GsMessage(reqMdl);

        //返信基本URL
        if (anp080UrlSetKbn__ == GSConstAnpi.URL_SETTING_MANUAL) {
            AnpiValidateUtil.validateTextField(errors,
                    anp080BaseUrl__, "anp080BaseUrl", gsMsg.getMessage("anp.anp080.02"),
                    GSConstAnpi.MAXLENGTH_BASE_URL, true);
        }

        //送信メールアドレス
        AnpiValidateUtil.validateMail(errors,
            anp080SendMail__, "anp080SendMail", gsMsg.getMessage("anp.anp080.06"), true);

        //メール送信サーバ
        AnpiValidateUtil.validateTextField(errors,
                anp080SendHost__, "anp080SendHost",
                gsMsg.getMessage("anp.smtp.server"), GSConstAnpi.MAXLENGTH_SEND_HOST, true);

        //メール送信サーバ ポート番号
        AnpiValidateUtil.validateTextFieldOfNumber(errors,
            anp080SendPort__, "anp080SendPort", gsMsg.getMessage("anp.anp080.14"),
            GSConstAnpi.MAXLENGTH_SEND_PORT, true);

//        //メール送信サーバ SSLフラグ
//        if (!StringUtil.isNullZeroString(anp080SendSsl__)) {
//            if (!anp080SendSsl__.equals(String.valueOf(GSConstAnpi.SEND_SSL_NOUSE))
//             && !anp080SendSsl__.equals(String.valueOf(GSConstAnpi.SEND_SSL_USE))) {
//                String msgKey = "error.input.format.text";
//                ActionMessage msg = new ActionMessage(msgKey, "メール送信サーバ SSLフラグ");
//                StrutsUtil.addMessage(errors, msg, "anp080SendSsl." + msgKey);
//            }
//        }

        //SMTP認証ON/OFF
        boolean isSmtp = false;
        if (!StringUtil.isNullZeroString(anp080SmtpAuth__)) {
            if (!anp080SmtpAuth__.equals(String.valueOf(GSConstAnpi.SMTP_AUTH_NOT))
             && !anp080SmtpAuth__.equals(String.valueOf(GSConstAnpi.SMTP_AUTH_YES))) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, gsMsg.getMessage("anp.anp080.15"));
                StrutsUtil.addMessage(errors, msg, "anp080SmtpAuth." + msgKey);
            } else {
                isSmtp = (anp080SmtpAuth__.equals(String.valueOf(GSConstAnpi.SMTP_AUTH_YES)));
            }
        }

        if (isSmtp) {
            //メール送信サーバ ユーザ名
            AnpiValidateUtil.validateTextField(errors,
                anp080SendUser__, "anp080SendUser", gsMsg.getMessage("anp.anp080.11"),
                GSConstAnpi.MAXLENGTH_SEND_USER, false);

            //メール送信サーバ パスワード
            AnpiValidateUtil.validateTextField(errors,
                anp080SendPass__, "anp080SendPass", gsMsg.getMessage("anp.anp080.12"),
                GSConstAnpi.MAXLENGTH_SEND_PASSWORD, false);
        }

        return errors;
    }
}