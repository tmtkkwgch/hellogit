package jp.groupsession.v2.rng.rng090kn;

import java.util.List;

import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rng.rng090.Rng090Form;

/**
 * <br>[機  能] 稟議 テンプレート登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng090knForm extends Rng090Form {

    /** 添付ファイルID */
    private String rng090knTmpFileId__ = "";

    /** 表示用の内容 */
    private String rng090knViewContent__ = "";
    /** 承認経路 ユーザ名 */
    private List<CmnUsrmInfModel> rng090knApprUserList__ = null;
    /** 最終確認 */
    private List<CmnUsrmInfModel> rng090knConfirmUserList__ = null;

    /** カテゴリ名 */
    private String rng090knCatName__ = "";

    /**
     * <p>rng090knViewContent を取得します。
     * @return rng090knViewContent
     */
    public String getRng090knViewContent() {
        return rng090knViewContent__;
    }

    /**
     * <p>rng090knViewContent をセットします。
     * @param rng090knViewContent rng090knViewContent
     */
    public void setRng090knViewContent(String rng090knViewContent) {
        rng090knViewContent__ = rng090knViewContent;
    }

    /**
     * <p>rng090knTmpFileId を取得します。
     * @return rng090knTmpFileId
     */
    public String getRng090knTmpFileId() {
        return rng090knTmpFileId__;
    }

    /**
     * <p>rng090knTmpFileId をセットします。
     * @param rng090knTmpFileId rng090knTmpFileId
     */
    public void setRng090knTmpFileId(String rng090knTmpFileId) {
        rng090knTmpFileId__ = rng090knTmpFileId;
    }

    /**
     * <p>rng090knApprUserList を取得します。
     * @return rng090knApprUserList
     */
    public List<CmnUsrmInfModel> getRng090knApprUserList() {
        return rng090knApprUserList__;
    }

    /**
     * <p>rng090knApprUserList をセットします。
     * @param rng090knApprUserList rng090knApprUserList
     */
    public void setRng090knApprUserList(List<CmnUsrmInfModel> rng090knApprUserList) {
        rng090knApprUserList__ = rng090knApprUserList;
    }

    /**
     * <p>rng090knConfirmUserList を取得します。
     * @return rng090knConfirmUserList
     */
    public List<CmnUsrmInfModel> getRng090knConfirmUserList() {
        return rng090knConfirmUserList__;
    }

    /**
     * <p>rng090knConfirmUserList をセットします。
     * @param rng090knConfirmUserList rng090knConfirmUserList
     */
    public void setRng090knConfirmUserList(
            List<CmnUsrmInfModel> rng090knConfirmUserList) {
        rng090knConfirmUserList__ = rng090knConfirmUserList;
    }

    /**
     * @return rng090knCatName
     */
    public String getRng090knCatName() {
        return rng090knCatName__;
    }

    /**
     * @param rng090knCatName 設定する rng090knCatName
     */
    public void setRng090knCatName(String rng090knCatName) {
        rng090knCatName__ = rng090knCatName;
    }
}
