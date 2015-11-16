package jp.groupsession.v2.cmn.cmn002;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.struts.AbstractGsAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] フレーム(メニューとボディ)のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn002Action extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn002Action.class);

    /**
     * <p>
     * アクション実行
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        log__.debug("START");

        ActionForward forward = null;
        Cmn002Form cmnForm = (Cmn002Form) form;
        forward = __doDisp(map, cmnForm, req, res, con);
        return forward;
    }

    /**
     * <p>表示
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    public ActionForward __doDisp(ActionMapping map, Cmn002Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        ActionForward forward = map.getInputForward();

        BaseUserModel umodel = getSessionUserModel(req);
        if (umodel == null) {
            return null;
        }

        if (umodel.getUsrsid() == 0) {
            //初期管理者ユーザの場合
            //管理者ツールURLをセット
            ActionForward defforward = map.findForward("admin");
            String url = ".." + defforward.getPath();
            form.setUrl(url);
        }

        //入力チェック
        PluginConfig pconfig = getPluginConfig(req);
        ActionErrors errors = form.validateBodyUrl(map, req, pconfig);
        if (errors.size() > 0) {
            log__.debug("エラー " + errors.size());
            addErrors(req, errors);
            //デフォルトURLをセット
            form.setUrl(__getDefaultUrl(map));
        }

        //未入力の場合
        if (form.getUrl() == null || form.getUrl().length() <= 0) {
            //デフォルトURLをセット
            form.setUrl(__getDefaultUrl(map));
        }
        log__.debug("BODY URL is " + form.getUrl());
        return forward;
    }

    /**
     * BodyデフォルトのURLを返す
     * @param map アクションマッピング
     * @return デフォルトURL
     */
    private String __getDefaultUrl(ActionMapping map) {
        ActionForward defforward = map.findForward("main");
        String url = ".." + defforward.getPath();
        return url;
    }
}
