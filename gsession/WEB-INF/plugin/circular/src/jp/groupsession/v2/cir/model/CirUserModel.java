package jp.groupsession.v2.cir.model;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.NullDefault;
import java.io.Serializable;

/**
 * <p>CIR_USER Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.1
 */
public class CirUserModel implements Serializable {

    /** USR_SID mapping */
    private int usrSid__;
    /** CUR_MAX_DSP mapping */
    private int curMaxDsp__;
    /** CUR_RELOAD mapping */
    private int curReload__;
    /** CUR_AUID mapping */
    private int curAuid__;
    /** CUR_ADATE mapping */
    private UDate curAdate__;
    /** CUR_EUID mapping */
    private int curEuid__;
    /** CUR_EDATE mapping */
    private UDate curEdate__;

    /**
     * <p>curReload を取得します。
     * @return curReload
     */
    public int getCurReload() {
        return curReload__;
    }

    /**
     * <p>curReload をセットします。
     * @param curReload curReload
     */
    public void setCurReload(int curReload) {
        curReload__ = curReload;
    }

    /**
     * <p>Default Constructor
     */
    public CirUserModel() {
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
     * <p>get CUR_MAX_DSP value
     * @return CUR_MAX_DSP value
     */
    public int getCurMaxDsp() {
        return curMaxDsp__;
    }

    /**
     * <p>set CUR_MAX_DSP value
     * @param curMaxDsp CUR_MAX_DSP value
     */
    public void setCurMaxDsp(int curMaxDsp) {
        curMaxDsp__ = curMaxDsp;
    }

    /**
     * <p>get CUR_AUID value
     * @return CUR_AUID value
     */
    public int getCurAuid() {
        return curAuid__;
    }

    /**
     * <p>set CUR_AUID value
     * @param curAuid CUR_AUID value
     */
    public void setCurAuid(int curAuid) {
        curAuid__ = curAuid;
    }

    /**
     * <p>get CUR_ADATE value
     * @return CUR_ADATE value
     */
    public UDate getCurAdate() {
        return curAdate__;
    }

    /**
     * <p>set CUR_ADATE value
     * @param curAdate CUR_ADATE value
     */
    public void setCurAdate(UDate curAdate) {
        curAdate__ = curAdate;
    }

    /**
     * <p>get CUR_EUID value
     * @return CUR_EUID value
     */
    public int getCurEuid() {
        return curEuid__;
    }

    /**
     * <p>set CUR_EUID value
     * @param curEuid CUR_EUID value
     */
    public void setCurEuid(int curEuid) {
        curEuid__ = curEuid;
    }

    /**
     * <p>get CUR_EDATE value
     * @return CUR_EDATE value
     */
    public UDate getCurEdate() {
        return curEdate__;
    }

    /**
     * <p>set CUR_EDATE value
     * @param curEdate CUR_EDATE value
     */
    public void setCurEdate(UDate curEdate) {
        curEdate__ = curEdate;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuilder buf = new StringBuilder();
        buf.append(usrSid__);
        buf.append(",");
        buf.append(curMaxDsp__);
        buf.append(",");
        buf.append(curAuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(curAdate__, ""));
        buf.append(",");
        buf.append(curEuid__);
        buf.append(",");
        buf.append(NullDefault.getStringFmObj(curEdate__, ""));
        return buf.toString();
    }

}
