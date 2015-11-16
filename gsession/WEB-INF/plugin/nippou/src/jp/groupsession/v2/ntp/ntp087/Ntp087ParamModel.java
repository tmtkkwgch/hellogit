package jp.groupsession.v2.ntp.ntp087;

import java.sql.SQLException;
import java.util.List;

import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.GSValidateNippou;
import jp.groupsession.v2.ntp.model.NtpLabelValueModel;
import jp.groupsession.v2.ntp.model.NtpTargetModel;
import jp.groupsession.v2.ntp.ntp086.Ntp086ParamModel;

import org.apache.struts.action.ActionErrors;

/**
 * <br>[機  能] 日報 テンプレート登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp087ParamModel extends Ntp086ParamModel {

    /** グループSID: グループ一覧 */
    public static final String GRP_SID_GRPLIST = "-9";

    /** 初期表示フラグ */
    private int ntp087initDspFlg__ = 0;
    /** 処理モード */
    private String ntp087ProcMode__ = GSConstNippou.CMD_ADD;
    /** テンプレート名 */
    private String ntp087TemplateName__ = null;
    /** 表示項目 */
    private String[] ntp087DspItem__ = null;
    /** 表示目標 */
    private String[] ntp087DspTarget__ = null;
    /** 内容 初期値 */
    private String ntp087Detail__ = null;

    /** 初期表示フラグ 0=初期 1=初期済み */
    private String ntp087InitFlg__ = String.valueOf(GSConstNippou.INIT_FLG);

    /** グループSID */
    private String ntp087groupSid__ =   GRP_SID_GRPLIST;
    /** メンバーSID */
    private String[] ntp087memberSid__ = new String[0];

    /** グループ一覧  */
    private List <NtpLabelValueModel> ntp087GroupList__ = null;
    /** 追加済みユーザ一覧 */
    private List <NtpLabelValueModel> ntp087LeftList__ = null;
    /** 追加用ユーザ一覧 */
    private List <NtpLabelValueModel> ntp087RightList__ = null;

    /** 追加済みメンバー(選択) */
    private String[] ntp087SelectLeft__ = new String[0];
    /** 追加用メンバー(選択) */
    private String[] ntp087SelectRight__ = new String[0];

    /** 目標一覧 */
    private List<NtpTargetModel> ntp087TargetList__ = null;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck()
    throws SQLException {
        ActionErrors errors = new ActionErrors();

        //テンプレート名入力チェック
        GSValidateNippou.validateCmnFieldText(errors,
                GSConstNippou.TEXT_TEMPLATE_NAME,
                ntp087TemplateName__,
               "ntp087TemplateName",
                GSConstNippou.MAX_LENGTH_TEMPLATE_NAME,
                true);

        //内容入力チェック
        GSValidateNippou.validateCmnFieldText(errors,
                GSConstNippou.TEXT_NAIYO,
                ntp087Detail__,
               "ntp087ContactName",
                GSConstNippou.MAX_LENGTH_VALUE,
                false);
        return errors;
    }

    /**
     * <p>ntp087TemplateName を取得します。
     * @return ntp087TemplateName
     */
    public String getNtp087TemplateName() {
        return ntp087TemplateName__;
    }

    /**
     * <p>ntp087TemplateName をセットします。
     * @param ntp087TemplateName ntp087TemplateName
     */
    public void setNtp087TemplateName(String ntp087TemplateName) {
        ntp087TemplateName__ = ntp087TemplateName;
    }

    /**
     * <p>ntp087DspItem を取得します。
     * @return ntp087DspItem
     */
    public String[] getNtp087DspItem() {
        return ntp087DspItem__;
    }

    /**
     * <p>ntp087DspItem をセットします。
     * @param ntp087DspItem ntp087DspItem
     */
    public void setNtp087DspItem(String[] ntp087DspItem) {
        ntp087DspItem__ = ntp087DspItem;
    }

    /**
     * <p>ntp087DspTarget を取得します。
     * @return ntp087DspTarget
     */
    public String[] getNtp087DspTarget() {
        return ntp087DspTarget__;
    }

    /**
     * <p>ntp087DspTarget をセットします。
     * @param ntp087DspTarget ntp087DspTarget
     */
    public void setNtp087DspTarget(String[] ntp087DspTarget) {
        ntp087DspTarget__ = ntp087DspTarget;
    }

    /**
     * <p>ntp087Detail を取得します。
     * @return ntp087Detail
     */
    public String getNtp087Detail() {
        return ntp087Detail__;
    }

    /**
     * <p>ntp087Detail をセットします。
     * @param ntp087Detail ntp087Detail
     */
    public void setNtp087Detail(String ntp087Detail) {
        ntp087Detail__ = ntp087Detail;
    }

    /**
     * <p>ntp087groupSid を取得します。
     * @return ntp087groupSid
     */
    public String getNtp087groupSid() {
        return ntp087groupSid__;
    }

    /**
     * <p>ntp087groupSid をセットします。
     * @param ntp087groupSid ntp087groupSid
     */
    public void setNtp087groupSid(String ntp087groupSid) {
        ntp087groupSid__ = ntp087groupSid;
    }

    /**
     * <p>ntp087memberSid を取得します。
     * @return ntp087memberSid
     */
    public String[] getNtp087memberSid() {
        return ntp087memberSid__;
    }

    /**
     * <p>ntp087memberSid をセットします。
     * @param ntp087memberSid ntp087memberSid
     */
    public void setNtp087memberSid(String[] ntp087memberSid) {
        ntp087memberSid__ = ntp087memberSid;
    }

    /**
     * <p>ntp087GroupList を取得します。
     * @return ntp087GroupList
     */
    public List<NtpLabelValueModel> getNtp087GroupList() {
        return ntp087GroupList__;
    }

    /**
     * <p>ntp087GroupList をセットします。
     * @param ntp087GroupList ntp087GroupList
     */
    public void setNtp087GroupList(List<NtpLabelValueModel> ntp087GroupList) {
        ntp087GroupList__ = ntp087GroupList;
    }

    /**
     * <p>ntp087LeftList を取得します。
     * @return ntp087LeftList
     */
    public List<NtpLabelValueModel> getNtp087LeftList() {
        return ntp087LeftList__;
    }

    /**
     * <p>ntp087LeftList をセットします。
     * @param ntp087LeftList ntp087LeftList
     */
    public void setNtp087LeftList(List<NtpLabelValueModel> ntp087LeftList) {
        ntp087LeftList__ = ntp087LeftList;
    }

    /**
     * <p>ntp087RightList を取得します。
     * @return ntp087RightList
     */
    public List<NtpLabelValueModel> getNtp087RightList() {
        return ntp087RightList__;
    }

    /**
     * <p>ntp087RightList をセットします。
     * @param ntp087RightList ntp087RightList
     */
    public void setNtp087RightList(List<NtpLabelValueModel> ntp087RightList) {
        ntp087RightList__ = ntp087RightList;
    }

    /**
     * <p>ntp087SelectLeft を取得します。
     * @return ntp087SelectLeft
     */
    public String[] getNtp087SelectLeft() {
        return ntp087SelectLeft__;
    }

    /**
     * <p>ntp087SelectLeft をセットします。
     * @param ntp087SelectLeft ntp087SelectLeft
     */
    public void setNtp087SelectLeft(String[] ntp087SelectLeft) {
        ntp087SelectLeft__ = ntp087SelectLeft;
    }

    /**
     * <p>ntp087SelectRight を取得します。
     * @return ntp087SelectRight
     */
    public String[] getNtp087SelectRight() {
        return ntp087SelectRight__;
    }

    /**
     * <p>ntp087SelectRight をセットします。
     * @param ntp087SelectRight ntp087SelectRight
     */
    public void setNtp087SelectRight(String[] ntp087SelectRight) {
        ntp087SelectRight__ = ntp087SelectRight;
    }

    /**
     * <p>ntp087InitFlg を取得します。
     * @return ntp087InitFlg
     */
    public String getNtp087InitFlg() {
        return ntp087InitFlg__;
    }

    /**
     * <p>ntp087InitFlg をセットします。
     * @param ntp087InitFlg ntp087InitFlg
     */
    public void setNtp087InitFlg(String ntp087InitFlg) {
        ntp087InitFlg__ = ntp087InitFlg;
    }

    /**
     * <p>ntp087ProcMode を取得します。
     * @return ntp087ProcMode
     */
    public String getNtp087ProcMode() {
        return ntp087ProcMode__;
    }

    /**
     * <p>ntp087ProcMode をセットします。
     * @param ntp087ProcMode ntp087ProcMode
     */
    public void setNtp087ProcMode(String ntp087ProcMode) {
        ntp087ProcMode__ = ntp087ProcMode;
    }

    /**
     * <p>ntp087TargetList を取得します。
     * @return ntp087TargetList
     */
    public List<NtpTargetModel> getNtp087TargetList() {
        return ntp087TargetList__;
    }

    /**
     * <p>ntp087TargetList をセットします。
     * @param ntp087TargetList ntp087TargetList
     */
    public void setNtp087TargetList(List<NtpTargetModel> ntp087TargetList) {
        ntp087TargetList__ = ntp087TargetList;
    }

    /**
     * <p>ntp087initDspFlg を取得します。
     * @return ntp087initDspFlg
     */
    public int getNtp087initDspFlg() {
        return ntp087initDspFlg__;
    }

    /**
     * <p>ntp087initDspFlg をセットします。
     * @param ntp087initDspFlg ntp087initDspFlg
     */
    public void setNtp087initDspFlg(int ntp087initDspFlg) {
        ntp087initDspFlg__ = ntp087initDspFlg;
    }

}
