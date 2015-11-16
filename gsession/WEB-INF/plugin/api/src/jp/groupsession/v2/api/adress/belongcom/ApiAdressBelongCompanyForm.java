package jp.groupsession.v2.api.adress.belongcom;

import jp.groupsession.v2.api.AbstractApiForm;
/**
 *
 * <br>[機  能]WEB API アドレス帳 アドレス一覧取得フォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiAdressBelongCompanyForm extends AbstractApiForm {
    /** 会社SID*/
    private String acoSid__ = "-1";
    /** 会社拠点SID*/
    private String abaSid__ = "-1";
    /**
     * <p>acoSid を取得します。
     * @return acoSid
     */
    public String getAcoSid() {
        return acoSid__;
    }
    /**
     * <p>acoSid をセットします。
     * @param acoSid acoSid
     */
    public void setAcoSid(String acoSid) {
        acoSid__ = acoSid;
    }
    /**
     * <p>abaSid を取得します。
     * @return abaSid
     */
    public String getAbaSid() {
        return abaSid__;
    }
    /**
     * <p>abaSid をセットします。
     * @param abaSid abaSid
     */
    public void setAbaSid(String abaSid) {
        abaSid__ = abaSid;
    }


}
