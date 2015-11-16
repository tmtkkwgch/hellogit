package jp.groupsession.v2.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.DBUtilFactory;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.background.BatchMasterThread;
import jp.groupsession.v2.cmn.background.IGsBatch;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.base.CmnBatchJobDao;
import jp.groupsession.v2.cmn.model.base.CmnBatchJobModel;
import jp.groupsession.v2.cmn.quartz.AbstractJob;
import jp.groupsession.v2.cmn.quartz.JobException;
import jp.groupsession.v2.man.ManBatchBackupListenerImpl;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 一日おきに実行されるJOB
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class DayJob extends AbstractJob implements IGsBatch {

    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(DayJob.class);

    /** バッチ プログラム名 */
    private static final String PG_NAME = "jp.groupsession.v2.batch.DayJob";

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public DayJob() { }

    /**
     * <p>Jobの実行
     * @param pluginConfig プラグイン情報
     * @throws JobException 例外
     * @throws SQLException SQL実行時例外
     */
    public void execute(PluginConfig pluginConfig) throws JobException,
            SQLException {
        log__.debug("START");
        BatchMasterThread bachMaster = new BatchMasterThread(this, pluginConfig);
        bachMaster.run();
        log__.debug("END");
    }

    /**
     * <p>Jobの実行
     * @param dsKey ドメイン
     * @param pluginConfig プラグイン情報
     * @throws JobException 例外
     * @throws SQLException SQL実行時例外
     */
    public void executeBatch(String dsKey, PluginConfig pluginConfig)
                               throws JobException, SQLException {

        __executeBatch(dsKey, pluginConfig, GSConst.NOT_DO_BATCH);
    }

    /**
     * <br>[機  能] バッチ処理強制実行
     * <br>[解  説]
     * <br>[備  考]
     * @param dsKey ドメイン
     * @param pluginConfig プラグイン情報
     * @throws JobException 例外
     * @throws SQLException SQL実行時例外
     */
    public void executeBatchManual(String dsKey, PluginConfig pluginConfig)
                               throws JobException, SQLException {

        __executeBatch(dsKey, pluginConfig, GSConst.DO_BATCH);
    }

    /**
     * <br>[機  能] バッチ処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param dsKey ドメイン
     * @param pluginConfig プラグイン情報
     * @param doBatchFlg 強制実行フラグ 0:強制実行しない 1:強制実行する
     * @throws JobException 例外
     * @throws SQLException SQL実行時例外
     *
     */
    private void __executeBatch(String dsKey, PluginConfig pluginConfig, int doBatchFlg)
            throws JobException, SQLException {
        Connection con = null;

        UDate now = new UDate();
        log__.info("(" + dsKey + ")" + "バッチジョブ開始します : "
                + now.getStrHour() + ":" + now.getStrMinute() + ":" + now.getStrSecond());

        boolean h2db = (DBUtilFactory.getInstance().getDbType() == GSConst.DBTYPE_H2DB);

            String listenerName = null;
            try {

                con = GroupSession.getConnection(dsKey);

                if (con != null) {

                    if (h2db) {
                        __setBatchQueryTimeout(con);
                    }

                    CmnBatchJobDao batDao = new CmnBatchJobDao(con);
                    CmnBatchJobModel batMdl = batDao.select();

                    int hour = -1;
                    if (batMdl != null) {
                        hour = batMdl.getBatFrDate();
                    }

                    //実行時間の判定(実行予定時間 = 現在時間)
                    if (hour == now.getIntHour() || doBatchFlg == GSConst.DO_BATCH) {
                        GSContext gscontext = getGscontext();

                        //バックアップバッチを実行
                        setBackup(true);

                        CommonBiz cmnBiz = new CommonBiz();
                        IBatchBackupListener[] backupBatchListeners
                            = cmnBiz.getBackupBatchListeners(pluginConfig, con);
                        if (backupBatchListeners.length > 0) {

                            try {
                                for (IBatchBackupListener listener : backupBatchListeners) {
                                    listener.doBeforeBackup(con, gscontext, dsKey);
                                }
                                log__.debug("Backup日次バッチ:事前処理完了");

                                for (IBatchBackupListener listener : backupBatchListeners) {
                                    listener.doBackup(con, gscontext);
                                    log__.debug("Backup日次バッチ:doBackup "
                                            + listener.getClass().getName());
                                    if (listener instanceof ManBatchBackupListenerImpl) {
                                        Thread.sleep(3000);

                                        //データソース、コンテキストの再設定を行う
                                        //DS
                                        JDBCUtil.closeConnection(con);
                                        GroupSession.getResourceManager().resetDataSource(dsKey);
                                        log__.debug("Backup日次バッチ:DataSource 再作成");

                                        //採番
                                        GroupSession.getResourceManager()
                                            .resetCountController(dsKey);
                                        log__.debug("Backup日次バッチ:採番コントローラ 再作成(SAIBAN)");

                                        con = GroupSession.getConnection(dsKey);
                                        if (h2db) {
                                            __setBatchQueryTimeout(con);
                                        }

                                    }
                                }
                            } finally {
                                for (IBatchBackupListener listener : backupBatchListeners) {
                                    try {
                                        listener.doAfterBackup(con, gscontext, dsKey);
                                    } catch (Exception e) {
                                        log__.error("バックアップ処理終了後で例外発生", e);
                                    }
                                }
                                log__.debug("Backup日次バッチ:事後処理完了");
                            }
                        }
                        setBackup(false);

                        //バッチを実行
                        IBatchListener[] batchListeners
                            = cmnBiz.getBatchListeners(pluginConfig, con);
                        IBatchModel ibm = new IBatchModel();
                        ibm.setGsContext(gscontext);
                        ibm.setDomain(dsKey);
                        for (IBatchListener listener : batchListeners) {
                            log__.debug("日次バッチ実行対象" + listener.getClass().getName());
                            try {
                                listener.doDayBatch(con, ibm);
                            } catch (Exception e) {
                                log__.error("日次バッチ処理の実行に失敗 : "
                                        + NullDefault.getString(listenerName, ""), e);
                            }
                        }

                        GsMessage gsMsg = new GsMessage();
                        String message = gsMsg.getMessage("cmn.batch.day.end", null);
                        String value = "";
                        if (CommonBiz.isMultiAP() && CommonBiz.getApNumber() > 0) {
                            value = "AP" + CommonBiz.getApNumber();
                        }

                        //ログ出力
                        cmnBiz.outPutBatchLog(PG_NAME, con, message,
                                            GSConstLog.LEVEL_INFO, value, dsKey);
                    }
                }
            } catch (ClassNotFoundException e) {
                log__.fatal("指定された日次バッチ実装クラスが存在しない : "
                        + NullDefault.getString(listenerName, ""), e);
                throw new JobException("指定された日次バッチ実装クラスが存在しない : "
                        + NullDefault.getString(listenerName, ""), e);
            } catch (IllegalAccessException e) {
                log__.fatal("指定された日次バッチ実装クラスの取得に失敗 : "
                        + NullDefault.getString(listenerName, ""), e);
                throw new JobException("指定された日次バッチ実装クラスの取得に失敗 : "
                        + NullDefault.getString(listenerName, ""), e);
            } catch (InstantiationException e) {
                log__.fatal("指定された日次バッチ実装クラスの取得に失敗 : "
                        + NullDefault.getString(listenerName, ""), e);
                throw new JobException("指定された日次バッチ実装クラスの取得に失敗 : "
                        + NullDefault.getString(listenerName, ""), e);
            } catch (Exception e) {
                log__.fatal("日次バッチ処理の実行に失敗 : "
                        + NullDefault.getString(listenerName, ""), e);
                //throw new JobException("日次バッチ処理の実行に失敗", e);
                throw new JobException("日次バッチ処理の実行に失敗 : "
                        + NullDefault.getString(listenerName, ""), e);
            } catch (Throwable e) {
                log__.fatal("日次バッチ処理の実行に失敗: "
                        + NullDefault.getString(listenerName, ""), e);
                throw new JobException("日次バッチ処理の実行に失敗 : "
                        + NullDefault.getString(listenerName, ""), e);
            } finally {
                setBackup(false);
                if (h2db) {
                    __setNormalQueryTimeout(con);
                }
                JDBCUtil.closeConnection(con);
            }
    }

    /**
     * <br>[機  能] QUERY_TIMEOUT を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     * @param queryTimeout QUERY_TIMEOUT
     * @throws SQLException SQL実行時例外
     */
    private void __setQueryTimeout(Connection con, int queryTimeout) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("set QUERY_TIMEOUT ?;");
            sql.addIntValue(queryTimeout);
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            pstmt.executeUpdate();

        } finally {
            JDBCUtil.closePreparedStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] QUERY_TIMEOUTにQUERY_TIMEOUT_BATCHを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     * @throws SQLException SQL実行時例外
     */
    private void __setBatchQueryTimeout(Connection con) throws SQLException {
        int queryTimeout = 0;
        ResourceBundle optionResource = ResourceBundle.getBundle("connectOption");
        Enumeration<String> keys = optionResource.getKeys();
        boolean undefined = true;
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (key.toUpperCase().equals("QUERY_TIMEOUT_BATCH")) {
                undefined = false;
                queryTimeout = Integer.parseInt(optionResource.getString(key));
                break;
            }
        }
        if (undefined) {
            queryTimeout = 1800000;
        }
        __setQueryTimeout(con, queryTimeout);

    }

    /**
     * <br>[機  能] 標準のQUERY_TIMEOUTを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     * @throws SQLException SQL実行時例外
     */
    private void __setNormalQueryTimeout(Connection con) throws SQLException {
        int queryTimeout = 0;
        ResourceBundle optionResource = ResourceBundle.getBundle("connectOption");
        Enumeration<String> keys = optionResource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (key.toUpperCase().equals("QUERY_TIMEOUT")) {
                queryTimeout = Integer.parseInt(optionResource.getString(key));
                break;
            }
        }
        __setQueryTimeout(con, queryTimeout);
    }
}
