package jp.groupsession.v2.man.man290;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.man320.Man320Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン インフォメーション登録画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man290Form extends Man320Form {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man290Form.class);

    /** 年リスト */
    private ArrayList <LabelValueBean> man290YearLabel__ = null;
    /** 月リスト */
    private ArrayList <LabelValueBean> man290MonthLabel__ = null;
    /** 日リスト */
    private ArrayList <LabelValueBean> man290DayLabel__ = null;
    /** 時リスト */
    private ArrayList <LabelValueBean> man290HourLabel__ = null;
    /** 分リスト */
    private ArrayList <LabelValueBean> man290MinuteLabel__ = null;
    /** 週リスト */
    private ArrayList <LabelValueBean> man290WeekLabel__ = null;
    /** 日リスト(拡張部分) */
    private ArrayList <LabelValueBean> man290ExDayLabel__ = null;

    //入力項目
    /** 拡張区分 */
    private String man290ExtKbn__ = null;
    /** 表示判定 */
    private int man290elementKbn__ = 0;
    /** 曜日multibox */
    private String[] man290Dweek__ = null;
    /** 週 */
    private String man290Week__ = null;
    /** 日 */
    private String man290Day__ = null;

    /** 開始年 */
    private String man290FrYear__ = null;
    /** 開始月 */
    private String man290FrMonth__ = null;
    /** 開始日 */
    private String man290FrDay__ = null;
    /** 開始時 */
    private String man290FrHour__ = null;
    /** 開始分 */
    private String man290FrMin__ = null;

    /** 終了年 */
    private String man290ToYear__ = null;
    /** 終了月 */
    private String man290ToMonth__ = null;
    /** 終了日 */
    private String man290ToDay__ = null;
    /** 終了時 */
    private String man290ToHour__ = null;
    /** 終了分 */
    private String man290ToMin__ = null;

    /** メッセージ */
    private String man290Msg__ = null;
    /** 内容 */
    private String man290Value__ = null;
    /** 状態区分 */
    private String man290Jtkbn__ = null;

    /** 公開対象者SID */
    private String[] man290memberSid__ = new String[0];
    /** 追加済みメンバー(選択) */
    private String[] man290SelectLeftUser__ = new String[0];
    /** 追加用メンバー(選択) */
    private String[] man290SelectRightUser__ = new String[0];
    /** グループSID */
    private int man290groupSid__ = Integer.parseInt(GSConstMain.GROUP_COMBO_VALUE);
    /** グループ一覧 */
    private List <LabelValueBean> man290GroupList__ = null;
    /** 追加済みユーザ一覧 */
    private List <LabelValueBean> man290LeftUserList__ = null;
    /** 追加用ユーザ一覧 */
    private List <LabelValueBean> man290RightUserList__ = null;

    /** 添付ファイル */
    private String[] man290files__ = null;
    /** ファイルコンボ */
    private List <LabelValueBean> man290FileLabelList__ = null;

    /** WEB検索プラグイン使用可否 0=使用 1=未使用 */
    private int webSearchUse__ = GSConst.PLUGIN_USE;

    /** 休日表示区分 0=そのまま表示 1=表示しない 2=前営業日 3=翌営業日 */
    private int man290HolKbn__ = 0;

    /** ヘルプモード */
    private String man290helpMode__ = null;

    /**
     * <br>[機  能] 入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateCheck(RequestModel reqMdl, Connection con)
    throws SQLException {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage(reqMdl);

        //拡張区分
        if (getMan290ExtKbn().equals(String.valueOf(GSConstMain.INFO_KBN_WEEK))) {
            if (getMan290Dweek() == null) {
                msg = new ActionMessage("error.select.required.text",
                        gsMsg.getMessage("cmn.dayofweek"));
                errors.add("error.select.required.text", msg);
            }
        } else if (getMan290ExtKbn().equals(String.valueOf(GSConstMain.INFO_KBN_MONTH))) {
            if (getMan290Week().equals("0")
             && getMan290Day().equals("0")
             && getMan290Dweek() == null
             ) {
                msg = new ActionMessage("error.select.required.text",
                        gsMsg.getMessage("main.src.man290.11"));
                errors.add("error.select.required.text", msg);
            }
            //週・日付の同時指定はエラー
            if (!getMan290Week().equals("0") && !getMan290Day().equals("0")) {
                msg = new ActionMessage("error.select.required.text",
                        gsMsg.getMessage("main.src.man290.12"));
                errors.add("error.select.required.text", msg);

            } else if (!getMan290Week().equals("0") && getMan290Dweek() == null) {
                msg = new ActionMessage("error.select.required.text",
                        gsMsg.getMessage("cmn.dayofweek"));
                errors.add("error.select.required.text", msg);
            }
        }

        //開始年月日チェックフラグ(true=入力OK、false=NG)
        boolean fromOk = false;

        int iSYear = -1;
        if (!StringUtil.isNullZeroStringSpace(getMan290FrYear())) {
            iSYear = Integer.parseInt(getMan290FrYear());
        }
        int iSMonth = Integer.parseInt(getMan290FrMonth());
        int iSDay = Integer.parseInt(getMan290FrDay());

        UDate frDate = new UDate();
        frDate.setDate(iSYear, iSMonth, iSDay);
        frDate.setSecond(GSConstMain.DAY_START_SECOND);
        frDate.setMilliSecond(GSConstMain.DAY_START_MILLISECOND);
        if (frDate.getYear() != iSYear
        || frDate.getMonth() != iSMonth
        || frDate.getIntDay() != iSDay) {
            msg = new ActionMessage("error.input.notfound.date",
                    gsMsg.getMessage("main.src.man290.13"));
            errors.add("error.input.notfound.date", msg);
        } else {
            fromOk = true;
        }

        //終了年月日チェックフラグ(true=入力OK、false=NG)
        boolean toOk = false;

        int iEYear = -1;
        if (!StringUtil.isNullZeroStringSpace(getMan290ToYear())) {
            iEYear = Integer.parseInt(getMan290ToYear());
        }
        int iEMonth = Integer.parseInt(getMan290ToMonth());
        int iEDay = Integer.parseInt(getMan290ToDay());

        UDate toDate = new UDate();
        toDate.setDate(iEYear, iEMonth, iEDay);
        toDate.setSecond(GSConstMain.DAY_START_SECOND);
        toDate.setMilliSecond(GSConstMain.DAY_START_MILLISECOND);
        if (toDate.getYear() != iEYear
        || toDate.getMonth() != iEMonth
        || toDate.getIntDay() != iEDay) {
            msg = new ActionMessage("error.input.notfound.date",
                    gsMsg.getMessage("main.src.man290.14"));
            errors.add("error.input.notfound.date", msg);
        } else {
            toOk = true;
        }
        if (fromOk && toOk) {
            //from～to大小チェック
            if (frDate.compare(frDate, toDate) == UDate.SMALL) {
                msg = new ActionMessage("error.input.comp.text",
                        gsMsg.getMessage("main.src.man290.15"),
                        gsMsg.getMessage("cmn.start.or.end2"));
                errors.add("" + "error.input.comp.text", msg);
            }
        }


        boolean timeFrOk = true;
        boolean timeToOk = true;
        if (getMan290FrHour().equals("-1")
                || getMan290FrMin().equals("-1")
                || getMan290ToHour().equals("-1")
                || getMan290ToMin().equals("-1")) {
                           msg = new ActionMessage("error.input.required.text",
                                   gsMsg.getMessage("schedule.src.36"));
                           errors.add("" + "error.input.required.text", msg);
                           timeFrOk = false;
               }


        //個別チェックOKの場合
        if (timeFrOk && timeToOk) {
            frDate.setHour(Integer.parseInt(getMan290FrHour()));
            frDate.setMinute(Integer.parseInt(getMan290FrMin()));
            toDate.setHour(Integer.parseInt(getMan290ToHour()));
            toDate.setMinute(Integer.parseInt(getMan290ToMin()));

            //from～to大小チェック
            if (frDate.compare(frDate, toDate) != UDate.LARGE) {
                msg = new ActionMessage("error.input.comp.text",
                        gsMsg.getMessage("cmn.start.end"),
                        gsMsg.getMessage("cmn.start.lessthan.end"));
                errors.add("" + "error.input.comp.text", msg);
            }

        }

        //メッセージのチェック
        if (__checkNoInput(errors, getMan290Msg(), "man290Msg",
                gsMsg.getMessage("cmn.message"))) {
            if (__checkRange(
                    errors,
                    getMan290Msg(),
                    "man290Msg",
                    gsMsg.getMessage("cmn.message"),
                    GSConstMain.MAX_LENGTH_MSG)) {
                //先頭スペースチェック
                if (ValidateUtil.isSpaceStart(getMan290Msg())) {
                    msg = new ActionMessage("error.input.spase.start",
                                            gsMsg.getMessage(GSConstMain.TEXT_MSG));
                    StrutsUtil.addMessage(errors, msg, "man290Msg");
                } else {
                    __checkJisString(
                            errors,
                            getMan290Msg(),
                            "man290Msg",
                            gsMsg.getMessage("cmn.message"));
                }
            }

        }
        boolean valueError = false;
        //内容のチェック
        if (__checkRangeTextarea(
                errors,
                getMan290Value(),
                "man290Value",
                gsMsg.getMessage("cmn.content"),
                GSConstMain.MAX_LENGTH_VALUE)) {
            if (!StringUtil.isNullZeroString(getMan290Value())) {
                //スペースのみチェック
                if (!valueError && ValidateUtil.isSpaceOrKaigyou(getMan290Value())) {
                    msg = new ActionMessage("error.input.spase.cl.only",
                            gsMsg.getMessage("cmn.content"));
                    StrutsUtil.addMessage(errors, msg, "man290Value");
                    valueError = true;
                }
                //先頭スペースチェック
                if (!valueError && ValidateUtil.isSpaceStart(getMan290Value())) {
                    msg = new ActionMessage("error.input.spase.start",
                                            gsMsg.getMessage("cmn.content"));
                    StrutsUtil.addMessage(errors, msg, "man290Value");
                    valueError = true;
                }
                if (!valueError) {
                    __checkJisString(
                            errors,
                            getMan290Value(),
                            "man290Value",
                            gsMsg.getMessage("cmn.content"));
                }
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 指定された項目の未入力チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkNoInput(ActionErrors errors,
                                    String value,
                                    String element,
                                    String elementName) {
        boolean result = true;
        ActionMessage msg = null;

        if (StringUtil.isNullZeroString(value)) {
            msg = new ActionMessage("error.input.required.text", elementName);
            errors.add(element + "error.input.required.text", msg);
            result = false;
        } else {
            //スペースのみの入力かチェック
            if (ValidateUtil.isSpace(value)) {
                msg = new ActionMessage("error.input.spase.only", elementName);
                errors.add(element + "error.input.spase.only", msg);
                result = false;
            }

        }

        return result;
    }

    /**
     * <br>[機  能] 指定された項目の桁数チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @param range 桁数
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkRange(ActionErrors errors,
                                String value,
                                String element,
                                String elementName,
                                int range) {
        boolean result = true;
        ActionMessage msg = null;

        //MAX値を超えていないか
        if (value.length() > range) {
            msg = new ActionMessage("error.input.length.text", elementName,
                    String.valueOf(range));
            errors.add(element + "error.input.length.text", msg);
            result = false;
        }
        return result;
    }

    /**
     * <br>[機  能] 指定された項目の桁数チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @param range 桁数
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkRangeTextarea(ActionErrors errors,
                                String value,
                                String element,
                                String elementName,
                                int range) {
        boolean result = true;
        ActionMessage msg = null;

        //MAX値を超えていないか
        if (value.length() > range) {
            msg = new ActionMessage("error.input.length.textarea", elementName,
                    String.valueOf(range));
            errors.add(element + "error.input.length.textarea", msg);
            result = false;
            log__.debug("error:7");
        }
        return result;
    }

    /**
     * <br>[機  能] 指定された項目がJIS第2水準文字かチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param value 項目の値
     * @param element 項目名
     * @param elementName 項目名(日本語)
     * @return チェック結果 true : 正常, false : 異常
     */
    private boolean __checkJisString(ActionErrors errors,
                                String value,
                                String element,
                                String elementName) {
        boolean result = true;
        ActionMessage msg = null;
        //JIS第2水準文字か
        if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
            //利用不可能な文字を入力した場合
            String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
            msg = new ActionMessage("error.input.njapan.text", elementName, nstr);
            errors.add(element + "error.input.njapan.text", msg);
            result = false;
        }
        return result;
    }

    /**
     * @return the man290FileLabelList
     */
    public List<LabelValueBean> getMan290FileLabelList() {
        return man290FileLabelList__;
    }

    /**
     * @param man290FileLabelList the man290FileLabelList to set
     */
    public void setMan290FileLabelList(List<LabelValueBean> man290FileLabelList) {
        man290FileLabelList__ = man290FileLabelList;
    }

    /**
     * @return the man290files
     */
    public String[] getMan290files() {
        return man290files__;
    }

    /**
     * @param man290files the man290files to set
     */
    public void setMan290files(String[] man290files) {
        man290files__ = man290files;
    }

    /**
     * @return the man290elementKbn
     */
    public int getMan290elementKbn() {
        return man290elementKbn__;
    }

    /**
     * @param man290elementKbn the man290elementKbn to set
     */
    public void setMan290elementKbn(int man290elementKbn) {
        man290elementKbn__ = man290elementKbn;
    }

    /**
     * @return the man290ExDayLabel
     */
    public ArrayList<LabelValueBean> getMan290ExDayLabel() {
        return man290ExDayLabel__;
    }

    /**
     * @param man290ExDayLabel the man290ExDayLabel to set
     */
    public void setMan290ExDayLabel(ArrayList<LabelValueBean> man290ExDayLabel) {
        man290ExDayLabel__ = man290ExDayLabel;
    }

    /**
     * @return the man290groupSid
     */
    public int getMan290groupSid() {
        return man290groupSid__;
    }

    /**
     * @param man290groupSid the man290groupSid to set
     */
    public void setMan290groupSid(int man290groupSid) {
        man290groupSid__ = man290groupSid;
    }

    /**
     * @return the man290YearLabel
     */
    public ArrayList<LabelValueBean> getMan290YearLabel() {
        return man290YearLabel__;
    }

    /**
     * @param man290YearLabel the man290YearLabel to set
     */
    public void setMan290YearLabel(ArrayList<LabelValueBean> man290YearLabel) {
        man290YearLabel__ = man290YearLabel;
    }

    /**
     * @return the man290MonthLabel
     */
    public ArrayList<LabelValueBean> getMan290MonthLabel() {
        return man290MonthLabel__;
    }

    /**
     * @param man290MonthLabel the man290MonthLabel to set
     */
    public void setMan290MonthLabel(ArrayList<LabelValueBean> man290MonthLabel) {
        man290MonthLabel__ = man290MonthLabel;
    }

    /**
     * @return the man290DayLabel
     */
    public ArrayList<LabelValueBean> getMan290DayLabel() {
        return man290DayLabel__;
    }

    /**
     * @param man290DayLabel the man290DayLabel to set
     */
    public void setMan290DayLabel(ArrayList<LabelValueBean> man290DayLabel) {
        man290DayLabel__ = man290DayLabel;
    }

    /**
     * @return the man290HourLabel
     */
    public ArrayList<LabelValueBean> getMan290HourLabel() {
        return man290HourLabel__;
    }

    /**
     * @param man290HourLabel the man290HourLabel to set
     */
    public void setMan290HourLabel(ArrayList<LabelValueBean> man290HourLabel) {
        man290HourLabel__ = man290HourLabel;
    }

    /**
     * @return the man290MinuteLabel
     */
    public ArrayList<LabelValueBean> getMan290MinuteLabel() {
        return man290MinuteLabel__;
    }

    /**
     * @param man290MinuteLabel the man290MinuteLabel to set
     */
    public void setMan290MinuteLabel(ArrayList<LabelValueBean> man290MinuteLabel) {
        man290MinuteLabel__ = man290MinuteLabel;
    }

    /**
     * @return the man290WeekLabel
     */
    public ArrayList<LabelValueBean> getMan290WeekLabel() {
        return man290WeekLabel__;
    }

    /**
     * @param man290WeekLabel the man290WeekLabel to set
     */
    public void setMan290WeekLabel(ArrayList<LabelValueBean> man290WeekLabel) {
        man290WeekLabel__ = man290WeekLabel;
    }

    /**
     * @return the man290ExtKbn
     */
    public String getMan290ExtKbn() {
        return man290ExtKbn__;
    }

    /**
     * @param man290ExtKbn the man290ExtKbn to set
     */
    public void setMan290ExtKbn(String man290ExtKbn) {
        man290ExtKbn__ = man290ExtKbn;
    }

    /**
     * @return the man290Dweek
     */
    public String[] getMan290Dweek() {
        return man290Dweek__;
    }

    /**
     * @param man290Dweek the man290Dweek to set
     */
    public void setMan290Dweek(String[] man290Dweek) {
        man290Dweek__ = man290Dweek;
    }

    /**
     * @return the man290Week
     */
    public String getMan290Week() {
        return man290Week__;
    }

    /**
     * @param man290Week the man290Week to set
     */
    public void setMan290Week(String man290Week) {
        man290Week__ = man290Week;
    }

    /**
     * @return the man290Day
     */
    public String getMan290Day() {
        return man290Day__;
    }

    /**
     * @param man290Day the man290Day to set
     */
    public void setMan290Day(String man290Day) {
        man290Day__ = man290Day;
    }

    /**
     * @return the man290FrYear
     */
    public String getMan290FrYear() {
        return man290FrYear__;
    }

    /**
     * @param man290FrYear the man290FrYear to set
     */
    public void setMan290FrYear(String man290FrYear) {
        man290FrYear__ = man290FrYear;
    }

    /**
     * @return the man290FrMonth
     */
    public String getMan290FrMonth() {
        return man290FrMonth__;
    }

    /**
     * @param man290FrMonth the man290FrMonth to set
     */
    public void setMan290FrMonth(String man290FrMonth) {
        man290FrMonth__ = man290FrMonth;
    }

    /**
     * @return the man290FrDay
     */
    public String getMan290FrDay() {
        return man290FrDay__;
    }

    /**
     * @param man290FrDay the man290FrDay to set
     */
    public void setMan290FrDay(String man290FrDay) {
        man290FrDay__ = man290FrDay;
    }

    /**
     * @return the man290FrHour
     */
    public String getMan290FrHour() {
        return man290FrHour__;
    }

    /**
     * @param man290FrHour the man290FrHour to set
     */
    public void setMan290FrHour(String man290FrHour) {
        man290FrHour__ = man290FrHour;
    }

    /**
     * @return the man290FrMin
     */
    public String getMan290FrMin() {
        return man290FrMin__;
    }

    /**
     * @param man290FrMin the man290FrMin to set
     */
    public void setMan290FrMin(String man290FrMin) {
        man290FrMin__ = man290FrMin;
    }

    /**
     * @return the man290ToYear
     */
    public String getMan290ToYear() {
        return man290ToYear__;
    }

    /**
     * @param man290ToYear the man290ToYear to set
     */
    public void setMan290ToYear(String man290ToYear) {
        man290ToYear__ = man290ToYear;
    }

    /**
     * @return the man290ToMonth
     */
    public String getMan290ToMonth() {
        return man290ToMonth__;
    }

    /**
     * @param man290ToMonth the man290ToMonth to set
     */
    public void setMan290ToMonth(String man290ToMonth) {
        man290ToMonth__ = man290ToMonth;
    }

    /**
     * @return the man290ToDay
     */
    public String getMan290ToDay() {
        return man290ToDay__;
    }

    /**
     * @param man290ToDay the man290ToDay to set
     */
    public void setMan290ToDay(String man290ToDay) {
        man290ToDay__ = man290ToDay;
    }

    /**
     * @return the man290ToHour
     */
    public String getMan290ToHour() {
        return man290ToHour__;
    }

    /**
     * @param man290ToHour the man290ToHour to set
     */
    public void setMan290ToHour(String man290ToHour) {
        man290ToHour__ = man290ToHour;
    }

    /**
     * @return the man290ToMin
     */
    public String getMan290ToMin() {
        return man290ToMin__;
    }

    /**
     * @param man290ToMin the man290ToMin to set
     */
    public void setMan290ToMin(String man290ToMin) {
        man290ToMin__ = man290ToMin;
    }

    /**
     * @return the man290Msg
     */
    public String getMan290Msg() {
        return man290Msg__;
    }

    /**
     * @param man290Msg the man290Msg to set
     */
    public void setMan290Msg(String man290Msg) {
        man290Msg__ = man290Msg;
    }

    /**
     * @return the man290Value
     */
    public String getMan290Value() {
        return man290Value__;
    }

    /**
     * @param man290Value the man290Value to set
     */
    public void setMan290Value(String man290Value) {
        man290Value__ = man290Value;
    }

    /**
     * @return the man290Jtkbn
     */
    public String getMan290Jtkbn() {
        return man290Jtkbn__;
    }

    /**
     * @param man290Jtkbn the man290Jtkbn to set
     */
    public void setMan290Jtkbn(String man290Jtkbn) {
        man290Jtkbn__ = man290Jtkbn;
    }

    /**
     * @return the man290memberSid
     */
    public String[] getMan290memberSid() {
        return man290memberSid__;
    }

    /**
     * @param man290memberSid the man290memberSid to set
     */
    public void setMan290memberSid(String[] man290memberSid) {
        man290memberSid__ = man290memberSid;
    }

    /**
     * @return the man290SelectLeftUser
     */
    public String[] getMan290SelectLeftUser() {
        return man290SelectLeftUser__;
    }

    /**
     * @param man290SelectLeftUser the man290SelectLeftUser to set
     */
    public void setMan290SelectLeftUser(String[] man290SelectLeftUser) {
        man290SelectLeftUser__ = man290SelectLeftUser;
    }

    /**
     * @return the man290SelectRightUser
     */
    public String[] getMan290SelectRightUser() {
        return man290SelectRightUser__;
    }

    /**
     * @param man290SelectRightUser the man290SelectRightUser to set
     */
    public void setMan290SelectRightUser(String[] man290SelectRightUser) {
        man290SelectRightUser__ = man290SelectRightUser;
    }

    /**
     * @return the man290GroupList
     */
    public List<LabelValueBean> getMan290GroupList() {
        return man290GroupList__;
    }

    /**
     * @param man290GroupList the man290GroupList to set
     */
    public void setMan290GroupList(List<LabelValueBean> man290GroupList) {
        man290GroupList__ = man290GroupList;
    }

    /**
     * @return the man290LeftUserList
     */
    public List<LabelValueBean> getMan290LeftUserList() {
        return man290LeftUserList__;
    }

    /**
     * @param man290LeftUserList the man290LeftUserList to set
     */
    public void setMan290LeftUserList(List<LabelValueBean> man290LeftUserList) {
        man290LeftUserList__ = man290LeftUserList;
    }

    /**
     * @return the man290RightUserList
     */
    public List<LabelValueBean> getMan290RightUserList() {
        return man290RightUserList__;
    }

    /**
     * @param man290RightUserList the man290RightUserList to set
     */
    public void setMan290RightUserList(List<LabelValueBean> man290RightUserList) {
        man290RightUserList__ = man290RightUserList;
    }

    /**
     * <p>webSearchUse を取得します。
     * @return webSearchUse
     */
    public int getWebSearchUse() {
        return webSearchUse__;
    }

    /**
     * <p>webSearchUse をセットします。
     * @param webSearchUse webSearchUse
     */
    public void setWebSearchUse(int webSearchUse) {
        webSearchUse__ = webSearchUse;
    }

    /**
     * <p>man290HolKbn を取得します。
     * @return man290HolKbn
     */
    public int getMan290HolKbn() {
        return man290HolKbn__;
    }

    /**
     * <p>man290HolKbn をセットします。
     * @param man290HolKbn man290HolKbn
     */
    public void setMan290HolKbn(int man290HolKbn) {
        man290HolKbn__ = man290HolKbn;
    }

    /**
     * <p>man290helpMode を取得します。
     * @return man290helpMode
     */
    public String getMan290helpMode() {
        return man290helpMode__;
    }

    /**
     * <p>man290helpMode をセットします。
     * @param man290helpMode man290helpMode
     */
    public void setMan290helpMode(String man290helpMode) {
        man290helpMode__ = man290helpMode;
    }
}
