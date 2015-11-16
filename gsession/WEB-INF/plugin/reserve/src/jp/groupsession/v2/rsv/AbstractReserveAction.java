package jp.groupsession.v2.rsv;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.groupsession.v2.bbs.AbstractBulletinAction;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.struts.AbstractGsAction;

/**
 * <br>[機  能] 施設予約プラグインで共通使用するアクションクラスです
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractReserveAction extends AbstractGsAction {
    /** プラグインID */
    private static final String PLUGIN_ID = "reserve";
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(AbstractBulletinAction.class);

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
    protected String _getReserveTempDir(HttpServletRequest req) {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), GSConstReserve.PLUGIN_ID_RESERVE, getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        return tempDir;
    }


}