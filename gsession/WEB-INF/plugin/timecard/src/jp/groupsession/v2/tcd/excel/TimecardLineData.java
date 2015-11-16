package jp.groupsession.v2.tcd.excel;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.AbstractModel;

/**
 * <br>[機  能] 勤務表の1行ごとの情報を格納するModelクラスです。
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class TimecardLineData extends AbstractModel {

    /** 日付 */
    private UDate date__ = null;
    /** 休日フラグ */
    private int holiday__ = 0;
    /** 打刻開始時間 */
    private String strikeStartTime__ = "";
    /** 打刻終了時間稼働時間 */
    private String strikeEndTime__ = "";
    /** 開始時間 */
    private String startTime__ = "";
    /** 終了時間稼働時間 */
    private String endTime__ = "";
    /** 稼働時間 */
    private String utilTime__ = "";
    /** 残業時間 */
    private String overTime__ = "";
    /** 深夜残業時間 */
    private String nightOverTime__ = "";
    /** 備考 */
    private String bikou__ = "";

    /** 遅刻 */
    private String tikoku__ = "";
    /** 早退 */
    private String soutai__ = "";
    /** 代休 */
    private String daikyu__ = "";
    /** 有給 */
    private String yuukyu__ = "";
    /** 欠勤 */
    private String kekkin__ = "";

    /**
     * <p>daikyu を取得します。
     * @return daikyu
     */
    public String getDaikyu() {
        return daikyu__;
    }
    /**
     * <p>daikyu をセットします。
     * @param daikyu daikyu
     */
    public void setDaikyu(String daikyu) {
        daikyu__ = daikyu;
    }
    /**
     * <p>kekkin を取得します。
     * @return kekkin
     */
    public String getKekkin() {
        return kekkin__;
    }
    /**
     * <p>kekkin をセットします。
     * @param kekkin kekkin
     */
    public void setKekkin(String kekkin) {
        kekkin__ = kekkin;
    }
    /**
     * <p>soutai を取得します。
     * @return soutai
     */
    public String getSoutai() {
        return soutai__;
    }
    /**
     * <p>soutai をセットします。
     * @param soutai soutai
     */
    public void setSoutai(String soutai) {
        soutai__ = soutai;
    }
    /**
     * <p>yuukyu を取得します。
     * @return yuukyu
     */
    public String getYuukyu() {
        return yuukyu__;
    }
    /**
     * <p>yuukyu をセットします。
     * @param yuukyu yuukyu
     */
    public void setYuukyu(String yuukyu) {
        yuukyu__ = yuukyu;
    }
    /**
     * @return bikou を戻します。
     */
    public String getBikou() {
        return bikou__;
    }
    /**
     * @param bikou bikou を設定。
     */
    public void setBikou(String bikou) {
        this.bikou__ = bikou;
    }
    /**
     * @return holiday を戻します。
     */
    public int getHoliday() {
        return holiday__;
    }
    /**
     * @param holiday holiday を設定。
     */
    public void setHoliday(int holiday) {
        this.holiday__ = holiday;
    }
    /**
     * @return date を戻します。
     */
    public UDate getDate() {
        return date__;
    }
    /**
     * @param date date を設定。
     */
    public void setDate(UDate date) {
        this.date__ = date;
    }
    /**
     * @return endTime を戻します。
     */
    public String getEndTime() {
        return endTime__;
    }
    /**
     * @param endTime endTime を設定。
     */
    public void setEndTime(String endTime) {
        this.endTime__ = endTime;
    }
    /**
     * @return nightOverTime を戻します。
     */
    public String getNightOverTime() {
        return nightOverTime__;
    }
    /**
     * @param nightOverTime nightOverTime を設定。
     */
    public void setNightOverTime(String nightOverTime) {
        this.nightOverTime__ = nightOverTime;
    }
    /**
     * @return overTime を戻します。
     */
    public String getOverTime() {
        return overTime__;
    }
    /**
     * @param overTime overTime を設定。
     */
    public void setOverTime(String overTime) {
        this.overTime__ = overTime;
    }
    /**
     * @return startTime を戻します。
     */
    public String getStartTime() {
        return startTime__;
    }
    /**
     * @param startTime startTime を設定。
     */
    public void setStartTime(String startTime) {
        this.startTime__ = startTime;
    }
    /**
     * @return utilTime を戻します。
     */
    public String getUtilTime() {
        return utilTime__;
    }
    /**
     * @param utilTime utilTime を設定。
     */
    public void setUtilTime(String utilTime) {
        this.utilTime__ = utilTime;
    }
    /**
     * <p>tikoku を取得します。
     * @return tikoku
     */
    public String getTikoku() {
        return tikoku__;
    }
    /**
     * <p>tikoku をセットします。
     * @param tikoku tikoku
     */
    public void setTikoku(String tikoku) {
        tikoku__ = tikoku;
    }
    /**
     * <p>strikeEndTime を取得します。
     * @return strikeEndTime
     */
    public String getStrikeEndTime() {
        return strikeEndTime__;
    }
    /**
     * <p>strikeEndTime をセットします。
     * @param strikeEndTime strikeEndTime
     */
    public void setStrikeEndTime(String strikeEndTime) {
        strikeEndTime__ = strikeEndTime;
    }
    /**
     * <p>strikeStartTime を取得します。
     * @return strikeStartTime
     */
    public String getStrikeStartTime() {
        return strikeStartTime__;
    }
    /**
     * <p>strikeStartTime をセットします。
     * @param strikeStartTime strikeStartTime
     */
    public void setStrikeStartTime(String strikeStartTime) {
        strikeStartTime__ = strikeStartTime;
    }
}