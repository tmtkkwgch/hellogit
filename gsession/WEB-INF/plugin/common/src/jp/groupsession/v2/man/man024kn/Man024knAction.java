package jp.groupsession.v2.man.man024kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.AdminAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] メイン 管理者設定 テンプレートから追加確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Man024knAction extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man024knAction.class);

    /** 戻るボタン押下時CMD */
    private static String cmd_BACK = "backTemp";

    /** 休日に反映ボタン押下時CMD */
    private static String cmd_REFLECT = "reflectHoliday";


    /**
     * <br>[機  能] アクション実行
     * <br>[解  説] コントローラの役割を担います
     * <br>[備  考]
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
        log__.debug("START");

        ActionForward forward = null;
        Man024knForm thisForm = (Man024knForm) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD==>" + cmd);

        if (cmd.equals(cmd_BACK)) {
            log__.debug(">>>戻るボタン押下");
            forward = map.findForward(cmd_BACK);
        } else if (cmd.equals(cmd_REFLECT)) {
            log__.debug(">>>休日に反映ボタン押下");
            forward = __doReflect(map, thisForm, req, res, con);
        } else {
            log__.debug(">>>初期表示");
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        }

        log__.debug("END");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doInit(ActionMapping map, Man024knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        con.setAutoCommit(true);
        Man024knParamModel paramMdl = new Man024knParamModel();
        paramMdl.setParam(form);
        Man024knBiz biz = new Man024knBiz(getRequestModel(req));
        biz.getInitData(paramMdl, con);
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

    }

    /**
     * <br>[機  能] 休日に反映ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doReflect(ActionMapping map, Man024knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        ActionForward forward = null;

        RequestModel reqMdl = getRequestModel(req);
        Man024knParamModel paramMdl = new Man024knParamModel();
        paramMdl.setParam(form);
        Man024knBiz biz = new Man024knBiz(reqMdl);
        boolean result = biz.execDataTran(paramMdl, con, __getSessionSid(req));
        paramMdl.setFormData(form);

        if (result) {
            __setKanryou(map, req, form);
            forward = map.findForward("gf_msg");
        }
        //ログ出力
        CommonBiz cmnBiz = new CommonBiz();
        GsMessage gsMsg = new GsMessage(reqMdl);
        cmnBiz.outPutCommonLog(map, reqMdl, gsMsg, con,
                getInterMessage(req, "cmn.entry"), GSConstLog.LEVEL_INFO,
                "");
        return forward;
    }

    /**
     * <br>[機  能] セッションユーザSIDを取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return int セッションuserSID
     */
    private int __getSessionSid(HttpServletRequest req) {

        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        return  usModel.getUsrsid(); //セッションユーザSID
    }

    /**
     * <br>[機  能] 登録完了画面のパラメータセット
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     */
    private void __setKanryou(
        ActionMapping map,
        HttpServletRequest req,
        Man024knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //権限エラー警告画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        urlForward = map.findForward("list");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "touroku.kanryo.object";
        cmn999Form.setMessage(msgRes.getMessage(msgState,
                getInterMessage(req, GSConstMain.HOLIDAY_MSG)));

        cmn999Form.addHiddenParam("man020DspYear", form.getMan020DspYear());
        req.setAttribute("cmn999Form", cmn999Form);

    }

}
