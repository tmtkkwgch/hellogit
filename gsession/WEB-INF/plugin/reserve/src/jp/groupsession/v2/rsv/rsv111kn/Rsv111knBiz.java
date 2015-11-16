package jp.groupsession.v2.rsv.rsv111kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
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
import jp.groupsession.v2.rsv.dao.RsvSisKryrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisKyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisRyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.ReserveSmlModel;
import jp.groupsession.v2.rsv.model.RsvScdOperationModel;
import jp.groupsession.v2.rsv.model.RsvSisKryrkModel;
import jp.groupsession.v2.rsv.model.RsvSisKyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisRyrkModel;
import jp.groupsession.v2.rsv.model.RsvSisYrkModel;
import jp.groupsession.v2.rsv.rsv070.Rsv070Model;
import jp.groupsession.v2.rsv.rsv110.Rsv110Biz;
import jp.groupsession.v2.rsv.rsv110.Rsv110SisetuModel;
import jp.groupsession.v2.rsv.rsv111.Rsv111Biz;
import jp.groupsession.v2.rsv.rsv111.Rsv111ParamModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 施設予約拡張登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv111knBiz extends AbstractReserveBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv111knBiz.class);
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;
    /** コネクション */
    protected Connection con_ = null;
    /**削除ログ*/
    StringBuilder delLog__ = new StringBuilder();
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param con コネクション
     */
    public Rsv111knBiz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 画面表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Rsv111knParamModel paramMdl) throws SQLException {

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

                //施設予約拡張SIDを取得
                int rsrRsid = paramMdl.getRsv111RsrRsid();

                //複写して登録or施設予約拡張SIDが-1のとき
                if (procMode.equals(GSConstReserve.PROC_MODE_COPY_ADD) || rsrRsid == -1) {
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

                    //関連するスケジュールデータ存在フラグを設定
                    __existSchData(paramMdl);
                }

                rsdSid = yrkMdl.getRsdSid();
            }

            //登録済データの日付リストを設定
            __setOldKurikaeshiDataList(paramMdl);
        }

        //新規に登録する日付リストを設定
        __setNewKurikaeshiDataList(paramMdl);

        //施設グループ情報を取得
        Rsv070Model grpMdl = __getGroupData(rsdSid);
        if (grpMdl != null) {
            int rskSid = grpMdl.getRskSid();

            //施設区分毎に入力可能な項目を設定
            __setSisetuHeader(paramMdl, rskSid);

            //施設グループ情報セット
            __setGroupData(paramMdl, grpMdl);
        }

        //期間開始
        paramMdl.setYoyakuFrString(__convertUdateToYmd(paramMdl, 1));
        //期間終了
        paramMdl.setYoyakuToString(__convertUdateToYmd(paramMdl, 2));
        //時間開始
        paramMdl.setYoyakuTimeFrString(__convertUdateToHm(paramMdl, 1));
        //時間終了
        paramMdl.setYoyakuTimeToString(__convertUdateToHm(paramMdl, 2));
        //内容
        paramMdl.setRsv111knRsrBiko(StringUtilHtml.transToHTmlPlusAmparsant(
                NullDefault.getString(paramMdl.getRsv111RsrBiko(), "")));

        //スケジュールを登録するユーザがいる場合、登録するユーザの名称をセット
        Rsv110Biz biz110 = new Rsv110Biz(reqMdl_, con_);
        biz110.setUserName(paramMdl, paramMdl.getRsv111SchKbn(),
                        paramMdl.getRsv111SvUsers(), paramMdl.getRsv111SchGroupSid());
        biz110 = null;

    }

    /**
     * <br>[機  能] 入力内容をDBに反映する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param ctrl 採番用コネクション
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @return sidDataList 予約SID、施設SIDリスト
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<int []> updateYoyakuData(
            Rsv111knParamModel paramMdl,
            MlCountMtController ctrl,
            int userSid,
            String appRootPath)
        throws SQLException {

        ArrayList<int []> sidDataList = new ArrayList<int []>();
        String procMode = paramMdl.getRsv110ProcMode();

        //新規モード or 複写して登録モード
        if (procMode.equals(GSConstReserve.PROC_MODE_SINKI)
               || procMode.equals(GSConstReserve.PROC_MODE_COPY_ADD)) {

            //スケジュールが選択されている
            int schKbn = paramMdl.getRsv111SchKbn();
            if ((schKbn == GSConstReserve.RSV_SCHKBN_GROUP
                    && NullDefault.getInt(paramMdl.getRsv111SchGroupSid(), -1) >= 0)
            || (schKbn == GSConstReserve.RSV_SCHKBN_USER
                    && paramMdl.getRsv111SvUsers() != null
                    && paramMdl.getRsv111SvUsers().length > 0)) {
                //施設情報とスケジュール登録
                sidDataList = __insertYoyakuWithSchedule(paramMdl, ctrl, userSid, appRootPath);
            } else {
                //施設情報のみ登録
                sidDataList = __insertYoyaku(paramMdl, ctrl, userSid, appRootPath);
            }
        //編集モード
        } else if (procMode.equals(GSConstReserve.PROC_MODE_EDIT)) {

            //「スケジュールへ反映」が選択されている かつ
            //スケジュールと関連付いている または スケジュールが入力されている
            if (paramMdl.getRsv111ScdReflection() == GSConstReserve.SCD_REFLECTION_OK
            && ((paramMdl.getRsv110ScdRsSid() > 0 && paramMdl.isRsv111ExistSchDateFlg())
                || (__checkInputSchedule(paramMdl)))) {
                //施設情報とスケジュール更新
                sidDataList = __updateKurikaeshiYoyaku(paramMdl, ctrl, userSid, appRootPath);

            } else {
                //施設情報のみ更新
                sidDataList = __updateYoyaku(paramMdl, ctrl, userSid, appRootPath);
            }

        }
        return sidDataList;
    }
    /**
     * <br>[機  能]オペレーションログ出力用予約削除内容を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ格納モデル
     * @throws SQLException SQL実行エラー
     */
    private void __creatDelOpLog(Rsv111knParamModel paramMdl) throws SQLException {

        //予約情報取得
        Rsv110SisetuModel yrkMdl = __getYoyakuData(paramMdl);
        Rsv070Model sisetsuJyohoMdl = __getGroupData(yrkMdl.getRsdSid());

        StringBuilder opLog = new StringBuilder();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        opLog.append("[");
        opLog.append(gsMsg.getMessage("cmn.facility.name"));
        opLog.append("]");
        opLog.append(sisetsuJyohoMdl.getRsdName());
        opLog.append("\n");
        opLog.append("[");
        opLog.append(gsMsg.getMessage("cmn.period"));
        opLog.append("]");

        opLog.append(gsMsg.getMessage("cmn.view.date", new String[] {
                String.valueOf(yrkMdl.getRsyFrDate().getYear()),
                String.valueOf(yrkMdl.getRsyFrDate().getMonth()),
                String.valueOf(yrkMdl.getRsyFrDate().getIntDay()),
                String.valueOf(yrkMdl.getRsyFrDate().getIntHour()),
                String.valueOf(yrkMdl.getRsyFrDate().getIntMinute())
        }));
        opLog.append(" ～ ");
        opLog.append(gsMsg.getMessage("cmn.view.date", new String[] {
                String.valueOf(yrkMdl.getRsyToDate().getYear()),
                String.valueOf(yrkMdl.getRsyToDate().getMonth()),
                String.valueOf(yrkMdl.getRsyToDate().getIntDay()),
                String.valueOf(yrkMdl.getRsyToDate().getIntHour()),
                String.valueOf(yrkMdl.getRsyToDate().getIntMinute())
        }));
        opLog.append("\n");
        opLog.append("[");
        opLog.append(gsMsg.getMessage("reserve.72"));
        opLog.append("]");
        opLog.append(yrkMdl.getRsyMok());
        opLog.append("\n");
        opLog.append("[");
        opLog.append(gsMsg.getMessage("cmn.content"));
        opLog.append("]");
        opLog.append(yrkMdl.getRsyBiko());
        delLog__ = opLog;
    }
    /**
     * <br>[機  能] 予約を削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @throws SQLException SQL実行時例外
     */
    public void deleteYoyakuData(Rsv111knParamModel paramMdl) throws SQLException {

        int rsrRsid = paramMdl.getRsv111RsrRsid();
        int sceSid = -1;
        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con_);
        RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);
        RsvSisKryrkDao kryrkDao = new RsvSisKryrkDao(con_);
        RsvScdOperationDao scdDao = new RsvScdOperationDao(con_);

        RsvSisYrkModel selParam = new RsvSisYrkModel();
        selParam.setRsySid(paramMdl.getRsv110RsySid());
        RsvSisYrkModel yrkBase = yrkDao.select(selParam);

        __creatDelOpLog(paramMdl);

        //スケジュールと関連付いている
        if (paramMdl.getRsv110ScdRsSid() > 0) {
            //スケジュール拡張SID取得
            sceSid = scdDao.selectSceSid(paramMdl.getRsv110ScdRsSid());

            //スケジュールが繰り返し登録されている
            ArrayList<Integer> scdRsSidArray = new ArrayList<Integer>();
            if (sceSid > 0) {
                //拡張登録された全ての施設のスケジュールリレーションSIDを取得
                ArrayList<Integer> rsSidList = scdDao.selectKakutyoAllScdRsSid(sceSid);
                for (int rsSid : rsSidList) {
                    if (rsSid > 0) {
                        scdRsSidArray.add(rsSid);
                    }
                }

            } else {
                RsvSisYrkModel param = new RsvSisYrkModel();
                param.setRsySid(paramMdl.getRsv110RsySid());
                RsvSisYrkModel yrkRet = yrkDao.select(param);
                if (yrkRet != null && yrkRet.getScdRsSid() > 0) {
                    scdRsSidArray.add(yrkRet.getScdRsSid());
                }
            }

            //施設予約SID取得
            ArrayList<Integer> rsySidArray = scdDao.selectKakutyoAllRsysid(scdRsSidArray);
            //施設拡張SID取得
            ArrayList<Integer> rsrRsidArray = scdDao.selectKakutyoAllRsrRsid(scdRsSidArray);

            //施設予約情報削除
            yrkDao.deleteRyrkData(scdRsSidArray);
            //施設予約区分別情報削除
            kyrkDao.delete(rsySidArray);

            //施設予約が繰り返し登録されている
            if (paramMdl.getRsv111RsrRsid() > 0) {


              //施設予約拡張情報削除
                ryrkDao.delete(rsrRsidArray);
                //施設予約拡張区分別情報削除
                kryrkDao.delete(rsrRsidArray);
            }

        } else {
            //施設予約が繰り返し登録されている
            if (paramMdl.getRsv111RsrRsid() > 0) {
                //削除する施設予約SIDを取得
                ArrayList<Integer> rsySids = yrkDao.getYrkDataSidList(rsrRsid);
                //施設予約情報削除
                yrkDao.deleteRyrkData(rsrRsid);
                //施設予約区分別情報削除
                kyrkDao.delete(rsySids);

                //施設予約拡張情報削除
                ryrkDao.delete(rsrRsid);
                //施設予約拡張区分別情報削除
                kryrkDao.delete(rsrRsid);


            } else {

                //施設予約情報削除
                RsvSisYrkModel param = new RsvSisYrkModel();
                param.setRsySid(paramMdl.getRsv110RsySid());
                yrkDao.delete(param);
                //施設予約区分別情報削除
                kyrkDao.delete(paramMdl.getRsv110RsySid());

            }
        }

        //スケジュールと関連付いている & 「スケジュールへ反映」が選択されている
        if (paramMdl.getRsv110ScdRsSid() > 0
                && paramMdl.getRsv111ScdReflection() == GSConstReserve.SCD_REFLECTION_OK) {

            int scdUsrKbn = 0;
            ArrayList<Integer> usrSidArray = new ArrayList<Integer>();
            if (sceSid > 0) {
                //登録対象ユーザ取得
                usrSidArray = scdDao.selectKakutyoAllUsrSid(sceSid);
                //スケジュール ユーザ区分
                scdUsrKbn = scdDao.selectKakutyoUsrKbn(sceSid);
            } else {
                //登録対象ユーザ取得
                usrSidArray = scdDao.selectScdUsrSid(yrkBase.getScdRsSid());
                //スケジュール ユーザ区分
                scdUsrKbn = scdDao.getSchUsrKbn(yrkBase.getScdRsSid());
            }

            //スケジュールアクセス不可グループ or ユーザを取得
            int sessionUserSid = reqMdl_.getSmodel().getUsrsid();
            SchDao schDao = new SchDao(con_);
            List<Integer> notAccessList = new ArrayList<Integer>();
            if (scdUsrKbn == GSConstReserve.RSV_SCHKBN_GROUP) {
                notAccessList = schDao.getNotRegistGrpList(sessionUserSid);
            } else {
                notAccessList = schDao.getNotRegistUserList(sessionUserSid);
            }

            //登録対象ユーザからスケジュールアクセス不可グループ or ユーザを除外する
            ArrayList<Integer> schAccessUserList = new ArrayList<Integer>();
            ArrayList<Integer> schNotAccessUserList = new ArrayList<Integer>();
            for (int scdUsrSid : usrSidArray) {
                if (notAccessList.indexOf(scdUsrSid) < 0) {
                    schAccessUserList.add(scdUsrSid);
                } else {
                    schNotAccessUserList.add(scdUsrSid);
                }
            }
            usrSidArray = schAccessUserList;

            //削除対象スケジュールのスケジュールユーザSID
            String[] delScdUsrSid = new String[schAccessUserList.size()];
            for (int scdUsrIdx = 0; scdUsrIdx < schAccessUserList.size(); scdUsrIdx++) {
                delScdUsrSid[scdUsrIdx]
                        = String.valueOf(schAccessUserList.get(scdUsrIdx));
            }

            //スケジュールが繰り返し登録されているデータ
            if (sceSid != -1) {
                //スケジュール削除
                scdDao.deleteKakutyoDataWithUsers(sceSid, delScdUsrSid);

                //スケジュール拡張に紐付くスケジュールが無くなった
                if (scdDao.selectExDataCnt(sceSid) == 0) {
                    //スケジュール拡張データ削除
                    scdDao.deleteExData(sceSid);
                }
            } else {
                //スケジュール削除
                scdDao.deleteScdTiWithUsers(yrkBase.getScdRsSid(), delScdUsrSid);
            }
        }
    }

    /**
     * <br>[機  能] 繰り返し施設予約情報を登録する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param ctrl 採番用コネクション
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @return sidDataList 予約SID,施設SIDリスト
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<int []> __insertYoyaku(Rsv111knParamModel paramMdl,
                                 MlCountMtController ctrl, int userSid, String appRootPath)
        throws SQLException {

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con_);
        RsvSisKryrkDao kryrkDao = new RsvSisKryrkDao(con_);

        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int sessionUsrSid = usrMdl.getUsrsid();
        int sisetuSid = paramMdl.getRsv110RsdSid();
        UDate now = new UDate();
        ArrayList<int []> sidDataList = new ArrayList<int[]>();

       /*******************************************************
        *
        * 施設予約拡張データ登録
        *
        *******************************************************/
        RsvSisRyrkModel updRyrk =
                __getRyrkBaseModel(paramMdl, sessionUsrSid, now, ctrl);

        //拡張予約拡張SID採番
        int rsrRsid =
                (int) ctrl.getSaibanNumber(
                        GSConstReserve.SBNSID_RESERVE,
                        GSConstReserve.SBNSID_SUB_KAKUTYO,
                        sessionUsrSid);

        //施設予約拡張SID
        updRyrk.setRsrRsid(rsrRsid);

        //拡張予約データ登録
        ryrkDao.insert(updRyrk);

        //施設予約拡張区分別データ登録
        if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
            RsvSisKryrkModel kryrkMdl = __getKryrkModel(
                    paramMdl, rsrRsid, sessionUsrSid, now, appRootPath);
            kryrkDao.insert(kryrkMdl);
        }


       /*******************************************************
        *
        * 施設予約データ登録
        *
        *******************************************************/

        //取得のチェック
        Rsv111Biz biz = new Rsv111Biz(reqMdl_, con_);
        HashMap<String, String> dayMap = biz.getInsertDateList(paramMdl);

        if (!dayMap.isEmpty()) {
            ArrayList<RsvSisYrkModel> updArray = new ArrayList<RsvSisYrkModel>();
            ArrayList<RsvSisKyrkModel> kyrkArray = new ArrayList<RsvSisKyrkModel>();

            ArrayList<String> dayList = new ArrayList<String>(dayMap.values());
            Collections.sort(dayList);

            RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
            for (String day : dayList) {

                RsvSisYrkModel mdl = new RsvSisYrkModel();

                mdl = __getNewYrkBaseModel(paramMdl, sessionUsrSid, now, day, ctrl);
                //施設SID
                mdl.setRsdSid(sisetuSid);
                //登録者ID
                mdl.setRsyAuid(sessionUsrSid);
                //登録日時
                mdl.setRsyAdate(now);
                //施設予約拡張SID
                mdl.setRsrRsid(rsrRsid);
                //承認状況
                rsvCmnBiz.setSisYrkApprData(con_, sisetuSid, mdl, userSid);

                updArray.add(mdl);
                sidDataList.add(new int []{mdl.getRsySid(), sisetuSid});

                //施設予約区分別情報登録
                if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
                    RsvSisKyrkModel kyrkMdl =
                            __getKyrModel(
                                    paramMdl, mdl.getRsySid(), sessionUsrSid, now, appRootPath);
                    kyrkArray.add(kyrkMdl);
                }
            }

            if (!updArray.isEmpty()) {
                yrkDao.insertNewYoyakuPlural(updArray);

                //施設予約区分別情報登録
                if (!kyrkArray.isEmpty()) {
                    RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);
                    kyrkDao.insert(kyrkArray);
                }
            }
        }
        return sidDataList;
    }

    /**
     * <br>[機  能] 繰り返し施設予約情報を登録する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param ctrl 採番用コネクション
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @return sidDataList 予約SID,施設SIDのリスト
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<int []> __insertYoyakuWithSchedule(Rsv111knParamModel paramMdl,
            MlCountMtController ctrl, int userSid, String appRootPath)
        throws SQLException {

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con_);
        RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);
        RsvSisKryrkDao kryrkDao = new RsvSisKryrkDao(con_);


        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int sessionUsrSid = usrMdl.getUsrsid();
        int sisetuSid = paramMdl.getRsv110RsdSid();
        UDate now = new UDate();
        ArrayList<int []> sidDataList = new ArrayList<int []>();

       /*******************************************************
        *
        * 施設予約拡張データ登録
        *
        *******************************************************/
        RsvSisRyrkModel updRyrk =
            __getRyrkBaseModel(paramMdl, sessionUsrSid, now, ctrl);

        //拡張予約拡張SID採番
        int rsrRsid =
            (int) ctrl.getSaibanNumber(
                    GSConstReserve.SBNSID_RESERVE,
                    GSConstReserve.SBNSID_SUB_KAKUTYO,
                    sessionUsrSid);

        //施設予約拡張SID
        updRyrk.setRsrRsid(rsrRsid);

        //拡張予約データ登録
        ryrkDao.insert(updRyrk);

        //施設予約拡張区分別データ登録
        if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
            RsvSisKryrkModel kryrkMdl = __getKryrkModel(
                    paramMdl, rsrRsid, sessionUsrSid, now, appRootPath);
            kryrkDao.insert(kryrkMdl);
        }

        /*******************************************************
        *
        * 施設予約データ登録
        *
        *******************************************************/


        //登録モデルのベース作成
        RsvScdOperationModel schMdl = new RsvScdOperationModel();
        schMdl.setScdDaily(GSConstSchedule.TIME_EXIST);
        schMdl.setScdBgcolor(GSConstSchedule.DF_BG_COLOR);
        schMdl.setScdTitle(NullDefault.getString(paramMdl.getRsv111RsrMok(), ""));
        schMdl.setScdValue(NullDefault.getString(paramMdl.getRsv111RsrBiko(), ""));
        schMdl.setScdBiko("");
        schMdl.setScdPublic(GSConstSchedule.DSP_PUBLIC);
        schMdl.setScdAuid(sessionUsrSid);
        schMdl.setScdAdate(now);
        schMdl.setScdEuid(sessionUsrSid);
        schMdl.setScdEdate(now);
        schMdl.setScdEdit(RsvScheduleBiz.getScdEditKbn(paramMdl.getRsv111RsrEdit()));

        //拡張登録SID
        int extSid = -1;
        extSid =
            (int) ctrl.getSaibanNumber(
                    SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH_EX,
                    sessionUsrSid);

        schMdl.setSceSid(extSid);


        //取得のチェック
        Rsv111Biz biz = new Rsv111Biz(reqMdl_, con_);
        HashMap<String, String> dayMap = biz.getInsertDateList(paramMdl);
        String[] users = paramMdl.getRsv111SvUsers();

        if (!dayMap.isEmpty()) {
            ArrayList<RsvSisYrkModel> updArray = new ArrayList<RsvSisYrkModel>();
            ArrayList<RsvSisKyrkModel> kyrkArray = new ArrayList<RsvSisKyrkModel>();
            ArrayList<String> dayList = new ArrayList<String>(dayMap.values());
            Collections.sort(dayList);

            RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
            RsvScdOperationDao opDao = new RsvScdOperationDao(con_);
            for (String day : dayList) {

                RsvSisYrkModel mdl = new RsvSisYrkModel();
                mdl = __getNewYrkBaseModel(paramMdl, sessionUsrSid, now, day, ctrl);

                //------- スケジュール情報の登録 ---------------
                UDate frUd = mdl.getRsyFrDate().cloneUDate();
                UDate toUd = mdl.getRsyToDate().cloneUDate();

                schMdl.setScdFrDate(frUd);
                schMdl.setScdToDate(toUd);

                int scdGpSid = -1;
                int rsSid = -1;

                if (paramMdl.getRsv111SchKbn() == GSConstReserve.RSV_SCHKBN_USER
                && users.length > 1) {
                    //スケジュールグループSID
                    scdGpSid =
                        (int) ctrl.getSaibanNumber(
                                SaibanModel.SBNSID_SCHEDULE,
                                SaibanModel.SBNSID_SUB_SCH_GP,
                                sessionUsrSid);
                }
                //リレーションSID採番
                rsSid =
                    (int) ctrl.getSaibanNumber(
                            SaibanModel.SBNSID_SCHEDULE,
                            SaibanModel.SBNSID_SUB_SCH_RES,
                            sessionUsrSid);

                schMdl.setScdGrpSid(scdGpSid);
                schMdl.setScdRsSid(rsSid);

                if (paramMdl.getRsv111SchKbn() == GSConstReserve.RSV_SCHKBN_GROUP) {
                    int scdSid =
                        (int) ctrl.getSaibanNumber(
                                SaibanModel.SBNSID_SCHEDULE,
                                SaibanModel.SBNSID_SUB_SCH,
                                sessionUsrSid);

                    schMdl.setScdSid(scdSid);
                    schMdl.setScdUsrSid(Integer.parseInt(paramMdl.getRsv111SchGroupSid()));
                    schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_GROUP);
                    opDao.insertSchData(schMdl);
                } else {
                    for (String addUser : users) {
                        int scdSid =
                            (int) ctrl.getSaibanNumber(
                                    SaibanModel.SBNSID_SCHEDULE,
                                    SaibanModel.SBNSID_SUB_SCH,
                                    sessionUsrSid);

                        int addUserSid = Integer.parseInt(addUser);

                        schMdl.setScdSid(scdSid);
                        schMdl.setScdUsrSid(addUserSid);
                        schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_USER);
                        opDao.insertSchData(schMdl);
                    }
                }
                //-----------------------------------------------

                //リレーションSID
                mdl.setScdRsSid(rsSid);
                //施設SID
                mdl.setRsdSid(sisetuSid);
                //登録者ID
                mdl.setRsyAuid(sessionUsrSid);
                //登録日時
                mdl.setRsyAdate(now);
                //施設予約拡張SID
                mdl.setRsrRsid(rsrRsid);
                //承認状況
                rsvCmnBiz.setSisYrkApprData(con_, sisetuSid, mdl, userSid);

                updArray.add(mdl);
                sidDataList.add(new int []{mdl.getRsySid(), sisetuSid});

                //施設予約区分別情報登録
                if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
                    RsvSisKyrkModel kyrkMdl =
                            __getKyrModel(
                                    paramMdl, mdl.getRsySid(), sessionUsrSid, now, appRootPath);
                    kyrkArray.add(kyrkMdl);
                }
            }

            if (!updArray.isEmpty()) {
                yrkDao.insertNewYoyakuPlural2(updArray);

                //施設予約区分別情報登録
                if (!kyrkArray.isEmpty()) {
                    kyrkDao.insert(kyrkArray);
                }

                //スケジュール拡張情報の登録
                __insertSchExData(paramMdl, extSid, null, sessionUsrSid, now);
            }

        }
        return sidDataList;
    }

    /**
     * <br>[機  能] 繰り返し施設予約情報を更新する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param ctrl 採番用コネクション
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @return sidDataList 予約SID、施設SIDリスト
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<int []> __updateYoyaku(Rsv111knParamModel paramMdl,
                                 MlCountMtController ctrl, int userSid, String appRootPath)
        throws SQLException {

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con_);
        RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);
        RsvSisKryrkDao kryrkDao = new RsvSisKryrkDao(con_);

        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int sessionUsrSid = usrMdl.getUsrsid();

        //予約情報取得
        Rsv110SisetuModel yrkMdl = __getYoyakuData(paramMdl);
        int sisetuSid = yrkMdl.getRsdSid();
        int auid = sessionUsrSid;
        UDate old = yrkMdl.getRsyAdate();
        UDate now = new UDate();
        ArrayList<int []> sidDataList = new ArrayList<int[]>();

       /*******************************************************
        *
        * 施設予約拡張データ登録
        *
        *******************************************************/
        RsvSisRyrkModel updRyrk =
            __getRyrkBaseModel(paramMdl, sessionUsrSid, now, ctrl);

        //拡張予約拡張SID採番
        int rsrRsid =
            (int) ctrl.getSaibanNumber(
                    GSConstReserve.SBNSID_RESERVE,
                    GSConstReserve.SBNSID_SUB_KAKUTYO,
                    sessionUsrSid);

        //施設予約拡張SID
        updRyrk.setRsrRsid(rsrRsid);
        paramMdl.setExtendSid(rsrRsid);

        //拡張予約データ登録
        ryrkDao.insert(updRyrk);

        //施設予約拡張区分別データ登録
        if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
            RsvSisKryrkModel kryrkMdl =
                    __getKryrkModel(paramMdl, rsrRsid, sessionUsrSid, now, appRootPath);
            kryrkDao.insert(kryrkMdl);
        }


       /*******************************************************
        *
        * 施設予約データ登録
        *
        *******************************************************/

        //取得のチェック
        Rsv111Biz biz = new Rsv111Biz(reqMdl_, con_);
        HashMap<String, String> dayMap = biz.getInsertDateList(paramMdl);

        if (!dayMap.isEmpty()) {
            ArrayList<RsvSisYrkModel> updArray = new ArrayList<RsvSisYrkModel>();
            ArrayList<RsvSisKyrkModel> kyrkArray = new ArrayList<RsvSisKyrkModel>();
            ArrayList<String> dayList = new ArrayList<String>(dayMap.values());
            Collections.sort(dayList);

            RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
            for (String day : dayList) {

                RsvSisYrkModel mdl = new RsvSisYrkModel();

                mdl = __getNewYrkBaseModel(paramMdl, sessionUsrSid, now, day, ctrl);

                //施設SID
                mdl.setRsdSid(sisetuSid);
                //登録者ID
                mdl.setRsyAuid(auid);
                //登録日時
                mdl.setRsyAdate(old);
                //施設予約拡張SID
                mdl.setRsrRsid(rsrRsid);
                //承認状況
                rsvCmnBiz.setSisYrkApprData(con_, sisetuSid, mdl, userSid);

                updArray.add(mdl);
                sidDataList.add(new int [] {mdl.getRsySid(), sisetuSid});

                //施設予約区分別情報登録
                if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
                    RsvSisKyrkModel kyrkMdl =
                            __getKyrModel(
                                    paramMdl, mdl.getRsySid(), sessionUsrSid, now, appRootPath);
                    kyrkMdl.setRkyAdate(old);
                    kyrkArray.add(kyrkMdl);
                }
            }

            if (!updArray.isEmpty()) {
                yrkDao.insertNewYoyakuPlural(updArray);

                //施設予約区分別情報登録
                if (!kyrkArray.isEmpty()) {
                        kyrkDao.insert(kyrkArray);
                }
            }

            //既存データの削除
            if (yrkMdl.getRsrRsid() > 0) {
                ArrayList<Integer> rsySids = yrkDao.getYrkDataSidList(yrkMdl.getRsrRsid());
                yrkDao.deleteRyrkData(yrkMdl.getRsrRsid());
                ryrkDao.delete(yrkMdl.getRsrRsid());
                kyrkDao.delete(rsySids);
                kryrkDao.delete(yrkMdl.getRsrRsid());
            } else {
                RsvSisYrkModel param = new RsvSisYrkModel();
                param.setRsySid(paramMdl.getRsv110RsySid());
                yrkDao.delete(param);
                kyrkDao.delete(paramMdl.getRsv110RsySid());
            }
        }
        return sidDataList;
    }

    /**
     * <br>[機  能] スケジュールと結びついた繰り返し施設予約情報を更新する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param ctrl 採番用コネクション
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @return sidDataList 予約SID、施設SIDリスト
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<int []> __updateKurikaeshiYoyaku(
            Rsv111knParamModel paramMdl, MlCountMtController ctrl, int userSid, String appRootPath)
        throws SQLException {

        RsvScdOperationDao scdDao = new RsvScdOperationDao(con_);
        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        RsvSisRyrkDao ryrkDao = new RsvSisRyrkDao(con_);
        RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con_);
        RsvSisKryrkDao kryrkDao = new RsvSisKryrkDao(con_);

        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int sessionUsrSid = usrMdl.getUsrsid();
        UDate now = new UDate();
        ArrayList<int []> sidDataList = new ArrayList<int []>();

        ArrayList<Integer> scdRsSidArray = new ArrayList<Integer>();
        ArrayList<Integer> usrSidArray = new ArrayList<Integer>();
        ArrayList<Integer> rsdSidArray = new ArrayList<Integer>();
        int scdUsrKbn = GSConstSchedule.USER_KBN_USER;
        Rsv110SisetuModel yrkRet = null;
        //予約情報取得
        yrkRet = __getYoyakuData(paramMdl);

        //スケジュール拡張SID取得
        int sceSid = scdDao.selectSceSid(paramMdl.getRsv110ScdRsSid());

        if (sceSid > 0) {
            //拡張登録された全ての施設のスケジュールリレーションSIDを取得
            scdRsSidArray = scdDao.selectKakutyoAllScdRsSid(sceSid);
            //登録対象ユーザ取得
            usrSidArray = scdDao.selectKakutyoAllUsrSid(sceSid);
            //登録対象施設取得
            rsdSidArray = yrkDao.getKakutyoAllRsdSid(scdRsSidArray);

            //スケジュール ユーザ区分
            scdUsrKbn = scdDao.selectKakutyoUsrKbn(sceSid);
        } else {
            //施設のスケジュールリレーションSIDを取得
            scdRsSidArray.add(yrkRet.getScdRsSid());
            //登録対象ユーザ取得
            usrSidArray = scdDao.selectScdUsrSid(yrkRet.getScdRsSid());
            //登録対象施設取得
            rsdSidArray.add(yrkRet.getRsdSid());

            //スケジュール ユーザ区分
            scdUsrKbn = scdDao.getSchUsrKbn(yrkRet.getScdRsSid());
        }

        //スケジュールアクセス不可グループ or ユーザを取得
        int sessionUserSid = reqMdl_.getSmodel().getUsrsid();
        SchDao schDao = new SchDao(con_);
        List<Integer> notAccessList = new ArrayList<Integer>();
        if (scdUsrKbn == GSConstReserve.RSV_SCHKBN_GROUP) {
            notAccessList = schDao.getNotRegistGrpList(sessionUserSid);
        } else {
            notAccessList = schDao.getNotRegistUserList(sessionUserSid);
        }

        //登録対象ユーザからスケジュールアクセス不可グループ or ユーザを除外する
        ArrayList<Integer> schAccessUserList = new ArrayList<Integer>();
        ArrayList<Integer> schNotAccessUserList = new ArrayList<Integer>();
        for (int scdUsrSid : usrSidArray) {
            if (notAccessList.indexOf(scdUsrSid) < 0) {
                schAccessUserList.add(scdUsrSid);
            } else {
                schNotAccessUserList.add(scdUsrSid);
            }
        }
        usrSidArray = schAccessUserList;

        //既存データの登録者、登録日時取得
        RsvScdOperationModel oldMdl = scdDao.selectOldScdData(sceSid);
        if (oldMdl == null) {
            //ベースデータセット
            oldMdl = new RsvScdOperationModel();
            oldMdl.setSceBgcolor(GSConstSchedule.DF_BG_COLOR);
            oldMdl.setSceBiko("");
            oldMdl.setScePublic(GSConstSchedule.DSP_PUBLIC);
            oldMdl.setSceAuid(yrkRet.getRsyAuid());
            oldMdl.setSceAdate(yrkRet.getRsyAdate());
            oldMdl.setSceEdit(GSConstSchedule.EDIT_CONF_OWN);
        }

        RsvScdOperationModel schMdl = new RsvScdOperationModel();

        //登録モデルのベース作成
        schMdl.setScdDaily(0);
        schMdl.setScdBgcolor(oldMdl.getSceBgcolor());
        schMdl.setScdTitle(NullDefault.getString(paramMdl.getRsv111RsrMok(), ""));
        schMdl.setScdValue(NullDefault.getString(paramMdl.getRsv111RsrBiko(), ""));
        schMdl.setScdBiko(NullDefault.getString(oldMdl.getSceBiko(), ""));
        schMdl.setScdPublic(oldMdl.getScePublic());
        schMdl.setScdAuid(oldMdl.getSceAuid());
        schMdl.setScdAdate(oldMdl.getSceAdate());
        schMdl.setScdEuid(sessionUsrSid);
        schMdl.setScdEdate(now);
//        schMdl.setScdEdit(oldMdl.getSceEdit());
        schMdl.setScdEdit(RsvScheduleBiz.getScdEditKbn(paramMdl.getRsv111RsrEdit()));

        //拡張登録SID
        int extSid = -1;

        extSid =
            (int) ctrl.getSaibanNumber(
                    SaibanModel.SBNSID_SCHEDULE,
                    SaibanModel.SBNSID_SUB_SCH_EX,
                    sessionUsrSid);

        schMdl.setSceSid(extSid);

        //スケジュールグルプSID（同時登録有りの場合）
        int scdGpSid = -1;
        int scdResSid = -1;
        int scdSid = -1;

        Rsv111Biz biz = new Rsv111Biz(reqMdl_, con_);
        HashMap<Integer, Integer> rsrRsidMap = new HashMap<Integer, Integer>();
        HashMap<String, String> dayMap = biz.getInsertDateList(paramMdl);
        ArrayList<String> dayList = new ArrayList<String>(dayMap.values());
        Collections.sort(dayList);

        int schKbn = paramMdl.getRsv111SchKbn();
        int rsvSchGrpSid = NullDefault.getInt(paramMdl.getRsv111SchGroupSid(), -1);
        String[] users = paramMdl.getRsv111SvUsers();
        boolean entrySchedule = __checkInputSchedule(paramMdl);

        for (String day : dayList) {

            UDate frUd = new UDate();
            UDate toUd = new UDate();

            frUd.setDate(day);
            frUd.setHour(Integer.parseInt(paramMdl.getRsv111RsrTimeHourFr()));
            frUd.setMinute(Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteFr()));
            frUd.setSecond(GSConstReserve.DAY_START_SECOND);
            frUd.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

            toUd.setDate(day);
            toUd.setHour(Integer.parseInt(paramMdl.getRsv111RsrTimeHourTo()));
            toUd.setMinute(Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteTo()));
            toUd.setSecond(GSConstReserve.DAY_START_SECOND);
            toUd.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

            schMdl.setScdFrDate(frUd);
            schMdl.setScdToDate(toUd);

            if (paramMdl.getRsv111ScdReflection() == GSConstReserve.SCD_REFLECTION_NO) {

                if (!usrSidArray.isEmpty()) {
                    if (usrSidArray.size() > 1) {
                        //スケジュールグルプSID（同時登録有りの場合）
                        scdGpSid =
                            (int) ctrl.getSaibanNumber(
                                    SaibanModel.SBNSID_SCHEDULE,
                                    SaibanModel.SBNSID_SUB_SCH_GP,
                                    sessionUsrSid);
                    } else {
                        scdGpSid = -1;
                    }

                    //スケジュール施設予約SID
                    scdResSid =
                        (int) ctrl.getSaibanNumber(
                                SaibanModel.SBNSID_SCHEDULE,
                                SaibanModel.SBNSID_SUB_SCH_RES,
                                sessionUsrSid);

                    schMdl.setScdGrpSid(scdGpSid);
                    schMdl.setScdRsSid(scdResSid);
                    //スケジュールの登録
                    for (String addUser : users) {
                        scdSid =
                            (int) ctrl.getSaibanNumber(
                                    SaibanModel.SBNSID_SCHEDULE,
                                    SaibanModel.SBNSID_SUB_SCH,
                                    sessionUsrSid);

                        int addUserSid = Integer.parseInt(addUser);

                        schMdl.setScdSid(scdSid);
                        schMdl.setScdUsrSid(addUserSid);
                        schMdl.setScdUsrKbn(scdUsrKbn);
                        scdDao.insertSchData(schMdl);
                    }
                }
            } else if (entrySchedule) {

                if (schKbn == GSConstReserve.RSV_SCHKBN_USER && users.length > 1) {
                    //スケジュールグループSID（同時登録有りの場合）
                    scdGpSid =
                        (int) ctrl.getSaibanNumber(
                                SaibanModel.SBNSID_SCHEDULE,
                                SaibanModel.SBNSID_SUB_SCH_GP,
                                sessionUsrSid);
                } else {
                    scdGpSid = -1;
                }

                //スケジュール施設予約SID
                scdResSid =
                    (int) ctrl.getSaibanNumber(
                            SaibanModel.SBNSID_SCHEDULE,
                            SaibanModel.SBNSID_SUB_SCH_RES,
                            sessionUsrSid);

                schMdl.setScdGrpSid(scdGpSid);
                schMdl.setScdRsSid(scdResSid);

                if (paramMdl.getRsv111SchKbn() == GSConstReserve.RSV_SCHKBN_GROUP) {
                    //グループスケジュールの登録
                    scdSid =
                        (int) ctrl.getSaibanNumber(
                                SaibanModel.SBNSID_SCHEDULE,
                                SaibanModel.SBNSID_SUB_SCH,
                                sessionUsrSid);

                    schMdl.setScdSid(scdSid);
                    schMdl.setScdUsrSid(rsvSchGrpSid);
                    schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_GROUP);
                    scdDao.insertSchData(schMdl);

                } else {
                    //ユーザスケジュールの登録
                    for (String addUser : users) {
                        scdSid =
                            (int) ctrl.getSaibanNumber(
                                    SaibanModel.SBNSID_SCHEDULE,
                                    SaibanModel.SBNSID_SUB_SCH,
                                    sessionUsrSid);

                        int addUserSid = Integer.parseInt(addUser);

                        schMdl.setScdSid(scdSid);
                        schMdl.setScdUsrSid(addUserSid);
                        schMdl.setScdUsrKbn(GSConstSchedule.USER_KBN_USER);
                        scdDao.insertSchData(schMdl);
                    }
                }
            }

            if (!rsdSidArray.isEmpty()) {

                ArrayList<RsvSisYrkModel> updArray = new ArrayList<RsvSisYrkModel>();
                ArrayList<RsvSisKyrkModel> kyrkArray = new ArrayList<RsvSisKyrkModel>();
                RsvCommonBiz rsvCmnBiz = new RsvCommonBiz();
                for (int rsdSid : rsdSidArray) {

                    int rsrRsid = -1;
                    if (rsrRsidMap.containsKey(rsdSid)) {
                        rsrRsid = rsrRsidMap.get(rsdSid);
                    } else {
                        //拡張予約拡張SID採番
                        rsrRsid =
                            (int) ctrl.getSaibanNumber(
                                    GSConstReserve.SBNSID_RESERVE,
                                    GSConstReserve.SBNSID_SUB_KAKUTYO,
                                    sessionUsrSid);

                        rsrRsidMap.put(rsdSid, rsrRsid);
                    }

                    RsvSisYrkModel mdl = new RsvSisYrkModel();
                    mdl = __getNewYrkBaseModel(paramMdl, sessionUsrSid, now, day, ctrl);

                    //施設SID
                    mdl.setRsdSid(rsdSid);
                    //スケジュールリレーションSID
                    mdl.setScdRsSid(scdResSid);
                    //登録者ID
                    mdl.setRsyAuid(oldMdl.getSceAuid());
                    //登録日時
                    mdl.setRsyAdate(oldMdl.getSceAdate());
                    //施設予約拡張SID
                    mdl.setRsrRsid(rsrRsid);
                    //承認状況
                    rsvCmnBiz.setSisYrkApprData(con_, rsdSid, mdl, userSid);

                    updArray.add(mdl);
                    sidDataList.add(new int []{mdl.getRsySid(), rsdSid});

                    //施設予約区分別情報登録
                    if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
                        RsvSisKyrkModel kyrkMdl =
                                __getKyrModel(
                                        paramMdl, mdl.getRsySid(), sessionUsrSid, now, appRootPath);
                        kyrkArray.add(kyrkMdl);
                    }
                }

                if (!updArray.isEmpty()) {
                    yrkDao.insertUpdYoyakuPlural(updArray);
                    //施設予約区分別情報登録
                    if (!kyrkArray.isEmpty()) {
                        kyrkDao.insert(kyrkArray);
                    }
                }
            }
        }

        if (!rsrRsidMap.isEmpty()) {

            ArrayList<Integer> rsrRsidList =
                new ArrayList<Integer>(rsrRsidMap.values());

            for (int rsrRsid : rsrRsidList) {

                RsvSisRyrkModel updRyrk =
                    __getRyrkBaseModel(paramMdl, sessionUsrSid, now, ctrl);

                //施設予約拡張SID
                updRyrk.setRsrRsid(rsrRsid);

                //拡張予約データ登録
                ryrkDao.insert(updRyrk);

                //施設予約拡張区分別データ登録
                if (_isRskKbnRegCheck(paramMdl.getRsv110SisetuKbn())) {
                    RsvSisKryrkModel kryrkMdl =
                            __getKryrkModel(paramMdl, rsrRsid, sessionUsrSid, now, appRootPath);
                    kryrkDao.insert(kryrkMdl);
                }
            }

            //選択拡張SIDをパラメータに格納
            Rsv110SisetuModel yrkMdl = __getYoyakuData(paramMdl);
            int sisetuSid = yrkMdl.getRsdSid();
            if (rsrRsidMap.containsKey(Integer.valueOf(sisetuSid))) {
                paramMdl.setExtendSid(rsrRsidMap.get(sisetuSid));
            }
        }

        //スケジュール拡張情報の登録
        __insertSchExData(paramMdl, extSid, null, sessionUsrSid, now);

        if (yrkRet.getRsrRsid() > 0) {
            //施設情報削除
            yrkDao.deleteRyrkData(yrkRet.getRsrRsid());
            //施設予約区分別情報削除
            ryrkDao.delete(yrkRet.getRsrRsid());
            //施設予約拡張区分別情報削除
            kryrkDao.delete(yrkRet.getRsrRsid());

            //施設予約拡張情報削除
            ArrayList<Integer> rsySids = yrkDao.getYrkDataSidList(yrkRet.getRsrRsid());
            kyrkDao.delete(rsySids);

        } else if (paramMdl.getRsv110RsySid() > 0) {
            RsvSisYrkModel param = new RsvSisYrkModel();
            param.setRsySid(paramMdl.getRsv110RsySid());
            yrkDao.delete(param);
            kyrkDao.delete(paramMdl.getRsv110RsySid());
        }

        //削除対象スケジュールのスケジュールユーザSID
        String[] delScdUsrSid = new String[schAccessUserList.size()];
        for (int scdUsrIdx = 0; scdUsrIdx < schAccessUserList.size(); scdUsrIdx++) {
            delScdUsrSid[scdUsrIdx]
                    = String.valueOf(schAccessUserList.get(scdUsrIdx));
        }

        if (sceSid > 0) {
            //スケジュール削除
//            scdDao.deleteKakutyoData(sceSid);
            scdDao.deleteKakutyoDataWithUsers(sceSid, delScdUsrSid);

            //スケジュール拡張に紐付くスケジュールが無くなった
            if (scdDao.selectExDataCnt(sceSid) == 0) {
                //スケジュール拡張データ削除
                scdDao.deleteExData(sceSid);
            }
        } else {
            //スケジュール削除
            if (yrkRet.getScdRsSid() > 0) {
//                scdDao.deleteScdTi(yrkRet.getScdRsSid());
                scdDao.deleteScdTiWithUsers(yrkRet.getScdRsSid(), delScdUsrSid);
            }
        }
        return sidDataList;
    }

    /**
     * <br>[機  能] 拡張予約データモデル取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param sessionUsrSid セッションユーザSID
     * @param now システム日付
     * @param ctrl 採番用コネクション
     * @return ret 拡張予約データモデル
     */
    private RsvSisRyrkModel __getRyrkBaseModel(Rsv111knParamModel paramMdl,
                                                int sessionUsrSid,
                                                UDate now,
                                                MlCountMtController ctrl) {

        RsvSisRyrkModel ret = new RsvSisRyrkModel();

        //区分
        ret.setRsrKbn(paramMdl.getRsv111RsrKbn());
        //曜日(日曜)
        ret.setRsrDweek1(paramMdl.getRsv111RsrDweek1());
        //曜日(月曜)
        ret.setRsrDweek2(paramMdl.getRsv111RsrDweek2());
        //曜日(火曜)
        ret.setRsrDweek3(paramMdl.getRsv111RsrDweek3());
        //曜日(水曜)
        ret.setRsrDweek4(paramMdl.getRsv111RsrDweek4());
        //曜日(木曜)
        ret.setRsrDweek5(paramMdl.getRsv111RsrDweek5());
        //曜日(金曜)
        ret.setRsrDweek6(paramMdl.getRsv111RsrDweek6());
        //曜日(土曜)
        ret.setRsrDweek7(paramMdl.getRsv111RsrDweek7());
        //日
        ret.setRsrDay(paramMdl.getRsv111RsrDay());
        //週
        ret.setRsrWeek(paramMdl.getRsv111RsrWeek());
        //毎年 月
        ret.setRsrMonthYearly(paramMdl.getRsv111RsrMonthOfYearly());
        //毎年 日
        ret.setRsrDayYearly(paramMdl.getRsv111RsrDayOfYearly());

        //時分from
        UDate timeFr = now.cloneUDate();
        timeFr.setHour(Integer.parseInt(paramMdl.getRsv111RsrTimeHourFr()));
        timeFr.setMinute(Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteFr()));
        ret.setRsrTimeFr(timeFr);

        //時分to
        UDate timeTo = now.cloneUDate();
        timeTo.setHour(Integer.parseInt(paramMdl.getRsv111RsrTimeHourTo()));
        timeTo.setMinute(Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteTo()));
        ret.setRsrTimeTo(timeTo);

        //振替区分
        ret.setRsrTranKbn(paramMdl.getRsv111RsrTranKbn());

        //反映期間from
        UDate dateFr = new UDate();
        dateFr.setDate(
                Integer.parseInt(paramMdl.getRsv111RsrDateYearFr()),
                Integer.parseInt(paramMdl.getRsv111RsrDateMonthFr()),
                Integer.parseInt(paramMdl.getRsv111RsrDateDayFr()));
        ret.setRsrDateFr(dateFr);

        //反映期間to
        UDate dateTo = new UDate();
        dateTo.setDate(
                Integer.parseInt(paramMdl.getRsv111RsrDateYearTo()),
                Integer.parseInt(paramMdl.getRsv111RsrDateMonthTo()),
                Integer.parseInt(paramMdl.getRsv111RsrDateDayTo()));
        ret.setRsrDateTo(dateTo);

        //利用目的
        ret.setRsrMok(NullDefault.getString(paramMdl.getRsv111RsrMok(), ""));
        //内容
        ret.setRsrBiko(NullDefault.getString(paramMdl.getRsv111RsrBiko(), ""));
        //編集権限設定
        ret.setRsrEdit(paramMdl.getRsv111RsrEdit());
        //登録者ID
        ret.setRsrAuid(sessionUsrSid);
        //登録日時
        ret.setRsrAdate(now);
        //更新者ID
        ret.setRsrEuid(sessionUsrSid);
        //更新日時
        ret.setRsrEdate(now);

        return ret;
    }

    /**
     * <br>[機  能] 予約データモデル取得
     * <br>[解  説]
     * <br>[備  考] 新規登録時
     *
     * @param paramMdl Rsv111knParamModel
     * @param sessionUsrSid セッションユーザSID
     * @param now システム日付
     * @param ctrl 採番用コネクション
     * @param day 登録日付
     * @return ret 予約データモデル
     * @throws SQLException SQL実行時例外
     */
    private RsvSisYrkModel __getNewYrkBaseModel(Rsv111knParamModel paramMdl,
                                                 int sessionUsrSid,
                                                 UDate now,
                                                 String day,
                                                 MlCountMtController ctrl)
        throws SQLException {

        RsvSisYrkModel ret = new RsvSisYrkModel();

        //予約SID採番
        int yoyakuSid =
            (int) ctrl.getSaibanNumber(
                    GSConstReserve.SBNSID_RESERVE,
                    GSConstReserve.SBNSID_SUB_YOYAKU,
                    sessionUsrSid);

        //予約情報SID
        ret.setRsySid(yoyakuSid);
        //利用目的
        ret.setRsyMok(NullDefault.getString(paramMdl.getRsv111RsrMok(), ""));

        UDate frUd = new UDate();
        UDate toUd = new UDate();

        frUd.setDate(day);
        frUd.setHour(Integer.parseInt(paramMdl.getRsv111RsrTimeHourFr()));
        frUd.setMinute(Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteFr()));
        frUd.setSecond(GSConstReserve.DAY_START_SECOND);
        frUd.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

        toUd.setDate(day);
        toUd.setHour(Integer.parseInt(paramMdl.getRsv111RsrTimeHourTo()));
        toUd.setMinute(Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteTo()));
        toUd.setSecond(GSConstReserve.DAY_START_SECOND);
        toUd.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);

        //予約開始
        ret.setRsyFrDate(frUd);
        //予約終了
        ret.setRsyToDate(toUd);
        //備考
        ret.setRsyBiko(NullDefault.getString(paramMdl.getRsv111RsrBiko(), ""));
        //編集権限
        ret.setRsyEdit(paramMdl.getRsv111RsrEdit());
        //更新者ID
        ret.setRsyEuid(sessionUsrSid);
        //更新日時
        ret.setRsyEdate(now);

        return ret;
    }

    /**
     * <br>[機  能] 予約情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @return ret 予約情報
     * @throws SQLException SQL実行時例外
     */
    private Rsv110SisetuModel __getYoyakuData(Rsv111knParamModel paramMdl) throws SQLException {

        RsvSisYrkDao yrkDao = new RsvSisYrkDao(con_);
        Rsv110SisetuModel ret = yrkDao.selectYoyakuEditData(paramMdl.getRsv110RsySid());

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
     * <br>[機  能] 施設区分に応じて施設のヘッダ文字列をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param rskSid 施設区分SID
     */
    private void __setSisetuHeader(Rsv111knParamModel paramMdl, int rskSid) {

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
     * <br>[機  能] DBから取得した施設グループ情報をセットする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param dbMdl DB取得結果
     */
    private void __setGroupData(Rsv111knParamModel paramMdl,
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
     * <br>[機  能] 今回登録する日付リストを設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @throws SQLException SQL実行時例外
     */
    private void __setNewKurikaeshiDataList(Rsv111knParamModel paramMdl) throws SQLException {

        ArrayList<String> newList = new ArrayList<String>();

        //登録日のチェック
        Rsv111Biz biz = new Rsv111Biz(reqMdl_, con_);
        HashMap<String, String> dayMap = biz.getInsertDateList(paramMdl);

        if (!dayMap.isEmpty()) {
            ArrayList<String> dayList = new ArrayList<String>(dayMap.values());
            Collections.sort(dayList);

            for (String day : dayList) {
                UDate dayUd = new UDate();
                dayUd.setDate(day);
                newList.add(UDateUtil.getSlashYYMD(dayUd));
            }
        }

        paramMdl.setTargetDay(newList);
    }

    /**
     * <br>[機  能] 登録済データの日付リストを設定
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @throws SQLException SQL実行時例外
     */
    private void __setOldKurikaeshiDataList(Rsv111knParamModel paramMdl) throws SQLException {

        ArrayList<String> ret = new ArrayList<String>();

        RsvSisYrkDao dao = new RsvSisYrkDao(con_);
        //予約拡張SID取得
        int rsrRsid = paramMdl.getRsv111RsrRsid();

        if (rsrRsid > 0) {
            ret = dao.getKurikaeshiDataList(rsrRsid);
        } else {
            //予約SID取得
            int rsySid = paramMdl.getRsv110RsySid();
            ret = dao.getTanituDataList(rsySid);
        }

        paramMdl.setOldDay(ret);
    }

    /**
     * <br>[機  能] 年月日選択値をyyyy/MM/ddに変換する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param convKbn 1:from 2:to
     * @return convDateString 変換後
     */
    private String __convertUdateToYmd(Rsv111knParamModel paramMdl, int convKbn) {

        String convDateString = "";
        StringBuilder convBuf = new StringBuilder();

        int intYear = -1;
        int intMonth = -1;
        int intDay = -1;

        if (convKbn == 1) {
            intYear = Integer.parseInt(paramMdl.getRsv111RsrDateYearFr());
            intMonth = Integer.parseInt(paramMdl.getRsv111RsrDateMonthFr());
            intDay = Integer.parseInt(paramMdl.getRsv111RsrDateDayFr());
        } else if (convKbn == 2) {
            intYear = Integer.parseInt(paramMdl.getRsv111RsrDateYearTo());
            intMonth = Integer.parseInt(paramMdl.getRsv111RsrDateMonthTo());
            intDay = Integer.parseInt(paramMdl.getRsv111RsrDateDayTo());
        }

        UDate convUd = new UDate();
        convUd.setDate(intYear, intMonth, intDay);
        convBuf.append(UDateUtil.getSlashYYMD(convUd));
        convDateString = convBuf.toString();

        return convDateString;
    }

    /**
     * <br>[機  能] 時間選択値をhh:mmに変換する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @param convKbn 1:from 2:to
     * @return convDateString 変換後
     */
    private String __convertUdateToHm(Rsv111knParamModel paramMdl, int convKbn) {

        String convDateString = "";
        StringBuilder convBuf = new StringBuilder();

        String strHour = "";
        String strMinute = "";

        if (convKbn == 1) {
            strHour = StringUtil.toDecFormat(paramMdl.getRsv111RsrTimeHourFr(), "00");
            strMinute = StringUtil.toDecFormat(paramMdl.getRsv111RsrTimeMinuteFr(), "00");
        } else if (convKbn == 2) {
            strHour = StringUtil.toDecFormat(paramMdl.getRsv111RsrTimeHourTo(), "00");
            strMinute = StringUtil.toDecFormat(paramMdl.getRsv111RsrTimeMinuteTo(), "00");
        }

        convBuf.append(strHour);
        convBuf.append("：");
        convBuf.append(strMinute);
        convDateString = convBuf.toString();

        return convDateString;
    }

    /**
     * <br>[機  能] 施設予約データに対応するスケジュールデータが存在するかチェック
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv111knParamModel
     * @throws SQLException SQL実行時例外
     */
    private void __existSchData(Rsv111ParamModel paramMdl)
        throws SQLException {

        RsvScdOperationDao scdDao = new RsvScdOperationDao(con_);

        //スケジュール拡張SID取得
        int sceSid = scdDao.selectSceSid(paramMdl.getRsv110ScdRsSid());

        if (sceSid < 1) {
            paramMdl.setRsv111ExistSchDateFlg(false);
            return;
        }

        int exdataCnt = scdDao.selectExDataCnt(sceSid);
        if (exdataCnt < 1) {
            //スケジュール拡張に紐付くスケジュールが無い
            paramMdl.setRsv111ExistSchDateFlg(false);
            return;
        }

        paramMdl.setRsv111ExistSchDateFlg(true);
    }

    /**
     * <br>[機  能] スケジュール拡張情報を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv111knParamModel
     * @param extSid スケジュール拡張SID
     * @param oldMdl 変更前スケジュール拡張情報(新規登録時はnull);
     * @param sessionUsrSid セッションユーザSID
     * @param now 現在日時
     * @return 登録したスケジュール拡張情報
     * @throws SQLException SQL実行時例外
     */
    public RsvScdOperationModel __insertSchExData(Rsv111knParamModel paramMdl, int extSid,
                                    RsvScdOperationModel oldMdl, int sessionUsrSid, UDate now)
    throws SQLException {

        RsvScdOperationModel exMdl = new RsvScdOperationModel();
        exMdl.setSceSid(extSid);
        exMdl.setSceKbn(paramMdl.getRsv111RsrKbn());
        exMdl.setSceDweek1(paramMdl.getRsv111RsrDweek1());
        exMdl.setSceDweek2(paramMdl.getRsv111RsrDweek2());
        exMdl.setSceDweek3(paramMdl.getRsv111RsrDweek3());
        exMdl.setSceDweek4(paramMdl.getRsv111RsrDweek4());
        exMdl.setSceDweek5(paramMdl.getRsv111RsrDweek5());
        exMdl.setSceDweek6(paramMdl.getRsv111RsrDweek6());
        exMdl.setSceDweek7(paramMdl.getRsv111RsrDweek7());
        exMdl.setSceDay(paramMdl.getRsv111RsrDay());
        exMdl.setSceWeek(paramMdl.getRsv111RsrWeek());
        exMdl.setSceMonthOfYearly(paramMdl.getRsv111RsrMonthOfYearly());
        exMdl.setSceDayOfYearly(paramMdl.getRsv111RsrDayOfYearly());

        //時分from
        UDate timeFr = now.cloneUDate();
        timeFr.setHour(Integer.parseInt(paramMdl.getRsv111RsrTimeHourFr()));
        timeFr.setMinute(Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteFr()));
        timeFr.setSecond(GSConstReserve.DAY_START_SECOND);
        timeFr.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);
        exMdl.setSceTimeFr(timeFr);

        //時分to
        UDate timeTo = now.cloneUDate();
        timeTo.setHour(Integer.parseInt(paramMdl.getRsv111RsrTimeHourTo()));
        timeTo.setMinute(Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteTo()));
        timeTo.setSecond(GSConstReserve.DAY_START_SECOND);
        timeTo.setMilliSecond(GSConstReserve.DAY_START_MILLISECOND);
        exMdl.setSceTimeTo(timeTo);

        exMdl.setSceTranKbn(paramMdl.getRsv111RsrTranKbn());

        //反映期間from
        UDate dateFr = new UDate();
        dateFr.setDate(
                Integer.parseInt(paramMdl.getRsv111RsrDateYearFr()),
                Integer.parseInt(paramMdl.getRsv111RsrDateMonthFr()),
                Integer.parseInt(paramMdl.getRsv111RsrDateDayFr()));
        dateFr.setZeroHhMmSs();
        exMdl.setSceDateFr(dateFr);

        //反映期間to
        UDate dateTo = new UDate();
        dateTo.setDate(
                Integer.parseInt(paramMdl.getRsv111RsrDateYearTo()),
                Integer.parseInt(paramMdl.getRsv111RsrDateMonthTo()),
                Integer.parseInt(paramMdl.getRsv111RsrDateDayTo()));
        dateTo.setZeroHhMmSs();
        exMdl.setSceDateTo(dateTo);

        if (oldMdl != null) {
            exMdl.setSceBgcolor(oldMdl.getSceBgcolor());
            exMdl.setSceBiko(NullDefault.getString(oldMdl.getSceBiko(), ""));
            exMdl.setScePublic(oldMdl.getScePublic());
            exMdl.setSceAuid(oldMdl.getSceAuid());
            exMdl.setSceAdate(oldMdl.getSceAdate());
//            exMdl.setSceEdit(oldMdl.getSceEdit());
        } else {
            exMdl.setSceBgcolor(GSConstSchedule.DF_BG_COLOR);
            exMdl.setSceBiko("");
            exMdl.setScePublic(GSConstSchedule.DSP_PUBLIC);
            exMdl.setSceAuid(sessionUsrSid);
            exMdl.setSceAdate(now);
//            exMdl.setSceEdit(GSConstSchedule.EDIT_CONF_OWN);
        }

        exMdl.setSceEdit(RsvScheduleBiz.getScdEditKbn(paramMdl.getRsv111RsrEdit()));
        exMdl.setSceTitle(NullDefault.getString(paramMdl.getRsv111RsrMok(), ""));
        exMdl.setSceValue(NullDefault.getString(paramMdl.getRsv111RsrBiko(), ""));
        exMdl.setSceEuid(sessionUsrSid);
        exMdl.setSceEdate(now);

        //登録
        RsvScdOperationDao scdDao = new RsvScdOperationDao(con_);
        scdDao.insert(exMdl);

        return exMdl;
    }

    /**
     * <br>[機  能] スケジュールが入力されているかを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv111knParamModel
     * @return true:入力されている false:未入力
     */
    private boolean __checkInputSchedule(Rsv111knParamModel paramMdl) {
        int schKbn = paramMdl.getRsv111SchKbn();
        int rsvSchGrpSid = NullDefault.getInt(paramMdl.getRsv111SchGroupSid(), -1);
        String[] users = paramMdl.getRsv111SvUsers();
        return (schKbn == GSConstReserve.RSV_SCHKBN_GROUP && rsvSchGrpSid >= 0)
        || (schKbn == GSConstReserve.RSV_SCHKBN_USER && users != null && users.length > 0);
    }

    /**
     * <br>[機  能] 予約情報登録者ユーザSID取得。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv111knParamModel
     * @return int ユーザSID
     * @throws Exception 実行例外
     */
    public int getEntryUserSid(
        Rsv111knParamModel paramMdl) throws Exception {

        int entryUserSid = -1;
        Rsv110SisetuModel model = __getYoyakuData(paramMdl);

        entryUserSid = model.getRsyAuid();
        return entryUserSid;
    }

    /**
     * <br>[機  能] ショートメールで通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv111knParamModel
     * @param cntCon MlCountMtController
     * @param userSid 更新者SID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig PluginConfig
     * @param entryUserSid 登録ユーザSID
     * @throws Exception 実行例外
     */
    public void sendSmail(
        Rsv111knParamModel paramMdl,
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
        UDate fromUDate = new UDate();
        UDate toUDate = new UDate();
        Rsv110SisetuModel model = new Rsv110SisetuModel();
        rsvModel.setRsvSid(model.getRsdSid());
        rsvModel.setRsvMokuteki(paramMdl.getRsv111RsrMok());
        rsvModel.setRsvNaiyou(paramMdl.getRsv111RsrBiko());
        rsvModel.setUserSid(userSid);

        fromUDate.setTimeStamp(Integer.parseInt(paramMdl.getRsv111RsrDateYearFr()),
                               Integer.parseInt(paramMdl.getRsv111RsrDateMonthFr()),
                               Integer.parseInt(paramMdl.getRsv111RsrDateDayFr()),
                               Integer.parseInt(paramMdl.getRsv111RsrTimeHourFr()),
                               Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteFr()),
                               0);

        toUDate.setTimeStamp(Integer.parseInt(paramMdl.getRsv111RsrDateYearTo()),
                             Integer.parseInt(paramMdl.getRsv111RsrDateMonthTo()),
                             Integer.parseInt(paramMdl.getRsv111RsrDateDayTo()),
                             Integer.parseInt(paramMdl.getRsv111RsrTimeHourTo()),
                             Integer.parseInt(paramMdl.getRsv111RsrTimeMinuteTo()),
                             0);

        rsvModel.setRsvFrDate(fromUDate);
        rsvModel.setRsvToDate(toUDate);
        rsvModel.setRsvAdate(now);

        rsvModel.setRsvUrl(createReserveLoopUrl(reqMdl_,
                                            paramMdl.getExtendSid(),
                                            Integer.parseInt(GSConstReserve.PROC_MODE_EDIT),
                                            strNow));
        rsvModel.setEditModeFlg(1);

        //送信
        sendSmail(con_, cntCon, rsvModel, appRootPath, pluginConfig, reqMdl_, entryUserSid);

    }

    /**
     * <br>[機  能] 施設予約区分別データモデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param rsrRsid 施設予約SID
     * @param sessionUsrSid セッションユーザSID
     * @param now 時間
     * @param appRootPath アプリケーションルートパス
     * @return 施設予約拡張区分別データモデル
     */
    private RsvSisKyrkModel __getKyrModel(
            Rsv111knParamModel paramMdl,
            int rsrRsid,
            int sessionUsrSid,
            UDate now,
            String appRootPath) {

        RsvSisKyrkModel mdl = new RsvSisKyrkModel();
        //予約SID
        mdl.setRsySid(rsrRsid);
        mdl.setRkyBusyo(paramMdl.getRsv111Busyo());
        mdl.setRkyName(paramMdl.getRsv111UseName());
        mdl.setRkyNum(paramMdl.getRsv111UseNum());
        mdl.setRkyUseKbn(paramMdl.getRsv111UseKbn());
        mdl.setRkyContact(paramMdl.getRsv111Contact());
        mdl.setRkyGuide(paramMdl.getRsv111Guide());
        mdl.setRkyParkNum(paramMdl.getRsv111ParkNum());
        if (RsvCommonBiz.isUsePrintKbn(appRootPath)) {
            mdl.setRkyPrintKbn(paramMdl.getRsv111PrintKbn());
        }
        mdl.setRkyDest(paramMdl.getRsv111Dest());
        mdl.setRkyAuid(sessionUsrSid);
        mdl.setRkyAdate(now);
        mdl.setRkyEuid(sessionUsrSid);
        mdl.setRkyEdate(now);

        return mdl;
    }

    /**
     * <br>[機  能] 施設予約拡張区分別データモデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param rsrRsid 施設予約拡張SID
     * @param sessionUsrSid セッションユーザSID
     * @param now 時間
     * @param appRootPath アプリケーションルートパス
     * @return 施設予約拡張区分別データモデル
     */
    private RsvSisKryrkModel __getKryrkModel(
            Rsv111knParamModel paramMdl,
            int rsrRsid,
            int sessionUsrSid,
            UDate now,
            String appRootPath) {
        RsvSisKryrkModel mdl = new RsvSisKryrkModel();

        mdl.setRsrRsid(rsrRsid);
        mdl.setRkrBusyo(paramMdl.getRsv111Busyo());
        mdl.setRkrName(paramMdl.getRsv111UseName());
        mdl.setRkrNum(paramMdl.getRsv111UseNum());
        mdl.setRkrUseKbn(paramMdl.getRsv111UseKbn());
        mdl.setRkrContact(paramMdl.getRsv111Contact());
        mdl.setRkrGuide(paramMdl.getRsv111Guide());
        mdl.setRkrParkNum(paramMdl.getRsv111ParkNum());
        if (RsvCommonBiz.isUsePrintKbn(appRootPath)) {
            mdl.setRkrPrintKbn(paramMdl.getRsv111PrintKbn());
        }
        mdl.setRkrDest(paramMdl.getRsv111Dest());
        mdl.setRkrAuid(sessionUsrSid);
        mdl.setRkrAdate(now);
        mdl.setRkrEuid(sessionUsrSid);
        mdl.setRkrEdate(now);

        return mdl;
    }
    /**
     *
     * <br>[機  能] 削除登録時のログを取得
     * <br>[解  説]
     * <br>[備  考]
     * @return ログ内容
     */
    public String getDelOpLog() {
        if (delLog__ != null) {
            return delLog__.toString();
        }
        return null;
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
    public String getOpLog(int sisetsuSid, Rsv111knParamModel paramMdl)
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
                String.valueOf(paramMdl.getRsv111RsrDateYearFr()),
                String.valueOf(paramMdl.getRsv111RsrDateMonthFr()),
                String.valueOf(paramMdl.getRsv111RsrDateDayFr()),
                String.valueOf(paramMdl.getRsv111RsrTimeHourFr()),
                String.valueOf(paramMdl.getRsv111RsrTimeMinuteFr())
        }));
        opLog.append(" ～ ");
        opLog.append(gsMsg.getMessage("cmn.view.date", new String[] {
                String.valueOf(paramMdl.getRsv111RsrDateYearTo()),
                String.valueOf(paramMdl.getRsv111RsrDateMonthTo()),
                String.valueOf(paramMdl.getRsv111RsrDateDayTo()),
                String.valueOf(paramMdl.getRsv111RsrTimeHourTo()),
                String.valueOf(paramMdl.getRsv111RsrTimeMinuteTo())
        }));
        opLog.append("\n");
        opLog.append("[");
        opLog.append(mokuteki);
        opLog.append("] ");
        opLog.append(paramMdl.getRsv111RsrMok());
        opLog.append("\n");
        opLog.append("[");
        opLog.append(naiyou);
        opLog.append("] ");
        opLog.append(paramMdl.getRsv111RsrBiko());
        return opLog.toString();
    }
}