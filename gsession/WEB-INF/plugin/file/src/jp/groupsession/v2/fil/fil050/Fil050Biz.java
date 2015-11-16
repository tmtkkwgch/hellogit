package jp.groupsession.v2.fil.fil050;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.FilTreeBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileCallConfDao;
import jp.groupsession.v2.fil.dao.FileCallDataDao;
import jp.groupsession.v2.fil.dao.FileDAccessUserDao;
import jp.groupsession.v2.fil.dao.FileDao;
import jp.groupsession.v2.fil.dao.FileDirectoryBinDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.dao.FileFileRekiDao;
import jp.groupsession.v2.fil.dao.FileShortcutConfDao;
import jp.groupsession.v2.fil.fil050.dao.Fil050Dao;
import jp.groupsession.v2.fil.fil050.model.Fil050Model;
import jp.groupsession.v2.fil.model.FilChildTreeModel;
import jp.groupsession.v2.fil.model.FileCallConfModel;
import jp.groupsession.v2.fil.model.FileDAccessUserModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileFileBinModel;
import jp.groupsession.v2.fil.model.FileFileRekiModel;
import jp.groupsession.v2.fil.model.FileModel;
import jp.groupsession.v2.fil.model.FileShortcutConfModel;
import jp.groupsession.v2.fil.model.FileUconfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] フォルダ詳細画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil050Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil050Biz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil050Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil050ParamModel
     * @param buMdl ユーザモデル
     * @return true:取得成功 false:取得失敗
     * @throws SQLException SQL実行例外
     * @throws IOException IOエラー
     */
    public boolean setInitData(Fil050ParamModel paramMdl,
            BaseUserModel buMdl)
    throws SQLException, IOException {

        log__.debug("fil050Biz Start");

        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        int fdrSid = NullDefault.getInt(paramMdl.getFil050DirSid(), 0);

        //個人設定が無い場合は初期値で登録する。
        filBiz.getUserConf(buMdl.getUsrsid(), con__);

        //DBより初期値を設定する。
        if (!__setData(paramMdl, buMdl)) {
            return false;
        }

        //キャビネットSIDを取得する。
        int cabSid = filBiz.getCabinetSid(fdrSid, con__);
        //特権ユーザ判定
        boolean superUser = filBiz.isEditCabinetUser(cabSid, con__);

        if (NullDefault.getString(
                paramMdl.getFil050DspMode(), GSConstFile.DSP_MODE_HIST).equals(
                        GSConstFile.DSP_MODE_HIST)) {
            //更新履歴一覧を設定する。
            __setUpdateReki(paramMdl, buMdl, superUser);
        } else {
            //アクセス制御取得
            __setAccessList(paramMdl, buMdl);
        }

        //URL設定
        paramMdl.setFil050FolderUrl(__createFolderUrl(paramMdl));

        //ファイル編集権限を設定
        paramMdl.setFil050EditAuthKbn(GSConstFile.DSP_KBN_OFF);
        if (filBiz.isDirAccessAuthUser(cabSid, fdrSid,
                buMdl.getUsrsid(), Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE),
                con__)) {
            paramMdl.setFil050EditAuthKbn(GSConstFile.DSP_KBN_ON);
        }

        return true;
    }

    /**
     * <br>[機  能] DB登録値を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil050ParamModel
     * @param buMdl ユーザモデル
     * @return true:取得成功 false:取得失敗
     * @throws SQLException SQL実行例外
     */
    private boolean __setData(Fil050ParamModel paramMdl, BaseUserModel buMdl)
    throws SQLException {

        boolean success = false;

        int dirSid = NullDefault.getInt(paramMdl.getFil050DirSid(), 0);
        FileDao fileDao = new FileDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        //フォルダ情報を取得する。
        FileModel fileModel = fileDao.getFolderInf(dirSid, buMdl.getUsrsid());
        if (fileModel != null) {

            //フォルダパス
            paramMdl.setFil050FolderPath(filBiz.getDirctoryPath(dirSid, con__));

            //備考
            paramMdl.setFil050Biko(NullDefault.getString(
                    StringUtilHtml.transToHTmlPlusAmparsant(fileModel.getFdrBiko()), ""));

            //添付ファイル情報を設定する
            __setFileList(paramMdl, buMdl, fileModel.getFdrVersion());

            //ショートカット
            if (fileModel.getShortcutCount() > 0) {
                //登録済み
                paramMdl.setFil050ShortcutKbn(String.valueOf(GSConstFile.SHORTCUT_ON));
            } else {
                //未登録
                paramMdl.setFil050ShortcutKbn(String.valueOf(GSConstFile.SHORTCUT_OFF));
            }

            //更新通知
            if (fileModel.getCallCount() > 0) {
                //有効
                paramMdl.setFil050CallKbn(String.valueOf(GSConstFile.CALL_ON));
            } else {
                //無効
                paramMdl.setFil050CallKbn(String.valueOf(GSConstFile.CALL_OFF));
            }

            success = true;
        }

        return success;
    }

    /**
     * <br>[機  能] 添付ファイルラベルを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil050ParamModel
     * @param buMdl ユーザモデル
     * @param version バージョン
     * @throws SQLException SQL実行例外
     */
    private void __setFileList(Fil050ParamModel paramMdl, BaseUserModel buMdl, int version)
    throws SQLException {

        int dirSid = NullDefault.getInt(paramMdl.getFil050DirSid(), 0);

        //添付ファイル情報を取得する
        FileDirectoryBinDao dirBinDao = new FileDirectoryBinDao(con__);
        List<CmnBinfModel> cmBinList = dirBinDao.getBinList(dirSid, version);

        if (cmBinList == null || cmBinList.size() < 1) {
            return;
        }

        List<LabelValueBean> fileLabel = new ArrayList<LabelValueBean>();
        for (CmnBinfModel cbModel : cmBinList) {
            String nameAndSize = cbModel.getBinFileName() + cbModel.getBinFileSizeDsp();
            fileLabel.add(new LabelValueBean(
                    nameAndSize, String.valueOf(cbModel.getBinSid())));
        }
        //添付ファイルのラベルを設定する。
        paramMdl.setFil050FileLabelList(fileLabel);

    }
    /**
     * <br>[機  能] 更新履歴一覧を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil050ParamModel
     * @param buMdl セッションユーザモデル
     * @param superUser 特権ユーザ
     * @throws SQLException SQL実行例外
     */
    private void __setUpdateReki(Fil050ParamModel paramMdl, BaseUserModel buMdl, boolean superUser)
    throws SQLException {

        int orderKey = NullDefault.getInt(paramMdl.getFil050OrderKey(), 0);
        int sortKey = NullDefault.getInt(paramMdl.getFil050SortKey(), 0);
        int fdrSid = NullDefault.getInt(paramMdl.getFil050DirSid(), 0);
        Fil050Dao fil050Dao = new Fil050Dao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        //ユーザSID
        int usrSid = superUser ? -1 : buMdl.getUsrsid();
        //件数カウント
        long maxCount = fil050Dao.countRekiList(fdrSid, usrSid);
        log__.debug("件数 = " + maxCount);

        //ファイル管理個人設定を取得する。
        FileUconfModel model = filBiz.getUserConf(buMdl.getUsrsid(), con__);

        //履歴表示件数
        int limit = model.getFucRirekiCnt();
        //現在ページ
        int nowPage = paramMdl.getFil050PageNum1();
        //結果取得開始カーソル位置
        int start = PageUtil.getRowNumber(nowPage, limit);

        //ページあふれ制御
        int maxPageNum = PageUtil.getPageCount(maxCount, limit);
        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, limit);
        if (maxPageStartRow < start) {
            nowPage = maxPageNum;
            start = maxPageStartRow;
        }

        //ページコンボを設定する。
        paramMdl.setFil050PageNum1(nowPage);
        paramMdl.setFil050PageNum2(nowPage);
        paramMdl.setFil050PageLabel(PageUtil.createPageOptions(maxCount, limit));

        if (maxCount < 1) {
            return;
        }

        //更新履歴一覧を取得する。
        List<Fil050Model> rekiList = fil050Dao.getRekiList(
                fdrSid, usrSid, orderKey, sortKey, start, limit);

        paramMdl.setFil050RekiList(rekiList);

        //復旧ボタン表示判定
        if (!superUser) {
            paramMdl.setFil050RepairDspFlg(GSConstFile.DSP_KBN_OFF);
            for (Fil050Model rekiMdl : rekiList) {
                if (rekiMdl.isRepairBtnDspFlg()) {
                    paramMdl.setFil050RepairDspFlg(GSConstFile.DSP_KBN_ON);
                    break;
                }
            }
        }
    }

    /**
     * <br>[機  能] アクセス制御一覧を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param buMdl セッションユーザモデル
     * @throws SQLException 実行例外
     */
    private void __setAccessList(Fil050ParamModel paramMdl, BaseUserModel buMdl)
            throws SQLException {

        int orderKey = NullDefault.getInt(paramMdl.getFil050OrderKey(), 0);
        int sortKey = NullDefault.getInt(paramMdl.getFil050SortKey(), 0);
        int fdrSid = NullDefault.getInt(paramMdl.getFil050DirSid(), 0);
        FileDAccessUserDao dao = new FileDAccessUserDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        //件数カウント
        long maxCount = dao.getAccessListCount(fdrSid);
        log__.debug("件数 = " + maxCount);

        //ファイル管理個人設定を取得する。
        FileUconfModel model = filBiz.getUserConf(buMdl.getUsrsid(), con__);

        //アクセス制御表示件数
        int limit = model.getFucRirekiCnt();
        //現在ページ
        int nowPage = paramMdl.getFil050PageNum1();
        //結果取得開始カーソル位置
        int start = PageUtil.getRowNumber(nowPage, limit);

        //ページあふれ制御
        int maxPageNum = PageUtil.getPageCount(maxCount, limit);
        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, limit);
        if (maxPageStartRow < start) {
            nowPage = maxPageNum;
            start = maxPageStartRow;
        }

        //ページコンボを設定する。
        paramMdl.setFil050PageNum1(nowPage);
        paramMdl.setFil050PageNum2(nowPage);
        paramMdl.setFil050PageLabel(PageUtil.createPageOptions(maxCount, limit));

        if (maxCount < 1) {
            return;
        }

        ArrayList<FileDAccessUserModel> accessList = dao.getAccessList(
                fdrSid, sortKey, orderKey, start, limit);
        paramMdl.setFil050AccessList(accessList);
    }


    /**
     * <br>[機  能] ショートカット情報の設定・削除を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil050ParamModel
     * @param shortcutKbn ショートカット区分
     * @param buMdl ユーザモデル
     * @throws SQLException SQL実行例外
     */
    public void updateShortcut(Fil050ParamModel paramMdl, int shortcutKbn, BaseUserModel buMdl)
    throws SQLException {

        FileShortcutConfDao shortcutDao = new FileShortcutConfDao(con__);

        int dirSid = NullDefault.getInt(paramMdl.getFil050DirSid(), 0);

        int sessionUsrSid = buMdl.getUsrsid();

        if (shortcutKbn == GSConstFile.SHORTCUT_ON) {

            //ショートカット設定を登録する。
            UDate now = new UDate();
            FileShortcutConfModel scModel = new FileShortcutConfModel();
            scModel.setFdrSid(dirSid);
            scModel.setUsrSid(sessionUsrSid);
            scModel.setFscAdate(now);
            shortcutDao.insert(scModel);

        } else if (shortcutKbn == GSConstFile.SHORTCUT_OFF)  {

            //ショートカット情報を削除する。
            shortcutDao.delete(dirSid, sessionUsrSid);
        }

    }

    /**
     * <br>[機  能] 更新通知情報の設定・削除を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil050ParamModel
     * @param callKbn 更新通知区分
     * @param buMdl ユーザモデル
     * @throws SQLException SQL実行例外
     */
    public void updateCall(Fil050ParamModel paramMdl, int callKbn, BaseUserModel buMdl)
    throws SQLException {


        if (paramMdl.getFil050CallLevelKbn() > GSConstFile.CALL_LEVEL_OFF) {
            //子フォルダを一括更新
            updateCallChild(paramMdl, callKbn, buMdl);
        } else {
            //カレントディレクトリのみ更新
            updateCallOne(paramMdl, callKbn, buMdl);
        }

    }

    /**
     * <br>[機  能] 更新通知情報の設定・削除を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil050ParamModel
     * @param callKbn 更新通知区分
     * @param buMdl ユーザモデル
     * @throws SQLException SQL実行例外
     */
    public void updateCallOne(Fil050ParamModel paramMdl, int callKbn, BaseUserModel buMdl)
    throws SQLException {


        FileCallConfDao callConfDao = new FileCallConfDao(con__);
        FileCallDataDao callDataDao = new FileCallDataDao(con__);

        int dirSid = NullDefault.getInt(paramMdl.getFil050DirSid(), 0);
        int sessionUsrSid = buMdl.getUsrsid();

        if (callKbn == GSConstFile.CALL_ON) {

            //更新通知設定を登録する。
            FileCallConfModel callConfModel = new FileCallConfModel();
            callConfModel.setFdrSid(dirSid);
            callConfModel.setUsrSid(sessionUsrSid);
            callConfDao.insert(callConfModel);

        } else if (callKbn == GSConstFile.CALL_OFF)  {

            //更新通知情報を削除する。
            callConfDao.delete(dirSid, sessionUsrSid);
            callDataDao.delete(dirSid, sessionUsrSid);
        }
    }

    /**
     * <br>[機  能] 更新通知情報の設定・削除を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil050ParamModel
     * @param callKbn 更新通知区分
     * @param buMdl ユーザモデル
     * @throws SQLException SQL実行例外
     */
    public void updateCallChild(Fil050ParamModel paramMdl, int callKbn, BaseUserModel buMdl)
    throws SQLException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FilTreeBiz treeBiz = new FilTreeBiz(con__);
        FileCallConfDao callConfDao = new FileCallConfDao(con__);
        FileCallDataDao callDataDao = new FileCallDataDao(con__);
        FileCallConfModel callConfModel = null;

        int dirSid = NullDefault.getInt(paramMdl.getFil050DirSid(), 0);
        int sessionUsrSid = buMdl.getUsrsid();

        //カレントディレクトリ情報取得
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);

        //子階層のデータ取得
        FilChildTreeModel childMdl = treeBiz.getChildOfTarget(dirModel);
        ArrayList<FileDirectoryModel> listOfDir = childMdl.getListOfDir();

        //子階層の更新通知設定を更新
        if (listOfDir != null && listOfDir.size() > 0) {

            if (callKbn == GSConstFile.CALL_ON) {

                callConfDao.delete(listOfDir, sessionUsrSid);
                for (FileDirectoryModel model : listOfDir) {
                    //更新通知設定を登録する。
                    callConfModel = new FileCallConfModel();
                    callConfModel.setFdrSid(model.getFdrSid());
                    callConfModel.setUsrSid(sessionUsrSid);
                    callConfDao.insert(callConfModel);
                }

            } else if (callKbn == GSConstFile.CALL_OFF) {
                callConfDao.delete(listOfDir, sessionUsrSid);
                callDataDao.delete(listOfDir, sessionUsrSid);
            }
        }
    }

    /**
     * <br>[機  能] ファイルの復旧を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl Fil050ParamModel
     * @param buMdl ユーザモデル
     * @param cntCon 採番コントロール
     * @param appRootPath APP ROOTパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール使用可能フラグ
     * @throws Exception 実行例外
     */
    public void updateRepair(
            RequestModel reqMdl,
            Fil050ParamModel paramMdl,
            BaseUserModel buMdl,
            MlCountMtController cntCon,
            String appRootPath,
            PluginConfig pconfig,
            boolean smailPluginUseFlg)
    throws Exception {

        int dirSid = NullDefault.getInt(paramMdl.getFil050SltDirSid(), -1);
        int version = NullDefault.getInt(paramMdl.getFil050SltDirVer(), -1);
        int sessionUsrSid = buMdl.getUsrsid();
        UDate now = new UDate();

        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileFileBinDao fileBinDao = new FileFileBinDao(con__);
        FileFileRekiDao fileRekiDao = new FileFileRekiDao(con__);

        //最新バージョン + 1を取得
        int nextVersion = filBiz.getNextVersion(dirSid, con__);

        //ディレクトリ情報
        FileDirectoryModel dirModel = dirDao.select(dirSid, version);
        if (dirModel != null) {
            dirModel.setFdrVersion(nextVersion);
            dirModel.setFdrJtkbn(GSConstFile.JTKBN_NORMAL);
            dirModel.setFdrAdate(now);
            dirModel.setFdrAuid(sessionUsrSid);
            dirModel.setFdrEdate(now);
            dirModel.setFdrEuid(sessionUsrSid);
            dirDao.insert(dirModel);
        }

        //復旧
        FileDirectoryModel bean = new FileDirectoryModel();
        bean.setFdrSid(dirSid);
        bean.setFdrJtkbn(GSConstFile.JTKBN_NORMAL);
        bean.setFdrEuid(sessionUsrSid);
        bean.setFdrEdate(now);
        dirDao.updateJtkbn(bean);

        //ファイル情報
        FileFileBinModel fileBinModel = fileBinDao.select(dirSid, version);

        if (fileBinModel != null) {

            Long newBinSid = filBiz.copyFile(
                    appRootPath, fileBinModel.getBinSid(),
                    sessionUsrSid, con__, cntCon, reqMdl.getDomain());

            fileBinModel.setBinSid(newBinSid);
            fileBinModel.setFdrVersion(nextVersion);
            fileBinModel.setFflLockKbn(GSConstFile.LOCK_KBN_OFF);
            fileBinDao.insert(fileBinModel);
        }


        //更新履歴情報
        FileFileRekiModel fileRekiModel = fileRekiDao.select(dirSid, version);
        if (fileRekiModel != null) {
            fileRekiModel.setFdrVersion(nextVersion);
            fileRekiModel.setFfrKbn(GSConstFile.REKI_KBN_REPAIR);
            fileRekiModel.setFfrEuid(sessionUsrSid);
            fileRekiModel.setFfrEdate(now);
            fileRekiDao.insert(fileRekiModel);
        }

        //バージョン管理外のファイルを削除する。
        __deleteOldVersion(dirSid, nextVersion - 1, sessionUsrSid);

        //ディレクトリアクセス設定を更新
        dirDao.updateAccessSid(dirSid);

        //更新通知設定
        filBiz.updateCall(
                dirSid, cntCon, appRootPath, pconfig, smailPluginUseFlg, reqMdl, sessionUsrSid);
    }

    /**
     * <br>[機  能] バージョン管理外のファイルを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param newVersion 最新バージョン
     * @param sessionUsrSid セッションユーザSID
     * @throws SQLException SQL実行例外
     */
    private void __deleteOldVersion(int dirSid, int newVersion, int sessionUsrSid)
    throws SQLException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

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
        __deleteOldFile(dirSid, delVersion, sessionUsrSid);

    }

    /**
     * <br>[機  能] バージョン管理外のファイル情報を削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param delVersion 削除基準バージョン
     * @param sessionUsrSid セッションユーザSID
     * @throws SQLException SQL実行例外
     */
    private void __deleteOldFile(int dirSid, int delVersion, int sessionUsrSid)
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
            cmnBinModel.setBinUpuser(sessionUsrSid);
            cmnBinModel.setBinUpdate(now);

            //バイナリー情報を論理削除する
            cbDao.updateJKbn(cmnBinModel, binSidList);
        }

        //ファイル情報を削除する。
        fileDao.deleteOldVersion(dirSid, delVersion);
    }

    /**
     * <br>[機  能] フォルダURLを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Fil050ParamModel
     * @return フォルダURL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    public String __createFolderUrl(Fil050ParamModel paramMdl)
    throws UnsupportedEncodingException {

        String folderUrl = null;

        String url = reqMdl__.getReferer();
        if (!StringUtil.isNullZeroString(url)) {

            folderUrl = url.substring(0, url.lastIndexOf("/"));
            folderUrl = folderUrl.substring(0, folderUrl.lastIndexOf("/"));
            folderUrl += "/common/cmn001.do?url=";

            String paramUrl = reqMdl__.getRequestURI();
            paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("/"));

            String domain = "";
            if (!reqMdl__.getDomain().equals(GSConst.GS_DOMAIN)) {
                domain = reqMdl__.getDomain() + "/";
                paramUrl = paramUrl.replace(
                        GSConstFile.PLUGIN_ID_FILE, domain + GSConstFile.PLUGIN_ID_FILE);
            }

            paramUrl += "/fil050.do";

            paramUrl += "?fil050DirSid=" + paramMdl.getFil050DirSid();
            paramUrl += "&fil010SelectDirSid=" + paramMdl.getFil010SelectDirSid();
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

            folderUrl += paramUrl;
        }

        return folderUrl;
    }
}