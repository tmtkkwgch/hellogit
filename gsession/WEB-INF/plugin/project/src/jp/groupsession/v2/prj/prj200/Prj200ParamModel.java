package jp.groupsession.v2.prj.prj200;

import java.util.List;
import jp.groupsession.v2.prj.prj080.Prj080ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] プロジェクト管理 個人設定 プロジェクトメイン初期値設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj200ParamModel extends Prj080ParamModel {

    /** 開始予定 選択値 */
    private String prj200Date__ = null;
    /** 状態 選択値 */
    private String prj200Status__ = null;
    /** メンバー 選択値 */
    private String prj200Member__ = null;

    /** 開始予定 コンボリスト */
    private List<LabelValueBean> prj200DateList__;
    /** 状態 コンボリスト */
    private List<LabelValueBean> prj200StatusList__;
    /** メンバー コンボリスト */
    private List<LabelValueBean> prj200MemberList__;

    /**
     * <p>prj200Date を取得します。
     * @return prj200Date
     */
    public String getPrj200Date() {
        return prj200Date__;
    }
    /**
     * <p>prj200Date をセットします。
     * @param prj200Date prj200Date
     */
    public void setPrj200Date(String prj200Date) {
        prj200Date__ = prj200Date;
    }
    /**
     * <p>prj200Status を取得します。
     * @return prj200Status
     */
    public String getPrj200Status() {
        return prj200Status__;
    }
    /**
     * <p>prj200Status をセットします。
     * @param prj200Status prj200Status
     */
    public void setPrj200Status(String prj200Status) {
        prj200Status__ = prj200Status;
    }
    /**
     * <p>prj200Member を取得します。
     * @return prj200Member
     */
    public String getPrj200Member() {
        return prj200Member__;
    }
    /**
     * <p>prj200Member をセットします。
     * @param prj200Member prj200Member
     */
    public void setPrj200Member(String prj200Member) {
        prj200Member__ = prj200Member;
    }
    /**
     * <p>prj200DateList を取得します。
     * @return prj200DateList
     */
    public List<LabelValueBean> getPrj200DateList() {
        return prj200DateList__;
    }
    /**
     * <p>prj200DateList をセットします。
     * @param prj200DateList prj200DateList
     */
    public void setPrj200DateList(List<LabelValueBean> prj200DateList) {
        prj200DateList__ = prj200DateList;
    }
    /**
     * <p>prj200StatusList を取得します。
     * @return prj200StatusList
     */
    public List<LabelValueBean> getPrj200StatusList() {
        return prj200StatusList__;
    }
    /**
     * <p>prj200StatusList をセットします。
     * @param prj200StatusList prj200StatusList
     */
    public void setPrj200StatusList(List<LabelValueBean> prj200StatusList) {
        prj200StatusList__ = prj200StatusList;
    }
    /**
     * <p>prj200MemberList を取得します。
     * @return prj200MemberList
     */
    public List<LabelValueBean> getPrj200MemberList() {
        return prj200MemberList__;
    }
    /**
     * <p>prj200MemberList をセットします。
     * @param prj200MemberList prj200MemberList
     */
    public void setPrj200MemberList(List<LabelValueBean> prj200MemberList) {
        prj200MemberList__ = prj200MemberList;
    }
}