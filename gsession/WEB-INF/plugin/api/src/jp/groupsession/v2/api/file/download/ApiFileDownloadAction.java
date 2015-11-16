package jp.groupsession.v2.api.file.download;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <br>[機  能] ファイルのダウンロードを行うWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileDownloadAction extends AbstractApiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiFileDownloadAction.class);

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

        ApiFileDownloadForm thisForm = (ApiFileDownloadForm) form;
        CommonBiz cmnBiz = new CommonBiz();
        Long binSid = NullDefault.getLong(thisForm.getBinSid(), -1);
        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        //ファイル管理プラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstFile.PLUGIN_ID_FILE)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConstFile.PLUGIN_ID_FILE));
            return null;
        }
        //入力チェック
        ActionErrors errors = thisForm.validateFileDownload(con, reqMdl, gsMsg);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return null;
        }

        String tempDir = "";
        boolean okFlg = false;
        try {

            //DBよりファイル情報を取得する。
            CmnBinfModel binModel = cmnBiz.getBinInfo(con, binSid,
                    GroupSession.getResourceManager().getDomain(req));
            if (binModel == null
                    || binModel.getBinJkbn() == GSConst.JTKBN_DELETE
                    || binModel.getBinFilekbn() != GSConst.FILEKBN_FILE) {
                //ファイルが存在しないか、削除されているか場合
                __errorFilenotFound(req, errors);
                return null;
            }

            //ログ出力処理
            FilCommonBiz fileBiz = new FilCommonBiz(con, reqMdl);
            //集計用データを登録する
            fileBiz.regFileDownloadLogCnt(con, getSessionUserSid(req), binSid, new UDate());
            fileBiz.outPutApiLog(umodel.getUsrsid(), this.getClass().getCanonicalName(),
                    getInterMessage(req, "cmn.download"),
                    GSConstLog.LEVEL_INFO, binModel.getBinFileName(),
                    String.valueOf(binSid));

            //時間のかかる処理の前にコネクションを破棄
            JDBCUtil.closeConnectionAndNull(con);

            //ファイルのダウンロード
            TempFileUtil.downloadAtachment(
                    req, res, binModel, getAppRootPath(), Encoding.UTF_8);

            binModel = null;
            okFlg = true;
        } catch (SQLException e) {
            log__.error("SQL実行時エラー", e);
        } catch (IOToolsException e) {
            log__.error("ファイルのダウンロードに失敗", e);
        } finally {

            //テンポラリディレクトリのファイルを削除する
            IOTools.deleteDir(tempDir);
            log__.debug("テンポラリディレクトリのファイル削除");
        }

        //ルートエレメントResultSet
        Element result = new Element("Result");
        Document doc = new Document(result);
        if (okFlg) {
            result.addContent("OK");
        } else {
            result.addContent("NG");
        }

        log__.debug("createXml end");
        return doc;
    }

    /**
     * ファイルが存在しない場合のエラーを設定する。
     * @param req リクエスト
     * @param errors アクションエラー
     */
    private void __errorFilenotFound(HttpServletRequest req, ActionErrors errors) {

        //ファイルが存在しないか、削除されている場合
        ActionMessage msg = new ActionMessage("error.input.notfound.file");
        StrutsUtil.addMessage(errors, msg, "binSid");
        addErrors(req, errors);
    }
}
