package jp.groupsession.v2.rsv.rsv090kn;

import java.util.List;

import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv090.Rsv090Form;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 [施設登録確認]画面のフォームクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv090knForm extends Rsv090Form {

    /** 備考(表示用) */
    private String rsv090knBiko__ = null;
    /** デフォルト表示画像名 */
    private String rsv090knDefoDspImgName__ = null;
    /** 施設/設備ファイルデータ */
    private List<LabelValueBean> rsv090knSisetuFileLabelList__ = null;
    /** 場所/地図ファイルデータ */
    private List<LabelValueBean> rsv090knPlaceFileLabelList__ = null;
    /** 施設/設備画像登録フラグ */
    private String rsv090knSetImgFlg__ = "0";
    /** 施設SID */
    private int rsv090knSisetuSid__ = 0;
    /** ユーザSID */
    private int rsv090knUsrSid__ = 0;

    /** 場所/地図コメント１ */
    private String rsv090knPlaceFileComment1__ = null;
    /** 場所/地図コメント２ */
    private String rsv090knPlaceFileComment2__ = null;
    /** 場所/地図コメント３ */
    private String rsv090knPlaceFileComment3__ = null;
    /** 場所/地図コメント４ */
    private String rsv090knPlaceFileComment4__ = null;
    /** 場所/地図コメント５ */
    private String rsv090knPlaceFileComment5__ = null;
    /** 場所/地図コメント６ */
    private String rsv090knPlaceFileComment6__ = null;
    /** 場所/地図コメント７ */
    private String rsv090knPlaceFileComment7__ = null;
    /** 場所/地図コメント８ */
    private String rsv090knPlaceFileComment8__ = null;
    /** 場所/地図コメント９ */
    private String rsv090knPlaceFileComment9__ = null;
    /** 場所/地図コメント１０ */
    private String rsv090knPlaceFileComment10__ = null;

    //****************場所/地図コメント表示区分*******************
    /** 場所/地図コメント１ */
    private String rsv090knPlaceFileComment1DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);
    /** 場所/地図コメント２ */
    private String rsv090knPlaceFileComment2DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);
    /** 場所/地図コメント３ */
    private String rsv090knPlaceFileComment3DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);
    /** 場所/地図コメント４ */
    private String rsv090knPlaceFileComment4DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);
    /** 場所/地図コメント５ */
    private String rsv090knPlaceFileComment5DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);
    /** 場所/地図コメント６ */
    private String rsv090knPlaceFileComment6DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);
    /** 場所/地図コメント７ */
    private String rsv090knPlaceFileComment7DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);
    /** 場所/地図コメント８ */
    private String rsv090knPlaceFileComment8DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);
    /** 場所/地図コメント９ */
    private String rsv090knPlaceFileComment9DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);
    /** 場所/地図コメント１０ */
    private String rsv090knPlaceFileComment10DspKbn__
                                        = String.valueOf(GSConstReserve.SISETU_IMG_OFF);

    /**
     * <p>rsv090knSisetuSid を取得します。
     * @return rsv090knSisetuSid
     */
    public int getRsv090knSisetuSid() {
        return rsv090knSisetuSid__;
    }
    /**
     * <p>rsv090knSisetuSid をセットします。
     * @param rsv090knSisetuSid rsv090knSisetuSid
     */
    public void setRsv090knSisetuSid(int rsv090knSisetuSid) {
        rsv090knSisetuSid__ = rsv090knSisetuSid;
    }
    /**
     * <p>rsv090knUsrSid を取得します。
     * @return rsv090knUsrSid
     */
    public int getRsv090knUsrSid() {
        return rsv090knUsrSid__;
    }
    /**
     * <p>rsv090knUsrSid をセットします。
     * @param rsv090knUsrSid rsv090knUsrSid
     */
    public void setRsv090knUsrSid(int rsv090knUsrSid) {
        rsv090knUsrSid__ = rsv090knUsrSid;
    }
    /**
     * <p>rsv090knBiko__ を取得します。
     * @return rsv090knBiko
     */
    public String getRsv090knBiko() {
        return rsv090knBiko__;
    }
    /**
     * <p>rsv090knBiko__ をセットします。
     * @param rsv090knBiko rsv090knBiko__
     */
    public void setRsv090knBiko(String rsv090knBiko) {
        rsv090knBiko__ = rsv090knBiko;
    }
    /**
     * <p>rsv090knPlaceFileLabelList を取得します。
     * @return rsv090knPlaceFileLabelList
     */
    public List<LabelValueBean> getRsv090knPlaceFileLabelList() {
        return rsv090knPlaceFileLabelList__;
    }
    /**
     * <p>rsv090knPlaceFileLabelList をセットします。
     * @param rsv090knPlaceFileLabelList rsv090knPlaceFileLabelList
     */
    public void setRsv090knPlaceFileLabelList(List<LabelValueBean> rsv090knPlaceFileLabelList) {
        rsv090knPlaceFileLabelList__ = rsv090knPlaceFileLabelList;
    }
    /**
     * <p>rsv090knSisetuFileLabelList を取得します。
     * @return rsv090knSisetuFileLabelList
     */
    public List<LabelValueBean> getRsv090knSisetuFileLabelList() {
        return rsv090knSisetuFileLabelList__;
    }
    /**
     * <p>rsv090knSisetuFileLabelList をセットします。
     * @param rsv090knSisetuFileLabelList rsv090knSisetuFileLabelList
     */
    public void setRsv090knSisetuFileLabelList(
            List<LabelValueBean> rsv090knSisetuFileLabelList) {
        rsv090knSisetuFileLabelList__ = rsv090knSisetuFileLabelList;
    }
    /**
     * <p>rsv090knSetImgFlg を取得します。
     * @return rsv090knSetImgFlg
     */
    public String getRsv090knSetImgFlg() {
        return rsv090knSetImgFlg__;
    }
    /**
     * <p>rsv090knSetImgFlg をセットします。
     * @param rsv090knSetImgFlg rsv090knSetImgFlg
     */
    public void setRsv090knSetImgFlg(String rsv090knSetImgFlg) {
        rsv090knSetImgFlg__ = rsv090knSetImgFlg;
    }
    /**
     * <p>rsv090knDefoDspImgName を取得します。
     * @return rsv090knDefoDspImgName
     */
    public String getRsv090knDefoDspImgName() {
        return rsv090knDefoDspImgName__;
    }
    /**
     * <p>rsv090knDefoDspImgName をセットします。
     * @param rsv090knDefoDspImgName rsv090knDefoDspImgName
     */
    public void setRsv090knDefoDspImgName(String rsv090knDefoDspImgName) {
        rsv090knDefoDspImgName__ = rsv090knDefoDspImgName;
    }
    /**
     * <p>rsv090knPlaceFileComment1 を取得します。
     * @return rsv090knPlaceFileComment1
     */
    public String getRsv090knPlaceFileComment1() {
        return rsv090knPlaceFileComment1__;
    }
    /**
     * <p>rsv090knPlaceFileComment1 をセットします。
     * @param rsv090knPlaceFileComment1 rsv090knPlaceFileComment1
     */
    public void setRsv090knPlaceFileComment1(String rsv090knPlaceFileComment1) {
        rsv090knPlaceFileComment1__ = rsv090knPlaceFileComment1;
    }
    /**
     * <p>rsv090knPlaceFileComment10 を取得します。
     * @return rsv090knPlaceFileComment10
     */
    public String getRsv090knPlaceFileComment10() {
        return rsv090knPlaceFileComment10__;
    }
    /**
     * <p>rsv090knPlaceFileComment10 をセットします。
     * @param rsv090knPlaceFileComment10 rsv090knPlaceFileComment10
     */
    public void setRsv090knPlaceFileComment10(String rsv090knPlaceFileComment10) {
        rsv090knPlaceFileComment10__ = rsv090knPlaceFileComment10;
    }
    /**
     * <p>rsv090knPlaceFileComment2 を取得します。
     * @return rsv090knPlaceFileComment2
     */
    public String getRsv090knPlaceFileComment2() {
        return rsv090knPlaceFileComment2__;
    }
    /**
     * <p>rsv090knPlaceFileComment2 をセットします。
     * @param rsv090knPlaceFileComment2 rsv090knPlaceFileComment2
     */
    public void setRsv090knPlaceFileComment2(String rsv090knPlaceFileComment2) {
        rsv090knPlaceFileComment2__ = rsv090knPlaceFileComment2;
    }
    /**
     * <p>rsv090knPlaceFileComment3 を取得します。
     * @return rsv090knPlaceFileComment3
     */
    public String getRsv090knPlaceFileComment3() {
        return rsv090knPlaceFileComment3__;
    }
    /**
     * <p>rsv090knPlaceFileComment3 をセットします。
     * @param rsv090knPlaceFileComment3 rsv090knPlaceFileComment3
     */
    public void setRsv090knPlaceFileComment3(String rsv090knPlaceFileComment3) {
        rsv090knPlaceFileComment3__ = rsv090knPlaceFileComment3;
    }
    /**
     * <p>rsv090knPlaceFileComment4 を取得します。
     * @return rsv090knPlaceFileComment4
     */
    public String getRsv090knPlaceFileComment4() {
        return rsv090knPlaceFileComment4__;
    }
    /**
     * <p>rsv090knPlaceFileComment4 をセットします。
     * @param rsv090knPlaceFileComment4 rsv090knPlaceFileComment4
     */
    public void setRsv090knPlaceFileComment4(String rsv090knPlaceFileComment4) {
        rsv090knPlaceFileComment4__ = rsv090knPlaceFileComment4;
    }
    /**
     * <p>rsv090knPlaceFileComment5 を取得します。
     * @return rsv090knPlaceFileComment5
     */
    public String getRsv090knPlaceFileComment5() {
        return rsv090knPlaceFileComment5__;
    }
    /**
     * <p>rsv090knPlaceFileComment5 をセットします。
     * @param rsv090knPlaceFileComment5 rsv090knPlaceFileComment5
     */
    public void setRsv090knPlaceFileComment5(String rsv090knPlaceFileComment5) {
        rsv090knPlaceFileComment5__ = rsv090knPlaceFileComment5;
    }
    /**
     * <p>rsv090knPlaceFileComment6 を取得します。
     * @return rsv090knPlaceFileComment6
     */
    public String getRsv090knPlaceFileComment6() {
        return rsv090knPlaceFileComment6__;
    }
    /**
     * <p>rsv090knPlaceFileComment6 をセットします。
     * @param rsv090knPlaceFileComment6 rsv090knPlaceFileComment6
     */
    public void setRsv090knPlaceFileComment6(String rsv090knPlaceFileComment6) {
        rsv090knPlaceFileComment6__ = rsv090knPlaceFileComment6;
    }
    /**
     * <p>rsv090knPlaceFileComment7 を取得します。
     * @return rsv090knPlaceFileComment7
     */
    public String getRsv090knPlaceFileComment7() {
        return rsv090knPlaceFileComment7__;
    }
    /**
     * <p>rsv090knPlaceFileComment7 をセットします。
     * @param rsv090knPlaceFileComment7 rsv090knPlaceFileComment7
     */
    public void setRsv090knPlaceFileComment7(String rsv090knPlaceFileComment7) {
        rsv090knPlaceFileComment7__ = rsv090knPlaceFileComment7;
    }
    /**
     * <p>rsv090knPlaceFileComment8 を取得します。
     * @return rsv090knPlaceFileComment8
     */
    public String getRsv090knPlaceFileComment8() {
        return rsv090knPlaceFileComment8__;
    }
    /**
     * <p>rsv090knPlaceFileComment8 をセットします。
     * @param rsv090knPlaceFileComment8 rsv090knPlaceFileComment8
     */
    public void setRsv090knPlaceFileComment8(String rsv090knPlaceFileComment8) {
        rsv090knPlaceFileComment8__ = rsv090knPlaceFileComment8;
    }
    /**
     * <p>rsv090knPlaceFileComment9 を取得します。
     * @return rsv090knPlaceFileComment9
     */
    public String getRsv090knPlaceFileComment9() {
        return rsv090knPlaceFileComment9__;
    }
    /**
     * <p>rsv090knPlaceFileComment9 をセットします。
     * @param rsv090knPlaceFileComment9 rsv090knPlaceFileComment9
     */
    public void setRsv090knPlaceFileComment9(String rsv090knPlaceFileComment9) {
        rsv090knPlaceFileComment9__ = rsv090knPlaceFileComment9;
    }
    /**
     * <p>rsv090knPlaceFileComment10DspKbn を取得します。
     * @return rsv090knPlaceFileComment10DspKbn
     */
    public String getRsv090knPlaceFileComment10DspKbn() {
        return rsv090knPlaceFileComment10DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment10DspKbn をセットします。
     * @param rsv090knPlaceFileComment10DspKbn rsv090knPlaceFileComment10DspKbn
     */
    public void setRsv090knPlaceFileComment10DspKbn(
            String rsv090knPlaceFileComment10DspKbn) {
        rsv090knPlaceFileComment10DspKbn__ = rsv090knPlaceFileComment10DspKbn;
    }
    /**
     * <p>rsv090knPlaceFileComment1DspKbn を取得します。
     * @return rsv090knPlaceFileComment1DspKbn
     */
    public String getRsv090knPlaceFileComment1DspKbn() {
        return rsv090knPlaceFileComment1DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment1DspKbn をセットします。
     * @param rsv090knPlaceFileComment1DspKbn rsv090knPlaceFileComment1DspKbn
     */
    public void setRsv090knPlaceFileComment1DspKbn(
            String rsv090knPlaceFileComment1DspKbn) {
        rsv090knPlaceFileComment1DspKbn__ = rsv090knPlaceFileComment1DspKbn;
    }
    /**
     * <p>rsv090knPlaceFileComment2DspKbn を取得します。
     * @return rsv090knPlaceFileComment2DspKbn
     */
    public String getRsv090knPlaceFileComment2DspKbn() {
        return rsv090knPlaceFileComment2DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment2DspKbn をセットします。
     * @param rsv090knPlaceFileComment2DspKbn rsv090knPlaceFileComment2DspKbn
     */
    public void setRsv090knPlaceFileComment2DspKbn(
            String rsv090knPlaceFileComment2DspKbn) {
        rsv090knPlaceFileComment2DspKbn__ = rsv090knPlaceFileComment2DspKbn;
    }
    /**
     * <p>rsv090knPlaceFileComment3DspKbn を取得します。
     * @return rsv090knPlaceFileComment3DspKbn
     */
    public String getRsv090knPlaceFileComment3DspKbn() {
        return rsv090knPlaceFileComment3DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment3DspKbn をセットします。
     * @param rsv090knPlaceFileComment3DspKbn rsv090knPlaceFileComment3DspKbn
     */
    public void setRsv090knPlaceFileComment3DspKbn(
            String rsv090knPlaceFileComment3DspKbn) {
        rsv090knPlaceFileComment3DspKbn__ = rsv090knPlaceFileComment3DspKbn;
    }
    /**
     * <p>rsv090knPlaceFileComment4DspKbn を取得します。
     * @return rsv090knPlaceFileComment4DspKbn
     */
    public String getRsv090knPlaceFileComment4DspKbn() {
        return rsv090knPlaceFileComment4DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment4DspKbn をセットします。
     * @param rsv090knPlaceFileComment4DspKbn rsv090knPlaceFileComment4DspKbn
     */
    public void setRsv090knPlaceFileComment4DspKbn(
            String rsv090knPlaceFileComment4DspKbn) {
        rsv090knPlaceFileComment4DspKbn__ = rsv090knPlaceFileComment4DspKbn;
    }
    /**
     * <p>rsv090knPlaceFileComment5DspKbn を取得します。
     * @return rsv090knPlaceFileComment5DspKbn
     */
    public String getRsv090knPlaceFileComment5DspKbn() {
        return rsv090knPlaceFileComment5DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment5DspKbn をセットします。
     * @param rsv090knPlaceFileComment5DspKbn rsv090knPlaceFileComment5DspKbn
     */
    public void setRsv090knPlaceFileComment5DspKbn(
            String rsv090knPlaceFileComment5DspKbn) {
        rsv090knPlaceFileComment5DspKbn__ = rsv090knPlaceFileComment5DspKbn;
    }
    /**
     * <p>rsv090knPlaceFileComment6DspKbn を取得します。
     * @return rsv090knPlaceFileComment6DspKbn
     */
    public String getRsv090knPlaceFileComment6DspKbn() {
        return rsv090knPlaceFileComment6DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment6DspKbn をセットします。
     * @param rsv090knPlaceFileComment6DspKbn rsv090knPlaceFileComment6DspKbn
     */
    public void setRsv090knPlaceFileComment6DspKbn(
            String rsv090knPlaceFileComment6DspKbn) {
        rsv090knPlaceFileComment6DspKbn__ = rsv090knPlaceFileComment6DspKbn;
    }
    /**
     * <p>rsv090knPlaceFileComment7DspKbn を取得します。
     * @return rsv090knPlaceFileComment7DspKbn
     */
    public String getRsv090knPlaceFileComment7DspKbn() {
        return rsv090knPlaceFileComment7DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment7DspKbn をセットします。
     * @param rsv090knPlaceFileComment7DspKbn rsv090knPlaceFileComment7DspKbn
     */
    public void setRsv090knPlaceFileComment7DspKbn(
            String rsv090knPlaceFileComment7DspKbn) {
        rsv090knPlaceFileComment7DspKbn__ = rsv090knPlaceFileComment7DspKbn;
    }
    /**
     * <p>rsv090knPlaceFileComment8DspKbn を取得します。
     * @return rsv090knPlaceFileComment8DspKbn
     */
    public String getRsv090knPlaceFileComment8DspKbn() {
        return rsv090knPlaceFileComment8DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment8DspKbn をセットします。
     * @param rsv090knPlaceFileComment8DspKbn rsv090knPlaceFileComment8DspKbn
     */
    public void setRsv090knPlaceFileComment8DspKbn(
            String rsv090knPlaceFileComment8DspKbn) {
        rsv090knPlaceFileComment8DspKbn__ = rsv090knPlaceFileComment8DspKbn;
    }
    /**
     * <p>rsv090knPlaceFileComment9DspKbn を取得します。
     * @return rsv090knPlaceFileComment9DspKbn
     */
    public String getRsv090knPlaceFileComment9DspKbn() {
        return rsv090knPlaceFileComment9DspKbn__;
    }
    /**
     * <p>rsv090knPlaceFileComment9DspKbn をセットします。
     * @param rsv090knPlaceFileComment9DspKbn rsv090knPlaceFileComment9DspKbn
     */
    public void setRsv090knPlaceFileComment9DspKbn(
            String rsv090knPlaceFileComment9DspKbn) {
        rsv090knPlaceFileComment9DspKbn__ = rsv090knPlaceFileComment9DspKbn;
    }
}