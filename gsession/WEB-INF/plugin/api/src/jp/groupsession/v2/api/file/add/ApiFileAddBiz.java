package jp.groupsession.v2.api.file.add;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110Biz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.exception.TempFileException;
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
 * <br>[機  能] ファイルの登録を行うWEBAPIビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileAddBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiFileAddBiz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエストモデル */
    private RequestModel reqMdl__ = null;
    /** ユーザSID */
    private int usrSid__ = -1;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param usrSid ユーザSID
     * @param reqMdl RequestModel
     */
    public ApiFileAddBiz(Connection con, int usrSid, RequestModel reqMdl) {
        con__ = con;
        usrSid__ = usrSid;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] ファイルを登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl ApiFileAddParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param cntCon MlCountMtController
     * @param appRootPath アプリケーションルートパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @throws Exception 実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void registerData(
            ApiFileAddParamModel paramMdl,
            String tempDir,
            MlCountMtController cntCon,
            String appRootPath,
            PluginConfig pconfig,
            boolean smailPluginUseFlg) throws Exception, TempFileException {

        CommonBiz cmnbiz = new CommonBiz();
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        int parentDirSid = NullDefault.getInt(paramMdl.getFdrParentSid(), -1);
        UDate now = new UDate();

        //テンポラリディレクトリパスにある添付ファイルを全て登録
        List<CmnBinfModel> binList = cmnbiz.insertSameBinInfoForFileKanri(
                con__, tempDir, appRootPath, cntCon, usrSid__, now);

        //親ディレクトリ情報を取得する。
        FileDirectoryModel parDirModel = dirDao.getNewDirectory(parentDirSid);
        int level = parDirModel.getFdrLevel() + 1;

        //新規登録
        __insertFileInf(
                paramMdl,
                binList,
                level,
                cntCon,
                appRootPath,
                pconfig,
                smailPluginUseFlg);

    }

    /**
     * <br>[機  能] 新規登録の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl ApiFileAddParamModel
     * @param binList バイナリリスト
     * @param level ディレクトリ階層
     * @param cntCon MlCountMtController
     * @param appRoot ROOTパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @throws Exception 実行例外
     */
    private void __insertFileInf(
            ApiFileAddParamModel paramMdl,
            List<CmnBinfModel> binList,
            int level,
            MlCountMtController cntCon,
            String appRoot,
            PluginConfig pconfig,
            boolean smailPluginUseFlg)
    throws Exception {


        if (binList == null) {
            return;
        }

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileFileBinDao fileBinDao = new FileFileBinDao(con__);
        FileFileRekiDao rekiDao = new FileFileRekiDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__);

        int version = 1;
        UDate now = new UDate();
        int parentDirSid = NullDefault.getInt(paramMdl.getFdrParentSid(), -1);
        int cabinetSid = filBiz.getCabinetSid(parentDirSid, con__);
        //ディレクトリ情報モデルを設定する。
        FileDirectoryModel dirModel = new FileDirectoryModel();
        dirModel.setFdrVersion(version);
        dirModel.setFcbSid(cabinetSid);
        dirModel.setFdrParentSid(parentDirSid);
        dirModel.setFdrKbn(GSConstFile.DIRECTORY_FILE);
        dirModel.setFdrVerKbn(GSConstFile.VERSION_KBN_OFF);
        dirModel.setFdrLevel(level);
        dirModel.setFdrBiko("");
        dirModel.setFdrJtkbn(GSConstFile.JTKBN_NORMAL);
        dirModel.setFdrAuid(usrSid__);
        dirModel.setFdrAdate(now);
        dirModel.setFdrEuid(usrSid__);
        dirModel.setFdrEdate(now);

        //ファイル情報モデルを設定する。
        FileFileBinModel fileBinModel = new FileFileBinModel();
        fileBinModel.setFdrVersion(version);
        fileBinModel.setFflLockKbn(GSConstFile.LOCK_KBN_OFF);
        fileBinModel.setFflLockUser(usrSid__);

        //ディレクトリ情報モデルを設定する。
        FileFileRekiModel rekiModel = new FileFileRekiModel();
        rekiModel.setFdrVersion(version);
        rekiModel.setFfrKbn(GSConstFile.REKI_KBN_NEW);
        rekiModel.setFfrEuid(usrSid__);
        rekiModel.setFfrEdate(now);
        rekiModel.setFfrParentSid(parentDirSid);

        for (CmnBinfModel binMdl : binList) {
            //ディレクトリSIDを採番する。
            int dirSid = (int) cntCon.getSaibanNumber(
                    GSConstFile.PLUGIN_ID_FILE,
                    GSConstFile.SBNSID_SUB_DIRECTORY,
                    usrSid__);

            dirModel.setFdrSid(dirSid);
            dirModel.setFdrName(binMdl.getBinFileName());
            dirDao.insert(dirModel);

            fileBinModel.setFdrSid(dirSid);
            fileBinModel.setBinSid(binMdl.getBinSid());
            fileBinModel.setFflExt(binMdl.getBinFileExtension());
            fileBinModel.setFflFileSize(binMdl.getBinFileSize());
            fileBinDao.insert(fileBinModel);

            rekiModel.setFdrSid(dirSid);
            rekiModel.setFfrFname(binMdl.getBinFileName());
            rekiDao.insert(rekiModel);
            //更新通知を設定する。
            filBiz.updateCall(dirSid, cntCon, appRoot, pconfig, smailPluginUseFlg,
                            reqMdl__, usrSid__);

            //集計データを登録する
            int fulUsrSid = 0;
                fulUsrSid = usrSid__;
            int fulGrpSid = -1;

            filBiz.regFileUploadLogCnt(
                    con__, fulUsrSid, fulGrpSid,
                    GSConstFile.LOG_COUNT_KBN_NEW, cabinetSid, dirSid, now);
            //親ディレクトリ配下のアクセス制限を更新
            dirDao.updateAccessSid(dirSid);

        }

    }

    /**
     * <br>[機  能] 添付ファイルをテンポラリディレクトリへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリ
     * @param paramMdlFile ファイルデータ
     * @throws IOToolsException IOエラー
     * @throws Exception エラー
     */
    public void setTempFile(String tempDir, FormFile paramMdlFile)
                                               throws IOToolsException, Exception {

        String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)

        int fileNum = 1;
        //添付ファイル名
        String fileName = (new File(paramMdlFile.getFileName())).getName();
        long fileSize = paramMdlFile.getFileSize();
        //添付ファイル(本体)のパスを取得
        File saveFilePath = CommonBiz.getSaveFilePath(tempDir, dateStr, fileNum);

        //添付ファイルアップロード
        TempFileUtil.upload(paramMdlFile, tempDir, saveFilePath.getName());

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