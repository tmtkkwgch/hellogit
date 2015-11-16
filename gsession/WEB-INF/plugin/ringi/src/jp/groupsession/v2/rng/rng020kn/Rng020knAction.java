package jp.groupsession.v2.rng.rng020kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.rng.AbstractRingiAction;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 稟議作成確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng020knAction extends AbstractRingiAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng020knAction.class);

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

        Rng020knForm thisForm = (Rng020knForm) form;
        if (cmd.equals("rng020")) {
            log__.debug("*** 戻るボタンクリック");
            forward = map.findForward("rng020");

        } else if (cmd.equals("rng020knSinsei")) {
            log__.debug("申請ボタンクリック");
            forward = __doEntry(map, thisForm, req, res, con, 0);

        } else if (cmd.equals("fileDownload")) {
            log__.debug("添付ファイルダウンロード");
            forward = __doDownLoad(map, thisForm, req, res, con);

        } else {
            log__.debug("*** 初期表示を行います。");
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
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     */
    public ActionForward __doInit(ActionMapping map, Rng020knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException, IOException, IOToolsException {

        try {
            con.setAutoCommit(true);
            Rng020knParamModel paramMdl = new Rng020knParamModel();
            paramMdl.setParam(form);
            Rng020knBiz biz = new Rng020knBiz(con);
            biz.setInitData(getRequestModel(req), paramMdl, getAppRootPath(),
                        _getRingiDir(req), getSessionUserModel(req));
            paramMdl.setFormData(form);
            con.setAutoCommit(false);
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
     * <br>[機  能] 稟議情報登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param mode 登録モード 0:申請 1:草稿に保存
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doEntry(
        ActionMapping map,
        Rng020knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con,
        int mode) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェックを行う
        ActionErrors errors = null;
        errors = form.validateCheck(Rng020knForm.CHECKTYPE_REQUEST, req);
        if (errors != null && !errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        ActionForward forward = null;
        boolean commit = false;
        PluginConfig pconfig = getPluginConfigForMain(getPluginConfig(req), con);
        CommonBiz cmnBiz = new CommonBiz();
        boolean smailPluginUseFlg = cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig);
        try {

            RequestModel reqMdl = getRequestModel(req);
            Rng020knParamModel paramMdl = new Rng020knParamModel();
            paramMdl.setParam(form);
            Rng020knBiz biz = new Rng020knBiz(con,
                                            getSessionUserModel(req).getUsrsid());
            biz.entryRingiData(paramMdl, getCountMtController(req),
                            getAppRootPath(), _getRingiDir(req), mode,
                            getPluginConfig(req), smailPluginUseFlg,
                            reqMdl);
            paramMdl.setFormData(form);

            forward = __setCompPageParam(map, req, form);

            GsMessage gsMsg = new GsMessage(reqMdl);
            String msg = gsMsg.getMessage("cmn.entry");

            //ログ出力処理
            RngBiz rngBiz = new RngBiz(con);
            String opCode = msg;
            rngBiz.outPutLog(
                    map, opCode,
                    GSConstLog.LEVEL_TRACE,
                    "[title]" + form.getRng020Title(),
                    reqMdl);

            con.commit();
            commit = true;

            //テンポラリディレクトリの削除
            IOTools.deleteDir(_getRingiDir(req));
        } catch (Exception e) {
            log__.error("稟議情報の登録に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        return forward;
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
        Rng020knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), RngConst.PLUGIN_ID_RINGI, getRequestModel(req));
        String fileId = form.getRng020BinSid();

        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(tempDir, fileId.concat(GSConstCommon.ENDSTR_OBJFILE));
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
        //添付ファイル保存用のパスを取得する(フルパス)
        String filePath = tempDir + fileId.concat(GSConstCommon.ENDSTR_SAVEFILE);
        filePath = IOTools.replaceFileSep(filePath);

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String msg = gsMsg.getMessage("cmn.download");

        //ログ出力処理
        RngBiz rngBiz = new RngBiz(con);
        rngBiz.outPutLog(
                map,
                msg, GSConstLog.LEVEL_INFO, fMdl.getFileName(),
                reqMdl, fileId);

        //時間のかかる処理の前にコネクションを破棄
        JDBCUtil.closeConnectionAndNull(con);
        //ファイルをダウンロードする
        TempFileUtil.downloadAtachment(req, res, filePath, fMdl.getFileName(), Encoding.UTF_8);

        return null;
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @return ActionForward
     */
    private ActionForward __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Rng020knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        if (form.getRng130searchFlg() == 1) {
            urlForward = map.findForward("search");
        } else {
            urlForward = map.findForward("rng010");
        }
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = null;
        if (form.getRngCmdMode() == RngConst.RNG_CMDMODE_ADD) {
            msgState = "touroku.kanryo.object";
        } else if (form.getRngCmdMode() == RngConst.RNG_CMDMODE_EDIT) {
            msgState = "hensyu.kanryo.object";
        }
        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "rng.62");

        cmn999Form.setMessage(msgRes.getMessage(msgState, msg));

        cmn999Form.addHiddenParam("rngProcMode", form.getRngProcMode());
        cmn999Form.addHiddenParam("rng010orderKey", form.getRng010orderKey());
        cmn999Form.addHiddenParam("rng010sortKey", form.getRng010sortKey());
        cmn999Form.addHiddenParam("rng010pageTop", form.getRng010pageTop());
        cmn999Form.addHiddenParam("rng010pageBottom", form.getRng010pageBottom());

        cmn999Form.addHiddenParam("rngKeyword", form.getRngKeyword());
        cmn999Form.addHiddenParam("rng130Type", form.getRng130Type());
        cmn999Form.addHiddenParam("sltGroupSid", form.getSltGroupSid());
        cmn999Form.addHiddenParam("sltUserSid", form.getSltUserSid());
        cmn999Form.addHiddenParam("rng130keyKbn", form.getRng130keyKbn());
        cmn999Form.addHiddenParam("rng130searchSubject1", form.getRng130searchSubject1());
        cmn999Form.addHiddenParam("rng130searchSubject2", form.getRng130searchSubject2());
        cmn999Form.addHiddenParam("sltSortKey1", form.getSltSortKey1());
        cmn999Form.addHiddenParam("rng130orderKey1", form.getRng130orderKey1());
        cmn999Form.addHiddenParam("sltSortKey2", form.getSltSortKey2());
        cmn999Form.addHiddenParam("rng130orderKey2", form.getRng130orderKey1());
        cmn999Form.addHiddenParam("sltApplYearFr", form.getSltApplYearFr());
        cmn999Form.addHiddenParam("sltApplMonthFr", form.getSltApplMonthFr());
        cmn999Form.addHiddenParam("sltApplDayFr", form.getSltApplDayFr());
        cmn999Form.addHiddenParam("sltApplYearTo", form.getSltApplYearTo());
        cmn999Form.addHiddenParam("sltApplMonthTo", form.getSltApplMonthTo());
        cmn999Form.addHiddenParam("sltApplDayTo", form.getSltApplDayTo());
        cmn999Form.addHiddenParam("sltLastManageYearFr", form.getSltLastManageYearFr());
        cmn999Form.addHiddenParam("sltLastManageMonthFr", form.getSltLastManageMonthFr());
        cmn999Form.addHiddenParam("sltLastManageDayFr", form.getSltLastManageDayFr());
        cmn999Form.addHiddenParam("sltLastManageYearTo", form.getSltLastManageYearTo());
        cmn999Form.addHiddenParam("sltLastManageMonthTo", form.getSltLastManageMonthTo());
        cmn999Form.addHiddenParam("sltLastManageDayTo", form.getSltLastManageDayTo());
        cmn999Form.addHiddenParam("rng130pageTop", form.getRng130pageTop());
        cmn999Form.addHiddenParam("rng130pageBottom", form.getRng130pageBottom());

        cmn999Form.addHiddenParam("svRngKeyword", form.getSvRngKeyword());
        cmn999Form.addHiddenParam("svRng130Type", form.getSvRng130Type());
        cmn999Form.addHiddenParam("svGroupSid", form.getSvGroupSid());
        cmn999Form.addHiddenParam("svUserSid", form.getSvUserSid());
        cmn999Form.addHiddenParam("svRng130keyKbn", form.getSvRng130keyKbn());
        cmn999Form.addHiddenParam("svRng130searchSubject1", form.getSvRng130searchSubject1());
        cmn999Form.addHiddenParam("svRng130searchSubject2", form.getSvRng130searchSubject2());
        cmn999Form.addHiddenParam("svSortKey1", form.getSvSortKey1());
        cmn999Form.addHiddenParam("svRng130orderKey1", form.getSvRng130orderKey1());
        cmn999Form.addHiddenParam("svSortKey2", form.getSvSortKey2());
        cmn999Form.addHiddenParam("svRng130orderKey2", form.getSvRng130orderKey1());
        cmn999Form.addHiddenParam("svApplYearFr", form.getSvApplYearFr());
        cmn999Form.addHiddenParam("svApplMonthFr", form.getSvApplMonthFr());
        cmn999Form.addHiddenParam("svApplDayFr", form.getSvApplDayFr());
        cmn999Form.addHiddenParam("svApplYearTo", form.getSvApplYearTo());
        cmn999Form.addHiddenParam("svApplMonthTo", form.getSvApplMonthTo());
        cmn999Form.addHiddenParam("svApplDayTo", form.getSvApplDayTo());
        cmn999Form.addHiddenParam("svLastManageYearFr", form.getSvLastManageYearFr());
        cmn999Form.addHiddenParam("svLastManageMonthFr", form.getSvLastManageMonthFr());
        cmn999Form.addHiddenParam("svLastManageDayFr", form.getSvLastManageDayFr());
        cmn999Form.addHiddenParam("svLastManageYearTo", form.getSvLastManageYearTo());
        cmn999Form.addHiddenParam("svLastManageMonthTo", form.getSvLastManageMonthTo());
        cmn999Form.addHiddenParam("svLastManageDayTo", form.getSvLastManageDayTo());
        cmn999Form.addHiddenParam("rng130searchFlg", form.getRng130searchFlg());

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

}
