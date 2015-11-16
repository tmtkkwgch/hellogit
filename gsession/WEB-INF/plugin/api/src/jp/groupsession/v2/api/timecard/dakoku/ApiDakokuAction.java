package jp.groupsession.v2.api.timecard.dakoku;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.TimecardBiz;
import jp.groupsession.v2.tcd.TimecardUtil;
import jp.groupsession.v2.tcd.model.TcdTcdataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <br>[機  能] タイムカードの始業終業時間の打刻を行うWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiDakokuAction extends AbstractApiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiDakokuAction.class);

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
        //タイムカードプラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConstTimecard.PLUGIN_ID_TIMECARD)) {
            addErrors(req,
                        addCantAccsessPluginError(req, null,
                                GSConstTimecard.PLUGIN_ID_TIMECARD));
            return null;
        }
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage(req);
        //タイムカードを更新する。
        ApiDakokuBiz biz = new ApiDakokuBiz();
        int updateKbn = biz.updateTcd(umodel.getUsrsid(), con);

        /** メッセージ タイムカード **/
        String timeCard = gsMsg.getMessage("tcd.50");

        if (updateKbn == 1) {
            //タイムカード更新済みの場合
            msg = new ActionMessage("error.input.timecard.exist", timeCard);
            StrutsUtil.addMessage(errors, msg, "timecard");
            addErrors(req, errors);
            return null;
        } else if (updateKbn == 2) {
            //タイムカード情報が不正の場合
            msg = new ActionMessage("error.input.format.file",
                    timeCard, gsMsg.getMessage("tcd.add.value"));
            StrutsUtil.addMessage(errors, msg, "timecard");
            addErrors(req, errors);
            return null;
        }

        //ログ出力
        UDate sysDate = new UDate();
        TimecardBiz tbiz = new TimecardBiz();
        TcdTcdataModel tcMdl = tbiz.getTargetTcddata(umodel.getUsrsid(), sysDate, con);
        String date = "";
        String inTime = "";
        String outTime = "";
        if (tcMdl == null) {
            date = tcMdl.getTcdDate().getDateString();
            if (tcMdl.getTcdStrikeIntime() != null) {
                inTime = TimecardUtil.getTimeString(tcMdl.getTcdStrikeIntime());
            }
            if (tcMdl.getTcdStrikeOuttime() != null) {
                outTime = TimecardUtil.getTimeString(tcMdl.getTcdStrikeOuttime());
            }
        }
        tbiz.outPutApiLog(getRequestModel(req), con, umodel.getUsrsid(),
                this.getClass().getCanonicalName(),
                getInterMessage(req, "cmn.change"), GSConstLog.LEVEL_TRACE,
                "[date]" + date + " [in]" + inTime + " [out]" + outTime);

        //ルートエレメントResultSet
        Element result = new Element("Result");
        Document doc = new Document(result);

        //XMLデータ作成
        String ret = "OK";
        result.addContent(ret);

        log__.debug("createXml end");
        return doc;
    }
}
