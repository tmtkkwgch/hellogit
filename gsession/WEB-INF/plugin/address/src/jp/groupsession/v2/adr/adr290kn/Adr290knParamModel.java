package jp.groupsession.v2.adr.adr290kn;

import jp.groupsession.v2.adr.adr290.Adr290ParamModel;

/**
 * <br>[機  能] アドレス帳 カテゴリ登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr290knParamModel extends Adr290ParamModel {

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
