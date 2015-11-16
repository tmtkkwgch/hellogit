package jp.groupsession.v2.sml.sml110;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.sml100.Sml100Form;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] ショートメール 管理者設定 転送設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml110Form extends Sml100Form {

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
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Sml110Form() {
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
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(
            ActionMapping map,
            HttpServletRequest req,
            Connection con) throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        String serverUrl = gsMsg.getMessage(req, "sml.sml110.07");
        String smtpServer = gsMsg.getMessage(req, "sml.114");
        String smtpSerPort = gsMsg.getMessage(req, "sml.sml110.08");
        String userId = gsMsg.getMessage(req, "sml.sml110.22");
        String userPass = gsMsg.getMessage(req, "sml.sml110.21");
        String fromAdr = gsMsg.getMessage(req, "sml.sml110.17");

        //SMTPサーバURLのチェック
        if (sml110MailForward__.equals(String.valueOf(GSConstSmail.MAIL_FORWARD_OK))) {
            //アドレス 入力
            if (StringUtil.isNullZeroString(sml110SmtpUrl__)) {
                msg = new ActionMessage("error.input.required.text", serverUrl);
                errors.add("sml110SmtpUrl.error.input.required.text", msg);
            //アドレス 文字数
            } else if (sml110SmtpUrl__.length() > GSConstSmail.MAX_LENGTH_SMTP) {
                msg = new ActionMessage("error.input.length.text",
                        smtpServer,
                        GSConstSmail.MAX_LENGTH_SMTP);
                errors.add("sml110SmtpUrl.error.input.length.text", msg);
            //アドレス 使用文字
            } else if (!GSValidateUtil.isGsJapaneaseString(sml110SmtpUrl__)) {
                String nstr = GSValidateUtil.getNotGsJapaneaseString(sml110SmtpUrl__);
                msg = new ActionMessage("error.input.njapan.text",
                        smtpServer,
                        nstr);
                errors.add("sml110SmtpUrl.error.input.njapan.text", msg);
            }


            //ポート番号
            if (StringUtil.isNullZeroString(sml110SmtpPort__)) {
                msg = new ActionMessage("error.input.required.text",
                        smtpSerPort);
                errors.add("sml110SmtpPort.error.input.required.text", msg);
            } else if (StringUtil.isNullZeroString(sml110SmtpPort__)) {
                msg = new ActionMessage("error.input.required.text",
                        smtpSerPort);
                errors.add("sml110SmtpPort.error.input.required.text", msg);
            //ポート番号 文字数
            } else if (sml110SmtpPort__.length() > GSConstSmail.MAX_LENGTH_SMTP_PORT) {
                msg = new ActionMessage("error.input.length.text",
                        smtpSerPort,
                        GSConstSmail.MAX_LENGTH_SMTP_PORT);
                errors.add("sml110SmtpPort.error.input.length.text", msg);
            //ポート番号 半角数字
            } else if (!ValidateUtil.isNumber(sml110SmtpPort__)) {
                msg = new ActionMessage("error.input.length.number2",
                        smtpSerPort,
                        String.valueOf(GSConstSmail.MAX_LENGTH_SMTP_PORT));
                errors.add("sml110SmtpPort.error.input.comp.text", msg);
            //ポート番号 最大値
            } else if (Integer.parseInt(sml110SmtpPort__)
                    > GSConstSmail.MAX_NUMBER_SMTP_PORTNUM) {
                msg = new ActionMessage("error.input.number.under",
                        smtpSerPort,
                        GSConstSmail.MAX_NUMBER_SMTP_PORTNUM);
                errors.add("sml110SmtpPort.error.input.comp.text", msg);
            }

            //認証ユーザ
            if (!StringUtil.isNullZeroString(sml110SmtpUser__)) {
                //ユーザID 文字数
                if (sml110SmtpUser__.length() > GSConstSmail.MAX_LENGTH_USER) {
                    msg = new ActionMessage("error.input.length.text",
                            userId,
                            GSConstSmail.MAX_LENGTH_USER);
                    errors.add("sml110SmtpUser.error.input.length.text", msg);
                //ユーザID 使用文字
                } else if (!GSValidateUtil.isGsJapaneaseString(sml110SmtpUser__)) {
                    String nstr = GSValidateUtil.getNotGsJapaneaseString(sml110SmtpUser__);
                    msg = new ActionMessage("error.input.njapan.text",
                            userId,
                            nstr);
                    errors.add("sml110SmtpUser.error.input.njapan.text", msg);
                }
            }
            //認証パスワード
            if (!StringUtil.isNullZeroString(sml110SmtpPass__)) {
                //パスワード 文字数
                if (sml110SmtpPass__.length() > GSConstSmail.MAX_LENGTH_PASS) {
                    msg = new ActionMessage("error.input.length.text",
                            userPass,
                            GSConstSmail.MAX_LENGTH_PASS);
                    errors.add("sml110SmtpPass.error.input.length.text", msg);
                //パスワード 使用文字
                } else if (!GSValidateUtil.isGsJapaneaseString(sml110SmtpPass__)) {
                    String nstr = GSValidateUtil.getNotGsJapaneaseString(sml110SmtpPass__);
                    msg = new ActionMessage("error.input.njapan.text",
                            userPass,
                            nstr);
                    errors.add("sml110SmtpPass.error.input.njapan.text", msg);
                }
            }
            //転送メールfromアドレス
            if (StringUtil.isNullZeroString(sml110FromAddress__)) {
                // 未入力チェック
                msg = new ActionMessage("error.input.required.text", fromAdr);
                StrutsUtil.addMessage(errors, msg, "sml110FromAddress"
                        + "error.input.required.text");
            } else {
                errors = validateMail(
                        errors,
                        sml110FromAddress__,
                        "sml110FromAddress",
                        fromAdr);
            }

            //転送先を制限する文字列の入力チェックを行う
            if (sml110FwLmtKbn__.equals(GSConstSmail.MAIL_FORWARD_LIMIT)) {
                errors = validateFwMail(errors, req);
            }
        }
        return errors;
    }

    /**
     * <p>転送先を制限する文字列の入力チェックを行う
     * @param errors ActionErrors
     * @param req リクエスト
     * @return ActionErrors
     */
    public ActionErrors validateFwMail(ActionErrors errors, HttpServletRequest req) {

        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage();
        String lengthLimit = gsMsg.getMessage(req, "sml.158");

        // 未入力チェック
        if (sml110FwlmtTextArea__ == null || sml110FwlmtTextArea__.equals("")) {

            msg = new ActionMessage("error.input.required.text", lengthLimit);
            StrutsUtil.addMessage(errors, msg, "sml110FwlmtTextArea"
                    + "error.input.required.text");
            return errors;
        }

        //転送先制限文字フォーマットチェック
        String[] fwlmtText = null;
        if (sml110FwlmtTextArea__ != null) {
            fwlmtText = sml110FwlmtTextArea__.split("\n");

        }

        for (int i = 0; fwlmtText.length > i; i++) {
            if (!GSValidateUtil.isAsciiOrNumber(StringUtilHtml.deleteHtmlTag(fwlmtText[i]))) {
                msg = new ActionMessage("error.input.format.text", lengthLimit);
                StrutsUtil.addMessage(errors, msg, "sml110FwlmtTextArea"
                        + "error.input.format.text");
                return errors;
            }
        }

    return errors;
    }

    /**
     * <p>メールアドレスの入力チェックを行う
     * @param errors ActionErrors
     * @param mail メールアドレス
     * @param eprefix メッセージサフィックス
     * @param text 項目名
     * @return ActionErrors
     */
    public static ActionErrors validateMail(ActionErrors errors,
            String mail, String eprefix, String text) {
        ActionMessage msg = null;

//        String eprefix = num + "mail.";

        if (!StringUtil.isNullZeroString(mail)) {
            if (mail.length() > GSConstUser.MAX_LENGTH_MAIL) {
                // MAX桁チェック
                msg = new ActionMessage("error.input.length.text",
                        text,
                        GSConstUser.MAX_LENGTH_MAIL);
                StrutsUtil.addMessage(errors, msg, eprefix
                        + "error.input.length.text");
            } else {

                //メールフォーマットチェック
                if (!GSValidateUtil.isMailFormat(mail)) {
                    msg = new ActionMessage("error.input.format.text", text);
                    StrutsUtil.addMessage(errors, msg, eprefix
                            + "error.input.format.text");
                }
            }

        }
        return errors;
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
     * @return sml110SslFlg
     */
    public int getSml110SslFlg() {
        return sml110SslFlg__;
    }

    /**
     * @param sml110SslFlg セットする sml110SslFlg
     */
    public void setSml110SslFlg(int sml110SslFlg) {
        sml110SslFlg__ = sml110SslFlg;
    }
}