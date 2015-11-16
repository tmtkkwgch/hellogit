package jp.groupsession.v2.cmn;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * <br>[機  能] GsResources.propertiesからGroupSessionの各種設定を取得する
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class GsResourceBundle {

    /** プロパティファイル名 */
    private static final String BUNDLE_NAME = "Gs2Resources";

    /** インスタンス */
    private static final ResourceBundle RESOURCE_BUNDLE =
        ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * <p>インスタンスを生成させないためのプライベートコンストラクタ
     */
    private GsResourceBundle() {
    }

    /**
     * <p>プロパティファイルから引数をキーに値を取得する
     * <>
     * @param key メッセージを取得するキー
     * @return 引数に対応した値
     */
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }

}
