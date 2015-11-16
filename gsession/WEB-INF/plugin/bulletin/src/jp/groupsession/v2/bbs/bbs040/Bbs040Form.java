package jp.groupsession.v2.bbs.bbs040;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bbs.bbs010.Bbs010Form;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] 掲示板 詳細検索画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs040Form extends Bbs010Form {

    /** フォーラム */
    private int bbs040forumSid__ = 0;
    /** キーワード区分 */
    private int bbs040keyKbn__ = 0;
    /** 検索対象(スレッドタイトル) */
    private int bbs040taisyouThread__ = 0;
    /** 検索対象(内容) */
    private int bbs040taisyouNaiyou__ = 0;
    /** 投稿者名 */
    private String bbs040userName__ = null;
    /** 未読・既読 */
    private int bbs040readKbn__ = 0;
    /** 投稿日時指定無し */
    private int bbs040dateNoKbn__ = 0;
    /** 投稿日時From年 */
    private int bbs040fromYear__ = 0;
    /** 投稿日時From月 */
    private int bbs040fromMonth__ = 0;
    /** 投稿日時From日 */
    private int bbs040fromDay__ = 0;
    /** 投稿日時To年 */
    private int bbs040toYear__ = 0;
    /** 投稿日時To月 */
    private int bbs040toMonth__ = 0;
    /** 投稿日時To日 */
    private int bbs040toDay__ = 0;
    /** 検索画面ID */
    private String searchDspID__ = null;
    /** スレッドSID */
    private int threadSid__ = 0;

    /** ページコンボ上 */
    private int bbs060page1__ = 0;

    /** フォーラムコンボ */
    private List < LabelValueBean > bbs040forumList__ = null;
    /** 年コンボ */
    private List < LabelValueBean > bbs040yearList__ = null;
    /** 月コンボ */
    private List < LabelValueBean > bbs040monthList__ = null;
    /** 日コンボ */
    private List < LabelValueBean > bbs040dayList__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Bbs040Form() {

        //投稿日時Toに現在日時を設定
        UDate date = new UDate();
        bbs040toYear__ = date.getYear();
        bbs040toMonth__ = date.getMonth();
        bbs040toDay__ = date.getIntDay();
    }

    /**
     * @return searchDspID を戻します。
     */
    public String getSearchDspID() {
        return searchDspID__;
    }
    /**
     * @param searchDspID 設定する searchDspID。
     */
    public void setSearchDspID(String searchDspID) {
        searchDspID__ = searchDspID;
    }
    /**
     * @return bbs040dateNoKbn を戻します。
     */
    public int getBbs040dateNoKbn() {
        return bbs040dateNoKbn__;
    }
    /**
     * @param bbs040dateNoKbn 設定する bbs040dateNoKbn。
     */
    public void setBbs040dateNoKbn(int bbs040dateNoKbn) {
        bbs040dateNoKbn__ = bbs040dateNoKbn;
    }
    /**
     * @return bbs040forumSid を戻します。
     */
    public int getBbs040forumSid() {
        return bbs040forumSid__;
    }
    /**
     * @param bbs040forumSid 設定する bbs040forumSid。
     */
    public void setBbs040forumSid(int bbs040forumSid) {
        bbs040forumSid__ = bbs040forumSid;
    }
    /**
     * @return bbs040fromDay を戻します。
     */
    public int getBbs040fromDay() {
        return bbs040fromDay__;
    }
    /**
     * @param bbs040fromDay 設定する bbs040fromDay。
     */
    public void setBbs040fromDay(int bbs040fromDay) {
        bbs040fromDay__ = bbs040fromDay;
    }
    /**
     * @return bbs040fromMonth を戻します。
     */
    public int getBbs040fromMonth() {
        return bbs040fromMonth__;
    }
    /**
     * @param bbs040fromMonth 設定する bbs040fromMonth。
     */
    public void setBbs040fromMonth(int bbs040fromMonth) {
        bbs040fromMonth__ = bbs040fromMonth;
    }
    /**
     * @return bbs040fromYear を戻します。
     */
    public int getBbs040fromYear() {
        return bbs040fromYear__;
    }
    /**
     * @param bbs040fromYear 設定する bbs040fromYear。
     */
    public void setBbs040fromYear(int bbs040fromYear) {
        bbs040fromYear__ = bbs040fromYear;
    }
    /**
     * @return bbs040keyKbn を戻します。
     */
    public int getBbs040keyKbn() {
        return bbs040keyKbn__;
    }
    /**
     * @param bbs040keyKbn 設定する bbs040keyKbn。
     */
    public void setBbs040keyKbn(int bbs040keyKbn) {
        bbs040keyKbn__ = bbs040keyKbn;
    }
    /**
     * @return bbs040readKbn を戻します。
     */
    public int getBbs040readKbn() {
        return bbs040readKbn__;
    }
    /**
     * @param bbs040readKbn 設定する bbs040readKbn。
     */
    public void setBbs040readKbn(int bbs040readKbn) {
        bbs040readKbn__ = bbs040readKbn;
    }
    /**
     * @return bbs040taisyouNaiyou を戻します。
     */
    public int getBbs040taisyouNaiyou() {
        return bbs040taisyouNaiyou__;
    }
    /**
     * @param bbs040taisyouNaiyou 設定する bbs040taisyouNaiyou。
     */
    public void setBbs040taisyouNaiyou(int bbs040taisyouNaiyou) {
        bbs040taisyouNaiyou__ = bbs040taisyouNaiyou;
    }
    /**
     * @return bbs040taisyouThread を戻します。
     */
    public int getBbs040taisyouThread() {
        return bbs040taisyouThread__;
    }
    /**
     * @param bbs040taisyouThread 設定する bbs040taisyouThread。
     */
    public void setBbs040taisyouThread(int bbs040taisyouThread) {
        bbs040taisyouThread__ = bbs040taisyouThread;
    }
    /**
     * @return bbs040toDay を戻します。
     */
    public int getBbs040toDay() {
        return bbs040toDay__;
    }
    /**
     * @param bbs040toDay 設定する bbs040toDay。
     */
    public void setBbs040toDay(int bbs040toDay) {
        bbs040toDay__ = bbs040toDay;
    }
    /**
     * @return bbs040toMonth を戻します。
     */
    public int getBbs040toMonth() {
        return bbs040toMonth__;
    }
    /**
     * @param bbs040toMonth 設定する bbs040toMonth。
     */
    public void setBbs040toMonth(int bbs040toMonth) {
        bbs040toMonth__ = bbs040toMonth;
    }
    /**
     * @return bbs040toYear を戻します。
     */
    public int getBbs040toYear() {
        return bbs040toYear__;
    }
    /**
     * @param bbs040toYear 設定する bbs040toYear。
     */
    public void setBbs040toYear(int bbs040toYear) {
        bbs040toYear__ = bbs040toYear;
    }
    /**
     * <p>bbs040userName を取得します。
     * @return bbs040userName
     */
    public String getBbs040userName() {
        return bbs040userName__;
    }

    /**
     * <p>bbs040userName をセットします。
     * @param bbs040userName bbs040userName
     */
    public void setBbs040userName(String bbs040userName) {
        bbs040userName__ = bbs040userName;
    }

    /**
     * @return bbs040dayList を戻します。
     */
    public List < LabelValueBean > getBbs040dayList() {
        return bbs040dayList__;
    }
    /**
     * @param bbs040dayList 設定する bbs040dayList。
     */
    public void setBbs040dayList(List < LabelValueBean > bbs040dayList) {
        bbs040dayList__ = bbs040dayList;
    }
    /**
     * @return bbs040forumList を戻します。
     */
    public List < LabelValueBean > getBbs040forumList() {
        return bbs040forumList__;
    }
    /**
     * @param bbs040forumList 設定する bbs040forumList。
     */
    public void setBbs040forumList(List < LabelValueBean > bbs040forumList) {
        bbs040forumList__ = bbs040forumList;
    }
    /**
     * @return bbs040monthList を戻します。
     */
    public List < LabelValueBean > getBbs040monthList() {
        return bbs040monthList__;
    }
    /**
     * @param bbs040monthList 設定する bbs040monthList。
     */
    public void setBbs040monthList(List < LabelValueBean > bbs040monthList) {
        bbs040monthList__ = bbs040monthList;
    }
    /**
     * @return bbs040yearList を戻します。
     */
    public List < LabelValueBean > getBbs040yearList() {
        return bbs040yearList__;
    }
    /**
     * @param bbs040yearList 設定する bbs040yearList。
     */
    public void setBbs040yearList(List < LabelValueBean > bbs040yearList) {
        bbs040yearList__ = bbs040yearList;
    }
    /**
     * @return bbs060page1 を戻します。
     */
    public int getBbs060page1() {
        return bbs060page1__;
    }
    /**
     * @param bbs060page1 設定する bbs060page1。
     */
    public void setBbs060page1(int bbs060page1) {
        bbs060page1__ = bbs060page1;
    }
    /**
     * <p>threadSid を取得します。
     * @return threadSid
     */
    public int getThreadSid() {
        return threadSid__;
    }
    /**
     * <p>threadSid をセットします。
     * @param threadSid threadSid
     */
    public void setThreadSid(int threadSid) {
        threadSid__ = threadSid;
    }

    /**
     * パラメータのコピーを行う
     * @param form フォーム
     */
    public void copyFormParam(Bbs040Form form) {
        bbs040forumSid__ = form.getBbs040forumSid();
        bbs040keyKbn__ = form.getBbs040keyKbn();
        bbs040taisyouThread__ = form.getBbs040taisyouThread();
        bbs040taisyouNaiyou__ = form.getBbs040taisyouNaiyou();
        bbs040userName__ = form.getBbs040userName();
        bbs040readKbn__ = form.getBbs040readKbn();
        bbs040dateNoKbn__ = form.getBbs040dateNoKbn();
        bbs040fromYear__ = form.getBbs040fromYear();
        bbs040fromMonth__ = form.getBbs040fromMonth();
        bbs040fromDay__ = form.getBbs040fromDay();
        bbs040toYear__ = form.getBbs040toYear();
        bbs040toMonth__ = form.getBbs040toMonth();
        bbs040toDay__ = form.getBbs040toDay();
        searchDspID__ = form.getSearchDspID();
        bbs060page1__ = form.getBbs060page1();
    }

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateCheck(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        //-- フォーラムチェック --
        GsMessage gsMsg = new GsMessage();
        String textForum = gsMsg.getMessage(req, "bbs.3");

        //未選択チェック
        if (bbs040forumSid__ <= 0) {
            msg = new ActionMessage("error.select.required.text", textForum);
            StrutsUtil.addMessage(errors, msg, "bbs040forumSid");

        }


        //-- キーワードチェック --

        if (StringUtil.isNullZeroString(bbs040userName__)
        && bbs040readKbn__ != 1 && bbs040readKbn__ != 2
        && bbs040dateNoKbn__ == 1) {
            //投稿者名、未読・既読、投稿日時が全て未入力 or 指定なしの場合
            //下記の入力チェックを行う

            //未入力チェック
            if (StringUtil.isNullZeroString(getS_key())) {
                String textKeyword = gsMsg.getMessage(req, "cmn.keyword");
                msg = new ActionMessage("error.input.required.text",
                                         textKeyword);
                StrutsUtil.addMessage(errors, msg, "s_key");
                return errors;
            }
        }
        if (!StringUtil.isNullZeroString(getS_key())) {
            //スペースのみチェック
            if (ValidateUtil.isSpace(getS_key())) {
                String textKeyword = gsMsg.getMessage(req, "cmn.keyword");
                msg = new ActionMessage("error.input.spase.only", textKeyword);
                StrutsUtil.addMessage(errors, msg, "s_key");
            }
            //先頭スペースチェック
            if (ValidateUtil.isSpaceStart(getS_key())) {
                String textKeyword = gsMsg.getMessage(req, "cmn.keyword");
                msg = new ActionMessage("error.input.spase.start", textKeyword);
                StrutsUtil.addMessage(errors, msg, "s_key");
            }
        }
        //JIS第2水準チェック
        if (!GSValidateUtil.isGsJapaneaseString(getS_key())) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseString(getS_key());
            String textKeyword = gsMsg.getMessage(req, "cmn.keyword");
            msg = new ActionMessage("error.input.njapan.text", textKeyword,
                                    nstr);
            StrutsUtil.addMessage(errors, msg, "s_key");
        }



        //-- 検索対象チェック --

        //未選択チェック
        if (!StringUtil.isNullZeroString(getS_key())
        && bbs040taisyouThread__ != 1
        && bbs040taisyouNaiyou__ != 1) {
            String textSearchSbj = gsMsg.getMessage(req, "cmn.search2");
            msg = new ActionMessage("error.input.selectoen.check", textSearchSbj);
            StrutsUtil.addMessage(errors, msg, "bbs040taisyou");

        }


        //-- 投稿者名チェック --

        if (!StringUtil.isNullZeroString(bbs040userName__)) {

            String targetName = gsMsg.getMessage(req, "cmn.contributor.name");
            //スペースのみチェック
            if (ValidateUtil.isSpace(bbs040userName__)) {
                msg = new ActionMessage("error.input.spase.only", targetName);
                StrutsUtil.addMessage(errors, msg, "bbs040userName");
            }

            //先頭スペースチェック
            if (ValidateUtil.isSpaceStart(bbs040userName__)) {
                msg = new ActionMessage("error.input.spase.start", targetName);
                StrutsUtil.addMessage(errors, msg, "bbs040userName");
            }

            //JIS第2水準チェック
            if (!GSValidateUtil.isGsJapaneaseStringTextArea(bbs040userName__)) {
                //利用不可能な文字を入力した場合
                String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(bbs040userName__);
                msg = new ActionMessage("error.input.njapan.text",
                                                        targetName,
                                                        nstr);
                StrutsUtil.addMessage(errors, msg, "bbs040userName");
            }
        }

        if (bbs040dateNoKbn__ != 1) {
            //-- 投稿者日時 Fromチェック --

            //日付論理チェック
            UDate fromDate = new UDate();
            fromDate.setTime(0);
            fromDate.setDate(bbs040fromYear__, bbs040fromMonth__, bbs040fromDay__);
            boolean fromDateOk = true;
            if (fromDate.getYear() != bbs040fromYear__
            || fromDate.getMonth() != bbs040fromMonth__
            || fromDate.getIntDay() != bbs040fromDay__) {
                String textContributionDateFrom = gsMsg.getMessage(req, "bbs.21");
                msg = new ActionMessage("error.input.notfound.date",
                                        textContributionDateFrom);
                StrutsUtil.addMessage(errors, msg, "bbs040fromYear");
                fromDateOk = false;
            }


            //-- 投稿者日時 Toチェック --

            //日付論理チェック
            UDate toDate = new UDate();
            toDate.setTime(0);
            toDate.setDate(bbs040toYear__, bbs040toMonth__, bbs040toDay__);
            if (toDate.getYear() != bbs040toYear__
            || toDate.getMonth() != bbs040toMonth__
            || toDate.getIntDay() != bbs040toDay__) {
                String textContributionDateTo = gsMsg.getMessage(req, "bbs.22");
                msg = new ActionMessage("error.input.notfound.date",
                                        textContributionDateTo);
                StrutsUtil.addMessage(errors, msg, "bbs040toYear");
            } else if (fromDateOk && fromDate.compareDateYMD(toDate) == UDate.SMALL) {
                String textContributionDateFromTo = gsMsg.getMessage(req, "bbs.23");
                String textContributionDateMin = gsMsg.getMessage(req, "bbs.24");
                msg = new ActionMessage("error.input.comp.text",
                        textContributionDateFromTo,
                        textContributionDateMin);
                StrutsUtil.addMessage(errors, msg, "rsv110Kikan");
            }
        }

        return errors;
    }

}
