package jp.groupsession.v2.cmn;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] 共通機能で使用する画面の入力チェックを行うクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class GSValidateCommon {

    /**
     * <br>[機  能] 削除するマイグループの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param groupSid 削除するマイグループSID
     * @param req リクエスト
     * @return ActionErrors
     */
    public static ActionErrors validateDeleteGroup(ActionErrors errors, String[] groupSid,
                                                   HttpServletRequest req) {
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage();
        /** メッセージ マイグループ **/
        String deleteMyGroup = gsMsg.getMessage(req, "cmn.delete.mygroup");

        //未選択チェック
        if (groupSid == null) {
            msg = new ActionMessage(
                    "error.select.required.text", deleteMyGroup);
            StrutsUtil.addMessage(errors, msg, "groupSid");
        } else {
            if (groupSid.length < 1) {
                msg = new ActionMessage(
                        "error.select.required.text", deleteMyGroup);
                StrutsUtil.addMessage(errors, msg, "groupSid");
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] マイグループ名の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param groupName マイグループ名
     * @param req リクエスト
     * @return ActionErrors
     */
    public static ActionErrors validateMyGroupName(ActionErrors errors, String groupName,
                                                   HttpServletRequest req) {
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        /** メッセージ マイグループ名 **/
        String myGroupName = gsMsg.getMessage(req, "cmn.cmn130.1");

        //未入力チェック
        if (StringUtil.isNullZeroString(groupName)) {
            msg = new ActionMessage("error.input.required.text", myGroupName);
            StrutsUtil.addMessage(errors, msg, "groupName");
            return errors;
        }

        //MAX桁チェック
        if (groupName.length() > GSConstCommon.MAX_LENGTH_MYGROUPNAME) {
            msg = new ActionMessage("error.input.length.text",
                                    myGroupName,
                                    GSConstCommon.MAX_LENGTH_MYGROUPNAME);
            StrutsUtil.addMessage(errors, msg, "groupName");
        }
        //スペースのみチェック
        if (ValidateUtil.isSpace(groupName)) {
            msg = new ActionMessage("error.input.spase.only", myGroupName);
            StrutsUtil.addMessage(errors, msg, "groupName");
        }
        //先頭スペースチェック
        if (ValidateUtil.isSpaceStart(groupName)) {
            msg = new ActionMessage("error.input.spase.start",
                                    myGroupName);
            StrutsUtil.addMessage(errors, msg, "groupName");
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseString(groupName)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(groupName);
            msg = new ActionMessage("error.input.njapan.text",
                                    myGroupName,
                                    nstr);
            StrutsUtil.addMessage(errors, msg, "groupName");
        }

        return errors;
    }

    /**
     * <br>[機  能] メモの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param memo メモ
     * @param req リクエスト
     * @return ActionErrors
     */
    public static ActionErrors validateMemo(ActionErrors errors, String memo,
                                            HttpServletRequest req) {
        ActionMessage msg = null;

        //未入力チェック
        if (StringUtil.isNullZeroString(memo)) {
            return errors;
        }

        GsMessage gsMsg = new GsMessage();
        /** メッセージ メモ **/
        String msgMemo = gsMsg.getMessage(req, "cir.11");


        //MAX桁チェック
        if (memo.length() > GSConstCommon.MAX_LENGTH_MEMO) {
            msg = new ActionMessage("error.input.length.text",
                                    msgMemo,
                                    GSConstCommon.MAX_LENGTH_MEMO);
            StrutsUtil.addMessage(errors, msg, "memo");
            return errors;
        }
        //スペース(改行)のみチェック
        if (ValidateUtil.isSpaceOrKaigyou(memo)) {
            msg = new ActionMessage("error.input.spase.cl.only", msgMemo);
            StrutsUtil.addMessage(errors, msg, "value");
            return errors;
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(memo)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(memo);
            msg = new ActionMessage("error.input.njapan.text",
                                    msgMemo,
                                    nstr);
            StrutsUtil.addMessage(errors, msg, "memo");
        }
        return errors;
    }

    /**
     * <br>[機  能] メンバーの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param userSid メンバーのユーザSID
     * @param req リクエスト
     * @return ActionErrors
     */
    public static ActionErrors validateMember(ActionErrors errors,
                                              String[] userSid, HttpServletRequest req) {
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        /** メッセージ メンバー **/
        String member = gsMsg.getMessage(req, "cmn.member");

        //未選択チェック
        if (userSid == null) {
            msg = new ActionMessage(
                    "error.select.required.text", member);
            StrutsUtil.addMessage(errors, msg, "userSid");
        } else {
            if (userSid.length < 1) {
                msg = new ActionMessage(
                        "error.select.required.text", member);
                StrutsUtil.addMessage(errors, msg, "userSid");
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 日付テキストフィールド(yyyy/MM/dd)の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックするフィールドの文字列
     * @param checkObject エラーメッセージ表示テキスト(例："名前" 例："コメント"）
     * @param fieldName チェックするフィールド
     * @param indispensable 入力が必須か true:必須 false:必須ではない
     * @return ActionErrors
     */
    public static ActionErrors validateDateFieldText(
                                        ActionErrors errors,
                                        String value,
                                        String checkObject,
                                        String fieldName,
                                        boolean indispensable) {

        ActionMessage msg = null;

        String fieldfix = checkObject + ".";

        if (StringUtil.isNullZeroString(value)) {
            //未入力チェック
            if (indispensable) {
                msg = new ActionMessage("error.input.required.text", checkObject);
                StrutsUtil.addMessage(
                        errors, msg, fieldfix + fieldName);
            }
            return errors;
        } else {
            //日付フォーマットチェック
            if (!ValidateUtil.isSlashDateFormat(value)) {
                msg = new ActionMessage("error.input.format.text", checkObject);
                StrutsUtil.addMessage(
                        errors, msg, fieldfix + fieldName);
            } else {
                //日付存在チェック
                UDate date = new UDate();
                int month = Integer.parseInt(value.substring(5, value.lastIndexOf("/")));
                date.setDate(Integer.parseInt(value.substring(0, 4)),
                            month,
                            Integer.parseInt(value.substring(value.lastIndexOf("/") + 1)));

                if (date.getMonth() != month) {
                    msg = new ActionMessage("error.input.notfound.date", checkObject);
                    StrutsUtil.addMessage(
                            errors, msg, fieldfix + fieldName);
                }
            }
        }


        return errors;
    }

    /**
     * <br>[機  能] 時刻テキストフィールド(hh:mm)の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックするフィールドの文字列
     * @param checkObject エラーメッセージ表示テキスト(例："名前" 例："コメント"）
     * @param fieldName チェックするフィールド
     * @param indispensable 入力が必須か true:必須 false:必須ではない
     * @return ActionErrors
     */
    public static ActionErrors validateTimeFieldText(
                                        ActionErrors errors,
                                        String value,
                                        String checkObject,
                                        String fieldName,
                                        boolean indispensable) {

        ActionMessage msg = null;

        String fieldfix = checkObject + ".";

        if (StringUtil.isNullZeroString(value)) {
            //未入力チェック
            if (indispensable) {
                msg = new ActionMessage("error.input.required.text", checkObject);
                StrutsUtil.addMessage(
                        errors, msg, fieldfix + fieldName);
            }
            return errors;
        } else {
            //時刻フォーマットチェック
            if (!ValidateUtil.isTimeFormat(value)) {
                msg = new ActionMessage("error.input.format.text", checkObject);
                StrutsUtil.addMessage(
                        errors, msg, fieldfix + fieldName);
            } else {
                //時刻存在チェック
                UDate date = new UDate();
                int hour = Integer.parseInt(value.substring(0, value.lastIndexOf(":")));
                date.setHour(hour);
                date.setMinute(Integer.parseInt(value.substring(value.lastIndexOf(":") + 1)));

                if (date.getIntHour() != hour) {
                    msg = new ActionMessage("error.input.notfound.time", checkObject);
                    StrutsUtil.addMessage(
                            errors, msg, fieldfix + fieldName);
                }
            }
        }


        return errors;
    }

    /**
     * <br>[機  能] 日時テキストフィールド(yyyy/mm/dd hh:mm)の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックするフィールドの文字列
     * @param checkObject エラーメッセージ表示テキスト(例："名前" 例："コメント"）
     * @param fieldName チェックするフィールド
     * @param indispensable 入力が必須か true:必須 false:必須ではない
     * @return ActionErrors
     */
    public static ActionErrors validateDateTimeFieldText(
                                        ActionErrors errors,
                                        String value,
                                        String checkObject,
                                        String fieldName,
                                        boolean indispensable) {

        ActionMessage msg = null;

        String fieldfix = checkObject + ".";

        if (StringUtil.isNullZeroString(value)) {
            //未入力チェック
            if (indispensable) {
                msg = new ActionMessage("error.input.required.text", checkObject);
                StrutsUtil.addMessage(
                        errors, msg, fieldfix + fieldName);
            }
            return errors;
        } else {
            //日時フォーマットチェック
            if (!ValidateUtil.isSlashDateTimeFormat(value)) {
                msg = new ActionMessage("error.input.format.text", checkObject);
                StrutsUtil.addMessage(
                        errors, msg, fieldfix + fieldName);
            } else {
                //日時存在チェック
                UDate date = new UDate();

                int month = Integer.parseInt(value.substring(5, 7));
                date.setDate(Integer.parseInt(value.substring(0, 4)),
                            month,
                            Integer.parseInt(value.substring(8, 10)));

                if (date.getMonth() != month) {
                    msg = new ActionMessage("error.input.notfound.date", checkObject);
                    StrutsUtil.addMessage(
                            errors, msg, fieldfix + fieldName);
                } else {

                    int hour = Integer.parseInt(value.substring(11, 13));
                    date.setHour(hour);
                    date.setMinute(Integer.parseInt(value.substring(14)));

                    if (date.getIntHour() != hour) {
                        msg = new ActionMessage("error.input.notfound.time", checkObject);
                        StrutsUtil.addMessage(
                                errors, msg, fieldfix + fieldName);
                    }
                }
            }
        }


        return errors;
    }

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
     * @return ActionErrors
     */
    public static ActionErrors validateTextField(
        ActionErrors errors,
        String value,
        String paramName,
        String paramNameJpn,
        int maxLength,
        boolean necessary) {
        ActionMessage msg = null;

        //未入力チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                msg = new ActionMessage("error.input.required.text", paramNameJpn);
                StrutsUtil.addMessage(errors, msg, paramName);
                return errors;
            }
            return errors;
        }

        //MAX桁チェック
        if (value.length() > maxLength) {
            msg = new ActionMessage("error.input.length.text", paramNameJpn, maxLength);
            StrutsUtil.addMessage(errors, msg, paramName);
            return errors;
        }
        //スペース(改行)のみチェック
        if (ValidateUtil.isSpaceOrKaigyou(value)) {
            msg = new ActionMessage("error.input.spase.cl.only", paramNameJpn);
            StrutsUtil.addMessage(errors, msg, paramName);
            return errors;
        }

        //先頭スペースチェック
        if (ValidateUtil.isSpaceStart(value)) {
            msg = new ActionMessage("error.input.spase.start", paramNameJpn);
            StrutsUtil.addMessage(errors, msg, paramName);
        }

        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
            msg = new ActionMessage("error.input.njapan.text",
                    paramNameJpn, nstr);
            StrutsUtil.addMessage(errors, msg, paramName);
        }

        return errors;
    }

    /**
     * <br>[機  能] テキストフィールド(カタカナ)の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value チェックを行う値
     * @param paramName パラメータ名 (xxxDate, wtbxxxText 等)
     * @param paramNameJpn パラメータの日本語名 (日付, 時刻 等)
     * @param maxLength 最大文字数
     * @param necessary 入力が必須か true:必須 false:必須ではない
     * @return ActionErrors
     */
    public static ActionErrors validateTextFieldKana(
        ActionErrors errors,
        String value,
        String paramName,
        String paramNameJpn,
        int maxLength,
        boolean necessary) {
        ActionMessage msg = null;

        //未入力チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                msg = new ActionMessage("error.input.required.text", paramNameJpn);
                StrutsUtil.addMessage(errors, msg, paramName);
                return errors;
            }
            return errors;
        }

        //MAX桁チェック
        if (value.length() > maxLength) {
            msg = new ActionMessage("error.input.length.text", paramNameJpn, maxLength);
            StrutsUtil.addMessage(errors, msg, paramName);
            return errors;
        }
        //スペース(改行)のみチェック
        if (ValidateUtil.isSpaceOrKaigyou(value)) {
            msg = new ActionMessage("error.input.spase.cl.only", paramNameJpn);
            StrutsUtil.addMessage(errors, msg, paramName);
            return errors;
        }

        //先頭スペースチェック
        if (ValidateUtil.isSpaceStart(value)) {
            msg = new ActionMessage("error.input.spase.start", paramNameJpn);
            StrutsUtil.addMessage(errors, msg, paramName);
            return errors;
        }

        if (!GSValidateUtil.isGsWideKana(value)) {
            //全角カナチェック
            String msgKey = "error.input.kana.text";
            msg = new ActionMessage(msgKey, paramNameJpn);
            StrutsUtil.addMessage(errors, msg, paramName);
            return errors;
        }

        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
            msg = new ActionMessage("error.input.njapan.text",
                    paramNameJpn, nstr);
            StrutsUtil.addMessage(errors, msg, paramName);
        }

        return errors;
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
    public static ActionErrors validateTextAreaField(
                                                ActionErrors errors,
                                                String value,
                                                String paramName,
                                                String paramNameJpn,
                                                int maxLength,
                                                boolean necessary) {

        ActionMessage msg = null;

        //未入力チェック
        if (StringUtil.isNullZeroString(value)) {
            if (necessary) {
                msg = new ActionMessage("error.input.required.text", paramNameJpn);
                StrutsUtil.addMessage(errors, msg, paramName);
                return errors;
            }
            return errors;
        }

        //MAX桁チェック
        if (value.length() > maxLength) {
            msg = new ActionMessage("error.input.length.text", paramNameJpn, maxLength);
            StrutsUtil.addMessage(errors, msg, paramName);
            return errors;
        }
        //スペース(改行)のみチェック
        if (ValidateUtil.isSpaceOrKaigyou(value)) {
            msg = new ActionMessage("error.input.spase.cl.only", paramNameJpn);
            StrutsUtil.addMessage(errors, msg, paramName);
            return errors;
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
            msg = new ActionMessage("error.input.njapan.text",
                    paramNameJpn, nstr);
            StrutsUtil.addMessage(errors, msg, paramName);
        }
        return errors;
    }
}