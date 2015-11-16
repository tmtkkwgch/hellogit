package jp.groupsession.v2.rng;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.struts.AbstractGsAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 稟議プラグインで共通使用するアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractRingiAction extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(AbstractRingiAction.class);
    /** プラグインID */
    private static final String PLUGIN_ID = "ringi";

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
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return テンポラリディレクトリパス
     */
    protected String _getRingiDir(HttpServletRequest req) {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), RngConst.PLUGIN_ID_RINGI, getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        return tempDir;
    }

    /**
     * <br>[機  能] テンポラリディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return テンポラリディレクトリパス
     */
    protected String _getRingiTemplateDir(HttpServletRequest req) {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir
            = cmnBiz.getTempDir(
                    getTempPath(req), RngConst.PLUGIN_ID_RINGI_TEMPLATE, getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        return tempDir;
    }
}