package jp.groupsession.v2.adr.adr170kn;

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
import jp.groupsession.v2.adr.AdrCommonBiz;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr170.Adr170Action;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] アドレス帳 コンタクト履歴登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr170knAction extends Adr170Action {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr170knAction.class);
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

        log__.debug("START_Adr170kn");
        ActionForward forward = null;

        Adr170knForm thisForm = (Adr170knForm) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();
        //権限チェック
        forward = checkPow(map, thisForm, req, con);
        if (forward != null && !cmd.equals("fileDownload")) {
            return forward;
        }

        if (cmd.equals("kakutei")) {
            log__.debug("確定ボタンクリック");
            forward = __doKakutei(map, thisForm, req, res, con);

        } else if (cmd.equals("input_back")) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward("adr170");

        } else if (cmd.equals("fileDownload")) {
            log__.debug("添付ファイルダウンロード");
            forward = __doDownLoad(map, thisForm, req, res, con);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END_Adr170kn");
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
        Adr170knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        Adr170knBiz biz = new Adr170knBiz(con, getRequestModel(req));

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getAdr170pluginId(), getRequestModel(req));

        //添付ファイル情報セット
        Adr170knParamModel paramMdl = new Adr170knParamModel();
        paramMdl.setParam(form);
        biz.setTempFiles(paramMdl, tempDir);
        paramMdl.setFormData(form);

        //初期表示情報を取得する
        con.setAutoCommit(true);

        paramMdl = new Adr170knParamModel();
        paramMdl.setParam(form);
        ActionForward forward = null;
        biz.getInitData(paramMdl, con, getSessionUserModel(req));
        paramMdl.setFormData(form);
        forward =  map.getInputForward();
        con.setAutoCommit(false);

        return forward;
    }

    /**
     * <br>[機  能] 確定ボタンクリック時の処理を行う
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
    private ActionForward __doKakutei(
        ActionMapping map,
        Adr170knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con)
        throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェック
        con.setAutoCommit(true);
        ActionErrors errors = form.validateAdr170(getSessionUserModel(req), con, req);
        con.setAutoCommit(false);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), GSConstAddress.PLUGIN_ID_ADDRESS, getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);
        Adr170knParamModel paramMdl = new Adr170knParamModel();
        paramMdl.setParam(form);

        ActionForward forward = null;

        boolean commit = false;
        try {
            Adr170knBiz biz = new Adr170knBiz(con, getRequestModel(req));

            biz.entryData(paramMdl, getCountMtController(req),
                    getSessionUserModel(req).getUsrsid(),
                    getAppRootPath(), tempDir,
                    getPluginConfig(req));
            paramMdl.setFormData(form);


            forward = __setKanryoDsp(map, form, req);
            con.commit();
            commit = true;

            //テンポラリディレクトリの削除
            IOTools.deleteDir(tempDir);

        } catch (Exception e) {
            log__.error("コンタクト履歴情報の登録に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        String opCode = "";
        GsMessage gsMsg = new GsMessage();
        if (form.getAdr160ProcMode() == GSConstAddress.PROCMODE_ADD) {
            opCode = gsMsg.getMessage(req, "cmn.entry");

        } else if (form.getAdr160ProcMode() == GSConstAddress.PROCMODE_EDIT) {
            opCode = gsMsg.getMessage(req, "cmn.change");
        }

        //ログ出力処理
        AdrCommonBiz adrBiz = new AdrCommonBiz(con);
        adrBiz.outPutLog(
                map, req, res,
                opCode, GSConstLog.LEVEL_TRACE, "[title]" + form.getAdr170title());

        return forward;
    }

    /**
     * [機  能] 完了画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Adr170knForm form,
        HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("adr160");
        cmn999Form.setUrlOK(forwardOk.getPath());

        MessageResources msgRes = getResources(req);
        GsMessage gsMsg = new GsMessage();

        int procMode = form.getAdr160ProcMode();
        if (procMode == GSConstAddress.PROCMODE_ADD) {
            //登録完了
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object", gsMsg.getMessage(req, "address.6")));
        } else if (procMode == GSConstAddress.PROCMODE_EDIT) {
            //更新完了
            cmn999Form.setMessage(
                    msgRes.getMessage("hensyu.kanryo.object", gsMsg.getMessage(req, "address.6")));
        }

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        form.setHiddenParam160(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
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
        Adr170knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getAdr170pluginId(), getRequestModel(req));
        String fileId = form.getAdr170knBinSid();

        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(tempDir, fileId.concat(GSConstCommon.ENDSTR_OBJFILE));
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
        //添付ファイル保存用のパスを取得する(フルパス)
        String filePath = tempDir + fileId.concat(GSConstCommon.ENDSTR_SAVEFILE);
        filePath = IOTools.replaceFileSep(filePath);

        //ログ出力処理
        GsMessage gsMsg = new GsMessage();
        AdrCommonBiz adrBiz = new AdrCommonBiz(con);
        adrBiz.outPutLog(
                map, req, res,
                gsMsg.getMessage(req, "cmn.download"),
                GSConstLog.LEVEL_INFO,
                fMdl.getFileName(), fileId);

        //時間のかかる処理の前にコネクションを破棄
        JDBCUtil.closeConnectionAndNull(con);
        //ファイルをダウンロードする
        TempFileUtil.downloadAtachment(req, res, filePath, fMdl.getFileName(), Encoding.UTF_8);

        return null;
    }

}