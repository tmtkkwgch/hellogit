package jp.groupsession.v2.rsv.rsv140;

import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv030.Rsv030ParamModel;

/**
 * <br>[機  能] 施設予約 個人設定メニュー画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv140ParamModel extends Rsv030ParamModel {
    /** 日間表示時間帯設定可能か */
    private boolean rsv140canEditDtm__ = false;
    /** ショートメール通知設定区分 */
    private int rsv140SmailSendKbn__ = GSConstReserve.RAC_SMAIL_SEND_KBN_USER;

    /**
     * <p>rsv140canEditDtm を取得します。
     * @return rsv140canEditDtm
     */
    public boolean isRsv140canEditDtm() {
        return rsv140canEditDtm__;
    }

    /**
     * <p>rsv140canEditDtm をセットします。
     * @param rsv140canEditDtm rsv140canEditDtm
     */
    public void setRsv140canEditDtm(boolean rsv140canEditDtm) {
        rsv140canEditDtm__ = rsv140canEditDtm;
    }

    /**
     * <p>rsv140SmailSendKbn を取得します。
     * @return rsv140SmailSendKbn
     */
    public int getRsv140SmailSendKbn() {
        return rsv140SmailSendKbn__;
    }

    /**
     * <p>rsv140SmailSendKbn をセットします。
     * @param rsv140SmailSendKbn rsv140SmailSendKbn
     */
    public void setRsv140SmailSendKbn(int rsv140SmailSendKbn) {
        rsv140SmailSendKbn__ = rsv140SmailSendKbn;
    }
}