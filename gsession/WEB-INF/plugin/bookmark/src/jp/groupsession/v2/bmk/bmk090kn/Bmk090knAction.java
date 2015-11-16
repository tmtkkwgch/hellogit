package jp.groupsession.v2.bmk.bmk090kn;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.bmk.AbstractBookmarkAction;
import jp.groupsession.v2.bmk.biz.BookmarkBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 権限設定(グループブックマーク)確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 */
public class Bmk090knAction extends AbstractBookmarkAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk090knAction.class);

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
        Bmk090knForm bmkform = (Bmk090knForm) form;
        RequestModel reqMdl = getRequestModel(req);

        //グループ管理者権限判定
        BookmarkBiz bmkBiz = new BookmarkBiz(con, reqMdl);
        int gSid = bmkform.getBmk010groupSid();
        if (!bmkBiz.isGrpAdmin(con, gSid)) {
            //権限エラー
            forward = getNotAdminSeniPage(map, req);
            return forward;
        }

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        //確定ボタン押下
        if (cmd.equals("bmk090knkakutei")) {
            log__.debug("確定ボタン押下");
            forward = __doKakutei(map, bmkform, reqMdl, req, res, con);
        //戻るボタン押下
        } else if (cmd.equals("bmk090knback")) {
            log__.debug("戻るボタン押下");
            forward = map.findForward("bmk090");
        //初期表示処理
        } else {
            log__.debug("初期表示処理");
            forward = __doInit(map, bmkform, reqMdl, req, res, con);
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
     * @param reqMdl リクエスト情報
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Bmk090knForm form,
                                    RequestModel reqMdl,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {

        log__.debug("初期表示");

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk090knBiz biz = new Bmk090knBiz(reqMdl);

        Bmk090knParamModel paramMdl = new Bmk090knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Adr040knForm
     * @param reqMdl リクエスト情報
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doKakutei(ActionMapping map, Bmk090knForm form,
            RequestModel reqMdl, HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェックを行う
        con.setAutoCommit(true);
        ActionErrors errors = null;
        errors = form.validateCheck(con, req);
        con.setAutoCommit(false);

        if (errors != null && !errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, reqMdl, req, res, con);
        }

        ActionForward forward = null;

        //セッションユーザSIDを取得する。
        int sessionUserSid = this.getSessionUserModel(req).getUsrsid();
        //DB更新
        Bmk090knBiz biz = new Bmk090knBiz(reqMdl);

        Bmk090knParamModel paramMdl = new Bmk090knParamModel();
        paramMdl.setParam(form);
        biz.setBmkGconf(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);


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
        Bmk090knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("bmk010");
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "cmn.setting.permissions");

        //メッセージセット
        cmn999Form.setMessage(msgRes.getMessage("touroku.kanryo.object", msg));

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
}
