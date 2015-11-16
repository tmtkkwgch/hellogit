package jp.groupsession.v2.api.webmail.countm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.wml.dao.WebmailDao;
import jp.groupsession.v2.wml.dao.base.WmlAccountDao;
import jp.groupsession.v2.wml.model.WmlMainInfoMessageModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <br>[機  能] WEBメールの未読メール件数を取得するWEBAPIアクション
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
        //WEBメールプラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstWebmail.PLUGIN_ID_WEBMAIL)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConstWebmail.PLUGIN_ID_WEBMAIL));
            return null;
        }

        //ResultSet
        Element resultSet = new Element("ResultSet");
        Document doc = new Document(resultSet);

        int usid = umodel.getUsrsid();

        //未確認の件数を取得する。
        try {
            WmlAccountDao accountDao = new WmlAccountDao(con);
            int[] wacSidList = accountDao.getAccountSidList(usid);
            if (wacSidList != null && wacSidList.length > 0) {

                WebmailDao wmlDao = new WebmailDao(con);
                List<WmlMainInfoMessageModel> wmlMsgList
                        = wmlDao.getMainInfoMessageList(usid, wacSidList);

                for (WmlMainInfoMessageModel wmlMsgData : wmlMsgList) {
                   //Result
                    Element result = new Element("Result");
                    resultSet.addContent(result);
                    __setWmlElement(result, wmlMsgData);
                }
            }
        } catch (SQLException e) {
            log__.error("未開封メッセージカウントの取得に失敗", e);
        }

        return doc;
    }

    /**
     * <br>[機  能] 未読情報をXMLのresult属性にセットする。
     * <br>[解  説]
     * <br>[備  考]
     * @param result エレメント
     * @param wmlMsgList WmlMainInfoMessageModel
     * @throws Exception 実行例外
     */
    private void __setWmlElement(Element result, WmlMainInfoMessageModel wmlMsgList)
            throws Exception {

        //WacSid
        result.addContent(_createElement("WacSid", wmlMsgList.getWacSid()));

        //WacName
        result.addContent(_createElement("WacName", wmlMsgList.getWacName()));

        //CountM
        Element countm = new Element("CountM");
        countm.addContent(Long.toString(wmlMsgList.getNoReadCount()));
        result.addContent(countm);

    }
}
