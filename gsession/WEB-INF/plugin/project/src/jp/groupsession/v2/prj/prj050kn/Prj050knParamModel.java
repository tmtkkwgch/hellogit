package jp.groupsession.v2.prj.prj050kn;

import jp.groupsession.v2.prj.prj050.Prj050ParamModel;

/**
 * <br>[機  能] TODO登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj050knParamModel extends Prj050ParamModel {

    /** プロジェクト名称 */
    private String projectName__;
    /** カテゴリ名称 */
    private String categoryName__;
    /** 開始予定年月日 */
    private String kaisiYotei__;
    /** 期限年月日 */
    private String kigen__;
    /** 開始実績年月日 */
    private String kaisiJisseki__;
    /** 終了実績年月日 */
    private String syuryoJisseki__;
    /** 警告開始名称 */
    private String keikokuName__;
    /** 重要度 */
    private String juyoName__;
    /** TODO状態 */
    private String statusName__;
    /** TODO進捗率 */
    private int statusRate__;
    /** 状態変更時コメント */
    private String statusCmt__;
    /** 内容 */
    private String naiyo__;

    /** 添付ファイルID(ダウンロード時) */
    private String fileId__;

    /**
     * <p>projectName を取得します。
     * @return projectName
     */
    public String getProjectName() {
        return projectName__;
    }

    /**
     * <p>projectName をセットします。
     * @param projectName projectName
     */
    public void setProjectName(String projectName) {
        projectName__ = projectName;
    }

    /**
     * <p>categoryName を取得します。
     * @return categoryName
     */
    public String getCategoryName() {
        return categoryName__;
    }

    /**
     * <p>categoryName をセットします。
     * @param categoryName categoryName
     */
    public void setCategoryName(String categoryName) {
        categoryName__ = categoryName;
    }

    /**
     * <p>kaisiYotei を取得します。
     * @return kaisiYotei
     */
    public String getKaisiYotei() {
        return kaisiYotei__;
    }

    /**
     * <p>kaisiYotei をセットします。
     * @param kaisiYotei kaisiYotei
     */
    public void setKaisiYotei(String kaisiYotei) {
        kaisiYotei__ = kaisiYotei;
    }

    /**
     * <p>kigen を取得します。
     * @return kigen
     */
    public String getKigen() {
        return kigen__;
    }

    /**
     * <p>kigen をセットします。
     * @param kigen kigen
     */
    public void setKigen(String kigen) {
        kigen__ = kigen;
    }

    /**
     * <p>kaisiJisseki を取得します。
     * @return kaisiJisseki
     */
    public String getKaisiJisseki() {
        return kaisiJisseki__;
    }

    /**
     * <p>kaisiJisseki をセットします。
     * @param kaisiJisseki kaisiJisseki
     */
    public void setKaisiJisseki(String kaisiJisseki) {
        kaisiJisseki__ = kaisiJisseki;
    }

    /**
     * <p>syuryoJisseki を取得します。
     * @return syuryoJisseki
     */
    public String getSyuryoJisseki() {
        return syuryoJisseki__;
    }

    /**
     * <p>syuryoJisseki をセットします。
     * @param syuryoJisseki syuryoJisseki
     */
    public void setSyuryoJisseki(String syuryoJisseki) {
        syuryoJisseki__ = syuryoJisseki;
    }

    /**
     * <p>keikokuName を取得します。
     * @return keikokuName
     */
    public String getKeikokuName() {
        return keikokuName__;
    }

    /**
     * <p>keikokuName をセットします。
     * @param keikokuName keikokuName
     */
    public void setKeikokuName(String keikokuName) {
        keikokuName__ = keikokuName;
    }

    /**
     * <p>juyoName を取得します。
     * @return juyoName
     */
    public String getJuyoName() {
        return juyoName__;
    }

    /**
     * <p>juyoName をセットします。
     * @param juyoName juyoName
     */
    public void setJuyoName(String juyoName) {
        juyoName__ = juyoName;
    }

    /**
     * <p>statusName を取得します。
     * @return statusName
     */
    public String getStatusName() {
        return statusName__;
    }

    /**
     * <p>statusName をセットします。
     * @param statusName statusName
     */
    public void setStatusName(String statusName) {
        statusName__ = statusName;
    }

    /**
     * <p>statusCmt を取得します。
     * @return statusCmt
     */
    public String getStatusCmt() {
        return statusCmt__;
    }

    /**
     * <p>statusCmt をセットします。
     * @param statusCmt statusCmt
     */
    public void setStatusCmt(String statusCmt) {
        statusCmt__ = statusCmt;
    }

    /**
     * <p>naiyo を取得します。
     * @return naiyo
     */
    public String getNaiyo() {
        return naiyo__;
    }

    /**
     * <p>naiyo をセットします。
     * @param naiyo naiyo
     */
    public void setNaiyo(String naiyo) {
        naiyo__ = naiyo;
    }

    /**
     * <p>statusRate を取得します。
     * @return statusRate
     */
    public int getStatusRate() {
        return statusRate__;
    }

    /**
     * <p>statusRate をセットします。
     * @param statusRate statusRate
     */
    public void setStatusRate(int statusRate) {
        statusRate__ = statusRate;
    }

    /**
     * <p>fileId を取得します。
     * @return fileId
     */
    public String getFileId() {
        return fileId__;
    }

    /**
     * <p>fileId をセットします。
     * @param fileId fileId
     */
    public void setFileId(String fileId) {
        fileId__ = fileId;
    }

}
