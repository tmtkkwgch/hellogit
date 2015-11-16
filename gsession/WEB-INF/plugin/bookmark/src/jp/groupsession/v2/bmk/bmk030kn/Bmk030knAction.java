package jp.groupsession.v2.bmk.bmk030kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.bmk.AbstractBookmarkAction;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.biz.BookmarkBiz;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ブックマーク登録確認画面アクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk030knAction extends AbstractBookmarkAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk030knAction.class);

    /** 遷移元画面 */
    private String returnPage__;

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
     * @see jp.co.sjts.util.struts.AbstractAction
     * @see #executeAction(org.apache.struts.action.ActionMapping,
     *                      org.apache.struts.action.ActionForm,
     *                      javax.servlet.http.HttpServletRequest,
     *                      javax.servlet.http.HttpServletResponse,
     *                      java.sql.Connection)
     */
    public ActionForward executeAction(ActionMapping map,
                                        ActionForm form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con)
        throws Exception {

        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);

        Bmk030knForm thisForm = (Bmk030knForm) form;

        //遷移元画面セット
        if (thisForm.getBmk070ReturnPage().equals("bmk070")
                || thisForm.getBmk070ReturnPage().equals("bmk150")) {
            returnPage__ = thisForm.getBmk070ReturnPage();
        } else {
            returnPage__ = thisForm.getReturnPage();
        }

        if (cmd.equals("bmk030knpushOk")) {
            log__.debug("確定ボタン押下");
            forward = __doKakutei(map, thisForm, req, res, con);

        } else if (cmd.equals("bmk030knpushBack")) {
            log__.debug("戻るボタン押下");
            forward = map.findForward("bmk030");

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
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Bmk030knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {

        Bmk030knBiz biz = new Bmk030knBiz(con, getRequestModel(req));

        Bmk030knParamModel paramMdl = new Bmk030knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, getSessionUserModel(req));
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doKakutei(
        ActionMapping map,
        Bmk030knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception, SQLException {

        BaseUserModel buMdl = getSessionUserModel(req);

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェック
        con.setAutoCommit(true);
        ActionErrors errors = form.validateBmk030(con, buMdl, getRequestModel(req));
        con.setAutoCommit(false);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        //採番コントローラ
        MlCountMtController cntCon = getCountMtController(req);

        //ログインユーザSIDを取得
        int userSid = 0;
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //登録・更新処理を行う
        Bmk030knBiz biz = new Bmk030knBiz(con, getRequestModel(req));

        Bmk030knParamModel paramMdl = new Bmk030knParamModel();
        paramMdl.setParam(form);
        biz.doAddEdit(paramMdl, con, cntCon, userSid);
        paramMdl.setFormData(form);

        //ログ出力処理
        BookmarkBiz bmkBiz = new BookmarkBiz(con, getRequestModel(req));
        String opCode = "";

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "cmn.entry");
        String msg2 = gsMsg.getMessage(req, "cmn.edit");

        //新規登録
        if (form.getProcMode() == GSConstBookmark.BMK_MODE_TOUROKU) {
            opCode = msg;
        //編集
        } else {
            opCode = msg2;
        }

        bmkBiz.outPutLog(opCode,
               GSConstLog.LEVEL_TRACE,
               "[title]" + form.getBmk030title(), map.getType());

        //登録・更新完了画面を表示
        return __setKanryoDsp(map, form, req);
    }

    /**
     * [機  能] 完了画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Bmk030knForm form,
        HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward(returnPage__);
        cmn999Form.setUrlOK(forwardOk.getPath());

        MessageResources msgRes = getResources(req);

        int procMode = form.getProcMode();
        if (procMode == GSConstBookmark.BMK_MODE_TOUROKU) {
            //登録完了
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object",
                            StringUtilHtml.transToHTmlPlusAmparsant(form.getBmk030modeName())));
        } else if (procMode == GSConstBookmark.BMK_MODE_EDIT) {
            //更新完了
            cmn999Form.setMessage(
                    msgRes.getMessage("hensyu.kanryo.object",
                            StringUtilHtml.transToHTmlPlusAmparsant(form.getBmk030modeName())));
        }

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        cmn999Form.addHiddenParam("bmk070Page", form.getBmk070Page());
        cmn999Form.addHiddenParam("bmk070PageTop", form.getBmk070PageTop());
        cmn999Form.addHiddenParam("bmk070PageBottom", form.getBmk070PageBottom());
        cmn999Form.addHiddenParam("bmk070OrderKey", form.getBmk070OrderKey());
        cmn999Form.addHiddenParam("bmk070SortKey", form.getBmk070SortKey());
        cmn999Form.addHiddenParam("bmk070ReturnPage", form.getBmk070ReturnPage());
        cmn999Form.addHiddenParam("bmk080Page", form.getBmk080Page());
        cmn999Form.addHiddenParam("bmk080PageTop", form.getBmk080PageTop());
        cmn999Form.addHiddenParam("bmk080PageBottom", form.getBmk080PageBottom());
        cmn999Form.addHiddenParam("bmk150PageNum", form.getBmk150PageNum());
        cmn999Form.addHiddenParam("bmk070ToBmk150DspFlg",
                String.valueOf(form.isBmk070ToBmk150DspFlg()));

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
}