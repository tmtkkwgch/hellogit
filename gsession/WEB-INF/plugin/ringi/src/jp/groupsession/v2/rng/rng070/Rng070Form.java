package jp.groupsession.v2.rng.rng070;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.RngValidate;
import jp.groupsession.v2.rng.model.RingiDataModel;
import jp.groupsession.v2.rng.rng010.Rng010Form;
import jp.groupsession.v2.rng.rng050.Rng050Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議 管理者設定 完了案件管理画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng070Form extends Rng010Form {
    //フィールド
    /** キーワード */
    private String rngInputKeyword__ = null;

    /** 稟議情報(完了)リスト */
    private List<RingiDataModel> rng070dataList__ = null;
    /** 年リスト */
    private List<LabelValueBean> rng070YearList__ = null;
    /** 月リスト */
    private List<LabelValueBean> rng070MonthList__ = null;
    /** 日リスト */
    private List<LabelValueBean> rng070DayList__ = null;

    /** 削除チェックボックス */
    private String[] rng070dellist__ = null;
    //キー値
    /** ソートキー */
    private int rngAdminSortKey__ = RngConst.RNG_SORT_KAKUNIN;
    /** オーダーキー */
    private int rngAdminOrderKey__ = RngConst.RNG_ORDER_DESC;
    /** ページ(上) */
    private int rngAdminPageTop__ = 1;
    /** ページ(下) */
    private int rngAdminPageBottom__ = 1;
    /** ページリスト */
    private List<LabelValueBean> rngAdminPageList__;
    /** 検索実行フラグ */
    private int rngAdminSearchFlg__ = 0;
    /** グループSID */
    private int rngAdminGroupSid__ = -1;
    /** ユーザSID */
    private int rngAdminUserSid__ = -1;
    /** キーワード */
    private String rngAdminKeyword__;
    /** 申請日時 年 From */
    private int rngAdminApplYearFr__ = -1;
    /** 申請日時 月 From */
    private int rngAdminApplMonthFr__ = -1;
    /** 申請日時 日 From */
    private int rngAdminApplDayFr__ = -1;
    /** 申請日時 年 To */
    private int rngAdminApplYearTo__ = -1;
    /** 申請日時 月 To */
    private int rngAdminApplMonthTo__ = -1;
    /** 申請日時 日 To */
    private int rngAdminApplDayTo__ = -1;
    /** 最終処理日時 年 From */
    private int rngAdminLastManageYearFr__ = -1;
    /** 最終処理日時 月 From */
    private int rngAdminLastManageMonthFr__ = -1;
    /** 最終処理日時 日 From */
    private int rngAdminLastManageDayFr__ = -1;
    /** 最終処理日時 年 To */
    private int rngAdminLastManageYearTo__ = -1;
    /** 最終処理日時 月 To */
    private int rngAdminLastManageMonthTo__ = -1;
    /** 最終処理日時 日 To */
    private int rngAdminLastManageDayTo__ = -1;

    //コンボ
    /** 選択グループSID */
    private int sltGroupSid__ = -1;
    /** 選択グループSID */
    private int sltUserSid__ = -1;
    /** 選択申請日時 年 From */
    private int sltApplYearFr__ = -1;
    /** 選択申請日時 月 From */
    private int sltApplMonthFr__ = -1;
    /** 選択申請日時 日 From */
    private int sltApplDayFr__ = -1;
    /** 選択申請日時 年 To */
    private int sltApplYearTo__ = -1;
    /** 選択申請日時 月 To */
    private int sltApplMonthTo__ = -1;
    /** 選択申請日時 日 To */
    private int sltApplDayTo__ = -1;
    /** 選択最終処理日時 年 From */
    private int sltLastManageYearFr__ = -1;
    /** 選択最終処理日時 月 From */
    private int sltLastManageMonthFr__ = -1;
    /** 選択最終処理日時 日 From */
    private int sltLastManageDayFr__ = -1;
    /** 選択最終処理日時 年 To */
    private int sltLastManageYearTo__ = -1;
    /** 選択最終処理日時 月 To */
    private int sltLastManageMonthTo__ = -1;
    /** 選択最終処理日時 日 To */
    private int sltLastManageDayTo__ = -1;
    /** 表示用グループリスト */
    private List<LabelValueBean> rng070groupList__ = null;
    /** 表示用ユーザーリスト */
    private List<LabelValueBean> rng070userList__ = null;

    /** WEB検索プラグイン使用可否 0=使用 1=未使用 */
    private int rngWebSearchUse__ = GSConst.PLUGIN_USE;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return errors エラー
     */
    public ActionErrors validateCheck(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        GsMessage gsMsg = new GsMessage();
        String keyword = gsMsg.getMessage(req, "cmn.keyword");

        //キーワード
        errors = RngValidate.validateCmnFieldText(
                errors,             //errors
                keyword,         //エラーメッセージ表示テキスト
                rngInputKeyword__,      //チェックするフィールド
                "rngAdminKeyword",    //チェックするフィールドの文字列
                100,                //最大桁数
                false);             //入力必須か

        String shinFrom = gsMsg.getMessage(req, "rng.83");
        String shinTo = gsMsg.getMessage(req, "rng.84");
        String lastFrom = gsMsg.getMessage(req, "rng.78");
        String lastTo = gsMsg.getMessage(req, "rng.79");

        Rng050Form form050 = new Rng050Form();
        int errCount = errors.size();
        boolean inputFrom = false;
        boolean inputTo = false;
        if (sltApplYearFr__ > 0 || sltApplMonthFr__ > 0 || sltApplDayFr__ > 0) {

            //申請日時From
            errors = form050.checkDate(errors, shinFrom,
                            sltApplYearFr__, sltApplMonthFr__, sltApplDayFr__, req);
            inputFrom = true;
        }

        if (sltApplYearTo__ > 0 || sltApplMonthTo__ > 0 || sltApplDayTo__ > 0) {
            //申請日時To
            errors = form050.checkDate(errors, shinTo,
                            sltApplYearTo__, sltApplMonthTo__, sltApplDayTo__, req);
            inputTo = true;
        }

        if (inputFrom && inputTo && errCount == errors.size()) {
            //申請日時範囲チェック
            long time = System.currentTimeMillis();
            UDate frDate = UDate.getInstance(time);
            UDate toDate = UDate.getInstance(time);
            frDate.setDate(sltApplYearFr__, sltApplMonthFr__, sltApplDayFr__);
            toDate.setDate(sltApplYearTo__, sltApplMonthTo__, sltApplDayTo__);

            errors = form050.checkDateRange(errors, shinFrom, shinTo, frDate, toDate);
        }

        errCount = errors.size();
        inputFrom = false;
        inputTo = false;
        if (sltLastManageYearFr__ > 0
        || sltLastManageMonthFr__ > 0
        || sltLastManageDayFr__ > 0) {
            //最終確認日時From
            errors = form050.checkDate(errors,
                                       lastFrom,
                                       sltLastManageYearFr__,
                                       sltLastManageMonthFr__,
                                       sltLastManageDayFr__,
                                       req);
        }

        if (sltLastManageYearTo__ > 0
        || sltLastManageMonthTo__ > 0
        || sltLastManageDayTo__ > 0) {
            //最終確認日時To
            errors = form050.checkDate(errors,
                                       lastTo,
                                       sltLastManageYearTo__,
                                       sltLastManageMonthTo__,
                                       sltLastManageDayTo__,
                                       req);
        }

        if (inputFrom && inputTo && errCount == errors.size()) {
            //最終確認日時範囲チェック
            long time = System.currentTimeMillis();
            UDate frDate = UDate.getInstance(time);
            UDate toDate = UDate.getInstance(time);
            frDate.setDate(sltLastManageYearFr__, sltLastManageMonthFr__, sltLastManageDayFr__);
            toDate.setDate(sltLastManageYearTo__, sltLastManageMonthTo__, sltLastManageDayTo__);

            errors = form050.checkDateRange(errors, lastFrom, lastTo, frDate, toDate);
        }

        return errors;
    }

    /**
     * <br>[機  能] 削除時の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return errors エラー
     */
    public ActionErrors validateDel(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "rng.80");

        //キーワード
        errors = RngValidate.validateDeleteRng(
                errors, msg, rng070dellist__, "rng070dellist__");

        return errors;
    }

    /**
     * <p>rng070dataList を取得します。
     * @return rng070dataList
     */
    public List<RingiDataModel> getRng070dataList() {
        return rng070dataList__;
    }

    /**
     * <p>rng070dataList をセットします。
     * @param rng070dataList rng070dataList
     */
    public void setRng070dataList(List<RingiDataModel> rng070dataList) {
        rng070dataList__ = rng070dataList;
    }

    /**
     * <p>rng070groupList を取得します。
     * @return rng070groupList
     */
    public List<LabelValueBean> getRng070groupList() {
        return rng070groupList__;
    }

    /**
     * <p>rng070groupList をセットします。
     * @param rng070groupList rng070groupList
     */
    public void setRng070groupList(List<LabelValueBean> rng070groupList) {
        rng070groupList__ = rng070groupList;
    }

    /**
     * <p>rng070userList を取得します。
     * @return rng070userList
     */
    public List<LabelValueBean> getRng070userList() {
        return rng070userList__;
    }

    /**
     * <p>rng070userList をセットします。
     * @param rng070userList rng070userList
     */
    public void setRng070userList(List<LabelValueBean> rng070userList) {
        rng070userList__ = rng070userList;
    }

    /**
     * <p>rngInputKeyword を取得します。
     * @return rngInputKeyword
     */
    public String getRngInputKeyword() {
        return rngInputKeyword__;
    }

    /**
     * <p>rngInputKeyword をセットします。
     * @param rngInputKeyword rngInputKeyword
     */
    public void setRngInputKeyword(String rngInputKeyword) {
        rngInputKeyword__ = rngInputKeyword;
    }

    /**
     * <p>rngAdminGroupSid を取得します。
     * @return rngAdminGroupSid
     */
    public int getRngAdminGroupSid() {
        return rngAdminGroupSid__;
    }

    /**
     * <p>rngAdminGroupSid をセットします。
     * @param rngAdminGroupSid rngAdminGroupSid
     */
    public void setRngAdminGroupSid(int rngAdminGroupSid) {
        rngAdminGroupSid__ = rngAdminGroupSid;
    }

    /**
     * <p>rngAdminUserSid を取得します。
     * @return rngAdminUserSid
     */
    public int getRngAdminUserSid() {
        return rngAdminUserSid__;
    }

    /**
     * <p>rngAdminUserSid をセットします。
     * @param rngAdminUserSid rngAdminUserSid
     */
    public void setRngAdminUserSid(int rngAdminUserSid) {
        rngAdminUserSid__ = rngAdminUserSid;
    }

    /**
     * <p>rngAdminKeyword を取得します。
     * @return rngAdminKeyword
     */
    public String getRngAdminKeyword() {
        return rngAdminKeyword__;
    }

    /**
     * <p>rngAdminKeyword をセットします。
     * @param rngAdminKeyword rngAdminKeyword
     */
    public void setRngAdminKeyword(String rngAdminKeyword) {
        rngAdminKeyword__ = rngAdminKeyword;
    }

    /**
     * <p>rngAdminOrderKey を取得します。
     * @return rngAdminOrderKey
     */
    public int getRngAdminOrderKey() {
        return rngAdminOrderKey__;
    }

    /**
     * <p>rngAdminOrderKey をセットします。
     * @param rngAdminOrderKey rngAdminOrderKey
     */
    public void setRngAdminOrderKey(int rngAdminOrderKey) {
        rngAdminOrderKey__ = rngAdminOrderKey;
    }

    /**
     * <p>rngAdminPageBottom を取得します。
     * @return rngAdminPageBottom
     */
    public int getRngAdminPageBottom() {
        return rngAdminPageBottom__;
    }

    /**
     * <p>rngAdminPageBottom をセットします。
     * @param rngAdminPageBottom rngAdminPageBottom
     */
    public void setRngAdminPageBottom(int rngAdminPageBottom) {
        rngAdminPageBottom__ = rngAdminPageBottom;
    }

    /**
     * <p>rngAdminPageTop を取得します。
     * @return rngAdminPageTop
     */
    public int getRngAdminPageTop() {
        return rngAdminPageTop__;
    }

    /**
     * <p>rngAdminPageTop をセットします。
     * @param rngAdminPageTop rngAdminPageTop
     */
    public void setRngAdminPageTop(int rngAdminPageTop) {
        rngAdminPageTop__ = rngAdminPageTop;
    }

    /**
     * <p>rngAdminSortKey を取得します。
     * @return rngAdminSortKey
     */
    public int getRngAdminSortKey() {
        return rngAdminSortKey__;
    }

    /**
     * <p>rngAdminSortKey をセットします。
     * @param rngAdminSortKey rngAdminSortKey
     */
    public void setRngAdminSortKey(int rngAdminSortKey) {
        rngAdminSortKey__ = rngAdminSortKey;
    }

    /**
     * <p>sltGroupSid を取得します。
     * @return sltGroupSid
     */
    public int getSltGroupSid() {
        return sltGroupSid__;
    }

    /**
     * <p>sltGroupSid をセットします。
     * @param sltGroupSid sltGroupSid
     */
    public void setSltGroupSid(int sltGroupSid) {
        sltGroupSid__ = sltGroupSid;
    }

    /**
     * <p>sltUserSid を取得します。
     * @return sltUserSid
     */
    public int getSltUserSid() {
        return sltUserSid__;
    }

    /**
     * <p>sltUserSid をセットします。
     * @param sltUserSid sltUserSid
     */
    public void setSltUserSid(int sltUserSid) {
        sltUserSid__ = sltUserSid;
    }

    /**
     * <p>rng070dellist を取得します。
     * @return rng070dellist
     */
    public String[] getRng070dellist() {
        return rng070dellist__;
    }

    /**
     * <p>rng070dellist をセットします。
     * @param rng070dellist rng070dellist
     */
    public void setRng070dellist(String[] rng070dellist) {
        rng070dellist__ = rng070dellist;
    }

    /**
     * <p>rngAdminPageList を取得します。
     * @return rngAdminPageList
     */
    public List<LabelValueBean> getRngAdminPageList() {
        return rngAdminPageList__;
    }

    /**
     * <p>rngAdminPageList をセットします。
     * @param rngAdminPageList rngAdminPageList
     */
    public void setRngAdminPageList(List<LabelValueBean> rngAdminPageList) {
        rngAdminPageList__ = rngAdminPageList;
    }

    /**
     * <p>rngAdminSearchFlg を取得します。
     * @return rngAdminSearchFlg
     */
    public int getRngAdminSearchFlg() {
        return rngAdminSearchFlg__;
    }

    /**
     * <p>rngAdminSearchFlg をセットします。
     * @param rngAdminSearchFlg rngAdminSearchFlg
     */
    public void setRngAdminSearchFlg(int rngAdminSearchFlg) {
        rngAdminSearchFlg__ = rngAdminSearchFlg;
    }

    /**
     * <p>rng070DayList を取得します。
     * @return rng070DayList
     */
    public List<LabelValueBean> getRng070DayList() {
        return rng070DayList__;
    }

    /**
     * <p>rng070DayList をセットします。
     * @param rng070DayList rng070DayList
     */
    public void setRng070DayList(List<LabelValueBean> rng070DayList) {
        rng070DayList__ = rng070DayList;
    }

    /**
     * <p>rng070MonthList を取得します。
     * @return rng070MonthList
     */
    public List<LabelValueBean> getRng070MonthList() {
        return rng070MonthList__;
    }

    /**
     * <p>rng070MonthList をセットします。
     * @param rng070MonthList rng070MonthList
     */
    public void setRng070MonthList(List<LabelValueBean> rng070MonthList) {
        rng070MonthList__ = rng070MonthList;
    }

    /**
     * <p>rng070YearList を取得します。
     * @return rng070YearList
     */
    public List<LabelValueBean> getRng070YearList() {
        return rng070YearList__;
    }

    /**
     * <p>rng070YearList をセットします。
     * @param rng070YearList rng070YearList
     */
    public void setRng070YearList(List<LabelValueBean> rng070YearList) {
        rng070YearList__ = rng070YearList;
    }

    /**
     * <p>rngAdminApplDayFr を取得します。
     * @return rngAdminApplDayFr
     */
    public int getRngAdminApplDayFr() {
        return rngAdminApplDayFr__;
    }

    /**
     * <p>rngAdminApplDayFr をセットします。
     * @param rngAdminApplDayFr rngAdminApplDayFr
     */
    public void setRngAdminApplDayFr(int rngAdminApplDayFr) {
        rngAdminApplDayFr__ = rngAdminApplDayFr;
    }

    /**
     * <p>rngAdminApplDayTo を取得します。
     * @return rngAdminApplDayTo
     */
    public int getRngAdminApplDayTo() {
        return rngAdminApplDayTo__;
    }

    /**
     * <p>rngAdminApplDayTo をセットします。
     * @param rngAdminApplDayTo rngAdminApplDayTo
     */
    public void setRngAdminApplDayTo(int rngAdminApplDayTo) {
        rngAdminApplDayTo__ = rngAdminApplDayTo;
    }

    /**
     * <p>rngAdminApplMonthFr を取得します。
     * @return rngAdminApplMonthFr
     */
    public int getRngAdminApplMonthFr() {
        return rngAdminApplMonthFr__;
    }

    /**
     * <p>rngAdminApplMonthFr をセットします。
     * @param rngAdminApplMonthFr rngAdminApplMonthFr
     */
    public void setRngAdminApplMonthFr(int rngAdminApplMonthFr) {
        rngAdminApplMonthFr__ = rngAdminApplMonthFr;
    }

    /**
     * <p>rngAdminApplMonthTo を取得します。
     * @return rngAdminApplMonthTo
     */
    public int getRngAdminApplMonthTo() {
        return rngAdminApplMonthTo__;
    }

    /**
     * <p>rngAdminApplMonthTo をセットします。
     * @param rngAdminApplMonthTo rngAdminApplMonthTo
     */
    public void setRngAdminApplMonthTo(int rngAdminApplMonthTo) {
        rngAdminApplMonthTo__ = rngAdminApplMonthTo;
    }

    /**
     * <p>rngAdminApplYearFr を取得します。
     * @return rngAdminApplYearFr
     */
    public int getRngAdminApplYearFr() {
        return rngAdminApplYearFr__;
    }

    /**
     * <p>rngAdminApplYearFr をセットします。
     * @param rngAdminApplYearFr rngAdminApplYearFr
     */
    public void setRngAdminApplYearFr(int rngAdminApplYearFr) {
        rngAdminApplYearFr__ = rngAdminApplYearFr;
    }

    /**
     * <p>rngAdminApplYearTo を取得します。
     * @return rngAdminApplYearTo
     */
    public int getRngAdminApplYearTo() {
        return rngAdminApplYearTo__;
    }

    /**
     * <p>rngAdminApplYearTo をセットします。
     * @param rngAdminApplYearTo rngAdminApplYearTo
     */
    public void setRngAdminApplYearTo(int rngAdminApplYearTo) {
        rngAdminApplYearTo__ = rngAdminApplYearTo;
    }

    /**
     * <p>rngAdminLastManageDayFr を取得します。
     * @return rngAdminLastManageDayFr
     */
    public int getRngAdminLastManageDayFr() {
        return rngAdminLastManageDayFr__;
    }

    /**
     * <p>rngAdminLastManageDayFr をセットします。
     * @param rngAdminLastManageDayFr rngAdminLastManageDayFr
     */
    public void setRngAdminLastManageDayFr(int rngAdminLastManageDayFr) {
        rngAdminLastManageDayFr__ = rngAdminLastManageDayFr;
    }

    /**
     * <p>rngAdminLastManageDayTo を取得します。
     * @return rngAdminLastManageDayTo
     */
    public int getRngAdminLastManageDayTo() {
        return rngAdminLastManageDayTo__;
    }

    /**
     * <p>rngAdminLastManageDayTo をセットします。
     * @param rngAdminLastManageDayTo rngAdminLastManageDayTo
     */
    public void setRngAdminLastManageDayTo(int rngAdminLastManageDayTo) {
        rngAdminLastManageDayTo__ = rngAdminLastManageDayTo;
    }

    /**
     * <p>rngAdminLastManageMonthFr を取得します。
     * @return rngAdminLastManageMonthFr
     */
    public int getRngAdminLastManageMonthFr() {
        return rngAdminLastManageMonthFr__;
    }

    /**
     * <p>rngAdminLastManageMonthFr をセットします。
     * @param rngAdminLastManageMonthFr rngAdminLastManageMonthFr
     */
    public void setRngAdminLastManageMonthFr(int rngAdminLastManageMonthFr) {
        rngAdminLastManageMonthFr__ = rngAdminLastManageMonthFr;
    }

    /**
     * <p>rngAdminLastManageMonthTo を取得します。
     * @return rngAdminLastManageMonthTo
     */
    public int getRngAdminLastManageMonthTo() {
        return rngAdminLastManageMonthTo__;
    }

    /**
     * <p>rngAdminLastManageMonthTo をセットします。
     * @param rngAdminLastManageMonthTo rngAdminLastManageMonthTo
     */
    public void setRngAdminLastManageMonthTo(int rngAdminLastManageMonthTo) {
        rngAdminLastManageMonthTo__ = rngAdminLastManageMonthTo;
    }

    /**
     * <p>rngAdminLastManageYearFr を取得します。
     * @return rngAdminLastManageYearFr
     */
    public int getRngAdminLastManageYearFr() {
        return rngAdminLastManageYearFr__;
    }

    /**
     * <p>rngAdminLastManageYearFr をセットします。
     * @param rngAdminLastManageYearFr rngAdminLastManageYearFr
     */
    public void setRngAdminLastManageYearFr(int rngAdminLastManageYearFr) {
        rngAdminLastManageYearFr__ = rngAdminLastManageYearFr;
    }

    /**
     * <p>rngAdminLastManageYearTo を取得します。
     * @return rngAdminLastManageYearTo
     */
    public int getRngAdminLastManageYearTo() {
        return rngAdminLastManageYearTo__;
    }

    /**
     * <p>rngAdminLastManageYearTo をセットします。
     * @param rngAdminLastManageYearTo rngAdminLastManageYearTo
     */
    public void setRngAdminLastManageYearTo(int rngAdminLastManageYearTo) {
        rngAdminLastManageYearTo__ = rngAdminLastManageYearTo;
    }

    /**
     * <p>sltApplDayFr を取得します。
     * @return sltApplDayFr
     */
    public int getSltApplDayFr() {
        return sltApplDayFr__;
    }

    /**
     * <p>sltApplDayFr をセットします。
     * @param sltApplDayFr sltApplDayFr
     */
    public void setSltApplDayFr(int sltApplDayFr) {
        sltApplDayFr__ = sltApplDayFr;
    }

    /**
     * <p>sltApplDayTo を取得します。
     * @return sltApplDayTo
     */
    public int getSltApplDayTo() {
        return sltApplDayTo__;
    }

    /**
     * <p>sltApplDayTo をセットします。
     * @param sltApplDayTo sltApplDayTo
     */
    public void setSltApplDayTo(int sltApplDayTo) {
        sltApplDayTo__ = sltApplDayTo;
    }

    /**
     * <p>sltApplMonthFr を取得します。
     * @return sltApplMonthFr
     */
    public int getSltApplMonthFr() {
        return sltApplMonthFr__;
    }

    /**
     * <p>sltApplMonthFr をセットします。
     * @param sltApplMonthFr sltApplMonthFr
     */
    public void setSltApplMonthFr(int sltApplMonthFr) {
        sltApplMonthFr__ = sltApplMonthFr;
    }

    /**
     * <p>sltApplMonthTo を取得します。
     * @return sltApplMonthTo
     */
    public int getSltApplMonthTo() {
        return sltApplMonthTo__;
    }

    /**
     * <p>sltApplMonthTo をセットします。
     * @param sltApplMonthTo sltApplMonthTo
     */
    public void setSltApplMonthTo(int sltApplMonthTo) {
        sltApplMonthTo__ = sltApplMonthTo;
    }

    /**
     * <p>sltApplYearFr を取得します。
     * @return sltApplYearFr
     */
    public int getSltApplYearFr() {
        return sltApplYearFr__;
    }

    /**
     * <p>sltApplYearFr をセットします。
     * @param sltApplYearFr sltApplYearFr
     */
    public void setSltApplYearFr(int sltApplYearFr) {
        sltApplYearFr__ = sltApplYearFr;
    }

    /**
     * <p>sltApplYearTo を取得します。
     * @return sltApplYearTo
     */
    public int getSltApplYearTo() {
        return sltApplYearTo__;
    }

    /**
     * <p>sltApplYearTo をセットします。
     * @param sltApplYearTo sltApplYearTo
     */
    public void setSltApplYearTo(int sltApplYearTo) {
        sltApplYearTo__ = sltApplYearTo;
    }

    /**
     * <p>sltLastManageDayFr を取得します。
     * @return sltLastManageDayFr
     */
    public int getSltLastManageDayFr() {
        return sltLastManageDayFr__;
    }

    /**
     * <p>sltLastManageDayFr をセットします。
     * @param sltLastManageDayFr sltLastManageDayFr
     */
    public void setSltLastManageDayFr(int sltLastManageDayFr) {
        sltLastManageDayFr__ = sltLastManageDayFr;
    }

    /**
     * <p>sltLastManageDayTo を取得します。
     * @return sltLastManageDayTo
     */
    public int getSltLastManageDayTo() {
        return sltLastManageDayTo__;
    }

    /**
     * <p>sltLastManageDayTo をセットします。
     * @param sltLastManageDayTo sltLastManageDayTo
     */
    public void setSltLastManageDayTo(int sltLastManageDayTo) {
        sltLastManageDayTo__ = sltLastManageDayTo;
    }

    /**
     * <p>sltLastManageMonthFr を取得します。
     * @return sltLastManageMonthFr
     */
    public int getSltLastManageMonthFr() {
        return sltLastManageMonthFr__;
    }

    /**
     * <p>sltLastManageMonthFr をセットします。
     * @param sltLastManageMonthFr sltLastManageMonthFr
     */
    public void setSltLastManageMonthFr(int sltLastManageMonthFr) {
        sltLastManageMonthFr__ = sltLastManageMonthFr;
    }

    /**
     * <p>sltLastManageMonthTo を取得します。
     * @return sltLastManageMonthTo
     */
    public int getSltLastManageMonthTo() {
        return sltLastManageMonthTo__;
    }

    /**
     * <p>sltLastManageMonthTo をセットします。
     * @param sltLastManageMonthTo sltLastManageMonthTo
     */
    public void setSltLastManageMonthTo(int sltLastManageMonthTo) {
        sltLastManageMonthTo__ = sltLastManageMonthTo;
    }

    /**
     * <p>sltLastManageYearFr を取得します。
     * @return sltLastManageYearFr
     */
    public int getSltLastManageYearFr() {
        return sltLastManageYearFr__;
    }

    /**
     * <p>sltLastManageYearFr をセットします。
     * @param sltLastManageYearFr sltLastManageYearFr
     */
    public void setSltLastManageYearFr(int sltLastManageYearFr) {
        sltLastManageYearFr__ = sltLastManageYearFr;
    }

    /**
     * <p>sltLastManageYearTo を取得します。
     * @return sltLastManageYearTo
     */
    public int getSltLastManageYearTo() {
        return sltLastManageYearTo__;
    }

    /**
     * <p>sltLastManageYearTo をセットします。
     * @param sltLastManageYearTo sltLastManageYearTo
     */
    public void setSltLastManageYearTo(int sltLastManageYearTo) {
        sltLastManageYearTo__ = sltLastManageYearTo;
    }

    /**
     * <p>rngWebSearchUse を取得します。
     * @return rngWebSearchUse
     */
    public int getRngWebSearchUse() {
        return rngWebSearchUse__;
    }

    /**
     * <p>rngWebSearchUse をセットします。
     * @param rngWebSearchUse rngWebSearchUse
     */
    public void setRngWebSearchUse(int rngWebSearchUse) {
        rngWebSearchUse__ = rngWebSearchUse;
    }
}
