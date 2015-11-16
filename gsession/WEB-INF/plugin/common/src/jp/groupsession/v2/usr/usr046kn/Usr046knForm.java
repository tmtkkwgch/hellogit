package jp.groupsession.v2.usr.usr046kn;

import jp.groupsession.v2.usr.usr046.Usr046Form;

/**
 * <br>[機  能] ユーザ情報 ラベル登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Usr046knForm extends Usr046Form {

    /** カテゴリ名 */
    private String usr046knCatName__;
    /** カテゴリ名(削除時保存用) */
    private String usr046knDelCatName__;

    /** 備考(表示用) */
    private String bikou__;

    /**
     * @return usr046knCatName
     */
    public String getUsr046knCatName() {
        return usr046knCatName__;
    }

    /**
     * @param usr046knCatName セットする usr046knCatName
     */
    public void setUsr046knCatName(String usr046knCatName) {
        usr046knCatName__ = usr046knCatName;
    }

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
     * <p>usr046knDelCatName を取得します。
     * @return usr046knDelCatName
     */
    public String getUsr046knDelCatName() {
        return usr046knDelCatName__;
    }

    /**
     * <p>usr046knDelCatName をセットします。
     * @param usr046knDelCatName usr046knDelCatName
     */
    public void setUsr046knDelCatName(String usr046knDelCatName) {
        usr046knDelCatName__ = usr046knDelCatName;
    }

}
