package jp.groupsession.v2.prj.prj050kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
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
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.prj.AbstractProjectAction;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.prj030.Prj030Biz;
import jp.groupsession.v2.prj.prj050.Prj050Action;
import jp.groupsession.v2.prj.prj050.Prj050Form;
import jp.groupsession.v2.prj.prj060.Prj060Biz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] TODO登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj050knAction extends AbstractProjectAction {

    /** CMD:確定ボタンクリック */
    public static final String CMD_KAKUTEI_CLICK = "kakuteiClick";
    /** CMD:戻るボタンクリック */
    public static final String CMD_BACK_CLICK = Prj050Action.CMD_BACK_REDRAW;
    /** CMD:添付ファイル名クリック */
    public static final String CMD_FILE_DL = "fileDl";

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj050knAction.class);

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

        if (CMD_FILE_DL.equals(cmd)) {
            log__.debug("添付ファイル名クリック");
            return true;

        }
        return false;
    }

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

        log__.debug("Prj050knAction start");
        ActionForward forward = null;

        Prj050knForm thisForm = (Prj050knForm) form;

        con.setAutoCommit(true);
        //ショートメール、回覧プラグインの使用可否判定
        __canUsePlugin(map, thisForm, req, res, con);
        con.setAutoCommit(false);

        //コマンドパラメータ取得
        String cmd = PrjCommonBiz.getCmdProperty(req);

        if (CMD_KAKUTEI_CLICK.equals(cmd)) {
            log__.debug("確定ボタンクリック");
            forward = __doPushRegist(map, thisForm, req, res, con);

        } else if (CMD_BACK_CLICK.equals(cmd)) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward(CMD_BACK_CLICK);

        } else if (CMD_FILE_DL.equals(cmd)) {
            log__.debug("添付ファイルダウンロード");
            forward = __doDownLoad(map, thisForm, req, res, con);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("Prj050knAction end");
        return forward;
    }

    /**
     * <br>[機  能] ショートメール、回覧プラグインが使用可能か判定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    private void __canUsePlugin(ActionMapping map,
                                 Prj050knForm form,
                                 HttpServletRequest req,
                                 HttpServletResponse res,
                                 Connection con) throws SQLException {

        //プラグイン設定を取得する
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));

        Prj030Biz biz = new Prj030Biz(con, getRequestModel(req));

        Prj050knParamModel paramMdl = new Prj050knParamModel();
        paramMdl.setParam(form);
        biz.setUsePlugin(paramMdl, pconfig);
        paramMdl.setFormData(form);

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
        Prj050knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        con.setAutoCommit(true);
        //初期表示情報を画面にセットする
        Prj050knBiz biz = new Prj050knBiz(con, getRequestModel(req));

        Prj050knParamModel paramMdl = new Prj050knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, getTempPath(req));
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
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @throws IOException 入出力時例外
     * @throws Exception その他例外
     */
    private ActionForward __doPushRegist(ActionMapping map,
                                          Prj050knForm form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con)
        throws SQLException, IOToolsException, IOException, Exception {

        ActionErrors errors = null;

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //詳細入力チェック
        if (form.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            errors = form.validate050(con, req);

        //簡易入力チェック
        } else {
            errors = form.validate050Easy(req);
        }

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
        Prj050knBiz biz = new Prj050knBiz(con, getRequestModel(req));


        Prj050knParamModel paramMdl = new Prj050knParamModel();
        paramMdl.setParam(form);
        boolean dataexist =
            biz.doAddEdit(
                    paramMdl,
                    cntCon,
                    userSid,
                    getAppRootPath(),
                    getTempPath(req),
                    getPluginConfig(req));
        paramMdl.setFormData(form);

        //テンポラリディレクトリのファイルを削除する
        CommonBiz cmnBiz = new CommonBiz();
        IOTools.deleteDir(cmnBiz.getTempDir(getTempPath(req),
                              GSConstProject.PLUGIN_ID_PROJECT, getRequestModel(req)));

        GsMessage gsMsg = new GsMessage(req);

        //ログ出力処理
        PrjCommonBiz prjBiz = new PrjCommonBiz(con, gsMsg, getRequestModel(req));
        String opCode = "";

        //処理モード
        String cmdMode = form.getPrj050cmdMode();
        if (cmdMode.equals(GSConstProject.CMD_MODE_ADD)) {
            //登録
            opCode = getInterMessage(req, "cmn.entry");

        } else if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
            //更新
            opCode = getInterMessage(req, "cmn.change");
        }

        String todoTitle = "";
        if (form.getPrj050elementKbn() == GSConstProject.DSP_TODO_DETAIL) {
            todoTitle = form.getPrj050title();
        } else {
            todoTitle = form.getPrj050titleEasy();
        }

        prjBiz.outPutLog(
                map, req, res, opCode,
                GSConstLog.LEVEL_TRACE,
                "[name]" + todoTitle);

        //登録・更新完了画面を表示
        return __setKanryoDsp(map, form, req, dataexist);
    }

    /**
     * [機  能] 登録・更新完了画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param dataexist 画面遷移時、対象データがあるか(削除、プロジェクトSID変更済でないか)
     *                  true=データが存在する、false=存在しない
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Prj050knForm form,
        HttpServletRequest req,
        boolean dataexist) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        if (!StringUtil.isNullZeroStringSpace(form.getPrj060schUrl())) {
            //URLを内部リンクに変更
            String url = StringUtilHtml.transToText(form.getPrj060schUrl());
            if (url.indexOf("/" + GSConst.PLUGINID_SCH) != -1) {
                url = url.substring(url.indexOf("/" + GSConst.PLUGINID_SCH));
            } else if (url.indexOf("/" + GSConst.PLUGINID_MAIN) != -1) {
                url = url.substring(url.indexOf("/" + GSConst.PLUGINID_MAIN));
            }
            cmn999Form.setUrlOK(url);
        } else {
            ActionForward forwardOk = setBackForward(map, form, dataexist, req);
            cmn999Form.setUrlOK(forwardOk.getPath());
        }

        MessageResources msgRes = getResources(req);

        String cmdMode = form.getPrj050cmdMode();
        if (cmdMode.equals(GSConstProject.CMD_MODE_ADD)) {
            //登録完了
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object", GSConstProject.MSG_TODO));
        } else if (cmdMode.equals(GSConstProject.CMD_MODE_EDIT)) {
            //更新完了
            cmn999Form.setMessage(
                    msgRes.getMessage("hensyu.kanryo.object", GSConstProject.MSG_TODO));
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
     * @param form Prj050Form
     * @param dataexist 画面遷移時、対象データがあるか(削除、プロジェクトSID変更済でないか)
     *                   true=データが存在する、false=存在しない
     * @param req リクエスト
     * @return ActionForward
     */
    public ActionForward setBackForward(
        ActionMapping map,
        Prj050Form form,
        boolean dataexist,
        HttpServletRequest req) {

        ActionForward forward = null;

        String prj050scrId =
            NullDefault.getString(form.getPrj050scrId(), GSConstProject.SCR_ID_PRJ010);

        if (prj050scrId.equals(GSConstProject.SCR_ID_PRJ010)) {
            //ダッシュボードへ遷移する
            forward = map.findForward(GSConstProject.SCR_INDEX);

        } else if (prj050scrId.equals(GSConstProject.SCR_ID_PRJ060)) {

            String prj060scrId =
                NullDefault.getString(form.getPrj060scrId(), GSConstProject.SCR_ID_PRJ010);

            Prj060Biz biz = new Prj060Biz(getRequestModel(req));

            if (dataexist) {
                //コマンドパラメータ取得
                String cmd = PrjCommonBiz.getCmdProperty(req);
                //データあり
                if (cmd.equals(Prj050Action.CMD_BACK_CLICK)) {
                    //戻るボタン押下時
                    //TODO参照へ遷移する
                    forward = map.findForward(GSConstProject.SCR_TODO_VIEW);

                } else {
                    forward = biz.getActionForward(prj060scrId, map);
                }

            } else {
                //データ削除済みの場合、さらに前の画面へ遷移する
                forward = biz.getActionForward(prj060scrId, map);
            }

        } else if (prj050scrId.equals(GSConstProject.SCR_ID_PRJ070)) {
            //TODO詳細検索へ遷移する
            forward = map.findForward(GSConstProject.SCR_TODO_SEARCH);

        } else if (prj050scrId.equals(GSConstProject.SCR_ID_PRJ030)) {
            //プロジェクトメインへ遷移する
            forward = map.findForward(GSConstProject.SCR_PRJ_MAIN);
        } else if (prj050scrId.equals(GSConstProject.SCR_ID_MAIN)) {
            //メインメニューへ遷移する
            forward = map.findForward(GSConstProject.SCR_MENU);
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
    private ActionForward __doDownLoad(
        ActionMapping map,
        Prj050knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), GSConstProject.PLUGIN_ID_PROJECT, getRequestModel(req));
        String fileId = form.getFileId();

        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(tempDir, fileId.concat(GSConstCommon.ENDSTR_OBJFILE));
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
        //添付ファイル保存用のパスを取得する(フルパス)
        String filePath = tempDir + fileId.concat(GSConstCommon.ENDSTR_SAVEFILE);
        filePath = IOTools.replaceFileSep(filePath);

        GsMessage gsMsg = new GsMessage(req);

        //ログ出力処理
        PrjCommonBiz prjBiz = new PrjCommonBiz(con, gsMsg, getRequestModel(req));
        prjBiz.outPutLog(map, req, res,
                getInterMessage(req, "cmn.download"), GSConstLog.LEVEL_INFO,
                fMdl.getFileName(), fileId);

        //時間のかかる処理の前にコネクションを破棄
        JDBCUtil.closeConnectionAndNull(con);
        //ファイルをダウンロードする
        TempFileUtil.downloadAtachment(req, res, filePath, fMdl.getFileName(), Encoding.UTF_8);

        return null;
    }
}
