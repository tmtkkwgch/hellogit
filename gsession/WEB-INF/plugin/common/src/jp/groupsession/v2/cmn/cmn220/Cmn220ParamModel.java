package jp.groupsession.v2.cmn.cmn220;

import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.model.AbstractParamModel;
import jp.groupsession.v2.cmn.model.CmnLabelValueModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] グループ一覧ポップアップのパラメータ、出力値を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn220ParamModel extends AbstractParamModel {
    /** グループリスト */
    ArrayList<GroupModel> groupList__ = null;
    /** マイグループリスト */
    ArrayList<CmnLabelValueModel> myGroupList__ = null;
    /** 親画面ID */
    String parentDspID__ = null;
    /** 親画面セレクトボックス名 */
    String selBoxName__ = null;
    /** 親画面DOM名 */
    String svDomName__ = null;
    /** 親画面のコマンド */
    String submitCmd__ = null;
    /** 親画面の選択グループ */
    String selGroup__ = null;
    /** 親画面の選択グループ */
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
    String selUserSid__ = null;
    /** 移動ユーザ */
    String moveUsers__ = null;
    /** 移動ユーザ */
    String[] moveUserSid__ = null;
    /** グループSID */
    private String cmn220groupSid__ = null;
    /** グループ一覧 */
    private List<LabelValueBean> cmn220GroupList__ = null;
    /** 追加用ユーザ一覧 */
    private List<Cmn220UsrDspModel> cmn220TopUserList__ = null;
    /** 追加済みユーザ一覧 */
    private List<Cmn220UsrDspModel> cmn220BottomUserList__ = null;
    /** 追加済みユーザSID */
    private String[] cmn220userSid__ = null;
    /** 追加ユーザSID(上) */
    private String[] cmn220userSidTop__ = null;
    /** 追加ユーザSID(下) */
    private String[] cmn220userSidBottom__ = null;
    /** 追加用ユーザ(選択) */
    private String[] cmn220SelectLeftUser__ = null;
    /** 表示グループ名 */
    private String cmn220DspGrpName__ = null;
    /** 検索フラグ */
    private int cmn220SearchFlg__ = 0;
    /** 検索文字列 */
    private String cmn220SearchStr__ = null;
    /** デフォルトグループSID(保存用) */
    private String cmn220dfGpSidStrSv__ = null;
    /** デフォルトグループSID(保存用) */
    private String cmn220dspGpSidSv__;
    /** ユーザソートフラグ  0:社員/職員番号 1:ユーザ名*/
    private int cmn220SortTopKey__ = 0;
    /** ユーザソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn220SortTopKbn__ = 1;
    /** ユーザソートフラグ  0:社員/職員番号 1:ユーザ名*/
    private int cmn220SortBottomKey__ = 0;
    /** ユーザソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn220SortBottomKbn__ = 1;
    /** グループソートフラグ  0:社員/職員番号 1:ユーザ名*/
    private int cmn220SortTopKeyGp__ = -1;
    /** グループソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn220SortTopKbnGp__ = 1;
    /** グループソートフラグ  0:社員/職員番号 1:ユーザ名*/
    private int cmn220SortBottomKeyGp__ = -1;
    /** グループソートフラグ（昇順、降順）  1:昇順 -1:降順*/
    private int cmn220SortBottomKbnGp__ = 1;
    /** タブフラグ 0:タブ非表示  1:タブ非表*/
    private int cmn220TabFlg__ = 0;
    /** タブモード 0:ユーザ  1:グループ*/
    private int cmn220TabMode__ = 0;
    /** 追加用グループ一覧 */
    private List<GroupModel> cmn220TopGroupList__ = null;
    /** 追加済みグループ一覧 */
    private List<GroupModel> cmn220BottomGroupList__ = null;
    /** 追加済みグループSID */
    private String[] cmn220groupSidadd__ = null;
    /** 追加グループSID(上) */
    private String[] cmn220groupSidTop__ = null;
    /** 追加グループSID(下) */
    private String[] cmn220groupSidBottom__ = null;

    /** 利用不可ユーザSID */
    private String[] cmn220banUserSid__ = null;
    /** 選択不可グループSID */
    private String[] cmn220banGroupSid__ = null;
    /** 選択不可（コンボボックス）グループSID */
    private String[] cmn220disableGroupSid__ = null;
    /** グループリスト表示非表示フラグ */
    private List<Integer> groupDisableKbnList__ = null;

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
     * <p>cmn220groupSidTop を取得します。
     * @return cmn220groupSidTop
     */
    public String[] getCmn220groupSidTop() {
        return cmn220groupSidTop__;
    }
    /**
     * <p>cmn220groupSidTop をセットします。
     * @param cmn220groupSidTop cmn220groupSidTop
     */
    public void setCmn220groupSidTop(String[] cmn220groupSidTop) {
        cmn220groupSidTop__ = cmn220groupSidTop;
    }
    /**
     * <p>cmn220groupSidBottom を取得します。
     * @return cmn220groupSidBottom
     */
    public String[] getCmn220groupSidBottom() {
        return cmn220groupSidBottom__;
    }
    /**
     * <p>cmn220groupSidBottom をセットします。
     * @param cmn220groupSidBottom cmn220groupSidBottom
     */
    public void setCmn220groupSidBottom(String[] cmn220groupSidBottom) {
        cmn220groupSidBottom__ = cmn220groupSidBottom;
    }
    /**
     * <p>cmn220TopGroupList を取得します。
     * @return cmn220TopGroupList
     */
    public List<GroupModel> getCmn220TopGroupList() {
        return cmn220TopGroupList__;
    }
    /**
     * <p>cmn220TopGroupList をセットします。
     * @param cmn220TopGroupList cmn220TopGroupList
     */
    public void setCmn220TopGroupList(List<GroupModel> cmn220TopGroupList) {
        cmn220TopGroupList__ = cmn220TopGroupList;
    }
    /**
     * <p>cmn220BottomGroupList を取得します。
     * @return cmn220BottomGroupList
     */
    public List<GroupModel> getCmn220BottomGroupList() {
        return cmn220BottomGroupList__;
    }
    /**
     * <p>cmn220BottomGroupList をセットします。
     * @param cmn220BottomGroupList cmn220BottomGroupList
     */
    public void setCmn220BottomGroupList(List<GroupModel> cmn220BottomGroupList) {
        cmn220BottomGroupList__ = cmn220BottomGroupList;
    }
    /**
     * <p>cmn220TabFlg を取得します。
     * @return cmn220TabFlg
     */
    public int getCmn220TabFlg() {
        return cmn220TabFlg__;
    }
    /**
     * <p>cmn220TabFlg をセットします。
     * @param cmn220TabFlg cmn220TabFlg
     */
    public void setCmn220TabFlg(int cmn220TabFlg) {
        cmn220TabFlg__ = cmn220TabFlg;
    }
    /**
     * <p>cmn220TabMode を取得します。
     * @return cmn220TabMode
     */
    public int getCmn220TabMode() {
        return cmn220TabMode__;
    }
    /**
     * <p>cmn220TabMode をセットします。
     * @param cmn220TabMode cmn220TabMode
     */
    public void setCmn220TabMode(int cmn220TabMode) {
        cmn220TabMode__ = cmn220TabMode;
    }
    /**
     * <p>cmn220SortTopKey を取得します。
     * @return cmn220SortTopKey
     */
    public int getCmn220SortTopKey() {
        return cmn220SortTopKey__;
    }
    /**
     * <p>cmn220SortTopKey をセットします。
     * @param cmn220SortTopKey cmn220SortTopKey
     */
    public void setCmn220SortTopKey(int cmn220SortTopKey) {
        cmn220SortTopKey__ = cmn220SortTopKey;
    }
    /**
     * <p>cmn220SortTopKbn を取得します。
     * @return cmn220SortTopKbn
     */
    public int getCmn220SortTopKbn() {
        return cmn220SortTopKbn__;
    }
    /**
     * <p>cmn220SortTopKbn をセットします。
     * @param cmn220SortTopKbn cmn220SortTopKbn
     */
    public void setCmn220SortTopKbn(int cmn220SortTopKbn) {
        cmn220SortTopKbn__ = cmn220SortTopKbn;
    }
    /**
     * <p>cmn220SortBottomKey を取得します。
     * @return cmn220SortBottomKey
     */
    public int getCmn220SortBottomKey() {
        return cmn220SortBottomKey__;
    }
    /**
     * <p>cmn220SortBottomKey をセットします。
     * @param cmn220SortBottomKey cmn220SortBottomKey
     */
    public void setCmn220SortBottomKey(int cmn220SortBottomKey) {
        cmn220SortBottomKey__ = cmn220SortBottomKey;
    }
    /**
     * <p>cmn220SortBottomKbn を取得します。
     * @return cmn220SortBottomKbn
     */
    public int getCmn220SortBottomKbn() {
        return cmn220SortBottomKbn__;
    }
    /**
     * <p>cmn220SortBottomKbn をセットします。
     * @param cmn220SortBottomKbn cmn220SortBottomKbn
     */
    public void setCmn220SortBottomKbn(int cmn220SortBottomKbn) {
        cmn220SortBottomKbn__ = cmn220SortBottomKbn;
    }
    /**
     * <p>cmn220SearchStr を取得します。
     * @return cmn220SearchStr
     */
    public String getCmn220SearchStr() {
        return cmn220SearchStr__;
    }
    /**
     * <p>cmn220SearchStr をセットします。
     * @param cmn220SearchStr cmn220SearchStr
     */
    public void setCmn220SearchStr(String cmn220SearchStr) {
        cmn220SearchStr__ = cmn220SearchStr;
    }
    /**
     * <p>cmn220SearchFlg を取得します。
     * @return cmn220SearchFlg
     */
    public int getCmn220SearchFlg() {
        return cmn220SearchFlg__;
    }
    /**
     * <p>cmn220SearchFlg をセットします。
     * @param cmn220SearchFlg cmn220SearchFlg
     */
    public void setCmn220SearchFlg(int cmn220SearchFlg) {
        cmn220SearchFlg__ = cmn220SearchFlg;
    }
    /**
     * <p>cmn220DspGrpName を取得します。
     * @return cmn220DspGrpName
     */
    public String getCmn220DspGrpName() {
        return cmn220DspGrpName__;
    }
    /**
     * <p>cmn220DspGrpName をセットします。
     * @param cmn220DspGrpName cmn220DspGrpName
     */
    public void setCmn220DspGrpName(String cmn220DspGrpName) {
        cmn220DspGrpName__ = cmn220DspGrpName;
    }
    /**
     * <p>cmn220GroupList を取得します。
     * @return cmn220GroupList
     */
    public List<LabelValueBean> getCmn220GroupList() {
        return cmn220GroupList__;
    }
    /**
     * <p>cmn220GroupList をセットします。
     * @param cmn220GroupList cmn220GroupList
     */
    public void setCmn220GroupList(List<LabelValueBean> cmn220GroupList) {
        cmn220GroupList__ = cmn220GroupList;
    }
    /**
     * <p>cmn220TopUserList を取得します。
     * @return cmn220TopUserList
     */
    public List<Cmn220UsrDspModel> getCmn220TopUserList() {
        return cmn220TopUserList__;
    }
    /**
     * <p>cmn220TopUserList をセットします。
     * @param cmn220TopUserList cmn220TopUserList
     */
    public void setCmn220TopUserList(List<Cmn220UsrDspModel> cmn220TopUserList) {
        cmn220TopUserList__ = cmn220TopUserList;
    }
    /**
     * <p>cmn220BottomUserList を取得します。
     * @return cmn220BottomUserList
     */
    public List<Cmn220UsrDspModel> getCmn220BottomUserList() {
        return cmn220BottomUserList__;
    }
    /**
     * <p>cmn220BottomUserList をセットします。
     * @param cmn220BottomUserList cmn220BottomUserList
     */
    public void setCmn220BottomUserList(List<Cmn220UsrDspModel> cmn220BottomUserList) {
        cmn220BottomUserList__ = cmn220BottomUserList;
    }
    /**
     * <p>cmn220userSid を取得します。
     * @return cmn220userSid
     */
    public String[] getCmn220userSid() {
        return cmn220userSid__;
    }
    /**
     * <p>cmn220userSid をセットします。
     * @param cmn220userSid cmn220userSid
     */
    public void setCmn220userSid(String[] cmn220userSid) {
        cmn220userSid__ = cmn220userSid;
    }
    /**
     * <p>cmn220userSidTop を取得します。
     * @return cmn220userSidTop
     */
    public String[] getCmn220userSidTop() {
        return cmn220userSidTop__;
    }
    /**
     * <p>cmn220userSidTop をセットします。
     * @param cmn220userSidTop cmn220userSidTop
     */
    public void setCmn220userSidTop(String[] cmn220userSidTop) {
        cmn220userSidTop__ = cmn220userSidTop;
    }
    /**
     * <p>cmn220userSidBottom を取得します。
     * @return cmn220userSidBottom
     */
    public String[] getCmn220userSidBottom() {
        return cmn220userSidBottom__;
    }
    /**
     * <p>cmn220userSidBottom をセットします。
     * @param cmn220userSidBottom cmn220userSidBottom
     */
    public void setCmn220userSidBottom(String[] cmn220userSidBottom) {
        cmn220userSidBottom__ = cmn220userSidBottom;
    }
    /**
     * <p>cmn220SelectLeftUser を取得します。
     * @return cmn220SelectLeftUser
     */
    public String[] getCmn220SelectLeftUser() {
        return cmn220SelectLeftUser__;
    }
    /**
     * <p>cmn220SelectLeftUser をセットします。
     * @param cmn220SelectLeftUser cmn220SelectLeftUser
     */
    public void setCmn220SelectLeftUser(String[] cmn220SelectLeftUser) {
        cmn220SelectLeftUser__ = cmn220SelectLeftUser;
    }
    /**
     * <p>cmn220SelectRightUser を取得します。
     * @return cmn220SelectRightUser
     */
    public String[] getCmn220SelectRightUser() {
        return cmn220SelectRightUser__;
    }
    /**
     * <p>cmn220SelectRightUser をセットします。
     * @param cmn220SelectRightUser cmn220SelectRightUser
     */
    public void setCmn220SelectRightUser(String[] cmn220SelectRightUser) {
        cmn220SelectRightUser__ = cmn220SelectRightUser;
    }
    /** 追加済みユーザ(選択) */
    private String[] cmn220SelectRightUser__ = null;
    /**
     * <p>cmn220groupSid を取得します。
     * @return cmn220groupSid
     */
    public String getCmn220groupSid() {
        return cmn220groupSid__;
    }
    /**
     * <p>cmn220groupSid をセットします。
     * @param cmn220groupSid cmn220groupSid
     */
    public void setCmn220groupSid(String cmn220groupSid) {
        cmn220groupSid__ = cmn220groupSid;
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
     * <p>cmn220dfGpSidStrSv を取得します。
     * @return cmn220dfGpSidStrSv
     */
    public String getCmn220dfGpSidStrSv() {
        return cmn220dfGpSidStrSv__;
    }
    /**
     * <p>cmn220dfGpSidStrSv をセットします。
     * @param cmn220dfGpSidStrSv cmn220dfGpSidStrSv
     */
    public void setCmn220dfGpSidStrSv(String cmn220dfGpSidStrSv) {
        cmn220dfGpSidStrSv__ = cmn220dfGpSidStrSv;
    }
    /**
     * <p>cmn220dspGpSidSv を取得します。
     * @return cmn220dspGpSidSv
     */
    public String getCmn220dspGpSidSv() {
        return cmn220dspGpSidSv__;
    }
    /**
     * <p>cmn220dspGpSidSv をセットします。
     * @param cmn220dspGpSidSv cmn220dspGpSidSv
     */
    public void setCmn220dspGpSidSv(String cmn220dspGpSidSv) {
        cmn220dspGpSidSv__ = cmn220dspGpSidSv;
    }
    /**
     * <p>cmn220groupSidadd を取得します。
     * @return cmn220groupSidadd
     */
    public String[] getCmn220groupSidadd() {
        return cmn220groupSidadd__;
    }
    /**
     * <p>cmn220groupSidadd をセットします。
     * @param cmn220groupSidadd cmn220groupSidadd
     */
    public void setCmn220groupSidadd(String[] cmn220groupSidadd) {
        cmn220groupSidadd__ = cmn220groupSidadd;
    }
    /**
     * <p>cmn220SortTopKeyGp を取得します。
     * @return cmn220SortTopKeyGp
     */
    public int getCmn220SortTopKeyGp() {
        return cmn220SortTopKeyGp__;
    }
    /**
     * <p>cmn220SortTopKeyGp をセットします。
     * @param cmn220SortTopKeyGp cmn220SortTopKeyGp
     */
    public void setCmn220SortTopKeyGp(int cmn220SortTopKeyGp) {
        cmn220SortTopKeyGp__ = cmn220SortTopKeyGp;
    }
    /**
     * <p>cmn220SortTopKbnGp を取得します。
     * @return cmn220SortTopKbnGp
     */
    public int getCmn220SortTopKbnGp() {
        return cmn220SortTopKbnGp__;
    }
    /**
     * <p>cmn220SortTopKbnGp をセットします。
     * @param cmn220SortTopKbnGp cmn220SortTopKbnGp
     */
    public void setCmn220SortTopKbnGp(int cmn220SortTopKbnGp) {
        cmn220SortTopKbnGp__ = cmn220SortTopKbnGp;
    }
    /**
     * <p>cmn220SortBottomKeyGp を取得します。
     * @return cmn220SortBottomKeyGp
     */
    public int getCmn220SortBottomKeyGp() {
        return cmn220SortBottomKeyGp__;
    }
    /**
     * <p>cmn220SortBottomKeyGp をセットします。
     * @param cmn220SortBottomKeyGp cmn220SortBottomKeyGp
     */
    public void setCmn220SortBottomKeyGp(int cmn220SortBottomKeyGp) {
        cmn220SortBottomKeyGp__ = cmn220SortBottomKeyGp;
    }
    /**
     * <p>cmn220SortBottomKbnGp を取得します。
     * @return cmn220SortBottomKbnGp
     */
    public int getCmn220SortBottomKbnGp() {
        return cmn220SortBottomKbnGp__;
    }
    /**
     * <p>cmn220SortBottomKbnGp をセットします。
     * @param cmn220SortBottomKbnGp cmn220SortBottomKbnGp
     */
    public void setCmn220SortBottomKbnGp(int cmn220SortBottomKbnGp) {
        cmn220SortBottomKbnGp__ = cmn220SortBottomKbnGp;
    }
    /**
     * <p>cmn220banUserSid を取得します。
     * @return cmn220banUserSid
     */
    public String[] getCmn220banUserSid() {
        return cmn220banUserSid__;
    }
    /**
     * <p>cmn220banUserSid をセットします。
     * @param cmn220banUserSid cmn220banUserSid
     */
    public void setCmn220banUserSid(String[] cmn220banUserSid) {
        cmn220banUserSid__ = cmn220banUserSid;
    }
    /**
     * <p>cmn220banGroupSid を取得します。
     * @return cmn220banGroupSid
     */
    public String[] getCmn220banGroupSid() {
        return cmn220banGroupSid__;
    }
    /**
     * <p>cmn220banGroupSid をセットします。
     * @param cmn220banGroupSid cmn220disableGroupSid
     */
    public void setCmn220banGroupSid(String[] cmn220banGroupSid) {
        cmn220banGroupSid__ = cmn220banGroupSid;
    }
    /**
     * <p>cmn220disableGroupSid を取得します。
     * @return cmn220disableGroupSid
     */
    public String[] getCmn220disableGroupSid() {
        return cmn220disableGroupSid__;
    }
    /**
     * <p>cmn220disableGroupSid をセットします。
     * @param cmn220disableGroupSid cmn220disableGroupSid
     */
    public void setCmn220disableGroupSid(String[] cmn220disableGroupSid) {
        cmn220disableGroupSid__ = cmn220disableGroupSid;
    }
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
     * <p>groupDisableKbnList を取得します。
     * @return groupDisableKbnList
     */
    public List<Integer> getGroupDisableKbnList() {
        return groupDisableKbnList__;
    }
    /**
     * <p>groupDisableKbnList をセットします。
     * @param groupDisableKbnList groupDisableKbnList
     */
    public void setGroupDisableKbnList(List<Integer> groupDisableKbnList) {
        groupDisableKbnList__ = groupDisableKbnList;
    }

}