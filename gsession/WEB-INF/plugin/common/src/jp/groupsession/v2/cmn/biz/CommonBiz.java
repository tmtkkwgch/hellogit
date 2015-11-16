package jp.groupsession.v2.cmn.biz;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.encryption.EncryptionException;
import jp.co.sjts.util.encryption.Sha;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.lang.ClassUtil;
import jp.co.sjts.util.ldap.LdapConst;
import jp.groupsession.v2.batch.IBatchBackupListener;
import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.cmn.ConfigBundle;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.GsResourceBundle;
import jp.groupsession.v2.cmn.IGsResourceManager;
import jp.groupsession.v2.cmn.ITempFileUtil;
import jp.groupsession.v2.cmn.ITopMenuInfo;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.config.AdminSettingInfo;
import jp.groupsession.v2.cmn.config.LoggingConfig;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.config.PortletInfo;
import jp.groupsession.v2.cmn.config.PrivateSettingInfo;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnContmDao;
import jp.groupsession.v2.cmn.dao.base.CmnLogConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnPluginAdminDao;
import jp.groupsession.v2.cmn.dao.base.CmnPluginControlDao;
import jp.groupsession.v2.cmn.dao.base.CmnPluginControlMemberDao;
import jp.groupsession.v2.cmn.dao.base.CmnPositionDao;
import jp.groupsession.v2.cmn.dao.base.CmnTdfkDao;
import jp.groupsession.v2.cmn.dao.base.CmnTdispDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.CmnContmModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.TempFileModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnInfoMsgModel;
import jp.groupsession.v2.cmn.model.base.CmnLogConfModel;
import jp.groupsession.v2.cmn.model.base.CmnLogModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlModel;
import jp.groupsession.v2.cmn.model.base.CmnPositionModel;
import jp.groupsession.v2.cmn.model.base.CmnTdfkModel;
import jp.groupsession.v2.cmn.model.base.CmnTdispModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmModel;
import jp.groupsession.v2.cmn.model.base.WmlMailFileModel;
import jp.groupsession.v2.cmn.model.base.WmlTempfileModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.MainInfoMessage;
import jp.groupsession.v2.man.MainInfoMessageModel;
import jp.groupsession.v2.man.man320.Man320Biz;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;
import jp.groupsession.v2.usr.GSPassword;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] GroupSession全体で使用する共通ビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class CommonBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(CommonBiz.class);

    /**
     * <br>[機  能] 登録に使用する側のコンボで選択中のメンバーをメンバーリストから削除する
     *
     * <br>[解  説] 登録に使用する側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で削除矢印ボタンをクリックした場合、
     *              登録に使用する側のコンボで選択中の値(deleteSelectSid)をメンバーリストから削除する。
     *
     * <br>[備  考] 登録に使用する側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param deleteSelectSid 登録に使用する側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 削除済みのメンバーリスト
     */
    public String[] getDeleteMember(String[] deleteSelectSid, String[] memberSid) {

        if (deleteSelectSid == null) {
            return memberSid;
        }
        if (deleteSelectSid.length < 1) {
            return memberSid;
        }

        if (memberSid == null) {
            memberSid = new String[0];
        }

        //削除後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < memberSid.length; i++) {
            boolean setFlg = true;

            for (int j = 0; j < deleteSelectSid.length; j++) {
                if (memberSid[i].equals(deleteSelectSid[j])) {
                    setFlg = false;
                    break;
                }
            }

            if (setFlg) {
                list.add(memberSid[i]);
            }
        }

        log__.debug("削除後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] 登録に使用する側のコンボで選択中のメンバーをメンバーリストから削除する
     *
     * <br>[解  説] 登録に使用する側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で削除矢印ボタンをクリックした場合、
     *              登録に使用する側のコンボで選択中の値(deleteSelectSid)をメンバーリストから削除する。
     *
     * <br>[備  考] 登録に使用する側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param deleteSelectSid 登録に使用する側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @param splitFlg true:区切り文字を含む false:含まない
     * @return 削除済みのメンバーリスト
     */
    public String[] getDeleteMemberSplitKey(String[] deleteSelectSid,
                                             String[] memberSid,
                                             boolean splitFlg) {

        if (deleteSelectSid == null) {
            return memberSid;
        }
        if (deleteSelectSid.length < 1) {
            return memberSid;
        }

        if (memberSid == null) {
            memberSid = new String[0];
        }

        String[] spDelSidList = null;
        if (deleteSelectSid != null && deleteSelectSid.length > 0) {

            int idx = 0;
            spDelSidList = new String[deleteSelectSid.length];

            for (String hdn : deleteSelectSid) {

                String[] splitStr = hdn.split(GSConst.GSESSION2_ID);
                spDelSidList[idx] = String.valueOf(splitStr[0]);
                idx += 1;
            }
        }

        String[] spMemSidList = null;

        if (memberSid != null && memberSid.length > 0) {

            int idx = 0;
            spMemSidList = new String[memberSid.length];

            for (String hdn : memberSid) {

                String[] splitStr = hdn.split(GSConst.GSESSION2_ID);
                spMemSidList[idx] = String.valueOf(splitStr[0]);
                idx += 1;
            }
        }

        //削除後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        if (spMemSidList != null) {
            for (int i = 0; i < spMemSidList.length; i++) {
                boolean setFlg = true;

                for (int j = 0; j < spDelSidList.length; j++) {
                    if (spMemSidList[i].equals(spDelSidList[j])) {
                        setFlg = false;
                        break;
                    }
                }

                if (setFlg) {

                    if (splitFlg) {
                        list.add(
                                memberSid[i]
                              + GSConst.GSESSION2_ID);
                    } else {
                        list.add(memberSid[i]);
                    }
                }
            }
        }

        log__.debug("削除後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] 追加側のコンボで選択中のメンバーをメンバーリストに追加する
     *
     * <br>[解  説] 画面右側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で追加矢印ボタンをクリックした場合、
     *              追加側のコンボで選択中の値(addSelectSid)をメンバーリストに追加する。
     *
     * <br>[備  考] 追加側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param addSelectSid 追加側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 追加済みのメンバーリスト
     */
    public String[] getAddMember(String[] addSelectSid, String[] memberSid) {

        if (addSelectSid == null) {
            return memberSid;
        }
        if (addSelectSid.length < 1) {
            return memberSid;
        }


        //追加後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        if (memberSid != null) {
            for (int j = 0; j < memberSid.length; j++) {
                if (ValidateUtil.isNumber(memberSid[j])) {
                    if (Integer.parseInt(memberSid[j]) >= 0) {
                        list.add(memberSid[j]);
                    }
                }
            }
        }

        for (int i = 0; i < addSelectSid.length; i++) {
            if (ValidateUtil.isNumber(addSelectSid[i])) {
                if (Integer.parseInt(addSelectSid[i]) >= 0) {
                    list.add(addSelectSid[i]);
                }
            }
        }

        log__.debug("追加後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] 追加側のコンボで選択中のメンバーをメンバーリストに追加する
     *
     * <br>[解  説] 画面右側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で追加矢印ボタンをクリックした場合、
     *              追加側のコンボで選択中の値(addSelectSid)をメンバーリストに追加する。
     *
     * <br>[備  考] 追加側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param addSelectSid 追加側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 追加済みのメンバーリスト
     */
    public String[] getAddMemberSplitKey(String[] addSelectSid, String[] memberSid) {

        if (addSelectSid == null) {
            return memberSid;
        }
        if (addSelectSid.length < 1) {
            return memberSid;
        }

        //追加後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        if (memberSid != null) {
            for (int j = 0; j < memberSid.length; j++) {
                list.add(memberSid[j]);
            }
        }

        for (int i = 0; i < addSelectSid.length; i++) {
            if (Integer.parseInt(addSelectSid[i]) >= 0) {
                list.add(
                        addSelectSid[i]
                       + GSConst.GSESSION2_ID);
            }
        }

        log__.debug("追加後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }
//    /**
//     * <br>[機  能] タイムカード：IP着信による始業時間セット
//     * <br>[解  説]
//     * <br>[備  考]
//     *
//     * @param usid ユーザSID
//     * @param con コネクション
//     * @throws SQLException SQL実行時例外
//     */
//    public void setTcdInTime(int usid, Connection con) throws SQLException {
//
//        log__.debug("始業時間セット：開始");
//
//        TimecardDao dao = new TimecardDao(con);
//        TcdTcdataModel bean = new TcdTcdataModel();
//        UDate now = new UDate();
//        UDate nowDate = new UDate();
//        nowDate.setTimeStamp(now.getYear(), now.getMonth(), now.getIntDay(), 0, 0, 0);
//        bean.setUsrSid(usid);
//        bean.setTcdDate(nowDate);
//        bean.setTcdIntime(new Time(now.getTime()));
//        bean.setTcdStatus(UIOSTS_IN);
//        bean.setTcdAuid(usid);
//        bean.setTcdAdate(now);
//        bean.setTcdEuid(usid);
//        bean.setTcdEdate(now);
//
//        if (dao.selCnt(bean) == 0) {
//            dao.insert(bean);
//        } else {
//            //現在時＜既存始業時間の時、更新
//            UDate tcd = new UDate();
//            if (bean.getTcdIntime() != null) {
//                tcd.setTime(bean.getTcdIntime().getTime());
//                if (now.compareDateYMDHM(tcd) == UDate.SMALL) {
//                    log__.debug("始業時間入力済み：セットしない");
//                    return;
//                }
//            }
//            bean.setTcdIntime(new Time(now.getTime()));
//            dao.updateFromCL(bean);
//        }
//        con.commit();
//
//        log__.debug("始業時間セット：終了");
//        return;
//    }
//
//    /**
//     * <br>[機  能] タイムカード：IP着信による終業時間セット
//     * <br>[解  説]
//     * <br>[備  考]
//     *
//     * @param usid ユーザSID
//     * @param con コネクション
//     * @throws SQLException SQL実行時例外
//     */
//    public void setTcdOutTime(int usid, Connection con) throws SQLException {
//
//        log__.debug("終業時間セット：開始");
//
//        TimecardDao dao = new TimecardDao(con);
//        TcdTcdataModel bean = new TcdTcdataModel();
//        UDate now = new UDate();
//        UDate nowDate = new UDate();
//        nowDate.setTimeStamp(now.getYear(), now.getMonth(), now.getIntDay(), 0, 0, 0);
//        bean.setUsrSid(usid);
//        bean.setTcdDate(nowDate);
//        bean.setTcdOuttime(new Time(now.getTime()));
//        bean.setTcdStatus(UIOSTS_OUT);
//        bean.setTcdAuid(usid);
//        bean.setTcdAdate(now);
//        bean.setTcdEuid(usid);
//        bean.setTcdEdate(now);
//
//        if (dao.selCnt(bean) == 0) {
//            dao.insert(bean);
//        } else {
//            //現在時＞既存終業時間の時、更新
//            UDate tcd = new UDate();
//            if (bean.getTcdOuttime() != null) {
//                tcd.setTime(bean.getTcdOuttime().getTime());
//                if (now.compareDateYMDHM(tcd) == UDate.LARGE) {
//                    log__.debug("終業時間入力済み：セットしない");
//                    return;
//                }
//            }
//            bean.setTcdOuttime(new Time(now.getTime()));
//            dao.updateFromCL(bean);
//        }
//        con.commit();
//
//        log__.debug("終業時間セット：終了");
//
//        return;
//    }

    /**
     * <br>[機  能] テンポラリディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param pluginId プラグインID
     * @param reqMdl リクエストモデル
     * @return テンポラリディレクトリパス
     */
    public String getTempDir(String rootDir, String pluginId, RequestModel reqMdl) {

        String savePath = "";

        if (reqMdl != null && reqMdl.getSession() != null) {
            //セッションID
            String sessionId = reqMdl.getSession().getId();

            StringBuilder tempDir = new StringBuilder("");
            tempDir.append(rootDir);
            tempDir.append("/");
            tempDir.append(pluginId);
            tempDir.append("/");
            tempDir.append(sessionId);
            tempDir.append("/");

            savePath = IOTools.replaceFileSep(tempDir.toString());
        }

        return savePath;
    }
    /**
     * <br>[機  能] UUIDを使用してランダムなテンポラリディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param pluginId プラグインID
     * @return テンポラリディレクトリパス
     */
    public String getTempDir(String rootDir, String pluginId) {

        UUID uuid = UUID.randomUUID();
        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(rootDir);
        tempDir.append("/");
        tempDir.append(pluginId);
        tempDir.append("/");
        tempDir.append(uuid.toString());
        tempDir.append("/");

        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }
    /**
     * バイナリーデータの保存先ディレクトリPATHを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションROOT
     * @return String バイナリーデータの保存先ディレクトリPATH
     */
    public String getFileRootPath(String appRootPath) {
        StringBuilder rootDir = new StringBuilder("");
        if (ConfigBundle.getValue("GSDATA_DIR") != null) {
            //設定ファイル(gsdata.conf)の指定ディレクトリ
            String confPath = ConfigBundle.getValue("GSDATA_DIR");
            confPath = IOTools.setEndPathChar(confPath);
            rootDir.append(confPath);
            rootDir.append(GSConst.FILE_DIR);
        } else {
            //デフォルト
            appRootPath = IOTools.setEndPathChar(appRootPath);
            rootDir.append(appRootPath);
            rootDir.append(GSConst.FILE_SAVE_DIR);
        }

        String fileRoot = IOTools.replaceSlashFileSep(rootDir.toString());
        fileRoot = IOTools.setEndPathChar(fileRoot);
        return fileRoot;
    }
    /**
     * ファイル管理用のバイナリーデータの保存先ディレクトリPATHを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションROOT
     * @return String バイナリーデータの保存先ディレクトリPATH
     */
    public String getFileRootPathForFileKanri(String appRootPath) {
        StringBuilder rootDir = new StringBuilder("");
        if (ConfigBundle.getValue("FILEKANRI_DIR") != null) {
            //設定ファイル(gsdata.conf)の指定ディレクトリ
            String confPath = ConfigBundle.getValue("FILEKANRI_DIR");
            confPath = IOTools.setEndPathChar(confPath);
            rootDir.append(confPath);
            rootDir.append(GSConst.FILE_KANRI_DIR);
        } else {
            //デフォルト
            appRootPath = IOTools.setEndPathChar(appRootPath);
            rootDir.append(appRootPath);
            rootDir.append(GSConst.FILE_KANRI_SAVE_DIR);
        }

        String fileRoot = IOTools.replaceSlashFileSep(rootDir.toString());
        fileRoot = IOTools.setEndPathChar(fileRoot);
        return fileRoot;
    }
    /**
     * <br>[機  能] WEBメール用のバイナリーデータの保存先ディレクトリPATHを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションROOT
     * @return String バイナリーデータの保存先ディレクトリPATH
     */
    public String getFileRootPathForWebmail(String appRootPath) {
        StringBuilder rootDir = new StringBuilder("");
        if (ConfigBundle.getValue("WEBMAIL_DIR") != null) {
            //設定ファイル(gsdata.conf)の指定ディレクトリ
            String confPath = IOTools.replaceFileSep(ConfigBundle.getValue("WEBMAIL_DIR"));
            rootDir.append(IOTools.setEndPathChar(confPath));
        } else {
            //デフォルト
            appRootPath = IOTools.replaceFileSep(appRootPath);
            rootDir.append(IOTools.setEndPathChar(appRootPath));
            rootDir.append(IOTools.replaceFileSep(GSConst.FILE_WEBMAIL_SAVE_DIR));
        }

        String fileRoot = IOTools.setEndPathChar(rootDir.toString());
        return IOTools.replaceSlashFileSep(fileRoot);
    }
    /**
     * バックアップディレクトリPATHを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションROOT
     * @return String バックアップディレクトリPATH
     */
    public static String getBackupDirPath(String appRootPath) {
        String backupDir
            = NullDefault.getString(ConfigBundle.getValue("BACKUP_DIR"), "").trim();
        if (backupDir.length() == 0) {
            backupDir = appRootPath.concat("WEB-INF/");
        } else if (!(new File(backupDir)).isDirectory()) {
            backupDir = backupDir.concat("/");
        }
        backupDir = backupDir.concat("backup/");
        backupDir = IOTools.replaceFileSep(backupDir);

        return backupDir;
    }

    /**
     * 手動バックアップディレクトリPATHを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションROOT
     * @return String 手動バックアップディレクトリPATH
     */
    public static String getManualBackupDirPath(String appRootPath) {
        String backupDir
            = NullDefault.getString(ConfigBundle.getValue("BACKUP_DIR"), "").trim();
        if (backupDir.length() == 0) {
            backupDir = appRootPath.concat("WEB-INF/");
        } else if (!IOTools.replaceSlashFileSep(backupDir).endsWith("/")) {
            backupDir += "/";
        }

        backupDir = backupDir.concat("backup/");
        backupDir = backupDir.concat("manual/");
        backupDir = IOTools.replaceFileSep(backupDir);

        return backupDir;
    }

    /**
     * <br>[機  能] 添付ファイル保存用のパスを取得する(DB登録用のパス)
     * <br>[解  説] 例）2006/08/23/1
     * <br>[備  考]
     * @param saveDate 日付
     * @param binSid バイナリーSID
     * @return 添付ファイル保存用のパス
     */
    public String getSavePathForDb(UDate saveDate, Long binSid) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(saveDate.getStrYear());
        tempDir.append("/");
        tempDir.append(saveDate.getStrMonth());
        tempDir.append("/");
        tempDir.append(saveDate.getStrDay());
        tempDir.append("/");
        tempDir.append(String.valueOf(binSid));
//      添付ファイル保存先PATHのファイルセパレーターは/で保存
//        String savePath = IOTools.replaceFileSep(tempDir.toString());
        String savePath = IOTools.replaceSlashFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] 添付ファイル保存用のパスを取得する(DB登録用のパス)
     * <br>[解  説] 例）2006/08/23/1
     * <br>[備  考]
     * @param saveDate 日付
     * @param binSid バイナリーSID
     * @return 添付ファイル保存用のパス
     */
    public String getSavePathForDb(UDate saveDate, long binSid) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(saveDate.getStrYear());
        tempDir.append("/");
        tempDir.append(saveDate.getStrMonth());
        tempDir.append("/");
        tempDir.append(saveDate.getStrDay());
        tempDir.append("/");
        tempDir.append(String.valueOf(binSid));
//      添付ファイル保存先PATHのファイルセパレーターは/で保存
//        String savePath = IOTools.replaceFileSep(tempDir.toString());
        String savePath = IOTools.replaceSlashFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] ライセンスファイル保存用のパスを取得する(フルパス)
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/file
     * <br>[備  考]
     *
     * @param appRootPath アプリケーションルートパス
     * @param domain ドメイン
     * @return 添付ファイル保存用のパス
     */
    public String getSaveLicensePath(String appRootPath, String domain) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(__getLicenseFileRootPath(appRootPath, domain));
        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] 添付ファイル保存用のパスを取得する(フルパス)
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/file/2006/08/23/1
     * <br>[備  考]
     * @param saveDate 日付
     * @param binSid バイナリーSID
     * @param appRootPath アプリケーションルートパス
     * @return 添付ファイル保存用のパス
     */
    public String getSaveFullPath(UDate saveDate, Long binSid, String appRootPath) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(getFileRootPath(appRootPath));
//        tempDir.append(appRootPath);
//        tempDir.append(GSConst.FILE_SAVE_DIR);
        tempDir.append(getSavePathForDb(saveDate, binSid));
        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] 添付ファイル保存用のパスを取得する(フルパス)
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/file/2006/08/23/1
     * <br>[備  考]
     * @param appRootPath アプリケーションルートパス
     * @param binPath DB(CMN_BINF)から取得したファイルパス
     * @return 添付ファイル保存用のパス
     */
    public String getSaveFullPath(String appRootPath, String binPath) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(getFileRootPath(appRootPath));
//        tempDir.append(appRootPath);
//        tempDir.append(GSConst.FILE_SAVE_DIR);
        tempDir.append(binPath);

        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] 添付ファイル保存用ディレクトリのパスを取得する
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/file/2006/08/23/
     * <br>[備  考]
     * @param saveDate 日付
     * @param appRootPath アプリケーションルートパス
     * @return 添付ファイル保存用のパス
     */
    public String getSaveDirPath(UDate saveDate, String appRootPath) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(getFileRootPath(appRootPath));
//        tempDir.append(appRootPath);
//        tempDir.append(GSConst.FILE_SAVE_DIR);
        tempDir.append(saveDate.getStrYear());
        tempDir.append("/");
        tempDir.append(saveDate.getStrMonth());
        tempDir.append("/");
        tempDir.append(saveDate.getStrDay());
        tempDir.append("/");

        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }


    /**
     * <br>[機  能] ファイル管理用の添付ファイル保存用のパスを取得する(フルパス)
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/file/2006/08/23/1
     * <br>[備  考]
     * @param saveDate 日付
     * @param binSid バイナリーSID
     * @param appRootPath アプリケーションルートパス
     * @return 添付ファイル保存用のパス
     */
    public String getSaveFullPathForFileKanri(UDate saveDate, Long binSid, String appRootPath) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(getFileRootPathForFileKanri(appRootPath));
        tempDir.append(getSavePathForDb(saveDate, binSid));
        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] ファイル管理用の添付ファイル保存用のパスを取得する(フルパス)
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/file/2006/08/23/1
     * <br>[備  考]
     * @param appRootPath アプリケーションルートパス
     * @param binPath DB(CMN_BINF)から取得したファイルパス
     * @return 添付ファイル保存用のパス
     */
    public String getSaveFullPathForFileKanri(String appRootPath, String binPath) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(getFileRootPathForFileKanri(appRootPath));
        tempDir.append(binPath);

        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] ファイル管理用の添付ファイル保存用ディレクトリのパスを取得する
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/filekanri/2006/08/23/
     * <br>[備  考]
     * @param saveDate 日付
     * @param appRootPath アプリケーションルートパス
     * @return 添付ファイル保存用のパス
     */
    public String getSaveDirPathForFileKanri(UDate saveDate, String appRootPath) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(getFileRootPathForFileKanri(appRootPath));
        tempDir.append(saveDate.getStrYear());
        tempDir.append("/");
        tempDir.append(saveDate.getStrMonth());
        tempDir.append("/");
        tempDir.append(saveDate.getStrDay());
        tempDir.append("/");

        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] WEBメール用の添付ファイル保存用ディレクトリのパスを取得する
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/webmail/2006/08/23/
     * <br>[備  考]
     * @param saveDate 日付
     * @param appRootPath アプリケーションルートパス
     * @return 添付ファイル保存用のパス
     */
    public String getSaveDirPathForFileWebmail(UDate saveDate, String appRootPath) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(getFileRootPathForWebmail(appRootPath));
        tempDir.append(saveDate.getStrYear());
        tempDir.append("/");
        tempDir.append(saveDate.getStrMonth());
        tempDir.append("/");
        tempDir.append(saveDate.getStrDay());
        tempDir.append("/");

        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] WEBメール添付ファイル保存用のパスを取得する(フルパス)
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/webmail/2010/06/21/1
     * <br>[備  考]
     * @param appRootPath アプリケーションルートパス
     * @param binPath DB(WML_TEMPFILE)から取得したファイルパス
     * @return 添付ファイル保存用のパス
     */
    public String getSaveFullPathForWebmail(String appRootPath, String binPath) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(getFileRootPathForWebmail(appRootPath));
        tempDir.append(binPath);

        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] WEBメール用の添付ファイル保存用のパスを取得する(フルパス)
     * <br>[解  説] 例）C:/gsession/war/WEB-INF/file/2006/08/23/1
     * <br>[備  考]
     * @param saveDate 日付
     * @param binSid バイナリーSID
     * @param appRootPath アプリケーションルートパス
     * @return 添付ファイル保存用のパス
     */
    public String getSaveFullPathForWebmail(UDate saveDate, long binSid, String appRootPath) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(getFileRootPathForWebmail(appRootPath));
        tempDir.append(getSavePathForDb(saveDate, binSid));
        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] 添付ファイル(本体)のパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param fileNum 連番
     * @return 添付ファイル(本体)のパス
     */
    public static File getSaveFilePath(String tempDir, String dateStr, int fileNum) {

        return __getFilePath(tempDir, dateStr, fileNum, GSConstCommon.ENDSTR_SAVEFILE);
    }

    /**
     * <br>[機  能] オブジェクトファイルのパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param fileNum 連番
     * @return 添付ファイル(本体)のパス
     */
    public static File getObjFilePath(String tempDir, String dateStr, int fileNum) {

        return __getFilePath(tempDir, dateStr, fileNum, GSConstCommon.ENDSTR_OBJFILE);
    }

    /**
     * <br>[機  能] ファイルパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリファイル
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param fileNum 連番
     * @param endStr 接尾文字列("file" or "obj")
     * @return ファイルパス
     */
    private static File __getFilePath(
        String tempDir,
        String dateStr,
        int fileNum,
        String endStr) {

        StringBuilder filePath = new StringBuilder("");
        filePath.append(tempDir);
        filePath.append(dateStr);
        filePath.append(StringUtil.toDecFormat(fileNum, "000"));
        filePath.append(endStr);

        return new File(filePath.toString());
    }

    /**
     * バイナリーデータの保存先ディレクトリPATHを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションROOT
     * @param domain ドメイン
     * @return String バイナリーデータの保存先ディレクトリPATH
     */
    private String __getLicenseFileRootPath(String appRootPath, String domain) {
        StringBuilder rootDir = new StringBuilder("");
        if (ConfigBundle.getValue("GSDATA_DIR") != null) {
            //設定ファイル(gsdata.conf)の指定ディレクトリ
            String confPath = ConfigBundle.getValue("GSDATA_DIR");
            confPath = IOTools.setEndPathChar(confPath);
            rootDir.append(confPath);
            rootDir.append(GSConst.FILE_DIR);
        } else {
            //デフォルト
            appRootPath = IOTools.setEndPathChar(appRootPath);
            rootDir.append(appRootPath);
            rootDir.append(GSConst.FILE_SAVE_DIR);
        }
        String fileRoot
                 = GroupSession.getResourceManager().getSaveLicensePath(rootDir.toString(), domain);
        fileRoot = IOTools.replaceSlashFileSep(fileRoot);
        fileRoot = IOTools.setEndPathChar(fileRoot);
        return fileRoot;
    }

    /**
     * <br>[機  能] 画面に表示する添付ファイル一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @return 画面表示用添付ファイル一覧
     * @throws IOToolsException 添付ファイルの読み込みに失敗
     */
    public List<LabelValueBean> getTempFileLabelList(String tempDir)
    throws IOToolsException {

        //テンポラリディレクトリにあるファイル名称を取得
        List < String > fileList = IOTools.getFileNames(tempDir);

        //画面に表示するファイルのリストを作成
        List<LabelValueBean> fileLblList = new ArrayList<LabelValueBean>();

        if (fileList != null) {

            for (int i = 0; i < fileList.size(); i++) {

                //ファイル名を取得
                String fileName = fileList.get(i);

                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                String name = fileName.replaceFirst(
                        GSConstCommon.ENDSTR_OBJFILE, GSConstCommon.ENDSTR_SAVEFILE);
                long atattiSize = new File(tempDir, name).length();
                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }

                String[] value = fileName.split(GSConstCommon.ENDSTR_OBJFILE);

                //表示用リストへ追加
                Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
                fileLblList.add(new LabelValueBean(
                        addAtattiSizeForName(fMdl.getFileName(), atattiSize), value[0]));

                log__.debug("ファイル名 = " + fMdl.getFileName());
                log__.debug("保存ファイル名 = " + fMdl.getSaveFileName());
                log__.debug("ファイルサイズ(byte) =" + fMdl.getAtattiSize());
            }
        }

        return fileLblList;
    }

    /**
     * <br>[機  能] TEMPディレクトリにある添付ファイルの合計サイズを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @return 画面表示用添付ファイル一覧
     * @throws IOToolsException 添付ファイルの読み込みに失敗
     */
    public Long getTempFileSize(String tempDir)
    throws IOToolsException {

        Long fileSize = new Long(0);

        //テンポラリディレクトリにあるファイル名称を取得
        List < String > fileList = IOTools.getFileNames(tempDir);

        if (fileList != null) {

            for (int i = 0; i < fileList.size(); i++) {

                //ファイル名を取得
                String fileName = fileList.get(i);

                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }

                String name = fileName.replaceFirst(
                        GSConstCommon.ENDSTR_OBJFILE, GSConstCommon.ENDSTR_SAVEFILE);
                long atattiSize = new File(tempDir, name).length();

                fileSize += atattiSize;
            }
        }

        return fileSize;
    }

    /**
     * <br>[機  能] TEMPディレクトリにある添付ファイル一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリパス
     * @return 添付ファイル一覧
     * @throws IOToolsException 添付ファイルの読み込みに失敗
     */
    public List<TempFileModel> getTempFiles(String tempDir)
    throws IOToolsException {

        //テンポラリディレクトリにあるファイル名称を取得
        List < String > fileList = IOTools.getFileNames(tempDir);

        //ファイルのリストを作成
        List<TempFileModel> fileLblList = new ArrayList<TempFileModel>();
        TempFileModel tempFileMdl = null;
        if (fileList != null) {

            for (int i = 0; i < fileList.size(); i++) {

                //ファイル名を取得
                String fileName = fileList.get(i);

                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                String name = fileName.replaceFirst(
                        GSConstCommon.ENDSTR_OBJFILE, GSConstCommon.ENDSTR_SAVEFILE);
                File file = new File(tempDir, name);
                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }

                //表示用リストへ追加
                Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
                log__.debug("fMdl.getFileName()==>" + fMdl.getFileName());
                tempFileMdl = new TempFileModel();
                tempFileMdl.setFile(file);
                tempFileMdl.setFileName(fMdl.getFileName());
                fileLblList.add(tempFileMdl);
                log__.debug("file.getName()==>" + file.getName());
            }
        }

        return fileLblList;
    }
    /**
     * <br>[機  能] テンポラリディレクトリパスにある添付ファイルを全て登録し、
     *              登録時のバイナリーSIDをListで返す
     *
     * <br>[解  説] ファイル本体は保存用ディレクトリにコピー、
     *              ファイル情報はDBに登録する
     * <br>[備  考]
     * @param con コネクション
     * @param tempDir テンポラリディレクトリパス
     * @param appRootPath アプリケーションのルートパス
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return 登録したバイナリーSIDのリスト
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public List<String> insertBinInfo(
        Connection con,
        String tempDir,
        String appRootPath,
        MlCountMtController cntCon,
        int userSid,
        UDate now) throws TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        List<String> binList
            = tempFileUtil.insertBinInfo(con, tempDir, appRootPath, cntCon, userSid, now);

        return binList;

    }

    /**
     * <br>[機  能] 指定した添付ファイルを登録し、
     *              登録時のバイナリーSIDを返す
     *
     * <br>[解  説] ファイル本体は保存用ディレクトリにコピー、
     *              ファイル情報はDBに登録する
     * <br>[備  考]
     * @param con コネクション
     * @param appRootPath アプリケーションのルートパス
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @param filePath ファイルパス
     * @param fileName ファイル名
     * @return 登録したバイナリーSIDのリスト
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public Long insertBinInfo(
        Connection con,
        String appRootPath,
        MlCountMtController cntCon,
        int userSid,
        UDate now,
        String filePath,
        String fileName) throws TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        Long binSid = tempFileUtil.insertBinInfo(
                con, appRootPath, cntCon, userSid, now, filePath, fileName);

        return binSid;

    }

    /**
     * <br>[機  能] 指定した添付ファイルを登録し、
     *              登録時のバイナリーSIDを返す
     *
     * <br>[解  説] ファイル本体は保存用ディレクトリにコピー、
     *              ファイル情報はDBに登録する
     * <br>[備  考]
     * @param con コネクション
     * @param appRootPath アプリケーションのルートパス
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @param filePath ファイルパス
     * @param fileName ファイル名
     * @return 登録したバイナリーSIDのリスト
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public Long insertBinInfoForFilekanri(
        Connection con,
        String appRootPath,
        MlCountMtController cntCon,
        int userSid,
        UDate now,
        String filePath,
        String fileName) throws TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        Long binSid = tempFileUtil.insertBinInfoForFilekanri(
                con, appRootPath, cntCon, userSid, now, filePath, fileName);

        return binSid;

    }

    /**
     * <br>[機  能] 添付ファイルを登録し、登録時のバイナリーSIDリストを返す
     * <br>[解  説] ファイル本体の保存先を振り分ける。
     * <br>[備  考] ウェブメール用
     * @param con コネクション
     * @param appRootPath アプリケーションのルートパス
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @param fileDataList ファイルデータリスト
     * @param mailNum メール番号
     * @return 登録したバイナリーSIDのリスト
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public List<Long> insertBinInfoForWebmail(
        Connection con,
        String appRootPath,
        MlCountMtController cntCon,
        int userSid,
        UDate now,
        List<WmlMailFileModel> fileDataList,
        long mailNum) throws TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        List<Long> binSidList = tempFileUtil.insertBinInfoForWebmail(
                con, appRootPath, cntCon, userSid, now, fileDataList, mailNum);

        return binSidList;

    }

    /**
     * <br>[機  能] テンポラリディレクトリパスにある添付ファイルを全て登録し、
     *              登録時のバイナリーSIDをListで返す
     * <br>[解  説]
     * <br>[備  考] ファイル管理で使用
     * @param con コネクション
     * @param tempDir テンポラリディレクトリパス
     * @param appRootPath アプリケーションのルートパス
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return 登録したバイナリーSIDのリスト
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public List<String> insertBinInfoForFileKanri(
        Connection con,
        String tempDir,
        String appRootPath,
        MlCountMtController cntCon,
        int userSid,
        UDate now) throws TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        List<String> binList = tempFileUtil.insertBinInfoForFilekanri(
                con, tempDir, appRootPath, cntCon, userSid, now);

        return binList;
    }

    /**
     * <br>[機  能] テンポラリディレクトリパスにある添付ファイルを全て登録し、
     *              登録時のバイナリーSIDをListで返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param tempDir テンポラリディレクトリパス
     * @param appRootPath アプリケーションのルートパス
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return 登録したバイナリーSIDのリスト
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public List<String> insertSameBinInfo(
        Connection con,
        String tempDir,
        String appRootPath,
        MlCountMtController cntCon,
        int userSid,
        UDate now) throws TempFileException {

        ITempFileUtil tempFileUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        List<String> binList = tempFileUtil.insertSameBinInfo(
                con, tempDir, appRootPath, cntCon, userSid, now);

        return binList;
    }

    /**
     * <br>[機  能] テンポラリディレクトリパスにある添付ファイルを全て登録し、
     *              登録時のバイナリーモデルをListで返す
     * <br>[解  説]
     * <br>[備  考] ファイル管理で使用
     * @param con コネクション
     * @param tempDir テンポラリディレクトリパス
     * @param appRootPath アプリケーションのルートパス
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @return 登録したバイナリーSIDのリスト
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public List<CmnBinfModel> insertSameBinInfoForFileKanri(
        Connection con,
        String tempDir,
        String appRootPath,
        MlCountMtController cntCon,
        int userSid,
        UDate now) throws TempFileException {

        ITempFileUtil tempFileUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        List<CmnBinfModel> binList = tempFileUtil.insertSameBinInfoForFileKanri(
                con, tempDir, appRootPath, cntCon, userSid, now);
        return binList;
    }

    /**
     * <br>[機  能] 指定した添付ファイルを更新し、更新件数を返す。
     * <br>[解  説] ファイル本体は保存保存先を振り分ける。
     * <br>[備  考]
     * @param con コネクション
     * @param appRootPath アプリケーションのルートパス
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @param filePath ファイルパス
     * @param binSid バイナリーSID
     * @param fileName ファイル名
     * @param cntCon MlCountMtController
     * @return 更新件数
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public int updateBinInfo(
        Connection con,
        String appRootPath,
        int userSid,
        UDate now,
        String filePath,
        Long binSid,
        String fileName,
        MlCountMtController cntCon) throws TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        int count = tempFileUtil.updateBinInfo(
                con, appRootPath, userSid, now, filePath, binSid, fileName, cntCon);

        return count;

    }

    /**
     * <br>[機  能] 指定したバイナリSIDのファイルをコピーする。
     * <br>[解  説]
     * <br>[備  考] ファイル管理で使用する。
     * @param appRoot アプリケーションのルートパス
     * @param binSid バイナリSID
     * @param usrSid ユーザSID
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param domain ドメイン
     * @return newBinSid 採番バイナリSID
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public Long copyfile(
            String appRoot,
            Long binSid,
            int usrSid,
            Connection con,
            MlCountMtController cntCon,
            String domain
            ) throws TempFileException {


        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        Long newBinSid = tempFileUtil.copyFile(appRoot, binSid, usrSid, con, cntCon, domain);
        tempFileUtil.utilDestroy();
        return newBinSid;

    }

    /**
     * <br>[機  能] 添付ファイルを削除する。
     * <br>[解  説] ファイルシステムの添付ファイル本体を削除する。
     * <br>[備  考]
     * @param cbMdl CmnBinfModel
     * @param appRootPath アプリケーションのルートパス
     * @throws IOToolsException ファイル操作時例外
     */
    public void deleteFile(CmnBinfModel cbMdl, String appRootPath) throws IOToolsException {

        ITempFileUtil tempFileUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        tempFileUtil.deleteFile(cbMdl, appRootPath);
    }

    /**
     * <br>[機  能] 添付ファイルを削除する。
     * <br>[解  説] 添付ファイル本体を削除する。
     * <br>[備  考]
     * @param con コネクション
     * @param appRootPath アプリケーションのルートパス
     * @param binSidList 削除対象のバイナリSID一覧
     * @throws SQLException ExceptionSQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     */
    public void deleteFileForWebmail(Connection con, String appRootPath,
                                    List<Long> binSidList)
    throws SQLException, IOToolsException {

        ITempFileUtil tempFileUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        tempFileUtil.deleteFileForWebmail(con, appRootPath, binSidList);
    }

    /**
     * <br>[機  能] 指定されたファイル(複数)を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param delFile ファイル名
     * @param tempDir テンポラリディレクトリパス
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void deleteFile(String[] delFile, String tempDir) throws IOToolsException {

        if (delFile == null) {
            log__.debug("削除するファイルのフルパス(オブジェクト) = 戻る1");
            return;
        }
        if (delFile.length < 1) {
            log__.debug("削除するファイルのフルパス(オブジェクト) = 戻る2");
            return;
        }

        for (int i = 0; i < delFile.length; i++) {

            //テンポラリファイルのフルパス(オブジェクト)
            String delPathObj = tempDir + "/" + delFile[i] + GSConstCommon.ENDSTR_OBJFILE;
            delPathObj = IOTools.replaceFileSep(delPathObj);
            log__.debug("削除するファイルのフルパス(オブジェクト) = " + delPathObj);

            //テンポラリファイルのフルパス(本体)
            String delPathFile = tempDir + "/" + delFile[i] + GSConstCommon.ENDSTR_SAVEFILE;
            delPathFile = IOTools.replaceFileSep(delPathFile);
            log__.debug("削除するファイルのフルパス(本体) = " + delPathFile);

            //ファイルを削除
            IOTools.deleteFile(delPathObj);
            IOTools.deleteFile(delPathFile);
        }
    }

    /**
     * <br>[機  能] 添付ファイルを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param binSids バイナリーSID(複数)
     * @return int 削除件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteBinInf(Connection con, String[] binSids) throws SQLException {

        int count = 0;
        ITempFileUtil tempFileUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        count = tempFileUtil.deleteBinInf(con, binSids);
        return count;
    }

    /**
     * <br>[機  能] バイナリー情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param binSid バイナリSID
     * @param domain ドメイン
     * @return バイナリ情報
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public CmnBinfModel getBinInfo(Connection con, Long binSid, String domain)
    throws TempFileException {

        ITempFileUtil tempFileUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);
        CmnBinfModel model = tempFileUtil.getBinInfo(con, binSid, domain);

        return model;
    }

    /**
     * <br>[機  能] バイナリー情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param binSid バイナリSID
     * @param domain ドメイン
     * @return バイナリ情報
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public CmnBinfModel getBinInfoToDomain(Connection con, Long binSid, String domain)
    throws TempFileException {

        ITempFileUtil tempFileUtil
        = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);
        CmnBinfModel model = tempFileUtil.getBinInfoToDomain(con, binSid, domain);

        return model;
    }

    /**
     * <br>[機  能] 指定したバイナリー情報リストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param binSids バイナリSIDリスト
     * @param domain ドメイン
     * @return バイナリ情報リスト
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public List<CmnBinfModel> getBinInfo(Connection con, String[] binSids, String domain)
    throws TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        List<CmnBinfModel> binList = tempFileUtil.getBinInfo(con, binSids, domain);

        return binList;
    }

    /**
     * <br>[機  能] 指定したバイナリー情報リストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param binSids バイナリSIDリスト
     * @param domain ドメイン
     * @return バイナリ情報リスト
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public List<CmnBinfModel> getBinInfoToDomain(Connection con, String[] binSids, String domain)
    throws TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        List<CmnBinfModel> binList = tempFileUtil.getBinInfoToDomain(con, binSids, domain);

        return binList;
    }

    /**
     * <br>[機  能] バイナリー情報を取得する
     * <br>[解  説]
     * <br>[備  考] ウェブメールで使用する
     * @param con コネクション
     * @param wmdMailnum メッセージ番号
     * @param wtfSid バイナリSID
     * @param domain ドメイン
     * @return バイナリ情報
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public WmlTempfileModel getBinInfoForWebmail(Connection con,
                                                 long wmdMailnum,
                                                 long wtfSid,
                                                 String domain)
    throws TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        WmlTempfileModel model = tempFileUtil.getBinInfoForWebmail(con, wmdMailnum, wtfSid, domain);
        return model;
    }

    /**
     * <br>[機  能] バイナリデータを元にテンポラリディレクトリ内に添付ファイルを作成する(単一ファイル用)
     * <br>[解  説]
     * <br>[備  考] 添付ファイル名と保存用ファイル名を返します
     * @param binMdl 添付ファイル情報
     * @param appRoot アプリケーションルートパス
     * @param tempDir テンポラリディレクトリパス
     * @return 保存用ファイル名
     * @throws IOException 添付ファイルの作成に失敗
     * @throws IOToolsException 添付ファイルの作成に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public String saveSingleTempFile(
            CmnBinfModel binMdl,
            String appRoot,
            String tempDir)
                    throws IOException,
                    IOToolsException,
                    TempFileException {
        String saveName = "";

        //添付ファイルを本体とオブジェクトファイルにしてテンポラリディレクトリに格納する。
        String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)
        saveTempFile(dateStr, binMdl, appRoot, tempDir, 0);

        List <String> fileList = IOTools.getFileNames(tempDir);
        for (String fileName : fileList) {
            if (fileName.endsWith(GSConstCommon.ENDSTR_SAVEFILE)) {
                String[] splitFileName = fileName.split(GSConstCommon.ENDSTR_SAVEFILE);
                saveName = splitFileName[0];
                break;
            }
        }

        return saveName;
    }

    /**
     * <br>[機  能] バイナリデータを元にテンポラリディレクトリ内に添付ファイルを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param binData バイナリデータ
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param fileNum ファイルの連番
     * @return filePath 添付ファイルパス
     * @throws IOException 添付ファイルの作成に失敗
     * @throws IOToolsException 添付ファイルの作成に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public String saveTempFile(String dateStr, CmnBinfModel binData,
                            String appRoot, String tempDir, int fileNum)
    throws IOException, IOToolsException, TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        String filePath = tempFileUtil.saveTempFile(dateStr, binData, appRoot, tempDir, fileNum);

        return filePath;

    }

    /**
     * <br>[機  能] バイナリデータを元にテンポラリディレクトリ内に添付ファイルを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param binData バイナリデータ
     * @param fileMdl 添付ファイル情報
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param fileNum ファイルの連番
     * @return filePath 添付ファイルパス
     * @throws IOException 添付ファイルの作成に失敗
     * @throws IOToolsException 添付ファイルの作成に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public String saveTempFile(String dateStr, CmnBinfModel binData, Cmn110FileModel fileMdl,
                            String appRoot, String tempDir, int fileNum)
    throws IOException, IOToolsException, TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        String filePath = tempFileUtil.saveTempFile(
                dateStr, binData, fileMdl, appRoot, tempDir, fileNum);

        return filePath;

    }

    /**
     * <br>[機  能] バイナリデータを元にテンポラリディレクトリ内に添付ファイルを作成する
     * <br>[解  説] オブジェクトファイルを生成しない
     * <br>[備  考]
     * @param binData バイナリデータ
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @return filePath 添付ファイルパス
     * @throws IOException 添付ファイルの作成に失敗
     * @throws IOToolsException 添付ファイルの作成に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public String saveTempFile(CmnBinfModel binData, String appRoot, String tempDir)
    throws IOException, IOToolsException, TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        String filePath = tempFileUtil.saveTempFile(binData, appRoot, tempDir);

        return filePath;

    }

    /**
     * <br>[機  能] バイナリデータを元にテンポラリディレクトリ内に添付ファイルを作成する
     * <br>[解  説]
     * <br>[備  考] ウェブメールで使用する。
     * @param dateStr 日付文字列(YYYYMMDD)
     * @param binData バイナリデータ
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param fileNum ファイルの連番
     * @return filePath 添付ファイルパス
     * @throws IOException 添付ファイルの作成に失敗
     * @throws IOToolsException 添付ファイルの作成に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public String saveTempFileForWebmail(String dateStr, WmlTempfileModel binData,
                            String appRoot, String tempDir, int fileNum)
    throws IOException, IOToolsException, TempFileException {

        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);

        String filePath
            = tempFileUtil.saveTempFileForWebmail(
                    dateStr, binData, appRoot, tempDir, fileNum);

        return filePath;

    }

    /**
     * <br>[機  能] ファイルのサイズを付加した名称を取得します。ex ZZZZ.9(MB) or ZZZZ.9(KB)
     * <br>[解  説]
     * <br>[備  考]
     * @param fileName ファイル名称
     * @param byteSize バイトサイズ
     * @return MB単位のファイルサイズ
     */
    public static String addAtattiSizeForName(String fileName, long byteSize) {

//        String value = fileName + "(" + formatByteSizeString(byteSize) + ")";

        log__.debug("ファイルサイズ :" + fileName);

        return fileName;
    }

    /**
     * <br>[機  能] バイトのサイズを文字列で取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param byteSize バイトサイズ
     * @return バイトの文字列表現 (ex: xxxKB xMB)
     */
    public static String formatByteSizeString(long byteSize) {

        String ret = "";

        //フォーマッタ
        String formatta = "0.0";
        DecimalFormat decimalFormat = new DecimalFormat(formatta);

        long divider = 1024;
        String unit = "KB";
        log__.debug("ファイルサイズ(byte) :" + byteSize);
        if (byteSize >= (1024 * 1024)) {
            divider = divider * 1024;
            unit = "MB";
        }

        //ファイルサイズ取得
        BigDecimal bunbo = new BigDecimal(divider);
        BigDecimal bd = new BigDecimal(byteSize);
        ret = decimalFormat.format(bd.divide(bunbo, 1, BigDecimal.ROUND_HALF_UP));

        return ret.concat(unit);
    }

    /**
     * <br>[機  能] バイトのサイズを文字列で取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param byteSize バイトサイズ
     * @return バイトの文字列表現 (ex: xxxKB xMB)
     */
    public String getByteSizeString(long byteSize) {

        String ret = "";

        //フォーマッタ
        String formatta = "0.0";
        DecimalFormat decimalFormat = new DecimalFormat(formatta);

        long divider = 1024;
        String unit = "KB";
        log__.debug("ファイルサイズ(byte) :" + byteSize);
        if (byteSize >= (1024 * 1024)) {
            divider = divider * 1024;
            unit = "MB";
        }

        //ファイルサイズ取得
        BigDecimal bunbo = new BigDecimal(divider);
        BigDecimal bd = new BigDecimal(byteSize);
        ret = decimalFormat.format(bd.divide(bunbo, 1, BigDecimal.ROUND_HALF_UP));

        String str = "(" + ret.concat(unit) + ")";
        return str;
    }

    /**
     * 都道府県コンボ用の都道府県リストを取得する
     * @param con コネクション
     * @param gsMsg GSメッセージ
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<LabelValueBean> getTdfkLabelList(
            Connection con, GsMessage gsMsg) throws SQLException {

        //選択してください
        String textSelect = gsMsg.getMessage("cmn.select.plz");

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean(textSelect, String.valueOf(0)));

        //グループリスト取得
        CmnTdfkDao tdDao = new CmnTdfkDao(con);
        List < CmnTdfkModel > tdList = tdDao.select();

        CmnTdfkModel tdMdl = null;
        for (int i = 0; i < tdList.size(); i++) {
            tdMdl = tdList.get(i);
            labelList.add(
                    new LabelValueBean(tdMdl.getTdfName(), String.valueOf(tdMdl.getTdfSid())));
        }
        log__.debug("labelList.size()=>" + labelList.size());
        return labelList;
    }

    /**
     * <br>[機  能] 検索キーワードを設定する
     * <br>[解  説] スペース区切りで複数のキーワードを設定する
     * <br>[備  考]
     * @param keyword キーワード
     * @return List in String
     */
    public List<String> setKeyword(String keyword) {
        List < String > keywordList = new ArrayList < String >();
        String searchKey = StringUtil.substitute(keyword, "　", " ");
        StringTokenizer st = new StringTokenizer(searchKey, " ");
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            if (!StringUtil.isNullZeroString(str)) {
                keywordList.add(str);
            }
        }
        return keywordList;
    }

    /**
     * プラグインが使用可能か判定します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param pluginId プラグインID
     * @param pconfig プラグイン設定
     * @return boolean true:使用可能 false:使用不可
     */
    public boolean isCanUsePlugin(String pluginId, PluginConfig pconfig) {
        for (String plugin : pconfig.getPluginIdList()) {
            if (pluginId == null || plugin.equals(pluginId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <br>[機  能] セッション保持時間を取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return int セッション保持時間
     * @throws SQLException SQL実行時例外
     */
    public int getSessionTime(Connection con) throws SQLException {

        CmnContmDao dao = new CmnContmDao(con);
        int sessionTime = dao.getSessionTime();
        if (sessionTime < 0) {
            sessionTime = GSConst.SESSION_TIME;

            CmnContmModel model = dao.select();
            if (model != null) {
                boolean commitFlg = false;
                try {
                    //コントロールマスタに情報が無い場合は、登録する。
                    model.setCntPxyUse(GSConstMain.PROXY_SERVER_NOT_USE);
                    model.setCntSessionTime(GSConst.SESSION_TIME);
                    model.setCntMenuStatic(GSConstMain.MENU_STATIC_NOT_USE);
                    dao.insert(model);
                    con.commit();
                    commitFlg = true;
                } catch (SQLException e) {
                    log__.error("SQLException", e);
                    throw e;
                } finally {
                    if (!commitFlg) {
                        JDBCUtil.rollback(con);
                    }
                }
            }
        }
        return sessionTime * 60;
    }

    /**
     * <br>[機  能] テンポラリディレクトリパスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rootDir ルートディレクトリ
     * @param pluginId プラグインID
     * @param userSid ユーザSID
     * @return テンポラリディレクトリパス
     */
    public String getTempDirMbl(String rootDir,
                                 String pluginId,
                                 String userSid) {

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(rootDir);
        tempDir.append("/");
        tempDir.append(pluginId);
        tempDir.append("/");
        tempDir.append(userSid);
        tempDir.append("/");

        UDate now = new UDate();
        tempDir.append(now.getStrYear());
        tempDir.append(now.getStrMonth());
        tempDir.append(now.getStrDay());
        tempDir.append(now.getStrHour());
        tempDir.append(now.getStrMinute());
        tempDir.append(now.getStrSecond());
        tempDir.append(now.getStrMilliSecond());
        tempDir.append("/");

        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能]拡張子からブラウザで表示する画像ファイルか判定を行う
     * <br>[解  説] true:表示,false:非表示
     * <br>[備  考] jpeg,jpg,gif,pngを対象とする
     * @param ext 拡張子
     * @return boolean true:表示,false:非表示
     */
    public static boolean isViewFile(String ext) {
        if (".gif".equals(ext)
                || ".jpeg".equals(ext)
                || ".jpg".equals(ext)
                || ".png".equals(ext)) {
            return true;
        }
        return false;
    }

    /**
     * <br>[機  能] WEB検索の使用可否を判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param pconfig PluginConfig
     * @return 判定結果 0:使用可能 or 1:使用不可
     */
    public static int getWebSearchUse(PluginConfig pconfig) {
        return GSConst.PLUGIN_USE;
    }

    /**
     * <br>[機  能] ユーザ情報プラグインの使用可否を判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param pconfig PluginConfig
     * @return 判定結果 0:使用可能 or 1:使用不可
     */
    public static int getUserPluginUse(PluginConfig pconfig) {
        int pluginUse = GSConst.PLUGIN_NOT_USE;
        if (pconfig.getPlugin(GSConst.PLUGINID_USER) != null) {
            pluginUse = GSConst.PLUGIN_USE;
        }

        return pluginUse;
    }

    /**
     * <br>[機  能] アドレス帳プラグインの使用可否を判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param pconfig PluginConfig
     * @return 判定結果 0:使用可能 or 1:使用不可
     */
    public static int getAddressPluginUse(PluginConfig pconfig) {
        int pluginUse = GSConst.PLUGIN_NOT_USE;
        if (pconfig.getPlugin("address") != null) {
            pluginUse = GSConst.PLUGIN_USE;
        }

        return pluginUse;
    }

    /**
     * <br>[機  能] ヘッダ文字列からcharsetを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param contentType ヘッダ文字列
     * @return charset
     */
    public static String getHeaderCharset(String contentType) {
        if (StringUtil.isNullZeroString(contentType)) {
            return contentType;
        }

        String charset = "";
        int csIdx = contentType.trim().toLowerCase().indexOf("charset=");
        if (csIdx > 0) {
            charset = contentType.substring(csIdx + 8);
            charset = charset.replace("\"", "");

            String[] separatorList = {";", " ", "\r", "\n"};
            for (String separator : separatorList) {
                csIdx = charset.indexOf(separator);
                if (csIdx > 0) {
                    charset = charset.substring(0, csIdx);
                    break;
                }
            }
            separatorList = null;
        }

        return charset;
    }

    /**
     * 共通部 WARN ERRORのシステムログ出力を行う
     * @param map マップ
     * @param reqMdl リクエストモデル
     * @param gsMsg GSメッセージ
     * @param con コネクション
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     */
    public void outPutSystemLog(
            ActionMapping map,
            RequestModel reqMdl,
            GsMessage gsMsg,
            Connection con,
            String opCode,
            String level,
            String value) {

        BaseUserModel usModel = reqMdl.getSmodel();
        int usrSid = -1;
        if (usModel != null) {
            usrSid = usModel.getUsrsid(); //セッションユーザSID
        }

        //エラーログを画面表示用に作成・セット
        Object oerror = reqMdl.getOerror();
        if (oerror != null) {
            Throwable terror = (Throwable) oerror;
            StringBuilder ebuf = new StringBuilder();

            ebuf.append(terror.toString());
            value = ebuf.toString();
        }

        /** メッセージ システム共通 **/
        String sys = gsMsg.getMessage("cmn.sys.public");

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usrSid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConst.PLUGINID_COMMON);
        logMdl.setLogPluginName(sys);
        String type = map.getType();
        logMdl.setLogPgId(StringUtil.trimRengeString(type, 100));
        logMdl.setLogPgName(sys);
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(
                StringUtil.trimRengeString(value, GSConstCommon.MAX_LENGTH_LOG_OP_VALUE));
        logMdl.setLogIp(reqMdl.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);

        LoggingBiz logBiz = new LoggingBiz(con);

        String domain = reqMdl.getDomain();
        logBiz.outPutLog(logMdl, domain);
    }

    /**
     * 共通部　個別ログ出力を行う
     * @param map マップ
     * @param reqMdl リクエストモデル
     * @param gsMsg GSメッセージ
     * @param con コネクション
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     */
    public void outPutCommonLog(
            ActionMapping map,
            RequestModel reqMdl,
            GsMessage gsMsg,
            Connection con,
            String opCode,
            String level,
            String value) {
        outPutCommonLog(map, reqMdl, gsMsg, con, opCode, level, value, null);
    }

    /**
     * 共通部　個別ログ出力を行う
     * @param map マップ
     * @param reqMdl リクエストモデル
     * @param gsMsg GSメッセージ
     * @param con コネクション
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param fileId 添付ファイルID
     */
    public void outPutCommonLog(
            ActionMapping map,
            RequestModel reqMdl,
            GsMessage gsMsg,
            Connection con,
            String opCode,
            String level,
            String value,
            String fileId) {

        /** メッセージ システム共通 **/
        String sys = gsMsg.getMessage("cmn.sys.public");

        BaseUserModel usModel = reqMdl.getSmodel();
        int usrSid = -1;
        if (usModel != null) {
            usrSid = usModel.getUsrsid(); //セッションユーザSID
        }

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usrSid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConst.PLUGINID_COMMON);
        logMdl.setLogPluginName(sys);
        String type = map.getType();
        logMdl.setLogPgId(StringUtil.trimRengeString(type, 100));
        logMdl.setLogPgName(getPgName(type));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(
                StringUtil.trimRengeString(value, GSConstCommon.MAX_LENGTH_LOG_OP_VALUE));
        logMdl.setLogIp(reqMdl.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);
        if (fileId != null) {
            logMdl.setLogCode("binSid：" + fileId);
        }

        LoggingBiz logBiz = new LoggingBiz(con);

        String domain = reqMdl.getDomain();
        logBiz.outPutLog(logMdl, domain);
    }
    /**
     * 共通部　個別ログ出力を行う
     * @param pgid バッチプログラムID
     * @param con コネクション
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param domain ドメイン
     */
    public void outPutBatchLog(
            String pgid,
            Connection con,
            String opCode,
            String level,
            String value,
            String domain) {

        int usrSid = -1;
        GsMessage gsMsg = new GsMessage();
        /** メッセージ システム共通 **/
        String sys = gsMsg.getMessage("cmn.sys.public", null);

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usrSid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConst.PLUGINID_COMMON);
        logMdl.setLogPluginName(sys);
        logMdl.setLogPgId(pgid);
        logMdl.setLogPgName(getPgName(pgid));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(
                StringUtil.trimRengeString(value, GSConstCommon.MAX_LENGTH_LOG_OP_VALUE));
        logMdl.setLogIp("127.0.0.1");
        logMdl.setVerVersion(GSConst.VERSION);

        LoggingBiz logBiz = new LoggingBiz(con);
        logBiz.outPutLog(logMdl, domain);
    }
    /**
     * プログラムIDからプログラム名称を取得する
     * @param id アクションID
     * @return String
     */
    public String getPgName(String id) {
        String ret = new String();
        if (id == null) {
            return ret;
        }

        log__.info("プログラムID==>" + id);

        GsMessage gsMsg = new GsMessage();
        String textKojin = gsMsg.getMessage("cmn.preferences2", null);
        String textKanri = gsMsg.getMessage("cmn.admin.setting", null);

        if (id.equals("jp.groupsession.v2.batch.DayJob")) {
            //日次バッチ
            String text = gsMsg.getMessage("cmn.batch.day", null);
            return text;
        }

        if (id.equals("jp.groupsession.v2.cmn.cmn001.Cmn001Action")) {
            //ログイン
            String text = gsMsg.getMessage("cmn.login", null);
            return text;
        }
        if (id.equals("jp.groupsession.v2.cmn.cmn130.Cmn130Action")) {
            //マイグループ設定
            String text = gsMsg.getMessage("main.man030.8", null);
            return textKojin + " " + text;
        }
        if (id.equals("jp.groupsession.v2.cmn.cmn131kn.Cmn131knAction")) {
            //マイグループ登録/編集確認
            String text = gsMsg.getMessage("cmn.mygroup.add", null);
            return textKojin + " " + text;
        }
        if (id.equals("jp.groupsession.v2.cmn.cmn140.Cmn140Action")) {
            //メニュー項目の設定
            String text = gsMsg.getMessage("main.man030.5", null);
            return textKojin + " " + text;
        }
        if (id.equals("jp.groupsession.v2.cmn.cmn150.Cmn150Action")) {
            //メイン画面表示設定
            String text = gsMsg.getMessage("cmn.setting.main.view2", null);
            return textKojin + " " + text;
        }
        if (id.equals("jp.groupsession.v2.cmn.cmn160kn.Cmn160knAction")) {
            //企業情報登録確認
            String text = gsMsg.getMessage("cmn.cmn160kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.cmn.cmn170.Cmn170Action")) {
            //テーマ設定
            String text = gsMsg.getMessage("main.man030.10", null);
            return textKojin + " " + text;
        }

        if (id.equals("jp.groupsession.v2.man.man020.Man020Action")) {
            //休日設定
            String text = gsMsg.getMessage("main.holiday.setting", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man021.Man021Action")) {
            //休日設定/追加
            String text = gsMsg.getMessage("main.man021.3", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man022kn.Man022knAction")) {
            //休日削除確認
            String text = gsMsg.getMessage("main.man022kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man023.Man023Action")) {
            //休日テンプレート一覧
            String text = gsMsg.getMessage("main.man023.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man024kn.Man024knAction")) {
            //休日テンプレート追加確認
            String text = gsMsg.getMessage("main.holiday.template.add.kn", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man025.Man025Action")) {
            //休日テンプレート追加/通常
            String text = gsMsg.getMessage("main.holiday.template.add", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man026.Man026Action")) {
            //休日テンプレート追加/拡張
            String text = gsMsg.getMessage("main.holiday.template.add.ex", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man027kn.Man027knAction")) {
            //休日テンプレート削除確認
            String text = gsMsg.getMessage("main.holiday.template.del", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man040.Man040Action")) {
            //バッチ処理起動時間設定
            String text = gsMsg.getMessage("main.man002.34", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man040kn.Man040knAction")) {
            //バッチ処理起動時間設定確認
            String text = gsMsg.getMessage("main.man002.34", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man070kn.Man070knAction")) {
            //プロキシサーバ設定確認
            String text = gsMsg.getMessage("main.man070kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man080.Man080Action")) {
            //自動バックアップ設定
            String text = gsMsg.getMessage("cmn.autobackup.setting", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man080kn.Man080knAction")) {
            //自動バックアップ設定確認
            String text = gsMsg.getMessage("main.man080kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man081.Man081Action")) {
            //手動バックアップ設定
            String text = gsMsg.getMessage("man.backup.configuration.manual", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man090.Man090Action")) {
            //アプリケーションログ一覧
            String text = gsMsg.getMessage("main.man002.57", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man100.Man100Action")) {
            //役職マネージャー
            String text = gsMsg.getMessage("main.man002.26", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man110.Man110Action")) {
            //役職登録
            String text = gsMsg.getMessage("cmn.entry.position", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man110kn.Man110knAction")) {
            //役職登録確認
            String text = gsMsg.getMessage("cmn.entry.position.kn", null);
            return textKanri + " " + text;
        }

        if (id.equals("jp.groupsession.v2.man.man112.Man112Action")) {
            //役職インポート
            String text = gsMsg.getMessage("main.man112.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man112kn.Man112knAction")) {
            //役職インポート確認
            String text = gsMsg.getMessage("main.man112kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man120.Man120Action")) {
            //プラグインマネージャー
            String text = gsMsg.getMessage("main.man002.19", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man130kn.Man130knAction")) {
            //添付ファイル設定確認
            String text = gsMsg.getMessage("main.man130kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man140kn.Man140knAction")) {
            //セッション保持時間設定確認
            String text = gsMsg.getMessage("main.man140kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man150kn.Man150knAction")) {
            //ライセンスファイル登録・更新確認
            String text = gsMsg.getMessage("main.man150.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man160kn.Man160knAction")) {
            //モバイルID・パスワード設定確認
            String text = gsMsg.getMessage("main.man160kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man180kn.Man180knAction")) {
            //ログイン履歴自動削除設定
            String text = gsMsg.getMessage("main.man180.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man190kn.Man190knAction")) {
            //ログイン履歴手動削除
            String text = gsMsg.getMessage("main.manualdelete.login.history", null);
            return textKanri + " " + text;
        }

        if (id.equals("jp.groupsession.v2.man.man200kn.Man200knAction")) {
            //パスワードルール設定確認
            String text = gsMsg.getMessage("main.man200kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man210kn.Man210knAction")) {
            //モバイル使用一括設定確認
            String text = gsMsg.getMessage("main.man210kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man220.Man220Action")) {
            //グループ・ユーザ並び順設定
            String text = gsMsg.getMessage("main.grp.usr.sort.setting", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man240kn.Man240knAction")) {
            //オペレーションログ設定確認
            String text = gsMsg.getMessage("main.man240kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man250.Man250Action")) {
            //オペレーションログ検索
            String text = gsMsg.getMessage("main.man002.53", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man260kn.Man260knAction")) {
            //オペレーションログ自動削除設定確認
            String text = gsMsg.getMessage("main.man260kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man270kn.Man270knAction")) {
            //ログイン設定確認
            String text = gsMsg.getMessage("main.man270kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man290.Man290Action")) {
            //インフォメーション登録
            String text = gsMsg.getMessage("main.man290.1", null);
            return text;
        }
        if (id.equals("jp.groupsession.v2.man.man290kn.Man290knAction")) {
            //インフォメーション登録確認
            String text = gsMsg.getMessage("main.man290kn.1", null);
            return text;
        }
        if (id.equals("jp.groupsession.v2.man.man300.Man300Action")) {
            //インフォメーション管理者設定
            String text = gsMsg.getMessage("man.info.admin.settings", null);
            return text;
        }
        if (id.equals("jp.groupsession.v2.man.man300kn.Man300knAction")) {
            //インフォメーション管理者設定確認
            String text = gsMsg.getMessage("man.info.admin.settings.kn", null);
            return text;
        }
        if (id.equals("jp.groupsession.v2.man.man310.Man310Action")) {
            //インフォメーション詳細
            String text = gsMsg.getMessage("main.man310.1", null);
            return text;
        }
        if (id.equals("jp.groupsession.v2.man.man320.Man320Action")) {
            //インフォメーション一覧
            String text = gsMsg.getMessage("main.man320.1", null);
            return text;
        }
        if (id.equals("jp.groupsession.v2.man.man330.Man330Action")) {
            //所属情報一括設定
            String text = gsMsg.getMessage("main.memberships.conf", null);
            return text;
        }
        if (id.equals("jp.groupsession.v2.man.man330kn.Man330knAction")) {
            //所属情報一括設定確認
            String text = gsMsg.getMessage("main.memberships.conf.kn", null);
            return text;
        }
        if (id.equals("jp.groupsession.v2.man.man350kn.Man350knAction")) {
            //管理者設定 メイン画面レイアウト設定確認
            String text = gsMsg.getMessage("main.man350kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.man.man360kn.Man360knAction")) {
            //個人設定 メイン画面レイアウト設定確認
            String text = gsMsg.getMessage("main.man350kn.1", null);
            return textKojin + " " +  text;
        }
        if (id.equals("jp.groupsession.v2.man.man500kn.Man500knAction")) {
            //管理者設定 個人情報編集権限設定確認
            String text = gsMsg.getMessage("main.man500kn.1", null);
            return textKanri + " " +  text;
        }

        if (id.equals("jp.groupsession.v2.usr.usr010.Usr010Action")) {
            //グループマネージャー
            String text = gsMsg.getMessage("user.44", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.usr.usr011kn.Usr011knAction")) {
            //グループマネージャー(追加/編集)確認
            String text = gsMsg.getMessage("user.usr011kn.2", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.usr.usr013.Usr013Action")) {
            //グループインポート
            String text = gsMsg.getMessage("user.usr013.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.usr.usr013kn.Usr013knAction")) {
            //グループインポート確認
            String text = gsMsg.getMessage("user.usr013kn.1", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.usr.usr030.Usr030Action")) {
            //ユーザマネージャー
            String text = gsMsg.getMessage("main.man002.24", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.usr.usr031kn.Usr031knAction")) {
            //ユーザマネージャー(追加/修正)確認
            String text = gsMsg.getMessage("user.usr031kn.2", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.usr.usr032.Usr032Action")) {
            //ユーザインポート
            String text = gsMsg.getMessage("user.usr032.3", null);
            return textKanri + " " + text;
        }
        if (id.equals("jp.groupsession.v2.usr.usr032kn.Usr032knAction")) {
            //ユーザインポート確認
            String text = gsMsg.getMessage("user.usr032kn.8", null);
            return textKanri + " " + text;
        }

        if (id.equals("jp.groupsession.v2.usr.usr050.Usr050Action")) {
            //パスワード変更
            String text = gsMsg.getMessage("cmn.change.password", null);
            return textKojin + " " + text;
        }


        return ret;
    }

    /**
     * 管理者設定の使用するプラグイン設定を反映したPluginConfigを取得します。
     * @param pconfig プラグイン設定
     * @param con コネクション
     * @return PluginConfig
     * @throws SQLException SQL実行時例外
     */
    public PluginConfig getPluginConfigForMain(PluginConfig pconfig, Connection con)
    throws SQLException {

        PluginConfig ret = new PluginConfig();
        //管理者設定を取得
        CmnTdispDao tdispDao = new CmnTdispDao(con);
        List<String> menuPluginIdList = tdispDao.getMenuPluginIdList(GSConst.SYSTEM_USER_ADMIN);

        Plugin plugin = null;
        for (String pId : menuPluginIdList) {
            plugin = pconfig.getPlugin(pId);
            if (plugin != null) {
                ret.addPlugin(plugin);
            }
        }

        return ret;
    }

    /**
     * <br>[機  能] 指定したユーザが使用可能なプラグインのプラグインIDを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @return プラグインID
     * @throws SQLException SQL実行時例外
     */
    public List<String> getCanUsePluginIdList(Connection con, int userSid)
    throws SQLException {

        //管理者設定を取得
        CmnTdispDao tdispDao = new CmnTdispDao(con);
        List<String> canUsePluginIdList = tdispDao.getMenuPluginIdList(GSConst.SYSTEM_USER_ADMIN);

        if (userSid > 0) {
            canUsePluginIdList = getPluginIdWithoutControl(con, canUsePluginIdList, userSid);
        }

        return canUsePluginIdList;
    }

    /**
     * 指定されたプラグインIDからプラグイン使用制限で使用不可のプラグインIDを除外する
     * @param con コネクション
     * @param pluginIdList プラグインID
     * @param userSid ユーザSID
     * @return プラグインID
     * @throws SQLException SQL実行時例外
     */
    public List<String> getPluginIdWithoutControl(Connection con, List<String> pluginIdList,
                                                int userSid)
    throws SQLException {
        List<String> controlList = new ArrayList<String>();
        controlList.addAll(pluginIdList);

        //グループ・ユーザ指定のプラグインID一覧を取得する
        CmnPluginControlDao controlDao = new CmnPluginControlDao(con);
        List<CmnPluginControlModel> limitPluginList
                = controlDao.getMemberControlPluginList(pluginIdList);
        List<String> limitPluginIdList = new ArrayList<String>();

        //プラグインIDリストを作成
        if (!limitPluginList.isEmpty()) {
            for (CmnPluginControlModel ctrlModel : limitPluginList) {
                limitPluginIdList.add(ctrlModel.getPctPid());
            }
        }

        //アクセス権設定を行っているプラグインIDを取得
        CmnPluginControlMemberDao memberDao = new CmnPluginControlMemberDao(con);
        List<String> setteiList = memberDao.getCantUsePluginList(limitPluginIdList, userSid);

        boolean setteiFlg = false;
        if (!limitPluginList.isEmpty()) {
            for (CmnPluginControlModel ctrlModel : limitPluginList) {

                //設定存在フラグ
                setteiFlg = false;
                for (String setPlugin : setteiList) {
                    if (setPlugin.equals(ctrlModel.getPctPid())) {
                        setteiFlg = true;
                        break;
                    }
                }

                if (ctrlModel.getPctType() == GSConstMain.PCT_TYPE_PERMIT) {
                    if (setteiFlg) {
                        //制限ユーザに指定されている場合
                        controlList.remove(ctrlModel.getPctPid());
                    }
                } else {
                    if (!setteiFlg) {
                        //許可ユーザに指定されていない場合
                        controlList.remove(ctrlModel.getPctPid());
                    }

                }
            }
        }

        return controlList;
    }

    /**
     * <br>[機  能] 指定したプラグインが使用可能なユーザを取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param pluginId プラグインID
     * @param userSidList ユーザSID
     * @return 指定したユーザSIDのうち、プラグインが使用可能なユーザのユーザSID
     * @throws SQLException SQL実行時例外
     */
    public List<Integer> getCanUsePluginUser(Connection con, String pluginId,
                                            List<Integer> userSidList)
    throws SQLException {

        List<Integer> retList = new ArrayList<Integer>();

        //プラグイン使用制限が未登録 or 使用制限区分 = 全てのユーザが使用可能 の場合
        //指定されたユーザSIDをそのまま返す
        CmnPluginControlDao ctrDao = new CmnPluginControlDao(con);
        CmnPluginControlModel ctrData = ctrDao.select(pluginId);
        if (ctrData == null || ctrData.getPctKbn() == GSConstMain.PCT_KBN_ALLOK) {
            return userSidList;
        }

        //設定されているユーザSID一覧を取得する。
        CmnPluginControlMemberDao memberDao = new CmnPluginControlMemberDao(con);
        List<Integer> memList = memberDao.getCantUseUserList(pluginId, userSidList);


        if (ctrData.getPctType() == GSConstMain.PCT_TYPE_PERMIT) {
            //制限方法が許可の場合
            retList.addAll(userSidList);
            for (Integer userId : memList) {
                retList.remove(userId);
            }

        } else {
            //制限方法が制限の場合
            retList.addAll(memList);
        }

        return retList;

    }

    /**
     * <br>[機  能] ショートメールが使用可能なユーザを取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSidList ユーザSID
     * @return 指定したユーザのうち、ショートメールが使用可能なユーザのユーザSID
     * @throws SQLException SQL実行時例外
     */
    public List<Integer> getCanUseSmailUser(Connection con, List<Integer> userSidList)
    throws SQLException {
        return getCanUsePluginUser(con, GSConstMain.PLUGIN_ID_SMAIL, userSidList);
    }

    /**
     * <br>[機  能] 回覧板が使用可能なユーザを取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSidList ユーザSID
     * @return 指定したユーザのうち、ショートメールが使用可能なユーザのユーザSID
     * @throws SQLException SQL実行時例外
     */
    public List<Integer> getCanUseCircularUser(Connection con, List<Integer> userSidList)
    throws SQLException {
        return getCanUsePluginUser(con, GSConstMain.PLUGIN_ID_CIRCULAR, userSidList);
    }

    /**
     * <p>バックアップリスナー実装クラスのリストを返す
     * @param pluginConfig プラグイン情報
     * @param con コネクション
     * @throws ClassNotFoundException 指定されたバックアップリスナークラスが存在しない
     * @throws IllegalAccessException バックアップリスナー実装クラスのインスタンス生成に失敗
     * @throws InstantiationException バックアップリスナー実装クラスのインスタンス生成に失敗
     * @throws SQLException 管理者設定 - プラグイン設定の取得に失敗
     * @return バッチリスナー
     */
    public IBatchBackupListener[] getBackupBatchListeners(PluginConfig pluginConfig, Connection con)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        List<IBatchBackupListener> batchList = new ArrayList<IBatchBackupListener>();

        //バックアップバッチリスナーを取得する
        PluginConfig pconfig = getPluginConfigForMain(pluginConfig, con);
        String[] backupBatchListenerClass = pconfig.getBackupBatchListeners();
        for (String listenerClass : backupBatchListenerClass) {
            Object obj = ClassUtil.getObject(listenerClass);
            batchList.add((IBatchBackupListener) obj);
        }

        return (IBatchBackupListener[]) batchList.toArray(
                        new IBatchBackupListener[batchList.size()]);
    }
    /**
     * <p>トップメニュー情報実装クラスのリストを返す
     * @param pluginConfig プラグイン情報
     * @param con コネクション
     * @throws ClassNotFoundException 指定されたトップメニュー情報リスナークラスが存在しない
     * @throws IllegalAccessException トップメニュー情報実装クラスのインスタンス生成に失敗
     * @throws InstantiationException トップメニュー情報実装クラスのインスタンス生成に失敗
     * @throws SQLException 管理者設定 - プラグイン設定の取得に失敗
     * @return バッチリスナー
     */
    public ITopMenuInfo[] getIMenuInfo(PluginConfig pluginConfig, Connection con)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        List<ITopMenuInfo> topMenuList = new ArrayList<ITopMenuInfo>();

        //バックアップバッチリスナーを取得する
        PluginConfig pconfig = getPluginConfigForMain(pluginConfig, con);
        String[] topMenuInfoClass = pconfig.getTopMenuInfoImpl();
        for (String listenerClass : topMenuInfoClass) {
            Object obj = ClassUtil.getObject(listenerClass);
            topMenuList.add((ITopMenuInfo) obj);
        }
        return (ITopMenuInfo[]) topMenuList.toArray(
                new ITopMenuInfo[topMenuList.size()]);
    }
    /**
     * プラグインIDを指定してアイコンのURLを取得します。
     * @param pluginId プラグインID
     * @param domain ドメイン
     * @return アイコンのURL
     */
    public String getPluginIconUrl(String pluginId, String domain) {

        String defaultIcon = "../main/images/menu_icon_single_info.gif";
        IGsResourceManager resourceManager = GroupSession.getResourceManager();
        PluginConfig pconf = resourceManager.getPluginConfig(domain);
        Plugin plugin = pconf.getPlugin(pluginId);
        AdminSettingInfo admInfo = plugin.getAdminSettingInfo();
        PrivateSettingInfo priInfo = plugin.getPrivateSettingInfo();
        String admIcon = admInfo.getIcon();
        String priIcon = priInfo.getIcon();
        if (!StringUtil.isNullZeroString(admIcon)) {
            return admIcon;
        }
        if (!StringUtil.isNullZeroString(priIcon)) {
            return priIcon;
        }
        return defaultIcon;
    }

    /**
     * <br>[機  能] 指定したユーザがプラグインの管理者かを判定します。
     * <br>[解  説]
     * <br>[備  考] システム管理グループに所属するユーザもプラグインの管理者として扱う
     * @param con コネクション
     * @param umodel セッションユーザ情報
     * @param pluginId プラグインID
     * @return true:プラグイン管理者 false:一般ユーザ
     * @throws SQLException SQL実行時例外
     */
    public boolean isPluginAdmin(Connection con, BaseUserModel umodel, String pluginId)
    throws SQLException {
        if (umodel.isAdmin()) {
            return true;
        } else if (StringUtil.isNullZeroString(pluginId)
                    || pluginId.equals(GSConst.PLUGINID_MAIN)) {
            return false;
        }

        return isPluginAdmin(con, umodel.getUsrsid(), pluginId);
    }


    /**
     * <br>[機  能] 指定したユーザがプラグインの管理者かを判定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID ユーザSID
     * @param pluginId プラグインID
     * @return true:プラグイン管理者 false:一般ユーザ
     * @throws SQLException SQL実行時例外
     */
    public boolean isPluginAdmin(Connection con, int userSid, String pluginId)
    throws SQLException {

        CmnPluginAdminDao dao = new CmnPluginAdminDao(con);
        int count = dao.getCountPluginAdmin(userSid, pluginId);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * <br>[機  能] APサーバ複数台の場合は、オペレーションログ設定を更新する。
     * <br>[解  説] DBの設定からGSContextを更新する。
     * <br>[備  考]
     * @param con コネクション
     * @param domain ドメイン
     * @throws SQLException SQL実行時例外
     */
    public void setOperationLogGsContext(Connection con,
                                         String domain) throws SQLException {

        if (CommonBiz.isMultiAP()) {
            //オペレーションログの再設定
            LoggingConfig loggingConfig = new LoggingConfig();

            CmnLogConfDao dao = new CmnLogConfDao(con);
            ArrayList<CmnLogConfModel> logConfList = dao.select();

            if (logConfList == null || logConfList.size() < 1) {
                GroupSession.getResourceManager().setLoggingConfig(domain, loggingConfig);
                return;
            }

            for (CmnLogConfModel model : logConfList) {
                loggingConfig.addLogConf(model);
            }

            //GSコンテキストへ設定
            GroupSession.getResourceManager().setLoggingConfig(domain, loggingConfig);
        }
    }

    /**
     * <br>[機  能] BatchStatusを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return BatchStatus
     */
    public static String getBatchStatus() {
        return NullDefault.getString(GsResourceBundle.getString(GSConst.BATCH_STATUS), "");
    }

    /**
     * <br>[機  能] Multi APの状態を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return Multi APの状態
     */
    public static boolean isMultiAP() {
        return NullDefault.getString(
                GsResourceBundle.getString(GSConst.MULTIAP), "").equals(GSConst.MULTIAP_MULTI);
    }

    /**
     * <br>[機  能] APサーバ番号を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return APサーバ番号の状態
     */
    public static int getApNumber() {
        if (!isMultiAP()) {
            return 0;
        }
        return NullDefault.getInt(
                GsResourceBundle.getString(GSConst.AP_NUMBER), 0);
    }

    /**
     * <br>[機  能] IPアドレス取得条件の状態を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return IPアドレス取得条件
     */
    public static boolean isIpAdrState() {
        return NullDefault.getString(
                GsResourceBundle.getString(
                        GSConst.IPADRSTATUS), "").equals(GSConst.IPADRSTATUS_TRUE);
    }

    /**
     * <br>[機  能] バッチリスナー実装クラスのリストを返す
     * <br>[解  説]
     * <br>[備  考] 管理者設定の使用するプラグインのリスナーのみ取得します。
     * @param pluginConfig プラグイン情報
     * @param con コネクション
     * @throws ClassNotFoundException 指定されたバッチリスナークラスが存在しない
     * @throws IllegalAccessException バッチリスナー実装クラスのインスタンス生成に失敗
     * @throws InstantiationException バッチリスナー実装クラスのインスタンス生成に失敗
     * @throws SQLException SQL実行例外
     * @return バッチリスナー
     */
    public IBatchListener[] getBatchListeners(PluginConfig pluginConfig, Connection con)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        log__.debug("getBatchListeners START");

        List<IBatchListener> batchList = new ArrayList<IBatchListener>();
        String batchStatus = CommonBiz.getBatchStatus();

        CommonBiz cmnBiz = new CommonBiz();
        PluginConfig pconfig = cmnBiz.getPluginConfigForMain(pluginConfig, con);

        String[] batchListenerClass = new String[0];
        //バッチリスナーを取得する
        if (batchStatus.equals(GSConst.BATCH_STATUS_TRUE)) {
            batchListenerClass = pconfig.getBatchListeners();
        } else if (batchStatus.equals(GSConst.BATCH_STATUS_LIMITATION)) {
            List<Plugin> pluginList = pconfig.getPluginDataList();
            List<String> batchListenerClassList = new ArrayList<String>();
            for (Plugin plugin : pluginList) {
                if (plugin.getBatchInfo().isLimitation()) {
                    batchListenerClassList.addAll(
                            Arrays.asList(pconfig.getBatchListeners(plugin.getId())));
                }
            }

            batchListenerClass
                = (String[]) batchListenerClassList.toArray(
                                                    new String[batchListenerClassList.size()]);
        }

        for (int i = 0; i < batchListenerClass.length; i++) {
            Object obj = ClassUtil.getObject(batchListenerClass[i]);
            batchList.add((IBatchListener) obj);
            log__.debug("BatchListener class = " + batchListenerClass[i]);
        }

        log__.debug("__getBatchListeners END");
        return (IBatchListener[]) batchList.toArray(new IBatchListener[batchList.size()]);
    }

    /**
     * <br>[機  能] メインインフォーメーションメッセージをセットする。
     * <br>[解  説]
     * <br>[備  考]
     * @param gsMsg Gsメッセージ
     * @param reqMdl リクエストモデル
     * @param con DBコネクション
     * @param usModel ユーザ情報
     * @param pconfig プラグイン設定情報
     * @param context コンテキスト
     * @return インフォーメーションメッセージリスト
     * @throws Exception インフォーメーション取得クラスの設定ミスの場合にスロー
     */
    public ArrayList<MainInfoMessageModel> getInfoMessageList(
            GsMessage gsMsg, RequestModel reqMdl,
            Connection con, BaseUserModel usModel,
            PluginConfig pconfig, ServletContext context) throws Exception {
        //        __setMainInfoMessage(con, usModel, pconfig, form);
        String [] pifclss = pconfig.getMainInfoMessageImpl();
        MainInfoMessage[] info = null;
        try {
            info = __getMainInfoMessages(pifclss);
        } catch (ClassNotFoundException e) {
            log__.error("クラスが見つかりません。設定を見直してください。", e);
            throw e;
        } catch (IllegalAccessException e) {
            log__.error("クラスの設定が間違っています。設定を見直してください。", e);
            throw e;
        } catch (InstantiationException e) {
            log__.error("クラスの設定が間違っています。設定を見直してください。", e);
            throw e;
        }

        //
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("realPath", context.getRealPath("/"));

        ArrayList<MainInfoMessageModel> msgs = new ArrayList<MainInfoMessageModel>();
        for (MainInfoMessage imsgCls : info) {
            List<MainInfoMessageModel> plist
                    = imsgCls.getMessage(paramMap, usModel.getUsrsid(), con, gsMsg, reqMdl);
            if (plist != null) {
                msgs.addAll(plist);
            }
        }

        //メインインフォメーション詳細POPUPURL
        String infoPopupUrl = "return openMainInfoWindow";

        //手動登録インフォメーションを取得
        Man320Biz biz = new Man320Biz();
        ArrayList<CmnInfoMsgModel> infoList = biz.getActiveInformationMsg(
                usModel.getUsrsid(), new UDate(), con);
        MainInfoMessageModel model = null;
        for (CmnInfoMsgModel infMdl : infoList) {
            model = new MainInfoMessageModel();
            model.setLinkUrl(infoPopupUrl + "(" + infMdl.getImsSid() + ")");
            model.setMessage(infMdl.getImsMsg());
            model.setPopupDsp(true);
            model.setIcon("../main/images/menu_icon_single_info.gif");
            msgs.add(model);
        }

        return msgs;
    }

    /**
     * <p>メッセージのリストを取得する
     * @param classNames プラグインクラス名
     * @throws ClassNotFoundException 指定されたクラスが存在しない
     * @throws IllegalAccessException 実装クラスのインスタンス生成に失敗
     * @throws InstantiationException 実装クラスのインスタンス生成に失敗
     * @return バッチリスナー
     */
    private MainInfoMessage[] __getMainInfoMessages(String[] classNames)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MainInfoMessage[] iclasses = new MainInfoMessage[classNames.length];
        for (int i = 0; i < classNames.length; i++) {
            Object obj = ClassUtil.getObject(classNames[i]);
            iclasses[i] = (MainInfoMessage) obj;
        }
        return iclasses;
    }

    /**
     * <br>[機  能] ポータル管理者権限があるか判定する。
     * <br>[解  説]
     * <br>[備  考] ポータルプラグインが使用可能かつシステム管理者かプラグイン管理者ならばtrue
     * @param con コネクション
     * @param usModel ユーザモデル
     * @param pconfig プラグイン設定
     * @throws SQLException SQL実行例外
     * @return バッチリスナー
     */
    public boolean isPortalAdmin(Connection con, BaseUserModel usModel, PluginConfig pconfig)
    throws SQLException {

        boolean adminFlg = false;

        //ポータルプラグインが使用可能ならばtrue
        if (isCanUsePlugin(GSConstMain.PLUGIN_ID_PORTAL, pconfig)) {
            //システム管理者かプラグイン管理者ならばtrue
            adminFlg = isPluginAdmin(con, usModel, GSConstMain.PLUGIN_ID_PORTAL);
        }
        return adminFlg;
    }

    /**
     * [機  能] プラグインポートレット選択画面のコンボボックスを取得する。
     * [解  説] 指定されたユーザが使用不可のプラグインは除外します<br>
     * [備  考] <br>
     * @param con コネクション
     * @param gsMsg GSメッセージ
     * @param domain ドメイン
     * @return PluginConfig
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getPluginPortletCombo(
            Connection con, GsMessage gsMsg, String domain)
    throws SQLException {
        List<LabelValueBean> combo = new ArrayList<LabelValueBean>();
        //ポータル プラグイン選択画面はデフォルトとして設定する。
        combo.add(new LabelValueBean(gsMsg.getMessage("plugin.portlet"),
                                    "ptl080"));

        IGsResourceManager resourceManager = GroupSession.getResourceManager();
        PluginConfig pconfig = resourceManager.getPluginConfig(domain);
        List<String> pluginIdList = getCanUsePluginIdList(con, -1);

        if (!pluginIdList.isEmpty()) {
            List<PortletInfo> portletList = new ArrayList<PortletInfo>();
            Plugin plugin = null;
            for (String pId : pluginIdList) {
                plugin = pconfig.getPlugin(pId);
                if (plugin != null) {
                    portletList.addAll(plugin.getPortletInfo());
                }
            }

            Collections.sort(portletList);
            for (PortletInfo portlet : portletList) {
                combo.add(new LabelValueBean(gsMsg.getMessage(portlet.getListNameId()),
                        portlet.getListId()));
            }
        }

        return combo;
    }

    /**
     * [機  能] プラグインポートレットの画面IDを取得する。
     * [解  説] <br>
     * [備  考] <br>
     * @param pconfig PluginConfig
     * @param pluginId プラグインID
     * @param screenId 選択画面ID
     * @return dspScreenId ポートレット画面ID
     */
    public String getPluginPortletScreenId(
            PluginConfig pconfig, String pluginId, String screenId) {

        String dspScreenId = "";

        ArrayList<PortletInfo> portletInfoList = pconfig.getPlugin(pluginId).getPortletInfo();

        for (PortletInfo pInfo : portletInfoList) {
            String listId = pInfo.getListId();
            if (!StringUtil.isNullZeroStringSpace(listId)) {
                if (listId.equals(screenId)) {
                    dspScreenId = pInfo.getId();
                    break;
                }
            }
        }

        return dspScreenId;
    }

    /**
     * <br>[機  能] Group SESSION biz URL を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return Group SESSION biz URL
     */
    public static String getBizUrl() {
        String bizUrl = ConfigBundle.getValue("GS_BIZ_URL");
        if (!bizUrl.endsWith("/")) {
            bizUrl += "/";
        }
        return bizUrl;
    }

    /**
     * <br>[機  能] 拡張日付を取得する
     * <br>[解  説]
     * <br>[備  考] "日" = 末日の場合、月の末日を設定する
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 拡張日付
     */
    public static UDate createExDate(String year, String month, String day) {
        return createExDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    /**
     * <br>[機  能] 拡張日付を取得する
     * <br>[解  説]
     * <br>[備  考] "日" = 末日の場合、月の末日を設定する
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 拡張日付
     */
    public static UDate createExDate(int year, int month, int day) {
        UDate date = new UDate();
        date.setDate(year, month, 1);
        date.setDay(getExDay(date, day));
        return date;
    }

    /**
     * <br>[機  能] 拡張日付 日を取得する
     * <br>[解  説]
     * <br>[備  考] "日" = 末日の場合、月の末日を返す
     * @param date 拡張日付
     * @param day 日
     * @return 拡張日付 日
     */
    public static int getExDay(UDate date, int day) {
        if (day == GSConstCommon.LAST_DAY_OF_MONTH) {
            return date.getMaxDayOfMonth();
        }
        return day;
    }

    /**
     * <br>[機  能] 拡張日付 日を取得する
     * <br>[解  説]
     * <br>[備  考] "日" = 末日の場合、月の末日を返す
     * @param date 拡張日付
     * @param day 日
     * @return 拡張日付 日
     */
    public static String getExDay(UDate date, String day) {
        String exDay = NullDefault.getString(day, "");
        if (exDay.equals(Integer.toString(GSConstCommon.LAST_DAY_OF_MONTH))) {
            exDay = Integer.toString(date.getMaxDayOfMonth());
        }
        return exDay;
    }

    /**
     * <br>[機  能] マルチアカウント 登録者コンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @param reqMdl リクエスト情報
     * @return 登録者コンボ
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getMARegistrantCombo(Connection con, int userSid,
                                                                                RequestModel reqMdl)
    throws SQLException {
        List<LabelValueBean> registList = new ArrayList<LabelValueBean>();

        //ユーザを設定
        UserBiz userBiz = new UserBiz();
        List<BaseUserModel> userList
            = userBiz.getBaseUserList(con, new String[] {Integer.toString(userSid)});
        registList.add(new LabelValueBean(userList.get(0).getUsiseimei(), "0"));

        //ユーザの所属グループを設定
        GsMessage gsMsg = new GsMessage(reqMdl);
        GroupBiz grpBiz = new GroupBiz();
        registList.addAll(
                grpBiz.getBelongGroupLabelList(userSid, con, false, gsMsg));

        return registList;
    }

    /** KB */
    private static final long KB__ = 1024;
    /** MB */
    private static final long MB__ = (long) Math.pow(1024, 2);
    /** GB */
    private static final long GB__ = (long) Math.pow(1024, 3);
    /**
     * <br>[機  能] ディスクサイズの変換を行う
     * <br>[解  説] サイズに応じた単位を付加する
     * <br>[備  考]
     * @param diskSize ディスクサイズ
     * @return ディスクサイズ(換算)
     */
    public String convertDiskSize(long diskSize) {

        String strDiskSize = "";
        BigDecimal decDiskSize = new BigDecimal(diskSize);
        if (diskSize >= MB__  && diskSize < GB__) {
            strDiskSize
                    = decDiskSize.divide(new BigDecimal(MB__), 1,
                                            BigDecimal.ROUND_HALF_UP).toPlainString() + "M";
        } else if (diskSize >= GB__ && diskSize >= GB__) {
            strDiskSize
                    = decDiskSize.divide(new BigDecimal(GB__), 1,
                                            BigDecimal.ROUND_HALF_UP).toPlainString() + "G";
        } else {
            strDiskSize
            = decDiskSize.divide(new BigDecimal(KB__), 0,
                                        BigDecimal.ROUND_HALF_UP).toPlainString() + "K";
        }
        return strDiskSize + "B";
    }

    /**
     * <br>[機  能] アクセス元のIPアドレスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return メッセージ
     */
    public static String getRemoteAddr(HttpServletRequest req) {

        String remoteAddr = req.getRemoteAddr();

        if (CommonBiz.isIpAdrState()) {
            remoteAddr = req.getHeader("x-forwarded-for");

            //取得できない場合は通常の取得処理
            if (StringUtil.isNullZeroStringSpace(remoteAddr)) {
                remoteAddr = req.getRemoteAddr();
            }

        } else {
            remoteAddr = req.getRemoteAddr();
        }

        return remoteAddr;
    }

    /**
     * <br>[機  能] 指定したバイナリSIDがユーザ画像のバイナリSIDか、また
     *                    その画像が公開されているかチェックする
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param binSid バイナリSID
     * @param usrSid ユーザSID
     * @return true:ユーザ画像のbinSid false:それ以外
     * @throws SQLException SQL実行時例外
     */
    public boolean isCheckUserImage(Connection con, Long binSid, int usrSid) throws SQLException {

        CmnUsrmInfDao dao = new CmnUsrmInfDao(con);
        return dao.isCheckUserImage(binSid, usrSid);
    }
    /**
     *
     * <br>[機  能] リダイレクト先のURLがWEB-INF以下のファイルを参照していないことを確認する
     * <br>[解  説]
     * <br>[備  考]
     * @param url リダイレクト先
     * @return 正常の場合 true
     */
    public boolean isCheckRedirectUrl(String url) {
        if (StringUtil.isNullZeroStringSpace(url)) {
            return false;
        }
        String upUrl = url.toUpperCase();
        if (upUrl.indexOf("/WEB-INF/") >= 0) {
            return false;
        }
        return true;
    }

    /**
     * バイナリーデータの保存先ディレクトリPATHを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションROOT
     * @return String バイナリーデータの保存先ディレクトリPATH
     */
    public String getSsoKeyword(String appRootPath) {
        String keyword = null;
        if (ConfigBundle.getValue("SSO_KEYWORD") != null) {
            //設定ファイル(gsdata.conf)の指定ディレクトリ
            keyword = ConfigBundle.getValue("SSO_KEYWORD");
        }

        return keyword;
    }


    /**
     * <br>[機  能] 文字列内に${USERID}${PASSWORD}等の予約語があった場合に置換えを行なう
     * <br>[解  説]
     * <br>[備  考] urlEncFlg=true時、予約語で置き換わる文字のみURLエンコードを実行
     *
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param str 文字列
     * @param appRootPath アプリケーションルートパス
     * @param usrInfMdl ユーザ個人情報モデル
     * @param urlEncFlg URLエンコードフラグ  true:エンコードする  false:しない
     * @param useTimeFlg
     * @return メニュー情報
     * @throws SQLException SQL実行例外
     * @throws EncryptionException 暗号化に失敗時例外
     * @throws NoSuchAlgorithmException SHA-512アルゴリズムが使用不可
     * @throws UnsupportedEncodingException 文字エンコード時例外
     */
    public String replaceGSReservedWord(
            RequestModel reqMdl,
            Connection con,
            String appRootPath,
            String str,
            CmnUsrmInfModel usrInfMdl,
            boolean urlEncFlg)
                    throws SQLException, EncryptionException,
                    NoSuchAlgorithmException, UnsupportedEncodingException {

        //置き換え１
        String repStr = replaceGSReservedWordNoTime(
                reqMdl, con, appRootPath, str, usrInfMdl, urlEncFlg);

        //置き換え２（TIME、HASHのみ）
        repStr = replaceGSReservedWordOnlyTime(
                reqMdl, con, appRootPath, repStr);

        return repStr;

    }

    /**
     * <br>[機  能] タイムスタンプを必要としない予約語を置き換える
     * <br>[解  説]
     * <br>[備  考] urlEncFlg=true時、予約語で置き換わる文字のみURLエンコードを実行
     *
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param str 文字列
     * @param appRootPath アプリケーションルートパス
     * @param usrInfMdl ユーザ個人情報モデル
     * @param urlEncFlg URLエンコードフラグ  true:エンコードする  false:しない
     * @param useTimeFlg
     * @return メニュー情報
     * @throws SQLException SQL実行例外
     * @throws EncryptionException 暗号化に失敗時例外
     * @throws NoSuchAlgorithmException SHA-512アルゴリズムが使用不可
     * @throws UnsupportedEncodingException 文字エンコード時例外
     */
    public String replaceGSReservedWordNoTime(
            RequestModel reqMdl,
            Connection con,
            String appRootPath,
            String str,
            CmnUsrmInfModel usrInfMdl,
            boolean urlEncFlg)
                    throws SQLException, EncryptionException,
                    NoSuchAlgorithmException, UnsupportedEncodingException {
        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int usrSid = usModel.getUsrsid(); //セッションユーザSID

        GsMessage gsMsg = new GsMessage(reqMdl);

        if (str.indexOf("${USERID}") != -1) {
            //ユーザsidを取得
            if (usrSid > 0) {
                str = str.replace("${USERID}", usModel.getLgid());
            }
        }
        if (str.indexOf("${PASSWORD}") != -1) {
            //LDAPプラグインの使用を判定し、パスワードを取得する
            boolean ldapFlg = false;
            CmnTdispDao dispDao = new CmnTdispDao(con);
            List<CmnTdispModel> dispList = dispDao.getAdminTdispList();
            for (CmnTdispModel tdispMdl : dispList) {
                //LDAPプラグインを使用している場合はパスワードを取得しない
                if (tdispMdl.getTdpPid().equals(LdapConst.PROTOCOL_LDAP)) {
                    ldapFlg = true;
                }
            }
            if (!ldapFlg) {
                //パスワードを取得
                String pass = null;
                CmnUsrmDao udao = new CmnUsrmDao(con);
                CmnUsrmModel sumodel = udao.select(usrSid);
                pass = GSPassword.getDecryPassword(sumodel.getUsrPswd());
                if (!StringUtil.isNullZeroString(pass)) {
                    str = str.replace("${PASSWORD}", pass);
                }
            }
        }

        if (str.indexOf("${GROUPSID}") != -1) {
            //デフォルトグループSIDを取得
            if (usrSid > 0) {
                GroupBiz gb = new GroupBiz();
                int grpSid = gb.getDefaultGroupSid(usrSid, con);
                str = str.replace("${GROUPSID}", String.valueOf(grpSid));
            }
        }

        if (str.indexOf("${GROUPID}") != -1) {
            //デフォルトグループIDを取得
            if (usrSid > 0) {
                GroupBiz gb = new GroupBiz();
                String grpId = gb.getDefaultGroupId(usrSid, con);
                str = str.replace("${GROUPID}", NullDefault.getString(grpId, ""));
            }
        }

        if (usrInfMdl != null) {
            //社員番号
            if (str.indexOf("${SYAINNO}") != -1) {
                str = str.replace("${SYAINNO}",
                        __urlEncode(
                                NullDefault.getString(usrInfMdl.getUsiSyainNo(), ""), urlEncFlg));
            }

            //氏名（姓名）
            if (str.indexOf("${NAME}") != -1) {
                str = str.replace("${NAME}",
                        __urlEncode(usrInfMdl.getUsiSei() + usrInfMdl.getUsiMei(), urlEncFlg));
            }

            //氏名（姓のみ）
            if (str.indexOf("${SEI}") != -1) {
                str = str.replace("${SEI}", __urlEncode(usrInfMdl.getUsiSei(), urlEncFlg));
            }

            //氏名（名のみ）
            if (str.indexOf("${MEI}") != -1) {
                str = str.replace("${MEI}",
                        __urlEncode(usrInfMdl.getUsiMei(), urlEncFlg));
            }

            //氏名カナ(姓名)
            if (str.indexOf("${NAMEKANA}") != -1) {
                str = str.replace("${NAMEKANA}",
                        __urlEncode(usrInfMdl.getUsiSeiKn() + usrInfMdl.getUsiMeiKn(), urlEncFlg));
            }

            //氏名カナ(姓のみ)
            if (str.indexOf("${SEIKANA}") != -1) {
                str = str.replace("${SEIKANA}", __urlEncode(usrInfMdl.getUsiSeiKn(), urlEncFlg));
            }

            //氏名カナ(名のみ)
            if (str.indexOf("${MEIKANA}") != -1) {
                str = str.replace("${MEIKANA}", __urlEncode(usrInfMdl.getUsiMeiKn(), urlEncFlg));
            }

            //所属
            if (str.indexOf("${SYOZOKU}") != -1) {
                str = str.replace("${SYOZOKU}",
                        __urlEncode(
                                NullDefault.getString(usrInfMdl.getUsiSyozoku(), ""), urlEncFlg));
            }

            //役職
            if (str.indexOf("${POSITION}") != -1) {
                int posSid = usrInfMdl.getPosSid();
                CmnPositionDao posDao = new CmnPositionDao(con);
                CmnPositionModel posMdl = posDao.getPosInfo(posSid);
                if (posMdl != null) {
                    str = str.replace("${POSITION}",
                            __urlEncode(posMdl.getPosName(), urlEncFlg));
                } else {
                    str = str.replace("${POSITION}", "");
                }
            }

            //性別
            if (str.indexOf("${SEIBETU}") != -1) {

                int seibetu = usrInfMdl.getUsiSeibetu();
                String strSeibetu = null;
                if (seibetu == GSConstUser.SEIBETU_MAN) {
                    strSeibetu = gsMsg.getMessage("user.124");
                } else if (seibetu == GSConstUser.SEIBETU_WOMAN) {
                    strSeibetu = gsMsg.getMessage("user.125");
                } else {
                    strSeibetu = gsMsg.getMessage("cmn.notset");
                }

                str = str.replace("${SEIBETU}", __urlEncode(strSeibetu, urlEncFlg));
            }

            //入社年月日(西暦)
            if (str.indexOf("${EDATE}") != -1) {
                if (usrInfMdl.getUsiEntranceDate() != null) {
                    str = str.replace("${EDATE}", usrInfMdl.getUsiEntranceDate().getDateString());
                } else {
                    str = str.replace("${EDATE}", "");
                }
            }

            //生年月日(西暦)
            if (str.indexOf("${BDATE}") != -1) {
                if (usrInfMdl.getUsiBdate() != null) {
                    str = str.replace("${BDATE}", usrInfMdl.getUsiBdate().getDateString());
                } else {
                    str = str.replace("${BDATE}", "");
                }
            }

            //メールアドレス１
            if (str.indexOf("${MAIL1}") != -1) {
                str = str.replace("${MAIL1}", NullDefault.getString(usrInfMdl.getUsiMail1(), ""));
            }

            //メールアドレス２
            if (str.indexOf("${MAIL2}") != -1) {
                str = str.replace("${MAIL2}", NullDefault.getString(usrInfMdl.getUsiMail2(), ""));
            }

            //メールアドレス３
            if (str.indexOf("${MAIL3}") != -1) {
                str = str.replace("${MAIL3}", NullDefault.getString(usrInfMdl.getUsiMail3(), ""));
            }

            //住所 郵便番号（ハイフン付き）
            if (str.indexOf("${ZIP}") != -1) {
                str = str.replace("${ZIP}",
                        NullDefault.getString(usrInfMdl.getUsiZip1(), "")
                        + "-"
                        + NullDefault.getString(usrInfMdl.getUsiZip2(), ""));
            }

            //住所１
            if (str.indexOf("${ADDR1}") != -1) {
                str = str.replace("${ADDR1}",
                        __urlEncode(
                                NullDefault.getString(usrInfMdl.getUsiAddr1(), ""), urlEncFlg));
            }

            //住所２
            if (str.indexOf("${ADDR2}") != -1) {
                str = str.replace("${ADDR2}",
                        __urlEncode(
                                NullDefault.getString(usrInfMdl.getUsiAddr2(), ""), urlEncFlg));
            }

            //電話番号１
            if (str.indexOf("${TEL1}") != -1) {
                str = str.replace("${TEL1}",
                        NullDefault.getString(usrInfMdl.getUsiTel1(), ""));
            }

            //電話番号２
            if (str.indexOf("${TEL2}") != -1) {
                str = str.replace("${TEL2}",
                        NullDefault.getString(usrInfMdl.getUsiTel2(), ""));
            }

            //電話番号３
            if (str.indexOf("${TEL3}") != -1) {
                str = str.replace("${TEL3}",
                        NullDefault.getString(usrInfMdl.getUsiTel3(), ""));
            }

            //ＦＡＸ１
            if (str.indexOf("${FAX1}") != -1) {
                str = str.replace("${FAX1}",
                        NullDefault.getString(usrInfMdl.getUsiFax1(), ""));
            }

            //ＦＡＸ２
            if (str.indexOf("${FAX2}") != -1) {
                str = str.replace("${FAX2}",
                        NullDefault.getString(usrInfMdl.getUsiFax2(), ""));
            }

            //ＦＡＸ３
            if (str.indexOf("${FAX3}") != -1) {
                str = str.replace("${FAX3}",
                        NullDefault.getString(usrInfMdl.getUsiFax3(), ""));
            }
        }

        return str;
    }

    /**
     * <br>[機  能] タイムスタンプを必要とするGS予約語を置き換える。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @param str 文字列
     * @param appRootPath アプリケーションルートパス
     * @param useTimeFlg
     * @return メニュー情報
     * @throws NoSuchAlgorithmException SHA-512アルゴリズムが使用不可
     */
    public String replaceGSReservedWordOnlyTime(
            RequestModel reqMdl,
            Connection con,
            String appRootPath,
            String str)
                    throws NoSuchAlgorithmException {
        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();

        //キーワード
        CommonBiz cmnBiz = new CommonBiz();
        String keyword = cmnBiz.getSsoKeyword(appRootPath);

        //タイムスタンプ（取得時時間）
        UDate now = new UDate();
        if (str.indexOf("${TIME}") != -1) {
            str = str.replace("${TIME}", now.getTimeStamp());
        }

        //ハッシュ( ユーザSID＋タイムスタンプ＋キーワード の連結文字列をSHA-512で暗号化したもの）
        if (str.indexOf("${HASH_UID_TM_KW}") != -1) {

            String hashkey = usModel.getLgid()
                    + now.getTimeStamp()
                    + NullDefault.getString(keyword, "");

            str = str.replace("${HASH_UID_TM_KW}", Sha.encryptSha512AsHex(hashkey));
        }

        return str;
    }

    /**
     * <br>[機  能] 指定文字をURLエンコードする
     * <br>[解  説]
     * <br>[備  考]
     * @param str 文字
     * @param encFlg エンコードフラグ true:エンコードする  false:しない
     * @return エンコード文字列
     * @throws UnsupportedEncodingException 文字エンコード時例外
     */
    private String __urlEncode(String str, boolean encFlg) throws UnsupportedEncodingException {
        if (encFlg) {
            str = URLEncoder.encode(str, Encoding.UTF_8);
        }

        return str;
    }
}