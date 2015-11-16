package jp.groupsession.v2.fil.fil090kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.FilTreeBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileDAccessConfDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.fil090.Fil090Biz;
import jp.groupsession.v2.fil.model.FilChildTreeModel;
import jp.groupsession.v2.fil.model.FileAccessConfModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] フォルダ・ファイル移動確認画面のBIZクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil090knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil090knBiz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil090knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil090knParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setInitData(
        Fil090knParamModel paramMdl,
        String tempDir,
        String appRoot)
        throws SQLException, IOToolsException, IOException, TempFileException {

        log__.debug("Fil090knBiz start");

        if (paramMdl.getFil090SelectPluralKbn() == Fil090Biz.MOVE_PLURAL_YES) {
            //一括移動の場合
            __setDirDataForPlural(paramMdl, tempDir, appRoot);
            return;
        }

        CommonBiz cmnBiz = new CommonBiz();
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);

        int dirSid = NullDefault.getInt(paramMdl.getFil090DirSid(), -1);

        //ディレクトリ情報を取得する。
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);
        if (dirModel == null) {
            return;
        }

        paramMdl.setFil090Mode(String.valueOf(dirModel.getFdrKbn()));
        paramMdl.setFil090Biko(StringUtilHtml.transToHTmlPlusAmparsant(dirModel.getFdrBiko()));
        int cabSid = filBiz.getCabinetSid(dirSid, con__);
        paramMdl.setFil090VerKbn(String.valueOf(filBiz.getVerKbn(cabSid, dirSid, con__)));

        //添付ファイルをテンポラリディレクトリに保存する。
        if (paramMdl.getFil090Mode().equals(GSConstFile.DIRECTORY_FOLDER)) {
            paramMdl.setFil090DirName(dirModel.getFdrName());
            filBiz.setFolderTempFile(appRoot, tempDir, dirSid, 0, con__);
        } else {
            filBiz.setTempFile(appRoot, tempDir, dirSid, dirModel.getFdrVersion(), con__);
        }
        //添付ファイルラベルリストの設定
        paramMdl.setFil090FileLabelList(cmnBiz.getTempFileLabelList(tempDir));

        //移動先ディレクトリパスの設定
        paramMdl.setFil090SltDirPath(__getDirPath(paramMdl));


        //更新者を取得
        String editId = NullDefault.getString(
                paramMdl.getFil090EditId(),
                      String.valueOf(reqMdl__.getSmodel().getUsrsid()));
        if (filBiz.isEditGroup(editId)) {
            //グループ情報取得
            GroupDao gpDao = new GroupDao(con__);
            if (ValidateUtil.isNumber(editId.substring("G".length()))) {
                CmnGroupmModel gpMdl = new CmnGroupmModel();
                gpMdl = gpDao.getGroup(Integer.valueOf(editId.substring("G".length())));
                paramMdl.setFil090knEditName(gpMdl.getGrpName());
            }
        } else {
            paramMdl.setFil090knEditName(reqMdl__.getSmodel().getUsiseimei());
        }
    }

    /**
     * <br>[機  能] 移動元ディレクトリ情報を取得しを設定する。
     * <br>[解  説] 一括移動モード
     * <br>[備  考]
     * @param paramMdl Fil090knParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションのルートパス
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private void __setDirDataForPlural(Fil090knParamModel paramMdl, String tempDir, String appRoot)
    throws SQLException, IOToolsException, IOException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        //移動するディレクトリ
        String[] selectDir = paramMdl.getFil040SelectDel();

        if (selectDir == null || selectDir.length < 1) {
            return;
        }

        //ディレクトリ情報一覧を取得する。(最新バージョン)
        List<FileDirectoryModel> dirList = dirDao.getNewDirectoryList(selectDir);
        if (dirList == null || dirList.size() < 1) {
            return;
        }

        List<String> folderNameList = new ArrayList<String>();
        for (FileDirectoryModel model : dirList) {
            if (model.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
                //フォルダ
                folderNameList.add(model.getFdrName());
            }
        }

        paramMdl.setFil090FolderNameList(folderNameList);

        //添付ファイルラベルリストの設定
        paramMdl.setFil090FileLabelList(cmnBiz.getTempFileLabelList(tempDir));

        //移動先ディレクトリパスの設定
        paramMdl.setFil090SltDirPath(__getDirPath(paramMdl));

        //更新者を取得
        String editId = NullDefault.getString(
                paramMdl.getFil090EditId(),
                      String.valueOf(reqMdl__.getSmodel().getUsrsid()));
        if (filBiz.isEditGroup(editId)) {
            //グループ情報取得
            GroupDao gpDao = new GroupDao(con__);
            if (ValidateUtil.isNumber(editId.substring("G".length()))) {
                CmnGroupmModel gpMdl = new CmnGroupmModel();
                gpMdl = gpDao.getGroup(Integer.valueOf(editId.substring("G".length())));
                paramMdl.setFil090knEditName(gpMdl.getGrpName());
            }
        } else {
            paramMdl.setFil090knEditName(reqMdl__.getSmodel().getUsiseimei());
        }

    }

    /**
     * <br>[機  能] フォルダ・ファイルの移動処理。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil090knParamModel
     * @param buMdl セッションユーザモデル
     * @throws SQLException SQL実行例外
     */
    public void moveDir(
        Fil090knParamModel paramMdl,
        BaseUserModel buMdl) throws SQLException {
        //移動元ディレクトリ
        int dirSid = NullDefault.getInt(paramMdl.getFil090DirSid(), 0);

        //移動先ディレクトリ
        int sltDirSid = NullDefault.getInt(paramMdl.getMoveToDir(), 0);

        FilCommonBiz filBiz = new FilCommonBiz();
        //編集者グループSID（グループ名登録でない場合は0）
        int egid = filBiz.getGroupSid(paramMdl.getFil090EditId());

        moveDir(dirSid, sltDirSid, egid, buMdl.getUsrsid());

    }
    /**
     * <br>[機  能] フォルダ・ファイルの移動処理。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid 移動元ディレクトリ
     * @param sltDirSid 移動先ディレクトリ
     * @param egid 編集者グループSID（グループ名登録でない場合は0）
     * @param sessionUsrSid セッションユーザSID
     * @throws SQLException SQL実行例外
     */
    public void moveDir(
            int dirSid,
            int sltDirSid,
            int egid,
            int sessionUsrSid) throws SQLException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDAccessConfDao daConfdao = new FileDAccessConfDao(con__);


        //移動先ディレクトリ情報を取得する。
        FileDirectoryModel sltDirModel = dirDao.select(sltDirSid, GSConstFile.VERSION_NEW);
        if (sltDirModel == null) {
            return;
        }
        //移動元ディレクトリ情報を取得する。
        FileDirectoryModel dirModel = dirDao.select(dirSid, -1);
        if (dirModel == null) {
            return;
        }

        //親ディレクトリ情報の更新
        __updateParent(sessionUsrSid, dirSid,
                dirModel.getFdrKbn(), sltDirModel, egid);
        //----------2015-08-17仕様変更-----------
        //旧仕様 親ディレクトリのアクセス権限を適用する
        //daConfdao.deleteSubDirectoriesFiles(dirSid, false);
        //新仕様 親ディレクトリとのアクセス権限の差分を適用する
        __updateDAccessConf(daConfdao, dirSid, sltDirSid);
        //-------------------------------------



        if (dirModel.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {


            //移動したディレクトリの階層の更新
            __updateLevel(sltDirSid, sessionUsrSid, dirModel, egid);
        }
        dirDao.updateAccessSid(sltDirSid);
    }

    /**
     * <br>[機  能] フォルダ・ファイルの移動処理。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil090knParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param buMdl セッションユーザモデル
     * @param appRootPath アプリケーションルートパス
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     */
    public void moveDirPlural(
        Fil090knParamModel paramMdl,
        String tempDir,
        BaseUserModel buMdl,
        String appRootPath) throws SQLException, IOToolsException, IOException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDAccessConfDao daConfdao = new FileDAccessConfDao(con__);

        //移動元ディレクトリ
        String[] fil040Select = paramMdl.getFil040SelectDel();

        //ディレクトリ情報一覧を取得する。(最新バージョン)
        List<FileDirectoryModel> dirList = dirDao.getNewDirectoryList(fil040Select);
        if (dirList == null || dirList.size() < 1) {
            return;
        }

        //移動先ディレクトリ
        int sltDirSid = NullDefault.getInt(paramMdl.getMoveToDir(), 0);
        //移動先ディレクトリ情報を取得する。
        FileDirectoryModel sltDirModel = dirDao.select(sltDirSid, GSConstFile.VERSION_NEW);
        if (sltDirModel == null) {
            return;
        }
        FilCommonBiz filBiz = new FilCommonBiz();
        for (FileDirectoryModel dirModel : dirList) {

            //親ディレクトリ情報の更新
            int egid = filBiz.getGroupSid(paramMdl.getFil090EditId());
            __updateParent(buMdl.getUsrsid(),
                    dirModel.getFdrSid(), dirModel.getFdrKbn(), sltDirModel, egid);
            //----------2015-08-17仕様変更-----------
            //旧仕様 親ディレクトリのアクセス権限を適用する
            //daConfdao.deleteSubDirectoriesFiles(dirModel.getFdrSid(), false);
            //新仕様 親ディレクトリとのアクセス権限の差分を適用する
            __updateDAccessConf(daConfdao, dirModel.getFdrSid(), sltDirSid);
            //-------------------------------------

            if (dirModel.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
                //移動したディレクトリの階層の更新
                __updateLevel(sltDirSid, buMdl.getUsrsid(), dirModel, egid);
            }
        }
        dirDao.updateAccessSid(sltDirSid);
    }
    /**
     *
     * <br>[機  能] 移動先ディレクトリのアクセス制限にないサブディレクトリの個別アクセス制限を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param daDao FileDAccessConfDao
     * @param dirSid 移動対象ディレクトリ
     * @param sltDirSid 移動先ディレクトリ
     * @throws SQLException SQL実行時例外
     */
    private void __updateDAccessConf(FileDAccessConfDao daDao,
            int dirSid,
            int sltDirSid) throws SQLException {
        final int kbn_write = Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE);
        final int kbn_read = Integer.parseInt(GSConstFile.ACCESS_KBN_READ);
        String[] fullaccessGroups = daDao.getAccessParentGroup(sltDirSid, kbn_write);
        String[] fullaccessUsers = daDao.getAccessParentUser(sltDirSid, -1, kbn_write);
        String[] readGroups = daDao.getAccessParentGroup(sltDirSid, kbn_read);
        String[] readUsers = daDao.getAccessParentUser(sltDirSid, -1, kbn_read);

        //移動先に制限がない場合は処理無し
        if (fullaccessGroups.length == 0 && fullaccessUsers.length == 0
                && readGroups.length == 0 && readUsers.length == 0) {
            return;
        }
        //下位権限の論理チェックを行う
        List<FileAccessConfModel> subList = daDao.getAccessSubDirectoriesFiles(dirSid, false);
        if (subList == null || subList.size() <= 0) {
            return;
        }
        ArrayList<Integer> accessGroups = new ArrayList<Integer>();
        ArrayList<Integer> accessUsers = new ArrayList<Integer>();
        for (String sid : fullaccessGroups) {
            accessGroups.add(Integer.parseInt(sid.substring(1)));
        }
        for (String sid : readGroups) {
            accessGroups.add(Integer.parseInt(sid.substring(1)));
        }
        for (String sid : fullaccessUsers) {
            accessUsers.add(Integer.parseInt(sid));
        }
        for (String sid : readUsers) {
            accessUsers.add(Integer.parseInt(sid));
        }

        List<Integer> delSids = new ArrayList<Integer>();
        for (FileAccessConfModel confMdl : subList) {
            //親ディレクトリの権限に含まれないグループ・ユーザを削除
            boolean delFlg = false;
            int subSid = confMdl.getFcbSid();
            if (confMdl.getFaaAuth() == kbn_write) {
                //追加・変更・削除
                if (confMdl.getUsrKbn() == GSConstFile.USER_KBN_GROUP) {
                    //グループ
                    delFlg = __deleteNotInc(daDao,
                                            accessGroups,
                                            subSid,
                                            confMdl.getUsrSid(),
                                            GSConstFile.USER_KBN_GROUP);
                } else if (confMdl.getUsrKbn() == GSConstFile.USER_KBN_USER) {
                    //ユーザ
                    delFlg = __deleteNotInc(daDao,
                                            accessUsers,
                                            subSid,
                                            confMdl.getUsrSid(),
                                            GSConstFile.USER_KBN_USER);
                }

            } else if (confMdl.getFaaAuth() == kbn_read) {
                //閲覧
                if (confMdl.getUsrKbn() == GSConstFile.USER_KBN_GROUP) {
                    //グループ
                    delFlg = __deleteNotInc(daDao,
                                            accessGroups,
                                            subSid,
                                            confMdl.getUsrSid(),
                                            GSConstFile.USER_KBN_GROUP);
                } else if (confMdl.getUsrKbn() == GSConstFile.USER_KBN_USER) {
                    //ユーザ
                    delFlg = __deleteNotInc(daDao,
                                            accessUsers,
                                            subSid,
                                            confMdl.getUsrSid(),
                                            GSConstFile.USER_KBN_USER);
                }
            }

            //削除したSIDを追加
            if (delFlg && !delSids.contains(subSid)) {
                delSids.add(subSid);
            }
        }

        //削除したフォルダ・ファイルのアクセス権限をチェック
        for (int subSid : delSids) {
            int count = daDao.getCount(subSid);
            if (count <= 0) {
                //アクセス権限が一切なくなったフォルダ、ファイルを管理者のみの閲覧にする
                daDao.deleteSubDirectoriesFiles(subSid, false);
                FileAccessConfModel daConfMdl = new FileAccessConfModel();
                daConfMdl.setFcbSid(subSid);
                daConfMdl.setUsrSid(0);
                daConfMdl.setUsrKbn(GSConstFile.USER_KBN_USER);
                daConfMdl.setFaaAuth(Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
                daDao.insert(daConfMdl);
            }
        }

    }
    /**
     * 親の権限に含まれていないグループ・ユーザを削除する
     * @param dao FileDAccessConfDao
     * @param sids 親権限SIDリスト
     * @param fdrSid ディレクトリSID
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ区分
     * @return true:削除実行 false:削除対象でない
     * @throws SQLException 実行例外
     */
    private boolean __deleteNotInc(FileDAccessConfDao dao,
                                   ArrayList<Integer> sids,
                                   int fdrSid,
                                   int usrSid,
                                   int usrKbn) throws SQLException {

        if (!sids.contains(new Integer(usrSid))) {
            //削除
            dao.delete(fdrSid, usrSid, usrKbn);
            return true;
        }
        return false;
    }
    /**
     * <br>[機  能] 親ディレクトリ情報を更新する。
     * <br>[解  説]
     * <br>[備  考]
     * @param sessionUsrSid セッションユーザSID
     * @param dirSid 移動元ディレクトリ
     * @param fdrKbn ディレクトリ区分
     * @param sltDirModel 移動先ディレクトリ情報
     * @param egid 編集者グループSID ユーザ名登録の場合は0
     * @throws SQLException SQL実行時例外
     */
    private void __updateParent(
            int sessionUsrSid,
            int dirSid,
            int fdrKbn,
            FileDirectoryModel sltDirModel,
            int egid)
    throws SQLException {


        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDirectoryModel dirModel = new FileDirectoryModel();
        UDate now = new UDate();
        dirModel.setFdrSid(dirSid);
        dirModel.setFdrLevel(sltDirModel.getFdrLevel() + 1);
        dirModel.setFdrEdate(now);
        dirModel.setFdrEuid(sessionUsrSid);
        dirModel.setFdrParentSid(sltDirModel.getFdrSid());
        dirModel.setFdrEgid(egid);

        //ファイル移動モード時は、更新履歴を更新する。
        if (fdrKbn == GSConstFile.DIRECTORY_FILE) {
            //親ディレクトリSIDとディレクトリ階層を更新する
            dirDao.updateParLv(dirModel);
        } else {
            //親ディレクトリを更新する
            if (dirModel.getFdrEgid() > 0) {
                dirDao.updateParentSidByGp(dirModel);
            } else {
                dirDao.updateParentSid(dirModel);
            }

        }
    }

    /**
     * <br>[機  能] 移動フォルダの階層情報を更新する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid 移動先ディレクトリSID
     * @param sessionUsrSid セッションユーザSID
     * @param dirModel 移動元ディレクトリ情報
     * @param egid 編集者グループSID ユーザ名登録の場合は0
     * @throws SQLException SQL実行時例外
     */
    private void __updateLevel(int dirSid, int sessionUsrSid,
            FileDirectoryModel dirModel, int egid)
    throws SQLException {

        //子階層のデータ取得
        FilTreeBiz treeBiz = new FilTreeBiz(con__);
        FilChildTreeModel childMdl = treeBiz.getChildOfTarget(dirModel);

        //移動前後の階層の差を取得
        int margin = __getLevelMargin(dirModel.getFdrSid(), dirSid);

        //フォルダ階層の更新
        ArrayList<FileDirectoryModel> listOfDir = childMdl.getListOfDir();
        if (!listOfDir.isEmpty()) {
            __updateLevel(listOfDir, sessionUsrSid, margin, egid);
        }

        //ファイル階層更新
        ArrayList<FileDirectoryModel> listOfFile = childMdl.getListOfFile();
        if (!listOfFile.isEmpty()) {
            __updateLevel(listOfFile, sessionUsrSid, margin, egid);
        }
    }

    /**
     * <br>[機  能] 移動前後のディレクトリ階層差を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid 移動元ディレクトリ
     * @param moveToDir 移動先ディレクトリ
     * @return margin 移動前後の階層差
     * @throws SQLException SQL実行時例外
     */
    private int __getLevelMargin(int dirSid, int moveToDir) throws SQLException {

        int margin = 0;
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);

        //移動先ディレクトリ情報を取得する。
        FileDirectoryModel sltDirModel = dirDao.getNewDirectory(moveToDir);
        if (dirModel == null || sltDirModel == null) {
            return margin;
        }
        margin = sltDirModel.getFdrLevel() - dirModel.getFdrLevel() + 1;

        return margin;
    }

    /**
     * <br>[機  能] ディレクトリの階層を更新する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirList 更新ディレクトリリスト
     * @param sessionUsrSid セッションユーザSID
     * @param margin 移動前後の階層差
     * @param egid 編集者グループSID ユーザ名登録の場合は0
     * @throws SQLException SQL実行時例外
     */
    private void __updateLevel(ArrayList<FileDirectoryModel> dirList,
                     int sessionUsrSid, int margin, int egid)
    throws SQLException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        UDate now = new UDate();

        for (FileDirectoryModel model : dirList) {
            model.setFdrEuid(sessionUsrSid);
            model.setFdrEgid(egid);
            model.setFdrEdate(now);
            model.setFdrLevel(model.getFdrLevel() + margin);
            dirDao.updateLevel(model);
        }

    }

    /**
     * <br>[機  能] 移動先ディレクトリパスを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil090knParamModel
     * @return dirPath 移動先ディレクトリパス
     * @throws SQLException SQL実行時例外
     */
    private String __getDirPath(Fil090knParamModel paramMdl) throws SQLException {

        int dirSid = NullDefault.getInt(paramMdl.getMoveToDir(), 0);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        String dirPath = filBiz.getDirctoryPath(dirSid, con__);

        return dirPath;
    }
}