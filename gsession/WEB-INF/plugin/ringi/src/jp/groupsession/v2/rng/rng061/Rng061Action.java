package jp.groupsession.v2.rng.rng061;

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
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.rng.AbstractRingiAction;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.rng020.Rng020Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 稟議 内容テンプレート選択確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng061Action extends AbstractRingiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng061Action.class);

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

        if (cmd.equals("fileDownload")) {
            log__.debug("添付ファイルダウンロード");
            return true;

        }
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

        Rng061Form thisForm = (Rng061Form) form;
        if (cmd.equals("rng010")) {
            log__.debug("*** 稟議作成。");
            forward = map.findForward("rng020");

        } else if (cmd.equals("rng060")) {
            log__.debug("*** 内容テンプレート一覧。");
            forward = __doBack(map, thisForm, req, res, con);

        } else if (cmd.equals("optbtn")) {
            log__.debug("*** 選択ボタンクリック。");
            forward = __doOpt(map, thisForm, req, res, con);

        } else if (cmd.equals("fileDownload")) {
            log__.debug("*** 添付ファイルダウンロード");
            forward = __doDownLoad(map, thisForm, req, res, con);

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
    public ActionForward __doInit(ActionMapping map, Rng061Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        Rng061Biz biz = new Rng061Biz(con, getRequestModel(req));
        biz.doDeleteFile(_getRingiTemplateDir(req));

        Rng061ParamModel paramMdl = new Rng061ParamModel();
        paramMdl.setParam(form);
        biz.initDsp(paramMdl, getAppRootPath(), _getRingiTemplateDir(req), con,
                    getSessionUserSid(req));
        paramMdl.setFormData(form);
        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 選択ボタンクリック時の処理
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
    public ActionForward __doOpt(ActionMapping map, Rng061Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //選択ボタンクリック時の処理
        RequestModel reqMdl = getRequestModel(req);
        Rng061ParamModel paramMdl = new Rng061ParamModel();
        paramMdl.setParam(form);
        Rng061Biz biz = new Rng061Biz(con, reqMdl);
        biz.initOpt(
                paramMdl,
                getAppRootPath(),
                _getRingiDir(req),
                _getRingiTemplateDir(req));
        paramMdl.setFormData(form);

        //リクエストパラメータ
        log__.debug("getRng061content" + form.getRng061content());
        form.setRng020Content(form.getRng061content());

        UserBiz userBiz = new UserBiz();
        form.setRng020apprUser(userBiz.getNormalUser(con, form.getRng061apprUser()));
        form.setRng020confirmUser(userBiz.getNormalUser(con, form.getRng061confirmUser()));

        req.removeAttribute("rng061Form");
        req.removeAttribute("rng020Content");
        req.setAttribute("rng020Form", (Rng020Form) form);

        return map.findForward("rng020");
    }

    /**
     * <br>[機  能] 添付ファイルダウンロードの処理
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
    private ActionForward __doDownLoad(
        ActionMapping map,
        Rng061Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        //テンプレートSID
        int tplSid = form.getRngSelectTplSid();
        //バイナリSID
        Long fileId = NullDefault.getLong(form.getRng061TmpFileId(), -1);

        RngBiz rngBiz = new RngBiz(con);
        //テンプレートの添付ファイルがダウンロード可能かチェックする
        if (rngBiz.isCheckDLTemplateTemp(con, tplSid, fileId, getSessionUserSid(req))) {

            CommonBiz cmnBiz = new CommonBiz();
            CmnBinfModel cbMdl = cmnBiz.getBinInfo(con, fileId,
                    GroupSession.getResourceManager().getDomain(req));

            if (cbMdl != null) {
                GsMessage gsMsg = new GsMessage(getRequestModel(req));
                String textDownload = gsMsg.getMessage("cmn.download");

                //ログ出力処理
                rngBiz.outPutLog(map, textDownload, GSConstLog.LEVEL_INFO,
                        cbMdl.getBinFileName(), getRequestModel(req), String.valueOf(fileId));

                //時間のかかる処理の前にコネクションを破棄
                JDBCUtil.closeConnectionAndNull(con);

                TempFileUtil.downloadAtachment(req, res, cbMdl, getAppRootPath(), Encoding.UTF_8);
            }
        }

        return null;
    }

    /**
     * <br>[機  能] 戻るボタン押下時の処理
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
    private ActionForward __doBack(
        ActionMapping map,
        Rng061Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        Rng061Biz biz = new Rng061Biz(con, getRequestModel(req));
        biz.doDeleteFile(_getRingiTemplateDir(req));

        return map.findForward("rng060");
    }

}
