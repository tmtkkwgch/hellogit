package jp.groupsession.v2.fil.fil060kn;

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
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileCallConfDao;
import jp.groupsession.v2.fil.dao.FileDAccessConfDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.model.FileAccessConfModel;
import jp.groupsession.v2.fil.model.FileCallConfModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] フォルダ登録確認画面のBIZクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil060knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil060knBiz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil060knBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil060knParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param buMdl セッションユーザモデル
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void setInitData(
        Fil060knParamModel paramMdl,
        String tempDir,
        BaseUserModel buMdl) throws SQLException, IOToolsException {

        log__.debug("Fil060Biz Start");

        //個人設定が無い場合は初期値で登録する。
        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        filBiz.getUserConf(buMdl.getUsrsid(), con__);

        //添付ファイルのラベルを設定する。
        CommonBiz commonBiz = new CommonBiz();
        paramMdl.setFil060FileLabelList(commonBiz.getTempFileLabelList(tempDir));

        //備考をHTML表示用に変換
        paramMdl.setFil060knBiko(StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getFil060Biko()));

        //更新者を取得
        String editId = NullDefault.getString(
                paramMdl.getFil060EditId(), String.valueOf(buMdl.getUsrsid()));
        if (isEditGroup(editId)) {
            //グループ情報取得
            GroupDao gpDao = new GroupDao(con__);
            if (ValidateUtil.isNumber(editId.substring("G".length()))) {
                CmnGroupmModel gpMdl = new CmnGroupmModel();
                gpMdl = gpDao.getGroup(Integer.valueOf(editId.substring("G".length())));
                paramMdl.setFil060knEditName(gpMdl.getGrpName());
            }
        } else {
            paramMdl.setFil060knEditName(buMdl.getUsiseimei());
        }

        //アクセス権限 フルアクセス一覧
        paramMdl.setFil060AcFullLavel(__getAccessFullLavle(paramMdl));
        //アクセス権限 閲覧アクセス一覧
        paramMdl.setFil060AcReadLavel(__getAccessReadLavle(paramMdl));
    }

    /**
     * <br>[機  能] フォルダを登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil060knParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param buMdl セッションユーザモデル
     * @param cntCon MlCountMtController
     * @param appRootPath アプリケーションルートパス
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void registerData(
        Fil060knParamModel paramMdl,
        String tempDir,
        BaseUserModel buMdl,
        MlCountMtController cntCon,
        String appRootPath) throws SQLException, IOToolsException, TempFileException {

//        FileDirectoryBinDao dirBinDao = new FileDirectoryBinDao(con__);
        int dirSid = -1;
        int usrSid = buMdl.getUsrsid();
//        UDate now = new UDate();

        if (paramMdl.getFil060CmdMode() == 0) {
            //ディレクトリSIDを採番する。
            dirSid = (int) cntCon.getSaibanNumber(GSConstFile.PLUGIN_ID_FILE,
                            GSConstFile.SBNSID_SUB_DIRECTORY,
                            usrSid);
            //新規登録
            __insertDirectory(paramMdl, dirSid, usrSid);

            //親ディレクトリが更新通知を設定している場合、更新通知を設定する。
            insertCallConf(paramMdl, dirSid);

        } else {

            //ディレクトリSID
            dirSid = NullDefault.getInt(paramMdl.getFil050DirSid(), -1);

            //ディレクトリ情報を更新
            __updateDirectory(paramMdl, dirSid, usrSid);

        }

        //ディレクトリアクセス設定
        __insertDaccessConf(dirSid, paramMdl);
        FileDirectoryDao fdDao = new FileDirectoryDao(con__);
        fdDao.updateAccessSid(dirSid);

//        if (paramMdl.getFil060CmdMode() == GSConstFile.MODE_EDIT) {
//            //ディレクトリ添付情報の削除
//            dirBinDao.delete(dirSid);
//        }
//
//        //テンポラリディレクトリパスにある添付ファイルを全て登録
//        CommonBiz biz = new CommonBiz();
//        List<String> binList = biz.insertBinInfoForFileKanri(
//                con__, tempDir, appRootPath, cntCon, usrSid, now);
//
//        //ディレクトリ添付情報の登録
//        __insertDirBinInf(dirSid, binList);

    }

    /**
     * <br>[機  能] ディレクトリ情報を登録する。（編集時）
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil060knParamModel
     * @param dirSid ディレクトリSID
     * @param usrSid ユーザSID
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void __updateDirectory(
        Fil060knParamModel paramMdl,
        int dirSid,
        int usrSid) throws SQLException, IOToolsException {

        FilCommonBiz filBiz = new FilCommonBiz();
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDirectoryModel newDirModel = new FileDirectoryModel();
        UDate now = new UDate();

        //最新バージョン情報取得
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);
        if (dirModel == null) {
            return;
        }

        newDirModel.setFdrSid(dirSid);
        newDirModel.setFdrBiko(paramMdl.getFil060Biko());
        newDirModel.setFdrKbn(GSConstFile.DIRECTORY_FOLDER);
        newDirModel.setFdrLevel(dirModel.getFdrLevel());
//        newDirModel.setFdrLevel(dirModel.getFdrLevel() + 1);
        newDirModel.setFdrName(paramMdl.getFil060DirName());
        newDirModel.setFdrEdate(now);
        newDirModel.setFdrEuid(usrSid);
        newDirModel.setFdrEgid(filBiz.getGroupSid(paramMdl.getFil060EditId()));

        dirDao.updateFolder(newDirModel);
    }

    /**
     * <br>[機  能] ディレクトリ情報を登録する。（追加時）
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil060knParamModel
     * @param dirSid ディレクトリSID
     * @param usrSid ユーザSID
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void __insertDirectory(Fil060knParamModel paramMdl, int dirSid, int usrSid)
    throws SQLException, IOToolsException {

        FilCommonBiz filBiz = new FilCommonBiz();
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDirectoryModel newDirModel = new FileDirectoryModel();
        UDate now = new UDate();
        int parDirSid = NullDefault.getInt(paramMdl.getFil050ParentDirSid(), 0);

        //親ディレクトリ情報取得
        FileDirectoryModel dirModel = dirDao.select(parDirSid, -1);
        if (dirModel == null) {
            return;
        }
        newDirModel.setFdrSid(dirSid);
        newDirModel.setFcbSid(dirModel.getFcbSid());
        newDirModel.setFdrLevel(dirModel.getFdrLevel() + 1);
        newDirModel.setFdrParentSid(parDirSid);
        newDirModel.setFdrBiko(paramMdl.getFil060Biko());
        newDirModel.setFdrName(paramMdl.getFil060DirName());
        newDirModel.setFdrJtkbn(GSConstFile.JTKBN_NORMAL);
        newDirModel.setFdrKbn(GSConstFile.DIRECTORY_FOLDER);
        newDirModel.setFdrVerKbn(GSConstFile.VERSION_KBN_OFF);
        newDirModel.setFdrVersion(GSConstFile.VERSION_KBN_OFF);
        newDirModel.setFdrAuid(usrSid);
        newDirModel.setFdrAdate(now);
        newDirModel.setFdrEuid(usrSid);
        newDirModel.setFdrEgid(filBiz.getGroupSid(paramMdl.getFil060EditId()));
        newDirModel.setFdrEdate(now);
        dirDao.insert(newDirModel);
    }

//    /**
//     * <br>[機  能] ディレクトリ添付情報を登録する。
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param dirSid ディレクトリSID
//     * @param binList バイナリＳＩＤリスト
//     * @throws SQLException SQL実行例外
//     * @throws IOToolsException ファイルアクセス時例外
//     */
//    public void __insertDirBinInf(int dirSid, List<String> binList)
//    throws SQLException, IOToolsException {
//
//        if (binList == null || binList.size() < 1) {
//            return;
//        }
//
//        FileDirectoryBinModel newDirBinModel = new FileDirectoryBinModel();
//        FileDirectoryBinDao dirBinDao = new FileDirectoryBinDao(con__);
//
//        newDirBinModel.setFdrSid(dirSid);
//        newDirBinModel.setFdrVersion(0);
//
//        //添付情報の登録
//        for (String binSid : binList) {
//            newDirBinModel.setBinSid(Long.parseLong(binSid));
//            dirBinDao.insert(newDirBinModel);
//        }
//    }

    /**
     * <br>[機  能] 親ディレクトリに更新通知が設定されている場合、登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil060knParamModel
     * @param dirSid ディレクトリSID
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public void insertCallConf(Fil060knParamModel paramMdl, int dirSid)
    throws SQLException, IOToolsException {

        int parDirSid = NullDefault.getInt(paramMdl.getFil050ParentDirSid(), 0);

        FileCallConfDao confDao = new FileCallConfDao(con__);

        //親ディレクトリに更新通知設定しているユーザ取得
        ArrayList<FileCallConfModel> confModelList = confDao.select(parDirSid);

        FileCallConfModel confModel = null;

        if (confModelList != null && confModelList.size() > 0) {
            //更新通知設定を登録
            for (FileCallConfModel model : confModelList) {
                confModel = new FileCallConfModel();
                confModel.setFdrSid(dirSid);
                confModel.setUsrSid(model.getUsrSid());
                confDao.insert(confModel);
            }
        }

    }

    /**
     * 更新者がユーザかグループかを判定する
     * <br>[機  能]先頭文字に"G"が有る場合はグループ
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return boolean true:マイグループ false=通常のグループ
     */
    public static boolean isEditGroup(String gpSid) {
        boolean ret = false;
        if (gpSid == null) {
            return ret;
        }
        // 置換対象文字列が存在する場所を取得
        int index = gpSid.indexOf("G");

        // 先頭文字に"G"が有る場合はグループ
        if (index == 0) {
            return true;
        } else {
            return ret;
        }
    }

    /**
     * アクセス権限のフルアクセス用一覧を取得する
     * @param paramMdl Fil060knParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessFullLavle(
            Fil060knParamModel paramMdl)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getFil060SvAcFull();
        return __getAccessLavle(leftFull);
    }

    /**
     * アクセス権限のフルアクセス用一覧を取得する
     * @param paramMdl Fil060knParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessReadLavle(
            Fil060knParamModel paramMdl)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftRead = paramMdl.getFil060SvAcRead();
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
    private void __insertDaccessConf(int dirSid, Fil060knParamModel paramMdl) throws SQLException {

        //現在の権限を削除
        FileDAccessConfDao dao = new FileDAccessConfDao(con__);
        dao.delete(dirSid);

        if (paramMdl.getFile060AdaptIncFile().equals(GSConstFile.ADAPT_FILE_INC_KBN_ON)) {
            //下位フォルダに適用
            dao.deleteSubDirectoriesFiles(dirSid, false);
        }

        if (paramMdl.getFil060AccessKbn().equals(String.valueOf(GSConstFile.ACCESS_KBN_OFF))) {
            //個別設定しない
            return;
        }

        //フルアクセス
        ArrayList<Integer> groupFullList = new ArrayList<Integer>();
        ArrayList<Integer> userFullList = new ArrayList<Integer>();
        String[] full = paramMdl.getFil060SvAcFull();
        if (full != null) {
            for (int i = 0; i < full.length; i++) {
                String str = NullDefault.getString(full[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    groupFullList.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    userFullList.add(new Integer(str));
                }
            }
        }
        //フルアクセスグループ登録
        dao.insert(dirSid, groupFullList,
                GSConstFile.USER_KBN_GROUP, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
        //フルアクセスユーザ登録
        dao.insert(dirSid, userFullList,
                GSConstFile.USER_KBN_USER, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));

        //閲覧アクセス
        ArrayList<Integer> groupEditList = new ArrayList<Integer>();
        ArrayList<Integer> userEditList = new ArrayList<Integer>();
        String[] read = paramMdl.getFil060SvAcRead();
        if (read != null) {
            for (int i = 0; i < read.length; i++) {
                String str = NullDefault.getString(read[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    groupEditList.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    userEditList.add(new Integer(str));
                }
            }
        }
        //閲覧アクセスグループ登録
        dao.insert(dirSid, groupEditList,
                GSConstFile.USER_KBN_GROUP, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
        //閲覧アクセスユーザ登録
        dao.insert(dirSid, userEditList,
                GSConstFile.USER_KBN_USER, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));

        //下位フォルダに適用しない場合は下位権限の論理チェックを行う
        if (paramMdl.getFile060AdaptIncFile().equals(GSConstFile.ADAPT_FILE_INC_KBN_OFF)) {
            List<FileAccessConfModel> subList = dao.getAccessSubDirectoriesFiles(dirSid);
            if (subList == null || subList.size() <= 0) {
                return;
            }
            groupEditList.addAll(groupFullList);
            userEditList.addAll(userFullList);
            List<Integer> delSids = new ArrayList<Integer>();
            for (FileAccessConfModel confMdl : subList) {
                //親ディレクトリの権限に含まれないグループ・ユーザを削除
                boolean delFlg = false;
                int subSid = confMdl.getFcbSid();
                if (confMdl.getFaaAuth() == Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE)) {
                    //追加・変更・削除
                    if (confMdl.getUsrKbn() == GSConstFile.USER_KBN_GROUP) {
                        //グループ
                        delFlg = __deleteNotInc(dao,
                                                groupEditList,
                                                subSid,
                                                confMdl.getUsrSid(),
                                                GSConstFile.USER_KBN_GROUP);
                    } else if (confMdl.getUsrKbn() == GSConstFile.USER_KBN_USER) {
                        //ユーザ
                        delFlg = __deleteNotInc(dao,
                                                userEditList,
                                                subSid,
                                                confMdl.getUsrSid(),
                                                GSConstFile.USER_KBN_USER);
                    }

                } else if (confMdl.getFaaAuth() == Integer.parseInt(GSConstFile.ACCESS_KBN_READ)) {
                    //閲覧
                    if (confMdl.getUsrKbn() == GSConstFile.USER_KBN_GROUP) {
                        //グループ
                        delFlg = __deleteNotInc(dao,
                                                groupEditList,
                                                subSid,
                                                confMdl.getUsrSid(),
                                                GSConstFile.USER_KBN_GROUP);
                    } else if (confMdl.getUsrKbn() == GSConstFile.USER_KBN_USER) {
                        //ユーザ
                        delFlg = __deleteNotInc(dao,
                                                userEditList,
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
                int count = dao.getCount(subSid);
                if (count <= 0) {
                    //アクセス権限が一切なくなったフォルダ、ファイルを管理者のみの閲覧にする
                    dao.deleteSubDirectoriesFiles(subSid, false);
                    FileAccessConfModel daConfMdl = new FileAccessConfModel();
                    daConfMdl.setFcbSid(subSid);
                    daConfMdl.setUsrSid(0);
                    daConfMdl.setUsrKbn(GSConstFile.USER_KBN_USER);
                    daConfMdl.setFaaAuth(Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
                    dao.insert(daConfMdl);
                }
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
}