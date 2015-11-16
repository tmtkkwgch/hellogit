package jp.groupsession.v2.api.file.editfolder;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <br>[機  能] フォルダの編集を行うWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileEditFolderAction extends AbstractApiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiFileEditFolderAction.class);

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

        ApiFileEditFolderForm thisForm = (ApiFileEditFolderForm) form;
        ApiFileEditFolderBiz biz = new ApiFileEditFolderBiz();
        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        //ファイル管理プラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstFile.PLUGIN_ID_FILE)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConstFile.PLUGIN_ID_FILE));
            return null;
        }

        //入力チェック
        ActionErrors errors = thisForm.validateFileUpload(con, reqMdl, gsMsg);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return null;
        }

        boolean commitFlg = false;

        int upCnt = 0;
        try {

            int userSid = umodel.getUsrsid();
            ApiFileEditFolderParamModel params = new ApiFileEditFolderParamModel();
            params.setParam(thisForm);

            //編集処理
            upCnt = biz.registerData(con, params, userSid);
            log__.debug("更新件数 " + upCnt);

            //ログ出力処理
            FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);
            fileBiz.outPutApiLog(umodel.getUsrsid(), getClass().getCanonicalName(),
                            getInterMessage(req, "cmn.change"), GSConstLog.LEVEL_TRACE,
                            "[name]" + thisForm.getFdrName());

            con.commit();
            commitFlg = true;

        } catch (Exception e) {
            log__.error("Exception", e);
            throw e;

        } finally {
            if (!commitFlg) {
                JDBCUtil.rollback(con);
            }
        }

        //ルートエレメントResultSet
        Element result = new Element("Result");
        Document doc = new Document(result);
        if (commitFlg) {
            result.addContent("OK");
        } else {
            result.addContent("NG");
        }
        log__.debug("createXml end");
        return doc;
    }

}
