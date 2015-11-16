package jp.groupsession.v2.rsv.rsv210kn;

import jp.groupsession.v2.rsv.rsv210.Rsv210Form;

/**
 * <br>[機  能] 施設予約 施設予約一括登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv210knForm extends Rsv210Form {

    /** 時間(開始) */
    private String rsv210knTimeFr__ = null;
    /** 時間(終了) */
    private String rsv210knTimeTo__ = null;
    /** 内容(表示用) */
    private String rsv210knNaiyo__ = null;

    /**
     * <p>rsv210knNaiyo__ を取得します。
     * @return rsv210knNaiyo
     */
    public String getRsv210knNaiyo() {
        return rsv210knNaiyo__;
    }
    /**
     * <p>rsv210knNaiyo__ をセットします。
     * @param rsv210knNaiyo rsv210knNaiyo__
     */
    public void setRsv210knNaiyo(String rsv210knNaiyo) {
        rsv210knNaiyo__ = rsv210knNaiyo;
    }
    /**
     * <p>rsv210knTimeFr__ を取得します。
     * @return rsv210knTimeFr
     */
    public String getRsv210knTimeFr() {
        return rsv210knTimeFr__;
    }
    /**
     * <p>rsv210knTimeFr__ をセットします。
     * @param rsv210knTimeFr rsv210knTimeFr__
     */
    public void setRsv210knTimeFr(String rsv210knTimeFr) {
        rsv210knTimeFr__ = rsv210knTimeFr;
    }
    /**
     * <p>rsv210knTimeTo__ を取得します。
     * @return rsv210knTimeTo
     */
    public String getRsv210knTimeTo() {
        return rsv210knTimeTo__;
    }
    /**
     * <p>rsv210knTimeTo__ をセットします。
     * @param rsv210knTimeTo rsv210knTimeTo__
     */
    public void setRsv210knTimeTo(String rsv210knTimeTo) {
        rsv210knTimeTo__ = rsv210knTimeTo;
    }
}