package jp.groupsession.v2.ntp.ntp096;

import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.ntp090.Ntp090ParamModel;

/**
 * <br>[機  能] 日報 スケジュール表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp096ParamModel extends Ntp090ParamModel {

    /** デフォルト表示件数 */
    private int ntp096SchKbn__ = -1;
    /** 初期表示フラグ */
    private int ntp096InitFlg__ = GSConstNippou.INIT_FLG;
    /**
     * <p>ntp096SchKbn を取得します。
     * @return ntp096SchKbn
     */
    public int getNtp096SchKbn() {
        return ntp096SchKbn__;
    }
    /**
     * <p>ntp096SchKbn をセットします。
     * @param ntp096SchKbn ntp096SchKbn
     */
    public void setNtp096SchKbn(int ntp096SchKbn) {
        ntp096SchKbn__ = ntp096SchKbn;
    }
    /**
     * <p>ntp096InitFlg を取得します。
     * @return ntp096InitFlg
     */
    public int getNtp096InitFlg() {
        return ntp096InitFlg__;
    }
    /**
     * <p>ntp096InitFlg をセットします。
     * @param ntp096InitFlg ntp096InitFlg
     */
    public void setNtp096InitFlg(int ntp096InitFlg) {
        ntp096InitFlg__ = ntp096InitFlg;
    }



}
