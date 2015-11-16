package jp.groupsession.v2.rss.listener;

import java.sql.Connection;

import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.batch.IBatchModel;
import jp.groupsession.v2.rss.RssBiz;

/**
 * <br>[機  能] バッチ処理起動時に実行される処理を実装
 * <br>[解  説] RSSについての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class RssBatchListenerImpl implements IBatchListener {

    /**
     * <p>日次バッチ起動時に実行される
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void doDayBatch(Connection con, IBatchModel param) throws Exception {
    }

    /**
     * <p>1時間間隔で実行されるJob
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void doOneHourBatch(Connection con, IBatchModel param) throws Exception {

        //規定時間を過ぎても更新されていないRSSフィード情報を更新する。
        RssBiz rssBiz = new RssBiz();
        rssBiz.updateFeedData(
                con, param.getGsContext(), rssBiz.getUpdateTime(con), param.getDomain());

    }

    /**
     * <p>5分間隔で実行されるJob
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void do5mBatch(Connection con, IBatchModel param) throws Exception {
//        log__.debug("RSSバッチ処理開始");
//        //
//        UDate ud = new UDate();
//        int mm = ud.getIntMinute();
//
//        if (mm % 15 > 0) {
//            System.gc(); //GCによりH2のテンポラリファイルを削除
//            log__.debug("RSSバッチ処理終了");
//            return;
//        }
//
//        log__.debug("RSSバッチ処理終了");
    }
}