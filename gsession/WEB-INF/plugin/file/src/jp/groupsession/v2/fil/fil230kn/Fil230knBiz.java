package jp.groupsession.v2.fil.fil230kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.FilTreeBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileCallConfDao;
import jp.groupsession.v2.fil.dao.FileCallDataDao;
import jp.groupsession.v2.fil.dao.FileDAccessConfDao;
import jp.groupsession.v2.fil.dao.FileDirectoryBinDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.dao.FileFileRekiDao;
import jp.groupsession.v2.fil.dao.FileFileTextDao;
import jp.groupsession.v2.fil.dao.FileShortcutConfDao;
import jp.groupsession.v2.fil.model.FilChildTreeModel;
import jp.groupsession.v2.fil.model.FileDirectoryBinModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileFileBinModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 管理者設定 ファイル一括削除確認画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil230knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil230knBiz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil230knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil230knParamModel
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Fil230knParamModel paramMdl) throws SQLException {

        log__.debug("fil230knBiz Start");

        //削除するフォルダパスを設定する。
        __setDeleteDir(paramMdl);

    }

    /**
     * <br>[機  能] 削除するフォルダパスを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil230knParamModel
     * @throws SQLException SQL実行例外
     */
    private void __setDeleteDir(Fil230knParamModel paramMdl) throws SQLException {

        int dirPath = NullDefault.getInt(paramMdl.getFil230DeleteDirSid(), 0);

        FilCommonBiz filCmnBiz = new FilCommonBiz(con__, reqMdl__);
        String delPath = filCmnBiz.getDirctoryPath(dirPath, con__);
        paramMdl.setFil230DeleteDir(delPath);

    }

    /**
     * <br>[機  能] ファイルを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil230knParamModel
     * @param appRootPath アプリケーションルートパス
     * @throws Exception 実行例外
     */
    public void deleteData(Fil230knParamModel paramMdl, String appRootPath) throws Exception {

        int delDirSid = NullDefault.getInt(paramMdl.getFil230DeleteDirSid(), -1);
        int deleteOpt = NullDefault.getInt(paramMdl.getFil230DeleteOpt(), -1);

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);

        //選択ディレクトリの情報取得
        FileDirectoryModel myDir = dirDao.getNewDirectory(delDirSid);

        if (myDir == null) {
            return;
        }

        //子階層のデータ取得
        FilTreeBiz treeBiz = new FilTreeBiz(con__);
        FilChildTreeModel childMdl = treeBiz.getChildOfTarget(myDir);

        ArrayList<FileDirectoryModel> listOfDir = childMdl.getListOfDir();
        if (!listOfDir.isEmpty() && deleteOpt == GSConstFile.DELETE_OPTION_FOLDER_FILE) {
            //ディレクトリアクセス制限削除
            FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);
            if (myDir.getFdrParentSid() == GSConstFile.DIRECTORY_ROOT) {
                //ルートディレクトリ
                daConfDao.deleteSubDirectoriesFiles(delDirSid, true);
            } else {
                daConfDao.deleteSubDirectoriesFiles(delDirSid, false);
            }
            //ディレクトリ情報の削除
            __deleteDirData(listOfDir, appRootPath);
        }

        ArrayList<FileDirectoryModel> listOfFile = childMdl.getListOfFile();
        if (!listOfFile.isEmpty()) {
            //ファイル情報削除
            __deleteFileData(listOfFile, appRootPath);

        }

    }

    /**
     * <br>[機  能] ファイルを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param listOfDir 削除ディレクトリ情報リスト
     * @param appRootPath アプリケーションルートパス
     * @throws Exception 実行例外
     */
    private void __deleteFileData(ArrayList<FileDirectoryModel> listOfDir,
            String appRootPath)
    throws Exception {

        //ディレクトリアクセス制御削除
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);
        for (FileDirectoryModel dirMdl : listOfDir) {
            daConfDao.delete(dirMdl.getFdrSid());
        }
        //ディレクトリ情報の削除
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        dirDao.deleteDir(listOfDir);

        //ショートカット設定の削除
        FileShortcutConfDao scutDao = new FileShortcutConfDao(con__);
        scutDao.deleteDir(listOfDir);

        //更新通知設定の削除
        FileCallConfDao callConfDao = new FileCallConfDao(con__);
        callConfDao.deleteDir(listOfDir);

        //更新確認情報の削除
        FileCallDataDao callDataDao = new FileCallDataDao(con__);
        callDataDao.deleteDir(listOfDir);

        //ファイル履歴情報の削除
        FileFileRekiDao fileRekiDao = new FileFileRekiDao(con__);
        fileRekiDao.deleteDir(listOfDir);

        //添付ファイル情報の削除
        __deleteBinDataFile(listOfDir, appRootPath);

        //ファイル情報の削除
        FileFileBinDao fileBinDao = new FileFileBinDao(con__);
        fileBinDao.deleteDir(listOfDir);

        //ファイル内容情報の削除
        FileFileTextDao fileTextDao = new FileFileTextDao(con__);
        fileTextDao.deleteDir(listOfDir);

    }

    /**
     * <br>[機  能] フォルダを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param listOfDir 削除ディレクトリ情報リスト
     * @param appRootPath アプリケーションルートパス
     * @throws Exception 実行例外
     */
    private void __deleteDirData(ArrayList<FileDirectoryModel> listOfDir, String appRootPath)
    throws Exception {

        //ディレクトリ情報の削除
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        dirDao.deleteDir(listOfDir);

        //添付情報の削除
        FileDirectoryBinDao dirBinDao = new FileDirectoryBinDao(con__);
        dirBinDao.deleteDir(listOfDir);

        //添付ファイル情報の削除
        __deleteBinDataDir(listOfDir, appRootPath);
    }

    /**
     * <br>[機  能] 添付ファイル情報をを削除する。
     * <br>[解  説] 添付ファイルの物理削除を行います。
     * <br>[備  考]
     * @param listOfDir 削除ディレクトリ情報リスト
     * @param appRootPath アプリケーションルートパス
     * @throws Exception 実行例外
     */
    private void __deleteBinDataFile(ArrayList<FileDirectoryModel> listOfDir, String appRootPath)
    throws Exception {

        FileFileBinDao fileBinDao = new FileFileBinDao(con__);

        //バイナリSID情報を取得する。
        List<FileFileBinModel> dirMdlList = fileBinDao.getFileListAllVersion(listOfDir);

        CmnBinfDao binDao = new CmnBinfDao(con__);
        CommonBiz cmnBiz = new CommonBiz();

        String[] binList = new String[listOfDir.size()];
        int i = 0;
        for (FileFileBinModel model : dirMdlList) {
            binList[i] = String.valueOf(model.getBinSid());
//            binList[i] = String.valueOf(model.getFdrSid());
            i++;
        }

        //バイナリ情報を取得する。
        List<CmnBinfModel> cmnBinList = binDao.select(binList);

        for (CmnBinfModel binMdl : cmnBinList) {

            //ファイルシステムより添付ファイルを削除する。
            cmnBiz.deleteFile(binMdl, appRootPath);
        }

        cmnBiz.deleteBinInf(con__, binList);
    }

    /**
     * <br>[機  能] 添付ファイル情報をを削除する。
     * <br>[解  説] 添付ファイルの物理削除を行います。
     * <br>[備  考]
     * @param listOfDir 削除ディレクトリ情報リスト
     * @param appRootPath アプリケーションルートパス
     * @throws Exception 実行例外
     */
    private void __deleteBinDataDir(ArrayList<FileDirectoryModel> listOfDir, String appRootPath)
    throws Exception {

        FileDirectoryBinDao dirBinDao = new FileDirectoryBinDao(con__);

        //バイナリSID情報を取得する。
        List<FileDirectoryBinModel> dirMdlList = dirBinDao.getFileListAllVersion(listOfDir);

        CmnBinfDao binDao = new CmnBinfDao(con__);
        CommonBiz cmnBiz = new CommonBiz();

        String[] binList = new String[listOfDir.size()];
        int i = 0;
        for (FileDirectoryBinModel model : dirMdlList) {
            binList[i] = String.valueOf(model.getFdrSid());
            i++;
        }

        //バイナリ情報を取得する。
        List<CmnBinfModel> cmnBinList = binDao.select(binList);

        for (CmnBinfModel binMdl : cmnBinList) {

            //ファイルシステムより添付ファイルを削除する。
            cmnBiz.deleteFile(binMdl, appRootPath);

        }

        cmnBiz.deleteBinInf(con__, binList);
    }
}