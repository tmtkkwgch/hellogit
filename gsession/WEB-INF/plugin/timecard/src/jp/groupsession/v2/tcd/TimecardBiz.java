package jp.groupsession.v2.tcd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.biz.LoggingBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnHolidayDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.dao.base.TcdAdmConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnHolidayModel;
import jp.groupsession.v2.cmn.model.base.CmnLogModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.dao.TcdPriConfDao;
import jp.groupsession.v2.tcd.dao.TcdPriConfModel;
import jp.groupsession.v2.tcd.dao.TcdTcdataDao;
import jp.groupsession.v2.tcd.dao.TcdTimezoneDao;
import jp.groupsession.v2.tcd.dao.TcdTimezoneModel;
import jp.groupsession.v2.tcd.excel.ExcelUtil;
import jp.groupsession.v2.tcd.excel.TimeCardXlsParametarModel;
import jp.groupsession.v2.tcd.excel.TimecardLineData;
import jp.groupsession.v2.tcd.excel.WorkReportData;
import jp.groupsession.v2.tcd.model.TcdTcdataModel;
import jp.groupsession.v2.tcd.model.TcdTotalValueModel;
import jp.groupsession.v2.tcd.pdf.TcdPdfUtil;
import jp.groupsession.v2.tcd.tcd010.Tcd010Biz;
import jp.groupsession.v2.tcd.tcd010.Tcd010Model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] タイムカードプラグインで共通使用するビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class TimecardBiz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(TimecardBiz.class);

    /** 最小時刻 */
    private static final int MINIMUM_TIME = 0;
    /** 最大時刻 */
    private static final int MAXIMUM_TIME = 2400;

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
    public TimecardBiz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public TimecardBiz() {
    }

    /**
     * <br>[機  能] タイムカード管理者設定を取得する。
     * <br>[解  説] レコードが存在しない場合、デフォルト値で作成します。
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return TcdAdmConfModel
     * @throws SQLException SQL実行時例外
     */
    public TcdAdmConfModel getTcdAdmConfModel(int usrSid, Connection con) throws SQLException {
        log__.debug("タイムカード管理者設定取得");
        TcdAdmConfDao dao = new TcdAdmConfDao(con);
        TcdAdmConfModel model = dao.select();
        if (model == null) {
            boolean commit = false;
            try {
                model = new TcdAdmConfModel(usrSid);
                dao.insert(model);
                commit = true;
            } catch (SQLException e) {
                log__.error("タイムカード管理者設定の登録に失敗しました。");
                throw e;
            } finally {
                if (commit) {
                    con.commit();
                }

            }
        }
        return model;
    }

    /**
     * <br>[機  能] タイムカード個人設定を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return TcdAdmConfModel
     * @throws SQLException SQL実行時例外
     */
    public TcdPriConfModel getTcdPriConfModel(int usrSid, Connection con) throws SQLException {

        log__.debug("タイムカード個人設定取得");
        TcdPriConfDao dao = new TcdPriConfDao(con);
        TcdPriConfModel model = dao.select(usrSid);
        if (model == null) {
            boolean commit = false;
            try {
                //デフォルト値で作成
                model = new TcdPriConfModel(usrSid);
                dao.insert(model);
                commit = true;
            } catch (SQLException e) {
                log__.error("タイムカード個人設定の登録に失敗しました。");
                throw e;
            } finally {
                if (commit) {
                    con.commit();
                }
            }
        }
        return model;
    }

    /**
     * <br>[機  能] タイムカード時間帯設定を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param order ソート有無
     * @param con コネクション
     * @return TcdAdmConfModel
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<TcdTimezoneModel> getTimeZoneModel(
            boolean order, Connection con) throws SQLException {
        log__.debug("タイムカード管理者設定取得");
        TcdTimezoneDao dao = new TcdTimezoneDao(con);
        ArrayList<TcdTimezoneModel> list = null;
        list = dao.selectOrder(order);

        return list;
    }
    /**
     * <br>[機  能] タイムカード時間帯区分のリストを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList
     */
    public ArrayList < LabelValueBean > getZoneLabelList() {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String hour = gsMsg.getMessage("cmn.time");
        String normal = gsMsg.getMessage("tcd.103");
        String zangyo = gsMsg.getMessage("tcd.tcd010.09");
        String shinya = gsMsg.getMessage("tcd.tcd010.06");
        String kyukei = gsMsg.getMessage("tcd.100");

        labelList.add(new LabelValueBean(
                      normal + hour,
                      String.valueOf(GSConstTimecard.TIMEZONE_KBN_NORMAL)));
        labelList.add(new LabelValueBean(
                      zangyo + hour,
                      String.valueOf(GSConstTimecard.TIMEZONE_KBN_ZANGYO)));
        labelList.add(new LabelValueBean(
                      shinya + hour,
                      String.valueOf(GSConstTimecard.TIMEZONE_KBN_SINYA)));
        labelList.add(new LabelValueBean(
                      kyukei + hour,
                      String.valueOf(GSConstTimecard.TIMEZONE_KBN_KYUKEI)));


        return labelList;
    }

    /**
     * <br>[機  能] 時のリストを取得する。
     * <br>[解  説] 00時～GSConstTimecard.MAX_TIMECARD_HOURまで
     * <br>[備  考]
     * @return ArrayList
     */
    public ArrayList < LabelValueBean > getHourLabelList() {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean(" ", String.valueOf(-1))); //未設定
        int hour = 0;
        while (hour <= GSConstTimecard.MAX_TIMECARD_HOUR) {
            labelList.add(new LabelValueBean(
                    StringUtil.toDecFormat(String.valueOf(hour), "00"),
                    String.valueOf(hour)));
            hour++;
        }

        return labelList;
    }

    /**
     * <br>[機  能] 時のリストを取得する
     * <br>[解  説] 00時～GSConstTimecard.MAX_TIMECARD_HOURまで
     * <br>[備  考]
     * @param maxHour 最大時刻
     * @return ArrayList
     */
    public ArrayList < LabelValueBean > getHourLabelList(int maxHour) {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean(" ", String.valueOf(-1))); //未設定
        int hour = 0;
        while (hour <= maxHour) {
            labelList.add(new LabelValueBean(
                    StringUtil.toDecFormat(String.valueOf(hour), "00"),
                    String.valueOf(hour)));
            hour++;
        }

        return labelList;
    }

    /**
     * <br>[機  能] 分のリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param admMdl 管理者設定
     * @return ArrayList
     */
    public ArrayList < LabelValueBean > getMinLabelList(TcdAdmConfModel admMdl) {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        //何分刻みか取得
        int interval = admMdl.getTacInterval();

        labelList.add(new LabelValueBean(" ", String.valueOf(-1))); //未設定
        int min = 0;
        if (interval < 60) {
            while (min < 60) {
                labelList.add(new LabelValueBean(
                        StringUtil.toDecFormat(String.valueOf(min), "00"),
                        String.valueOf(min)));
                min = min + interval;
            }
        } else {
            labelList.add(new LabelValueBean(
                    StringUtil.toDecFormat(String.valueOf(min), "00"),
                    String.valueOf(min)));
        }
        return labelList;
    }

    /**
     * <br>[機  能] 年のリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public ArrayList < LabelValueBean > getYearLabelList(Connection con) throws SQLException {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        TcdTcdataDao tcDao = new TcdTcdataDao(con);

        GsMessage gsMsg = new GsMessage();
        int year = tcDao.getMinYear();

        int maxYear = tcDao.getMaxYear();
        while (year <= maxYear) {
            String strYear = gsMsg.getMessage(
                    "cmn.year",
                    new String[] {StringUtil.toDecFormat(String.valueOf(year), "0000")});
            labelList.add(new LabelValueBean(
                    strYear,
                    String.valueOf(year)));
            year++;
        }

        return labelList;
    }

    /**
     * <br>[機  能] 月のリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList
     */
    public ArrayList < LabelValueBean > getMonthLabelList() {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String strMonth = gsMsg.getMessage("cmn.month");

        int month = 1;
        while (month <= 12) {
            labelList.add(new LabelValueBean(
                    StringUtil.toDecFormat(String.valueOf(month), "00") + strMonth,
                    String.valueOf(month)));
            month++;
        }

        return labelList;
    }

    /**
     * <br>[機  能] メイン画面からの更新・表示対象のタイムカード情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param date 日付
     * @param con コネクション
     * @return TcdTcdataModel
     * @throws SQLException SQL実行時例外
     */
    public TcdTcdataModel getTargetTcddata(int userSid, UDate date, Connection con)
    throws SQLException {
        TcdTcdataModel ret = null;
        TcdTcdataDao tcDao = new TcdTcdataDao(con);
        //指定日付から24時間以内に始業打刻され、終業打刻されていない情報を取得
        ArrayList<TcdTcdataModel> tcdList = tcDao.getFailTimecardData(userSid, date);

        //管理者設定を取得
        TimecardBiz tBiz = new TimecardBiz();
        TcdAdmConfModel admConf = tBiz.getTcdAdmConfModel(userSid, con);
        int interval = 1;
        if (admConf != null) {
            interval = admConf.getTacInterval();
        }

        for (TcdTcdataModel tcdMdl : tcdList) {
            Time inTime = TimecardBiz.adjustIntime(tcdMdl.getTcdIntime(), interval);
            if (!isOver24Hours(tcdMdl.getTcdDate(), inTime)) {
                ret = tcdMdl;
            }
        }
        if (ret == null) {
            //指定日付のタイムカード情報
            ret = tcDao.select(userSid, date.getYear(), date.getMonth(), date.getIntDay());
        }
        return ret;
    }

    /**
     * <br>[機  能] 不正データが存在するか判定します
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param con コネクション
     * @param admConf 管理者設定モデル
     * @return boolean true:存在する　false:存在しない
     * @throws SQLException SQL実行時例外
     */
    public boolean isFailDataExist(int userSid, Connection con, TcdAdmConfModel admConf)
    throws SQLException {
        UDate sysDate = new UDate();
        //当日以前にfromのみ登録しているデータが存在するかチェック
        TcdTcdataDao tcdDao = new TcdTcdataDao(con);
        ArrayList<TcdTcdataModel> tcdList = tcdDao.getFailTimecardData(userSid, sysDate);
        int interval = 1;
        if (admConf != null) {
            interval = admConf.getTacInterval();
        }

        for (TcdTcdataModel tcdMdl : tcdList) {
            Time inTime = TimecardBiz.adjustIntime(tcdMdl.getTcdIntime(), interval);

            if (isOver24Hours(tcdMdl.getTcdDate(), inTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <br>[機  能] 不正データが存在する場合、その年月日を取得する
     * <br>[解  説] 不正なデータが無い場合nullを返します。
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param con コネクション
     * @return boolean true:存在する　false:存在しない
     * @throws SQLException SQL実行時例外
     */
    public UDate getFailDataYmd(int userSid, Connection con) throws SQLException {
        UDate sysDate = new UDate();
        //当日以前にfromのみ登録しているデータが存在するかチェック
        TcdTcdataDao tcdDao = new TcdTcdataDao(con);
        ArrayList<TcdTcdataModel> tcdList = tcdDao.getFailTimecardData(userSid, sysDate);

        //管理者設定を取得
        TimecardBiz tBiz = new TimecardBiz();
        TcdAdmConfModel admConf = tBiz.getTcdAdmConfModel(userSid, con);

        int interval = 1;
        if (admConf != null) {
            interval = admConf.getTacInterval();
        }

        for (TcdTcdataModel tcdMdl : tcdList) {
            Time inTime = TimecardBiz.adjustIntime(tcdMdl.getTcdIntime(), interval);
            if (isOver24Hours(tcdMdl.getTcdDate(), inTime)) {
                return tcdMdl.getTcdDate();
            }
        }
        return null;
    }

    /**
     * <br>[機  能] 始業時間からシステム時間が24時間以上経過しているか判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param frDate 始業日
     * @param fr 始業時間
     * @return boolean true:24時間以上 false:24時間未満
     */
    public boolean isOver24Hours(UDate frDate, Time fr) {

        UDate sysDate = new UDate();
        sysDate.setSecond(0);
        sysDate.setMilliSecond(0);

        UDate tcDate = frDate.cloneUDate();
        tcDate.setHour(TimecardUtil.getTimeHour(fr));
        tcDate.setMinute(TimecardUtil.getTimeMin(fr));
        int days = UDateUtil.diffDay(sysDate, tcDate);
        if (days > 0) {
            return true;
        }
        return false;
    }

    /**
     * <br>[機  能] タイムカードエクセルを出力します。
     * <br>[解  説]
     * <br>[備  考]
     * @param parmModel タイムカード作成パラメータ
     * @throws SQLException SQL実行例外
     * @throws IOException 入出力例外
     */
    public void createTimeCardXls(TimeCardXlsParametarModel parmModel)
    throws SQLException, IOException {

        log__.debug("勤務表出力を行います。");

        OutputStream oStream = null;
        Connection con = parmModel.getCon();
        String appRootPath = parmModel.getAppRootPath();
        String outPath = parmModel.getOutTempDir();

        //基本設定取得
        TimecardBiz tmBiz = new TimecardBiz(reqMdl__);
        TcdAdmConfModel admMdl = tmBiz.getTcdAdmConfModel(parmModel.getTargetUserSid(), con);

        //時間帯設定取得
        ArrayList<TcdTimezoneModel> timeZoneList = tmBiz.getTimeZoneModel(false, con);

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String strPlace = gsMsg.getMessage("tcd.107");

        //作業場所
        String place = strPlace;

        //ユーザ
        int userSid = parmModel.getTargetUserSid();
        CmnUsrmInfDao uinfDao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel uinfModel = uinfDao.select(userSid);
        String name = uinfModel.getUsiSei() + "　" + uinfModel.getUsiMei();

        //対象年
        int year = parmModel.getTargetYear();
        //対象月
        int month = parmModel.getTargetMonth();

        //sheetデータ
        WorkReportData wReportData = new WorkReportData();
        //作業場所
        wReportData.setPlace(place);
        //名前
        wReportData.setUserName(name);
        //該当年
        wReportData.setYear(year);
        //該当月
        wReportData.setMonth(month);

        //
        Tcd010Biz tcd010Biz = new Tcd010Biz();
        //基準:稼働日数・稼働時間
        BigDecimal kadoBaseDays = BigDecimal.ZERO;  //稼動基準日数
        BigDecimal kadoBaseHours = BigDecimal.ZERO; //稼動基準時間数
        BigDecimal baseDayOfHour = BigDecimal.ZERO; //稼動基準時間数/日
        UDate fdate = new UDate();
        UDate tdate = tcd010Biz.setTimeCardCal(year, month, admMdl.getTacSimebi(), fdate);
//      休日情報取得
        CmnHolidayDao holDao = new CmnHolidayDao(con);
        HashMap<String, CmnHolidayModel> holMap = holDao.getHoliDayListFotTcd(fdate, tdate);
        kadoBaseDays = tcd010Biz.getKadoBaseDays(fdate, tdate, admMdl, holMap);
        kadoBaseHours = tcd010Biz.getKadoBaseHours(kadoBaseDays, admMdl, timeZoneList);
        if (kadoBaseDays != null && !kadoBaseDays.equals(BigDecimal.ZERO)) {
            BigDecimal minBd = convertHourToMinBigDecimal(admMdl, kadoBaseHours);
            minBd = minBd.divide(kadoBaseDays, 0, BigDecimal.ROUND_HALF_UP);
            baseDayOfHour = convertMinToHourBigDecimal(admMdl, minBd);
        }
        //通常時間帯取得
        TcdTimezoneDao tzDao = new TcdTimezoneDao(con);
        Time normalFrTime = tzDao.getFrTimeMin(GSConstTimecard.TIMEZONE_KBN_NORMAL);
        Time normalToTime = tzDao.getToTimeMax(GSConstTimecard.TIMEZONE_KBN_NORMAL);

        //基準稼働時間
        wReportData.setBaseHour(baseDayOfHour.toString());
        //基準稼働日数
        wReportData.setBaseDay(kadoBaseDays.toString());

        List<Tcd010Model> tcdList = parmModel.getTimeCardInfoList();

        //1ヶ月分の勤務表データを保持するリスト
        List<TimecardLineData> reportDataList =
            Collections.synchronizedList(new ArrayList<TimecardLineData>());

        //1行分のデータを保持するモデル
        TimecardLineData reportData = null;

        //lineデータ作成（１ヶ月分のデータ）
        for (Tcd010Model tcdModel : tcdList) { //テストデータは１件のみ
            reportData = new TimecardLineData();

            UDate date = new UDate();
            if (!(tcdModel.getTcd010Ymd() == null)) {
                date.setDate(tcdModel.getTcd010Ymd());
            }

            //休日丸め処理（週末、祝日）
            int holiday = tcdModel.getHolKbn();
            if (date.getWeek() == UDate.SATURDAY || date.getWeek() == UDate.SUNDAY) {
                holiday = 1;
            }
            String strikeStartTime = tcdModel.getTcd010StrikeShigyouStr();
            String strikeEndTime = tcdModel.getTcd010StrikeSyugyouStr();
            String startTime = tcdModel.getTcd010ShigyouStr();
            String endTime = tcdModel.getTcd010SyugyouStr();

            //稼働時間
            String utilTime = __getKadoHoursString(tcdModel, admMdl, timeZoneList);
            //残業時間
            String overTime = __getZangyoHoursString(tcdModel, admMdl, timeZoneList);
            //深夜残業時間
            String nightOverTime = __getSinyaHours(tcdModel, admMdl, timeZoneList);
            //備考
            String bikou = tcdModel.getTcd010Bikou();

            //遅刻
//            String tikoku = __getChikokuDays(tcdModel);
            String tikoku = __getChikokuTimes(tcdModel, admMdl, timeZoneList,
                                            normalFrTime, normalToTime);
            //早退
//            String soutai = __getSoutaiDays(tcdModel);
            String soutai = __getSoutaiTimes(tcdModel, admMdl, timeZoneList,
                                            normalFrTime, normalToTime);
            //代休
            String daikyu = __getKekkinDays(tcdModel, GSConstTimecard.HOL_KBN_DAIKYU);
            //有給
            String yuukyu = __getYukyuDays(tcdModel);
            //欠勤
            String kekkin = __getKekkinDays(tcdModel, GSConstTimecard.HOL_KBN_KEKKIN);

            log__.debug("------------------------------------");
            log__.debug(">>>holiday" + holiday);
            log__.debug(">>>strikeStartTime" + strikeStartTime);
            log__.debug(">>>strikeEndTime" + strikeEndTime);
            log__.debug(">>>startTime" + startTime);
            log__.debug(">>>endTime" + endTime);
            log__.debug(">>>overTime" + overTime);
            log__.debug(">>>nightOverTime" + nightOverTime);
            log__.debug(">>>bikou" + bikou);
            log__.debug("------------------------------------");
            //日付
            reportData.setDate(date);
            //休日フラグ
            reportData.setHoliday(holiday);
            //打刻開始時間
            reportData.setStrikeStartTime(strikeStartTime);
            //打刻終了稼働時間
            reportData.setStrikeEndTime(strikeEndTime);
            //開始時間
            reportData.setStartTime(startTime);
            //終了稼働時間
            reportData.setEndTime(endTime);
            //稼働時間
            reportData.setUtilTime(utilTime);
            //残業時間
            reportData.setOverTime(overTime);
            //深夜残業時間
            reportData.setNightOverTime(nightOverTime);
            //遅刻
            reportData.setTikoku(tikoku);
            //早退
            reportData.setSoutai(soutai);
            //代休
            reportData.setDaikyu(daikyu);
            //有給
            reportData.setYuukyu(yuukyu);
            //欠勤
            reportData.setKekkin(kekkin);
            //備考
            reportData.setBikou(bikou);

            reportDataList.add(reportData);
        }

        //行データ
        wReportData.setLineDataList(reportDataList);
        //行データ
        wReportData.setLineDataList(reportDataList);


        try {
            IOTools.isDirCheck(outPath, true);
            String bookName = parmModel.getOutBookTmpName();
            oStream = new FileOutputStream(outPath + bookName);
            ExcelUtil eUtil = new ExcelUtil(reqMdl__);
            eUtil.createWorkReport(appRootPath, oStream, wReportData, admMdl);
        } catch (Exception e) {
            log__.error("エ勤務表出力に失敗しました。", e);
        } finally {
            oStream.flush();
            oStream.close();
        }
        log__.debug("勤務表出力を終了します。");
    }
    /**
     * <br>[機  能] タイムカードエクセルを出力します。
     * <br>[解  説]
     * <br>[備  考]
     * @param parmModel タイムカード作成パラメータ
     * @throws SQLException SQL実行例外
     * @throws IOException 入出力例外
     */
    public void createTimeCardPdf(TimeCardXlsParametarModel parmModel)
    throws SQLException, IOException {

        log__.debug("勤務表PDF出力を行います。");

        OutputStream oStream = null;
        Connection con = parmModel.getCon();
        String appRootPath = parmModel.getAppRootPath();
        String outPath = parmModel.getOutTempDir();

        //基本設定取得
        TimecardBiz tmBiz = new TimecardBiz(reqMdl__);
        TcdAdmConfModel admMdl = tmBiz.getTcdAdmConfModel(parmModel.getTargetUserSid(), con);
        //時間帯設定取得
        ArrayList<TcdTimezoneModel> timeZoneList = tmBiz.getTimeZoneModel(false, con);
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String strPlace = gsMsg.getMessage("tcd.107");
        //作業場所
        String place = strPlace;
        //ユーザ
        int userSid = parmModel.getTargetUserSid();
        CmnUsrmInfDao uinfDao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel uinfModel = uinfDao.select(userSid);
        String name = uinfModel.getUsiSei() + "　" + uinfModel.getUsiMei();

        //対象年
        int year = parmModel.getTargetYear();
        //対象月
        int month = parmModel.getTargetMonth();

        //PDFへ出力するデータをWorkReportDataに設定
        WorkReportData wReportData = new WorkReportData();
        //作業場所
        wReportData.setPlace(place);
        //名前
        wReportData.setUserName(name);
        //該当年
        wReportData.setYear(year);
        //該当月
        wReportData.setMonth(month);
        //
        Tcd010Biz tcd010Biz = new Tcd010Biz();
        //基準:稼働日数・稼働時間
        BigDecimal kadoBaseDays = BigDecimal.ZERO;  //稼動基準日数
        BigDecimal kadoBaseHours = BigDecimal.ZERO; //稼動基準時間数
        BigDecimal baseDayOfHour = BigDecimal.ZERO; //稼動基準時間数/日
        UDate fdate = new UDate();
        UDate tdate = tcd010Biz.setTimeCardCal(year, month, admMdl.getTacSimebi(), fdate);
        //休日情報取得
        CmnHolidayDao holDao = new CmnHolidayDao(con);
        HashMap<String, CmnHolidayModel> holMap = holDao.getHoliDayListFotTcd(fdate, tdate);
        //通常時間帯取得
        TcdTimezoneDao tzDao = new TcdTimezoneDao(con);
        Time normalFrTime = tzDao.getFrTimeMin(GSConstTimecard.TIMEZONE_KBN_NORMAL);
        Time normalToTime = tzDao.getToTimeMax(GSConstTimecard.TIMEZONE_KBN_NORMAL);
        kadoBaseDays = tcd010Biz.getKadoBaseDays(fdate, tdate, admMdl, holMap);
        kadoBaseHours = tcd010Biz.getKadoBaseHours(kadoBaseDays, admMdl, timeZoneList);
        if (kadoBaseDays != null && !kadoBaseDays.equals(BigDecimal.ZERO)) {
            BigDecimal minBd = convertHourToMinBigDecimal(admMdl, kadoBaseHours);
            minBd = minBd.divide(kadoBaseDays, 2, BigDecimal.ROUND_HALF_UP);
            baseDayOfHour = convertMinToHourBigDecimal(admMdl, minBd);
        }
        //基準稼働時間
        wReportData.setBaseHour(baseDayOfHour.toString());
        //基準稼働日数
        wReportData.setBaseDay(kadoBaseDays.toString());

        List<Tcd010Model> tcdList = parmModel.getTimeCardInfoList();

        //1ヶ月分の勤務表データを保持するリスト
        List<TimecardLineData> reportDataList =
            Collections.synchronizedList(new ArrayList<TimecardLineData>());

        //1行分のデータを保持するモデル
        TimecardLineData reportData = null;

        //lineデータ作成（１ヶ月分のデータ）
        for (Tcd010Model tcdModel : tcdList) { //テストデータは１件のみ
            reportData = new TimecardLineData();

            UDate date = new UDate();
            if (!(tcdModel.getTcd010Ymd() == null)) {
                date.setDate(tcdModel.getTcd010Ymd());
            }

            //休日丸め処理（週末、祝日）
            int holiday = tcdModel.getHolKbn();
            if (date.getWeek() == UDate.SATURDAY || date.getWeek() == UDate.SUNDAY) {
                holiday = 1;
            }
            String strikeStartTime = tcdModel.getTcd010StrikeShigyouStr();
            String strikeEndTime = tcdModel.getTcd010StrikeSyugyouStr();
            String startTime = tcdModel.getTcd010ShigyouStr();
            String endTime = tcdModel.getTcd010SyugyouStr();

            //稼働時間
//            String utilTime = StringUtil.toCommaUnderZeroTrim(
            String utilTime = StringUtil.toCommaFormat(
                    __getKadoHoursString(tcdModel, admMdl, timeZoneList));
            //残業時間
            String overTime = StringUtil.toCommaFormat(
                    __getZangyoHoursString(tcdModel, admMdl, timeZoneList));
            //深夜残業時間
            String nightOverTime = StringUtil.toCommaFormat(
                    __getSinyaHours(tcdModel, admMdl, timeZoneList));
            //備考
            String bikou = tcdModel.getTcd010Bikou();

            //遅刻
//            String tikoku = StringUtil.toCommaFormat(__getChikokuDays(tcdModel));
            String tikoku = __getChikokuTimes(tcdModel, admMdl, timeZoneList,
                                            normalFrTime, normalToTime);
            //早退
//            String soutai = StringUtil.toCommaFormat(__getSoutaiDays(tcdModel));
            String soutai = __getSoutaiTimes(tcdModel, admMdl, timeZoneList,
                                            normalFrTime, normalToTime);
            //代休
            String daikyu = StringUtil.toCommaFormat(
                    __getKekkinDays(tcdModel, GSConstTimecard.HOL_KBN_DAIKYU));
            //有給
            String yuukyu = StringUtil.toCommaFormat(
                    __getYukyuDays(tcdModel));
            //欠勤
            String kekkin = StringUtil.toCommaFormat(
                    __getKekkinDays(tcdModel, GSConstTimecard.HOL_KBN_KEKKIN));

            //日付
            reportData.setDate(date);
            //休日フラグ
            reportData.setHoliday(holiday);
            //打刻開始時間
            reportData.setStrikeStartTime(strikeStartTime);
            //打刻終了稼働時間
            reportData.setStrikeEndTime(strikeEndTime);
            //開始時間
            reportData.setStartTime(startTime);
            //終了稼働時間
            reportData.setEndTime(endTime);
            //稼働時間
            reportData.setUtilTime(utilTime);
            //残業時間
            reportData.setOverTime(overTime);
            //深夜残業時間
            reportData.setNightOverTime(nightOverTime);
            //遅刻
            reportData.setTikoku(tikoku);
            //早退
            reportData.setSoutai(soutai);
            //代休
            reportData.setDaikyu(daikyu);
            //有給
            reportData.setYuukyu(yuukyu);
            //欠勤
            reportData.setKekkin(kekkin);
            //備考
            reportData.setBikou(bikou);

            reportDataList.add(reportData);
        }

        //行データ
        wReportData.setLineDataList(reportDataList);

        TcdTotalValueModel total
        = tcd010Biz.getTotalValueModel(
                userSid, fdate, tdate
                , reqMdl__.getSmodel().getUsrsid(), con, reqMdl__);

        wReportData.setSumBaseHour(StringUtil.toCommaFromBigDecimal(kadoBaseHours));

        wReportData.setSumUtil(total.getKadoHoursStr());
        if (total.getZangyoDays().compareTo(BigDecimal.ZERO) > 0) {
            wReportData.setSumOver(total.getZangyoHoursStr());
        } else {
            wReportData.setSumOver(" ");
        }
        if (total.getSinyaDays().compareTo(BigDecimal.ZERO) > 0) {
            wReportData.setSumNight(total.getSinyaHoursStr());
        } else {
            wReportData.setSumNight(" ");
        }
        if (total.getChikokuTimes().compareTo(BigDecimal.ZERO) > 0) {
            wReportData.setSumTikoku(total.getChikokuTimesStr());
        } else {
            wReportData.setSumTikoku(" ");
        }
        if (total.getSoutaiTimes().compareTo(BigDecimal.ZERO) > 0) {
            wReportData.setSumSoutai(total.getSoutaiTimesStr());
        } else {
            wReportData.setSumSoutai(" ");
        }
        try {
            IOTools.isDirCheck(outPath, true);
            String bookName = parmModel.getOutBookTmpName();
            oStream = new FileOutputStream(outPath + bookName);
            TcdPdfUtil eUtil = new TcdPdfUtil(reqMdl__);
            eUtil.createWorkReport(appRootPath, oStream, wReportData);
        } catch (Exception e) {
            log__.error("エ勤務表出力に失敗しました。", e);
        } finally {
            oStream.flush();
            oStream.close();
        }
        log__.debug("勤務表出力を終了します。");
    }
    /**
     * <br>[機  能] 稼動実績時間数を計算し文字列を返却します
     * <br>[解  説]
     * <br>[備  考]
     * @param model タイムカード情報
     * @param admMdl 設定情報
     * @param timeZoneList 時間帯情報
     * @return BigDecimal 稼動実績時間数
     */
    private String __getKadoHoursString(
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            ArrayList<TcdTimezoneModel> timeZoneList) {

        //集計用
        BigDecimal kadoHours = BigDecimal.ZERO;     //稼動実績時間数

        if (model.getTcdOuttime() != null) {
            UDate frDate = new UDate();
            frDate.setZeroHhMmSs();
            frDate.setMilliSecond(0);
            UDate toDate = frDate.cloneUDate();
            Time frTime = model.getTcdIntime();
            Time toTime = model.getTcdOuttime();
            frDate.setHour(TimecardUtil.getTimeHour(frTime));
            frDate.setMinute(TimecardUtil.getTimeMin(frTime));
            toDate.setHour(TimecardUtil.getTimeHour(toTime));
            toDate.setMinute(TimecardUtil.getTimeMin(toTime));

            if (frDate.getTimeMillis() > toDate.getTimeMillis()) {
                toDate.addDay(1);
            }

            UDate cvFrDate = TimecardBiz.convertForDspTime(frDate, admMdl, true);
            UDate cvToDate = TimecardBiz.convertForDspTime(toDate, admMdl, false);

            //始業時間 > 終了時間の場合、計算を行わない
            if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {

                //終了が0:00以降の場合、計算用日付(cvToDateCl)の日を+1
                //cvToDateはそのまま
                UDate cvToDateCl = cvToDate.cloneUDate();
//            if (cvFrDate.getTimeMillis() > cvToDateCl.getTimeMillis()) {
//                cvToDateCl.addDay(1);
//            }

                long min = UDateUtil.diffMinute(cvFrDate, cvToDateCl);
                log__.debug(">>>cvFrDate =" + cvFrDate.toSQLTimestamp());
                log__.debug(">>>cvToDate =" + cvToDate.toSQLTimestamp());
                long submin = __getRepetitionTimeZoneMinute(
                        cvFrDate, cvToDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_KYUKEI);

                kadoHours = kadoHours.add(BigDecimal.valueOf(min - submin));
            }
        }
        return StringUtil.toCommaFromBigDecimal(convertMinToHourBigDecimal(admMdl, kadoHours));
    }

    /**
     * <br>[機  能] 残業時間を計算し文字列を返却します
     * <br>[解  説]
     * <br>[備  考]
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @return 残業時間
     */
    private String __getZangyoHoursString(
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            ArrayList<TcdTimezoneModel> timeZoneList) {

        //集計用
        BigDecimal zangyoHours = BigDecimal.ZERO;     //残業時間数

        if (model.getTcdOuttime() != null) {
            UDate frDate = new UDate();
            frDate.setZeroHhMmSs();
            frDate.setMilliSecond(0);
            UDate toDate = frDate.cloneUDate();
            Time frTime = model.getTcdIntime();
            Time toTime = model.getTcdOuttime();
            frDate.setHour(TimecardUtil.getTimeHour(frTime));
            frDate.setMinute(TimecardUtil.getTimeMin(frTime));
            toDate.setHour(TimecardUtil.getTimeHour(toTime));
            toDate.setMinute(TimecardUtil.getTimeMin(toTime));

            if (frDate.getTimeMillis() > toDate.getTimeMillis()) {
                toDate.addDay(1);
            }

            UDate cvFrDate = TimecardBiz.convertForDspTime(frDate, admMdl, true);
            UDate cvToDate = TimecardBiz.convertForDspTime(toDate, admMdl, false);

            //始業時間 > 終了時間の場合、計算を行わない
            if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {

                long zanmin = __getRepetitionTimeZoneMinute(
                        cvFrDate, cvToDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_ZANGYO);
                long sinyamin = __getRepetitionTimeZoneMinute(
                        cvFrDate, cvToDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_SINYA);
                zangyoHours = zangyoHours.add(BigDecimal.valueOf(zanmin + sinyamin));
            }
        }
        return StringUtil.toCommaFromBigDecimal(convertMinToHourBigDecimal(admMdl, zangyoHours));
    }

    /**
     * <br>[機  能] 深夜実績時間を計算し文字列を返却します
     * <br>[解  説]
     * <br>[備  考]
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @return 深夜実績時間
     */
    private String __getSinyaHours(
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            ArrayList<TcdTimezoneModel> timeZoneList) {

        //集計用
        BigDecimal sinyaHours = BigDecimal.ZERO;     //深夜残業時間数

        if (model.getTcdOuttime() != null) {
            UDate frDate = new UDate();
            frDate.setZeroHhMmSs();
            frDate.setMilliSecond(0);
            UDate toDate = frDate.cloneUDate();
            Time frTime = model.getTcdIntime();
            Time toTime = model.getTcdOuttime();
            frDate.setHour(TimecardUtil.getTimeHour(frTime));
            frDate.setMinute(TimecardUtil.getTimeMin(frTime));
            toDate.setHour(TimecardUtil.getTimeHour(toTime));
            toDate.setMinute(TimecardUtil.getTimeMin(toTime));

            if (frDate.getTimeMillis() > toDate.getTimeMillis()) {
                toDate.addDay(1);
            }

            UDate cvFrDate = TimecardBiz.convertForDspTime(frDate, admMdl, true);
            UDate cvToDate = TimecardBiz.convertForDspTime(toDate, admMdl, false);

            //始業時間 > 終了時間の場合、計算を行わない
            if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {
                long sinyamin = __getRepetitionTimeZoneMinute(
                        cvFrDate, cvToDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_SINYA);

                sinyaHours = sinyaHours.add(BigDecimal.valueOf(sinyamin));
            }
        }

        return StringUtil.toCommaFromBigDecimal(convertMinToHourBigDecimal(admMdl, sinyaHours));
    }

    /**
     * <br>[機  能] 欠勤日数ををString形で返却します
     * <br>[解  説]
     * <br>[備  考]
     * @param model タイムカード情報
     * @param kbn 集計する欠勤区分
     * @return 欠勤日数
     */
    private String __getKekkinDays(Tcd010Model model, int kbn) {
        BigDecimal kekkinDays = BigDecimal.ZERO;
        if (model.getTcdHolkbn() == kbn) {
            BigDecimal days = BigDecimal.valueOf(0);
            if (model.getTcdHolcnt() != null) {
                days = model.getTcdHolcnt();
            }
            kekkinDays = kekkinDays.add(days);
        }
        return StringUtil.toCommaFromBigDecimal(kekkinDays);
    }

    /**
     * <br>[機  能] 有給として扱う日数をString形で返却します（慶弔含む）
     * <br>[解  説]
     * <br>[備  考]
     * @param model タイムカード情報
     * @return 欠勤日数
     */
    private String __getYukyuDays(Tcd010Model model) {
        BigDecimal yukyuDays = BigDecimal.ZERO;
        if (model.getTcdHolkbn() == GSConstTimecard.HOL_KBN_KEITYO) {
            BigDecimal days = BigDecimal.valueOf(0);
            if (model.getTcdHolcnt() != null) {
                days = model.getTcdHolcnt();
            }
            yukyuDays = yukyuDays.add(days);
        }
        if (model.getTcdHolkbn() == GSConstTimecard.HOL_KBN_YUUKYU) {
            BigDecimal days = BigDecimal.valueOf(0);
            if (model.getTcdHolcnt() != null) {
                days = model.getTcdHolcnt();
            }
            yukyuDays = yukyuDays.add(days);
        }

        return StringUtil.toCommaFromBigDecimal(yukyuDays);
    }

//    /**
//     * <br>[機  能] 遅刻日数ををString形で返却します
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param model タイムカード情報
//     * @return 遅刻実績日数
//     */
//    private String __getChikokuDays(Tcd010Model model) {
//
//        BigDecimal chikokuDays = BigDecimal.ZERO.divide(
//                BigDecimal.ONE, 1, BigDecimal.ROUND_HALF_UP);
//
//        BigDecimal day = BigDecimal.valueOf(1);
//        if (model.getTcdChkkbn() == GSConstTimecard.CHK_KBN_SELECT) {
//            chikokuDays = chikokuDays.add(day);
//        }
//        return StringUtil.toCommaFromBigDecimal(chikokuDays);
//    }

    /**
     * <br>[機  能] 遅刻時間を取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @param normalFrTime 通常時間帯開始時刻
     * @param normalToTime 通常時間帯終了時刻
     * @return BigDecimal 遅刻実績時間
     */
    public BigDecimal getChikokuTime(Tcd010Model model, TcdAdmConfModel admMdl,
                                        ArrayList<TcdTimezoneModel> timeZoneList,
                                        Time normalFrTime, Time normalToTime) {

        BigDecimal chikokuTime = null;
        if (model.getTcdChkkbn() == GSConstTimecard.CHK_KBN_SELECT
        && normalFrTime != null
        && model.getTcdIntime() != null) {

            UDate frDate = new UDate();
            frDate.setZeroHhMmSs();
            UDate normalToDate = frDate.cloneUDate();

            UDate toDate = frDate.cloneUDate();
            Time toTime = model.getTcdIntime();
            if (admMdl != null) {
                toTime = TimecardBiz.adjustIntime(toTime, admMdl.getTacInterval());
            }

            frDate.setHour(TimecardUtil.getTimeHour(normalFrTime));
            frDate.setMinute(TimecardUtil.getTimeMin(normalFrTime));
            toDate.setHour(TimecardUtil.getTimeHour(toTime));
            toDate.setMinute(TimecardUtil.getTimeMin(toTime));

            //始業時刻 > 通常時間帯終了時刻の場合、始業時刻 = 通常時間帯終了時刻とする
            //(遅刻時間は通常時間帯で計算する)
            if (normalToTime != null) {
                normalToDate.setHour(TimecardUtil.getTimeHour(normalToTime));
                normalToDate.setMinute(TimecardUtil.getTimeMin(normalToTime));
                if (normalToDate.compareDateYMDHM(frDate) == UDate.LARGE) {
                    normalToDate.addDay(1);
                }

                if (normalToDate.compareDateYMDHM(toDate) == UDate.LARGE) {
                    toDate = normalToDate.cloneUDate();
                }
            }

            if (toDate.compareDateYMDHM(frDate) == UDate.SMALL) {
                UDate cvFrDate = TimecardBiz.convertForDspTime(frDate, admMdl, true);
                UDate cvToDate = TimecardBiz.convertForDspTime(toDate, admMdl, false);

                //通常時間帯開始時刻 > 始業時間の場合、計算を行わない
                if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {
                    long min = UDateUtil.diffMinute(cvFrDate, cvToDate);
                    long submin = __getRepetitionTimeZoneMinute(
                            cvFrDate, cvToDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_KYUKEI);
                    chikokuTime = BigDecimal.valueOf(min - submin);
                }

            }
        }
        return chikokuTime;
    }

    /**
     * <br>[機  能] 遅刻時間(文字列)を取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @param normalFrTime 通常時間帯開始時刻
     * @param normalToTime 通常時間帯終了時刻
     * @return 遅刻時間
     */
    public String __getChikokuTimes(Tcd010Model model, TcdAdmConfModel admMdl,
                                        ArrayList<TcdTimezoneModel> timeZoneList,
                                        Time normalFrTime, Time normalToTime) {

        BigDecimal chikokuTime = getChikokuTime(model, admMdl, timeZoneList,
                                                normalFrTime, normalToTime);
        if (chikokuTime == null) {
            chikokuTime = BigDecimal.ZERO;
        } else {
            chikokuTime = TimecardBiz.convertMinToHourBigDecimal(admMdl, chikokuTime);
        }

        return StringUtil.toCommaFromBigDecimal(chikokuTime);
    }

//    /**
//     * <br>[機  能] 早退日数をString形で返却します
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param model タイムカード情報
//     * @return 早退実績日数
//     */
//    private String __getSoutaiDays(Tcd010Model model) {
//        BigDecimal soutaiDays = BigDecimal.ZERO.divide(
//                BigDecimal.ONE, 1, BigDecimal.ROUND_HALF_UP);
//
//        BigDecimal day = BigDecimal.valueOf(1);
//        if (model.getTcdSoukbn() == GSConstTimecard.SOU_KBN_SELECT) {
//            soutaiDays = soutaiDays.add(day);
//        }
//        return StringUtil.toCommaFromBigDecimal(soutaiDays);
//    }

    /**
     * <br>[機  能] 早退時間を取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @param normalFrTime 通常時間帯開始時刻
     * @param normalToTime 通常時間帯終了時刻
     * @return BigDecimal 早退実績時間
     */
    public BigDecimal getSoutaiTime(Tcd010Model model, TcdAdmConfModel admMdl,
                                    ArrayList<TcdTimezoneModel> timeZoneList,
                                    Time normalFrTime, Time normalToTime) {

        BigDecimal soutaiTime = null;
        if (model.getTcdSoukbn() == GSConstTimecard.SOU_KBN_SELECT
        && normalFrTime != null
        && normalToTime != null
        && model.getTcdOuttime() != null
        && model.getTcdOuttime() != null) {

            UDate frDate = new UDate();
            frDate.setZeroHhMmSs();
            UDate toDate = frDate.cloneUDate();
            UDate normalFrDate = frDate.cloneUDate();
            UDate normalToDate = frDate.cloneUDate();

            Time frTime = model.getTcdIntime();
            if (admMdl != null) {
                frTime = TimecardBiz.adjustIntime(frTime, admMdl.getTacInterval());
            }
            Time toTime = model.getTcdOuttime();
            if (admMdl != null) {
                toTime = TimecardBiz.adjustOuttime(toTime, admMdl.getTacInterval());
            }

            TimecardUtil.setTime(frDate, frTime);
            TimecardUtil.setTime(toDate, toTime);
            TimecardUtil.setTime(normalFrDate, normalFrTime);
            TimecardUtil.setTime(normalToDate, normalToTime);

            if (toDate.compareDateYMDHM(frDate) == UDate.LARGE) {
                toDate.addDay(1);
            }
            if (normalToDate.compareDateYMDHM(normalFrDate) == UDate.LARGE) {
                normalToDate.addDay(1);
            }

            if (normalFrDate.compareDateYMDHM(toDate) == UDate.LARGE
            && normalToDate.compareDateYMDHM(toDate) == UDate.SMALL) {
                UDate cvFrDate = TimecardBiz.convertForDspTime(toDate, admMdl, true);
                UDate cvToDate = TimecardBiz.convertForDspTime(normalToDate, admMdl, false);

                //通常時間帯終了時刻 < 終業時間の場合、計算を行わない
                if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {
                    long min = UDateUtil.diffMinute(cvFrDate, cvToDate);
                    long submin = __getRepetitionTimeZoneMinute(
                            cvFrDate, cvToDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_KYUKEI);
                    soutaiTime = BigDecimal.valueOf(min - submin);
                }

            }
        }
        return soutaiTime;
    }

    /**
     * <br>[機  能] 早退時間(文字列)を取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @param normalFrTime 通常時間帯開始時刻
     * @param normalToTime 通常時間帯終了時刻
     * @return 早退実績時間
     */
    public String __getSoutaiTimes(Tcd010Model model, TcdAdmConfModel admMdl,
                                    ArrayList<TcdTimezoneModel> timeZoneList,
                                    Time normalFrTime, Time normalToTime) {

        BigDecimal soutaiTime = getSoutaiTime(model, admMdl, timeZoneList,
                                                normalFrTime, normalToTime);
        if (soutaiTime == null) {
            soutaiTime = BigDecimal.ZERO;
        } else {
            soutaiTime = TimecardBiz.convertMinToHourBigDecimal(admMdl, soutaiTime);
        }

        return StringUtil.toCommaFromBigDecimal(soutaiTime);
    }

    /**
     * <br>[機  能] 時間帯情報から指定した時間帯と重複する時間数を分単位で取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param frDate 開始時間
     * @param toDate 終了時間
     * @param timeZoneList 時間帯情報
     * @param zoneKbn 取得する時間帯の区分
     * @return long 分
     */
    private long __getRepetitionTimeZoneMinute(
            UDate frDate,
            UDate toDate,
            ArrayList<TcdTimezoneModel> timeZoneList,
            int zoneKbn) {

        long ret = 0;
        TcdTimezoneModel tzMdl = null;
        for (int i = 0; i < timeZoneList.size(); i++) {
            tzMdl = timeZoneList.get(i);
            if (tzMdl.getTtzKbn() == zoneKbn) {
                // 重複時間
                Time frTz = tzMdl.getTtzFrtime();
                Time toTz = tzMdl.getTtzTotime();
                int tzFrHm = TimecardUtil.getTimeHour(frTz) * 100 + TimecardUtil.getTimeMin(frTz);
                int tzToHm = TimecardUtil.getTimeHour(toTz) * 100 + TimecardUtil.getTimeMin(toTz);
                if (tzToHm < tzFrHm) {
                    tzToHm += 2400;
                }

                int[] frHm;
                int[] toHm;
                if (toDate.getIntHour() * 100 + toDate.getIntMinute()
                  < frDate.getIntHour() * 100 + frDate.getIntMinute()) {
                    frHm = new int[2];
                    toHm = new int[2];
                    frHm[0] = frDate.getIntHour() * 100 + frDate.getIntMinute();
                    toHm[0] = MAXIMUM_TIME;
                    frHm[1] = MINIMUM_TIME;
                    toHm[1] = toDate.getIntHour() * 100 + toDate.getIntMinute();
                } else {
                    frHm = new int[1];
                    toHm = new int[1];
                    frHm[0] = frDate.getIntHour() * 100 + frDate.getIntMinute();
                    toHm[0] = toDate.getIntHour() * 100 + toDate.getIntMinute();
                }
                for (int j = 0; j < frHm.length; j++) {

                    if ((frHm[j] < tzToHm) && (toHm[j] > tzFrHm)) {
                        // 開始時間
                        int nStart = frHm[j];
                        if (nStart < tzFrHm) {
                            nStart = tzFrHm;
                        }
                        // 終了時間
                        int nStop = toHm[j];
                        if (nStop > tzToHm) {
                            nStop = tzToHm;
                        }

                        ret += changeTime(nStart, nStop);
                    }

                }
            }

        }

        return ret;
    }

    /**
     * <br>[機  能] 開始と終了時間の時差を分換算します。
     * <br>[解  説]
     * <br>[備  考]
     * @param startTime 開始
     * @param stopTime 終了
     * @return int 分
     */
    public int changeTime(int startTime, int stopTime) {
        int start = startTime;
        int stop = stopTime;
        if (start > stop) {
            stop += MAXIMUM_TIME;
        }
        return ((stop / 100) * 60 + (stop % 100)) - ((start / 100) * 60 + (start % 100));
    }

    /**
     * <br>[機  能] 指定時間に当てはまるかをチェックする
     * <br>[解  説]
     * <br>[備  考]
     * @param from 開始時間
     * @param to 終了時間
     * @param target チェック対象時間
     * @return 現在の日付が範囲内ならtrue
     */
    public static boolean isRange(final UDate from, final UDate to, final UDate target) {

        if (from == null || to == null || target == null) {
            return false;
        }
        long fromNum = from.getTime();
        long toNum = to.getTime();
        long tarNum = target.getTime();
        return fromNum <= tarNum && tarNum <= toNum;
    }

    /**
     * <br>[機  能] 始業時間から遅刻か判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param frTime 始業時刻
     * @param con コネクション
     * @return boolean true:遅刻 false:遅刻していない
     * @throws SQLException SQL実行時例外
     */
    public boolean isChikoku(Time frTime, Connection con) throws SQLException {

        if (frTime == null) {
            return false;
        }
        //通常時間帯を取得
        TcdTimezoneDao tzDao = new TcdTimezoneDao(con);
        Time time = tzDao.getFrTimeMin(GSConstTimecard.TIMEZONE_KBN_NORMAL);
        if (time == null) {
            return false;
        }
        log__.debug("time.compareTo(frTime==>" + time.compareTo(frTime));
        if (time.compareTo(frTime) == UDate.LARGE) {
            return true;
        }

        return false;
    }

    /**
     * <br>[機  能] 終業時間から早退か判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param toTime 終業時刻
     * @param con コネクション
     * @return boolean true:24時間以上 false:24時間未満
     * @throws SQLException SQL実行時例外
     */
    public boolean isSoutai(Time toTime, Connection con) throws SQLException {
        if (toTime == null) {
            return false;
        }
        //通常時間帯を取得
        TcdTimezoneDao tzDao = new TcdTimezoneDao(con);
        Time time = tzDao.getToTimeMax(GSConstTimecard.TIMEZONE_KBN_NORMAL);
        if (time == null) {
            return false;
        }
        log__.debug("time.compareTo(toTime==>" + time.compareTo(toTime));
        if (time.compareTo(toTime) == UDate.LARGE) {
            return true;
        }
        return false;
    }

    /**
     * <br>[機  能] 基本設定の間隔に適したUDateを変換します。
     * <br>[解  説]
     * <br>[備  考]
     * @param date 変換元UDate
     * @param admConf 管理者設定
     * @param roundFlg true:切上げ(始業時刻) false:切捨て(終業時刻)
     * @return UDate 変換後UDate
     */
    public static UDate convertForDspTime(UDate date, TcdAdmConfModel admConf, boolean roundFlg) {

        int inv = admConf.getTacInterval();
        UDate ret = date.cloneUDate();
        ret.setSecond(0);
        ret.setMilliSecond(0);
        //分を変換
        int cnvMin = 0;
        int min = ret.getIntMinute();

        int div = min / inv;
        int zan = min % inv;
//        Time time = null;
        if (roundFlg) {
//            //切り上げ処理
            if (zan > 0) {
                cnvMin = div * inv + inv;
            } else {
                cnvMin = min;
            }
            ret.setMinute(cnvMin);

        } else {
            //切捨て処理
            cnvMin = div * inv;
            ret.setMinute(cnvMin);

        }

        return ret;
    }

    /**
     * <br>[機  能] long型の分データを基本設定の換算値の従がい時間情報へ変換します。
     * <br>[解  説]
     * <br>[備  考]
     * @param admMdl 基本設定
     * @param minBd 分情報
     * @return BigDecimal 時間情報
     */
    public static BigDecimal convertMinToHourBigDecimal(TcdAdmConfModel admMdl, BigDecimal minBd) {
        BigDecimal ret = BigDecimal.ZERO;
        //時間
        BigDecimal hourBd = minBd.divide(BigDecimal.valueOf(60), BigDecimal.ROUND_DOWN);
        //分
        BigDecimal ansBd = minBd.subtract(hourBd.multiply(BigDecimal.valueOf(60)));
        //分を換算
        if (admMdl.getTacKansan() == GSConstTimecard.TIMECARD_SINSU[0]) {
            //10進数
            ret = ansBd.divide(BigDecimal.valueOf(60), 2, BigDecimal.ROUND_DOWN);
        } else {
            //60進数
            ret = ansBd.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_DOWN);
        }
        ret = ret.add(hourBd);
        return ret;
    }
    /**
     * <br>[機  能] 基本設定の換算値の従がった時間情報をlong型の分データへ変換します。
     * <br>[解  説]
     * <br>[備  考]
     * @param admMdl 基本設定
     * @param hourBd 時間情報
     * @return BigDecimal 分情報
     */
    public static BigDecimal convertHourToMinBigDecimal(TcdAdmConfModel admMdl, BigDecimal hourBd) {
        BigDecimal ret = BigDecimal.ZERO;
        BigDecimal left = BigDecimal.valueOf(hourBd.longValue());
        BigDecimal right = hourBd.subtract(BigDecimal.valueOf(hourBd.longValue()));
        ret.setScale(0);
        ret = ret.add(left.multiply(BigDecimal.valueOf(60)));

        if (admMdl.getTacKansan() == GSConstTimecard.TIMECARD_SINSU[0]) {
            //10進数
            ret = ret.add(right.multiply(BigDecimal.valueOf(60)));
        } else {
            //60進数
            ret = ret.add(right.movePointRight(2));
        }
        return ret;
    }
    /**
     * <br>[機  能] 始業時間の調整を行う
     * <br>[解  説] 始業時間が入力単位と異なる場合、始業時間の切り上げを行う
     * <br>[備  考]
     * @param inTime 始業時間
     * @param interval 入力単位
     * @return 始業時間
     */
    public static Time adjustIntime(Time inTime, int interval) {

        if (inTime != null) {
            UDate adjustTime = UDate.getInstance(inTime.getTime());
            if (adjustTime.getIntMinute() % interval != 0) {
                adjustTime.addMinute(interval - (adjustTime.getIntMinute() % interval));
                return new Time(adjustTime.getTime());
            }
        }

        return inTime;
    }

    /**
     * <br>[機  能] 終業時間の調整を行う
     * <br>[解  説] 終業時間が入力単位と異なる場合、 終業時間の切捨てを行う
     * <br>[備  考]
     * @param outTime 終業時間
     * @param interval 入力単位
     * @return 終業時間
     */
    public static Time adjustOuttime(Time outTime, int interval) {

        if (outTime != null) {
            UDate adjustTime = UDate.getInstance(outTime.getTime());
            if (adjustTime.getIntMinute() % interval != 0) {
                adjustTime.addMinute(-1 * (adjustTime.getIntMinute() % interval));
                return new Time(adjustTime.getTime());
            }
        }

        return outTime;
    }

    /**
     * タイムカード　個別ログ出力を行う
     * @param map マップ
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     */
    public void outPutTimecardLog(
            ActionMapping map,
            RequestModel reqMdl,
            Connection con,
            String opCode,
            String level,
            String value) {
        outPutTimecardLog(map, reqMdl, con, opCode, level, value, GSConstTimecard.TCD_LOG_FLG_NONE);
    }

    /**
     * タイムカード　個別ログ出力を行う
     * @param map マップ
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param logFlg ログ出力判別フラグ
     */
    public void outPutTimecardLog(
            ActionMapping map,
            RequestModel reqMdl,
            Connection con,
            String opCode,
            String level,
            String value,
            int logFlg) {

        BaseUserModel usModel = reqMdl.getSmodel();
        int usrSid = -1;
        if (usModel != null) {
            usrSid = usModel.getUsrsid(); //セッションユーザSID
        }

        GsMessage gsMsg = new GsMessage(reqMdl);
        String timecard = gsMsg.getMessage("tcd.50");

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usrSid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConstTimecard.PLUGIN_ID_TIMECARD);
        logMdl.setLogPluginName(timecard);
        String type = map.getType();
        logMdl.setLogPgId(StringUtil.trimRengeString(type, 100));
        logMdl.setLogPgName(getPgName(type));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(value);
        logMdl.setLogIp(reqMdl.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);
        if (logFlg == GSConstTimecard.TCD_LOG_FLG_PDF) {
            logMdl.setLogCode(" PDFエクスポート");
        }

        LoggingBiz logBiz = new LoggingBiz(con);
        String domain = reqMdl.getDomain();
        logBiz.outPutLog(logMdl, domain);
    }
    /**
     * タイムカードＡＰＩ全般全般のログ出力を行う
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param usid ユーザSID
     * @param pgId プログラムID
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     */
    public void outPutApiLog(
            RequestModel reqMdl,
            Connection con,
            int usid,
            String pgId,
            String opCode,
            String level,
            String value) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String timecard = gsMsg.getMessage("tcd.50");

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConstTimecard.PLUGIN_ID_TIMECARD);
        logMdl.setLogPluginName(timecard);
        logMdl.setLogPgId(pgId);
        logMdl.setLogPgName(getPgName(pgId));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(value);
        logMdl.setLogIp(reqMdl.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);

        LoggingBiz logBiz = new LoggingBiz(con);
        String domain = reqMdl.getDomain();
        logBiz.outPutLog(logMdl, domain);
    }
    /**
     * プログラムIDからプログラム名称を取得する
     * @param id アクションID
     * @return String
     */
    public String getPgName(String id) {
        String ret = new String();
        if (id == null) {
            return ret;
        }

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String logName = "";

        log__.info("プログラムID==>" + id);

        if (id.equals("jp.groupsession.v2.tcd.main.TcdMainAction")) {
            logName = gsMsg.getMessage("tcd.108");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.tcd.tcd010.Tcd010Action")) {
            logName = gsMsg.getMessage("tcd.tcd010.18");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.tcd.tcd020.Tcd020Action")) {
            logName = gsMsg.getMessage("tcd.109");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.tcd.tcd040.Tcd040Action")) {
            logName = gsMsg.getMessage("tcd.110");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.tcd.tcd050kn.Tcd050knAction")) {
            logName = gsMsg.getMessage("tcd.111");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.tcd.tcd070.Tcd070Action")) {
            logName = gsMsg.getMessage("tcd.112");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.tcd.tcd080.Tcd080Action")) {
            logName = gsMsg.getMessage("tcd.113");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.tcd.tcd090kn.Tcd090knAction")) {
            logName = gsMsg.getMessage("tcd.114");
            return logName;
        }

        if (id.equals("jp.groupsession.v2.api.timecard.dakoku.ApiDakokuAction")) {
            logName = gsMsg.getMessage("tcd.115");
            return logName;
        }
        return ret;
    }

    /**
     * <br>[機  能] 締め日を考慮しデフォルト表示年月を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param date 対象年月
     * @param admConf TcdAdmConfModel
     * @return UDate 表示年月
     */
    public static UDate getDspUDate(UDate date, TcdAdmConfModel admConf) {
        UDate ret = date.cloneUDate();
        int sime = admConf.getTacSimebi();
        if (sime == GSConstTimecard.TIMECARD_LIMITDAY[5]) {
            return ret;
        } else {
            int day = ret.getIntDay();
            if (sime >= day) {
                ret.addMonth(-1);
            }
            return ret;
        }
    }
}
