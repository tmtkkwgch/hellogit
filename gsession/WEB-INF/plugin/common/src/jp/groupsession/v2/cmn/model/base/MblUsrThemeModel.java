package jp.groupsession.v2.cmn.model.base;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.NullDefault;
import java.io.Serializable;

/**
 * <p>MBL_USR_THEME Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class MblUsrThemeModel implements Serializable {

    /** USR_SID mapping */
    private int usrSid__;
    /** MBT_SID mapping */
    private int mbtSid__;
    /** MUT_AUID mapping */
    private int mutAuid__;
    /** MUT_ADATE mapping */
    private UDate mutAdate__;
    /** MUT_EUID mapping */
    private int mutEuid__;
    /** MUT_EDATE mapping */
    private UDate mutEdate__;

    /**
     * <p>Default Constructor
     */
    public MblUsrThemeModel() {
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
     * <p>get MBT_SID value
     * @return MBT_SID value
     */
    public int getMbtSid() {
        return mbtSid__;
    }

    /**
     * <p>set MBT_SID value
     * @param mbtSid MBT_SID value
     */
    public void setMbtSid(int mbtSid) {
        mbtSid__ = mbtSid;
    }

    /**
     * <p>get MUT_AUID value
     * @return MUT_AUID value
     */
    public int getMutAuid() {
        return mutAuid__;
    }

    /**
     * <p>set MUT_AUID value
     * @param mutAuid MUT_AUID value
     */
    public void setMutAuid(int mutAuid) {
        mutAuid__ = mutAuid;
    }

    /**
     * <p>get MUT_ADATE value
     * @return MUT_ADATE value
     */
    public UDate getMutAdate() {
        return mutAdate__;
    }

    /**
     * <p>set MUT_ADATE value
     * @param mutAdate MUT_ADATE value
     */
    public void setMutAdate(UDate mutAdate) {
        mutAdate__ = mutAdate;
    }

    /**
     * <p>get MUT_EUID value
     * @return MUT_EUID value
     */
    public int getMutEuid() {
        return mutEuid__;
    }

    /**
     * <p>set MUT_EUID value
     * @param mutEuid MUT_EUID value
     */
    public void setMutEuid(int mutEuid) {
        mutEuid__ = mutEuid;
    }

    /**
     * <p>get MUT_EDATE value
     * @return MUT_EDATE value
     */
    public UDate getMutEdate() {
        return mutEdate__;
    }

    /**
     * <p>set MUT_EDATE value
     * @param mutEdate MUT_EDATE value
     */
    public void setMutEdate(UDate mutEdate) {
        mutEdate__ = mutEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(usrSid__);
        buf.append(",");
        buf.append(mbtSid__);
        buf.append(",");
        buf.append(mutAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(mutAdate__, ""));
        buf.append(",");
        buf.append(mutEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(mutEdate__, ""));
        return buf.toString();
    }

}
