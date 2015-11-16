package jp.groupsession.v2.api.user.belongname;

import jp.groupsession.v2.api.AbstractApiForm;

/**
 *
 * <br>[機  能] グループ所属ユーザ名一覧取得WEBAPIフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiBelongUserNameForm extends AbstractApiForm {
    /** グループSID*/
    private String grpSid__;

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
}
