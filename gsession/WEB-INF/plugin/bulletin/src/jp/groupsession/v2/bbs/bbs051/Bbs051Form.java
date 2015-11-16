package jp.groupsession.v2.bbs.bbs051;

import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs010.Bbs010Form;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 メイン表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs051Form extends Bbs010Form {

    /** メイン画面スレッド表示件数の選択値 */
    public static final String[] THREMAINCNTVALUE
        = new String[] {"5", "10", "20", "30", "40", "50"};

    /** メイン画面スレッド表示件数 */
    private int bbs051threMainCnt__ = 0;
    /** メイ画面確認済み投稿表示有無 */
    private int bbs051mainChkedDsp__ = GSConstBulletin.BBS_CHECKED_NOT_DSP;

    /** メイン画面スレッド表示件数 */
    private List < LabelValueBean > bbs051threMainCntLabel__ = null;

    /**
     * コンストラクタ
     */
    public Bbs051Form() {
        bbs051threMainCntLabel__ = new ArrayList < LabelValueBean >();
        for (String label : THREMAINCNTVALUE) {
            bbs051threMainCntLabel__.add(new LabelValueBean(label, label));
        }

    }

    /**
     * @return bbs051mainChkedDsp
     */
    public int getBbs051mainChkedDsp() {
        return bbs051mainChkedDsp__;
    }

    /**
     * @param bbs051mainChkedDsp セットする bbs051mainChkedDsp
     */
    public void setBbs051mainChkedDsp(int bbs051mainChkedDsp) {
        bbs051mainChkedDsp__ = bbs051mainChkedDsp;
    }

    /**
     * <p>bbs051threMainCnt を取得します。
     * @return bbs051threMainCnt
     */
    public int getBbs051threMainCnt() {
        return bbs051threMainCnt__;
    }

    /**
     * <p>bbs051threMainCnt をセットします。
     * @param bbs051threMainCnt bbs051threMainCnt
     */
    public void setBbs051threMainCnt(int bbs051threMainCnt) {
        bbs051threMainCnt__ = bbs051threMainCnt;
    }

    /**
     * <p>bbs051threMainCntLabel を取得します。
     * @return bbs051threMainCntLabel
     */
    public List<LabelValueBean> getBbs051threMainCntLabel() {
        return bbs051threMainCntLabel__;
    }

    /**
     * <p>bbs051threMainCntLabel をセットします。
     * @param bbs051threMainCntLabel bbs051threMainCntLabel
     */
    public void setBbs051threMainCntLabel(
            List<LabelValueBean> bbs051threMainCntLabel) {
        bbs051threMainCntLabel__ = bbs051threMainCntLabel;
    }

}
