package jp.groupsession.v2.rsv;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.batch.IBatchModel;
import jp.groupsession.v2.rsv.dao.RsvAdmConfDao;
import jp.groupsession.v2.rsv.dao.RsvSisKyrkDao;
import jp.groupsession.v2.rsv.dao.RsvSisYrkDao;
import jp.groupsession.v2.rsv.model.RsvAdmConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] バッチ処理起動時に実行される処理を実装
 * <br>[解  説] 施設予約についての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class ReserveBatchListenerImpl implements IBatchListener {

    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(ReserveBatchListenerImpl.class);

    /**
     * <br>[機  能] 日次バッチ起動
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void doDayBatch(Connection con, IBatchModel param) throws Exception {

        log__.debug("施設予約バッチ処理開始");

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {

            //施設予約管理者設定を取得
            RsvAdmConfDao admDao = new RsvAdmConfDao(con);
            RsvAdmConfModel admMdl = admDao.select();

            if (admMdl == null) {
                //管理者設定が未設定
                return;
            }

            int adlKbn = admMdl.getRacAdlKbn();
            if (adlKbn == GSConstReserve.RSV_RADIO_OFF) {
                //削除区分 = 自動で削除しない
                return;
            }

            int adlYear = admMdl.getRacAdlYear();
            int adlMonth = admMdl.getRacAdlMonth();

            //削除する境界の日付を設定する
            UDate delUd = new UDate();
            log__.debug("現在日 = " + delUd.getDateString());
            log__.debug("削除条件 経過年 = " + adlYear);
            log__.debug("削除条件 経過年 = " + adlMonth);

            delUd.addYear((adlYear * -1));
            delUd.addMonth((adlMonth * -1));
            delUd.setHour(GSConstReserve.DAY_END_HOUR);
            delUd.setMinute(GSConstReserve.DAY_END_MINUTES);
            delUd.setSecond(GSConstReserve.DAY_END_SECOND);
            delUd.setMilliSecond(GSConstReserve.DAY_END_MILLISECOND);

            log__.debug("削除境界線(これ以前のデータを削除) = " + delUd.getTimeStamp());

            RsvSisYrkDao yrkDao = new RsvSisYrkDao(con);
            ArrayList<Integer> deleteSidList = yrkDao.getRsySidsDeleteForAdmin(delUd);
            yrkDao.deleteForAdmin(delUd);
            RsvSisKyrkDao kyrkDao = new RsvSisKyrkDao(con);
            kyrkDao.delete(deleteSidList);

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("施設予約バッチ処理失敗", e);
            JDBCUtil.rollback(con);
            throw e;
        } finally {

            log__.debug("施設予約バッチ処理終了");

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