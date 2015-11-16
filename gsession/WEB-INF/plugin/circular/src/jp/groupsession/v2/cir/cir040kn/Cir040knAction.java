package jp.groupsession.v2.cir.cir040kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cir.AbstractCircularAction;
import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.biz.CirCommonBiz;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 回覧板 新規作成確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir040knAction extends AbstractCircularAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cir040knAction.class);

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

        log__.debug("START_Cir040");
        ActionForward forward = null;

        Cir040knForm thisForm = (Cir040knForm) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();
        log__.debug(" cmd = " + cmd);
        if (cmd.equals("send")) {
            log__.debug("回覧板 送信");
            forward = __doOk(map, thisForm, req, res, con);

        } else if (cmd.equals("editCir")) {
            log__.debug("回覧板 編集");
            forward = __doEdit(map, thisForm, req, res, con);

        } else if (cmd.equals("cir040knback")) {
            log__.debug("戻る");
            forward = map.findForward("back");

        } else if (cmd.equals("fileDownload")) {
            log__.debug("添付ファイルダウンロード");
            forward = __doDownLoad(map, thisForm, req, res, con);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END_Cir040");
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
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @return ActionForward
     */
    private ActionForward __doInit(
        ActionMapping map,
        Cir040knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        //リクエストパラメータに回覧先がある場合、フォームにセット
        Object obj = req.getAttribute("cir040userSid");
        if (obj != null) {
            form.setCir040userSid((String[]) obj);
        }
        //テンポラリディレクトリパスを取得
        String tempDir = __getPluginIdTempDir(req);
        log__.debug("テンポラリディレクトリ = " + tempDir);


        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 再表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @return ActionForward
     */
    private ActionForward __doDsp(
        ActionMapping map,
        Cir040knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        //テンポラリディレクトリパスを取得
        String tempDir = __getPluginIdTempDir(req);
        log__.debug("テンポラリディレクトリ = " + tempDir);

        Cir040knParamModel paramMdl = new Cir040knParamModel();
        paramMdl.setParam(form);

        //初期表示情報を画面にセットする
        con.setAutoCommit(true);

        Cir040knBiz biz = new Cir040knBiz(con);
        biz.setInitData(paramMdl, con, tempDir);
        con.setAutoCommit(false);

        //添付ファイル情報セット
        biz.setTempFiles(paramMdl, tempDir);
        paramMdl.setFormData(form);

        //トランザクショントークン設定
        saveToken(req);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 回覧板 送信ボタンクリック時の処理
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
    private ActionForward __doOk(
        ActionMapping map,
        Cir040knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        RequestModel reqMdl = getRequestModel(req);

        //入力チェック
        ActionErrors errors = form.validateCheck(reqMdl);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doDsp(map, form, req, res, con);
        }
        log__.debug("入力エラーなし、登録を行う");

        //採番コントローラ
        MlCountMtController cntCon = getCountMtController(req);

        //ログインユーザSIDを取得
        BaseUserModel buMdl = getSessionUserModel(req);
        int userSid = buMdl.getUsrsid();

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = __getPluginIdTempDir(req);
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //アプリケーションのルートパス
        String appRootPath = getAppRootPath();

        Cir040knParamModel paramMdl = new Cir040knParamModel();
        paramMdl.setParam(form);

        //登録処理を行う
        Cir040knBiz biz = new Cir040knBiz(con);
        int cirSid = biz.doInsert(paramMdl, cntCon, userSid, tempDir, appRootPath);

        //受信者にショートメールで通知
        PluginConfig pconfig = getPluginConfigForMain(getPluginConfig(req), con);
        cmnBiz = new CommonBiz();
        if (cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig)) {
            biz.doNotifies(buMdl, paramMdl, cirSid, getAppRootPath(), cntCon,
                        getPluginConfig(req), reqMdl);
        }
        paramMdl.setFormData(form);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textSosin = gsMsg.getMessage("cmn.sent");

        //ログ出力処理
        CirCommonBiz cirBiz = new CirCommonBiz(con);
        cirBiz.outPutLog(map, reqMdl, textSosin,
                GSConstLog.LEVEL_TRACE, "[title]" + form.getCir040title());

        //完了画面を表示
        return __setKanryoDsp(map, form, req);
    }

    /**
     * <br>[機  能] 回覧板 編集ボタンクリック時の処理
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
    private ActionForward __doEdit(
        ActionMapping map,
        Cir040knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        RequestModel reqMdl = getRequestModel(req);

        //入力チェック
        ActionErrors errors = form.validateCheck(reqMdl);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doDsp(map, form, req, res, con);
        }
        log__.debug("入力エラーなし、登録を行う");

        //採番コントローラ
        MlCountMtController cntCon = getCountMtController(req);

        //ログインユーザSIDを取得
        BaseUserModel buMdl = getSessionUserModel(req);
        int userSid = buMdl.getUsrsid();

        //テンポラリディレクトリパスを取得
        String tempDir = __getPluginIdTempDir(req);
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //アプリケーションのルートパス
        String appRootPath = getAppRootPath();

        Cir040knParamModel paramMdl = new Cir040knParamModel();
        paramMdl.setParam(form);

        //編集処理を行う

        if (!StringUtil.isNullZeroStringSpace(paramMdl.getCirEditInfSid())) {
            Cir040knBiz biz = new Cir040knBiz(con);
            biz.doEdit(paramMdl, userSid, tempDir, cntCon, appRootPath);
        }

        paramMdl.setFormData(form);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textSosin = gsMsg.getMessage("cmn.sent");

        //ログ出力処理
        CirCommonBiz cirBiz = new CirCommonBiz(con);
        cirBiz.outPutLog(map, reqMdl, textSosin,
                GSConstLog.LEVEL_TRACE, "[title]" + form.getCir040title());

        //完了画面を表示
        return __setEditKanryoDsp(map, form, req);
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
        Cir040knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        //テンポラリディレクトリパスを取得
        String tempDir = __getPluginIdTempDir(req);

        String fileId = form.getCir040knBinSid();

        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(tempDir, fileId.concat(GSConstCommon.ENDSTR_OBJFILE));
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
        //添付ファイル保存用のパスを取得する(フルパス)
        String filePath = tempDir + fileId.concat(GSConstCommon.ENDSTR_SAVEFILE);
        filePath = IOTools.replaceFileSep(filePath);
        //ファイルをダウンロードする
        TempFileUtil.downloadAtachment(req, res, filePath, fMdl.getFileName(), Encoding.UTF_8);

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String textDownload = gsMsg.getMessage("cmn.download");

        //ログ出力処理
        CirCommonBiz cirBiz = new CirCommonBiz(con);
        cirBiz.outPutLog(map, reqMdl, textDownload,
                GSConstLog.LEVEL_INFO, fMdl.getFileName(),
                fileId, GSConstCircular.CIR_LOG_FLG_DOWNLOAD);

        return null;
    }

    /**
     * [機  能] <br>完了画面のパラメータセット
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Cir040knForm form,
        HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);

        if (form.getCir040webmail() == 1) {
            //WEBメールからの呼び出し
            cmn999Form.setWtarget(Cmn999Form.WTARGET_SELF);
            cmn999Form.setType_popup(Cmn999Form.POPUP_TRUE);
            cmn999Form.setCloseScript("javascript:window.parent.webmailEntrySubWindowClose();");
        } else {
            cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

            //OKボタンクリック時遷移先
            ActionForward forwardOk = map.findForward("cir010back");
            cmn999Form.setUrlOK(forwardOk.getPath());
        }


        GsMessage gsMsg = new GsMessage();
        String textCir = gsMsg.getMessage(req, "cir.5");

        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
                msgRes.getMessage("touroku.kanryo.object", textCir));

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        cmn999Form.addHiddenParam("cir010pageNum1", form.getCir010pageNum1());
        cmn999Form.addHiddenParam("cir010pageNum2", form.getCir010pageNum2());
        cmn999Form.addHiddenParam("cir010cmdMode", form.getCir010cmdMode());
        cmn999Form.addHiddenParam("cir010orderKey", form.getCir010orderKey());
        cmn999Form.addHiddenParam("cir010sortKey", form.getCir010sortKey());
        cmn999Form.addHiddenParam("cir010delInfSid", form.getCir010delInfSid());

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * [機  能] <br>編集画面のパラメータセット
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setEditKanryoDsp(
        ActionMapping map,
        Cir040knForm form,
        HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);

        if (form.getCir040webmail() == 1) {
            //WEBメールからの呼び出し
            cmn999Form.setWtarget(Cmn999Form.WTARGET_SELF);
            cmn999Form.setType_popup(Cmn999Form.POPUP_TRUE);
            cmn999Form.setCloseScript("javascript:window.parent.webmailEntrySubWindowClose();");
        } else {
            cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

            //OKボタンクリック時遷移先
            ActionForward forwardOk = map.findForward("cir010back");
            cmn999Form.setUrlOK(forwardOk.getPath());
        }


        GsMessage gsMsg = new GsMessage();
        String textCir = gsMsg.getMessage(req, "cir.5");

        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
                msgRes.getMessage("hensyu.kanryo.object", textCir));

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        cmn999Form.addHiddenParam("cir010pageNum1", form.getCir010pageNum1());
        cmn999Form.addHiddenParam("cir010pageNum2", form.getCir010pageNum2());
        cmn999Form.addHiddenParam("cir010cmdMode", form.getCir010cmdMode());
        cmn999Form.addHiddenParam("cir010orderKey", form.getCir010orderKey());
        cmn999Form.addHiddenParam("cir010sortKey", form.getCir010sortKey());
        cmn999Form.addHiddenParam("cir010delInfSid", form.getCir010delInfSid());

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 確認時添付用のテンポラリディレクトリを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return テンポラリディレクトリパス
     */
    private String __getPluginIdTempDir(HttpServletRequest req) {
        String pluginIdTemp = __getPluginIdTemp(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), pluginIdTemp, getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);
        return tempDir;
    }


    /**
     * <br>[機  能] 確認時添付用のテンポラリディレクトリ用のプラグインIDを取得する
     * <br>[解  説] プラグインID = プラグインID/セッションID/cirKnTemp
     * <br>[備  考]
     * @param req リクエスト
     * @return プラグインID
     */
    private String __getPluginIdTemp(HttpServletRequest req) {
        String pluginIdTemp = GSConstCircular.PLUGIN_ID_CIRCULAR
                + "/" + req.getSession().getId() + "/" + GSConstCircular.TEMP_DIR_NEW;
        return pluginIdTemp;
    }
}
