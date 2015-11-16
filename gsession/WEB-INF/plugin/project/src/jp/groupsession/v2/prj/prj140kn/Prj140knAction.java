package jp.groupsession.v2.prj.prj140kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.prj.AbstractProjectAction;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.prj020.Prj020Action;
import jp.groupsession.v2.prj.prj140.Prj140Action;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] プロジェクト管理 プロジェクトテンプレート登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj140knAction extends AbstractProjectAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj140knAction.class);
    /** CMD:確定ボタンクリック */
    public static final String CMD_KAKUTEI_CLICK = "kakuteiClick";
    /** CMD:選択ボタンクリック */
    public static final String CMD_SELECT_CLICK = Prj020Action.CMD_EDIT_CLICK;
    /** CMD:戻るボタンクリック(テンプレ選択時) */
    public static final String CMD_BACK = "backToTmpList";
    /** CMD:戻るボタンクリック */
    public static final String CMD_BACK_CLICK = Prj140Action.CMD_BACK_REDRAW;

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
        Prj140knForm thisForm = (Prj140knForm) form;

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

        if (CMD_KAKUTEI_CLICK.equals(cmd)) {
            log__.debug("確定ボタンクリック");
            forward = __doPushRegist(map, thisForm, req, res, con);

        } else if (CMD_SELECT_CLICK.equals(cmd)) {
            log__.debug("選択ボタンクリック");
            forward = __doTemplateSelect(map, thisForm, req, res, con);

        } else if (CMD_BACK_CLICK.equals(cmd)) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward(CMD_BACK_CLICK);

        } else if (CMD_BACK.equals(cmd)) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward(CMD_BACK);

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
                                    Prj140knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException {

        con.setAutoCommit(true);

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //プラグイン設定を取得する
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));

        //初期表示情報を画面にセットする
        Prj140knBiz biz = new Prj140knBiz(con, getRequestModel(req));

        Prj140knParamModel paramMdl = new Prj140knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, pconfig, userSid, getTempPath(req));
        paramMdl.setFormData(form);

        // トランザクショントークン設定
        this.saveToken(req);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doPushRegist(ActionMapping map,
                                          Prj140knForm form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con)
        throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェック
        ActionErrors errors = form.validate140(con, getSessionUserModel(req), req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        //採番コントローラ
        MlCountMtController cntCon = getCountMtController(req);

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //登録、または更新処理を行う
        Prj140knBiz biz = new Prj140knBiz(con, getRequestModel(req));

        Prj140knParamModel paramMdl = new Prj140knParamModel();
        paramMdl.setParam(form);
        biz.doAddEdit(paramMdl, cntCon, userSid, getTempPath(req));
        paramMdl.setFormData(form);

        //オブジェクトファイルを削除する
        PrjCommonBiz.deleteFile(
                getTempPath(req), getRequestModel(req), GSConstProject.SAVE_FILENAME);

        //ログ出力処理
        GsMessage gsMsg = new GsMessage(req);
        PrjCommonBiz prjBiz = new PrjCommonBiz(con, gsMsg, getRequestModel(req));
        String opCode = "";

        if (form.getPrtSid() < 1) {
            opCode = getInterMessage(req, "cmn.entry");
        } else {
            opCode = getInterMessage(req, "cmn.change");
        }

        prjBiz.outPutLog(
                map, req, res, opCode,
                GSConstLog.LEVEL_INFO,
                "[name]" + form.getPrj140prtTmpName());

        //登録・更新完了画面を表示
        return __setKanryoDsp(map, form, req);
    }

    /**
     * <br>[機  能] 選択ボタンクリック時処理
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
    private ActionForward __doTemplateSelect(ActionMapping map,
                                              Prj140knForm form,
                                              HttpServletRequest req,
                                              HttpServletResponse res,
                                              Connection con)
        throws SQLException, IOToolsException {

        Prj140knBiz biz = new Prj140knBiz(con, getRequestModel(req));

        Prj140knParamModel paramMdl = new Prj140knParamModel();
        paramMdl.setParam(form);
        biz.setTemplateData(paramMdl, getTempPath(req));
        paramMdl.setFormData(form);
        return map.findForward(CMD_SELECT_CLICK);
    }

    /**
     * [機  能] 登録・更新完了画面のパラメータセット
     * [解  説]
     * [備  考]
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(ActionMapping map,
                                          Prj140knForm form,
                                          HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        cmn999Form.setUrlOK(map.findForward("updComp").getPath());
        MessageResources msgRes = getResources(req);
        GsMessage gsMsg = new GsMessage();
        //プロジェクトテンプレート
        String textProjectTmp = gsMsg.getMessage(req, "project.prj130.1");
        int prtSid = form.getPrtSid();
        if (prtSid < 1) {
            //登録完了
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object", textProjectTmp));
        } else {
            //更新完了
            cmn999Form.setMessage(
                    msgRes.getMessage("hensyu.kanryo.object", textProjectTmp));
        }

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());

        //画面パラメータをセット
        form.setcmn999FormParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}