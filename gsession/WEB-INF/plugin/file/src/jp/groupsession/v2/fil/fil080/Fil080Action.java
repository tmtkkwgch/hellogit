package jp.groupsession.v2.fil.fil080;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.WmlDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.AbstractFileAction;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.model.FileFileBinModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ファイル登録画面のアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil080Action extends AbstractFileAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil080Action.class);

    /**
     *<br>[機  能] アクションを実行する
     *<br>[解  説]
     *<br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
        HttpServletRequest req, HttpServletResponse res, Connection con)
        throws Exception {

        log__.debug("fil080Action START");

        ActionForward forward = null;
        Fil080Form thisForm = (Fil080Form) form;

        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();
        if (cmd.equals("calledWebmail")) {
            log__.debug("WEBメール連携");
            forward = __doCalledWebmail(map, thisForm, req, res, con);
            if (forward != null) {
                return forward;
            }
            cmd = "init";
        }

        con.setAutoCommit(true);
        if (thisForm.getFil080webmail() != 1) {
            //Webメール共有時の親ディレクトリ未選択時はチェック対象外
            //キャビネットアクセス権限チェック
            if (!__checkViewCabinet(thisForm, req, con)) {
                return getCanNotViewErrorPage(map, req);
            }
            int ecode = __checkEditDir(thisForm, req, con);
            if (ecode == FilCommonBiz.ERR_NONE_CAB_EDIT_POWER) {
                GsMessage gsMsg = new GsMessage(req);
                String actName = "";
                if (NullDefault.getInt(thisForm.getFil070DirSid(), -1) != -1) {
                    actName = gsMsg.getMessage("cmn.edit");
                } else {
                    actName = gsMsg.getMessage("fil.16");
                }
                return getPowNoneErrorPage(map, req,
                        gsMsg.getMessage("cmn.edit"),
                        actName);
            }
            if (ecode == FilCommonBiz.ERR_NOT_EXIST) {
                GsMessage gsMsg = new GsMessage(req);
                String targetName = "";
                if (NullDefault.getInt(thisForm.getFil070DirSid(), -1) != -1) {
                    targetName = gsMsg.getMessage("cmn.file");
                } else {
                    targetName = gsMsg.getMessage("cmn.folder");
                }
                return getCanNotViewNonePowerErrorPage(map, req, targetName);
            }
            if (ecode == FilCommonBiz.ERR_NOT_DIRKBN) {
                return getSubmitErrorPage(map, req);
            }
        }
        //ファイルロックチェック
        if (!__checkFileLock(thisForm, req, con)) {
            return __getFileLockErrorPage(map, req, thisForm);
        }

        con.setAutoCommit(false);

        if (cmd.equals("fil080back")) {
            //戻るボタンクリック
            forward = __doBack(map, thisForm, req, con);

        } else if (cmd.equals("fil080add")) {
            //編集ボタンクリック
            forward = __doEdit(map, thisForm, req, res, con);

        } else if (cmd.equals("fil080delete")) {
            //削除ボタンクリック
            forward = __doDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteOk")) {
            //削除確認OKボタンクリック
            forward = __doDeleteOk(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteTemp")) {
            //添付ファイル削除ボタンクリック
            forward = __doDeleteTemp(map, thisForm, req, res, con);

        } else if (cmd.equals("fil080changeCabinet")) {
            //キャビネットコンボ変更
            forward = __doChangeCabinet(map, thisForm, req, res, con);

        } else if (cmd.equals("changeDir")) {
            //登録先ディレクトリ変更
            String changeDirSid
                = NullDefault.getString(thisForm.getMoveToDir(),
                                                thisForm.getFil070ParentDirSid());

            thisForm.setFil010SelectDirSid(changeDirSid);
            thisForm.setFil070ParentDirSid(changeDirSid);
            thisForm.setSelectDir(changeDirSid);

            thisForm.setFil040SelectDelAll("0");
            forward = __doInit(map, thisForm, req, res, con);
        } else if (cmd.equals("fil080fulladd")) {
            //アクセス　フルアクセス追加ボタンクリック
            forward = __doFullAddUser(map, thisForm, req, res, con);

        } else if (cmd.equals("fil080fulldel")) {
            //アクセス　フルアクセス削除ボタンクリック
            forward = __doFullDelUser(map, thisForm, req, res, con);

        } else if (cmd.equals("fil080readadd")) {
            //アクセス　閲覧アクセス追加ボタンクリック
            forward = __doReadAddUser(map, thisForm, req, res, con);

        } else if (cmd.equals("fil080readdel")) {
            //アクセス　閲覧アクセス削除ボタンクリック
            forward = __doReadDelUser(map, thisForm, req, res, con);






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
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception  実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Fil080Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws Exception {

        con.setAutoCommit(true);
        CommonBiz cmnBiz = new CommonBiz();
        RequestModel reqMdl = getRequestModel(req);
        Fil080Biz biz = new Fil080Biz(con, reqMdl);

        //テンポラリディレクトリパスを取得
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), reqMdl);

        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);

        //初期表示を設定
        Fil080ParamModel paramMdl = new Fil080ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, tempDir, getAppRootPath(), buMdl);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);
        return map.getInputForward();
    }
    /**
     * <br>[機  能] ディレクトリへの編集権限があるか判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @return エラーコード
     * @throws SQLException 実行例外
     */
    private int __checkEditDir(
            Fil080Form form,
            HttpServletRequest req,
            Connection con) throws SQLException {
        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        boolean targetParent = false;
        int dirSid = NullDefault.getInt(form.getFil070DirSid(), -1);
        if (dirSid < 1) {
            dirSid = NullDefault.getInt(form.getFil070ParentDirSid(), -1);
            targetParent = true;
        }
        if (targetParent) {
            return cmnBiz.checkPowEditDir(dirSid, GSConstFile.DIRECTORY_FOLDER);
        } else {
            return cmnBiz.checkPowEditDir(dirSid, GSConstFile.DIRECTORY_FILE);
        }
    }

    /**
     * <br>[機  能] 遷移元画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイル操作時例外
     */
    private ActionForward __doBack(
            ActionMapping map, Fil080Form form, HttpServletRequest req, Connection con)
    throws SQLException, IOToolsException {

        ActionForward forward = null;

        CommonBiz cmnBiz = new CommonBiz();
        RequestModel reqMdl = getRequestModel(req);
        Fil080Biz biz = new Fil080Biz(con, reqMdl);

        //テンポラリディレクトリパスを取得
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), reqMdl);

        //テンポラリディレクトリのファイル削除を行う
        IOTools.deleteDir(tempDir);

        if (form.getFil080Mode() == GSConstFile.MODE_ADD) {
            forward = map.findForward("fil040");
        } else {
            forward = map.findForward("fil070");
        }

        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);

        if (form.getAdmLockKbn() == GSConstFile.LOCK_KBN_ON) {
            Fil080ParamModel paramMdl = new Fil080ParamModel();
            paramMdl.setParam(form);
            biz.fileUnLock(paramMdl, buMdl.getUsrsid());
            paramMdl.setFormData(form);
        }

        return forward;
    }

    /**
     * <br>[機  能] 追加・編集ボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception  実行時例外
     */
    private ActionForward __doEdit(ActionMapping map,
                                    Fil080Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws Exception {

        con.setAutoCommit(true);
        //ファイルロックチェック
        ActionForward forward = __checkFileLock(map, form, req, con);
        if (forward != null) {
            return forward;
        }

        CommonBiz cmnBiz = new CommonBiz();

        //テンポラリディレクトリパスを取得
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), getRequestModel(req));

        //入力チェック
        ActionErrors errors = form.fil080validateCheck(tempDir, con, getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }



        saveToken(req);

        return map.findForward("fil080kn");
    }

    /**
     * <br>[機  能] フォルダ削除ボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws SQLException 実行例外
     * @return ActionForward
     */
    private ActionForward __doDelete(
        ActionMapping map,
        Fil080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws IOException, IOToolsException, SQLException {

        con.setAutoCommit(true);
        //ファイルロックチェック
        ActionForward forward = __checkFileLock(map, form, req, con);
        if (forward != null) {
            return forward;
        }

        return __setKakuninPageParam(map, req, form, con);
    }

    /**
     * <br>[機  能] 削除ボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws SQLException 実行例外
     * @return ActionForward
     */
    private ActionForward __doDeleteOk(
        ActionMapping map,
        Fil080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws IOException, IOToolsException, SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        RequestModel reqMdl = getRequestModel(req);
        Fil080Biz biz = new Fil080Biz(con, reqMdl);

        //ファイルロックチェック
        ActionForward forward = __checkFileLock(map, form, req, con);
        if (forward != null) {
            return forward;
        }

        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);

        //テンポラリディレクトリパスを取得
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), reqMdl);

        boolean commitFlg = false;
        String fileName = "";

        try {
            //ディレクトリを削除する。
            Fil080ParamModel paramMdl = new Fil080ParamModel();
            paramMdl.setParam(form);
            fileName = biz.deleteFile(paramMdl, tempDir, buMdl.getUsrsid());
            paramMdl.setFormData(form);

            commitFlg = true;
        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }

        GsMessage gsMsg = new GsMessage();
        String textDel = gsMsg.getMessage(req, "cmn.delete");

        //ログ出力処理
        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));
        filBiz.outPutLog(
                textDel, GSConstLog.LEVEL_TRACE, "[name]" + fileName, map.getType());

        //テンポラリディレクトリのファイル削除を行う
        IOTools.deleteDir(tempDir);

        return __setCompPageParam(map, req, form);
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
     * @return ActionForward
     * @throws Exception  実行時例外
     */
    private ActionForward __doDeleteTemp(
        ActionMapping map,
        Fil080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //選択された添付ファイルを削除する
        cmnBiz.deleteFile(form.getFil080TempFiles(), tempDir);

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ファイルがロックされているか判定する。
     * <br>[解  説] 編集ユーザでロックされていない場合はロックする。
     * <br>[備  考]
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @throws SQLException 実行例外
     * @return ActionForward
     */
    private boolean __checkFileLock(
        Fil080Form form,
        HttpServletRequest req,
        Connection con) throws SQLException {
        boolean errorFlg = true;

        int dirSid = NullDefault.getInt(form.getFil070DirSid(), -1);
        if (dirSid == -1) {
            return errorFlg;
        }
        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));

        //管理者設定ロック区分を取得する。
        int admLockKbn = filBiz.getLockKbnAdmin(con);
        form.setAdmLockKbn(admLockKbn);
        if (admLockKbn == GSConstFile.LOCK_KBN_OFF) {
            return errorFlg;
        }

        //ファイルにロックがかかっていないか判定する。
        errorFlg = filBiz.checkFileLock(dirSid, getSessionUserSid(req), con);

        return errorFlg;
    }

    /**
     * <br>[機  能] 確認メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @param con コネクション
     * @return ActionForward アクションフォワード
     * @throws SQLException SQL実行時の例外
     */
    private ActionForward __setKakuninPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Fil080Form form,
        Connection con) throws SQLException {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForwardOk = null;
        ActionForward urlForwardCancel = null;

        //メッセージセット
        String msgState = null;

        msgState = "sakujo.kakunin.once";

        GsMessage gsMsg = new GsMessage();
        String textFile = gsMsg.getMessage(req, "cmn.file");

        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        urlForwardCancel = map.findForward("fil080");
        urlForwardOk = map.findForward("fil080deleteOk");
        cmn999Form.setUrlOK(urlForwardOk.getPath());
        cmn999Form.setUrlCancel(urlForwardCancel.getPath());

        cmn999Form.setMessage(msgRes.getMessage(msgState, textFile));

        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());
        cmn999Form.addHiddenParam("backDspLow", form.getBackDspLow());
        cmn999Form.addHiddenParam("admVerKbn", form.getAdmVerKbn());
        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
        cmn999Form.addHiddenParam("fil050SortKey", form.getFil050SortKey());
        cmn999Form.addHiddenParam("fil050OrderKey", form.getFil050OrderKey());
        cmn999Form.addHiddenParam("fil050SltDirSid", form.getFil050SltDirSid());
        cmn999Form.addHiddenParam("fil050SltDirVer", form.getFil050SltDirVer());
        cmn999Form.addHiddenParam("fil050DirSid", form.getFil050DirSid());

        cmn999Form.addHiddenParam("fil070DirSid", form.getFil070DirSid());
        cmn999Form.addHiddenParam("fil070ParentDirSid", form.getFil070ParentDirSid());

        cmn999Form.addHiddenParam("fil080PluginId", form.getFil080PluginId());
        cmn999Form.addHiddenParam("fil080Mode", form.getFil080Mode());
        cmn999Form.addHiddenParam("fil080VerKbn", form.getFil080VerKbn());
        cmn999Form.addHiddenParam("fil080Biko", form.getFil080Biko());
        cmn999Form.addHiddenParam("fil080UpCmt", form.getFil080UpCmt());
        cmn999Form.addHiddenParam("fil080PluralKbn", form.getFil080PluralKbn());
        cmn999Form.addHiddenParam("fil080SvPluralKbn", form.getFil080SvPluralKbn());

        cmn999Form.addHiddenParam("fil100ChkTrgFolder", form.getFil100ChkTrgFolder());
        cmn999Form.addHiddenParam("fil100ChkTrgFile", form.getFil100ChkTrgFile());
        cmn999Form.addHiddenParam("fil100SearchMode", form.getFil100SearchMode());
        cmn999Form.addHiddenParam("fil100ChkWdTrgName", form.getFil100ChkWdTrgName());
        cmn999Form.addHiddenParam("fil100ChkWdTrgBiko", form.getFil100ChkWdTrgBiko());
        cmn999Form.addHiddenParam("fil100ChkWdTrgText", form.getFil100ChkWdTrgText());
        cmn999Form.addHiddenParam("fileSearchfromYear", form.getFileSearchfromYear());
        cmn999Form.addHiddenParam("fileSearchfromMonth", form.getFileSearchfromMonth());
        cmn999Form.addHiddenParam("fileSearchfromDay", form.getFileSearchfromDay());
        cmn999Form.addHiddenParam("fileSearchtoYear", form.getFileSearchtoYear());
        cmn999Form.addHiddenParam("fileSearchtoMonth", form.getFileSearchtoMonth());
        cmn999Form.addHiddenParam("fileSearchtoDay", form.getFileSearchtoDay());
        cmn999Form.addHiddenParam("fil100ChkOnOff", form.getFil100ChkOnOff());
        cmn999Form.addHiddenParam("fil100SvSltCabinetSid", form.getFil100SvSltCabinetSid());
        cmn999Form.addHiddenParam("fil100SvChkTrgFolder", form.getFil100SvChkTrgFolder());
        cmn999Form.addHiddenParam("fil100SvChkTrgFile", form.getFil100SvChkTrgFile());
        cmn999Form.addHiddenParam("fil100SvSearchMode", form.getFil100SvSearchMode());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgName", form.getFil100SvChkWdTrgName());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgBiko", form.getFil100SvChkWdTrgBiko());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgText", form.getFil100SvChkWdTrgText());
        cmn999Form.addHiddenParam("fil100SvChkWdKeyWord", form.getFil100SvChkWdKeyWord());
        cmn999Form.addHiddenParam("fileSvSearchfromYear", form.getFileSvSearchfromYear());
        cmn999Form.addHiddenParam("fileSvSearchfromMonth", form.getFileSvSearchfromMonth());
        cmn999Form.addHiddenParam("fileSvSearchfromDay", form.getFileSvSearchfromDay());
        cmn999Form.addHiddenParam("fileSvSearchtoYear", form.getFileSvSearchtoYear());
        cmn999Form.addHiddenParam("fileSvSearchtoMonth", form.getFileSvSearchtoMonth());
        cmn999Form.addHiddenParam("fileSvSearchtoDay", form.getFileSvSearchtoDay());
        cmn999Form.addHiddenParam("fil100SvChkOnOff", form.getFil100SvChkOnOff());
        cmn999Form.addHiddenParam("fil100sortKey", form.getFil100sortKey());
        cmn999Form.addHiddenParam("fil100orderKey", form.getFil100orderKey());
        cmn999Form.addHiddenParam("fil100pageNum1", form.getFil100pageNum1());
        cmn999Form.addHiddenParam("fil100pageNum2", form.getFil100pageNum2());
        cmn999Form.addHiddenParam("fil240PageNum", form.getFil240PageNum());

        cmn999Form.addHiddenParam("backDspCall", form.getBackDspCall());

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @return ActionForward アクションフォワード
     * @throws IOToolsException 添付ファイルの操作に失敗
     */
    private ActionForward __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Fil080Form form) throws IOToolsException {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        if (form.getBackDspCall() == null || form.getBackDspCall().equals("")) {
            urlForward = map.findForward("fil040");
        } else {
            urlForward = map.findForward("fil240");
        }
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "sakujo.kanryo.object";

        GsMessage gsMsg = new GsMessage();
        String textFile = gsMsg.getMessage(req, "cmn.file");

        cmn999Form.setMessage(msgRes.getMessage(msgState, textFile));

        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());
        cmn999Form.addHiddenParam("backDspLow", form.getBackDspLow());
        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
        cmn999Form.addHiddenParam("fil070DirSid", form.getFil070DirSid());
        cmn999Form.addHiddenParam("fil070ParentDirSid", form.getFil070ParentDirSid());

        cmn999Form.addHiddenParam("fil100ChkTrgFolder", form.getFil100ChkTrgFolder());
        cmn999Form.addHiddenParam("fil100ChkTrgFile", form.getFil100ChkTrgFile());
        cmn999Form.addHiddenParam("fil100SearchMode", form.getFil100SearchMode());
        cmn999Form.addHiddenParam("fil100ChkWdTrgName", form.getFil100ChkWdTrgName());
        cmn999Form.addHiddenParam("fil100ChkWdTrgBiko", form.getFil100ChkWdTrgBiko());
        cmn999Form.addHiddenParam("fil100ChkWdTrgText", form.getFil100ChkWdTrgText());
        cmn999Form.addHiddenParam("fileSearchfromYear", form.getFileSearchfromYear());
        cmn999Form.addHiddenParam("fileSearchfromMonth", form.getFileSearchfromMonth());
        cmn999Form.addHiddenParam("fileSearchfromDay", form.getFileSearchfromDay());
        cmn999Form.addHiddenParam("fileSearchtoYear", form.getFileSearchtoYear());
        cmn999Form.addHiddenParam("fileSearchtoMonth", form.getFileSearchtoMonth());
        cmn999Form.addHiddenParam("fileSearchtoDay", form.getFileSearchtoDay());
        cmn999Form.addHiddenParam("fil100ChkOnOff", form.getFil100ChkOnOff());
        cmn999Form.addHiddenParam("fil100SvSltCabinetSid", form.getFil100SvSltCabinetSid());
        cmn999Form.addHiddenParam("fil100SvChkTrgFolder", form.getFil100SvChkTrgFolder());
        cmn999Form.addHiddenParam("fil100SvChkTrgFile", form.getFil100SvChkTrgFile());
        cmn999Form.addHiddenParam("fil100SvSearchMode", form.getFil100SvSearchMode());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgName", form.getFil100SvChkWdTrgName());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgBiko", form.getFil100SvChkWdTrgBiko());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgText", form.getFil100SvChkWdTrgText());
        cmn999Form.addHiddenParam("fil100SvChkWdKeyWord", form.getFil100SvChkWdKeyWord());
        cmn999Form.addHiddenParam("fileSvSearchfromYear", form.getFileSvSearchfromYear());
        cmn999Form.addHiddenParam("fileSvSearchfromMonth", form.getFileSvSearchfromMonth());
        cmn999Form.addHiddenParam("fileSvSearchfromDay", form.getFileSvSearchfromDay());
        cmn999Form.addHiddenParam("fileSvSearchtoYear", form.getFileSvSearchtoYear());
        cmn999Form.addHiddenParam("fileSvSearchtoMonth", form.getFileSvSearchtoMonth());
        cmn999Form.addHiddenParam("fileSvSearchtoDay", form.getFileSvSearchtoDay());
        cmn999Form.addHiddenParam("fil100SvChkOnOff", form.getFil100SvChkOnOff());
        cmn999Form.addHiddenParam("fil100sortKey", form.getFil100sortKey());
        cmn999Form.addHiddenParam("fil100orderKey", form.getFil100orderKey());
        cmn999Form.addHiddenParam("fil100pageNum1", form.getFil100pageNum1());
        cmn999Form.addHiddenParam("fil100pageNum2", form.getFil100pageNum2());
        cmn999Form.addHiddenParam("fil240PageNum", form.getFil240PageNum());

        cmn999Form.addHiddenParam("backDspCall", form.getBackDspCall());

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }




























    /**
     * <br>[機  能] キャビネットへのアクセス権限があるか判定する。
     * <br>[解  説] 編集ユーザでロックされていない場合はロックする。
     * <br>[備  考]
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @throws SQLException 実行例外
     * @return ActionForward
     */
    private boolean __checkViewCabinet(
        Fil080Form form,
        HttpServletRequest req,
        Connection con) throws SQLException {
        boolean errorFlg = true;

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        int dirSid = NullDefault.getInt(form.getFil070DirSid(), -1);
        int fcbSid = cmnBiz.getCabinetSid(dirSid, con);

        //ディレクトリが未選択の場合、ディレクトリSIDを設定
        if (NullDefault.getInt(form.getFil010SelectDirSid(), -1) < 0) {
            form.setFil010SelectDirSid(String.valueOf(dirSid));
        }

        //キャビネットが未選択の場合、キャビネットSIDを設定
        if (NullDefault.getInt(form.getFil010SelectCabinet(), -1) < 0) {
            form.setFil010SelectCabinet(String.valueOf(fcbSid));
        }

        //セッションユーザ情報を取得する。
        HttpSession session = req.getSession();
        BaseUserModel umodel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

        //キャビネットへのアクセス権限があるか判定する。
        errorFlg = cmnBiz.isAccessAuthUser(NullDefault.getInt(form.getFil010SelectCabinet(), fcbSid)
                                         , con
                                         , umodel);
        return errorFlg;
    }


    /**
     * <br>[機  能] ファイルがロックされているか判定する。
     * <br>[解  説] 編集ユーザでロックされていない場合はロックする。
     * <br>[備  考]
     * @param map マップ
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @throws SQLException 実行例外
     * @return ActionForward
     */
    private ActionForward __checkFileLock(
        ActionMapping map,
        Fil080Form form,
        HttpServletRequest req,
        Connection con) throws SQLException {
        ActionForward forward = null;
        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));

        if (form.getFil080Mode() == GSConstFile.MODE_ADD) {
            return forward;
        }

        //管理者設定ロック区分を取得する。
        int admLockKbn = filBiz.getLockKbnAdmin(con);
        form.setAdmLockKbn(admLockKbn);
        if (admLockKbn == GSConstFile.LOCK_KBN_OFF) {
            //ロック機能が無効の場合
            return forward;
        }

        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);
        int usrSid = buMdl.getUsrsid();

        //ディレクトリSID
        int dirSid = NullDefault.getInt(form.getFil070DirSid(), -1);

        FileFileBinDao fileBinDao = new FileFileBinDao(con);
        FileFileBinModel fileBinModel = fileBinDao.getNewFile(dirSid);

        if (fileBinModel == null) {
            //ディレクトリ情報が存在しない場合
            return getSubmitErrorPage(map, req);
        }

        //ロック区分ＯＦＦならばエラー
        if (fileBinModel.getFflLockKbn() == GSConstFile.LOCK_KBN_OFF) {
            return __getFileUnlockErrorPage(map, req, form);
        }

        //ログインユーザ以外がロックしている場合
        if (fileBinModel.getFflLockKbn() == GSConstFile.LOCK_KBN_ON
                && fileBinModel.getFflLockUser() != usrSid) {
            //編集ユーザがログインユーザと異なった場合
            return __getFileLockErrorPage(map, req, form);
        }

        return forward;
    }

    /**
     * <br>[機  能] キャビネットコンボ変更時の処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception  実行時例外
     */
    private ActionForward __doChangeCabinet(ActionMapping map,
                                    Fil080Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws Exception  {

        Fil080Biz biz = new Fil080Biz(con, getRequestModel(req));

        Fil080ParamModel paramMdl = new Fil080ParamModel();
        paramMdl.setParam(form);
        biz.setChangeCabinet(paramMdl, con);
        paramMdl.setFormData(form);

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] WEBメール連携
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doCalledWebmail(
        ActionMapping map,
        Fil080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //対象メールを閲覧可能かを判定
        WmlDao wmlDao = new WmlDao(con);
        if (!wmlDao.canReadMail(form.getFil080webmailId(), getSessionUserSid(req))) {
            return getAuthErrorPageWithPopup(map, req);
        }

        //追加可能なキャビネットを設定
        RequestModel reqMdl = getRequestModel(req);
        //WEBメールの添付ファイル情報を設定
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), reqMdl);

        Fil080ParamModel paramMdl = new Fil080ParamModel();
        paramMdl.setParam(form);
        Fil080Biz biz = new Fil080Biz(con, reqMdl);
        biz.setWebmailData(paramMdl, getAppRootPath(), tempDir);
        paramMdl.setFormData(form);

        form.setFil080webmail(1);
        return null;
    }

    /**
     * <br>[機  能] ファイルロックエラーメッセージ画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form フォーム
     * @return ActionForward アクションフォワード
     */
    private ActionForward __getFileLockErrorPage(
            ActionMapping map, HttpServletRequest req, Fil080Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("fil070");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "error.file.lock";

        cmn999Form.setMessage(msgRes.getMessage(msgState));

        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());
        cmn999Form.addHiddenParam("backDspLow", form.getBackDspLow());
        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());

        cmn999Form.addHiddenParam("fil070DirSid", form.getFil070DirSid());
        cmn999Form.addHiddenParam("fil070ParentDirSid", form.getFil070ParentDirSid());

        cmn999Form.addHiddenParam("fil100ChkTrgFolder", form.getFil100ChkTrgFolder());
        cmn999Form.addHiddenParam("fil100ChkTrgFile", form.getFil100ChkTrgFile());
        cmn999Form.addHiddenParam("fil100SearchMode", form.getFil100SearchMode());
        cmn999Form.addHiddenParam("fil100ChkWdTrgName", form.getFil100ChkWdTrgName());
        cmn999Form.addHiddenParam("fil100ChkWdTrgBiko", form.getFil100ChkWdTrgBiko());
        cmn999Form.addHiddenParam("fileSearchfromYear", form.getFileSearchfromYear());
        cmn999Form.addHiddenParam("fileSearchfromMonth", form.getFileSearchfromMonth());
        cmn999Form.addHiddenParam("fileSearchfromDay", form.getFileSearchfromDay());
        cmn999Form.addHiddenParam("fileSearchtoYear", form.getFileSearchtoYear());
        cmn999Form.addHiddenParam("fileSearchtoMonth", form.getFileSearchtoMonth());
        cmn999Form.addHiddenParam("fileSearchtoDay", form.getFileSearchtoDay());
        cmn999Form.addHiddenParam("fil100ChkOnOff", form.getFil100ChkOnOff());
        cmn999Form.addHiddenParam("fil100SvSltCabinetSid", form.getFil100SvSltCabinetSid());
        cmn999Form.addHiddenParam("fil100SvChkTrgFolder", form.getFil100SvChkTrgFolder());
        cmn999Form.addHiddenParam("fil100SvChkTrgFile", form.getFil100SvChkTrgFile());
        cmn999Form.addHiddenParam("fil100SvSearchMode", form.getFil100SvSearchMode());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgName", form.getFil100SvChkWdTrgName());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgBiko", form.getFil100SvChkWdTrgBiko());
        cmn999Form.addHiddenParam("fil100SvChkWdKeyWord", form.getFil100SvChkWdKeyWord());
        cmn999Form.addHiddenParam("fileSvSearchfromYear", form.getFileSvSearchfromYear());
        cmn999Form.addHiddenParam("fileSvSearchfromMonth", form.getFileSvSearchfromMonth());
        cmn999Form.addHiddenParam("fileSvSearchfromDay", form.getFileSvSearchfromDay());
        cmn999Form.addHiddenParam("fileSvSearchtoYear", form.getFileSvSearchtoYear());
        cmn999Form.addHiddenParam("fileSvSearchtoMonth", form.getFileSvSearchtoMonth());
        cmn999Form.addHiddenParam("fileSvSearchtoDay", form.getFileSvSearchtoDay());
        cmn999Form.addHiddenParam("fil100SvChkOnOff", form.getFil100SvChkOnOff());
        cmn999Form.addHiddenParam("fil100sortKey", form.getFil100sortKey());
        cmn999Form.addHiddenParam("fil100orderKey", form.getFil100orderKey());
        cmn999Form.addHiddenParam("fil100pageNum1", form.getFil100pageNum1());
        cmn999Form.addHiddenParam("fil100pageNum2", form.getFil100pageNum2());
        cmn999Form.addHiddenParam("fil240PageNum", form.getFil240PageNum());
        cmn999Form.addHiddenParam("backDspCall", form.getBackDspCall());
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] ファイルロック解除エラーメッセージ画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form フォーム
     * @return ActionForward アクションフォワード
     */
    private ActionForward __getFileUnlockErrorPage(
        ActionMapping map, HttpServletRequest req, Fil080Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("fil040");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "error.file.unlock";

        cmn999Form.setMessage(msgRes.getMessage(msgState));

        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());
        cmn999Form.addHiddenParam("backDspLow", form.getBackDspLow());
        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
        cmn999Form.addHiddenParam("fil070DirSid", form.getFil070DirSid());
        cmn999Form.addHiddenParam("fil070ParentDirSid", form.getFil070ParentDirSid());

        cmn999Form.addHiddenParam("fil100ChkTrgFolder", form.getFil100ChkTrgFolder());
        cmn999Form.addHiddenParam("fil100ChkTrgFile", form.getFil100ChkTrgFile());
        cmn999Form.addHiddenParam("fil100SearchMode", form.getFil100SearchMode());
        cmn999Form.addHiddenParam("fil100ChkWdTrgName", form.getFil100ChkWdTrgName());
        cmn999Form.addHiddenParam("fil100ChkWdTrgBiko", form.getFil100ChkWdTrgBiko());
        cmn999Form.addHiddenParam("fil100ChkWdTrgText", form.getFil100ChkWdTrgText());
        cmn999Form.addHiddenParam("fileSearchfromYear", form.getFileSearchfromYear());
        cmn999Form.addHiddenParam("fileSearchfromMonth", form.getFileSearchfromMonth());
        cmn999Form.addHiddenParam("fileSearchfromDay", form.getFileSearchfromDay());
        cmn999Form.addHiddenParam("fileSearchtoYear", form.getFileSearchtoYear());
        cmn999Form.addHiddenParam("fileSearchtoMonth", form.getFileSearchtoMonth());
        cmn999Form.addHiddenParam("fileSearchtoDay", form.getFileSearchtoDay());
        cmn999Form.addHiddenParam("fil100ChkOnOff", form.getFil100ChkOnOff());
        cmn999Form.addHiddenParam("fil100SvSltCabinetSid", form.getFil100SvSltCabinetSid());
        cmn999Form.addHiddenParam("fil100SvChkTrgFolder", form.getFil100SvChkTrgFolder());
        cmn999Form.addHiddenParam("fil100SvChkTrgFile", form.getFil100SvChkTrgFile());
        cmn999Form.addHiddenParam("fil100SvSearchMode", form.getFil100SvSearchMode());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgName", form.getFil100SvChkWdTrgName());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgBiko", form.getFil100SvChkWdTrgBiko());
        cmn999Form.addHiddenParam("fil100SvChkWdTrgText", form.getFil100SvChkWdTrgText());
        cmn999Form.addHiddenParam("fil100SvChkWdKeyWord", form.getFil100SvChkWdKeyWord());
        cmn999Form.addHiddenParam("fileSvSearchfromYear", form.getFileSvSearchfromYear());
        cmn999Form.addHiddenParam("fileSvSearchfromMonth", form.getFileSvSearchfromMonth());
        cmn999Form.addHiddenParam("fileSvSearchfromDay", form.getFileSvSearchfromDay());
        cmn999Form.addHiddenParam("fileSvSearchtoYear", form.getFileSvSearchtoYear());
        cmn999Form.addHiddenParam("fileSvSearchtoMonth", form.getFileSvSearchtoMonth());
        cmn999Form.addHiddenParam("fileSvSearchtoDay", form.getFileSvSearchtoDay());
        cmn999Form.addHiddenParam("fil100SvChkOnOff", form.getFil100SvChkOnOff());
        cmn999Form.addHiddenParam("fil100sortKey", form.getFil100sortKey());
        cmn999Form.addHiddenParam("fil100orderKey", form.getFil100orderKey());
        cmn999Form.addHiddenParam("fil100pageNum1", form.getFil100pageNum1());
        cmn999Form.addHiddenParam("fil100pageNum2", form.getFil100pageNum2());
        cmn999Form.addHiddenParam("fil240PageNum", form.getFil240PageNum());
        cmn999Form.addHiddenParam("backDspCall", form.getBackDspCall());
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
         }

    /**
     * <br>[機  能] フルアクセス追加ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doFullAddUser(
        ActionMapping map,
        Fil080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil080SvAcFull(
                cmnBiz.getAddMember(form.getFil080AcEditRight(), form.getFil080SvAcFull()));

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] フルアクセス削除ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doFullDelUser(
        ActionMapping map,
        Fil080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil080SvAcFull(
                cmnBiz.getDeleteMember(form.getFil080AcFull(), form.getFil080SvAcFull()));

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 閲覧アクセス追加ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doReadAddUser(
        ActionMapping map,
        Fil080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil080SvAcRead(
                cmnBiz.getAddMember(form.getFil080AcReadRight(), form.getFil080SvAcRead()));

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 閲覧アクセス削除ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doReadDelUser(
        ActionMapping map,
        Fil080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil080SvAcRead(
                cmnBiz.getDeleteMember(form.getFil080AcRead(), form.getFil080SvAcRead()));

        return __doInit(map, form, req, res, con);
    }

}