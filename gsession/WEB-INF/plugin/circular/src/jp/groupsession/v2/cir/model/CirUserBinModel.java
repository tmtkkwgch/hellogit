package jp.groupsession.v2.cir.model;

import java.io.Serializable;

/**
 * <p>CIR_USER_BIN Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class CirUserBinModel implements Serializable {

    /** CIF_SID mapping */
    private int cifSid__;
    /** CAC_SID mapping */
    private int cacSid__;
    /** CUB_BIN_SID mapping */
    private Long cubBinSid__ = new Long(0);

    /**
     * <p>Default Constructor
     */
    public CirUserBinModel() {
    }

    /**
     * <p>get CIF_SID value
     * @return CIF_SID value
     */
    public int getCifSid() {
        return cifSid__;
    }

    /**
     * <p>set CIF_SID value
     * @param cifSid CIF_SID value
     */
    public void setCifSid(int cifSid) {
        cifSid__ = cifSid;
    }

    /**
     * <p>get CAC_SID value
     * @return CAC_SID value
     */
    public int getCacSid() {
        return cacSid__;
    }

    /**
     * <p>set CAC_SID value
     * @param cacSid CAC_SID value
     */
    public void setCacSid(int cacSid) {
        cacSid__ = cacSid;
    }

    /**
     * <p>get CUB_BIN_SID value
     * @return CUB_BIN_SID value
     */
    public Long getCubBinSid() {
        return cubBinSid__;
    }

    /**
     * <p>set CUB_BIN_SID value
     * @param cubBinSid CUB_BIN_SID value
     */
    public void setCubBinSid(Long cubBinSid) {
        cubBinSid__ = cubBinSid;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(cifSid__);
        buf.append(",");
        buf.append(cacSid__);
        buf.append(",");
        buf.append(cubBinSid__);
        return buf.toString();
    }

}
