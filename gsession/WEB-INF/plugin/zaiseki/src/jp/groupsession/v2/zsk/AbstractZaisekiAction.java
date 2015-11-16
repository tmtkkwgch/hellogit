package jp.groupsession.v2.zsk;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.struts.AbstractGsAction;

/**
 * <br>[機  能] 在席管理プラグインで共通使用するアクションクラスです
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractZaisekiAction extends AbstractGsAction {
    /** プラグインID */
    private static final String PLUGIN_ID = "zaiseki";

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
    protected String _getZaisekiTempDir(HttpServletRequest req) {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), GSConstZaiseki.PLUGIN_ID_ZAISEKI, getRequestModel(req));

        return tempDir;
    }
}