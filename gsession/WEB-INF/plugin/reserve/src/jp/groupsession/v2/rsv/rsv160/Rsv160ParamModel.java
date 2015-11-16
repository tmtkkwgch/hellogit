package jp.groupsession.v2.rsv.rsv160;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.rsv.rsv140.Rsv140ParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能]施設予約 個人設定 日間表示時間帯設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv160ParamModel extends Rsv140ParamModel {
    /** 時間コンボリスト */
    private ArrayList<LabelValueBean> rsv160ourLabelList__ = null;
    /** 時間コンボ 選択 */
    private String rsv160SelectedFromSid__ = "";
    /** 時間コンボ 選択 */
    private String rsv160SelectedToSid__ = "";
    /** 初期表示フラグ */
    private int rsv160initDspFlg__ = 0;

    /**
     * <br>[機  能] 入力チェックを行います
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return errors
     */
    public ActionErrors validateCheck(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        String eprefix = "reserve";

        //時間帯設定整合性チェック
        if (Integer.parseInt(rsv160SelectedFromSid__) > Integer.parseInt(rsv160SelectedToSid__)) {
            GsMessage gsMsg = new GsMessage();

            msg = new ActionMessage("error.input.comp.text",
                    gsMsg.getMessage(req, "cmn.show.timezone.days.setting"),
                    gsMsg.getMessage(req, "cmn.start.or.end"));
            StrutsUtil.addMessage(
                    errors, msg, eprefix + "rsv160SelectedFromSid__");
        }
        return errors;
    }
    /**
     * <p>rsv160initDspFlg を取得します。
     * @return rsv160initDspFlg
     */
    public int getRsv160initDspFlg() {
        return rsv160initDspFlg__;
    }
    /**
     * <p>rsv160initDspFlg をセットします。
     * @param rsv160initDspFlg rsv160initDspFlg
     */
    public void setRsv160initDspFlg(int rsv160initDspFlg) {
        rsv160initDspFlg__ = rsv160initDspFlg;
    }
    /**
     * <p>rsv160SelectedFromSid を取得します。
     * @return rsv160SelectedFromSid
     */
    public String getRsv160SelectedFromSid() {
        return rsv160SelectedFromSid__;
    }
    /**
     * <p>rsv160SelectedFromSid をセットします。
     * @param rsv160SelectedFromSid rsv160SelectedFromSid
     */
    public void setRsv160SelectedFromSid(String rsv160SelectedFromSid) {
        rsv160SelectedFromSid__ = rsv160SelectedFromSid;
    }
    /**
     * <p>rsv160SelectedToSid を取得します。
     * @return rsv160SelectedToSid
     */
    public String getRsv160SelectedToSid() {
        return rsv160SelectedToSid__;
    }
    /**
     * <p>rsv160SelectedToSid をセットします。
     * @param rsv160SelectedToSid rsv160SelectedToSid
     */
    public void setRsv160SelectedToSid(String rsv160SelectedToSid) {
        rsv160SelectedToSid__ = rsv160SelectedToSid;
    }
    /**
     * <p>rsv160ourLabelList を取得します。
     * @return rsv160ourLabelList
     */
    public ArrayList<LabelValueBean> getRsv160ourLabelList() {
        return rsv160ourLabelList__;
    }
    /**
     * <p>rsv160ourLabelList をセットします。
     * @param rsv160ourLabelList rsv160ourLabelList
     */
    public void setRsv160ourLabelList(ArrayList<LabelValueBean> rsv160ourLabelList) {
        rsv160ourLabelList__ = rsv160ourLabelList;
    }

}