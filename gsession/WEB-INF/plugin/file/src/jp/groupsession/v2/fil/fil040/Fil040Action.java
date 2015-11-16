package jp.groupsession.v2.fil.fil040;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
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
 * <br>[機  能] フォルダ情報一覧画面のアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil040Action extends AbstractFileAction {


    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil040Action.class);
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

        log__.debug("fil040Action START");

        ActionForward forward = null;
        Fil040Form thisForm = (Fil040Form) form;

        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("fil040back")) {
            //戻るボタンクリック
            forward = __doBack(map, thisForm);

        } else if (cmd.equals("fil040aconf")) {
            //管理者設定ボタンクリック
            forward = map.findForward("fil200");

        } else if (cmd.equals("fil040pconf")) {
            //個人設定ボタンクリック
            forward = map.findForward("fil110");

        } else if (cmd.equals("fil040edit")) {
            //編集ボタンクリック
            forward = map.findForward("fil060");

        } else if (cmd.equals("fil040delete")) {
            //削除ボタンクリック
            forward = __doDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("fil040deleteOk")) {
            //削除確認でＯＫボタンクリック
            forward = __doDeleteOk(map, thisForm, req, res, con);

        } else if (cmd.equals("fil040folderDetail")) {
            //フォルダ詳細ボタンクリック
            forward = map.findForward("fil050");

        } else if (cmd.equals("fil040fileDetail")) {
            //ファイル詳細ボタンクリック
            forward = map.findForward("fil070");

        } else if (cmd.equals("fil040search")) {
            //検索ボタンクリック
            forward = map.findForward("fil100");

        } else if (cmd.equals("fil040createFolder")) {
            //フォルダ作成ボタンクリック
            forward = map.findForward("fil060");

        } else if (cmd.equals("fil040addFile")) {
            //ファイル追加ボタンクリック
            forward = map.findForward("fil080");

        } else if (cmd.equals("fil040move")) {
            //移動ボタンクリック
            forward = map.findForward("fil090");

        } else if (cmd.equals("fil040movePlural")) {
            //一括移動ボタンクリック
            forward = __doMovePlural(map, thisForm, req, res, con);

        } else if (cmd.equals("fil040unlock")) {
            //ロック解除ボタンクリック
            forward = __doUnlock(map, thisForm, req, res, con);

        } else if (cmd.equals("fil040unlockOk")) {
            //ロック解除確認でOKボタンクリック
            forward = __doUnlockOk(map, thisForm, req, res, con);

        } else if (cmd.equals("fil040lockPlural")) {
            //一括ロックボタンクリック
            forward = __doLockPlural(map, thisForm, req, res, con);

        } else if (cmd.equals("fil040unlockPlural")) {
            //一括ロック解除ボタンクリック
            forward = __doUnlockPlural(map, thisForm, req, res, con);

        } else if (cmd.equals("fil040changeCabinet")) {
            //キャビネットコンボ変更
            forward = __doChangeCabinet(map, thisForm, req, res, con);

        } else if (cmd.equals("fileDownload")) {
            log__.debug("ファイルダウンロード");
            forward = __doDownLoad(map, thisForm, req, res, con);
        } else if (cmd.equals("detailDir")) {
            //表示ディレクトリ変更
            thisForm.setFil010SelectDirSid(
                    NullDefault.getString(thisForm.getMoveToDir(),
                            thisForm.getFil010SelectDirSid()));
            thisForm.setFil040SelectDelAll("0");
            forward = __doInit(map, thisForm, req, res, con);
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
     */
    private ActionForward __doInit(ActionMapping map,
                                    Fil040Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException, IOException  {

        con.setAutoCommit(true);
        //キャビネットアクセス権限チェック
        if (!__checkViewCabinet(form, req, con)) {
            return getCanNotViewErrorPage(map, req);
        }
        //ディレクトリアクセスチェック
        if (!__checkViewDirectory(form, req, con)) {
            GsMessage gsMsg = new GsMessage(req);
            return getPowNoneErrorPage(map, req,
                    gsMsg.getMessage("cmn.reading"),
                    gsMsg.getMessage("api.cmn.view"));
        }

        Fil040Biz biz = new Fil040Biz(getRequestModel(req));
        Fil040ParamModel paramMdl = new Fil040ParamModel();
        paramMdl.setParam(form);
        boolean existFlg = biz.setInitData(paramMdl, con);
        paramMdl.setFormData(form);

        if (!existFlg) {
            //ディレクトリが存在しない場合キャビネット一覧へ遷移する。
            return map.findForward("cabinetMain");
        }
        con.setAutoCommit(false);
        return map.getInputForward();
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
        Fil040Form form,
        HttpServletRequest req,
        Connection con) throws SQLException {
        boolean errorFlg = true;

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));

        //セッションユーザ情報を取得する。
        HttpSession session = req.getSession();
        BaseUserModel umodel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

        //キャビネットへのアクセス権限があるか判定する。
        errorFlg = cmnBiz.isAccessAuthUser(NullDefault.getInt(form.getFil010SelectCabinet(), -1)
                                         , con
                                         , umodel);
        return errorFlg;
    }

    /**
     * <br>[機  能] ディレクトリへのアクセス権限があるか判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @return true:権限あり false:権限なし
     * @throws SQLException 実行例外
     */
    private boolean __checkViewDirectory(
            Fil040Form form,
            HttpServletRequest req,
            Connection con) throws SQLException {

        boolean errorFlg = true;
        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        int dirSid = NullDefault.getInt(form.getFil010SelectDirSid(), -1);
        int fcbSid = cmnBiz.getCabinetSid(dirSid, con);

        //セッションユーザ情報を取得する。
        HttpSession session = req.getSession();
        BaseUserModel umodel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

        errorFlg = cmnBiz.isDirAccessAuthUser(fcbSid,
                                              dirSid,
                                              umodel.getUsrsid(),
                                              -1,
                                              con);

        return errorFlg;
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
    private ActionForward __doBack(ActionMapping map, Fil040Form form) {

        ActionForward forward = null;

        if (form.getBackDsp().equals(GSConstFile.MOVE_TO_MAIN)) {
            forward = map.findForward(GSConstFile.MOVE_TO_MAIN);
        } else {
            forward = map.findForward("cabinetMain");
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
    private ActionForward __doDownLoad(ActionMapping map,
                                        Fil040Form form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con) throws SQLException, Exception {

        //バイナリSID
        Long binSid = NullDefault.getLong(form.getFileSid(), -1);
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        String textDownload = gsMsg.getMessage("cmn.download");
        //権限チェック
        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));
        if (!filBiz.isDownloadAuthUser(binSid)) {
            return getPowNoneErrorPage(map, req,
                    gsMsg.getMessage("cmn.reading"),
                    textDownload);
        }

        CommonBiz cmnBiz = new CommonBiz();
        CmnBinfModel cbMdl = cmnBiz.getBinInfo(con, binSid,
                GroupSession.getResourceManager().getDomain(req));
        if (cbMdl == null) {
            return __doInit(map, form, req, res, con);
        }


        //ログ出力処理
        filBiz.outPutLog(
                textDownload, GSConstLog.LEVEL_INFO, cbMdl.getBinFileName(), map.getType(),
                String.valueOf(binSid));

        //集計用データを登録する
        filBiz.regFileDownloadLogCnt(con, getSessionUserSid(req), binSid, new UDate());

        //時間のかかる処理の前にコネクションを破棄
        JDBCUtil.closeConnectionAndNull(con);

        //ファイルをダウンロードする
        cbMdl.setBinFilekbn(GSConst.FILEKBN_FILE);
        TempFileUtil.downloadAtachment(req, res, cbMdl, getAppRootPath(), Encoding.UTF_8);
        cbMdl.removeTempFile();
        return null;
    }

    /**
     * <br>[機  能] 削除ボタンクリック時処理
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
     */
    private ActionForward __doDelete(ActionMapping map,
            Fil040Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con) throws SQLException, IOToolsException, IOException {

        ActionForward forward = null;
        con.setAutoCommit(true);
        ActionErrors errors = form.validateCheck(con, getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        // トランザクショントークン設定
        this.saveToken(req);

        //確認画面へ
        log__.debug("削除確認画面へ");
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //権限エラー警告画面パラメータの設定
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("fil040_del_ok");
        cmn999Form.setUrlOK(urlForward.getPath());
        urlForward = map.findForward("fil040_back");
        cmn999Form.setUrlCancel(urlForward.getPath());

        String msg = __getDelMessage(form, req, con, "sakujo.kakunin.once");
        cmn999Form.setMessage(msg);

        //パラメータ
        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDelLink", form.getFil010SelectDelLink());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());

        cmn999Form.addHiddenParam("fil040SortKey", form.getFil040SortKey());
        cmn999Form.addHiddenParam("fil040OrderKey", form.getFil040OrderKey());
        cmn999Form.addHiddenParam("fil040SelectDel", form.getFil040SelectDel());
        cmn999Form.addHiddenParam("fil040SelectDelAll", form.getFil040SelectDelAll());

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>[機  能] 削除確認画面でＯＫボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doDeleteOk(ActionMapping map,
            Fil040Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con) throws Exception {

        RequestModel reqMdl = getRequestModel(req);
        ActionForward forward = null;
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        GsMessage gsMsg = new GsMessage(req);
        //権限チェック
        ActionErrors errors = form.validateDaccsess(new ActionErrors(),
                                                    con,
                                                    getRequestModel(req),
                                                    gsMsg.getMessage("cmn.delete"));
        if (!errors.isEmpty()) {
            return getPowNoneErrorPage(map, req,
                    gsMsg.getMessage("cmn.edit"),
                    gsMsg.getMessage("cmn.delete"));
        }

        //削除完了メッセージを取得
        String msg = __getDelMessage(form, req, con, "sakujo.kanryo.object");

        List<String> filDirList =  new ArrayList<String>();
        boolean commitFlg = false;
        con.setAutoCommit(false);
        //削除処理
        try {
            Fil040Biz biz = new Fil040Biz(reqMdl);
            Fil040ParamModel paramMdl = new Fil040ParamModel();
            paramMdl.setParam(form);
            filDirList = biz.deleteDirectory(paramMdl, con);
            paramMdl.setFormData(form);

            commitFlg = true;
        } catch (Exception e) {
            log__.error("ファイル・フォルダの削除に失敗しました" + e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        FilCommonBiz filBiz = new FilCommonBiz(con, reqMdl);
        String delete = gsMsg.getMessage("cmn.delete");

        //listに格納したディレクトリ名を削除するディレクトリ名に入れていく
        for (String fName : filDirList) {
            //ログ出力処理
            filBiz.outPutLog(
                    delete, GSConstLog.LEVEL_INFO,
                    "[name]" + fName,
                    map.getType());
        }
        forward = __setCompDsp(map, req, form, msg);
        return forward;
    }

    /**
     * <br>[機  能] 移動ボタンクリック時処理
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
     */
    private ActionForward __doMovePlural(ActionMapping map,
            Fil040Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con) throws SQLException, IOToolsException, IOException {

        BaseUserModel buMdl = getSessionUserModel(req);
        ActionErrors errors = form.validateCheckMove(con, getRequestModel(req), buMdl.getUsrsid());
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        return map.findForward("fil090");
    }

    /**
     * <br>[機  能] ロック解除ボタンクリック時処理
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
     */
    private ActionForward __doUnlock(ActionMapping map,
            Fil040Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con) throws SQLException, IOToolsException, IOException {

        ActionForward forward = null;
        con.setAutoCommit(true);
        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);
        int fcbSid = NullDefault.getInt(form.getFil010SelectCabinet(), -1);
        int dirSid = NullDefault.getInt(form.getFil040SelectUnlock(), -1);

        //ロック解除権限チェック
        FilCommonBiz fileBiz = new FilCommonBiz(con, getRequestModel(req));
        if (!fileBiz.isCanFileUnlockUser(fcbSid, con)
                && !fileBiz.checkFileLock(dirSid, buMdl.getUsrsid(), con)) {
            return __getFileUnlockErrorPage(map, req, form);
        }

        // トランザクショントークン設定
        this.saveToken(req);

        GsMessage gsMsg = new GsMessage();
        String textFile = gsMsg.getMessage(req, "cmn.file");

        //確認画面へ
        log__.debug("ロック解除確認画面へ");
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //権限エラー警告画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("fil040_unlock_ok");
        cmn999Form.setUrlOK(urlForward.getPath());
        urlForward = map.findForward("fil040_back");
        cmn999Form.setUrlCancel(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("kaijyo.kakunin.unlock", textFile));
        //パラメータ
        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDelLink", form.getFil010SelectDelLink());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());

        cmn999Form.addHiddenParam("fil040SortKey", form.getFil040SortKey());
        cmn999Form.addHiddenParam("fil040OrderKey", form.getFil040OrderKey());
        cmn999Form.addHiddenParam("fil040SelectDel", form.getFil040SelectDel());
        cmn999Form.addHiddenParam("fil040SelectDelAll", form.getFil040SelectDelAll());

        cmn999Form.addHiddenParam("fil040SelectUnlock", form.getFil040SelectUnlock());
        cmn999Form.addHiddenParam("fil040SelectUnlockVer", form.getFil040SelectUnlockVer());

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>[機  能] アンロック確認画面でＯＫボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doUnlockOk(ActionMapping map,
            Fil040Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con) throws Exception {

        ActionForward forward = null;
        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);
        int fcbSid = NullDefault.getInt(form.getFil010SelectCabinet(), -1);
        int dirSid = NullDefault.getInt(form.getFil040SelectUnlock(), -1);

        //ロック解除権限チェック
        FilCommonBiz fileBiz = new FilCommonBiz(con, getRequestModel(req));
        if (!fileBiz.isCanFileUnlockUser(fcbSid, con)
                && !fileBiz.checkFileLock(dirSid, buMdl.getUsrsid(), con)) {
            return __getFileUnlockErrorPage(map, req, form);
        }

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }
        boolean commitFlg = false;
        con.setAutoCommit(false);
        //ロックの解除処理
        try {
            Fil040Biz biz = new Fil040Biz(getRequestModel(req));

            Fil040ParamModel paramMdl = new Fil040ParamModel();
            paramMdl.setParam(form);
            biz.unLock(buMdl.getUsrsid(), paramMdl, con);
            paramMdl.setFormData(form);

            commitFlg = true;
        } catch (Exception e) {
            log__.error("ロックの解除に失敗しました" + e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        //メッセージ作成
        MessageResources msgRes = getResources(req);
        String msg = msgRes.getMessage("kaijyo.kanryo.unlock");
        forward = __setCompDsp(map, req, form, msg);

        return forward;
    }

    /**
     * <br>[機  能] 完了画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @param msg 表示するメッセージ
     * @return ActionForward フォワード
     */
    private ActionForward __setCompDsp(ActionMapping map,
                                        HttpServletRequest req,
                                        Fil040Form form,
                                        String msg) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("fil040_back");
        cmn999Form.setUrlOK(forwardOk.getPath());

        //メッセージ
        cmn999Form.setMessage(msg);

        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDelLink", form.getFil010SelectDelLink());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());

        cmn999Form.addHiddenParam("fil040SortKey", form.getFil040SortKey());
        cmn999Form.addHiddenParam("fil040OrderKey", form.getFil040OrderKey());
        cmn999Form.addHiddenParam("fil040SelectDelAll", form.getFil040SelectDelAll());

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
    private ActionForward __getFileUnlockErrorPage(ActionMapping map,
                                                   HttpServletRequest req,
                                                   Fil040Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("fil040_back");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "error.file.unlock";

        cmn999Form.setMessage(msgRes.getMessage(msgState));

        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDelLink", form.getFil010SelectDelLink());
        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());

        cmn999Form.addHiddenParam("fil040SortKey", form.getFil040SortKey());
        cmn999Form.addHiddenParam("fil040OrderKey", form.getFil040OrderKey());
        cmn999Form.addHiddenParam("fil040SelectDelAll", form.getFil040SelectDelAll());
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
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
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     * @throws IOException IOエラー
     */
    private ActionForward __doChangeCabinet(ActionMapping map,
                                    Fil040Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException, IOException  {

        Fil040Biz biz = new Fil040Biz(getRequestModel(req));

        Fil040ParamModel paramMdl = new Fil040ParamModel();
        paramMdl.setParam(form);
        biz.setChangeCabinet(paramMdl, con);
        paramMdl.setFormData(form);

        return __doInit(map, form, req, res, con);

    }

    /**
     * <br>[機  能] ロックボタンクリック時処理
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
     */
    private ActionForward __doLockPlural(ActionMapping map,
            Fil040Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con) throws SQLException, IOToolsException, IOException {

        Fil040Biz biz = new Fil040Biz(getRequestModel(req));

        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);

        //ロック可能チェック
        ActionErrors errors = form.fileLockCheck(con, getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }
        //ファイルロックを行う。
        Fil040ParamModel paramMdl = new Fil040ParamModel();
        paramMdl.setParam(form);
        biz.lockPlural(buMdl.getUsrsid(), paramMdl, con);
        paramMdl.setFormData(form);

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ロック解除ボタンクリック時処理
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
     */
    private ActionForward __doUnlockPlural(ActionMapping map,
            Fil040Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con) throws SQLException, IOToolsException, IOException {

        Fil040Biz biz = new Fil040Biz(getRequestModel(req));

        //セッションユーザモデル
        BaseUserModel buMdl = getSessionUserModel(req);

        //ロック解除可能チェック
        ActionErrors errors = form.fileUnlockCheck(con, getRequestModel(req), buMdl);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        //ファイルロックを行う。
        Fil040ParamModel paramMdl = new Fil040ParamModel();
        paramMdl.setParam(form);
        biz.unlockPlural(buMdl.getUsrsid(), paramMdl, con);
        paramMdl.setFormData(form);

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除時のメッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param form フォーム
     * @param req リクエスト
     * @param con コネクション
     * @param msgKey 削除メッセージのメッセージキー
     * @return 削除時のメッセージ
     * @throws SQLException SQL実行時例外
     */
    private String __getDelMessage(Fil040Form form, HttpServletRequest req, Connection con,
                                    String msgKey)
    throws SQLException {
        Fil040Biz biz = new Fil040Biz(getRequestModel(req));

        Fil040ParamModel paramMdl = new Fil040ParamModel();
        paramMdl.setParam(form);
        List<Integer> fdrType = biz.getFdrKbn(paramMdl, con);
        paramMdl.setFormData(form);

        String delTarget = "";
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        if (fdrType.indexOf(GSConstFile.DIRECTORY_FOLDER) >= 0) {
            delTarget = gsMsg.getMessage("cmn.folder");
        }

        if (fdrType.indexOf(GSConstFile.DIRECTORY_FILE) >= 0) {
            if (delTarget.length() > 0) {
                delTarget += "・";
            }
            delTarget += gsMsg.getMessage(req, "cmn.file");
        }

        //メッセージ作成
        MessageResources msgRes = getResources(req);
        String msg = msgRes.getMessage(msgKey, delTarget);

        return msg;
    }
}
