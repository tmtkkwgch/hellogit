package jp.groupsession.v2.sch.main;

import jp.groupsession.v2.struts.AbstractGsForm;

/**
 * <br>[機  能] スケジュール(メイン画面表示用)のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class SchMainForm extends AbstractGsForm {

    //共通項目
    /** 管理者権限有無 1:権限有り 2:権限無し*/
    private int adminKbn__;

    //週間スケジュール
    /** 週間スケジュール 表示日付 */
    private String schWeekDate__;
    /** 週間スケジュール情報モデル */
    private ScheduleWeekModel schWeekMdl__;
    //スケジュール共通
    /** 選択日付 */
    private String schSelectDate__;
    /** 選択ユーザSID */
    private String schSelectUsrSid__;
    /** 選択ユーザ区分 */
    private String schSelectUsrKbn__;
    /** 選択スケジュールSID */
    private String schSelectSchSid__;
    /** 動作区分*/
    private int moveKbn__ = 0;
    /** スケジュール画面URL */
    private String  schTopUrl__;

    /**
     * @return schTopUrl__ を戻します。
     */
    public String getSchTopUrl() {
        return schTopUrl__;
    }
    /**
     * @param schTopUrl 設定する schTopUrl__。
     */
    public void setSchTopUrl(String schTopUrl) {
        schTopUrl__ = schTopUrl;
    }
    /**
     * @return moveKbn を戻します。
     */
    public int getMoveKbn() {
        return moveKbn__;
    }
    /**
     * @param moveKbn 設定する moveKbn。
     */
    public void setMoveKbn(int moveKbn) {
        moveKbn__ = moveKbn;
    }
    /**
     * @return schSelectDate を戻します。
     */
    public String getSchSelectDate() {
        return schSelectDate__;
    }
    /**
     * @param schSelectDate 設定する schSelectDate。
     */
    public void setSchSelectDate(String schSelectDate) {
        schSelectDate__ = schSelectDate;
    }
    /**
     * @return schSelectSchSid を戻します。
     */
    public String getSchSelectSchSid() {
        return schSelectSchSid__;
    }
    /**
     * @param schSelectSchSid 設定する schSelectSchSid。
     */
    public void setSchSelectSchSid(String schSelectSchSid) {
        schSelectSchSid__ = schSelectSchSid;
    }
    /**
     * @return schSelectUsrKbn を戻します。
     */
    public String getSchSelectUsrKbn() {
        return schSelectUsrKbn__;
    }
    /**
     * @param schSelectUsrKbn 設定する schSelectUsrKbn。
     */
    public void setSchSelectUsrKbn(String schSelectUsrKbn) {
        schSelectUsrKbn__ = schSelectUsrKbn;
    }
    /**
     * @return schSelectUsrSid を戻します。
     */
    public String getSchSelectUsrSid() {
        return schSelectUsrSid__;
    }
    /**
     * @param schSelectUsrSid 設定する schSelectUsrSid。
     */
    public void setSchSelectUsrSid(String schSelectUsrSid) {
        schSelectUsrSid__ = schSelectUsrSid;
    }
    /**
     * @return adminKbn を戻します。
     */
    public int getAdminKbn() {
        return adminKbn__;
    }
    /**
     * @param adminKbn 設定する adminKbn。
     */
    public void setAdminKbn(int adminKbn) {
        adminKbn__ = adminKbn;
    }
    /**
     * @return schWeekMdl を戻します。
     */
    public ScheduleWeekModel getSchWeekMdl() {
        return schWeekMdl__;
    }
    /**
     * @param schWeekMdl 設定する schWeekMdl。
     */
    public void setSchWeekMdl(ScheduleWeekModel schWeekMdl) {
        schWeekMdl__ = schWeekMdl;
    }
    /**
     * @return schWeekDate を戻します。
     */
    public String getSchWeekDate() {
        return schWeekDate__;
    }
    /**
     * @param schWeekDate 設定する schWeekDate。
     */
    public void setSchWeekDate(String schWeekDate) {
        schWeekDate__ = schWeekDate;
    }
}
