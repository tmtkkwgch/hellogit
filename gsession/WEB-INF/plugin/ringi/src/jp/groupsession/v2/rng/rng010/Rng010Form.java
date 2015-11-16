package jp.groupsession.v2.rng.rng010;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.rng.AbstractRingiForm;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.RngValidate;
import jp.groupsession.v2.rng.model.RingiDataModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議一覧画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng010Form extends AbstractRingiForm {

    /** 管理者フラグ */
    private int rng010adminFlg__ = -1;
    /** オーダーキー */
    private int rng010orderKey__ = RngConst.RNG_ORDER_ASC;
    /** ソートキー */
    private int rng010sortKey__ = RngConst.RNG_SORT_JYUSIN;
    /** ページコンボ上段 */
    private int rng010pageTop__ = 1;
    /** ページコンボ下段 */
    private int rng010pageBottom__ = 1;
    /** ページコンボリスト */
    private List < LabelValueBean > pageList__ = null;
    /** 稟議情報一覧 */
    private List <RingiDataModel> rngDataList__ = null;
    /** 削除対象一覧 */
    private String[] rng010DelSidList__ = null;
    /** キーワード */
    private String rngKeyword__ = null;
    /** 削除権限 */
    private int rng010delAuth__ = 0;

    /** 遷移元 メイン個人設定:1 メイン管理者設定:1 その他:0*/
    private int backScreen__ = GSConstMain.BACK_PLUGIN;

    /**
     * <p>pageList を取得します。
     * @return pageList
     */
    public List<LabelValueBean> getPageList() {
        return pageList__;
    }
    /**
     * <p>pageList をセットします。
     * @param pageList pageList
     */
    public void setPageList(List<LabelValueBean> pageList) {
        pageList__ = pageList;
    }
    /**
     * <p>rng010adminFlg を取得します。
     * @return rng010adminFlg
     */
    public int getRng010adminFlg() {
        return rng010adminFlg__;
    }
    /**
     * <p>rng010adminFlg をセットします。
     * @param rng010adminFlg rng010adminFlg
     */
    public void setRng010adminFlg(int rng010adminFlg) {
        rng010adminFlg__ = rng010adminFlg;
    }
    /**
     * <p>rng010orderKey を取得します。
     * @return rng010orderKey
     */
    public int getRng010orderKey() {
        return rng010orderKey__;
    }
    /**
     * <p>rng010orderKey をセットします。
     * @param rng010orderKey rng010orderKey
     */
    public void setRng010orderKey(int rng010orderKey) {
        rng010orderKey__ = rng010orderKey;
    }
    /**
     * <p>rng010pageBottom を取得します。
     * @return rng010pageBottom
     */
    public int getRng010pageBottom() {
        return rng010pageBottom__;
    }
    /**
     * <p>rng010pageBottom をセットします。
     * @param rng010pageBottom rng010pageBottom
     */
    public void setRng010pageBottom(int rng010pageBottom) {
        rng010pageBottom__ = rng010pageBottom;
    }
    /**
     * <p>rng010pageTop を取得します。
     * @return rng010pageTop
     */
    public int getRng010pageTop() {
        return rng010pageTop__;
    }
    /**
     * <p>rng010pageTop をセットします。
     * @param rng010pageTop rng010pageTop
     */
    public void setRng010pageTop(int rng010pageTop) {
        rng010pageTop__ = rng010pageTop;
    }
    /**
     * <p>rng010sortKey を取得します。
     * @return rng010sortKey
     */
    public int getRng010sortKey() {
        return rng010sortKey__;
    }
    /**
     * <p>rng010sortKey をセットします。
     * @param rng010sortKey rng010sortKey
     */
    public void setRng010sortKey(int rng010sortKey) {
        rng010sortKey__ = rng010sortKey;
    }
    /**
     * <p>rngDataList を取得します。
     * @return rngDataList
     */
    public List<RingiDataModel> getRngDataList() {
        return rngDataList__;
    }
    /**
     * <p>rngDataList をセットします。
     * @param rngDataList rngDataList
     */
    public void setRngDataList(List<RingiDataModel> rngDataList) {
        rngDataList__ = rngDataList;
    }
    /**
     * <p>rng010DelSidList を取得します。
     * @return rng010DelSidList
     */
    public String[] getRng010DelSidList() {
        return rng010DelSidList__;
    }
    /**
     * <p>rng010DelSidList をセットします。
     * @param rng010DelSidList rng010DelSidList
     */
    public void setRng010DelSidList(String[] rng010DelSidList) {
        rng010DelSidList__ = rng010DelSidList;
    }
    /**
     * <p>rngKeyword を取得します。
     * @return rngKeyword
     */
    public String getRngKeyword() {
        return rngKeyword__;
    }
    /**
     * <p>rngKeyword をセットします。
     * @param rngKeyword rngKeyword
     */
    public void setRngKeyword(String rngKeyword) {
        rngKeyword__ = rngKeyword;
    }

    /**
     * <p>rng010delAuth を取得します。
     * @return rng010delAuth
     */
    public int getRng010delAuth() {
        return rng010delAuth__;
    }
    /**
     * <p>rng010delAuth をセットします。
     * @param rng010delAuth rng010delAuth
     */
    public void setRng010delAuth(int rng010delAuth) {
        rng010delAuth__ = rng010delAuth;
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
        String msg2 = gsMsg.getMessage(req, "rng.62");

        //削除対象チェック
        if (rng010DelSidList__ == null || rng010DelSidList__.length < 1) {
            //未選択チェック
            msg = new ActionMessage("error.select.required.text", msg2);
            StrutsUtil.addMessage(errors, msg, "rng010DelSidList");
        }

        return errors;
    }

    /**
     * <br>[機  能] 入力チェック(検索ボタンクリック時)を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheckSearch(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "cmn.keyword");
        //キーワードチェック
        errors = RngValidate.validateCmnFieldText(
                                                errors,
                                                msg,
                                                rngKeyword__,
                                                "rngKeyword",
                                                RngConst.MAX_LENGTH_TITLE,
                                                false);

        return errors;
    }
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
     * <br>[機  能] 共通メッセージ画面遷移時に保持するパラメータを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param msgForm 共通メッセージ画面Form
     */
    public void setHiddenParam(Cmn999Form msgForm) {
        msgForm.addHiddenParam("backScreen", backScreen__);
        msgForm.addHiddenParam("rngProcMode", getRngProcMode());
        msgForm.addHiddenParam("rngTemplateMode", getRngTemplateMode());

        msgForm.addHiddenParam("rng010adminFlg", rng010adminFlg__);
        msgForm.addHiddenParam("rng010orderKey", rng010orderKey__);
        msgForm.addHiddenParam("rng010sortKey", rng010sortKey__);
        msgForm.addHiddenParam("rng010pageTop", rng010pageTop__);
        msgForm.addHiddenParam("rng010pageBottom", rng010pageBottom__);
        msgForm.addHiddenParam("rng010DelSidList", rng010DelSidList__);
        msgForm.addHiddenParam("rngKeyword", rngKeyword__);
    }

}
