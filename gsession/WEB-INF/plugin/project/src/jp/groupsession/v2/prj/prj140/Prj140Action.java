package jp.groupsession.v2.prj.prj140;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.prj.AbstractProjectAction;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.prj020.Prj020Action;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] プロジェクト管理 プロジェクトテンプレート登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj140Action extends AbstractProjectAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj140Action.class);
    /** CMD:OKボタンクリック */
    public static final String CMD_OK_CLICK = "okClick";
    /** CMD:削除ボタンクリック */
    public static final String CMD_DEL_CLICK = "deleteClick";
    /** CMD:削除実行 */
    public static final String CMD_DEL_EXE = "deleteExe";

    /** CMD:戻るボタンクリック */
    public static final String CMD_BACK_CLICK = "backFromInputTmp";
    /** CMD:画面再表示(初期表示以外) */
    public static final String CMD_BACK_REDRAW = Prj020Action.CMD_BACK_REDRAW;
    /** CMD:設定画面で設定ボタンクリック */
    public static final String CMD_EDIT_CLICK = "tmpEditClick";

    /** CMD:所属メンバー追加ボタンクリック */
    public static final String CMD_MEMBER_ADD_CLICK = "memberAdd";
    /** CMD:所属メンバー削除ボタンクリック */
    public static final String CMD_MEMBER_REMOVE_CLICK = "memberRemove";
    /** CMD:プロジェクト管理者追加ボタンクリック */
    public static final String CMD_ADMIN_ADD_CLICK = "adminAdd";
    /** CMD:プロジェクト管理者削除ボタンクリック */
    public static final String CMD_ADMIN_REMOVE_CLICK = "adminRemove";

    /** CMD:プロジェクト状態拡張ボタンクリック */
    public static final String CMD_PRJ_STATUS_MODIFY_CLICK = "prjStatusModify";
    /** CMD:カテゴリ拡張ボタンクリック */
    public static final String CMD_CATEGORI_MODIFY_CLICK = "categoriModify";
    /** CMD:状態拡張ボタンクリック */
    public static final String CMD_STATUS_MODIFY_CLICK = "statusModify";
    /** CMD:プロジェクトメンバ設定ボタンクリック */
    public static final String CMD_MEMBER_EDIT = "memberEdit";

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
        Prj140Form thisForm = (Prj140Form) form;

        int tmpMode = thisForm.getPrjTmpMode();

        //テンプレート表示モード = 共有テンプレート
        if (tmpMode == GSConstProject.MODE_TMP_KYOYU) {
            BaseUserModel buMdl = getSessionUserModel(req);
            CommonBiz cmnBiz = new CommonBiz();
            boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstProject.PLUGIN_ID_PROJECT);

            if (!adminUser) {
                return setPrjTemplateError(map, req);
            }
        } else if (tmpMode < 1) {
            return setPrjTemplateError(map, req);
        }

        //コマンドパラメータ取得
        String cmd = PrjCommonBiz.getCmdProperty(req);

        if (CMD_OK_CLICK.equals(cmd)) {
            log__.debug("OKボタンクリック");
            forward = __doPushEntry(map, thisForm, req, res, con);

        } else if (CMD_DEL_CLICK.equals(cmd)) {
            log__.debug("削除ボタンクリック");
            forward = __doDeleteConf(map, thisForm, req, res, con);

        } else if (CMD_DEL_EXE.equals(cmd)) {
            log__.debug("削除実行");
            forward = __doDeleteExe(map, thisForm, req, res, con);

        } else if (CMD_BACK_CLICK.equals(cmd)) {
            log__.debug("戻るボタンクリック");
            forward = __doBack(map, thisForm, req, res, con);

        } else if (CMD_MEMBER_ADD_CLICK.equals(cmd)) {
            log__.debug("所属メンバー追加ボタンクリック");
            forward = __doAddMember(map, thisForm, req, res, con);

        } else if (CMD_MEMBER_REMOVE_CLICK.equals(cmd)) {
            log__.debug("所属メンバー削除ボタンクリック");
            forward = __doRemoveMember(map, thisForm, req, res, con);

        } else if (CMD_ADMIN_ADD_CLICK.equals(cmd)) {
            log__.debug("プロジェクト管理者追加ボタンクリック");
            forward = __doAddAdminMember(map, thisForm, req, res, con);

        } else if (CMD_ADMIN_REMOVE_CLICK.equals(cmd)) {
            log__.debug("プロジェクト管理者削除ボタンクリック");
            forward = __doRemoveAdminMember(map, thisForm, req, res, con);

        } else if (CMD_PRJ_STATUS_MODIFY_CLICK.equals(cmd)) {
            log__.debug("プロジェクト状態拡張ボタンクリック");
            forward = map.findForward(CMD_PRJ_STATUS_MODIFY_CLICK);

        } else if (CMD_CATEGORI_MODIFY_CLICK.equals(cmd)) {
            log__.debug("カテゴリ拡張ボタンクリック");
            forward = map.findForward(CMD_CATEGORI_MODIFY_CLICK);

        } else if (CMD_STATUS_MODIFY_CLICK.equals(cmd)) {
            log__.debug("状態拡張ボタンクリック");
            forward = map.findForward(CMD_STATUS_MODIFY_CLICK);

        } else if (CMD_MEMBER_EDIT.equals(cmd)) {
            log__.debug("プロジェクトメンバー設定ボタンクリック");
            forward = map.findForward(CMD_MEMBER_EDIT);

        } else if (CMD_BACK_REDRAW.equals(cmd) || CMD_EDIT_CLICK.equals(cmd)) {
            log__.debug("画面再表示(初期表示以外)");
            forward = __doRedraw(map, thisForm, req, res, con);

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
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doInit(ActionMapping map,
                                    Prj140Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws SQLException, IOToolsException {

        con.setAutoCommit(true);
        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //初期表示情報を画面にセットする
        Prj140Biz biz = new Prj140Biz(con, getRequestModel(req));

        Prj140ParamModel paramMdl = new Prj140ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, userSid, getTempPath(req));
        paramMdl.setFormData(form);


        return __doDspSet(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 画面に常に表示する情報を取得する
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
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doDspSet(ActionMapping map,
                                      Prj140Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException {

        con.setAutoCommit(true);
        //プラグイン設定を取得する
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));

        //画面に常に表示する情報を取得する
        Prj140Biz biz = new Prj140Biz(con, getRequestModel(req));

        Prj140ParamModel paramMdl = new Prj140ParamModel();
        paramMdl.setParam(form);
        biz.getDspData(paramMdl, pconfig, getTempPath(req));
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 戻るボタン押下時
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doBack(ActionMapping map,
                                    Prj140Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws IOToolsException {

        //オブジェクトファイルを削除する
        PrjCommonBiz.deleteFile(
                getTempPath(req), getRequestModel(req), GSConstProject.SAVE_FILENAME);

        return map.findForward("backTmpList");
    }

    /**
     * <br>[機  能] 所属メンバー追加を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doAddMember(ActionMapping map,
                                         Prj140Form form,
                                         HttpServletRequest req,
                                         HttpServletResponse res,
                                         Connection con)
        throws SQLException, IOToolsException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setPrj140hdnMember(
                cmnBiz.getAddMemberSplitKey(form.getPrj140user(), form.getPrj140hdnMember()));

        return __doDspSet(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 所属メンバーの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doRemoveMember(ActionMapping map,
                                            Prj140Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws SQLException, IOToolsException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setPrj140hdnMember(
                cmnBiz.getDeleteMemberSplitKey(
                        form.getPrj140syozokuMember(),
                        form.getPrj140hdnMember(),
                        true));
        //管理者からも削除
        form.setPrj140hdnAdmin(
                cmnBiz.getDeleteMemberSplitKey(
                        form.getPrj140syozokuMember(),
                        form.getPrj140hdnAdmin(),
                        false));

        return __doDspSet(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 管理者所属メンバー追加を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doAddAdminMember(ActionMapping map,
                                              Prj140Form form,
                                              HttpServletRequest req,
                                              HttpServletResponse res,
                                              Connection con)
        throws SQLException, IOToolsException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setPrj140hdnAdmin(
                cmnBiz.getAddMember(form.getPrj140prjMember(), form.getPrj140hdnAdmin()));

        return __doDspSet(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 管理者所属メンバーメンバーの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doRemoveAdminMember(ActionMapping map,
                                                 Prj140Form form,
                                                 HttpServletRequest req,
                                                 HttpServletResponse res,
                                                 Connection con)
        throws SQLException, IOToolsException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setPrj140hdnAdmin(
                cmnBiz.getDeleteMember(form.getPrj140adminMember(), form.getPrj140hdnAdmin()));

        return __doDspSet(map, form, req, res, con);
    }
    /**
     * <br>[機  能] 再描画処理
     *
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doRedraw(ActionMapping map,
                                         Prj140Form form,
                                         HttpServletRequest req,
                                         HttpServletResponse res,
                                         Connection con)
        throws SQLException, IOToolsException {

        //メンバーに存在しないユーザは管理者から削除
        Map<String, String> sidMap = new HashMap<String, String>();
        if (form.getPrj140hdnMember() != null
                          && form.getPrj140hdnMember().length > 0) {
            for (String hdn : form.getPrj140hdnMember()) {
                String[] splitStr = hdn.split(GSConst.GSESSION2_ID);
                String keyValue = String.valueOf(splitStr[0]);
                sidMap.put(keyValue, keyValue);
            }
        }
        List<String> admList = new ArrayList<String>();
        if (form.getPrj140hdnAdmin() != null
                && form.getPrj140hdnAdmin().length > 0) {
            for (String hdn : form.getPrj140hdnAdmin()) {
                if (sidMap.get(hdn) != null) {
                    admList.add(hdn);
                }
            }
            form.setPrj140hdnAdmin(admList.toArray(new String[admList.size()]));
        }
        return __doDspSet(map, form, req, res, con);
    }
    /**
     * <br>[機  能] OKボタンクリック時処理
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
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doPushEntry(ActionMapping map,
                                         Prj140Form form,
                                         HttpServletRequest req,
                                         HttpServletResponse res,
                                         Connection con)
        throws SQLException, IOToolsException {

        con.setAutoCommit(true);
        ActionErrors errors = form.validate140(con, getSessionUserModel(req), req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doDspSet(map, form, req, res, con);
        }

        return map.findForward(CMD_OK_CLICK);
    }

    /**
     * <br>[機  能] 削除ボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @return ActionForward
     */
    private ActionForward __doDeleteConf(ActionMapping map,
                                          Prj140Form form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con)
        throws SQLException, IOToolsException {

        // トランザクショントークン設定
        this.saveToken(req);

        //削除確認画面を表示
        return __setKakuninDsp(map, form, req, con);
    }

    /**
     * <br>[機  能] 削除処理を行う(削除実行)
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
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doDeleteExe(ActionMapping map,
                                         Prj140Form form,
                                         HttpServletRequest req,
                                         HttpServletResponse res,
                                         Connection con)
        throws SQLException, IOToolsException {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //プロジェクトを削除する
        Prj140Biz biz = new Prj140Biz(con, getRequestModel(req));

        Prj140ParamModel paramMdl = new Prj140ParamModel();
        paramMdl.setParam(form);
        biz.deleteProject(paramMdl, userSid);
        paramMdl.setFormData(form);

        //オブジェクトファイルを削除する
        PrjCommonBiz.deleteFile(getTempPath(req),
                getRequestModel(req), GSConstProject.SAVE_FILENAME);

        //ログ出力処理
        GsMessage gsMsg = new GsMessage(req);
        PrjCommonBiz prjBiz = new PrjCommonBiz(con, gsMsg, getRequestModel(req));
        String opCode = getInterMessage(req, "cmn.delete");

        prjBiz.outPutLog(
                map, req, res, opCode,
                GSConstLog.LEVEL_INFO,
                "[name]" + form.getPrj140prtTmpName());

        //削除完了画面を表示
        return __setKanryoDsp(map, form, req);
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
    private ActionForward __setKakuninDsp(ActionMapping map,
                                           Prj140Form form,
                                           HttpServletRequest req,
                                           Connection con)
        throws SQLException {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //キャンセルボタンクリック時遷移先
        ActionForward forwardCancel = map.findForward("redraw");
        cmn999Form.setUrlCancel(
                forwardCancel.getPath() + "?" + GSConst.P_CMD + "=" + CMD_BACK_REDRAW);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("redraw");
        cmn999Form.setUrlOK(forwardOk.getPath() + "?" + GSConst.P_CMD + "=" + CMD_DEL_EXE);
        GsMessage gsMsg = new GsMessage();
        //プロジェクトテンプレート
        String textProjectTmp = gsMsg.getMessage(req, "project.prj130.1");
        //メッセージ
        MessageResources msgRes = getResources(req);
        String msg = msgRes.getMessage("sakujo.kakunin.once", textProjectTmp);
        cmn999Form.setMessage(msg);

        //画面パラメータをセット
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        form.setcmn999FormParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
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
    private ActionForward __setKanryoDsp(ActionMapping map,
                                          Prj140Form form,
                                          HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        cmn999Form.setUrlOK(map.findForward("backTmpList").getPath());

        MessageResources msgRes = getResources(req);
        GsMessage gsMsg = new GsMessage();
        //プロジェクトテンプレート
        String textProjectTmp = gsMsg.getMessage(req, "project.prj130.1");
        //削除完了
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object", textProjectTmp));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        form.setcmn999FormParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}