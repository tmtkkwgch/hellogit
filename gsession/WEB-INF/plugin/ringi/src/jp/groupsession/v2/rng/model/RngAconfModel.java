package jp.groupsession.v2.rng.model;

import java.io.Serializable;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;

/**
 * <p>RNG_ACONF Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RngAconfModel implements Serializable {

    /** RAR_DEL_AUTH mapping */
    private int rarDelAuth__;
    /** RAR_AUID mapping */
    private int rarAuid__;
    /** RAR_ADATE mapping */
    private UDate rarAdate__;
    /** RAR_EUID mapping */
    private int rarEuid__;
    /** RAR_EDATE mapping */
    private UDate rarEdate__;
    /** RAR_SML_NTF mapping */
    private int rarSmlNtf__;
    /** RAR_SML_NTF_KBN mapping */
    private int rarSmlNtfKbn__;

    /**
     * <p>Default Constructor
     */
    public RngAconfModel() {
    }

    /**
     * <p>get RAR_DEL_AUTH value
     * @return RAR_DEL_AUTH value
     */
    public int getRarDelAuth() {
        return rarDelAuth__;
    }

    /**
     * <p>set RAR_DEL_AUTH value
     * @param rarDelAuth RAR_DEL_AUTH value
     */
    public void setRarDelAuth(int rarDelAuth) {
        rarDelAuth__ = rarDelAuth;
    }

    /**
     * <p>get RAR_AUID value
     * @return RAR_AUID value
     */
    public int getRarAuid() {
        return rarAuid__;
    }

    /**
     * <p>set RAR_AUID value
     * @param rarAuid RAR_AUID value
     */
    public void setRarAuid(int rarAuid) {
        rarAuid__ = rarAuid;
    }

    /**
     * <p>get RAR_ADATE value
     * @return RAR_ADATE value
     */
    public UDate getRarAdate() {
        return rarAdate__;
    }

    /**
     * <p>set RAR_ADATE value
     * @param rarAdate RAR_ADATE value
     */
    public void setRarAdate(UDate rarAdate) {
        rarAdate__ = rarAdate;
    }

    /**
     * <p>get RAR_EUID value
     * @return RAR_EUID value
     */
    public int getRarEuid() {
        return rarEuid__;
    }

    /**
     * <p>set RAR_EUID value
     * @param rarEuid RAR_EUID value
     */
    public void setRarEuid(int rarEuid) {
        rarEuid__ = rarEuid;
    }

    /**
     * <p>get RAR_EDATE value
     * @return RAR_EDATE value
     */
    public UDate getRarEdate() {
        return rarEdate__;
    }

    /**
     * <p>set RAR_EDATE value
     * @param rarEdate RAR_EDATE value
     */
    public void setRarEdate(UDate rarEdate) {
        rarEdate__ = rarEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(rarDelAuth__);
        buf.append(",");
        buf.append(rarAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(rarAdate__, ""));
        buf.append(",");
        buf.append(rarEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(rarEdate__, ""));
        return buf.toString();
    }

    /**
     * <p>rarSmlNtf を取得します。
     * @return rarSmlNtf
     */
    public int getRarSmlNtf() {
        return rarSmlNtf__;
    }

    /**
     * <p>rarSmlNtf をセットします。
     * @param rarSmlNtf rarSmlNtf
     */
    public void setRarSmlNtf(int rarSmlNtf) {
        rarSmlNtf__ = rarSmlNtf;
    }

    /**
     * <p>rarSmlNtfKbn を取得します。
     * @return rarSmlNtfKbn
     */
    public int getRarSmlNtfKbn() {
        return rarSmlNtfKbn__;
    }

    /**
     * <p>rarSmlNtfKbn をセットします。
     * @param rarSmlNtfKbn rarSmlNtfKbn
     */
    public void setRarSmlNtfKbn(int rarSmlNtfKbn) {
        rarSmlNtfKbn__ = rarSmlNtfKbn;
    }

}
