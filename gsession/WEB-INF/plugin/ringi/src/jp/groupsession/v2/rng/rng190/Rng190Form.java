package jp.groupsession.v2.rng.rng190;

import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.rng040.Rng040Form;

/**
 * <br>[機  能] 稟議 管理者設定 ショートメール通知設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng190Form extends Rng040Form {

    /** ショートメール通知設定 0:各ユーザが設定 1:管理者が設定*/
    private int rng190SmlNtf__ = RngConst.RAR_SML_NTF_USER;
    /** ショートメール通知区分 0:通知しない 0:通知する 1:通知しない */
    private int rng190SmlNtfKbn__ = RngConst.RAR_SML_NTF_KBN_YES;
    /**
     * <p>rng190SmlNtf を取得します。
     * @return rng190SmlNtf
     */
    public int getRng190SmlNtf() {
        return rng190SmlNtf__;
    }
    /**
     * <p>rng190SmlNtf をセットします。
     * @param rng190SmlNtf rng190SmlNtf
     */
    public void setRng190SmlNtf(int rng190SmlNtf) {
        rng190SmlNtf__ = rng190SmlNtf;
    }
    /**
     * <p>rng190SmlNtfKbn を取得します。
     * @return rng190SmlNtfKbn
     */
    public int getRng190SmlNtfKbn() {
        return rng190SmlNtfKbn__;
    }
    /**
     * <p>rng190SmlNtfKbn をセットします。
     * @param rng190SmlNtfKbn rng190SmlNtfKbn
     */
    public void setRng190SmlNtfKbn(int rng190SmlNtfKbn) {
        rng190SmlNtfKbn__ = rng190SmlNtfKbn;
    }


}
