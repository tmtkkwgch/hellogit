package jp.groupsession.v2.api.file.rename;

import java.io.IOException;
import java.sql.Connection;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.api.file.update.ApiFileUpdateBiz;
import jp.groupsession.v2.cmn.biz.CommonBiz;
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

/**
 * <br>[機  能] ファイル名の更新を行うWEBAPIビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiFileRenameBiz {

    /** DBコネクション */
    private Connection con__ = null;
    /** ユーザSID */
    private int usrSid__ = -1;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param usrSid ユーザSID
     */
    public ApiFileRenameBiz(Connection con, int usrSid) {
        con__ = con;
        usrSid__ = usrSid;
    }

    /**
     * <br>[機  能] ファイルを登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param params ParamModel
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
            ApiFileRenameParamModel params,
            MlCountMtController cntCon,
            String appRootPath,
            PluginConfig pconfig,
            boolean smailPluginUseFlg,
            RequestModel reqMdl
            ) throws Exception, IOToolsException, IOException {

        //編集
        __updateFileInf(
                params,
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
     * @param cntCon MlCountMtController
     * @param appRoot ROOTパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    private void __updateFileInf(
            ApiFileRenameParamModel param,
            MlCountMtController cntCon,
            String appRoot,
            PluginConfig pconfig,
            boolean smailPluginUseFlg,
            RequestModel reqMdl)
    throws Exception {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileFileBinDao fileBinDao = new FileFileBinDao(con__);
        FileFileRekiDao rekiDao = new FileFileRekiDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__);
        CmnBinfDao cbDao = new CmnBinfDao(con__);
        CommonBiz biz = new CommonBiz();

        int dirSid = NullDefault.getInt(param.getFdrSid(), -1);
        int cabinetSid = filBiz.getCabinetSid(dirSid, con__);
        int parentDirSid = filBiz.getParentDirSid(dirSid, con__);
        String fdrName = param.getFdrName();

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


        //最新のファイル情報を取得する。
        FileFileBinModel fbinModel = fileBinDao.getNewFile(dirSid);
        UDate now = new UDate();
        //バイナリ情報を更新する。
        CmnBinfModel cmnBinfModel = cbDao.getBinInfo(fbinModel.getBinSid());
        String filePath = biz.getFileRootPathForFileKanri(appRoot) + cmnBinfModel.getBinFilePath();
        Long binSid = biz.insertBinInfoForFilekanri(
                con__, appRoot, cntCon, usrSid__, now, filePath, fdrName);

        int nextVersion = version + 1;

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
        dirModel.setFdrName(fdrName);
        dirDao.insert(dirModel);

        //ファイル情報モデルを設定する。
        FileFileBinModel fileBinModel = new FileFileBinModel();
        fileBinModel.setFdrVersion(nextVersion);
        fileBinModel.setFflLockKbn(GSConstFile.LOCK_KBN_OFF);
        fileBinModel.setFflLockUser(usrSid__);
        fileBinModel.setFdrSid(dirSid);
        fileBinModel.setBinSid(binSid);
        fileBinModel.setFflExt(fbinModel.getFflExt());
        fileBinModel.setFflFileSize(fbinModel.getFflFileSize());
        fileBinDao.insert(fileBinModel);

        //ディレクトリ履歴情報モデルを設定する。
        FileFileRekiModel rekiModel = new FileFileRekiModel();
        rekiModel.setFdrVersion(nextVersion);
        rekiModel.setFfrKbn(GSConstFile.REKI_KBN_UPDATE);
        rekiModel.setFfrEuid(usrSid__);
        rekiModel.setFfrEdate(now);
        rekiModel.setFdrSid(dirSid);
        rekiModel.setFfrParentSid(parentDirSid);
        rekiModel.setFfrFname(fdrName);
        rekiDao.insert(rekiModel);

        //集計データを登録する
        int fulUsrSid = 0;
        int fulGrpSid = 0;
        fulUsrSid = usrSid__;
        fulGrpSid = -1;
        filBiz.regFileUploadLogCnt(
                con__, fulUsrSid, fulGrpSid,
                GSConstFile.LOG_COUNT_KBN_NEW, cabinetSid, dirSid, now);

        //アクセス制限を更新
        dirDao.updateAccessSid(dirSid);

        //バージョン管理外のファイルを削除する。
        ApiFileUpdateBiz updateBiz = new ApiFileUpdateBiz(con__, usrSid__);
        updateBiz.deleteOldVersion(dirSid, version, cabinetSid);

        //更新通知を設定する。
        filBiz.updateCall(dirSid, cntCon, appRoot, pconfig, smailPluginUseFlg, reqMdl, usrSid__);

    }

}