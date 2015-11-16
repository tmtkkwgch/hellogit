package jp.groupsession.v2.bmk.bmk060kn;

import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.bmk.bmk060.Bmk060ParamModel;

/**
 * <br>[機  能] ラベル登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk060knParamModel extends Bmk060ParamModel {
    /** 統合ラベル一覧 */
    private List<String> bmk090knLabelList__ = null;
    //表示項目
    /** 統合ラベル名 */
    private ArrayList<String> bmk060knLabelNameList__ = null;

    /**
     * <p>bmk090knLabelList を取得します。
     * @return bmk090knLabelList
     */
    public List<String> getBmk090knLabelList() {
        return bmk090knLabelList__;
    }
    /**
     * <p>bmk090knLabelList をセットします。
     * @param bmk090knLabelList bmk090knLabelList
     */
    public void setBmk090knLabelList(List<String> bmk090knLabelList) {
        bmk090knLabelList__ = bmk090knLabelList;
    }
    /**
     * <p>bmk060knLabelNameList を取得します。
     * @return bmk060knLabelNameList
     */
    public ArrayList<String> getBmk060knLabelNameList() {
        return bmk060knLabelNameList__;
    }
    /**
     * <p>bmk060knLabelNameList をセットします。
     * @param bmk060knLabelNameList bmk060knLabelNameList
     */
    public void setBmk060knLabelNameList(ArrayList<String> bmk060knLabelNameList) {
        bmk060knLabelNameList__ = bmk060knLabelNameList;
    }
}
