package jp.groupsession.v2.ntp.ntp061kn;

import jp.groupsession.v2.ntp.ntp061.Ntp061Form;
/**
 * <br>[機  能] 日報 案件登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp061knForm extends Ntp061Form {
    /** 案件詳細 表示用*/
    private String ntp061knNanSyosai__ = null;

    /**
     * <p>ntp061knNanSyosai を取得します。
     * @return ntp061knNanSyosai
     */
    public String getNtp061knNanSyosai() {
        return ntp061knNanSyosai__;
    }

    /**
     * <p>ntp061knNanSyosai をセットします。
     * @param ntp061knNanSyosai ntp061knNanSyosai
     */
    public void setNtp061knNanSyosai(String ntp061knNanSyosai) {
        ntp061knNanSyosai__ = ntp061knNanSyosai;
    }
}