package jp.groupsession.v2.rsv.rsv250kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
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
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveAction;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv250.Rsv250Biz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 施設予約 管理者設定 施設予約インポート確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv250knAction extends AbstractReserveAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv250knAction.class);

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        return false;
    }

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

        if (cmd.equals("downLoad")) {
            log__.debug("取り込みCSVファイルダウンロード");
                return true;
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
         Rsv250knForm rsv250knform = (Rsv250knForm) form;

         //コマンドパラメータ取得
         String cmd = NullDefault.getString(req.getParameter("CMD"), "");
         cmd = cmd.trim();

         //インポートボタン押下
         if (cmd.equals("doImport")) {
             log__.debug("インポートボタン押下");
             forward = __doImport(map, rsv250knform, req, res, con);
         //戻るボタン押下
         } else if (cmd.equals("back_to_import_input")) {
             log__.debug("戻るボタン押下");
             forward = map.findForward("back_to_import_input");
         //添付ダウンロード
         } else if (cmd.equals("downLoad")) {
             forward = __doDownLoad(map, rsv250knform, req, res, con);
         //初期表示処理
         } else {
             log__.debug("初期表示処理");
             forward = __doInit(map, rsv250knform, req, res, con);
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
     * @throws Exception CSV情報取得時例外
      */
     private ActionForward __doInit(ActionMapping map,
                                     Rsv250knForm form,
                                     HttpServletRequest req,
                                     HttpServletResponse res,
                                     Connection con)
         throws Exception {
         con.setAutoCommit(true);
         Rsv250knBiz biz = new Rsv250knBiz(getRequestModel(req), con);

         //テンポラリディレクトリパスを取得
         CommonBiz cmnBiz = new CommonBiz();
         String tempDir =
             cmnBiz.getTempDir(
                     getTempPath(req),
                     GSConstReserve.PLUGIN_ID_RESERVE,
                     getRequestModel(req));

         //再入力チェック
         ActionErrors errors = form.validateCheck(map, getRequestModel(req), tempDir, con);
         if (errors.size() > 0) {
             addErrors(req, errors);
             return map.getInputForward();
         }

         //取込みファイル名称取得
         Rsv250knParamModel paramMdl = new Rsv250knParamModel();
         paramMdl.setParam(form);
         biz.setImportFileName(paramMdl, tempDir);
         paramMdl.setFormData(form);

         con.setAutoCommit(false);
         return map.getInputForward();
     }

     /**
      * <br>[機  能] インポート実行
      * <br>[解  説]
      * <br>[備  考]
      *
      * @param map アクションマッピング
      * @param form アクションフォーム
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @throws Exception 実行例外
      * @return アクションフォーワード
      */
     private ActionForward __doImport(ActionMapping map,
                                       Rsv250knForm form,
                                       HttpServletRequest req,
                                       HttpServletResponse res,
                                       Connection con)
         throws Exception {

         if (!isTokenValid(req, true)) {
             log__.info("２重投稿");
             return getSubmitErrorPage(map, req);
         }

         //テンポラリディレクトリパスを取得
         CommonBiz cmnBiz = new CommonBiz();
         String tempDir =
             cmnBiz.getTempDir(
                     getTempPath(req),
                     GSConstReserve.PLUGIN_ID_RESERVE,
                     getRequestModel(req));

         //再入力チェック
         ActionErrors errors = form.validateCheck(map, getRequestModel(req), tempDir, con);
         if (errors.size() > 0) {
             log__.debug("取込み前再チェックエラー");
             addErrors(req, errors);
             return map.getInputForward();
         }

         //取込み処理
         con.setAutoCommit(false);
         boolean commit = false;

         try {

             //セッションユーザSID取得
             BaseUserModel umodel = getSessionUserModel(req);
             int userSid = umodel.getUsrsid();

             //システム日付取得
             UDate now = new UDate();

             //採番用コネクション取得
             MlCountMtController cntCon = getCountMtController(req);

             //インポート
             RequestModel reqMdl = getRequestModel(req);
             PluginConfig pconfig = getPluginConfigForMain(getPluginConfig(req), con);
             RsvImportCsv imp = new RsvImportCsv(
                           con, reqMdl, userSid, now, cntCon, pconfig,
                           getPluginConfig(req), getAppRootPath());
             long num = imp.importCsv(tempDir);

             //ログ出力処理
             GsMessage gsMsg = new GsMessage();
             AbstractReserveBiz rsvBiz = new AbstractReserveBiz(con);
             rsvBiz.outPutLog(map, req, res,
                     gsMsg.getMessage(req, "cmn.import"),
                     GSConstLog.LEVEL_INFO, "[count]" + (num - 1));

             commit = true;

         } catch (Exception e) {
             log__.error("施設予約CSVの取り込みに失敗しました。" + e);
             throw e;
         } finally {

             //テンポラリディレクトリのファイル削除を行う
             Rsv250Biz inpbiz = new Rsv250Biz(getRequestModel(req), con);
             inpbiz.doDeleteFile(tempDir);

             if (commit) {
                 con.commit();
             } else {
                 JDBCUtil.rollback(con);
             }
         }

         //完了画面遷移
         return __doImportComp(map, form, req, res, con);

     }

     /**
      * <br>[機  能] 施設インポート完了後の画面遷移設定
      * <br>[解  説]
      * <br>[備  考]
      *
      * @param map マップ
      * @param form フォーム
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @return ActionForward フォワード
      * @throws Exception 実行時例外
      */
     private ActionForward __doImportComp(ActionMapping map,
                                           Rsv250knForm form,
                                           HttpServletRequest req,
                                           HttpServletResponse res,
                                           Connection con) throws Exception {

         Cmn999Form cmn999Form = new Cmn999Form();
         cmn999Form.setType(Cmn999Form.TYPE_OK);
         cmn999Form.setIcon(Cmn999Form.ICON_INFO);
         cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

         //OKボタンクリック時遷移先
         ActionForward forwardOk = map.findForward("importComp");
         cmn999Form.setUrlOK(forwardOk.getPath());
         MessageResources msgRes = getResources(req);
         GsMessage gsMsg = new GsMessage();
         cmn999Form.setMessage(
                 msgRes.getMessage("touroku.kanryo.object",
                                    gsMsg.getMessage(req, "reserve.src.45")));

         //画面パラメータをセット
         cmn999Form.addHiddenParam("cmd", "ok");

         //パラメータ
         cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
         cmn999Form.addHiddenParam("rsvBackPgId", form.getRsvBackPgId());
         cmn999Form.addHiddenParam("rsvDspFrom", form.getRsvDspFrom());
         cmn999Form.addHiddenParam("rsvSelectedGrpSid", form.getRsvSelectedGrpSid());
         cmn999Form.addHiddenParam("rsvSelectedSisetuSid", form.getRsvSelectedSisetuSid());
         cmn999Form.addHiddenParam("rsv100InitFlg", String.valueOf(form.isRsv100InitFlg()));
         cmn999Form.addHiddenParam("rsv100SearchFlg", String.valueOf(form.isRsv100SearchFlg()));
         cmn999Form.addHiddenParam("rsv100SortKey", form.getRsv100SortKey());
         cmn999Form.addHiddenParam("rsv100OrderKey", form.getRsv100OrderKey());
         cmn999Form.addHiddenParam("rsv100PageTop", form.getRsv100PageTop());
         cmn999Form.addHiddenParam("rsv100PageBottom", form.getRsv100PageBottom());
         cmn999Form.addHiddenParam("rsv100selectedFromYear", form.getRsv100selectedFromYear());
         cmn999Form.addHiddenParam("rsv100selectedFromMonth", form.getRsv100selectedFromMonth());
         cmn999Form.addHiddenParam("rsv100selectedFromDay", form.getRsv100selectedFromDay());
         cmn999Form.addHiddenParam("rsv100selectedToYear", form.getRsv100selectedToYear());
         cmn999Form.addHiddenParam("rsv100selectedToMonth", form.getRsv100selectedToMonth());
         cmn999Form.addHiddenParam("rsv100selectedToDay", form.getRsv100selectedToDay());
         cmn999Form.addHiddenParam("rsv100KeyWord", form.getRsv100KeyWord());
         cmn999Form.addHiddenParam("rsv100SearchCondition", form.getRsv100SearchCondition());
         cmn999Form.addHiddenParam("rsv100TargetMok", form.getRsv100TargetMok());
         cmn999Form.addHiddenParam("rsv100TargetNiyo", form.getRsv100TargetNiyo());
         cmn999Form.addHiddenParam("rsv100CsvOutField", form.getRsv100CsvOutField());
         cmn999Form.addHiddenParam("rsv100SelectedKey1", form.getRsv100SelectedKey1());
         cmn999Form.addHiddenParam("rsv100SelectedKey2", form.getRsv100SelectedKey2());
         cmn999Form.addHiddenParam("rsv100SelectedKey1Sort", form.getRsv100SelectedKey1Sort());
         cmn999Form.addHiddenParam("rsv100SelectedKey2Sort", form.getRsv100SelectedKey2Sort());
         cmn999Form.addHiddenParam("rsvIkkatuTorokuKey", form.getRsvIkkatuTorokuKey());
         cmn999Form.addHiddenParam("rsv100svFromYear", form.getRsv100svFromYear());
         cmn999Form.addHiddenParam("rsv100svFromMonth", form.getRsv100svFromMonth());
         cmn999Form.addHiddenParam("rsv100svFromDay", form.getRsv100svFromDay());
         cmn999Form.addHiddenParam("rsv100svToYear", form.getRsv100svToYear());
         cmn999Form.addHiddenParam("rsv100svToMonth", form.getRsv100svToMonth());
         cmn999Form.addHiddenParam("rsv100svToDay", form.getRsv100svToDay());
         cmn999Form.addHiddenParam("rsv100svGrp1", form.getRsv100svGrp1());
         cmn999Form.addHiddenParam("rsv100svGrp2", form.getRsv100svGrp2());
         cmn999Form.addHiddenParam("rsv100svKeyWord", form.getRsv100svKeyWord());
         cmn999Form.addHiddenParam("rsv100svSearchCondition", form.getRsv100svSearchCondition());
         cmn999Form.addHiddenParam("rsv100svTargetMok", form.getRsv100svTargetMok());
         cmn999Form.addHiddenParam("rsv100svTargetNiyo", form.getRsv100svTargetNiyo());
         cmn999Form.addHiddenParam("rsv100svSelectedKey1", form.getRsv100svSelectedKey1());
         cmn999Form.addHiddenParam("rsv100svSelectedKey2", form.getRsv100svSelectedKey2());
         cmn999Form.addHiddenParam("rsv100svSelectedKey1Sort", form.getRsv100svSelectedKey1Sort());
         cmn999Form.addHiddenParam("rsv100svSelectedKey2Sort", form.getRsv100svSelectedKey2Sort());
         cmn999Form.addHiddenParam("rsv100SearchSvFlg",
                 String.valueOf(form.isRsv100SearchSvFlg()));

         cmn999Form.addHiddenParam("rsv100dateKbn", form.getRsv100dateKbn());
         cmn999Form.addHiddenParam("rsv100apprStatus", form.getRsv100apprStatus());
         cmn999Form.addHiddenParam("rsv100svDateKbn", form.getRsv100svDateKbn());
         cmn999Form.addHiddenParam("rsv100svApprStatus", form.getRsv100svApprStatus());
         req.setAttribute("cmn999Form", cmn999Form);

         return map.findForward("gf_msg");
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
     private ActionForward __doDownLoad(ActionMapping map,
                                         Rsv250knForm form,
                                         HttpServletRequest req,
                                         HttpServletResponse res,
                                         Connection con)
         throws SQLException, Exception {
//       テンポラリディレクトリパスを取得
         CommonBiz cmnBiz = new CommonBiz();
         String tempDir =
             cmnBiz.getTempDir(
                     getTempPath(req),
                     GSConstReserve.PLUGIN_ID_RESERVE,
                     getRequestModel(req));
         Rsv250knBiz biz = new Rsv250knBiz(getRequestModel(req), con);
         //取込みファイル名称取得
         String fileId = biz.getImportFileName(tempDir);
         log__.debug("tempDir==>" + tempDir);
         log__.debug("fileId==>" + fileId);
         //オブジェクトファイルを取得
         ObjectFile objFile = new ObjectFile(tempDir, fileId.concat(GSConstCommon.ENDSTR_OBJFILE));
         Object fObj = objFile.load();
         Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
         //添付ファイル保存用のパスを取得する(フルパス)
         String filePath = tempDir + fileId.concat(GSConstCommon.ENDSTR_SAVEFILE);
         filePath = IOTools.replaceFileSep(filePath);

         //ログ出力処理
         GsMessage gsMsg = new GsMessage();
         AbstractReserveBiz rsvBiz = new AbstractReserveBiz(con);
         rsvBiz.outPutLog(
                 map, req, res,
                 gsMsg.getMessage(req, "cmn.download"), GSConstLog.LEVEL_INFO, fMdl.getFileName());

         //時間のかかる処理の前にコネクションを破棄
         JDBCUtil.closeConnectionAndNull(con);
         //ファイルをダウンロードする
         TempFileUtil.downloadAtachment(req, res, filePath, fMdl.getFileName(), Encoding.UTF_8);

         return null;
     }
}