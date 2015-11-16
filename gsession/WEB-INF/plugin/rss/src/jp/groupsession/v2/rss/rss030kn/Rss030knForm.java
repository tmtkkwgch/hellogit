package jp.groupsession.v2.rss.rss030kn;

import jp.groupsession.v2.rss.rss030.Rss030Form;


/**
 * <br>[機  能] RSSリーダー RSS登録確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rss030knForm extends Rss030Form {

    /** メイン表示(表示文字列) */
    private String strMainView__;

    /**
     * <p>strMainView を取得します。
     * @return strMainView
     */
    public String getStrMainView() {
        return strMainView__;
    }

    /**
     * <p>strMainView をセットします。
     * @param strMainView strMainView
     */
    public void setStrMainView(String strMainView) {
        strMainView__ = strMainView;
    }

}
