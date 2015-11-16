package jp.groupsession.v2.rng.rng180;

import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.rng040.Rng040Form;

/**
 * <br>[機  能] 稟議 管理者設定 基本設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng180Form extends Rng040Form {
    /** 初期表示区分 */
    private int rng180initFlg__ = RngConst.DSP_FIRST;
    /** 稟議の削除 管理者/ユーザ */
    private int rng180delKbn__ = 0;
    /**
     * <p>rng180delKbn を取得します。
     * @return rng180delKbn
     */
    public int getRng180delKbn() {
        return rng180delKbn__;
    }
    /**
     * <p>rng180delKbn をセットします。
     * @param rng180delKbn rng180delKbn
     */
    public void setRng180delKbn(int rng180delKbn) {
        rng180delKbn__ = rng180delKbn;
    }
    /**
     * <p>rng180initFlg を取得します。
     * @return rng180initFlg
     */
    public int getRng180initFlg() {
        return rng180initFlg__;
    }
    /**
     * <p>rng180initFlg をセットします。
     * @param rng180initFlg rng180initFlg
     */
    public void setRng180initFlg(int rng180initFlg) {
        rng180initFlg__ = rng180initFlg;
    }
}
