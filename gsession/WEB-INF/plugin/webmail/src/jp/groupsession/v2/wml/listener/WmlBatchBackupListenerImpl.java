package jp.groupsession.v2.wml.listener;

import java.sql.Connection;

import jp.groupsession.v2.batch.IBatchBackupListener;
import jp.groupsession.v2.wml.pop3.Pop3Server;

/**
 * <br>[機  能] バックアップ処理時に実行されるリスナーを実装
 * <br>[解  説] 自動バックアップについての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class WmlBatchBackupListenerImpl implements IBatchBackupListener {

    /**
     * <p>バックアップ処理の前に実行されるJob
     * @param con DBコネクション
     * @param param バックアップ前処理で使用する情報
     * @param domain ドメイン
     * @throws Exception バックアップ前処理実行時例外
     */
    public void doBeforeBackup(Connection con, Object param, String domain) throws Exception {
        Pop3Server.stopReceive();

        while (Pop3Server.existReceiveAccount(domain)) {
            Thread.sleep(3000);
        }
    }

    /**
     * <p>バックアップ処理
     * @param con DBコネクション
     * @param param バックアップ処理時に使用する情報
     * @throws Exception バックアップ処理実行時例外
     */
    public void doBackup(Connection con, Object param) throws Exception {
    }

    /**
     * <p>バックアップ処理終了後に実行されるJob
     * @param con DBコネクション
     * @param param バックアップ終了後処理で使用する情報
     * @param domain ドメイン
     * @throws Exception バックアップ終了後処理実行時例外
     */
    public void doAfterBackup(Connection con, Object param, String domain) throws Exception {
        Pop3Server.startReceive();
    }
}