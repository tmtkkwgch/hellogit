package jp.groupsession.v2.man.man080kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.AdminAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] メイン 管理者設定 自動バックアップ設定確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Man080knAction extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man080knAction.class);

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
        Man080knForm thisForm = (Man080knForm) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD==>" + cmd);

        if (cmd.equals("backInput")) {
            forward = map.findForward("backInput");
        } else if (cmd.equals("entry")) {
            //設定ボタンクリック
            forward = __doEntry(map, thisForm, req, res, con);

        } else {
            forward = map.getInputForward();
        }

        log__.debug("END");
        return forward;
    }

    /**
     * <br>[機  能] 設定ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doEntry(ActionMapping map, Man080knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        ActionForward forward = null;

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            forward = getSubmitErrorPage(map, req);
            return forward;
        }

        //セッション情報を取得
        RequestModel reqMdl = getRequestModel(req);
        int sessionUsrSid = reqMdl.getSmodel().getUsrsid();

        boolean commit = false;
        try {
            Man080knParamModel paramMdl = new Man080knParamModel();
            paramMdl.setParam(form);
            Man080knBiz biz = new Man080knBiz();
            biz.insertBackupConf(paramMdl, con, sessionUsrSid);
            paramMdl.setFormData(form);

            __setKanryou(map, req, form);
            forward = map.findForward("gf_msg");

            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.error("バックアップ設定の登録に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }
        //ログ出力
        CommonBiz cmnBiz = new CommonBiz();
        GsMessage gsMsg = new GsMessage(reqMdl);
        cmnBiz.outPutCommonLog(map, reqMdl, gsMsg, con,
                getInterMessage(reqMdl, "cmn.change"), GSConstLog.LEVEL_INFO, "");
        return forward;
    }
    /**
     * <br>[機  能] 削除完了画面設定処理
     * <br>[解  説] 削除完了画面のパラメータセット
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     */
    private void __setKanryou(
        ActionMapping map,
        HttpServletRequest req,
        Man080knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        urlForward = map.findForward("menu");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "touroku.kanryo.object";
        cmn999Form.setMessage(msgRes.getMessage(msgState,
                getInterMessage(req, GSConstMain.BACKUP_CONF_MSG)));
        req.setAttribute("cmn999Form", cmn999Form);

    }

}
