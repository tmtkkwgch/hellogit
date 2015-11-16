package jp.groupsession.v2.rsv.rsv210kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
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
import jp.groupsession.v2.rsv.model.RsvHidDayModel;
import jp.groupsession.v2.rsv.model.RsvHidGroupModel;
import jp.groupsession.v2.rsv.model.RsvHidModel;
import jp.groupsession.v2.rsv.model.RsvHidSisetuModel;
import jp.groupsession.v2.rsv.model.RsvScdOperationModel;
import jp.groupsession.v2.rsv.model.RsvSisKyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisYrkModel;
import jp.groupsession.v2.rsv.rsv070.Rsv070Model;
import jp.groupsession.v2.rsv.rsv210.Rsv210Biz;
import jp.groupsession.v2.struts.msg.GsMessage;

/**
 * <br>[機  能] 施設予約 施設予約一括登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv210knBiz extends AbstractReserveBiz {

    /** リクエスト情報 */
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
    public Rsv210knBiz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv210knParamModel
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Rsv210knParamModel paramMdl) throws SQLException {

        //登録者名をセット
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        paramMdl.setRsv210Torokusya(usrMdl.getUsisei() + "  " + usrMdl.getUsimei());

        //時間
        String timeFr =
            StringUtil.toDecFormat(paramMdl.getRsv210SelectedHourFr(), "00")
            + ":"
            + StringUtil.toDecFormat(paramMdl.getRsv210SelectedMinuteFr(), "00");

        String timeTo =
            StringUtil.toDecFormat(paramMdl.getRsv210SelectedHourTo(), "00")
            + ":"
            + StringUtil.toDecFormat(paramMdl.getRsv210SelectedMinuteTo(), "00");

        paramMdl.setRsv210knTimeFr(timeFr);
        paramMdl.setRsv210knTimeTo(timeTo);

        //内容
        paramMdl.setRsv210knNaiyo(StringUtilHtml.transToHTmlPlusAmparsant(
                NullDefault.getString(paramMdl.getRsv210Naiyo(), "")));

        //登録対象施設
        paramMdl.setRsvIkkatuTorokuHiddenList(getTargetSisetuList(paramMdl));

        //スケジュールを登録するユーザがいる場合、登録するユーザの名称をセット
        Rsv210Biz biz210 = new Rsv210Biz(reqMdl_, con_);
        biz210.setUserName(paramMdl, paramMdl.getSv_users());
        biz210 = null;
    }

    /**
     * <br>[機  能] 選択施設情報リストを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv210knParamModel
     * @return hiddList 選択施設リスト
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<RsvHidDayModel> getTargetSisetuList(Rsv210knParamModel paramMdl)
        throws SQLException {

        ArrayList<String> hiddArray = new ArrayList<String>();
        String[] hiddStr = paramMdl.getRsvIkkatuTorokuKey();
        if (hiddStr != null && hiddStr.length > 0) {
            for (String key : hiddStr) {
                hiddArray.add(key);
            }
        }

        Collections.sort(hiddArray);
        String saveDay = null;
        String saveHiddDayKey = null;
        RsvSisDataDao dao = new RsvSisDataDao(con_);
        ArrayList<String> searchArray = new ArrayList<String>();
        ArrayList<RsvHidDayModel> hiddList = new ArrayList<RsvHidDayModel>();

        //画面に表示しきれていないキーがあれば処理
        if (!hiddArray.isEmpty()) {

            for (String hiddKey : hiddArray) {

                //キーから日付部分を取得
                String hiddDayKey = hiddKey.substring(0, hiddKey.indexOf("-"));

                if (saveDay == null) {
                    saveDay = hiddDayKey;
                    saveHiddDayKey = hiddDayKey;
                } else if (!saveDay.equals(hiddDayKey)) {

                    ArrayList<RsvHidModel> hiddDayList = dao.selectHidSisetuList(searchArray);
                    hiddList.add(__getDaylySisetuList(hiddDayList, saveDay, saveHiddDayKey));

                    //配列とキーを初期化
                    searchArray = new ArrayList<String>();
                    saveDay = hiddDayKey;
                    saveHiddDayKey = hiddDayKey;
                }

                //キーの施設SID部分を追加
                String hiddSidKey = hiddKey.substring(hiddKey.indexOf("-") + 1);
                searchArray.add(hiddSidKey);
            }

            if (!searchArray.isEmpty()) {
                //リスト末尾
                ArrayList<RsvHidModel> hiddDayList = dao.selectHidSisetuList(searchArray);
                hiddList.add(__getDaylySisetuList(hiddDayList, saveDay, saveHiddDayKey));
            }
        }

        return hiddList;
    }

    /**
     * <br>[機  能] DB取得結果を画面表示用に変換する
     * <br>[解  説]
     * <br>
     * <br>   施設グループ    施設
     * <br>       A            1
     * <br>       A            2
     * <br>       A            3
     * <br>       B            4
     * <br>       B            5
     * <br>       B            6
     * <br>
     * <br>   DBから上記の形式で取得したリストを
     * <br>
     * <br>   施設グループ    施設
     * <br>       A            1 2 3
     * <br>       B            4 5 6
     * <br>
     * <br>   の形式へ変換する
     * <br>
     * <br>[備  考]
     *
     * @param hiddDayList DB取得結果リスト
     * @param saveDay キー
     * @param saveHiddDayKey 日付文字列yyyyMMdd
     * @return ret 変換後モデル
     */
    private RsvHidDayModel __getDaylySisetuList(ArrayList<RsvHidModel> hiddDayList,
                                                 String saveDay,
                                                 String saveHiddDayKey) {

        //画面表示用に変換
        int saveRsgSid = -1;
        String saveRsgName = null;
        ArrayList<RsvHidSisetuModel> hidSisetuList =
            new ArrayList<RsvHidSisetuModel>();
        ArrayList<RsvHidGroupModel> hidGroupList =
            new ArrayList<RsvHidGroupModel>();

        RsvHidDayModel day = new RsvHidDayModel();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        UDate udDay = new UDate();
        udDay.setDate(saveDay);
        day.setHidDayStr(
                gsMsg.getMessage("cmn.year", new String[] {udDay.getStrYear()})
                + udDay.getStrMonth()
                + gsMsg.getMessage("cmn.month")
                + udDay.getStrDay()
                + gsMsg.getMessage("cmn.day") + "（"
                + UDateUtil.getStrWeek(udDay.getWeek(), reqMdl_)
                + "）");

        for (RsvHidModel dbMdl : hiddDayList) {

            if (saveRsgSid == -1) {
                saveRsgSid = dbMdl.getRsgSid();
                saveRsgName = dbMdl.getRsgName();

                RsvHidSisetuModel sisetu = new RsvHidSisetuModel();
                sisetu.setRsdSid(dbMdl.getRsdSid());
                sisetu.setRsdName(dbMdl.getRsdName());
                sisetu.setRsvIkkatuTorokuKey(saveHiddDayKey + "-" + dbMdl.getRsdSid());
                hidSisetuList.add(sisetu);
                continue;

            //同じグループに所属する施設をまとめる
            } else if (saveRsgSid == dbMdl.getRsgSid()) {

                RsvHidSisetuModel sisetu = new RsvHidSisetuModel();
                sisetu.setRsdSid(dbMdl.getRsdSid());
                sisetu.setRsdName(dbMdl.getRsdName());
                sisetu.setRsvIkkatuTorokuKey(saveHiddDayKey + "-" + dbMdl.getRsdSid());
                hidSisetuList.add(sisetu);
                continue;

            //グループブレイク
            } else if (saveRsgSid != dbMdl.getRsgSid()) {

                RsvHidGroupModel group = new RsvHidGroupModel();
                group.setRsgName(saveRsgName);
                group.setSisetuList(hidSisetuList);
                hidGroupList.add(group);

                saveRsgSid = dbMdl.getRsgSid();
                saveRsgName = dbMdl.getRsgName();

                hidSisetuList = new ArrayList<RsvHidSisetuModel>();
                RsvHidSisetuModel sisetu = new RsvHidSisetuModel();
                sisetu.setRsdSid(dbMdl.getRsdSid());
                sisetu.setRsdName(dbMdl.getRsdName());
                sisetu.setRsvIkkatuTorokuKey(saveHiddDayKey + "-" + dbMdl.getRsdSid());
                hidSisetuList.add(sisetu);
            }

            day.setGrpList(hidGroupList);
        }
        RsvHidGroupModel group = new RsvHidGroupModel();
        group.setRsgName(saveRsgName);
        group.setSisetuList(hidSisetuList);
        hidGroupList.add(group);
        day.setGrpList(hidGroupList);

        return day;
    }

    /**
     * <br>[機  能] 入力内容をDBに反映する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv210knParamModel
     * @param ctrl 採番用コネクション
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @return sidDataList 予約SID、施設SIDリスト
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<int []> updateYoyakuData(
            Rsv210knParamModel paramMdl, MlCountMtController ctrl, int userSid, String appRootPath)
        throws SQLException {

        ArrayList<String> hiddArray = new ArrayList<String>();
        String[] hiddStr = paramMdl.getRsvIkkatuTorokuKey();
        if (hiddStr != null && hiddStr.length > 0) {
            for (String key : hiddStr) {
                hiddArray.add(key);
            }
        }

        Collections.sort(hiddArray);

        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int usrSid = usrMdl.getUsrsid();

        UDate now = new UDate();
        ArrayList<int []> sidDataList = new ArrayList<int []>();

        int rsvSchGrpSid = NullDefault.getInt(paramMdl.getRsv210SchGroupSid(), -1);
        String[] users = paramMdl.getSv_users();

        List<String> scdUserList = new ArrayList<String>();
        List<String> scdGroupList = new ArrayList<String>();
        Map<String, Integer> rsSidMap = new HashMap<String, Integer>();

        RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
        for (String key : hiddArray) {

            String ymdStr = key.substring(0, key.indexOf("-"));

            //スケジュール予約SID採番
            int schKbn = paramMdl.getRsv210SchKbn();
            boolean schInsertFlg = (schKbn == GSConstReserve.RSV_SCHKBN_USER
                                        && users != null && users.length > 0)
                                || (schKbn == GSConstReserve.RSV_SCHKBN_GROUP
                                        && rsvSchGrpSid >= 0);
            int rsSid = -1;
            if (schInsertFlg) {
                if (rsSidMap.containsKey(ymdStr)) {
                    rsSid = rsSidMap.get(ymdStr).intValue();
                } else  {
                    rsSid =
                        (int) ctrl.getSaibanNumber(
                                SaibanModel.SBNSID_SCHEDULE,
                                SaibanModel.SBNSID_SUB_SCH_RES,
                                usrSid);
                    rsSidMap.put(ymdStr, rsSid);
                }
            }

            UDate udYrk = new UDate();
            udYrk.setDate(ymdStr);

            //予約開始
            UDate frDate = new UDate();
            frDate.setDate(
                    udYrk.getYear(),
                    udYrk.getMonth(),
                    udYrk.getIntDay());
            frDate.setHour(paramMdl.getRsv210SelectedHourFr());
            frDate.setMinute(paramMdl.getRsv210SelectedMinuteFr());
            frDate.setSecond(GSConstReserve.DAY_START_SECOND);
            frDate.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

            //予約終了
            UDate toDate = new UDate();
            toDate.setDate(
                    udYrk.getYear(),
                    udYrk.getMonth(),
                    udYrk.getIntDay());
            toDate.setHour(paramMdl.getRsv210SelectedHourTo());
            toDate.setMinute(paramMdl.getRsv210SelectedMinuteTo());
            toDate.setSecond(GSConstReserve.DAY_START_SECOND);
            toDate.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

            RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);

            //予約SID採番
            int yoyakuSid =
                (int) ctrl.getSaibanNumber(
                        GSConstReserve.SBNSID_RESERVE,
                        GSConstReserve.SBNSID_SUB_YOYAKU,
                        usrSid);

            //施設SID
            String sisetuSid = key.substring(key.indexOf("-") + 1);
            RsvSisYrkModel yrkParam = new RsvSisYrkModel();
            yrkParam.setRsySid(yoyakuSid);
            yrkParam.setRsdSid(Integer.parseInt(sisetuSid));
            yrkParam.setRsyMok(NullDefault.getString(paramMdl.getRsv210Mokuteki(), ""));
            yrkParam.setRsyFrDate(frDate);
            yrkParam.setRsyToDate(toDate);
            yrkParam.setRsyBiko(NullDefault.getString(paramMdl.getRsv210Naiyo(), ""));
            yrkParam.setRsyAuid(usrSid);
            yrkParam.setRsyAdate(now);
            yrkParam.setRsyEuid(usrSid);
            yrkParam.setRsyEdate(now);
            yrkParam.setRsyEdit(paramMdl.getRsv210RsyEdit());
            yrkParam.setScdRsSid(rsSid);

            //承認状況
            rsvCmnBiz.setSisYrkApprData(con_, Integer.parseInt(sisetuSid), yrkParam, userSid);

            //施設予約区分別情報を登録
            RsvSisDataDao dataDao = new RsvSisDataDao(con_);
            Rsv070Model mdl = dataDao.getPopUpSisetuData(Integer.parseInt(sisetuSid));
            if (mdl != null) {

                if (_isRskKbnRegCheck(mdl.getRskSid())) {
                    RsvCommonBiz rsvBiz = new RsvCommonBiz();
                    RsvSisKyrkModel kyrkMdl =
                            rsvBiz.getSisKbnInitData(
                                    con_, reqMdl_, mdl.getRskSid(), appRootPath);
                    kyrkMdl.setRsySid(yoyakuSid);
                    kyrkMdl.setRkyAuid(userSid);
                    kyrkMdl.setRkyAdate(now);
                    kyrkMdl.setRkyEuid(userSid);
                    kyrkMdl.setRkyEdate(now);

                    RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);
                    kyrkDao.insert(kyrkMdl);
                }
            }

            yrkDao.insertNewYoyaku(yrkParam);
            sidDataList.add(new int []{yoyakuSid, Integer.parseInt(sisetuSid)});

            //スケジュール情報の登録
            if (schInsertFlg) {
                //スケジュールグループSID
                int scdGpSid = -1;
                if (paramMdl.getRsv210SchKbn() == GSConstReserve.RSV_SCHKBN_USER
                && users.length > 1) {
                    scdGpSid = (int) ctrl.getSaibanNumber(SaibanModel.SBNSID_SCHEDULE,
                                                        SaibanModel.SBNSID_SUB_SCH_GP,
                                                        usrSid);
                }

                RsvScdOperationModel schMdl
                    = __createSchData(frDate, toDate, paramMdl, usrSid, now, scdGpSid, rsSid);
                RsvScdOperationDao opDao = new RsvScdOperationDao(con_);

                if (paramMdl.getRsv210SchKbn() == GSConstReserve.RSV_SCHKBN_GROUP) {
                    //グループスケジュールの登録
                    String grpKey = ymdStr + "-" + rsvSchGrpSid;
                    if (scdGroupList.indexOf(grpKey) < 0) {
                        int scdSid =
                            (int) ctrl.getSaibanNumber(
                                    SaibanModel.SBNSID_SCHEDULE,
                                    SaibanModel.SBNSID_SUB_SCH,
                                    usrSid);

                        schMdl.setScdSid(scdSid);
                        schMdl.setScdUsrSid(rsvSchGrpSid);
                        schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_GROUP);
                        opDao.insertSchData(schMdl);

                        scdGroupList.add(grpKey);
                    }

                } else {
                    //ユーザスケジュールの登録
                    for (String addUser : users) {
                        String userKey = ymdStr + "-" + addUser;
                        if (scdUserList.indexOf(userKey) < 0) {
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

                            scdUserList.add(userKey);
                        }
                    }
                }
            }
        }
        return sidDataList;
    }

    /**
     * <br>[機  能] 登録モデルを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param frDate 開始日時
     * @param toDate 終了日時
     * @param paramMdl Rsv210knParamModel
     * @param usrSid セッションユーザSID
     * @param now 現在日時
     * @param scdGpSid スケジュールグループSID
     * @param rsSid スケジュール予約SID
     * @return 登録モデル
     */
    private RsvScdOperationModel __createSchData(
            UDate frDate, UDate toDate, Rsv210knParamModel paramMdl,
                                            int usrSid, UDate now,
                                            int scdGpSid, int rsSid) {


        RsvScdOperationModel schMdl = new RsvScdOperationModel();
        schMdl.setScdDaily(GSConstSchedule.TIME_EXIST);
        schMdl.setScdFrDate(frDate);
        schMdl.setScdToDate(toDate);
        schMdl.setScdBgcolor(GSConstSchedule.DF_BG_COLOR);
        schMdl.setScdTitle(NullDefault.getString(paramMdl.getRsv210Mokuteki(), ""));
        schMdl.setScdValue(NullDefault.getString(paramMdl.getRsv210Naiyo(), ""));
        schMdl.setScdBiko("");
        schMdl.setScdPublic(GSConstSchedule.DSP_PUBLIC);
        schMdl.setScdAuid(usrSid);
        schMdl.setScdAdate(now);
        schMdl.setScdEuid(usrSid);
        schMdl.setScdEdate(now);
        schMdl.setScdEdit(RsvScheduleBiz.getScdEditKbn(paramMdl.getRsv210RsyEdit()));

        //拡張登録SID
        schMdl.setSceSid(-1);

        schMdl.setScdGrpSid(scdGpSid);
        schMdl.setScdRsSid(rsSid);

        return schMdl;
    }
}