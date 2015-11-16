package jp.groupsession.v2.prj.prj240;

import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.prj080.Prj080ParamModel;
/**
 * <br>[機  能] プロジェクト管理 個人設定 TODO登録画面初期値設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj240ParamModel extends Prj080ParamModel {

    /**TODOO入力区分 0:簡易入力 1:詳細入力 */
    private int prj240DspKbn__ = GSConstProject.DSP_TODO_EASY;

    /**
     * <p>prj240DspKbn を取得します。
     * @return prj240DspKbn
     */
    public int getPrj240DspKbn() {
        return prj240DspKbn__;
    }

    /**
     * <p>prj240DspKbn をセットします。
     * @param prj240DspKbn prj240DspKbn
     */
    public void setPrj240DspKbn(int prj240DspKbn) {
        prj240DspKbn__ = prj240DspKbn;
    }


}