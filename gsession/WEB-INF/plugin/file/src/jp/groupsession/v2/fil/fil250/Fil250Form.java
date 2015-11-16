package jp.groupsession.v2.fil.fil250;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.fil200.Fil200Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 管理者設定 更新通知一括設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil250Form extends Fil200Form {

    /** 選択キャビネットSID */
    private String fil250SltCabinetSid__ = null;
    /** 削除ディレクトリSID */
    private String fil250DirSid__ = null;
    /** ディレクトリパス */
    private String fil250DirPath__ = null;
    /** ルートディレクトリパス */
    private String fil250RootDirSid__ = null;
    /** ルートディレクトリ名 */
    private String fil250RootDirName__ = null;

    /** 更新通知対象者 */
    private String[] fil250callUser__ = null;
    /** 更新通知対象者(保持) */
    private String[] fil250SvCallUser__ = null;
    /** 更新通知対象者(候補) */
    private String[] fil250callUserRight__ = null;
    /** 更新通知対象者(候補) グループ*/
    private String fil250callUserSltGroup__ = null;
    /** 更新通知対象者(候補) 選択checkbox*/
    private String fil250callUserAllSlt__ = GSConstFile.DSP_KBN_OFF;

    /** キャビネットリスト */
    private List<LabelValueBean> fil250cabinetList__ = new ArrayList<LabelValueBean>();

    /** 更新通知対象者 ユーザリスト */
    private ArrayList<LabelValueBean> fil250callUserCombo__ = null;
    /** 更新通知対象者 グループコンボ */
    private ArrayList< LabelValueBean > fil250callUserGroupCombo__ = null;
    /** 更新通知対象者 候補リスト */
    private ArrayList< LabelValueBean > fil250callUserRightCombo__ = null;


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

        //キャビネット
        if (fil250SltCabinetSid__ == null || fil250SltCabinetSid__.equals("-1")) {
            String textCabinet = gsMsg.getMessage(req, "fil.23");
            msg = new ActionMessage("error.select.required.text", textCabinet);
            StrutsUtil.addMessage(errors, msg, "fil250SltCabinetSid");
        }

        //ディレクトリ
        if (StringUtil.isNullZeroString(fil250DirSid__)) {
            String textFolder = gsMsg.getMessage(req, "cmn.folder");
            msg = new ActionMessage("error.select.required.text", textFolder);
            StrutsUtil.addMessage(errors, msg, "fil250DirSid");
        }

        //更新通知対象者
        if (fil250SvCallUser__ == null || fil250SvCallUser__.length < 1) {
            String textCallUser = gsMsg.getMessage(req, "fil.125");
            msg = new ActionMessage("error.select.required.text", textCallUser);
            StrutsUtil.addMessage(errors, msg, "fil250SvCallUser");
        }
        return errors;
    }

    /**
     * <p>fil250cabinetList を取得します。
     * @return fil250cabinetList
     */
    public List<LabelValueBean> getFil250cabinetList() {
        return fil250cabinetList__;
    }

    /**
     * <p>fil250cabinetList をセットします。
     * @param fil250cabinetList fil250cabinetList
     */
    public void setFil250cabinetList(List<LabelValueBean> fil250cabinetList) {
        fil250cabinetList__ = fil250cabinetList;
    }

    /**
     * <p>fil250DirSid を取得します。
     * @return fil250DirSid
     */
    public String getFil250DirSid() {
        return fil250DirSid__;
    }

    /**
     * <p>fil250DirSid をセットします。
     * @param fil250DirSid fil250DirSid
     */
    public void setFil250DirSid(String fil250DirSid) {
        fil250DirSid__ = fil250DirSid;
    }

    /**
     * <p>fil250SltCabinetSid を取得します。
     * @return fil250SltCabinetSid
     */
    public String getFil250SltCabinetSid() {
        return fil250SltCabinetSid__;
    }

    /**
     * <p>fil250SltCabinetSid をセットします。
     * @param fil250SltCabinetSid fil250SltCabinetSid
     */
    public void setFil250SltCabinetSid(String fil250SltCabinetSid) {
        fil250SltCabinetSid__ = fil250SltCabinetSid;
    }

    /**
     * <p>fil250DirPath を取得します。
     * @return fil250DirPath
     */
    public String getFil250DirPath() {
        return fil250DirPath__;
    }

    /**
     * <p>fil250DirPath をセットします。
     * @param fil250DirPath fil250DirPath
     */
    public void setFil250DirPath(String fil250DirPath) {
        fil250DirPath__ = fil250DirPath;
    }

    /**
     * <p>fil250RootDirSid を取得します。
     * @return fil250RootDirSid
     */
    public String getFil250RootDirSid() {
        return fil250RootDirSid__;
    }

    /**
     * <p>fil250RootDirSid をセットします。
     * @param fil250RootDirSid fil250RootDirSid
     */
    public void setFil250RootDirSid(String fil250RootDirSid) {
        fil250RootDirSid__ = fil250RootDirSid;
    }

    /**
     * <p>fil250RootDirName を取得します。
     * @return fil250RootDirName
     */
    public String getFil250RootDirName() {
        return fil250RootDirName__;
    }

    /**
     * <p>fil250RootDirName をセットします。
     * @param fil250RootDirName fil250RootDirName
     */
    public void setFil250RootDirName(String fil250RootDirName) {
        fil250RootDirName__ = fil250RootDirName;
    }

    /**
     * <p>fil250callUser を取得します。
     * @return fil250callUser
     */
    public String[] getFil250callUser() {
        return fil250callUser__;
    }

    /**
     * <p>fil250callUser をセットします。
     * @param fil250callUser fil250callUser
     */
    public void setFil250callUser(String[] fil250callUser) {
        fil250callUser__ = fil250callUser;
    }

    /**
     * <p>fil250callUserAllSlt を取得します。
     * @return fil250callUserAllSlt
     */
    public String getFil250callUserAllSlt() {
        return fil250callUserAllSlt__;
    }

    /**
     * <p>fil250callUserAllSlt をセットします。
     * @param fil250callUserAllSlt fil250callUserAllSlt
     */
    public void setFil250callUserAllSlt(String fil250callUserAllSlt) {
        fil250callUserAllSlt__ = fil250callUserAllSlt;
    }

    /**
     * <p>fil250callUserRight を取得します。
     * @return fil250callUserRight
     */
    public String[] getFil250callUserRight() {
        return fil250callUserRight__;
    }

    /**
     * <p>fil250callUserRight をセットします。
     * @param fil250callUserRight fil250callUserRight
     */
    public void setFil250callUserRight(String[] fil250callUserRight) {
        fil250callUserRight__ = fil250callUserRight;
    }

    /**
     * <p>fil250callUserSltGroup を取得します。
     * @return fil250callUserSltGroup
     */
    public String getFil250callUserSltGroup() {
        return fil250callUserSltGroup__;
    }

    /**
     * <p>fil250callUserSltGroup をセットします。
     * @param fil250callUserSltGroup fil250callUserSltGroup
     */
    public void setFil250callUserSltGroup(String fil250callUserSltGroup) {
        fil250callUserSltGroup__ = fil250callUserSltGroup;
    }

    /**
     * <p>fil250SvCallUser を取得します。
     * @return fil250SvCallUser
     */
    public String[] getFil250SvCallUser() {
        return fil250SvCallUser__;
    }

    /**
     * <p>fil250SvCallUser をセットします。
     * @param fil250SvCallUser fil250SvCallUser
     */
    public void setFil250SvCallUser(String[] fil250SvCallUser) {
        fil250SvCallUser__ = fil250SvCallUser;
    }

    /**
     * <p>fil250callUserCombo を取得します。
     * @return fil250callUserCombo
     */
    public ArrayList<LabelValueBean> getFil250callUserCombo() {
        return fil250callUserCombo__;
    }

    /**
     * <p>fil250callUserCombo をセットします。
     * @param fil250callUserCombo fil250callUserCombo
     */
    public void setFil250callUserCombo(ArrayList<LabelValueBean> fil250callUserCombo) {
        fil250callUserCombo__ = fil250callUserCombo;
    }

    /**
     * <p>fil250callUserGroupCombo を取得します。
     * @return fil250callUserGroupCombo
     */
    public ArrayList<LabelValueBean> getFil250callUserGroupCombo() {
        return fil250callUserGroupCombo__;
    }

    /**
     * <p>fil250callUserGroupCombo をセットします。
     * @param fil250callUserGroupCombo fil250callUserGroupCombo
     */
    public void setFil250callUserGroupCombo(
            ArrayList<LabelValueBean> fil250callUserGroupCombo) {
        fil250callUserGroupCombo__ = fil250callUserGroupCombo;
    }

    /**
     * <p>fil250callUserRightCombo を取得します。
     * @return fil250callUserRightCombo
     */
    public ArrayList<LabelValueBean> getFil250callUserRightCombo() {
        return fil250callUserRightCombo__;
    }

    /**
     * <p>fil250callUserRightCombo をセットします。
     * @param fil250callUserRightCombo fil250callUserRightCombo
     */
    public void setFil250callUserRightCombo(
            ArrayList<LabelValueBean> fil250callUserRightCombo) {
        fil250callUserRightCombo__ = fil250callUserRightCombo;
    }

}
