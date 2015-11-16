package jp.groupsession.v2.rss.rss030kn;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstRss;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rss.AbstractRssAction;
import jp.groupsession.v2.rss.RssBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <p>RSSリーダー RSS登録確認画面のアクションクラス
 * @author JTS
 */
public class Rss030knAction extends AbstractRssAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rss030knAction.class);

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
        Rss030knForm rssForm = (Rss030knForm) form;

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);

        if (cmd.equals("decision")) {
            //追加ボタンクリック
            forward = __doDecision(map, rssForm, req, res, con);
        } else if (cmd.equals("backToInput")) {
            //戻るボタンクリック
            forward = map.findForward("backRssInput");
        } else {
            //初期表示
            forward = __doInit(map, rssForm, req, res, con);
        }

        log__.debug("END");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doInit(ActionMapping map,
        Rss030knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {

        Rss030knParamModel paramMdl = new Rss030knParamModel();
        paramMdl.setParam(form);
        Rss030knBiz biz = new Rss030knBiz();
        biz.setInitData(paramMdl, con, getRequestModel(req));
        paramMdl.setFormData(form);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doDecision(ActionMapping map,
        Rss030knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {

        //2重投稿
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        con.setAutoCommit(true);
        //入力チェック
        ActionErrors errors = new ActionErrors();
        errors = form.validateCheck(con, getSessionUserSid(req), getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        //ログインユーザSIDを取得
        int userSid = getSessionUserSid(req);

        RequestModel reqMdl = getRequestModel(req);
        Rss030knBiz biz = new Rss030knBiz();
        boolean commit = false;
        con.setAutoCommit(false);
        int cmdMode = form.getRssCmdMode();
        try {

            Rss030knParamModel paramMdl = new Rss030knParamModel();
            paramMdl.setParam(form);
            if (cmdMode == GSConstRss.RSSCMDMODE_ADD) {

                //登録処理
                MlCountMtController cntCon = getCountMtController(req);
                biz.insertRssData(paramMdl, con, cntCon, userSid, reqMdl);

            } else if (cmdMode == GSConstRss.RSSCMDMODE_EDIT) {

                //更新処理
                biz.updateRssData(paramMdl, con, getCountMtController(req), userSid, reqMdl);

            }
            con.commit();
            commit = true;
        } catch (Exception e) {
            log__.error("RSS登録処理エラー", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        //ログ出力処理
        RssBiz rssBiz = new RssBiz(con, reqMdl);
        String opCode = null;
        if (cmdMode == GSConstRss.RSSCMDMODE_ADD) {
            //新規登録
            opCode = getInterMessage(reqMdl, "cmn.entry");
        } else if (cmdMode == GSConstRss.RSSCMDMODE_EDIT) {
            //更新
            opCode = getInterMessage(reqMdl, "cmn.change");
        }

        rssBiz.outPutLog(
                map, reqMdl, opCode, GSConstLog.LEVEL_TRACE, "[title]" + form.getRssTitle());
        __setCompPageParam(map, req, form);

        con.setAutoCommit(false);
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
        Rss030knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("moveRssList");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = null;
        int cmdMode = form.getRssCmdMode();
        if (cmdMode == GSConstRss.RSSCMDMODE_ADD) {
            msgState = "touroku.kanryo.object";
        } else if (cmdMode == GSConstRss.RSSCMDMODE_EDIT) {
            msgState = "hensyu.kanryo.object";
        }
        cmn999Form.setMessage(msgRes.getMessage(msgState,
                            GSConstRss.RSS_MSG));

        req.setAttribute("cmn999Form", cmn999Form);

    }

}

