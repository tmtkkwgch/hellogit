package jp.groupsession.v2.sch.sch094;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.sch.sch100.Sch100Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール一覧表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch094Form extends Sch100Form {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch094Form.class);

    /** デフォルト表示件数 */
    private int sch094DefLine__ = -1;
    /** デフォルト表示件数 ラベル */
    private List<LabelValueBean> sch094LineLabel__ = null;
    /** 自動リロード時間コンボ */
    private List < LabelValueBean > sch094TimeLabelList__ = null;
    /** 自動リロード時間の選択値 */
    private String sch094ReloadTime__ = null;
    /** 表示開始曜日設定の曜日コンボ */
    private List < LabelValueBean > sch094WeekList__ = null;
    /** 表示開始曜日設定の曜日の選択値 */
    private String sch094SelWeek__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Sch094Form() {
        //ソートキーラベル
        ArrayList<LabelValueBean> lineLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < GSConstSchedule.LIST_LINE_COUNTER.length; i++) {
            String label = String.valueOf(GSConstSchedule.LIST_LINE_COUNTER[i]);
            String value = Integer.toString(GSConstSchedule.LIST_LINE_COUNTER[i]);
            log__.debug("label==>" + label);
            log__.debug("value==>" + value);
            lineLabel.add(new LabelValueBean(label, value));
        }
        sch094LineLabel__ = lineLabel;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheck(HttpServletRequest req) {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        //表示件数
        boolean kensuFlg = false;
        for (int kensu : GSConstSchedule.LIST_LINE_COUNTER) {
            if (sch094DefLine__ == kensu) {
                kensuFlg = true;
                break;
            }
        }
        GsMessage gsMsg = new GsMessage();
        /** メッセージ 表示件数 **/
        String dsp = gsMsg.getMessage(req, "cmn.number.display");

        if (!kensuFlg) {
            msg = new ActionMessage("error.select3.required.text", dsp);
            errors.add("sch094DefLine" + "error.select3.required.text", msg);
        }

        return errors;
    }

    /**
     * <p>sch094DefLine を取得します。
     * @return sch094DefLine
     */
    public int getSch094DefLine() {
        return sch094DefLine__;
    }

    /**
     * <p>sch094DefLine をセットします。
     * @param sch094DefLine sch094DefLine
     */
    public void setSch094DefLine(int sch094DefLine) {
        sch094DefLine__ = sch094DefLine;
    }

    /**
     * <p>sch094LineLabel を取得します。
     * @return sch094LineLabel
     */
    public List<LabelValueBean> getSch094LineLabel() {
        return sch094LineLabel__;
    }

    /**
     * <p>sch094LineLabel をセットします。
     * @param sch094LineLabel sch094LineLabel
     */
    public void setSch094LineLabel(List<LabelValueBean> sch094LineLabel) {
        sch094LineLabel__ = sch094LineLabel;
    }

    /**
     * <p>sch094ReloadTime を取得します。
     * @return sch094ReloadTime
     */
    public String getSch094ReloadTime() {
        return sch094ReloadTime__;
    }

    /**
     * <p>sch094ReloadTime をセットします。
     * @param sch094ReloadTime sch094ReloadTime
     */
    public void setSch094ReloadTime(String sch094ReloadTime) {
        sch094ReloadTime__ = sch094ReloadTime;
    }

    /**
     * <p>sch094TimeLabelList を取得します。
     * @return sch094TimeLabelList
     */
    public List<LabelValueBean> getSch094TimeLabelList() {
        return sch094TimeLabelList__;
    }

    /**
     * <p>sch094TimeLabelList をセットします。
     * @param sch094TimeLabelList sch094TimeLabelList
     */
    public void setSch094TimeLabelList(List<LabelValueBean> sch094TimeLabelList) {
        sch094TimeLabelList__ = sch094TimeLabelList;
    }

    /**
     * @return sch094SelWeek
     */
    public String getSch094SelWeek() {
        return sch094SelWeek__;
    }

    /**
     * @param sch094SelWeek 設定する sch094SelWeek
     */
    public void setSch094SelWeek(String sch094SelWeek) {
        sch094SelWeek__ = sch094SelWeek;
    }

    /**
     * @return sch094WeekList
     */
    public List<LabelValueBean> getSch094WeekList() {
        return sch094WeekList__;
    }

    /**
     * @param sch094WeekList 設定する sch094WeekList
     */
    public void setSch094WeekList(List<LabelValueBean> sch094WeekList) {
        sch094WeekList__ = sch094WeekList;
    }
}
