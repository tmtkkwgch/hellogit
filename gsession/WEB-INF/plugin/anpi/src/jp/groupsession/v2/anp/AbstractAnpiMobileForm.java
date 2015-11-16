package jp.groupsession.v2.anp;

import org.apache.struts.action.ActionForm;

/**
 * <br>[機  能] HTML版モバイル 共通フォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class AbstractAnpiMobileForm extends ActionForm {

    /** 安否確認SID */
    private String anpiSid__;
    /** ユーザSID */
    private String userSid__;

    /**
     * <p>anpiSid を取得します。
     * @return anpiSid
     */
    public String getAnpiSid() {
        return anpiSid__;
    }
    /**
     * <p>anpiSid をセットします。
     * @param anpiSid anpiSid
     */
    public void setAnpiSid(String anpiSid) {
        anpiSid__ = anpiSid;
    }
    /**
     * <p>userSid を取得します。
     * @return userSid
     */
    public String getUserSid() {
        return userSid__;
    }
    /**
     * <p>userSid をセットします。
     * @param userSid userSid
     */
    public void setUserSid(String userSid) {
        userSid__ = userSid;
    }
}
