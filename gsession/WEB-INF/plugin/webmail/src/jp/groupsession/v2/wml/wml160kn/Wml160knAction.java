package jp.groupsession.v2.wml.wml160kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.wml.AbstractWebmailAction;
import jp.groupsession.v2.wml.AbstractWmlForm;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.wml160.WebmailCsvModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] WEBメール アカウントインポート確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml160knAction extends AbstractWebmailAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml160knAction.class);

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
        Wml160knForm thisForm = (Wml160knForm) form;

        //管理者権限チェック
        if (!_checkAuth(map, req, con)) {
            return map.findForward("gf_power");
        }

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

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
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng020knForm
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Wml160knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        try {
            con.setAutoCommit(true);
            Wml160knParamModel paramMdl = new Wml160knParamModel();
            paramMdl.setParam(form);
            Wml160knBiz biz = new Wml160knBiz();
            biz.setInitData(con, paramMdl, _getWebmailTempDir(req));
            paramMdl.setFormData(form);

        } catch (SQLException se) {
            throw se;
        } catch (IOException ioe) {
            throw ioe;
        } catch (IOToolsException iote) {
            throw iote;
        }
        // トランザクショントークン設定
        saveToken(req);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] アカウント登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    private ActionForward __doDecision(
                            ActionMapping map,
                            Wml160knForm form,
                            HttpServletRequest req,
                            HttpServletResponse res,
                            Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェックを行う
        ActionErrors errors = null;
        RequestModel reqMdl = getRequestModel(req);
        errors = form.validateCheck(con, reqMdl, _getWebmailTempDir(req));
        if (errors != null && !errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        boolean commit = false;
        String tempDir = _getWebmailTempDir(req);
        List<WebmailCsvModel> accountList = null;
        try {
            Wml160knBiz biz = new Wml160knBiz();
            accountList = biz.addAccount(con, reqMdl, tempDir,
                            getCountMtController(req), getSessionUserSid(req));

            con.commit();
            commit = true;

        } catch (Exception e) {
            log__.error("アカウント情報のインポートに失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
            IOTools.deleteDir(_getWebmailTempDir(req));
        }

        //ログ出力
        if (accountList != null) {
            StringBuilder buf = new StringBuilder();
            for (WebmailCsvModel mdl : accountList) {
                if (buf.length() > 0) {
                    buf.append(",");
                }
                buf.append("[name]" + mdl.getAccountName());
                buf.append(" [value]" + mdl.getMail());
            }
            //ログ出力
            GsMessage gsMsg = new GsMessage(reqMdl);
            WmlBiz wmlBiz = new WmlBiz();
            wmlBiz.outPutLog(map, reqMdl, con,
                    gsMsg.getMessage("cmn.change"), GSConstLog.LEVEL_INFO,
                    StringUtil.trimRengeString(buf.toString(), 3000));
        }
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
        Wml160knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        urlForward = map.findForward("userAccountList");
        if (form.getWmlAccountMode() == GSConstWebmail.WAC_TYPE_USER) {
            urlForward = map.findForward("accountManager");
        }

        ((AbstractWmlForm) form).setHiddenParam(cmn999Form);
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = null;
        if (form.getWmlCmdMode() == GSConstWebmail.CMDMODE_ADD) {
            msgState = "touroku.kanryo.object";
        } else if (form.getWmlCmdMode() == GSConstWebmail.CMDMODE_EDIT) {
            msgState = "hensyu.kanryo.object";
        }
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        cmn999Form.setMessage(msgRes.getMessage(msgState, gsMsg.getMessage("wml.wml160.05")));
        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);
    }

}
