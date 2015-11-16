package jp.groupsession.v2.zsk.zsk130;

import java.util.List;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.zsk.GSConstZaiseki;
import jp.groupsession.v2.zsk.zsk070.Zsk070ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 在席管理 個人設定 座席表メンバー表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk130ParamModel extends Zsk070ParamModel {

    /** ソートキー1 */
    private int zsk130SortKey1__ = GSConstZaiseki.SORT_KEY_NAME;
    /** ソートキー1オーダー */
    private int zsk130SortOrder1__ = GSConst.ORDER_KEY_ASC;
    /** ソートキー2 */
    private int zsk130SortKey2__ = GSConstZaiseki.SORT_KEY_NAME;
    /** ソートキー2オーダー */
    private int zsk130SortOrder2__ = GSConst.ORDER_KEY_ASC;
    /** ソートキー ラベル */
    private List<LabelValueBean> zsk130SortKeyLabel__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
//    public Zsk130Form() {
//        //ソートキーラベル
//        ArrayList<LabelValueBean> sortLabel = new ArrayList<LabelValueBean>();
//        for (int i = 0; i < GSConstZaiseki.SORT_KEY_ZSK.length; i++) {
//            String label = GSConstZaiseki.SORT_KEY_ZSK_TEXT[i];
//            String value = Integer.toString(GSConstZaiseki.SORT_KEY_ZSK[i]);
//            sortLabel.add(new LabelValueBean(label, value));
//        }
//        zsk130SortKeyLabel__ = sortLabel;
//    }

    /**
     * <p>zsk130SortKey1 を取得します。
     * @return zsk130SortKey1
     */
    public int getZsk130SortKey1() {
        return zsk130SortKey1__;
    }
    /**
     * <p>zsk130SortKey1 をセットします。
     * @param zsk130SortKey1 zsk130SortKey1
     */
    public void setZsk130SortKey1(int zsk130SortKey1) {
        zsk130SortKey1__ = zsk130SortKey1;
    }
    /**
     * <p>zsk130SortOrder1 を取得します。
     * @return zsk130SortOrder1
     */
    public int getZsk130SortOrder1() {
        return zsk130SortOrder1__;
    }
    /**
     * <p>zsk130SortOrder1 をセットします。
     * @param zsk130SortOrder1 zsk130SortOrder1
     */
    public void setZsk130SortOrder1(int zsk130SortOrder1) {
        zsk130SortOrder1__ = zsk130SortOrder1;
    }
    /**
     * <p>zsk130SortKey2 を取得します。
     * @return zsk130SortKey2
     */
    public int getZsk130SortKey2() {
        return zsk130SortKey2__;
    }
    /**
     * <p>zsk130SortKey2 をセットします。
     * @param zsk130SortKey2 zsk130SortKey2
     */
    public void setZsk130SortKey2(int zsk130SortKey2) {
        zsk130SortKey2__ = zsk130SortKey2;
    }
    /**
     * <p>zsk130SortOrder2 を取得します。
     * @return zsk130SortOrder2
     */
    public int getZsk130SortOrder2() {
        return zsk130SortOrder2__;
    }
    /**
     * <p>zsk130SortOrder2 をセットします。
     * @param zsk130SortOrder2 zsk130SortOrder2
     */
    public void setZsk130SortOrder2(int zsk130SortOrder2) {
        zsk130SortOrder2__ = zsk130SortOrder2;
    }
    /**
     * <p>zsk130SortKeyLabel を取得します。
     * @return zsk130SortKeyLabel
     */
    public List<LabelValueBean> getZsk130SortKeyLabel() {
        return zsk130SortKeyLabel__;
    }
    /**
     * <p>zsk130SortKeyLabel をセットします。
     * @param zsk130SortKeyLabel zsk130SortKeyLabel
     */
    public void setZsk130SortKeyLabel(List<LabelValueBean> zsk130SortKeyLabel) {
        zsk130SortKeyLabel__ = zsk130SortKeyLabel;
    }
}