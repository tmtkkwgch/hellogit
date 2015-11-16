package jp.groupsession.v2.zsk.zsk130kn;

import jp.groupsession.v2.zsk.zsk130.Zsk130ParamModel;

/**
 * <br>[機  能] 在席管理 個人設定 座席表メンバー表示設定確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk130knParamModel extends Zsk130ParamModel {

    /** ソートキー1 表示用 */
    private String zsk130knSortKey1Name__;
    /** ソートキー2 表示用 */
    private String zsk130knSortKey2Name__;

    /**
     * <p>zsk130knSortKey1Name を取得します。
     * @return zsk130knSortKey1Name
     */
    public String getZsk130knSortKey1Name() {
        return zsk130knSortKey1Name__;
    }
    /**
     * <p>zsk130knSortKey1Name をセットします。
     * @param zsk130knSortKey1Name zsk130knSortKey1Name
     */
    public void setZsk130knSortKey1Name(String zsk130knSortKey1Name) {
        zsk130knSortKey1Name__ = zsk130knSortKey1Name;
    }
    /**
     * <p>zsk130knSortKey2Name を取得します。
     * @return zsk130knSortKey2Name
     */
    public String getZsk130knSortKey2Name() {
        return zsk130knSortKey2Name__;
    }
    /**
     * <p>zsk130knSortKey2Name をセットします。
     * @param zsk130knSortKey2Name zsk130knSortKey2Name
     */
    public void setZsk130knSortKey2Name(String zsk130knSortKey2Name) {
        zsk130knSortKey2Name__ = zsk130knSortKey2Name;
    }
}