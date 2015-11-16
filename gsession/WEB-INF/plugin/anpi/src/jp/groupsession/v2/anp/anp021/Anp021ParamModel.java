package jp.groupsession.v2.anp.anp021;

import jp.groupsession.v2.anp.AbstractAnpiMobileParamModel;

/**
 * <br>[機  能] 安否状況入力画面[モバイル版]のパラメータモデル
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp021ParamModel extends AbstractAnpiMobileParamModel {

    /** 安否状況 */
    private String anp021JokyoFlg__;
    /** 現在地 */
    private String anp021PlaceFlg__;
    /** 出社状況 */
    private String anp021SyusyaFlg__;
    /** コメント */
    private String anp021Comment__;
    /** 訓練モードフラグ */
    private int anp021KnrenFlg__ = 0;
    /**
     * <p>anp021JokyoFlg を取得します。
     * @return anp021JokyoFlg
     */
    public String getAnp021JokyoFlg() {
        return anp021JokyoFlg__;
    }
    /**
     * <p>anp021JokyoFlg をセットします。
     * @param anp021JokyoFlg anp021JokyoFlg
     */
    public void setAnp021JokyoFlg(String anp021JokyoFlg) {
        anp021JokyoFlg__ = anp021JokyoFlg;
    }
    /**
     * <p>anp021PlaceFlg を取得します。
     * @return anp021PlaceFlg
     */
    public String getAnp021PlaceFlg() {
        return anp021PlaceFlg__;
    }
    /**
     * <p>anp021PlaceFlg をセットします。
     * @param anp021PlaceFlg anp021PlaceFlg
     */
    public void setAnp021PlaceFlg(String anp021PlaceFlg) {
        anp021PlaceFlg__ = anp021PlaceFlg;
    }
    /**
     * <p>anp021SyusyaFlg を取得します。
     * @return anp021SyusyaFlg
     */
    public String getAnp021SyusyaFlg() {
        return anp021SyusyaFlg__;
    }
    /**
     * <p>anp021SyusyaFlg をセットします。
     * @param anp021SyusyaFlg anp021SyusyaFlg
     */
    public void setAnp021SyusyaFlg(String anp021SyusyaFlg) {
        anp021SyusyaFlg__ = anp021SyusyaFlg;
    }
    /**
     * <p>anp021Comment を取得します。
     * @return anp021Comment
     */
    public String getAnp021Comment() {
        return anp021Comment__;
    }
    /**
     * <p>anp021Comment をセットします。
     * @param anp021Comment anp021Comment
     */
    public void setAnp021Comment(String anp021Comment) {
        anp021Comment__ = anp021Comment;
    }
    /**
     * <p>anp021KnrenFlg を取得します。
     * @return anp021KnrenFlg
     */
    public int getAnp021KnrenFlg() {
        return anp021KnrenFlg__;
    }
    /**
     * <p>anp021KnrenFlg をセットします。
     * @param anp021KnrenFlg anp021KnrenFlg
     */
    public void setAnp021KnrenFlg(int anp021KnrenFlg) {
        anp021KnrenFlg__ = anp021KnrenFlg;
    }
}