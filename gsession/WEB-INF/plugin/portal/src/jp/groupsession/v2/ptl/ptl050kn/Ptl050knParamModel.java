package jp.groupsession.v2.ptl.ptl050kn;

import java.util.ArrayList;

import jp.groupsession.v2.ptl.ptl050.Ptl050ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ポータル ポータル登録確認画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl050knParamModel extends Ptl050ParamModel {
    /** 説明(表示用) */
    private String ptl050knviewDescription__ = null;
    /** 閲覧メンバーの名前一覧 */
    private ArrayList<LabelValueBean> ptl050knMemNameList__ = null;

    /**
     * <p>ptl050knMemNameList を取得します。
     * @return ptl050knMemNameList
     */
    public ArrayList<LabelValueBean> getPtl050knMemNameList() {
        return ptl050knMemNameList__;
    }
    /**
     * <p>ptl050knMemNameList をセットします。
     * @param ptl050knMemNameList ptl050knMemNameList
     */
    public void setPtl050knMemNameList(ArrayList<LabelValueBean> ptl050knMemNameList) {
        ptl050knMemNameList__ = ptl050knMemNameList;
    }
    /**
     * <p>ptl050knviewDescription を取得します。
     * @return ptl050knviewDescription
     */
    public String getPtl050knviewDescription() {
        return ptl050knviewDescription__;
    }
    /**
     * <p>ptl050knviewDescription をセットします。
     * @param ptl050knviewDescription ptl050knviewDescription
     */
    public void setPtl050knviewDescription(String ptl050knviewDescription) {
        ptl050knviewDescription__ = ptl050knviewDescription;
    }
}