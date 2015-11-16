package jp.groupsession.v2.ptl;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.man.GSConstPortal;
import jp.groupsession.v2.struts.AdminAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ポータルで共通的に使用するアクションクラスです
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractPortalAction extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(AbstractPortalAction.class);

    /** プラグインID */
    private static final String PLUGIN_ID = GSConstPortal.PLUGIN_ID;

    /**プラグインIDを取得します
     * @return String プラグインID
     * @see jp.groupsession.v2.struts.AbstractGsAction#getPluginId()
     */
    @Override
    public String getPluginId() {
        return PLUGIN_ID;
    }

    /**
     * <br>[機  能] テンポラリディレクトリパスを取得する
     * <br>[解  説] 処理モード = 編集の場合、スレッド情報を設定する
     * <br>[備  考]
     * @param req リクエスト
     * @return テンポラリディレクトリパス
     */
    protected String _getPortalTempDir(HttpServletRequest req) {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), PLUGIN_ID, getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        return tempDir;
    }

}
