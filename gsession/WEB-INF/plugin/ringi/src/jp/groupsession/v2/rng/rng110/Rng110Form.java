package jp.groupsession.v2.rng.rng110;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.RngValidate;
import jp.groupsession.v2.rng.rng100.Rng100Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議 経路テンプレート登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng110Form extends Rng100Form {

    /** 経路テンプレート名称 */
    private String rng110name__ = null;
    /** ユーザ一覧 */
    private String[] rng110users__ = null;
    /** 承認経路 */
    private String[] rng110apprUser__ = null;
    /** 最終確認 */
    private String[] rng110confirmUser__ = null;
    /** 承認経路(選択) */
    private String[] rng110selectApprUser__ = null;
    /** 最終確認(選択) */
    private String[] rng110selectConfirmUser__ = null;
    /** グループ */
    private int rng110group__ = -1;
    /** ユーザSID */
    private int rng110UserSid__ = 0;

    //表示項目
    /** グループ一覧 */
    private List<LabelValueBean> rng110groupList__ = null;
    /** ユーザ一覧 */
    private List<LabelValueBean> rng110userList__ = null;
    /** 承認経路一覧 */
    private List<LabelValueBean> rng110apprUserList__ = null;
    /** 最終確認一覧 */
    private List<LabelValueBean> rng110confirmUserList__ = null;

    /**
     * <p>rng110apprUser を取得します。
     * @return rng110apprUser
     */
    public String[] getRng110apprUser() {
        return rng110apprUser__;
    }

    /**
     * <p>rng110apprUser をセットします。
     * @param rng110apprUser rng110apprUser
     */
    public void setRng110apprUser(String[] rng110apprUser) {
        rng110apprUser__ = rng110apprUser;
    }

    /**
     * <p>rng110apprUserList を取得します。
     * @return rng110apprUserList
     */
    public List<LabelValueBean> getRng110apprUserList() {
        return rng110apprUserList__;
    }

    /**
     * <p>rng110apprUserList をセットします。
     * @param rng110apprUserList rng110apprUserList
     */
    public void setRng110apprUserList(List<LabelValueBean> rng110apprUserList) {
        rng110apprUserList__ = rng110apprUserList;
    }

    /**
     * <p>rng110confirmUser を取得します。
     * @return rng110confirmUser
     */
    public String[] getRng110confirmUser() {
        return rng110confirmUser__;
    }

    /**
     * <p>rng110confirmUser をセットします。
     * @param rng110confirmUser rng110confirmUser
     */
    public void setRng110confirmUser(String[] rng110confirmUser) {
        rng110confirmUser__ = rng110confirmUser;
    }

    /**
     * <p>rng110confirmUserList を取得します。
     * @return rng110confirmUserList
     */
    public List<LabelValueBean> getRng110confirmUserList() {
        return rng110confirmUserList__;
    }

    /**
     * <p>rng110confirmUserList をセットします。
     * @param rng110confirmUserList rng110confirmUserList
     */
    public void setRng110confirmUserList(List<LabelValueBean> rng110confirmUserList) {
        rng110confirmUserList__ = rng110confirmUserList;
    }

    /**
     * <p>rng110group を取得します。
     * @return rng110group
     */
    public int getRng110group() {
        return rng110group__;
    }

    /**
     * <p>rng110group をセットします。
     * @param rng110group rng110group
     */
    public void setRng110group(int rng110group) {
        rng110group__ = rng110group;
    }

    /**
     * <p>rng110groupList を取得します。
     * @return rng110groupList
     */
    public List<LabelValueBean> getRng110groupList() {
        return rng110groupList__;
    }

    /**
     * <p>rng110groupList をセットします。
     * @param rng110groupList rng110groupList
     */
    public void setRng110groupList(List<LabelValueBean> rng110groupList) {
        rng110groupList__ = rng110groupList;
    }

    /**
     * <p>rng110name を取得します。
     * @return rng110name
     */
    public String getRng110name() {
        return rng110name__;
    }

    /**
     * <p>rng110name をセットします。
     * @param rng110name rng110name
     */
    public void setRng110name(String rng110name) {
        rng110name__ = rng110name;
    }

    /**
     * <p>rng110selectApprUser を取得します。
     * @return rng110selectApprUser
     */
    public String[] getRng110selectApprUser() {
        return rng110selectApprUser__;
    }

    /**
     * <p>rng110selectApprUser をセットします。
     * @param rng110selectApprUser rng110selectApprUser
     */
    public void setRng110selectApprUser(String[] rng110selectApprUser) {
        rng110selectApprUser__ = rng110selectApprUser;
    }

    /**
     * <p>rng110selectConfirmUser を取得します。
     * @return rng110selectConfirmUser
     */
    public String[] getRng110selectConfirmUser() {
        return rng110selectConfirmUser__;
    }

    /**
     * <p>rng110selectConfirmUser をセットします。
     * @param rng110selectConfirmUser rng110selectConfirmUser
     */
    public void setRng110selectConfirmUser(String[] rng110selectConfirmUser) {
        rng110selectConfirmUser__ = rng110selectConfirmUser;
    }

    /**
     * <p>rng110userList を取得します。
     * @return rng110userList
     */
    public List<LabelValueBean> getRng110userList() {
        return rng110userList__;
    }

    /**
     * <p>rng110userList をセットします。
     * @param rng110userList rng110userList
     */
    public void setRng110userList(List<LabelValueBean> rng110userList) {
        rng110userList__ = rng110userList;
    }

    /**
     * <p>rng110users を取得します。
     * @return rng110users
     */
    public String[] getRng110users() {
        return rng110users__;
    }

    /**
     * <p>rng110users をセットします。
     * @param rng110users rng110users
     */
    public void setRng110users(String[] rng110users) {
        rng110users__ = rng110users;
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
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        String keiroName = gsMsg.getMessage(req, "rng.rng110.02");
        String error = gsMsg.getMessage(req, "rng.82");

        //-- 経路名称チェック --
        errors = RngValidate.validateCmnFieldText(
                errors,
                keiroName,
                rng110name__,
                "rng110Name",
                RngConst.MAX_LENGTH_KEIRONAME,
                true);

        
        //承認経路 最終確認ユーザ未選択チェック
        if ((rng110apprUser__ == null || rng110apprUser__.length < 1)
        && (rng110confirmUser__ == null || rng110confirmUser__.length < 1)) {

            //承認経路と最終確認ユーザの両方が選択されていない場合、エラー
            msg = new ActionMessage("error.select.required.text", error);
            StrutsUtil.addMessage(errors, msg, "rng110apprUser");

        }

        return errors;
    }

    /**
     * <p>rng110UserSid を取得します。
     * @return rng110UserSid
     */
    public int getRng110UserSid() {
        return rng110UserSid__;
    }

    /**
     * <p>rng110UserSid をセットします。
     * @param rng110UserSid rng110UserSid
     */
    public void setRng110UserSid(int rng110UserSid) {
        rng110UserSid__ = rng110UserSid;
    }

}
