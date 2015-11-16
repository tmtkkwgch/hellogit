package jp.groupsession.v2.sch.sch087;

import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.sch.sch080.Sch080ParamModel;

/**
 * <br>[機  能] スケジュール 管理者設定 スケジュール重複登録設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 */
public class Sch087ParamModel extends Sch080ParamModel {

    /** 初期表示フラグ */
    private int sch087init__ = 0;
    /** 重複登録 選択区分 */
    private int sch087RepeatKbnType__ = GSConstSchedule.SAD_REPEAT_STYPE_USER;
    /** 重複登録 */
    private int sch087RepeatKbn__ = GSConstSchedule.SCH_REPEAT_KBN_OK;
    /** 自身による登録を許可 */
    private int sch087RepeatMyKbn__ = GSConstSchedule.SCH_REPEAT_MY_KBN_NG;
    /**
     * <p>sch087init を取得します。
     * @return sch087init
     */
    public int getSch087init() {
        return sch087init__;
    }
    /**
     * <p>sch087init をセットします。
     * @param sch087init sch087init
     */
    public void setSch087init(int sch087init) {
        sch087init__ = sch087init;
    }
    /**
     * <p>sch087RepeatKbn を取得します。
     * @return sch087RepeatKbn
     */
    public int getSch087RepeatKbn() {
        return sch087RepeatKbn__;
    }
    /**
     * <p>sch087RepeatKbn をセットします。
     * @param sch087RepeatKbn sch087RepeatKbn
     */
    public void setSch087RepeatKbn(int sch087RepeatKbn) {
        sch087RepeatKbn__ = sch087RepeatKbn;
    }
    /**
     * <p>sch087RepeatKbnType を取得します。
     * @return sch087RepeatKbnType
     */
    public int getSch087RepeatKbnType() {
        return sch087RepeatKbnType__;
    }
    /**
     * <p>sch087RepeatKbnType をセットします。
     * @param sch087RepeatKbnType sch087RepeatKbnType
     */
    public void setSch087RepeatKbnType(int sch087RepeatKbnType) {
        sch087RepeatKbnType__ = sch087RepeatKbnType;
    }
    /**
     * <p>sch087RepeatMyKbn を取得します。
     * @return sch087RepeatMyKbn
     */
    public int getSch087RepeatMyKbn() {
        return sch087RepeatMyKbn__;
    }
    /**
     * <p>sch087RepeatMyKbn をセットします。
     * @param sch087RepeatMyKbn sch087RepeatMyKbn
     */
    public void setSch087RepeatMyKbn(int sch087RepeatMyKbn) {
        sch087RepeatMyKbn__ = sch087RepeatMyKbn;
    }
}