package jp.groupsession.v2.fil.fil010;

import jp.groupsession.v2.fil.model.FileCabinetModel;

/**
 * キャビネット表示用モデル
 * @author JTS
 *
 */
public class FileCabinetDspModel extends FileCabinetModel {

    /** 画面表示用　キャビネット名称 */
    private String dspfcbName__;
    /** 画面表示用　備考 */
    private String dspBikoString__;
    /** 備考改行有無区分 */
    private int dspBikoBrKbn__;
    /** 使用領域文字列 */
    private String diskUsedString__;
    /** 使用領域警告有無 */
    private String diskUsedWarning__;
    /** アクセス制限アイコン */
    private String accessIconKbn__;
    /** 通知設定アイコン */
    private String callIconKbn__;

    /** RootディレクトリSID */
    private int rootDirSid__;

    /**
     * @return rootDirSid
     */
    public int getRootDirSid() {
        return rootDirSid__;
    }
    /**
     * @param rootDirSid 設定する rootDirSid
     */
    public void setRootDirSid(int rootDirSid) {
        rootDirSid__ = rootDirSid;
    }
    /**
     * @return dspBikoBrKbn
     */
    public int getDspBikoBrKbn() {
        return dspBikoBrKbn__;
    }
    /**
     * @param dspBikoBrKbn 設定する dspBikoBrKbn
     */
    public void setDspBikoBrKbn(int dspBikoBrKbn) {
        dspBikoBrKbn__ = dspBikoBrKbn;
    }
    /**
     * @return dspfcbName
     */
    public String getDspfcbName() {
        return dspfcbName__;
    }
    /**
     * @param dspfcbName 設定する dspfcbName
     */
    public void setDspfcbName(String dspfcbName) {
        dspfcbName__ = dspfcbName;
    }
    /**
     * @return dspBikoString
     */
    public String getDspBikoString() {
        return dspBikoString__;
    }
    /**
     * @param dspBikoString 設定する dspBikoString
     */
    public void setDspBikoString(String dspBikoString) {
        dspBikoString__ = dspBikoString;
    }
    /**
     * @return accessIconKbn
     */
    public String getAccessIconKbn() {
        return accessIconKbn__;
    }
    /**
     * @param accessIconKbn 設定する accessIconKbn
     */
    public void setAccessIconKbn(String accessIconKbn) {
        accessIconKbn__ = accessIconKbn;
    }
    /**
     * @return callIconKbn
     */
    public String getCallIconKbn() {
        return callIconKbn__;
    }
    /**
     * @param callIconKbn 設定する callIconKbn
     */
    public void setCallIconKbn(String callIconKbn) {
        callIconKbn__ = callIconKbn;
    }
    /**
     * @return diskUsedString
     */
    public String getDiskUsedString() {
        return diskUsedString__;
    }
    /**
     * @param diskUsedString 設定する diskUsedString
     */
    public void setDiskUsedString(String diskUsedString) {
        diskUsedString__ = diskUsedString;
    }
    /**
     * @return diskUsedWarning
     */
    public String getDiskUsedWarning() {
        return diskUsedWarning__;
    }
    /**
     * @param diskUsedWarning 設定する diskUsedWarning
     */
    public void setDiskUsedWarning(String diskUsedWarning) {
        diskUsedWarning__ = diskUsedWarning;
    }

}
