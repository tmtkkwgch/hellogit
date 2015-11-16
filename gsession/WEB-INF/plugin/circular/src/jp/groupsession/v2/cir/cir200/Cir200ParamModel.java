package jp.groupsession.v2.cir.cir200;

import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.cir100.Cir100ParamModel;

/**
 * <br>[機  能] 回覧板 管理者設定 ショートメール通知設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir200ParamModel extends Cir100ParamModel {
    /** 初期表示フラグ */
    private int cir200InitFlg__ = 0;
    /** ショートメール通知区分 */
    private int cir200SmlSendKbn__ = GSConstCircular.CAF_SML_NTF_USER;
    /** ショートメール通知 */
    private int cir200SmlSend__ = GSConstCircular.CAF_SML_NTF_KBN_YES;
    /**
     * <p>cir200InitFlg を取得します。
     * @return cir200InitFlg
     */
    public int getCir200InitFlg() {
        return cir200InitFlg__;
    }
    /**
     * <p>cir200InitFlg をセットします。
     * @param cir200InitFlg cir200InitFlg
     */
    public void setCir200InitFlg(int cir200InitFlg) {
        cir200InitFlg__ = cir200InitFlg;
    }
    /**
     * <p>cir200SmlSendKbn を取得します。
     * @return cir200SmlSendKbn
     */
    public int getCir200SmlSendKbn() {
        return cir200SmlSendKbn__;
    }
    /**
     * <p>cir200SmlSendKbn をセットします。
     * @param cir200SmlSendKbn cir200SmlSendKbn
     */
    public void setCir200SmlSendKbn(int cir200SmlSendKbn) {
        cir200SmlSendKbn__ = cir200SmlSendKbn;
    }
    /**
     * <p>cir200SmlSend を取得します。
     * @return cir200SmlSend
     */
    public int getCir200SmlSend() {
        return cir200SmlSend__;
    }
    /**
     * <p>cir200SmlSend をセットします。
     * @param cir200SmlSend cir200SmlSend
     */
    public void setCir200SmlSend(int cir200SmlSend) {
        cir200SmlSend__ = cir200SmlSend;
    }

}