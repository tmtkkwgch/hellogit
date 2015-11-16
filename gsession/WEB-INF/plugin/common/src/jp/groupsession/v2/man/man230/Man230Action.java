package jp.groupsession.v2.man.man230;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.ConfigBundle;
import jp.groupsession.v2.struts.AdminAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] メイン 管理者設定 システム情報画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man230Action extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man230Action.class);

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
     * @return アクションフォーム
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        ActionForward forward = null;
        Man230Form thisForm = (Man230Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("cmd = " + cmd);

        if (cmd.equals("backKtool")) {
            //戻るボタンクリック
            forward = map.findForward("ktool");

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
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
            Man230Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con)
    throws Exception {

        log__.debug("初期表示");

        ServletContext context = getServlet().getServletContext();
        String confDbDir = ConfigBundle.getValue("GSDATA_DIR");
        if (StringUtil.isNullZeroString(confDbDir)) {
            confDbDir = context.getRealPath("/");
        }

        Man230ParamModel paramMdl = new Man230ParamModel();
        paramMdl.setParam(form);
        Man230Biz biz = new Man230Biz();
        biz.setInitData(con, paramMdl, context, confDbDir, getRequestModel(req));
        paramMdl.setFormData(form);
        return map.getInputForward();
    }
}
