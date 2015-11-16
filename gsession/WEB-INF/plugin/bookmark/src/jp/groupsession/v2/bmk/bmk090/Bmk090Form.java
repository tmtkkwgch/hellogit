package jp.groupsession.v2.bmk.bmk090;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.bmk010.Bmk010Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 権限設定(グループブックマーク)画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk090Form extends Bmk010Form {
    /** グループ別ブックマーク編集区分 */
    private int bmk090GrpEditKbn__ = -1;
    /** グループ名 */
    private String bmk090GrpName__ = null;

    //共有ブックマーク編集区分、グループ指定時
    /** 追加済みグループSID */
    private String[] bmk090GrpSid__ = null;
    /** 追加用グループ一覧 */
    private List<LabelValueBean> bmk090LeftGroupList__ = null;
    /** 追加済みグループ一覧 */
    private List<LabelValueBean> bmk090RightGroupList__ = null;
    /** 追加用グループ(選択) */
    private String[] bmk090SelectLeftGroup__ = null;
    /** 追加済みグループ(選択) */
    private String[] bmk090SelectRightGroup__ = null;

    //共有ブックマーク編集区分、ユーザ指定時
    /** 追加済みユーザSID */
    private String[] bmk090UserSid__ = null;
    /** お知らせ先SID(初期) */
    private String[] bmk090UserSidOld__ = null;
    /** グループSID */
    private int bmk090GroupSid__ = 0;
    /** グループ一覧 */
    private List<LabelValueBean> bmk090GroupList__ = null;
    /** 追加用ユーザ一覧 */
    private List<LabelValueBean> bmk090LeftUserList__ = null;
    /** 追加済みユーザ一覧 */
    private List<LabelValueBean> bmk090RightUserList__ = null;
    /** 追加用ユーザ(選択) */
    private String[] bmk090SelectLeftUser__ = null;
    /** 追加済みユーザ(選択) */
    private String[] bmk090SelectRightUser__ = null;

    /**
     * <p>bmk090GrpEditKbn を取得します。
     * @return bmk090GrpEditKbn
     */
    public int getBmk090GrpEditKbn() {
        return bmk090GrpEditKbn__;
    }
    /**
     * <p>bmk090GrpEditKbn をセットします。
     * @param bmk090GrpEditKbn bmk090GrpEditKbn
     */
    public void setBmk090GrpEditKbn(int bmk090GrpEditKbn) {
        bmk090GrpEditKbn__ = bmk090GrpEditKbn;
    }

    /**
     * <p>bmk090GrpName を取得します。
     * @return bmk090GrpName
     */
    public String getBmk090GrpName() {
        return bmk090GrpName__;
    }
    /**
     * <p>bmk090GrpName をセットします。
     * @param bmk090GrpName bmk090GrpName
     */
    public void setBmk090GrpName(String bmk090GrpName) {
        bmk090GrpName__ = bmk090GrpName;
    }
    /**
     * <p>bmk090GrpSid を取得します。
     * @return bmk090GrpSid
     */
    public String[] getBmk090GrpSid() {
        return bmk090GrpSid__;
    }
    /**
     * <p>bmk090GrpSid をセットします。
     * @param bmk090GrpSid bmk090GrpSid
     */
    public void setBmk090GrpSid(String[] bmk090GrpSid) {
        bmk090GrpSid__ = bmk090GrpSid;
    }
    /**
     * <p>bmk090LeftGroupList を取得します。
     * @return bmk090LeftGroupList
     */
    public List<LabelValueBean> getBmk090LeftGroupList() {
        return bmk090LeftGroupList__;
    }
    /**
     * <p>bmk090LeftGroupList をセットします。
     * @param bmk090LeftGroupList bmk090LeftGroupList
     */
    public void setBmk090LeftGroupList(List<LabelValueBean> bmk090LeftGroupList) {
        bmk090LeftGroupList__ = bmk090LeftGroupList;
    }
    /**
     * <p>bmk090RightGroupList を取得します。
     * @return bmk090RightGroupList
     */
    public List<LabelValueBean> getBmk090RightGroupList() {
        return bmk090RightGroupList__;
    }
    /**
     * <p>bmk090RightGroupList をセットします。
     * @param bmk090RightGroupList bmk090RightGroupList
     */
    public void setBmk090RightGroupList(List<LabelValueBean> bmk090RightGroupList) {
        bmk090RightGroupList__ = bmk090RightGroupList;
    }
    /**
     * <p>bmk090SelectLeftGroup を取得します。
     * @return bmk090SelectLeftGroup
     */
    public String[] getBmk090SelectLeftGroup() {
        return bmk090SelectLeftGroup__;
    }
    /**
     * <p>bmk090SelectLeftGroup をセットします。
     * @param bmk090SelectLeftGroup bmk090SelectLeftGroup
     */
    public void setBmk090SelectLeftGroup(String[] bmk090SelectLeftGroup) {
        bmk090SelectLeftGroup__ = bmk090SelectLeftGroup;
    }
    /**
     * <p>bmk090SelectRightGroup を取得します。
     * @return bmk090SelectRightGroup
     */
    public String[] getBmk090SelectRightGroup() {
        return bmk090SelectRightGroup__;
    }
    /**
     * <p>bmk090SelectRightGroup をセットします。
     * @param bmk090SelectRightGroup bmk090SelectRightGroup
     */
    public void setBmk090SelectRightGroup(String[] bmk090SelectRightGroup) {
        bmk090SelectRightGroup__ = bmk090SelectRightGroup;
    }
    /**
     * <p>bmk090UserSid を取得します。
     * @return bmk090UserSid
     */
    public String[] getBmk090UserSid() {
        return bmk090UserSid__;
    }
    /**
     * <p>bmk090UserSid をセットします。
     * @param bmk090UserSid bmk090UserSid
     */
    public void setBmk090UserSid(String[] bmk090UserSid) {
        bmk090UserSid__ = bmk090UserSid;
    }
    /**
     * <p>bmk090UserSidOld を取得します。
     * @return bmk090UserSidOld
     */
    public String[] getBmk090UserSidOld() {
        return bmk090UserSidOld__;
    }
    /**
     * <p>bmk090UserSidOld をセットします。
     * @param bmk090UserSidOld bmk090UserSidOld
     */
    public void setBmk090UserSidOld(String[] bmk090UserSidOld) {
        bmk090UserSidOld__ = bmk090UserSidOld;
    }
    /**
     * <p>bmk090GroupSid を取得します。
     * @return bmk090GroupSid
     */
    public int getBmk090GroupSid() {
        return bmk090GroupSid__;
    }
    /**
     * <p>bmk090GroupSid をセットします。
     * @param bmk090GroupSid bmk090GroupSid
     */
    public void setBmk090GroupSid(int bmk090GroupSid) {
        bmk090GroupSid__ = bmk090GroupSid;
    }
    /**
     * <p>bmk090GroupList を取得します。
     * @return bmk090GroupList
     */
    public List<LabelValueBean> getBmk090GroupList() {
        return bmk090GroupList__;
    }
    /**
     * <p>bmk090GroupList をセットします。
     * @param bmk090GroupList bmk090GroupList
     */
    public void setBmk090GroupList(List<LabelValueBean> bmk090GroupList) {
        bmk090GroupList__ = bmk090GroupList;
    }
    /**
     * <p>bmk090LeftUserList を取得します。
     * @return bmk090LeftUserList
     */
    public List<LabelValueBean> getBmk090LeftUserList() {
        return bmk090LeftUserList__;
    }
    /**
     * <p>bmk090LeftUserList をセットします。
     * @param bmk090LeftUserList bmk090LeftUserList
     */
    public void setBmk090LeftUserList(List<LabelValueBean> bmk090LeftUserList) {
        bmk090LeftUserList__ = bmk090LeftUserList;
    }
    /**
     * <p>bmk090RightUserList を取得します。
     * @return bmk090RightUserList
     */
    public List<LabelValueBean> getBmk090RightUserList() {
        return bmk090RightUserList__;
    }
    /**
     * <p>bmk090RightUserList をセットします。
     * @param bmk090RightUserList bmk090RightUserList
     */
    public void setBmk090RightUserList(List<LabelValueBean> bmk090RightUserList) {
        bmk090RightUserList__ = bmk090RightUserList;
    }
    /**
     * <p>bmk090SelectLeftUser を取得します。
     * @return bmk090SelectLeftUser
     */
    public String[] getBmk090SelectLeftUser() {
        return bmk090SelectLeftUser__;
    }
    /**
     * <p>bmk090SelectLeftUser をセットします。
     * @param bmk090SelectLeftUser bmk090SelectLeftUser
     */
    public void setBmk090SelectLeftUser(String[] bmk090SelectLeftUser) {
        bmk090SelectLeftUser__ = bmk090SelectLeftUser;
    }
    /**
     * <p>bmk090SelectRightUser を取得します。
     * @return bmk090SelectRightUser
     */
    public String[] getBmk090SelectRightUser() {
        return bmk090SelectRightUser__;
    }
    /**
     * <p>bmk090SelectRightUser をセットします。
     * @param bmk090SelectRightUser bmk090SelectRightUser
     */
    public void setBmk090SelectRightUser(String[] bmk090SelectRightUser) {
        bmk090SelectRightUser__ = bmk090SelectRightUser;
    }
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param req リクエスト
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(Connection con, HttpServletRequest req)
    throws SQLException {
        ActionErrors errors = new ActionErrors();

        GsMessage gsMsg = new GsMessage();
        String msg2 = "";

        if (bmk090GrpEditKbn__ == GSConstBookmark.EDIT_POW_GROUP) {
            msg2 = gsMsg.getMessage(req, "cmn.editgroup");;
            //グループ指定：未選択チェック
            if (bmk090GrpSid__ == null || bmk090GrpSid__.length == 0) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, msg2);
                StrutsUtil.addMessage(
                        errors, msg, "bmk090GrpSid." + msgKey);
            }
        } else if (bmk090GrpEditKbn__ == GSConstBookmark.EDIT_POW_USER) {
            msg2 = gsMsg.getMessage(req, "cmn.edituser");;
            //ユーザ指定：未選択チェック
            if (bmk090UserSid__ == null || bmk090UserSid__.length == 0) {
                String msgKey = "error.select.required.text";
                ActionMessage msg = new ActionMessage(msgKey, msg2);
                StrutsUtil.addMessage(
                        errors, msg, "bmk090UserSid." + msgKey);
            }
        }
        return errors;
    }
}
