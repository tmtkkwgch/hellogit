package jp.groupsession.v2.sch.sch040;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.SaibanModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.dao.RsvSisGrpDao;
import jp.groupsession.v2.rsv.dao.RsvSisKryrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisKyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisRyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.rsv.model.RsvSisGrpModel;
import jp.groupsession.v2.rsv.model.RsvSisKyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisRyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisYrkModel;
import jp.groupsession.v2.rsv.model.RsvUserModel;
import jp.groupsession.v2.rsv.rsv070.Rsv070Model;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchAddressDao;
import jp.groupsession.v2.sch.dao.SchColMsgDao;
import jp.groupsession.v2.sch.dao.SchCompanyDao;
import jp.groupsession.v2.sch.dao.SchDataDao;
import jp.groupsession.v2.sch.dao.SchExaddressDao;
import jp.groupsession.v2.sch.dao.SchExcompanyDao;
import jp.groupsession.v2.sch.dao.SchExdataDao;
import jp.groupsession.v2.sch.dao.SchPriConfDao;
import jp.groupsession.v2.sch.dao.ScheduleReserveDao;
import jp.groupsession.v2.sch.dao.ScheduleSearchDao;
import jp.groupsession.v2.sch.model.SchAddressModel;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchCompanyModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.SchExaddressModel;
import jp.groupsession.v2.sch.model.SchExcompanyModel;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.sch.model.SchRepeatKbnModel;
import jp.groupsession.v2.sch.model.ScheduleExSearchModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.sch.pdf.SchTanPdfModel;
import jp.groupsession.v2.sch.pdf.SchTanPdfUtil;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.sch.sch040.model.Sch040AddressModel;
import jp.groupsession.v2.sch.sch040.model.Sch040AttendModel;
import jp.groupsession.v2.sch.sch040.model.Sch040CompanyModel;
import jp.groupsession.v2.sch.sch040.model.Sch040ContactModel;
import jp.groupsession.v2.sch.sch040.model.Sch040DBCompanyBaseModel;
import jp.groupsession.v2.sch.sch040.model.Sch040DBCompanyModel;
import jp.groupsession.v2.sch.sch220.Sch220Biz;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch040Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch040Biz.class);
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスト情報 */
    public RequestModel reqMdl__ = null;
    /** 採番コントローラ */
    public MlCountMtController cntCon__ = null;

    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param cntCon MlCountMtController
     * @param reqMdl RequestModel
     */
    public Sch040Biz(Connection con, MlCountMtController cntCon,
                     RequestModel reqMdl) {
        reqMdl__ = reqMdl;
        con__ = con;
        cntCon__ = cntCon;
    }
    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl リクエスト情報
     */
    public Sch040Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }
    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl リクエスト情報
     * @param cntCon MlCountMtController
     */
    public Sch040Biz(Connection con, RequestModel reqMdl, MlCountMtController cntCon) {
        con__ = con;
        reqMdl__ = reqMdl;
        cntCon__ = cntCon;
    }
    /**
     * <br>[機  能] 初期表示画面情報を取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl アクションフォーム
     * @param pconfig プラグインコンフィグ
     * @param con コネクション
     * @return アクションフォーム
     * @throws SQLException SQL実行時例外
     */
    public Sch040ParamModel getInitData(Sch040ParamModel paramMdl,
                      PluginConfig pconfig, Connection con)
    throws SQLException {
        RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();

        //セッション情報を取得
        Sch010Biz sch010biz = new Sch010Biz(reqMdl__);
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        CommonBiz cmnBiz = new CommonBiz();

        //施設予約の管理者
        boolean rsvAdmin = cmnBiz.isPluginAdmin(con, usModel, GSConstSchedule.PLUGIN_ID_RESERVE);

        //施設予約使用有無
        if (pconfig.getPlugin("reserve") != null) {
            paramMdl.setReservePluginKbn(GSConstSchedule.RESERVE_PLUGIN_USE);
            log__.debug("施設予約使用");
        } else {
            paramMdl.setReservePluginKbn(GSConstSchedule.RESERVE_PLUGIN_NOT_USE);
            log__.debug("施設予約使用不可");
        }

        //アドレス帳使用有無
        if (pconfig.getPlugin("address") != null) {
            paramMdl.setAddressPluginKbn(GSConstSchedule.PLUGIN_USE);
            log__.debug("アドレス帳使用");
        } else {
            paramMdl.setAddressPluginKbn(GSConstSchedule.PLUGIN_NOT_USE);
            log__.debug("アドレス帳使用不可");
        }

        //WEB検索使用有無
        if (pconfig.getPlugin("search") != null) {
            paramMdl.setSearchPluginKbn(GSConstSchedule.PLUGIN_USE);
            log__.debug("WEB検索使用");
        } else {
            paramMdl.setSearchPluginKbn(GSConstSchedule.PLUGIN_NOT_USE);
            log__.debug("WEB検索使用不可");
        }

        //個人設定を取得
        SchPriConfModel confMdl = sch010biz.getPrivateConf(sessionUsrSid, con);
        //管理者設定を取得
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = biz.getAdmConfModel(con);

        //タイトル色区分
        paramMdl.setSch040colorKbn(adminConf.getSadMsgColorKbn());

        //共有範囲
        paramMdl.setSch040CrangeKbn(adminConf.getSadCrange());

        //リクエストパラメータを取得
        //表示開始日
        String strDspDate = NullDefault.getString(
                paramMdl.getSch010DspDate(), new UDate().getDateString());
        UDate dspDate = new UDate();
        dspDate.setDate(strDspDate);

        //年月日初期選択値
        UDate uDate = new UDate();
        uDate.setDate(
                NullDefault.getString(
                        paramMdl.getSch010SelectDate(), uDate.getDateString()));

        //表示項目設定
        String cmd = NullDefault.getString(paramMdl.getCmd(), "");
        if (cmd.equals(GSConstSchedule.CMD_ADD)) {
            int iniPub = GSConstSchedule.DSP_PUBLIC;
            int iniFcolor = GSConstSchedule.DF_BG_COLOR;
            int iniEdit = GSConstSchedule.EDIT_CONF_NONE;
            if (confMdl != null) {
                iniPub = confMdl.getSccIniPublic();
                iniPub = biz.getInitPubAuth(con, confMdl);
                if (NullDefault.getString(paramMdl.getSch010SelectUsrKbn(), "").equals(
                        String.valueOf(GSConstSchedule.USER_KBN_GROUP))
                        && iniPub != GSConstSchedule.DSP_PUBLIC
                        && iniPub != GSConstSchedule.DSP_NOT_PUBLIC) {
                    iniPub = GSConstSchedule.DSP_PUBLIC;
                }

                iniFcolor = confMdl.getSccIniFcolor();
                iniEdit = biz.getInitEditAuth(con, confMdl);
            }

            iniFcolor = biz.getUserColor(iniFcolor, con);

//          新規モード

            //登録日時の初期化
            paramMdl.setSch040AddDate(null);

            //名前
            String uid = NullDefault.getStringZeroLength(paramMdl.getSch010SelectUsrSid(), "-1");
            String ukb = NullDefault.getStringZeroLength(paramMdl.getSch010SelectUsrKbn(), "0");
            log__.debug("uid=" + uid);
            log__.debug("ukb=" + ukb);
            paramMdl.setSch040UsrName(
                    getUsrName(Integer.parseInt(uid), Integer.parseInt(ukb), con));
            //登録者
            paramMdl.setSch040AddUsrName(usModel.getUsisei() + " " + usModel.getUsimei());
            //背景色
            paramMdl.setSch040Bgcolor(NullDefault.getString(
                    paramMdl.getSch040Bgcolor(), String.valueOf(iniFcolor)));
            //公開非公開
            paramMdl.setSch040Public(NullDefault.getString(
                    paramMdl.getSch040Public(), String.valueOf(iniPub)));
            paramMdl.setSch040Edit(NullDefault.getString(
                    paramMdl.getSch040Edit(), String.valueOf(iniEdit)));

            //出欠確認区分
            paramMdl.setSch040AttendKbn(NullDefault.getString(
                    paramMdl.getSch040AttendKbn(), String.valueOf(GSConstSchedule.ATTEND_KBN_NO)));

            //スケジュール編集画面 表示モード
            paramMdl.setSch040EditDspMode(
                    String.valueOf(GSConstSchedule.EDIT_DSP_MODE_NORMAL));
            //グループラベル
            paramMdl.setSch040GroupLabel(sch010biz.getGroupLabelList(con, sessionUsrSid));

            //デフォルト表示グループ
            SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
            String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);
            int dfGpSid = SchCommonBiz.getDspGroupSid(dfGpSidStr);
            int dspGpSid = 0;
            boolean myGroupFlg = false;
            //表示グループ
            String dspGpSidStr = NullDefault.getString(paramMdl.getSch040GroupSid(), dfGpSidStr);
            dspGpSidStr = scBiz.getEnableSelectGroup(
                    paramMdl.getSch040GroupLabel(), dspGpSidStr, dfGpSidStr);
            if (SchCommonBiz.isMyGroupSid(dspGpSidStr)) {
                dspGpSid = SchCommonBiz.getDspGroupSid(dspGpSidStr);
                paramMdl.setSch040GroupSid(dspGpSidStr);
                myGroupFlg = true;
            } else {
                dspGpSid = NullDefault.getInt(paramMdl.getSch040GroupSid(), dfGpSid);
                paramMdl.setSch040GroupSid(dspGpSidStr);
            }
            paramMdl.setSch041GroupSid(paramMdl.getSch040GroupSid());

            //除外するユーザSIDを設定
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            usrSids.add(new Integer(GSConstUser.SID_ADMIN));
            usrSids.add(new Integer(uid));

            //追加済みユーザSID
            ArrayList < Integer > list = null;
            ArrayList < CmnUsrmInfModel > selectUsrList = null;
            String[] users = paramMdl.getSv_users();
            if (users != null && users.length > 0) {
                list = new ArrayList<Integer>();
                for (int i = 0; i < users.length; i++) {
                    list.add(new Integer(users[i]));
                    //同時登録ユーザを所属リストから除外する
                    usrSids.add(new Integer(users[i]));
                }

                UserBiz userBiz = new UserBiz();
                selectUsrList = (ArrayList<CmnUsrmInfModel>) userBiz.getUserList(con, users);
            }

            SchCommonBiz schBiz = new SchCommonBiz(reqMdl__);
            ArrayList<CmnUsrmInfModel> belongList =
                schBiz.getBelongUserList(
                        con,
                        dspGpSid,
                        usrSids,
                        sessionUsrSid,
                        myGroupFlg);

            //グループ所属ユーザラベル
            removeNotRegistUser(con, belongList, sessionUsrSid);
            paramMdl.setSch040BelongLabel(belongList);

            //同時登録ユーザラベル
            paramMdl.setSch040SelectUsrLabel(selectUsrList);

            //施設予約個人設定を取得
            RsvUserModel rsvUserConf = rsvCmnBiz.getRevUserModel(sessionUsrSid, con);
            int dfReservGpSid = GSConstReserve.COMBO_DEFAULT_VALUE;
            if (rsvUserConf != null) {
                dfReservGpSid = rsvUserConf.getRsgSid();
            }
            paramMdl.setSch040ReserveGroupSid(
                    NullDefault.getString(paramMdl.getSch040ReserveGroupSid(),
                    String.valueOf(dfReservGpSid)));

            paramMdl.setSch040ReserveGroupLabel(
                    getReserveGroupLabelList(con, usModel.getUsrsid(), rsvAdmin, reqMdl__));

            //除外する施設SIDを設定
            ArrayList<Integer> resSids = new ArrayList<Integer>();
            RsvSisDataDao dataDao = new RsvSisDataDao(con);
            //追加済み施設SID
            ArrayList < Integer > resList = null;
            ArrayList<RsvSisDataModel> selectResList = null;
            String[] reservs = paramMdl.getSvReserveUsers();
            if (NullDefault.getString(paramMdl.getSch040InitFlg(), "0").equals("1")
            && reservs != null && reservs.length > 0) {
                resList = new ArrayList<Integer>();
                for (int i = 0; i < reservs.length; i++) {
                    resList.add(new Integer(reservs[i]));
                    //同時登録施設を所属リストから除外する
                    resSids.add(new Integer(reservs[i]));
                }
                if (rsvAdmin) {
                    //全施設
                    selectResList =
                        dataDao.selectGrpSisetuList(resList);
                } else {
                    //閲覧権限のある施設
                    selectResList =
                        dataDao.selectGrpSisetuCanReadList(resList, sessionUsrSid);
                }

            }

            paramMdl.setSch040ReserveSelectLabel(selectResList);

            ArrayList<RsvSisDataModel> belongResList = null;
            if (rsvAdmin) {
                //全施設を取得する
                belongResList =
                    dataDao.selectGrpSisetuList(
                            NullDefault.getInt(paramMdl.getSch040ReserveGroupSid(), 0),
                            resSids);
            } else {
                //アクセス権限のある施設を取得
                belongResList =
                    dataDao.selectGrpSisetuCanEditList(
                            NullDefault.getInt(paramMdl.getSch040ReserveGroupSid(), 0),
                            resSids, sessionUsrSid);
            }

            paramMdl.setSch040ReserveBelongLabel(belongResList);

            //拡張情報を画面パラメータへ設定
            SchCommonBiz cBiz = new SchCommonBiz(reqMdl__);
            ScheduleExSearchModel extMdl =
                cBiz.getDispDefaultExtend(sessionUsrSid, uDate, con,
                     NullDefault.getInt(paramMdl.getSch010SelectUsrKbn(),
                             GSConstSchedule.USER_KBN_USER));
            __setScheduleExSearchModelToForm(
                    sessionUsrSid, paramMdl, extMdl, con, GSConstSchedule.CMD_ADD);

        } else if (cmd.equals(GSConstSchedule.CMD_EDIT)) {
//----------修正モード------------------------------------------------------//
            String scdSid = NullDefault.getString(paramMdl.getSch010SchSid(), "-1");
            String uid = paramMdl.getSch010SelectUsrSid();
            GsMessage gsMsg = new GsMessage(reqMdl__);
            if (scdSid != null) {
                ScheduleSearchModel schMdl = getSchData(Integer.parseInt(scdSid), adminConf, con);
                if (schMdl == null) {
                    //編集対象が無い場合
                    return paramMdl;
                }

                //対象スケジュールを編集可能かを判定する
                SchCommonBiz schCmnBiz = new SchCommonBiz();
                if (!schCmnBiz.canRegistSchedule(con, schMdl, sessionUsrSid)) {
                    paramMdl.setSch040ViewFlg(false);
                    return paramMdl;
                }

                if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
                    paramMdl.setSch040UsrName(
                            getUsrName(schMdl.getScdUsrSid(), schMdl.getScdUsrKbn(), con));
                } else {
                    paramMdl.setSch040UsrName(schMdl.getScdUsrSei() + " " + schMdl.getScdUsrMei());
                }
                paramMdl.setSch040AddUsrName(schMdl.getScdAuidSei() + " " + schMdl.getScdAuidMei());
                paramMdl.setSch040AddUsrJkbn(String.valueOf(schMdl.getScdAuidJkbn()));
                //登録日時
                String textAddDate = gsMsg.getMessage("schedule.src.84");
                paramMdl.setSch040AddDate(
                        textAddDate + " : "
                        + UDateUtil.getSlashYYMD(schMdl.getScdAdate())
                        + " "
                        + UDateUtil.getSeparateHM(schMdl.getScdAdate()));

                UDate frDate = schMdl.getScdFrDate();
                UDate toDate = schMdl.getScdToDate();
                //開始年月日
                paramMdl.setSch040FrYear(
                        NullDefault.getStringZeroLength(paramMdl.getSch040FrYear(),
                                String.valueOf(frDate.getYear())));
                paramMdl.setSch040FrMonth(
                        NullDefault.getStringZeroLength(paramMdl.getSch040FrMonth(),
                                String.valueOf(frDate.getMonth())));
                paramMdl.setSch040FrDay(
                        NullDefault.getStringZeroLength(paramMdl.getSch040FrDay(),
                                String.valueOf(frDate.getIntDay())));
                //終了年月日
                paramMdl.setSch040ToYear(
                        NullDefault.getStringZeroLength(paramMdl.getSch040ToYear(),
                                String.valueOf(toDate.getYear())));
                paramMdl.setSch040ToMonth(
                        NullDefault.getStringZeroLength(paramMdl.getSch040ToMonth(),
                                String.valueOf(toDate.getMonth())));
                paramMdl.setSch040ToDay(
                        NullDefault.getStringZeroLength(paramMdl.getSch040ToDay(),
                                String.valueOf(toDate.getIntDay())));
                //時間指定
                if (NullDefault.getInt(paramMdl.getSch040InitFlg(), GSConstSchedule.INIT_FLG)
                        == GSConstSchedule.INIT_FLG) {
                    paramMdl.setSch040TimeKbn(String.valueOf(schMdl.getScdDaily()));
                }
                //時間指定無し
                if (NullDefault.getInt(paramMdl.getSch040TimeKbn(), GSConstSchedule.TIME_EXIST)
                        == GSConstSchedule.TIME_NOT_EXIST) {

                    paramMdl.setSch040FrHour(
                            NullDefault.getStringZeroLength(paramMdl.getSch040FrHour(),
                                    GSConstSchedule.TIME_NOT_SELECT));
                    paramMdl.setSch040FrMin(
                            NullDefault.getStringZeroLength(paramMdl.getSch040FrMin(),
                                    GSConstSchedule.TIME_NOT_SELECT));
                    paramMdl.setSch040ToHour(
                            NullDefault.getStringZeroLength(paramMdl.getSch040ToHour(),
                                    GSConstSchedule.TIME_NOT_SELECT));
                    paramMdl.setSch040ToMin(
                            NullDefault.getStringZeroLength(paramMdl.getSch040ToMin(),
                                    GSConstSchedule.TIME_NOT_SELECT));
                } else {
                    paramMdl.setSch040FrHour(
                            NullDefault.getStringZeroLength(paramMdl.getSch040FrHour(),
                            String.valueOf(frDate.getIntHour())));
                    paramMdl.setSch040FrMin(
                            NullDefault.getStringZeroLength(paramMdl.getSch040FrMin(),
                                    String.valueOf(frDate.getIntMinute())));
                    paramMdl.setSch040ToHour(
                            NullDefault.getStringZeroLength(paramMdl.getSch040ToHour(),
                                    String.valueOf(toDate.getIntHour())));
                    paramMdl.setSch040ToMin(
                            NullDefault.getStringZeroLength(paramMdl.getSch040ToMin(),
                                    String.valueOf(toDate.getIntMinute())));
                }

                //背景
                int iniBgcolor = GSConstSchedule.DF_BG_COLOR;
                if (schMdl.getScdBgcolor() > GSConstSchedule.DF_BG_COLOR) {
                    iniBgcolor = biz.getUserColor(schMdl.getScdBgcolor(), con);

                }
                paramMdl.setSch040Bgcolor(
                        NullDefault.getString(
                                paramMdl.getSch040Bgcolor(), String.valueOf(iniBgcolor)));
                //タイトル
                paramMdl.setSch040Title(
                        NullDefault.getString(paramMdl.getSch040Title(), schMdl.getScdTitle()));
                //内容
                paramMdl.setSch040Value(
                        NullDefault.getString(paramMdl.getSch040Value(), schMdl.getScdValue()));
                //備考
                paramMdl.setSch040Biko(
                        NullDefault.getString(paramMdl.getSch040Biko(), schMdl.getScdBiko()));

                //公開
                paramMdl.setSch040Public(
                        NullDefault.getString(paramMdl.getSch040Public(),
                                String.valueOf(schMdl.getScdPublic())));
                //編集権限
                paramMdl.setSch040Edit(
                        NullDefault.getString(paramMdl.getSch040Edit(),
                                String.valueOf(schMdl.getScdEdit())));

                //出欠確認区分
                int attendKbn = schMdl.getScdAttendKbn();
                paramMdl.setSch040AttendKbn(
                        NullDefault.getString(paramMdl.getSch040AttendKbn(),
                                String.valueOf(attendKbn)));

                //出欠回答区分
                paramMdl.setSch040AttendAnsKbn(
                        NullDefault.getString(paramMdl.getSch040AttendAnsKbn(),
                                String.valueOf(schMdl.getScdAttendAns())));

                //出欠登録者区分
                int attendAnsUsrKbn = schMdl.getScdAttendAuKbn();
                //スケジュール編集画面 表示モード
                int editDspMode = getEditDspMode(attendKbn, attendAnsUsrKbn);
                paramMdl.setSch040EditDspMode(String.valueOf(editDspMode));

                if (editDspMode != GSConstSchedule.EDIT_DSP_MODE_NORMAL) {
                    //出欠確認回答一覧
                    ArrayList<Sch040AttendModel> ansList =
                            getAttendAnsList(schMdl.getScdGrpSid());
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

                //スケジュール編集画面表示モードが回答者の場合
                if (isCheckEditDspModeAns(attendKbn, attendAnsUsrKbn)) {
                    //表示用の文字列を設定

                    //開始日時
                    StringBuilder frBuf = new StringBuilder();
                    frBuf.append(
                            gsMsg.getMessage("cmn.date4",
                                                        new String[] {
                                                        String.valueOf(frDate.getYear()),
                                                        String.valueOf(frDate.getMonth()),
                                                        String.valueOf(frDate.getIntDay())
                                                        }));
                    //終了日時
                    StringBuilder toBuf = new StringBuilder();
                    toBuf.append(
                            gsMsg.getMessage("cmn.date4",
                                                        new String[] {
                                                        String.valueOf(toDate.getYear()),
                                                        String.valueOf(toDate.getMonth()),
                                                        String.valueOf(toDate.getIntDay())
                                                        }));
                    if (NullDefault.getInt(paramMdl.getSch040TimeKbn(), GSConstSchedule.TIME_EXIST)
                            != GSConstSchedule.TIME_NOT_EXIST) {
                        //時
                        String[] paramsFr = {String.valueOf(frDate.getIntHour()),
                                StringUtil.toDecFormat(frDate.getIntMinute(), "00")};
                        frBuf.append(gsMsg.getMessage("cmn.time.input", paramsFr));

                        String[] paramsTo = {String.valueOf(toDate.getIntHour()),
                                StringUtil.toDecFormat(toDate.getIntMinute(), "00")};
                        toBuf.append(gsMsg.getMessage("cmn.time.input", paramsTo));
                    }
                    paramMdl.setSch040DspFromDate(frBuf.toString());
                    paramMdl.setSch040DspToDate(toBuf.toString());
                    paramMdl.setSch040DspValue(
                            StringUtilHtml.transToHTmlPlusAmparsant(
                                    NullDefault.getString(schMdl.getScdValue(), "")));
                    paramMdl.setSch040DspBiko(
                            StringUtilHtml.transToHTmlPlusAmparsant(
                                    NullDefault.getString(schMdl.getScdBiko(), "")));

                    //出欠確認依頼者スケジュール存在フラグ
                    //依頼者のスケジュールが削除された場合、回答者は削除する事が可能
                    SchDataDao schDataDao = new SchDataDao(con);
                    boolean delFlg = schDataDao.isCheckAttendAuSchDelete(Integer.parseInt(scdSid));
                    if (delFlg) {
                        paramMdl.setSch040AttendDelFlg(GSConstSchedule.ATTEND_SCH_DEL_YES);
                    } else {
                        paramMdl.setSch040AttendDelFlg(GSConstSchedule.ATTEND_SCH_DEL_NO);
                    }
                }


                //拡張SID存在フラグ
                boolean textDspFlg = false;
                if (schMdl.getSceSid() > 0) {
                    textDspFlg = true;
                }
                paramMdl.setSch040ExTextDspFlg(textDspFlg);


                //デフォルト表示グループ
                SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
                String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);
                int dfGpSid = SchCommonBiz.getDspGroupSid(dfGpSidStr);
                int dspGpSid = 0;
                boolean myGroupFlg = false;
                //表示グループ
                String dspGpSidStr = NullDefault.getString(
                        paramMdl.getSch040GroupSid(), dfGpSidStr);

                if (SchCommonBiz.isMyGroupSid(dspGpSidStr)) {
                    dspGpSid = SchCommonBiz.getDspGroupSid(dspGpSidStr);
                    paramMdl.setSch040GroupSid(dspGpSidStr);
                    myGroupFlg = true;
                } else {
                    dspGpSid = NullDefault.getInt(paramMdl.getSch040GroupSid(), dfGpSid);
                    paramMdl.setSch040GroupSid(dspGpSidStr);
                }
                paramMdl.setSch041GroupSid(paramMdl.getSch040GroupSid());
                //グループラベル
                paramMdl.setSch040GroupLabel(sch010biz.getGroupLabelList(con, sessionUsrSid));
                //所属ユーザを取得
                CmnUsrmInfDao usrmInfDao = new CmnUsrmInfDao(con);
                //除外するユーザSIDを設定
                ArrayList<Integer> usrSids = new ArrayList<Integer>();
                usrSids.add(new Integer(GSConstUser.SID_ADMIN));
                usrSids.add(new Integer(uid));

                //追加済みユーザSID
                ArrayList < Integer > list = null;
                ArrayList < CmnUsrmInfModel > selectUsrList = null;

                if (paramMdl.getSv_users() == null || paramMdl.getSv_users().length == 0
                     && paramMdl.getSch040InitFlg().equals(
                             String.valueOf(GSConstSchedule.INIT_FLG))) {
                    removeNotRegistUser(con, schMdl.getUsrInfList(), sessionUsrSid);
                    __setSaveUsersForDb(paramMdl, schMdl.getUsrInfList());
                }

                //同時修正の初期値を設定
                if (paramMdl.getSch040InitFlg().equals(String.valueOf(GSConstSchedule.INIT_FLG))) {
                    int iniSame = biz.getInitSameAuth(con, confMdl);
                    paramMdl.setSch040BatchRef(String.valueOf(iniSame));
                }

                String[] users = paramMdl.getSv_users();
                if (users != null && users.length > 0) {
                    list = new ArrayList<Integer>();
                    for (int i = 0; i < users.length; i++) {
                        list.add(new Integer(users[i]));
                        //同時登録ユーザを所属リストから除外する
                        usrSids.add(new Integer(users[i]));
                    }

                    selectUsrList = usrmInfDao.getUserList(list);
                }

                SchCommonBiz schBiz = new SchCommonBiz(reqMdl__);
                ArrayList<CmnUsrmInfModel> belongList =
                    schBiz.getBelongUserList(
                            con,
                            dspGpSid,
                            usrSids,
                            sessionUsrSid,
                            myGroupFlg);

                //グループ所属ユーザラベル
                removeNotRegistUser(con, belongList, sessionUsrSid);
                paramMdl.setSch040BelongLabel(belongList);

                //同時登録ユーザラベル
                paramMdl.setSch040SelectUsrLabel(selectUsrList);

                //既に登録されている同時登録ユーザラベル
                paramMdl.setSch040AddedUsrLabel(schMdl.getUsrInfList());

                //施設予約
                //施設予約個人設定を取得
                RsvUserModel rsvUserConf = rsvCmnBiz.getRevUserModel(sessionUsrSid, con);
                int resGpSid = GSConstReserve.COMBO_DEFAULT_VALUE;
                if (rsvUserConf != null) {
                    resGpSid = rsvUserConf.getRsgSid();
                }
                paramMdl.setSch040ReserveGroupSid(
                        NullDefault.getString(
                                paramMdl.getSch040ReserveGroupSid(),
                                String.valueOf(resGpSid)));
                paramMdl.setSch040ReserveGroupLabel(
                        getReserveGroupLabelList(con, sessionUsrSid, rsvAdmin, reqMdl__));

                //除外する施設SIDを設定
                ArrayList<Integer> resSids = new ArrayList<Integer>();
                RsvSisDataDao dataDao = new RsvSisDataDao(con);
                //追加済み施設SID
                if (NullDefault.getString(paramMdl.getSch040InitFlg(), "0").equals("0")
                && (paramMdl.getSvReserveUsers() == null
                        || paramMdl.getSvReserveUsers().length == 0)) {
                    __setSaveReserveForDb(paramMdl, con);
                }
                ArrayList < Integer > resList = null;
                ArrayList<RsvSisDataModel> selectResList = null;
                String[] reservs = paramMdl.getSvReserveUsers();
                if (reservs != null && reservs.length > 0) {
                    resList = new ArrayList<Integer>();
                    for (int i = 0; i < reservs.length; i++) {
                        resList.add(new Integer(reservs[i]));
                        //同時登録施設を所属リストから除外する
                        resSids.add(new Integer(reservs[i]));
                    }

                    if (rsvAdmin) {
                        //全施設
                        selectResList =
                            dataDao.selectGrpSisetuList(resList);
                    } else {
                        //閲覧権限のある施設
                        selectResList =
                            dataDao.selectGrpSisetuCanReadList(resList, sessionUsrSid);
                    }

                }

                paramMdl.setSch040ReserveSelectLabel(selectResList);

                ArrayList<RsvSisDataModel> belongResList = null;
                if (rsvAdmin) {
                    //全施設を取得する
                    belongResList =
                        dataDao.selectGrpSisetuList(
                                NullDefault.getInt(
                                        paramMdl.getSch040ReserveGroupSid(), 0), resSids);
                } else {
                    //アクセス権限のある施設を取得
                    belongResList =
                        dataDao.selectGrpSisetuCanEditList(
                                NullDefault.getInt(paramMdl.getSch040ReserveGroupSid(), 0),
                                resSids, sessionUsrSid);
                }

                paramMdl.setSch040ReserveBelongLabel(belongResList);

                //編集権限のない施設数を取得する。
                int count = getCanNotEditRsvCount(paramMdl, sessionUsrSid, con, rsvAdmin);
                paramMdl.setSch040CantReadRsvCount(count);

                //会社情報、アドレス帳情報を設定
                if (NullDefault.getInt(paramMdl.getSch040InitFlg(), GSConstSchedule.INIT_FLG)
                    == GSConstSchedule.INIT_FLG) {
                    SchCompanyDao companyDao = new SchCompanyDao(con);
                    List<SchCompanyModel> companyList = companyDao.select(Integer.parseInt(scdSid));

                    if (!companyList.isEmpty()) {
                        String[] companySid = new String[companyList.size()];
                        String[] companyBaseSid = new String[companyList.size()];

                        for (int index = 0; index < companyList.size(); index++) {
                            SchCompanyModel companyModel = companyList.get(index);
                            companySid[index] = String.valueOf(companyModel.getAcoSid());
                            companyBaseSid[index] = String.valueOf(companyModel.getAbaSid());
                        }

                        paramMdl.setSch040CompanySid(companySid);
                        paramMdl.setSch040CompanyBaseSid(companyBaseSid);
                    }

                    SchAddressDao addressDao = new SchAddressDao(con);
                    List<SchAddressModel> addressList = addressDao.select(Integer.parseInt(scdSid));
                    if (addressList != null) {
                        String[] addressId = new String[addressList.size()];
                        for (int index = 0; index < addressList.size(); index++) {
                            addressId[index] = String.valueOf(addressList.get(index).getAdrSid());
                            if (addressList.get(index).getAdcSid() > 0) {
                                paramMdl.setSch040contact(1);
                            }
                        }

                        paramMdl.setSch040AddressId(addressId);
                    }

                    setCompanyData(paramMdl, con, sessionUsrSid, reqMdl__);
                }

                //拡張情報を画面パラメータへ設定
                ScheduleExSearchModel extMdl =
                    getSchExData(Integer.parseInt(scdSid), adminConf, con);
                if (extMdl == null) {
                    SchCommonBiz cBiz = new SchCommonBiz(reqMdl__);
                    extMdl = cBiz.getDispDefaultExtend(sessionUsrSid, uDate, con,
                             NullDefault.getInt(
                                     paramMdl.getSch010SelectUsrKbn(),
                                     GSConstSchedule.USER_KBN_USER));
                }
                __setScheduleExSearchModelToForm(
                        sessionUsrSid, paramMdl, extMdl, con, GSConstSchedule.CMD_EDIT);
                paramMdl.setSch040DataFlg(true);
                paramMdl.setSch040ViewFlg(true);

            } else {
                paramMdl.setSch040DataFlg(false);
                paramMdl.setSch040ViewFlg(true);
            }

        }

        //共通項目
        //カラーコメント
        SchColMsgDao msgDao = new SchColMsgDao(con);
        ArrayList<String> msgList = msgDao.selectMsg();
        paramMdl.setSch040ColorMsgList(msgList);
        //年コンボを作成
        paramMdl.setSch040YearLabel(getYearLabel(dspDate.getYear()));
        //月コンボを作成
        paramMdl.setSch040MonthLabel(getMonthLabel());
        //日コンボを作成
        paramMdl.setSch040DayLabel(getDayLabel());
        //時コンボを作成
        paramMdl.setSch040HourLabel(getHourLabel());
        //分コンボを作成
        paramMdl.setSch040MinuteLabel(getMinuteLabel(con));

        paramMdl.setSch040FrYear(
                NullDefault.getString(paramMdl.getSch040FrYear(),
                        String.valueOf(uDate.getYear())));
        paramMdl.setSch040FrMonth(
                NullDefault.getString(paramMdl.getSch040FrMonth(),
                        String.valueOf(uDate.getMonth())));
        paramMdl.setSch040FrDay(
                NullDefault.getString(paramMdl.getSch040FrDay(),
                        String.valueOf(uDate.getIntDay())));
        paramMdl.setSch040ToYear(
                NullDefault.getString(paramMdl.getSch040ToYear(),
                        String.valueOf(uDate.getYear())));
        paramMdl.setSch040ToMonth(
                NullDefault.getString(paramMdl.getSch040ToMonth(),
                        String.valueOf(uDate.getMonth())));
        paramMdl.setSch040ToDay(
                NullDefault.getString(paramMdl.getSch040ToDay(),
                        String.valueOf(uDate.getIntDay())));
        //時間
        UDate frDate = null;
        UDate toDate = null;
        if (confMdl != null) {
            frDate = confMdl.getSccIniFrDate();
            toDate = confMdl.getSccIniToDate();
        } else {
            frDate = new UDate();
            toDate = new UDate();
            frDate.setHour(GSConstSchedule.DF_FROM_HOUR);
            frDate.setMinute(GSConstSchedule.DF_FROM_MINUTES);
            toDate.setHour(GSConstSchedule.DF_TO_HOUR);
            toDate.setMinute(GSConstSchedule.DF_TO_MINUTES);
        }
        paramMdl.setSch040FrHour(
                NullDefault.getStringZeroLength(paramMdl.getSch040FrHour(),
                String.valueOf(frDate.getIntHour())));
        paramMdl.setSch040FrMin(
                NullDefault.getStringZeroLength(paramMdl.getSch040FrMin(),
                        String.valueOf(frDate.getIntMinute())));
        paramMdl.setSch040ToHour(
                NullDefault.getStringZeroLength(paramMdl.getSch040ToHour(),
                        String.valueOf(toDate.getIntHour())));
        paramMdl.setSch040ToMin(
                NullDefault.getStringZeroLength(paramMdl.getSch040ToMin(),
                        String.valueOf(toDate.getIntMinute())));
        CommonBiz commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(
                con, usModel, GSConstSchedule.PLUGIN_ID_SCHEDULE);
        if (adminUser) {
            paramMdl.setAdminKbn(GSConst.USER_ADMIN);
        } else {
            paramMdl.setAdminKbn(GSConst.USER_NOT_ADMIN);
        }

        //ボタン用の処理モードを設定する。
        String btnCmd = "";
        if (!StringUtil.isNullZeroStringSpace(cmd)) {
            btnCmd = StringUtil.toSingleCortationEscape(cmd);
        }
        paramMdl.setSch040BtnCmd(btnCmd);

        //閲覧不可のグループ、ユーザを設定
        SchDao schDao = new SchDao(con);
        paramMdl.setSchNotAccessGroupList(schDao.getNotRegistGrpList(sessionUsrSid));
        paramMdl.setSchNotAccessUserList(schDao.getNotRegistUserList(sessionUsrSid));

        //同時登録ユーザラベルからアクセス不可ユーザを除外する
        List<CmnUsrmInfModel> selectUsrList = paramMdl.getSch040SelectUsrLabel();
        if (selectUsrList != null && !selectUsrList.isEmpty()) {
            ArrayList<CmnUsrmInfModel> accessSelectUsrList = new ArrayList<CmnUsrmInfModel>();
            for (CmnUsrmInfModel selectUser : selectUsrList) {
                if (paramMdl.getSchNotAccessUserList().indexOf(selectUser.getUsrSid()) < 0) {
                    accessSelectUsrList.add(selectUser);
                }
            }
            paramMdl.setSch040SelectUsrLabel(accessSelectUsrList);
        }

        //初期表示完了
        paramMdl.setSch040InitFlg(String.valueOf(GSConstSchedule.NOT_INIT_FLG));
        return paramMdl;
    }

    /**
     * <br>[機  能] DBに登録されている同時登録ユーザ情報を画面パラメータへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch040ParamModel
     * @param list 同時登録ユーザ情報リスト
     */
    private void __setSaveUsersForDb(Sch040ParamModel paramMdl, ArrayList<CmnUsrmInfModel> list) {
        ArrayList<String> sv_user_list = new ArrayList<String>();
        if (list != null) {
            for (CmnUsrmInfModel usrMdl : list) {
                sv_user_list.add(String.valueOf(usrMdl.getUsrSid()));
            }

            paramMdl.setSv_users((String[]) sv_user_list.toArray(new String[sv_user_list.size()]));
        }
    }

    /**
     * <br>[機  能] DBに登録されている同時登録施設情報を画面パラメータへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch040ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setSaveReserveForDb(
            Sch040ParamModel paramMdl, Connection con) throws SQLException {
        int scdSid = NullDefault.getInt(paramMdl.getSch010SchSid(), -1);
        //施設SIDリストを取得
        ScheduleReserveDao schRsvDao = new ScheduleReserveDao(con);
        ArrayList<Integer> list = schRsvDao.getScheduleReserveData(scdSid);
        ArrayList<String> sv_user_list = new ArrayList<String>();

        if (list != null) {
            for (Integer rsdSid : list) {
                sv_user_list.add(String.valueOf(rsdSid));
            }

            paramMdl.setSvReserveUsers(
                    (String[]) sv_user_list.toArray(new String[sv_user_list.size()]));
        }
    }

    /**
     * <br>[機  能] DBに登録されている同時登録ユーザ情報を画面パラメータへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param list 同時登録ユーザ情報リスト
     * @return String[] 同時登録ユーザ配列
     */
    public String[] getSaveUsersForDbEx(ArrayList<CmnUsrmInfModel> list) {
        String[] ret = null;
        ArrayList<String> sv_user_list = new ArrayList<String>();
        if (list != null) {
            for (CmnUsrmInfModel usrMdl : list) {
                sv_user_list.add(String.valueOf(usrMdl.getUsrSid()));
            }
            ret = (String[]) sv_user_list.toArray(new String[sv_user_list.size()]);
        }
        return ret;
    }

    /**
     * <br>[機  能] DBに登録されている同時登録施設情報をString[]へ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param exSid スケジュール拡張SID
     * @param con コネクション
     * @return String[] 同時登録ユーザ配列
     * @throws SQLException SQL実行時例外
     */
    public String[] getSaveReserveForDbEx(int exSid, Connection con)
    throws SQLException {

        ScheduleReserveDao schRsvDao = new ScheduleReserveDao(con);
        ArrayList<Integer> list = schRsvDao.getScheduleReserveDataFromExSid(exSid);

        String[] ret = null;
        ArrayList<String> sv_user_list = new ArrayList<String>();
        if (list != null) {
            for (Integer rsdSid : list) {
                sv_user_list.add(String.valueOf(rsdSid));
            }
            ret = (String[]) sv_user_list.toArray(new String[sv_user_list.size()]);
        }
        return ret;
    }

    /**
     * <br>[機  能] ユーザSIDとユーザ区分からユーザ氏名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ区分
     * @param con コネクション
     * @return String ユーザ氏名
     * @throws SQLException SQL実行時例外
     */
    public String getUsrName(int usrSid, int usrKbn, Connection con)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String ret = "";
        if (usrKbn == GSConstSchedule.USER_KBN_GROUP) {
            //グループ
            String textGroup = gsMsg.getMessage("cmn.group");
            if (usrSid == GSConstSchedule.SCHEDULE_GROUP) {
                ret = textGroup;
            } else {
                GroupDao grpDao = new GroupDao(con);
                ret = grpDao.getGroup(usrSid).getGrpName();
            }

        } else {
            UserSearchDao uDao = new UserSearchDao(con);
            CmnUsrmInfModel uMdl = uDao.getUserInfoJtkb(usrSid, GSConstUser.USER_JTKBN_ACTIVE);
            ret = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
        }
        return ret;
    }

    /**
     * <br>[機  能] スケジュールSIDからスケジュール情報(編集用)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @param adminConf 管理者設定
     * @param con コネクション
     * @return ScheduleSearchModel
     * @throws SQLException SQL実行時例外
     */
    public ScheduleSearchModel getSchData(
            int scdSid,
            SchAdmConfModel adminConf,
            Connection con)
    throws SQLException {
        return getSchData(scdSid, adminConf, GSConstSchedule.SSP_AUTHFILTER_EDIT, con);
    }
    /**
     * <br>[機  能] スケジュールSIDからスケジュール情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @param adminConf 管理者設定
     * @param authFilter 権限フィルター 0:すべて 1:閲覧可能 2:編集可能
     * @param con コネクション
     * @return ScheduleSearchModel
     * @throws SQLException SQL実行時例外
     */
    public ScheduleSearchModel getSchData(
            int scdSid,
            SchAdmConfModel adminConf,
            int authFilter,
            Connection con)
    throws SQLException {

        ScheduleSearchModel scdMdl = null;
        CmnUsrmInfModel uMdl = null;
        try {
            ScheduleSearchDao scdDao = new ScheduleSearchDao(con);
            scdMdl = scdDao.getScheduleData(scdSid, authFilter,
                                                                reqMdl__.getSmodel().getUsrsid());
            if (scdMdl == null) {
                return null;
            }
            UserSearchDao uDao = new UserSearchDao(con);
            CmnUsrmDao cuDao = new CmnUsrmDao(con);
            //登録者
            uMdl = uDao.getUserInfoJtkb(scdMdl.getScdAuid(), -1);
            if (uMdl != null) {
                scdMdl.setScdAuidSei(uMdl.getUsiSei());
                scdMdl.setScdAuidMei(uMdl.getUsiMei());
                scdMdl.setScdAuidJkbn(cuDao.getUserJkbn(scdMdl.getScdAuid()));
            }
            //対象ユーザ
            if (scdMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER) {
                uMdl = uDao.getUserInfoJtkb(scdMdl.getScdUsrSid(), -1);
                if (uMdl != null) {
                    scdMdl.setScdUsrSei(uMdl.getUsiSei());
                    scdMdl.setScdUsrMei(uMdl.getUsiMei());
                    scdMdl.setScdUsrJkbn(cuDao.getUserJkbn(scdMdl.getScdUsrSid()));
                }
            } else {
                scdMdl.setScdUsrSei(getUsrName(scdMdl.getScdUsrSid(), scdMdl.getScdUsrKbn(), con));
            }
        } catch (SQLException e) {
            log__.error("スケジュール情報の取得に失敗" + e);
            throw e;
        }

        return scdMdl;
    }

    /**
     * <br>[機  能] スケジュールSIDから同時登録しているスケジュール情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @param adminConf 管理者設定
     * @param con コネクション
     * @return ScheduleSearchModel
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<ScheduleSearchModel> getSchDataList(
            int scdSid,
            SchAdmConfModel adminConf,
            Connection con)
    throws SQLException {

        ArrayList<ScheduleSearchModel> ret = new ArrayList<ScheduleSearchModel>();
        ScheduleSearchModel scdMdl = null;
        try {
            ScheduleSearchDao scdDao = new ScheduleSearchDao(con);
            //同時登録スケジュールSIDを取得
            ArrayList<Integer> scdSidList = scdDao.getScheduleUsrs(
                    scdSid, reqMdl__.getSmodel().getUsrsid(),
                    GSConstSchedule.CRANGE_SHARE_ALL,
                    GSConstSchedule.SSP_AUTHFILTER_EDIT
                    );

            for (Integer sid : scdSidList) {
                scdMdl = getSchData(sid.intValue(), adminConf, con);
                if (scdMdl != null) {
                    ret.add(scdMdl);
                }
            }

        } catch (SQLException e) {
            log__.error("スケジュール情報の取得に失敗" + e);
            throw e;
        }

        return ret;
    }

    /**
     * <br>[機  能] スケジュールSIDからスケジュール拡張情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @param adminConf 管理者設定
     * @param con コネクション
     * @return ScheduleSearchModel
     * @throws SQLException SQL実行時例外
     */
    public ScheduleExSearchModel getSchExData(
            int scdSid,
            SchAdmConfModel adminConf,
            Connection con)
    throws SQLException {
        ScheduleExSearchModel scdMdl = null;
        CmnUsrmInfModel uMdl = null;
        try {
            SchExdataDao exDao = new SchExdataDao(con);
            scdMdl = exDao.getScheduleExData(scdSid, adminConf.getSadCrange(),
                                                                reqMdl__.getSmodel().getUsrsid());
            if (scdMdl != null) {
                UserSearchDao uDao = new UserSearchDao(con);
                CmnUsrmDao cuDao = new CmnUsrmDao(con);
                //登録者
                uMdl = uDao.getUserInfoJtkb(scdMdl.getSceAuid(), -1);
                if (uMdl != null) {
                    scdMdl.setSceAuidSei(uMdl.getUsiSei());
                    scdMdl.setSceAuidMei(uMdl.getUsiMei());
                    scdMdl.setSceAuidJkbn(cuDao.getUserJkbn(scdMdl.getSceAuid()));
                }
                //対象ユーザ
                if (scdMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER) {
                    uMdl = uDao.getUserInfoJtkb(scdMdl.getScdUsrSid(), -1);
                    if (uMdl != null) {
                        scdMdl.setSceUsrSei(uMdl.getUsiSei());
                        scdMdl.setSceUsrMei(uMdl.getUsiMei());
                        scdMdl.setSceUsrJkbn(cuDao.getUserJkbn(scdMdl.getScdUsrSid()));
                    }
                }
            }

        } catch (SQLException e) {
            log__.error("スケジュール情報の取得に失敗" + e);
            throw e;
        }
        return scdMdl;
    }

    /**
     * <br>[機  能] 施設グループリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param sessionUsrSid セッションユーザSID
     * @param admFlg システム管理者フラグ
     * @param reqMdl リクエスト情報
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getReserveGroupLabelList(
            Connection con, int sessionUsrSid, boolean admFlg, RequestModel reqMdl)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textAll = gsMsg.getMessage("cmn.all");

        RsvSisGrpDao dao = new RsvSisGrpDao(con);
        ArrayList<RsvSisGrpModel> ret = null;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        if (admFlg) {
            ret = dao.selectAllGroupData();
        } else {
            ret = dao.getCanEditData(sessionUsrSid);
        }

        labelList.add(
                new LabelValueBean(textAll,
                        String.valueOf(GSConstReserve.COMBO_DEFAULT_VALUE)));

        for (RsvSisGrpModel mdl : ret) {
            labelList.add(
                    new LabelValueBean(mdl.getRsgName(),
                            String.valueOf(mdl.getRsgSid())));
        }

        return labelList;
    }

    /**
     * <br>[機  能] 表示開始日から年コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param year 基準年
     * @return ArrayList (in LabelValueBean)  年コンボ
     */
    public ArrayList<LabelValueBean> getYearLabel(int year) {
        year--;
        GsMessage gsMsg = new GsMessage(reqMdl__);
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i < GSConstSchedule.YEAR_LIST_CNT; i++) {
            String strYear = String.valueOf(year);
            labelList.add(
                new LabelValueBean(
                  gsMsg.getMessage("cmn.year", new String[] {strYear}), strYear));
            year++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 月コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  月コンボ
     */
    public ArrayList<LabelValueBean> getMonthLabel() {
        int month = 1;
        GsMessage gsMsg = new GsMessage(reqMdl__);
        /** メッセージ 月 **/
        String strMonth = gsMsg.getMessage("cmn.month");
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 12; i++) {
            labelList.add(
                    new LabelValueBean(month + strMonth, String.valueOf(month)));
            month++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 日コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  日コンボ
     */
    public ArrayList<LabelValueBean> getDayLabel() {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        /** メッセージ 月 **/
        String strDay = gsMsg.getMessage("cmn.day");
        int day = 1;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 31; i++) {
            labelList.add(
                    new LabelValueBean(day + strDay, String.valueOf(day)));
            day++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 時コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  時コンボ
     */
    public ArrayList<LabelValueBean> getHourLabel() {
        int hour = 0;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //未設定
        String textNoSet = gsMsg.getMessage("cmn.notset");
        labelList.add(
                new LabelValueBean(textNoSet, "-1"));
        for (int i = 0; i < 24; i++) {
            labelList.add(
                    new LabelValueBean(String.valueOf(hour), String.valueOf(hour)));
            hour++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 分コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return ArrayList (in LabelValueBean)  分コンボ
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<LabelValueBean> getMinuteLabel(Connection con) throws SQLException {
        SchCommonBiz cmnBiz = new SchCommonBiz(reqMdl__);
        int hourDivCount = cmnBiz.getDayScheduleHourMemoriCount(con);
        int min = 0;
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //未設定
        String textNoSet = gsMsg.getMessage("cmn.notset");
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(
                new LabelValueBean(textNoSet, "-1"));
        int hourMemCount = 60 / hourDivCount;
        for (int i = 0; i < hourDivCount; i++) {
            labelList.add(
                    new LabelValueBean(
                            StringUtil.toDecFormat(min, "00"), String.valueOf(min)));
            min = min + hourMemCount;
        }
        return labelList;
    }

    /**
     * <br>[機  能] ユーザコンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param uList (in CmnUsrmInfModel) ユーザ情報リスト
     * @return ArrayList (in LabelValueBean)  年コンボ
     */
    public ArrayList<LabelValueBean> getUserLabel(ArrayList < CmnUsrmInfModel > uList) {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        if (uList != null) {
            CmnUsrmInfModel uMdl = null;
            for (int i = 0; i < uList.size(); i++) {
                uMdl = uList.get(i);
                String name = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
                labelList.add(
                        new LabelValueBean(name, String.valueOf(uMdl.getUsrSid())));
            }
        }

        return labelList;
    }


    /**
     * <br>[機  能] スケジュールを新規登録します
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl アクションフォーム
     * @param userSid 登録者SID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @return sidDataList 予約SID、施設SIDリスト
     * @throws Exception SQL実行時例外
     */
    public ArrayList<int []> insertScheduleDate(
            RequestModel reqMdl,
            Sch040ParamModel paramMdl,
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            boolean smailPluginUseFlg) throws Exception {

        SchDataModel schMdl = null;
        SchCommonBiz cmnBiz = new SchCommonBiz(reqMdl);

        //登録モデルを作成
        schMdl = new SchDataModel();
        UDate frDate = new UDate();
        UDate toDate = frDate.cloneUDate();
        UDate now = new UDate();

        ArrayList<int []> sidDataList = new ArrayList<int []>();

        int frYear = Integer.parseInt(paramMdl.getSch040FrYear());
        int frMonth = Integer.parseInt(paramMdl.getSch040FrMonth());
        int frDay = Integer.parseInt(paramMdl.getSch040FrDay());

        int frHour = GSConstSchedule.DAY_START_HOUR;
        int frMin = GSConstSchedule.DAY_START_MINUTES;
        int toHour = GSConstSchedule.DAY_END_HOUR;
        int toMin = GSConstSchedule.DAY_END_MINUTES;
        if (paramMdl.getSch040TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            frHour = Integer.parseInt(paramMdl.getSch040FrHour());
            frMin = Integer.parseInt(paramMdl.getSch040FrMin());
            toHour = Integer.parseInt(paramMdl.getSch040ToHour());
            toMin = Integer.parseInt(paramMdl.getSch040ToMin());
            schMdl.setScdDaily(GSConstSchedule.TIME_EXIST);
        } else {
            schMdl.setScdDaily(GSConstSchedule.TIME_NOT_EXIST);
        }

        frDate.setDate(frYear, frMonth, frDay);
        if (frHour != -1 && frMin != -1) {
            frDate.setHour(frHour);
            frDate.setMinute(frMin);
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        }

        int toYear = Integer.parseInt(paramMdl.getSch040ToYear());
        int toMonth = Integer.parseInt(paramMdl.getSch040ToMonth());
        int toDay = Integer.parseInt(paramMdl.getSch040ToDay());
        toDate.setDate(toYear, toMonth, toDay);
        if (toHour != -1 && toMin != -1) {
            toDate.setHour(toHour);
            toDate.setMinute(toMin);
            toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            toDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
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
        schMdl.setScdAdate(now);
        schMdl.setScdEuid(userSid);
        schMdl.setScdEdate(now);
        //編集区分
        schMdl.setScdEdit(
                NullDefault.getInt(paramMdl.getSch040Edit(), GSConstSchedule.EDIT_CONF_NONE));
        //拡張登録SID
        int extSid = -1;
        schMdl.setSceSid(extSid);

        List<Integer> scdSidList = new ArrayList<Integer>();
        Map<Integer, Integer> scdUserMap = new HashMap<Integer, Integer>();
        int scdSid = -1;
        int scdGpSid = GSConstSchedule.DF_SCHGP_ID;
        int scdResSid = GSConstSchedule.DF_SCHGP_ID;
        //SID採番
        scdSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                SaibanModel.SBNSID_SUB_SCH, userSid);
        schMdl.setScdSid(scdSid);
        schMdl.setScdUsrSid(Integer.parseInt(paramMdl.getSch010SelectUsrSid()));
        schMdl.setScdUsrKbn(Integer.parseInt(paramMdl.getSch010SelectUsrKbn()));


        SchDataDao schDao = new SchDataDao(con__);
        String[] svUsers = paramMdl.getSv_users();
        int addUserSid = -1;
        //スケジュールグループSID（同時登録有りの場合）
        if (svUsers != null && svUsers.length > 0) {
            //スケジュールグループSID（同時登録有りの場合）
            scdGpSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH_GP, userSid);
        }
        String[] svReserves = paramMdl.getSvReserveUsers();
        if (svReserves != null && svReserves.length > 0) {
            //スケジュール施設予約SID（施設予約有りの場合）
            scdResSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH_RES, userSid);
        }
        schMdl.setScdGrpSid(scdGpSid);
        schMdl.setScdRsSid(scdResSid);


        //出欠状況
        if (Integer.parseInt(paramMdl.getSch010SelectUsrKbn()) == GSConstSchedule.USER_KBN_USER) {
            if (paramMdl.getSch040AttendKbn().equals(
                    String.valueOf(GSConstSchedule.ATTEND_KBN_YES))
                    && paramMdl.getSch010SelectUsrKbn().equals(
                            String.valueOf(GSConstSchedule.USER_KBN_USER))) {
                schMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_YES);
                schMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_YES);
                schMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_YES);
                schMdl.setScdEdit(GSConstSchedule.EDIT_CONF_OWN);
            } else {
                schMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_NO);
                schMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
                schMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
            }
        } else {
            schMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_NO);
            schMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
            schMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
        }


        //登録
        schDao.insert(schMdl);
        scdSidList.add(schMdl.getScdSid());
        scdUserMap.put(schMdl.getScdSid(), schMdl.getScdUsrSid());

        //ユーザSID
        String usrSid = paramMdl.getSch010SelectUsrSid();
        //URL取得
        String url = __createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                                           String.valueOf(scdSid), usrSid,
                                           paramMdl);
        cmnBiz.sendPlgSmail(con__, cntCon__, schMdl, appRootPath, plconf, smailPluginUseFlg, url);
        //同時登録分
        if (svUsers != null) {
            for (int i = 0; i < svUsers.length; i++) {
                scdSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                        SaibanModel.SBNSID_SUB_SCH, userSid);
                addUserSid = Integer.parseInt(svUsers[i]);
                url = __createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                                            String.valueOf(scdSid), String.valueOf(addUserSid),
                                            paramMdl);
                schMdl.setScdSid(scdSid);
                schMdl.setScdUsrSid(addUserSid);
                schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_USER);

                //出欠状況
                if (paramMdl.getSch040AttendKbn().equals(
                        String.valueOf(GSConstSchedule.ATTEND_KBN_YES))) {
                    schMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_YES);
                    schMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
                    schMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
                    schMdl.setScdEdit(GSConstSchedule.EDIT_CONF_OWN);
                } else {
                    schMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_NO);
                    schMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
                    schMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
                }

                schDao.insert(schMdl);
                scdSidList.add(schMdl.getScdSid());

                //通知メール
                if (paramMdl.getSch040AttendKbn().equals(
                        String.valueOf(GSConstSchedule.ATTEND_KBN_YES))) {
                    //出欠確認「する」の場合で、
                    //他のユーザによって登録されてた場合の通知設定と出欠確認依頼時の通知設定が
                    //どちらも設定されていた場合は,出欠確認依頼通知メールのみ送信する
                    SchAdmConfModel admConf = cmnBiz.getAdmConfModel(con__);
                    SchPriConfModel priConf = cmnBiz.getSchPriConfModel(con__, addUserSid);

                    //メール送信内容 0:他ユーザからの登録通知  1:出欠確認
                    int sendMailType = 0;
                    //ショートメール通知使用区分

                    //管理者が設定する
                    if (admConf.getSadSmailSendKbn() == GSConstSchedule.SMAIL_SEND_KBN_ADMIN) {
                        //出欠確認通知するの場合
                        if (admConf.getSadSmailAttend() == GSConstSchedule.SMAIL_USE) {
                            sendMailType = 1;
                        } else {
                            //他ユーザからの登録通知するの場合
                            if (admConf.getSadSmail() == GSConstSchedule.SMAIL_USE) {
                                sendMailType = 0;
                            }
                        }

                    //個人が設定するの場合
                    } else {
                        //出欠確認通知するの場合
                        if (priConf.getSccSmailAttend() == GSConstSchedule.SMAIL_USE) {
                            sendMailType = 1;
                        } else {
                            //他ユーザからの登録通知するの場合
                            if (priConf.getSccSmail() == GSConstSchedule.SMAIL_USE) {
                                sendMailType = 0;
                            }
                        }
                    }

                    if (sendMailType == 1) {
                        //出欠確認依頼メール
                        cmnBiz.sendAttendSmail(con__, cntCon__, schMdl, appRootPath,
                                plconf, smailPluginUseFlg, url, 0);
                    } else {
                        //他ユーザからの登録通知メール
                        cmnBiz.sendPlgSmail(con__, cntCon__, schMdl, appRootPath,
                                plconf, smailPluginUseFlg, url);
                    }

                } else {
                    cmnBiz.sendPlgSmail(con__, cntCon__, schMdl, appRootPath,
                            plconf, smailPluginUseFlg, url);
                }
            }
        }

        //施設予約を登録
        int yoyakuSid = -1;
        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con__);
        if (svReserves != null) {
            for (int i = 0; i < svReserves.length; i++) {
                yoyakuSid = (int) cntCon__.getSaibanNumber(
                        GSConstReserve.SBNSID_RESERVE,
                        GSConstReserve.SBNSID_SUB_YOYAKU,
                        userSid);
                RsvSisYrkModel yrkParam = new RsvSisYrkModel();
                yrkParam.setRsySid(yoyakuSid);
                yrkParam.setRsdSid(Integer.parseInt(svReserves[i]));
                String moku = NullDefault.getString(paramMdl.getSch040Title(), "");
                yrkParam.setRsyMok(moku);
                yrkParam.setRsyFrDate(frDate);
                yrkParam.setRsyToDate(toDate);
                yrkParam.setRsyBiko(NullDefault.getString(paramMdl.getSch040Value(), ""));
                yrkParam.setRsyAuid(userSid);
                yrkParam.setRsyAdate(now);
                yrkParam.setRsyEuid(userSid);
                yrkParam.setRsyEdate(now);
                yrkParam.setScdRsSid(scdResSid);
                yrkParam.setRsyEdit(
                        NullDefault.getInt(
                                paramMdl.getSch040Edit(), GSConstSchedule.EDIT_CONF_NONE));

                //承認状況
                RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
                rsvCmnBiz.setSisYrkApprData(con__,  yrkParam.getRsdSid(), yrkParam, userSid);
                yrkDao.insertNewYoyaku(yrkParam);


                //施設予約区分別情報を登録（スケジュールからの場合は全て初期値）
                RsvSisDataDao dataDao = new RsvSisDataDao(con__);
                Rsv070Model mdl = dataDao.getPopUpSisetuData(Integer.parseInt(svReserves[i]));
                if (mdl != null) {

                    if (RsvCommonBiz.isRskKbnRegCheck(mdl.getRskSid())) {
                        RsvCommonBiz rsvBiz = new RsvCommonBiz();
                        RsvSisKyrkModel kyrkMdl =
                                rsvBiz.getSisKbnInitData(
                                        con__, reqMdl, mdl.getRskSid(), appRootPath);
                        kyrkMdl.setRsySid(yoyakuSid);
                        kyrkMdl.setRkyAuid(userSid);
                        kyrkMdl.setRkyAdate(now);
                        kyrkMdl.setRkyEuid(userSid);
                        kyrkMdl.setRkyEdate(now);

                        RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con__);
                        kyrkDao.insert(kyrkMdl);
                    }
                }


                sidDataList.add(new int []{yoyakuSid, Integer.parseInt(svReserves[i])});
            }
        }

        //会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴を登録
        __insertSchCompany(paramMdl, scdSidList, scdUserMap, userSid, now);

        return sidDataList;
    }

    /**
     * <br>[機  能] スケジュールを更新します
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl アクションフォーム
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @return sidDataList 予約SID、施設SIDリスト
     * @throws Exception SQL実行時例外
     */
    public ArrayList<int []> updateScheduleDate(RequestModel reqMdl, Sch040ParamModel paramMdl,
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            boolean smailPluginUseFlg) throws Exception {

        //管理者設定を取得
        SchCommonBiz biz = new SchCommonBiz(reqMdl);
        SchAdmConfModel adminConf = biz.getAdmConfModel(con__);
        SchCommonBiz cmnBiz = new SchCommonBiz(reqMdl);

        ArrayList<int []> sidDataList = new ArrayList<int []>();

        String scdSid = paramMdl.getSch010SchSid();
        SchDataModel scdMdl = new SchDataModel();
        UDate now = new UDate();
        UDate frDate = new UDate();
        frDate.setDate(
                Integer.parseInt(paramMdl.getSch040FrYear()),
                Integer.parseInt(paramMdl.getSch040FrMonth()),
                Integer.parseInt(paramMdl.getSch040FrDay())
                );
        frDate.setZeroHhMmSs();

        int frHour = GSConstSchedule.DAY_START_HOUR;
        int frMin = GSConstSchedule.DAY_START_MINUTES;
        int toHour = GSConstSchedule.DAY_END_HOUR;
        int toMin = GSConstSchedule.DAY_END_MINUTES;
        if (paramMdl.getSch040TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            frHour = Integer.parseInt(paramMdl.getSch040FrHour());
            frMin = Integer.parseInt(paramMdl.getSch040FrMin());
            toHour = Integer.parseInt(paramMdl.getSch040ToHour());
            toMin = Integer.parseInt(paramMdl.getSch040ToMin());
            scdMdl.setScdDaily(GSConstSchedule.TIME_EXIST);
        } else {
            scdMdl.setScdDaily(GSConstSchedule.TIME_NOT_EXIST);
        }


        if (frHour != -1 && frMin != -1) {
            frDate.setHour(frHour);
            frDate.setMinute(frMin);
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        }

        UDate toDate = new UDate();
        toDate.setDate(
                Integer.parseInt(paramMdl.getSch040ToYear()),
                Integer.parseInt(paramMdl.getSch040ToMonth()),
                Integer.parseInt(paramMdl.getSch040ToDay())
                );

        if (toHour != -1 && toMin != -1) {
            toDate.setHour(toHour);
            toDate.setMinute(toMin);
            toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            toDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        }

        scdMdl.setScdSid(Integer.parseInt(scdSid));
        scdMdl.setScdFrDate(frDate);
        scdMdl.setScdToDate(toDate);
        scdMdl.setScdBgcolor(Integer.parseInt(paramMdl.getSch040Bgcolor()));
        scdMdl.setScdTitle(paramMdl.getSch040Title());
        scdMdl.setScdValue(paramMdl.getSch040Value());
        scdMdl.setScdBiko(paramMdl.getSch040Biko());
        scdMdl.setScdPublic(
                NullDefault.getInt(paramMdl.getSch040Public(), GSConstSchedule.DSP_PUBLIC));

        scdMdl.setScdAuid(userSid);
        scdMdl.setScdAdate(now);
        scdMdl.setScdEuid(userSid);
        scdMdl.setScdEdate(now);

        //編集区分
        scdMdl.setScdEdit(
                NullDefault.getInt(paramMdl.getSch040Edit(), GSConstSchedule.EDIT_CONF_NONE));

        //出欠確認区分
        if (Integer.parseInt(paramMdl.getSch010SelectUsrKbn()) == GSConstSchedule.USER_KBN_USER) {
            if (paramMdl.getSch040AttendKbn().equals(
                    String.valueOf(GSConstSchedule.ATTEND_KBN_YES))) {
                scdMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_YES);
                scdMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_YES);
                scdMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_YES);
                scdMdl.setScdEdit(GSConstSchedule.EDIT_CONF_OWN);
            } else {
                scdMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_NO);
                scdMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
                scdMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
            }
        } else {
            scdMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_NO);
            scdMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
            scdMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
        }



        SchDataDao schDao = new SchDataDao(con__);
        ScheduleSearchDao ssDao = new ScheduleSearchDao(con__);

        //編集前スケジュール取得
        ScheduleSearchModel oldMdl = getSchData(Integer.parseInt(scdSid), adminConf, con__);
        //拡張登録SID
        int extSid = oldMdl.getSceSid();
        scdMdl.setSceSid(extSid);
        //スケジュール施設予約SID
        int resSid = oldMdl.getScdRsSid();
        scdMdl.setScdRsSid(resSid);
        String[] svReserves = null;
        if (paramMdl.getSch040TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            svReserves = paramMdl.getSvReserveUsers();
        }

        int scdResSid = GSConstSchedule.DF_SCHGP_ID;
        int newScdSid = -1;

        //施設拡張取得(スケジュール情報を削除する前に取得)
        RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con__);
        RsvSisRyrkModel ryrkMdl = null;
        if (paramMdl.getSch040ResBatchRef().equals("1")
                || paramMdl.getSch040TimeKbn().equals(
                        String.valueOf(GSConstSchedule.TIME_NOT_EXIST))) {
            ryrkMdl = ryrkDao.selectFromScdSid(Integer.parseInt(scdSid));
        }

        if (Integer.parseInt(paramMdl.getSch010SelectUsrKbn()) == GSConstSchedule.USER_KBN_USER) {
            //出欠確認する場合
            if (paramMdl.getSch040AttendKbn().equals(
                    String.valueOf(GSConstSchedule.ATTEND_KBN_YES))) {
                //出欠確認する場合は強制的に同時修正する
                paramMdl.setSch040BatchRef("1");
            }
        }

        if (paramMdl.getSch040BatchRef().equals("0")) {
            //同時登録反映無しの場合
            scdMdl.setScdGrpSid(GSConstSchedule.DF_SCHGP_ID);
            scdMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_NO);
            scdMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
            scdMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);

            //施設予約へ反映する場合、新たに採番
            if (paramMdl.getSch040ResBatchRef().equals("1")) {
                if (svReserves != null && svReserves.length > 0) {
                    //スケジュール施設予約SID（施設予約有りの場合）
                    scdResSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                            SaibanModel.SBNSID_SUB_SCH_RES, userSid);
                    scdMdl.setScdRsSid(scdResSid);
                    schDao.updateRsSid(resSid, scdResSid);
                }
            }
            //選択スケジュールを更新
            schDao.updateSchedule(scdMdl);

            //会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴を更新
            __updateSchCompany(paramMdl, Integer.parseInt(scdSid), scdMdl.getScdUsrSid(),
                            scdMdl.getScdEdate(), scdMdl.getScdEuid());

            //ユーザSID
            String usrSid = paramMdl.getSch010SelectUsrSid();
            //URL取得
            String url = __createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                                               String.valueOf(scdSid), usrSid,
                                               paramMdl);
            cmnBiz.sendPlgSmail(
                    con__, cntCon__, scdMdl, appRootPath, plconf, smailPluginUseFlg, url);

            //編集前のデータで出欠確認を行っていた場合、リレーションで紐付いている
            //回答側のスケジュールの出欠確認データをリセットする
            if (oldMdl.getScdAttendKbn() == GSConstSchedule.ATTEND_KBN_YES) {
                //グループ
                if (oldMdl.getScdGrpSid() != GSConstSchedule.DF_SCHGP_ID) {
                    schDao.updateAttendReset(oldMdl.getScdGrpSid());
                }
            }

        } else {
            //同時登録ユーザへ反映更新
            //新スケジュールを登録
            int scdGpSid = GSConstSchedule.DF_SCHGP_ID;
            List<Integer> newScdSidList = new ArrayList<Integer>();
            Map<Integer, Integer> scdUserMap = new HashMap<Integer, Integer>();

            //SID採番
            newScdSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH, userSid);
            scdMdl.setScdSid(newScdSid);
            scdMdl.setScdUsrSid(Integer.parseInt(paramMdl.getSch010SelectUsrSid()));
            scdMdl.setScdUsrKbn(Integer.parseInt(paramMdl.getSch010SelectUsrKbn()));

            String[] svUsers = paramMdl.getSv_users();
            int addUserSid = -1;
            //スケジュールグループSID（同時登録有りの場合）
            if (svUsers != null && svUsers.length > 0) {
                //スケジュールグループSID（同時登録有りの場合）
                scdGpSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                        SaibanModel.SBNSID_SUB_SCH_GP, userSid);
            }
            scdMdl.setScdGrpSid(scdGpSid);

            //施設予約へ反映する場合、新たに採番
            if (paramMdl.getSch040ResBatchRef().equals("1")) {
                if (svReserves != null && svReserves.length > 0) {
                    //スケジュール施設予約SID（施設予約有りの場合）
                    scdResSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                            SaibanModel.SBNSID_SUB_SCH_RES, userSid);
                    scdMdl.setScdRsSid(scdResSid);
                }
            }

            //登録
            schDao.insert(scdMdl);
            newScdSidList.add(scdMdl.getScdSid());
            scdUserMap.put(scdMdl.getScdSid(), scdMdl.getScdUsrSid());

            //ユーザSID
            String usrSid = paramMdl.getSch010SelectUsrSid();

            //URL取得
            String url = __createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                                               String.valueOf(newScdSid), usrSid,
                                               paramMdl);
            cmnBiz.sendPlgSmail(
                    con__, cntCon__, scdMdl, appRootPath, plconf, smailPluginUseFlg, url);

            //同時登録分
            if (svUsers != null) {
                if (Integer.parseInt(paramMdl.getSch010SelectUsrKbn())
                        == GSConstSchedule.USER_KBN_USER) {
                    //出欠確認区分「確認する」の場合
                    Map<Integer, Integer> map = null;
                    if (paramMdl.getSch040AttendKbn().equals(
                            String.valueOf(GSConstSchedule.ATTEND_KBN_YES))) {
                        //各ユーザ出欠確認の回答データを引き継ぐため、削除前のスケジュールデータより
                        //ユーザSID：出欠パラメータを取得する
                        map = schDao.selectAttendData(Integer.valueOf(scdSid));
                    }

                    for (int i = 0; i < svUsers.length; i++) {

                        newScdSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                                SaibanModel.SBNSID_SUB_SCH, userSid);
                        addUserSid = Integer.parseInt(svUsers[i]);
                        url = __createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                                String.valueOf(newScdSid),
                                String.valueOf(addUserSid),
                                paramMdl);
                        scdMdl.setScdSid(newScdSid);
                        scdMdl.setScdUsrSid(addUserSid);
                        scdMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_USER);

                        //出欠確認をする場合
                        if (paramMdl.getSch040AttendKbn().equals(
                                String.valueOf(GSConstSchedule.ATTEND_KBN_YES))) {

                            scdMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_YES);
                            scdMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
                            //既にスケジュールのデータがあった場合（回答内容を引き継ぐ）
                            if (map.containsKey(addUserSid)) {
                                int attendKbn = map.get(addUserSid);
                                scdMdl.setScdAttendAns(attendKbn);

                                //スケジュールデータ無かった場合（初期値）
                            } else {
                                scdMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
                            }

                            //出欠確認をしない場合
                        } else {
                            scdMdl.setScdAttendKbn(GSConstSchedule.ATTEND_KBN_NO);
                            scdMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
                            scdMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
                        }

                        schDao.insert(scdMdl);
                        newScdSidList.add(scdMdl.getScdSid());
                        scdUserMap.put(scdMdl.getScdSid(), scdMdl.getScdUsrSid());


                        //通知メールを送る
                        //出欠確認をする場合
                        if (paramMdl.getSch040AttendKbn().equals(
                                String.valueOf(GSConstSchedule.ATTEND_KBN_YES))) {
                            //再通知をする場合
                            if (paramMdl.getSch040EditMailSendKbn()
                                    == GSConstSchedule.ATTEND_UPDATE_MAIL_YES) {
                                log__.debug("再通知メールの送信");
                                //出欠確認依頼メール
                                cmnBiz.sendAttendSmail(con__, cntCon__, scdMdl, appRootPath,
                                        plconf, smailPluginUseFlg, url, 1);
                            } else {
                                //スケジュール編集画面の表示モードが通常スケジュールの場合、メール送る
                                if (String.valueOf(GSConstSchedule.EDIT_DSP_MODE_NORMAL)
                                        .equals(paramMdl.getSch040EditDspMode())) {
                                    //出欠確認依頼メール
                                    cmnBiz.sendAttendSmail(con__, cntCon__, scdMdl, appRootPath,
                                            plconf, smailPluginUseFlg, url, 0);
                                }
                            }
                        } else {
                            cmnBiz.sendPlgSmail(con__, cntCon__, scdMdl,
                                    appRootPath, plconf, smailPluginUseFlg, url);
                        }
                    }
                }
            }

            //会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴を登録
            __insertSchCompany(paramMdl, newScdSidList, scdUserMap,
                            scdMdl.getScdEuid(), scdMdl.getScdEdate());

            //旧スケジュールを削除
            //同時登録済みスケジュールSIDリスト
            ArrayList<Integer> scds = ssDao.getScheduleUsrs(
                    Integer.parseInt(scdSid),
                    userSid,
                    adminConf.getSadCrange(),
                    GSConstSchedule.SSP_AUTHFILTER_EDIT
                    );
            schDao.delete(Integer.parseInt(scdSid));
            schDao.delete(scds);

            //変更前スケジュールの会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴を削除
            List<Integer> deleteScdSidList = new ArrayList<Integer>();
            deleteScdSidList.add(Integer.parseInt(scdSid));
            deleteScdSidList.addAll(scds);
            deleteSchCompany(con__, deleteScdSidList, paramMdl.getSch040contact());
        }

        int rsrSid = -1;
        //施設予約への更新判定 時間指定無しの場合は更新
        if (paramMdl.getSch040ResBatchRef().equals("1")
                || paramMdl.getSch040TimeKbn().equals(
                        String.valueOf(GSConstSchedule.TIME_NOT_EXIST))) {

            if (ryrkMdl != null) {
                rsrSid = ryrkMdl.getRsrRsid();
            }
            //施設予約を登録
            int yoyakuSid = -1;
            RsvSisYrkDao yrkDao = new RsvSisYrkDao(con__);
            if (svReserves != null) {
                for (int i = 0; i < svReserves.length; i++) {
                    yoyakuSid = (int) cntCon__.getSaibanNumber(
                            GSConstReserve.SBNSID_RESERVE,
                            GSConstReserve.SBNSID_SUB_YOYAKU,
                            userSid);


                    RsvSisYrkModel yrkParam = new RsvSisYrkModel();
                    yrkParam.setRsySid(yoyakuSid);
                    yrkParam.setRsdSid(Integer.parseInt(svReserves[i]));
                    String moku = NullDefault.getString(paramMdl.getSch040Title(), "");
                    yrkParam.setRsyMok(moku);
                    yrkParam.setRsyFrDate(frDate);
                    yrkParam.setRsyToDate(toDate);
                    yrkParam.setRsyBiko(NullDefault.getString(paramMdl.getSch040Value(), ""));
                    yrkParam.setRsyAuid(userSid);
                    yrkParam.setRsyAdate(now);
                    yrkParam.setRsyEuid(userSid);
                    yrkParam.setRsyEdate(now);
                    yrkParam.setScdRsSid(scdResSid);
                    //施設拡張SID
                    yrkParam.setRsrRsid(rsrSid);

                    yrkParam.setRsyEdit(
                            NullDefault.getInt(paramMdl.getSch040Edit(),
                                               GSConstSchedule.EDIT_CONF_NONE));

                    //承認状況
                    RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
                    rsvCmnBiz.setSisYrkApprData(con__,  yrkParam.getRsdSid(), yrkParam, userSid);

                    yrkDao.insertNewYoyaku(yrkParam);

                    sidDataList.add(new int []{yoyakuSid, Integer.parseInt(svReserves[i])});

                    //施設予約区分別情報を登録（スケジュールからの場合は全て初期値）
                    RsvSisDataDao dataDao = new RsvSisDataDao(con__);
                    Rsv070Model mdl = dataDao.getPopUpSisetuData(Integer.parseInt(svReserves[i]));
                    if (mdl != null) {

                        if (RsvCommonBiz.isRskKbnRegCheck(mdl.getRskSid())) {
                            RsvCommonBiz rsvBiz = new RsvCommonBiz();
                            RsvSisKyrkModel kyrkMdl =
                                    rsvBiz.getSisKbnInitData(
                                            con__, reqMdl, mdl.getRskSid(), appRootPath);
                            kyrkMdl.setRsySid(yoyakuSid);
                            kyrkMdl.setRkyAuid(userSid);
                            kyrkMdl.setRkyAdate(now);
                            kyrkMdl.setRkyEuid(userSid);
                            kyrkMdl.setRkyEdate(now);

                            RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con__);
                            kyrkDao.insert(kyrkMdl);
                        }
                    }
                }
            }

            if (resSid > -1) {
                //削除するの施設予約SIDを取得する
                RsvSisYrkDao rsyDao = new RsvSisYrkDao(con__);
                ArrayList<Integer> rsySidList = rsyDao.getScheduleRserveSids(resSid);
                //施設予約区分別情報を削除
                if (rsySidList != null && rsySidList.size() > 0) {
                    RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con__);
                    kyrkDao.delete(rsySidList);
                }

                //旧施設予約情報を削除
                yrkDao.deleteScdRsSid(resSid);
            }

            //ひも付いている施設予約情報が無くなった場合、予約拡張データを削除
            if (rsrSid > -1 && yrkDao.getYrkDataCnt(rsrSid) < 1) {
                //件数取得し0件の場合
                ryrkDao.delete(rsrSid);
                //施設予約拡張区分別情報削除
                RsvSisKryrkDao kryrkDao = new RsvSisKryrkDao(con__);
                kryrkDao.delete(rsrSid);
            }
        }

        return sidDataList;
    }


    /**
     * <br>[機  能] スケジュールを更新します(出欠回答者の場合)
     * <br>[解  説] 回答区分、更新日時のみ変更
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl アクションフォーム
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @throws Exception SQL実行時例外
     */
    public void updateScheduleDateAns(RequestModel reqMdl, Sch040ParamModel paramMdl,
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            boolean smailPluginUseFlg) throws Exception {
        SchDataDao dao  = new SchDataDao(con__);
        SchDataModel scdMdl = new SchDataModel();

        //スケジュールSID
        int schSid = Integer.valueOf(paramMdl.getSch010SchSid());

        //更新前 未回答数取得
        int cntBefore = dao.countAnsNone(schSid);

        UDate date = new UDate();

        //更新モデルの作成
        scdMdl.setScdSid(schSid);
        scdMdl.setScdAttendAns(
                NullDefault.getInt(
                        paramMdl.getSch040AttendAnsKbn(), GSConstSchedule.ATTEND_ANS_NONE));
        scdMdl.setScdEuid(userSid);
        scdMdl.setScdEdate(date);

        dao.updateAnsKbn(scdMdl);

        //更新後 未回答数取得
        int cntAfter = dao.countAnsNone(schSid);
        //出欠確認 依頼者に更新完了通知を行う
        //未回答件数が無い場合、且つカウンタが1→0になった場合
        if (cntAfter == 0 && cntAfter != cntBefore) {
            boolean delFlg = dao.isCheckAttendAuSchDelete(schSid);
            //依頼者のスケジュールが削除された場合、完了通知メールを送信しない
            if (!delFlg) {
                //回答者のスケジュールSIDより紐付いている出欠確認 依頼者のスケジュールデータを取得する
                SchDataModel parSchMdl = dao.getAttendRegistSch(schSid);
                if (parSchMdl != null) {
                    SchCommonBiz cmnBiz = new SchCommonBiz(reqMdl);
                    //URL取得
                    String url = __createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                            String.valueOf(parSchMdl.getScdSid()),
                            String.valueOf(parSchMdl.getScdUsrSid()),
                            paramMdl);
                    cmnBiz.sendAttendCompSmail(
                            con__, cntCon__, parSchMdl, appRootPath,
                            plconf, smailPluginUseFlg, url, date);
                }
            }
        }

    }


    /**
     * <br>[機  能] スケジュール情報を削除(物理削除)します
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @param con コネクション
     * @return 削除レコード件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteSchedule(int scdSid, Connection con) throws SQLException {

        int cnt = 0;
        SchDataDao scdDao = new SchDataDao(con);
        cnt = scdDao.delete(scdSid);

        List<Integer> scds = new ArrayList<Integer>();
        scds.add(scdSid);
        deleteSchCompany(con, scds, 0);

        return cnt;
    }

    /**
     * <br>[機  能] スケジュール情報を削除(物理削除)します
     * <br>[解  説]
     * <br>[備  考]
     * @param scds 同時登録スケジュールSID
     * @param con コネクション
     * @return 削除レコード件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteSchedule(ArrayList<Integer> scds, Connection con)
    throws SQLException {

        int cnt = 0;
        SchDataDao scdDao = new SchDataDao(con);
        cnt = scdDao.delete(scds);

        deleteSchCompany(con, scds, 0);

        return cnt;
    }

    /**
     * <br>[機  能] 施設予約情報を削除(物理削除)します
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @param con コネクション
     * @return 削除レコード件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteReserve(int scdSid, Connection con) throws SQLException {

        int cnt = 0;
        //管理者設定を取得
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = biz.getAdmConfModel(con);
        ScheduleSearchModel oldMdl = getSchData(scdSid, adminConf, con);
        RsvSisYrkDao rsDao = new RsvSisYrkDao(con);
        if (oldMdl.getScdRsSid() > -1) {

            //削除するの施設予約SIDを取得する
            RsvSisYrkDao rsyDao = new RsvSisYrkDao(con__);
            ArrayList<Integer> rsySidList = rsyDao.getScheduleRserveSids(oldMdl.getScdRsSid());
            //施設予約区分別情報を削除
            if (rsySidList != null && rsySidList.size() > 0) {
                RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con__);
                kyrkDao.delete(rsySidList);
            }

            cnt = rsDao.deleteScdRsSid(oldMdl.getScdRsSid());
        }
        return cnt;
    }

    /**
     * <br>[機  能] 拡張情報をフォームへ設定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param sessionUserSid ユーザSID
     * @param paramMdl Sch040ParamModel
     * @param model 拡張情報
     * @param con コネクション
     * @param cmd コマンド
     * @throws SQLException SQL実行時例外
     */
    private void __setScheduleExSearchModelToForm(
            int sessionUserSid,
            Sch040ParamModel paramMdl,
            ScheduleExSearchModel model,
            Connection con,
            String cmd) throws SQLException {
        String uid = paramMdl.getSch010SelectUsrSid();
        //拡張SID
        int sceSid = model.getSceSid();

        SchCommonBiz schCmnBiz = new SchCommonBiz();
        RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();

        paramMdl.setSch041ExtSid(String.valueOf(sceSid));
        //拡張区分
        paramMdl.setSch041ExtKbn(
                NullDefault.getString(paramMdl.getSch041ExtKbn(),
                        String.valueOf(model.getSceKbn())));
        //週
        paramMdl.setSch041Week(
                NullDefault.getString(paramMdl.getSch041Week(),
                        String.valueOf(model.getSceWeek())));
        //日
        paramMdl.setSch041Day(
                NullDefault.getString(paramMdl.getSch041Day(),
                        String.valueOf(model.getSceDay())));
        //毎年 日
        if (model.getSceDayOfYearly() > 0) {
            paramMdl.setSch041DayOfYearly(
                    NullDefault.getString(paramMdl.getSch041DayOfYearly(),
                            String.valueOf(model.getSceDayOfYearly())));
        }
        //毎年 月
        if (model.getSceMonthOfYearly() > 0) {
            paramMdl.setSch041MonthOfYearly(
                    NullDefault.getString(paramMdl.getSch041MonthOfYearly(),
                    String.valueOf(model.getSceMonthOfYearly())));
        }

        //振替区分
        paramMdl.setSch041TranKbn(
                NullDefault.getString(paramMdl.getSch041TranKbn(),
                        String.valueOf(model.getSceTranKbn())));
        //設定期間
        paramMdl.setSch041FrYear(
                NullDefault.getString(paramMdl.getSch041FrYear(),
                String.valueOf(model.getSceDateFr().getYear())));
        paramMdl.setSch041FrMonth(
                NullDefault.getString(paramMdl.getSch041FrMonth(),
                String.valueOf(model.getSceDateFr().getMonth())));
        paramMdl.setSch041FrDay(
                NullDefault.getString(paramMdl.getSch041FrDay(),
                String.valueOf(model.getSceDateFr().getIntDay())));
        paramMdl.setSch041ToYear(
                NullDefault.getString(paramMdl.getSch041ToYear(),
                String.valueOf(model.getSceDateTo().getYear())));
        paramMdl.setSch041ToMonth(
                NullDefault.getString(paramMdl.getSch041ToMonth(),
                String.valueOf(model.getSceDateTo().getMonth())));
        paramMdl.setSch041ToDay(
                NullDefault.getString(paramMdl.getSch041ToDay(),
                String.valueOf(model.getSceDateTo().getIntDay())));
        //時間指定
        if (paramMdl.getSch040InitFlg().equals(String.valueOf(GSConstSchedule.INIT_FLG))) {
            paramMdl.setSch041TimeKbn(String.valueOf(model.getSceDaily()));
        }
        //時間
        if (paramMdl.getSch041TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            paramMdl.setSch041FrHour(
                    NullDefault.getString(paramMdl.getSch041FrHour(),
                    String.valueOf(model.getSceTimeFr().getIntHour())));
            paramMdl.setSch041FrMin(
                    NullDefault.getString(paramMdl.getSch041FrMin(),
                    String.valueOf(model.getSceTimeFr().getIntMinute())));
            paramMdl.setSch041ToHour(
                    NullDefault.getString(paramMdl.getSch041ToHour(),
                    String.valueOf(model.getSceTimeTo().getIntHour())));
            paramMdl.setSch041ToMin(
                    NullDefault.getString(paramMdl.getSch041ToMin(),
                    String.valueOf(model.getSceTimeTo().getIntMinute())));
        } else {
            paramMdl.setSch041FrHour(GSConstSchedule.TIME_NOT_SELECT);
            paramMdl.setSch041FrMin(GSConstSchedule.TIME_NOT_SELECT);
            paramMdl.setSch041ToHour(GSConstSchedule.TIME_NOT_SELECT);
            paramMdl.setSch041ToMin(GSConstSchedule.TIME_NOT_SELECT);
        }

        //タイトル
        paramMdl.setSch041Title(
                NullDefault.getString(paramMdl.getSch041Title(),
                model.getSceTitle()));
        //タイトルカラー
        int iniBgcolor = GSConstSchedule.DF_BG_COLOR;
        if (model.getSceBgcolor() > GSConstSchedule.DF_BG_COLOR) {
            iniBgcolor = schCmnBiz.getUserColor(model.getSceBgcolor(), con);

        }
        paramMdl.setSch041Bgcolor(
                NullDefault.getString(paramMdl.getSch041Bgcolor(), String.valueOf(iniBgcolor)));
        //内容
        paramMdl.setSch041Value(
                NullDefault.getString(paramMdl.getSch041Value(),
                model.getSceValue()));
        //備考
        paramMdl.setSch041Biko(
                NullDefault.getString(paramMdl.getSch041Biko(),
                model.getSceBiko()));
        //公開
        paramMdl.setSch041Public(
                NullDefault.getString(paramMdl.getSch041Public(),
                String.valueOf(model.getScePublic())));
        //編集権限
        paramMdl.setSch041Edit(
                NullDefault.getString(paramMdl.getSch041Edit(),
                String.valueOf(model.getSceEdit())));

        //曜日
        if (paramMdl.getSch041Dweek() == null
        || paramMdl.getSch041Dweek().length <= 0) {
            __setDayWeekToForm(paramMdl, model);
        }
        //施設グループ
        //施設予約個人設定を取得
        RsvUserModel rsvUserConf = rsvCmnBiz.getRevUserModel(sessionUserSid, con);
        int dfReservGpSid = GSConstReserve.COMBO_DEFAULT_VALUE;
        if (rsvUserConf != null) {
            dfReservGpSid = rsvUserConf.getRsgSid();
        }
        paramMdl.setSch041ReserveGroupSid(
                NullDefault.getString(paramMdl.getSch041ReserveGroupSid(),
                        String.valueOf(dfReservGpSid)));
        //同時登録ユーザ
        if (paramMdl.getSch041SvUsers() == null
                || paramMdl.getSch041SvUsers().length == 0) {
            //除外するユーザSIDを設定
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            usrSids.add(new Integer(GSConstUser.SID_ADMIN));
            usrSids.add(new Integer(uid));
            //追加済みユーザSID
            if (paramMdl.getSch041SvUsers() == null
                 || paramMdl.getSch041SvUsers().length == 0) {
                //拡張登録で同時登録されたユーザの一覧を取得
                paramMdl.setSch041SvUsers(getSaveUsersForDbEx(model.getUsrInfList()));
            }
        }

        //同時登録施設
        if ((paramMdl.getSch041SvReserve() == null || paramMdl.getSch041SvReserve().length == 0)
                && cmd.equals(GSConstSchedule.CMD_EDIT) && model.getSceSid() > 0) {
            //拡張登録で同時登録された施設の一覧を取得
            paramMdl.setSch041SvReserve(getSaveReserveForDbEx(model.getSceSid(), con));
        }

        //会社情報、アドレス帳情報を設定
        SchExcompanyDao exCompanyDao = new SchExcompanyDao(con);
        List<SchExcompanyModel> exCompanyList = exCompanyDao.select(model.getSceSid());
        if (!exCompanyList.isEmpty()) {

            String[] acoSidList = new String[exCompanyList.size()];
            String[] abaSidList = new String[exCompanyList.size()];
            for (int index = 0; index < exCompanyList.size(); index++) {
                SchExcompanyModel exCompanyModel = exCompanyList.get(index);
                acoSidList[index] = String.valueOf(exCompanyModel.getAcoSid());
                abaSidList[index] = String.valueOf(exCompanyModel.getAbaSid());
            }

            paramMdl.setSch041CompanySid(acoSidList);
            paramMdl.setSch041CompanyBaseSid(abaSidList);
        }

        SchExaddressDao exAddressDao = new SchExaddressDao(con);
        List<SchExaddressModel> exAddressList = exAddressDao.select(model.getSceSid());
        if (!exCompanyList.isEmpty()) {
            String[] adrSidList = new String[exAddressList.size()];
            for (int index = 0; index < exAddressList.size(); index++) {
                adrSidList[index] = String.valueOf(exAddressList.get(index).getAdrSid());
            }
            paramMdl.setSch041AddressId(adrSidList);

            Sch040Dao sch040Dao = new Sch040Dao(con);
            if (sch040Dao.isExistAdrContact(sceSid)) {
                paramMdl.setSch041contact(1);
            }
        }
    }

    /**
     * <br>[機  能] 曜日指定パラメータを設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch040ParamModel
     * @param model ScheduleExSearchModel
     */
    private void __setDayWeekToForm(Sch040ParamModel paramMdl, ScheduleExSearchModel model) {

        ArrayList<String> dWeekList = new ArrayList<String>();
        if (model.getSceDweek1() == 1) {
            dWeekList.add("1");
        }
        if (model.getSceDweek2() == 1) {
            dWeekList.add("2");
        }
        if (model.getSceDweek3() == 1) {
            dWeekList.add("3");
        }
        if (model.getSceDweek4() == 1) {
            dWeekList.add("4");
        }
        if (model.getSceDweek5() == 1) {
            dWeekList.add("5");
        }
        if (model.getSceDweek6() == 1) {
            dWeekList.add("6");
        }
        if (model.getSceDweek7() == 1) {
            dWeekList.add("7");
        }
        paramMdl.setSch041Dweek((String[]) dWeekList.toArray(new String[dWeekList.size()]));
    }

    /**
     * <br>[機  能] スケジュール一般登録確認URLを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param cmd 処理モード
     * @param sch010SchSid スケジュールSID
     * @param usrSid ユーザーSID
     * @param paramMdl Sch040ParamModel
     * @return スケジュール一般登録確認URL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    private String __createScheduleUrlDefo(String cmd,
                                          String sch010SchSid, String usrSid,
                                          Sch040ParamModel paramMdl)
    throws UnsupportedEncodingException {
        String scheduleUrl = null;

        //URLを作成
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

    /**
     * <br>[機  能] 会社情報、アドレス帳情報を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch040ParamModel
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    public void setCompanyData(
            Sch040ParamModel paramMdl, Connection con, int userSid, RequestModel reqMdl)
    throws SQLException {

        Sch040Dao dao040 = new Sch040Dao(con);
        String[] acoSidList = paramMdl.getSch040CompanySid();
        String[] abaSidList = paramMdl.getSch040CompanyBaseSid();
        List<String> companyIdList = new ArrayList<String>();
        Map<String, Sch040CompanyModel> companyMap = new HashMap<String, Sch040CompanyModel>();

        Sch040CompanyModel noCompanyModel = new Sch040CompanyModel();
        GsMessage gsMsg = new GsMessage(reqMdl);
        //会社登録無し
        String textCmpDataNone = gsMsg.getMessage("schedule.src.87");
        noCompanyModel.setCompanyName(textCmpDataNone);
        noCompanyModel.setCompanyAddress(null);
        noCompanyModel.setCompanySid(0);
        noCompanyModel.setCompanyBaseSid(0);
        companyMap.put("0:0", noCompanyModel);

        if (acoSidList != null && abaSidList != null) {

            for (int index = 0; index < acoSidList.length; index++) {
                int acoSid = Integer.parseInt(acoSidList[index]);
                int abaSid = Integer.parseInt(abaSidList[index]);

                Sch040CompanyModel companyData = createCompanyData(dao040, acoSid, abaSid);
                if (companyData != null) {
                    String companyId = acoSid + ":" + abaSid;
                    companyMap.put(companyId, companyData);
                    companyIdList.add(companyId);
                }
            }
        }

        //アドレス情報を取得
        Sch040Dao sch040Dao = new Sch040Dao(con);
        List<Sch040AddressModel> addressList
                    = sch040Dao.getAddressList(con, paramMdl.getSch040AddressId(), userSid);
        List<String> addressSidList = new ArrayList<String>();
        if (addressList != null) {

            for (Sch040AddressModel adrData : addressList) {
                String companyId = adrData.getCompanySid() + ":" + adrData.getCompanyBaseSid();
                Sch040CompanyModel companyData = companyMap.get(companyId);
                if (companyData == null) {
                    companyData = createCompanyData(dao040,
                                                    adrData.getCompanySid(),
                                                    adrData.getCompanyBaseSid());
                    companyMap.put(companyId, companyData);
                    companyIdList.add(companyId);
                }

                addressSidList.add(String.valueOf(adrData.getAdrSid()));
                companyData.getAddressDataList().add(adrData);
                companyMap.put(companyId, companyData);
            }
        }

        String[] companySidArray = new String[companyIdList.size()];
        String[] companyBaseSidArray = new String[companyIdList.size()];
        List<Sch040CompanyModel> companyList = new ArrayList<Sch040CompanyModel>();

        if (!companyMap.get("0:0").getAddressDataList().isEmpty()) {
            companyList.add(companyMap.get("0:0"));
        }

        for (int index = 0; index < companyIdList.size(); index++) {
            String companyId = companyIdList.get(index);
            companySidArray[index] = companyId.split(":")[0];
            companyBaseSidArray[index] = companyId.split(":")[1];
            companyList.add(companyMap.get(companyId));
        }

        paramMdl.setSch040CompanySid(companySidArray);
        paramMdl.setSch040CompanyBaseSid(companyBaseSidArray);
        paramMdl.setSch040AddressId(addressSidList.toArray(new String[addressSidList.size()]));
        paramMdl.setSch040CompanyList(companyList);
    }

    /**
     * <br>[機  能] 会社情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param dao040 スケジュール登録画面DAO
     * @param acoSid 会社SID
     * @param abaSid 会社拠点SID
     * @return 会社情報
     * @throws SQLException SQL実行時例外
     */
    public Sch040CompanyModel createCompanyData(Sch040Dao dao040, int acoSid, int abaSid)
    throws SQLException {

        Sch040CompanyModel companyData = null;

        Sch040DBCompanyModel model = dao040.getCompanyData(acoSid);
        if (model != null) {
            companyData = new Sch040CompanyModel();

            String companyName = model.getAcoName();
            String companyNameSearch = model.getAcoName();
            String companyaddress = null;

            Sch040DBCompanyBaseModel baseModel = dao040.getCompanyBaseData(abaSid);
            if (baseModel != null) {
                companyName += " " + baseModel.getAbaName();
                companyaddress = baseModel.getAbaAddr1();
                if (!StringUtil.isNullZeroStringSpace(baseModel.getAbaAddr2())) {
                    companyaddress += baseModel.getAbaAddr2();
                }
                companyaddress = StringUtil.toSingleCortationEscape(companyaddress);
            }

            companyNameSearch = StringUtil.toSingleCortationEscape(companyNameSearch);

            companyData.setCompanySid(acoSid);
            companyData.setCompanyBaseSid(abaSid);
            companyData.setCompanyName(companyName);
            companyData.setCompanyNameSearch(companyNameSearch);
            companyData.setCompanyAddress(companyaddress);
        }

        return companyData;
    }

    /**
     * <br>[機  能] 会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch040ParamModel
     * @param scdSidList スケジュールSID
     * @param scdUserMap スケジュールSIDとユーザSIDのMapping
     * @param sessionUserSid セッションユーザSID
     * @param date 更新日付
     * @throws SQLException SQL実行時例外
     */
    private void __insertSchCompany(Sch040ParamModel paramMdl,
                                    List<Integer> scdSidList,
                                    Map<Integer, Integer> scdUserMap,
                                    int sessionUserSid, UDate date)
    throws SQLException {

        //会社情報Mappingを登録
        List<SchCompanyModel> companyList = createCompanyModel(scdSidList,
                                                            paramMdl.getSch040CompanySid(),
                                                            paramMdl.getSch040CompanyBaseSid(),
                                                            sessionUserSid, date);
        if (companyList != null) {
            SchCompanyDao companyDao = new SchCompanyDao(con__);
            for (SchCompanyModel companyModel : companyList) {
                companyDao.insert(companyModel);
            }
        }

        //アドレス帳情報Mapping、コンタクト履歴を登録する
        String[] addressId = paramMdl.getSch040AddressId();
        List<SchAddressModel> addressList = createAddressModel(scdSidList, addressId,
                                                            sessionUserSid, date);
        if (addressList != null) {
            SchAddressDao addressDao = new SchAddressDao(con__);
            Sch040Dao dao040 = new Sch040Dao(con__);
            boolean contactFlg = (paramMdl.getSch040contact() == 1);

            String contactTitle = paramMdl.getSch040Title();
            String[] startDate = new String[5];
            startDate[0] = paramMdl.getSch040FrYear();
            startDate[1] = paramMdl.getSch040FrMonth();
            startDate[2] = paramMdl.getSch040FrDay();
            startDate[3] = paramMdl.getSch040FrHour();
            startDate[4] = paramMdl.getSch040FrMin();
            String[] endDate = new String[5];
            endDate[0] = paramMdl.getSch040ToYear();
            endDate[1] = paramMdl.getSch040ToMonth();
            endDate[2] = paramMdl.getSch040ToDay();
            endDate[3] = paramMdl.getSch040ToHour();
            endDate[4] = paramMdl.getSch040ToMin();

            int adcGrpSid = -1;
            Map<Integer, Integer> contactMap = new HashMap<Integer, Integer>();
            if (contactFlg && addressId != null) {
                //アドレス帳情報が複数選択されている場合はコンタクト履歴グループSIDを採番する
                if (addressId.length > 1) {
                    adcGrpSid = (int) cntCon__.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                            GSConst.SBNSID_SUB_CONTACT_GRP,
                                                            sessionUserSid);
                }

                //コンタクト履歴の登録
                for (String adrSid : addressId) {
                    Sch040ContactModel contactMdl
                            = createContactModel(Integer.parseInt(adrSid), adcGrpSid,
                                                contactTitle, startDate, endDate,
                                                sessionUserSid, date);
                    dao040.insertContact(contactMdl);

                    contactMap.put(contactMdl.getAdrSid(), contactMdl.getAdcSid());
                }

            }

            for (SchAddressModel adrMdl : addressList) {
                if (contactFlg) {
                    adrMdl.setAdcSid(contactMap.get(adrMdl.getAdrSid()));
                }

                addressDao.insert(adrMdl);
            }
        }
    }

    /**
     * <br>[機  能] 会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch040ParamModel
     * @param scdSid スケジュールSID
     * @param userSid 登録/更新ユーザSID
     * @param date 更新日付
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __updateSchCompany(Sch040ParamModel paramMdl, int scdSid,
                                    int userSid, UDate date, int sessionUserSid)
    throws SQLException {

        List<Integer> scdSidList = new ArrayList<Integer>();
        scdSidList.add(scdSid);

        deleteSchCompany(con__, scdSidList, paramMdl.getSch040contact());

        Map<Integer, Integer> scdUserMap = new HashMap<Integer, Integer>();
        scdUserMap.put(scdSid, userSid);
        __insertSchCompany(paramMdl, scdSidList, scdUserMap, sessionUserSid, date);
    }

    /**
     * <br>[機  能] 会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param scdSidList スケジュールSID
     * @param contactFlg コンタクト履歴変更有無
     * @throws SQLException SQL実行時例外
     */
    public void deleteSchCompany(Connection con, List<Integer> scdSidList, int contactFlg)
    throws SQLException {

        SchCompanyDao companyDao = new SchCompanyDao(con);
        companyDao.delete(scdSidList);

        Sch040Dao dao040 = new Sch040Dao(con);
        SchAddressDao addressDao = new SchAddressDao(con);
        for (Integer scdSid : scdSidList) {
            if (contactFlg == 1) {
                dao040.deleteScheduleContact(con, scdSid);
            }
            addressDao.delete(scdSid);
        }
    }

    /**
     * <br>[機  能] スケジュール-会社情報Mapping Modelを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSidList スケジュールSID
     * @param acoSidList 会社SID
     * @param abaSidList 会社拠点SID
     * @param userSid セッションユーザSID
     * @param date 登録/更新日付
     * @return スケジュール-会社情報Mapping Model
     */
    public List<SchCompanyModel> createCompanyModel(List<Integer> scdSidList,
                                                    String[] acoSidList,
                                                    String[] abaSidList,
                                                    int userSid, UDate date) {

        List<SchCompanyModel> companyList = null;

        if (acoSidList != null && abaSidList != null) {

            companyList = new ArrayList<SchCompanyModel>();

            for (int scdSid : scdSidList) {
                for (int index = 0; index < acoSidList.length; index++) {
                    SchCompanyModel companyModel = new SchCompanyModel();
                    companyModel.setScdSid(scdSid);
                    companyModel.setAcoSid(Integer.parseInt(acoSidList[index]));
                    companyModel.setAbaSid(Integer.parseInt(abaSidList[index]));
                    companyModel.setSccAuid(userSid);
                    companyModel.setSccAdate(date);
                    companyModel.setSccEuid(userSid);
                    companyModel.setSccEdate(date);

                    companyList.add(companyModel);
                }
            }
        }

        return companyList;
    }

    /**
     * <br>[機  能] スケジュール-アドレス帳情報Mapping Modelを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSidList スケジュールSID
     * @param adrSidList アドレス帳SID
     * @param userSid セッションユーザSID
     * @param date 登録/更新日付
     * @return スケジュール-会社情報Mapping Model
     */
    public List<SchAddressModel> createAddressModel(List<Integer> scdSidList, String[] adrSidList,
                                                    int userSid, UDate date) {

        List<SchAddressModel> addressList = null;

        if (adrSidList != null) {

            addressList = new ArrayList<SchAddressModel>();

            for (Integer scdSid : scdSidList) {
                for (String adrSid : adrSidList) {
                    SchAddressModel addressModel = new SchAddressModel();
                    addressModel.setScdSid(scdSid);
                    addressModel.setAdrSid(Integer.parseInt(adrSid));
                    addressModel.setScaAuid(userSid);
                    addressModel.setScaAdate(date);
                    addressModel.setScaEuid(userSid);
                    addressModel.setScaEdate(date);

                    addressList.add(addressModel);
                }
            }
        }

        return addressList;
    }

    /**
     * <br>[機  能] コンタクト履歴Modelを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param adrSid アドレスSID
     * @param adcGrpSid アドレスグループSID
     * @param title タイトル
     * @param startDate 開始日時
     * @param endDate 終了日時
     * @param userSid 登録/更新セッションユーザSID
     * @param date 登録/更新日付
     * @return コンタクト履歴Model
     * @throws SQLException コンタクト履歴SIDの採番に失敗
     */
    public Sch040ContactModel createContactModel(int adrSid, int adcGrpSid,
                                                String title,
                                                String[] startDate, String[] endDate,
                                                int userSid, UDate date)
    throws SQLException {

        int adcSid = (int) cntCon__.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                GSConst.SBNSID_SUB_CONTACT,
                                                userSid);

        Sch040ContactModel contactMdl = new Sch040ContactModel();
        contactMdl.setAdcSid(adcSid);
        contactMdl.setAdrSid(adrSid);
        contactMdl.setAdcTitle(title);
        contactMdl.setAdcType(GSConst.CONTYP_MEETING);

        if (StringUtil.isNullZeroString(startDate[3])) {
            startDate[3] = "0";
        }
        if (StringUtil.isNullZeroString(startDate[4])) {
            startDate[4] = "0";
        }
        contactMdl.setAdcCttime(__createDate(startDate));

        if (StringUtil.isNullZeroString(endDate[3])) {
            endDate[3] = "23";
        }
        if (StringUtil.isNullZeroString(endDate[4])) {
            endDate[4] = "55";
        }
        contactMdl.setAdcCttimeTo(__createDate(endDate));

        contactMdl.setAdcAuid(userSid);
        contactMdl.setAdcAdate(date);
        contactMdl.setAdcEuid(userSid);
        contactMdl.setAdcEdate(date);
        contactMdl.setAdcGrpSid(adcGrpSid);

        return contactMdl;
    }

    /**
     * <br>[機  能] UDateの作成を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param dateElement 日時(年、月、日、時、分)
     * @return UDate
     */
    private UDate __createDate(String[] dateElement) {
        UDate date = new UDate();
        date.setZeroHhMmSs();
        date.setDate(Integer.parseInt(dateElement[0]),
                    Integer.parseInt(dateElement[1]),
                    Integer.parseInt(dateElement[2]));
        date.setHour(Integer.parseInt(dateElement[3]));
        date.setMinute(Integer.parseInt(dateElement[4]));

        return date;
    }

    /**
     * <br>[機  能] 重複登録の警告スケジュール一覧を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch040ParamModel
     * @param sessionUsrSid ユーザSID
     * @param con コネクション
     * @param mode 1:NG 2:警告を表示
     * @param scdUsrKbn ユーザ区分(ユーザ or グループ)
     * @return 警告スケジュールリスト
     * @throws SQLException SQLExceptionm
     */
    public List<SchDataModel> getSchWarningList(
            Sch040ParamModel paramMdl,
            int sessionUsrSid,
            Connection con,
            int mode,
            int scdUsrKbn) throws SQLException {
        List<SchDataModel> rptSchList = new ArrayList<SchDataModel>();

        if (paramMdl.getSch040TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_NOT_EXIST))) {
            return rptSchList;
        }
        //同時登録メンバー
        String[] sv_users = paramMdl.getSv_users();

        //個人設定を取得する。
        SchPriConfDao priConfDao = new SchPriConfDao(con);
        SchPriConfModel priModel = priConfDao.select(sessionUsrSid);

        SchCommonBiz schBiz = new SchCommonBiz(reqMdl__);
        SchRepeatKbnModel repertMdl = schBiz.getRepertKbn(con, priModel, sessionUsrSid);
        boolean mySchOkFlg = repertMdl.getRepeatKbn() == GSConstSchedule.SCH_REPEAT_KBN_NG
                            && repertMdl.getRepeatMyKbn() == GSConstSchedule.SCH_REPEAT_MY_KBN_OK;
        //ユーザリストを作成
        List<Integer> usrList = new ArrayList<Integer>();
        if (sv_users != null && sv_users.length > 0) {
            for (int i = 0; i < sv_users.length; i++) {
                if (mySchOkFlg && sessionUsrSid == Integer.parseInt(sv_users[i])) {
                    continue;
                }
                usrList.add(Integer.parseInt(sv_users[i]));
            }
        }

        //複写フラグ
        String copyFlg
        = NullDefault.getString(paramMdl.getSch040CopyFlg(), GSConstSchedule.NOT_COPY_FLG);

        //ユーザリストに被登録者を含める
        if (!mySchOkFlg || sessionUsrSid != Integer.parseInt(paramMdl.getSch010SelectUsrSid())) {
            usrList.add(Integer.parseInt(paramMdl.getSch010SelectUsrSid()));
        }

        int frYear = Integer.parseInt(paramMdl.getSch040FrYear());
        int frMonth = Integer.parseInt(paramMdl.getSch040FrMonth());
        int frDay = Integer.parseInt(paramMdl.getSch040FrDay());

        int toYear = Integer.parseInt(paramMdl.getSch040ToYear());
        int toMonth = Integer.parseInt(paramMdl.getSch040ToMonth());
        int toDay = Integer.parseInt(paramMdl.getSch040ToDay());

        int frHour = GSConstSchedule.DAY_START_HOUR;
        int frMin = GSConstSchedule.DAY_START_MINUTES;

        int toHour = GSConstSchedule.DAY_END_HOUR;
        int toMin = GSConstSchedule.DAY_END_MINUTES;
        int toSec = GSConstSchedule.DAY_START_SECOND;
        int toMiliSec = GSConstSchedule.DAY_START_MILLISECOND;

        if (paramMdl.getSch040TimeKbn().equals(String.valueOf(GSConstSchedule.TIME_EXIST))) {
            frHour = Integer.parseInt(paramMdl.getSch040FrHour());
            frMin = Integer.parseInt(paramMdl.getSch040FrMin());
            toHour = Integer.parseInt(paramMdl.getSch040ToHour());
            toMin = Integer.parseInt(paramMdl.getSch040ToMin());
            toSec = GSConstSchedule.DAY_START_SECOND;
            toMiliSec = GSConstSchedule.DAY_START_MILLISECOND;
        }

        //予約開始
        UDate chkFrDate = new UDate();
        chkFrDate.setDate(frYear, frMonth, frDay);
        chkFrDate.setHour(frHour);
        chkFrDate.setMinute(frMin);
        chkFrDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        chkFrDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);

        //予約終了
        UDate chkToDate = new UDate();
        chkToDate.setDate(toYear, toMonth, toDay);
        chkToDate.setHour(toHour);
        chkToDate.setMinute(toMin);
        chkToDate.setSecond(toSec);
        chkToDate.setMilliSecond(toMiliSec);


        //編集スケジュールSID
        int schSid = NullDefault.getInt(paramMdl.getSch010SchSid(), 0);

        SchDataDao schDao = new SchDataDao(con);
        int schGrpSid = -1;
        String batchRef = paramMdl.getSch040BatchRef();

        if (batchRef.equals("1")) {
            //同時修正する場合

            SchDataModel bean = new SchDataModel();
            bean.setScdSid(schSid);
            SchDataModel schModel = schDao.select(bean);

            if (schModel != null) {
                schGrpSid = schModel.getScdGrpSid();
            }
        }

        SchAdmConfModel admConf = schBiz.getAdmConfModel(con);
        boolean canEditRepeatKbn = schBiz.canEditRepertKbn(admConf);
        if (mode == GSConstSchedule.SCH_REPEAT_KBN_NG) {
            List<Integer> ngUsrList = null;
            if (canEditRepeatKbn) {
                //重複登録不可にしているユーザリストを取得
                ngUsrList = priConfDao.getUsrSidListRepeatKbn(usrList,
                                                            GSConstSchedule.SCH_REPEAT_KBN_NG);
            } else {
                if (admConf.getSadRepeatKbn() == GSConstSchedule.SCH_REPEAT_KBN_NG) {
                    ngUsrList = new ArrayList<Integer>();
                    ngUsrList.addAll(usrList);
                }
            }
            if (ngUsrList != null && !ngUsrList.isEmpty()) {
                //重複登録しているスケジュール一覧を取得する。
                rptSchList =
                    schDao.getSchData(ngUsrList, schSid, chkFrDate, chkToDate, schGrpSid,
                                                copyFlg, scdUsrKbn);
            }

        } else if (mode == GSConstSchedule.SCH_REPEAT_KBN_WARNING) {

            //重複登録警告にしているユーザリストを取得
            List<Integer> warningUsrList = null;
            if (canEditRepeatKbn) {
                warningUsrList = priConfDao.getUsrSidListRepeatKbn(usrList,
                                                            GSConstSchedule.SCH_REPEAT_KBN_WARNING);
            } else {
                warningUsrList = new ArrayList<Integer>();
                if (admConf.getSadRepeatKbn() != GSConstSchedule.SCH_REPEAT_KBN_OK) {
                    warningUsrList.addAll(usrList);
                }
            }

            //セッションユーザをチェックに含める
            if (mySchOkFlg
                    && sessionUsrSid == Integer.parseInt(paramMdl.getSch010SelectUsrSid())) {
                warningUsrList.add(Integer.parseInt(paramMdl.getSch010SelectUsrSid()));
            }

            if (warningUsrList != null && !warningUsrList.isEmpty()) {
                //重複登録しているスケジュール一覧を取得する。
                rptSchList = schDao.getSchData(
                        warningUsrList, schSid, chkFrDate, chkToDate, schGrpSid,
                        copyFlg, scdUsrKbn);
            }
        }

        return rptSchList;
    }

    /**
     * <br>[機  能] アクセス権限のない施設数を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch040ParamModel
     * @param sessionUsrSid ユーザSID
     * @param con コネクション
     * @param rsvAdmin 施設予約管理者
     * @return count 施設数
     * @throws SQLException SQLExceptionm
     */
    public int getCanNotEditRsvCount(
            Sch040ParamModel paramMdl,
            int sessionUsrSid,
            Connection con,
            boolean rsvAdmin
            ) throws SQLException {
        int count = 0;

        if (rsvAdmin) {
            return count;
        }

        ScheduleReserveDao schRsvDao = new ScheduleReserveDao(con);
        int scdSid = NullDefault.getInt(paramMdl.getSch010SchSid(), -1);

        ArrayList<Integer> allRsdList = schRsvDao.getScheduleReserveData(scdSid);
        if (allRsdList == null || allRsdList.size() == 0) {
            return count;
        }

        //施設SIDリストを取得
        ArrayList<Integer> rsdList
            = schRsvDao.getCanEditScheduleReserveData(scdSid, sessionUsrSid);

        if (rsdList.size() == allRsdList.size()) {
            return count;
        }

        for (Integer rsdSid : allRsdList) {
            if (!rsdList.contains(rsdSid)) {
                count++;
            }
        }

        return count;
    }

    /**
     * <br>[機  能] アクセス権限のない施設数を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param exSid 拡張SID
     * @param sessionUsrSid ユーザSID
     * @param con コネクション
     * @param rsvAdmin 施設予約管理者
     * @return count 施設数
     * @throws SQLException SQLExceptionm
     */
    public int getCanNotEditRsvCountEx(
            int exSid,
            int sessionUsrSid,
            Connection con,
            boolean rsvAdmin
            ) throws SQLException {
        int count = 0;

        if (rsvAdmin) {
            return count;
        }

        ScheduleReserveDao schRsvDao = new ScheduleReserveDao(con);

        ArrayList<Integer> allRsdList = schRsvDao.getScheduleReserveDataFromExSid(exSid);
        if (allRsdList == null || allRsdList.size() == 0) {
            return count;
        }

        //施設SIDリストを取得
        ArrayList<Integer> rsdList
            = schRsvDao.getCanEditScheduleReserveDataFromExSid(exSid, sessionUsrSid);

        if (rsdList.size() == allRsdList.size()) {
            return count;
        }

        for (Integer rsdSid : allRsdList) {
            if (!rsdList.contains(rsdSid)) {
                count++;
            }
        }

        return count;
    }


    /**
     * <br>[機  能] スケジュール(単票)をPDF出力します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータモデル
     * @param appRootPath アプリケーションルートパス
     * @param outTempDir テンポラリディレクトパス
     * @param pconfig プラグイン情報
     * @param userSid セッションユーザSID
     * @return pdfModel 施設予約単票PDFモデル
     * @throws IOException IO実行時例外
     * @throws SQLException SQL実行例外
     */
    public SchTanPdfModel createSchTanPdf(
            Sch040ParamModel paramMdl,
            String appRootPath,
            String outTempDir,
            PluginConfig pconfig,
            int userSid)
                    throws IOException, SQLException {
        OutputStream oStream = null;

        //スケジュール(単票)PDF出力用モデル
        SchTanPdfModel pdfModel = getSchPdfDataList(paramMdl, pconfig, userSid);

        String saveFileName = "schtan" + reqMdl__.getSmodel().getUsrsid() + ".pdf";
        pdfModel.setSaveFileName(saveFileName);

        try {
            IOTools.isDirCheck(outTempDir, true);
            oStream = new FileOutputStream(outTempDir + saveFileName);
            SchTanPdfUtil pdfUtil = new SchTanPdfUtil(reqMdl__);
            pdfUtil.createSchTanPdf(pdfModel, appRootPath, oStream);
        } catch (Exception e) {
            log__.error("スケジュール(単票)PDF出力に失敗しました。", e);
        } finally {
            if (oStream != null) {
                oStream.flush();
                oStream.close();
            }
        }
        log__.debug("スケジュール(単票)PDF出力を終了します。");

        return pdfModel;
    }

    /**
     * <br>[機  能] スケジュール単票PDF出力用データモデルを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param pconfig プラグインコンフィグ
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     * @return スケジュール単票PDFモデル
     */
    public SchTanPdfModel getSchPdfDataList(
            Sch040ParamModel paramMdl,
            PluginConfig pconfig, int userSid) throws SQLException {

        SchTanPdfModel ret = new SchTanPdfModel();

        //施設予約使用有無
        if (pconfig.getPlugin("reserve") != null) {
            ret.setReservePluginKbn(GSConstSchedule.RESERVE_PLUGIN_USE);
            log__.debug("施設予約使用");
        } else {
            ret.setReservePluginKbn(GSConstSchedule.RESERVE_PLUGIN_NOT_USE);
            log__.debug("施設予約使用不可");
        }

        //アドレス帳使用有無
        if (pconfig.getPlugin("address") != null) {
            ret.setAddressPluginKbn(GSConstSchedule.PLUGIN_USE);
            log__.debug("アドレス帳使用");
        } else {
            ret.setAddressPluginKbn(GSConstSchedule.PLUGIN_NOT_USE);
            log__.debug("アドレス帳使用不可");
        }

        //管理者設定を取得
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = biz.getAdmConfModel(con__);


        String scdSid = NullDefault.getString(paramMdl.getSch010SchSid(), "-1");
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String title = null;
        if (scdSid != null) {

            ScheduleSearchModel schMdl = getSchData(Integer.parseInt(scdSid), adminConf, con__);
            if (schMdl != null) {

                //グループ or ユーザ名
                if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
                    ret.setPdfName(getUsrName(schMdl.getScdUsrSid(), schMdl.getScdUsrKbn(), con__));
                } else {
                    ret.setPdfName(schMdl.getScdUsrSei() + " " + schMdl.getScdUsrMei());
                }
                //登録者
                ret.setPdfRegistUser(schMdl.getScdAuidSei() + " " + schMdl.getScdAuidMei());
                //登録日時
                String textAddDate = gsMsg.getMessage("schedule.src.84");
                ret.setPdfRegistDate(
                        textAddDate + " : "
                                + UDateUtil.getSlashYYMD(schMdl.getScdAdate())
                                + " "
                                + UDateUtil.getSeparateHM(schMdl.getScdAdate()));

                //日時
                UDate frDate = schMdl.getScdFrDate();
                UDate toDate = schMdl.getScdToDate();

                //時間指定区分
                ret.setPdfTimeKbn(schMdl.getScdDaily());
                if (schMdl.getScdDaily() == GSConstSchedule.TIME_EXIST) {
                    //開始
                    ret.setPdfFrDate(UDateUtil.getYymdJ(frDate, reqMdl__)
                            + " " + UDateUtil.getSeparateHMJ(frDate, reqMdl__));
                    //終了
                    ret.setPdfToDate(UDateUtil.getYymdJ(toDate, reqMdl__)
                            + " " + UDateUtil.getSeparateHMJ(toDate, reqMdl__));
                } else {
                  //開始
                    ret.setPdfFrDate(UDateUtil.getYymdJ(frDate, reqMdl__));
                    //終了
                    ret.setPdfToDate(UDateUtil.getYymdJ(toDate, reqMdl__));
                }

                //期間
                UDate kFrDate = schMdl.getScdFrDate();
                UDate kToDate = schMdl.getScdToDate();
                kFrDate.setZeroHhMmSs();
                kFrDate.addDay(-1);
                kToDate.setZeroHhMmSs();

                long kikan = UDateUtil.diffMillis(frDate, toDate);
                double days = Math.floor((double) kikan / (1000 * 60 * 60 * 24));
                ret.setPdfKikan((int) days);

                //会社・担当者
                SchCompanyDao companyDao = new SchCompanyDao(con__);
                List<SchCompanyModel> companyList = companyDao.select(Integer.parseInt(scdSid));

                String[] companySid = null;
                String[] companyBaseSid = null;
                if (!companyList.isEmpty()) {
                    companySid = new String[companyList.size()];
                    companyBaseSid = new String[companyList.size()];
                    for (int index = 0; index < companyList.size(); index++) {
                        SchCompanyModel companyModel = companyList.get(index);
                        companySid[index] = String.valueOf(companyModel.getAcoSid());
                        companyBaseSid[index] = String.valueOf(companyModel.getAbaSid());
                    }
                }

                String[] addressId = null;
                SchAddressDao addressDao = new SchAddressDao(con__);
                List<SchAddressModel> addressList = addressDao.select(Integer.parseInt(scdSid));
                if (addressList != null) {
                    addressId = new String[addressList.size()];
                    for (int index = 0; index < addressList.size(); index++) {
                        addressId[index] = String.valueOf(addressList.get(index).getAdrSid());
                    }
                }

                List<Sch040CompanyModel> pdfCompanyList =
                        __getPdfCmpInf(companySid, companyBaseSid, addressId, userSid);
                ret.setPdfCompanyList(pdfCompanyList);

                //タイトル
                title = schMdl.getScdTitle();
                ret.setPdfTitle(schMdl.getScdTitle());
                //タイトル色
                ret.setPdfColor(biz.getUserColor(schMdl.getScdBgcolor(), con__));
                //タイトル色 コメント一覧
                SchColMsgDao msgDao = new SchColMsgDao(con__);
                ArrayList<String> msgList = msgDao.selectMsg();
                ret.setPdfColorMsg(msgList);

                //内容
                ret.setPdfValue(schMdl.getScdValue());
                //備考
                ret.setPdfBiko(schMdl.getScdBiko());
                //公開
                ret.setPdfPublicKbn(schMdl.getScdPublic());
                //編集権限
                ret.setPdfEditKbn(schMdl.getScdEdit());
                //同時登録ユーザリスト
                ret.setPdfUserList(schMdl.getUsrInfList());
                //施設予約一覧
                ret.setPdfSisList(getPdfSisInf(paramMdl));


                //出欠確認表示区分
                ret.setPdfAttendKbn(schMdl.getScdAttendKbn());
                //出欠確認するの場合
                if (schMdl.getScdAttendKbn() == GSConstSchedule.ATTEND_KBN_YES) {
                    //出欠確認登録者区分
                    ret.setPdfAttendAuKbn(schMdl.getScdAttendAuKbn());
                    //出欠確認回答区分
                    ret.setPdfAttendAnsKbn(schMdl.getScdAttendAns());

                    Sch220Biz sch220biz = new Sch220Biz(reqMdl__);
                    ArrayList<Sch040AttendModel> ansList =
                            sch220biz.getAttendAnsListAll(schMdl.getScdSid(), con__);
                    //出欠確認回答一覧
                    ret.setPdfAttendAnsList(ansList);
                }


                //出欠確認区分
                int attendKbn = schMdl.getScdAttendKbn();
                //出欠登録者区分
                int attendAnsUsrKbn = schMdl.getScdAttendAuKbn();
                //スケジュール編集画面 表示モード
                int editDspMode = getEditDspMode(attendKbn, attendAnsUsrKbn);
                ret.setPdfEditDspMode(editDspMode);

            }
        }

        //ファイル名
        String fileName = gsMsg.getMessage("schedule.108");
        fileName += "_";
        fileName += NullDefault.getString(title, "");
        String encFileName = __fileNameCheck(fileName) + ".pdf";

        ret.setFileName(encFileName);
        return ret;
    }


    /**
     * <br>[機  能] PDF表示用の施設予約一覧データを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @return 施設予約リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<String> getPdfSisInf(Sch040ParamModel paramMdl)
            throws SQLException {
        ArrayList<String> ret = new ArrayList<String>();

        CommonBiz cmnBiz = new CommonBiz();
        //施設予約の管理者
        boolean rsvAdmin =
                cmnBiz.isPluginAdmin(
                        con__, reqMdl__.getSmodel(), GSConstSchedule.PLUGIN_ID_RESERVE);

        //施設予約
        String [] rsvUsrs = paramMdl.getSvReserveUsers();
        //追加済み施設SID
        if ((paramMdl.getSvReserveUsers() == null
                || paramMdl.getSvReserveUsers().length == 0)) {
            int intScdSid = NullDefault.getInt(paramMdl.getSch010SchSid(), -1);
            //施設SIDリストを取得
            ScheduleReserveDao schRsvDao = new ScheduleReserveDao(con__);
            ArrayList<Integer> list = schRsvDao.getScheduleReserveData(intScdSid);
            ArrayList<String> sv_user_list = new ArrayList<String>();

            if (list != null) {
                for (Integer rsdSid : list) {
                    sv_user_list.add(String.valueOf(rsdSid));
                }

                rsvUsrs =
                        (String[]) sv_user_list.toArray(new String[sv_user_list.size()]);
            }
        }
        //除外する施設SIDを設定
        ArrayList<Integer> resSids = new ArrayList<Integer>();
        RsvSisDataDao dataDao = new RsvSisDataDao(con__);
        ArrayList < Integer > resList = null;
        ArrayList<RsvSisDataModel> selectResList = null;
        String[] reservs = rsvUsrs;
        if (reservs != null && reservs.length > 0) {
            resList = new ArrayList<Integer>();
            for (int i = 0; i < reservs.length; i++) {
                resList.add(new Integer(reservs[i]));
                //同時登録施設を所属リストから除外する
                resSids.add(new Integer(reservs[i]));
            }

            if (rsvAdmin) {
                //全施設
                selectResList =
                    dataDao.selectGrpSisetuList(resList);
            } else {
                //閲覧権限のある施設
                selectResList =
                    dataDao.selectGrpSisetuCanReadList(resList, reqMdl__.getSmodel().getUsrsid());
            }

            ArrayList<String> sisNameList = new ArrayList<String>();
            for (RsvSisDataModel rsvsisMdl : selectResList) {
                sisNameList.add(rsvsisMdl.getRsdName());
            }
          ret = sisNameList;
        }
        return ret;
    }

    /**
     * <br>[機  能] PDF表示用の会社情報、アドレス帳情報を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param companySid 会社SID
     * @param companyBaseSid 会社拠点SID
     * @param addressId アドレス帳情報SID
     * @param userSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     * @return 会社情報リスト
     */
    private List<Sch040CompanyModel> __getPdfCmpInf(
            String [] companySid,
            String [] companyBaseSid,
            String [] addressId,
            int userSid)
                    throws SQLException {

        Sch040Dao dao040 = new Sch040Dao(con__);
        String[] acoSidList = companySid;
        String[] abaSidList = companyBaseSid;
        List<String> companyIdList = new ArrayList<String>();
        Map<String, Sch040CompanyModel> companyMap = new HashMap<String, Sch040CompanyModel>();

        Sch040CompanyModel noCompanyModel = new Sch040CompanyModel();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //会社登録無し
        String textCmpDataNone = gsMsg.getMessage("schedule.src.87");
        noCompanyModel.setCompanyName(textCmpDataNone);
        noCompanyModel.setCompanyAddress(null);
        noCompanyModel.setCompanySid(0);
        noCompanyModel.setCompanyBaseSid(0);
        companyMap.put("0:0", noCompanyModel);

        if (acoSidList != null && abaSidList != null) {

            for (int index = 0; index < acoSidList.length; index++) {
                int acoSid = Integer.parseInt(acoSidList[index]);
                int abaSid = Integer.parseInt(abaSidList[index]);

                Sch040CompanyModel companyData = createCompanyData(dao040, acoSid, abaSid);
                if (companyData != null) {
                    String companyId = acoSid + ":" + abaSid;
                    companyMap.put(companyId, companyData);
                    companyIdList.add(companyId);
                }
            }
        }

        //アドレス情報を取得
        Sch040Dao sch040Dao = new Sch040Dao(con__);
        List<Sch040AddressModel> addressList
                    = sch040Dao.getAddressList(con__, addressId, userSid);
        List<String> addressSidList = new ArrayList<String>();
        if (addressList != null) {

            for (Sch040AddressModel adrData : addressList) {
                String companyId = adrData.getCompanySid() + ":" + adrData.getCompanyBaseSid();
                Sch040CompanyModel companyData = companyMap.get(companyId);
                if (companyData == null) {
                    companyData = createCompanyData(dao040,
                                                    adrData.getCompanySid(),
                                                    adrData.getCompanyBaseSid());
                    companyMap.put(companyId, companyData);
                    companyIdList.add(companyId);
                }

                addressSidList.add(String.valueOf(adrData.getAdrSid()));
                companyData.getAddressDataList().add(adrData);
                companyMap.put(companyId, companyData);
            }
        }

        String[] companySidArray = new String[companyIdList.size()];
        String[] companyBaseSidArray = new String[companyIdList.size()];
        List<Sch040CompanyModel> companyList = new ArrayList<Sch040CompanyModel>();

        if (!companyMap.get("0:0").getAddressDataList().isEmpty()) {
            companyList.add(companyMap.get("0:0"));
        }

        for (int index = 0; index < companyIdList.size(); index++) {
            String companyId = companyIdList.get(index);
            companySidArray[index] = companyId.split(":")[0];
            companyBaseSidArray[index] = companyId.split(":")[1];
            companyList.add(companyMap.get(companyId));
        }

        return companyList;

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

    /**
     * <br>[機  能] スケジュール編集画面 表示モードを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param attendKbn 出欠確認区分
     * @param attendRegKbn 出欠登録者区分
     * @return 表示モード 0：通常スケジュール  1:出欠依頼者  2:出欠回答者
     *
     */
    public int getEditDspMode(int attendKbn, int attendRegKbn) {

        //出欠確認区分「確認しない」の場合
        if (attendKbn == GSConstSchedule.ATTEND_KBN_NO) {
            return GSConstSchedule.EDIT_DSP_MODE_NORMAL;
        //出欠確認区分「確認する」の場合
        } else if (attendKbn == GSConstSchedule.ATTEND_KBN_YES) {

            //出欠登録者区分「登録者」
            if (attendRegKbn == GSConstSchedule.ATTEND_REGIST_USER_YES) {
                return GSConstSchedule.EDIT_DSP_MODE_REGIST;
            //出欠登録者区分「登録者以外」
            } else {
                return GSConstSchedule.EDIT_DSP_MODE_ANSWER;
            }
        }
        return GSConstSchedule.EDIT_DSP_MODE_NORMAL;
    }

    /**
     * <br>[機  能] スケジュール編集画面 表示モードが回答者モードかチェックする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param attendKbn 出欠確認区分
     * @param attendRegKbn 出欠登録者区分
     * @return true 表示モード 出欠回答者
     */
    public boolean isCheckEditDspModeAns(int attendKbn, int attendRegKbn) {

        return GSConstSchedule.EDIT_DSP_MODE_ANSWER
                == getEditDspMode(attendKbn, attendRegKbn);
    }

    /**
     * <br>[機  能] 指定したスケジュールグループSIDの出欠確認回答一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdGrpSid スケジュールグループSID（リレーション用）
     * @throws SQLException SQL実行例外
     * @return 出欠確認回答一覧
     */
    public ArrayList<Sch040AttendModel> getAttendAnsList(int scdGrpSid)
            throws SQLException {

        ArrayList<Sch040AttendModel> ret = new ArrayList<Sch040AttendModel>();
        Sch040AttendModel attendMdl = null;
        SchDataDao dao = new SchDataDao(con__);
        ArrayList<SchDataModel> schList = dao.selectAttendAnsSchGrp(scdGrpSid);

        for (SchDataModel schMdl : schList) {
            attendMdl = new Sch040AttendModel();
            String strEditDate =
                    UDateUtil.getSlashYYMD(schMdl.getScdEdate())
                    + " "
                    + UDateUtil.getSeparateHM(schMdl.getScdEdate());
            attendMdl.setAttendAnsDate(strEditDate);
            attendMdl.setAttendAnsUsrSid(schMdl.getScdUsrSid());
            attendMdl.setAttendAnsUsrName(schMdl.getScdUserName());
            attendMdl.setAttendAnsKbn(schMdl.getScdAttendAns());
            ret.add(attendMdl);
        }
        return ret;
    }


    /**
     * <br>[機  能]スケジュール登録・編集・削除時ログ出力内容を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param frDate 開始日時
     * @param toDate 終了日時
     * @param title タイトル
     * @param value 内容
     * @param target 対象ユーザ
     * @param sisetuName 施設名
     * @return オペレーションログ表示内容
     */
    public String getOpLog(
            String frDate,  String toDate, String title, String value, String target,
            ArrayList<String> sisetuName) {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        StringBuilder sbValue = new StringBuilder();
        sbValue.append("[");
        sbValue.append(gsMsg.getMessage("sml.155"));
        sbValue.append("]");
        sbValue.append(target);
        sbValue.append("\n");
        sbValue.append("[");
        sbValue.append(gsMsg.getMessage("schedule.sch100.11"));
        sbValue.append("]");
        sbValue.append(frDate);
        sbValue.append("\n");
        sbValue.append("[");
        sbValue.append(gsMsg.getMessage("schedule.sch100.16"));
        sbValue.append("]");
        sbValue.append(toDate);
        sbValue.append("\n");
        sbValue.append("[");
        sbValue.append(gsMsg.getMessage("cmn.title"));
        sbValue.append("]");
        sbValue.append(title);
        sbValue.append("\n");
        sbValue.append("[");
        sbValue.append(gsMsg.getMessage("cmn.content"));
        sbValue.append("]");
        sbValue.append(value);
        if (sisetuName != null  && sisetuName.size() > 0) {
            sbValue.append("\n");
            sbValue.append("[");
            sbValue.append(gsMsg.getMessage("cmn.facility.name"));
            sbValue.append("]");
            sbValue.append(sisetuName);
        } else {
            sbValue.append("");
        }
        return sbValue.toString();
    }
    /**
     * <br>[機  能]スケジュールSIDを元に施設予約された場合の施設リストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param schSid スケジュールSID
     * @param paramMdl パラメータモデル
     * @throws NumberFormatException NumberFormatException
     * @throws SQLException SQL実行時例外
     * @return 施設リスト
     */
    public ArrayList<RsvSisDataModel> getSisetuList(String  schSid, Sch040ParamModel paramMdl)
            throws NumberFormatException, SQLException {
        //施設リスト
        ArrayList<RsvSisDataModel> selectResList = null;
        //施設予約
        String [] rsvUsrs = paramMdl.getSvReserveUsers();
        //スケジュールSIDから同時登録された施設予約情報を取得し、施設SIDリストに格納
        ScheduleReserveDao schRsvDao = new ScheduleReserveDao(con__);
        ArrayList<Integer> list = schRsvDao.getScheduleReserveData(NullDefault.getInt(schSid, -1));
        ArrayList<String> sv_user_list = new ArrayList<String>();
        if (list != null) {
            for (Integer rsdSid : list) {
                sv_user_list.add(String.valueOf(rsdSid));
            }
            rsvUsrs =
                    (String[]) sv_user_list.toArray(new String[sv_user_list.size()]);
        }

        //除外する施設SIDを設定
        ArrayList<Integer> resSids = new ArrayList<Integer>();
        ArrayList < Integer > resList = null;
        String[] reservs = rsvUsrs;
        if (reservs != null && reservs.length > 0) {
            resList = new ArrayList<Integer>();
            for (int i = 0; i < reservs.length; i++) {
                resList.add(new Integer(reservs[i]));
                //同時登録施設を所属リストから除外する
                resSids.add(new Integer(reservs[i]));
            }
            if (list != null) {
                //施設SIDリストを元に指定されたグループの施設リストを取得する
                RsvSisDataDao dataDao = new RsvSisDataDao(con__);
                selectResList = dataDao.selectGrpSisetuList(list);
            } else {
                selectResList = new ArrayList<RsvSisDataModel>();
            }
        }
        return selectResList;
    }
    /**
     * <br>[機  能] ユーザ一覧からスケジュール登録不可のユーザを除外する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userList ユーザ一覧
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void removeNotRegistUser(Connection con, List<CmnUsrmInfModel> userList,
                                                    int sessionUserSid)
    throws SQLException {
        if (userList == null) {
            return;
        }
        //グループ所属ユーザラベルから登録不可ユーザを除外する
        SchDao schDao = new SchDao(con);
        List<Integer> notAccessUsrList = schDao.getNotRegistUserList(sessionUserSid);
        ArrayList<CmnUsrmInfModel> accessUserList = new ArrayList<CmnUsrmInfModel>();
        for (CmnUsrmInfModel userLabel : userList) {
            if (notAccessUsrList.indexOf(userLabel.getUsrSid()) < 0) {
                accessUserList.add(userLabel);
            }
        }
        userList.clear();
        userList.addAll(accessUserList);
    }
}
