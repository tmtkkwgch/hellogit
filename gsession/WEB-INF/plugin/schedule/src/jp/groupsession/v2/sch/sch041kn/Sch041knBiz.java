package jp.groupsession.v2.sch.sch041kn;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnHolidayDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.dao.base.TcdAdmConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnHolidayModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.SaibanModel;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.man.MaintenanceUtil;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.dao.RsvSisKryrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisKyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisRyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.rsv.model.RsvSisKyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisRyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisYrkModel;
import jp.groupsession.v2.rsv.rsv070.Rsv070Model;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchAddressDao;
import jp.groupsession.v2.sch.dao.SchCompanyDao;
import jp.groupsession.v2.sch.dao.SchDataDao;
import jp.groupsession.v2.sch.dao.SchExaddressDao;
import jp.groupsession.v2.sch.dao.SchExcompanyDao;
import jp.groupsession.v2.sch.dao.SchExdataDao;
import jp.groupsession.v2.sch.dao.ScheduleReserveDao;
import jp.groupsession.v2.sch.dao.ScheduleSearchDao;
import jp.groupsession.v2.sch.model.SchAddressModel;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchCompanyModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.SchExaddressModel;
import jp.groupsession.v2.sch.model.SchExcompanyModel;
import jp.groupsession.v2.sch.model.SchExdataModel;
import jp.groupsession.v2.sch.model.ScheduleExSearchModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.sch.sch040.Sch040Biz;
import jp.groupsession.v2.sch.sch040.Sch040Dao;
import jp.groupsession.v2.sch.sch040.model.Sch040ContactModel;
import jp.groupsession.v2.sch.sch041.Sch041DateSearchModel;
import jp.groupsession.v2.sch.sch041.Sch041DateSortModel;
import jp.groupsession.v2.sch.sch041.Sch041Form;
import jp.groupsession.v2.sch.sch041.Sch041ParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] スケジュール繰り返し登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch041knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch041knBiz.class);
    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    public Sch041knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示画面情報を取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch041knParamModel
     * @param pconfig プラグインコンフィグ
     * @param con コネクション
     * @return アクションフォーム
     * @throws SQLException SQL実行時例外
     */
    public Sch041knParamModel getInitData(Sch041knParamModel paramMdl,
                                    PluginConfig pconfig, Connection con) throws SQLException {

        //アドレス帳使用有無
        if (pconfig.getPlugin("address") != null) {
            paramMdl.setAddressPluginKbn(GSConstSchedule.PLUGIN_USE);
            log__.debug("アドレス帳使用");
        } else {
            paramMdl.setAddressPluginKbn(GSConstSchedule.PLUGIN_NOT_USE);
            log__.debug("アドレス帳使用不可");
        }

        //WEB検索使用有無
        if (pconfig.getPlugin("search") != null) {
            paramMdl.setSearchPluginKbn(GSConstSchedule.PLUGIN_USE);
            log__.debug("WEB検索使用");
        } else {
            paramMdl.setSearchPluginKbn(GSConstSchedule.PLUGIN_NOT_USE);
            log__.debug("WEB検索使用不可");
        }

        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //名前
        String uid = paramMdl.getSch010SelectUsrSid();
        String ukb = paramMdl.getSch010SelectUsrKbn();
        log__.debug("uid=" + uid);
        log__.debug("ukb=" + ukb);
        paramMdl.setSch040UsrName(getUsrName(Integer.parseInt(uid), Integer.parseInt(ukb), con));
        //登録者
        paramMdl.setSch040AddUsrName(usModel.getUsisei() + " " + usModel.getUsimei());
        //設定期間・時間
        __setSch041knFormFromReq(paramMdl);
        //登録予定日付
        HashMap<String, UDate> addDateMap = getInsertDateList(paramMdl, sessionUsrSid, con);
        paramMdl.setSch041knAftDateList(__getStrDateListFromMap(addDateMap));
        //同時登録ユーザ名リスト
        CmnUsrmInfDao uinfDao = new CmnUsrmInfDao(con);
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        usrSids.add(new Integer(GSConstUser.SID_ADMIN));
        usrSids.add(new Integer(uid));
        ArrayList < Integer > list = null;
        ArrayList < CmnUsrmInfModel > selectUsrList = null;
        String[] users = null;
        if (paramMdl.getCmd().equals(GSConstSchedule.CMD_DELETE)) {
            //削除確認用にDBスケジュール情報を設定
            users = __setSch041knFormFromDb(paramMdl, con);
        } else {
            users = paramMdl.getSch041SvUsers();
        }

        if (users != null && users.length > 0) {
            list = new ArrayList<Integer>();
            for (int i = 0; i < users.length; i++) {
                list.add(new Integer(users[i]));
                //同時登録ユーザを所属リストから除外する
                usrSids.add(new Integer(users[i]));
            }
            selectUsrList = uinfDao.getUserList(list);
        }
        paramMdl.setSch041knSelectUsrList(selectUsrList);

        String scdSid = paramMdl.getSch010SchSid();
        //スケジュール情報
        if (!paramMdl.getCmd().equals(GSConstSchedule.CMD_ADD)) {
            //登録済み日付
            ArrayList<String> oldDateList =
                getOldDateList(Integer.parseInt(scdSid), sessionUsrSid, con);
            paramMdl.setSch041knBefDateList(oldDateList);
        }
        //施設予約
        String[] reserves = null;
        if (paramMdl.getCmd().equals(GSConstSchedule.CMD_DELETE)) {
            reserves = setSch041knReservesFromDb(paramMdl, con);
        } else {
            reserves = paramMdl.getSch041SvReserve();
        }
        paramMdl.setSch041knReserveList(getReserveNameList(reserves, con));

        //会社情報を設定
        Sch040Biz biz040 = new Sch040Biz(con, reqMdl__);
        biz040.setCompanyData(paramMdl, con, sessionUsrSid, reqMdl__);

        //ボタン用の処理モードを設定する。
        String cmd = paramMdl.getCmd();
        String btnCmd = "";
        if (!StringUtil.isNullZeroStringSpace(cmd)) {
            btnCmd = StringUtil.toSingleCortationEscape(cmd);
        }
        paramMdl.setSch040BtnCmd(btnCmd);

        return paramMdl;
    }

    /**
     * <br>[機  能] 施設SIDの配列から施設名のリストを生成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param reserves 施設SID配列
     * @param con コネクション
     * @return ArrayList 施設リスト
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<RsvSisDataModel> getReserveNameList(String[] reserves, Connection con)
    throws SQLException {
        ArrayList<RsvSisDataModel> ret = null;
        RsvSisDataDao rsDao = new RsvSisDataDao(con);
        ret = rsDao.selectSisetuList(reserves);
        return ret;
    }

    /**
     * <br>[機  能] Mapに格納されている日付情報をArrayList(in String(yyyy年mm月dd日形式))に置き換えます。
     * <br>[解  説]
     * <br>[備  考]
     * @param map 日付情報
     * @return ArrayList 格納しなおした日付情報
     */
    private ArrayList<String> __getStrDateListFromMap(HashMap<String, UDate> map) {

        ArrayList<UDate> col = new ArrayList<UDate>(map.values());
        ArrayList<Sch041DateSortModel> sort = new ArrayList<Sch041DateSortModel>();
        Sch041DateSortModel sortMdl = null;
        for (UDate date : col) {
            sortMdl = new Sch041DateSortModel();
            sortMdl.setUdate(date);
            sort.add(sortMdl);
        }
        Collections.sort(sort);

        ArrayList<String> ret = new ArrayList<String>();
        for (Sch041DateSortModel model : sort) {
            ret.add(UDateUtil.getYymdJwithStrWeek(model.getUdate(), reqMdl__));
        }

        return ret;
    }

    /**
     * <br>[機  能] 日付指定条件に該当する日付リストを取得します。
     * <br>[解  説] 営業日の判定にはタイムカード基本設定を使用します
     * <br>[備  考]
     * @param model 抽出条件
     * @param sessionUsrSid セッションユーザ
     * @param con コネクション
     * @return HashMap ケジュールを登録する日付MAP
     * @throws SQLException SQL実行時例外
     */
    public HashMap<String, UDate> getDateList(
            Sch041DateSearchModel model, int sessionUsrSid, Connection con)
    throws SQLException {

        UDate frDate = model.getFromDate();
        UDate toDate = model.getToDate();

        if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_YEAR))) {
            int iMonth = NullDefault.getInt(model.getMonthOfYearly(), 0);
            int iDay = NullDefault.getInt(model.getDayOfYearly(), 0);

            if (frDate.getMonth() > iMonth
                    || (frDate.getIntDay() > iDay
                            && frDate.getMonth() == iMonth)) {
                frDate.addYear(1);
            }
            frDate.setMonth(iMonth);
            frDate.setDay(iDay);
        }

        //営業日情報を取得する
        CmnHolidayDao holDao = new CmnHolidayDao(con);
        HashMap<String, CmnHolidayModel> holMap = holDao.getHoliDayListFotTcd(frDate, toDate);
        TcdAdmConfModel acMdl = getTcdAdmConfModel(sessionUsrSid, con);

        HashMap<String, UDate> dateMap = new HashMap<String, UDate>();
        UDate setDate = null;
        log__.debug("frDate.compareDateYMD(toDate)=>" + frDate.compareDateYMD(toDate));
        while (frDate.compareDateYMD(toDate) != UDate.SMALL) {
            //抽出条件に該当するか判定
            if (__isMatchCondition(frDate, model)) {
                //振替判定
                setDate = __getConvertDate(frDate, model, holMap, acMdl);
                if (setDate != null) {
                    dateMap.put(setDate.getDateString(), setDate);
                    log__.debug("登録日付==>" + setDate.getDateString());
                }
            }
            if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_YEAR))) {
                frDate.addYear(1);
            } else {
                frDate.addDay(1);
            }
        }

        return dateMap;
    }

    /**
     * <br>[機  能] 日付指定条件に該当する日付リストを取得します。
     * <br>[解  説] 営業日の判定にはタイムカード基本設定を使用します
     * <br>[備  考]
     * @param model 抽出条件
     * @param sessionUsrSid セッションユーザ
     * @param con コネクション
     * @return HashMap ケジュールを登録する日付MAP
     * @throws SQLException SQL実行時例外
     */
    public HashMap<String, String> getStrDateList(
            Sch041DateSearchModel model, int sessionUsrSid, Connection con)
    throws SQLException {

        UDate frDate = model.getFromDate();
        UDate toDate = model.getToDate();
        //営業日情報を取得する
        CmnHolidayDao holDao = new CmnHolidayDao(con);
        HashMap<String, CmnHolidayModel> holMap = holDao.getHoliDayListFotTcd(frDate, toDate);
        TcdAdmConfModel acMdl = getTcdAdmConfModel(sessionUsrSid, con);

        HashMap<String, String> dateMap = new HashMap<String, String>();
        UDate setDate = null;
        while (frDate.compareDateYMD(toDate) != UDate.SMALL) {
            //抽出条件に該当するか判定
            if (__isMatchCondition(frDate, model)) {
                //振替判定
                setDate = __getConvertDate(frDate, model, holMap, acMdl);
                if (setDate != null) {
                    dateMap.put(setDate.getDateString(), setDate.getDateString());
                    log__.debug("登録日付==>" + setDate.getDateString());
                }
            }
            frDate.addDay(1);
        }

        return dateMap;
    }

    /**
     * <br>[機  能] 営業日判定を行い非営業日の場合、振替設定によって日付をコンバートします。
     * <br>[解  説]
     * <br>[備  考]
     * @param date 日付
     * @param model 抽出条件
     * @param holMap 休日情報
     * @param acMdl タイムカード管理設定
     * @return UDate コンバート結果
     */
    private UDate __getConvertDate(
            UDate date,
            Sch041DateSearchModel model,
            HashMap<String, CmnHolidayModel> holMap,
            TcdAdmConfModel acMdl) {

        UDate ret = date.cloneUDate();
        int tran = Integer.parseInt(model.getTranKbn());
        log__.debug("振替区分==>" + tran);
        int addDay = 0;
        if (tran == GSConstSchedule.FURIKAE_KBN_AFT) {
            addDay = 1;
        } else if (tran == GSConstSchedule.FURIKAE_KBN_BEF) {
            addDay = -1;
        } else if (tran == GSConstSchedule.FURIKAE_KBN_NOADD) {
            addDay = 0;
        } else {
            return ret;
        }

        //休日として扱う曜日か判定
        boolean fin = true;
        while (fin) {
            if (__isMatchWeek(ret.getWeek(), acMdl)
             || holMap.containsKey(ret.getDateString())) {
                log__.debug("休日として認識==>" + ret.getDateString());
                //休日は登録しない場合
                if (tran == GSConstSchedule.FURIKAE_KBN_NOADD) {
                    return null;
                }
                ret.addDay(addDay);
            } else {
                fin = false;
            }
        }

        return ret;
    }

    /**
     * <br>[機  能] 抽出条件に該当する日付か判定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param date 判定対象の日付
     * @param model 抽出条件
     * @return boolean true:該当 false:該当しない
     */
    private boolean __isMatchCondition(
            UDate date,
            Sch041DateSearchModel model) {

        if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_DAY))) {
            //毎日
            return true;
        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_WEEK))) {
            //毎週
            return __isMatchWeek(date.getWeek(), model);

        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_MONTH))) {
            //毎月
            if (model.getWeekOfMonthly() != null) {
                //週・曜日指定
                int weekNo = Integer.parseInt(model.getWeekOfMonthly());
                if (__isMatchWeek(date.getWeek(), model)) {
                    UDate wkDate = date.cloneUDate();
                    int wkWeekOfMonth
                        = MaintenanceUtil.getAccurateWeekOfMonth(
                                                        wkDate, wkDate.getWeek());
                    log__.debug("wkDate==>" + wkDate.getDateString());
                    log__.debug("weekNo==>" + weekNo);
                    log__.debug("wkWeekOfMonth==>" + wkWeekOfMonth);
                    if (weekNo == wkWeekOfMonth) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                //日指定
                String monthly = NullDefault.getString(model.getDayOfMonthly(), "");
                return CommonBiz.getExDay(date, monthly).equals(String.valueOf(date.getIntDay()));
            }
        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_YEAR))) {
            //毎年
            String yearly = NullDefault.getString(model.getDayOfYearly(), "");
            return (model.getMonthOfYearly().equals(String.valueOf(date.getMonth()))
                    && CommonBiz.getExDay(date, yearly).equals(String.valueOf(date.getIntDay())));
        } else {
            return false;
        }

    }

    /**
     * <br>[機  能] 指定した曜日が指定されているか判定します
     * <br>[解  説]
     * <br>[備  考]
     * @param week 週
     * @param acMdl 管理設定
     * @return true:休日曜日　false:休日曜日以外
     */
    private boolean __isMatchWeek(int week, TcdAdmConfModel acMdl) {
        switch (week) {
        case UDate.SUNDAY:
            if (acMdl.getTacHolSun() == 0) {
                return false;
            }
            break;
        case UDate.MONDAY:
            if (acMdl.getTacHolMon() == 0) {
                return false;
            }
            break;
        case UDate.TUESDAY:
            if (acMdl.getTacHolTue() == 0) {
                return false;
            }
            break;
        case UDate.WEDNESDAY:
            if (acMdl.getTacHolWed() == 0) {
                return false;
            }
            break;
        case UDate.THURSDAY:
            if (acMdl.getTacHolThu() == 0) {
                return false;
            }
            break;
        case UDate.FRIDAY:
            if (acMdl.getTacHolFri() == 0) {
                return false;
            }
            break;
        case UDate.SATURDAY:
            if (acMdl.getTacHolSat() == 0) {
                return false;
            }
            break;
        default:
            return false;
        }
        return true;
    }

    /**
     * <br>[機  能] 指定した曜日が指定されているか判定します
     * <br>[解  説]
     * <br>[備  考]
     * @param week 週
     * @param model 抽出条件
     * @return true:指定されている　false:指定されていない
     */
    private boolean __isMatchWeek(int week, Sch041DateSearchModel model) {
        switch (week) {
        case UDate.SUNDAY:
            if (model.getWeekOfWeekly1() == null) {
                return false;
            }
            break;
        case UDate.MONDAY:
            if (model.getWeekOfWeekly2() == null) {
                return false;
            }
            break;
        case UDate.TUESDAY:
            if (model.getWeekOfWeekly3() == null) {
                return false;
            }
            break;
        case UDate.WEDNESDAY:
            if (model.getWeekOfWeekly4() == null) {
                return false;
            }
            break;
        case UDate.THURSDAY:
            if (model.getWeekOfWeekly5() == null) {
                return false;
            }
            break;
        case UDate.FRIDAY:
            if (model.getWeekOfWeekly6() == null) {
                return false;
            }
            break;
        case UDate.SATURDAY:
            if (model.getWeekOfWeekly7() == null) {
                return false;
            }
            break;
        default:
            return false;
        }
        return true;
    }

    /**
     * <br>[機  能] 画面パラメータからスケジュールを登録する日付リストを取得します。
     * <br>[解  説] 営業日の判定にはタイムカード基本設定を使用します
     * <br>[備  考]
     * @param paramMdl Sch041knParamModel
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @return HashMap ケジュールを登録する日付MAP
     * @throws SQLException SQL実行時例外
     */
    public HashMap<String, UDate> getInsertDateList(
            Sch041ParamModel paramMdl, int sessionUsrSid, Connection con)
    throws SQLException {

        Sch041DateSearchModel model = new Sch041DateSearchModel();
        //抽出区分
        model.setDateKbn(paramMdl.getSch041ExtKbn());

        if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_DAY))) {
            model.setTranKbn(paramMdl.getSch041TranKbn());
        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_WEEK))) {
            __setWeekOfWeekly(paramMdl.getSch041Dweek(), model);
            model.setTranKbn(paramMdl.getSch041TranKbn());
        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_MONTH))) {
            if (!paramMdl.getSch041Week().equals("0")) {
                __setWeekOfWeekly(paramMdl.getSch041Dweek(), model);
                model.setWeekOfMonthly(paramMdl.getSch041Week());
            } else {
                model.setDayOfMonthly(paramMdl.getSch041Day());
            }
            model.setTranKbn(paramMdl.getSch041TranKbn());
        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_YEAR))) {
           model.setDayOfYearly(paramMdl.getSch041DayOfYearly());
           model.setMonthOfYearly(paramMdl.getSch041MonthOfYearly());
           model.setTranKbn(paramMdl.getSch041TranKbn());
        }
        //抽出期間
        UDate frDate = new UDate();
        frDate.setYear(Integer.parseInt(paramMdl.getSch041FrYear()));
        frDate.setMonth(Integer.parseInt(paramMdl.getSch041FrMonth()));
        frDate.setDay(Integer.parseInt(paramMdl.getSch041FrDay()));
        frDate.setZeroHhMmSs();
        model.setFromDate(frDate);
        UDate toDate = new UDate();
        toDate.setYear(Integer.parseInt(paramMdl.getSch041ToYear()));
        toDate.setMonth(Integer.parseInt(paramMdl.getSch041ToMonth()));
        toDate.setDay(Integer.parseInt(paramMdl.getSch041ToDay()));
        toDate.setZeroHhMmSs();
        model.setToDate(toDate);
        //日付リストを取得
        HashMap<String, UDate> dateMap = getDateList(model, sessionUsrSid, con);

        return dateMap;
    }

    /**
     * <br>[機  能] 画面パラメータからスケジュールを登録する日付リストを取得します。
     * <br>[解  説] 営業日の判定にはタイムカード基本設定を使用します
     * <br>[備  考]
     * @param paramMdl Sch041knParamModel
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @return HashMap ケジュールを登録する日付MAP
     * @throws SQLException SQL実行時例外
     */
    public HashMap<String, UDate> getInsertDateList(
            Sch041Form paramMdl, int sessionUsrSid, Connection con)
    throws SQLException {
        Sch041DateSearchModel model = new Sch041DateSearchModel();
        //抽出区分
        model.setDateKbn(paramMdl.getSch041ExtKbn());
        model.setTranKbn(String.valueOf(GSConstSchedule.FURIKAE_KBN_NONE));
        if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_WEEK))) {
            __setWeekOfWeekly(paramMdl.getSch041Dweek(), model);
            model.setTranKbn(paramMdl.getSch041TranKbn());
        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_MONTH))) {
            if (!paramMdl.getSch041Week().equals("0")) {
                __setWeekOfWeekly(paramMdl.getSch041Dweek(), model);
                model.setWeekOfMonthly(paramMdl.getSch041Week());
            } else {
                model.setDayOfMonthly(paramMdl.getSch041Day());
            }
            model.setTranKbn(paramMdl.getSch041TranKbn());
        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_YEAR))) {
            model.setDayOfYearly(paramMdl.getSch041DayOfYearly());
            model.setMonthOfYearly(paramMdl.getSch041MonthOfYearly());
            model.setTranKbn(paramMdl.getSch041TranKbn());
        }
        //抽出期間
        UDate frDate = new UDate();
        frDate.setYear(Integer.parseInt(paramMdl.getSch041FrYear()));
        frDate.setMonth(Integer.parseInt(paramMdl.getSch041FrMonth()));
        frDate.setDay(Integer.parseInt(paramMdl.getSch041FrDay()));
        frDate.setZeroHhMmSs();
        model.setFromDate(frDate);
        UDate toDate = new UDate();
        toDate.setYear(Integer.parseInt(paramMdl.getSch041ToYear()));
        toDate.setMonth(Integer.parseInt(paramMdl.getSch041ToMonth()));
        toDate.setDay(Integer.parseInt(paramMdl.getSch041ToDay()));
        toDate.setZeroHhMmSs();
        model.setToDate(toDate);
        //日付リストを取得
        HashMap<String, UDate> dateMap = getDateList(model, sessionUsrSid, con);

        return dateMap;
    }

    /**
     * <br>[機  能] 画面パラメータからスケジュールを登録する日付リストを取得します。
     * <br>[解  説] 営業日の判定にはタイムカード基本設定を使用します
     * <br>[備  考]
     * @param paramMdl Sch041knParamModel
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @return HashMap ケジュールを登録する日付MAP
     * @throws SQLException SQL実行時例外
     */
    public HashMap<String, String> getInsertStrDateList(
            Sch041ParamModel paramMdl, int sessionUsrSid, Connection con)
    throws SQLException {
        Sch041DateSearchModel model = new Sch041DateSearchModel();
        //抽出区分
        model.setDateKbn(paramMdl.getSch041ExtKbn());
        model.setTranKbn(String.valueOf(GSConstSchedule.FURIKAE_KBN_NONE));
        if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_WEEK))) {
            __setWeekOfWeekly(paramMdl.getSch041Dweek(), model);
            model.setTranKbn(paramMdl.getSch041TranKbn());
        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_MONTH))) {
            if (!paramMdl.getSch041Week().equals("0")) {
                __setWeekOfWeekly(paramMdl.getSch041Dweek(), model);
                model.setWeekOfMonthly(paramMdl.getSch041Week());
            } else {
                model.setDayOfMonthly(paramMdl.getSch041Day());
            }
            model.setTranKbn(paramMdl.getSch041TranKbn());
        } else if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_YEAR))) {
            model.setDayOfYearly(paramMdl.getSch041DayOfYearly());
            model.setMonthOfYearly(paramMdl.getSch041MonthOfYearly());
            model.setTranKbn(paramMdl.getSch041TranKbn());
        }
        //抽出期間
        UDate frDate = new UDate();
        frDate.setYear(Integer.parseInt(paramMdl.getSch041FrYear()));
        frDate.setMonth(Integer.parseInt(paramMdl.getSch041FrMonth()));
        frDate.setDay(Integer.parseInt(paramMdl.getSch041FrDay()));
        frDate.setZeroHhMmSs();
        model.setFromDate(frDate);
        UDate toDate = new UDate();
        toDate.setYear(Integer.parseInt(paramMdl.getSch041ToYear()));
        toDate.setMonth(Integer.parseInt(paramMdl.getSch041ToMonth()));
        toDate.setDay(Integer.parseInt(paramMdl.getSch041ToDay()));
        toDate.setZeroHhMmSs();
        model.setToDate(toDate);
        //日付リストを取得
        HashMap<String, String> dateMap = getStrDateList(model, sessionUsrSid, con);

        return dateMap;
    }

    /**
     * <br>[機  能] スケジュールSIDから登録済スケジュールの日付リストを取得します。
     * <br>[解  説] 拡張情報が未登録の場合はsize=0のリストを返します。
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @return ArrayList ケジュールを登録する日付リスト
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<String> getOldDateList(int scdSid, int sessionUsrSid, Connection con)
    throws SQLException {

        ArrayList<String> ret = new ArrayList<String>();
        //管理者設定を取得
        SchCommonBiz cBiz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = cBiz.getAdmConfModel(con);

        HashMap<String, UDate> dateMap = null;
        Sch040Biz biz = new Sch040Biz(con, reqMdl__);
        ScheduleSearchModel scdMdl = biz.getSchData(scdSid, adminConf, con);
        if (scdMdl == null) {
            return ret;
        }
        ScheduleExSearchModel sceMdl = biz.getSchExData(scdSid, adminConf, con);
        if (sceMdl != null) {
            //拡張登録から登録日付を設定
            Sch041DateSearchModel model = new Sch041DateSearchModel();
            //抽出区分
            model.setDateKbn(String.valueOf(sceMdl.getSceKbn()));
            model.setTranKbn(String.valueOf(GSConstSchedule.FURIKAE_KBN_NONE));

            if (model.getDateKbn().equals(String.valueOf(GSConstSchedule.EXTEND_KBN_WEEK))) {
                String[] weeks = __getDayWeekStringList(sceMdl);
                __setWeekOfWeekly(weeks, model);
                model.setTranKbn(String.valueOf(sceMdl.getSceTranKbn()));
            } else if (model.getDateKbn().equals(
                    String.valueOf(GSConstSchedule.EXTEND_KBN_MONTH))) {
                if (sceMdl.getSceWeek() != 0) {
                    String[] weeks = __getDayWeekStringList(sceMdl);
                    __setWeekOfWeekly(weeks, model);
                    model.setWeekOfMonthly(String.valueOf(sceMdl.getSceWeek()));
                } else {
                    model.setDayOfMonthly(String.valueOf(sceMdl.getSceDay()));
                }
                model.setTranKbn(String.valueOf(sceMdl.getSceTranKbn()));
            } else if (model.getDateKbn().equals(
                    String.valueOf(GSConstSchedule.EXTEND_KBN_YEAR))) {
                model.setDayOfYearly(String.valueOf(sceMdl.getSceDayOfYearly()));
                model.setMonthOfYearly(String.valueOf(sceMdl.getSceMonthOfYearly()));
                model.setTranKbn(String.valueOf(sceMdl.getSceTranKbn()));

            }
            //抽出期間
            model.setFromDate(sceMdl.getSceDateFr());
            model.setToDate(sceMdl.getSceDateTo());
            //日付リストを取得
            dateMap = getDateList(model, sessionUsrSid, con);
            ret = __getStrDateListFromMap(dateMap);
        } else {
            String frDate = UDateUtil.getYymdJ(scdMdl.getScdFrDate(), reqMdl__);
            String toDate = UDateUtil.getYymdJ(scdMdl.getScdToDate(), reqMdl__);
            ret.add(frDate + "～" + toDate);
        }
        return ret;
    }

    /**
     * <br>[機  能] 曜日を抽出用モデルに設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param weeks 曜日指定
     * @param model 抽出用モデル
     */
    private void __setWeekOfWeekly(String[] weeks, Sch041DateSearchModel model) {
        if (weeks == null) {
            return;
        }

        for (String week : weeks) {
            int intWeek = Integer.parseInt(week);
            switch (intWeek) {
            case 1:
                model.setWeekOfWeekly1("1");
                break;
            case 2:
                model.setWeekOfWeekly2("1");
                break;
            case 3:
                model.setWeekOfWeekly3("1");
                break;
            case 4:
                model.setWeekOfWeekly4("1");
                break;
            case 5:
                model.setWeekOfWeekly5("1");
                break;
            case 6:
                model.setWeekOfWeekly6("1");
                break;
            case 7:
                model.setWeekOfWeekly7("1");
                break;
            default:
                break;
            }
        }
    }

    /**
     *
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
     * <br>リクエストパラメータから画面項目を設定する
     * @param paramMdl Sch041knParamModel
     */
    private void __setSch041knFormFromReq(Sch041knParamModel paramMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textMonth = gsMsg.getMessage("cmn.month");
        String textDay = gsMsg.getMessage("cmn.day");
        //開始日時
        StringBuilder frBuf = new StringBuilder();
        StringBuilder toBuf = new StringBuilder();
        frBuf.append(gsMsg.getMessage("cmn.year", new String[] {paramMdl.getSch041FrYear()}));
        frBuf.append(paramMdl.getSch041FrMonth());
        frBuf.append(textMonth);
        frBuf.append(paramMdl.getSch041FrDay());
        frBuf.append(textDay);

        toBuf.append(gsMsg.getMessage("cmn.year", new String[] {paramMdl.getSch041ToYear()}));
        toBuf.append(paramMdl.getSch041ToMonth());
        toBuf.append(textMonth);
        toBuf.append(paramMdl.getSch041ToDay());
        toBuf.append(textDay);

        StringBuilder frTimeBuf = new StringBuilder();
        StringBuilder toTimeBuf = new StringBuilder();
        //時間指定無し判定
        if (paramMdl.getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            //時
            //From時間指定有り
            if (!paramMdl.getSch041FrHour().equals("-1")
             && !paramMdl.getSch041FrMin().equals("-1")) {
                String[] params = {paramMdl.getSch041FrHour(),
                        StringUtil.toDecFormat(paramMdl.getSch041FrMin(), "00")};
                frTimeBuf.append(gsMsg.getMessage("cmn.time.input", params));
            } else {
                //省略
                String[] params = {"0", "00"};
                frTimeBuf.append(gsMsg.getMessage("cmn.time.input", params));
            }
            //To時間指定有り
            if (!paramMdl.getSch041ToHour().equals("-1")
             && !paramMdl.getSch041ToMin().equals("-1")) {
                String[] params = {paramMdl.getSch041ToHour(),
                        StringUtil.toDecFormat(paramMdl.getSch041ToMin(), "00")};
                toTimeBuf.append(gsMsg.getMessage("cmn.time.input", params));
            } else {
                //省略
                String[] params = {"23", "59"};
                toTimeBuf.append(gsMsg.getMessage("cmn.time.input", params));
            }
        }

        //開始日時
        paramMdl.setSch041knFromDate(frBuf.toString());
        //終了日時
        paramMdl.setSch041knToDate(toBuf.toString());
        //開始時間
        paramMdl.setSch041knFromTime(frTimeBuf.toString());
        //終了時間
        paramMdl.setSch041knToTime(toTimeBuf.toString());
        //内容
        paramMdl.setSch041knDspValue(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSch041Value()));
        //備考
        paramMdl.setSch041knDspBiko(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSch041Biko()));
    }

    /**
     * <br>スケジュール拡張情報から削除確認用画面項目を設定する
     * @param paramMdl Sch041knParamModel
     * @param con コネクション
     * @return String[] 同時登録ユーザ
     * @throws SQLException SQL実行時例外
     */
    private String[] __setSch041knFormFromDb(Sch041knParamModel paramMdl, Connection con)
    throws SQLException {

        String[] users = null;
        int scdSid = NullDefault.getInt(paramMdl.getSch010SchSid(), -1);
        //管理者設定を取得
        SchCommonBiz cBiz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = cBiz.getAdmConfModel(con);
        Sch040Biz biz = new Sch040Biz(con, reqMdl__);
        ScheduleSearchModel scdMdl = biz.getSchData(scdSid, adminConf, con);
        if (scdMdl == null) {
            return null;
        }
        ScheduleExSearchModel sceMdl = biz.getSchExData(scdSid, adminConf, con);

        if (sceMdl != null) {
            String frDate = UDateUtil.getYymdJ(sceMdl.getSceDateFr(), reqMdl__);
            String toDate = UDateUtil.getYymdJ(sceMdl.getSceDateTo(), reqMdl__);
            //開始日時
            paramMdl.setSch041knDelFrDate(frDate);
            //終了日時
            paramMdl.setSch041knDelToDate(toDate);

            String frTime = UDateUtil.getSeparateHMJ(sceMdl.getSceTimeFr(), reqMdl__);
            String toTime = UDateUtil.getSeparateHMJ(sceMdl.getSceTimeTo(), reqMdl__);
            //開始時間
            paramMdl.setSch041knDelFrTime(frTime);
            //終了時間
            paramMdl.setSch041knDelToTime(toTime);
            //タイトル
            paramMdl.setSch041knDelTitle(sceMdl.getSceTitle());
            //タイトル色
            paramMdl.setSch041knDelBgcolor(String.valueOf(sceMdl.getSceBgcolor()));
            //内容
            paramMdl.setSch041knDelValue(
                    StringUtilHtml.transToHTmlPlusAmparsant(sceMdl.getSceValue()));
            //備考
            paramMdl.setSch041knDelBiko(
                    StringUtilHtml.transToHTmlPlusAmparsant(sceMdl.getSceBiko()));
            //公開
            paramMdl.setSch041knDelPublic(String.valueOf(sceMdl.getScePublic()));
            //振替
            paramMdl.setSch041knDelTranKbn(String.valueOf(sceMdl.getSceTranKbn()));
            //編集
            paramMdl.setSch041knDelEdit(String.valueOf(sceMdl.getSceEdit()));

            //同時登録ユーザ一覧
            users = biz.getSaveUsersForDbEx(sceMdl.getUsrInfList());
        }
        return users;
    }

    /**
     * <br>[機  能] 削除時の確認画面用にDBに登録されている施設予約情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return String[] 施設SIDの配列
     */
    public String[] setSch041knReservesFromDb(Sch041knParamModel paramMdl, Connection con)
    throws SQLException {
        String[] reserves = null;

        //管理者設定を取得
        SchCommonBiz cBiz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = cBiz.getAdmConfModel(con);
        int scdSid = Integer.parseInt(paramMdl.getSch010SchSid());
        Sch040Biz biz = new Sch040Biz(con, reqMdl__);
        ScheduleSearchModel scdMdl = biz.getSchData(scdSid, adminConf, con);
        if (scdMdl == null) {
            return null;
        }
        int sceSid = scdMdl.getSceSid();
        reserves = biz.getSaveReserveForDbEx(sceSid, con);

        return reserves;
    }

    /**
     * <br>ユーザSIDとユーザ区分からユーザ氏名を取得する
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ区分
     * @param con コネクション
     * @return String ユーザ氏名
     * @throws SQLException SQL実行時例外
     */
    public String getUsrName(int usrSid, int usrKbn, Connection con)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String ret = "";
        if (usrKbn == GSConstSchedule.USER_KBN_GROUP) {
            if (usrSid == GSConstSchedule.SCHEDULE_GROUP) {
                //グループ
                String textGroup = gsMsg.getMessage("cmn.group");
                ret = textGroup;
            } else {
                GroupDao grpDao = new GroupDao(con);
                ret = grpDao.getGroup(usrSid).getGrpName();
            }

        } else {
            UserSearchDao uDao = new UserSearchDao(con);
            CmnUsrmInfModel uMdl = uDao.getUserInfoJtkb(usrSid, GSConstUser.USER_JTKBN_ACTIVE);
            ret = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
        }
        return ret;
    }

    /**
     * <br>スケジュールを新規登録します
     * @param paramMdl Sch041knParamModel
     * @param cntCon 採番コントローラ
     * @param userSid 登録者SID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception SQL実行時例外
     */
    public void insertScheduleDate(
            Sch041knParamModel paramMdl,
            MlCountMtController cntCon,
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            boolean smailPluginUseFlg,
            RequestModel reqMdl) throws Exception {

        UDate now = new UDate();
        SchDataModel schMdl = null;
        SchCommonBiz cmnBiz = new SchCommonBiz(con__, reqMdl);
        String url = null;
        int roopCount = 0;
        int roopCountDouji = 0;
        //登録日付
        HashMap<String, UDate> addDateMap = getInsertDateList(paramMdl, userSid, con__);
        ArrayList<UDate> dateList = new ArrayList<UDate>(addDateMap.values());
        //登録予定日付
        ArrayList<String> strDateList = __getStrDateListFromMap(addDateMap);
        //登録モデルを作成
        schMdl = new SchDataModel();
        if (paramMdl.getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            schMdl.setScdDaily(GSConstSchedule.TIME_EXIST);
        } else {
            schMdl.setScdDaily(GSConstSchedule.TIME_NOT_EXIST);
        }

        schMdl.setScdBgcolor(
                NullDefault.getInt(paramMdl.getSch041Bgcolor(), GSConstSchedule.DF_BG_COLOR));
        schMdl.setScdTitle(paramMdl.getSch041Title());
        schMdl.setScdValue(paramMdl.getSch041Value());
        schMdl.setScdBiko(paramMdl.getSch041Biko());
        schMdl.setScdPublic(
                NullDefault.getInt(paramMdl.getSch041Public(), GSConstSchedule.DSP_PUBLIC));
        schMdl.setScdAuid(userSid);
        schMdl.setScdAdate(now);
        schMdl.setScdEuid(userSid);
        schMdl.setScdEdate(now);
        //編集区分
        schMdl.setScdEdit(
                NullDefault.getInt(paramMdl.getSch041Edit(), GSConstSchedule.EDIT_CONF_NONE));
        //拡張登録SID
        int extSid = GSConstSchedule.DF_SCHGP_ID;
        extSid = (int) cntCon.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                SaibanModel.SBNSID_SUB_SCH_EX, userSid);
        schMdl.setSceSid(extSid);
        //施設予約拡張SID
        int resExtSid = GSConstSchedule.DF_SCHGP_ID;
        String[] svReserves = paramMdl.getSch041SvReserve();
        if (svReserves != null && svReserves.length > 0) {
            //施設予約拡張登録SID
            resExtSid = (int) cntCon.getSaibanNumber(
                    GSConstReserve.SBNSID_RESERVE,
                    GSConstReserve.SBNSID_SUB_KAKUTYO,
                    userSid);
        }

        SchDataDao schDao = new SchDataDao(con__);
        String[] svUsers = paramMdl.getSch041SvUsers();
        int addUserSid = -1;
        //スケジュールグループSID（同時登録有りの場合）
        int scdGpSid = GSConstSchedule.DF_SCHGP_ID;
        int scdResSid = GSConstSchedule.DF_SCHGP_ID;

        int scdSid = GSConstSchedule.DF_SCHGP_ID;
        Map<Integer, UDate[]> newScdMap = new HashMap<Integer, UDate[]>();
        Map<Integer, Integer> scdUserMap = new HashMap<Integer, Integer>();

        Map<Integer, SchDataModel> addUserSchDataMap = new HashMap<Integer, SchDataModel>();

        int frHour = GSConstSchedule.DAY_START_HOUR;
        int frMin = GSConstSchedule.DAY_START_MINUTES;
        int toHour = GSConstSchedule.DAY_END_HOUR;
        int toMin = GSConstSchedule.DAY_END_MINUTES;
        if (paramMdl.getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            frHour = Integer.parseInt(paramMdl.getSch041FrHour());
            frMin = Integer.parseInt(paramMdl.getSch041FrMin());
            toHour = Integer.parseInt(paramMdl.getSch041ToHour());
            toMin = Integer.parseInt(paramMdl.getSch041ToMin());
        }
        for (UDate addDate : dateList) {

            //日付ループ
            UDate frDate = addDate.cloneUDate();
            UDate toDate = frDate.cloneUDate();

//            boolean frExit = false;
            if (frHour != -1 && frMin != -1) {
                frDate.setZeroHhMmSs();
                frDate.setHour(frHour);
                frDate.setMinute(frMin);
//                frExit = true;
            }

//            boolean toExit = false;
            if (toHour != -1 && toMin != -1) {
                toDate.setZeroHhMmSs();
                toDate.setHour(toHour);
                toDate.setMinute(toMin);
//                toExit = true;
            }
            schMdl.setScdFrDate(frDate);
            schMdl.setScdToDate(toDate);
            //SID採番
            scdSid = (int) cntCon.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH, userSid);
            //ショートメール通知処理
            schMdl.setScdUsrSid(Integer.parseInt(paramMdl.getSch010SelectUsrSid()));
            schMdl.setScdUsrKbn(Integer.parseInt(paramMdl.getSch010SelectUsrKbn()));
            schMdl.setScdSid(scdSid);
            if (svUsers != null && svUsers.length > 0) {
                //スケジュールグループSID（同時登録有りの場合）
                scdGpSid = (int) cntCon.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                        SaibanModel.SBNSID_SUB_SCH_GP, userSid);
            }
            if (svReserves != null && svReserves.length > 0) {
                //スケジュール施設予約SID（施設予約有りの場合）
                scdResSid = (int) cntCon.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                        SaibanModel.SBNSID_SUB_SCH_RES, userSid);
            }
            schMdl.setScdGrpSid(scdGpSid);
            schMdl.setScdRsSid(scdResSid);
            //登録
            schDao.insert(schMdl);

            UDate[] schDate = new UDate[2];
            schDate[0] = schMdl.getScdFrDate();
            schDate[1] = schMdl.getScdToDate();
            newScdMap.put(schMdl.getScdSid(), schDate);
            scdUserMap.put(schMdl.getScdSid(), schMdl.getScdUsrSid());

            if (roopCount == 0) {
                //ユーザSID
                String usrSid = paramMdl.getSch010SelectUsrSid();
                //URL取得
                url = __createScheduleUrlRepeat(reqMdl, GSConstSchedule.CMD_EDIT,
                                                     String.valueOf(scdSid), usrSid,
                                                     paramMdl);

                cmnBiz.sendPlgSmailEx(cntCon, schMdl, appRootPath,
                        plconf, strDateList, smailPluginUseFlg, url);
                roopCount++;
            }

            //同時登録分
            if (svUsers != null) {
                for (String svUserSid : svUsers) {
                    addUserSid = Integer.parseInt(svUserSid);
                    schMdl.setScdUsrSid(addUserSid);
                    schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_USER);
                    scdSid = (int) cntCon.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                            SaibanModel.SBNSID_SUB_SCH, userSid);
                    schMdl.setScdSid(scdSid);
                    schDao.insert(schMdl);

                    UDate[] svSchDate = new UDate[2];
                    svSchDate[0] = schMdl.getScdFrDate();
                    svSchDate[1] = schMdl.getScdToDate();
                    newScdMap.put(schMdl.getScdSid(), svSchDate);
//                    scdUserMap.put(schMdl.getScdSid(), schMdl.getScdUsrSid());

                    if (roopCountDouji < svUsers.length) {
                        //URL取得
                        url = __createScheduleUrlRepeat(reqMdl, GSConstSchedule.CMD_EDIT,
                                                             String.valueOf(scdSid),
                                                             String.valueOf(addUserSid),
                                                             paramMdl);
                        addUserSchDataMap.put(new Integer(addUserSid), schMdl.cloneSchData());
                        cmnBiz.sendPlgSmailEx(cntCon, schMdl, appRootPath,
                                plconf, strDateList, smailPluginUseFlg, url);
                        roopCountDouji++;
                    }
                }
            }
            //
            if (paramMdl.getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
                int yoyakuSid = -1;
                RsvSisYrkDao yrkDao = new RsvSisYrkDao(con__);
                if (svReserves != null) {
                    RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
                    for (int i = 0; i < svReserves.length; i++) {
                        yoyakuSid = (int) cntCon.getSaibanNumber(
                                GSConstReserve.SBNSID_RESERVE,
                                GSConstReserve.SBNSID_SUB_YOYAKU,
                                userSid);
                        RsvSisYrkModel yrkParam = new RsvSisYrkModel();
                        yrkParam.setRsySid(yoyakuSid);
                        yrkParam.setRsdSid(Integer.parseInt(svReserves[i]));
                        String moku = NullDefault.getString(paramMdl.getSch041Title(), "");
                        yrkParam.setRsyMok(moku);
                        yrkParam.setRsyFrDate(frDate);
                        yrkParam.setRsyToDate(toDate);
                        yrkParam.setRsyBiko(NullDefault.getString(paramMdl.getSch041Value(), ""));
                        yrkParam.setRsyAuid(userSid);
                        yrkParam.setRsyAdate(now);
                        yrkParam.setRsyEuid(userSid);
                        yrkParam.setRsyEdate(now);
                        yrkParam.setScdRsSid(scdResSid);
                        yrkParam.setRsrRsid(resExtSid);
                        yrkParam.setRsyEdit(
                                NullDefault.getInt(
                                        paramMdl.getSch041Edit(),
                                        GSConstSchedule.EDIT_CONF_NONE));

                        //承認状況
                        rsvCmnBiz.setSisYrkApprData(
                                con__,  yrkParam.getRsdSid(), yrkParam, userSid);
                        yrkDao.insertNewYoyaku(yrkParam);


                        //施設予約区分別情報を登録（スケジュールからの場合は全て初期値）
                        RsvSisDataDao dataDao = new RsvSisDataDao(con__);
                        Rsv070Model mdl =
                                dataDao.getPopUpSisetuData(Integer.parseInt(svReserves[i]));
                        if (mdl != null) {
                            if (RsvCommonBiz.isRskKbnRegCheck(mdl.getRskSid())) {
                                RsvCommonBiz rsvBiz = new RsvCommonBiz();
                                RsvSisKyrkModel kyrkMdl =
                                        rsvBiz.getSisKbnInitData(
                                                con__, reqMdl, mdl.getRskSid(), appRootPath);
                                kyrkMdl.setRsySid(yoyakuSid);
                                kyrkMdl.setRkyAuid(userSid);
                                kyrkMdl.setRkyAdate(now);
                                kyrkMdl.setRkyEuid(userSid);
                                kyrkMdl.setRkyEdate(now);

                                RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con__);
                                kyrkDao.insert(kyrkMdl);
                            }
                        }

                    }

                }
            }
        }

        if (svReserves != null
         && svReserves.length > 0
         && paramMdl.getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            //施設予約拡張
            insertReserveExData(resExtSid, paramMdl, userSid, con__);
        }

        //スケジュール拡張情報を登録
        insertScheduleExData(extSid, paramMdl, userSid, con__, cntCon);

        //スケジュール-会社情報Mappingを登録
        __insertSchCompany(con__, cntCon, paramMdl, newScdMap, scdUserMap, userSid, new UDate());
    }

    /**
     * <br>[機  能] スケジュール拡張情報を登録します
     * <br>[解  説]
     * <br>[備  考]
     * @param extSid スケジュール拡張SID
     * @param paramMdl フォーム
     * @param userSid セッションユーザSID
     * @param con コネクション
     * @param cntCon 採番コントローラ
     * @throws SQLException SQL実行時例外
     */
    public void insertScheduleExData(int extSid, Sch041knParamModel paramMdl, int userSid,
                                    Connection con, MlCountMtController cntCon)
    throws SQLException {
        SchExdataModel exMdl = new SchExdataModel();
        exMdl.setSceSid(extSid);
        exMdl.setSceKbn(Integer.parseInt(paramMdl.getSch041ExtKbn()));
        String[] weeks = paramMdl.getSch041Dweek();
        exMdl.setSceDweek1(0);
        exMdl.setSceDweek2(0);
        exMdl.setSceDweek3(0);
        exMdl.setSceDweek4(0);
        exMdl.setSceDweek5(0);
        exMdl.setSceDweek6(0);
        exMdl.setSceDweek7(0);
        if (weeks != null) {
            for (String week : weeks) {
                int intWeek = Integer.parseInt(week);
                switch (intWeek) {
                case UDate.SUNDAY:
                    exMdl.setSceDweek1(1);
                    break;
                case UDate.MONDAY:
                    exMdl.setSceDweek2(1);
                    break;
                case UDate.TUESDAY:
                    exMdl.setSceDweek3(1);
                    break;
                case UDate.WEDNESDAY:
                    exMdl.setSceDweek4(1);
                    break;
                case UDate.THURSDAY:
                    exMdl.setSceDweek5(1);
                    break;
                case UDate.FRIDAY:
                    exMdl.setSceDweek6(1);
                    break;
                case UDate.SATURDAY:
                    exMdl.setSceDweek7(1);
                    break;
                default:
                    break;
                }
            }
        }
        exMdl.setSceDay(NullDefault.getInt(paramMdl.getSch041Day(), 0));
        exMdl.setSceWeek(NullDefault.getInt(paramMdl.getSch041Week(), 0));
        exMdl.setSceMonthOfYearly(NullDefault.getInt(paramMdl.getSch041MonthOfYearly(), 0));
        exMdl.setSceDayOfYearly(NullDefault.getInt(paramMdl.getSch041DayOfYearly(), 0));

        exMdl.setSceTranKbn(NullDefault.getInt(paramMdl.getSch041TranKbn(), 0));
        UDate frDate = new UDate();
        UDate toDate = new UDate();
        int frYear = Integer.parseInt(paramMdl.getSch041FrYear());
        int frMonth = Integer.parseInt(paramMdl.getSch041FrMonth());
        int frDay = Integer.parseInt(paramMdl.getSch041FrDay());

        int frHour = GSConstSchedule.DAY_START_HOUR;
        int frMin = GSConstSchedule.DAY_START_MINUTES;
        int toHour = GSConstSchedule.DAY_END_HOUR;
        int toMin = GSConstSchedule.DAY_END_MINUTES;
        if (paramMdl.getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            frHour = Integer.parseInt(paramMdl.getSch041FrHour());
            frMin = Integer.parseInt(paramMdl.getSch041FrMin());
            toHour = Integer.parseInt(paramMdl.getSch041ToHour());
            toMin = Integer.parseInt(paramMdl.getSch041ToMin());
        }

        frDate.setDate(frYear, frMonth, frDay);
        frDate.setZeroHhMmSs();
        frDate.setHour(frHour);
        frDate.setMinute(frMin);
        int toYear = Integer.parseInt(paramMdl.getSch041ToYear());
        int toMonth = Integer.parseInt(paramMdl.getSch041ToMonth());
        int toDay = Integer.parseInt(paramMdl.getSch041ToDay());
        toDate.setDate(toYear, toMonth, toDay);
        toDate.setZeroHhMmSs();
        toDate.setHour(toHour);
        toDate.setMinute(toMin);

        exMdl.setSceDateFr(frDate);
        exMdl.setSceDateTo(toDate);
        exMdl.setSceTimeFr(frDate);
        exMdl.setSceTimeTo(toDate);
        exMdl.setSceBgcolor(
                NullDefault.getInt(
                        paramMdl.getSch041Bgcolor(),
                        GSConstSchedule.DF_BG_COLOR));

        exMdl.setSceTitle(paramMdl.getSch041Title());
        exMdl.setSceValue(paramMdl.getSch041Value());
        exMdl.setSceBiko(paramMdl.getSch041Biko());
        exMdl.setScePublic(NullDefault.getInt(
                paramMdl.getSch041Public(), GSConstSchedule.DSP_PUBLIC));
        exMdl.setSceEdit(Integer.parseInt(paramMdl.getSch041Edit()));
        exMdl.setSceDaily(NullDefault.getInt(
                paramMdl.getSch041TimeKbn(), GSConstSchedule.TIME_EXIST));
        exMdl.setSceAuid(userSid);
        exMdl.setSceAdate(new UDate());
        exMdl.setSceEuid(userSid);
        exMdl.setSceEdate(new UDate());
        SchExdataDao exDao = new SchExdataDao(con);
        exDao.insert(exMdl);

        //スケジュール拡張情報-会社情報Mappingを登録
        __insertExSchCompany(
                con, cntCon, paramMdl, exMdl.getSceSid(), userSid, exMdl.getSceEdate());
    }

    /**
     * <br>[機  能] 施設予約拡張情報を登録します
     * <br>[解  説]
     * <br>[備  考]
     * @param extSid スケジュール拡張SID
     * @param paramMdl フォーム
     * @param userSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void insertReserveExData(
            int extSid, Sch041knParamModel paramMdl, int userSid, Connection con)
    throws SQLException {

        RsvSisRyrkModel ret = new RsvSisRyrkModel();
        UDate now = new UDate();
        //施設予約拡張SID
        ret.setRsrRsid(extSid);
        //区分
        ret.setRsrKbn(Integer.parseInt(paramMdl.getSch041ExtKbn()));
        String[] weeks = paramMdl.getSch041Dweek();
        ret.setRsrDweek1(0);
        ret.setRsrDweek2(0);
        ret.setRsrDweek3(0);
        ret.setRsrDweek4(0);
        ret.setRsrDweek5(0);
        ret.setRsrDweek6(0);
        ret.setRsrDweek7(0);
        if (weeks != null) {
            for (String week : weeks) {
                int intWeek = Integer.parseInt(week);
                switch (intWeek) {
                case UDate.SUNDAY:
                    ret.setRsrDweek1(1);
                    break;
                case UDate.MONDAY:
                    ret.setRsrDweek2(1);
                    break;
                case UDate.TUESDAY:
                    ret.setRsrDweek3(1);
                    break;
                case UDate.WEDNESDAY:
                    ret.setRsrDweek4(1);
                    break;
                case UDate.THURSDAY:
                    ret.setRsrDweek5(1);
                    break;
                case UDate.FRIDAY:
                    ret.setRsrDweek6(1);
                    break;
                case UDate.SATURDAY:
                    ret.setRsrDweek7(1);
                    break;
                default:
                    break;
                }
            }
        }
        //日
        ret.setRsrDay(NullDefault.getInt(paramMdl.getSch041Day(), 0));
        //週
        ret.setRsrWeek(NullDefault.getInt(paramMdl.getSch041Week(), 0));
        //月
        ret.setRsrMonthYearly(NullDefault.getInt(paramMdl.getSch041MonthOfYearly(), 0));
        //日
        ret.setRsrDayYearly(NullDefault.getInt(paramMdl.getSch041DayOfYearly(), 0));

        //時分from
        UDate frDate = new UDate();
        UDate toDate = frDate.cloneUDate();
        int frYear = Integer.parseInt(paramMdl.getSch041FrYear());
        int frMonth = Integer.parseInt(paramMdl.getSch041FrMonth());
        int frDay = Integer.parseInt(paramMdl.getSch041FrDay());
        int frHour = Integer.parseInt(paramMdl.getSch041FrHour());
        int frMin = Integer.parseInt(paramMdl.getSch041FrMin());
        frDate.setDate(frYear, frMonth, frDay);
        frDate.setZeroHhMmSs();
        frDate.setHour(frHour);
        frDate.setMinute(frMin);
        int toYear = Integer.parseInt(paramMdl.getSch041ToYear());
        int toMonth = Integer.parseInt(paramMdl.getSch041ToMonth());
        int toDay = Integer.parseInt(paramMdl.getSch041ToDay());
        int toHour = Integer.parseInt(paramMdl.getSch041ToHour());
        int toMin = Integer.parseInt(paramMdl.getSch041ToMin());
        toDate.setDate(toYear, toMonth, toDay);
        toDate.setZeroHhMmSs();
        toDate.setHour(toHour);
        toDate.setMinute(toMin);
        //時間from
        ret.setRsrTimeFr(frDate);
        //時間to
        ret.setRsrTimeTo(toDate);
        //反映期間from
        ret.setRsrDateFr(frDate);
        //反映期間to
        ret.setRsrDateTo(toDate);
        //振替区分
        ret.setRsrTranKbn(NullDefault.getInt(paramMdl.getSch041TranKbn(), 0));

        //利用目的
        String moku = NullDefault.getString(paramMdl.getSch041Title(), "");
        if (moku.length() > 20) {
            ret.setRsrMok(moku.substring(0, 20));
        } else {
            ret.setRsrMok(moku);
        }
        //内容
        ret.setRsrBiko(NullDefault.getString(paramMdl.getSch041Value(), ""));
        //編集権限設定
        ret.setRsrEdit(Integer.parseInt(paramMdl.getSch041Edit()));
        //登録者ID
        ret.setRsrAuid(userSid);
        //登録日時
        ret.setRsrAdate(now);
        //更新者ID
        ret.setRsrEuid(userSid);
        //更新日時
        ret.setRsrEdate(now);

        RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con);
        ryrkDao.insert(ret);
    }

    /**
     * <br>スケジュールを更新します
     * @param paramMdl Sch041knParamModel
     * @param cntCon 採番コントローラ
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @return 更新件数
     * @throws Exception SQL実行時例外
     */
    public int updateScheduleDate(Sch041knParamModel paramMdl,
            MlCountMtController cntCon,
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            boolean smailPluginUseFlg,
            RequestModel reqMdl) throws Exception {

        //管理者設定を取得
        SchCommonBiz biz = new SchCommonBiz(reqMdl);
        SchAdmConfModel adminConf = biz.getAdmConfModel(con__);
        int cnt = 0;
        //スケジュールを新規登録
        insertScheduleDate(
                paramMdl, cntCon, userSid, appRootPath, plconf, smailPluginUseFlg, reqMdl);

        Sch040Biz sch040biz = new Sch040Biz(con__, reqMdl);
        int scdSid = Integer.parseInt(paramMdl.getSch010SchSid());
        //旧同時登録施設予約を削除
        deleteReserves(scdSid, con__);

        //旧スケジュールを削除
        ScheduleSearchModel scdMdl = sch040biz.getSchData(scdSid, adminConf, con__);
        int sceSid = scdMdl.getSceSid();
        SchDataDao scdDao = new SchDataDao(con__);
        ScheduleSearchDao ssDao = new ScheduleSearchDao(con__);

        List<Integer> delScdList = new ArrayList<Integer>();
        if (sceSid != GSConstSchedule.DEF_SCE_SID) {
            __deleteSchMapping(con__, sceSid, paramMdl.getSch041contact(), userSid);

            //拡張SIDを指定し、スケジュール情報を削除
            ArrayList<Integer> scds = ssDao.getExScheduleUsrs(
                    sceSid,
                    userSid,
                    GSConstSchedule.SSP_AUTHFILTER_EDIT);
            scdDao.delete(scds);

            //スケジュール拡張情報を削除
            __deleteSchExData(con__, sceSid, userSid);

        } else {
            ArrayList<Integer> scds = ssDao.getScheduleUsrs(
                    scdSid,
                    userSid,
                    adminConf.getSadCrange(),
                    GSConstSchedule.SSP_AUTHFILTER_EDIT
                    );
            cnt = scdDao.delete(scdSid);
            cnt = scdDao.delete(scds);

            delScdList.add(scdSid);
            delScdList.addAll(scds);

            sch040biz.deleteSchCompany(con__, delScdList, 0);
        }

        return cnt;
    }

    /**
     * <br>スケジュール拡張情報を削除(物理削除)します
     * @param sceSid スケジュール拡張SID
     * @param con コネクション
     * @param sessionUserSid セッションユーザSID
     * @return 削除レコード件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteScheduleEx(int sceSid, Connection con, int sessionUserSid)
    throws SQLException {

        __deleteSchMapping(con, sceSid, 0, sessionUserSid);

        int cnt = 0;
        SchDataDao scdDao = new SchDataDao(con);
//        scdDao.extendDataDelete(sceSid);
        ScheduleSearchDao ssDao = new ScheduleSearchDao(con__);
        ArrayList<Integer> scds = ssDao.getExScheduleUsrs(
                sceSid,
                sessionUserSid,
                GSConstSchedule.SSP_AUTHFILTER_EDIT);
        scdDao.delete(scds);

        //関連するスケジュールの件数が2件未満の場合、スケジュール拡張情報を削除する
        __deleteSchExData(con, sceSid, sessionUserSid);

        return cnt;
    }

    /**
     * <br>施設予約情報を削除(物理削除)します
     * <br>スケジュールSIDで関連付いている予約情報全てを削除します。
     * @param scdSid スケジュール拡張SID
     * @param con コネクション
     * @return 削除レコード件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteReserves(int scdSid, Connection con) throws SQLException {

        int cnt = 0;
        //管理者設定を取得
        Sch040Biz sch040Biz = new Sch040Biz(con, reqMdl__);
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = biz.getAdmConfModel(con);
        ScheduleSearchModel oldMdl = sch040Biz.getSchData(scdSid, adminConf, con);
        int sceSid = oldMdl.getSceSid();
        //旧スケジュールに関連付いている予約SIDを取得する
        ScheduleReserveDao schRsvDao = new ScheduleReserveDao(con);
        ArrayList<Integer> resList = schRsvDao.getScheduleReserveSidFromExSid(sceSid);
        RsvSisYrkDao rsDao = new RsvSisYrkDao(con);
        if (oldMdl.getScdRsSid() > 0) {
            ArrayList<RsvSisYrkModel> yrkList = rsDao.getScheduleRserveData(oldMdl.getScdRsSid());
            if (yrkList.size() > 0) {
                RsvSisYrkModel yrkMdl = yrkList.get(0);
                //拡張SIDが設定されていれば拡張を削除
                if (yrkMdl.getRsrRsid() > 0) {
                    RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con);
                    ryrkDao.delete(yrkMdl.getRsrRsid());

                    //施設予約拡張区分別情報削除
                    RsvSisKryrkDao kryrkDao = new RsvSisKryrkDao(con__);
                    kryrkDao.delete(yrkMdl.getRsrRsid());
                }
            }
        }
        //予約情報を削除
        cnt = rsDao.deleteRsySid(resList);

        //施設予約区分別情報を削除
        RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con);
        if (resList != null && resList.size() > 0) {
            kyrkDao.delete(resList);
        }

        return cnt;
    }

    /**
     * <br>[機  能] 曜日指定情報をString[]へ設定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param model ScheduleExSearchModel
     * @return String[] 設定値配列
     */
    private String[] __getDayWeekStringList(ScheduleExSearchModel model) {

        ArrayList<String> dWeekList = new ArrayList<String>();
        if (model.getSceDweek1() == 1) {
            dWeekList.add("1");
        }
        if (model.getSceDweek2() == 1) {
            dWeekList.add("2");
        }
        if (model.getSceDweek3() == 1) {
            dWeekList.add("3");
        }
        if (model.getSceDweek4() == 1) {
            dWeekList.add("4");
        }
        if (model.getSceDweek5() == 1) {
            dWeekList.add("5");
        }
        if (model.getSceDweek6() == 1) {
            dWeekList.add("6");
        }
        if (model.getSceDweek7() == 1) {
            dWeekList.add("7");
        }
        return (String[]) dWeekList.toArray(new String[dWeekList.size()]);
    }

    /**
     * <br>[機  能] スケジュール繰り返し登録確認URLを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param cmd 処理モード
     * @param sch010SchSid スケジュールSID
     * @param usrSid ユーザーSID
     * @param paramMdl フォーム
     * @return スケジュール繰り返し登録確認URL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    private String __createScheduleUrlRepeat(RequestModel reqMdl, String cmd,
                                            String sch010SchSid, String usrSid,
                                            Sch041knParamModel paramMdl)
    throws UnsupportedEncodingException {
        String scheduleUrl = null;

        //スレッドのURLを作成
        String schUrl = reqMdl.getReferer();
        if (!StringUtil.isNullZeroString(schUrl)) {

            scheduleUrl = schUrl.substring(0, schUrl.lastIndexOf("/"));
            scheduleUrl = scheduleUrl.substring(0, scheduleUrl.lastIndexOf("/"));
            scheduleUrl += "/common/cmn001.do?url=";

            String paramUrl = reqMdl.getRequestURI();
            paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("/"));

            String domain = "";
            String reqDomain = NullDefault.getString(reqMdl.getDomain(), "");
            if (!reqDomain.equals(GSConst.GS_DOMAIN)) {
                domain = reqDomain + "/";
                paramUrl = paramUrl.replace(
                 GSConstSchedule.PLUGIN_ID_SCHEDULE, domain + GSConstSchedule.PLUGIN_ID_SCHEDULE);
            }

            paramUrl += "/sch041.do";
            paramUrl += "?sch010SelectDate=" + paramMdl.getSch010SelectDate();
            paramUrl += "&cmd=" + cmd;
            paramUrl += "&sch010SchSid=" + sch010SchSid;
            paramUrl += "&sch010SelectUsrSid=" + usrSid;
            paramUrl += "&sch010SelectUsrKbn=" + paramMdl.getSch010SelectUsrKbn();
            paramUrl += "&sch010DspDate=" + paramMdl.getSch010DspDate();
            paramUrl += "&dspMod=" + paramMdl.getDspMod();
            paramUrl += "&sch010DspGpSid=" + paramMdl.getSch010DspGpSid();
            paramUrl += "&dspKbn=" + GSConstSchedule.LINK_INIT_FLG;
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

            scheduleUrl += paramUrl;
        }

        return scheduleUrl;
    }

    /**
     * <br>[機  能] 会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon 採番コントロ－ル
     * @param paramMdl フォーム
     * @param newScdMap スケジュールSIDと開始日時、終了日時のMapping
     * @param scdUserMap スケジュールSIDとユーザSIDのMapping
     * @param sessionUserSid セッションユーザSID
     * @param date 更新日付
     * @throws SQLException SQL実行時例外
     */
    private void __insertSchCompany(Connection con,
                                    MlCountMtController cntCon,
                                    Sch041knParamModel paramMdl,
                                    Map<Integer, UDate[]> newScdMap,
                                    Map<Integer, Integer> scdUserMap,
                                    int sessionUserSid, UDate date)
    throws SQLException {

        List<Integer> scdSidList = Arrays.asList(newScdMap.keySet().toArray(
                new Integer[newScdMap.size()]));

        //会社情報Mappingを登録
        Sch040Biz biz040 = new Sch040Biz(con, cntCon, reqMdl__);
        List<SchCompanyModel> companyList = biz040.createCompanyModel(scdSidList,
                                                        paramMdl.getSch041CompanySid(),
                                                        paramMdl.getSch041CompanyBaseSid(),
                                                        sessionUserSid, date);
        if (companyList != null) {
            SchCompanyDao companyDao = new SchCompanyDao(con);
            for (SchCompanyModel companyModel : companyList) {
                companyDao.insert(companyModel);
            }
        }

        //アドレス帳情報Mapping、コンタクト履歴を登録する
        String[] addressId = paramMdl.getSch041AddressId();
        List<SchAddressModel> addressList = biz040.createAddressModel(scdSidList, addressId,
                                                                    sessionUserSid, date);
        if (addressList != null) {
            SchAddressDao addressDao = new SchAddressDao(con);
            Sch040Dao dao040 = new Sch040Dao(con);
            boolean contactFlg = (paramMdl.getSch041contact() == 1);
            String contactTitle = paramMdl.getSch041Title();

//            //アドレス帳情報が複数選択されている場合は登録ユーザ、日付ごとに
//            //コンタクト履歴グループSIDを採番する
//            Map<String, Integer> adcGrpSidMap = new HashMap<String, Integer>();
//            if (contactFlg && addressId != null && addressId.length > 1) {
//                List<String> adcMapKeyList = new ArrayList<String>();
//
//                for (int scdSid : scdSidList) {
//                    String adcMapKey = __createAdcSidMapKey(scdUserMap.get(scdSid),
//                                                            newScdMap.get(scdSid)[0]);
//
//                    if (!adcMapKeyList.contains(adcMapKey)) {
//                        adcMapKeyList.add(adcMapKey);
//                    }
//                }
//
//                for (String adcMapKey : adcMapKeyList) {
//                    Integer adcGrpSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
//                                        GSConst.SBNSID_SUB_CONTACT_GRP,
//                                        sessionUserSid);
//                    adcGrpSidMap.put(adcMapKey, adcGrpSid);
//                }
//            }

            Map<String, Integer> adcGrpSidMap = new HashMap<String, Integer>();
            Map<String, Integer> contactMap = new HashMap<String, Integer>();
            if (contactFlg && addressId != null) {
                List<UDate[]> schDateList = new ArrayList<UDate[]>();
                Iterator<UDate[]> dateIter = newScdMap.values().iterator();
                while (dateIter.hasNext()) {
                    UDate[] newSchDate = dateIter.next();

                    boolean exist = false;
                    String dateString = newSchDate[0].getDateString();
                    for (UDate[] schDate : schDateList) {
                        exist = schDate[0].getDateString().equals(dateString);
                        if (exist) {
                            break;
                        }
                    }
                    if (!exist) {
                        schDateList.add(newSchDate);
                    }
                }

                //アドレス帳情報が複数選択されている場合は日付ごとに
                //コンタクト履歴グループSIDを採番する
                for (UDate[] schDate : schDateList) {
                    int adcGrpSid = -1;
                    if (addressId.length > 1) {
                        adcGrpSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                                GSConst.SBNSID_SUB_CONTACT_GRP,
                                                                sessionUserSid);
                    }

                    //コンタクト履歴の登録
                    for (String adrSid : addressId) {
                        String[] startDate = __createStartDate(paramMdl, schDate[0]);
                        String[] endDate = __createEndDate(paramMdl, schDate[1]);
                        Sch040ContactModel contactMdl
                                = biz040.createContactModel(
                                        Integer.parseInt(adrSid), adcGrpSid,
                                        contactTitle, startDate, endDate,
                                        sessionUserSid, date);
                        dao040.insertContact(contactMdl);

                        adcGrpSidMap.put(__createAdcSidMapKey(contactMdl.getAdrSid(), schDate[0]),
                                        contactMdl.getAdcSid());
                        contactMap.put(__createAdcSidMapKey(contactMdl.getAdrSid(), schDate[0]),
                                    contactMdl.getAdcSid());
                    }
                }
            }

            for (SchAddressModel adrMdl : addressList) {
                if (contactFlg) {
                    UDate[] schDate = newScdMap.get(adrMdl.getScdSid());
//                    String[] startDate = __createStartDate(paramMdl, schDate[0]);
//                    String[] endDate = __createEndDate(paramMdl, schDate[1]);;
//
//                    int scdUserSid = scdUserMap.get(adrMdl.getScdSid());
//                    int adcGrpSid = -1;
//                    String adcMapKey = __createAdcSidMapKey(scdUserSid, schDate[0]);
//                    if (adcGrpSidMap != null && adcGrpSidMap.containsKey(adcMapKey)) {
//                        adcGrpSid = adcGrpSidMap.get(adcMapKey);
//                    }
//                    Sch040ContactModel contactMdl
//                        = biz040.createContactModel(cntCon, adrMdl.getAdrSid(), adcGrpSid,
//                          contactTitle, startDate, endDate, scdUserSid, date, sessionUserSid);
//                    dao040.insertContact(contactMdl);
//
//                    adrMdl.setAdcSid(contactMdl.getAdcSid());
                    adrMdl.setAdcSid(contactMap.get(
                            __createAdcSidMapKey(adrMdl.getAdrSid(), schDate[0])));

                }

                addressDao.insert(adrMdl);
            }
        }
    }

    /**
     * <br>[機  能] スケジュール拡張情報の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param sceSid スケジュール拡張SID
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __deleteSchExData(Connection con, int sceSid, int sessionUserSid)
    throws SQLException {

        ScheduleSearchDao schSearchDao = new ScheduleSearchDao(con);
        int exCount = schSearchDao.getExCount(sceSid);
        if (exCount < 2) {
            if (exCount == 1) {
                SchDataDao scdDao = new SchDataDao(con);
                scdDao.updateSceSid(sceSid, -1);
            }

            SchExdataDao sceDao = new SchExdataDao(con);
            sceDao.delete(sceSid);

            //他プラグインのMapping情報を削除
            __deleteSchExMapping(con, sceSid, sessionUserSid);
        }

    }

    /**
     * <br>[機  能] 会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param sceSid スケジュール拡張SID
     * @param contactFlg コンタクト履歴変更有無
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __deleteSchMapping(Connection con, int sceSid, int contactFlg,
                                                        int sessionUserSid)
    throws SQLException {

        SchCompanyDao companyDao = new SchCompanyDao(con);
        SchAddressDao addressDao = new SchAddressDao(con);
        ScheduleSearchDao schSearchDao = new ScheduleSearchDao(con);
        Sch040Dao dao040 = new Sch040Dao(con);
        List<Integer> scdSidList = schSearchDao.getExScheduleUsrs(sceSid, sessionUserSid
                , GSConstSchedule.SSP_AUTHFILTER_EDIT);
        for (int scdSid : scdSidList) {
            companyDao.delete(scdSid);

            if (contactFlg == 1) {
                dao040.deleteScheduleContact(con, scdSid);
            }
            addressDao.delete(scdSid);
        }
    }

    /**
     * <br>[機  能] 会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param sceSid スケジュール拡張SID
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __deleteSchExMapping(Connection con, int sceSid, int sessionUserSid)
    throws SQLException {

        SchExcompanyDao exCompanyDao = new SchExcompanyDao(con);
        exCompanyDao.delete(sceSid);

        SchExaddressDao exAddressDao = new SchExaddressDao(con);
        exAddressDao.delete(sceSid);

    }

    /**
     * <br>[機  能] 会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon 採番コントロール
     * @param paramMdl フォーム
     * @param sceSid スケジュールSID
     * @param userSid セッションユーザSID
     * @param date 更新日付
     * @throws SQLException SQL実行時例外
     */
    private void __insertExSchCompany(Connection con,
                                    MlCountMtController cntCon,
                                    Sch041knParamModel paramMdl,
                                    int sceSid,
                                    int userSid, UDate date)
    throws SQLException {

        //会社情報Mappingを登録
        String[] acoSidList = paramMdl.getSch041CompanySid();
        String[] abaSidList = paramMdl.getSch041CompanyBaseSid();
        if (acoSidList != null && acoSidList.length > 0) {
            SchExcompanyModel exCompanyModel = new SchExcompanyModel();
            exCompanyModel.setSceSid(sceSid);
            exCompanyModel.setSccAuid(userSid);
            exCompanyModel.setSccAdate(date);
            exCompanyModel.setSccEuid(userSid);
            exCompanyModel.setSccEdate(date);

            SchExcompanyDao exCompanyDao = new SchExcompanyDao(con);
            for (int index = 0; index < acoSidList.length; index++) {
                exCompanyModel.setAcoSid(Integer.parseInt(acoSidList[index]));
                exCompanyModel.setAbaSid(Integer.parseInt(abaSidList[index]));
                exCompanyDao.insert(exCompanyModel);
            }
        }

        //アドレス帳情報Mappingを登録する
        String[] adrSidList = paramMdl.getSch041AddressId();
        if (adrSidList != null && adrSidList.length > 0) {

            SchExaddressModel exAdrMdl = new SchExaddressModel();
            exAdrMdl.setSceSid(sceSid);
            exAdrMdl.setSeaAuid(userSid);
            exAdrMdl.setSeaAdate(date);
            exAdrMdl.setSeaEuid(userSid);
            exAdrMdl.setSeaEdate(date);

            SchExaddressDao exAddressDao = new SchExaddressDao(con);
            for (String adrSid : adrSidList) {
                exAdrMdl.setAdrSid(Integer.parseInt(adrSid));
                exAddressDao.insert(exAdrMdl);
            }
        }
    }

//    /**
//     * <br>[機  能] コンタクト履歴グループSIDMappingのキーを生成する
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param userSid ユーザSID
//     * @param date 日付
//     * @return コンタクト履歴グループSIDMappingのキー
//     */
//    private String __createAdcSidMapKey(int userSid, UDate date) {
//        String key = userSid + ":";
//        key += date.getDateString() + ":";
//        key += date.getIntHour() + ":" + date.getIntMinute();
//
//        return key;
//    }
    /**
     * <br>[機  能] コンタクト履歴グループSIDMappingのキーを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param adrSid アドレス帳SID
     * @param date 日付
     * @return コンタクト履歴グループSIDMappingのキー
     */
    private String __createAdcSidMapKey(int adrSid, UDate date) {
        String key = adrSid + ":";
        key += date.getDateString() + ":";
        key += date.getIntHour() + ":" + date.getIntMinute();

        return key;
    }

    /**
     * <br>[機  能] 開始日時を作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param date 日時
     * @return 開始日時
     */
    private String[] __createStartDate(Sch041knParamModel paramMdl, UDate date) {
        String[] startDate = new String[5];
        startDate[0] = String.valueOf(date.getYear());
        startDate[1] = String.valueOf(date.getMonth());
        startDate[2] = String.valueOf(date.getIntDay());
        startDate[3] = paramMdl.getSch041FrHour();
        startDate[4] = paramMdl.getSch041FrMin();

        return startDate;
    }

    /**
     * <br>[機  能] 終了日時を作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param date 日時
     * @return 終了日時
     */
    private String[] __createEndDate(Sch041knParamModel paramMdl, UDate date) {
        String[] startDate = new String[5];
        startDate[0] = String.valueOf(date.getYear());
        startDate[1] = String.valueOf(date.getMonth());
        startDate[2] = String.valueOf(date.getIntDay());
        startDate[3] = paramMdl.getSch041ToHour();
        startDate[4] = paramMdl.getSch041ToMin();

        return startDate;
    }
}
