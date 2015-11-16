package jp.groupsession.v2.wml.wml190kn;

import jp.groupsession.v2.wml.wml190.Wml190Form;

/**
 * <br>[機  能] WEBメール 個人設定 アカウント編集確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml190knForm extends Wml190Form {
    /** テーマ 表示用 */
    private String wml190knTheme__ = null;
    /** 引用符 表示用 */
    private String wml190knQuotes__ = null;
    /**
     * <p>wml190knTheme を取得します。
     * @return wml190knTheme
     */
    public String getWml190knTheme() {
        return wml190knTheme__;
    }
    /**
     * <p>wml190knTheme をセットします。
     * @param wml190knTheme wml190knTheme
     */
    public void setWml190knTheme(String wml190knTheme) {
        wml190knTheme__ = wml190knTheme;
    }
    /**
     * <p>wml190knQuotes を取得します。
     * @return wml190knQuotes
     */
    public String getWml190knQuotes() {
        return wml190knQuotes__;
    }
    /**
     * <p>wml190knQuotes をセットします。
     * @param wml190knQuotes wml190knQuotes
     */
    public void setWml190knQuotes(String wml190knQuotes) {
        wml190knQuotes__ = wml190knQuotes;
    }
}
