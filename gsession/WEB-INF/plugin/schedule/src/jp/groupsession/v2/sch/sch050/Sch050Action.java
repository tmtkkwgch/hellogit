package jp.groupsession.v2.sch.sch050;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.sch.AbstractScheduleAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] スケジュール個人設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch050Action extends AbstractScheduleAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch050Action.class);

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

        log__.debug("START_SCH050");
        ActionForward forward = null;
        Sch050Form uform = (Sch050Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD==>" + cmd);
        if (cmd.equals("050_ok")) {
            //個人設定登録
            forward = __doOk(map, uform, req, res, con);
        } else if (cmd.equals("050_back")) {
            //戻る
            forward = __doBack(map, uform, req, res, con);
        } else {
            //個人設定表示
            __doInit(map, uform, req, res, con);
            forward = map.getInputForward();
        }
        log__.debug("END_SCH050");
        return forward;
    }

    /**
     * <br>初期表処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doInit(ActionMapping map, Sch050Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        con.setAutoCommit(true);
        Sch050Biz biz = new Sch050Biz(getRequestModel(req));

        Sch050ParamModel paramMdl = new Sch050ParamModel();
        paramMdl.setParam(form);
        biz.getInitData(paramMdl, con);
        paramMdl.setFormData(form);

        // トランザクショントークン設定
        this.saveToken(req);
        con.setAutoCommit(false);
    }

    /**
     * <br>登録ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doOk(ActionMapping map, Sch050Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        ActionForward forward = null;
        ActionErrors errors = form.validateCheck(map, req);
        if (errors.size() > 0) {
            addErrors(req, errors);
            __doInit(map, form, req, res, con);
            log__.debug("入力エラー");
            return map.getInputForward();
        }

        log__.debug("入力エラーなし");
        forward = __doCommit(map, form, req, res, con);
        return forward;
    }

    /**
     * <br>登録・修正処理を行う
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doCommit(ActionMapping map, Sch050Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        ActionForward forward = null;


        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        Sch050Biz biz = new Sch050Biz(getRequestModel(req));
        boolean commitFlg = false;
        con.setAutoCommit(false);
        try {
            Sch050ParamModel paramMdl = new Sch050ParamModel();
            paramMdl.setParam(form);
            biz.updateScheduleConf(paramMdl, sessionUsrSid, con);
            paramMdl.setFormData(form);
            commitFlg = true;
        } catch (SQLException e) {
            log__.error("スケジュール登録に失敗しました" + e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                con.rollback();
            }
        }
        forward = __doCompDsp(map, form, req, res, con);
        return forward;
    }

    /**
     * <br>個人設定メニューへ遷移する
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     */
    private ActionForward __doBack(ActionMapping map, Sch050Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {

        ActionForward forward = null;
        forward = map.findForward("050_back");

        return forward;
    }

    /**
     * <br>更新完了画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doCompDsp(ActionMapping map, Sch050Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //スケジュール登録完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("050_back");
        cmn999Form.setUrlOK(urlForward.getPath());
        //スケジュール個人設定
        GsMessage gsMsg = new GsMessage();
        String textConfMsg = gsMsg.getMessage(req, "schedule.src.10");
        cmn999Form.setMessage(msgRes.getMessage("settei.kanryo.object",
                                                            textConfMsg));
        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }
}
