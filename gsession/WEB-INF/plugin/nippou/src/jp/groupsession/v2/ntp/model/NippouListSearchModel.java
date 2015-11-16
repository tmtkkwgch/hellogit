package jp.groupsession.v2.ntp.model;

import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.AbstractModel;

/**
 * <br>[機  能] 日報一覧の検索条件を格納Modelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class NippouListSearchModel extends AbstractModel {

    /** ユーザSID */
    private int usrSid__;

    /** 昇順降順 */
    private int ntpOrder__;
    /** ソートKEY */
    private int ntpSortKey__;
    /** 昇順降順 */
    private int ntpOrder2__;
    /** ソートKEY */
    private int ntpSortKey2__;
    /** オフセット */
    private int ntpOffset__;
    /** 最大表示件数 */
    private int ntpLimit__;

    //検索条件
    /** グループSID */
    private String ntpSltGroupSid__;
    /** ユーザSID */
    private int ntpSltUserSid__;
    /** 報告日付from */
    private UDate ntpDateFrom__;
    /** 報告日付to */
    private UDate ntpDateTo__;

    /** キーワード */
    private List<String> ntpKeyValue__;
    /** キーワード区分(AND・OR) */
    private int keyWordkbn__;
    /** 検索対象 (件名・本文) */
    private String[] targetKbn__ = null;
    /** 検索対象 タイトル*/
    private boolean targetTitle__ = false;
    /** 検索対象 内容*/
    private boolean targetValue__ = false;
    /** タイトルカラー*/
    private String[] bgcolor__ = null;
    /** 見込み度*/
    private String[] mikomido__ = null;
    /** 活動分類*/
    private int katudouBunrui__;
    /** 活動方法*/
    private int katudouHouhou__;

    /** 案件SID*/
    private int ankenSid__;
    /** 企業SID*/
    private int companySid__;
    /** 拠点SID*/
    private int companyBaseSid__;

    /** マイグループフラグ*/
    private boolean myGrpFlg__ = false;

    /**
     * <p>usrSid を取得します。
     * @return usrSid
     */
    public int getUsrSid() {
        return usrSid__;
    }
    /**
     * <p>usrSid をセットします。
     * @param usrSid usrSid
     */
    public void setUsrSid(int usrSid) {
        usrSid__ = usrSid;
    }
    /**
     * <p>ntpOrder を取得します。
     * @return ntpOrder
     */
    public int getNtpOrder() {
        return ntpOrder__;
    }
    /**
     * <p>ntpOrder をセットします。
     * @param ntpOrder ntpOrder
     */
    public void setNtpOrder(int ntpOrder) {
        ntpOrder__ = ntpOrder;
    }
    /**
     * <p>ntpSortKey を取得します。
     * @return ntpSortKey
     */
    public int getNtpSortKey() {
        return ntpSortKey__;
    }
    /**
     * <p>ntpSortKey をセットします。
     * @param ntpSortKey ntpSortKey
     */
    public void setNtpSortKey(int ntpSortKey) {
        ntpSortKey__ = ntpSortKey;
    }
    /**
     * <p>ntpOrder2 を取得します。
     * @return ntpOrder2
     */
    public int getNtpOrder2() {
        return ntpOrder2__;
    }
    /**
     * <p>ntpOrder2 をセットします。
     * @param ntpOrder2 ntpOrder2
     */
    public void setNtpOrder2(int ntpOrder2) {
        ntpOrder2__ = ntpOrder2;
    }
    /**
     * <p>ntpSortKey2 を取得します。
     * @return ntpSortKey2
     */
    public int getNtpSortKey2() {
        return ntpSortKey2__;
    }
    /**
     * <p>ntpSortKey2 をセットします。
     * @param ntpSortKey2 ntpSortKey2
     */
    public void setNtpSortKey2(int ntpSortKey2) {
        ntpSortKey2__ = ntpSortKey2;
    }
    /**
     * <p>ntpOffset を取得します。
     * @return ntpOffset
     */
    public int getNtpOffset() {
        return ntpOffset__;
    }
    /**
     * <p>ntpOffset をセットします。
     * @param ntpOffset ntpOffset
     */
    public void setNtpOffset(int ntpOffset) {
        ntpOffset__ = ntpOffset;
    }
    /**
     * <p>ntpLimit を取得します。
     * @return ntpLimit
     */
    public int getNtpLimit() {
        return ntpLimit__;
    }
    /**
     * <p>ntpLimit をセットします。
     * @param ntpLimit ntpLimit
     */
    public void setNtpLimit(int ntpLimit) {
        ntpLimit__ = ntpLimit;
    }
    /**
     * <p>ntpSltUserSid を取得します。
     * @return ntpSltUserSid
     */
    public int getNtpSltUserSid() {
        return ntpSltUserSid__;
    }
    /**
     * <p>ntpSltUserSid をセットします。
     * @param ntpSltUserSid ntpSltUserSid
     */
    public void setNtpSltUserSid(int ntpSltUserSid) {
        ntpSltUserSid__ = ntpSltUserSid;
    }
    /**
     * <p>ntpKeyValue を取得します。
     * @return ntpKeyValue
     */
    public List<String> getNtpKeyValue() {
        return ntpKeyValue__;
    }
    /**
     * <p>ntpKeyValue をセットします。
     * @param ntpKeyValue ntpKeyValue
     */
    public void setNtpKeyValue(List<String> ntpKeyValue) {
        ntpKeyValue__ = ntpKeyValue;
    }
    /**
     * <p>keyWordkbn を取得します。
     * @return keyWordkbn
     */
    public int getKeyWordkbn() {
        return keyWordkbn__;
    }
    /**
     * <p>keyWordkbn をセットします。
     * @param keyWordkbn keyWordkbn
     */
    public void setKeyWordkbn(int keyWordkbn) {
        keyWordkbn__ = keyWordkbn;
    }
    /**
     * <p>targetKbn を取得します。
     * @return targetKbn
     */
    public String[] getTargetKbn() {
        return targetKbn__;
    }
    /**
     * <p>targetKbn をセットします。
     * @param targetKbn targetKbn
     */
    public void setTargetKbn(String[] targetKbn) {
        targetKbn__ = targetKbn;
    }
    /**
     * <p>targetTitle を取得します。
     * @return targetTitle
     */
    public boolean isTargetTitle() {
        return targetTitle__;
    }
    /**
     * <p>targetTitle をセットします。
     * @param targetTitle targetTitle
     */
    public void setTargetTitle(boolean targetTitle) {
        targetTitle__ = targetTitle;
    }
    /**
     * <p>targetValue を取得します。
     * @return targetValue
     */
    public boolean isTargetValue() {
        return targetValue__;
    }
    /**
     * <p>targetValue をセットします。
     * @param targetValue targetValue
     */
    public void setTargetValue(boolean targetValue) {
        targetValue__ = targetValue;
    }
    /**
     * <p>bgcolor を取得します。
     * @return bgcolor
     */
    public String[] getBgcolor() {
        return bgcolor__;
    }
    /**
     * <p>bgcolor をセットします。
     * @param bgcolor bgcolor
     */
    public void setBgcolor(String[] bgcolor) {
        bgcolor__ = bgcolor;
    }
    /**
     * <p>mikomido を取得します。
     * @return mikomido
     */
    public String[] getMikomido() {
        return mikomido__;
    }
    /**
     * <p>mikomido をセットします。
     * @param mikomido mikomido
     */
    public void setMikomido(String[] mikomido) {
        mikomido__ = mikomido;
    }
    /**
     * <p>katudouBunrui を取得します。
     * @return katudouBunrui
     */
    public int getKatudouBunrui() {
        return katudouBunrui__;
    }
    /**
     * <p>katudouBunrui をセットします。
     * @param katudouBunrui katudouBunrui
     */
    public void setKatudouBunrui(int katudouBunrui) {
        katudouBunrui__ = katudouBunrui;
    }
    /**
     * <p>katudouHouhou を取得します。
     * @return katudouHouhou
     */
    public int getKatudouHouhou() {
        return katudouHouhou__;
    }
    /**
     * <p>katudouHouhou をセットします。
     * @param katudouHouhou katudouHouhou
     */
    public void setKatudouHouhou(int katudouHouhou) {
        katudouHouhou__ = katudouHouhou;
    }
    /**
     * <p>ntpDateFrom を取得します。
     * @return ntpDateFrom
     */
    public UDate getNtpDateFrom() {
        return ntpDateFrom__;
    }
    /**
     * <p>ntpDateFrom をセットします。
     * @param ntpDateFrom ntpDateFrom
     */
    public void setNtpDateFrom(UDate ntpDateFrom) {
        ntpDateFrom__ = ntpDateFrom;
    }
    /**
     * <p>ntpDateTo を取得します。
     * @return ntpDateTo
     */
    public UDate getNtpDateTo() {
        return ntpDateTo__;
    }
    /**
     * <p>ntpDateTo をセットします。
     * @param ntpDateTo ntpDateTo
     */
    public void setNtpDateTo(UDate ntpDateTo) {
        ntpDateTo__ = ntpDateTo;
    }
    /**
     * <p>myGrpFlg を取得します。
     * @return myGrpFlg
     */
    public boolean isMyGrpFlg() {
        return myGrpFlg__;
    }
    /**
     * <p>myGrpFlg をセットします。
     * @param myGrpFlg myGrpFlg
     */
    public void setMyGrpFlg(boolean myGrpFlg) {
        myGrpFlg__ = myGrpFlg;
    }
    /**
     * <p>ntpSltGroupSid を取得します。
     * @return ntpSltGroupSid
     */
    public String getNtpSltGroupSid() {
        return ntpSltGroupSid__;
    }
    /**
     * <p>ntpSltGroupSid をセットします。
     * @param ntpSltGroupSid ntpSltGroupSid
     */
    public void setNtpSltGroupSid(String ntpSltGroupSid) {
        ntpSltGroupSid__ = ntpSltGroupSid;
    }
    /**
     * <p>ankenSid を取得します。
     * @return ankenSid
     */
    public int getAnkenSid() {
        return ankenSid__;
    }
    /**
     * <p>ankenSid をセットします。
     * @param ankenSid ankenSid
     */
    public void setAnkenSid(int ankenSid) {
        ankenSid__ = ankenSid;
    }
    /**
     * <p>companySid を取得します。
     * @return companySid
     */
    public int getCompanySid() {
        return companySid__;
    }
    /**
     * <p>companySid をセットします。
     * @param companySid companySid
     */
    public void setCompanySid(int companySid) {
        companySid__ = companySid;
    }
    /**
     * <p>companyBaseSid を取得します。
     * @return companyBaseSid
     */
    public int getCompanyBaseSid() {
        return companyBaseSid__;
    }
    /**
     * <p>companyBaseSid をセットします。
     * @param companyBaseSid companyBaseSid
     */
    public void setCompanyBaseSid(int companyBaseSid) {
        companyBaseSid__ = companyBaseSid;
    }
}
