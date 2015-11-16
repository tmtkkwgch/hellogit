package jp.groupsession.v2.man.man024kn;

import jp.groupsession.v2.man.man023.Man023Form;

/**
 * <br>[機  能] メイン 管理者設定 テンプレートから追加確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Man024knForm extends Man023Form {

    /** 反映可能件数 */
    private int countReflect__ = -1;
    /** 休日反映対象テンプレートSID */
    private int[] man024knOKSid__ = null;
    /** 上書き登録区分 */
    private String[] man024knAsterisks__ = null;
    /** 表示日付の値 */
    private String[] man024knDateValue__ = null;
    /** 表示休日名称 */
    private String[] man024knNameValue__ = null;
    /**
     * @return countReflect__ を戻します。
     */
    public int getCountReflect() {
        return countReflect__;
    }

    /**
     * @param countReflect 設定する countReflect__。
     */
    public void setCountReflect(int countReflect) {
        countReflect__ = countReflect;
    }

    /**
     * @return getMan024knOKSid__ を戻します。
     */
    public int[] getMan024knOKSid() {
        return man024knOKSid__;
    }

    /**
     * @param man024knOKSid 設定する man024knOKSid__。
     */
    public void setMan024knOKSid(int[] man024knOKSid) {
        this.man024knOKSid__ = man024knOKSid;
    }

    /**
     * @return man024knAsterisks を戻します。
     */
    public String[] getMan024knAsterisks() {
        return man024knAsterisks__;
    }

    /**
     * @param man024knAsterisks 設定する man024knAsterisks__。
     */
    public void setMan024knAsterisks(String[] man024knAsterisks) {
        man024knAsterisks__ = man024knAsterisks;
    }

    /**
     * @return man024knDateValue__ を戻します。
     */
    public String[] getMan024knDateValue() {
        return man024knDateValue__;
    }

    /**
     * @param man024knDateValue 設定する man024knDateValue__。
     */
    public void setMan024knDateValue(String[] man024knDateValue) {
        this.man024knDateValue__ = man024knDateValue;
    }

    /**
     * @return man024knNameValue__ を戻します。
     */
    public String[] getMan024knNameValue() {
        return man024knNameValue__;
    }

    /**
     * @param man024knNameValue 設定する man024knNameValue__。
     */
    public void setMan024knNameValue(String[] man024knNameValue) {
        this.man024knNameValue__ = man024knNameValue;
    }

}
