package jp.groupsession.v2.api.schedule.edit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.SaibanModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.RelationBetweenScdAndRsvChkBiz;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.dao.RsvSisKryrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisKyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisRyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.RsvRegSmailModel;
import jp.groupsession.v2.rsv.model.RsvSisKyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisRyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisYrkModel;
import jp.groupsession.v2.rsv.rsv070.Rsv070Model;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchAddressDao;
import jp.groupsession.v2.sch.dao.SchCompanyDao;
import jp.groupsession.v2.sch.dao.SchDataDao;
import jp.groupsession.v2.sch.dao.ScheduleReserveDao;
import jp.groupsession.v2.sch.dao.ScheduleSearchDao;
import jp.groupsession.v2.sch.model.SchAddressModel;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchCompanyModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.sch.sch010.Sch010Biz;
import jp.groupsession.v2.sch.sch040.Sch040Biz;
import jp.groupsession.v2.sch.sch040.Sch040Dao;
import jp.groupsession.v2.sch.sch040.model.Sch040AddressModel;
import jp.groupsession.v2.sch.sch040.model.Sch040CompanyModel;
import jp.groupsession.v2.sch.sch040.model.Sch040ContactModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
/**
 *
 * <br>[機  能] スケジュール編集WEBAPIビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiSchEditBiz {
    /** ログ */
    private static Log log__ =
            LogFactory.getLog(new Throwable().getStackTrace()[0].getClassName());
    /** コネクション */
    public Connection con__ = null;
    /** リクエストモデル*/
    public RequestModel reqMdl__ = null;
    /** 採番コントローラ */
    public MlCountMtController cntCon__ = null;

    /** パラメータモデル */
    ApiSchEditParamModel formParam__;
    /** 処理モード 登録・編集*/
    public static final int MODE_EDIT = 0;
    /** 処理モード 削除*/
    public static final int MODE_DELETE = 1;
    /** 処理モード 出欠応答*/
    public static final int MODE_ANSER = 2;

    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl RequestModel
     * @param cntCon MlCountMtController
     */
    public ApiSchEditBiz(Connection con,
            RequestModel reqMdl, MlCountMtController cntCon) {
        super();
        con__ = con;
        reqMdl__ = reqMdl;
        cntCon__ = cntCon;
    }


    /**
     * <p>formParam を取得します。
     * @return formParam
     */
    public ApiSchEditParamModel getFormParam() {
        return formParam__;
    }
    /**
     * <p>formParam をセットします。
     * @param formParam formParam
     */
    public void setFormParam(ApiSchEditParamModel formParam) {
        formParam__ = formParam;
    }


    /**
     * <br>[機  能] スケジュールを新規登録します
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid 登録者SID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param tempDir テンポラリディレクトリパス
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @throws Exception SQL実行時例外
     */
    public void insertSchedule(
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            String tempDir,
            boolean smailPluginUseFlg
            ) throws Exception {

        SchDataModel schMdl = null;

        //登録モデルを作成
        schMdl = new SchDataModel();
        UDate frDate = formParam__.getFrDate();
        UDate toDate = formParam__.getToDate();
        UDate now = new UDate();

        schMdl.setScdDaily(NullDefault.getInt(formParam__.getTimeKbn()
                , GSConstSchedule.TIME_EXIST));

        if (schMdl.getScdDaily() == GSConstSchedule.TIME_EXIST) {
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
            toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            toDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        }


        schMdl.setScdFrDate(frDate);
        schMdl.setScdToDate(toDate);
        schMdl.setScdBgcolor(
                NullDefault.getInt(formParam__.getColorKbn(), GSConstSchedule.DF_BG_COLOR));
        schMdl.setScdTitle(formParam__.getTitle());
        schMdl.setScdValue(NullDefault.getString(formParam__.getNaiyo(), ""));
        schMdl.setScdBiko(NullDefault.getString(formParam__.getBiko(), ""));
        schMdl.setScdPublic(
                NullDefault.getInt(formParam__.getSchKf(), GSConstSchedule.DSP_PUBLIC));

        schMdl.setScdAuid(userSid);
        schMdl.setScdAdate(now);
        schMdl.setScdEuid(userSid);
        schMdl.setScdEdate(now);
        //編集区分
        schMdl.setScdEdit(
                NullDefault.getInt(formParam__.getSchEf(), GSConstSchedule.EDIT_CONF_NONE));
        //拡張登録SID
        int extSid = -1;
        schMdl.setSceSid(extSid);

        int scdSid = -1;
        int scdGpSid = GSConstSchedule.DF_SCHGP_ID;
        int scdResSid = GSConstSchedule.DF_SCHGP_ID;
        //SID採番
        scdSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                SaibanModel.SBNSID_SUB_SCH, userSid);
        //form値にsidを保存
        formParam__.setSchSid(String.valueOf(scdSid));

        schMdl.setScdSid(scdSid);
        schMdl.setScdUsrSid(Integer.parseInt(formParam__.getUsrSid()));
        schMdl.setScdUsrKbn(Integer.parseInt(formParam__.getUserKbn()));


        String[] svUsers = formParam__.getSameScheduledUser();
        if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
            svUsers = null;
        }
        //スケジュールグループSID（同時登録有りの場合）
        if (svUsers != null && svUsers.length > 0) {
            //スケジュールグループSID（同時登録有りの場合）
            scdGpSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH_GP, userSid);
        }
        schMdl.setScdGrpSid(scdGpSid);
        String[] svReserves = formParam__.getReserves();
        if (svReserves != null && svReserves.length > 0) {
            //スケジュール施設予約SID（施設予約有りの場合）
            scdResSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH_RES, userSid);
        }
        schMdl.setScdRsSid(scdResSid);

        int attendKbn = NullDefault.getInt(formParam__.getAttendKbn(),
                GSConstSchedule.ATTEND_KBN_NO);
        schMdl.setScdAttendKbn(attendKbn);
        if (attendKbn == GSConstSchedule.ATTEND_KBN_YES) {
            schMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_YES);
            schMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_YES);
        } else {
            schMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
            schMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
        }


        __insertSchdule(schMdl,
                svUsers,
                appRootPath,
                plconf,
                tempDir,
                smailPluginUseFlg,
                null,
                0);
        __insertReserve(
                schMdl,
                svReserves,
                -1,
                appRootPath,
                plconf,
                tempDir,
                smailPluginUseFlg);

    }

    /**
     * <br>[機  能] スケジュールを新規登録します
     * <br>[解  説]
     * <br>[備  考]
     * @param schMdl スケジュールモデル
     * @param svUsers 同時登録ユーザSIDリスト
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param tempDir テンポラリディレクトリパス
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param attendMap 出欠確認応答マップ
     * @param sendAttendMailType 出欠確認メールタイプ 0:新規 1:再送
     * @throws Exception SQL実行時例外
     */
    private void __insertSchdule(SchDataModel schMdl,
            String[] svUsers,
            String appRootPath,
            PluginConfig plconf,
            String tempDir,
            boolean smailPluginUseFlg,
            Map<Integer, Integer> attendMap,
            int sendAttendMailType
            ) throws Exception {
        List<Integer> scdSidList = new ArrayList<Integer>();

        if (attendMap == null) {
            attendMap = new HashMap<Integer, Integer>();
        }
        SchDataDao schDao = new SchDataDao(con__);
        //登録
        schDao.insert(schMdl);
        scdSidList.add(schMdl.getScdSid());

        Map<Integer, Integer> scdUserMap = new HashMap<Integer, Integer>();
        scdUserMap.put(schMdl.getScdSid(), schMdl.getScdUsrSid());

        //URL取得
        String url = createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                                           String.valueOf(schMdl.getScdSid()),
                                           String.valueOf(schMdl.getScdUsrSid()),
                                           schMdl.getScdFrDate().getDateString(),
                                           String.valueOf(schMdl.getScdUsrKbn())
                                           );
        SchCommonBiz cmnBiz = new SchCommonBiz(reqMdl__);
        cmnBiz.sendPlgSmail(con__, cntCon__, schMdl, appRootPath, plconf, smailPluginUseFlg, url);
        //同時登録分
        if (svUsers != null) {
            for (int i = 0; i < svUsers.length; i++) {
                int scdSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                        SaibanModel.SBNSID_SUB_SCH, schMdl.getScdEuid());
                int addUserSid = Integer.parseInt(svUsers[i]);
                schMdl.setScdSid(scdSid);
                schMdl.setScdUsrSid(addUserSid);
                schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_USER);
                schMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
                schMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
                if (attendMap.containsKey(addUserSid)) {
                    schMdl.setScdAttendAns(attendMap.get(addUserSid));
                }


                schDao.insert(schMdl);
                scdSidList.add(schMdl.getScdSid());
                scdUserMap.put(schMdl.getScdSid(), schMdl.getScdUsrSid());


                //URL取得
                url = createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                                                   String.valueOf(schMdl.getScdSid()),
                                                   String.valueOf(schMdl.getScdUsrSid()),
                                                   schMdl.getScdFrDate().getDateString(),
                                                   String.valueOf(schMdl.getScdUsrKbn())
                                                   );

                if (schMdl.getScdAttendKbn() == GSConstSchedule.ATTEND_KBN_YES) {
                    if (sendAttendMailType >= 0) {
                        cmnBiz.sendAttendSmail(con__, cntCon__, schMdl, appRootPath,
                                plconf, smailPluginUseFlg, url, sendAttendMailType);
                    }
                } else {
                    cmnBiz.sendPlgSmail(con__,
                            cntCon__,
                            schMdl,
                            appRootPath,
                            plconf,
                            smailPluginUseFlg,
                            url);
                }
            }
        }
        //会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴を登録
        __insertSchCompany(scdSidList, scdUserMap, schMdl.getScdEuid(), schMdl.getScdEdate());

    }
    /**
     * <br>[機  能] 施設予約を新規登録します
     * <br>[解  説]
     * <br>[備  考]
     * @param schMdl スケジュールモデル
     * @param svReserves 施設SIDリスト
     * @param rsrSid 施設拡張SID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param tempDir テンポラリディレクトリパス
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @throws Exception SQL実行時例外
     */
    void __insertReserve(SchDataModel schMdl,
            String[] svReserves,
            int rsrSid,
            String appRootPath,
            PluginConfig plconf,
            String tempDir,
            boolean smailPluginUseFlg
            ) throws Exception {
        //施設予約を登録
        int yoyakuSid = -1;
        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con__);
        if (svReserves != null) {
            for (int i = 0; i < svReserves.length; i++) {
                yoyakuSid = (int) cntCon__.getSaibanNumber(
                        GSConstReserve.SBNSID_RESERVE,
                        GSConstReserve.SBNSID_SUB_YOYAKU,
                        schMdl.getScdEuid());
                RsvSisYrkModel yrkParam = new RsvSisYrkModel();
                yrkParam.setRsySid(yoyakuSid);
                yrkParam.setRsdSid(Integer.parseInt(svReserves[i]));
                String moku = schMdl.getScdTitle();
                yrkParam.setRsyMok(moku);
                yrkParam.setRsyFrDate(schMdl.getScdFrDate());
                yrkParam.setRsyToDate(schMdl.getScdToDate());
                yrkParam.setRsyBiko(schMdl.getScdValue());
                yrkParam.setRsyAuid(schMdl.getScdEuid());
                yrkParam.setRsyAdate(schMdl.getScdEdate());
                yrkParam.setRsyEuid(schMdl.getScdEuid());
                yrkParam.setRsyEdate(schMdl.getScdEdate());
                yrkParam.setScdRsSid(schMdl.getScdRsSid());
                yrkParam.setRsyEdit(schMdl.getScdEdit());
                yrkParam.setRsrRsid(rsrSid);

                //承認状況
                RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
                rsvCmnBiz.setSisYrkApprData(con__,
                        yrkParam.getRsdSid(),
                        yrkParam,
                        schMdl.getScdEuid());
                yrkDao.insertNewYoyaku(yrkParam);

                //施設予約区分別情報を登録
                RsvSisDataDao dataDao = new RsvSisDataDao(con__);
                Rsv070Model mdl = dataDao.getPopUpSisetuData(Integer.parseInt(svReserves[i]));
                if (mdl != null) {
                    if (RsvCommonBiz.isRskKbnRegCheck(mdl.getRskSid())) {
                        RsvCommonBiz rsvBiz = new RsvCommonBiz();
                        RsvSisKyrkModel kyrkMdl =
                                rsvBiz.getSisKbnInitData(
                                        con__, reqMdl__, mdl.getRskSid(), appRootPath);
                        kyrkMdl.setRsySid(yoyakuSid);
                        kyrkMdl.setRkyAuid(schMdl.getScdEuid());
                        kyrkMdl.setRkyAdate(schMdl.getScdEdate());
                        kyrkMdl.setRkyEuid(schMdl.getScdEuid());
                        kyrkMdl.setRkyEdate(schMdl.getScdEdate());

                        RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con__);
                        kyrkDao.insert(kyrkMdl);
                    }
                }


                int sisetsuSid = Integer.parseInt(svReserves[i]);
                int sessionUsrSid = schMdl.getScdEuid();
                //選択した施設に承認設定がされている場合
                if (rsvCmnBiz.isApprSis(con__, sisetsuSid, sessionUsrSid)) {
                    //ショートメールで通知
                    if (smailPluginUseFlg) {

                        RsvRegSmailModel regMdl = new RsvRegSmailModel();
                        regMdl.setCon(con__);
                        regMdl.setReqMdl(reqMdl__);
                        regMdl.setRsySid(yoyakuSid);
                        regMdl.setRsdSid(sisetsuSid);
                        regMdl.setCntCon(cntCon__);
                        regMdl.setUserSid(sessionUsrSid);
                        regMdl.setAppRootPath(appRootPath);
                        regMdl.setTempDir(tempDir);
                        regMdl.setPluginConfig(plconf);

                        rsvCmnBiz.sendRegSmail(regMdl, __createReserveUrl(yoyakuSid));
                    }
                }


            }
        }
    }

    /**
     * <br>[機  能] 会社、アドレス帳マップの作成
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid usrSid
     * @param acoSids 会社SId
     * @param abaSids 会社拠点SID
     * @param adrSids アドレスSID
     * @return 会社、アドレス帳マップ
     * @throws SQLException SQL実行時例外
     *
     */
    public Map<String, Sch040CompanyModel>makeCompanyMap(int userSid,
            String[] acoSids,
            String[] abaSids,
            String[] adrSids) throws SQLException {
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

        Sch040Biz sch040biz = new Sch040Biz(con__, reqMdl__, cntCon__);
        Sch040Dao sch040Dao = new Sch040Dao(con__);

        if (acoSids != null && abaSids != null) {

            for (int index = 0; index < acoSids.length; index++) {
                int acoSid = Integer.parseInt(acoSids[index]);
                int abaSid = Integer.parseInt(abaSids[index]);
                Sch040CompanyModel companyData =
                        sch040biz.createCompanyData(sch040Dao, acoSid, abaSid);
                if (companyData != null) {
                    String companyId = acoSid + ":" + abaSid;
                    companyMap.put(companyId, companyData);
                }
            }
        }


        //アドレス情報を取得
        List<Sch040AddressModel> addressList
                    = sch040Dao.getAddressList(con__, adrSids, userSid);
        List<String> addressSidList = new ArrayList<String>();
        if (addressList != null) {

            for (Sch040AddressModel adrData : addressList) {

                String companyId = adrData.getCompanySid() + ":" + adrData.getCompanyBaseSid();
                Sch040CompanyModel companyData = companyMap.get(companyId);
                if (companyData == null) {
                    companyData = sch040biz.createCompanyData(sch040Dao,
                                                    adrData.getCompanySid(),
                                                    adrData.getCompanyBaseSid());
                    if (companyData != null) {
                        companyMap.put(companyId, companyData);
                    }
                }
                if (companyData == null) {
                    companyId = "0:0";
                    companyData = companyMap.get(companyId);
                }
                addressSidList.add(String.valueOf(adrData.getAdrSid()));
                companyData.getAddressDataList().add(adrData);
                companyMap.put(companyId, companyData);
            }
        }
        return companyMap;
    }
    /**
     * <br>[機  能] 会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSidList スケジュールSID
     * @param scdUserMap スケジュールSIDとユーザSIDのMapping
     * @param sessionUserSid セッションユーザSID
     * @param date 更新日付
     * @throws SQLException SQL実行時例外
     */
    private void __insertSchCompany(
                                    List<Integer> scdSidList,
                                    Map<Integer, Integer> scdUserMap,
                                    int sessionUserSid, UDate date)
    throws SQLException {

        Sch040Biz sch040Biz = new Sch040Biz(con__, reqMdl__, cntCon__);

        //会社情報Mappingを登録
        Map<String, Sch040CompanyModel> comMap =
                makeCompanyMap(sessionUserSid,
                        formParam__.getAcoSid(),
                        formParam__.getAbaSid(),
                        formParam__.getAdress());
        String[] acoSids = new String[comMap.size()];
        String[] abaSids = new String[comMap.size()];
        int index = 0;
        for (String key : comMap.keySet()) {
            Sch040CompanyModel company = comMap.get(key);
            acoSids[index] = String.valueOf(company.getCompanySid());
            abaSids[index] = String.valueOf(company.getCompanyBaseSid());
            index++;
        }


        List<SchCompanyModel> companyList = sch040Biz.createCompanyModel(scdSidList,
                                                            acoSids,
                                                            abaSids,
                                                            sessionUserSid, date);
        if (companyList != null) {
            SchCompanyDao companyDao = new SchCompanyDao(con__);
            for (SchCompanyModel companyModel : companyList) {
                companyDao.insert(companyModel);
            }
        }

        //アドレス帳情報Mapping、コンタクト履歴を登録する
        String[] addressId = formParam__.getAdress();
        List<SchAddressModel> addressList = sch040Biz.createAddressModel(scdSidList, addressId,
                                                            sessionUserSid, date);
        if (addressList != null) {
            SchAddressDao addressDao = new SchAddressDao(con__);
            Sch040Dao dao040 = new Sch040Dao(con__);
            boolean contactFlg = (NullDefault.getInt(formParam__.getContactFlg(), 0) == 1);

            String contactTitle = formParam__.getTitle();
            String[] startDate = new String[5];
            startDate[0] = String.valueOf(formParam__.getFrDate().getYear());
            startDate[1] = String.valueOf(formParam__.getFrDate().getMonth());
            startDate[2] = String.valueOf(formParam__.getFrDate().getIntDay());
            startDate[3] = String.valueOf(formParam__.getFrDate().getIntHour());
            startDate[4] = String.valueOf(formParam__.getFrDate().getIntMinute());
            String[] endDate = new String[5];
            endDate[0] = String.valueOf(formParam__.getToDate().getYear());
            endDate[1] = String.valueOf(formParam__.getToDate().getMonth());
            endDate[2] = String.valueOf(formParam__.getToDate().getIntDay());
            endDate[3] = String.valueOf(formParam__.getToDate().getIntHour());
            endDate[4] = String.valueOf(formParam__.getToDate().getIntMinute());


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
                            = sch040Biz.createContactModel(Integer.parseInt(adrSid), adcGrpSid,
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
     * <br>[機  能] スケジュールを更新します
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param tempDir テンポラリディレクトリパス
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @throws Exception SQL実行時例外
     */
    public void updateSchedule(
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            String tempDir,
            boolean smailPluginUseFlg) throws Exception {

        Sch040Biz sch040Biz = new Sch040Biz(con__, reqMdl__, cntCon__);

        //管理者設定を取得
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = biz.getAdmConfModel(con__);
        SchCommonBiz cmnBiz = new SchCommonBiz(reqMdl__);

        int timeKbn = NullDefault.getInt(formParam__.getTimeKbn(), GSConstSchedule.TIME_EXIST);


        String scdSid = formParam__.getSchSid();
        SchDataModel scdMdl = new SchDataModel();
        UDate now = new UDate();
        UDate frDate = formParam__.getFrDate();
        UDate toDate = formParam__.getToDate();



        scdMdl.setScdDaily(timeKbn);

        if (scdMdl.getScdDaily() == GSConstSchedule.TIME_EXIST) {
            frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
            toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
            toDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        }



        scdMdl.setScdSid(Integer.parseInt(scdSid));
        scdMdl.setScdFrDate(frDate);
        scdMdl.setScdToDate(toDate);
        scdMdl.setScdBgcolor(NullDefault.getInt(formParam__.getColorKbn(),
                GSConstSchedule.DF_BG_COLOR));
        scdMdl.setScdTitle(formParam__.getTitle());
        scdMdl.setScdValue(NullDefault.getString(formParam__.getNaiyo(), ""));
        scdMdl.setScdBiko(NullDefault.getString(formParam__.getBiko(), ""));
        scdMdl.setScdPublic(
                NullDefault.getInt(formParam__.getSchKf(), GSConstSchedule.DSP_PUBLIC));

        scdMdl.setScdAuid(userSid);
        scdMdl.setScdAdate(now);
        scdMdl.setScdEuid(userSid);
        scdMdl.setScdEdate(now);
        int attendKbn = NullDefault.getInt(formParam__.getAttendKbn(),
                GSConstSchedule.ATTEND_KBN_NO);
        scdMdl.setScdAttendKbn(attendKbn);
        if (attendKbn == GSConstSchedule.ATTEND_KBN_YES) {
            scdMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_YES);
            scdMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_YES);
        } else {
            scdMdl.setScdAttendAns(GSConstSchedule.ATTEND_ANS_NONE);
            scdMdl.setScdAttendAuKbn(GSConstSchedule.ATTEND_REGIST_USER_NO);
        }


        //編集区分
        scdMdl.setScdEdit(
                NullDefault.getInt(formParam__.getSchEf(), GSConstSchedule.EDIT_CONF_NONE));


        SchDataDao schDao = new SchDataDao(con__);
        ScheduleSearchDao ssDao = new ScheduleSearchDao(con__);


        //編集前スケジュール取得
        ScheduleSearchModel oldMdl =
                sch040Biz.getSchData(Integer.parseInt(scdSid), adminConf, con__);
        //拡張登録SID
        int extSid = oldMdl.getSceSid();
        scdMdl.setSceSid(extSid);
        //スケジュール施設予約SID
        int resSid = oldMdl.getScdRsSid();
        scdMdl.setScdRsSid(resSid);
        String[] svReserves = null;
        if (timeKbn == GSConstSchedule.TIME_EXIST) {
            svReserves = formParam__.getReserves();
        }

        int scdResSid = GSConstSchedule.DF_SCHGP_ID;
        int newScdSid = -1;

        //施設拡張取得(スケジュール情報を削除する前に取得)
        RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con__);
        RsvSisRyrkModel ryrkMdl = null;
        if (NullDefault.getInt(formParam__.getBatchResRef(), 1) == 1
                || timeKbn == GSConstSchedule.TIME_NOT_EXIST) {
            ryrkMdl = ryrkDao.selectFromScdSid(Integer.parseInt(scdSid));
        }

        if (NullDefault.getInt(formParam__.getBatchRef(), 1) == 0) {
            //同時登録反映無しの場合
            scdMdl.setScdGrpSid(GSConstSchedule.DF_SCHGP_ID);
            //施設予約へ反映する場合、新たに採番
            if (NullDefault.getInt(formParam__.getBatchResRef(), 1) == 1) {
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
            __updateSchCompany(Integer.parseInt(scdSid), scdMdl.getScdUsrSid(),
                            scdMdl.getScdEdate(), scdMdl.getScdEuid());

            //URL取得
            String url = createScheduleUrlDefo(GSConstSchedule.CMD_EDIT,
                                               String.valueOf(scdMdl.getScdSid()),
                                               String.valueOf(scdMdl.getScdUsrSid()),
                                               scdMdl.getScdFrDate().getDateString(),
                                               String.valueOf(scdMdl.getScdUsrKbn())
                                               );
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

            //SID採番
            newScdSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH, userSid);
            scdMdl.setScdSid(newScdSid);
            scdMdl.setScdUsrSid(Integer.parseInt(formParam__.getUsrSid()));
            scdMdl.setScdUsrKbn(Integer.parseInt(formParam__.getUserKbn()));

            //form値にsidを保存
            formParam__.setSchSid(String.valueOf(newScdSid));

            int scdGpSid = GSConstSchedule.DF_SCHGP_ID;
            String[] svUsers = formParam__.getSameScheduledUser();
            //スケジュールグループSID（同時登録有りの場合）
            if (svUsers != null && svUsers.length > 0) {
                //スケジュールグループSID（同時登録有りの場合）
                scdGpSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                        SaibanModel.SBNSID_SUB_SCH_GP, userSid);
            }
            scdMdl.setScdGrpSid(scdGpSid);

            //施設予約へ反映する場合、新たに採番
            if (NullDefault.getInt(formParam__.getBatchResRef(), 1) == 1) {
                if (svReserves != null && svReserves.length > 0) {
                    //スケジュール施設予約SID（施設予約有りの場合）
                    scdResSid = (int) cntCon__.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                            SaibanModel.SBNSID_SUB_SCH_RES, userSid);
                    scdMdl.setScdRsSid(scdResSid);
                }
            }
            //旧スケジュールの出欠確認Map作成
            List<SchDataModel> oldScds = ssDao.getSameScheduleList(oldMdl.getScdGrpSid(),
                    Arrays.asList(new Integer[] { userSid }));
            Map<Integer, Integer> userAttendMap = new HashMap<Integer, Integer>();
            for (SchDataModel schData : oldScds) {
                userAttendMap.put(schData.getScdUsrSid(), schData.getScdAttendAns());
            }

            int sendAttendMailType = oldMdl.getScdAttendKbn();
            if (sendAttendMailType == GSConstSchedule.ATTEND_KBN_YES
                    && NullDefault.getInt(formParam__.getAttendMailResendKbn(), 0) == 0) {
                sendAttendMailType = -1;
            }
            __insertSchdule(scdMdl,
                    svUsers,
                    appRootPath,
                    plconf,
                    tempDir,
                    smailPluginUseFlg,
                    userAttendMap,
                    sendAttendMailType);

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
            sch040Biz.deleteSchCompany(con__,
                    deleteScdSidList,
                    Integer.valueOf(formParam__.getContactFlg()));
        }

        int rsrSid = -1;
        //施設予約への更新判定 時間指定無しの場合は更新
        if (NullDefault.getInt(formParam__.getBatchResRef(), 1) == 1
                || timeKbn == GSConstSchedule.TIME_NOT_EXIST) {

            if (ryrkMdl != null) {
                rsrSid = ryrkMdl.getRsrRsid();
            }
            __insertReserve(scdMdl,
                    svReserves,
                    rsrSid,
                    appRootPath,
                    plconf,
                    tempDir,
                    smailPluginUseFlg);

            RsvSisYrkDao yrkDao = new RsvSisYrkDao(con__);

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

    }

    /**
     * <br>[機  能] 会社情報Mapping、アドレス帳情報Mapping、コンタクト履歴の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @param userSid 登録/更新ユーザSID
     * @param date 更新日付
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __updateSchCompany(int scdSid,
                                    int userSid, UDate date, int sessionUserSid)
    throws SQLException {

        Sch040Biz sch040Biz = new Sch040Biz(con__, reqMdl__, cntCon__);
        List<Integer> scdSidList = new ArrayList<Integer>();
        scdSidList.add(scdSid);
        int contact = Integer.valueOf(formParam__.getContactFlg());
        sch040Biz.deleteSchCompany(con__, scdSidList, contact);

        Map<Integer, Integer> scdUserMap = new HashMap<Integer, Integer>();
        scdUserMap.put(scdSid, userSid);
        __insertSchCompany(scdSidList, scdUserMap, sessionUserSid, date);
    }
    /**
     * <br>[機  能] スケジュール一般登録確認URLを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param cmd 処理モード
     * @param sch010SchSid スケジュールSID
     * @param usrSid ユーザーSID
     * @param frDateStr スケジュール日付文字列(yyyyMMdd)
     * @param userKbn ユーザー区分（0:ユーザ 1:グループ）
     * @return スケジュール一般登録確認URL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    public String createScheduleUrlDefo(String cmd,
                                          String sch010SchSid, String usrSid,
                                          String frDateStr,
                                          String userKbn)
    throws UnsupportedEncodingException {
        String scheduleUrl = null;

        //スレッドのURLを作成
        StringBuffer schUrl = reqMdl__.getRequestURL();
        if (schUrl != null || schUrl.toString().length() > 0) {
            scheduleUrl = schUrl.substring(0, schUrl.lastIndexOf("/api/"));

            String domain = "";
            String reqDomain = NullDefault.getString(reqMdl__.getDomain(), "");

            scheduleUrl += "/common/cmn001.do?url=";

            if (!reqDomain.equals(GSConst.GS_DOMAIN)) {
                domain = reqDomain + "/";
                scheduleUrl = scheduleUrl.replace(
                 "common", domain + "common");
            }

            String paramUrl = reqMdl__.getRequestURI();
            paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("/api"));
            paramUrl += "/" + GSConstSchedule.PLUGIN_ID_SCHEDULE;


            if (!reqDomain.equals(GSConst.GS_DOMAIN)) {
                paramUrl = paramUrl.replace(
                 GSConstSchedule.PLUGIN_ID_SCHEDULE, domain + GSConstSchedule.PLUGIN_ID_SCHEDULE);
            }

            paramUrl += "/sch040.do";
            paramUrl += "?sch010SelectDate=" + frDateStr;
            paramUrl += "&cmd=" + cmd;
            paramUrl += "&sch010SchSid=" + sch010SchSid;
            paramUrl += "&sch010SelectUsrSid=" + usrSid;
            paramUrl += "&sch010SelectUsrKbn=" + userKbn;
            paramUrl += "&sch010DspDate=" + frDateStr;
            paramUrl += "&dspMod=" + GSConstSchedule.DSP_MOD_WEEK;
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

            scheduleUrl += paramUrl;
        }

        return scheduleUrl;
    }
    /**
     * <br>[機  能] スケジュールの存在チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param schSid スケジュールSID
     * @param actionString エラーメッセージ内アクション名称
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateExistData(
            ActionErrors errors,
            int schSid,
            String actionString) throws SQLException {
        //セッション情報を取得
        //管理者設定を取得
        SchCommonBiz adminbiz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel adminConf = adminbiz.getAdmConfModel(con__);

        Sch040Biz biz = new Sch040Biz(con__, reqMdl__);
        ScheduleSearchModel scdMdl = biz.getSchData(schSid, adminConf, con__);
        if (scdMdl == null) {
            GsMessage gsMsg = new GsMessage(reqMdl__);
            //スケジュール
            String textSchedule = gsMsg.getMessage("schedule.108");
            //変更
            ActionMessage msg = new ActionMessage("error.none.edit.data",
                    textSchedule, actionString);
            StrutsUtil.addMessage(errors, msg, "detail");
        } else {
            //指定スケジュールの編集権限チェック
            SchCommonBiz schCmnBiz = new SchCommonBiz();
            if (!schCmnBiz.canRegistSchedule(con__, scdMdl, reqMdl__.getSmodel().getUsrsid())) {
                //変更
                ActionMessage msg = new ActionMessage("error.scd.auth");
                StrutsUtil.addMessage(errors, msg, "detail");
            }
        }

        return errors;
    }

    /**
     * <br>[機  能] 同時登録スケジュールの編集権限チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param schSid スケジュールSID
     * @param batchRef 同時編集フラグ 0:同時編集しない 1:同時編集する
     * @param mode モード 0：編集・登録 1:削除 2:出欠応答
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateSchPowerCheck(
            ActionErrors errors,
            int schSid,
            int batchRef,
            int mode) throws SQLException {

        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //変更
        String textChange = gsMsg.getMessage("cmn.change");
        //同時登録スケジュールの編集権限チェック
        if (schSid != -1) {
            //
            //セッション情報を取得
            BaseUserModel usModel = reqMdl__.getSmodel();
            int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
            CommonBiz commonBiz = new CommonBiz();
            boolean isAdmin = commonBiz.isPluginAdmin(con__,
                    usModel, GSConstSchedule.PLUGIN_ID_SCHEDULE);
            //管理者設定を取得
            SchCommonBiz adminbiz = new SchCommonBiz(reqMdl__);
            SchAdmConfModel adminConf = adminbiz.getAdmConfModel(con__);

            Sch040Biz sch040biz = new Sch040Biz(con__, reqMdl__);
            ScheduleSearchModel scdMdl = sch040biz.getSchData(schSid, adminConf, con__);
            if (scdMdl == null) {
                String textSimultaneousEdit = gsMsg.getMessage("schedule.9");
                msg = new ActionMessage("error.edit.power.user",
                        textSimultaneousEdit,
                        textChange);
                StrutsUtil.addMessage(errors, msg, "adduser");
                return errors;
            } else {
                //指定スケジュールの編集権限チェック
                SchCommonBiz schCmnBiz = new SchCommonBiz();
                if (!schCmnBiz.canRegistSchedule(con__, scdMdl, reqMdl__.getSmodel().getUsrsid())) {
                    //変更
                    msg = new ActionMessage("error.scd.auth");
                    StrutsUtil.addMessage(errors, msg, "detail");
                    return errors;
                }
            }

            //出欠確認スケジュールは単体削除不可
            if (mode == MODE_DELETE && scdMdl.getScdAttendKbn() != GSConstSchedule.ATTEND_KBN_NO
                    && batchRef == GSConstSchedule.SAME_EDIT_OFF) {
                msg = new ActionMessage("error.cant.edit.single.attend.schedule");
                StrutsUtil.addMessage(errors, msg, "attend");
                return errors;
            }
            //出欠確認するかつ、出欠登録者区分が「１：登録者以外」かつ同時登録者に「０：登録者」が存在する場合、編集不可
            if (mode != MODE_ANSER && scdMdl.getScdAttendKbn() != GSConstSchedule.ATTEND_KBN_NO
                    && scdMdl.getScdAttendAuKbn() == GSConstSchedule.ATTEND_REGIST_USER_NO) {
                ArrayList<ScheduleSearchModel> schDataList = null;
                schDataList = sch040biz.getSchDataList(scdMdl.getScdSid(), adminConf, con__);
                boolean flg = false;
                if (mode == 0) {
                    flg = true;
                }
                for (ScheduleSearchModel mdl : schDataList) {
                    if (mdl.getScdAttendAuKbn() != GSConstSchedule.ATTEND_REGIST_USER_NO) {
                        flg = true;
                        break;
                    }
                }
                if (flg) {

                    msg = new ActionMessage("error.cant.edit.attend.schedule");
                    StrutsUtil.addMessage(errors, msg, "attend");
                    return errors;
                }

            }
            //出欠応答時、出欠登録者区分が「０：登録者」の場合、編集不可
            if (mode == MODE_ANSER && scdMdl.getScdAttendKbn() != GSConstSchedule.ATTEND_KBN_NO
                    && scdMdl.getScdAttendAuKbn() != GSConstSchedule.ATTEND_REGIST_USER_NO) {
                msg = new ActionMessage("error.cant.anser.attend.request.schedule");
                StrutsUtil.addMessage(errors, msg, "attend");
                return errors;

            }
            Sch010Biz biz = new Sch010Biz(reqMdl__);
            if (!biz.isEditOk(scdMdl, sessionUsrSid, isAdmin, con__)) {
                String textSimultaneousEdit = gsMsg.getMessage("schedule.9");
                msg = new ActionMessage("error.edit.power.user",
                        textSimultaneousEdit,
                        textChange);
                StrutsUtil.addMessage(errors, msg, "adduser");
                return errors;
            }
            if (batchRef != GSConstSchedule.SAME_EDIT_OFF
                    && !biz.isAllEditOk(scdMdl, adminConf, reqMdl__, sessionUsrSid, isAdmin, con__)
                ) {
                    //同時登録スケジュールに対する編集
                    String textSimultaneousEdit = gsMsg.getMessage("schedule.src.33");
                    msg = new ActionMessage("error.edit.power.user",
                            textSimultaneousEdit,
                            textChange);
                    StrutsUtil.addMessage(errors, msg, "adduser");
            }
        }
        return errors;
    }
    /**
     * <br>[機  能] 同時登録施設予約の編集権限チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param errors アクションエラー
     * @param schSid スケジュールSID
     * @param batchResRef 同時編集フラグ
     * @return エラー
     * @throws SQLException SQL実行時例外
     */
    public ActionErrors validateResPowerCheck(
            ActionErrors errors,
            int schSid,
            int batchResRef) throws SQLException {

        ActionMessage msg = null;
        GsMessage gsMsg = new GsMessage(reqMdl__);

        //アクセス権限チェック
        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        CommonBiz cmnBiz = new CommonBiz();


        //編集権限のない施設数を取得する。
        if (batchResRef == 1) {
            boolean rsvAdmin = cmnBiz.isPluginAdmin(
                    con__, usModel, GSConstSchedule.PLUGIN_ID_RESERVE);
            int count = getCanNotEditRsvCount(usModel.getUsrsid(), schSid, rsvAdmin);

            if (count > 0) {
                //施設予約アクセス権限なし
                msg = new ActionMessage("error.myself.auth");
                StrutsUtil.addMessage(errors, msg, "error.myself.auth");
                return errors;
            }

        }


        //変更
        String textChange = gsMsg.getMessage("cmn.change");
        //同時登録施設予約の編集権限チェック
        if (schSid != -1 && batchResRef == 1) {

            RelationBetweenScdAndRsvChkBiz rsvChkBiz
            = new RelationBetweenScdAndRsvChkBiz(reqMdl__, con__);
            int errorCd = rsvChkBiz.isRsvEdit(
                    schSid,
                    RelationBetweenScdAndRsvChkBiz.CHK_KBN_TANITU);
            log__.debug("施設予約の編集権限チェック:エラーコード==>" + errorCd);
            if (errorCd == RelationBetweenScdAndRsvChkBiz.ERR_CD_SCD_CANNOT_EDIT) {
                //施設予約に対する編集
                String textRsvEdit = gsMsg.getMessage("schedule.src.32");
                msg = new ActionMessage("error.edit.power.user", textRsvEdit, textChange);
                StrutsUtil.addMessage(errors, msg, "addres");
            }
        }
        return errors;
    }
    /**
     * <br>[機  能] アクセス権限のない施設数を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param sessionUsrSid ユーザSID
     * @param scdSid スケジュールSID
     * @param rsvAdmin 施設予約管理者
     * @return count 施設数
     * @throws SQLException SQLExceptionm
     */
    public int getCanNotEditRsvCount(
            int sessionUsrSid,
            int scdSid,
            boolean rsvAdmin
            ) throws SQLException {
        int count = 0;

        if (rsvAdmin) {
            return count;
        }

        ScheduleReserveDao schRsvDao = new ScheduleReserveDao(con__);

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
     * <br>[機  能] 施設予約登録確認URLを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rsvSid 施設SID
     * @return 施設予約登録確認URL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
   private String __createReserveUrl(int rsvSid) throws UnsupportedEncodingException {
       String reserveUrl = null;

       UDate now = new UDate();
       String date = now.getDateString();

       //スレッドのURLを作成
       StringBuffer schUrl = reqMdl__.getRequestURL();
       if (schUrl != null || schUrl.toString().length() > 0) {
           reserveUrl = schUrl.substring(0, schUrl.lastIndexOf("/api/"));

           String domain = "";
           String reqDomain = NullDefault.getString(reqMdl__.getDomain(), "");

           reserveUrl += "/common/cmn001.do?url=";

           if (!reqDomain.equals(GSConst.GS_DOMAIN)) {
               domain = reqDomain + "/";
               reserveUrl = reserveUrl.replace(
                "common", domain + "common");
           }

           String paramUrl = reqMdl__.getRequestURI();
           paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("/api"));
           paramUrl += "/" + GSConstReserve.PLUGIN_ID_RESERVE;

           if (!reqDomain.equals(GSConst.GS_DOMAIN)) {
               paramUrl = paramUrl.replace(
                       GSConstReserve.PLUGIN_ID_RESERVE
                , domain + GSConstReserve.PLUGIN_ID_RESERVE);
           }
           paramUrl += "/rsv110.do";

           paramUrl += "?rsv110ProcMode=" + Integer.parseInt(GSConstReserve.PROC_MODE_EDIT);
           paramUrl += "&rsv110RsySid=" + rsvSid;
           paramUrl += "&rsvDspFrom=" + date;
           paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

           reserveUrl += paramUrl;
       }

       return reserveUrl;
   }


}
