package jp.groupsession.v2.bmk.bmk110;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.bmk100.Bmk100ParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 管理者設定 権限設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk110ParamModel extends Bmk100ParamModel {
    /** 共有ブックマーク編集区分 */
    private int bmk110PubEditKbn__ = -1;
    /** グループブックマーク編集区分 */
    private int bmk110GrpEditKbn__ = -1;

    //共有ブックマーク編集区分、グループ指定時
    /** 追加済みグループSID */
    private String[] bmk110GrpSid__ = null;
    /** 追加用グループ一覧 */
    private List<LabelValueBean> bmk110LeftGroupList__ = null;
    /** 追加済みグループ一覧 */
    private List<LabelValueBean> bmk110RightGroupList__ = null;
    /** 追加用グループ(選択) */
    private String[] bmk110SelectLeftGroup__ = null;
    /** 追加済みグループ(選択) */
    private String[] bmk110SelectRightGroup__ = null;

    //共有ブックマーク編集区分、ユーザ指定時
    /** 追加済みユーザSID */
    private String[] bmk110UserSid__ = null;

    /** グループSID */
    private int bmk110GroupSid__ = 0;
    /** グループ一覧 */
    private List<LabelValueBean> bmk110GroupList__ = null;
    /** 追加用ユーザ一覧 */
    private List<LabelValueBean> bmk110LeftUserList__ = null;
    /** 追加済みユーザ一覧 */
    private List<LabelValueBean> bmk110RightUserList__ = null;
    /** 追加用ユーザ(選択) */
    private String[] bmk110SelectLeftUser__ = null;
    /** 追加済みユーザ(選択) */
    private String[] bmk110SelectRightUser__ = null;

    /**
     * <p>bmk110GrpEditKbn を取得します。
     * @return bmk110GrpEditKbn
     */
    public int getBmk110GrpEditKbn() {
        return bmk110GrpEditKbn__;
    }
    /**
     * <p>bmk110GrpEditKbn をセットします。
     * @param bmk110GrpEditKbn bmk110GrpEditKbn
     */
    public void setBmk110GrpEditKbn(int bmk110GrpEditKbn) {
        bmk110GrpEditKbn__ = bmk110GrpEditKbn;
    }
    /**
     * <p>bmk110PubEditKbn を取得します。
     * @return bmk110PubEditKbn
     */
    public int getBmk110PubEditKbn() {
        return bmk110PubEditKbn__;
    }
    /**
     * <p>bmk110PubEditKbn をセットします。
     * @param bmk110PubEditKbn bmk110PubEditKbn
     */
    public void setBmk110PubEditKbn(int bmk110PubEditKbn) {
        bmk110PubEditKbn__ = bmk110PubEditKbn;
    }

    /**
     * <p>bmk110GrpSid を取得します。
     * @return bmk110GrpSid
     */
    public String[] getBmk110GrpSid() {
        return bmk110GrpSid__;
    }
    /**
     * <p>bmk110GrpSid をセットします。
     * @param bmk110GrpSid bmk110GrpSid
     */
    public void setBmk110GrpSid(String[] bmk110GrpSid) {
        bmk110GrpSid__ = bmk110GrpSid;
    }
    /**
     * <p>bmk110LeftGroupList を取得します。
     * @return bmk110LeftGroupList
     */
    public List<LabelValueBean> getBmk110LeftGroupList() {
        return bmk110LeftGroupList__;
    }
    /**
     * <p>bmk110LeftGroupList をセットします。
     * @param bmk110LeftGroupList bmk110LeftGroupList
     */
    public void setBmk110LeftGroupList(List<LabelValueBean> bmk110LeftGroupList) {
        bmk110LeftGroupList__ = bmk110LeftGroupList;
    }
    /**
     * <p>bmk110RightGroupList を取得します。
     * @return bmk110RightGroupList
     */
    public List<LabelValueBean> getBmk110RightGroupList() {
        return bmk110RightGroupList__;
    }
    /**
     * <p>bmk110RightGroupList をセットします。
     * @param bmk110RightGroupList bmk110RightGroupList
     */
    public void setBmk110RightGroupList(List<LabelValueBean> bmk110RightGroupList) {
        bmk110RightGroupList__ = bmk110RightGroupList;
    }
    /**
     * <p>bmk110SelectLeftGroup を取得します。
     * @return bmk110SelectLeftGroup
     */
    public String[] getBmk110SelectLeftGroup() {
        return bmk110SelectLeftGroup__;
    }
    /**
     * <p>bmk110SelectLeftGroup をセットします。
     * @param bmk110SelectLeftGroup bmk110SelectLeftGroup
     */
    public void setBmk110SelectLeftGroup(String[] bmk110SelectLeftGroup) {
        bmk110SelectLeftGroup__ = bmk110SelectLeftGroup;
    }
    /**
     * <p>bmk110SelectRightGroup を取得します。
     * @return bmk110SelectRightGroup
     */
    public String[] getBmk110SelectRightGroup() {
        return bmk110SelectRightGroup__;
    }
    /**
     * <p>bmk110SelectRightGroup をセットします。
     * @param bmk110SelectRightGroup bmk110SelectRightGroup
     */
    public void setBmk110SelectRightGroup(String[] bmk110SelectRightGroup) {
        bmk110SelectRightGroup__ = bmk110SelectRightGroup;
    }
    /**
     * <p>bmk110UserSid を取得します。
     * @return bmk110UserSid
     */
    public String[] getBmk110UserSid() {
        return bmk110UserSid__;
    }
    /**
     * <p>bmk110UserSid をセットします。
     * @param bmk110UserSid bmk110UserSid
     */
    public void setBmk110UserSid(String[] bmk110UserSid) {
        bmk110UserSid__ = bmk110UserSid;
    }
    /**
     * <p>bmk110GroupSid を取得します。
     * @return bmk110GroupSid
     */
    public int getBmk110GroupSid() {
        return bmk110GroupSid__;
    }
    /**
     * <p>bmk110GroupSid をセットします。
     * @param bmk110GroupSid bmk110GroupSid
     */
    public void setBmk110GroupSid(int bmk110GroupSid) {
        bmk110GroupSid__ = bmk110GroupSid;
    }
    /**
     * <p>bmk110GroupList を取得します。
     * @return bmk110GroupList
     */
    public List<LabelValueBean> getBmk110GroupList() {
        return bmk110GroupList__;
    }
    /**
     * <p>bmk110GroupList をセットします。
     * @param bmk110GroupList bmk110GroupList
     */
    public void setBmk110GroupList(List<LabelValueBean> bmk110GroupList) {
        bmk110GroupList__ = bmk110GroupList;
    }
    /**
     * <p>bmk110LeftUserList を取得します。
     * @return bmk110LeftUserList
     */
    public List<LabelValueBean> getBmk110LeftUserList() {
        return bmk110LeftUserList__;
    }
    /**
     * <p>bmk110LeftUserList をセットします。
     * @param bmk110LeftUserList bmk110LeftUserList
     */
    public void setBmk110LeftUserList(List<LabelValueBean> bmk110LeftUserList) {
        bmk110LeftUserList__ = bmk110LeftUserList;
    }
    /**
     * <p>bmk110RightUserList を取得します。
     * @return bmk110RightUserList
     */
    public List<LabelValueBean> getBmk110RightUserList() {
        return bmk110RightUserList__;
    }
    /**
     * <p>bmk110RightUserList をセットします。
     * @param bmk110RightUserList bmk110RightUserList
     */
    public void setBmk110RightUserList(List<LabelValueBean> bmk110RightUserList) {
        bmk110RightUserList__ = bmk110RightUserList;
    }
    /**
     * <p>bmk110SelectLeftUser を取得します。
     * @return bmk110SelectLeftUser
     */
    public String[] getBmk110SelectLeftUser() {
        return bmk110SelectLeftUser__;
    }
    /**
     * <p>bmk110SelectLeftUser をセットします。
     * @param bmk110SelectLeftUser bmk110SelectLeftUser
     */
    public void setBmk110SelectLeftUser(String[] bmk110SelectLeftUser) {
        bmk110SelectLeftUser__ = bmk110SelectLeftUser;
    }
    /**
     * <p>bmk110SelectRightUser を取得します。
     * @return bmk110SelectRightUser
     */
    public String[] getBmk110SelectRightUser() {
        return bmk110SelectRightUser__;
    }
    /**
     * <p>bmk110SelectRightUser をセットします。
     * @param bmk110SelectRightUser bmk110SelectRightUser
     */
    public void setBmk110SelectRightUser(String[] bmk110SelectRightUser) {
        bmk110SelectRightUser__ = bmk110SelectRightUser;
    }
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheck(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();

        GsMessage gsMsg = new GsMessage();
        String msg2 = "";

        if (bmk110PubEditKbn__ == GSConstBookmark.EDIT_POW_GROUP) {
            //グループ指定：未選択チェック
            if (bmk110GrpSid__ == null || bmk110GrpSid__.length == 0) {
                msg2 = gsMsg.getMessage(req, "cmn.editgroup");
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, msg2);
                StrutsUtil.addMessage(
                        errors, msg, "bmk110GrpSid." + msgKey);
            }
        } else if (bmk110PubEditKbn__ == GSConstBookmark.EDIT_POW_USER) {
            //ユーザ指定：未選択チェック
            if (bmk110UserSid__ == null || bmk110UserSid__.length == 0) {
                msg2 = gsMsg.getMessage(req, "cmn.edituser");
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, msg2);
                StrutsUtil.addMessage(
                        errors, msg, "bmk110UserSid." + msgKey);
            }
        }
        return errors;
    }
}
