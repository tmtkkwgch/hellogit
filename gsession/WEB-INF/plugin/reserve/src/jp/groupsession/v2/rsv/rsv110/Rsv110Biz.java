package jp.groupsession.v2.rsv.rsv110;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.biz.RsvScheduleBiz;
import jp.groupsession.v2.rsv.dao.RsvAdmConfDao;
import jp.groupsession.v2.rsv.dao.RsvScdOperationDao;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.dao.RsvSisKryrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisKyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisRyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.dao.RsvUserDao;
import jp.groupsession.v2.rsv.model.RsvAdmConfModel;
import jp.groupsession.v2.rsv.model.RsvScdOperationModel;
import jp.groupsession.v2.rsv.model.RsvSisKryrkModel;
import jp.groupsession.v2.rsv.model.RsvSisRyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisYrkModel;
import jp.groupsession.v2.rsv.model.RsvUserModel;
import jp.groupsession.v2.rsv.model.other.ExtendedLabelValueModel;
import jp.groupsession.v2.rsv.model.other.RsvSchAdmConfModel;
import jp.groupsession.v2.rsv.model.other.ScheduleRsvModel;
import jp.groupsession.v2.rsv.pdf.RsvTanPdfModel;
import jp.groupsession.v2.rsv.pdf.RsvTanPdfUtil;
import jp.groupsession.v2.rsv.rsv070.Rsv070Model;
import jp.groupsession.v2.rsv.rsv210.Rsv210Model;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.SmlSender;
import jp.groupsession.v2.sml.model.SmlSenderModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 施設予約登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv110Biz extends AbstractReserveBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv110Biz.class);
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
    public Rsv110Biz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 画面表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param pconfig プラグイン情報
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションルートパス
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Rsv110ParamModel paramMdl, PluginConfig pconfig, int userSid,
            String appRootPath)
            throws SQLException {

        //印刷区分使用フラグの設定
        paramMdl.setRsvPrintUseKbn(RsvCommonBiz.getPrintUseKbn(appRootPath));

        String procMode = paramMdl.getRsv110ProcMode();

        //新規モード
        if (procMode.equals(GSConstReserve.PROC_MODE_SINKI)) {
            log__.debug("新規モード");
            __setSinkiData(paramMdl);
            //編集モード or 複写して登録モード
        } else if (procMode.equals(GSConstReserve.PROC_MODE_EDIT)
                || procMode.equals(GSConstReserve.PROC_MODE_COPY_ADD)) {
            log__.debug("編集モード or 複写して登録モード");
            __setEditData(paramMdl, pconfig, userSid);
            //ポップアップモード
        } else if (procMode.equals(GSConstReserve.PROC_MODE_POPUP)) {
            log__.debug("ポップアップ表示モード");
            __setPopUpData(reqMdl_, paramMdl, pconfig, userSid);
        }

        //初期表示フラグOFF
        paramMdl.setRsv110InitFlg(false);

        //スケジュール使用有無
        if (pconfig.getPlugin(GSConstReserve.PLUGIN_ID_SCHEDULE) != null) {
            paramMdl.setSchedulePluginKbn(GSConst.PLUGIN_USE);
            log__.debug("スケジュール使用");

            //スケジュール管理者設定 共有範囲を取得する
            SchDao schDao = new SchDao(con_);
            paramMdl.setRsv110SchCrangeKbn(schDao.getSadCrange());
        } else {
            paramMdl.setSchedulePluginKbn(GSConst.PLUGIN_NOT_USE);
            log__.debug("スケジュール使用不可");
        }
    }

    /**
     * <br>[機  能] 新規モード画面表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @throws SQLException SQL実行時例外
     */
    private void __setSinkiData(Rsv110ParamModel paramMdl) throws SQLException {

        boolean initFlg = paramMdl.isRsv110InitFlg();

        //登録者名をセット
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        paramMdl.setRsv110Torokusya(usrMdl.getUsisei() + "  " + usrMdl.getUsimei());

        //年コンボ設定用 開始年
        String dspDate = paramMdl.getRsv110SinkiDefaultDate();
        UDate frUd = new UDate();
        frUd.setDate(dspDate);
        frUd.setHour(GSConstReserve.YRK_DEFAULT_START_HOUR);
        frUd.setMinute(GSConstReserve.YRK_DEFAULT_START_MINUTE);

        //年コンボ設定用 終了年
        UDate toUd = new UDate();
        toUd.setDate(dspDate);
        toUd.setHour(GSConstReserve.YRK_DEFAULT_END_HOUR);
        toUd.setMinute(GSConstReserve.YRK_DEFAULT_END_MINUTE);

        //登録日付の初期化
        paramMdl.setRsv110AddDate(null);

        //期間コンボ設定
        __setKikanCombo(paramMdl, frUd, toUd);

        if (initFlg) {
            //初期表示時はコンボの選択値を設定
            __setComboSelectValueInit(paramMdl, frUd, toUd);
        }

        //施設グループ情報を取得
        Rsv070Model grpMdl = __getGroupData(paramMdl.getRsv110RsdSid());
        int rskSid = 0;
        if (grpMdl != null) {
            rskSid = grpMdl.getRskSid();

            //施設区分毎に入力可能な項目を設定
            __setSisetuHeader(paramMdl, rskSid);

            //施設グループ情報セット
            __setGroupData(paramMdl, grpMdl);

            if (initFlg) {
                if (RsvCommonBiz.isRskKbnRegCheck(rskSid)) {
                    __setInitSisYrkData(paramMdl, usrMdl, rskSid);
                }
            }
        }


        //同時登録ユーザリストセット
        __setUserList(paramMdl, null);

        //初回表示時のみ繰り返し登録情報をセット
        if (paramMdl.isRsv111InitFlg()) {
            __setKakutyoData(
                    paramMdl, paramMdl.getRsv110SinkiDefaultDate(), null, null, usrMdl, rskSid);
        }
    }

    /**
     * <br>[機  能] 編集モード画面表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param pconfig プラグイン情報
     * @param userSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __setEditData(Rsv110ParamModel paramMdl, PluginConfig pconfig, int userSid)
            throws SQLException {

        boolean initFlg = paramMdl.isRsv110InitFlg();
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);

        //予約情報取得
        Rsv110SisetuModel yrkMdl = getYoyakuData(paramMdl);
        if (yrkMdl != null) {
            paramMdl.setRsvDataFlg(true);

            //複写して登録
            if (paramMdl.getRsv110ProcMode().equals(GSConstReserve.PROC_MODE_COPY_ADD)) {
                //登録者名をセット
                paramMdl.setRsv110Torokusya(usrMdl.getUsisei() + "  " + usrMdl.getUsimei());

                //編集
            } else {
                //登録者名をセット
                __setTorokusya(paramMdl, yrkMdl);

                //施設SIDを画面に設定
                paramMdl.setRsvSelectedSisetuSid(yrkMdl.getRsdSid());
            }

            if (paramMdl.getRsv110ProcMode().equals(GSConstReserve.PROC_MODE_EDIT)) {
                //登録日付をセット
                __setTorokuDate(paramMdl, yrkMdl);
            }

            //期間コンボ設定
            __setKikanCombo(paramMdl, yrkMdl.getRsyFrDate(), yrkMdl.getRsyToDate());

            //施設グループ情報を取得
            Rsv070Model grpMdl = __getGroupData(yrkMdl.getRsdSid());
            int rskSid = 0;
            if (grpMdl != null) {
                rskSid = grpMdl.getRskSid();

                //施設区分毎に入力可能な項目を設定
                __setSisetuHeader(paramMdl, rskSid);

                //施設グループ情報セット
                __setGroupData(paramMdl, grpMdl);
            }

            //予約データ修正可否の判定
            __setEditAuth(paramMdl, yrkMdl, grpMdl);

            if (initFlg) {

                //取得値をセット
                __setYoyakuData(paramMdl, yrkMdl, pconfig, userSid);

                __setKbnYoyakuData(paramMdl, yrkMdl, rskSid);


                //初期表示時はコンボの選択値を設定
                __setComboSelectValueEdit(
                        paramMdl,
                        yrkMdl.getRsyFrDate(),
                        yrkMdl.getRsyToDate());
            }

            //同時登録ユーザリストセット
            __setUserList(paramMdl, yrkMdl);

            //初回表示時のみ繰り返し登録情報をセット
            if (paramMdl.isRsv111InitFlg()) {
                RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con_);
                RsvSisRyrkModel ryrkMdl = ryrkDao.select(yrkMdl.getRsrRsid());
                __setKakutyoData(
                        paramMdl, yrkMdl.getRsyFrDate().getDateString(),
                        ryrkMdl, yrkMdl, usrMdl, rskSid);
            }

            //関連するスケジュールデータ存在チェック
            __existSchData(paramMdl);

        }
    }

    /**
     * <br>[機  能] 施設予約データに対応するスケジュールデータが存在するかチェック
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @throws SQLException SQL実行時例外
     */
    private void __existSchData(Rsv110ParamModel paramMdl)
            throws SQLException {

        //施設予約情報取得
        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        RsvSisYrkModel selParam = new RsvSisYrkModel();
        selParam.setRsySid(paramMdl.getRsv110RsySid());
        RsvSisYrkModel yrkRet = yrkDao.select(selParam);

        if (yrkRet == null) {
            //施設予約情報が無い
            paramMdl.setRsv110ExistSchDateFlg(false);
            //スケジュールデータへ反映させない
            paramMdl.setRsv110ScdReflection(GSConstReserve.SCD_REFLECTION_NO);
            return;
        }

        RsvScdOperationDao scdDao = new RsvScdOperationDao(con_);
        ArrayList<RsvScdOperationModel> scdRet = null;
        if (yrkRet.getScdRsSid() < 0) {
            //関連付いたスケジュールが無い
            paramMdl.setRsv110ExistSchDateFlg(false);
            //スケジュールデータへ反映させない
            paramMdl.setRsv110ScdReflection(GSConstReserve.SCD_REFLECTION_NO);
            return;
        }
        scdRet = scdDao.selectSchList(yrkRet.getScdRsSid());

        if (scdRet.isEmpty()) {
            //関連付いたスケジュールが無い
            paramMdl.setRsv110ExistSchDateFlg(false);
            //スケジュールデータへ反映させない
            paramMdl.setRsv110ScdReflection(GSConstReserve.SCD_REFLECTION_NO);
            return;
        }
        paramMdl.setRsv110ExistSchDateFlg(true);
    }

    /**
     * <br>[機  能] ポップアップモード画面表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl フォーム
     * @param pconfig プラグイン情報
     * @param userSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __setPopUpData(
            RequestModel reqMdl, Rsv110ParamModel paramMdl, PluginConfig pconfig,
            int userSid)
                    throws SQLException {

        //予約情報取得
        Rsv110SisetuModel yrkMdl = getYoyakuData(paramMdl);
        if (yrkMdl != null) {

            //施設SIDを画面に設定
            paramMdl.setRsvSelectedSisetuSid(yrkMdl.getRsdSid());

            //期間コンボ設定
            __setKikanCombo(paramMdl, yrkMdl.getRsyFrDate(), yrkMdl.getRsyToDate());

            __setComboSelectValueEdit(
                    paramMdl,
                    yrkMdl.getRsyFrDate(),
                    yrkMdl.getRsyToDate());

            //登録者名をセット
            __setTorokusya(paramMdl, yrkMdl);

            //取得値をセット
            __setYoyakuData(paramMdl, yrkMdl, pconfig, userSid);

            //施設グループ情報を取得
            Rsv070Model grpMdl = __getGroupData(yrkMdl.getRsdSid());
            if (grpMdl != null) {
                int rskSid = grpMdl.getRskSid();

                __setKbnYoyakuData(paramMdl, yrkMdl, rskSid);

                //施設区分毎に表示可能な項目を設定
                __setSisetuHeader(paramMdl, rskSid);

                //施設グループ情報セット
                __setGroupData(paramMdl, grpMdl);
            }

            //同時登録ユーザリストセット
            __setUserList(paramMdl, yrkMdl);
        }
    }

    /**
     * <br>[機  能] 予約情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @return ret 予約情報
     * @throws SQLException SQL実行時例外
     */
    public Rsv110SisetuModel getYoyakuData(Rsv110ParamModel paramMdl) throws SQLException {
        return __getYoyakuData(paramMdl.getRsv110RsySid());
    }

    /**
     * <br>[機  能] 予約情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param rsySid 予約SID
     * @return ret 予約情報
     * @throws SQLException SQL実行時例外
     */
    private Rsv110SisetuModel __getYoyakuData(int rsySid) throws SQLException {

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        Rsv110SisetuModel ret = yrkDao.selectYoyakuEditData(rsySid);

        return ret;
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
     * <br>[機  能] 施設グループ情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param rsdSid 施設SID
     * @return ret 取得結果
     * @throws SQLException SQL実行時例外
     */
    public Rsv210Model getGroupData(int rsdSid) throws SQLException {

        RsvSisDataDao dataDao = new RsvSisDataDao(con_);
        Rsv210Model ret = dataDao.getGroupCheckModel(rsdSid);

        return ret;
    }

    /**
     * <br>[機  能] 施設予約情報削除処理
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ
     * @throws SQLException SQL実行時例外
     */
    public void doYoyakuDelete(Rsv110ParamModel paramMdl)
    throws SQLException {

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);

        //スケジュールと関連付いている & 「スケジュールへ反映」が選択されている
        if (paramMdl.getRsv110ScdRsSid() > 0
                && paramMdl.getRsv110ScdReflection() == GSConstReserve.SCD_REFLECTION_OK) {

            //関連付いているスケジュールを削除
            RsvSisYrkModel selParam = new RsvSisYrkModel();
            selParam.setRsySid(paramMdl.getRsv110RsySid());
            RsvSisYrkModel ret = yrkDao.select(selParam);
            if (ret != null) {

                //スケジュールと関連付いている施設予約が他に存在する場合、
                //スケジュールの削除を行なわない
                int scdRssid = ret.getScdRsSid();
                if (yrkDao.getSisYrkCountFromScdRs(scdRssid) <= 1) {

                    //スケジュール拡張SID取得
                    RsvScdOperationDao scdDao = new RsvScdOperationDao(con_);
                    int sceSid = scdDao.selectSceSid(scdRssid);

                    //スケジュールデータ一覧を取得
                    RsvScdOperationDao opDao = new RsvScdOperationDao(con_);
                    ArrayList<RsvScdOperationModel> schDataList =
                        opDao.selectSchList(scdRssid);

                    //スケジュールアクセス不可グループ or ユーザを取得
                    int sessionUserSid = reqMdl_.getSmodel().getUsrsid();
                    SchDao schDao = new SchDao(con_);
                    List<Integer> notAccessUserList = new ArrayList<Integer>();
                    List<Integer> notAccessGroupList = new ArrayList<Integer>();
                    notAccessGroupList = schDao.getNotRegistGrpList(sessionUserSid);
                    notAccessUserList = schDao.getNotRegistUserList(sessionUserSid);

                    //スケジュールを削除
                    List<String> delUsers = new ArrayList<String>();
                    for (RsvScdOperationModel mdl : schDataList) {
                        List<Integer> notAccessList = null;
                        if (mdl.getScdUsrKbn() == GSConstReserve.RSV_SCHKBN_GROUP) {
                            notAccessList = notAccessGroupList;
                        } else {
                            notAccessList = notAccessUserList;
                        }
                        //アクセス不可グループ or ユーザ を削除対象から除外する
                        if (notAccessList.indexOf(mdl.getScdUsrSid()) < 0) {
                            delUsers.add(String.valueOf(mdl.getScdUsrSid()));
                        }
                    }

                    //スケジュール削除
//                    scdDao.deleteScdTi(scdRssid);
                    opDao.deleteScdTiWithUsers(
                            scdRssid, delUsers.toArray(new String[delUsers.size()]));

                    //スケジュールが繰り返し登録されているデータ
                    if (sceSid != -1) {
                        //スケジュール拡張に紐付くスケジュールが無くなった
                        if (scdDao.selectExDataCnt(sceSid) == 0) {
                            //スケジュール拡張データ削除
                            scdDao.deleteExData(sceSid);
                        }
                    }
                }
            }
        }

        //施設予約データを削除
        RsvSisYrkModel delParam = new RsvSisYrkModel();
        delParam.setRsySid(paramMdl.getRsv110RsySid());
        yrkDao.delete(delParam);
        kyrkDao.delete(paramMdl.getRsv110RsySid());
    }

    /**
     * <br>[機  能] 拡張予約データをセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param dspDate 処理日付(yyyyMMdd)
     * @param bean 拡張予約モデル
     * @param yrkMdl 施設予約情報
     * @param usrMdl ユーザモデル
     * @param rskSid 施設区分
     * @throws SQLException SQL実行時例外
     */
    private void __setKakutyoData(Rsv110ParamModel paramMdl,
            String dspDate,
            RsvSisRyrkModel bean,
            Rsv110SisetuModel yrkMdl,
            BaseUserModel usrMdl,
            int rskSid)
                    throws SQLException {
        //コンボ設定用 表示日
        UDate selDate = new UDate();
        selDate.setDate(dspDate);
        //毎年 月
        paramMdl.setRsv111RsrMonthOfYearly(selDate.getMonth());
        //毎年 日
        paramMdl.setRsv111RsrDayOfYearly(selDate.getIntDay());

        if (bean == null) {

            //施設予約拡張SID
            paramMdl.setRsv111RsrRsid(-1);
            //拡張区分
            paramMdl.setRsv111RsrKbn(GSConstReserve.KAKUTYO_KBN_EVERY_DAY);
            //曜日(日曜)
            paramMdl.setRsv111RsrDweek1(GSConstReserve.WEEK_CHECK_OFF);
            //曜日(月曜)
            paramMdl.setRsv111RsrDweek2(GSConstReserve.WEEK_CHECK_OFF);
            //曜日(火曜)
            paramMdl.setRsv111RsrDweek3(GSConstReserve.WEEK_CHECK_OFF);
            //曜日(水曜)
            paramMdl.setRsv111RsrDweek4(GSConstReserve.WEEK_CHECK_OFF);
            //曜日(木曜)
            paramMdl.setRsv111RsrDweek5(GSConstReserve.WEEK_CHECK_OFF);
            //曜日(金曜)
            paramMdl.setRsv111RsrDweek6(GSConstReserve.WEEK_CHECK_OFF);
            //曜日(土曜)
            paramMdl.setRsv111RsrDweek7(GSConstReserve.WEEK_CHECK_OFF);
            //週
            paramMdl.setRsv111RsrWeek(GSConstReserve.COMBO_DEFAULT_VALUE);
            //日
            paramMdl.setRsv111RsrDay(GSConstReserve.COMBO_DEFAULT_VALUE);


            //振替区分
            paramMdl.setRsv111RsrTranKbn(GSConstReserve.FURIKAE_NO);

            //年コンボ設定用 開始年
            UDate frUd = new UDate();
            frUd.setDate(dspDate);
            frUd.setHour(GSConstReserve.YRK_DEFAULT_START_HOUR);
            frUd.setMinute(GSConstReserve.YRK_DEFAULT_START_MINUTE);

            //年コンボ設定用 終了年
            UDate toUd = new UDate();
            toUd.setDate(dspDate);
            toUd.setHour(GSConstReserve.YRK_DEFAULT_END_HOUR);
            toUd.setMinute(GSConstReserve.YRK_DEFAULT_END_MINUTE);

            //反映期間from 年
            paramMdl.setRsv111RsrDateYearFr(String.valueOf(frUd.getYear()));
            //反映期間from 月
            paramMdl.setRsv111RsrDateMonthFr(String.valueOf(frUd.getMonth()));
            //反映期間from 日
            paramMdl.setRsv111RsrDateDayFr(String.valueOf(frUd.getIntDay()));
            //反映期間To 年
            paramMdl.setRsv111RsrDateYearTo(String.valueOf(toUd.getYear()));
            //反映期間To 月
            paramMdl.setRsv111RsrDateMonthTo(String.valueOf(toUd.getMonth()));
            //反映期間To 日
            paramMdl.setRsv111RsrDateDayTo(String.valueOf(toUd.getIntDay()));

            //時間・編集権限のデフォルト値を設定
            __setDefHourMin(paramMdl);
            //利用目的
            paramMdl.setRsv111RsrMok("");
            //内容
            paramMdl.setRsv111RsrBiko("");

            if (RsvCommonBiz.isRskKbnRegCheck(rskSid)) {
                //施設予約区分別情報
                GroupDao dao = new GroupDao(con_);
                CmnGroupmModel grpMdl = dao.getDefaultGroup(usrMdl.getUsrsid());

                //担当部署
                paramMdl.setRsv111Busyo(grpMdl.getGrpName());
                //担当・使用者名
                paramMdl.setRsv111UseName(usrMdl.getUsisei() + "  " + usrMdl.getUsimei());
                //連絡先
                CmnUsrmInfDao uInfDao = new CmnUsrmInfDao(con_);
                CmnUsrmInfModel uInfMdl = uInfDao.select(usrMdl.getUsrsid());
                paramMdl.setRsv111Contact(NullDefault.getString(uInfMdl.getUsiTelNai1(), ""));

                //施設区分 部屋
                if (rskSid == GSConstReserve.RSK_KBN_HEYA) {
                    paramMdl.setRsv111UseKbn(GSConstReserve.RSY_USE_KBN_NOSET);

                } else if (rskSid == GSConstReserve.RSK_KBN_CAR) {
                    paramMdl.setRsv111PrintKbn(GSConstReserve.RSY_PRINT_KBN_YES);
                }
            }


        } else {

            //施設予約拡張SID
            paramMdl.setRsv111RsrRsid(bean.getRsrRsid());
            //拡張区分
            paramMdl.setRsv111RsrKbn(bean.getRsrKbn());
            //曜日(日曜)
            paramMdl.setRsv111RsrDweek1(bean.getRsrDweek1());
            //曜日(月曜)
            paramMdl.setRsv111RsrDweek2(bean.getRsrDweek2());
            //曜日(火曜)
            paramMdl.setRsv111RsrDweek3(bean.getRsrDweek3());
            //曜日(水曜)
            paramMdl.setRsv111RsrDweek4(bean.getRsrDweek4());
            //曜日(木曜)
            paramMdl.setRsv111RsrDweek5(bean.getRsrDweek5());
            //曜日(金曜)
            paramMdl.setRsv111RsrDweek6(bean.getRsrDweek6());
            //曜日(土曜)
            paramMdl.setRsv111RsrDweek7(bean.getRsrDweek7());
            //週
            paramMdl.setRsv111RsrWeek(bean.getRsrWeek());
            //日
            paramMdl.setRsv111RsrDay(bean.getRsrDay());
            //毎年 月
            if (bean.getRsrMonthYearly() > 0) {
                paramMdl.setRsv111RsrMonthOfYearly(bean.getRsrMonthYearly());
            }
            //毎年 日
            if (bean.getRsrDayYearly() > 0) {
                paramMdl.setRsv111RsrDayOfYearly(bean.getRsrDayYearly());
            }

            //振替区分
            paramMdl.setRsv111RsrTranKbn(bean.getRsrTranKbn());

            UDate frUd = bean.getRsrDateFr();
            UDate toUd = bean.getRsrDateTo();

            //反映期間from 年
            paramMdl.setRsv111RsrDateYearFr(String.valueOf(frUd.getYear()));
            //反映期間from 月
            paramMdl.setRsv111RsrDateMonthFr(String.valueOf(frUd.getMonth()));
            //反映期間from 日
            paramMdl.setRsv111RsrDateDayFr(String.valueOf(frUd.getIntDay()));
            //反映期間To 年
            paramMdl.setRsv111RsrDateYearTo(String.valueOf(toUd.getYear()));
            //反映期間To 月
            paramMdl.setRsv111RsrDateMonthTo(String.valueOf(toUd.getMonth()));
            //反映期間To 日
            paramMdl.setRsv111RsrDateDayTo(String.valueOf(toUd.getIntDay()));

            UDate frTime = bean.getRsrTimeFr();
            UDate toTime = bean.getRsrTimeTo();

            //時間from
            paramMdl.setRsv111RsrTimeHourFr(String.valueOf(frTime.getIntHour()));
            //時間from 分
            paramMdl.setRsv111RsrTimeMinuteFr(String.valueOf(frTime.getIntMinute()));
            //時間To
            paramMdl.setRsv111RsrTimeHourTo(String.valueOf(toTime.getIntHour()));
            //時間To
            paramMdl.setRsv111RsrTimeMinuteTo(String.valueOf(toTime.getIntMinute()));

            //利用目的
            paramMdl.setRsv111RsrMok(NullDefault.getString(bean.getRsrMok(), ""));
            //内容
            paramMdl.setRsv111RsrBiko(NullDefault.getString(bean.getRsrBiko(), ""));
            //権限設定
            paramMdl.setRsv111RsrEdit(bean.getRsrEdit());

            if (RsvCommonBiz.isRskKbnRegCheck(rskSid)) {
                RsvSisKryrkDao kryrkDao = new RsvSisKryrkDao(con_);
                RsvSisKryrkModel kryrkModel = kryrkDao.select(bean.getRsrRsid());
                if (kryrkModel != null) {
                    paramMdl.setRsv111Busyo(NullDefault.getString(kryrkModel.getRkrBusyo(), ""));
                    paramMdl.setRsv111UseName(NullDefault.getString(kryrkModel.getRkrName(), ""));
                    paramMdl.setRsv111UseNum(NullDefault.getString(kryrkModel.getRkrNum(), ""));
                    paramMdl.setRsv111Contact(
                            NullDefault.getString(kryrkModel.getRkrContact(), ""));

                    if (rskSid == GSConstReserve.RSK_KBN_HEYA) {
                        paramMdl.setRsv111UseKbn(kryrkModel.getRkrUseKbn());
                        paramMdl.setRsv111Guide(
                                NullDefault.getString(kryrkModel.getRkrGuide(), ""));
                        paramMdl.setRsv111ParkNum(
                                NullDefault.getString(kryrkModel.getRkrParkNum(), ""));
                    } else if (rskSid == GSConstReserve.RSK_KBN_CAR) {
                        paramMdl.setRsv111PrintKbn(kryrkModel.getRkrPrintKbn());
                        paramMdl.setRsv111Dest(NullDefault.getString(kryrkModel.getRkrDest(), ""));
                    }
                }
            }
        }

        //編集モード時、スケジュール情報を取得する
        String procMode = paramMdl.getRsv110ProcMode();
        if (procMode.equals(GSConstReserve.PROC_MODE_EDIT)) {
            //SCD_RSSIDからスケジュールSIDを取得する
            if (yrkMdl.getScdRsSid() > 0) {

                int scdSid = getScdSid(yrkMdl.getScdRsSid());
                if (scdSid > 0) {
                    //スケジュール管理者設定(共有範囲など)を取得
                    RsvScheduleBiz rsvSchBiz = new RsvScheduleBiz();
                    RsvSchAdmConfModel adminConf = rsvSchBiz.getAdmConfModel(con_);

                    ScheduleRsvModel schMdl =
                            getSchData(reqMdl_, scdSid, adminConf, con_);

                    if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
                        paramMdl.setRsv111SchKbn(GSConstReserve.RSV_SCHKBN_GROUP);

                        //同時登録スケジュールにアクセス不可の場合、未選択に設定する
                        int scdUsrSid = schMdl.getScdUsrSid();
                        if (paramMdl.getRsv110SchNotAccessGroupList().indexOf(scdUsrSid) < 0) {
                            paramMdl.setRsv111SchGroupSid(String.valueOf(scdUsrSid));
                        } else {
                            paramMdl.setRsv111SchGroupSid("-1");
                        }
                    } else {
                        if (schMdl != null
                                && (schMdl.getUsrInfList() == null
                                || schMdl.getUsrInfList().isEmpty())
                                && schMdl.getScdGrpSid() == GSConstSchedule.DF_SCHGP_ID) {

                            ArrayList<CmnUsrmInfModel> myList =
                                    new ArrayList<CmnUsrmInfModel>();

                            CmnUsrmInfModel myMdl = new CmnUsrmInfModel();
                            myMdl.setUsrSid(schMdl.getScdUsrSid());
                            myMdl.setUsiSei(schMdl.getScdUsrSei());
                            myMdl.setUsiMei(schMdl.getScdUsrMei());

                            myList.add(myMdl);
                            schMdl.setUsrInfList(myList);
                        }

                        if (paramMdl.getRsv111SvUsers() == null
                                || paramMdl.getRsv111SvUsers().length == 0) {
                            __setExSaveUsersForDb(paramMdl, schMdl.getUsrInfList());
                        }
                    }
                }

            }
        }

        //初期表示フラグ
        paramMdl.setRsv111InitFlg(false);
    }

    /**
     * <br>[機  能] 期間コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param frDate 予約開始日
     * @param toDate 予約終了日
     * @throws SQLException SQL実行時例外
     */
    private void __setKikanCombo(Rsv110ParamModel paramMdl, UDate frDate, UDate toDate)
            throws SQLException {

        //年コンボの設定
        paramMdl.setRsv110YearComboList(__getYearCombo(frDate, toDate));

        //月コンボの設定
        paramMdl.setRsv110MonthComboList(__getMonthCombo());

        //日コンボの設定
        paramMdl.setRsv110DayComboList(__getDayCombo());

        //時コンボの設定
        paramMdl.setRsv110HourComboList(__getHourCombo());

        //分コンボの設定
        paramMdl.setRsv110MinuteComboList(__getMinuteCombo());
    }

    /**
     * <br>[機  能] 年コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param frDate 予約開始日
     * @param toDate 予約終了日
     * @return labelList 年コンボリスト
     */
    private ArrayList<LabelValueBean> __getYearCombo(UDate frDate,
            UDate toDate) {

        UDate cloneFrDate = frDate.cloneUDate();
        UDate cloneToDate = toDate.cloneUDate();

        //コンボ下限値
        cloneFrDate.addYear(-1);

        int frYear = cloneFrDate.getYear();
        int toYear = cloneToDate.getYear();

        //予約開始日から3年後までを最低範囲とする
        if (toYear - frYear < 3) {
            //下限値として-1年してあるので + 4年を加算
            cloneToDate.setYear(frYear + 4);
        }

        int start = frYear;
        int end = cloneToDate.getYear();

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        //上記で設定した開始年から終了年まででコンボを作成
        GsMessage gsMsg = new GsMessage(reqMdl_);
        for (int i = start; i <= end; i++) {
            labelList.add(
                    new LabelValueBean(
                            gsMsg.getMessage("cmn.year", new String[] {String.valueOf(i)}),
                            String.valueOf(i)));
        }

        return labelList;
    }

    /**
     * <br>[機  能] 月コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return labelList 月コンボリスト
     */
    private ArrayList<LabelValueBean> __getMonthCombo() {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        //1月～12月まででコンボを作成
        GsMessage gsMsg = new GsMessage(reqMdl_);
        for (int i = 1; i <= 12; i++) {
            labelList.add(
                    new LabelValueBean(
                            String.valueOf(i) + gsMsg.getMessage("cmn.month"),
                            String.valueOf(i)));
        }

        return labelList;
    }

    /**
     * <br>[機  能] 日コンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return labelList 日コンボリスト
     */
    private ArrayList<LabelValueBean> __getDayCombo() {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        //1日～31日まででコンボを作成
        GsMessage gsMsg = new GsMessage(reqMdl_);
        for (int i = 1; i <= 31; i++) {
            labelList.add(
                    new LabelValueBean(
                            String.valueOf(i) + gsMsg.getMessage("cmn.day"),
                            String.valueOf(i)));
        }

        return labelList;
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
    private ArrayList<LabelValueBean> __getMinuteCombo()
            throws SQLException {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        //施設予約管理者設定より時間間隔を取得
        RsvAdmConfDao confDao = new RsvAdmConfDao(con_);
        RsvAdmConfModel confModel = confDao.select();
        int confMin = GSConstReserve.DF_HOUR_DIVISION;
        if (confModel != null) {
            if (confModel.getRacHourDiv() > 0) {
                confMin = confModel.getRacHourDiv();
            }
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
     * <br>[機  能] 各コンボの選択値を設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param frDate 予約開始
     * @param toDate 予約終了
     * @throws SQLException SQL実行時例外
     */
    private void __setComboSelectValueInit(
            Rsv110ParamModel paramMdl, UDate frDate, UDate toDate)
                    throws SQLException {

        //セッションユーザSID
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int usrSid = usrMdl.getUsrsid();

        RsvUserDao confDao = new RsvUserDao(con_);
        RsvUserModel conf = confDao.select(usrSid);

        //編集権限
        RsvCommonBiz rsvBiz = new RsvCommonBiz();
        paramMdl.setRsv110RsyEdit(rsvBiz.getInitEditAuth(con_, conf));

        if (conf == null) {
            __setComboSelectValueEdit(paramMdl, frDate, toDate);
            return;
        }

        //個人設定より、時・分の初期値を取得する。
        UDate iniFrDate = conf.getRsuIniFrDate();
        UDate iniToDate = conf.getRsuIniToDate();
        if (iniFrDate == null) {
            iniFrDate = new UDate();
            iniFrDate.setHour(frDate.getIntHour());
            iniFrDate.setMinute(frDate.getIntMinute());
        }
        if (iniToDate == null) {
            iniToDate = new UDate();
            iniToDate.setHour(toDate.getIntHour());
            iniToDate.setMinute(toDate.getIntMinute());
        }

        //年コンボFr選択値
        paramMdl.setRsv110SelectedYearFr(frDate.getYear());
        //月コンボFr選択値
        paramMdl.setRsv110SelectedMonthFr(frDate.getMonth());
        //日コンボFr選択値
        paramMdl.setRsv110SelectedDayFr(frDate.getIntDay());

        //時コンボFr選択値
        paramMdl.setRsv110SelectedHourFr(iniFrDate.getIntHour());
        //分コンボFr選択値
        paramMdl.setRsv110SelectedMinuteFr(iniFrDate.getIntMinute());

        //年コンボTo選択値(初期表示時はその日内に完結するようにセット)
        paramMdl.setRsv110SelectedYearTo(toDate.getYear());
        //月コンボTo選択値
        paramMdl.setRsv110SelectedMonthTo(toDate.getMonth());
        //日コンボTo選択値
        paramMdl.setRsv110SelectedDayTo(toDate.getIntDay());
        //時コンボTo選択値
        paramMdl.setRsv110SelectedHourTo(iniToDate.getIntHour());
        //分コンボTo選択値
        paramMdl.setRsv110SelectedMinuteTo(iniToDate.getIntMinute());
    }

    /**
     * <br>[機  能] 各コンボの選択値を設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param frDate 予約開始
     * @param toDate 予約終了
     * @throws SQLException SQL実行時例外
     */
    private void __setComboSelectValueEdit(
            Rsv110ParamModel paramMdl, UDate frDate, UDate toDate)
                    throws SQLException {

        //年コンボFr選択値
        paramMdl.setRsv110SelectedYearFr(frDate.getYear());
        //月コンボFr選択値
        paramMdl.setRsv110SelectedMonthFr(frDate.getMonth());
        //日コンボFr選択値
        paramMdl.setRsv110SelectedDayFr(frDate.getIntDay());
        //時コンボFr選択値
        paramMdl.setRsv110SelectedHourFr(frDate.getIntHour());
        //分コンボFr選択値
        paramMdl.setRsv110SelectedMinuteFr(frDate.getIntMinute());

        //年コンボTo選択値(初期表示時はその日内に完結するようにセット)
        paramMdl.setRsv110SelectedYearTo(toDate.getYear());
        //月コンボTo選択値
        paramMdl.setRsv110SelectedMonthTo(toDate.getMonth());
        //日コンボTo選択値
        paramMdl.setRsv110SelectedDayTo(toDate.getIntDay());
        //時コンボTo選択値
        paramMdl.setRsv110SelectedHourTo(toDate.getIntHour());
        //分コンボTo選択値
        paramMdl.setRsv110SelectedMinuteTo(toDate.getIntMinute());
    }

    /**
     * <br>[機  能] DBから取得した施設グループ情報をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param dbMdl DB取得結果
     */
    private void __setGroupData(Rsv110ParamModel paramMdl,
            Rsv070Model dbMdl) {

        //所属グループ名
        paramMdl.setRsv110GrpName(NullDefault.getString(dbMdl.getRsgName(), ""));
        //施設区分 */
        paramMdl.setRsv110SisetuKbn(dbMdl.getRskSid());
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
     * <br>[機  能] 予約データ変更権限を判定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param bean 施設予約情報
     * @param grpBean グループ情報
     * @throws SQLException SQL実行時例外
     */
    private void __setEditAuth(
            Rsv110ParamModel paramMdl, Rsv110SisetuModel bean, Rsv070Model grpBean)
                    throws SQLException {

        boolean auth = false;
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);

        /***********************************************
         *
         * 強権限を判定
         * (どの権限設定の場合でも強制的に変更可能)
         *
         ***********************************************/
        //システム管理者グループ所属
        CommonBiz cmnBiz = new CommonBiz();
        boolean admFlg = cmnBiz.isPluginAdmin(con_, usrMdl, GSConstReserve.PLUGIN_ID_RESERVE);
        if (admFlg) {
            auth = true;
        }

        /***********************************************
        *
        * 施設グループ管理責任者か判定
        * (どの権限設定の場合でも強制的に変更可能)
        *
        ***********************************************/
        RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
        if (!auth) {
            if (rsvCmnBiz.isGroupAdmin(con_, bean.getRsdSid(), usrMdl.getUsrsid())) {
                auth = true;
            }
        }

        /***********************************************
         *
         * アクセス権限を判定
         *
         ***********************************************/
        if (!auth) {
            boolean accessOkFlg = false;
            accessOkFlg = _isEditRsvGrp(con_, grpBean.getRsgSid(), usrMdl.getUsrsid(), admFlg);

            //編集権限
            if (!accessOkFlg) {
                paramMdl.setRsv110EditAuth(false);
                return;
            }
        }

        /***********************************************
         *
         * 強権限が無い場合は細かくチェック
         *
         ***********************************************/
        if (!auth) {
            int rsyEdit = bean.getRsyEdit();
            auth = rsvCmnBiz.isAbleEditLowAuth(con_, bean.getRsyAuid()
                    , rsyEdit, bean.getScdRsSid(), usrMdl.getUsrsid());

        }

        paramMdl.setRsv110EditAuth(auth);
    }

    /**
     * <br>[機  能] DBから取得した予約情報をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param dbMdl DBからの取得値
     */
    private void __setTorokusya(Rsv110ParamModel paramMdl, Rsv110SisetuModel dbMdl) {

        //登録者名
        paramMdl.setRsv110Torokusya(
                NullDefault.getString(dbMdl.getUsiSei(), "")
                + "  "
                + NullDefault.getString(dbMdl.getUsiMei(), ""));
    }

    /**
     * <br>[機  能] DBから取得した予約情報をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param dbMdl DBからの取得値
     */
    private void __setTorokuDate(Rsv110ParamModel paramMdl, Rsv110SisetuModel dbMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl_);
        //登録日時
        String textAddDate = gsMsg.getMessage("schedule.src.84");
        paramMdl.setRsv110AddDate(
                textAddDate + " : "
                        + UDateUtil.getSlashYYMD(dbMdl.getRsyEdate())
                        + " "
                        + UDateUtil.getSeparateHM(dbMdl.getRsyEdate()));
    }

    /**
     * <br>[機  能] DBから取得した予約情報をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param dbMdl DBからの取得値
     * @param pconfig プラグイン情報
     * @param userSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    private void __setYoyakuData(Rsv110ParamModel paramMdl,
            Rsv110SisetuModel dbMdl,
            PluginConfig pconfig,
            int userSid)
                    throws SQLException {

        //利用目的
        paramMdl.setRsv110Mokuteki(NullDefault.getString(dbMdl.getRsyMok(), ""));
        //利用目的(エスケープ処理  承認・却下時のメッセージ用)
        paramMdl.setRsv110MokutekiEsc(StringUtilHtml.transToHTmlPlusAmparsant(
                NullDefault.getString(dbMdl.getRsyMok(), "")));
        //編集権限
        paramMdl.setRsv110RsyEdit(dbMdl.getRsyEdit());

        //スケジュールプラグイン使用判定
        if (pconfig.getPlugin("schedule") != null) {
            log__.debug("スケジュールプラグイン使用");
            //スケジュールリレーションSID
            paramMdl.setRsv110ScdRsSid(dbMdl.getScdRsSid());
        } else {
            log__.debug("スケジュールプラグイン未使用");
            //スケジュールリレーションSID
            paramMdl.setRsv110ScdRsSid(0);
        }

        //ポップアップモードの場合は表示用の予約日付をセット
        if (paramMdl.getRsv110ProcMode().equals(GSConstReserve.PROC_MODE_POPUP)
                || !paramMdl.isRsv110EditAuth()) {
            //予約開始
            paramMdl.setYoyakuFrString(__convertUdateToYmdhm(dbMdl.getRsyFrDate()));
            //予約終了
            paramMdl.setYoyakuToString(__convertUdateToYmdhm(dbMdl.getRsyToDate()));
            //内容
            paramMdl.setRsv110Naiyo(StringUtilHtml.transToHTmlPlusAmparsant(
                    NullDefault.getString(dbMdl.getRsyBiko(), "")));
        } else {
            //内容
            paramMdl.setRsv110Naiyo(NullDefault.getString(dbMdl.getRsyBiko(), ""));
        }

        //承認ボタン表示フラグ
        paramMdl.setRsv110ApprBtnFlg(0);
        if (dbMdl.getRsyApprStatus() == GSConstReserve.RSY_APPR_STATUS_NOAPPR) {
            //セッションユーザが施設グループ管理者かを判定する
            RsvCommonBiz rsvBiz = new RsvCommonBiz();
            if (rsvBiz.isSisGrpAdmin(con_, paramMdl.getRsv110RsySid(), userSid)) {
                paramMdl.setRsv110ApprBtnFlg(1);
            }
        }
    }

    /**
     * <br>[機  能] UDateをyyyy/mm/dd hh:MM に変換する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param target 変換対象
     * @return convDateString 変換後
     */
    private String __convertUdateToYmdhm(UDate target) {

        String convDateString = "";

        if (target == null) {
            return convDateString;
        }

        StringBuilder convBuf = new StringBuilder();
        convBuf.append(UDateUtil.getSlashYYMD(target));
        convBuf.append("    ");
        convBuf.append(UDateUtil.getSeparateHM(target));

        convDateString = convBuf.toString();

        return convDateString;
    }

    /**
     * <br>[機  能] DBから取得した区分別予約情報をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param dbMdl DBからの取得値
     * @param rskSid 施設区分
     * @throws SQLException SQL実行時例外
     */
    private void __setKbnYoyakuData(
            Rsv110ParamModel paramMdl,
            Rsv110SisetuModel dbMdl,
            int rskSid)
                    throws SQLException {

        paramMdl.setRsv110Busyo(NullDefault.getString(dbMdl.getRkyBusyo(), ""));
        paramMdl.setRsv110UseName(NullDefault.getString(dbMdl.getRkyName(), ""));
        paramMdl.setRsv110UseNum(NullDefault.getString(dbMdl.getRkyNum(), ""));
        paramMdl.setRsv110Contact(NullDefault.getString(dbMdl.getRkyContact(), ""));

        if (rskSid == GSConstReserve.RSK_KBN_HEYA) {
            paramMdl.setRsv110UseKbn(dbMdl.getRkyUseKbn());
            paramMdl.setRsv110Guide(NullDefault.getString(dbMdl.getRkyGuide(), ""));
            paramMdl.setRsv110ParkNum(NullDefault.getString(dbMdl.getRkyParkNum(), ""));
        } else if (rskSid == GSConstReserve.RSK_KBN_CAR) {
            paramMdl.setRsv110PrintKbn(dbMdl.getRkyPrintKbn());
            paramMdl.setRsv110Dest(NullDefault.getString(dbMdl.getRkyDest(), ""));
        }


    }

    /**
     * <br>[機  能] 施設区分に応じて施設のヘッダ文字列をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
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
     * <br>[機  能] 期間の時・分・編集権限の初期値を設定する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @throws SQLException SQL実行時例外
     */
    private void __setDefHourMin(Rsv110ParamModel paramMdl) throws SQLException {

        //セッションユーザSIDを取得
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int usrSid = usrMdl.getUsrsid();

        RsvUserDao dao = new RsvUserDao(con_);
        RsvUserModel conf = dao.select(usrSid);
        if (conf == null) {
            paramMdl.setRsv111RsrTimeHourFr(
                    String.valueOf(GSConstReserve.YRK_DEFAULT_START_HOUR));
            paramMdl.setRsv111RsrTimeMinuteFr(
                    String.valueOf(GSConstReserve.YRK_DEFAULT_START_MINUTE));
            paramMdl.setRsv111RsrTimeHourTo(
                    String.valueOf(GSConstReserve.YRK_DEFAULT_END_HOUR));
            paramMdl.setRsv111RsrTimeMinuteTo(
                    String.valueOf(GSConstReserve.YRK_DEFAULT_END_MINUTE));
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

            paramMdl.setRsv111RsrTimeHourFr(String.valueOf(hourFr));
            paramMdl.setRsv111RsrTimeMinuteFr(String.valueOf(minFr));
            paramMdl.setRsv111RsrTimeHourTo(String.valueOf(hourTo));
            paramMdl.setRsv111RsrTimeMinuteTo(String.valueOf(minTo));
        }

        //編集権限の初期値を設定
        RsvCommonBiz rsvBiz = new RsvCommonBiz();
        paramMdl.setRsv111RsrEdit(rsvBiz.getInitEditAuth(con_, conf));

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
     * <br>[機  能] 施設予約区分別情報の初期値を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param usrMdl ユーザモデル
     * @param rskSid 施設区分
     * @throws SQLException SQL実行時例外
     */
    private void __setInitSisYrkData(
            Rsv110ParamModel paramMdl,
            BaseUserModel usrMdl,
            int rskSid) throws SQLException {

        GroupDao dao = new GroupDao(con_);
        CmnGroupmModel grpMdl = dao.getDefaultGroup(usrMdl.getUsrsid());

        //担当部署
        paramMdl.setRsv110Busyo(grpMdl.getGrpName());
        //担当・使用者名
        paramMdl.setRsv110UseName(usrMdl.getUsisei() + "  " + usrMdl.getUsimei());
        //連絡先
        CmnUsrmInfDao uInfDao = new CmnUsrmInfDao(con_);
        CmnUsrmInfModel uInfMdl = uInfDao.select(usrMdl.getUsrsid());
        paramMdl.setRsv110Contact(NullDefault.getString(uInfMdl.getUsiTelNai1(), ""));

        //施設区分 部屋
        if (rskSid == GSConstReserve.RSK_KBN_HEYA) {
            paramMdl.setRsv110UseKbn(GSConstReserve.RSY_USE_KBN_NOSET);
        } else if (rskSid == GSConstReserve.RSK_KBN_CAR) {
            paramMdl.setRsv110PrintKbn(GSConstReserve.RSY_PRINT_KBN_YES);
        }

    }

    /**
     * <br>[機  能] 同時登録ユーザ情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param bean 既存登録データ(編集時)
     * @throws SQLException SQL実行時例外
     */
    private void __setUserList(Rsv110ParamModel paramMdl, Rsv110SisetuModel bean)
            throws SQLException {

        int usrSid = reqMdl_.getSmodel().getUsrsid();

        //デフォルト表示グループ
        RsvScheduleBiz rsvSchBiz = new RsvScheduleBiz();
        String dfGpSidStr = rsvSchBiz.getDispDefaultGroupSidStr(con_, usrSid);
        int dfGpSid = RsvScheduleBiz.getDspGroupSid(dfGpSidStr);
        int dspGpSid = 0;
        boolean myGroupFlg = false;
        //グループラベル
        paramMdl.setRsv110GroupLabel(getGroupLabelList(usrSid));

        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getRsv110GroupSid(), dfGpSidStr);

        dspGpSidStr = getEnableSelectGroup(paramMdl.getRsv110GroupLabel(),
                dspGpSidStr,
                dfGpSidStr);

        if (RsvScheduleBiz.isMyGroupSid(dspGpSidStr)) {
            dspGpSid = RsvScheduleBiz.getDspGroupSid(dspGpSidStr);
            paramMdl.setRsv110GroupSid(dspGpSidStr);
            myGroupFlg = true;
        } else {
            dspGpSid = NullDefault.getInt(paramMdl.getRsv110GroupSid(), dfGpSid);
            paramMdl.setRsv110GroupSid(dspGpSidStr);
        }
        paramMdl.setRsv111GroupSid(paramMdl.getRsv110GroupSid());

        //同時登録スケジュールグループリスト
        paramMdl.setRsv110SchGroupLabel(rsvSchBiz.getSchGroupCombo(con_, reqMdl_, usrSid));

        //スケジュール閲覧不可のグループ、ユーザを設定
        int sessionUserSid = reqMdl_.getSmodel().getUsrsid();
        SchDao schDao = new SchDao(con_);
        if (GSConstReserve.PROC_MODE_POPUP.equals(paramMdl.getRsv110ProcMode())) {
            paramMdl.setRsv110SchNotAccessGroupList(schDao.getNotAccessGrpList(sessionUserSid));
            paramMdl.setRsv110SchNotAccessUserList(schDao.getNotAccessUserList(sessionUserSid));
        } else {
            paramMdl.setRsv110SchNotAccessGroupList(schDao.getNotRegistGrpList(sessionUserSid));
            paramMdl.setRsv110SchNotAccessUserList(schDao.getNotRegistUserList(sessionUserSid));
        }
        //編集モード or ポップアップモード時
        String procMode = paramMdl.getRsv110ProcMode();

        if (paramMdl.isRsv110InitFlg()
                && (procMode.equals(GSConstReserve.PROC_MODE_EDIT)
                        || procMode.equals(GSConstReserve.PROC_MODE_POPUP))) {

            //SCD_RSSIDからスケジュールSIDを取得する
            if (bean.getScdRsSid() > 0) {
                int scdSid = getScdSid(bean.getScdRsSid());
                if (scdSid > 0) {

                    //スケジュール管理者設定(共有範囲など)を取得
                    RsvSchAdmConfModel adminConf = rsvSchBiz.getAdmConfModel(con_);

                    ScheduleRsvModel schMdl =
                            getSchData(reqMdl_, scdSid, adminConf, con_);

                    int scdUsrSid = schMdl.getScdUsrSid();
                    if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
                        paramMdl.setRsv110SchKbn(GSConstReserve.RSV_SCHKBN_GROUP);

                        //同時登録スケジュールにアクセス不可の場合、未選択に設定する
                        if (paramMdl.getRsv110SchNotAccessGroupList().indexOf(scdUsrSid) < 0) {
                            paramMdl.setRsv110SchGroupSid(String.valueOf(scdUsrSid));
                        } else {
                            paramMdl.setRsv110SchGroupSid("-1");
                        }
                    } else {
                        if (schMdl != null
                                && (schMdl.getUsrInfList() == null
                                || schMdl.getUsrInfList().isEmpty())
                                && schMdl.getScdGrpSid() == GSConstSchedule.DF_SCHGP_ID) {

//                            CmnUsrmInfModel myMdl = new CmnUsrmInfModel();
//                            myMdl.setUsrSid(usrSid);
//                            myMdl.setUsiSei(usrMdl.getUsisei());
//                            myMdl.setUsiMei(usrMdl.getUsimei());
//
//                            ArrayList<CmnUsrmInfModel> myList =
//                                new ArrayList<CmnUsrmInfModel>();
//
//                            myList.add(myMdl);
//                            schMdl.setUsrInfList(myList);

                            CmnUsrmInfModel myMdl = new CmnUsrmInfModel();
                            myMdl.setUsrSid(schMdl.getScdUsrSid());
                            myMdl.setUsiSei(schMdl.getScdUsrSei());
                            myMdl.setUsiMei(schMdl.getScdUsrMei());

                            ArrayList<CmnUsrmInfModel> myList =
                                    new ArrayList<CmnUsrmInfModel>();

                            myList.add(myMdl);
                            schMdl.setUsrInfList(myList);
                        }

                        if (paramMdl.getSv_users() == null
                                || paramMdl.getSv_users().length == 0) {
                            __setSaveUsersForDb(paramMdl, schMdl.getUsrInfList());
                        }
                    }
                }
            }
        }

        //除外するユーザSIDを設定
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        usrSids.add(new Integer(GSConstUser.SID_ADMIN));

        //追加済みユーザSID
        ArrayList<Integer> list = null;
        ArrayList<CmnUsrmInfModel> selectUsrList = null;
        String[] users = paramMdl.getSv_users();

        if (users != null && users.length > 0) {
            //同時登録ユーザからスケジュールアクセス不可ユーザを除外する
            ArrayList<String> accessUserList = new ArrayList<String>();
            for (String user : users) {
                if (paramMdl.getRsv110SchNotAccessUserList().indexOf(Integer.parseInt(user)) < 0) {
                    accessUserList.add(user);
                }
            }
            paramMdl.setSv_users(accessUserList.toArray(new String[accessUserList.size()]));
            users = paramMdl.getSv_users();

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
        if (GSConstReserve.PROC_MODE_POPUP.equals(paramMdl.getRsv110ProcMode())) {
            rsvBiz.removeNotAccessUser(con_, belongList, usrSid);
        } else {
            rsvBiz.removeNotRegistUser(con_, belongList, usrSid);
        }
        paramMdl.setRsv110BelongLabel(belongList);

        //同時登録ユーザラベル
        paramMdl.setRsv110SelectUsrLabel(selectUsrList);

        //スケジュールを登録するユーザがいる場合、登録するユーザの名称をセット
        if (paramMdl.getSv_users() != null && paramMdl.getSv_users().length > 0) {
            setUserName(paramMdl, paramMdl.getRsv110SchKbn(), paramMdl.getSv_users(),
                    paramMdl.getRsv110SchGroupSid());
        }
    }

    /**
     * <br>[機  能] DBに登録されているスケジュール登録ユーザ情報を画面パラメータへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param list 同時登録ユーザ情報リスト
     */
    private void __setSaveUsersForDb(Rsv110ParamModel paramMdl, ArrayList<CmnUsrmInfModel> list) {

        ArrayList<String> sv_user_list = new ArrayList<String>();
        if (list != null) {
            for (CmnUsrmInfModel usrMdl : list) {
                sv_user_list.add(String.valueOf(usrMdl.getUsrSid()));
            }
            paramMdl.setSv_users((String[]) sv_user_list.toArray(new String[sv_user_list.size()]));
        }
    }

    /**
     * <br>[機  能] DBに登録されているスケジュール登録ユーザ情報を画面パラメータへ設定する
     * <br>[解  説] 拡張登録のパラメータを設定する
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param list 同時登録ユーザ情報リスト
     */
    private void __setExSaveUsersForDb(Rsv110ParamModel paramMdl, ArrayList<CmnUsrmInfModel> list) {

        ArrayList<String> sv_user_list = new ArrayList<String>();
        if (list != null) {
            for (CmnUsrmInfModel usrMdl : list) {
                sv_user_list.add(String.valueOf(usrMdl.getUsrSid()));
            }
            paramMdl.setRsv111SvUsers(
                    (String[]) sv_user_list.toArray(
                            new String[sv_user_list.size()]));
        }
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
     * @param reqMdl リクエスト情報
     * @param scdSid スケジュールSID
     * @param adminConf 管理者設定
     * @param con コネクション
     * @return ScheduleSearchModel
     * @throws SQLException SQL実行時例外
     */
    public ScheduleRsvModel getSchData(RequestModel reqMdl,
            int scdSid,
            RsvSchAdmConfModel adminConf,
            Connection con)
                    throws SQLException {

        ScheduleRsvModel scdMdl = null;
        CmnUsrmInfModel uMdl = null;

        try {

            RsvScdOperationDao rsvSchDao = new RsvScdOperationDao(con_);
            scdMdl = rsvSchDao.getSchData(scdSid, reqMdl.getSmodel().getUsrsid());

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
                                reqMdl_,
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
     * @param reqMdl リクエスト情報
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ区分
     * @param con コネクション
     * @return String ユーザ氏名
     * @throws SQLException SQL実行時例外
     */
    public String getUsrName(RequestModel reqMdl, int usrSid, int usrKbn,
            Connection con)
                    throws SQLException {
        String ret = "";
        if (usrKbn == GSConstSchedule.USER_KBN_GROUP) {

            if (usrSid == GSConstSchedule.SCHEDULE_GROUP) {
                GsMessage gsMsg = new GsMessage(reqMdl);
                ret = gsMsg.getMessage("cmn.group");
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
     * <br>[機  能] スケジュール登録ユーザ/グループ名一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl フォーム
     * @param kbn スケジュール登録区分
     * @param users 選択されているユーザのユーザSID
     * @param grpSid 選択されているグループのグループSID
     * @throws SQLException SQL実行時例外
     */
    public void setUserName(Rsv110ParamModel paramMdl, int kbn, String[] users, String grpSid)
            throws SQLException {

        ArrayList<String> usrArray = null;
        if (kbn == GSConstReserve.RSV_SCHKBN_GROUP) {

            if (NullDefault.getInt(grpSid, -1) >= 0) {
                GroupDao grpDao = new GroupDao(con_);
                CmnGroupmModel grpMdl = grpDao.getGroup(Integer.parseInt(grpSid));
                usrArray = new ArrayList<String>();
                usrArray.add(grpMdl.getGrpName());
            }
        } else {
            UserBiz userBiz = new UserBiz();
            ArrayList<BaseUserModel> usrList
            = userBiz.getBaseUserList(con_, users);

            if (usrList != null && !usrList.isEmpty()) {
                usrArray = new ArrayList<String>();
                for (BaseUserModel usrData : usrList) {
                    usrArray.add(usrData.getUsiseimei());
                }
            }
        }

        paramMdl.setRsvSchUserNameArray(usrArray);
    }

    /**
     * <br>[機  能] 施設予約情報の承認区分を更新する
     * <br>[解  説]
     * <br>[備  考] 同時に承認状況を[承認待ち]から[通常]へ更新する
     * @param paramMdl フォーム
     * @param apprKbn 承認区分(承認 or 却下)
     * @param userSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void updateYoyakuAppr(Rsv110ParamModel paramMdl, int apprKbn, int userSid)
            throws SQLException {
        int rsySid = paramMdl.getRsv110RsySid();

        RsvSisYrkDao sisYrkDao = new RsvSisYrkDao(con_);
        sisYrkDao.updateYoyakuAppr(rsySid, apprKbn, userSid, new UDate());
    }

    /**
     * <br>[機  能] 予約情報登録者ユーザSID取得。
     * <br>[解  説]
     * <br>[備  考]
     * @param  rsySid 予約SID
     * @return int ユーザSID
     * @throws Exception 実行例外
     */
    public int getEntryUserSid(
            int rsySid) throws Exception {

        int entryUserSid = -1;
        Rsv110SisetuModel model = __getYoyakuData(rsySid);

        entryUserSid = model.getRsyAuid();
        return entryUserSid;
    }

    /**
     * <br>[機  能] ショートメールで施設予約 申請通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param smailData ショートメール通知情報
     * @throws Exception 実行例外
     */
    public void sendSmail(Rsv110SMailModel smailData) throws Exception {

        //施設予約表示モデル(ショートメール送信用)
        Rsv110SyoninSmlModel rsvModel = new Rsv110SyoninSmlModel();

        UDate now = new UDate();
        String strNow = now.getDateString();
        RsvCommonBiz cmnBiz = new RsvCommonBiz();

        Rsv110SisetuModel model = __getYoyakuData(smailData.getRsySid());
        rsvModel.setRsvSid(model.getRsdSid());
        rsvModel.setRsvMokuteki(model.getRsyMok());
        rsvModel.setRsvNaiyou(model.getRsyBiko());
        rsvModel.setSendUserSid(model.getRsyAuid());
        rsvModel.setRsvFrDate(model.getRsyFrDate());
        rsvModel.setRsvToDate(model.getRsyToDate());
        rsvModel.setRsvAdate(model.getRsyEdate());
        rsvModel.setRsvUrl(cmnBiz.createReserveUrl(smailData.getReqMdl(),
                smailData.getRsySid(),
                Integer.parseInt(GSConstReserve.PROC_MODE_EDIT),
                strNow));

        //送信
        sendSmail(smailData.getCon(), smailData.getCntCon(), rsvModel,
                smailData.getAppRootPath(), smailData.getPluginConfig(),
                smailData.getReqMdl(), smailData.getProcMode(), smailData.getDelMode());
    }

    /**
     * <br>[機  能] ショートメールで更新通知を行う。
     * <br>[解  説] 承認設定がされている施設予約が登録した際に行う。
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param rsvModel 投稿内容(ショートメール送信用)
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param reqMdl リクエスト情報
     * @param procMode 処理モード
     * @param delMode 削除モード
     * @throws Exception 実行例外
     */
    public void sendSmail(
            Connection con,
            MlCountMtController cntCon,
            Rsv110SyoninSmlModel rsvModel,
            String appRootPath,
            PluginConfig pluginConfig,
            RequestModel reqMdl,
            int procMode,
            int delMode
            ) throws Exception {

        //投稿者名
        GsMessage gsMsg = new GsMessage(reqMdl);


        String aDate = "";
        String frDate = "";
        String toDate = "";

        String bodyml = "";

        aDate = UDateUtil.getSlashYYMD(rsvModel.getRsvAdate());
        frDate = UDateUtil.getSlashYYMD(rsvModel.getRsvFrDate())
                + " "
                + UDateUtil.getSeparateHMS(rsvModel.getRsvFrDate());
        toDate = UDateUtil.getSlashYYMD(rsvModel.getRsvToDate())
                + " "
                + UDateUtil.getSeparateHMS(rsvModel.getRsvToDate());

        //本文
        String tmpPath = __getSmlTemplateFilePathSyonin(appRootPath); //テンプレートファイルパス取得
        String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
        Map<String, String> map = new HashMap<String, String>();
        map.put("RESERVE", rsvModel.getRsvMokuteki());
        map.put("NAIYOU", rsvModel.getRsvNaiyou());
        map.put("NAIYOU", rsvModel.getRsvNaiyou());
        map.put("ADATE", aDate);
        map.put("FRDATE", frDate);
        map.put("TODATE", toDate);
        if (delMode == 1) {
            map.put("URL", " ");
            map.put("INFO", gsMsg.getMessage("reserve.rsv110.mail.3"));
        } else {
            map.put("URL", "URL:" + rsvModel.getRsvUrl());
            map.put("INFO", " ");
        }
        if (procMode == GSConstReserve.RSY_APPR_KBN_APPROVAL) {
            map.put("PROCMODE", gsMsg.getMessage("reserve.rsv110.mail.1"));
        } else if (procMode == GSConstReserve.RSY_APPR_KBN_REJECTION) {
            map.put("PROCMODE", gsMsg.getMessage("reserve.rsv110.mail.2"));
        }
        bodyml = StringUtil.merge(tmpBody, map);

        if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {

            String textMessage = gsMsg.getMessage("cmn.mail.omit");

            bodyml = textMessage + "\r\n\r\n" + bodyml;

            bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);

        }

        List<Integer> sidList = new ArrayList<Integer>();
        sidList.add(rsvModel.getSendUserSid());
        //ショートメール送信用モデルを作成する。
        //
        SmlSenderModel smlModel = new SmlSenderModel();
        //送信者(システムメールを指定)
        smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
        //TO
        smlModel.setSendToUsrSidArray(sidList);

        //タイトル
        String title = gsMsg.getMessage("reserve.172");
        title = StringUtil.trimRengeString(title,
                GSConstCommon.MAX_LENGTH_SMLTITLE);
        smlModel.setSendTitle(title);

        //本文
        smlModel.setSendBody(bodyml);
        //メール形式
        smlModel.setSendType(GSConstSmail.SAC_SEND_MAILTYPE_NORMAL);
        //マーク
        smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);

        //メール送信処理開始
        SmlSender sender = new SmlSender(con, cntCon, smlModel,
                pluginConfig, appRootPath, reqMdl);
        sender.execute();
    }

    /**
     * <br>[機  能] アプリケーションのルートパスから登録通知メールのテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    private String __getSmlTemplateFilePathSyonin(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/reserve/smail/syounin_tsuuchi.txt");
        return ret;
    }


    /**
     * <br>[機  能] 施設予約(単票)をPDF出力します。
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
    public RsvTanPdfModel createRsvTanPdf(
            Rsv110ParamModel paramMdl,
            String appRootPath,
            String outTempDir,
            PluginConfig pconfig,
            int userSid)
                    throws IOException, SQLException {
        OutputStream oStream = null;

        //施設予約(単票)PDF出力用モデル
        RsvTanPdfModel pdfModel = __getRsvPdfDataList(paramMdl, pconfig, userSid);

        String saveFileName = "rsvtan" + reqMdl_.getSmodel().getUsrsid() + ".pdf";
        pdfModel.setSaveFileName(saveFileName);

        try {
            IOTools.isDirCheck(outTempDir, true);
            oStream = new FileOutputStream(outTempDir + saveFileName);
            RsvTanPdfUtil pdfUtil = new RsvTanPdfUtil(reqMdl_);
            pdfUtil.createRsvTanPdf(pdfModel, appRootPath, oStream);
        } catch (Exception e) {
            log__.error("施設予約(単票)PDF出力に失敗しました。", e);
        } finally {
            if (oStream != null) {
                oStream.flush();
                oStream.close();
            }
        }
        log__.debug("施設予約(単票)PDF出力を終了します。");

        return pdfModel;
    }

    /**
     * <br>[機  能] PDF出力用のデータモデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param pconfig プラグイン情報
     * @param userSid セッションユーザSID
     * @return PDF出力用モデル
     * @throws SQLException SQL実行時例外
     */
    private RsvTanPdfModel __getRsvPdfDataList(
            Rsv110ParamModel paramMdl,
            PluginConfig pconfig,
            int userSid) throws SQLException {

        RsvTanPdfModel ret = new RsvTanPdfModel();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        //予約情報取得
        Rsv110SisetuModel yrkMdl = getYoyakuData(paramMdl);

        if (yrkMdl != null) {

            //登録者名をセット
            ret.setPdfRsvRegName(
                    NullDefault.getString(yrkMdl.getUsiSei(), "")
                    + " "
                    + NullDefault.getString(yrkMdl.getUsiMei(), ""));

            //登録日付をセット
            ret.setPdfRsvRegDate(
                    UDateUtil.getSlashYYMD(yrkMdl.getRsyEdate())
                    + " "
                    + UDateUtil.getSeparateHM(yrkMdl.getRsyEdate()));

            //施設グループ情報を取得
            Rsv070Model grpMdl = __getGroupData(yrkMdl.getRsdSid());
            String sisName = null;
            if (grpMdl != null) {

                //施設グループ
                ret.setPdfRsvGrpName(NullDefault.getString(grpMdl.getRsgName(), ""));
                //施設区分 */
                int rskSid = grpMdl.getRskSid();
                ret.setPdfRsvKbn(rskSid);
                //施設区分名称 */
                ret.setPdfRsvDspKbn(NullDefault.getString(grpMdl.getRskName(), ""));
                //施設名称
                sisName = NullDefault.getString(grpMdl.getRsdName(), "");
                ret.setPdfRsvName(sisName);
                //資産管理番号
                ret.setPdfRsvSisNum(NullDefault.getString(grpMdl.getRsdSnum(), ""));

                //可変項目
                switch (rskSid) {
                //部屋
                case GSConstReserve.RSK_KBN_HEYA:
                    //座席数
                    ret.setPdfRsvSeatNum(NullDefault.getString(grpMdl.getRsdProp1Value(), ""));
                    //喫煙
                    ret.setPdfRsvSmoking(
                            NullDefault.getStringZeroLength(
                                    grpMdl.getRsdProp2Value(),
                                    String.valueOf(GSConstReserve.PROP_KBN_KA)));
                    break;
                //物品
                case GSConstReserve.RSK_KBN_BUPPIN:
                    //個数
                    ret.setPdfRsvNum(NullDefault.getString(grpMdl.getRsdProp1Value(), ""));
                    //社外持出し
                    ret.setPdfRsvTakeOut(
                            NullDefault.getStringZeroLength(
                                    grpMdl.getRsdProp3Value(),
                                    String.valueOf(GSConstReserve.PROP_KBN_KA)));
                    break;
                //車
                case GSConstReserve.RSK_KBN_CAR:
                    //乗員数
                    ret.setPdfRsvCarSeatNum(NullDefault.getString(grpMdl.getRsdProp1Value(), ""));
                    //喫煙
                    ret.setPdfRsvSmoking(
                            NullDefault.getStringZeroLength(
                                    grpMdl.getRsdProp2Value(),
                                    String.valueOf(GSConstReserve.PROP_KBN_KA)));
                    //ナンバー
                    ret.setPdfRsvCarNumber(NullDefault.getString(grpMdl.getRsdProp4Value(), ""));
                    break;
                //書籍
                case GSConstReserve.RSK_KBN_BOOK:
                    //冊数
                    ret.setPdfRsvBookNum(NullDefault.getString(grpMdl.getRsdProp1Value(), ""));
                    //社外持出し
                    ret.setPdfRsvTakeOut(
                            NullDefault.getStringZeroLength(
                                    grpMdl.getRsdProp3Value(),
                                    String.valueOf(GSConstReserve.PROP_KBN_KA)));
                    //ISBN
                    ret.setPdfRsvBookIsbn(NullDefault.getString(grpMdl.getRsdProp5Value(), ""));
                    break;
                default:
                    break;
                }

                //予約可能期限
                ret.setPdfRsvKikan(NullDefault.getString(grpMdl.getRsdProp6Value(), ""));
                //重複登録
                ret.setPdfRsvOverRegKbn(
                        NullDefault.getStringZeroLength(
                                grpMdl.getRsdProp7Value(),
                                String.valueOf(GSConstReserve.PROP_KBN_KA)));
                //備考
                ret.setPdfRsvBiko(NullDefault.getString(grpMdl.getRsdBiko(), ""));
            }

            //利用目的
            ret.setPdfRsvObj(NullDefault.getString(yrkMdl.getRsyMok(), ""));
            //期間 開始
            String from = UDateUtil.getYymdJ(yrkMdl.getRsyFrDate(), reqMdl_)
                    + " " + UDateUtil.getSeparateHMJ(yrkMdl.getRsyFrDate(), reqMdl_);
            ret.setPdfRsvFromDate(from);
            //期間 終了
            String to = UDateUtil.getYymdJ(yrkMdl.getRsyToDate(), reqMdl_)
                    + " " + UDateUtil.getSeparateHMJ(yrkMdl.getRsyToDate(), reqMdl_);
            ret.setPdfRsvToDate(to);
            //内容
            ret.setPdfRsvSubject(NullDefault.getString(yrkMdl.getRsyBiko(), ""));
            //編集権限
            ret.setPdfRsvEditKbn(yrkMdl.getRsyEdit());

            //担当部署
            ret.setPdfRsvBusyo(NullDefault.getString(yrkMdl.getRkyBusyo(), ""));
            //担当・使用者名
            ret.setPdfRsvUseName(NullDefault.getString(yrkMdl.getRkyName(), ""));
            // 人数
            ret.setPdfRsvUseNum(NullDefault.getString(yrkMdl.getRkyNum(), ""));
            // 区分 (0:未設定 1:会議 2:研修 3:その他)
            String strKbn = "";
            switch (yrkMdl.getRkyUseKbn()) {
            case GSConstReserve.RSY_USE_KBN_NOSET:
                strKbn = gsMsg.getMessage("reserve.use.kbn.noset");
                break;
            case GSConstReserve.RSY_USE_KBN_KAIGI:
                strKbn = gsMsg.getMessage("reserve.use.kbn.meeting");
                break;
            case GSConstReserve.RSY_USE_KBN_KENSYU:
                strKbn = gsMsg.getMessage("reserve.use.kbn.training");
                break;
            case GSConstReserve.RSY_USE_KBN_OTHER:
                strKbn = gsMsg.getMessage("reserve.use.kbn.other");
                break;
            default:
                strKbn = gsMsg.getMessage("reserve.use.kbn.noset");
                break;
            }
            ret.setPdfRsvUseKbn(strKbn);
            // 連絡先
            ret.setPdfRsvContact(NullDefault.getString(yrkMdl.getRkyContact(), ""));
            // 会議名案内
            ret.setPdfRsvGuide(NullDefault.getString(yrkMdl.getRkyGuide(), ""));
            // 駐車場見込み台数
            ret.setPdfRsvParkNum(NullDefault.getString(yrkMdl.getRkyParkNum(), ""));
            // 印刷区
            if (yrkMdl.getRkyPrintKbn() == GSConstReserve.RSY_PRINT_KBN_NO) {
                ret.setPdfRsvPrintKbn(gsMsg.getMessage("reserve.print.no"));
            } else if (yrkMdl.getRkyPrintKbn() == GSConstReserve.RSY_PRINT_KBN_YES) {
                ret.setPdfRsvPrintKbn(gsMsg.getMessage("reserve.print.yes"));
            }
            // 行き先
            ret.setPdfRsvDest(NullDefault.getString(yrkMdl.getRkyDest(), ""));


            //スケジュールプラグイン使用可能フラグ
            //スケジュール使用可能
            if (pconfig.getPlugin(GSConstReserve.PLUGIN_ID_SCHEDULE) != null) {
                ret.setPdfRsvSchUseFlg(GSConst.PLUGIN_USE);
                //同時登録ユーザリストセット
                __setPdfUserList(paramMdl, yrkMdl, ret);

                //スケジュール使用不可
            } else {
                ret.setPdfRsvSchUseFlg(GSConst.PLUGIN_NOT_USE);
            }

            //ファイル名
            String fileName = UDateUtil.getYymdJ(yrkMdl.getRsyFrDate(), reqMdl_);
            fileName += gsMsg.getMessage("reserve.src.21");
            if (sisName != null) {
                fileName += "_" + sisName;
            }
            fileName += "_" + NullDefault.getString(yrkMdl.getRsyMok(), "");
            String encFileName = __fileNameCheck(fileName) + ".pdf";
            ret.setFileName(encFileName);

        }

        return ret;
    }

    /**
     * <br>[機  能] 同時登録ユーザ情報をPDFモデルに設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param bean 既存登録データ(編集時)
     * @param pdfMdl PDFモデル
     * @throws SQLException SQL実行時例外
     */
    private void __setPdfUserList(
            Rsv110ParamModel paramMdl,
            Rsv110SisetuModel bean,
            RsvTanPdfModel pdfMdl)
                    throws SQLException {

        //デフォルト表示グループ
        RsvScheduleBiz rsvSchBiz = new RsvScheduleBiz();
        String[] users = null;
        //SCD_RSSIDからスケジュールSIDを取得する
        if (bean.getScdRsSid() > 0) {
            int scdSid = getScdSid(bean.getScdRsSid());
            if (scdSid > 0) {

                //スケジュール管理者設定(共有範囲など)を取得
                RsvSchAdmConfModel adminConf = rsvSchBiz.getAdmConfModel(con_);
                ScheduleRsvModel schMdl =
                        getSchData(reqMdl_, scdSid, adminConf, con_);

                if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
                    pdfMdl.setPdfRsvSchKbn(GSConstReserve.RSV_SCHKBN_GROUP);
                    CmnGroupmDao dao = new CmnGroupmDao(con_);
                    CmnGroupmModel dMdl = dao.select(schMdl.getScdUsrSid());
                    pdfMdl.setPdfRsvSchGrpName(dMdl.getGrpName());
                } else {
                    pdfMdl.setPdfRsvSchKbn(GSConstReserve.RSV_SCHKBN_USER);
                    if (schMdl != null
                            && (schMdl.getUsrInfList() == null
                            || schMdl.getUsrInfList().isEmpty())
                            && schMdl.getScdGrpSid() == GSConstSchedule.DF_SCHGP_ID) {

                        CmnUsrmInfModel myMdl = new CmnUsrmInfModel();
                        myMdl.setUsrSid(schMdl.getScdUsrSid());
                        myMdl.setUsiSei(schMdl.getScdUsrSei());
                        myMdl.setUsiMei(schMdl.getScdUsrMei());

                        ArrayList<CmnUsrmInfModel> myList =
                                new ArrayList<CmnUsrmInfModel>();

                        myList.add(myMdl);
                        schMdl.setUsrInfList(myList);
                    }

                    ArrayList<String> sv_user_list = new ArrayList<String>();
                    if (schMdl.getUsrInfList() != null) {
                        for (CmnUsrmInfModel uInfMdl : schMdl.getUsrInfList()) {
                            sv_user_list.add(String.valueOf(uInfMdl.getUsrSid()));
                        }
                        users = (String[]) sv_user_list.toArray(new String[sv_user_list.size()]);
                    }
                }
            }
        }

        //追加済みユーザSID
        ArrayList<Integer> list = null;
        ArrayList<CmnUsrmInfModel> selectUsrList = null;
        List<Integer> notAccessUsers = null;
        SchDao schDao = new SchDao(con_);
        notAccessUsers = schDao.getNotRegistUserList(reqMdl_.getSmodel().getUsrsid());

        if (users != null && users.length > 0) {
            list = new ArrayList<Integer>();
            for (String user : users) {
                if (notAccessUsers.indexOf(Integer.parseInt(user)) < 0) {
                    list.add(new Integer(user));
                }
            }
        }

        UserBiz userBiz = new UserBiz();
        if (list != null && list.size() > 0) {
            selectUsrList = (ArrayList<CmnUsrmInfModel>) userBiz.getUserList(con_, list);
        }

        List<String> userNameList = new ArrayList<String>();
        if (selectUsrList != null) {
            for (CmnUsrmInfModel usrInfMdl : selectUsrList) {
                String name = usrInfMdl.getUsiSei() + " " + usrInfMdl.getUsiMei();
                userNameList.add(name);
            }
        }
        pdfMdl.setPdfRsvSchUserList(userNameList);

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
     * <br>[機  能]オペレーションログ出力用予約削除内容を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ格納モデル
     * @return オペレーションログ表示内容
     * @throws SQLException SQL実行エラー
     */
    public String getOpLog(Rsv110ParamModel paramMdl) throws SQLException {

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        Rsv210Model sisetsuJyohoMdl = getGroupData(
                yrkDao.getSisDataSid(paramMdl.getRsv110RsySid()));
        Rsv110SisetuModel sisYrkData = __getYoyakuData(paramMdl.getRsv110RsySid());
        if (sisYrkData == null) {
            return "";
        }
        StringBuilder opLog = new StringBuilder();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        String sisetu = gsMsg.getMessage("cmn.facility.name");
        String kikann = gsMsg.getMessage("cmn.period");
        String mokuteki = gsMsg.getMessage("reserve.72");
        String naiyou = gsMsg.getMessage("cmn.content");
        opLog.append("[");
        opLog.append(sisetu);
        opLog.append("] ");
        opLog.append(sisetsuJyohoMdl.getRsdName());
        opLog.append("\n");
        opLog.append("[");
        opLog.append(kikann);
        opLog.append("] ");
        opLog.append(gsMsg.getMessage("cmn.view.date", new String[] {
                String.valueOf(sisYrkData.getRsyFrDate().getYear()),
                String.valueOf(sisYrkData.getRsyFrDate().getMonth()),
                String.valueOf(sisYrkData.getRsyFrDate().getIntDay()),
                String.valueOf(sisYrkData.getRsyFrDate().getIntHour()),
                String.valueOf(sisYrkData.getRsyFrDate().getIntMinute())
        }));
        opLog.append(" ～ ");
        opLog.append(gsMsg.getMessage("cmn.view.date", new String[] {
                String.valueOf(sisYrkData.getRsyToDate().getYear()),
                String.valueOf(sisYrkData.getRsyToDate().getMonth()),
                String.valueOf(sisYrkData.getRsyToDate().getIntDay()),
                String.valueOf(sisYrkData.getRsyToDate().getIntHour()),
                String.valueOf(sisYrkData.getRsyToDate().getIntMinute())
        }));
        opLog.append("\n");
        opLog.append("[");
        opLog.append(mokuteki);
        opLog.append("] ");
        opLog.append(sisYrkData.getRsyMok());
        opLog.append("\n");
        opLog.append("[");
        opLog.append(naiyou);
        opLog.append("] ");
        opLog.append(sisYrkData.getRsyBiko());
        return opLog.toString();
    }
}