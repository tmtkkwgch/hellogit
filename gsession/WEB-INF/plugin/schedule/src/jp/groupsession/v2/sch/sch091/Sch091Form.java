package jp.groupsession.v2.sch.sch091;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.sch.sch100.Sch100Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール 個人設定 初期値設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch091Form extends Sch100Form {

    /** 開始 時 */
    private int sch091DefFrH__ = -1;
    /** 開始 分 */
    private int sch091DefFrM__ = -1;
    /** 終了 時 */
    private int sch091DefToH__ = -1;
    /** 終了 分 */
    private int sch091DefToM__ = -1;
    /** 公開フラグ */
    private int sch091PubFlg__ = GSConstSchedule.DSP_PUBLIC;
    /** タイトル色 */
    private String sch091Fcolor__ = null;
    /** ラベル 時 */
    private List < LabelValueBean > sch091HourLabel__ = null;
    /** ラベル 分 */
    private List < LabelValueBean > sch091MinuteLabel__ = null;
    /** 編集権限 */
    private int sch091Edit__ = GSConstSchedule.EDIT_CONF_NONE;
    /** カラーコメントリスト */
    private ArrayList < String > sch091ColorMsgList__ = null;
    /** 同時修正 */
    private int sch091Same__ = GSConstSchedule.SAME_EDIT_ON;
    /** 編集権限 編集許可 */
    private boolean sch091EditFlg__ = false;
    /** 公開区分 編集許可 */
    private boolean sch091PublicFlg__ = false;
    /** 同時修正 編集許可 */
    private boolean sch091SameFlg__ = false;
    /** 初期表示フラグ */
    private int sch091initFlg__ = 0;

    /** タイトル色区分 */
    private int sch091colorKbn__ = GSConstSchedule.SAD_MSG_NO_ADD;

    /**
     * <p>sch091ColorMsgList を取得します。
     * @return sch091ColorMsgList
     */
    public ArrayList<String> getSch091ColorMsgList() {
        return sch091ColorMsgList__;
    }

    /**
     * <p>sch091ColorMsgList をセットします。
     * @param sch091ColorMsgList sch091ColorMsgList
     */
    public void setSch091ColorMsgList(ArrayList<String> sch091ColorMsgList) {
        sch091ColorMsgList__ = sch091ColorMsgList;
    }

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Sch091Form() {
    }

    /**
     * <p>sch091Edit を取得します。
     * @return sch091Edit
     */
    public int getSch091Edit() {
        return sch091Edit__;
    }

    /**
     * <p>sch091Edit をセットします。
     * @param sch091Edit sch091Edit
     */
    public void setSch091Edit(int sch091Edit) {
        sch091Edit__ = sch091Edit;
    }

    /**
     * <p>sch091Fcolor を取得します。
     * @return sch091Fcolor
     */
    public String getSch091Fcolor() {
        return sch091Fcolor__;
    }

    /**
     * <p>sch091Fcolor をセットします。
     * @param sch091Fcolor sch091Fcolor
     */
    public void setSch091Fcolor(String sch091Fcolor) {
        sch091Fcolor__ = sch091Fcolor;
    }

    /**
     * <p>sch091DefFrH を取得します。
     * @return sch091DefFrH
     */
    public int getSch091DefFrH() {
        return sch091DefFrH__;
    }

    /**
     * <p>sch091DefFrH をセットします。
     * @param sch091DefFrH sch091DefFrH
     */
    public void setSch091DefFrH(int sch091DefFrH) {
        sch091DefFrH__ = sch091DefFrH;
    }

    /**
     * <p>sch091DefFrM を取得します。
     * @return sch091DefFrM
     */
    public int getSch091DefFrM() {
        return sch091DefFrM__;
    }

    /**
     * <p>sch091DefFrM をセットします。
     * @param sch091DefFrM sch091DefFrM
     */
    public void setSch091DefFrM(int sch091DefFrM) {
        sch091DefFrM__ = sch091DefFrM;
    }

    /**
     * <p>sch091DefToH を取得します。
     * @return sch091DefToH
     */
    public int getSch091DefToH() {
        return sch091DefToH__;
    }

    /**
     * <p>sch091DefToH をセットします。
     * @param sch091DefToH sch091DefToH
     */
    public void setSch091DefToH(int sch091DefToH) {
        sch091DefToH__ = sch091DefToH;
    }

    /**
     * <p>sch091DefToM を取得します。
     * @return sch091DefToM
     */
    public int getSch091DefToM() {
        return sch091DefToM__;
    }

    /**
     * <p>sch091DefToM をセットします。
     * @param sch091DefToM sch091DefToM
     */
    public void setSch091DefToM(int sch091DefToM) {
        sch091DefToM__ = sch091DefToM;
    }

    /**
     * <p>sch091HourLabel を取得します。
     * @return sch091HourLabel
     */
    public List<LabelValueBean> getSch091HourLabel() {
        return sch091HourLabel__;
    }

    /**
     * <p>sch091HourLabel をセットします。
     * @param sch091HourLabel sch091HourLabel
     */
    public void setSch091HourLabel(List<LabelValueBean> sch091HourLabel) {
        sch091HourLabel__ = sch091HourLabel;
    }

    /**
     * <p>sch091MinuteLabel を取得します。
     * @return sch091MinuteLabel
     */
    public List<LabelValueBean> getSch091MinuteLabel() {
        return sch091MinuteLabel__;
    }

    /**
     * <p>sch091MinuteLabel をセットします。
     * @param sch091MinuteLabel sch091MinuteLabel
     */
    public void setSch091MinuteLabel(List<LabelValueBean> sch091MinuteLabel) {
        sch091MinuteLabel__ = sch091MinuteLabel;
    }

    /**
     * <p>sch091PubFlg を取得します。
     * @return sch091PubFlg
     */
    public int getSch091PubFlg() {
        return sch091PubFlg__;
    }

    /**
     * <p>sch091PubFlg をセットします。
     * @param sch091PubFlg sch091PubFlg
     */
    public void setSch091PubFlg(int sch091PubFlg) {
        sch091PubFlg__ = sch091PubFlg;
    }

    /**
     * <p>sch091EditFlg を取得します。
     * @return sch091EditFlg
     */
    public boolean isSch091EditFlg() {
        return sch091EditFlg__;
    }

    /**
     * <p>sch091EditFlg をセットします。
     * @param sch091EditFlg sch091EditFlg
     */
    public void setSch091EditFlg(boolean sch091EditFlg) {
        sch091EditFlg__ = sch091EditFlg;
    }

    /**
     * <p>sch091Same を取得します。
     * @return sch091Same
     */
    public int getSch091Same() {
        return sch091Same__;
    }

    /**
     * <p>sch091Same をセットします。
     * @param sch091Same sch091Same
     */
    public void setSch091Same(int sch091Same) {
        sch091Same__ = sch091Same;
    }

    /**
     * <p>sch091initFlg を取得します。
     * @return sch091initFlg
     */
    public int getSch091initFlg() {
        return sch091initFlg__;
    }

    /**
     * <p>sch091initFlg をセットします。
     * @param sch091initFlg sch091initFlg
     */
    public void setSch091initFlg(int sch091initFlg) {
        sch091initFlg__ = sch091initFlg;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheck(ActionMapping map, HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        //時間は特にエラーチェックなし

        GsMessage gsMsg = new GsMessage();
        //メッセージ 公開
        String koukai = gsMsg.getMessage(req, "cmn.public");
        //メッセージ 同時修正
        String douji = gsMsg.getMessage(req, "schedule.32");

        //公開フラグ
        if (sch091PubFlg__ != GSConstSchedule.DSP_PUBLIC
                && sch091PubFlg__ != GSConstSchedule.DSP_NOT_PUBLIC
                && sch091PubFlg__ != GSConstSchedule.DSP_YOTEIARI
                && sch091PubFlg__ != GSConstSchedule.DSP_BELONG_GROUP) {
            String prefix = "sch091PubFlg.";
            msg = new ActionMessage("tcd.105", koukai);
            errors.add(prefix + "tcd.105", msg);
        }

        //同時修正フラグ
        if (sch091Same__ != GSConstSchedule.SAME_EDIT_OFF
                && sch091Same__ != GSConstSchedule.SAME_EDIT_ON) {
            String prefix = "sch091Same.";
            msg = new ActionMessage("tcd.105", douji);
            errors.add(prefix + "tcd.105", msg);
        }

        return errors;
    }

    /**
     * <p>sch091colorKbn を取得します。
     * @return sch091colorKbn
     */
    public int getSch091colorKbn() {
        return sch091colorKbn__;
    }

    /**
     * <p>sch091colorKbn をセットします。
     * @param sch091colorKbn sch091colorKbn
     */
    public void setSch091colorKbn(int sch091colorKbn) {
        sch091colorKbn__ = sch091colorKbn;
    }

    /**
     * <p>sch091PublicFlg を取得します。
     * @return sch091PublicFlg
     */
    public boolean isSch091PublicFlg() {
        return sch091PublicFlg__;
    }

    /**
     * <p>sch091PublicFlg をセットします。
     * @param sch091PublicFlg sch091PublicFlg
     */
    public void setSch091PublicFlg(boolean sch091PublicFlg) {
        sch091PublicFlg__ = sch091PublicFlg;
    }

    /**
     * <p>sch091SameFlg を取得します。
     * @return sch091SameFlg
     */
    public boolean isSch091SameFlg() {
        return sch091SameFlg__;
    }

    /**
     * <p>sch091SameFlg をセットします。
     * @param sch091SameFlg sch091SameFlg
     */
    public void setSch091SameFlg(boolean sch091SameFlg) {
        sch091SameFlg__ = sch091SameFlg;
    }
}
