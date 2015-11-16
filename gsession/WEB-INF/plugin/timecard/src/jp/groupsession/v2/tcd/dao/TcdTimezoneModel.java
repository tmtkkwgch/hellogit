package jp.groupsession.v2.tcd.dao;

import java.io.Serializable;
import java.sql.Time;

import jp.co.sjts.util.NullDefault;

/**
 * <p>TCD_TIMEZONE Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class TcdTimezoneModel implements Serializable {

    /** TTZ_SID mapping */
    private int ttzSid__;
    /** TTZ_KBN mapping */
    private int ttzKbn__;
    /** TTZ_FRTIME mapping */
    private Time ttzFrtime__;
    /** TTZ_TOTIME mapping */
    private Time ttzTotime__;

    /**
     * <p>Default Constructor
     */
    public TcdTimezoneModel() {
    }

    /**
     * <p>get TTZ_SID value
     * @return TTZ_SID value
     */
    public int getTtzSid() {
        return ttzSid__;
    }

    /**
     * <p>set TTZ_SID value
     * @param ttzSid TTZ_SID value
     */
    public void setTtzSid(int ttzSid) {
        ttzSid__ = ttzSid;
    }

    /**
     * <p>get TTZ_KBN value
     * @return TTZ_KBN value
     */
    public int getTtzKbn() {
        return ttzKbn__;
    }

    /**
     * <p>set TTZ_KBN value
     * @param ttzKbn TTZ_KBN value
     */
    public void setTtzKbn(int ttzKbn) {
        ttzKbn__ = ttzKbn;
    }

    /**
     * <p>get TTZ_FRTIME value
     * @return TTZ_FRTIME value
     */
    public Time getTtzFrtime() {
        return ttzFrtime__;
    }

    /**
     * <p>set TTZ_FRTIME value
     * @param ttzFrtime TTZ_FRTIME value
     */
    public void setTtzFrtime(Time ttzFrtime) {
        ttzFrtime__ = ttzFrtime;
    }

    /**
     * <p>get TTZ_TOTIME value
     * @return TTZ_TOTIME value
     */
    public Time getTtzTotime() {
        return ttzTotime__;
    }

    /**
     * <p>set TTZ_TOTIME value
     * @param ttzTotime TTZ_TOTIME value
     */
    public void setTtzTotime(Time ttzTotime) {
        ttzTotime__ = ttzTotime;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuilder buf = new StringBuilder();
        buf.append(ttzSid__);
        buf.append(",");
        buf.append(ttzKbn__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(ttzFrtime__, ""));
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(ttzTotime__, ""));
        return buf.toString();
    }

}
