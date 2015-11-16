package jp.groupsession.v2.bmk.bmk060;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.bmk.AbstractBookmarkAction;
import jp.groupsession.v2.bmk.biz.BookmarkBiz;
import jp.groupsession.v2.bmk.bmk010.Bmk010Biz;
import jp.groupsession.v2.bmk.dao.BmkBelongLabelDao;
import jp.groupsession.v2.bmk.dao.BmkLabelDao;
import jp.groupsession.v2.bmk.model.BmkLabelModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ラベル登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Bmk060Action extends AbstractBookmarkAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk060Action.class);

    /**
     * <br>[機  能] アクション実行
     * <br>[解  説] コントローラの役割を担います
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        log__.debug("START");

        ActionForward forward = null;
        Bmk060Form bmkForm = (Bmk060Form) form;

        BookmarkBiz bmkBiz = new BookmarkBiz(con, getRequestModel(req));
        BaseUserModel model = this.getSessionUserModel(req);
        int bmkKbn = bmkForm.getBmk010mode();
        int usrSid = this.getSessionUserModel(req).getUsrsid();
        int gSid = bmkForm.getBmk010groupSid();

        // 編集権限チェック
        if (!bmkBiz.isEditPow(con, model, bmkKbn, usrSid, gSid)) {
            //権限エラー
            forward = getNotAdminSeniPage(map, req);
            return forward;
        }

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);

        if (cmd.equals("bmk060ok")) {
            //ＯＫボタンクリック
            forward = __doOk(map, bmkForm, req, res, con);
        } else if (cmd.equals("bmk060back")) {
            //戻るボタンクリック
            forward = map.findForward("bmk050");
        } else if (cmd.equals("bmk060del")) {
            forward = __doDeleteConfirmation(map, bmkForm, req, res, con);
        } else if (cmd.equals("deleteOk")) {
            forward = __doDeleteOk(map, bmkForm, req, res, con);
        } else if (cmd.equals("removeLabel")) {
            log__.debug("削除(左矢印)ボタン押下");
            forward = __doRight(map, bmkForm, req, res, con);
        } else if (cmd.equals("addLabel")) {
            log__.debug("追加(右矢印)ボタン押下");
            forward = __doLeft(map, bmkForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, bmkForm, req, res, con);
        }

        log__.debug("END");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException 実行例外
     */
    private ActionForward __doInit(ActionMapping map,
        Bmk060Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws SQLException {
        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk060Biz biz = new Bmk060Biz();

        Bmk060ParamModel paramMdl = new Bmk060ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);
        return map.getInputForward();
    }
    /**
     * <br>確認処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doOk(ActionMapping map, Bmk060Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {
        log__.debug("確認");

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        //入力チェック
        con.setAutoCommit(true);
        ActionErrors errors = form.validateCheck(con, sessionUserSid, req);
        con.setAutoCommit(false);

        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }
        saveToken(req);
        return map.findForward("bmk060kn");
    }
    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doLeft(
        ActionMapping map,
        Bmk060Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setBmk060LabelList(
                cmnBiz.getAddMember(form.getBmk060SelectRightLabel(), form.getBmk060LabelList()));

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doRight(
        ActionMapping map,
        Bmk060Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setBmk060LabelList(
                cmnBiz.getDeleteMember(form.getBmk060SelectLeftLabel()
                    , form.getBmk060LabelList()));

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除確認処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException SQL実行時例外
     * @throws Exception 実行時例外
     */
    private ActionForward __doDeleteConfirmation(ActionMapping map,
                                                  Bmk060Form form,
                                                  HttpServletRequest req,
                                                  HttpServletResponse res,
                                                  Connection con)
        throws Exception, SQLException {

        ActionForward forward = null;

        try {
            con.setAutoCommit(true);
            BmkLabelDao dao = new BmkLabelDao(con);
            BmkLabelModel model = dao.select(form.getBmk050LblSid());
            //ラベル名の設定
            form.setBmk060LblName(model.getBlbName());

            //削除確認画面を設定
            forward = __setConfirmationDsp(map, req, form);
            con.setAutoCommit(false);

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        }

        return forward;
    }

    /**
     * <br>[機  能] 確認画面設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @return ActionForward フォワード
     */
    private ActionForward __setConfirmationDsp(ActionMapping map,
                                                HttpServletRequest req,
                                                Bmk060Form form) {

        MessageResources msgRes = getResources(req);

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        ActionForward forwardOk = map.findForward("redraw");
        ActionForward forwardCancel = map.findForward("redraw");

        cmn999Form.setUrlOK(forwardOk.getPath() + "?" + GSConst.P_CMD + "=deleteOk");
        cmn999Form.setUrlCancel(forwardCancel.getPath());

        GsMessage gsMsg = new GsMessage();
        String label = gsMsg.getMessage(req, "cmn.label");

        cmn999Form.setMessage(
                msgRes.getMessage(
                    "sakujo.kakunin.once", label + "：" + form.getBmk060LblName()));

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        cmn999Form.addHiddenParam("bmk050DelSidList", form.getBmk050DelSidList());
        cmn999Form.addHiddenParam("bmk050ProcMode", form.getBmk050ProcMode());
        cmn999Form.addHiddenParam("bmk050LblSid", form.getBmk050LblSid());
        cmn999Form.addHiddenParam("bmk060LabelList", form.getBmk060LabelList());
        cmn999Form.addHiddenParam("bmk060LblKbn", form.getBmk060LblKbn());
        cmn999Form.addHiddenParam("bmk060LblName", form.getBmk060LblName());

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
    /**
     * <br>[機  能] 削除確認画面でOKボタン押下
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDeleteOk(ActionMapping map,
                                        Bmk060Form form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con)
        throws SQLException {

        boolean commitFlg = false;
        con.setAutoCommit(false);

        try {

            //削除処理実行
            BmkBelongLabelDao belognDao = new BmkBelongLabelDao(con);
            belognDao.delete(form.getBmk050LblSid());

            BmkLabelDao lblDao = new BmkLabelDao(con);
            lblDao.delete(form.getBmk050LblSid());

            GsMessage gsMsg = new GsMessage();
            String delete = gsMsg.getMessage(req, "cmn.delete");

            //ログ出力処理
            BookmarkBiz bmkBiz = new BookmarkBiz(con, getRequestModel(req));
            String opCode = delete;

            bmkBiz.outPutLog(opCode,
                    GSConstLog.LEVEL_TRACE,
                    "[name]" + form.getBmk060LblName(), map.getType());

            commitFlg = true;

            //完了画面設定
            return __setCompDsp(map, req, form);

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
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
                                        Bmk060Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("bmk050");
        cmn999Form.setUrlOK(forwardOk.getPath());

        GsMessage gsMsg = new GsMessage();
        String label = gsMsg.getMessage(req, "cmn.label");

        //メッセージ
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object", label));

        //画面パラメータをセット
        if (form.getBmk010searchLabel() == form.getBmk050LblSid()) {
            //検索指定ラベルの削除
            form.setBmk010searchLabel(Bmk010Biz.INIT_VALUE);
        }
        form.setHiddenParam(cmn999Form);
        cmn999Form.addHiddenParam("bmk050DelSidList", form.getBmk050DelSidList());

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
}
