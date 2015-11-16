package jp.groupsession.v2.tcd.tcd020;

import java.util.ArrayList;

import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.tcd.tcd010.Tcd010ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] タイムカード編集画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd020ParamModel extends Tcd010ParamModel {
    /** ユーザー種別 0:一般 1:グループ管理者 2:管理者 */
    private String usrKbn__;

    /** 名前 */
    private String tcd020Name__;
    /** 日付 */
    private String tcd020Date__;
    /** 打刻始業時間　時 */
    private String tcd020StrikeInHour__;
    /** 打刻始業時間　分 */
    private String tcd020StrikeInMinute__;
    /** 終業時間　時 */
    private String tcd020StrikeOutHour__;
    /** 終業時間　分 */
    private String tcd020StrikeOutMinute__;
    /** 始業時間　時 */
    private String tcd020InHour__;
    /** 始業時間　分 */
    private String tcd020InMinute__;
    /** 終業時間　時 */
    private String tcd020OutHour__;
    /** 終業時間　分 */
    private String tcd020OutMinute__;
    /** 備考 */
    private String tcd020Biko__;
    /** 休日区分 */
    private String tcd020HolKbn__;
    /** その他休日内容 */
    private String tcd020HolValue__;
    /** 休日日数 */
    private String tcd020HolDays__;
    /** 遅刻区分 */
    private String tcd020ChkKbn__ = null;
    /** 早退区分 */
    private String tcd020SouKbn__ = null;

    /** 打刻時間 テキスト表示 */
    private String tcd020StrikeTimeStr__;
    /** 開始時間 テキスト表示 */
    private String tcd020InTimeStr__;
    /** 終業時間 テキスト表示 */
    private String tcd020OutTimeStr__;

    /** 時リスト */
    private ArrayList < LabelValueBean > tcd020HourLabel__;

    /** 分リスト */
    private ArrayList < LabelValueBean > tcd020MinuteLabel__;

    /** 分リスト（打刻時間） */
    private ArrayList < LabelValueBean > tcd020StrikeMinuteLabel__;

    /** 始業終業時間編集権限 */
    private String tcd020LockFlg__ = String.valueOf(GSConstTimecard.UNLOCK_FLG);
    /** 打刻時間編集権限 */
    private String tcd020LockStrike__ = String.valueOf(GSConstTimecard.UNLOCK_FLG);
    /** 備考入力必須 */
    private String tcd020LockBiko__ = String.valueOf(GSConstTimecard.BIKO_UNNECESSARY_FLG);
    /** 早退区分編集権限 */
    private String tcd020LockLate__ = String.valueOf(GSConstTimecard.UNLOCK_FLG);
    /** 休日編集権限 */
    private String tcd020LockHoliday__ = String.valueOf(GSConstTimecard.UNLOCK_FLG);

    /** 複数編集フラグ*/
    private boolean pluralFlg__ = GSConstTimecard.PLURAL_FLG_SINGLE;

    /**
     * <p>tcd020LockFlg を取得します。
     * @return tcd020LockFlg
     */
    public String getTcd020LockFlg() {
        return tcd020LockFlg__;
    }

    /**
     * <p>tcd020LockFlg をセットします。
     * @param tcd020LockFlg tcd020LockFlg
     */
    public void setTcd020LockFlg(String tcd020LockFlg) {
        tcd020LockFlg__ = tcd020LockFlg;
    }

    /**
     * <p>tcd020LockHoliday を取得します。
     * @return tcd020LockHoliday
     */
    public String getTcd020LockHoliday() {
        return tcd020LockHoliday__;
    }

    /**
     * <p>tcd020LockHoliday をセットします。
     * @param tcd020LockHoliday tcd020LockHoliday
     */
    public void setTcd020LockHoliday(String tcd020LockHoliday) {
        tcd020LockHoliday__ = tcd020LockHoliday;
    }

    /**
     * <p>tcd020LockLate を取得します。
     * @return tcd020LockLate
     */
    public String getTcd020LockLate() {
        return tcd020LockLate__;
    }

    /**
     * <p>tcd020LockLate をセットします。
     * @param tcd020LockLate tcd020LockLate
     */
    public void setTcd020LockLate(String tcd020LockLate) {
        tcd020LockLate__ = tcd020LockLate;
    }

    /**
     * <p>tcd020LockStrike を取得します。
     * @return tcd020LockStrike
     */
    public String getTcd020LockStrike() {
        return tcd020LockStrike__;
    }

    /**
     * <p>tcd020LockStrike をセットします。
     * @param tcd020LockStrike tcd020LockStrike
     */
    public void setTcd020LockStrike(String tcd020LockStrike) {
        tcd020LockStrike__ = tcd020LockStrike;
    }

    /**
     * <p>tcd020StrikeMinuteLabel を取得します。
     * @return tcd020StrikeMinuteLabel
     */
    public ArrayList<LabelValueBean> getTcd020StrikeMinuteLabel() {
        return tcd020StrikeMinuteLabel__;
    }

    /**
     * <p>tcd020StrikeMinuteLabel をセットします。
     * @param tcd020StrikeMinuteLabel tcd020StrikeMinuteLabel
     */
    public void setTcd020StrikeMinuteLabel(
            ArrayList<LabelValueBean> tcd020StrikeMinuteLabel) {
        tcd020StrikeMinuteLabel__ = tcd020StrikeMinuteLabel;
    }

    /**
     * <p>tcd020ChkKbn を取得します。
     * @return tcd020ChkKbn
     */
    public String getTcd020ChkKbn() {
        return tcd020ChkKbn__;
    }

    /**
     * <p>tcd020ChkKbn をセットします。
     * @param tcd020ChkKbn tcd020ChkKbn
     */
    public void setTcd020ChkKbn(String tcd020ChkKbn) {
        tcd020ChkKbn__ = tcd020ChkKbn;
    }

    /**
     * <p>tcd020SouKbn を取得します。
     * @return tcd020SouKbn
     */
    public String getTcd020SouKbn() {
        return tcd020SouKbn__;
    }

    /**
     * <p>tcd020SouKbn をセットします。
     * @param tcd020SouKbn tcd020SouKbn
     */
    public void setTcd020SouKbn(String tcd020SouKbn) {
        tcd020SouKbn__ = tcd020SouKbn;
    }

    /**
     * <p>tcd020Biko を取得します。
     * @return tcd020Biko
     */
    public String getTcd020Biko() {
        return tcd020Biko__;
    }

    /**
     * <p>tcd020Biko をセットします。
     * @param tcd020Biko tcd020Biko
     */
    public void setTcd020Biko(String tcd020Biko) {
        tcd020Biko__ = tcd020Biko;
    }

    /**
     * <p>tcd020Date を取得します。
     * @return tcd020Date
     */
    public String getTcd020Date() {
        return tcd020Date__;
    }

    /**
     * <p>tcd020Date をセットします。
     * @param tcd020Date tcd020Date
     */
    public void setTcd020Date(String tcd020Date) {
        tcd020Date__ = tcd020Date;
    }

    /**
     * <p>tcd020HolDays を取得します。
     * @return tcd020HolDays
     */
    public String getTcd020HolDays() {
        return tcd020HolDays__;
    }

    /**
     * <p>tcd020HolDays をセットします。
     * @param tcd020HolDays tcd020HolDays
     */
    public void setTcd020HolDays(String tcd020HolDays) {
        tcd020HolDays__ = tcd020HolDays;
    }

    /**
     * <p>tcd020HolKbn を取得します。
     * @return tcd020HolKbn
     */
    public String getTcd020HolKbn() {
        return tcd020HolKbn__;
    }

    /**
     * <p>tcd020HolKbn をセットします。
     * @param tcd020HolKbn tcd020HolKbn
     */
    public void setTcd020HolKbn(String tcd020HolKbn) {
        tcd020HolKbn__ = tcd020HolKbn;
    }

    /**
     * <p>tcd020HolValue を取得します。
     * @return tcd020HolValue
     */
    public String getTcd020HolValue() {
        return tcd020HolValue__;
    }

    /**
     * <p>tcd020HolValue をセットします。
     * @param tcd020HolValue tcd020HolValue
     */
    public void setTcd020HolValue(String tcd020HolValue) {
        tcd020HolValue__ = tcd020HolValue;
    }

    /**
     * <p>tcd020HourLabel を取得します。
     * @return tcd020HourLabel
     */
    public ArrayList<LabelValueBean> getTcd020HourLabel() {
        return tcd020HourLabel__;
    }

    /**
     * <p>tcd020HourLabel をセットします。
     * @param tcd020HourLabel tcd020HourLabel
     */
    public void setTcd020HourLabel(ArrayList<LabelValueBean> tcd020HourLabel) {
        tcd020HourLabel__ = tcd020HourLabel;
    }

    /**
     * <p>tcd020InHour を取得します。
     * @return tcd020InHour
     */
    public String getTcd020InHour() {
        return tcd020InHour__;
    }

    /**
     * <p>tcd020InHour をセットします。
     * @param tcd020InHour tcd020InHour
     */
    public void setTcd020InHour(String tcd020InHour) {
        tcd020InHour__ = tcd020InHour;
    }

    /**
     * <p>tcd020InMinute を取得します。
     * @return tcd020InMinute
     */
    public String getTcd020InMinute() {
        return tcd020InMinute__;
    }

    /**
     * <p>tcd020InMinute をセットします。
     * @param tcd020InMinute tcd020InMinute
     */
    public void setTcd020InMinute(String tcd020InMinute) {
        tcd020InMinute__ = tcd020InMinute;
    }

    /**
     * <p>tcd020MinuteLabel を取得します。
     * @return tcd020MinuteLabel
     */
    public ArrayList<LabelValueBean> getTcd020MinuteLabel() {
        return tcd020MinuteLabel__;
    }

    /**
     * <p>tcd020MinuteLabel をセットします。
     * @param tcd020MinuteLabel tcd020MinuteLabel
     */
    public void setTcd020MinuteLabel(ArrayList<LabelValueBean> tcd020MinuteLabel) {
        tcd020MinuteLabel__ = tcd020MinuteLabel;
    }

    /**
     * <p>tcd020Name を取得します。
     * @return tcd020Name
     */
    public String getTcd020Name() {
        return tcd020Name__;
    }

    /**
     * <p>tcd020Name をセットします。
     * @param tcd020Name tcd020Name
     */
    public void setTcd020Name(String tcd020Name) {
        tcd020Name__ = tcd020Name;
    }

    /**
     * <p>tcd020OutHour を取得します。
     * @return tcd020OutHour
     */
    public String getTcd020OutHour() {
        return tcd020OutHour__;
    }

    /**
     * <p>tcd020OutHour をセットします。
     * @param tcd020OutHour tcd020OutHour
     */
    public void setTcd020OutHour(String tcd020OutHour) {
        tcd020OutHour__ = tcd020OutHour;
    }

    /**
     * <p>tcd020OutMinute を取得します。
     * @return tcd020OutMinute
     */
    public String getTcd020OutMinute() {
        return tcd020OutMinute__;
    }

    /**
     * <p>tcd020OutMinute をセットします。
     * @param tcd020OutMinute tcd020OutMinute
     */
    public void setTcd020OutMinute(String tcd020OutMinute) {
        tcd020OutMinute__ = tcd020OutMinute;
    }

    /**
     * <p>tcd020StrikeInHour を取得します。
     * @return tcd020StrikeInHour
     */
    public String getTcd020StrikeInHour() {
        return tcd020StrikeInHour__;
    }

    /**
     * <p>tcd020StrikeInHour をセットします。
     * @param tcd020StrikeInHour tcd020StrikeInHour
     */
    public void setTcd020StrikeInHour(String tcd020StrikeInHour) {
        tcd020StrikeInHour__ = tcd020StrikeInHour;
    }

    /**
     * <p>tcd020StrikeInMinute を取得します。
     * @return tcd020StrikeInMinute
     */
    public String getTcd020StrikeInMinute() {
        return tcd020StrikeInMinute__;
    }

    /**
     * <p>tcd020StrikeInMinute をセットします。
     * @param tcd020StrikeInMinute tcd020StrikeInMinute
     */
    public void setTcd020StrikeInMinute(String tcd020StrikeInMinute) {
        tcd020StrikeInMinute__ = tcd020StrikeInMinute;
    }

    /**
     * <p>tcd020StrikeOutHour を取得します。
     * @return tcd020StrikeOutHour
     */
    public String getTcd020StrikeOutHour() {
        return tcd020StrikeOutHour__;
    }

    /**
     * <p>tcd020StrikeOutHour をセットします。
     * @param tcd020StrikeOutHour tcd020StrikeOutHour
     */
    public void setTcd020StrikeOutHour(String tcd020StrikeOutHour) {
        tcd020StrikeOutHour__ = tcd020StrikeOutHour;
    }

    /**
     * <p>tcd020StrikeOutMinute を取得します。
     * @return tcd020StrikeOutMinute
     */
    public String getTcd020StrikeOutMinute() {
        return tcd020StrikeOutMinute__;
    }

    /**
     * <p>tcd020StrikeOutMinute をセットします。
     * @param tcd020StrikeOutMinute tcd020StrikeOutMinute
     */
    public void setTcd020StrikeOutMinute(String tcd020StrikeOutMinute) {
        tcd020StrikeOutMinute__ = tcd020StrikeOutMinute;
    }

    /**
     * <p>usrKbn を取得します。
     * @return usrKbn
     */
    public String getUsrKbn() {
        return usrKbn__;
    }

    /**
     * <p>usrKbn をセットします。
     * @param usrKbn usrKbn
     */
    public void setUsrKbn(String usrKbn) {
        usrKbn__ = usrKbn;
    }

    /**
     * <p>tcd020LockBiko を取得します。
     * @return tcd020LockBiko
     */
    public String getTcd020LockBiko() {
        return tcd020LockBiko__;
    }

    /**
     * <p>tcd020LockBiko をセットします。
     * @param tcd020LockBiko tcd020LockBiko
     */
    public void setTcd020LockBiko(String tcd020LockBiko) {
        tcd020LockBiko__ = tcd020LockBiko;
    }

    /**
     * <p>pluralFlg を取得します。
     * @return pluralFlg
     */
    public boolean isPluralFlg() {
        return pluralFlg__;
    }

    /**
     * <p>pluralFlg をセットします。
     * @param pluralFlg pluralFlg
     */
    public void setPluralFlg(boolean pluralFlg) {
        pluralFlg__ = pluralFlg;
    }

    /**
     * <p>tcd020InTimeStr を取得します。
     * @return tcd020InTimeStr
     */
    public String getTcd020InTimeStr() {
        return tcd020InTimeStr__;
    }

    /**
     * <p>tcd020InTimeStr をセットします。
     * @param tcd020InTimeStr tcd020InTimeStr
     */
    public void setTcd020InTimeStr(String tcd020InTimeStr) {
        tcd020InTimeStr__ = tcd020InTimeStr;
    }

    /**
     * <p>tcd020OutTimeStr を取得します。
     * @return tcd020OutTimeStr
     */
    public String getTcd020OutTimeStr() {
        return tcd020OutTimeStr__;
    }

    /**
     * <p>tcd020OutTimeStr をセットします。
     * @param tcd020OutTimeStr tcd020OutTimeStr
     */
    public void setTcd020OutTimeStr(String tcd020OutTimeStr) {
        tcd020OutTimeStr__ = tcd020OutTimeStr;
    }

    /**
     * <p>tcd020StrikeTimeStr を取得します。
     * @return tcd020StrikeTimeStr
     */
    public String getTcd020StrikeTimeStr() {
        return tcd020StrikeTimeStr__;
    }

    /**
     * <p>tcd020StrikeTimeStr をセットします。
     * @param tcd020StrikeTimeStr tcd020StrikeTimeStr
     */
    public void setTcd020StrikeTimeStr(String tcd020StrikeTimeStr) {
        tcd020StrikeTimeStr__ = tcd020StrikeTimeStr;
    }
}