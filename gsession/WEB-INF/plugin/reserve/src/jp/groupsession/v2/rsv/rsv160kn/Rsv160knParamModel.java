package jp.groupsession.v2.rsv.rsv160kn;

import jp.groupsession.v2.rsv.rsv160.Rsv160ParamModel;

/**
 * <br>[機  能] 施設予約 個人設定 日間表示時間帯設定確認画面のフォームク
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv160knParamModel extends Rsv160ParamModel {

    /** 表示テキスト From */
    private String rsv160knDspFrom__ = "";
    /** 表示テキスト To */
    private String rsv160knDspTo__ = "";

    /**
     * <p>rsv160knDspFrom を取得します。
     * @return rsv160knDspFrom
     */
    public String getRsv160knDspFrom() {
        return rsv160knDspFrom__;
    }
    /**
     * <p>rsv160knDspFrom をセットします。
     * @param rsv160knDspFrom rsv160knDspFrom
     */
    public void setRsv160knDspFrom(String rsv160knDspFrom) {
        rsv160knDspFrom__ = rsv160knDspFrom;
    }
    /**
     * <p>rsv160knDspTo を取得します。
     * @return rsv160knDspTo
     */
    public String getRsv160knDspTo() {
        return rsv160knDspTo__;
    }
    /**
     * <p>rsv160knDspTo をセットします。
     * @param rsv160knDspTo rsv160knDspTo
     */
    public void setRsv160knDspTo(String rsv160knDspTo) {
        rsv160knDspTo__ = rsv160knDspTo;
    }
}