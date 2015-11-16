package jp.groupsession.v2.anp;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.perl.Perl5Util;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;


/**
 * <br>[機  能] 入力チェックに関係するクラス
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class AnpiValidateUtil {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(AnpiValidateUtil.class);

    /**
     * <br>[機  能] テキストフィールドの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param maxLength 最大文字数
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateTextField(
        ActionErrors errors,
        String value,
        String paramName,
        String paramNameJpn,
        int maxLength,
        boolean necessary) {

        log__.debug(paramNameJpn + " のチェックを行います。");

        int startErrCount = errors.size();

        //入力必須チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                validateNecessary(errors, value, paramName, paramNameJpn);
                return false;
            }

            return true;
        }

        //スペースのみチェック
        if (!validateSpaceOnly(errors, value, paramName, paramNameJpn)) {
            return false;
        }

        //先頭スペースチェック
        if (!validateInitialSpace(errors, value, paramName, paramNameJpn)) {
            return false;
        }

        //タブスペースチェック
        if (!validateTabSpace(errors, value, paramName, paramNameJpn)) {
            return false;
        }

        //最大長チェック
        validateMaxLength(errors, value, paramName, paramNameJpn, maxLength);

        //カンマチェック
        if (value.indexOf(",") > 0) {
            ActionMessage msg =
                new ActionMessage("error.cantinput.connma", paramNameJpn);
            StrutsUtil.addMessage(errors, msg, paramName);
        }

        //JIS第二水準チェック
        validateGsJapanese(errors, value, paramName, paramNameJpn);

        return startErrCount == errors.size();
    }

    /**
     * <br>[機  能] 数字フィールドの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param maxLength 最大文字数
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateTextFieldOfNumber(
        ActionErrors errors,
        String value,
        String paramName,
        String paramNameJpn,
        int maxLength,
        boolean necessary) {

        log__.debug(paramNameJpn + " のチェックを行います。");

        int startErrCount = errors.size();
        //入力必須チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                validateNecessary(errors, value, paramName, paramNameJpn);
                return false;
            }

            return true;
        }

        //最大長チェック
        validateMaxLength(errors, value, paramName, paramNameJpn, maxLength);

        //半角数字チェック
        validateNumber(errors, value, paramName, paramNameJpn);

        return startErrCount == errors.size();
    }

    /**
     * <br>[機  能] 半角英数字フィールドの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param maxLength 最大文字数
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateTextFieldOfAlphaNum(
        ActionErrors errors,
        String value,
        String paramName,
        String paramNameJpn,
        int maxLength,
        boolean necessary) {

        log__.debug(paramNameJpn + " のチェックを行います。");

        int startErrCount = errors.size();
        //入力必須チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                validateNecessary(errors, value, paramName, paramNameJpn);
                return false;
            }

            return true;
        }

        //最大長チェック
        validateMaxLength(errors, value, paramName, paramNameJpn, maxLength);

        //半角英数字チェック
        validateAlphaNum(errors, value, paramName, paramNameJpn);

        return startErrCount == errors.size();
    }

    /**
     * <p>電話番号の入力チェックを行う
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param maxLength 最大文字数
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateTel(
                                    ActionErrors errors,
                                    String value,
                                    String paramName,
                                    String paramNameJpn,
                                    int maxLength,
                                    boolean necessary) {

        int startErrCnt = errors.size();

        //入力必須チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                validateNecessary(errors, value, paramName, paramNameJpn);
                return false;
            }

            return true;
        }

        //最大長チェック
        validateMaxLength(errors, value, paramName, paramNameJpn, maxLength);

        //電話番号フォーマットチェック
        ActionMessage msg = null;
        if (!GSValidateUtil.isTel(value)) {
            msg = new ActionMessage("error.input.format.text", paramNameJpn);
            StrutsUtil.addMessage(errors, msg, paramName
                    + ".error.input.format.text");
        }

        return startErrCnt == errors.size();
    }

    /**
     * <br>[機  能] 全角カナフィールドの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param maxLength 最大文字数
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateTextFieldKana(
        ActionErrors errors,
        String value,
        String paramName,
        String paramNameJpn,
        int maxLength,
        boolean necessary) {

        log__.debug(paramNameJpn + " のチェックを行います。");

        int startErrCount = errors.size();

        //入力必須チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                validateNecessary(errors, value, paramName, paramNameJpn);
                return false;
            }

            return true;
        }

        //スペースのみチェック
        if (!validateSpaceOnly(errors, value, paramName, paramNameJpn)) {
            return false;
        }

        //先頭スペースチェック
        if (!validateInitialSpace(errors, value, paramName, paramNameJpn)) {
            return false;
        }

        //最大長チェック
        validateMaxLength(errors, value, paramName, paramNameJpn, maxLength);

        //全角カナチェック
        validateKana(errors, value, paramName, paramNameJpn);

        return startErrCount == errors.size();
    }

    /**
     * <br>[機  能] テキストエリアの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param maxLength 最大文字数
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateTextAreaField(
                                                ActionErrors errors,
                                                String value,
                                                String paramName,
                                                String paramNameJpn,
                                                int maxLength,
                                                boolean necessary) {

        log__.debug(paramNameJpn + " のチェックを行います。");

        int startErrCount = errors.size();

        //入力必須チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                validateNecessary(errors, value, paramName, paramNameJpn);
                return false;
            }
            return true;
        }

        //スペース、改行のみチェック
        if (!validateSpaceOrBROnly(errors, value, paramName, paramNameJpn)) {
            return false;
        }

        //先頭スペースチェック
        if (!validateInitialSpace(errors, value, paramName, paramNameJpn)) {
            return false;
        }

        //最大長チェック
        validateMaxLength(errors, value, paramName, paramNameJpn, maxLength);

        //JIS第二水準チェック
        validateGsJapanese(errors, value, paramName, paramNameJpn);

        return startErrCount == errors.size();
    }


    /**
     * <br>[機  能] 未入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateNecessary(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn) {

        String fieldfix = paramName + ".";

        if (StringUtil.isNullZeroString(value)) {
            //未入力チェック
            String msgKey = "error.input.required.text";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
            return false;
        }
        return true;
    }

    /**
     * <br>[機  能] スペースのみチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateSpaceOnly(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn) {

        String fieldfix = paramName + ".";

        if (ValidateUtil.isSpace(value)) {
            String msgKey = "error.input.spase.only";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
            return false;
        }
        return true;
    }

    /**
     * <br>[機  能] スペース、改行のみのみチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateSpaceOrBROnly(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn) {

        String fieldfix = paramName + ".";

        if (ValidateUtil.isSpaceOrKaigyou(value)) {
            String msgKey = "error.input.spase.cl.only";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
            return false;
        }

        return true;
    }

    /**
     * <br>[機  能] 先頭スペースチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateInitialSpace(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn) {

        String fieldfix = paramName + ".";

        if (ValidateUtil.isSpaceStart(value)) {
            String msgKey = "error.input.spase.start";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
            return false;
        }

        return true;
    }

    /**
     * <br>[機  能] 最大長を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param maxLength 最大長
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateMaxLength(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn,
                                int maxLength) {

        String fieldfix = paramName + ".";

        if (value.length() > maxLength) {
            String msgKey = "error.input.length.text";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn, maxLength);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
            return false;
        }

        return true;
    }

    /**
     * <br>[機  能] 半角数字チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateNumber(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn) {

        String fieldfix = paramName + ".";
        if (!GSValidateUtil.isNumber(value)) {
            String msgKey = "error.input.number.hankaku";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
            return false;
        }

        return true;
    }

    /**
     * <br>[機  能] ユーザIDのフォーマットチェックを行う
     * <br>[解  説]
     * <br>[備  考] 半角英数、「.」、「_」、「-」、「@」が使用可能
     * @param input 文字列
     * @return true: 正常なユーザＩＤフォーマット, false: ユーザＩＤのフォーマットではない
     */
    public static boolean isUseridFormat(String input) {
        Perl5Util util = new Perl5Util();
        if (util.match("/^[A-Z0-9a-z@._\\-]+$/", input)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <br>[機  能] 半角英数字チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateAlphaNum(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn) {

        String fieldfix = paramName + ".";
        if (!GSValidateUtil.isAlphaNum(value)) {
            String msgKey = "error.input.text.hankaku";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
            return false;
        }

        return true;
    }

    /**
     * <br>[機  能] 全角カナチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateKana(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn) {

        String fieldfix = paramName + ".";

        if (!GSValidateUtil.isGsWideKana(value)) {
            //全角カナチェック
            String msgKey = "error.input.kana.text";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(errors, msg, fieldfix + msgKey);
            return false;
        }

        return true;
    }

    /**
     * <br>[機  能] 利用可能文字(JIS第二水準)チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateGsJapanese(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn) {

        String fieldfix = paramName + ".";

        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);

            String msgKey = "error.input.njapan.text";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn, nstr);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
            return false;
        }

        return true;
    }

    /**
     * <br>[機  能] 日付の論理チェックを行います
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param targetName チェック対象
     * @param year 年
     * @param month 月
     * @param day 日
     * @param req リクエスト
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateDate(
            ActionErrors errors,
            String targetName,
            String year,
            String month,
            String day,
            HttpServletRequest req) {

        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage();

        String eprefix = "date";
        String fieldFix = targetName + ".";
        String inputedDate = gsMsg.getMessage(req, "cmn.year", year)
                           + month + gsMsg.getMessage(req, "cmn.Monday")
                           + day + gsMsg.getMessage(req, "cmn.day");
        int iBYear = Integer.parseInt(year);
        int iBMonth = Integer.parseInt(month);
        int iBDay = Integer.parseInt(day);

        UDate date = new UDate();
        date.setDate(iBYear, iBMonth, iBDay);
        if (date.getYear() != iBYear
        || date.getMonth() != iBMonth
        || date.getIntDay() != iBDay) {
            msg = new ActionMessage("error.input.notfound.date",
                    targetName + "（" + inputedDate + ")");
            StrutsUtil.addMessage(errors, msg, eprefix + fieldFix
                    + "error.input.notfound.date");
            return true;
        }
        //エラー無し
        return false;
    }

    /**
     * <br>[機  能] 年月日大小チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param targetNameS チェック対象(開始日付)
     * @param targetNameE チェック対象(終了日付)
     * @param startDate 開始日付
     * @param endDate 終了日付
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    public static boolean validateDataRange(ActionErrors errors,
                                                   String targetNameS,
                                                   String targetNameE,
                                                   UDate startDate,
                                                   UDate endDate) {
        ActionMessage msg = null;
        String eprefix = "dateRange.";
        String fieldFix = targetNameS + "." + targetNameE + ".";

        if (endDate.compareDateYMD(startDate) == UDate.LARGE) {
            msg = new ActionMessage(
                    "error.input.comp.text",
                    targetNameS + "・" + targetNameE, targetNameS + "<" + targetNameE);
            StrutsUtil.addMessage(errors, msg, eprefix + fieldFix + "error.input.comp.text");
            return true;
        }

        //入力エラー無し
        return false;
    }

    /**
     * <br>[機  能] 年月日コンボ選択チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param paramName パラメータ名
     * @param year チェック対象(年)
     * @param month チェック対象(月)
     * @param date チェック対象(日)
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @param req リクエスト
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    public static boolean validateDataListSel(ActionErrors errors,
                                                   String paramName,
                                                   String year,
                                                   String month,
                                                   String date,
                                                   boolean necessary,
                                                   HttpServletRequest req) {
        log__.debug(paramName + " のチェックを行います。");

        year = NullDefault.getString(year, "-1");
        month = NullDefault.getString(month, "-1");
        date = NullDefault.getString(date, "-1");
        String fieldfix = paramName + ".";

        //選択チェック
        if (!year.equals("-1") || !month.equals("-1") || !date.equals("-1")) {
            if (year.equals("-1") || month.equals("-1") || date.equals("-1")) {
                String msgKey = "error.input.notvalidate.data";
                ActionMessage msg = new ActionMessage(msgKey, paramName);
                StrutsUtil.addMessage(
                        errors, msg, fieldfix + msgKey);
                return true;
            }
        }

        //入力必須チェック
        if (year.equals("-1") && month.equals("-1") && date.equals("-1")) {
            if (necessary == true) {
                String msgKey = "error.input.required.text";
                ActionMessage msg = new ActionMessage(msgKey, paramName);
                StrutsUtil.addMessage(
                        errors, msg, fieldfix + msgKey);
                return true;
            }
        //論理チェック
        } else if (AnpiValidateUtil.validateDate(errors, paramName, year, month, date, req)) {
            return true;
        }

        //入力エラー無し
        return false;
    }

    /**
     * <br>[機  能] 郵便番号の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param num1 上３桁
     * @param num2 下４桁
     * @param req リクエスト
     * @return ActionErrors
     */
    public static ActionErrors validatePostNum(ActionErrors errors,
                                                String num1, String num2, HttpServletRequest req) {
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage();
        String eprefix1 = "post1.";
        String eprefix2 = "post2.";

        boolean errorFlg = false;
        boolean input1 = false;
        boolean input2 = false;

        //上３桁
        if (!StringUtil.isNullZeroString(num1)) {
            input1 = true;
            if (num1.length() > GSConstAddress.MAX_LENGTH_POSTNO1) {
                // MAX桁チェック
                msg = new ActionMessage("error.input.length.text",
                        //郵便番号上3桁
                        gsMsg.getMessage(req, "user.src.30"),
                        GSConstAddress.MAX_LENGTH_POSTNO1);
                StrutsUtil.addMessage(errors, msg, eprefix1
                        + "error.input.length.text");
                errorFlg = true;
            } else {

                //郵便番号フォーマットチェック
                if (!GSValidateUtil.isNumber(num1)
                        || num1.length() != GSConstAddress.MAX_LENGTH_POSTNO1) {
                    msg = new ActionMessage("error.input.comp.text",
                            //郵便番号上3桁
                            gsMsg.getMessage(req, "user.src.30"),
                            gsMsg.getMessage(req, "user.src.54"));
                    StrutsUtil.addMessage(errors, msg, eprefix1
                            + "error.input.comp.text");
                    errorFlg = true;
                }
            }

        }
        //下４桁
        if (!StringUtil.isNullZeroString(num2)) {
            input2 = true;
            if (num1.length() > GSConstAddress.MAX_LENGTH_POSTNO2) {
                // MAX桁チェック
                msg = new ActionMessage("error.input.length.text",
                        //郵便番号下4桁
                        gsMsg.getMessage(req, "user.src.31"),
                        GSConstAddress.MAX_LENGTH_POSTNO2);
                StrutsUtil.addMessage(errors, msg, eprefix2
                        + "error.input.length.text");
                errorFlg = true;
            } else {

                //郵便番号フォーマットチェック
                if (!GSValidateUtil.isNumber(num2)
                        || num2.length() != GSConstAddress.MAX_LENGTH_POSTNO2) {
                    msg = new ActionMessage("error.input.comp.text",
                            //郵便番号下4桁
                            gsMsg.getMessage(req, "user.src.31"),
                            gsMsg.getMessage(req, "user.src.55"));
                    StrutsUtil.addMessage(errors, msg, eprefix2
                            + "error.input.comp.text");
                    errorFlg = true;
                }
            }

        }

        //総合チェック
        if (!errorFlg) {
            if (input2) {
                validateNecessary(errors,
                                  num1,
                                  "post1",
                                  //郵便番号上3桁
                                  gsMsg.getMessage(req, "user.src.30"));
            }
            if (input1) {
                validateNecessary(errors,
                                  num2,
                                  "post2",
                                  //郵便番号下4桁
                                  gsMsg.getMessage(req, "user.src.31"));
            }
        }
        return errors;
    }


    /**
     * <br>[機  能] CSV項目の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param maxLength 最大文字数
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @param rowNum 行番号
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateCsvText(
        ActionErrors errors,
        String value,
        String paramName,
        String paramNameJpn,
        int maxLength,
        boolean necessary,
        int rowNum) {

        log__.debug(paramNameJpn + " のチェックを行います。");

        int startErrCount = errors.size();

        //入力必須チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                validateNecessary(errors, value, paramName, paramNameJpn);
                return false;
            }

            return true;
        }

        //スペースのみチェック
        if (!validateSpaceOnly(errors, value, paramName, paramNameJpn)) {
            return false;
        }

        //先頭スペースチェック
        if (!validateInitialSpace(errors, value, paramName, paramNameJpn)) {
            return false;
        }

        //最大長チェック
        validateMaxLength(errors, value, paramName, paramNameJpn, maxLength);

        //JIS第二水準チェック
        validateGsJapanese(errors, value, paramName, paramNameJpn);

        return startErrCount == errors.size();
    }

    /**
     * <br>[機  能] メールアドレスの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateMail(
            ActionErrors errors,
            String value,
            String paramName,
            String paramNameJpn,
            boolean necessary) {

        log__.debug(paramNameJpn + " のチェックを行います。");

        String fieldfix = paramName + ".";
        int startErrCount = errors.size();

        //入力必須チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                validateNecessary(errors, value, paramName, paramNameJpn);
                return false;
            }

            return true;
        }

        //最大長チェック
        validateMaxLength(errors, value, paramName, paramNameJpn, GSConstUser.MAX_LENGTH_MAIL);

        //メールフォーマットチェック
        if (!GSValidateUtil.isMailFormat(value)) {
            String msgKey = "error.input.format.text";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
        }

        return startErrCount == errors.size();
   }

    /**
     * <br>[機  能] TABスペースチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    public static boolean validateTabSpace(
                                ActionErrors errors,
                                String value,
                                String paramName,
                                String paramNameJpn) {

        String fieldfix = paramName + ".";

        if (ValidateUtil.isTab(value)) {
            //タブ文字が含まれている
            String msgKey = "error.input.tab.text";
            ActionMessage msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
            return false;
        }

        return true;
    }
}