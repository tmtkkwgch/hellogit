package jp.groupsession.v2.man.model.base;

import java.io.Serializable;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;

/**
 * <p>BBS_LOG_COUNT_ADEL Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class BbsLogCountAdelModel implements Serializable {

    /** BLD_DEL_KBN mapping */
    private int bldDelKbn__;
    /** BLD_DEL_YEAR mapping */
    private int bldDelYear__;
    /** BLD_DEL_MONTH mapping */
    private int bldDelMonth__;
    /** BLD_AUID mapping */
    private int bldAuid__;
    /** BLD_ADATE mapping */
    private UDate bldAdate__;
    /** BLD_EUID mapping */
    private int bldEuid__;
    /** BLD_EDATE mapping */
    private UDate bldEdate__;

    /**
     * <p>Default Constructor
     */
    public BbsLogCountAdelModel() {
    }

    /**
     * <p>get BLD_DEL_KBN value
     * @return BLD_DEL_KBN value
     */
    public int getBldDelKbn() {
        return bldDelKbn__;
    }

    /**
     * <p>set BLD_DEL_KBN value
     * @param bldDelKbn BLD_DEL_KBN value
     */
    public void setBldDelKbn(int bldDelKbn) {
        bldDelKbn__ = bldDelKbn;
    }

    /**
     * <p>get BLD_DEL_YEAR value
     * @return BLD_DEL_YEAR value
     */
    public int getBldDelYear() {
        return bldDelYear__;
    }

    /**
     * <p>set BLD_DEL_YEAR value
     * @param bldDelYear BLD_DEL_YEAR value
     */
    public void setBldDelYear(int bldDelYear) {
        bldDelYear__ = bldDelYear;
    }

    /**
     * <p>get BLD_DEL_MONTH value
     * @return BLD_DEL_MONTH value
     */
    public int getBldDelMonth() {
        return bldDelMonth__;
    }

    /**
     * <p>set BLD_DEL_MONTH value
     * @param bldDelMonth BLD_DEL_MONTH value
     */
    public void setBldDelMonth(int bldDelMonth) {
        bldDelMonth__ = bldDelMonth;
    }

    /**
     * <p>get BLD_AUID value
     * @return BLD_AUID value
     */
    public int getBldAuid() {
        return bldAuid__;
    }

    /**
     * <p>set BLD_AUID value
     * @param bldAuid BLD_AUID value
     */
    public void setBldAuid(int bldAuid) {
        bldAuid__ = bldAuid;
    }

    /**
     * <p>get BLD_ADATE value
     * @return BLD_ADATE value
     */
    public UDate getBldAdate() {
        return bldAdate__;
    }

    /**
     * <p>set BLD_ADATE value
     * @param bldAdate BLD_ADATE value
     */
    public void setBldAdate(UDate bldAdate) {
        bldAdate__ = bldAdate;
    }

    /**
     * <p>get BLD_EUID value
     * @return BLD_EUID value
     */
    public int getBldEuid() {
        return bldEuid__;
    }

    /**
     * <p>set BLD_EUID value
     * @param bldEuid BLD_EUID value
     */
    public void setBldEuid(int bldEuid) {
        bldEuid__ = bldEuid;
    }

    /**
     * <p>get BLD_EDATE value
     * @return BLD_EDATE value
     */
    public UDate getBldEdate() {
        return bldEdate__;
    }

    /**
     * <p>set BLD_EDATE value
     * @param bldEdate BLD_EDATE value
     */
    public void setBldEdate(UDate bldEdate) {
        bldEdate__ = bldEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(bldDelKbn__);
        buf.append(",");
        buf.append(bldDelYear__);
        buf.append(",");
        buf.append(bldDelMonth__);
        buf.append(",");
        buf.append(bldAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(bldAdate__, ""));
        buf.append(",");
        buf.append(bldEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(bldEdate__, ""));
        return buf.toString();
    }

}
