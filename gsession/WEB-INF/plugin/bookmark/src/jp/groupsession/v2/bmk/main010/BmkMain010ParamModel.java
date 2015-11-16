package jp.groupsession.v2.bmk.main010;

import java.util.List;

import jp.groupsession.v2.bmk.bmk010.Bmk010ParamModel;
import jp.groupsession.v2.bmk.model.BmkBookmarkDataModel;


/**
 * <p>メイン画面個人ブックマーク表示用のフォーム
 * @author JTS
 */
public class BmkMain010ParamModel extends Bmk010ParamModel {
    /** 個人設定(メイン表示設定)**/
    private int dspFlg__ = -1;
    /** 個人ブックマーク一覧 */
    private List<BmkBookmarkDataModel> bmkMain010List__ = null;
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
     * <p>bmkMain010List を取得します。
     * @return bmkMain010List
     */
    public List<BmkBookmarkDataModel> getBmkMain010List() {
        return bmkMain010List__;
    }

    /**
     * <p>bmkMain010List をセットします。
     * @param bmkMain010List bmkMain010List
     */
    public void setBmkMain010List(List<BmkBookmarkDataModel> bmkMain010List) {
        bmkMain010List__ = bmkMain010List;
    }

    /**
     * <p>dspFlg を取得します。
     * @return dspFlg
     */
    public int getDspFlg() {
        return dspFlg__;
    }

    /**
     * <p>dspFlg をセットします。
     * @param dspFlg dspFlg
     */
    public void setDspFlg(int dspFlg) {
        dspFlg__ = dspFlg;
    }
}
