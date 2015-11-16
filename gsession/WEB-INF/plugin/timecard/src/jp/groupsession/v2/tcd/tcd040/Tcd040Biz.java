package jp.groupsession.v2.tcd.tcd040;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnHolidayModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.TimecardBiz;
import jp.groupsession.v2.tcd.dao.TcdTcdataDao;
import jp.groupsession.v2.tcd.dao.TcdUserGrpDao;
import jp.groupsession.v2.tcd.dao.TimecardDao;
import jp.groupsession.v2.tcd.model.TcdManagerModel;
import jp.groupsession.v2.tcd.model.TcdTotalValueModel;
import jp.groupsession.v2.tcd.model.TcdUserGrpModel;
import jp.groupsession.v2.tcd.tcd010.Tcd010Biz;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] タイムカード勤怠集計画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd040Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd040Biz.class);

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     */
    public Tcd040Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示画面情報を設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Tcd040ParamModel paramMdl, Connection con)
    throws SQLException {
        log__.debug("タイムカード表示情報取得開始");
        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        //基本設定を取得
        TimecardBiz tcBiz = new TimecardBiz(reqMdl__);
        TcdAdmConfModel admConf = tcBiz.getTcdAdmConfModel(sessionUsrSid, con);
        //ユーザ種別を判定 一般・グループ管理者・管理者
        int userKbn = getUserKbn(usModel, con);
        paramMdl.setUsrKbn(String.valueOf(userKbn));

        //年コンボ
        paramMdl.setTcd040YearLabelList(tcBiz.getYearLabelList(con));
        //月コンボ
        paramMdl.setTcd040MonthLabelList(tcBiz.getMonthLabelList());
        //表示年月を取得
        UDate sysDate = TimecardBiz.getDspUDate(new UDate(), admConf);

        //期首月を取得
        TimecardDao tcrDao = new TimecardDao(con);
        int kisyu = 0;
        kisyu = tcrDao.getKishuMonth();

        if (sysDate.getMonth() < kisyu) {
            //期首月が現在の月より小さいとき、年を一つ引く
            paramMdl.setTcd040SltYear(
                NullDefault.getString(paramMdl.getTcd040SltYear(),
                        String.valueOf(sysDate.getYear() - 1)));
        } else {
            paramMdl.setTcd040SltYear(
                    NullDefault.getString(paramMdl.getTcd040SltYear(),
                            String.valueOf(sysDate.getYear())));
        }
        paramMdl.setTcd040SltMonth(
                NullDefault.getString(paramMdl.getTcd040SltMonth(),
                        String.valueOf(kisyu)));
        paramMdl.setTcd040SltYearTo(
                NullDefault.getString(paramMdl.getTcd040SltYearTo(),
                        String.valueOf(sysDate.getYear())));
        paramMdl.setTcd040SltMonthTo(
                NullDefault.getString(paramMdl.getTcd040SltMonthTo(),
                        String.valueOf(sysDate.getMonth())));

        //グループコンボ
        List<LabelValueBean> groupLabel = null;
        groupLabel = getGroupLabelList(con, sessionUsrSid, userKbn, reqMdl__);
        paramMdl.setTcd040GpLabelList(groupLabel);
        int dspGpSid = NullDefault.getInt(
                paramMdl.getTcd040SltGroup(), getInitGpSid(sessionUsrSid, groupLabel, con));
        paramMdl.setTcd040SltGroup(String.valueOf(dspGpSid));

        //検索
        if ((NullDefault.getString(paramMdl.getTcd040SearchFlg(), "0")).equals("0")) {
            paramMdl.setTcd040SearchFlg("1");
            paramMdl.save();
        }
        //出力条件文字列生成
        paramMdl.setTcd040SearchList(__getSearchStringArray(paramMdl, con));
        //検索結果を取得
        if (paramMdl.getTcd040SearchFlg().equals("1")) {
            ArrayList<TcdManagerModel> retList = getSearchResultList(
                    paramMdl, admConf, sessionUsrSid, con);
            paramMdl.setTcd040ResultList(retList);
        }
    }

    /**
     * <br>[機  能] タイムカードマネージャーの検索結果リストを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param admConf 基本設定
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @return ArrayList 検索結果リスト
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<TcdManagerModel> getSearchResultList(
            Tcd040ParamModel paramMdl,
            TcdAdmConfModel admConf,
            int sessionUsrSid,
            Connection con) throws SQLException {

        //所属ユーザリストを取得
        int sltGp = Integer.parseInt(paramMdl.getTcd040SltGroupSv());
        int sortKey = Integer.parseInt(paramMdl.getTcd040SortKey());
        int order = Integer.parseInt(paramMdl.getTcd040OrderKey());
        UserSearchDao usDao = new UserSearchDao(con);
        ArrayList<CmnUsrmInfModel> uList = null;

        if (sortKey == GSConstTimecard.SORT_SYAINNO) {
            uList = usDao.getBelongUserList(
                    sltGp, null, false, sortKey, order, 0, 0);
        } else {
            uList = usDao.getBelongUserList(
                    sltGp, null, false, GSConstUser.USER_SORT_NAME, order, 0, 0);
        }

        log__.debug("所属ユーザ人数==>" + uList.size());
        //ユーザ毎の集計処理
        int year = Integer.parseInt(paramMdl.getTcd040SltYearSv());
        int month = Integer.parseInt(paramMdl.getTcd040SltMonthSv());
        UDate fdate = new UDate();
        UDate tdate = setTimeCardCal(Integer.parseInt(paramMdl.getTcd040SltYearToSv()),
                                    Integer.parseInt(paramMdl.getTcd040SltMonthToSv()),
                                    admConf.getTacSimebi(), fdate);
        fdate.setYear(year);
        fdate.setMonth(month);

        Tcd010Biz biz = new Tcd010Biz();
        TcdTotalValueModel totalMdl = null;
        TcdManagerModel tcdMngMdl = null;
        ArrayList<TcdManagerModel> tcdMngList = new ArrayList<TcdManagerModel>();

        //合計
        TcdTotalValueModel totalData = new TcdTotalValueModel();
        totalData.setKadoBaseDays(BigDecimal.ZERO);
        totalData.setKadoDays(BigDecimal.ZERO);
        totalData.setKadoBaseHours(BigDecimal.ZERO);
        totalData.setKadoHours(BigDecimal.ZERO);
        totalData.setZangyoDays(BigDecimal.ZERO);
        totalData.setZangyoHours(BigDecimal.ZERO);
        totalData.setSinyaDays(BigDecimal.ZERO);
        totalData.setSinyaHours(BigDecimal.ZERO);
        totalData.setKyusyutuDays(BigDecimal.ZERO);
        totalData.setKyusyutuHours(BigDecimal.ZERO);
        totalData.setChikokuDays(BigDecimal.ZERO);
        totalData.setChikokuTimes(BigDecimal.ZERO);
        totalData.setSoutaiDays(BigDecimal.ZERO);
        totalData.setSoutaiTimes(BigDecimal.ZERO);
        totalData.setKekkinDays(BigDecimal.ZERO);
        totalData.setKeityoDays(BigDecimal.ZERO);
        totalData.setYuukyuDays(BigDecimal.ZERO);
        totalData.setDaikyuDays(BigDecimal.ZERO);
        totalData.setSonotaDays(BigDecimal.ZERO);

        int userCount = 0;
        boolean isInZero = paramMdl.getTcd040avgInZero() != 1;
        for (CmnUsrmInfModel uMdl : uList) {
            totalMdl = biz.getTotalValueModel(uMdl.getUsrSid(), fdate, tdate,
                                            sessionUsrSid, con, reqMdl__);
            //検索条件とのマッチング
            if (__isSearchMatting(paramMdl, totalMdl)) {
                tcdMngMdl = new TcdManagerModel(totalMdl);
                tcdMngMdl.setUserSid(uMdl.getUsrSid());
                tcdMngMdl.setUserName(uMdl.getUsiSei() + " " + uMdl.getUsiMei());
                tcdMngMdl.setUserSyainNo(uMdl.getUsiSyainNo());
                tcdMngMdl.setSortKey(sortKey);
                tcdMngList.add(tcdMngMdl);

                totalData.setKadoBaseDays(
                        totalData.getKadoBaseDays().add(tcdMngMdl.getKadoBaseDays()));
                totalData.setKadoDays(
                        totalData.getKadoDays().add(tcdMngMdl.getKadoDays()));
                totalData.setKadoBaseHours(
                        totalData.getKadoBaseHours().add(tcdMngMdl.getKadoBaseHours()));
                totalData.setKadoHours(
                        totalData.getKadoHours().add(tcdMngMdl.getKadoHours()));
                totalData.setZangyoDays(
                        totalData.getZangyoDays().add(tcdMngMdl.getZangyoDays()));
                totalData.setZangyoHours(
                        totalData.getZangyoHours().add(tcdMngMdl.getZangyoHours()));
                totalData.setSinyaDays(
                        totalData.getSinyaDays().add(tcdMngMdl.getSinyaDays()));
                totalData.setSinyaHours(
                        totalData.getSinyaHours().add(tcdMngMdl.getSinyaHours()));
                totalData.setKyusyutuDays(
                        totalData.getKyusyutuDays().add(tcdMngMdl.getKyusyutuDays()));
                totalData.setKyusyutuHours(
                        totalData.getKyusyutuHours().add(tcdMngMdl.getKyusyutuHours()));
                totalData.setChikokuDays(
                        totalData.getChikokuDays().add(tcdMngMdl.getChikokuDays()));
                totalData.setChikokuTimes(
                        totalData.getChikokuTimes().add(tcdMngMdl.getChikokuTimes()));
                totalData.setSoutaiDays(
                        totalData.getSoutaiDays().add(tcdMngMdl.getSoutaiDays()));
                totalData.setSoutaiTimes(
                        totalData.getSoutaiTimes().add(tcdMngMdl.getSoutaiTimes()));
                totalData.setKekkinDays(
                        totalData.getKekkinDays().add(tcdMngMdl.getKekkinDays()));
                totalData.setKeityoDays(
                        totalData.getKeityoDays().add(tcdMngMdl.getKeityoDays()));
                totalData.setYuukyuDays(
                        totalData.getYuukyuDays().add(tcdMngMdl.getYuukyuDays()));
                totalData.setDaikyuDays(
                        totalData.getDaikyuDays().add(tcdMngMdl.getDaikyuDays()));
                totalData.setSonotaDays(
                        totalData.getSonotaDays().add(tcdMngMdl.getSonotaDays()));

                if (isInZero || tcdMngMdl.getKadoHours().compareTo(BigDecimal.ZERO) != 0) {
                    userCount++;
                }
            }
        }
        biz.setTotalValueModelDspString(totalData);
        paramMdl.setTotalData(totalData);

        TcdTotalValueModel averageData = new TcdTotalValueModel();
        averageData.setKadoBaseDays(__divideDecimal(totalData.getKadoBaseDays(), userCount));
        averageData.setKadoDays(__divideDecimal(totalData.getKadoDays(), userCount));
        averageData.setKadoBaseHours(__divideDecimal(totalData.getKadoBaseHours(), userCount));
        averageData.setKadoHours(__divideDecimal(totalData.getKadoHours(), userCount));
        averageData.setZangyoDays(__divideDecimal(totalData.getZangyoDays(), userCount));
        averageData.setZangyoHours(__divideDecimal(totalData.getZangyoHours(), userCount));
        averageData.setSinyaDays(__divideDecimal(totalData.getSinyaDays(), userCount));
        averageData.setSinyaHours(__divideDecimal(totalData.getSinyaHours(), userCount));
        averageData.setKyusyutuDays(__divideDecimal(totalData.getKyusyutuDays(), userCount));
        averageData.setKyusyutuHours(__divideDecimal(totalData.getKyusyutuHours(), userCount));
        averageData.setChikokuDays(__divideDecimal(totalData.getChikokuDays(), userCount));
        averageData.setChikokuTimes(__divideDecimal(totalData.getChikokuTimes(), userCount));
        averageData.setSoutaiDays(__divideDecimal(totalData.getSoutaiDays(), userCount));
        averageData.setSoutaiTimes(__divideDecimal(totalData.getSoutaiTimes(), userCount));
        averageData.setKekkinDays(__divideDecimal(totalData.getKekkinDays(), userCount));
        averageData.setKeityoDays(__divideDecimal(totalData.getKeityoDays(), userCount));
        averageData.setYuukyuDays(__divideDecimal(totalData.getYuukyuDays(), userCount));
        averageData.setDaikyuDays(__divideDecimal(totalData.getDaikyuDays(), userCount));
        averageData.setSonotaDays(__divideDecimal(totalData.getSonotaDays(), userCount));
        biz.setTotalValueModelDspString(averageData);

        paramMdl.setAverageData(averageData);

        //ソート処理
        if (sortKey != GSConstTimecard.SORT_SIMEI && sortKey != GSConstTimecard.SORT_SYAINNO) {
            Collections.sort(tcdMngList);
            if (order == GSConstTimecard.ORDER_KEY_DESC) {
                Collections.reverse(tcdMngList);
            }
        }

        log__.debug("tcdMngList.size()==>" + tcdMngList.size());

        return tcdMngList;
    }

    /**
     * <br>[機  能] 検索条件に合致するか判定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param totalMdl 集計値
     * @return boolean true:合致　false:合致しない
     */
    private boolean __isSearchMatting(Tcd040ParamModel paramMdl, TcdTotalValueModel totalMdl) {
        boolean ret = true;
        //残業有無
        if (paramMdl.getTcd040ZangyoCkSv() != null && paramMdl.getTcd040ZangyoCkSv().equals("1")) {
            if (totalMdl.getZangyoDays().compareTo(BigDecimal.valueOf(0)) == 0
             && totalMdl.getZangyoHours().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        //深夜有無
        if (paramMdl.getTcd040SinyaCkSv() != null && paramMdl.getTcd040SinyaCkSv().equals("1")) {
            if (totalMdl.getSinyaDays().compareTo(BigDecimal.valueOf(0)) == 0
             && totalMdl.getSinyaHours().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        //休日出勤有無
        if (paramMdl.getTcd040KyujituCkSv() != null
        && paramMdl.getTcd040KyujituCkSv().equals("1")) {
            if (totalMdl.getKyusyutuDays().compareTo(BigDecimal.valueOf(0)) == 0
             && totalMdl.getKyusyutuHours().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        //遅刻有無
        if (paramMdl.getTcd040ChikokuCkSv() != null
        && paramMdl.getTcd040ChikokuCkSv().equals("1")) {
            if (totalMdl.getChikokuDays().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        //早退有無
        if (paramMdl.getTcd040SoutaiCkSv() != null && paramMdl.getTcd040SoutaiCkSv().equals("1")) {
            if (totalMdl.getSoutaiDays().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        //欠勤有無
        if (paramMdl.getTcd040KekkinCkSv() != null && paramMdl.getTcd040KekkinCkSv().equals("1")) {
            if (totalMdl.getKekkinDays().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        //慶弔有無
        if (paramMdl.getTcd040KeityoCkSv() != null && paramMdl.getTcd040KeityoCkSv().equals("1")) {
            if (totalMdl.getKeityoDays().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        //有給休暇有無
        if (paramMdl.getTcd040YuukyuCkSv() != null && paramMdl.getTcd040YuukyuCkSv().equals("1")) {
            if (totalMdl.getYuukyuDays().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        //代休有無
        if (paramMdl.getTcd040DaikyuCkSv() != null && paramMdl.getTcd040DaikyuCkSv().equals("1")) {
            if (totalMdl.getDaikyuDays().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        //その他有無
        if (paramMdl.getTcd040SonotaCkSv() != null && paramMdl.getTcd040SonotaCkSv().equals("1")) {
            if (totalMdl.getSonotaDays().compareTo(BigDecimal.valueOf(0)) == 0) {
                return  false;
            }
        }
        return ret;
    }

    /**
     *
     * <br>[機  能] 検索条件を表す文字列リストを生成します。
     * <br>[解  説] save項目から生成する。
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return ArrayList 検索条件を表す文字列リスト
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<String> __getSearchStringArray(Tcd040ParamModel paramMdl, Connection con)
    throws SQLException {
        ArrayList<String> ret = new ArrayList<String>();
        UDate date = new UDate();
        date.setYear(Integer.parseInt(paramMdl.getTcd040SltYearSv()));
        date.setMonth(Integer.parseInt(paramMdl.getTcd040SltMonthSv()));
        String dateStr = UDateUtil.getYymJ(date, reqMdl__);

        UDate dateTo = date.cloneUDate();
        dateTo.setYear(Integer.parseInt(paramMdl.getTcd040SltYearToSv()));
        dateTo.setMonth(Integer.parseInt(paramMdl.getTcd040SltMonthToSv()));
        if (date.getYear() != dateTo.getYear() || date.getMonth() != dateTo.getMonth()) {
            dateStr += "～";
            dateStr += UDateUtil.getYymJ(dateTo, reqMdl__);
        }
        ret.add(dateStr);

        String gSid = paramMdl.getTcd040SltGroupSv();
        GroupDao gDao = new GroupDao(con);
        CmnGroupmModel gMdl = gDao.getGroup(Integer.parseInt(gSid));
        if (gMdl != null) {
            ret.add(gMdl.getGrpName());
        }

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String msg = gsMsg.getMessage("tcd.tcd040.16");

        if (paramMdl.getTcd040avgInZero() == 1) {
            ret.add(msg);
        }

        String msg2 = "";

        //checkbox
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040ZangyoCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.10");
            ret.add(msg2);
        }
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040SinyaCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.08");
            ret.add(msg2);
        }
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040KyujituCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.15");
            ret.add(msg2);
        }
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040ChikokuCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.05");
            ret.add(msg2);
        }
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040SoutaiCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.07");
            ret.add(msg2);
        }
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040KekkinCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.13");
            ret.add(msg2);
        }
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040KeityoCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.14");
            ret.add(msg2);
        }
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040YuukyuCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.01");
            ret.add(msg2);
        }
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040DaikyuCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.06");
            ret.add(msg2);
        }
        if (!StringUtil.isNullZeroString(paramMdl.getTcd040SonotaCkSv())) {
            msg2 = gsMsg.getMessage("tcd.tcd040.17");
            ret.add(msg2);
        }

        return ret;
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
     * <br>[機  能] 休日として扱うか判定します。
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
     * <br>[機  能] 集計モデルに表示用フォーマットを設定する
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

        log__.debug("frDate==>" + frDate.toString());
        log__.debug("toDate==>" + toDate.toString());
        return toDate;
    }

    /**
     * <br>[機  能] ユーザの種別が一般・グループ管理者・管理者かを判定します。
     * <br>[解  説] 管理者権限を持っている場合はグループ管理者権限よりも優先されます。
     * <br>[備  考]
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
            labelList = getGroupLabel(con, reqMdl, usrSid, false, 1);
        }
        return labelList;
    }

    /**
     * <br>[機  能] 指定グループに所属するユーザリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param groupSid グループSID
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getUserLabelList(Connection con, int groupSid)
    throws SQLException {

        List < LabelValueBean > labelList = null;

        UserBiz usrBiz = new UserBiz();
        labelList = usrBiz.getUserLabelList(con, groupSid, null);
        return labelList;
    }

    /**
     * <br>[機  能] グループリストの中にデフォルトグループが存在した場合そのグループSIDを返します。
     * <br>[解  説] デフォルトグループが存在しない場合、リストの先頭を返します。
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param groupLabel グループラベルリスト
     * @param con コネクション
     * @return int グループSID
     * @throws SQLException SQL実行時例外
     */
    public int getInitGpSid(int userSid, List<LabelValueBean> groupLabel, Connection con)
    throws SQLException {
        int ret = 0;
        if (groupLabel == null) {
            return ret;
        }
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
     *
     * <br>[機  能] ユーザリストの中にセッションユーザが存在した場合そのユーザSIDを返します。
     * <br>[解  説]
     * <br>[備  考] セッションユーザが存在しない場合、-1を返します。
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
        if (userLabel == null) {
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
     * @return int 削除件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteTcdData(Tcd040ParamModel paramMdl, int sessionUsrSid, Connection con)
    throws SQLException {
        int ret = 0;

        int usrSid = Integer.parseInt(paramMdl.getUsrSid());
        int year = Integer.parseInt(paramMdl.getYear());
        int month = Integer.parseInt(paramMdl.getMonth());
        String[] days = paramMdl.getSelectDay();
        TimecardBiz biz = new TimecardBiz(reqMdl__);
        TcdAdmConfModel admConf = biz.getTcdAdmConfModel(sessionUsrSid, con);

        boolean commit = false;
        try {
            TcdTcdataDao dao = new TcdTcdataDao(con);
            dao.delete(usrSid, year, month, days, admConf.getTacSimebi(), reqMdl__);
            commit = true;
        } catch (SQLException e) {
            log__.error("タイムカード情報削除に失敗しました。" + e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            }
        }

        return ret;
    }

    /**
     * <br>[機  能] BigDecimalの除算を行う。
     * <br>[解  説]
     * <br>[備  考] 丸めは四捨五入で行う
     *
     * @param value 除算対象のBigDecimal
     * @param divisor valueを除算する値
     * @return 計算結果
     */
    private BigDecimal __divideDecimal(BigDecimal value, int divisor) {
        if (value == null || value.doubleValue() == 0 || divisor == 0) {
            return value;
        }

        return value.divide(new BigDecimal(divisor), value.scale(), BigDecimal.ROUND_HALF_UP);
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

        int spaceCount = level - 1;
        for (int count = 0; count < spaceCount; count++) {
            space += "　";
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
}
