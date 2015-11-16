package jp.groupsession.v2.tcd.tcd040;

import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.model.TcdManagerModel;
import jp.groupsession.v2.tcd.model.TcdTotalValueModel;
import jp.groupsession.v2.tcd.tcd030.Tcd030Form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] タイムカード勤怠集計画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd040Form extends Tcd030Form {

    /** 検索実行フラグ */
    private String tcd040SearchFlg__;
    /** 年 */
    private String tcd040SltYear__;
    /** 月 */
    private String tcd040SltMonth__;
    /** 年(To) */
    private String tcd040SltYearTo__;
    /** 月(To) */
    private String tcd040SltMonthTo__;
    /** グループ */
    private String tcd040SltGroup__;
    /** 残業有り */
    private String tcd040ZangyoCk__;
    /** 深夜残業有り */
    private String tcd040SinyaCk__;
    /** 休日出勤有り */
    private String tcd040KyujituCk__;
    /** 遅刻有り */
    private String tcd040ChikokuCk__;
    /** 早退有り */
    private String tcd040SoutaiCk__;
    /** 欠勤有り */
    private String tcd040KekkinCk__;
    /** 慶弔休暇有り */
    private String tcd040KeityoCk__;
    /** 有給休暇有り */
    private String tcd040YuukyuCk__;
    /** 代休有り */
    private String tcd040DaikyuCk__;
    /** その他休暇有り */
    private String tcd040SonotaCk__;
    //検索条件Save用
    /** save年 */
    private String tcd040SltYearSv__;
    /** save月 */
    private String tcd040SltMonthSv__;
    /** save年(To) */
    private String tcd040SltYearToSv__;
    /** save月(To) */
    private String tcd040SltMonthToSv__;
    /** saveグループ */
    private String tcd040SltGroupSv__;
    /** save残業有り */
    private String tcd040ZangyoCkSv__;
    /** save深夜残業有り */
    private String tcd040SinyaCkSv__;
    /** save休日出勤有り */
    private String tcd040KyujituCkSv__;
    /** save遅刻有り */
    private String tcd040ChikokuCkSv__;
    /** save早退有り */
    private String tcd040SoutaiCkSv__;
    /** save欠勤有り */
    private String tcd040KekkinCkSv__;
    /** save慶弔休暇有り */
    private String tcd040KeityoCkSv__;
    /** save有給休暇有り */
    private String tcd040YuukyuCkSv__;
    /** save代休有り */
    private String tcd040DaikyuCkSv__;
    /** saveその他休暇有り */
    private String tcd040SonotaCkSv__;

    /** ソート項目 */
    private String tcd040SortKey__ = "1";
    /** オーダー項目 */
    private String tcd040OrderKey__ = "0";
    //画面項目
    /** 年コンボ */
    private List<LabelValueBean> tcd040YearLabelList__ = null;
    /** 月コンボ */
    private List<LabelValueBean> tcd040MonthLabelList__ = null;
    /** グループコンボ */
    private List<LabelValueBean> tcd040GpLabelList__ = null;
    /** 出力条件リスト */
    private ArrayList<String> tcd040SearchList__ = null;
    //検索結果
    /** 検索結果*/
    private ArrayList<TcdManagerModel> tcd040ResultList__ = null;

    /** 平均に0を含めるか */
    private int tcd040avgInZero__ = 0;
    /** 平均 */
    private TcdTotalValueModel averageData__ = null;
    /** 合計 */
    private TcdTotalValueModel totalData__ = null;

    /**
     * <p>tcd040SearchFlg を取得します。
     * @return tcd040SearchFlg
     */
    public String getTcd040SearchFlg() {
        return tcd040SearchFlg__;
    }
    /**
     * <p>tcd040SearchFlg をセットします。
     * @param tcd040SearchFlg tcd040SearchFlg
     */
    public void setTcd040SearchFlg(String tcd040SearchFlg) {
        tcd040SearchFlg__ = tcd040SearchFlg;
    }
    /**
     * <p>tcd040GpLabelList を取得します。
     * @return tcd040GpLabelList
     */
    public List<LabelValueBean> getTcd040GpLabelList() {
        return tcd040GpLabelList__;
    }
    /**
     * <p>tcd040GpLabelList をセットします。
     * @param tcd040GpLabelList tcd040GpLabelList
     */
    public void setTcd040GpLabelList(List<LabelValueBean> tcd040GpLabelList) {
        tcd040GpLabelList__ = tcd040GpLabelList;
    }
    /**
     * <p>tcd040MonthLabelList を取得します。
     * @return tcd040MonthLabelList
     */
    public List<LabelValueBean> getTcd040MonthLabelList() {
        return tcd040MonthLabelList__;
    }
    /**
     * <p>tcd040MonthLabelList をセットします。
     * @param tcd040MonthLabelList tcd040MonthLabelList
     */
    public void setTcd040MonthLabelList(List<LabelValueBean> tcd040MonthLabelList) {
        tcd040MonthLabelList__ = tcd040MonthLabelList;
    }
    /**
     * <p>tcd040ResultList を取得します。
     * @return tcd040ResultList
     */
    public ArrayList<TcdManagerModel> getTcd040ResultList() {
        return tcd040ResultList__;
    }
    /**
     * <p>tcd040ResultList をセットします。
     * @param tcd040ResultList tcd040ResultList
     */
    public void setTcd040ResultList(ArrayList<TcdManagerModel> tcd040ResultList) {
        tcd040ResultList__ = tcd040ResultList;
    }
    /**
     * <p>tcd040SearchList を取得します。
     * @return tcd040SearchList
     */
    public ArrayList<String> getTcd040SearchList() {
        return tcd040SearchList__;
    }
    /**
     * <p>tcd040SearchList をセットします。
     * @param tcd040SearchList tcd040SearchList
     */
    public void setTcd040SearchList(ArrayList<String> tcd040SearchList) {
        tcd040SearchList__ = tcd040SearchList;
    }
    /**
     * <p>tcd040YearLabelList を取得します。
     * @return tcd040YearLabelList
     */
    public List<LabelValueBean> getTcd040YearLabelList() {
        return tcd040YearLabelList__;
    }
    /**
     * <p>tcd040YearLabelList をセットします。
     * @param tcd040YearLabelList tcd040YearLabelList
     */
    public void setTcd040YearLabelList(List<LabelValueBean> tcd040YearLabelList) {
        tcd040YearLabelList__ = tcd040YearLabelList;
    }
    /**
     * <p>tcd040ChikokuCk を取得します。
     * @return tcd040ChikokuCk
     */
    public String getTcd040ChikokuCk() {
        return tcd040ChikokuCk__;
    }
    /**
     * <p>tcd040ChikokuCk をセットします。
     * @param tcd040ChikokuCk tcd040ChikokuCk
     */
    public void setTcd040ChikokuCk(String tcd040ChikokuCk) {
        tcd040ChikokuCk__ = tcd040ChikokuCk;
    }
    /**
     * <p>tcd040ChikokuCkSv を取得します。
     * @return tcd040ChikokuCkSv
     */
    public String getTcd040ChikokuCkSv() {
        return tcd040ChikokuCkSv__;
    }
    /**
     * <p>tcd040ChikokuCkSv をセットします。
     * @param tcd040ChikokuCkSv tcd040ChikokuCkSv
     */
    public void setTcd040ChikokuCkSv(String tcd040ChikokuCkSv) {
        tcd040ChikokuCkSv__ = tcd040ChikokuCkSv;
    }
    /**
     * <p>tcd040DaikyuCk を取得します。
     * @return tcd040DaikyuCk
     */
    public String getTcd040DaikyuCk() {
        return tcd040DaikyuCk__;
    }
    /**
     * <p>tcd040DaikyuCk をセットします。
     * @param tcd040DaikyuCk tcd040DaikyuCk
     */
    public void setTcd040DaikyuCk(String tcd040DaikyuCk) {
        tcd040DaikyuCk__ = tcd040DaikyuCk;
    }
    /**
     * <p>tcd040DaikyuCkSv を取得します。
     * @return tcd040DaikyuCkSv
     */
    public String getTcd040DaikyuCkSv() {
        return tcd040DaikyuCkSv__;
    }
    /**
     * <p>tcd040DaikyuCkSv をセットします。
     * @param tcd040DaikyuCkSv tcd040DaikyuCkSv
     */
    public void setTcd040DaikyuCkSv(String tcd040DaikyuCkSv) {
        tcd040DaikyuCkSv__ = tcd040DaikyuCkSv;
    }
    /**
     * <p>tcd040KeityoCk を取得します。
     * @return tcd040KeityoCk
     */
    public String getTcd040KeityoCk() {
        return tcd040KeityoCk__;
    }
    /**
     * <p>tcd040KeityoCk をセットします。
     * @param tcd040KeityoCk tcd040KeityoCk
     */
    public void setTcd040KeityoCk(String tcd040KeityoCk) {
        tcd040KeityoCk__ = tcd040KeityoCk;
    }
    /**
     * <p>tcd040KeityoCkSv を取得します。
     * @return tcd040KeityoCkSv
     */
    public String getTcd040KeityoCkSv() {
        return tcd040KeityoCkSv__;
    }
    /**
     * <p>tcd040KeityoCkSv をセットします。
     * @param tcd040KeityoCkSv tcd040KeityoCkSv
     */
    public void setTcd040KeityoCkSv(String tcd040KeityoCkSv) {
        tcd040KeityoCkSv__ = tcd040KeityoCkSv;
    }
    /**
     * <p>tcd040KekkinCk を取得します。
     * @return tcd040KekkinCk
     */
    public String getTcd040KekkinCk() {
        return tcd040KekkinCk__;
    }
    /**
     * <p>tcd040KekkinCk をセットします。
     * @param tcd040KekkinCk tcd040KekkinCk
     */
    public void setTcd040KekkinCk(String tcd040KekkinCk) {
        tcd040KekkinCk__ = tcd040KekkinCk;
    }
    /**
     * <p>tcd040KekkinCkSv を取得します。
     * @return tcd040KekkinCkSv
     */
    public String getTcd040KekkinCkSv() {
        return tcd040KekkinCkSv__;
    }
    /**
     * <p>tcd040KekkinCkSv をセットします。
     * @param tcd040KekkinCkSv tcd040KekkinCkSv
     */
    public void setTcd040KekkinCkSv(String tcd040KekkinCkSv) {
        tcd040KekkinCkSv__ = tcd040KekkinCkSv;
    }
    /**
     * <p>tcd040KyujituCk を取得します。
     * @return tcd040KyujituCk
     */
    public String getTcd040KyujituCk() {
        return tcd040KyujituCk__;
    }
    /**
     * <p>tcd040KyujituCk をセットします。
     * @param tcd040KyujituCk tcd040KyujituCk
     */
    public void setTcd040KyujituCk(String tcd040KyujituCk) {
        tcd040KyujituCk__ = tcd040KyujituCk;
    }
    /**
     * <p>tcd040KyujituCkSv を取得します。
     * @return tcd040KyujituCkSv
     */
    public String getTcd040KyujituCkSv() {
        return tcd040KyujituCkSv__;
    }
    /**
     * <p>tcd040KyujituCkSv をセットします。
     * @param tcd040KyujituCkSv tcd040KyujituCkSv
     */
    public void setTcd040KyujituCkSv(String tcd040KyujituCkSv) {
        tcd040KyujituCkSv__ = tcd040KyujituCkSv;
    }
    /**
     * <p>tcd040OrderKey を取得します。
     * @return tcd040OrderKey
     */
    public String getTcd040OrderKey() {
        return tcd040OrderKey__;
    }
    /**
     * <p>tcd040OrderKey をセットします。
     * @param tcd040OrderKey tcd040OrderKey
     */
    public void setTcd040OrderKey(String tcd040OrderKey) {
        tcd040OrderKey__ = tcd040OrderKey;
    }
    /**
     * <p>tcd040SinyaCk を取得します。
     * @return tcd040SinyaCk
     */
    public String getTcd040SinyaCk() {
        return tcd040SinyaCk__;
    }
    /**
     * <p>tcd040SinyaCk をセットします。
     * @param tcd040SinyaCk tcd040SinyaCk
     */
    public void setTcd040SinyaCk(String tcd040SinyaCk) {
        tcd040SinyaCk__ = tcd040SinyaCk;
    }
    /**
     * <p>tcd040SinyaCkSv を取得します。
     * @return tcd040SinyaCkSv
     */
    public String getTcd040SinyaCkSv() {
        return tcd040SinyaCkSv__;
    }
    /**
     * <p>tcd040SinyaCkSv をセットします。
     * @param tcd040SinyaCkSv tcd040SinyaCkSv
     */
    public void setTcd040SinyaCkSv(String tcd040SinyaCkSv) {
        tcd040SinyaCkSv__ = tcd040SinyaCkSv;
    }
    /**
     * <p>tcd040SltGroup を取得します。
     * @return tcd040SltGroup
     */
    public String getTcd040SltGroup() {
        return tcd040SltGroup__;
    }
    /**
     * <p>tcd040SltGroup をセットします。
     * @param tcd040SltGroup tcd040SltGroup
     */
    public void setTcd040SltGroup(String tcd040SltGroup) {
        tcd040SltGroup__ = tcd040SltGroup;
    }
    /**
     * <p>tcd040SltGroupSv を取得します。
     * @return tcd040SltGroupSv
     */
    public String getTcd040SltGroupSv() {
        return tcd040SltGroupSv__;
    }
    /**
     * <p>tcd040SltGroupSv をセットします。
     * @param tcd040SltGroupSv tcd040SltGroupSv
     */
    public void setTcd040SltGroupSv(String tcd040SltGroupSv) {
        tcd040SltGroupSv__ = tcd040SltGroupSv;
    }
    /**
     * <p>tcd040SltMonth を取得します。
     * @return tcd040SltMonth
     */
    public String getTcd040SltMonth() {
        return tcd040SltMonth__;
    }
    /**
     * <p>tcd040SltMonth をセットします。
     * @param tcd040SltMonth tcd040SltMonth
     */
    public void setTcd040SltMonth(String tcd040SltMonth) {
        tcd040SltMonth__ = tcd040SltMonth;
    }
    /**
     * <p>tcd040SltMonthSv を取得します。
     * @return tcd040SltMonthSv
     */
    public String getTcd040SltMonthSv() {
        return tcd040SltMonthSv__;
    }
    /**
     * <p>tcd040SltMonthSv をセットします。
     * @param tcd040SltMonthSv tcd040SltMonthSv
     */
    public void setTcd040SltMonthSv(String tcd040SltMonthSv) {
        tcd040SltMonthSv__ = tcd040SltMonthSv;
    }
    /**
     * <p>tcd040SltYear を取得します。
     * @return tcd040SltYear
     */
    public String getTcd040SltYear() {
        return tcd040SltYear__;
    }
    /**
     * <p>tcd040SltYear をセットします。
     * @param tcd040SltYear tcd040SltYear
     */
    public void setTcd040SltYear(String tcd040SltYear) {
        tcd040SltYear__ = tcd040SltYear;
    }
    /**
     * <p>tcd040SltYearSv を取得します。
     * @return tcd040SltYearSv
     */
    public String getTcd040SltYearSv() {
        return tcd040SltYearSv__;
    }
    /**
     * <p>tcd040SltYearSv をセットします。
     * @param tcd040SltYearSv tcd040SltYearSv
     */
    public void setTcd040SltYearSv(String tcd040SltYearSv) {
        tcd040SltYearSv__ = tcd040SltYearSv;
    }
    /**
     * <p>tcd040SonotaCk を取得します。
     * @return tcd040SonotaCk
     */
    public String getTcd040SonotaCk() {
        return tcd040SonotaCk__;
    }
    /**
     * <p>tcd040SonotaCk をセットします。
     * @param tcd040SonotaCk tcd040SonotaCk
     */
    public void setTcd040SonotaCk(String tcd040SonotaCk) {
        tcd040SonotaCk__ = tcd040SonotaCk;
    }
    /**
     * <p>tcd040SonotaCkSv を取得します。
     * @return tcd040SonotaCkSv
     */
    public String getTcd040SonotaCkSv() {
        return tcd040SonotaCkSv__;
    }
    /**
     * <p>tcd040SonotaCkSv をセットします。
     * @param tcd040SonotaCkSv tcd040SonotaCkSv
     */
    public void setTcd040SonotaCkSv(String tcd040SonotaCkSv) {
        tcd040SonotaCkSv__ = tcd040SonotaCkSv;
    }
    /**
     * <p>tcd040SortKey を取得します。
     * @return tcd040SortKey
     */
    public String getTcd040SortKey() {
        return tcd040SortKey__;
    }
    /**
     * <p>tcd040SortKey をセットします。
     * @param tcd040SortKey tcd040SortKey
     */
    public void setTcd040SortKey(String tcd040SortKey) {
        tcd040SortKey__ = tcd040SortKey;
    }
    /**
     * <p>tcd040SoutaiCk を取得します。
     * @return tcd040SoutaiCk
     */
    public String getTcd040SoutaiCk() {
        return tcd040SoutaiCk__;
    }
    /**
     * <p>tcd040SoutaiCk をセットします。
     * @param tcd040SoutaiCk tcd040SoutaiCk
     */
    public void setTcd040SoutaiCk(String tcd040SoutaiCk) {
        tcd040SoutaiCk__ = tcd040SoutaiCk;
    }
    /**
     * <p>tcd040SoutaiCkSv を取得します。
     * @return tcd040SoutaiCkSv
     */
    public String getTcd040SoutaiCkSv() {
        return tcd040SoutaiCkSv__;
    }
    /**
     * <p>tcd040SoutaiCkSv をセットします。
     * @param tcd040SoutaiCkSv tcd040SoutaiCkSv
     */
    public void setTcd040SoutaiCkSv(String tcd040SoutaiCkSv) {
        tcd040SoutaiCkSv__ = tcd040SoutaiCkSv;
    }
    /**
     * <p>tcd040YuukyuCk を取得します。
     * @return tcd040YuukyuCk
     */
    public String getTcd040YuukyuCk() {
        return tcd040YuukyuCk__;
    }
    /**
     * <p>tcd040YuukyuCk をセットします。
     * @param tcd040YuukyuCk tcd040YuukyuCk
     */
    public void setTcd040YuukyuCk(String tcd040YuukyuCk) {
        tcd040YuukyuCk__ = tcd040YuukyuCk;
    }
    /**
     * <p>tcd040YuukyuCkSv を取得します。
     * @return tcd040YuukyuCkSv
     */
    public String getTcd040YuukyuCkSv() {
        return tcd040YuukyuCkSv__;
    }
    /**
     * <p>tcd040YuukyuCkSv をセットします。
     * @param tcd040YuukyuCkSv tcd040YuukyuCkSv
     */
    public void setTcd040YuukyuCkSv(String tcd040YuukyuCkSv) {
        tcd040YuukyuCkSv__ = tcd040YuukyuCkSv;
    }
    /**
     * <p>tcd040ZangyoCk を取得します。
     * @return tcd040ZangyoCk
     */
    public String getTcd040ZangyoCk() {
        return tcd040ZangyoCk__;
    }
    /**
     * <p>tcd040ZangyoCk をセットします。
     * @param tcd040ZangyoCk tcd040ZangyoCk
     */
    public void setTcd040ZangyoCk(String tcd040ZangyoCk) {
        tcd040ZangyoCk__ = tcd040ZangyoCk;
    }
    /**
     * <p>tcd040ZangyoCkSv を取得します。
     * @return tcd040ZangyoCkSv
     */
    public String getTcd040ZangyoCkSv() {
        return tcd040ZangyoCkSv__;
    }
    /**
     * <p>tcd040ZangyoCkSv をセットします。
     * @param tcd040ZangyoCkSv tcd040ZangyoCkSv
     */
    public void setTcd040ZangyoCkSv(String tcd040ZangyoCkSv) {
        tcd040ZangyoCkSv__ = tcd040ZangyoCkSv;
    }
    /**
     * <p>tcd040SltMonthTo を取得します。
     * @return tcd040SltMonthTo
     */
    public String getTcd040SltMonthTo() {
        return tcd040SltMonthTo__;
    }
    /**
     * <p>tcd040SltMonthTo をセットします。
     * @param tcd040SltMonthTo tcd040SltMonthTo
     */
    public void setTcd040SltMonthTo(String tcd040SltMonthTo) {
        tcd040SltMonthTo__ = tcd040SltMonthTo;
    }
    /**
     * <p>tcd040SltMonthToSv を取得します。
     * @return tcd040SltMonthToSv
     */
    public String getTcd040SltMonthToSv() {
        return tcd040SltMonthToSv__;
    }
    /**
     * <p>tcd040SltMonthToSv をセットします。
     * @param tcd040SltMonthToSv tcd040SltMonthToSv
     */
    public void setTcd040SltMonthToSv(String tcd040SltMonthToSv) {
        tcd040SltMonthToSv__ = tcd040SltMonthToSv;
    }
    /**
     * <p>tcd040SltYearTo を取得します。
     * @return tcd040SltYearTo
     */
    public String getTcd040SltYearTo() {
        return tcd040SltYearTo__;
    }
    /**
     * <p>tcd040SltYearTo をセットします。
     * @param tcd040SltYearTo tcd040SltYearTo
     */
    public void setTcd040SltYearTo(String tcd040SltYearTo) {
        tcd040SltYearTo__ = tcd040SltYearTo;
    }
    /**
     * <p>tcd040SltYearToSv を取得します。
     * @return tcd040SltYearToSv
     */
    public String getTcd040SltYearToSv() {
        return tcd040SltYearToSv__;
    }
    /**
     * <p>tcd040SltYearToSv をセットします。
     * @param tcd040SltYearToSv tcd040SltYearToSv
     */
    public void setTcd040SltYearToSv(String tcd040SltYearToSv) {
        tcd040SltYearToSv__ = tcd040SltYearToSv;
    }
    /**
     * <p>averageData を取得します。
     * @return averageData
     */
    public TcdTotalValueModel getAverageData() {
        return averageData__;
    }
    /**
     * <p>averageData をセットします。
     * @param averageData averageData
     */
    public void setAverageData(TcdTotalValueModel averageData) {
        averageData__ = averageData;
    }
    /**
     * <p>tcd040avgInZero を取得します。
     * @return tcd040avgInZero
     */
    public int getTcd040avgInZero() {
        return tcd040avgInZero__;
    }
    /**
     * <p>tcd040avgInZero をセットします。
     * @param tcd040avgInZero tcd040avgInZero
     */
    public void setTcd040avgInZero(int tcd040avgInZero) {
        tcd040avgInZero__ = tcd040avgInZero;
    }
    /**
     * <p>totalData を取得します。
     * @return totalData
     */
    public TcdTotalValueModel getTotalData() {
        return totalData__;
    }
    /**
     * <p>totalData をセットします。
     * @param totalData totalData
     */
    public void setTotalData(TcdTotalValueModel totalData) {
        totalData__ = totalData;
    }

    /**
     * <br>[機  能] 検索時の入力チェックを行います。
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return ActionErrors
     */
    public ActionErrors validateCheck(RequestModel reqMdl) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        //年、月が年(To)、月(To)より前の日付の場合はエラー
        int fromYear = Integer.parseInt(tcd040SltYear__);
        int toYear = Integer.parseInt(tcd040SltYearTo__);
        int fromMonth = Integer.parseInt(tcd040SltMonth__);
        int toMonth = Integer.parseInt(tcd040SltMonthTo__);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String kikanFrom = gsMsg.getMessage("tcd.162");
        String kikanTo = gsMsg.getMessage("tcd.163");

        if (fromYear > toYear || (fromYear == toYear && fromMonth > toMonth)) {
            msg = new ActionMessage("error.input.range.date", kikanFrom, kikanTo);
            errors.add("" + "error.input.range.date", msg);

        }

        return errors;
    }
}
