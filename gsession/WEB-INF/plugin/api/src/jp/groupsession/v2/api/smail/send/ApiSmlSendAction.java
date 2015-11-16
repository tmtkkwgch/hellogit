package jp.groupsession.v2.api.smail.send;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <br>[機  能] ショートメールを送信するWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiSmlSendAction extends AbstractApiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiSmlSendAction.class);

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
        //ショートメールプラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstSmail.PLUGIN_ID_SMAIL)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConstSmail.PLUGIN_ID_SMAIL));
            return null;
        }

        CommonBiz cmnBiz = new CommonBiz();
        ApiSmlSendBiz biz = new ApiSmlSendBiz(umodel.getUsrsid());
        ApiSmlSendForm thisForm = (ApiSmlSendForm) form;
        GsMessage gsMsg = new GsMessage(req);
        RequestModel reqMdl = getRequestModel(req);
        //入力チェック
        ActionErrors errors
        = thisForm.validateCheckSmlSend(con, gsMsg, getSessionUserSid(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return null;
        }

        //テンポラリディレクトリパスを取得
        String tempDir = cmnBiz.getTempDir(getTempPath(req), getPluginId(), reqMdl);
        //アプリケーションのルートパス
        String appRootPath = getAppRootPath();

        boolean commitFlg = false;
        try {

            //添付ファイルをテンポラリディレクトリに保存する。
            biz.setTempFileAll(tempDir, thisForm);

            //ショートメール送信
            MlCountMtController cntCon = getCountMtController(req);
            biz.insertMailData(
                    thisForm, con, cntCon, appRootPath, tempDir, getPluginConfig(req),
                    getRequestModel(req));
            commitFlg = true;
        } catch (SQLException e) {
            log__.error("メッセージの送信に失敗", e);
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }

        //ログ出力処理
        SmlCommonBiz smlBiz = new SmlCommonBiz(con, reqMdl);
        smlBiz.outPutApiLog(req, con, umodel.getUsrsid(), this.getClass().getCanonicalName(),
                getInterMessage(req, "cmn.sent"), GSConstLog.LEVEL_TRACE, "");

        //Result
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
