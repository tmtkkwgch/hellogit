package jp.groupsession.v2.adr;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.struts.AbstractGsAction;

/**
 * <br>[機  能] アドレス帳プラグインで共通使用するアクションクラスです。
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractAddressAction extends AbstractGsAction {

    /** プラグインID */
    private static final String PLUGIN_ID = "address";

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
    public String getTempDirPath(HttpServletRequest req) {

        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), GSConstAddress.PLUGIN_ID_ADDRESS, getRequestModel(req));

        return tempDir;
    }
}