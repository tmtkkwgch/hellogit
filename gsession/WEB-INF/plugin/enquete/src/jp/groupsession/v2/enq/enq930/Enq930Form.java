package jp.groupsession.v2.enq.enq930;

import java.util.ArrayList;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.enq900.Enq900Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 管理者設定 表示設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq930Form extends Enq900Form {

    /** 表示区分 */
    private int enq930DspKbn__ = 0;
    /** 表示件数選択値 */
    private int enq930SelectCnt__ = 0;
    /** 表示件数一覧 */
    private ArrayList<LabelValueBean> enq930CntListLabel__ = null;

    /**
     * <p>表示区分 を取得します。
     * @return 表示区分
     */
    public int getEnq930DspKbn() {
        return enq930DspKbn__;
    }
    /**
     * <p>表示区分 をセットします。
     * @param enq930DspKbn 表示区分
     */
    public void setEnq930DspKbn(int enq930DspKbn) {
        enq930DspKbn__ = enq930DspKbn;
    }
    /**
     * <p>表示件数選択値 を取得します。
     * @return 表示件数選択値
     */
    public int getEnq930SelectCnt() {
        return enq930SelectCnt__;
    }
    /**
     * <p>表示件数選択値 をセットします。
     * @param enq930SelectCnt 表示件数選択値
     */
    public void setEnq930SelectCnt(int enq930SelectCnt) {
        enq930SelectCnt__ = enq930SelectCnt;
    }
    /**
     * <p>表示件数一覧 を取得します。
     * @return 表示件数一覧
     */
    public ArrayList<LabelValueBean> getEnq930CntListLabel() {
        return enq930CntListLabel__;
    }
    /**
     * <p>表示件数一覧 をセットします。
     * @param enq930CntList 表示件数一覧
     */
    public void setEnq930CntListLabel(ArrayList<LabelValueBean> enq930CntList) {
        enq930CntListLabel__ = enq930CntList;
    }

    /**
     * <br>[機  能] 表示設定更新時のオペレーションログ内容を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return [表示区分]管理者が設定する or 各ユーザが設定する
     *         [一覧表示件数]XXX
     */
    public String getLogText(RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String ret = "[" + gsMsg.getMessage("enq.enq930.01") + "]";
        if (enq930DspKbn__ == GSConstEnquete.CONF_LIST_USE_LIMIT) {
            ret += gsMsg.getMessage("cmn.set.the.admin") + System.getProperty("line.separator");
            ret += "[" + gsMsg.getMessage("enq.enq930.02") +  "]" + getEnq930SelectCnt();
        } else {
            ret += gsMsg.getMessage("cmn.set.eachuser");
        }

        return ret;
    }
}
