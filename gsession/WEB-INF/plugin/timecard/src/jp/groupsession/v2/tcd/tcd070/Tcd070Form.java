package jp.groupsession.v2.tcd.tcd070;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.dao.TcdTimezoneDao;
import jp.groupsession.v2.tcd.dao.TcdTimezoneModel;
import jp.groupsession.v2.tcd.tcd060.Tcd060Form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] タイムカード 管理者設定 時間帯設定登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd070Form extends Tcd060Form {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd070Form.class);

    /** 時間帯区分 */
    private String tcd070ZoneKbn__;
    /** 開始 時 */
    private String tcd070FrH__;
    /** 開始 分 */
    private String tcd070FrM__;
    /** 終了 時 */
    private String tcd070ToH__;
    /** 終了 分 */
    private String tcd070ToM__;
    /** ラベル 区分 */
    private List < LabelValueBean > tcd070ZoneLabel__ = null;
    /** ラベル 時 */
    private List < LabelValueBean > tcd070HourLabel__ = null;
    /** ラベル 分 */
    private List < LabelValueBean > tcd070MinuteLabel__ = null;

    /** 時間最大値 */
    private static final String MAX_HOUR = "24";
    /** 分最小値 */
    private static final String MIN_MINUTE = "0";
    /** 24:00セット時間 */
    private static final String MAX_HOUR_VALUE = "23";
    /** 24:00セット分 */
    private static final String MAX_MINUTE_VALUE = "59";

    /**
     * <p>tcd070ZoneLabel を取得します。
     * @return tcd070ZoneLabel
     */
    public List<LabelValueBean> getTcd070ZoneLabel() {
        return tcd070ZoneLabel__;
    }

    /**
     * <p>tcd070ZoneLabel をセットします。
     * @param tcd070ZoneLabel tcd070ZoneLabel
     */
    public void setTcd070ZoneLabel(List<LabelValueBean> tcd070ZoneLabel) {
        tcd070ZoneLabel__ = tcd070ZoneLabel;
    }

    /**
     * <p>tcd070FrH を取得します。
     * @return tcd070FrH
     */
    public String getTcd070FrH() {
        return tcd070FrH__;
    }

    /**
     * <p>tcd070FrH をセットします。
     * @param tcd070FrH tcd070FrH
     */
    public void setTcd070FrH(String tcd070FrH) {
        tcd070FrH__ = tcd070FrH;
    }

    /**
     * <p>tcd070FrM を取得します。
     * @return tcd070FrM
     */
    public String getTcd070FrM() {
        return tcd070FrM__;
    }

    /**
     * <p>tcd070FrM をセットします。
     * @param tcd070FrM tcd070FrM
     */
    public void setTcd070FrM(String tcd070FrM) {
        tcd070FrM__ = tcd070FrM;
    }

    /**
     * <p>tcd070ToH を取得します。
     * @return tcd070ToH
     */
    public String getTcd070ToH() {
        return tcd070ToH__;
    }

    /**
     * <p>tcd070ToH をセットします。
     * @param tcd070ToH tcd070ToH
     */
    public void setTcd070ToH(String tcd070ToH) {
        tcd070ToH__ = tcd070ToH;
    }

    /**
     * <p>tcd070ToM を取得します。
     * @return tcd070ToM
     */
    public String getTcd070ToM() {
        return tcd070ToM__;
    }

    /**
     * <p>tcd070ToM をセットします。
     * @param tcd070ToM tcd070ToM
     */
    public void setTcd070ToM(String tcd070ToM) {
        tcd070ToM__ = tcd070ToM;
    }

    /**
     * <p>tcd070HourLabel を取得します。
     * @return tcd070HourLabel
     */
    public List<LabelValueBean> getTcd070HourLabel() {
        return tcd070HourLabel__;
    }

    /**
     * <p>tcd070HourLabel をセットします。
     * @param tcd070HourLabel tcd070HourLabel
     */
    public void setTcd070HourLabel(List<LabelValueBean> tcd070HourLabel) {
        tcd070HourLabel__ = tcd070HourLabel;
    }

    /**
     * <p>tcd070MinuteLabel を取得します。
     * @return tcd070MinuteLabel
     */
    public List<LabelValueBean> getTcd070MinuteLabel() {
        return tcd070MinuteLabel__;
    }

    /**
     * <p>tcd070MinuteLabel をセットします。
     * @param tcd070MinuteLabel tcd070MinuteLabel
     */
    public void setTcd070MinuteLabel(List<LabelValueBean> tcd070MinuteLabel) {
        tcd070MinuteLabel__ = tcd070MinuteLabel;
    }

    /**
     * <p>tcd070ZoneKbn を取得します。
     * @return tcd070ZoneKbn
     */
    public String getTcd070ZoneKbn() {
        return tcd070ZoneKbn__;
    }

    /**
     * <p>tcd070ZoneKbn をセットします。
     * @param tcd070ZoneKbn tcd070ZoneKbn
     */
    public void setTcd070ZoneKbn(String tcd070ZoneKbn) {
        tcd070ZoneKbn__ = tcd070ZoneKbn;
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
    public ActionErrors validateCheck(
            RequestModel reqMdl,
            Connection con) throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage(reqMdl);
        String gsmsg = "";

        //未入力チェック
        boolean errorFlg = false;
        if (tcd070FrH__.equals("-1")) {
            gsmsg = gsMsg.getMessage("tcd.167");
            msg = new ActionMessage("error.select.required.text", gsmsg);
            errors.add("tcd070FrH__" + "error.select.required.text", msg);
            errorFlg = true;
        }
        //未入力チェック
        if (tcd070FrM__.equals("-1")) {
            gsmsg = gsMsg.getMessage("tcd.168");
            msg = new ActionMessage("error.select.required.text", gsmsg);
            errors.add("tcd070FrM__" + "error.select.required.text", msg);
            errorFlg = true;
        }
        //未入力チェック
        if (tcd070ToH__.equals("-1")) {
            gsmsg = gsMsg.getMessage("tcd.169");
            msg = new ActionMessage("error.select.required.text", gsmsg);
            errors.add("tcd070ToH__" + "error.select.required.text", msg);
            errorFlg = true;
        }
        //未入力チェック
        if (tcd070ToM__.equals("-1")) {
            gsmsg = gsMsg.getMessage("tcd.170");
            msg = new ActionMessage("error.select.required.text", gsmsg);
            errors.add("tcd070ToM__" + "error.select.required.text", msg);
            errorFlg = true;
        }

        if (!errorFlg) {
            String haniMsg = gsMsg.getMessage("tcd.172");
            //0:00～24:00範囲チェック
            if (!__isTimeRange(tcd070FrH__, tcd070FrM__)) {
                gsmsg = gsMsg.getMessage("tcd.171");
                msg = new ActionMessage("error.input.comp.text", gsmsg, haniMsg);
                errors.add("fromRange" + "error.input.comp.text", msg);
            }
            if (!__isTimeRange(tcd070ToH__, tcd070ToM__)) {
                gsmsg = gsMsg.getMessage("tcd.173");
                msg = new ActionMessage("error.input.comp.text", gsmsg, haniMsg);
                errors.add("toRange" + "error.input.comp.text", msg);
            }

            UDate inDate = new UDate();
            inDate.setZeroHhMmSs();
            UDate outDate = inDate.cloneUDate();
            inDate.setHour(Integer.parseInt(tcd070FrH__));
            inDate.setMinute(Integer.parseInt(tcd070FrM__));
            outDate.setHour(Integer.parseInt(tcd070ToH__));
            outDate.setMinute(Integer.parseInt(tcd070ToM__));

            //大小チェック
            if (inDate.compare(inDate, outDate) != UDate.LARGE) {
                String gsmsg2 = gsMsg.getMessage("cmn.start.end");
                String gsmsg3 = gsMsg.getMessage("cmn.start.lessthan.end");
                msg = new ActionMessage("error.input.comp.text", gsmsg2, gsmsg3);
                errors.add("" + "error.input.comp.text", msg);
            } else {
                String timezone = gsMsg.getMessage("tcd.tcd070.01");
                //時間帯が重複している場合はエラー
                log__.debug("時間帯の重複チェック");
                if (__isRepetitionTimeZone(con)) {
                    msg = new ActionMessage(
                            "error.input.double.timezone",
                            timezone,
                            timezone);
                    errors.add("error.input.double.timezone", msg);
                }
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 0:00～24:00範囲チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param hour 時
     * @param minute 分
     * @return boolean true=範囲内、false=範囲外
     */
    private boolean __isTimeRange(String hour, String minute) {

        if (hour.equals(MAX_HOUR) && !minute.equals(MIN_MINUTE)) {
            return false;
        }
        return true;
    }

    /**
     * <br>[機  能] 入力した時間帯が登録済み時間帯と重複しているか判定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:重複している false:重複していない
     * @throws SQLException SQL実行時例外
     */
    private boolean __isRepetitionTimeZone(Connection con) throws SQLException {

        boolean ret = false;
        UDate date1 = new UDate();
        date1.setZeroHhMmSs();
        date1.setHour(Integer.parseInt(tcd070FrH__));
        date1.setMinute(Integer.parseInt(tcd070FrM__));

        UDate date2 = new UDate();
        date2.setZeroHhMmSs();
        if (tcd070ToH__.equals(MAX_HOUR)) {
            date2.setHour(Integer.parseInt(MAX_HOUR_VALUE));
            date2.setMinute(Integer.parseInt(MAX_MINUTE_VALUE));
        } else {
            date2.setHour(Integer.parseInt(tcd070ToH__));
            date2.setMinute(Integer.parseInt(tcd070ToM__));
        }

        TcdTimezoneDao tzDao = new TcdTimezoneDao(con);
        int sid = NullDefault.getInt(getEditTimezoneSid(), -1);
        ArrayList<TcdTimezoneModel> tzList = tzDao.selectBetween(date1, date2, sid);
        for (TcdTimezoneModel tzMdl : tzList) {
            Time frTime = tzMdl.getTtzFrtime();
            Time toTime = tzMdl.getTtzTotime();
            log__.debug("frTime.toString()==>" + frTime.toString());
            log__.debug("toTime.toString()==>" + toTime.toString());
            log__.debug("frTime.compareTo(toTime)==>" + frTime.compareTo(toTime));
            if (frTime.compareTo(toTime) < UDate.EQUAL) {
                log__.debug("toが00:00:00以外");
                //toが00:00:00以外の場合はエラー
                ret = true;

            } else {
                log__.debug("toが00:00:00");
                //toが00:00:00の場合
                if (frTime.compareTo(toTime) == UDate.EQUAL) {
                    log__.debug("fromが00:00:00");
                    //fromが00:00:00の場合はエラー
                    ret = true;

                } else {
                    log__.debug("fromが00:00:00以外");
                    Time formTo = new Time(date2.getTime());

                    UDate dbFrDate = new UDate();
                    dbFrDate.setTime(frTime.getTime());
                    dbFrDate.setSecond(0);
                    dbFrDate.setMilliSecond(0);
                    log__.debug("dbFrDate.toString()==>" + dbFrDate.toString());

                    UDate formToDate = new UDate();
                    formToDate.setTime(formTo.getTime());
                    formToDate.setSecond(0);
                    formToDate.setMilliSecond(0);
                    log__.debug("formToDate.toString()==>" + formToDate.toString());

                    if (formToDate.compareDateHM(dbFrDate) < UDate.EQUAL) {
                        log__.debug("画面toより登録済みfromが小さい");
                        //画面toより登録済みfromが小さい場合はエラー
                        ret = true;
                    }
                }
            }
        }

        return ret;
    }
}
