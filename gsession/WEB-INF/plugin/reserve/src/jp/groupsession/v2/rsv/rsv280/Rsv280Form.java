package jp.groupsession.v2.rsv.rsv280;

import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv040.Rsv040Form;

/**
 * <br>[機  能] 施設予約 管理者設定 施設予約初期値設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv280Form extends Rsv040Form {

    /** 編集権限 区分 */
    private int rsv280EditKbn__ = GSConstReserve.RAC_INIEDITKBN_USER;
    /** 編集権限 */
    private int rsv280Edit__ = GSConstReserve.EDIT_AUTH_NONE;
    /** 初期表示フラグ */
    private int rsv280initFlg__ = 0;

    /**
     * <p>rsv280EditKbn を取得します。
     * @return rsv280EditKbn
     */
    public int getRsv280EditKbn() {
        return rsv280EditKbn__;
    }
    /**
     * <p>rsv280EditKbn をセットします。
     * @param rsv280EditKbn rsv280EditKbn
     */
    public void setRsv280EditKbn(int rsv280EditKbn) {
        rsv280EditKbn__ = rsv280EditKbn;
    }
    /**
     * <p>rsv280Edit を取得します。
     * @return rsv280Edit
     */
    public int getRsv280Edit() {
        return rsv280Edit__;
    }
    /**
     * <p>rsv280Edit をセットします。
     * @param rsv280Edit rsv280Edit
     */
    public void setRsv280Edit(int rsv280Edit) {
        rsv280Edit__ = rsv280Edit;
    }
    /**
     * <p>rsv280initFlg を取得します。
     * @return rsv280initFlg
     */
    public int getRsv280initFlg() {
        return rsv280initFlg__;
    }
    /**
     * <p>rsv280initFlg をセットします。
     * @param rsv280initFlg rsv280initFlg
     */
    public void setRsv280initFlg(int rsv280initFlg) {
        rsv280initFlg__ = rsv280initFlg;
    }
}
