package jp.groupsession.v2.api.file.update;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
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
 * <br>[機  能] ファイルの更新を行うWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileUpdateAction extends AbstractApiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiFileUpdateAction.class);

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

        //ファイル管理プラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstFile.PLUGIN_ID_FILE)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConstFile.PLUGIN_ID_FILE));
            return null;
        }
        //リクエストからファイル情報の取得する。
        FormFile formFile = ((ApiFileUpdateForm) form).getUploadFile();

        if (formFile == null || formFile.getFileName() == null) {
            Element result = new Element("Result");
            Document doc = new Document(result);
            result.addContent(getInterMessage(req, "cmn.select.file"));
            return doc;

        }
        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);

        ApiFileUpdateForm thisForm = (ApiFileUpdateForm) form;
        ApiFileUpdateBiz biz = new ApiFileUpdateBiz(con, umodel.getUsrsid());
        CommonBiz cmnBiz = new CommonBiz();

        //入力チェック
        ActionErrors errors = thisForm.validateFileUpdate(
                                 con, umodel, gsMsg, getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return null;
        }

        ApiFileUpdateParamModel param = new ApiFileUpdateParamModel();
        param.setParam(thisForm);

        //ファイルをロックする。
        biz.fileLock(NullDefault.getInt(thisForm.getFdrSid(), -1), umodel.getUsrsid());


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

            //登録処理
            biz.registerData(
                    param,
                    tempDir,
                    cntCon,
                    appRootPath,
                    plconf,
                    smailPluginUseFlg, getRequestModel(req));

            //ロックを解除する。
            ApiFileUpdateBiz lockbiz = new ApiFileUpdateBiz(con, umodel.getUsrsid());
            lockbiz.fileUnlock(NullDefault.getInt(thisForm.getFdrSid(), -1));

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

        //ログ出力処理
        FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);
        String fileName = "";
        if (thisForm.getUploadFile() != null) {
            fileName = thisForm.getUploadFile().getFileName();
        }
        fileBiz.outPutApiLog(umodel.getUsrsid(), this.getClass().getCanonicalName(),
                getInterMessage(req, "cmn.change"), GSConstLog.LEVEL_TRACE, "[file]" + fileName);

        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);

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
