package jp.groupsession.v2.fil.fil080kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileDAccessConfDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.dao.FileFileRekiDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileFileBinModel;
import jp.groupsession.v2.fil.model.FileFileRekiModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ファイル登録確認画面のBIZクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil080knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil080knBiz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /** ユーザSID */
    private int usrSid__;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil080knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl リクエスト情報
     * @param usrSid ユーザSID
     */
    public Fil080knBiz(Connection con, RequestModel reqMdl, int usrSid) {
        con__ = con;
        reqMdl__ = reqMdl;
        usrSid__ = usrSid;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080knParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param buMdl セッションユーザモデル
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void setInitData(
        Fil080knParamModel paramMdl,
        String tempDir,
        BaseUserModel buMdl) throws SQLException, IOToolsException {

        log__.debug("Fil080knBiz Start");
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        //親ディレクトリSIDを取得する。
        int parentDirSid = NullDefault.getInt(paramMdl.getFil070ParentDirSid(), -1);

        //個人設定が無い場合は初期値で登録する。
        filBiz.getUserConf(buMdl.getUsrsid(), con__);

        //フォルダパスを設定する。
        paramMdl.setFil080DirPath(filBiz.getDirctoryPath(parentDirSid, con__));

        //添付ファイルのラベルを設定する。
        CommonBiz commonBiz = new CommonBiz();
        paramMdl.setFil080FileLabelList(commonBiz.getTempFileLabelList(tempDir));

        //備考をHTML表示用に変換
        paramMdl.setFil080knBiko(StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getFil080Biko()));

        //更新コメントをHTML表示用に変換
        paramMdl.setFil080knUpCmt(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getFil080UpCmt()));

        //更新者を取得
        String editId = NullDefault.getString(
                paramMdl.getFil080EditId(), String.valueOf(buMdl.getUsrsid()));
        if (filBiz.isEditGroup(editId)) {
            //グループ情報取得
            GroupDao gpDao = new GroupDao(con__);
            if (ValidateUtil.isNumber(editId.substring("G".length()))) {
                CmnGroupmModel gpMdl = new CmnGroupmModel();
                gpMdl = gpDao.getGroup(Integer.valueOf(editId.substring("G".length())));
                paramMdl.setFil080knEditName(gpMdl.getGrpName());
            }
        } else {
            paramMdl.setFil080knEditName(buMdl.getUsiseimei());
        }

        //アクセス権限 フルアクセス一覧
        paramMdl.setFil080AcFullLavel(__getAccessFullLavle(paramMdl));
        //アクセス権限 閲覧アクセス一覧
        paramMdl.setFil080AcReadLavel(__getAccessReadLavle(paramMdl));
    }

    /**
     * <br>[機  能] ファイルを登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080knParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param cntCon MlCountMtController
     * @param appRootPath アプリケーションルートパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @return fileName ファイル名
     * @throws Exception 実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public String registerData(
            Fil080knParamModel paramMdl,
            String tempDir,
            MlCountMtController cntCon,
            String appRootPath,
            PluginConfig pconfig,
            boolean smailPluginUseFlg) throws Exception, TempFileException {

        CommonBiz cmnbiz = new CommonBiz();
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        int parentDirSid = NullDefault.getInt(paramMdl.getFil070ParentDirSid(), -1);
        UDate now = new UDate();
        String fileName = "";

        //テンポラリディレクトリパスにある添付ファイルを全て登録
        List<CmnBinfModel> binList = cmnbiz.insertSameBinInfoForFileKanri(
                con__, tempDir, appRootPath, cntCon, usrSid__, now);

        //親ディレクトリ情報を取得する。
        FileDirectoryModel parDirModel = dirDao.getNewDirectory(parentDirSid);
        int level = parDirModel.getFdrLevel() + 1;

        if (paramMdl.getFil080Mode() == GSConstFile.MODE_ADD) {
            //新規登録
            fileName = __insertFileInf(
                    paramMdl,
                    binList,
                    level,
                    cntCon,
                    appRootPath,
                    pconfig,
                    smailPluginUseFlg);

        } else {
            //編集
            fileName = __updateFileInf(
                    paramMdl,
                    binList,
                    level,
                    cntCon,
                    appRootPath,
                    pconfig,
                    smailPluginUseFlg);
        }

        return fileName;
    }

    /**
     * <br>[機  能] 新規登録の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080knParamModel
     * @param binList バイナリリスト
     * @param level ディレクトリ階層
     * @param cntCon MlCountMtController
     * @param appRoot ROOTパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @return fileName
     * @throws Exception 実行例外
     */
    private String __insertFileInf(
            Fil080knParamModel paramMdl,
            List<CmnBinfModel> binList,
            int level,
            MlCountMtController cntCon,
            String appRoot,
            PluginConfig pconfig,
            boolean smailPluginUseFlg)
    throws Exception {


        if (binList == null) {
            return "";
        }

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileFileBinDao fileBinDao = new FileFileBinDao(con__);
        FileFileRekiDao rekiDao = new FileFileRekiDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        int version = 1;
        UDate now = new UDate();
        int parentDirSid = NullDefault.getInt(paramMdl.getFil070ParentDirSid(), -1);
        int cabinetSid = filBiz.getCabinetSid(parentDirSid, con__);
        String fileName = "";

        //ディレクトリ情報モデルを設定する。
        FileDirectoryModel dirModel = new FileDirectoryModel();
        dirModel.setFdrVersion(version);
        dirModel.setFcbSid(cabinetSid);
        dirModel.setFdrParentSid(parentDirSid);
        dirModel.setFdrKbn(GSConstFile.DIRECTORY_FILE);
        dirModel.setFdrVerKbn(NullDefault.getInt(paramMdl.getFil080VerKbn(), 0));
        dirModel.setFdrLevel(level);
        dirModel.setFdrBiko(paramMdl.getFil080Biko());
        dirModel.setFdrJtkbn(GSConstFile.JTKBN_NORMAL);
        dirModel.setFdrAuid(usrSid__);
        dirModel.setFdrAdate(now);
        dirModel.setFdrEuid(usrSid__);
        dirModel.setFdrEdate(now);
        dirModel.setFdrEgid(filBiz.getGroupSid(paramMdl.getFil080EditId()));

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
        rekiModel.setFfrUpCmt(paramMdl.getFil080UpCmt());
        rekiModel.setFfrEgid(filBiz.getGroupSid(paramMdl.getFil080EditId()));

        ArrayList<Integer> sidList = new ArrayList<Integer>();
        int i = 0;
        for (CmnBinfModel binMdl : binList) {
            //ディレクトリSIDを採番する。
            int dirSid = (int) cntCon.getSaibanNumber(
                    GSConstFile.PLUGIN_ID_FILE,
                    GSConstFile.SBNSID_SUB_DIRECTORY,
                    usrSid__);
            sidList.add(dirSid);

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
            //ディレクトリアクセス設定
            __insertDaccessConf(dirSid, paramMdl);

            //集計データを登録する
            int fulUsrSid = 0;
            int fulGrpSid = 0;
            if (filBiz.getGroupSid(paramMdl.getFil080EditId()) == 0) {
                fulUsrSid = usrSid__;
                fulGrpSid = -1;
            } else {
                fulUsrSid = -1;
                fulGrpSid = filBiz.getGroupSid(paramMdl.getFil080EditId());
            }
            filBiz.regFileUploadLogCnt(
                    con__, fulUsrSid, fulGrpSid,
                    GSConstFile.LOG_COUNT_KBN_NEW, cabinetSid, dirSid, now);

            if (i > 0) {
                fileName += ", ";
            }
            fileName += binMdl.getBinFileName();
            i++;
        }

        //親ディレクトリ配下のアクセス制限を更新
        //更新通知を設定する。
        for (int sid : sidList) {
            dirDao.updateAccessSid(sid);
            filBiz.updateCall(sid, cntCon, appRoot, pconfig, smailPluginUseFlg,
                    reqMdl__, usrSid__);
        }

        fileName = StringUtil.trimRengeString(fileName, 3000);
        return fileName;
    }

    /**
     * <br>[機  能] 編集時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080knParamModel
     * @param binList バイナリリスト
     * @param level ディレクトリ階層
     * @param cntCon MlCountMtController
     * @param appRoot ROOTパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @return fileName
     * @throws Exception 実行例外
     */
    private String __updateFileInf(
            Fil080knParamModel paramMdl,
            List<CmnBinfModel> binList,
            int level,
            MlCountMtController cntCon,
            String appRoot,
            PluginConfig pconfig,
            boolean smailPluginUseFlg)
    throws Exception {


        if (binList == null || binList.size() < 1) {
            return "";
        }
        CmnBinfModel binMdl = null;
        for (CmnBinfModel mdl : binList) {
            binMdl = mdl;
        }

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileFileBinDao fileBinDao = new FileFileBinDao(con__);
        FileFileRekiDao rekiDao = new FileFileRekiDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        int dirSid = NullDefault.getInt(paramMdl.getFil070DirSid(), -1);
        int cabinetSid = filBiz.getCabinetSid(dirSid, con__);
        int parentDirSid = NullDefault.getInt(paramMdl.getFil070ParentDirSid(), -1);
        //最新のバージョンを取得
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);
        if (dirModel == null) {
            dirModel = new FileDirectoryModel();
            dirModel.setFdrVersion(-1);
            dirModel.setFdrLevel(level);
            dirModel.setFdrParentSid(parentDirSid);
        }
        int version = dirModel.getFdrVersion();
        int nextVersion = version + 1;
        UDate now = new UDate();

        //ディレクトリ情報モデルを設定する。
        dirModel.setFdrSid(dirSid);
        dirModel.setFdrVersion(nextVersion);
        dirModel.setFcbSid(cabinetSid);
        dirModel.setFdrKbn(GSConstFile.DIRECTORY_FILE);
        dirModel.setFdrVerKbn(NullDefault.getInt(paramMdl.getFil080VerKbn(), 0));
        dirModel.setFdrBiko(paramMdl.getFil080Biko());
        dirModel.setFdrJtkbn(GSConstFile.JTKBN_NORMAL);
        dirModel.setFdrAuid(usrSid__);
        dirModel.setFdrAdate(now);
        dirModel.setFdrEuid(usrSid__);
        dirModel.setFdrEdate(now);
        dirModel.setFdrName(binMdl.getBinFileName());
        dirModel.setFdrEgid(filBiz.getGroupSid(paramMdl.getFil080EditId()));
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
        rekiModel.setFfrUpCmt(paramMdl.getFil080UpCmt());
        rekiModel.setFfrEgid(filBiz.getGroupSid(paramMdl.getFil080EditId()));
        rekiDao.insert(rekiModel);

        //バージョン管理外のファイルを削除する。
        __deleteOldVersion(paramMdl, version);

        //ディレクトリアクセス設定
        __insertDaccessConf(dirSid, paramMdl);
        dirDao.updateAccessSid(dirSid);
        //集計データを登録する
        int fulUsrSid = 0;
        int fulGrpSid = 0;
        if (filBiz.getGroupSid(paramMdl.getFil080EditId()) == 0) {
            fulUsrSid = usrSid__;
            fulGrpSid = -1;
        } else {
            fulUsrSid = -1;
            fulGrpSid = filBiz.getGroupSid(paramMdl.getFil080EditId());
        }
        filBiz.regFileUploadLogCnt(
                con__, fulUsrSid, fulGrpSid,
                GSConstFile.LOG_COUNT_KBN_EDIT, cabinetSid, dirSid, now);

        //更新通知を設定する。
        filBiz.updateCall(dirSid, cntCon, appRoot, pconfig, smailPluginUseFlg,
                        reqMdl__, usrSid__);

        //ロックを解除する。
        if (filBiz.getLockKbnAdmin(con__) == GSConstFile.LOCK_KBN_ON) {
            filBiz.updateLockKbn(dirSid, version, GSConstFile.LOCK_KBN_OFF, usrSid__, con__);
        }

        return binMdl.getBinFileName();
    }

    /**
     * <br>[機  能] バージョン管理外のファイルを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080knParamModel
     * @param newVersion 最新バージョン
     * @throws SQLException SQL実行例外
     */
    private void __deleteOldVersion(Fil080knParamModel paramMdl, int newVersion)
    throws SQLException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        int dirSid = NullDefault.getInt(paramMdl.getFil070DirSid(), -1);

        //キャビネットSIDを取得する。
        int cabinetSid = filBiz.getCabinetSid(dirSid, con__);

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
        __deleteOldFile(paramMdl, delVersion);
    }

    /**
     * <br>[機  能] バージョン管理外のファイル情報を削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080knParamModel
     * @param delVersion 削除基準バージョン
     * @throws SQLException SQL実行例外
     */
    private void __deleteOldFile(Fil080knParamModel paramMdl, int delVersion)
    throws SQLException {

        FileFileBinDao fileDao = new FileFileBinDao(con__);
        CmnBinfDao cbDao = new CmnBinfDao(con__);
        CmnBinfModel cmnBinModel = new CmnBinfModel();
        UDate now = new UDate();
        int dirSid = NullDefault.getInt(paramMdl.getFil070DirSid(), -1);

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
     * アクセス権限のフルアクセス用一覧を取得する
     * @param paramMdl Fil080knParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessFullLavle(
            Fil080knParamModel paramMdl)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getFil080SvAcFull();
        return __getAccessLavle(leftFull);
    }

    /**
     * アクセス権限のフルアクセス用一覧を取得する
     * @param paramMdl Fil080knParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessReadLavle(
            Fil080knParamModel paramMdl)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftRead = paramMdl.getFil080SvAcRead();
        return __getAccessLavle(leftRead);

    }

    /**
     * アクセス権限の一覧を取得する
     * @param left 取得するユーザSID・グループSID
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessLavle(String[] left)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

        //
        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");
                log__.debug("str==>" + str);
                log__.debug("G.index==>" + str.indexOf("G"));
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    grpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    usrSids.add(str);
                }
            }
        }
        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con__);
        ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
        LabelValueBean lavelBean = null;
        for (GroupModel gmodel : glist) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(gmodel.getGroupName());
            lavelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            ret.add(lavelBean);
        }

        //ユーザ情報取得
//        UserSearchDao usDao = new UserSearchDao(con);
//        ArrayList<BaseUserModel> ulist = usDao.getSelectedUserList(
//                (String[]) usrSids.toArray(new String[usrSids.size()]));
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con__,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            lavelBean.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(lavelBean);
        }

        return ret;
    }

    /**
     * ディレクトリアクセス設定を登録する
     * @param dirSid ディレクトリSID
     * @param paramMdl パラメータモデル
     * @throws SQLException 実行例外
     */
    private void __insertDaccessConf(int dirSid, Fil080knParamModel paramMdl) throws SQLException {

        FileDAccessConfDao dao = new FileDAccessConfDao(con__);
        dao.delete(dirSid);

        if (paramMdl.getFil080AccessKbn().equals("0")) {
            //個別設定しない
            return;
        }

        //フルアクセス
        ArrayList<Integer> userList = new ArrayList<Integer>();
        ArrayList<Integer> groupList = new ArrayList<Integer>();
        String[] full = paramMdl.getFil080SvAcFull();
        if (full != null) {
            for (int i = 0; i < full.length; i++) {
                String str = NullDefault.getString(full[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    groupList.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    userList.add(new Integer(str));
                }
            }
        }
        //フルアクセスユーザ登録
        dao.insert(dirSid, userList,
                GSConstFile.USER_KBN_USER, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
        //フルアクセスグループ登録
        dao.insert(dirSid, groupList,
                GSConstFile.USER_KBN_GROUP, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));

        //閲覧アクセス
        userList = new ArrayList<Integer>();
        groupList = new ArrayList<Integer>();
        String[] read = paramMdl.getFil080SvAcRead();
        if (read != null) {
            for (int i = 0; i < read.length; i++) {
                String str = NullDefault.getString(read[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    groupList.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    userList.add(new Integer(str));
                }
            }
        }
        //閲覧アクセスユーザ登録
        dao.insert(dirSid, userList,
                GSConstFile.USER_KBN_USER, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
        //閲覧アクセスグループ登録
        dao.insert(dirSid, groupList,
                GSConstFile.USER_KBN_GROUP, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
    }
}