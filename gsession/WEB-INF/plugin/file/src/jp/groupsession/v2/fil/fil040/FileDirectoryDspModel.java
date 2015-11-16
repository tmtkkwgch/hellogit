package jp.groupsession.v2.fil.fil040;

import jp.groupsession.v2.fil.model.FileDirectoryModel;

/**
 * ディレクトリ表示用モデル
 * @author JTS
 *
 */
public class FileDirectoryDspModel extends FileDirectoryModel {

    /** バイナリーSID */
    private Long fileBinSid__ = new Long(-1);
    /** 画面表示用　ファイルサイズ */
    private String fileSize__;
    /** 画面表示用　更新通知設定 */
    private String callKbn__;
    /** 画面表示用　更新日時 */
    private String edateString__;
    /** 画面表示用　更新者 */
    private String upUsrName__;
    /** 画面表示用　更新者 状態区分 */
    private int upUsrJkbn__;
    /** 画面表示用　ロック区分 */
    private String lockKbn__;
    /** 画面表示用　ロックユーザ名 */
    private String lockUsrName__;
    /** 画面表示用　ロック日付 */
    private String lockDate__;
    /** 画面表示用　アクセス区分 */
    private int accessKbn__;

    /** ロックユーザSID */
    private int lockUsrSid__;
    /** ファイルサイズ(byte) */
    private long fflFileSize__;

    /**
     * @return fileBinSid
     */
    public Long getFileBinSid() {
        return fileBinSid__;
    }
    /**
     * @param fileBinSid 設定する fileBinSid
     */
    public void setFileBinSid(Long fileBinSid) {
        this.fileBinSid__ = fileBinSid;
    }
    /**
     * @return callKbn
     */
    public String getCallKbn() {
        return callKbn__;
    }
    /**
     * @param callKbn 設定する callKbn
     */
    public void setCallKbn(String callKbn) {
        callKbn__ = callKbn;
    }
    /**
     * @return edateString
     */
    public String getEdateString() {
        return edateString__;
    }
    /**
     * @param edateString 設定する edateString
     */
    public void setEdateString(String edateString) {
        edateString__ = edateString;
    }
    /**
     * @return fileSize
     */
    public String getFileSize() {
        return fileSize__;
    }
    /**
     * @param fileSize 設定する fileSize
     */
    public void setFileSize(String fileSize) {
        fileSize__ = fileSize;
    }
    /**
     * @return lockKbn
     */
    public String getLockKbn() {
        return lockKbn__;
    }
    /**
     * @param lockKbn 設定する lockKbn
     */
    public void setLockKbn(String lockKbn) {
        lockKbn__ = lockKbn;
    }
    /**
     * @return lockUsrName
     */
    public String getLockUsrName() {
        return lockUsrName__;
    }
    /**
     * @param lockUsrName 設定する lockUsrName
     */
    public void setLockUsrName(String lockUsrName) {
        lockUsrName__ = lockUsrName;
    }
    /**
     * @return upUsrName
     */
    public String getUpUsrName() {
        return upUsrName__;
    }
    /**
     * @param upUsrName 設定する upUsrName
     */
    public void setUpUsrName(String upUsrName) {
        upUsrName__ = upUsrName;
    }
    /**
     * <p>fflFileSize を取得します。
     * @return fflFileSize
     */
    public long getFflFileSize() {
        return fflFileSize__;
    }
    /**
     * <p>fflFileSize をセットします。
     * @param fflFileSize fflFileSize
     */
    public void setFflFileSize(long fflFileSize) {
        fflFileSize__ = fflFileSize;
    }
    /**
     * <p>lockUsrSid を取得します。
     * @return lockUsrSid
     */
    public int getLockUsrSid() {
        return lockUsrSid__;
    }
    /**
     * <p>lockUsrSid をセットします。
     * @param lockUsrSid lockUsrSid
     */
    public void setLockUsrSid(int lockUsrSid) {
        lockUsrSid__ = lockUsrSid;
    }
    /**
     * <p>lockDate を取得します。
     * @return lockDate
     */
    public String getLockDate() {
        return lockDate__;
    }
    /**
     * <p>lockDate をセットします。
     * @param lockDate lockDate
     */
    public void setLockDate(String lockDate) {
        lockDate__ = lockDate;
    }
    /**
     * <p>upUsrJkbn を取得します。
     * @return upUsrJkbn
     */
    public int getUpUsrJkbn() {
        return upUsrJkbn__;
    }
    /**
     * <p>upUsrJkbn をセットします。
     * @param upUsrJkbn upUsrJkbn
     */
    public void setUpUsrJkbn(int upUsrJkbn) {
        upUsrJkbn__ = upUsrJkbn;
    }
    /**
     * @return accessKbn
     */
    public int getAccessKbn() {
        return accessKbn__;
    }
    /**
     * @param accessKbn セットする accessKbn
     */
    public void setAccessKbn(int accessKbn) {
        accessKbn__ = accessKbn;
    }
}
