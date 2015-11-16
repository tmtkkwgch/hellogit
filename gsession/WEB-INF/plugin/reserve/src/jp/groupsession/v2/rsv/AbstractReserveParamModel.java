package jp.groupsession.v2.rsv;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.model.AbstractParamModel;

/**
 * <br>[機  能] 施設予約プラグインで共通使用するフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class AbstractReserveParamModel extends AbstractParamModel {

    /** 期間 未設定 選択 */
    public static final int DATEKBN_SELECT = 1;

    /** 管理者フラグ */
    private boolean rsvAdmFlg__;
    /** 施設グループ編集可否フラグ true:編集可 false:編集不可 */
    private boolean rsvGroupEditFlg__ = false;
    /** 施設グループコンボ選択値 */
    private int rsvSelectedGrpSid__ = -2;
    /** 表示日付 */
    private String rsvDspFrom__ = null;
    /** 選択済み施設SID */
    private int rsvSelectedSisetuSid__ = -1;
    /** 選択済み施設予約SID */
    private int rsvSelectedYoyakuSid__;
    /** 選択済日付 */
    private String rsvSelectedDate__ = null;
    /** 戻り先画面 */
    private String rsvBackPgId__ = null;
    /** 施設予約 印刷区分使用区分 */
    private int rsvPrintUseKbn__ = GSConstReserve.RSV_PRINT_USE_NO;

    //施設利用状況照会画面

    /** 開始年 */
    private int rsv100selectedFromYear__ = -1;
    /** 開始月 */
    private int rsv100selectedFromMonth__ = -1;
    /** 開始日 */
    private int rsv100selectedFromDay__ = -1;
    /** 終了年 */
    private int rsv100selectedToYear__ = -1;
    /** 終了月 */
    private int rsv100selectedToMonth__ = -1;
    /** 終了日 */
    private int rsv100selectedToDay__ = -1;
    /** 期間 未設定 */
    private int rsv100dateKbn__ = 0;
    //フラグ
    /** 初期表示フラグ */
    private boolean rsv100InitFlg__ = true;
    /** 検索フラグ */
    private boolean rsv100SearchFlg__ = false;
    /** 検索フラグ(保存) */
    private boolean rsv100SearchSvFlg__ = false;
    //キー値
    /** ソートキー */
    private int rsv100SortKey__ = GSConstReserve.RSV_SORT_FROM;
    /** オーダーキー */
    private int rsv100OrderKey__ = GSConst.ORDER_KEY_ASC;
    /** ページ(上) */
    private int rsv100PageTop__ = 1;
    /** ページ(下) */
    private int rsv100PageBottom__ = 1;

    /** キーワード */
    private String rsv100KeyWord__ = null;
    /** 検索条件 */
    private int rsv100SearchCondition__ = 0;
    /** 検索対象 (利用目的) */
    private int rsv100TargetMok__ = 0;
    /** 検索対象 (内容) */
    private int rsv100TargetNiyo__ = 0;
    /** 承認状況 */
    private int rsv100apprStatus__ = GSConstReserve.SRH_APPRSTATUS_ALL;

    /** CSV出力項目 */
    private String[] rsv100CsvOutField__ = null;

    /** 第一キー選択値 */
    private int rsv100SelectedKey1__ = 1;
    /** 第二キー選択値 */
    private int rsv100SelectedKey2__ = 2;
    /** 第一キー ソート */
    private int rsv100SelectedKey1Sort__ = 0;
    /** 第二キー ソート */
    private int rsv100SelectedKey2Sort__ = 0;

    //セーブ用フィールド
    /** 開始年 */
    private int rsv100svFromYear__;
    /** 開始月 */
    private int rsv100svFromMonth__;
    /** 開始日 */
    private int rsv100svFromDay__;
    /** 終了年 */
    private int rsv100svToYear__;
    /** 終了月 */
    private int rsv100svToMonth__;
    /** 終了日 */
    private int rsv100svToDay__;
    /** 期間 未設定 */
    private int rsv100svDateKbn__ = 0;
    /** グループ1 */
    private int rsv100svGrp1__;
    /** グループ2 */
    private int rsv100svGrp2__;

    /** キーワード */
    private String rsv100svKeyWord__;
    /** 検索条件 */
    private int rsv100svSearchCondition__;
    /** 検索対象 (利用目的) */
    private int rsv100svTargetMok__;
    /** 検索対象 (内容) */
    private int rsv100svTargetNiyo__;
    /** 検索対象 (承認状況) */
    private int rsv100svApprStatus__;
    /** 第一キー選択値 */
    private int rsv100svSelectedKey1__;
    /** 第二キー選択値 */
    private int rsv100svSelectedKey2__;
    /** 第一キー ソート */
    private int rsv100svSelectedKey1Sort__;
    /** 第二キー ソート */
    private int rsv100svSelectedKey2Sort__;
    /** 選択施設グループの施設区分(save) */
    private int rsv100svSelectSisKbn__;

    /**
     * <p>rsv100svFromDay__ を取得します。
     * @return rsv100svFromDay
     */
    public int getRsv100svFromDay() {
        return rsv100svFromDay__;
    }
    /**
     * <p>rsv100svFromDay__ をセットします。
     * @param rsv100svFromDay rsv100svFromDay__
     */
    public void setRsv100svFromDay(int rsv100svFromDay) {
        rsv100svFromDay__ = rsv100svFromDay;
    }
    /**
     * <p>rsv100svFromMonth__ を取得します。
     * @return rsv100svFromMonth
     */
    public int getRsv100svFromMonth() {
        return rsv100svFromMonth__;
    }
    /**
     * <p>rsv100svFromMonth__ をセットします。
     * @param rsv100svFromMonth rsv100svFromMonth__
     */
    public void setRsv100svFromMonth(int rsv100svFromMonth) {
        rsv100svFromMonth__ = rsv100svFromMonth;
    }
    /**
     * <p>rsv100svFromYear__ を取得します。
     * @return rsv100svFromYear
     */
    public int getRsv100svFromYear() {
        return rsv100svFromYear__;
    }
    /**
     * <p>rsv100svFromYear__ をセットします。
     * @param rsv100svFromYear rsv100svFromYear__
     */
    public void setRsv100svFromYear(int rsv100svFromYear) {
        rsv100svFromYear__ = rsv100svFromYear;
    }
    /**
     * <p>rsv100svGrp1__ を取得します。
     * @return rsv100svGrp1
     */
    public int getRsv100svGrp1() {
        return rsv100svGrp1__;
    }
    /**
     * <p>rsv100svGrp1__ をセットします。
     * @param rsv100svGrp1 rsv100svGrp1__
     */
    public void setRsv100svGrp1(int rsv100svGrp1) {
        rsv100svGrp1__ = rsv100svGrp1;
    }
    /**
     * <p>rsv100svGrp2__ を取得します。
     * @return rsv100svGrp2
     */
    public int getRsv100svGrp2() {
        return rsv100svGrp2__;
    }
    /**
     * <p>rsv100svGrp2__ をセットします。
     * @param rsv100svGrp2 rsv100svGrp2__
     */
    public void setRsv100svGrp2(int rsv100svGrp2) {
        rsv100svGrp2__ = rsv100svGrp2;
    }
    /**
     * <p>rsv100svKeyWord__ を取得します。
     * @return rsv100svKeyWord
     */
    public String getRsv100svKeyWord() {
        return rsv100svKeyWord__;
    }
    /**
     * <p>rsv100svKeyWord__ をセットします。
     * @param rsv100svKeyWord rsv100svKeyWord__
     */
    public void setRsv100svKeyWord(String rsv100svKeyWord) {
        rsv100svKeyWord__ = rsv100svKeyWord;
    }
    /**
     * <p>rsv100svSearchCondition__ を取得します。
     * @return rsv100svSearchCondition
     */
    public int getRsv100svSearchCondition() {
        return rsv100svSearchCondition__;
    }
    /**
     * <p>rsv100svSearchCondition__ をセットします。
     * @param rsv100svSearchCondition rsv100svSearchCondition__
     */
    public void setRsv100svSearchCondition(int rsv100svSearchCondition) {
        rsv100svSearchCondition__ = rsv100svSearchCondition;
    }
    /**
     * <p>rsv100svSelectedKey1__ を取得します。
     * @return rsv100svSelectedKey1
     */
    public int getRsv100svSelectedKey1() {
        return rsv100svSelectedKey1__;
    }
    /**
     * <p>rsv100svSelectedKey1__ をセットします。
     * @param rsv100svSelectedKey1 rsv100svSelectedKey1__
     */
    public void setRsv100svSelectedKey1(int rsv100svSelectedKey1) {
        rsv100svSelectedKey1__ = rsv100svSelectedKey1;
    }
    /**
     * <p>rsv100svSelectedKey1Sort__ を取得します。
     * @return rsv100svSelectedKey1Sort
     */
    public int getRsv100svSelectedKey1Sort() {
        return rsv100svSelectedKey1Sort__;
    }
    /**
     * <p>rsv100svSelectedKey1Sort__ をセットします。
     * @param rsv100svSelectedKey1Sort rsv100svSelectedKey1Sort__
     */
    public void setRsv100svSelectedKey1Sort(int rsv100svSelectedKey1Sort) {
        rsv100svSelectedKey1Sort__ = rsv100svSelectedKey1Sort;
    }
    /**
     * <p>rsv100svSelectedKey2__ を取得します。
     * @return rsv100svSelectedKey2
     */
    public int getRsv100svSelectedKey2() {
        return rsv100svSelectedKey2__;
    }
    /**
     * <p>rsv100svSelectedKey2__ をセットします。
     * @param rsv100svSelectedKey2 rsv100svSelectedKey2__
     */
    public void setRsv100svSelectedKey2(int rsv100svSelectedKey2) {
        rsv100svSelectedKey2__ = rsv100svSelectedKey2;
    }
    /**
     * <p>rsv100svSelectedKey2Sort__ を取得します。
     * @return rsv100svSelectedKey2Sort
     */
    public int getRsv100svSelectedKey2Sort() {
        return rsv100svSelectedKey2Sort__;
    }
    /**
     * <p>rsv100svSelectedKey2Sort__ をセットします。
     * @param rsv100svSelectedKey2Sort rsv100svSelectedKey2Sort__
     */
    public void setRsv100svSelectedKey2Sort(int rsv100svSelectedKey2Sort) {
        rsv100svSelectedKey2Sort__ = rsv100svSelectedKey2Sort;
    }
    /**
     * <p>rsv100svTargetMok__ を取得します。
     * @return rsv100svTargetMok
     */
    public int getRsv100svTargetMok() {
        return rsv100svTargetMok__;
    }
    /**
     * <p>rsv100svTargetMok__ をセットします。
     * @param rsv100svTargetMok rsv100svTargetMok__
     */
    public void setRsv100svTargetMok(int rsv100svTargetMok) {
        rsv100svTargetMok__ = rsv100svTargetMok;
    }
    /**
     * <p>rsv100svTargetNiyo__ を取得します。
     * @return rsv100svTargetNiyo
     */
    public int getRsv100svTargetNiyo() {
        return rsv100svTargetNiyo__;
    }
    /**
     * <p>rsv100svTargetNiyo__ をセットします。
     * @param rsv100svTargetNiyo rsv100svTargetNiyo__
     */
    public void setRsv100svTargetNiyo(int rsv100svTargetNiyo) {
        rsv100svTargetNiyo__ = rsv100svTargetNiyo;
    }
    /**
     * <p>rsv100svToDay__ を取得します。
     * @return rsv100svToDay
     */
    public int getRsv100svToDay() {
        return rsv100svToDay__;
    }
    /**
     * <p>rsv100svToDay__ をセットします。
     * @param rsv100svToDay rsv100svToDay__
     */
    public void setRsv100svToDay(int rsv100svToDay) {
        rsv100svToDay__ = rsv100svToDay;
    }
    /**
     * <p>rsv100svToMonth__ を取得します。
     * @return rsv100svToMonth
     */
    public int getRsv100svToMonth() {
        return rsv100svToMonth__;
    }
    /**
     * <p>rsv100svToMonth__ をセットします。
     * @param rsv100svToMonth rsv100svToMonth__
     */
    public void setRsv100svToMonth(int rsv100svToMonth) {
        rsv100svToMonth__ = rsv100svToMonth;
    }
    /**
     * <p>rsv100svToYear__ を取得します。
     * @return rsv100svToYear
     */
    public int getRsv100svToYear() {
        return rsv100svToYear__;
    }
    /**
     * <p>rsv100svToYear__ をセットします。
     * @param rsv100svToYear rsv100svToYear__
     */
    public void setRsv100svToYear(int rsv100svToYear) {
        rsv100svToYear__ = rsv100svToYear;
    }
    /**
     * <p>rsv100SelectedKey1__ を取得します。
     * @return rsv100SelectedKey1
     */
    public int getRsv100SelectedKey1() {
        return rsv100SelectedKey1__;
    }
    /**
     * <p>rsv100SelectedKey1__ をセットします。
     * @param rsv100SelectedKey1 rsv100SelectedKey1__
     */
    public void setRsv100SelectedKey1(int rsv100SelectedKey1) {
        rsv100SelectedKey1__ = rsv100SelectedKey1;
    }
    /**
     * <p>rsv100SelectedKey1Sort__ を取得します。
     * @return rsv100SelectedKey1Sort
     */
    public int getRsv100SelectedKey1Sort() {
        return rsv100SelectedKey1Sort__;
    }
    /**
     * <p>rsv100SelectedKey1Sort__ をセットします。
     * @param rsv100SelectedKey1Sort rsv100SelectedKey1Sort__
     */
    public void setRsv100SelectedKey1Sort(int rsv100SelectedKey1Sort) {
        rsv100SelectedKey1Sort__ = rsv100SelectedKey1Sort;
    }
    /**
     * <p>rsv100SelectedKey2__ を取得します。
     * @return rsv100SelectedKey2
     */
    public int getRsv100SelectedKey2() {
        return rsv100SelectedKey2__;
    }
    /**
     * <p>rsv100SelectedKey2__ をセットします。
     * @param rsv100SelectedKey2 rsv100SelectedKey2__
     */
    public void setRsv100SelectedKey2(int rsv100SelectedKey2) {
        rsv100SelectedKey2__ = rsv100SelectedKey2;
    }
    /**
     * <p>rsv100SelectedKey2Sort__ を取得します。
     * @return rsv100SelectedKey2Sort
     */
    public int getRsv100SelectedKey2Sort() {
        return rsv100SelectedKey2Sort__;
    }
    /**
     * <p>rsv100SelectedKey2Sort__ をセットします。
     * @param rsv100SelectedKey2Sort rsv100SelectedKey2Sort__
     */
    public void setRsv100SelectedKey2Sort(int rsv100SelectedKey2Sort) {
        rsv100SelectedKey2Sort__ = rsv100SelectedKey2Sort;
    }
    /**
     * <p>rsv100KeyWord__ を取得します。
     * @return rsv100KeyWord
     */
    public String getRsv100KeyWord() {
        return rsv100KeyWord__;
    }
    /**
     * <p>rsv100KeyWord__ をセットします。
     * @param rsv100KeyWord rsv100KeyWord__
     */
    public void setRsv100KeyWord(String rsv100KeyWord) {
        rsv100KeyWord__ = rsv100KeyWord;
    }
    /**
     * <p>rsv100SearchCondition__ を取得します。
     * @return rsv100SearchCondition
     */
    public int getRsv100SearchCondition() {
        return rsv100SearchCondition__;
    }
    /**
     * <p>rsv100SearchCondition__ をセットします。
     * @param rsv100SearchCondition rsv100SearchCondition__
     */
    public void setRsv100SearchCondition(int rsv100SearchCondition) {
        rsv100SearchCondition__ = rsv100SearchCondition;
    }
    /**
     * <p>rsv100TargetMok__ を取得します。
     * @return rsv100TargetMok
     */
    public int getRsv100TargetMok() {
        return rsv100TargetMok__;
    }
    /**
     * <p>rsv100TargetMok__ をセットします。
     * @param rsv100TargetMok rsv100TargetMok__
     */
    public void setRsv100TargetMok(int rsv100TargetMok) {
        rsv100TargetMok__ = rsv100TargetMok;
    }
    /**
     * <p>rsv100TargetNiyo__ を取得します。
     * @return rsv100TargetNiyo
     */
    public int getRsv100TargetNiyo() {
        return rsv100TargetNiyo__;
    }
    /**
     * <p>rsv100TargetNiyo__ をセットします。
     * @param rsv100TargetNiyo rsv100TargetNiyo__
     */
    public void setRsv100TargetNiyo(int rsv100TargetNiyo) {
        rsv100TargetNiyo__ = rsv100TargetNiyo;
    }
    /**
     * <p>rsvSelectedYoyakuSid__ を取得します。
     * @return rsvSelectedYoyakuSid
     */
    public int getRsvSelectedYoyakuSid() {
        return rsvSelectedYoyakuSid__;
    }
    /**
     * <p>rsvSelectedYoyakuSid__ をセットします。
     * @param rsvSelectedYoyakuSid rsvSelectedYoyakuSid__
     */
    public void setRsvSelectedYoyakuSid(int rsvSelectedYoyakuSid) {
        rsvSelectedYoyakuSid__ = rsvSelectedYoyakuSid;
    }
    /**
     * <p>rsvSelectedDate__ を取得します。
     * @return rsvSelectedDate
     */
    public String getRsvSelectedDate() {
        return rsvSelectedDate__;
    }
    /**
     * <p>rsvSelectedDate__ をセットします。
     * @param rsvSelectedDate rsvSelectedDate__
     */
    public void setRsvSelectedDate(String rsvSelectedDate) {
        rsvSelectedDate__ = rsvSelectedDate;
    }
    /**
     * <p>rsvAdmFlg__ を取得します。
     * @return rsvAdmFlg
     */
    public boolean isRsvAdmFlg() {
        return rsvAdmFlg__;
    }
    /**
     * <p>rsvAdmFlg__ をセットします。
     * @param rsvAdmFlg rsvAdmFlg__
     */
    public void setRsvAdmFlg(boolean rsvAdmFlg) {
        rsvAdmFlg__ = rsvAdmFlg;
    }
    /**
     * <p>rsvGroupEditFlg__ を取得します。
     * @return rsvGroupEditFlg
     */
    public boolean isRsvGroupEditFlg() {
        return rsvGroupEditFlg__;
    }
    /**
     * <p>rsvGroupEditFlg__ をセットします。
     * @param rsvGroupEditFlg rsvGroupEditFlg__
     */
    public void setRsvGroupEditFlg(boolean rsvGroupEditFlg) {
        rsvGroupEditFlg__ = rsvGroupEditFlg;
    }
    /**
     * <p>rsvSelectedGrpSid__ を取得します。
     * @return rsvSelectedGrpSid
     */
    public int getRsvSelectedGrpSid() {
        return rsvSelectedGrpSid__;
    }
    /**
     * <p>rsvSelectedGrpSid__ をセットします。
     * @param rsvSelectedGrpSid rsvSelectedGrpSid__
     */
    public void setRsvSelectedGrpSid(int rsvSelectedGrpSid) {
        rsvSelectedGrpSid__ = rsvSelectedGrpSid;
    }
    /**
     * <p>rsvDspFrom__ を取得します。
     * @return rsvDspFrom
     */
    public String getRsvDspFrom() {
        return rsvDspFrom__;
    }
    /**
     * <p>rsvDspFrom__ をセットします。
     * @param rsvDspFrom rsvDspFrom__
     */
    public void setRsvDspFrom(String rsvDspFrom) {
        rsvDspFrom__ = rsvDspFrom;
    }
    /**
     * <p>rsvSelectedSisetuSid__ を取得します。
     * @return rsvSelectedSisetuSid
     */
    public int getRsvSelectedSisetuSid() {
        return rsvSelectedSisetuSid__;
    }
    /**
     * <p>rsvSelectedSisetuSid__ をセットします。
     * @param rsvSelectedSisetuSid rsvSelectedSisetuSid__
     */
    public void setRsvSelectedSisetuSid(int rsvSelectedSisetuSid) {
        rsvSelectedSisetuSid__ = rsvSelectedSisetuSid;
    }
    /**
     * <p>rsvBackPgId を取得します。
     * @return rsvBackPgId
     */
    public String getRsvBackPgId() {
        return rsvBackPgId__;
    }
    /**
     * <p>rsvBackPgId をセットします。
     * @param rsvBackPgId rsvBackPgId
     */
    public void setRsvBackPgId(String rsvBackPgId) {
        rsvBackPgId__ = rsvBackPgId;
    }
    /**
     * <p>rsv100selectedFromMonth を取得します。
     * @return rsv100selectedFromMonth
     */
    public int getRsv100selectedFromMonth() {
        return rsv100selectedFromMonth__;
    }

    /**
     * <p>rsv100selectedFromMonth をセットします。
     * @param rsv100selectedFromMonth rsv100selectedFromMonth
     */
    public void setRsv100selectedFromMonth(int rsv100selectedFromMonth) {
        rsv100selectedFromMonth__ = rsv100selectedFromMonth;
    }

    /**
     * <p>rsv100selectedFromYear を取得します。
     * @return rsv100selectedFromYear
     */
    public int getRsv100selectedFromYear() {
        return rsv100selectedFromYear__;
    }

    /**
     * <p>rsv100selectedFromYear をセットします。
     * @param rsv100selectedFromYear rsv100selectedFromYear
     */
    public void setRsv100selectedFromYear(int rsv100selectedFromYear) {
        rsv100selectedFromYear__ = rsv100selectedFromYear;
    }
    /**
     * <p>rsv100selectedToMonth を取得します。
     * @return rsv100selectedToMonth
     */
    public int getRsv100selectedToMonth() {
        return rsv100selectedToMonth__;
    }

    /**
     * <p>rsv100selectedToMonth をセットします。
     * @param rsv100selectedToMonth rsv100selectedToMonth
     */
    public void setRsv100selectedToMonth(int rsv100selectedToMonth) {
        rsv100selectedToMonth__ = rsv100selectedToMonth;
    }

    /**
     * <p>rsv100selectedToYear を取得します。
     * @return rsv100selectedToYear
     */
    public int getRsv100selectedToYear() {
        return rsv100selectedToYear__;
    }

    /**
     * <p>rsv100selectedToYear をセットします。
     * @param rsv100selectedToYear rsv100selectedToYear
     */
    public void setRsv100selectedToYear(int rsv100selectedToYear) {
        rsv100selectedToYear__ = rsv100selectedToYear;
    }
    /**
     * <p>rsv100selectedFromDay を取得します。
     * @return rsv100selectedFromDay
     */
    public int getRsv100selectedFromDay() {
        return rsv100selectedFromDay__;
    }

    /**
     * <p>rsv100selectedFromDay をセットします。
     * @param rsv100selectedFromDay rsv100selectedFromDay
     */
    public void setRsv100selectedFromDay(int rsv100selectedFromDay) {
        rsv100selectedFromDay__ = rsv100selectedFromDay;
    }

    /**
     * <p>rsv100selectedToDay を取得します。
     * @return rsv100selectedToDay
     */
    public int getRsv100selectedToDay() {
        return rsv100selectedToDay__;
    }

    /**
     * <p>rsv100selectedToDay をセットします。
     * @param rsv100selectedToDay rsv100selectedToDay
     */
    public void setRsv100selectedToDay(int rsv100selectedToDay) {
        rsv100selectedToDay__ = rsv100selectedToDay;
    }

    /**
     * <p>rsv100InitFlg を取得します。
     * @return rsv100InitFlg
     */
    public boolean isRsv100InitFlg() {
        return rsv100InitFlg__;
    }

    /**
     * <p>rsv100InitFlg をセットします。
     * @param rsv100InitFlg rsv100InitFlg
     */
    public void setRsv100InitFlg(boolean rsv100InitFlg) {
        rsv100InitFlg__ = rsv100InitFlg;
    }
    /**
     * <p>rsv100OrderKey を取得します。
     * @return rsv100OrderKey
     */
    public int getRsv100OrderKey() {
        return rsv100OrderKey__;
    }

    /**
     * <p>rsv100OrderKey をセットします。
     * @param rsv100OrderKey rsv100OrderKey
     */
    public void setRsv100OrderKey(int rsv100OrderKey) {
        rsv100OrderKey__ = rsv100OrderKey;
    }

    /**
     * <p>rsv100PageBottom を取得します。
     * @return rsv100PageBottom
     */
    public int getRsv100PageBottom() {
        return rsv100PageBottom__;
    }

    /**
     * <p>rsv100PageBottom をセットします。
     * @param rsv100PageBottom rsv100PageBottom
     */
    public void setRsv100PageBottom(int rsv100PageBottom) {
        rsv100PageBottom__ = rsv100PageBottom;
    }
    /**
     * <p>rsv100PageTop を取得します。
     * @return rsv100PageTop
     */
    public int getRsv100PageTop() {
        return rsv100PageTop__;
    }

    /**
     * <p>rsv100PageTop をセットします。
     * @param rsv100PageTop rsv100PageTop
     */
    public void setRsv100PageTop(int rsv100PageTop) {
        rsv100PageTop__ = rsv100PageTop;
    }

    /**
     * <p>rsv100SearchFlg を取得します。
     * @return rsv100SearchFlg
     */
    public boolean isRsv100SearchFlg() {
        return rsv100SearchFlg__;
    }

    /**
     * <p>rsv100SearchFlg をセットします。
     * @param rsv100SearchFlg rsv100SearchFlg
     */
    public void setRsv100SearchFlg(boolean rsv100SearchFlg) {
        rsv100SearchFlg__ = rsv100SearchFlg;
    }
    /**
     * <p>rsv100SortKey を取得します。
     * @return rsv100SortKey
     */
    public int getRsv100SortKey() {
        return rsv100SortKey__;
    }

    /**
     * <p>rsv100SortKey をセットします。
     * @param rsv100SortKey rsv100SortKey
     */
    public void setRsv100SortKey(int rsv100SortKey) {
        rsv100SortKey__ = rsv100SortKey;
    }
    /**
     * <p>rsv100CsvOutField を取得します。
     * @return rsv100CsvOutField
     */
    public String[] getRsv100CsvOutField() {
        return rsv100CsvOutField__;
    }
    /**
     * <p>rsv100CsvOutField をセットします。
     * @param rsv100CsvOutField rsv100CsvOutField
     */
    public void setRsv100CsvOutField(String[] rsv100CsvOutField) {
        rsv100CsvOutField__ = rsv100CsvOutField;
    }
    /**
     * <p>rsv100SearchSvFlg を取得します。
     * @return rsv100SearchSvFlg
     */
    public boolean isRsv100SearchSvFlg() {
        return rsv100SearchSvFlg__;
    }
    /**
     * <p>rsv100SearchSvFlg をセットします。
     * @param rsv100SearchSvFlg rsv100SearchSvFlg
     */
    public void setRsv100SearchSvFlg(boolean rsv100SearchSvFlg) {
        rsv100SearchSvFlg__ = rsv100SearchSvFlg;
    }
    /**
     * @return rsv100dateKbn
     */
    public int getRsv100dateKbn() {
        return rsv100dateKbn__;
    }
    /**
     * @param rsv100dateKbn 設定する rsv100dateKbn
     */
    public void setRsv100dateKbn(int rsv100dateKbn) {
        rsv100dateKbn__ = rsv100dateKbn;
    }
    /**
     * @return rsv100svDateKbn
     */
    public int getRsv100svDateKbn() {
        return rsv100svDateKbn__;
    }
    /**
     * @param rsv100svDateKbn 設定する rsv100svDateKbn
     */
    public void setRsv100svDateKbn(int rsv100svDateKbn) {
        rsv100svDateKbn__ = rsv100svDateKbn;
    }
    /**
     * @return rsv100apprStatus
     */
    public int getRsv100apprStatus() {
        return rsv100apprStatus__;
    }
    /**
     * @param rsv100apprStatus 設定する rsv100apprStatus
     */
    public void setRsv100apprStatus(int rsv100apprStatus) {
        rsv100apprStatus__ = rsv100apprStatus;
    }
    /**
     * @return rsv100svApprStatus
     */
    public int getRsv100svApprStatus() {
        return rsv100svApprStatus__;
    }
    /**
     * @param rsv100svApprStatus 設定する rsv100svApprStatus
     */
    public void setRsv100svApprStatus(int rsv100svApprStatus) {
        rsv100svApprStatus__ = rsv100svApprStatus;
    }
    /**
     * <p>rsvPrintUseKbn を取得します。
     * @return rsvPrintUseKbn
     */
    public int getRsvPrintUseKbn() {
        return rsvPrintUseKbn__;
    }
    /**
     * <p>rsvPrintUseKbn をセットします。
     * @param rsvPrintUseKbn rsvPrintUseKbn
     */
    public void setRsvPrintUseKbn(int rsvPrintUseKbn) {
        rsvPrintUseKbn__ = rsvPrintUseKbn;
    }
    /**
     * <p>rsv100svSelectSisKbn を取得します。
     * @return rsv100svSelectSisKbn
     */
    public int getRsv100svSelectSisKbn() {
        return rsv100svSelectSisKbn__;
    }
    /**
     * <p>rsv100svSelectSisKbn をセットします。
     * @param rsv100svSelectSisKbn rsv100svSelectSisKbn
     */
    public void setRsv100svSelectSisKbn(int rsv100svSelectSisKbn) {
        rsv100svSelectSisKbn__ = rsv100svSelectSisKbn;
    }
}