package jp.groupsession.v2.api.webmail.importmail;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.wml.biz.WmlBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <br>[機  能] WEBメールのインポートを行うWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiImportMailAction extends AbstractApiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiImportMailAction.class);

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

        log__.debug("createXml start");
        //WEBメールプラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstWebmail.PLUGIN_ID_WEBMAIL)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConstWebmail.PLUGIN_ID_WEBMAIL));
            return null;
        }

        //入力チェック
        ApiImportMailForm thisForm = (ApiImportMailForm) form;
        RequestModel reqMdl = getRequestModel(req);
        ActionErrors errors = thisForm.validate(con, reqMdl, umodel.getUsrsid());
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return null;
        }

        ApiImportMailBiz biz = new ApiImportMailBiz();
        String impTempDir = null;
        boolean commit = false;

        try {
            //インポートファイルをテンポラリディレクトリへアップロード
            impTempDir = getTempPath(req)
                    + "/" + GSConstWebmail.PLUGIN_ID_WEBMAIL
                    + "/" +  req.getSession().getId()
                    + "/import/";
            File saveFilePath = new File(impTempDir + "importFile.eml");
            TempFileUtil.upload(thisForm.getImportFile(), impTempDir, saveFilePath.getName());

            String tempDir = impTempDir + "/tmp/";
            IOTools.isDirCheck(tempDir, true);

            int userSid = getSessionUserModel(req).getUsrsid();
            ApiImportMailModel impModel = new ApiImportMailModel();
            impModel.setCon(con);
            impModel.setReqMdl(reqMdl);
            impModel.setMsgResource(getResources(req));
            impModel.setMtCon(getCountMtController(req));
            impModel.setForm(thisForm);
            impModel.setUserSid(userSid);
            impModel.setAppRootPath(getAppRootPath());
            impModel.setTempDir(tempDir);
            impModel.setImpFilePath(saveFilePath);

            biz.importMsgFile(impModel);
            con.commit();
            commit = true;

            //オペレーションログ出力処理
            WmlBiz wmlBiz = new WmlBiz();
            wmlBiz.outPutApiLog(reqMdl, con, userSid, getClass().getCanonicalName(),
                    getInterMessage(req, "cmn.import"), GSConstLog.LEVEL_TRACE,
                    "[file]" + thisForm.getImportFile().getFileName());

        } catch (Throwable e) {
            log__.error("インポート失敗", e);
        } finally {
            if (!commit) {
                con.rollback();
            }

            if (impTempDir != null) {
                IOTools.deleteDir(new File(impTempDir));
            }
        }

        //ルートエレメントResultSet
        Element result = new Element("Result");
        Document doc = new Document(result);
        if (commit) {
            result.addContent("OK");
        } else {
            result.addContent("NG");
        }

        log__.debug("createXml end");
        return doc;
    }

}
