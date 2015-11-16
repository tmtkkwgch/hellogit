package jp.groupsession.v2.rsv.rsv180;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveAction;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 施設予約 施設インポート画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv180Action extends AbstractReserveAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv180Action.class);

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

        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        //ダウンロードフラグ
        String downLoadFlg = NullDefault.getString(req.getParameter("sample"), "");
        downLoadFlg = downLoadFlg.trim();

        if (cmd.equals("rsv180_sample")) {
            if (downLoadFlg.equals("1")) {
                log__.debug("サンプルCSVファイルダウンロード");
                return true;
            }
        }
        return false;
    }

    /**
     * <br>[機  能] アクションを実行する
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form ActionForm
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     * @see jp.co.sjts.util.struts.AbstractAction
     * @see #executeAction(org.apache.struts.action.ActionMapping,
     *                      org.apache.struts.action.ActionForm,
     *                      javax.servlet.http.HttpServletRequest,
     *                      javax.servlet.http.HttpServletResponse,
     *                      java.sql.Connection)
     */
     public ActionForward executeAction(ActionMapping map,
                                         ActionForm form,
                                         HttpServletRequest req,
                                         HttpServletResponse res,
                                         Connection con) throws Exception {

         ActionForward forward = null;
         Rsv180Form rsvform = (Rsv180Form) form;

         //コマンドパラメータ取得
         String cmd = NullDefault.getString(req.getParameter("CMD"), "");
         cmd = cmd.trim();

         //インポートボタン押下
         if (cmd.equals("import")) {
             log__.debug("インポートボタン押下");
             forward = __doImportCheck(map, rsvform, req, res, con);
         //サンプルダウンロードリンククリック
         } else if (cmd.equals("rsv180_sample")) {
             log__.debug("サンプルダウンロードリンククリック");
             __doSampleDownLoad(map, req, res, con);
         //戻るボタン押下
         } else if (cmd.equals("back_to_sisetu_zyoho")) {
             log__.debug("戻るボタン押下");
             forward = map.findForward("back_to_sisetu_zyoho");
         //確認で戻るボタン押下
         } else if (cmd.equals("back_to_import_input")) {
             log__.debug("確認画面で戻るボタン押下");
             forward = __doDsp(map, rsvform, req, res, con);
         //初期表示処理
         } else {
             log__.debug("初期表示処理");
             forward = __doInit(map, rsvform, req, res, con);
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
                                     Rsv180Form form,
                                     HttpServletRequest req,
                                     HttpServletResponse res,
                                     Connection con)
         throws SQLException, IOToolsException {

         RequestModel reqMdl = getRequestModel(req);

         con.setAutoCommit(true);
         Rsv180Biz biz = new Rsv180Biz(reqMdl, con);

         boolean processFlg = false;

         Rsv180ParamModel paramMdl = new Rsv180ParamModel();
         paramMdl.setParam(form);
         processFlg = biz.isPossibleToProcess(paramMdl);
         paramMdl.setFormData(form);

         //処理権限判定
         if (!processFlg) {
             //処理権限無し
             return getSubmitErrorPage(map, req);
         }

         //テンポラリディレクトリパスを取得
         CommonBiz cmnBiz = new CommonBiz();
         String tempDir =
             cmnBiz.getTempDir(getTempPath(req),
                 GSConstReserve.PLUGIN_ID_RESERVE, reqMdl);

         //テンポラリディレクトリのファイル削除を行う
         biz.doDeleteFile(tempDir);
         con.setAutoCommit(false);
         return __doDsp(map, form, req, res, con);
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
     private ActionForward __doImportCheck(ActionMapping map,
                                            Rsv180Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
         throws Exception {

         //テンポラリディレクトリパスを取得
         CommonBiz cmnBiz = new CommonBiz();
         String tempDir =
             cmnBiz.getTempDir(getTempPath(req),
                 GSConstReserve.PLUGIN_ID_RESERVE, getRequestModel(req));

         ActionErrors errors = form.validateCheck(map, req, tempDir, con);
         if (!errors.isEmpty()) {
             addErrors(req, errors);
             return __doDsp(map, form, req, res, con);
         }

         return map.findForward("doImport");
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
                                    Rsv180Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
         throws SQLException, IOToolsException {

         RequestModel reqMdl = getRequestModel(req);

         con.setAutoCommit(true);
         //テンポラリディレクトリパスを取得
         CommonBiz cmnBiz = new CommonBiz();
         String tempDir =
             cmnBiz.getTempDir(getTempPath(req),
                     GSConstReserve.PLUGIN_ID_RESERVE, reqMdl);

         //初期表示情報を画面にセットする
         Rsv180Biz biz = new Rsv180Biz(reqMdl, con);

         Rsv180ParamModel paramMdl = new Rsv180ParamModel();
         paramMdl.setParam(form);
         biz.setInitData(paramMdl, tempDir);
         paramMdl.setFormData(form);

         //トランザクショントークン設定
         this.saveToken(req);
         con.setAutoCommit(false);
         return map.getInputForward();
     }

     /**
      * <br>[機  能] サンプルCSVをダウンロード
      * <br>[解  説]
      * <br>[備  考]
      * @param map マップ
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @throws Exception ダウンロード時例外
      */
     private void __doSampleDownLoad(
             ActionMapping map, HttpServletRequest req, HttpServletResponse res, Connection con)
         throws Exception {

         String fileName = "";
         String fileKbn = NullDefault.getString(req.getParameter("kbn"), "");

         if (fileKbn.equals(String.valueOf(GSConstReserve.RSK_KBN_HEYA))) {
             fileName = GSConstReserve.SAMPLE_HEYA_CSV_NAME;
         } else if (fileKbn.equals(String.valueOf(GSConstReserve.RSK_KBN_BUPPIN))) {
             fileName = GSConstReserve.SAMPLE_BUPPIN_CSV_NAME;
         } else if (fileKbn.equals(String.valueOf(GSConstReserve.RSK_KBN_CAR))) {
             fileName = GSConstReserve.SAMPLE_CAR_CSV_NAME;
         } else if (fileKbn.equals(String.valueOf(GSConstReserve.RSK_KBN_BOOK))) {
             fileName = GSConstReserve.SAMPLE_BOOK_CSV_NAME;
         } else if (fileKbn.equals(String.valueOf(GSConstReserve.RSK_KBN_OTHER))) {
             fileName = GSConstReserve.SAMPLE_OTHER_CSV_NAME;
         }

         StringBuilder buf = new StringBuilder();
         buf.append(getAppRootPath());
         buf.append(File.separator);
         buf.append(GSConstReserve.PLUGIN_ID_RESERVE);
         buf.append(File.separator);
         buf.append("doc");
         buf.append(File.separator);
         buf.append(fileName);
         String fullPath = buf.toString();
         TempFileUtil.downloadAtachment(req, res, fullPath, fileName, Encoding.UTF_8);

         //ログ出力処理
         GsMessage gsMsg = new GsMessage();
         AbstractReserveBiz rsvBiz = new AbstractReserveBiz(con);
         rsvBiz.outPutLog(map, req, res,
                 gsMsg.getMessage(req, "cmn.download"), GSConstLog.LEVEL_INFO, fileName);
     }
}