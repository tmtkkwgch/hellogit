package jp.groupsession.v2.cmn.config;

/**
 * <br>[機  能] メイン個人設定表示情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class PrivateSettingInfo {

    /** 表示/非表示 */
    private String view__ = null;
    /** 個人メニュー画面URL */
    private String url__ = null;
    /** アイコンURL */
    private String icon__ = null;
    /**
     * <p>url を取得します。
     * @return url
     */
    public String getUrl() {
        return url__;
    }
    /**
     * <p>url をセットします。
     * @param url url
     */
    public void setUrl(String url) {
        url__ = url;
    }
    /**
     * <p>view を取得します。
     * @return view
     */
    public String getView() {
        return view__;
    }
    /**
     * <p>view をセットします。
     * @param view view
     */
    public void setView(String view) {
        view__ = view;
    }
    /**
     * <p>icon を取得します。
     * @return icon
     */
    public String getIcon() {
        return icon__;
    }
    /**
     * <p>icon をセットします。
     * @param icon icon
     */
    public void setIcon(String icon) {
        icon__ = icon;
    }

}
