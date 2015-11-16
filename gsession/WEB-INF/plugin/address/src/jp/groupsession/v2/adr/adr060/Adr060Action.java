package jp.groupsession.v2.adr.adr060;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.adr.AbstractAddressAction;
import jp.groupsession.v2.adr.AdrCommonBiz;
import jp.groupsession.v2.adr.model.AdrUconfModel;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] アドレス帳 表示件数設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr060Action extends AbstractAddressAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr060Action.class);

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
        ActionForward forward = null;

        Adr060Form adrForm = (Adr060Form) form;
        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
//        if (cmd.equals("adr060kakunin")) {
//            //確認
//            forward = __doKakunin(map, adrForm, req, res, con);
        if (cmd.equals("adr060commit")) {
            //登録
            forward = __doCommit(map, adrForm, req, res, con);
        } else if (cmd.equals("adr060back")) {
            //戻るボタンクリック
            forward = map.findForward("adr050");
        } else if (cmd.equals("changeCancel")) {
            //キャンセル
            forward = __doInit(map, adrForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, adrForm, req, res, con);
        }
        return forward;
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
    private ActionForward __doCommit(ActionMapping map, Adr060Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("登録処理");

//        //不正な画面遷移
//        if (!isTokenValid(req, true)) {
//            log__.info("２重投稿");
//            return getSubmitErrorPage(map, req);
//        }

        //セッションユーザSIDを取得する。
        int sessionUserSid = this.getSessionUserModel(req).getUsrsid();

        //DB更新
        Adr060Biz biz = new Adr060Biz(getRequestModel(req));

        Adr060ParamModel paramMdl = new Adr060ParamModel();
        paramMdl.setParam(form);
        AdrUconfModel adrUconfMdl
          = biz.setAdrUconfSetting(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);


        //ログ出力処理
        AdrCommonBiz adrBiz = new AdrCommonBiz(con);
        String opCode = "";
        GsMessage gsMsg = new GsMessage();
        if (adrUconfMdl == null) {
            opCode = gsMsg.getMessage(req, "cmn.entry");
        } else {
            opCode = gsMsg.getMessage(req, "cmn.change");
        }

        adrBiz.outPutLog(
                map, req, res, opCode, GSConstLog.LEVEL_INFO, "");

        //共通メッセージ画面(OK)を表示
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
        Adr060Form form) {

        GsMessage gsMsg = new GsMessage();
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("adr050");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "touroku.kanryo.object";

        //表示件数
        String key1 = gsMsg.getMessage(req, "cmn.number.display");
        cmn999Form.setMessage(msgRes.getMessage(msgState, key1));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("adr060DspCount", form.getAdr060DspCount());
        cmn999Form.addHiddenParam("adr060ComCount", form.getAdr060ComCount());

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);
    }

//    /**
//     * <br>確認処理
//     * @param map アクションマッピング
//     * @param form アクションフォーム
//     * @param req リクエスト
//     * @param res レスポンス
//     * @param con コネクション
//     * @return アクションフォーワード
//     * @throws SQLException SQL実行時例外
//     */
//    private ActionForward __doKakunin(ActionMapping map, Adr060Form form,
//            HttpServletRequest req, HttpServletResponse res, Connection con)
//            throws SQLException {
//
//        log__.debug("確認処理");
//
//        //トランザクショントークン設定
//        saveToken(req);
//
//        //共通メッセージ画面(OK キャンセル)を表示
//        __setKakuninPageParam(map, req, form);
//        return map.findForward("gf_msg");
//    }
//
//    /**
//     * <br>[機  能] 確認メッセージ画面遷移時のパラメータを設定
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param map マッピング
//     * @param req リクエスト
//     * @param form アクションフォーム
//     */
//    private void __setKakuninPageParam(
//        ActionMapping map,
//        HttpServletRequest req,
//        Adr060Form form) {
//
//        Cmn999Form cmn999Form = new Cmn999Form();
//
//        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
//        MessageResources msgRes = getResources(req);
//        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
//        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
//
//        cmn999Form.setUrlOK(map.findForward("changeOk").getPath());
//        cmn999Form.setUrlCancel(map.findForward("changeCancel").getPath());
//
//        //メッセージセット
//        String msgState = "setting.kakunin.data";
//        String mkey1 = "表示件数";
//        String mkey2 = form.getAdr060DspCount();
//        cmn999Form.setMessage(msgRes.getMessage(msgState, mkey1, mkey2));
//
//        cmn999Form.addHiddenParam("adr060DspCount", form.getAdr060DspCount());
//        cmn999Form.addHiddenParam("adr060ComCount", form.getAdr060ComCount());
//
//        //画面パラメータをセット
//        form.setHiddenParam(cmn999Form);
//
//        req.setAttribute("cmn999Form", cmn999Form);
//    }

    /**
     * <br>初期表処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Adr060Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("初期表示");

        con.setAutoCommit(true);
        //セッションユーザSIDを取得する。
        int sessionUserSid = this.getSessionUserModel(req).getUsrsid();
        Adr060Biz biz = new Adr060Biz(getRequestModel(req));

        Adr060ParamModel paramMdl = new Adr060ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

}
