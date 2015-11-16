package jp.groupsession.v2.sch.sch020;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnHolidayDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupMsDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.SchAppendDataParam;
import jp.groupsession.v2.cmn.model.UserSearchModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnHolidayModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchPriConfDao;
import jp.groupsession.v2.sch.dao.ScheduleSearchDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.sch.pdf.SchGekPdfModel;
import jp.groupsession.v2.sch.pdf.SchGekPdfUtil;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.sch.sch010.Sch010PeriodModel;
import jp.groupsession.v2.sch.sch010.Sch010UsrModel;
import jp.groupsession.v2.sch.sch010.SimpleScheduleModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール 月間画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch020Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch020Biz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /** DBコネクション */
    private Connection con__ = null;
    /** pconfig */
    private PluginConfig pconfig__ = null;
    /** セッションユーザ所属グループSIDリスト */
    private List<Integer> belongGpSidList__ = null;
    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param pconfig PluginConfig
     * @param reqMdl RequestModel
     */
    public Sch020Biz(Connection con, PluginConfig pconfig,
                     RequestModel reqMdl) {
        reqMdl__ = reqMdl;
        con__ = con;
        pconfig__ = pconfig;
    }

    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Sch020Biz(Connection con, RequestModel reqMdl) {
        reqMdl__ = reqMdl;
        con__ = con;
    }

    /**
     * 初期表示画面情報を取得します
     * @param paramMdl アクションフォーム
     * @return アクションフォーム
     * @throws SQLException SQL実行時例外
     */
    public Sch020ParamModel getInitData(Sch020ParamModel paramMdl)
    throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //管理者設定を取得
        SchCommonBiz cmnbiz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = cmnbiz.getAdmConfModel(con__);

        //共有範囲
        paramMdl.setSch010CrangeKbn(adminConf.getSadCrange());

        //セッションユーザの所属グループを格納
        CmnBelongmDao bdao = new CmnBelongmDao(con__);
        belongGpSidList__ = bdao.selectUserBelongGroupSid(sessionUsrSid);

        //表示ユーザを取得
        paramMdl.setSch010SelectUsrSid(NullDefault.getString(paramMdl.getSch010SelectUsrSid(),
                String.valueOf(usModel.getUsrsid())));
        paramMdl.setDspMod(GSConstSchedule.DSP_MOD_MONTH);
        int userSid = NullDefault.getInt(
                paramMdl.getSch020SelectUsrSid(),
                Integer.parseInt(paramMdl.getSch010SelectUsrSid()));
        paramMdl.setSch010SelectUsrKbn(NullDefault.getString(paramMdl.getSch010SelectUsrKbn(),
                String.valueOf(GSConstSchedule.USER_KBN_USER)));
        int userKbn = Integer.parseInt(paramMdl.getSch010SelectUsrKbn());


        //グループコンボ生成
        Sch010Biz biz = new Sch010Biz(reqMdl__);
        SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
        //グループ設定
        paramMdl.setSch010GpLabelList(biz.getGroupLabelList(con__, sessionUsrSid));

        //デフォルト表示グループ
        String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con__, sessionUsrSid);
        int dfGpSid = SchCommonBiz.getDspGroupSid(dfGpSidStr);
        int dspGpSid = 0;
        boolean myGroupFlg = false;
        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getSch010DspGpSid(), dfGpSidStr);
        dspGpSidStr = scBiz.getEnableSelectGroup(
                paramMdl.getSch010GpLabelList(), dspGpSidStr, dfGpSidStr);
        if (SchCommonBiz.isMyGroupSid(dspGpSidStr)) {
            dspGpSid = SchCommonBiz.getDspGroupSid(dspGpSidStr);
            myGroupFlg = true;
            paramMdl.setSch010DspGpSid(dspGpSidStr);
        } else {
            dspGpSid = NullDefault.getInt(paramMdl.getSch010DspGpSid(), dfGpSid);
            paramMdl.setSch010DspGpSid(String.valueOf(dspGpSid));
        }
        log__.debug("paramMdl.getSch010DspGpSid()-(1)==>" + paramMdl.getSch010DspGpSid());

        //カレンダー表示開始の曜日を取得
        SchPriConfDao spcDao = new SchPriConfDao(con__);
        int dspStartWeek = spcDao.select(sessionUsrSid).getSccIniWeek();

        if (dspStartWeek == 0) {
            dspStartWeek = 1;
        }

        paramMdl.setSch020DspStartWeek(dspStartWeek);

        //グループメンバー表示フラグ
        boolean gpmemFlg = false;

        if (userSid < 0 && !SchCommonBiz.isMyGroupSid(paramMdl.getSch010DspGpSid())) {
            //グループスケジュール表示
            userKbn = GSConstSchedule.USER_KBN_GROUP;
            userSid = NullDefault.getInt(paramMdl.getSch010DspGpSid(), 0);
            //グループ＋メンバースケジュールを表示するか
            if (NullDefault.getInt(
                    paramMdl.getSch020SelectUsrSid(), GSConstSchedule.SCHEDULE_GROUP)
                    == GSConstSchedule.SCHEDULE_GROUP_MEMBER) {
                gpmemFlg = true;
            }
        } else {
            if (paramMdl.getSch020SelectUsrSid() != null) {
                userKbn = GSConstSchedule.USER_KBN_USER;
            }
        }
        log__.debug("SCH020Biz.userSid==>" + userSid);
        log__.debug("SCH020Biz.userKbn==>" + userKbn);

        //リクエストパラメータを取得
        //基準日
        String strDspDate = NullDefault.getString(
                paramMdl.getSch010DspDate(), new UDate().getDateString());

        if (StringUtil.isNullZeroStringSpace(paramMdl.getSch010DspDate())) {
            strDspDate = new UDate().getDateString();
            paramMdl.setSch010DspDate(strDspDate);
        }
        log__.debug("表示年月日==>" + strDspDate);
        UDate dspDate = new UDate();
        dspDate.setDate(strDspDate);
        //カレンダー開始日付
        UDate frDate = __getStartCalenderDate(dspDate, dspStartWeek);
        log__.debug("カレンダー開始日付=" + frDate.getDateString());
        //カレンダー終了日付
        UDate toDate = __getEndCalenderDate(dspDate, dspStartWeek);
        log__.debug("カレンダー終了日付=" + toDate.getDateString());

        //ユーザコンボ生成
        List<LabelValueBean> userLabel = __getUserLabelList(
                sessionUsrSid, dspGpSid, myGroupFlg, reqMdl__);
        paramMdl.setSch020UsrLabelList(userLabel);
        //マイグループの場合、所属しているか判定
        if (myGroupFlg) {
            CmnMyGroupMsDao mgmsDao = new CmnMyGroupMsDao(con__);
            String[] users = new String[]{String.valueOf(userSid)};
            //未所属の場合所属ユーザの先頭を設定
            if (mgmsDao.getMyGroupMsInfo(sessionUsrSid, dspGpSid, users, true).size() < 1) {
                userSid = Integer.parseInt((userLabel.get(0)).getValue());
            }
            paramMdl.setSch020SelectUsrSid(String.valueOf(userSid));
            paramMdl.setSch020MyGroupFlg(myGroupFlg);
        } else if (userKbn == GSConstSchedule.USER_KBN_USER) {
            boolean userExist = false;
            if (userLabel != null && !userLabel.isEmpty()) {
                for (LabelValueBean lvb : userLabel) {
                    String lvbVal = lvb.getValue();
                    if (Integer.parseInt(lvbVal) == userSid) {
                        userExist = true;
                        break;
                    }
                }
            }
            if (!userExist) {
                if (!SchCommonBiz.isMyGroupSid(paramMdl.getSch010DspGpSid())) {
                    userKbn = GSConstSchedule.USER_KBN_GROUP;
                    userSid = NullDefault.getInt(paramMdl.getSch010DspGpSid(), 0);
                }
            }
            paramMdl.setSch020SelectUsrSid(String.valueOf(userSid));
        } else {
            if (gpmemFlg) {
                paramMdl.setSch020SelectUsrSid(String.valueOf(
                        GSConstSchedule.SCHEDULE_GROUP_MEMBER));
            } else {
                paramMdl.setSch020SelectUsrSid(String.valueOf(userSid));
            }
        }

        if (NullDefault.getInt(paramMdl.getSch020SelectUsrSid(), -1) > 0
        && userKbn == GSConstSchedule.USER_KBN_USER) {
            //選択したユーザをセット
            paramMdl.setSch010SelectUsrSid(paramMdl.getSch020SelectUsrSid());
        } else {
            //「指定無し」の値をセット
            paramMdl.setSch010SelectUsrSid(String.valueOf(GSConstSchedule.SCHEDULE_GROUP));
        }

        //表示項目取得
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //年
        String textYear = gsMsg.getMessage("cmn.year", new String[] {dspDate.getStrYear()});
        //月
        String textMonth = gsMsg.getMessage("cmn.month");
        paramMdl.setSch020StrDspDate(textYear + dspDate.getStrMonth() + textMonth);
        paramMdl.setSch020ScheduleList(
                __getMonthScheduleList(
                        paramMdl,
                        frDate.cloneUDate(),
                        toDate.cloneUDate(),
                        userSid,
                        userKbn,
                        gpmemFlg,
                        reqMdl__)
                        );

        CommonBiz commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(
                con__, usModel, GSConstSchedule.PLUGIN_ID_SCHEDULE);

        if (adminUser) {
            paramMdl.setAdminKbn(GSConst.USER_ADMIN);
        } else {
            paramMdl.setAdminKbn(GSConst.USER_NOT_ADMIN);
        }

        //自動リロード時間を設定する。
        paramMdl.setSch020Reload(__getReloadtime(sessionUsrSid));

        //閲覧不可のグループを設定
        SchDao schDao = new SchDao(con__);
        paramMdl.setSchNotAccessGroupList(schDao.getNotAccessGrpList(sessionUsrSid));

        //スケジュール登録可能フラグを設定
        if (userKbn == GSConstSchedule.USER_KBN_GROUP
        && !myGroupFlg) {
            paramMdl.setSch020RegistFlg(
                    schDao.canRegistGroupSchedule(
                                Integer.parseInt(paramMdl.getSch010DspGpSid()),
                                sessionUsrSid));
        } else {
            paramMdl.setSch020RegistFlg(
                    schDao.canRegistUserSchedule(
                                Integer.parseInt(paramMdl.getSch020SelectUsrSid()),
                                sessionUsrSid));
        }

        return paramMdl;
    }

    /**
     * <br>月間カレンダー表示時の開始日付を取得します
     * @param date 表示年月
     * @param dspStartWeek 表示開始曜日
     * @return UDate 開始日付
     */
    private UDate __getStartCalenderDate(UDate date, int dspStartWeek) {

        UDate frDate = date.cloneUDate();
        frDate.setDay(1);
        //曜日を取得
        int week = -1;
        boolean flg = true;
        while (flg) {
            week = frDate.getWeek();
            if (week == dspStartWeek) {
                flg = false;
            } else {
                frDate.addDay(-1);
            }
        }
        return frDate;
    }

    /**
     * <br>月間カレンダー表示時の終了日付を取得します
     * @param date 表示年月
     * @param dspStartWeek 表示開始曜日
     * @return UDate 終了日付
     */
    private UDate __getEndCalenderDate(UDate date, int dspStartWeek) {
        UDate toDate = date.cloneUDate();
        int maxDay = toDate.getMaxDayOfMonth();
        toDate.setDay(maxDay);
        int week = -1;
        boolean flg = true;

        if (dspStartWeek == GSConstSchedule.CHANGE_WEEK_SUN) {
            dspStartWeek = UDate.SATURDAY;
        } else if (dspStartWeek == GSConstSchedule.CHANGE_WEEK_MON) {
            dspStartWeek = UDate.SUNDAY;
        } else if (dspStartWeek == GSConstSchedule.CHANGE_WEEK_TUE) {
            dspStartWeek = UDate.MONDAY;
        } else if (dspStartWeek == GSConstSchedule.CHANGE_WEEK_WED) {
            dspStartWeek = UDate.TUESDAY;
        } else if (dspStartWeek == GSConstSchedule.CHANGE_WEEK_THU) {
            dspStartWeek = UDate.WEDNESDAY;
        } else if (dspStartWeek == GSConstSchedule.CHANGE_WEEK_FRI) {
            dspStartWeek = UDate.THURSDAY;
        } else if (dspStartWeek == GSConstSchedule.CHANGE_WEEK_SAT) {
            dspStartWeek = UDate.FRIDAY;
        }
        while (flg) {
            week = toDate.getWeek();
            if (week == dspStartWeek) {
                flg = false;
            } else {
                toDate.addDay(1);
            }
        }
        return toDate;
    }

    /**
     * <br>指定ユーザの月間スケジュールを取得します
     * @param paramMdl アクションフォーム
     * @param frDate 開始日付
     * @param toDate 終了日付
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ区分 0=一般ユーザ 1=グループ
     * @param gpmemFlg グループメンバースケジュール表示有無
     * @param reqMdl リクエスト情報
     * @return ArrayList グループ>指定ユーザの順に格納
     * @throws SQLException SQL実行時例外
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<Sch020MonthOfModel> __getMonthScheduleList(
            Sch020ParamModel paramMdl,
            UDate frDate,
            UDate toDate,
            int usrSid,
            int usrKbn,
            boolean gpmemFlg,
            RequestModel reqMdl) throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        frDate.setHour(GSConstSchedule.DAY_START_HOUR);
        frDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
        frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        toDate.setHour(GSConstSchedule.DAY_END_HOUR);
        toDate.setMinute(GSConstSchedule.DAY_END_MINUTES);
        toDate.setSecond(GSConstSchedule.DAY_END_SECOND);
        //休日情報を取得する
        CmnHolidayDao holDao = new CmnHolidayDao(con__);
        HashMap < String, CmnHolidayModel > holMap = holDao.getHoliDayList(frDate, toDate);
        CmnHolidayModel holMdl = null;

        //グループ・指定ユーザのcolListを保持
        ArrayList<Sch020MonthOfModel> rowList = new ArrayList<Sch020MonthOfModel>();
        //ユーザ情報を保持
        Sch010UsrModel usMdl = null;
        ArrayList < Sch020DayOfModel > colList = null;
        //DBスケジュール情報
        ArrayList < SchDataModel > schDataList = null;
        //ユーザ別、１ヶ月間分のスケジュール
        Sch020MonthOfModel monthMdl = null;

        //指定ユーザスケジュール
        monthMdl = new Sch020MonthOfModel();
        colList = new ArrayList<Sch020DayOfModel>();
        usMdl = new Sch010UsrModel();
        UserSearchDao usrDao = new UserSearchDao(con__);

        //期間スケジュール取得用日付
        UDate prFrDate = frDate.cloneUDate();

        log__.debug("usrSid=>" + usrSid);
        log__.debug("usrKbn=>" + usrKbn);

        //デフォルト表示グループ
        int sessionUserSid = reqMdl.getSmodel().getUsrsid();
        SchCommonBiz scBiz = new SchCommonBiz(reqMdl);
        String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con__, sessionUserSid);
        int dfGpSid = SchCommonBiz.getDspGroupSid(dfGpSidStr);

        int dspGpSid = 0;
        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getSch010DspGpSid(), dfGpSidStr);
        boolean myGroupFlg = SchCommonBiz.isMyGroupSid(dspGpSidStr);
        if (myGroupFlg) {
            dspGpSid = SchCommonBiz.getDspGroupSid(dspGpSidStr);
        } else {
            dspGpSid = NullDefault.getInt(paramMdl.getSch010DspGpSid(), dfGpSid);
        }

        //ユーザスケジュール
        if (usrKbn == GSConstSchedule.USER_KBN_USER) {
            CmnUsrmInfModel usrInfMdl = usrDao.getUserInfoJtkb(
                    usrSid, GSConstUser.USER_JTKBN_ACTIVE);
            if (usrInfMdl != null) {
                paramMdl.setSch020StrUserName(usrInfMdl.getUsiSei() + " " + usrInfMdl.getUsiMei());
                usMdl.setUsrName(usrInfMdl.getUsiSei() + " " + usrInfMdl.getUsiMei());
            }

            usMdl.setUsrSid(usrSid);
            usMdl.setUsrKbn(GSConstSchedule.USER_KBN_USER);
            monthMdl.setSch020UsrMdl(usMdl);
        //グループスケジュール
        } else if (usrKbn == GSConstSchedule.USER_KBN_GROUP) {

            String grpName = null;
            if (usrSid == GSConstSchedule.SCHEDULE_GROUP) {
                //グループ
                String textGroup = gsMsg.getMessage("cmn.group");
                grpName = textGroup;
            } else {
                GroupDao grpDao = new GroupDao(con__);
                CmnGroupmModel gpMdl = grpDao.getGroup(usrSid);
                if (gpMdl != null) {
                    grpName = gpMdl.getGrpName();

                }
            }
            paramMdl.setSch020StrUserName(grpName);
            usMdl.setUsrName(grpName);
            usMdl.setUsrSid(usrSid);
            usMdl.setUsrKbn(GSConstSchedule.USER_KBN_GROUP);
            monthMdl.setSch020UsrMdl(usMdl);
        }

        //閲覧可能グループかを判定
        SchDao scheduleDao = new SchDao(con__);
        boolean accessGrp = true;
        if (!myGroupFlg) {
            accessGrp = scheduleDao.canAccessGroupSchedule(dspGpSid, sessionUserSid);
            paramMdl.setSchAccessGroup(accessGrp);
        }

        //スケジュール情報を取得(指定ユーザ)
        ScheduleSearchDao schDao = new ScheduleSearchDao(con__);
        if (gpmemFlg) {
            //グループスケジュールを取得
            if (accessGrp) {
                schDataList = schDao.select(
                        usrSid,
                        usrKbn,
                        -1,
                        frDate,
                        toDate,
                        GSConstSchedule.DSP_MOD_MONTH,
                        sessionUserSid);
            } else {
                schDataList = new ArrayList<SchDataModel>();
            }
            //除外するユーザSIDを設定
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            usrSids.add(new Integer(GSConstUser.SID_ADMIN));
            usrSids.add(new Integer(GSConstUser.SID_SYSTEM_MAIL));

            //所属ユーザを取得
            UserSearchDao usDao = new UserSearchDao(con__);
            ArrayList<UserSearchModel> belongList = usDao.getBelongUserInfoJtkb(usrSid,
                    usrSids, GSConstUser.USER_SORT_YKSK, GSConst.ORDER_KEY_ASC, -1, -1);
            //所属ユーザからスケジュール閲覧不可ユーザを除外する
            if (!belongList.isEmpty()) {
                List<Integer> notAccessUserList = scheduleDao.getNotAccessUserList(sessionUserSid);
                if (!notAccessUserList.isEmpty()) {
                    List<UserSearchModel> accessBelongList = new ArrayList<UserSearchModel>();
                    for (UserSearchModel belongUser : belongList) {
                        if (notAccessUserList.indexOf(belongUser.getUsrSid()) < 0) {
                            accessBelongList.add(belongUser);
                        }
                    }
                    belongList.clear();
                    belongList.addAll(accessBelongList);
                }
            }
            //メンバースケジュールを取得
            ArrayList <SchDataModel> memSchDataList = schDao.selectUsers(
                    belongList,
                    GSConstSchedule.USER_KBN_USER,
                    -1,
                    frDate,
                    toDate,
                    GSConstSchedule.DSP_MOD_MONTH);

            //他プラグイン情報を取得
            for (UserSearchModel usm : belongList) {
                __getAppendPlgData(paramMdl, frDate, toDate,
                     usm.getUsrSid(), memSchDataList, con__);
            }

            //グループとメンバースケジュールを合体
            schDataList.addAll(memSchDataList);
        } else {
            //グループ又はユーザのスケジュールを取得
            SchPriConfModel pconf = scBiz.getSchPriConfModel(con__, sessionUserSid);
            if (usrKbn == GSConstSchedule.USER_KBN_USER && usrSid == sessionUserSid
                       && pconf.getSccGrpShowKbn() == 0) {
                //個人設定でグループスケジュールを表示にしている場合は所属グループのスケジュールを取得
                CmnBelongmDao belongDao = new CmnBelongmDao(con__);
                ArrayList<Integer> belongList = belongDao.selectUserBelongGroupSid(usrSid);
                schDataList = schDao.getBelongGroupSchData2(
                        belongList,
                        -1,
                        frDate,
                        toDate,
                        GSConstSchedule.DSP_MOD_MONTH,
                        sessionUserSid);

                //スケジュール情報を取得(指定ユーザ)
                ArrayList <SchDataModel> memSchDataList = schDao.select(
                        usrSid,
                        GSConstSchedule.USER_KBN_USER,
                        -1,
                        frDate,
                        toDate,
                        GSConstSchedule.DSP_MOD_MONTH,
                        sessionUserSid);

                //他プラグイン情報を取得
                __getAppendPlgData(paramMdl, frDate, toDate,
                        usrSid, memSchDataList, con__);

                //グループとメンバースケジュールを合体
                schDataList.addAll(memSchDataList);
            } else {
                if (usrKbn == GSConstSchedule.USER_KBN_GROUP
                && !accessGrp) {
                    //表示グループがスケジュール閲覧不可グループの場合、グループスケジュールを取得しない
                    schDataList = new ArrayList<SchDataModel>();
                } else {
                    schDataList = schDao.select(
                            usrSid,
                            usrKbn,
                            -1,
                            frDate,
                            toDate,
                            GSConstSchedule.DSP_MOD_MONTH,
                            sessionUserSid);
                }

                //他プラグイン情報を取得
                __getAppendPlgData(paramMdl, frDate, toDate,
                        usrSid, schDataList, con__);
            }
        }


        UDate dspDate = new UDate();
        dspDate.setDate(
                NullDefault.getString(paramMdl.getSch010DspDate(), new UDate().getDateString()));

        Sch020DayOfModel dayMdl = null;
        ArrayList<SimpleScheduleModel> dayMdlList = null;
        SimpleScheduleModel dspSchMdl = null;
        //システム日付
        UDate today = new UDate();
        while (frDate.compareDateYMD(toDate) != UDate.SMALL) {
            //１日分のスケジュール
            dayMdlList = new ArrayList<SimpleScheduleModel>();
            dayMdl = new Sch020DayOfModel();
            // 休日名称
            holMdl = holMap.get(frDate.getDateString());
            if (holMdl != null) {
              dayMdl.setHolidayName(holMdl.getHolName());
              dayMdl.setHolidayKbn(GSConstSchedule.HOLIDAY_TRUE);
            } else {
              dayMdl.setHolidayName(null);
              dayMdl.setHolidayKbn(GSConstSchedule.HOLIDAY_FALSE);
            }
            //同月判定
            if (__isThisMonth(dspDate, frDate)) {
                dayMdl.setThisMonthKbn(1);
            } else {
                dayMdl.setThisMonthKbn(0);
            }
            dayMdl.setDspDay(String.valueOf(frDate.getIntDay()));
            dayMdl.setSchDate(frDate.getDateString());
            dayMdl.setUsrSid(usrSid);
            dayMdl.setUsrKbn(usrKbn);
            dayMdl.setWeekKbn(frDate.getWeek());
            if (today.getDateString().equals(frDate.getDateString())) {
                dayMdl.setTodayKbn(GSConstSchedule.TODAY_TRUE);
            } else {
                dayMdl.setTodayKbn(GSConstSchedule.TODAY_FALSE);
            }

            SchDataModel schMdl = null;

            for (int j = 0; j < schDataList.size(); j++) {
                //スケジュール１個
                schMdl = schDataList.get(j);
                //本日のスケジュールか判定
                if (Sch010Biz.isTodaySchedule(schMdl, frDate)) {
                    dspSchMdl = new SimpleScheduleModel();

                    //表示スケジュールを設定
                    dspSchMdl = __getSchDspMdl(schMdl, sessionUserSid, dspGpSid, frDate,
                                            paramMdl, reqMdl);

                    if (dspSchMdl != null) {
                        dayMdlList.add(dspSchMdl);
                    }
                }
            }
            dayMdl.setSchDataList(dayMdlList);
            colList.add(dayMdl);
            //日付を進める
            frDate.addDay(1);
        }
        monthMdl.setSch020SchList(colList);

        /** 期間スケジュールを作成 **/
        int sevenDayCnt = 0;
        int weekCnt = 0;

        //時間指定のスケジュール
        ArrayList<Sch020DayOfModel> colList2 = new ArrayList<Sch020DayOfModel>();
        //期間スケジュール
        ArrayList<Sch020WeekOfModel> swmList = new ArrayList<Sch020WeekOfModel>();
        HashMap<String, SimpleScheduleModel>  periodMdlMap
                                     = new HashMap<String, SimpleScheduleModel>();
        Sch020WeekOfModel weekMdl = new Sch020WeekOfModel();

        for (Sch020DayOfModel sdm : colList) {
            //時間指定のスケジュール
            colList2.add(sdm);
            if (!sdm.getSchDataList().isEmpty()) {
                ArrayList < SimpleScheduleModel > sdmList = sdm.getSchDataList();
                for (SimpleScheduleModel ssm : sdmList) {
                    if (ssm.getTimeKbn() == GSConstSchedule.TIME_NOT_EXIST) {
                        //期間のスケジュールをマップに格納
                        if (ssm.getSchAppendUrl() == null) {
                            //スケジュールのデータ
                            periodMdlMap.put(String.valueOf(ssm.getSchSid()), ssm);
                        } else {
                            //スケジュール以外のプラグインのデータ
                            periodMdlMap.put(ssm.getSchAppendUrl(), ssm);
                        }
                    }
                }
            }
            //週ごとにデータを作成
            sevenDayCnt = sevenDayCnt + 1;
            if (sevenDayCnt == 7) {
                weekCnt = weekCnt + 1;
                //期間スケジュールを取得
                weekMdl = new Sch020WeekOfModel();
                if (!periodMdlMap.isEmpty()) {
                    weekMdl = __getPeriodSch(weekMdl, periodMdlMap, prFrDate);
                }
                periodMdlMap = new HashMap<String, SimpleScheduleModel>();
                prFrDate.addDay(7);
                sevenDayCnt = 0;
                swmList.add(weekMdl);
            }
        }

        monthMdl.setSch020PeriodSchList(swmList);
        rowList.add(monthMdl);

        return rowList;
    }

    /**
     * <br>年月が同じが判定します
     * @param date 比較対象１
     * @param compDate 比較対象１
     * @return true:同年同月 false:同じではない
     */
    private boolean __isThisMonth(UDate date, UDate compDate) {
        if (date.equalsYear(compDate) && date.equalsMonth(compDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <br>[機  能] 指定グループに所属するユーザリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid セッションユーザSID
     * @param groupSid グループSID
     * @param myGroupFlg マイグループ選択
     * @param reqMdl リクエスト情報
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private List<LabelValueBean> __getUserLabelList(
        int userSid, int groupSid, boolean myGroupFlg,
        RequestModel reqMdl) throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        //グループ + 所属ユーザ
        String textGroupUser = gsMsg.getMessage("schedule.src.82");
        //グループスケジュール
        String textGroupSchedule = gsMsg.getMessage("schedule.src.83");
        List < LabelValueBean > labelList = null;

        SchDao schDao = new SchDao(con__);
        UserBiz usrBiz = new UserBiz();
        if (myGroupFlg) {
            labelList = usrBiz.getMyGroupUserLabelList(con__, userSid, groupSid, null);

            //閲覧不可のユーザを除外する
            List<Integer> notAccessUserList = schDao.getNotAccessUserList(userSid);
            ArrayList<LabelValueBean> labelList2 = new ArrayList<LabelValueBean>();
            for (LabelValueBean label : labelList) {
                if (notAccessUserList.indexOf(Integer.parseInt(label.getValue())) < 0) {
                    labelList2.add(label);
                }
            }
            labelList.clear();
            labelList.addAll(labelList2);

        } else {
            labelList = usrBiz.getNormalUserLabelList(con__, groupSid, null, false, gsMsg);

            //閲覧不可のユーザを除外する
            List<Integer> notAccessUserList = schDao.getNotAccessUserList(groupSid, userSid);
            ArrayList<LabelValueBean> labelList2 = new ArrayList<LabelValueBean>();
            for (LabelValueBean label : labelList) {
                if (notAccessUserList.indexOf(Integer.parseInt(label.getValue())) < 0) {
                    labelList2.add(label);
                }
            }
            labelList.clear();
            labelList.addAll(labelList2);

            labelList.add(0, new LabelValueBean(textGroupUser, "-2"));
            labelList.add(0, new LabelValueBean(textGroupSchedule, "-1"));
        }

        return labelList;
    }

    /**
     * <br>[機  能] 自動リロード時間を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param sessionUsrSid ユーザSID
     * @return int リロード時間
     * @throws SQLException SQL実行時例外
     */
    private int __getReloadtime(int sessionUsrSid)
    throws SQLException {

        int reloadTime = GSConstReserve.AUTO_RELOAD_10MIN;
        SchPriConfDao dao = new SchPriConfDao(con__);
        SchPriConfModel model = dao.select(sessionUsrSid);
        if (model != null) {
            reloadTime = model.getSccReload();
        }
        return reloadTime;
    }

    /**
     * <br>[機  能] 表示用スケジュールデータを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param schMdl スケジュールデータ
     * @param sessionUsrSid ユーザSID
     * @param grpSid グループSID
     * @param frDate 開始日時
     * @param paramMdl Sch020ParamModel
     * @param reqMdl リクエスト情報
     * @return SimpleScheduleModel 表示用モデル
     * @throws SQLException SQL実行時例外
     * @throws SQLException SQL実行時例外
     */
    private SimpleScheduleModel __getSchDspMdl(
            SchDataModel schMdl,
            int sessionUsrSid,
            int grpSid,
            UDate frDate,
            Sch020ParamModel paramMdl,
            RequestModel reqMdl) throws SQLException {

        SimpleScheduleModel dspSchMdl = new SimpleScheduleModel();

        boolean grpBelongHnt = false;

        if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER) {
            //表示スケジュールユーザと同じグループに所属しているか判定
            grpBelongHnt = __getSchUsrBelongHnt(schMdl.getScdUserBlongGpList());
        } else {
            //表示グループに所属しているか判定
            GroupBiz gpBiz = new GroupBiz();
            grpBelongHnt = gpBiz.isBelongGroup(sessionUsrSid, grpSid, con__);
        }

        //予定あり
        GsMessage gsMsg = new GsMessage(reqMdl);
        String textYoteiari = gsMsg.getMessage("schedule.src.9");

        if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER
                && schMdl.getScdUsrSid() == sessionUsrSid) {
            //本人
            dspSchMdl.setPublic(GSConstSchedule.DSP_PUBLIC);
            dspSchMdl.setTitle(schMdl.getScdTitle());
        } else if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER
                && schMdl.getScdUsrSid() != sessionUsrSid) {

            //他ユーザ
            if (schMdl.getScdPublic() == GSConstSchedule.DSP_YOTEIARI) {
                //予定あり
                dspSchMdl.setTitle(textYoteiari);
                dspSchMdl.setPublic(GSConstSchedule.DSP_YOTEIARI);

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
                //非公開
                dspSchMdl.setPublic(schMdl.getScdPublic());
                return dspSchMdl;

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP
                     && grpBelongHnt) {
                    //所属グループのみ公開(マイグループは除く)
                    dspSchMdl.setTitle(schMdl.getScdTitle());
                    dspSchMdl.setGrpEdKbn(GSConstSchedule.EDIT_CONF_GROUP);
                    dspSchMdl.setPublic(schMdl.getScdPublic());

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP
                    && !(grpBelongHnt)) {

                //閲覧可能な所属グループではないユーザには「予定あり」
                dspSchMdl.setTitle(textYoteiari);
                dspSchMdl.setPublic(GSConstSchedule.DSP_YOTEIARI);
            } else {
                //公開
                dspSchMdl.setTitle(schMdl.getScdTitle());
            }

        //グループスケジュール
        } else {

            if (sessionUsrSid == schMdl.getScdAuid()) {
                dspSchMdl.setKjnEdKbn(GSConstSchedule.EDIT_CONF_OWN);
            }

            if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC
                    && !(grpBelongHnt)) {
                return null;

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC
                    && grpBelongHnt) {

                //公開
                dspSchMdl.setTitle(schMdl.getScdTitle());
                dspSchMdl.setGrpEdKbn(GSConstSchedule.EDIT_CONF_GROUP);

            } else {
                //公開
                dspSchMdl.setTitle(schMdl.getScdTitle());
                dspSchMdl.setGrpEdKbn(GSConstSchedule.EDIT_CONF_GROUP);
            }

            //グループのスケジュール
            dspSchMdl.setPublic(schMdl.getScdPublic());
        }

        dspSchMdl.setSchSid(schMdl.getScdSid());
        dspSchMdl.setUserSid(String.valueOf(schMdl.getScdUsrSid()));
        dspSchMdl.setTime(Sch010Biz.getTimeString(schMdl, frDate));
        dspSchMdl.setTimeKbn(schMdl.getScdDaily());
        dspSchMdl.setBgColor(schMdl.getScdBgcolor());
        dspSchMdl.setValueStr(schMdl.getScdValue());
        dspSchMdl.setUserName(schMdl.getScdUserName());
        dspSchMdl.setFromDate(schMdl.getScdFrDate());
        dspSchMdl.setToDate(schMdl.getScdToDate());
        //ユーザ区分
        if (schMdl.getScdAppendUrl() == null) {
            //スケジュールのデータ
            dspSchMdl.setSchSid(schMdl.getScdSid());
            dspSchMdl.setUserKbn(String.valueOf(schMdl.getScdUsrKbn()));
        } else {
            //スケジュール以外のプラグインのデータ
            dspSchMdl.setSchAppendUrl(schMdl.getScdAppendUrl());
            dspSchMdl.setUserKbn(schMdl.getScdAppendId());
        }

        return dspSchMdl;
    }

    /**
     * <br>[機  能] セッションユーザがスケジュールユーザと同じグループに所属しているか判定
     * <br>[解  説]
     * <br>[備  考]
     * @param belongSids 所属グループSID
     * @return 所属フラグ
     */
    private boolean __getSchUsrBelongHnt(ArrayList<Integer> belongSids) {
        boolean belongFlg = false;
        if (belongSids != null && !belongSids.isEmpty()) {
            for (int gpSid : belongSids) {
                if (belongGpSidList__ != null) {
                    if (belongGpSidList__.indexOf(gpSid) > -1) {
                        belongFlg = true;
                    }
                }
            }
        }
        return belongFlg;
    }

    /**
     * <br>[機  能] マイグループメンバー存在チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch020ParamModel
     * @param sessionUsrSid ユーザSID
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateGroupMemberExistCheck(
            Sch020ParamModel paramMdl,
            int sessionUsrSid) throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        //マイグループ以外はOK
        if (!SchCommonBiz.isMyGroupSid(paramMdl.getSch010DspGpSid())) {
            return errors;
        }

        //ユーザ一覧取得
        int dspGpSid = SchCommonBiz.getDspGroupSid(paramMdl.getSch010DspGpSid());
        List<LabelValueBean> userLabel = __getUserLabelList(
                sessionUsrSid, dspGpSid, true, reqMdl__);


        if (userLabel == null || userLabel.size() < 1) {

            //デフォルトグループを設定
            SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
            String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con__, sessionUsrSid);
            userLabel = __getUserLabelList(
                    sessionUsrSid, SchCommonBiz.getDspGroupSid(dfGpSidStr),
                    SchCommonBiz.isMyGroupSid(dfGpSidStr), reqMdl__);
            if (userLabel == null || userLabel.size() < 1) {
                GroupBiz gbiz = new GroupBiz();
                dfGpSidStr = String.valueOf(gbiz.getDefaultGroupSid(sessionUsrSid, con__));
            }

            paramMdl.setSch010DspGpSid(dfGpSidStr);

            //グループ名を取得する。
            CmnMyGroupDao mygrpDao = new CmnMyGroupDao(con__);
            CmnMyGroupModel mygrpModel = mygrpDao.getMyGroupInfo(dspGpSid);
            String grpName = "";
            if (mygrpModel != null) {
                grpName = StringUtilHtml.transToHTmlPlusAmparsant(mygrpModel.getMgpName());
            }

            //所属ユーザなしエラー
            msg = new ActionMessage("error.user.not.exist.belong", grpName);
            StrutsUtil.addMessage(errors, msg, "not.exist");
        }


        return errors;
    }
    /**
     * <br>[機  能] 期間スケジュールを作成
     * <br>[解  説]
     * <br>[備  考]
     * @param weekMdl スケジュールデータ
     * @param periodMdlMap 期間データ
     * @param dspDate 画面表示日付
     * @return Sch010WeekOfModel 表示用モデル
     */
    private Sch020WeekOfModel __getPeriodSch(
            Sch020WeekOfModel weekMdl,
            HashMap<String, SimpleScheduleModel> periodMdlMap,
            UDate dspDate) {
        /** 期間スケジュールを作成 **/
        //期間モデル
        Sch010PeriodModel prdMdl = null;
        //期間スケジュール格納リスト
        ArrayList<SimpleScheduleModel> periodSchList = new ArrayList<SimpleScheduleModel>();
        //期間指定スケジュールを取得
        if (!periodMdlMap.isEmpty()) {
            Set<Entry<String, SimpleScheduleModel>> set = periodMdlMap.entrySet();
            Iterator<Entry<String, SimpleScheduleModel>> it = set.iterator();
            while (it.hasNext()) {
                Entry<String, SimpleScheduleModel> entry
                    = (Entry<String, SimpleScheduleModel>) it.next();
                SimpleScheduleModel schPrdMdl = (SimpleScheduleModel) entry.getValue();
                //開始日、終了日、期間を設定
                prdMdl = new Sch010PeriodModel();
                UDate ddate = dspDate.cloneUDate();
                prdMdl = __getPeriodMdl(schPrdMdl, ddate);
                schPrdMdl.setPeriodMdl(prdMdl);
                periodSchList.add(schPrdMdl);
            }
        }

        //Listを開始日付でソート
        if (!periodSchList.isEmpty()) {
            Collections.sort(periodSchList, new Comparator<SimpleScheduleModel>() {
                public int compare(SimpleScheduleModel t1, SimpleScheduleModel t2) {
                  return t1.getFromDate().compareDateYMD(t2.getFromDate()) * -1;
                }
             });
        }

        //行データ作成済みSID保持リスト
        ArrayList<String> set = new ArrayList<String>();
        //データ追加用リスト
        ArrayList<SimpleScheduleModel> rowAddList = null;
        //行スケジュール格納リスト
        ArrayList<ArrayList<SimpleScheduleModel>> rowSchList
                                         = new ArrayList<ArrayList<SimpleScheduleModel>>();

        //行の作成
        int rowCnt = 1;
        while (set.size() != periodSchList.size()) {
            rowAddList = new ArrayList<SimpleScheduleModel>();
            for (SimpleScheduleModel ssm : periodSchList) {
                if (!set.isEmpty()) {
                    if (ssm.getSchAppendUrl() == null) {
                        //スケジュールのデータ
                        if (set.indexOf(String.valueOf(ssm.getSchSid())) == -1
                                                                     && rowAddList.isEmpty()) {
                            //データをまだ作成してないand行データなし
                            rowAddList.add(ssm);
                            set.add(String.valueOf(ssm.getSchSid()));
                        }
                        if (set.indexOf(String.valueOf(ssm.getSchSid())) == -1) {
                            //データをまだ作成してない
                            if (ssm.getFromDate().compareDateYMD(
                                    rowAddList.get(rowAddList.size() - 1).getToDate()) != 1
                                && ssm.getFromDate().compareDateYMD(
                                        rowAddList.get(rowAddList.size() - 1).getToDate()) != 0) {
                                //日付が現在の行のデータとかぶらない場合
                                rowAddList.add(ssm);
                                set.add(String.valueOf(ssm.getSchSid()));
                            }
                        }
                    } else {
                        //他のプラグインのデータ
                        if (set.indexOf(ssm.getSchAppendUrl()) == -1 && rowAddList.isEmpty()) {
                            //データをまだ作成してないand行データなし
                            rowAddList.add(ssm);
                            set.add(ssm.getSchAppendUrl());
                        }
                        if (set.indexOf(ssm.getSchAppendUrl()) == -1) {
                            //データをまだ作成してない
                            if (ssm.getFromDate().compareDateYMD(
                                    rowAddList.get(rowAddList.size() - 1).getToDate()) != 1
                                && ssm.getFromDate().compareDateYMD(
                                        rowAddList.get(rowAddList.size() - 1).getToDate()) != 0) {
                                //日付が現在の行のデータとかぶらない場合
                                rowAddList.add(ssm);
                                set.add(ssm.getSchAppendUrl());
                            }
                        }
                    }
                } else {
                    rowAddList.add(ssm);
                    if (ssm.getSchAppendUrl() == null) {
                        //スケジュールのデータ
                        set.add(String.valueOf(ssm.getSchSid()));
                    } else {
                        //他プラグインのデータ
                        set.add(ssm.getSchAppendUrl());
                    }
                }
            }

            //行データの中の登録されている日を取得
            HashMap<Integer, SimpleScheduleModel> weekCnt
                                              = new HashMap<Integer, SimpleScheduleModel>();
            for (SimpleScheduleModel ssm : rowAddList) {
                int col = ssm.getPeriodMdl().getSchPeriodStart();
                int colCnt = ssm.getPeriodMdl().getSchPeriodCnt();
                while (0 < colCnt) {
                    int val = col + colCnt - 1;
                    weekCnt.put(val, ssm);
                    colCnt = colCnt - 1;
                }
            }
            //行データの中で開いている日がある場合は空のモデルを挿入
            ArrayList<SimpleScheduleModel> rowResultList = new ArrayList<SimpleScheduleModel>();
            ArrayList<String> rslSet = new ArrayList<String>();
            for (int i = 1; i < 8; i++) {
                if (!weekCnt.isEmpty()) {
                    SimpleScheduleModel sm = new SimpleScheduleModel();
                    if (weekCnt.get(i) == null) {
                        rowResultList.add(sm);
                    } else {
                        sm = weekCnt.get(i);
                        if (!rslSet.isEmpty()) {
                            if (sm.getSchAppendUrl() == null) {
                                //スケジュールのデータ
                                if (rslSet.indexOf(String.valueOf(sm.getSchSid())) == -1) {
                                    rowResultList.add(sm);
                                    rslSet.add(String.valueOf(sm.getSchSid()));
                                }
                            } else {
                                //他のプラグインのデータ
                                if (rslSet.indexOf(sm.getSchAppendUrl()) == -1) {
                                    rowResultList.add(sm);
                                    rslSet.add(sm.getSchAppendUrl());
                                }
                            }
                        } else {
                            rowResultList.add(weekCnt.get(i));
                            if (sm.getSchAppendUrl() == null) {
                                //スケジュールのデータ
                                rslSet.add(String.valueOf(sm.getSchSid()));
                            } else {
                                //他のプラグインのデータ
                                rslSet.add(sm.getSchAppendUrl());
                            }
                        }
                    }
                }
            }
            rowSchList.add(rowResultList);
            rowCnt = rowCnt + 1;
        }
        if (!rowSchList.isEmpty()) {
            weekMdl.setSch020NoTimeSchList(rowSchList);
            weekMdl.setSch020PeriodRow(rowCnt);
        }
        return weekMdl;
    }

    /**
     * <br>[機  能] 期間スケジュール位置取得
     * <br>[解  説]
     * <br>[備  考]
     * @param schMdl スケジュールデータ
     * @param date 画面表示日付
     * @return Sch010PeriodModel 表示用モデル
     */
    private Sch010PeriodModel __getPeriodMdl(
            SimpleScheduleModel schMdl,
            UDate date) {

        date.setHour(0);
        date.setMinute(0);
        date.setSecond(0);

        UDate startDate = null;
        UDate endDate = null;

        Sch010PeriodModel prdMdl = new Sch010PeriodModel();
        //開始日時が画面表示日より前の場合は、画面表示日をセット
        if (schMdl.getFromDate().compareDateYMD(date) == 1) {
            startDate = date.cloneUDate();
        } else {
            startDate = schMdl.getFromDate();
        }

        //終了日時が画面表示終了日より後の場合は、画面表示終了日をセット
        UDate toDate = date.cloneUDate();
        toDate.addDay(6);
        toDate.setHour(0);
        toDate.setMinute(0);
        toDate.setSecond(0);
        if (schMdl.getToDate().compareDateYMD(toDate) == -1) {
            endDate = toDate.cloneUDate();
        } else {
            endDate = schMdl.getToDate();
        }

        startDate.setDate(startDate.getStrYear() + startDate.getStrMonth() + startDate.getStrDay());
        startDate.setHour(0);
        startDate.setMinute(0);
        startDate.setSecond(0);
        endDate.setDate(endDate.getStrYear() + endDate.getStrMonth() + endDate.getStrDay());
        endDate.setHour(0);
        endDate.setMinute(0);
        endDate.setSecond(0);

        //日数を計算
        int dayCnt = UDateUtil.diffDay(startDate, endDate) + 1;
        prdMdl.setSchPeriodCnt(dayCnt);

        //開始位置を取得
        int start = 1;
        for (int i = 1; i < 8; i++) {
            if (startDate.compareDateYMD(date) == 0) {
                start = i;
            }
            date.addDay(1);
        }
        prdMdl.setSchPeriodStart(start);

        return prdMdl;
    }

    /**
     * <br>[機  能] 他プラグインデータを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch020ParamModel
     * @param frDate 表示開始日付
     * @param toDate 表示終了日付
     * @param usrSid ユーザSID
     * @param schDataList スケジュールデータ
     * @param con コネクション
     */
    private void __getAppendPlgData(
            Sch020ParamModel paramMdl,
            UDate frDate,
            UDate toDate,
            int usrSid,
            ArrayList <SchDataModel> schDataList,
            Connection con) {

        //他プラグイン情報を取得
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        ArrayList<SchDataModel> apdSchList = new ArrayList<SchDataModel>();
        if (pconfig__ != null) {
            UDate prmFrDate = frDate.cloneUDate();
            UDate prmToDate = toDate.cloneUDate();
            SchAppendDataParam scparamMdl = new SchAppendDataParam();
            scparamMdl.setUsrSid(usrSid);
            scparamMdl.setFrDate(prmFrDate);
            scparamMdl.setToDate(prmToDate);
            scparamMdl.setSrcId(GSConstSchedule.DSP_ID_SCH010);
            scparamMdl.setGrpSid(paramMdl.getSch010DspGpSid());
            scparamMdl.setDspDate(paramMdl.getSch010DspDate());
            scparamMdl.setReturnUrl(createUrl(paramMdl.getSch010DspDate(),
                    paramMdl.getSch010DspGpSid(), paramMdl.getSch020SelectUsrSid()));
            try {
                apdSchList = biz.getAppendSchData(reqMdl__, con, pconfig__, scparamMdl);
            } catch (Exception e) {
                log__.error("他プラグインのスケジュールデータ取得に失敗");
            }
        }
        if (!apdSchList.isEmpty()) {
            schDataList.addAll(apdSchList);
        }
    }

    /**
     * <br>[機  能]自画面のURLを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param dspDate 画面日付
     * @param dspGrpSid 表示グループSID
     * @param selectUsrSid 画面表示ユーザSID
     * @return スケジュールURL
     */
    public String createUrl(String dspDate, String dspGrpSid, String selectUsrSid
                                                                              ) {

        String returnUrl = null;

        //自画面のURLを作成
        String url = reqMdl__.getReferer();
        if (!StringUtil.isNullZeroString(url)) {
            returnUrl = url.substring(0, url.lastIndexOf("/"));
            returnUrl = returnUrl.substring(0, returnUrl.lastIndexOf("/"));
            returnUrl += "/schedule/sch020.do?";
            returnUrl += "sch010DspDate=" + dspDate;
            returnUrl += "&sch010DspGpSid=" + dspGrpSid;
            returnUrl += "&sch020SelectUsrSid=" + selectUsrSid;
            returnUrl += "&changeDateFlg=" + 1;
            try {
                returnUrl = URLEncoder.encode(returnUrl, Encoding.UTF_8);
            } catch (UnsupportedEncodingException e) {
                log__.error("自画面のURLの作成に失敗");
            }
        }

        return returnUrl;
    }


    /**
     * <br>[機  能] 週間スケジュールをPDF出力します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Sch020ParamModel
     * @param con コネクション
     * @param appRootPath アプリケーションルートパス
     * @param outTempDir テンポラリディレクトパス
     * @param tmpFileName テンポラリファイル名
     * @param reqMdl リクエスト情報
     * @return pdfModel SmlPdfModel
     * @throws IOException IO実行時例外
     */
    public SchGekPdfModel createSchGekPdf(
            Sch020ParamModel paramMdl,
            Connection con,
            String appRootPath,
            String outTempDir,
            String tmpFileName,
            RequestModel reqMdl)
        throws IOException {
        OutputStream oStream = null;

        //ヘッダー年月
        String headDate = paramMdl.getSch020StrDspDate();
        //スケジュールリスト
        ArrayList<Sch020MonthOfModel> scheduleList = paramMdl.getSch020ScheduleList();

        GsMessage gsMsg = new GsMessage(reqMdl);


        //表示グループ
        String dispGroup = new String();
        for (int i = 0; i < paramMdl.getSch010GpLabelList().size(); i++) {
            if (paramMdl.getSch010GpLabelList().get(i).getValue().equals(
                    paramMdl.getSch010DspGpSid())) {
                dispGroup = paramMdl.getSch010GpLabelList().get(i).getLabel();
            }
        }
        //表示ユーザ
        String dispUser = new String();
        for (int i = 0; i < paramMdl.getSch020UsrLabelList().size(); i++) {
            if (paramMdl.getSch020UsrLabelList().get(i).getValue()
                    .equals(paramMdl.getSch020SelectUsrSid())) {
                dispUser = paramMdl.getSch020UsrLabelList().get(i).getLabel();
                break;
            } else {
                //グループスケジュール
                dispUser = gsMsg.getMessage("schedule.src.83");
            }
        }

        //開始曜日
        int startWeek = paramMdl.getSch020DspStartWeek();

        //曜日リストの作成
        List <LabelValueBean> weekList = new ArrayList<LabelValueBean>();
        weekList = __getWeekData(startWeek, reqMdl);


        //PDFモデル
        SchGekPdfModel pdfModel = new SchGekPdfModel();
        //ヘッダー年月
        pdfModel.setDspDate(headDate);
        //表示グループ
        pdfModel.setDspGroup(dispGroup);
        //表示ユーザ
        pdfModel.setDspUser(dispUser);
        //曜日リスト
        pdfModel.setWeekList(weekList);
        //スケジュールリスト
        pdfModel.setScheduleList(scheduleList);


        String outBookName = pdfModel.getDspDate()
                + gsMsg.getMessage("schedule.sch020.1");
        String encOutBookName = fileNameCheck(outBookName) + ".pdf";
        pdfModel.setFileName(encOutBookName);

        try {
            IOTools.isDirCheck(outTempDir, true);
            oStream = new FileOutputStream(outTempDir + tmpFileName);
            SchGekPdfUtil pdfUtil = new SchGekPdfUtil(reqMdl);
            pdfUtil.createSchGekkanPdf(pdfModel, appRootPath, oStream);
        } catch (Exception e) {
            log__.error("スケジュールPDF出力に失敗しました。", e);
        } finally {
            if (oStream != null) {
                oStream.flush();
                oStream.close();
            }
        }
        log__.debug("スケジュールPDF出力を終了します。");

        return pdfModel;
    }

    /**
     * <br>[機  能] ファイル名として使用できるか文字列チェックする。
     * <br>[解  説] /\?*:|"<>. を除去
     *                    255文字超える場合は以降を除去
     * <br>[備  考]
     * @param fileName テンポラリディレクトリ
     * @return 出力したファイルのパス
     */
    private String fileNameCheck(String fileName) {
            String escName = fileName;

            escName = StringUtilHtml.replaceString(escName, "/", "");
            escName = StringUtilHtml.replaceString(escName, "\\", ""); //\
            escName = StringUtilHtml.replaceString(escName, "?", "");
            escName = StringUtilHtml.replaceString(escName, "*", "");
            escName = StringUtilHtml.replaceString(escName, ":", "");
            escName = StringUtilHtml.replaceString(escName, "|", "");
            escName = StringUtilHtml.replaceString(escName, "\"", ""); //"
            escName = StringUtilHtml.replaceString(escName, "<", "");
            escName = StringUtilHtml.replaceString(escName, ">", "");
            escName = StringUtilHtml.replaceString(escName, ".", "");
            escName = StringUtil.trimRengeString(escName, 251); //ファイル名＋拡張子(.pdf)=255文字以内

        return escName;
    }

    /**
     * <br>[機  能] 曜日データを取得する
     * <br>[解  説] 開始パラメータを渡して曜日のリストを返す。
     * <br>[備  考]
     * @param startWeek 開始曜日
     * @param reqMdl リクエスト情報
     * @return 出力したファイルのパス
     */
    private List<LabelValueBean> __getWeekData(int startWeek, RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage();

        List<LabelValueBean> weekData = new ArrayList<LabelValueBean>();
        switch (startWeek) {
            case GSConstSchedule.CHANGE_WEEK_SUN:
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.sunday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SUN)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.Monday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_MON)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.tuesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_TUE)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.wednesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_WED)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.thursday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_THU)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.friday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_FRI)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.saturday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SAT)));
                break;
            case GSConstSchedule.CHANGE_WEEK_MON:
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.Monday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_MON)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.tuesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_TUE)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.wednesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_WED)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.thursday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_THU)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.friday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_FRI)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.saturday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SAT)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.sunday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SUN)));
                break;
            case GSConstSchedule.CHANGE_WEEK_TUE:
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.tuesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_TUE)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.wednesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_WED)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.thursday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_THU)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.friday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_FRI)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.saturday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SAT)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.sunday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SUN)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.Monday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_MON)));
                break;
            case GSConstSchedule.CHANGE_WEEK_WED:
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.wednesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_WED)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.thursday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_THU)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.friday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_FRI)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.saturday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SAT)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.sunday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SUN)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.Monday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_MON)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.tuesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_TUE)));
                break;
            case GSConstSchedule.CHANGE_WEEK_THU:
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.thursday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_THU)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.friday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_FRI)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.saturday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SAT)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.sunday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SUN)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.Monday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_MON)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.tuesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_TUE)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.wednesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_WED)));
                break;
            case GSConstSchedule.CHANGE_WEEK_FRI:
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.friday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_FRI)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.saturday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SAT)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.sunday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SUN)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.Monday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_MON)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.tuesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_TUE)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.wednesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_WED)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.thursday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_THU)));
                break;
            case GSConstSchedule.CHANGE_WEEK_SAT:
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.saturday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SAT)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.sunday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SUN)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.Monday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_MON)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.tuesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_TUE)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.wednesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_WED)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.thursday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_THU)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.friday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_FRI)));
                break;
            default :
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.sunday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SUN)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.Monday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_MON)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.tuesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_TUE)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.wednesday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_WED)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.thursday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_THU)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.friday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_FRI)));
                weekData.add(new LabelValueBean(gsMsg.getMessage("cmn.saturday"),
                        String.valueOf(GSConstSchedule.CHANGE_WEEK_SAT)));
                break;
        }
        return weekData;
    }
}
