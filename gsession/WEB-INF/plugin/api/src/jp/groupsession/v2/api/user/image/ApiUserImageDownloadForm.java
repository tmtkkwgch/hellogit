package jp.groupsession.v2.api.user.image;

import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.api.GSConstApi;
import jp.groupsession.v2.api.GSValidateApi;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
/**
 *
 * <br>[機  能] WEBAPI ユーザ画像取得
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiUserImageDownloadForm extends AbstractApiForm {
    /** usrSid*/
    private String usrSid__;

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
     * @param gsMsg GsMessage
     * @return エラー
     */
    public ActionErrors validateUsrInf(GsMessage gsMsg) {
        ActionErrors errors = new ActionErrors();
        GSValidateApi.validateSid(errors,
                usrSid__,
                "usrSid",
                gsMsg.getMessage(GSConstApi.USER_SID_STRING),
                true);

         return errors;
    }
}
