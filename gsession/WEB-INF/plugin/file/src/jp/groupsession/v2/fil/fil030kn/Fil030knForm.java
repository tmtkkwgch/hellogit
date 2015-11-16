package jp.groupsession.v2.fil.fil030kn;

import jp.groupsession.v2.fil.fil030.Fil030Form;

/**
 * <br>[機  能] キャビネット登録・編集確認画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil030knForm extends Fil030Form {

    /** 表示用 キャビネットの容量上限*/
    private String fil030knDspCapaSize__;
    /** 表示用 キャビネットの備考*/
    private String fil030knDspBiko__;
    /** 添付ファイルのバイナリSID(ダウンロード時) */
    private String fil030knBinSid__ = null;
    /** 添付ファイルのバイナリSID(ダウンロード時) */
    private String fil030knBinSidMark__ = null;

    /**
     * @return fil030knBinSid
     */
    public String getFil030knBinSid() {
        return fil030knBinSid__;
    }
    /**
     * @param fil030knBinSid 設定する fil030knBinSid
     */
    public void setFil030knBinSid(String fil030knBinSid) {
        fil030knBinSid__ = fil030knBinSid;
    }
    /**
     * @return fil030knDspBiko
     */
    public String getFil030knDspBiko() {
        return fil030knDspBiko__;
    }
    /**
     * @param fil030knDspBiko 設定する fil030knDspBiko
     */
    public void setFil030knDspBiko(String fil030knDspBiko) {
        fil030knDspBiko__ = fil030knDspBiko;
    }
    /**
     * @return fil030knDspCapaSize
     */
    public String getFil030knDspCapaSize() {
        return fil030knDspCapaSize__;
    }
    /**
     * @param fil030knDspCapaSize 設定する fil030knDspCapaSize
     */
    public void setFil030knDspCapaSize(String fil030knDspCapaSize) {
        fil030knDspCapaSize__ = fil030knDspCapaSize;
    }
    /**
     * <p>fil030knBinSidMark を取得します。
     * @return fil030knBinSidMark
     */
    public String getFil030knBinSidMark() {
        return fil030knBinSidMark__;
    }
    /**
     * <p>fil030knBinSidMark をセットします。
     * @param fil030knBinSidMark fil030knBinSidMark
     */
    public void setFil030knBinSidMark(String fil030knBinSidMark) {
        fil030knBinSidMark__ = fil030knBinSidMark;
    }
}