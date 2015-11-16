package jp.groupsession.v2.man.model.base;

import java.io.Serializable;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;

/**
 * <p>CIR_LOG_COUNT_ADEL Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class CirLogCountAdelModel implements Serializable {

    /** CLD_DEL_KBN mapping */
    private int cldDelKbn__;
    /** CLD_DEL_YEAR mapping */
    private int cldDelYear__;
    /** CLD_DEL_MONTH mapping */
    private int cldDelMonth__;
    /** CLD_AUID mapping */
    private int cldAuid__;
    /** CLD_ADATE mapping */
    private UDate cldAdate__;
    /** CLD_EUID mapping */
    private int cldEuid__;
    /** CLD_EDATE mapping */
    private UDate cldEdate__;

    /**
     * <p>Default Constructor
     */
    public CirLogCountAdelModel() {
    }

    /**
     * <p>get CLD_DEL_KBN value
     * @return CLD_DEL_KBN value
     */
    public int getCldDelKbn() {
        return cldDelKbn__;
    }

    /**
     * <p>set CLD_DEL_KBN value
     * @param cldDelKbn CLD_DEL_KBN value
     */
    public void setCldDelKbn(int cldDelKbn) {
        cldDelKbn__ = cldDelKbn;
    }

    /**
     * <p>get CLD_DEL_YEAR value
     * @return CLD_DEL_YEAR value
     */
    public int getCldDelYear() {
        return cldDelYear__;
    }

    /**
     * <p>set CLD_DEL_YEAR value
     * @param cldDelYear CLD_DEL_YEAR value
     */
    public void setCldDelYear(int cldDelYear) {
        cldDelYear__ = cldDelYear;
    }

    /**
     * <p>get CLD_DEL_MONTH value
     * @return CLD_DEL_MONTH value
     */
    public int getCldDelMonth() {
        return cldDelMonth__;
    }

    /**
     * <p>set CLD_DEL_MONTH value
     * @param cldDelMonth CLD_DEL_MONTH value
     */
    public void setCldDelMonth(int cldDelMonth) {
        cldDelMonth__ = cldDelMonth;
    }

    /**
     * <p>get CLD_AUID value
     * @return CLD_AUID value
     */
    public int getCldAuid() {
        return cldAuid__;
    }

    /**
     * <p>set CLD_AUID value
     * @param cldAuid CLD_AUID value
     */
    public void setCldAuid(int cldAuid) {
        cldAuid__ = cldAuid;
    }

    /**
     * <p>get CLD_ADATE value
     * @return CLD_ADATE value
     */
    public UDate getCldAdate() {
        return cldAdate__;
    }

    /**
     * <p>set CLD_ADATE value
     * @param cldAdate CLD_ADATE value
     */
    public void setCldAdate(UDate cldAdate) {
        cldAdate__ = cldAdate;
    }

    /**
     * <p>get CLD_EUID value
     * @return CLD_EUID value
     */
    public int getCldEuid() {
        return cldEuid__;
    }

    /**
     * <p>set CLD_EUID value
     * @param cldEuid CLD_EUID value
     */
    public void setCldEuid(int cldEuid) {
        cldEuid__ = cldEuid;
    }

    /**
     * <p>get CLD_EDATE value
     * @return CLD_EDATE value
     */
    public UDate getCldEdate() {
        return cldEdate__;
    }

    /**
     * <p>set CLD_EDATE value
     * @param cldEdate CLD_EDATE value
     */
    public void setCldEdate(UDate cldEdate) {
        cldEdate__ = cldEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(cldDelKbn__);
        buf.append(",");
        buf.append(cldDelYear__);
        buf.append(",");
        buf.append(cldDelMonth__);
        buf.append(",");
        buf.append(cldAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(cldAdate__, ""));
        buf.append(",");
        buf.append(cldEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(cldEdate__, ""));
        return buf.toString();
    }

}
