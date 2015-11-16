package jp.groupsession.v2.api.file.move;

import jp.groupsession.v2.cmn.model.AbstractParamModel;

/**
 * <br>[機  能] /file/moveのフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileMoveParamModel extends AbstractParamModel {
    /** ディレクトリSID */
    private String fdrSid__ = null;
    /** ファイル名 */
    private String fdrParentSid__ = null;

    /** キャビネットSID(入力チェック用) */
    private int fcbSid__ = 0;
    /**
     * @return fdrSid
     */
    public String getFdrSid() {
        return fdrSid__;
    }

    /**
     * @param fdrSid 設定する fdrSid
     */
    public void setFdrSid(String fdrSid) {
        fdrSid__ = fdrSid;
    }

    /**
     * <p>fcbSid を取得します。
     * @return fcbSid
     */
    public int getFcbSid() {
        return fcbSid__;
    }

    /**
     * <p>fcbSid をセットします。
     * @param fcbSid fcbSid
     */
    public void setFcbSid(int fcbSid) {
        fcbSid__ = fcbSid;
    }

    /**
     * <p>fdrParentSid を取得します。
     * @return fdrParentSid
     */
    public String getFdrParentSid() {
        return fdrParentSid__;
    }

    /**
     * <p>fdrParentSid をセットします。
     * @param fdrParentSid fdrParentSid
     */
    public void setFdrParentSid(String fdrParentSid) {
        fdrParentSid__ = fdrParentSid;
    }
}
