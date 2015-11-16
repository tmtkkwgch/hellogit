package jp.groupsession.v2.sch.sch030;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.SchAppendDataParam;
import jp.groupsession.v2.cmn.model.UserSearchModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.ScheduleSearchDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.SchLabelValueModel;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.sch.pdf.SchNikPdfModel;
import jp.groupsession.v2.sch.pdf.SchNikPdfUtil;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.sch.sch010.Sch010DayOfModel;
import jp.groupsession.v2.sch.sch010.Sch010UsrModel;
import jp.groupsession.v2.sch.sch010.Sch010WeekOfModel;
import jp.groupsession.v2.sch.sch010.SimpleScheduleModel;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] スケジュール 日間画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch030Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch030Biz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /** 在席管理利用状況を保持*/
    private int zaisekiUseOk__ = GSConstSchedule.PLUGIN_USE;
    /** ショートメール利用状況を保持*/
    private int smailUseOk__ = GSConstSchedule.PLUGIN_USE;
    /** pconfig */
    private PluginConfig pconfig__ = null;
    /** 画面表示グループSID */
    private String dspGpSid__ = null;
    /** セッションユーザ所属グループSIDリスト */
    private List<Integer> belongGpSidList__ = null;

    /**
     * <p>コンストラクタ
     * @param pconfig PluginConfig
     * @param reqMdl RequestModel
     */
    public Sch030Biz(PluginConfig pconfig, RequestModel reqMdl) {
        reqMdl__ = reqMdl;
        pconfig__ = pconfig;
    }

    /**
     * 初期表示画面情報を取得します
     * @param paramMdl Sch030ParamModel
     * @param con コネクション
     * @return アクションフォーム
     * @throws SQLException SQL実行時例外
     */
    public Sch030ParamModel getInitData(
                    Sch030ParamModel paramMdl,
                    Connection con) throws SQLException {

        paramMdl.setDspMod(GSConstSchedule.DSP_MOD_DAY);

        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //セッションユーザの所属グループを格納
        CmnBelongmDao bdao = new CmnBelongmDao(con);
        belongGpSidList__ = bdao.selectUserBelongGroupSid(sessionUsrSid);

        //管理者設定を取得
        SchCommonBiz cmnbiz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = cmnbiz.getAdmConfModel(con);

        //共有範囲
        paramMdl.setSch010CrangeKbn(adminConf.getSadCrange());

        //管理者設定から１時間あたりのメモリ数を取得
        SchCommonBiz cmnBiz = new SchCommonBiz(reqMdl__);
        int memCount = cmnBiz.getDayScheduleHourMemoriCount(con);
        //個人設定取得&作成
        Sch010Biz biz = new Sch010Biz(reqMdl__);
        SchPriConfModel confMdl = biz.getPrivateConf(sessionUsrSid, con);
        paramMdl.setSch030Reload(confMdl.getSccReload());
        String confFrHour = confMdl.getSccFrDate().getStrHour();
        String confToHour = confMdl.getSccToDate().getStrHour();
        int frHour = Integer.parseInt(confFrHour);
        int toHour = Integer.parseInt(confToHour);
        int totalCols = (toHour - frHour + 1) * memCount + 2;

        //リクエストパラメータを取得
        //表示開始日
        String strDspDate = NullDefault.getString(
                paramMdl.getSch010DspDate(), new UDate().getDateString());
        UDate dspDate = new UDate();
        dspDate.setDate(strDspDate);

        //個人設定よりデフォルト表示グループSIDを取得する。
        //データが存在しない場合、グループが削除されていた場合はデフォルト所属グループを返す
        SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
        //グループコンボ設定
        paramMdl.setSch010GpLabelList(biz.getGroupLabelList(con, sessionUsrSid));

        //デフォルト表示グループ
        String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);
        int dfGpSid = SchCommonBiz.getDspGroupSid(dfGpSidStr);

        int dspGpSid = 0;
        boolean myGroupFlg = false;
        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getSch010DspGpSid(), dfGpSidStr);
        dspGpSidStr = scBiz.getEnableSelectGroup(
                paramMdl.getSch010GpLabelList(), dspGpSidStr, dfGpSidStr);
        if (SchCommonBiz.isMyGroupSid(dspGpSidStr)) {
            dspGpSid = SchCommonBiz.getDspGroupSid(dspGpSidStr);
            paramMdl.setSch010DspGpSid(dspGpSidStr);
            dspGpSid__ = dspGpSidStr;
            myGroupFlg = true;
        } else {
            dspGpSid = NullDefault.getInt(paramMdl.getSch010DspGpSid(), dfGpSid);
            paramMdl.setSch010DspGpSid(dspGpSidStr);
            dspGpSid__ = dspGpSidStr;
        }

        //表示項目取得
        paramMdl.setDspMod(GSConstSchedule.DSP_MOD_DAY);
        paramMdl.setSch010DspDate(strDspDate);
        paramMdl.setSch030StrDate(getHeaderDate(dspDate, reqMdl__));
        paramMdl.setSch030FromHour(confFrHour);
        paramMdl.setSch030ToHour(confToHour);
        paramMdl.setSch030TotalCols(String.valueOf(totalCols));
        paramMdl.setSch030MemoriCount(String.valueOf(memCount));
        paramMdl.setSch030TimeChartList(__getTimeChart(frHour, toHour));
        //在席・ショートメール使用状況
        zaisekiUseOk__ = paramMdl.getZaisekiUseOk();
        smailUseOk__ = paramMdl.getSmailUseOk();

        //本人グループのスケジュールを取得
        paramMdl.setSch010TopList(
                __getDayScheduleTopList(
                        dspDate.cloneUDate(),
                        dspGpSid,
                        frHour,
                        toHour,
                        sessionUsrSid,
                        myGroupFlg,
                        con));

        //グループメンバーのスケジュールを取得
        paramMdl.setSch010BottomList(
                __getDayScheduleBottomList(
                        dspDate.cloneUDate(),
                        dspGpSid,
                        frHour,
                        toHour,
                        sessionUsrSid,
                        myGroupFlg,
                        con));
        CommonBiz commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(
                con, usModel, GSConstSchedule.PLUGIN_ID_SCHEDULE);
        if (adminUser) {
            paramMdl.setAdminKbn(GSConst.USER_ADMIN);
        } else {
            paramMdl.setAdminKbn(GSConst.USER_NOT_ADMIN);
        }

        //閲覧不可のグループを設定
        SchDao schDao = new SchDao(con);
        paramMdl.setSchNotAccessGroupList(schDao.getNotAccessGrpList(sessionUsrSid));

        return paramMdl;
    }


    /**
     * <br>指定日付のタイムチャートを取得する
     * @param frHour 開始時刻
     * @param toHour 終了時刻
     * @return ArrayList タイムチャートリスト
     */
    private ArrayList<String> __getTimeChart(int frHour, int toHour) {

        ArrayList<String> timeList = new ArrayList<String>();
        for (int i = frHour; i <= toHour; i++) {
            timeList.add(String.valueOf(i));
        }
        return timeList;
    }

    /**
     * <br>グループと指定ユーザの週間スケジュールを取得します
     * @param dspDate 開始日付
     * @param grpSid 表示グループSID
     * @param fromHour 表示開始時刻
     * @param toHour 表示終了時刻
     * @param sessionUserSid セッションユーザSID
     * @param myGroupFlg マイグループ選択
     * @param con コネクション
     * @return ArrayList グループ>指定ユーザの順に格納
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<Sch010WeekOfModel> __getDayScheduleTopList(
            UDate dspDate,
            int grpSid,
            int fromHour,
            int toHour,
            int sessionUserSid,
            boolean myGroupFlg,
            Connection con) throws SQLException {

        //抽出範囲整形
        UDate frDate = dspDate.cloneUDate();
        frDate.setHour(fromHour);
        frDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
        frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);

        UDate toDate = dspDate.cloneUDate();
        toDate.setHour(toHour);
        toDate.setMinute(GSConstSchedule.DAY_END_MINUTES);
        toDate.setSecond(GSConstSchedule.DAY_END_SECOND);
        toDate.setMilliSecond(GSConstSchedule.DAY_END_MILLISECOND);

        //グループ・指定ユーザのcolListを保持
        ArrayList<Sch010WeekOfModel> rowList = new ArrayList<Sch010WeekOfModel>();
        //ユーザ情報を保持
        Sch010UsrModel usMdl = null;
        ArrayList < Sch010DayOfModel > colList = null;
        //DBスケジュール情報
        ArrayList < SchDataModel > schDataList = null;
        //ユーザ別、１週間分のスケジュール
        Sch010WeekOfModel weekMdl = null;

        ScheduleSearchDao schDao = new ScheduleSearchDao(con);
        Sch010DayOfModel dayMdl = null;
        ArrayList < SimpleScheduleModel > dayMdlList = null;
        SimpleScheduleModel dspSchMdl = null;
        UDate date = dspDate.cloneUDate();
        SchDataModel schMdl = null;
        //表示グループに所属しているか判定
        GroupBiz gpBiz = new GroupBiz();

        //個人設定を取得
        SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = scBiz.getSchPriConfModel(con, sessionUserSid);

        //閲覧可能グループかを判定
        boolean accessGrp = true;
        SchDao scheduleDao = new SchDao(con);
        if (!myGroupFlg) {
            accessGrp = scheduleDao.canAccessGroupSchedule(grpSid, sessionUserSid);
        }

        //グループ
        if (!myGroupFlg && accessGrp) {
            GroupDao grpDao = new GroupDao(con);
            CmnGroupmModel grpMdl = grpDao.getGroup(grpSid);
            weekMdl = new Sch010WeekOfModel();
            weekMdl.setZaisekiUseOk(zaisekiUseOk__);
            weekMdl.setSmailUseOk(smailUseOk__);
            colList = new ArrayList<Sch010DayOfModel>();
            usMdl = new Sch010UsrModel();
            usMdl.setUsrSid(grpSid);
            usMdl.setUsrKbn(GSConstSchedule.USER_KBN_GROUP);
            usMdl.setZaisekiKbn(GSConst.UIOSTS_IN);

            if (grpMdl != null) {
                usMdl.setUsrName(grpMdl.getGrpName());
            }

            //スケジュール登録可能グループかを判定
            usMdl.setSchRegistFlg(
                    scheduleDao.canRegistGroupSchedule(grpSid, sessionUserSid));

            weekMdl.setSch010UsrMdl(usMdl);

            //スケジュール情報を取得(グループ)
            schDataList = schDao.select(
                    grpSid,
                    GSConstSchedule.USER_KBN_GROUP,
                    -1,
                    frDate,
                    toDate,
                    GSConstSchedule.DSP_MOD_DAY,
                    sessionUserSid);
            //１日分のスケジュール
            dayMdlList = new ArrayList<SimpleScheduleModel>();
            dayMdl = new Sch010DayOfModel();
            dayMdl.setSchDate(date.getDateString());
            dayMdl.setUsrSid(grpSid);
            dayMdl.setUsrKbn(GSConstSchedule.USER_KBN_GROUP);
            dayMdl.setWeekKbn(date.getWeek());
            for (int j = 0; j < schDataList.size(); j++) {
                //スケジュール１個
                schMdl = schDataList.get(j);
                dspSchMdl = new SimpleScheduleModel();

                if (sessionUserSid == schMdl.getScdAuid()) {
                    dspSchMdl.setKjnEdKbn(GSConstSchedule.EDIT_CONF_OWN);
                }

                if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC
                        && !(gpBiz.isBelongGroup(sessionUserSid, grpSid, con))) {
                    //非公開
                    continue;
                } else {
                    //公開
                    dspSchMdl.setTitle(schMdl.getScdTitle());
                    dspSchMdl.setGrpEdKbn(GSConstSchedule.EDIT_CONF_GROUP);
                    dspSchMdl.setPublic(schMdl.getScdPublic());
                    dspSchMdl.setSchSid(schMdl.getScdSid());
                    dspSchMdl.setTime(getTimeString(schMdl, frDate, toDate));
                    dspSchMdl.setBgColor(schMdl.getScdBgcolor());
                    dspSchMdl.setFromDate(schMdl.getScdFrDate());
                    dspSchMdl.setToDate(schMdl.getScdToDate());
                    dspSchMdl.setTimeKbn(schMdl.getScdDaily());
                    dspSchMdl.setValueStr(schMdl.getScdValue());
                    dayMdlList.add(dspSchMdl);
                }
            }

            dayMdl.setSchDataList(dayMdlList);
            colList.add(dayMdl);
            weekMdl.setSch010SchList(colList);
            rowList.add(weekMdl);
        }

        //指定ユーザ
        weekMdl = new Sch010WeekOfModel();
        weekMdl.setZaisekiUseOk(zaisekiUseOk__);
        weekMdl.setSmailUseOk(smailUseOk__);
        colList = new ArrayList<Sch010DayOfModel>();
        usMdl = new Sch010UsrModel();
        UserSearchDao usrDao = new UserSearchDao(con);
        UserSearchModel usrInfMdl = usrDao.getUserInfoJtkb(
                sessionUserSid, GSConstUser.USER_JTKBN_ACTIVE);
        usMdl.setUsrName(usrInfMdl.getUsiSei() + " " + usrInfMdl.getUsiMei());
        usMdl.setUsrSid(sessionUserSid);
        usMdl.setUsrKbn(GSConstSchedule.USER_KBN_USER);
        usMdl.setZaisekiKbn(usrInfMdl.getUioStatus());
        usMdl.setZaisekiMsg(usrInfMdl.getUioComment());
        //ショートメールプラグインを使用していないユーザを除外する。
        //送信制限されているユーザを除外する。
        List<Integer> smlUsrs = new ArrayList<Integer>();
        smlUsrs.add(sessionUserSid);
        CommonBiz commonBiz = new CommonBiz();
        smlUsrs = (ArrayList<Integer>) commonBiz.getCanUseSmailUser(con, smlUsrs);
        SmlCommonBiz smlCommonBiz = new SmlCommonBiz(con, reqMdl__);
        smlUsrs = smlCommonBiz.getValiableDestUsrSid(con,
                reqMdl__.getSmodel().getUsrsid(), smlUsrs);
        //ショートメール有効無効設定
        if (!smlUsrs.contains(usMdl.getUsrSid())) {
            usMdl.setSmlAble(0);
        } else {
            usMdl.setSmlAble(1);
        }
        usMdl.setSchRegistFlg(true);

        weekMdl.setSch010UsrMdl(usMdl);

        if (pconf.getSccGrpShowKbn() == 0) {
            //個人設定でグループスケジュールを表示にしている場合は所属グループのスケジュールを取得
            CmnBelongmDao belongDao = new CmnBelongmDao(con);
            ArrayList<Integer> belongList = belongDao.selectUserBelongGroupSid(sessionUserSid);
            schDataList = schDao.getBelongGroupSchData2(
                    belongList,
                    -1,
                    frDate,
                    toDate,
                    GSConstSchedule.DSP_MOD_DAY,
                    sessionUserSid);
        }
        //スケジュール情報を取得(指定ユーザ)
        ArrayList <SchDataModel> memSchDataList = schDao.select(
                sessionUserSid,
                GSConstSchedule.USER_KBN_USER,
                -1,
                frDate,
                toDate,
                GSConstSchedule.DSP_MOD_DAY,
                sessionUserSid);
        __getAppendPlgData(dspDate, sessionUserSid, memSchDataList, con, reqMdl__);

        if (schDataList == null) {
            schDataList = new ArrayList<SchDataModel>();
        }
        schDataList.addAll(memSchDataList);

        date = dspDate.cloneUDate();
        //１日分のスケジュール
        dayMdlList = new ArrayList<SimpleScheduleModel>();
        dayMdl = new Sch010DayOfModel();
        dayMdl.setHolidayName(null);
        dayMdl.setSchDate(date.getDateString());
        dayMdl.setUsrSid(sessionUserSid);
        dayMdl.setUsrKbn(GSConstSchedule.USER_KBN_USER);
        dayMdl.setWeekKbn(date.getWeek());
        schMdl = null;
        for (int j = 0; j < schDataList.size(); j++) {
            //スケジュール１個
            schMdl = schDataList.get(j);
            dspSchMdl = new SimpleScheduleModel();
            dspSchMdl.setKjnEdKbn(GSConstSchedule.EDIT_CONF_OWN);
            dspSchMdl.setSchSid(schMdl.getScdSid());
            dspSchMdl.setTitle(schMdl.getScdTitle());
            dspSchMdl.setTime(getTimeString(schMdl, frDate, toDate));
            dspSchMdl.setPublic(schMdl.getScdPublic());
            dspSchMdl.setBgColor(schMdl.getScdBgcolor());
            dspSchMdl.setFromDate(schMdl.getScdFrDate());
            dspSchMdl.setToDate(schMdl.getScdToDate());
            dspSchMdl.setTimeKbn(schMdl.getScdDaily());
            dspSchMdl.setValueStr(schMdl.getScdValue());
            dspSchMdl.setUserSid(String.valueOf(schMdl.getScdUsrSid()));
            dspSchMdl.setUserKbn(String.valueOf(schMdl.getScdUsrKbn()));
            if (StringUtil.isNullZeroStringSpace(schMdl.getScdAppendUrl())) {
                //スケジュールのデータ
                dspSchMdl.setSchSid(schMdl.getScdSid());
                dspSchMdl.setUserKbn(String.valueOf(schMdl.getScdUsrKbn()));

                if (dspSchMdl.getUserKbn().equals(String.valueOf(GSConstSchedule.USER_KBN_GROUP))
                        && pconf.getSccGrpShowKbn() == 0) {
                   //グループスケジュールかつ個人設定でグループスケジュールを表示
                   dayMdlList.add(dspSchMdl);
                } else if (dspSchMdl.getUserKbn()
                       .equals(String.valueOf(GSConstSchedule.USER_KBN_USER))) {
                   //ユーザスケジュール
                   dayMdlList.add(dspSchMdl);
                }

            } else {
                //スケジュール以外のプラグインのデータ
                dspSchMdl.setSchAppendUrl(schMdl.getScdAppendUrl());
                dspSchMdl.setUserKbn(schMdl.getScdAppendId());
                dayMdlList.add(dspSchMdl);
            }


        }
        dayMdl.setSchDataList(dayMdlList);
        colList.add(dayMdl);
        weekMdl.setSch010SchList(colList);
        rowList.add(weekMdl);

        return rowList;
    }

    /**
     * <br>表示グループに所属するユーザの週間スケジュールを取得します
     * @param dspDate 開始日付
     * @param gpSid 表示グループSID
     * @param frHour 開始時間
     * @param toHour 終了時間
     * @param usrSid セッションユーザSID
     * @param myGroupFlg マイグループ選択
     * @param con コネクション
     * @return ArrayList 役職>姓カナ>名カナの順に格納
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<Sch010WeekOfModel> __getDayScheduleBottomList(
            UDate dspDate,
            int gpSid,
            int frHour,
            int toHour,
            int usrSid,
            boolean myGroupFlg,
            Connection con) throws SQLException {

        //所属ユーザを取得
        UserSearchDao usDao = new UserSearchDao(con);
        //除外するユーザSIDを設定
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        usrSids.add(new Integer(GSConstUser.SID_ADMIN));
        usrSids.add(new Integer(usrSid));

        //スケジュール個人設定で取得した表示順を取得する。
        SchCommonBiz sBiz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = sBiz.getSchPriConfModel(con, usrSid);
        SchAdmConfModel aconf = sBiz.getAdmConfModel(con);

        int sortKey1 = -1;
        int orderKey1 = -1;
        int sortKey2 = -1;
        int orderKey2 = -1;

        //各ユーザが設定したメンバー表示順
        if (pconf.getSccSortEdit() == GSConstSchedule.MEM_EDIT_EXECUTE
                && aconf.getSadSortKbn() == GSConstSchedule.MEM_DSP_USR) {
            log__.debug("***ユーザが設定したソート順で表示します***");
            sortKey1 = pconf.getSccSortKey1();
            orderKey1 = pconf.getSccSortOrder1();
            sortKey2 = pconf.getSccSortKey2();
            orderKey2 = pconf.getSccSortOrder2();

        //管理者で設定したメンバー表示順
        } else {
            log__.debug("***管理者が設定したソート順で表示します***");
            sortKey1 = aconf.getSadSortKey1();
            orderKey1 = aconf.getSadSortOrder1();
            sortKey2 = aconf.getSadSortKey2();
            orderKey2 = aconf.getSadSortOrder2();
        }

        //グループメンバー取得
        SchDao schDao = new SchDao(con);
        ArrayList<UserSearchModel> belongList = null;
        List<Integer> notAccessUserList = null;
        if (!myGroupFlg) {
            belongList = usDao.getBelongUserInfoJtkb(gpSid,
                    usrSids, sortKey1, orderKey1, sortKey2, orderKey2);
            notAccessUserList = schDao.getNotAccessUserList(gpSid, usrSid);
        } else {
            belongList = usDao.getMyGroupBelongUserInfoJtkb(gpSid, usrSid,
                    usrSids, sortKey1, orderKey1, sortKey2, orderKey2);
            notAccessUserList = schDao.getNotAccessUserList(usrSid);
        }

        //ショートメールプラグインを使用していないユーザを除外する。
        //送信制限されているユーザを除外する。
        List<Integer> smlUsrs = new ArrayList<Integer>();
        for (UserSearchModel usMdl: belongList) {
            smlUsrs.add(usMdl.getUsrSid());
        }
        CommonBiz commonBiz = new CommonBiz();
        smlUsrs = (ArrayList<Integer>) commonBiz.getCanUseSmailUser(con, smlUsrs);
        SmlCommonBiz smlCommonBiz = new SmlCommonBiz(con, reqMdl__);
        smlUsrs = smlCommonBiz.getValiableDestUsrSid(con,
                reqMdl__.getSmodel().getUsrsid(), smlUsrs);

        //閲覧を許可されていないユーザを除外する
        ArrayList<UserSearchModel> belongList2 = new ArrayList<UserSearchModel>();
        for (UserSearchModel userData : belongList) {
            if (notAccessUserList.indexOf(userData.getUsrSid()) < 0) {
                //ショートメール有効無効設定
                if (!smlUsrs.contains(userData.getUsrSid())) {
                    userData.setSmlAble(0);
                } else {
                    userData.setSmlAble(1);
                }
                belongList2.add(userData);
            }
        }
        belongList.clear();
        belongList.addAll(belongList2);

        //表示グループに所属しているか判定
        GroupBiz gpBiz = new GroupBiz();
        boolean belongGrpHnt = gpBiz.isBelongGroup(usrSid, gpSid, con);

        //一括で生成する様に変更
        ArrayList<Sch010WeekOfModel> rowList = getDailyUserScheduleNew(
                belongList, dspDate.cloneUDate(), frHour, toHour, con, belongGrpHnt, myGroupFlg,
                reqMdl__);
        return rowList;
    }

    /**
     * <br>ユーザ毎の日間のスケジュールを取得します
     * @param belongList ユーザ情報リスト
     * @param dspDate 表示開始日付
     * @param frHour 開始時間
     * @param toHour 終了時間
     * @param con コネクション
     * @param belongGrpHnt 所属グループ判定 true:所属している false:所属していない
     * @param myGroupFlg マイグループ判定 true:所属している false:所属していない
     * @param reqMdl リクエスト情報
     * @return Sch010WeekOfModel 週間スケジュール
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<Sch010WeekOfModel> getDailyUserScheduleNew(
            ArrayList<UserSearchModel> belongList,
            UDate dspDate,
            int frHour,
            int toHour,
            Connection con,
            boolean belongGrpHnt,
            boolean myGroupFlg,
            RequestModel reqMdl) throws SQLException {

        ArrayList<Sch010WeekOfModel> rowList = new ArrayList<Sch010WeekOfModel>();

        //取得範囲整形
        UDate fromDate = dspDate.cloneUDate();
        fromDate.setHour(frHour);
        fromDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
        fromDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        fromDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        UDate toDate = dspDate.cloneUDate();
        toDate.setHour(toHour);
        toDate.setMinute(GSConstSchedule.DAY_END_MINUTES);
        toDate.setSecond(GSConstSchedule.DAY_END_SECOND);
        toDate.setMilliSecond(GSConstSchedule.DAY_END_MILLISECOND);
        Sch010WeekOfModel weekMdl = null;

        //スケジュール情報を取得(指定ユーザ)
        //DBスケジュール情報
        ScheduleSearchDao schDao = new ScheduleSearchDao(con);
        ArrayList<SchDataModel> schDataList = schDao.selectUsers(
                belongList,
                GSConstSchedule.USER_KBN_USER,
                -1,
                fromDate,
                toDate,
                GSConstSchedule.DSP_MOD_DAY);

        //他プラグイン情報を取得
        for (UserSearchModel usm : belongList) {
            __getAppendPlgData(dspDate, usm.getUsrSid(), schDataList, con, reqMdl);
        }

        //スケジュール登録を許可されていないユーザの一覧を取得
        SchDao scheduleDao = new SchDao(con);
        List<Integer> notRegistUserList
            = scheduleDao.getNotRegistUserList(reqMdl.getSmodel().getUsrsid());

        //所属ユーザ
        UserSearchModel dbUsrMdl = null;
        for (int i = 0; i < belongList.size(); i++) {
            dbUsrMdl = belongList.get(i);
            //ユーザ別に１週間のスケジュールを取得
            weekMdl = __getUserWeekOfModel(dspDate, dbUsrMdl, schDataList,
                                           fromDate, toDate, belongGrpHnt, myGroupFlg,
                                           reqMdl);
            weekMdl.setZaisekiUseOk(zaisekiUseOk__);
            weekMdl.setSmailUseOk(smailUseOk__);

            weekMdl.setSchRegistFlg(
                    notRegistUserList.indexOf(weekMdl.getSch010UsrMdl().getUsrSid()) < 0);
            weekMdl.getSch010UsrMdl().setSchRegistFlg(weekMdl.isSchRegistFlg());

            rowList.add(weekMdl);
        }
        return rowList;
    }

    /**
     * ユーザ毎の１日のスケジュールモデルを生成する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param dspDate 表示開始日
     * @param dbUsrMdl 生成対象のユーザ情報
     * @param schDataList スケジュール情報
     * @param fromDate 開始日付
     * @param toDate 終了日付
     * @param belongGrpHnt 所属グループ判定 true:所属している false:所属していない
     * @param myGroupFlg マイグループ選択フラグ
     * @param reqMdl リクエスト情報
     * @return Sch010WeekOfModel
     * @throws SQLException SQL実行時例外
     */
    private Sch010WeekOfModel __getUserWeekOfModel(
            UDate dspDate,
            UserSearchModel dbUsrMdl,
            ArrayList < SchDataModel > schDataList,
            UDate fromDate,
            UDate toDate,
            boolean belongGrpHnt,
            boolean myGroupFlg,
            RequestModel reqMdl) throws SQLException {

        Sch010WeekOfModel weekMdl = new Sch010WeekOfModel();
        ArrayList<Sch010DayOfModel> colList = new ArrayList<Sch010DayOfModel>();
        //指定ユーザ
        int usrSid = dbUsrMdl.getUsrSid();
        Sch010UsrModel usMdl = new Sch010UsrModel();
        usMdl.setUsrName(dbUsrMdl.getUsiSei() + " " + dbUsrMdl.getUsiMei());
        usMdl.setUsrSid(usrSid);
        usMdl.setUsrKbn(GSConstSchedule.USER_KBN_USER);
        usMdl.setZaisekiKbn(dbUsrMdl.getUioStatus());
        usMdl.setZaisekiMsg(dbUsrMdl.getUioComment());
        usMdl.setSmlAble(dbUsrMdl.getSmlAble());

        weekMdl.setSch010UsrMdl(usMdl);

        UDate date = dspDate.cloneUDate();
        ArrayList<SimpleScheduleModel> dayMdlList = null;
        Sch010DayOfModel dayMdl = null;
        SimpleScheduleModel dspSchMdl = null;

        //１日分のスケジュール
        dayMdlList = new ArrayList<SimpleScheduleModel>();
        dayMdl = new Sch010DayOfModel();
        dayMdl.setHolidayName(null);
        dayMdl.setSchDate(date.getDateString());
        dayMdl.setUsrSid(usrSid);
        dayMdl.setUsrKbn(GSConstSchedule.USER_KBN_USER);
        dayMdl.setWeekKbn(date.getWeek());
        SchDataModel schMdl = null;
        //予定あり
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textYoteiari = gsMsg.getMessage("schedule.src.9");
        for (int j = 0; j < schDataList.size(); j++) {
            //スケジュール１個
            schMdl = schDataList.get(j);
            if (schMdl.getScdUsrSid() != usrSid) {
                continue;
            }


            if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER) {
            //ユーザスケジュールの場合表示スケジュールユーザと同じグループに所属しているか判定
                belongGrpHnt = __getSchUsrBelongHnt(schMdl.getScdUserBlongGpList());
            }


            //本日のスケジュールか判定
            dspSchMdl = new SimpleScheduleModel();
            dspSchMdl.setSchSid(schMdl.getScdSid());
            if (schMdl.getScdPublic() == GSConstSchedule.DSP_YOTEIARI) {
                //予定あり
                dspSchMdl.setTitle(textYoteiari);
                dspSchMdl.setPublic(GSConstSchedule.DSP_YOTEIARI);

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
                //非公開
                continue;

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP
                     && belongGrpHnt) {

                dspSchMdl.setTitle(schMdl.getScdTitle());
                dspSchMdl.setGrpEdKbn(GSConstSchedule.EDIT_CONF_GROUP);
                dspSchMdl.setPublic(schMdl.getScdPublic());

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP
                    && !(belongGrpHnt)) {

                //閲覧可能な所属グループではないユーザには「予定あり」
                dspSchMdl.setTitle(textYoteiari);
                dspSchMdl.setPublic(GSConstSchedule.DSP_YOTEIARI);
            } else {
                //公開
                dspSchMdl.setTitle(schMdl.getScdTitle());
                dspSchMdl.setPublic(schMdl.getScdPublic());
            }

            dspSchMdl.setTime(getTimeString(schMdl, fromDate, toDate));
            dspSchMdl.setBgColor(schMdl.getScdBgcolor());
            dspSchMdl.setFromDate(schMdl.getScdFrDate());
            dspSchMdl.setToDate(schMdl.getScdToDate());
            dspSchMdl.setTimeKbn(schMdl.getScdDaily());
            dspSchMdl.setValueStr(schMdl.getScdValue());
            if (StringUtil.isNullZeroStringSpace(schMdl.getScdAppendUrl())) {
                //スケジュールのデータ
                dspSchMdl.setSchSid(schMdl.getScdSid());
                dspSchMdl.setUserKbn(String.valueOf(GSConstSchedule.USER_KBN_USER));
            } else {
                //スケジュール以外のプラグインのデータ
                dspSchMdl.setSchAppendUrl(schMdl.getScdAppendUrl());
                dspSchMdl.setUserKbn(schMdl.getScdAppendId());
            }
            dayMdlList.add(dspSchMdl);

        }
        dayMdl.setSchDataList(dayMdlList);
        colList.add(dayMdl);
        weekMdl.setSch010SchList(colList);
        return weekMdl;
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
     * <br>スケジュール時間表示を画面表示用に編集します
     * @param schMdl スケジュール情報
     * @param dFrDate 表示開始日時
     * @param dToDate 表示終了日時
     * @return String 画面表示用時間
     */
    public static String getTimeString(SchDataModel schMdl, UDate dFrDate, UDate dToDate) {

        StringBuilder buf = new StringBuilder();
        UDate frDate = schMdl.getScdFrDate();
        UDate toDate = schMdl.getScdToDate();
        UDate cmpToDate = null;
        if (dToDate.getIntHour() == GSConstSchedule.DAY_END_HOUR) {
            cmpToDate = dToDate.cloneUDate();
            cmpToDate.addDay(1);
            cmpToDate.setHour(GSConstSchedule.DAY_START_HOUR);
            cmpToDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
            cmpToDate.setSecond(GSConstSchedule.DAY_END_SECOND);
            cmpToDate.setMilliSecond(GSConstSchedule.DAY_END_MILLISECOND);
        } else {
            cmpToDate = dToDate.cloneUDate();
        }

        if (schMdl.getScdDaily() == GSConstSchedule.TIME_EXIST) {
            boolean flg = false;
            //スケジュール開始日時が表示範囲か判定
            if (frDate.betweenYMDHM(dFrDate, dToDate)) {
                buf.append(frDate.getStrHour());
                buf.append(":");
                buf.append(frDate.getStrMinute());
                buf.append("-");
                flg = true;
            }
            //スケジュール終了日時が表示範囲か判定
            if (toDate.betweenYMDHM(dFrDate, cmpToDate)) {
                if (flg == false) {
                    buf.append("-");
                }
                if (toDate.getIntHour() == GSConstSchedule.DAY_START_HOUR
                        && toDate.getIntMinute() == GSConstSchedule.DAY_START_MINUTES) {
                    buf.append("24");
                    buf.append(":");
                    buf.append("00");
                } else {
                    buf.append(toDate.getStrHour());
                    buf.append(":");
                    buf.append(toDate.getStrMinute());
                }

            }
        }
        log__.debug("getTimeString ==>" + buf.toString());
        return buf.toString();
    }

    /**
     * <br>ヘッダー部分へ表示する日付文字列を作成する
     * @param date 表示日付
     * @param reqMdl リクエスト情報
     * @return String 日付文字列(YYYY年MM月DD日(W))
     */
    public String getHeaderDate(UDate date, RequestModel reqMdl) {
        String ret = "";
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //年
        String textYear = gsMsg.getMessage("cmn.year", new String[] {date.getStrYear()});
        if (date != null) {
            StringBuilder buf = new StringBuilder();
            buf.append(textYear);
            buf.append(date.getStrMonth());
            buf.append(gsMsg.getMessage("cmn.month"));
            buf.append(date.getStrDay());
            buf.append(gsMsg.getMessage("cmn.day"));
            buf.append("(");
            buf.append(Sch010Biz.getStrWeek(date.getWeek(), reqMdl__));
            buf.append(")");
            ret = buf.toString();
        }
        return ret;
    }

    /**
     * <br>[機  能] 他プラグインデータを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param dspDate 画面日付
     * @param usrSid ユーザSID
     * @param schDataList スケジュールデータ
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    private void __getAppendPlgData(
            UDate dspDate,
            int usrSid,
            ArrayList <SchDataModel> schDataList,
            Connection con,
            RequestModel reqMdl) {

        //他プラグイン情報を取得
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        ArrayList<SchDataModel> apdSchList = new ArrayList<SchDataModel>();
        if (pconfig__ != null) {
            UDate prmFrDate = dspDate.cloneUDate();
            UDate prmToDate = prmFrDate.cloneUDate();
            SchAppendDataParam paramMdl = new SchAppendDataParam();
            paramMdl.setUsrSid(usrSid);
            paramMdl.setFrDate(prmFrDate);
            paramMdl.setToDate(prmToDate);
            paramMdl.setSrcId(GSConstSchedule.DSP_ID_SCH010);
            paramMdl.setDspDate(dspDate.getStrYear() + dspDate.getStrMonth() + dspDate.getStrDay());
            paramMdl.setReturnUrl(createUrl(
             dspDate.getStrYear() + dspDate.getStrMonth() + dspDate.getStrDay(), reqMdl));
            try {
                apdSchList = biz.getAppendSchData(reqMdl__, con, pconfig__, paramMdl);
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
     * @param reqMdl リクエスト情報
     * @return スレッドURL
     */
    public String createUrl(String dspDate, RequestModel reqMdl) {

        String returnUrl = null;

        //自画面のURLを作成
        String url = reqMdl__.getReferer();
        if (!StringUtil.isNullZeroString(url)) {
            returnUrl = url.substring(0, url.lastIndexOf("/"));
            returnUrl = returnUrl.substring(0, returnUrl.lastIndexOf("/"));
            returnUrl += "/schedule/sch030.do?";
            returnUrl += "sch010DspDate=" + dspDate;
            returnUrl += "&sch010DspGpSid=" + dspGpSid__;
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
     * <br>[機  能] 日間スケジュールをPDF出力します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param con コネクション
     * @param appRootPath アプリケーションルートパス
     * @param outTempDir テンポラリディレクトパス
     * @param tmpFileName テンポラリファイル名
     * @param reqMdl リクエスト情報
     * @return pdfModel SmlPdfModel
     * @throws IOException IO実行時例外
     */
    public SchNikPdfModel createSchNikPdf(
            Sch030ParamModel paramMdl,
            Connection con,
            String appRootPath,
            String outTempDir,
            String tmpFileName,
            RequestModel reqMdl)
        throws IOException {
        OutputStream oStream = null;

        //データセット処理

        //スケジュールリスト
        ArrayList<Sch010WeekOfModel> topList = paramMdl.getSch010TopList();
        ArrayList<Sch010WeekOfModel> btmList = paramMdl.getSch010BottomList();
        ArrayList<ArrayList<Sch010WeekOfModel>> allList =
                new ArrayList<ArrayList<Sch010WeekOfModel>>();
        allList.add(topList);
        allList.add(btmList);

        //表示開始時間(HH)
        int intFrom = GSConstSchedule.DAY_START_HOUR;
        //表示終了時間(HH)
        int intTo = GSConstSchedule.DAY_END_HOUR;

        if (paramMdl.getSch030FromHour() != null) {
            intFrom = Integer.parseInt(paramMdl.getSch030FromHour());
        }
        if (paramMdl.getSch030ToHour() != null) {
            intTo = Integer.parseInt(paramMdl.getSch030ToHour());
        }

        //上段表示スケジュール区分
        int intTop = 1;
        //管理者区分
        int intAdmin = paramMdl.getAdminKbn();

        //ヘッダー年月日
        String headDate = paramMdl.getSch030StrDate();
        /** タイムチャートリスト */
        ArrayList<String> timeChartList = paramMdl.getSch030TimeChartList();
        /** １時間あたりのメモリ個数*/
        String memoriCount = paramMdl.getSch030MemoriCount();

        GsMessage gsMsg = new GsMessage(reqMdl__);


        //表示グループ
        String dispGroup = new String();
        for (SchLabelValueModel schLabelValueMdl : paramMdl.getSch010GpLabelList()) {
            if (schLabelValueMdl.getValue().equals(paramMdl.getSch010DspGpSid())) {
                dispGroup = schLabelValueMdl.getLabel();
            }
        }

        //PDFモデル
        SchNikPdfModel pdfModel = new SchNikPdfModel();
        //ヘッダー年月
        pdfModel.setDspDate(headDate);
        //表示グループ
        pdfModel.setDspGroup(dispGroup);
        pdfModel.setAllList(allList);
        pdfModel.setIntFrom(intFrom);
        pdfModel.setIntTo(intTo);
        pdfModel.setIntTop(intTop);
        pdfModel.setIntAdmin(intAdmin);
        pdfModel.setTimeChartList(timeChartList);
        pdfModel.setMemoriCount(Integer.valueOf(memoriCount));


        //ファイル名
        String outBookName = pdfModel.getDspDate()
                + gsMsg.getMessage("schedule.sch030.1");
        String encOutBookName = fileNameCheck(outBookName) + ".pdf";
        pdfModel.setFileName(encOutBookName);

        try {
            IOTools.isDirCheck(outTempDir, true);
            oStream = new FileOutputStream(outTempDir + tmpFileName);
            SchNikPdfUtil pdfUtil = new SchNikPdfUtil(reqMdl__);
            pdfUtil.createSchNikkanPdf(pdfModel, appRootPath, oStream);
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
            escName = StringUtil.trimRengeString(escName, 251); //ファイル名＋拡張子=255文字以内

        return escName;
    }

}
