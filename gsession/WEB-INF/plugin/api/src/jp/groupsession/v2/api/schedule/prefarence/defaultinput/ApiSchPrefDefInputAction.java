package jp.groupsession.v2.api.schedule.prefarence.defaultinput;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.api.schedule.AbstractApiSchAction;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchColMsgDao;
import jp.groupsession.v2.sch.model.SchColMsgModel;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.jdom.Document;
import org.jdom.Element;
/**
 *
 * <br>[機  能]スケジュール初期値設定取得WEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiSchPrefDefInputAction extends AbstractApiSchAction {

    /** ログ */
    private static Log log__ =
            LogFactory.getLog(new Throwable().getStackTrace()[0].getClassName());

    /** タイトル色固定データ */
    private static String[] colorArray__ = {
        "#0000ff",
        "#ff0000",
        "#009900",
        "#ff9900",
        "#333333",
        "#000080",
        "#800000",
        "#008080",
        "#808080",
        "#008dcb"
    };

    @Override
    public Document createXml(ActionForm aForm, HttpServletRequest req,
            HttpServletResponse res, Connection con, BaseUserModel umodel)
            throws Exception {
        log__.debug("createXml start");
        RequestModel reqMdl = getRequestModel(req);
        ApiSchPrefDefInputForm form = (ApiSchPrefDefInputForm) aForm;
        GsMessage gsMsg = new GsMessage(reqMdl);
        ActionErrors err = form.validateCheck(gsMsg);
        if (!err.isEmpty()) {
            addErrors(req, err);
            return null;
        }


        int usrSid = NullDefault.getInt(form.getUsrSid(), umodel.getUsrsid());

        SchCommonBiz biz = new SchCommonBiz(reqMdl);
        SchPriConfModel pconf = biz.getSchPriConfModel(con, usrSid);

        SchColMsgDao colDao = new SchColMsgDao(con);
        ArrayList<SchColMsgModel> colorList = colDao.select();

        //開始時刻
        UDate fr = pconf.getSccIniFrDate();
        //終了時刻
        UDate to = pconf.getSccIniToDate();
        //タイトルカラー
        int clrKbn = pconf.getSccIniFcolor();
        //公開区分
        int pubKbn = biz.getInitPubAuth(con, pconf);  // pconf.getSccIniPublic();
        //編集区分
        int ediKbn = biz.getInitEditAuth(con, pconf); // pconf.getSccIniEdit();
        //同時編集区分
        int samKbn = biz.getInitSameAuth(con, pconf); // pconf.getSccIniSame();

        //ルートエレメントResultSet
        Element resultSet = new Element("ResultSet");
        Document doc = new Document(resultSet);
        Element result = new Element("Result");
        resultSet.addContent(result);

        result.addContent(_createElement("DefaultFromTime", UDateUtil.getSeparateHM(fr)));
        result.addContent(_createElement("DefaultToTime", UDateUtil.getSeparateHM(to)));
        result.addContent(_createElement("DefaultColorKbn", clrKbn));
        result.addContent(_createElement("DefaultSchKf", pubKbn));
        result.addContent(_createElement("DefaultSchEf", ediKbn));
        result.addContent(_createElement("DefaultSchSf", samKbn));
        int hourDivCount = biz.getDayScheduleHourMemoriCount(con);
        int hourMemCount = 60 / hourDivCount;

        result.addContent(_createElement("MinutesDivine", hourMemCount));

        // タイトル色一覧
        if (colorList != null) {
            for (int i = 0; i < colorArray__.length; i++) {
                String  colRgb   = colorArray__[i];
                int     colSid   = i + 1;
                String  colTitle = "";
                boolean isUsed   = false;

                // 一覧からデータ抽出
                for (int j = 0; j < colorList.size(); j++) {
                    SchColMsgModel col = colorList.get(j);
                    if (col.getScmId() == colSid) {
                        colTitle = col.getScmMsg();
                        isUsed   = true;
                        colorList.remove(j); // リストから除外
                        break;
                    }
                }

                Element titleColor = new Element("TitleColors");
                titleColor.addContent(_createElement("kbn",   colSid));
                titleColor.addContent(_createElement("title", colTitle));
                titleColor.addContent(_createElement("color", colRgb));
                titleColor.addContent(_createElement("use",   (isUsed ? 1 : 0)));
                result.addContent(titleColor);
            }
        }

        log__.debug("createXml end");


        return doc;
    }

}
