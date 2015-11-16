package jp.groupsession.v2.wml.wml150kn;

import jp.groupsession.v2.wml.wml150.Wml150ParamModel;

/**
 * <br>[機  能] WEBメール アカウント基本設定確認画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml150knParamModel extends Wml150ParamModel {
    /** 転送先制限 許可する文字列 表示用 */
    private String[] wml150knFwLimitText__ = null;

    /**
     * <p>wml150knFwLimitText を取得します。
     * @return wml150knFwLimitText
     */
    public String[] getWml150knFwLimitText() {
        return wml150knFwLimitText__;
    }

    /**
     * <p>wml150knFwLimitText をセットします。
     * @param wml150knFwLimitText wml150knFwLimitText
     */
    public void setWml150knFwLimitText(String[] wml150knFwLimitText) {
        wml150knFwLimitText__ = wml150knFwLimitText;
    }
}