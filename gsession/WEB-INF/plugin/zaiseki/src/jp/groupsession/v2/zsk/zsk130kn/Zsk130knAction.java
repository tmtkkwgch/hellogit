package jp.groupsession.v2.zsk.zsk130kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.struts.AbstractGsAction;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.zsk.biz.ZsjCommonBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 在席管理 個人設定 座席表メンバー表示設定確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk130knAction extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Zsk130knAction.class);

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

        //座席表メンバー表示の設定を許可されていない場合、エラーページへ遷移
        ZsjCommonBiz zskBiz = new ZsjCommonBiz(getRequestModel(req));
        if (!zskBiz.canEditViewMember(con)) {
            return getSubmitErrorPage(map, req);
        }

        ActionForward forward = null;
        Zsk130knForm zskForm = (Zsk130knForm) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        if (cmd.equals("updateZsk130")) {
            //OK
            forward = __doUpdate(map, zskForm, req, res, con);
        } else if (cmd.equals("backZsk130")) {
            //戻る
            forward = map.findForward("backZsk130");
        } else {
            //デフォルト
            forward = __doInit(map, zskForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     */
    private ActionForward __doInit(ActionMapping map,
                                    Zsk130knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) {

        Zsk130knBiz biz = new Zsk130knBiz(getRequestModel(req));

        Zsk130knParamModel paramMdl = new Zsk130knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl);
        paramMdl.setFormData(form);

        ActionForward forward = map.getInputForward();
        return forward;
    }

    /**
     * <br>[機  能] 更新処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doUpdate(ActionMapping map,
                                      Zsk130knForm form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException {

        con.setAutoCommit(false);
        boolean commitFlg = false;
        BaseUserModel umodel = getSessionUserModel(req);

        try {

            Zsk130knBiz biz = new Zsk130knBiz(getRequestModel(req));

            Zsk130knParamModel paramMdl = new Zsk130knParamModel();
            paramMdl.setParam(form);
            biz.updatePriConf(paramMdl, con, umodel);
            paramMdl.setFormData(form);

            commitFlg = true;

            GsMessage gsMsg = new GsMessage();
            String msg = gsMsg.getMessage(req, "cmn.edit");

            //ログ出力
            ZsjCommonBiz cmnBiz = new ZsjCommonBiz(getRequestModel(req));
            cmnBiz.outPutLog(con,
                             msg, GSConstLog.LEVEL_INFO, "", map.getType());

            ActionForward forward = __setCompPageParam(map, req, form);
            return forward;

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
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マッピング
     * @param req リクエスト
     * @param form フォーム
     * @return ActionForward
     */
    private ActionForward __setCompPageParam(ActionMapping map,
                                              HttpServletRequest req,
                                              Zsk130knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        ActionForward urlForward = map.findForward("zsk130UpdComp");
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "zsk.28");

        //メッセージセット
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(msgRes.getMessage("touroku.kanryo.object", msg));
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("selectZifSid", form.getSelectZifSid());
        cmn999Form.addHiddenParam("uioStatus", form.getUioStatus());
        cmn999Form.addHiddenParam("uioStatusBiko", form.getUioStatusBiko());
        cmn999Form.addHiddenParam("sortKey", form.getSortKey());
        cmn999Form.addHiddenParam("orderKey", form.getOrderKey());
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}