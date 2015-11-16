package jp.groupsession.v2.adr.adr220kn;

import jp.groupsession.v2.adr.adr220.Adr220ParamModel;

/**
 * <br>[機  能] アドレス帳 役職登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr220knParamModel extends Adr220ParamModel {

    //表示項目
    /** 備考(表示用) */
    private String bikou__;

    /**
     * <p>bikou を取得します。
     * @return bikou
     */
    public String getBikou() {
        return bikou__;
    }
    /**
     * <p>bikou をセットします。
     * @param bikou bikou
     */
    public void setBikou(String bikou) {
        bikou__ = bikou;
    }
}