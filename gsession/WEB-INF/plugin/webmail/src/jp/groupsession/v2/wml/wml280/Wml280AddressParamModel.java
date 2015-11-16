package jp.groupsession.v2.wml.wml280;

import jp.groupsession.v2.cmn.model.AbstractModel;

/**
 * <br>[機  能] WEBメール 送信先リスト登録画面の送信先パラメータを保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml280AddressParamModel extends AbstractModel {
    /** 種別 ユーザ情報 */
    public static final int TYPE_USER = 0;
    /** 種別 アドレス帳 */
    public static final int TYPE_ADDRESS = 1;

    /** 送信先ID */
    private String destId__ = null;
    /** 種別 */
    private int type__ = 0;
    /** SID(ユーザSID or アドレス帳SID) */
    private int sid__ = 0;
    /** メール番号 */
    private int mailNo__ = 0;
    /** 名称 */
    private String name__ = null;
    /** メールアドレス */
    private String mailAddress__ = null;
    /** メールアドレス(送信先設定用) */
    private String sendMailAddress__ = null;
    /** 役職名 */
    private String yakusyoku__ = null;

    //アドレス帳情報
    /** 会社名 */
    private String acoName__ = null;
    /** 会社拠点名 */
    private String abaName__ = null;

    /**
     * <p>destId を取得します。
     * @return destId
     */
    public String getDestId() {
        return destId__;
    }
    /**
     * <p>destId をセットします。
     * @param destId destId
     */
    public void setDestId(String destId) {
        destId__ = destId;
    }
    /**
     * <p>type を取得します。
     * @return type
     */
    public int getType() {
        return type__;
    }
    /**
     * <p>type をセットします。
     * @param type type
     */
    public void setType(int type) {
        type__ = type;
    }
    /**
     * <p>sid を取得します。
     * @return sid
     */
    public int getSid() {
        return sid__;
    }
    /**
     * <p>sid をセットします。
     * @param sid sid
     */
    public void setSid(int sid) {
        sid__ = sid;
    }
    /**
     * <p>mailNo を取得します。
     * @return mailNo
     */
    public int getMailNo() {
        return mailNo__;
    }
    /**
     * <p>mailNo をセットします。
     * @param mailNo mailNo
     */
    public void setMailNo(int mailNo) {
        mailNo__ = mailNo;
    }
    /**
     * <p>name を取得します。
     * @return name
     */
    public String getName() {
        return name__;
    }
    /**
     * <p>name をセットします。
     * @param name name
     */
    public void setName(String name) {
        name__ = name;
    }
    /**
     * <p>mailAddress を取得します。
     * @return mailAddress
     */
    public String getMailAddress() {
        return mailAddress__;
    }
    /**
     * <p>mailAddress をセットします。
     * @param mailAddress mailAddress
     */
    public void setMailAddress(String mailAddress) {
        mailAddress__ = mailAddress;
    }
    /**
     * <p>acoName を取得します。
     * @return acoName
     */
    public String getAcoName() {
        return acoName__;
    }
    /**
     * <p>acoName をセットします。
     * @param acoName acoName
     */
    public void setAcoName(String acoName) {
        acoName__ = acoName;
    }
    /**
     * <p>abaName を取得します。
     * @return abaName
     */
    public String getAbaName() {
        return abaName__;
    }
    /**
     * <p>abaName をセットします。
     * @param abaName abaName
     */
    public void setAbaName(String abaName) {
        abaName__ = abaName;
    }
    /**
     * <p>yakusyoku を取得します。
     * @return yakusyoku
     */
    public String getYakusyoku() {
        return yakusyoku__;
    }
    /**
     * <p>yakusyoku をセットします。
     * @param yakusyoku yakusyoku
     */
    public void setYakusyoku(String yakusyoku) {
        yakusyoku__ = yakusyoku;
    }
    /**
     * <p>sendMailAddress を取得します。
     * @return sendMailAddress
     */
    public String getSendMailAddress() {
        return sendMailAddress__;
    }
    /**
     * <p>sendMailAddress をセットします。
     * @param sendMailAddress sendMailAddress
     */
    public void setSendMailAddress(String sendMailAddress) {
        sendMailAddress__ = sendMailAddress;
    }
}