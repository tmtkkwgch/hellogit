package jp.groupsession.v2.zsk.model;

/**
 * <br>[機  能] 在席状況を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ZaiIndexPlusModel extends ZaiIndexModel {

    /** 在席状況 */
    private int uioStatus__;

    /**
     * <p>uioStatus を取得します。
     * @return uioStatus
     */
    public int getUioStatus() {
        return uioStatus__;
    }

    /**
     * <p>uioStatus をセットします。
     * @param uioStatus uioStatus
     */
    public void setUioStatus(int uioStatus) {
        uioStatus__ = uioStatus;
    }

}
