package jp.groupsession.v2.sch.sch040kn;

import jp.groupsession.v2.sch.sch040.Sch040ParamModel;

/**
 * <br>[機  能] スケジュール確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch040knParamModel extends Sch040ParamModel {

    /** 開始日付文字列 */
    private String sch040knFromDate__ = null;
    /** 終了日付文字列 */
    private String sch040knToDate__ = null;
    /** 修正権限フラグ */
    private int sch040knIsEdit__;

    /**
     * @return sch040knIsEdit を戻します。
     */
    public int getSch040knIsEdit() {
        return sch040knIsEdit__;
    }
    /**
     * @param sch040knIsEdit 設定する sch040knIsEdit。
     */
    public void setSch040knIsEdit(int sch040knIsEdit) {
        sch040knIsEdit__ = sch040knIsEdit;
    }
    /**
     * @return sch040knFromDate を戻します。
     */
    public String getSch040knFromDate() {
        return sch040knFromDate__;
    }
    /**
     * @param sch040knFromDate 設定する sch040knFromDate。
     */
    public void setSch040knFromDate(String sch040knFromDate) {
        sch040knFromDate__ = sch040knFromDate;
    }
    /**
     * @return sch040knToDate を戻します。
     */
    public String getSch040knToDate() {
        return sch040knToDate__;
    }
    /**
     * @param sch040knToDate 設定する sch040knToDate。
     */
    public void setSch040knToDate(String sch040knToDate) {
        sch040knToDate__ = sch040knToDate;
    }

}
