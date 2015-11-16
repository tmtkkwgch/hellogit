package jp.groupsession.v2.fil.fil060kn;

import jp.groupsession.v2.fil.fil060.Fil060ParamModel;

/**
 * <br>[機  能] フォルダ登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil060knParamModel extends Fil060ParamModel {

    /** 備考 表示用 */
    private String fil060knBiko__ = null;
    /** 更新者名 */
    private String fil060knEditName__ = null;
    /**
     * <p>fil060knBiko を取得します。
     * @return fil060knBiko
     */
    public String getFil060knBiko() {
        return fil060knBiko__;
    }

    /**
     * <p>fil060knBiko をセットします。
     * @param fil060knBiko fil060knBiko
     */
    public void setFil060knBiko(String fil060knBiko) {
        fil060knBiko__ = fil060knBiko;
    }

    /**
     * <p>fil060knEditName を取得します。
     * @return fil060knEditName
     */
    public String getFil060knEditName() {
        return fil060knEditName__;
    }

    /**
     * <p>fil060knEditName をセットします。
     * @param fil060knEditName fil060knEditName
     */
    public void setFil060knEditName(String fil060knEditName) {
        fil060knEditName__ = fil060knEditName;
    }
}