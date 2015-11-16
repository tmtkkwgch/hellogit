package jp.groupsession.v2.rng.rng140;

import jp.groupsession.v2.rng.rng060.Rng060ParamModel;

/**
 * <br>[機  能] 稟議 テンプレートカテゴリ登録画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng140ParamModel extends Rng060ParamModel {
    //入力項目
    /** カテゴリ名 */
    private String rng140CatName__ = "";

    /** カテゴリSID */
    private int rng140CatSid__ = 0;
    /** 状態区分 0=登録  1=編集 */
    private int rng140ProcMode__ = 0;
    /** 遷移元フラグ */
    private int rng140SeniFlg__ = 0;
    /**
     * @return rng140CatName
     */
    public String getRng140CatName() {
        return rng140CatName__;
    }

    /**
     * @param rng140CatName セットする rng140CatName
     */
    public void setRng140CatName(String rng140CatName) {
        rng140CatName__ = rng140CatName;
    }

    /**
     * @return rng140CatSid
     */
    public int getRng140CatSid() {
        return rng140CatSid__;
    }

    /**
     * @param rng140CatSid セットする rng140CatSid
     */
    public void setRng140CatSid(int rng140CatSid) {
        rng140CatSid__ = rng140CatSid;
    }

    /**
     * @return rng140ProcMode
     */
    public int getRng140ProcMode() {
        return rng140ProcMode__;
    }

    /**
     * @param rng140ProcMode 設定する rng140ProcMode
     */
    public void setRng140ProcMode(int rng140ProcMode) {
        rng140ProcMode__ = rng140ProcMode;
    }

    /**
     * @return rng140SeniFlg
     */
    public int getRng140SeniFlg() {
        return rng140SeniFlg__;
    }

    /**
     * @param rng140SeniFlg 設定する rng140SeniFlg
     */
    public void setRng140SeniFlg(int rng140SeniFlg) {
        rng140SeniFlg__ = rng140SeniFlg;
    }
}