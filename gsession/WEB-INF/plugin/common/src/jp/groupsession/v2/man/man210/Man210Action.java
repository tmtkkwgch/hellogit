package jp.groupsession.v2.man.man210;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.struts.AdminAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] メイン 管理者設定 モバイル使用一括設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man210Action extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man210Action.class);

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
         Man210Form manform = (Man210Form) form;

         //コマンドパラメータ取得
         String cmd = NullDefault.getString(req.getParameter("CMD"), "");
         cmd = cmd.trim();

         //OKボタン押下
         if (cmd.equals("settei_kakunin")) {
             log__.debug("OKボタン押下");
             forward = __doSetting(map, manform, req, res, con);
         //戻るボタン押下
         } else if (cmd.equals("backKtool")) {
             log__.debug("戻るボタン押下");
             forward = map.findForward("ktools");
             //戻るボタン押下
         } else if (cmd.equals("redraw")) {
             forward = __doRedraw(map, manform, req, res, con);
         } else if (cmd.equals("deleteMnb")) {
             log__.debug("左矢印");
             forward = __doDeleteMnb(map, manform, req, res, con);

         } else if (cmd.equals("addMnb")) {
             log__.debug("右矢印");
             forward = __doAddMnb(map, manform, req, res, con);

         //初期表示処理
         } else {
             log__.debug("初期表示処理");
             forward = __doInit(map, manform, req, res, con);
         }

         return forward;
     }

     /**
      * <br>[機  能] 初期表示処理
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
     private ActionForward __doInit(ActionMapping map,
                                     Man210Form form,
                                     HttpServletRequest req,
                                     HttpServletResponse res,
                                     Connection con) throws Exception {

         con.setAutoCommit(true);
         Man210ParamModel paramMdl = new Man210ParamModel();
         paramMdl.setParam(form);
         Man210Biz biz = new Man210Biz();
         biz.setDspData(con, paramMdl, getRequestModel(req));
         paramMdl.setFormData(form);
         con.setAutoCommit(false);

         return map.getInputForward();
     }
     /**
      * <br>[機  能] 再表示処理
      * <br>[解  説]
      * <br>[備  考]
      * @param map マップ
      * @param form フォーム
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @return ActionForward フォワード
      * @throws Exception 実行時例外
      */
     private ActionForward __doRedraw(ActionMapping map,
                                     Man210Form form,
                                     HttpServletRequest req,
                                     HttpServletResponse res,
                                     Connection con) throws Exception {

         con.setAutoCommit(true);
         Man210ParamModel paramMdl = new Man210ParamModel();
         paramMdl.setParam(form);
         Man210Biz biz = new Man210Biz();
         biz.setDspData(con, paramMdl, getRequestModel(req));
         paramMdl.setFormData(form);
         con.setAutoCommit(false);

         return map.getInputForward();
     }
     /**
      * <br>[機  能] OKボタンクリック時の処理
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
     private ActionForward __doSetting(ActionMapping map,
                                     Man210Form form,
                                     HttpServletRequest req,
                                     HttpServletResponse res,
                                     Connection con) throws Exception {
         ActionErrors errors = null;

         if (form.getMan210ObjKbn() == 1) {
             // 対象が指定ユーザ
             errors = form.validateCheck(getRequestModel(req));
             if (errors.size() > 0) {
                 addErrors(req, errors);
                 __doRedraw(map, form, req, res, con);
                 log__.debug("入力エラー");
                 return map.getInputForward();
             }
         }

         //トランザクショントークン設定
         saveToken(req);
         return map.findForward("settei_kakunin");
     }

     /**
      * <br>[機  能] 左矢印クリック時の処理
      * <br>[解  説]
      * <br>[備  考]
      * @param map アクションマッピング
      * @param form アクションフォーム
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @throws SQLException SQL実行例外
      * @return ActionForward
      */
     private ActionForward __doDeleteMnb(
         ActionMapping map,
         Man210Form form,
         HttpServletRequest req,
         HttpServletResponse res,
         Connection con) throws SQLException {

         //コンボで選択中のメンバーをメンバーリストから削除する
         con.setAutoCommit(true);
         Man210ParamModel paramMdl = new Man210ParamModel();
         paramMdl.setParam(form);
         Man210Biz biz = new Man210Biz();
         biz.deleteMnb(paramMdl);
         biz.setDspData(con, paramMdl, getRequestModel(req));
         paramMdl.setFormData(form);
         con.setAutoCommit(false);

         return map.getInputForward();
     }

     /**
      * <br>[機  能] 右矢印クリック時の処理
      * <br>[解  説]
      * <br>[備  考]
      * @param map アクションマッピング
      * @param form アクションフォーム
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @throws SQLException SQL実行例外
      * @return ActionForward
      */
     private ActionForward __doAddMnb(
         ActionMapping map,
         Man210Form form,
         HttpServletRequest req,
         HttpServletResponse res,
         Connection con) throws SQLException {

         //追加用メンバーコンボで選択中のメンバーをメンバーリストに追加する
         con.setAutoCommit(true);
         Man210ParamModel paramMdl = new Man210ParamModel();
         paramMdl.setParam(form);
         Man210Biz biz = new Man210Biz();
         biz.addMnb(paramMdl);
         biz.setDspData(con, paramMdl, getRequestModel(req));
         paramMdl.setFormData(form);
         con.setAutoCommit(false);

         return map.getInputForward();
     }
}
