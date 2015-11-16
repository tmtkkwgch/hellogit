package jp.groupsession.v2.man.man300;

import java.util.List;

import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.man320.Man320Form;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン インフォメーション 管理者設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man300Form extends Man320Form {
    //入力項目
    /** 公開対象者SID */
    private String[] man300memberSid__ = new String[0];
    /** 追加済みメンバー(選択) */
    private String[] man300SelectLeftUser__ = new String[0];
    /** 追加用メンバー(選択) */
    private String[] man300SelectRightUser__ = new String[0];
    /** グループSID */
    private int man300groupSid__ = Integer.parseInt(GSConstMain.GROUP_COMBO_VALUE);
    /** グループ一覧 */
    private List <LabelValueBean> man300GroupList__ = null;
    /** 追加済みユーザ一覧 */
    private List <LabelValueBean> man300LeftUserList__ = null;
    /** 追加用ユーザ一覧 */
    private List <LabelValueBean> man300RightUserList__ = null;
    /** 遷移元 メイン個人設定メニュー:2 メイン管理者設定メニュー:1 その他:0*/
    private int backScreen__ = GSConstMain.BACK_PLUGIN;

    /**
     * <p>backScreen を取得します。
     * @return backScreen
     */
    public int getBackScreen() {
        return backScreen__;
    }
    /**
     * <p>backScreen をセットします。
     * @param backScreen backScreen
     */
    public void setBackScreen(int backScreen) {
        backScreen__ = backScreen;
    }
    /**
     * @return the man300memberSid
     */
    public String[] getMan300memberSid() {
        return man300memberSid__;
    }
    /**
     * @param man300memberSid the man300memberSid to set
     */
    public void setMan300memberSid(String[] man300memberSid) {
        man300memberSid__ = man300memberSid;
    }
    /**
     * @return the man300SelectLeftUser
     */
    public String[] getMan300SelectLeftUser() {
        return man300SelectLeftUser__;
    }
    /**
     * @param man300SelectLeftUser the man300SelectLeftUser to set
     */
    public void setMan300SelectLeftUser(String[] man300SelectLeftUser) {
        man300SelectLeftUser__ = man300SelectLeftUser;
    }
    /**
     * @return the man300SelectRightUser
     */
    public String[] getMan300SelectRightUser() {
        return man300SelectRightUser__;
    }
    /**
     * @param man300SelectRightUser the man300SelectRightUser to set
     */
    public void setMan300SelectRightUser(String[] man300SelectRightUser) {
        man300SelectRightUser__ = man300SelectRightUser;
    }
    /**
     * @return the man300groupSid
     */
    public int getMan300groupSid() {
        return man300groupSid__;
    }
    /**
     * @param man300groupSid the man300groupSid to set
     */
    public void setMan300groupSid(int man300groupSid) {
        man300groupSid__ = man300groupSid;
    }
    /**
     * @return the man300GroupList
     */
    public List<LabelValueBean> getMan300GroupList() {
        return man300GroupList__;
    }
    /**
     * @param man300GroupList the man300GroupList to set
     */
    public void setMan300GroupList(List<LabelValueBean> man300GroupList) {
        man300GroupList__ = man300GroupList;
    }
    /**
     * @return the man300LeftUserList
     */
    public List<LabelValueBean> getMan300LeftUserList() {
        return man300LeftUserList__;
    }
    /**
     * @param man300LeftUserList the man300LeftUserList to set
     */
    public void setMan300LeftUserList(List<LabelValueBean> man300LeftUserList) {
        man300LeftUserList__ = man300LeftUserList;
    }
    /**
     * @return the man300RightUserList
     */
    public List<LabelValueBean> getMan300RightUserList() {
        return man300RightUserList__;
    }
    /**
     * @param man300RightUserList the man300RightUserList to set
     */
    public void setMan300RightUserList(List<LabelValueBean> man300RightUserList) {
        man300RightUserList__ = man300RightUserList;
    }

}
