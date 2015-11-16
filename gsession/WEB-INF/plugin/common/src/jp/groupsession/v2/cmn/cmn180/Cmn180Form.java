package jp.groupsession.v2.cmn.cmn180;

import java.util.List;

import jp.groupsession.v2.cmn.cmn150.Cmn150Form;

/**
 * <br>[機  能] 天気予報(メイン)のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn180Form extends Cmn150Form {

    /** 天気予報 URL */
    private String cmn180Url__ = null;
    /** 週間天気予報 URL */
    private String cmn180WeekUrl__ = null;
    /** 地域情報一覧 */
    private List<Cmn180AreaModel> areaList__ = null;
    /** 日付 */
    private String cmn180Date__ = null;

    /**
     * <p>cmn180Url を取得します。
     * @return cmn180Url
     */
    public String getCmn180Url() {
        return cmn180Url__;
    }
    /**
     * <p>cmn180Url をセットします。
     * @param cmn180Url cmn180Url
     */
    public void setCmn180Url(String cmn180Url) {
        cmn180Url__ = cmn180Url;
    }
    /**
     * <p>cmn180WeekUrl を取得します。
     * @return cmn180WeekUrl
     */
    public String getCmn180WeekUrl() {
        return cmn180WeekUrl__;
    }
    /**
     * <p>cmn180WeekUrl をセットします。
     * @param cmn180WeekUrl cmn180WeekUrl
     */
    public void setCmn180WeekUrl(String cmn180WeekUrl) {
        cmn180WeekUrl__ = cmn180WeekUrl;
    }
    /**
     * <p>areaList を取得します。
     * @return areaList
     */
    public List<Cmn180AreaModel> getAreaList() {
        return areaList__;
    }
    /**
     * <p>cmn180Date を取得します。
     * @return cmn180Date
     */
    public String getCmn180Date() {
        return cmn180Date__;
    }
    /**
     * <p>cmn180Date をセットします。
     * @param cmn180Date cmn180Date
     */
    public void setCmn180Date(String cmn180Date) {
        cmn180Date__ = cmn180Date;
    }
    /**
     * <p>areaList をセットします。
     * @param areaList areaList
     */
    public void setAreaList(List<Cmn180AreaModel> areaList) {
        areaList__ = areaList;
    }

}
