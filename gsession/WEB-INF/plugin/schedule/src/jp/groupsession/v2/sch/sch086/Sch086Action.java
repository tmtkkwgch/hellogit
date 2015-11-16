package jp.groupsession.v2.sch.sch086;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.AbstractScheduleAdminAction;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] スケジュール 管理者設定 スケジュール初期値設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 */
public class Sch086Action extends AbstractScheduleAdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch086Action.class);

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
         Sch086Form schform = (Sch086Form) form;

         //コマンドパラメータ取得
         String cmd = NullDefault.getString(req.getParameter("CMD"), "");
         cmd = cmd.trim();

         //OKボタン押下
         if (cmd.equals("sch086Ok")) {
             //確認
             forward = __doOk(map, schform, req, res, con);
         } else if (cmd.equals("sch086Entry")) {
             //登録
             forward = __doCommit(map, schform, req, res, con);
         //戻るボタン押下
         } else if (cmd.equals("back")) {
             log__.debug("戻るボタン押下");
             forward = map.findForward("ktool");
         //初期表示処理
         } else {
             log__.debug("初期表示処理");
             forward = __doInit(map, schform, req, res, con);
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
      */
     private ActionForward __doInit(ActionMapping map,
                                     Sch086Form form,
                                     HttpServletRequest req,
                                     HttpServletResponse res,
                                     Connection con)
         throws SQLException {

         con.setAutoCommit(true);
         Sch086Biz biz = new Sch086Biz(getRequestModel(req));
         Sch086ParamModel paramMdl = new Sch086ParamModel();
         paramMdl.setParam(form);
         biz.setInitData(con, paramMdl);
         paramMdl.setFormData(form);

         return map.getInputForward();
     }

     /**
      * <br>確認処理
      * @param map アクションマッピング
      * @param form アクションフォーム
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @return アクションフォーワード
      * @throws SQLException SQL実行時例外
      */
     private ActionForward __doOk(ActionMapping map, Sch086Form form,
             HttpServletRequest req, HttpServletResponse res, Connection con)
             throws SQLException {

         log__.debug("確認処理");
         //トランザクショントークン設定
         saveToken(req);

         //共通メッセージ画面(OK キャンセル)を表示
         __setKakuninPageParam(map, req, form);
         return map.findForward("gf_msg");
     }

     /**
      * <br>[機  能] 確認メッセージ画面遷移時のパラメータを設定
      * <br>[解  説]
      * <br>[備  考]
      * @param map マッピング
      * @param req リクエスト
      * @param form アクションフォーム
      */
     private void __setKakuninPageParam(
         ActionMapping map,
         HttpServletRequest req,
         Sch086Form form) {

         Cmn999Form cmn999Form = new Cmn999Form();

         cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
         MessageResources msgRes = getResources(req);
         cmn999Form.setIcon(Cmn999Form.ICON_INFO);
         cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
         cmn999Form.setUrlOK(map.findForward("mine").getPath() + "?CMD=sch086Entry");
         cmn999Form.setUrlCancel(map.findForward("mine").getPath());

         //メッセージセット
         String msgState = "edit.kakunin.once";
         GsMessage gsMsg = new GsMessage();
         //スケジュール 初期値
         String textSchBasicSetting = gsMsg.getMessage(req, "schedule.131");
         String mkey1 = textSchBasicSetting;
         cmn999Form.setMessage(msgRes.getMessage(msgState, mkey1));

         cmn999Form.addHiddenParam("cmd", "sch086Entry");
         cmn999Form.addHiddenParam("sch086init", form.getSch086init());
         cmn999Form.addHiddenParam("sch086EditType", form.getSch086EditType());
         cmn999Form.addHiddenParam("sch086Edit", form.getSch086Edit());
         cmn999Form.addHiddenParam("sch086PublicType", form.getSch086PublicType());
         cmn999Form.addHiddenParam("sch086Public", form.getSch086Public());
         cmn999Form.addHiddenParam("sch086SameType", form.getSch086SameType());
         cmn999Form.addHiddenParam("sch086Same", form.getSch086Same());

         form.setMsgFormParam(cmn999Form);

         req.setAttribute("cmn999Form", cmn999Form);
     }

     /**
      * <br>登録処理
      * @param map アクションマッピング
      * @param form アクションフォーム
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @return アクションフォーワード
      * @throws SQLException SQL実行時例外
      */
     private ActionForward __doCommit(ActionMapping map, Sch086Form form,
             HttpServletRequest req, HttpServletResponse res, Connection con)
             throws SQLException {

         log__.debug("登録処理");

         RequestModel reqMdl = getRequestModel(req);

         //不正な画面遷移
         if (!isTokenValid(req, true)) {
             log__.info("２重投稿");
             return getSubmitErrorPage(map, req);
         }

         //DB更新
         boolean commit = false;
         try {
             Sch086Biz biz = new Sch086Biz(getRequestModel(req));
             Sch086ParamModel paramMdl = new Sch086ParamModel();
             paramMdl.setParam(form);
             biz.entryKbn(con, paramMdl, getSessionUserSid(req));
             paramMdl.setFormData(form);

             con.commit();
             commit = true;
         } finally {
             if (!commit) {
                 con.rollback();
             }
         }

         //メッセージ 削除
         GsMessage gsMsg = new GsMessage();
         String change = gsMsg.getMessage(req, "cmn.change");
         //ログ出力処理
         SchCommonBiz schBiz = new SchCommonBiz(con, reqMdl);
         schBiz.outPutLog(
                 map, req, res,
                 change, GSConstLog.LEVEL_INFO, "");

         //共通メッセージ画面(OK キャンセル)を表示
         __setCompPageParam(map, req, form);
         return map.findForward("gf_msg");
     }

     /**
      * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
      * <br>[解  説]
      * <br>[備  考]
      * @param map マッピング
      * @param req リクエスト
      * @param form アクションフォーム
      */
     private void __setCompPageParam(
         ActionMapping map,
         HttpServletRequest req,
         Sch086Form form) {

         Cmn999Form cmn999Form = new Cmn999Form();
         ActionForward urlForward = null;

         cmn999Form.setType(Cmn999Form.TYPE_OK);
         MessageResources msgRes = getResources(req);
         cmn999Form.setIcon(Cmn999Form.ICON_INFO);
         cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
         urlForward = map.findForward("ktool");
         cmn999Form.setUrlOK(urlForward.getPath());

         //メッセージセット
         String msgState = "touroku.kanryo.object";
         GsMessage gsMsg = new GsMessage();
         //スケジュール基本設定
         String textSchBasicSetting = gsMsg.getMessage(req, "schedule.131");
         cmn999Form.setMessage(msgRes.getMessage(msgState, textSchBasicSetting));

         form.setMsgFormParam(cmn999Form);

         req.setAttribute("cmn999Form", cmn999Form);

     }
}