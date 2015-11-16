package jp.groupsession.v2.rsv.rsv150;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv140.Rsv140Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 個人設定 表示設定画面のフォームクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv150Form extends Rsv140Form {

    /** 施設コンボリスト */
    private ArrayList<LabelValueBean> rsv150sisetuLabelList__ = null;
    /** 施設コンボリスト 選択SID */
    private int rsv150SelectedGrpSid__ = GSConstReserve.COMBO_PLEASE_CHOICE;
    /** 表示項目1 利用目的 */
    private String rsv150DispItem1__;
    /** 表示項目2 登録者名 */
    private String rsv150DispItem2__;
    /** 初期表示フラグ */
    private int rsv150initDspFlg__ = 0;
    /** 自動リロード時間コンボ */
    private List < LabelValueBean > rsv150TimeLabelList__ = null;
    /** 自動リロード時間の選択値 */
    private String rsv150ReloadTime__ = null;
    /** 施設画像表示区分 */
    private int rsv150ImgDspKbn__ = GSConstReserve.SISETU_IMG_ON;
    /** デフォルト表示画面 */
    private int rsv150DefDsp__ = 0;

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
        GsMessage gsMsg = new GsMessage();

        if ((rsv150DispItem1__ == null || rsv150DispItem1__.equals("0"))
                && (rsv150DispItem2__ == null || rsv150DispItem2__.equals("0"))) {
                msg = new ActionMessage("error.select.required.text",
                        gsMsg.getMessage(req, "reserve.100"));
                StrutsUtil.addMessage(
                        errors, msg, eprefix + "rsv150DispItem");
        }
        return errors;
    }
    /**
     * <p>rsv150ImgDspKbn を取得します。
     * @return rsv150ImgDspKbn
     */
    public int getRsv150ImgDspKbn() {
        return rsv150ImgDspKbn__;
    }
    /**
     * <p>rsv150ImgDspKbn をセットします。
     * @param rsv150ImgDspKbn rsv150ImgDspKbn
     */
    public void setRsv150ImgDspKbn(int rsv150ImgDspKbn) {
        rsv150ImgDspKbn__ = rsv150ImgDspKbn;
    }
    /**
     * <p>rsv150initDspFlg を取得します。
     * @return rsv150initDspFlg
     */
    public int getRsv150initDspFlg() {
        return rsv150initDspFlg__;
    }
    /**
     * <p>rsv150initDspFlg をセットします。
     * @param rsv150initDspFlg rsv150initDspFlg
     */
    public void setRsv150initDspFlg(int rsv150initDspFlg) {
        rsv150initDspFlg__ = rsv150initDspFlg;
    }
    /**
     * <p>rsv150DispItem1 を取得します。
     * @return rsv150DispItem1
     */
    public String getRsv150DispItem1() {
        return rsv150DispItem1__;
    }
    /**
     * <p>rsv150DispItem1 をセットします。
     * @param rsv150DispItem1 rsv150DispItem1
     */
    public void setRsv150DispItem1(String rsv150DispItem1) {
        rsv150DispItem1__ = rsv150DispItem1;
    }
    /**
     * <p>rsv150DispItem2 を取得します。
     * @return rsv150DispItem2
     */
    public String getRsv150DispItem2() {
        return rsv150DispItem2__;
    }
    /**
     * <p>rsv150DispItem2 をセットします。
     * @param rsv150DispItem2 rsv150DispItem2
     */
    public void setRsv150DispItem2(String rsv150DispItem2) {
        rsv150DispItem2__ = rsv150DispItem2;
    }
    /**
     * <p>rsv150SelectedGrpSid をセットします。
     * @param rsv150SelectedGrpSid rsv150SelectedGrpSid
     */
    public void setRsv150SelectedGrpSid(int rsv150SelectedGrpSid) {
        rsv150SelectedGrpSid__ = rsv150SelectedGrpSid;
    }
    /**
     * <p>rsv150sisetuLabelList を取得します。
     * @return rsv150sisetuLabelList
     */
    public ArrayList<LabelValueBean> getRsv150sisetuLabelList() {
        return rsv150sisetuLabelList__;
    }
    /**
     * <p>rsv150sisetuLabelList をセットします。
     * @param rsv150sisetuLabelList rsv150sisetuLabelList
     */
    public void setRsv150sisetuLabelList(
            ArrayList<LabelValueBean> rsv150sisetuLabelList) {
        rsv150sisetuLabelList__ = rsv150sisetuLabelList;
    }
    /**
     * <p>rsv150SelectedGrpSid を取得します。
     * @return rsv150SelectedGrpSid
     */
    public int getRsv150SelectedGrpSid() {
        return rsv150SelectedGrpSid__;
    }
    /**
     * <p>rsv150ReloadTime を取得します。
     * @return rsv150ReloadTime
     */
    public String getRsv150ReloadTime() {
        return rsv150ReloadTime__;
    }
    /**
     * <p>rsv150ReloadTime をセットします。
     * @param rsv150ReloadTime rsv150ReloadTime
     */
    public void setRsv150ReloadTime(String rsv150ReloadTime) {
        rsv150ReloadTime__ = rsv150ReloadTime;
    }
    /**
     * <p>rsv150TimeLabelList を取得します。
     * @return rsv150TimeLabelList
     */
    public List<LabelValueBean> getRsv150TimeLabelList() {
        return rsv150TimeLabelList__;
    }
    /**
     * <p>rsv150TimeLabelList をセットします。
     * @param rsv150TimeLabelList rsv150TimeLabelList
     */
    public void setRsv150TimeLabelList(List<LabelValueBean> rsv150TimeLabelList) {
        rsv150TimeLabelList__ = rsv150TimeLabelList;
    }
    /**
     * <p>rsv150DefDsp を取得します。
     * @return rsv150DefDsp
     */
    public int getRsv150DefDsp() {
        return rsv150DefDsp__;
    }
    /**
     * <p>rsv150DefDsp をセットします。
     * @param rsv150DefDsp rsv150DefDsp
     */
    public void setRsv150DefDsp(int rsv150DefDsp) {
        rsv150DefDsp__ = rsv150DefDsp;
    }
}