package jp.groupsession.v2.cmn.cmn200;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.json.JSONArray;
import jp.groupsession.v2.struts.AbstractGsAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] ポップアップカレンダー休日取得のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn200Action extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn200Action.class);

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

        ActionForward forward = null;
        Cmn200Form thisForm = (Cmn200Form) form;

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);

        forward = __doInit(map, thisForm, req, res, con);

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
     * @throws Exception 実行例外
     * @return ActionForward
     */
    private ActionForward __doInit(
        ActionMapping map,
        Cmn200Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //初期表示情報を画面にセットする
        Cmn200Biz biz = new Cmn200Biz();
        JSONArray jsonArray = biz.setInitData(con, form.getCmn200dateStr());

        PrintWriter writer = null;
        log__.debug("jsonArray!!=" + jsonArray);
        try {
            res.setContentType("text/txt; charset=UTF-8");
            writer = res.getWriter();
            if (jsonArray != null) {
                writer.println(jsonArray);
            } else {
                writer.println("{\"result\" : \"1\"}");
            }
            writer.flush();
        } catch (Exception e) {
            log__.debug("書き込みに失敗。");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        return null;
    }
}
