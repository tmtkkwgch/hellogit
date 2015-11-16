package jp.groupsession.v2.wml.wml080;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.WmlTempfileModel;
import jp.groupsession.v2.wml.AbstractWebmailAction;
import jp.groupsession.v2.wml.biz.WmlBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] WEBメール メール詳細ポップアップのアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml080Action extends AbstractWebmailAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml080Action.class);

    /**
     * <br>[機  能] キャッシュを有効にして良いか判定を行う
     * <br>[解  説] ダウンロード時のみ有効にする
     * <br>[備  考]
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:有効にする,false:無効にする
     */
    public boolean isCacheOk(HttpServletRequest req, ActionForm form) {

        //CMD
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        return (cmd.equals("downloadFile"));
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
        Wml080Form thisForm = (Wml080Form) form;

        //管理者権限チェック
        if (!_checkAuth(map, req, con)) {
            return map.findForward("gf_power");
        }

        if (NullDefault.getString(req.getParameter(GSConst.P_CMD), "").equals("downloadFile")) {
            forward = __doDownloadFile(map, thisForm, req, res, con);
        } else {
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
    public ActionForward __doInit(ActionMapping map, Wml080Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        Wml080ParamModel paramMdl = new Wml080ParamModel();
        paramMdl.setParam(form);
        Wml080Biz biz = new Wml080Biz();
        biz.setInitData(con, paramMdl, getRequestModel(req));
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 添付ファイルのダウンロードを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDownloadFile(ActionMapping map,
                                            Wml080Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con) throws SQLException, Exception {

        RequestModel reqMdl = getRequestModel(req);

        WmlBiz wmlBiz = new WmlBiz();
        WmlTempfileModel fileMdl
            = wmlBiz.getTempFileData(
                    con, form.getWml080mailNum(), form.getWml080downloadFileId(), reqMdl);

        if (fileMdl != null) {
            //ログ出力
            wmlBiz.outPutLog(map, reqMdl, con,
                    getInterMessage(req, "cmn.download"),
                    GSConstLog.LEVEL_INFO, fileMdl.getWtfFileName());

            JDBCUtil.closeConnectionAndNull(con);

            //ファイルをダウンロードする
            TempFileUtil.downloadInlineForWebmail(
                    req, res, fileMdl, getAppRootPath(), Encoding.UTF_8);
        }
        return null;
    }
}
