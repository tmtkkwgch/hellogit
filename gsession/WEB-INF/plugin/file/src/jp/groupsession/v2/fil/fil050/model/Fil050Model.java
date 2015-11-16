package jp.groupsession.v2.fil.fil050.model;

import java.io.Serializable;

/**
 * <br>[機  能] フォルダ詳細画面のモデルクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil050Model implements Serializable {

    /** ユーザSID */
    private int usrSid__;
    /** ユーザ状態区分 */
    private int usrJkbn__;
    /** ユーザ姓名 */
    private String usrSeiMei__;
    /** ディレクトリSID */
    private int fdrSid__;
    /** バージョン */
    private int ffrVersion__;
    /** ファイル名 */
    private String ffrName__;
    /** 履歴区分 */
    private int ffrKbn__;
    /** 更新者ID */
    private int ffrEuid__;
    /** 更新日時 */
    private String ffrEdate__;
    /** 添付SID */
    private Long binSid__ = new Long(0);
    /** 添付ファイル保存名 */
    private String saveFileName__;
    /** 復旧ボタン表示フラグ */
    private boolean repairBtnDspFlg__ = true;
    /** ディレクトリ状態区分 */
    private int fdrJtkbn__;
    /** 更新コメント */
    private String ffrUpCmt__;

    /**
     * <p>saveFileName を取得します。
     * @return saveFileName
     */
    public String getSaveFileName() {
        return saveFileName__;
    }
    /**
     * <p>saveFileName をセットします。
     * @param saveFileName saveFileName
     */
    public void setSaveFileName(String saveFileName) {
        saveFileName__ = saveFileName;
    }
    /**
     * <p>binSid を取得します。
     * @return binSid
     */
    public Long getBinSid() {
        return binSid__;
    }
    /**
     * <p>binSid をセットします。
     * @param binSid binSid
     */
    public void setBinSid(Long binSid) {
        binSid__ = binSid;
    }
    /**
     * <p>fdrSid を取得します。
     * @return fdrSid
     */
    public int getFdrSid() {
        return fdrSid__;
    }
    /**
     * <p>fdrSid をセットします。
     * @param fdrSid fdrSid
     */
    public void setFdrSid(int fdrSid) {
        fdrSid__ = fdrSid;
    }
    /**
     * <p>ffrEdate を取得します。
     * @return ffrEdate
     */
    public String getFfrEdate() {
        return ffrEdate__;
    }
    /**
     * <p>ffrEdate をセットします。
     * @param ffrEdate ffrEdate
     */
    public void setFfrEdate(String ffrEdate) {
        ffrEdate__ = ffrEdate;
    }
    /**
     * <p>ffrEuid を取得します。
     * @return ffrEuid
     */
    public int getFfrEuid() {
        return ffrEuid__;
    }
    /**
     * <p>ffrEuid をセットします。
     * @param ffrEuid ffrEuid
     */
    public void setFfrEuid(int ffrEuid) {
        ffrEuid__ = ffrEuid;
    }
    /**
     * <p>ffrKbn を取得します。
     * @return ffrKbn
     */
    public int getFfrKbn() {
        return ffrKbn__;
    }
    /**
     * <p>ffrKbn をセットします。
     * @param ffrKbn ffrKbn
     */
    public void setFfrKbn(int ffrKbn) {
        ffrKbn__ = ffrKbn;
    }
    /**
     * <p>ffrName を取得します。
     * @return ffrName
     */
    public String getFfrName() {
        return ffrName__;
    }
    /**
     * <p>ffrName をセットします。
     * @param ffrName ffrName
     */
    public void setFfrName(String ffrName) {
        ffrName__ = ffrName;
    }
    /**
     * <p>ffrVersion を取得します。
     * @return ffrVersion
     */
    public int getFfrVersion() {
        return ffrVersion__;
    }
    /**
     * <p>ffrVersion をセットします。
     * @param ffrVersion ffrVersion
     */
    public void setFfrVersion(int ffrVersion) {
        ffrVersion__ = ffrVersion;
    }
    /**
     * <p>usrJkbn を取得します。
     * @return usrJkbn
     */
    public int getUsrJkbn() {
        return usrJkbn__;
    }
    /**
     * <p>usrJkbn をセットします。
     * @param usrJkbn usrJkbn
     */
    public void setUsrJkbn(int usrJkbn) {
        usrJkbn__ = usrJkbn;
    }
    /**
     * <p>usrSeiMei を取得します。
     * @return usrSeiMei
     */
    public String getUsrSeiMei() {
        return usrSeiMei__;
    }
    /**
     * <p>usrSeiMei をセットします。
     * @param usrSeiMei usrSeiMei
     */
    public void setUsrSeiMei(String usrSeiMei) {
        usrSeiMei__ = usrSeiMei;
    }
    /**
     * <p>usrSid を取得します。
     * @return usrSid
     */
    public int getUsrSid() {
        return usrSid__;
    }
    /**
     * <p>usrSid をセットします。
     * @param usrSid usrSid
     */
    public void setUsrSid(int usrSid) {
        usrSid__ = usrSid;
    }
    /**
     * <p>repairBtnDspFlg を取得します。
     * @return repairBtnDspFlg
     */
    public boolean isRepairBtnDspFlg() {
        return repairBtnDspFlg__;
    }
    /**
     * <p>repairBtnDspFlg をセットします。
     * @param repairBtnDspFlg repairBtnDspFlg
     */
    public void setRepairBtnDspFlg(boolean repairBtnDspFlg) {
        repairBtnDspFlg__ = repairBtnDspFlg;
    }
    /**
     * <p>fdrJtkbn を取得します。
     * @return fdrJtkbn
     */
    public int getFdrJtkbn() {
        return fdrJtkbn__;
    }
    /**
     * <p>fdrJtkbn をセットします。
     * @param fdrJtkbn fdrJtkbn
     */
    public void setFdrJtkbn(int fdrJtkbn) {
        fdrJtkbn__ = fdrJtkbn;
    }
    /**
     * <p>ffrUpCmt を取得します。
     * @return ffrUpCmt
     */
    public String getFfrUpCmt() {
        return ffrUpCmt__;
    }
    /**
     * <p>ffrUpCmt をセットします。
     * @param ffrUpCmt ffrUpCmt
     */
    public void setFfrUpCmt(String ffrUpCmt) {
        ffrUpCmt__ = ffrUpCmt;
    }

}
