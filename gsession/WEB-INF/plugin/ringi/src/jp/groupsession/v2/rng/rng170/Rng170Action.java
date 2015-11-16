package jp.groupsession.v2.rng.rng170;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.rng.AbstractRingiAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 稟議 手動データ削除画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng170Action extends AbstractRingiAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng170Action.class);

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
        Rng170Form thisForm = (Rng170Form) form;

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        if (cmd.equals("confirm")) {
            // トランザクショントークン設定
            this.saveToken(req);
            //削除ボタンクリック
            forward = map.findForward("confirm");

        } else if (cmd.equals("backMenu")) {
            //戻るボタンクリック
            forward = map.findForward("rngAdmMenu");

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
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Rng170Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        Rng170Biz biz = new Rng170Biz();
        Rng170ParamModel paramMdl = new Rng170ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(getRequestModel(req), paramMdl);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }
}
