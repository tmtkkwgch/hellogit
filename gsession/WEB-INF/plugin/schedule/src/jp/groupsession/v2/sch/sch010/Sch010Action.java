package jp.groupsession.v2.sch.sch010;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrInoutModel;
import jp.groupsession.v2.sch.AbstractScheduleAction;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.pdf.SchSyuPdfModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.sml010.Sml010Form;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.zsk.biz.ZsjCommonBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] スケジュール 週間画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch010Action extends AbstractScheduleAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch010Action.class);

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

        if (cmd.equals("pdf")) {
            log__.debug("PDFファイルダウンロード");
            return true;
        }
        return false;
    }

    /**
     * <br>アクション実行
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        log__.debug("START_SCH010");
        ActionForward forward = null;
        Sch010Form thisForm = (Sch010Form) form;
        __setCanUsePluginFlg(thisForm, req, con);
        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        if (cmd.equals("kojin")) {
            //個人週間スケジュール
            forward = map.findForward("kojin");
        } else if (cmd.equals("day")) {
            //日間スケジュール
            forward = map.findForward("day");
        } else if (cmd.equals("month")) {
            //月間スケジュール
            forward = map.findForward("month");
        } else if (cmd.equals("list")) {
            //一覧スケジュール
            forward = map.findForward("list");
        } else if (cmd.equals("add")) {
            //スケジュール追加
            forward = map.findForward("add");
        } else if (cmd.equals("edit")) {
            con.setAutoCommit(true);
            //スケジュール修正・閲覧
            //編集権限チェック
            RequestModel reqMdl = getRequestModel(req);
            Sch010Biz biz = new Sch010Biz(reqMdl,
                    getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req)));
            if (biz.isEditOk(Integer.parseInt(thisForm.getSch010SchSid()), reqMdl, con)) {
                forward = map.findForward("edit");
            } else {
                forward = map.findForward("dsp");
            }

        } else if (cmd.equals("today")) {
            //今日ボタン
            __doMoveDays(thisForm, 0, true);
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("move_rd")) {
            //次日移動
            __doMoveDays(thisForm, 1, false);
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("move_ld")) {
            //前日移動
            __doMoveDays(thisForm, -1, false);
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("move_rw")) {
            //次週移動
            __doMoveDays(thisForm, 7, false);
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("move_lw")) {
            //前週移動
            __doMoveDays(thisForm, -7, false);
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("msg")) {
            //ショートメール
            __doCreateMsg(map, thisForm, req, res, con);
            forward = map.findForward("msg");
        } else if (cmd.equals("ktool")) {
            //管理者ツール
            forward = map.findForward("ktool");
        } else if (cmd.equals("pset")) {
            //個人設定
            forward = map.findForward("pset");
        } else if (cmd.equals("sch010Zaiseki")) {
            //在席
            __doZaiseki(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("sch010Fuzai")) {
            //不在
            __doFuzai(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("sch010Sonota")) {
            //その他
            __doSonota(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("search")) {
            //一覧画面へ
            forward = map.findForward("list");
        } else if (cmd.equals("reload")) {
            //再読込
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("import")) {
            //スケジュールインポート
            forward = map.findForward("imp");
        } else if (cmd.equals("pdf")) {
            //スケジュールPDF出力
            log__.debug("週間スケジュールＰＤＦファイルダウンロード");
            __doInit(map, thisForm, req , res, con);
            forward = __doDownLoadPdf(map, thisForm, req, res, con);
        } else {
            //スケジュール週間初期表示
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        }
        log__.debug("END_SCH010");
        return forward;
    }

    /**
     * <br>初期表示処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doInit(
            ActionMapping map,
            Sch010Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con)
    throws SQLException {
        con.setAutoCommit(true);

        RequestModel reqMdl = getRequestModel(req);
        Sch010Biz biz = new Sch010Biz(reqMdl,
                getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req)));

        Sch010ParamModel paramMdl = new Sch010ParamModel();
        paramMdl.setParam(form);
        biz.getInitData(paramMdl, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);
    }

    /**
     * <br>在席にする処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doZaiseki(ActionMapping map, Sch010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        boolean commitFlg = false;
        con.setAutoCommit(false);

        UDate now = new UDate();
        BaseUserModel umodel = getSessionUserModel(req);
        CmnUsrInoutModel param = new CmnUsrInoutModel();

        int uid = NullDefault.getInt(form.getSch010SelectUsrSid(), -1);
        if (uid != -1) {
            param.setUioSid(uid);
            param.setUioStatus(GSConst.UIOSTS_IN);
            param.setUioBiko(null);
            param.setUioAuid(umodel.getUsrsid());
            param.setUioAdate(now);
            param.setUioEuid(umodel.getUsrsid());
            param.setUioEdate(now);
            try {
                ZsjCommonBiz zbiz = new ZsjCommonBiz(getRequestModel(req));
                zbiz.updateZskStatus(con, param);
                commitFlg = true;
           } catch (SQLException e) {
               log__.error("在席状況の更新に失敗しました。");
               throw e;
           } finally {
               if (commitFlg) {
                   con.commit();
               } else {
                   con.rollback();
               }
           }
        }
        form.setSch010SelectUsrSid("");
        __doInit(map, form, req, res, con);
    }

    /**
     * <br>不在にする処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doFuzai(ActionMapping map, Sch010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        boolean commitFlg = false;
        con.setAutoCommit(false);

        UDate now = new UDate();
        BaseUserModel umodel = getSessionUserModel(req);
        CmnUsrInoutModel param = new CmnUsrInoutModel();
        int uid = NullDefault.getInt(form.getSch010SelectUsrSid(), -1);
        if (uid != -1) {
            param.setUioSid(uid);
            param.setUioStatus(GSConst.UIOSTS_LEAVE);
            param.setUioBiko(null);
            param.setUioAuid(umodel.getUsrsid());
            param.setUioAdate(now);
            param.setUioEuid(umodel.getUsrsid());
            param.setUioEdate(now);
            try {
                ZsjCommonBiz zbiz = new ZsjCommonBiz(getRequestModel(req));
                zbiz.updateZskStatus(con, param);
                commitFlg = true;
           } catch (SQLException e) {
               log__.error("在席状況の更新に失敗しました。");
               throw e;
           } finally {
               if (commitFlg) {
                   con.commit();
               } else {
                   con.rollback();
               }
           }
        }

        form.setSch010SelectUsrSid("");
        __doInit(map, form, req, res, con);
    }

    /**
     * <br>その他にする処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doSonota(ActionMapping map, Sch010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        boolean commitFlg = false;
        con.setAutoCommit(false);

        UDate now = new UDate();
        BaseUserModel umodel = getSessionUserModel(req);
        CmnUsrInoutModel param = new CmnUsrInoutModel();

        int uid = NullDefault.getInt(form.getSch010SelectUsrSid(), -1);
        if (uid != -1) {
            param.setUioSid(uid);
            param.setUioStatus(GSConst.UIOSTS_ETC);
            param.setUioBiko(null);
            param.setUioAuid(umodel.getUsrsid());
            param.setUioAdate(now);
            param.setUioEuid(umodel.getUsrsid());
            param.setUioEdate(now);
            try {
                ZsjCommonBiz zbiz = new ZsjCommonBiz(getRequestModel(req));
                zbiz.updateZskStatus(con, param);
                commitFlg = true;
           } catch (SQLException e) {
               log__.error("在席状況の更新に失敗しました。");
               throw e;
           } finally {
               if (commitFlg) {
                   con.commit();
               } else {
                   con.rollback();
               }
           }
        }

        form.setSch010SelectUsrSid("");
        __doInit(map, form, req, res, con);
    }


    /**
     * <br>表示日付の移動を行います
     * @param form アクションフォーム
     * @param moveDay 移動日数
     * @param today 今日へ移動
     * @throws SQLException SQL実行例外
     */
    private void __doMoveDays(
            Sch010Form form,
            int moveDay,
            boolean today) throws SQLException {

        String dspDate = "";
        if (today) {
            dspDate = new UDate().getDateString();
        } else {
            dspDate = NullDefault.getString(
                    form.getSch010DspDate(), new UDate().getDateString());
        }

        UDate udate = new UDate();
        udate.setDate(dspDate);
        udate.addDay(moveDay);
        form.setChangeDateFlg(1);
        form.setSch010DspDate(udate.getDateString());
    }

    /**
     * <br>メッセンジャー処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doCreateMsg(ActionMapping map, Sch010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        log__.debug("週間==>メッセンジャー");
        //パラメータ取得
        String selectUserSid = form.getSch010SelectUsrSid();

        Sml010Form msgForm = new Sml010Form();
        msgForm.setSml010scriptFlg(GSConstSmail.SCRIPT_FIG_TRUE);
        msgForm.setSml010scriptKbn(GSConstSmail.SCRIPT_CREATE_MAIL);
        msgForm.setSml010scriptSelUsrSid(selectUserSid);

        req.setAttribute("sml010Form", msgForm);

    }

    /**
     * 在席管理・ショートメールプラグインが利用可能かフォームへ設定する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param form フォーム
     * @param req リクエスト
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setCanUsePluginFlg(Sch010Form form, HttpServletRequest req, Connection con)
    throws SQLException {
        //プラグイン設定を取得する
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));
        CommonBiz cmnBiz = new CommonBiz();
        //在席管理は利用可能か判定
        if (cmnBiz.isCanUsePlugin(GSConstSchedule.PLUGIN_ID_ZAISEKI, pconfig)) {
            form.setZaisekiUseOk(GSConstSchedule.PLUGIN_USE);
        } else {
            form.setZaisekiUseOk(GSConstSchedule.PLUGIN_NOT_USE);
        }
        //ショートメールは利用可能か判定
        if (cmnBiz.isCanUsePlugin(GSConstSchedule.PLUGIN_ID_SMAIL, pconfig)) {
            form.setSmailUseOk(GSConstSchedule.PLUGIN_USE);
        } else {
            form.setSmailUseOk(GSConstSchedule.PLUGIN_NOT_USE);
        }
    }

    /**
     * <br>[機  能] PDFファイルダウンロード処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException SQL実行例外
     * @throws Exception 実行時例外
     */
    private ActionForward __doDownLoadPdf(ActionMapping map,
            Sch010Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, Exception {

        log__.debug("週間スケジュールＰＤＦファイルダウンロード処理");
        ActionForward forward = null;

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
                cmnBiz.getTempDir(
                        getTempPath(req), GSConstSchedule.PLUGIN_ID_SCHEDULE, getRequestModel(req));

        //ディレクトリの作成
        File tmpDir = new File(tempDir);
        tmpDir.mkdirs();
        forward = __createPdf(map, form, req, res, con, tempDir);

        return forward;
    }

    /**
     * <br>[機  能] PDFファイルダウンロード処理を実行
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param outDir 出力先ディレクトリ
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     * @throws IOException ファイルの書き出しに失敗
     * @throws IOToolsException テンポラリディレクトリの削除に失敗
     * @throws TempFileException 添付ファイル情報の取得に失敗
     * @throws Exception 実行例外
     */
    private ActionForward __createPdf(ActionMapping map, Sch010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con, String outDir)
            throws SQLException, IOException, IOToolsException, TempFileException, Exception {

        log__.debug("ファイルダウンロード処理(PDF)");
        //アプリケーションルートパス取得
        String appRootPath = getAppRootPath();
        //プラグイン固有のテンポラリパス取得
        CommonBiz cmnBiz = new CommonBiz();
        String outTempDir = IOTools.replaceFileSep(
                cmnBiz.getTempDir(
                        getTempPath(req), GSConstSchedule.PLUGIN_ID_SCHEDULE, getRequestModel(req))
                + "expsyupdf/");

        RequestModel reqMdl = getRequestModel(req);
        Sch010Biz biz = new Sch010Biz(reqMdl);
        String dateStr = form.getSch010DspDate();
        String tmpFileName =  dateStr.concat(GSConstCommon.ENDSTR_SAVEFILE);
        Sch010ParamModel paramMdl = new Sch010ParamModel();
        paramMdl.setParam(form);
        SchSyuPdfModel pdfMdl = biz.createSchSyuPdf(paramMdl, con, appRootPath
                , outTempDir, tmpFileName);
        paramMdl.setFormData(form);

        String outBookName = pdfMdl.getFileName();
        String outFilePath = IOTools.setEndPathChar(outTempDir)
                + tmpFileName;
        TempFileUtil.downloadAtachment(req, res, outFilePath, outBookName, Encoding.UTF_8);
        //TEMPディレクトリ削除
        IOTools.deleteDir(IOTools.setEndPathChar(outTempDir));
        GsMessage gsMsg = new GsMessage();
        String downloadPdf = gsMsg.getMessage(req, "cmn.pdf");
        //ログ出力処理
        SchCommonBiz schBiz = new SchCommonBiz(con, reqMdl);
        String logCode = "週間 PDF出力 grpSid ：" + form.getSch010DspGpSid();
        schBiz.outPutLog(map, req, res, downloadPdf, GSConstLog.LEVEL_INFO, outBookName, logCode);

        return null;
    }
}
