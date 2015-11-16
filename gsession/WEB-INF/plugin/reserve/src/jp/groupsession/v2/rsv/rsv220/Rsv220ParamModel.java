package jp.groupsession.v2.rsv.rsv220;

import jp.groupsession.v2.rsv.rsv040.Rsv040ParamModel;

/**
 * <br>[機  能] 施設予約 管理者設定 施設予約基本設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv220ParamModel extends Rsv040ParamModel {

    /** 施設予約単位分設定 */
    private String rsv220HourDiv__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Rsv220ParamModel() {
    }

    /**
     * <p>rsv220HourDiv を取得します。
     * @return rsv220HourDiv
     */
    public String getRsv220HourDiv() {
        return rsv220HourDiv__;
    }

    /**
     * <p>rsv220HourDiv をセットします。
     * @param rsv220HourDiv rsv220HourDiv
     */
    public void setRsv220HourDiv(String rsv220HourDiv) {
        rsv220HourDiv__ = rsv220HourDiv;
    }
}