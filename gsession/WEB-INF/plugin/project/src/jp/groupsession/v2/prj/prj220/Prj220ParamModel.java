package jp.groupsession.v2.prj.prj220;

import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.prj080.Prj080ParamModel;
/**
 * <br>[機  能] プロジェクト管理 個人設定 スケジュール表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj220ParamModel extends Prj080ParamModel {

    /**スケジュール表示区分 0:表示する 1:表示しない */
    private int prj220SchDsp__ = GSConstProject.DSP_TODO_SHOW;

    /**
     * <p>prj220SchDsp を取得します。
     * @return prj220SchDsp
     */
    public int getPrj220SchDsp() {
        return prj220SchDsp__;
    }

    /**
     * <p>prj220SchDsp をセットします。
     * @param prj220SchDsp prj220SchDsp
     */
    public void setPrj220SchDsp(int prj220SchDsp) {
        prj220SchDsp__ = prj220SchDsp;
    }

}