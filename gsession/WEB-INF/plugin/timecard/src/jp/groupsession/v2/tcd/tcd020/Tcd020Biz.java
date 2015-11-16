package jp.groupsession.v2.tcd.tcd020;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.TimecardBiz;
import jp.groupsession.v2.tcd.dao.TcdPriConfModel;
import jp.groupsession.v2.tcd.dao.TcdTcdataDao;
import jp.groupsession.v2.tcd.dao.TimecardDao;
import jp.groupsession.v2.tcd.model.TcdTcdataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] タイムカード編集画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd020Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd020Biz.class);

    /**
     * <br>[機  能] 初期表示画面情報を設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Tcd020ParamModel paramMdl,
            RequestModel reqMdl, Connection con) throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();

        //ユーザ種別を判定 一般・グループ管理者・管理者
        int userKbn = __getUserKbn(usModel, con);
        paramMdl.setUsrKbn(String.valueOf(userKbn));

        //管理者設定情報を設定します。
        __setAdmConf(usModel, con, paramMdl, reqMdl);

        if (StringUtil.isNullZeroString(paramMdl.getEditDay())) {
            //複数
            if (paramMdl.getSelectDay().length == 1) {
                //複数編集でも1件のみ選択の場合
                String[] days = paramMdl.getSelectDay();
                log__.debug("days[0]==>" + days[0]);
                paramMdl.setEditDay(days[0]);
                __doInitOneData(paramMdl, reqMdl, con);
            } else {
                __doInitDatas(paramMdl, reqMdl, con);
            }
        } else {
            //単体
            __doInitOneData(paramMdl, reqMdl, con);
        }
    }

    /**
     * <br>[機  能] 初期表示(単体)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doInitOneData(Tcd020ParamModel paramMdl,
            RequestModel reqMdl, Connection con)
            throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        TimecardBiz tBiz = new TimecardBiz(reqMdl);
        //基本設定取得
        TcdAdmConfModel admConf = tBiz.getTcdAdmConfModel(sessionUsrSid, con);

        //時間コンボ生成
        setTimeCombo(paramMdl, sessionUsrSid, con, reqMdl);

        //打刻分コンボ
        __setStrikeMinuteCombo(paramMdl, reqMdl);

        //ユーザ情報
        int userSid = Integer.parseInt(paramMdl.getUsrSid());
        CmnUsrmInfDao uiDao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel uiMdl = uiDao.select(userSid);
        paramMdl.setTcd020Name(uiMdl.getUsiSei() + " " + uiMdl.getUsiMei());

        //登録・編集対象日付
        int year = Integer.parseInt(paramMdl.getYear());
        int month = Integer.parseInt(paramMdl.getMonth());
        int day = Integer.parseInt(paramMdl.getEditDay());
        //締め日を考慮し編集日付を取得
        UDate udate = getEditDate(year, month, day, admConf.getTacSimebi());
        String editDate = UDateUtil.getYymdJ(udate, reqMdl);
        paramMdl.setTcd020Date(editDate);

        //タイムカード情報取得
        TcdTcdataDao tcdDao = new TcdTcdataDao(con);
        TcdTcdataModel tcdMdl = tcdDao.select(
                userSid,
                udate.getYear(),
                udate.getMonth(),
                udate.getIntDay());
        //画面項目初期値設定
        String tcd020StrikeInHour = null;
        String tcd020StrikeInMinute = null;
        String tcd020StrikeOutHour = null;
        String tcd020StrikeOutMinute = null;
        String tcd020InHour = null;
        String tcd020InMinute = null;
        String tcd020OutHour = null;
        String tcd020OutMinute = null;
        String tcd020Biko = null;
        String tcd020HolKbn = "0";
        String tcd020HolValue = null;
        String tcd020HolDays = null;
        String tcd020ChkKbn = "0";
        String tcd020SouKbn = "0";
        String strikeTimeText = null;
        String inTimeText = null;
        String outTimeText = null;

        GsMessage gsMsg = new GsMessage(reqMdl);
        String msgNami = gsMsg.getMessage("tcd.142");

        //DB情報があれば設定(初期表示)
        if (tcdMdl != null) {

            StringUtil strUtil = new StringUtil();

            //打刻時間
            Time strikeItime = tcdMdl.getTcdStrikeIntime();
            UDate sidate = null;
            if (strikeItime != null) {
                sidate = UDate.getInstance(strikeItime.getTime());
                tcd020StrikeInHour = String.valueOf(sidate.getIntHour());
                tcd020StrikeInMinute = String.valueOf(sidate.getIntMinute());

                strikeTimeText
                    = strUtil.getTimeString(sidate.getStrHour(),
                                            sidate.getStrMinute(), reqMdl);
                strikeTimeText += msgNami;

            }
            Time strikeOtime = tcdMdl.getTcdStrikeOuttime();
            UDate sodate = null;
            if (strikeOtime != null) {
                sodate = UDate.getInstance(strikeOtime.getTime());
                if (strikeItime != null
                        && UDate.getInstance(strikeItime.getTime()).compareDateYMDHM(
                        UDate.getInstance(strikeOtime.getTime())) == UDate.SMALL) {
                    tcd020StrikeOutHour = String.valueOf(sodate.getIntHour() + 24);
                } else {
                    tcd020StrikeOutHour = String.valueOf(sodate.getIntHour());
                }
                tcd020StrikeOutMinute = String.valueOf(sodate.getIntMinute());

                if (strikeTimeText == null) {
                    strikeTimeText = "　～　";
                }

                strikeTimeText
                += strUtil.getTimeString(sodate.getStrHour(),
                                        sodate.getStrMinute(), reqMdl);
            }

            Time itime = tcdMdl.getTcdIntime();
            UDate idate = null;
            if (itime != null) {
                idate = TimecardBiz.convertForDspTime(
                        UDate.getInstance(itime.getTime()), admConf, true);
                tcd020InHour = String.valueOf(idate.getIntHour());
                tcd020InMinute = String.valueOf(idate.getIntMinute());

                inTimeText
                = strUtil.getTimeString(idate.getStrHour(),
                        idate.getStrMinute(), reqMdl);
            }
            Time otime = tcdMdl.getTcdOuttime();
            UDate odate = null;
            if (otime != null) {
                odate = TimecardBiz.convertForDspTime(
                        UDate.getInstance(otime.getTime()), admConf, false);
                if (UDate.getInstance(itime.getTime()).compareDateYMDHM(
                        UDate.getInstance(otime.getTime())) == UDate.SMALL) {
                    tcd020OutHour = String.valueOf(odate.getIntHour() + 24);
                } else {
                    tcd020OutHour = String.valueOf(odate.getIntHour());
                }
                tcd020OutMinute = String.valueOf(odate.getIntMinute());

                outTimeText
                = strUtil.getTimeString(odate.getStrHour(),
                        odate.getStrMinute(), reqMdl);
            }
            tcd020Biko = tcdMdl.getTcdBiko();
            tcd020HolKbn = String.valueOf(tcdMdl.getTcdHolkbn());
            tcd020HolValue = NullDefault.getString(tcdMdl.getTcdHolother(), "");
            if (tcdMdl.getTcdHolcnt() != null) {
                tcd020HolDays = tcdMdl.getTcdHolcnt().toString();
            } else {
                tcd020HolDays = "";
            }
            tcd020ChkKbn = String.valueOf(tcdMdl.getTcdChkkbn());
            tcd020SouKbn = String.valueOf(tcdMdl.getTcdSoukbn());

        } else {

            tcd020StrikeInHour = "-1";
            tcd020StrikeInMinute = "-1";
            tcd020StrikeOutHour = "-1";
            tcd020StrikeOutMinute = "-1";

            //個人設定から取得
            TcdPriConfModel priMdl = tBiz.getTcdPriConfModel(sessionUsrSid, con);
            tcd020InHour = String.valueOf(priMdl.getTpcInHour());
            tcd020InMinute = String.valueOf(priMdl.getTpcInMin());
            tcd020OutHour = String.valueOf(priMdl.getTpcOutHour());
            tcd020OutMinute = String.valueOf(priMdl.getTpcOutMin());
        }

        paramMdl.setTcd020StrikeInHour(
                NullDefault.getString(paramMdl.getTcd020StrikeInHour(), tcd020StrikeInHour));
        paramMdl.setTcd020StrikeInMinute(
                NullDefault.getString(paramMdl.getTcd020StrikeInMinute(), tcd020StrikeInMinute));
        paramMdl.setTcd020StrikeOutHour(
                NullDefault.getString(paramMdl.getTcd020StrikeOutHour(), tcd020StrikeOutHour));
        paramMdl.setTcd020StrikeOutMinute(
                NullDefault.getString(paramMdl.getTcd020StrikeOutMinute(), tcd020StrikeOutMinute));
        paramMdl.setTcd020InHour(
                NullDefault.getString(paramMdl.getTcd020InHour(), tcd020InHour));
        paramMdl.setTcd020InMinute(
                NullDefault.getString(paramMdl.getTcd020InMinute(), tcd020InMinute));
        paramMdl.setTcd020OutHour(
                NullDefault.getString(paramMdl.getTcd020OutHour(), tcd020OutHour));
        paramMdl.setTcd020OutMinute(
                NullDefault.getString(paramMdl.getTcd020OutMinute(), tcd020OutMinute));
        paramMdl.setTcd020Biko(
                NullDefault.getString(paramMdl.getTcd020Biko(), tcd020Biko));
        paramMdl.setTcd020HolKbn(
                NullDefault.getString(paramMdl.getTcd020HolKbn(), tcd020HolKbn));
        paramMdl.setTcd020HolValue(
                NullDefault.getString(paramMdl.getTcd020HolValue(), tcd020HolValue));
        paramMdl.setTcd020HolDays(
                NullDefault.getString(paramMdl.getTcd020HolDays(), tcd020HolDays));
        paramMdl.setTcd020ChkKbn(
                NullDefault.getString(paramMdl.getTcd020ChkKbn(), tcd020ChkKbn));
        paramMdl.setTcd020SouKbn(
                NullDefault.getString(paramMdl.getTcd020SouKbn(), tcd020SouKbn));
        paramMdl.setTcd020StrikeTimeStr(strikeTimeText);
        paramMdl.setTcd020InTimeStr(inTimeText);
        paramMdl.setTcd020OutTimeStr(outTimeText);

    }

    /**
     * <br>[機  能] 初期表示(複数)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __doInitDatas(Tcd020ParamModel paramMdl,
            RequestModel reqMdl, Connection con)
            throws SQLException {

        //複数編集フラグ
        paramMdl.setPluralFlg(GSConstTimecard.PLURAL_FLG_PLURAL);
        String[] days = paramMdl.getSelectDay();
        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        TimecardBiz tBiz = new TimecardBiz(reqMdl);
        //基本設定取得
        TcdAdmConfModel admConf = tBiz.getTcdAdmConfModel(sessionUsrSid, con);

        //時間コンボ生成
        setTimeCombo(paramMdl, sessionUsrSid, con, reqMdl);
        //ユーザ情報
        int userSid = Integer.parseInt(paramMdl.getUsrSid());
        CmnUsrmInfDao uiDao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel uiMdl = uiDao.select(userSid);
        paramMdl.setTcd020Name(uiMdl.getUsiSei() + " " + uiMdl.getUsiMei());

        //登録・編集対象日付
        int year = Integer.parseInt(paramMdl.getYear());
        int month = Integer.parseInt(paramMdl.getMonth());
        //複数日付文字列を生成
        String editDate = getMultiDaysString(
                year, month, days, GSConstTimecard.DAYS_SEP, admConf.getTacSimebi(), 0,
                reqMdl);
        paramMdl.setTcd020Date(editDate);
        //複数一括編集(デフォルト値を画面へ設定)
        //個人設定から取得
        TcdPriConfModel priConf = tBiz.getTcdPriConfModel(sessionUsrSid, con);
        String tcd020StrikeInHour = "-1";
        String tcd020StrikeInMinute = "-1";
        String tcd020StrikeOutHour = "-1";
        String tcd020StrikeOutMinute = "-1";
        String tcd020InHour = String.valueOf(priConf.getTpcInHour());
        String tcd020InMinute = String.valueOf(priConf.getTpcInMin());
        String tcd020OutHour = String.valueOf(priConf.getTpcOutHour());
        String tcd020OutMinute = String.valueOf(priConf.getTpcOutMin());
        String tcd020Biko = null;
        String tcd020HolKbn = "0";
        String tcd020ChkKbn = "0";
        String tcd020SouKbn = "0";
        String tcd020HolValue = null;
        String tcd020HolDays = null;
        paramMdl.setTcd020StrikeInHour(
                NullDefault.getString(paramMdl.getTcd020StrikeInHour(), tcd020StrikeInHour));
        paramMdl.setTcd020StrikeInMinute(
                NullDefault.getString(paramMdl.getTcd020StrikeInMinute(), tcd020StrikeInMinute));
        paramMdl.setTcd020StrikeOutHour(
                NullDefault.getString(paramMdl.getTcd020StrikeOutHour(), tcd020StrikeOutHour));
        paramMdl.setTcd020StrikeOutMinute(
                NullDefault.getString(paramMdl.getTcd020StrikeOutMinute(), tcd020StrikeOutMinute));
        paramMdl.setTcd020InHour(NullDefault.getString(paramMdl.getTcd020InHour(), tcd020InHour));
        paramMdl.setTcd020InMinute(
                NullDefault.getString(paramMdl.getTcd020InMinute(), tcd020InMinute));
        paramMdl.setTcd020OutHour(
                NullDefault.getString(paramMdl.getTcd020OutHour(), tcd020OutHour));
        paramMdl.setTcd020OutMinute(
                NullDefault.getString(paramMdl.getTcd020OutMinute(), tcd020OutMinute));
        paramMdl.setTcd020Biko(
                NullDefault.getString(paramMdl.getTcd020Biko(), tcd020Biko));
        paramMdl.setTcd020HolKbn(NullDefault.getString(paramMdl.getTcd020HolKbn(), tcd020HolKbn));
        paramMdl.setTcd020HolValue(
                NullDefault.getString(paramMdl.getTcd020HolValue(), tcd020HolValue));
        paramMdl.setTcd020HolDays(
                NullDefault.getString(paramMdl.getTcd020HolDays(), tcd020HolDays));
        paramMdl.setTcd020ChkKbn(NullDefault.getString(paramMdl.getTcd020ChkKbn(), tcd020ChkKbn));
        paramMdl.setTcd020SouKbn(NullDefault.getString(paramMdl.getTcd020SouKbn(), tcd020SouKbn));
    }

    /**
     * <br>[機  能] 表示年月日と締め日から編集日付を生成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param year 表示年
     * @param month 表示月
     * @param day 編集日
     * @param sime 締め日
     * @return UDate 編集日付
     */
    public UDate getEditDate(int year, int month, int day, int sime) {
        UDate ret = new UDate();
        ret.setDate(year, month, day);
        ret.setZeroHhMmSs();
        //末締めか
        if (sime == GSConstTimecard.TIMECARD_LIMITDAY[5]) {
            return ret;
        } else {
            if (sime >= day) {
                ret.addMonth(1);
            }
            return ret;
        }
    }

    /**
     * <br>[機  能] YYYY年MM月DD,DD,DD,DD日形式の文字列を生成する。
     * <br>[解  説]
     * <br>[備  考]
     * @param year 年
     * @param month 月
     * @param days 日配列
     * @param sep 区切り文字
     * @param sime 締め日
     * @param brcount 改行する件数
     * @param reqMdl リクエスト情報
     * @return String
     */
    public String getMultiDaysString(
            int year, int month, String[] days, String sep, int sime, int brcount,
            RequestModel reqMdl) {
        String ret = "";
        UDate udate = getEditDate(year, month, Integer.parseInt(days[0]), sime);
        log__.debug("編集日[0]==>" + udate.toString());
        StringBuilder buf = new StringBuilder();
        buf.append(UDateUtil.getYymJ(udate, reqMdl));
        int day = 0;

        GsMessage gsMsg = new GsMessage(reqMdl);
        String msgDay = gsMsg.getMessage("cmn.day");

        for (int i = 0; i < days.length; i++) {
            day = Integer.parseInt(days[i]);
            UDate subDate = new UDate();
            subDate = getEditDate(year, month, day, sime);
            log__.debug("編集日==>" + subDate.toString());
            if (udate.compareDateYM(subDate) != UDate.EQUAL) {
                buf.delete(buf.lastIndexOf(sep, buf.length()), buf.length());
                buf.append(msgDay + " ");
                buf.append(UDateUtil.getYymJ(subDate, reqMdl));
                udate = subDate;
            }
            if (brcount > 0 && i == 0) {
                buf.append("<br>");
            }
            buf.append(StringUtil.toDecFormat(String.valueOf(day), "00"));
            buf.append(sep);
            if (brcount > 0 && (i + 1) % brcount == 0) {
                buf.append("<br>");
            }
        }
        buf.delete(buf.lastIndexOf(sep, buf.length()), buf.length());
        buf.append(msgDay);
        ret = buf.toString();
        return ret;
    }

    /**
     *<br>[機  能]リストボックス用時間ラベルの生成
     *<br>[解  説]
     *<br>[備  考]
     * @return 時間ラベル
     */
    public ArrayList < LabelValueBean > getHourLabel() {
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean("", "-1"));
        for (int hour = 0; hour < 24; hour++) {
            labelList.add(new LabelValueBean(String.valueOf(hour), String.valueOf(hour)));
        }
        return labelList;
    }

    /**
     *<br>[機  能] リストボックス用分ラベルの生成
     *<br>[解  説]
     *<br>[備  考]
     * @return 分ラベル
     */
    public static ArrayList < LabelValueBean > getMinuteLabel() {
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean("", "-1"));

        for (int hm = 0; hm < 6; hm++) {
            labelList.add(new LabelValueBean(String.valueOf(hm), String
                    .valueOf(hm)));
        }
        return labelList;
    }

    /**
     * <br>[機  能] 時間コンボ　時：分をそれぞれ生成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param usrSid ユーザSID
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void setTimeCombo(Tcd020ParamModel paramMdl, int usrSid, Connection con,
                            RequestModel reqMdl)
    throws SQLException {
        TimecardBiz tBiz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admMdl = tBiz.getTcdAdmConfModel(usrSid, con);
        paramMdl.setTcd020HourLabel(tBiz.getHourLabelList());
        paramMdl.setTcd020MinuteLabel(tBiz.getMinLabelList(admMdl));
    }

    /**
     * <br>[機  能] 一分刻みの分コンボを生成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     */
    private void __setStrikeMinuteCombo(Tcd020ParamModel paramMdl, RequestModel reqMdl) {
        TimecardBiz tBiz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admMdl = new TcdAdmConfModel();
        admMdl.setTacInterval(1);
        paramMdl.setTcd020StrikeMinuteLabel(tBiz.getMinLabelList(admMdl));
    }

    /**
     * <br>[機  能] タイムカードを登録・更新します。
     * <br>[解  説]
     * <br>[備  考] 既存データがある場合はupdate 無い場合はinsertします
     * @param paramMdl パラメータ情報
     * @param usModel ユーザモデル
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return TcdTcdataModel 更新対象
     * @throws SQLException SQL実行時例外
     */
    public TcdTcdataModel updateTcdTcdata(Tcd020ParamModel paramMdl,
                                        BaseUserModel usModel, Connection con,
                                        RequestModel reqMdl)
    throws SQLException {
        int ret = 0;

        int usrSid = usModel.getUsrsid();

        TcdTcdataModel tcMdl = new TcdTcdataModel();
        UDate sysDate = new UDate();

        //基本設定取得
        TimecardBiz tcBiz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admConf = tcBiz.getTcdAdmConfModel(usrSid, con);
        //更新内容を設定
        tcMdl.setUsrSid(Integer.parseInt(paramMdl.getUsrSid()));
        UDate tcdDate = new UDate();
        tcdDate.setYear(Integer.parseInt(paramMdl.getYear()));
        tcdDate.setMonth(Integer.parseInt(paramMdl.getMonth()));

        //打刻時間
        if (paramMdl.isPluralFlg() || __isLock(usModel, con, admConf.getTacLockStrike())) {
            tcMdl.setTcdStrikeIntime(null);
            tcMdl.setTcdStrikeOuttime(null);
            tcMdl.setTcdLockStrike(GSConstTimecard.LOCK_FLG);
        } else {

            if (!paramMdl.getTcd020StrikeInHour().equals("-1")
                    && !paramMdl.getTcd020StrikeInMinute().equals("-1")) {
                tcdDate.setHour(Integer.parseInt(paramMdl.getTcd020StrikeInHour()));
                tcdDate.setMinute(Integer.parseInt(paramMdl.getTcd020StrikeInMinute()));
                tcdDate.setSecond(0);
                Time strikeInTime = new Time(tcdDate.getTime());
                tcMdl.setTcdStrikeIntime(strikeInTime);
            }
            if (!paramMdl.getTcd020StrikeOutHour().equals("-1")
                    && !paramMdl.getTcd020StrikeOutMinute().equals("-1")) {
                tcdDate.setHour(Integer.parseInt(paramMdl.getTcd020StrikeOutHour()));
                tcdDate.setMinute(Integer.parseInt(paramMdl.getTcd020StrikeOutMinute()));
                tcdDate.setSecond(0);
                Time strikeOutTime = new Time(tcdDate.getTime());
                tcMdl.setTcdStrikeOuttime(strikeOutTime);
            }

        }

        //始業終業時間
        if (__isLock(usModel, con, admConf.getTacLockFlg())) {
            tcMdl.setTcdIntime(null);
            tcMdl.setTcdOuttime(null);
            tcMdl.setTcdLockTime(GSConstTimecard.LOCK_FLG);
        } else {
            if (!paramMdl.getTcd020InHour().equals("-1")
                    && !paramMdl.getTcd020InMinute().equals("-1")) {
                tcdDate.setHour(Integer.parseInt(paramMdl.getTcd020InHour()));
                tcdDate.setMinute(Integer.parseInt(paramMdl.getTcd020InMinute()));
                tcdDate.setSecond(0);
                Time inTime = new Time(tcdDate.getTime());
                tcMdl.setTcdIntime(inTime);
            }
            if (!paramMdl.getTcd020OutHour().equals("-1")
                    && !paramMdl.getTcd020OutMinute().equals("-1")) {
                tcdDate.setHour(Integer.parseInt(paramMdl.getTcd020OutHour()));
                tcdDate.setMinute(Integer.parseInt(paramMdl.getTcd020OutMinute()));
                tcdDate.setSecond(0);
                Time outTime = new Time(tcdDate.getTime());
                tcMdl.setTcdOuttime(outTime);
            }
        }

        tcMdl.setTcdStatus(GSConstTimecard.TCD_STATUS_EDIT);
        tcMdl.setTcdBiko(paramMdl.getTcd020Biko());

        //休日区分
        if (__isLock(usModel, con, admConf.getTacLockHoliday())) {
            tcMdl.setTcdHolkbn(GSConstTimecard.HOL_KBN_UNSELECT);
            tcMdl.setTcdHolother("");
            tcMdl.setTcdHolcnt(null);
            tcMdl.setTcdLockHoliday(GSConstTimecard.LOCK_FLG);
        } else {
            tcMdl.setTcdHolkbn(NullDefault.getInt(paramMdl.getTcd020HolKbn(),
                    GSConstTimecard.HOL_KBN_UNSELECT));
            tcMdl.setTcdHolother(NullDefault.getString(paramMdl.getTcd020HolValue(), ""));
            if (!StringUtil.isNullZeroStringSpace(paramMdl.getTcd020HolDays())) {
                tcMdl.setTcdHolcnt(new BigDecimal(paramMdl.getTcd020HolDays()));
                log__.debug("休日日数==>" + tcMdl.getTcdHolcnt().toString());
            }
        }

        //遅刻早退区分
        if (__isLock(usModel, con, admConf.getTacLockLate())) {
            tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_UNSELECT);
            tcMdl.setTcdSoukbn(GSConstTimecard.SOU_KBN_UNSELECT);
            tcMdl.setTcdLockLate(GSConstTimecard.LOCK_FLG);
        } else {
            tcMdl.setTcdChkkbn(NullDefault.getInt(paramMdl.getTcd020ChkKbn(),
                    GSConstTimecard.CHK_KBN_UNSELECT));
            tcMdl.setTcdSoukbn(NullDefault.getInt(paramMdl.getTcd020SouKbn(),
                    GSConstTimecard.SOU_KBN_UNSELECT));
        }


        tcMdl.setTcdAuid(usrSid);
        tcMdl.setTcdAdate(sysDate);
        tcMdl.setTcdEuid(usrSid);
        tcMdl.setTcdEdate(sysDate);
        //反映する日付を共用配列へ移行
        ArrayList<String> dayList = new ArrayList<String>();
        if (StringUtil.isNullZeroString(paramMdl.getEditDay())) {
            //複数
            log__.debug("複数更新");
            String[] days = paramMdl.getSelectDay();
            for (int i = 0; i < days.length; i++) {
                dayList.add(days[i]);
            }
        } else {
            //単体
            log__.debug("単体更新");
            dayList.add(paramMdl.getEditDay());
        }
        int year = Integer.parseInt(paramMdl.getYear());
        int month = Integer.parseInt(paramMdl.getMonth());
        int sime = admConf.getTacSimebi();
        TcdTcdataDao tcDao = new TcdTcdataDao(con);
        TimecardDao dao = new TimecardDao(con);

        for (int j = 0; j < dayList.size(); j++) {

            tcdDate = getEditDate(year, month, Integer.parseInt(dayList.get(j)), sime);
            tcMdl.setTcdDate(tcdDate);
            ret = dao.updateLock(tcMdl);
            if (ret < 1) {
                tcDao.insert(tcMdl);
            }
        }

        return tcMdl;
    }

    /**
     *
     * <br>[機  能] ユーザの種別が一般・グループ管理者・管理者かを判定します。
     * <br>[解  説] 管理者権限を持っている場合はグループ管理者権限よりも優先されます。
     * <br>[備  考]
     * @param usModel セッションユーザモデル
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return int ユーザ種別
     */
    private int __getUserKbn(BaseUserModel usModel, Connection con) throws SQLException {

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
     * <br>[機  能]タイムカード管理者設定情報を設定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param usModel セッションユーザモデル
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    private void __setAdmConf(BaseUserModel usModel, Connection con, Tcd020ParamModel paramMdl,
                            RequestModel reqMdl)
    throws SQLException {

        if (!paramMdl.getUsrKbn().equals(String.valueOf(GSConstTimecard.USER_KBN_NORMAL))) {
            paramMdl.setTcd020LockFlg(String.valueOf(GSConstTimecard.UNLOCK_FLG));
            paramMdl.setTcd020LockStrike(String.valueOf(GSConstTimecard.UNLOCK_FLG));
            paramMdl.setTcd020LockBiko(String.valueOf(GSConstTimecard.UNLOCK_FLG));
            paramMdl.setTcd020LockLate(String.valueOf(GSConstTimecard.UNLOCK_FLG));
            paramMdl.setTcd020LockHoliday(String.valueOf(GSConstTimecard.UNLOCK_FLG));
            return;
        }

        TimecardBiz tcdBiz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admConfModel = tcdBiz.getTcdAdmConfModel(usModel.getUsrsid(), con);
        paramMdl.setTcd020LockFlg(String.valueOf(admConfModel.getTacLockFlg()));
        paramMdl.setTcd020LockStrike(String.valueOf(admConfModel.getTacLockStrike()));
        paramMdl.setTcd020LockBiko(String.valueOf(admConfModel.getTacLockBiko()));
        paramMdl.setTcd020LockLate(String.valueOf(admConfModel.getTacLockLate()));
        paramMdl.setTcd020LockHoliday(String.valueOf(admConfModel.getTacLockHoliday()));
    }

    /**
     * <br>[機  能] ロックフラグを返します。
     * <br>[解  説]
     * <br>[備  考]
     * @param usModel セッションユーザモデル
     * @param con コネクション
     * @param lockFlg ロックフラグ(管理者設定)
     * @throws SQLException SQL実行時例外
     * @return boolean ロックフラグ
     */
    private boolean __isLock(BaseUserModel usModel, Connection con, int lockFlg)
    throws SQLException {

        if (__getUserKbn(usModel, con) == GSConstTimecard.USER_KBN_ADMIN
                || __getUserKbn(usModel, con) == GSConstTimecard.USER_KBN_GRPADM
                || lockFlg == GSConstTimecard.UNLOCK_FLG) {
            return false;
        }

        return true;
    }
}
