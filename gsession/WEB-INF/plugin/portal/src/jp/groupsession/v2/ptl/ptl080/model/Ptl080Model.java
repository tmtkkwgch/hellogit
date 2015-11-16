package jp.groupsession.v2.ptl.ptl080.model;
import java.io.Serializable;

/**
 * <br>[機  能] ポータル プラグイン選択画面(ポップアップ)の表示情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl080Model implements Serializable {

    /** ID */
    private String mainscreenInfoId__;
    /** 画面名 */
    private String mainscreenInfoName__;
    /** プラグインアイコンURL */
    private String pluginIconUrl__;
    /** プラグイン名 */
    private String pluginName__;
    /** プラグインID */
    private String pluginId__;

    /**
     * <p>mainscreenInfoId を取得します。
     * @return mainscreenInfoId
     */
    public String getMainscreenInfoId() {
        return mainscreenInfoId__;
    }
    /**
     * <p>mainscreenInfoId をセットします。
     * @param mainscreenInfoId mainscreenInfoId
     */
    public void setMainscreenInfoId(String mainscreenInfoId) {
        mainscreenInfoId__ = mainscreenInfoId;
    }
    /**
     * <p>mainscreenInfoName を取得します。
     * @return mainscreenInfoName
     */
    public String getMainscreenInfoName() {
        return mainscreenInfoName__;
    }
    /**
     * <p>mainscreenInfoName をセットします。
     * @param mainscreenInfoName mainscreenInfoName
     */
    public void setMainscreenInfoName(String mainscreenInfoName) {
        mainscreenInfoName__ = mainscreenInfoName;
    }
    /**
     * <p>pluginIconUrl を取得します。
     * @return pluginIconUrl
     */
    public String getPluginIconUrl() {
        return pluginIconUrl__;
    }
    /**
     * <p>pluginIconUrl をセットします。
     * @param pluginIconUrl pluginIconUrl
     */
    public void setPluginIconUrl(String pluginIconUrl) {
        pluginIconUrl__ = pluginIconUrl;
    }
    /**
     * <p>pluginName を取得します。
     * @return pluginName
     */
    public String getPluginName() {
        return pluginName__;
    }
    /**
     * <p>pluginName をセットします。
     * @param pluginName pluginName
     */
    public void setPluginName(String pluginName) {
        pluginName__ = pluginName;
    }
    /**
     * <p>pluginId を取得します。
     * @return pluginId
     */
    public String getPluginId() {
        return pluginId__;
    }
    /**
     * <p>pluginId をセットします。
     * @param pluginId pluginId
     */
    public void setPluginId(String pluginId) {
        pluginId__ = pluginId;
    }

}
