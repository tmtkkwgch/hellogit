package jp.groupsession.v2.api.file.move;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.api.file.update.ApiFileUpdateBiz;
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
 * <br>[機  能] フォルダ・ファイルの移動を行うWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileMoveAction extends AbstractApiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiFileMoveAction.class);

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
        GsMessage gsMsg = new GsMessage(reqMdl);
        ApiFileMoveForm thisForm = (ApiFileMoveForm) form;
        ApiFileMoveBiz biz = new ApiFileMoveBiz();

        FilCommonBiz filBiz = new FilCommonBiz();
        //ファイル管理プラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstFile.PLUGIN_ID_FILE)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConstFile.PLUGIN_ID_FILE));
            return null;
        }
        //入力チェック
        ActionErrors errors = thisForm.validateFileMove(con, gsMsg, umodel, getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return null;
        }
        ApiFileMoveParamModel param = new ApiFileMoveParamModel();
        param.setParam(thisForm);

        //ファイルをロックする。
        ApiFileUpdateBiz lockbiz = new ApiFileUpdateBiz(con, umodel.getUsrsid());
        lockbiz.fileLock(NullDefault.getInt(thisForm.getFdrSid(), -1), umodel.getUsrsid());

        boolean commitFlg = false;
        String dirName = "";
        try {
            //移動先ディレクトリ名
            dirName = filBiz.getDirctoryName(
                    NullDefault.getInt(thisForm.getFdrParentSid(), 0), con);

            //更新処理
            biz.registerData(param, reqMdl, con);

            //ロックを解除する。
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
        fileBiz.outPutApiLog(umodel.getUsrsid(), this.getClass().getCanonicalName(),
                getInterMessage(req, "cmn.change"), GSConstLog.LEVEL_TRACE, "[name]" + dirName);

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
