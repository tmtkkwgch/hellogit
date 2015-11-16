package jp.groupsession.v2.fil.model;

import java.io.Serializable;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;

/**
 * <p>FILE_CABINET Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.5
 */
public class FileCabinetModel implements Serializable {

    /** FCB_SID mapping */
    private int fcbSid__;
    /** FCB_NAME mapping */
    private String fcbName__;
    /** FCB_ACCESS_KBN mapping */
    private int fcbAccessKbn__;
    /** FCB_CAPA_KBN mapping */
    private int fcbCapaKbn__;
    /** FCB_CAPA_SIZE mapping */
    private int fcbCapaSize__;
    /** FCB_CAPA_WARN mapping */
    private int fcbCapaWarn__;
    /** FCB_VER_KBN mapping */
    private int fcbVerKbn__;
    /** FCB_VERALL_KBN mapping */
    private int fcbVerallKbn__;
    /** FCB_BIKO mapping */
    private String fcbBiko__;
    /** FCB_SORT mapping */
    private int fcbSort__;
    /** FCB_MARK mapping */
    private Long fcbMark__ = new Long(0);
    /** FCB_AUID mapping */
    private int fcbAuid__;
    /** FCB_ADATE mapping */
    private UDate fcbAdate__;
    /** FCB_EUID mapping */
    private int fcbEuid__;
    /** FCB_EDATE mapping */
    private UDate fcbEdate__;

    /**
     * <p>Default Constructor
     */
    public FileCabinetModel() {
    }

    /**
     * <p>get FCB_SID value
     * @return FCB_SID value
     */
    public int getFcbSid() {
        return fcbSid__;
    }

    /**
     * <p>set FCB_SID value
     * @param fcbSid FCB_SID value
     */
    public void setFcbSid(int fcbSid) {
        fcbSid__ = fcbSid;
    }

    /**
     * <p>get FCB_NAME value
     * @return FCB_NAME value
     */
    public String getFcbName() {
        return fcbName__;
    }

    /**
     * <p>set FCB_NAME value
     * @param fcbName FCB_NAME value
     */
    public void setFcbName(String fcbName) {
        fcbName__ = fcbName;
    }

    /**
     * <p>get FCB_ACCESS_KBN value
     * @return FCB_ACCESS_KBN value
     */
    public int getFcbAccessKbn() {
        return fcbAccessKbn__;
    }

    /**
     * <p>set FCB_ACCESS_KBN value
     * @param fcbAccessKbn FCB_ACCESS_KBN value
     */
    public void setFcbAccessKbn(int fcbAccessKbn) {
        fcbAccessKbn__ = fcbAccessKbn;
    }

    /**
     * <p>get FCB_CAPA_KBN value
     * @return FCB_CAPA_KBN value
     */
    public int getFcbCapaKbn() {
        return fcbCapaKbn__;
    }

    /**
     * <p>set FCB_CAPA_KBN value
     * @param fcbCapaKbn FCB_CAPA_KBN value
     */
    public void setFcbCapaKbn(int fcbCapaKbn) {
        fcbCapaKbn__ = fcbCapaKbn;
    }

    /**
     * <p>get FCB_CAPA_SIZE value
     * @return FCB_CAPA_SIZE value
     */
    public int getFcbCapaSize() {
        return fcbCapaSize__;
    }

    /**
     * <p>set FCB_CAPA_SIZE value
     * @param fcbCapaSize FCB_CAPA_SIZE value
     */
    public void setFcbCapaSize(int fcbCapaSize) {
        fcbCapaSize__ = fcbCapaSize;
    }

    /**
     * <p>get FCB_CAPA_WARN value
     * @return FCB_CAPA_WARN value
     */
    public int getFcbCapaWarn() {
        return fcbCapaWarn__;
    }

    /**
     * <p>set FCB_CAPA_WARN value
     * @param fcbCapaWarn FCB_CAPA_WARN value
     */
    public void setFcbCapaWarn(int fcbCapaWarn) {
        fcbCapaWarn__ = fcbCapaWarn;
    }

    /**
     * <p>get FCB_VER_KBN value
     * @return FCB_VER_KBN value
     */
    public int getFcbVerKbn() {
        return fcbVerKbn__;
    }

    /**
     * <p>set FCB_VER_KBN value
     * @param fcbVerKbn FCB_VER_KBN value
     */
    public void setFcbVerKbn(int fcbVerKbn) {
        fcbVerKbn__ = fcbVerKbn;
    }

    /**
     * <p>get FCB_VERALL_KBN value
     * @return FCB_VERALL_KBN value
     */
    public int getFcbVerallKbn() {
        return fcbVerallKbn__;
    }

    /**
     * <p>set FCB_VERALL_KBN value
     * @param fcbVerallKbn FCB_VERALL_KBN value
     */
    public void setFcbVerallKbn(int fcbVerallKbn) {
        fcbVerallKbn__ = fcbVerallKbn;
    }

    /**
     * <p>get FCB_BIKO value
     * @return FCB_BIKO value
     */
    public String getFcbBiko() {
        return fcbBiko__;
    }

    /**
     * <p>set FCB_BIKO value
     * @param fcbBiko FCB_BIKO value
     */
    public void setFcbBiko(String fcbBiko) {
        fcbBiko__ = fcbBiko;
    }

    /**
     * <p>get FCB_SORT value
     * @return FCB_SORT value
     */
    public int getFcbSort() {
        return fcbSort__;
    }

    /**
     * <p>set FCB_SORT value
     * @param fcbSort FCB_SORT value
     */
    public void setFcbSort(int fcbSort) {
        fcbSort__ = fcbSort;
    }

    /**
     * <p>get FCB_AUID value
     * @return FCB_AUID value
     */
    public int getFcbAuid() {
        return fcbAuid__;
    }

    /**
     * <p>set FCB_AUID value
     * @param fcbAuid FCB_AUID value
     */
    public void setFcbAuid(int fcbAuid) {
        fcbAuid__ = fcbAuid;
    }

    /**
     * <p>get FCB_ADATE value
     * @return FCB_ADATE value
     */
    public UDate getFcbAdate() {
        return fcbAdate__;
    }

    /**
     * <p>set FCB_ADATE value
     * @param fcbAdate FCB_ADATE value
     */
    public void setFcbAdate(UDate fcbAdate) {
        fcbAdate__ = fcbAdate;
    }

    /**
     * <p>get FCB_EUID value
     * @return FCB_EUID value
     */
    public int getFcbEuid() {
        return fcbEuid__;
    }

    /**
     * <p>set FCB_EUID value
     * @param fcbEuid FCB_EUID value
     */
    public void setFcbEuid(int fcbEuid) {
        fcbEuid__ = fcbEuid;
    }

    /**
     * <p>get FCB_EDATE value
     * @return FCB_EDATE value
     */
    public UDate getFcbEdate() {
        return fcbEdate__;
    }

    /**
     * <p>set FCB_EDATE value
     * @param fcbEdate FCB_EDATE value
     */
    public void setFcbEdate(UDate fcbEdate) {
        fcbEdate__ = fcbEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuilder buf = new StringBuilder();
        buf.append(fcbSid__);
        buf.append(",");
        buf.append(NullDefault.getString(fcbName__, ""));
        buf.append(",");
        buf.append(fcbAccessKbn__);
        buf.append(",");
        buf.append(fcbCapaKbn__);
        buf.append(",");
        buf.append(fcbCapaSize__);
        buf.append(",");
        buf.append(fcbCapaWarn__);
        buf.append(",");
        buf.append(fcbVerKbn__);
        buf.append(",");
        buf.append(fcbVerallKbn__);
        buf.append(",");
        buf.append(fcbBiko__);
        buf.append(",");
        buf.append(fcbSort__);
        buf.append(",");
        buf.append(fcbAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(fcbAdate__, ""));
        buf.append(",");
        buf.append(fcbEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(fcbEdate__, ""));
        return buf.toString();
    }

    /**
     * <p>fcbMark を取得します。
     * @return fcbMark
     */
    public Long getFcbMark() {
        return fcbMark__;
    }

    /**
     * <p>fcbMark をセットします。
     * @param fcbMark fcbMark
     */
    public void setFcbMark(Long fcbMark) {
        fcbMark__ = fcbMark;
    }

}
