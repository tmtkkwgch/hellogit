package jp.groupsession.v2.rsv.rsv170;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.rsv.AbstractReserveAction;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 施設予約 個人設定 施設利用状況照会一覧表示設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv170Action extends AbstractReserveAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv170Action.class);

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
         Rsv170Form rsvform = (Rsv170Form) form;

         //コマンドパラメータ取得
         String cmd = NullDefault.getString(req.getParameter("CMD"), "");
         cmd = cmd.trim();

         //設定ボタン押下
         if (cmd.equals("edit")) {
             log__.debug("設定ボタン押下");
             forward = __doSet(map, rsvform, req, res, con);
         //戻るボタン押下
         } else if (cmd.equals("back_to_kojn_menu")) {
             log__.debug("戻るボタン押下");
             forward = map.findForward("back_to_kojn_menu");
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
      * @param map アクションマッピング
      * @param form アクションフォーム
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @throws SQLException SQL実行例外
      * @return ActionForward
      */
     private ActionForward __doInit(ActionMapping map,
                                     Rsv170Form form,
                                     HttpServletRequest req,
                                     HttpServletResponse res,
                                     Connection con)
         throws SQLException {

         con.setAutoCommit(true);
         Rsv170Biz biz = new Rsv170Biz(getRequestModel(req), con);

         Rsv170ParamModel paramMdl = new Rsv170ParamModel();
         paramMdl.setParam(form);
         biz.setInitData(paramMdl);
         paramMdl.setFormData(form);

         //トランザクショントークン設定
         saveToken(req);
         con.setAutoCommit(false);
         return map.getInputForward();
     }

     /**
      * <br>[機  能] 設定ボタンクリック時処理
      * <br>[解  説]
      * <br>[備  考]
      *
      * @param map アクションマッピング
      * @param form アクションフォーム
      * @param req リクエスト
      * @param res レスポンス
      * @param con コネクション
      * @return ActionForward 画面遷移先
      * @throws SQLException SQL実行時例外
      */
     private ActionForward __doSet(ActionMapping map,
                                    Rsv170Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
         throws SQLException {

         ActionForward forward = null;
         boolean commit = false;

         try {

             if (!isTokenValid(req, true)) {
                 log__.info("２重投稿");
                 return getSubmitErrorPage(map, req);
             }

             //個人設定の更新
             Rsv170Biz biz = new Rsv170Biz(getRequestModel(req), con);

             Rsv170ParamModel paramMdl = new Rsv170ParamModel();
             paramMdl.setParam(form);
             biz.updateMaxDsp(paramMdl);
             paramMdl.setFormData(form);

             //完了画面
             forward = __doCompDsp(map, form, req, res);
             commit = true;

         } catch (SQLException e) {
             log__.error("SQLException", e);
             throw e;
         } finally {
             if (commit) {
                 con.commit();
             } else {
                 con.rollback();
             }
         }

         //ログ出力処理
         GsMessage gsMsg = new GsMessage();
         AbstractReserveBiz rsvBiz = new AbstractReserveBiz(con);
         rsvBiz.outPutLog(map, req, res,
                 gsMsg.getMessage(req, "cmn.change"), GSConstLog.LEVEL_INFO, "");

         return forward;
     }

     /**
      * <br>[機  能] 完了画面設定
      * <br>[解  説]
      * <br>[備  考]
      *
      * @param map アクションマッピング
      * @param form アクションフォーム
      * @param req リクエスト
      * @param res レスポンス
      * @return ActionForward
      */
     private ActionForward __doCompDsp(ActionMapping map,
                                        Rsv170Form form,
                                        HttpServletRequest req,
                                        HttpServletResponse res) {

         ActionForward forward = null;
         Cmn999Form cmn999Form = new Cmn999Form();
         ActionForward urlForward = null;

         GsMessage gsMsg = new GsMessage();

         //完了画面パラメータの設定
         MessageResources msgRes = getResources(req);
         cmn999Form.setType(Cmn999Form.TYPE_OK);
         cmn999Form.setIcon(Cmn999Form.ICON_INFO);
         cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

         urlForward = map.findForward("back_to_kojn_menu");
         cmn999Form.setUrlOK(urlForward.getPath());
         cmn999Form.setMessage(
                 msgRes.getMessage("settei.kanryo.object",
                         gsMsg.getMessage(req, "reserve.146")));

         //hiddenパラメータ
         cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
         cmn999Form.addHiddenParam("rsvBackPgId", form.getRsvBackPgId());
         cmn999Form.addHiddenParam("rsvDspFrom", form.getRsvDspFrom());
         cmn999Form.addHiddenParam("rsvSelectedGrpSid", form.getRsvSelectedGrpSid());
         cmn999Form.addHiddenParam("rsvSelectedSisetuSid", form.getRsvSelectedSisetuSid());
         cmn999Form.addHiddenParam("rsv100InitFlg",
                 String.valueOf(form.isRsv100InitFlg()));
         cmn999Form.addHiddenParam("rsv100SearchFlg",
                 String.valueOf(form.isRsv100SearchFlg()));
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

         forward = map.findForward("gf_msg");
         return forward;
     }
}