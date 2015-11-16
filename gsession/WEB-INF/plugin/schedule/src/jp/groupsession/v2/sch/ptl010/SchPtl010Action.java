package jp.groupsession.v2.sch.ptl010;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.GSException;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.http.GSAuthenticateException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.AbstractScheduleAction;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.sch.sch010.Sch010Form;
import jp.groupsession.v2.sch.sch020.Sch020Form;
import jp.groupsession.v2.sch.sch030.Sch030Form;
import jp.groupsession.v2.sch.sch040.Sch040Form;
import jp.groupsession.v2.sch.sch040kn.Sch040knForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] スケジュール ポートレット グループスケジュール週間画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class SchPtl010Action extends AbstractScheduleAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(SchPtl010Action.class);

    /**
     * <br>[機  能] Connectionに設定する自動コミットモードを返す
     * <br>[解  説]
     * <br>[備  考]
     * @return AutoCommit設定値
     */
    protected boolean _getAutoCommit() {
        return true;
    }

    /**
     * <p>
     * アクション実行
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

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);
        SchPtl010Form uform = (SchPtl010Form) form;

        if (cmd.equals("schw_today")) {
            //今日ボタン
            __doMoveDaysForWeek(uform, 0, true);
            __doInit(map, uform, req, res, con, true);
            forward = map.getInputForward();
        } else if (cmd.equals("schw_move_rd")) {
            //次日移動
            __doMoveDaysForWeek(uform, 1, false);
            __doInit(map, uform, req, res, con, true);
            forward = map.getInputForward();
        } else if (cmd.equals("schw_move_ld")) {
            //前日移動
            __doMoveDaysForWeek(uform, -1, false);
            __doInit(map, uform, req, res, con, true);
            forward = map.getInputForward();
        } else if (cmd.equals("schw_move_rw")) {
            //次週移動
            __doMoveDaysForWeek(uform, 7, false);
            __doInit(map, uform, req, res, con, true);
            forward = map.getInputForward();
        } else if (cmd.equals("schw_move_lw")) {
            //前週移動
            __doMoveDaysForWeek(uform, -7, false);
            __doInit(map, uform, req, res, con, true);
            forward = map.getInputForward();
        } else if (cmd.equals("schw_add")) {
            //登録
            forward = __doScheduleAdd(map, uform, req, res, con);
        } else if (cmd.equals("schw_edit")) {
            //修正・閲覧
            forward = __doScheduleEdit(map, uform, req, res, con);
        } else if (cmd.equals("month")) {
            //月間一覧
            log__.debug("月間一覧へ遷移");
            forward = __doScheduleMonth(map, uform, req, res, con);
        } else if (cmd.equals("week")) {
            //週間一覧
            log__.debug("週間一覧へ遷移");
            forward = __doScheduleWeek(map, uform, req, res, con);
        } else if (cmd.equals("day")) {
            //日間一覧
            log__.debug("日間一覧へ遷移");
            forward = __doScheduleDay(map, uform, req, res, con);
        } else {
            //初期表示
            __doInit(map, uform, req, res, con, false);
            forward = map.getInputForward();
        }
        return forward;
    }

    /**
     * 初期表処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param changeDateFlg 日付変更フラグ false:変更なし true:変更あり
     * @throws SQLException SQL実行時例外
     * @throws GSException GS用汎実行例外
     */
    private void __doInit(
            ActionMapping map,
            SchPtl010Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con,
            boolean changeDateFlg)
            throws SQLException, GSException {

        //セッション情報
        BaseUserModel userMdl = getSessionUserModel(req);
        if (userMdl == null) {
            throw new GSAuthenticateException("ユーザ情報の取得に失敗");
        }

        SchPtl010Biz biz = new SchPtl010Biz();
        SchPtl010ParamModel paramMdl = new SchPtl010ParamModel();
        paramMdl.setParam(form);
        biz.getInitData(
                paramMdl, con, changeDateFlg, userMdl, getRequestModel(req));
        paramMdl.setFormData(form);

    }

    /**
     * 週間スケジュールの表示日付の移動を行います
     * @param form アクションフォーム
     * @param moveDay 移動日数
     * @param today 今日へ移動
     */
    private void __doMoveDaysForWeek(SchPtl010Form form, int moveDay, boolean today) {
        String dspDate = "";
        if (today) {
            dspDate = new UDate().getDateString();
        } else {
            dspDate = NullDefault.getString(
                    form.getSchWeekDate(), new UDate().getDateString());
        }

        UDate udate = new UDate();
        udate.setDate(dspDate);
        udate.addDay(moveDay);
        form.setMoveKbn(1);
        form.setSchWeekDate(udate.getDateString());
    }
    /**
     * スケジュール登録画面へ遷移
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     * @throws GSException GS用汎実行例外
     */
    private ActionForward __doScheduleAdd(ActionMapping map, SchPtl010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException, GSException {
        ActionForward forward = null;

        log__.debug("メイン：スケジュール登録");
        Sch040Form schForm = new Sch040Form();
        schForm.setCmd(GSConstSchedule.CMD_ADD);
        schForm.setDspMod(GSConstSchedule.DSP_MOD_MAIN);
        schForm.setSch010DspDate(form.getSchWeekDate());
        schForm.setSch010SelectDate(form.getSchSelectDate());
        schForm.setSch010SelectUsrSid(String.valueOf(form.getSchDspGrpSid()));
        schForm.setSch010SelectUsrKbn(String.valueOf(GSConstSchedule.USER_KBN_GROUP));
        schForm.setSchWeekDate(form.getSchWeekDate());
        req.setAttribute("sch040Form", schForm);
        forward = map.findForward("sch040");
        return forward;
    }
    /**
     * スケジュール編集画面へ遷移
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doScheduleEdit(ActionMapping map, SchPtl010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        ActionForward forward = null;

        RequestModel reqMdl = getRequestModel(req);
        Sch010Biz biz = new Sch010Biz(reqMdl);
        if (biz.isEditOk(Integer.parseInt(form.getSchSelectSchSid()), reqMdl, con)) {
            log__.debug("メイン：スケジュール編集");
            Sch040Form schForm = new Sch040Form();
            schForm.setCmd(GSConstSchedule.CMD_EDIT);
            schForm.setDspMod(GSConstSchedule.DSP_MOD_MAIN);
            schForm.setSch010DspDate(form.getSchWeekDate());
            schForm.setSch010SelectDate(form.getSchSelectDate());
            schForm.setSch010SchSid(form.getSchSelectSchSid());
            schForm.setSch010SelectUsrSid(String.valueOf(form.getSchDspGrpSid()));
            schForm.setSch010SelectUsrKbn(String.valueOf(GSConstSchedule.USER_KBN_GROUP));
            schForm.setSchWeekDate(form.getSchWeekDate());
            req.setAttribute("sch040Form", schForm);
            forward = map.findForward("sch040");
        } else {
            log__.debug("メイン：スケジュール閲覧");
            Sch040knForm schForm = new Sch040knForm();
            schForm.setCmd(GSConstSchedule.CMD_EDIT);
            schForm.setDspMod(GSConstSchedule.DSP_MOD_MAIN);
            schForm.setSch010DspDate(form.getSchWeekDate());
            schForm.setSch010SelectDate(form.getSchSelectDate());
            schForm.setSch010SchSid(form.getSchSelectSchSid());
            schForm.setSch010SelectUsrSid(String.valueOf(form.getSchDspGrpSid()));
            schForm.setSch010SelectUsrKbn(String.valueOf(GSConstSchedule.USER_KBN_GROUP));
            schForm.setSchWeekDate(form.getSchWeekDate());
            req.setAttribute("sch040knForm", schForm);
            forward = map.findForward("sch040kn");
        }
        return forward;
    }

    /**
     * スケジュール月間画面へ遷移
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doScheduleMonth(ActionMapping map, SchPtl010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        ActionForward forward = null;

//        Sch010Biz biz = new Sch010Biz();
        log__.debug("メイン：スケジュール月間");
        Sch020Form schForm = new Sch020Form();
        schForm.setCmd("month");
        schForm.setSch010DspDate(form.getSchWeekDate());
        schForm.setSch020SelectUsrSid(String.valueOf(GSConstSchedule.SCHEDULE_GROUP));
        schForm.setSch010SelectUsrKbn(String.valueOf(GSConstSchedule.USER_KBN_GROUP));
        schForm.setSch010DspGpSid(String.valueOf(form.getSchDspGrpSid()));
        req.setAttribute("sch020Form", schForm);
        forward = map.findForward("sch020");

        return forward;
    }
    /**
     * スケジュール月間画面へ遷移
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     * @throws GSException GS用汎実行例外
     */
    private ActionForward __doScheduleDay(ActionMapping map, SchPtl010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException, GSException {
        ActionForward forward = null;

        log__.debug("メイン：スケジュール日間");
        Sch030Form schForm = new Sch030Form();
        schForm.setCmd("day");
        schForm.setSch010DspDate(form.getSchSelectDate());
        schForm.setSch010SelectUsrKbn(String.valueOf(GSConstSchedule.USER_KBN_GROUP));
        schForm.setSch010DspGpSid(String.valueOf(form.getSchDspGrpSid()));

        req.setAttribute("sch030Form", schForm);
        forward = map.findForward("sch030");

        return forward;
    }

    /**
     * スケジュール週間画面へ遷移
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doScheduleWeek(ActionMapping map, SchPtl010Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        ActionForward forward = null;

//        Sch010Biz biz = new Sch010Biz();
        log__.debug("メイン：スケジュール週間");
        Sch010Form schForm = new Sch010Form();
        schForm.setCmd("");
        schForm.setSch010DspGpSid(String.valueOf(form.getSchDspGrpSid()));
        req.setAttribute("sch010Form", schForm);
        forward = map.findForward("sch010");

        return forward;
    }
}
