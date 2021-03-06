package jp.groupsession.v2.tcd.tcd030;

import jp.groupsession.v2.tcd.tcd010.Tcd010ParamModel;

/**
 * <br>[機  能] タイムカード 管理者設定メニュー画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd030ParamModel extends Tcd010ParamModel {
    /** 管理者設定メニューの表示レベルを設定 */
    private String menuLevel__;

    /**
     * <p>menuLevel を取得します。
     * @return menuLevel
     */
    public String getMenuLevel() {
        return menuLevel__;
    }

    /**
     * <p>menuLevel をセットします。
     * @param menuLevel menuLevel
     */
    public void setMenuLevel(String menuLevel) {
        menuLevel__ = menuLevel;
    }
}