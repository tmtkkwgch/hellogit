package jp.groupsession.v2.rng.rng061;

import java.util.List;

import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rng.rng060.Rng060ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議 内容テンプレート選択確認画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng061ParamModel extends Rng060ParamModel {

    /** テンプレート名 */
    private String rng061title__;
    /** 稟議タイトル */
    private String rng061rngTitle__;
    /** 内容 */
    private String rng061content__;
    /** 内容 JSP表示用 */
    private String rng061viewContent__;
    /** ダウンロード時添付ファイルＩＤ */
    private String rng061TmpFileId__;
    /** 内容テンプレート種別 */
    private int rng061templateType__ = 0;
    /** ファイルコンボ */
    private List < LabelValueBean > rng061FileLabelList__ = null;
    /** 承認経路 ユーザリスト */
    private List<CmnUsrmInfModel> rng061ApprUserList__ = null;
    /** 最終確認 ユーザリスト */
    private List<CmnUsrmInfModel> rng061ConfirmUserList__ = null;
    /** 承認経路 */
    private String[] rng061apprUser__ = null;
    /** 最終確認 */
    private String[] rng061confirmUser__ = null;
    /** 添付情報 */
    private List<CmnBinfModel> tmpFileList__ = null;
    /**
     * <p>rng061title を取得します。
     * @return rng061title
     */
    public String getRng061title() {
        return rng061title__;
    }
    /**
     * <p>rng061title をセットします。
     * @param rng061title rng061title
     */
    public void setRng061title(String rng061title) {
        rng061title__ = rng061title;
    }
    /**
     * <p>rng061rngTitle を取得します。
     * @return rng061rngTitle
     */
    public String getRng061rngTitle() {
        return rng061rngTitle__;
    }
    /**
     * <p>rng061rngTitle をセットします。
     * @param rng061rngTitle rng061rngTitle
     */
    public void setRng061rngTitle(String rng061rngTitle) {
        rng061rngTitle__ = rng061rngTitle;
    }
    /**
     * <p>rng061content を取得します。
     * @return rng061content
     */
    public String getRng061content() {
        return rng061content__;
    }
    /**
     * <p>rng061content をセットします。
     * @param rng061content rng061content
     */
    public void setRng061content(String rng061content) {
        rng061content__ = rng061content;
    }
    /**
     * <p>rng061viewContent を取得します。
     * @return rng061viewContent
     */
    public String getRng061viewContent() {
        return rng061viewContent__;
    }
    /**
     * <p>rng061viewContent をセットします。
     * @param rng061viewContent rng061viewContent
     */
    public void setRng061viewContent(String rng061viewContent) {
        rng061viewContent__ = rng061viewContent;
    }
    /**
     * <p>rng061TmpFileId を取得します。
     * @return rng061TmpFileId
     */
    public String getRng061TmpFileId() {
        return rng061TmpFileId__;
    }
    /**
     * <p>rng061TmpFileId をセットします。
     * @param rng061TmpFileId rng061TmpFileId
     */
    public void setRng061TmpFileId(String rng061TmpFileId) {
        rng061TmpFileId__ = rng061TmpFileId;
    }
    /**
     * <p>rng061templateType を取得します。
     * @return rng061templateType
     */
    public int getRng061templateType() {
        return rng061templateType__;
    }
    /**
     * <p>rng061templateType をセットします。
     * @param rng061templateType rng061templateType
     */
    public void setRng061templateType(int rng061templateType) {
        rng061templateType__ = rng061templateType;
    }
    /**
     * <p>rng061FileLabelList を取得します。
     * @return rng061FileLabelList
     */
    public List<LabelValueBean> getRng061FileLabelList() {
        return rng061FileLabelList__;
    }
    /**
     * <p>rng061FileLabelList をセットします。
     * @param rng061FileLabelList rng061FileLabelList
     */
    public void setRng061FileLabelList(List<LabelValueBean> rng061FileLabelList) {
        rng061FileLabelList__ = rng061FileLabelList;
    }
    /**
     * <p>rng061ApprUserList を取得します。
     * @return rng061ApprUserList
     */
    public List<CmnUsrmInfModel> getRng061ApprUserList() {
        return rng061ApprUserList__;
    }
    /**
     * <p>rng061ApprUserList をセットします。
     * @param rng061ApprUserList rng061ApprUserList
     */
    public void setRng061ApprUserList(List<CmnUsrmInfModel> rng061ApprUserList) {
        rng061ApprUserList__ = rng061ApprUserList;
    }
    /**
     * <p>rng061ConfirmUserList を取得します。
     * @return rng061ConfirmUserList
     */
    public List<CmnUsrmInfModel> getRng061ConfirmUserList() {
        return rng061ConfirmUserList__;
    }
    /**
     * <p>rng061ConfirmUserList をセットします。
     * @param rng061ConfirmUserList rng061ConfirmUserList
     */
    public void setRng061ConfirmUserList(List<CmnUsrmInfModel> rng061ConfirmUserList) {
        rng061ConfirmUserList__ = rng061ConfirmUserList;
    }
    /**
     * <p>rng061apprUser を取得します。
     * @return rng061apprUser
     */
    public String[] getRng061apprUser() {
        return rng061apprUser__;
    }
    /**
     * <p>rng061apprUser をセットします。
     * @param rng061apprUser rng061apprUser
     */
    public void setRng061apprUser(String[] rng061apprUser) {
        rng061apprUser__ = rng061apprUser;
    }
    /**
     * <p>rng061confirmUser を取得します。
     * @return rng061confirmUser
     */
    public String[] getRng061confirmUser() {
        return rng061confirmUser__;
    }
    /**
     * <p>rng061confirmUser をセットします。
     * @param rng061confirmUser rng061confirmUser
     */
    public void setRng061confirmUser(String[] rng061confirmUser) {
        rng061confirmUser__ = rng061confirmUser;
    }
    /**
     * <p>tmpFileList を取得します。
     * @return tmpFileList
     */
    public List<CmnBinfModel> getTmpFileList() {
        return tmpFileList__;
    }
    /**
     * <p>tmpFileList をセットします。
     * @param tmpFileList tmpFileList
     */
    public void setTmpFileList(List<CmnBinfModel> tmpFileList) {
        tmpFileList__ = tmpFileList;
    }
}