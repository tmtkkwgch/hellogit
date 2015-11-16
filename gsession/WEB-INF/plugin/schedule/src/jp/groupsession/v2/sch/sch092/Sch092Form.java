package jp.groupsession.v2.sch.sch092;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.sch.sch100.Sch100Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール 日間表示時間帯設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch092Form extends Sch100Form {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch092Form.class);
    /** ラベル 時 */
    private List < LabelValueBean > sch092HourLabel__ = null;
    /** 開始 時 */
    private int sch092FrH__ = -1;
    /** 終了 時 */
    private int sch092ToH__ = -1;

    /**
     * <p>sch092FrH を取得します。
     * @return sch092FrH
     */
    public int getSch092FrH() {
        return sch092FrH__;
    }

    /**
     * <p>sch092FrH をセットします。
     * @param sch092FrH sch092FrH
     */
    public void setSch092FrH(int sch092FrH) {
        sch092FrH__ = sch092FrH;
    }

    /**
     * <p>sch092HourLabel を取得します。
     * @return sch092HourLabel
     */
    public List<LabelValueBean> getSch092HourLabel() {
        return sch092HourLabel__;
    }

    /**
     * <p>sch092HourLabel をセットします。
     * @param sch092HourLabel sch092HourLabel
     */
    public void setSch092HourLabel(List<LabelValueBean> sch092HourLabel) {
        sch092HourLabel__ = sch092HourLabel;
    }

    /**
     * <p>sch092ToH を取得します。
     * @return sch092ToH
     */
    public int getSch092ToH() {
        return sch092ToH__;
    }

    /**
     * <p>sch092ToH をセットします。
     * @param sch092ToH sch092ToH
     */
    public void setSch092ToH(int sch092ToH) {
        sch092ToH__ = sch092ToH;
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
        log__.debug("sch092FrH__==>" + sch092FrH__);
        log__.debug("sch092ToH__==>" + sch092ToH__);
        if (sch092FrH__ > sch092ToH__) {
            GsMessage gsMsg = new GsMessage();
            //日間表示時間帯
            String textDayTimeZoon = gsMsg.getMessage(req, "cmn.show.timezone.days");
            //開始時刻 < 終了時刻の場合
            //開始時刻 < 終了時刻
            String textStartLessEnd = gsMsg.getMessage(req, "schedule.src.65");
            msg = new ActionMessage("error.input.comp.text", textDayTimeZoon, textStartLessEnd);
            errors.add("error.input.comp.text", msg);
        }
        return errors;
    }
}
