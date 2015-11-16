package jp.groupsession.v2.ntp.ntp080;

import jp.groupsession.v2.ntp.ntp100.Ntp100Form;

/**
 * <br>[機  能] 日報 管理者設定メニュー画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp080Form extends Ntp100Form {
    /** ショートメール使用可否*/
    private int canUseSml__;

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
