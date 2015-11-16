package jp.groupsession.v2.sml.sml020;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.json.JSONObject;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn120.Cmn120Form;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.WmlDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.AbstractSmlAction;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmlAccountDao;
import jp.groupsession.v2.sml.model.SmlAccountModel;
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
 * <br>[機  能] ショートメール作成画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml020Action extends AbstractSmlAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml020Action.class);

    /** ユーザ選択モード ユーザ名クリック */
    public static final int SELECT_USR_MODE_USRNAME = 1;
    /** ユーザ選択モード 宛先追加クリック */
    public static final int SELECT_USR_MODE_ATESAKI = 2;
    /** ユーザ選択モード CC追加クリック */
    public static final int SELECT_USR_MODE_CC = 3;
    /** ユーザ選択モード BCC追加クリック */
    public static final int SELECT_USR_MODE_BCC = 4;

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
     * @see jp.groupsession.v2.sml.AbstractSmlAction
     * @see #executeAction(org.apache.struts.action.ActionMapping,
     *                      org.apache.struts.action.ActionForm,
     *                      javax.servlet.http.HttpServletRequest,
     *                      javax.servlet.http.HttpServletResponse,
     *                      java.sql.Connection)
     */
    public ActionForward executeSmail(ActionMapping map,
                                       ActionForm form,
                                       HttpServletRequest req,
                                       HttpServletResponse res,
                                       Connection con)
        throws Exception {

        log__.debug("START_SML020");

        ActionForward forward = null;
        Sml020Form smlform = (Sml020Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        //草稿保存時且つプロセスモードが４（草稿編集からの草稿保存）以外
        if (!cmd.equals("sokoData") || smlform.getSml020ProcMode()
                .equals(GSConstSmail.MSG_CREATE_MODE_SOKO)) {
            //指定されたメールを閲覧可能か判定
            if (!cmd.equals("sendCheck") && smlform.getSml010SelectedSid() > 0) {
                con.setAutoCommit(true);
                try {
                    SmlCommonBiz smlBiz = new SmlCommonBiz(con, getRequestModel(req));
                    if (!smlBiz.isViewSmail(con, smlform.getSmlViewAccount(),
                            smlform.getSml010SelectedSid())) {
                        return getAuthErrorPage(map, req);
                    }
                } finally {
                    con.setAutoCommit(false);
                }
            }
        }

        //送信ボタン押下
        if (cmd.equals("sendkn")) {
            log__.debug("送信ボタン押下");
            forward = __doSend(map, smlform, req, res, con);
        //草稿保存
        } else if (cmd.equals("soko")) {
            log__.debug("草稿保存ボタン押下");
            forward = __doSave(map, smlform, req, res, con);
        //戻るボタン押下
        } else if (cmd.equals("backFromSmailCreate")) {
            log__.debug("戻るボタン押下");
            forward = __doBack(map, smlform, req, res, con);
        //宛先選択ボタン押下
        } else if (cmd.equals("fromselect")) {
            log__.debug("宛先選択ボタン押下");
            forward = __doUserSelect(map, smlform, req, res, con);
        //宛先選択画面からの戻り時
        } else if (cmd.equals("dsp")) {
            log__.debug("宛先選択画面からの戻り");
            smlform.setSml020AllUserReFlg(0);
            __doAtesakiBack(smlform);
            forward = __doRedraw(map, smlform, req, res, con);
        //宛先選択画面からの戻り時
        } else if (cmd.equals("deleteCansel")) {
            log__.debug("削除からの戻り");
            smlform.setSml020AllUserReFlg(0);
            forward = __doRedraw(map, smlform, req, res, con);
        //ひな形適用ボタン押下
        } else if (cmd.equals("hinagataSet")) {
            log__.debug("ひな形適用ボタン押下");
            forward = __doSetHinaData(map, smlform, req, res, con);
        //添付ファイル 削除ボタン押下
        } else if (cmd.equals("delete")) {
            log__.debug("添付ファイル 削除ボタン押下");
            forward = __doDelete(map, smlform, req, res, con);
        //メイン画面からの遷移
        } else if (cmd.equals("smlSend")) {
            log__.debug("メイン画面からの遷移");
            forward = __doInitToMain(map, smlform, req, res, con);
        //削除確認
        } else if (cmd.equals("deleteKn")) {
            log__.debug("削除ボタン押下");
            forward = __doDeleteConfirmation(map, smlform, req, res, con);
        //削除確認画面でOKボタン押下
        } else if (cmd.equals("deleteOk")) {
            log__.debug("削除確認画面でOK押下");
            forward = __doDeleteOk(map, smlform, req, res, con);
        //ユーザ一覧ユーザ名クリック
        } else if (cmd.equals("sml020addUsr")) {
            log__.debug("ユーザ名クリック");
            forward = __doAddUser(map, smlform, req, res, con, SELECT_USR_MODE_USRNAME);
        //ユーザ一覧宛先追加ボタンクリック
        } else if (cmd.equals("sml020addUsrAtesaki")) {
            log__.debug("宛先追加ボタンクリック");
            forward = __doAddUser(map, smlform, req, res, con, SELECT_USR_MODE_ATESAKI);
        //ユーザ一覧CC追加ボタンクリック
        } else if (cmd.equals("sml020addUsrCc")) {
            log__.debug("CC追加ボタンクリック");
            forward = __doAddUser(map, smlform, req, res, con, SELECT_USR_MODE_CC);
        //ユーザ一覧BCC追加ボタンクリック
        } else if (cmd.equals("sml020addUsrBcc")) {
            log__.debug("BCC追加ボタンクリック");
            forward = __doAddUser(map, smlform, req, res, con, SELECT_USR_MODE_BCC);
        //宛先削除リンククリック
        } else if (cmd.equals("deleteAtesaki")) {
            log__.debug("宛先削除リンククリック");
            forward = __doDeleteUser(map, smlform, req, res, con);
        //グループコンボ変更
        } else if (cmd.equals("changeGrp")) {
            log__.debug("グループコンボ変更");
            forward = __doRedraw(map, smlform, req, res, con);

        //添付ファイル 削除ボタン押下
        } else if (cmd.equals("deleteTmpData")) {
            log__.debug("添付ファイル 削除ボタン押下");
            __doDeleteTmpData(map, smlform, req, res, con);
        //テンポラリディレクトリ削除
        } else if (cmd.equals("deleteTmpDirData")) {
            log__.debug("添付ファイル 削除ボタン押下");
            __doDeleteTmpDirData(map, smlform, req, res, con);
        //送信ボタン押下
        } else if (cmd.equals("sendCheck")) {
            log__.debug("送信ボタン押下");
            __doSendDataCheck(map, smlform, req, res, con);
        //草稿保存
        } else if (cmd.equals("sokoData")) {
            log__.debug("草稿保存ボタン押下");
            __doSaveData(map, smlform, req, res, con);
        //削除確認
        } else if (cmd.equals("deleteKnData")) {
            log__.debug("削除ボタン押下");
            __doDeleteConfirmationData(map, smlform, req, res, con);
        //削除確認画面でOKボタン押下
        } else if (cmd.equals("deleteOkData")) {
            log__.debug("削除確認画面でOK押下");
            __doDeleteOkData(map, smlform, req, res, con);
            //返信ボタン押下
        } else if (cmd.equals("hensinData")) {
            log__.debug("返信ボタン押下");
            __setSmsgParamData(map, req, res, con, smlform, GSConstSmail.MSG_CREATE_MODE_HENSIN);
        //全返信ボタン押下
        } else if (cmd.equals("zenhensinData")) {
            log__.debug("全返信ボタン押下");
            smlform.setSml020AllUserReFlg(1);
            __setSmsgParamData(map, req, res, con, smlform, GSConstSmail.MSG_CREATE_MODE_ZENHENSIN);
        //転送ボタン押下
        } else if (cmd.equals("tensoData")) {
            log__.debug("転送ボタン押下");
            __setSmsgParamData(map, req, res, con, smlform, GSConstSmail.MSG_CREATE_MODE_TENSO);
        //複写して新規作成ボタン押下
        } else if (cmd.equals("copyData")) {
            log__.debug("複写して新規作成ボタン押下");
            __setSmsgParamData(map, req, res, con, smlform, GSConstSmail.MSG_CREATE_MODE_COPY);
        //草稿から作成
        } else if (cmd.equals("getSokoData")) {
            log__.debug("草稿から作成");
            smlform.setSml020ProcMode(GSConstSmail.MSG_CREATE_MODE_SOKO);
            __doInitData(map, smlform, req, res, con);
        //ひな形適用ボタン押下
        } else if (cmd.equals("hinagataSetData")) {
            log__.debug("ひな形適用ボタン押下");
            __doSetHina(map, smlform, req, res, con);

        } else if (cmd.equals("getCalledWebmail")) {
            log__.debug("WEBメール連携");
            __doCalledWebmail(map, smlform, req, res, con);

        } else if (cmd.equals("getNewmail")) {
            log__.debug("新規作成");
            __doNewmail(map, smlform, req, res, con);

        //初期表示
        } else {
            log__.debug("初期表示");

            //全返信ボタン押下時の設定
            if (cmd.equals("zenhensin")) {
                smlform.setSml020AllUserReFlg(1);
            }
            forward = __doInit(map, smlform, req, res, con);
        }
        log__.debug("END_SML020");
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
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Sml020Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {

        ActionForward forward = null;
        con.setAutoCommit(true);
        //リクエストパラメータに送信先がある場合、フォームにセット
        Object obj = req.getAttribute("cmn120userSid");
        if (obj != null) {
            form.setSml020userSid((String[]) obj);
        }

        RequestModel reqMdl = getRequestModel(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getSml020pluginId(), reqMdl);

        //テンポラリディレクトリのファイル削除を行う
        Sml020Biz biz = new Sml020Biz(reqMdl);
        biz.doDeleteFile(tempDir);

        //アプリケーションのルートパス
        String appRootPath = getAppRootPath();
        String procMode = form.getSml020ProcMode();

        //返信、全返信、転送モード
        Sml020ParamModel paramMdl = new Sml020ParamModel();
        paramMdl.setParam(form);
        if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_TENSO)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_COPY)) {
            biz.setMailData(paramMdl, reqMdl, con, appRootPath, tempDir,
                    GroupSession.getResourceManager().getDomain(req));
        //草稿から作成
        } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_SOKO)) {
            biz.setMailDataFromSitagaki(paramMdl, reqMdl, con, appRootPath, tempDir,
                    GroupSession.getResourceManager().getDomain(req));
        }
        paramMdl.setFormData(form);

        biz.setAutoDest(paramMdl, reqMdl, con);

        forward = __doRedraw(map, form, req, res, con);
        con.setAutoCommit(false);

        return forward;
    }

    /**
     * <br>[機  能] 再描画処理
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
    private ActionForward __doRedraw(ActionMapping map,
                                      Sml020Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException {

        con.setAutoCommit(true);
        RequestModel reqMdl = getRequestModel(req);
        Sml020Biz biz = new Sml020Biz(reqMdl);
        //トランザクショントークン設定
        this.saveToken(req);

        Sml020ParamModel paramMdl = new Sml020ParamModel();
        paramMdl.setParam(form);

        //ひな形リスト設定
        biz.setHinagataList(paramMdl, reqMdl, con);

        //宛先名称一覧を設定
        biz.setAtesaki(paramMdl, reqMdl, con);
        //CC名称一覧を設定
        biz.setAtesakiCc(paramMdl, reqMdl, con);
        //BCC名称一覧を設定
        biz.setAtesakiBcc(paramMdl, con);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), paramMdl.getSml020pluginId(), reqMdl);

        //添付ファイル情報セット
        biz.setTempFiles(paramMdl, tempDir, con);

        if (form.getSml020webmail() == 1) {
            //ユーザ一覧を設定
            biz.setLeftMenu(paramMdl, con, reqMdl);
        }

        paramMdl.setFormData(form);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 宛先選択画面から戻ってきたときの処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param form フォーム
     */
    private void __doAtesakiBack(Sml020Form form) {

        if (form.getSml020SendKbn() == GSConstSmail.SML_SEND_KBN_ATESAKI) {
            form.setSml020userSid(form.getCmn120userSid());
        } else if (form.getSml020SendKbn() == GSConstSmail.SML_SEND_KBN_CC) {
            form.setSml020userSidCc(form.getCmn120userSid());
        } else if (form.getSml020SendKbn() == GSConstSmail.SML_SEND_KBN_BCC) {
            form.setSml020userSidBcc(form.getCmn120userSid());
        }

    }

    /**
     * <br>[機  能] 再描画処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doSend(ActionMapping map,
                                    Sml020Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws Exception {

        con.setAutoCommit(true);

        try {

            //入力チェック
            ActionErrors errors =
                form.validateCheck020(Sml020Form.VALIDATE_MODE_SOUSIN, con, getRequestModel(req));
            if (!errors.isEmpty()) {
                addErrors(req, errors);
                return __doRedraw(map, form, req, res, con);
            }
            //トランザクショントークン設定
            saveToken(req);
            //確認画面設定
            return __setKakuninDsp(map, req, form, 1);

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        }
    }

    /**
     * <br>[機  能] 草稿保存ボタン押下時処理
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
    private ActionForward __doSave(ActionMapping map,
                                    Sml020Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws Exception {

        con.setAutoCommit(false);
        boolean commitFlg = false;
        RequestModel reqMdl = getRequestModel(req);

        try {

            if (!isTokenValid(req, true)) {
                log__.info("２重投稿");
                return getSubmitErrorPage(map, req);
            }

            //入力チェック
            ActionErrors errors =
                form.validateCheck020(Sml020Form.VALIDATE_MODE_SAVE, con, reqMdl);
            if (!errors.isEmpty()) {
                addErrors(req, errors);
                return __doRedraw(map, form, req, res, con);
            }

            //テンポラリディレクトリパスを取得
            CommonBiz cmnBiz = new CommonBiz();
            String tempDir = cmnBiz.getTempDir(
                    getTempPath(req), form.getSml020pluginId(), reqMdl);

            //アプリケーションのルートパス
            String appRootPath = getAppRootPath();

            //DBに登録
            Sml020Biz biz = new Sml020Biz(reqMdl);
            MlCountMtController cntCon = getCountMtController(req);
            Sml020ParamModel paramMdl = new Sml020ParamModel();
            paramMdl.setParam(form);
            biz.insertSitagakiData(paramMdl, reqMdl, con, cntCon, appRootPath, tempDir);
            paramMdl.setFormData(form);
            commitFlg = true;

            GsMessage gsMsg = new GsMessage();
            String msg = gsMsg.getMessage(req, "cmn.entry");
            String msgHozon = gsMsg.getMessage(req, "sml.sml020.07");

            //ログ出力処理
            //ログ出力処理
            SmlAccountModel sacMdl = new SmlAccountModel();
            SmlAccountDao sacDao = new SmlAccountDao(con);
            sacMdl = sacDao.select(form.getSmlViewAccount());

            SmlCommonBiz smlBiz = new SmlCommonBiz(con, reqMdl);
            smlBiz.outPutLog(map, reqMdl,
                    msg, GSConstLog.LEVEL_TRACE, "アカウント:" + sacMdl.getSacName()
                                                 + "\n" + msgHozon);

            //完了画面設定
            return __setCompDsp(map, req, form, 2);

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
    }

    /**
     * <br>[機  能] お知らせ先選択クリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doUserSelect(ActionMapping map,
                                          Sml020Form form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con) {

        Cmn120Form cmn120Form = new Cmn120Form();

        //「戻る」ボタンURLセット
        ActionForward forward = map.findForward("redraw");
        cmn120Form.setCmn120BackUrl(forward.getPath() + "?" + GSConst.P_CMD + "=dsp");

        GsMessage gsMsg = new GsMessage();
        String from = gsMsg.getMessage(req, "cmn.from");

        //機能名称セット
        cmn120Form.setCmn120FunctionName(from);

        //フォーム識別子
        cmn120Form.setCmn120FormKey("sml020Form");
        cmn120Form.setCmn120userSid(null);

        req.setAttribute("cmn120Form", cmn120Form);
        req.setAttribute("sml020Form", form);
        return map.findForward("selectuser");
    }

    /**
     * <br>[機  能] ひな形を適用する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @return ActionForward
     */
    private ActionForward __doSetHinaData(ActionMapping map,
                                           Sml020Form form,
                                           HttpServletRequest req,
                                           HttpServletResponse res,
                                           Connection con)
        throws SQLException, IOToolsException {

        con.setAutoCommit(true);
        Sml020Biz biz = new Sml020Biz(getRequestModel(req));

        //ひな形が選択されている場合
        if (form.getSml020SelectHinaId() > 0) {
            //ひな形データ設定
            Sml020ParamModel paramMdl = new Sml020ParamModel();
            paramMdl.setParam(form);
            biz.setHinagataData(paramMdl, con);
            paramMdl.setFormData(form);
            //ひな形SIDクリア
            form.setSml020SelectHinaId(0);
        }

        return __doRedraw(map, form, req, res, con);
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
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @return ActionForward
     */
    private ActionForward __doDelete(ActionMapping map,
                                      Sml020Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getSml020pluginId(), getRequestModel(req));

        //選択された添付ファイルを削除する
        cmnBiz.deleteFile(form.getSml020selectFiles(), tempDir);

        return __doRedraw(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 戻るボタン押下時処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws IOToolsException ファイルアクセス時例外
     */
    private ActionForward __doBack(ActionMapping map,
                                    Sml020Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws IOToolsException {

        ActionForward forward = null;
        String procMode = form.getSml020ProcMode();

        //検索画面を経由している場合
        if (GSConstSmail.SEARCH_BACK_ON.equals(form.getSml090BackParm())) {
            if  (procMode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)
                    || procMode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)
                    || procMode.equals(GSConstSmail.MSG_CREATE_MODE_TENSO)
                    || procMode.equals(GSConstSmail.MSG_CREATE_MODE_COPY)) {
                    return map.findForward("backToDetail");
            }
            return map.findForward("backToSearch");
        }

        //一覧画面からの遷移(新規作成、草稿から作成時)
        if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_NEW)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_SOKO)) {
            forward = map.findForward("backToMsgList");
        //内容確認画面からの遷移(返信、全返信、転送時)
        } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_TENSO)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_COPY)) {
            forward = map.findForward("backToDetail");
        //スケジュール(日間)からの遷移
        } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_SC_NIKKAN)) {
            forward = map.findForward("backToNikkan");
        //スケジュール(週間)からの遷移
        } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_SC_SYUKAN)) {
            forward = map.findForward("backToSyukan");
        //日報(日間)からの遷移
        } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_NTP_NIKKAN)) {
            forward = map.findForward("backToNtpNikkan");
        //日報(週間)からの遷移
        } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_NTP_SYUKAN)) {
            forward = map.findForward("backToNtpSyukan");
        //在席管理からの遷移
        } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_ZAISEKI)) {
            forward = map.findForward("backToZaiseki");
        //メインからの遷移
        } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_MAIN)) {
            forward = map.findForward("backToTop");
        }
        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getSml020pluginId(), getRequestModel(req));

        //テンポラリディレクトリのファイル削除を行う
        Sml020Biz biz = new Sml020Biz(getRequestModel(req));
        biz.doDeleteFile(tempDir);

        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理(メイン画面)
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
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doInitToMain(ActionMapping map,
                                    Sml020Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {

        //メッセージ作成モードを設定
        form.setSml020ProcMode(GSConstSmail.MSG_CREATE_MODE_MAIN);
        //トランザクショントークン設定
        this.saveToken(req);
        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 確認画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @param mode 1=送信完了  2=草稿保存完了
     * @return ActionForward フォワード
     */
    private ActionForward __setKakuninDsp(ActionMapping map,
                                        HttpServletRequest req,
                                        Sml020Form form,
                                        int mode) {

        return map.findForward("sml020kakunin");
    }

    /**
     * <br>[機  能] 完了画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @param mode 1=送信完了  2=草稿保存完了
     * @return ActionForward フォワード
     */
    private ActionForward __setCompDsp(ActionMapping map,
                                        HttpServletRequest req,
                                        Sml020Form form,
                                        int mode) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);

        String procMode = form.getSml020ProcMode();
        String fowardStr = "";

        //TOP画面より
        if (form.getSml010ProcMode().equals(GSConstSmail.TAB_DSP_MODE_JUSIN_FROM_TOP)) {
            cmn999Form.addHiddenParam("schWeekDate", form.getSchWeekDate());
            cmn999Form.addHiddenParam("schDailyDate", form.getSchDailyDate());
            fowardStr = "backToTop";
        } else {
            //新規、返信、全返信、転送、草稿、複写新規
            if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_NEW)
                    || procMode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)
                    || procMode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)
                    || procMode.equals(GSConstSmail.MSG_CREATE_MODE_TENSO)
                    || procMode.equals(GSConstSmail.MSG_CREATE_MODE_SOKO)
                    || procMode.equals(GSConstSmail.MSG_CREATE_MODE_COPY)) {

                //画面パラメータをセット
                cmn999Form.addHiddenParam("sml010ProcMode", form.getSml010ProcMode());
                cmn999Form.addHiddenParam("sml010Sort_key", form.getSml010Sort_key());
                cmn999Form.addHiddenParam("sml010Order_key", form.getSml010Order_key());
                cmn999Form.addHiddenParam("sml010PageNum", form.getSml010PageNum());
                cmn999Form.addHiddenParam("sml010DelSid", form.getSml010DelSid());
                cmn999Form.addHiddenParam("sml020ProcMode", form.getSml020ProcMode());
                cmn999Form.addHiddenParam("sml030SelectedRowNum", form.getSml030SelectedRowNum());
                fowardStr = "backToMsgList";
            //スケジュールから(日間)
            } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_SC_NIKKAN)) {
                //画面パラメータをセット
                cmn999Form.addHiddenParam("sch010DspDate", form.getSch010DspDate());
                cmn999Form.addHiddenParam("sch010DspGpSid", form.getSch010DspGpSid());
                cmn999Form.addHiddenParam("sch030FromHour", form.getSch030FromHour());
                fowardStr = "backToNikkan";
            //スケジュールから(週間)
            } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_SC_SYUKAN)) {
                //画面パラメータをセット
                cmn999Form.addHiddenParam("sch010DspDate", form.getSch010DspDate());
                cmn999Form.addHiddenParam("sch010DspGpSid", form.getSch010DspGpSid());
                cmn999Form.addHiddenParam("sch030FromHour", form.getSch030FromHour());
                fowardStr = "backToSyukan";
            //日報から(日間)
            } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_NTP_NIKKAN)) {
                //画面パラメータをセット
                cmn999Form.addHiddenParam("ntp010DspDate", form.getNtp010DspDate());
                cmn999Form.addHiddenParam("ntp010DspGpSid", form.getNtp010DspGpSid());
                cmn999Form.addHiddenParam("ntp030FromHour", form.getNtp030FromHour());
                fowardStr = "backToNtpNikkan";
            //日報から(週間)
            } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_NTP_SYUKAN)) {
                //画面パラメータをセット
                cmn999Form.addHiddenParam("ntp010DspDate", form.getNtp010DspDate());
                cmn999Form.addHiddenParam("ntp010DspGpSid", form.getNtp010DspGpSid());
                cmn999Form.addHiddenParam("ntp030FromHour", form.getNtp030FromHour());
                fowardStr = "backToNtpSyukan";
            //在席管理から
            } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_ZAISEKI)) {
                //在席管理からポップアップで開かれる場合は下記のコメントを外す
                //cmn999Form.setType_popup(Cmn999Form.POPUP_TRUE);
                fowardStr = "backToZaiseki";
            //メインから
            } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_MAIN)) {
                fowardStr = "backToTop";
            }

            //検索画面から
            if (GSConstSmail.SEARCH_BACK_ON.equals(form.getSml090BackParm())) {
                cmn999Form.addHiddenParam("sml090ProcModeSave", form.getSml090ProcModeSave());
                cmn999Form.addHiddenParam("sml090BackParm", form.getSml090BackParm());
                cmn999Form.addHiddenParam("searchFlg", form.getSearchFlg());
                cmn999Form.addHiddenParam("sml090page1", form.getSml090page1());
                cmn999Form.addHiddenParam("sml090page2", form.getSml090page2());
                cmn999Form.addHiddenParam("sml090SltGroup", form.getSml090SltGroup());
                cmn999Form.addHiddenParam("sml090SltUser", form.getSml090SltUser());
                cmn999Form.addHiddenParam("sml090MailSyubetsu", form.getSml090MailSyubetsu());
                cmn999Form.addHiddenParam("sml090MailMark", form.getSml090MailMark());
                cmn999Form.addHiddenParam("sml090KeyWord", form.getSml090KeyWord());
                cmn999Form.addHiddenParam("sml090KeyWordkbn", form.getSml090KeyWordkbn());
                cmn999Form.addHiddenParam("sml090SearchTarget", form.getSml090SearchTarget());
                cmn999Form.addHiddenParam("sml090SearchSortKey1", form.getSml090SearchSortKey1());
                cmn999Form.addHiddenParam("sml090SearchOrderKey1", form.getSml090SearchOrderKey1());
                cmn999Form.addHiddenParam("sml090SearchSortKey2", form.getSml090SearchSortKey2());
                cmn999Form.addHiddenParam("sml090SearchOrderKey2", form.getSml090SearchOrderKey2());
//                cmn999Form.addHiddenParam("cmn120userSid", form.getCmn120userSid());
                cmn999Form.addHiddenParam("cmn120userSid", form.getSml090userSid());
                cmn999Form.addHiddenParam("cmn120SvuserSid", form.getCmn120SvuserSid());
                cmn999Form.addHiddenParam("sml090SvSltGroup", form.getSml090SvSltGroup());
                cmn999Form.addHiddenParam("sml090SvSltUser", form.getSml090SvSltUser());
                cmn999Form.addHiddenParam("sml090SvAtesaki", form.getSml090SvAtesaki());
                cmn999Form.addHiddenParam("sml090SvMailSyubetsu", form.getSml090SvMailSyubetsu());
                cmn999Form.addHiddenParam("sml090SvMailMark", form.getSml090SvMailMark());
                cmn999Form.addHiddenParam("sml090SvKeyWord", form.getSml090SvKeyWord());
                cmn999Form.addHiddenParam("sml090SvKeyWordkbn", form.getSml090SvKeyWordkbn());
                cmn999Form.addHiddenParam("sml090SvSearchTarget", form.getSml090SvSearchTarget());
                cmn999Form.addHiddenParam(
                        "sml090SvSearchOrderKey1", form.getSml090SvSearchOrderKey1());
                cmn999Form.addHiddenParam(
                        "sml090SvSearchSortKey1", form.getSml090SvSearchSortKey1());
                cmn999Form.addHiddenParam(
                        "sml090SvSearchOrderKey2", form.getSml090SvSearchOrderKey2());
                cmn999Form.addHiddenParam(
                        "sml090SvSearchSortKey2", form.getSml090SvSearchSortKey2());

                fowardStr = "backToSearch";
            }
        }

        if (form.getSml020webmail() == 1) {
            //WEBメールからの呼び出し
            cmn999Form.setWtarget(Cmn999Form.WTARGET_SELF);
            cmn999Form.setType_popup(Cmn999Form.POPUP_TRUE);
        } else {
            cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

            //OKボタンクリック時遷移先
            ActionForward forwardOk = map.findForward(fowardStr);
            cmn999Form.setUrlOK(forwardOk.getPath());
        }

        //メッセージ
        MessageResources msgRes = getResources(req);
        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "cmn.message");
        String soukou = gsMsg.getMessage(req, "cmn.draft");
        //送信完了
        if (mode == 1) {
            cmn999Form.setMessage(
                    msgRes.getMessage("sousin.kanryo.object", msg));
        //草稿保存完了
        } else if (mode == 2) {
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object", soukou));
        }

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 削除確認画面表示処理
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
     */
    private ActionForward __doDeleteConfirmation(ActionMapping map,
                                                  Sml020Form form,
                                                  HttpServletRequest req,
                                                  HttpServletResponse res,
                                                  Connection con)
        throws SQLException {

        con.setAutoCommit(true);
        //削除するメッセージの件名を取得する
        Sml020ParamModel paramMdl = new Sml020ParamModel();
        paramMdl.setParam(form);
        Sml020Biz biz = new Sml020Biz(getRequestModel(req));
        String mailName = biz.getMailTitle(paramMdl, con);
        paramMdl.setFormData(form);

        return __setDeleteDsp(map, req, form, mailName);
    }

    /**
     * <br>[機  能] 削除確認画面でOKボタン押下
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
     */
    private ActionForward __doDeleteOk(ActionMapping map,
                                        Sml020Form form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con)
        throws SQLException {

        boolean commitFlg = false;
        con.setAutoCommit(false);

        RequestModel reqMdl = getRequestModel(req);
        try {

            //削除処理実行
            Sml020ParamModel paramMdl = new Sml020ParamModel();
            paramMdl.setParam(form);
            Sml020Biz biz = new Sml020Biz(reqMdl);
            biz.deleteMessage(paramMdl, reqMdl, con);
            paramMdl.setFormData(form);

            GsMessage gsMsg = new GsMessage();
            String delete = gsMsg.getMessage(req, "cmn.delete");

            //ログ出力処理
            SmlAccountModel sacMdl = new SmlAccountModel();
            SmlAccountDao sacDao = new SmlAccountDao(con);
            sacMdl = sacDao.select(form.getSmlViewAccount());

            SmlCommonBiz smlBiz = new SmlCommonBiz(con, reqMdl);
            smlBiz.outPutLog(map, reqMdl,
                    delete, GSConstLog.LEVEL_TRACE, "アカウント:" + sacMdl.getSacName()
                                                 + "\n");

            commitFlg = true;

            //完了画面設定
            return __setCompDsp(map, req, form);

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
    }

    /**
     * <br>[機  能] 削除確認画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @param mailName 削除メール件名
     * @return ActionForward フォワード
     */
    private ActionForward __setDeleteDsp(ActionMapping map,
                                          HttpServletRequest req,
                                          Sml020Form form,
                                          String mailName) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("redraw");
        cmn999Form.setUrlOK(forwardOk.getPath() + "?" + GSConst.P_CMD + "=deleteOk");

        //キャンセルボタンクリック時遷移先
        ActionForward forwardCancel = map.findForward("redraw");
        cmn999Form.setUrlCancel(forwardCancel.getPath());
        cmn999Form.setUrlCancel(forwardCancel.getPath() + "?" + GSConst.P_CMD + "=deleteCansel");

        //メッセージ
        MessageResources msgRes = getResources(req);

        GsMessage gsMsg = new GsMessage();
        String soukou = gsMsg.getMessage(req, "sml.101");
        String msg = gsMsg.getMessage(req, "cmn.message");
        String msg2 = gsMsg.getMessage(req, "wml.231");

        String mailKbnName = soukou + " ";
        String msgId = "move.gomibako.mail";

        cmn999Form.setMessage(
                msgRes.getMessage(
                        msgId,
                        msg,
                        msg2
                        + mailKbnName
                        + StringUtilHtml.transToHTmlPlusAmparsant(mailName)));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("sml010ProcMode", form.getSml010ProcMode());
        cmn999Form.addHiddenParam("sml010Sort_key", form.getSml010Sort_key());
        cmn999Form.addHiddenParam("sml010Order_key", form.getSml010Order_key());
        cmn999Form.addHiddenParam("sml010PageNum", form.getSml010PageNum());
        cmn999Form.addHiddenParam("sml010SelectedSid", form.getSml010SelectedSid());
        cmn999Form.addHiddenParam("sml010DelSid", form.getSml010DelSid());
        cmn999Form.addHiddenParam("sml010SelectedMailKbn", form.getSml010SelectedMailKbn());
        cmn999Form.addHiddenParam("schWeekDate", form.getSchWeekDate());
        cmn999Form.addHiddenParam("schDailyDate", form.getSchDailyDate());


        cmn999Form.addHiddenParam("sml020ProcMode", form.getSml020ProcMode());
        cmn999Form.addHiddenParam("sml020userSid", form.getSml020userSid());
        cmn999Form.addHiddenParam("sml020userSidCc", form.getSml020userSidCc());
        cmn999Form.addHiddenParam("sml020userSidBcc", form.getSml020userSidBcc());
        cmn999Form.addHiddenParam("sml020Title", form.getSml020Title());
        cmn999Form.addHiddenParam("sml020Mark", form.getSml020Mark());
        cmn999Form.addHiddenParam("sml020Body", form.getSml020Body());
        cmn999Form.addHiddenParam("sml020SendKbn", form.getSml020SendKbn());

        //検索から遷移時のパラメータセット
        __setSearchHiddenParm(cmn999Form, form);

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 削除完了画面
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
                                        Sml020Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = null;

        //検索画面から遷移時
        if (GSConstSmail.SEARCH_BACK_ON.equals(form.getSml090BackParm())) {
            forwardOk = map.findForward("backToSearch");

        } else {
            //草稿一覧
            forwardOk = map.findForward("backToMsgList");
        }

        cmn999Form.setUrlOK(forwardOk.getPath());

        //メッセージ
        MessageResources msgRes = getResources(req);
        String msgId = "move.gomibako.object";

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "cmn.message");

        cmn999Form.setMessage(msgRes.getMessage(msgId, msg));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("sml010ProcMode", form.getSml010ProcMode());
        cmn999Form.addHiddenParam("sml010Sort_key", form.getSml010Sort_key());
        cmn999Form.addHiddenParam("sml010Order_key", form.getSml010Order_key());
        cmn999Form.addHiddenParam("sml010PageNum", form.getSml010PageNum());
        cmn999Form.addHiddenParam("schWeekDate", form.getSchWeekDate());
        cmn999Form.addHiddenParam("schDailyDate", form.getSchDailyDate());

        //検索から遷移時のパラメータセット
        __setSearchHiddenParm(cmn999Form, form);

        int selectedSid = form.getSml010SelectedSid();
        String[] delList = form.getSml010DelSid();
        String[] newDelList = null;
        ArrayList<String> delArray = new ArrayList<String>();

        //選択チェックhiddenリスト再生成(削除したデータにチェックされていた場合に外すため)
        if (delList != null && delList.length > 0) {
            for (int i = 0; i < delList.length; i++) {
                 if (selectedSid != Integer.parseInt(delList[i].substring(1))) {
                    delArray.add(delList[i]);
                }
            }
            if (delArray.isEmpty()) {
                newDelList = new String[0];
            } else {
                newDelList =
                    (String[]) delArray.toArray(new String[delArray.size()]);
            }
        }

        cmn999Form.addHiddenParam("sml010DelSid", newDelList);

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 検索画面から遷移時のパラメータセット
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn999Form Cmn999From
     * @param form Sml020Form
     */
    private void __setSearchHiddenParm(Cmn999Form cmn999Form, Sml020Form form) {
        //検索画面から遷移の時
        if (GSConstSmail.SEARCH_BACK_ON.equals(form.getSml090BackParm())) {
            cmn999Form.addHiddenParam("sml090ProcModeSave", form.getSml090ProcModeSave());
            cmn999Form.addHiddenParam("sml090BackParm", form.getSml090BackParm());
            cmn999Form.addHiddenParam("searchFlg", form.getSearchFlg());
            cmn999Form.addHiddenParam("sml090page1", form.getSml090page1());
            cmn999Form.addHiddenParam("sml090page2", form.getSml090page2());
            cmn999Form.addHiddenParam("sml090SltGroup", form.getSml090SltGroup());
            cmn999Form.addHiddenParam("sml090SltUser", form.getSml090SltUser());
            cmn999Form.addHiddenParam("sml090MailSyubetsu", form.getSml090MailSyubetsu());
            cmn999Form.addHiddenParam("sml090MailMark", form.getSml090MailMark());
            cmn999Form.addHiddenParam("sml090KeyWord", form.getSml090KeyWord());
            cmn999Form.addHiddenParam("sml090KeyWordkbn", form.getSml090KeyWordkbn());
            cmn999Form.addHiddenParam("sml090SearchTarget", form.getSml090SearchTarget());
            cmn999Form.addHiddenParam("sml090SearchSortKey1", form.getSml090SearchSortKey1());
            cmn999Form.addHiddenParam("sml090SearchOrderKey1", form.getSml090SearchOrderKey1());
            cmn999Form.addHiddenParam("sml090SearchSortKey2", form.getSml090SearchSortKey2());
            cmn999Form.addHiddenParam("sml090SearchOrderKey2", form.getSml090SearchOrderKey2());
            cmn999Form.addHiddenParam("sml090DelSid", form.getSml090DelSid());
            cmn999Form.addHiddenParam("sml090SelectedDelSid", form.getSml090SelectedDelSid());
//            cmn999Form.addHiddenParam("cmn120userSid", form.getCmn120userSid());
            //宛先start
            cmn999Form.addHiddenParam("sml090userSid", form.getSml090userSid());
            cmn999Form.addHiddenParam("cmn120userSid", form.getSml090userSid());
            cmn999Form.addHiddenParam("sml020userSid", form.getSml020userSid());
            cmn999Form.addHiddenParam("sml020userSidCc", form.getSml020userSidCc());
            cmn999Form.addHiddenParam("sml020userSidBcc", form.getSml020userSidBcc());
            //宛先end
            cmn999Form.addHiddenParam("cmn120SvuserSid", form.getCmn120SvuserSid());
            cmn999Form.addHiddenParam("sml090SvSltGroup", form.getSml090SvSltGroup());
            cmn999Form.addHiddenParam("sml090SvSltUser", form.getSml090SvSltUser());
            cmn999Form.addHiddenParam("sml090SvAtesaki", form.getSml090SvAtesaki());
            cmn999Form.addHiddenParam("sml090SvMailSyubetsu", form.getSml090SvMailSyubetsu());
            cmn999Form.addHiddenParam("sml090SvMailMark", form.getSml090SvMailMark());
            cmn999Form.addHiddenParam("sml090SvKeyWord", form.getSml090SvKeyWord());
            cmn999Form.addHiddenParam("sml090SvKeyWordkbn", form.getSml090SvKeyWordkbn());
            cmn999Form.addHiddenParam("sml090SvSearchTarget", form.getSml090SvSearchTarget());
            cmn999Form.addHiddenParam(
                    "sml090SvSearchOrderKey1", form.getSml090SvSearchOrderKey1());
            cmn999Form.addHiddenParam(
                    "sml090SvSearchSortKey1", form.getSml090SvSearchSortKey1());
            cmn999Form.addHiddenParam(
                    "sml090SvSearchOrderKey2", form.getSml090SvSearchOrderKey2());
            cmn999Form.addHiddenParam(
                    "sml090SvSearchSortKey2", form.getSml090SvSearchSortKey2());
        }

    }

    /**
     * <br>[機  能] 宛先CCBCCユーザ追加
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param mode ユーザ追加モード
     * @return ActionForward フォワード
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル例外
     */
    private ActionForward __doAddUser(ActionMapping map,
            Sml020Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con,
                                      int mode)
        throws SQLException, IOToolsException {

        Sml020Biz biz = new Sml020Biz(getRequestModel(req));
        //ユーザを追加する。
        Sml020ParamModel paramMdl = new Sml020ParamModel();
        paramMdl.setParam(form);
        biz.addUserAtesaki(paramMdl, mode);
        paramMdl.setFormData(form);

        return __doRedraw(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 宛先CCBCCユーザ追加
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
     * @throws IOToolsException ファイル例外
     */
    private ActionForward __doDeleteUser(ActionMapping map,
            Sml020Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con)
        throws SQLException, IOToolsException {

        Sml020ParamModel paramMdl = new Sml020ParamModel();
        paramMdl.setParam(form);

        //ユーザを削除する。
        Sml020Biz biz = new Sml020Biz(getRequestModel(req));
        biz.deleteUserAtesaki(paramMdl);
        paramMdl.setFormData(form);

        return __doRedraw(map, form, req, res, con);
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
     * @throws Exception 実行時例外
     */
    private void __doInitData(ActionMapping map,
                                    Sml020Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws Exception {

        con.setAutoCommit(true);
        //リクエストパラメータに送信先がある場合、フォームにセット
        Object obj = req.getAttribute("cmn120userSid");
        if (obj != null) {
            form.setSml020userSid((String[]) obj);
        }

        RequestModel reqMdl = getRequestModel(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getSml020pluginId(), reqMdl);

        //テンポラリディレクトリのファイル削除を行う
        Sml020Biz biz = new Sml020Biz(reqMdl);
        biz.doDeleteFile(tempDir);

        //アプリケーションのルートパス
        String appRootPath = getAppRootPath();
        String procMode = form.getSml020ProcMode();

        //返信、全返信、転送モード
        Sml020ParamModel paramMdl = new Sml020ParamModel();
        paramMdl.setParam(form);
        if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_HENSIN)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_ZENHENSIN)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_TENSO)
            || procMode.equals(GSConstSmail.MSG_CREATE_MODE_COPY)) {
            biz.setMailData(paramMdl, reqMdl, con, appRootPath, tempDir,
                    GroupSession.getResourceManager().getDomain(req));
        //草稿から作成
        } else if (procMode.equals(GSConstSmail.MSG_CREATE_MODE_SOKO)) {
            biz.setMailDataFromSitagaki(paramMdl, reqMdl, con, appRootPath, tempDir,
                    GroupSession.getResourceManager().getDomain(req));
        }
        paramMdl.setFormData(form);

        __doRedrawData(map, form, req, res, con);
        con.setAutoCommit(false);

    }

    /**
     * <br>[機  能] 再描画処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     */
    private void __doRedrawData(ActionMapping map,
                                      Sml020Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws Exception {

        JSONObject jsonData = new JSONObject();

        con.setAutoCommit(true);
        RequestModel reqMdl = getRequestModel(req);
        Sml020Biz biz = new Sml020Biz(reqMdl);
        //トランザクショントークン設定
        this.saveToken(req);

        Sml020ParamModel paramMdl = new Sml020ParamModel();
        paramMdl.setParam(form);

        //ひな形リスト設定
        biz.setHinagataList(paramMdl, reqMdl, con);

        //宛先名称一覧を設定
        biz.setAtesaki(paramMdl, reqMdl, con);
        //CC名称一覧を設定
        biz.setAtesakiCc(paramMdl, reqMdl, con);
        //BCC名称一覧を設定
        biz.setAtesakiBcc(paramMdl, con);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), paramMdl.getSml020pluginId(), reqMdl);

        //添付ファイル情報セット
        biz.setTempFiles(paramMdl, tempDir, con);

        paramMdl.setFormData(form);

        jsonData = JSONObject.fromObject(form);

        PrintWriter out = null;

        try {
            res.setHeader("Cache-Control", "no-cache");
            res.setContentType("application/json;charset=UTF-8");
            out = res.getWriter();
            out.print(jsonData);
            out.flush();
        } catch (Exception e) {
            log__.error("jsonデータ送信失敗(草稿データ)");
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }

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
     * @throws Exception 実行時例外
     */
    private void __doDeleteTmpData(ActionMapping map,
                                      Sml020Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
                                      throws Exception {

        JSONObject jsonData = new JSONObject();

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getSml020pluginId(), getRequestModel(req));

        //選択された添付ファイルを削除する
        cmnBiz.deleteFile(form.getSml020selectFiles(), tempDir);

        jsonData = JSONObject.fromObject(form);

        PrintWriter out = null;

        try {
            res.setHeader("Cache-Control", "no-cache");
            res.setContentType("application/json;charset=UTF-8");
            out = res.getWriter();
            out.print(jsonData);
            out.flush();
        } catch (Exception e) {
            log__.error("jsonデータ送信失敗(削除添付ファイルデータ)");
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    /**
     * <br>[機  能] テンポラリディレクトリのファイルをすべて削除
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     */
    private void __doDeleteTmpDirData(ActionMapping map,
                                      Sml020Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
                                      throws Exception {
        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), GSConstSmail.PLUGIN_ID_SMAIL, getRequestModel(req));

        //テンポラリディレクトリのファイル削除を行う
        Sml020Biz biz = new Sml020Biz(getRequestModel(req));
        biz.doDeleteFile(tempDir);
    }

    /**
     * <br>[機  能] 再描画処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     */
    private void __doSendDataCheck(ActionMapping map,
                                    Sml020Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws Exception {

        con.setAutoCommit(true);

        JSONObject jsonData = new JSONObject();

        try {

            //入力チェック
            ActionErrors errors =
                form.validateCheck020(Sml020Form.VALIDATE_MODE_SOUSIN, con, getRequestModel(req));
            if (!errors.isEmpty()) {
                form.setErrorsList(__getJsonErrorMsg(req, errors));
            }

            jsonData = JSONObject.fromObject(form);

            PrintWriter out = null;

            try {
                res.setHeader("Cache-Control", "no-cache");
                res.setContentType("application/json;charset=UTF-8");
                out = res.getWriter();
                out.print(jsonData);
                out.flush();
            } catch (Exception e) {
                log__.error("jsonデータ送信失敗(削除添付ファイルデータ)");
                throw e;
            } finally {
                if (out != null) {
                    out.close();
                }
            }

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        }
    }

    /**
     * <br>[機  能] 草稿保存ボタン押下時処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     */
    private void __doSaveData(ActionMapping map,
                                    Sml020Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws Exception {

        JSONObject jsonData = new JSONObject();

        con.setAutoCommit(false);
        boolean commitFlg = false;
        RequestModel reqMdl = getRequestModel(req);

        try {

            //入力チェック
            ActionErrors errors =
                form.validateCheck020(Sml020Form.VALIDATE_MODE_SAVE, con, reqMdl);
            if (!errors.isEmpty()) {
                form.setErrorsList(__getJsonErrorMsg(req, errors));
            } else {
                //テンポラリディレクトリパスを取得
                CommonBiz cmnBiz = new CommonBiz();
                String tempDir = cmnBiz.getTempDir(
                        getTempPath(req), form.getSml020pluginId(), reqMdl);

                //アプリケーションのルートパス
                String appRootPath = getAppRootPath();

                //DBに登録
                Sml020Biz biz = new Sml020Biz(reqMdl);
                MlCountMtController cntCon = getCountMtController(req);
                Sml020ParamModel paramMdl = new Sml020ParamModel();
                paramMdl.setParam(form);
                biz.insertSitagakiData(paramMdl, reqMdl, con, cntCon, appRootPath, tempDir);
                paramMdl.setFormData(form);
                commitFlg = true;

                GsMessage gsMsg = new GsMessage();
                String msg = gsMsg.getMessage(req, "cmn.entry");
                String msgHozon = gsMsg.getMessage(req, "sml.sml020.07");

                //ログ出力処理
                //ログ出力処理
                SmlAccountModel sacMdl = new SmlAccountModel();
                SmlAccountDao sacDao = new SmlAccountDao(con);
                sacMdl = sacDao.select(form.getSmlViewAccount());

                SmlCommonBiz smlBiz = new SmlCommonBiz(con, reqMdl);
                smlBiz.outPutLog(map, reqMdl,
                        msg, GSConstLog.LEVEL_TRACE, "アカウント:" + sacMdl.getSacName()
                                                     + "\n" + msgHozon);
            }

            jsonData = JSONObject.fromObject(form);

            PrintWriter out = null;

            try {
                res.setHeader("Cache-Control", "no-cache");
                res.setContentType("application/json;charset=UTF-8");
                out = res.getWriter();
                out.print(jsonData);
                out.flush();
            } catch (Exception e) {
                log__.error("jsonデータ送信失敗(草稿に保存データ)");
                throw e;
            } finally {
                if (out != null) {
                    out.close();
                }
            }

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
    }

   /**
    * <br>[機  能] 削除確認画面表示処理
    * <br>[解  説]
    * <br>[備  考]
    *
    * @param map マップ
    * @param form フォーム
    * @param req リクエスト
    * @param res レスポンス
    * @param con コネクション
 * @throws Exception 実行時例外
    */
   private void __doDeleteConfirmationData(ActionMapping map,
                                                 Sml020Form form,
                                                 HttpServletRequest req,
                                                 HttpServletResponse res,
                                                 Connection con)
       throws Exception {

       JSONObject jsonData = new JSONObject();

       con.setAutoCommit(true);
       //削除するメッセージの件名を取得する
       Sml020ParamModel paramMdl = new Sml020ParamModel();
       paramMdl.setParam(form);
       Sml020Biz biz = new Sml020Biz(getRequestModel(req));
       String mailName = biz.getMailTitle(paramMdl, con);
       paramMdl.setFormData(form);

       //メッセージ
       List<String> messageList = new ArrayList<String>();
       MessageResources msgRes = getResources(req);

       GsMessage gsMsg = new GsMessage();
       String soukou = gsMsg.getMessage(req, "sml.101");
       String msg = gsMsg.getMessage(req, "cmn.message");
       String msg2 = gsMsg.getMessage(req, "wml.231");

       String mailKbnName = soukou + " ";
       String msgId = "move.gomibako.mail";

       messageList.add(
               msgRes.getMessage(
                       msgId,
                       msg,
                       msg2
                       + mailKbnName
                       + StringUtilHtml.transToHTmlPlusAmparsant(mailName)));

       form.setMessageList(messageList);

       jsonData = JSONObject.fromObject(form);

       PrintWriter out = null;

       try {
           res.setHeader("Cache-Control", "no-cache");
           res.setContentType("application/json;charset=UTF-8");
           out = res.getWriter();
           out.print(jsonData);
           out.flush();
       } catch (Exception e) {
           log__.error("jsonデータ送信失敗(草稿に保存データ)");
           throw e;
       } finally {
           if (out != null) {
               out.close();
           }
       }
   }

   /**
    * <br>[機  能] 返信・全返信・転送時処理
    * <br>[解  説]
    * <br>[備  考]
    *
    * @param map マップ
    * @param form フォーム
    * @param req リクエスト
    * @param res レスポンス
    * @param con コネクション
    * @param mode モード
    * @throws Exception 実行時例外
    */
   private void __setSmsgParamData(ActionMapping map,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con,
                                   Sml020Form form,
                                   String mode)
       throws Exception {

       JSONObject jsonData = new JSONObject();

       boolean commitFlg = false;
       con.setAutoCommit(false);

       RequestModel reqMdl = getRequestModel(req);
       try {

           CommonBiz cmnBiz = new CommonBiz();
           String appRootPath = getAppRootPath();
           String tempDir = cmnBiz.getTempDir(
                   getTempPath(req), form.getSml020pluginId(), reqMdl);

           form.setSml020ProcMode(mode);

           Sml020ParamModel paramMdl = new Sml020ParamModel();
           paramMdl.setParam(form);
           Sml020Biz biz = new Sml020Biz(reqMdl);
           biz.setMailData(paramMdl, reqMdl, con, appRootPath, tempDir, reqMdl.getDomain());
           biz.setTempFiles(paramMdl, tempDir, con);

           //宛先名称一覧を設定
           biz.setAtesaki(paramMdl, reqMdl, con);
           //CC名称一覧を設定
           biz.setAtesakiCc(paramMdl, reqMdl, con);
           //BCC名称一覧を設定
           biz.setAtesakiBcc(paramMdl, con);
           paramMdl.setFormData(form);

           jsonData = JSONObject.fromObject(form);

           PrintWriter out = null;

           try {
               res.setHeader("Cache-Control", "no-cache");
               res.setContentType("application/json;charset=UTF-8");
               out = res.getWriter();
               out.print(jsonData);
               out.flush();
           } catch (Exception e) {
               log__.error("jsonデータ送信失敗(草稿に保存データ)");
               throw e;
           } finally {
               if (out != null) {
                   out.close();
               }
           }
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
   }

   /**
    * <br>[機  能] 削除確認画面でOKボタン押下
    * <br>[解  説]
    * <br>[備  考]
    *
    * @param map マップ
    * @param form フォーム
    * @param req リクエスト
    * @param res レスポンス
    * @param con コネクション
    * @throws Exception 実行時例外
    */
   private void __doDeleteOkData(ActionMapping map,
                                       Sml020Form form,
                                       HttpServletRequest req,
                                       HttpServletResponse res,
                                       Connection con)
       throws Exception {

       JSONObject jsonData = new JSONObject();

       boolean commitFlg = false;
       con.setAutoCommit(false);

       RequestModel reqMdl = getRequestModel(req);
       try {

           //削除処理実行
           Sml020ParamModel paramMdl = new Sml020ParamModel();
           paramMdl.setParam(form);
           Sml020Biz biz = new Sml020Biz(reqMdl);
           biz.deleteMessage(paramMdl, reqMdl, con);
           paramMdl.setFormData(form);

           GsMessage gsMsg = new GsMessage();
           String delete = gsMsg.getMessage(req, "cmn.delete");

           //ログ出力処理
           SmlAccountModel sacMdl = new SmlAccountModel();
           SmlAccountDao sacDao = new SmlAccountDao(con);
           sacMdl = sacDao.select(form.getSmlViewAccount());

           SmlCommonBiz smlBiz = new SmlCommonBiz(con, reqMdl);
           smlBiz.outPutLog(map, reqMdl,
                   delete, GSConstLog.LEVEL_TRACE, "アカウント:" + sacMdl.getSacName()
                                                + "\n");

           commitFlg = true;

           //メッセージ
           List<String> messageList = new ArrayList<String>();
           MessageResources msgRes = getResources(req);
           String msgId = "move.gomibako.object";

           String msg = gsMsg.getMessage(req, "cmn.message");

           messageList.add(msgRes.getMessage(msgId, msg));
           form.setMessageList(messageList);

           jsonData = JSONObject.fromObject(form);

           PrintWriter out = null;

           try {
               res.setHeader("Cache-Control", "no-cache");
               res.setContentType("application/json;charset=UTF-8");
               out = res.getWriter();
               out.print(jsonData);
               out.flush();
           } catch (Exception e) {
               log__.error("jsonデータ送信失敗(草稿に保存データ)");
               throw e;
           } finally {
               if (out != null) {
                   out.close();
               }
           }
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
   }

   /**
    * <br>jsonエラーメッセージ作成
    * @param req リクエスト
    * @param errors エラーメッセージ
    * @throws Exception 実行例外
    * @return errorResult jsonエラーメッセージ
    */
   private List<String> __getJsonErrorMsg(
       HttpServletRequest req, ActionErrors errors) throws Exception {

       @SuppressWarnings("all")
       Iterator iterator = errors.get();

       List<String> errorList = new ArrayList<String>();
       while (iterator.hasNext()) {
           ActionMessage error = (ActionMessage) iterator.next();
           errorList.add(getResources(req).getMessage(error.getKey(), error.getValues()));
       }
       return errorList;
   }

   /**
    * <br>[機  能] ひな形を適用する
    * <br>[解  説]
    * <br>[備  考]
    *
    * @param map アクションマッピング
    * @param form アクションフォーム
    * @param req リクエスト
    * @param res レスポンス
    * @param con コネクション
 * @throws Exception 実行時例外
    */
   private void __doSetHina(ActionMapping map,
                                          Sml020Form form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con)
       throws Exception {

       con.setAutoCommit(true);
       Sml020Biz biz = new Sml020Biz(getRequestModel(req));

       //ひな形が選択されている場合
       if (form.getSml020SelectHinaId() > 0) {
           //ひな形データ設定
           Sml020ParamModel paramMdl = new Sml020ParamModel();
           paramMdl.setParam(form);
           biz.setHinagataData(paramMdl, con);
           paramMdl.setFormData(form);
           //ひな形SIDクリア
           form.setSml020SelectHinaId(0);
       }

       __doRedrawData(map, form, req, res, con);
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
    * @throws Exception 実行例外
    */
   private void __doCalledWebmail(
       ActionMapping map,
       Sml020Form form,
       HttpServletRequest req,
       HttpServletResponse res,
       Connection con) throws Exception {

       //対象メールを閲覧可能かを判定
       WmlDao wmlDao = new WmlDao(con);
       if (wmlDao.canReadMail(form.getSml020webmailId(), getSessionUserSid(req))) {
           CommonBiz cmnBiz = new CommonBiz();
           RequestModel reqMdl = getRequestModel(req);
           String tempDir = cmnBiz.getTempDir(
                   getTempPath(req), form.getSml020pluginId(), reqMdl);

           Sml020ParamModel paramMdl = new Sml020ParamModel();
           paramMdl.setParam(form);
           Sml020Biz biz = new Sml020Biz(reqMdl);
           biz.setWebmailData(paramMdl, con,
                                       getAppRootPath(), tempDir);
           paramMdl.setFormData(form);

           form.setSml020webmail(1);
           __doRedrawData(map, form, req, res, con);
       }
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
    * @throws Exception 実行例外
    */
   private void __doNewmail(
       ActionMapping map,
       Sml020Form form,
       HttpServletRequest req,
       HttpServletResponse res,
       Connection con) throws Exception {

           RequestModel reqMdl = getRequestModel(req);

           Sml020ParamModel paramMdl = new Sml020ParamModel();
           paramMdl.setParam(form);
           Sml020Biz biz = new Sml020Biz(reqMdl);
           biz.setAutoDest(paramMdl, reqMdl, con);
           paramMdl.setFormData(form);

           __doRedrawData(map, form, req, res, con);
   }
}