package jp.groupsession.v2.bmk.main020;

import java.util.List;

import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.bmk010.Bmk010ParamModel;
import jp.groupsession.v2.bmk.bmk010.model.Bmk010InfoModel;


/**
 * <br>[機  能] 新着ブックマーク（メイン）画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class BmkMain020ParamModel extends Bmk010ParamModel {

    /** メイン画面表示フラグ */
    private int bmkmain020dspFlg__ = GSConstBookmark.DSP_YES;

    /** 新着ブックマーク一覧 */
    private List<Bmk010InfoModel> bmkmain020NewList__;

    /** ブックマークトップ画面URL */
    private String bmkTopUrl__;

    /**
     * @return bmkTopUrl__ を戻します。
     */
    public String getBmkTopUrl() {
        return bmkTopUrl__;
    }
    /**
     * @param bmkTopUrl 設定する bmkTopUrl__。
     */
    public void setBmkTopUrl(String bmkTopUrl) {
        bmkTopUrl__ = bmkTopUrl;
    }

    /**
     * <p>bmkmain020dspFlg を取得します。
     * @return bmkmain020dspFlg
     */
    public int getBmkmain020dspFlg() {
        return bmkmain020dspFlg__;
    }

    /**
     * <p>bmkmain020dspFlg をセットします。
     * @param bmkmain020dspFlg bmkmain020dspFlg
     */
    public void setBmkmain020dspFlg(int bmkmain020dspFlg) {
        bmkmain020dspFlg__ = bmkmain020dspFlg;
    }

    /**
     * <p>bmkmain020NewList を取得します。
     * @return bmkmain020NewList
     */
    public List<Bmk010InfoModel> getBmkmain020NewList() {
        return bmkmain020NewList__;
    }

    /**
     * <p>bmkmain020NewList をセットします。
     * @param bmkmain020NewList bmkmain020NewList
     */
    public void setBmkmain020NewList(List<Bmk010InfoModel> bmkmain020NewList) {
        bmkmain020NewList__ = bmkmain020NewList;
    }
}