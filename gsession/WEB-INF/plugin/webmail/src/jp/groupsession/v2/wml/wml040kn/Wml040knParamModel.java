package jp.groupsession.v2.wml.wml040kn;

import jp.groupsession.v2.wml.wml040.Wml040ParamModel;

/**
 * <br>[機  能] WEBメール アカウント登録確認画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml040knParamModel extends Wml040ParamModel {
    /** 備考 表示用 */
    private String wml040knBiko__ = null;
    /** 署名 表示用 */
    private String wml040knSign__ = null;
    /** テーマ 表示用 */
    private String wml040knTheme__ = null;
    /** 引用符 表示用 */
    private String wml040knQuotes__ = null;

    /**
     * <p>wml040knBiko を取得します。
     * @return wml040knBiko
     */
    public String getWml040knBiko() {
        return wml040knBiko__;
    }
    /**
     * <p>wml040knBiko をセットします。
     * @param wml040knBiko wml040knBiko
     */
    public void setWml040knBiko(String wml040knBiko) {
        wml040knBiko__ = wml040knBiko;
    }
    /**
     * <p>wml040knSign を取得します。
     * @return wml040knSign
     */
    public String getWml040knSign() {
        return wml040knSign__;
    }
    /**
     * <p>wml040knSign をセットします。
     * @param wml040knSign wml040knSign
     */
    public void setWml040knSign(String wml040knSign) {
        wml040knSign__ = wml040knSign;
    }
    /**
     * <p>wml040knTheme を取得します。
     * @return wml040knTheme
     */
    public String getWml040knTheme() {
        return wml040knTheme__;
    }
    /**
     * <p>wml040knTheme をセットします。
     * @param wml040knTheme wml040knTheme
     */
    public void setWml040knTheme(String wml040knTheme) {
        wml040knTheme__ = wml040knTheme;
    }
    /**
     * <p>wml040knQuotes を取得します。
     * @return wml040knQuotes
     */
    public String getWml040knQuotes() {
        return wml040knQuotes__;
    }
    /**
     * <p>wml040knQuotes をセットします。
     * @param wml040knQuotes wml040knQuotes
     */
    public void setWml040knQuotes(String wml040knQuotes) {
        wml040knQuotes__ = wml040knQuotes;
    }
}