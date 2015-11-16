package jp.groupsession.v2.sch.sch084;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.csv.AbstractCsvRecordReader;
import jp.co.sjts.util.csv.CsvTokenizer;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchDataDao;
import jp.groupsession.v2.sch.dao.SchPriConfDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.sch.model.SchRepeatKbnModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] スケジュール 管理者設定 スケジュールインポート画面の取込みファイルをチェックするクラス
 * <br>[解  説]
 * <br>[備  考]
 */
public class SchImportCheck extends AbstractCsvRecordReader {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(SchImportCheck.class);
    /** エラー行存在フラグ */
    private boolean errorFlg__ = false;
    /** コネクション */
    private Connection con__ = null;
    /** アクションエラー */
    private ActionErrors errors__ = null;
    /** 有効データカウント */
    private int count__ = 0;
    /** フォーマットエラーフラグ */
    private boolean formatError__ = false;
    /** スケジュール基本設定 */
    private SchAdmConfModel conf__ = null;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;
    /** 重複登録チェックフラグ */
    private boolean repeatCheckFlg__ = false;
    /** SchDataDao */
    private SchDataDao schDataDao__ = null;
    /** SchPriConfDao */
    private SchPriConfDao priConfDao__ = null;
    /** CmnUsrmDao */
    private CmnUsrmDao usrDao__ = null;
    /** 重複チェック日時マップ */
    private HashMap<String, UDate> frDateMap__ = null;
    /** 重複チェック日時マップ */
    private HashMap<String, UDate> toDateMap__ = null;
    /** 重複チェック行リスト */
    private HashMap<String, List<String>> okMap__ = null;

    /**
     * <p>conf を取得します。
     * @return conf
     */
    public SchAdmConfModel getConf() {
        return conf__;
    }
    /**
     * <p>conf をセットします。
     * @param conf conf
     */
    public void setConf(SchAdmConfModel conf) {
        conf__ = conf;
    }
    /**
     * <p>con__ を取得します。
     * @return con
     */
    public Connection getCon() {
        return con__;
    }
    /**
     * <p>con__ をセットします。
     * @param con con__
     */
    public void setCon(Connection con) {
        con__ = con;
    }
    /**
     * <p>count__ を取得します。
     * @return count
     */
    public int getCount() {
        return count__;
    }
    /**
     * <p>count__ をセットします。
     * @param count count__
     */
    public void setCount(int count) {
        count__ = count;
    }
    /**
     * <p>errorFlg__ を取得します。
     * @return errorFlg
     */
    public boolean isErrorFlg() {
        return errorFlg__;
    }
    /**
     * <p>errorFlg__ をセットします。
     * @param errorFlg errorFlg__
     */
    public void setErrorFlg(boolean errorFlg) {
        errorFlg__ = errorFlg;
    }
    /**
     * <p>errors__ を取得します。
     * @return errors
     */
    public ActionErrors getErrors() {
        return errors__;
    }
    /**
     * <p>errors__ をセットします。
     * @param errors errors__
     */
    public void setErrors(ActionErrors errors) {
        errors__ = errors;
    }
    /**
     * <p>formatError__ を取得します。
     * @return formatError
     */
    public boolean isFormatError() {
        return formatError__;
    }
    /**
     * <p>formatError__ をセットします。
     * @param formatError formatError__
     */
    public void setFormatError(boolean formatError) {
        formatError__ = formatError;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param error アクションエラー
     * @param con コネクション
     * @param reqMdl RequestModel
     * @param repeatCheckFlg 重複登録チェックフラグ true:チェックする
     */
    public SchImportCheck(ActionErrors error, Connection con, RequestModel reqMdl,
            boolean repeatCheckFlg) {
        setErrors(error);
        setCon(con);
        reqMdl__ = reqMdl;
        setRepeatCheckFlg(repeatCheckFlg);
        schDataDao__ = new SchDataDao(con);
        priConfDao__ = new SchPriConfDao(con);
        usrDao__ = new CmnUsrmDao(con);
        frDateMap__ = new HashMap<String, UDate>();
        toDateMap__ = new HashMap<String, UDate>();
        okMap__ = new HashMap<String, List<String>>();
    }

    /**
     * <br>[機　能] CSVファイルのチェックを行なう
     * <br>[解　説]
     * <br>[備  考]
     *
     * @param csvFile 入力ファイル名
     * @return ture:エラー有 false:エラー無し
     * @throws Exception 実行時例外
     */
     public boolean isCsvDataOk(String csvFile) throws Exception {

         boolean ret = false;
         //基本設定取得
         SchCommonBiz biz = new SchCommonBiz(reqMdl__);
         setConf(biz.getAdmConfModel(con__));

         //ファイル読込み
         readFile(new File(csvFile), Encoding.WINDOWS_31J);
         log__.debug("有効データ件数 " + getCount());

         ret = isErrorFlg();

         //有効データ無し
         if (getCount() == 0) {
             ret = true;
         }
         return ret;
     }

    /**
     * <br>[機  能] csvファイル一行の処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param num 行番号
     * @param lineStr 行データ
     * @throws Exception csv読込時例外
     * @see jp.co.sjts.util.csv.AbstractCsvRecordReader#processedLine(long, java.lang.String)
     */
    protected void processedLine(long num, String lineStr) throws Exception {

        String buff;
        CsvTokenizer stringTokenizer = new CsvTokenizer(lineStr, ",");
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //CSV項目数
        String textCsvNumberItems = gsMsg.getMessage("cmn.csv.number.items");
        if (num > 1) {

            try {

                int j = 0;
                String eprefix = "inputFile." + num + ".";;
                int ecnt = errors__.size();

                log__.debug("項目数=" + stringTokenizer.length());
                if (stringTokenizer.length() != GSConstSchedule.IMP_VALUE_SIZE_ADM) {
                    //公開しない
                    String textLine = gsMsg.getMessage("cmn.line",
                            new String[] {String.valueOf(num)});
                    //取込みファイル
                    String textCaptureFile = gsMsg.getMessage("cmn.capture.file");
                    ActionMessage msg =
                        new ActionMessage(
                                "error.input.format.file",
                                textCaptureFile,
                                textCsvNumberItems + "(" + textLine + ")");
                    StrutsUtil.addMessage(errors__, msg, eprefix + num + "error.input.format.file");
                } else {

                    boolean inputFlg = false;
                    boolean inputFrTime = false;
                    boolean inputToTime = false;
                    UDate frDate = new UDate();
                    UDate toDate = new UDate();
                    boolean frCheck = false;
                    boolean toCheck = false;
                    UDate checkFrDate = null;
                    UDate checkToDate = null;

                    String loginId = "";
                    while (stringTokenizer.hasMoreTokens()) {
                        j++;
                        buff = stringTokenizer.nextToken();

                        //ログインID
                        if (j == 1) {
                            //ログインID入力有り
                            if (!StringUtil.isNullZeroString(buff)) {
                                inputFlg = true;
                                __isOkLoginId(errors__, buff, num);
                                loginId = buff;
                            } else {
                                inputFlg = false;
                            }
                        }
                        //グループID
                        if (j == 2) {
                            __isOkGroupId(errors__, buff, inputFlg, num);
                        }
                        //開始日付
                        if (j == 3) {
                            __isOkDate(errors__, buff, GSConstSchedule.FROM_DATE_KBN, num, frDate);
                        }
                        //開始時刻
                        if (j == 4) {
                            if (!StringUtil.isNullZeroString(buff)) {
                                inputFrTime = true;
                                __isOkTime(errors__,
                                        buff,
                                        GSConstSchedule.FROM_DATE_KBN,
                                        num, frDate);
                            } else {
                                inputFrTime = false;
                            }
                        }
                        //終了日付
                        if (j == 5) {
                            __isOkDate(errors__, buff, GSConstSchedule.TO_DATE_KBN, num, toDate);
                        }

                        //終了時刻
                        if (j == 6) {
                            if (!StringUtil.isNullZeroString(buff)) {
                                inputToTime = true;
                                __isOkTime(errors__,
                                        buff,
                                        GSConstSchedule.TO_DATE_KBN,
                                        num, toDate);
                            } else {
                                inputToTime = false;
                            }

                            //時刻片側入力チェック
                            if (inputFrTime != inputToTime) {
                                //時刻
                                String textTime = gsMsg.getMessage("schedule.src.76");
                                //num行目:
                                String textLine
                                    = gsMsg.getMessage("cmn.line2",
                                            new String[] {String.valueOf(num)});

                                ActionMessage msg = new ActionMessage("error.both.input",
                                        textLine + textTime);
                                StrutsUtil.addMessage(errors__, msg, eprefix + "error.both.input");
                            }
                            //開始・終了大小チェック
                            //開始・終了にエラーがある場合はnull
                            if (frDate != null && toDate != null) {
                                if (!inputFrTime && !inputToTime) {
                                    frDate.setZeroHhMmSs();
                                    toDate.setMaxHhMmSs();
                                }
                                if (frDate.compare(frDate, toDate) != UDate.LARGE) {
                                    //行目の開始・終了
                                    String textLineStartEnd =
                                        gsMsg.getMessage("cmn.start.end.of.line");
                                    //開始 < 終了
                                    String textStartLessEnd
                                                = gsMsg.getMessage("cmn.start.lessthan.end");
                                    ActionMessage msg = new ActionMessage("error.input.comp.text",
                                            num + textLineStartEnd, textStartLessEnd);
                                    StrutsUtil.addMessage(errors__,
                                            msg, eprefix + "error.input.comp.text");
                                }
                            }
                        }

                        //タイトル
                        if (j == 7) {
                            __isOkTitle(errors__, buff, num);
                        }
                        //タイトル色
                        if (j == 8) {
                            __isOkColor(errors__, buff, num);
                        }
                        //内容
                        if (j == 9) {
                            __isOkValue(errors__, buff, num);
                        }
                        //備考
                        if (j == 10) {
                            //備考
                            __isOkBiko(errors__, buff, num);
                        }
                        //編集権限
                        if (j == 11) {
                            __isOkEdit(errors__, buff, num);
                        }
                        //公開区分
                        if (j == 12) {
                            __isOkPub(errors__, buff, num);
                        }

                    }

                    //重複登録チェック
                    if (repeatCheckFlg__ && ecnt == errors__.size()) {
                        if (loginId != null && !loginId.equals("")) {

                            //ユーザSIDを取得する。
                            int usrSid = 0;

                            CmnUsrmModel usrModel = usrDao__.getUserSid(loginId);
                            if (usrModel != null) {
                                usrSid = usrModel.getUsrSid();
                            }

                            //個人設定を取得する。
                            SchPriConfModel priModel = priConfDao__.select(usrSid);
                            SchCommonBiz schBiz = new SchCommonBiz(reqMdl__);
                            SchRepeatKbnModel repertMdl
                                = schBiz.getRepertKbn(con__, priModel, usrSid);
                            if (repertMdl.getRepeatKbn()
                                    == GSConstSchedule.SCH_REPEAT_KBN_NG) {

                                List<Integer> ngUsrList = new ArrayList<Integer>();
                                ngUsrList.add(usrSid);
                                List<SchDataModel> rptSchList =
                                    schDataDao__.getSchData(ngUsrList, -1, frDate, toDate, -1, "0");

                                if (rptSchList != null && rptSchList.size() > 0) {

                                    //行目の
                                    String textLine = gsMsg.getMessage(
                                            "cmn.line2", new String[] {String.valueOf(num)});
                                    //スケジュール
                                    String textSchedule = gsMsg.getMessage("schedule.108");
                                    //期間
                                    String textKikan = gsMsg.getMessage("cmn.period");
                                    String errorMsg = textLine + textKikan;

                                    ActionMessage msg = new ActionMessage(
                                            "error.double.timezone", errorMsg, textSchedule);

                                    StrutsUtil.addMessage(errors__, msg, "repeat" + num);
                                }

                                List<String> okList = okMap__.get(loginId);
                                if (okList != null && okList.size() > 0) {

                                    //ファイル内重複チェック
                                    for (String n : okList) {

                                        checkFrDate = frDateMap__.get(String.valueOf(n));
                                        checkToDate = toDateMap__.get(String.valueOf(n));

                                        frCheck = checkFrDate.betweenYMDHM(frDate, toDate);
                                        toCheck = checkToDate.betweenYMDHM(frDate, toDate);

                                        if (frCheck || toCheck) {

                                            //スケジュール
                                            String textSch
                                                = gsMsg.getMessage("schedule.108");
                                            //行目
                                            String textLine = gsMsg.getMessage(
                                                "cmn.line2", new String[] {String.valueOf(num)});
                                            //行目
                                            String dupLine = gsMsg.getMessage(
                                                             "cmn.line2", new String[] {n});

                                            ActionMessage msg = new ActionMessage(
                                                    "error.select.dup.list2",
                                                    textLine + textSch,
                                                    dupLine + textSch);

                                            StrutsUtil.addMessage(
                                                    errors__, msg, "repeatThis" + num);

                                            break;
                                        }
                                    }
                                }
                                if (okList == null) {
                                    okList = new ArrayList<String>();
                                }
                                okList.add(String.valueOf(num));
                                okMap__.put(loginId, okList);
                                frDateMap__.put(String.valueOf(num), frDate);
                                toDateMap__.put(String.valueOf(num), toDate);

                            }
                        }
                    }
                }

                //エラー有り
                if (ecnt < errors__.size()) {
                    //エラー存在フラグON
                    setErrorFlg(true);
                } else {
                    //明細データ以降
                    if (num > 1) {
                        //有効データ件数カウントアップ
                        int cnt = getCount();
                        cnt += 1;
                        setCount(cnt);
                    }
                }

            } catch (Exception e) {
                log__.error("CSVファイル読込み時例外");
                throw e;
            }

        }
    }

    /**
     * <br>[機  能] ログインIDのチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param errors ActionErrors
     * @param loginId ログインID
     * @param num 行数
     * @return ActionErrors
     * @throws SQLException SQL実行時例外
     */
    private ActionErrors __isOkLoginId(ActionErrors errors, String loginId, long num)
    throws SQLException {

        ActionMessage msg = null;
        String eprefix = String.valueOf(num) + "loginId.";
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //行目の
        String textLine = gsMsg.getMessage("cmn.line2", new String[] {String.valueOf(num)});
        /** メッセージ ユーザID **/
        String userId = gsMsg.getMessage("cmn.user.id");
        if (loginId.length() < GSConstUser.MIN_LENGTH_USERID
                || loginId.length() > GSConstUser.MAX_LENGTH_USERID) {
            //MIN,MAX桁チェック
            msg = new ActionMessage("error.input.length2.text",
                    textLine + userId,
                    GSConstUser.MIN_LENGTH_USERID, GSConstUser.MAX_LENGTH_USERID);
            StrutsUtil.addMessage(errors, msg, eprefix + "error.input.length2.text");
        } else if (!GSValidateUtil.isUseridFormat(loginId)) {
            //ユーザＩＤフォーマットチェック
            msg = new ActionMessage("error.input.format.text",
                    textLine + userId);
            StrutsUtil.addMessage(errors, msg, eprefix + "error.input.format.text");
        } else {
            //存在チェック
            CmnUsrmDao usrmDao = new CmnUsrmDao(con__);
            if (usrmDao.selectLoginId(loginId) == 0) {
                msg = new ActionMessage("search.notfound.tdfkcode",
                        textLine + userId);
                StrutsUtil.addMessage(errors, msg, eprefix + "search.notfound.tdfkcode");
            }

        }
        return errors;
    }
    /**
     * <br>[機  能] グループIDのチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param errors ActionErrors
     * @param groupId グループID
     * @param idinput ログインID入力有無
     * @param num 行数
     * @throws SQLException SQL実行時例外
     * @return ActionErrors
     */
    private ActionErrors __isOkGroupId(
            ActionErrors errors,
            String groupId,
            boolean idinput,
            long num)
    throws SQLException {

        ActionMessage msg = null;
        String eprefix = String.valueOf(num) + "groupId.";
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //グループID
        String textGroupId = gsMsg.getMessage("cmn.group.id");
        /** メッセージ ユーザID **/
        String userId = gsMsg.getMessage("cmn.user.id");
        if (idinput) {
            //入力有りの場合はグループIDは入力不可
            if (!StringUtil.isNullZeroString(groupId)) {
                //公開しない
                String textLine = gsMsg.getMessage("cmn.line",
                                              new String[] {String.valueOf(num)});
                //入力チェック
                msg = new ActionMessage("error.both.input.user.group",
                        textLine, userId, textGroupId);
                StrutsUtil.addMessage(errors, msg, eprefix + "error.both.input.user.group");
            }
        } else {
            //行目の
            String textLine = gsMsg.getMessage("cmn.line2",
                                          new String[] {String.valueOf(num)});
            //入力無しの場合はグループIDは必須入力＆入力値チェック
            if (StringUtil.isNullZeroString(groupId)) {
                //未入力チェック
                msg = new ActionMessage("error.input.required.text",
                        textLine + userId);
                StrutsUtil.addMessage(errors, msg, eprefix + "error.input.required.text");
            } else if (groupId.length() > GSConstUser.MAX_LENGTH_USERID) {
                // MAX桁チェック
                msg = new ActionMessage("error.input.length.text",
                        textLine + textGroupId,
                        GSConstUser.MAX_LENGTH_GROUPID);
                StrutsUtil.addMessage(errors, msg, eprefix
                        + "error.input.length.text");
            } else if (!GSValidateUtil.isUseridFormat(groupId)) {
                //ユーザＩＤフォーマットチェック
                msg = new ActionMessage("error.input.format.text",
                        textLine + textGroupId);
                StrutsUtil.addMessage(errors, msg, eprefix + "error.input.format.text");
            } else {
                //存在チェック
                CmnGroupmDao dao = new CmnGroupmDao(con__);
                CmnGroupmModel model = dao.getGroupInf(groupId);
                if (model == null) {
                    msg = new ActionMessage("search.notfound.tdfkcode",
                            textLine + textGroupId);
                    StrutsUtil.addMessage(errors, msg, eprefix + "search.notfound.tdfkcode");
                }
            }
        }

        return errors;
    }


    /**
     * <p>年月日の入力チェックを行う(CSV用)
     * @param errors ActionErrors
     * @param ymd 年月日
     * @param frto 開始(0)・終了(1)
     * @param num 行数
     * @param udate チェックした日付
     * @return ActionErrors
     */
    private ActionErrors __isOkDate(ActionErrors errors,
            String ymd, int frto, long num, UDate udate) {

        boolean errorFlg = false;
        ActionMessage msg = null;
        String eprefix = String.valueOf(num) + "date.";
        String title = "";
        //開始日付
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textFrDate = gsMsg.getMessage("cmn.startdate");
        //終了日付
        String textToDate = gsMsg.getMessage("cmn.end.date");
        //行目の
        String textLine = gsMsg.getMessage("cmn.line2",
                                       new String[] {String.valueOf(num)});
        if (frto == 0) {
            title = textLine + textFrDate;
        } else {
            title = textLine + textToDate;
        }

        if (StringUtil.isNullZeroString(ymd)) {
            //未入力チェック
            msg = new ActionMessage("error.input.required.text", title);
            StrutsUtil.addMessage(errors, msg, eprefix + "error.input.required.text");
            errorFlg = true;
        } else {
            ArrayList<String> list = StringUtil.split("/", ymd);

            String sptYear = "";
            String sptMonth = "";
            String sptDay = "";
            boolean ymdFomatHnt = false;

            if (list.size() == 3) {
                sptYear = list.get(0);
                sptMonth = list.get(1);
                sptDay = list.get(2);

                //数値チェック
                if (!GSValidateUtil.isNumber(sptYear)
                    || !GSValidateUtil.isNumber(sptMonth)
                    || !GSValidateUtil.isNumber(sptDay)) {
                    msg = new ActionMessage("error.input.comp.text",
                            title,
                            gsMsg.getMessage("cmn.format.date2"));
                    StrutsUtil.addMessage(errors, msg, eprefix
                            + "error.input.comp.text");
                    errorFlg = true;
                } else {
                    ymd = StringUtil.getStrYyyyMmDd(sptYear, sptMonth, sptDay);
                    ymdFomatHnt = true;
                }
            }

            //yyyy/mm/dd形式入力
            if (!ymdFomatHnt) {
                msg = new ActionMessage("error.input.comp.text",
                        title,
                        gsMsg.getMessage("cmn.format.date2"));
                StrutsUtil.addMessage(errors, msg, eprefix
                        + "error.input.comp.text");
                errorFlg = true;

            } else {

                int iBYear = 0;
                int iBMonth = 0;
                int iBDay = 0;
                try {
                    String year = ymd.substring(0, 4);
                    String month = ymd.substring(4, 6);
                    String day = ymd.substring(6, 8);
                    iBYear = Integer.parseInt(year);
                    iBMonth = Integer.parseInt(month);
                    iBDay = Integer.parseInt(day);
                } catch (NumberFormatException e) {
                    log__.debug("年月日CSV入力エラー");
                    msg = new ActionMessage("error.input.notfound.date", title);
                    StrutsUtil.addMessage(errors, msg, eprefix
                            + "error.input.notfound.date");
                    errorFlg = true;
                }

                //論理チェック
                UDate date = new UDate();
                date.setDate(iBYear, iBMonth, iBDay);
                if (date.getYear() != iBYear
                || date.getMonth() != iBMonth
                || date.getIntDay() != iBDay) {

                    msg = new ActionMessage("error.input.notfound.date", title);
                    StrutsUtil.addMessage(errors, msg, eprefix
                            + "error.input.notfound.date");
                    errorFlg = true;
                }
            }
        }
        if (!errorFlg) {
            udate.setDate(ymd);
        } else {
            udate = null;
        }
        return errors;
    }

    /**
     * <p>時刻の入力チェックを行う(CSV用)
     * @param errors ActionErrors
     * @param time 時刻(HH:MM)
     * @param frto 開始(0)・終了(1)
     * @param num 行数
     * @param udate チェック済み日付
     * @return ActionErrors
     */
    private ActionErrors __isOkTime(ActionErrors errors,
            String time, int frto, long num, UDate udate) {

        boolean errorFlg = false;
        ActionMessage msg = null;
        String eprefix = String.valueOf(num) + String.valueOf(frto) + "time.";
        String title = "";
        //開始時刻
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textFrTime = gsMsg.getMessage("cmn.starttime");
        //終了時刻
        String textToTime = gsMsg.getMessage("cmn.endtime");
        //行目の
        String textLine = gsMsg.getMessage("cmn.line2", new String[] {String.valueOf(num)});
        if (frto == 0) {
            title = textLine + textFrTime;
        } else {
            title = textLine + textToTime;
        }

        int iBHour = 0;
        int iBMin = 0;
        if (!StringUtil.isNullZeroString(time)) {

            if (time.length() > 5) {
                //半角数字5桁(hh:mm形式)
                String textFormatTime = gsMsg.getMessage("cmn.format.time");
                msg = new ActionMessage("error.input.comp.text",
                        title,
                        textFormatTime);
                StrutsUtil.addMessage(errors, msg, eprefix
                        + "error.input.comp.text");
                errorFlg = true;
            } else {

                try {
                    ArrayList<String> list = StringUtil.split(":", time);
                    String hour = (String) list.get(0);
                    String min = (String) list.get(1);

                    iBHour = Integer.parseInt(hour);
                    iBMin = Integer.parseInt(min);
//                } catch (NumberFormatException e) {
                } catch (Exception e) {
                    log__.debug("時刻CSV入力エラー");
                    msg = new ActionMessage("error.input.notvalidate.data", title);
                    StrutsUtil.addMessage(errors, msg, eprefix
                            + "error.input.notvalidate.data");
                    errorFlg = true;
                }

                //論理チェック
                if (iBHour < 0 || 23 < iBHour) {
                    msg = new ActionMessage("error.input.notvalidate.data", title);
                    StrutsUtil.addMessage(errors, msg, eprefix
                            + "error.input.notvalidate.data");
                    errorFlg = true;
                }
                if (iBMin < 0 || 59 < iBMin) {
                    msg = new ActionMessage("error.input.notvalidate.data", title);
                    StrutsUtil.addMessage(errors, msg, eprefix
                            + "error.input.notvalidate.data");
                    errorFlg = true;
                }
                //分単位チェック
                int hourDiv = getConf().getSadHourDiv();
                int ans = iBMin % hourDiv;
                if (ans != 0) {
                    msg = new ActionMessage("error.input.notvalidate.data", title);
                    StrutsUtil.addMessage(errors, msg, eprefix
                            + "error.input.notvalidate.data");
                    errorFlg = true;
                }
            }
            if (!errorFlg && udate != null) {
                udate.setZeroHhMmSs();
                udate.setHour(iBHour);
                udate.setMinute(iBMin);
            } else {
                udate = null;
            }
        } else if (udate != null) {
            udate.setZeroHhMmSs();
            udate.setHour(iBHour);
            udate.setMinute(iBMin);
        }
        return errors;
    }

    /**
     * <br>[機  能] タイトルのチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param errors ActionErrors
     * @param title タイトル
     * @param num 行数
     * @return ActionErrors
     */
    private ActionErrors __isOkTitle(ActionErrors errors, String title, long num) {

        ActionMessage msg = null;
        String eprefix = String.valueOf(num) + "title";
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //行目の
        String textLine = gsMsg.getMessage("cmn.line2", new String[] {String.valueOf(num)});
        //スケジュール
        String textTitle = gsMsg.getMessage("cmn.title");
        String errorMsg = textLine + textTitle;

        if (__checkNoInput(errors, title, eprefix, errorMsg)) {
            if (__checkRange(
                    errors,
                    title,
                    eprefix,
                    errorMsg,
                    GSConstSchedule.MAX_LENGTH_TITLE)) {
                //先頭スペースチェック
                if (ValidateUtil.isSpaceStart(title)) {
                    msg = new ActionMessage("error.input.spase.start",
                            errorMsg);
                    StrutsUtil.addMessage(errors, msg, eprefix);
                } else if (ValidateUtil.isTab(title)) {
                    msg = new ActionMessage("error.input.tab.text",
                            errorMsg);
                    StrutsUtil.addMessage(errors, msg, eprefix);
                    
                } else {
                    __checkJisString(
                            errors,
                            title,
                            eprefix,
                            errorMsg);
                }
            }

        }

        return errors;
    }

    /**
     * <br>[機  能] タイトル色のチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param errors ActionErrors
     * @param color 色区分
     * @param num 行数
     * @return ActionErrors
     * @throws SQLException SQL実行時例外
     */
    private ActionErrors __isOkColor(
            ActionErrors errors, String color, long num) throws SQLException {

        SchCommonBiz schBiz = new SchCommonBiz();
        int colorKbn = schBiz.getAdmConfModel(con__).getSadMsgColorKbn();

        ActionMessage msg = null;
        //タイトル色
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textTitleColor = gsMsg.getMessage("schedule.10");
        String eprefix = String.valueOf(num) + "color";
        //行目の
        String textLine = gsMsg.getMessage("cmn.line2", new String[] {String.valueOf(num)});
        String errorMsg = textLine + textTitleColor;

        if (StringUtil.isNullZeroString(color)) {
            msg =
                new ActionMessage("error.input.required.text",
                        errorMsg);
            StrutsUtil.addMessage(errors, msg, eprefix);

        } else if (colorKbn == GSConstSchedule.SAD_MSG_NO_ADD
                && !color.equals("1")
                && !color.equals("2")
                && !color.equals("3")
                && !color.equals("4")
                && !color.equals("5")
               ) {
                   msg =
                       new ActionMessage(
                               "error.input.comp.text",
                               errorMsg,
                               "1～5");
                   StrutsUtil.addMessage(errors, msg, eprefix + "color");
               } else if (colorKbn == GSConstSchedule.SAD_MSG_ADD
                       && !color.equals("1")
                       && !color.equals("2")
                       && !color.equals("3")
                       && !color.equals("4")
                       && !color.equals("5")
                       && !color.equals("6")
                       && !color.equals("7")
                       && !color.equals("8")
                       && !color.equals("9")
                       && !color.equals("10")
                      ) {
                          msg =
                              new ActionMessage(
                                      "error.input.comp.text",
                                      errorMsg,
                                      "1～10");
                          StrutsUtil.addMessage(errors, msg, eprefix + "color");
                      }

        return errors;
    }

    /**
     * <br>[機  能] 内容のチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param errors ActionErrors
     * @param value 内容
     * @param num 行数
     * @return ActionErrors
     */
    private ActionErrors __isOkValue(ActionErrors errors, String value, long num) {

        ActionMessage msg = null;
        String eprefix = String.valueOf(num) + "value";
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //行目の
        String textLine = gsMsg.getMessage("cmn.line2", new String[] {String.valueOf(num)});
        //内容
        if (!StringUtil.isNullZeroString(value)) {
            //内容
            String textContent = gsMsg.getMessage("cmn.content");
            //内容 桁数チェック
            if (value.length() > GSConstSchedule.MAX_LENGTH_VALUE) {
                msg = new ActionMessage("error.input.length.text",
                        textLine + textContent,
                            String.valueOf(GSConstSchedule.MAX_LENGTH_VALUE));
                StrutsUtil.addMessage(errors, msg, eprefix + "value");
            }
            //内容 スペース・改行のみチェック
            if (ValidateUtil.isSpaceOrKaigyou(value)) {
                msg = new ActionMessage("error.input.spase.cl.only",
                        textLine + textContent);
                StrutsUtil.addMessage(errors, msg, eprefix + "value");
            }
            //内容 JIS第2水準チェック
            if (!GSValidateUtil.isGsJapaneaseStringTextArea(value)) {
                //利用不可能な文字を入力した場合
                String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(value);
                msg = new ActionMessage("error.input.njapan.text",
                        textLine + textContent,
                        nstr);
                StrutsUtil.addMessage(errors, msg, eprefix + "value");
            }
        }

        return errors;
    }
    /**
     * <br>[機  能] 備考のチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param errors ActionErrors
     * @param biko 備考
     * @param num 行数
     * @return ActionErrors
     */
    private ActionErrors __isOkBiko(ActionErrors errors, String biko, long num) {

        ActionMessage msg = null;
        String eprefix = String.valueOf(num) + "biko";
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //行目の
        String textLine = gsMsg.getMessage("cmn.line2", new String[] {String.valueOf(num)});
        //備考
        if (!StringUtil.isNullZeroString(biko)) {
            //備考
            String textMemo = gsMsg.getMessage("cmn.memo");
            //備考 桁数チェック
            if (biko.length() > GSConstReserve.MAX_LENGTH_BIKO) {
                msg = new ActionMessage("error.input.length.text",
                        textLine + textMemo,
                            String.valueOf(GSConstReserve.MAX_LENGTH_BIKO));
                StrutsUtil.addMessage(errors, msg, eprefix + "biko");
            }
            //備考 スペース・改行のみチェック
            if (ValidateUtil.isSpaceOrKaigyou(biko)) {
                msg = new ActionMessage("error.input.spase.cl.only",
                        textLine + textMemo);
                StrutsUtil.addMessage(errors, msg, eprefix + "biko");
            }
            //備考 JIS第2水準チェック
            if (!GSValidateUtil.isGsJapaneaseStringTextArea(biko)) {
                //利用不可能な文字を入力した場合
                String nstr = GSValidateUtil.getNotGsJapaneaseStringTextArea(biko);
                msg = new ActionMessage("error.input.njapan.text",
                        textLine + textMemo,
                        nstr);
                StrutsUtil.addMessage(errors, msg, eprefix + "biko");
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 編集権限のチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param errors ActionErrors
     * @param edit 編集権限区分
     * @param num 行数
     * @return ActionErrors
     */
    private ActionErrors __isOkEdit(ActionErrors errors, String edit, long num) {

        ActionMessage msg = null;
        String eprefix = String.valueOf(num) + "edit";
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //行目の
        String textLine = gsMsg.getMessage("cmn.line2", new String[] {String.valueOf(num)});
        //編集権限
        String textEditPermission = gsMsg.getMessage("cmn.edit.permissions");
        String errorMsg = textLine + textEditPermission;

        if (StringUtil.isNullZeroString(edit)) {
            msg =
                new ActionMessage("error.input.required.text",
                        errorMsg);
            StrutsUtil.addMessage(errors, msg, eprefix);

        } else if (!edit.equals("0")
         && !edit.equals("1")
         && !edit.equals("2")
        ) {
            msg =
                new ActionMessage(
                        "error.input.comp.text",
                        errorMsg,
                        "0～2");
            StrutsUtil.addMessage(errors, msg, eprefix);
        }

        return errors;
    }

    /**
     * <br>[機  能] 公開区分のチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param errors ActionErrors
     * @param pub 公開区分
     * @param num 行数
     * @return ActionErrors
     */
    private ActionErrors __isOkPub(ActionErrors errors, String pub, long num) {

        ActionMessage msg = null;
        //公開区分
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textPublicKbn = gsMsg.getMessage("cmn.public.kbn");
        String eprefix = String.valueOf(num) + "public";
        //行目の
        String textLine = gsMsg.getMessage("cmn.line2", new String[] {String.valueOf(num)});
        String errorMsg = textLine + textPublicKbn;

        if (StringUtil.isNullZeroString(pub)) {
            msg =
                new ActionMessage("error.input.required.text",
                        errorMsg);
            StrutsUtil.addMessage(errors, msg, eprefix);

        } else if (!pub.equals("0")
         && !pub.equals("1")
         && !pub.equals("2")
         && !pub.equals("3")) {
            msg =
                new ActionMessage(
                        "error.input.comp.text",
                        errorMsg,
                        "0～3");
            StrutsUtil.addMessage(errors, msg, eprefix);
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
            log__.debug("error:6");
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
     * <p>repeatCheckFlg__ を取得します。
     * @return repeatCheckFlg__
     */
    public boolean isRepeatCheckFlg() {
        return repeatCheckFlg__;
    }
    /**
     * <p>repeatCheckFlg__ をセットします。
     * @param repeatCheckFlg repeatCheckFlg__
     */
    public void setRepeatCheckFlg(boolean repeatCheckFlg) {
        repeatCheckFlg__ = repeatCheckFlg;
    }
    /**
     * <p>frDateMap__ を取得します。
     * @return frDateMap__
     */
    public HashMap<String, UDate> getFrDateMap__() {
        return frDateMap__;
    }
    /**
     * <p>frDateMap__ をセットします。
     * @param frDateMap frDateMap__
     */
    public void setFrDateMap__(HashMap<String, UDate> frDateMap) {
        frDateMap__ = frDateMap;
    }
    /**
     * <p>toDateMap__ を取得します。
     * @return toDateMap__
     */
    public HashMap<String, UDate> getToDateMap__() {
        return toDateMap__;
    }
    /**
     * <p>toDateMap__ をセットします。
     * @param toDateMap toDateMap__
     */
    public void setToDateMap__(HashMap<String, UDate> toDateMap) {
        toDateMap__ = toDateMap;
    }
}