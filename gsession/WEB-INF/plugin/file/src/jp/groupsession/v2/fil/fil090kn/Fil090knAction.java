package jp.groupsession.v2.fil.fil090kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.fil.AbstractFileAction;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.fil070.Fil070Form;
import jp.groupsession.v2.fil.fil090.Fil090Biz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] フォルダ・ファイル移動確認画面のアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil090knAction extends AbstractFileAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil090knAction.class);

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

        log__.debug("fil090knAction START");

        ActionForward forward = null;
        Fil090knForm thisForm = (Fil090knForm) form;

        con.setAutoCommit(true);
        //キャビネットアクセス権限チェック
        if (!__checkViewCabinet(thisForm, req, con)) {
            return getCanNotViewErrorPage(map, req);
        }
        //選択ファイル権限チェック
        if (!thisForm.checkSltDirAccess(con, getRequestModel(req))) {
            GsMessage gsMsg = new GsMessage(req);
            return getPowNoneErrorPage(map, req,
                    gsMsg.getMessage("cmn.edit"),
                    gsMsg.getMessage("cmn.move"));
        }
        //移動先ディレクトリ権限チェック
        if (!thisForm.checkSltMoveDirAccess(con, getRequestModel(req))) {
            GsMessage gsMsg = new GsMessage(req);
            return getPowNoneErrorPage(map, req,
                    gsMsg.getMessage("cmn.edit"),
                    gsMsg.getMessage("cmn.move"));
        }

        con.setAutoCommit(false);

        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("fil090knback")) {
            //戻るボタンクリック
            forward = __doBack(map, thisForm);

        } else if (cmd.equals("fil090knok")) {
            //移動ボタンクリック
            forward = __doMove(map, thisForm, req, res, con);

        } else if (cmd.equals("fileDownload")) {
            //添付ファイル名クリック
            forward = __doDownLoad(map, thisForm, req, res, con);

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
     * @throws IOToolsException ファイル操作時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Fil090knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {
        con.setAutoCommit(true);
        CommonBiz cmnBiz = new CommonBiz();
        Fil090knBiz biz = new Fil090knBiz(con, getRequestModel(req));

        //テンポラリディレクトリパスを取得
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), getRequestModel(req));

        //初期表示を設定する。
        Fil090knParamModel paramMdl = new Fil090knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, tempDir, getAppRootPath());
        paramMdl.setFormData(form);
        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 遷移元画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @return ActionForward フォワード
     */
    private ActionForward __doBack(ActionMapping map, Fil090knForm form) {

        ActionForward forward = null;

        forward = map.findForward("fil090");
        return forward;
    }

    /**
     * <br>[機  能] OKボタンクリック時の処理。
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
     * @throws IOToolsException ファイル操作時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doMove(
            ActionMapping map, Fil090knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException, IOToolsException, IOException, TempFileException {

        //入力チェック
        BaseUserModel buMdl = getSessionUserModel(req);
        ActionErrors errors = form.fil090validateCheck(
                con, getRequestModel(req), buMdl.getUsrsid());
        if (!errors.isEmpty()) {
            log__.info("入力エラー");
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }
        //2重投稿
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        CommonBiz cmnBiz = new CommonBiz();
        Fil090knBiz biz = new Fil090knBiz(con, getRequestModel(req));

        //テンポラリディレクトリパスを取得
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), getRequestModel(req));
        //セッションユーザモデル

        boolean commitFlg = false;
        con.setAutoCommit(false);
        String dirName = "";
        try {

            if (form.getFil090SelectPluralKbn() == Fil090Biz.MOVE_PLURAL_NO) {
                //ディレクトリ移動処理（単一移動）
                Fil090knParamModel paramMdl = new Fil090knParamModel();
                paramMdl.setParam(form);
                biz.moveDir(paramMdl, buMdl);
                paramMdl.setFormData(form);

                //移動ディレクトリ名
                int dirSid = NullDefault.getInt(form.getFil090DirSid(), 0);
                FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));
                dirName = filBiz.getDirctoryName(dirSid, con);

            } else {
                //ディレクトリ移動処理（一括移動）
                Fil090knParamModel paramMdl = new Fil090knParamModel();
                paramMdl.setParam(form);
                biz.moveDirPlural(paramMdl, tempDir, buMdl, getAppRootPath());
                paramMdl.setFormData(form);
            }

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
        String textMoveFolder = gsMsg.getMessage(req, "cmn.move.folder");
        String textMoveFile = gsMsg.getMessage(req, "fil.move.file");
        String textEdit = gsMsg.getMessage(req, "cmn.change");
        String textMovePluralFile = gsMsg.getMessage(req, "fil.116");

        //ログ出力処理
        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));
        String logValue = "";
        if (form.getFil090SelectPluralKbn() == Fil090Biz.MOVE_PLURAL_NO) {
            if (form.getFil090Mode().equals(String.valueOf(GSConstFile.DIRECTORY_FILE))) {
                logValue = textMoveFile;
            } else {
                logValue = textMoveFolder;
            }
            logValue = logValue + "[name]" + dirName;
        } else {
            logValue = textMovePluralFile;
        }


        filBiz.outPutLog(
                textEdit, GSConstLog.LEVEL_TRACE, logValue, map.getType());

        //テンポラリディレクトリのファイル削除を行う
        IOTools.deleteDir(tempDir);
        return __setCompPageParam(map, req, form);
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @return ActionForward アクションフォワード
     */
    private ActionForward __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Fil090knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("fil040");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = null;

        msgState = "cmn.kanryo.object";

        GsMessage gsMsg = new GsMessage();
        String textFolder = gsMsg.getMessage(req, "cmn.folder");
        String textFile = gsMsg.getMessage(req, "cmn.file");

        String dir = "";
        if (form.getFil090Mode().equals(String.valueOf(GSConstFile.DIRECTORY_FOLDER))) {
            dir = textFolder;
        } else {
            dir = textFile;
        }

        cmn999Form.setMessage(msgRes.getMessage(msgState, gsMsg.getMessage(req, "fil.move", dir)));

        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());

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
        Fil070Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        CommonBiz cmnBiz = new CommonBiz();
        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));

        //テンポラリディレクトリ
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), getRequestModel(req));

        //添付ファイルのダウンロード
        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(
                tempDir, form.getFileSid().concat(GSConstCommon.ENDSTR_OBJFILE));
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
        //添付ファイル保存用のパスを取得する(フルパス)
        String filePath = tempDir + form.getFileSid().concat(GSConstCommon.ENDSTR_SAVEFILE);
        filePath = IOTools.replaceFileSep(filePath);

        GsMessage gsMsg = new GsMessage();
        String textDownload = gsMsg.getMessage("cmn.download");

        //ログ出力処理
        filBiz.outPutLog(
                textDownload, GSConstLog.LEVEL_INFO, fMdl.getFileName(), map.getType(),
                form.getFileSid());

        //ファイルをダウンロードする
        TempFileUtil.downloadAtachment(req, res,
                filePath, fMdl.getFileName(), Encoding.UTF_8);

        return null;
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
        Fil090knForm form,
        HttpServletRequest req,
        Connection con) throws SQLException {
        boolean errorFlg = true;

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        int dirSid = NullDefault.getInt(form.getFil090DirSid(), -1);
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
}