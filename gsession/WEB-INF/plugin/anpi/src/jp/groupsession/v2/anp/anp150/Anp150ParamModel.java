package jp.groupsession.v2.anp.anp150;

import java.util.List;

import jp.groupsession.v2.anp.anp070.Anp070ParamModel;
import jp.groupsession.v2.anp.model.AnpLabelValueModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 安否確認 管理者設定 緊急連絡先一括設定画面のパラメータモデル
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp150ParamModel extends Anp070ParamModel {

    /** 対象 全ユーザ */
    public static final int TAISYO_ALL = 0;
    /** 対象 ユーザ指定 */
    public static final int TAISYO_SELECT = 1;
    /** アドレス未登録ユーザパス区分 0:エラーとする 1:登録しない */
    private int anp150PassKbn__ = 0;

    /** 対象 */
    private int anp150TargetKbn__;

    /** グループコンボボックス選択SID */
    private String anp150SelectGroupSid__ = null;
    /** 安否確認管理者グループコンボボックスリスト */
    private List<AnpLabelValueModel> anp150GroupLabel__ = null;

    /** 設定対象ユーザ・グループSIDリスト */
    private String[] anp150TargetList__ = null;

    /** 設定対象ユーザ・グループリスト (追加済み 左)*/
    private List <LabelValueBean> anp150TargetLabel__ = null;
    /** 設定非対象ユーザ・グループリスト (追加用 右)*/
    private List <LabelValueBean> anp150NoTargetLabel__ = null;
    /** 設定対象ユーザ・グループ選択SID (追加済み 左)*/
    private String[] anp150SelectTargetSid__ = null;
    /** 設定非対象ユーザ・グループ選択SID （追加用 右）*/
    private String[] anp150SelectNoTargetSid__ = null;

    /** アドレスコンボボックス選択アドレス */
    private String anp150SelectMail__;
    /** アドレスリスト*/
    private List <LabelValueBean> anp150MailLabel__ = null;
    /** その他のアドレス */
    private String anp150OtherMail__;

    /** 上書きフラグ */
    private int anp150UpdateFlg__;

    /**
     * <p>anp150TargetKbn を取得します。
     * @return anp150TargetKbn
     */
    public int getAnp150TargetKbn() {
        return anp150TargetKbn__;
    }

    /**
     * <p>anp150TargetKbn をセットします。
     * @param anp150TargetKbn anp150TargetKbn
     */
    public void setAnp150TargetKbn(int anp150TargetKbn) {
        anp150TargetKbn__ = anp150TargetKbn;
    }

    /**
     * <p>anp150SelectGroupSid を取得します。
     * @return anp150SelectGroupSid
     */
    public String getAnp150SelectGroupSid() {
        return anp150SelectGroupSid__;
    }

    /**
     * <p>anp150SelectGroupSid をセットします。
     * @param anp150SelectGroupSid anp150SelectGroupSid
     */
    public void setAnp150SelectGroupSid(String anp150SelectGroupSid) {
        anp150SelectGroupSid__ = anp150SelectGroupSid;
    }

    /**
     * <p>anp150GroupLabel を取得します。
     * @return anp150GroupLabel
     */
    public List<AnpLabelValueModel> getAnp150GroupLabel() {
        return anp150GroupLabel__;
    }

    /**
     * <p>anp150GroupLabel をセットします。
     * @param anp150GroupLabel anp150GroupLabel
     */
    public void setAnp150GroupLabel(List<AnpLabelValueModel> anp150GroupLabel) {
        anp150GroupLabel__ = anp150GroupLabel;
    }

    /**
     * <p>anp150TargetList を取得します。
     * @return anp150TargetList
     */
    public String[] getAnp150TargetList() {
        return anp150TargetList__;
    }

    /**
     * <p>anp150TargetList をセットします。
     * @param anp150TargetList anp150TargetList
     */
    public void setAnp150TargetList(String[] anp150TargetList) {
        anp150TargetList__ = anp150TargetList;
    }

    /**
     * <p>anp150TargetLabel を取得します。
     * @return anp150TargetLabel
     */
    public List<LabelValueBean> getAnp150TargetLabel() {
        return anp150TargetLabel__;
    }

    /**
     * <p>anp150TargetLabel をセットします。
     * @param anp150TargetLabel anp150TargetLabel
     */
    public void setAnp150TargetLabel(List<LabelValueBean> anp150TargetLabel) {
        anp150TargetLabel__ = anp150TargetLabel;
    }

    /**
     * <p>anp150NoTargetLabel を取得します。
     * @return anp150NoTargetLabel
     */
    public List<LabelValueBean> getAnp150NoTargetLabel() {
        return anp150NoTargetLabel__;
    }

    /**
     * <p>anp150NoTargetLabel をセットします。
     * @param anp150NoTargetLabel anp150NoTargetLabel
     */
    public void setAnp150NoTargetLabel(List<LabelValueBean> anp150NoTargetLabel) {
        anp150NoTargetLabel__ = anp150NoTargetLabel;
    }

    /**
     * <p>anp150SelectTargetSid を取得します。
     * @return anp150SelectTargetSid
     */
    public String[] getAnp150SelectTargetSid() {
        return anp150SelectTargetSid__;
    }

    /**
     * <p>anp150SelectTargetSid をセットします。
     * @param anp150SelectTargetSid anp150SelectTargetSid
     */
    public void setAnp150SelectTargetSid(String[] anp150SelectTargetSid) {
        anp150SelectTargetSid__ = anp150SelectTargetSid;
    }

    /**
     * <p>anp150SelectNoTargetSid を取得します。
     * @return anp150SelectNoTargetSid
     */
    public String[] getAnp150SelectNoTargetSid() {
        return anp150SelectNoTargetSid__;
    }

    /**
     * <p>anp150SelectNoTargetSid をセットします。
     * @param anp150SelectNoTargetSid anp150SelectNoTargetSid
     */
    public void setAnp150SelectNoTargetSid(String[] anp150SelectNoTargetSid) {
        anp150SelectNoTargetSid__ = anp150SelectNoTargetSid;
    }

    /**
     * <p>anp150SelectMail を取得します。
     * @return anp150SelectMail
     */
    public String getAnp150SelectMail() {
        return anp150SelectMail__;
    }

    /**
     * <p>anp150SelectMail をセットします。
     * @param anp150SelectMail anp150SelectMail
     */
    public void setAnp150SelectMail(String anp150SelectMail) {
        anp150SelectMail__ = anp150SelectMail;
    }

    /**
     * <p>anp150MailLabel を取得します。
     * @return anp150MailLabel
     */
    public List<LabelValueBean> getAnp150MailLabel() {
        return anp150MailLabel__;
    }

    /**
     * <p>anp150MailLabel をセットします。
     * @param anp150MailLabel anp150MailLabel
     */
    public void setAnp150MailLabel(List<LabelValueBean> anp150MailLabel) {
        anp150MailLabel__ = anp150MailLabel;
    }

    /**
     * <p>anp150OtherMail を取得します。
     * @return anp150OtherMail
     */
    public String getAnp150OtherMail() {
        return anp150OtherMail__;
    }

    /**
     * <p>anp150OtherMail をセットします。
     * @param anp150OtherMail anp150OtherMail
     */
    public void setAnp150OtherMail(String anp150OtherMail) {
        anp150OtherMail__ = anp150OtherMail;
    }

    /**
     * <p>anp150UpdateFlg を取得します。
     * @return anp150UpdateFlg
     */
    public int getAnp150UpdateFlg() {
        return anp150UpdateFlg__;
    }

    /**
     * <p>anp150UpdateFlg をセットします。
     * @param anp150UpdateFlg anp150UpdateFlg
     */
    public void setAnp150UpdateFlg(int anp150UpdateFlg) {
        anp150UpdateFlg__ = anp150UpdateFlg;
    }

    /**
     * <p>anp150PassKbn を取得します。
     * @return anp150PassKbn
     */
    public int getAnp150PassKbn() {
        return anp150PassKbn__;
    }

    /**
     * <p>anp150PassKbn をセットします。
     * @param anp150PassKbn anp150PassKbn
     */
    public void setAnp150PassKbn(int anp150PassKbn) {
        anp150PassKbn__ = anp150PassKbn;
    }
}