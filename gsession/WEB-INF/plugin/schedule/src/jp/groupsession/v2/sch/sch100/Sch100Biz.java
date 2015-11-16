package jp.groupsession.v2.sch.sch100;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupMsDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBelongmModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchColMsgDao;
import jp.groupsession.v2.sch.dao.ScheduleSearchDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.SchLabelValueModel;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.sch.pdf.SchListPdfModel;
import jp.groupsession.v2.sch.pdf.SchListPdfUtil;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.sch.sch010.Sch010UsrModel;
import jp.groupsession.v2.sch.sch010.SimpleScheduleModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch100Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch100Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /** セッションユーザ所属グループSIDリスト */
    private List<Integer> belongGpSidList__ = null;
    /**
     * <p>Set HttpServletRequest
     * @param reqMdl リクエスト情報
     */
    public Sch100Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * 初期表示画面情報を取得します
     * @param paramMdl アクションフォーム
     * @param con コネクション
     * @return Sch100Form アクションフォーム
     * @throws SQLException SQL実行時例外
     */
    public Sch100ParamModel getInitData(Sch100ParamModel paramMdl, Connection con)
    throws SQLException {

        paramMdl.setListMod("");

        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID


        //セッションユーザの所属グループを格納
        CmnBelongmDao bdao = new CmnBelongmDao(con);
        belongGpSidList__ = bdao.selectUserBelongGroupSid(sessionUsrSid);

        //管理者設定を取得
        SchCommonBiz cmnbiz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = cmnbiz.getAdmConfModel(con);

        //タイトル色区分
        paramMdl.setSch100colorKbn(adminConf.getSadMsgColorKbn());

        //共有範囲
        paramMdl.setSch010CrangeKbn(adminConf.getSadCrange());

        //セッション情報からユーザモデル生成
        Sch010UsrModel uMdl = new Sch010UsrModel();
        uMdl.setUsrSid(sessionUsrSid);
        uMdl.setUsrKbn(GSConstSchedule.USER_KBN_USER);
        paramMdl.setSch100UsrMdl(uMdl);
        paramMdl.setSch010SelectUsrSid(NullDefault.getString(paramMdl.getSch010SelectUsrSid()
                , String.valueOf(sessionUsrSid)));
        //管理者権限
        CommonBiz commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(con, usModel,
                                                     GSConstSchedule.PLUGIN_ID_SCHEDULE);
        if (adminUser) {
            paramMdl.setAdminKbn(GSConst.USER_ADMIN);
        } else {
            paramMdl.setAdminKbn(GSConst.USER_NOT_ADMIN);
        }
        //グループ・ユーザコンボ設定
        __setGroupUserCombo(paramMdl, sessionUsrSid, con);
        //開始・終了期間コンボ設定
        __setDateCombo(paramMdl);
        //ソートコンボ設定
        __setSortCombo(paramMdl);
        //カラーコメントリスト設定
        __setColorComment(paramMdl, con);

        //WEB検索のキーワードとHTML表示用のキーワードを設定する。
        String key = paramMdl.getSch010searchWord();
        String webSearchWord = "";
        String htmlSearchWord = "";
        if (!StringUtil.isNullZeroStringSpace(key)) {
            webSearchWord = StringUtil.toSingleCortationEscape(key);
            htmlSearchWord = StringUtilHtml.transToHTmlPlusAmparsant(key);
        }
        paramMdl.setSch100WebSearchWord(webSearchWord);
        paramMdl.setSch100HtmlSearchWord(htmlSearchWord);

        //ボタン用の処理モードを設定する。
        String cmd = paramMdl.getCmd();
        String btnCmd = "";
        if (!StringUtil.isNullZeroStringSpace(cmd)) {
            btnCmd = StringUtil.toSingleCortationEscape(cmd);
        }
        paramMdl.setSch100BtnCmd(btnCmd);

        return paramMdl;
    }

    /**
     * <br>[機  能] 検索対象がNULLの場合、検索対象のデフォルト値を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100ParamModel
     */
    public void setDefultSearchTarget(Sch100ParamModel paramMdl) {
        //検索対象
        if (paramMdl.getSch100SearchTarget() == null
                || paramMdl.getSch100SearchTarget().length == 0) {
            paramMdl.setSch100SearchTarget(getDefultSearchTarget());
        }
    }
    /**
     * <br>[機  能] 検索対象がNULLの場合、タイトルカラーのデフォルト値を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100ParamModel
     * @param con Connection
     * @throws SQLException SQL実行時例外
     */
    public void setDefultBgcolor(Sch100ParamModel paramMdl, Connection con) throws SQLException {

//        SchCommonBiz schBiz = new SchCommonBiz();
//        SchAdmConfModel admMdl = new SchAdmConfModel();
//        admMdl = schBiz.getAdmConfModel(con);
//
//        //検索対象
//        if (paramMdl.getSch100Bgcolor() == null || paramMdl.getSch100Bgcolor().length == 0) {
//            if (admMdl.getSadMsgColorKbn() == GSConstSchedule.SAD_MSG_NO_ADD) {
//                paramMdl.setSch100Bgcolor(Sch100Biz.getDefultBgcolor());
//            } else {
//                paramMdl.setSch100Bgcolor(Sch100Biz.getDefultBgcolorAll());
//            }
//
//        }
    }
    /**
     * <br>[機  能] CSV出力対象がNULLの場合、CSV出力対象のデフォルト値を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100ParamModel
     */
    public void setDefultCsvOut(Sch100ParamModel paramMdl) {
        //CSV出力対象
        if (paramMdl.getSch100CsvOutField() == null
                || paramMdl.getSch100CsvOutField().length == 0) {
            paramMdl.setSch100CsvOutField(Sch100Biz.getDefultCsvOut());
        }
    }
    /**
     * <br>[機  能] 検索対象のデフォルト値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return String[] デフォルトターゲット配列
     */
    public static String[] getDefultSearchTarget() {
        String[] targets = {
                String.valueOf(GSConstSchedule.SEARCH_TARGET_TITLE),
                String.valueOf(GSConstSchedule.SEARCH_TARGET_HONBUN)
              };
        return targets;
    }

    /**
     * <br>[機  能] タイトルカラーのデフォルト値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return String[] デフォルトタイトルカラー配列
     */
    public static String[] getDefultBgcolor() {
        String[] bgcolors = {
                String.valueOf(GSConstSchedule.BGCOLOR_BLUE),
                String.valueOf(GSConstSchedule.BGCOLOR_RED),
                String.valueOf(GSConstSchedule.BGCOLOR_GREEN),
                String.valueOf(GSConstSchedule.BGCOLOR_ORANGE),
                String.valueOf(GSConstSchedule.BGCOLOR_BLACK)
              };
        return bgcolors;
    }

    /**
     * <br>[機  能] タイトルカラーのデフォルト値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return String[] デフォルトタイトルカラー配列
     */
    public static String[] getDefultBgcolorAll() {
        String[] bgcolors = {
                String.valueOf(GSConstSchedule.BGCOLOR_BLUE),
                String.valueOf(GSConstSchedule.BGCOLOR_RED),
                String.valueOf(GSConstSchedule.BGCOLOR_GREEN),
                String.valueOf(GSConstSchedule.BGCOLOR_ORANGE),
                String.valueOf(GSConstSchedule.BGCOLOR_BLACK),
                String.valueOf(GSConstSchedule.BGCOLOR_NAVY),
                String.valueOf(GSConstSchedule.BGCOLOR_MAROON),
                String.valueOf(GSConstSchedule.BGCOLOR_TEAL),
                String.valueOf(GSConstSchedule.BGCOLOR_GRAY),
                String.valueOf(GSConstSchedule.BGCOLOR_LBLUE)
              };
        return bgcolors;
    }

    /**
     * <br>[機  能] CSV出力のデフォルト値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return String[] デフォルトCSV出力項目
     */
    public static String[] getDefultCsvOut() {
        String[] csvOut = {
                GSConstSchedule.CSV_OUT_LOGIN_ID,
                GSConstSchedule.CSV_OUT_GROUP_ID,
                GSConstSchedule.CSV_OUT_NAME,
                GSConstSchedule.CSV_OUT_FRDATE,
                GSConstSchedule.CSV_OUT_FRTIME,
                GSConstSchedule.CSV_OUT_TODATE,
                GSConstSchedule.CSV_OUT_TOTIME,
                GSConstSchedule.CSV_OUT_TITLE,
                GSConstSchedule.CSV_OUT_TITLE_COLOR,
                GSConstSchedule.CSV_OUT_VALUE,
                GSConstSchedule.CSV_OUT_BIKO,
                GSConstSchedule.CSV_OUT_EDITPERMIT,
                GSConstSchedule.CSV_OUT_OPEN,
                GSConstSchedule.CSV_OUT_TIMEKBN,
                GSConstSchedule.CSV_OUT_ADNAME,
                GSConstSchedule.CSV_OUT_EDNAME
        };
        return csvOut;
    }

    /**
     * フォーム情報から検索モデルを生成します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100ParamModel
     * @param con コネクション
     * @param sessionUsrSid ユーザSID
     * @throws SQLException SQL実行時例外
     * @return ScheduleListSearchModel 検索条件モデル
     */
    public ScheduleListSearchModel setScheduleListSearchModel(
            Sch100ParamModel paramMdl, Connection con, int sessionUsrSid) throws SQLException {

        CommonBiz cBiz = new CommonBiz();
        ScheduleListSearchModel searchMdl = new ScheduleListSearchModel();
        int userSid = Integer.parseInt(paramMdl.getSch010SelectUsrSid());
        int userKbn = Integer.parseInt(paramMdl.getSch010SelectUsrKbn());
        searchMdl.setSessionUserSid(sessionUsrSid);
        searchMdl.setSchUsrSid(userSid);
        searchMdl.setSchUsrKbn(userKbn);
        searchMdl.setSchOrder(paramMdl.getSch100SvOrderKey1());
        searchMdl.setSchSortKey(paramMdl.getSch100SvSortKey1());
        searchMdl.setSchOrder2(paramMdl.getSch100SvOrderKey2());
        searchMdl.setSchSortKey2(paramMdl.getSch100SvSortKey2());
        searchMdl.setKeyWordkbn(Integer.parseInt(paramMdl.getSch100SvKeyWordkbn()));
        String keyWord = NullDefault.getString(paramMdl.getSch100SvKeyValue(), "");
        searchMdl.setSchKeyValue(cBiz.setKeyword(keyWord));

        String[] targets = paramMdl.getSch100SvSearchTarget();
        boolean targetTitle = false;
        boolean targetBody = false;
        if (targets != null && targets.length > 0) {
            for (String target : targets) {
                if (String.valueOf(GSConstSchedule.SEARCH_TARGET_TITLE).equals(target)) {
                    targetTitle = true;
                }
                if (String.valueOf(GSConstSchedule.SEARCH_TARGET_HONBUN).equals(target)) {
                    targetBody = true;
                }
            }
        }
        searchMdl.setTargetTitle(targetTitle);
        searchMdl.setTargetValue(targetBody);
        //タイトル・内容についての検索条件がある場合は公開スケジュールを対象に検索
        if (StringUtil.isNullZeroString(paramMdl.getSch100SvKeyValue())
                ) {
            searchMdl.setSchPublic(-1);
        } else {
            searchMdl.setSchPublic(GSConstSchedule.DSP_PUBLIC);
        }

        //データが存在しない場合、グループが削除されていた場合はデフォルト所属グループを返す
        SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
        //デフォルト表示グループ
        String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);
        int dfGpSid = SchCommonBiz.getDspGroupSid(dfGpSidStr);

        int gpSid = 0;
        boolean myGrpFlg = false;

        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getSch100SvSltGroup(), dfGpSidStr);

        if (SchCommonBiz.isMyGroupSid(dspGpSidStr)) {
            gpSid = SchCommonBiz.getDspGroupSid(dspGpSidStr);
            //マイグループSIDをセット
            searchMdl.setSchSltGroupSid(dspGpSidStr);
            myGrpFlg = true;
        } else {
            gpSid = NullDefault.getInt(paramMdl.getSch100SvSltGroup(), dfGpSid);
            //通常グループSIDをセット
            searchMdl.setSchSltGroupSid(String.valueOf(gpSid));
        }
        searchMdl.setMyGrpFlg(myGrpFlg);

        //表示グループに所属しているか判定
        GroupBiz gpBiz = new GroupBiz();

        //ログインユーザが検索グループに所属しているか
        if (gpBiz.isBelongGroup(sessionUsrSid, gpSid, con)) {
            searchMdl.setGpBelongKbn(GSConstSchedule.EDIT_CONF_GROUP);
        }

        //検索モデルへ設定
        searchMdl.setSchSltUserSid(Integer.parseInt(paramMdl.getSch100SvSltUser()));
        UDate date1 = new UDate();
        date1.setDate(
                Integer.parseInt(paramMdl.getSch100SvSltStartYearFr()),
                Integer.parseInt(paramMdl.getSch100SvSltStartMonthFr()),
                Integer.parseInt(paramMdl.getSch100SvSltStartDayFr()));
        UDate date2 = new UDate();
        date2.setDate(
                Integer.parseInt(paramMdl.getSch100SvSltStartYearTo()),
                Integer.parseInt(paramMdl.getSch100SvSltStartMonthTo()),
                Integer.parseInt(paramMdl.getSch100SvSltStartDayTo()));
        UDate date3 = new UDate();
        date3.setDate(
                Integer.parseInt(paramMdl.getSch100SvSltEndYearFr()),
                Integer.parseInt(paramMdl.getSch100SvSltEndMonthFr()),
                Integer.parseInt(paramMdl.getSch100SvSltEndDayFr()));
        UDate date4 = new UDate();
        date4.setDate(
                Integer.parseInt(paramMdl.getSch100SvSltEndYearTo()),
                Integer.parseInt(paramMdl.getSch100SvSltEndMonthTo()),
                Integer.parseInt(paramMdl.getSch100SvSltEndDayTo()));
        searchMdl.setSchStartDateFr(date1);
        searchMdl.setSchStartDateTo(date2);
        searchMdl.setSchEndDateFr(date3);
        searchMdl.setSchEndDateTo(date4);
        searchMdl.setBgcolor(paramMdl.getSch100SvBgcolor());

        return searchMdl;
    }

    /**
     * 検索結果情報を取得します
     * @param paramMdl アクションフォーム
     * @param con コネクション
     * @return int 検索結果件数
     * @throws SQLException SQL実行時例外
     */
    public int getSearchResult(
                        Sch100ParamModel paramMdl,
                        Connection con) throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = biz.getSchPriConfModel(con, sessionUsrSid);
        //１ページ表示件数
        int limit = pconf.getSccDspList();

        //スケジュール検索情報を取得
        ScheduleListSearchModel searchMdl = setScheduleListSearchModel(
                paramMdl, con, sessionUsrSid);

        //全データ件数
        int maxCount = getScheduleCnt(
                paramMdl,
                searchMdl,
                sessionUsrSid,
                con);

        log__.debug("getScheduleCn==>" + maxCount);
        //現在ページ、スタート行
        int nowPage = paramMdl.getSch100PageNum();
        int offset = PageUtil.getRowNumber(nowPage, limit);
        //ページあふれ制御
        int maxPageNum = PageUtil.getPageCount(maxCount, limit);
        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, limit);
        if (maxPageStartRow < offset) {
            nowPage = maxPageNum;
            offset = maxPageStartRow;
        }
        searchMdl.setSchOffset(offset);
        searchMdl.setSchLimit(limit);

        paramMdl.setSch100ScheduleList(
                __getScheduleList(
                        paramMdl,
                        searchMdl,
                        sessionUsrSid,
                        true,
                        con)
                        );

        //ページング
        paramMdl.setSch100PageNum(nowPage);
        paramMdl.setSch100Slt_page1(nowPage);
        paramMdl.setSch100Slt_page2(nowPage);
        paramMdl.setSch100PageLabel(
            PageUtil.createPageOptions(maxCount, limit));
        return maxCount;
    }

    /**
     * <br>指定ユーザの月間スケジュールを取得します
     * @param paramMdl アクションフォーム
     * @param searchMdl 検索条件
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @return int データ件数
     * @throws SQLException SQL実行時例外
     */
    public int getScheduleCnt(
            Sch100ParamModel paramMdl,
            ScheduleListSearchModel searchMdl,
            int sessionUsrSid,
            Connection con) throws SQLException {

        //スケジュール情報を取得(指定ユーザ)
        ScheduleSearchDao schDao = new ScheduleSearchDao(con);
        return schDao.getCount(searchMdl, sessionUsrSid);
    }

    /**
     * <br>指定ユーザの月間スケジュールを取得します
     * @param paramMdl アクションフォーム
     * @param searchMdl 検索条件
     * @param sessionUsrSid セッションユーザSID
     * @param offset オフセット有無
     * @param con コネクション
     * @return ArrayList グループ>指定ユーザの順に格納
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<SimpleScheduleModel> __getScheduleList(
            Sch100ParamModel paramMdl,
            ScheduleListSearchModel searchMdl,
            int sessionUsrSid,
            boolean offset,
            Connection con) throws SQLException {

        //DBスケジュール情報
        ArrayList < SchDataModel > schDataList = null;
        //指定ユーザスケジュール
        ScheduleSearchDao schDao = new ScheduleSearchDao(con);
        //スケジュール情報取得
        schDataList = schDao.select(searchMdl, sessionUsrSid, offset);
        log__.debug("スケジュール件数==>" + schDataList.size());
        UDate dspDate = new UDate();
        dspDate.setDate(paramMdl.getSch010DspDate());

        ArrayList<SimpleScheduleModel> dayMdlList = new ArrayList<SimpleScheduleModel>();
        SimpleScheduleModel dspSchMdl = null;
        SchDataModel schMdl = null;

        //データが存在しない場合、グループが削除されていた場合はデフォルト所属グループを返す
        SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
        //デフォルト表示グループ
        String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);
        int dfGpSid = SchCommonBiz.getDspGroupSid(dfGpSidStr);

        int gpSid = 0;
        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getSch100SltGroup(), dfGpSidStr);
        boolean myGrpHnt = false;
        if (SchCommonBiz.isMyGroupSid(dspGpSidStr)) {
            gpSid = SchCommonBiz.getDspGroupSid(dspGpSidStr);
            myGrpHnt = true;
        } else {
            gpSid = NullDefault.getInt(paramMdl.getSch100SltGroup(), dfGpSid);
        }

        for (int j = 0; j < schDataList.size(); j++) {
            schMdl = schDataList.get(j);
            dspSchMdl = new SimpleScheduleModel();

            //表示スケジュールを設定
            dspSchMdl = __getSchDspMdl(schMdl, sessionUsrSid, gpSid, con, myGrpHnt);
            dayMdlList.add(dspSchMdl);
        }
        return dayMdlList;
    }

    /**
     * スケジュール一覧用の日付文字列を作成する
     * @param ud 変換するUDate
     * @return String 文字列変換されたud
     */
    public static String getDateString(UDate ud) {

        String ret = "";
        if (ud == null) {
            return ret;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(UDateUtil.getSlashYYMD(ud));
        sb.append("　");
        sb.append(UDateUtil.getSeparateHM(ud));
        ret = sb.toString();
        return ret;
    }
    /**
     * <br>[機  能] 指定グループに所属するユーザリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param groupSid グループSID
     * @param userSid セッションユーザSID
     * @param myGroupFlg マイグループ選択
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getUserLabelList(Connection con,
            int groupSid, int userSid, boolean myGroupFlg) throws SQLException {
        //指定無し
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textWithoutSpecify = gsMsg.getMessage("cmn.without.specifying");
        List < LabelValueBean > labelList = null;
        UserBiz usrBiz = new UserBiz();
        SchDao schDao = new SchDao(con);
        List<Integer> notAccessUserList = null;
        if (myGroupFlg) {
            labelList = usrBiz.getMyGroupUserLabelList(con, userSid, groupSid, null);
            notAccessUserList = schDao.getNotAccessUserList(userSid);
        } else {
            labelList = usrBiz.getNormalUserLabelList(con, groupSid, null, false, gsMsg);
            notAccessUserList = schDao.getNotAccessUserList(groupSid, userSid);
        }

        //閲覧を許可されていないユーザを除外する
        ArrayList<LabelValueBean> labelList2 = new ArrayList<LabelValueBean>();
        for (LabelValueBean label : labelList) {
            if (notAccessUserList.indexOf(Integer.parseInt(label.getValue())) < 0) {
                labelList2.add(label);
            }
        }
        labelList.clear();
        labelList.addAll(labelList2);

        labelList.add(0, new LabelValueBean(textWithoutSpecify, "-1"));
        return labelList;
    }

    /**
     * 検索条件部分のグループ、ユーザコンボを生成する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100ParamModel
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setGroupUserCombo(
            Sch100ParamModel paramMdl,
            int sessionUsrSid,
            Connection con)
    throws SQLException {

        //検索対象ユーザSID
        int userSid = NullDefault.getInt(
                paramMdl.getSch100SltUser(),
                Integer.parseInt(paramMdl.getSch010SelectUsrSid()));
        int userKbn = Integer.parseInt(paramMdl.getSch010SelectUsrKbn());
        String gpSid = NullDefault.getString(
                paramMdl.getSch100SltGroup(), paramMdl.getSch010DspGpSid());
        //グループスケジュール
        if (userSid == -1 && !SchCommonBiz.isMyGroupSid(gpSid)) {
            userSid = NullDefault.getInt(gpSid, 0);
            userKbn = GSConstSchedule.USER_KBN_GROUP;
        } else {
            if (paramMdl.getSch100SltUser() != null) {
                userKbn = GSConstSchedule.USER_KBN_USER;
            }
        }
        //グループコンボのラベルを取得する。
        Sch010Biz sch010Biz = new Sch010Biz(reqMdl__);
        List<SchLabelValueModel> groupLabel = sch010Biz.getGroupLabelList(con, sessionUsrSid);
        paramMdl.setSch100GroupLabel(groupLabel);

        SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
        int dspGpSid = -1;
        int dspUsrSid = -1;
        String dspGpSidStr = null;
        boolean myGroupFlg = false;
        if (userKbn == GSConstSchedule.USER_KBN_GROUP) {
            dspGpSidStr = scBiz.getEnableSelectGroup(groupLabel,
                    paramMdl.getSch100SltGroup(),
                    String.valueOf(userSid));
            dspGpSid = NullDefault.getInt(dspGpSidStr, userSid);
            dspUsrSid = NullDefault.getInt(paramMdl.getSch100SltUser(), -1);
            paramMdl.setSch100SltGroup(String.valueOf(dspGpSid));
        } else {
            String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);
            dspGpSidStr = NullDefault.getString(gpSid, dfGpSidStr);
            dspGpSidStr = scBiz.getEnableSelectGroup(groupLabel, dspGpSidStr, dfGpSidStr);
            if (SchCommonBiz.isMyGroupSid(dspGpSidStr)) {
                myGroupFlg = true;
            }
            dspGpSid = SchCommonBiz.getDspGroupSid(dspGpSidStr);
            dspUsrSid = NullDefault.getInt(paramMdl.getSch100SltUser(), userSid);
            paramMdl.setSch100SltGroup(dspGpSidStr);
        }

        //表示グループに本人が未所属で本人のスケジュールを表示する場合、
        if (userSid == sessionUsrSid) {
            //デフォルト所属グループ
            GroupBiz gpBiz = new GroupBiz();
            int sysDfGpSid = gpBiz.getDefaultGroupSid(sessionUsrSid, con);
            if (myGroupFlg) {
                CmnMyGroupMsDao mgmsDao = new CmnMyGroupMsDao(con);
                String[] users = new String[]{String.valueOf(userSid)};
                if (mgmsDao.getMyGroupMsInfo(sessionUsrSid, dspGpSid, users, true).size() < 1) {
                    dspGpSid = sysDfGpSid;
                    myGroupFlg = false;
                    paramMdl.setSch100SltGroup(String.valueOf(dspGpSid));
                }
            } else {
                CmnBelongmDao dao = new CmnBelongmDao(con);
                CmnBelongmModel mdl = dao.select(userSid, dspGpSid);
                if (mdl == null) {
                    dspGpSid = sysDfGpSid;
                    paramMdl.setSch100SltGroup(String.valueOf(dspGpSid));
                }
            }
        }

        //ユーザコンボ
        List<LabelValueBean> userLabel = getUserLabelList(con, dspGpSid, sessionUsrSid, myGroupFlg);
        paramMdl.setSch100UserLabel(userLabel);

        //マイグループの場合、所属しているか判定
        if (myGroupFlg) {
            CmnMyGroupMsDao mgmsDao = new CmnMyGroupMsDao(con);
            String[] users = new String[]{String.valueOf(userSid)};
            //未所属の場合所属ユーザの先頭を設定
            if (mgmsDao.getMyGroupMsInfo(sessionUsrSid, dspGpSid, users, true).size() < 1) {
                dspUsrSid = Integer.parseInt((userLabel.get(0)).getValue());
            }
        }

        paramMdl.setSch100SltUser(String.valueOf(dspUsrSid));
    }

    /**
     * 検索条件部分の開始・終了期間コンボを設定する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100ParamModel
     */
    private void __setDateCombo(Sch100ParamModel paramMdl) {
        //基準日
        String strDspDate = NullDefault.getString(
                paramMdl.getSch010DspDate(), new UDate().getDateString());
        UDate dspDate = new UDate();
        dspDate.setDate(strDspDate);
        UDate toDspDate = new UDate();
        toDspDate.setDate(strDspDate);
        toDspDate.addYear(1); //基準日から１年後

        //年月日コンボを設定
        paramMdl.setSch100StartYearFrLabel(__getYearLabelList(reqMdl__));   //年
        paramMdl.setSch100StartMonthFrLabel(__getMonthLabelList(reqMdl__)); //月
        paramMdl.setSch100StartDayFrLabel(__getDayLabelList(reqMdl__));     //日

        //期間設定
        paramMdl.setSch100SltStartYearFr(
                NullDefault.getString(paramMdl.getSch100SltStartYearFr(), dspDate.getStrYear()));
        paramMdl.setSch100SltStartMonthFr(
                NullDefault.getString(paramMdl.getSch100SltStartMonthFr(),
                        String.valueOf(dspDate.getMonth())));
        paramMdl.setSch100SltStartDayFr(
                NullDefault.getString(paramMdl.getSch100SltStartDayFr(),
                        String.valueOf(dspDate.getIntDay())));

        paramMdl.setSch100SltStartYearTo(
                NullDefault.getString(paramMdl.getSch100SltStartYearTo(), toDspDate.getStrYear()));
        paramMdl.setSch100SltStartMonthTo(
                NullDefault.getString(paramMdl.getSch100SltStartMonthTo(),
                        String.valueOf(toDspDate.getMonth())));
        paramMdl.setSch100SltStartDayTo(
                NullDefault.getString(paramMdl.getSch100SltStartDayTo(),
                        String.valueOf(toDspDate.getIntDay())));

        paramMdl.setSch100SltEndYearFr(
                NullDefault.getString(paramMdl.getSch100SltEndYearFr(), dspDate.getStrYear()));
        paramMdl.setSch100SltEndMonthFr(
                NullDefault.getString(paramMdl.getSch100SltEndMonthFr(),
                        String.valueOf(dspDate.getMonth())));
        paramMdl.setSch100SltEndDayFr(
                NullDefault.getString(paramMdl.getSch100SltEndDayFr(),
                        String.valueOf(dspDate.getIntDay())));
        paramMdl.setSch100SltEndYearTo(
                NullDefault.getString(paramMdl.getSch100SltEndYearTo(), toDspDate.getStrYear()));
        paramMdl.setSch100SltEndMonthTo(
                NullDefault.getString(paramMdl.getSch100SltEndMonthTo(),
                        String.valueOf(toDspDate.getMonth())));
        paramMdl.setSch100SltEndDayTo(
                NullDefault.getString(paramMdl.getSch100SltEndDayTo(),
                        String.valueOf(toDspDate.getIntDay())));
    }

    /**
     * 年コンボを作成する
     * @param reqMdl リクエスト情報
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getYearLabelList(RequestModel reqMdl) {

        //現在の年から前10年、後10年分のリスト
        int sysYear = (new UDate()).getYear();
        int start = sysYear - 10;
        int end = sysYear + 10;
        GsMessage gsMsg = new GsMessage(reqMdl);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                            gsMsg.getMessage(
                                    "cmn.year",
                                    new String[] {String.valueOf(value)}), String.valueOf(value)));
        }
        return labelList;
    }

    /**
     * 月コンボを作成する
     * @param reqMdl リクエスト情報
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getMonthLabelList(RequestModel reqMdl) {

        int start = 1;
        int end = 12;
        GsMessage gsMsg = new GsMessage(reqMdl);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                            value + gsMsg.getMessage("cmn.month"), String.valueOf(value)));
        }
        return labelList;
    }
    /**
     * 日コンボを作成する
     * @param reqMdl リクエスト情報
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getDayLabelList(RequestModel reqMdl) {

        int start = 1;
        int end = 31;

        GsMessage gsMsg = new GsMessage();
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                             value + gsMsg.getMessage("cmn.day"), String.valueOf(value)));
        }
        return labelList;
    }

    /**
     * 検索条件部分のソートコンボを設定する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100ParamModel
     */
    private void __setSortCombo(Sch100ParamModel paramMdl) {

        paramMdl.setSortKeyList(__makeSortCombo(paramMdl));
    }

    /**
     * 検索条件部分のソートコンボを設定する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100ParamModel
     * @return ソートコンボラベル一覧
     */
    private ArrayList<LabelValueBean> __makeSortCombo(Sch100ParamModel paramMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //ソートキーラベル
        ArrayList<LabelValueBean> sortLabel = new ArrayList<LabelValueBean>();
        //名前
        String textName = gsMsg.getMessage("cmn.name4");
        //開始日時
        String textStartDate = gsMsg.getMessage("schedule.sch100.11");
        //終了日時
        String textEndDate = gsMsg.getMessage("schedule.sch100.16");
        //タイトル/内容
        String textTitleContent = gsMsg.getMessage("schedule.sch100.7");
        String[] listSortKeyAllText = new String[] { textName,
                textStartDate, textEndDate, textTitleContent };
        for (int i = 0; i < GSConstSchedule.LIST_SORT_KEY_ALL.length; i++) {
            String label = listSortKeyAllText[i];
            String value = Integer.toString(GSConstSchedule.LIST_SORT_KEY_ALL[i]);
            sortLabel.add(new LabelValueBean(label, value));
        }
        return sortLabel;
    }

    /**
     * ユーザ氏名を取得する
     * <br>[機  能]ユーザ区分がグループの場合はグループ名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param userKbn ユーザ区分
     * @param con コネクション
     * @return String ユーザ名
     * @throws SQLException SQL実行時例外
     */
    private String __getUserName(int userSid, int userKbn, Connection con)
    throws SQLException {

        String ret = "";
        if (userKbn == GSConstSchedule.USER_KBN_USER) {
            CmnUsrmInfDao uDao = new CmnUsrmInfDao(con);
            CmnUsrmInfModel uMdl = uDao.select(userSid);
            if (uMdl != null) {
                ret = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
            }
        } else if (userKbn == GSConstSchedule.USER_KBN_GROUP) {
            GroupDao gDao = new GroupDao(con);
            CmnGroupmModel gMdl = gDao.getGroup(userSid);
            if (gMdl != null) {
                ret = gMdl.getGrpName();
            }
        }

        return ret;
    }

    /**
     * <br>[機  能]検索条件部分のタイトルカラーコメントを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setColorComment(Sch100ParamModel paramMdl, Connection con) throws SQLException {

        SchColMsgDao msgDao = new SchColMsgDao(con);
        ArrayList<String> msgList = msgDao.selectMsg();
        paramMdl.setSch100ColorMsgList(msgList);

    }
    /**
     * <br>[機  能] 表示用スケジュールデータを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param schMdl スケジュールデータ
     * @param sessionUsrSid ユーザSID
     * @param grpSid グループSID
     * @param con コネクション
     * @param myGrpHnt マイグループ判定 False:マイグループではない True:マイグループである
     * @return SimpleScheduleModel 表示用モデル
     * @throws SQLException SQL実行時例外
     */
    private SimpleScheduleModel __getSchDspMdl(
            SchDataModel schMdl,
            int sessionUsrSid,
            int grpSid,
            Connection con,
            boolean myGrpHnt) throws SQLException {

        SimpleScheduleModel dspSchMdl = new SimpleScheduleModel();
        //予定あり
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textYoteiari = gsMsg.getMessage("schedule.src.9");
        //表示グループに所属しているか判定
        GroupBiz gpBiz = new GroupBiz();
        boolean grpBelongHnt = gpBiz.isBelongGroup(sessionUsrSid, grpSid, con);

        if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER) {
            //ユーザスケジュールの場合は表示スケジュールユーザと同じグループに所属しているか判定
            grpBelongHnt = __getSchUsrBelongHnt(schMdl.getScdUserBlongGpList());
        }

        if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER
                && schMdl.getScdUsrSid() == sessionUsrSid) {
            //本人
            dspSchMdl.setPublic(GSConstSchedule.DSP_PUBLIC);
            dspSchMdl.setTitle(schMdl.getScdTitle());
        } else if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER
                && schMdl.getScdUsrSid() != sessionUsrSid) {

            //他ユーザ
            if (schMdl.getScdPublic() == GSConstSchedule.DSP_YOTEIARI) {
                //予定あり
                dspSchMdl.setTitle(textYoteiari);
                dspSchMdl.setPublic(GSConstSchedule.DSP_YOTEIARI);

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
                dspSchMdl.setPublic(schMdl.getScdPublic());
                //非公開
                return dspSchMdl;

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP
                     && grpBelongHnt) {

//                if (!myGrpHnt) {
//                    //所属グループのみ公開(マイグループは除く)
                    dspSchMdl.setTitle(schMdl.getScdTitle());
                    dspSchMdl.setGrpEdKbn(GSConstSchedule.EDIT_CONF_GROUP);
                    dspSchMdl.setPublic(schMdl.getScdPublic());
//                } else {
//                    //マイグループには「予定あり」
//                    dspSchMdl.setTitle(textYoteiari);
//                    dspSchMdl.setPublic(GSConstSchedule.DSP_YOTEIARI);
//                }

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP
                    && !(grpBelongHnt)) {

                //閲覧可能な所属グループではないユーザには「予定あり」
                dspSchMdl.setTitle(textYoteiari);
                dspSchMdl.setPublic(GSConstSchedule.DSP_YOTEIARI);
            } else {
                //公開
                dspSchMdl.setTitle(schMdl.getScdTitle());
            }

        //グループスケジュール
        } else {

            if (sessionUsrSid == schMdl.getScdAuid()) {
                dspSchMdl.setKjnEdKbn(GSConstSchedule.EDIT_CONF_OWN);
            }

            if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC
                    && !(grpBelongHnt)) {
                //非公開
                dspSchMdl.setPublic(schMdl.getScdPublic());
                return dspSchMdl;

            } else if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC
                    && grpBelongHnt) {

                //公開
                dspSchMdl.setTitle(schMdl.getScdTitle());
                dspSchMdl.setGrpEdKbn(GSConstSchedule.EDIT_CONF_GROUP);

            } else {
                //公開
                dspSchMdl.setTitle(schMdl.getScdTitle());
                dspSchMdl.setGrpEdKbn(GSConstSchedule.EDIT_CONF_GROUP);
            }

            //グループのスケジュール
            dspSchMdl.setPublic(schMdl.getScdPublic());
        }

        dspSchMdl.setSchSid(schMdl.getScdSid());
        dspSchMdl.setUserSid(String.valueOf(schMdl.getScdUsrSid()));
        dspSchMdl.setUserKbn(String.valueOf(schMdl.getScdUsrKbn()));
//        dspSchMdl.setTime(Sch010Biz.getTimeString(schMdl, frDate));
        dspSchMdl.setTimeKbn(schMdl.getScdDaily());
        dspSchMdl.setBgColor(schMdl.getScdBgcolor());
        dspSchMdl.setFromDateStr(Sch100Biz.getDateString(schMdl.getScdFrDate()));
        dspSchMdl.setToDateStr(Sch100Biz.getDateString(schMdl.getScdToDate()));
        dspSchMdl.setValueStr(StringUtilHtml.transToHTmlPlusAmparsant(schMdl.getScdValue()));
        dspSchMdl.setUserName(__getUserName(schMdl.getScdUsrSid(), schMdl.getScdUsrKbn(), con));

        return dspSchMdl;
    }
    /**
     * <br>[機  能] セッションユーザがスケジュールユーザと同じグループに所属しているか判定
     * <br>[解  説]
     * <br>[備  考]
     * @param belongSids 所属グループSID
     * @return 所属フラグ
     */
    private boolean __getSchUsrBelongHnt(ArrayList<Integer> belongSids) {
        boolean belongFlg = false;
        if (belongSids != null && !belongSids.isEmpty()) {
            for (int gpSid : belongSids) {
                if (belongGpSidList__ != null) {
                    if (belongGpSidList__.indexOf(gpSid) > -1) {
                        belongFlg = true;
                    }
                }
            }
        }
        return belongFlg;
    }

    /**
     * <br>[機  能] スケジュール一覧をPDF出力します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータモデル
     * @param appRootPath アプリケーションルートパス
     * @param outTempDir テンポラリディレクトパス
     * @param con コネクション
     * @return pdfModel スケジュール一覧ｓPDFモデル
     * @throws IOException IO実行時例外
     * @throws SQLException SQL実行例外
     */
    public SchListPdfModel createSchListPdf(
            Sch100ParamModel paramMdl,
            String appRootPath,
            String outTempDir,
            Connection con)
                    throws IOException, SQLException {
        OutputStream oStream = null;
        GsMessage msg = new GsMessage(reqMdl__);

        //スケジュール一覧PDF出力用モデル
        SchListPdfModel pdfModel = __getSchPdfDataList(paramMdl, con);

        String outBookName = msg.getMessage("schedule.sch100.5");
        String encOutBookName = __fileNameCheck(outBookName) + ".pdf";
        pdfModel.setFileName(encOutBookName);

        String saveFileName = "schlist" + reqMdl__.getSmodel().getUsrsid() + ".pdf";
        pdfModel.setSaveFileName(saveFileName);

        try {
            IOTools.isDirCheck(outTempDir, true);
            oStream = new FileOutputStream(outTempDir + saveFileName);
            SchListPdfUtil pdfUtil = new SchListPdfUtil(reqMdl__);
            pdfUtil.createSchListPdf(pdfModel, appRootPath, oStream);
        } catch (Exception e) {
            log__.error("スケジュール一覧PDF出力に失敗しました。", e);
        } finally {
            if (oStream != null) {
                oStream.flush();
                oStream.close();
            }
        }
        log__.debug("スケジュール一覧PDF出力を終了します。");

        return pdfModel;
    }

    /**
     * <br>[機  能] スケジュール一覧のPDFモデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @return スケジュール一覧PDFモデル
     * @throws SQLException SQL実行例外
     */
    private SchListPdfModel __getSchPdfDataList(
            Sch100ParamModel paramMdl,
            Connection con) throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        SchListPdfModel ret = new SchListPdfModel();

        //グループ名
        SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
        //デフォルト表示グループ
        String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, reqMdl__.getSmodel().getUsrsid());
        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getSch100SltGroup(), dfGpSidStr);

        //グループコンボのラベルを取得する。
        Sch010Biz sch010Biz = new Sch010Biz(reqMdl__);
        List<SchLabelValueModel> groupLabel =
                sch010Biz.getGroupLabelList(con, reqMdl__.getSmodel().getUsrsid());

        String grpName = null;
        if (groupLabel != null) {
            for (SchLabelValueModel mdl : groupLabel) {
                if (dspGpSidStr.equals(mdl.getValue())) {
                    grpName = mdl.getLabel();
                    break;
                }
            }
        }
        ret.setPdfGroup(NullDefault.getString(grpName, ""));

        // ユーザ名
        String name;
        if (Integer.parseInt(paramMdl.getSch100SvSltUser()) != -1) {
            CmnUsrmInfDao uDao = new CmnUsrmInfDao(con);
            CmnUsrmInfModel uMdl = uDao.select(Integer.parseInt(paramMdl.getSch100SvSltUser()));
            name = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
        } else {
            //指定無し
            name = gsMsg.getMessage("cmn.without.specifying");
        }
        ret.setPdfUser(name);

        //開始日
        ret.setPdfStDate(__getPdfStartDate(paramMdl));
        //終了日
        ret.setPdfEdDate(__getPdfEndDate(paramMdl));
        //キーワード
        ret.setPdfKeyWord(NullDefault.getString(paramMdl.getSch100SvKeyValue(), ""));
        //キーワード条件区分
        int keyWordKbn = Integer.parseInt(paramMdl.getSch100SvKeyWordkbn());
        ret.setPdfKeyWordKbn(keyWordKbn);

        String[] targets = paramMdl.getSch100SvSearchTarget();
        boolean targetTitle = false;
        boolean targetBody = false;
        if (targets != null && targets.length > 0) {
            for (String target : targets) {
                if (String.valueOf(GSConstSchedule.SEARCH_TARGET_TITLE).equals(target)) {
                    targetTitle = true;
                }
                if (String.valueOf(GSConstSchedule.SEARCH_TARGET_HONBUN).equals(target)) {
                    targetBody = true;
                }
            }
        }
        //検索対象 件名
        ret.setPdfTarKen(targetTitle);
        //検索対象 本文
        ret.setPdfTarHon(targetBody);
        //タイトル色 選択
        ret.setPdfColor(paramMdl.getSch100SvBgcolor());
        //タイトル色 コメント
        SchColMsgDao msgDao = new SchColMsgDao(con);
        ArrayList<String> msgList = msgDao.selectMsg();
        ret.setPdfColorMsg((String []) msgList.toArray(new String[msgList.size()]));
        //ソートキー１
        ret.setPdfSortKey1(__getPdfSortLabel(paramMdl, paramMdl.getSch100SvSortKey1()));
        //オーダーキー１
        ret.setPdfOrderKey1(paramMdl.getSch100SvOrderKey1());
        //ソートキー２
        ret.setPdfSortKey2(__getPdfSortLabel(paramMdl, paramMdl.getSch100SvSortKey2()));
        //オーダーキー２
        ret.setPdfOrderKey2(paramMdl.getSch100SvOrderKey2());


        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //スケジュール検索情報を取得
        ScheduleListSearchModel searchMdl = setScheduleListSearchModel(
                paramMdl, con, sessionUsrSid);

        ArrayList<SimpleScheduleModel> sch100ScheduleList =
                __getScheduleList(paramMdl, searchMdl, sessionUsrSid, false, con);

        //スケジュールリスト
        ret.setPdfScheduleList(sch100ScheduleList);

        return ret;

    }

    /**
     * <br>[機  能] PDF表示用の検索条件 開始日を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @return PDF表示用の検索条件 開始日
     */
    private String __getPdfStartDate(Sch100ParamModel paramMdl) {
        String ret = null;

        UDate stDateFr = new UDate();
        stDateFr.setDate(
                Integer.parseInt(paramMdl.getSch100SvSltStartYearFr()),
                Integer.parseInt(paramMdl.getSch100SvSltStartMonthFr()),
                Integer.parseInt(paramMdl.getSch100SvSltStartDayFr()));
        UDate stDateTo = new UDate();
        stDateTo.setDate(
                Integer.parseInt(paramMdl.getSch100SvSltStartYearTo()),
                Integer.parseInt(paramMdl.getSch100SvSltStartMonthTo()),
                Integer.parseInt(paramMdl.getSch100SvSltStartDayTo()));

        ret = UDateUtil.getYymdJ(stDateFr, reqMdl__)
                + " ～ " + UDateUtil.getYymdJ(stDateTo, reqMdl__);

        return ret;
    }

    /**
     * <br>[機  能] PDF表示用の検索条件 終了日を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @return PDF表示用の検索条件 終了日
     */
    private String __getPdfEndDate(Sch100ParamModel paramMdl) {
        String ret = null;

        UDate edDateFr = new UDate();
        edDateFr.setDate(
                Integer.parseInt(paramMdl.getSch100SvSltEndYearFr()),
                Integer.parseInt(paramMdl.getSch100SvSltEndMonthFr()),
                Integer.parseInt(paramMdl.getSch100SvSltEndDayFr()));
        UDate edDateTo = new UDate();
        edDateTo.setDate(
                Integer.parseInt(paramMdl.getSch100SvSltEndYearTo()),
                Integer.parseInt(paramMdl.getSch100SvSltEndMonthTo()),
                Integer.parseInt(paramMdl.getSch100SvSltEndDayTo()));

        ret = UDateUtil.getYymdJ(edDateFr, reqMdl__)
                + " ～ " + UDateUtil.getYymdJ(edDateTo, reqMdl__);

        return ret;
    }

    /**
     * <br>[機  能] 選択したソートコンボのラベルを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param value 選択値
     * @return ret ラベル
     */
    private String __getPdfSortLabel(Sch100ParamModel paramMdl, int value) {
        String ret = null;
        ArrayList<LabelValueBean> sortLabel = __makeSortCombo(paramMdl);

        for (LabelValueBean bean : sortLabel) {
            if (Integer.valueOf(bean.getValue()) == value) {
                ret = bean.getLabel();
                break;
            }
        }
        return ret;
    }

    /**
     * <br>[機  能] ファイル名として使用できるか文字列チェックする。
     * <br>[解  説] /\?*:|"<>. を除去
     *                    255文字超える場合は以降を除去
     * <br>[備  考]
     * @param fileName テンポラリディレクトリ
     * @return 出力したファイルのパス
     */
    private String __fileNameCheck(String fileName) {
        String escName = fileName;

        escName = StringUtilHtml.replaceString(escName, "/", "");
        escName = StringUtilHtml.replaceString(escName, "\\", ""); //\
        escName = StringUtilHtml.replaceString(escName, "?", "");
        escName = StringUtilHtml.replaceString(escName, "*", "");
        escName = StringUtilHtml.replaceString(escName, ":", "");
        escName = StringUtilHtml.replaceString(escName, "|", "");
        escName = StringUtilHtml.replaceString(escName, "\"", ""); //"
        escName = StringUtilHtml.replaceString(escName, "<", "");
        escName = StringUtilHtml.replaceString(escName, ">", "");
        escName = StringUtilHtml.replaceString(escName, ".", "");
        escName = StringUtil.trimRengeString(escName, 251); //ファイル名＋拡張子=255文字以内

        return escName;
    }
}