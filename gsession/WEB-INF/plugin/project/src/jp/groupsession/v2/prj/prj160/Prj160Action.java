package jp.groupsession.v2.prj.prj160;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.prj.AbstractProjectAction;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.prj160kn.Prj160knAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] プロジェクト管理 TODOインポート画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj160Action extends AbstractProjectAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj160Action.class);
    /** CMD:ダウンロードリンククリック */
    public static final String CMD_CSV_DOWNLOAD = "csvDownLoad";
    /** CMD:インポートボタンクリック */
    public static final String CMD_IMP_CLICK = "csvImport";
    /** CMD:戻るボタンクリック */
    public static final String CMD_BACK_CLICK = "backFromCsvImport";
    /** CMD:(確認画面で)戻るボタンクリック */
    public static final String CMD_BACK_CLICK_KN = Prj160knAction.CMD_BACK_CLICK;

    /**
     * <br>[機  能] キャッシュを有効にして良いか判定を行う
     * <br>[解  説] ダウンロード時のみ有効にする
     * <br>[備  考]
     *
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:有効にする,false:無効にする
     */
    public boolean isCacheOk(HttpServletRequest req, ActionForm form) {

        //コマンドパラメータ取得
        String cmd = PrjCommonBiz.getCmdProperty(req);

        //ダウンロードフラグ
        String downLoadFlg = NullDefault.getString(req.getParameter("sample"), "");
        downLoadFlg = downLoadFlg.trim();

        if (cmd.equals(CMD_CSV_DOWNLOAD)) {
            if (downLoadFlg.equals("1")) {
                log__.debug("サンプルCSVファイルダウンロード");
                return true;
            }
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
    public ActionForward executeAction(ActionMapping map,
                                        ActionForm form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con) throws Exception {

        ActionForward forward = null;
        Prj160Form thisForm = (Prj160Form) form;

        //コマンドパラメータ取得
        String cmd = PrjCommonBiz.getCmdProperty(req);

        if (CMD_CSV_DOWNLOAD.equals(cmd)) {
            log__.debug("サンプルcsvファイルダウンロード");
            __doSampleDownLoad(req, res);

            //ログ出力処理
            GsMessage gsMsg = new GsMessage(req);
            PrjCommonBiz prjBiz = new PrjCommonBiz(con, gsMsg, getRequestModel(req));
            prjBiz.outPutLog(
                    map, req, res,
                    getInterMessage(req, "cmn.download"),
                    GSConstLog.LEVEL_INFO,
                    GSConstProject.TODO_CSV_FILENAME);

        } else if (CMD_BACK_CLICK.equals(cmd)) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward(CMD_BACK_CLICK);

        } else if (CMD_IMP_CLICK.equals(cmd)) {
            log__.debug("インポートボタンクリック");
            forward = __doImport(map, thisForm, req, res, con);

        } else if ("projectChange".equals(cmd)) {
            log__.debug("プロジェクトコンボ変更");
            forward = __doDsp(map, thisForm, req, res, con);

        } else if (CMD_BACK_CLICK_KN.equals(cmd)) {
            log__.debug("確認画面からの戻り");
            forward = __doDsp(map, thisForm, req, res, con);

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
     * @throws SQLException SQL実行例外
     * @return ActionForward
     * @throws IOToolsException 取込みファイル操作時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Prj160Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
            cmnBiz.getTempDir(getTempPath(req),
                GSConstProject.PLUGIN_ID_PROJECT, getRequestModel(req));

        Prj160Biz biz = new Prj160Biz(getRequestModel(req), con);

        //テンポラリディレクトリのファイル削除を行う
        biz.doDeleteFile(tempDir);

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 再表示を行う
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
    private ActionForward __doDsp(ActionMapping map,
                                   Prj160Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con)
        throws SQLException, IOToolsException {

        con.setAutoCommit(true);
        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
            cmnBiz.getTempDir(getTempPath(req),
                    GSConstProject.PLUGIN_ID_PROJECT, getRequestModel(req));

        //表示情報を画面にセットする
        Prj160Biz biz = new Prj160Biz(getRequestModel(req), con);

        Prj160ParamModel paramMdl = new Prj160ParamModel();
        paramMdl.setParam(form);
        biz.setDspData(paramMdl, tempDir);
        paramMdl.setFormData(form);

        //トランザクショントークン設定
        this.saveToken(req);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] インポートボタン押下時処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
    * @throws Exception インポート処理時例外
     */
    private ActionForward __doImport(ActionMapping map,
                                      Prj160Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws Exception {

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

        return map.findForward("doImport");
    }

    /**
     * <br>[機  能] サンプルCSVをダウンロード
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param req リクエスト
     * @param res レスポンス
     * @throws Exception ダウンロード時例外
     */
    private void __doSampleDownLoad(HttpServletRequest req, HttpServletResponse res)
        throws Exception {

        String fileName = GSConstProject.TODO_CSV_FILENAME;

        StringBuilder buf = new StringBuilder();
        buf.append(getAppRootPath());
        buf.append(File.separator);
        buf.append(GSConstProject.PLUGIN_ID_PROJECT);
        buf.append(File.separator);
        buf.append("doc");
        buf.append(File.separator);
        buf.append(fileName);
        String fullPath = buf.toString();
        TempFileUtil.downloadAtachment(req, res, fullPath, fileName, Encoding.UTF_8);
    }
}