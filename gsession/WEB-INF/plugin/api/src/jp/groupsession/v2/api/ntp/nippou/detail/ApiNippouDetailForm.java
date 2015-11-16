package jp.groupsession.v2.api.ntp.nippou.detail;

import jp.groupsession.v2.api.AbstractApiForm;
/**
 * <br>[機  能] WEBAPI 日報詳細情報取得フォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiNippouDetailForm extends AbstractApiForm {
    /** ユーザSID */
    String usrSid__;
    /** 取得日 */
    String date__;
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
     * <p>date を取得します。
     * @return date
     */
    public String getDate() {
        return date__;
    }
    /**
     * <p>date をセットします。
     * @param date date
     */
    public void setDate(String date) {
        date__ = date;
    }

}
