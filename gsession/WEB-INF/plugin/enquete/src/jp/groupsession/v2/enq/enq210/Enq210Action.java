package jp.groupsession.v2.enq.enq210;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.AbstractEnqueteAction;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.biz.EnqCommonBiz;
import jp.groupsession.v2.enq.enq010.Enq010Form;
import jp.groupsession.v2.enq.enq210kn.Enq210knForm;
import jp.groupsession.v2.enq.enq210kn.Enq210knParamModel;
import jp.groupsession.v2.enq.enq230.Enq230Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] アンケート 設問登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq210Action extends AbstractEnqueteAction {

    /** 操作 編集 */
    protected static final int OPERATION_EDIT_ = 0;
    /** 操作 削除 */
    protected static final int OPERATION_DELETE_ = 1;
    /** 操作 複写して新規登録 */
    private static final int OPERATION_COPY__ = 2;

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Enq210Action.class);

    /**
     * <br>[機  能] アクションを実行する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     * @throws Exception 実行時例外
     */
    public ActionForward executeAction(ActionMapping map,
                                       ActionForm form,
                                       HttpServletRequest req,
                                       HttpServletResponse res,
                                       Connection con)
        throws Exception {

        //アンケート作成可能者以外はアクセス不可
        if (!_checkEnqCrtUser(con, req)) {
            return getSubmitErrorPage(map, req);
        }

        Enq210Form thisForm = (Enq210Form) form;

        // コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        con.setAutoCommit(true);
        try {
            if (thisForm.getEnqEditMode() == GSConstEnquete.EDITMODE_EDIT) {
                EnqCommonBiz enqBiz = new EnqCommonBiz();
                long emnSid = thisForm.getEditEnqSid();

                //編集対象アンケートが存在するかを確認
                Enq210Biz biz = new Enq210Biz();
                Enq210ParamModel paramMdl = new Enq210ParamModel();
                paramMdl.setParam(thisForm);
                if (!biz.canEditEnquete(con, paramMdl)) {
                    if (cmd.equals("enq210delete") || cmd.equals("enq210delete_ok")) {
                        return __doNoneDataError(map, req, thisForm, OPERATION_DELETE_);
                    } else {
                        return __doNoneDataError(map, req, thisForm, OPERATION_EDIT_);
                    }
                }

                //ユーザが編集対象アンケートを編集可能かを判定
                if (thisForm.getEnq210editMode() != Enq210Form.EDITMODE_TEMPLATE) {
                    if (!enqBiz.canEditEnquete(getRequestModel(req), con, emnSid)) {
                        return getSubmitErrorPage(map, req);
                    }
                } else {
                    if (!enqBiz.checkExistEnquete(
                            getRequestModel(req), con, emnSid, GSConstEnquete.DATA_KBN_TEMPLATE)) {
                        return getSubmitErrorPage(map, req);
                    }
                }
            }
        } finally {
            con.setAutoCommit(false);
        }

        ActionForward forward = null;
        if (cmd.equals("enq210back")) {
            //戻る
            //テンポラリディレクトリを削除
            IOTools.deleteDir(_getEnqueteTempDir(req));

            forward = __doBack(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210copyNew")) {
            //複写して新規登録
            forward = __doCopyNew(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210preview")) {
            //プレビュー
            forward = __doPreview(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210draft")) {
            //草稿に保存
            forward = __doDraft(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210entry")) {
            //発信 or OK
            forward = __doEntry(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210delete")) {
            //削除
            forward = __doDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210delete_ok")) {
            // 削除実行
            forward = __doDeleteOk(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210delTemp")) {
            //削除(添付ファイル)
            forward = __doDeleteTemp(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210addQuestion")) {
            //設問追加
            forward = map.findForward("enq210Question");

        } else if (cmd.equals("enq210editQuestion")) {
            //設問編集
            forward = map.findForward("enq210Question");

        } else if (cmd.equals("enq210deleteQuestion")) {
            //設問削除
            forward = __doDeleteQuestion(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210upQuestion")) {
            //設問順序 上へ
            forward = __doSortQuestion(map, thisForm, req, res, con, Enq210Biz.SORTTYPE_UP_);

        } else if (cmd.equals("enq210downQuestion")) {
            //設問順序 下へ
            forward = __doSortQuestion(map, thisForm, req, res, con, Enq210Biz.SORTTYPE_DOWN_);

        } else if (cmd.equals("enq210addAnswer")) {
            //追加(対象者)ボタンクリック
            forward = __doAddAnswer(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210delAnswer")) {
            //削除(対象者)ボタンクリック
            forward = __doDelAnswer(map, thisForm, req, res, con);

        } else if (cmd.equals("selectTemplate")) {
            //テンプレート選択ボタンクリック
            forward = __doSelectTemplate(map, thisForm, req, res, con);

        } else if (cmd.equals("enq210copyNew")) {
            //複写して新規登録
            forward = __doCopyNew(map, thisForm, req, res, con);

        } else {
            //初期表示処理
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                   Enq210Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con)
    throws Exception {

        con.setAutoCommit(true);
        try {
            // 初期表示情報を取得
            Enq210Biz biz = new Enq210Biz();
            Enq210ParamModel paramModel = new Enq210ParamModel();
            paramModel.setParam(form);
            biz.setInitData(paramModel, getRequestModel(req), con,
                                    getAppRootPath(),
                                    _getEnqueteTempDir(req));
            paramModel.setFormData(form);

            //ショートメールプラグイン使用可否を設定
            form.setEnq210pluginSmailUse(_getUseSmailPluginKbn(req, con));
        } finally {
            con.setAutoCommit(false);
        }

        // トランザクショントークン設定
        saveToken(req);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 戻るボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     */
    private ActionForward __doBack(ActionMapping map,
                                   Enq210Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con) {

        String forward = null;
        if (form.getEnq210editMode() == Enq210Form.EDITMODE_TEMPLATE) {
            forward = "enqTemplate";
        } else {
            forward = "enqList";
        }
        return map.findForward(forward);
    }

    /**
     * <br>[機  能] 複写して新規登録ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doCopyNew(ActionMapping map,
                                   Enq210Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con)
    throws Exception {

        IOTools.deleteDir(_getEnqueteTempDir(req));
        form.setEnq210initFlg(0);
        ActionForward forward = __doInit(map, form, req, res, con);

        form.setEnqEditMode(GSConstEnquete.EDITMODE_ADD);
        form.setEditEnqSid(0);

        return forward;
    }

    /**
     * <br>[機  能] プレビューボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doPreview(ActionMapping map,
                                      Enq210Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
    throws Exception {

        // トランザクショントークン
        saveToken(req);

        return map.findForward("enq210preview");
    }

    /**
     * <br>[機  能] 草稿に保存ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     * @throws SQLException SQL実行時例外
     * @throws Exception 実行時例外
     */
    private ActionForward __doDraft(ActionMapping map,
                                   Enq210Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con) throws SQLException, Exception {

        //入力チェック
        ActionErrors errors
            = form.validateInputDraft(getRequestModel(req), _getEnqueteTempDir(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        RequestModel reqMdl = getRequestModel(req);

        con.setAutoCommit(false);
        boolean commit = false;
        try {
            Enq210ParamModel paramModel = new Enq210knParamModel();
            paramModel.setParam(form);
            paramModel.setEnq210editMode(Enq210Form.EDITMODE_DRAFT);
            Enq210Biz biz = new Enq210Biz();
            biz.entryEnqueteData(paramModel, reqMdl,
                                            con, getCountMtController(req),
                                            getAppRootPath(),
                                            _getEnqueteTempDir(req));
            paramModel.setFormData(form);

            con.commit();
            commit = true;
        } finally {
            if (!commit) {
                JDBCUtil.rollback(con);
            }
        }

        //テンポラリディレクトリを削除
        IOTools.deleteDir(_getEnqueteTempDir(req));

        //オペレーションログ出力
        GsMessage gsMsg = new GsMessage(reqMdl);
        String opCode = gsMsg.getMessage("cmn.save.draft");
        EnqCommonBiz enqlBiz = new EnqCommonBiz(con);
        enqlBiz.outPutLog(map, reqMdl, gsMsg.getMessage("enq.plugin"),
                                opCode, GSConstLog.LEVEL_INFO,
                                "[title]" + form.getEnq210Title());

        return __doCompEntry(map, req, form);
    }

    /**
     * <br>[機  能] 発信ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     * @throws SQLException SQL実行時例外
     * @throws Exception 実行時例外
     */
    private ActionForward __doEntry(ActionMapping map,
                                   Enq210Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con) throws SQLException, Exception {

        //入力チェック
        ActionErrors errors
            = form.validateInput(getRequestModel(req), _getEnqueteTempDir(req), con);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        saveToken(req);
        return map.findForward("confirm");
    }

    /**
     * <br>[機  能] 削除ボタン(添付ファイル)押下時処理を行う
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
    private ActionForward __doDeleteTemp(
        ActionMapping map,
        Enq210Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        Enq210Biz biz = new Enq210Biz();
        String queTempDir = biz.getEnqTempDir(getRequestModel(req), _getEnqueteTempDir(req));
        IOTools.deleteInDir(queTempDir);

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 設問情報削除処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doDeleteQuestion(ActionMapping map,
                                   Enq210Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con)
    throws Exception {

        Enq210Biz biz = new Enq210Biz();
        Enq210ParamModel paramModel = new Enq210ParamModel();
        paramModel.setParam(form);
        biz.deleteQuestion(paramModel, getRequestModel(req), _getEnqueteTempDir(req));
        paramModel.setFormData(form);

        form.setEnq210scrollQuestonFlg(1);
        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 設問情報順序変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param sortType 上へ or 下へ
     * @return フォワード
     * @throws SQLException SQL実行時例外
     * @throws Exception 実行時例外
     */
    private ActionForward __doSortQuestion(ActionMapping map,
                                   Enq210Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con,
                                   int sortType) throws SQLException, Exception {

        Enq210ParamModel paramModel = new Enq210ParamModel();
        paramModel.setParam(form);
        Enq210Biz biz = new Enq210Biz();

        biz.sortQuestion(paramModel, getRequestModel(req), _getEnqueteTempDir(req), sortType);
        paramModel.setFormData(form);

        form.setEnq210scrollQuestonFlg(1);
        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(対象者)ボタン押下時処理を行う
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
    private ActionForward __doAddAnswer(
        ActionMapping map,
        Enq210Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        Enq210Biz biz = new Enq210Biz();
        form.setEnq210answerList(
                biz.getAddMember(form.getEnq210answerList(),
                                    form.getEnq210NoSelectAnswerList()));
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(対象者)ボタン押下時処理を行う
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
    private ActionForward __doDelAnswer(
        ActionMapping map,
        Enq210Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        Enq210Biz biz = new Enq210Biz();
        form.setEnq210answerList(
                biz.getDeleteMember(form.getEnq210selectAnswerList(),
                                    form.getEnq210answerList()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] テンプレート選択時の処理を行う
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
    private ActionForward __doSelectTemplate(
        ActionMapping map,
        Enq210Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        long templateId = form.getEnq210templateId();
        if (templateId > 0) {
            Enq210ParamModel paramModel = new Enq210ParamModel();
            paramModel.setParam(form);

            Enq210Biz biz = new Enq210Biz();
            biz.setEnqueteData(templateId, paramModel, getRequestModel(req),
                                        con, getAppRootPath(), _getEnqueteTempDir(req), true);
            paramModel.setEnq210initFlg(1);
            paramModel.setFormData(form);
        }

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDelete(ActionMapping map,
                                   Enq210Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con) throws SQLException {

        //削除対象アンケートが存在するかを確認
        Enq210Biz biz = new Enq210Biz();
        Enq210ParamModel paramMdl = new Enq210ParamModel();
        paramMdl.setParam(form);
        if (!biz.canEditEnquete(con, paramMdl)) {
            return __doNoneDataError(map, req, form, OPERATION_DELETE_);
        }

        ActionForward forward = null;
        //トランザクショントークン設定
        saveToken(req);

        // 確認画面
        log__.debug("削除確認画面へ");
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("init");
        cmn999Form.setUrlOK(urlForward.getPath() + "?" + GSConst.P_CMD + "=enq210delete_ok");
        cmn999Form.setUrlCancel(urlForward.getPath());

        String msgText = gsMsg.getMessage("enq.plugin");
        if (form.getEnq210editMode() == Enq210knForm.EDITMODE_TEMPLATE) {
            msgText += " " + gsMsg.getMessage("cmn.template");
        }
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kakunin.once", msgText));
        form.setHiddenParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);
        forward = map.findForward("gf_msg");

        return forward;
    }

    /**
     * <br>[機  能] 削除処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDeleteOk(ActionMapping map,
                                       Enq210Form form,
                                       HttpServletRequest req,
                                       HttpServletResponse res,
                                       Connection con) throws SQLException {

        //削除対象アンケートが存在するかを確認
        Enq210Biz biz = new Enq210Biz();
        Enq210ParamModel paramMdl = new Enq210ParamModel();
        paramMdl.setParam(form);
        if (!biz.canEditEnquete(con, paramMdl)) {
            return __doNoneDataError(map, req, form, OPERATION_DELETE_);
        }

        String delEnqName = null;
        con.setAutoCommit(false);
        boolean commit = false;
        try {
            Enq210ParamModel paramModel = new Enq210ParamModel();
            paramModel.setParam(form);
            delEnqName = biz.deleteEnqQuestion(paramModel, getRequestModel(req), con);
            if (delEnqName == null) {
                return __doNoneDataError(map, req, form, OPERATION_DELETE_);
            }
            paramModel.setFormData(form);
            con.commit();
            commit = true;
        } finally {
            if (!commit) {
                JDBCUtil.rollback(con);
            }
        }

        //テンポラリディレクトリを削除
        IOTools.deleteDir(_getEnqueteTempDir(req));

        //ログ出力
        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String pluginName = gsMsg.getMessage("enq.plugin");
        EnqCommonBiz enqBiz = new EnqCommonBiz(con);
        enqBiz.outPutLog(map, reqMdl, pluginName,
                gsMsg.getMessage("cmn.delete"), GSConstLog.LEVEL_INFO,
                "[title]" + delEnqName);

        return __doDeleteCompDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除完了画面設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return フォワード
     */
    private ActionForward __doDeleteCompDsp(ActionMapping map,
                                            Enq210Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con) {

        ActionForward forward = null;
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("enqList");
        cmn999Form.setUrlOK(urlForward.getPath());

        String msgText = gsMsg.getMessage("enq.plugin");
        if (form.getEnq210editMode() == Enq210knForm.EDITMODE_TEMPLATE) {
            msgText += " " + gsMsg.getMessage("cmn.template");
            urlForward = map.findForward("enqTemplate");
            cmn999Form.setUrlOK(urlForward.getPath());
        }
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object", msgText));

        ((Enq010Form) form).setHiddenParam(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);
        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>[機  能] 登録完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @return ActionForward
     */
    private ActionForward __doCompEntry(
        ActionMapping map,
        HttpServletRequest req,
        Enq210Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;
        GsMessage gsMsg = new GsMessage();

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("enqList");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = null;
        String msg = gsMsg.getMessage(req, "enq.plugin");
        msgState = gsMsg.getMessage("cmn.save.draft2", new String[] {msg});
        cmn999Form.setMessage(msgState);
        ((Enq230Form) form).setHiddenParam(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 編集 / 複写対象が存在しない場合のエラー画面設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @param operation 操作
     * @return ActionForward
     */
    protected ActionForward __doNoneDataError(
        ActionMapping map,
        HttpServletRequest req,
        Enq210Form form,
        int operation) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("enqList");

        //メッセージセット
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        String enqName = gsMsg.getMessage("enq.plugin");
        if (form.getEnq210editMode() == Enq210Form.EDITMODE_TEMPLATE) {
            enqName = gsMsg.getMessage(req, "cmn.shared.template");
            urlForward = map.findForward("enqTemplate");
        }

        String textOperation = gsMsg.getMessage("cmn.change");
        if (operation == OPERATION_DELETE_) {
            textOperation = gsMsg.getMessage("cmn.delete");
        } else if (operation == OPERATION_COPY__) {
            textOperation = gsMsg.getMessage("cmn.register.copy");
        }

        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(msgRes.getMessage("error.none.edit.data",
                                        enqName, textOperation));

        cmn999Form.setUrlOK(urlForward.getPath());
        ((Enq230Form) form).setHiddenParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}
