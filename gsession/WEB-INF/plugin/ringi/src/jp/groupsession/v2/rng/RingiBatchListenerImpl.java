package jp.groupsession.v2.rng;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.batch.IBatchModel;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.dao.RngAutodeleteDao;
import jp.groupsession.v2.rng.model.RngAutodeleteModel;
import jp.groupsession.v2.rng.model.RngDeleteModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] バッチ処理起動時に実行される処理を実装
 * <br>[解  説] 施設予約についての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class RingiBatchListenerImpl implements IBatchListener {

    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(RingiBatchListenerImpl.class);

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

        log__.info("稟議 日次バッチ処理開始");

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {
            //稟議 自動削除条件を取得
            RngAutodeleteDao autoDelDao = new RngAutodeleteDao(con);
            RngAutodeleteModel autoDelMdl = autoDelDao.getData();

            if (autoDelMdl != null) {
                List<RngDeleteModel> delList = new ArrayList<RngDeleteModel>();
                //申請中
                __addDeleteModel(delList,
                        autoDelMdl.getRadPendingKbn(),
                        RngDeleteModel.DELTYPE_PENDING,
                        autoDelMdl.getRadPendingYear(),
                        autoDelMdl.getRadPendingMonth(),
                        autoDelMdl.getRadPendingDay());

                //完了
                __addDeleteModel(delList,
                        autoDelMdl.getRadCompleteKbn(),
                        RngDeleteModel.DELTYPE_COMPLETE,
                        autoDelMdl.getRadCompleteYear(),
                        autoDelMdl.getRadCompleteMonth(),
                        autoDelMdl.getRadCompleteDay());

                //草稿
                __addDeleteModel(delList,
                        autoDelMdl.getRadDraftKbn(),
                        RngDeleteModel.DELTYPE_DRAFT,
                        autoDelMdl.getRadDraftYear(),
                        autoDelMdl.getRadDraftMonth(),
                        autoDelMdl.getRadDraftDay());

                RngBiz rngBiz = new RngBiz(null);
                rngBiz.deleteRngData(con, delList, 0);
                con.commit();
            }

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("稟議 日次バッチ処理失敗", e);
            throw e;
        } finally {
            log__.info("稟議 日次バッチ処理終了");

            if (!commitFlg) {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * <br>[機  能] 稟議削除条件Modelの追加を行う
     * <br>[解  説] 削除区分 = 削除する の場合のみ追加する
     * <br>[備  考]
     * @param delList 稟議削除条件List
     * @param delKbn 削除区分
     * @param delType 削除種別
     * @param year 年
     * @param month 月
     * @param day 日
     */
    private void __addDeleteModel(List<RngDeleteModel> delList,
                                int delKbn, int delType,
                                int year, int month, int day) {

        if (delKbn == RngConst.RAD_KBN_DELETE) {
            RngDeleteModel delMdl = new RngDeleteModel();
            delMdl.setDelType(delType);
            delMdl.setDelYear(year);
            delMdl.setDelMonth(month);
            delMdl.setDelDay(day);

            delList.add(delMdl);
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