package jp.groupsession.v2.fil.fil060kn;

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
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.fil.AbstractFileAction;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] フォルダ登録確認画面のアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil060knAction extends AbstractFileAction {


    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil060knAction.class);

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

//        if (cmd.equals("fileDownload")) {
//            log__.debug("添付ファイルダウンロード");
//            return true;
//
//        }
        return false;
    }

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

        log__.debug("fil060knAction START");

        ActionForward forward = null;
        Fil060knForm thisForm = (Fil060knForm) form;

        //キャビネットアクセス権限チェック
        con.setAutoCommit(true);
        if (!__checkViewCabinet(thisForm, req, con)) {
            return getCanNotViewErrorPage(map, req);
        }
        //ディレクトリアクセスチェック
        int ecode = __checkEditDir(thisForm, req, con);
        if (ecode == FilCommonBiz.ERR_NONE_CAB_EDIT_POWER) {
            GsMessage gsMsg = new GsMessage(req);
            String actName = "";
            if (NullDefault.getInt(thisForm.getFil050DirSid(), -1) != -1) {
                actName = gsMsg.getMessage("cmn.edit");
            } else {
                actName = gsMsg.getMessage("fil.39");
            }
            return getPowNoneErrorPage(map, req,
                    gsMsg.getMessage("cmn.edit"),
                    actName);
        }
        if (ecode == FilCommonBiz.ERR_NOT_EXIST) {
            GsMessage gsMsg = new GsMessage(req);
            String targetName = "";
            targetName = gsMsg.getMessage("cmn.folder");
            return getCanNotViewNonePowerErrorPage(map, req, targetName);
        }
        if (ecode == FilCommonBiz.ERR_NOT_DIRKBN) {
            return getSubmitErrorPage(map, req);
        }

        con.setAutoCommit(false);


        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("fil060knback")) {
            //戻るボタンクリック
            forward = __doBack(map, thisForm);

        } else if (cmd.equals("fil060knok")) {
            //OKボタンクリック
            forward = __doRegister(map, thisForm, req, res, con);

//        } else if (cmd.equals("fileDownload")) {
//            //添付ファイル名クリック
//            forward = __doDownLoad(map, thisForm, req, res, con);

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
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Fil060knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException {

        CommonBiz cmnBiz = new CommonBiz();
        Fil060knBiz biz = new Fil060knBiz(con, getRequestModel(req));

        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);

        //テンポラリディレクトリ
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), getRequestModel(req));

        //初期表示を設定
        Fil060knParamModel paramMdl = new Fil060knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, tempDir, buMdl);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時の処理


     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doRegister(ActionMapping map,
                                    Fil060knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        Fil060knBiz biz = new Fil060knBiz(con, getRequestModel(req));

        //2重投稿
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェック
        ActionErrors errors = form.fil060validateCheck(con, req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);

        //アプリケーションルートパス
        String appRootPath = getAppRootPath();

        //テンポラリディレクトリ
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), getRequestModel(req));

        //採番コントローラ
        MlCountMtController cntCon = getCountMtController(req);

        boolean commitFlg = false;

        try {
            //登録処理
            Fil060knParamModel paramMdl = new Fil060knParamModel();
            paramMdl.setParam(form);
            biz.registerData(paramMdl, tempDir, buMdl, cntCon, appRootPath);
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
        String textEdit = gsMsg.getMessage(req, "cmn.change");
        String textEntry = gsMsg.getMessage(req, "cmn.entry");
        //ログ出力処理
        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));
        String opCode = "";
        if (form.getFil060CmdMode() == 0) {
            opCode = textEntry;
        } else {
            opCode = textEdit;
        }
        filBiz.outPutLog(
                opCode, GSConstLog.LEVEL_TRACE, "[name]" + form.getFil060DirName(), map.getType());

        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);

        //完了画面へ遷移
        return __setCompPageParam(map, req, form);
    }

    /**
     * <br>[機  能] 遷移元画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @return ActionForward フォワード
     * @throws IOToolsException ファイル操作時例外
     */
    private ActionForward __doBack(ActionMapping map, Fil060knForm form)
    throws IOToolsException {

        ActionForward forward = null;

        forward = map.findForward("fil060");

        return forward;
    }
//
//    /**
//     * <br>[機  能] 添付ファイルダウンロードの処理
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param map アクションマッピング
//     * @param form アクションフォーム
//     * @param req リクエスト
//     * @param res レスポンス
//     * @param con コネクション
//     * @throws SQLException SQL実行例外
//     * @throws Exception 実行時例外
//     * @return ActionForward
//     */
//    private ActionForward __doDownLoad(
//        ActionMapping map,
//        Fil060knForm form,
//        HttpServletRequest req,
//        HttpServletResponse res,
//        Connection con) throws SQLException, Exception {
//
//        CommonBiz cmnBiz = new CommonBiz();
//        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));
//
//        //テンポラリディレクトリ
//        String tempDir = cmnBiz.getTempDir(
//                getTempPath(req), getPluginId(), getRequestModel(req));
//
//        //添付ファイルのダウンロード
//        //オブジェクトファイルを取得
//        ObjectFile objFile = new ObjectFile(
//                tempDir, form.getFileSid().concat(GSConstCommon.ENDSTR_OBJFILE));
//        Object fObj = objFile.load();
//        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
//        //添付ファイル保存用のパスを取得する(フルパス)
//        String filePath = tempDir + form.getFileSid().concat(GSConstCommon.ENDSTR_SAVEFILE);
//        filePath = IOTools.replaceFileSep(filePath);
//
//        GsMessage gsMsg = new GsMessage();
//        String textDownload = gsMsg.getMessage("cmn.download");
//
//        //ログ出力処理
//        filBiz.outPutLog(
//                textDownload, GSConstLog.LEVEL_INFO, fMdl.getFileName(), map.getType());
//
//        //ファイルをダウンロードする
//        TempFileUtil.downloadAtachment(req, res,
//                filePath, fMdl.getFileName(), Encoding.UTF_8);
//
//        return null;
//    }

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
        Fil060knForm form) throws IOToolsException {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        if (form.getFil060CmdMode() == GSConstFile.MODE_ADD) {
            urlForward = map.findForward("fil040");
        } else {
            urlForward = map.findForward("fil050");
        }
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = null;

        if (form.getFil060CmdMode() == GSConstFile.MODE_ADD) {
            msgState = "touroku.kanryo.object";
        } else {
            msgState = "hensyu.kanryo.object";
        }

        GsMessage gsMsg = new GsMessage();
        String textFolder = gsMsg.getMessage(req, "cmn.folder");

        cmn999Form.setMessage(msgRes.getMessage(msgState, textFolder));

        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());
        cmn999Form.addHiddenParam("backDspLow", form.getBackDspLow());
        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
        cmn999Form.addHiddenParam("fil050SortKey", form.getFil050SortKey());
        cmn999Form.addHiddenParam("fil050OrderKey", form.getFil050OrderKey());
        cmn999Form.addHiddenParam("fil050DirSid", form.getFil050DirSid());

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

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
}

    /**
     * <br>[機  能] 編集権限の有無を判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param form フォーム
     * @param con コネクション
     * @param req リクエスト
     * @return authFlg true:編集権限あり　false:編集権限なし
     * @throws SQLException SQL実行時例外
     */
//    private boolean __isEditAuth(Fil060knForm form, Connection con, HttpServletRequest req)
//    throws SQLException {
//
//        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));
//
//        int dirSid = NullDefault.getInt(form.getFil050ParentDirSid(), -1);
//        if (dirSid < 1) {
//            dirSid = NullDefault.getInt(form.getFil050DirSid(), -1);
//        }
//        int cabSid = filBiz.getCabinetSid(dirSid, con);
//
//        return filBiz.isWriteAuthUser(cabSid, con);
//    }

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
        Fil060knForm form,
        HttpServletRequest req,
        Connection con) throws SQLException {
        boolean errorFlg = true;

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        int dirSid = NullDefault.getInt(form.getFil050DirSid(), -1);
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
        errorFlg = cmnBiz.isAccessAuthUser(NullDefault.getInt(
                                               form.getFil010SelectCabinet(), fcbSid),
                                           con,
                                           umodel);
        return errorFlg;
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
            Fil060knForm form,
            HttpServletRequest req,
            Connection con) throws SQLException {
        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        int dirSid = NullDefault.getInt(form.getFil050DirSid(), -1);
        if (dirSid < 1) {
            dirSid = NullDefault.getInt(form.getFil050ParentDirSid(), -1);
        }
        return cmnBiz.checkPowEditDir(dirSid, GSConstFile.DIRECTORY_FOLDER);
    }

}