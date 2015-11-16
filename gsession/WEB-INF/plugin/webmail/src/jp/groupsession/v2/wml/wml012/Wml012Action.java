package jp.groupsession.v2.wml.wml012;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.wml.AbstractWebmailAction;
import jp.groupsession.v2.wml.biz.WmlBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] WEBメール 送信メール確認(ポップアップ)画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml012Action extends AbstractWebmailAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml012Action.class);

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
        Wml012Form thisForm = (Wml012Form) form;

        String cmd = NullDefault.getString(req.getParameter("wml012CMD"), "");

        if (cmd.equals("saveMail")) {
            __saveSendMailData(map, thisForm, req, res, con);
        } else {
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END");
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
    public ActionForward __doInit(ActionMapping map, Wml012Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        Wml012Biz biz = new Wml012Biz();
        Wml012ParamModel paramMdl = biz.getInitData(con, getRequestModel(req), getTempPath(req));
        if (paramMdl != null) {
            paramMdl.setFormData(form);
        }
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 送信メール情報の保存
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
    public ActionForward __saveSendMailData(ActionMapping map, Wml012Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        PrintWriter writer = null;
        RequestModel reqMdl = getRequestModel(req);
        try {
            //入力チェック
            if (!__validateSendMail(map, form, req, res, con)) {
                return null;
            }

            Wml012ParamModel paramMdl = new Wml012ParamModel();
            paramMdl.setParam(form);
            Wml012Biz biz = new Wml012Biz();
            biz.saveSendMailData(paramMdl, reqMdl, getTempPath(req));
            paramMdl.setFormData(form);
            res.setContentType("text/json; charset=UTF-8");
            writer = res.getWriter();
            writer.write("{");
            writer.write("\"error\" : \"0\"");
            writer.write("}");
        } catch (Exception e) {
            log__.error("メールの送信に失敗", e);
            res.setContentType("text/json; charset=UTF-8");
            writer = res.getWriter();
            writer.write("{");
            writer.write("\"error\" : \"1\",");
            writer.write("\"errorMessage\" : [");
            writer.write("\"" + Wml012Biz.escapeText(getInterMessage(req, "wml.js.40")) + "\"");
            writer.write("]");
            writer.write("}");

            //エラーログ出力
            String value = getInterMessage(req, "wml.js.40");
            WmlBiz wmlBiz = new WmlBiz();
            wmlBiz.outPutLog(map, reqMdl, con,
                    getInterMessage(req, "cmn.sent"), GSConstLog.LEVEL_ERROR,
                    StringUtil.trimRengeString(value, 3000));
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        return null;
    }

    /**
     * <br>[機  能] 送信メール情報の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return true: エラーなし false:エラーあり
     * @throws SQLException SQL実行例外
     * @throws Exception 実行時例外
     */
    private boolean __validateSendMail(ActionMapping map,
                                        Wml012Form form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con) throws SQLException, Exception {

        PrintWriter writer = null;
        RequestModel reqMdl = getRequestModel(req);

        boolean result = false;
        try {
            String[] message = form.validateSendMail(con, getGsContext(),
                                                                        getSessionUserSid(req),
                                                                        getAppRootPath(),
                                                                        getTempPath(req),
                                                                        reqMdl);
            result = message == null;
            if (!result) {
                //送信エラー
                res.setContentType("text/json; charset=UTF-8");
                writer = res.getWriter();
                writer.write("{");
                writer.write("\"error\" : \"1\",");
                writer.write("\"errorMessage\" : [");
                for (int index = 0; index < message.length; index++) {
                    if (index > 0) {
                        writer.write(",");
                    }
                    writer.write("\"" + Wml012Biz.escapeText(message[index]) + "\"");
                }
                writer.write("]");
                writer.write("}");
            }

            if (writer != null) {
                writer.flush();
            }

        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        return result;
    }
}
