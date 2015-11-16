package jp.groupsession.v2.tcd.tcd050;

import java.util.ArrayList;

import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.man020.Man020HolidayModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.tcd030.Tcd030Form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] タイムカード 管理者設定 基本設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd050Form extends Tcd030Form {

    /** 間隔 */
    private String tcd050BetweenSlt__;
    /** 進数 */
    private String tcd050SinsuSlt__;
    /** 締日 */
    private String tcd050LimitDaySlt__;
    /** 間隔リスト */
    private ArrayList <LabelValueBean> tcd050BetweenLabel__;
    /** 進数リスト */
    private ArrayList <LabelValueBean> tcd050SinsuLabel__;
    /** 締日リスト */
    private ArrayList <LabelValueBean> tcd050LimitDayLabel__;
    /** 曜日multibox */
    private String[] tcd050SelectWeek__;
    /** 休日multibox */
    private String[] tcd050SelectHoliDay__;
    /** 休日表示年 */
    private String tcd050DspHolidayYear__;
    /** 休日リスト情報 */
    private ArrayList<Man020HolidayModel> tcd050HolidayInfoList__;

    /** 初期表示フラグ */
    private int tcd050initFlg__ = 0;

    /** ロック*/
    private String tcd050LockChk__;
    /** ロックリスト */
    private ArrayList <LabelValueBean> tcd050LockLabel__;

    /**
     * <p>tcd050LockLabel を取得します。
     * @return tcd050LockLabel
     */
    public ArrayList<LabelValueBean> getTcd050LockLabel() {
        return tcd050LockLabel__;
    }

    /**
     * <p>tcd050LockLabel をセットします。
     * @param tcd050LockLabel tcd050LockLabel
     */
    public void setTcd050LockLabel(ArrayList<LabelValueBean> tcd050LockLabel) {
        tcd050LockLabel__ = tcd050LockLabel;
    }

    /**
     * <p>tcd050LockChk を取得します。
     * @return tcd050LockChk
     */
    public String getTcd050LockChk() {
        return tcd050LockChk__;
    }

    /**
     * <p>tcd050LockChk をセットします。
     * @param tcd050LockChk tcd050LockChk
     */
    public void setTcd050LockChk(String tcd050LockChk) {
        tcd050LockChk__ = tcd050LockChk;
    }

    /**
     * <p>tcd050BetweenSlt を取得します。
     * @return tcd050BetweenSlt
     */
    public String getTcd050BetweenSlt() {
        return tcd050BetweenSlt__;
    }

    /**
     * <p>tcd050BetweenSlt をセットします。
     * @param tcd050BetweenSlt tcd050BetweenSlt
     */
    public void setTcd050BetweenSlt(String tcd050BetweenSlt) {
        tcd050BetweenSlt__ = tcd050BetweenSlt;
    }

    /**
     * <p>tcd050LimitDaySlt を取得します。
     * @return tcd050LimitDaySlt
     */
    public String getTcd050LimitDaySlt() {
        return tcd050LimitDaySlt__;
    }

    /**
     * <p>tcd050LimitDaySlt をセットします。
     * @param tcd050LimitDaySlt tcd050LimitDaySlt
     */
    public void setTcd050LimitDaySlt(String tcd050LimitDaySlt) {
        tcd050LimitDaySlt__ = tcd050LimitDaySlt;
    }

    /**
     * <p>tcd050SinsuSlt を取得します。
     * @return tcd050SinsuSlt
     */
    public String getTcd050SinsuSlt() {
        return tcd050SinsuSlt__;
    }

    /**
     * <p>tcd050SinsuSlt をセットします。
     * @param tcd050SinsuSlt tcd050SinsuSlt
     */
    public void setTcd050SinsuSlt(String tcd050SinsuSlt) {
        tcd050SinsuSlt__ = tcd050SinsuSlt;
    }

    /**
     * <p>tcd050BetweenLabel を取得します。
     * @return tcd050BetweenLabel
     */
    public ArrayList<LabelValueBean> getTcd050BetweenLabel() {
        return tcd050BetweenLabel__;
    }

    /**
     * <p>tcd050BetweenLabel をセットします。
     * @param tcd050BetweenLabel tcd050BetweenLabel
     */
    public void setTcd050BetweenLabel(ArrayList<LabelValueBean> tcd050BetweenLabel) {
        tcd050BetweenLabel__ = tcd050BetweenLabel;
    }

    /**
     * <p>tcd050DspHolidayYear を取得します。
     * @return tcd050DspHolidayYear
     */
    public String getTcd050DspHolidayYear() {
        return tcd050DspHolidayYear__;
    }

    /**
     * <p>tcd050DspHolidayYear をセットします。
     * @param tcd050DspHolidayYear tcd050DspHolidayYear
     */
    public void setTcd050DspHolidayYear(String tcd050DspHolidayYear) {
        tcd050DspHolidayYear__ = tcd050DspHolidayYear;
    }

    /**
     * <p>tcd050HolidayInfoList を取得します。
     * @return tcd050HolidayInfoList
     */
    public ArrayList<Man020HolidayModel> getTcd050HolidayInfoList() {
        return tcd050HolidayInfoList__;
    }

    /**
     * <p>tcd050HolidayInfoList をセットします。
     * @param tcd050HolidayInfoList tcd050HolidayInfoList
     */
    public void setTcd050HolidayInfoList(ArrayList<Man020HolidayModel> tcd050HolidayInfoList) {
        tcd050HolidayInfoList__ = tcd050HolidayInfoList;
    }

    /**
     * <p>tcd050LimitDayLabel を取得します。
     * @return tcd050LimitDayLabel
     */
    public ArrayList<LabelValueBean> getTcd050LimitDayLabel() {
        return tcd050LimitDayLabel__;
    }

    /**
     * <p>tcd050LimitDayLabel をセットします。
     * @param tcd050LimitDayLabel tcd050LimitDayLabel
     */
    public void setTcd050LimitDayLabel(ArrayList<LabelValueBean> tcd050LimitDayLabel) {
        tcd050LimitDayLabel__ = tcd050LimitDayLabel;
    }

    /**
     * <p>tcd050SelectHoliDay を取得します。
     * @return tcd050SelectHoliDay
     */
    public String[] getTcd050SelectHoliDay() {
        return tcd050SelectHoliDay__;
    }

    /**
     * <p>tcd050SelectHoliDay をセットします。
     * @param tcd050SelectHoliDay tcd050SelectHoliDay
     */
    public void setTcd050SelectHoliDay(String[] tcd050SelectHoliDay) {
        tcd050SelectHoliDay__ = tcd050SelectHoliDay;
    }

    /**
     * <p>tcd050SelectWeek を取得します。
     * @return tcd050SelectWeek
     */
    public String[] getTcd050SelectWeek() {
        return tcd050SelectWeek__;
    }

    /**
     * <p>tcd050SelectWeek をセットします。
     * @param tcd050SelectWeek tcd050SelectWeek
     */
    public void setTcd050SelectWeek(String[] tcd050SelectWeek) {
        tcd050SelectWeek__ = tcd050SelectWeek;
    }

    /**
     * <p>tcd050SinsuLabel を取得します。
     * @return tcd050SinsuLabel
     */
    public ArrayList<LabelValueBean> getTcd050SinsuLabel() {
        return tcd050SinsuLabel__;
    }

    /**
     * <p>tcd050SinsuLabel をセットします。
     * @param tcd050SinsuLabel tcd050SinsuLabel
     */
    public void setTcd050SinsuLabel(ArrayList<LabelValueBean> tcd050SinsuLabel) {
        tcd050SinsuLabel__ = tcd050SinsuLabel;
    }

    /**
     * <p>tcd050initFlg を取得します。
     * @return tcd050initFlg
     */
    public int getTcd050initFlg() {
        return tcd050initFlg__;
    }

    /**
     * <p>tcd050initFlg をセットします。
     * @param tcd050initFlg tcd050initFlg
     */
    public void setTcd050initFlg(int tcd050initFlg) {
        tcd050initFlg__ = tcd050initFlg;
    }

    /**
     * <br>[機  能] タイムカード基本設定画面の入力チェックを行います
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return ActionErrors
     */
    public ActionErrors validateCheck(RequestModel reqMdl) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage(reqMdl);
        String errMsg = gsMsg.getMessage("tcd.179");
        String kouShin = gsMsg.getMessage("tcd.183");
        String shin60 = gsMsg.getMessage("tcd.164");

        //1分・10分間隔の場合、10進数はエラー
        if (tcd050BetweenSlt__.equals(String.valueOf(GSConstTimecard.TIMECARD_BETWEEN[0]))
         || tcd050BetweenSlt__.equals(String.valueOf(GSConstTimecard.TIMECARD_BETWEEN[1]))
        ) {
            if (tcd050SinsuSlt__.equals(String.valueOf(GSConstTimecard.TIMECARD_SINSU[0]))) {
                //エラー
                msg = new ActionMessage("error.select.conf.badvalue",
                                        errMsg,
                                        kouShin,
                                        shin60);
                errors.add("" + "error.select.conf.badvalue", msg);
            }
        }
        return errors;
    }
}
