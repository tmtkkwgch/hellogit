package jp.groupsession.v2.api.file.update;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110Biz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.dao.FileFileRekiDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileFileBinModel;
import jp.groupsession.v2.fil.model.FileFileRekiModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;

/**
 * <br>[機  能] ファイルの更新を行うWEBAPIビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileUpdateBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiFileUpdateBiz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** ユーザSID */
    private int usrSid__ = -1;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param usrSid ユーザSID
     */
    public ApiFileUpdateBiz(Connection con, int usrSid) {
        con__ = con;
        usrSid__ = usrSid;
    }

    /**
     * <br>[機  能] ファイルを登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param param ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param cntCon MlCountMtController
     * @param appRootPath アプリケーションルートパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     */
    public void registerData(
            ApiFileUpdateParamModel param,
            String tempDir,
            MlCountMtController cntCon,
            String appRootPath,
            PluginConfig pconfig,
            boolean smailPluginUseFlg,
            RequestModel reqMdl
            ) throws Exception, IOToolsException, IOException {

        CommonBiz cmnbiz = new CommonBiz();
        UDate now = new UDate();

        //テンポラリディレクトリパスにある添付ファイルを全て登録
        List<CmnBinfModel> binList = cmnbiz.insertSameBinInfoForFileKanri(
                con__, tempDir, appRootPath, cntCon, usrSid__, now);

        //編集
        __updateFileInf(
                param,
                binList,
                cntCon,
                appRootPath,
                pconfig,
                smailPluginUseFlg, reqMdl);

    }
    /**
     * <br>[機  能] 編集時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param param ParamModel
     * @param binList バイナリリスト
     * @param cntCon MlCountMtController
     * @param appRoot ROOTパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    private void __updateFileInf(
            ApiFileUpdateParamModel param,
            List<CmnBinfModel> binList,
            MlCountMtController cntCon,
            String appRoot,
            PluginConfig pconfig,
            boolean smailPluginUseFlg,
            RequestModel reqMdl
            )
    throws Exception {


        if (binList == null || binList.size() < 1) {
            return;
        }
        CmnBinfModel binMdl = null;
        for (CmnBinfModel mdl : binList) {
            binMdl = mdl;
        }

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileFileBinDao fileBinDao = new FileFileBinDao(con__);
        FileFileRekiDao rekiDao = new FileFileRekiDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__);


        int dirSid = NullDefault.getInt(param.getFdrSid(), -1);
        int cabinetSid = filBiz.getCabinetSid(dirSid, con__);
        int parentDirSid = filBiz.getParentDirSid(dirSid, con__);

        //親ディレクトリ情報を取得する。
        FileDirectoryModel parDirModel = dirDao.getNewDirectory(parentDirSid);
        int level = parDirModel.getFdrLevel() + 1;

        //最新のバージョンを取得
        int version = -1;
        int verKbn = 0;
        String biko = "";

        FileDirectoryDao dao = new FileDirectoryDao(con__);
        FileDirectoryModel model = dao.getNewDirectory(dirSid);
        if (model != null) {
            version = model.getFdrVersion();
            verKbn = model.getFdrVerKbn();
            biko = model.getFdrBiko();
        }

        int nextVersion = version + 1;
        UDate now = new UDate();

        //ディレクトリ情報モデルを設定する。
        FileDirectoryModel dirModel = new FileDirectoryModel();
        dirModel.setFdrSid(dirSid);
        dirModel.setFdrVersion(nextVersion);
        dirModel.setFcbSid(cabinetSid);
        dirModel.setFdrParentSid(parentDirSid);
        dirModel.setFdrKbn(GSConstFile.DIRECTORY_FILE);
        dirModel.setFdrVerKbn(verKbn);
        dirModel.setFdrLevel(level);
        dirModel.setFdrBiko(biko);
        dirModel.setFdrJtkbn(GSConstFile.JTKBN_NORMAL);
        dirModel.setFdrAuid(usrSid__);
        dirModel.setFdrAdate(now);
        dirModel.setFdrEuid(usrSid__);
        dirModel.setFdrEdate(now);
        dirModel.setFdrName(binMdl.getBinFileName());
        dirDao.insert(dirModel);

        //ファイル情報モデルを設定する。
        FileFileBinModel fileBinModel = new FileFileBinModel();
        fileBinModel.setFdrVersion(nextVersion);
        fileBinModel.setFflLockKbn(GSConstFile.LOCK_KBN_OFF);
        fileBinModel.setFflLockUser(usrSid__);
        fileBinModel.setFdrSid(dirSid);
        fileBinModel.setBinSid(binMdl.getBinSid());
        fileBinModel.setFflExt(binMdl.getBinFileExtension());
        fileBinModel.setFflFileSize(binMdl.getBinFileSize());
        fileBinDao.insert(fileBinModel);

        //ディレクトリ履歴情報モデルを設定する。
        FileFileRekiModel rekiModel = new FileFileRekiModel();
        rekiModel.setFdrVersion(nextVersion);
        rekiModel.setFfrKbn(GSConstFile.REKI_KBN_UPDATE);
        rekiModel.setFfrEuid(usrSid__);
        rekiModel.setFfrEdate(now);
        rekiModel.setFdrSid(dirSid);
        rekiModel.setFfrParentSid(parentDirSid);
        rekiModel.setFfrFname(binMdl.getBinFileName());
        rekiDao.insert(rekiModel);

        //集計データを登録する
        int fulUsrSid = 0;
        int fulGrpSid = 0;
        fulUsrSid = usrSid__;
        fulGrpSid = -1;
        filBiz.regFileUploadLogCnt(
                con__, fulUsrSid, fulGrpSid,
                GSConstFile.LOG_COUNT_KBN_NEW, cabinetSid, dirSid, now);

        //親ディレクトリ配下のアクセス制限を更新
        dirDao.updateAccessSid(dirSid);

        //バージョン管理外のファイルを削除する。
        deleteOldVersion(dirSid, version, cabinetSid);

        //更新通知を設定する。
        filBiz.updateCall(dirSid, cntCon, appRoot, pconfig, smailPluginUseFlg,
                        reqMdl, usrSid__);


    }

    /**
     * <br>[機  能] バージョン管理外のファイルを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param newVersion 最新バージョン
     * @param cabinetSid キャビネットSID
     * @throws SQLException SQL実行例外
     */
    public void deleteOldVersion(int dirSid, int newVersion, int cabinetSid)
    throws SQLException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz();

        //ファイルSID

        //バージョン管理区分を取得する。
        int verKbn = filBiz.getVerKbn(cabinetSid, dirSid, con__);
        int delVersion = newVersion - verKbn + 1;
        if (delVersion < 1) {
            //削除データ無し
            return;
        }

        //管理しないディレクトリ情報を削除する。
        dirDao.deleteOldVersion(dirSid, delVersion);

        //ファイル情報を削除する。
        __deleteOldFile(dirSid, delVersion);
    }

    /**
     * <br>[機  能] バージョン管理外のファイル情報を削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSid
     * @param delVersion 削除基準バージョン
     * @throws SQLException SQL実行例外
     */
    private void __deleteOldFile(int dirSid, int delVersion)
    throws SQLException {

        FileFileBinDao fileDao = new FileFileBinDao(con__);
        CmnBinfDao cbDao = new CmnBinfDao(con__);
        CmnBinfModel cmnBinModel = new CmnBinfModel();
        UDate now = new UDate();

        //削除対象ファイルリスト
        List<FileFileBinModel> delList = fileDao.getOldVersion(dirSid, delVersion);

        if (delList != null && delList.size() > 0) {
            List<Long> binSidList = new ArrayList<Long>();
            for (FileFileBinModel binModel : delList) {
                binSidList.add(binModel.getBinSid());
            }
            cmnBinModel.setBinJkbn(GSConst.JTKBN_DELETE);
            cmnBinModel.setBinUpuser(usrSid__);
            cmnBinModel.setBinUpdate(now);

            //バイナリー情報を論理削除する
            cbDao.updateJKbn(cmnBinModel, binSidList);
        }

        //ファイル情報を削除する。
        fileDao.deleteOldVersion(dirSid, delVersion);
    }
    /**
     * <br>[機  能] ファイルロックを行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param usrSid セッションユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void fileLock(int dirSid, int usrSid)
    throws SQLException {


        FilCommonBiz filBiz = new FilCommonBiz();

        //最新バージョンを取得
        int version = filBiz.getNewVersion(dirSid, con__);

        //ファイルロックを行う。
        filBiz.updateLockKbnCommit(
                dirSid, version, GSConstFile.LOCK_KBN_ON, usrSid, con__);

    }

    /**
     * <br>[機  能] ファイルアンロックを行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @throws SQLException SQL実行時例外
     */
    public void fileUnlock(int dirSid) throws SQLException {
        FilCommonBiz filBiz = new FilCommonBiz();

        //最新バージョンを取得
        int version = filBiz.getNewVersion(dirSid, con__);
        //ロックを解除する。
        if (filBiz.getLockKbnAdmin(con__) == GSConstFile.LOCK_KBN_ON) {
            filBiz.updateLockKbn(
                    dirSid,
                    version, GSConstFile.LOCK_KBN_OFF, usrSid__, con__);
        }
    }
    /**
     * <br>[機  能] 添付ファイルをテンポラリディレクトリへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリ
     * @param formFile ファイルデータ
     * @throws IOToolsException IOエラー
     * @throws Exception エラー
     */
    public void setTempFile(String tempDir, FormFile formFile) throws IOToolsException, Exception {

        String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)

        int fileNum = 1;
        //添付ファイル名
        String fileName = (new File(formFile.getFileName())).getName();
        long fileSize = formFile.getFileSize();
        //添付ファイル(本体)のパスを取得
        File saveFilePath = CommonBiz.getSaveFilePath(tempDir, dateStr, fileNum);

        //添付ファイルアップロード
        TempFileUtil.upload(formFile, tempDir, saveFilePath.getName());

        //オブジェクトファイルを設定
        File objFilePath = Cmn110Biz.getObjFilePath(tempDir, dateStr, fileNum);
        Cmn110FileModel fileMdl = new Cmn110FileModel();
        fileMdl.setFileName(fileName);
        fileMdl.setSaveFileName(saveFilePath.getName());
        fileMdl.setAtattiSize(fileSize);

        String[] fileVal = fileMdl.getSaveFileName().split(GSConstCommon.ENDSTR_SAVEFILE);
        log__.debug("*** セーブファイル = " + fileVal[0]);
        fileMdl.setSplitObjName(fileVal[0]);

        ObjectFile objFile = new ObjectFile(objFilePath.getParent(), objFilePath.getName());
        objFile.save(fileMdl);

    }

}