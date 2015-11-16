package jp.groupsession.v2.anp.anp021;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.anp.AbstractAnpiMobileForm;
import jp.groupsession.v2.anp.AnpiValidateUtil;
import jp.groupsession.v2.anp.GSConstAnpi;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] 安否状況入力画面[モバイル版]のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp021Form extends AbstractAnpiMobileForm {

    /** 安否状況 */
    private String anp021JokyoFlg__;
    /** 現在地 */
    private String anp021PlaceFlg__;
    /** 出社状況 */
    private String anp021SyusyaFlg__;
    /** コメント */
    private String anp021Comment__;
    /** 訓練モードフラグ */
    private int anp021KnrenFlg__ = 0;
    /** 初期表示フラグ */
    private int anp021initFlg__ = 0;
    /**
     * <p>anp021JokyoFlg を取得します。
     * @return anp021JokyoFlg
     */
    public String getAnp021JokyoFlg() {
        return anp021JokyoFlg__;
    }
    /**
     * <p>anp021JokyoFlg をセットします。
     * @param anp021JokyoFlg anp021JokyoFlg
     */
    public void setAnp021JokyoFlg(String anp021JokyoFlg) {
        anp021JokyoFlg__ = anp021JokyoFlg;
    }
    /**
     * <p>anp021PlaceFlg を取得します。
     * @return anp021PlaceFlg
     */
    public String getAnp021PlaceFlg() {
        return anp021PlaceFlg__;
    }
    /**
     * <p>anp021PlaceFlg をセットします。
     * @param anp021PlaceFlg anp021PlaceFlg
     */
    public void setAnp021PlaceFlg(String anp021PlaceFlg) {
        anp021PlaceFlg__ = anp021PlaceFlg;
    }
    /**
     * <p>anp021SyusyaFlg を取得します。
     * @return anp021SyusyaFlg
     */
    public String getAnp021SyusyaFlg() {
        return anp021SyusyaFlg__;
    }
    /**
     * <p>anp021SyusyaFlg をセットします。
     * @param anp021SyusyaFlg anp021SyusyaFlg
     */
    public void setAnp021SyusyaFlg(String anp021SyusyaFlg) {
        anp021SyusyaFlg__ = anp021SyusyaFlg;
    }
    /**
     * <p>anp021Comment を取得します。
     * @return anp021Comment
     */
    public String getAnp021Comment() {
        return anp021Comment__;
    }
    /**
     * <p>anp021Comment をセットします。
     * @param anp021Comment anp021Comment
     */
    public void setAnp021Comment(String anp021Comment) {
        anp021Comment__ = anp021Comment;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return エラー
     */
    public ActionErrors validateAnp021(RequestModel reqMdl) {
        ActionErrors errors = new ActionErrors();

        GsMessage gsMsg = new GsMessage(reqMdl);

        //安否状況
        if (AnpiValidateUtil.validateNecessary(
                errors, anp021JokyoFlg__, "anp020JokyoFlg", gsMsg.getMessage("anp.jokyo"))) {
            __validateJokyoFlg(errors, reqMdl);
        }

        //現在地
        if (AnpiValidateUtil.validateNecessary(
                errors, anp021PlaceFlg__, "anp020PlaceFlg", gsMsg.getMessage("anp.place"))) {
            __validatePlaceFlg(errors, reqMdl);
        }

        //出社状況
        if (AnpiValidateUtil.validateNecessary(
                errors, anp021SyusyaFlg__,
                "anp020SyusyaFlg", gsMsg.getMessage("anp.syusya.state"))) {
            __validateSyusyaFlg(errors, reqMdl);
        }

        //コメント
        AnpiValidateUtil.validateTextAreaField(errors,
                anp021Comment__, "anp021Comment", gsMsg.getMessage("anp.comment")
                , GSConstAnpi.MAXLENGTH_REPLYCOMMENT, false);

        return errors;
    }

    /**
     * 安否状況フラグの入力チェック
     * @param errors ActionErrors
     * @param reqMdl リクエストモデル
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    private boolean __validateJokyoFlg(ActionErrors errors, RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);

        if (anp021JokyoFlg__.equals(String.valueOf(GSConstAnpi.JOKYO_FLG_UNSET))) {
            String msgKey = "error.select2.required.text";
            ActionMessage msg = new ActionMessage(
                    msgKey, gsMsg.getMessage("anp.jokyo"), gsMsg.getMessage("anp.esnotset"));
            StrutsUtil.addMessage(errors, msg, "anp020JokyoFlg." + msgKey);
            return false;
        }

        if (!anp021JokyoFlg__.equals(String.valueOf(GSConstAnpi.JOKYO_FLG_GOOD))
         && !anp021JokyoFlg__.equals(String.valueOf(GSConstAnpi.JOKYO_FLG_KEISYO))
         && !anp021JokyoFlg__.equals(String.valueOf(GSConstAnpi.JOKYO_FLG_JUSYO))) {
            String msgKey = "error.select.required.text";
            ActionMessage msg = new ActionMessage(msgKey, gsMsg.getMessage("anp.jokyo"));
            StrutsUtil.addMessage(errors, msg, "anp020JokyoFlg." + msgKey);
            return false;
        }

        return true;
    }

    /**
     * 現在地フラグの入力チェック
     * @param reqMdl リクエストモデル
     * @param errors ActionErrors
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    private boolean __validatePlaceFlg(ActionErrors errors, RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);

        if (anp021PlaceFlg__.equals(String.valueOf(GSConstAnpi.PLACE_FLG_UNSET))) {
            String msgKey = "error.select2.required.text";
            ActionMessage msg = new ActionMessage(
                    msgKey, gsMsg.getMessage("anp.place"), gsMsg.getMessage("anp.esnotset"));
            StrutsUtil.addMessage(errors, msg, "anp020PlaceFlg." + msgKey);
            return false;
        }

        if (!anp021PlaceFlg__.equals(String.valueOf(GSConstAnpi.PLACE_FLG_HOUSE))
         && !anp021PlaceFlg__.equals(String.valueOf(GSConstAnpi.PLACE_FLG_OFFICE))
         && !anp021PlaceFlg__.equals(String.valueOf(GSConstAnpi.PLACE_FLG_OUT))) {
            String msgKey = "error.select.required.text";
            ActionMessage msg = new ActionMessage(msgKey, gsMsg.getMessage("anp.place"));
            StrutsUtil.addMessage(errors, msg, "anp020PlaceFlg." + msgKey);
            return false;
        }

        return true;
    }

    /**
     * 出社状況フラグの入力チェック
     * @param reqMdl リクエストモデル
     * @param errors ActionErrors
     * @return 入力チェック結果 true : 正常 false : 不正
     */
    private boolean __validateSyusyaFlg(ActionErrors errors, RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);

        if (anp021SyusyaFlg__.equals(String.valueOf(GSConstAnpi.SYUSYA_FLG_UNSET))) {
            String msgKey = "error.select2.required.text";
            ActionMessage msg = new ActionMessage(
                    msgKey, gsMsg.getMessage("anp.syusya.state"), gsMsg.getMessage("anp.esnotset"));
            StrutsUtil.addMessage(errors, msg, "anp020SyusyaFlg." + msgKey);
            return false;
        }

        if (!anp021SyusyaFlg__.equals(String.valueOf(GSConstAnpi.SYUSYA_FLG_NO))
         && !anp021SyusyaFlg__.equals(String.valueOf(GSConstAnpi.SYUSYA_FLG_OK))
         && !anp021SyusyaFlg__.equals(String.valueOf(GSConstAnpi.SYUSYA_FLG_OKD))) {
            String msgKey = "error.select.required.text";
            ActionMessage msg = new ActionMessage(msgKey, gsMsg.getMessage("anp.syusya.state"));
            StrutsUtil.addMessage(errors, msg, "anp020SyusyaFlg." + msgKey);
            return false;
        }

        return true;
    }
    /**
     * <p>anp021KnrenFlg を取得します。
     * @return anp021KnrenFlg
     */
    public int getAnp021KnrenFlg() {
        return anp021KnrenFlg__;
    }
    /**
     * <p>anp021KnrenFlg をセットします。
     * @param anp021KnrenFlg anp021KnrenFlg
     */
    public void setAnp021KnrenFlg(int anp021KnrenFlg) {
        anp021KnrenFlg__ = anp021KnrenFlg;
    }
    /**
     * <p>anp021initFlg を取得します。
     * @return anp021initFlg
     */
    public int getAnp021initFlg() {
        return anp021initFlg__;
    }
    /**
     * <p>anp021initFlg をセットします。
     * @param anp021initFlg anp021initFlg
     */
    public void setAnp021initFlg(int anp021initFlg) {
        anp021initFlg__ = anp021initFlg;
    }
}