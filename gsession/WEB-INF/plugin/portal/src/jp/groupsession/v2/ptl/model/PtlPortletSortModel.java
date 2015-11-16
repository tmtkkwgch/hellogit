package jp.groupsession.v2.ptl.model;

import java.io.Serializable;

/**
 * <p>PTL_PORTLET_SORT Data Bindding JavaBean
 *
 * @author JTS DaoGenerator version 0.5
 */
public class PtlPortletSortModel implements Serializable {

    /** PLT_SID mapping */
    private int pltSid__;
    /** PLS_SORT mapping */
    private int plsSort__;
    /** PLS_VIEW mapping */
    private int plsView__;

    /**
     * <p>Default Constructor
     */
    public PtlPortletSortModel() {
    }

    /**
     * <p>get PLT_SID value
     * @return PLT_SID value
     */
    public int getPltSid() {
        return pltSid__;
    }

    /**
     * <p>set PLT_SID value
     * @param pltSid PLT_SID value
     */
    public void setPltSid(int pltSid) {
        pltSid__ = pltSid;
    }

    /**
     * <p>get PLS_SORT value
     * @return PLS_SORT value
     */
    public int getPlsSort() {
        return plsSort__;
    }

    /**
     * <p>set PLS_SORT value
     * @param plsSort PLS_SORT value
     */
    public void setPlsSort(int plsSort) {
        plsSort__ = plsSort;
    }

    /**
     * <p>get PLS_VIEW value
     * @return PLS_VIEW value
     */
    public int getPlsView() {
        return plsView__;
    }

    /**
     * <p>set PLS_VIEW value
     * @param plsView PLS_VIEW value
     */
    public void setPlsView(int plsView) {
        plsView__ = plsView;
    }

    /**
     * <p>to Csv String
     * @return Csv String
     */
    public String toCsvString() {
        StringBuffer buf = new StringBuffer();
        buf.append(pltSid__);
        buf.append(",");
        buf.append(plsSort__);
        buf.append(",");
        buf.append(plsView__);
        return buf.toString();
    }

}
