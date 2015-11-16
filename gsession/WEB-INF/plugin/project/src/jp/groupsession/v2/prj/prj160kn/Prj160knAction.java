package jp.groupsession.v2.prj.prj160kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.prj.AbstractProjectAction;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.model.PrjMembersModel;
import jp.groupsession.v2.prj.model.PrjTodocategoryModel;
import jp.groupsession.v2.prj.model.PrjTodostatusModel;
import jp.groupsession.v2.prj.prj160.Prj160Biz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] プロジェクト管理 TODOインポート確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj160knAction extends AbstractProjectAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj160knAction.class);
    /** CMD:実行ボタンクリック */
    public static final String CMD_CSV_IMPORT = "todoCsvImport";
    /** CMD:戻るボタンクリック */
    public static final String CMD_BACK_CLICK = "backFromCsvImportKn";

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
    public ActionForward executeAction(ActionMapping map,
                                        ActionForm form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con) throws Exception {

        ActionForward forward = null;
        Prj160knForm thisForm = (Prj160knForm) form;

        //コマンドパラメータ取得
        String cmd = PrjCommonBiz.getCmdProperty(req);

        if (CMD_CSV_IMPORT.equals(cmd)) {
            log__.debug("実行ボタンクリック");
            forward = __doImport(map, thisForm, req, res, con);

        } else if (CMD_BACK_CLICK.equals(cmd)) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward(CMD_BACK_CLICK);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 取込みファイル操作時例外
     * @throws Exception その他例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Prj160knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException, Exception {

        con.setAutoCommit(true);
        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
            cmnBiz.getTempDir(getTempPath(req),
                GSConstProject.PLUGIN_ID_PROJECT, getRequestModel(req));

        ActionErrors errors = form.validateCheck(map, req, tempDir, con);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doDsp(map, form, req, res, con);
        }

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 画面表示を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     * @throws IOToolsException 取込みファイル操作時例外
     */
    private ActionForward __doDsp(ActionMapping map,
                                   Prj160knForm form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con)
        throws SQLException, IOToolsException {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
            cmnBiz.getTempDir(getTempPath(req),
                GSConstProject.PLUGIN_ID_PROJECT, getRequestModel(req));

        Prj160knBiz biz = new Prj160knBiz(getRequestModel(req), con);
        Prj160knParamModel paramMdl = new Prj160knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, tempDir);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] インポート処理を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 取込みファイル操作時例外
     * @throws Exception その他実行時例外
     */
    private ActionForward __doImport(ActionMapping map,
                                      Prj160knForm form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException, Exception {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
            cmnBiz.getTempDir(getTempPath(req),
                    GSConstProject.PLUGIN_ID_PROJECT, getRequestModel(req));

        //再度入力チェック
        ActionErrors errors = form.validateCheck(map, req, tempDir, con);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        //取込み処理
        con.setAutoCommit(false);
        boolean commit = false;

        try {

            //セッションユーザSID取得
            int userSid = -1;
            BaseUserModel usrMdl = getSessionUserModel(req);
            if (usrMdl != null) {
                userSid = usrMdl.getUsrsid();
            }

            //マイプロジェクト区分を取得
            int prjMyKbn = form.getPrj160PrjMyKbn();

            int prjSid = form.getPrj160PrjSid();
            Prj160knBiz biz = new Prj160knBiz(getRequestModel(req), con);

            //リセットインポートの場合既存データを削除
            if (form.getPrj160ImportMeans() == GSConstProject.PRJ_IMP_MEANS_RESET) {
                biz.resetTodo(prjSid, userSid);
            }

            //カテゴリマッピング取得
            HashMap<String, PrjTodocategoryModel> categoryMap =
                biz.getCategoryMap(prjSid);

            //状態マッピング取得
            HashMap<String, PrjTodostatusModel> statusMap =
                biz.getStatusMap(prjSid);

            //プロジェクトメンバーマッピングを取得
            HashMap<String, PrjMembersModel> memberMap =
                biz.getMemberMap(prjSid);

            //採番用コネクション取得
            MlCountMtController cntCon = getCountMtController(req);

            //コンストラクタ
            TodoCsvImportConstModel todoConstModel = new TodoCsvImportConstModel();
            todoConstModel.setPrjSid(prjSid);
            todoConstModel.setPrjMyKbn(prjMyKbn);
            todoConstModel.setUsrSid(userSid);
            todoConstModel.setCategoryMap(categoryMap);
            todoConstModel.setStatusMap(statusMap);
            todoConstModel.setMemberMap(memberMap);
            TodoCsvImport imp = new TodoCsvImport(con, cntCon, todoConstModel, req);

            //インポート
            long num = imp.importCsv(tempDir);

            //ログ出力処理
            GsMessage gsMsg = new GsMessage(req);
            PrjCommonBiz prjBiz = new PrjCommonBiz(con, gsMsg, getRequestModel(req));
            prjBiz.outPutLog(
                    map, req, res,
                    getInterMessage(req, "cmn.import"),
                    GSConstLog.LEVEL_INFO, "[count]" + (num - 1));

            commit = true;

            //完了画面遷移
            return __setCompDsp(map, form, req);

        } catch (Exception e) {
            log__.error("TODO_CSVの取り込みに失敗しました。" + e);
            throw e;

        } finally {

            //テンポラリディレクトリのファイル削除を行う
            Prj160Biz prj160biz = new Prj160Biz(getRequestModel(req), con);
            prj160biz.doDeleteFile(tempDir);

            if (commit) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * [機  能] インポート完了画面のパラメータセット
     * [解  説]
     * [備  考]
     *
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setCompDsp(ActionMapping map,
                                        Prj160knForm form,
                                        HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        cmn999Form.setUrlOK(map.findForward("impComp").getPath());
        MessageResources msgRes = getResources(req);

        cmn999Form.setMessage(
                msgRes.getMessage("comp.todo.import", GSConstProject.MSG_TODO));

        //画面パラメータをセット
        form.setcmn999FormParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}