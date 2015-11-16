package jp.groupsession.v2.bbs.bbs030kn;

import java.util.ArrayList;

import jp.groupsession.v2.bbs.bbs030.Bbs030ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 フォーラム登録確認画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs030knParamModel extends Bbs030ParamModel {
    /** コメント(表示用) */
    private String bbs030viewcomment__ = null;
    /** 編集メンバーの名前一覧 */
    private ArrayList<LabelValueBean> bbs030knMemNameList__ = null;
    /** 閲覧メンバーの名前一覧 */
    private ArrayList<LabelValueBean> bbs030knMemNameListRead__ = null;
    /** 管理者メンバーの名前一覧 */
    private ArrayList<LabelValueBean> bbs030knMemNameListAdm__ = null;
    /** スレッドテンプレート(表示用) */
    private String bbs030viewTemplate__ = null;

    /**
     * @return bbs030viewcomment を戻します。
     */
    public String getBbs030viewcomment() {
        return bbs030viewcomment__;
    }

    /**
     * @param bbs030viewcomment 設定する bbs030viewcomment。
     */
    public void setBbs030viewcomment(String bbs030viewcomment) {
        bbs030viewcomment__ = bbs030viewcomment;
    }

    /**
     * @return bbs030knMemNameList を戻します。
     */
    public ArrayList<LabelValueBean> getBbs030knMemNameList() {
        return bbs030knMemNameList__;
    }

    /**
     * @param bbs030knMemNameList 設定する bbs030knMemNameList。
     */
    public void setBbs030knMemNameList(ArrayList<LabelValueBean> bbs030knMemNameList) {
        bbs030knMemNameList__ = bbs030knMemNameList;
    }

    /**
     * <p>bbs030knMemNameListRead を取得します。
     * @return bbs030knMemNameListRead
     */
    public ArrayList<LabelValueBean> getBbs030knMemNameListRead() {
        return bbs030knMemNameListRead__;
    }

    /**
     * <p>bbs030knMemNameListRead をセットします。
     * @param bbs030knMemNameListRead bbs030knMemNameListRead
     */
    public void setBbs030knMemNameListRead(
            ArrayList<LabelValueBean> bbs030knMemNameListRead) {
        bbs030knMemNameListRead__ = bbs030knMemNameListRead;
    }

    /**
     * <p>bbs030knMemNameListAdm を取得します。
     * @return bbs030knMemNameListAdm
     */
    public ArrayList<LabelValueBean> getBbs030knMemNameListAdm() {
        return bbs030knMemNameListAdm__;
    }

    /**
     * <p>bbs030knMemNameListAdm をセットします。
     * @param bbs030knMemNameListAdm bbs030knMemNameListAdm
     */
    public void setBbs030knMemNameListAdm(
            ArrayList<LabelValueBean> bbs030knMemNameListAdm) {
        bbs030knMemNameListAdm__ = bbs030knMemNameListAdm;
    }

    /**
     * <p>bbs030viewTemplate を取得します。
     * @return bbs030viewTemplate
     */
    public String getBbs030viewTemplate() {
        return bbs030viewTemplate__;
    }

    /**
     * <p>bbs030viewTemplate をセットします。
     * @param bbs030viewTemplate bbs030viewTemplate
     */
    public void setBbs030viewTemplate(String bbs030viewTemplate) {
        bbs030viewTemplate__ = bbs030viewTemplate;
    }
}