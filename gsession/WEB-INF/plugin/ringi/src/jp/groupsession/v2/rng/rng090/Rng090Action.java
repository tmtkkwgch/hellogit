package jp.groupsession.v2.rng.rng090;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
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
 * <br>[機  能] 稟議 テンプレート登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng090Action extends AbstractRingiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng090Action.class);

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        Rng090Form thisForm = (Rng090Form) form;
        int tFlg = thisForm.getRngTemplateMode();
        if (tFlg == RngConst.RNG_TEMPLATE_SHARE) {
            return false;
        } else {
            return true;
        }
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

        Rng090Form thisForm = (Rng090Form) form;
        Rng090Biz biz = new Rng090Biz(con, getRequestModel(req));
        if (cmd.equals("ok090")) {
            log__.debug("*** OKボタン。");
            forward = __doOk(map, thisForm, req, res, con);

        } else if (cmd.equals("rng060")) {
            log__.debug("*** 内容テンプレート一覧。");
            forward = map.findForward("rng060");

        } else if (cmd.equals("cmn999del")) {
            log__.debug("*** 削除ボタン。");
            forward = __setDeleteDsp(map, req, thisForm);

        } else if (cmd.equals("delexe")) {
            log__.debug("*** 削除確認OK。");
            forward = __doDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("delTempfile")) {
            log__.debug("*** 添付ファイル削除ボタン。");
            forward = __doTempDelete(map, thisForm, req, res, con);

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

        } else {
            log__.debug("*** 初期表示を行います。処理モード＝" + thisForm.getRngTplCmdMode());
            if (cmd.equals("060title") || cmd.equals("rng090")) {
                log__.debug("テンポラリディレクトリ ファイルの削除を行います。");
                biz.doDeleteFile(_getRingiTemplateDir(req));
            } else {
                log__.debug("テンポラリディレクトリ ファイルの削除を行いませんでした。");
            }
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng010Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Rng090Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        BaseUserModel buMdl = getSessionUserModel(req);
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        Rng090ParamModel paramMdl = new Rng090ParamModel();
        paramMdl.setParam(form);
        Rng090Biz biz = new Rng090Biz(con, getRequestModel(req));
        form.setRng090UserSid(buMdl.getUsrsid());
        biz.initDsp(paramMdl, getAppRootPath(), _getRingiTemplateDir(req), cmd, buMdl.getUsrsid());
        paramMdl.setFormData(form);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] OKボタンクリック時
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng010Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    private ActionForward __doOk(ActionMapping map, Rng090Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        errors = form.validateCheck(getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        // トランザクショントークン設定
        saveToken(req);

        return map.findForward("rng090kn");
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
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    private ActionForward __doTempDelete(
        ActionMapping map,
        Rng090Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //テンポラリディレクトリパスを取得
        String tempDir = _getRingiTemplateDir(req);
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //選択された添付ファイルを削除する
        CommonBiz biz = new CommonBiz();
        biz.deleteFile(form.getRng090files(), tempDir);

        Rng090ParamModel paramMdl = new Rng090ParamModel();
        paramMdl.setParam(form);
        Rng090Biz rbiz = new Rng090Biz(con, getRequestModel(req));
        rbiz.setFileLabelList(paramMdl, tempDir);
        paramMdl.setFormData(form);
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除確認画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @return ActionForward フォワード
     */
    private ActionForward __setDeleteDsp(ActionMapping map,
                                          HttpServletRequest req,
                                          Rng090Form form) {

        saveToken(req);

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("delexe");
        cmn999Form.setUrlOK(forwardOk.getPath() + "?" + GSConst.P_CMD + "=delexe");

        //キャンセルボタンクリック時遷移先
        ActionForward forwardCancel = map.findForward("delback");
        cmn999Form.setUrlCancel(forwardCancel.getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "rng.92");

        //メッセージ
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
                msgRes.getMessage(
                        "sakujo.kakunin.once", msg));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("rngProcMode", form.getRngProcMode());
        cmn999Form.addHiddenParam("rng010orderKey", form.getRng010orderKey());
        cmn999Form.addHiddenParam("rng010sortKey", form.getRng010sortKey());
        cmn999Form.addHiddenParam("rng010pageTop", form.getRng010pageTop());
        cmn999Form.addHiddenParam("rng010pageBottom", form.getRng010pageBottom());

        cmn999Form.addHiddenParam("rng090title", form.getRng090title());
        cmn999Form.addHiddenParam("rng090rngTitle", form.getRng090rngTitle());
        cmn999Form.addHiddenParam("rng090content", form.getRng090content());
        cmn999Form.addHiddenParam("rng090CatSid", form.getRng090CatSid());
        cmn999Form.addHiddenParam("rng090users", form.getRng090users());
        cmn999Form.addHiddenParam("rng090apprUser", form.getRng090apprUser());
        cmn999Form.addHiddenParam("rng090confirmUser", form.getRng090confirmUser());
        cmn999Form.addHiddenParam("rng090group", form.getRng090group());

        cmn999Form.addHiddenParam("rngTemplateMode", form.getRngTemplateMode());
        cmn999Form.addHiddenParam("rngTplCmdMode", form.getRngTplCmdMode());
        cmn999Form.addHiddenParam("rngSelectTplSid", form.getRngSelectTplSid());
        cmn999Form.addHiddenParam("rng060SelectCat", form.getRng060SelectCat());
        cmn999Form.addHiddenParam("rng060SelectCatUsr", form.getRng060SelectCatUsr());

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 削除実行
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    private ActionForward __doDelete(
        ActionMapping map,
        Rng090Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

      //トランザクショントークンチェック
        if (!isTokenValid(req, true)) {
            log__.info("トランザクショントークンエラー");
            return getSubmitErrorPage(map, req);
        }

        ActionForward forward = null;
        boolean commit = false;
        try {
            RequestModel reqMdl = getRequestModel(req);
            Rng090ParamModel paramMdl = new Rng090ParamModel();
            paramMdl.setParam(form);
            Rng090Biz biz = new Rng090Biz(con, reqMdl);
            //テンプレートの削除
            biz.deleteTpl(paramMdl, getSessionUserSid(req), form.getRngSelectTplSid());
            paramMdl.setFormData(form);
            forward = __setCompDsp(map, req, form);
            GsMessage gsMsg = new GsMessage(reqMdl);
            String msg = gsMsg.getMessage("cmn.delete");

            String opCode = msg;

            //ログ出力処理
            RngBiz rngBiz = new RngBiz(con);
            rngBiz.outPutLog(
                    map, opCode,
                    GSConstLog.LEVEL_INFO,
                    "[title]" + form.getRng090title(),
                    reqMdl);

            con.commit();
            commit = true;
        } catch (Exception e) {
            log__.error("稟議テンプレート情報の登録に失敗", e);
        } finally {
            if (!commit) {
                con.rollback();
            }
        }


        return forward;
    }

    /**
     * <br>[機  能] 削除完了画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @return ActionForward フォワード
     */
    private ActionForward __setCompDsp(ActionMapping map,
                                        HttpServletRequest req,
                                        Rng090Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = null;

        forwardOk = map.findForward("rng060");

        cmn999Form.setUrlOK(forwardOk.getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "rng.92");

        //メッセージ
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object", msg));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("rngProcMode", form.getRngProcMode());
        cmn999Form.addHiddenParam("rng010orderKey", form.getRng010orderKey());
        cmn999Form.addHiddenParam("rng010sortKey", form.getRng010sortKey());
        cmn999Form.addHiddenParam("rng010pageTop", form.getRng010pageTop());
        cmn999Form.addHiddenParam("rng010pageBottom", form.getRng010pageBottom());

        cmn999Form.addHiddenParam("rngTemplateMode", form.getRngTemplateMode());
        cmn999Form.addHiddenParam("rngTplCmdMode", form.getRngTplCmdMode());
        cmn999Form.addHiddenParam("rng060SelectCat", form.getRng060SelectCat());
        cmn999Form.addHiddenParam("rng060SelectCatUsr", form.getRng060SelectCatUsr());

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
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
        Rng090Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        RngBiz rngBiz = new RngBiz(con);
        form.setRng090apprUser(rngBiz.getUpMember(form.getRng090selectApprUser(),
                                                form.getRng090apprUser()));

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
        Rng090Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        RngBiz rngBiz = new RngBiz(con);
        form.setRng090apprUser(rngBiz.getDownMember(form.getRng090selectApprUser(),
                                                    form.getRng090apprUser()));

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
        Rng090Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng090apprUser(
                cmnBiz.getAddMember(form.getRng090users(), form.getRng090apprUser()));
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
        Rng090Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng090apprUser(
                cmnBiz.getDeleteMember(form.getRng090selectApprUser(), form.getRng090apprUser()));

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
        Rng090Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng090confirmUser(
                cmnBiz.getAddMember(form.getRng090users(), form.getRng090confirmUser()));

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
        Rng090Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng090confirmUser(
                cmnBiz.getDeleteMember(
                        form.getRng090selectConfirmUser(), form.getRng090confirmUser()));

        return  __doInit(map, form, req, res, con);
    }
}
