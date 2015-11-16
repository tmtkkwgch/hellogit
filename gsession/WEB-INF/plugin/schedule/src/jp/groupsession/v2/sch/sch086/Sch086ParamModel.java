package jp.groupsession.v2.sch.sch086;

import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.sch.sch080.Sch080ParamModel;

/**
 * <br>[機  能] スケジュール 管理者設定 スケジュール初期値設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 */
public class Sch086ParamModel extends Sch080ParamModel {

    /** 初期表示フラグ */
    private int sch086init__ = 0;
    /** 編集権限 選択区分 */
    private int sch086EditType__ = GSConstSchedule.SAD_INIEDIT_STYPE_USER;
    /** 編集権限 */
    private int sch086Edit__ = GSConstSchedule.EDIT_CONF_NONE;

    /** 公開区分 選択区分 */
    private int sch086PublicType__ = GSConstSchedule.SAD_INIPUBLIC_STYPE_USER;
    /** 公開区分 */
    private int sch086Public__ = GSConstSchedule.DSP_PUBLIC;

    /** 同時編集 選択区分 */
    private int sch086SameType__ = GSConstSchedule.SAD_INISAME_STYPE_USER;
    /** 同時編集 */
    private int sch086Same__ = GSConstSchedule.SAME_EDIT_ON;

    /**
     * <p>sch086Edit を取得します。
     * @return sch086Edit
     */
    public int getSch086Edit() {
        return sch086Edit__;
    }
    /**
     * <p>sch086Edit をセットします。
     * @param sch086Edit sch086Edit
     */
    public void setSch086Edit(int sch086Edit) {
        sch086Edit__ = sch086Edit;
    }
    /**
     * <p>sch086EditType を取得します。
     * @return sch086EditType
     */
    public int getSch086EditType() {
        return sch086EditType__;
    }
    /**
     * <p>sch086EditType をセットします。
     * @param sch086EditType sch086EditType
     */
    public void setSch086EditType(int sch086EditType) {
        sch086EditType__ = sch086EditType;
    }
    /**
     * <p>sch086init を取得します。
     * @return sch086init
     */
    public int getSch086init() {
        return sch086init__;
    }
    /**
     * <p>sch086init をセットします。
     * @param sch086init sch086init
     */
    public void setSch086init(int sch086init) {
        sch086init__ = sch086init;
    }
    /**
     * <p>sch086PublicType を取得します。
     * @return sch086PublicType
     */
    public int getSch086PublicType() {
        return sch086PublicType__;
    }
    /**
     * <p>sch086PublicType をセットします。
     * @param sch086PublicType sch086PublicType
     */
    public void setSch086PublicType(int sch086PublicType) {
        sch086PublicType__ = sch086PublicType;
    }
    /**
     * <p>sch086Public を取得します。
     * @return sch086Public
     */
    public int getSch086Public() {
        return sch086Public__;
    }
    /**
     * <p>sch086Public をセットします。
     * @param sch086Public sch086Public
     */
    public void setSch086Public(int sch086Public) {
        sch086Public__ = sch086Public;
    }
    /**
     * <p>sch086SameType を取得します。
     * @return sch086SameType
     */
    public int getSch086SameType() {
        return sch086SameType__;
    }
    /**
     * <p>sch086SameType をセットします。
     * @param sch086SameType sch086SameType
     */
    public void setSch086SameType(int sch086SameType) {
        sch086SameType__ = sch086SameType;
    }
    /**
     * <p>sch086Same を取得します。
     * @return sch086Same
     */
    public int getSch086Same() {
        return sch086Same__;
    }
    /**
     * <p>sch086Same をセットします。
     * @param sch086Same sch086Same
     */
    public void setSch086Same(int sch086Same) {
        sch086Same__ = sch086Same;
    }
}