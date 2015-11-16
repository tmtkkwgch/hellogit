package jp.groupsession.v2.sml.sml190;

import java.util.ArrayList;

import jp.groupsession.v2.sml.sml100.Sml100ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ショートメール 個人設定 メイン表示設定画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml190ParamModel extends Sml100ParamModel {
    /** メイン表示件数 */
    private String sml190mainDsp__;
    /** 既読ショートメールメイン表示区分 */
    private String sml190kidokuKbn__;
    /** メイン表示ソート */
    private String sml190mainSort__;

    /** メイン表示件数コンボ */
    private ArrayList<LabelValueBean> sml190mainDspList__;

    /**
     * @return sml190mainDsp
     */
    public String getSml190mainDsp() {
        return sml190mainDsp__;
    }
    /**
     * @param sml190mainDsp セットする sml190mainDsp
     */
    public void setSml190mainDsp(String sml190mainDsp) {
        sml190mainDsp__ = sml190mainDsp;
    }
    /**
     * @return sml190kidokuKbn
     */
    public String getSml190kidokuKbn() {
        return sml190kidokuKbn__;
    }
    /**
     * @param sml190kidokuKbn セットする sml190kidokuKbn
     */
    public void setSml190kidokuKbn(String sml190kidokuKbn) {
        sml190kidokuKbn__ = sml190kidokuKbn;
    }
    /**
     * @return sml190mainSort
     */
    public String getSml190mainSort() {
        return sml190mainSort__;
    }
    /**
     * @param sml190mainSort セットする sml190mainSort
     */
    public void setSml190mainSort(String sml190mainSort) {
        sml190mainSort__ = sml190mainSort;
    }
    /**
     * @return sml190mainDspList
     */
    public ArrayList<LabelValueBean> getSml190mainDspList() {
        return sml190mainDspList__;
    }
    /**
     * @param sml190mainDspList セットする sml190mainDspList
     */
    public void setSml190mainDspList(ArrayList<LabelValueBean> sml190mainDspList) {
        sml190mainDspList__ = sml190mainDspList;
    }
}