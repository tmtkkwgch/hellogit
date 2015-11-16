package jp.groupsession.v2.wml.wml280;

import jp.groupsession.v2.cmn.model.AbstractModel;

/**
 * <br>[機  能] WEBメール 送信先リスト登録画面の[アドレス帳]情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml280AddressBookModel extends AbstractModel {
    /** アドレス帳SID */
    private int adrSid__ = 0;
    /** 会社SID */
    private int acoSid__ = 0;
    /** 会社拠点SID */
    private int abaSid__ = 0;

    /** 氏名 姓 */
    private String adrSei__ = null;;
    /** 氏名 名 */
    private String adrMei__ = null;
    /** メールアドレス1 */
    private String adrMail1__ = null;
    /** メールアドレス2 */
    private String adrMail2__ = null;
    /** メールアドレス3 */
    private String adrMail3__ = null;
    /** 会社名 */
    private String acoName__ = null;
    /** 会社拠点名 */
    private String abaName__ = null;
    /** 役職名 */
    private String yakusyoku__ = null;

    /**
     * <p>adrSid を取得します。
     * @return adrSid
     */
    public int getAdrSid() {
        return adrSid__;
    }
    /**
     * <p>adrSid をセットします。
     * @param adrSid adrSid
     */
    public void setAdrSid(int adrSid) {
        adrSid__ = adrSid;
    }
    /**
     * <p>adrMail1 を取得します。
     * @return adrMail1
     */
    public String getAdrMail1() {
        return adrMail1__;
    }
    /**
     * <p>adrMail1 をセットします。
     * @param adrMail1 adrMail1
     */
    public void setAdrMail1(String adrMail1) {
        adrMail1__ = adrMail1;
    }
    /**
     * <p>adrMail2 を取得します。
     * @return adrMail2
     */
    public String getAdrMail2() {
        return adrMail2__;
    }
    /**
     * <p>adrMail2 をセットします。
     * @param adrMail2 adrMail2
     */
    public void setAdrMail2(String adrMail2) {
        adrMail2__ = adrMail2;
    }
    /**
     * <p>adrMail3 を取得します。
     * @return adrMail3
     */
    public String getAdrMail3() {
        return adrMail3__;
    }
    /**
     * <p>adrMail3 をセットします。
     * @param adrMail3 adrMail3
     */
    public void setAdrMail3(String adrMail3) {
        adrMail3__ = adrMail3;
    }
    /**
     * <p>acoSid を取得します。
     * @return acoSid
     */
    public int getAcoSid() {
        return acoSid__;
    }
    /**
     * <p>acoSid をセットします。
     * @param acoSid acoSid
     */
    public void setAcoSid(int acoSid) {
        acoSid__ = acoSid;
    }
    /**
     * <p>abaSid を取得します。
     * @return abaSid
     */
    public int getAbaSid() {
        return abaSid__;
    }
    /**
     * <p>abaSid をセットします。
     * @param abaSid abaSid
     */
    public void setAbaSid(int abaSid) {
        abaSid__ = abaSid;
    }
    /**
     * <p>adrSei を取得します。
     * @return adrSei
     */
    public String getAdrSei() {
        return adrSei__;
    }
    /**
     * <p>adrSei をセットします。
     * @param adrSei adrSei
     */
    public void setAdrSei(String adrSei) {
        adrSei__ = adrSei;
    }
    /**
     * <p>adrMei を取得します。
     * @return adrMei
     */
    public String getAdrMei() {
        return adrMei__;
    }
    /**
     * <p>adrMei をセットします。
     * @param adrMei adrMei
     */
    public void setAdrMei(String adrMei) {
        adrMei__ = adrMei;
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
}