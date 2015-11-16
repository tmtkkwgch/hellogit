package jp.groupsession.v2.sml.sml190;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.AbstractSmlAction;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ショートメール 個人設定 メイン表示設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml190Action extends AbstractSmlAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml190Action.class);

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
     * @see jp.groupsession.v2.sml.AbstractSmlAction
     * @see #executeAction(org.apache.struts.action.ActionMapping,
     *                      org.apache.struts.action.ActionForm,
     *                      javax.servlet.http.HttpServletRequest,
     *                      javax.servlet.http.HttpServletResponse,
     *                      java.sql.Connection)
     */
    public ActionForward executeSmail(ActionMapping map,
                                       ActionForm form,
                                       HttpServletRequest req,
                                       HttpServletResponse res,
                                       Connection con)
        throws Exception {

        ActionForward forward = null;
        Sml190Form thisForm = (Sml190Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        //戻るボタン押下
        if (cmd.equals("backToList")) {
            log__.debug("戻るボタン押下");
            return map.findForward("backToList");
        //設定ボタン押下
        } else if (cmd.equals("edit")) {
            log__.debug("設定ボタン押下");
            forward = __doSet(map, thisForm, req, res, con);
        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示を行う
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
    private ActionForward __doInit(ActionMapping map,
                                    Sml190Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException {

        con.setAutoCommit(true);

        Sml190ParamModel paramMdl = new Sml190ParamModel();
        paramMdl.setParam(form);
        Sml190Biz biz = new Sml190Biz();
        biz.setInitData(getRequestModel(req), paramMdl, con);
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 設定ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doSet(ActionMapping map,
                                   Sml190Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con)
        throws SQLException {

        ActionForward forward = null;
        boolean commit = false;

        RequestModel reqMdl = getRequestModel(req);
        try {

            //ショートメール個人設定の更新
            Sml190ParamModel paramMdl = new Sml190ParamModel();
            paramMdl.setParam(form);
            Sml190Biz biz = new Sml190Biz();
            biz.updateMainDsp(paramMdl, con, reqMdl);
            paramMdl.setFormData(form);

            GsMessage gsMsg = new GsMessage(reqMdl);
            String edit = gsMsg.getMessage("cmn.edit");
            String outOpLog = biz.getOpLog(reqMdl, paramMdl);
            //ログ出力処理
            SmlCommonBiz smlBiz = new SmlCommonBiz(con, reqMdl);
            smlBiz.outPutLog(map, reqMdl,
                    edit, GSConstLog.LEVEL_INFO, outOpLog);

            //完了画面
            forward = __doCompDsp(map, form, req, res);
            commit = true;

        } catch (SQLException e) {
            log__.error("ショートメール個人設定更新失敗", e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            } else {
                con.rollback();
            }
        }
        return forward;
    }

    /**
     * <br>[機  能] 完了画面設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @return ActionForward
     */
    private ActionForward __doCompDsp(ActionMapping map,
                                       Sml190Form form,
                                       HttpServletRequest req,
                                       HttpServletResponse res) {

        ActionForward forward = null;
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        GsMessage gsMsg = new GsMessage();
        String mailMainDsp = gsMsg.getMessage(req, "sml.sml190.01");

        //OKボタンクリック時遷移先
        urlForward = map.findForward("backToList");
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(
                msgRes.getMessage("settei.kanryo.object", mailMainDsp));

        //hiddenパラメータ
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("sml010ProcMode", form.getSml010ProcMode());
        cmn999Form.addHiddenParam("sml010Sort_key", form.getSml010Sort_key());
        cmn999Form.addHiddenParam("sml010Order_key", form.getSml010Order_key());
        cmn999Form.addHiddenParam("sml010PageNum", form.getSml010PageNum());
        cmn999Form.addHiddenParam("sml010SelectedSid", form.getSml010SelectedSid());
        cmn999Form.addHiddenParam("sml010DelSid", form.getSml010DelSid());
        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }
}