package jp.groupsession.v2.wml.wml070;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.wml.GSValidateWebmail;
import jp.groupsession.v2.wml.wml020.Wml020Form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] WEBメール 送受信ログ管理画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml070Form extends Wml020Form {

    /** 検索フラグ */
    private int wml070searchFlg__ = GSConstWebmail.SEARCH_EXECUTE_TRUE;
    /** 件名 */
    private String wml070Title__ = null;
    /** メールアドレス */
    private String wml070Address__ = null;
    /** メールアドレス 送信者 */
    private int wml070AddressFrom__ = GSConstWebmail.ADDRESS_KBN_NO;
    /** メールアドレス 宛先 */
    private int wml070AddressTo__ = GSConstWebmail.ADDRESS_KBN_NO;
    /** 日付 年 */
    private int wml070SendDateYear__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日付 月 */
    private int wml070SendDateMonth__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日付 日 */
    private int wml070SendDateDay__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日付 条件 */
    private int wml070SendDateCondition__ = GSConstWebmail.DATE_KBN_EQUAL;
    /** 日付TO 年 */
    private int wml070SendDateYearTo__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日付TO 月 */
    private int wml070SendDateMonthTo__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日付TO 日 */
    private int wml070SendDateDayTo__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 種別 */
    private int wml070Type__ = GSConstWebmail.TYPE_FREE;

    /** ページ1 */
    private int wml070pageTop__ = 1;
    /** ページ2 */
    private int wml070pageBottom__ = 1;
    /** ページ表示フラグ */
    private boolean wml070pageDspFlg__ = false;
    /** ページコンボ */
    private List<LabelValueBean> pageList__ = null;
    /** 年コンボ */
    private List<LabelValueBean> yearLabel__ = null;
    /** 月コンボ */
    private List<LabelValueBean> monthLabel__ = null;
    /** 日コンボ */
    private List<LabelValueBean> dayLabel__ = null;

    /** 送受信ログ一覧 */
    private List<Wml070MaildataDspModel> wml070SendResvList__ = null;

    /** ソートキー */
    private int wml070sortKey__ = GSConstWebmail.SKEY_DATE;
    /** 並び順 */
    private int wml070order__ = GSConstWebmail.ORDER_DESC;

    /** メッセージNo */
    private int wml080mailNum__ = 0;

    /*-- SVパラメータ start ----------------------------------------------------------*/
    /** 件名 */
    private String wml070svTitle__ = null;
    /** メールアドレス */
    private String wml070svAddress__ = null;
    /** メールアドレス 送信者 */
    private int wml070svAddressFrom__ = GSConstWebmail.ADDRESS_KBN_NO;
    /** メールアドレス 宛先 */
    private int wml070svAddressTo__ = GSConstWebmail.ADDRESS_KBN_NO;
    /** 日時 年 */
    private int wml070svSendDateYear__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日時 月 */
    private int wml070svSendDateMonth__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日時 日 */
    private int wml070svSendDateDay__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日時 条件 */
    private int wml070svSendDateCondition__ = GSConstWebmail.DATE_KBN_EQUAL;
    /** 日付TO 年 */
    private int wml070svSendDateYearTo__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日付TO 月 */
    private int wml070svSendDateMonthTo__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 日付TO 日 */
    private int wml070svSendDateDayTo__ = GSConstWebmail.SELECT_DATECOMBO;
    /** 種別 */
    private int wml070svType__ = GSConstWebmail.TYPE_FREE;
    /*-- SVパラメータ end ----------------------------------------------------------*/

    /**
     * 検索条件パラメータをSAVEフィールドへ移行します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     */
    public void saveSearchParm() {

        wml070svTitle__ = wml070Title__;
        wml070svAddress__ = wml070Address__;
        wml070svAddressFrom__ = wml070AddressFrom__;
        wml070svAddressTo__ = wml070AddressTo__;
        wml070svSendDateYear__ = wml070SendDateYear__;
        wml070svSendDateMonth__ = wml070SendDateMonth__;
        wml070svSendDateDay__ = wml070SendDateDay__;
        wml070svSendDateYearTo__ = wml070SendDateYearTo__;
        wml070svSendDateMonthTo__ = wml070SendDateMonthTo__;
        wml070svSendDateDayTo__ = wml070SendDateDayTo__;
        wml070svSendDateCondition__ = wml070SendDateCondition__;
        wml070svType__ = wml070Type__;
    }

    /**
     * <br>[機  能] 詳細検索画面入力チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateWml070Check(ActionMapping map, HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();

        //件名入力チェック
        GSValidateWebmail.validateTextBoxInput(errors, wml070Title__,
                "wml070Title",
                getInterMessage(req, GSConstWebmail.TEXT_NAME),
                GSConstWebmail.MAXLEN_NAME, false);

        //メールアドレス入力チェック
        if (wml070AddressFrom__ == GSConstWebmail.ADDRESS_KBN_OK
                || wml070AddressTo__ == GSConstWebmail.ADDRESS_KBN_OK) {
            GSValidateWebmail.validateMailSearchInput(errors, wml070Address__,
                    "wml070Address",
                    getInterMessage(req, GSConstWebmail.TEXT_ADDRESS),
                    GSConstWebmail.MAXLEN_ACCOUNT_ADDRESS, true);

        } else if (wml070AddressFrom__ == GSConstWebmail.ADDRESS_KBN_NO
                && wml070AddressTo__ == GSConstWebmail.ADDRESS_KBN_NO
                && wml070Address__ != null
                && wml070Address__.length() != 0) {

            ActionMessage msg = new ActionMessage("error.select.required.text",
                    getInterMessage(req, GSConstWebmail.TEXT_SEARCH_ADDRESS));
            StrutsUtil.addMessage(
                    errors, msg, "noCheckAdKbn" + "error.select.required.text");
        }

        //日付
        boolean toInput = false;
        String dateNameJpn = getInterMessage(req, GSConstWebmail.TEXT_DATE);

        int errCount = errors.size();
        if (wml070SendDateCondition__ == GSConstWebmail.DATE_KBN_DATEAREA) {
            toInput = (wml070SendDateYearTo__ != GSConstWebmail.SELECT_DATECOMBO
                    && wml070SendDateMonthTo__ != GSConstWebmail.SELECT_DATECOMBO
                    && wml070SendDateDayTo__ != GSConstWebmail.SELECT_DATECOMBO);
            dateNameJpn += " From";
        }
        GSValidateWebmail.validateDate(req, errors, "wml070SendDate",
                                    dateNameJpn, wml070SendDateYear__,
                                    wml070SendDateMonth__, wml070SendDateDay__,
                                    toInput);

        if (wml070SendDateCondition__ == GSConstWebmail.DATE_KBN_DATEAREA) {
            boolean fromInput = (wml070SendDateYear__ != GSConstWebmail.SELECT_DATECOMBO
                    && wml070SendDateMonth__ != GSConstWebmail.SELECT_DATECOMBO
                    && wml070SendDateDay__ != GSConstWebmail.SELECT_DATECOMBO);

            GSValidateWebmail.validateDate(req, errors, "wml070SendDateTo",
                    getInterMessage(req, GSConstWebmail.TEXT_DATE) + " To", wml070SendDateYearTo__,
                                    wml070SendDateMonthTo__, wml070SendDateDayTo__, fromInput);

            if (errCount == errors.size() && fromInput && toInput) {

                UDate fromDate = new UDate();
                fromDate.setDate(wml070SendDateYear__,
                                wml070SendDateMonth__,
                                wml070SendDateDay__);
                UDate toDate = new UDate();
                toDate.setDate(wml070SendDateYearTo__,
                            wml070SendDateMonthTo__,
                            wml070SendDateDayTo__);
                if (fromDate.compareDateYMD(toDate) == UDate.SMALL) {
                    ActionMessage msg = new ActionMessage("error.input.comp.text",
                                                        getInterMessage(req, "wml.204"),
                                                        getInterMessage(req, "wml.203"));
                    StrutsUtil.addMessage(errors, msg, "wml070SendDateTo.error.input.comp.text");
                }
            }
        }
        return errors;
    }

    /**
     * <p>wml070searchFlg を取得します。
     * @return wml070searchFlg
     */
    public int getWml070searchFlg() {
        return wml070searchFlg__;
    }

    /**
     * <p>wml070searchFlg をセットします。
     * @param wml070searchFlg wml070searchFlg
     */
    public void setWml070searchFlg(int wml070searchFlg) {
        wml070searchFlg__ = wml070searchFlg;
    }

    /**
     * <p>wml070SendDateDay を取得します。
     * @return wml070SendDateDay
     */
    public int getWml070SendDateDay() {
        return wml070SendDateDay__;
    }

    /**
     * <p>wml070SendDateDay をセットします。
     * @param wml070SendDateDay wml070SendDateDay
     */
    public void setWml070SendDateDay(int wml070SendDateDay) {
        wml070SendDateDay__ = wml070SendDateDay;
    }

    /**
     * <p>wml070SendDateMonth を取得します。
     * @return wml070SendDateMonth
     */
    public int getWml070SendDateMonth() {
        return wml070SendDateMonth__;
    }

    /**
     * <p>wml070SendDateMonth をセットします。
     * @param wml070SendDateMonth wml070SendDateMonth
     */
    public void setWml070SendDateMonth(int wml070SendDateMonth) {
        wml070SendDateMonth__ = wml070SendDateMonth;
    }

    /**
     * <p>wml070SendDateYear を取得します。
     * @return wml070SendDateYear
     */
    public int getWml070SendDateYear() {
        return wml070SendDateYear__;
    }

    /**
     * <p>wml070SendDateYear をセットします。
     * @param wml070SendDateYear wml070SendDateYear
     */
    public void setWml070SendDateYear(int wml070SendDateYear) {
        wml070SendDateYear__ = wml070SendDateYear;
    }

    /**
     * <p>wml070svSendDateCondition を取得します。
     * @return wml070svSendDateCondition
     */
    public int getWml070svSendDateCondition() {
        return wml070svSendDateCondition__;
    }

    /**
     * <p>wml070svSendDateCondition をセットします。
     * @param wml070svSendDateCondition wml070svSendDateCondition
     */
    public void setWml070svSendDateCondition(int wml070svSendDateCondition) {
        wml070svSendDateCondition__ = wml070svSendDateCondition;
    }

    /**
     * <p>wml070svSendDateDay を取得します。
     * @return wml070svSendDateDay
     */
    public int getWml070svSendDateDay() {
        return wml070svSendDateDay__;
    }

    /**
     * <p>wml070svSendDateDay をセットします。
     * @param wml070svSendDateDay wml070svSendDateDay
     */
    public void setWml070svSendDateDay(int wml070svSendDateDay) {
        wml070svSendDateDay__ = wml070svSendDateDay;
    }

    /**
     * <p>wml070svSendDateMonth を取得します。
     * @return wml070svSendDateMonth
     */
    public int getWml070svSendDateMonth() {
        return wml070svSendDateMonth__;
    }

    /**
     * <p>wml070svSendDateMonth をセットします。
     * @param wml070svSendDateMonth wml070svSendDateMonth
     */
    public void setWml070svSendDateMonth(int wml070svSendDateMonth) {
        wml070svSendDateMonth__ = wml070svSendDateMonth;
    }

    /**
     * <p>wml070svSendDateYear を取得します。
     * @return wml070svSendDateYear
     */
    public int getWml070svSendDateYear() {
        return wml070svSendDateYear__;
    }

    /**
     * <p>wml070svSendDateYear をセットします。
     * @param wml070svSendDateYear wml070svSendDateYear
     */
    public void setWml070svSendDateYear(int wml070svSendDateYear) {
        wml070svSendDateYear__ = wml070svSendDateYear;
    }

    /**
     * <p>wml070svTitle を取得します。
     * @return wml070svTitle
     */
    public String getWml070svTitle() {
        return wml070svTitle__;
    }

    /**
     * <p>wml070svTitle をセットします。
     * @param wml070svTitle wml070svTitle
     */
    public void setWml070svTitle(String wml070svTitle) {
        wml070svTitle__ = wml070svTitle;
    }

    /**
     * <p>wml070Title を取得します。
     * @return wml070Title
     */
    public String getWml070Title() {
        return wml070Title__;
    }

    /**
     * <p>wml070Title をセットします。
     * @param wml070Title wml070Title
     */
    public void setWml070Title(String wml070Title) {
        wml070Title__ = wml070Title;
    }

    /**
     * <p>wml070pageDspFlg を取得します。
     * @return wml070pageDspFlg
     */
    public boolean isWml070pageDspFlg() {
        return wml070pageDspFlg__;
    }

    /**
     * <p>wml070pageDspFlg をセットします。
     * @param wml070pageDspFlg wml070pageDspFlg
     */
    public void setWml070pageDspFlg(boolean wml070pageDspFlg) {
        wml070pageDspFlg__ = wml070pageDspFlg;
    }

    /**
     * <p>pageList を取得します。
     * @return pageList
     */
    public List<LabelValueBean> getPageList() {
        return pageList__;
    }

    /**
     * <p>pageList をセットします。
     * @param pageList pageList
     */
    public void setPageList(List<LabelValueBean> pageList) {
        pageList__ = pageList;
    }

    /**
     * <p>wml070SendResvList を取得します。
     * @return wml070SendResvList
     */
    public List<Wml070MaildataDspModel> getWml070SendResvList() {
        return wml070SendResvList__;
    }

    /**
     * <p>wml070SendResvList をセットします。
     * @param wml070SendResvList wml070SendResvList
     */
    public void setWml070SendResvList(List<Wml070MaildataDspModel> wml070SendResvList) {
        wml070SendResvList__ = wml070SendResvList;
    }

    /**
     * <p>dayLabel を取得します。
     * @return dayLabel
     */
    public List<LabelValueBean> getDayLabel() {
        return dayLabel__;
    }

    /**
     * <p>dayLabel をセットします。
     * @param dayLabel dayLabel
     */
    public void setDayLabel(List<LabelValueBean> dayLabel) {
        dayLabel__ = dayLabel;
    }

    /**
     * <p>monthLabel を取得します。
     * @return monthLabel
     */
    public List<LabelValueBean> getMonthLabel() {
        return monthLabel__;
    }

    /**
     * <p>monthLabel をセットします。
     * @param monthLabel monthLabel
     */
    public void setMonthLabel(List<LabelValueBean> monthLabel) {
        monthLabel__ = monthLabel;
    }

    /**
     * <p>yearLabel を取得します。
     * @return yearLabel
     */
    public List<LabelValueBean> getYearLabel() {
        return yearLabel__;
    }

    /**
     * <p>yearLabel をセットします。
     * @param yearLabel yearLabel
     */
    public void setYearLabel(List<LabelValueBean> yearLabel) {
        yearLabel__ = yearLabel;
    }

    /**
     * <p>wml070order を取得します。
     * @return wml070order
     */
    public int getWml070order() {
        return wml070order__;
    }

    /**
     * <p>wml070order をセットします。
     * @param wml070order wml070order
     */
    public void setWml070order(int wml070order) {
        wml070order__ = wml070order;
    }

    /**
     * <p>wml070sortKey を取得します。
     * @return wml070sortKey
     */
    public int getWml070sortKey() {
        return wml070sortKey__;
    }

    /**
     * <p>wml070sortKey をセットします。
     * @param wml070sortKey wml070sortKey
     */
    public void setWml070sortKey(int wml070sortKey) {
        wml070sortKey__ = wml070sortKey;
    }

    /**
     * <p>wml070Address を取得します。
     * @return wml070Address
     */
    public String getWml070Address() {
        return wml070Address__;
    }

    /**
     * <p>wml070Address をセットします。
     * @param wml070Address wml070Address
     */
    public void setWml070Address(String wml070Address) {
        wml070Address__ = wml070Address;
    }

    /**
     * <p>wml070AddressFrom を取得します。
     * @return wml070AddressFrom
     */
    public int getWml070AddressFrom() {
        return wml070AddressFrom__;
    }

    /**
     * <p>wml070AddressFrom をセットします。
     * @param wml070AddressFrom wml070AddressFrom
     */
    public void setWml070AddressFrom(int wml070AddressFrom) {
        wml070AddressFrom__ = wml070AddressFrom;
    }

    /**
     * <p>wml070AddressTo を取得します。
     * @return wml070AddressTo
     */
    public int getWml070AddressTo() {
        return wml070AddressTo__;
    }

    /**
     * <p>wml070AddressTo をセットします。
     * @param wml070AddressTo wml070AddressTo
     */
    public void setWml070AddressTo(int wml070AddressTo) {
        wml070AddressTo__ = wml070AddressTo;
    }

    /**
     * <p>wml070SendDateCondition を取得します。
     * @return wml070SendDateCondition
     */
    public int getWml070SendDateCondition() {
        return wml070SendDateCondition__;
    }

    /**
     * <p>wml070SendDateCondition をセットします。
     * @param wml070SendDateCondition wml070SendDateCondition
     */
    public void setWml070SendDateCondition(int wml070SendDateCondition) {
        wml070SendDateCondition__ = wml070SendDateCondition;
    }

    /**
     * <p>wml070svAddress を取得します。
     * @return wml070svAddress
     */
    public String getWml070svAddress() {
        return wml070svAddress__;
    }

    /**
     * <p>wml070svAddress をセットします。
     * @param wml070svAddress wml070svAddress
     */
    public void setWml070svAddress(String wml070svAddress) {
        wml070svAddress__ = wml070svAddress;
    }

    /**
     * <p>wml070svAddressFrom を取得します。
     * @return wml070svAddressFrom
     */
    public int getWml070svAddressFrom() {
        return wml070svAddressFrom__;
    }

    /**
     * <p>wml070svAddressFrom をセットします。
     * @param wml070svAddressFrom wml070svAddressFrom
     */
    public void setWml070svAddressFrom(int wml070svAddressFrom) {
        wml070svAddressFrom__ = wml070svAddressFrom;
    }

    /**
     * <p>wml070svAddressTo を取得します。
     * @return wml070svAddressTo
     */
    public int getWml070svAddressTo() {
        return wml070svAddressTo__;
    }

    /**
     * <p>wml070svAddressTo をセットします。
     * @param wml070svAddressTo wml070svAddressTo
     */
    public void setWml070svAddressTo(int wml070svAddressTo) {
        wml070svAddressTo__ = wml070svAddressTo;
    }

    /**
     * <p>wml070svType を取得します。
     * @return wml070svType
     */
    public int getWml070svType() {
        return wml070svType__;
    }

    /**
     * <p>wml070svType をセットします。
     * @param wml070svType wml070svType
     */
    public void setWml070svType(int wml070svType) {
        wml070svType__ = wml070svType;
    }

    /**
     * <p>wml070Type を取得します。
     * @return wml070Type
     */
    public int getWml070Type() {
        return wml070Type__;
    }

    /**
     * <p>wml070Type をセットします。
     * @param wml070Type wml070Type
     */
    public void setWml070Type(int wml070Type) {
        wml070Type__ = wml070Type;
    }

    /**
     * <p>wml070pageBottom を取得します。
     * @return wml070pageBottom
     */
    public int getWml070pageBottom() {
        return wml070pageBottom__;
    }

    /**
     * <p>wml070pageBottom をセットします。
     * @param wml070pageBottom wml070pageBottom
     */
    public void setWml070pageBottom(int wml070pageBottom) {
        wml070pageBottom__ = wml070pageBottom;
    }

    /**
     * <p>wml070pageTop を取得します。
     * @return wml070pageTop
     */
    public int getWml070pageTop() {
        return wml070pageTop__;
    }

    /**
     * <p>wml070pageTop をセットします。
     * @param wml070pageTop wml070pageTop
     */
    public void setWml070pageTop(int wml070pageTop) {
        wml070pageTop__ = wml070pageTop;
    }

    /**
     * <p>wml080mailNum を取得します。
     * @return wml080mailNum
     */
    public int getWml080mailNum() {
        return wml080mailNum__;
    }

    /**
     * <p>wml080mailNum をセットします。
     * @param wml080mailNum wml080mailNum
     */
    public void setWml080mailNum(int wml080mailNum) {
        wml080mailNum__ = wml080mailNum;
    }

    /**
     * <p>wml070SendDateDayTo を取得します。
     * @return wml070SendDateDayTo
     */
    public int getWml070SendDateDayTo() {
        return wml070SendDateDayTo__;
    }

    /**
     * <p>wml070SendDateDayTo をセットします。
     * @param wml070SendDateDayTo wml070SendDateDayTo
     */
    public void setWml070SendDateDayTo(int wml070SendDateDayTo) {
        wml070SendDateDayTo__ = wml070SendDateDayTo;
    }

    /**
     * <p>wml070SendDateMonthTo を取得します。
     * @return wml070SendDateMonthTo
     */
    public int getWml070SendDateMonthTo() {
        return wml070SendDateMonthTo__;
    }

    /**
     * <p>wml070SendDateMonthTo をセットします。
     * @param wml070SendDateMonthTo wml070SendDateMonthTo
     */
    public void setWml070SendDateMonthTo(int wml070SendDateMonthTo) {
        wml070SendDateMonthTo__ = wml070SendDateMonthTo;
    }

    /**
     * <p>wml070SendDateYearTo を取得します。
     * @return wml070SendDateYearTo
     */
    public int getWml070SendDateYearTo() {
        return wml070SendDateYearTo__;
    }

    /**
     * <p>wml070SendDateYearTo をセットします。
     * @param wml070SendDateYearTo wml070SendDateYearTo
     */
    public void setWml070SendDateYearTo(int wml070SendDateYearTo) {
        wml070SendDateYearTo__ = wml070SendDateYearTo;
    }

    /**
     * <p>wml070svSendDateDayTo を取得します。
     * @return wml070svSendDateDayTo
     */
    public int getWml070svSendDateDayTo() {
        return wml070svSendDateDayTo__;
    }

    /**
     * <p>wml070svSendDateDayTo をセットします。
     * @param wml070svSendDateDayTo wml070svSendDateDayTo
     */
    public void setWml070svSendDateDayTo(int wml070svSendDateDayTo) {
        wml070svSendDateDayTo__ = wml070svSendDateDayTo;
    }

    /**
     * <p>wml070svSendDateMonthTo を取得します。
     * @return wml070svSendDateMonthTo
     */
    public int getWml070svSendDateMonthTo() {
        return wml070svSendDateMonthTo__;
    }

    /**
     * <p>wml070svSendDateMonthTo をセットします。
     * @param wml070svSendDateMonthTo wml070svSendDateMonthTo
     */
    public void setWml070svSendDateMonthTo(int wml070svSendDateMonthTo) {
        wml070svSendDateMonthTo__ = wml070svSendDateMonthTo;
    }

    /**
     * <p>wml070svSendDateYearTo を取得します。
     * @return wml070svSendDateYearTo
     */
    public int getWml070svSendDateYearTo() {
        return wml070svSendDateYearTo__;
    }

    /**
     * <p>wml070svSendDateYearTo をセットします。
     * @param wml070svSendDateYearTo wml070svSendDateYearTo
     */
    public void setWml070svSendDateYearTo(int wml070svSendDateYearTo) {
        wml070svSendDateYearTo__ = wml070svSendDateYearTo;
    }
}
