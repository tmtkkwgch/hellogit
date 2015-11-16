package jp.groupsession.v2.bmk.bmk130;

import java.util.List;

import jp.groupsession.v2.bmk.bmk120.Bmk120ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 個人設定 表示件数設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk130ParamModel extends Bmk120ParamModel {

    /** 表示件数コンボ */
    private List<LabelValueBean> bmk130DspCountList__ = null;
    /** 表示件数の選択値 */
    private String bmk130DspCount__ = null;

    /**
     * <p>bmk130DspCount を取得します。
     * @return bmk130DspCount
     */
    public String getBmk130DspCount() {
        return bmk130DspCount__;
    }
    /**
     * <p>bmk130DspCount をセットします。
     * @param bmk130DspCount bmk130DspCount
     */
    public void setBmk130DspCount(String bmk130DspCount) {
        bmk130DspCount__ = bmk130DspCount;
    }
    /**
     * <p>bmk130DspCountList を取得します。
     * @return bmk130DspCountList
     */
    public List<LabelValueBean> getBmk130DspCountList() {
        return bmk130DspCountList__;
    }
    /**
     * <p>bmk130DspCountList をセットします。
     * @param bmk130DspCountList bmk130DspCountList
     */
    public void setBmk130DspCountList(List<LabelValueBean> bmk130DspCountList) {
        bmk130DspCountList__ = bmk130DspCountList;
    }
}