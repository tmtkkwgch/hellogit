package jp.groupsession.v2.fil.fil080;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;

import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.WmlDao;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.WmlMailDataModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.WmlTempfileModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.FilTreeBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileAconfDao;
import jp.groupsession.v2.fil.dao.FileCabinetDao;
import jp.groupsession.v2.fil.dao.FileCallDataDao;
import jp.groupsession.v2.fil.dao.FileDAccessConfDao;
import jp.groupsession.v2.fil.dao.FileDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.dao.FileFileRekiDao;
import jp.groupsession.v2.fil.dao.FileFileTextDao;
import jp.groupsession.v2.fil.dao.FileShortcutConfDao;
import jp.groupsession.v2.fil.fil010.Fil010Biz;
import jp.groupsession.v2.fil.fil010.FileCabinetDspModel;
import jp.groupsession.v2.fil.model.FileAconfModel;
import jp.groupsession.v2.fil.model.FileCabinetModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileFileBinModel;
import jp.groupsession.v2.fil.model.FileFileRekiModel;
import jp.groupsession.v2.fil.model.FileModel;
import jp.groupsession.v2.fil.model.FileParentAccessDspModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ファイル登録画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil080Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil080Biz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil080Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションのルートパス
     * @param buMdl セッションユーザモデル
     * @throws Exception  実行時例外
     */
    public void setInitData(
        Fil080ParamModel paramMdl,
        String tempDir,
        String appRoot,
        BaseUserModel buMdl) throws Exception {

        log__.debug("Fil080Biz Start");
        int dirSid = NullDefault.getInt(paramMdl.getFil070DirSid(), -1);

        if (dirSid == -1) {
            paramMdl.setFil080Mode(GSConstFile.MODE_ADD);
        } else {
            paramMdl.setFil080Mode(GSConstFile.MODE_EDIT);
        }

        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        CommonBiz commonBiz = new CommonBiz();

        //複数登録区分が変更されていた場合はテンポラリディレクトリを削除する。
        if (paramMdl.getFil080PluralKbn().equals(Fil080ParamModel.PLURALKBN_SINGLE)
                && paramMdl.getFil080SvPluralKbn().equals(Fil080ParamModel.PLURALKBN_MULTI)) {
            //テンポラリディレクトリのファイル削除を行う
            IOTools.deleteDir(tempDir);
        }
        paramMdl.setFil080SvPluralKbn(paramMdl.getFil080PluralKbn());

        if (paramMdl.getFil080PluginId() == null) {
            //初期表示
            if (paramMdl.getFil080Mode() == GSConstFile.MODE_EDIT) {
                //編集モードの場合DBより各項目を設定する。
                __setData(paramMdl, tempDir, appRoot, buMdl);
            }

            //プラグインIDを設定
            paramMdl.setFil080PluginId(GSConstFile.PLUGIN_ID_FILE);

            //個人設定が無い場合は初期値で登録する。
            filBiz.getUserConf(buMdl.getUsrsid(), con__);
        }


        //親ディレクトリSIDを取得する。
        int parentDirSid = NullDefault.getInt(paramMdl.getFil070ParentDirSid(), -1);
        if (parentDirSid < 1) {
            parentDirSid = filBiz.getParentDirSid(dirSid, con__);
        }
        paramMdl.setFil070ParentDirSid(String.valueOf(parentDirSid));
        if (parentDirSid > 0) {
            //バージョン管理区分表示設定を行う。
            __setVersion(paramMdl);

        }
        //フォルダパスを設定する。
        paramMdl.setFil080DirPath(filBiz.getDirctoryPath(parentDirSid, con__));

        //ファイルロックユーザ名設定（セッションユーザ）
        paramMdl.setFil080LockUsrName(buMdl.getUsiseimei());

        //添付ファイルのラベルを設定する。
        List<LabelValueBean> fileList = commonBiz.getTempFileLabelList(tempDir);
        paramMdl.setFil080FileLabelList(fileList);

        //WEBメール連携の場合、キャビネットコンボとディレクトリツリー情報を設定する
        if (paramMdl.getFil080webmail() == 1) {
            __setCabinetList(buMdl, paramMdl, con__);

            //キャビネットSID
            int fcbSid = NullDefault.getInt(paramMdl.getFil040SelectCabinet(), -1);
            //特権ユーザ判定
            boolean superUser = filBiz.isEditCabinetUser(fcbSid, con__);
            //ユーザSID
            int sessionUsrSid = buMdl.getUsrsid();

            //Tree情報取得
            FilTreeBiz treeBiz = new FilTreeBiz(con__);
            paramMdl.setTreeFormLv0(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_0,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv1(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_1,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv2(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_2,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv3(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_3,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv4(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_4,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv5(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_5,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv6(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_6,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv7(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_7,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv8(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_8,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv9(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_9,
                                               null,
                                               sessionUsrSid,
                                               superUser));
            paramMdl.setTreeFormLv10(
                    treeBiz.getFileTreeForMove(fcbSid,
                                               GSConstFile.DIRECTORY_LEVEL_10,
                                               null,
                                               sessionUsrSid,
                                               superUser));
        }

        List<LabelValueBean> lvList = new ArrayList<LabelValueBean>();

        //更新者設定
        paramMdl.setFil080EditId(NullDefault.getString(
                paramMdl.getFil080EditId(), String.valueOf(buMdl.getUsrsid())));

        lvList.add(new LabelValueBean(
                buMdl.getUsiseimei(), String.valueOf(buMdl.getUsrsid())));

        //ユーザの所属グループを取得
        ArrayList<GroupModel> gpList = null;
        UsidSelectGrpNameDao gpDao = new UsidSelectGrpNameDao(con__);
        gpList = gpDao.selectGroupNmListOrderbyClass(buMdl.getUsrsid());
        if (gpList != null && !gpList.isEmpty()) {
            for (GroupModel gpMdl : gpList) {
                lvList.add(new LabelValueBean(
                        gpMdl.getGroupName(), "G" + String.valueOf(gpMdl.getGroupSid())));
            }
        }
        paramMdl.setFil080groupList(lvList);

        if (__isParentZeroUser(parentDirSid)) {
            //親がゼロユーザ
            paramMdl.setFil080ParentZeroUser("1");
            return;
        } else {
            paramMdl.setFil080ParentZeroUser("0");
        }

        //親ディレクトリ（追加・変更・削除）アクセス制限リスト
        paramMdl.setFil080ParentEditList(__getParentAccessUser(
                paramMdl, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE)));
        //親ディレクトリ（閲覧）アクセス制限リスト
        paramMdl.setFil080ParentReadList(__getParentAccessUser(
                paramMdl, Integer.parseInt(GSConstFile.ACCESS_KBN_READ)));
        if (paramMdl.getFil080ParentEditList().size()
                > Integer.parseInt(GSConstFile.MAXCOUNT_PARENT_ACCESS)
         || paramMdl.getFil080ParentReadList().size()
                > Integer.parseInt(GSConstFile.MAXCOUNT_PARENT_ACCESS)) {
            paramMdl.setFil080ParentAccessAllDspFlg(1);
        }

        //アクセス権限 追加・変更・削除 グループコンボ
        paramMdl.setFil080AcEditGroupLavel(__getAccessGroupLavle(
                true, parentDirSid, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE)));
        //アクセス権限 追加・変更・削除 候補一覧
        paramMdl.setFil080AcEditRightLavel(__getAccessEditRightLavle(paramMdl));
        //アクセス権限 フルアクセス一覧
        paramMdl.setFil080AcFullLavel(__getAccessFullLavle(paramMdl));

        //アクセス権限 閲覧 グループコンボ
        paramMdl.setFil080AcReadGroupLavel(__getAccessGroupLavle(
                true, parentDirSid, Integer.parseInt(GSConstFile.ACCESS_KBN_READ)));
        //アクセス権限 閲覧 候補一覧
        paramMdl.setFil080AcReadRightLavel(__getAccessReadRightLavle(paramMdl));
        //アクセス権限 閲覧アクセス一覧
        paramMdl.setFil080AcReadLavel(__getAccessReadLavle(paramMdl));
    }

    /**
     * <br>[機  能] DBよりファイル情報を取得しを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションのルートパス
     * @param buMdl セッションユーザモデル
     * @throws Exception 実行時例外
     */
    private void __setData(Fil080ParamModel paramMdl,
                           String tempDir,
                           String appRoot,
                           BaseUserModel buMdl)
                           throws Exception {

        int dirSid = NullDefault.getInt(paramMdl.getFil070DirSid(), -1);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        FileDao filDao = new FileDao(con__);

        //最新のファイル情報を取得する。
        FileModel fileModel = filDao.getFileInf(dirSid, buMdl.getUsrsid());

        if (fileModel == null) {
            return;
        }

        paramMdl.setFil080Biko(fileModel.getFdrBiko());
        paramMdl.setFil080VerKbn(String.valueOf(fileModel.getFdrVerKbn()));
        paramMdl.setFil080EditId(filBiz.setUpdateSid(buMdl.getUsrsid(),
                                                    fileModel.getFdrEgid(),
                                                    con__));

        //添付ファイルをテンポラリディレクトリへ設定する
        filBiz.setTempFile(appRoot, tempDir, dirSid, fileModel.getFdrVersion(), con__);

        int version = filBiz.getNewVersion(dirSid, con__);

        //ファイルロックを行う。
        filBiz.updateLockKbnCommit(
                dirSid, version, GSConstFile.LOCK_KBN_ON, buMdl.getUsrsid(), con__);

        //アクセス制限情報
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);
        if (daConfDao.getCount(dirSid) > 0) {
            //追加・変更・削除
            String[] leftFull = daConfDao.getAccessUser(
                    dirSid, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
            if (leftFull != null && leftFull.length > 0) {
                //0ユーザチェック
                List<String> leftList = new ArrayList<String>();
                leftList.addAll(Arrays.asList(leftFull));
                if (leftList.contains("0")) {
                    leftList.remove("0");
                    leftFull = leftList.toArray(new String[leftList.size()]);
                }
            }
            paramMdl.setFil080SvAcFull(leftFull);
            //閲覧
            String[] leftRead = daConfDao.getAccessUser(
                    dirSid, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
            paramMdl.setFil080SvAcRead(leftRead);
            paramMdl.setFil080AccessKbn(String.valueOf(GSConstFile.ACCESS_KBN_ON));
        } else {
            paramMdl.setFil080AccessKbn(String.valueOf(GSConstFile.ACCESS_KBN_OFF));

        }
    }

    /**
     * <br>[機  能] ファイルを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param usrSid ユーザSID
     * @return fileName ファイル名
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException IOエラー
     */
    public String deleteFile(Fil080ParamModel paramMdl, String tempDir, int usrSid)
    throws SQLException, IOToolsException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileFileBinDao fileDao = new FileFileBinDao(con__);
        FileFileRekiDao rekiDao = new FileFileRekiDao(con__);
        FileShortcutConfDao shortcutDao = new FileShortcutConfDao(con__);
        FileCallDataDao callDataDao = new FileCallDataDao(con__);
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);

        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        int dirSid = NullDefault.getInt(paramMdl.getFil070DirSid(), -1);
        int fcbSid = filBiz.getCabinetSid(dirSid, con__);
        UDate now = new UDate();

        //バージョン管理区分を取得する。
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);

        //管理者設定の削除したファイルの保存期間を取得する。
        int saveDays = filBiz.getDelFileSaveDays(con__);

        if (dirModel == null) {
            return null;
        }

        int version = dirModel.getFdrVersion();
        int verKbn = filBiz.getVerKbn(fcbSid, dirSid, con__);

        if (verKbn != GSConstFile.VERSION_KBN_OFF || saveDays > 0) {
            //ﾊﾞージョン管理 = ON の場合、論理削除を行う。
            FileDirectoryModel fileDirModel = new FileDirectoryModel();
            fileDirModel.setFdrSid(dirSid);
            fileDirModel.setFdrEuid(usrSid);
            fileDirModel.setFdrEdate(now);
            fileDirModel.setFdrJtkbn(GSConstFile.JTKBN_DELETE);
            dirDao.updateJtkbn(fileDirModel);

            //削除バージョンを登録する。
            FileDirectoryModel delDirModel = new FileDirectoryModel();
            delDirModel.setFdrSid(dirSid);
            delDirModel.setFcbSid(dirModel.getFcbSid());
            delDirModel.setFdrLevel(dirModel.getFdrLevel());
            delDirModel.setFdrParentSid(dirModel.getFdrParentSid());
            delDirModel.setFdrName(dirModel.getFdrName());
            delDirModel.setFdrVersion(dirModel.getFdrVersion() + 1);
            delDirModel.setFdrVerKbn(dirModel.getFdrVerKbn());
            delDirModel.setFdrBiko(dirModel.getFdrBiko());
            delDirModel.setFdrKbn(dirModel.getFdrKbn());
            delDirModel.setFdrEdate(now);
            delDirModel.setFdrAuid(usrSid);
            delDirModel.setFdrAdate(now);
            delDirModel.setFdrEuid(usrSid);
            delDirModel.setFdrEdate(now);
            delDirModel.setFdrJtkbn(GSConstFile.JTKBN_DELETE);
            dirDao.insert(delDirModel);

            FileFileBinDao fileBinDao = new FileFileBinDao(con__);
            FileFileBinModel binModel = fileBinDao.select(dirSid, version);
            binModel.setFdrVersion(version + 1);
            fileBinDao.insert(binModel);
            if (verKbn == GSConstFile.VERSION_KBN_OFF) {
                dirDao.delete(dirModel.getFdrSid(), dirModel.getFdrVersion());
                fileBinDao.delete(dirModel.getFdrSid(), dirModel.getFdrVersion());
            }

            //ディレクトリアクセス設定を更新
            __insertDeleteFileDaccess(dirSid);
            dirDao.updateAccessSid(dirSid);
        } else {

            //ディレクトリアクセス制限削除
            daConfDao.delete(dirSid);

            //ディレクトリ情報を削除する。
            dirDao.delete(dirSid);

            //共通ファイル情報の物理削除
            __deleteCmnFile(dirSid, usrSid);
            //ファイル情報を削除する。
            fileDao.delete(dirSid);
        }

        //ショートカット情報
        shortcutDao.delete(dirSid, usrSid);
        //更新通知情報
        callDataDao.delete(dirSid, usrSid);

        //テキスト情報
        FileFileTextDao textDao = new FileFileTextDao(con__);
        textDao.delete(dirSid);

        //ファイル更新履歴を登録する。
        FileFileRekiModel rekiModel = new FileFileRekiModel();
        rekiModel.setFdrSid(dirSid);
        rekiModel.setFdrVersion(version + 1);
        rekiModel.setFfrFname(dirModel.getFdrName());
        rekiModel.setFfrKbn(GSConstFile.REKI_KBN_DELETE);
        rekiModel.setFfrParentSid(dirModel.getFdrParentSid());
        rekiModel.setFfrUpCmt("");
        rekiModel.setFfrEdate(now);
        rekiModel.setFfrEuid(usrSid);
        rekiDao.insert(rekiModel);

        return dirModel.getFdrName();
    }

    /**
     * <br>[機  能] 論理削除ファイルに権限設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @throws NumberFormatException 実行例外
     * @throws SQLException 実行例外
     */
    private void __insertDeleteFileDaccess(int dirSid) throws NumberFormatException, SQLException {

        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);
        if (daConfDao.getCount(dirSid) > 0) {
            return;
        }

        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        int parentSid = filBiz.getParentDirSid(dirSid, con__);

        //フルアクセス
        String[] editSids = daConfDao.getAccessUser(
                parentSid, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
        if (editSids != null && editSids.length > 0) {
            ArrayList<Integer> userList = new ArrayList<Integer>();
            ArrayList<Integer> groupList = new ArrayList<Integer>();
            for (int i = 0; i < editSids.length; i++) {
                String str = NullDefault.getString(editSids[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    groupList.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    userList.add(new Integer(str));
                }
            }
            //フルアクセスグループ登録
            daConfDao.insert(dirSid, groupList,
                    GSConstFile.USER_KBN_GROUP, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
            //フルアクセスユーザ登録
            daConfDao.insert(dirSid, userList,
                    GSConstFile.USER_KBN_USER, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
        }

        //閲覧アクセス
        String[] readSids = daConfDao.getAccessUser(
                parentSid, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
        if (readSids != null && readSids.length > 0) {
            ArrayList<Integer> userList = new ArrayList<Integer>();
            ArrayList<Integer> groupList = new ArrayList<Integer>();
            for (int i = 0; i < readSids.length; i++) {
                String str = NullDefault.getString(readSids[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    groupList.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    userList.add(new Integer(str));
                }
            }
            //閲覧アクセスグループ登録
            daConfDao.insert(dirSid, groupList,
                    GSConstFile.USER_KBN_GROUP, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
            //閲覧アクセスユーザ登録
            daConfDao.insert(dirSid, userList,
                    GSConstFile.USER_KBN_USER, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
        }
    }

    /**
     * <br>[機  能] 共通ファイル情報を削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param usrSid ユーザSID
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException IOエラー
     */
    private void __deleteCmnFile(int dirSid, int usrSid) throws SQLException, IOToolsException {

        FileFileBinDao fileDao = new FileFileBinDao(con__);
        CmnBinfDao cbDao = new CmnBinfDao(con__);
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
     * <br>[機  能] バージョン管理の表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080ParamModel
     * @throws SQLException SQL実行時例外
     */
    private void __setVersion(Fil080ParamModel paramMdl) throws SQLException {


        //管理者設定バージョン管理区分の設定
        int admVerKbn = __setVerKbnAdmin(paramMdl);
        if (admVerKbn == GSConstFile.VERSION_KBN_OFF) {
            return;
        }

        int dirSid = NullDefault.getInt(paramMdl.getFil070DirSid(), -1);

        //キャビネットSIDを取得する。
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        int fcbSid = 0;
        if (dirSid == -1) {
            fcbSid = filBiz.getCabinetSid(
                    NullDefault.getInt(paramMdl.getFil070ParentDirSid(), -2), con__);
        } else {
            fcbSid = filBiz.getCabinetSid(dirSid, con__);
        }

        FileCabinetDao cabnetDao = new FileCabinetDao(con__);
        FileCabinetModel cabModel = cabnetDao.select(fcbSid);

        if (cabModel.getFcbVerallKbn() == GSConstFile.VERSION_ALL_KBN_ON) {
            paramMdl.setFil080VerKbn(String.valueOf(cabModel.getFcbVerKbn()));
            paramMdl.setFil080VerallKbn(String.valueOf(GSConstFile.VERSION_ALL_KBN_ON));
            return;
        }

        //バージョンコンボ設定
        paramMdl.setFil080VerKbnLabelList(filBiz.getVerKbnLabelList());
        paramMdl.setFil080VerallKbn(String.valueOf(GSConstFile.VERSION_ALL_KBN_OFF));

    }

    /**
     * <br>[機  能] ロック解除処理
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil080ParamModel
     * @param usrSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void fileUnLock(Fil080ParamModel paramMdl, int usrSid) throws SQLException {

        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        int dirSid = NullDefault.getInt(paramMdl.getFil070DirSid(), -1);
        //最新のバージョンを取得
        int version = filBiz.getNewVersion(dirSid, con__);

        boolean commitFlg = false;
        con__.setAutoCommit(false);
        try {
            //ロックを解除する。
            filBiz.updateLockKbn(dirSid, version, GSConstFile.LOCK_KBN_OFF, usrSid, con__);

            commitFlg = true;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (commitFlg) {
                con__.commit();
            } else {
                JDBCUtil.rollback(con__);
            }
        }
    }

    /**
     * <br>[機  能] キャビネット変更時設定。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setChangeCabinet(Fil080ParamModel paramMdl, Connection con)
    throws SQLException {


        paramMdl.setFil010SelectCabinet(paramMdl.getFil040SelectCabinet());
    }

    /**
     * <br>[機  能] WEBメール メール情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトリパス
     * @throws Exception 実行例外
     */
    public void setWebmailData(
        Fil080ParamModel paramMdl,
        String appRootPath,
        String tempDir) throws Exception {

        long mailNum = paramMdl.getFil080webmailId();
        WmlDao wmlDao = new WmlDao(con__);
        WmlMailDataModel mailData = wmlDao.getMailData(mailNum, reqMdl__.getDomain());

        IOTools.deleteDir(tempDir);

        if (mailData.getTempFileList() != null) {
            UDate now = new UDate();
            String dateStr = now.getDateString();
            CommonBiz cmnBiz = new CommonBiz();
            int fileNum = 1;
            for (WmlTempfileModel fileMdl : mailData.getTempFileList()) {
                cmnBiz.saveTempFileForWebmail(dateStr, fileMdl, appRootPath, tempDir, fileNum);
                fileNum++;
            }

            if (mailData.getTempFileList().size() > 1) {
                paramMdl.setFil080PluralKbn(Fil080ParamModel.PLURALKBN_MULTI);
                paramMdl.setFil080SvPluralKbn(Fil080ParamModel.PLURALKBN_MULTI);
            } else {
                paramMdl.setFil080PluralKbn(Fil080ParamModel.PLURALKBN_SINGLE);
                paramMdl.setFil080SvPluralKbn(Fil080ParamModel.PLURALKBN_SINGLE);
            }
        }
    }

    /**
     * <br>[機  能] キャビネットコンボを設定する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param buMdl ユーザーモデル
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setCabinetList(BaseUserModel buMdl, Fil080ParamModel paramMdl, Connection con)
    throws SQLException {

        Fil010Biz fil010Biz = new Fil010Biz(reqMdl__);
        ArrayList<FileCabinetDspModel> cabinetList
                = fil010Biz.getCabinetList(buMdl, con, false, true);
        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        labelList.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"),
                String.valueOf(-1)));

        if (cabinetList != null && cabinetList.size() > 0) {
            for (FileCabinetDspModel model : cabinetList) {
                labelList.add(new LabelValueBean(model.getFcbName(),
                        String.valueOf(model.getFcbSid())));
            }
        }
        paramMdl.setFil040CabinetList(labelList);
    }

    /**
     * <br>管理者設定のバージョン管理区分を取得します。
     * @param paramMdl Fil080ParamModel
     * @return verKbn バージョン管理区分（管理者設定）
     * @throws SQLException SQL実行時例外
     */
    private int __setVerKbnAdmin(Fil080ParamModel paramMdl) throws SQLException {

        FileAconfDao aconfDao = new FileAconfDao(con__);
        FileAconfModel aconfMdl = aconfDao.select();

        if (aconfMdl == null) {
            return GSConstFile.VERSION_KBN_ON;
        }
        paramMdl.setAdmVerKbn(aconfMdl.getFacVerKbn());

        return aconfMdl.getFacVerKbn();
    }

    /**
     * 親ディレクトリのアクセス制限ユーザリストを取得する
     * @param paramMdl パラメータモデル
     * @param auth 権限区分
     * @return アクセス制限ユーザリスト
     * @throws SQLException 実行例外
     */
    private ArrayList<FileParentAccessDspModel> __getParentAccessUser(
            Fil080ParamModel paramMdl,
            int auth) throws SQLException {

        ArrayList<FileParentAccessDspModel> ret = new ArrayList<FileParentAccessDspModel>();

        GroupDao dao = new GroupDao(con__);
        CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con__);

        //グループを全て取得
        CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
        ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);

        //親ディレクトリのアクセス権限を取得
        List<String> parentSids = new ArrayList<String>();
        String[] sids = null;
        int fdrSid = NullDefault.getInt(paramMdl.getFil070ParentDirSid(), -1);
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);
        sids = daConfDao.getAccessUser(fdrSid, auth);
        if (sids != null) {
            parentSids.addAll(Arrays.asList(sids));
        }

        //アクセス制限ユーザリスト作成（グループ）
        if (parentSids != null && parentSids.size() > 0) {
            for (GroupModel bean : allGpList) {
                if (!parentSids.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    continue;
                }
                FileParentAccessDspModel dspBean = new FileParentAccessDspModel();
                dspBean.setUserName(bean.getGroupName());
                dspBean.setGrpFlg(1);
                ret.add(dspBean);
            }
        }

        //アクセス制限ユーザリスト作成（ユーザ）
        if (parentSids != null && parentSids.size() > 0) {
            for (int i = 0; i < parentSids.size(); i++) {
                if (parentSids.get(i).substring(0, 1).equals("G")
                 || parentSids.get(i).equals("0")) {
                    parentSids.remove(i);
                    i--;
                }
            }
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> usList
                = userBiz.getUserList(con__, (String[]) parentSids.toArray(new String[0]));
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                FileParentAccessDspModel dspBean = new FileParentAccessDspModel();
                dspBean.setUserName(cuiMdl.getUsiSei() + " " + cuiMdl.getUsiMei());
                dspBean.setGrpFlg(0);
                ret.add(dspBean);
            }
        }

        return ret;
    }

    /**
     * アクセス権限の候補抽出用グループ一覧を取得する
     * @param comboflg true:コンボ用 false:候補用
     * @param parentSid 親SID
     * @param auth 権限区分
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessGroupLavle(
            boolean comboflg, int parentSid, int auth)
    throws SQLException {
        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

//        GroupDao dao = new GroupDao(con);
//        ArrayList<GroupModel> gpList = dao.getGroupTree();
        GroupBiz groupBiz = new GroupBiz();
        ArrayList<GroupModel> gpList = groupBiz.getGroupList(con__);
        LabelValueBean lavelBean = new LabelValueBean();

        GsMessage gsMsg = new GsMessage(reqMdl__);

        if (comboflg) {
            String textGroup = gsMsg.getMessage("cmn.grouplist");
            lavelBean.setLabel(textGroup);
            lavelBean.setValue(GSConstFile.GROUP_COMBO_VALUE);
            ret.add(lavelBean);
        } else {
            String textSelect = gsMsg.getMessage("cmn.select.plz");
            lavelBean.setLabel(textSelect);
            lavelBean.setValue("-1");
            ret.add(lavelBean);
        }

        //親ディレクトリのアクセス制限を取得
        List<String> parentSids = new ArrayList<String>();
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);
        //編集候補に閲覧候補も選択できるようにする
        auth = Integer.parseInt(GSConstFile.ACCESS_KBN_READ);
        String[] sids = daConfDao.getAccessParentUserForBelongGroup(parentSid, auth);
        if (sids != null) {
            parentSids.addAll(Arrays.asList(sids));
        }

        //グループが取得できない場合は作成しない
        if (parentSids == null || parentSids.size() <= 0) {
            return ret;
        }

        //コンボ作成
        for (GroupModel gmodel : gpList) {
            if (parentSids.size() > 0
            && (!parentSids.contains(String.valueOf(gmodel.getGroupSid()))
            &&  !parentSids.contains(String.valueOf("G" + gmodel.getGroupSid())))) {
                continue;
            }
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(gmodel.getGroupName());
            lavelBean.setValue(String.valueOf(gmodel.getGroupSid()));
            ret.add(lavelBean);
        }
        return ret;
    }

    /**
     * アクセス権限(追加・変更・削除)の候補用一覧を取得する
     * @param paramMdl Fil080ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessEditRightLavle(Fil080ParamModel paramMdl)
            throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        List<String> parentSids = new ArrayList<String>();

        //アクセス権限 グループコンボ選択値
        int acSltGp = NullDefault.getInt(
                paramMdl.getFil080AcEditSltGroup(),
                Integer.parseInt(GSConstFile.GROUP_COMBO_VALUE));
        //除外するグループSID
        String[] leftFull = paramMdl.getFil080SvAcFull();
        String[] leftRead = paramMdl.getFil080SvAcRead();

        if (acSltGp == Integer.parseInt(GSConstFile.GROUP_COMBO_VALUE)) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con__);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con__);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);

            //除外するグループSID(フルアクセス選択済み)
            List<String> fullGrepList = new ArrayList<String>();
            if (leftFull != null) {
                fullGrepList.addAll(Arrays.asList(leftFull));
            }
            if (leftRead != null) {
                fullGrepList.addAll(Arrays.asList(leftRead));
            }

            //親ディレクトリのアクセス権限を取得
            parentSids = __getParentAccessSids(
                    paramMdl, GSConstFile.USER_KBN_GROUP,
                    Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE), acSltGp);
            parentSids.addAll(__getParentAccessSids(
                    paramMdl, GSConstFile.USER_KBN_GROUP,
                    Integer.parseInt(GSConstFile.ACCESS_KBN_READ), acSltGp));
            if (parentSids == null || parentSids.size() <= 0) {
                return ret;
            }

            for (GroupModel bean : allGpList) {
                if (!parentSids.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    continue;
                }
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    ret.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }

        } else {

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            if (leftFull != null) {
                for (int i = 0; i < leftFull.length; i++) {
                    usrSids.add(new Integer(NullDefault.getInt(leftFull[i], -1)));
                }
            }
            if (leftRead != null) {
                for (int i = 0; i < leftRead.length; i++) {
                    usrSids.add(new Integer(NullDefault.getInt(leftRead[i], -1)));
                }
            }
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con__, acSltGp, usrSids);

            //親ディレクトリのアクセス権限を取得
            parentSids = __getParentAccessSids(
                    paramMdl, GSConstFile.USER_KBN_USER,
                    Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE), acSltGp);
            parentSids.addAll(__getParentAccessSids(
                    paramMdl, GSConstFile.USER_KBN_USER,
                    Integer.parseInt(GSConstFile.ACCESS_KBN_READ), acSltGp));
            if (parentSids == null || parentSids.size() <= 0) {
                return ret;
            }

            LabelValueBean lavelBean = null;
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                if (parentSids.size() > 0
                && !parentSids.contains(String.valueOf(cuiMdl.getUsrSid()))) {
                    continue;
                }
                lavelBean = new LabelValueBean();
                lavelBean.setLabel(cuiMdl.getUsiSei() + " " + cuiMdl.getUsiMei());
                lavelBean.setValue(String.valueOf(cuiMdl.getUsrSid()));
                ret.add(lavelBean);
            }
        }

        return ret;
    }

    /**
     * アクセス権限(閲覧)の候補用一覧を取得する
     * @param paramMdl Fil080ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessReadRightLavle(
            Fil080ParamModel paramMdl)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        List<String> parentSids = new ArrayList<String>();

        //アクセス権限 グループコンボ選択値
        int acSltGp = NullDefault.getInt(
                paramMdl.getFil080AcReadSltGroup(),
                Integer.parseInt(GSConstFile.GROUP_COMBO_VALUE));
        //除外するグループSID
        String[] leftRead = paramMdl.getFil080SvAcRead();
        String[] leftFull = paramMdl.getFil080SvAcFull();

        if (acSltGp == Integer.parseInt(GSConstFile.GROUP_COMBO_VALUE)) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con__);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con__);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);

            //除外するグループSID(閲覧アクセス選択済み)
            List<String> readGrepList = new ArrayList<String>();
            if (leftRead != null) {
                readGrepList.addAll(Arrays.asList(leftRead));
            }
            if (leftFull != null) {
                readGrepList.addAll(Arrays.asList(leftFull));
            }

            //親ディレクトリのアクセス権限を取得
            parentSids = __getParentAccessSids(
                    paramMdl, GSConstFile.USER_KBN_GROUP,
                    Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE), acSltGp);
            parentSids.addAll(__getParentAccessSids(
                    paramMdl, GSConstFile.USER_KBN_GROUP,
                    Integer.parseInt(GSConstFile.ACCESS_KBN_READ), acSltGp));
            if (parentSids == null || parentSids.size() <= 0) {
                return ret;
            }

            for (GroupModel bean : allGpList) {
                if (!parentSids.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    continue;
                }
                if (!readGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    ret.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }

        } else {

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            if (leftRead != null) {
                for (int i = 0; i < leftRead.length; i++) {
                    usrSids.add(new Integer(NullDefault.getInt(leftRead[i], -1)));
                }
            }
            if (leftFull != null) {
                for (int i = 0; i < leftFull.length; i++) {
                    usrSids.add(new Integer(NullDefault.getInt(leftFull[i], -1)));
                }
            }
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con__, acSltGp, usrSids);

            //親ディレクトリのアクセス権限を取得
            parentSids = __getParentAccessSids(
                    paramMdl, GSConstFile.USER_KBN_USER,
                    Integer.parseInt(GSConstFile.ACCESS_KBN_READ), acSltGp);
            if (parentSids == null || parentSids.size() <= 0) {
                return ret;
            }

            LabelValueBean lavelBean = null;
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                if (parentSids.size() > 0
                && !parentSids.contains(String.valueOf(cuiMdl.getUsrSid()))) {
                    continue;
                }
                lavelBean = new LabelValueBean();
                lavelBean.setLabel(cuiMdl.getUsiSei() + " " + cuiMdl.getUsiMei());
                lavelBean.setValue(String.valueOf(cuiMdl.getUsrSid()));
                ret.add(lavelBean);
            }
        }

        return ret;
    }

    /**
     * 親ディレクトリのアクセス権限を取得する
     * @param paramMdl パラメータモデル
     * @param usrKbn ユーザ区分
     * @param auth 権限区分
     * @param acSltGp グループSID
     * @return SID
     * @throws NumberFormatException 実行例外
     * @throws SQLException 実行例外
     */
    private List<String> __getParentAccessSids(Fil080ParamModel paramMdl,
            int usrKbn, int auth, int acSltGp) throws NumberFormatException, SQLException {

        List<String> sids = new ArrayList<String>();
        String[] parentSids = null;
        int fdrSid = NullDefault.getInt(paramMdl.getFil070ParentDirSid(), -1);

        //親ディレクトリのアクセス権限を取得
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);
        if (usrKbn == GSConstFile.USER_KBN_USER) {
            parentSids = daConfDao.getAccessParentUser(fdrSid, acSltGp, auth);
        } else if (usrKbn == GSConstFile.USER_KBN_GROUP) {
            parentSids = daConfDao.getAccessParentGroup(fdrSid, auth);
        }

        if (parentSids != null) {
            sids.addAll(Arrays.asList(parentSids));
        }

        return sids;
    }

    /**
     * 親ディレクトリのアクセス権限がゼロユーザか判定する
     * @param parentSid 親ディレクトリSID
     * @return true:ゼロユーザ false:ゼロユーザでない
     * @throws SQLException 実行例外
     */
    private boolean __isParentZeroUser(int parentSid) throws SQLException {

        String[] sids = null;
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);
        sids = daConfDao.getAccessUser(
                parentSid, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
        if (sids != null && sids.length > 0) {
            List<String> parentSids = new ArrayList<String>();
            parentSids.addAll(Arrays.asList(sids));
            if (parentSids.contains("0")) {
                //ゼロユーザ
                return true;
            }
        }

        return false;
    }

    /**
     * アクセス権限のフルアクセス用一覧を取得する
     * @param paramMdl Fil080ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessFullLavle(
            Fil080ParamModel paramMdl)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getFil080SvAcFull();
        return __getAccessLavle(leftFull);
    }

    /**
     * アクセス権限のフルアクセス用一覧を取得する
     * @param paramMdl Fil080ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessReadLavle(
            Fil080ParamModel paramMdl)
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


}
