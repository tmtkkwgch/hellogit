package jp.groupsession.v2.ntp;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.batch.IBatchModel;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.model.NtpAdmConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] バッチ処理起動時に実行される処理を実装
 * <br>[解  説] 日報についての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class NippouBatchListenerImpl implements IBatchListener {
    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(NippouBatchListenerImpl.class);

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public NippouBatchListenerImpl() {
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
        log__.info("日報バッチ処理開始");

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {
            NtpCommonBiz biz = new NtpCommonBiz();
            NtpAdmConfModel conf = biz.getAdmConfModel(con);
            if (conf.getNacAtdelFlg() == GSConstNippou.AUTO_DELETE_ON) {
                //自動削除実行

                //基準日を作成
                UDate bdate = new UDate();
                int year = conf.getNacAtdelY();
                int month = conf.getNacAtdelM();
                bdate.addYear(-year);
                bdate.addMonth(-month);

                //日報データ(確認)削除実行
                biz.deleteOldNippouChk(con, bdate);
                //日報データ(コメント)削除実行
                biz.deleteOldNippouCmt(con, bdate);
                //日報データ(いいね)削除実行
                biz.deleteOldNippouGood(con, bdate);
                //日報データ削除実行
                biz.deleteOldNippou(con, bdate);
            }
            commitFlg = true;
            log__.debug("日報バッチ処理終了");
        } catch (SQLException e) {
            log__.error("日報バッチ処理失敗", e);
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
