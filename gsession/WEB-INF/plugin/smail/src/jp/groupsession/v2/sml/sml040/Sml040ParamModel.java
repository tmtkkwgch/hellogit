package jp.groupsession.v2.sml.sml040;

import java.util.List;

import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.sml120.Sml120ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ショートメール 個人設定 表示設定画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml040ParamModel extends Sml120ParamModel {
    /** 表示件数 */
    private int sml040ViewCnt__ = 0;
    /** 表示件数コンボ */
    private List < LabelValueBean > sml040DspCntList__ = null;

    /** 自動リロード時間コンボ */
    private List < LabelValueBean > sml040TimeLabelList__ = null;
    /** 自動リロード時間の選択値 */
    private String sml040ReloadTime__ = null;

    /** 社員画像表示設定 */
    private String sml040PhotoDsp__ = "0";

    /** 添付画像表示設定 */
    private int sml040ImageTempDsp__ = GSConstSmail.SML_IMAGE_TEMP_DSP;

    /** 設定種別 表示件数 */
    private int sml040MaxDspStype__ = GSConstSmail.DISP_CONF_USER;
    /** 設定種別 自動リロード時間 */
    private int sml040ReloadTimeStype__ = GSConstSmail.DISP_CONF_USER;
    /** 設定種別 写真表示設定 */
    private int sml040PhotoDspStype__ = GSConstSmail.DISP_CONF_USER;
    /** 設定種別 添付画像表示設定 */
    private int sml040AttachImgDspStype__ = GSConstSmail.DISP_CONF_USER;

    /**
     * <p>sml040ViewCnt を取得します。
     * @return sml040ViewCnt
     */
    public int getSml040ViewCnt() {
        return sml040ViewCnt__;
    }

    /**
     * <p>sml040ViewCnt をセットします。
     * @param sml040ViewCnt sml040ViewCnt
     */
    public void setSml040ViewCnt(int sml040ViewCnt) {
        sml040ViewCnt__ = sml040ViewCnt;
    }

    /**
     * <p>sml040DspCntList を取得します。
     * @return sml040DspCntList
     */
    public List<LabelValueBean> getSml040DspCntList() {
        return sml040DspCntList__;
    }

    /**
     * <p>sml040DspCntList をセットします。
     * @param sml040DspCntList sml040DspCntList
     */
    public void setSml040DspCntList(List<LabelValueBean> sml040DspCntList) {
        sml040DspCntList__ = sml040DspCntList;
    }

    /**
     * <p>sml040TimeLabelList を取得します。
     * @return sml040TimeLabelList
     */
    public List<LabelValueBean> getSml040TimeLabelList() {
        return sml040TimeLabelList__;
    }

    /**
     * <p>sml040TimeLabelList をセットします。
     * @param sml040TimeLabelList sml040TimeLabelList
     */
    public void setSml040TimeLabelList(List<LabelValueBean> sml040TimeLabelList) {
        sml040TimeLabelList__ = sml040TimeLabelList;
    }

    /**
     * <p>sml040ReloadTime を取得します。
     * @return sml040ReloadTime
     */
    public String getSml040ReloadTime() {
        return sml040ReloadTime__;
    }

    /**
     * <p>sml040ReloadTime をセットします。
     * @param sml040ReloadTime sml040ReloadTime
     */
    public void setSml040ReloadTime(String sml040ReloadTime) {
        sml040ReloadTime__ = sml040ReloadTime;
    }

    /**
     * <p>sml040PhotoDsp を取得します。
     * @return sml040PhotoDsp
     */
    public String getSml040PhotoDsp() {
        return sml040PhotoDsp__;
    }

    /**
     * <p>sml040PhotoDsp をセットします。
     * @param sml040PhotoDsp sml040PhotoDsp
     */
    public void setSml040PhotoDsp(String sml040PhotoDsp) {
        sml040PhotoDsp__ = sml040PhotoDsp;
    }

    /**
     * <p>sml040ImageTempDsp を取得します。
     * @return sml040ImageTempDsp
     */
    public int getSml040ImageTempDsp() {
        return sml040ImageTempDsp__;
    }

    /**
     * <p>sml040ImageTempDsp をセットします。
     * @param sml040ImageTempDsp sml040ImageTempDsp
     */
    public void setSml040ImageTempDsp(int sml040ImageTempDsp) {
        sml040ImageTempDsp__ = sml040ImageTempDsp;
    }

    /**
     * <p>sml040MaxDspStype を取得します。
     * @return sml040MaxDspStype
     */
    public int getSml040MaxDspStype() {
        return sml040MaxDspStype__;
    }

    /**
     * <p>sml040MaxDspStype をセットします。
     * @param sml040MaxDspStype sml040MaxDspStype
     */
    public void setSml040MaxDspStype(int sml040MaxDspStype) {
        sml040MaxDspStype__ = sml040MaxDspStype;
    }

    /**
     * <p>sml040ReloadTimeStype を取得します。
     * @return sml040ReloadTimeStype
     */
    public int getSml040ReloadTimeStype() {
        return sml040ReloadTimeStype__;
    }

    /**
     * <p>sml040ReloadTimeStype をセットします。
     * @param sml040ReloadTimeStype sml040ReloadTimeStype
     */
    public void setSml040ReloadTimeStype(int sml040ReloadTimeStype) {
        sml040ReloadTimeStype__ = sml040ReloadTimeStype;
    }

    /**
     * <p>sml040PhotoDspStype を取得します。
     * @return sml040PhotoDspStype
     */
    public int getSml040PhotoDspStype() {
        return sml040PhotoDspStype__;
    }

    /**
     * <p>sml040PhotoDspStype をセットします。
     * @param sml040PhotoDspStype sml040PhotoDspStype
     */
    public void setSml040PhotoDspStype(int sml040PhotoDspStype) {
        sml040PhotoDspStype__ = sml040PhotoDspStype;
    }

    /**
     * <p>sml040AttachImgDspStype を取得します。
     * @return sml040AttachImgDspStype
     */
    public int getSml040AttachImgDspStype() {
        return sml040AttachImgDspStype__;
    }

    /**
     * <p>sml040AttachImgDspStype をセットします。
     * @param sml040AttachImgDspStype sml040AttachImgDspStype
     */
    public void setSml040AttachImgDspStype(int sml040AttachImgDspStype) {
        sml040AttachImgDspStype__ = sml040AttachImgDspStype;
    }
}