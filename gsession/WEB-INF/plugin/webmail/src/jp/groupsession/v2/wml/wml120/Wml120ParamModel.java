package jp.groupsession.v2.wml.wml120;

import jp.groupsession.v2.wml.wml110.Wml110ParamModel;

/**
 * <br>[機  能] WEBメール ラベル登録画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml120ParamModel extends Wml110ParamModel {
    /** ラベル名 */
    private String wml120LabelName__ = null;
    /** 初期表示区分 */
    private int wml120initKbn__ = 0;

    /**
     * <p>wml120LabelName を取得します。
     * @return wml120LabelName
     */
    public String getWml120LabelName() {
        return wml120LabelName__;
    }
    /**
     * <p>wml120LabelName をセットします。
     * @param wml120LabelName wml120LabelName
     */
    public void setWml120LabelName(String wml120LabelName) {
        wml120LabelName__ = wml120LabelName;
    }
    /**
     * <p>wml120initKbn を取得します。
     * @return wml120initKbn
     */
    public int getWml120initKbn() {
        return wml120initKbn__;
    }
    /**
     * <p>wml120initKbn をセットします。
     * @param wml120initKbn wml120initKbn
     */
    public void setWml120initKbn(int wml120initKbn) {
        wml120initKbn__ = wml120initKbn;
    }
}