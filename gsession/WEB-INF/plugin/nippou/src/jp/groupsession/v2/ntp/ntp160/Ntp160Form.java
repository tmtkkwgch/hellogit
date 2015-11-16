package jp.groupsession.v2.ntp.ntp160;

import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.ntp.ntp120.Ntp120Form;
import jp.groupsession.v2.ntp.ntp150.Ntp150ProcessModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 プロセス順位一覧画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp160Form extends Ntp120Form {
    /** プロセス一覧 */
    private ArrayList<Ntp150ProcessModel> ntp160ProcessList__ = null;
    /** プロセスSID */
    private int ntp160NgpSid__ = -1;
    /** 業務内容SID */
    private int ntp160NgySid__ = -1;
    /** 業務一覧 */
    private List<LabelValueBean> ntp160GyomuList__ = null;
    /** 表示順リスト */
    private String[] ntp160SortKey__ = null;
    /** チェックされている並び順 */
    private String ntp160SortRadio__ = null;

    /**
     * @return ntp160GyomuList
     */
    public List<LabelValueBean> getNtp160GyomuList() {
        return ntp160GyomuList__;
    }
    /**
     * @param ntp160GyomuList 設定する ntp160GyomuList
     */
    public void setNtp160GyomuList(List<LabelValueBean> ntp160GyomuList) {
        ntp160GyomuList__ = ntp160GyomuList;
    }
    /**
     * @return ntp160NgpSid
     */
    public int getNtp160NgpSid() {
        return ntp160NgpSid__;
    }
    /**
     * @param ntp160NgpSid 設定する ntp160NgpSid
     */
    public void setNtp160NgpSid(int ntp160NgpSid) {
        ntp160NgpSid__ = ntp160NgpSid;
    }
    /**
     * @return ntp160NgySid
     */
    public int getNtp160NgySid() {
        return ntp160NgySid__;
    }
    /**
     * @param ntp160NgySid 設定する ntp160NgySid
     */
    public void setNtp160NgySid(int ntp160NgySid) {
        ntp160NgySid__ = ntp160NgySid;
    }
    /**
     * @return ntp160ProcessList
     */
    public ArrayList<Ntp150ProcessModel> getNtp160ProcessList() {
        return ntp160ProcessList__;
    }
    /**
     * @param ntp160ProcessList 設定する ntp160ProcessList
     */
    public void setNtp160ProcessList(ArrayList<Ntp150ProcessModel> ntp160ProcessList) {
        ntp160ProcessList__ = ntp160ProcessList;
    }

    /**
     * @return ntp160SortKey
     */
    public String[] getNtp160SortKey() {
        return ntp160SortKey__;
    }
    /**
     * @param ntp160SortKey 設定する ntp160SortKey
     */
    public void setNtp160SortKey(String[] ntp160SortKey) {
        ntp160SortKey__ = ntp160SortKey;
    }
    /**
     * @return ntp160SortRadio
     */
    public String getNtp160SortRadio() {
        return ntp160SortRadio__;
    }
    /**
     * @param ntp160SortRadio 設定する ntp160SortRadio
     */
    public void setNtp160SortRadio(String ntp160SortRadio) {
        ntp160SortRadio__ = ntp160SortRadio;
    }
}