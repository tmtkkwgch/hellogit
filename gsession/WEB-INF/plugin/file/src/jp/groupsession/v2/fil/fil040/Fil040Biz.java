package jp.groupsession.v2.fil.fil040;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
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
import jp.groupsession.v2.fil.fil010.Fil010Biz;
import jp.groupsession.v2.fil.fil010.FileCabinetDspModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileFileBinModel;
import jp.groupsession.v2.fil.model.FileFileRekiModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * フォルダ情報一覧で使用するビジネスロジッククラス
 * @author JTS
 */
public class Fil040Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil040Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>コンストラクタ
     * @param reqMdl RequestModel
     */
    public Fil040Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>初期表示画面情報を取得します
     * @param paramMdl Fil040ParamModel
     * @param con コネクション
     * @return errorFlg ディレクトリが存在しない場合false
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイル操作時例外
     * @throws IOException IOエラー
     */
    public boolean setInitData(Fil040ParamModel paramMdl, Connection con)
    throws SQLException, IOToolsException, IOException {

        //セッション情報を取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        FilCommonBiz cmnBiz = new FilCommonBiz(con, reqMdl__);
        int cabSid = NullDefault.getInt(paramMdl.getFil010SelectCabinet(), -1);
        int dspDirSid = NullDefault.getInt(paramMdl.getFil010SelectDirSid(), -1);

        //URL設定
        paramMdl.setFil040UrlString(createFolderUrl(paramMdl));
        //画面制御設定
        CommonBiz  commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(con, usModel, GSConstFile.PLUGIN_ID_FILE);

        if (adminUser) {
            paramMdl.setFil010DspAdminConfBtn(GSConstFile.DSP_KBN_ON);
            paramMdl.setFil010DspCabinetAddBtn(GSConstFile.DSP_KBN_ON);
        } else if (cmnBiz.isCanCreateCabinetUser(usModel, con)) {
            paramMdl.setFil010DspCabinetAddBtn(GSConstFile.DSP_KBN_ON);
        }

        //特権ユーザ判定
        boolean superUser = cmnBiz.isEditCabinetUser(cabSid, con);
        paramMdl.setFil040DspAddBtn(GSConstFile.DSP_KBN_OFF);
        if (superUser) {
            paramMdl.setFil040DspAddBtn(GSConstFile.DSP_KBN_ON);
        } else if (cmnBiz.isDirAccessAuthUser(cabSid,
                                              dspDirSid,
                                              sessionUsrSid,
                                              Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE),
                                              con)) {
            paramMdl.setFil040DspAddBtn(GSConstFile.DSP_KBN_ON);
        }

        //管理者設定ロック区分を設定する。
        paramMdl.setAdmLockKbn(cmnBiz.getLockKbnAdmin(con));

        //表示するカレントディレクトリSIDを取得
        FileDirectoryDao dirDao = new FileDirectoryDao(con);

        FileDirectoryModel dirMdl = null;
        dirMdl = dirDao.getNewDirectory(dspDirSid);
        if (dirMdl == null) {
            //ディレクトリが情報が存在しない場合
            return false;
        }

        //選択されたディレクトリがファイルだった場合、親ディレクトリ取得
        if (dirMdl.getFdrKbn() == GSConstFile.DIRECTORY_FILE) {
            dspDirSid = dirMdl.getFdrParentSid();
            dirMdl = null;
            dirMdl = dirDao.getNewDirectory(dspDirSid);
        }

        if (dirMdl != null) {
            log__.debug("表示するカレントディレクトリSID=>" + dirMdl.getFdrSid());
            //キャビネット名称
            paramMdl.setFil040CabinetName(cmnBiz.getCabinetName(cabSid, con));
            //タイトル用パス情報
            paramMdl.setFil040DirectoryPathList(
                    cmnBiz.getRootToCurrentDirctoryList(dirMdl.getFdrSid(), con));

            //Tree情報取得
            __setTreeInfo(paramMdl, con, dirMdl.getFcbSid(), sessionUsrSid, superUser);
//            FilTreeBiz treeBiz = new FilTreeBiz(con);
//            paramMdl.setTreeFormLv0(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_0,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv1(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_1,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv2(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_2,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv3(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_3,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv4(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_4,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv5(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_5,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv6(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_6,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv7(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_7,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv8(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_8,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv9(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_9,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));
//            paramMdl.setTreeFormLv10(
//                    treeBiz.getFileTree(dirMdl.getFcbSid(),
//                                        GSConstFile.DIRECTORY_LEVEL_10,
//                                        sessionUsrSid,
//                                        -1,
//                                        superUser));

            //TREE OPEN設定
            paramMdl.setSelectDir(String.valueOf(dirMdl.getFdrSid()));
//          ソート
            int sort = paramMdl.getFil040SortKey();
            //オーダー
            int order = paramMdl.getFil040OrderKey();
            //カレント情報取得
            if (superUser) {
                //特権ユーザの場合全てのフォルダ・ファイルを取得
                paramMdl.setFil040DirectoryList(
                        dirDao.getNewLowDirectory(
                                dirMdl.getFdrSid(), sessionUsrSid, sort, order, reqMdl__));
            } else {
                //特権ユーザでない場合閲覧可能なフォルダ・ファイルを取得
                paramMdl.setFil040DirectoryList(
                        dirDao.getNewLowDirectoryAccessLimit(
                                dirMdl.getFdrSid(), sessionUsrSid, sort, order, reqMdl__));
            }

            //全て選択・解除用有無を設定
            paramMdl.setFil040DspSelectDelAll(
                    __getDspSelectDelAll(paramMdl.getFil040DirectoryList()));

            //10階層の場合はフォルダ追加非表示
            paramMdl.setFil040DspFolderAddBtn(GSConstFile.DSP_KBN_ON);
            if (dirMdl.getFdrLevel() == GSConstFile.DIRECTORY_LEVEL_10) {
                paramMdl.setFil040DspFolderAddBtn(GSConstFile.DSP_KBN_OFF);
            }
            //ファイルアンロック権限判定
            if (cmnBiz.isCanFileUnlockUser(dirMdl.getFcbSid(), con)) {
                paramMdl.setFil040UnLockAuth(GSConstFile.UNLOCK_AUTH_ON);
            } else {
                paramMdl.setFil040UnLockAuth(GSConstFile.UNLOCK_AUTH_OFF);
            }

        }

        //アクセス可能なキャビネット一覧を取得
        __setCabinetList(usModel, paramMdl, con);

        return true;
    }

    /**
     * <br>[機  能] Tree情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @param cabSid キャビネットSID
     * @param sessionUsrSid ユーザSID
     * @param superUser 特権ユーザ
     * @throws SQLException 実行例外
     */
    private void __setTreeInfo(Fil040ParamModel paramMdl,
                               Connection con,
                               int cabSid,
                               int sessionUsrSid,
                               boolean superUser) throws SQLException {

        //Tree情報取得
        FilTreeBiz treeBiz = new FilTreeBiz(con);
        paramMdl.setTreeFormLv0(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_0,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv0() == null || paramMdl.getTreeFormLv0().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv1(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_1,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv1() == null || paramMdl.getTreeFormLv1().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv2(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_2,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv2() == null || paramMdl.getTreeFormLv2().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv3(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_3,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv3() == null || paramMdl.getTreeFormLv3().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv4(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_4,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv4() == null || paramMdl.getTreeFormLv4().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv5(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_5,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv5() == null || paramMdl.getTreeFormLv5().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv6(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_6,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv6() == null || paramMdl.getTreeFormLv6().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv7(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_7,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv7() == null || paramMdl.getTreeFormLv7().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv8(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_8,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv8() == null || paramMdl.getTreeFormLv8().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv9(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_9,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
        if (paramMdl.getTreeFormLv9() == null || paramMdl.getTreeFormLv9().length <= 0) {
            return;
        }
        paramMdl.setTreeFormLv10(
                treeBiz.getFileTree(cabSid,
                                    GSConstFile.DIRECTORY_LEVEL_10,
                                    sessionUsrSid,
                                    -1,
                                    superUser));
    }

    /**
     * <br>[機  能] 全て選択・解除用有無
     * <br>[解  説]
     * <br>[備  考]
     * @param dirList ディレクトリリスト
     * @return 0:表示しない 1:表示する
     */
    private String __getDspSelectDelAll(ArrayList<FileDirectoryDspModel> dirList) {

        String dsp = "0";
        if (dirList != null && dirList.size() > 0) {
            for (FileDirectoryDspModel dirMdl : dirList) {
                if (dirMdl.getAccessKbn() == 1) {
                    dsp = "1";
                    return dsp;
                }
            }
        }

        return dsp;
    }

    /**
     * <br>[機  能] フォルダURLを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Fil040ParamModel
     * @return フォルダURL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    public String createFolderUrl(Fil040ParamModel paramMdl)
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

            paramUrl += "/fil040.do";

            paramUrl += "?fil010SelectCabinet=" + paramMdl.getFil010SelectCabinet();
            paramUrl += "&fil010SelectDirSid=" + paramMdl.getFil010SelectDirSid();
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

            folderUrl += paramUrl;
        }

        return folderUrl;
    }

    /**
     * <br>[機  能] ディレクトリの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Fil040ParamModel
     * @param con コネクション
     * @return filDirList 削除選択されたフォルダ、ファイル名のリスト
     * @throws SQLException SQL実行時例外
     */
    public List<String> deleteDirectory(Fil040ParamModel paramMdl, Connection con)
    throws SQLException {
        return deleteDirectory(paramMdl.getFil040SelectDel(),
                NullDefault.getInt(paramMdl.getFil010SelectCabinet(), -1),
                NullDefault.getInt(paramMdl.getFil010SelectDirSid(), -1),
                con);
    }
    /**
     * <br>[機  能] ディレクトリの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param delSids 削除対象SID配列
     * @param fcbSid キャビネットSID
     * @param parentDirSid 親ディレクトリSID
     * @param con コネクション
     * @return filDirList 削除選択されたフォルダ、ファイル名のリスト
     * @throws SQLException SQL実行時例外
     */
    public List<String> deleteDirectory(String[] delSids,
            int fcbSid,
            int parentDirSid,
            Connection con)
    throws SQLException {

        //セッション情報を取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        UDate now = new UDate();

        FileDirectoryDao dirDao = new FileDirectoryDao(con);
        FileDirectoryBinDao dirBinDao = new FileDirectoryBinDao(con);
        FileCallConfDao cConfDao = new FileCallConfDao(con);
        FileCallDataDao cDataDao = new FileCallDataDao(con);
        FileShortcutConfDao shortDao = new FileShortcutConfDao(con);
        FileFileBinDao fBinDao = new FileFileBinDao(con);
        FileFileRekiDao rekiDao = new FileFileRekiDao(con);
        CmnBinfDao binDao = new CmnBinfDao(con);
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con);
        FilCommonBiz filBiz = new FilCommonBiz(con, reqMdl__);

        CmnBinfModel binMdl = new CmnBinfModel();
        binMdl.setBinUpuser(sessionUsrSid);
        binMdl.setBinUpdate(now);
        binMdl.setBinJkbn(GSConst.JTKBN_DELETE);

        ArrayList<Long> binSidList = null;
        FileDirectoryModel dirMdl = null;
        List<String> filDirList =  new ArrayList<String>();

        //管理者設定の削除したファイルの保存期間を取得する。
        int saveDays = filBiz.getDelFileSaveDays(con);


        for (String sid : delSids) {
            //削除するディレクトリを取得
            dirMdl = dirDao.getNewDirectory(Integer.parseInt(sid));
            if (dirMdl == null) {
                continue;
            }
            //ログ出力時のファイル・フォルダ名をfilDirListの末尾に追加していく
            filDirList.add(dirMdl.getFdrName());

            if (dirMdl.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
                //ディレクトリは物理削除
                binSidList = dirBinDao.getBinSidList(
                        dirMdl.getFdrSid(), dirMdl.getFdrVersion());
                binDao.updateJKbn(binMdl, binSidList);
                dirDao.delete(dirMdl.getFdrSid(), dirMdl.getFdrVersion());
                dirBinDao.delete(dirMdl.getFdrSid());
                cConfDao.delete(dirMdl.getFdrSid());
                cDataDao.delete(dirMdl.getFdrSid());
                shortDao.delete(dirMdl.getFdrSid());
            } else {

                //バージョン管理区分を取得する。
                int verKbn = filBiz.getVerKbn(fcbSid, NullDefault.getInt(sid, -1), con);

                //ファイルはバージョン管理する場合は論理削除
                if (verKbn != GSConstFile.VERSION_KBN_OFF || saveDays > 0) {
                    //論理削除
                    FileDirectoryModel bean = new FileDirectoryModel();
                    bean.setFdrSid(dirMdl.getFdrSid());
                    bean.setFdrJtkbn(GSConstFile.JTKBN_DELETE);
                    bean.setFdrEuid(sessionUsrSid);
                    bean.setFdrEdate(now);
                    dirDao.updateJtkbn(bean);

                    //削除バージョンを登録する。
                    FileDirectoryModel delDirModel = new FileDirectoryModel();
                    delDirModel.setFdrSid(dirMdl.getFdrSid());
                    delDirModel.setFcbSid(dirMdl.getFcbSid());
                    delDirModel.setFdrLevel(dirMdl.getFdrLevel());
                    delDirModel.setFdrParentSid(dirMdl.getFdrParentSid());
                    delDirModel.setFdrName(dirMdl.getFdrName());
                    delDirModel.setFdrVersion(dirMdl.getFdrVersion() + 1);
                    delDirModel.setFdrVerKbn(verKbn);
                    delDirModel.setFdrBiko(dirMdl.getFdrBiko());
                    delDirModel.setFdrKbn(dirMdl.getFdrKbn());
                    delDirModel.setFdrEdate(now);
                    delDirModel.setFdrAuid(sessionUsrSid);
                    delDirModel.setFdrAdate(now);
                    delDirModel.setFdrEuid(sessionUsrSid);
                    delDirModel.setFdrEdate(now);
                    delDirModel.setFdrJtkbn(GSConstFile.JTKBN_DELETE);
                    dirDao.insert(delDirModel);

                    FileFileBinModel binModel
                    = fBinDao.select(dirMdl.getFdrSid(), dirMdl.getFdrVersion());
                    binModel.setFdrVersion(dirMdl.getFdrVersion() + 1);
                    fBinDao.insert(binModel);

                    if (verKbn == GSConstFile.VERSION_KBN_OFF) {
                        dirDao.delete(dirMdl.getFdrSid(), dirMdl.getFdrVersion());
                        fBinDao.delete(dirMdl.getFdrSid(), dirMdl.getFdrVersion());
                    }

                    //論理削除したディレクトリにアクセス制限を設定
                    __insertDeleteFileDaccess(dirMdl.getFdrSid(), con);

                } else {

                    //物理削除
                    daConfDao.delete(dirMdl.getFdrSid());
                    binSidList = fBinDao.getBinSidList(dirMdl.getFdrSid(), dirMdl.getFdrVersion());
                    binDao.updateJKbn(binMdl, binSidList);
                    dirDao.delete(dirMdl.getFdrSid(), dirMdl.getFdrVersion());
                    fBinDao.delete(dirMdl.getFdrSid(), dirMdl.getFdrVersion());
                }

                //ショートカット削除
                shortDao.delete(dirMdl.getFdrSid());
                //更新通知削除
                cDataDao.delete(dirMdl.getFdrSid());

                //ファイルテキスト情報
                FileFileTextDao textDao = new FileFileTextDao(con);
                textDao.delete(dirMdl.getFdrSid());

                //履歴出力
                FileFileRekiModel rekiModel = new FileFileRekiModel();
                rekiModel.setFdrSid(dirMdl.getFdrSid());
                rekiModel.setFdrVersion(dirMdl.getFdrVersion() + 1);
                rekiModel.setFfrFname(dirMdl.getFdrName());
                rekiModel.setFfrKbn(GSConstFile.REKI_KBN_DELETE);
                rekiModel.setFfrParentSid(dirMdl.getFdrParentSid());
                rekiModel.setFfrEdate(now);
                rekiModel.setFfrEuid(sessionUsrSid);
                rekiDao.insert(rekiModel);
            }
        }

        //アクセス制限を更新
        dirDao.updateAccessSid(parentDirSid);

        return filDirList;
    }

    /**
     * <br>[機  能] 論理削除ファイルに権限設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param con コネクション
     * @throws NumberFormatException 実行例外
     * @throws SQLException 実行例外
     */
    private void __insertDeleteFileDaccess(int dirSid, Connection con)
            throws NumberFormatException, SQLException {

        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con);
        if (daConfDao.getCount(dirSid) > 0) {
            return;
        }

        FilCommonBiz filBiz = new FilCommonBiz(con, reqMdl__);
        int parentSid = filBiz.getParentDirSid(dirSid, con);

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
     * <br>[機  能] ディレクトリ種別を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Fil040ParamModel
     * @param con コネクション
     * @return ディレクトリ種別
     * @throws SQLException SQL実行時例外
     */
    public List<Integer> getFdrKbn(Fil040ParamModel paramMdl, Connection con)
    throws SQLException {
        String[] fdrSids = paramMdl.getFil040SelectDel();
        FileDirectoryDao dirDao = new FileDirectoryDao(con);
        return dirDao.getFdrKbn(fdrSids);
    }
    /**
     * <br>[機  能] ファイルのロックの解除を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param usrSid ユーザーSID
     * @param paramMdl Fil040ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void unLock(int usrSid, Fil040ParamModel paramMdl, Connection con)
    throws SQLException {
        FilCommonBiz cmnBiz = new FilCommonBiz(con, reqMdl__);
        cmnBiz.updateLockKbnCommit(
                NullDefault.getInt(paramMdl.getFil040SelectUnlock(), -1),
                NullDefault.getInt(paramMdl.getFil040SelectUnlockVer(), -1),
                GSConstFile.LOCK_KBN_OFF,
                usrSid,
                con);

    }

    /**
     * <br>[機  能] ファイルのロックの一括解除を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param usrSid ユーザーSID
     * @param paramMdl Fil040ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void unlockPlural(int usrSid, Fil040ParamModel paramMdl, Connection con)
    throws SQLException {

        String[] fil040Select = paramMdl.getFil040SelectDel();

        FilCommonBiz cmnBiz = new FilCommonBiz(con, reqMdl__);
        cmnBiz.updateLockKbnCommit(
                fil040Select,
                GSConstFile.LOCK_KBN_OFF,
                usrSid,
                con);
    }

    /**
     * <br>[機  能] ファイルのロックの一括解除を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param usrSid ユーザーSID
     * @param paramMdl Fil040ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void lockPlural(int usrSid, Fil040ParamModel paramMdl, Connection con)
    throws SQLException {

        String[] fil040Select = paramMdl.getFil040SelectDel();

        FilCommonBiz cmnBiz = new FilCommonBiz(con, reqMdl__);
        cmnBiz.updateLockKbnCommit(
                fil040Select,
                GSConstFile.LOCK_KBN_ON,
                usrSid,
                con);

    }

    /**
     * <br>[機  能] キャビネットコンボを設定する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param buMdl ユーザーモデル
     * @param paramMdl Fil040ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setCabinetList(BaseUserModel buMdl, Fil040ParamModel paramMdl, Connection con)
    throws SQLException {

        Fil010Biz fil010Biz = new Fil010Biz(reqMdl__);
        ArrayList<FileCabinetDspModel> cabinetList
                = fil010Biz.getAccessCabinetList(buMdl, con, false);
        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        if (cabinetList != null && cabinetList.size() > 0) {
            for (FileCabinetDspModel model : cabinetList) {
                labelList.add(new LabelValueBean(model.getFcbName(),
                        String.valueOf(model.getFcbSid())));
            }
        }
        paramMdl.setFil040CabinetList(labelList);
        paramMdl.setFil040SelectCabinet(paramMdl.getFil010SelectCabinet());
    }

    /**
     * <br>[機  能] キャビネット変更時設定。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Fil040ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setChangeCabinet(Fil040ParamModel paramMdl, Connection con)
    throws SQLException {

        //ルートディレクトリを取得する。
        FileDirectoryDao dirDao = new FileDirectoryDao(con);
        FileDirectoryModel dirModel
            = dirDao.getRootDirectory(NullDefault.getInt(paramMdl.getFil040SelectCabinet(), -1));
        if (dirModel != null) {
            paramMdl.setFil010SelectDirSid(String.valueOf(dirModel.getFdrSid()));
        }

        paramMdl.setFil010SelectCabinet(paramMdl.getFil040SelectCabinet());
        paramMdl.setFil040SortKey(GSConstFile.SORT_NAME);
        paramMdl.setFil040OrderKey(GSConst.ORDER_KEY_ASC);
    }
}
