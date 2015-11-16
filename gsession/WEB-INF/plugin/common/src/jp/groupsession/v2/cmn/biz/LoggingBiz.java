package jp.groupsession.v2.cmn.biz;

import java.sql.Connection;
import java.sql.SQLException;

import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.config.LoggingConfig;
import jp.groupsession.v2.cmn.dao.base.CmnLogDao;
import jp.groupsession.v2.cmn.model.base.CmnLogConfModel;
import jp.groupsession.v2.cmn.model.base.CmnLogModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] オペレーションLog出力に使用するビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class LoggingBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(LoggingBiz.class);
    /** DBコネクション */
    private Connection con__ = null;

    /**
     * デフォルトコンストラクタ
     */
    public LoggingBiz() {

    }

    /**
     * デフォルトコンストラクタ
     * @param con コネクション
     */
    public LoggingBiz(Connection con) {
        setCon(con);
    }

    /**
     * @return con
     */
    public Connection getCon() {
        return con__;
    }

    /**
     * @param con 設定する con
     */
    public void setCon(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 設定を元にオペレーションログを登録する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param logMdl 登録するログ情報
     * @param domain ドメイン
     */
    public void outPutLog(CmnLogModel logMdl, String domain) {

        //ログ設定を取得
        LoggingConfig logConfig = GroupSession.getResourceManager().getLoggingConfig(domain);

        if (logConfig != null) {
            if (logMdl.getLogPlugin() != null) {
                CmnLogConfModel confMdl = null;

                if (logConfig.getPlugin(logMdl.getLogPlugin()) != null) {
                    confMdl = logConfig.getPlugin(logMdl.getLogPlugin());
                }
                if (confMdl != null) {

                    //ログを出力するか判定する
                    if (__isOutPutLog(logMdl, confMdl)) {

                        boolean commitFlg = false;
                        boolean beforeAutoCommit = false;
                        try {
                            beforeAutoCommit = con__.getAutoCommit();
                            con__.setAutoCommit(false);
                            CmnLogDao dao = new CmnLogDao(con__);
                            dao.insert(logMdl);
                            commitFlg = true;
                        } catch (SQLException e) {
                            //ログ出力時の例外はスローしない
                            log__.error("ログ登録に失敗しました。", e);
                        } finally {
                            try {
                                if (commitFlg) {
                                    con__.commit();
                                } else {
                                    con__.rollback();
                                }

                                if (beforeAutoCommit) {
                                    con__.setAutoCommit(beforeAutoCommit);
                                }
                            } catch (SQLException e) {
                                log__.error("ログ登録に失敗しました。", e);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * ログを出力するか判定する
     * @param logMdl 出力するログモデル
     * @param confMdl ログ設定
     * @return true:出力する false:出力しない
     */
    private boolean __isOutPutLog(CmnLogModel logMdl, CmnLogConfModel confMdl) {
        String level = logMdl.getLogLevel();
        if (level.equals(GSConstLog.LEVEL_ERROR)
                && confMdl.getLgcLevelError() == GSConstLog.LOG_ENABLE) {
            //エラーログは出力する
            return true;
        }
        if (level.equals(GSConstLog.LEVEL_WARN)
                && confMdl.getLgcLevelWarn() == GSConstLog.LOG_ENABLE) {
            //エラーログは出力する
            return true;
        }
        if (level.equals(GSConstLog.LEVEL_INFO)
                && confMdl.getLgcLevelInfo() == GSConstLog.LOG_ENABLE) {
            //エラーログは出力する
            return true;
        }
        if (level.equals(GSConstLog.LEVEL_TRACE)
                && confMdl.getLgcLevelTrace() == GSConstLog.LOG_ENABLE) {
            //エラーログは出力する
            return true;
        }
        return false;
    }

}