package jp.groupsession.v2.rng.rng130;

import java.util.List;

import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.model.RingiDataModel;
import jp.groupsession.v2.rng.rng010.Rng010ParamModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議詳細検索画面の情報を保持するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng130ParamModel extends Rng010ParamModel {
    /** 種別 */
    private int rng130Type__ = RngConst.RNG_SORT_JYUSIN;
    /** 承認者 グループ */
    private int sltGroupSid__ = -1;
    /** 承認者 ユーザ */
    private int sltUserSid__ = -1;
    /** キーワード 条件 */
    private int rng130keyKbn__ = 0;
    /** 検索対象 件名 */
    private int rng130searchSubject1__ = 0;
    /** 検索対象 内容 */
    private int rng130searchSubject2__ = 0;
    /** 第１ソートキー */
    private int sltSortKey1__ = RngConst.RNG_SORT_TITLE;
    /** 第１オーダーキー */
    private int rng130orderKey1__ = RngConst.RNG_ORDER_ASC;
    /** 第２ソートキー */
    private int sltSortKey2__ = -1;
    /** 第２オーダーキー */
    private int rng130orderKey2__ = RngConst.RNG_ORDER_ASC;
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
    /** ページコンボ上段 */
    private int rng130pageTop__ = 1;
    /** ページコンボ下段 */
    private int rng130pageBottom__ = 1;

    /** キーワード(検索条件保持用) */
    private String svRngKeyword__ = null;
    /** 種別(検索条件保持用) */
    private int svRng130Type__ = RngConst.RNG_SORT_JYUSIN;
    /** 承認者 グループ(検索条件保持用) */
    private int svGroupSid__ = -1;
    /** 承認者 ユーザ(検索条件保持用) */
    private int svUserSid__ = -1;
    /** キーワード 条件(検索条件保持用) */
    private int svRng130keyKbn__ = 0;
    /** 検索対象 件名(検索条件保持用) */
    private int svRng130searchSubject1__ = 0;
    /** 検索対象 内容(検索条件保持用) */
    private int svRng130searchSubject2__ = 0;
    /** 第１ソートキー(検索条件保持用) */
    private int svSortKey1__ = -1;
    /** 第１オーダーキー(検索条件保持用) */
    private int svRng130orderKey1__ = RngConst.RNG_ORDER_ASC;
    /** 第２ソートキー(検索条件保持用) */
    private int svSortKey2__ = -1;
    /** 第２オーダーキー(検索条件保持用) */
    private int svRng130orderKey2__ = RngConst.RNG_ORDER_ASC;
    /** 選択申請日時 年 From(検索条件保持用) */
    private int svApplYearFr__ = -1;
    /** 選択申請日時 月 From(検索条件保持用) */
    private int svApplMonthFr__ = -1;
    /** 選択申請日時 日 From(検索条件保持用) */
    private int svApplDayFr__ = -1;
    /** 選択申請日時 年 To(検索条件保持用) */
    private int svApplYearTo__ = -1;
    /** 選択申請日時 月 To(検索条件保持用) */
    private int svApplMonthTo__ = -1;
    /** 選択申請日時 日 To(検索条件保持用) */
    private int svApplDayTo__ = -1;
    /** 選択最終処理日時 年 From(検索条件保持用) */
    private int svLastManageYearFr__ = -1;
    /** 選択最終処理日時 月 From(検索条件保持用) */
    private int svLastManageMonthFr__ = -1;
    /** 選択最終処理日時 日 From(検索条件保持用) */
    private int svLastManageDayFr__ = -1;
    /** 選択最終処理日時 年 To(検索条件保持用) */
    private int svLastManageYearTo__ = -1;
    /** 選択最終処理日時 月 To(検索条件保持用) */
    private int svLastManageMonthTo__ = -1;
    /** 選択最終処理日時 日 To(検索条件保持用) */
    private int svLastManageDayTo__ = -1;

    /** 表示用グループリスト */
    private List<LabelValueBean> rng130groupList__ = null;
    /** 表示用ユーザーリスト */
    private List<LabelValueBean> rng130userList__ = null;
    /** 年リスト */
    private List<LabelValueBean> rng130YearList__ = null;
    /** 月リスト */
    private List<LabelValueBean> rng130MonthList__ = null;
    /** 日リスト */
    private List<LabelValueBean> rng130DayList__ = null;
    /** ソートキーリスト */
    private List<LabelValueBean> sortKeyList__ = null;
    /** ページコンボリスト */
    private List < LabelValueBean > pageList__ = null;
    /** 稟議情報一覧 */
    private List<RingiDataModel> rng130rngDataList__ = null;

    /** 検索フラグ */
    private int rng130searchFlg__ = 0;

    /**
     * <p>rng130Type を取得します。
     * @return rng130Type
     */
    public int getRng130Type() {
        return rng130Type__;
    }

    /**
     * <p>rng130Type をセットします。
     * @param rng130Type rng130Type
     */
    public void setRng130Type(int rng130Type) {
        rng130Type__ = rng130Type;
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
     * <p>rng130keyKbn を取得します。
     * @return rng130keyKbn
     */
    public int getRng130keyKbn() {
        return rng130keyKbn__;
    }

    /**
     * <p>rng130keyKbn をセットします。
     * @param rng130keyKbn rng130keyKbn
     */
    public void setRng130keyKbn(int rng130keyKbn) {
        rng130keyKbn__ = rng130keyKbn;
    }

    /**
     * <p>rng130searchSubject1 を取得します。
     * @return rng130searchSubject1
     */
    public int getRng130searchSubject1() {
        return rng130searchSubject1__;
    }

    /**
     * <p>rng130searchSubject1 をセットします。
     * @param rng130searchSubject1 rng130searchSubject1
     */
    public void setRng130searchSubject1(int rng130searchSubject1) {
        rng130searchSubject1__ = rng130searchSubject1;
    }

    /**
     * <p>rng130searchSubject2 を取得します。
     * @return rng130searchSubject2
     */
    public int getRng130searchSubject2() {
        return rng130searchSubject2__;
    }

    /**
     * <p>rng130searchSubject2 をセットします。
     * @param rng130searchSubject2 rng130searchSubject2
     */
    public void setRng130searchSubject2(int rng130searchSubject2) {
        rng130searchSubject2__ = rng130searchSubject2;
    }

    /**
     * <p>sltSortKey1 を取得します。
     * @return sltSortKey1
     */
    public int getSltSortKey1() {
        return sltSortKey1__;
    }

    /**
     * <p>sltSortKey1 をセットします。
     * @param sltSortKey1 sltSortKey1
     */
    public void setSltSortKey1(int sltSortKey1) {
        sltSortKey1__ = sltSortKey1;
    }

    /**
     * <p>rng130orderKey1 を取得します。
     * @return rng130orderKey1
     */
    public int getRng130orderKey1() {
        return rng130orderKey1__;
    }

    /**
     * <p>rng130orderKey1 をセットします。
     * @param rng130orderKey1 rng130orderKey1
     */
    public void setRng130orderKey1(int rng130orderKey1) {
        rng130orderKey1__ = rng130orderKey1;
    }

    /**
     * <p>sltSortKey2 を取得します。
     * @return sltSortKey2
     */
    public int getSltSortKey2() {
        return sltSortKey2__;
    }

    /**
     * <p>sltSortKey2 をセットします。
     * @param sltSortKey2 sltSortKey2
     */
    public void setSltSortKey2(int sltSortKey2) {
        sltSortKey2__ = sltSortKey2;
    }

    /**
     * <p>rng130orderKey2 を取得します。
     * @return rng130orderKey2
     */
    public int getRng130orderKey2() {
        return rng130orderKey2__;
    }

    /**
     * <p>rng130orderKey2 をセットします。
     * @param rng130orderKey2 rng130orderKey2
     */
    public void setRng130orderKey2(int rng130orderKey2) {
        rng130orderKey2__ = rng130orderKey2;
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
     * <p>rng130pageTop を取得します。
     * @return rng130pageTop
     */
    public int getRng130pageTop() {
        return rng130pageTop__;
    }

    /**
     * <p>rng130pageTop をセットします。
     * @param rng130pageTop rng130pageTop
     */
    public void setRng130pageTop(int rng130pageTop) {
        rng130pageTop__ = rng130pageTop;
    }

    /**
     * <p>rng130pageBottom を取得します。
     * @return rng130pageBottom
     */
    public int getRng130pageBottom() {
        return rng130pageBottom__;
    }

    /**
     * <p>rng130pageBottom をセットします。
     * @param rng130pageBottom rng130pageBottom
     */
    public void setRng130pageBottom(int rng130pageBottom) {
        rng130pageBottom__ = rng130pageBottom;
    }

    /**
     * <p>svRngKeyword を取得します。
     * @return svRngKeyword
     */
    public String getSvRngKeyword() {
        return svRngKeyword__;
    }

    /**
     * <p>svRngKeyword をセットします。
     * @param svRngKeyword svRngKeyword
     */
    public void setSvRngKeyword(String svRngKeyword) {
        svRngKeyword__ = svRngKeyword;
    }

    /**
     * <p>svRng130Type を取得します。
     * @return svRng130Type
     */
    public int getSvRng130Type() {
        return svRng130Type__;
    }

    /**
     * <p>svRng130Type をセットします。
     * @param svRng130Type svRng130Type
     */
    public void setSvRng130Type(int svRng130Type) {
        svRng130Type__ = svRng130Type;
    }

    /**
     * <p>svGroupSid を取得します。
     * @return svGroupSid
     */
    public int getSvGroupSid() {
        return svGroupSid__;
    }

    /**
     * <p>svGroupSid をセットします。
     * @param svGroupSid svGroupSid
     */
    public void setSvGroupSid(int svGroupSid) {
        svGroupSid__ = svGroupSid;
    }

    /**
     * <p>svUserSid を取得します。
     * @return svUserSid
     */
    public int getSvUserSid() {
        return svUserSid__;
    }

    /**
     * <p>svUserSid をセットします。
     * @param svUserSid svUserSid
     */
    public void setSvUserSid(int svUserSid) {
        svUserSid__ = svUserSid;
    }

    /**
     * <p>svRng130keyKbn を取得します。
     * @return svRng130keyKbn
     */
    public int getSvRng130keyKbn() {
        return svRng130keyKbn__;
    }

    /**
     * <p>svRng130keyKbn をセットします。
     * @param svRng130keyKbn svRng130keyKbn
     */
    public void setSvRng130keyKbn(int svRng130keyKbn) {
        svRng130keyKbn__ = svRng130keyKbn;
    }

    /**
     * <p>svRng130searchSubject1 を取得します。
     * @return svRng130searchSubject1
     */
    public int getSvRng130searchSubject1() {
        return svRng130searchSubject1__;
    }

    /**
     * <p>svRng130searchSubject1 をセットします。
     * @param svRng130searchSubject1 svRng130searchSubject1
     */
    public void setSvRng130searchSubject1(int svRng130searchSubject1) {
        svRng130searchSubject1__ = svRng130searchSubject1;
    }

    /**
     * <p>svRng130searchSubject2 を取得します。
     * @return svRng130searchSubject2
     */
    public int getSvRng130searchSubject2() {
        return svRng130searchSubject2__;
    }

    /**
     * <p>svRng130searchSubject2 をセットします。
     * @param svRng130searchSubject2 svRng130searchSubject2
     */
    public void setSvRng130searchSubject2(int svRng130searchSubject2) {
        svRng130searchSubject2__ = svRng130searchSubject2;
    }

    /**
     * <p>svSortKey1 を取得します。
     * @return svSortKey1
     */
    public int getSvSortKey1() {
        return svSortKey1__;
    }

    /**
     * <p>svSortKey1 をセットします。
     * @param svSortKey1 svSortKey1
     */
    public void setSvSortKey1(int svSortKey1) {
        svSortKey1__ = svSortKey1;
    }

    /**
     * <p>svRng130orderKey1 を取得します。
     * @return svRng130orderKey1
     */
    public int getSvRng130orderKey1() {
        return svRng130orderKey1__;
    }

    /**
     * <p>svRng130orderKey1 をセットします。
     * @param svRng130orderKey1 svRng130orderKey1
     */
    public void setSvRng130orderKey1(int svRng130orderKey1) {
        svRng130orderKey1__ = svRng130orderKey1;
    }

    /**
     * <p>svSortKey2 を取得します。
     * @return svSortKey2
     */
    public int getSvSortKey2() {
        return svSortKey2__;
    }

    /**
     * <p>svSortKey2 をセットします。
     * @param svSortKey2 svSortKey2
     */
    public void setSvSortKey2(int svSortKey2) {
        svSortKey2__ = svSortKey2;
    }

    /**
     * <p>svRng130orderKey2 を取得します。
     * @return svRng130orderKey2
     */
    public int getSvRng130orderKey2() {
        return svRng130orderKey2__;
    }

    /**
     * <p>svRng130orderKey2 をセットします。
     * @param svRng130orderKey2 svRng130orderKey2
     */
    public void setSvRng130orderKey2(int svRng130orderKey2) {
        svRng130orderKey2__ = svRng130orderKey2;
    }

    /**
     * <p>svApplYearFr を取得します。
     * @return svApplYearFr
     */
    public int getSvApplYearFr() {
        return svApplYearFr__;
    }

    /**
     * <p>svApplYearFr をセットします。
     * @param svApplYearFr svApplYearFr
     */
    public void setSvApplYearFr(int svApplYearFr) {
        svApplYearFr__ = svApplYearFr;
    }

    /**
     * <p>svApplMonthFr を取得します。
     * @return svApplMonthFr
     */
    public int getSvApplMonthFr() {
        return svApplMonthFr__;
    }

    /**
     * <p>svApplMonthFr をセットします。
     * @param svApplMonthFr svApplMonthFr
     */
    public void setSvApplMonthFr(int svApplMonthFr) {
        svApplMonthFr__ = svApplMonthFr;
    }

    /**
     * <p>svApplDayFr を取得します。
     * @return svApplDayFr
     */
    public int getSvApplDayFr() {
        return svApplDayFr__;
    }

    /**
     * <p>svApplDayFr をセットします。
     * @param svApplDayFr svApplDayFr
     */
    public void setSvApplDayFr(int svApplDayFr) {
        svApplDayFr__ = svApplDayFr;
    }

    /**
     * <p>svApplYearTo を取得します。
     * @return svApplYearTo
     */
    public int getSvApplYearTo() {
        return svApplYearTo__;
    }

    /**
     * <p>svApplYearTo をセットします。
     * @param svApplYearTo svApplYearTo
     */
    public void setSvApplYearTo(int svApplYearTo) {
        svApplYearTo__ = svApplYearTo;
    }

    /**
     * <p>svApplMonthTo を取得します。
     * @return svApplMonthTo
     */
    public int getSvApplMonthTo() {
        return svApplMonthTo__;
    }

    /**
     * <p>svApplMonthTo をセットします。
     * @param svApplMonthTo svApplMonthTo
     */
    public void setSvApplMonthTo(int svApplMonthTo) {
        svApplMonthTo__ = svApplMonthTo;
    }

    /**
     * <p>svApplDayTo を取得します。
     * @return svApplDayTo
     */
    public int getSvApplDayTo() {
        return svApplDayTo__;
    }

    /**
     * <p>svApplDayTo をセットします。
     * @param svApplDayTo svApplDayTo
     */
    public void setSvApplDayTo(int svApplDayTo) {
        svApplDayTo__ = svApplDayTo;
    }

    /**
     * <p>svLastManageYearFr を取得します。
     * @return svLastManageYearFr
     */
    public int getSvLastManageYearFr() {
        return svLastManageYearFr__;
    }

    /**
     * <p>svLastManageYearFr をセットします。
     * @param svLastManageYearFr svLastManageYearFr
     */
    public void setSvLastManageYearFr(int svLastManageYearFr) {
        svLastManageYearFr__ = svLastManageYearFr;
    }

    /**
     * <p>svLastManageMonthFr を取得します。
     * @return svLastManageMonthFr
     */
    public int getSvLastManageMonthFr() {
        return svLastManageMonthFr__;
    }

    /**
     * <p>svLastManageMonthFr をセットします。
     * @param svLastManageMonthFr svLastManageMonthFr
     */
    public void setSvLastManageMonthFr(int svLastManageMonthFr) {
        svLastManageMonthFr__ = svLastManageMonthFr;
    }

    /**
     * <p>svLastManageDayFr を取得します。
     * @return svLastManageDayFr
     */
    public int getSvLastManageDayFr() {
        return svLastManageDayFr__;
    }

    /**
     * <p>svLastManageDayFr をセットします。
     * @param svLastManageDayFr svLastManageDayFr
     */
    public void setSvLastManageDayFr(int svLastManageDayFr) {
        svLastManageDayFr__ = svLastManageDayFr;
    }

    /**
     * <p>svLastManageYearTo を取得します。
     * @return svLastManageYearTo
     */
    public int getSvLastManageYearTo() {
        return svLastManageYearTo__;
    }

    /**
     * <p>svLastManageYearTo をセットします。
     * @param svLastManageYearTo svLastManageYearTo
     */
    public void setSvLastManageYearTo(int svLastManageYearTo) {
        svLastManageYearTo__ = svLastManageYearTo;
    }

    /**
     * <p>svLastManageMonthTo を取得します。
     * @return svLastManageMonthTo
     */
    public int getSvLastManageMonthTo() {
        return svLastManageMonthTo__;
    }

    /**
     * <p>svLastManageMonthTo をセットします。
     * @param svLastManageMonthTo svLastManageMonthTo
     */
    public void setSvLastManageMonthTo(int svLastManageMonthTo) {
        svLastManageMonthTo__ = svLastManageMonthTo;
    }

    /**
     * <p>svLastManageDayTo を取得します。
     * @return svLastManageDayTo
     */
    public int getSvLastManageDayTo() {
        return svLastManageDayTo__;
    }

    /**
     * <p>svLastManageDayTo をセットします。
     * @param svLastManageDayTo svLastManageDayTo
     */
    public void setSvLastManageDayTo(int svLastManageDayTo) {
        svLastManageDayTo__ = svLastManageDayTo;
    }

    /**
     * <p>rng130groupList を取得します。
     * @return rng130groupList
     */
    public List<LabelValueBean> getRng130groupList() {
        return rng130groupList__;
    }

    /**
     * <p>rng130groupList をセットします。
     * @param rng130groupList rng130groupList
     */
    public void setRng130groupList(List<LabelValueBean> rng130groupList) {
        rng130groupList__ = rng130groupList;
    }

    /**
     * <p>rng130userList を取得します。
     * @return rng130userList
     */
    public List<LabelValueBean> getRng130userList() {
        return rng130userList__;
    }

    /**
     * <p>rng130userList をセットします。
     * @param rng130userList rng130userList
     */
    public void setRng130userList(List<LabelValueBean> rng130userList) {
        rng130userList__ = rng130userList;
    }

    /**
     * <p>rng130YearList を取得します。
     * @return rng130YearList
     */
    public List<LabelValueBean> getRng130YearList() {
        return rng130YearList__;
    }

    /**
     * <p>rng130YearList をセットします。
     * @param rng130YearList rng130YearList
     */
    public void setRng130YearList(List<LabelValueBean> rng130YearList) {
        rng130YearList__ = rng130YearList;
    }

    /**
     * <p>rng130MonthList を取得します。
     * @return rng130MonthList
     */
    public List<LabelValueBean> getRng130MonthList() {
        return rng130MonthList__;
    }

    /**
     * <p>rng130MonthList をセットします。
     * @param rng130MonthList rng130MonthList
     */
    public void setRng130MonthList(List<LabelValueBean> rng130MonthList) {
        rng130MonthList__ = rng130MonthList;
    }

    /**
     * <p>rng130DayList を取得します。
     * @return rng130DayList
     */
    public List<LabelValueBean> getRng130DayList() {
        return rng130DayList__;
    }

    /**
     * <p>rng130DayList をセットします。
     * @param rng130DayList rng130DayList
     */
    public void setRng130DayList(List<LabelValueBean> rng130DayList) {
        rng130DayList__ = rng130DayList;
    }

    /**
     * <p>sortKeyList を取得します。
     * @return sortKeyList
     */
    public List<LabelValueBean> getSortKeyList() {
        return sortKeyList__;
    }

    /**
     * <p>sortKeyList をセットします。
     * @param sortKeyList sortKeyList
     */
    public void setSortKeyList(List<LabelValueBean> sortKeyList) {
        sortKeyList__ = sortKeyList;
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
     * <p>rng130rngDataList を取得します。
     * @return rng130rngDataList
     */
    public List<RingiDataModel> getRng130rngDataList() {
        return rng130rngDataList__;
    }

    /**
     * <p>rng130rngDataList をセットします。
     * @param rng130rngDataList rng130rngDataList
     */
    public void setRng130rngDataList(List<RingiDataModel> rng130rngDataList) {
        rng130rngDataList__ = rng130rngDataList;
    }

    /**
     * <p>rng130searchFlg を取得します。
     * @return rng130searchFlg
     */
    public int getRng130searchFlg() {
        return rng130searchFlg__;
    }

    /**
     * <p>rng130searchFlg をセットします。
     * @param rng130searchFlg rng130searchFlg
     */
    public void setRng130searchFlg(int rng130searchFlg) {
        rng130searchFlg__ = rng130searchFlg;
    }
}