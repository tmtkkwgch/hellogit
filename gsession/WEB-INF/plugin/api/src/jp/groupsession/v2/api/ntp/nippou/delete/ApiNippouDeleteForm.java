package jp.groupsession.v2.api.ntp.nippou.delete;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpDataDao;
import jp.groupsession.v2.ntp.model.NtpDataModel;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
/**
 *
 * <br>[機  能] WEBAPI 日報削除フォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiNippouDeleteForm extends AbstractApiForm {
    /** 日報SID */
    private String nipSid__;
    /** ユーザSID */
    private String usrSid__;
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
     * <p>usrSid を取得します。
     * @return usrSid
     */
    public String getUsrSid() {
        return usrSid__;
    }
    /**
     * <p>usrSid をセットします。
     * @param usrSid usrSid
     */
    public void setUsrSid(String usrSid) {
        usrSid__ = usrSid;
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
            String nipSid = nipSid__;
            if (StringUtil.isNullZeroString(nipSid)) {
                msg = new ActionMessage("error.input.required.text", GSConstNippou.TEXT_NIPPOU_SID);
                StrutsUtil.addMessage(errors, msg, "nipSid");
                return errors;
            }
            if (!GSValidateUtil.isNumberHaifun(nipSid)) {
                msg = new ActionMessage(
                        "error.input.number.hankaku", GSConstNippou.TEXT_NIPPOU_SID);
                StrutsUtil.addMessage(errors, msg, "nipSid");
                return errors;

            }


            /** USR_SID mapping */
            String usrSid = usrSid__;
            if (StringUtil.isNullZeroString(usrSid)) {
                msg = new ActionMessage("error.input.required.text", GSConstNippou.TEXT_USER_SID);
                StrutsUtil.addMessage(errors, msg, "usrSid");
                return errors;
            }
            if (!GSValidateUtil.isNumberHaifun(usrSid)) {
                msg = new ActionMessage(
                        "error.input.number.hankaku", GSConstNippou.TEXT_USER_SID);
                StrutsUtil.addMessage(errors, msg, "usrSid");
                return errors;

            }
            //編集権減チェック
            HttpSession session = req.getSession();
            BaseUserModel usModel =
                (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
            int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

            //日報取得
            NtpDataDao dao = new NtpDataDao(con);
            NtpDataModel data = dao.select(Integer.parseInt(nipSid));
            if (!usModel.getAdminFlg() && sessionUsrSid != data.getUsrSid()) {

                msg = new ActionMessage(
                        "error.edit.power.user", "", "編集");
                StrutsUtil.addMessage(errors, msg, "admFlg");
                return errors;
            }


        return errors;
    }
}
