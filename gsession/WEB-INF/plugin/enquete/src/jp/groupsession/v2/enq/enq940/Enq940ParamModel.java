package jp.groupsession.v2.enq.enq940;

import jp.groupsession.v2.enq.enq900.Enq900ParamModel;

/**
 * <br>[機  能] 管理者設定 メイン表示設定画面のパラメータモデル
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq940ParamModel extends Enq900ParamModel {

    /** メイン表示区分 */
    private int enq940MainDspKbn__ = 0;
    /** メイン表示 */
    private int enq940MainDsp__ = 0;

    /**
     * <p>メイン表示区分 を取得します。
     * @return メイン表示区分
     */
    public int getEnq940MainDspKbn() {
        return enq940MainDspKbn__;
    }
    /**
     * <p>メイン表示区分 をセットします。
     * @param enq940MainDspKbn メイン表示区分
     */
    public void setEnq940MainDspKbn(int enq940MainDspKbn) {
        enq940MainDspKbn__ = enq940MainDspKbn;
    }
    /**
     * <p>メイン表示 を取得します。
     * @return メイン表示
     */
    public int getEnq940MainDsp() {
        return enq940MainDsp__;
    }
    /**
     * <p>メイン表示 をセットします。
     * @param enq940MainDsp メイン表示
     */
    public void setEnq940MainDsp(int enq940MainDsp) {
        enq940MainDsp__ = enq940MainDsp;
    }
}
