package jp.groupsession.v2.rsv.rsv210;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.biz.RsvScheduleBiz;
import jp.groupsession.v2.rsv.dao.RsvAdmConfDao;
import jp.groupsession.v2.rsv.dao.RsvScdOperationDao;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.dao.RsvUserDao;
import jp.groupsession.v2.rsv.model.RsvAdmConfModel;
import jp.groupsession.v2.rsv.model.RsvHidDayModel;
import jp.groupsession.v2.rsv.model.RsvHidGroupModel;
import jp.groupsession.v2.rsv.model.RsvHidModel;
import jp.groupsession.v2.rsv.model.RsvHidSisetuModel;
import jp.groupsession.v2.rsv.model.RsvUserModel;
import jp.groupsession.v2.rsv.model.other.ExtendedLabelValueModel;
import jp.groupsession.v2.rsv.model.other.RsvSchAdmConfModel;
import jp.groupsession.v2.rsv.model.other.ScheduleRsvModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 施設予約一括登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
  *
 * @author JTS
*/
public class Rsv210Biz extends AbstractReserveBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv210Biz.class);
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
    public Rsv210Biz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv210ParamModel
     * @param pconfig プラグイン情報
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Rsv210ParamModel paramMdl, PluginConfig pconfig) throws SQLException {

        //登録者名をセット
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        paramMdl.setRsv210Torokusya(usrMdl.getUsisei() + "  " + usrMdl.getUsimei());

        //時分コンボ設定
        __setTimeCombo(paramMdl);

        //初期表示時はコンボ選択値にデフォルト値を設定
        if (paramMdl.isRsv210InitFlg()) {
            __setDefHourMin(paramMdl);
        }

        //登録対象の施設情報を取得
        __setTargetSisetuList(paramMdl);

        //スケジュール登録情報を取得
        __setUserList(paramMdl);

        paramMdl.setRsv210InitFlg(false);

        //スケジュール使用有無
        if (pconfig.getPlugin(GSConstReserve.PLUGIN_ID_SCHEDULE) != null) {
            paramMdl.setSchedulePluginKbn(GSConst.PLUGIN_USE);

            //スケジュール管理者設定 共有範囲を取得する
            SchDao schDao = new SchDao(con_);
            paramMdl.setRsv210SchCrangeKbn(schDao.getSadCrange());

            //閲覧不可のグループ、ユーザを設定
            int sessionUserSid = reqMdl_.getSmodel().getUsrsid();
            paramMdl.setRsv210SchNotAccessGroupList(schDao.getNotRegistGrpList(sessionUserSid));
            paramMdl.setRsv210SchNotAccessUserList(schDao.getNotRegistUserList(sessionUserSid));
        } else {
            paramMdl.setSchedulePluginKbn(GSConst.PLUGIN_NOT_USE);
        }

    }

    /**
     * <br>[機  能] 選択施設情報リストをセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv210ParamModel
     * @throws SQLException SQL実行時例外
     */
    private void __setTargetSisetuList(Rsv210ParamModel paramMdl) throws SQLException {

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
        String yrkFrom = null;
        if (!hiddArray.isEmpty()) {

            for (String hiddKey : hiddArray) {

                //キーから日付部分を取得
                String hiddDayKey = hiddKey.substring(0, hiddKey.indexOf("-"));

                if (saveDay == null) {
                    saveDay = hiddDayKey;
                    saveHiddDayKey = hiddDayKey;
                    yrkFrom = hiddDayKey;
                } else if (!saveDay.equals(hiddDayKey)) {

                    ArrayList<RsvHidModel> hiddDayList = dao.selectHidSisetuList(searchArray);
                    hiddList.add(__getDaylySisetuList(hiddDayList, saveDay, saveHiddDayKey));

                    //配列とキーを初期化
                    searchArray = new ArrayList<String>();
                    saveDay = hiddDayKey;
                    saveHiddDayKey = hiddDayKey;

                    //一括予約開始日付を取得
                    if (__formatYrkDayStr(yrkFrom) > __formatYrkDayStr(hiddDayKey)) {
                        yrkFrom = hiddDayKey;
                    }
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

        //一括予約開始日付を設定
        if (yrkFrom != null) {
            paramMdl.setRsv210YrkFrom(yrkFrom);
        } else {
            paramMdl.setRsv210YrkFrom(paramMdl.getRsvDspFrom());
        }

        paramMdl.setRsvIkkatuTorokuHiddenList(hiddList);
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
     * <br>[機  能] 時分コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv210ParamModel
     * @throws SQLException SQL実行例外
     */
    private void __setTimeCombo(Rsv210ParamModel paramMdl) throws SQLException {
        //時コンボの設定
        paramMdl.setRsv210HourComboList(__getHourCombo());
        //分コンボの設定
        paramMdl.setRsv210MinuteComboList(__getMinuteCombo());
    }

    /**
     * <br>[機  能] 期間の時・分・編集権限の初期値を設定する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv210ParamModel
     * @throws SQLException SQL実行時例外
     */
    private void __setDefHourMin(Rsv210ParamModel paramMdl) throws SQLException {

        //セッションユーザSIDを取得
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int usrSid = usrMdl.getUsrsid();

        RsvUserDao dao = new RsvUserDao(con_);
        RsvUserModel conf = dao.select(usrSid);
        if (conf == null) {
            paramMdl.setRsv210SelectedHourFr(GSConstReserve.YRK_DEFAULT_START_HOUR);
            paramMdl.setRsv210SelectedMinuteFr(GSConstReserve.YRK_DEFAULT_START_MINUTE);
            paramMdl.setRsv210SelectedHourTo(GSConstReserve.YRK_DEFAULT_END_HOUR);
            paramMdl.setRsv210SelectedMinuteTo(GSConstReserve.YRK_DEFAULT_END_MINUTE);

        } else {
            int hourFr = 0;
            int minFr = 0;
            int hourTo = 0;
            int minTo = 0;
            if (conf.getRsuIniFrDate() == null) {
                hourFr = GSConstReserve.YRK_DEFAULT_START_HOUR;
                minFr = GSConstReserve.YRK_DEFAULT_START_MINUTE;
            } else {
                hourFr = conf.getRsuIniFrDate().getIntHour();
                minFr = conf.getRsuIniFrDate().getIntMinute();
            }
            if (conf.getRsuIniToDate() == null) {
                hourTo = GSConstReserve.YRK_DEFAULT_END_HOUR;
                minTo = GSConstReserve.YRK_DEFAULT_END_MINUTE;
            } else {
                hourTo = conf.getRsuIniToDate().getIntHour();
                minTo = conf.getRsuIniToDate().getIntMinute();
            }

            paramMdl.setRsv210SelectedHourFr(hourFr);
            paramMdl.setRsv210SelectedMinuteFr(minFr);
            paramMdl.setRsv210SelectedHourTo(hourTo);
            paramMdl.setRsv210SelectedMinuteTo(minTo);
        }

        RsvCommonBiz rsvBiz = new RsvCommonBiz();
        paramMdl.setRsv210RsyEdit(rsvBiz.getInitEditAuth(con_, conf));
    }

    /**
     * <br>[機  能] 時コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return labelList 時コンボリスト
     */
    private ArrayList<LabelValueBean> __getHourCombo() {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        //0時～23時まででコンボを作成
        for (int i = 0; i <= 23; i++) {
            labelList.add(
                    new LabelValueBean(
                            String.valueOf(i),
                            String.valueOf(i)));
        }
        return labelList;
    }

    /**
     * <br>[機  能] 分コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return labelList 分コンボリスト
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getMinuteCombo() throws SQLException {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        //施設予約管理者設定より時間間隔を取得
        RsvAdmConfDao confDao = new RsvAdmConfDao(con_);
        RsvAdmConfModel confModel = confDao.select();
        int confMin = GSConstReserve.DF_HOUR_DIVISION;
        if (confModel != null) {
            confMin = confModel.getRacHourDiv();
        }

        //コンボ作成
        int min = 0;
        for (int i = 1; min < 60; i++) {
            labelList.add(
                    new LabelValueBean(
                            StringUtil.toDecFormat(min, "00"),
                            String.valueOf(min)));
            min = i * confMin;
       }

        return labelList;
    }

    /**
     * <br>[機  能] 同時登録ユーザ情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv210ParamModel
     * @throws SQLException SQL実行時例外
     */
    private void __setUserList(Rsv210ParamModel paramMdl)
        throws SQLException {

        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int usrSid = usrMdl.getUsrsid();
        //グループラベル
        paramMdl.setRsv210GroupLabel(getGroupLabelList(usrSid));

        //デフォルト表示グループ
        RsvScheduleBiz rsvSchBiz = new RsvScheduleBiz();
        String dfGpSidStr = rsvSchBiz.getDispDefaultGroupSidStr(con_, usrSid);
        int dfGpSid = RsvScheduleBiz.getDspGroupSid(dfGpSidStr);
        int dspGpSid = 0;
        boolean myGroupFlg = false;

        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getRsv210GroupSid(), dfGpSidStr);

        dspGpSidStr = getEnableSelectGroup(paramMdl.getRsv210GroupLabel(),
                dspGpSidStr,
                dfGpSidStr);

        if (RsvScheduleBiz.isMyGroupSid(dspGpSidStr)) {
            dspGpSid = RsvScheduleBiz.getDspGroupSid(dspGpSidStr);
            paramMdl.setRsv210GroupSid(dspGpSidStr);
            myGroupFlg = true;
        } else {
            dspGpSid = NullDefault.getInt(paramMdl.getRsv210GroupSid(), dfGpSid);
            paramMdl.setRsv210GroupSid(dspGpSidStr);
        }


        //同時登録スケジュールグループリスト
        paramMdl.setRsv210SchGroupLabel(rsvSchBiz.getSchGroupCombo(con_, reqMdl_, usrSid));

        //除外するユーザSIDを設定
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        usrSids.add(new Integer(GSConstUser.SID_ADMIN));

        //追加済みユーザSID
        ArrayList<Integer> list = null;
        ArrayList<CmnUsrmInfModel> selectUsrList = null;
        String[] users = paramMdl.getSv_users();

        if (users != null && users.length > 0) {
            list = new ArrayList<Integer>();
            for (int i = 0; i < users.length; i++) {
                list.add(new Integer(users[i]));
                //同時登録ユーザを所属リストから除外する
                usrSids.add(new Integer(users[i]));
            }
        }

        UserBiz userBiz = new UserBiz();
        if (list != null && list.size() > 0) {
            selectUsrList = (ArrayList<CmnUsrmInfModel>) userBiz.getUserList(con_, list);
        }

        ArrayList<CmnUsrmInfModel> belongList
                    = userBiz.getBelongUserList(con_,
                                                dspGpSid,
                                                usrSids,
                                                usrSid,
                                                myGroupFlg);

        //グループ所属ユーザラベル
        RsvCommonBiz rsvBiz = new RsvCommonBiz();
        rsvBiz.removeNotRegistUser(con_, belongList, usrSid);
        paramMdl.setRsv210BelongLabel(belongList);

        //同時登録ユーザラベル
        paramMdl.setRsv210SelectUsrLabel(selectUsrList);

        //スケジュールを登録するユーザがいる場合、登録するユーザの名称をセット
        if (paramMdl.getSv_users() != null && paramMdl.getSv_users().length > 0) {
            setUserName(paramMdl, paramMdl.getSv_users());
        }
    }

    /**
     * <br>[機  能] スケジュールSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rsSid スケジュールリレーションSID
     * @return ret スケジュールSID
     * @throws SQLException SQL実行時例外
     */
    public int getScdSid(int rsSid) throws SQLException {

        RsvScdOperationDao rsvSchDao = new RsvScdOperationDao(con_);
        int scdSid = rsvSchDao.getScdSidFromRsSid(rsSid);

        return scdSid;
    }

    /**
     * <br>[機  能] 表示グループ用のグループリストを取得する
     * <br>[解  説] 管理者設定の共有範囲が「ユーザ全員で共有」の場合有効な全てのグループを取得する。
     * <br>         「所属グループ内のみ共有可」の場合、ユーザが所属するグループのみを返す。
     * <br>[備  考]
     *
     * @param usrSid ユーザSID
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<ExtendedLabelValueModel> getGroupLabelList(int usrSid) throws SQLException {

        List<ExtendedLabelValueModel> labelList = null;

        RsvScheduleBiz rsvSchBiz = new RsvScheduleBiz();
        labelList =
            rsvSchBiz.getGroupLabelForSchedule(
                con_, reqMdl_, usrSid, false);

        return labelList;
    }

    /**
     * <br>スケジュールSIDからスケジュール情報を取得する
     * @param req リクエスト
     * @param scdSid スケジュールSID
     * @param adminConf 管理者設定
     * @param con コネクション
     * @return ScheduleSearchModel
     * @throws SQLException SQL実行時例外
     */
    public ScheduleRsvModel getSchData(HttpServletRequest req,
                                        int scdSid,
                                        RsvSchAdmConfModel adminConf,
                                        Connection con)
        throws SQLException {

        ScheduleRsvModel scdMdl = null;
        CmnUsrmInfModel uMdl = null;

        try {

            RsvScdOperationDao rsvSchDao = new RsvScdOperationDao(con_);
            scdMdl = rsvSchDao.getSchData(scdSid, reqMdl_.getSmodel().getUsrsid());

            if (scdMdl == null || (scdMdl != null && scdMdl.getScdSid() < 1)) {
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
                scdMdl.setScdUsrSei(
                        getUsrName(
                                req,
                                scdMdl.getScdUsrSid(),
                                scdMdl.getScdUsrKbn(),
                                con));
            }
        } catch (SQLException e) {
            log__.error("スケジュール情報の取得に失敗" + e);
            throw e;
        }

        return scdMdl;
    }

    /**
     * <br>ユーザSIDとユーザ区分からユーザ氏名を取得する
     * @param req リクエスト
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ区分
     * @param con コネクション
     * @return String ユーザ氏名
     * @throws SQLException SQL実行時例外
     */
    public String getUsrName(HttpServletRequest req, int usrSid, int usrKbn, Connection con)
    throws SQLException {
        String ret = "";
        if (usrKbn == GSConstSchedule.USER_KBN_GROUP) {

            if (usrSid == GSConstSchedule.SCHEDULE_GROUP) {
                GsMessage gsMsg = new GsMessage();
                ret = gsMsg.getMessage(req, "cmn.group");
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
     * <br>[機  能] スケジュール登録ユーザ名一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv210ParamModel
     * @param users 選択されているユーザのユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void setUserName(Rsv210ParamModel paramMdl, String[] users) throws SQLException {

        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> usrArray
            = userBiz.getBaseUserList(con_, users);

        paramMdl.setUserNameArray(usrArray);
    }

    /**
     * <br>[機  能] 施設予約 予約日付 をint型へ変換する
     * <br>[解  説]
     * <br>[備  考]
     * @param dayStr 日付文字列
     * @return int型へ変換した日付文字列
     */
    private int __formatYrkDayStr(String dayStr) {
        if (StringUtil.isNullZeroString(dayStr)
        || !ValidateUtil.isNumber(dayStr)
        || dayStr.length() != 8) {
            return 99999999;
        }

        return Integer.parseInt(dayStr);
    }
}