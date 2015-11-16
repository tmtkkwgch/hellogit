package jp.groupsession.v2.ntp.ntp094;

import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.ntp090.Ntp090ParamModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 日報一覧表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp094ParamModel extends Ntp090ParamModel {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp094ParamModel.class);

    /** デフォルト表示件数 */
    private int ntp094DefLine__ = -1;
    /** デフォルト表示件数 ラベル */
    private List<LabelValueBean> ntp094LineLabel__ = null;
    /** 自動リロード時間コンボ */
    private List < LabelValueBean > ntp094TimeLabelList__ = null;
    /** 自動リロード時間の選択値 */
    private String ntp094ReloadTime__ = null;
    /** デフォルト表示件数 */
    private int ntp094Position__ = GSConstNippou.DAY_POSITION_RIGHT;
    /** 初期表示フラグ */
    private int ntp094InitFlg__ = GSConstNippou.INIT_FLG;


    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Ntp094ParamModel() {
        //ソートキーラベル
        ArrayList<LabelValueBean> lineLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < GSConstNippou.LIST_LINE_COUNTER.length; i++) {
            String label = String.valueOf(GSConstNippou.LIST_LINE_COUNTER[i]);
            String value = Integer.toString(GSConstNippou.LIST_LINE_COUNTER[i]);
            log__.debug("label==>" + label);
            log__.debug("value==>" + value);
            lineLabel.add(new LabelValueBean(label, value));
        }
        ntp094LineLabel__ = lineLabel;
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @return エラー
     */
    public ActionErrors validateCheck() {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        //表示件数
        boolean kensuFlg = false;
        for (int kensu : GSConstNippou.LIST_LINE_COUNTER) {
            if (ntp094DefLine__ == kensu) {
                kensuFlg = true;
                break;
            }
        }
        if (!kensuFlg) {
            msg = new ActionMessage("error.select3.required.text", "表示件数");
            errors.add("ntp094DefLine" + "error.select3.required.text", msg);
        }

        return errors;
    }

    /**
     * <p>ntp094DefLine を取得します。
     * @return ntp094DefLine
     */
    public int getNtp094DefLine() {
        return ntp094DefLine__;
    }

    /**
     * <p>ntp094DefLine をセットします。
     * @param ntp094DefLine ntp094DefLine
     */
    public void setNtp094DefLine(int ntp094DefLine) {
        ntp094DefLine__ = ntp094DefLine;
    }

    /**
     * <p>ntp094LineLabel を取得します。
     * @return ntp094LineLabel
     */
    public List<LabelValueBean> getNtp094LineLabel() {
        return ntp094LineLabel__;
    }

    /**
     * <p>ntp094LineLabel をセットします。
     * @param ntp094LineLabel ntp094LineLabel
     */
    public void setNtp094LineLabel(List<LabelValueBean> ntp094LineLabel) {
        ntp094LineLabel__ = ntp094LineLabel;
    }

    /**
     * <p>ntp094ReloadTime を取得します。
     * @return ntp094ReloadTime
     */
    public String getNtp094ReloadTime() {
        return ntp094ReloadTime__;
    }

    /**
     * <p>ntp094ReloadTime をセットします。
     * @param ntp094ReloadTime ntp094ReloadTime
     */
    public void setNtp094ReloadTime(String ntp094ReloadTime) {
        ntp094ReloadTime__ = ntp094ReloadTime;
    }

    /**
     * <p>ntp094TimeLabelList を取得します。
     * @return ntp094TimeLabelList
     */
    public List<LabelValueBean> getNtp094TimeLabelList() {
        return ntp094TimeLabelList__;
    }

    /**
     * <p>ntp094TimeLabelList をセットします。
     * @param ntp094TimeLabelList ntp094TimeLabelList
     */
    public void setNtp094TimeLabelList(List<LabelValueBean> ntp094TimeLabelList) {
        ntp094TimeLabelList__ = ntp094TimeLabelList;
    }

    /**
     * <p>ntp094Position を取得します。
     * @return ntp094Position
     */
    public int getNtp094Position() {
        return ntp094Position__;
    }

    /**
     * <p>ntp094Position をセットします。
     * @param ntp094Position ntp094Position
     */
    public void setNtp094Position(int ntp094Position) {
        ntp094Position__ = ntp094Position;
    }

    /**
     * <p>ntp094InitFlg を取得します。
     * @return ntp094InitFlg
     */
    public int getNtp094InitFlg() {
        return ntp094InitFlg__;
    }

    /**
     * <p>ntp094InitFlg をセットします。
     * @param ntp094InitFlg ntp094InitFlg
     */
    public void setNtp094InitFlg(int ntp094InitFlg) {
        ntp094InitFlg__ = ntp094InitFlg;
    }

}
