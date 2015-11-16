package jp.groupsession.v2.fil.fil030;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.fil.AbstractFileAction;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileAccessConfDao;
import jp.groupsession.v2.fil.dao.FileCabinetAdminDao;
import jp.groupsession.v2.fil.dao.FileCabinetBinDao;
import jp.groupsession.v2.fil.dao.FileCabinetDao;
import jp.groupsession.v2.fil.dao.FileCallConfDao;
import jp.groupsession.v2.fil.dao.FileCallDataDao;
import jp.groupsession.v2.fil.dao.FileDAccessConfDao;
import jp.groupsession.v2.fil.dao.FileDirectoryBinDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.dao.FileFileRekiDao;
import jp.groupsession.v2.fil.dao.FileFileTextDao;
import jp.groupsession.v2.fil.dao.FileShortcutConfDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] キャビネット登録・編集画面のアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil030Action extends AbstractFileAction {


    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil030Action.class);
    /** アイコン画像名 */
    public String imageFileName__ = "";
    /** アイコン画像保存名 */
    public String imageFileSaveName__ = "";


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

        log__.debug("fil030Action START");

        ActionForward forward = null;
        Fil030Form thisForm = (Fil030Form) form;
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        con.setAutoCommit(true);
        //キャビネット登録・編集権限チェック
        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        if (thisForm.getCmnMode().equals(GSConstFile.CMN_MODE_ADD)) {
            if (!cmnBiz.isCanCreateCabinetUser(con)) {
                return __doNonePowerError(map, thisForm, req, res, con);
            }
        } else if (thisForm.getCmnMode().equals(GSConstFile.CMN_MODE_MLT)) {
            if (thisForm.getFil220sltCheck() != null) {
                for (String cadSidStr : thisForm.getFil220sltCheck()) {
                    int cabSid = NullDefault.getInt(cadSidStr, -1);
                    int ecode = cmnBiz.checkPowEditCab(cabSid);
                    if (ecode == FilCommonBiz.ERR_NONE_CAB_EDIT_POWER) {
                        return __doNonePowerError(map, thisForm, req, res, con);
                    }
                    if (ecode == FilCommonBiz.ERR_NOT_EXIST) {
                        GsMessage gsMsg = new GsMessage(req);
                        String targetName = "";
                        targetName = gsMsg.getMessage("fil.23");
                        return getCanNotViewNonePowerErrorPage(map, req, targetName);
                    }

                }
            }
        } else {
            int cabSid = NullDefault.getInt(thisForm.getFil030SelectCabinet(), -1);
            int ecode = cmnBiz.checkPowEditCab(cabSid);
            if (ecode == FilCommonBiz.ERR_NONE_CAB_EDIT_POWER) {
                return __doNonePowerError(map, thisForm, req, res, con);
            }
            if (ecode == FilCommonBiz.ERR_NOT_EXIST) {
                GsMessage gsMsg = new GsMessage(req);
                String targetName = "";
                targetName = gsMsg.getMessage("fil.23");
                return getCanNotViewNonePowerErrorPage(map, req, targetName);
            }
        }
        con.setAutoCommit(false);

        if (cmd.equals("fil030back")) {
            //戻るボタンクリック
            forward = __doBack(map, thisForm, req);
        } else if (cmd.equals("fil030edit")) {
            //登録・編集ボタンクリック
            forward = __doEdit(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030delete")) {
            //削除ボタンクリック
            forward = __doDelete(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030deleteOk")) {
            //削除実行
            forward = __doDeleteCabinet(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030acgroup")) {
            //アクセス　グループコンボ変更
            forward = __doChangeGrp(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030fulladd")) {
            //アクセス　フルアクセス追加ボタンクリック
            forward = __doFullAddUser(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030fulldel")) {
            //アクセス　フルアクセス削除ボタンクリック
            forward = __doFullDelUser(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030readadd")) {
            //アクセス　閲覧アクセス追加ボタンクリック
            forward = __doReadAddUser(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030readdel")) {
            //アクセス　閲覧アクセス削除ボタンクリック
            forward = __doReadDelUser(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030admgroup")) {
            //管理者　グループコンボ変更
            forward = __doChangeGrp(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030admadd")) {
            //管理者　追加ボタンクリック
            forward = __doAdmAddUser(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030admdel")) {
            //管理者　削除ボタンクリック
            forward = __doAdmDelUser(map, thisForm, req, res, con);
//        } else if (cmd.equals("fil030tempdelete")) {
//            //添付削除
//            forward = __doTempDelete(map, thisForm, req, res, con);
        } else if (cmd.equals("fil030tempdeleteMark")) {
            //添付削除
            forward = __doTempDeleteMark(map, thisForm, req, res, con);

        } else if (cmd.equals("getImageFile")) {
            //アイコン画像ファイルの表示
            forward = __doGetImageFile(map, thisForm, req, res, con);

        } else {
            if (!cmd.equals("fil030knback") && !thisForm.getFil030InitFlg().equals("1")) {
                __doInitTemp(map, thisForm, req, res, con);
                thisForm.setFile030AdaptIncFile(GSConstFile.ADAPT_FILE_INC_KBN_OFF);
            }
            //初期表示
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理を行う
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
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doInit(
            ActionMapping map,
            Fil030Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con)
                    throws SQLException, IOToolsException, IOException, TempFileException {
        imageFileName__ = "";
        imageFileSaveName__ = "";

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 表示処理を行う
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
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doDsp(ActionMapping map,
                                    Fil030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {
        con.setAutoCommit(true);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getFil030pluginId(), getRequestModel(req));
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        Fil030Biz biz = new Fil030Biz(con, getRequestModel(req));
        //初期表示
        Fil030ParamModel paramMdl = new Fil030ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, tempDir, getAppRootPath(), cmd);
        paramMdl.setFormData(form);

        form.setFil030FileLabelList(cmnBiz.getTempFileLabelList(
                tempDir + GSConstFile.TEMP_DIR_NORMAL + File.separator));
        if (form.getCmnMode().equals(GSConstFile.CMN_MODE_MLT) == false) {
            form.setFil030FileLabelListMark(cmnBiz.getTempFileLabelList(
                    tempDir + GSConstFile.TEMP_DIR_MARK + File.separator));
        }
        if (!NullDefault.getString(form.getFil030ImageName(), "").equals("")
                && !NullDefault.getString(form.getFil030ImageSaveName(), "").equals("")) {
            imageFileName__ = form.getFil030ImageName();
            imageFileSaveName__ = form.getFil030ImageSaveName();
        }

        this.saveToken(req);
        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 登録・変更処理
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
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doEdit(ActionMapping map,
                                    Fil030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {

        ActionForward forward = null;
        //入力チェック
        ActionErrors errors = form.validateCheck(map, req, con);
        if (errors.size() > 0) {
            log__.debug("入力エラー");
            addErrors(req, errors);
            return __doDsp(map, form, req, res, con);
        }

        forward = map.findForward("fil030kn");

        return forward;
    }

//    /**
//     * <br>[機  能] 添付削除ボタンクリック時の処理
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param map アクションマッピング
//     * @param form アクションフォーム
//     * @param req リクエスト
//     * @param res レスポンス
//     * @param con コネクション
//     * @throws SQLException SQL実行例外
//     * @throws IOToolsException ファイルアクセス時例外
//     * @throws IOException IOエラー
//     * @throws TempFileException 添付ファイルUtil内での例外
//     * @return ActionForward
//     */
//    private ActionForward __doTempDelete(ActionMapping map,
//                                      Fil030Form form,
//                                      HttpServletRequest req,
//                                      HttpServletResponse res,
//                                      Connection con)
//        throws SQLException, IOToolsException, IOException, TempFileException {
//
//        //テンポラリディレクトリパスを取得
//        CommonBiz cmnBiz = new CommonBiz();
//        String tempDir = cmnBiz.getTempDir(
//                getTempPath(req), form.getFil030pluginId(), getRequestModel(req));
//
//        //選択された添付ファイルを削除する
//        cmnBiz.deleteFile(form.getFil030SelectTempFiles(),
//                tempDir + GSConstFile.TEMP_DIR_NORMAL);
//
//        return __doInit(map, form, req, res, con);
//    }

    /**
     * <br>[機  能] マーク削除ボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doTempDeleteMark(ActionMapping map,
                                      Fil030Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getFil030pluginId(), getRequestModel(req));

//        //選択された添付ファイルを削除する
//        cmnBiz.deleteFile(form.getFil030SelectTempFiles(),
//                tempDir + GSConstFile.TEMP_DIR_MARK);
//
//        //選択された添付ファイルを削除する
//        String[] tempMark = new String[1];
//        tempMark[0] = form.getFil030binSid();

        IOTools.deleteDir(tempDir + GSConstFile.TEMP_DIR_MARK);
        imageFileName__ = "";
        imageFileSaveName__ = "";
        form.setFil030ImageName("");
        form.setFil030ImageSaveName("");
        form.setFil030InitFlg("1");
        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 添付ディレクトリを初期化する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     */
    private void __doInitTemp(ActionMapping map,
                                      Fil030Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException, IOException {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getFil030pluginId(), getRequestModel(req));

        //選択された添付ファイルを削除する
        Fil030Biz biz = new Fil030Biz(con, getRequestModel(req));
        if (form.getCmnMode().equals(GSConstFile.CMN_MODE_MLT) == false) {
            biz.doDeleteFile(tempDir);
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
     * @return ActionForward フォワード
     * @throws IOToolsException ファイル操作時例外
     */
    private ActionForward __doBack(ActionMapping map, Fil030Form form, HttpServletRequest req)
    throws IOToolsException {

        ActionForward forward = null;
        if (form.getBackDsp().equals(GSConstFile.MOVE_TO_FIL010)) {
            forward = map.findForward("fil010");
        } else if (form.getBackDsp().equals(GSConstFile.MOVE_TO_FIL020)) {
            forward = map.findForward("fil020");
        } else {
            forward = map.findForward("fil220");
        }

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getFil030pluginId(), getRequestModel(req));

        //テンポラリディレクトリの削除
        IOTools.deleteDir(tempDir);
        return forward;
    }

    /**
     * <br>グループ変更処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doChangeGrp(ActionMapping map, Fil030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException, IOToolsException, IOException, TempFileException {

        log__.debug("初期表示");

        return __doDsp(map, form, req, res, con);
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
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doFullAddUser(
        ActionMapping map,
        Fil030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException, TempFileException {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil030SvAcFull(
                cmnBiz.getAddMember(form.getFil030AcRight(), form.getFil030SvAcFull()));

        return __doDsp(map, form, req, res, con);
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
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @return ActionForward
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doFullDelUser(
        ActionMapping map,
        Fil030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException, TempFileException {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil030SvAcFull(
                cmnBiz.getDeleteMember(form.getFil030AcFull(), form.getFil030SvAcFull()));

        return __doDsp(map, form, req, res, con);
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
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doReadAddUser(
        ActionMapping map,
        Fil030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException, TempFileException {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil030SvAcRead(
                cmnBiz.getAddMember(form.getFil030AcRight(), form.getFil030SvAcRead()));

        return __doDsp(map, form, req, res, con);
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
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doReadDelUser(
        ActionMapping map,
        Fil030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException, TempFileException {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil030SvAcRead(
                cmnBiz.getDeleteMember(form.getFil030AcRead(), form.getFil030SvAcRead()));

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 管理者追加ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doAdmAddUser(
        ActionMapping map,
        Fil030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException, TempFileException {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil030SvAdm(
                cmnBiz.getAddMember(form.getFil030AdmRight(), form.getFil030SvAdm()));

        return __doDsp(map, form, req, res, con);
    }
    /**
     * <br>[機  能] 管理者削除ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doAdmDelUser(
        ActionMapping map,
        Fil030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException, TempFileException {

        FilCommonBiz cmnBiz = new FilCommonBiz(con, getRequestModel(req));
        form.setFil030SvAdm(
                cmnBiz.getDeleteMember(form.getFil030Adm(), form.getFil030SvAdm()));

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>削除ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doDelete(ActionMapping map, Fil030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException, TempFileException {

        ActionForward forward = null;
        // トランザクショントークン設定
        this.saveToken(req);

        GsMessage gsMsg = new GsMessage();
        String textCabinet = gsMsg.getMessage(req, "fil.23");

        //確認画面へ
        log__.debug("削除確認画面へ");
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //権限エラー警告画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("fil030_del_ok");
        cmn999Form.setUrlOK(urlForward.getPath());
        urlForward = map.findForward("fil030_del_cancel");
        cmn999Form.setUrlCancel(urlForward.getPath());

        if (form.getCmnMode().equals(GSConstFile.CMN_MODE_EDT)) {
            cmn999Form.setMessage(msgRes.getMessage("sakujo.kakunin.list",
                    textCabinet,
                    StringUtilHtml.transToHTmlPlusAmparsant(form.getFil030CabinetName())));
        }

        cmn999Form.addHiddenParam("cmnMode", form.getCmnMode());
        cmn999Form.addHiddenParam("backDsp", form.getBackDsp());

        cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
        cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
        cmn999Form.addHiddenParam("fil040SelectDel", form.getFil040SelectDel());
        cmn999Form.addHiddenParam("fil010SelectDelLink", form.getFil010SelectDelLink());

        //入力項目
        cmn999Form.addHiddenParam("fil030SelectCabinet", form.getFil030SelectCabinet());
        cmn999Form.addHiddenParam("fil030CabinetName", form.getFil030CabinetName());
        cmn999Form.addHiddenParam("fil030AccessKbn", form.getFil030AccessKbn());
        cmn999Form.addHiddenParam("fil030CapaKbn", form.getFil030CapaKbn());
        cmn999Form.addHiddenParam("fil030CapaSize", form.getFil030CapaSize());
        cmn999Form.addHiddenParam("fil030CapaWarn", form.getFil030CapaWarn());
        cmn999Form.addHiddenParam("fil030VerKbn", form.getFil030VerKbn());
        cmn999Form.addHiddenParam("fil030VerKbnAdm", form.getFil030VerKbn());
        cmn999Form.addHiddenParam("fil030VerAllKbn", form.getFil030VerAllKbn());
        cmn999Form.addHiddenParam("fil030Biko", form.getFil030Biko());
        cmn999Form.addHiddenParam("fil030InitFlg", form.getFil030InitFlg());
        cmn999Form.addHiddenParam("fil030ImageName", form.getFil030ImageName());
        cmn999Form.addHiddenParam("fil030ImageSaveName", form.getFil030ImageSaveName());

        //アクセス制限　フル登録ユーザ
        String[] fullUsers = form.getFil030SvAcFull();
        if (fullUsers != null) {
            for (String user : fullUsers) {
                cmn999Form.addHiddenParam("fil030SvAcFull", user);
            }
        }
        //アクセス制限　フル登録ユーザ
        String[] readUsers = form.getFil030SvAcRead();
        if (readUsers != null) {
            for (String user : readUsers) {
                cmn999Form.addHiddenParam("fil030SvAcRead", user);
            }
        }

        //管理者　登録ユーザ
        String[] admUsers = form.getFil030SvAdm();
        if (admUsers != null) {
            for (String user : admUsers) {
                cmn999Form.addHiddenParam("fil030SvAdm", user);
            }
        }

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }
    /**
     * <br>削除ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doDeleteCabinet(ActionMapping map, Fil030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        ActionForward forward = null;
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        boolean commitFlg = false;
        con.setAutoCommit(false);
        //削除処理
        try {

            FileCabinetDao cabDao = new FileCabinetDao(con);
            FileCabinetAdminDao cabAdmDao = new FileCabinetAdminDao(con);
            FileAccessConfDao cabAccDao = new FileAccessConfDao(con);
            FileCabinetBinDao cabBinDao = new FileCabinetBinDao(con);
            FileDirectoryDao dirDao = new FileDirectoryDao(con);

            FileDirectoryBinDao dbinDao = new FileDirectoryBinDao(con);
            FileFileBinDao fbinDao = new FileFileBinDao(con);
            FileFileRekiDao dirBinDao = new FileFileRekiDao(con);
            FileCallConfDao callDao = new FileCallConfDao(con);
            FileCallDataDao callDateDao = new FileCallDataDao(con);
            FileShortcutConfDao shortDao = new FileShortcutConfDao(con);
            FileDAccessConfDao daConfdao = new FileDAccessConfDao(con);

            //削除対象のディレクトリSIDを取得
            int cabSid = NullDefault.getInt(form.getFil030SelectCabinet(), -1);
            ArrayList<FileDirectoryModel> dirList = dirDao.getDirectory(cabSid);
            //削除対象のバーナリーSIDを取得
            ArrayList<String> cbinList = cabBinDao.selectBinSids(cabSid);
            ArrayList<String> dbinList = dbinDao.getBinSids(dirList);
            ArrayList<String> fbinList = fbinDao.getBinSids(dirList);
            ArrayList<String> binList = new ArrayList<String>();
            binList.addAll(cbinList);
            binList.addAll(dbinList);
            binList.addAll(fbinList);

            //キャビネット関連情報を削除
            cabDao.delete(cabSid);
            cabAdmDao.delete(cabSid);
            cabAccDao.delete(cabSid);
            cabBinDao.delete(cabSid);

            //ディレクトリ関連情報を削除
            daConfdao.deleteSubDirectoriesFiles(dirDao.getRootDirectory(cabSid).getFdrSid(), false);
            dirDao.deleteBelongCabinet(cabSid);
            dbinDao.deleteDir(dirList);
            fbinDao.deleteDir(dirList);
            dirBinDao.deleteDir(dirList);
            callDao.deleteDir(dirList);
            callDateDao.deleteDir(dirList);
            shortDao.deleteDir(dirList);

            Fil030Biz biz = new Fil030Biz(con, getRequestModel(req));
            //アプリケーションルートパス
            String appRootPath = getAppRootPath();

            //全文検索
            FileFileTextDao textDao = new FileFileTextDao(con);
            textDao.deleteDir(dirList);

            //バイナリー情報を削除
            biz.doDeleteFile(binList, appRootPath, con);
            //
            commitFlg = true;
        } catch (Exception e) {
            log__.error("キャビネット削除に失敗しました" + e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        GsMessage gsMsg = new GsMessage();
        String textDel = gsMsg.getMessage(req, "cmn.delete");

        //ログ出力処理
        FilCommonBiz filBiz = new FilCommonBiz(con, getRequestModel(req));
        filBiz.outPutLog(textDel,
                GSConstLog.LEVEL_TRACE, "[cabinet]" + form.getFil030CabinetName(), map.getType());

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getFil030pluginId(), getRequestModel(req));

        //テンポラリディレクトリの削除
        IOTools.deleteDir(tempDir);

        forward = __setCompDsp(map, req, form);

        return forward;
    }

    /**
     * <br>編集権限が無い場合のエラー画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doNonePowerError(ActionMapping map, Fil030Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        GsMessage gsMsg = new GsMessage();
        String textTourokuHenkou = gsMsg.getMessage(req, "fil.91");
        String textCabinetSakusei = gsMsg.getMessage(req, "fil.64");

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("fil010");
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("error.edit.power.user",
                textCabinetSakusei, textTourokuHenkou));

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
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
     * @return ActionForward フォワード
     */
    private ActionForward __setCompDsp(ActionMapping map,
                                        HttpServletRequest req,
                                        Fil030Form form) {

        GsMessage gsMsg = new GsMessage();
        String textCabinet = gsMsg.getMessage(req, "fil.23");

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        cmn999Form.addHiddenParam("filSearchWd", form.getFilSearchWd());
        cmn999Form.addHiddenParam("fil010SelectDelLink", form.getFil010SelectDelLink());

        String fowardStr = "fil010";
        if (!form.getBackDsp().equals(GSConstFile.MOVE_TO_FIL010)
                && !form.getBackDsp().equals(GSConstFile.MOVE_TO_FIL020)) {
            cmn999Form.addHiddenParam("fil010SelectCabinet", form.getFil010SelectCabinet());
            cmn999Form.addHiddenParam("fil010SelectDirSid", form.getFil010SelectDirSid());
            cmn999Form.addHiddenParam("backDsp", form.getBackDsp());
            cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
            cmn999Form.addHiddenParam("fil040SelectDel", form.getFil040SelectDel());
            cmn999Form.addHiddenParam("fil010SelectDelLink", form.getFil010SelectDelLink());
            fowardStr = "fil220";
        }

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward(fowardStr);
        cmn999Form.setUrlOK(forwardOk.getPath());

        //メッセージ
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object", textCabinet));

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] tempディレクトリの画像を読み込む
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con Connection
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doGetImageFile(ActionMapping map,
                                            Fil030Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        //テンポラリディレクトリパス
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getFil030pluginId(), getRequestModel(req))
                + GSConstFile.TEMP_DIR_MARK + "/";
        String fullPath = IOTools.replaceFileSep(
                tempDir + imageFileSaveName__ + GSConstCommon.ENDSTR_SAVEFILE);
        TempFileUtil.downloadInline(req, res, fullPath, imageFileName__, Encoding.UTF_8);

        return null;
    }

    @Override
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        Fil030Form thisForm = (Fil030Form) form;
        if (thisForm.getCmnMode().equals(GSConstFile.CMN_MODE_MLT)) {
            return false;
        }
        return super.canNotAdminAccess(req, form);
    }
}