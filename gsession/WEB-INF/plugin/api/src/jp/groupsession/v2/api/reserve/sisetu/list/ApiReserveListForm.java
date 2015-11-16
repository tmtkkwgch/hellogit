package jp.groupsession.v2.api.reserve.sisetu.list;

import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.api.GSValidateApi;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;

/**
 * <br>[機  能] 施設一覧取得WEBAPIフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiReserveListForm extends AbstractApiForm {
    /** 施設グループSID*/
    private String rsgSid__;

    /**
     * <p>rsgSid を取得します。
     * @return rsgSid
     */
    public String getRsgSid() {
        return rsgSid__;
    }

    /**
     * <p>rsgSid をセットします。
     * @param rsgSid rsgSid
     */
    public void setRsgSid(String rsgSid) {
        rsgSid__ = rsgSid;
    }
    /**
    *
    * <br>[機  能] 入力チェック
    * <br>[解  説]
    * <br>[備  考]
    * @param gsMsg GsMessage
    * @return errors
    */
   public ActionErrors validateCheck(
           GsMessage gsMsg) {
       ActionErrors errors = new ActionErrors();

       GSValidateApi.validateSid(errors, rsgSid__, "rsgSid"
               , gsMsg.getMessage("api.rsv.rsg.sid"), false);

       return errors;
   }
}
