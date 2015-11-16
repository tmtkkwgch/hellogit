package jp.groupsession.v2.cmn.cmn230;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.cmn220.Cmn220UsrDspModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.model.CmnLabelValueModel;
import jp.groupsession.v2.struts.AbstractGsForm;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 全グループから選択ポップアップのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn230Form extends AbstractGsForm {

    /** グループリスト */
    ArrayList<GroupModel> groupList__ = null;
    /** マイグループリスト */
    ArrayList<CmnLabelValueModel> myGroupList__ = null;
    /** 親画面ID */
    String parentDspID__ = null;
    /** 親画面セレクトボックス名 */
    String selBoxName__ = null;
    /** 親画面DOM名(上) */
    String svDomName__ = null;
    /** 親画面DOM名(下) */
    String svDomName2__ = null;
    /** 親プラグインID */
    String plginId__ = null;
    /** 親画面のコマンド */
    String submitCmd__ = null;
    /** 親画面の選択グループ */
    String selGroup__ = null;
    /** 親画面の選択グループ (保存用)*/
    String selGroupSv__ = null;
    /** グループタブ表示フラグ */
    int selGrpFlg__ = 0;
    /** システム管理グループ表示フラグ */
    int admGpFlg__ = 0;
    /** マイグループ表示フラグ */
    int myGroupFlg__ = 0;
    /** 文字置換えフラグ */
    int splitFlg__ = 0;
    /** コマンド区分 */
    int cmdKbn__ = 0;
    /** 選択ユーザ */
    String sv_users__ = null;
    /** 選択ユーザ */
    String sv_users2__ = null;
    /** 選択ユーザ(左) */
    String selUserSid__ = null;
    /** 選択ユーザ(右) */
    String selUserSid2__ = null;
    /** 移動ユーザ */
    String moveUsers__ = null;
    /** 移動ユーザ */
    String[] moveUserSid__ = null;
    /** グループSID */
    private String cmn230groupSid__ = null;
    /** グループ一覧 */
    private List<LabelValueBean> cmn230GroupList__ = null;
    /** 追加用ユーザ一覧 */
    private List<Cmn220UsrDspModel> cmn230TopUserList__ = null;
    /** 追加済みユーザ一覧 */
    private List<Cmn220UsrDspModel> cmn230BottomUserList__ = null;
    /** 追加済みユーザ一覧 */
    private List<Cmn220UsrDspModel> cmn230BottomUserList2__ = null;
    /** 追加済みユーザSID */
    private String[] cmn230userSid__ = null;
    /** 追加済みユーザSID */
    private String[] cmn230userSid2__ = null;
    /** 追加ユーザSID(上) */
    private String[] cmn230userSidTop__ = null;
    /** 追加ユーザSID(下) */
    private String[] cmn230userSidBottom__ = null;
    /** 追加ユーザSID(下) */
    private String[] cmn230userSidBottom2__ = null;
    /** 追加用ユーザ(選択) */
    private String[] cmn230SelectLeftUser__ = null;
    /** 追加用ユーザ(選択) */
    private String[] cmn230SelectLeftUser2__ = null;
    /** 表示グループ名 */
    private String cmn230DspGrpName__ = null;
    /** 追加先(左)*/
    private String cmn230LeftTitleName__ = null;
    /** 追加先(右)*/
    private String cmn230RightTitleName__ = null;
    /** 検索フラグ */
    private int cmn230SearchFlg__ = 0;
    /** 検索文字列 */
    private String cmn230SearchStr__ = null;
    /** ソートフラグ  0:社員/職員番号 1:ユーザ名*/
    private int cmn230SortTopKey__ = 0;
    /** ソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn230SortTopKbn__ = 1;
    /** ソートフラグ  0:社員/職員番号 1:ユーザ名*/
    private int cmn230SortBottomKey__ = 1;
    /** ソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn230SortBottomKbn__ = 1;
    /** ソートフラグ  0:社員/職員番号 1:ユーザ名*/
    private int cmn230SortBottomKey2__ = 1;
    /** ソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn230SortBottomKbn2__ = 1;
    /** ソートフラグ  0:グループID 1:グループ名*/
    private int cmn230SortTopKeyGp__ = 0;
    /** ソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn230SortTopKbnGp__ = 1;
    /** ソートフラグ  0:社員/職員番号 1:ユーザ名*/
    private int cmn230SortBottomKeyGp__ = 1;
    /** ソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn230SortBottomKbnGp__ = 1;
    /** ソートフラグ  0:グループID 1:グループ名*/
    private int cmn230SortBottomKeyGp2__ = 1;
    /** デフォルトグループSID(保存用) */
    private String cmn230dfGpSidStrSv__ = null;
    /** デフォルトグループSID(保存用) */
    private String cmn230dspGpSidSv__;
    /** タブフラグ 0:タブ非表示  1:タブ非表*/
    private int cmn230TabFlg__ = 0;
    /** タブモード 0:ユーザ  1:グループ*/
    private int cmn230TabMode__ = 0;
    /** 追加済みグループSID(左) */
    private String[] cmn230groupSidadd__ = null;
    /** 追加済みグループSID（右） */
    private String[] cmn230groupSidadd2__ = null;
    /** 追加グループSID(上) */
    private String[] cmn230groupSidTop__ = null;
    /** 追加グループSID(下) */
    private String[] cmn230groupSidBottom__ = null;
    /** 追加グループSID(下) */
    private String[] cmn230groupSidBottom2__ = null;
    /** 追加用グループ一覧 */
    private List<GroupModel> cmn230TopGroupList__ = null;
    /** 追加済みグループ一覧 */
    private List<GroupModel> cmn230BottomGroupList__ = null;
    /** 追加済みグループ一覧 */
    private List<GroupModel> cmn230BottomGroupList2__ = null;
    /** 移動グループ */
    String moveGroups__ = null;
    /** 移動グループ */
    String[] moveGroupSid__ = null;
    /**
     * <p>moveGroups を取得します。
     * @return moveGroups
     */
    public String getMoveGroups() {
        return moveGroups__;
    }
    /**
     * <p>moveGroups をセットします。
     * @param moveGroups moveGroups
     */
    public void setMoveGroups(String moveGroups) {
        moveGroups__ = moveGroups;
    }
    /**
     * <p>moveGroupSid を取得します。
     * @return moveGroupSid
     */
    public String[] getMoveGroupSid() {
        return moveGroupSid__;
    }
    /**
     * <p>moveGroupSid をセットします。
     * @param moveGroupSid moveGroupSid
     */
    public void setMoveGroupSid(String[] moveGroupSid) {
        moveGroupSid__ = moveGroupSid;
    }
    /**
     * <p>cmn230dfGpSidStrSv を取得します。
     * @return cmn230dfGpSidStrSv
     */
    public String getCmn230dfGpSidStrSv() {
        return cmn230dfGpSidStrSv__;
    }
    /**
     * <p>cmn230dfGpSidStrSv をセットします。
     * @param cmn230dfGpSidStrSv cmn230dfGpSidStrSv
     */
    public void setCmn230dfGpSidStrSv(String cmn230dfGpSidStrSv) {
        cmn230dfGpSidStrSv__ = cmn230dfGpSidStrSv;
    }
    /**
     * <p>cmn230dspGpSidSv を取得します。
     * @return cmn230dspGpSidSv
     */
    public String getCmn230dspGpSidSv() {
        return cmn230dspGpSidSv__;
    }
    /**
     * <p>cmn230dspGpSidSv をセットします。
     * @param cmn230dspGpSidSv cmn230dspGpSidSv
     */
    public void setCmn230dspGpSidSv(String cmn230dspGpSidSv) {
        cmn230dspGpSidSv__ = cmn230dspGpSidSv;
    }
    /**
     * <p>cmn230TabFlg を取得します。
     * @return cmn230TabFlg
     */
    public int getCmn230TabFlg() {
        return cmn230TabFlg__;
    }
    /**
     * <p>cmn230TabFlg をセットします。
     * @param cmn230TabFlg cmn230TabFlg
     */
    public void setCmn230TabFlg(int cmn230TabFlg) {
        cmn230TabFlg__ = cmn230TabFlg;
    }
    /**
     * <p>cmn230TabMode を取得します。
     * @return cmn230TabMode
     */
    public int getCmn230TabMode() {
        return cmn230TabMode__;
    }
    /**
     * <p>cmn230TabMode をセットします。
     * @param cmn230TabMode cmn230TabMode
     */
    public void setCmn230TabMode(int cmn230TabMode) {
        cmn230TabMode__ = cmn230TabMode;
    }
    /**
     * <p>cmn230groupSidadd を取得します。
     * @return cmn230groupSidadd
     */
    public String[] getCmn230groupSidadd() {
        return cmn230groupSidadd__;
    }
    /**
     * <p>cmn230groupSidadd をセットします。
     * @param cmn230groupSidadd cmn230groupSidadd
     */
    public void setCmn230groupSidadd(String[] cmn230groupSidadd) {
        cmn230groupSidadd__ = cmn230groupSidadd;
    }
    /**
     * <p>cmn230groupSidadd2 を取得します。
     * @return cmn230groupSidadd2
     */
    public String[] getCmn230groupSidadd2() {
        return cmn230groupSidadd2__;
    }
    /**
     * <p>cmn230groupSidadd2 をセットします。
     * @param cmn230groupSidadd2 cmn230groupSidadd2
     */
    public void setCmn230groupSidadd2(String[] cmn230groupSidadd2) {
        cmn230groupSidadd2__ = cmn230groupSidadd2;
    }
    /**
     * <p>sv_users2 を取得します。
     * @return sv_users2
     */
    public String getSv_users2() {
        return sv_users2__;
    }
    /**
     * <p>sv_users2 をセットします。
     * @param svUsers2 sv_users2
     */
    public void setSv_users2(String svUsers2) {
        sv_users2__ = svUsers2;
    }
    /**
     * <p>cmn230BottomUserList2 を取得します。
     * @return cmn230BottomUserList2
     */
    public List<Cmn220UsrDspModel> getCmn230BottomUserList2() {
        return cmn230BottomUserList2__;
    }
    /**
     * <p>cmn230BottomUserList2 をセットします。
     * @param cmn230BottomUserList2 cmn230BottomUserList2
     */
    public void setCmn230BottomUserList2(List<Cmn220UsrDspModel> cmn230BottomUserList2) {
        cmn230BottomUserList2__ = cmn230BottomUserList2;
    }
    /**
     * <p>cmn230userSid2 を取得します。
     * @return cmn230userSid2
     */
    public String[] getCmn230userSid2() {
        return cmn230userSid2__;
    }
    /**
     * <p>cmn230userSid2 をセットします。
     * @param cmn230userSid2 cmn230userSid2
     */
    public void setCmn230userSid2(String[] cmn230userSid2) {
        cmn230userSid2__ = cmn230userSid2;
    }
    /**
     * <p>cmn230userSidBottom2 を取得します。
     * @return cmn230userSidBottom2
     */
    public String[] getCmn230userSidBottom2() {
        return cmn230userSidBottom2__;
    }
    /**
     * <p>cmn230userSidBottom2 をセットします。
     * @param cmn230userSidBottom2 cmn230userSidBottom2
     */
    public void setCmn230userSidBottom2(String[] cmn230userSidBottom2) {
        cmn230userSidBottom2__ = cmn230userSidBottom2;
    }
    /**
     * <p>cmn230SelectLeftUser2 を取得します。
     * @return cmn230SelectLeftUser2
     */
    public String[] getCmn230SelectLeftUser2() {
        return cmn230SelectLeftUser2__;
    }
    /**
     * <p>cmn230SelectLeftUser2 をセットします。
     * @param cmn230SelectLeftUser2 cmn230SelectLeftUser2
     */
    public void setCmn230SelectLeftUser2(String[] cmn230SelectLeftUser2) {
        cmn230SelectLeftUser2__ = cmn230SelectLeftUser2;
    }
    /**
     * <p>cmn230SortBottomKey2 を取得します。
     * @return cmn230SortBottomKey2
     */
    public int getCmn230SortBottomKey2() {
        return cmn230SortBottomKey2__;
    }
    /**
     * <p>cmn230SortBottomKey2 をセットします。
     * @param cmn230SortBottomKey2 cmn230SortBottomKey2
     */
    public void setCmn230SortBottomKey2(int cmn230SortBottomKey2) {
        cmn230SortBottomKey2__ = cmn230SortBottomKey2;
    }
    /**
     * <p>cmn230SortBottomKbn2 を取得します。
     * @return cmn230SortBottomKbn2
     */
    public int getCmn230SortBottomKbn2() {
        return cmn230SortBottomKbn2__;
    }
    /**
     * <p>cmn230SortBottomKbn2 をセットします。
     * @param cmn230SortBottomKbn2 cmn230SortBottomKbn2
     */
    public void setCmn230SortBottomKbn2(int cmn230SortBottomKbn2) {
        cmn230SortBottomKbn2__ = cmn230SortBottomKbn2;
    }
    /**
     * <p>cmn230SortTopKey を取得します。
     * @return cmn230SortTopKey
     */
    public int getCmn230SortTopKey() {
        return cmn230SortTopKey__;
    }
    /**
     * <p>cmn230SortTopKey をセットします。
     * @param cmn230SortTopKey cmn230SortTopKey
     */
    public void setCmn230SortTopKey(int cmn230SortTopKey) {
        cmn230SortTopKey__ = cmn230SortTopKey;
    }
    /**
     * <p>cmn230SortTopKbn を取得します。
     * @return cmn230SortTopKbn
     */
    public int getCmn230SortTopKbn() {
        return cmn230SortTopKbn__;
    }
    /**
     * <p>cmn230SortTopKbn をセットします。
     * @param cmn230SortTopKbn cmn230SortTopKbn
     */
    public void setCmn230SortTopKbn(int cmn230SortTopKbn) {
        cmn230SortTopKbn__ = cmn230SortTopKbn;
    }
    /**
     * <p>cmn230SortBottomKey を取得します。
     * @return cmn230SortBottomKey
     */
    public int getCmn230SortBottomKey() {
        return cmn230SortBottomKey__;
    }
    /**
     * <p>cmn230SortBottomKey をセットします。
     * @param cmn230SortBottomKey cmn230SortBottomKey
     */
    public void setCmn230SortBottomKey(int cmn230SortBottomKey) {
        cmn230SortBottomKey__ = cmn230SortBottomKey;
    }
    /**
     * <p>cmn230SortBottomKbn を取得します。
     * @return cmn230SortBottomKbn
     */
    public int getCmn230SortBottomKbn() {
        return cmn230SortBottomKbn__;
    }
    /**
     * <p>cmn230SortBottomKbn をセットします。
     * @param cmn230SortBottomKbn cmn230SortBottomKbn
     */
    public void setCmn230SortBottomKbn(int cmn230SortBottomKbn) {
        cmn230SortBottomKbn__ = cmn230SortBottomKbn;
    }
    /**
     * <p>cmn230SearchStr を取得します。
     * @return cmn230SearchStr
     */
    public String getCmn230SearchStr() {
        return cmn230SearchStr__;
    }
    /**
     * <p>cmn230SearchStr をセットします。
     * @param cmn230SearchStr cmn230SearchStr
     */
    public void setCmn230SearchStr(String cmn230SearchStr) {
        cmn230SearchStr__ = cmn230SearchStr;
    }
    /**
     * <p>cmn230SearchFlg を取得します。
     * @return cmn230SearchFlg
     */
    public int getCmn230SearchFlg() {
        return cmn230SearchFlg__;
    }
    /**
     * <p>cmn230SearchFlg をセットします。
     * @param cmn230SearchFlg cmn230SearchFlg
     */
    public void setCmn230SearchFlg(int cmn230SearchFlg) {
        cmn230SearchFlg__ = cmn230SearchFlg;
    }
    /**
     * <p>cmn230DspGrpName を取得します。
     * @return cmn230DspGrpName
     */
    public String getCmn230DspGrpName() {
        return cmn230DspGrpName__;
    }
    /**
     * <p>cmn230DspGrpName をセットします。
     * @param cmn230DspGrpName cmn230DspGrpName
     */
    public void setCmn230DspGrpName(String cmn230DspGrpName) {
        cmn230DspGrpName__ = cmn230DspGrpName;
    }
    /**
     * <p>cmn230GroupList を取得します。
     * @return cmn230GroupList
     */
    public List<LabelValueBean> getCmn230GroupList() {
        return cmn230GroupList__;
    }
    /**
     * <p>cmn230GroupList をセットします。
     * @param cmn230GroupList cmn230GroupList
     */
    public void setCmn230GroupList(List<LabelValueBean> cmn230GroupList) {
        cmn230GroupList__ = cmn230GroupList;
    }
    /**
     * <p>cmn230TopUserList を取得します。
     * @return cmn230TopUserList
     */
    public List<Cmn220UsrDspModel> getCmn230TopUserList() {
        return cmn230TopUserList__;
    }
    /**
     * <p>cmn230TopUserList をセットします。
     * @param cmn230TopUserList cmn230TopUserList
     */
    public void setCmn230TopUserList(List<Cmn220UsrDspModel> cmn230TopUserList) {
        cmn230TopUserList__ = cmn230TopUserList;
    }
    /**
     * <p>cmn230BottomUserList を取得します。
     * @return cmn230BottomUserList
     */
    public List<Cmn220UsrDspModel> getCmn230BottomUserList() {
        return cmn230BottomUserList__;
    }
    /**
     * <p>cmn230BottomUserList をセットします。
     * @param cmn230BottomUserList cmn230BottomUserList
     */
    public void setCmn230BottomUserList(List<Cmn220UsrDspModel> cmn230BottomUserList) {
        cmn230BottomUserList__ = cmn230BottomUserList;
    }
    /**
     * <p>cmn230userSid を取得します。
     * @return cmn230userSid
     */
    public String[] getCmn230userSid() {
        return cmn230userSid__;
    }
    /**
     * <p>cmn230userSid をセットします。
     * @param cmn230userSid cmn230userSid
     */
    public void setCmn230userSid(String[] cmn230userSid) {
        cmn230userSid__ = cmn230userSid;
    }
    /**
     * <p>cmn230userSidTop を取得します。
     * @return cmn230userSidTop
     */
    public String[] getCmn230userSidTop() {
        return cmn230userSidTop__;
    }
    /**
     * <p>cmn230userSidTop をセットします。
     * @param cmn230userSidTop cmn230userSidTop
     */
    public void setCmn230userSidTop(String[] cmn230userSidTop) {
        cmn230userSidTop__ = cmn230userSidTop;
    }
    /**
     * <p>cmn230userSidBottom を取得します。
     * @return cmn230userSidBottom
     */
    public String[] getCmn230userSidBottom() {
        return cmn230userSidBottom__;
    }
    /**
     * <p>cmn230userSidBottom をセットします。
     * @param cmn230userSidBottom cmn230userSidBottom
     */
    public void setCmn230userSidBottom(String[] cmn230userSidBottom) {
        cmn230userSidBottom__ = cmn230userSidBottom;
    }
    /**
     * <p>cmn230SelectLeftUser を取得します。
     * @return cmn230SelectLeftUser
     */
    public String[] getCmn230SelectLeftUser() {
        return cmn230SelectLeftUser__;
    }
    /**
     * <p>cmn230SelectLeftUser をセットします。
     * @param cmn230SelectLeftUser cmn230SelectLeftUser
     */
    public void setCmn230SelectLeftUser(String[] cmn230SelectLeftUser) {
        cmn230SelectLeftUser__ = cmn230SelectLeftUser;
    }
    /**
     * <p>cmn230SelectRightUser を取得します。
     * @return cmn230SelectRightUser
     */
    public String[] getCmn230SelectRightUser() {
        return cmn230SelectRightUser__;
    }
    /**
     * <p>cmn230SelectRightUser をセットします。
     * @param cmn230SelectRightUser cmn230SelectRightUser
     */
    public void setCmn230SelectRightUser(String[] cmn230SelectRightUser) {
        cmn230SelectRightUser__ = cmn230SelectRightUser;
    }
    /** 追加済みユーザ(選択) */
    private String[] cmn230SelectRightUser__ = null;
    /**
     * <p>cmn230groupSid を取得します。
     * @return cmn230groupSid
     */
    public String getCmn230groupSid() {
        return cmn230groupSid__;
    }
    /**
     * <p>cmn230groupSid をセットします。
     * @param cmn230groupSid cmn230groupSid
     */
    public void setCmn230groupSid(String cmn230groupSid) {
        cmn230groupSid__ = cmn230groupSid;
    }
    /**
     * <p>submitCmd を取得します。
     * @return submitCmd
     */
    public String getSubmitCmd() {
        return submitCmd__;
    }
    /**
     * <p>submitCmd をセットします。
     * @param submitCmd submitCmd
     */
    public void setSubmitCmd(String submitCmd) {
        submitCmd__ = submitCmd;
    }
    /**
     * <p>myGroupList を取得します。
     * @return myGroupList
     */
    public ArrayList<CmnLabelValueModel> getMyGroupList() {
        return myGroupList__;
    }
    /**
     * <p>myGroupList をセットします。
     * @param myGroupList myGroupList
     */
    public void setMyGroupList(ArrayList<CmnLabelValueModel> myGroupList) {
        myGroupList__ = myGroupList;
    }
    /**
     * <p>myGroupFlg を取得します。
     * @return myGroupFlg
     */
    public int getMyGroupFlg() {
        return myGroupFlg__;
    }
    /**
     * <p>myGroupFlg をセットします。
     * @param myGroupFlg myGroupFlg
     */
    public void setMyGroupFlg(int myGroupFlg) {
        myGroupFlg__ = myGroupFlg;
    }
    /**
     * <p>parentDspID を取得します。
     * @return parentDspID
     */
    public String getParentDspID() {
        return parentDspID__;
    }
    /**
     * <p>parentDspID をセットします。
     * @param parentDspID parentDspID
     */
    public void setParentDspID(String parentDspID) {
        parentDspID__ = parentDspID;
    }
    /**
     * <p>selBoxName を取得します。
     * @return selBoxName
     */
    public String getSelBoxName() {
        return selBoxName__;
    }
    /**
     * <p>selBoxName をセットします。
     * @param selBoxName selBoxName
     */
    public void setSelBoxName(String selBoxName) {
        selBoxName__ = selBoxName;
    }
    /**
     * <p>groupList を取得します。
     * @return groupList
     */
    public ArrayList<GroupModel> getGroupList() {
        return groupList__;
    }
    /**
     * <p>groupList をセットします。
     * @param groupList groupList
     */
    public void setGroupList(ArrayList<GroupModel> groupList) {
        groupList__ = groupList;
    }
    /**
     * <p>sv_users を取得します。
     * @return sv_users
     */
    public String getSv_users() {
        return sv_users__;
    }
    /**
     * <p>sv_users をセットします。
     * @param svUsers sv_users
     */
    public void setSv_users(String svUsers) {
        sv_users__ = svUsers;
    }
    /**
     * <p>selUserSid を取得します。
     * @return selUserSid
     */
    public String getSelUserSid() {
        return selUserSid__;
    }
    /**
     * <p>selUserSid をセットします。
     * @param selUserSid selUserSid
     */
    public void setSelUserSid(String selUserSid) {
        selUserSid__ = selUserSid;
    }
    /**
     * <p>moveUserSid を取得します。
     * @return moveUserSid
     */
    public String[] getMoveUserSid() {
        return moveUserSid__;
    }
    /**
     * <p>moveUserSid をセットします。
     * @param moveUserSid moveUserSid
     */
    public void setMoveUserSid(String[] moveUserSid) {
        moveUserSid__ = moveUserSid;
    }
    /**
     * <p>svDomName を取得します。
     * @return svDomName
     */
    public String getSvDomName() {
        return svDomName__;
    }
    /**
     * <p>svDomName をセットします。
     * @param svDomName svDomName
     */
    public void setSvDomName(String svDomName) {
        svDomName__ = svDomName;
    }
    /**
     * <p>selGroup を取得します。
     * @return selGroup
     */
    public String getSelGroup() {
        return selGroup__;
    }
    /**
     * <p>selGroup をセットします。
     * @param selGroup selGroup
     */
    public void setSelGroup(String selGroup) {
        selGroup__ = selGroup;
    }
    /**
     * <p>moveUsers を取得します。
     * @return moveUsers
     */
    public String getMoveUsers() {
        return moveUsers__;
    }
    /**
     * <p>moveUsers をセットします。
     * @param moveUsers moveUsers
     */
    public void setMoveUsers(String moveUsers) {
        moveUsers__ = moveUsers;
    }
    /**
     * <p>selGroupSv を取得します。
     * @return selGroupSv
     */
    public String getSelGroupSv() {
        return selGroupSv__;
    }
    /**
     * <p>selGroupSv をセットします。
     * @param selGroupSv selGroupSv
     */
    public void setSelGroupSv(String selGroupSv) {
        selGroupSv__ = selGroupSv;
    }
    /**
     * <p>admGpFlg を取得します。
     * @return admGpFlg
     */
    public int getAdmGpFlg() {
        return admGpFlg__;
    }
    /**
     * <p>admGpFlg をセットします。
     * @param admGpFlg admGpFlg
     */
    public void setAdmGpFlg(int admGpFlg) {
        admGpFlg__ = admGpFlg;
    }
    /**
     * <p>splitFlg を取得します。
     * @return splitFlg
     */
    public int getSplitFlg() {
        return splitFlg__;
    }
    /**
     * <p>splitFlg をセットします。
     * @param splitFlg splitFlg
     */
    public void setSplitFlg(int splitFlg) {
        splitFlg__ = splitFlg;
    }
    /**
     * <p>cmdKbn を取得します。
     * @return cmdKbn
     */
    public int getCmdKbn() {
        return cmdKbn__;
    }
    /**
     * <p>cmdKbn をセットします。
     * @param cmdKbn cmdKbn
     */
    public void setCmdKbn(int cmdKbn) {
        cmdKbn__ = cmdKbn;
    }
    /**
     * <p>selUserSid2 を取得します。
     * @return selUserSid2
     */
    public String getSelUserSid2() {
        return selUserSid2__;
    }
    /**
     * <p>selUserSid2 をセットします。
     * @param selUserSid2 selUserSid2
     */
    public void setSelUserSid2(String selUserSid2) {
        selUserSid2__ = selUserSid2;
    }
    /**
     * <p>svDomName2 を取得します。
     * @return svDomName2
     */
    public String getSvDomName2() {
        return svDomName2__;
    }
    /**
     * <p>svDomName2 をセットします。
     * @param svDomName2 svDomName2
     */
    public void setSvDomName2(String svDomName2) {
        svDomName2__ = svDomName2;
    }
    /**
     * <p>plginId を取得します。
     * @return plginId
     */
    public String getPlginId() {
        return plginId__;
    }
    /**
     * <p>plginId をセットします。
     * @param plginId plginId
     */
    public void setPlginId(String plginId) {
        plginId__ = plginId;
    }
    /**
     * <p>cmn230LeftTitleName を取得します。
     * @return cmn230LeftTitleName
     */
    public String getCmn230LeftTitleName() {
        return cmn230LeftTitleName__;
    }
    /**
     * <p>cmn230LeftTitleName をセットします。
     * @param cmn230LeftTitleName cmn230LeftTitleName
     */
    public void setCmn230LeftTitleName(String cmn230LeftTitleName) {
        cmn230LeftTitleName__ = cmn230LeftTitleName;
    }
    /**
     * <p>cmn230RightTitleName を取得します。
     * @return cmn230RightTitleName
     */
    public String getCmn230RightTitleName() {
        return cmn230RightTitleName__;
    }
    /**
     * <p>cmn230RightTitleName をセットします。
     * @param cmn230RightTitleName cmn230RightTitleName
     */
    public void setCmn230RightTitleName(String cmn230RightTitleName) {
        cmn230RightTitleName__ = cmn230RightTitleName;
    }
    /**
     * <p>cmn230SortTopKeyGp を取得します。
     * @return cmn230SortTopKeyGp
     */
    public int getCmn230SortTopKeyGp() {
        return cmn230SortTopKeyGp__;
    }
    /**
     * <p>cmn230SortTopKeyGp をセットします。
     * @param cmn230SortTopKeyGp cmn230SortTopKeyGp
     */
    public void setCmn230SortTopKeyGp(int cmn230SortTopKeyGp) {
        cmn230SortTopKeyGp__ = cmn230SortTopKeyGp;
    }
    /**
     * <p>cmn230SortTopKbnGp を取得します。
     * @return cmn230SortTopKbnGp
     */
    public int getCmn230SortTopKbnGp() {
        return cmn230SortTopKbnGp__;
    }
    /**
     * <p>cmn230SortTopKbnGp をセットします。
     * @param cmn230SortTopKbnGp cmn230SortTopKbnGp
     */
    public void setCmn230SortTopKbnGp(int cmn230SortTopKbnGp) {
        cmn230SortTopKbnGp__ = cmn230SortTopKbnGp;
    }
    /**
     * <p>cmn230SortBottomKeyGp を取得します。
     * @return cmn230SortBottomKeyGp
     */
    public int getCmn230SortBottomKeyGp() {
        return cmn230SortBottomKeyGp__;
    }
    /**
     * <p>cmn230SortBottomKeyGp をセットします。
     * @param cmn230SortBottomKeyGp cmn230SortBottomKeyGp
     */
    public void setCmn230SortBottomKeyGp(int cmn230SortBottomKeyGp) {
        cmn230SortBottomKeyGp__ = cmn230SortBottomKeyGp;
    }
    /**
     * <p>cmn230SortBottomKbnGp を取得します。
     * @return cmn230SortBottomKbnGp
     */
    public int getCmn230SortBottomKbnGp() {
        return cmn230SortBottomKbnGp__;
    }
    /**
     * <p>cmn230SortBottomKbnGp をセットします。
     * @param cmn230SortBottomKbnGp cmn230SortBottomKbnGp
     */
    public void setCmn230SortBottomKbnGp(int cmn230SortBottomKbnGp) {
        cmn230SortBottomKbnGp__ = cmn230SortBottomKbnGp;
    }
    /**
     * <p>cmn230SortBottomKeyGp2 を取得します。
     * @return cmn230SortBottomKeyGp2
     */
    public int getCmn230SortBottomKeyGp2() {
        return cmn230SortBottomKeyGp2__;
    }
    /**
     * <p>cmn230SortBottomKeyGp2 をセットします。
     * @param cmn230SortBottomKeyGp2 cmn230SortBottomKeyGp2
     */
    public void setCmn230SortBottomKeyGp2(int cmn230SortBottomKeyGp2) {
        cmn230SortBottomKeyGp2__ = cmn230SortBottomKeyGp2;
    }
    /**
     * <p>cmn230SortBottomKbnGp2 を取得します。
     * @return cmn230SortBottomKbnGp2
     */
    public int getCmn230SortBottomKbnGp2() {
        return cmn230SortBottomKbnGp2__;
    }
    /**
     * <p>cmn230SortBottomKbnGp2 をセットします。
     * @param cmn230SortBottomKbnGp2 cmn230SortBottomKbnGp2
     */
    public void setCmn230SortBottomKbnGp2(int cmn230SortBottomKbnGp2) {
        cmn230SortBottomKbnGp2__ = cmn230SortBottomKbnGp2;
    }
    /**
     * <p>cmn230groupSidTop を取得します。
     * @return cmn230groupSidTop
     */
    public String[] getCmn230groupSidTop() {
        return cmn230groupSidTop__;
    }
    /**
     * <p>cmn230groupSidTop をセットします。
     * @param cmn230groupSidTop cmn230groupSidTop
     */
    public void setCmn230groupSidTop(String[] cmn230groupSidTop) {
        cmn230groupSidTop__ = cmn230groupSidTop;
    }
    /**
     * <p>cmn230groupSidBottom を取得します。
     * @return cmn230groupSidBottom
     */
    public String[] getCmn230groupSidBottom() {
        return cmn230groupSidBottom__;
    }
    /**
     * <p>cmn230groupSidBottom をセットします。
     * @param cmn230groupSidBottom cmn230groupSidBottom
     */
    public void setCmn230groupSidBottom(String[] cmn230groupSidBottom) {
        cmn230groupSidBottom__ = cmn230groupSidBottom;
    }
    /**
     * <p>cmn230groupSidBottom2 を取得します。
     * @return cmn230groupSidBottom2
     */
    public String[] getCmn230groupSidBottom2() {
        return cmn230groupSidBottom2__;
    }
    /**
     * <p>cmn230groupSidBottom2 をセットします。
     * @param cmn230groupSidBottom2 cmn230groupSidBottom2
     */
    public void setCmn230groupSidBottom2(String[] cmn230groupSidBottom2) {
        cmn230groupSidBottom2__ = cmn230groupSidBottom2;
    }
    /**
     * <p>cmn230TopGroupList を取得します。
     * @return cmn230TopGroupList
     */
    public List<GroupModel> getCmn230TopGroupList() {
        return cmn230TopGroupList__;
    }
    /**
     * <p>cmn230TopGroupList をセットします。
     * @param cmn230TopGroupList cmn230TopGroupList
     */
    public void setCmn230TopGroupList(List<GroupModel> cmn230TopGroupList) {
        cmn230TopGroupList__ = cmn230TopGroupList;
    }
    /**
     * <p>cmn230BottomGroupList を取得します。
     * @return cmn230BottomGroupList
     */
    public List<GroupModel> getCmn230BottomGroupList() {
        return cmn230BottomGroupList__;
    }
    /**
     * <p>cmn230BottomGroupList をセットします。
     * @param cmn230BottomGroupList cmn230BottomGroupList
     */
    public void setCmn230BottomGroupList(List<GroupModel> cmn230BottomGroupList) {
        cmn230BottomGroupList__ = cmn230BottomGroupList;
    }
    /**
     * <p>cmn230BottomGroupList2 を取得します。
     * @return cmn230BottomGroupList2
     */
    public List<GroupModel> getCmn230BottomGroupList2() {
        return cmn230BottomGroupList2__;
    }
    /**
     * <p>cmn230BottomGroupList2 をセットします。
     * @param cmn230BottomGroupList2 cmn230BottomGroupList2
     */
    public void setCmn230BottomGroupList2(List<GroupModel> cmn230BottomGroupList2) {
        cmn230BottomGroupList2__ = cmn230BottomGroupList2;
    }
    /** ソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn230SortBottomKbnGp2__ = 1;
    /**
     * <p>selGrpFlg を取得します。
     * @return selGrpFlg
     */
    public int getSelGrpFlg() {
        return selGrpFlg__;
    }
    /**
     * <p>selGrpFlg をセットします。
     * @param selGrpFlg selGrpFlg
     */
    public void setSelGrpFlg(int selGrpFlg) {
        selGrpFlg__ = selGrpFlg;
    }
    /**
     * <br>[機  能] 指定されたTODOが全て編集可能かをチェックする
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     */
    public void validatePrm(HttpServletRequest req) {

        //チェックボックスの値を初期化
        String[] array = new String[0];
        if (req.getParameter("cmn230userSidTop") != null) {
            cmn230userSidTop__ = array;
        }
        if (req.getParameter("cmn230userSidBottom") != null) {
            cmn230userSidBottom__ = array;
        }
        if (req.getParameter("cmn230userSidBottom2") != null) {
            cmn230userSidBottom2__ = array;
        }
        if (req.getParameter("cmn230groupSidTop") != null) {
            cmn230groupSidTop__ = array;
        }
        if (req.getParameter("cmn230groupSidBottom") != null) {
            cmn230groupSidBottom__ = array;
        }
        if (req.getParameter("cmn230groupSidBottom2") != null) {
            cmn230groupSidBottom2__ = array;
        }
    }
}