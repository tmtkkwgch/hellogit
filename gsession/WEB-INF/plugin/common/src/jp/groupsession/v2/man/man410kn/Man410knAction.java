package jp.groupsession.v2.man.man410kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.man400.Man400Biz;
import jp.groupsession.v2.man.man410.Man410Action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] メイン 統計情報手動削除確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man410knAction extends Man410Action {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man410knAction.class);

    /**
     * <br>[機  能] アクション実行
     * <br>[解  説] コントローラの役割を担います
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return アクションフォーム
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {
        log__.debug("START");

        //プラグイン使用制限をチェック
        Man400Biz biz = new Man400Biz();
        if (!biz.canUsePlugin(getSessionUserModel(req), con,
                getPluginConfigForMain(getPluginConfig(req), con))) {
            return map.findForward("gf_submit");
        }

        ActionForward forward = null;
        Man410knForm thisForm = (Man410knForm) form;

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);

        if (cmd.equals("decision")) {
            //確定ボタンクリック
            forward = __doDecision(map, thisForm, req, res, con);

        } else if (cmd.equals("backInput")) {
            //戻るボタンクリック
            forward = map.findForward("backInput");

        } else {
            //初期表示
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form フォーム
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Man410knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {
        Man410knParamModel paramMdl = new Man410knParamModel();
        paramMdl.setParam(form);
        Man410knBiz biz = new Man410knBiz();
        biz.setInitData(getRequestModel(req), paramMdl, con,
                getSessionUserModel(req), getPluginConfigForMain(getPluginConfig(req), con));
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form フォーム
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDecision(ActionMapping map, Man410knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        Man410knBiz biz = new Man410knBiz(con);

        //手動削除を行う
        Man410knParamModel paramMdl = new Man410knParamModel();
        paramMdl.setParam(form);
        RequestModel reqMdl = getRequestModel(req);
        biz.deleteLogCount(reqMdl, paramMdl, userSid);
        paramMdl.setFormData(form);

        __setCompPageParam(map, req, form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     */
    private void __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Man410knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = __backMenu(map, form);
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = null;
        msgState = "cmn.kanryo.object";
        cmn999Form.setMessage(
                msgRes.getMessage(msgState, getInterMessage(req, "cmn.statistics.manual.delete")));
        //画面パラメータをセット
        req.setAttribute("cmn999Form", cmn999Form);
    }

    /**
     * <br>[機  能] 戻り先の画面を判別する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @return ActionForward
     */
    private ActionForward __backMenu(ActionMapping map, Man410knForm form) {
        ActionForward forward = null;
        String orgnPage = form.getBackPlugin();
        if (orgnPage.equals(GSConst.PLUGINID_WML)) {
            //WEBメール管理者画面
            forward  = map.findForward("adminWebmail");
        } else if (orgnPage.equals(GSConst.PLUGINID_SML)) {
            //ショートメール管理者画面
            forward = map.findForward("adminSmail");
        } else if (orgnPage.equals(GSConst.PLUGINID_CIR)) {
            //回覧板管理者画面
            forward  = map.findForward("adminCircular");
        } else if (orgnPage.equals(GSConst.PLUGIN_ID_FILE)) {
            //ファイル管理管理者画面
            forward = map.findForward("adminFile");
        } else {
            //掲示板管理者画面
            forward = map.findForward("adminBulletin");
        }
        return forward;
    }
}
