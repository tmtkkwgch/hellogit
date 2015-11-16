package jp.groupsession.v2.cir.cir100;

import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.cir010.Cir010ParamModel;

/**
 * <br>[機  能] 回覧板 管理者設定メニュー画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir100ParamModel extends Cir010ParamModel {
    /** 自動削除区分 */
    private int cir100autoDelKbn__ = GSConstCircular.AUTO_DEL_ADM;

    /**
     * <p>cir100autoDelKbn を取得します。
     * @return cir100autoDelKbn
     */
    public int getCir100autoDelKbn() {
        return cir100autoDelKbn__;
    }

    /**
     * <p>cir100autoDelKbn をセットします。
     * @param cir100autoDelKbn cir100autoDelKbn
     */
    public void setCir100autoDelKbn(int cir100autoDelKbn) {
        cir100autoDelKbn__ = cir100autoDelKbn;
    }
}