package jp.groupsession.v2.rng.rng030;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.rng.AbstractRingiAction;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 稟議内容確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng030Action extends AbstractRingiAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng030Action.class);

    /** 確認/完了画面の種類 承認 */
    private static final int MSGPAGETYPE_APPROVAL__ = 1;
    /** 確認/完了画面の種類 否認 */
    private static final int MSGPAGETYPE_DENIAL__ = 2;
    /** 確認/完了画面の種類 差し戻し */
    private static final int MSGPAGETYPE_REFLECTION__ = 3;
    /** 確認/完了画面の種類 確認 */
    private static final int MSGPAGETYPE_CONFIRMATION__ = 4;
    /** 確認/完了画面の種類 完了 */
    private static final int MSGPAGETYPE_COMPLETE__ = 5;
    /** 確認/完了画面の種類 強制完了 */
    private static final int MSGPAGETYPE_COMPELCOMPLETE__ = 6;
    /** 確認/完了画面の種類 強制削除 */
    private static final int MSGPAGETYPE_COMPELDELETE__ = 7;
    /** 確認/完了画面の種類 スキップ */
    private static final int MSGPAGETYPE_SKIP__ = 8;
    /** 確認/完了画面の種類 再申請 */
    private static final int MSGPAGETYPE_APPLICATE__ = 9;

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {

        int apprMode = ((Rng030Form) form).getRngApprMode();

        if (apprMode == RngConst.RNG_APPRMODE_DISCUSSING
        || apprMode == RngConst.RNG_APPRMODE_COMPLETE) {
            return false;
        }
        return true;
    }

    /**
     * <p>キャッシュを有効にして良いか判定を行う
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:有効にする,false:無効にする
     */
    public boolean isCacheOk(HttpServletRequest req, ActionForm form) {
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        return cmd.equals("fileDownload");
    }

    /**
     * <br>[機  能] アクションを実行する
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form ActionForm
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);

        Rng030Form thisForm = (Rng030Form) form;
        if (cmd.equals("backList")) {
            log__.debug("戻るボタンクリック");

            Rng030ParamModel paramMdl = new Rng030ParamModel();
            paramMdl.setParam(thisForm);
            Rng030Biz biz = new Rng030Biz(con, getRequestModel(req));
            forward = biz.getForward(map, paramMdl);
            paramMdl.setFormData(thisForm);

            //テンポラリディレクトリを削除する
            IOTools.deleteDir(_getRingiDir(req));

        } else if (cmd.equals("delTemp")) {
            log__.debug("削除(添付ファイル)ボタンクリック");
            forward = __doDelTemp(map, thisForm, req, res, con);

        } else if (cmd.equals("approval")) {
            log__.debug("承認ボタンクリック");
            forward = __doApproval(map, thisForm, req, res, con);

        } else if (cmd.equals("reject")) {
            log__.debug("却下ボタンクリック");
            forward = __doDenial(map, thisForm, req, res, con);

        } else if (cmd.equals("reflection")) {
            log__.debug("差し戻しボタンクリック");
            forward = __doReflection(map, thisForm, req, res, con);

        } else if (cmd.equals("confirmation")) {
            log__.debug("確認ボタンクリック");
            forward = __doConfirmation(map, thisForm, req, res, con);

        } else if (cmd.equals("complete")) {
            log__.debug("完了ボタンクリック");
            forward = __doComplete(map, thisForm, req, res, con);

        } else if (cmd.equals("compelcomplete")) {
            log__.debug("強制完了ボタンクリック");
            forward = __doCompleComplete(map, thisForm, req, res, con);

        } else if (cmd.equals("compeldelete")) {
            log__.debug("強制削除ボタンクリック");
            forward = __doCompleDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("skip")) {
            log__.debug("スキップボタンクリック");
            forward = __doSkip(map, thisForm, req, res, con);

        } else if (cmd.equals("applicate")) {
            log__.debug("再申請ボタンクリック");
            forward = __doApplicate(map, thisForm, req, res, con);

        } else if (cmd.equals("fileDownload")) {
            //添付ファイルリンククリッククリック
            forward = __doDownLoad(map, thisForm, req, res, con);

        } else if (cmd.equals("copyApply")) {
            //複写して申請ボタンクリック
            forward = __doCopyApply(map, thisForm, req, res, con);

        } else if (cmd.equals("rngConfirm")) {
            log__.debug("各種確認画面からの遷移");
            forward = __doCompletePage(map, thisForm, res, req, con);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        Rng030Biz biz = new Rng030Biz(con, getRequestModel(req));
        //添付ファイルをテンポラリディレクトリへ移動する
        biz.readRngBinData(con, form.getRngSid(), getSessionUserModel(req).getUsrsid(),
                        getAppRootPath(), _getRingiDir(req));

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 画面表示情報設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doDsp(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        //画面表示情報を設定
        RequestModel reqMdl = getRequestModel(req);
        Rng030ParamModel paramMdl = new Rng030ParamModel();
        paramMdl.setParam(form);
        Rng030Biz biz = new Rng030Biz(con, reqMdl);
        boolean existRingi = biz.setDspData(reqMdl, paramMdl, con,
                                            getAppRootPath(), _getRingiDir(req),
                                            getSessionUserModel(req), req);
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

        //稟議が存在しない場合は共通メッセージ画面へ遷移する
        if (!existRingi) {

            Cmn999Form cmn999Form = new Cmn999Form();
            ActionForward urlForward = map.findForward("rng010");

            MessageResources msgRes = getResources(req);
            cmn999Form.setIcon(Cmn999Form.ICON_INFO);
            cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
            cmn999Form.setType(Cmn999Form.TYPE_OK);
            cmn999Form.setUrlOK(urlForward.getPath());

            GsMessage gsMsg = new GsMessage();
            String msg = gsMsg.getMessage(req, "rng.62");

            //メッセージセット
            cmn999Form.setMessage(msgRes.getMessage("search.data.notfound", msg));
            req.setAttribute("cmn999Form", cmn999Form);

            return map.findForward("gf_msg");
        }

        //WEB検索プラグインを使用可能か設定
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));
        form.setRngWebSearchUse(CommonBiz.getWebSearchUse(pconfig));

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 添付ファイル削除ボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return ActionForward
     */
    private ActionForward __doDelTemp(
        ActionMapping map,
        Rng030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = _getRingiDir(req);
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //選択された添付ファイルを削除する
        cmnBiz.deleteFile(form.getRng030files(), tempDir);

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 承認ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doApproval(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);

            return __doDsp(map, form, req, res, con);
        }

        //確認画面の設定を行う
        return __doConfirm(map, form, req, res, con, MSGPAGETYPE_APPROVAL__);
    }

    /**
     * <br>[機  能] 否認ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doDenial(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);

            return __doDsp(map, form, req, res, con);
        }

        //確認画面の設定を行う
        return __doConfirm(map, form, req, res, con, MSGPAGETYPE_DENIAL__);
    }

    /**
     * <br>[機  能] 差し戻しボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doReflection(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);

            return __doDsp(map, form, req, res, con);
        }

        //確認画面の設定を行う
        return __doConfirm(map, form, req, res, con, MSGPAGETYPE_REFLECTION__);
    }

    /**
     * <br>[機  能] 確認ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doConfirmation(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);

            return __doDsp(map, form, req, res, con);
        }

        //確認画面の設定を行う
        return __doConfirm(map, form, req, res, con, MSGPAGETYPE_CONFIRMATION__);
    }

    /**
     * <br>[機  能] 完了ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doComplete(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);

            return __doDsp(map, form, req, res, con);
        }

        //確認画面の設定を行う
        return __doConfirm(map, form, req, res, con, MSGPAGETYPE_COMPLETE__);
    }

    /**
     * <br>[機  能] 強制完了ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doCompleComplete(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);

            return __doDsp(map, form, req, res, con);
        }

        //確認画面の設定を行う
        return __doConfirm(map, form, req, res, con, MSGPAGETYPE_COMPELCOMPLETE__);
    }

    /**
     * <br>[機  能] 強制削除ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doCompleDelete(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //確認画面の設定を行う
        return __doConfirm(map, form, req, res, con, MSGPAGETYPE_COMPELDELETE__);
    }

    /**
     * <br>[機  能] スキップボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doSkip(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //確認画面の設定を行う
        return __doConfirm(map, form, req, res, con, MSGPAGETYPE_SKIP__);

    }

    /**
     * <br>[機  能] 再申請ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doApplicate(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェックを行う
        ActionErrors errors = form.validateCheckApplicate(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);

            return __doDsp(map, form, req, res, con);
        }

        //確認画面の設定を行う
        return __doConfirm(map, form, req, res, con, MSGPAGETYPE_APPLICATE__);
    }

    /**
     * <br>[機  能] 添付ファイルダウンロードの処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDownLoad(
                                    ActionMapping map,
                                    Rng030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws SQLException, Exception {

        CommonBiz cmnBiz = new CommonBiz();
        RngBiz rngBiz = new RngBiz(con);

        CommonBiz commonBiz = new CommonBiz();
        boolean admin = commonBiz.isPluginAdmin(
                con, getRequestModel(req).getSmodel(), RngConst.PLUGIN_ID_RINGI);

        int rngSid = form.getRngSid();
        Long binSid = form.getRng030fileId();
        //申請された稟議の添付ファイルをダウンロード可能かチェックする
        if (rngBiz.isCheckDLRngTemp(con, rngSid, getSessionUserSid(req), admin, binSid)) {
            CmnBinfModel cbMdl = cmnBiz.getBinInfo(con, binSid,
                    GroupSession.getResourceManager().getDomain(req));

            if (cbMdl != null) {

                GsMessage gsMsg = new GsMessage(getRequestModel(req));
                String download = gsMsg.getMessage("cmn.download");

                //ログ出力処理
                rngBiz.outPutLog(
                        map,
                        download, GSConstLog.LEVEL_INFO, cbMdl.getBinFileName(),
                        getRequestModel(req));

                //時間のかかる処理の前にコネクションを破棄
                JDBCUtil.closeConnectionAndNull(con);
                //ファイルをダウンロードする
                TempFileUtil.downloadAtachment(req, res, cbMdl, getAppRootPath(),
                        Encoding.UTF_8);
            }
        }

        return null;
    }

    /**
     * <br>[機  能] 複写して申請ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng030Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doCopyApply(ActionMapping map, Rng030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        return map.findForward("rng020");
    }

    /**
     * <br>[機  能] 各種確認画面の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param mode 確認画面の種類
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doConfirm(ActionMapping map,
                                    Rng030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con,
                                    int mode
                                    )
        throws Exception {


        Cmn999Form cmn999Form = new Cmn999Form();
//        ActionForward urlForward = null;

        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //種別により確認メッセージを変更
        String msgState = "";
        switch(mode) {
            case  MSGPAGETYPE_APPROVAL__:
                //承認
                msgState = "approval.kakunin.ringi2";
                //稟議タイトル
                cmn999Form.addHiddenParam("rng030ViewTitle", form.getRng030ViewTitle());

                break;
            case MSGPAGETYPE_DENIAL__ :
                //否認
                msgState = "denial.kakunin.ringi2";
                //稟議タイトル
                cmn999Form.addHiddenParam("rng030ViewTitle", form.getRng030ViewTitle());
                break;
            case MSGPAGETYPE_REFLECTION__ :
                //差し戻し
                msgState = "reflection.kakunin.ringi2";
                //稟議タイトル
                cmn999Form.addHiddenParam("rng030ViewTitle", form.getRng030ViewTitle());
                break;
            case MSGPAGETYPE_CONFIRMATION__ :
                //確認
                msgState = "confirmation.kakunin.ringi2";
                //稟議タイトル
                cmn999Form.addHiddenParam("rng030ViewTitle", form.getRng030ViewTitle());
                break;
            case MSGPAGETYPE_COMPLETE__ :
            case MSGPAGETYPE_COMPELCOMPLETE__ :
                //完了 or 強制完了
                msgState = "complete.kakunin.ringi2";
                //稟議タイトル
                cmn999Form.addHiddenParam("rng030ViewTitle", form.getRng030ViewTitle());
                break;
            case MSGPAGETYPE_COMPELDELETE__ :
                //強制削除
                msgState = "delete.kakunin.ringi2";
                //稟議タイトル
                cmn999Form.addHiddenParam("rng030ViewTitle", form.getRng030ViewTitle());
                break;
            case MSGPAGETYPE_SKIP__ :
                //スキップ
                msgState = "skip.kakunin.ringi2";
                //稟議タイトル
                cmn999Form.addHiddenParam("rng030ViewTitle", form.getRng030ViewTitle());
                break;
            case MSGPAGETYPE_APPLICATE__ :
                //再申請
                msgState = "applicate.kakunin.ringi2";
                break;
            default :
                return __doDsp(map, form, req, res, con);
        }
        if (mode == MSGPAGETYPE_APPLICATE__) {
            //再申請
            cmn999Form.setMessage(msgRes.getMessage(msgState,
                    StringUtilHtml.transToHTmlPlusAmparsant(form.getRng030Title())));

        } else {
            //それ以外
            cmn999Form.setMessage(msgRes.getMessage(msgState,
                    StringUtilHtml.transToHTmlPlusAmparsant(form.getRng030ViewTitle())));
        }

        cmn999Form.setUrlOK(map.findForward("mine").getPath() + "?CMD=rngConfirm");
        cmn999Form.setUrlCancel(map.findForward("mine").getPath());
        cmn999Form = __setFormParam(cmn999Form, form, mode);
        req.setAttribute("cmn999Form", cmn999Form);

        saveToken(req);

        return map.findForward("gf_msg");

    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時の設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param form アクションフォーム
     * @param res レスポンス
     * @param req リクエスト
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行時例外発生
     */
    private ActionForward __doCompletePage(ActionMapping map,
                                            Rng030Form form,
                                            HttpServletResponse res,
                                            HttpServletRequest req,
                                            Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        Cmn999Form cmn999Form = new Cmn999Form();

        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        int mode = form.getRng030confirmMode();
        String title = form.getRng030ViewTitle();
        RngBiz rngBiz = new RngBiz(con);

        boolean commit = false;

        try {
            //種別により更新処理、完了メッセージを変更
            RequestModel reqMdl = getRequestModel(req);
            String msgState = "";
            Rng030Biz biz = new Rng030Biz(con, reqMdl);
            int userSid = getSessionUserModel(req).getUsrsid();
            String appRootPath = getAppRootPath();
            String tempDir = _getRingiDir(req);
            PluginConfig pconfig = getPluginConfigForMain(getPluginConfig(req), con);
            CommonBiz cmnBiz = new CommonBiz();
            boolean smailPluginUseFlg = cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig);

            GsMessage gsMsg = new GsMessage(reqMdl);
            String msg = "";
            String msg2 = "";

            Rng030ParamModel paramMdl = new Rng030ParamModel();
            paramMdl.setParam(form);
            switch (mode) {
                case MSGPAGETYPE_APPROVAL__ :
                    //承認

                    if (!form.validateCheck(req).isEmpty()) {
                        //入力チェック
                        return map.findForward("gf_auth");
                    }

                    biz.approvalRingi(paramMdl, getCountMtController(req), userSid,
                                    appRootPath, tempDir,
                                    getPluginConfig(req),
                                    smailPluginUseFlg,
                                    reqMdl);
                    paramMdl.setFormData(form);
                    msgState = "approval.kanryo.ringi2";
                    msg = gsMsg.getMessage("cmn.edit");
                    msg2 = gsMsg.getMessage("rng.41");

                    //ログ出力処理
                    rngBiz.outPutLog(
                            map,
                            msg,
                            GSConstLog.LEVEL_TRACE,
                            msg2 + "[title]" + title,
                            reqMdl);
                    break;
                case MSGPAGETYPE_DENIAL__ :
                    //否認

                    if (!form.validateCheck(req).isEmpty()) {
                        //入力チェック
                        return map.findForward("gf_auth");
                    }

                    biz.denialRingi(paramMdl, getCountMtController(req), userSid,
                                    appRootPath, tempDir,
                                    getPluginConfig(req),
                                    smailPluginUseFlg,
                                    reqMdl);
                    paramMdl.setFormData(form);
                    msgState = "denial.kanryo.ringi2";

                    msg = gsMsg.getMessage("cmn.edit");
                    msg2 = gsMsg.getMessage("rng.rng030.12");
                    //ログ出力処理
                    rngBiz.outPutLog(
                            map,
                            msg,
                            GSConstLog.LEVEL_TRACE,
                            msg2 + "[title]" + title,
                            reqMdl);

                    break;
                case MSGPAGETYPE_REFLECTION__ :
                    //差し戻し

                    if (!form.validateCheck(req).isEmpty()) {
                        //入力チェック
                        return map.findForward("gf_auth");
                    }

                    biz.reflectionRingi(paramMdl, getCountMtController(req), userSid,
                                        appRootPath, tempDir,
                                        getPluginConfig(req),
                                        smailPluginUseFlg,
                                        reqMdl);
                    paramMdl.setFormData(form);
                    msgState = "reflection.kanryo.ringi2";

                    msg = gsMsg.getMessage("cmn.edit");
                    msg2 = gsMsg.getMessage("rng.rng030.08");

                    //ログ出力処理
                    rngBiz.outPutLog(
                            map,
                            msg,
                            GSConstLog.LEVEL_TRACE,
                            msg2 + "[title]" + title,
                            reqMdl);

                    break;
                case MSGPAGETYPE_CONFIRMATION__ :
                    //確認
                    biz.confirmationRingi(paramMdl, con, getCountMtController(req),
                                        userSid, appRootPath, tempDir);
                    paramMdl.setFormData(form);
                    msgState = "confirmation.kanryo.ringi2";
                    break;
                case MSGPAGETYPE_COMPLETE__ :
                    //完了
                    biz.completeRingi(paramMdl, con, userSid,
                                    getCountMtController(req), appRootPath, getPluginConfig(req));
                    paramMdl.setFormData(form);
                    msgState = "complete.kanryo.ringi2";
                    break;
                case MSGPAGETYPE_COMPELCOMPLETE__ :
                    //強制完了

                    if (!form.validateCheck(req).isEmpty()) {
                        //入力チェック
                        return map.findForward("gf_auth");
                    }

                    biz.compelCompleteRingi(paramMdl, con, userSid,
                                    getCountMtController(req), appRootPath, getPluginConfig(req));
                    paramMdl.setFormData(form);
                    msgState = "complete.kanryo.ringi2";

                    msg = gsMsg.getMessage("cmn.edit");
                    msg2 = gsMsg.getMessage("rng.rng030.06");

                    //ログ出力処理
                    rngBiz.outPutLog(
                            map,
                            msg,
                            GSConstLog.LEVEL_INFO,
                            msg2,
                            reqMdl);
                    break;
                case MSGPAGETYPE_COMPELDELETE__ :
                    //強制削除
                    biz.compelDeleteRingi(paramMdl, con, userSid);
                    paramMdl.setFormData(form);
                    msgState = "delete.kanryo.ringi2";

                    msg = gsMsg.getMessage("cmn.delete");
                    msg2 = gsMsg.getMessage("rng.rng030.07");
                    //ログ出力処理
                    rngBiz.outPutLog(
                            map,
                            msg,
                            GSConstLog.LEVEL_INFO,
                            msg2,
                            reqMdl);
                    break;
                case MSGPAGETYPE_SKIP__ :
                    //スキップ

                    if (biz.isLastApproval(con, form.getRngSid())) {
                        //現在確認中のユーザが最終確認者かを確認する
                        return map.findForward("gf_auth");
                    }
                    biz.skipRingi(paramMdl, con, userSid);
                    paramMdl.setFormData(form);
                    msgState = "skip.kanryo.ringi2";

                    msg = gsMsg.getMessage("cmn.edit");
                    msg2 = gsMsg.getMessage("rng.rng030.03");
                    //ログ出力処理
                    rngBiz.outPutLog(
                            map,
                            msg,
                            GSConstLog.LEVEL_TRACE,
                            msg2,
                            reqMdl);
                    break;
                case MSGPAGETYPE_APPLICATE__ :
                    //再申請

                    if (!form.validateCheckApplicate(req).isEmpty()) {
                        //入力チェック
                        return map.findForward("gf_auth");
                    }

                    biz.applicateRingi(paramMdl, getCountMtController(req), userSid,
                                    appRootPath, _getRingiDir(req), getPluginConfig(req),
                                    smailPluginUseFlg);
                    paramMdl.setFormData(form);
                    msgState = "applicate.kanryo.ringi2";
                    break;
                default :
                    return map.findForward("gf_auth");
            }

            MessageResources msgRes = getResources(req);
            if (mode == MSGPAGETYPE_APPLICATE__) {
                //再申請
                cmn999Form.setMessage(msgRes.getMessage(msgState,
                        StringUtilHtml.transToHTmlPlusAmparsant(form.getRng030Title())));
            } else {
                //それ以外
                cmn999Form.setMessage(msgRes.getMessage(msgState,
                        StringUtilHtml.transToHTmlPlusAmparsant(form.getRng030ViewTitle())));
            }

            //戻り先画面を設定
            ActionForward urlForward = biz.getForward(map, paramMdl);
            paramMdl.setFormData(form);
            cmn999Form.setUrlOK(urlForward.getPath());

            cmn999Form = __setFormParam(cmn999Form, form, mode);
            req.setAttribute("cmn999Form", cmn999Form);

            //テンポラリディレクトリを削除する
            IOTools.deleteDir(_getRingiDir(req));

            con.commit();
            commit = true;
        } catch (Exception e) {
            log__.error(e);
            throw e;

        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 共通メッセージフォームにフォームパラメータを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn999Form 共通メッセージフォーム
     * @param form アクションフォーム
     * @param mode 確認種別
     * @return 共通メッセージフォーム
     */
    private Cmn999Form __setFormParam(Cmn999Form cmn999Form, Rng030Form form, int mode) {

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("rngSid", form.getRngSid());
        cmn999Form.addHiddenParam("rngTemplateMode", form.getRngTemplateMode());
        cmn999Form.addHiddenParam("rngProcMode", form.getRngProcMode());
        cmn999Form.addHiddenParam("rngCmdMode", form.getRngCmdMode());
        cmn999Form.addHiddenParam("rngApprMode", form.getRngApprMode());
        cmn999Form.addHiddenParam("rngDspMode", form.getRngDspMode());
        cmn999Form.addHiddenParam("rngSearchKeyword", form.getRngSearchKeyword());
        cmn999Form.addHiddenParam("rngSearchGroup", form.getRngSearchGroup());
        cmn999Form.addHiddenParam("rngSearchUser", form.getRngSearchUser());
        cmn999Form.addHiddenParam("rng010orderKey", form.getRng010orderKey());
        cmn999Form.addHiddenParam("rng010sortKey", form.getRng010sortKey());
        cmn999Form.addHiddenParam("rng010pageTop", form.getRng010pageTop());
        cmn999Form.addHiddenParam("rng010pageBottom", form.getRng010pageBottom());
        cmn999Form.addHiddenParam("rng030CmdMode", form.getRng030CmdMode());
        cmn999Form.addHiddenParam("rng030mode", form.getRng030mode());
        cmn999Form.addHiddenParam("rng030compBtnFlg", form.getRng030compBtnFlg());
        cmn999Form.addHiddenParam("rng030skipBtnFlg", form.getRng030skipBtnFlg());
        cmn999Form.addHiddenParam("rng030fileId", form.getRng030fileId());
        cmn999Form.addHiddenParam("rng030Comment", form.getRng030Comment());
        cmn999Form.addHiddenParam("rng030files", form.getRng030files());
        cmn999Form.addHiddenParam("rng030confirmMode", mode);
        cmn999Form.addHiddenParam("rng030Title", form.getRng030Title());
        cmn999Form.addHiddenParam("rng030Content", form.getRng030Content());

        cmn999Form.addHiddenParam("rngAdminKeyword", form.getRngAdminKeyword());
        cmn999Form.addHiddenParam("rngAdminGroupSid", form.getRngAdminGroupSid());
        cmn999Form.addHiddenParam("rngAdminUserSid", form.getRngAdminUserSid());
        cmn999Form.addHiddenParam("rngAdminApplYearFr", form.getRngAdminApplYearFr());
        cmn999Form.addHiddenParam("rngAdminApplMonthFr", form.getRngAdminApplMonthFr());
        cmn999Form.addHiddenParam("rngAdminApplDayFr", form.getRngAdminApplDayFr());
        cmn999Form.addHiddenParam("rngAdminApplYearTo", form.getRngAdminApplYearTo());
        cmn999Form.addHiddenParam("rngAdminApplMonthTo", form.getRngAdminApplMonthTo());
        cmn999Form.addHiddenParam("rngAdminApplDayTo", form.getRngAdminApplDayTo());
        cmn999Form.addHiddenParam("rngAdminLastManageYearFr", form.getRngAdminLastManageYearFr());
        cmn999Form.addHiddenParam("rngAdminLastManageMonthFr", form.getRngAdminLastManageMonthFr());
        cmn999Form.addHiddenParam("rngAdminLastManageDayFr", form.getRngAdminLastManageDayFr());
        cmn999Form.addHiddenParam("rngAdminLastManageYearTo", form.getRngAdminLastManageYearTo());
        cmn999Form.addHiddenParam("rngAdminLastManageMonthTo", form.getRngAdminLastManageMonthTo());
        cmn999Form.addHiddenParam("rngAdminLastManageDayTo", form.getRngAdminLastManageDayTo());
        cmn999Form.addHiddenParam("rngAdminSortKey", form.getRngAdminSortKey());
        cmn999Form.addHiddenParam("rngAdminOrderKey", form.getRngAdminOrderKey());
        cmn999Form.addHiddenParam("rngAdminPageTop", form.getRngAdminPageTop());
        cmn999Form.addHiddenParam("rngAdminPageBottom", form.getRngAdminPageBottom());
        cmn999Form.addHiddenParam("rngAdminSearchFlg", form.getRngAdminSearchFlg());
        cmn999Form.addHiddenParam("rngInputKeyword", form.getRngInputKeyword());
        cmn999Form.addHiddenParam("sltGroupSid", form.getSltGroupSid());
        cmn999Form.addHiddenParam("sltUserSid", form.getSltUserSid());
        cmn999Form.addHiddenParam("sltApplYearFr", form.getSltApplYearFr());
        cmn999Form.addHiddenParam("sltApplMonthFr", form.getSltApplMonthFr());
        cmn999Form.addHiddenParam("sltApplDayFr", form.getSltApplDayFr());
        cmn999Form.addHiddenParam("sltApplYearTo", form.getSltApplYearTo());
        cmn999Form.addHiddenParam("sltApplMonthTo", form.getSltApplMonthTo());
        cmn999Form.addHiddenParam("sltApplDayTo", form.getSltApplDayTo());
        cmn999Form.addHiddenParam("sltLastManageYearFr", form.getSltLastManageYearFr());
        cmn999Form.addHiddenParam("sltLastManageMonthFr", form.getSltLastManageMonthFr());
        cmn999Form.addHiddenParam("sltLastManageDayFr", form.getSltLastManageDayFr());
        cmn999Form.addHiddenParam("sltLastManageYearTo", form.getSltLastManageYearTo());
        cmn999Form.addHiddenParam("sltLastManageMonthTo", form.getSltLastManageMonthTo());
        cmn999Form.addHiddenParam("sltLastManageDayTo", form.getSltLastManageDayTo());

        cmn999Form.addHiddenParam("rngKeyword", form.getRngKeyword());
        cmn999Form.addHiddenParam("rng130Type", form.getRng130Type());
        cmn999Form.addHiddenParam("rng130keyKbn", form.getRng130keyKbn());
        cmn999Form.addHiddenParam("rng130searchSubject1", form.getRng130searchSubject1());
        cmn999Form.addHiddenParam("rng130searchSubject2", form.getRng130searchSubject2());
        cmn999Form.addHiddenParam("sltSortKey1", form.getSltSortKey1());
        cmn999Form.addHiddenParam("rng130orderKey1", form.getRng130orderKey1());
        cmn999Form.addHiddenParam("sltSortKey2", form.getSltSortKey2());
        cmn999Form.addHiddenParam("rng130orderKey2", form.getRng130orderKey1());
        cmn999Form.addHiddenParam("rng130pageTop", form.getRng130pageTop());
        cmn999Form.addHiddenParam("rng130pageBottom", form.getRng130pageBottom());

        cmn999Form.addHiddenParam("svRngKeyword", form.getSvRngKeyword());
        cmn999Form.addHiddenParam("svRng130Type", form.getSvRng130Type());
        cmn999Form.addHiddenParam("svGroupSid", form.getSvGroupSid());
        cmn999Form.addHiddenParam("svUserSid", form.getSvUserSid());
        cmn999Form.addHiddenParam("svRng130keyKbn", form.getSvRng130keyKbn());
        cmn999Form.addHiddenParam("svRng130searchSubject1", form.getSvRng130searchSubject1());
        cmn999Form.addHiddenParam("svRng130searchSubject2", form.getSvRng130searchSubject2());
        cmn999Form.addHiddenParam("svSortKey1", form.getSvSortKey1());
        cmn999Form.addHiddenParam("svRng130orderKey1", form.getSvRng130orderKey1());
        cmn999Form.addHiddenParam("svSortKey2", form.getSvSortKey2());
        cmn999Form.addHiddenParam("svRng130orderKey2", form.getSvRng130orderKey1());
        cmn999Form.addHiddenParam("svApplYearFr", form.getSvApplYearFr());
        cmn999Form.addHiddenParam("svApplMonthFr", form.getSvApplMonthFr());
        cmn999Form.addHiddenParam("svApplDayFr", form.getSvApplDayFr());
        cmn999Form.addHiddenParam("svApplYearTo", form.getSvApplYearTo());
        cmn999Form.addHiddenParam("svApplMonthTo", form.getSvApplMonthTo());
        cmn999Form.addHiddenParam("svApplDayTo", form.getSvApplDayTo());
        cmn999Form.addHiddenParam("svLastManageYearFr", form.getSvLastManageYearFr());
        cmn999Form.addHiddenParam("svLastManageMonthFr", form.getSvLastManageMonthFr());
        cmn999Form.addHiddenParam("svLastManageDayFr", form.getSvLastManageDayFr());
        cmn999Form.addHiddenParam("svLastManageYearTo", form.getSvLastManageYearTo());
        cmn999Form.addHiddenParam("svLastManageMonthTo", form.getSvLastManageMonthTo());
        cmn999Form.addHiddenParam("svLastManageDayTo", form.getSvLastManageDayTo());
        cmn999Form.addHiddenParam("rng130searchFlg", form.getRng130searchFlg());

        return cmn999Form;
    }
}
