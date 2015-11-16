package jp.groupsession.v2.rng.model;

import java.io.Serializable;

/**
 * <p>RNG_UCONF Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RngUconfModel implements Serializable {

    /** USR_SID mapping */
    private int usrSid__;
    /** RUR_SML_NTF mapping */
    private int rurSmlNtf__;
    /** RUR_VIEW_CNT mapping */
    private int rurViewCnt__;

    /**
     * <p>Default Constructor
     */
    public RngUconfModel() {
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
     * <p>get RUR_SML_NTF value
     * @return RUR_SML_NTF value
     */
    public int getRurSmlNtf() {
        return rurSmlNtf__;
    }

    /**
     * <p>set RUR_SML_NTF value
     * @param rurSmlNtf RUR_SML_NTF value
     */
    public void setRurSmlNtf(int rurSmlNtf) {
        rurSmlNtf__ = rurSmlNtf;
    }

    /**
     * <p>get RUR_VIEW_CNT value
     * @return RUR_VIEW_CNT value
     */
    public int getRurViewCnt() {
        return rurViewCnt__;
    }

    /**
     * <p>set RUR_VIEW_CNT value
     * @param rurViewCnt RUR_VIEW_CNT value
     */
    public void setRurViewCnt(int rurViewCnt) {
        rurViewCnt__ = rurViewCnt;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuilder buf = new StringBuilder();
        buf.append(usrSid__);
        buf.append(",");
        buf.append(rurSmlNtf__);
        buf.append(",");
        buf.append(rurViewCnt__);
        return buf.toString();
    }

}
