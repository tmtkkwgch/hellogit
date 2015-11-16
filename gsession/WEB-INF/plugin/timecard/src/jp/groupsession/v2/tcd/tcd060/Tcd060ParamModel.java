package jp.groupsession.v2.tcd.tcd060;

import java.util.ArrayList;

import jp.groupsession.v2.tcd.model.TcdTimezoneChartModel;
import jp.groupsession.v2.tcd.model.TcdTimezoneMeiModel;
import jp.groupsession.v2.tcd.tcd030.Tcd030ParamModel;

/**
 * <br>[機  能] タイムカード 管理者設定 時間帯設定画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd060ParamModel extends Tcd030ParamModel {
    /** チャート表示有無*/
    private String dspChartKbn__;
    /** 追加する時間帯区分*/
    private String addTimezoneKbn__;
    /** 編集する時間帯SID*/
    private String editTimezoneSid__;

    /** 時間帯タイムチャート(TagLib用) */
    private TcdTimezoneChartModel tcd060Timechart__;
    /** 時間帯明細部 */
    private ArrayList<TcdTimezoneMeiModel> tcd060TimezoneMeiList__;

    /**
     * <p>dspChartKbn を取得します。
     * @return dspChartKbn
     */
    public String getDspChartKbn() {
        return dspChartKbn__;
    }
    /**
     * <p>dspChartKbn をセットします。
     * @param dspChartKbn dspChartKbn
     */
    public void setDspChartKbn(String dspChartKbn) {
        dspChartKbn__ = dspChartKbn;
    }
    /**
     * <p>addTimezoneKbn を取得します。
     * @return addTimezoneKbn
     */
    public String getAddTimezoneKbn() {
        return addTimezoneKbn__;
    }
    /**
     * <p>addTimezoneKbn をセットします。
     * @param addTimezoneKbn addTimezoneKbn
     */
    public void setAddTimezoneKbn(String addTimezoneKbn) {
        addTimezoneKbn__ = addTimezoneKbn;
    }
    /**
     * <p>editTimezoneSid を取得します。
     * @return editTimezoneSid
     */
    public String getEditTimezoneSid() {
        return editTimezoneSid__;
    }
    /**
     * <p>editTimezoneSid をセットします。
     * @param editTimezoneSid editTimezoneSid
     */
    public void setEditTimezoneSid(String editTimezoneSid) {
        editTimezoneSid__ = editTimezoneSid;
    }
    /**
     * <p>tcd060Timechart を取得します。
     * @return tcd060Timechart
     */
    public TcdTimezoneChartModel getTcd060Timechart() {
        return tcd060Timechart__;
    }
    /**
     * <p>tcd060Timechart をセットします。
     * @param tcd060Timechart tcd060Timechart
     */
    public void setTcd060Timechart(TcdTimezoneChartModel tcd060Timechart) {
        tcd060Timechart__ = tcd060Timechart;
    }
    /**
     * <p>tcd060TimezoneMeiList を取得します。
     * @return tcd060TimezoneMeiList
     */
    public ArrayList<TcdTimezoneMeiModel> getTcd060TimezoneMeiList() {
        return tcd060TimezoneMeiList__;
    }
    /**
     * <p>tcd060TimezoneMeiList をセットします。
     * @param tcd060TimezoneMeiList tcd060TimezoneMeiList
     */
    public void setTcd060TimezoneMeiList(
            ArrayList<TcdTimezoneMeiModel> tcd060TimezoneMeiList) {
        tcd060TimezoneMeiList__ = tcd060TimezoneMeiList;
    }
}