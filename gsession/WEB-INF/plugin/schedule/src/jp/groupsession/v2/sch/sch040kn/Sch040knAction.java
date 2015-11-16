package jp.groupsession.v2.sch.sch040kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.sch.AbstractScheduleAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] スケジュール確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch040knAction extends AbstractScheduleAction {

    /** エラーの種類 閲覧不可データ */
    private static final int ERRORTYPE_VIEW__ = 0;
    /** エラーの種類 データなし */
    private static final int ERRORTYPE_NONE__ = 1;

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch040knAction.class);

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

        log__.debug("START_SCH040kn");
        ActionForward forward = null;
        Sch040knForm uform = (Sch040knForm) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD==>" + cmd);
        if (cmd.equals("040kn_week")) {
            //日間スケジュール
            forward = map.findForward("040kn_week");
        } else if (cmd.equals("040kn_month")) {
            //月間スケジュール
            forward = map.findForward("040kn_month");
        } else if (cmd.equals("040kn_day")) {
            //日間スケジュール
            forward = map.findForward("040kn_day");
        } else if (cmd.equals("040kn_back")) {
            //画面遷移先を取得
            forward = __doBack(map, uform, req, res, con);
        } else if (cmd.equals("040kn_commit")) {
            //登録・更新
            forward = __doCommit(map, uform, req, res, con);
        } else {
            //スケジュール詳細・確認
            forward = __doInit(map, uform, req, res, con);

        }
        log__.debug("forward==>" + forward.getPath());
        log__.debug("END_SCH040kn");
        return forward;
    }

    /**
     * <br>リクエストを解析し画面遷移先を取得する
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Sch040knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        con.setAutoCommit(true);
        ActionForward forward = null;
        RequestModel reqMdl = getRequestModel(req);
        Sch040knBiz biz = new Sch040knBiz(reqMdl);

        Sch040knParamModel paramMdl = new Sch040knParamModel();
        paramMdl.setParam(form);
        biz.getInitData(paramMdl, con);
        paramMdl.setFormData(form);

        if (form.getCmd().equals(GSConstSchedule.CMD_EDIT)) {
            if (!form.isSch040ViewFlg()) {
                //編集データ閲覧権限チェック
                forward = __doDataError(map, form, req, res, con, ERRORTYPE_VIEW__);
            } else if (!form.isSch040DataFlg()) {
                //編集データ有無チェック
                forward = __doDataError(map, form, req, res, con, ERRORTYPE_NONE__);
            } else {
                forward = map.getInputForward();
            }
        } else {
            forward = map.getInputForward();
        }
        con.setAutoCommit(false);
        return forward;
    }

    /**
     * <br>リクエストを解析し画面遷移先を取得する
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doBack(ActionMapping map, Sch040knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        ActionForward forward = null;
        String dspMod = form.getDspMod();
        String listMod = form.getListMod();
        if (listMod.equals(GSConstSchedule.DSP_MOD_LIST)) {
            forward = map.findForward("040kn_list");
            return forward;
        }
        if (dspMod.equals(GSConstSchedule.DSP_MOD_WEEK)) {
            forward = map.findForward("040kn_week");
        } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MONTH)) {
            forward = map.findForward("040kn_month");
        } else if (dspMod.equals(GSConstSchedule.DSP_MOD_DAY)) {
            forward = map.findForward("040kn_day");
        } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MAIN)) {
            forward = map.findForward("040kn_main");
        } else if (dspMod.equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
            forward = map.findForward("040kn_kojin");
        } else {
            //デフォルト遷移先(例外処理)
            forward = map.findForward("040kn_week");
        }
        return forward;
    }

    /**
     * <br>処理モードによって登録・修正処理を行う
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doCommit(ActionMapping map, Sch040knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {
        ActionForward forward = null;

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            forward = getSubmitErrorPage(map, req);
            return forward;
        }
        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        //アプリケーションRoot
        String appRootPath = getAppRootPath();
        //プラグイン設定
        PluginConfig plconf = getPluginConfig(req);

        PluginConfig pconfig = getPluginConfigForMain(plconf, con);
        CommonBiz cmnBiz = new CommonBiz();
        boolean smailPluginUseFlg = cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig);

        RequestModel reqMdl = getRequestModel(req);
        MlCountMtController cntCon = getCountMtController(req);
        Sch040knBiz biz = new Sch040knBiz(con, reqMdl, cntCon);
        boolean commitFlg = false;
        con.setAutoCommit(false);
        try {
            //新規登録
            if (form.getCmd().equals(GSConstSchedule.CMD_ADD)) {

                Sch040knParamModel paramMdl = new Sch040knParamModel();
                paramMdl.setParam(form);
                biz.insertScheduleDate(reqMdl, paramMdl,
                        sessionUsrSid, appRootPath, plconf, smailPluginUseFlg);
                paramMdl.setFormData(form);

            } else if (form.getCmd().equals(GSConstSchedule.CMD_EDIT)) {
                Sch040knParamModel paramMdl = new Sch040knParamModel();
                paramMdl.setParam(form);
                biz.updateScheduleDate(paramMdl, sessionUsrSid, appRootPath, plconf, con);
                paramMdl.setFormData(form);
            }
            commitFlg = true;
        } catch (Exception e) {
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
     * <br>登録・更新完了画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doCompDsp(ActionMapping map, Sch040knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //スケジュール登録完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        if (form.getListMod().equals(GSConstSchedule.DSP_MOD_LIST)) {
            urlForward = map.findForward("040kn_list");
        } else {
            if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_WEEK)) {
                urlForward = map.findForward("040kn_week");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MONTH)) {

                urlForward = map.findForward("040kn_month");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_DAY)) {
                urlForward = map.findForward("040kn_day");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MAIN)) {
                urlForward = map.findForward("040kn_main");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
                urlForward = map.findForward("040kn_kojin");
            } else {
                urlForward = map.findForward("040kn_week");
            }
        }
        GsMessage gsMsg = new GsMessage();
        //スケジュール
        String textSchedule = gsMsg.getMessage(req, "schedule.108");
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("touroku.kanryo.object",
                textSchedule));
        cmn999Form.addHiddenParam("sch010DspDate", form.getSch010DspDate());
        cmn999Form.addHiddenParam("changeDateFlg", form.getChangeDateFlg());
        cmn999Form.addHiddenParam("sch010DspGpSid", form.getSch010DspGpSid());
        cmn999Form.addHiddenParam("sch010SelectUsrSid", form.getSch010SelectUsrSid());
        cmn999Form.addHiddenParam("sch010SelectUsrKbn", form.getSch010SelectUsrKbn());
        cmn999Form.addHiddenParam("sch010SelectDate", form.getSch010SelectDate());
        cmn999Form.addHiddenParam("sch020SelectUsrSid", form.getSch020SelectUsrSid());
        cmn999Form.addHiddenParam("schWeekDate", form.getSchWeekDate());
        cmn999Form.addHiddenParam("schDailyDate", form.getSchDailyDate());
        cmn999Form.addHiddenParam("sch100SelectUsrSid", form.getSch100SelectUsrSid());
        cmn999Form.addHiddenParam("sch100PageNum", form.getSch100PageNum());
        cmn999Form.addHiddenParam("sch100Slt_page1", form.getSch100Slt_page1());
        cmn999Form.addHiddenParam("sch100Slt_page2", form.getSch100Slt_page2());
        cmn999Form.addHiddenParam("sch100OrderKey1", form.getSch100OrderKey1());
        cmn999Form.addHiddenParam("sch100SortKey1", form.getSch100SortKey1());
        cmn999Form.addHiddenParam("sch100OrderKey2", form.getSch100OrderKey2());
        cmn999Form.addHiddenParam("sch100SortKey2", form.getSch100SortKey2());
        cmn999Form.addHiddenParam("sch100SvOrderKey1", form.getSch100SvOrderKey1());
        cmn999Form.addHiddenParam("sch100SvSortKey1", form.getSch100SvSortKey1());
        cmn999Form.addHiddenParam("sch100SvOrderKey2", form.getSch100SvOrderKey2());
        cmn999Form.addHiddenParam("sch100SortKey2", form.getSch100SvSortKey2());

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>[機  能] スケジュールデータに関するエラーページ設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param errorType エラーの種類
     * @return ActionForward
     */
    private ActionForward __doDataError(ActionMapping map, Sch040knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con,
            int errorType) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;
        GsMessage gsMsg = new GsMessage();

        //スケジュール登録完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        String listMod = NullDefault.getString(form.getListMod(), "");
        if (listMod.equals(GSConstSchedule.DSP_MOD_LIST)) {
            urlForward = map.findForward("040kn_list");
        } else {
            String dspMod = NullDefault.getString(form.getDspMod(), "");
            if (dspMod.equals(GSConstSchedule.DSP_MOD_WEEK)) {
                urlForward = map.findForward("040kn_week");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MONTH)) {

                urlForward = map.findForward("040kn_month");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_DAY)) {
                urlForward = map.findForward("040kn_day");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MAIN)) {
                urlForward = map.findForward("040kn_main");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
                urlForward = map.findForward("040kn_kojin");
            } else {
                urlForward = map.findForward("040kn_week");
            }
        }

        //エラーメッセージ
        if (errorType == ERRORTYPE_NONE__) {
            //変更
            String textSchedule = gsMsg.getMessage(req, "schedule.108");
            String textChange = gsMsg.getMessage(req, "cmn.change");
            cmn999Form.setMessage(msgRes.getMessage("error.none.edit.data",
                    textSchedule, textChange));
        } else if (errorType == ERRORTYPE_VIEW__) {
            cmn999Form.setMessage(msgRes.getMessage("error.notaccess.scd"));
        }

        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.addHiddenParam("sch010DspDate", form.getSch010DspDate());
        cmn999Form.addHiddenParam("changeDateFlg", form.getChangeDateFlg());
        cmn999Form.addHiddenParam("sch010DspGpSid", form.getSch010DspGpSid());
        cmn999Form.addHiddenParam("sch010SelectUsrSid", form.getSch010SelectUsrSid());
        cmn999Form.addHiddenParam("sch010SelectUsrKbn", form.getSch010SelectUsrKbn());
        cmn999Form.addHiddenParam("sch010SelectDate", form.getSch010SelectDate());
        cmn999Form.addHiddenParam("schWeekDate", form.getSchWeekDate());
        cmn999Form.addHiddenParam("schDailyDate", form.getSchDailyDate());
        cmn999Form.addHiddenParam("sch100SelectUsrSid", form.getSch100SelectUsrSid());
        cmn999Form.addHiddenParam("sch100PageNum", form.getSch100PageNum());
        cmn999Form.addHiddenParam("sch100Slt_page1", form.getSch100Slt_page1());
        cmn999Form.addHiddenParam("sch100Slt_page2", form.getSch100Slt_page2());
        cmn999Form.addHiddenParam("sch100OrderKey1", form.getSch100OrderKey1());
        cmn999Form.addHiddenParam("sch100SortKey1", form.getSch100SortKey1());
        cmn999Form.addHiddenParam("sch100OrderKey2", form.getSch100OrderKey2());
        cmn999Form.addHiddenParam("sch100SortKey2", form.getSch100SortKey2());
        cmn999Form.addHiddenParam("sch100SvOrderKey1", form.getSch100SvOrderKey1());
        cmn999Form.addHiddenParam("sch100SvSortKey1", form.getSch100SvSortKey1());
        cmn999Form.addHiddenParam("sch100SvOrderKey2", form.getSch100SvOrderKey2());
        cmn999Form.addHiddenParam("sch100SortKey2", form.getSch100SvSortKey2());

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }
}
