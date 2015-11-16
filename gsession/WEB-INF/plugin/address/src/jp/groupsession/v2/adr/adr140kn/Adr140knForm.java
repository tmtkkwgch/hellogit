package jp.groupsession.v2.adr.adr140kn;

import jp.groupsession.v2.adr.adr140.Adr140Form;

/**
 * <br>[機  能] アドレス帳 ラベル登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr140knForm extends Adr140Form {

    //表示項目
    /** 備考(表示用) */
    private String bikou__;
    /** カテゴリ名 */
    private String catName__;
    /** カテゴリ名(削除時保存用) */
    private String adr140knDelCatName__;

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

    /**
     * @return catName
     */
    public String getCatName() {
        return catName__;
    }

    /**
     * @param catName セットする catName
     */
    public void setCatName(String catName) {
        catName__ = catName;
    }

    /**
     * <p>adr140knDelCatName を取得します。
     * @return adr140knDelCatName
     */
    public String getAdr140knDelCatName() {
        return adr140knDelCatName__;
    }

    /**
     * <p>adr140knDelCatName をセットします。
     * @param adr140knDelCatName adr140knDelCatName
     */
    public void setAdr140knDelCatName(String adr140knDelCatName) {
        adr140knDelCatName__ = adr140knDelCatName;
    }

}
