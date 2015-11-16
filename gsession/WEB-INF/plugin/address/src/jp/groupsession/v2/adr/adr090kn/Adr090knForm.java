package jp.groupsession.v2.adr.adr090kn;

import jp.groupsession.v2.adr.adr090.Adr090Form;

/**
 * <br>[機  能] アドレス帳 業種登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr090knForm extends Adr090Form {

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
