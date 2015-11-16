package jp.groupsession.v2.tcd.tcd070;

import java.util.List;

import jp.groupsession.v2.tcd.tcd060.Tcd060ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] タイムカード 管理者設定 時間帯設定登録画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd070ParamModel extends Tcd060ParamModel {
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
}