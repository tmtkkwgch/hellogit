package jp.groupsession.v2.enq.enq810;

import java.util.ArrayList;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.enq800.Enq800Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アンケート 個人設定 表示設定画面のアクションフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq810Form extends Enq800Form {

    /** 表示件数選択地 */
    private int enq810SelectCnt__ = 0;
    /** 表示件数一覧 */
    private ArrayList<LabelValueBean> enq810CntListLabel__ = null;

    /**
     * <p>enq810SelectCnt を取得します。
     * @return enq810SelectCnt
     */
    public int getEnq810SelectCnt() {
        return enq810SelectCnt__;
    }
    /**
     * <p>enq810SelectCnt をセットします。
     * @param enq810SelectCnt enq810SelectCnt
     */
    public void setEnq810SelectCnt(int enq810SelectCnt) {
        enq810SelectCnt__ = enq810SelectCnt;
    }
    /**
     * <p>enq810CntListLabel を取得します。
     * @return enq810CntListLabel
     */
    public ArrayList<LabelValueBean> getEnq810CntListLabel() {
        return enq810CntListLabel__;
    }
    /**
     * <p>enq810CntListLabel をセットします。
     * @param enq810CntListLabel enq810CntListLabel
     */
    public void setEnq810CntListLabel(ArrayList<LabelValueBean> enq810CntListLabel) {
        enq810CntListLabel__ = enq810CntListLabel;
    }

    /**
     * <br>[機  能] 表示設定更新時のオペレーションログ内容を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return [一覧表示件数]XXX
     */
    public String getLogText(RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String ret = "[" + gsMsg.getMessage("enq.enq810.01") + "]" + getEnq810SelectCnt();

        return ret;
    }
}
