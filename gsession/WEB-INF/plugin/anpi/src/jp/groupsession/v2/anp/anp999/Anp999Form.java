package jp.groupsession.v2.anp.anp999;

import java.util.ArrayList;

import jp.groupsession.v2.anp.AbstractAnpiMobileForm;


/**
 * <br>[機  能] 安否確認モバイル版のメッセージ画面のForm
 * <br>[解  説]
 * <br>[備  考]
 *
 */
public class Anp999Form extends AbstractAnpiMobileForm {

    /** OKボタンタイプ */
    public static final int TYPE_OK = 0;
    /** OK,Cancelボタンタイプ */
    public static final int TYPE_OKCANCEL = 1;
    /** OK,戻るボタンタイプ */
    public static final int TYPE_OKBACK = 2;
    /** エラー送信ボタンタイプ */
    public static final int TYPE_ERROR_REPORT = 3;
    /** 警告画面 */
    public static final int ICON_WARN = 0;
    /** インフォメーション画面 */
    public static final int ICON_INFO = 1;

    /** リトライ先 */
    private String retryURL__;
    /** リダイレクト遷移先 */
    private String directURL__;
    /** ボタンタイプ */
    private int type__ = -1;
    /** アイコンイメージ */
    private int icon__;
    /** OKボタンURL */
    private String urlOK__;
    /** CANCELボタンURL */
    private String urlCancel__;
    /** 戻るボタンURL */
    private String urlBack__;
    /** エラー報告ボタンURL */
    private String urlReport__;
    /** OKボタンに表示する文字 */
    private String okBtnValue__ = "ＯＫ";
    /** 画面タイトル */
    private String title__;
    /** 画面メッセージ */
    private String message__;
    /** hiddenリスト */
    private ArrayList < Anp999Model > hiddenList__;

    /** トークンフラグ */
    private boolean tokenFlg__ = false;
    /** 二度押し後のURL */
    private String tokenUrl__;

    /** エラーログ（動作環境付加） */
    private String errorLog__;
    /** エラーログ */
    private String errorLogOnly__;

    /** 動作環境 */
    private String detailInfo__;

    /**
     * <p>retryURL を取得します。
     * @return retryURL
     */
    public String getRetryURL() {
        return retryURL__;
    }
    /**
     * <p>retryURL をセットします。
     * @param retryURL retryURL
     */
    public void setRetryURL(String retryURL) {
        retryURL__ = retryURL;
    }
    /**
     * <p>directURLを取得します。
     * @return directURL__を戻します。
     */
    public String getDirectURL() {
        return directURL__;
    }
    /**
     * <p>directURLをセットします。
     * @param directURL directURLを設定。
     */
    public void setDirectURL(String directURL) {
        directURL__ = directURL;
    }
    /**
     * <p>整数パラメータをhiddenListに追加セットします。
     * @param key キー名
     * @param value 値
     */
    public void addHiddenParam(String key, int value) {
        addHiddenParam(key, Integer.toString(value));
    }
    /**
     * <p>整数パラメータをhiddenListに追加セットします。
     * @param key キー名
     * @param value 値
     */
    public void addHiddenParam(String key, long value) {
        addHiddenParam(key, Long.toString(value));
    }
    /**
     * <p>文字列パラメータをhiddenListに追加セットします。
     * @param key キー名
     * @param value 値
     */
    public void addHiddenParam(String key, String value) {
        if (hiddenList__ == null) {
            hiddenList__ = new ArrayList<Anp999Model>();
        }
        Anp999Model hdMdl = new Anp999Model();
        hdMdl.setKey(key);
        hdMdl.setValue(value);
        hiddenList__.add(hdMdl);
    }
    /**
     * <p>整数配列パラメータをhiddenListに追加セットします。
     * @param key キー名
     * @param value 値
     */
    public void addHiddenParam(String key, int[] value) {
        if (value != null) {
            for (int i = 0; i < value.length; i++) {
                addHiddenParam(key, value[i]);
            }
        }
    }
    /**
     * <p>文字列配列パラメータをhiddenListに追加セットします。
     * @param key キー名
     * @param value 値
     */
    public void addHiddenParam(String key, String[] value) {
        if (value != null) {
            for (int i = 0; i < value.length; i++) {
                addHiddenParam(key, value[i]);
            }
        }
    }
    /**
     * <p>文字列リストパラメータをhiddenListに追加セットします。
     * @param key キー名
     * @param value 値
     */
    public void addHiddenParam(String key, ArrayList<String> value) {
        if (value != null) {
            for (int i = 0; i < value.size(); i++) {
                addHiddenParam(key, (String) value.get(i));
            }
        }
    }

    /**
     * <p>hiddenKeyList__を取得します。
     * @return hiddenList__を戻します。
     */
    public ArrayList<Anp999Model> getHiddenList() {
        return hiddenList__;
    }
    /**
     * <p>hiddenKeyList__をセットします。
     * @param hiddenList hiddenKeyList__を設定。
     */
    public void setHiddenList(ArrayList<Anp999Model> hiddenList) {
        hiddenList__ = hiddenList;
    }
    /**
     * <p>icon__を取得します。
     * @return icon__を戻します。
     */
    public int getIcon() {
        return icon__;
    }
    /**
     * <p>icon__をセットします。
     * @param icon icon__を設定。
     */
    public void setIcon(int icon) {
        icon__ = icon;
    }
    /**
     * <p>message__を取得します。
     * @return message__を戻します。
     */
    public String getMessage() {
        return message__;
    }
    /**
     * <p>message__をセットします。
     * @param message message__を設定。
     */
    public void setMessage(String message) {
        message__ = message;
    }
    /**
     * <p>title__を取得します。
     * @return title__を戻します。
     */
    public String getTitle() {
        return title__;
    }
    /**
     * <p>title__をセットします。
     * @param title title__を設定。
     */
    public void setTitle(String title) {
        title__ = title;
    }
    /**
     * <p>type__を取得します。
     * @return type__を戻します。
     */
    public int getType() {
        return type__;
    }
    /**
     * <p>type__をセットします。
     * @param type type__を設定。
     */
    public void setType(int type) {
        type__ = type;
    }
    /**
     * <p>urlCancel__を取得します。
     * @return urlCancel__を戻します。
     */
    public String getUrlCancel() {
        return urlCancel__;
    }
    /**
     * <p>urlCancel__をセットします。
     * @param urlCancel urlCancel__を設定。
     */
    public void setUrlCancel(String urlCancel) {
        urlCancel__ = urlCancel;
    }
    /**
     * <p>urlBack__を取得します。
     * @return urlBack__を戻します。
     */
    public String getUrlBack() {
        return urlBack__;
    }
    /**
     * <p>urlBack__をセットします。
     * @param urlBack urlBack__を設定。
     */
    public void setUrlBack(String urlBack) {
        urlBack__ = urlBack;
    }
    /**
     * <p>urlOK__を取得します。
     * @return urlOK__を戻します。
     */
    public String getUrlOK() {
        return urlOK__;
    }
    /**
     * <p>urlOK__をセットします。
     * @param urlOK urlOK__を設定。
     */
    public void setUrlOK(String urlOK) {
        urlOK__ = urlOK;
    }
    /**
     * <p>tokenFlg__を取得します。
     * @return tokenFlg__を戻します。
     */
    public boolean getTokenFlg() {
        return tokenFlg__;
    }
    /**
     * <p>tokenFlg__をセットします。
     * @param tokenFlg tokenFlg__を設定。
     */
    public void setTokenFlg(boolean tokenFlg) {
        tokenFlg__ = tokenFlg;
    }
    /**
     * <p>tokenUrl__を取得します。
     * @return tokenUrl__を戻します。
     */
    public String getTokenUrl() {
        return tokenUrl__;
    }
    /**
     * <p>tokenUrl__をセットします。
     * @param tokenUrl tokenUrl__を設定。
     */
    public void setTokenUrl(String tokenUrl) {
        tokenUrl__ = tokenUrl;
    }

    /**
     * @return okBtnValue を戻します。
     */
    public String getOkBtnValue() {
        return okBtnValue__;
    }
    /**
     * @param okBtnValue okBtnValue を設定。
     */
    public void setOkBtnValue(String okBtnValue) {
        okBtnValue__ = okBtnValue;
    }
    /**
     * <p>errorLog を取得します。
     * @return errorLog
     */
    public String getErrorLog() {
        return errorLog__;
    }
    /**
     * <p>errorLog をセットします。
     * @param errorLog errorLog
     */
    public void setErrorLog(String errorLog) {
        errorLog__ = errorLog;
    }
    /**
     * <p>urlReport を取得します。
     * @return urlReport
     */
    public String getUrlReport() {
        return urlReport__;
    }
    /**
     * <p>urlReport をセットします。
     * @param urlReport urlReport
     */
    public void setUrlReport(String urlReport) {
        urlReport__ = urlReport;
    }
    /**
     * @return detailInfo
     */
    public String getDetailInfo() {
        return detailInfo__;
    }
    /**
     * @param detailInfo 設定する detailInfo
     */
    public void setDetailInfo(String detailInfo) {
        detailInfo__ = detailInfo;
    }
    /**
     * @return errorLogOnly
     */
    public String getErrorLogOnly() {
        return errorLogOnly__;
    }
    /**
     * @param errorLogOnly 設定する errorLogOnly
     */
    public void setErrorLogOnly(String errorLogOnly) {
        errorLogOnly__ = errorLogOnly;
    }
}