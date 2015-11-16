package jp.groupsession.v2.cir;

import jp.groupsession.v2.struts.AdminAction;

/**
 * <br>[機  能] 回覧板プラグインで共通的に使用するアクションクラスです(管理者用)
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractCircularAdminAction extends AdminAction {
    /** プラグインID */
    private static final String PLUGIN_ID = "circular";

    /**プラグインIDを取得します
     * @return String プラグインID
     * @see jp.groupsession.v2.struts.AbstractGsAction#getPluginId()
     */
    @Override
    public String getPluginId() {
        return PLUGIN_ID;
    }
}