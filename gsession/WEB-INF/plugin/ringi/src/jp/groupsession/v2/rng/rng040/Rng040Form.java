package jp.groupsession.v2.rng.rng040;

import jp.groupsession.v2.rng.rng010.Rng010Form;

/**
 * <br>[機  能] 稟議 管理者設定メニュー画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng040Form extends Rng010Form {
    /** ショートメール使用可否*/
    int canUseSml__;

    /**
     * <p>canUseSml を取得します。
     * @return canUseSml
     */
    public int getCanUseSml() {
        return canUseSml__;
    }

    /**
     * <p>canUseSml をセットします。
     * @param canUseSml canUseSml
     */
    public void setCanUseSml(int canUseSml) {
        canUseSml__ = canUseSml;
    }
}
