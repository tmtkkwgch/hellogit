package jp.groupsession.v2.bbs.bbs130;

import jp.groupsession.v2.bbs.bbs010.Bbs010ParamModel;

/**
 * <br>[機  能] 掲示板 個人設定メニュー画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs130ParamModel extends Bbs010ParamModel {
    /** ショートメール通知設定制限 */
    private boolean bbs130smlKbn__ = true;
    /**
     * <p>bbs130smlKbn を取得します。
     * @return bbs130smlKbn
     */
    public boolean isBbs130smlKbn() {
        return bbs130smlKbn__;
    }
    /**
     * <p>bbs130smlKbn をセットします。
     * @param bbs130smlKbn bbs130smlKbn
     */
    public void setBbs130smlKbn(boolean bbs130smlKbn) {
        bbs130smlKbn__ = bbs130smlKbn;
    }
}