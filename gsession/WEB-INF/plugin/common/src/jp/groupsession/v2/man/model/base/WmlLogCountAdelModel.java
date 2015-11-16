package jp.groupsession.v2.man.model.base;

import java.io.Serializable;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;

/**
 * <p>WML_LOG_COUNT_ADEL Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class WmlLogCountAdelModel implements Serializable {

    /** WLD_DEL_KBN mapping */
    private int wldDelKbn__;
    /** WLD_DEL_YEAR mapping */
    private int wldDelYear__;
    /** WLD_DEL_MONTH mapping */
    private int wldDelMonth__;
    /** WLD_AUID mapping */
    private int wldAuid__;
    /** WLD_ADATE mapping */
    private UDate wldAdate__;
    /** WLD_EUID mapping */
    private int wldEuid__;
    /** WLD_EDATE mapping */
    private UDate wldEdate__;

    /**
     * <p>Default Constructor
     */
    public WmlLogCountAdelModel() {
    }

    /**
     * <p>get WLD_DEL_KBN value
     * @return WLD_DEL_KBN value
     */
    public int getWldDelKbn() {
        return wldDelKbn__;
    }

    /**
     * <p>set WLD_DEL_KBN value
     * @param wldDelKbn WLD_DEL_KBN value
     */
    public void setWldDelKbn(int wldDelKbn) {
        wldDelKbn__ = wldDelKbn;
    }

    /**
     * <p>get WLD_DEL_YEAR value
     * @return WLD_DEL_YEAR value
     */
    public int getWldDelYear() {
        return wldDelYear__;
    }

    /**
     * <p>set WLD_DEL_YEAR value
     * @param wldDelYear WLD_DEL_YEAR value
     */
    public void setWldDelYear(int wldDelYear) {
        wldDelYear__ = wldDelYear;
    }

    /**
     * <p>get WLD_DEL_MONTH value
     * @return WLD_DEL_MONTH value
     */
    public int getWldDelMonth() {
        return wldDelMonth__;
    }

    /**
     * <p>set WLD_DEL_MONTH value
     * @param wldDelMonth WLD_DEL_MONTH value
     */
    public void setWldDelMonth(int wldDelMonth) {
        wldDelMonth__ = wldDelMonth;
    }

    /**
     * <p>get WLD_AUID value
     * @return WLD_AUID value
     */
    public int getWldAuid() {
        return wldAuid__;
    }

    /**
     * <p>set WLD_AUID value
     * @param wldAuid WLD_AUID value
     */
    public void setWldAuid(int wldAuid) {
        wldAuid__ = wldAuid;
    }

    /**
     * <p>get WLD_ADATE value
     * @return WLD_ADATE value
     */
    public UDate getWldAdate() {
        return wldAdate__;
    }

    /**
     * <p>set WLD_ADATE value
     * @param wldAdate WLD_ADATE value
     */
    public void setWldAdate(UDate wldAdate) {
        wldAdate__ = wldAdate;
    }

    /**
     * <p>get WLD_EUID value
     * @return WLD_EUID value
     */
    public int getWldEuid() {
        return wldEuid__;
    }

    /**
     * <p>set WLD_EUID value
     * @param wldEuid WLD_EUID value
     */
    public void setWldEuid(int wldEuid) {
        wldEuid__ = wldEuid;
    }

    /**
     * <p>get WLD_EDATE value
     * @return WLD_EDATE value
     */
    public UDate getWldEdate() {
        return wldEdate__;
    }

    /**
     * <p>set WLD_EDATE value
     * @param wldEdate WLD_EDATE value
     */
    public void setWldEdate(UDate wldEdate) {
        wldEdate__ = wldEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(wldDelKbn__);
        buf.append(",");
        buf.append(wldDelYear__);
        buf.append(",");
        buf.append(wldDelMonth__);
        buf.append(",");
        buf.append(wldAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(wldAdate__, ""));
        buf.append(",");
        buf.append(wldEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(wldEdate__, ""));
        return buf.toString();
    }

}
