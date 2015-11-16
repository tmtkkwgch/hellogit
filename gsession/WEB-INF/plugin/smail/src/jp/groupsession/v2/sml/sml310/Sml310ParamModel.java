package jp.groupsession.v2.sml.sml310;

import java.util.List;

import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.model.LabelDataModel;
import jp.groupsession.v2.sml.sml270.Sml270ParamModel;

/**
 * <br>[機  能] ショートメール ラベルの管理画面のパラメータ情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml310ParamModel extends Sml270ParamModel {
    /** ラベル編集モード */
    private int smlLabelCmdMode__ = GSConstSmail.CMDMODE_ADD;
    /** 編集対象ラベルSID */
    private int smlEditLabelId__ = 0;
    /** アカウント名 */
    private String sml310accountName__ = null;
    /** ラベルリスト */
    private List<LabelDataModel> lbnList__ = null;
    /** アカウント */
    private String sml310account__ = null;
    /** 編集対象ラベルSID */
    private String sml310editLabel__ = null;
    /** 並び替え対象のラベル */
    private String[] sml310sortLabel__ = null;
    /** チェックされている並び順 */
    private String sml310SortRadio__ = null;
    /** 表示回数 */
    private int dspCount__ = 0;

    /**
     * <p>lbnList を取得します。
     * @return lbnList
     */
    public List<LabelDataModel> getLbnList() {
        return lbnList__;
    }
    /**
     * <p>lbnList をセットします。
     * @param lbnList lbnList
     */
    public void setLbnList(List<LabelDataModel> lbnList) {
        lbnList__ = lbnList;
    }

    /**
     * <p>dspCount を取得します。
     * @return dspCount
     */
    public int getDspCount() {
        return dspCount__;
    }

    /**
     * <p>dspCount をセットします。
     * @param dspCount dspCount
     */
    public void setDspCount(int dspCount) {
        dspCount__ = dspCount;
    }
    /**
     * <p>smlLabelCmdMode を取得します。
     * @return smlLabelCmdMode
     */
    public int getSmlLabelCmdMode() {
        return smlLabelCmdMode__;
    }
    /**
     * <p>smlLabelCmdMode をセットします。
     * @param smlLabelCmdMode smlLabelCmdMode
     */
    public void setSmlLabelCmdMode(int smlLabelCmdMode) {
        smlLabelCmdMode__ = smlLabelCmdMode;
    }
    /**
     * <p>smlEditLabelId を取得します。
     * @return smlEditLabelId
     */
    public int getSmlEditLabelId() {
        return smlEditLabelId__;
    }
    /**
     * <p>smlEditLabelId をセットします。
     * @param smlEditLabelId smlEditLabelId
     */
    public void setSmlEditLabelId(int smlEditLabelId) {
        smlEditLabelId__ = smlEditLabelId;
    }
    /**
     * <p>sml310account を取得します。
     * @return sml310account
     */
    public String getSml310account() {
        return sml310account__;
    }
    /**
     * <p>sml310account をセットします。
     * @param sml310account sml310account
     */
    public void setSml310account(String sml310account) {
        sml310account__ = sml310account;
    }
    /**
     * <p>sml310editLabel を取得します。
     * @return sml310editLabel
     */
    public String getSml310editLabel() {
        return sml310editLabel__;
    }
    /**
     * <p>sml310editLabel をセットします。
     * @param sml310editLabel sml310editLabel
     */
    public void setSml310editLabel(String sml310editLabel) {
        sml310editLabel__ = sml310editLabel;
    }
    /**
     * <p>sml310sortLabel を取得します。
     * @return sml310sortLabel
     */
    public String[] getSml310sortLabel() {
        return sml310sortLabel__;
    }
    /**
     * <p>sml310sortLabel をセットします。
     * @param sml310sortLabel sml310sortLabel
     */
    public void setSml310sortLabel(String[] sml310sortLabel) {
        sml310sortLabel__ = sml310sortLabel;
    }
    /**
     * <p>sml310SortRadio を取得します。
     * @return sml310SortRadio
     */
    public String getSml310SortRadio() {
        return sml310SortRadio__;
    }
    /**
     * <p>sml310SortRadio をセットします。
     * @param sml310SortRadio sml310SortRadio
     */
    public void setSml310SortRadio(String sml310SortRadio) {
        sml310SortRadio__ = sml310SortRadio;
    }
    /**
     * <p>sml310accountName を取得します。
     * @return sml310accountName
     */
    public String getSml310accountName() {
        return sml310accountName__;
    }
    /**
     * <p>sml310accountName をセットします。
     * @param sml310accountName sml310accountName
     */
    public void setSml310accountName(String sml310accountName) {
        sml310accountName__ = sml310accountName;
    }

}