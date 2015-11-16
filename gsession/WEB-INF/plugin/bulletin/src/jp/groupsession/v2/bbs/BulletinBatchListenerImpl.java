package jp.groupsession.v2.bbs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.batch.IBatchModel;
import jp.groupsession.v2.bbs.dao.BbsForInfDao;
import jp.groupsession.v2.bbs.dao.BbsLogCountSumDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsAdmConfModel;
import jp.groupsession.v2.bbs.model.BbsForInfModel;
import jp.groupsession.v2.bbs.model.BbsLogCountSumModel;
import jp.groupsession.v2.cmn.biz.StatisticsBiz;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.dao.base.BbsLogCountAdelDao;
import jp.groupsession.v2.man.model.base.BbsLogCountAdelModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] バッチ処理起動時に実行される処理を実装
 * <br>[解  説] 掲示板についての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class BulletinBatchListenerImpl implements IBatchListener {
    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(BulletinBatchListenerImpl.class);

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public BulletinBatchListenerImpl() {
        super();
    }

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void doDayBatch(Connection con, IBatchModel param) throws Exception {
        log__.debug("掲示板バッチ処理開始");

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {
            BbsBiz biz = new BbsBiz();
            BbsAdmConfModel conf = biz.getBbsAdminData(con, -1);
            if (conf.getBacAtdelFlg() == GSConstBulletin.AUTO_DELETE_ON) {
                //自動削除実行

                //基準日を作成
                UDate bdate = new UDate();
                int year = conf.getBacAtdelY();
                int month = conf.getBacAtdelM();
                bdate.addYear(-year);
                bdate.addMonth(-month);
                //削除実行
                biz.deleteOldBulletin(con, bdate);
            }

            //掲示期限を過ぎたスレッドを削除する
            biz.deleteOverLimitBulletin(con);

            //統計情報の集計を行う
            BbsLogCountSumDao logSumDao = new BbsLogCountSumDao(con);
            int[] blsKbnList = {GSConstBulletin.BLS_KBN_WRITE,
                                    GSConstBulletin.BLS_KBN_VIEW
            };

            for (int blsKbn : blsKbnList) {
                List<BbsLogCountSumModel> logSumList
                    = logSumDao.getNonRegisteredList(blsKbn);
                if (logSumList != null && !logSumList.isEmpty()) {
                    for (BbsLogCountSumModel logSumMdl : logSumList) {
                        if (logSumDao.update(logSumMdl) == 0) {
                            logSumDao.insert(logSumMdl);
                        }
                    }
                }
            }

            //掲示板 統計情報集計データ削除
            log__.debug("掲示板 統計情報集計データ削除処理開始");
            BbsLogCountAdelModel bbsLogCntAdelMdl = null;
            BbsLogCountAdelDao bbsLogCntAdelDao = new BbsLogCountAdelDao(con);
            List<BbsLogCountAdelModel> adelList = bbsLogCntAdelDao.select();
            if (adelList != null && !adelList.isEmpty()) {
                bbsLogCntAdelMdl = adelList.get(0);
            }
            if (bbsLogCntAdelMdl != null
                    && bbsLogCntAdelMdl.getBldDelKbn() == GSConstMain.LAD_DELKBN_AUTO) {
                int bbsLogAdelYear = bbsLogCntAdelMdl.getBldDelYear();
                int bbsLogAdelMonth = bbsLogCntAdelMdl.getBldDelMonth();

                //削除する境界の日付を設定する
                UDate bbsLogDelUd = new UDate();
                log__.debug("現在日 = " + bbsLogDelUd.getDateString());
                log__.debug("削除条件 経過年 = " + bbsLogAdelYear);
                log__.debug("削除条件 経過月 = " + bbsLogAdelMonth);
                bbsLogDelUd.addYear(bbsLogAdelYear * -1);
                bbsLogDelUd.addMonth(bbsLogAdelMonth * -1);
                bbsLogDelUd.setMaxHhMmSs();
                log__.debug("削除境界線(この日以前のデータを削除) = " + bbsLogDelUd.getTimeStamp());

                StatisticsBiz statsBiz = new StatisticsBiz();
                //集計データを削除
                int bbsViewLogCount =
                        statsBiz.deleteLogCount(con, "BBS_WRITE_LOG_COUNT",
                                "BWL_WRT_DATE", bbsLogAdelYear, bbsLogAdelMonth);
                log__.debug("掲示板閲覧 統計情報集計データ" + bbsViewLogCount + "件を削除");
                int bbsWriteLogCount =
                        statsBiz.deleteLogCount(con, "BBS_VIEW_LOG_COUNT",
                                "BVL_VIEW_DATE", bbsLogAdelYear, bbsLogAdelMonth);
                log__.debug("掲示板投稿 統計情報集計データ" + bbsWriteLogCount + "件を削除");
            }

            commitFlg = true;
            log__.debug("掲示板バッチ処理終了");
        } catch (SQLException e) {
            log__.error("掲示板バッチ処理失敗", e);
            JDBCUtil.rollback(con);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * <p>1時間間隔で実行されるJob
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void doOneHourBatch(Connection con, IBatchModel param) throws Exception {

        UDate now = new UDate();

        //時刻 = 0時の場合、フォーラム集計情報の更新を行う
        if (now.getIntHour() == 0) {

            con.setAutoCommit(false);
            boolean commitFlg = false;

            try {
                //フォーラム一覧を取得
                now.setZeroHhMmSs();

                BbsForInfDao forDao = new BbsForInfDao(con);
                List<BbsForInfModel> forList = forDao.select();
                BbsBiz bbsBiz = new BbsBiz();
                BulletinDao bbsDao = new BulletinDao(con);
                for (BbsForInfModel forMdl : forList) {

                    //全フォーラムの最終更新日時を更新する
                    //フォーラムの最終更新日時と表示されているスレッド内の最新日時から
                    //最新のものを取得して更新する
                    bbsDao.updateBfsWrtDateBatch(forMdl.getBfiSid(), now);

                    //フォーラム集計情報の更新
                    bbsBiz.updateBbsForSum(con, forMdl.getBfiSid(), 0, now, false);
                }


//                BbsForInfDao forDao = new BbsForInfDao(con);
//                BbsThreInfDao threDao = new BbsThreInfDao(con);

//                List<BbsForInfModel> forList = forDao.select();
//                ArrayList<BbsThreInfModel> overLimitThreList = new ArrayList<BbsThreInfModel>();

//                for (BbsForInfModel forMdl : forList) {
//                    UDate delDate = now.cloneUDate();
//                    if (forMdl.getBfiKeep() == GSConstBulletin.THREAD_KEEP_YES) {
//                        delDate.addYear(-forMdl.getBfiKeepDateY());
//                        delDate.addMonth(-forMdl.getBfiKeepDateM());
//                    }
//                    overLimitThreList.addAll(
//                            threDao.getOverLimitThreData(forMdl.getBfiSid(), delDate));
//                }
//                ArrayList<BbsThreInfModel> overLimitThreList = threDao.getOverLimitThreData(now);

//                BbsBiz bbsBiz = new BbsBiz();
//                for (BbsThreInfModel threData : overLimitThreList) {
//                    //フォーラム集計情報の更新
//                    bbsBiz.updateBbsForSum(con, threData.getBfiSid(), 0, now, false);
//                }

                commitFlg = true;
                log__.debug("掲示板バッチ処理終了");
            } catch (SQLException e) {
                log__.error("掲示板バッチ処理失敗", e);
                JDBCUtil.rollback(con);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    JDBCUtil.rollback(con);
                }
            }
        }
    }

    /**
     * <p>5分間隔で実行されるJob
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void do5mBatch(Connection con, IBatchModel param) throws Exception {

    }
}
