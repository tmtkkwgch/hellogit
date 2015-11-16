package jp.groupsession.v2.fil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.batch.IBatchListener;
import jp.groupsession.v2.batch.IBatchModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.StatisticsBiz;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.fil.dao.FileDAccessConfDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.dao.FileFileTextDao;
import jp.groupsession.v2.fil.dao.FileLogCountSumDao;
import jp.groupsession.v2.fil.extractor.FileTextExtractor;
import jp.groupsession.v2.fil.model.FileAconfModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileFileBinModel;
import jp.groupsession.v2.fil.model.FileFileTextModel;
import jp.groupsession.v2.fil.model.FileLogCountSumModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.dao.base.FileLogAdelDao;
import jp.groupsession.v2.man.model.base.FileLogAdelModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] バッチ処理起動時に実行される処理を実装
 * <br>[解  説] ファイル管理についての処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class FileBatchListenerImpl implements IBatchListener {
    /** ロギングクラス */
    private static Log log__ = LogFactory.getLog(FileBatchListenerImpl.class);

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public FileBatchListenerImpl() {
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
        log__.info("ファイル管理バッチ処理開始");

        //アプリケーションのルートパスを取得
        String rootPath = "";
        GSContext gscontext = param.getGsContext();
        Object pathObj = gscontext.get(GSContext.APP_ROOT_PATH);
        if (pathObj != null) {
            rootPath = (String) pathObj;
        }

        con.setAutoCommit(false);
        boolean commitFlg = false;

        UDate date = new UDate();
        date.setZeroHhMmSs();
        try {
            //
            FilCommonBiz cBiz = new FilCommonBiz();
            FileAconfModel admMdl = cBiz.getFileAconfModel(con);
//            FileAconfDao admDao = new FileAconfDao(con);
//            FileAconfModel admMdl = admDao.select();
            int saveDays = admMdl.getFacSaveDays();

            date.addDay(saveDays * -1);
            log__.debug("削除基準日==>" + date.getDateString());

            //保存期間を過ぎた状態区分=削除のディレクトリを物理削除します
            FileDirectoryDao dirDao = new FileDirectoryDao(con);
            FileFileBinDao fileDao = new FileFileBinDao(con);
            FileDAccessConfDao daConfDao = new FileDAccessConfDao(con);
            ArrayList<FileDirectoryModel> dirList = dirDao.getDeletedDirectory(date);

            //実態を物理削除
            for (FileDirectoryModel mdl : dirList) {
                daConfDao.deleteSubDirectoriesFiles(mdl.getFdrSid(), false);
                dirDao.delete(mdl.getFdrSid());
                __deleteCmnFile(mdl.getFdrSid(), 0, con);
                fileDao.delete(mdl.getFdrSid());
            }
            //ファイルの実態を物理削除
            __deleteFile(rootPath, con);

            //未登録の統計情報_集計結果を登録
            __entryLogSum(con);

            //ファイル管理 統計情報集計データ削除
            __deleteLog(con);

            commitFlg = true;
            log__.debug("ファイル管理バッチ処理終了");
        } catch (SQLException e) {
            log__.error("ファイル管理バッチ処理失敗", e);
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
     * <br>[機  能] 共通ファイル情報を削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param usrSid ユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException IOエラー
     */
    private void __deleteCmnFile(int dirSid, int usrSid, Connection con)
    throws SQLException, IOToolsException {

        FileFileBinDao fileDao = new FileFileBinDao(con);
        CmnBinfDao cbDao = new CmnBinfDao(con);
        CmnBinfModel cmnBinModel = new CmnBinfModel();
        UDate now = new UDate();

        //添付ファイルリストを取得
        List<FileFileBinModel> binList = fileDao.select(dirSid);

        if (binList != null && binList.size() > 0) {
            List<Long> binSidList = new ArrayList<Long>();
            for (FileFileBinModel binModel : binList) {
                binSidList.add(binModel.getBinSid());
            }
            cmnBinModel.setBinJkbn(GSConst.JTKBN_DELETE);
            cmnBinModel.setBinUpuser(usrSid);
            cmnBinModel.setBinUpdate(now);

            //バイナリー情報を論理削除する
            cbDao.updateJKbn(cmnBinModel, binSidList);
        }
    }

    /**
     * 論理削除しているファイルを物理削除します
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param rootPath APP_ROOT_PATH
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス例外
     */
    private void __deleteFile(String rootPath, Connection con)
    throws SQLException, IOToolsException {
        //論理削除済みのバイナリー情報を取得する
        CmnBinfDao cbDao = new CmnBinfDao(con);
        List < CmnBinfModel > cbList = cbDao.getDeleteFile();

        //バイナリーSIDリスト
        String[] binList = new String[cbList.size()];

        log__.debug("ファイルを削除");
        CommonBiz cmnBiz = new CommonBiz();
        for (int i = 0; i < cbList.size(); i++) {
            CmnBinfModel cbMdl = cbList.get(i);

            //添付ファイルを削除する。
            cmnBiz.deleteFile(cbMdl, rootPath);

            //バイナリーSIDリストに追加
            binList[i] = String.valueOf(cbMdl.getBinSid());
        }

        log__.debug("バイナリーSIDを複数指定し、バイナリー情報を削除する");
        cmnBiz.deleteBinInf(con, binList);
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
        log__.info("ファイル管理5分バッチ処理開始");

        //アプリケーションのルートパスを取得
        String rootPath = "";
        GSContext gscontext = param.getGsContext();
        Object pathObj = gscontext.get(GSContext.APP_ROOT_PATH);
        if (pathObj != null) {
            rootPath = (String) pathObj;
        }

        //全文検索使用時
        if (FilCommonBiz.isUseAllTextSearch(rootPath)) {
            con.setAutoCommit(false);

            //まだテキストの読込みが完了していないファイル一覧を取得する
            FileFileTextDao textDao = new FileFileTextDao(con);
            List<FileFileTextModel> list = textDao.getNoReadFileList();
            if (list.size() == 0) {
                log__.info("ファイル内容読込み対象なし");
                return;
            }

            //テンポラリディレクトリパスを取得
            CommonBiz cmnBiz = new CommonBiz();
            String tempDir = cmnBiz.getTempDir(
                    GroupSession.getResourceManager().getTempPath(param.getDomain())
                    , GSConstFile.PLUGIN_ID_FILE);

            try {
                //ファイル内容を読み込んで、更新
                int num = 1;

                for (FileFileTextModel bean : list) {
                    //削除
                    __deleteFileText(con, bean.getFdrSid());
                    //追加
//                UDate start = new UDate();
                    String tempSubDir = IOTools.replaceFileSep(
                            tempDir + "/" + StringUtil.toDecFormat(num, "0000000000") + "/");
                    __insertFileText(con, param, tempSubDir, rootPath, bean);
//                log__.debug("[" + bean.getFdrSid() + "] file read time => "
//                        + UDateUtil.diffMillis(start, new UDate()) + "ms");
                    num++;
                }
            } catch (Exception e) {
                throw e;
            } finally {
                //テンポラリディレクトリのファイルを削除する（※消しそこない防止）
                IOTools.deleteInDir(tempDir);
                IOTools.deleteDir(tempDir);     //ディレクトリも削除
            }

        }

        log__.info("ファイル管理5分バッチ処理終了");
    }

    /**
     * ファイルテキスト情報データの削除
     * @param con DBコネクション
     * @param fdrSid フォルダSID
     * @throws SQLException SQL実行例外
     */
    private void __deleteFileText(Connection con, int fdrSid) throws SQLException {

        try {
            FileFileTextDao textDao = new FileFileTextDao(con);
            textDao.delete(fdrSid);
            con.commit();
        } catch (SQLException e) {
            JDBCUtil.rollback(con);
            throw e;
        }
    }

    /**
     * ファイルテキスト情報データの追加
     * @param con DBコネクション
     * @param param バッチ処理時に使用する情報
     * @param tempDir テンポラリディレクトリ
     * @param appRoot アプリケーションのルートパス
     * @param textMdl ファイル内容モデル
     * @throws Exception 実行例外
     */
    private void __insertFileText(Connection con, IBatchModel param,
                                String tempDir, String appRoot, FileFileTextModel textMdl)
                        throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        FileFileBinDao fileBinDao = new FileFileBinDao(con);
        FilProcessExtractorCallback reg = new FilProcessExtractorCallback(con, textMdl);

        try {
            //バイナリファイル取得
            Long binSid = fileBinDao.getCmnBinSid(textMdl.getFdrSid(), textMdl.getFdrVersion());
            if (binSid == 0) {
                return;
            }

            // 添付ファイル情報を取得する。
            CmnBinfModel cbMdl = cmnBiz.getBinInfo(con, binSid, param.getDomain());
            log__.debug("読込ファイル名->" + cbMdl.getBinFileName());

            //添付ファイルをテンポラリディレクトリにコピーする。
            String tempfile = cmnBiz.saveTempFile(cbMdl, appRoot, tempDir);
            if (tempfile == null) {
                return;
            }

            // ファイル中身を抽出
            FileTextExtractor extractor = new FileTextExtractor();
            extractor.setCallback(reg);
            extractor.setMaxLength(500000);
            extractor.read(tempfile);

            // コミット
            con.commit();

        } catch (SQLException e) {
            // エラー
            throw e;
        } catch (Exception e) {
            // エラー
            reg.onError(e);
            log__.warn("ファイル読込みエラー", e);
        } finally {
            //テンポラリディレクトリの添付ファイルを削除する
            IOTools.deleteDir(tempDir);
        }
    }

    /**
     * <br>[機  能] 未登録の統計情報_集計結果を登録
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @exception SQLException SQL実行時例外
     */
    private void __entryLogSum(Connection con) throws SQLException {
        //統計情報の集計を行う
        FileLogCountSumDao logSumDao = new FileLogCountSumDao(con);
        int[] flsKbnList = {GSConstFile.FLS_KBN_DOWNLOAD,
                                GSConstFile.FLS_KBN_UPLOAD
        };

        for (int flsKbn : flsKbnList) {
            List<FileLogCountSumModel> logSumList
                = logSumDao.getNonRegisteredList(flsKbn);
            if (logSumList != null && !logSumList.isEmpty()) {
                for (FileLogCountSumModel logSumMdl : logSumList) {
                    if (logSumDao.update(logSumMdl) == 0) {
                        logSumDao.insert(logSumMdl);
                    }
                }
            }
        }
    }

    /**
     * <br>[機  能] 統計情報の集計データを削除します
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @exception SQLException SQL実行時例外
     */
    private void __deleteLog(Connection con) throws SQLException {
        log__.debug("ファイル管理 統計情報集計データ削除処理開始");
        FileLogAdelModel filLogAdelMdl = null;
        FileLogAdelDao filLogAdelDao = new FileLogAdelDao(con);
        List<FileLogAdelModel> adelList = filLogAdelDao.select();
        if (adelList != null && !adelList.isEmpty()) {
            filLogAdelMdl = adelList.get(0);
        }
        if (filLogAdelMdl != null
                && filLogAdelMdl.getFldDelKbn() == GSConstMain.LAD_DELKBN_AUTO) {
            int filLogAdelYear = filLogAdelMdl.getFldDelYear();
            int filLogAdelMonth = filLogAdelMdl.getFldDelMonth();

            //削除する境界の日付を設定する
            UDate filLogDelUd = new UDate();
            log__.debug("現在日 = " + filLogDelUd.getDateString());
            log__.debug("削除条件 経過年 = " + filLogAdelYear);
            log__.debug("削除条件 経過月 = " + filLogAdelMonth);
            filLogDelUd.addYear(filLogAdelYear * -1);
            filLogDelUd.addMonth(filLogAdelMonth * -1);
            filLogDelUd.setMaxHhMmSs();
            log__.debug("削除境界線(この日以前のデータを削除) = " + filLogDelUd.getTimeStamp());

            StatisticsBiz statsBiz = new StatisticsBiz();
            //集計データを削除
            int filDlLogCount = statsBiz.deleteLogCount(
                    con, "FILE_DOWNLOAD_LOG", "FDL_DATE", filLogAdelYear, filLogAdelMonth);
            log__.debug("ファイル管理ダウンロード 統計情報集計データ" + filDlLogCount + "件を削除");
            int filUlLogCount = statsBiz.deleteLogCount(
                    con, "FILE_UPLOAD_LOG", "FUL_DATE", filLogAdelYear, filLogAdelMonth);
            log__.debug("ファイル管理アップロード 統計情報集計データ" + filUlLogCount + "件を削除");
        }
    }
}
