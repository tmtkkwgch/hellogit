package jp.groupsession.v2.zsk.zsk100kn;

import jp.groupsession.v2.zsk.zsk100.Zsk100ParamModel;

/**
 * <br>[機  能] 在席管理 個人設定 メイン画面メンバー表示設定確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Zsk100knParamModel extends Zsk100ParamModel {

    /** 表示グループ名 */
    private String zsk100GrpName__ = null;

    /**
     * <p>zsk100GrpName を取得します。
     * @return zsk100GrpName
     */
    public String getZsk100GrpName() {
        return zsk100GrpName__;
    }

    /**
     * <p>zsk100GrpName をセットします。
     * @param zsk100GrpName zsk100GrpName
     */
    public void setZsk100GrpName(String zsk100GrpName) {
        zsk100GrpName__ = zsk100GrpName;
    }
}
