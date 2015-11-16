package jp.groupsession.v2.rng.model;

import java.io.Serializable;

/**
 * <p>RNG_BIN Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RngBinModel implements Serializable {

    /** RNG_SID mapping */
    private int rngSid__;
    /** BIN_SID mapping */
    private Long binSid__ = new Long(0);
    /** USR_SID mapping */
    private int usrSid__;

    /**
     * <p>Default Constructor
     */
    public RngBinModel() {
    }

    /**
     * <p>get RNG_SID value
     * @return RNG_SID value
     */
    public int getRngSid() {
        return rngSid__;
    }

    /**
     * <p>set RNG_SID value
     * @param rngSid RNG_SID value
     */
    public void setRngSid(int rngSid) {
        rngSid__ = rngSid;
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
     * <p>get USR_SID value
     * @return USR_SID value
     */
    public int getUsrSid() {
        return usrSid__;
    }

    /**
     * <p>set USR_SID value
     * @param usrSid USR_SID value
     */
    public void setUsrSid(int usrSid) {
        usrSid__ = usrSid;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuilder buf = new StringBuilder();
        buf.append(rngSid__);
        buf.append(",");
        buf.append(binSid__);
        buf.append(",");
        buf.append(usrSid__);
        return buf.toString();
    }

}
