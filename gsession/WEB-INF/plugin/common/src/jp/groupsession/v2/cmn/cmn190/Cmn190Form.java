package jp.groupsession.v2.cmn.cmn190;

import jp.groupsession.v2.cmn.cmn150.Cmn150Form;

/**
 * <br>[機  能] 今日は何の日？(メイン)のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn190Form extends Cmn150Form {

    /** 今日の日付(MMdd形式) */
    private String cmn190dateStr__ = null;
    /** 記念日 日別 URL */
    private String cmn190dailyUrl__ = null;
    /** 記念日 日別(GS表示用) URL */
    private String cmn190gadgetUrl__ = null;

    /**
     * <p>cmn190dailyUrl を取得します。
     * @return cmn190dailyUrl
     */
    public String getCmn190dailyUrl() {
        return cmn190dailyUrl__;
    }
    /**
     * <p>cmn190dailyUrl をセットします。
     * @param cmn190dailyUrl cmn190dailyUrl
     */
    public void setCmn190dailyUrl(String cmn190dailyUrl) {
        cmn190dailyUrl__ = cmn190dailyUrl;
    }
    /**
     * <p>cmn190dateStr を取得します。
     * @return cmn190dateStr
     */
    public String getCmn190dateStr() {
        return cmn190dateStr__;
    }
    /**
     * <p>cmn190dateStr をセットします。
     * @param cmn190dateStr cmn190dateStr
     */
    public void setCmn190dateStr(String cmn190dateStr) {
        cmn190dateStr__ = cmn190dateStr;
    }
    /**
     * <p>cmn190gadgetUrl を取得します。
     * @return cmn190gadgetUrl
     */
    public String getCmn190gadgetUrl() {
        return cmn190gadgetUrl__;
    }
    /**
     * <p>cmn190gadgetUrl をセットします。
     * @param cmn190gadgetUrl cmn190gadgetUrl
     */
    public void setCmn190gadgetUrl(String cmn190gadgetUrl) {
        cmn190gadgetUrl__ = cmn190gadgetUrl;
    }
}
