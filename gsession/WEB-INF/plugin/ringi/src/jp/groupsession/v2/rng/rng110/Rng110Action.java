package jp.groupsession.v2.rng.rng110;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
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
 * <br>[機  能] 稟議 経路テンプレート登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng110Action extends AbstractRingiAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng110Action.class);

    /** 処理モード 登録 */
    private static final int COMPMODE_ENTRY__ = 0;
    /** 処理モード 削除 */
    private static final int COMPMODE_DELETE__ = 1;

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

        Rng110Form thisForm = (Rng110Form) form;
        if (cmd.equals("backList")) {
            forward = map.findForward("keirolist");

        } else if (cmd.equals("rng060")) {
            log__.debug("*** 内容テンプレート一覧。");
            forward = map.findForward("rng060");

        } else if (cmd.equals("upAppr")) {
            log__.debug("上矢印(承認経路)ボタンクリック");
            forward = __doUpAppr(map, thisForm, req, res, con);

        } else if (cmd.equals("downAppr")) {
            log__.debug("下矢印(承認経路)ボタンクリック");
            forward = __doDownAppr(map, thisForm, req, res, con);

        } else if (cmd.equals("addAppr")) {
            log__.debug("追加(承認経路)ボタンクリック");
            forward = __doAddAppr(map, thisForm, req, res, con);

        } else if (cmd.equals("delAppr")) {
            log__.debug("削除(承認経路)ボタンクリック");
            forward = __doDelAppr(map, thisForm, req, res, con);

        } else if (cmd.equals("addConfirm")) {
            log__.debug("追加(最終確認)ボタンクリック");
            forward = __doAddConfirm(map, thisForm, req, res, con);

        } else if (cmd.equals("delConfirm")) {
            log__.debug("削除(最終確認)ボタンクリック");
            forward = __doDelConfirm(map, thisForm, req, res, con);

        } else if (cmd.equals("delete")) {
            log__.debug("削除ボタンクリック");
            forward = __doDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteConfirm")) {
            log__.debug("削除確認画面からの遷移");
            forward = __doDeleteComplete(map, thisForm, req, res, con);

        } else if (cmd.equals("entry")) {
            log__.debug("OKボタンクリック");
            forward = __doEntry(map, thisForm, req, res, con);

        } else {
            log__.debug("*** 初期表示を行います。");
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng020Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Rng110Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        Rng110ParamModel paramMdl = new Rng110ParamModel();
        paramMdl.setParam(form);
        Rng110Biz biz = new Rng110Biz(con);
        biz.setInitData(req, paramMdl, getSessionUserSid(req));
        paramMdl.setFormData(form);

        saveToken(req);
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 上矢印(承認経路)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doUpAppr(
        ActionMapping map,
        Rng110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        RngBiz rngBiz = new RngBiz(con);
        form.setRng110apprUser(rngBiz.getUpMember(form.getRng110selectApprUser(),
                                                form.getRng110apprUser()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 下矢印(承認経路)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDownAppr(
        ActionMapping map,
        Rng110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        RngBiz rngBiz = new RngBiz(con);
        form.setRng110apprUser(rngBiz.getDownMember(form.getRng110selectApprUser(),
                                                    form.getRng110apprUser()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(承認経路)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doAddAppr(
        ActionMapping map,
        Rng110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng110apprUser(
                cmnBiz.getAddMember(form.getRng110users(), form.getRng110apprUser()));
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(承認経路)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDelAppr(
        ActionMapping map,
        Rng110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng110apprUser(
                cmnBiz.getDeleteMember(form.getRng110selectApprUser(), form.getRng110apprUser()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(最終確認)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doAddConfirm(
        ActionMapping map,
        Rng110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng110confirmUser(
                cmnBiz.getAddMember(form.getRng110users(), form.getRng110confirmUser()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(最終確認)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDelConfirm(
        ActionMapping map,
        Rng110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng110confirmUser(
                cmnBiz.getDeleteMember(
                        form.getRng110selectConfirmUser(), form.getRng110confirmUser()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除ボタンクリック時の処理
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
    private ActionForward __doDelete(
        ActionMapping map,
        Rng110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        Cmn999Form cmn999Form = new Cmn999Form();

        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "rng.25");

        //種別により確認メッセージを変更
        cmn999Form.setMessage(msgRes.getMessage("sakujo.kakunin.once", msg));

        cmn999Form.setUrlOK(map.findForward("mine").getPath() + "?CMD=deleteConfirm");
        cmn999Form.setUrlCancel(map.findForward("mine").getPath());

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("rngProcMode", form.getRngProcMode());
        cmn999Form.addHiddenParam("rng010orderKey", form.getRng010orderKey());
        cmn999Form.addHiddenParam("rng010sortKey", form.getRng010sortKey());
        cmn999Form.addHiddenParam("rng010pageTop", form.getRng010pageTop());
        cmn999Form.addHiddenParam("rng010pageBottom", form.getRng010pageBottom());
        cmn999Form.addHiddenParam("rctSid", form.getRctSid());
        cmn999Form.addHiddenParam("rngRctCmdMode", form.getRngRctCmdMode());
        cmn999Form.addHiddenParam("rng110name", form.getRng110name());
        cmn999Form.addHiddenParam("rng110users", form.getRng110users());
        cmn999Form.addHiddenParam("rng110apprUser", form.getRng110apprUser());
        cmn999Form.addHiddenParam("rng110confirmUser", form.getRng110confirmUser());
        cmn999Form.addHiddenParam("rng110group", form.getRng110group());

        req.setAttribute("cmn999Form", cmn999Form);

        saveToken(req);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 削除ボタンクリック時の処理
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
    private ActionForward __doDeleteComplete(
        ActionMapping map,
        Rng110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        ActionForward forward = null;

        boolean commit = false;
        try {
            Rng110ParamModel paramMdl = new Rng110ParamModel();
            paramMdl.setParam(form);
            Rng110Biz biz = new Rng110Biz(con);
            biz.deleteKeiroTemplate(paramMdl, getSessionUserSid(req));
            forward = __setCompPageParam(map, req, form, COMPMODE_DELETE__);
            paramMdl.setFormData(form);

            GsMessage gsMsg = new GsMessage();
            String msg = gsMsg.getMessage(req, "cmn.delete");

            String opCode = msg;

            //ログ出力処理
            RngBiz rngBiz = new RngBiz(con);
            rngBiz.outPutLog(
                    map, opCode,
                    GSConstLog.LEVEL_INFO,
                    "[name]" + form.getRng110name(),
                    getRequestModel(req));

            con.commit();
            commit = true;

        } catch (Exception e) {
            log__.error("経路テンプレート情報の削除に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        return forward;
    }

    /**
     * <br>[機  能] 稟議情報登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doEntry(
        ActionMapping map,
        Rng110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (errors != null && !errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        ActionForward forward = null;
        boolean commit = false;
        try {
            Rng110ParamModel paramMdl = new Rng110ParamModel();
            paramMdl.setParam(form);
            Rng110Biz biz = new Rng110Biz(con);
            biz.entryKeiroTemplate(paramMdl, getCountMtController(req),
                            getSessionUserSid(req));
            paramMdl.setFormData(form);

            forward = __setCompPageParam(map, req, form, COMPMODE_ENTRY__);

            RequestModel reqMdl = getRequestModel(req);
            GsMessage gsMsg = new GsMessage(reqMdl);
            String entry = gsMsg.getMessage("cmn.entry");
            String edit = gsMsg.getMessage("cmn.edit");

            //ログ出力処理
            RngBiz rngBiz = new RngBiz(con);
            String opCode = "";

            if (form.getRngRctCmdMode() == RngConst.RNG_CMDMODE_ADD) {
                opCode = entry;
            } else if (form.getRngRctCmdMode() == RngConst.RNG_CMDMODE_EDIT) {
                opCode = edit;
            }

            rngBiz.outPutLog(
                    map, opCode,
                    GSConstLog.LEVEL_INFO,
                    "[name]" + form.getRng110name(),
                    reqMdl);

            con.commit();
            commit = true;

        } catch (Exception e) {
            log__.error("経路テンプレート情報の登録に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        return forward;
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @param mode 処理モード
     * @return ActionForward
     */
    private ActionForward __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Rng110Form form,
        int mode) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("keirolist");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = null;
        if (mode == COMPMODE_ENTRY__) {
            if (form.getRngCmdMode() == RngConst.RNG_CMDMODE_ADD) {
                msgState = "touroku.kanryo.object";
            } else if (form.getRngCmdMode() == RngConst.RNG_CMDMODE_EDIT) {
                msgState = "hensyu.kanryo.object";
            }
        } else if (mode == COMPMODE_DELETE__) {
            msgState = "sakujo.kanryo.object";
        }
        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "rng.25");

        cmn999Form.setMessage(msgRes.getMessage(msgState, msg));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("rngProcMode", form.getRngProcMode());
        cmn999Form.addHiddenParam("rng010orderKey", form.getRng010orderKey());
        cmn999Form.addHiddenParam("rng010sortKey", form.getRng010sortKey());
        cmn999Form.addHiddenParam("rng010pageTop", form.getRng010pageTop());
        cmn999Form.addHiddenParam("rng010pageBottom", form.getRng010pageBottom());

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

}
