package jp.groupsession.v2.api.ntp.nippou.comment.delete;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.ntp.dao.NtpCommentDao;
import jp.groupsession.v2.ntp.model.NtpCommentModel;
/**
 * コメント削除APIform
 * <br>[機  能] WEBAPI 日報 コメント削除フォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiNippouCommentDeleteForm extends AbstractApiForm {
    /** コメントSID*/
    private String npcSid__;

    /**
     * <p>npcSid を取得します。
     * @return npcSid
     */
    public String getNpcSid() {
        return npcSid__;
    }

    /**
     * <p>npcSid をセットします。
     * @param npcSid npcSid
     */
    public void setNpcSid(String npcSid) {
        npcSid__ = npcSid;
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
        if (StringUtil.isNullZeroString(npcSid__)) {
            msg = new ActionMessage("error.input.required.text", "コメントSid");
            StrutsUtil.addMessage(errors, msg, "npcSid");
            return errors;
        }
        if (!GSValidateUtil.isNumberHaifun(npcSid__)) {
            msg = new ActionMessage(
                    "error.input.number.hankaku", "コメントSid");
            StrutsUtil.addMessage(errors, msg, "npcSid");
            return errors;

        }
        //編集権減チェック
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //コメント取得
        NtpCommentDao comDao = new NtpCommentDao(con);
        NtpCommentModel comModel = comDao.select(Integer.parseInt(npcSid__));

        if (!usModel.getAdminFlg() && sessionUsrSid != comModel.getUsrSid()) {

            msg = new ActionMessage(
                    "error.edit.power.user", "", "削除");
            StrutsUtil.addMessage(errors, msg, "admFlg");
            return errors;
        }


        return errors;
    }

}
