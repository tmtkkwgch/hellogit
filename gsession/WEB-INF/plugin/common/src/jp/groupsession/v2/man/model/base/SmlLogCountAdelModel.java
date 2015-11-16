package jp.groupsession.v2.man.model.base;

import java.io.Serializable;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;

/**
 * <p>SML_LOG_COUNT_ADEL Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class SmlLogCountAdelModel implements Serializable {

    /** SLD_DEL_KBN mapping */
    private int sldDelKbn__;
    /** SLD_DEL_YEAR mapping */
    private int sldDelYear__;
    /** SLD_DEL_MONTH mapping */
    private int sldDelMonth__;
    /** SLD_AUID mapping */
    private int sldAuid__;
    /** SLD_ADATE mapping */
    private UDate sldAdate__;
    /** SLD_EUID mapping */
    private int sldEuid__;
    /** SLD_EDATE mapping */
    private UDate sldEdate__;

    /**
     * <p>Default Constructor
     */
    public SmlLogCountAdelModel() {
    }

    /**
     * <p>get SLD_DEL_KBN value
     * @return SLD_DEL_KBN value
     */
    public int getSldDelKbn() {
        return sldDelKbn__;
    }

    /**
     * <p>set SLD_DEL_KBN value
     * @param sldDelKbn SLD_DEL_KBN value
     */
    public void setSldDelKbn(int sldDelKbn) {
        sldDelKbn__ = sldDelKbn;
    }

    /**
     * <p>get SLD_DEL_YEAR value
     * @return SLD_DEL_YEAR value
     */
    public int getSldDelYear() {
        return sldDelYear__;
    }

    /**
     * <p>set SLD_DEL_YEAR value
     * @param sldDelYear SLD_DEL_YEAR value
     */
    public void setSldDelYear(int sldDelYear) {
        sldDelYear__ = sldDelYear;
    }

    /**
     * <p>get SLD_DEL_MONTH value
     * @return SLD_DEL_MONTH value
     */
    public int getSldDelMonth() {
        return sldDelMonth__;
    }

    /**
     * <p>set SLD_DEL_MONTH value
     * @param sldDelMonth SLD_DEL_MONTH value
     */
    public void setSldDelMonth(int sldDelMonth) {
        sldDelMonth__ = sldDelMonth;
    }

    /**
     * <p>get SLD_AUID value
     * @return SLD_AUID value
     */
    public int getSldAuid() {
        return sldAuid__;
    }

    /**
     * <p>set SLD_AUID value
     * @param sldAuid SLD_AUID value
     */
    public void setSldAuid(int sldAuid) {
        sldAuid__ = sldAuid;
    }

    /**
     * <p>get SLD_ADATE value
     * @return SLD_ADATE value
     */
    public UDate getSldAdate() {
        return sldAdate__;
    }

    /**
     * <p>set SLD_ADATE value
     * @param sldAdate SLD_ADATE value
     */
    public void setSldAdate(UDate sldAdate) {
        sldAdate__ = sldAdate;
    }

    /**
     * <p>get SLD_EUID value
     * @return SLD_EUID value
     */
    public int getSldEuid() {
        return sldEuid__;
    }

    /**
     * <p>set SLD_EUID value
     * @param sldEuid SLD_EUID value
     */
    public void setSldEuid(int sldEuid) {
        sldEuid__ = sldEuid;
    }

    /**
     * <p>get SLD_EDATE value
     * @return SLD_EDATE value
     */
    public UDate getSldEdate() {
        return sldEdate__;
    }

    /**
     * <p>set SLD_EDATE value
     * @param sldEdate SLD_EDATE value
     */
    public void setSldEdate(UDate sldEdate) {
        sldEdate__ = sldEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(sldDelKbn__);
        buf.append(",");
        buf.append(sldDelYear__);
        buf.append(",");
        buf.append(sldDelMonth__);
        buf.append(",");
        buf.append(sldAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(sldAdate__, ""));
        buf.append(",");
        buf.append(sldEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(sldEdate__, ""));
        return buf.toString();
    }

}
