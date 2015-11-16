package jp.groupsession.v2.enq.enq950;

import java.util.ArrayList;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.GSConstEnquete;
import jp.groupsession.v2.enq.enq900.Enq900Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 管理者設定 アンケート手動削除画面のフォームクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq950Form extends Enq900Form {

    /** 発信 削除区分 */
    private String enq950SendDelKbn__ = null;
    /** 草稿 削除区分 */
    private String enq950DraftDelKbn__ = null;
    /** 年リスト */
    private ArrayList<LabelValueBean> enq950YearLabel__ = null;
    /** 月リスト */
    private ArrayList<LabelValueBean> enq950MonthLabel__ = null;
    /** 発信 選択年 */
    private String enq950SelectSendYear__ = null;
    /** 発信 選択月 */
    private String enq950SelectSendMonth__ = null;
    /** 草稿 選択年 */
    private String enq950SelectDraftYear__ = null;
    /** 草稿 選択月 */
    private String enq950SelectDraftMonth__ = null;

    /**
     * <p>発信 削除区分 を取得します。
     * @return 発信 削除区分
     */
    public String getEnq950SendDelKbn() {
        return enq950SendDelKbn__;
    }

    /**
     * <p>発信 削除区分 をセットします。
     * @param enq950SendDelKbn 発信 削除区分
     */
    public void setEnq950SendDelKbn(String enq950SendDelKbn) {
        enq950SendDelKbn__ = enq950SendDelKbn;
    }

    /**
     * <p>草稿 削除区分 を取得します。
     * @return 草稿 削除区分
     */
    public String getEnq950DraftDelKbn() {
        return enq950DraftDelKbn__;
    }

    /**
     * <p>草稿 削除区分 をセットします。
     * @param enq950DraftDelKbn 草稿 削除区分
     */
    public void setEnq950DraftDelKbn(String enq950DraftDelKbn) {
        enq950DraftDelKbn__ = enq950DraftDelKbn;
    }

    /**
     * <p>年リスト を取得します。
     * @return 年リスト
     */
    public ArrayList<LabelValueBean> getEnq950YearLabel() {
        return enq950YearLabel__;
    }

    /**
     * <p>年リスト をセットします。
     * @param enq950YearLabel 年リスト
     */
    public void setEnq950YearLabel(ArrayList<LabelValueBean> enq950YearLabel) {
        enq950YearLabel__ = enq950YearLabel;
    }

    /**
     * <p>月リスト を取得します。
     * @return 月リスト
     */
    public ArrayList<LabelValueBean> getEnq950MonthLabel() {
        return enq950MonthLabel__;
    }

    /**
     * <p>月リスト をセットします。
     * @param enq950MonthLabel 月リスト
     */
    public void setEnq950MonthLabel(ArrayList<LabelValueBean> enq950MonthLabel) {
        enq950MonthLabel__ = enq950MonthLabel;
    }

    /**
     * <p>発信 選択年 を取得します。
     * @return 発信 選択年
     */
    public String getEnq950SelectSendYear() {
        return enq950SelectSendYear__;
    }

    /**
     * <p>発信 選択年 をセットします。
     * @param enq950SelectSendYear 発信 選択年
     */
    public void setEnq950SelectSendYear(String enq950SelectSendYear) {
        enq950SelectSendYear__ = enq950SelectSendYear;
    }

    /**
     * <p>発信 選択月 を取得します。
     * @return 発信 選択月
     */
    public String getEnq950SelectSendMonth() {
        return enq950SelectSendMonth__;
    }

    /**
     * <p>発信 選択月 をセットします。
     * @param enq950SelectSendMonth 発信 選択月
     */
    public void setEnq950SelectSendMonth(String enq950SelectSendMonth) {
        enq950SelectSendMonth__ = enq950SelectSendMonth;
    }

    /**
     * <p>草稿 選択年 を取得します。
     * @return 草稿 選択年
     */
    public String getEnq950SelectDraftYear() {
        return enq950SelectDraftYear__;
    }

    /**
     * <p>草稿 選択年 をセットします。
     * @param enq950SelectDraftYear 草稿 選択年
     */
    public void setEnq950SelectDraftYear(String enq950SelectDraftYear) {
        enq950SelectDraftYear__ = enq950SelectDraftYear;
    }

    /**
     * <p>草稿 選択月 を取得します。
     * @return 草稿 選択月
     */
    public String getEnq950SelectDraftMonth() {
        return enq950SelectDraftMonth__;
    }

    /**
     * <p>草稿 選択月 をセットします。
     * @param enq950SelectDraftMonth 草稿 選択月
     */
    public void setEnq950SelectDraftMonth(String enq950SelectDraftMonth) {
        enq950SelectDraftMonth__ = enq950SelectDraftMonth;
    }

    /**
     * <br>[機  能] 手動削除時のオペレーションログ内容を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return [発信フォルダ]削除しない or YY年MMヶ月
     *         [草稿フォルダ]表示する or 表示しない
     */
    public String getLogText(RequestModel reqMdl) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String ret = null;

        // 発信フォルダ
        ret = "[" + gsMsg.getMessage("enq.enq950.05") + "]";
        if (enq950SendDelKbn__ == String.valueOf(GSConstEnquete.DELETE_KBN_OFF)) {
            ret += gsMsg.getMessage("cmn.dont.delete") + System.getProperty("line.separator");
        } else {
            ret += gsMsg.getMessageVal0("cmn.year", enq950SelectSendYear__)
                 + gsMsg.getMessageVal0("cmn.months", enq950SelectSendMonth__)
                 + System.getProperty("line.separator");
        }
        // 草稿フォルダ
        ret = "[" + gsMsg.getMessage("enq.enq950.06") + "]";
        if (enq950DraftDelKbn__ == String.valueOf(GSConstEnquete.DELETE_KBN_OFF)) {
            ret += gsMsg.getMessage("cmn.dont.delete") + System.getProperty("line.separator");
        } else {
            ret += gsMsg.getMessageVal0("cmn.year", enq950SelectDraftYear__)
                 + gsMsg.getMessageVal0("cmn.months", enq950SelectDraftMonth__);
        }

       return ret;
    }


}
