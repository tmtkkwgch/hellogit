package jp.groupsession.v2.ntp.ntp133kn;
import jp.groupsession.v2.ntp.ntp133.Ntp133ParamModel;

/**
 * <br>[機  能] 日報 商品カテゴリ登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp133knParamModel extends Ntp133ParamModel {

    /** 備考(表示用) */
    private String bikou__;

    /**
     * <p>bikou を取得します。
     * @return bikou
     */
    public String getBikou() {
        return bikou__;
    }

    /**
     * <p>bikou をセットします。
     * @param bikou bikou
     */
    public void setBikou(String bikou) {
        bikou__ = bikou;
    }



}
