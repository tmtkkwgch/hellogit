package jp.groupsession.v2.adr.adr170;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.adr.AdrCommonBiz;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr160.Adr160Action;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] アドレス帳 コンタクト履歴登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr170Action extends Adr160Action {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr170Action.class);

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

        log__.debug("START_Adr170");
        ActionForward forward = null;

        Adr170Form thisForm = (Adr170Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        //権限チェック
        forward = checkPow(map, thisForm, req, con);
        if (forward != null) {
            return forward;
        }

        if (cmd.equals("ok")) {
            log__.debug("OKボタンクリック");
            forward = __doOk(map, thisForm, req, res, con);

        } else if (cmd.equals("delete")) {
            log__.debug("削除ボタンクリック");
            forward = __doDeleteConf(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteExe")) {
            log__.debug("削除実行");
            forward = __doDeleteExe(map, thisForm, req, res, con);

        } else if (cmd.equals("input_back")) {
            log__.debug("削除確認画面から戻る");
            forward = __doDsp(map, thisForm, req, res, con);

        } else if (cmd.equals("list_back")) {
            log__.debug("戻るボタンクリック");
            //遷移元によって変更
            if (thisForm.getSeniFlg().equals("1")) {
                forward = map.findForward("adr161");
            } else {
                forward = map.findForward("adr160");
            }

        } else if (cmd.equals("deleteFile")) {
            log__.debug("添付ファイル削除ボタンクリック");
            forward = __doDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("user_add")) {
            log__.debug("追加ボタンクリック");
            forward = __doUsrAdd(map, thisForm, req, res, con);

        } else if (cmd.equals("user_delete")) {
            log__.debug("削除ボタンクリック");
            forward = __doUsrDel(map, thisForm, req, res, con);

        } else if (cmd.equals("selectedProject")) {
            //プロジェクト選択
            log__.debug("プロジェクトを選択");
            forward = __doSelectCompany(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteProject")) {
            //プロジェクト削除
            log__.debug("プロジェクト削除");
            forward = __doDeleteProject(map, thisForm, req, res, con);

        } else if (cmd.equals("input_back")) {
            //コンタクト履歴作成確認画面から戻る
            log__.debug("作成確認画面から戻る");
            forward = __doDsp(map, thisForm, req, res, con);

        } else if (cmd.equals("chngGrpCmb")) {
            //アドレスグループコンボ変更
            log__.debug("グループコンボ変更");
            forward = __doDsp(map, thisForm, req, res, con);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END_Adr170");
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
     * @throws IOException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doInit(
        ActionMapping map,
        Adr170Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException, TempFileException {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getAdr170pluginId(), getRequestModel(req));
        log__.debug("テンポラリディレクトリパス ===> " + tempDir);

        //テンポラリディレクトリのファイル削除を行う
        Adr170Biz biz = new Adr170Biz(getRequestModel(req));
        biz.doDeleteFile(tempDir);

        //編集の場合、添付ファイルをテンポラリディレクトリへ移動する
        UDate now = new UDate();
        if (form.getAdr160ProcMode() == GSConstAddress.PROCMODE_EDIT) {
            con.setAutoCommit(true);
            Adr170Biz adrbiz = new Adr170Biz(getRequestModel(req));
            adrbiz.setTempFileData(con,
                    form.getAdr160EditSid(),
                    -1, getAppRootPath(),
                    tempDir,
                    now,
                    GroupSession.getResourceManager().getDomain(req));


            con.setAutoCommit(false);
        }

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
     * @throws IOException 添付ファイルの操作に失敗
     * @return ActionForward
     */
    private ActionForward __doDsp(
        ActionMapping map,
        Adr170Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException {

        con.setAutoCommit(true);

        //管理者設定を反映したプラグイン設定情報を取得
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getAdr170pluginId(), getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //初期表示情報を画面にセットする

        Adr170ParamModel paramMdl = new Adr170ParamModel();
        paramMdl.setParam(form);
        Adr170Biz biz = new Adr170Biz(getRequestModel(req));
        
        ActionForward forward = null;
        AdrCommonBiz commonBiz = new AdrCommonBiz(con);
        if (form.getAdr160ProcMode() == GSConstAddress.PROCMODE_EDIT 
                && commonBiz.canViewContactData(con, paramMdl.getAdr160EditSid()) != 0) {
            forward =  __doNoneAdcDataError(map, form, req, res, con);
        } else {
            biz.getInitData(con, paramMdl, getAppRootPath(),
                    tempDir, getSessionUserModel(req), pconfig);
            paramMdl.setFormData(form);
            forward =  map.getInputForward();
        }
        

        con.setAutoCommit(false);

        // トランザクショントークン設定
        saveToken(req);

        return forward;
    }

    /**
     * <br>[機  能] OKボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 添付ファイルの操作に失敗
     * @return ActionForward
     */
    private ActionForward __doOk(
        ActionMapping map,
        Adr170Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException {

        //入力チェック
        con.setAutoCommit(true);
        ActionErrors errors = form.validateAdr170(getSessionUserModel(req), con, req);
        con.setAutoCommit(false);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doDsp(map, form, req, res, con);
        }

        // トランザクショントークン設定
        saveToken(req);

        return map.findForward("adr170kn");
    }

    /**
     * <br>[機  能] 削除ボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 添付ファイルの操作に失敗
     */
    private ActionForward __doDeleteConf(
        ActionMapping map,
        Adr170Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException {
        AdrCommonBiz commonBiz = new AdrCommonBiz(con);
        if (form.getAdr160ProcMode() == GSConstAddress.PROCMODE_EDIT 
                && commonBiz.canViewContactData(con, form.getAdr160EditSid()) != 0) {
            return __doNoneAdcDataError(map, form, req, res, con);
        }
        //入力チェック
        ActionErrors errors = form.validateAdr170Delete(getSessionUserModel(req), con);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doDsp(map, form, req, res, con);
        }

        // トランザクショントークン設定
        saveToken(req);

        //削除確認画面を表示
        return __setKakuninDsp(map, form, req, con);
    }

    /**
     * <br>[機  能] 削除処理を行う(削除実行)
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
    private ActionForward __doDeleteExe(
        ActionMapping map,
        Adr170Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        AdrCommonBiz commonBiz = new AdrCommonBiz(con);
        if (form.getAdr160ProcMode() == GSConstAddress.PROCMODE_EDIT 
                && commonBiz.canViewContactData(con, form.getAdr160EditSid()) != 0) {
            return __doNoneAdcDataError(map, form, req, res, con);
        }        
        
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //セッションユーザSIDを取得する。
        int userSid = this.getSessionUserModel(req).getUsrsid();

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getAdr170pluginId(), getRequestModel(req));
        log__.debug("テンポラリディレクトリパス ===> " + tempDir);

        //テンポラリディレクトリのファイル削除
        Adr170Biz biz = new Adr170Biz(getRequestModel(req));
        biz.doDeleteFile(tempDir);
        //バイナリ情報論理削除,コンタクト履歴添付情報物理削除

        Adr170ParamModel paramMdl = new Adr170ParamModel();
        paramMdl.setParam(form);
        biz.deleteAlb(con, paramMdl, userSid);
        paramMdl.setFormData(form);

        GsMessage gsMsg = new GsMessage();
        String opCode = gsMsg.getMessage(req, "cmn.delete");

        //ログ出力処理
        AdrCommonBiz adrBiz = new AdrCommonBiz(con);
        adrBiz.outPutLog(
                map, req, res, opCode,
                GSConstLog.LEVEL_TRACE,
                "[title]" + form.getAdr170title());

        //削除完了画面を表示
        return __setKanryoDsp(map, form, req);
    }

    /**
     * [機  能] 削除完了画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Adr170Form form,
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
        //削除完了
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object", gsMsg.getMessage(req, "address.6")));

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        form.setHiddenParam160(cmn999Form);
        form.setHiddenParam170(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * [機  能] 削除確認画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     */
    private ActionForward __setKakuninDsp(
        ActionMapping map,
        Adr170Form form,
        HttpServletRequest req,
        Connection con) throws SQLException {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //キャンセルボタンクリック時遷移先
        ActionForward forwardCancel = map.findForward("adr170");
        cmn999Form.setUrlCancel(
                forwardCancel.getPath() + "?" + GSConst.P_CMD + "=input_back");

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("adr170");
        cmn999Form.setUrlOK(forwardOk.getPath() + "?" + GSConst.P_CMD + "=deleteExe");

        //メッセージ
        MessageResources msgRes = getResources(req);
        Adr170Biz biz = new Adr170Biz(getRequestModel(req));
        String msg = biz.getDeletePosMsg(con, form.getAdr160EditSid(), msgRes);

        cmn999Form.setMessage(msg);

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        form.setHiddenParam160(cmn999Form);
        form.setHiddenParam170(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
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
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 添付ファイルの操作に失敗
     * @return ActionForward
     */
    private ActionForward __doDelete(
        ActionMapping map,
        Adr170Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException, IOException {

        //セッションユーザSIDを取得する。
        int userSid = getSessionUserModel(req).getUsrsid();

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getAdr170pluginId(), getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //選択された添付ファイルを削除する
        cmnBiz.deleteFile(form.getAdr170selectFiles(), tempDir);

        //バイナリ情報,コンタクト履歴添付情報を削除
        boolean commit = false;
        try {
            Adr170Biz biz = new Adr170Biz(getRequestModel(req));
            biz.deleteBin(con, form.getAdr160EditSid(), userSid, 0);
            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.error("バイナリ情報,コンタクト履歴添付情報の削除に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ユーザ追加処理
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
    private ActionForward __doUsrAdd(ActionMapping map,
                                      Adr170Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setSaveUser(
                cmnBiz.getAddMember(form.getAdr170SelectedRight(), form.getSaveUser()));

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ユーザ削除処理
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
    private ActionForward __doUsrDel(ActionMapping map,
                                      Adr170Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setSaveUser(
                cmnBiz.getDeleteMember(form.getAdr170SelectedLeft(), form.getSaveUser()));

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 会社を選択処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doSelectCompany(ActionMapping map,
                                            Adr170Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
    throws Exception {

        //重複したパラメータを除外する
        String[] prjSidArray = form.getAdr170ProjectSid();
        if (prjSidArray != null) {
            List<String> projectIdList = new ArrayList<String>();

            for (int index = 0; index < prjSidArray.length; index++) {
                String projectId = prjSidArray[index];
                if (projectIdList.indexOf(projectId) < 0) {
                    projectIdList.add(projectId);
                }
            }

            prjSidArray = new String[projectIdList.size()];
            for (int index = 0; index < projectIdList.size(); index++) {
                prjSidArray[index] = projectIdList.get(index);
            }

            form.setAdr170ProjectSid(prjSidArray);
        }

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] プロジェクト削除処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doDeleteProject(ActionMapping map,
                                            Adr170Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
                                            throws Exception {

        int delProjectSid = NullDefault.getInt(form.getAdr170delProjectSid(), -1);
        if (delProjectSid < 1) {
            return __doInit(map, form, req, res, con);
        }

        String[] prjSids = form.getAdr170ProjectSid();

        if (prjSids != null && prjSids.length > 0) {
            List <String> prjSidList = new ArrayList<String>();
            String[] newPrjSids = null;
            for (String psid : prjSids) {
                if (delProjectSid != NullDefault.getInt(psid, -1)) {
                    prjSidList.add(psid);
                }
            }

            if (prjSidList != null & prjSidList.size() > 0) {
                newPrjSids = new String[prjSidList.size()];
                int i = 0;
                for (String sid : prjSidList) {
                    newPrjSids[i++] = sid;
                }
            }
            form.setAdr170ProjectSid(newPrjSids);
        }

        return __doDsp(map, form, req, res, con);
    }
    /**
     * <br>コンタクト情報が無い場合のエラー画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doNoneAdcDataError(ActionMapping map, Adr170Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;
        GsMessage gsMsg = new GsMessage();
        //エラー画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("adr160");
        
        form.setHiddenParam(cmn999Form);
        
        //コンタクト履歴情報
        String textSchedule = gsMsg.getMessage(req, "address.src.4");
        //変更
        String textChange = gsMsg.getMessage(req, "cmn.change");
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        if (cmd.equals("delete")) {
            textChange = gsMsg.getMessage(req, "cmn.delete");
        }        
        if (cmd.equals("deleteExe")) {
            textChange = gsMsg.getMessage(req, "cmn.delete");
        }        
        

        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("error.none.edit.data",
                textSchedule, textChange));

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }    
}