package jp.groupsession.v2.rng.rng040;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.rng.AbstractRingiAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 稟議 管理者設定メニュー画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng040Action extends AbstractRingiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng040Action.class);

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        return false;
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

        Rng040Form thisForm = (Rng040Form) form;
        if (cmd.equals("rng010")) {
            log__.debug("*** 戻るボタン押下。");
            forward = __doBack(map, thisForm);

        } else if (cmd.equals("rng060")) {
            log__.debug("*** 内容テンプレート一覧");
            //テンプレートモードセット
            //thisForm.setRngTemplateMode(RngConst.RNG_TEMPLATE_SHARE);
            forward = map.findForward("rng060");

        } else if (cmd.equals("rng050")) {
            log__.debug("*** 申請中案件管理");
            forward = map.findForward("rng050");

        } else if (cmd.equals("rng070")) {
            log__.debug("*** 完了案件管理");
            forward = map.findForward("rng070");

        } else if (cmd.equals("rng160")) {
            log__.debug("*** 自動削除設定");
            forward = map.findForward("rng160");

        } else if (cmd.equals("rng170")) {
            log__.debug("*** 手動データ削除");
            forward = map.findForward("rng170");

        } else if (cmd.equals("smlsetting")) {
            log__.debug("*** ショートメール通知設定");
            forward = map.findForward("smlsetting");

        } else if (cmd.equals("rng180")) {
            log__.debug("*** 基本設定");
            forward = map.findForward("rng180");

        } else {
            log__.debug("*** 初期表示を行います。");
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
    public ActionForward __doInit(ActionMapping map, Rng040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        if (__canUseSmlConf(form, req, con)) {
            form.setCanUseSml(GSConst.PLUGIN_USE);
        } else {
            form.setCanUseSml(GSConst.PLUGIN_NOT_USE);
        }
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 戻る処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng010Form
     * @return ActionForward
     */
    public ActionForward __doBack(ActionMapping map, Rng040Form form) {

        if (form.getBackScreen() == GSConstMain.BACK_MAIN_ADM_SETTING) {
            return map.findForward("mainAdmSetting");
        }
        return map.findForward("rng010");
    }
    /**
     * <br>[機  能] ショートメール設定が利用可能か判定
     * <br>[解  説]
     * <br>[備  考]
     * @param form フォーム
     * @param req リクエスト
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return true ショートメール使用可能
     *
     */
    private boolean __canUseSmlConf(Rng040Form form, HttpServletRequest req, Connection con)
    throws SQLException {

        //プラグイン設定を取得する
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));
        CommonBiz cmnBiz = new CommonBiz();
        //ショートメールは利用可能か判定
        return cmnBiz.isCanUsePlugin(GSConst.PLUGINID_SML, pconfig);
    }
}
