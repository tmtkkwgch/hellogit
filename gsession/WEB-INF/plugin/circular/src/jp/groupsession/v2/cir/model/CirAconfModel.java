package jp.groupsession.v2.cir.model;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.NullDefault;
import java.io.Serializable;

/**
 * <p>CIR_ACONF Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class CirAconfModel implements Serializable {

    /** CAF_SMAIL_SEND_KBN mapping */
    private int cafSmailSendKbn__;
    /** CAF_SMAIL_SEND mapping */
    private int cafSmailSend__;
    /** CAF_AUID mapping */
    private int cafAuid__;
    /** CAF_ADATE mapping */
    private UDate cafAdate__;
    /** CAF_EUID mapping */
    private int cafEuid__;
    /** CAF_EDATE mapping */
    private UDate cafEdate__;

    /**
     * <p>Default Constructor
     */
    public CirAconfModel() {
    }

    /**
     * <p>get CAF_SMAIL_SEND_KBN value
     * @return CAF_SMAIL_SEND_KBN value
     */
    public int getCafSmailSendKbn() {
        return cafSmailSendKbn__;
    }

    /**
     * <p>set CAF_SMAIL_SEND_KBN value
     * @param cafSmailSendKbn CAF_SMAIL_SEND_KBN value
     */
    public void setCafSmailSendKbn(int cafSmailSendKbn) {
        cafSmailSendKbn__ = cafSmailSendKbn;
    }

    /**
     * <p>get CAF_SMAIL_SEND value
     * @return CAF_SMAIL_SEND value
     */
    public int getCafSmailSend() {
        return cafSmailSend__;
    }

    /**
     * <p>set CAF_SMAIL_SEND value
     * @param cafSmailSend CAF_SMAIL_SEND value
     */
    public void setCafSmailSend(int cafSmailSend) {
        cafSmailSend__ = cafSmailSend;
    }

    /**
     * <p>get CAF_AUID value
     * @return CAF_AUID value
     */
    public int getCafAuid() {
        return cafAuid__;
    }

    /**
     * <p>set CAF_AUID value
     * @param cafAuid CAF_AUID value
     */
    public void setCafAuid(int cafAuid) {
        cafAuid__ = cafAuid;
    }

    /**
     * <p>get CAF_ADATE value
     * @return CAF_ADATE value
     */
    public UDate getCafAdate() {
        return cafAdate__;
    }

    /**
     * <p>set CAF_ADATE value
     * @param cafAdate CAF_ADATE value
     */
    public void setCafAdate(UDate cafAdate) {
        cafAdate__ = cafAdate;
    }

    /**
     * <p>get CAF_EUID value
     * @return CAF_EUID value
     */
    public int getCafEuid() {
        return cafEuid__;
    }

    /**
     * <p>set CAF_EUID value
     * @param cafEuid CAF_EUID value
     */
    public void setCafEuid(int cafEuid) {
        cafEuid__ = cafEuid;
    }

    /**
     * <p>get CAF_EDATE value
     * @return CAF_EDATE value
     */
    public UDate getCafEdate() {
        return cafEdate__;
    }

    /**
     * <p>set CAF_EDATE value
     * @param cafEdate CAF_EDATE value
     */
    public void setCafEdate(UDate cafEdate) {
        cafEdate__ = cafEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(cafSmailSendKbn__);
        buf.append(",");
        buf.append(cafSmailSend__);
        buf.append(",");
        buf.append(cafAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(cafAdate__, ""));
        buf.append(",");
        buf.append(cafEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(cafEdate__, ""));
        return buf.toString();
    }

}
