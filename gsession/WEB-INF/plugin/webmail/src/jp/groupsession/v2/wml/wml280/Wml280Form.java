package jp.groupsession.v2.wml.wml280;

import java.util.List;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.wml.GSValidateWebmail;
import jp.groupsession.v2.wml.wml270.Wml270Form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] WEBメール 送信先リスト登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml280Form extends Wml270Form {

    /** ユーザ情報 or アクセス制御コンボ(選択用)のグループコンボに設定する値 グループ一覧のVALUE */
    public static final int GROUP_COMBO_VALUE = -9;
    /** 送信先リスト名 MAX文字数 */
    public static final int MAXLEN_DESTLIST_NAME = 100;
    /** 送信先リスト 備考 MAX文字数 */
    public static final int MAXLEN_DESTLIST_BIKO = 1000;

    /** 初期表示 */
    private int wml280initFlg__ = 0;
    /** 送信先リスト名 */
    private String wml280name__ = null;
    /** 備考 */
    private String wml280biko__ = null;
    /** 送信先 ユーザ情報 */
    private String[] wml280destUser__ = null;
    /** 送信先 アドレス帳情報 */
    private String[] wml280destAddress__ = null;

    /** アクセス権限 編集ユーザ */
    private String[] wml280accessFull__ = null;
    /** アクセス権限 閲覧ユーザ */
    private String[] wml280accessRead__ = null;

    /** 送信先 ユーザ情報(選択用) */
    private String[] wml280destUserSelect__  = null;
    /** 送信先 ユーザ情報一覧 */
    private List<Wml280AddressParamModel> destUserList__  = null;

    /** アクセス権限 グループ */
    private int wml280accessGroup__  = -1;
    /** アクセス権限 編集ユーザ(選択用) */
    private String[] wml280accessFullSelect__ = null;
    /** アクセス権限 閲覧ユーザ(選択用) */
    private String[] wml280accessReadSelect__ = null;
    /** アクセス権限(未選択用) */
    private String[] wml280accessNoSelect__ = null;

    /** グループコンボ */
    private List<LabelValueBean> groupCombo__ = null;

    /** アクセス権限 編集ユーザコンボ */
    private List<LabelValueBean> wml280accessFullSelectCombo__  = null;
    /** アクセス権限 閲覧ユーザコンボ */
    private List<LabelValueBean> wml280accessReadSelectCombo__  = null;
    /** アクセス権限 未選択コンボ */
    private List<LabelValueBean> wml280accessNoSelectCombo__  = null;

    //---------
    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return エラー
     */
    public ActionErrors validateCheck(RequestModel reqMdl) {
        ActionErrors errors = new ActionErrors();
        GsMessage gsMsg = new GsMessage(reqMdl);

        //送信先リスト名入力チェック
        GSValidateWebmail.validateTextBoxInput(errors, wml280name__,
                "wml280name",
                gsMsg.getMessage("wml.263"),
                MAXLEN_DESTLIST_NAME, true);

        //備考入力チェック
        GSValidateWebmail.validateTextarea(errors, wml280biko__,
                "wml280biko",
                gsMsg.getMessage("cmn.memo"),
                MAXLEN_DESTLIST_BIKO,
                false);

        //アクセス権限 設定チェック
        if ((wml280destUser__ == null || wml280destUser__.length <= 0)
        && (wml280destAddress__ == null || wml280destAddress__.length <= 0)) {
            String fieldfix = "wml280destUser.";
            String msgKey = "error.select.required.text";
            ActionMessage msg = new ActionMessage(msgKey,
                    gsMsg.getMessage("anp.send.dest"));
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
        }

        //アクセス権限 設定チェック
        if (wml280accessFull__ == null || wml280accessFull__.length <= 0) {
            String fieldfix = "wml280accessFull.";
            String msgKey = "error.select.required.text";
            ActionMessage msg = new ActionMessage(msgKey,
                    gsMsg.getMessage("fil.71"));
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);
        }

        return errors;
    }
    /**
     * <br>[機  能] 入力チェックを行う(送信先削除)
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return エラー
     */
    public ActionErrors validateDeleteDestCheck(RequestModel reqMdl) {
        ActionErrors errors = new ActionErrors();
        GsMessage gsMsg = new GsMessage(reqMdl);

        //送信先チェック
        if (wml280destUserSelect__ == null || wml280destUserSelect__.length <= 0) {
            String fieldfix = "wml280dest.";
            String msgKey = "error.select.required.text";
            ActionMessage msg = new ActionMessage(msgKey,
                    gsMsg.getMessage("anp.send.dest"));
            StrutsUtil.addMessage(
                    errors, msg, fieldfix + msgKey);

        }

        return errors;
    }

    /**
     * <br>[機  能] 共通メッセージ画面遷移時に保持するパラメータを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param msgForm 共通メッセージ画面Form
     */
    public void setHiddenParam(Cmn999Form msgForm) {
        super.setHiddenParam(msgForm);

        msgForm.addHiddenParam("wmlEditDestList", getWmlEditDestList());
        msgForm.addHiddenParam("wml280initFlg", getWml280initFlg());
        msgForm.addHiddenParam("wml280name", getWml280name());
        msgForm.addHiddenParam("wml280biko", getWml280biko());
        msgForm.addHiddenParam("wml280destUser", getWml280destUser());
        msgForm.addHiddenParam("wml280destAddress", getWml280destAddress());
        msgForm.addHiddenParam("wml280accessFull", getWml280accessFull());
        msgForm.addHiddenParam("wml280accessRead", getWml280accessRead());
        msgForm.addHiddenParam("wml280accessGroup", getWml280accessGroup());
    }
    /**
     * <p>wml280initFlg を取得します。
     * @return wml280initFlg
     */
    public int getWml280initFlg() {
        return wml280initFlg__;
    }
    /**
     * <p>wml280initFlg をセットします。
     * @param wml280initFlg wml280initFlg
     */
    public void setWml280initFlg(int wml280initFlg) {
        wml280initFlg__ = wml280initFlg;
    }
    /**
     * <p>wml280name を取得します。
     * @return wml280name
     */
    public String getWml280name() {
        return wml280name__;
    }
    /**
     * <p>wml280name をセットします。
     * @param wml280name wml280name
     */
    public void setWml280name(String wml280name) {
        wml280name__ = wml280name;
    }
    /**
     * <p>wml280biko を取得します。
     * @return wml280biko
     */
    public String getWml280biko() {
        return wml280biko__;
    }
    /**
     * <p>wml280biko をセットします。
     * @param wml280biko wml280biko
     */
    public void setWml280biko(String wml280biko) {
        wml280biko__ = wml280biko;
    }
    /**
     * <p>wml280destUser を取得します。
     * @return wml280destUser
     */
    public String[] getWml280destUser() {
        return wml280destUser__;
    }
    /**
     * <p>wml280destUser をセットします。
     * @param wml280destUser wml280destUser
     */
    public void setWml280destUser(String[] wml280destUser) {
        wml280destUser__ = wml280destUser;
    }
    /**
     * <p>wml280destAddress を取得します。
     * @return wml280destAddress
     */
    public String[] getWml280destAddress() {
        return wml280destAddress__;
    }
    /**
     * <p>wml280destAddress をセットします。
     * @param wml280destAddress wml280destAddress
     */
    public void setWml280destAddress(String[] wml280destAddress) {
        wml280destAddress__ = wml280destAddress;
    }
    /**
     * <p>wml280accessFull を取得します。
     * @return wml280accessFull
     */
    public String[] getWml280accessFull() {
        return wml280accessFull__;
    }
    /**
     * <p>wml280accessFull をセットします。
     * @param wml280accessFull wml280accessFull
     */
    public void setWml280accessFull(String[] wml280accessFull) {
        wml280accessFull__ = wml280accessFull;
    }
    /**
     * <p>wml280accessRead を取得します。
     * @return wml280accessRead
     */
    public String[] getWml280accessRead() {
        return wml280accessRead__;
    }
    /**
     * <p>wml280accessRead をセットします。
     * @param wml280accessRead wml280accessRead
     */
    public void setWml280accessRead(String[] wml280accessRead) {
        wml280accessRead__ = wml280accessRead;
    }
    /**
     * <p>wml280destUserSelect を取得します。
     * @return wml280destUserSelect
     */
    public String[] getWml280destUserSelect() {
        return wml280destUserSelect__;
    }
    /**
     * <p>wml280destUserSelect をセットします。
     * @param wml280destUserSelect wml280destUserSelect
     */
    public void setWml280destUserSelect(String[] wml280destUserSelect) {
        wml280destUserSelect__ = wml280destUserSelect;
    }
    /**
     * <p>destUserList を取得します。
     * @return destUserList
     */
    public List<Wml280AddressParamModel> getDestUserList() {
        return destUserList__;
    }
    /**
     * <p>destUserList をセットします。
     * @param destUserList destUserList
     */
    public void setDestUserList(List<Wml280AddressParamModel> destUserList) {
        destUserList__ = destUserList;
    }
    /**
     * <p>wml280accessGroup を取得します。
     * @return wml280accessGroup
     */
    public int getWml280accessGroup() {
        return wml280accessGroup__;
    }
    /**
     * <p>wml280accessGroup をセットします。
     * @param wml280accessGroup wml280accessGroup
     */
    public void setWml280accessGroup(int wml280accessGroup) {
        wml280accessGroup__ = wml280accessGroup;
    }
    /**
     * <p>wml280accessFullSelect を取得します。
     * @return wml280accessFullSelect
     */
    public String[] getWml280accessFullSelect() {
        return wml280accessFullSelect__;
    }
    /**
     * <p>wml280accessFullSelect をセットします。
     * @param wml280accessFullSelect wml280accessFullSelect
     */
    public void setWml280accessFullSelect(String[] wml280accessFullSelect) {
        wml280accessFullSelect__ = wml280accessFullSelect;
    }
    /**
     * <p>wml280accessReadSelect を取得します。
     * @return wml280accessReadSelect
     */
    public String[] getWml280accessReadSelect() {
        return wml280accessReadSelect__;
    }
    /**
     * <p>wml280accessReadSelect をセットします。
     * @param wml280accessReadSelect wml280accessReadSelect
     */
    public void setWml280accessReadSelect(String[] wml280accessReadSelect) {
        wml280accessReadSelect__ = wml280accessReadSelect;
    }
    /**
     * <p>wml280accessNoSelect を取得します。
     * @return wml280accessNoSelect
     */
    public String[] getWml280accessNoSelect() {
        return wml280accessNoSelect__;
    }
    /**
     * <p>wml280accessNoSelect をセットします。
     * @param wml280accessNoSelect wml280accessNoSelect
     */
    public void setWml280accessNoSelect(String[] wml280accessNoSelect) {
        wml280accessNoSelect__ = wml280accessNoSelect;
    }
    /**
     * <p>groupCombo を取得します。
     * @return groupCombo
     */
    public List<LabelValueBean> getGroupCombo() {
        return groupCombo__;
    }
    /**
     * <p>groupCombo をセットします。
     * @param groupCombo groupCombo
     */
    public void setGroupCombo(List<LabelValueBean> groupCombo) {
        groupCombo__ = groupCombo;
    }
    /**
     * <p>wml280accessFullSelectCombo を取得します。
     * @return wml280accessFullSelectCombo
     */
    public List<LabelValueBean> getWml280accessFullSelectCombo() {
        return wml280accessFullSelectCombo__;
    }
    /**
     * <p>wml280accessFullSelectCombo をセットします。
     * @param wml280accessFullSelectCombo wml280accessFullSelectCombo
     */
    public void setWml280accessFullSelectCombo(
            List<LabelValueBean> wml280accessFullSelectCombo) {
        wml280accessFullSelectCombo__ = wml280accessFullSelectCombo;
    }
    /**
     * <p>wml280accessReadSelectCombo を取得します。
     * @return wml280accessReadSelectCombo
     */
    public List<LabelValueBean> getWml280accessReadSelectCombo() {
        return wml280accessReadSelectCombo__;
    }
    /**
     * <p>wml280accessReadSelectCombo をセットします。
     * @param wml280accessReadSelectCombo wml280accessReadSelectCombo
     */
    public void setWml280accessReadSelectCombo(
            List<LabelValueBean> wml280accessReadSelectCombo) {
        wml280accessReadSelectCombo__ = wml280accessReadSelectCombo;
    }
    /**
     * <p>wml280accessNoSelectCombo を取得します。
     * @return wml280accessNoSelectCombo
     */
    public List<LabelValueBean> getWml280accessNoSelectCombo() {
        return wml280accessNoSelectCombo__;
    }
    /**
     * <p>wml280accessNoSelectCombo をセットします。
     * @param wml280accessNoSelectCombo wml280accessNoSelectCombo
     */
    public void setWml280accessNoSelectCombo(
            List<LabelValueBean> wml280accessNoSelectCombo) {
        wml280accessNoSelectCombo__ = wml280accessNoSelectCombo;
    }
}
