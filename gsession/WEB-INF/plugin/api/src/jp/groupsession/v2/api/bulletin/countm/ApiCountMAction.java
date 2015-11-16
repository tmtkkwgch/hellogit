package jp.groupsession.v2.api.bulletin.countm;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.cmn.dao.BaseUserModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <br>[機  能] 掲示板未確認件数を取得するWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiCountMAction extends AbstractApiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiCountMAction.class);

    /**
     * <br>[機  能] レスポンスXML情報を作成する。
     * <br>[解  説]
     * <br>[備  考]
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @param umodel ユーザ情報
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    public Document createXml(ActionForm form, HttpServletRequest req,
            HttpServletResponse res, Connection con, BaseUserModel umodel)
            throws Exception {
        //掲示板プラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstBulletin.PLUGIN_ID_BULLETIN)) {
            addErrors(req, addCantAccsessPluginError(
                    req, null, GSConstBulletin.PLUGIN_ID_BULLETIN));
            return null;
        }

        //未確認の件数を取得する。
        BulletinDao dao = new BulletinDao(con);
        int count = 0;
        try {
            count = dao.getThreadListCnt(umodel.getUsrsid());
        } catch (SQLException e) {
            log__.error("未確認スレッドカウントの取得に失敗", e);
        }

        //Result
        Element result = new Element("Result");
        Document doc = new Document(result);

        //
        log__.debug("createXml start");
        result.addContent(Integer.toString(count));
        log__.debug("createXml end");
        return doc;
    }

}
