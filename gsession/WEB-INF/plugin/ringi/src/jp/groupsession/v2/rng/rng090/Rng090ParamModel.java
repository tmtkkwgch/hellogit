package jp.groupsession.v2.rng.rng090;

import java.util.List;

import jp.groupsession.v2.rng.rng060.Rng060ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議 テンプレート登録画面のパラメータを保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng090ParamModel extends Rng060ParamModel {
    /** テンプレート名 */
    private String rng090title__ = null;
    /** 稟議タイトル */
    private String rng090rngTitle__ = null;
    /** 内容 */
    private String rng090content__ = null;
    /** カテゴリSID */
    private int rng090CatSid__ = 0;
    /** ユーザSID */
    private int rng090UserSid__ = 0;

    /** 添付ファイル */
    private String[] rng090files__ = null;

    /** ファイルコンボ */
    private List < LabelValueBean > rng090FileLabelList__ = null;

    /** ユーザ一覧 */
    private String[] rng090users__ = null;
    /** 承認経路 */
    private String[] rng090apprUser__ = null;
    /** 最終確認 */
    private String[] rng090confirmUser__ = null;
    /** 承認経路(選択) */
    private String[] rng090selectApprUser__ = null;
    /** 最終確認(選択) */
    private String[] rng090selectConfirmUser__ = null;
    /** グループ */
    private int rng090group__ = -1;

    //表示項目
    /** グループ一覧 */
    private List<LabelValueBean> rng090groupList__ = null;
    /** ユーザ一覧 */
    private List<LabelValueBean> rng090userList__ = null;
    /** 承認経路一覧 */
    private List<LabelValueBean> rng090apprUserList__ = null;
    /** 最終確認一覧 */
    private List<LabelValueBean> rng090confirmUserList__ = null;
    /** カテゴリ一覧 */
    private List<LabelValueBean> rng090CategoryList__ = null;
    /**
     * <p>rng090content を取得します。
     * @return rng090content
     */
    public String getRng090content() {
        return rng090content__;
    }

    /**
     * <p>rng090content をセットします。
     * @param rng090content rng090content
     */
    public void setRng090content(String rng090content) {
        rng090content__ = rng090content;
    }

    /**
     * <p>rng090FileLabelList を取得します。
     * @return rng090FileLabelList
     */
    public List<LabelValueBean> getRng090FileLabelList() {
        return rng090FileLabelList__;
    }

    /**
     * <p>rng090FileLabelList をセットします。
     * @param rng090FileLabelList rng090FileLabelList
     */
    public void setRng090FileLabelList(List<LabelValueBean> rng090FileLabelList) {
        rng090FileLabelList__ = rng090FileLabelList;
    }

    /**
     * <p>rng090files を取得します。
     * @return rng090files
     */
    public String[] getRng090files() {
        return rng090files__;
    }

    /**
     * <p>rng090files をセットします。
     * @param rng090files rng090files
     */
    public void setRng090files(String[] rng090files) {
        rng090files__ = rng090files;
    }

    /**
     * <p>rng090title を取得します。
     * @return rng090title
     */
    public String getRng090title() {
        return rng090title__;
    }

    /**
     * <p>rng090title をセットします。
     * @param rng090title rng090title
     */
    public void setRng090title(String rng090title) {
        rng090title__ = rng090title;
    }
    /**
     * <p>rng090rngTitle を取得します。
     * @return rng090rngTitle
     */
    public String getRng090rngTitle() {
        return rng090rngTitle__;
    }
    /**
     * <p>rng090rngTitle をセットします。
     * @param rng090rngTitle rng090rngTitle
     */
    public void setRng090rngTitle(String rng090rngTitle) {
        rng090rngTitle__ = rng090rngTitle;
    }
    /**
     * <p>rng090apprUser を取得します。
     * @return rng090apprUser
     */
    public String[] getRng090apprUser() {
        return rng090apprUser__;
    }
    /**
     * <p>rng090apprUser をセットします。
     * @param rng090apprUser rng090apprUser
     */
    public void setRng090apprUser(String[] rng090apprUser) {
        rng090apprUser__ = rng090apprUser;
    }
    /**
     * <p>rng090apprUserList を取得します。
     * @return rng090apprUserList
     */
    public List<LabelValueBean> getRng090apprUserList() {
        return rng090apprUserList__;
    }
    /**
     * <p>rng090apprUserList をセットします。
     * @param rng090apprUserList rng090apprUserList
     */
    public void setRng090apprUserList(List<LabelValueBean> rng090apprUserList) {
        rng090apprUserList__ = rng090apprUserList;
    }
    /**
     * <p>rng090confirmUser を取得します。
     * @return rng090confirmUser
     */
    public String[] getRng090confirmUser() {
        return rng090confirmUser__;
    }
    /**
     * <p>rng090confirmUser をセットします。
     * @param rng090confirmUser rng090confirmUser
     */
    public void setRng090confirmUser(String[] rng090confirmUser) {
        rng090confirmUser__ = rng090confirmUser;
    }
    /**
     * <p>rng090confirmUserList を取得します。
     * @return rng090confirmUserList
     */
    public List<LabelValueBean> getRng090confirmUserList() {
        return rng090confirmUserList__;
    }
    /**
     * <p>rng090confirmUserList をセットします。
     * @param rng090confirmUserList rng090confirmUserList
     */
    public void setRng090confirmUserList(List<LabelValueBean> rng090confirmUserList) {
        rng090confirmUserList__ = rng090confirmUserList;
    }
    /**
     * <p>rng090group を取得します。
     * @return rng090group
     */
    public int getRng090group() {
        return rng090group__;
    }
    /**
     * <p>rng090group をセットします。
     * @param rng090group rng090group
     */
    public void setRng090group(int rng090group) {
        rng090group__ = rng090group;
    }
    /**
     * <p>rng090groupList を取得します。
     * @return rng090groupList
     */
    public List<LabelValueBean> getRng090groupList() {
        return rng090groupList__;
    }
    /**
     * <p>rng090groupList をセットします。
     * @param rng090groupList rng090groupList
     */
    public void setRng090groupList(List<LabelValueBean> rng090groupList) {
        rng090groupList__ = rng090groupList;
    }
    /**
     * <p>rng090selectApprUser を取得します。
     * @return rng090selectApprUser
     */
    public String[] getRng090selectApprUser() {
        return rng090selectApprUser__;
    }
    /**
     * <p>rng090selectApprUser をセットします。
     * @param rng090selectApprUser rng090selectApprUser
     */
    public void setRng090selectApprUser(String[] rng090selectApprUser) {
        rng090selectApprUser__ = rng090selectApprUser;
    }
    /**
     * <p>rng090selectConfirmUser を取得します。
     * @return rng090selectConfirmUser
     */
    public String[] getRng090selectConfirmUser() {
        return rng090selectConfirmUser__;
    }
    /**
     * <p>rng090selectConfirmUser をセットします。
     * @param rng090selectConfirmUser rng090selectConfirmUser
     */
    public void setRng090selectConfirmUser(String[] rng090selectConfirmUser) {
        rng090selectConfirmUser__ = rng090selectConfirmUser;
    }
    /**
     * <p>rng090userList を取得します。
     * @return rng090userList
     */
    public List<LabelValueBean> getRng090userList() {
        return rng090userList__;
    }
    /**
     * <p>rng090userList をセットします。
     * @param rng090userList rng090userList
     */
    public void setRng090userList(List<LabelValueBean> rng090userList) {
        rng090userList__ = rng090userList;
    }
    /**
     * <p>rng090users を取得します。
     * @return rng090users
     */
    public String[] getRng090users() {
        return rng090users__;
    }
    /**
     * <p>rng090users をセットします。
     * @param rng090users rng090users
     */
    public void setRng090users(String[] rng090users) {
        rng090users__ = rng090users;
    }
    /**
     * @return rng090CategoryList
     */
    public List<LabelValueBean> getRng090CategoryList() {
        return rng090CategoryList__;
    }
    /**
     * @param rng090CategoryList 設定する rng090CategoryList
     */
    public void setRng090CategoryList(List<LabelValueBean> rng090CategoryList) {
        rng090CategoryList__ = rng090CategoryList;
    }
    /**
     * @return rng090CatSid
     */
    public int getRng090CatSid() {
        return rng090CatSid__;
    }
    /**
     * @param rng090CatSid 設定する rng090CatSid
     */
    public void setRng090CatSid(int rng090CatSid) {
        rng090CatSid__ = rng090CatSid;
    }
    /**
     * <p>rng090UserSid を取得します。
     * @return rng090UserSid
     */
    public int getRng090UserSid() {
        return rng090UserSid__;
    }
    /**
     * <p>rng090UserSid をセットします。
     * @param rng090UserSid rng090UserSid
     */
    public void setRng090UserSid(int rng090UserSid) {
        rng090UserSid__ = rng090UserSid;
    }
}