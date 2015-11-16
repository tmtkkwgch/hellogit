package jp.groupsession.v2.api.ntp.nippou.comment.edit;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.cmn.GSValidateCommon;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.ntp.GSConstNippou;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
/**
 *
 * <br>[機  能] WEBAPI 日報コメント投稿フォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiNippouCommentAddForm extends AbstractApiForm {
    /** 日報SID*/
    private String nipSid__;
    /** コメント*/
    private String comment__;
    /**
     * <p>comment を取得します。
     * @return comment
     */
    public String getComment() {
        return comment__;
    }
    /**
     * <p>comment をセットします。
     * @param comment comment
     */
    public void setComment(String comment) {
        comment__ = comment;
    }
    /**
     * <p>nipSid を取得します。
     * @return nipSid
     */
    public String getNipSid() {
        return nipSid__;
    }
    /**
     * <p>nipSid をセットします。
     * @param nipSid nipSid
     */
    public void setNipSid(String nipSid) {
        nipSid__ = nipSid;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param req リクエスト
     * @return errors エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(Connection con, HttpServletRequest req) throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        /** NIP_SID mapping */
        if (StringUtil.isNullZeroString(nipSid__)) {
            msg = new ActionMessage("error.input.required.text", GSConstNippou.TEXT_NIPPOU_SID);
            StrutsUtil.addMessage(errors, msg, "nipSid");
            return errors;
        }
        if (!GSValidateUtil.isNumberHaifun(nipSid__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", GSConstNippou.TEXT_NIPPOU_SID);
            StrutsUtil.addMessage(errors, msg, "nipSid");
            return errors;

        }
        /** NIP_DETAIL mapping */
        comment__ = NullDefault.getString(comment__, "");
        GSValidateCommon.validateTextAreaField(errors, comment__,
                "comment", "コメント",
                GSConstNippou.MAX_LENGTH_NAIYO, true);
        if (!errors.isEmpty()) {
            return errors;
        }


        return errors;
    }

}
