package jp.groupsession.v2.sch.sch020;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import jp.groupsession.v2.sch.AbstractScheduleAction;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.pdf.SchGekPdfModel;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] スケジュール 月間画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch020Action extends AbstractScheduleAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch020Action.class);

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

        log__.debug("START_SCH020");
        ActionForward forward = null;
        Sch020Form uform = (Sch020Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        if (cmd.equals("day")) {
            //日間スケジュール
            forward = map.findForward("020_day");
        } else if (cmd.equals("week")) {
            //週間スケジュール
            forward = map.findForward("020_week");
        } else if (cmd.equals("kojin")) {
            //個人週間スケジュール
            forward = map.findForward("020_kojin");
        } else if (cmd.equals("add")) {
            //スケジュール追加
            forward = map.findForward("020_add");
        } else if (cmd.equals("edit")) {
            //スケジュール修正・閲覧
            con.setAutoCommit(true);
            //編集権限チェック
            RequestModel reqMdl = getRequestModel(req);
            Sch010Biz biz = new Sch010Biz(reqMdl);
            if (biz.isEditOk(Integer.parseInt(uform.getSch010SchSid()), reqMdl, con)) {
                forward = map.findForward("020_edit");
            } else {
                forward = map.findForward("020_dsp");
            }
        } else if (cmd.equals("list")) {
            //スケジュール一覧
            forward = map.findForward("020_list");
        } else if (cmd.equals("search")) {
            //一覧画面へ
            forward = map.findForward("020_list");
        } else if (cmd.equals("today")) {
            //今日ボタン
            __doMoveMonth(uform, 0, true);
            __doInit(map, uform, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("move_rm")) {
            //次月移動
            __doMoveMonth(uform, 1, false);
            __doInit(map, uform, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("move_lm")) {
            //前月移動
            __doMoveMonth(uform, -1, false);
            __doInit(map, uform, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("ktool")) {
            //管理者ツール
            forward = map.findForward("ktool");
        } else if (cmd.equals("pset")) {
            //個人設定
            forward = map.findForward("pset");
        } else if (cmd.equals("reload")) {
            //再読込
            __doInit(map, uform, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("import")) {
            //スケジュールインポート
            forward = map.findForward("020_imp");
        } else if (cmd.equals("pdf")) {
            //スケジュールPDF出力
            log__.debug("月間スケジュールＰＤＦファイルダウンロード");
            __doInit(map, uform, req, res, con);
            forward = __doDownLoadPdf(map, uform, req , res, con);
        } else {
            //スケジュール月間表示
            __doInit(map, uform, req, res, con);
            forward = map.getInputForward();
        }
        log__.debug("END_SCH020");
        return forward;
    }

    /**
     * <br>初期表処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doInit(ActionMapping map, Sch020Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        con.setAutoCommit(true);

        //プラグイン設定を取得する
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));

        Sch020Biz biz = new Sch020Biz(con, pconfig, getRequestModel(req));
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //マイグループにメンバーが存在チェック
        Sch020ParamModel paramMdl = new Sch020ParamModel();
        paramMdl.setParam(form);
        ActionErrors errors = biz.validateGroupMemberExistCheck(paramMdl, sessionUsrSid);
        paramMdl.setFormData(form);

        if (errors.size() > 0) {
            log__.debug("入力エラー");
            addErrors(req, errors);
        }

        paramMdl = new Sch020ParamModel();
        paramMdl.setParam(form);
        biz.getInitData(paramMdl);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);
    }

    /**
     * <br>表示日付の移動を行います
     * @param form アクションフォーム
     * @param moveMonth 移動月数
     * @param today 今日へ移動
     */
    private void __doMoveMonth(Sch020Form form, int moveMonth, boolean today) {
        String dspDate = "";
        if (today) {
            dspDate = new UDate().getDateString();
        } else {
            dspDate = NullDefault.getString(
                    form.getSch010DspDate(), new UDate().getDateString());
        }

        UDate udate = new UDate();
        udate.setDate(dspDate);
        UDate rsDate = udate.cloneUDate();
        rsDate = UDateUtil.addMonthLastDay(rsDate, moveMonth);

        int iSYear = rsDate.getYear();
        int iSMonth = rsDate.getMonth();
        int iSDay = udate.getIntDay();
        rsDate.setDay(udate.getIntDay());
        //日付論理エラーの場合、月末日を設定
        if (rsDate.getYear() != iSYear
         || rsDate.getMonth() != iSMonth
         || rsDate.getIntDay() != iSDay) {
            rsDate = udate.cloneUDate();
            rsDate = UDateUtil.addMonthLastDay(rsDate, moveMonth);
        }
        form.setSch010DspDate(rsDate.getDateString());
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
            Sch020Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con)
        throws SQLException, Exception {

        log__.debug("月間スケジュールＰＤＦファイルダウンロード処理");
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
    private ActionForward __createPdf(ActionMapping map, Sch020Form form,
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
                + "expgekpdf/");

        Sch020Biz biz = new Sch020Biz(con, getRequestModel(req));
        String dateStr = form.getSch010DspDate();
        String tmpFileName =  dateStr.concat(GSConstCommon.ENDSTR_SAVEFILE);

        Sch020ParamModel paramMdl = new Sch020ParamModel();
        paramMdl.setParam(form);
        SchGekPdfModel pdfMdl = biz.createSchGekPdf(
                paramMdl, con, appRootPath, outTempDir, tmpFileName, getRequestModel(req));
        paramMdl.setFormData(form);

        String outBookName = pdfMdl.getFileName();

        String outFilePath = IOTools.setEndPathChar(outTempDir) + tmpFileName;
        TempFileUtil.downloadAtachment(req, res, outFilePath, outBookName, Encoding.UTF_8);
        //TEMPディレクトリ削除
        IOTools.deleteDir(IOTools.setEndPathChar(outTempDir));
        GsMessage gsMsg = new GsMessage();
        String downloadPdf = gsMsg.getMessage(req, "cmn.pdf");
        //ログ出力処理
        SchCommonBiz schBiz = new SchCommonBiz(con, getRequestModel(req));
        String logCode = "月間 PDF出力 grpSid：" + form.getSch010DspGpSid()
                + "、usrSid：" + form.getSch020SelectUsrSid();
        schBiz.outPutLog(map, req, res, downloadPdf, GSConstLog.LEVEL_INFO, outBookName, logCode);

        return null;
    }
}
