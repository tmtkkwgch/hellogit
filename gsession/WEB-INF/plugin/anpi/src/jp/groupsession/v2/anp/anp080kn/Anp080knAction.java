package jp.groupsession.v2.anp.anp080kn;


import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.anp.AbstractAnpiAction;
import jp.groupsession.v2.anp.AnpiCommonBiz;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 管理者設定・基本設定確認画面のアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp080knAction extends AbstractAnpiAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp080knAction.class);

    /**
     * <br>[機  能] アクション実行
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォアード
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);
        Anp080knForm uform = (Anp080knForm) form;

        //管理者権限確認
        if (!AnpiCommonBiz.isGsAnpiAdmin(getRequestModel(req), con)) {
            return getDomainErrorPage(map, req);
        }

        if (cmd.equals("anp080knback")) {
            //戻る
            forward = map.findForward("back");

        } else if (cmd.equals("anp080knexcute")) {
            //確定（完了メッセージ→管理者設定メニュー）
            forward = __doUpdate(map, uform, req, res, con);

        } else if (cmd.equals("anp080knconTest")) {
            //接続テスト
            forward = __doConnectTest(map, uform, req, res, con);

        } else {
            //初期化
            forward = __doInit(map, uform, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 例外
     * @return アクションフォアード
     */
    private ActionForward __doInit(ActionMapping map, Anp080knForm form,
                    HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        return __refresh(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 更新処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 例外
     * @return アクションフォアード
     */
    private ActionForward __doUpdate(ActionMapping map, Anp080knForm form,
                HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        log__.debug("更新処理");

        //入力チェック
        ActionErrors errors = form.validateAnp080(getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __refresh(map, form, req, res, con);
        }

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

       //登録
        Anp080knBiz biz = new Anp080knBiz();
        Anp080knParamModel paramModel = new Anp080knParamModel();
        paramModel.setParam(form);
        boolean initFlg = biz.doUpdate(paramModel, getRequestModel(req), con);
        paramModel.setFormData(form);

        //ログ
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        String opCode;
        AnpiCommonBiz anpBiz = new AnpiCommonBiz(con);
        if (initFlg) {
            opCode = gsMsg.getMessage("cmn.entry");
        } else {
            opCode = gsMsg.getMessage("cmn.change");
        }
        anpBiz.outPutLog(map, getRequestModel(req), opCode, GSConstLog.LEVEL_INFO, "");

        //完了画面を表示
        return __setUpdateDsp(map, form, req);
    }

    /**
     * <br>[機  能] 完了画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @throws Exception 実行例外
     * @return アクションフォーワード
     */
    private ActionForward __setUpdateDsp(ActionMapping map, Anp080knForm form,
                                    HttpServletRequest req)
                        throws Exception {

        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        MessageResources msgRes = getResources(req);
        String msg = msgRes.getMessage("cmn.kanryo.object", gsMsg.getMessage("anp.anp080.01"));

        return __setMsgDsp(map, form, req, msg);
    }

    /**
     * <br>[機  能] 接続テスト
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doConnectTest(ActionMapping map, Anp080knForm form,
                HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        log__.debug("接続テスト");

        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        //送信サーバに接続可能かチェック
        Anp080knBiz biz = new Anp080knBiz();
        Anp080knParamModel paramModel = new Anp080knParamModel();
        paramModel.setParam(form);
        ActionErrors errors = biz.connectTest(paramModel, getRequestModel(req));
        paramModel.setFormData(form);

        String opCode = gsMsg.getMessage("anp.anp080kn.02");
        String value;
        AnpiCommonBiz anpBiz = new AnpiCommonBiz(con);
        if (errors.isEmpty()) {
            String msgKey = "success.connect.mailserver";
            ActionMessage msg = new ActionMessage(msgKey);
            StrutsUtil.addMessage(errors, msg, "success.connect.mailserver");
            value = gsMsg.getMessage("anp.logmsg.success");
        } else {
            value = gsMsg.getMessage("anp.logmsg.failure");
        }
        //ログ
        anpBiz.outPutLog(map, getRequestModel(req), opCode, GSConstLog.LEVEL_INFO, value);

        addErrors(req, errors);
        return __refresh(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 例外
     * @return アクションフォアード
     */
    private ActionForward __refresh(ActionMapping map, Anp080knForm form,
                    HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //初期データ取得
        Anp080knBiz biz = new Anp080knBiz();
        Anp080knParamModel paramModel = new Anp080knParamModel();
        paramModel.setParam(form);
        biz.setInitData(paramModel, getRequestModel(req), con);
        paramModel.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] メッセージ画面のパラメータセット
     * <br>[解  説] OKボタンのみのメッセージ画面を表示する
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param msg メッセージ文字列
     * @return ActionForward
     */
    private ActionForward __setMsgDsp(ActionMapping map, Anp080knForm form,
                                    HttpServletRequest req, String msg) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk;

        if (NullDefault.getString(form.getAnp080BackScreen(), "").equals("1")) {
            log__.debug("メッセージ配信画面へ遷移");
            forwardOk = map.findForward("haisin");
        } else {
            forwardOk = map.findForward("admtool");
        }
        cmn999Form.setUrlOK(forwardOk.getPath());

        //メッセージ
        cmn999Form.setMessage(msg);

        //画面値のセーブ
        form.setHiddenParamAnp010(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}
