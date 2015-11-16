package jp.groupsession.v2.wml.pop3;

import java.io.File;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.server.IServer;
import jp.groupsession.v2.wml.ReceiveServer;
import jp.groupsession.v2.wml.batch.WmlReceiveBatch;
import jp.groupsession.v2.wml.model.WmlReceiveServerModel;
import jp.groupsession.v2.wml.model.base.WmlAccountRcvsvrModel;
import jp.groupsession.v2.wml.pop3.model.Pop3ReceiveResultModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3Store;

/**
 * <br>[機  能] POPサーバ メールの受信を行う
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Pop3Server implements ReceiveServer, IServer {

    /** SSL Factoryクラス */
    public static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Pop3Server.class);

    /** シャットダウン */
    private static boolean shutdown__ = false;

    /** 接続 */
    private Store store__ = null;
    /** デフォルトフォルダ */
    private Folder defaultFolder__ = null;
    /** フォルダ */
    private Folder folder__ = null;

    /**
     * <br>[機  能] POP3サーバ接続情報のチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param model  メール受信サーバの接続情報
     * @return true: 正常 false: 不正
     * @throws Exception メールサーバの接続closeに失敗
     */
    public boolean checkPopServer(WmlReceiveServerModel model)
    throws Exception {
        boolean result = false;

        try {
            openStore(model);
            openFolder(model);

            result = true;

        } catch (Exception e) {
            log__.info("POP3サーバ接続に失敗" , e);
        } finally {
            close();
        }

        return result;
    }

    /**
     * <br>[機  能] POP3サーバへ接続し、メールを受信する
     * <br>[解  説]
     * <br>[備  考]
     * @param model  メール受信サーバの接続情報
     * @param receive メール受信クラス メール情報の登録に使用する
     * @param msgResource MessageResources
     * @param ds DataSource
     * @param domain ドメイン
     * @throws Exception メールの受信に失敗
     */
    public synchronized void receiveMessage(WmlReceiveServerModel model, Pop3Receive receive,
                                   MessageResources msgResource, DataSource ds, String domain)
    throws Exception {

        long timerv = System.currentTimeMillis();

        try {
            openStore(model);
            openFolder(model);
            int msgCount = folder__.getMessageCount();
            int newMsgCount = 0;
            log__.info("アカウント[" + model.getAccountName() + "] メール取得件数 : " + msgCount);

            //取得件数0の場合
            if (msgCount <= 0) {
                log__.info("メール取得件数 : 0件のため、アカウント[" + model.getAccountName() + "] の受信処理を終了します。");
                receive.insertRsvServerData(0, 0);

                //受信済みメールUIDLを削除
                if (model.isDelReceive()) {
                    receive.deleteUidlAll(model.getWacSid(), model.getAccountString());
                }
                return;
            }

            //受信サイズ比較による処理中止の判定
            boolean insertFlg = true;
            if (!model.isReReceive()) {
                boolean rcvCompare = __compareRsvServerData(receive, folder__, model, ds);
                if (rcvCompare) {
                    log__.info("アカウント[" + model.getAccountName()
                            + "] のメール件数、サイズが前回受信時と同じなので受信処理を中止します。");
                    insertFlg = false;
                }
            }

            //現在のディスク使用量を設定
            receive.initUseDiskSize();

            Pop3ReceiveResultModel receiveResultMdl = null;
            boolean errMailFlg = false;
            while (insertFlg) {

                //メールを全部取得する
                Message[] messages = folder__.getMessages();
                FetchProfile fp = new FetchProfile();
                fp.add(UIDFolder.FetchProfileItem.UID);
                fp.add(FetchProfile.Item.CONTENT_INFO);
                folder__.fetch(messages, fp);

                Set<Integer> insertMailIndex = Collections.synchronizedSet(new HashSet<Integer>());
                int insertCount = 0;
                Message message = null;

                //受信対象のメッセージIDを取得
                String lindexPath = GroupSession.getResourceManager().getTempPath(domain);
                lindexPath = IOTools.setEndPathChar(lindexPath)
                        + "webmail" + File.separator +  "lucene_index" + File.separator;
                List<String> msgIdList
                    = receive.getReceiveMessageId((POP3Folder) folder__, messages,
                                                model, ds, lindexPath);

                //登録処理開始
                int msgIndex = 1;
                String uid = null;
                boolean mailCommit = false;
                for (; msgIndex <= msgCount; msgIndex++) {

                    //最後の受信処理の場合、必ずコミットするようにする。
                    boolean lastFlg = msgIndex == msgCount;

                    if (shutdown__) {
                        break;
                    }

                    message = messages[msgIndex - 1];
                    uid = getUID(message);
                    if (msgIdList.indexOf(uid) >= 0) {
                        msgIdList.remove(uid);

                        //ディスク容量チェック
                        if (model.isDiskLimit()
                                && model.getDiskLimitSizeForByte()
                                < (receive.getUseDiskSize() + message.getSize())) {
                            log__.info("アカウントのディスク使用量: "
                                    + receive.getUseDiskSize());
                            log__.info("アカウントのディスク使用上限: "
                                    + model.getDiskLimitSizeForByte());
                            log__.info("受信したメールのサイズ: " + message.getSize());
                            log__.warn(model.getAccountName()
                                    + "のディスク容量制限を超えたため、メールの受信を終了します。");
                            errMailFlg = true;

                            // ショートメール通知
                            receive.sendDiskLimitMail(model.getWacSid(), null, msgResource, domain);

                            break;
                        }

                        //メールを保存
                        receiveResultMdl = receive._insertMailData(
                                domain, (MimeMessage) message, uid, msgResource, lastFlg);
                        mailCommit = receiveResultMdl.isCommitFlg();
                        if (receiveResultMdl.isErrMailFlg()) {
                            errMailFlg = true;
                        }
                        insertMailIndex.add(msgIndex);

                        //コミットした場合
                        if (mailCommit) {
                            __mailDelete(insertMailIndex, model);
                            insertMailIndex.clear();
                            if (!shutdown__) {
                                //コネクションの解放 & 再取得
                                receive.reConnect(ds);
                            }
                        }

                        newMsgCount++;
                        insertCount++;
                        if (insertCount >= model.getMaxReceiveMailCount()) {
                            break;
                        }
                    }

                    uid = null;
                    message = null;
                }

                if (!errMailFlg && newMsgCount < model.getMaxReceiveMailCount()) {
                    int mailCnt = folder__.getMessageCount();
                    int accountSize = ((POP3Folder) folder__).getSize();
                    receive.insertRsvServerData(mailCnt, accountSize);
                }

                insertFlg = false;
//                closeFolder();
//                System.gc();
//                openFolder(model);
                msgCount = folder__.getMessageCount();

//                if (mailCommit) {
//                    __mailDelete(insertMailIndex, model);
//                }
            }

            log__.info("アカウント[" + model.getAccountName() + "] の受信処理完了 "
                        + (System.currentTimeMillis() - timerv));
            log__.info("アカウント[" + model.getAccountName() + "] 新着メール件数 = "
                        + newMsgCount);

        } catch (Throwable e) {
            log__.error("POP3サーバからのメール受信時に例外発生 アカウント[" + model.getAccountName() + "]", e);
            throw new Exception("POP3サーバからのメール受信時に例外発生 アカウント[" + model.getAccountName() + "]", e);
        } finally {
            close();
        }

    }

    /**
     * <br>[機  能] 指定したメッセージのUIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param msg メッセージ
     * @return UID
     * @throws MessagingException UIDの取得に失敗
     */
    public synchronized String getUID(Message msg) throws MessagingException {
        return new String(((POP3Folder) folder__).getUID(msg));
    }

    /**
     * <br>[機  能] メールサーバへの接続を切断する
     * <br>[解  説]
     * <br>[備  考]
     * @throws Exception メールサーバへの接続切断に失敗
     */
    public synchronized void close() throws Exception {
        try {
            closeFolder();
        } catch (Throwable e) {
            log__.error("Folderのcloseに失敗", e);
        }
        closeStore();
    }

    /**
     * <br>[機  能] Storeのopenを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param model 接続情報
     * @throws MessagingException Storeのopenに失敗
     */
    public synchronized void openStore(WmlReceiveServerModel model)
    throws MessagingException {

        Properties prop = new Properties();

        prop.setProperty("mail.pop3.connectiontimeout",
                        String.valueOf(model.getReceiveConnectTimeout() * 1000));
        prop.setProperty("mail.pop3.timeout", String.valueOf(model.getReceiveTimeout() * 1000));

        log__.info("********** host = " + model.getHost());
        log__.info("********** port = " + model.getPort());
        log__.info("********** user = " + model.getUser());
        log__.info("********** pass = " + model.getPassword());

        if (model.isApop()) {
            prop.put("mail.pop3.apop.enable", "true");
            log__.info("********** APOPを使用する");
        }
        if (model.isSsl()) {
            prop.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
            prop.setProperty("mail.pop3.socketFactory.fallback", "false");
            prop.setProperty("mail.pop3.port", String.valueOf(model.getPort()));
            prop.setProperty("mail.pop3.socketFactory.port", String.valueOf(model.getPort()));
            log__.info("********** SSLを使用する");
        }

        Session session = Session.getInstance(prop, null);

        store__ = session.getStore("pop3");
        store__.connect(model.getHost(), model.getPort(),
                        model.getUser(), model.getPassword());
    }

    /**
     * <br>[機  能] Storeのopenを行う
     * <br>[解  説]
     * <br>[備  考]
     * @throws MessagingException Storeのopenに失敗
     */
    public synchronized void closeStore()
    throws MessagingException {
        if (store__ != null && store__.isConnected()) {
            ((POP3Store) store__).close();
        }
        store__ = null;
    }

    /**
     * <br>[機  能] 受信フォルダのopenを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param model 接続情報
     * @throws MessagingException 受信フォルダのOPENに失敗
     */
    public synchronized void openFolder(WmlReceiveServerModel model)
    throws MessagingException {
        defaultFolder__ = store__.getDefaultFolder();
        folder__ = defaultFolder__.getFolder("INBOX");

        if (model.isDelReceive()) {
            folder__.open(Folder.READ_WRITE);
        } else {
            folder__.open(Folder.READ_ONLY);
        }
    }

    /**
     * <br>[機  能] 受信フォルダのcloseを行う
     * <br>[解  説]
     * <br>[備  考]
     * @throws MessagingException 受信フォルダのcloseに失敗
     */
    public synchronized void closeFolder()
    throws MessagingException {
        if (folder__ != null && folder__.isOpen()) {
            folder__.close(true);
        }
        folder__ = null;

        if (defaultFolder__ != null && defaultFolder__.isOpen()) {
            defaultFolder__.close(true);
        }
        defaultFolder__ = null;
    }

    /**
     * <br>[機  能] メールの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param insertMailIndex 登録処理済みメールのindex
     * @param model 接続情報
     * @throws MessagingException メールの削除に失敗
     */
    private void __mailDelete(Set<Integer> insertMailIndex,
                                WmlReceiveServerModel model)
    throws MessagingException {

        if (model.isDelReceive()) {
            for (Integer msgNum : insertMailIndex) {
                try {
                    Message msg = folder__.getMessage(msgNum.intValue());
                    msg.setFlag(Flags.Flag.DELETED, true);
                } catch (Exception e) {
                    log__.warn("メールの削除に失敗", e);
                }
            }
        }

    }

    /**
     * <br>[機  能] サーバを起動する
     * <br>[解  説]
     * <br>[備  考]
     */
    public void start() {
        shutdown__ = false;
    }

    /**
     * <br>[機  能]サーバを終了する
     * <br>[解  説]
     * <br>[備  考]
     */
    public void shutdown() {
        shutdown__ = true;
    }

    /**
     * <br>[機  能] 受信処理を開始する
     * <br>[解  説]
     * <br>[備  考]
     */
    public static void startReceive() {
        shutdown__ = false;
    }

    /**
     * <br>[機  能] 受信処理を終了する
     * <br>[解  説]
     * <br>[備  考]
     */
    public static void stopReceive() {
        shutdown__ = true;
    }

    /**
     * <br>[機  能] サーバ終了処理が完了したかを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @return true:完了 false:終了処理実行中
     */
    public boolean isShutdownEnd() {

        boolean result = true;
        try {
            String[] domainList = GroupSession.getResourceManager().getDomain();
            for (String domain : domainList) {
                if (!existReceiveAccount(domain)) {
                    result = false;
                    break;
                }
            }
        } catch (Exception e) {
            log__.error("WEBメール サーバ終了処理でエラー発生", e);
            result = false;
        }

        return result;
    }

    /**
     * <br>[機  能] 受信処理中のアカウントが存在するかを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param domain ドメイン
     * @return true:受信中のアカウントあり false:受信中のアカウントなし
     */
    public static boolean existReceiveAccount(String domain) {
        return WmlReceiveBatch.getThreadCount(domain) > 0;
    }

    /**
     * <br>[機  能] ディスク使用量、メール件数の比較を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param receive メール受信クラス
     * @param folder メールサーバの受信メール格納フォルダ
     * @param receiveServerData メール受信サーバの接続情報
     * @param ds DataSource
     * @return true:変化なし false:異なる
     * @throws MessagingException 受信メール格納フォルダの容量取得に失敗
     * @throws SQLException SQL実行時例外
     */
    private boolean __compareRsvServerData(Pop3Receive receive, Folder folder,
                                                            WmlReceiveServerModel receiveServerData,
                                                            DataSource ds)
    throws MessagingException, SQLException {
        int mailCnt = folder__.getMessageCount();
        int accountSize = ((POP3Folder) folder__).getSize();

        int beforeMailCnt = 0;
        int beforeSize = 0;
        WmlAccountRcvsvrModel svrData = receive.getRsvServerData();
        if (svrData != null) {
            beforeMailCnt = (int) svrData.getWrsReceiveCount();
            beforeSize = (int) svrData.getWrsReceiveSize();
        }

        boolean result = mailCnt == beforeMailCnt && accountSize == beforeSize;
        if (result && receiveServerData.getReceiveRcvsvrCheckTime() > 0) {
            if (svrData == null || svrData.getWrsEdate() == null) {
                result = false;
            } else {
                UDate now = new UDate();
                now.addMinute(-1 * receiveServerData.getReceiveRcvsvrCheckTime());
                result = svrData.getWrsEdate().compare(svrData.getWrsEdate(), now) == UDate.SMALL;
                if (!result) {
                    receive.updateRsvServerDate(ds);
                }
                now = null;
            }
        }

        svrData = null;

        return result;
    }
}