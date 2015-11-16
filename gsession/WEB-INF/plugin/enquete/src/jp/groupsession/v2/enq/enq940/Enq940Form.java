package jp.groupsession.v2.enq.enq940;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.enq900.Enq900Form;
import jp.groupsession.v2.struts.msg.GsMessage;

/**
 * <br>[機  能] 管理者設定 メイン表示設定画面のアクションフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq940Form extends Enq900Form {

    /** メイン表示区分 */
    private int enq940MainDspKbn__ = 0;
    /** メイン表示 */
    private int enq940MainDsp__ = 0;

    /**
     * <p>メイン表示区分 を取得します。
     * @return メイン表示区分
     */
    public int getEnq940MainDspKbn() {
        return enq940MainDspKbn__;
    }
    /**
     * <p>メイン表示区分 をセットします。
     * @param enq940MainDspKbn メイン表示区分
     */
    public void setEnq940MainDspKbn(int enq940MainDspKbn) {
        enq940MainDspKbn__ = enq940MainDspKbn;
    }
    /**
     * <p>メイン表示 を取得します。
     * @return メイン表示
     */
    public int getEnq940MainDsp() {
        return enq940MainDsp__;
    }
    /**
     * <p>メイン表示 をセットします。
     * @param enq940MainDsp メイン表示
     */
    public void setEnq940MainDsp(int enq940MainDsp) {
        enq940MainDsp__ = enq940MainDsp;
    }

    /**
     * <br>[機  能] メイン表示設定更新時のオペレーションログ内容を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return [メイン表示区分]管理者が設定する or 各ユーザが設定する
     *         [メイン表示]表示する or 表示しない
     */
    public String getLogText(RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String ret = "[" + gsMsg.getMessage("enq.enq940.01") + "]";
        if (enq940MainDspKbn__ == GSConstEnquete.CONF_MAIN_DSP_USE_LIMIT) {
            ret += gsMsg.getMessage("cmn.set.the.admin") + System.getProperty("line.separator");
            ret += "[" + gsMsg.getMessage("enq.enq940.02") +  "]";
            if (getEnq940MainDsp() == GSConstEnquete.CONF_MAIN_DSP_OFF) {
                ret += gsMsg.getMessage("cmn.dont.show");
            } else {
                ret += gsMsg.getMessage("cmn.display.ok");
            }
        } else {
            ret += gsMsg.getMessage("cmn.set.eachuser");
        }

        return ret;
    }
}
