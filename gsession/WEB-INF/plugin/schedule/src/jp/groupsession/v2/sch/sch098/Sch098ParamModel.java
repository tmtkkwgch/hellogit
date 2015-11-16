package jp.groupsession.v2.sch.sch098;

import jp.groupsession.v2.sch.sch100.Sch100ParamModel;

/**
 * <br>[機  能] スケジュール グループスケジュール表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch098ParamModel extends Sch100ParamModel {

    /** デフォルト表示画面 */
    private String sch098DefDsp__ = null;

    /**
     * <p>sch098DefDsp を取得します。
     * @return sch098DefDsp
     */
    public String getSch098DefDsp() {
        return sch098DefDsp__;
    }

    /**
     * <p>sch098DefDsp をセットします。
     * @param sch098DefDsp sch098DefDsp
     */
    public void setSch098DefDsp(String sch098DefDsp) {
        sch098DefDsp__ = sch098DefDsp;
    }

}
