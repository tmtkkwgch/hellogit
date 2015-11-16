package jp.groupsession.v2.tcd.model;

import java.math.BigDecimal;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.AbstractModel;


/**
 * <br>[機  能] タイムカード情報の集計値を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class TcdTotalValueModel extends AbstractModel {

    /** 集計月 */
    private String kadoMonth__ = null;
    /** 集計開始日付 */
    private UDate startDate__ = null;
    /** 集計終了日付 */
    private UDate endDate__ = null;
    //集計用
    /** 稼動基準日数 */
    private BigDecimal kadoBaseDays__ = null;
    /** 稼動実績日数 */
    private BigDecimal kadoDays__ = null;
    /** 稼動基準時間数 */
    private BigDecimal kadoBaseHours__ = null;
    /** 稼動実績時間数 */
    private BigDecimal kadoHours__ = null;
    /** 残業日数 */
    private BigDecimal zangyoDays__ = null;
    /** 残業時間数 */
    private BigDecimal zangyoHours__ = null;
    /** 深夜日数 */
    private BigDecimal sinyaDays__ = null;
    /** 深夜時間数 */
    private BigDecimal sinyaHours__ = null;
    /** 休日出勤日数 */
    private BigDecimal kyusyutuDays__ = null;
    /** 休日出勤時間数 */
    private BigDecimal kyusyutuHours__ = null;
    /** 遅刻日数 */
    private BigDecimal chikokuDays__ = null;
    /** 遅刻時間 */
    private BigDecimal chikokuTimes__ = null;
    /** 早退日数 */
    private BigDecimal soutaiDays__ = null;
    /** 早退時間 */
    private BigDecimal soutaiTimes__ = null;
    /** 欠勤日数 */
    private BigDecimal kekkinDays__ = null;
    /** 慶弔休暇日数 */
    private BigDecimal keityoDays__ = null;
    /** 有給休暇日数 */
    private BigDecimal yuukyuDays__ = null;
    /** 代休日数 */
    private BigDecimal daikyuDays__ = null;
    /** その他休暇日数 */
    private BigDecimal sonotaDays__ = null;
    //画面表示用
    /** 稼動基準日数 */
    private String kadoBaseDaysStr__ = null;
    /** 稼動実績日数 */
    private String kadoDaysStr__ = null;
    /** 稼動基準時間数 */
    private String kadoBaseHoursStr__ = null;
    /** 稼動実績時間数 */
    private String kadoHoursStr__ = null;
    /** 残業日数 */
    private String zangyoDaysStr__ = null;
    /** 残業時間数 */
    private String zangyoHoursStr__ = null;
    /** 深夜日数 */
    private String sinyaDaysStr__ = null;
    /** 深夜時間数 */
    private String sinyaHoursStr__ = null;
    /** 休日出勤日数 */
    private String kyusyutuDaysStr__ = null;
    /** 休日出勤時間数 */
    private String kyusyutuHoursStr__ = null;
    /** 遅刻日数 */
    private String chikokuDaysStr__ = null;
    /** 遅刻時間 */
    private String chikokuTimesStr__ = null;
    /** 早退日数 */
    private String soutaiDaysStr__ = null;
    /** 早退時間 */
    private String soutaiTimesStr__ = null;
    /** 欠勤日数 */
    private String kekkinDaysStr__ = null;
    /** 慶弔休暇日数 */
    private String keityoDaysStr__ = null;
    /** 有給休暇日数 */
    private String yuukyuDaysStr__ = null;
    /** 代休日数 */
    private String daikyuDaysStr__ = null;
    /** その他休暇日数 */
    private String sonotaDaysStr__ = null;

    /**
     * <p>sinyaDaysStr を取得します。
     * @return sinyaDaysStr
     */
    public String getSinyaDaysStr() {
        return sinyaDaysStr__;
    }
    /**
     * <p>sinyaDaysStr をセットします。
     * @param sinyaDaysStr sinyaDaysStr
     */
    public void setSinyaDaysStr(String sinyaDaysStr) {
        sinyaDaysStr__ = sinyaDaysStr;
    }
    /**
     * <p>sinyaHoursStr を取得します。
     * @return sinyaHoursStr
     */
    public String getSinyaHoursStr() {
        return sinyaHoursStr__;
    }
    /**
     * <p>sinyaHoursStr をセットします。
     * @param sinyaHoursStr sinyaHoursStr
     */
    public void setSinyaHoursStr(String sinyaHoursStr) {
        sinyaHoursStr__ = sinyaHoursStr;
    }
    /**
     * <p>sinyaDays を取得します。
     * @return sinyaDays
     */
    public BigDecimal getSinyaDays() {
        return sinyaDays__;
    }
    /**
     * <p>sinyaDays をセットします。
     * @param sinyaDays sinyaDays
     */
    public void setSinyaDays(BigDecimal sinyaDays) {
        sinyaDays__ = sinyaDays;
    }
    /**
     * <p>sinyaHours を取得します。
     * @return sinyaHours
     */
    public BigDecimal getSinyaHours() {
        return sinyaHours__;
    }
    /**
     * <p>sinyaHours をセットします。
     * @param sinyaHours sinyaHours
     */
    public void setSinyaHours(BigDecimal sinyaHours) {
        sinyaHours__ = sinyaHours;
    }
    /**
     * <p>chikokuDays を取得します。
     * @return chikokuDays
     */
    public BigDecimal getChikokuDays() {
        return chikokuDays__;
    }
    /**
     * <p>chikokuDays をセットします。
     * @param chikokuDays chikokuDays
     */
    public void setChikokuDays(BigDecimal chikokuDays) {
        chikokuDays__ = chikokuDays;
    }
    /**
     * <p>chikokuTimes を取得します。
     * @return chikokuTimes
     */
    public BigDecimal getChikokuTimes() {
        return chikokuTimes__;
    }
    /**
     * <p>chikokuTimes をセットします。
     * @param chikokuTimes chikokuTimes
     */
    public void setChikokuTimes(BigDecimal chikokuTimes) {
        chikokuTimes__ = chikokuTimes;
    }
    /**
     * <p>chikokuDaysStr を取得します。
     * @return chikokuDaysStr
     */
    public String getChikokuDaysStr() {
        return chikokuDaysStr__;
    }
    /**
     * <p>chikokuDaysStr をセットします。
     * @param chikokuDaysStr chikokuDaysStr
     */
    public void setChikokuDaysStr(String chikokuDaysStr) {
        chikokuDaysStr__ = chikokuDaysStr;
    }
    /**
     * <p>chikokuTimesStr を取得します。
     * @return chikokuTimesStr
     */
    public String getChikokuTimesStr() {
        return chikokuTimesStr__;
    }
    /**
     * <p>chikokuTimesStr をセットします。
     * @param chikokuTimesStr chikokuTimesStr
     */
    public void setChikokuTimesStr(String chikokuTimesStr) {
        chikokuTimesStr__ = chikokuTimesStr;
    }
    /**
     * <p>daikyuDays を取得します。
     * @return daikyuDays
     */
    public BigDecimal getDaikyuDays() {
        return daikyuDays__;
    }
    /**
     * <p>daikyuDays をセットします。
     * @param daikyuDays daikyuDays
     */
    public void setDaikyuDays(BigDecimal daikyuDays) {
        daikyuDays__ = daikyuDays;
    }
    /**
     * <p>daikyuDaysStr を取得します。
     * @return daikyuDaysStr
     */
    public String getDaikyuDaysStr() {
        return daikyuDaysStr__;
    }
    /**
     * <p>daikyuDaysStr をセットします。
     * @param daikyuDaysStr daikyuDaysStr
     */
    public void setDaikyuDaysStr(String daikyuDaysStr) {
        daikyuDaysStr__ = daikyuDaysStr;
    }
    /**
     * <p>endDate を取得します。
     * @return endDate
     */
    public UDate getEndDate() {
        return endDate__;
    }
    /**
     * <p>endDate をセットします。
     * @param endDate endDate
     */
    public void setEndDate(UDate endDate) {
        endDate__ = endDate;
    }
    /**
     * <p>kadoBaseDays を取得します。
     * @return kadoBaseDays
     */
    public BigDecimal getKadoBaseDays() {
        return kadoBaseDays__;
    }
    /**
     * <p>kadoBaseDays をセットします。
     * @param kadoBaseDays kadoBaseDays
     */
    public void setKadoBaseDays(BigDecimal kadoBaseDays) {
        kadoBaseDays__ = kadoBaseDays;
    }
    /**
     * <p>kadoBaseDaysStr を取得します。
     * @return kadoBaseDaysStr
     */
    public String getKadoBaseDaysStr() {
        return kadoBaseDaysStr__;
    }
    /**
     * <p>kadoBaseDaysStr をセットします。
     * @param kadoBaseDaysStr kadoBaseDaysStr
     */
    public void setKadoBaseDaysStr(String kadoBaseDaysStr) {
        kadoBaseDaysStr__ = kadoBaseDaysStr;
    }
    /**
     * <p>kadoBaseHours を取得します。
     * @return kadoBaseHours
     */
    public BigDecimal getKadoBaseHours() {
        return kadoBaseHours__;
    }
    /**
     * <p>kadoBaseHours をセットします。
     * @param kadoBaseHours kadoBaseHours
     */
    public void setKadoBaseHours(BigDecimal kadoBaseHours) {
        kadoBaseHours__ = kadoBaseHours;
    }
    /**
     * <p>kadoBaseHoursStr を取得します。
     * @return kadoBaseHoursStr
     */
    public String getKadoBaseHoursStr() {
        return kadoBaseHoursStr__;
    }
    /**
     * <p>kadoBaseHoursStr をセットします。
     * @param kadoBaseHoursStr kadoBaseHoursStr
     */
    public void setKadoBaseHoursStr(String kadoBaseHoursStr) {
        kadoBaseHoursStr__ = kadoBaseHoursStr;
    }
    /**
     * <p>kadoDays を取得します。
     * @return kadoDays
     */
    public BigDecimal getKadoDays() {
        return kadoDays__;
    }
    /**
     * <p>kadoDays をセットします。
     * @param kadoDays kadoDays
     */
    public void setKadoDays(BigDecimal kadoDays) {
        kadoDays__ = kadoDays;
    }
    /**
     * <p>kadoDaysStr を取得します。
     * @return kadoDaysStr
     */
    public String getKadoDaysStr() {
        return kadoDaysStr__;
    }
    /**
     * <p>kadoDaysStr をセットします。
     * @param kadoDaysStr kadoDaysStr
     */
    public void setKadoDaysStr(String kadoDaysStr) {
        kadoDaysStr__ = kadoDaysStr;
    }
    /**
     * <p>kadoHours を取得します。
     * @return kadoHours
     */
    public BigDecimal getKadoHours() {
        return kadoHours__;
    }
    /**
     * <p>kadoHours をセットします。
     * @param kadoHours kadoHours
     */
    public void setKadoHours(BigDecimal kadoHours) {
        kadoHours__ = kadoHours;
    }
    /**
     * <p>kadoHoursStr を取得します。
     * @return kadoHoursStr
     */
    public String getKadoHoursStr() {
        return kadoHoursStr__;
    }
    /**
     * <p>kadoHoursStr をセットします。
     * @param kadoHoursStr kadoHoursStr
     */
    public void setKadoHoursStr(String kadoHoursStr) {
        kadoHoursStr__ = kadoHoursStr;
    }
    /**
     * <p>keityoDays を取得します。
     * @return keityoDays
     */
    public BigDecimal getKeityoDays() {
        return keityoDays__;
    }
    /**
     * <p>keityoDays をセットします。
     * @param keityoDays keityoDays
     */
    public void setKeityoDays(BigDecimal keityoDays) {
        keityoDays__ = keityoDays;
    }
    /**
     * <p>keityoDaysStr を取得します。
     * @return keityoDaysStr
     */
    public String getKeityoDaysStr() {
        return keityoDaysStr__;
    }
    /**
     * <p>keityoDaysStr をセットします。
     * @param keityoDaysStr keityoDaysStr
     */
    public void setKeityoDaysStr(String keityoDaysStr) {
        keityoDaysStr__ = keityoDaysStr;
    }
    /**
     * <p>kekkinDays を取得します。
     * @return kekkinDays
     */
    public BigDecimal getKekkinDays() {
        return kekkinDays__;
    }
    /**
     * <p>kekkinDays をセットします。
     * @param kekkinDays kekkinDays
     */
    public void setKekkinDays(BigDecimal kekkinDays) {
        kekkinDays__ = kekkinDays;
    }
    /**
     * <p>kekkinDaysStr を取得します。
     * @return kekkinDaysStr
     */
    public String getKekkinDaysStr() {
        return kekkinDaysStr__;
    }
    /**
     * <p>kekkinDaysStr をセットします。
     * @param kekkinDaysStr kekkinDaysStr
     */
    public void setKekkinDaysStr(String kekkinDaysStr) {
        kekkinDaysStr__ = kekkinDaysStr;
    }
    /**
     * <p>kyusyutuDays を取得します。
     * @return kyusyutuDays
     */
    public BigDecimal getKyusyutuDays() {
        return kyusyutuDays__;
    }
    /**
     * <p>kyusyutuDays をセットします。
     * @param kyusyutuDays kyusyutuDays
     */
    public void setKyusyutuDays(BigDecimal kyusyutuDays) {
        kyusyutuDays__ = kyusyutuDays;
    }
    /**
     * <p>kyusyutuDaysStr を取得します。
     * @return kyusyutuDaysStr
     */
    public String getKyusyutuDaysStr() {
        return kyusyutuDaysStr__;
    }
    /**
     * <p>kyusyutuDaysStr をセットします。
     * @param kyusyutuDaysStr kyusyutuDaysStr
     */
    public void setKyusyutuDaysStr(String kyusyutuDaysStr) {
        kyusyutuDaysStr__ = kyusyutuDaysStr;
    }
    /**
     * <p>kyusyutuHours を取得します。
     * @return kyusyutuHours
     */
    public BigDecimal getKyusyutuHours() {
        return kyusyutuHours__;
    }
    /**
     * <p>kyusyutuHours をセットします。
     * @param kyusyutuHours kyusyutuHours
     */
    public void setKyusyutuHours(BigDecimal kyusyutuHours) {
        kyusyutuHours__ = kyusyutuHours;
    }
    /**
     * <p>kyusyutuHoursStr を取得します。
     * @return kyusyutuHoursStr
     */
    public String getKyusyutuHoursStr() {
        return kyusyutuHoursStr__;
    }
    /**
     * <p>kyusyutuHoursStr をセットします。
     * @param kyusyutuHoursStr kyusyutuHoursStr
     */
    public void setKyusyutuHoursStr(String kyusyutuHoursStr) {
        kyusyutuHoursStr__ = kyusyutuHoursStr;
    }
    /**
     * <p>sonotaDays を取得します。
     * @return sonotaDays
     */
    public BigDecimal getSonotaDays() {
        return sonotaDays__;
    }
    /**
     * <p>sonotaDays をセットします。
     * @param sonotaDays sonotaDays
     */
    public void setSonotaDays(BigDecimal sonotaDays) {
        sonotaDays__ = sonotaDays;
    }
    /**
     * <p>sonotaDaysStr を取得します。
     * @return sonotaDaysStr
     */
    public String getSonotaDaysStr() {
        return sonotaDaysStr__;
    }
    /**
     * <p>sonotaDaysStr をセットします。
     * @param sonotaDaysStr sonotaDaysStr
     */
    public void setSonotaDaysStr(String sonotaDaysStr) {
        sonotaDaysStr__ = sonotaDaysStr;
    }
    /**
     * <p>soutaiDays を取得します。
     * @return soutaiDays
     */
    public BigDecimal getSoutaiDays() {
        return soutaiDays__;
    }
    /**
     * <p>soutaiDays をセットします。
     * @param soutaiDays soutaiDays
     */
    public void setSoutaiDays(BigDecimal soutaiDays) {
        soutaiDays__ = soutaiDays;
    }
    /**
     * <p>soutaiTimes を取得します。
     * @return soutaiTimes
     */
    public BigDecimal getSoutaiTimes() {
        return soutaiTimes__;
    }
    /**
     * <p>soutaiTimes をセットします。
     * @param soutaiTimes soutaiTimes
     */
    public void setSoutaiTimes(BigDecimal soutaiTimes) {
        soutaiTimes__ = soutaiTimes;
    }
    /**
     * <p>soutaiDaysStr を取得します。
     * @return soutaiDaysStr
     */
    public String getSoutaiDaysStr() {
        return soutaiDaysStr__;
    }
    /**
     * <p>soutaiDaysStr をセットします。
     * @param soutaiDaysStr soutaiDaysStr
     */
    public void setSoutaiDaysStr(String soutaiDaysStr) {
        soutaiDaysStr__ = soutaiDaysStr;
    }
    /**
     * <p>soutaiTimesStr を取得します。
     * @return soutaiTimesStr
     */
    public String getSoutaiTimesStr() {
        return soutaiTimesStr__;
    }
    /**
     * <p>soutaiTimesStr をセットします。
     * @param soutaiTimesStr soutaiTimesStr
     */
    public void setSoutaiTimesStr(String soutaiTimesStr) {
        soutaiTimesStr__ = soutaiTimesStr;
    }
    /**
     * <p>startDate を取得します。
     * @return startDate
     */
    public UDate getStartDate() {
        return startDate__;
    }
    /**
     * <p>startDate をセットします。
     * @param startDate startDate
     */
    public void setStartDate(UDate startDate) {
        startDate__ = startDate;
    }
    /**
     * <p>yuukyuDays を取得します。
     * @return yuukyuDays
     */
    public BigDecimal getYuukyuDays() {
        return yuukyuDays__;
    }
    /**
     * <p>yuukyuDays をセットします。
     * @param yuukyuDays yuukyuDays
     */
    public void setYuukyuDays(BigDecimal yuukyuDays) {
        yuukyuDays__ = yuukyuDays;
    }
    /**
     * <p>yuukyuDaysStr を取得します。
     * @return yuukyuDaysStr
     */
    public String getYuukyuDaysStr() {
        return yuukyuDaysStr__;
    }
    /**
     * <p>yuukyuDaysStr をセットします。
     * @param yuukyuDaysStr yuukyuDaysStr
     */
    public void setYuukyuDaysStr(String yuukyuDaysStr) {
        yuukyuDaysStr__ = yuukyuDaysStr;
    }
    /**
     * <p>zangyoDays を取得します。
     * @return zangyoDays
     */
    public BigDecimal getZangyoDays() {
        return zangyoDays__;
    }
    /**
     * <p>zangyoDays をセットします。
     * @param zangyoDays zangyoDays
     */
    public void setZangyoDays(BigDecimal zangyoDays) {
        zangyoDays__ = zangyoDays;
    }
    /**
     * <p>zangyoDaysStr を取得します。
     * @return zangyoDaysStr
     */
    public String getZangyoDaysStr() {
        return zangyoDaysStr__;
    }
    /**
     * <p>zangyoDaysStr をセットします。
     * @param zangyoDaysStr zangyoDaysStr
     */
    public void setZangyoDaysStr(String zangyoDaysStr) {
        zangyoDaysStr__ = zangyoDaysStr;
    }
    /**
     * <p>zangyoHours を取得します。
     * @return zangyoHours
     */
    public BigDecimal getZangyoHours() {
        return zangyoHours__;
    }
    /**
     * <p>zangyoHours をセットします。
     * @param zangyoHours zangyoHours
     */
    public void setZangyoHours(BigDecimal zangyoHours) {
        zangyoHours__ = zangyoHours;
    }
    /**
     * <p>zangyoHoursStr を取得します。
     * @return zangyoHoursStr
     */
    public String getZangyoHoursStr() {
        return zangyoHoursStr__;
    }
    /**
     * <p>zangyoHoursStr をセットします。
     * @param zangyoHoursStr zangyoHoursStr
     */
    public void setZangyoHoursStr(String zangyoHoursStr) {
        zangyoHoursStr__ = zangyoHoursStr;
    }
    /**
     * <p>kadoMonth を取得します。
     * @return kadoMonth
     */
    public String getKadoMonth() {
        return kadoMonth__;
    }
    /**
     * <p>kadoMonth をセットします。
     * @param kadoMonth kadoMonth
     */
    public void setKadoMonth(String kadoMonth) {
        kadoMonth__ = kadoMonth;
    }

}
