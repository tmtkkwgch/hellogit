package jp.groupsession.v2.zsk;

import java.sql.Connection;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.batch.IBatchModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.base.CmnUsrInoutDao;
import jp.groupsession.v2.cmn.model.base.CmnUsrInoutModel;
import jp.groupsession.v2.zsk.dao.ZaiFixUpdateDao;
import jp.groupsession.v2.zsk.model.ZaiFixUpdateModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] バッチ処理起動時に実行される処理を実装
 * <br>[解  説] 在席管理に関する処理を実行します。
 * <br>[備  考]
 *
 * @author JTS
 */
public class ZaisekiBatchListenerImpl implements IBatchListener {

    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(ZaisekiBatchListenerImpl.class);

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
        log__.debug("在席管理バッチ処理開始");

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {

            //在席状況定時一括更新
            ZaiFixUpdateDao fixUpdateDao = new ZaiFixUpdateDao(con);
            ZaiFixUpdateModel fixUpdateMdl = fixUpdateDao.select();
            UDate now = new UDate();
            if (fixUpdateMdl != null) {

                if (fixUpdateMdl.getZfuUpdateKbn() == GSConstZaiseki.FIXED_UPDATE_ON
                        && now.getIntHour() == fixUpdateMdl.getZfuFixUpdateTime()) {
                    CmnUsrInoutDao inoutDao = new CmnUsrInoutDao(con);
                    CmnUsrInoutModel inoutMdl = new CmnUsrInoutModel();
                    inoutMdl.setUioStatus(fixUpdateMdl.getZfuStatus());
                    inoutMdl.setUioBiko(fixUpdateMdl.getZfuMsg());
                    inoutMdl.setUioEdate(now);
                    inoutMdl.setUioEuid(GSConst.SYSTEM_USER_ADMIN);

                    inoutDao.updateAll(inoutMdl);
                }
            }

            commitFlg = true;

            log__.debug("在席管理バッチ処理終了");

        } catch (Exception e) {
            log__.error("在席管理バッチ処理失敗", e);
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
     * <p>5分間隔で実行されるJob
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void do5mBatch(Connection con, IBatchModel param) throws Exception {
    }
}
