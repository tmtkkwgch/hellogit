package jp.groupsession.v2.man.man150kn;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.base.CmnContmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.lic.GSConstLicese;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.man150.Man150Biz;
import jp.groupsession.v2.struts.AdminAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] メイン 管理者設定 ライセンスファイル登録・更新確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man150knAction extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man150knAction.class);

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
         Man150knForm manform = (Man150knForm) form;

         //コマンドパラメータ取得
         String cmd = NullDefault.getString(req.getParameter("CMD"), "");
         cmd = cmd.trim();

         //インポートボタン押下
         if (cmd.equals("import_ok")) {
             log__.debug("インポートボタン押下");
             forward = __doImport(map, manform, req, res, con);
         //戻るボタン押下
         } else if (cmd.equals("backToImport")) {
             log__.debug("戻るボタン押下");
             forward = map.findForward("backToImport");
         //初期表示処理
         } else {
             log__.debug("初期表示処理");
             forward = __doInit(map, manform, req, res, con);
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
                                     Man150knForm form,
                                     HttpServletRequest req,
                                     HttpServletResponse res,
                                     Connection con)
         throws Exception {

         RequestModel reqMdl = getRequestModel(req);

         //テンポラリディレクトリパスを取得
         CommonBiz cmnBiz = new CommonBiz();
         String tempDir =
             cmnBiz.getTempDir(
                     getTempPath(req), GSConstLicese.PLIGIN_ID, reqMdl);

         GSContext gs = getGsContext();
         CmnContmDao cntDao = new CmnContmDao(con);
         String gsUid = cntDao.getGsUid();

         //再入力チェック
         ActionErrors errors = form.validateCheck(reqMdl, tempDir, gs, gsUid);
         if (!errors.isEmpty()) {
             addErrors(req, errors);
             return map.getInputForward();
         }

         //取込情報取得
         Man150knParamModel paramMdl = new Man150knParamModel();
         paramMdl.setParam(form);
         Man150knBiz biz = new Man150knBiz();
         biz.setInitData(paramMdl, reqMdl, tempDir);
         paramMdl.setFormData(form);

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
                                       Man150knForm form,
                                       HttpServletRequest req,
                                       HttpServletResponse res,
                                       Connection con)
         throws Exception {

         if (!isTokenValid(req, true)) {
             log__.info("２重投稿");
             return getSubmitErrorPage(map, req);
         }

         RequestModel reqMdl = getRequestModel(req);

         //テンポラリディレクトリパスを取得
         CommonBiz cmnBiz = new CommonBiz();
         String tempDir =
             cmnBiz.getTempDir(
                     getTempPath(req), GSConstLicese.PLIGIN_ID, reqMdl);

         GSContext gs = getGsContext();
         CmnContmDao cntDao = new CmnContmDao(con);
         String gsUid = cntDao.getGsUid();

         //再入力チェック
         ActionErrors errors = form.validateCheck(reqMdl, tempDir, gs, gsUid);
         if (!errors.isEmpty()) {
             addErrors(req, errors);
             return map.getInputForward();
         }

         //ライセンスファイル取込
         String appRootPath = getAppRootPath();
         String domain = GroupSession.getResourceManager().getDomain(req);
         Man150knBiz biz = new Man150knBiz();
         biz.importLicenseFile(appRootPath, tempDir, gs, domain);

         //テンポラリディレクトリのファイル削除
         Man150Biz man150biz = new Man150Biz();
         man150biz.doDeleteFile(tempDir);

         //ログ出力
         GsMessage gsMsg = new GsMessage(reqMdl);
         cmnBiz.outPutCommonLog(map, reqMdl, gsMsg, con,
                 getInterMessage(reqMdl, "cmn.import"), GSConstLog.LEVEL_INFO,
                           getInterMessage(req, GSConstMain.TEXT_LICENSE_FILE));

         return __doImportComp(map, form, req, res, con);
     }

     /**
      * <br>[機  能] ライセンスファイル取込完了後の画面遷移設定
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
                                           Man150knForm form,
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
         cmn999Form.setMessage(
                 msgRes.getMessage(
                         "comp.regorupd.data",
                         getInterMessage(req, GSConstMain.TEXT_LICENSE_FILE)));

         req.setAttribute("cmn999Form", cmn999Form);
         return map.findForward("gf_msg");
     }
}