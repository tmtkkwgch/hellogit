package jp.groupsession.v2.fil;

import jp.groupsession.v2.struts.AdminAction;

/**
 * <br>[機  能] ファイルj管理プラグインで共通的に使用するアクションクラスです(管理者用)
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractFileAdminAction extends AdminAction {
    /** プラグインID */
    private static final String PLUGIN_ID = "file";

    /**プラグインIDを取得します
     * @return String プラグインID
     * @see jp.groupsession.v2.struts.AbstractGsAction#getPluginId()
     */
    @Override
    public String getPluginId() {
        return PLUGIN_ID;
    }
}