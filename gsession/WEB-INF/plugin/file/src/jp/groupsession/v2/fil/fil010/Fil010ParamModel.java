package jp.groupsession.v2.fil.fil010;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.fil.FilTreeParamModel;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] キャビネット一覧画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil010ParamModel extends FilTreeParamModel {

    /** 処理モード 0=新規 1=編集 2=一括編集 */
    private String cmnMode__ = GSConstFile.CMN_MODE_ADD;

    /** 遷移元 メイン個人設定:1 メイン管理者設定:1 その他:0*/
    private int backScreen__ = GSConstMain.BACK_PLUGIN;
    /** 遷移元 */
    private String backDsp__ = GSConstFile.MOVE_TO_FIL010;
    /** 遷移元 （フォルダ・ファイルの詳細画面で使用）*/
    private String backDspLow__ = GSConstFile.MOVE_TO_FIL010;
    /** 遷移元 （更新通知一覧画面で使用）*/
    private String backDspCall__ = "";

    /** 検索キーワード */
    private String filSearchWd__ = "";
    /** バージョン管理区分 */
    private int admVerKbn__;
    /** ロック機能区分 */
    private int admLockKbn__;

    /** ショートカット選択multibox */
    private String[] fil010SelectDelLink__;

    /** 管理者設定ボタン表示　有無 */
    private String fil010DspAdminConfBtn__ = GSConstFile.DSP_KBN_OFF;
    /** キャビネット作成ボタン表示　有無 */
    private String fil010DspCabinetAddBtn__ = GSConstFile.DSP_KBN_OFF;
    /** 選択したキャビネットSID */
    private String fil010SelectCabinet__;
    /** 選択したフォルダーSID(フォルダ一覧のカレントディレクトリ) */
    private String fil010SelectDirSid__;

    //画面表示用
    /** キャビネット一覧 */
    private ArrayList<FileCabinetDspModel> cabinetList__;
    /** ショートカット一覧 */
    private ArrayList<FileLinkSimpleModel> shortcutList__;
    /** 更新通知一覧 */
    private ArrayList<FileLinkSimpleModel> callList__;
    /** ショートカット一覧・更新通知一覧表示フラグ */
    private int shortcutCallListFlg__;

    /** アイコンファイルSIDダウンロード用 */
    private int photoFileSid__ = 0;
    /** アイコンファイルSID */
    private Long fil010binSid__ = new Long(0);
    /**
     * <br>[機  能] 詳細検索画面入力チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateFil010Check(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        String textDelShortcut = gsMsg.getMessage(req, "fil.76");

        if (fil010SelectDelLink__ == null)  {
            //未選択の場合エラー
            msg = new ActionMessage(
                    "error.select.required.text", textDelShortcut);
            StrutsUtil.addMessage(errors, msg, "fil010SelectDelLink");
        }

        return errors;
    }

    /**
     * <br>[機  能] 共通メッセージフォームへのパラメータ設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param msgForm 共通メッセージフォーム
     */
    public void setHiddenParam(Cmn999Form msgForm) {
        //ショートカット選択multibox
        msgForm.addHiddenParam("fil010SelectDelLink", fil010SelectDelLink__);
        //検索キーワード
        msgForm.addHiddenParam("filSearchWd", filSearchWd__);
        //遷移元
        msgForm.addHiddenParam("backDsp", backDsp__);
        //遷移元
        msgForm.addHiddenParam("backDspLow", backDspLow__);
        //遷移元 メイン個人設定:1 メイン管理者設定:1 その他:0
        msgForm.addHiddenParam("backScreen", backScreen__);
    }

    /**
     * @return backDsp
     */
    public String getBackDsp() {
        return backDsp__;
    }
    /**
     * @param backDsp 設定する backDsp
     */
    public void setBackDsp(String backDsp) {
        backDsp__ = backDsp;
    }
    /**
     * @return cmnMode
     */
    public String getCmnMode() {
        return cmnMode__;
    }
    /**
     * @param cmnMode 設定する cmnMode
     */
    public void setCmnMode(String cmnMode) {
        cmnMode__ = cmnMode;
    }
    /**
     * @return fil010SelectCabinet
     */
    public String getFil010SelectCabinet() {
        return fil010SelectCabinet__;
    }
    /**
     * @param fil010SelectCabinet 設定する fil010SelectCabinet
     */
    public void setFil010SelectCabinet(String fil010SelectCabinet) {
        fil010SelectCabinet__ = fil010SelectCabinet;
    }
    /**
     * @return fil010DspAdminConfBtn
     */
    public String getFil010DspAdminConfBtn() {
        return fil010DspAdminConfBtn__;
    }
    /**
     * @param fil010DspAdminConfBtn 設定する fil010DspAdminConfBtn
     */
    public void setFil010DspAdminConfBtn(String fil010DspAdminConfBtn) {
        fil010DspAdminConfBtn__ = fil010DspAdminConfBtn;
    }
    /**
     * @return fil010DspCabinetAddBtn
     */
    public String getFil010DspCabinetAddBtn() {
        return fil010DspCabinetAddBtn__;
    }
    /**
     * @param fil010DspCabinetAddBtn 設定する fil010DspCabinetAddBtn
     */
    public void setFil010DspCabinetAddBtn(String fil010DspCabinetAddBtn) {
        fil010DspCabinetAddBtn__ = fil010DspCabinetAddBtn;
    }
    /**
     * @return cabinetList
     */
    public ArrayList<FileCabinetDspModel> getCabinetList() {
        return cabinetList__;
    }
    /**
     * @param cabinetList 設定する cabinetList
     */
    public void setCabinetList(ArrayList<FileCabinetDspModel> cabinetList) {
        cabinetList__ = cabinetList;
    }
    /**
     * @return callList
     */
    public ArrayList<FileLinkSimpleModel> getCallList() {
        return callList__;
    }
    /**
     * @param callList 設定する callList
     */
    public void setCallList(ArrayList<FileLinkSimpleModel> callList) {
        callList__ = callList;
    }
    /**
     * @return shortcutList
     */
    public ArrayList<FileLinkSimpleModel> getShortcutList() {
        return shortcutList__;
    }
    /**
     * @param shortcutList 設定する shortcutList
     */
    public void setShortcutList(ArrayList<FileLinkSimpleModel> shortcutList) {
        shortcutList__ = shortcutList;
    }
    /**
     * @return fil010SelectDelLink
     */
    public String[] getFil010SelectDelLink() {
        return fil010SelectDelLink__;
    }
    /**
     * @param fil010SelectDelLink 設定する fil010SelectDelLink
     */
    public void setFil010SelectDelLink(String[] fil010SelectDelLink) {
        fil010SelectDelLink__ = fil010SelectDelLink;
    }
    /**
     * @return filSearchWd
     */
    public String getFilSearchWd() {
        return filSearchWd__;
    }
    /**
     * @param filSearchWd 設定する filSearchWd
     */
    public void setFilSearchWd(String filSearchWd) {
        filSearchWd__ = filSearchWd;
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
     * @return fil010SelectDirSid
     */
    public String getFil010SelectDirSid() {
        return fil010SelectDirSid__;
    }
    /**
     * @param fil010SelectDirSid 設定する fil010SelectDirSid
     */
    public void setFil010SelectDirSid(String fil010SelectDirSid) {
        fil010SelectDirSid__ = fil010SelectDirSid;
    }
    /**
     * <p>backDspLow を取得します。
     * @return backDspLow
     */
    public String getBackDspLow() {
        return backDspLow__;
    }
    /**
     * <p>backDspLow をセットします。
     * @param backDspLow backDspLow
     */
    public void setBackDspLow(String backDspLow) {
        backDspLow__ = backDspLow;
    }

    /**
     * <p>admVerKbn を取得します。
     * @return admVerKbn
     */
    public int getAdmVerKbn() {
        return admVerKbn__;
    }

    /**
     * <p>admVerKbn をセットします。
     * @param admVerKbn admVerKbn
     */
    public void setAdmVerKbn(int admVerKbn) {
        admVerKbn__ = admVerKbn;
    }

    /**
     * <p>admLockKbn を取得します。
     * @return admLockKbn
     */
    public int getAdmLockKbn() {
        return admLockKbn__;
    }

    /**
     * <p>admLockKbn をセットします。
     * @param admLockKbn admLockKbn
     */
    public void setAdmLockKbn(int admLockKbn) {
        admLockKbn__ = admLockKbn;
    }

    /**
     * <p>backDspCall を取得します。
     * @return backDspCall
     */
    public String getBackDspCall() {
        return backDspCall__;
    }

    /**
     * <p>backDspCall をセットします。
     * @param backDspCall backDspCall
     */
    public void setBackDspCall(String backDspCall) {
        backDspCall__ = backDspCall;
    }

    /**
     * <p>photoFileSid を取得します。
     * @return photoFileSid
     */
    public int getPhotoFileSid() {
        return photoFileSid__;
    }

    /**
     * <p>photoFileSid をセットします。
     * @param photoFileSid photoFileSid
     */
    public void setPhotoFileSid(int photoFileSid) {
        photoFileSid__ = photoFileSid;
    }

    /**
     * <p>fil010binSid を取得します。
     * @return fil010binSid
     */
    public Long getFil010binSid() {
        return fil010binSid__;
    }

    /**
     * <p>fil010binSid をセットします。
     * @param fil010binSid fil010binSid
     */
    public void setFil010binSid(Long fil010binSid) {
        fil010binSid__ = fil010binSid;
    }

    /**
     * <p>shortcutCallListFlg を取得します。
     * @return shortcutCallListFlg
     */
    public int getShortcutCallListFlg() {
        return shortcutCallListFlg__;
    }

    /**
     * <p>shortcutCallListFlg をセットします。
     * @param shortcutCallListFlg shortcutCallListFlg
     */
    public void setShortcutCallListFlg(int shortcutCallListFlg) {
        shortcutCallListFlg__ = shortcutCallListFlg;
    }
}