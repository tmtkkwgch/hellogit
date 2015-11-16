package jp.groupsession.v2.rsv.rsv170;

import java.util.ArrayList;

import jp.groupsession.v2.rsv.rsv140.Rsv140Form;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 個人設定 施設利用状況照会一覧表示設定画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv170Form extends Rsv140Form {

    /** 表示件数 */
    private int rsv170ViewCnt__ = -1;
    /** 表示件数コンボ */
    private ArrayList<LabelValueBean> rsv170DspCntList__ = null;

    /**
     * <p>rsv170DspCntList__ を取得します。
     * @return rsv170DspCntList
     */
    public ArrayList<LabelValueBean> getRsv170DspCntList() {
        return rsv170DspCntList__;
    }
    /**
     * <p>rsv170DspCntList__ をセットします。
     * @param rsv170DspCntList rsv170DspCntList__
     */
    public void setRsv170DspCntList(ArrayList<LabelValueBean> rsv170DspCntList) {
        rsv170DspCntList__ = rsv170DspCntList;
    }
    /**
     * <p>rsv170ViewCnt__ を取得します。
     * @return rsv170ViewCnt
     */
    public int getRsv170ViewCnt() {
        return rsv170ViewCnt__;
    }
    /**
     * <p>rsv170ViewCnt__ をセットします。
     * @param rsv170ViewCnt rsv170ViewCnt__
     */
    public void setRsv170ViewCnt(int rsv170ViewCnt) {
        rsv170ViewCnt__ = rsv170ViewCnt;
    }
}