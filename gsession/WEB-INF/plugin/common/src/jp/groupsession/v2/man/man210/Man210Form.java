package jp.groupsession.v2.man.man210;

import java.util.ArrayList;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.AbstractGsForm;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 管理者設定 モバイル使用一括設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man210Form extends AbstractGsForm {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man210Form.class);

    /** 対象区分 0 = 全 1 = 指定 */
    private int man210ObjKbn__ = 0;
    /** モバイル使用区分 0 = 可 1 = 不可 */
    private int man210UseKbn__ = 0;
    /** 固体識別番号制御 */
    private String man210NumCont__ = String.valueOf(GSConstUser.UID_DOESNT_CONTROL);
    /** 固体識別番号自動登録 */
    private String man210NumAutAdd__ = String.valueOf(GSConstUser.UID_AUTO_REG_NO);

    /** グループ */
    private String man210groupSid__ = null;
    /** 追加用メンバー(選択中) */
    private String[] man210addUserSid__ = null;
    /** 現在選択中のメンバー(コンボ表示に使用する値) */
    private String[] man210userSid__ = null;
    /** 現在選択中のメンバー(コンボで選択中) */
    private String[] man210selectUserSid__ = null;
    /** グループコンボ */
    private ArrayList<LabelValueBean> man210GpLabelList__ = null;
    /** 現在選択中のメンバーコンボ */
    private ArrayList<LabelValueBean> man210MbLabelList__ = null;
    /** 追加用メンバーコンボ */
    private ArrayList<LabelValueBean> man210AdLabelList__ = null;
    /**
     * <p>man210NumAutAdd を取得します。
     * @return man210NumAutAdd
     */
    public String getMan210NumAutAdd() {
        return man210NumAutAdd__;
    }
    /**
     * <p>man210NumAutAdd をセットします。
     * @param man210NumAutAdd man210NumAutAdd
     */
    public void setMan210NumAutAdd(String man210NumAutAdd) {
        man210NumAutAdd__ = man210NumAutAdd;
    }
    /**
     * <p>man210NumCont を取得します。
     * @return man210NumCont
     */
    public String getMan210NumCont() {
        return man210NumCont__;
    }
    /**
     * <p>man210NumCont をセットします。
     * @param man210NumCont man210NumCont
     */
    public void setMan210NumCont(String man210NumCont) {
        man210NumCont__ = man210NumCont;
    }
    /**
     * <p>man210ObjKbn を取得します。
     * @return man210ObjKbn
     */
    public int getMan210ObjKbn() {
        return man210ObjKbn__;
    }
    /**
     * <p>man210ObjKbn をセットします。
     * @param man210ObjKbn man210ObjKbn
     */
    public void setMan210ObjKbn(int man210ObjKbn) {
        man210ObjKbn__ = man210ObjKbn;
    }
    /**
     * <p>man210UseKbn を取得します。
     * @return man210UseKbn
     */
    public int getMan210UseKbn() {
        return man210UseKbn__;
    }
    /**
     * <p>man210UseKbn をセットします。
     * @param man210UseKbn man210UseKbn
     */
    public void setMan210UseKbn(int man210UseKbn) {
        man210UseKbn__ = man210UseKbn;
    }
    /**
     * <p>man210addUserSid を取得します。
     * @return man210addUserSid
     */
    public String[] getMan210addUserSid() {
        return man210addUserSid__;
    }
    /**
     * <p>man210addUserSid をセットします。
     * @param man210addUserSid man210addUserSid
     */
    public void setMan210addUserSid(String[] man210addUserSid) {
        man210addUserSid__ = man210addUserSid;
    }
    /**
     * <p>man210AdLabelList を取得します。
     * @return man210AdLabelList
     */
    public ArrayList<LabelValueBean> getMan210AdLabelList() {
        return man210AdLabelList__;
    }
    /**
     * <p>man210AdLabelList をセットします。
     * @param man210AdLabelList man210AdLabelList
     */
    public void setMan210AdLabelList(ArrayList<LabelValueBean> man210AdLabelList) {
        man210AdLabelList__ = man210AdLabelList;
    }
    /**
     * <p>man210GpLabelList を取得します。
     * @return man210GpLabelList
     */
    public ArrayList<LabelValueBean> getMan210GpLabelList() {
        return man210GpLabelList__;
    }
    /**
     * <p>man210GpLabelList をセットします。
     * @param man210GpLabelList man210GpLabelList
     */
    public void setMan210GpLabelList(ArrayList<LabelValueBean> man210GpLabelList) {
        man210GpLabelList__ = man210GpLabelList;
    }
    /**
     * <p>man210groupSid を取得します。
     * @return man210groupSid
     */
    public String getMan210groupSid() {
        return man210groupSid__;
    }
    /**
     * <p>man210groupSid をセットします。
     * @param man210groupSid man210groupSid
     */
    public void setMan210groupSid(String man210groupSid) {
        man210groupSid__ = man210groupSid;
    }
    /**
     * <p>man210MbLabelList を取得します。
     * @return man210MbLabelList
     */
    public ArrayList<LabelValueBean> getMan210MbLabelList() {
        return man210MbLabelList__;
    }
    /**
     * <p>man210MbLabelList をセットします。
     * @param man210MbLabelList man210MbLabelList
     */
    public void setMan210MbLabelList(ArrayList<LabelValueBean> man210MbLabelList) {
        man210MbLabelList__ = man210MbLabelList;
    }
    /**
     * <p>man210selectUserSid を取得します。
     * @return man210selectUserSid
     */
    public String[] getMan210selectUserSid() {
        return man210selectUserSid__;
    }
    /**
     * <p>man210selectUserSid をセットします。
     * @param man210selectUserSid man210selectUserSid
     */
    public void setMan210selectUserSid(String[] man210selectUserSid) {
        man210selectUserSid__ = man210selectUserSid;
    }
    /**
     * <p>man210userSid を取得します。
     * @return man210userSid
     */
    public String[] getMan210userSid() {
        return man210userSid__;
    }
    /**
     * <p>man210userSid をセットします。
     * @param man210userSid man210userSid
     */
    public void setMan210userSid(String[] man210userSid) {
        man210userSid__ = man210userSid;
    }
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return エラー
     */
    public ActionErrors validateCheck(RequestModel reqMdl) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        log__.debug("入力チェック開始");
        GsMessage gsMsg = new GsMessage(reqMdl);
        String eprefix = "man210userSid.";
        if (man210userSid__ == null) {
            //未入力チェック
            msg = new ActionMessage("error.select.required.text",
                    gsMsg.getMessage("cmn.named.user"));

            StrutsUtil.addMessage(errors, msg, eprefix
                    +  "error.select.required.text");
        }
        log__.debug("入力チェック終了");
        return errors;
    }
}