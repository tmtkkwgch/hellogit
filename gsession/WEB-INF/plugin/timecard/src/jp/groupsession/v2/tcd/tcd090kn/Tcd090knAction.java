package jp.groupsession.v2.tcd.tcd090kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.AbstractTimecardAction;
import jp.groupsession.v2.tcd.TimecardBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;


/**
 * <br>[機  能] タイムカード 管理者設定 編集権限設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd090knAction extends AbstractTimecardAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd090knAction.class);
    /** 管理者のみ使用可能
     * @see jp.groupsession.v2.struts.AbstractGsAction
     * #canNotAdminAccess(
     * javax.servlet.http.HttpServletRequest,
     * org.apache.struts.action.ActionForm)
     * @param req リクエスト
     * @param form フォーム
     * @return boolean true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        return false;
    }
    /**
     *<br>[機  能]tcd050Action
     *<br>[解  説]
     *<br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
        HttpServletRequest req, HttpServletResponse res, Connection con)
        throws Exception {

        ActionForward forward = null;

        //コマンド取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);
        cmd = cmd.trim();

        Tcd090knForm tcForm = (Tcd090knForm) form;

        if (cmd.equals("tcd090kn_back")) {
            //戻る
            forward = map.findForward("tcd090knback");
        } else if (cmd.equals("tcd090kn_submit")) {
            //OKボタン
            forward = __doSubmit(map, tcForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, tcForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     */
    private ActionForward __doInit(ActionMapping map, Tcd090knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doSubmit(ActionMapping map, Tcd090knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con) throws Exception {
        ActionForward forward = null;

        //２重投稿チェック
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //セッション情報を取得
        int sessionUsrSid = getSessionUserSid(req);

        //更新処理
        boolean commit = false;
        try {
            Tcd090knParamModel paramMdl = new Tcd090knParamModel();
            paramMdl.setParam(form);
            Tcd090knBiz biz = new Tcd090knBiz();
            biz.updateTcdAdmConf(paramMdl, sessionUsrSid, con);
            paramMdl.setFormData(form);

            commit = true;

        } catch (SQLException e) {
            //SQL実行時例外
            log__.error("タイムカード編集権限設定更新に失敗しました。" + e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String msg = gsMsg.getMessage("cmn.edit");

        //ログ出力
        TimecardBiz cBiz = new TimecardBiz(reqMdl);
        cBiz.outPutTimecardLog(map, reqMdl, con,
                msg, GSConstLog.LEVEL_INFO, "");

        forward = __doUpdateCompDsp(map, form, req, res, con);

        return forward;
    }

    /**
     * <br>[機  能] 登録完了画面設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doUpdateCompDsp(ActionMapping map, Tcd090knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //登録完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("tcd090kncommit");
        cmn999Form.setUrlOK(urlForward.getPath());
        GsMessage gsMsg = new GsMessage();
        String msgYear = gsMsg.getMessage(req, "tcd.99");

        cmn999Form.setMessage(msgRes.getMessage("touroku.kanryo.object", msgYear));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("year", form.getYear());
        cmn999Form.addHiddenParam("month", form.getMonth());
        cmn999Form.addHiddenParam("tcdDspFrom", form.getTcdDspFrom());

        cmn999Form.addHiddenParam("usrSid", form.getUsrSid());
        cmn999Form.addHiddenParam("sltGroupSid", form.getSltGroupSid());
        cmn999Form.addHiddenParam("selectDay", form.getSelectDay());

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}
