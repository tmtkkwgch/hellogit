package jp.groupsession.v2.api.cmn.getholiday;

import jp.groupsession.v2.api.AbstractApiForm;
import jp.groupsession.v2.cmn.GSValidateCommon;

import org.apache.struts.action.ActionErrors;

/**
 * <br>[機  能] cmn/getholidayのform
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiCmnGetHolidayForm extends AbstractApiForm {
    /** 取得開始日 */
    private String from__;
    /** 取得終了日 */
    private String to__;
    /**
     * <p>from を取得します。
     * @return from
     */
    public String getFrom() {
        return from__;
    }
    /**
     * <p>from をセットします。
     * @param from from
     */
    public void setFrom(String from) {
        this.from__ = from;
    }
    /**
     * <p>to を取得します。
     * @return to
     */
    public String getTo() {
        return to__;
    }
    /**
     * <p>to をセットします。
     * @param to to
     */
    public void setTo(String to) {
        this.to__ = to;
    }
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @return エラー
     */
    public ActionErrors validateFromTo() {
        ActionErrors errors = new ActionErrors();
        //開始日FROM startFrom
        GSValidateCommon.validateDateFieldText(errors, from__,
                "from", "from", false);
        //開始日TO   startTo
        GSValidateCommon.validateDateFieldText(errors, to__, "to", "to", false);
        return errors;
    }
}
