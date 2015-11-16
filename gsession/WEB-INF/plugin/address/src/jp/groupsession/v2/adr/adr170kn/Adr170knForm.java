package jp.groupsession.v2.adr.adr170kn;

import java.util.ArrayList;

import jp.groupsession.v2.adr.adr170.Adr170Form;
import jp.groupsession.v2.adr.model.AdrAddressModel;

/**
 * <br>[機  能] アドレス帳 コンタクト履歴登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr170knForm extends Adr170Form {

    //表示項目
    /** コンタクト日時From(表示用) */
    private String adr170cttimeDsp__;
    /** コンタクト日時To(表示用) */
    private String adr170cttimeToDsp__;
    /** プロジェクト(表示用) */
    private String adr170projectDsp__;
    /** 備考(表示用) */
    private String adr170bikoDsp__;
    /** 添付ファイルのバイナリSID(ダウンロード時) */
    private String adr170knBinSid__ = null;
    /** 同時登録ユーザ */
    private ArrayList<AdrAddressModel> adr170knUser__ = null;

    /**
     * <p>adr170bikoDsp を取得します。
     * @return adr170bikoDsp
     */
    public String getAdr170bikoDsp() {
        return adr170bikoDsp__;
    }
    /**
     * <p>adr170bikoDsp をセットします。
     * @param adr170bikoDsp adr170bikoDsp
     */
    public void setAdr170bikoDsp(String adr170bikoDsp) {
        adr170bikoDsp__ = adr170bikoDsp;
    }
    /**
     * <p>adr170cttimeDsp を取得します。
     * @return adr170cttimeDsp
     */
    public String getAdr170cttimeDsp() {
        return adr170cttimeDsp__;
    }
    /**
     * <p>adr170cttimeDsp をセットします。
     * @param adr170cttimeDsp adr170cttimeDsp
     */
    public void setAdr170cttimeDsp(String adr170cttimeDsp) {
        adr170cttimeDsp__ = adr170cttimeDsp;
    }
    /**
     * <p>adr170projectDsp を取得します。
     * @return adr170projectDsp
     */
    public String getAdr170projectDsp() {
        return adr170projectDsp__;
    }
    /**
     * <p>adr170projectDsp をセットします。
     * @param adr170projectDsp adr170projectDsp
     */
    public void setAdr170projectDsp(String adr170projectDsp) {
        adr170projectDsp__ = adr170projectDsp;
    }
    /**
     * <p>adr170knBinSid を取得します。
     * @return adr170knBinSid
     */
    public String getAdr170knBinSid() {
        return adr170knBinSid__;
    }
    /**
     * <p>adr170knBinSid をセットします。
     * @param adr170knBinSid adr170knBinSid
     */
    public void setAdr170knBinSid(String adr170knBinSid) {
        adr170knBinSid__ = adr170knBinSid;
    }
    /**
     * <p>adr170cttimeToDsp を取得します。
     * @return adr170cttimeToDsp
     */
    public String getAdr170cttimeToDsp() {
        return adr170cttimeToDsp__;
    }
    /**
     * <p>adr170cttimeToDsp をセットします。
     * @param adr170cttimeToDsp adr170cttimeToDsp
     */
    public void setAdr170cttimeToDsp(String adr170cttimeToDsp) {
        adr170cttimeToDsp__ = adr170cttimeToDsp;
    }
    /**
     * <p>adr170knUser を取得します。
     * @return adr170knUser
     */
    public ArrayList<AdrAddressModel> getAdr170knUser() {
        return adr170knUser__;
    }
    /**
     * <p>adr170knUser をセットします。
     * @param adr170knUser adr170knUser
     */
    public void setAdr170knUser(ArrayList<AdrAddressModel> adr170knUser) {
        adr170knUser__ = adr170knUser;
    }
}