package jp.groupsession.v2.bbs.bbs052;

import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs010.Bbs010Form;


/**
 * <br>[機  能] 掲示板 ショートメール通知設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs052Form extends Bbs010Form {

    /** ショートメール通知 */
    private int bbs052smlNtf__ = GSConstBulletin.BBS_SMAIL_TSUUCHI;

    /**
     * <p>bbs052smlNtf を取得します。
     * @return bbs052smlNtf
     */
    public int getBbs052smlNtf() {
        return bbs052smlNtf__;
    }

    /**
     * <p>bbs052smlNtf をセットします。
     * @param bbs052smlNtf bbs052smlNtf
     */
    public void setBbs052smlNtf(int bbs052smlNtf) {
        bbs052smlNtf__ = bbs052smlNtf;
    }

}
