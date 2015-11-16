package jp.groupsession.v2.bbs;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSValidateUtil;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] 掲示板の入力チェックを行うクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class GSValidateBulletin {

    /**
     * <br>[機  能] タイトルの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param title タイトル
     * @param checkObject チェック対象
     * @param maxLength 最大文字数
     * @return ActionErrors
     */
    public static ActionErrors validateTitle(
        ActionErrors errors,
        String title,
        String checkObject,
        int maxLength) {

        ActionMessage msg = null;

        //未入力チェック
        if (StringUtil.isNullZeroString(title)) {
            msg = new ActionMessage("error.input.required.text", checkObject);
            StrutsUtil.addMessage(errors, msg, checkObject);
            return errors;
        }

        //MAX桁チェック
        if (title.length() > maxLength) {
            msg = new ActionMessage(
                    "error.input.length.text", checkObject, String.valueOf(maxLength));
            StrutsUtil.addMessage(errors, msg, checkObject);
        }
        //スペースのみチェック
        if (ValidateUtil.isSpace(title)) {
            msg = new ActionMessage("error.input.spase.only", checkObject);
            StrutsUtil.addMessage(errors, msg, checkObject);
        }
        //先頭スペースチェック
        if (ValidateUtil.isSpaceStart(title)) {
            msg = new ActionMessage("error.input.spase.start", checkObject);
            StrutsUtil.addMessage(errors, msg, checkObject);
        }

        //タブ文字が含まれている
        if (ValidateUtil.isTab(title)) {
            msg = new ActionMessage("error.input.tab.text", checkObject);
            StrutsUtil.addMessage(errors, msg, checkObject);
        }

        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseString(title)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(title);
            msg = new ActionMessage("error.input.njapan.text", checkObject, nstr);
            StrutsUtil.addMessage(errors, msg, checkObject);
        }

        return errors;
    }

    /**
     * <br>[機  能] 内容の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value 内容
     * @param checkObject チェック対象
     * @param maxLength 最大文字数
     * @return ActionErrors
     */
    public static ActionErrors validateValue(
        ActionErrors errors,
        String value,
        String checkObject,
        int maxLength) {

        ActionMessage msg = null;

        //未入力チェック
        if (StringUtil.isNullZeroString(value)) {
            msg = new ActionMessage("error.input.required.text", checkObject);
            StrutsUtil.addMessage(errors, msg, checkObject);
            return errors;
        }

        //MAX桁チェック
        if (value.length() > maxLength) {
            msg = new ActionMessage("error.input.length.textarea", checkObject, maxLength);
            StrutsUtil.addMessage(errors, msg, checkObject);
        }
        //スペース(改行)のみチェック
        if (ValidateUtil.isSpaceOrKaigyou(value)) {
            msg = new ActionMessage("error.input.spase.cl.only", checkObject);
            StrutsUtil.addMessage(errors, msg, checkObject);
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
            msg = new ActionMessage("error.input.njapan.text", checkObject, nstr);
            StrutsUtil.addMessage(errors, msg, checkObject);
        }
        return errors;
    }

}
