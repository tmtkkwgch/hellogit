package jp.groupsession.v2.man.model.base;

import java.io.Serializable;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;

/**
 * <p>FILE_LOG_ADEL Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class FileLogAdelModel implements Serializable {

    /** FLD_DEL_KBN mapping */
    private int fldDelKbn__;
    /** FLD_DEL_YEAR mapping */
    private int fldDelYear__;
    /** FLD_DEL_MONTH mapping */
    private int fldDelMonth__;
    /** FLD_AUID mapping */
    private int fldAuid__;
    /** FLD_ADATE mapping */
    private UDate fldAdate__;
    /** FLD_EUID mapping */
    private int fldEuid__;
    /** FLD_EDATE mapping */
    private UDate fldEdate__;

    /**
     * <p>Default Constructor
     */
    public FileLogAdelModel() {
    }

    /**
     * <p>get FLD_DEL_KBN value
     * @return FLD_DEL_KBN value
     */
    public int getFldDelKbn() {
        return fldDelKbn__;
    }

    /**
     * <p>set FLD_DEL_KBN value
     * @param fldDelKbn FLD_DEL_KBN value
     */
    public void setFldDelKbn(int fldDelKbn) {
        fldDelKbn__ = fldDelKbn;
    }

    /**
     * <p>get FLD_DEL_YEAR value
     * @return FLD_DEL_YEAR value
     */
    public int getFldDelYear() {
        return fldDelYear__;
    }

    /**
     * <p>set FLD_DEL_YEAR value
     * @param fldDelYear FLD_DEL_YEAR value
     */
    public void setFldDelYear(int fldDelYear) {
        fldDelYear__ = fldDelYear;
    }

    /**
     * <p>get FLD_DEL_MONTH value
     * @return FLD_DEL_MONTH value
     */
    public int getFldDelMonth() {
        return fldDelMonth__;
    }

    /**
     * <p>set FLD_DEL_MONTH value
     * @param fldDelMonth FLD_DEL_MONTH value
     */
    public void setFldDelMonth(int fldDelMonth) {
        fldDelMonth__ = fldDelMonth;
    }

    /**
     * <p>get FLD_AUID value
     * @return FLD_AUID value
     */
    public int getFldAuid() {
        return fldAuid__;
    }

    /**
     * <p>set FLD_AUID value
     * @param fldAuid FLD_AUID value
     */
    public void setFldAuid(int fldAuid) {
        fldAuid__ = fldAuid;
    }

    /**
     * <p>get FLD_ADATE value
     * @return FLD_ADATE value
     */
    public UDate getFldAdate() {
        return fldAdate__;
    }

    /**
     * <p>set FLD_ADATE value
     * @param fldAdate FLD_ADATE value
     */
    public void setFldAdate(UDate fldAdate) {
        fldAdate__ = fldAdate;
    }

    /**
     * <p>get FLD_EUID value
     * @return FLD_EUID value
     */
    public int getFldEuid() {
        return fldEuid__;
    }

    /**
     * <p>set FLD_EUID value
     * @param fldEuid FLD_EUID value
     */
    public void setFldEuid(int fldEuid) {
        fldEuid__ = fldEuid;
    }

    /**
     * <p>get FLD_EDATE value
     * @return FLD_EDATE value
     */
    public UDate getFldEdate() {
        return fldEdate__;
    }

    /**
     * <p>set FLD_EDATE value
     * @param fldEdate FLD_EDATE value
     */
    public void setFldEdate(UDate fldEdate) {
        fldEdate__ = fldEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(fldDelKbn__);
        buf.append(",");
        buf.append(fldDelYear__);
        buf.append(",");
        buf.append(fldDelMonth__);
        buf.append(",");
        buf.append(fldAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(fldAdate__, ""));
        buf.append(",");
        buf.append(fldEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(fldEdate__, ""));
        return buf.toString();
    }

}
