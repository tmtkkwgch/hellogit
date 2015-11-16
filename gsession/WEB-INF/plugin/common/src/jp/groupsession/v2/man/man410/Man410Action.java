package jp.groupsession.v2.man.man410;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.man400.Man400Biz;
import jp.groupsession.v2.struts.AdminAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] メイン 統計情報手動削除設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man410Action extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man410Action.class);

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
        Man410Form thisForm = (Man410Form) form;

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        if (cmd.equals("confirm")) {
            // トランザクショントークン設定
            this.saveToken(req);
            //設定ボタンクリック
            forward = map.findForward("confirm");

        } else if (cmd.equals("admTool")) {
            //戻るボタンクリック
            forward = __backToMenu(map, thisForm);

        } else {
            //初期表示
            forward = __doInit(map, thisForm, req, res, con, cmd);
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
     * @param cmd コマンド
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Man410Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con, String cmd)
            throws SQLException {

        con.setAutoCommit(true);

        //初期表示情報を設定
        RequestModel reqMdl = getRequestModel(req);
        Man410ParamModel paramMdl = new Man410ParamModel();
        paramMdl.setParam(form);
        Man410Biz biz = new Man410Biz();
        biz.setInitData(reqMdl, paramMdl, cmd, con,
                getSessionUserModel(req), getPluginConfigForMain(getPluginConfig(req), con));
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

   /**
    * <br>[機  能] 戻り先の画面を判別する
    * <br>[解  説]
    * <br>[備  考]
    * @param map アクションマッピング
    * @param form アクションフォーム
    * @return ActionForward
    */
   private ActionForward __backToMenu(ActionMapping map, Man410Form form) {
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
