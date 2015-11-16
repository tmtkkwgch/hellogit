package jp.groupsession.v2.cmn.cmn120;

import java.util.List;

import org.apache.struts.util.LabelValueBean;

import jp.groupsession.v2.struts.AbstractGsForm;

/**
 * <br>[機  能] ユーザ選択画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn120Form extends AbstractGsForm {

    /** 呼び出し元画面URL */
    private String cmn120BackUrl__ = null;
    /** 機能名称 */
    private String cmn120FunctionName__ = null;
    /** 呼び出し元画面のフォーム識別子 */
    private String cmn120FormKey__ = null;
    /** 呼び出し元画面に返すパラメータ名 */
    private String cmn120paramName__ = null;

    /** お知らせ先SID */
    private String[] cmn120userSid__ = null;
    /** お知らせ先SID(初期) */
    private String[] cmn120userSidOld__ = null;
    /** グループSID */
    private String cmn120groupSid__ = null;
    /** マイグループSID */
    private int cmn120MyGroupSid__ = 0;
    /** ページ遷移フラグ */
    private int cmn120MovePage__ = 0;
    /** 利用不可ユーザSID */
    private String[] cmn120banUserSid__ = null;
    /** 利用不可グループSID */
    private String[] cmn120banGroupSid__ = null;
    /** 選択不可（コンボボックス）グループSID */
    private String[] cmn120disableGroupSid__ = null;

    /** マイグループ一覧 */
    private List<LabelValueBean> cmn120MyGroupList__ = null;
    /** グループ一覧 */
    private List<LabelValueBean> cmn120GroupList__ = null;
    /** 追加用ユーザ一覧 */
    private List<LabelValueBean> cmn120LeftUserList__ = null;
    /** 追加済みユーザ一覧 */
    private List<LabelValueBean> cmn120RightUserList__ = null;
    /** グループdisabledフラグリスト*/
    private List<Integer> cmn120GroupDisabledList__ = null;

    /** 追加用ユーザ(選択) */
    private String[] cmn120SelectLeftUser__ = null;
    /** 追加済みユーザ(選択) */
    private String[] cmn120SelectRightUser__ = null;

    /** 画面区分 */
    private int cmn120DspKbn__ = 0;

    /**
     * @return cmn120BackUrl を戻します。
     */
    public String getCmn120BackUrl() {
        return cmn120BackUrl__;
    }
    /**
     * @param cmn120BackUrl 設定する cmn120BackUrl。
     */
    public void setCmn120BackUrl(String cmn120BackUrl) {
        cmn120BackUrl__ = cmn120BackUrl;
    }
    /**
     * @return cmn120FunctionName を戻します。
     */
    public String getCmn120FunctionName() {
        return cmn120FunctionName__;
    }
    /**
     * @param cmn120FunctionName 設定する cmn120FunctionName。
     */
    public void setCmn120FunctionName(String cmn120FunctionName) {
        cmn120FunctionName__ = cmn120FunctionName;
    }
    /**
     * @return cmn120FormKey を戻します。
     */
    public String getCmn120FormKey() {
        return cmn120FormKey__;
    }
    /**
     * @param cmn120FormKey 設定する cmn120FormKey。
     */
    public void setCmn120FormKey(String cmn120FormKey) {
        cmn120FormKey__ = cmn120FormKey;
    }
    /**
     * @return cmn120GroupList を戻します。
     */
    public List<LabelValueBean> getCmn120GroupList() {
        return cmn120GroupList__;
    }
    /**
     * @param cmn120GroupList 設定する cmn120GroupList。
     */
    public void setCmn120GroupList(List<LabelValueBean> cmn120GroupList) {
        cmn120GroupList__ = cmn120GroupList;
    }

    /**
     * @return cmn120MyGroupList を戻します。
     */
    public List<LabelValueBean> getCmn120MyGroupList() {
        return cmn120MyGroupList__;
    }
    /**
     * @param cmn120MyGroupList 設定する cmn120MyGroupList。
     */
    public void setCmn120MyGroupList(List<LabelValueBean> cmn120MyGroupList) {
        cmn120MyGroupList__ = cmn120MyGroupList;
    }
    /**
     * @return cmn120MyGroupSid を戻します。
     */
    public int getCmn120MyGroupSid() {
        return cmn120MyGroupSid__;
    }
    /**
     * @param cmn120MyGroupSid 設定する cmn120MyGroupSid。
     */
    public void setCmn120MyGroupSid(int cmn120MyGroupSid) {
        cmn120MyGroupSid__ = cmn120MyGroupSid;
    }
    /**
     * @return cmn120MovePage を戻します。
     */
    public int getCmn120MovePage() {
        return cmn120MovePage__;
    }
    /**
     * @param cmn120MovePage 設定する cmn120MovePage。
     */
    public void setCmn120MovePage(int cmn120MovePage) {
        cmn120MovePage__ = cmn120MovePage;
    }
    /**
     * <p>cmn120banUserSid を取得します。
     * @return cmn120banUserSid
     */
    public String[] getCmn120banUserSid() {
        return cmn120banUserSid__;
    }
    /**
     * <p>cmn120banUserSid をセットします。
     * @param cmn120banUserSid cmn120banUserSid
     */
    public void setCmn120banUserSid(String[] cmn120banUserSid) {
        cmn120banUserSid__ = cmn120banUserSid;
    }
    /**
     * <p>cmn120disableGroupSid を取得します。
     * @return cmn120disableGroupSid
     */
    public String[] getCmn120disableGroupSid() {
        return cmn120disableGroupSid__;
    }
    /**
     * <p>cmn120disableGroupSid をセットします。
     * @param cmn120disableGroupSid cmn120disableGroupSid
     */
    public void setCmn120disableGroupSid(String[] cmn120disableGroupSid) {
        cmn120disableGroupSid__ = cmn120disableGroupSid;
    }
    /**
     * <p>cmn120banGroupSid を取得します。
     * @return cmn120banGroupSid
     */
    public String[] getCmn120banGroupSid() {
        return cmn120banGroupSid__;
    }
    /**
     * <p>cmn120banGroupSid をセットします。
     * @param cmn120banGroupSid cmn120banGroupSid
     */
    public void setCmn120banGroupSid(String[] cmn120banGroupSid) {
        cmn120banGroupSid__ = cmn120banGroupSid;
    }
    /**
     * @return cmn120LeftUserList を戻します。
     */
    public List<LabelValueBean> getCmn120LeftUserList() {
        return cmn120LeftUserList__;
    }
    /**
     * @param cmn120LeftUserList 設定する cmn120LeftUserList。
     */
    public void setCmn120LeftUserList(List<LabelValueBean> cmn120LeftUserList) {
        cmn120LeftUserList__ = cmn120LeftUserList;
    }
    /**
     * @return cmn120RightUserList を戻します。
     */
    public List<LabelValueBean> getCmn120RightUserList() {
        return cmn120RightUserList__;
    }
    /**
     * @param cmn120RightUserList 設定する cmn120RightUserList。
     */
    public void setCmn120RightUserList(List<LabelValueBean> cmn120RightUserList) {
        cmn120RightUserList__ = cmn120RightUserList;
    }
    /**
     * <p>cmn120GroupDisabledList を取得します。
     * @return cmn120GroupDisabledList
     */
    public List<Integer> getCmn120GroupDisabledList() {
        return cmn120GroupDisabledList__;
    }
    /**
     * <p>cmn120GroupDisabledList をセットします。
     * @param cmn120GroupDisabledList cmn120GroupDisabledList
     */
    public void setCmn120GroupDisabledList(List<Integer> cmn120GroupDisabledList) {
        cmn120GroupDisabledList__ = cmn120GroupDisabledList;
    }
    /**
     * @return cmn120userSid を戻します。
     */
    public String[] getCmn120userSid() {
        return cmn120userSid__;
    }
    /**
     * @param cmn120userSid 設定する cmn120userSid。
     */
    public void setCmn120userSid(String[] cmn120userSid) {
        cmn120userSid__ = cmn120userSid;
    }
    /**
     * @return cmn120userSidOld を戻します。
     */
    public String[] getCmn120userSidOld() {
        return cmn120userSidOld__;
    }
    /**
     * @param cmn120userSidOld 設定する cmn120userSidOld。
     */
    public void setCmn120userSidOld(String[] cmn120userSidOld) {
        cmn120userSidOld__ = cmn120userSidOld;
    }
    /**
     * @return cmn120SelectLeftUser を戻します。
     */
    public String[] getCmn120SelectLeftUser() {
        return cmn120SelectLeftUser__;
    }
    /**
     * @param cmn120SelectLeftUser 設定する cmn120SelectLeftUser。
     */
    public void setCmn120SelectLeftUser(String[] cmn120SelectLeftUser) {
        cmn120SelectLeftUser__ = cmn120SelectLeftUser;
    }
    /**
     * @return cmn120SelectRightUser を戻します。
     */
    public String[] getCmn120SelectRightUser() {
        return cmn120SelectRightUser__;
    }
    /**
     * @param cmn120SelectRightUser 設定する cmn120SelectRightUser。
     */
    public void setCmn120SelectRightUser(String[] cmn120SelectRightUser) {
        cmn120SelectRightUser__ = cmn120SelectRightUser;
    }
    /**
     * <p>cmn120paramName を取得します。
     * @return cmn120paramName
     */
    public String getCmn120paramName() {
        return cmn120paramName__;
    }
    /**
     * <p>cmn120paramName をセットします。
     * @param cmn120paramName cmn120paramName
     */
    public void setCmn120paramName(String cmn120paramName) {
        cmn120paramName__ = cmn120paramName;
    }
    /**
     * <p>cmn120groupSid を取得します。
     * @return cmn120groupSid
     */
    public String getCmn120groupSid() {
        return cmn120groupSid__;
    }
    /**
     * <p>cmn120groupSid をセットします。
     * @param cmn120groupSid cmn120groupSid
     */
    public void setCmn120groupSid(String cmn120groupSid) {
        cmn120groupSid__ = cmn120groupSid;
    }
    /**
     * <p>cmn120DspKbn を取得します。
     * @return cmn120DspKbn
     */
    public int getCmn120DspKbn() {
        return cmn120DspKbn__;
    }
    /**
     * <p>cmn120DspKbn をセットします。
     * @param cmn120DspKbn cmn120DspKbn
     */
    public void setCmn120DspKbn(int cmn120DspKbn) {
        cmn120DspKbn__ = cmn120DspKbn;
    }

}
