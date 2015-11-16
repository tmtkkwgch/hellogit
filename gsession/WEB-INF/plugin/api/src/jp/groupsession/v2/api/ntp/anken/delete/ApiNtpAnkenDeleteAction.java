package jp.groupsession.v2.api.ntp.anken.delete;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;
import org.jdom.Element;

import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.ntp.dao.NtpAnShohinDao;
import jp.groupsession.v2.ntp.dao.NtpAnkenDao;
/**
 * <br>[機  能] 日報 案件削除するWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiNtpAnkenDeleteAction extends AbstractApiAction {

    /**ロガー クラス*/
    private static Log log__ = LogFactory.getLog(new Throwable().getStackTrace()[0].getClassName());
    @Override
    public Document createXml(ActionForm form, HttpServletRequest req,
            HttpServletResponse res, Connection con, BaseUserModel umodel)
            throws Exception {
        log__.debug("createXml start");
        //日報プラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConst.PLUGIN_ID_NIPPOU)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConst.PLUGIN_ID_NIPPOU));
            return null;
        }

        ApiNtpAnkenDeleteForm thisForm = (ApiNtpAnkenDeleteForm) form;

        ActionErrors err = thisForm.validateCheck();
        if (!err.isEmpty()) {
            addErrors(req, err);
            return null;
        }
        boolean commitFlg = false;
        con.setAutoCommit(false);
        try {
            NtpAnkenDao ankenDao = new NtpAnkenDao(con);

            int nanSid = Integer.parseInt(thisForm.getNanSid());

            ankenDao.delete(nanSid);
            //案件商品情報の登録
            NtpAnShohinDao anShohinDao = new NtpAnShohinDao(con);
            anShohinDao.delete(nanSid);

            commitFlg = true;


        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
        Element result = new Element("Result");
        Document doc = new Document(result);

        result.addContent("OK");

        return doc;
    }

}
