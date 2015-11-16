package jp.groupsession.v2.man.man300kn;

import java.util.ArrayList;

import jp.groupsession.v2.man.man300.Man300ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン インフォメーション 管理者設定確認画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man300knParamModel extends Man300ParamModel {
    /** インフォメーション管理者リスト */
    private ArrayList <LabelValueBean> man300knKoukaiList__ = null;

    /**
     * @return the man300knKoukaiList
     */
    public ArrayList<LabelValueBean> getMan300knKoukaiList() {
        return man300knKoukaiList__;
    }

    /**
     * @param man300knKoukaiList the man300knKoukaiList to set
     */
    public void setMan300knKoukaiList(ArrayList<LabelValueBean> man300knKoukaiList) {
        man300knKoukaiList__ = man300knKoukaiList;
    }
}