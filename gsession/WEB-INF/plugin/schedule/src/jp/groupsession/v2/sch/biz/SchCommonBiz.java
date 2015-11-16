package jp.groupsession.v2.sch.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.lang.ClassUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.SchAppendSchData;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.LoggingBiz;
import jp.groupsession.v2.cmn.biz.RelationBetweenScdAndRsvChkBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.SchAppendDataModel;
import jp.groupsession.v2.cmn.model.SchAppendDataParam;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnLogModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.sch.dao.SchAdmConfDao;
import jp.groupsession.v2.sch.dao.SchDataDao;
import jp.groupsession.v2.sch.dao.SchExdataDao;
import jp.groupsession.v2.sch.dao.SchPriConfDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.SchLabelValueModel;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.sch.model.SchRepeatKbnModel;
import jp.groupsession.v2.sch.model.ScheduleExSearchModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.SmlSender;
import jp.groupsession.v2.sml.model.SmlSenderModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュールプラグインに関する共通ビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class SchCommonBiz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(SchCommonBiz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト */
    private RequestModel reqMdl__ = null;
    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public SchCommonBiz() {
    }
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    public SchCommonBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }


    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     */
    public SchCommonBiz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <p>マイグループ又はグループに所属するユーザ情報一覧を取得する。
     * @param con コネクション
     * @param gpSid グループSID
     * @param usrSids 除外するユーザSID
     * @param sessionUsrSid セッションユーザSID
     * @param myGroupFlg マイグループ選択フラグ
     * @return ArrayList 検索にヒットしたユーザデータ(CmnUsrmInfModel)
     * @throws SQLException SQL実行例外
     */
    public ArrayList<CmnUsrmInfModel> getBelongUserList(Connection con, int gpSid,
            ArrayList<Integer> usrSids, int sessionUsrSid, boolean myGroupFlg) throws SQLException {

        UserBiz userBiz = new UserBiz();
        return userBiz.getBelongUserList(con, gpSid, usrSids, sessionUsrSid, myGroupFlg);
    }

    /**
     * <br>[機  能] スケジュール個人設定SchPriConfModelを取得します。
     * <br>[解  説] DBに登録がない場合デフォルト値を返します。
     * <br>[備  考]
     * @param con DBコネクション
     * @param usrSid ユーザSID
     * @return スケジュール個人設定SchPriConfModel
     * @throws SQLException SQL実行エラー
     */
    public SchPriConfModel getSchPriConfModel(Connection con, int usrSid) throws SQLException {
        //
        SchPriConfDao dao = new SchPriConfDao(con);
        SchPriConfModel pconf = dao.select(usrSid);
        boolean commitFlg = false;
        if (pconf == null) {
            con.setAutoCommit(false);
            pconf = getDefaulPriConfModel(usrSid, con);
            try {
                dao.insert(pconf);
                commitFlg = true;
            } catch (SQLException e) {
                log__.error("個人設定の取得に失敗しました。" + e);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }
        }
        log__.debug("pconf = " + pconf.toCsvString());
        return pconf;
    }

    /**
     * <br>[機  能] 新規登録者用スケジュール個人設定SchPriConfModelを取得します。
     * <br>[解  説] DBに登録がない場合デフォルト値を返します。
     * <br>[備  考]
     * @param con DBコネクション
     * @param usrSid ユーザSID
     * @return スケジュール個人設定SchPriConfModel
     * @throws SQLException SQL実行エラー
     */
    public SchPriConfModel getNewSchPriConfModel(Connection con, int usrSid) throws SQLException {
        //
        SchPriConfDao dao = new SchPriConfDao(con);
        SchPriConfModel pconf = dao.select(usrSid);
        //ユーザSIDが0のSchPriConfModelを取得
        SchPriConfModel pribaseModel = getSchPriConfBaseModel(con);

        pconf = getDefaulPriConfModel(usrSid, con);
        //デフォルト表示グループを設定
        GroupBiz gpBiz = new GroupBiz();
        int gsid = gpBiz.getDefaultGroupSid(usrSid, con);
        pconf.setSccDspGroup(gsid);
        pconf.setSccSortKey1(pribaseModel.getSccSortKey1());
        pconf.setSccSortKey2(pribaseModel.getSccSortKey2());
        pconf.setSccSortOrder1(pribaseModel.getSccSortOrder1());
        pconf.setSccSortOrder2(pribaseModel.getSccSortOrder2());

        dao.insert(pconf);
        log__.debug("pconf = " + pconf.toCsvString());
        return pconf;
    }

    /**
     * <br>[機  能] SchPriConfテーブルにユーザSIDが０のデータを作成します。
     * <br>[解  説] 新規作成ユーザはこの
     * <br>[備  考]
     * @param con DBコネクション
     * @return スケジュール個人設定SchPriConfModel
     * @throws SQLException SQL実行エラー
     */

    public SchPriConfModel getSchPriConfBaseModel(Connection con) throws SQLException {
        //
        SchPriConfDao dao = new SchPriConfDao(con);
        SchPriConfModel pconf = new SchPriConfModel();
        SchPriConfModel pribaseModel = dao.select(0);

        if (pribaseModel == null) {
            boolean commitFlg = false;
            con.setAutoCommit(false);

            pconf = getDefaulPriConfModel(0, con);
            try {
                dao.insert(pconf);
                commitFlg = true;
            } catch (SQLException e) {
                log__.error("個人設定の取得に失敗しました。" + e);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }

            log__.debug("pconf = " + pconf.toCsvString());
        }
        return pconf;
    }

    /**
     * <br>[機  能] スケジュール個人設定のデフォルト値を返します。
     * <br>[解  説]
     * <br>[備  考] DBから個人設定情報が取得できない場合に使用してください。
     * @param usrSid ユーザSID
     * @param con DBコネクション
     * @return スケジュール個人設定のデフォルト値
     * @throws SQLException SQL実行エラー
     */
    public SchPriConfModel getDefaulPriConfModel(int usrSid, Connection con) throws SQLException {
        SchPriConfModel confBean = new SchPriConfModel();

        //ユーザSID
        confBean.setUsrSid(usrSid);
        //開始時間 9時で作成
        UDate frDate = new UDate();
        frDate.setHour(GSConstSchedule.DF_FROM_HOUR);
        frDate.setMinute(GSConstSchedule.DF_FROM_MINUTES);
        frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        confBean.setSccFrDate(frDate);
        //終了時間 18時で作成
        UDate toDate = new UDate();
        toDate.setHour(GSConstSchedule.DF_TO_HOUR);
        toDate.setMinute(GSConstSchedule.DF_TO_MINUTES);
        toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        toDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        confBean.setSccToDate(toDate);
        //デフォルト表示グループ
        GroupBiz gpBiz = new GroupBiz();
        int gsid = gpBiz.getDefaultGroupSid(usrSid, con);
        confBean.setSccDspGroup(gsid);
        //一覧表示件数
        confBean.setSccDspList(GSConstSchedule.DEFAULT_LIST_CNT);
        //自動リロード
        confBean.setSccReload(GSConstSchedule.AUTO_RELOAD_10MIN);
        //表示開始曜日
        confBean.setSccIniWeek(GSConstSchedule.CHANGE_WEEK_TODAY);

        confBean.setSccAuid(usrSid);
        confBean.setSccAdate(new UDate());
        confBean.setSccEuid(usrSid);
        confBean.setSccEdate(new UDate());

        //初期値 同時修正フラグ
        confBean.setSccIniSame(GSConstSchedule.SAME_EDIT_ON);
        //初期値 公開フラグ
        confBean.setSccIniPublic(GSConstSchedule.DSP_PUBLIC);
        //初期値 編集権限
        confBean.setSccIniEdit(GSConstSchedule.EDIT_CONF_NONE);
        //初期値 タイトルカラー
        confBean.setSccIniFcolor(GSConstSchedule.DF_BG_COLOR);
        //初期値 開始時刻 9時
        confBean.setSccIniFrDate(frDate);
        //初期値 終了時刻 18時
        confBean.setSccIniToDate(toDate);

        //ソート1
        confBean.setSccSortKey1(GSConstSchedule.SORT_KEY_YKSK);
        confBean.setSccSortOrder1(GSConst.ORDER_KEY_ASC);
        //ソート2
        confBean.setSccSortKey2(GSConstSchedule.SORT_KEY_NAME);
        confBean.setSccSortOrder2(GSConst.ORDER_KEY_ASC);
        //一覧表示件数
        confBean.setSccDspList(GSConstSchedule.DEFAULT_LIST_CNT);
        //マイグループ
        confBean.setSccDspMygroup(0);
        //メンバー表示順編集
        confBean.setSccSortEdit(GSConstSchedule.MEM_EDIT_NOT_EXECUTE);
        //初期表示画面
        confBean.setSccDefDsp(GSConstSchedule.DSP_WEEK);
        //グループスケジュール表示設定
        confBean.setSccGrpShowKbn(GSConstSchedule.GROUP_SCH_SHOW);
        //出欠確認時ショートメール通知
        confBean.setSccSmailAttend(GSConstSchedule.SMAIL_NOT_USE);

        return confBean;
    }

    /**
     * <br>[機  能] スケジュール管理者設定を取得し、取得できない場合はデフォルト値を返します。
     * <br>[解  説]
     * <br>[備  考] DBから個人設定情報が取得できない場合に使用してください。
     * @param con DBコネクション
     * @return スケジュール個人設定のデフォルト値
     * @throws SQLException SQL実行エラー
     */
    public SchAdmConfModel getAdmConfModel(Connection con) throws SQLException {
        //DBより現在の設定を取得する。
        SchAdmConfDao dao = new SchAdmConfDao(con);
        SchAdmConfModel conf = dao.select();
        if (conf == null) {
            //データがない場合
            conf = new SchAdmConfModel();
            //共有範囲
            conf.setSadCrange(GSConstSchedule.CRANGE_SHARE_ALL);
            //自動削除
            conf.setSadAtdelFlg(GSConstSchedule.AUTO_DELETE_OFF);
            conf.setSadAtdelY(-1);
            conf.setSadAtdelM(-1);
            //時間間隔
            conf.setSadHourDiv(GSConstSchedule.DF_HOUR_DIVISION);

            //登録者・更新者
            UDate now = new UDate();
            conf.setSadAuid(0);
            conf.setSadAdate(now);
            conf.setSadEuid(0);
            conf.setSadEdate(now);

            //メンバ表示順
            conf.setSadSortKbn(GSConstSchedule.MEM_DSP_USR);
            conf.setSadSortKey1(GSConstSchedule.SORT_KEY_YKSK);
            conf.setSadSortOrder1(GSConstSchedule.ORDER_KEY_ASC);
            conf.setSadSortKey2(GSConstSchedule.SORT_KEY_NAME);
            conf.setSadSortOrder2(GSConstSchedule.ORDER_KEY_ASC);

            //初期値 編集権限設定
            conf.setSadIniEditStype(GSConstSchedule.SAD_INIEDIT_STYPE_USER);
            conf.setSadIniEdit(GSConstSchedule.EDIT_CONF_NONE);

            //初期値 公開区分
            conf.setSadInitPublicStype(GSConstSchedule.SAD_INIPUBLIC_STYPE_USER);
            conf.setSadIniPublic(GSConstSchedule.DSP_PUBLIC);

            //初期値 同時修正設定
            conf.setSadIniSameStype(GSConstSchedule.SAD_INISAME_STYPE_USER);
            conf.setSadIniSame(GSConstSchedule.SAME_EDIT_ON);

            //重複登録区分
            conf.setSadRepeatStype(GSConstSchedule.SAD_REPEAT_STYPE_USER);
            conf.setSadRepeatKbn(GSConstSchedule.SCH_REPEAT_KBN_OK);
            conf.setSadRepeatMyKbn(GSConstSchedule.SCH_REPEAT_MY_KBN_NG);

            //デフォルト表示グループ
            conf.setSadDspGroup(GSConstSchedule.SAD_DSP_GROUP_USER);

            //タイトル色区分
            conf.setSadMsgColorKbn(GSConstSchedule.SAD_MSG_NO_ADD);

            //ショートメール通知設定
            conf.setSadSmailSendKbn(GSConstSchedule.SMAIL_SEND_KBN_USER);
            conf.setSadSmail(GSConstSchedule.SMAIL_NOT_USE);
            conf.setSadSmailGroup(GSConstSchedule.SMAIL_NOT_USE);
            conf.setSadSmailAttend(GSConstSchedule.SMAIL_NOT_USE);
        }

        log__.debug(conf.toCsvString());
        return conf;
    }

    /**
     * スケジュールで扱う時間間隔を取得する。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return int 時間間隔
     * @throws SQLException SQL実行時例外
     */
    public int getHourDivision(Connection con) throws SQLException {
        int ret = GSConstSchedule.DF_HOUR_DIVISION;
        SchAdmConfModel conf = getAdmConfModel(con);
        ret = conf.getSadHourDiv();
        return ret;
    }

    /**
     * スケジュールで扱う時間間隔から１時間を何分割するかを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return int 時間の何分割数
     * @throws SQLException SQL実行時例外
     */
    public int getDayScheduleHourMemoriCount(Connection con)
    throws SQLException {
        int ret = GSConstSchedule.HOUR_DIVISION_COUNT_10;
        int hourDiv = getHourDivision(con);
        switch (hourDiv) {
        case 5:
            ret = GSConstSchedule.HOUR_DIVISION_COUNT_5;
            break;
        case 10:
            ret = GSConstSchedule.HOUR_DIVISION_COUNT_10;
            break;
        case 15:
            ret = GSConstSchedule.HOUR_DIVISION_COUNT_15;
            break;
        default:
            break;
        }
        return ret;
    }

    /**
     * 日間画面での1目盛の分を取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return int 日間画面での1目盛の分
     * @throws SQLException SQL実行時例外
     */
    public int getDayScheduleHourMemoriMin(Connection con)
    throws SQLException {
        int divMin = getDayScheduleHourMemoriCount(con);
        return 60 / divMin;
    }

    /**
     * <br>[機  能] 基準日を指定し、基準日より古いスケジュールデータを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con DBコネクション
     * @param bdate 基準日
     * @throws SQLException SQL実行エラー
     */
    public void deleteOldSchedule(Connection con, UDate bdate) throws SQLException {

        SchDataDao dao = new SchDataDao(con);

        //削除するスケジュールSIDを取得する。
        List<Integer> allDelList = dao.getScdSidList(bdate);

        if (allDelList == null || allDelList.size() < 1) {
            return;
        }


        //スケジュールを何度かに分けて削除する。
        int i = 0;
        int delCount = GSConstSchedule.SCH_BATCH_DELETE_COUNT;
        ArrayList<Integer> delList = new ArrayList<Integer>();
        for (Integer scdSid : allDelList) {

            delList.add(scdSid);
            i++;

            if (i >= delCount) {
                //削除する。
                dao.delete(delList);
                delList = new ArrayList<Integer>();
                i = 0;
            }
        }

        if (delList != null && delList.size() > 0) {
            //削除する。
            dao.delete(delList);
        }
    }

    /**
     * <br>[機  能] スケジュール情報が存在しないスケジュール拡張情報を削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con DBコネクション
     * @throws SQLException SQL実行エラー
     */
    public void deleteSchNoData(Connection con) throws SQLException {

        SchExdataDao dao = new SchExdataDao(con);

        //削除するスケジュール拡張SIDを取得する。
        List<Integer> allDelList = dao.selectSchNoData();

        if (allDelList == null || allDelList.size() < 1) {
            return;
        }

        //スケジュール拡張情報を何度かに分けて削除する。
        int i = 0;
        int delCount = GSConstSchedule.SCH_BATCH_DELETE_COUNT;
        ArrayList<Integer> delList = new ArrayList<Integer>();
        for (Integer scdSid : allDelList) {

            delList.add(scdSid);

            if (i >= delCount) {
                //削除する。
                dao.delete(delList);
                delList = new ArrayList<Integer>();
                i = 0;
            }

        }

        if (delList != null && delList.size() > 0) {
            //削除する。
            dao.delete(delList);
        }
    }

    /**
     * <br>[機  能] スケジュールのデフォルト表示で表示するグループSIDを取得する。
     * <br>[解  説] DBに登録された個人設定情報を取得しその表示グループを返す。
     * <br>グループが削除されていた場合は、デフォルトグループを返す。
     * <br>[備  考]
     * @param con DBコネクション
     * @param usrSid ユーザSID
     * @return グループSID
     * @throws SQLException SQL実行エラー
     */
    public int getDispDefaultGroupSid(Connection con, int usrSid) throws SQLException {
        //
        SchAdmConfModel aconf = getAdmConfModel(con);
        SchPriConfModel pconf = getSchPriConfModel(con, usrSid);

        if (pconf == null) {
            pconf = getDefaulPriConfModel(usrSid, con);
        }
        int gsid = 0;
        if (aconf.getSadCrange() == GSConstSchedule.CRANGE_SHARE_GROUP) {
            gsid = pconf.getSccDspGroup();
        } else {
            if (pconf.getSccDspMygroup() != 0) {
                gsid = pconf.getSccDspMygroup();
                //マイグループ存在チェック
                CmnMyGroupDao cmgDao = new CmnMyGroupDao(con);
                if (cmgDao.getMyGroupList(usrSid,
                        String.valueOf(gsid)).size() < 1) {
                    //マイグループが存在しない場合はデフォルトグループを返す
                    GroupBiz gbiz = new GroupBiz();
                    gsid = gbiz.getDefaultGroupSid(usrSid, con);
                }
            } else {
                gsid = pconf.getSccDspGroup();
                //グループ存在チェック
                GroupDao gdao = new GroupDao(con);
                CmnGroupmModel group = gdao.getGroup(gsid);
                if (group == null) {
                    //個人設定未設定 or 不正データの場合、ユーザマネージャのデフォルトグループ
                    GroupBiz gbiz = new GroupBiz();
                    gsid = gbiz.getDefaultGroupSid(usrSid, con);
                } else {
                    if (GSConst.JTKBN_DELETE == group.getGrpJkbn()) {
                        //状態区分が削除の場合はデフォルトグループを返す
                        GroupBiz gbiz = new GroupBiz();
                        gsid = gbiz.getDefaultGroupSid(usrSid, con);
                    }
                }
            }
        }
        log__.debug("デフォルト表示グループID=>" + gsid);
        return gsid;
    }
    /**
     * <br>[機  能] スケジュールのデフォルト表示で表示するグループSIDを取得する。
     * <br>[解  説] DBに登録された個人設定情報を取得しその表示グループを返す。
     * <br>グループが削除されていた場合は、デフォルトグループを返す。
     * <br>[備  考]
     * @param con DBコネクション
     * @param usrSid ユーザSID
     * @return グループSID
     * @throws SQLException SQL実行エラー
     */
    public String getDispDefaultGroupSidStr(Connection con, int usrSid) throws SQLException {
        GroupBiz gbiz = new GroupBiz();

        //管理者設定 デフォルト表示グループ = 1:デフォルトグループに強制 の場合、ユーザのデフォルトグループを返す
        SchAdmConfModel aconf = getAdmConfModel(con);
        if (aconf.getSadDspGroup() == GSConstSchedule.SAD_DSP_GROUP_DEFGROUP) {
            return String.valueOf(gbiz.getDefaultGroupSid(usrSid, con));
        }

        SchPriConfModel pconf = getSchPriConfModel(con, usrSid);
        if (pconf == null) {
            pconf = getDefaulPriConfModel(usrSid, con);
        }

        String gsid = null;
        if (aconf.getSadCrange() == GSConstSchedule.CRANGE_SHARE_GROUP) {
            gsid = String.valueOf(pconf.getSccDspGroup());
            //個人設定値が閲覧可能なグループで無い場合はデフォルトグループを表示
            if (!isDspOkGroup(pconf.getSccDspGroup(), usrSid, con)) {
                gsid = String.valueOf(gbiz.getDefaultGroupSid(usrSid, con));
            }
        } else {
            if (pconf.getSccDspMygroup() != 0) {
                gsid = String.valueOf(pconf.getSccDspMygroup());
                //マイグループ存在チェック
                CmnMyGroupDao cmgDao = new CmnMyGroupDao(con);
                if (cmgDao.getMyGroupList(usrSid,
                        String.valueOf(gsid)).size() < 1) {
                    //マイグループが存在しない場合はデフォルトグループを返す
                    gsid = String.valueOf(gbiz.getDefaultGroupSid(usrSid, con));
                } else {
                    gsid = GSConstSchedule.MY_GROUP_STRING + gsid;
                }
            } else {
                //表示グループ = デフォルトグループ
                if (pconf.getSccDspGroup() == GSConstSchedule.SCC_DSP_GROUP_DEF) {
                    gsid = String.valueOf(gbiz.getDefaultGroupSid(usrSid, con));
                } else {
                    gsid = String.valueOf(pconf.getSccDspGroup());
                    //グループ存在チェック
                    GroupDao gdao = new GroupDao(con);
                    CmnGroupmModel group = gdao.getGroup(Integer.parseInt(gsid));
                    if (group == null) {
                        //個人設定未設定 or 不正データの場合、ユーザマネージャのデフォルトグループ
                        gsid = String.valueOf(gbiz.getDefaultGroupSid(usrSid, con));
                    } else {
                        if (GSConst.JTKBN_DELETE == group.getGrpJkbn()) {
                            //状態区分が削除の場合はデフォルトグループを返す
                            gsid = String.valueOf(gbiz.getDefaultGroupSid(usrSid, con));
                        }
                    }
                }
            }
        }

        return gsid;
    }
    /**
     * フォーム情報のグループコンボ値からグループSID又はマイグループSIDを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return int グループSID又はマイグループSID
     */
    public static int getDspGroupSid(String gpSid) {
        int ret = 0;
        if (StringUtil.isNullZeroString(gpSid)) {
            return ret;
        }

        if (isMyGroupSid(gpSid)) {
            return Integer.parseInt(gpSid.substring(1));
        } else {
            return Integer.parseInt(gpSid);
        }
    }
    /**
     * ユーザが閲覧可能なグループか判定する。
     * 所属グループ、グループ外ユーザとして設定されているグループは閲覧可能
     * @param gpSid グループSID
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return boolean true=可能 false=不可
     * @throws SQLException SQL実行時例外
     */
    public boolean isDspOkGroup(int gpSid, int usrSid, Connection con) throws SQLException {

        //管理者設定
        SchAdmConfModel admConf = getAdmConfModel(con);
        //閲覧可能なグループリスト取得
        SchGroupBiz schGrpBiz = new SchGroupBiz();
        List<Integer> accessGrpList
            = schGrpBiz.getAccessGrpList(con, usrSid, admConf.getSadCrange());

        return accessGrpList.indexOf(gpSid) >= 0;
    }

    /**
     * フォーム情報のグループコンボ値がグループSIDかマイグループSIDかを判定する
     * <br>[機  能]先頭文字に"M"が有る場合、マイグループSID
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return boolean true:マイグループ false=通常のグループ
     */
    public static boolean isMyGroupSid(String gpSid) {
        boolean ret = false;
        if (gpSid == null) {
            return ret;
        }
        // 置換対象文字列が存在する場所を取得
        int index = gpSid.indexOf(GSConstSchedule.MY_GROUP_STRING);

        // 先頭文字に"M"が有る場合はマイグループ
        if (index == 0) {
            return true;
        } else {
            return ret;
        }
    }

    /**
     * <br>[機  能] スケジュール拡張登録画面の初期値を取得
     * <br>[解  説] 画面表示用に使用します
     * <br>[備  考] 拡張SIDと登録・更新者、時間は設定されません。
     * @param usrSid ユーザSID
     * @param date 登録・編集日付
     * @param con DBコネクション
     * @param userKbn 0:個人 1:グループスケジュール
     * @return ScheduleExSearchModel 拡張情報Bean
     * @throws SQLException SQL実行エラー
     */
    public ScheduleExSearchModel getDispDefaultExtend(
            int usrSid, UDate date, Connection con, int userKbn)
    throws SQLException {
        //個人設定を取得
        SchPriConfModel priConf = getSchPriConfModel(con, usrSid);
        ScheduleExSearchModel extMdl = new ScheduleExSearchModel();

        extMdl.setSceKbn(GSConstSchedule.EXTEND_KBN_DAY);
        extMdl.setSceTranKbn(GSConstSchedule.FURIKAE_KBN_NONE);
        extMdl.setSceWeek(GSConstSchedule.SETTING_NONE);
        extMdl.setSceDay(GSConstSchedule.SETTING_NONE);
        extMdl.setSceDayOfYearly(date.getIntDay());
        extMdl.setSceMonthOfYearly(date.getMonth());
        extMdl.setSceDweek1(GSConstSchedule.SETTING_NONE);
        extMdl.setSceDweek2(GSConstSchedule.SETTING_NONE);
        extMdl.setSceDweek3(GSConstSchedule.SETTING_NONE);
        extMdl.setSceDweek4(GSConstSchedule.SETTING_NONE);
        extMdl.setSceDweek5(GSConstSchedule.SETTING_NONE);
        extMdl.setSceDweek6(GSConstSchedule.SETTING_NONE);
        extMdl.setSceDweek7(GSConstSchedule.SETTING_NONE);
        extMdl.setSceDateFr(date);
        extMdl.setSceDateTo(date);
        extMdl.setSceTimeFr(priConf.getSccIniFrDate());
        extMdl.setSceTimeTo(priConf.getSccIniToDate());
        extMdl.setSceTitle("");
        extMdl.setSceBgcolor(priConf.getSccIniFcolor());
        extMdl.setSceValue("");
        extMdl.setSceBiko("");

        int initPub = getInitPubAuth(con, priConf);
        if (userKbn == GSConstSchedule.USER_KBN_GROUP
            && initPub != GSConstSchedule.DSP_PUBLIC
            && initPub != GSConstSchedule.DSP_NOT_PUBLIC) {
            initPub = GSConstSchedule.DSP_PUBLIC;
        }
        extMdl.setScePublic(initPub);
        extMdl.setSceEdit(getInitEditAuth(con, priConf));

        return extMdl;
    }

    /**
     * 表示グループ用のグループリストを取得する
     * @param usrSid ユーザSID
     * @param con コネクション
     * @param sentakuFlg true:「非表示」のラベルを作成する, false:作成しない
     * @return グループラベルのArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<SchLabelValueModel> getGroupLabelForSchedule(int usrSid,
            Connection con, boolean sentakuFlg) throws SQLException {
        return getGroupLabelForSchedule(usrSid, con, sentakuFlg,
                GSConstSchedule.SSP_AUTHFILTER_VIEW);
    }
    /**
     * スケジュールグループ選択用のグループリストを取得する
     * @param usrSid ユーザSID
     * @param con コネクション
     * @param sentakuFlg true:「非表示」のラベルを作成する, false:作成しない
     * @param authFilter 0:すべて 1:閲覧用 2:編集用
     * @return グループラベルのArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<SchLabelValueModel> getGroupLabelForSchedule(int usrSid,
            Connection con, boolean sentakuFlg, int authFilter) throws SQLException {
        //管理者設定
        SchAdmConfModel admConf = getAdmConfModel(con);

        ArrayList<SchLabelValueModel> labelList = new ArrayList<SchLabelValueModel>();

        //グループリスト取得
        SchGroupBiz schGrpBiz = new SchGroupBiz();
        List<GroupModel> gpList = null;
        if (admConf.getSadCrange() == GSConstSchedule.CRANGE_SHARE_ALL) {
            log__.debug("全員で共有するグループリストを取得");
            //全員で共有
            gpList = schGrpBiz.getGroupCombList(con, -1);
        } else {
            //所属グループのみで共有
            log__.debug("所属グループのみで共有するグループリストを取得");
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();

            GroupDao dao = new GroupDao(con);
            ArrayList<GroupModel> gpBaseList = dao.getGroupList(sortMdl);


            SchDao schDao = new SchDao(con);
//          //ユーザが所属するグループを取得
            CmnBelongmDao belongDao = new CmnBelongmDao(con);
            List<Integer> belongGroupMdlList = belongDao.selectUserBelongGroupSid(usrSid);
            List<Integer> belongGrpList = new ArrayList<Integer>();
            List<Integer> accessGrpList = new ArrayList<Integer>();
            if (authFilter == GSConstSchedule.SSP_AUTHFILTER_EDIT) {
                //編集可能ユーザが所属するグループを取得
                belongGrpList
                  = schDao.getGroupBelongSpRegistUser(usrSid);
                accessGrpList = schDao.getAccessGrpList(usrSid, GSConstSchedule.SSP_AUTH_EDIT);
            }
            if (authFilter == GSConstSchedule.SSP_AUTHFILTER_VIEW) {
                //閲覧可能ユーザが所属するグループを取得
                belongGrpList
                  = schDao.getGroupBelongSpAccessUser(usrSid);
                accessGrpList = schDao.getAccessGrpList(usrSid);
            }

            gpList = new ArrayList<GroupModel>();
            for (GroupModel gpMdl : gpBaseList) {
                int grpSid = gpMdl.getGroupSid();
                if (belongGroupMdlList.contains(grpSid)
                || accessGrpList.contains(grpSid)
                || belongGrpList.contains(grpSid)) {
                    gpList.add(gpMdl);
                }
            }

        }
        SchLabelValueModel label = null;
        int grpSid = -1;
        for (GroupModel gmodel : gpList) {
            grpSid = gmodel.getGroupSid();
            label = new SchLabelValueModel(gmodel.getGroupName(),
                    String.valueOf(grpSid), "0");
            label.setViewKbn(true);

            labelList.add(label);
        }

        //共有範囲設定が「全て共有」の場合マイグループを追加
        if (admConf.getSadCrange() == GSConstSchedule.CRANGE_SHARE_ALL) {
            List<SchLabelValueModel> mylabelList = getMyGroupLabel(usrSid, con);
            labelList.addAll(0, mylabelList);
        }
        //グループ
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textHide = gsMsg.getMessage("cmn.hide");
        if (sentakuFlg) {
            labelList.add(new SchLabelValueModel(textHide, String.valueOf(-1), "0"));
        }

        return labelList;
    }
    /**
     * 表示グループ用のグループリストを取得する
     * @param usrSid ユーザSID
     * @param con コネクション
     * @param sentakuFlg true:「非表示」のラベルを作成する, false:作成しない
     * @return グループラベルのArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<SchLabelValueModel> getGroupLabelCombo(int usrSid,
            Connection con, boolean sentakuFlg) throws SQLException {
        ArrayList<SchLabelValueModel> labelList = new ArrayList<SchLabelValueModel>();

        //グループリスト取得
        SchGroupBiz schGrpBiz = new SchGroupBiz();
        List<GroupModel> gpList = schGrpBiz.getGroupCombList(con, -1);

        SchLabelValueModel label = null;
        int grpSid = -1;
        for (GroupModel gmodel : gpList) {
            grpSid = gmodel.getGroupSid();
            label = new SchLabelValueModel(gmodel.getGroupName(),
                    String.valueOf(grpSid), "0");
            label.setViewKbn(true);

            labelList.add(label);
        }

        //マイグループを追加
        List<SchLabelValueModel> mylabelList = getMyGroupLabel(usrSid, con);
        labelList.addAll(0, mylabelList);

        //グループ
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textHide = gsMsg.getMessage("cmn.hide");
        if (sentakuFlg) {
            labelList.add(new SchLabelValueModel(textHide, String.valueOf(-1), "0"));
        }

        return labelList;
    }

    /**
     * 表示グループ用のグループリストを取得する(所属グループのみ)
     * 所属メンバーのいないマイグループは取得しない。
     * @param usrSid ユーザSID
     * @param con コネクション
     * @param sentakuFlg true:「非表示」のラベルを作成する, false:作成しない
     * @return グループラベルのArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<SchLabelValueModel> getGroupLabelForScheduleMygrpExist(int usrSid,
            Connection con, boolean sentakuFlg) throws SQLException {
        //管理者設定
        SchAdmConfModel admConf = getAdmConfModel(con);

        ArrayList<SchLabelValueModel> labelList = new ArrayList<SchLabelValueModel>();

        //グループリスト取得
        ArrayList<GroupModel> gpList = null;
        if (admConf.getSadCrange() == GSConstSchedule.CRANGE_SHARE_ALL) {
            //全員で共有
//            GroupDao dao = new GroupDao(con);
//            gpList = dao.getGroupTree();
            GroupBiz groupBiz = new GroupBiz();
            gpList = groupBiz.getGroupCombList(con);
        } else {
            //所属グループのみで共有
            UsidSelectGrpNameDao gpDao = new UsidSelectGrpNameDao(con);
            gpList = gpDao.selectGroupNmListOrderbyClass(usrSid);
        }

//        CmnGroupmModel gpMdl = null;
        for (GroupModel gmodel : gpList) {
            labelList.add(new SchLabelValueModel(gmodel.getGroupName(), String
                    .valueOf(gmodel.getGroupSid()), "0"));
        }

        //共有範囲設定が「全て共有」の場合マイグループを追加
        if (admConf.getSadCrange() == GSConstSchedule.CRANGE_SHARE_ALL) {
            List<SchLabelValueModel> mylabelList = getMyGroupLabelExistMember(usrSid, con);
            labelList.addAll(0, mylabelList);
        }
        //グループ
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textHide = gsMsg.getMessage("cmn.hide");
        if (sentakuFlg) {
            labelList.add(new SchLabelValueModel(textHide, String.valueOf(-1), "0"));
        }

        return labelList;
    }

    /**
     * ユーザIDを指定しマイグループラベルを生成します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param con コネクション
     * @return List
     * @throws SQLException SQL実行時例外
     */
    public List<SchLabelValueModel> getMyGroupLabel(int userSid, Connection con)
    throws SQLException {
        //ユーザSIDからマイグループ情報を取得する
        CmnMyGroupDao cmgDao = new CmnMyGroupDao(con);
        List<CmnMyGroupModel> cmgList = cmgDao.getMyGroupList(userSid);

        //マイグループリストをセット
        List<SchLabelValueModel> cmgLabelList = new ArrayList<SchLabelValueModel>();
        for (CmnMyGroupModel cmgMdl : cmgList) {

            cmgLabelList.add(
                    new SchLabelValueModel(
                            cmgMdl.getMgpName(),
                            GSConstSchedule.MY_GROUP_STRING
                            + String.valueOf(cmgMdl.getMgpSid()), "1")
                            );
        }
        return cmgLabelList;
    }

    /**
     * ユーザIDを指定しマイグループラベルを生成します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param con コネクション
     * @return List
     * @throws SQLException SQL実行時例外
     */
    public List<SchLabelValueModel> getMyGroupLabelExistMember(int userSid, Connection con)
    throws SQLException {
        //ユーザSIDからマイグループ情報を取得する
        CmnMyGroupDao cmgDao = new CmnMyGroupDao(con);
        List<CmnMyGroupModel> cmgList = cmgDao.getMyGroupExistMemberList(userSid);

        //マイグループリストをセット
        List<SchLabelValueModel> cmgLabelList = new ArrayList<SchLabelValueModel>();
        for (CmnMyGroupModel cmgMdl : cmgList) {

            cmgLabelList.add(
                    new SchLabelValueModel(
                            cmgMdl.getMgpName(),
                            GSConstSchedule.MY_GROUP_STRING
                            + String.valueOf(cmgMdl.getMgpSid()), "1")
                            );
        }
        return cmgLabelList;
    }

    /**
     * <br>[機  能] モバイルショートメールでスケジュール登録通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param schMdl スケジュール内容(ショートメール送信用)
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void sendSmail(
        Connection con,
        MlCountMtController cntCon,
        SchDataModel schMdl,
        String appRootPath,
        PluginConfig pluginConfig,
        boolean smailPluginUseFlg,
        RequestModel reqMdl
        ) throws Exception {
        GsMessage gsMsg = new GsMessage(reqMdl);
        if (!smailPluginUseFlg) {
            //ショートメールプラグインが無効の場合、ショートメールを送信しない。
            return;
        }
        CmnUsrmInfDao udao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel umodel = null;
        String userName = "";
        // 被登録者
        int userSid = schMdl.getScdUsrSid();
        // 登録者
        int addUserSid = schMdl.getScdEuid();
        SchPriConfModel priConf = null;
        SchAdmConfModel admConf = getAdmConfModel(con);

        //送信するユーザSIDリストを作成
        List<Integer> sidList = new ArrayList<Integer>();
        if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
            //グループスケジュールの場合
            //所属しているユーザ一覧を取得
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> belongList
                = userBiz.getBelongUserList(con, userSid, null);

            for (CmnUsrmInfModel usMdl : belongList) {
                priConf = getSchPriConfModel(con, usMdl.getUsrSid());
                if (getSmlSendKbn(admConf, priConf, 0)) {
                    sidList.add(new Integer(usMdl.getUsrSid()));
                }
            }
            GroupDao gpDao = new GroupDao(con);
            CmnGroupmModel gpMdl = gpDao.getGroup(userSid);
            userName = gpMdl.getGrpName();
        } else {
            //ユーザスケジュールの場合
            priConf = getSchPriConfModel(con, userSid);
            //本人以外が登録したスケジュール且つ、ショートメール通知する場合
            if (getSmlSendKbn(admConf, priConf, 1)
                    && schMdl.getScdUsrSid() != schMdl.getScdEuid()) {
                sidList.add(new Integer(userSid));
                umodel = udao.select(userSid);
                userName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            }
        }
        //送信先がない場合は終了
        if (sidList.size() < 1) {
            return;
        }

        priConf.getSccSmailGroup();
        try {

            //登録ユーザ名
            umodel = udao.select(addUserSid);
            String addUserName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            //タイトル
            String thtitle = schMdl.getScdTitle();
            //内容
            String body = schMdl.getScdValue();
            //備考
            String biko = schMdl.getScdBiko();
            //開始日時
            UDate frDate = schMdl.getScdFrDate();
            String fdate = UDateUtil.getSlashYYMD(frDate) + " "
            + UDateUtil.getSeparateHMS(frDate);
            //終了日時
            UDate toDate = schMdl.getScdToDate();
            String tdate = UDateUtil.getSlashYYMD(toDate) + " "
            + UDateUtil.getSeparateHMS(toDate);
            //登録日時
            UDate aDate = schMdl.getScdEdate();
            String adate = UDateUtil.getSlashYYMD(aDate) + " "
            + UDateUtil.getSeparateHMS(aDate);
            //本文
            String tmpPath = getSmlTemplateFilePath(appRootPath); //テンプレートファイルパス取得
            String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
            Map<String, String> map = new HashMap<String, String>();
            map.put("ADATE", adate);
            map.put("UNAME", addUserName);
            map.put("NAME", userName);
            map.put("TITLE", thtitle);
            map.put("FRDATE", fdate);
            map.put("TODATE", tdate);
            map.put("BODY", body);
            map.put("BIKO", biko);

            String bodyml = StringUtil.merge(tmpBody, map);
            String omit = gsMsg.getMessage("cmn.mail.omit");

            if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {
                bodyml = omit + "\r\n\r\n" + bodyml;
                bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);
            }

            //ショートメール送信用モデルを作成する。
            SmlSenderModel smlModel = new SmlSenderModel();
            //送信者(システムメールを指定)
            smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
            //TO
            smlModel.setSendToUsrSidArray(sidList);
            //タイトル
            //スケジュール
            String textSchedule = gsMsg.getMessage("schedule.108");
            //登録通知
            String textAdd = gsMsg.getMessage("schedule.130");
            String title = "[GS " + textSchedule + "] " +  textAdd  + " " + thtitle;
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
            SmlSender sender = new SmlSender(
                    con, cntCon, smlModel, pluginConfig, appRootPath, reqMdl);
            sender.execute();
        } catch (Exception e) {
            e.printStackTrace();
            log__.error("ショートメール送信に失敗しました。");
            throw e;
        }
    }

    /**
     * <br>[機  能] ショートメールプラグインでスケジュール登録通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param schMdl スケジュール内容(ショートメール送信用)
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param url スケジュールURL
     * @throws Exception 実行例外
     */
    public void sendPlgSmail(
        Connection con,
        MlCountMtController cntCon,
        SchDataModel schMdl,
        String appRootPath,
        PluginConfig pluginConfig,
        boolean smailPluginUseFlg,
        String url) throws Exception {
        GsMessage gsMsg = new GsMessage(reqMdl__);

        if (!smailPluginUseFlg) {
            //ショートメールプラグインが無効の場合、ショートメールを送信しない。
            return;
        }
        CmnUsrmInfDao udao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel umodel = null;
        String userName = "";
        // 被登録者
        int userSid = schMdl.getScdUsrSid();
        // 登録者
        int addUserSid = schMdl.getScdEuid();

        SchAdmConfModel admConf = getAdmConfModel(con);
        SchPriConfModel priConf = null;

        //送信するユーザSIDリストを作成
        List<Integer> sidList = new ArrayList<Integer>();
        if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
            //グループスケジュールの場合
            //所属しているユーザ一覧を取得
//            UserSearchDao usDao = new UserSearchDao(con);
//            ArrayList<CmnUsrmInfModel> belongList = usDao.getBelongUserList(userSid, null);
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> belongList
                = userBiz.getBelongUserList(con, userSid, null);
            for (CmnUsrmInfModel usMdl : belongList) {
                priConf = getSchPriConfModel(con, usMdl.getUsrSid());
                if (getSmlSendKbn(admConf, priConf, 0)) {
                    if (usMdl.getUsrSid() != reqMdl__.getSmodel().getUsrsid()) {
                        //登録者以外に通知
                        sidList.add(new Integer(usMdl.getUsrSid()));
                    }
                }
            }
            GroupDao gpDao = new GroupDao(con);
            CmnGroupmModel gpMdl = gpDao.getGroup(userSid);
            userName = gpMdl.getGrpName();
        } else {
            //ユーザスケジュールの場合
            priConf = getSchPriConfModel(con, userSid);
            //本人以外が登録したスケジュール且つ、ショートメール通知する場合
            if (getSmlSendKbn(admConf, priConf, 1)
                    && schMdl.getScdUsrSid() != schMdl.getScdEuid()) {
                sidList.add(new Integer(userSid));
                umodel = udao.select(userSid);
                userName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            }
        }
        //送信先がない場合は終了
        if (sidList.size() < 1) {
            return;
        }

        priConf.getSccSmailGroup();
        try {

            //登録ユーザ名
            umodel = udao.select(addUserSid);
            String addUserName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            //タイトル
            String thtitle = schMdl.getScdTitle();
            //内容
            String body = schMdl.getScdValue();
            //備考
            String biko = schMdl.getScdBiko();
            //開始日時
            UDate frDate = schMdl.getScdFrDate();
            String fdate = UDateUtil.getSlashYYMD(frDate) + " "
            + UDateUtil.getSeparateHMS(frDate);
            //終了日時
            UDate toDate = schMdl.getScdToDate();
            String tdate = UDateUtil.getSlashYYMD(toDate) + " "
            + UDateUtil.getSeparateHMS(toDate);
            //登録日時
            UDate aDate = schMdl.getScdEdate();
            String adate = UDateUtil.getSlashYYMD(aDate) + " "
            + UDateUtil.getSeparateHMS(aDate);
            //本文
            String tmpPath = getSmlTemplateFilePathPlg(appRootPath); //テンプレートファイルパス取得
            String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
            Map<String, String> map = new HashMap<String, String>();
            map.put("ADATE", adate);
            map.put("UNAME", addUserName);
            map.put("NAME", userName);
            map.put("TITLE", thtitle);
            map.put("FRDATE", fdate);
            map.put("TODATE", tdate);
            map.put("BODY", body);
            map.put("BIKO", biko);
            map.put("URL", url);

            String bodyml = StringUtil.merge(tmpBody, map);
            String omit = gsMsg.getMessage("cmn.mail.omit");

            if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {
                bodyml = omit + "\r\n\r\n" + bodyml;
                bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);
            }

            //ショートメール送信用モデルを作成する。
            SmlSenderModel smlModel = new SmlSenderModel();
            //送信者(システムメールを指定)
            smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
            //TO
            smlModel.setSendToUsrSidArray(sidList);
            //タイトル
            //スケジュール
            String textSchedule = gsMsg.getMessage("schedule.108");
            //登録通知
            String textAdd = gsMsg.getMessage("schedule.130");
            String title = "[GS " + textSchedule + "] " + textAdd + " " + thtitle;
            title = StringUtil.trimRengeString(title,
                    GSConstCommon.MAX_LENGTH_SMLTITLE);
            smlModel.setSendTitle(title);

            //本文
            smlModel.setSendBody(bodyml);
            //マーク
            smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);

            //メール送信処理開始
            SmlSender sender = new SmlSender(
                    con, cntCon, smlModel, pluginConfig, appRootPath, reqMdl__);
            sender.execute();
        } catch (Exception e) {
            e.printStackTrace();
            log__.error("ショートメール送信に失敗しました。");
            throw e;
        }
    }

    /**
     * <br>[機  能] ショートメールプラグインでスケジュール 出欠確認 回答依頼通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param schMdl スケジュール内容(ショートメール送信用)
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param url スケジュールURL
     * @param sendKbn 通知区分 0:回答依頼通知  1:再通知
     * @throws Exception 実行例外
     */
    public void sendAttendSmail(
        Connection con,
        MlCountMtController cntCon,
        SchDataModel schMdl,
        String appRootPath,
        PluginConfig pluginConfig,
        boolean smailPluginUseFlg,
        String url,
        int sendKbn) throws Exception {
        GsMessage gsMsg = new GsMessage(reqMdl__);

        if (!smailPluginUseFlg) {
            //ショートメールプラグインが無効の場合、ショートメールを送信しない。
            return;
        }
        CmnUsrmInfDao udao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel umodel = null;
        String userName = "";
        // 被登録者
        int userSid = schMdl.getScdUsrSid();
        // 登録者
        int addUserSid = schMdl.getScdEuid();

        SchAdmConfModel admConf = getAdmConfModel(con);
        SchPriConfModel priConf = null;

        //送信するユーザSIDリストを作成
        List<Integer> sidList = new ArrayList<Integer>();

        //ユーザスケジュールの場合
        priConf = getSchPriConfModel(con, userSid);
        //出欠確認メール通知する場合
        if (getAttendSmlSendKbn(admConf, priConf)) {
            sidList.add(new Integer(userSid));
            umodel = udao.select(userSid);
            userName = umodel.getUsiSei() + " " + umodel.getUsiMei();
        }

        //送信先がない場合は終了
        if (sidList.size() < 1) {
            return;
        }

        try {

            //登録ユーザ名
            umodel = udao.select(addUserSid);
            String addUserName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            //タイトル
            String thtitle = schMdl.getScdTitle();
            //内容
            String body = schMdl.getScdValue();
            //備考
            String biko = schMdl.getScdBiko();
            //開始日時
            UDate frDate = schMdl.getScdFrDate();
            String fdate = UDateUtil.getSlashYYMD(frDate) + " "
            + UDateUtil.getSeparateHMS(frDate);
            //終了日時
            UDate toDate = schMdl.getScdToDate();
            String tdate = UDateUtil.getSlashYYMD(toDate) + " "
            + UDateUtil.getSeparateHMS(toDate);
            //登録日時
            UDate aDate = schMdl.getScdEdate();
            String adate = UDateUtil.getSlashYYMD(aDate) + " "
            + UDateUtil.getSeparateHMS(aDate);
            //本文
            String tmpPath = null;
          //テンプレートファイルパス取得
            if (sendKbn == 1) {
                tmpPath = getSmlTemplateFilePathAttendEdit(appRootPath);
            } else {
                tmpPath = getSmlTemplateFilePathAttend(appRootPath);
            }
            String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
            Map<String, String> map = new HashMap<String, String>();
            map.put("ADATE", adate);
            map.put("UNAME", addUserName);
            map.put("NAME", userName);
            map.put("TITLE", thtitle);
            map.put("FRDATE", fdate);
            map.put("TODATE", tdate);
            map.put("BODY", body);
            map.put("BIKO", biko);
            map.put("URL", url);

            String bodyml = StringUtil.merge(tmpBody, map);
            String omit = gsMsg.getMessage("cmn.mail.omit");

            if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {
                bodyml = omit + "\r\n\r\n" + bodyml;
                bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);
            }

            //ショートメール送信用モデルを作成する。
            SmlSenderModel smlModel = new SmlSenderModel();
            //送信者(システムメールを指定)
            smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
            //TO
            smlModel.setSendToUsrSidArray(sidList);
            //タイトル
            //スケジュール
            String textSchedule = gsMsg.getMessage("schedule.108");
            //出欠確認
            String textAdd = null;
            if (sendKbn == 1) {
                textAdd = "出欠確認 (再通知)";
            } else {
                textAdd = "出欠確認";
            }
            String title = "[GS " + textSchedule + "] " + textAdd + " " + thtitle;
            title = StringUtil.trimRengeString(title,
                    GSConstCommon.MAX_LENGTH_SMLTITLE);
            smlModel.setSendTitle(title);

            //本文
            smlModel.setSendBody(bodyml);
            //マーク
            smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);

            //メール送信処理開始
            SmlSender sender = new SmlSender(
                    con, cntCon, smlModel, pluginConfig, appRootPath, reqMdl__);
            sender.execute();
        } catch (Exception e) {
            e.printStackTrace();
            log__.error("ショートメール送信に失敗しました。");
            throw e;
        }
    }



    /**
     * <br>[機  能] ショートメールプラグインでスケジュール 出欠確認 完了通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param schMdl スケジュール内容(ショートメール送信用)
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param url スケジュールURL
     * @param date 完了日時
     * @throws Exception 実行例外
     */
    public void sendAttendCompSmail(
        Connection con,
        MlCountMtController cntCon,
        SchDataModel schMdl,
        String appRootPath,
        PluginConfig pluginConfig,
        boolean smailPluginUseFlg,
        String url,
        UDate date) throws Exception {
        GsMessage gsMsg = new GsMessage(reqMdl__);

        if (!smailPluginUseFlg) {
            //ショートメールプラグインが無効の場合、ショートメールを送信しない。
            return;
        }

        SchAdmConfModel admConf = getAdmConfModel(con);
        SchPriConfModel priConf = null;

        //送信するユーザSIDリストを作成
        List<Integer> sidList = new ArrayList<Integer>();

        //ユーザスケジュールの場合
        priConf = getSchPriConfModel(con, schMdl.getScdUsrSid());
        //本人以外が登録したスケジュール且つ、ショートメール通知する場合
        if (getAttendSmlSendKbn(admConf, priConf)) {
            sidList.add(new Integer(schMdl.getScdUsrSid()));
        }

        //送信先がない場合は終了
        if (sidList.size() < 1) {
            return;
        }

        try {
            //タイトル
            String thtitle = schMdl.getScdTitle();
            //内容
            String body = schMdl.getScdValue();
            //備考
            String biko = schMdl.getScdBiko();
            //開始日時
            UDate frDate = schMdl.getScdFrDate();
            String fdate = UDateUtil.getSlashYYMD(frDate) + " "
            + UDateUtil.getSeparateHMS(frDate);
            //終了日時
            UDate toDate = schMdl.getScdToDate();
            String tdate = UDateUtil.getSlashYYMD(toDate) + " "
            + UDateUtil.getSeparateHMS(toDate);
            //登録日時（回答完了日時）
            UDate aDate = date;
            String adate = UDateUtil.getSlashYYMD(aDate) + " "
            + UDateUtil.getSeparateHMS(aDate);
            //本文
            String tmpPath = getSmlTemplateFilePathAttendComp(appRootPath); //テンプレートファイルパス取得
            String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
            Map<String, String> map = new HashMap<String, String>();
            map.put("ADATE", adate);
            map.put("TITLE", thtitle);
            map.put("FRDATE", fdate);
            map.put("TODATE", tdate);
            map.put("BODY", body);
            map.put("BIKO", biko);
            map.put("URL", url);

            String bodyml = StringUtil.merge(tmpBody, map);
            String omit = gsMsg.getMessage("cmn.mail.omit");

            if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {
                bodyml = omit + "\r\n\r\n" + bodyml;
                bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);
            }

            //ショートメール送信用モデルを作成する。
            SmlSenderModel smlModel = new SmlSenderModel();
            //送信者(システムメールを指定)
            smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
            //TO
            smlModel.setSendToUsrSidArray(sidList);
            //タイトル
            //スケジュール
            String textSchedule = gsMsg.getMessage("schedule.108");
            //出欠確認
            String textAdd = "出欠確認 完了通知";
            String title = "[GS " + textSchedule + "] " + textAdd + " " + thtitle;
            title = StringUtil.trimRengeString(title,
                    GSConstCommon.MAX_LENGTH_SMLTITLE);
            smlModel.setSendTitle(title);

            //本文
            smlModel.setSendBody(bodyml);
            //マーク
            smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);

            //メール送信処理開始
            SmlSender sender = new SmlSender(
                    con, cntCon, smlModel, pluginConfig, appRootPath, reqMdl__);
            sender.execute();
        } catch (Exception e) {
            e.printStackTrace();
            log__.error("ショートメール送信に失敗しました。");
            throw e;
        }
    }

    /**
     * <br>[機  能] ショートメールの送信区分を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param admConf SchAdmConfModel
     * @param priConf SchPriConfModel
     * @param schKbn 0:グループ 1:個人
     * @return sendFlg 送信区分
     * @throws Exception 実行例外
     */
    public boolean getSmlSendKbn(
            SchAdmConfModel admConf,
            SchPriConfModel priConf,
            int schKbn
        ) throws Exception {
        boolean sendFlg = false;

        if (admConf.getSadSmailSendKbn() == GSConstSchedule.SMAIL_SEND_KBN_ADMIN) {
            if (schKbn == 0) {
                sendFlg = admConf.getSadSmailGroup() == GSConstSchedule.SMAIL_USE;
            } else {
                sendFlg = admConf.getSadSmail() == GSConstSchedule.SMAIL_USE;
            }
        } else {
            if (schKbn == 0) {
                sendFlg = priConf.getSccSmailGroup() == GSConstSchedule.SMAIL_USE;
            } else {
                sendFlg = priConf.getSccSmail() == GSConstSchedule.SMAIL_USE;
            }
        }

        return sendFlg;
    }

    /**
     * <br>[機  能] ショートメールの送信区分を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param admConf SchAdmConfModel
     * @param priConf SchPriConfModel
     * @return sendFlg 送信区分
     * @throws Exception 実行例外
     */
    public boolean getAttendSmlSendKbn(
            SchAdmConfModel admConf,
            SchPriConfModel priConf
        ) throws Exception {
        boolean sendFlg = false;

        if (admConf.getSadSmailSendKbn() == GSConstSchedule.SMAIL_SEND_KBN_ADMIN) {
                sendFlg = admConf.getSadSmailAttend() == GSConstSchedule.SMAIL_USE;
        } else {
                sendFlg = priConf.getSccSmailAttend() == GSConstSchedule.SMAIL_USE;
        }

        return sendFlg;
    }

    /**
     * <br>[機  能] ショートメールでスケジュール登録通知を行う。
     * <br>[解  説] 拡張登録用
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param schMdl スケジュール内容(ショートメール送信用)
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param dateList 登録日付リスト
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @throws Exception 実行例外
     */
    public void sendSmailEx(
        Connection con,
        MlCountMtController cntCon,
        SchDataModel schMdl,
        String appRootPath,
        PluginConfig pluginConfig,
        ArrayList<String> dateList,
        boolean smailPluginUseFlg) throws Exception {
        GsMessage gsMsg = new GsMessage(reqMdl__);

        if (!smailPluginUseFlg) {
            //ショートメール機能が無効の場合リターン
            return;
        }
        CmnUsrmInfDao udao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel umodel = null;
        String userName = "";
        // 被登録者
        int userSid = schMdl.getScdUsrSid();
        // 登録者
        int addUserSid = schMdl.getScdEuid();
        SchPriConfModel priConf = null;
        SchAdmConfModel admConf = getAdmConfModel(con);

        //送信するユーザSIDリストを作成
        List<Integer> sidList = new ArrayList<Integer>();
        if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
            //グループスケジュールの場合
            //所属しているユーザ一覧を取得
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> belongList
                = userBiz.getBelongUserList(con, userSid, null);
            for (CmnUsrmInfModel usMdl : belongList) {
                priConf = getSchPriConfModel(con, usMdl.getUsrSid());
                if (getSmlSendKbn(admConf, priConf, 0)) {
                    sidList.add(new Integer(usMdl.getUsrSid()));
                }
            }
            GroupDao gpDao = new GroupDao(con);
            CmnGroupmModel gpMdl = gpDao.getGroup(userSid);
            userName = gpMdl.getGrpName();
        } else {
            //ユーザスケジュールの場合
            priConf = getSchPriConfModel(con, userSid);
            //本人以外が登録したスケジュール且つ、ショートメール通知する場合
            if (getSmlSendKbn(admConf, priConf, 1)
                    && schMdl.getScdUsrSid() != schMdl.getScdEuid()) {
                sidList.add(new Integer(userSid));
                umodel = udao.select(userSid);
                userName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            }
        }
        //送信先がない場合は終了
        if (sidList.size() < 1) {
            return;
        }

        priConf.getSccSmailGroup();
        try {

            //登録ユーザ名
            umodel = udao.select(addUserSid);
            String addUserName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            //タイトル
            String thtitle = schMdl.getScdTitle();
            //内容
            String body = schMdl.getScdValue();
            //備考
            String biko = schMdl.getScdBiko();
            StringBuilder dateBuf = new StringBuilder();
            for (String str : dateList) {
                dateBuf.append(str);
                dateBuf.append("\r\n");
            }
            //開始日時
            UDate frDate = schMdl.getScdFrDate();
            //終了日時
            UDate toDate = schMdl.getScdToDate();
            String time = UDateUtil.getSeparateHMJ(frDate, reqMdl__) + " - "
            + UDateUtil.getSeparateHMJ(toDate, reqMdl__);

            //登録日時
            UDate aDate = schMdl.getScdEdate();
            String adate = UDateUtil.getSlashYYMD(aDate) + " "
            + UDateUtil.getSeparateHMS(aDate);
            //本文
            String tmpPath = getSmlTemplateExFilePath(appRootPath); //テンプレートファイルパス取得
            String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
            Map<String, String> map = new HashMap<String, String>();
            map.put("ADATE", adate);
            map.put("UNAME", addUserName);
            map.put("NAME", userName);
            map.put("TITLE", thtitle);
            map.put("SETDATE", dateBuf.toString());
            map.put("SETTIME", time);
            map.put("BODY", body);
            map.put("BIKO", biko);

            String bodyml = StringUtil.merge(tmpBody, map);
            String omit = gsMsg.getMessage("cmn.mail.omit");

            if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {
                bodyml = omit + "\r\n\r\n" + bodyml;
                bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);
            }

            //ショートメール送信用モデルを作成する。
            SmlSenderModel smlModel = new SmlSenderModel();
            //送信者(システムメールを指定)
            smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
            //TO
            smlModel.setSendToUsrSidArray(sidList);
            //スケジュール
            String textSchedule = gsMsg.getMessage("schedule.108");
            //登録通知
            String textAdd = gsMsg.getMessage("schedule.130");
            String title = "[GS " + textSchedule + "] " + textAdd  + " " + thtitle;
            title = StringUtil.trimRengeString(title,
                    GSConstCommon.MAX_LENGTH_SMLTITLE);
            smlModel.setSendTitle(title);

            //本文
            smlModel.setSendBody(bodyml);
            //マーク
            smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);

            //メール送信処理開始
            SmlSender sender = new SmlSender(
                    con, cntCon, smlModel, pluginConfig, appRootPath, reqMdl__);
            sender.execute();
        } catch (Exception e) {
            e.printStackTrace();
            log__.error("ショートメール送信に失敗しました。");
            throw e;
        }
    }

    /**
     * <br>[機  能] ショートメールでスケジュール登録通知を行う。
     * <br>[解  説] 拡張登録用
     * <br>[備  考]
     * @param cntCon MlCountMtController
     * @param schMdl スケジュール内容(ショートメール送信用)
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param dateList 登録日付リスト
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param url リンク先URL
     * @throws Exception 実行例外
     */
    public void sendPlgSmailEx(
        MlCountMtController cntCon,
        SchDataModel schMdl,
        String appRootPath,
        PluginConfig pluginConfig,
        ArrayList<String> dateList,
        boolean smailPluginUseFlg,
        String url) throws Exception {
        GsMessage gsMsg = new GsMessage(reqMdl__);

        if (!smailPluginUseFlg) {
            //ショートメール機能が無効の場合リターン
            return;
        }
        CmnUsrmInfDao udao = new CmnUsrmInfDao(con__);
        CmnUsrmInfModel umodel = null;
        String userName = "";
        // 被登録者
        int userSid = schMdl.getScdUsrSid();
        // 登録者
        int addUserSid = schMdl.getScdEuid();
        SchPriConfModel priConf = null;
        SchAdmConfModel admConf = getAdmConfModel(con__);

        //送信するユーザSIDリストを作成
        List<Integer> sidList = new ArrayList<Integer>();
        if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
            //グループスケジュールの場合
            //所属しているユーザ一覧を取得
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> belongList
                = userBiz.getBelongUserList(con__, userSid, null);

            for (CmnUsrmInfModel usMdl : belongList) {
                priConf = getSchPriConfModel(con__, usMdl.getUsrSid());
                if (getSmlSendKbn(admConf, priConf, 0)) {
                    //登録者以外に通知
                    if (usMdl.getUsrSid() != reqMdl__.getSmodel().getUsrsid()) {
                        sidList.add(new Integer(usMdl.getUsrSid()));
                    }
                }
            }
            GroupDao gpDao = new GroupDao(con__);
            CmnGroupmModel gpMdl = gpDao.getGroup(userSid);
            userName = gpMdl.getGrpName();
        } else {
            //ユーザスケジュールの場合
            priConf = getSchPriConfModel(con__, userSid);
            //本人以外が登録したスケジュール且つ、ショートメール通知する場合
            if (getSmlSendKbn(admConf, priConf, 1)
                    && schMdl.getScdUsrSid() != schMdl.getScdEuid()) {
                sidList.add(new Integer(userSid));
                umodel = udao.select(userSid);
                userName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            }
        }
        //送信先がない場合は終了
        if (sidList.size() < 1) {
            return;
        }

        priConf.getSccSmailGroup();
        try {

            //登録ユーザ名
            umodel = udao.select(addUserSid);
            String addUserName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            //タイトル
            String thtitle = schMdl.getScdTitle();
            //内容
            String body = schMdl.getScdValue();
            //備考
            String biko = schMdl.getScdBiko();
            StringBuilder dateBuf = new StringBuilder();
            for (String str : dateList) {
                dateBuf.append(str);
                dateBuf.append("\r\n");
            }
            //開始日時
            UDate frDate = schMdl.getScdFrDate();
            //終了日時
            UDate toDate = schMdl.getScdToDate();
            String time = UDateUtil.getSeparateHMJ(frDate, reqMdl__) + " - "
            + UDateUtil.getSeparateHMJ(toDate, reqMdl__);

            //登録日時
            UDate aDate = schMdl.getScdEdate();
            String adate = UDateUtil.getSlashYYMD(aDate) + " "
            + UDateUtil.getSeparateHMS(aDate);
            //本文
            String tmpPath = getSmlTemplateExFilePathPlg(appRootPath); //テンプレートファイルパス取得
            String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
            Map<String, String> map = new HashMap<String, String>();
            map.put("ADATE", adate);
            map.put("UNAME", addUserName);
            map.put("NAME", userName);
            map.put("TITLE", thtitle);
            map.put("SETDATE", dateBuf.toString());
            map.put("SETTIME", time);
            map.put("BODY", body);
            map.put("BIKO", biko);
            map.put("URL", url);

            String bodyml = StringUtil.merge(tmpBody, map);
            String omit = gsMsg.getMessage("cmn.mail.omit");

            if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {
                bodyml = omit + "\r\n\r\n" + bodyml;
                bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);
            }

            //ショートメール送信用モデルを作成する。
            SmlSenderModel smlModel = new SmlSenderModel();
            //送信者(システムメールを指定)
            smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
            //TO
            smlModel.setSendToUsrSidArray(sidList);
            //スケジュール
            String textSchedule = gsMsg.getMessage("schedule.108");
            //登録通知
            String textAdd = gsMsg.getMessage("schedule.130");
            String title = "[GS " + textSchedule + "] " + textAdd + " " + thtitle;
            title = StringUtil.trimRengeString(title,
                    GSConstCommon.MAX_LENGTH_SMLTITLE);
            smlModel.setSendTitle(title);

            //本文
            smlModel.setSendBody(bodyml);
            //マーク
            smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);

            //メール送信処理開始
            SmlSender sender = new SmlSender(
                    con__, cntCon, smlModel, pluginConfig, appRootPath, reqMdl__);
            sender.execute();
        } catch (Exception e) {
            e.printStackTrace();
            log__.error("ショートメール送信に失敗しました。");
            throw e;
        }
    }

    /**
     * <br>[機  能] スケジュールモデルから表示用のタイトルを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param schModel SchDataModel
     * @param sessionUsrSid ユーザSID
     * @return 表示用スケジュールタイトル
     * @throws SQLException SQL実行例外
     */
    public String getDspTitle(
            SchDataModel schModel,
            int sessionUsrSid) throws SQLException {
        String title = "";
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textYoteiari = gsMsg.getMessage("schedule.src.9");

        //自分の予定の場合
        if (schModel.getScdUsrSid() == sessionUsrSid) {
            return schModel.getScdTitle();
        }

        //公開区分
        if (schModel.getScdPublic() == GSConstSchedule.DSP_YOTEIARI) {
            //予定あり
            title = textYoteiari;
        } else if (schModel.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
            //非公開

        } else if (schModel.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP) {
            //所属グループのみ公開

            //表示グループに所属しているか判定
            GroupBiz gpBiz = new GroupBiz();
            boolean belongFlg
                = gpBiz.isBothBelongGroup(sessionUsrSid, schModel.getScdUsrSid(), con__);
            if (belongFlg) {
                //所属グループのみ公開 所属している
                title = schModel.getScdTitle();
            } else {
                //所属グループのみ公開 未所属の場合は予定ありを表示
                title = textYoteiari;
            }
        } else {
            //公開
            title = schModel.getScdTitle();
        }
        return title;
    }

    /**
     * <br>[機  能] アプリケーションのルートパスから更新通知メールのテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    public String getSmlTemplateFilePath(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/schedule/smail/koushin_tsuuchi.txt");
        return ret;
    }
    /**
     * <br>[機  能] アプリケーションのルートパスから更新通知メール(拡張用)のテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    public String getSmlTemplateExFilePath(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/schedule/smail/koushin_tsuuchi_ex.txt");
        return ret;
    }

    /**
     * <br>[機  能] スケジュールプラグインアプリケーションのルートパスから更新通知メールのテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    public String getSmlTemplateFilePathPlg(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/schedule/smail/koushin_tsuuchi_plg.txt");
        return ret;
    }
    /**
     * <br>[機  能] スケジュールプラグインアプリケーションのルートパスから更新通知メール(拡張用)のテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    public String getSmlTemplateExFilePathPlg(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/schedule/smail/koushin_tsuuchi_ex_plg.txt");
        return ret;
    }

    /**
     * <br>[機  能] スケジュールプラグインアプリケーションのルートパスから出欠依頼通知メールのテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    public String getSmlTemplateFilePathAttend(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/schedule/smail/shukketsu_irai_tsuuchi.txt");
        return ret;
    }

    /**
     * <br>[機  能] スケジュールプラグインアプリケーションのルートパスから出欠依頼通知メール（再通知）のテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    public String getSmlTemplateFilePathAttendEdit(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/schedule/smail/shukketsu_edit_tsuuchi.txt");
        return ret;
    }

    /**
     * <br>[機  能] スケジュールプラグインアプリケーションのルートパスから出欠確認完了通知メールのテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    public String getSmlTemplateFilePathAttendComp(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/schedule/smail/shukketsu_comp_tsuuchi.txt");
        return ret;
    }

    /**
     * スケジュール全般のログ出力を行う
     * @param map マップ
     * @param req リクエスト
     * @param res レスポンス
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     */
    public void outPutLog(
            ActionMapping map,
            HttpServletRequest req,
            HttpServletResponse res,
            String opCode,
            String level,
            String value) {
        outPutLog(map, req, res, opCode, level, value, null);
    }

    /**
     * スケジュール全般のログ出力を行う
     * @param map マップ
     * @param req リクエスト
     * @param res レスポンス
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param logCode ログコード
     */
    public void outPutLog(
            ActionMapping map,
            HttpServletRequest req,
            HttpServletResponse res,
            String opCode,
            String level,
            String value,
            String logCode) {

        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int usrSid = -1;
        if (usModel != null) {
            usrSid = usModel.getUsrsid(); //セッションユーザSID
        }
        GsMessage gsMsg = new GsMessage();
        /** メッセージ スケジュール **/
        String schedule = gsMsg.getMessage(req, "schedule.108");

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usrSid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConstSchedule.PLUGIN_ID_SCHEDULE);
        logMdl.setLogPluginName(schedule);
        String type = map.getType();
        logMdl.setLogPgId(StringUtil.trimRengeString(type, 100));
        logMdl.setLogPgName(getPgName(map.getType()));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(value);
        logMdl.setLogIp(CommonBiz.getRemoteAddr(req));
        logMdl.setVerVersion(GSConst.VERSION);
        if (logCode != null) {
            logMdl.setLogCode(logCode);
        }

        LoggingBiz logBiz = new LoggingBiz(con__);
        String domain = GroupSession.getResourceManager().getDomain(req);
        logBiz.outPutLog(logMdl, domain);
    }
    /**
     * スケジュールＡＰＩ全般全般のログ出力を行う
     * @param req リクエスト
     * @param con コネクション
     * @param usid ユーザSID
     * @param pgId プログラムID
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     */
    public void outPutApiLog(
            RequestModel req,
            Connection con,
            int usid,
            String pgId,
            String opCode,
            String level,
            String value) {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        /** メッセージ スケジュール **/
        String schedule = gsMsg.getMessage("schedule.108");

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConstSchedule.PLUGIN_ID_SCHEDULE);
        logMdl.setLogPluginName(schedule);
        logMdl.setLogPgId(pgId);
        logMdl.setLogPgName(getPgName(pgId));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(value);
        logMdl.setLogIp(req.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);

        LoggingBiz logBiz = new LoggingBiz(con);
        String domain = reqMdl__.getDomain();
        logBiz.outPutLog(logMdl, domain);
    }
    /**
     * プログラムIDからプログラム名称を取得する
     * @param id アクションID
     * @return String
     */
    public String getPgName(String id) {
        String ret = new String();
        if (StringUtil.isNullZeroString(id)) {
            return ret;
        }

        GsMessage gsMsg = new GsMessage(reqMdl__);
        log__.info("プログラムID==>" + id);
        if (id.equals("jp.groupsession.v2.sch.sch040.Sch040Action")) {
            return gsMsg.getMessage("schedule.151");
        }
        if (id.equals("jp.groupsession.v2.sch.sch041kn.Sch041knAction")) {
            return gsMsg.getMessage("schedule.152");
        }

        if (id.equals("jp.groupsession.v2.sch.sch081.Sch081Action")) {
            return gsMsg.getMessage("schedule.153");
        }
        if (id.equals("jp.groupsession.v2.sch.sch082.Sch082Action")) {
            return gsMsg.getMessage("schedule.154");
        }
        if (id.equals("jp.groupsession.v2.sch.sch083.Sch083Action")) {
            return gsMsg.getMessage("schedule.155");
        }
        if (id.equals("jp.groupsession.v2.sch.sch084.Sch084Action")) {
            return gsMsg.getMessage("schedule.156");
        }
        if (id.equals("jp.groupsession.v2.sch.sch084kn.Sch084knAction")) {
            return gsMsg.getMessage("schedule.157");
        }
        if (id.equals("jp.groupsession.v2.sch.sch085.Sch085Action")) {
            return gsMsg.getMessage("schedule.158");
        }

        if (id.equals("jp.groupsession.v2.sch.sch091.Sch091Action")) {
            return gsMsg.getMessage("schedule.159");
        }
        if (id.equals("jp.groupsession.v2.sch.sch092.Sch092Action")) {
            return gsMsg.getMessage("schedule.160");
        }
        if (id.equals("jp.groupsession.v2.sch.sch093.Sch093Action")) {
            return gsMsg.getMessage("schedule.161");
        }
        if (id.equals("jp.groupsession.v2.sch.sch094.Sch094Action")) {
            return gsMsg.getMessage("schedule.162");
        }
        if (id.equals("jp.groupsession.v2.sch.sch095.Sch095Action")) {
            return gsMsg.getMessage("schedule.163");
        }

        if (id.equals("jp.groupsession.v2.sch.sch100.Sch100Action")) {
            return gsMsg.getMessage("schedule.164");
        }
        if (id.equals("jp.groupsession.v2.sch.sch110.Sch110Action")) {
            return gsMsg.getMessage("schedule.165");
        }
        if (id.equals("jp.groupsession.v2.sch.sch110kn.Sch110knAction")) {
            return gsMsg.getMessage("schedule.166");
        }

        if (id.equals("jp.groupsession.v2.api.schedule.search.ApiSchSearchAction")) {
            return gsMsg.getMessage("schedule.167");
        }
        return ret;
    }
    /**
     * <br>[機  能] スケジュールの初期表示で表示する画面のURLを取得する。
     * <br>[解  説] DBに登録された個人設定情報を取得しそのURLを返す。
     * <br>
     * <br>[備  考]
     * @param con DBコネクション
     * @param usrSid ユーザSID
     * @return 遷移先URL
     * @throws SQLException SQL実行エラー
     */
    public String getDispDefaultUrl(Connection con, int usrSid) throws SQLException {

        String url = null;
        SchPriConfModel pconf = getSchPriConfModel(con, usrSid);

        if (pconf != null) {
            int dsp = pconf.getSccDefDsp();
            if (dsp == GSConstSchedule.DSP_DAY) {
                url = GSConstSchedule.DSP_DAY_URL;
            } else if (dsp == GSConstSchedule.DSP_WEEK) {
                url = GSConstSchedule.DSP_WEEK_URL;
            } else if (dsp == GSConstSchedule.DSP_MONTH) {
                url = GSConstSchedule.DSP_MONTH_URL;
            } else if (dsp == GSConstSchedule.DSP_PRI_WEEK) {
                url = GSConstSchedule.DSP_PRI_WEEK_URL;
            }
        }
        return url;
    }

    /**
     * <br>[機  能] スケジュール 初期値 編集権限を設定可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canEditInitConf(Connection con) throws SQLException {
        return canEditInitConf(getAdmConfModel(con));
    }

    /**
     * <br>[機  能] スケジュール 初期値 編集権限を設定可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param admConf スケジュール 管理者設定
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canEditInitConf(SchAdmConfModel admConf) throws SQLException {
        return admConf.getSadIniEditStype()
                    == GSConstSchedule.SAD_INIEDIT_STYPE_USER;
    }

    /**
     * <br>[機  能] スケジュール 初期値 公開区分を設定可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canPubInitConf(Connection con) throws SQLException {
        return canPubInitConf(getAdmConfModel(con));
    }


    /**
     * <br>[機  能] スケジュール 初期値 公開区分を設定可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param admConf スケジュール 管理者設定
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canPubInitConf(SchAdmConfModel admConf) throws SQLException {
        return admConf.getSadInitPublicStype()
                    == GSConstSchedule.SAD_INIPUBLIC_STYPE_USER;
    }

    /**
     * <br>[機  能] スケジュール 初期値 同時編集を設定可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canSameInitConf(Connection con) throws SQLException {
        return canSameInitConf(getAdmConfModel(con));
    }


    /**
     * <br>[機  能] スケジュール 初期値 同時編集を設定可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param admConf スケジュール 管理者設定
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canSameInitConf(SchAdmConfModel admConf) throws SQLException {
        return admConf.getSadIniSameStype()
                    == GSConstSchedule.SAD_INISAME_STYPE_USER;
    }

    /**
     * <br>[機  能] 各ユーザが重複登録設定を設定可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canEditRepertKbn(Connection con) throws SQLException {
        return canEditRepertKbn(getAdmConfModel(con));
    }

    /**
     * <br>[機  能] 各ユーザが重複登録設定を設定可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param admConf スケジュール 管理者設定
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canEditRepertKbn(SchAdmConfModel admConf) throws SQLException {
        return admConf.getSadRepeatStype()
                    == GSConstSchedule.SAD_REPEAT_STYPE_USER;
    }

    /**
     * <br>[機  能] スケジュール 編集権限の初期値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @return スケジュール 編集権限の初期値
     * @throws SQLException SQL実行時例外
     */
    public int getInitEditAuth(Connection con, int userSid) throws SQLException {
        SchPriConfModel uconf = getSchPriConfModel(con, userSid);
        return getInitEditAuth(con, uconf);
    }

    /**
     * <br>[機  能] スケジュール 編集権限の初期値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param uconf スケジュール個人設定
     * @return スケジュール 編集権限の初期値
     * @throws SQLException SQL実行時例外
     */
    public int getInitEditAuth(Connection con, SchPriConfModel uconf) throws SQLException {
        int editAuth = uconf.getSccIniEdit();

        SchAdmConfModel admConf = getAdmConfModel(con);
        if (!canEditInitConf(admConf)) {
            editAuth = admConf.getSadIniEdit();
        }

        return editAuth;
    }

    /**
     * <br>[機  能] スケジュール 公開区分の初期値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param uconf スケジュール個人設定
     * @return スケジュール 編集権限の初期値
     * @throws SQLException SQL実行時例外
     */
    public int getInitPubAuth(Connection con, SchPriConfModel uconf)
            throws SQLException {

        int pubAuth = uconf.getSccIniPublic();

        SchAdmConfModel admConf = getAdmConfModel(con);
        if (!canPubInitConf(admConf)) {
            pubAuth = admConf.getSadIniPublic();
        }

        return pubAuth;
    }

    /**
     * <br>[機  能] スケジュール 同時修正の初期値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param uconf スケジュール個人設定
     * @return スケジュール 同時修正の初期値
     * @throws SQLException SQL実行時例外
     */
    public int getInitSameAuth(Connection con, SchPriConfModel uconf)
            throws SQLException {

        int sameAuth = uconf.getSccIniSame();

        SchAdmConfModel admConf = getAdmConfModel(con);
        if (!canSameInitConf(admConf)) {
            sameAuth = admConf.getSadIniSame();
        }

        return sameAuth;
    }

    /**
     * <br>[機  能] 重複登録区分を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param uconf スケジュール個人設定
     * @param userSid ユーザSID
     * @return true:重複登録可能 false:重複登録不可
     * @throws SQLException SQL実行時例外
     */
    public SchRepeatKbnModel getRepertKbn(Connection con, SchPriConfModel uconf, int userSid)
    throws SQLException {
        int repertKbn = 0;
        int repertMyKbn = 0;

        SchAdmConfModel admConf = getAdmConfModel(con);
        if (!canEditRepertKbn(admConf)) {
            repertKbn = admConf.getSadRepeatKbn();
            repertMyKbn = admConf.getSadRepeatMyKbn();
        } else {
            if (uconf != null) {
                repertKbn = uconf.getSccRepeatKbn();
                repertMyKbn = uconf.getSccRepeatMyKbn();
            } else {
                SchPriConfModel usrConf = getDefaulPriConfModel(userSid, con);
                repertKbn = usrConf.getSccRepeatKbn();
                repertMyKbn = usrConf.getSccRepeatMyKbn();
            }
        }

        SchRepeatKbnModel model = new SchRepeatKbnModel();
        model.setRepeatKbn(repertKbn);
        model.setRepeatMyKbn(repertMyKbn);

        return model;
    }


    /**
     * <br>[機  能] 追加情報をセットする。
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param con DBコネクション
     * @param pconfig プラグイン設定情報
     * @param paramMdl パラメータ
     * @return 他プラグインのスケジュールデータ
     * @throws Exception インフォーメーション取得クラスの設定ミスの場合にスロー
     */
    public ArrayList<SchDataModel> getAppendSchData(RequestModel reqMdl, Connection con,
            PluginConfig pconfig, SchAppendDataParam paramMdl) throws Exception {
        log__.debug("START getAppendSchData");

        String [] pifclss = pconfig.getSchAppendSchData();
        SchAppendSchData[] schData = null;
        try {
            schData = __getAppendSchData(pifclss);
        } catch (ClassNotFoundException e) {
            log__.error("クラスが見つかりません。設定を見直してください。", e);
            throw e;
        } catch (IllegalAccessException e) {
            log__.error("クラスの設定が間違っています。設定を見直してください。", e);
            throw e;
        } catch (InstantiationException e) {
            log__.error("クラスの設定が間違っています。設定を見直してください。", e);
            throw e;
        }

        ArrayList<SchAppendDataModel> schDatas = new ArrayList<SchAppendDataModel>();

        for (SchAppendSchData imsgCls : schData) {
            log__.debug(imsgCls);
            List<SchAppendDataModel> plist
                    = imsgCls.getAppendSchData(paramMdl, reqMdl, con);
            if (plist != null) {
                schDatas.addAll(plist);
            }
        }
        //ArrayList<SchAppendDataModel>をArrayList<SchDataModel>に変換
        ArrayList<SchDataModel> schDatasList = __getSimpleSch(schDatas);

        return schDatasList;
    }

    /**
     * <p>他プラグインのスケジュールデータ情報のリストを取得する
     * @param classNames プラグインクラス名
     * @throws ClassNotFoundException 指定されたクラスが存在しない
     * @throws IllegalAccessException 実装クラスのインスタンス生成に失敗
     * @throws InstantiationException 実装クラスのインスタンス生成に失敗
     * @return リスナー
     */
    private SchAppendSchData[] __getAppendSchData(String[] classNames)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        SchAppendSchData[] iclasses = new SchAppendSchData[classNames.length];
        for (int i = 0; i < classNames.length; i++) {
            Object obj = ClassUtil.getObject(classNames[i]);
            iclasses[i] = (SchAppendSchData) obj;
        }
        return iclasses;
    }

    /**
     * <p>他プラグインスケジュール情報の変換を行う
     * @param list プラグインクラス名
     * @return schDataList
     */
    private ArrayList<SchDataModel> __getSimpleSch(ArrayList<SchAppendDataModel> list) {
        ArrayList<SchDataModel> schDataList = new ArrayList<SchDataModel>();
        if (!list.isEmpty()) {
            SchDataModel ssm = null;
            for (SchAppendDataModel sdm : list) {
                ssm = new SchDataModel();
                ssm.setScdUsrSid(sdm.getUsrSid());
                ssm.setScdAppendId(sdm.getSchPlgId());
                ssm.setScdAppendUrl(sdm.getSchPlgUrl());
                ssm.setScdFrDate(sdm.getFromDate());
                ssm.setScdTitle(sdm.getTitle());
                ssm.setScdToDate(sdm.getToDate());
                ssm.setScdDaily(sdm.getTimeKbn());
                ssm.setScdValue(sdm.getValueStr());
                ssm.setScdPublic(sdm.getPublic());
                ssm.setScdBgcolor(ssm.getScdBgcolor());
                schDataList.add(ssm);
            }
        }
        return schDataList;
    }

    /**
     * <p>他プラグインスケジュール情報の変換を行う
     * @param color 色
     * @param con Connection
     * @return color
     * @throws SQLException SQL実行時例外
     */
    public int getUserColor(int color, Connection con) throws SQLException {

        if (color == 0) {
            color = GSConstSchedule.BGCOLOR_BLUE;
        }

        if (getAdmConfModel(con).getSadMsgColorKbn()
                == GSConstSchedule.SAD_MSG_NO_ADD
                && color > GSConstSchedule.BGCOLOR_BLACK) {
            color = GSConstSchedule.BGCOLOR_BLUE;
        }

        return color;
    }
    /**
     *
     * <br>[機  能] 選択した値がグループコンボ上にない場合に有効な値を返す
     * <br>[解  説]
     * <br>[備  考]
     * @param list ラベルリスト
     * @param nowSel 選択中ラベルID
     * @param defSel 初期ラベルID
     * @return 有効な選択値
     */
    public String getEnableSelectGroup(List<SchLabelValueModel> list
            , String nowSel
            , String defSel) {
        boolean nowVar = false;
        boolean defVar = false;
        if (list == null || list.size() <= 0) {
            return "";
        }
        for (LabelValueBean label : list) {
            if (label.getValue().equals(nowSel)) {
                nowVar = true;
                break;
            }
            if (label.getValue().equals(defSel)) {
                defVar = true;
            }
        }
        if (nowVar) {
            return nowSel;
        }
        if (defVar) {
            return defSel;
        }
        return list.get(0).getValue();
    }

    /**
     * <br>[機  能] 指定したスケジュールデータを閲覧可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param scdSid スケジュールSID
     * @param sessionUserSid セッションユーザSID
     * @return true: 閲覧可能 false: 閲覧不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canAccessSchedule(Connection con, int scdSid, int sessionUserSid)
    throws SQLException {
        SchDataDao schDao = new SchDataDao(con);
        SchDataModel schData = schDao.getSchData(scdSid);
        if (schData == null) {
            return true;
        }

        return canAccessSchedule(con, schData, sessionUserSid);
    }

    /**
     * <br>[機  能] 指定したスケジュールデータを閲覧可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param schData スケジュールデータ
     * @param sessionUserSid セッションユーザSID
     * @return true: 閲覧可能 false: 閲覧不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canAccessSchedule(Connection con, SchDataModel schData, int sessionUserSid)
    throws SQLException {
        RelationBetweenScdAndRsvChkBiz scdRsvBiz
            = new RelationBetweenScdAndRsvChkBiz(reqMdl__, con);

        //対象のスケジュールを閲覧可能かを判定する
        int scdUsrSid = schData.getScdUsrSid();
        int scdUsrKbn = schData.getScdUsrKbn();

        return scdRsvBiz.canAccessSchedule(scdUsrSid, scdUsrKbn, sessionUserSid);
    }
    /**
     * <br>[機  能] 指定したスケジュールデータを編集可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param schData スケジュールデータ
     * @param sessionUserSid セッションユーザSID
     * @return true: 閲覧可能 false: 閲覧不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canRegistSchedule(Connection con, SchDataModel schData, int sessionUserSid)
    throws SQLException {
        RelationBetweenScdAndRsvChkBiz scdRsvBiz
            = new RelationBetweenScdAndRsvChkBiz(reqMdl__, con);

        //対象のスケジュールを閲覧可能かを判定する
        int scdUsrSid = schData.getScdUsrSid();
        int scdUsrKbn = schData.getScdUsrKbn();
        return scdRsvBiz.canRegistSchedule(scdUsrSid, scdUsrKbn, sessionUserSid);
    }

    /**
     * <br>[機  能] 指定されたユーザSIDからアクセス可能なSIDのみ取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param sessionUsrSid セッションユーザSID
     * @param usrSids ユーザSIDリスト
     * @throws SQLException SQL実行時例外
     * @return アクセス可能ユーザSIDリスト
     */
    public ArrayList<Integer> getAccessUserList(Connection con, int sessionUsrSid,
            ArrayList<Integer> usrSids) throws SQLException {
        SchDao scheduleDao = new SchDao(con);
        List<Integer> notAccessUserList
        = scheduleDao.getNotAccessUserList(sessionUsrSid);

        ArrayList<Integer> ret = new ArrayList<Integer>();

        for (Integer usid : usrSids) {
            if (notAccessUserList.indexOf(usid) < 0) {
                ret.add(usid);
            }
        }
        return ret;
    }

}
