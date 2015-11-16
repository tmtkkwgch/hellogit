package jp.groupsession.v2.enq.enq820;

import jp.groupsession.v2.enq.enq800.Enq800ParamModel;

/**
 * <br>[機  能] アンケート 個人設定 メイン表示設定画面のパラメータモデル
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq820ParamModel extends Enq800ParamModel {

    /** メイン表示 */
    private int enq820MainDsp__ = 0;

    /**
     * <p>メイン表示 を取得します。
     * @return メイン表示
     */
    public int getEnq820MainDsp() {
        return enq820MainDsp__;
    }
    /**
     * <p>メイン表示 をセットします。
     * @param enq820MainDsp メイン表示
     */
    public void setEnq820MainDsp(int enq820MainDsp) {
        enq820MainDsp__ = enq820MainDsp;
    }
}
