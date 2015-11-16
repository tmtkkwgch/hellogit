package jp.groupsession.v2.ptl.ptl050;

import java.util.List;

import jp.groupsession.v2.man.GSConstPortal;
import jp.groupsession.v2.ptl.ptl030.Ptl030ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ポータル ポータル登録画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl050ParamModel extends Ptl030ParamModel {
    /** 初期表示フラグ */
    private int ptl050init__ = 0;
    /** ポータル名 */
    private String ptl050name__ = null;
    /** 公開区分 */
    private int ptl050openKbn__ = GSConstPortal.PTL_OPENKBN_OK;
    /** 説明 */
    private String ptl050description__ = null;

    /** アクセス権限 */
    private int ptl050accessKbn__ = GSConstPortal.PTL_ACCESS_OFF;

    /** グループSID */
    private int ptl050accessKbnGroup__ = Integer.parseInt(GSConstPortal.GROUP_COMBO_VALUE);
    /** メンバーSID */
    private String[] ptl050memberSid__ = new String[0];

    /** グループ一覧 */
    private List < LabelValueBean > ptl050GroupList__ = null;
    /** 追加済みユーザ一覧 */
    private List < LabelValueBean > ptl050LeftUserList__ = null;
    /** 追加用ユーザ一覧 */
    private List < LabelValueBean > ptl050RightUserList__ = null;
    /** 追加済みメンバー(選択) */
    private String[] ptl050accessKbnRead__ = new String[0];
    /** 追加用メンバー(選択) */
    private String[] ptl050SelectRightUser__ = new String[0];
    /**
     * <p>ptl050init を取得します。
     * @return ptl050init
     */
    public int getPtl050init() {
        return ptl050init__;
    }
    /**
     * <p>ptl050init をセットします。
     * @param ptl050init ptl050init
     */
    public void setPtl050init(int ptl050init) {
        ptl050init__ = ptl050init;
    }
    /**
     * <p>ptl050name を取得します。
     * @return ptl050name
     */
    public String getPtl050name() {
        return ptl050name__;
    }
    /**
     * <p>ptl050name をセットします。
     * @param ptl050name ptl050name
     */
    public void setPtl050name(String ptl050name) {
        ptl050name__ = ptl050name;
    }
    /**
     * <p>ptl050openKbn を取得します。
     * @return ptl050openKbn
     */
    public int getPtl050openKbn() {
        return ptl050openKbn__;
    }
    /**
     * <p>ptl050openKbn をセットします。
     * @param ptl050openKbn ptl050openKbn
     */
    public void setPtl050openKbn(int ptl050openKbn) {
        ptl050openKbn__ = ptl050openKbn;
    }
    /**
     * <p>ptl050description を取得します。
     * @return ptl050description
     */
    public String getPtl050description() {
        return ptl050description__;
    }
    /**
     * <p>ptl050description をセットします。
     * @param ptl050description ptl050description
     */
    public void setPtl050description(String ptl050description) {
        ptl050description__ = ptl050description;
    }
    /**
     * <p>ptl050accessKbn を取得します。
     * @return ptl050accessKbn
     */
    public int getPtl050accessKbn() {
        return ptl050accessKbn__;
    }
    /**
     * <p>ptl050accessKbn をセットします。
     * @param ptl050accessKbn ptl050accessKbn
     */
    public void setPtl050accessKbn(int ptl050accessKbn) {
        ptl050accessKbn__ = ptl050accessKbn;
    }
    /**
     * <p>ptl050accessKbnGroup を取得します。
     * @return ptl050accessKbnGroup
     */
    public int getPtl050accessKbnGroup() {
        return ptl050accessKbnGroup__;
    }
    /**
     * <p>ptl050accessKbnGroup をセットします。
     * @param ptl050accessKbnGroup ptl050accessKbnGroup
     */
    public void setPtl050accessKbnGroup(int ptl050accessKbnGroup) {
        ptl050accessKbnGroup__ = ptl050accessKbnGroup;
    }
    /**
     * <p>ptl050memberSid を取得します。
     * @return ptl050memberSid
     */
    public String[] getPtl050memberSid() {
        return ptl050memberSid__;
    }
    /**
     * <p>ptl050memberSid をセットします。
     * @param ptl050memberSid ptl050memberSid
     */
    public void setPtl050memberSid(String[] ptl050memberSid) {
        ptl050memberSid__ = ptl050memberSid;
    }
    /**
     * <p>ptl050GroupList を取得します。
     * @return ptl050GroupList
     */
    public List<LabelValueBean> getPtl050GroupList() {
        return ptl050GroupList__;
    }
    /**
     * <p>ptl050GroupList をセットします。
     * @param ptl050GroupList ptl050GroupList
     */
    public void setPtl050GroupList(List<LabelValueBean> ptl050GroupList) {
        ptl050GroupList__ = ptl050GroupList;
    }
    /**
     * <p>ptl050LeftUserList を取得します。
     * @return ptl050LeftUserList
     */
    public List<LabelValueBean> getPtl050LeftUserList() {
        return ptl050LeftUserList__;
    }
    /**
     * <p>ptl050LeftUserList をセットします。
     * @param ptl050LeftUserList ptl050LeftUserList
     */
    public void setPtl050LeftUserList(List<LabelValueBean> ptl050LeftUserList) {
        ptl050LeftUserList__ = ptl050LeftUserList;
    }
    /**
     * <p>ptl050RightUserList を取得します。
     * @return ptl050RightUserList
     */
    public List<LabelValueBean> getPtl050RightUserList() {
        return ptl050RightUserList__;
    }
    /**
     * <p>ptl050RightUserList をセットします。
     * @param ptl050RightUserList ptl050RightUserList
     */
    public void setPtl050RightUserList(List<LabelValueBean> ptl050RightUserList) {
        ptl050RightUserList__ = ptl050RightUserList;
    }
    /**
     * <p>ptl050accessKbnRead を取得します。
     * @return ptl050accessKbnRead
     */
    public String[] getPtl050accessKbnRead() {
        return ptl050accessKbnRead__;
    }
    /**
     * <p>ptl050accessKbnRead をセットします。
     * @param ptl050accessKbnRead ptl050accessKbnRead
     */
    public void setPtl050accessKbnRead(String[] ptl050accessKbnRead) {
        ptl050accessKbnRead__ = ptl050accessKbnRead;
    }
    /**
     * <p>ptl050SelectRightUser を取得します。
     * @return ptl050SelectRightUser
     */
    public String[] getPtl050SelectRightUser() {
        return ptl050SelectRightUser__;
    }
    /**
     * <p>ptl050SelectRightUser をセットします。
     * @param ptl050SelectRightUser ptl050SelectRightUser
     */
    public void setPtl050SelectRightUser(String[] ptl050SelectRightUser) {
        ptl050SelectRightUser__ = ptl050SelectRightUser;
    }
}