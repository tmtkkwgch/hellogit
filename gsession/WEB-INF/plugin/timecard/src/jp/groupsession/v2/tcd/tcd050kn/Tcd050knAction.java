package jp.groupsession.v2.tcd.tcd050kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.AbstractTimecardAction;
import jp.groupsession.v2.tcd.TimecardBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;


/**
 * <br>[機  能] タイムカード 管理者設定 基本設定確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd050knAction extends AbstractTimecardAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd050knAction.class);
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

        Tcd050knForm tcForm = (Tcd050knForm) form;

        if (cmd.equals("tcd050kn_back")) {
            //戻る
            forward = map.findForward("050kn_back");
        } else if (cmd.equals("tcd050kn_submit")) {
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
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Tcd050knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        Tcd050knParamModel paramMdl = new Tcd050knParamModel();
        paramMdl.setParam(form);
        Tcd050knBiz biz = new Tcd050knBiz();
        biz.setInitData(paramMdl, getRequestModel(req), con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] OKボタンクリック時処理
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
    private ActionForward __doSubmit(ActionMapping map, Tcd050knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con) throws Exception {

        //２重投稿チェック
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        RequestModel reqMdl = getRequestModel(req);

        //入力チェック
        ActionErrors ereors = form.validateCheck(reqMdl);
        if (!ereors.isEmpty()) {
            addErrors(req, ereors);
            __doInit(map, form, req, res, con);
            return map.getInputForward();
        }

        //更新処理
        //セッション情報を取得
        BaseUserModel usModel = getSessionUserModel(req);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //タイムカード情報を登録・更新
        boolean commit = false;
        try {
            Tcd050knParamModel paramMdl = new Tcd050knParamModel();
            paramMdl.setParam(form);
            Tcd050knBiz biz = new Tcd050knBiz();
            biz.updateTcdAdmConf(paramMdl, sessionUsrSid, con);
            paramMdl.setFormData(form);

            commit = true;
        } catch (SQLException e) {
            //SQL実行時例外
            log__.error("タイムカード基本設定更新に失敗しました。" + e);
            con.rollback();
            throw e;
        } finally {
            if (commit) {
                con.commit();
            }
        }

        //ログ出力
        GsMessage gsMsg = new GsMessage(reqMdl);
        String msgEdit = gsMsg.getMessage("cmn.edit");

        TimecardBiz tcdBiz = new TimecardBiz(reqMdl);
        tcdBiz.outPutTimecardLog(map, reqMdl, con, msgEdit, GSConstLog.LEVEL_INFO, "");

        return __doUpdateCompDsp(map, form, req, res, con);
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
    private ActionForward __doUpdateCompDsp(ActionMapping map, Tcd050knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //登録完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("050kn_comp");
        cmn999Form.setUrlOK(urlForward.getPath());
        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "tcd.tcd050.07");

        cmn999Form.setMessage(msgRes.getMessage("touroku.kanryo.object", msg));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("year", form.getYear());
        cmn999Form.addHiddenParam("month", form.getMonth());
        cmn999Form.addHiddenParam("tcdDspFrom", form.getTcdDspFrom());

        cmn999Form.addHiddenParam("usrSid", form.getUsrSid());
        cmn999Form.addHiddenParam("sltGroupSid", form.getSltGroupSid());
        cmn999Form.addHiddenParam("selectDay", form.getSelectDay());

        cmn999Form.addHiddenParam("tcd050DspHolidayYear", form.getTcd050DspHolidayYear());
        cmn999Form.addHiddenParam("tcd050BetweenSlt", form.getTcd050BetweenSlt());
        cmn999Form.addHiddenParam("tcd050SinsuSlt", form.getTcd050SinsuSlt());
        cmn999Form.addHiddenParam("tcd050LimitDaySlt", form.getTcd050LimitDaySlt());
        cmn999Form.addHiddenParam("tcd050SelectWeek", form.getTcd050SelectWeek());
        cmn999Form.addHiddenParam("tcd050SelectHoliDay", form.getTcd050SelectHoliDay());

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }
}
