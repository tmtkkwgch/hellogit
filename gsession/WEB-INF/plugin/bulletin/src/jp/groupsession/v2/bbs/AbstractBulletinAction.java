package jp.groupsession.v2.bbs;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.bbs.dao.BbsForInfDao;
import jp.groupsession.v2.bbs.model.BbsForInfModel;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.struts.AbstractGsAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 掲示板で共通使用するアクションクラスです。
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractBulletinAction extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(AbstractBulletinAction.class);

    /** プラグインID */
    private static final String PLUGIN_ID = "bulletin";

    /**プラグインIDを取得します
     * @return String プラグインID
     * @see jp.groupsession.v2.struts.AbstractGsAction#getPluginId()
     */
    @Override
    public String getPluginId() {
        return PLUGIN_ID;
    }

    /**
     * <br>[機  能] フォーラム閲覧権限のチェックを行う
     * <br>[解  説]
     * <br>[備  考] 権限無しの場合はエラーメッセージページへのパラメータを設定
     * @param map マップ
     * @param req リクエスト
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @return 権限の有無 true:権限あり false:権限無し
     * @throws Exception 実行例外
     */
    protected boolean _checkForumAuth(ActionMapping map,
        HttpServletRequest req,
        Connection con,
        int bfiSid)
        throws Exception {

        log__.debug("checkForumAuth START");

        BaseUserModel buMdl = getSessionUserModel(req);

//        //管理者の場合は権限あり
//        if (buMdl.isAdmin()) {
//            return true;
//        }

        //フォーラム閲覧権限のチェック
        BbsBiz bbsBiz = new BbsBiz();
        if (bbsBiz.checkForumAuth(con, bfiSid, buMdl.getUsrsid())) {
            return true;
        }


        //権限なしの場合はメッセージページのパラメータを設定する
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("backBBSList");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "error.access.forum.user";
        cmn999Form.setMessage(msgRes.getMessage(msgState));

        req.setAttribute("cmn999Form", cmn999Form);

        log__.debug("checkForumAuth END");

        return false;
    }

    /**
     * <br>[機  能] 返信可能なフォーラムかチェックを行う。
     * <br>[解  説]
     * <br>[備  考] 権限無しの場合はエラーメッセージページへのパラメータを設定
     * @param map マップ
     * @param req リクエスト
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @return 返信可能区分 true:可能 false:不可能
     * @throws Exception 実行例外
     */
    protected boolean _checkReplyAuth(ActionMapping map,
        HttpServletRequest req,
        Connection con,
        int bfiSid)
        throws Exception {

        log__.debug("checkReplyAuth START");

        //フォーラム閲覧権限のチェック
        BbsForInfDao bfiDao = new BbsForInfDao(con);
        BbsForInfModel bfiModel = new BbsForInfModel();
        bfiModel.setBfiSid(bfiSid);
        BbsForInfModel ret = bfiDao.select(bfiModel);
        if (ret.getBfiReply() == GSConstBulletin.BBS_THRE_REPLY_YES) {
            return true;
        }

        //権限なしの場合はメッセージページのパラメータを設定する
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("backBBSList");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "error.cannot.reply.thread";
        cmn999Form.setMessage(msgRes.getMessage(msgState));

        req.setAttribute("cmn999Form", cmn999Form);

        log__.debug("checkReplyAuth END");

        return false;
    }

    /**
     * <br>[機  能] テンポラリディレクトリパスを取得する
     * <br>[解  説] 処理モード = 編集の場合、スレッド情報を設定する
     * <br>[備  考]
     * @param req リクエスト
     * @return テンポラリディレクトリパス
     */
    protected String _getBulletinTempDir(HttpServletRequest req) {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir
            = cmnBiz.getTempDir(
                    getTempPath(req), GSConstBulletin.PLUGIN_ID_BULLETIN, getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        return tempDir;
    }
}
