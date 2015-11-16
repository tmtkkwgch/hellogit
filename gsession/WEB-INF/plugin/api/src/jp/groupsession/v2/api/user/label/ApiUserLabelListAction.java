package jp.groupsession.v2.api.user.label;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrDao;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;
import org.jdom.Element;
/**
 *
 * <br>[機  能] API　ユーザラベル一覧取得
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiUserLabelListAction extends AbstractApiAction {

    /**ロガー*/
    private static Log log__ = LogFactory.getLog(ApiUserLabelListAction.class);

    @Override
    public Document createXml(ActionForm aForm, HttpServletRequest req,
            HttpServletResponse res, Connection con, BaseUserModel umodel)
            throws Exception {
        log__.debug("createXml start");

        Element resultSet = new Element("ResultSet");
        Document doc = new Document(resultSet);

        CmnLabelUsrDao dao = new CmnLabelUsrDao(con);

        List<CmnLabelUsrModel> list = dao.select();
        if (list != null) {
            for (CmnLabelUsrModel lab : list) {
                Element result = new Element("Result");
                resultSet.addContent(result);

                result.addContent(_createElement("LabSid", lab.getLabSid()));
                result.addContent(_createElement("LabName", lab.getLabName()));
                result.addContent(_createElement("LabBiko", lab.getLabBiko()));
                result.addContent(_createElement("LabSort", lab.getLabSort()));
                result.addContent(_createElement("LucSid", lab.getLucSid()));
            }
        }
        resultSet.setAttribute("TotalCount"
                , String.valueOf(resultSet.getContentSize()));

        log__.debug("createXml end");

        return doc;
    }

}
