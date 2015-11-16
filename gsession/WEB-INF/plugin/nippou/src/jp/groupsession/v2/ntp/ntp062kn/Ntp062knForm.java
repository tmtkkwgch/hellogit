package jp.groupsession.v2.ntp.ntp062kn;

import jp.groupsession.v2.ntp.ntp062.Ntp062Form;

/**
 * <br>[機  能] 日報 インポート確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp062knForm extends Ntp062Form {

    /** 取込みファイル名称 */
    private String ntp062knFileName__ = null;
    /** 登録対象名称 */
    private String impTargetName__ = null;

    /**
     * <p>impTargetName を取得します。
     * @return impTargetName
     */
    public String getImpTargetName() {
        return impTargetName__;
    }

    /**
     * <p>impTargetName をセットします。
     * @param impTargetName impTargetName
     */
    public void setImpTargetName(String impTargetName) {
        impTargetName__ = impTargetName;
    }

    /**
     * <p>ntp062knFileName を取得します。
     * @return ntp062knFileName
     */
    public String getNtp062knFileName() {
        return ntp062knFileName__;
    }

    /**
     * <p>ntp062knFileName をセットします。
     * @param ntp062knFileName ntp062knFileName
     */
    public void setNtp062knFileName(String ntp062knFileName) {
        ntp062knFileName__ = ntp062knFileName;
    }
}