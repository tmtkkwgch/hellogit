package jp.groupsession.v2.rng.rng020;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.struts.StrutsUtil;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 稟議作成画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng020Action extends AbstractRingiAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng020Action.class);

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

        if (cmd.equals("templateFileDownload")) {
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

        Rng020Form thisForm = (Rng020Form) form;
        if (cmd.equals("rng010")) {
            log__.debug("*** 稟議一覧。");

            //テンポラリディレクトリの削除
            IOTools.deleteDir(_getRingiDir(req));

            if (thisForm.isRng020copyApply()) {
                forward = map.findForward("rng030");
            } else if (thisForm.getRng130searchFlg() == 1) {
                forward = map.findForward("search");
            } else {
                forward = map.findForward("rng010");
            }

        } else if (cmd.equals("rng060")) {
            log__.debug("*** 内容テンプレート一覧。");
            forward = map.findForward("rng060");

        } else if (cmd.equals("upAppr")) {
            log__.debug("上矢印(承認経路)ボタンクリック");
            forward = __doUpAppr(map, thisForm, req, res, con);

        } else if (cmd.equals("downAppr")) {
            log__.debug("下矢印(承認経路)ボタンクリック");
            forward = __doDownAppr(map, thisForm, req, res, con);

        } else if (cmd.equals("addAppr")) {
            log__.debug("追加(承認経路)ボタンクリック");
            thisForm.setRng020ScrollFlg("1");
            forward = __doAddAppr(map, thisForm, req, res, con);

        } else if (cmd.equals("delAppr")) {
            log__.debug("削除(承認経路)ボタンクリック");
            thisForm.setRng020ScrollFlg("1");
            forward = __doDelAppr(map, thisForm, req, res, con);

        } else if (cmd.equals("addConfirm")) {
            log__.debug("追加(最終確認)ボタンクリック");
            thisForm.setRng020ScrollFlg("1");
            forward = __doAddConfirm(map, thisForm, req, res, con);

        } else if (cmd.equals("delConfirm")) {
            log__.debug("削除(最終確認)ボタンクリック");
            thisForm.setRng020ScrollFlg("1");
            forward = __doDelConfirm(map, thisForm, req, res, con);

        } else if (cmd.equals("delTemp")) {
            log__.debug("削除(添付ファイル)ボタンクリック");
            forward = __doDelTemp(map, thisForm, req, res, con);

        } else if (cmd.equals("draft")) {
            log__.debug("草稿に保存ボタンクリック");
            forward = __doEntry(map, thisForm, req, res, con, 1);

        } else if (cmd.equals("approval")) {
            log__.debug("申請ボタンクリック");
            forward = __doEntry(map, thisForm, req, res, con, 0);

        } else if (cmd.equals("setChannel")) {
            log__.debug("経路に追加ボタンクリック");
            forward = __doSetChannel(map, thisForm, req, res, con);

        } else if (cmd.equals("rng020")) {
            log__.debug("確認画面戻るボタンクリック");
            forward = __doDsp(map, thisForm, req, res, con);

        } else if (cmd.equals("060back")) {
            log__.debug("内容テンプレート一覧画面戻るボタンクリック");
            forward = __doDsp(map, thisForm, req, res, con);

        } else if (cmd.equals("changeGroup")) {
            log__.debug("グループコンボ変更");
            forward = __doDsp(map, thisForm, req, res, con);

        } else if (cmd.equals("optbtn")) {
            log__.debug("テンプレート使用");
            forward = __doDsp(map, thisForm, req, res, con);

        } else if (cmd.equals("templateFileDownload")) {
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
     * @param form Rng020Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Rng020Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //テンポラリディレクトリの削除
        IOTools.deleteDir(_getRingiDir(req));

        Rng020ParamModel paramMdl = new Rng020ParamModel();
        paramMdl.setParam(form);
        Rng020Biz biz = new Rng020Biz(con, getRequestModel(req));
        biz.setInitData(req, paramMdl, getAppRootPath(),
                        _getRingiDir(req), getSessionUserModel(req));
        paramMdl.setFormData(form);

        // トランザクショントークン設定
        saveToken(req);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 再表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng020Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doDsp(ActionMapping map, Rng020Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        Rng020ParamModel paramMdl = new Rng020ParamModel();
        paramMdl.setParam(form);
        Rng020Biz biz = new Rng020Biz(con, getRequestModel(req));
        biz.setInitData(req, paramMdl, getAppRootPath(),
                        _getRingiDir(req), getSessionUserModel(req));
        paramMdl.setFormData(form);

        // トランザクショントークン設定
        saveToken(req);
        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 上矢印(承認経路)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doUpAppr(
        ActionMapping map,
        Rng020Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        RngBiz rngBiz = new RngBiz(con);
        form.setRng020apprUser(rngBiz.getUpMember(form.getRng020selectApprUser(),
                                                form.getRng020apprUser()));
        return  __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 下矢印(承認経路)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDownAppr(
        ActionMapping map,
        Rng020Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        RngBiz rngBiz = new RngBiz(con);
        form.setRng020apprUser(rngBiz.getDownMember(form.getRng020selectApprUser(),
                                                    form.getRng020apprUser()));

        return  __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(承認経路)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doAddAppr(
        ActionMapping map,
        Rng020Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng020apprUser(
                cmnBiz.getAddMember(form.getRng020users(), form.getRng020apprUser()));
        return  __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(承認経路)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDelAppr(
        ActionMapping map,
        Rng020Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng020apprUser(
                cmnBiz.getDeleteMember(form.getRng020selectApprUser(), form.getRng020apprUser()));

        return  __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(最終確認)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doAddConfirm(
        ActionMapping map,
        Rng020Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng020confirmUser(
                cmnBiz.getAddMember(form.getRng020users(), form.getRng020confirmUser()));

        return  __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(最終確認)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDelConfirm(
        ActionMapping map,
        Rng020Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setRng020confirmUser(
                cmnBiz.getDeleteMember(
                        form.getRng020selectConfirmUser(), form.getRng020confirmUser()));

        return  __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 添付ファイル削除ボタンクリック時の処理
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
    private ActionForward __doDelTemp(
        ActionMapping map,
        Rng020Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = _getRingiDir(req);
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //選択された添付ファイルを削除する
        cmnBiz.deleteFile(form.getRng020files(), tempDir);

        return __doDsp(map, form, req, res, con);
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
        Rng020Form form,
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
        if (mode == 0) {
            errors = form.validateCheck(Rng020Form.CHECKTYPE_REQUEST, req);
        } else if (mode == 1) {
            errors = form.validateCheck(Rng020Form.CHECKTYPE_DRAFT, req);
        }
        if (errors != null && !errors.isEmpty()) {
            addErrors(req, errors);
            return __doDsp(map, form, req, res, con);
        }

        //新規作成の場合、確認画面へ遷移する。
        if (mode == 0) {
            return map.findForward("rng020kn");
        }
        ActionForward forward = null;
        boolean commit = false;
        PluginConfig pconfig = getPluginConfigForMain(getPluginConfig(req), con);
        CommonBiz cmnBiz = new CommonBiz();
        boolean smailPluginUseFlg = cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig);

        try {
            RequestModel reqMdl = getRequestModel(req);
            Rng020ParamModel paramMdl = new Rng020ParamModel();
            paramMdl.setParam(form);
            Rng020Biz biz = new Rng020Biz(con, reqMdl, getSessionUserSid(req));
            biz.entryRingiData(paramMdl, getCountMtController(req),
                            getAppRootPath(), _getRingiDir(req), mode,
                            getPluginConfig(req),
                            smailPluginUseFlg,
                            getRequestModel(req));
            paramMdl.setFormData(form);

            forward = __setCompPageParam(map, req, form);

            GsMessage gsMsg = new GsMessage(reqMdl);
            String msg = gsMsg.getMessage("cmn.entry");
            String msg2 = gsMsg.getMessage("cmn.save.draft");

            //ログ出力処理
            RngBiz rngBiz = new RngBiz(con);
            String opCode = msg;

            rngBiz.outPutLog(
                    map, opCode,
                    GSConstLog.LEVEL_TRACE,
                    msg2,
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
     * <br>[機  能] 経路に追加ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doSetChannel(
        ActionMapping map,
        Rng020Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        GsMessage gsMsg = new GsMessage();
        String msg2 = gsMsg.getMessage(req, "rng.25");

        //経路が選択されていない場合はエラーメッセージを表示
        if (form.getRng020rctSid() < 0) {
            ActionErrors errors = new ActionErrors();
            ActionMessage msg = new ActionMessage("error.select.required.text", msg2);
            StrutsUtil.addMessage(errors, msg, "rng020rctSid");
            addErrors(req, errors);
        } else {
            Rng020ParamModel paramMdl = new Rng020ParamModel();
            paramMdl.setParam(form);
            Rng020Biz biz = new Rng020Biz(con, getRequestModel(req));
            biz.setChannelForTemplate(paramMdl, getSessionUserSid(req));
            paramMdl.setFormData(form);
        }

        return  __doDsp(map, form, req, res, con);
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
        Rng020Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        RequestModel reqMdl = getRequestModel(req);
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), RngConst.PLUGIN_ID_RINGI, reqMdl);
        Rng020Biz biz = new Rng020Biz(reqMdl);
        tempDir = biz.getTemplateFileDir(tempDir);
        String fileId = form.getRng020TemplateFileId();

        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(tempDir, fileId.concat(GSConstCommon.ENDSTR_OBJFILE));
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
        //添付ファイル保存用のパスを取得する(フルパス)
        String filePath = tempDir + fileId.concat(GSConstCommon.ENDSTR_SAVEFILE);
        filePath = IOTools.replaceFileSep(filePath);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String msg = gsMsg.getMessage("cmn.download");

        //ログ出力処理
        RngBiz rngBiz = new RngBiz(con);
        rngBiz.outPutLog(
                map,
                msg, GSConstLog.LEVEL_INFO, fMdl.getFileName(),
                reqMdl);

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
        Rng020Form form) {

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
