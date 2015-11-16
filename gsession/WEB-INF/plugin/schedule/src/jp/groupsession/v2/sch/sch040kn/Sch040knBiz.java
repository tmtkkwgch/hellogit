package jp.groupsession.v2.sch.sch040kn;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.SaibanModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchDataDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.sch.sch040.Sch040Biz;
import jp.groupsession.v2.sch.sch040.Sch040ParamModel;
import jp.groupsession.v2.sch.sch040.model.Sch040AttendModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] スケジュール確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch040knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch040knBiz.class);
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスト情報 */
    public RequestModel reqMdl__ = null;
    /** 採番コントローラ */
    public MlCountMtController cntCon__ = null;
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
    public Sch040knBiz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }
    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl リクエスト情報
     * @param cntCon MlCountMtController
     */
    public Sch040knBiz(Connection con, RequestModel reqMdl, MlCountMtController cntCon) {
        con__ = con;
        reqMdl__ = reqMdl;
        cntCon__ = cntCon;
    }

    /**
     * <br>初期表示画面情報を取得します
     * @param paramMdl Sch040knParamModel
     * @param con コネクション
     * @return アクションフォーム
     * @throws SQLException SQL実行時例外
     */
    public Sch040knParamModel getInitData(Sch040knParamModel paramMdl, Connection con)
    throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();

        //管理者設定を取得
        SchCommonBiz adminbiz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = adminbiz.getAdmConfModel(con);

        //リクエストパラメータを取得
        //表示項目設定
        Sch040Biz biz = new Sch040Biz(con__, reqMdl__, cntCon__);
        if (paramMdl.getCmd().equals(GSConstSchedule.CMD_ADD)) {
//          新規モード
            //名前
            String uid = paramMdl.getSch010SelectUsrSid();
            String ukb = paramMdl.getSch010SelectUsrKbn();
            log__.debug("uid=" + uid);
            log__.debug("ukb=" + ukb);
            paramMdl.setSch040UsrName(
                    biz.getUsrName(Integer.parseInt(uid), Integer.parseInt(ukb), con));
            //登録者
            paramMdl.setSch040AddUsrName(usModel.getUsisei() + " " + usModel.getUsimei());
            //画面項目設定
            paramMdl.setSch040knIsEdit(GSConstSchedule.CAN_EDIT_TRUE);
            __setSch040knFormFromReq(paramMdl);

            CmnUsrmInfDao uinfDao = new CmnUsrmInfDao(con);
            //追加済みユーザSID
            ArrayList<Integer> list = null;
            ArrayList<CmnUsrmInfModel> selectUsrList = null;
            String[] users = paramMdl.getSv_users();
            if (users != null && users.length > 0) {
                list = new ArrayList<Integer>();
                for (int i = 0; i < users.length; i++) {
                    list.add(new Integer(users[i]));
                }
                selectUsrList = uinfDao.getUserList(list);
            }
            //同時登録ユーザラベル
            paramMdl.setSch040SelectUsrLabel(selectUsrList);
        } else if (paramMdl.getCmd().equals(GSConstSchedule.CMD_EDIT)) {
            GsMessage gsMsg = new GsMessage(reqMdl__);
//          閲覧モード
            String scdSid = paramMdl.getSch010SchSid();
            ScheduleSearchModel scdMdl = biz.getSchData(Integer.parseInt(scdSid),
                    adminConf,
                    GSConstSchedule.SSP_AUTHFILTER_VIEW,
                    con);
            if (scdMdl != null) {
                //対象のスケジュールを閲覧可能かを判定する
                int sessionUserSid = usModel.getUsrsid();
                SchCommonBiz schCmnBiz = new SchCommonBiz();
                if (!schCmnBiz.canAccessSchedule(con, scdMdl, sessionUserSid)) {
                    paramMdl.setSch040ViewFlg(false);
                } else {
                    //ユーザ名
                    if (scdMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
                        paramMdl.setSch040UsrName(
                                biz.getUsrName(scdMdl.getScdUsrSid(), scdMdl.getScdUsrKbn(), con));
                    } else {
                        paramMdl.setSch040UsrName(
                                scdMdl.getScdUsrSei() + " " + scdMdl.getScdUsrMei());
                    }
                    //登録者
                    paramMdl.setSch040AddUsrName(
                            scdMdl.getScdAuidSei() + " " + scdMdl.getScdAuidMei());
                    paramMdl.setSch040AddUsrJkbn(String.valueOf(scdMdl.getScdAuidJkbn()));
                    //登録日時
                    String textAddDate = gsMsg.getMessage("schedule.src.84");
                    paramMdl.setSch040AddDate(
                            textAddDate + " : "
                            + UDateUtil.getSlashYYMD(scdMdl.getScdAdate())
                            + " "
                            + UDateUtil.getSeparateHM(scdMdl.getScdAdate()));
                    //閲覧モードチェック
                    Sch010Biz sch010biz = new Sch010Biz(reqMdl__);
                    if (sch010biz.isEditOk(Integer.parseInt(scdSid), reqMdl__, con)) {
                        paramMdl.setSch040knIsEdit(GSConstSchedule.CAN_EDIT_TRUE);
                        //表示項目をアクションフォームから取得
                        __setSch040knFormFromReq(paramMdl);

                    } else {
                        //閲覧モード
                        paramMdl.setSch040knIsEdit(GSConstSchedule.CAN_EDIT_FALSE);
                        //表示項目をDBから取得
                        __setSch040knFormFromDb(paramMdl, scdMdl, con);
                    }
                    paramMdl.setSch040DataFlg(true);
                    paramMdl.setSch040ViewFlg(true);
                }
            } else {
                paramMdl.setSch040DataFlg(false);
                paramMdl.setSch040ViewFlg(true);
            }

        }
        return paramMdl;
    }


    /**
     * <br>リクエストパラメータから画面項目を設定する
     * @param paramMdl Sch040knParamModel
     */
    private void __setSch040knFormFromReq(Sch040knParamModel paramMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl__);

        //開始日時
        StringBuilder frBuf = new StringBuilder();
        frBuf.append(gsMsg.getMessage("cmn.date4",
                                                new String[] {paramMdl.getSch040FrYear(),
                                                                    paramMdl.getSch040FrMonth(),
                                                                    paramMdl.getSch040FrDay()}));

        //終了日時
        StringBuilder toBuf = new StringBuilder();
        toBuf.append(gsMsg.getMessage("cmn.date4",
                                                new String[] {paramMdl.getSch040ToYear(),
                                                                    paramMdl.getSch040ToMonth(),
                                                                    paramMdl.getSch040ToDay()}));

        //時間指定無し判定
        if (!paramMdl.getSch040FrHour().equals("-1")
                || !paramMdl.getSch040FrMin().equals("-1")
                || !paramMdl.getSch040ToHour().equals("-1")
                || !paramMdl.getSch040ToMin().equals("-1")
        ) {
            //時
            //From時間指定有り
            if (!paramMdl.getSch040FrHour().equals("-1")
             && !paramMdl.getSch040FrMin().equals("-1")) {
                String[] params = {paramMdl.getSch040FrHour(),
                                    StringUtil.toDecFormat(paramMdl.getSch040FrMin(), "00")};
                frBuf.append(gsMsg.getMessage("cmn.time.input", params));
            } else {
                //省略
                String[] params = {"0", "00"};
                frBuf.append(gsMsg.getMessage("cmn.time.input", params));
            }
            //To時間指定有り
            if (!paramMdl.getSch040ToHour().equals("-1")
             && !paramMdl.getSch040ToMin().equals("-1")) {

                String[] params = {paramMdl.getSch040ToHour(),
                        StringUtil.toDecFormat(paramMdl.getSch040ToMin(), "00")};
                toBuf.append(gsMsg.getMessage("cmn.time.input", params));
            } else {
                //省略
                String[] params = {"23", "59"};
                toBuf.append(gsMsg.getMessage("cmn.time.input", params));
            }
        }
        //開始日時
        paramMdl.setSch040knFromDate(frBuf.toString());
        //終了日時
        paramMdl.setSch040knToDate(toBuf.toString());
    }

    /**
     * <br>DBから画面項目を設定する
     * @param paramMdl Sch040knParamModel
     * @param scdMdl スケジュール情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setSch040knFormFromDb(
            Sch040knParamModel paramMdl,
            ScheduleSearchModel scdMdl,
            Connection con) throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);

        //開始日時
        UDate frDate = scdMdl.getScdFrDate();
        StringBuilder frBuf = new StringBuilder();
        frBuf.append(
                gsMsg.getMessage("cmn.date4",
                                            new String[] {
                                            String.valueOf(frDate.getYear()),
                                            String.valueOf(frDate.getMonth()),
                                            String.valueOf(frDate.getIntDay())
                                            }));

        //終了日時
        UDate toDate = scdMdl.getScdToDate();
        StringBuilder toBuf = new StringBuilder();
        toBuf.append(
                gsMsg.getMessage("cmn.date4",
                                            new String[] {
                                            String.valueOf(toDate.getYear()),
                                            String.valueOf(toDate.getMonth()),
                                            String.valueOf(toDate.getIntDay())
                                            }));

        //時間指定無し判定
        if (scdMdl.getScdDaily() == GSConstSchedule.TIME_EXIST) {
            //時
            String[] paramsFr = {String.valueOf(frDate.getIntHour()),
                    StringUtil.toDecFormat(frDate.getIntMinute(), "00")};
            frBuf.append(gsMsg.getMessage("cmn.time.input", paramsFr));

            String[] paramsTo = {String.valueOf(toDate.getIntHour()),
                    StringUtil.toDecFormat(toDate.getIntMinute(), "00")};
            toBuf.append(gsMsg.getMessage("cmn.time.input", paramsTo));
        }
        //開始日時
        paramMdl.setSch040knFromDate(frBuf.toString());
        //終了日時
        paramMdl.setSch040knToDate(toBuf.toString());
        paramMdl.setSch040Bgcolor(String.valueOf(scdMdl.getScdBgcolor()));
        paramMdl.setSch040Title(scdMdl.getScdTitle());
        paramMdl.setSch040Value(scdMdl.getScdValue());
        paramMdl.setSch040Biko(scdMdl.getScdBiko());
        paramMdl.setSch040Public(String.valueOf(scdMdl.getScdPublic()));
        paramMdl.setSch040Edit(String.valueOf(scdMdl.getScdEdit()));
        //同時登録者
        ArrayList<CmnUsrmInfModel> sameUserList = scdMdl.getUsrInfList();
        paramMdl.setSch040AddedUsrLabel(sameUserList);

        //出欠確認区分
        int attendKbn = scdMdl.getScdAttendKbn();
        paramMdl.setSch040AttendKbn(
                NullDefault.getString(paramMdl.getSch040AttendKbn(),
                        String.valueOf(attendKbn)));

        //出欠回答区分
        paramMdl.setSch040AttendAnsKbn(
                NullDefault.getString(paramMdl.getSch040AttendAnsKbn(),
                        String.valueOf(scdMdl.getScdAttendAns())));

        //出欠登録者区分
        int attendAnsUsrKbn = scdMdl.getScdAttendAuKbn();
        //スケジュール編集画面 表示モード
        Sch040Biz biz = new Sch040Biz(con, reqMdl__);
        int editDspMode = biz.getEditDspMode(attendKbn, attendAnsUsrKbn);
        paramMdl.setSch040EditDspMode(String.valueOf(editDspMode));

        if (editDspMode != GSConstSchedule.EDIT_DSP_MODE_NORMAL) {
            //出欠確認回答一覧
            ArrayList<Sch040AttendModel> ansList =
                    biz.getAttendAnsList(scdMdl.getScdGrpSid());
            //出欠確認回答一覧 全て表示リンク 表示フラグ
            if (ansList.size() > GSConstSchedule.ATTEND_LIST_MAX_NUM) {
                paramMdl.setSch040AttendLinkFlg(1);
                paramMdl.setSch040AttendAnsList(
                        ansList.subList(0, GSConstSchedule.ATTEND_LIST_MAX_NUM));
            } else {
                paramMdl.setSch040AttendLinkFlg(0);
                paramMdl.setSch040AttendAnsList(ansList);

            }
        }
    }

    /**
     * <br>スケジュールを新規登録します
     * @param reqMdl リクエスト情報
     * @param paramMdl Sch040knParamModel
     * @param userSid 登録者SID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @throws Exception SQL実行時例外
     */
    public void insertScheduleDate(
            RequestModel reqMdl,
            Sch040knParamModel paramMdl,
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            boolean smailPluginUseFlg) throws Exception {

        SchCommonBiz cmnBiz = new SchCommonBiz(reqMdl);
        SchDataModel schMdl = null;
        //登録モデルを作成
        schMdl = new SchDataModel();
        UDate frDate = new UDate();
        UDate toDate = frDate.cloneUDate();

        int frYear = Integer.parseInt(paramMdl.getSch040FrYear());
        int frMonth = Integer.parseInt(paramMdl.getSch040FrMonth());
        int frDay = Integer.parseInt(paramMdl.getSch040FrDay());

        int frHour = GSConstSchedule.DAY_START_HOUR;
        int frMin = GSConstSchedule.DAY_START_MINUTES;
        int toHour = GSConstSchedule.DAY_END_HOUR;
        int toMin = GSConstSchedule.DAY_END_MINUTES;
        if (paramMdl.getSch040TimeKbn() == null) {
            frHour = Integer.parseInt(paramMdl.getSch040FrHour());
            frMin = Integer.parseInt(paramMdl.getSch040FrMin());
            toHour = Integer.parseInt(paramMdl.getSch040ToHour());
            toMin = Integer.parseInt(paramMdl.getSch040ToMin());
        }

        frDate.setDate(frYear, frMonth, frDay);
        boolean frExit = false;
        if (frHour != -1 && frMin != -1) {
            frDate.setHour(frHour);
            frDate.setMinute(frMin);
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            frExit = true;
        } else {
            frDate.setHour(GSConstSchedule.DAY_START_HOUR);
            frDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        }


        int toYear = Integer.parseInt(paramMdl.getSch040ToYear());
        int toMonth = Integer.parseInt(paramMdl.getSch040ToMonth());
        int toDay = Integer.parseInt(paramMdl.getSch040ToDay());

        toDate.setDate(toYear, toMonth, toDay);
        boolean toExit = false;
        if (toHour != -1 && toMin != -1) {
            toDate.setHour(toHour);
            toDate.setMinute(toMin);
            toDate.setSecond(GSConstSchedule.DAY_END_SECOND);
            toExit = true;
        } else {
            toDate.setHour(GSConstSchedule.DAY_END_HOUR);
            toDate.setMinute(GSConstSchedule.DAY_END_MINUTES);
            toDate.setSecond(GSConstSchedule.DAY_END_SECOND);
        }
        if (!frExit && !toExit) {
            frDate.setHour(GSConstSchedule.DAY_START_HOUR);
            frDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            toDate.setHour(GSConstSchedule.DAY_START_HOUR);
            toDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
            toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        }
        //時間指定
        if (paramMdl.getSch040TimeKbn() == null) {
            schMdl.setScdDaily(GSConstSchedule.TIME_EXIST);
        } else {
            schMdl.setScdDaily(GSConstSchedule.TIME_NOT_EXIST);
        }
        schMdl.setScdFrDate(frDate);
        schMdl.setScdToDate(toDate);
        schMdl.setScdBgcolor(
                NullDefault.getInt(paramMdl.getSch040Bgcolor(), GSConstSchedule.DF_BG_COLOR));
        schMdl.setScdTitle(paramMdl.getSch040Title());
        schMdl.setScdValue(paramMdl.getSch040Value());
        schMdl.setScdBiko(paramMdl.getSch040Biko());
        schMdl.setScdPublic(
                NullDefault.getInt(paramMdl.getSch040Public(), GSConstSchedule.DSP_PUBLIC));
        schMdl.setScdAuid(userSid);
        schMdl.setScdAdate(new UDate());
        schMdl.setScdEuid(userSid);
        schMdl.setScdEdate(new UDate());

        int scdSid = -1;
        int scdGpSid = -1;
        //SID採番
        scdSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                SaibanModel.SBNSID_SUB_SCH, userSid);
        schMdl.setScdSid(scdSid);
        schMdl.setScdUsrSid(Integer.parseInt(paramMdl.getSch010SelectUsrSid()));
        schMdl.setScdUsrKbn(Integer.parseInt(paramMdl.getSch010SelectUsrKbn()));


        SchDataDao schDao = new SchDataDao(con__);
        String[] svUsers = paramMdl.getSv_users();
        int addUserSid = -1;
        //スケジュールグルプSID（同時登録有りの場合）
        if (svUsers != null && svUsers.length > 0) {
            //スケジュールグルプSID（同時登録有りの場合）
            scdGpSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH_GP, userSid);
        }
        schMdl.setScdGrpSid(scdGpSid);
        //登録
        schDao.insert(schMdl);

        //ユーザSID
        String usrSid = paramMdl.getSch010SelectUsrSid();
        //URL取得
        String url = __createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                                           String.valueOf(scdSid), usrSid,
                                           paramMdl);

        cmnBiz.sendPlgSmail(
                con__, cntCon__, schMdl, appRootPath, plconf, smailPluginUseFlg, url);
        //同時登録分
        if (svUsers != null) {
            for (int i = 0; i < svUsers.length; i++) {
                scdSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                        SaibanModel.SBNSID_SUB_SCH, userSid);
                addUserSid = Integer.parseInt(svUsers[i]);

                schMdl.setScdSid(scdSid);
                schMdl.setScdUsrSid(addUserSid);
                schDao.insert(schMdl);
                cmnBiz.sendPlgSmail(con__, cntCon__, schMdl, appRootPath,
                        plconf, smailPluginUseFlg, url);
            }
        }

    }

    /**
     * <br>スケジュールを更新します
     * @param paramMdl Sch040knParamModel
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param con コネクション
     * @return 更新件数
     * @throws Exception SQL実行時例外
     */
    public int updateScheduleDate(
            Sch040knParamModel paramMdl,
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            Connection con) throws Exception {

        int cnt = 0;
        String scdSid = paramMdl.getSch010SchSid();
        SchDataModel scdMdl = new SchDataModel();

        UDate frDate = new UDate();
        frDate.setDate(
                Integer.parseInt(paramMdl.getSch040FrYear()),
                Integer.parseInt(paramMdl.getSch040FrMonth()),
                Integer.parseInt(paramMdl.getSch040FrDay())
                );
        frDate.setHour(Integer.parseInt(paramMdl.getSch040FrHour()));
        frDate.setMinute(Integer.parseInt(paramMdl.getSch040FrMin()));

        int frHour = GSConstSchedule.DAY_START_HOUR;
        int frMin = GSConstSchedule.DAY_START_MINUTES;
        int toHour = GSConstSchedule.DAY_END_HOUR;
        int toMin = GSConstSchedule.DAY_END_MINUTES;
        if (paramMdl.getSch040TimeKbn() == null) {
            frHour = Integer.parseInt(paramMdl.getSch040FrHour());
            frMin = Integer.parseInt(paramMdl.getSch040FrMin());
            toHour = Integer.parseInt(paramMdl.getSch040ToHour());
            toMin = Integer.parseInt(paramMdl.getSch040ToMin());
        }

        boolean frExit = false;
        if (frHour != -1 && frMin != -1) {
            frDate.setHour(frHour);
            frDate.setMinute(frMin);
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            frExit = true;
        } else {
            frDate.setHour(GSConstSchedule.DAY_START_HOUR);
            frDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        }

        UDate toDate = new UDate();
        toDate.setDate(
                Integer.parseInt(paramMdl.getSch040ToYear()),
                Integer.parseInt(paramMdl.getSch040ToMonth()),
                Integer.parseInt(paramMdl.getSch040ToDay())
                );

        boolean toExit = false;
        if (toHour != -1 && toMin != -1) {
            toDate.setHour(toHour);
            toDate.setMinute(toMin);
            toDate.setSecond(GSConstSchedule.DAY_END_SECOND);
            toExit = true;
        } else {
            toDate.setHour(GSConstSchedule.DAY_END_HOUR);
            toDate.setMinute(GSConstSchedule.DAY_END_MINUTES);
            toDate.setSecond(GSConstSchedule.DAY_END_SECOND);
        }
        if (!frExit && !toExit) {
            frDate.setHour(GSConstSchedule.DAY_START_HOUR);
            frDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            toDate.setHour(GSConstSchedule.DAY_START_HOUR);
            toDate.setMinute(GSConstSchedule.DAY_START_MINUTES);
            toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        }
        //時間指定
        if (paramMdl.getSch040TimeKbn() == null) {
            scdMdl.setScdDaily(GSConstSchedule.TIME_EXIST);
        } else {
            scdMdl.setScdDaily(GSConstSchedule.TIME_NOT_EXIST);
        }
        scdMdl.setScdSid(Integer.parseInt(scdSid));
        scdMdl.setScdFrDate(frDate);
        scdMdl.setScdToDate(toDate);
        scdMdl.setScdBgcolor(Integer.parseInt(paramMdl.getSch040Bgcolor()));
        scdMdl.setScdTitle(paramMdl.getSch040Title());
        scdMdl.setScdValue(paramMdl.getSch040Value());
        scdMdl.setScdBiko(paramMdl.getSch040Biko());
        scdMdl.setScdPublic(Integer.parseInt(paramMdl.getSch040Public()));
        scdMdl.setScdEuid(userSid);
        scdMdl.setScdEdate(new UDate());
        SchDataDao schDao = new SchDataDao(con);
        cnt = schDao.updateSchedule(scdMdl);
        return cnt;
    }

    /**
     * <br>[機  能] スケジュール一般登録確認URLを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param cmd 処理モード
     * @param sch010SchSid スケジュールSID
     * @param usrSid ユーザーSID
     * @param paramMdl フォーム
     * @return スケジュール一般登録確認URL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    private String __createScheduleUrlDefo(String cmd,
                                          String sch010SchSid, String usrSid,
                                          Sch040ParamModel paramMdl)
    throws UnsupportedEncodingException {
        String scheduleUrl = null;

        //スレッドのURLを作成
        String schUrl = reqMdl__.getReferer();
        if (!StringUtil.isNullZeroString(schUrl)) {
            scheduleUrl = schUrl.substring(0, schUrl.lastIndexOf("/"));
            scheduleUrl = scheduleUrl.substring(0, scheduleUrl.lastIndexOf("/"));
            scheduleUrl += "/common/cmn001.do?url=";

            String paramUrl = reqMdl__.getRequestURI();
            paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("/"));

            String domain = "";
            String reqDomain = NullDefault.getString(reqMdl__.getDomain(), "");
            if (!reqDomain.equals(GSConst.GS_DOMAIN)) {
                domain = reqDomain + "/";
                paramUrl = paramUrl.replace(
                 GSConstSchedule.PLUGIN_ID_SCHEDULE, domain + GSConstSchedule.PLUGIN_ID_SCHEDULE);
            }

            paramUrl += "/sch040.do";
            paramUrl += "?sch010SelectDate=" + paramMdl.getSch010SelectDate();
            paramUrl += "&cmd=" + cmd;
            paramUrl += "&sch010SchSid=" + sch010SchSid;
            paramUrl += "&sch010SelectUsrSid=" + usrSid;
            paramUrl += "&sch010SelectUsrKbn=" + paramMdl.getSch010SelectUsrKbn();
            paramUrl += "&sch010DspDate=" + paramMdl.getSch010DspDate();
            paramUrl += "&dspMod=" + paramMdl.getDspMod();
            paramUrl += "&sch010DspGpSid=" + paramMdl.getSch010DspGpSid();
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

            scheduleUrl += paramUrl;
        }

        return scheduleUrl;
    }
}
