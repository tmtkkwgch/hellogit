package jp.groupsession.v2.man;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.sjts.util.cache.FileCache;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.batch.IBatchModel;
import jp.groupsession.v2.cmn.DBUtilFactory;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.dao.base.CmnLhisBatchConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnLogDao;
import jp.groupsession.v2.cmn.dao.base.CmnLogDelConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnLoginHistoryDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmCountDao;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnLhisBatchConfModel;
import jp.groupsession.v2.cmn.model.base.CmnLogDelConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] バッチ処理起動時に実行される処理を実装
 * <br>[解  説] メイン機能に関連した処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class ManBatchListenerImpl implements IBatchListener {

    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(ManBatchListenerImpl.class);

    /**
     * <p>日次バッチ起動時に実行される
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void doDayBatch(Connection con, IBatchModel param) throws Exception {

        log__.debug("論理削除済みバイナリー情報のファイル削除、DBの物理削除処理を行う");

        //アプリケーションのルートパスを取得
        String rootPath = "";
        GSContext gscontext = param.getGsContext();
        Object pathObj = gscontext.get(GSContext.APP_ROOT_PATH);
        if (pathObj != null) {
            rootPath = (String) pathObj;
        }
        //テンポラリディレクトリをクリーン
        Object tmp
             = GroupSession.getResourceManager().getTempPath(param.getDomain());

        if (tmp != null) {
            if (tmp instanceof String) {
                String path = (String) tmp;
                //ファイルを削除
                File tempDir = new File(path);
                if (tempDir.exists()) {
                    IOTools.deleteDir(path);
                    IOTools.isDirCheck(path, true);
                }
            }
        }

        try {
            if (DBUtilFactory.getInstance().getDbType() == GSConst.DBTYPE_POSTGRES) {
                log__.info("ファイルキャッシュのリセット開始");
                FileCache.reset();
                log__.info("ファイルキャッシュのリセット終了");
            }
        } catch (Throwable e) {
            log__.error("ファイルキャッシュのリセットに失敗", e);
        }

        try {

            con.setAutoCommit(false);

            //論理削除済みのバイナリー情報を取得する
            CmnBinfDao cbDao = new CmnBinfDao(con);
            List < CmnBinfModel > cbList = cbDao.getDeleteFile();

            //バイナリーSIDリスト
            String[] binList = new String[cbList.size()];

            log__.debug("ファイルを削除");
            CommonBiz biz = new CommonBiz();
            for (int i = 0; i < cbList.size(); i++) {
                CmnBinfModel cbMdl = cbList.get(i);

                //ファイルシステムより添付ファイルを削除する。
                biz.deleteFile(cbMdl, rootPath);

                //バイナリーSIDリストに追加
                binList[i] = String.valueOf(cbMdl.getBinSid());
            }

            log__.debug("バイナリーSIDを複数指定し、バイナリー情報を削除する");
            biz.deleteBinInf(con, binList);

            con.commit();

            log__.debug("ログイン履歴削除処理開始");

            //ログイン履歴自動削除設定を取得
            CmnLhisBatchConfDao batchDao = new CmnLhisBatchConfDao(con);
            CmnLhisBatchConfModel batchMdl = batchDao.select();

            //「自動削除設定が設定済」かつ「削除区分 = 自動で削除する」の場合
            if (batchMdl != null && batchMdl.getCbcAdlKbn() == GSConstMain.LHIS_DELKBN_ON) {

                int adlYear = batchMdl.getCbcAdlYear();
                int adlMonth = batchMdl.getCbcAdlMonth();

                //削除する境界の日付を設定する
                UDate delUd = new UDate();
                log__.debug("現在日 = " + delUd.getDateString());
                log__.debug("削除条件 経過年 = " + adlYear);
                log__.debug("削除条件 経過年 = " + adlMonth);

                delUd.addYear((adlYear * -1));
                delUd.addMonth((adlMonth * -1));
                delUd.setHour(GSConstMain.DAY_END_HOUR);
                delUd.setMinute(GSConstMain.DAY_END_MINUTES);
                delUd.setSecond(GSConstMain.DAY_END_SECOND);
                delUd.setMilliSecond(GSConstMain.DAY_END_MILLISECOND);

                log__.debug("削除境界線(この日以前のデータを削除) = " + delUd.getTimeStamp());

                CmnLoginHistoryDao historyDao = new CmnLoginHistoryDao(con);
                int count = historyDao.delete(delUd);

                con.commit();

                log__.debug("ログイン履歴" + count + "件を削除");
            }

            log__.debug("オペレーションログ自動削除処理開始");

            CmnLogDelConfDao logDelDao = new CmnLogDelConfDao(con);
            CmnLogDelConfModel logdelMdl = logDelDao.getOpeLogDelConf();

            if (logdelMdl.getLdcAdlKbn() == GSConstMain.OPE_LOG_CONF) {
                //オペレーションログ自動削除設定を実行
                int autoDelYear = logdelMdl.getLdcAdlYear();
                int autoDelMonth = logdelMdl.getLdcAdlMonth();
                CmnLogDao logDao = new CmnLogDao(con);
                UDate now = new UDate();

                //削除する境界の日付を設定する
                UDate delUdate = now.cloneUDate();
                log__.debug("現在日 = " + delUdate.getDateString());
                log__.debug("削除条件 経過年 = " + autoDelYear);
                log__.debug("削除条件 経過年 = " + autoDelMonth);

                delUdate.addYear((autoDelYear * -1));
                delUdate.addMonth((autoDelMonth * -1));
                delUdate.setHour(GSConstMain.DAY_END_HOUR);
                delUdate.setMinute(GSConstMain.DAY_END_MINUTES);
                delUdate.setSecond(GSConstMain.DAY_END_SECOND);
                delUdate.setMilliSecond(GSConstMain.DAY_END_MILLISECOND);

                log__.debug("削除境界線(この日以前のログデータを削除) = " + delUdate.getTimeStamp());
                int logDelCount = logDao.delete(delUdate);
                log__.debug("オペレーションログ削除件数は" + logDelCount + "件です。");
                con.commit();
            }

        } catch (SQLException e) {
            log__.error("SQLException", e);
            JDBCUtil.rollback(con);
            throw e;
        } catch (IOToolsException e) {
            log__.error("IOToolsException", e);
            JDBCUtil.rollback(con);
            throw e;
        }
    }

    /**
     * <p>1時間間隔で実行されるJob
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @throws Exception バッチ処理実行時例外
     */
    public void doOneHourBatch(Connection con, IBatchModel param) throws Exception {
        //期限切れファイルキャッシュを削除
        try {
            FileCache.expiredCache();
        } catch (Exception e) {
            log__.error("期限切れファイルキャッシュの削除に失敗", e);
        }

        //ユーザ件数の再集計
        boolean commit = false;
        try {
            CmnUsrmCountDao usrCountDao = new CmnUsrmCountDao(con);
            UDate date = new UDate();
            usrCountDao.updateUserCount(date);
            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.error("ユーザ件数の再集計に失敗", e);
            throw e;
        } finally {
            if (!commit) {
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