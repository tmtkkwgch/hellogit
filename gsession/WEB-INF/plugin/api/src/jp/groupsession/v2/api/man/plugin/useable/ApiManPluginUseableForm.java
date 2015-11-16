package jp.groupsession.v2.api.man.plugin.useable;

import jp.groupsession.v2.api.AbstractApiForm;

/**
 *
 * <br>[機  能] 複数指定したプラグインIDの中から利用可能なプラグイン情報を返すWEBAPIフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiManPluginUseableForm extends AbstractApiForm {
    /** プラグインID*/
    private String[] pluginId__;

    /**
     * <p>pluginId を取得します。
     * @return pluginId
     */
    public String[] getPluginId() {
        return pluginId__;
    }

    /**
     * <p>pluginId をセットします。
     * @param pluginId pluginId
     */
    public void setPluginId(String[] pluginId) {
        pluginId__ = pluginId;
    }
}
