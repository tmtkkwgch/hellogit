package jp.groupsession.v2.sch.sch096;

import jp.groupsession.v2.sch.sch100.Sch100Form;

/**
 * <br>[機  能] スケジュール 初期表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch096Form extends Sch100Form {

    /** デフォルト表示画面 */
    private int sch096DefDsp__ = -1;

    /**
     * <p>sch096DefDsp を取得します。
     * @return sch096DefDsp
     */
    public int getSch096DefDsp() {
        return sch096DefDsp__;
    }
    /**
     * <p>sch096DefDsp をセットします。
     * @param sch096DefDsp sch096DefDsp
     */
    public void setSch096DefDsp(int sch096DefDsp) {
        sch096DefDsp__ = sch096DefDsp;
    }
}
