package jp.groupsession.v2.bmk;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSValidateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] ブックマークの入力チェックを行うクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class GSValidateBookmark {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(GSValidateBookmark.class);

    /** epefix RSS */
    private static final String E_BOOKMARK__ = "bookmark";

    /**
     * <br>[機  能] 削除するブックマークの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param bmkSid 削除するブックマークSID
     * @param text 項目名
     * @return ActionErrors
     */
    public static ActionErrors validateDeleteCir(
        ActionErrors errors,
        String[] bmkSid,
        String text) {

        ActionMessage msg = null;

        //未選択チェック
        if (bmkSid == null) {
            msg = new ActionMessage("error.select.required.text", text);
            StrutsUtil.addMessage(errors, msg, "groupSid");
        } else {
            if (bmkSid.length < 1) {
                msg = new ActionMessage("error.select.required.text", text);
                StrutsUtil.addMessage(errors, msg, "groupSid");
            }
        }
        return errors;
    }

    /**
     * <br>[機  能] テキストフィールドの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param checkObject エラーメッセージ表示テキスト(例："名前" 例："コメント"）
     * @param field チェックするフィールド
     * @param strField チェックするフィールドの文字列
     * @param maxLength 最大文字数
     * @param hisuFlg 入力が必須か true:必須 false:必須ではない
     * @return ActionErrors
     */
    public static ActionErrors validateCmnFieldText(
        ActionErrors errors,
        String checkObject,
        String field,
        String strField,
        int maxLength,
        boolean hisuFlg) {
        log__.debug(strField + " のチェックを行います。");
        ActionMessage msg = null;

        String eprefix = E_BOOKMARK__;
        String fieldfix = checkObject + ".";

        if (StringUtil.isNullZeroString(field)) {
            if (hisuFlg) {
                //未入力チェック
                msg = new ActionMessage("error.input.required.text", checkObject);
                StrutsUtil.addMessage(
                        errors, msg, eprefix + fieldfix + strField);
            }
            return errors;
        }

        if (ValidateUtil.isSpace(field)) {
            //スペースのみチェック
            msg = new ActionMessage("error.input.spase.only", checkObject);
            StrutsUtil.addMessage(
                    errors, msg, eprefix + fieldfix + strField);
            return errors;
        }

        if (ValidateUtil.isSpaceStart(field)) {
            //先頭スペースチェック
            msg = new ActionMessage("error.input.spase.start", checkObject);
            StrutsUtil.addMessage(errors, msg,
                    eprefix + fieldfix + strField);
            return errors;
        }

        if (ValidateUtil.isTab(field)) {
            //タブ文字が含まれている
            msg = new ActionMessage("error.input.tab.text", checkObject);
            StrutsUtil.addMessage(errors, msg,
                    eprefix + fieldfix + strField);
            return errors;
        }

        if (field.length() > maxLength) {
            //MAX桁チェック
            msg = new ActionMessage("error.input.length.text", checkObject,
                    maxLength);
            StrutsUtil.addMessage(
                    errors, msg, eprefix + fieldfix + strField);
            return errors;
        }

        if (!GSValidateUtil.isGsJapaneaseStringTextArea(field)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(field);
            msg = new ActionMessage("error.input.njapan.text", checkObject, nstr);
            StrutsUtil.addMessage(
                    errors, msg, eprefix + fieldfix + strField);
            return errors;
        }
        return errors;
    }

    /**
     * <br>[機  能] テキストエリアの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param checkObject エラーメッセージ表示テキスト(例："名前" 例："コメント"）
     * @param field チェックするフィールド
     * @param strField チェックするフィールドの文字列
     * @param maxLength 最大文字数
     * @param hisuFlg 入力が必須か true:必須 false:必須ではない
     * @return ActionErrors
     */
    public static ActionErrors validateFieldTextArea(
            ActionErrors errors,
            String checkObject,
            String field,
            String strField,
            int maxLength,
            boolean hisuFlg) {
        log__.debug(strField + " のチェックを行います。");
        ActionMessage msg = null;

        String eprefix = E_BOOKMARK__;
        String fieldfix = checkObject + ".";

        if (StringUtil.isNullZeroString(field)) {
            if (hisuFlg) {
                //未入力チェック
                msg = new ActionMessage("error.input.required.text", checkObject);
                StrutsUtil.addMessage(
                        errors, msg, eprefix + fieldfix + strField);
            }
            return errors;
        }

        if (field.length() > maxLength) {
            //MAX桁チェック
            msg = new ActionMessage("error.input.length.textarea", checkObject,
                    maxLength);
            StrutsUtil.addMessage(
                    errors, msg, eprefix + fieldfix + strField);

        //スペース、改行のみチェック
        } else if (ValidateUtil.isSpaceOrKaigyou(field)) {
            msg = new ActionMessage("error.input.spase.cl.only", checkObject);
            StrutsUtil.addMessage(errors, msg,
                    eprefix + fieldfix + strField);

        } else if (!GSValidateUtil.isGsJapaneaseStringTextArea(field)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(field);
            msg = new ActionMessage("error.input.njapan.text", checkObject, nstr);
            StrutsUtil.addMessage(
                    errors, msg, eprefix + fieldfix + strField);
        }

        return errors;
    }
}
