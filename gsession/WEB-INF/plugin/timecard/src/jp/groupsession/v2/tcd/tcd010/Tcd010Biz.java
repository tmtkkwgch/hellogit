package jp.groupsession.v2.tcd.tcd010;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnHolidayDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnHolidayModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrInoutModel;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.TimecardBiz;
import jp.groupsession.v2.tcd.TimecardUtil;
import jp.groupsession.v2.tcd.dao.TcdPriConfModel;
import jp.groupsession.v2.tcd.dao.TcdTcdataDao;
import jp.groupsession.v2.tcd.dao.TcdTimezoneDao;
import jp.groupsession.v2.tcd.dao.TcdTimezoneModel;
import jp.groupsession.v2.tcd.dao.TcdUserGrpDao;
import jp.groupsession.v2.tcd.dao.TimecardDao;
import jp.groupsession.v2.tcd.model.TcdTcdataModel;
import jp.groupsession.v2.tcd.model.TcdTotalValueModel;
import jp.groupsession.v2.tcd.model.TcdUserGrpModel;
import jp.groupsession.v2.zsk.biz.ZsjCommonBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] タイムカード 一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd010Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd010Biz.class);
    /** 最小時刻 */
    private static final int MINIMUM_TIME = 0;
    /** 最大時刻 */
    private static final int MAXIMUM_TIME = 2400;

    /**
     * <br>[機  能] 初期表示画面情報を設定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Tcd010ParamModel paramMdl,
            RequestModel reqMdl, Connection con) throws SQLException {
        log__.debug("タイムカード表示情報取得開始");
        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //ユーザ種別を判定 一般・グループ管理者・管理者
        int userKbn = getUserKbn(usModel, con);
        paramMdl.setUsrKbn(String.valueOf(userKbn));
        //グループコンボ
        List<LabelValueBean> groupLabel = null;
        List<LabelValueBean> userLabel = null;
        //ユーザ切替用グループ・ユーザコンボ作成(管理グループ)
        groupLabel = getGroupLabelList(con, sessionUsrSid, userKbn, reqMdl);
        paramMdl.setTcd010GpLabelList(groupLabel);
        int dspGpSid = NullDefault.getInt(
                paramMdl.getSltGroupSid(), getInitGpSid(sessionUsrSid, groupLabel, con));

        paramMdl.setSltGroupSid(String.valueOf(dspGpSid));

        userLabel = getUserLabelList(con, dspGpSid);
        paramMdl.setTcd010UsrLabelList(userLabel);

        //表示ユーザ
        String dspUsrSid = "";
        if (StringUtil.isNullZeroStringSpace(paramMdl.getUsrSid())) {
            dspUsrSid = getInitUsrSid(sessionUsrSid, userLabel, con);
        } else {
            dspUsrSid = getInitUsrSid(Integer.parseInt(paramMdl.getUsrSid()), userLabel, con);
        }
        log__.debug("dspUsrSid==>" + dspUsrSid);
        paramMdl.setUsrSid(dspUsrSid);
        paramMdl.setMyselfFlg(paramMdl.getUsrSid().equals(String.valueOf(sessionUsrSid)));
        //基本設定を取得
        TimecardBiz tcBiz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admConf = tcBiz.getTcdAdmConfModel(sessionUsrSid, con);
        TcdPriConfModel priConf = tcBiz.getTcdPriConfModel(sessionUsrSid, con);
        if (priConf.getTpcKinmuOut() == GSConstTimecard.KINMU_PDF) {
            paramMdl.setKinmuOut(String.valueOf(GSConstTimecard.KINMU_PDF));
        } else {
            paramMdl.setKinmuOut(String.valueOf(GSConstTimecard.KINMU_EXCEL));
        }

        //表示年月を取得
        UDate sysDate = TimecardBiz.getDspUDate(new UDate(), admConf);

        String strDspDate =
            NullDefault.getString(
                paramMdl.getTcdDspFrom(),
                sysDate.getDateString());

        paramMdl.setTcdDspFrom(strDspDate);
        paramMdl.setYear(strDspDate.substring(0, 4));
        paramMdl.setMonth(strDspDate.substring(4, 6));

        //タイムカード一覧情報を取得
        paramMdl.setTcd010List(
                __getTimeCardList(paramMdl, Integer.parseInt(dspUsrSid), admConf, con, reqMdl));
        //前月・当月の集計値を取得
        TcdTotalValueModel lastValueMdl
            = __getLastTotalValue(paramMdl, reqMdl, sessionUsrSid, admConf, con);
        paramMdl.setLastMonthMdl(lastValueMdl);
        TcdTotalValueModel thisValueMdl
            = __getThisTotalValue(paramMdl, reqMdl, sessionUsrSid, admConf, con);
        paramMdl.setThisMonthMdl(thisValueMdl);

        UDate lastDate = new UDate();
        lastDate.setDate(Integer.parseInt(paramMdl.getYear()),
                        Integer.parseInt(paramMdl.getMonth()), 1);
        paramMdl.setThisMonthString(lastDate.getStrMonth());
        lastDate.addMonth(-1);
        paramMdl.setLastMonthString(lastDate.getStrMonth());
        //不正データ処理
        if (tcBiz.isFailDataExist(Integer.parseInt(dspUsrSid), con, admConf)) {
            paramMdl.setTcd010FailFlg(String.valueOf(GSConstTimecard.DATA_FAIL));
        } else {
            paramMdl.setTcd010FailFlg(String.valueOf(GSConstTimecard.DATA_NOT_FAIL));
        }

        //年間集計値を取得//
        TimecardDao tcddao = new TimecardDao(con);

        //期首月を取得
        paramMdl.setKishuMonth(NullDefault.getString(String.valueOf(tcddao.getKishuMonth()), ""));
        //年度を取得
        paramMdl.setNendYear(getNend(Integer.parseInt(paramMdl.getKishuMonth()), paramMdl));
        UDate nenkan = new UDate();
        nenkan.setDate(Integer.parseInt(paramMdl.getNendYear()),
                Integer.parseInt(paramMdl.getKishuMonth()), 1);
        paramMdl.setKishuMonth(nenkan.getStrMonth());

        UDate fdate = new UDate();
        fdate.setYear(Integer.parseInt(paramMdl.getNendYear()));
        fdate.setMonth(Integer.parseInt(paramMdl.getKishuMonth()));
        setTimeCardCal(Integer.parseInt(paramMdl.getNendYear()),
                Integer.parseInt(paramMdl.getKishuMonth()), admConf.getTacSimebi(), fdate);


        UDate date = new UDate();

        //期末月を取得
        nenkan.addMonth(11);
        paramMdl.setKimatuMonth(nenkan.getStrMonth());
        UDate tdate = setTimeCardCal(nenkan.getYear(),
                nenkan.getMonth(), admConf.getTacSimebi(), date);
        paramMdl.setEndYear(nenkan.getStrYear());

        //年間のデータを取得
        TcdTotalValueModel yearMdl = new TcdTotalValueModel();
        yearMdl = getTotalValueModel(Integer.parseInt(paramMdl.getUsrSid()),
                  fdate, tdate, sessionUsrSid, con, reqMdl);
        paramMdl.setTotalYearMdl(yearMdl);

        //月別集計値//
        ArrayList<TcdTotalValueModel> monthTotalList = new ArrayList<TcdTotalValueModel>();
        TcdTotalValueModel monthTotalMdl = new TcdTotalValueModel();
        UDate from = new UDate();
        from.setDate(Integer.parseInt(paramMdl.getNendYear()),
                Integer.parseInt(paramMdl.getKishuMonth()), 1);
        for (int i = 1; i <= 12; i++) {
            from.setDate(from.getYear(),
                    from.getMonth(), 1);
            monthTotalMdl = __getTotalValue(paramMdl, reqMdl, sessionUsrSid, admConf, con, from);
            monthTotalMdl.setKadoMonth(from.getStrMonth());
            monthTotalList.add(monthTotalMdl);
            from.addMonth(1);
        }
        paramMdl.setMonthTtlList(monthTotalList);

        //ロック処理
        if (userKbn == GSConstTimecard.USER_KBN_NORMAL
         && admConf.getTacLockFlg() == GSConstTimecard.LOCK_FLG) {
            paramMdl.setTcd010LockFlg(String.valueOf(GSConstTimecard.LOCK_FLG));
        } else {
            paramMdl.setTcd010LockFlg(String.valueOf(GSConstTimecard.UNLOCK_FLG));
        }
        log__.debug("paramMdl.getTcd010LockFlg()==>" + paramMdl.getTcd010LockFlg());
        log__.debug("paramMdl.getTcd010FailFlg()==>" + paramMdl.getTcd010FailFlg());
    }

    /**
     * <br>[機  能] 前月のタイムカード集計値を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param sessionUsrSid セッションユーザSID
     * @param admConf 基本設定
     * @param con コネクション
     * @return TcdTotalValueModel 集計値モデル
     * @throws SQLException SQL実行時例外
     */
    private TcdTotalValueModel __getLastTotalValue(
            Tcd010ParamModel paramMdl,
            RequestModel reqMdl,
            int sessionUsrSid,
            TcdAdmConfModel admConf,
            Connection con) throws SQLException {

        int usrSid = Integer.parseInt(paramMdl.getUsrSid());
        int year = Integer.parseInt(paramMdl.getYear());
        int month = Integer.parseInt(paramMdl.getMonth());
        if (month == 1) {
            year--;
            month = 12;
        } else {
            month--;
        }
        UDate fdate = new UDate();
        UDate tdate = setTimeCardCal(year, month, admConf.getTacSimebi(), fdate);

        TcdTotalValueModel ret
            = getTotalValueModel(usrSid, fdate, tdate, sessionUsrSid, con, reqMdl);
        return ret;
    }

    /**
     * <br>[機  能] 今月のタイムカード集計値を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param sessionUsrSid セッションユーザSID
     * @param admConf 基本設定
     * @param con コネクション
     * @return TcdTotalValueModel 集計モデル
     * @throws SQLException SQL実行時例外
     */
    private TcdTotalValueModel __getThisTotalValue(
            Tcd010ParamModel paramMdl,
            RequestModel reqMdl,
            int sessionUsrSid,
            TcdAdmConfModel admConf,
            Connection con) throws SQLException {
        int usrSid = Integer.parseInt(paramMdl.getUsrSid());
        int year = Integer.parseInt(paramMdl.getYear());
        int month = Integer.parseInt(paramMdl.getMonth());
        UDate fdate = new UDate();
        UDate tdate = setTimeCardCal(year, month, admConf.getTacSimebi(), fdate);
        TcdTotalValueModel ret
            = getTotalValueModel(usrSid, fdate, tdate, sessionUsrSid, con, reqMdl);
        return ret;
    }

    /**
     * <br>[機  能] ユーザSIDと年月を指定しタイムカード集計値を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param date 開始日
     * @param endDate 終了日
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return TcdTotalValueModel 集計モデル
     * @throws SQLException SQL実行時例外
     */
    public TcdTotalValueModel getTotalValueModel(
            int usrSid,
            UDate date,
            UDate endDate,
            int sessionUsrSid,
            Connection con,
            RequestModel reqMdl)
    throws SQLException {

        //基本設定取得
        TimecardBiz tmBiz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admMdl = tmBiz.getTcdAdmConfModel(sessionUsrSid, con);
        //時間帯設定取得
        ArrayList<TcdTimezoneModel> timeZoneList = tmBiz.getTimeZoneModel(false, con);
        //休日情報取得
        CmnHolidayDao holDao = new CmnHolidayDao(con);
        HashMap<String, CmnHolidayModel> holMap = holDao.getHoliDayListFotTcd(date, endDate);

        //通常時間帯取得
        TcdTimezoneDao tzDao = new TcdTimezoneDao(con);
        Time normalFrTime = tzDao.getFrTimeMin(GSConstTimecard.TIMEZONE_KBN_NORMAL);
        Time normalToTime = tzDao.getToTimeMax(GSConstTimecard.TIMEZONE_KBN_NORMAL);

        //タイムカード情報取得
        TcdTcdataDao tcDao = new TcdTcdataDao(con);
        List<Tcd010Model> tcd010List = tcDao.select(usrSid, date, endDate);

        BigDecimal kadoBaseDays = BigDecimal.ZERO;  //稼動基準日数
        BigDecimal kadoDays = BigDecimal.ZERO;      //稼動実績日数
        BigDecimal kadoBaseHours = BigDecimal.ZERO; //稼動基準時間数
        BigDecimal kadoHours = BigDecimal.ZERO;     //稼動実績時間数
        BigDecimal zangyoDays = BigDecimal.ZERO;    //残業日数
        BigDecimal zangyoHours = BigDecimal.ZERO;   //残業時間数
        BigDecimal sinyaDays = BigDecimal.ZERO;     //深夜日数
        BigDecimal sinyaHours = BigDecimal.ZERO;    //深夜時間数
        BigDecimal kyusyutuDays = BigDecimal.ZERO;  //休日出勤日数
        BigDecimal kyusyutuHours = BigDecimal.ZERO; //休日出勤時間数
        BigDecimal chikokuDays = BigDecimal.ZERO;   //遅刻日数
        BigDecimal chikokuTimes = BigDecimal.ZERO;  //遅刻時間
        BigDecimal soutaiDays = BigDecimal.ZERO;    //早退日数
        BigDecimal soutaiTimes = BigDecimal.ZERO;    //早退時間
        BigDecimal kekkinDays = BigDecimal.ZERO;    //欠勤日数
        BigDecimal keityoDays = BigDecimal.ZERO;    //慶弔休暇日数
        BigDecimal yuukyuDays = BigDecimal.ZERO;    //有給休暇日数
        BigDecimal daikyuDays = BigDecimal.ZERO;    //代休日数
        BigDecimal sonotaDays = BigDecimal.ZERO;    //その他休暇日数

        //基準:稼働日数・稼働時間
        kadoBaseDays = getKadoBaseDays(date, endDate, admMdl, holMap);
        kadoBaseHours = getKadoBaseHours(kadoBaseDays, admMdl, timeZoneList);

        //集計処理
        Tcd010Model model = null;
        for (int i = 0; i < tcd010List.size(); i++) {
            model = tcd010List.get(i);

            if (model.getTcdOuttime() != null) {
                //終業時刻が未登録な場合集計対象外
                //実績稼動
                kadoDays = __getKadoDays(kadoDays, model);
                kadoHours = __getKadoHours(kadoHours, model, admMdl, timeZoneList); //分単位に集計
                zangyoDays = __getZangyoDays(zangyoDays, model, admMdl, timeZoneList);
                zangyoHours = __getZangyoHours(zangyoHours, model, admMdl, timeZoneList);
                sinyaDays = __getSinyaDays(sinyaDays, model, admMdl, timeZoneList);
                sinyaHours = __getSinyaHours(sinyaHours, model, admMdl, timeZoneList);
                //休日曜日
                kyusyutuDays = __getKyusyutuDays(kyusyutuDays, model, admMdl, holMap);
                //休日出勤稼動
                kyusyutuHours = __getKyusyutuHours(
                        kyusyutuHours, model, admMdl, holMap, timeZoneList);
            }
            //遅刻
            chikokuDays = __getChikokuDays(chikokuDays, model);
            chikokuTimes = __getChikokuTimes(chikokuTimes, model, admMdl, timeZoneList,
                                            normalFrTime, normalToTime, reqMdl);
            //早退
            soutaiDays = __getSoutaiDays(soutaiDays, model);
            soutaiTimes = __getSoutaiTimes(soutaiTimes, model, admMdl, timeZoneList,
                                        normalFrTime, normalToTime, reqMdl);
            //欠勤
            kekkinDays = __getKekkinDays(kekkinDays, model, GSConstTimecard.HOL_KBN_KEKKIN);
            //慶弔
            keityoDays = __getKekkinDays(keityoDays, model, GSConstTimecard.HOL_KBN_KEITYO);
            //有給
            yuukyuDays = __getKekkinDays(yuukyuDays, model, GSConstTimecard.HOL_KBN_YUUKYU);
            //代休
            daikyuDays = __getKekkinDays(daikyuDays, model, GSConstTimecard.HOL_KBN_DAIKYU);
            //その他
            sonotaDays = __getKekkinDays(sonotaDays, model, GSConstTimecard.HOL_KBN_SONOTA);
        }
        TcdTotalValueModel ret = new TcdTotalValueModel();
        ret.setStartDate(date);
        ret.setEndDate(endDate);
        ret.setKadoBaseDays(kadoBaseDays);
        ret.setKadoDays(kadoDays);
        ret.setKadoBaseHours(kadoBaseHours);
        ret.setKadoHours(TimecardBiz.convertMinToHourBigDecimal(admMdl, kadoHours));
        ret.setZangyoDays(zangyoDays);
        ret.setZangyoHours(TimecardBiz.convertMinToHourBigDecimal(admMdl, zangyoHours));
        ret.setSinyaDays(sinyaDays);
        ret.setSinyaHours(TimecardBiz.convertMinToHourBigDecimal(admMdl, sinyaHours));
        ret.setKyusyutuDays(kyusyutuDays);
        ret.setKyusyutuHours(TimecardBiz.convertMinToHourBigDecimal(admMdl, kyusyutuHours));
        ret.setChikokuDays(chikokuDays);
        ret.setChikokuTimes(TimecardBiz.convertMinToHourBigDecimal(admMdl, chikokuTimes));
        ret.setSoutaiDays(soutaiDays);
        ret.setSoutaiTimes(TimecardBiz.convertMinToHourBigDecimal(admMdl, soutaiTimes));
        ret.setKekkinDays(kekkinDays);
        ret.setKeityoDays(keityoDays);
        ret.setYuukyuDays(yuukyuDays);
        ret.setDaikyuDays(daikyuDays);
        ret.setSonotaDays(sonotaDays);
        //表示用フォーマットを設定
        setTotalValueModelDspString(ret);
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
     * 休日として扱うか判定します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param date 日付
     * @param admMdl 基本設定
     * @param holMap 休日設定
     * @return boolean true:休日　false:非休日
     */
    public boolean isHoliday(
            UDate date,
            TcdAdmConfModel admMdl,
            HashMap<String, CmnHolidayModel> holMap) {

        CmnHolidayModel holMdl = null;
        //休日設定日か判定(休日設定日は基準日にカウントしない)
        holMdl = holMap.get(date.getDateString());
        if (holMdl != null) {
            return true;
        } else {
            //休日換算する曜日か判定(休日曜日は基準日にカウントしない)
            int week = date.getWeek();
            switch (week) {
            case UDate.SUNDAY:
                if (admMdl.getTacHolSun() == GSConstTimecard.HOLIDAY_FLG) {
                    return true;
                }
                break;
            case UDate.MONDAY:
                if (admMdl.getTacHolMon() == GSConstTimecard.HOLIDAY_FLG) {
                    return true;
                }
                break;
            case UDate.TUESDAY:
                if (admMdl.getTacHolTue() == GSConstTimecard.HOLIDAY_FLG) {
                    return true;
                }
                break;
            case UDate.WEDNESDAY:
                if (admMdl.getTacHolWed() == GSConstTimecard.HOLIDAY_FLG) {
                    return true;
                }
                break;
            case UDate.THURSDAY:
                if (admMdl.getTacHolThu() == GSConstTimecard.HOLIDAY_FLG) {
                    return true;
                }
                break;
            case UDate.FRIDAY:
                if (admMdl.getTacHolFri() == GSConstTimecard.HOLIDAY_FLG) {
                    return true;
                }
                break;
            case UDate.SATURDAY:
                if (admMdl.getTacHolSat() == GSConstTimecard.HOLIDAY_FLG) {
                    return true;
                }
                break;
            default:
                return false;
            }
        }
        return false;
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
     * <br>[機  能] 稼動基準日を算出する。
     * <br>[解  説]
     * <br>[備  考] 基準日では無い場合はBigDecimal(0)を返します。
     * @param fdate 開始日付
     * @param tdate 終了日付
     * @param admMdl 基本設定モデル
     * @param holMap 休日情報
     * @return BigDecimal 基準日数
     */
    public BigDecimal getKadoBaseDays(
            UDate fdate,
            UDate tdate,
            TcdAdmConfModel admMdl,
            HashMap<String, CmnHolidayModel> holMap) {

        UDate date = fdate.cloneUDate();
        int count = 0;

        while (date.compareDateYMD(tdate) != UDate.SMALL) {
            if (!isHoliday(date, admMdl, holMap)) {
                count++;
            }
            //日付を進める
            date.addDay(1);
        }
        BigDecimal ret = new BigDecimal(count);
        return ret;
    }

    /**
     * <br>[機  能] 基準稼働時間を取得算出する。
     * <br>[解  説]
     * <br>[備  考]
     * @param baseDays 稼動日数
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯設定
     * @return BigDecimal 基準稼働時間
     */
    public BigDecimal getKadoBaseHours(
            BigDecimal baseDays,
            TcdAdmConfModel admMdl,
            ArrayList<TcdTimezoneModel> timeZoneList) {
        BigDecimal ret = BigDecimal.ZERO;
        ret.setScale(2);
        UDate frDate = new UDate();
        frDate.setZeroHhMmSs();
        frDate.setMilliSecond(0);
        UDate toDate = frDate.cloneUDate();
        long min = 0;
        //1日の稼動基準時間を取得
        if (timeZoneList == null) {
            return ret;
        }
        for (TcdTimezoneModel tzMdl : timeZoneList) {
            if (tzMdl.getTtzKbn() == GSConstTimecard.TIMEZONE_KBN_NORMAL) {
                frDate.setZeroHhMmSs();
                toDate.setZeroHhMmSs();
                Time frTime = tzMdl.getTtzFrtime();
                Time toTime = tzMdl.getTtzTotime();
                frDate.setHour(TimecardUtil.getTimeHour(frTime));
                frDate.setMinute(TimecardUtil.getTimeMin(frTime));
                toDate.setHour(TimecardUtil.getTimeHour(toTime));
                toDate.setMinute(TimecardUtil.getTimeMin(toTime));
                min = min + UDateUtil.diffMinute(frDate, toDate);
            }
        }

        BigDecimal minBd = baseDays.multiply(BigDecimal.valueOf(min));
        ret = TimecardBiz.convertMinToHourBigDecimal(admMdl, minBd);
        return ret;
    }

    /**
     * <br>[機  能] 稼動実績日数を引数のBigDecimal kadoDaysへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param kadoDays 稼動実績日数(集計先)
     * @param model タイムカード情報
     * @return BigDecimal 稼動実績日数
     */
    private BigDecimal __getKadoDays(BigDecimal kadoDays, Tcd010Model model) {
        if (model.getTcdOuttime() != null) {
            kadoDays = kadoDays.add(BigDecimal.valueOf(1));
        }
        return kadoDays;
    }

    /**
     * <br>[機  能] 稼動実績時間数を引数のBigDecimal kadoHoursへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param kadoHours 稼動実績時間数(集計先)
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @return BigDecimal 稼動実績時間数
     */
    private BigDecimal __getKadoHours(
            BigDecimal kadoHours,
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            ArrayList<TcdTimezoneModel> timeZoneList) {

        if (model.getTcdOuttime() != null) {
            UDate frDate = new UDate();
            frDate.setZeroHhMmSs();

            UDate toDate = frDate.cloneUDate();
            Time frTime = model.getTcdIntime();
            Time toTime = model.getTcdOuttime();
            frDate.setHour(TimecardUtil.getTimeHour(frTime));
            frDate.setMinute(TimecardUtil.getTimeMin(frTime));
            toDate.setHour(TimecardUtil.getTimeHour(toTime));
            toDate.setMinute(TimecardUtil.getTimeMin(toTime));

            if (toDate.compareDateYMDHM(frDate) == UDate.LARGE) {
                toDate.addDay(1);
            }

            UDate cvFrDate = TimecardBiz.convertForDspTime(frDate, admMdl, true);
            UDate cvToDate = TimecardBiz.convertForDspTime(toDate, admMdl, false);

            //始業時間 > 終了時間の場合、計算を行わない
            if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {
                long min = UDateUtil.diffMinute(cvFrDate, cvToDate);
                long submin = __getRepetitionTimeZoneMinute(
                        cvFrDate, cvToDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_KYUKEI);
                kadoHours = kadoHours.add(BigDecimal.valueOf(min - submin));
            }
        }
        return kadoHours;
    }

    /**
     * <br>[機  能] 残業実績日数を引数のBigDecimal zangyoDaysへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param zangyoDays 残業実績日数(集計先)
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @return BigDecimal 残業実績日数
     */
    private BigDecimal __getZangyoDays(
            BigDecimal zangyoDays,
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            ArrayList<TcdTimezoneModel> timeZoneList) {

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

        if (toDate.compareDateYMDHM(frDate) == UDate.LARGE) {
            toDate.addDay(1);
        }

        UDate cvFrDate = TimecardBiz.convertForDspTime(frDate, admMdl, true);
        UDate cvToDate = TimecardBiz.convertForDspTime(toDate, admMdl, false);

        //始業時間 > 終了時間の場合、計算を行わない
        if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {
            long zanmin = __getRepetitionTimeZoneMinute(
                    frDate, toDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_ZANGYO);
            long sinyamin = __getRepetitionTimeZoneMinute(
                    frDate, toDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_SINYA);

            if (zanmin > 0 || sinyamin > 0) {
                zangyoDays = zangyoDays.add(BigDecimal.valueOf(1));
            }
        }
        return zangyoDays;
    }

    /**
     * <br>[機  能] 残業実績日数を引数のBigDecimal kadoDaysへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param zangyoHours 残業実績時間数(集計先)
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @return BigDecimal 稼動実績日数
     */
    private BigDecimal __getZangyoHours(
            BigDecimal zangyoHours,
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            ArrayList<TcdTimezoneModel> timeZoneList) {

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

        if (toDate.compareDateYMDHM(frDate) == UDate.LARGE) {
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
        return zangyoHours;
    }

    /**
     * <br>[機  能] 深夜実績日数を引数のBigDecimal sinyaDaysへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param sinyaDays 残業実績日数(集計先)
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @return BigDecimal 残業実績日数
     */
    private BigDecimal __getSinyaDays(
            BigDecimal sinyaDays,
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            ArrayList<TcdTimezoneModel> timeZoneList) {

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

        if (toDate.compareDateYMDHM(frDate) == UDate.LARGE) {
            toDate.addDay(1);
        }

        UDate cvFrDate = TimecardBiz.convertForDspTime(frDate, admMdl, true);
        UDate cvToDate = TimecardBiz.convertForDspTime(toDate, admMdl, false);

        //始業時間 > 終了時間の場合、計算を行わない
        if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {
            long sinyamin = __getRepetitionTimeZoneMinute(
                    frDate, toDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_SINYA);

            if (sinyamin > 0) {
                sinyaDays = sinyaDays.add(BigDecimal.valueOf(1));
            }
        }
        return sinyaDays;
    }

    /**
     * <br>[機  能] 深夜実績日数を引数のBigDecimal sinyaDaysへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param sinyaDays 深夜実績時間(集計先)
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @return BigDecimal 深夜実績時間
     */
    private BigDecimal __getSinyaHours(
            BigDecimal sinyaDays,
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            ArrayList<TcdTimezoneModel> timeZoneList) {

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

        if (toDate.compareDateYMDHM(frDate) == UDate.LARGE) {
            toDate.addDay(1);
        }

        UDate cvFrDate = TimecardBiz.convertForDspTime(frDate, admMdl, true);
        UDate cvToDate = TimecardBiz.convertForDspTime(toDate, admMdl, false);

        //始業時間 > 終了時間の場合、計算を行わない
        if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {

            long sinyamin = __getRepetitionTimeZoneMinute(
                    cvFrDate, cvToDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_SINYA);

            sinyaDays = sinyaDays.add(BigDecimal.valueOf(sinyamin));
        }

        return sinyaDays;
    }

    /**
     * <br>[機  能] 休日出勤実績日数を引数のBigDecimal kyusyutuDaysへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param kyusyutuDays 休日出勤実績日数(集計先)
     * @param model タイムカード情報
     * @param admMdl 基本設定情報
     * @param holMap 休日情報
     * @return BigDecimal 休日出勤実績日数
     */
    private BigDecimal __getKyusyutuDays(
            BigDecimal kyusyutuDays,
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            HashMap<String, CmnHolidayModel> holMap) {

        UDate tcDate = model.getTcdDate();
        tcDate.setZeroHhMmSs();

        if (isHoliday(tcDate, admMdl, holMap)) {
            kyusyutuDays = kyusyutuDays.add(BigDecimal.valueOf(1));
        }

        return kyusyutuDays;
    }

    /**
     * <br>[機  能] 休日出勤実績稼働時間を引数のBigDecimal kyusyutuHoursへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param kyusyutuHours 休日出勤実績日数(集計先)
     * @param model タイムカード情報
     * @param admMdl 基本設定情報
     * @param holMap 休日情報
     * @param timeZoneList 時間帯情報
     * @return BigDecimal 休日出勤実績日数
     */
    private BigDecimal __getKyusyutuHours(
            BigDecimal kyusyutuHours,
            Tcd010Model model,
            TcdAdmConfModel admMdl,
            HashMap<String, CmnHolidayModel> holMap,
            ArrayList<TcdTimezoneModel> timeZoneList) {

        UDate tcDate = model.getTcdDate();
        tcDate.setZeroHhMmSs();

        if (isHoliday(tcDate, admMdl, holMap)) {
            //終了登録済み
            if (model.getTcdOuttime() != null) {
                UDate frDate = new UDate();
                frDate.setZeroHhMmSs();
                UDate toDate = frDate.cloneUDate();
                Time frTime = model.getTcdIntime();
                Time toTime = model.getTcdOuttime();
                frDate.setHour(TimecardUtil.getTimeHour(frTime));
                frDate.setMinute(TimecardUtil.getTimeMin(frTime));
                toDate.setHour(TimecardUtil.getTimeHour(toTime));
                toDate.setMinute(TimecardUtil.getTimeMin(toTime));
                if (toDate.compareDateYMDHM(frDate) == UDate.LARGE) {
                    toDate.addDay(1);
                }

                UDate cvFrDate = TimecardBiz.convertForDspTime(frDate, admMdl, true);
                UDate cvToDate = TimecardBiz.convertForDspTime(toDate, admMdl, false);

                //始業時間 > 終了時間の場合、計算を行わない
                if (cvToDate.compareDateYMDHM(cvFrDate) != UDate.LARGE) {

                    long min = UDateUtil.diffMinute(cvFrDate, cvToDate);
                    long submin = __getRepetitionTimeZoneMinute(
                            cvFrDate, cvToDate, timeZoneList, GSConstTimecard.TIMEZONE_KBN_KYUKEI);
                    kyusyutuHours = kyusyutuHours.add(BigDecimal.valueOf(min - submin));
                }
            }
        }
        return kyusyutuHours;
    }

    /**
     * <br>[機  能] 欠勤日数を引数のBigDecimal kekkinDaysへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param kekkinDays 欠勤実績日数(集計先)
     * @param model タイムカード情報
     * @param kbn 集計する欠勤区分
     * @return BigDecimal 欠勤実績日数
     */
    private BigDecimal __getKekkinDays(BigDecimal kekkinDays, Tcd010Model model, int kbn) {
        if (model.getTcdHolkbn() == kbn) {
            BigDecimal days = BigDecimal.valueOf(0);
            if (model.getTcdHolcnt() != null) {
                days = model.getTcdHolcnt();
            }
            kekkinDays = kekkinDays.add(days);
        }
        return kekkinDays;
    }

    /**
     * <br>[機  能] 遅刻日数を引数のBigDecimal chikokuDaysへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param chikokuDays 遅刻実績日数(集計先)
     * @param model タイムカード情報
     * @return BigDecimal 遅刻実績日数
     */
    private BigDecimal __getChikokuDays(BigDecimal chikokuDays, Tcd010Model model) {

        if (model.getTcdChkkbn() == GSConstTimecard.CHK_KBN_SELECT) {
            chikokuDays = chikokuDays.add(BigDecimal.valueOf(1));
        }
        return chikokuDays;
    }

    /**
     * <br>[機  能] 遅刻時間を引数のBigDecimal chikokuTimesへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param chikokuTimes 遅刻実績時間(集計先)
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @param normalFrTime 通常時間帯開始時刻
     * @param normalToTime 通常時間帯終了時刻
     * @param reqMdl リクエスト情報
     * @return BigDecimal 遅刻実績時間
     */
    private BigDecimal __getChikokuTimes(BigDecimal chikokuTimes,
                                        Tcd010Model model, TcdAdmConfModel admMdl,
                                        ArrayList<TcdTimezoneModel> timeZoneList,
                                        Time normalFrTime, Time normalToTime,
                                        RequestModel reqMdl) {

        TimecardBiz tcBiz = new TimecardBiz(reqMdl);
        BigDecimal chikokuTime
            = tcBiz.getChikokuTime(model, admMdl, timeZoneList,
                                    normalFrTime, normalToTime);
        if (chikokuTime != null) {
            chikokuTimes = chikokuTimes.add(chikokuTime);
        }
        return chikokuTimes;
    }

    /**
     * <br>[機  能] 早退日数を引数のBigDecimal soutaiDaysへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param soutaiDays 早退実績日数(集計先)
     * @param model タイムカード情報
     * @return BigDecimal 早退実績日数
     */
    private BigDecimal __getSoutaiDays(BigDecimal soutaiDays, Tcd010Model model) {

        if (model.getTcdSoukbn() == GSConstTimecard.SOU_KBN_SELECT) {
            soutaiDays = soutaiDays.add(BigDecimal.valueOf(1));
        }
        return soutaiDays;
    }

    /**
     * <br>[機  能] 早退時間を引数のBigDecimal soutaiTimesへ集計します。
     * <br>[解  説]
     * <br>[備  考]
     * @param soutaiTimes 早退実績時間(集計先)
     * @param model タイムカード情報
     * @param admMdl 基本設定
     * @param timeZoneList 時間帯情報
     * @param normalFrTime 通常時間帯開始時刻
     * @param normalToTime 通常時間帯終了時刻
     * @param reqMdl リクエスト情報
     * @return BigDecimal 早退実績時間
     */
    private BigDecimal __getSoutaiTimes(BigDecimal soutaiTimes, Tcd010Model model,
                                        TcdAdmConfModel admMdl,
                                        ArrayList<TcdTimezoneModel> timeZoneList,
                                        Time normalFrTime, Time normalToTime,
                                        RequestModel reqMdl) {

        TimecardBiz tcBiz = new TimecardBiz(reqMdl);
        BigDecimal soutaiTime
            = tcBiz.getSoutaiTime(model, admMdl, timeZoneList,
                                    normalFrTime, normalToTime);
        if (soutaiTime != null) {
            soutaiTimes = soutaiTimes.add(soutaiTime);
        }
        return soutaiTimes;
    }

    /**
     * <br>[機  能] 一ヶ月分のタイムカード情報を取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param usrSid ユーザSID
     * @param admConf 基本設定
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<Tcd010Model> __getTimeCardList(
            Tcd010ParamModel paramMdl,
            int usrSid,
            TcdAdmConfModel admConf,
            Connection con,
            RequestModel reqMdl) throws SQLException {

        //締め日
        int sime = admConf.getTacSimebi();
        UDate sysDate = new UDate();

        ArrayList<Tcd010Model> ret = new ArrayList<Tcd010Model>();
        int year = Integer.parseInt(paramMdl.getYear());
        int month = Integer.parseInt(paramMdl.getMonth());
        //休日情報取得
        UDate date = new UDate();
        //from～toを設定
        UDate endDate = setTimeCardCal(year, month, sime, date);

        CmnHolidayDao holDao = new CmnHolidayDao(con);
        HashMap<String, CmnHolidayModel> holMap = holDao.getHoliDayList(date, endDate);

        TcdTcdataDao tcdDao = new TcdTcdataDao(con);
        HashMap<String, Tcd010Model> timeMap = tcdDao.getTimeCardMap(usrSid, date, endDate);
        Tcd010Model tcdMdl = null;

        //開始～終了日
        int interval = admConf.getTacInterval();
        while (date.compareDateYMD(endDate) != UDate.SMALL) {

            tcdMdl = timeMap.get(date.getDateString());
            Time inTime = null;

            if (tcdMdl == null) {
                tcdMdl = new Tcd010Model();
            } else {
                //From > To の場合、24時間をプラス
                boolean addToTime = tcdMdl.getTcdOuttime() != null
                 && tcdMdl.getTcdIntime().compareTo(tcdMdl.getTcdOuttime()) == UDate.LARGE;

                //始業時間、終業時間の調整を行う
                inTime = TimecardBiz.adjustIntime(tcdMdl.getTcdIntime(), interval);
                Time outTime = TimecardBiz.adjustOuttime(tcdMdl.getTcdOuttime(), interval);

                tcdMdl.setTcd010ShigyouStr(TimecardUtil.getTimeString(inTime));
                if (addToTime) {
                    tcdMdl.setTcd010SyugyouStr(TimecardUtil.getTimeString(outTime, 24));
                } else {
                    tcdMdl.setTcd010SyugyouStr(TimecardUtil.getTimeString(outTime));
                }

                //打刻時間
                tcdMdl.setTcd010StrikeShigyouStr(
                        TimecardUtil.getTimeString(tcdMdl.getTcdStrikeIntime()));

                if (tcdMdl.getTcdStrikeIntime() != null && tcdMdl.getTcdStrikeOuttime() != null
                 && tcdMdl.getTcdStrikeIntime().compareTo(
                         tcdMdl.getTcdStrikeOuttime()) == UDate.LARGE) {

                    tcdMdl.setTcd010StrikeSyugyouStr(TimecardUtil.getTimeString(
                            tcdMdl.getTcdStrikeOuttime(), 24));
                } else {
                    tcdMdl.setTcd010StrikeSyugyouStr(TimecardUtil.getTimeString(
                            tcdMdl.getTcdStrikeOuttime()));
                }

                tcdMdl.setTcd010Bikou(tcdMdl.getTcdBiko());
                tcdMdl.setTcd010Kyujitsu(
                        TimecardUtil.getKyujitsuStr(tcdMdl.getTcdHolkbn(), reqMdl));
            }
            //カレンダー情報付加
            tcdMdl.setTcd010Ymd(date.getDateString());
            tcdMdl.setTcd010Year(date.getYear());
            tcdMdl.setTcd010Month(date.getMonth());
            tcdMdl.setTcd010Day(date.getIntDay());
            tcdMdl.setTcd010Week(date.getWeek());
            tcdMdl.setTcd010WeekStr(TimecardUtil.toStrWeek(date.getWeek(), reqMdl));
            //休日情報付加
            CmnHolidayModel holMdl = holMap.get(date.getDateString());
            if (holMdl != null) {
                tcdMdl.setHolKbn(1);
                tcdMdl.setHolName(holMdl.getHolName());
            }
            //不正データ判定
            //(システム日付以前で始業時刻から24時間以上経過していて終業時刻が未登録のもの)
            TimecardBiz tcBiz = new TimecardBiz(reqMdl);
            if (tcdMdl.getTcdIntime() != null) {

                if (sysDate.compareDateYMD(tcdMdl.getTcdDate()) == UDate.SMALL
                        && tcdMdl.getTcdOuttime() == null
                        && tcBiz.isOver24Hours(tcdMdl.getTcdDate(), inTime)) {
                    tcdMdl.setFailFlg(GSConstTimecard.DATA_FAIL);
                } else {
                    tcdMdl.setFailFlg(GSConstTimecard.DATA_NOT_FAIL);
                }
            }
            //ロックフラグ制御
            tcdMdl.setTcdLockFlg(GSConstTimecard.UNLOCK_FLG);

            //本日のタイムカードデータ
            if (sysDate.compareDateYMD(date) == UDate.EQUAL) {
                log__.debug("*****現在日付：" + sysDate.getYear() + "年"
                                              + sysDate.getMonth() + "月"
                                              + sysDate.getIntDay() + "日");

                //打刻ボタンの表示設定を行う
                setDakokuBtn(paramMdl, reqMdl, tcdMdl, admConf, con, usrSid);
            }

            ret.add(tcdMdl);
            //日付を進める
            date.addDay(1);
        }

        return ret;
    }

    /**
     * <br>[機  能] 集計モデルに表示用フォーマットを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param ttlMdl 集計モデル
     */
    public void setTotalValueModelDspString(TcdTotalValueModel ttlMdl) {

        ttlMdl.setKadoBaseDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getKadoBaseDays()));
        ttlMdl.setKadoDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getKadoDays()));
        ttlMdl.setKadoBaseHoursStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getKadoBaseHours()));
        ttlMdl.setKadoHoursStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getKadoHours()));
        ttlMdl.setZangyoDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getZangyoDays()));
        ttlMdl.setZangyoHoursStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getZangyoHours()));
        ttlMdl.setSinyaDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getSinyaDays()));
        ttlMdl.setSinyaHoursStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getSinyaHours()));
        ttlMdl.setKyusyutuDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getKyusyutuDays()));
        ttlMdl.setKyusyutuHoursStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getKyusyutuHours()));
        ttlMdl.setChikokuDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getChikokuDays()));
        ttlMdl.setChikokuTimesStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getChikokuTimes()));
        ttlMdl.setSoutaiDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getSoutaiDays()));
        ttlMdl.setSoutaiTimesStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getSoutaiTimes()));
        ttlMdl.setKekkinDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getKekkinDays()));
        ttlMdl.setKeityoDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getKeityoDays()));
        ttlMdl.setYuukyuDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getYuukyuDays()));
        ttlMdl.setDaikyuDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getDaikyuDays()));
        ttlMdl.setSonotaDaysStr(StringUtil.toCommaFromBigDecimal(ttlMdl.getSonotaDays()));
    }

    /**
     * <br>[機  能] 表示年月と締め日を元に表示するカレンダーのfrom～toを設定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param year 表示年
     * @param month 表示月
     * @param sime 締め日
     * @param frDate カレンダー開始日付(設定されます)
     * @return toDate カレンダー終了日付(設定されます)
     */
    public UDate setTimeCardCal(int year, int month, int sime, UDate frDate) {

        UDate toDate = null;
        frDate.setYear(year);
        frDate.setMonth(month);
        frDate.setZeroHhMmSs();

        if (sime == GSConstTimecard.TIMECARD_LIMITDAY[5]) {
            frDate.setDay(1);
            toDate = frDate.cloneUDate();
            toDate.setDay(frDate.getMaxDayOfMonth());
        } else {
            frDate.setDay(sime + 1);
            toDate = frDate.cloneUDate();
            toDate.addMonth(1);
            toDate.setDay(sime);
        }

        return toDate;
    }

    /**
     * <br>[機  能] ユーザの種別が一般・グループ管理者・管理者かを判定します。
     * <br>[解  説]
     * <br>[備  考] 管理者権限を持っている場合はグループ管理者権限よりも優先されます。
     * @param usModel セッションユーザモデル
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return int ユーザ種別
     */
    public int getUserKbn(BaseUserModel usModel, Connection con) throws SQLException {

        CommonBiz commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(con,
                usModel,
                GSConstTimecard.PLUGIN_ID_TIMECARD);

        if (adminUser) {
            return GSConstTimecard.USER_KBN_ADMIN;
        }
        //グループ管理者設定判定
        CmnGroupmDao gpDao = new CmnGroupmDao(con);
        List<CmnGroupmModel> list = gpDao.selectGroupAdmin(usModel.getUsrsid());
        if (list.size() > 0) {
            return GSConstTimecard.USER_KBN_GRPADM;
        }
        return GSConstTimecard.USER_KBN_NORMAL;
    }

    /**
     * <br>[機  能] 表示グループ用のグループリストを取得する
     * <br>[解  説] 管理者設定の共有範囲が「ユーザ全員で共有」の場合有効な全てのグループを取得する。
     * <br>「所属グループ内のみ共有可」の場合、ユーザが所属するグループのみを返す。
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ種別
     * @param reqMdl リクエスト情報
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getGroupLabelList(Connection con,
            int usrSid, int usrKbn, RequestModel reqMdl) throws SQLException {

        List < LabelValueBean > labelList = null;
        GsMessage gsMsg = new GsMessage(reqMdl);

        if (usrKbn == GSConstTimecard.USER_KBN_ADMIN) {
            //管理者
            GroupBiz gpBiz = new GroupBiz();
            labelList = gpBiz.getGroupCombLabelList(con, true, gsMsg);
            labelList.remove(0);
        } else if (usrKbn == GSConstTimecard.USER_KBN_GRPADM) {
            //グループ管理権限をもつグループのみ
            labelList = getGroupLabel(con, reqMdl, usrSid, false, 0);
        } else if (usrKbn == GSConstTimecard.USER_KBN_NORMAL) {
            //一般ユーザ
            labelList = getGroupLabel(con, reqMdl, usrSid, false, 1);

        }
        log__.debug("getGroupLabelList.size()==>" + labelList.size());
        return labelList;
    }

    /**
     * <br>[機  能] 指定グループに所属するユーザリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param groupSid グループSID
     * @return ユーザリスト
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getUserLabelList(Connection con, int groupSid)
    throws SQLException {

        List < LabelValueBean > labelList = null;
        String[] execludeusid = new String[] {
                Integer.toString(GSConst.SYSTEM_USER_ADMIN),
                Integer.toString(GSConst.SYSTEM_USER_MAIL) };
        UserBiz usrBiz = new UserBiz();
        labelList = usrBiz.getUserLabelList(con, groupSid, execludeusid);
        return labelList;
    }

    /**
     * <br>[機  能] グループリストの中にデフォルトグループが存在した場合そのグループSIDを返します。
     * <br>[解  説]
     * <br>[備  考] デフォルトグループが存在しない場合、リストの先頭を返します。
     * @param userSid ユーザSID
     * @param groupLabel グループラベルリスト
     * @param con コネクション
     * @return int グループSID
     * @throws SQLException SQL実行時例外
     */
    public int getInitGpSid(int userSid, List<LabelValueBean> groupLabel, Connection con)
    throws SQLException {

        int ret = 0;
        GroupBiz gpBiz = new GroupBiz();
        int dfGpSid = gpBiz.getDefaultGroupSid(userSid, con);
        LabelValueBean labelBean = null;
        for (int i = 0; i < groupLabel.size(); i++) {
            labelBean = groupLabel.get(i);
            if (Integer.parseInt(labelBean.getValue()) == dfGpSid) {
                ret = dfGpSid;
            }
        }
        if (ret == 0) {
            labelBean = groupLabel.get(0);
            ret = Integer.parseInt(labelBean.getValue());
        }

        log__.debug("getInitGpSid==>" + ret);
        return ret;
    }

    /**
     * <br>[機  能] ユーザリストの中にセッションユーザが存在した場合そのユーザSIDを返します。
     * <br>[解  説]
     * <br>[備  考] セッションユーザが存在しない場合、リストの先頭を返します。
     * @param userSid ユーザSID
     * @param userLabel ユーザラベルリスト
     * @param con コネクション
     * @return int グループSID
     * @throws SQLException SQL実行時例外
     */
    public String getInitUsrSid(int userSid, List<LabelValueBean> userLabel, Connection con)
    throws SQLException {
        String strUserSid = String.valueOf(userSid);
        String ret = "-1";
        if (userLabel == null || userLabel.size() == 0) {
            return ret;
        }
        LabelValueBean labelBean = null;
        for (int i = 0; i < userLabel.size(); i++) {
            labelBean = userLabel.get(i);
            log__.debug("labelBean.getValue()==>" + labelBean.getValue());
            log__.debug("strUserSid==>" + strUserSid);
            if (labelBean.getValue().equals(strUserSid)) {
                ret = strUserSid;
            }
        }
        if (ret.equals("-1")) {
            labelBean = userLabel.get(0);
            ret = labelBean.getValue();
        }
        return ret;
    }

    /**
     * <br>[機  能] タイムカード情報を削除します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @param reqMdl リクエスト情報｣
     * @return int 削除件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteTcdData(Tcd010ParamModel paramMdl, int sessionUsrSid, Connection con,
                            RequestModel reqMdl)
    throws SQLException {
        int ret = 0;

        int usrSid = Integer.parseInt(paramMdl.getUsrSid());
        int year = Integer.parseInt(paramMdl.getYear());
        int month = Integer.parseInt(paramMdl.getMonth());
        String[] days = paramMdl.getSelectDay();
        TimecardBiz biz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admConf = biz.getTcdAdmConfModel(sessionUsrSid, con);

        TcdTcdataDao dao = new TcdTcdataDao(con);
        dao.delete(usrSid, year, month, days, admConf.getTacSimebi(), reqMdl);

        return ret;
    }

    /**
     * <br>[機  能] 勤務表エクセル出力用のタイムカードリストを取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<Tcd010Model> getXlsTimeCardList(
            RequestModel reqMdl,
            Tcd010ParamModel paramMdl,
            Connection con) throws SQLException {

        int targetUserSid = getTargetUserSid(reqMdl, paramMdl, con);

        //基本設定を取得
        TimecardBiz tcBiz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admConf = tcBiz.getTcdAdmConfModel(targetUserSid, con);


        return __getTimeCardList(paramMdl, targetUserSid, admConf, con, reqMdl);
    }

    /**
     * <br>[機  能] Xls出力時の対象ユーザを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return targetUserSid
     * @throws SQLException SQL実行時例外
     */
    public int getTargetUserSid(
            RequestModel reqMdl,
            Tcd010ParamModel paramMdl,
            Connection con) throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int targetUserSid = usModel.getUsrsid(); //セッションユーザSID

        //ユーザ種別を判定 一般・グループ管理者・管理者
        int userKbn = getUserKbn(usModel, con);
        if (GSConstTimecard.USER_KBN_ADMIN == userKbn
                || GSConstTimecard.USER_KBN_GRPADM == userKbn) {
            targetUserSid = Integer.parseInt(paramMdl.getUsrSid());
        }
        return targetUserSid;
    }

    /**
     * <br>[機  能] 現在の年度を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param kishuMonth 期首月
     * @param paramMdl パラメータ情報
     * @return nendYear
     */
    public String getNend(int kishuMonth,
            Tcd010ParamModel paramMdl) {
        int nendYear = 0;
        int thisYear = Integer.parseInt(paramMdl.getYear());
        int nowMonth = Integer.parseInt(paramMdl.getMonth());
        if (nowMonth < kishuMonth) {
            nendYear = thisYear - 1;
        } else {
            nendYear = thisYear;
        }
        return Integer.toString(nendYear);
    }

    /**
     * <br>[機  能] 指定した月のタイムカード集計値を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param sessionUsrSid セッションユーザSID
     * @param admConf 基本設定
     * @param con コネクション
     * @param from 開始日付
     * @return TcdTotalValueModel 集計モデル
     * @throws SQLException SQL実行時例外
     */
    private TcdTotalValueModel __getTotalValue(
            Tcd010ParamModel paramMdl,
            RequestModel reqMdl,
            int sessionUsrSid,
            TcdAdmConfModel admConf,
            Connection con,
            UDate from) throws SQLException {
        int usrSid = Integer.parseInt(paramMdl.getUsrSid());
        int year = from.getYear();
        int month = from.getMonth();
        UDate tdate = setTimeCardCal(year, month, admConf.getTacSimebi(), from);
        TcdTotalValueModel ret
            = getTotalValueModel(usrSid, from, tdate, sessionUsrSid, con, reqMdl);
        return ret;
    }

    /**
     * <br>[機  能] 階層構造を反映させたグループリストを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param userSid ユーザSID
     * @param sentakuFlg true:「非表示」のラベルを作成する, false:作成しない
     * @param mkCmbFlg コンボ作成フラグ 0:グループ管理権限をもつグループのみ 1:一般ユーザ
     * @return GroupModel in ArrayList
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<LabelValueBean> getGroupLabel(Connection con,
                                                    RequestModel reqMdl,
                                                    int userSid,
                                                    boolean sentakuFlg,
                                                    int mkCmbFlg)
    throws SQLException {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        TcdUserGrpDao gpDao = new TcdUserGrpDao(con);

        //グループリストを取得
        List<TcdUserGrpModel> list = new ArrayList<TcdUserGrpModel>();
        if (mkCmbFlg == 0) {
            //グループ管理権限をもつグループのみ
            list = gpDao.selectGroupAdminOrderbyClass(userSid);

        } else if (mkCmbFlg == 1) {
            //一般ユーザ
            list = gpDao.selectGroupDataListOrderbyClass(userSid);
        }

        if (list == null || list.isEmpty()) {
            return null;
        }
        Iterator<TcdUserGrpModel> it = list.iterator();
        ArrayList<GroupModel> grpList = new ArrayList<GroupModel>();

        while (it.hasNext()) {
            TcdUserGrpModel gcmodel = it.next();
            GroupModel gtModel = new GroupModel();

            //グループSIDをセット
            gtModel.setGroupSid(gcmodel.getGroupSid());
            //グループ階層レベル
            gtModel.setClassLevel(gcmodel.getLowGrpLevel());
            //グループ名
            gtModel.setGroupName(__getGroupName(gtModel.getClassLevel(), gcmodel.getGroupName()));

            log__.debug("グループSID = " + gtModel.getGroupSid());
            log__.debug("グループ名(階層反映済) = " + gtModel.getGroupName());
            log__.debug("階層Lv = " + gtModel.getClassLevel());
            grpList.add(gtModel);
        }
        labelList = getGroupDspList(sentakuFlg, reqMdl, grpList);
        return labelList;
    }

    /**
     * <br>[機  能] グループ階層を反映させたグループ名を返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param classLevel グループ階層
     * @param groupName グループ名
     * @return グループ階層を反映させたグループ名
     */
    private String __getGroupName(int classLevel, String groupName) {

        String gpName = "";
        gpName = getCombSpace(classLevel) + groupName;

        return gpName;
    }

    /**
     * <br>[機  能] グループ情報一覧を取得する(コンボボックス用)
     * <br>[解  説] 指定したユーザが属するグループの一覧を取得する
     * <br>         ユーザSID < 0 の場合は全グループの一覧を取得する
     * <br>[備  考]
     * @param level 階層ﾚﾍﾞﾙ
     * @return グループ情報一覧
     */
    public String getCombSpace(int level) {

        String space = "";

        switch (level) {
            case 1:
            default :
                space = "";
                break;
            case 2:
                space = "　";
                break;
            case 3:
                space = "　　";
                break;
            case 4:
                space = "　　　";
                break;
            case 5:
                space = "　　　　";
                break;
            case 6:
                space = "　　　　　";
                break;
            case 7:
                space = "　　　　　　";
                break;
            case 8:
                space = "　　　　　　　";
                break;
            case 9:
                space = "　　　　　　　　";
                break;
            case 10:
                space = "　　　　　　　　　";
                break;
        }


        return space;
    }

    /**
     * <br>[機  能] 表示グループ用のグループリストを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param sentakuFlg true:「選択してください」のラベルを作成する, false:作成しない
     * @param reqMdl リクエスト情報
     * @param gpList グループリスト
     * @return グループラベルのArrayList
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<LabelValueBean> getGroupDspList(boolean sentakuFlg,
                                                 RequestModel reqMdl,
                                                 List<GroupModel> gpList) throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);
        //選択してください
        String textSelect = gsMsg.getMessage("cmn.select.plz");

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        if (sentakuFlg) {
            labelList.add(new LabelValueBean(textSelect, String.valueOf(-1)));
        }

        //グループリスト取得
        for (GroupModel gmodel : gpList) {
            labelList.add(new LabelValueBean(gmodel.getGroupName(), String
                    .valueOf(gmodel.getGroupSid())));
        }
        return labelList;
    }

    /**
     *
     * <br>[機  能] 打刻ボタンの表示設定を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param tcdMdl カレンダーデータ
     * @param admConf 基本設定
     * @param con コネクション
     * @param usrSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void setDakokuBtn(Tcd010ParamModel paramMdl,
                            RequestModel reqMdl,
                            Tcd010Model tcdMdl,
                            TcdAdmConfModel admConf,
                            Connection con,
                            int usrSid) throws SQLException {

        //不正データ処理
        TimecardBiz tcBiz = new TimecardBiz(reqMdl);
        if (tcBiz.isFailDataExist(usrSid, con, admConf)) {
            //不正日付の場合、打刻ボタン非表示
            return;
        }

        boolean inTimeFinFlg = false;
        boolean outTimeFinFlg = false;

        if (tcdMdl.getTcdIntime() != null) {
            inTimeFinFlg = true;
        }
        if (tcdMdl.getTcdOuttime() != null) {
            outTimeFinFlg = true;
        }

        if (!inTimeFinFlg && !outTimeFinFlg) {
            //未打刻のとき
            tcdMdl.setDakokuBtnStrKbn(GSConstTimecard.DAKOKUBTN_DSP_OK);
            tcdMdl.setDakokuBtnEndKbn(GSConstTimecard.DAKOKUBTN_DSP_NOT);

        } else if (inTimeFinFlg && !outTimeFinFlg) {
            //始業のみ打刻されているとき
            tcdMdl.setDakokuBtnStrKbn(GSConstTimecard.DAKOKUBTN_DSP_NOT);
            tcdMdl.setDakokuBtnEndKbn(GSConstTimecard.DAKOKUBTN_DSP_OK);

        } else if (inTimeFinFlg && outTimeFinFlg) {
            //始業・終業時間がすでに打刻されているとき
            tcdMdl.setDakokuBtnStrKbn(GSConstTimecard.DAKOKUBTN_DSP_NOT);
            tcdMdl.setDakokuBtnEndKbn(GSConstTimecard.DAKOKUBTN_DSP_NOT);
        }
    }

    /**
     * <br>[機  能] タイムカードを更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return TcdTcdataModel
     * @throws SQLException SQL実行時例外
     */
    public TcdTcdataModel updateTcd(RequestModel reqMdl, Connection con)
        throws SQLException {

        log__.debug("タイムカード更新");

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int userSid = usModel.getUsrsid();
        TimecardBiz tcBiz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admConf = tcBiz.getTcdAdmConfModel(userSid, con);

        UDate sysDate = new UDate();
        TimecardBiz tcdBiz = new TimecardBiz(reqMdl);
        TcdTcdataModel tcMdl = tcdBiz.getTargetTcddata(userSid, sysDate, con);

        TcdTcdataDao tcDao = new TcdTcdataDao(con);
        if (tcMdl == null) {
            //新規:始業時間登録
            tcMdl = new TcdTcdataModel();
            tcMdl.setUsrSid(userSid);
            UDate tcdDate = __setConvertYmdhm(tcMdl, admConf, true);
            tcMdl.setTcdDate(tcdDate);
            tcMdl.setTcdHolkbn(GSConstTimecard.HOL_KBN_UNSELECT);
            if (tcBiz.isChikoku(tcMdl.getTcdIntime(), con)) {
                log__.debug("tcMdl.getTcdIntime()==>" + tcMdl.getTcdIntime().toString());
                tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_SELECT);
            } else {
                tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_UNSELECT);
            }
            tcMdl.setTcdSoukbn(GSConstTimecard.SOU_KBN_UNSELECT);
            tcMdl.setTcdStatus(GSConstTimecard.TCD_STATUS_MAIN);
            tcMdl.setTcdAuid(userSid);
            tcMdl.setTcdAdate(sysDate);
            tcMdl.setTcdEuid(userSid);
            tcMdl.setTcdEdate(sysDate);
            tcDao.insert(tcMdl);
            //在席状況を更新
            zaisekiUpdate(GSConst.UIOSTS_IN, reqMdl, con);
        } else {
            if (tcMdl.getTcdIntime() == null && tcMdl.getTcdOuttime() == null) {
                //更新:始業時間登録
                __setConvertYmdhm(tcMdl, admConf, true);
                if (tcBiz.isChikoku(tcMdl.getTcdIntime(), con)) {
                    tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_SELECT);
                } else {
                    tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_UNSELECT);
                }
                tcMdl.setTcdStatus(GSConstTimecard.TCD_STATUS_MAIN);
                tcDao.updateDaily(tcMdl);
                //在席状況を更新
                zaisekiUpdate(GSConst.UIOSTS_IN, reqMdl, con);
            } else if (tcMdl.getTcdIntime() != null && tcMdl.getTcdOuttime() == null) {
                //更新:終業時間更新
                __setConvertYmdhm(tcMdl, admConf, false);
                if (tcBiz.isSoutai(tcMdl.getTcdOuttime(), con)) {
                    tcMdl.setTcdSoukbn(GSConstTimecard.SOU_KBN_SELECT);
                } else {
                    tcMdl.setTcdSoukbn(GSConstTimecard.SOU_KBN_UNSELECT);
                }
                tcMdl.setTcdStatus(GSConstTimecard.TCD_STATUS_MAIN);
                tcDao.updateDaily(tcMdl);
                //在席状況を更新
                zaisekiUpdate(GSConst.UIOSTS_LEAVE, reqMdl, con);
            } else if (tcMdl.getTcdIntime() != null && tcMdl.getTcdOuttime() != null) {
                //更新なし
            }

        }
        return tcMdl;
    }

    /**
     * <br>[機  能] 基本設定に従い引数の分をコンバートし、TcdTcdataModelへ設定します。
     * <br>[解  説] 端数は切り上げ
     * <br>[備  考] 例：基本設定：15分間隔 min=16分の場合、30分に変換
     * @param tcMdl 設定先モデル
     * @param admConf 基本設定
     * @param inFlg true:始業時刻に設定 false:終業時刻へ設定
     * @return UDate 設定する日付
     */
    private UDate __setConvertYmdhm(TcdTcdataModel tcMdl, TcdAdmConfModel admConf, boolean inFlg) {

        UDate sysDate = new UDate();
        UDate tcdDate = sysDate.cloneUDate();

        tcdDate.setSecond(0);
        tcdDate.setMilliSecond(0);
        //分を変換
        Time time = null;
        if (inFlg) {
            time = new Time(tcdDate.getTime());

            tcMdl.setTcdIntime(time);
            tcMdl.setTcdStrikeIntime(time);
        } else {
            time = new Time(tcdDate.getTime());

            tcMdl.setTcdOuttime(time);
            tcMdl.setTcdStrikeOuttime(time);
        }

        tcdDate.setZeroHhMmSs();
        log__.debug("tcdDate==>" + tcdDate.toSQLTimestamp());

        return  tcdDate;
    }

    /**
     * <br>[機  能] タイムカードの打刻に合わせて在席状況を更新します。
     * <br>[解  説]
     * <br>[備  考]
     * @param status 更新ステータス
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void zaisekiUpdate(int status, RequestModel reqMdl, Connection con)
    throws SQLException {
        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int userSid = usModel.getUsrsid();

        UDate now = new UDate();
        CmnUsrInoutModel param = new CmnUsrInoutModel();
        param.setUioSid(userSid);
        param.setUioStatus(status);
        param.setUioBiko("");
        param.setUioAuid(userSid);
        param.setUioAdate(now);
        param.setUioEuid(userSid);
        param.setUioEdate(now);

        ZsjCommonBiz zbiz = new ZsjCommonBiz(reqMdl);
        zbiz.updateZskStatus(con, param);
    }
}
