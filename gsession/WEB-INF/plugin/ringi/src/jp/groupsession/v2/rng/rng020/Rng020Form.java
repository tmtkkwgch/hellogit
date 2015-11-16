package jp.groupsession.v2.rng.rng020;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.RngValidate;
import jp.groupsession.v2.rng.rng130.Rng130Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議作成画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng020Form extends Rng130Form {

    /** 入力チェック種別 申請 */
    public static final int CHECKTYPE_REQUEST = 1;
    /** 入力チェック種別 草稿 */
    public static final int CHECKTYPE_DRAFT = 2;

    /** テンプレート名 */
    private String rng020Title__ = null;
    /** 稟議タイトル */
    private String rng020RngTitle__ = null;
    /** 内容 */
    private String rng020Content__ = null;
    /** ユーザ一覧 */
    private String[] rng020users__ = null;
    /** 添付ファイル一覧 */
    private String[] rng020files__ = null;
    /** 承認経路 */
    private String[] rng020apprUser__ = null;
    /** 最終確認 */
    private String[] rng020confirmUser__ = null;
    /** 承認経路(選択) */
    private String[] rng020selectApprUser__ = null;
    /** 最終確認(選択) */
    private String[] rng020selectConfirmUser__ = null;
    /** グループ */
    private int rng020group__ = -1;
    /** 経路テンプレート */
    private int rng020rctSid__ = -1;

    /** テンプレートから設定した添付ファイルのファイルID */
    private String rng020TemplateFileId__ = null;
    /** 複写して申請 */
    private boolean rng020copyApply__ = false;

    //表示項目

    /** 申請者 */
    private String rng020requestUser__ = null;
    /** 申請者 id*/
    private String rng020requestUserId__ = null;
    /** 作成日 */
    private String rng020createDate__ = null;
    /** 添付ファイル一覧 */
    private List<LabelValueBean> rng020fileList__ = null;
    /** グループ一覧 */
    private List<LabelValueBean> rng020groupList__ = null;
    /** ユーザ一覧 */
    private List<LabelValueBean> rng020userList__ = null;
    /** 承認経路一覧 */
    private List<LabelValueBean> rng020apprUserList__ = null;
    /** 最終確認一覧 */
    private List<LabelValueBean> rng020confirmUserList__ = null;
    /** 経路テンプレート一覧 */
    private List<LabelValueBean> rng020rctList__ = null;
    /** 添付ファイル一覧(テンプレートから取得) */
    private List<LabelValueBean> rng020templateFileList__ = null;

    /** 初期表示フラグ 0=初期 1=初期済み */
    private String rng020ScrollFlg__ = "0";

    /**
     * <p>rng020apprUser を取得します。
     * @return rng020apprUser
     */
    public String[] getRng020apprUser() {
        return rng020apprUser__;
    }
    /**
     * <p>rng020apprUser をセットします。
     * @param rng020apprUser rng020apprUser
     */
    public void setRng020apprUser(String[] rng020apprUser) {
        rng020apprUser__ = rng020apprUser;
    }
    /**
     * <p>rng020apprUserList を取得します。
     * @return rng020apprUserList
     */
    public List<LabelValueBean> getRng020apprUserList() {
        return rng020apprUserList__;
    }
    /**
     * <p>rng020apprUserList をセットします。
     * @param rng020apprUserList rng020apprUserList
     */
    public void setRng020apprUserList(List<LabelValueBean> rng020apprUserList) {
        rng020apprUserList__ = rng020apprUserList;
    }
    /**
     * <p>rng020confirmUser を取得します。
     * @return rng020confirmUser
     */
    public String[] getRng020confirmUser() {
        return rng020confirmUser__;
    }
    /**
     * <p>rng020confirmUser をセットします。
     * @param rng020confirmUser rng020confirmUser
     */
    public void setRng020confirmUser(String[] rng020confirmUser) {
        rng020confirmUser__ = rng020confirmUser;
    }
    /**
     * <p>rng020confirmUserList を取得します。
     * @return rng020confirmUserList
     */
    public List<LabelValueBean> getRng020confirmUserList() {
        return rng020confirmUserList__;
    }
    /**
     * <p>rng020confirmUserList をセットします。
     * @param rng020confirmUserList rng020confirmUserList
     */
    public void setRng020confirmUserList(List<LabelValueBean> rng020confirmUserList) {
        rng020confirmUserList__ = rng020confirmUserList;
    }
    /**
     * <p>rng020Content を取得します。
     * @return rng020Content
     */
    public String getRng020Content() {
        return rng020Content__;
    }
    /**
     * <p>rng020Content をセットします。
     * @param rng020Content rng020Content
     */
    public void setRng020Content(String rng020Content) {
        rng020Content__ = rng020Content;
    }
    /**
     * <p>rng020createDate を取得します。
     * @return rng020createDate
     */
    public String getRng020createDate() {
        return rng020createDate__;
    }
    /**
     * <p>rng020createDate をセットします。
     * @param rng020createDate rng020createDate
     */
    public void setRng020createDate(String rng020createDate) {
        rng020createDate__ = rng020createDate;
    }
    /**
     * <p>rng020files を取得します。
     * @return rng020files
     */
    public String[] getRng020files() {
        return rng020files__;
    }
    /**
     * <p>rng020files をセットします。
     * @param rng020files rng020files
     */
    public void setRng020files(String[] rng020files) {
        rng020files__ = rng020files;
    }
    /**
     * <p>rng020fileList を取得します。
     * @return rng020fileList
     */
    public List<LabelValueBean> getRng020fileList() {
        return rng020fileList__;
    }
    /**
     * <p>rng020fileList をセットします。
     * @param rng020fileList rng020fileList
     */
    public void setRng020fileList(List<LabelValueBean> rng020fileList) {
        rng020fileList__ = rng020fileList;
    }
    /**
     * <p>rng020group を取得します。
     * @return rng020group
     */
    public int getRng020group() {
        return rng020group__;
    }
    /**
     * <p>rng020group をセットします。
     * @param rng020group rng020group
     */
    public void setRng020group(int rng020group) {
        rng020group__ = rng020group;
    }
    /**
     * <p>rng020groupList を取得します。
     * @return rng020groupList
     */
    public List<LabelValueBean> getRng020groupList() {
        return rng020groupList__;
    }
    /**
     * <p>rng020groupList をセットします。
     * @param rng020groupList rng020groupList
     */
    public void setRng020groupList(List<LabelValueBean> rng020groupList) {
        rng020groupList__ = rng020groupList;
    }
    /**
     * <p>rng020Title を取得します。
     * @return rng020Title
     */
    public String getRng020Title() {
        return rng020Title__;
    }
    /**
     * <p>rng020Title をセットします。
     * @param rng020Title rng020Title
     */
    public void setRng020Title(String rng020Title) {
        rng020Title__ = rng020Title;
    }
    /**
     * <p>rng020requestUser を取得します。
     * @return rng020requestUser
     */
    public String getRng020requestUser() {
        return rng020requestUser__;
    }
    /**
     * <p>rng020requestUser をセットします。
     * @param rng020requestUser rng020requestUser
     */
    public void setRng020requestUser(String rng020requestUser) {
        rng020requestUser__ = rng020requestUser;
    }
    /**
     * <p>rng020selectApprUser を取得します。
     * @return rng020selectApprUser
     */
    public String[] getRng020selectApprUser() {
        return rng020selectApprUser__;
    }
    /**
     * <p>rng020selectApprUser をセットします。
     * @param rng020selectApprUser rng020selectApprUser
     */
    public void setRng020selectApprUser(String[] rng020selectApprUser) {
        rng020selectApprUser__ = rng020selectApprUser;
    }
    /**
     * <p>rng020selectConfirmUser を取得します。
     * @return rng020selectConfirmUser
     */
    public String[] getRng020selectConfirmUser() {
        return rng020selectConfirmUser__;
    }
    /**
     * <p>rng020selectConfirmUser をセットします。
     * @param rng020selectConfirmUser rng020selectConfirmUser
     */
    public void setRng020selectConfirmUser(String[] rng020selectConfirmUser) {
        rng020selectConfirmUser__ = rng020selectConfirmUser;
    }
    /**
     * <p>rng020userList を取得します。
     * @return rng020userList
     */
    public List<LabelValueBean> getRng020userList() {
        return rng020userList__;
    }
    /**
     * <p>rng020userList をセットします。
     * @param rng020userList rng020userList
     */
    public void setRng020userList(List<LabelValueBean> rng020userList) {
        rng020userList__ = rng020userList;
    }
    /**
     * <p>rng020users を取得します。
     * @return rng020users
     */
    public String[] getRng020users() {
        return rng020users__;
    }
    /**
     * <p>rng020users をセットします。
     * @param rng020users rng020users
     */
    public void setRng020users(String[] rng020users) {
        rng020users__ = rng020users;
    }
    /**
     * <p>rng020rctList を取得します。
     * @return rng020rctList
     */
    public List<LabelValueBean> getRng020rctList() {
        return rng020rctList__;
    }
    /**
     * <p>rng020rctList をセットします。
     * @param rng020rctList rng020rctList
     */
    public void setRng020rctList(List<LabelValueBean> rng020rctList) {
        rng020rctList__ = rng020rctList;
    }
    /**
     * <p>rng020copyApply を取得します。
     * @return rng020copyApply
     */
    public boolean isRng020copyApply() {
        return rng020copyApply__;
    }
    /**
     * <p>rng020copyApply をセットします。
     * @param rng020copyApply rng020copyApply
     */
    public void setRng020copyApply(boolean rng020copyApply) {
        rng020copyApply__ = rng020copyApply;
    }
    /**
     * <p>rng020rctSid を取得します。
     * @return rng020rctSid
     */
    public int getRng020rctSid() {
        return rng020rctSid__;
    }
    /**
     * <p>rng020rctSid をセットします。
     * @param rng020rctSid rng020rctSid
     */
    public void setRng020rctSid(int rng020rctSid) {
        rng020rctSid__ = rng020rctSid;
    }


    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param checkType 入力チェック種別
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheck(int checkType, HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        String title = gsMsg.getMessage(req, "cmn.title");
        String content = gsMsg.getMessage(req, "cmn.content");
        String keiro = gsMsg.getMessage(req, "rng.42");

        //-- タイトルチェック --
        errors = RngValidate.validateCmnFieldText(
                                                errors,
                                                title,
                                                rng020Title__,
                                                "rng020Title",
                                                RngConst.MAX_LENGTH_TITLE,
                                                true);

        //-- 内容チェック --
        errors = RngValidate.validateFieldTextArea(
                                                errors,
                                                content,
                                                rng020Content__,
                                                "rng020Content",
                                                RngConst.MAX_LENGTH_CONTENT,
                                                true);

        if (checkType == CHECKTYPE_REQUEST) {
            //入力チェック種別 = 申請の場合は承認経路のチェックを行う

            //承認経路未選択チェック
            if (rng020apprUser__ == null || rng020apprUser__.length < 1) {
                msg = new ActionMessage("error.select.required.text", keiro);
                StrutsUtil.addMessage(errors, msg, "rng020apprUser");

            }
        }

        return errors;
    }
    /**
     * <p>rng020RngTitle を取得します。
     * @return rng020RngTitle
     */
    public String getRng020RngTitle() {
        return rng020RngTitle__;
    }
    /**
     * <p>rng020RngTitle をセットします。
     * @param rng020RngTitle rng020RngTitle
     */
    public void setRng020RngTitle(String rng020RngTitle) {
        rng020RngTitle__ = rng020RngTitle;
    }

    /**
     * @return rng020ScrollFlg
     */
    public String getRng020ScrollFlg() {
        return rng020ScrollFlg__;
    }
    /**
     * @param rng020ScrollFlg 設定する rng020ScrollFlg
     */
    public void setRng020ScrollFlg(String rng020ScrollFlg) {
        rng020ScrollFlg__ = rng020ScrollFlg;
    }
    /**
     * <p>rng020requestUserId を取得します。
     * @return rng020requestUserId
     */
    public String getRng020requestUserId() {
        return rng020requestUserId__;
    }
    /**
     * <p>rng020requestUserId をセットします。
     * @param rng020requestUserId rng020requestUserId
     */
    public void setRng020requestUserId(String rng020requestUserId) {
        rng020requestUserId__ = rng020requestUserId;
    }
    /**
     * <p>rng020TemplateFileId を取得します。
     * @return rng020TemplateFileId
     */
    public String getRng020TemplateFileId() {
        return rng020TemplateFileId__;
    }
    /**
     * <p>rng020TemplateFileId をセットします。
     * @param rng020TemplateFileId rng020TemplateFileId
     */
    public void setRng020TemplateFileId(String rng020TemplateFileId) {
        rng020TemplateFileId__ = rng020TemplateFileId;
    }
    /**
     * <p>rng020templateFileList を取得します。
     * @return rng020templateFileList
     */
    public List<LabelValueBean> getRng020templateFileList() {
        return rng020templateFileList__;
    }
    /**
     * <p>rng020templateFileList をセットします。
     * @param rng020templateFileList rng020templateFileList
     */
    public void setRng020templateFileList(
            List<LabelValueBean> rng020templateFileList) {
        rng020templateFileList__ = rng020templateFileList;
    }
}
