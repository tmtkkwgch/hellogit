package jp.groupsession.v2.man;


/**
 * <br>[機  能] メインに表示するインフォメッセージの情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class MainInfoMessageModel {

    /** プラグイン */
    public String pluginName__ = null;
    /** メッセージ */
    public String message__ = null;
    /** リンクURL */
    public String linkUrl__ = null;
    /** HTMLエスケープ */
    public boolean htmlEscape__ = true;
    /** POPUP区分 */
    public boolean popupDsp__ = false;
    /** アイコンURL */
    private String icon__ = null;

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon__;
    }
    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        icon__ = icon;
    }
    /**
     * @return the popupDsp
     */
    public boolean isPopupDsp() {
        return popupDsp__;
    }
    /**
     * @param popupDsp the popupDsp to set
     */
    public void setPopupDsp(boolean popupDsp) {
        popupDsp__ = popupDsp;
    }
    /**
     * <p>linkUrl を取得します。
     * @return linkUrl
     */
    public String getLinkUrl() {
        return linkUrl__;
    }
    /**
     * <p>linkUrl をセットします。
     * @param linkUrl linkUrl
     */
    public void setLinkUrl(String linkUrl) {
        linkUrl__ = linkUrl;
    }
    /**
     * <p>message を取得します。
     * @return message
     */
    public String getMessage() {
        return message__;
    }
    /**
     * <p>message をセットします。
     * @param message message
     */
    public void setMessage(String message) {
        message__ = message;
    }
    /**
     * <p>htmlEscape を取得します。
     * @return htmlEscape
     */
    public boolean isHtmlEscape() {
        return htmlEscape__;
    }
    /**
     * <p>htmlEscape をセットします。
     * @param htmlEscape htmlEscape
     */
    public void setHtmlEscape(boolean htmlEscape) {
        htmlEscape__ = htmlEscape;
    }
    /**
     * <p>pluginName を取得します。
     * @return pluginName
     */
    public String getPluginName() {
        return pluginName__;
    }
    /**
     * <p>pluginName をセットします。
     * @param pluginName pluginName
     */
    public void setPluginName(String pluginName) {
        pluginName__ = pluginName;
    }
}
