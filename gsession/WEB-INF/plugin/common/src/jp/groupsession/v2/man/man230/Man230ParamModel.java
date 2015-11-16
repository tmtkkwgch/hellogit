package jp.groupsession.v2.man.man230;

import jp.groupsession.v2.cmn.model.AbstractParamModel;

/**
 * <br>[機  能] メイン 管理者設定 システム情報画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man230ParamModel extends AbstractParamModel {
    /** バージョン(DB) */
    private String man230GSVersionDB__ = null;
    /** OS */
    private String man230Os__ = null;
    /** J2EEコンテナ */
    private String man230J2ee__ = null;
    /** Java */
    private String man230Java__ = null;
    /** メモリ使用 */
    private String man230MemoryUse__ = null;
    /** メモリ使用割合 */
    private String man230MemoryUsePer__ = null;
    /** DB区分 */
    private String man230DbKbn__ = null;
    /** メモリ最大 */
    private String man230MemoryMax__ = null;
    /** 現在の空きディスク容量 */
    private String man230FreeDSpace__ = null;

    /** 開発用 */
    private String man230ConnectionCount__ = null;

    /**
     * <p>man230MemoryUsePer を取得します。
     * @return man230MemoryUsePer
     */
    public String getMan230MemoryUsePer() {
        return man230MemoryUsePer__;
    }
    /**
     * <p>man230MemoryUsePer をセットします。
     * @param man230MemoryUsePer man230MemoryUsePer
     */
    public void setMan230MemoryUsePer(String man230MemoryUsePer) {
        man230MemoryUsePer__ = man230MemoryUsePer;
    }
    /**
     * <p>man230FreeDSpace を取得します。
     * @return man230FreeDSpace
     */
    public String getMan230FreeDSpace() {
        return man230FreeDSpace__;
    }
    /**
     * <p>man230FreeDSpace をセットします。
     * @param man230FreeDSpace man230FreeDSpace
     */
    public void setMan230FreeDSpace(String man230FreeDSpace) {
        man230FreeDSpace__ = man230FreeDSpace;
    }
    /**
     * <p>man230J2ee を取得します。
     * @return man230J2ee
     */
    public String getMan230J2ee() {
        return man230J2ee__;
    }
    /**
     * <p>man230J2ee をセットします。
     * @param man230J2ee man230J2ee
     */
    public void setMan230J2ee(String man230J2ee) {
        man230J2ee__ = man230J2ee;
    }
    /**
     * <p>man230Java を取得します。
     * @return man230Java
     */
    public String getMan230Java() {
        return man230Java__;
    }
    /**
     * <p>man230Java をセットします。
     * @param man230Java man230Java
     */
    public void setMan230Java(String man230Java) {
        man230Java__ = man230Java;
    }
    /**
     * <p>man230MemoryMax を取得します。
     * @return man230MemoryMax
     */
    public String getMan230MemoryMax() {
        return man230MemoryMax__;
    }
    /**
     * <p>man230MemoryMax をセットします。
     * @param man230MemoryMax man230MemoryMax
     */
    public void setMan230MemoryMax(String man230MemoryMax) {
        man230MemoryMax__ = man230MemoryMax;
    }
    /**
     * <p>man230MemoryUse を取得します。
     * @return man230MemoryUse
     */
    public String getMan230MemoryUse() {
        return man230MemoryUse__;
    }
    /**
     * <p>man230MemoryUse をセットします。
     * @param man230MemoryUse man230MemoryUse
     */
    public void setMan230MemoryUse(String man230MemoryUse) {
        man230MemoryUse__ = man230MemoryUse;
    }
    /**
     * <p>man230Os を取得します。
     * @return man230Os
     */
    public String getMan230Os() {
        return man230Os__;
    }
    /**
     * <p>man230Os をセットします。
     * @param man230Os man230Os
     */
    public void setMan230Os(String man230Os) {
        man230Os__ = man230Os;
    }
    /**
     * <p>man230ConnectionCount を取得します。
     * @return man230ConnectionCount
     */
    public String getMan230ConnectionCount() {
        return man230ConnectionCount__;
    }
    /**
     * <p>man230ConnectionCount をセットします。
     * @param man230ConnectionCount man230ConnectionCount
     */
    public void setMan230ConnectionCount(String man230ConnectionCount) {
        man230ConnectionCount__ = man230ConnectionCount;
    }
    /**
     * <p>man230GSVersionDB を取得します。
     * @return man230GSVersionDB
     */
    public String getMan230GSVersionDB() {
        return man230GSVersionDB__;
    }
    /**
     * <p>man230GSVersionDB をセットします。
     * @param man230GSVersionDB man230GSVersionDB
     */
    public void setMan230GSVersionDB(String man230GSVersionDB) {
        man230GSVersionDB__ = man230GSVersionDB;
    }
    /**
     * <p>man230DbKbn を取得します。
     * @return man230DbKbn
     */
    public String getMan230DbKbn() {
        return man230DbKbn__;
    }
    /**
     * <p>man230DbKbn をセットします。
     * @param man230DbKbn man230DbKbn
     */
    public void setMan230DbKbn(String man230DbKbn) {
        man230DbKbn__ = man230DbKbn;
    }
}