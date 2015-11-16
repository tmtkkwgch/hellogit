package jp.groupsession.v2.api.ntp.anken.detail;


import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.api.GSValidateApi;
import jp.groupsession.v2.ntp.GSConstNippou;

import org.apache.struts.action.ActionErrors;
/**
 * <br>[機  能] 日報 案件詳細取得するWEBAPIフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiAnkenDetailForm extends AbstractApiForm {
    /** 案件Sid */
    private String nanSid__;

    /**
     * <p>nanSid を取得します。
     * @return nanSid
     */
    public String getNanSid() {
        return nanSid__;
    }

    /**
     * <p>nanSid をセットします。
     * @param nanSid nanSid
     */
    public void setNanSid(String nanSid) {
        nanSid__ = nanSid;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return errors エラー
     */
    public ActionErrors validateCheck() {
        ActionErrors errors = new ActionErrors();
        GSValidateApi.validateSid(errors, nanSid__, "nanSid", GSConstNippou.TEXT_ANKEN_SID, true);
        return errors;
    }

}
