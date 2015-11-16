package jp.groupsession.v2.rsv.rsv110kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.SaibanModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.biz.RsvScheduleBiz;
import jp.groupsession.v2.rsv.dao.RsvScdOperationDao;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.dao.RsvSisKyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.ReserveSmlModel;
import jp.groupsession.v2.rsv.model.RsvScdOperationModel;
import jp.groupsession.v2.rsv.model.RsvSisKyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisYrkModel;
import jp.groupsession.v2.rsv.rsv070.Rsv070Model;
import jp.groupsession.v2.rsv.rsv110.Rsv110Biz;
import jp.groupsession.v2.rsv.rsv110.Rsv110ParamModel;
import jp.groupsession.v2.rsv.rsv110.Rsv110SisetuModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 施設予約登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv110knBiz extends AbstractReserveBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv110knBiz.class);
    /** リクエスト */
    protected RequestModel reqMdl_ = null;
    /** コネクション */
    protected Connection con_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param con コネクション
     */
    public Rsv110knBiz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 画面表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv110knParamModel
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Rsv110knParamModel paramMdl) throws SQLException {

        String procMode = paramMdl.getRsv110ProcMode();
        int rsdSid = -1;

        //新規モード
        if (procMode.equals(GSConstReserve.PROC_MODE_SINKI)) {

            log__.debug("新規モード");

            //登録者名をセット
            BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
            paramMdl.setRsv110Torokusya(usrMdl.getUsisei() + "  " + usrMdl.getUsimei());

            rsdSid = paramMdl.getRsv110RsdSid();

        //編集モード or 複写して登録モード
        } else if (procMode.equals(GSConstReserve.PROC_MODE_EDIT)
                || procMode.equals(GSConstReserve.PROC_MODE_COPY_ADD)) {

            log__.debug("編集モード or 複写して登録モード");

            //予約情報取得
            Rsv110SisetuModel yrkMdl = __getYoyakuData(paramMdl);

            if (yrkMdl != null) {
                //複写して登録
                if (procMode.equals(GSConstReserve.PROC_MODE_COPY_ADD)) {
                    //登録者名をセット
                    BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
                    paramMdl.setRsv110Torokusya(usrMdl.getUsisei() + "  " + usrMdl.getUsimei());

                //編集
                } else {
                    //登録者名
                    paramMdl.setRsv110Torokusya(
                            NullDefault.getString(yrkMdl.getUsiSei(), "")
                            + "  "
                            + NullDefault.getString(yrkMdl.getUsiMei(), ""));
                }

                rsdSid = yrkMdl.getRsdSid();
            }
        }

        //施設グループ情報を取得
        Rsv070Model grpMdl = __getGroupData(rsdSid);
        if (grpMdl != null) {
            int rskSid = grpMdl.getRskSid();

            //施設区分毎に入力可能な項目を設定
            __setSisetuHeader(paramMdl, rskSid);

            //施設グループ情報セット
            __setGroupData(paramMdl, grpMdl);
        }

        //予約開始
        paramMdl.setYoyakuFrString(__convertUdateToYmdhm(paramMdl, 1));
        //予約終了
        paramMdl.setYoyakuToString(__convertUdateToYmdhm(paramMdl, 2));
        //内容
        paramMdl.setRsv110knNaiyo(StringUtilHtml.transToHTmlPlusAmparsant(
                NullDefault.getString(paramMdl.getRsv110Naiyo(), "")));

        //スケジュールを登録するユーザがいる場合、登録するユーザの名称をセット
        Rsv110Biz biz110 = new Rsv110Biz(reqMdl_, con_);
        biz110.setUserName(paramMdl, paramMdl.getRsv110SchKbn(),
                        paramMdl.getSv_users(), paramMdl.getRsv110SchGroupSid());
        biz110 = null;

    }

    /**
     * <br>[機  能] 入力内容をDBに反映する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv110knParamModel
     * @param ctrl 採番用コネクション
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @return 施設予約SID, 施設SID
     * @throws SQLException SQL実行時例外
     */
    public int[] updateYoyakuData(
            Rsv110knParamModel paramMdl, MlCountMtController ctrl, int userSid, String appRootPath)
        throws SQLException {

        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int usrSid = usrMdl.getUsrsid();
        int yoyakuSid = -1;
        int sisetuSid = -1;
        UDate now = new UDate();

        //予約開始
        UDate frDate = new UDate();
        frDate.setDate(paramMdl.getRsv110SelectedYearFr(),
                paramMdl.getRsv110SelectedMonthFr(),
                paramMdl.getRsv110SelectedDayFr());
        frDate.setHour(paramMdl.getRsv110SelectedHourFr());
        frDate.setMinute(paramMdl.getRsv110SelectedMinuteFr());
        frDate.setSecond(GSConstReserve.DAY_START_SECOND);
        frDate.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

        //予約終了
        UDate toDate = new UDate();
        toDate.setDate(paramMdl.getRsv110SelectedYearTo(),
                paramMdl.getRsv110SelectedMonthTo(),
                paramMdl.getRsv110SelectedDayTo());
        toDate.setHour(paramMdl.getRsv110SelectedHourTo());
        toDate.setMinute(paramMdl.getRsv110SelectedMinuteTo());
        toDate.setSecond(GSConstReserve.DAY_START_SECOND);
        toDate.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);

        int rsvSchGrpSid = NullDefault.getInt(paramMdl.getRsv110SchGroupSid(), -1);
        String[] users = paramMdl.getSv_users();

        //新規 or 複写して登録モード
        if (paramMdl.getRsv110ProcMode().equals(GSConstReserve.PROC_MODE_SINKI)
                || paramMdl.getRsv110ProcMode().equals(GSConstReserve.PROC_MODE_COPY_ADD)) {

            log__.debug("新規モード or 複写して登録モード");

            //予約SID採番
            yoyakuSid =
                (int) ctrl.getSaibanNumber(
                        GSConstReserve.SBNSID_RESERVE,
                        GSConstReserve.SBNSID_SUB_YOYAKU,
                        usrSid);

            boolean schInsertFlg = (paramMdl.getRsv110SchKbn() == GSConstReserve.RSV_SCHKBN_USER
                                        && users != null && users.length > 0)
                                || (paramMdl.getRsv110SchKbn() == GSConstReserve.RSV_SCHKBN_GROUP
                                        && rsvSchGrpSid >= 0);
            int rsSid = -1;
            if (schInsertFlg) {
                //リレーションSID採番
                rsSid =
                    (int) ctrl.getSaibanNumber(
                            SaibanModel.SBNSID_SCHEDULE,
                            SaibanModel.SBNSID_SUB_SCH_RES,
                            usrSid);
            }

            //施設SID
            sisetuSid = paramMdl.getRsv110RsdSid();

            RsvSisYrkModel yrkParam = new RsvSisYrkModel();
            yrkParam.setRsySid(yoyakuSid);
            yrkParam.setRsdSid(sisetuSid);
            yrkParam.setRsyMok(NullDefault.getString(paramMdl.getRsv110Mokuteki(), ""));
            yrkParam.setRsyFrDate(frDate);
            yrkParam.setRsyToDate(toDate);
            yrkParam.setRsyBiko(NullDefault.getString(paramMdl.getRsv110Naiyo(), ""));
            yrkParam.setRsyAuid(usrSid);
            yrkParam.setRsyAdate(now);
            yrkParam.setRsyEuid(usrSid);
            yrkParam.setRsyEdate(now);
            yrkParam.setRsyEdit(paramMdl.getRsv110RsyEdit());
            yrkParam.setScdRsSid(rsSid);

            //承認状況
            RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
            rsvCmnBiz.setSisYrkApprData(con_, sisetuSid, yrkParam, userSid);

            yrkDao.insertNewYoyaku(yrkParam);

            //施設予約区分別情報登録
            if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
                RsvSisKyrkModel kyrkMdl = new RsvSisKyrkModel();
                kyrkMdl.setRsySid(yoyakuSid);
                kyrkMdl.setRkyBusyo(paramMdl.getRsv110Busyo());
                kyrkMdl.setRkyName(paramMdl.getRsv110UseName());
                kyrkMdl.setRkyNum(paramMdl.getRsv110UseNum());
                kyrkMdl.setRkyUseKbn(paramMdl.getRsv110UseKbn());
                kyrkMdl.setRkyContact(paramMdl.getRsv110Contact());
                kyrkMdl.setRkyGuide(paramMdl.getRsv110Guide());
                kyrkMdl.setRkyParkNum(paramMdl.getRsv110ParkNum());
                if (RsvCommonBiz.isUsePrintKbn(appRootPath)) {
                    kyrkMdl.setRkyPrintKbn(paramMdl.getRsv110PrintKbn());
                }
                kyrkMdl.setRkyDest(paramMdl.getRsv110Dest());
                kyrkMdl.setRkyAuid(usrSid);
                kyrkMdl.setRkyAdate(now);
                kyrkMdl.setRkyEuid(usrSid);
                kyrkMdl.setRkyEdate(now);

                RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);
                kyrkDao.insert(kyrkMdl);

            }

            //スケジュールの登録
            if (schInsertFlg) {
                //スケジュールグループSID
                int scdGpSid = -1;
                if (paramMdl.getRsv110SchKbn() == GSConstReserve.RSV_SCHKBN_USER
                && users.length > 1) {
                    scdGpSid = (int) ctrl.getSaibanNumber(
                                SaibanModel.SBNSID_SCHEDULE,
                                SaibanModel.SBNSID_SUB_SCH_GP,
                                usrSid);
                }

                RsvScdOperationModel schMdl
                    = __createSchData(frDate, toDate, paramMdl, usrSid, now, scdGpSid, rsSid);
                RsvScdOperationDao opDao = new RsvScdOperationDao(con_);

                if (paramMdl.getRsv110SchKbn() == GSConstReserve.RSV_SCHKBN_GROUP) {
                    //グループスケジュールの登録
                    int scdSid =
                        (int) ctrl.getSaibanNumber(
                                SaibanModel.SBNSID_SCHEDULE,
                                SaibanModel.SBNSID_SUB_SCH,
                                usrSid);

                    schMdl.setScdSid(scdSid);
                    schMdl.setScdUsrSid(rsvSchGrpSid);
                    schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_GROUP);
                    opDao.insertSchData(schMdl);

                } else {
                    //ユーザスケジュールの登録
                    for (String addUser : users) {
                        int scdSid =
                            (int) ctrl.getSaibanNumber(
                                    SaibanModel.SBNSID_SCHEDULE,
                                    SaibanModel.SBNSID_SUB_SCH,
                                    usrSid);

                        int addUserSid = Integer.parseInt(addUser);

                        schMdl.setScdSid(scdSid);
                        schMdl.setScdUsrSid(addUserSid);
                        schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_USER);
                        opDao.insertSchData(schMdl);
                    }
                }
            }

        //編集
        } else if (paramMdl.getRsv110ProcMode().equals(GSConstReserve.PROC_MODE_EDIT)) {

            log__.debug("編集モード");

            //予約SID
            yoyakuSid = paramMdl.getRsv110RsySid();

            //スケジュールへ変更を反映するか？
            boolean editSchedule
                = paramMdl.getRsv110ScdReflection() == GSConstReserve.SCD_REFLECTION_OK;

            int oldRsSid = paramMdl.getRsv110ScdRsSid();
            int rsSid = oldRsSid;
            if (editSchedule && rsSid <= 0 && users != null && users.length > 0) {
                //リレーションSID採番
                rsSid =
                    (int) ctrl.getSaibanNumber(
                            SaibanModel.SBNSID_SCHEDULE,
                            SaibanModel.SBNSID_SUB_SCH_RES,
                            usrSid);
            }

            //施設SID
            Rsv110SisetuModel yrkMdl = __getYoyakuData(paramMdl);

            if (yrkMdl != null) {
                sisetuSid = yrkMdl.getRsdSid();
            }

            RsvSisYrkModel yrkParam = new RsvSisYrkModel();
            yrkParam.setRsySid(yoyakuSid);
            yrkParam.setRsyMok(NullDefault.getString(paramMdl.getRsv110Mokuteki(), ""));
            yrkParam.setRsyFrDate(frDate);
            yrkParam.setRsyToDate(toDate);
            yrkParam.setRsyBiko(NullDefault.getString(paramMdl.getRsv110Naiyo(), ""));
            yrkParam.setRsyAuid(usrSid);
            yrkParam.setRsyEuid(usrSid);
            yrkParam.setRsyEdate(now);
            yrkParam.setRsyEdit(paramMdl.getRsv110RsyEdit());
            yrkParam.setScdRsSid(rsSid);

            //承認状況
            RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
            rsvCmnBiz.setSisYrkApprData(con_, sisetuSid, yrkParam, userSid);

            yrkDao.updateYoyakuData(yrkParam);

            //施設予約区分別情報登録
            if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
                RsvSisKyrkModel kyrkMdl = new RsvSisKyrkModel();
                kyrkMdl.setRsySid(yoyakuSid);
                kyrkMdl.setRkyBusyo(paramMdl.getRsv110Busyo());
                kyrkMdl.setRkyName(paramMdl.getRsv110UseName());
                kyrkMdl.setRkyNum(paramMdl.getRsv110UseNum());
                kyrkMdl.setRkyUseKbn(paramMdl.getRsv110UseKbn());
                kyrkMdl.setRkyContact(paramMdl.getRsv110Contact());
                kyrkMdl.setRkyGuide(paramMdl.getRsv110Guide());
                kyrkMdl.setRkyParkNum(paramMdl.getRsv110ParkNum());
                if (RsvCommonBiz.isUsePrintKbn(appRootPath)) {
                    kyrkMdl.setRkyPrintKbn(paramMdl.getRsv110PrintKbn());
                }
                kyrkMdl.setRkyDest(paramMdl.getRsv110Dest());
                kyrkMdl.setRkyEuid(usrSid);
                kyrkMdl.setRkyEdate(now);

                RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);
                int cnt = kyrkDao.update(kyrkMdl);
                if (cnt == 0) {
                    kyrkMdl.setRkyAuid(usrSid);
                    kyrkMdl.setRkyAdate(now);
                    kyrkDao.insert(kyrkMdl);
                }
            }

            int scdGpSid = -1;
            List<String> addUsers = new ArrayList<String>();
            List<String> delUsers = new ArrayList<String>();

            //スケジュールと関連付いている & 変更を反映
            if (editSchedule) {
                if (paramMdl.getRsv110SchKbn() == GSConstReserve.RSV_SCHKBN_GROUP) {
                    addUsers.add(String.valueOf(rsvSchGrpSid));
                } else {
                    if (users != null && users.length > 0) {
                        for (String usr : users) {
                            addUsers.add(usr);
                        }
                    }
                }

                //既存のスケジュールを編集する
                if (oldRsSid > 0) {
                    RsvScdOperationDao opDao = new RsvScdOperationDao(con_);
                    ArrayList<RsvScdOperationModel> retArray =
                        opDao.selectSchList(oldRsSid);

                    ArrayList<RsvScdOperationModel> updArray =
                        new ArrayList<RsvScdOperationModel>();

                    if (!retArray.isEmpty()) {
                        SchDao schDao = new SchDao(con_);
                        List<Integer> notAccessList = new ArrayList<Integer>();
                        if (retArray.get(0).getScdUsrKbn() == GSConstReserve.RSV_SCHKBN_GROUP) {
                            notAccessList = schDao.getNotRegistGrpList(usrSid);
                        } else {
                            notAccessList = schDao.getNotRegistUserList(usrSid);
                        }
                        ArrayList<RsvScdOperationModel> notAccessDataList =
                                new ArrayList<RsvScdOperationModel>();

                        //既存登録データに対して更新する値を設定
                        for (RsvScdOperationModel mdl : retArray) {
                            //アクセス不可グループ or ユーザ を更新対象から除外する
                            if (notAccessList.indexOf(mdl.getScdUsrSid()) >= 0) {
                                notAccessDataList.add(mdl);
                                continue;
                            }

                            String updateUsrSid = String.valueOf(mdl.getScdUsrSid());
                            if (addUsers.indexOf(updateUsrSid) >= 0) {

                                UDate frUd = new UDate();
                                frUd.setYear(paramMdl.getRsv110SelectedYearFr());
                                frUd.setMonth(paramMdl.getRsv110SelectedMonthFr());
                                frUd.setDay(paramMdl.getRsv110SelectedDayFr());
                                frUd.setHour(paramMdl.getRsv110SelectedHourFr());
                                frUd.setMinute(paramMdl.getRsv110SelectedMinuteFr());
                                frUd.setSecond(GSConstReserve.DAY_START_SECOND);
                                frUd.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

                                UDate toUd = new UDate();
                                toUd.setYear(paramMdl.getRsv110SelectedYearTo());
                                toUd.setMonth(paramMdl.getRsv110SelectedMonthTo());
                                toUd.setDay(paramMdl.getRsv110SelectedDayTo());
                                toUd.setHour(paramMdl.getRsv110SelectedHourTo());
                                toUd.setMinute(paramMdl.getRsv110SelectedMinuteTo());
                                toUd.setSecond(GSConstReserve.DAY_START_SECOND);
                                toUd.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

                                mdl.setScdFrDate(frUd);
                                mdl.setScdToDate(toUd);
                                mdl.setScdTitle(
                                        NullDefault.getString(paramMdl.getRsv110Mokuteki(), ""));
                                mdl.setScdValue(
                                        NullDefault.getString(paramMdl.getRsv110Naiyo(), ""));

                                scdGpSid = mdl.getScdGrpSid();
                                if (scdGpSid <= 0 && users != null && users.length > 1) {
                                    scdGpSid =
                                        (int) ctrl.getSaibanNumber(
                                                SaibanModel.SBNSID_SCHEDULE,
                                                SaibanModel.SBNSID_SUB_SCH_GP,
                                                usrSid);
                                }
                                mdl.setScdGrpSid(scdGpSid);

                                mdl.setScdEuid(usrSid);
                                mdl.setScdEdate(now);
                                updArray.add(mdl);

                                addUsers.remove(addUsers.indexOf(updateUsrSid));
                            } else {
                                delUsers.add(updateUsrSid);
                            }
                        }

                        //更新
                        opDao.updateRsvToScdTi(updArray, scdGpSid);

                        //削除
                        if (!delUsers.isEmpty()) {
                            opDao.deleteScdTiWithUsers(
                                    oldRsSid, delUsers.toArray(new String[delUsers.size()]));
                        }
                    }
                }

                //追加されたスケジュールを登録する
                if (!addUsers.isEmpty()) {
                    //スケジュールグループSID
                    if (scdGpSid <= 0) {
                        scdGpSid =
                            (int) ctrl.getSaibanNumber(
                                    SaibanModel.SBNSID_SCHEDULE,
                                    SaibanModel.SBNSID_SUB_SCH_GP,
                                    usrSid);
                    }

                    RsvScdOperationModel schMdl
                        = __createSchData(frDate, toDate, paramMdl, usrSid, now, scdGpSid, rsSid);
                    RsvScdOperationDao opDao = new RsvScdOperationDao(con_);

                    if (paramMdl.getRsv110SchKbn() == GSConstReserve.RSV_SCHKBN_GROUP) {
                        //グループスケジュールの登録
                        int scdSid =
                            (int) ctrl.getSaibanNumber(
                                    SaibanModel.SBNSID_SCHEDULE,
                                    SaibanModel.SBNSID_SUB_SCH,
                                    usrSid);

                        schMdl.setScdSid(scdSid);
                        schMdl.setScdUsrSid(rsvSchGrpSid);
                        schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_GROUP);
                        opDao.insertSchData(schMdl);

                    } else {
                        //ユーザスケジュールの登録
                        for (String addUser : addUsers) {
                            int scdSid =
                                (int) ctrl.getSaibanNumber(
                                        SaibanModel.SBNSID_SCHEDULE,
                                        SaibanModel.SBNSID_SUB_SCH,
                                        usrSid);

                            int addUserSid = Integer.parseInt(addUser);

                            schMdl.setScdSid(scdSid);
                            schMdl.setScdUsrSid(addUserSid);
                            schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_USER);
                            opDao.insertSchData(schMdl);
                        }
                    }
                }
            }
        }

        return new int[]{yoyakuSid, sisetuSid};
    }

    /**
     * <br>[機  能] 施設グループ情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param rsdSid 施設SID
     * @return ret 取得結果
     * @throws SQLException SQL実行時例外
     */
    private Rsv070Model __getGroupData(int rsdSid) throws SQLException {

        RsvSisDataDao dataDao = new RsvSisDataDao(con_);
        Rsv070Model ret = dataDao.getPopUpSisetuData(rsdSid);

        return ret;
    }

    /**
     * <br>[機  能] 予約情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv110knParamModel
     * @return ret 予約情報
     * @throws SQLException SQL実行時例外
     */
    private Rsv110SisetuModel __getYoyakuData(Rsv110knParamModel paramMdl) throws SQLException {
        return __getYoyakuData(paramMdl.getRsv110RsySid());
    }

    /**
     * <br>[機  能] 予約情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param yoyakuSid 施設予約SID
     * @return ret 予約情報
     * @throws SQLException SQL実行時例外
     */
    private Rsv110SisetuModel __getYoyakuData(int yoyakuSid) throws SQLException {

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        Rsv110SisetuModel ret = yrkDao.selectYoyakuEditData(yoyakuSid);

        return ret;
    }

    /**
     * <br>[機  能] DBから取得した施設グループ情報をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv110knParamModel
     * @param dbMdl DB取得結果
     */
    private void __setGroupData(Rsv110knParamModel paramMdl,
                                  Rsv070Model dbMdl) {

        //所属グループ名
        paramMdl.setRsv110GrpName(NullDefault.getString(dbMdl.getRsgName(), ""));
        //施設区分名称 */
        paramMdl.setRsv110SisetuKbnName(NullDefault.getString(dbMdl.getRskName(), ""));
        //施設名称
        paramMdl.setRsv110SisetuName(NullDefault.getString(dbMdl.getRsdName(), ""));
        //資産管理番号
        paramMdl.setRsv110SisanKanri(NullDefault.getString(dbMdl.getRsdSnum(), ""));
        //可変項目1
        paramMdl.setRsv110Prop1Value(NullDefault.getString(dbMdl.getRsdProp1Value(), ""));
        //可変項目2
        paramMdl.setRsv110Prop2Value(
                NullDefault.getStringZeroLength(
                        dbMdl.getRsdProp2Value(),
                        String.valueOf(GSConstReserve.PROP_KBN_KA)));
        //可変項目3
        paramMdl.setRsv110Prop3Value(
                NullDefault.getStringZeroLength(
                        dbMdl.getRsdProp3Value(),
                        String.valueOf(GSConstReserve.PROP_KBN_KA)));
        //可変項目4
        paramMdl.setRsv110Prop4Value(NullDefault.getString(dbMdl.getRsdProp4Value(), ""));
        //可変項目5
        paramMdl.setRsv110Prop5Value(NullDefault.getString(dbMdl.getRsdProp5Value(), ""));
        //可変項目6
        paramMdl.setRsv110Prop6Value(NullDefault.getString(dbMdl.getRsdProp6Value(), ""));
        //可変項目7
        paramMdl.setRsv110Prop7Value(
                NullDefault.getStringZeroLength(
                        dbMdl.getRsdProp7Value(),
                        String.valueOf(GSConstReserve.PROP_KBN_KA)));
        //備考
        paramMdl.setRsv110Biko(
                StringUtilHtml.transToHTmlPlusAmparsant(
                        NullDefault.getString(dbMdl.getRsdBiko(), "")));
    }

    /**
     * <br>[機  能] 施設区分に応じて施設のヘッダ文字列をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv110knParamModel
     * @param rskSid 施設区分SID
     */
    private void __setSisetuHeader(Rsv110ParamModel paramMdl, int rskSid) {
        GsMessage gsMsg = new GsMessage(reqMdl_);
        switch (rskSid) {
            //部屋
            case GSConstReserve.RSK_KBN_HEYA:
                paramMdl.setRsv110PropHeaderName1(gsMsg.getMessage("reserve.128"));
                paramMdl.setRsv110PropHeaderName2(gsMsg.getMessage("reserve.132"));
                paramMdl.setRsv110PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv110PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            //物品
            case GSConstReserve.RSK_KBN_BUPPIN:
                paramMdl.setRsv110PropHeaderName1(gsMsg.getMessage("reserve.130"));
                paramMdl.setRsv110PropHeaderName3(gsMsg.getMessage("reserve.133"));
                paramMdl.setRsv110PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv110PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            //車
            case GSConstReserve.RSK_KBN_CAR:
                paramMdl.setRsv110PropHeaderName1(gsMsg.getMessage("reserve.129"));
                paramMdl.setRsv110PropHeaderName2(gsMsg.getMessage("reserve.132"));
                paramMdl.setRsv110PropHeaderName4(gsMsg.getMessage("reserve.134"));
                paramMdl.setRsv110PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv110PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            //書籍
            case GSConstReserve.RSK_KBN_BOOK:
                paramMdl.setRsv110PropHeaderName1(gsMsg.getMessage("reserve.131"));
                paramMdl.setRsv110PropHeaderName3(gsMsg.getMessage("reserve.133"));
                paramMdl.setRsv110PropHeaderName5(GSConstReserve.RSK_TEXT_ISBN);
                paramMdl.setRsv110PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv110PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            //その他
            case GSConstReserve.RSK_KBN_OTHER:
                paramMdl.setRsv110PropHeaderName6(gsMsg.getMessage("reserve.135"));
                paramMdl.setRsv110PropHeaderName7(gsMsg.getMessage("reserve.136"));
                break;
            default:
                break;
        }
    }

    /**
     * <br>[機  能] UDateをyyyy年 mm月 dd日 hh時 MM分 に変換する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv110knParamModel
     * @param convKbn 1:from 2:to
     * @return convDateString 変換後
     */
    private String __convertUdateToYmdhm(Rsv110knParamModel paramMdl, int convKbn) {

        String convDateString = "";
        StringBuilder convBuf = new StringBuilder();

        int intYear = -1;
        int intMonth = -1;
        int intDay = -1;
        int intHour = -1;
        int intMinute = -1;

        if (convKbn == 1) {
            intYear = paramMdl.getRsv110SelectedYearFr();
            intMonth = paramMdl.getRsv110SelectedMonthFr();
            intDay = paramMdl.getRsv110SelectedDayFr();
            intHour = paramMdl.getRsv110SelectedHourFr();
            intMinute = paramMdl.getRsv110SelectedMinuteFr();
        } else if (convKbn == 2) {
            intYear = paramMdl.getRsv110SelectedYearTo();
            intMonth = paramMdl.getRsv110SelectedMonthTo();
            intDay = paramMdl.getRsv110SelectedDayTo();
            intHour = paramMdl.getRsv110SelectedHourTo();
            intMinute = paramMdl.getRsv110SelectedMinuteTo();
        }

        UDate convUd = new UDate();
        convUd.setDate(intYear, intMonth, intDay);
        convUd.setHour(intHour);
        convUd.setMinute(intMinute);

        convBuf.append(UDateUtil.getSlashYYMD(convUd));
        convBuf.append("    ");
        convBuf.append(UDateUtil.getSeparateHM(convUd));

        convDateString = convBuf.toString();

        return convDateString;
    }

    /**
     * <br>[機  能] 登録モデルを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param frDate 開始日時
     * @param toDate 終了日時
     * @param paramMdl Rsv110knParamModel
     * @param usrSid セッションユーザSID
     * @param now 現在日時
     * @param scdGpSid スケジュールグループSID
     * @param rsSid スケジュール予約SID
     * @return 登録モデル
     */
    private RsvScdOperationModel __createSchData(
            UDate frDate, UDate toDate, Rsv110knParamModel paramMdl,
                                            int usrSid, UDate now,
                                            int scdGpSid, int rsSid) {
        //
        RsvScdOperationModel schMdl = new RsvScdOperationModel();
        schMdl.setScdDaily(GSConstSchedule.TIME_EXIST);
        schMdl.setScdFrDate(frDate);
        schMdl.setScdToDate(toDate);
        schMdl.setScdBgcolor(GSConstSchedule.DF_BG_COLOR);
        schMdl.setScdTitle(NullDefault.getString(paramMdl.getRsv110Mokuteki(), ""));
        schMdl.setScdValue(NullDefault.getString(paramMdl.getRsv110Naiyo(), ""));
        schMdl.setScdBiko("");
        schMdl.setScdPublic(GSConstSchedule.DSP_PUBLIC);
        schMdl.setScdAuid(usrSid);
        schMdl.setScdAdate(now);
        schMdl.setScdEuid(usrSid);
        schMdl.setScdEdate(now);
        schMdl.setScdEdit(RsvScheduleBiz.getScdEditKbn(paramMdl.getRsv110RsyEdit()));

        //拡張登録SID
        schMdl.setSceSid(-1);

        schMdl.setScdGrpSid(scdGpSid);
        schMdl.setScdRsSid(rsSid);

        return schMdl;
    }

    /**
     * <br>[機  能] ショートメールで通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv110knParamModel
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig PluginConfig
     * @param entryUserSid 登録ユーザSID
     * @throws Exception 実行例外
     */
    public void sendSmail(
        Rsv110knParamModel paramMdl,
        MlCountMtController cntCon,
        int userSid,
        String appRootPath,
        String tempDir,
        PluginConfig pluginConfig,
        int entryUserSid) throws Exception {

        //施設予約表示モデル(ショートメール送信用)
        ReserveSmlModel rsvModel = new ReserveSmlModel();

        UDate now = new UDate();
        String strNow = now.getDateString();
        Rsv110SisetuModel model = __getYoyakuData(paramMdl);
        rsvModel.setRsvSid(model.getRsdSid());
        rsvModel.setRsvMokuteki(model.getRsyMok());
        rsvModel.setRsvNaiyou(model.getRsyBiko());
        rsvModel.setUserSid(model.getRsyAuid());
        rsvModel.setRsvFrDate(model.getRsyFrDate());
        rsvModel.setRsvToDate(model.getRsyToDate());
        rsvModel.setRsvAdate(model.getRsyEdate());
        RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
        rsvModel.setRsvUrl(rsvCmnBiz.createReserveUrl(reqMdl_,
                                            paramMdl.getRsv110RsySid(),
                                            Integer.parseInt(GSConstReserve.PROC_MODE_EDIT),
                                            strNow));
        rsvModel.setEditModeFlg(0);
        //送信
        sendSmail(con_, cntCon, rsvModel, appRootPath, pluginConfig, reqMdl_, entryUserSid);

    }

    /**
     * <br>[機  能] 予約情報登録者ユーザSID取得。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv110knParamModel
     * @return int ユーザSID
     * @throws Exception 実行例外
     */
    public int getEntryUserSid(
        Rsv110knParamModel paramMdl) throws Exception {

        int entryUserSid = -1;
        Rsv110SisetuModel model = __getYoyakuData(paramMdl);

        entryUserSid = model.getRsyAuid();
        return entryUserSid;
    }

    /**
     * <br>[機  能]オペレーションログ出力用予約登録・編集内容を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param sisetsuSid 施設SID
     * @param paramMdl パラメータ格納モデル
     * @return オペレーションログ表示内容
     * @throws SQLException SQL実行エラー
     */
    public String getOpLog(int sisetsuSid, Rsv110knParamModel paramMdl)
            throws SQLException {
        //ログ出力準備
        GsMessage gsMsg = new GsMessage(reqMdl_);
        String sisetu = gsMsg.getMessage("cmn.facility.name");
        String kikann = gsMsg.getMessage("cmn.period");
        String mokuteki = gsMsg.getMessage("reserve.72");
        String naiyou = gsMsg.getMessage("cmn.content");
        Rsv070Model sisetuMdl = __getGroupData(sisetsuSid);
        StringBuilder opLog = new StringBuilder();
        opLog.append("[");
        opLog.append(sisetu);
        opLog.append("] ");
        opLog.append(sisetuMdl.getRsdName());
        opLog.append("\n");
        opLog.append("[");
        opLog.append(kikann);
        opLog.append("] ");
        opLog.append(gsMsg.getMessage("cmn.view.date", new String[] {
                String.valueOf(paramMdl.getRsv110SelectedYearFr()),
                String.valueOf(paramMdl.getRsv110SelectedMonthFr()),
                String.valueOf(paramMdl.getRsv110SelectedDayFr()),
                String.valueOf(paramMdl.getRsv110SelectedHourFr()),
                String.valueOf(paramMdl.getRsv110SelectedMinuteFr())
        }));
        opLog.append(" ～ ");
        opLog.append(gsMsg.getMessage("cmn.view.date", new String[] {
                String.valueOf(paramMdl.getRsv110SelectedYearTo()),
                String.valueOf(paramMdl.getRsv110SelectedMonthTo()),
                String.valueOf(paramMdl.getRsv110SelectedDayTo()),
                String.valueOf(paramMdl.getRsv110SelectedHourTo()),
                String.valueOf(paramMdl.getRsv110SelectedMinuteTo())
        }));
        opLog.append("\n");
        opLog.append("[");
        opLog.append(mokuteki);
        opLog.append("] ");
        opLog.append(paramMdl.getRsv110Mokuteki());
        opLog.append("\n");
        opLog.append("[");
        opLog.append(naiyou);
        opLog.append("] ");
        opLog.append(paramMdl.getRsv110Naiyo());
        return opLog.toString();
    }
}