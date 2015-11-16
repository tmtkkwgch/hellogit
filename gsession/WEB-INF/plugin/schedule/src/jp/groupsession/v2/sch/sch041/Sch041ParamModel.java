package jp.groupsession.v2.sch.sch041;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.RelationBetweenScdAndRsvChkBiz;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.RsvSisYrkModel;
import jp.groupsession.v2.rsv.rsv111.Rsv111Biz;
import jp.groupsession.v2.rsv.rsv210.Rsv210Model;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.sch.sch040.Sch040Biz;
import jp.groupsession.v2.sch.sch040.Sch040ParamModel;
import jp.groupsession.v2.sch.sch041kn.Sch041knBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール繰り返し登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch041ParamModel extends Sch040ParamModel {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch041ParamModel.class);

    /** 削除対象の会社ID */
    private String sch041delCompanyId__ = null;
    /** 削除対象の会社拠点ID */
    private String sch041delCompanyBaseId__ = null;
    /** タイトル色区分 */
    private int sch041colorKbn__ = GSConstSchedule.SAD_MSG_NO_ADD;

    /** 日リスト */
    private ArrayList < LabelValueBean > sch041DayLabel__ = null;
    /** 週リスト */
    private ArrayList < LabelValueBean > sch041WeekLabel__ = null;

    /** 拡張設定 日リスト */
    private ArrayList < LabelValueBean > sch041ExDayLabel__ = null;
    /** 拡張設定 日リスト */
    private ArrayList < LabelValueBean > sch041ExDayOfYearlyLabel__ = null;

    /**
     * <p>sch041ExDayLabel を取得します。
     * @return sch041ExDayLabel
     */
    public ArrayList<LabelValueBean> getSch041ExDayLabel() {
        return sch041ExDayLabel__;
    }

    /**
     * <p>sch041ExDayLabel をセットします。
     * @param sch041ExDayLabel sch041ExDayLabel
     */
    public void setSch041ExDayLabel(ArrayList<LabelValueBean> sch041ExDayLabel) {
        sch041ExDayLabel__ = sch041ExDayLabel;
    }

    /**
     * <p>sch041WeekLabel を取得します。
     * @return sch041WeekLabel
     */
    public ArrayList<LabelValueBean> getSch041WeekLabel() {
        return sch041WeekLabel__;
    }

    /**
     * <p>sch041WeekLabel をセットします。
     * @param sch041WeekLabel sch041WeekLabel
     */
    public void setSch041WeekLabel(ArrayList<LabelValueBean> sch041WeekLabel) {
        sch041WeekLabel__ = sch041WeekLabel;
    }

    /**
     * <p>sch041DayLabel を取得します。
     * @return sch041DayLabel
     */
    public ArrayList<LabelValueBean> getSch041DayLabel() {
        return sch041DayLabel__;
    }

    /**
     * <p>sch041DayLabel をセットします。
     * @param sch041DayLabel sch041DayLabel
     */
    public void setSch041DayLabel(ArrayList<LabelValueBean> sch041DayLabel) {
        sch041DayLabel__ = sch041DayLabel;
    }

    /**
     * <p>sch041delCompanyBaseId を取得します。
     * @return sch041delCompanyBaseId
     */
    public String getSch041delCompanyBaseId() {
        return sch041delCompanyBaseId__;
    }

    /**
     * <p>sch041delCompanyBaseId をセットします。
     * @param sch041delCompanyBaseId sch041delCompanyBaseId
     */
    public void setSch041delCompanyBaseId(String sch041delCompanyBaseId) {
        sch041delCompanyBaseId__ = sch041delCompanyBaseId;
    }

    /**
     * <p>sch041delCompanyId を取得します。
     * @return sch041delCompanyId
     */
    public String getSch041delCompanyId() {
        return sch041delCompanyId__;
    }

    /**
     * <p>sch041delCompanyId をセットします。
     * @param sch041delCompanyId sch041delCompanyId
     */
    public void setSch041delCompanyId(String sch041delCompanyId) {
        sch041delCompanyId__ = sch041delCompanyId;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(RequestModel reqMdl, Connection con)
    throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage(reqMdl);

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //拡張区分
        if (getSch041ExtKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_WEEK))) {
            if (getSch041Dweek() == null) {
                //曜日
                String textYoubi = gsMsg.getMessage("cmn.dayofweek");
                msg = new ActionMessage("error.select.required.text", textYoubi);
                errors.add("error.select.required.text", msg);
            }
        } else if (getSch041ExtKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_MONTH))) {
            if (getSch041Week().equals("0")
             && getSch041Day().equals("0")
             && getSch041Dweek() == null
             ) {
                //「週」又は「日」
                String textWeekOrDay = gsMsg.getMessage("schedule.src.39");
                msg = new ActionMessage("error.select.required.text", textWeekOrDay);
                errors.add("error.select.required.text", msg);
            }
            //週・日付の同時指定はエラー
            if (!getSch041Week().equals("0") && !getSch041Day().equals("0")) {
                //「週」又は「日」のいずれか
                String textWeekOrDay = gsMsg.getMessage("schedule.src.40");
                msg = new ActionMessage("error.select.required.text", textWeekOrDay);
                errors.add("error.select.required.text", msg);
            }
        }

        //開始年月日チェックフラグ(true=入力OK、false=NG)
        boolean fromOk = false;

        int iSYear = -1;
        if (!StringUtil.isNullZeroStringSpace(getSch041FrYear())) {
            iSYear = Integer.parseInt(getSch041FrYear());
        }
        int iSMonth = Integer.parseInt(getSch041FrMonth());
        int iSDay = Integer.parseInt(getSch041FrDay());

        UDate frDate = new UDate();
        frDate.setDate(iSYear, iSMonth, iSDay);
        frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        if (frDate.getYear() != iSYear
        || frDate.getMonth() != iSMonth
        || frDate.getIntDay() != iSDay) {
            String textPeriodStart = gsMsg.getMessage("schedule.src.43");
            msg = new ActionMessage("error.input.notfound.date", textPeriodStart);
            errors.add("error.input.notfound.date", msg);
        } else {
            fromOk = true;
        }

        //終了年月日チェックフラグ(true=入力OK、false=NG)
        boolean toOk = false;

        int iEYear = -1;
        if (!StringUtil.isNullZeroStringSpace(getSch041ToYear())) {
            iEYear = Integer.parseInt(getSch041ToYear());
        }
        int iEMonth = Integer.parseInt(getSch041ToMonth());
        int iEDay = Integer.parseInt(getSch041ToDay());

        UDate toDate = new UDate();
        toDate.setDate(iEYear, iEMonth, iEDay);
        toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        toDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        if (toDate.getYear() != iEYear
        || toDate.getMonth() != iEMonth
        || toDate.getIntDay() != iEDay) {
            String textPeriodEnd = gsMsg.getMessage("schedule.src.44");
            msg = new ActionMessage("error.input.notfound.date", textPeriodEnd);
            errors.add("error.input.notfound.date", msg);
        } else {
            toOk = true;
        }
        if (fromOk && toOk) {
            //from～to大小チェック
            if (frDate.compare(frDate, toDate) == UDate.SMALL) {
                //開始 ≦ 終了
                String textStartOrEnd2 = gsMsg.getMessage("cmn.start.or.end2");
                String textPeriodSetting = gsMsg.getMessage("schedule.src.42");
                msg = new ActionMessage("error.input.comp.text",
                                                 textPeriodSetting, textStartOrEnd2);
                errors.add("" + "error.input.comp.text", msg);
            }
        }


        boolean timeFrOk = true;
        boolean timeToOk = true;
        if (getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            if (getSch041FrHour().equals("-1")
             || getSch041FrMin().equals("-1")
             || getSch041ToHour().equals("-1")
             || getSch041ToMin().equals("-1")) {
                //時分を
                String textJifun = gsMsg.getMessage("cmn.time2");
                        msg = new ActionMessage("error.input.required.text", textJifun);
                        errors.add("" + "error.input.required.text", msg);
                        timeFrOk = false;
            }
        }

        //個別チェックOKの場合
        if (timeFrOk && timeToOk
                && getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            UDate frTimeDate = new UDate();
            UDate toTimeDate = frTimeDate.cloneUDate();
            if (getSch041FrHour().equals("-1") && getSch041FrMin().equals("-1")) {
                frTimeDate.setHour(GSConstSchedule.DAY_START_HOUR);
                frTimeDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
            } else {
                frTimeDate.setHour(Integer.parseInt(getSch041FrHour()));
                frTimeDate.setMinute(Integer.parseInt(getSch041FrMin()));
            }
            if (getSch041ToHour().equals("-1") && getSch041ToMin().equals("-1")) {
                toTimeDate.setHour(GSConstSchedule.DAY_END_HOUR);
                toTimeDate.setMinute(GSConstSchedule.DAY_SYSMAX_MINUTES);
            } else {
                toTimeDate.setHour(Integer.parseInt(getSch041ToHour()));
                toTimeDate.setMinute(Integer.parseInt(getSch041ToMin()));
            }
            //from～to大小チェック
            if (frDate.compare(frTimeDate, toTimeDate) != UDate.LARGE) {
                //開始 < 終了
                String textStartLessEnd = gsMsg.getMessage("cmn.start.lessthan.end");
                //開始・終了
                String textStartEnd = gsMsg.getMessage("cmn.start.end");
                msg = new ActionMessage("error.input.comp.text", textStartEnd, textStartLessEnd);
                errors.add("" + "error.input.comp.text", msg);
            }

        }

        //有効な日付があるかチェック
        Sch041knBiz sch041knBiz = new Sch041knBiz(con, reqMdl);
        HashMap<String, UDate> dateMap = sch041knBiz.getInsertDateList(this, sessionUsrSid, con);
        if (dateMap.isEmpty()) {
            //日付
            String textDate = gsMsg.getMessage("cmn.date2");
            msg = new ActionMessage("search.data.notfound", textDate);
            errors.add("search.data.notfound", msg);
        }

        //タイトルのチェック
        if (__checkNoInput(errors, getSch041Title(),
                           "sch041Title", gsMsg.getMessage("cmn.title"))) {
            if (__checkRange(
                    errors,
                    getSch041Title(),
                    "sch041Title",
                    gsMsg.getMessage("cmn.title"),
                    GSConstSchedule.MAX_LENGTH_TITLE)) {
                //先頭スペースチェック
                if (ValidateUtil.isSpaceStart(getSch041Title())) {
                    //タイトル
                    String textTitle = gsMsg.getMessage("cmn.title");
                    msg = new ActionMessage("error.input.spase.start",
                            textTitle);
                    StrutsUtil.addMessage(errors, msg, "sch041Title");
                } else {
                    __checkJisString(
                            errors,
                            getSch041Title(),
                            "sch041Title",
                            gsMsg.getMessage("cmn.title"));
                }
            }

        }
        boolean valueError = false;
        String content = gsMsg.getMessage("cmn.content");
        //内容のチェック
        if (__checkRangeTextarea(
                errors,
                getSch041Value(),
                "sch041Value",
                content,
                GSConstSchedule.MAX_LENGTH_VALUE)) {
            if (!StringUtil.isNullZeroString(getSch041Value())) {
                //スペースのみチェック
                if (!valueError && ValidateUtil.isSpaceOrKaigyou(getSch041Value())) {
                    msg = new ActionMessage("error.input.spase.cl.only", content);
                    StrutsUtil.addMessage(errors, msg, "sch041Value");
                    valueError = true;
                }
                //先頭スペースチェック
                if (!valueError && ValidateUtil.isSpaceStart(getSch041Value())) {
                    msg = new ActionMessage("error.input.spase.start",
                                            content);
                    StrutsUtil.addMessage(errors, msg, "sch041Value");
                    valueError = true;
                }
                if (!valueError) {
                    __checkJisString(
                            errors,
                            getSch041Value(),
                            "sch041Value",
                            content);
                }
            }
        }

        //公開フラグ
        boolean bikoError = false;
        String biko = gsMsg.getMessage("cmn.memo");
        //備考のチェック
        if (__checkRangeTextarea(
                errors,
                getSch041Biko(),
                "sch041Biko",
                biko,
                GSConstSchedule.MAX_LENGTH_BIKO)) {
            if (!StringUtil.isNullZeroString(getSch041Biko())) {
                //スペースのみチェック
                if (!bikoError && ValidateUtil.isSpaceOrKaigyou(getSch041Biko())) {
                    msg = new ActionMessage("error.input.spase.cl.only", biko);
                    StrutsUtil.addMessage(errors, msg, "sch041Biko");
                    bikoError = true;
                }
                //先頭スペースチェック
                if (!bikoError && ValidateUtil.isSpaceStart(getSch041Biko())) {
                    msg = new ActionMessage("error.input.spase.start", biko);
                    StrutsUtil.addMessage(errors, msg, "sch041Biko");
                    bikoError = true;
                }
                if (!bikoError) {
//                  JIS
                    __checkJisString(
                            errors,
                            getSch041Biko(),
                            "sch041Biko",
                            biko);
                }
            }
        }
        //同時登録スケジュールの編集権限チェック
        validateExSchPowerCheck(reqMdl, errors, con);

        if (errors.isEmpty()) {
            //スケジュール重複登録チェック
            errors = validateExSchRepeatCheck(reqMdl, errors, con, sessionUsrSid);
        }

        Sch040Biz sch040Biz = new Sch040Biz(con, reqMdl);

        //施設予約の編集権限チェック
        validateExResPowerCheck(reqMdl, errors, con);


        log__.info("施設予約チェックＳＴＡＲＴ");
        //施設予約チェック
        if (!errors.isEmpty()) {
            log__.info("!errors.isEmpty()");
            return errors;
        }
        if (getSch041SvReserve() == null
                || getSch041SvReserve().length < 1
                || getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_NOT_EXIST))) {
            return errors;
        }

        String[] rsdSids = null;
        if (getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            rsdSids = getSch041SvReserve();
        }
        log__.info("rsdSids==>" + rsdSids.toString());
        if (rsdSids != null) {
            log__.info("rsdSids != null)");
            RsvSisYrkDao yrkDao = new RsvSisYrkDao(con);
            boolean errorFlg = false;

            Sch041ParamModel paramMdl = new Sch041ParamModel();
            paramMdl.setParam(this);
            HashMap<String, String> dayMap = sch041knBiz.getInsertStrDateList(
                    paramMdl, sessionUsrSid, con);
            paramMdl.setFormData(this);

            if (dayMap.isEmpty()) {
                //日付
                String textDate = gsMsg.getMessage("cmn.date2");
                log__.info("dayMap.isEmpty()");
                msg = new ActionMessage("search.data.notfound", textDate);
                errors.add("search.data.notfound", msg);
            } else {
                log__.info("dayMap.isEmpty() else ");
                ArrayList<String> dayList = new ArrayList<String>(dayMap.values());
                Collections.sort(dayList);
                Rsv111Biz biz = new Rsv111Biz(reqMdl, con);
                int i = 0;
                //施設ループ
                for (String rsdSid : rsdSids) {
                    log__.info("施設ＳＩＤ==>" + rsdSid);
                    int sisetuSid = Integer.parseInt(rsdSid);
                    Rsv210Model dataMdl = biz.getGroupCheckData(sisetuSid);
                    if (dataMdl != null) {

                        List<RsvSisYrkModel> ngList = new ArrayList<RsvSisYrkModel>();
                        List<RsvSisYrkModel> allNgList = new ArrayList<RsvSisYrkModel>();

                        //予約可能期限チェック(期限が設定されていればチェックする)
                        String kigen = dataMdl.getRsdProp6();
                        if (!StringUtil.isNullZeroString(kigen)) {

                            //施設グループ管理者の場合は予約可能期限チェックをパスする
                            RsvCommonBiz rsvBiz = new RsvCommonBiz();
                            if (!rsvBiz.isGroupAdmin(con, sisetuSid, sessionUsrSid)) {

                                UDate now = new UDate();
                                UDate udKigen = now.cloneUDate();
                                udKigen.addDay(Integer.parseInt(kigen));

                                String kigenYmd = udKigen.getDateString();

                                //ソート済の配列の最後(最後の日付)を取得
                                String toDateStr = dayList.get(dayList.size() - 1);
                                UDate toDateUd = new UDate();
                                toDateUd.setDate(toDateStr);
                                String chkYmd = toDateUd.getDateString();

                                if (Integer.parseInt(chkYmd) > Integer.parseInt(kigenYmd)) {
                                    //開始・終了
                                    String textDayAfter = gsMsg.getMessage("cmn.days.after");
                                    String kigenStr =
                                            "※"
                                                    + dataMdl.getRsdProp6()
                                                    + textDayAfter;

                                    msg = new ActionMessage("error.kigen.over2.sisetu", kigenStr);
                                    StrutsUtil.addMessage(errors, msg, "sisetu");
                                    errorFlg = true;
                                }
                            }
                        }
                        log__.info("施設重複のチェック==>" + dataMdl.getRsdProp7());
//                      重複のチェック(重複登録 = 不可の場合にチェック)
                        String tyohuku = dataMdl.getRsdProp7();
                        if (!errorFlg
                                && !StringUtil.isNullZeroString(tyohuku)
                                && Integer.parseInt(tyohuku) == GSConstReserve.PROP_KBN_HUKA) {
                            UDate resfrDate = null;
                            UDate restoDate = null;

                            if (rsdSids != null) {
                                //登録時刻を取得
                                for (String date : dayList) {
                                    log__.info("施設重複のチェック日付==>" + date);
                                    resfrDate = new UDate();
                                    resfrDate.setDate(date);
                                    resfrDate.setZeroHhMmSs();
                                    resfrDate.setHour(Integer.parseInt(getSch041FrHour()));
                                    resfrDate.setMinute(Integer.parseInt(getSch041FrMin()));
                                    restoDate = resfrDate.cloneUDate();
                                    restoDate.setZeroHhMmSs();
                                    restoDate.setHour(Integer.parseInt(getSch041ToHour()));
                                    restoDate.setMinute(Integer.parseInt(getSch041ToMin()));

                                    log__.info("処理モードCmd==>" + getCmd());

                                    //新規モード
                                    if (getCmd().equals(GSConstSchedule.CMD_ADD)) {
                                        for (String sid : rsdSids) {
                                            ngList = yrkDao.getYrkNgList(
                                                    -1,
                                                    Integer.parseInt(sid),
                                                    resfrDate,
                                                    restoDate);
                                            allNgList.addAll(ngList);
                                        }
                                    //編集モード
                                    } else if (getCmd().equals(GSConstSchedule.CMD_EDIT)) {
                                        SchCommonBiz cBiz = new SchCommonBiz(reqMdl);
                                        SchAdmConfModel adminConf = cBiz.getAdmConfModel(con);
                                        ScheduleSearchModel scMdl = sch040Biz.getSchData(
                                                Integer.parseInt(getSch010SchSid()),
                                                adminConf,
                                                con);

                                        ArrayList<RsvSisYrkModel> yrkList = null;
                                        if (scMdl.getScdRsSid() != -1) {
                                            yrkList = yrkDao.getScheduleRserveData(
                                                    scMdl.getScdRsSid()
                                                    );
                                        }
                                        RsvSisYrkModel yrkMdl = null;

                                        int rsrSid = -1;
                                        if (yrkList != null) {
                                            rsrSid = yrkList.get(0).getRsrRsid();
                                        }

                                        for (String sid : rsdSids) {
                                            yrkMdl = getReserveData(yrkList, Integer.parseInt(sid));

                                            if (yrkMdl == null) {
                                                ngList = yrkDao.getSchYrkNgList(rsrSid, -1,
                                                        Integer.parseInt(rsdSid),
                                                        resfrDate,
                                                        restoDate);

                                            } else {

                                                ngList = yrkDao.getSchKakutyoYrkNgList(
                                                        yrkMdl.getRsrRsid(),
                                                        yrkMdl.getRsdSid(),
                                                        resfrDate,
                                                        restoDate);

//                                                if (!yrkDao.isYrkOk(
//                                                        yrkMdl.getRsySid(),
//                                                        yrkMdl.getRsdSid(),
//                                                        frDate,
//                                                        toDate)) {
//                                                    yrkOkFlg = false;
//                                                }
                                            }
                                            allNgList.addAll(ngList);

                                        }
                                    }

                                }
                            }
                        }
                        //重複チェック
                        if (allNgList != null && allNgList.size() > 0) {

                            String textSchedule = gsMsg.getMessage("cmn.reserve");
                            msg = new ActionMessage("error.input.dup", textSchedule);
                            StrutsUtil.addMessage(errors, msg, "rsv110YrkEr");


                            for (RsvSisYrkModel yrkModel : allNgList) {

                                String schTime = UDateUtil.getYymdJ(yrkModel.getRsyFrDate(),
                                                                    reqMdl);
                                schTime += UDateUtil.getSeparateHMJ(yrkModel.getRsyFrDate(),
                                                                    reqMdl);
                                schTime += "～";
                                schTime += UDateUtil.getYymdJ(yrkModel.getRsyToDate(), reqMdl);
                                schTime += UDateUtil.getSeparateHMJ(yrkModel.getRsyToDate(),
                                                                    reqMdl);


                                msg = new ActionMessage("error.input.dup.sch",
                                        schTime,
                                        StringUtilHtml.transToHTmlPlusAmparsant(
                                                yrkModel.getRsdName()),
                                        StringUtilHtml.transToHTmlPlusAmparsant(
                                                yrkModel.getRsyMok()));

                                StrutsUtil.addMessage(errors, msg,
                                        "rsv110YrkErr"
                                        + String.valueOf(yrkModel.getRsySid()) + String.valueOf(i));
                                i++;
                            }
                        }
                    }
                }



            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 編集権限チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateExPowerCheck(
            RequestModel reqMdl,
            Connection con) throws SQLException {
        ActionErrors errors = new ActionErrors();
        //同時登録スケジュールの編集権限チェックを行う
        errors = validateExSchPowerCheck(reqMdl, errors, con);
        //同時登録施設予約の編集権限チェックを行う
        errors = validateExResPowerCheck(reqMdl, errors, con);
        return errors;
    }
    /**
     * <br>[機  能] 同時登録スケジュールの編集権限チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param errors アクションエラー
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateExSchPowerCheck(
            RequestModel reqMdl,
            ActionErrors errors,
            Connection con) throws SQLException {

        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage(reqMdl);

        int schSid = NullDefault.getInt(getSch010SchSid(), -1);
        log__.debug("スケジュールSID==>" + schSid);
        //同時登録スケジュールの編集権限チェック
        if (getCmd().equals(GSConstSchedule.CMD_EDIT)) {
            //拡張登録されたスケジュール全てをチェック
            Sch041Biz biz = new Sch041Biz(reqMdl);
            if (!biz.isAllEditOkEx(schSid, reqMdl, con)) {
                //同時登録スケジュールに対する編集
                String textSimultaneousEdit = gsMsg.getMessage("schedule.src.33");
                //変更
                String textChange = gsMsg.getMessage("cmn.change");
                msg = new ActionMessage("error.edit.power.user", textSimultaneousEdit, textChange);
                StrutsUtil.addMessage(errors, msg, "adduser");
            }
        }
        return errors;
    }
    /**
     * <br>[機  能] 同時登録施設予約の編集権限チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param errors アクションエラー
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateExResPowerCheck(
            RequestModel reqMdl,
            ActionErrors errors,
            Connection con) throws SQLException {

        ActionMessage msg = null;
        int schSid = NullDefault.getInt(getSch010SchSid(), -1);
        GsMessage gsMsg = new GsMessage(reqMdl);

        //アクセス権限チェック
        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        CommonBiz cmnBiz = new CommonBiz();
        Sch040Biz biz = new Sch040Biz(con, reqMdl);


        //編集権限のない施設数を取得する。
        boolean rsvAdmin = cmnBiz.isPluginAdmin(con, usModel, GSConstSchedule.PLUGIN_ID_RESERVE);
        int count = biz.getCanNotEditRsvCount(this, usModel.getUsrsid(), con, rsvAdmin);

        if (count > 0) {
            //施設予約アクセス権限なし
            msg = new ActionMessage("error.myself.auth");
            StrutsUtil.addMessage(errors, msg, "error.myself.auth");
            return errors;
        }

        //変更
        String textChange = gsMsg.getMessage("cmn.change");
        //施設予約エラーチェック
        //同時登録施設予約の編集権限チェック
        if (getCmd().equals(GSConstSchedule.CMD_EDIT)) {

            RelationBetweenScdAndRsvChkBiz rsvChkBiz
                = new RelationBetweenScdAndRsvChkBiz(reqMdl, con);
            int errorCd = rsvChkBiz.isRsvEdit(
                    schSid,
                    RelationBetweenScdAndRsvChkBiz.CHK_KBN_KURIKAESHI);
            if (errorCd == RelationBetweenScdAndRsvChkBiz.ERR_CD_SCD_CANNOT_EDIT) {
                //施設予約に対する編集
                String textRsvEdit = gsMsg.getMessage("schedule.src.32");
                msg = new ActionMessage("error.edit.power.user", textRsvEdit, textChange);
                StrutsUtil.addMessage(errors, msg, "addres");
            }

        }
        return errors;
    }
    /**
     * <br>[機  能] 指定された項目の未入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkNoInput(ActionErrors errors,
                                    String value,
                                    String element,
                                    String elementName) {
        boolean result = true;
        ActionMessage msg = null;

        if (StringUtil.isNullZeroString(value)) {
            msg = new ActionMessage("error.input.required.text", elementName);
            errors.add(element + "error.input.required.text", msg);
            result = false;
        } else {
            //スペースのみの入力かチェック
            if (ValidateUtil.isSpace(value)) {
                msg = new ActionMessage("error.input.spase.only", elementName);
                errors.add(element + "error.input.spase.only", msg);
                result = false;
            }

        }

        return result;
    }

    /**
     * <br>[機  能] 指定された項目の桁数チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @param range 桁数
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkRange(ActionErrors errors,
                                String value,
                                String element,
                                String elementName,
                                int range) {
        boolean result = true;
        ActionMessage msg = null;

        //MAX値を超えていないか
        if (value.length() > range) {
            msg = new ActionMessage("error.input.length.text", elementName,
                    String.valueOf(range));
            errors.add(element + "error.input.length.text", msg);
            result = false;
        }
        return result;
    }

    /**
     * <br>[機  能] 指定された項目の桁数チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @param range 桁数
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkRangeTextarea(ActionErrors errors,
                                String value,
                                String element,
                                String elementName,
                                int range) {
        boolean result = true;
        ActionMessage msg = null;

        //MAX値を超えていないか
        if (value.length() > range) {
            msg = new ActionMessage("error.input.length.textarea", elementName,
                    String.valueOf(range));
            errors.add(element + "error.input.length.textarea", msg);
            result = false;
            log__.debug("error:7");
        }
        return result;
    }

    /**
     * <br>[機  能] 指定された項目がJIS第2水準文字かチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkJisString(ActionErrors errors,
                                String value,
                                String element,
                                String elementName) {
        boolean result = true;
        ActionMessage msg = null;
        //JIS第2水準文字か
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
            msg = new ActionMessage("error.input.njapan.text", elementName, nstr);
            errors.add(element + "error.input.njapan.text", msg);
            result = false;
        }
        return result;
    }

    /**
     * <br>[機  能] 同時登録スケジュールの重複登録チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param errors アクションエラー
     * @param con コネクション
     * @param sessionUsrSid ユーザSID
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateExSchRepeatCheck(
            RequestModel reqMdl,
            ActionErrors errors,
            Connection con,
            int sessionUsrSid
            ) throws SQLException {

        ActionMessage msg = null;

        Sch041Biz biz = new Sch041Biz(reqMdl);
        SchCommonBiz schBiz = new SchCommonBiz(con, reqMdl);

        //重複登録 NGスケジュール一覧を取得する。
        List<SchDataModel> rptSchList
                 = biz.getExSchWarningList(
                         reqMdl, this, sessionUsrSid, con, GSConstSchedule.SCH_REPEAT_KBN_NG);

        if (rptSchList != null && rptSchList.size() > 0) {
            int i = 1;

            GsMessage gsMsg = new GsMessage(reqMdl);
            String textSchedule = gsMsg.getMessage("schedule.108");
            String title = "";

            msg = new ActionMessage("error.input.dup", textSchedule);
            StrutsUtil.addMessage(errors, msg, "error.input.dup");

            for (SchDataModel ngMdl : rptSchList) {

                //公開区分で判定してタイトルを取得
                title = schBiz.getDspTitle(ngMdl, sessionUsrSid);
                String schTime = UDateUtil.getYymdJ(ngMdl.getScdFrDate(), reqMdl);
                schTime += UDateUtil.getSeparateHMJ(ngMdl.getScdFrDate(), reqMdl);
                schTime += "～";
                schTime += UDateUtil.getYymdJ(ngMdl.getScdToDate(), reqMdl);
                schTime += UDateUtil.getSeparateHMJ(ngMdl.getScdToDate(), reqMdl);

                msg = new ActionMessage("error.input.dup.sch",
                        schTime,
                        StringUtilHtml.transToHTmlPlusAmparsant(title),
                        StringUtilHtml.transToHTmlPlusAmparsant(ngMdl.getScdUserName()));
                StrutsUtil.addMessage(errors, msg, "error.input.dup.sch" + i);
                i++;
            }
        }

        return errors;
    }

    /**
     * <p>sch041colorKbn を取得します。
     * @return sch041colorKbn
     */
    public int getSch041colorKbn() {
        return sch041colorKbn__;
    }

    /**
     * <p>sch041colorKbn をセットします。
     * @param sch041colorKbn sch041colorKbn
     */
    public void setSch041colorKbn(int sch041colorKbn) {
        sch041colorKbn__ = sch041colorKbn;
    }

    /**
     * <p>sch041ExDayOfYearlyLabel を取得します。
     * @return sch041ExDayOfYearlyLabel
     */
    public ArrayList < LabelValueBean > getSch041ExDayOfYearlyLabel() {
        return sch041ExDayOfYearlyLabel__;
    }

    /**
     * <p>sch041ExDayOfYearlyLabel をセットします。
     * @param sch041ExDayOfYearlyLabel sch041ExDayOfYearlyLabel
     */
    public void setSch041ExDayOfYearlyLabel(ArrayList < LabelValueBean > sch041ExDayOfYearlyLabel) {
        sch041ExDayOfYearlyLabel__ = sch041ExDayOfYearlyLabel;
    }
}
