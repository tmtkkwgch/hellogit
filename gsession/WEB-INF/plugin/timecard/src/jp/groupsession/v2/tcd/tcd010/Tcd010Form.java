package jp.groupsession.v2.tcd.tcd010;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.AbstractGsForm;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.dao.TcdTcdataDao;
import jp.groupsession.v2.tcd.model.TcdTcdataModel;
import jp.groupsession.v2.tcd.model.TcdTotalValueModel;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] タイムカード 一覧画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd010Form extends AbstractGsForm {

    /** 処理コマンド */
    private String cmd__;

    /** 現在の年 */
    private String year__;

    /** 現在の月 */
    private String month__;

    /** 表示日付 */
    private String tcdDspFrom__ = null;

    /** ユーザーSID */
    private String usrSid__;

    /** ユーザー種別 0:一般 1:グループ管理者 2:管理者 */
    private String usrKbn__;

    /** 日選択multibox */
    private String[] selectDay__;
    /** 変更する日付*/
    private String editDay__;

    /** 一ヶ月分のタイムカード情報*/
    private ArrayList<Tcd010Model> tcd010List__ = null;
    /** 前月の集計値*/
    private TcdTotalValueModel lastMonthMdl__ = null;
    /** 当月の集計値*/
    private TcdTotalValueModel thisMonthMdl__ = null;

    /** 表示用：前月文字列 */
    private String lastMonthString__;
    /** 表示用：今月文字列 */
    private String thisMonthString__;

    //管理者・グループ管理者用
    /** 選択グループSID */
    private String sltGroupSid__;
    /** グループコンボ */
    private List<LabelValueBean> tcd010GpLabelList__ = null;
    /** ユーザコンボ */
    private List<LabelValueBean> tcd010UsrLabelList__ = null;

    /** 不正データ有無フラグ */
    private String tcd010FailFlg__;
    /** ロックフラグ */
    private String tcd010LockFlg__;

    /** 遷移元 メイン個人設定メニュー:2 メイン管理者メニュー:1 その他:0*/
    private int backScreen__ = GSConstMain.BACK_PLUGIN;

    //年間集計値用
    /** 年度*/
    private String nendYear__;
    /** 終了年度*/
    private String endYear__;
    /** 期首月*/
    private String kishuMonth__;
    /** 期末月*/
    private String kimatuMonth__;
    /** 年間の集計値*/
    private TcdTotalValueModel totalYearMdl__ = null;
    //月別集計値
    /** 月別の集計値*/
    private ArrayList<TcdTotalValueModel> monthTtlList = null;

    /** 勤務表出力区分 */
    private String kinmuOut__;

    /** ログインユーザフラグ */
    private boolean myselfFlg__;

    /** 打刻開始ボタン押下フラグ */
    private int dakokuStrSetFlg__ = GSConstTimecard.DAKOKUBTN_PUSH_NOT;
    /** 打刻終了ボタン押下フラグ */
    private int dakokuEndSetFlg__ = GSConstTimecard.DAKOKUBTN_PUSH_NOT;

    /**
     *<br>[機  能] 日ラジオボタンが選択されているかを返す
     *<br>[解  説]
     *<br>[備  考]
     * @return 選択されている:true　されていない:false
     */
    public boolean isDaySelected() {
        return getSelectDay() != null && getSelectDay().length > 0;
    }

    /**
     * <br>[機  能] 更新時の入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return errors エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateChkTcd010(int userSid,
                                        Connection con,
                                        RequestModel reqMdl)
    throws SQLException {

        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage(reqMdl);
        String timecardInfo = gsMsg.getMessage("tcd.98");

        UDate sysDate = new UDate();
        //当日タイムカード存在チェック
        TcdTcdataDao tcdDao = new TcdTcdataDao(con);
        TcdTcdataModel tcdMdl = tcdDao.select(
                userSid, sysDate.getYear(), sysDate.getMonth(), sysDate.getIntDay());
        if (tcdMdl != null) {
            boolean dakokuErrFlg = false;
            if (dakokuStrSetFlg__ == GSConstTimecard.DAKOKUBTN_PUSH
                    && tcdMdl.getTcdIntime() != null) {
                //打刻開始
                //登録済みはエラー
                dakokuErrFlg = true;

            } else if (dakokuEndSetFlg__ == GSConstTimecard.DAKOKUBTN_PUSH
                    && tcdMdl.getTcdOuttime() != null) {
                //打刻終了
                //登録済みはエラー
                dakokuErrFlg = true;
            }

            if (dakokuErrFlg) {
                //登録済みはエラー
                msg = new ActionMessage("error.input.timecard.exist", timecardInfo);
                errors.add("" + "error.input.timecard.exist", msg);
            }
        }

        return errors;
    }

    /**
     * @return the kinmuOut
     */
    public String getKinmuOut() {
        return kinmuOut__;
    }

    /**
     * @param kinmuOut the kinmuOut to set
     */
    public void setKinmuOut(String kinmuOut) {
        kinmuOut__ = kinmuOut;
    }

    /**
     * <p>totalYearMdl を取得します。
     * @return totalYearMdl
     */
    public TcdTotalValueModel getTotalYearMdl() {
        return totalYearMdl__;
    }

    /**
     * <p>totalYearMdl をセットします。
     * @param totalYearMdl totalYearMdl
     */
    public void setTotalYearMdl(TcdTotalValueModel totalYearMdl) {
        totalYearMdl__ = totalYearMdl;
    }

    /**
     * <p>lastMonthString を取得します。
     * @return lastMonthString
     */
    public String getLastMonthString() {
        return lastMonthString__;
    }

    /**
     * <p>lastMonthString をセットします。
     * @param lastMonthString lastMonthString
     */
    public void setLastMonthString(String lastMonthString) {
        lastMonthString__ = lastMonthString;
    }

    /**
     * <p>thisMonthString を取得します。
     * @return thisMonthString
     */
    public String getThisMonthString() {
        return thisMonthString__;
    }

    /**
     * <p>thisMonthString をセットします。
     * @param thisMonthString thisMonthString
     */
    public void setThisMonthString(String thisMonthString) {
        thisMonthString__ = thisMonthString;
    }

    /**
     * <p>tcd010LockFlg を取得します。
     * @return tcd010LockFlg
     */
    public String getTcd010LockFlg() {
        return tcd010LockFlg__;
    }

    /**
     * <p>tcd010LockFlg をセットします。
     * @param tcd010LockFlg tcd010LockFlg
     */
    public void setTcd010LockFlg(String tcd010LockFlg) {
        tcd010LockFlg__ = tcd010LockFlg;
    }

    /**
     * <p>tcd010FailFlg を取得します。
     * @return tcd010FailFlg
     */
    public String getTcd010FailFlg() {
        return tcd010FailFlg__;
    }

    /**
     * <p>tcd010FailFlg をセットします。
     * @param tcd010FailFlg tcd010FailFlg
     */
    public void setTcd010FailFlg(String tcd010FailFlg) {
        tcd010FailFlg__ = tcd010FailFlg;
    }

    /**
     * <p>editDay を取得します。
     * @return editDay
     */
    public String getEditDay() {
        return editDay__;
    }

    /**
     * <p>editDay をセットします。
     * @param editDay editDay
     */
    public void setEditDay(String editDay) {
        editDay__ = editDay;
    }

    /**
     * <p>usrKbn を取得します。
     * @return usrKbn
     */
    public String getUsrKbn() {
        return usrKbn__;
    }

    /**
     * <p>usrKbn をセットします。
     * @param usrKbn usrKbn
     */
    public void setUsrKbn(String usrKbn) {
        usrKbn__ = usrKbn;
    }

    /**
     * <p>sltGroupSid を取得します。
     * @return sltGroupSid
     */
    public String getSltGroupSid() {
        return sltGroupSid__;
    }

    /**
     * <p>sltGroupSid をセットします。
     * @param sltGroupSid sltGroupSid
     */
    public void setSltGroupSid(String sltGroupSid) {
        sltGroupSid__ = sltGroupSid;
    }

    /**
     * <p>tcd010GpLabelList を取得します。
     * @return tcd010GpLabelList
     */
    public List<LabelValueBean> getTcd010GpLabelList() {
        return tcd010GpLabelList__;
    }

    /**
     * <p>tcd010GpLabelList をセットします。
     * @param tcd010GpLabelList tcd010GpLabelList
     */
    public void setTcd010GpLabelList(List<LabelValueBean> tcd010GpLabelList) {
        tcd010GpLabelList__ = tcd010GpLabelList;
    }

    /**
     * <p>tcd010UsrLabelList を取得します。
     * @return tcd010UsrLabelList
     */
    public List<LabelValueBean> getTcd010UsrLabelList() {
        return tcd010UsrLabelList__;
    }

    /**
     * <p>tcd010UsrLabelList をセットします。
     * @param tcd010UsrLabelList tcd010UsrLabelList
     */
    public void setTcd010UsrLabelList(List<LabelValueBean> tcd010UsrLabelList) {
        tcd010UsrLabelList__ = tcd010UsrLabelList;
    }

    /**
     * <p>lastMonthMdl を取得します。
     * @return lastMonthMdl
     */
    public TcdTotalValueModel getLastMonthMdl() {
        return lastMonthMdl__;
    }

    /**
     * <p>lastMonthMdl をセットします。
     * @param lastMonthMdl lastMonthMdl
     */
    public void setLastMonthMdl(TcdTotalValueModel lastMonthMdl) {
        lastMonthMdl__ = lastMonthMdl;
    }

    /**
     * <p>thisMonthMdl を取得します。
     * @return thisMonthMdl
     */
    public TcdTotalValueModel getThisMonthMdl() {
        return thisMonthMdl__;
    }

    /**
     * <p>thisMonthMdl をセットします。
     * @param thisMonthMdl thisMonthMdl
     */
    public void setThisMonthMdl(TcdTotalValueModel thisMonthMdl) {
        thisMonthMdl__ = thisMonthMdl;
    }

    /**
     * <p>tcd010List を取得します。
     * @return tcd010List
     */
    public ArrayList<Tcd010Model> getTcd010List() {
        return tcd010List__;
    }

    /**
     * <p>tcd010List をセットします。
     * @param tcd010List tcd010List
     */
    public void setTcd010List(ArrayList<Tcd010Model> tcd010List) {
        tcd010List__ = tcd010List;
    }

    /**
     * <p>cmd を取得します。
     * @return cmd
     */
    public String getCmd() {
        return cmd__;
    }

    /**
     * <p>cmd をセットします。
     * @param cmd cmd
     */
    public void setCmd(String cmd) {
        cmd__ = cmd;
    }

    /**
     * <p>month を取得します。
     * @return month
     */
    public String getMonth() {
        return month__;
    }

    /**
     * <p>month をセットします。
     * @param month month
     */
    public void setMonth(String month) {
        month__ = month;
    }

    /**
     * <p>selectDay を取得します。
     * @return selectDay
     */
    public String[] getSelectDay() {
        return selectDay__;
    }

    /**
     * <p>selectDay をセットします。
     * @param selectDay selectDay
     */
    public void setSelectDay(String[] selectDay) {
        selectDay__ = selectDay;
    }

    /**
     * <p>usrSid を取得します。
     * @return usrSid
     */
    public String getUsrSid() {
        return usrSid__;
    }

    /**
     * <p>usrSid をセットします。
     * @param usrSid usrSid
     */
    public void setUsrSid(String usrSid) {
        usrSid__ = usrSid;
    }

    /**
     * <p>year を取得します。
     * @return year
     */
    public String getYear() {
        return year__;
    }

    /**
     * <p>year をセットします。
     * @param year year
     */
    public void setYear(String year) {
        year__ = year;
    }

    /**
     * <p>backScreen を取得します。
     * @return backScreen
     */
    public int getBackScreen() {
        return backScreen__;
    }

    /**
     * <p>backScreen をセットします。
     * @param backScreen backScreen
     */
    public void setBackScreen(int backScreen) {
        backScreen__ = backScreen;
    }

    /**
     * <p>kishuMonth を取得します。
     * @return kishuMonth
     */
    public String getKishuMonth() {
        return kishuMonth__;
    }

    /**
     * <p>kishuMonth をセットします。
     * @param kishuMonth kishuMonth
     */
    public void setKishuMonth(String kishuMonth) {
        kishuMonth__ = kishuMonth;
    }

    /**
     * <p>kimatuMonth を取得します。
     * @return kimatuMonth
     */
    public String getKimatuMonth() {
        return kimatuMonth__;
    }

    /**
     * <p>kimatuMonth をセットします。
     * @param kimatuMonth kimatuMonth
     */
    public void setKimatuMonth(String kimatuMonth) {
        kimatuMonth__ = kimatuMonth;
    }

    /**
     * <p>nendYear を取得します。
     * @return nendYear
     */
    public String getNendYear() {
        return nendYear__;
    }

    /**
     * <p>nendYear をセットします。
     * @param nendYear nendYear
     */
    public void setNendYear(String nendYear) {
        nendYear__ = nendYear;
    }

    /**
     * <p>monthTtlList を取得します。
     * @return monthTtlList
     */
    public ArrayList<TcdTotalValueModel> getMonthTtlList() {
        return monthTtlList;
    }

    /**
     * <p>monthTtlList をセットします。
     * @param monthList monthList
     */
    public void setMonthTtlList(ArrayList<TcdTotalValueModel> monthList) {
        this.monthTtlList = monthList;
    }

    /**
     * <p>endYear を取得します。
     * @return endYear
     */
    public String getEndYear() {
        return endYear__;
    }

    /**
     * <p>endYear をセットします。
     * @param endYear endYear
     */
    public void setEndYear(String endYear) {
        endYear__ = endYear;
    }

    /**
     * <p>tcdDspFrom を取得します。
     * @return tcdDspFrom
     */
    public String getTcdDspFrom() {
        return tcdDspFrom__;
    }

    /**
     * <p>tcdDspFrom をセットします。
     * @param tcdDspFrom tcdDspFrom
     */
    public void setTcdDspFrom(String tcdDspFrom) {
        tcdDspFrom__ = tcdDspFrom;
    }

    /**
     * <p>myselfFlg を取得します。
     * @return myselfFlg
     */
    public boolean isMyselfFlg() {
        return myselfFlg__;
    }

    /**
     * <p>myselfFlg をセットします。
     * @param myselfFlg myselfFlg
     */
    public void setMyselfFlg(boolean myselfFlg) {
        myselfFlg__ = myselfFlg;
    }

    /**
     * <p>dakokuEndSetFlg を取得します。
     * @return dakokuEndSetFlg
     */
    public int getDakokuEndSetFlg() {
        return dakokuEndSetFlg__;
    }

    /**
     * <p>dakokuEndSetFlg をセットします。
     * @param dakokuEndSetFlg dakokuEndSetFlg
     */
    public void setDakokuEndSetFlg(int dakokuEndSetFlg) {
        dakokuEndSetFlg__ = dakokuEndSetFlg;
    }

    /**
     * <p>dakokuStrSetFlg を取得します。
     * @return dakokuStrSetFlg
     */
    public int getDakokuStrSetFlg() {
        return dakokuStrSetFlg__;
    }

    /**
     * <p>dakokuStrSetFlg をセットします。
     * @param dakokuStrSetFlg dakokuStrSetFlg
     */
    public void setDakokuStrSetFlg(int dakokuStrSetFlg) {
        dakokuStrSetFlg__ = dakokuStrSetFlg;
    }
}
