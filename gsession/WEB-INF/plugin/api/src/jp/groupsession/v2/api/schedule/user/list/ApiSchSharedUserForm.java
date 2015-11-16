package jp.groupsession.v2.api.schedule.user.list;

import jp.groupsession.v2.api.AbstractApiForm;
/**
 * <br>[機  能] WEBAPI スケジュール用ユーザ一覧取得フォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiSchSharedUserForm extends AbstractApiForm {
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


}
