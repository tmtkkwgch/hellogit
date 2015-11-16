package jp.groupsession.v2.prj.model;

import java.io.Serializable;

import jp.co.sjts.util.date.UDate;

/**
 * <p>PRJ_DIRECTORY Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class PrjDirectoryModel implements Serializable {

    /** PRJ_SID mapping */
    private int prjSid__;
    /** PDR_SID mapping */
    private int pdrSid__;
    /** PDR_PARENT_SID mapping */
    private int pdrParentSid__;
    /** PDR_KBN mapping */
    private int pdrKbn__;
    /** PDR_LEVEL mapping */
    private int pdrLevel__;
    /** PDR_NAME mapping */
    private String pdrName__;
    /** PDR_NAIYO mapping */
    private String pdrNaiyo__;
    /** PDR_BIKO mapping */
    private String pdrBiko__;
    /** BIN_SID mapping */
    private Long binSid__ = new Long(0);
    /** PDR_AUID mapping */
    private int pdrAuid__;
    /** PDR_ADATE mapping */
    private UDate pdrAdate__;
    /** PDR_EUID mapping */
    private int pdrEuid__;
    /** PDR_EDATE mapping */
    private UDate pdrEdate__;

    /**
     * <p>Default Constructor
     */
    public PrjDirectoryModel() {
    }

    /**
     * <p>get PRJ_SID value
     * @return PRJ_SID value
     */
    public int getPrjSid() {
        return prjSid__;
    }
    /**
     * <p>set PRJ_SID value
     * @param prjSid PRJ_SID value
     */
    public void setPrjSid(int prjSid) {
        prjSid__ = prjSid;
    }
    /**
     * <p>get PDR_SID value
     * @return PDR_SID value
     */
    public int getPdrSid() {
        return pdrSid__;
    }
    /**
     * <p>set PDR_SID value
     * @param pdrSid PDR_SID value
     */
    public void setPdrSid(int pdrSid) {
        pdrSid__ = pdrSid;
    }
    /**
     * <p>get PDR_PARENT_SID value
     * @return PDR_PARENT_SID value
     */
    public int getPdrParentSid() {
        return pdrParentSid__;
    }
    /**
     * <p>set PDR_PARENT_SID value
     * @param pdrParentSid PDR_PARENT_SID value
     */
    public void setPdrParentSid(int pdrParentSid) {
        pdrParentSid__ = pdrParentSid;
    }
    /**
     * <p>get PDR_KBN value
     * @return PDR_KBN value
     */
    public int getPdrKbn() {
        return pdrKbn__;
    }
    /**
     * <p>set PDR_KBN value
     * @param pdrKbn PDR_KBN value
     */
    public void setPdrKbn(int pdrKbn) {
        pdrKbn__ = pdrKbn;
    }
    /**
     * <p>get PDR_LEVEL value
     * @return PDR_LEVEL value
     */
    public int getPdrLevel() {
        return pdrLevel__;
    }
    /**
     * <p>set PDR_LEVEL value
     * @param pdrLevel PDR_LEVEL value
     */
    public void setPdrLevel(int pdrLevel) {
        pdrLevel__ = pdrLevel;
    }
    /**
     * <p>get PDR_NAME value
     * @return PDR_NAME value
     */
    public String getPdrName() {
        return pdrName__;
    }
    /**
     * <p>set PDR_NAME value
     * @param pdrName PDR_NAME value
     */
    public void setPdrName(String pdrName) {
        pdrName__ = pdrName;
    }
    /**
     * <p>get PDR_NAIYO value
     * @return PDR_NAIYO value
     */
    public String getPdrNaiyo() {
        return pdrNaiyo__;
    }
    /**
     * <p>set PDR_NAIYO value
     * @param pdrNaiyo PDR_NAIYO value
     */
    public void setPdrNaiyo(String pdrNaiyo) {
        pdrNaiyo__ = pdrNaiyo;
    }
    /**
     * <p>get PDR_BIKO value
     * @return PDR_BIKO value
     */
    public String getPdrBiko() {
        return pdrBiko__;
    }
    /**
     * <p>set PDR_BIKO value
     * @param pdrBiko PDR_BIKO value
     */
    public void setPdrBiko(String pdrBiko) {
        pdrBiko__ = pdrBiko;
    }
    /**
     * <p>get BIN_SID value
     * @return BIN_SID value
     */
    public Long getBinSid() {
        return binSid__;
    }
    /**
     * <p>set BIN_SID value
     * @param binSid BIN_SID value
     */
    public void setBinSid(Long binSid) {
        binSid__ = binSid;
    }
    /**
     * <p>get PDR_AUID value
     * @return PDR_AUID value
     */
    public int getPdrAuid() {
        return pdrAuid__;
    }
    /**
     * <p>set PDR_AUID value
     * @param pdrAuid PDR_AUID value
     */
    public void setPdrAuid(int pdrAuid) {
        pdrAuid__ = pdrAuid;
    }
    /**
     * <p>get PDR_ADATE value
     * @return PDR_ADATE value
     */
    public UDate getPdrAdate() {
        return pdrAdate__;
    }
    /**
     * <p>set PDR_ADATE value
     * @param pdrAdate PDR_ADATE value
     */
    public void setPdrAdate(UDate pdrAdate) {
        pdrAdate__ = pdrAdate;
    }
    /**
     * <p>get PDR_EUID value
     * @return PDR_EUID value
     */
    public int getPdrEuid() {
        return pdrEuid__;
    }
    /**
     * <p>set PDR_EUID value
     * @param pdrEuid PDR_EUID value
     */
    public void setPdrEuid(int pdrEuid) {
        pdrEuid__ = pdrEuid;
    }
    /**
     * <p>get PDR_EDATE value
     * @return PDR_EDATE value
     */
    public UDate getPdrEdate() {
        return pdrEdate__;
    }
    /**
     * <p>set PDR_EDATE value
     * @param pdrEdate PDR_EDATE value
     */
    public void setPdrEdate(UDate pdrEdate) {
        pdrEdate__ = pdrEdate;
    }
}