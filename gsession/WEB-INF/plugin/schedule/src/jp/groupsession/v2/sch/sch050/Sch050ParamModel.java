package jp.groupsession.v2.sch.sch050;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.model.AbstractParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール個人設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch050ParamModel extends AbstractParamModel {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch050ParamModel.class);

    /** 開始時 */
    private String sch050FrHour__ = null;
    /** 開始分 */
    private String sch050FrMin__ = null;
    /** 終了時 */
    private String sch050ToHour__ = null;
    /** 終了分 */
    private String sch050ToMin__ = null;

    /** 時リスト */
    private ArrayList < LabelValueBean > sch050HourLabel__ = null;
    /** 分リスト */
    private ArrayList < LabelValueBean > sch050MinuteLabel__ = null;


    /**
     * @return sch050HourLabel を戻します。
     */
    public ArrayList < LabelValueBean > getSch050HourLabel() {
        return sch050HourLabel__;
    }

    /**
     * @param sch050HourLabel 設定する sch050HourLabel。
     */
    public void setSch050HourLabel(ArrayList < LabelValueBean > sch050HourLabel) {
        sch050HourLabel__ = sch050HourLabel;
    }

    /**
     * @return sch050MinuteLabel を戻します。
     */
    public ArrayList < LabelValueBean > getSch050MinuteLabel() {
        return sch050MinuteLabel__;
    }

    /**
     * @param sch050MinuteLabel 設定する sch050MinuteLabel。
     */
    public void setSch050MinuteLabel(ArrayList < LabelValueBean > sch050MinuteLabel) {
        sch050MinuteLabel__ = sch050MinuteLabel;
    }

    /**
     * @return sch050FrHour を戻します。
     */
    public String getSch050FrHour() {
        return sch050FrHour__;
    }

    /**
     * @param sch050FrHour 設定する sch050FrHour。
     */
    public void setSch050FrHour(String sch050FrHour) {
        sch050FrHour__ = sch050FrHour;
    }



    /**
     * @return sch050FrMin を戻します。
     */
    public String getSch050FrMin() {
        return sch050FrMin__;
    }



    /**
     * @param sch050FrMin 設定する sch050FrMin。
     */
    public void setSch050FrMin(String sch050FrMin) {
        sch050FrMin__ = sch050FrMin;
    }



    /**
     * @return sch050ToHour を戻します。
     */
    public String getSch050ToHour() {
        return sch050ToHour__;
    }



    /**
     * @param sch050ToHour 設定する sch050ToHour。
     */
    public void setSch050ToHour(String sch050ToHour) {
        sch050ToHour__ = sch050ToHour;
    }



    /**
     * @return sch050ToMin を戻します。
     */
    public String getSch050ToMin() {
        return sch050ToMin__;
    }



    /**
     * @param sch050ToMin 設定する sch050ToMin。
     */
    public void setSch050ToMin(String sch050ToMin) {
        sch050ToMin__ = sch050ToMin;
    }



    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheck(ActionMapping map, HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage();

        log__.debug("SCH050入力チェック開始");
        UDate frDate = new UDate();
        frDate.setHour(Integer.parseInt(sch050FrHour__));
        frDate.setMinute(Integer.parseInt(sch050FrMin__));
        frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        UDate toDate = new UDate();
        toDate.setHour(Integer.parseInt(sch050ToHour__));
        toDate.setMinute(Integer.parseInt(sch050ToMin__));
        toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        toDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);

        //from～to大小チェック
        if (frDate.compare(frDate, toDate) != UDate.LARGE) {
            //開始 < 終了
            String textStartLessEnd = gsMsg.getMessage(req, "cmn.start.lessthan.end");
            //開始・終了
            String textStartEnd = gsMsg.getMessage(req, "cmn.start.end");
            msg = new ActionMessage("error.input.comp.text", textStartEnd, textStartLessEnd);
            errors.add("" + "error.input.comp.text", msg);
        }
        log__.debug("SCH050入力チェック終了");

        return errors;
    }

}
