package jp.groupsession.v2.rsv.rsv230;

import java.util.List;

import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv140.Rsv140ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 初期値設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv230ParamModel extends Rsv140ParamModel {

    /** 開始 時 */
    private int rsv230DefFrH__ = 9;
    /** 開始 分 */
    private int rsv230DefFrM__ = 0;
    /** 終了 時 */
    private int rsv230DefToH__ = 18;
    /** 終了 分 */
    private int rsv230DefToM__ = 0;
    /** ラベル 時 */
    private List < LabelValueBean > rsv230HourLabel__ = null;
    /** ラベル 分 */
    private List < LabelValueBean > rsv230MinuteLabel__ = null;
    /** 編集権限 */
    private int rsv230Edit__ = GSConstReserve.EDIT_AUTH_NONE;
    /** 編集権限 編集許可 */
    private boolean rsv230EditFlg__ = false;

    /**
     * <p>rsv230DefFrH を取得します。
     * @return rsv230DefFrH
     */
    public int getRsv230DefFrH() {
        return rsv230DefFrH__;
    }
    /**
     * <p>rsv230DefFrH をセットします。
     * @param rsv230DefFrH rsv230DefFrH
     */
    public void setRsv230DefFrH(int rsv230DefFrH) {
        rsv230DefFrH__ = rsv230DefFrH;
    }
    /**
     * <p>rsv230DefFrM を取得します。
     * @return rsv230DefFrM
     */
    public int getRsv230DefFrM() {
        return rsv230DefFrM__;
    }
    /**
     * <p>rsv230DefFrM をセットします。
     * @param rsv230DefFrM rsv230DefFrM
     */
    public void setRsv230DefFrM(int rsv230DefFrM) {
        rsv230DefFrM__ = rsv230DefFrM;
    }
    /**
     * <p>rsv230DefToH を取得します。
     * @return rsv230DefToH
     */
    public int getRsv230DefToH() {
        return rsv230DefToH__;
    }
    /**
     * <p>rsv230DefToH をセットします。
     * @param rsv230DefToH rsv230DefToH
     */
    public void setRsv230DefToH(int rsv230DefToH) {
        rsv230DefToH__ = rsv230DefToH;
    }
    /**
     * <p>rsv230DefToM を取得します。
     * @return rsv230DefToM
     */
    public int getRsv230DefToM() {
        return rsv230DefToM__;
    }
    /**
     * <p>rsv230DefToM をセットします。
     * @param rsv230DefToM rsv230DefToM
     */
    public void setRsv230DefToM(int rsv230DefToM) {
        rsv230DefToM__ = rsv230DefToM;
    }
    /**
     * <p>rsv230Edit を取得します。
     * @return rsv230Edit
     */
    public int getRsv230Edit() {
        return rsv230Edit__;
    }
    /**
     * <p>rsv230Edit をセットします。
     * @param rsv230Edit rsv230Edit
     */
    public void setRsv230Edit(int rsv230Edit) {
        rsv230Edit__ = rsv230Edit;
    }
    /**
     * <p>rsv230HourLabel を取得します。
     * @return rsv230HourLabel
     */
    public List<LabelValueBean> getRsv230HourLabel() {
        return rsv230HourLabel__;
    }
    /**
     * <p>rsv230HourLabel をセットします。
     * @param rsv230HourLabel rsv230HourLabel
     */
    public void setRsv230HourLabel(List<LabelValueBean> rsv230HourLabel) {
        rsv230HourLabel__ = rsv230HourLabel;
    }
    /**
     * <p>rsv230MinuteLabel を取得します。
     * @return rsv230MinuteLabel
     */
    public List<LabelValueBean> getRsv230MinuteLabel() {
        return rsv230MinuteLabel__;
    }
    /**
     * <p>rsv230MinuteLabel をセットします。
     * @param rsv230MinuteLabel rsv230MinuteLabel
     */
    public void setRsv230MinuteLabel(List<LabelValueBean> rsv230MinuteLabel) {
        rsv230MinuteLabel__ = rsv230MinuteLabel;
    }
    /**
     * <p>rsv230EditFlg を取得します。
     * @return rsv230EditFlg
     */
    public boolean isRsv230EditFlg() {
        return rsv230EditFlg__;
    }
    /**
     * <p>rsv230EditFlg をセットします。
     * @param rsv230EditFlg rsv230EditFlg
     */
    public void setRsv230EditFlg(boolean rsv230EditFlg) {
        rsv230EditFlg__ = rsv230EditFlg;
    }
}
