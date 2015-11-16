package jp.groupsession.v2.prj.prj020kn;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConstCommon;
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
import jp.groupsession.v2.prj.prj020.Prj020ParamModel;
import jp.groupsession.v2.prj.prj030.Prj030Biz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] プロジェクト登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj020knAction extends AbstractProjectAction {

    /** CMD:確定ボタンクリック */
    public static final String CMD_KAKUTEI_CLICK = "kakuteiClick";

    /** CMD:戻るボタンクリック */
    public static final String CMD_BACK_CLICK = Prj020Action.CMD_BACK_REDRAW;

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj020knAction.class);
    /** 写真画像名 */
    public String imageFileName__ = "";
    /** 写真画像保存名 */
    public String imageFileSaveName__ = "";

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

        log__.debug("Prj020knAction start");
        ActionForward forward = null;

        Prj020knForm thisForm = (Prj020knForm) form;

        //登録・編集権限チェック
        con.setAutoCommit(true);
        GsMessage gsMsg = new GsMessage(req);
        PrjCommonBiz pcBiz = new PrjCommonBiz(con, gsMsg, getRequestModel(req));
        if (!pcBiz.getProjectKengen(thisForm.getPrj020cmdMode(),
                                    thisForm.getPrj020prjSid(),
                                    getSessionUserModel(req))) {
            //権限なし
            return setPrjKengenError(map, req, thisForm.getPrj020cmdMode());
        }
        con.setAutoCommit(false);

        //コマンドパラメータ取得
        String cmd = PrjCommonBiz.getCmdProperty(req);

        if (CMD_KAKUTEI_CLICK.equals(cmd)) {
            log__.debug("確定ボタンクリック");
            forward = __doPushRegist(map, thisForm, req, res, con);

        } else if (CMD_BACK_CLICK.equals(cmd)) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward(CMD_BACK_CLICK);

        } else if (cmd.equals("getImageFile")) {
            log__.debug("アイコン表示");
            forward = __doGetImageFile(map, thisForm, req, res, con);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("Prj020knAction end");
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
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doInit(
        ActionMapping map,
        Prj020knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        //プラグイン設定を取得する
        con.setAutoCommit(true);
        PluginConfig pconfig = getPluginConfigForMain(getPluginConfig(req), con);

        //画像ファイルを設定
        if (!NullDefault.getString(form.getPrj020IcoName(), "").equals("")
                && !NullDefault.getString(form.getPrj020IcoSaveName(), "").equals("")) {
            imageFileName__ = form.getPrj020IcoName();
            imageFileSaveName__ = form.getPrj020IcoSaveName();
        }

        //初期表示情報を画面にセットする
        Prj020knBiz biz = new Prj020knBiz(con, getRequestModel(req));

        Prj020knParamModel paramMdl = new Prj020knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, pconfig, getTempPath(req), getSessionUserModel(req).getUsrsid());
        paramMdl.setFormData(form);

        // トランザクショントークン設定
        this.saveToken(req);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doPushRegist(
        ActionMapping map,
        Prj020knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェック
        ActionErrors errors = form.validate020(con, getSessionUserModel(req), req);
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

        //アプリケーションのルートパス
        String appRoot = getAppRootPath();

        //登録、または更新処理を行う
        Prj020knBiz biz = new Prj020knBiz(con, getRequestModel(req));

        Prj020knParamModel paramMdl = new Prj020knParamModel();
        paramMdl.setParam(form);
        biz.doAddEdit(paramMdl, cntCon, userSid, getTempPath(req), appRoot);
        paramMdl.setFormData(form);


        //オブジェクトファイルを削除する
        PrjCommonBiz.deleteFile(
                getTempPath(req), getRequestModel(req), GSConstProject.SAVE_FILENAME);

        GsMessage gsMsg = new GsMessage(req);

        //ログ出力処理
        PrjCommonBiz prjBiz = new PrjCommonBiz(con, gsMsg, getRequestModel(req));
        String opCode = "";

        //処理モード
        String cmdMode = form.getPrj020cmdMode();
        if (cmdMode.equals(GSConstProject.CMD_MODE_ADD)) {
            //登録
            opCode = getInterMessage(req, "cmn.entry");

        } else if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
            //更新
            opCode = getInterMessage(req, "cmn.change");
        }

        prjBiz.outPutLog(
                map, req, res, opCode,
                GSConstLog.LEVEL_TRACE,
                "[name]" + form.getPrj020prjName());

        //登録・更新完了画面を表示
        return __setKanryoDsp(map, form, req);
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
                                            Prj020knForm form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        //テンポラリディレクトリパス
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
            cmnBiz.getTempDir(getTempPath(req), form.getPrj020pluginId(), getRequestModel(req))
            + GSConstProject.PLUGIN_ID_PROJECT + GSConstProject.TEMP_ICN_PRJ + File.separator;
        String fullPath = IOTools.replaceFileSep(
                tempDir + imageFileSaveName__ + GSConstCommon.ENDSTR_SAVEFILE);

        //ダウンロード
        TempFileUtil.downloadInline(req, res, fullPath, imageFileName__, Encoding.UTF_8);

        return null;
    }

    /**
     * [機  能] 登録・更新完了画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Prj020knForm form,
        HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        Prj020knParamModel paramMdl = new Prj020knParamModel();
        paramMdl.setParam(form);
        ActionForward forwardOk = setBackForward(map, paramMdl, true, req);
        paramMdl.setFormData(form);


        cmn999Form.setUrlOK(forwardOk.getPath());

        MessageResources msgRes = getResources(req);
        GsMessage gsMsg = new GsMessage();
        //プロジェクト
        String textProject = gsMsg.getMessage(req, "cmn.project");
        String cmdMode = form.getPrj020cmdMode();
        if (cmdMode.equals(GSConstProject.CMD_MODE_ADD)) {
            //登録完了
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object", textProject));
        } else if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
            //更新完了
            cmn999Form.setMessage(
                    msgRes.getMessage("hensyu.kanryo.object", textProject));
        }

        //画面パラメータをセット
        form.setcmn999FormParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 直前に表示していた画面へ遷移する(ActionForwardを返す)
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param paramMdl Prj020ParamModel
     * @param dataexist 画面遷移時、対象データがあるか(削除済でないか)
     *                   true=データが存在する、false=存在しない
     * @param req リクエスト
     * @return ActionForward
     */
    public ActionForward setBackForward(
        ActionMapping map,
        Prj020ParamModel paramMdl,
        boolean dataexist,
        HttpServletRequest req) {

        ActionForward forward = null;
        String prj020scrId =
            NullDefault.getString(paramMdl.getPrj020scrId(), GSConstProject.SCR_ID_PRJ010);

        if (prj020scrId.equals(GSConstProject.SCR_ID_PRJ010)) {
            //ダッシュボードへ遷移する
            forward = map.findForward(GSConstProject.SCR_INDEX);

        } else if (prj020scrId.equals(GSConstProject.SCR_ID_PRJ040)) {
            //プロジェクト詳細検索へ遷移する
            forward = map.findForward(GSConstProject.SCR_PRJ_SEARCH);

        } else if (prj020scrId.equals(GSConstProject.SCR_ID_PRJ030)) {

            if (dataexist) {
                //データあり
                //プロジェクトメインへ遷移する
                forward = map.findForward(GSConstProject.SCR_PRJ_MAIN);

            } else {
                //データ削除済みの場合、さらに前の画面へ遷移する
                String prj030scrId =
                    NullDefault.getString(paramMdl.getPrj030scrId(), GSConstProject.SCR_ID_PRJ010);
                Prj030Biz biz030 = new Prj030Biz(getRequestModel(req));
                forward = biz030.getActionForward(prj030scrId, map);
            }
        }
        return forward;
    }
}
