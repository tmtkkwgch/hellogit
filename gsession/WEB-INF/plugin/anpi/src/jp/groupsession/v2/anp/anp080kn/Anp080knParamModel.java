package jp.groupsession.v2.anp.anp080kn;

import jp.groupsession.v2.anp.anp080.Anp080ParamModel;

/**
 * <br>[機  能] 管理者設定・基本設定確認画面のパラメータモデル
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp080knParamModel extends Anp080ParamModel {

    /** 表示用パスワード */
    private String anp080knDspSendPass__;

    /**
     * <p>anp080knDspSendPass を取得します。
     * @return anp080knDspSendPass
     */
    public String getAnp080knDspSendPass() {
        return anp080knDspSendPass__;
    }

    /**
     * <p>anp080knDspSendPass をセットします。
     * @param anp080knDspSendPass anp080knDspSendPass
     */
    public void setAnp080knDspSendPass(String anp080knDspSendPass) {
        anp080knDspSendPass__ = anp080knDspSendPass;
    }
}