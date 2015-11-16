package jp.groupsession.v2.bmk.bmk060kn;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.bmk.AbstractBookmarkAction;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.biz.BookmarkBiz;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ラベル登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 */
public class Bmk060knAction extends AbstractBookmarkAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk060knAction.class);

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
    */
    public ActionForward executeAction(ActionMapping map,
                                        ActionForm form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con) throws Exception {

        ActionForward forward = null;
        Bmk060knForm bmkform = (Bmk060knForm) form;

        BookmarkBiz bmkBiz = new BookmarkBiz(con, getRequestModel(req));
        BaseUserModel model = this.getSessionUserModel(req);
        int bmkKbn = bmkform.getBmk010mode();
        int usrSid = this.getSessionUserModel(req).getUsrsid();
        int gSid = bmkform.getBmk010groupSid();

        // 編集権限チェック
        if (!bmkBiz.isEditPow(con, model, bmkKbn, usrSid, gSid)) {
            //権限エラー
            forward = getNotAdminSeniPage(map, req);
            return forward;
        }

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        log__.debug("処理モード = " + bmkform.getBmk050ProcMode());
        log__.debug("編集SID    = " + bmkform.getBmk050LblSid());
        log__.debug("ラベル名   = " + bmkform.getBmk060LblName());

        //確定ボタン押下
        if (cmd.equals("bmk060knkakutei")) {
            log__.debug("確定ボタン押下");
            forward = __doKakutei(map, bmkform, req, res, con);
        //戻るボタン押下
        } else if (cmd.equals("bmk060knback")) {
            log__.debug("戻るボタン押下");
            forward = map.findForward("bmk060");
        //初期表示処理
        } else {
            log__.debug("初期表示処理");
            forward = __doInit(map, bmkform, req, res, con);
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
                                    Bmk060knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {
        ActionForward forward = null;

        // トランザクショントークン設定
        saveToken(req);

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk060knBiz biz = new Bmk060knBiz();

        Bmk060knParamModel paramMdl = new Bmk060knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        forward = map.getInputForward();
        return forward;
    }

    /**
     * <br>[機  能] 確定ボタンクリック時
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Adr040knForm
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doKakutei(ActionMapping map, Bmk060knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェックを行う
        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        //入力チェック
        ActionErrors errors = form.validateCheck(con, sessionUserSid, req);

        if (errors != null && !errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        ActionForward forward = null;

        MlCountMtController cntCon = getCountMtController(req);
        //DB更新
        Bmk060knBiz biz = new Bmk060knBiz();

        Bmk060knParamModel paramMdl = new Bmk060knParamModel();
        paramMdl.setParam(form);
        biz.setBmkLabel(paramMdl, cntCon, sessionUserSid, con);
        paramMdl.setFormData(form);

        //ログ出力処理
        BookmarkBiz bmkBiz = new BookmarkBiz(con, getRequestModel(req));
        String opCode = "";

        GsMessage gsMsg = new GsMessage();
        String msg = "";

        //登録
        if (form.getBmk050ProcMode() == GSConstBookmark.BMK_MODE_TOUROKU) {
            msg = gsMsg.getMessage(req, "cmn.entry");
            opCode = msg;
        //編集
        } else {
            msg = gsMsg.getMessage(req, "cmn.change");
            opCode = msg;
        }

        bmkBiz.outPutLog(opCode,
                GSConstLog.LEVEL_TRACE,
                "[name]" + form.getBmk060LblName(), map.getType());

        forward = __setCompPageParam(map, req, form);
        return forward;
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @return ActionForward
     */
    private ActionForward __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Bmk060knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("bmk050");
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "cmn.label");

        //メッセージセット
        cmn999Form.setMessage(msgRes.getMessage("touroku.kanryo.object", msg));

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        cmn999Form.addHiddenParam("bmk050DelSidList", form.getBmk050DelSidList());

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
}
