package jp.groupsession.v2.fil.model;

import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.model.AbstractModel;

/**
 * <p>ファイル詳細検索に関する一覧表示用モデル
 *
 * @author JTS
 */
public class FileDspModel extends AbstractModel {
    //FILE_DIRECTORY
    /** ディレクトリSID */
    private int fdrSid__;
    /** バージョン */
    private int fdrVersion__;
    /** キャビネットSID */
    private int fcbSid__;
    /** 親ディレクトリSID */
    private int fdrParentSid__;
    /** ディレクトリ区分 */
    private int fdrKbn__;
    /** バージョン管理区分 */
    private int fdrVerKbn__;
    /** ディレクトリ階層 */
    private int fdrLevel__;
    /** ディレクトリ名称 */
    private String fdrName__;
    /** 備考 */
    private String fdrBiko__;
    /** 状態区分 */
    private int fdrJtkbn__;
    /** 編集SID */
    private String fdrEuid__;
    /** 編集日時 */
    private String fdrEdate__;

    //FILE_FILE_BIN
    /** バイナリSID */
    private Long binSid__ = new Long(0);
    /** ファイルの拡張子 */
    private String fflExt__;
    /** ファイルサイズ */
    private String fflFileSize__;
    /** ロック区分 */
    private int fflLockKbn__;
    /** ロックユーザSID */
    private int fflLockUser__;

    //FILE_CALL_CONF
    /** ユーザーSID */
    private int usrSid__;
    /** ユーザー 姓 */
    private String usrSei__;
    /** ユーザー 名 */
    private String usrMei__;
    /** 状態区分 */
    private int usrJKbn__;

    /** 更新通知 */
    private int callOn__ = GSConstCommon.NUM_INIT;

    /**
     * <p>usrMei を取得します。
     * @return usrMei
     */
    public String getUsrMei() {
        return usrMei__;
    }

    /**
     * <p>usrMei をセットします。
     * @param usrMei usrMei
     */
    public void setUsrMei(String usrMei) {
        usrMei__ = usrMei;
    }

    /**
     * <p>usrSei を取得します。
     * @return usrSei
     */
    public String getUsrSei() {
        return usrSei__;
    }

    /**
     * <p>usrSei をセットします。
     * @param usrSei usrSei
     */
    public void setUsrSei(String usrSei) {
        usrSei__ = usrSei;
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
     * <p>callOn を取得します。
     * @return callOn
     */
    public int getCallOn() {
        return callOn__;
    }

    /**
     * <p>callOn をセットします。
     * @param callOn callOn
     */
    public void setCallOn(int callOn) {
        callOn__ = callOn;
    }
    /**
     * <p>fdrEdate を取得します。
     * @return fdrEdate
     */
    public String getFdrEdate() {
        return fdrEdate__;
    }

    /**
     * <p>fdrEdate をセットします。
     * @param fdrEdate fdrEdate
     */
    public void setFdrEdate(String fdrEdate) {
        fdrEdate__ = fdrEdate;
    }

    /**
     * <p>fdrEuid を取得します。
     * @return fdrEuid
     */
    public String getFdrEuid() {
        return fdrEuid__;
    }

    /**
     * <p>fdrEuid をセットします。
     * @param fdrEuid fdrEuid
     */
    public void setFdrEuid(String fdrEuid) {
        fdrEuid__ = fdrEuid;
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
     * <p>fcbSid を取得します。
     * @return fcbSid
     */
    public int getFcbSid() {
        return fcbSid__;
    }

    /**
     * <p>fcbSid をセットします。
     * @param fcbSid fcbSid
     */
    public void setFcbSid(int fcbSid) {
        fcbSid__ = fcbSid;
    }

    /**
     * <p>fdrBiko を取得します。
     * @return fdrBiko
     */
    public String getFdrBiko() {
        return fdrBiko__;
    }

    /**
     * <p>fdrBiko をセットします。
     * @param fdrBiko fdrBiko
     */
    public void setFdrBiko(String fdrBiko) {
        fdrBiko__ = fdrBiko;
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
     * <p>fdrKbn を取得します。
     * @return fdrKbn
     */
    public int getFdrKbn() {
        return fdrKbn__;
    }

    /**
     * <p>fdrKbn をセットします。
     * @param fdrKbn fdrKbn
     */
    public void setFdrKbn(int fdrKbn) {
        fdrKbn__ = fdrKbn;
    }

    /**
     * <p>fdrLevel を取得します。
     * @return fdrLevel
     */
    public int getFdrLevel() {
        return fdrLevel__;
    }

    /**
     * <p>fdrLevel をセットします。
     * @param fdrLevel fdrLevel
     */
    public void setFdrLevel(int fdrLevel) {
        fdrLevel__ = fdrLevel;
    }

    /**
     * <p>fdrName を取得します。
     * @return fdrName
     */
    public String getFdrName() {
        return fdrName__;
    }

    /**
     * <p>fdrName をセットします。
     * @param fdrName fdrName
     */
    public void setFdrName(String fdrName) {
        fdrName__ = fdrName;
    }

    /**
     * <p>fdrParentSid を取得します。
     * @return fdrParentSid
     */
    public int getFdrParentSid() {
        return fdrParentSid__;
    }

    /**
     * <p>fdrParentSid をセットします。
     * @param fdrParentSid fdrParentSid
     */
    public void setFdrParentSid(int fdrParentSid) {
        fdrParentSid__ = fdrParentSid;
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
     * <p>fdrVerKbn を取得します。
     * @return fdrVerKbn
     */
    public int getFdrVerKbn() {
        return fdrVerKbn__;
    }

    /**
     * <p>fdrVerKbn をセットします。
     * @param fdrVerKbn fdrVerKbn
     */
    public void setFdrVerKbn(int fdrVerKbn) {
        fdrVerKbn__ = fdrVerKbn;
    }

    /**
     * <p>fdrVersion を取得します。
     * @return fdrVersion
     */
    public int getFdrVersion() {
        return fdrVersion__;
    }

    /**
     * <p>fdrVersion をセットします。
     * @param fdrVersion fdrVersion
     */
    public void setFdrVersion(int fdrVersion) {
        fdrVersion__ = fdrVersion;
    }

    /**
     * <p>fflExt を取得します。
     * @return fflExt
     */
    public String getFflExt() {
        return fflExt__;
    }

    /**
     * <p>fflExt をセットします。
     * @param fflExt fflExt
     */
    public void setFflExt(String fflExt) {
        fflExt__ = fflExt;
    }

    /**
     * <p>fflFileSize を取得します。
     * @return fflFileSize
     */
    public String getFflFileSize() {
        return fflFileSize__;
    }

    /**
     * <p>fflFileSize をセットします。
     * @param fflFileSize fflFileSize
     */
    public void setFflFileSize(String fflFileSize) {
        fflFileSize__ = fflFileSize;
    }

    /**
     * <p>fflLockKbn を取得します。
     * @return fflLockKbn
     */
    public int getFflLockKbn() {
        return fflLockKbn__;
    }

    /**
     * <p>fflLockKbn をセットします。
     * @param fflLockKbn fflLockKbn
     */
    public void setFflLockKbn(int fflLockKbn) {
        fflLockKbn__ = fflLockKbn;
    }

    /**
     * <p>fflLockUser を取得します。
     * @return fflLockUser
     */
    public int getFflLockUser() {
        return fflLockUser__;
    }

    /**
     * <p>fflLockUser をセットします。
     * @param fflLockUser fflLockUser
     */
    public void setFflLockUser(int fflLockUser) {
        fflLockUser__ = fflLockUser;
    }

    /**
     * @return usrJKbn
     */
    public int getUsrJKbn() {
        return usrJKbn__;
    }

    /**
     * @param usrJKbn セットする usrJKbn
     */
    public void setUsrJKbn(int usrJKbn) {
        usrJKbn__ = usrJKbn;
    }
}
