package jp.groupsession.v2.prj.prj090;

import java.util.List;

import jp.groupsession.v2.prj.prj080.Prj080ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] プロジェクト管理 個人設定 表示件数設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj090ParamModel extends Prj080ParamModel {

    //入力項目
    /** プロジェクト表示件数 */
    private int prj090prjViewCnt__ = 10;
    /** TODO表示件数 */
    private int prj090todoViewCnt__ = 10;

    //表示項目
    /** 表示件数コンボ */
    private List<LabelValueBean> cntList__;

    /**
     * <p>prj090prjViewCnt を取得します。
     * @return prj090prjViewCnt
     */
    public int getPrj090prjViewCnt() {
        return prj090prjViewCnt__;
    }
    /**
     * <p>prj090prjViewCnt をセットします。
     * @param prj090prjViewCnt prj090prjViewCnt
     */
    public void setPrj090prjViewCnt(int prj090prjViewCnt) {
        prj090prjViewCnt__ = prj090prjViewCnt;
    }
    /**
     * <p>prj090todoViewCnt を取得します。
     * @return prj090todoViewCnt
     */
    public int getPrj090todoViewCnt() {
        return prj090todoViewCnt__;
    }
    /**
     * <p>prj090todoViewCnt をセットします。
     * @param prj090todoViewCnt prj090todoViewCnt
     */
    public void setPrj090todoViewCnt(int prj090todoViewCnt) {
        prj090todoViewCnt__ = prj090todoViewCnt;
    }
    /**
     * <p>cntList を取得します。
     * @return cntList
     */
    public List<LabelValueBean> getCntList() {
        return cntList__;
    }
    /**
     * <p>cntList をセットします。
     * @param cntList cntList
     */
    public void setCntList(List<LabelValueBean> cntList) {
        cntList__ = cntList;
    }

}
