package jp.groupsession.v2.cir.cir050;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cir.AbstractCircularAction;
import jp.groupsession.v2.cir.biz.CirCommonBiz;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 回覧板 基本設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir050Action extends AbstractCircularAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cir050Action.class);

    /**
     * <br>[機  能] アクション実行
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
    public ActionForward executeAction(
        ActionMapping map,
        ActionForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        log__.debug("START_Cir050");
        ActionForward forward = null;

        Cir050Form thisForm = (Cir050Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("backToCir")) {
            log__.debug("戻るボタン押下");
            forward = map.findForward("return");

        } else if (cmd.equals("set")) {
            log__.debug("設定ボタン押下");
            forward = __doSet(map, thisForm, req, res, con);
        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        Cir050Biz biz = new Cir050Biz();
        thisForm.setCir050DspCntList(biz.getDspCntLabel());

        log__.debug("END_Cir050");
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
    private ActionForward __doInit(
        ActionMapping map,
        Cir050Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        con.setAutoCommit(true);
        Cir050ParamModel paramMdl = new Cir050ParamModel();
        paramMdl.setParam(form);
        Cir050Biz biz = new Cir050Biz();
        biz.setInitData(getRequestModel(req), paramMdl, con);
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

        //トランザクショントークン設定
        saveToken(req);

        return map.getInputForward();
    }

    /**
     * 追加(変更)ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doSet(ActionMapping map, Cir050Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        RequestModel reqMdl = getRequestModel(req);
        ActionForward forward = null;

        boolean commit = false;
        try {

            //回覧板個人設定の更新
            Cir050ParamModel paramMdl = new Cir050ParamModel();
            paramMdl.setParam(form);
            Cir050Biz biz = new Cir050Biz();
            biz.updateDspCount(reqMdl, paramMdl, con);
            paramMdl.setFormData(form);

            //回覧板個人設定完了画面へ
            forward = __doCompDsp(map, form, req, res);
            commit = true;
        } catch (SQLException e) {
            log__.error("回覧板個人設定の更新に失敗", e);
        } finally {
            if (commit) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textEdit = gsMsg.getMessage("cmn.change");

        //ログ出力処理
        CirCommonBiz cirBiz = new CirCommonBiz(con);
        cirBiz.outPutLog(map, reqMdl, textEdit, GSConstLog.LEVEL_INFO, "");

        return forward;
    }

    /**
     * 登録・変更完了画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @return ActionForward
     */
    private ActionForward __doCompDsp(ActionMapping map, Cir050Form form,
            HttpServletRequest req, HttpServletResponse res) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //回覧板 基本設定登録完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        GsMessage gsMsg = new GsMessage();
        String textPset = gsMsg.getMessage(req, "cir.5") + gsMsg.getMessage(req, "cmn.preferences");

        urlForward = map.findForward("return");
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("settei.kanryo.object", textPset));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("cir010cmdMode", form.getCir010cmdMode());
        cmn999Form.addHiddenParam("cir010order_key", form.getCir010orderKey());
        cmn999Form.addHiddenParam("cir010sort_key", form.getCir010sortKey());
        cmn999Form.addHiddenParam("cir010pageNum1", form.getCir010pageNum1());
        cmn999Form.addHiddenParam("cir010pageNum2", form.getCir010pageNum2());
        cmn999Form.addHiddenParam("cir010delInfSid", form.getCir010delInfSid());
        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

}
