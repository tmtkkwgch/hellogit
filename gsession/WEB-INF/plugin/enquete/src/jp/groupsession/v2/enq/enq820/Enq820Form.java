package jp.groupsession.v2.enq.enq820;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.enq800.Enq800Form;
import jp.groupsession.v2.struts.msg.GsMessage;

/**
 * <br>[機  能] アンケート 個人設定 メイン表示設定画面のアクションフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq820Form extends Enq800Form {

    /** メイン表示 */
    private int enq820MainDsp__ = 0;

    /**
     * <p>メイン表示 を取得します。
     * @return メイン表示
     */
    public int getEnq820MainDsp() {
        return enq820MainDsp__;
    }
    /**
     * <p>メイン表示 をセットします。
     * @param enq820MainDsp メイン表示
     */
    public void setEnq820MainDsp(int enq820MainDsp) {
        enq820MainDsp__ = enq820MainDsp;
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
        String ret = "[" + gsMsg.getMessage("enq.enq820.01") + "]";
        if (getEnq820MainDsp() == GSConstEnquete.CONF_MAIN_DSP_OFF) {
            ret += gsMsg.getMessage("cmn.dont.show");
        } else {
            ret += gsMsg.getMessage("cmn.display.ok");
        }

        return ret;
    }
}
