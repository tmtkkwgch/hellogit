package jp.groupsession.v2.ntp.ntp095;

import java.util.List;

import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.model.NtpLabelValueModel;
import jp.groupsession.v2.ntp.ntp090.Ntp090Form;

/**
 * <br>[機  能] 日報 ショートメール通知設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp095Form extends Ntp090Form {

    /** グループSID: グループ一覧 */
    public static final String GRP_SID_GRPLIST = "-9";

    /** 日報通知画面区分  0:表示しない 1:表示する*/
    private int ntp095NtpDspKbn__ = 0;
    /** コメント通知画面区分  0:表示しない 1:表示する*/
    private int ntp095CmtDspKbn__ = 0;
    /** いいね通知画面区分  0:表示しない 1:表示する*/
    private int ntp095GoodDspKbn__ = 0;

    /** 日報通知設定 */
    private String ntp095Smail__;
    /** コメント通知設定 */
    private String ntp095CmtSmail__;
    /** いいね通知設定 */
    private String ntp095GoodSmail__;

    /** グループ日報ショートメール通知設定 */
    private String ntp095SmailGroup__;

    /** 共有区分  0:全員 1:所属グループのみ*/
    private int ntp095KyoyuKbn__;

    /** 初期表示フラグ 0=初期 1=初期済み */
    private String ntp095InitFlg__ = String.valueOf(GSConstNippou.INIT_FLG);

    /** グループSID */
    private String ntp095groupSid__ = GRP_SID_GRPLIST;

    /** メンバーSID */
    private String[] ntp095memberSid__ = new String[0];

    /** グループ一覧  */
    private List <NtpLabelValueModel> ntp095GroupList__ = null;
    /** 追加済みユーザ一覧 */
    private List <NtpLabelValueModel> ntp095LeftList__ = null;
    /** 追加用ユーザ一覧 */
    private List <NtpLabelValueModel> ntp095RightList__ = null;

    /** 追加済み管理者(選択) */
    private String[] ntp095SelectLeft__ = new String[0];
    /** 追加用管理者(選択) */
    private String[] ntp095SelectRight__ = new String[0];
    /**
     * <p>ntp095Smail を取得します。
     * @return ntp095Smail
     */
    public String getNtp095Smail() {
        return ntp095Smail__;
    }
    /**
     * <p>ntp095Smail をセットします。
     * @param ntp095Smail ntp095Smail
     */
    public void setNtp095Smail(String ntp095Smail) {
        ntp095Smail__ = ntp095Smail;
    }
    /**
     * <p>ntp095SmailGroup を取得します。
     * @return ntp095SmailGroup
     */
    public String getNtp095SmailGroup() {
        return ntp095SmailGroup__;
    }
    /**
     * <p>ntp095SmailGroup をセットします。
     * @param ntp095SmailGroup ntp095SmailGroup
     */
    public void setNtp095SmailGroup(String ntp095SmailGroup) {
        ntp095SmailGroup__ = ntp095SmailGroup;
    }

    /**
     * <p>ntp095SelectLeft を取得します。
     * @return ntp095SelectLeft
     */
    public String[] getNtp095SelectLeft() {
        return ntp095SelectLeft__;
    }
    /**
     * <p>ntp095SelectLeft をセットします。
     * @param ntp095SelectLeft ntp095SelectLeft
     */
    public void setNtp095SelectLeft(String[] ntp095SelectLeft) {
        ntp095SelectLeft__ = ntp095SelectLeft;
    }
    /**
     * <p>ntp095SelectRight を取得します。
     * @return ntp095SelectRight
     */
    public String[] getNtp095SelectRight() {
        return ntp095SelectRight__;
    }
    /**
     * <p>ntp095SelectRight をセットします。
     * @param ntp095SelectRight ntp095SelectRight
     */
    public void setNtp095SelectRight(String[] ntp095SelectRight) {
        ntp095SelectRight__ = ntp095SelectRight;
    }

    /**
     * <p>ntp095memberSid を取得します。
     * @return ntp095memberSid
     */
    public String[] getNtp095memberSid() {
        return ntp095memberSid__;
    }
    /**
     * <p>ntp095memberSid をセットします。
     * @param ntp095memberSid ntp095memberSid
     */
    public void setNtp095memberSid(String[] ntp095memberSid) {
        ntp095memberSid__ = ntp095memberSid;
    }
    /**
     * <p>ntp095GroupList を取得します。
     * @return ntp095GroupList
     */
    public List<NtpLabelValueModel> getNtp095GroupList() {
        return ntp095GroupList__;
    }
    /**
     * <p>ntp095GroupList をセットします。
     * @param ntp095GroupList ntp095GroupList
     */
    public void setNtp095GroupList(List<NtpLabelValueModel> ntp095GroupList) {
        ntp095GroupList__ = ntp095GroupList;
    }
    /**
     * <p>ntp095groupSid を取得します。
     * @return ntp095groupSid
     */
    public String getNtp095groupSid() {
        return ntp095groupSid__;
    }
    /**
     * <p>ntp095groupSid をセットします。
     * @param ntp095groupSid ntp095groupSid
     */
    public void setNtp095groupSid(String ntp095groupSid) {
        ntp095groupSid__ = ntp095groupSid;
    }
    /**
     * <p>ntp095LeftList を取得します。
     * @return ntp095LeftList
     */
    public List<NtpLabelValueModel> getNtp095LeftList() {
        return ntp095LeftList__;
    }
    /**
     * <p>ntp095LeftList をセットします。
     * @param ntp095LeftList ntp095LeftList
     */
    public void setNtp095LeftList(List<NtpLabelValueModel> ntp095LeftList) {
        ntp095LeftList__ = ntp095LeftList;
    }
    /**
     * <p>ntp095RightList を取得します。
     * @return ntp095RightList
     */
    public List<NtpLabelValueModel> getNtp095RightList() {
        return ntp095RightList__;
    }
    /**
     * <p>ntp095RightList をセットします。
     * @param ntp095RightList ntp095RightList
     */
    public void setNtp095RightList(List<NtpLabelValueModel> ntp095RightList) {
        ntp095RightList__ = ntp095RightList;
    }
    /**
     * <p>grpSidGrplist を取得します。
     * @return grpSidGrplist
     */
    public static String getGrpSidGrplist() {
        return GRP_SID_GRPLIST;
    }
    /**
     * <p>ntp095KyoyuKbn を取得します。
     * @return ntp095KyoyuKbn
     */
    public int getNtp095KyoyuKbn() {
        return ntp095KyoyuKbn__;
    }
    /**
     * <p>ntp095KyoyuKbn をセットします。
     * @param ntp095KyoyuKbn ntp095KyoyuKbn
     */
    public void setNtp095KyoyuKbn(int ntp095KyoyuKbn) {
        ntp095KyoyuKbn__ = ntp095KyoyuKbn;
    }
    /**
     * <p>ntp095InitFlg を取得します。
     * @return ntp095InitFlg
     */
    public String getNtp095InitFlg() {
        return ntp095InitFlg__;
    }
    /**
     * <p>ntp095InitFlg をセットします。
     * @param ntp095InitFlg ntp095InitFlg
     */
    public void setNtp095InitFlg(String ntp095InitFlg) {
        ntp095InitFlg__ = ntp095InitFlg;
    }
    /**
     * <p>ntp095CmtSmail を取得します。
     * @return ntp095CmtSmail
     */
    public String getNtp095CmtSmail() {
        return ntp095CmtSmail__;
    }
    /**
     * <p>ntp095CmtSmail をセットします。
     * @param ntp095CmtSmail ntp095CmtSmail
     */
    public void setNtp095CmtSmail(String ntp095CmtSmail) {
        ntp095CmtSmail__ = ntp095CmtSmail;
    }
    /**
     * <p>ntp095NtpDspKbn を取得します。
     * @return ntp095NtpDspKbn
     */
    public int getNtp095NtpDspKbn() {
        return ntp095NtpDspKbn__;
    }
    /**
     * <p>ntp095NtpDspKbn をセットします。
     * @param ntp095NtpDspKbn ntp095NtpDspKbn
     */
    public void setNtp095NtpDspKbn(int ntp095NtpDspKbn) {
        ntp095NtpDspKbn__ = ntp095NtpDspKbn;
    }
    /**
     * <p>ntp095CmtDspKbn を取得します。
     * @return ntp095CmtDspKbn
     */
    public int getNtp095CmtDspKbn() {
        return ntp095CmtDspKbn__;
    }
    /**
     * <p>ntp095CmtDspKbn をセットします。
     * @param ntp095CmtDspKbn ntp095CmtDspKbn
     */
    public void setNtp095CmtDspKbn(int ntp095CmtDspKbn) {
        ntp095CmtDspKbn__ = ntp095CmtDspKbn;
    }
    /**
     * <p>ntp095GoodDspKbn を取得します。
     * @return ntp095GoodDspKbn
     */
    public int getNtp095GoodDspKbn() {
        return ntp095GoodDspKbn__;
    }
    /**
     * <p>ntp095GoodDspKbn をセットします。
     * @param ntp095GoodDspKbn ntp095GoodDspKbn
     */
    public void setNtp095GoodDspKbn(int ntp095GoodDspKbn) {
        ntp095GoodDspKbn__ = ntp095GoodDspKbn;
    }
    /**
     * <p>ntp095GoodSmail を取得します。
     * @return ntp095GoodSmail
     */
    public String getNtp095GoodSmail() {
        return ntp095GoodSmail__;
    }
    /**
     * <p>ntp095GoodSmail をセットします。
     * @param ntp095GoodSmail ntp095GoodSmail
     */
    public void setNtp095GoodSmail(String ntp095GoodSmail) {
        ntp095GoodSmail__ = ntp095GoodSmail;
    }
}
