package jp.groupsession.v2.fil.fil060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnBinfDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileCallConfDao;
import jp.groupsession.v2.fil.dao.FileCallDataDao;
import jp.groupsession.v2.fil.dao.FileDAccessConfDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.dao.FileFileRekiDao;
import jp.groupsession.v2.fil.dao.FileFileTextDao;
import jp.groupsession.v2.fil.dao.FileShortcutConfDao;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileParentAccessDspModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] フォルダ登録画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil060Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil060Biz.class);

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <p>Set Connection
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Fil060Biz(Connection con, RequestModel reqMdl) {
        reqMdl__ = reqMdl;
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil060ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションのルートパス
     * @param buMdl セッションユーザモデル
     * @throws Exception 実行時例外
     */
    public void setInitData(
        Fil060ParamModel paramMdl,
        String tempDir,
        String appRoot,
        BaseUserModel buMdl)
        throws Exception {

        log__.debug("Fil060Biz Start");

        //画面モードを設定
        int dirSid = NullDefault.getInt(paramMdl.getFil050DirSid(), -1);
        int mode = GSConstFile.MODE_ADD;
        if (dirSid != -1) {
            mode = GSConstFile.MODE_EDIT;
        }
        paramMdl.setFil060CmdMode(mode);

        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);
        if (paramMdl.getFil060PluginId() == null) {
            //初期表示

            //テンポラリディレクトリを初期化
            IOTools.deleteDir(tempDir);

            if (mode == 1) {
                //編集モードの場合DBより各項目を設定する。
                __setData(paramMdl, tempDir, appRoot, buMdl);
            }

            //プラグインIDを設定
            paramMdl.setFil060PluginId(GSConstFile.PLUGIN_ID_FILE);

            //個人設定が無い場合は初期値で登録する。
            filBiz.getUserConf(buMdl.getUsrsid(), con__);

            paramMdl.setFile060AdaptIncFile(GSConstFile.ADAPT_FILE_INC_KBN_OFF);

        }

//        //添付ファイルのラベルを設定する。
//        CommonBiz commonBiz = new CommonBiz();
//        paramMdl.setFil060FileLabelList(commonBiz.getTempFileLabelList(tempDir));

        List<LabelValueBean> lvList = new ArrayList<LabelValueBean>();

        //更新者設定
        paramMdl.setFil060EditId(NullDefault.getString(
                paramMdl.getFil060EditId(), String.valueOf(buMdl.getUsrsid())));

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
        paramMdl.setFil060groupList(lvList);

        //親ディレクトリSIDを取得する
        int parentSid = NullDefault.getInt(paramMdl.getFil050ParentDirSid(), -1);
        if (parentSid < 1) {
            parentSid = filBiz.getParentDirSid(dirSid, con__);
        }
        paramMdl.setFil050ParentDirSid(String.valueOf(parentSid));

        if (__isParentZeroUser(parentSid)) {
            //親がゼロユーザ
            paramMdl.setFil060ParentZeroUser("1");
            return;
        } else {
            paramMdl.setFil060ParentZeroUser("0");
        }

        //親ディレクトリ（追加・変更・削除）アクセス制限リスト
        paramMdl.setFil060ParentEditList(__getParentAccessUser(
                paramMdl, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE)));
        //親ディレクトリ（閲覧）アクセス制限リスト
        paramMdl.setFil060ParentReadList(__getParentAccessUser(
                paramMdl, Integer.parseInt(GSConstFile.ACCESS_KBN_READ)));
        if (paramMdl.getFil060ParentEditList().size()
                > Integer.parseInt(GSConstFile.MAXCOUNT_PARENT_ACCESS)
         || paramMdl.getFil060ParentReadList().size()
                > Integer.parseInt(GSConstFile.MAXCOUNT_PARENT_ACCESS)) {
            paramMdl.setFil060ParentAccessAllDspFlg(1);
        }

        //アクセス権限 追加・変更・削除 グループコンボ
        paramMdl.setFil060AcEditGroupLavel(__getAccessGroupLavle(
                true, parentSid, Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE)));
        //アクセス権限 追加・変更・削除 候補一覧
        paramMdl.setFil060AcEditRightLavel(__getAccessEditRightLavle(paramMdl));
        //アクセス権限 フルアクセス一覧
        paramMdl.setFil060AcFullLavel(__getAccessFullLavle(paramMdl));

        //アクセス権限 閲覧 グループコンボ
        paramMdl.setFil060AcReadGroupLavel(__getAccessGroupLavle(
                true, parentSid, Integer.parseInt(GSConstFile.ACCESS_KBN_READ)));
        //アクセス権限 閲覧 候補一覧
        paramMdl.setFil060AcReadRightLavel(__getAccessReadRightLavle(paramMdl));
        //アクセス権限 閲覧アクセス一覧
        paramMdl.setFil060AcReadLavel(__getAccessReadLavle(paramMdl));
    }

    /**
     * <br>[機  能] ディレクトリ情報DBより取得しを設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil060ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションのルートパス
     * @param buMdl BaseUserModel
     * @throws Exception 実行時例外
     */
    private void __setData(Fil060ParamModel paramMdl, String tempDir,
                                         String appRoot, BaseUserModel buMdl)
    throws Exception {

        FilCommonBiz filBiz = new FilCommonBiz();

        int dirSid = NullDefault.getInt(paramMdl.getFil050DirSid(), -1);
//        FilCommonBiz filBiz = new FilCommonBiz(con__, reqMdl__);

        //ディレクトリ情報
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);

        if (dirModel == null) {
            return;
        }
        paramMdl.setFil060DirName(dirModel.getFdrName());
        paramMdl.setFil060Biko(dirModel.getFdrBiko());
        paramMdl.setFil060EditId(filBiz.setUpdateSid(buMdl.getUsrsid(),
                                                     dirModel.getFdrEgid(),
                                                     con__));

//        //添付ファイルをテンポラリディレクトリへ設定する
//        filBiz.setFolderTempFile(appRoot, tempDir, dirSid, dirModel.getFdrVersion(), con__);

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
            paramMdl.setFil060SvAcFull(leftFull);
            //閲覧
            String[] leftRead = daConfDao.getAccessUser(
                    dirSid, Integer.parseInt(GSConstFile.ACCESS_KBN_READ));
            paramMdl.setFil060SvAcRead(leftRead);
            paramMdl.setFil060AccessKbn(String.valueOf(GSConstFile.ACCESS_KBN_ON));
        } else {
            paramMdl.setFil060AccessKbn(String.valueOf(GSConstFile.ACCESS_KBN_OFF));
        }
    }

    /**
     * <br>[機  能] ディレクトリを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Fil060ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param usrSid ユーザSID
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException IOエラー
     */
    public void deleteDirectory(
            Fil060ParamModel paramMdl, String tempDir, int usrSid)
    throws SQLException, IOToolsException {

        CmnBinfDao cbDao = new CmnBinfDao(con__);
        CmnBinfModel cmnBinModel = new CmnBinfModel();
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
//        FileDirectoryBinDao dirBinDao = new FileDirectoryBinDao(con__);
        FileCallConfDao callConfDao = new FileCallConfDao(con__);
        FileCallDataDao callDataDao = new FileCallDataDao(con__);
        FileShortcutConfDao shortcutDao = new FileShortcutConfDao(con__);
        FileFileBinDao ffBinDao = new FileFileBinDao(con__);
        FileFileRekiDao ffRekiDao = new FileFileRekiDao(con__);
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);

        UDate now = new UDate();
        int dirSid = NullDefault.getInt(paramMdl.getFil050DirSid(), -1);

        //ディレクトリアクセス制限
        daConfDao.deleteSubDirectoriesFiles(dirSid, false);
        //ディレクトリ情報
        dirDao.delete(dirSid, GSConstFile.FOLDER_VERSION);

//        //添付ファイルリストを取得
//        List<FileDirectoryBinModel> binList
//        = dirBinDao.select(dirSid, GSConstFile.FOLDER_VERSION);
//        if (binList != null && binList.size() > 0) {
//            List<Long> binSidList = new ArrayList<Long>();
//            for (FileDirectoryBinModel binModel : binList) {
//                binSidList.add(binModel.getBinSid());
//            }
//            cmnBinModel.setBinJkbn(GSConst.JTKBN_DELETE);
//            cmnBinModel.setBinUpuser(usrSid);
//            cmnBinModel.setBinUpdate(now);
//
//            //バイナリー情報を論理削除する
//            cbDao.updateJKbn(cmnBinModel, binSidList);
//        }
//        //ディレクトリ添付情報
//        dirBinDao.delete(dirSid);
        //更新通知設定
        callConfDao.delete(dirSid, usrSid);
        //更新確認情報
        callDataDao.delete(dirSid, usrSid);
        //ショートカット情報
        shortcutDao.delete(dirSid, usrSid);

        /* 以下ディレクトリSIDを親に持つレコードを削除 ----- */

        //削除するディレクトリSIDを取得
        List<FileDirectoryModel> fileDirList = dirDao.getLowDirectory(dirSid, -1);
        //バイナリーを削除
        ArrayList<String> strBinSidList = ffBinDao.getBinSids(fileDirList);
        if (strBinSidList != null && strBinSidList.size() > 0) {
            List<Long> binSidList = new ArrayList<Long>();
            for (String binSid : strBinSidList) {
                binSidList.add(Long.parseLong(binSid));
            }
            cmnBinModel.setBinJkbn(GSConst.JTKBN_DELETE);
            cmnBinModel.setBinUpuser(usrSid);
            cmnBinModel.setBinUpdate(now);

            //バイナリー情報を論理削除する
            cbDao.updateJKbn(cmnBinModel, binSidList);
        }
        //ディレクトリ情報削除
        dirDao.deleteDir((ArrayList<FileDirectoryModel>) fileDirList);
        //ファイル情報削除
        ffBinDao.deleteDir((ArrayList<FileDirectoryModel>) fileDirList);
        //更新通知設定を削除
        callConfDao.deleteDir((ArrayList<FileDirectoryModel>) fileDirList);
        //更新確認情報を削除
        callDataDao.deleteDir((ArrayList<FileDirectoryModel>) fileDirList);
        //ショートカット情報を削除
        shortcutDao.deleteDir((ArrayList<FileDirectoryModel>) fileDirList);
        //ファイル履歴を削除
        ffRekiDao.deleteDir((ArrayList<FileDirectoryModel>) fileDirList);

        //ファイルテキスト情報を削除
        FileFileTextDao textDao = new FileFileTextDao(con__);
        textDao.deleteDir((ArrayList<FileDirectoryModel>) fileDirList);
    }

    /**
     * 親ディレクトリのアクセス制限ユーザリストを取得する
     * @param paramMdl パラメータモデル
     * @param auth 権限区分
     * @return アクセス制限ユーザリスト
     * @throws SQLException 実行例外
     */
    private ArrayList<FileParentAccessDspModel> __getParentAccessUser(
            Fil060ParamModel paramMdl,
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
        int fdrSid = NullDefault.getInt(paramMdl.getFil050ParentDirSid(), -1);
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
     * @param paramMdl Fil060ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessEditRightLavle(Fil060ParamModel paramMdl)
            throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        List<String> parentSids = new ArrayList<String>();

        //アクセス権限 グループコンボ選択値
        int acSltGp = NullDefault.getInt(
                paramMdl.getFil060AcEditSltGroup(),
                Integer.parseInt(GSConstFile.GROUP_COMBO_VALUE));
        //除外するグループSID
        String[] leftFull = paramMdl.getFil060SvAcFull();
        String[] leftRead = paramMdl.getFil060SvAcRead();

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
     * @param paramMdl Fil060ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessReadRightLavle(
            Fil060ParamModel paramMdl)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        List<String> parentSids = new ArrayList<String>();

        //アクセス権限 グループコンボ選択値
        int acSltGp = NullDefault.getInt(
                paramMdl.getFil060AcReadSltGroup(),
                Integer.parseInt(GSConstFile.GROUP_COMBO_VALUE));
        //除外するグループSID
        String[] leftRead = paramMdl.getFil060SvAcRead();
        String[] leftFull = paramMdl.getFil060SvAcFull();

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
    private List<String> __getParentAccessSids(Fil060ParamModel paramMdl,
            int usrKbn, int auth, int acSltGp) throws NumberFormatException, SQLException {

        List<String> sids = new ArrayList<String>();
        String[] parentSids = null;
        int fdrSid = NullDefault.getInt(paramMdl.getFil050ParentDirSid(), -1);

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
     * @param paramMdl Fil060ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessFullLavle(
            Fil060ParamModel paramMdl)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getFil060SvAcFull();
        return __getAccessLavle(leftFull);
    }

    /**
     * アクセス権限のフルアクセス用一覧を取得する
     * @param paramMdl Fil060ParamModel
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAccessReadLavle(
            Fil060ParamModel paramMdl)
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