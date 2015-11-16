package jp.groupsession.v2.fil.fil090;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.FilTreeBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.model.FilChildTreeModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] フォルダ・ファイル移動画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil090Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil090Biz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /** 選択複数移動区分 1ディレクトリ移動 */
    public static final int MOVE_PLURAL_NO = 0;
    /** 選択複数移動区分 複数移動 */
    public static final int MOVE_PLURAL_YES = 1;

    /** 画面モード 複数移動 */
    public static final int MODE_PLURAL = 2;
    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil090Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil090ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションのルートパス
     * @param buMdl セッションユーザモデル
     * @param domain ドメイン
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setInitData(Fil090ParamModel paramMdl,
                            String tempDir,
                            String appRoot,
                            BaseUserModel buMdl,
                            String domain)
                      throws SQLException, IOToolsException, IOException, TempFileException {
        log__.debug("Fil090Biz Start");

        //移動元ディレクトリ情報を設定する。
        if (paramMdl.getFil090SelectPluralKbn() == MOVE_PLURAL_NO) {
            //単一移動モード
            __setDirData(paramMdl, tempDir, appRoot, buMdl);
        } else {
            //一括移動モード
            __setDirDataForPlural(paramMdl, tempDir, appRoot, buMdl, domain);
        }

        //移動先ディレクトリ階層の表示を設定する。
        __setTreeData(paramMdl);

        List<LabelValueBean> lvList = new ArrayList<LabelValueBean>();

        //更新者設定
        paramMdl.setFil090EditId(NullDefault.getString(
                paramMdl.getFil090EditId(), String.valueOf(buMdl.getUsrsid())));

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
        paramMdl.setFil090groupList(lvList);

    }

    /**
     * <br>[機  能] 移動元ディレクトリ情報を取得しを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil090ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションのルートパス
     * @param buMdl セッションユーザモデル
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private void __setDirData(Fil090ParamModel paramMdl,
                              String tempDir,
                              String appRoot,
                              BaseUserModel buMdl)
                         throws SQLException, IOToolsException, IOException, TempFileException {

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
        if (paramMdl.getFil090Mode().equals(String.valueOf(GSConstFile.DIRECTORY_FOLDER))) {
            paramMdl.setFil090DirName(dirModel.getFdrName());
            filBiz.setFolderTempFile(appRoot, tempDir, dirSid, 0, con__);
        } else {
            filBiz.setTempFile(appRoot, tempDir, dirSid, dirModel.getFdrVersion(), con__);
        }
        //添付ファイルラベルリストの設定
        paramMdl.setFil090FileLabelList(cmnBiz.getTempFileLabelList(tempDir));

        //移動先ディレクトリパスの設定
        paramMdl.setFil090SltDirPath(__getDirPath(paramMdl));

    }

    /**
     * <br>[機  能] 移動元ディレクトリ情報を取得しを設定する。
     * <br>[解  説] 一括移動モード
     * <br>[備  考]
     * @param paramMdl Fil090ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションのルートパス
     * @param buMdl セッションユーザモデル
     * @param domain ドメイン
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private void __setDirDataForPlural(Fil090ParamModel paramMdl,
                                       String tempDir,
                                       String appRoot,
                                       BaseUserModel buMdl,

                                       String domain)
                             throws SQLException, IOToolsException, IOException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);

        //移動するディレクトリ
        String[] selectDir = paramMdl.getFil040SelectDel();

        paramMdl.setFil090Mode(String.valueOf(MODE_PLURAL));

        if (selectDir == null || selectDir.length < 1) {
            return;
        }

        //ディレクトリ情報一覧を取得する。(最新バージョン)
        List<FileDirectoryModel> dirList = dirDao.getNewDirectoryList(selectDir);
        if (dirList == null || dirList.size() < 1) {
            return;
        }

        List<Long> binSidList = new ArrayList<Long>();
        List<String> folderNameList = new ArrayList<String>();
        for (FileDirectoryModel model : dirList) {

            if (model.getFdrKbn() == GSConstFile.DIRECTORY_FILE) {
                //ファイル
                binSidList.add(model.getBinSid());
            } else {
                //フォルダ
                folderNameList.add(model.getFdrName());
            }
        }

        paramMdl.setFil090FolderNameList(folderNameList);

        if (binSidList != null && binSidList.size() > 0) {
            //現在日付の文字列(YYYYMMDD)
            String dateStr = (new UDate()).getDateString();

            //添付ファイル情報リストを取得する
            String[] binSids = new String[binSidList.size()];
            int i = 0;
            for (Long sid : binSidList) {

                binSids[i] = String.valueOf(sid);
                i++;
            }
            List<CmnBinfModel> cmBinList = cmnBiz.getBinInfo(con__, binSids, domain);

            int fileNum = 1;
            for (CmnBinfModel cbMdl : cmBinList) {

                //添付ファイルをテンポラリディレクトリにコピーする。
                cmnBiz.saveTempFile(dateStr, cbMdl, appRoot, tempDir, fileNum);

                fileNum++;
            }
        }

        //添付ファイルラベルリストの設定
        paramMdl.setFil090FileLabelList(cmnBiz.getTempFileLabelList(tempDir));

        //移動先ディレクトリパスの設定
        paramMdl.setFil090SltDirPath(__getDirPath(paramMdl));

    }

    /**
     * <br>[機  能] フォルダ選択のディレクトリツリーを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil090ParamModel
     * @throws SQLException SQL実行例外
     */
    private void __setTreeData(Fil090ParamModel paramMdl) throws SQLException {

        int dirSid = NullDefault.getInt(paramMdl.getFil010SelectDirSid(), -1);

        paramMdl.setTreeFormLv0(null);
        paramMdl.setTreeFormLv1(null);
        paramMdl.setTreeFormLv2(null);
        paramMdl.setTreeFormLv3(null);
        paramMdl.setTreeFormLv4(null);
        paramMdl.setTreeFormLv5(null);
        paramMdl.setTreeFormLv6(null);
        paramMdl.setTreeFormLv7(null);
        paramMdl.setTreeFormLv8(null);
        paramMdl.setTreeFormLv9(null);
        paramMdl.setTreeFormLv10(null);

        //キャビネットSIDを取得する。
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        int cabinetSid = filBiz.getCabinetSid(dirSid, con__);

        //ルートディレクトリSIDを設定する。
        FileDirectoryDao dirdao = new FileDirectoryDao(con__);
        FileDirectoryModel rootModel = dirdao.getRootDirectory(cabinetSid);
        if (rootModel == null) {
            return;
        }

        List<Integer> fdrSidList = new ArrayList<Integer>();


        if (paramMdl.getFil090SelectPluralKbn() == MOVE_PLURAL_NO) {
            //単一移動モード
            int fdrSid = NullDefault.getInt(paramMdl.getFil090DirSid(), 0);
            fdrSidList.add(fdrSid);
        } else {
            //一括移動モード
            String[] selectDir = paramMdl.getFil040SelectDel();
            if (selectDir != null && selectDir.length > 0) {
                for (String selectDirSid : selectDir) {
                    fdrSidList.add(NullDefault.getInt(selectDirSid, 0));
                }
            }
        }

        //特権ユーザ判定
        boolean superUser = filBiz.isEditCabinetUser(cabinetSid, con__);
        //セッション情報を取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //ツリー情報を取得する
        FilTreeBiz treeBiz = new FilTreeBiz(con__);
        paramMdl.setTreeFormLv0(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_0,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv0() == null || paramMdl.getTreeFormLv0().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv1(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_1,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv1() == null || paramMdl.getTreeFormLv1().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv2(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_2,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv2() == null || paramMdl.getTreeFormLv2().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv3(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_3,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv3() == null || paramMdl.getTreeFormLv3().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv4(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_4,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv4() == null || paramMdl.getTreeFormLv4().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv5(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_5,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv5() == null || paramMdl.getTreeFormLv5().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv6(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_6,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv6() == null || paramMdl.getTreeFormLv6().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv7(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_7,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv7() == null || paramMdl.getTreeFormLv7().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv8(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_8,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv8() == null || paramMdl.getTreeFormLv8().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv9(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_9,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
        if (paramMdl.getTreeFormLv9() == null || paramMdl.getTreeFormLv9().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv10(
                treeBiz.getFileTreeForMove(cabinetSid,
                                           GSConstFile.DIRECTORY_LEVEL_10,
                                           fdrSidList,
                                           sessionUsrSid,
                                           superUser));
    }

    /**
     * <br>[機  能] 移動先ディレクトリパスを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil090ParamModel
     * @return dirPath 移動先ディレクトリパス
     * @throws SQLException SQL実行時例外
     */
    private String __getDirPath(Fil090ParamModel paramMdl) throws SQLException {

        int dirSid = NullDefault.getInt(paramMdl.getMoveToDir(), 0);
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        String dirPath = filBiz.getDirctoryPath(dirSid, con__);

        return dirPath;
    }

    /**
     * <br>[機  能] 指定したディレクトリ以下からの最大階層数を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @return maxLevel 指定したディレクトリからの最大階層数
     * @throws SQLException SQL実行時例外
     */
    public int getMaxLevel(int dirSid) throws SQLException {

        int maxLevel = 0;

        //自ディレクトリ情報を取得する。
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);
        if (dirModel == null) {
            return maxLevel;
        }
        if (dirModel.getFdrKbn() == GSConstFile.DIRECTORY_FILE) {
            //ファイルの場合は0階層
            return 0;
        }

        //子階層のデータ取得
        FilTreeBiz treeBiz = new FilTreeBiz(con__);
        FilChildTreeModel childMdl = treeBiz.getChildOfTarget(dirModel);

        //フォルダ階層の更新
        ArrayList<FileDirectoryModel> listOfDir = childMdl.getListOfDir();

        //ファイル階層更新
        ArrayList<FileDirectoryModel> listOfFile = childMdl.getListOfFile();
        //リストを統合する。
        listOfDir.addAll(listOfFile);
        //最大階層数の取得
        int level = __getMaxLevel(listOfDir);

        //指定ディレクトリからの階層数を取得する。
        maxLevel = level - dirModel.getFdrLevel() + 1;

        return maxLevel;
    }

    /**
     * <br>[機  能] ディレクトリリストから最大階層数を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param listOfDir ディレクトリリスト
     * @return maxLevel 指定したディレクトリからの最大階層数
     * @throws SQLException SQL実行時例外
     */
    private int __getMaxLevel(ArrayList<FileDirectoryModel> listOfDir) throws SQLException {
        int maxLevel = 0;

        for (FileDirectoryModel mdl : listOfDir) {
            if (mdl.getFdrLevel() > maxLevel) {
                maxLevel = mdl.getFdrLevel();
            }
        }

        return maxLevel;
    }

}