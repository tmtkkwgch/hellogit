package jp.groupsession.v2.sch.sch041kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.sch.AbstractScheduleAction;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.sch.sch040.Sch040Biz;
import jp.groupsession.v2.sch.sch041.Sch041Biz;
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
 * <br>[機  能] スケジュール繰り返し登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch041knAction extends AbstractScheduleAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch041knAction.class);

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

        log__.debug("START_SCH041kn");
        ActionForward forward = null;
        Sch041knForm uform = (Sch041knForm) form;

        //アクセス不可グループ、またはユーザに対してのスケジュール登録を許可しない
        int selectUserSid = NullDefault.getInt(uform.getSch010SelectUsrSid(), -1);
        if (selectUserSid >= 0) {
            int sessionUserSid = getSessionUserSid(req);
            String selectUsrKbn = NullDefault.getString(uform.getSch010SelectUsrKbn(), "");
            SchDao schDao = new SchDao(con);
            if (selectUsrKbn.equals(String.valueOf(GSConstSchedule.USER_KBN_GROUP))) {
                //グループスケジュール登録権限チェック
                if (!schDao.canRegistGroupSchedule(selectUserSid, sessionUserSid)) {
                    return getSubmitErrorPage(map, req);
                }
            } else {
                //ユーザスケジュール登録権限チェック
                if (!schDao.canRegistUserSchedule(selectUserSid, sessionUserSid)) {
                    return getSubmitErrorPage(map, req);
                }
            }
        }

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();
        Sch041Biz biz = new Sch041Biz(getRequestModel(req));
        int schSid = NullDefault.getInt(uform.getSch010SchSid(), -1);
        if ((uform.getCmd().equals(GSConstSchedule.CMD_EDIT)
                || uform.getCmd().equals(GSConstSchedule.CMD_DELETE))
                && !biz.isExistData(schSid, con)) {
            //データが存在しない
            return __doNoneDataError(map, uform, req, res, con);
        }
        log__.debug("CMD==>" + cmd);
        if (cmd.equals("041kn_del_ok")) {
             //削除更新実行
             forward = __doDeleteOk(map, uform, req, res, con);
        } else if (cmd.equals("041kn_commit")) {
            //登録実行 重複登録チェック有
            forward = __doCommit(map, uform, req, res, con, true);
        } else if (cmd.equals("041kn_commit_dup_nocheck")) {
            //登録実行 重複登録チェック無
            forward = __doCommit(map, uform, req, res, con, false);

        } else if (cmd.equals("041kn_back")) {
            //戻る
            forward = __doBack(map, uform, req, res, con);
        } else {
            //初期表示
            __doInit(map, uform, req, res, con);
            forward = map.getInputForward();
        }
        log__.debug("END_SCH041kn");
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
    private void __doInit(ActionMapping map, Sch041knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {
        log__.debug("SCH041kn.__doInit");
        con.setAutoCommit(true);
        RequestModel reqMdl = getRequestModel(req);
        Sch041knBiz biz = new Sch041knBiz(con, reqMdl);

        Sch041knParamModel paramMdl = new Sch041knParamModel();
        paramMdl.setParam(form);
        biz.getInitData(paramMdl, getPluginConfig(req), con);
        paramMdl.setFormData(form);

        Sch041Biz biz041 = new Sch041Biz(reqMdl);
        paramMdl = new Sch041knParamModel();
        paramMdl.setParam(form);
        biz041.setCompanyData(paramMdl, con, getSessionUserModel(req).getUsrsid(), reqMdl);
        paramMdl.setFormData(form);

        // トランザクショントークン設定
        saveToken(req);
        con.setAutoCommit(false);
    }

    /**
     * <br>確定ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param warnFlg 重複登録警告チェックフラグ
     * @return ActionForward 画面遷移先
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doCommit(ActionMapping map, Sch041knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con,
            boolean warnFlg)
    throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }
        GsMessage gsMsg = new GsMessage(req);
        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        RequestModel reqMdl = getRequestModel(req);

        //アプリケーションRoot
        String appRootPath = getAppRootPath();
        //プラグイン設定
        PluginConfig plconf = getPluginConfig(req);

        ActionForward forward = null;
        ActionErrors errors = form.validateCheck(reqMdl, con);
        if (errors.size() == 0) {
            //有効な日付があるかチェック
            Sch041knBiz biz = new Sch041knBiz(con, reqMdl);
            HashMap<String, UDate> dateMap = biz.getInsertDateList(form, sessionUsrSid, con);
            if (dateMap.isEmpty()) {
                //日付
                String textDate = gsMsg.getMessage("cmn.date2");
                ActionMessage msg = new ActionMessage("search.data.notfound", textDate);
                errors.add("search.data.notfound", msg);
            }
        }
        if (errors.size() > 0) {
            addErrors(req, errors);
            __doInit(map, form, req, res, con);
            log__.debug("入力エラー");
            return map.getInputForward();
        }

        if (warnFlg) {
            //重複登録警告チェック
            forward = __doDupWarningCheck(map, form, req, res, con);
            if (forward != null) {
                return forward;
            }
        }
        //更新登録の場合旧データの存在チェック
        if (form.getCmd().equals(GSConstSchedule.CMD_EDIT)) {
            SchCommonBiz biz = new SchCommonBiz(reqMdl);
            SchAdmConfModel adminConf = biz.getAdmConfModel(con);
            Sch040Biz sch040biz = new Sch040Biz(con, reqMdl);
            ScheduleSearchModel oldMdl = sch040biz.getSchData(
                    Integer.parseInt(form.getSch010SchSid()), adminConf, con);
            if (oldMdl == null) {
                forward = __doNoneDataError(map, form, req, res, con);
            }
        }

        PluginConfig pconfig = getPluginConfigForMain(plconf, con);
        CommonBiz cmnBiz = new CommonBiz();
        boolean smailPluginUseFlg = cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig);

        Sch041knBiz biz = new Sch041knBiz(con, reqMdl);
        boolean commitFlg = false;
        con.setAutoCommit(false);
        try {

            //会社情報の設定
            Sch040Biz biz040 = new Sch040Biz(con, reqMdl);
            Sch041knParamModel paramMdl = new Sch041knParamModel();
            paramMdl.setParam(form);
            biz040.setCompanyData(paramMdl, con, sessionUsrSid, reqMdl);
            paramMdl.setFormData(form);

            MlCountMtController cntCon = getCountMtController(req);
            //新規登録
            if (form.getCmd().equals(GSConstSchedule.CMD_ADD)) {
                //採番マスタからスケジュールSIDを取得
                paramMdl = new Sch041knParamModel();
                paramMdl.setParam(form);
                biz.insertScheduleDate(paramMdl, cntCon, sessionUsrSid,
                        appRootPath, plconf, smailPluginUseFlg, reqMdl);
                paramMdl.setFormData(form);

            } else if (form.getCmd().equals(GSConstSchedule.CMD_EDIT)) {
                paramMdl = new Sch041knParamModel();
                paramMdl.setParam(form);
                biz.updateScheduleDate(paramMdl, cntCon, sessionUsrSid,
                        appRootPath, plconf, smailPluginUseFlg, reqMdl);
                paramMdl.setFormData(form);

            }
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

        /** メッセージ 登録 **/
        String entry = gsMsg.getMessage("cmn.entry");
        /** メッセージ 変更 **/
        String change = gsMsg.getMessage("cmn.change");
        /** メッセージ 内容 **/
        String content = gsMsg.getMessage("cmn.content");

        //ログ出力処理
        SchCommonBiz schBiz = new SchCommonBiz(con, reqMdl);
        //開始日時
        int startHour = NullDefault.getInt(form.getSch041FrHour(), 0);
        int startMin = NullDefault.getInt(form.getSch041FrMin(), 0);
        String startDate
           = gsMsg.getMessage("cmn.view.date", new String[] {
                   form.getSch041FrYear(),
                   form.getSch041FrMonth(),
                   form.getSch041FrDay(),
                   String.valueOf(startHour),
                   String.valueOf(startMin)
            });

        //登録or編集
        String opCode = "";
        if (form.getCmd().equals(GSConstSchedule.CMD_ADD)) {
            opCode = entry;
        } else if (form.getCmd().equals(GSConstSchedule.CMD_EDIT)) {
            opCode = change;
        }
        //登録対象者名
        String userName = biz.getUsrName(
                Integer.parseInt(form.getSch010SelectUsrSid()),
                Integer.parseInt(form.getSch010SelectUsrKbn()), con);
        //施設予約
        ArrayList<RsvSisDataModel> sisetuList =
                biz.getReserveNameList(form.getSch041SvReserve(), con);
        //施設名リスト
        ArrayList<String> sisNameList = new ArrayList<String>();
        //施設リストを元に施設名を取り出して、施設名リストに格納する
        for (RsvSisDataModel rsvsisMdl : sisetuList) {
            sisNameList.add(rsvsisMdl.getRsdName());
        }
        //施設同時登録されていない場合、施設名を表示しない
        String sisetuName = null;
        if (sisNameList != null && sisNameList.size() > 0) {
            sisetuName = "[" + gsMsg.getMessage("cmn.facility.name") + "]" + sisNameList;
        } else {
            sisetuName = "";
        }
        schBiz.outPutLog(
                map, req, res, opCode, GSConstLog.LEVEL_TRACE,
                "[" + gsMsg.getMessage("sml.155") + "]" + userName + "\n"
                + "[" + gsMsg.getMessage("schedule.sch100.11") + "]" + startDate + "\n"
                + "[" + gsMsg.getMessage("cmn.title") + "]" + form.getSch041Title() + "\n"
                + "[" + content + "]" + form.getSch041Value() + "\n"
                + sisetuName

                );

        forward = __doCompDsp(map, form, req, res, con);
        return forward;
    }

    /**
     * <br>削除処理実行
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDeleteOk(ActionMapping map, Sch041knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        ActionForward forward = null;
        RequestModel reqMdl = getRequestModel(req);

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }
        ActionErrors errors = form.validatePowerCheck(reqMdl, con);
        if (errors.size() > 0) {
            addErrors(req, errors);
            __doInit(map, form, req, res, con);
            log__.debug("入力エラー");
            return map.getInputForward();
        }
        boolean commitFlg = false;
        con.setAutoCommit(false);
        ScheduleSearchModel scdMdl = new ScheduleSearchModel();
        Sch041knBiz sch041knBiz = new Sch041knBiz(con, reqMdl);
        Sch041knParamModel paramMdl = new Sch041knParamModel();
        paramMdl.setParam(form);
        //DBの施設予約情報取得
        String[] sisetu =
                sch041knBiz.setSch041knReservesFromDb(paramMdl, con);
        //施設リストの生成
        ArrayList<RsvSisDataModel> sisetuList =
                sch041knBiz.getReserveNameList(sisetu, con);
        //施設名リスト
        ArrayList<String> sisNameList = new ArrayList<String>();
        //施設リストを元に施設名を取り出して、施設名リストに格納する
        for (RsvSisDataModel rsvsisMdl : sisetuList) {
            sisNameList.add(rsvsisMdl.getRsdName());
        }
        try {
            //管理者設定を取得
            SchCommonBiz cbiz = new SchCommonBiz(reqMdl);
            SchAdmConfModel adminConf = cbiz.getAdmConfModel(con);
            Sch040Biz sch040Biz = new Sch040Biz(con, reqMdl);
            int scdSid = NullDefault.getInt(form.getSch010SchSid(), -1);

            //旧同時登録施設予約を削除
//            sch040Biz.deleteReserve(scdSid, con);
            scdMdl = sch040Biz.getSchData(scdSid, adminConf, con);

            if (scdMdl == null) {
                return __doNoneDataError(map, form, req, res, con);
            }

            int sceSid = scdMdl.getSceSid();

//          旧同時登録施設予約を削除
            sch041knBiz.deleteReserves(scdSid, con);
            //スケジュール情報を削除
            sch041knBiz.deleteScheduleEx(sceSid, con, getSessionUserSid(req));

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
        GsMessage gsMsg = new GsMessage();
        /** メッセージ 削除 **/
        String delete = gsMsg.getMessage(req, "cmn.delete");
      //開始日時
        String frDate = UDateUtil.getYymdJ(scdMdl.getScdFrDate(), reqMdl)
                + UDateUtil.getSeparateHMJ(scdMdl.getScdFrDate(), reqMdl);
      //グループが対象ユーザの場合、名を空欄指定
        String userMei = scdMdl.getScdUsrMei();
        if (scdMdl.getScdUsrMei() == null) {
            userMei = "";
        }

        //施設同時登録されていない場合、施設名を表示しない
        String sisetuName = null;
        if (sisNameList != null && sisNameList.size() > 0) {
            sisetuName = "[" + gsMsg.getMessage("cmn.facility.name") + "]" + sisNameList;
        } else {
            sisetuName = "";
        }
        //ログ出力処理
        SchCommonBiz schBiz = new SchCommonBiz(con, reqMdl);
        schBiz.outPutLog(map, req, res, delete, GSConstLog.LEVEL_TRACE,
                "[" + gsMsg.getMessage("sml.155") + "]" + scdMdl.getScdUsrSei() + userMei + "\n"
                + "[" + gsMsg.getMessage("schedule.sch100.11") + "]" + frDate + "\n"
                + "[" + gsMsg.getMessage("cmn.title") + "]" + scdMdl.getScdTitle() + "\n"
                + "[" + gsMsg.getMessage("cmn.content") + "]" + scdMdl.getScdValue() + "\n"
                + sisetuName
                );

        forward = __doDeleteCompDsp(map, form, req, res, con);
        return forward;
    }

    /**
     * <br>削除完了画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doDeleteCompDsp(ActionMapping map, Sch041knForm form,
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
            urlForward = map.findForward("041kn_list");
        } else {
            if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_WEEK)) {
                urlForward = map.findForward("041kn_week");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MONTH)) {
                urlForward = map.findForward("041kn_month");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_DAY)) {
                urlForward = map.findForward("041kn_day");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MAIN)) {
                urlForward = map.findForward("041kn_main");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
                urlForward = map.findForward("041kn_kojin");
            } else {
                urlForward = map.findForward("041kn_week");
            }
        }
        GsMessage gsMsg = new GsMessage();
        //スケジュール
        String textSchedule = gsMsg.getMessage(req, "schedule.108");
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("sakujo.kanryo.object",
                textSchedule));
        cmn999Form.addHiddenParam("dspMod", form.getDspMod());
        cmn999Form.addHiddenParam("listMod", form.getListMod());
        cmn999Form.addHiddenParam("sch010DspDate", form.getSch010DspDate());
        cmn999Form.addHiddenParam("changeDateFlg", form.getChangeDateFlg());
        cmn999Form.addHiddenParam("sch010DspGpSid", form.getSch010DspGpSid());
        cmn999Form.addHiddenParam("sch010SelectUsrSid", form.getSch010SelectUsrSid());
        cmn999Form.addHiddenParam("sch010SelectUsrKbn", form.getSch010SelectUsrKbn());
        cmn999Form.addHiddenParam("sch010searchWord", form.getSch010searchWord());
        cmn999Form.addHiddenParam("sch020SelectUsrSid", form.getSch020SelectUsrSid());
        //一覧画面用
        cmn999Form.addHiddenParam("sch100PageNum", form.getSch100PageNum());
        cmn999Form.addHiddenParam("sch100Slt_page1", form.getSch100Slt_page1());
        cmn999Form.addHiddenParam("sch100Slt_page2", form.getSch100Slt_page2());
        cmn999Form.addHiddenParam("sch100OrderKey1", form.getSch100OrderKey1());
        cmn999Form.addHiddenParam("sch100SortKey1", form.getSch100SortKey1());
        cmn999Form.addHiddenParam("sch100OrderKey2", form.getSch100OrderKey2());
        cmn999Form.addHiddenParam("sch100SortKey2", form.getSch100SortKey2());
        cmn999Form.addHiddenParam("sch100SelectUsrSid", form.getSch100SelectUsrSid());
        cmn999Form.addHiddenParam("sch100SvSltGroup", form.getSch100SvSltGroup());
        cmn999Form.addHiddenParam("sch100SvSltUser", form.getSch100SvSltUser());
        cmn999Form.addHiddenParam("sch100SvSltStartYearFr", form.getSch100SvSltStartYearFr());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthFr", form.getSch100SvSltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltStartDayFr", form.getSch100SvSltStartDayFr());
        cmn999Form.addHiddenParam("sch100SvSltStartYearTo", form.getSch100SvSltStartYearTo());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthTo", form.getSch100SvSltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltStartDayTo", form.getSch100SvSltStartDayTo());
        cmn999Form.addHiddenParam("sch100SvSltEndYearFr", form.getSch100SvSltEndYearFr());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthFr", form.getSch100SvSltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltEndDayFr", form.getSch100SvSltEndDayFr());
        cmn999Form.addHiddenParam("sch100SvSltEndYearTo", form.getSch100SvSltEndYearTo());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthTo", form.getSch100SvSltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltEndDayTo", form.getSch100SvSltEndDayTo());
        cmn999Form.addHiddenParam("sch100SvKeyWordkbn", form.getSch100SvKeyWordkbn());
        cmn999Form.addHiddenParam("sch100SvKeyValue", form.getSch100SvKeyValue());
        cmn999Form.addHiddenParam("sch100SvOrderKey1", form.getSch100SvOrderKey1());
        cmn999Form.addHiddenParam("sch100SvSortKey1", form.getSch100SvSortKey1());
        cmn999Form.addHiddenParam("sch100SvOrderKey2", form.getSch100SvOrderKey2());
        cmn999Form.addHiddenParam("sch100SvSortKey2", form.getSch100SvSortKey2());

        cmn999Form.addHiddenParam("sch100SltGroup", form.getSch100SltGroup());
        cmn999Form.addHiddenParam("sch100SltUser", form.getSch100SltUser());
        cmn999Form.addHiddenParam("sch100SltStartYearFr", form.getSch100SltStartYearFr());
        cmn999Form.addHiddenParam("sch100SltStartMonthFr", form.getSch100SltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SltStartDayFr", form.getSch100SltStartDayFr());
        cmn999Form.addHiddenParam("sch100SltStartYearTo", form.getSch100SltStartYearTo());
        cmn999Form.addHiddenParam("sch100SltStartMonthTo", form.getSch100SltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SltStartDayTo", form.getSch100SltStartDayTo());
        cmn999Form.addHiddenParam("sch100SltEndYearFr", form.getSch100SltEndYearFr());
        cmn999Form.addHiddenParam("sch100SltEndMonthFr", form.getSch100SltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SltEndDayFr", form.getSch100SltEndDayFr());
        cmn999Form.addHiddenParam("sch100SltEndYearTo", form.getSch100SltEndYearTo());
        cmn999Form.addHiddenParam("sch100SltEndMonthTo", form.getSch100SltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SltEndDayTo", form.getSch100SltEndDayTo());
        cmn999Form.addHiddenParam("sch100KeyWordkbn", form.getSch100KeyWordkbn());
        cmn999Form.addHiddenParam("sch100SvBgcolor", form.getSch100SvBgcolor());
        cmn999Form.addHiddenParam("sch100Bgcolor", form.getSch100Bgcolor());
        cmn999Form.addHiddenParam("sch100CsvOutField", form.getSch100CsvOutField());

        //検索対象
        String[] searchTarget = form.getSch100SearchTarget();
        if (searchTarget != null) {
            for (String target : searchTarget) {
                cmn999Form.addHiddenParam("sch100SearchTarget", target);
            }
        }
        //検索対象
        String[] svSearchTarget = form.getSch100SvSearchTarget();
        if (svSearchTarget != null) {
            for (String target : svSearchTarget) {
                cmn999Form.addHiddenParam("sch100SvSearchTarget", target);
            }
        }
        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
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
     */
    private ActionForward __doBack(ActionMapping map, Sch041knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {

        ActionForward forward = null;

//        String cmn = form.getCmd();
        forward = map.findForward("041kn_back");
        return forward;
    }

    /**
     * 登録・更新完了画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doCompDsp(ActionMapping map, Sch041knForm form,
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
            urlForward = map.findForward("041kn_list");
        } else {

            if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_WEEK)) {
                urlForward = map.findForward("041kn_week");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MONTH)) {

                urlForward = map.findForward("041kn_month");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_DAY)) {
                urlForward = map.findForward("041kn_day");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MAIN)) {
                urlForward = map.findForward("041kn_main");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
                urlForward = map.findForward("041kn_kojin");
            } else {
                urlForward = map.findForward("041kn_week");
            }
        }

        GsMessage gsMsg = new GsMessage();
        /** メッセージ スケジュール **/
        String schedule = gsMsg.getMessage(req, "schedule.108");

        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("touroku.kanryo.object", schedule));

        cmn999Form.addHiddenParam("dspMod", form.getDspMod());
        cmn999Form.addHiddenParam("sch010DspDate", form.getSch010DspDate());
        cmn999Form.addHiddenParam("changeDateFlg", form.getChangeDateFlg());
        cmn999Form.addHiddenParam("sch010DspGpSid", form.getSch010DspGpSid());
        cmn999Form.addHiddenParam("sch010SelectUsrSid", form.getSch010SelectUsrSid());
        cmn999Form.addHiddenParam("sch010SelectUsrKbn", form.getSch010SelectUsrKbn());
        cmn999Form.addHiddenParam("sch010SelectDate", form.getSch010SelectDate());
        cmn999Form.addHiddenParam("sch010searchWord", form.getSch010searchWord());
        cmn999Form.addHiddenParam("sch020SelectUsrSid", form.getSch020SelectUsrSid());
        cmn999Form.addHiddenParam("sch030FromHour", form.getSch030FromHour());
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
        //save
        cmn999Form.addHiddenParam("sch100SvSltGroup", form.getSch100SvSltGroup());
        cmn999Form.addHiddenParam("sch100SvSltUser", form.getSch100SvSltUser());
        cmn999Form.addHiddenParam("sch100SvSltStartYearFr", form.getSch100SvSltStartYearFr());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthFr", form.getSch100SvSltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltStartDayFr", form.getSch100SvSltStartDayFr());
        cmn999Form.addHiddenParam("sch100SvSltStartYearTo", form.getSch100SvSltStartYearTo());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthTo", form.getSch100SvSltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltStartDayTo", form.getSch100SvSltStartDayTo());
        cmn999Form.addHiddenParam("sch100SvSltEndYearFr", form.getSch100SvSltEndYearFr());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthFr", form.getSch100SvSltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltEndDayFr", form.getSch100SvSltEndDayFr());
        cmn999Form.addHiddenParam("sch100SvSltEndYearTo", form.getSch100SvSltEndYearTo());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthTo", form.getSch100SvSltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltEndDayTo", form.getSch100SvSltEndDayTo());
        cmn999Form.addHiddenParam("sch100SvKeyWordkbn", form.getSch100SvKeyWordkbn());
        cmn999Form.addHiddenParam("sch100SvKeyValue", form.getSch100SvKeyValue());
        cmn999Form.addHiddenParam("sch100SvOrderKey1", form.getSch100SvOrderKey1());
        cmn999Form.addHiddenParam("sch100SvSortKey1", form.getSch100SvSortKey1());
        cmn999Form.addHiddenParam("sch100SvOrderKey2", form.getSch100SvOrderKey2());
        cmn999Form.addHiddenParam("sch100SvSortKey2", form.getSch100SvSortKey2());
        cmn999Form.addHiddenParam("sch100SltGroup", form.getSch100SltGroup());
        cmn999Form.addHiddenParam("sch100SltUser", form.getSch100SltUser());
        cmn999Form.addHiddenParam("sch100SltStartYearFr", form.getSch100SltStartYearFr());
        cmn999Form.addHiddenParam("sch100SltStartMonthFr", form.getSch100SltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SltStartDayFr", form.getSch100SltStartDayFr());
        cmn999Form.addHiddenParam("sch100SltStartYearTo", form.getSch100SltStartYearTo());
        cmn999Form.addHiddenParam("sch100SltStartMonthTo", form.getSch100SltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SltStartDayTo", form.getSch100SltStartDayTo());
        cmn999Form.addHiddenParam("sch100SltEndYearFr", form.getSch100SltEndYearFr());
        cmn999Form.addHiddenParam("sch100SltEndMonthFr", form.getSch100SltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SltEndDayFr", form.getSch100SltEndDayFr());
        cmn999Form.addHiddenParam("sch100SltEndYearTo", form.getSch100SltEndYearTo());
        cmn999Form.addHiddenParam("sch100SltEndMonthTo", form.getSch100SltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SltEndDayTo", form.getSch100SltEndDayTo());
        cmn999Form.addHiddenParam("sch100KeyWordkbn", form.getSch100KeyWordkbn());
        cmn999Form.addHiddenParam("sch100SvBgcolor", form.getSch100SvBgcolor());
        cmn999Form.addHiddenParam("sch100Bgcolor", form.getSch100Bgcolor());
        cmn999Form.addHiddenParam("sch100CsvOutField", form.getSch100CsvOutField());

        //検索対象
        String[] searchTarget = form.getSch100SearchTarget();
        if (searchTarget != null) {
            for (String target : searchTarget) {
                cmn999Form.addHiddenParam("sch100SearchTarget", target);
            }
        }
        //検索対象
        String[] svSearchTarget = form.getSch100SvSearchTarget();
        if (svSearchTarget != null) {
            for (String target : svSearchTarget) {
                cmn999Form.addHiddenParam("sch100SvSearchTarget", target);
            }
        }
        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
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
    private ActionForward __doNoneDataError(ActionMapping map, Sch041knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;
        GsMessage gsMsg = new GsMessage();
        /** メッセージ スケジュール **/
        String schedule = gsMsg.getMessage(req, "schedule.108");

        //スケジュール登録完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        String listMod = NullDefault.getString(form.getListMod(), "");
        String dspMod = form.getDspMod();
        if (listMod.equals(GSConstSchedule.DSP_MOD_LIST)) {
            urlForward = map.findForward("041kn_list");
        } else {
            if (dspMod.equals(GSConstSchedule.DSP_MOD_WEEK)) {
                urlForward = map.findForward("041kn_week");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MONTH)) {
                urlForward = map.findForward("041kn_month");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_DAY)) {
                urlForward = map.findForward("041kn_day");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
                urlForward = map.findForward("041kn_kojin");
            } else if (dspMod.equals(GSConstSchedule.DSP_MOD_MAIN)) {
                urlForward = map.findForward("041kn_main");
            }
        }

        cmn999Form.setUrlOK(urlForward.getPath());
        //変更
        String textChange = gsMsg.getMessage(req, "cmn.change");
        cmn999Form.setMessage(msgRes.getMessage("error.none.edit.data", schedule, textChange));

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
        //save
        cmn999Form.addHiddenParam("sch100SvSltGroup", form.getSch100SvSltGroup());
        cmn999Form.addHiddenParam("sch100SvSltUser", form.getSch100SvSltUser());
        cmn999Form.addHiddenParam("sch100SvSltStartYearFr", form.getSch100SvSltStartYearFr());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthFr", form.getSch100SvSltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltStartDayFr", form.getSch100SvSltStartDayFr());
        cmn999Form.addHiddenParam("sch100SvSltStartYearTo", form.getSch100SvSltStartYearTo());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthTo", form.getSch100SvSltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltStartDayTo", form.getSch100SvSltStartDayTo());
        cmn999Form.addHiddenParam("sch100SvSltEndYearFr", form.getSch100SvSltEndYearFr());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthFr", form.getSch100SvSltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltEndDayFr", form.getSch100SvSltEndDayFr());
        cmn999Form.addHiddenParam("sch100SvSltEndYearTo", form.getSch100SvSltEndYearTo());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthTo", form.getSch100SvSltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltEndDayTo", form.getSch100SvSltEndDayTo());
        cmn999Form.addHiddenParam("sch100SvKeyWordkbn", form.getSch100SvKeyWordkbn());
        cmn999Form.addHiddenParam("sch100SvKeyValue", form.getSch100SvKeyValue());
        cmn999Form.addHiddenParam("sch100SvOrderKey1", form.getSch100SvOrderKey1());
        cmn999Form.addHiddenParam("sch100SvSortKey1", form.getSch100SvSortKey1());
        cmn999Form.addHiddenParam("sch100SvOrderKey2", form.getSch100SvOrderKey2());
        cmn999Form.addHiddenParam("sch100SvSortKey2", form.getSch100SvSortKey2());
        cmn999Form.addHiddenParam("sch100SltGroup", form.getSch100SltGroup());
        cmn999Form.addHiddenParam("sch100SltUser", form.getSch100SltUser());
        cmn999Form.addHiddenParam("sch100SltStartYearFr", form.getSch100SltStartYearFr());
        cmn999Form.addHiddenParam("sch100SltStartMonthFr", form.getSch100SltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SltStartDayFr", form.getSch100SltStartDayFr());
        cmn999Form.addHiddenParam("sch100SltStartYearTo", form.getSch100SltStartYearTo());
        cmn999Form.addHiddenParam("sch100SltStartMonthTo", form.getSch100SltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SltStartDayTo", form.getSch100SltStartDayTo());
        cmn999Form.addHiddenParam("sch100SltEndYearFr", form.getSch100SltEndYearFr());
        cmn999Form.addHiddenParam("sch100SltEndMonthFr", form.getSch100SltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SltEndDayFr", form.getSch100SltEndDayFr());
        cmn999Form.addHiddenParam("sch100SltEndYearTo", form.getSch100SltEndYearTo());
        cmn999Form.addHiddenParam("sch100SltEndMonthTo", form.getSch100SltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SltEndDayTo", form.getSch100SltEndDayTo());
        cmn999Form.addHiddenParam("sch100KeyWordkbn", form.getSch100KeyWordkbn());
        cmn999Form.addHiddenParam("sch100CsvOutField", form.getSch100CsvOutField());

        //検索対象
        String[] searchTarget = form.getSch100SearchTarget();
        if (searchTarget != null) {
            for (String target : searchTarget) {
                cmn999Form.addHiddenParam("sch100SearchTarget", target);
            }
        }
        //検索対象
        String[] svSearchTarget = form.getSch100SvSearchTarget();
        if (svSearchTarget != null) {
            for (String target : svSearchTarget) {
                cmn999Form.addHiddenParam("sch100SvSearchTarget", target);
            }
        }
        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>重複登録警告画面
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDupWarningCheck(ActionMapping map, Sch041knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        RequestModel reqMdl = getRequestModel(req);
        Sch041Biz biz = new Sch041Biz(reqMdl);
        SchCommonBiz schBiz = new SchCommonBiz(con, reqMdl);
        int sessionUsrSid = getSessionUserSid(req);

        //重複登録 警告スケジュール一覧を取得する。
        Sch041knParamModel paramMdl = new Sch041knParamModel();
        paramMdl.setParam(form);
        List<SchDataModel> rptSchList
        = biz.getExSchWarningList(
                reqMdl, paramMdl, sessionUsrSid, con,
                GSConstSchedule.SCH_REPEAT_KBN_WARNING);
        paramMdl.setFormData(form);

        String textSchList = "";
        if (rptSchList != null && rptSchList.size() > 0) {
            int i = 0;
            String title = "";
            for (SchDataModel model : rptSchList) {
                if (i > 0) {
                    textSchList += "<br>";
                }

                //公開区分で判定してタイトルを取得
                title = schBiz.getDspTitle(model, sessionUsrSid);

                textSchList += "・";
                textSchList += StringUtilHtml.transToHTmlPlusAmparsant(model.getScdUserName());
                textSchList += " ";
                textSchList += StringUtilHtml.transToHTmlPlusAmparsant(title);

                textSchList += "(";
                textSchList += UDateUtil.getYymdJ(model.getScdFrDate(), req);
                textSchList += UDateUtil.getSeparateHMJ(model.getScdFrDate(), req);
                textSchList += "～";
                textSchList += UDateUtil.getYymdJ(model.getScdToDate(), req);
                textSchList += UDateUtil.getSeparateHMJ(model.getScdToDate(), req);
                textSchList += ")";

                i++;
            }
        } else {
            //警告がなければNULLを返す
            return null;
        }

        ActionForward forward = null;
        // トランザクショントークン設定
        saveToken(req);

        //警告画面へ
        log__.debug("警告画面へ");
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //権限エラー警告画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("040kn_warning_ok");
        cmn999Form.setUrlOK(urlForward.getPath());
        urlForward = map.findForward("040kn_warning_cancel");
        cmn999Form.setUrlCancel(urlForward.getPath());
        GsMessage gsMsg = new GsMessage();
        //スケジュール
        String textSchedule = gsMsg.getMessage(req, "schedule.108");

        cmn999Form.setMessage(msgRes.getMessage("warning.input.dup.sch",
                                                    textSchedule, textSchList));

        //週間・日間・月間
        cmn999Form.addHiddenParam("cmd", form.getCmd());
        cmn999Form.addHiddenParam("dspMod", form.getDspMod());
        cmn999Form.addHiddenParam("listMod", form.getListMod());
        cmn999Form.addHiddenParam("sch010DspDate", form.getSch010DspDate());
        cmn999Form.addHiddenParam("changeDateFlg", form.getChangeDateFlg());
        cmn999Form.addHiddenParam("sch010DspGpSid", form.getSch010DspGpSid());
        //選択
        cmn999Form.addHiddenParam("sch010SelectUsrSid", form.getSch010SelectUsrSid());
        cmn999Form.addHiddenParam("sch010SelectUsrKbn", form.getSch010SelectUsrKbn());
        cmn999Form.addHiddenParam("sch010SelectDate", form.getSch010SelectDate());
        cmn999Form.addHiddenParam("sch010SchSid", form.getSch010SchSid());
        cmn999Form.addHiddenParam("sch020SelectUsrSid", form.getSch020SelectUsrSid());
        //登録・修正画面
        cmn999Form.addHiddenParam("sch040Bgcolor", form.getSch040Bgcolor());
        cmn999Form.addHiddenParam("sch040Title", form.getSch040Title());
        cmn999Form.addHiddenParam("sch040Value", form.getSch040Value());
        cmn999Form.addHiddenParam("sch040Biko", form.getSch040Biko());
        cmn999Form.addHiddenParam("sch040Public", form.getSch040Public());
        cmn999Form.addHiddenParam("sch040FrYear", form.getSch040FrYear());
        cmn999Form.addHiddenParam("sch040FrMonth", form.getSch040FrMonth());
        cmn999Form.addHiddenParam("sch040FrDay", form.getSch040FrDay());
        cmn999Form.addHiddenParam("sch040FrHour", form.getSch040FrHour());
        cmn999Form.addHiddenParam("sch040FrMin", form.getSch040FrMin());
        cmn999Form.addHiddenParam("sch040ToYear", form.getSch040ToYear());
        cmn999Form.addHiddenParam("sch040ToMonth", form.getSch040ToMonth());
        cmn999Form.addHiddenParam("sch040ToDay", form.getSch040ToDay());
        cmn999Form.addHiddenParam("sch040ToHour", form.getSch040ToHour());
        cmn999Form.addHiddenParam("sch040ToMin", form.getSch040ToMin());
        cmn999Form.addHiddenParam("sch040GroupSid", form.getSch040GroupSid());
        cmn999Form.addHiddenParam("sch040BatchRef", form.getSch040BatchRef());
        cmn999Form.addHiddenParam("sch040ReserveGroupSid", form.getSch040ReserveGroupSid());
        cmn999Form.addHiddenParam("sch040ResBatchRef", form.getSch040ResBatchRef());
        cmn999Form.addHiddenParam("sch040Edit", form.getSch040Edit());

        //同時登録ユーザ
        String[] users = form.getSv_users();
        if (users != null) {
            for (String user : users) {
                cmn999Form.addHiddenParam("sv_users", user);
            }
        }
        //同時登録施設
        String[] reserves = form.getSvReserveUsers();
        if (reserves != null) {
            for (String reserve : reserves) {
                cmn999Form.addHiddenParam("svReserveUsers", reserve);
            }
        }
        cmn999Form.addHiddenParam("sch040contact", form.getSch040contact());
        cmn999Form.addHiddenParam("sch040CompanySid", form.getSch040CompanySid());
        cmn999Form.addHiddenParam("sch040CompanyBaseSid", form.getSch040CompanyBaseSid());
        cmn999Form.addHiddenParam("sch040AddressId", form.getSch040AddressId());

        //拡張登録画面
        cmn999Form.addHiddenParam("sch041ExtKbn", form.getSch041ExtKbn());
        cmn999Form.addHiddenParam("sch041Week", form.getSch041Week());
        cmn999Form.addHiddenParam("sch041Day", form.getSch041Day());
        cmn999Form.addHiddenParam("sch041DayOfYearly", form.getSch041DayOfYearly());
        cmn999Form.addHiddenParam("sch041MonthOfYearly", form.getSch041MonthOfYearly());
        cmn999Form.addHiddenParam("sch041TranKbn", form.getSch041TranKbn());
        cmn999Form.addHiddenParam("sch041FrYear", form.getSch041FrYear());
        cmn999Form.addHiddenParam("sch041FrMonth", form.getSch041FrMonth());
        cmn999Form.addHiddenParam("sch041FrDay", form.getSch041FrDay());
        cmn999Form.addHiddenParam("sch041ToYear", form.getSch041ToYear());
        cmn999Form.addHiddenParam("sch041ToMonth", form.getSch041ToMonth());
        cmn999Form.addHiddenParam("sch041ToDay", form.getSch041ToDay());
        cmn999Form.addHiddenParam("sch041FrHour", form.getSch041FrHour());
        cmn999Form.addHiddenParam("sch041FrMin", form.getSch041FrMin());
        cmn999Form.addHiddenParam("sch041ToHour", form.getSch041ToHour());
        cmn999Form.addHiddenParam("sch041ToMin", form.getSch041ToMin());
        cmn999Form.addHiddenParam("sch041Bgcolor", form.getSch041Bgcolor());
        cmn999Form.addHiddenParam("sch041Title", form.getSch041Title());
        cmn999Form.addHiddenParam("sch041Value", form.getSch041Value());
        cmn999Form.addHiddenParam("sch041Biko", form.getSch041Biko());
        cmn999Form.addHiddenParam("sch041Public", form.getSch041Public());
        cmn999Form.addHiddenParam("sch041Edit", form.getSch041Edit());
        cmn999Form.addHiddenParam("sch041BatchRef", form.getSch041BatchRef());
        cmn999Form.addHiddenParam("sch041GroupSid", form.getSch041GroupSid());
        cmn999Form.addHiddenParam("sch041TimeKbn", form.getSch041TimeKbn());

        //曜日
        String[] weeks = form.getSch041Dweek();
        if (weeks != null) {
            cmn999Form.addHiddenParam("sch041Dweek", weeks);
        }
        //同時登録ユーザ
        String[] exusers = form.getSch041SvUsers();
        if (exusers != null) {
            for (String exuser : exusers) {
                cmn999Form.addHiddenParam("sch041SvUsers", exuser);
            }
        }
        //同時登録施設
        String[] exreserves = form.getSch041SvReserve();
        if (exreserves != null) {
            for (String exreserve : exreserves) {
                cmn999Form.addHiddenParam("sch041SvReserve", exreserve);
            }
        }
        cmn999Form.addHiddenParam("sch041contact", form.getSch041contact());
        cmn999Form.addHiddenParam("sch041CompanySid", form.getSch041CompanySid());
        cmn999Form.addHiddenParam("sch041CompanyBaseSid", form.getSch041CompanyBaseSid());
        cmn999Form.addHiddenParam("sch041AddressId", form.getSch041AddressId());

        //検索対象
        String[] searchTarget = form.getSch100SearchTarget();
        if (searchTarget != null) {
            for (String target : searchTarget) {
                cmn999Form.addHiddenParam("sch100SearchTarget", target);
            }
        }
        //検索対象
        String[] svSearchTarget = form.getSch100SvSearchTarget();
        if (svSearchTarget != null) {
            for (String target : svSearchTarget) {
                cmn999Form.addHiddenParam("sch100SvSearchTarget", target);
            }
        }
        //メイン画面用
        cmn999Form.addHiddenParam("schWeekDate", form.getSchWeekDate());
        cmn999Form.addHiddenParam("schDailyDate", form.getSchDailyDate());
        //一覧画面用
        cmn999Form.addHiddenParam("sch100SelectUsrSid", form.getSch100SelectUsrSid());
        cmn999Form.addHiddenParam("sch100PageNum", form.getSch100PageNum());
        cmn999Form.addHiddenParam("sch100Slt_page1", form.getSch100Slt_page1());
        cmn999Form.addHiddenParam("sch100Slt_page2", form.getSch100Slt_page2());
        cmn999Form.addHiddenParam("sch100OrderKey1", form.getSch100OrderKey1());
        cmn999Form.addHiddenParam("sch100SortKey1", form.getSch100SortKey1());
        cmn999Form.addHiddenParam("sch100OrderKey2", form.getSch100OrderKey2());
        cmn999Form.addHiddenParam("sch100SortKey2", form.getSch100SortKey2());

        cmn999Form.addHiddenParam("sch100SvSltGroup", form.getSch100SvSltGroup());
        cmn999Form.addHiddenParam("sch100SvSltUser", form.getSch100SvSltUser());
        cmn999Form.addHiddenParam("sch100SvSltStartYearFr", form.getSch100SvSltStartYearFr());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthFr", form.getSch100SvSltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltStartDayFr", form.getSch100SvSltStartDayFr());
        cmn999Form.addHiddenParam("sch100SvSltStartYearTo", form.getSch100SvSltStartYearTo());
        cmn999Form.addHiddenParam("sch100SvSltStartMonthTo", form.getSch100SvSltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltStartDayTo", form.getSch100SvSltStartDayTo());
        cmn999Form.addHiddenParam("sch100SvSltEndYearFr", form.getSch100SvSltEndYearFr());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthFr", form.getSch100SvSltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SvSltEndDayFr", form.getSch100SvSltEndDayFr());
        cmn999Form.addHiddenParam("sch100SvSltEndYearTo", form.getSch100SvSltEndYearTo());
        cmn999Form.addHiddenParam("sch100SvSltEndMonthTo", form.getSch100SvSltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SvSltEndDayTo", form.getSch100SvSltEndDayTo());
        cmn999Form.addHiddenParam("sch100SvKeyWordkbn", form.getSch100SvKeyWordkbn());
        cmn999Form.addHiddenParam("sch100SvKeyValue", form.getSch100SvKeyValue());
        cmn999Form.addHiddenParam("sch100SvOrderKey1", form.getSch100SvOrderKey1());
        cmn999Form.addHiddenParam("sch100SvSortKey1", form.getSch100SvSortKey1());
        cmn999Form.addHiddenParam("sch100SvOrderKey2", form.getSch100SvOrderKey2());
        cmn999Form.addHiddenParam("sch100SortKey2", form.getSch100SvSortKey2());
        cmn999Form.addHiddenParam("sch100SvBgcolor", form.getSch100SvBgcolor());

        cmn999Form.addHiddenParam("sch100SltGroup", form.getSch100SltGroup());
        cmn999Form.addHiddenParam("sch100SltUser", form.getSch100SltUser());
        cmn999Form.addHiddenParam("sch100SltStartYearFr", form.getSch100SltStartYearFr());
        cmn999Form.addHiddenParam("sch100SltStartMonthFr", form.getSch100SltStartMonthFr());
        cmn999Form.addHiddenParam("sch100SltStartDayFr", form.getSch100SltStartDayFr());
        cmn999Form.addHiddenParam("sch100SltStartYearTo", form.getSch100SltStartYearTo());
        cmn999Form.addHiddenParam("sch100SltStartMonthTo", form.getSch100SltStartMonthTo());
        cmn999Form.addHiddenParam("sch100SltStartDayTo", form.getSch100SltStartDayTo());
        cmn999Form.addHiddenParam("sch100SltEndYearFr", form.getSch100SltEndYearFr());
        cmn999Form.addHiddenParam("sch100SltEndMonthFr", form.getSch100SltEndMonthFr());
        cmn999Form.addHiddenParam("sch100SltEndDayFr", form.getSch100SltEndDayFr());
        cmn999Form.addHiddenParam("sch100SltEndYearTo", form.getSch100SltEndYearTo());
        cmn999Form.addHiddenParam("sch100SltEndMonthTo", form.getSch100SltEndMonthTo());
        cmn999Form.addHiddenParam("sch100SltEndDayTo", form.getSch100SltEndDayTo());
        cmn999Form.addHiddenParam("sch100KeyWordkbn", form.getSch100KeyWordkbn());
        cmn999Form.addHiddenParam("sch010searchWord", form.getSch010searchWord());
        cmn999Form.addHiddenParam("sch100Bgcolor", form.getSch100Bgcolor());
        cmn999Form.addHiddenParam("sch100CsvOutField", form.getSch100CsvOutField());

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

}