package jp.groupsession.v2.api.user.belong;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.api.GSConstApi;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] 指定したグループに所属するユーザ一覧を取得するWEBAPIフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiBelongForm extends AbstractApiForm {
    /** グループSID */
    private String grpSid__ = null;

    /**
     * <p>grpSid を取得します。
     * @return grpSid
     */
    public String getGrpSid() {
        return grpSid__;
    }

    /**
     * <p>grpSid をセットします。
     * @param grpSid grpSid
     */
    public void setGrpSid(String grpSid) {
        grpSid__ = grpSid;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateUsrBelong(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        if (StringUtil.isNullZeroString(grpSid__)) {
            //未入力
            msg = new ActionMessage("error.input.required.text",
                                    getInterMessage(req, GSConstApi.GROUP_SID_STRING));
            StrutsUtil.addMessage(errors, msg, "grpSid");
        } else {

            //数字チェック
            if (!ValidateUtil.isNumber(grpSid__)) {
                msg = new ActionMessage("error.input.number.hankaku",
                                        getInterMessage(req, GSConstApi.GROUP_SID_STRING));
                StrutsUtil.addMessage(errors, msg, "grpSid");
            }
        }

         return errors;
    }
}
