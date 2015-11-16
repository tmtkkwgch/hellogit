package jp.groupsession.v2.api.file.add;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <br>[機  能] ファイルの登録を行うWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileAddAction extends AbstractApiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiFileAddAction.class);

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
        RequestModel reqMdl = getRequestModel(req);

        //ファイル管理プラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstFile.PLUGIN_ID_FILE)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConstFile.PLUGIN_ID_FILE));
            return null;
        }
        //リクエストからファイル情報の取得する。
        FormFile formFile = ((ApiFileAddForm) form).getUploadFile();
        GsMessage gsMsg = new GsMessage(reqMdl.getLocale());

        if (formFile == null || formFile.getFileName() == null) {
            String textSelectFile = gsMsg.getMessage("cmn.select.file");
            Element result = new Element("Result");
            Document doc = new Document(result);
            result.addContent(textSelectFile);
            return doc;
        }

        ApiFileAddForm thisForm = (ApiFileAddForm) form;
        ApiFileAddBiz biz = new ApiFileAddBiz(con, umodel.getUsrsid(), reqMdl);

        //入力チェック
        ActionErrors errors = thisForm.validateFileUpload(con, umodel, gsMsg, reqMdl);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return null;
        }

        CommonBiz cmnBiz = new CommonBiz();

        //テンポラリディレクトリ
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), getPluginId(), getRequestModel(req));

        //アプリケーションルートパス
        String appRootPath = getAppRootPath();

        //プラグイン設定取得
        PluginConfig plconf = getPluginConfig(req);
        PluginConfig pconfig = getPluginConfigForMain(plconf, con);
        boolean smailPluginUseFlg = cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig);

        //採番コントローラ
        MlCountMtController cntCon = getCountMtController(req);

        boolean commitFlg = false;

        try {

            //テンポラリディレクトリに保存する。
            biz.setTempFile(tempDir, formFile);


            ApiFileAddParamModel paramMdl = new ApiFileAddParamModel();
            paramMdl.setParam(thisForm);
            //登録処理
            biz.registerData(
                    paramMdl,
                    tempDir,
                    cntCon,
                    appRootPath,
                    plconf,
                    smailPluginUseFlg);
            paramMdl.setFormData(thisForm);

            //ログ出力処理
            FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);
            String fileName = "";
            if (thisForm.getUploadFile() != null) {
                fileName = thisForm.getUploadFile().getFileName();
            }

            String textEntry = gsMsg.getMessage("cmn.entry");


            fileBiz.outPutApiLog(umodel.getUsrsid(), this.getClass().getCanonicalName(),
                    textEntry, GSConstLog.LEVEL_TRACE, "[file]" + fileName);

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

        //テンポラリディレクトリのファイルを削除する
        try {
            IOTools.deleteDir(tempDir);
        } catch (Exception e) {
            log__.warn("テンポラリディレクトリのファイル削除に失敗: " + tempDir);
            log__.warn("ファイル登録を続行します。");
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
