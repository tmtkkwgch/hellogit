package jp.groupsession.v2.cir;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] 回覧板の入力チェックを行うクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class GSValidateCircular {

    /**
     * <br>[機  能] 削除する回覧板の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param cirSid 削除するマイグループSID
     * @param text 項目名
     * @return ActionErrors
     */
    public static ActionErrors validateDeleteCir(
        ActionErrors errors,
        String[] cirSid,
        String text) {

        ActionMessage msg = null;

        //未選択チェック
        if (cirSid == null) {
            msg = new ActionMessage("error.select.required.text", text);
            StrutsUtil.addMessage(errors, msg, "groupSid");
        } else {
            if (cirSid.length < 1) {
                msg = new ActionMessage("error.select.required.text", text);
                StrutsUtil.addMessage(errors, msg, "groupSid");
            }
        }
        return errors;
    }

    /**
     * <br>[機  能] タイトルの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param title タイトル
     * @param reqMdl リクエスト情報
     * @return ActionErrors
     */
    public static ActionErrors validateTitle(ActionErrors errors, String title,
            RequestModel reqMdl) {
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage(reqMdl);
        String message = gsMsg.getMessage("cmn.title");

        //未入力チェック
        if (StringUtil.isNullZeroString(title)) {
            msg = new ActionMessage("error.input.required.text", message);
            StrutsUtil.addMessage(errors, msg, "title");
            return errors;
        }

        //MAX桁チェック
        if (title.length() > GSConstCircular.MAX_LENGTH_TITLE) {
            msg = new ActionMessage("error.input.length.text",
                                    message,
                                    GSConstCircular.MAX_LENGTH_TITLE);
            StrutsUtil.addMessage(errors, msg, "title");
        }
        //スペースのみチェック
        if (ValidateUtil.isSpace(title)) {
            msg = new ActionMessage("error.input.spase.only", message);
            StrutsUtil.addMessage(errors, msg, "title");
        }
        //先頭スペースチェック
        if (ValidateUtil.isSpaceStart(title)) {
            msg = new ActionMessage("error.input.spase.start", message);
            StrutsUtil.addMessage(errors, msg, "title");
        }
        //タブ文字が含まれている
        if (ValidateUtil.isTab(title)) {
            msg = new ActionMessage("error.input.tab.text", message);
            StrutsUtil.addMessage(errors, msg, "title");
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseString(title)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(title);
            msg = new ActionMessage("error.input.njapan.text",
                                    message,
                                    nstr);
            StrutsUtil.addMessage(errors, msg, "title");
        }

        return errors;
    }

    /**
     * <br>[機  能] 内容の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value 内容
     * @param reqMdl リクエスト情報
     * @return ActionErrors
     */
    public static ActionErrors validateValue(ActionErrors errors, String value,
            RequestModel reqMdl) {
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage(reqMdl);
        String message = gsMsg.getMessage("cmn.content");

        //未入力チェック
        if (StringUtil.isNullZeroString(value)) {
            msg = new ActionMessage("error.input.required.text", message);
            StrutsUtil.addMessage(errors, msg, "value");
            return errors;
        }

        //MAX桁チェック
        if (value.length() > GSConstCircular.MAX_LENGTH_VALUE) {
            msg = new ActionMessage("error.input.length.textarea",
                                    message,
                                    GSConstCircular.MAX_LENGTH_VALUE);
            StrutsUtil.addMessage(errors, msg, "value");
        }
        //スペース(改行)のみチェック
        if (ValidateUtil.isSpaceOrKaigyou(value)) {
            msg = new ActionMessage("error.input.spase.cl.only", message);
            StrutsUtil.addMessage(errors, msg, "value");
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
            msg = new ActionMessage("error.input.njapan.text",
                                    message,
                                    nstr);
            StrutsUtil.addMessage(errors, msg, "value");
        }
        return errors;
    }

    /**
     * <br>[機  能] 回覧先の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param userSid 回覧先
     * @param reqMdl リクエスト情報
     * @return ActionErrors
     */
    public static ActionErrors validateMember(ActionErrors errors, String[] userSid,
            RequestModel reqMdl) {
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textMember = gsMsg.getMessage("cir.20");

        //未選択チェック
        if (userSid == null) {
            msg = new ActionMessage("error.select.required.text", textMember);
            StrutsUtil.addMessage(errors, msg, "userSid");
        } else {
            if (userSid.length < 1) {
                msg = new ActionMessage("error.select.required.text", textMember);
                StrutsUtil.addMessage(errors, msg, "userSid");
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] メモの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param memo 内容
     * @param reqMdl リクエスト情報
     * @return ActionErrors
     */
    public static ActionErrors validateMemo(ActionErrors errors, String memo,
            RequestModel reqMdl) {
        ActionMessage msg = null;

        //未入力チェック
        if (StringUtil.isNullZeroString(memo)) {
            return errors;
        }

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textMemo = gsMsg.getMessage("cir.11");

        //MAX桁チェック
        if (memo.length() > GSConstCircular.MAX_LENGTH_MEMO) {
            msg = new ActionMessage("error.input.length.text",
                                    textMemo,
                                    GSConstCircular.MAX_LENGTH_MEMO);
            StrutsUtil.addMessage(errors, msg, "memo");
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(memo)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(memo);
            msg = new ActionMessage("error.input.njapan.text",
                                    textMemo,
                                    nstr);
            StrutsUtil.addMessage(errors, msg, "memo");
        }
        return errors;
    }

    /**
     * <br>[機  能] 検索キーワードの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param value 検査値
     * @param reqMdl リクエスト情報
     * @return true: エラーあり false: エラーなし
     */
    public static boolean validateSearchKeyword(ActionErrors errors, String value,
            RequestModel reqMdl) {
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textSearchKey = gsMsg.getMessage("cmn.search.keyword");

        //未入力はOK
        if (StringUtil.isNullZeroString(value)) {
            return false;
        }

        //スペースのみチェック
        if (ValidateUtil.isSpace(value)) {
            msg = new ActionMessage(
                    "error.input.spase.only", textSearchKey);
            StrutsUtil.addMessage(errors, msg, "keyWord");
            return true;
        }
        //先頭スペースチェック
        if (ValidateUtil.isSpaceStart(value)) {
            msg = new ActionMessage(
                    "error.input.spase.start", textSearchKey);
            StrutsUtil.addMessage(errors, msg, "keyWord");
            return true;
        }

        if (value.length() > GSConstCircular.MAX_LENGTH_KEYWORD) {
            //MAX桁チェック
            msg = new ActionMessage(
                    "error.input.length.text",
                    textSearchKey,
                    GSConstCircular.MAX_LENGTH_KEYWORD);
            StrutsUtil.addMessage(errors, msg, "keyWord__");
            return true;
        }

        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseString(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(value);
            msg = new ActionMessage(
                    "error.input.njapan.text", textSearchKey, nstr);
            StrutsUtil.addMessage(errors, msg, "keyWord");
            return true;
        }

        return false;
    }

    /**
     * <br>[機  能] 検索対象の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param keyWord キーワード
     * @param searchTarget 検索対象
     * @param reqMdl リクエスト情報
     * @return true: エラーあり false: エラーなし
     */
    public static boolean validateSearchTarget(
        ActionErrors errors,
        RequestModel reqMdl,
        String keyWord,
        String[] searchTarget) {

        ActionMessage msg = null;

        //キーワード未入力時はチェックなし
        if (NullDefault.getString(keyWord, "").length() < 1) {
            return false;
        }

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textTarget = gsMsg.getMessage("cmn.search2");

        if (searchTarget == null || searchTarget.length < 1) {
            //未選択の場合エラー
            msg = new ActionMessage(
                    "error.select.required.text", textTarget);
            StrutsUtil.addMessage(errors, msg, "target");
            return true;
        }

        return false;
    }

    /**
     * <br>[機  能] 検索ソート順の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param sortKey1 検査ソートKey1
     * @param sortKey2 検査ソートKey2
     * @param reqMdl リクエスト情報
     * @return true: エラーあり false: エラーなし
     */
    public static boolean validateSearchSortOrder(
            ActionErrors errors,
            RequestModel reqMdl,
            int sortKey1,
            int sortKey2) {
        ActionMessage msg = null;

        if (sortKey1 == sortKey2) {
            GsMessage gsMsg = new GsMessage(reqMdl);
            String textSortKey = gsMsg.getMessage("cmn.sortkey");

            //同一キー指定チェック
            msg = new ActionMessage("error.select.dup.list", textSortKey);
            StrutsUtil.addMessage(errors, msg, "sortKey");
            return true;
        }

        return false;
    }

    /**
     * <br>[機  能] テキストボックスの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param target チェック対象
     * @param targetName チェック対象名
     * @param targetJp チェック対象名(日本語)
     * @param maxLength 最大入力文字数
     * @param checkNoInput 未入力チェック true:チェックする false:チェックしない
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    public static boolean validateTextBoxInput(
            ActionErrors errors,
            String target,
            String targetName,
            String targetJp,
            int maxLength,
            boolean checkNoInput) {
        ActionMessage msg = null;

        String fieldFix = targetName + ".";

        if (StringUtil.isNullZeroString(target)) {
            if (!checkNoInput) {
                return false;
            }

            //未入力チェック
            msg = new ActionMessage("error.input.required.text", targetJp);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.required.text");
            return true;
        }

        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseString(target)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(target);
            msg = new ActionMessage("error.input.njapan.text",
                    targetJp, nstr);
            StrutsUtil.addMessage(errors, msg,
                                     fieldFix + "error.input.njapan.text");
            return true;
        }

        if (maxLength > 0 && target.length() > maxLength) {
            //MAX桁チェック
            msg = new ActionMessage("error.input.length.text",
                    targetJp, maxLength);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.length.text");
            return true;
        }

        if (ValidateUtil.isSpace(target)) {
            //スペースのみ
            String msgKey = "error.input.spase.only";
            msg = new ActionMessage(msgKey, targetJp);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + msgKey);
            return true;
        }

        if (ValidateUtil.isSpaceStart(target)) {
            //先頭スペース
            String msgKey = "error.input.spase.start";
            msg = new ActionMessage(msgKey, targetJp);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + msgKey);
            return true;
        }

        //タブ文字が含まれている
        if (ValidateUtil.isTab(target)) {
            String msgKey = "error.input.tab.text";
            msg = new ActionMessage(msgKey, targetJp);
            StrutsUtil.addMessage(errors, msg, fieldFix + msgKey);
            return true;
        }

        //入力エラー無し
        return false;
    }


    /**
     * <br>[機  能] テキストエリアの入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors ActionErrors
     * @param target チェック対象
     * @param targetName チェック対象名
     * @param targetJp チェック対象名(日本語)
     * @param maxLength 最大入力文字数
     * @param checkNoInput 未入力チェック true:チェックする false:チェックしない
     * @return チェック結果 true :エラー有り false :エラー無し
     */
    public static boolean validateTextarea(
            ActionErrors errors,
            String target,
            String targetName,
            String targetJp,
            int maxLength,
            boolean checkNoInput) {
        ActionMessage msg = null;

        String fieldFix = targetName + ".";

        if (StringUtil.isNullZeroString(target)) {
            if (!checkNoInput) {
                return false;
            }

            //未入力チェック
            msg = new ActionMessage("error.input.required.text", targetJp);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.required.text");
            return true;
        }

        //スペース・改行のみチェック
        if (ValidateUtil.isSpaceOrKaigyou(target)) {
            msg = new ActionMessage("error.input.spase.cl.only", targetJp);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.spase.cl.only");
            return true;
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(target)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(target);
            msg = new ActionMessage("error.input.njapan.text", targetJp, nstr);
            StrutsUtil.addMessage(errors, msg, fieldFix + "error.input.njapan.text");
            return true;
        }

        if (target.length() > maxLength) {
            //MAX桁チェック
            msg = new ActionMessage("error.input.length.text", targetJp, maxLength);
            StrutsUtil.addMessage(
                    errors, msg, fieldFix + "error.input.length.text");
            return true;
        }

        //入力エラー無し
        return false;
    }


    /**
     * <p>ユーザＩＤの入力チェックを行う(CSV取込み時)
     * @param errors ActionErrors
     * @param userid ユーザＩＤ
     * @param targetName 対象名
     * @param targetJp チェック対象名(日本語)
     * @param index 番号
     * @return ActionErrors
     */
    public static ActionErrors validateCsvUserId(
            ActionErrors errors,
            String userid,
            String targetName,
            String targetJp,
            int index) {

        ActionMessage msg = null;
        String eprefix = targetName + index + ".";

        if (!(StringUtil.isNullZeroString(userid))) {
            if (userid.length() < GSConstCircular.MINLEN_USERID
                    || userid.length() > GSConstCircular.MAXLEN_USERID) {
                //MIN,MAX桁チェック
                msg = new ActionMessage("error.input.length2.text",
                        targetJp + index,
                        GSConstCircular.MINLEN_USERID, GSConstCircular.MAXLEN_USERID);
                StrutsUtil.addMessage(errors, msg, eprefix + "error.input.length2.text");
            } else if (!GSValidateUtil.isUseridFormat(userid)) {
                //ユーザＩＤフォーマットチェック
                msg = new ActionMessage("error.input.format.text",
                        targetJp + index);
                StrutsUtil.addMessage(errors, msg, eprefix + "error.input.format.text");
            }
        }
        return errors;
    }

    /**
     * <p>ユーザＩＤの存在チェックを行う(CSV取込み時)
     * <p>自分のユーザIDは除く
     * @param errors ActionErrors
     * @param userid ユーザＩＤ
     * @param targetName 対象名
     * @param targetJp チェック対象名(日本語)
     * @param con DBコネクション
     * @param index 番号
     * @return ActionErrors
     * @throws SQLException SQL実行例外
     */
    public static ActionErrors validateCsvUserIdExist(
            ActionErrors errors,
            String userid,
            String targetName,
            String targetJp,
            Connection con,
            int index) throws SQLException {

        ActionMessage msg = null;
        String eprefix = targetName + index + ".";
        if (!(StringUtil.isNullZeroString(userid))) {
            CmnUsrmDao dao = new CmnUsrmDao(con);
            boolean ret = dao.existLoginidEdit(-1, userid);
            if (!ret) {
                //存在しない場合のエラー
                msg = new ActionMessage("error.not.exist.userid",
                        targetJp + index);
                StrutsUtil.addMessage(errors, msg, eprefix + "error.not.exist.userid");
            }
        }
        return errors;
    }
}
