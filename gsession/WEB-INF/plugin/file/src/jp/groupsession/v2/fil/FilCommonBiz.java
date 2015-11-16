package jp.groupsession.v2.fil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.LoggingBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnFileConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnFileConfModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnLogModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.fil.dao.FileAccessConfDao;
import jp.groupsession.v2.fil.dao.FileAconfDao;
import jp.groupsession.v2.fil.dao.FileCabinetAdminDao;
import jp.groupsession.v2.fil.dao.FileCabinetBinDao;
import jp.groupsession.v2.fil.dao.FileCabinetDao;
import jp.groupsession.v2.fil.dao.FileCallConfDao;
import jp.groupsession.v2.fil.dao.FileCallDataDao;
import jp.groupsession.v2.fil.dao.FileCrtConfDao;
import jp.groupsession.v2.fil.dao.FileDAccessConfDao;
import jp.groupsession.v2.fil.dao.FileDao;
import jp.groupsession.v2.fil.dao.FileDirectoryBinDao;
import jp.groupsession.v2.fil.dao.FileDirectoryDao;
import jp.groupsession.v2.fil.dao.FileDownloadLogDao;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.dao.FileFileRekiDao;
import jp.groupsession.v2.fil.dao.FileLogCountSumDao;
import jp.groupsession.v2.fil.dao.FileUconfDao;
import jp.groupsession.v2.fil.dao.FileUploadLogDao;
import jp.groupsession.v2.fil.model.FileAconfModel;
import jp.groupsession.v2.fil.model.FileCabinetAdminModel;
import jp.groupsession.v2.fil.model.FileCabinetModel;
import jp.groupsession.v2.fil.model.FileCallConfModel;
import jp.groupsession.v2.fil.model.FileCallDataModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileDownloadLogModel;
import jp.groupsession.v2.fil.model.FileFileBinModel;
import jp.groupsession.v2.fil.model.FileFileRekiModel;
import jp.groupsession.v2.fil.model.FileLogCountSumModel;
import jp.groupsession.v2.fil.model.FileUconfModel;
import jp.groupsession.v2.fil.model.FileUploadLogModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.SmlSender;
import jp.groupsession.v2.sml.model.SmlSenderModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] ファイル管理の共通ビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class FilCommonBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(FilCommonBiz.class);
    /** DBコネクション */
    private Connection con__ = null;
    /** リクエストモデル */
    private RequestModel reqMdl__ = null;

    /** アクセスエラーコード エラー無し*/
    public static final int ERR_NONE = 0;
    /** アクセスエラーコード 対象がない*/
    public static final int ERR_NOT_EXIST = 1;
    /** アクセスエラーコード キャビネット閲覧権限がない*/
    public static final int ERR_NONE_CAB_VIEW_POWER = 2;
    /** アクセスエラーコード キャビネット編集権限がない*/
    public static final int ERR_NONE_CAB_EDIT_POWER = 3;
    /** アクセスエラーコード ディレクトリ区分が一致しない*/
    public static final int ERR_NOT_DIRKBN = 4;

    /**
     * <p>コンストラクタ
     */
    public FilCommonBiz() {
    }
    /**
     * <p>コンストラクタ
     * @param con Connection
     */
    public FilCommonBiz(Connection con) {
        con__ = con;
    }

    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl リクエストモデル
     */
    public FilCommonBiz(Connection con, RequestModel reqMdl) {
        super();
        con__ = con;
        reqMdl__ = reqMdl;
    }
//    /**
//     * <br>[機  能] リクエストよりコマンドパラメータを取得する
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param req リクエスト
//     * @return CMDパラメータ
//     */
//    public static String getCmdProperty(HttpServletRequest req) {
//        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
//        cmd = cmd.trim();
//        log__.debug("--- cmd :" + cmd);
//        return cmd;
//    }

    /**
     * <br>[機  能] 選択用グループコンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param allGroupCombo ユーザコンボ
     * @param selectGroupSid 選択済みユーザSID
     * @return 選択用グループコンボ index=0:選択済 index=1:未選択
     * @throws SQLException SQL実行時例外
     */
    public List<List<LabelValueBean>> getGroupCombo(Connection con,
                                                    List<LabelValueBean> allGroupCombo,
                                                    String[] selectGroupSid)
    throws SQLException {

        List<String> selectGroupList = new ArrayList<String>();
        if (selectGroupSid != null) {
            selectGroupList = Arrays.asList(selectGroupSid);
        }

        //選択済みグループ、未選択グループのコンボ情報を作成する
        List<LabelValueBean> selectGroupCombo = new ArrayList<LabelValueBean>();
        List<LabelValueBean> noSelectGroupCombo = new ArrayList<LabelValueBean>();
        for (LabelValueBean groupMdl : allGroupCombo) {
            String grpSid = groupMdl.getValue();
            if (selectGroupList.contains(grpSid)) {
                selectGroupCombo.add(groupMdl);
            } else {
                noSelectGroupCombo.add(groupMdl);
            }
        }

        List<List<LabelValueBean>> groupComboList = new ArrayList<List<LabelValueBean>>();
        groupComboList.add(selectGroupCombo);
        groupComboList.add(noSelectGroupCombo);

        return groupComboList;
    }

    /**
     * <br>[機  能] 選択用ユーザコンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param allUserCombo ユーザコンボ
     * @param grpSid グループSID
     * @param selectUserSid 選択済みユーザSID
     * @return 選択用ユーザコンボ index=0:選択済 index=1:未選択
     * @throws SQLException SQL実行時例外
     */
    public List<List<LabelValueBean>> getUserCombo(Connection con,
                                                    int grpSid,
                                                    List<LabelValueBean> allUserCombo,
                                                    String[] selectUserSid)
    throws SQLException {


        List<String> selectUserList = new ArrayList<String>();
        if (selectUserSid != null) {
            selectUserList = Arrays.asList(selectUserSid);
        }

        //未選択ユーザのコンボ情報を作成する
        List<LabelValueBean> noSelectUserCombo = new ArrayList<LabelValueBean>();
        List<LabelValueBean> selectUserCombo = new ArrayList<LabelValueBean>();

        CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
        CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();

        FileDao fileDao = new FileDao(con);
        List<LabelValueBean> userCombo
                            = fileDao.getUserListBelongGroup(grpSid, sortMdl);
        for (LabelValueBean userMdl : userCombo) {
            String userSid = userMdl.getValue();
            if (!selectUserList.contains(userSid)) {
                noSelectUserCombo.add(userMdl);
            }
        }

        //選択済みユーザのコンボ情報を設定する
        if (allUserCombo != null) {
            for (LabelValueBean userMdl : allUserCombo) {
                String userSid = userMdl.getValue();
                if (selectUserList.contains(userSid)) {
                    selectUserCombo.add(userMdl);
                }
            }
        }

        List<List<LabelValueBean>> userComboList = new ArrayList<List<LabelValueBean>>();
        userComboList.add(selectUserCombo);
        userComboList.add(noSelectUserCombo);
        return userComboList;
    }

    /**
     * <br>[機  能] キャビネットコンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param notSelectFlg true:選択してくださいを追加する。
     * @return List in LabelValueBean
     * @throws SQLException SQL実行時例外
     */

    public List<LabelValueBean> getCabinetLabel(Connection con,
            boolean notSelectFlg)
    throws SQLException {

        //キャビネットリストを取得する。
        FileCabinetDao cabiDao = new FileCabinetDao(con);
        List<FileCabinetModel> cabiList = cabiDao.select();

        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        if (notSelectFlg) {
            GsMessage gsMsg = new GsMessage(reqMdl__);
            String textSelect = gsMsg.getMessage("cmn.select.plz");
            labelList.add(new LabelValueBean(textSelect, "-1"));
        }
        for (FileCabinetModel cabiMdl : cabiList) {
            labelList.add(new LabelValueBean(
                    cabiMdl.getFcbName(), String.valueOf(cabiMdl.getFcbSid())));
        }

        return labelList;
    }

    /**
     * <br>[機  能] ファイル・フォルダパスの文字列を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param directorySid ディレクトリSID
     * @param con コネクション
     * @return List in LabelValueBean
     * @throws SQLException SQL実行時例外
     */

    public String getDirctoryPath(int directorySid, Connection con)
    throws SQLException {

        String directoryPath = "";
        String cabinetName = "";
        String sep = "/";

        FileDirectoryDao dirDao = new FileDirectoryDao(con);
        FileDirectoryModel dirModel = null;

        int fdrSid = directorySid;

        for (int i = 1; i < 1000; i++) {

            //ディレクトリ情報を取得する。
            dirModel = dirDao.getNewDirectory(fdrSid);
            if (dirModel == null) {
                break;
            }

            if (dirModel.getFdrLevel() == GSConstFile.DIRECTORY_LEVEL_0) {

                //キャビネット名を取得する。
                cabinetName = getCabinetName(dirModel.getFcbSid(), con);

                break;
            } else if (i == 1)  {

                //ディレクトリパスに追加。（/を入れない）
                directoryPath = dirModel.getFdrName();
                fdrSid = dirModel.getFdrParentSid();
                if (dirModel.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
                    directoryPath += sep;
                }
            } else {
                //ディレクトリパスに追加。（/を入れる）
                directoryPath = dirModel.getFdrName() + sep + directoryPath;
                fdrSid = dirModel.getFdrParentSid();
            }
        }

        directoryPath = cabinetName + sep + directoryPath;

        return directoryPath;
    }

    /**
     * <br>[機  能] キャビネットSIDよりキャビネット名を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param cabinetSid キャビネットSID
     * @param con コネクション
     * @return List in LabelValueBean
     * @throws SQLException SQL実行時例外
     */

    public String getCabinetName(int cabinetSid, Connection con)
    throws SQLException {

        String cabinetName = null;
        FileCabinetDao cabiDao = new FileCabinetDao(con);

        FileCabinetModel cabiModel = cabiDao.select(cabinetSid);
        if (cabiModel != null) {
            cabinetName = cabiModel.getFcbName();
        }

        return cabinetName;
    }
    /**
     * <br>[機  能] Rootから指定ディレクトリSIDまでのディレクトリ情報一覧を取得する。
     * <br>[解  説] 並び順はRoot→カレント
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param con コネクション
     * @return List in LabelValueBean
     * @throws SQLException SQL実行時例外
     */

    public ArrayList<FileDirectoryModel> getRootToCurrentDirctoryList(int dirSid, Connection con)
    throws SQLException {

        ArrayList<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        FileDirectoryDao dirDao = new FileDirectoryDao(con);
        FileDirectoryModel dirModel = null;

        //カレントディレクトリ取得
        dirModel = dirDao.getNewDirectory(dirSid);
        if (dirModel != null) {
            ret.add(dirModel);
            while (dirModel.getFdrParentSid() > 0) {
                dirModel = dirDao.getNewDirectory(dirModel.getFdrParentSid());
                ret.add(0, dirModel);
            }
        }

        return ret;
    }
    /**
     * <br>[機  能] ディレクトリSIDよりキャビネットSIDを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param fdrSid ディレクトリSID
     * @param con コネクション
     * @return List in LabelValueBean
     * @throws SQLException SQL実行時例外
     */

    public int getCabinetSid(int fdrSid, Connection con)
    throws SQLException {

        int cabinetSid = 0;
        FileDirectoryDao dirDao = new FileDirectoryDao(con);

        FileDirectoryModel dirModel = dirDao.getNewDirectory(fdrSid);
        if (dirModel != null) {
            cabinetSid = dirModel.getFcbSid();
        }

        return cabinetSid;
    }

    /**
     * <br>[機  能] ディレクトリSIDよりキャビネット情報を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param fdrSid ディレクトリSID
     * @param con コネクション
     * @return FileCabinetModel
     * @throws SQLException SQL実行時例外
     */

    public FileCabinetModel getCabinetModel(int fdrSid, Connection con)
    throws SQLException {

        int cabinetSid = getCabinetSid(fdrSid, con);

        FileCabinetDao dao = new FileCabinetDao(con);
        FileCabinetModel model = dao.select(cabinetSid);

        return model;
    }

    /**
     * <br>[機  能] フォルダ名を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param directorySid ディレクトリSID
     * @param con コネクション
     * @return List in LabelValueBean
     * @throws SQLException SQL実行時例外
     */

    public String getDirctoryName(int directorySid, Connection con)
    throws SQLException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con);
        FileDirectoryModel dirModel = dirDao.getNewDirectory(directorySid);

        if (dirModel == null) {

            return "";
        }

        return dirModel.getFdrName();
    }

    /**
     * <br>[機  能] フォルダ名を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param directorySid ディレクトリSID
     * @param version バージョン
     * @param con コネクション
     * @return List in LabelValueBean
     * @throws SQLException SQL実行時例外
     */

    public String getDirctoryName(int directorySid, int version, Connection con)
    throws SQLException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con);
        FileDirectoryModel dirModel = dirDao.select(directorySid, version);

        if (dirModel == null) {

            return "";
        }

        return dirModel.getFdrName();
    }

    /**
     * <br>[機  能] 親ディレクトリSIDを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param directorySid ディレクトリSID
     * @param con コネクション
     * @return parentDirSId 親ディレクトリSID
     * @throws SQLException SQL実行時例外
     */

    public int getParentDirSid(int directorySid, Connection con)
    throws SQLException {

        FileDirectoryDao dirDao = new FileDirectoryDao(con);
        FileDirectoryModel dirModel = dirDao.getNewDirectory(directorySid);

        if (dirModel == null) {
            return -1;
        }

        return dirModel.getFdrParentSid();
    }

    /**
     * <br>[機  能] ディレクトリの最新バージョン+1の値を取得する。
     * <br>[解  説]　存在しない場合は-1を返します。
     * <br>[備  考]
     * @param directorySid ディレクトリSID
     * @param con コネクション
     * @return version 最新バージョン + 1
     * @throws SQLException SQL実行時例外
     */

    public int getNextVersion(int directorySid, Connection con)
    throws SQLException {

        int version = getNewVersion(directorySid, con);

        if (version != -1) {
            version += 1;
        }

        return version;
    }

    /**
     * <br>[機  能] ディレクトリの最新バージョンの値を取得する。
     * <br>[解  説]　存在しない場合は-1を返します。
     * <br>[備  考]
     * @param directorySid ディレクトリSID
     * @param con コネクション
     * @return version 最新バージョン
     * @throws SQLException SQL実行時例外
     */

    public int getNewVersion(int directorySid, Connection con)
    throws SQLException {

        int version = -1;

        FileDirectoryDao dao = new FileDirectoryDao(con);
        FileDirectoryModel model = dao.getNewDirectory(directorySid);
        if (model != null) {
            version = model.getFdrVersion();
        }

        return version;
    }

    /**
     * <br>[機  能] ディレクトリの最新バージョンの履歴を取得する。
     * <br>[解  説]　存在しない場合は-1を返します。
     * <br>[備  考]
     * @param directorySid ディレクトリSID
     * @param con コネクション
     * @return version 最新バージョン
     * @throws SQLException SQL実行時例外
     */

    public int getNewVersionReki(int directorySid, Connection con)
    throws SQLException {

        int version = -1;

        FileFileRekiDao dao = new FileFileRekiDao(con);
        FileFileRekiModel model = dao.getNewDirectoryReki(directorySid);
        if (model != null) {
            version = model.getFdrVersion();
        }

        return version;
    }

    /**
     * <br>[機  能] バージョン管理区分を取得する。
     * <br>[解  説]　管理しない場合は0を返します。
     * <br>[備  考]
     * @param cabinetSid ディレクトリSID
     * @param directorySid ディレクトリSID
     * @param con コネクション
     * @return version 最新バージョン
     * @throws SQLException SQL実行時例外
     */

    public int getVerKbn(int cabinetSid, int directorySid, Connection con)
    throws SQLException {

        int verKbn = GSConstFile.VERSION_KBN_OFF;

        //管理者設定のバージョン管理区分
        if (getVerKbnAdmin(con) == GSConstFile.VERSION_KBN_OFF) {
            return verKbn;
        }

        //キャビネットのバージョン管理区分
        FileCabinetDao cabDao = new FileCabinetDao(con);
        FileCabinetModel cabModel = cabDao.select(cabinetSid);
        if (cabModel == null) {
            return verKbn;
        }
        verKbn = cabModel.getFcbVerKbn();

        if (cabModel.getFcbVerallKbn() == GSConstFile.VERSION_ALL_KBN_ON) {
            return cabModel.getFcbVerKbn();
        }

        FileDirectoryDao dao = new FileDirectoryDao(con);
        FileDirectoryModel dirModel = dao.getNewDirectory(directorySid);
        if (dirModel != null) {
            verKbn = dirModel.getFdrVerKbn();
        }

        return verKbn;
    }

    /**
     * <br>[機  能] 更新通知を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param updateDirSid 更新ディレクトリSID
     * @param cntCon 採番コントロール
     * @param appRootPath APP ROOTパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @param reqMdl リクエスト情報
     * @param sessionUsrSid ユーザSID
     * @throws Exception 実行時例外
     */

    public void updateCall(
            int updateDirSid,
            MlCountMtController cntCon,
            String appRootPath,
            PluginConfig pconfig,
            boolean smailPluginUseFlg,
            RequestModel reqMdl,
            int sessionUsrSid)
    throws Exception {

        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileCallConfDao callConfDao = new FileCallConfDao(con__);

        //ディレクトリ情報を取得する。
        FileDirectoryModel dirModel = dirDao.getNewDirectory(updateDirSid);
        if (dirModel == null) {
            return;
        }

        //更新通知設定を取得する。
        List<FileCallConfModel> confList
                = callConfDao.getCallConf(dirModel.getFdrSid(), sessionUsrSid);

//      更新通知設定
        insertCallData(
                confList, dirModel, cntCon, appRootPath, pconfig, smailPluginUseFlg, reqMdl);
    }

    /**
     * <br>[機  能] 複数の更新通知情報を登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param beanList 更新情報リスト
     * @param dirModel 更新ディレクトリ情報
     * @param cntCon 採番コントロール
     * @param appRootPath APP ROOTパス
     * @param pconfig PluginConfig
     * @param smailPluginUseFlg ショートメール利用可能フラグ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void insertCallData(
            List<FileCallConfModel> beanList,
            FileDirectoryModel dirModel,
            MlCountMtController cntCon,
            String appRootPath,
            PluginConfig pconfig,
            boolean smailPluginUseFlg,
            RequestModel reqMdl
            ) throws Exception {

        if (beanList == null || beanList.size() < 1) {
            return;
        }
        FileCallDataDao callDataDao = new FileCallDataDao(con__);

        for (FileCallConfModel mdl : beanList) {
            callDataDao.delete(dirModel.getFdrSid(), mdl.getUsrSid());
        }
        for (FileCallConfModel mdl : beanList) {
            callDataDao.insert(transCallDataModel(mdl, dirModel.getFdrSid()));
        }
//      更新通知ショートメール
        sendPlgSmail(
                cntCon,
                reqMdl,
                dirModel,
                appRootPath,
                pconfig,
                smailPluginUseFlg,
                getFolderSyousaiUrlString(reqMdl, dirModel, con__),
                beanList);
    }

    /**
     * <br>[機  能] 更新通知設定モデルを更新確認情報モデルに変換する。
     * <br>[解  説]
     * <br>[備  考]
     * @param callConfModel 更新通知設定モデル
     * @param updateDirSid 更新ディレクトリSID
     * @return callDataModel 更新確認情報モデル
     * @throws SQLException SQL実行時例外
     */
    public FileCallDataModel transCallDataModel(FileCallConfModel callConfModel, int updateDirSid)
    throws SQLException {

        FileCallDataModel callDataModel = new FileCallDataModel();
        callDataModel.setFdrSid(updateDirSid);
        callDataModel.setUsrSid(callConfModel.getUsrSid());

        return callDataModel;
    }

    /**
     * <br>[機  能] ファイル管理の個人設定の初期値を登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return uconfModel ファイル管理個人設定モデル
     * @throws SQLException SQL実行時例外
     */
    public FileUconfModel insertUserConf(int usrSid, Connection con)
    throws SQLException {

        FileUconfDao dao = new FileUconfDao(con);
        FileUconfModel model = new FileUconfModel();
        model.setUsrSid(usrSid);
        model.setFucMainCall(GSConstFile.MAIN_CALL_DSP_CNT);
        model.setFucMainOkini(GSConstFile.MAIN_OKINI_DSP_ON);
        model.setFucRirekiCnt(GSConstFile.RIREKI_COUNT_DEFAULT);
        model.setFucSmailSend(GSConstFile.SMAIL_SEND_OFF);
        dao.insert(model);
        return model;
    }

    /**
     * <br>[機  能] ファイル管理の個人設定を登録する。
     * <br>[解  説] 存在しない場合は初期値で登録する。
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return uconfModel ファイル管理個人設定モデル
     * @throws SQLException SQL実行時例外
     */
    public FileUconfModel getUserConf(int usrSid, Connection con)
    throws SQLException {

        FileUconfDao uconfDao = new FileUconfDao(con);
        FileUconfModel uconfModel = uconfDao.select(usrSid);

        if (uconfModel == null) {
            boolean commitFlg = false;
            try {
                //個人設定が無い場合は初期値で登録する。
                uconfModel = insertUserConf(usrSid, con);
                commitFlg = true;

            } catch (SQLException e) {
                log__.error("SQLException", e);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    JDBCUtil.rollback(con);
                }
            }
        }

        return uconfModel;
    }

    /**
     * <br>[機  能] ファイルがダウンロード可能か判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param binSid バイナリSID
     * @return true:ダウンロード可 false:ダウンロード不可
     * @throws SQLException 実行例外
     */
    public boolean isDownloadAuthUser(long binSid)
            throws SQLException {
        return isDownloadAuthUser(binSid, true);
    }

    /**
     * <br>[機  能] ファイルがダウンロード可能か判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param binSid バイナリSID
     * @param isOnlyNewVersion 最新のみダウンロードを許可
     * @return true:ダウンロード可 false:ダウンロード不可
     * @throws SQLException 実行例外
     */
    public boolean isDownloadAuthUser(long binSid, boolean isOnlyNewVersion) throws SQLException {
        FileFileBinDao filBinDao = new FileFileBinDao(con__);
        FileDAccessConfDao daConfDao = new FileDAccessConfDao(con__);

        //バイナリSIDの存在チェック
        int count = filBinDao.getBinCount(binSid);
        if (count < 1) {
            return false;
        }

        //キャビネットSIDを取得
        int fcbSid = -1;
        fcbSid = filBinDao.getCabinetSid(binSid, isOnlyNewVersion);
        if (isEditCabinetUser(fcbSid, con__)) {
            //特権ユーザ
            return true;
        }

        //ログインユーザモデルを取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel umodel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        return daConfDao.isAccessBinFile(binSid, umodel.getUsrsid(), isOnlyNewVersion);
    }

    /**
     * ユーザがディレクトリアクセス権限を持っているか判定します。
     * システム管理者、管理者権限を持つユーザは権限有り
     * @param fcbSid キャビネットSID
     * @param dirSid ディレクトリSID
     * @param usrSid ユーザSID
     * @param auth 権限区分
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException 実行例外
     */
    public boolean isDirAccessAuthUser(
            int fcbSid, int dirSid, int usrSid, int auth, Connection con)
            throws SQLException {

        if (isEditCabinetUser(fcbSid, con)) {
            //特権ユーザ
            return true;
        }
        FileDAccessConfDao dao = new FileDAccessConfDao(con);
        if (dao.isAccessUser(dirSid, usrSid, auth)) {
            //編集権限ユーザ
            return true;
        }
        return false;
    }

    /**
     * ユーザがディレクトリアクセス権限を持っているか判定します。
     * システム管理者、管理者権限を持つユーザは権限有り
     * @param fcbSid キャビネットSID
     * @param dirSid ディレクトリSID
     * @param usrSid ユーザSID
     * @param auth 権限区分
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException 実行例外
     */
    public boolean isDirAccessAuthUser(
            int fcbSid, String[] dirSid, int usrSid, int auth, Connection con)
                    throws SQLException {

        if (isEditCabinetUser(fcbSid, con)) {
            //特権ユーザ
            return true;
        }
        if (dirSid != null && dirSid.length > 0) {
            FileDAccessConfDao dao = new FileDAccessConfDao(con);
            for (String sid : dirSid) {
                if (!dao.isAccessUser(NullDefault.getInt(sid, -1),
                                      usrSid,
                                      auth)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * ユーザがキャビネット管理者権限を持っているか判定します。
     * システム管理者、管理者権限を持つユーザは権限有り
     * @param fcbSid キャビネットSID
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isEditCabinetUser(int fcbSid, Connection con)
    throws SQLException {

        HttpSession session = reqMdl__.getSession();
        BaseUserModel umodel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        //システム管理者判定
        CommonBiz  commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(con, umodel, GSConstFile.PLUGIN_ID_FILE);

        if (adminUser) {
            return true;
        }
        //キャビネット管理者権限
        if (isCabinetAdminUser(fcbSid, umodel.getUsrsid(), con)) {
            return true;
        }
        return false;
    }

    /**
     * ユーザがキャビネットへのアクセス権限を持っているか判定します。
     * 閲覧権限があればtrue
     * @param fcbSid キャビネットSID
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isAccessAuthUser(int fcbSid, Connection con)
    throws SQLException {

        HttpSession session = reqMdl__.getSession();
        BaseUserModel umodel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        //アクセス制御をするキャビネットか判定
        FileCabinetDao cabDao = new FileCabinetDao(con);
        FileCabinetModel cabMdl = cabDao.select(fcbSid);
        if (cabMdl != null && cabMdl.getFcbAccessKbn() == GSConstFile.ACCESS_KBN_OFF) {
            return true;
        } else {
            //システム管理者判定
            CommonBiz  commonBiz = new CommonBiz();
            boolean adminUser = commonBiz.isPluginAdmin(con, umodel, GSConstFile.PLUGIN_ID_FILE);

            if (adminUser) {
                return true;
            }
            //キャビネット管理者権限
            if (isCabinetAdminUser(fcbSid, umodel.getUsrsid(), con)) {
                return true;
            }
            //アクセス制御から判定
            FileAccessConfDao accDao = new FileAccessConfDao(con);
            if (accDao.isAccessUser(fcbSid, umodel.getUsrsid(), -1)) {
                return true;
            }
            if (accDao.isAccessUserForBelongGroup(fcbSid, umodel.getUsrsid(), -1)) {
                return true;
            }
        }

        return false;
    }

    /**
     * ユーザがキャビネットへのアクセス権限を持っているか判定します。
     * 閲覧権限があればtrue
     * @param fcbSid キャビネットSID
     * @param con コネクション
     * @param umodel BaseUserModel
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isAccessAuthUser(int fcbSid, Connection con, BaseUserModel umodel)
    throws SQLException {


        //アクセス制御をするキャビネットか判定
        FileCabinetDao cabDao = new FileCabinetDao(con);
        FileCabinetModel cabMdl = cabDao.select(fcbSid);
        if (cabMdl != null && cabMdl.getFcbAccessKbn() == GSConstFile.ACCESS_KBN_OFF) {
            return true;
        } else {
            //システム管理者判定
            CommonBiz  commonBiz = new CommonBiz();
            boolean adminUser = commonBiz.isPluginAdmin(con, umodel, GSConstFile.PLUGIN_ID_FILE);
            if (adminUser) {
                return true;
            }
            //キャビネット管理者権限
            if (isCabinetAdminUser(fcbSid, umodel.getUsrsid(), con)) {
                return true;
            }
            //アクセス制御から判定
            FileAccessConfDao accDao = new FileAccessConfDao(con);
            if (accDao.isAccessUser(fcbSid, umodel.getUsrsid(), -1)) {
                return true;
            }
            if (accDao.isAccessUserForBelongGroup(fcbSid, umodel.getUsrsid(), -1)) {
                return true;
            }
        }

        return false;
    }

    /**
     * ユーザがキャビネットへの書込み権限を持っているか判定します。
     * 書込み権限があればtrue
     * @param fcbSid キャビネットSID
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isWriteAuthUser(int fcbSid, Connection con)
    throws SQLException {

        HttpSession session = reqMdl__.getSession();
        BaseUserModel umodel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

        return isWriteAuthUser(umodel, fcbSid, con);
    }
    /**
     * ユーザがキャビネットへの書込み権限を持っているか判定します。
     * 書込み権限があればtrue
     * @param umodel セッションユーザ情報
     * @param fcbSid キャビネットSID
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isWriteAuthUser(BaseUserModel umodel, int fcbSid, Connection con)
    throws SQLException {

        //アクセス制御をするキャビネットか判定
        FileCabinetDao cabDao = new FileCabinetDao(con);
        FileCabinetModel cabMdl = cabDao.select(fcbSid);
        if (cabMdl != null && cabMdl.getFcbAccessKbn() == GSConstFile.ACCESS_KBN_OFF) {
            return true;
        } else {
            //システム管理者判定
            CommonBiz  commonBiz = new CommonBiz();
            boolean adminUser = commonBiz.isPluginAdmin(con, umodel, GSConstFile.PLUGIN_ID_FILE);
            if (adminUser) {
                return true;
            }
            //キャビネット管理者権限
            if (isCabinetAdminUser(fcbSid, umodel.getUsrsid(), con)) {
                return true;
            }
            //アクセス制御から判定
            FileAccessConfDao accDao = new FileAccessConfDao(con);
            if (accDao.isAccessUser(fcbSid, umodel.getUsrsid(),
                    Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE))) {
                return true;
            }
            if (accDao.isAccessUserForBelongGroup(fcbSid, umodel.getUsrsid(),
                    Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE))) {
                return true;
            }
        }

        return false;
    }

    /**
     * ユーザがファイルロック解除が可能か判定します。
     * システム管理者、管理者権限を持つユーザは権限有り
     * @param fcbSid キャビネットSID
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isCanFileUnlockUser(int fcbSid, Connection con)
    throws SQLException {

        if (isEditCabinetUser(fcbSid, con)) {
            //特権ユーザ
            return true;
        }

        return false;
    }

    /**
     * ユーザがキャビネット編集権限を持っているか判定します。
     * システム管理者、作成権限、管理者権限を持つユーザは権限有り
     * @param fcbSid キャビネットSID
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isCabinetAdminUser(int fcbSid, int usrSid, Connection con)
    throws SQLException {
        FileCabinetAdminDao dao = new FileCabinetAdminDao(con);
        FileCabinetAdminModel mdl = dao.select(fcbSid, usrSid);
        if (mdl == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * ユーザがキャビネット作成権限を持っているか判定します。
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isCanCreateCabinetUser(Connection con)
    throws SQLException {

        if (!isCanCreateCabinetAdmin(con)) {
            //管理者設定のキャビネット作成区分権限設定なし
            return true;
        }

        HttpSession session = reqMdl__.getSession();
        BaseUserModel umodel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        CommonBiz  commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(con, umodel, GSConstFile.PLUGIN_ID_FILE);
        if (adminUser) {
            return true;
        }
        return __isCanCreateCabinetUser(umodel.getUsrsid(), con);
    }

    /**
     * ユーザがキャビネット作成権限を持っているか判定します。
     * @param umodel BaseUserModel
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isCanCreateCabinetUser(BaseUserModel umodel, Connection con)
    throws SQLException {

        if (!isCanCreateCabinetAdmin(con)) {
            //管理者設定のキャビネット作成区分権限設定なし
            return true;
        }

        CommonBiz  commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(con, umodel, GSConstFile.PLUGIN_ID_FILE);
        if (adminUser) {
            return true;
        }

        return __isCanCreateCabinetUser(umodel.getUsrsid(), con);
    }

    /**
     * 管理者設定でキャビネット作成権限が設定されているか判定します。
     * @param con コネクション
     * @return true:権限設定あり false:権限設定なし
     * @throws SQLException SQL実行時例外
     */
    public boolean isCanCreateCabinetAdmin(Connection con)
    throws SQLException {

        //管理者設定のキャビネット作成区分を取得
        FileAconfDao aconfDao = new FileAconfDao(con);
        FileAconfModel aconfModel =  aconfDao.select();
        if (aconfModel != null) {
            if (aconfModel.getFacCrtKbn() == GSConstFile.CREATE_CABINET_PERMIT_NO) {
                return false;
            }
        }

        return true;
    }

    /**
     * ユーザがファイルの閲覧権限を持っているか判定します。
     * 閲覧権限があればtrue
     * @param binSid バイナリSID
     * @param con コネクション
     * @param umodel BaseUserModel
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean canAccessFile(long binSid, Connection con, BaseUserModel umodel)
    throws SQLException {

        //キャビネットSIDを取得
        FileFileBinDao fileBinDao = new FileFileBinDao(con);
        int fcbSid = fileBinDao.getCabinetSid(binSid);

        return this.isAccessAuthUser(fcbSid, con, umodel);
    }

    /**
     * <br>[機  能] 登録に使用する側のコンボで選択中のメンバーをメンバーリストから削除する
     *
     * <br>[解  説] 登録に使用する側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で削除矢印ボタンをクリックした場合、
     *              登録に使用する側のコンボで選択中の値(deleteSelectSid)をメンバーリストから削除する。
     *
     * <br>[備  考] 登録に使用する側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param deleteSelectSid 登録に使用する側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 削除済みのメンバーリスト
     */
    public String[] getDeleteMember(String[] deleteSelectSid, String[] memberSid) {

        if (deleteSelectSid == null) {
            return memberSid;
        }
        if (deleteSelectSid.length < 1) {
            return memberSid;
        }

        if (memberSid == null) {
            memberSid = new String[0];
        }

        //削除後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < memberSid.length; i++) {
            boolean setFlg = true;

            for (int j = 0; j < deleteSelectSid.length; j++) {
                if (memberSid[i].equals(deleteSelectSid[j])) {
                    setFlg = false;
                    break;
                }
            }

            if (setFlg) {
                list.add(memberSid[i]);
            }
        }

        log__.debug("削除後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] 追加側のコンボで選択中のメンバーをメンバーリストに追加する
     *
     * <br>[解  説] 画面右側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で追加矢印ボタンをクリックした場合、
     *              追加側のコンボで選択中の値(addSelectSid)をメンバーリストに追加する。
     *
     * <br>[備  考] 追加側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param addSelectSid 追加側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 追加済みのメンバーリスト
     */
    public String[] getAddMember(String[] addSelectSid, String[] memberSid) {

        if (addSelectSid == null) {
            return memberSid;
        }
        if (addSelectSid.length < 1) {
            return memberSid;
        }


        //追加後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        if (memberSid != null) {
            for (int j = 0; j < memberSid.length; j++) {
                if (!memberSid[j].equals("-1")) {
                    list.add(memberSid[j]);
                }
            }
        }

        for (int i = 0; i < addSelectSid.length; i++) {
            if (!addSelectSid[i].equals("-1")) {
                list.add(addSelectSid[i]);
            }
        }

        log__.debug("追加後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }


    /**
     * ユーザがキャビネット作成権限を持っているか判定します。
     * @param userSid ユーザSID
     * @param con コネクション
     * @return true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    private boolean __isCanCreateCabinetUser(int userSid, Connection con) throws SQLException {
        FileCrtConfDao dao = new FileCrtConfDao(con);
        return dao.isCreateAuth(userSid);
    }
//
//    /**
//     * <br>[機  能] 添付ファイルダウンロードの処理
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param req リクエスト
//     * @param res レスポンス
//     * @param fileSid ダウンロード対象バイナリSID
//     * @param tempDir テンポラリディレクトリ
//     * @param con コネクション
//     * @param map マッピング
//     * @throws SQLException SQL実行例外
//     * @throws Exception 実行時例外
//     * @return ActionForward
//     */
//    public ActionForward doDownLoadxxx(
//        HttpServletRequest req,
//        HttpServletResponse res,
//        String fileSid,
//        String tempDir,
//        Connection con,
//        ActionMapping map) throws SQLException, Exception {
//
//        //オブジェクトファイルを取得
//        ObjectFile objFile = new ObjectFile(
//                tempDir, form.getFileSid().concat(GSConstCommon.ENDSTR_OBJFILE));
//        Object fObj = objFile.load();
//        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
//        //添付ファイル保存用のパスを取得する(フルパス)
//        String filePath = tempDir + form.getFileSid().concat(GSConstCommon.ENDSTR_SAVEFILE);
//        filePath = IOTools.replaceFileSep(filePath);
//
//        GsMessage gsMsg = new GsMessage();
//        String textDownload = gsMsg.getMessage("cmn.download");
//
//        //ログ出力処理
//        fBiz.outPutLog(
//                textDownload, GSConstLog.LEVEL_INFO, fMdl.getFileName(), map.getType());
//
//        //ファイルをダウンロードする
//        TempFileUtil.downloadAtachment(req, res,
//                filePath, fMdl.getFileName(), Encoding.UTF_8);
//
//        return null;
//    }

    /**
     * <br>[機  能] 添付ファイルをテンポラリディレクトリへ設定する
     * <br>[解  説] ファイル添付情報を取得する。
     * <br>[備  考]
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param fcbSid キャビネットSID
     * @param con コネクション
     * @return saveName
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public String setCabinetTempFile(
        String appRoot,
        String tempDir,
        int fcbSid,
        Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();

        String saveName = "";
        String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)

        FileCabinetBinDao cabinetBinDao = new FileCabinetBinDao(con);
        //ファイル管理の添付ファイル情報を取得する
        String[] binSids = cabinetBinDao.getBinList(fcbSid);
        if (binSids == null || binSids.length < 1) {
            return saveName;
        }
        //添付ファイル情報を取得する
        List<CmnBinfModel> cmBinList = cmnBiz.getBinInfo(con, binSids,
                reqMdl__.getDomain());

        int fileNum = 1;
        for (CmnBinfModel cbMdl : cmBinList) {

            //添付ファイルをテンポラリディレクトリにコピーする。
            cmnBiz.saveTempFile(dateStr, cbMdl, appRoot, tempDir, fileNum);

            fileNum++;
        }
        return saveName;
    }

    /**
     * <br>[機  能] 添付ファイルをテンポラリディレクトリへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param dirSid ディレクトリSID
     * @param version バージョン
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setFolderTempFile(
        String appRoot,
        String tempDir,
        int dirSid,
        int version,
        Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();

        String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)

        //添付ファイル情報を取得する
        FileDirectoryBinDao dirBinDao = new FileDirectoryBinDao(con);
        List <Long> binSidList = dirBinDao.getBinSidList(dirSid, version);
        String[] binSids = new String[binSidList.size()];
        int i = 0;
        for (Long sid : binSidList) {
            binSids[i] = String.valueOf(sid);
            i++;
        }
        if (binSids == null || binSids.length < 1) {
            return;
        }
        List <CmnBinfModel> cmnBinList = cmnBiz.getBinInfo(con, binSids,
                reqMdl__.getDomain());
        int fileNum = 1;
        for (CmnBinfModel cbMdl : cmnBinList) {

            //添付ファイルをテンポラリディレクトリにコピーする。
            cmnBiz.saveTempFile(dateStr, cbMdl, appRoot, tempDir, fileNum);
            fileNum++;
        }
    }

    /**
     * <br>[機  能] 添付ファイルをテンポラリディレクトリへ設定する
     * <br>[解  説] ファイル添付情報を取得する。
     * <br>[備  考]
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param dirSid ディレクトリSID
     * @param version バージョン
     * @param con コネクション
     * @return saveName
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public String setTempFile(
        String appRoot,
        String tempDir,
        int dirSid,
        int version,
        Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();

        String saveName = "";
        String dateStr = (new UDate()).getDateString(); //現在日付の文字列(YYYYMMDD)

        //添付ファイル情報を取得する
        FileFileBinDao fileBinDao = new FileFileBinDao(con);
        List<Long> binSidList = fileBinDao.getBinSidList(dirSid, version);
        if (binSidList == null || binSidList.size() < 1) {
            return saveName;
        }
        String[] binSids = new String[binSidList.size()];
        int i = 0;
        for (Long sid : binSidList) {
            binSids[i] = String.valueOf(sid);
            i++;
        }

        List<CmnBinfModel> cmBinList = cmnBiz.getBinInfo(con, binSids,
                reqMdl__.getDomain());

        int fileNum = 1;
        for (CmnBinfModel cbMdl : cmBinList) {

            //添付ファイルをテンポラリディレクトリにコピーする。
            cmnBiz.saveTempFile(dateStr, cbMdl, appRoot, tempDir, fileNum);

            fileNum++;
        }
        return saveName;
    }

    /**
     * <br>[機  能] 指定したバイナリSIDのファイルをコピーする。
     * <br>[解  説] ファイル添付情報を取得する。
     * <br>[備  考]
     * @param appRoot アプリケーションのルートパス
     * @param binSid バイナリSID
     * @param usrSid ユーザSID
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param domain ドメイン
     * @return newBinSid 採番バイナリSID
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public Long copyFile(
        String appRoot,
        Long binSid,
        int usrSid,
        Connection con,
        MlCountMtController cntCon,
        String domain
        ) throws TempFileException {

        CommonBiz cmnBiz = new CommonBiz();
        Long newBinSid = cmnBiz.copyfile(appRoot, binSid, usrSid, con, cntCon, domain);

        return newBinSid;
    }

    /**
     * <br>[機  能] テンポラリディレクトリにあるファイルの合計ファイルサイズを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリ
     * @param con コネクション
     * @return fileSizeSum 合計ファイルサイズ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    public BigDecimal getSumTempFileSize(String tempDir, Connection con)
    throws SQLException, IOToolsException {

        //テンポラリディレクトリにあるファイル名称を取得
        List < String > fileList = IOTools.getFileNames(tempDir);

        //ファイルのリストを作成
        BigDecimal fileSizeSum = new BigDecimal(0);

        if (fileList != null) {

            for (int i = 0; i < fileList.size(); i++) {

                //ファイル名を取得
                String fileName = fileList.get(i);

                if (!fileName.endsWith(GSConstCommon.ENDSTR_OBJFILE)) {
                    continue;
                }

                String name = fileName.replaceFirst(
                        GSConstCommon.ENDSTR_OBJFILE, GSConstCommon.ENDSTR_SAVEFILE);
                File file = new File(tempDir, name);
                long atattiSize = file.length();
                //オブジェクトファイルを取得
                ObjectFile objFile = new ObjectFile(tempDir, fileName);
                Object fObj = objFile.load();
                if (fObj == null) {
                    continue;
                }
                BigDecimal fileSize = BigDecimal.valueOf(atattiSize);
                fileSizeSum = fileSizeSum.add(fileSize);
            }
        }
        return fileSizeSum;
    }

    /**
     * <br>[機  能] バージョン管理コンボを取得する
     * <br>[解  説] ファイル添付情報を取得する。
     * <br>[備  考]
     * @return verKbnLabel バージョンラベルリスト
     */
    public List<LabelValueBean> getVerKbnLabelList() {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textSinai = gsMsg.getMessage("fil.65");

        List<LabelValueBean> verKbnLabel = new ArrayList<LabelValueBean>();
        verKbnLabel.add(new LabelValueBean(textSinai, "0"));
        for (int i = 1; i < 11; i++) {
            verKbnLabel.add(new LabelValueBean(
                    gsMsg.getMessage(
                            "fil.generations",
                            new String[] {String.valueOf(i)}), String.valueOf(i)));
        }

        return verKbnLabel;
    }

    /**
     * <br>[機  能] ファイルロック区分を更新する。
     * <br>[解  説] DBコミットする。
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param version バージョン
     * @param lockKbn ロック区分
     * @param usrSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void updateLockKbnCommit(
            int dirSid, int version, int lockKbn, int usrSid, Connection con)
    throws SQLException {

        boolean commitFlg = false;

        try {
            //ロックを更新する。
            FileFileBinDao fileBinDao = new FileFileBinDao(con);
            FileFileBinModel fileBinModel = new FileFileBinModel();

            UDate now = new UDate();
            fileBinModel.setFdrSid(dirSid);
            fileBinModel.setFdrVersion(version);
            fileBinModel.setFflLockKbn(lockKbn);
            fileBinModel.setFflLockUser(usrSid);
            fileBinModel.setFflLockDate(now);
            fileBinDao.updateLockKbn(fileBinModel);

            commitFlg = true;
        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * <br>[機  能] ファイルロック区分を更新する。
     * <br>[解  説] DBコミットする。
     * <br>[備  考]
     * @param dirSids ディレクトリSIDリスト
     * @param lockKbn ロック区分
     * @param usrSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void updateLockKbnCommit(
            String[] dirSids, int lockKbn, int usrSid, Connection con)
    throws SQLException {

        boolean commitFlg = false;

        try {
            //ロックを更新する。
            FileFileBinDao fileBinDao = new FileFileBinDao(con);
            FileFileBinModel fileBinModel = new FileFileBinModel();
            UDate now = new UDate();
            fileBinModel.setFflLockKbn(lockKbn);
            fileBinModel.setFflLockUser(usrSid);
            fileBinModel.setFflLockDate(now);

            fileBinDao.updateLockKbn(fileBinModel, dirSids);

            commitFlg = true;
        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * <br>[機  能] ファイルロック区分を更新する。
     * <br>[解  説]　DBコミットしない。
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param version バージョン
     * @param lockKbn ロック区分
     * @param usrSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void updateLockKbn(int dirSid, int version, int lockKbn, int usrSid, Connection con)
    throws SQLException {

        //ロックを更新する。
        FileFileBinDao fileBinDao = new FileFileBinDao(con);
        FileFileBinModel fileBinModel = new FileFileBinModel();
        UDate now = new UDate();
        fileBinModel.setFdrSid(dirSid);
        fileBinModel.setFdrVersion(version);
        fileBinModel.setFflLockKbn(lockKbn);
        fileBinModel.setFflLockUser(usrSid);
        fileBinModel.setFflLockDate(now);
        fileBinDao.updateLockKbn(fileBinModel);

    }

    /**
     * <br>[機  能] ファイル編集可能か判定する。
     * <br>[解  説] ファイルロックを行う。すでにロックされている場合には、ユーザチェックを行う。
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @param usrSid セッションユーザSID
     * @param con コネクション
     * @return lock true:編集可能 false:編集不可能
     * @throws SQLException SQL実行例外
     */
    public boolean checkFileLock(int dirSid, int usrSid, Connection con)
    throws SQLException {

        FileFileBinDao fileBinDao = new FileFileBinDao(con);
        FileFileBinModel fileBinModel = fileBinDao.getNewFile(dirSid);

        if (fileBinModel == null) {
            return false;
        }

        if (fileBinModel.getFflLockKbn() == GSConstFile.LOCK_KBN_ON
                && fileBinModel.getFflLockUser() != usrSid) {
            //編集ユーザがログインユーザと異なった場合
            return false;
        }
        return true;
    }

    /**
     * ファイル管理個人設定を取得します。存在しない場合は初期値で作成します
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return 個人設定
     * @throws SQLException SQL実行時例外
     */
    public FileUconfModel getFileUconfModel(int usrSid, Connection con)
    throws SQLException {

        FileUconfDao udao = new FileUconfDao(con);
        FileUconfModel uConfMdl = udao.select(usrSid);
        if (uConfMdl == null) {
            boolean commitFlg = false;
            try {
                uConfMdl = new FileUconfModel();
                uConfMdl.init(usrSid);
                udao.insert(uConfMdl);
                commitFlg = true;
            } catch (SQLException e) {
                log__.error("SQLException", e);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    JDBCUtil.rollback(con);
                }
            }
            return uConfMdl;
        } else {
            return uConfMdl;
        }
    }
    /**
     * ファイル管理 管理者設定を取得します。存在しない場合は初期値で作成します
     * @param con コネクション
     * @return 管理者設定
     * @throws SQLException SQL実行時例外
     */
    public FileAconfModel getFileAconfModel(Connection con)
    throws SQLException {

        CmnFileConfDao cfcDao = new CmnFileConfDao(con);
        FileAconfDao aconfDao = new FileAconfDao(con);
        FileAconfModel aconfMdl = aconfDao.select();
        if (aconfMdl == null) {
            boolean commitFlg = false;
            try {
                aconfMdl = new FileAconfModel();
                aconfMdl.init();
                CmnFileConfModel cfcMdl = cfcDao.select();
                if (cfcMdl != null) {
                    aconfMdl.setFacFileSize(cfcMdl.getFicMaxSize());
                }
                aconfDao.insert(aconfMdl);
                commitFlg = true;
            } catch (SQLException e) {
                log__.error("SQLException", e);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    JDBCUtil.rollback(con);
                }
            }
            return aconfMdl;
        } else {
            return aconfMdl;
        }
    }
    /**
     * <br>管理者設定のロック機能区分を取得します。
     * @param con コネクション
     * @return lockKbn ロック機能区分（管理者設定）
     * @throws SQLException SQL実行時例外
     */
    public int getLockKbnAdmin(Connection con) throws SQLException {

        FileAconfDao aconfDao = new FileAconfDao(con);
        FileAconfModel aconfMdl = aconfDao.select();

        if (aconfMdl == null) {
            return GSConstFile.LOCK_KBN_ON;
        }

        return aconfMdl.getFacLockKbn();
    }

    /**
     * <br>管理者設定のバージョン管理区分を取得します。
     * @param con コネクション
     * @return lockKbn ロック機能区分（管理者設定）
     * @throws SQLException SQL実行時例外
     */
    public int getVerKbnAdmin(Connection con) throws SQLException {

        FileAconfDao aconfDao = new FileAconfDao(con);
        FileAconfModel aconfMdl = aconfDao.select();

        if (aconfMdl == null) {
            return GSConstFile.VERSION_KBN_OFF;
        }

        return aconfMdl.getFacVerKbn();
    }


    /**
     * <br>管理者設定の削除したファイルの保存期間を取得します。
     * @param con コネクション
     * @return saveDays 削除ファイル保存期間
     * @throws SQLException SQL実行時例外
     */
    public int getDelFileSaveDays(Connection con)
    throws SQLException {

        FileAconfDao aconfDao = new FileAconfDao(con);
        FileAconfModel aconfMdl = aconfDao.select();

        if (aconfMdl == null) {
            return 0;
        }

        return aconfMdl.getFacSaveDays();
    }
    /**
     * <br>[機  能] ショートメールプラグインで更新通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param cntCon MlCountMtController
     * @param dirModel ディレクトリ情報
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param url ファイル管理URL
     * @param beanList 通知先リスト
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void sendPlgSmail(
        MlCountMtController cntCon,
        RequestModel reqMdl,
        FileDirectoryModel dirModel,
        String appRootPath,
        PluginConfig pluginConfig,
        boolean smailPluginUseFlg,
        String url,
        List<FileCallConfModel> beanList) throws Exception {

        if (!smailPluginUseFlg) {
            //ショートメールプラグインが無効の場合、ショートメールを送信しない。
            return;
        }
        CmnUsrmInfDao udao = new CmnUsrmInfDao(con__);
        CmnUsrmInfModel umodel = null;

        //管理者設定を取得
        FileAconfModel aconfMdl = getFileAconfModel(con__);

        // 更新者
        int userSid = dirModel.getFdrEuid();
        int gpSid = dirModel.getFdrEgid();
        List<Integer> sidList = new ArrayList<Integer>();
        for (FileCallConfModel mdl : beanList) {
            //管理者設定ショートメール通知 「管理者が設定する」の場合
            if (aconfMdl.getFacSmailSendKbn() == GSConstFile.FAC_SMAIL_SEND_KBN_ADMIN) {
                //管理者設定ショートメール通知 「通知する」の場合
                if (aconfMdl.getFacSmailSend() == GSConstFile.FAC_SMAIL_SEND_YES) {
                    sidList.add(new Integer(mdl.getUsrSid()));
                }

            //管理者設定ショートメール通知 「各ユーザが設定する」の場合
            } else {
                //個人設定ショートメール通知 「通知する」の場合
                if (mdl.getFucSmailSend() == GSConstFile.SMAIL_SEND_ON) {
                    sidList.add(new Integer(mdl.getUsrSid()));
                }
            }
        }

        try {

            //更新日付
            String udate = UDateUtil.getSlashYYMD(dirModel.getFdrEdate())
            + " "
            + UDateUtil.getSeparateHMS(dirModel.getFdrEdate());

            //登録ユーザ名
            String editUserName = "";
            if (gpSid > 0) {
                GroupDao gpDao = new GroupDao(con__);
                CmnGroupmModel gpMdl = new CmnGroupmModel();
                gpMdl = gpDao.getGroup(gpSid);
                if (gpMdl != null) {
                    editUserName = gpMdl.getGrpName();
                }
            } else {
                umodel = udao.select(userSid);
                editUserName = umodel.getUsiSei() + " " + umodel.getUsiMei();
            }

            //更新ファイルフルパス
            String fullpath = getDirctoryPath(dirModel.getFdrSid(), con__);

            //備考
            String biko = dirModel.getFdrBiko();

            //本文
            String tmpPath = getSmlTemplateFilePathPlg(appRootPath); //テンプレートファイルパス取得
            String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
            Map<String, String> map = new HashMap<String, String>();
            map.put("UDATE", udate);
            map.put("UNAME", editUserName);
            map.put("FILENAME", fullpath);
            map.put("BIKO", biko);
            map.put("URL", url);

            GsMessage gsMsg = new GsMessage(reqMdl);
            String textMessage = gsMsg.getMessage("cmn.mail.omit");

            String bodyml = StringUtil.merge(tmpBody, map);

            if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {
                bodyml = textMessage + "\r\n\r\n" + bodyml;
                bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);
            }

            //ショートメール送信用モデルを作成する。
            SmlSenderModel smlModel = new SmlSenderModel();
            //送信者(システムメールを指定)
            smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
            //TO
            smlModel.setSendToUsrSidArray(sidList);
            //タイトル
            String title = gsMsg.getMessage("fil.38");
            if (!StringUtil.isNullZeroString(fullpath)) {
                title += (new File(fullpath)).getName();
            }
            title = StringUtil.trimRengeString(title,
                    GSConstCommon.MAX_LENGTH_SMLTITLE);
            smlModel.setSendTitle(title);

            //本文
            smlModel.setSendBody(bodyml);
            //マーク
            smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);
            //メール形式
            smlModel.setSendType(GSConstSmail.SAC_SEND_MAILTYPE_NORMAL);

            //メール送信処理開始
            SmlSender sender = new SmlSender(con__, cntCon, smlModel,
                                             pluginConfig, appRootPath, reqMdl);
            sender.execute();
        } catch (Exception e) {
            log__.error("ショートメール送信に失敗しました。", e);
            throw e;
        }
    }

    /**
     * <br>[機  能] ファイル管理プラグインアプリケーションのルートパスから更新通知メールのテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    public String getSmlTemplateFilePathPlg(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/file/smail/koushin_tsuuchi.txt");
        return ret;
    }

    /**
     * ファイル詳細アクセス用のURLを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param dirModel ディレクトリSID
     * @param con コネクション
     * @return String URL
     * @throws SQLException SQL実行時例外
     * @throws UnsupportedEncodingException エンコーディング例外
     */
    public String getFolderSyousaiUrlString(
            RequestModel reqMdl, FileDirectoryModel dirModel, Connection con)
    throws SQLException, UnsupportedEncodingException {
        String folderUrl = null;

        String url = reqMdl.getReferer();
        if (!StringUtil.isNullZeroString(url)) {

            folderUrl = url.substring(0, url.lastIndexOf("/"));
            folderUrl = folderUrl.substring(0, folderUrl.lastIndexOf("/"));
            folderUrl += "/common/cmn001.do?url=";

            String paramUrl = reqMdl.getRequestURI();
            int sepIndex = 0;
            while ((sepIndex = paramUrl.lastIndexOf("/")) != 0) {
                paramUrl = paramUrl.substring(0, sepIndex);
            }

            String domain = "";
            String reqDomain = NullDefault.getString(reqMdl.getDomain(), "");
            if (!reqDomain.equals(GSConst.GS_DOMAIN)) {
                domain = "/" + reqDomain;
                paramUrl +=  domain;
            }
            paramUrl +=  "/file/fil070.do";

            paramUrl += "?fil070DirSid=" + dirModel.getFdrSid();
            paramUrl += "&fil010SelectDirSid=" + dirModel.getFdrParentSid();
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);
            folderUrl += paramUrl;
        }
        return folderUrl;
    }

    /**
     * ファイル管理全般のログ出力を行う
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param type type
     */
    public void outPutLog(
            String opCode,
            String level,
            String value,
            String type) {
        outPutLog(opCode, level, value, type, null);
    }

    /**
     * ファイル管理全般のログ出力を行う
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param type type
     * @param fileId 添付ファイルSID
     */
    public void outPutLog(
            String opCode,
            String level,
            String value,
            String type,
            String fileId) {

        BaseUserModel usModel = reqMdl__.getSmodel();
        int usrSid = -1;
        if (usModel != null) {
            usrSid = usModel.getUsrsid(); //セッションユーザSID
        }

        GsMessage gsMsg = new GsMessage();
        String textFileKanri = gsMsg.getMessage("cmn.filekanri");

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usrSid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConstFile.PLUGIN_ID_FILE);
        logMdl.setLogPluginName(textFileKanri);
        logMdl.setLogPgId(StringUtil.trimRengeString(type, 100));
        logMdl.setLogPgName(getPgName(type));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(value);
        logMdl.setLogIp(reqMdl__.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);
        if (fileId != null) {
            logMdl.setLogCode("binSid：" + fileId);
        }

        LoggingBiz logBiz = new LoggingBiz(con__);
        String domain = reqMdl__.getDomain();
        logBiz.outPutLog(logMdl, domain);
    }
    /**
     * ファイル管理ＡＰＩ全般のログ出力を行う
     * @param usid ユーザSID
     * @param pgId プログラムID
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     */
    public void outPutApiLog(
            int usid,
            String pgId,
            String opCode,
            String level,
            String value) {
        outPutApiLog(usid, pgId, opCode, level, value, null);
    }
    /**
     * ファイル管理ＡＰＩ全般のログ出力を行う
     * @param usid ユーザSID
     * @param pgId プログラムID
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param fileId 添付ファイルSID
     */
    public void outPutApiLog(
            int usid,
            String pgId,
            String opCode,
            String level,
            String value,
            String fileId) {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textFileKanri = gsMsg.getMessage("cmn.filekanri");

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConstFile.PLUGIN_ID_FILE);
        logMdl.setLogPluginName(textFileKanri);
        logMdl.setLogPgId(pgId);
        logMdl.setLogPgName(getPgName(pgId));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(value);
        logMdl.setLogIp(reqMdl__.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);
        if (fileId != null) {
            logMdl.setLogCode("binSid：" + fileId);
        }

        LoggingBiz logBiz = new LoggingBiz(con__);
//        String domain = GroupSession.getResourceManager().getDomain(req);
        logBiz.outPutLog(logMdl, reqMdl__.getDomain());
    }

    /**
     * 更新者がユーザかグループかを判定する
     * <br>[機  能]先頭文字に"G"が有る場合はグループ
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return boolean true:マイグループ false=通常のグループ
     */
    public boolean isEditGroup(String gpSid) {
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
     * 更新者がユーザかグループかを判定する
     * <br>[機  能]先頭文字に"G"が有る場合はグループ
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return boolean true:マイグループ false=通常のグループ
     */
    public int getGroupSid(String gpSid) {

        if (gpSid == null) {
            return 0;
        }
        // 置換対象文字列が存在する場所を取得
        int index = gpSid.indexOf("G");

        // 先頭文字に"G"が有る場合はグループID
        if (index == 0) {

            if (ValidateUtil.isNumber(gpSid.substring("G".length()))) {
                return Integer.valueOf(gpSid.substring("G".length()));
            } else {
                return 0;
            }

        } else {
            return 0;
        }
    }

    /**
    * 更新者を設定する
    * <br>[機  能]
    * <br>[解  説]
    * <br>[備  考]
    * @param usrSid ユーザSID
    * @param egSid グループSID
    * @param con コネクション
    * @return upSid 更新者SID
     * @throws Exception 実行時例外
    */
   public String setUpdateSid(
           int usrSid, int egSid, Connection con) throws Exception {
       String upSid = String.valueOf(usrSid);

       if (egSid > 0) {
           //ユーザが選択グループに所属しているか判定
           GroupBiz gBiz = new GroupBiz();
           if (gBiz.isBelongGroup(usrSid, egSid, con)) {
               upSid = "G" + egSid;
           }
       }

       return upSid;
   }

    /**
     * プログラムIDからプログラム名称を取得する
     * @param id アクションID
     * @return String
     */
    public String getPgName(String id) {
        String ret = new String();

        GsMessage gsMsg = new GsMessage(reqMdl__);

        if (id.equals("jp.groupsession.v2.api.file.add.ApiFileAddAction")) {
            String textTitle = gsMsg.getMessage("fil.58");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.api.file.addfolder.ApiFileAddFolderAction")) {
            String textTitle = gsMsg.getMessage("fil.39");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.api.file.editfolder.ApiFileEditFolderAction")) {
            String textTitle = gsMsg.getMessage("cmn.edit.folder");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.api.file.delete.ApiFileDeleteAction")) {
            String textTitle = gsMsg.getMessage("fil.41");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.api.file.download.ApiFileDownloadAction")) {
            String textTitle = gsMsg.getMessage("fil.42");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.api.file.update.ApiFileUpdateAction")) {
            String textTitle = gsMsg.getMessage("fil.43");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.api.file.lock.ApiFileLockAction")) {
            String textTitle = gsMsg.getMessage("fil.44");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.api.file.rename.ApiFileRenameAction")) {
            String textTitle = gsMsg.getMessage("fil.45");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.api.file.move.ApiFileMoveAction")) {
            String textTitle = gsMsg.getMessage("fil.46");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.api.file.unlock.ApiFileUnlockAction")) {
            String textTitle = gsMsg.getMessage("fil.48");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil010.Fil010Action")) {
            String textTitle = gsMsg.getMessage("fil.49");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil020.Fil020Action")) {
            String textTitle = gsMsg.getMessage("fil.50");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil030.Fil030Action")) {
            String textTitle = gsMsg.getMessage("fil.51");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil030kn.Fil030knAction")) {
            String textTitle = gsMsg.getMessage("fil.52");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil040.Fil040Action")) {
            String textTitle = gsMsg.getMessage("fil.53");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil050.Fil050Action")) {
            String textTitle = gsMsg.getMessage("fil.54");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil060.Fil060Action")) {
            String textTitle = gsMsg.getMessage("fil.55");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil060kn.Fil060knAction")) {
            String textTitle = gsMsg.getMessage("fil.56");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil070.Fil070Action")) {
            String textTitle = gsMsg.getMessage("fil.57");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil080.Fil080Action")) {
            String textTitle = gsMsg.getMessage("fil.58");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil080kn.Fil080knAction")) {
            String textTitle = gsMsg.getMessage("fil.59");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil090.Fil090Action")) {
            String textTitle = gsMsg.getMessage("fil.46");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil090kn.Fil090knAction")) {
            String textTitle = gsMsg.getMessage("fil.46");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil100.Fil100Action")) {
            String textTitle = gsMsg.getMessage("fil.60");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil120kn.Fil120knAction")) {
            String textTitle = gsMsg.getMessage("cmn.display.settings.kn");
            String textKojinSettei = gsMsg.getMessage("cmn.preferences");
            return textKojinSettei + " " + textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil130kn.Fil130knAction")) {
            String textTitle = gsMsg.getMessage("cmn.sml.notification.setting.kn");
            String textKojinSettei = gsMsg.getMessage("cmn.preferences");
            return textKojinSettei + " " + textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil210kn.Fil210knAction")) {
            String textTitle = gsMsg.getMessage("cmn.preferences.kn");
            String textKanriSettei = gsMsg.getMessage("cmn.admin.setting");
            return textKanriSettei + " " + textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil220.Fil220Action")) {
            String textTitle = gsMsg.getMessage("fil.62");
            String textKanriSettei = gsMsg.getMessage("cmn.admin.setting");
            return textKanriSettei + " " + textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.fil230kn.Fil230knAction")) {
            String textTitle = gsMsg.getMessage("fil.100");
            String textKanriSettei = gsMsg.getMessage("cmn.admin.setting");
            return textKanriSettei + " " + textTitle;
        }
        if (id.equals("jp.groupsession.v2.fil.filptl020.Filptl020Action")) {
            String textTitle = gsMsg.getMessage("fil.ptl020.1");
            return textTitle;
        }

        return ret;
    }

    /**
     * <br>[機  能] ファイル管理 管理者設定情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return ファイル管理管理者設定情報
     * @throws SQLException SQL実行例外
     */
    public FileAconfModel getFileAdminData(Connection con) throws SQLException {
        FileAconfDao admDao = new FileAconfDao(con);
        FileAconfModel admData = admDao.select();
        if (admData == null) {
            //取得できなかった場合は初期値を設定する
            admData = new FileAconfModel();
            admData.init();
        }

        return admData;
    }

    /**
     * <br>[機  能] ファイル管理 個人設定情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザID
     * @return ファイル管理個人設定情報
     * @throws SQLException SQL実行例外
     */
    public FileUconfModel getFileUserData(Connection con, int userSid) throws SQLException {
        FileUconfDao uconfDao = new FileUconfDao(con);
        FileUconfModel uconfMdl = uconfDao.select(userSid);
        if (uconfMdl == null || uconfMdl.getUsrSid() <= 0) {
            //取得できなかった場合は初期値を設定する
            uconfMdl = new FileUconfModel();
            uconfMdl.init(userSid);
        }

        return uconfMdl;
    }

    /**
     * <br>[機  能] 個人設定でショートメール通知設定が可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canSetSmailConf(Connection con) throws SQLException {
        FileAconfModel admData = getFileAdminData(con);
        return admData.getFacSmailSendKbn() != GSConstFile.FAC_SMAIL_SEND_KBN_ADMIN;
    }

    /**
     * <br>[機  能] 管理者設定でショートメール通知が許可されているが判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:通知可能 false:通知不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canSendSmail(Connection con) throws SQLException {
        FileAconfModel admData = getFileAdminData(con);
        return admData.getFacSmailSendKbn() != GSConstFile.FAC_SMAIL_SEND_KBN_ADMIN
                || admData.getFacSmailSend() == GSConstFile.FAC_SMAIL_SEND_YES;
    }
//    /**
//     * プログラムIDからプログラム名称を取得する
//     * @param id アクションID
//     * @return String
//     */
//    public String getPgName(String id) {
//        String ret = new String();
//        if (id == null) {
//            return ret;
//        }
//
//        log__.info("プログラムID==>" + id);
//
//        GsMessage gsMsg = new GsMessage(reqMdl__);
//
//        if (id.equals("jp.groupsession.v2.api.file.add.ApiFileAddAction")) {
//            String textTitle = gsMsg.getMessage("fil.58");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.api.file.addfolder.ApiFileAddFolderAction")) {
//            String textTitle = gsMsg.getMessage("fil.39");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.api.file.editfolder.ApiFileEditFolderAction")) {
//            String textTitle = gsMsg.getMessage("cmn.edit.folder");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.api.file.delete.ApiFileDeleteAction")) {
//            String textTitle = gsMsg.getMessage("fil.41");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.api.file.download.ApiFileDownloadAction")) {
//            String textTitle = gsMsg.getMessage("fil.42");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.api.file.update.ApiFileUpdateAction")) {
//            String textTitle = gsMsg.getMessage("fil.43");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.api.file.lock.ApiFileLockAction")) {
//            String textTitle = gsMsg.getMessage("fil.44");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.api.file.rename.ApiFileRenameAction")) {
//            String textTitle = gsMsg.getMessage("fil.45");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.api.file.move.ApiFileMoveAction")) {
//            String textTitle = gsMsg.getMessage("fil.46");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.api.file.unlock.ApiFileUnlockAction")) {
//            String textTitle = gsMsg.getMessage("fil.48");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil010.Fil010Action")) {
//            String textTitle = gsMsg.getMessage("fil.49");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil020.Fil020Action")) {
//            String textTitle = gsMsg.getMessage("fil.50");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil030.Fil030Action")) {
//            String textTitle = gsMsg.getMessage("fil.51");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil030kn.Fil030knAction")) {
//            String textTitle = gsMsg.getMessage("fil.52");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil040.Fil040Action")) {
//            String textTitle = gsMsg.getMessage("fil.53");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil050.Fil050Action")) {
//            String textTitle = gsMsg.getMessage("fil.54");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil060.Fil060Action")) {
//            String textTitle = gsMsg.getMessage("fil.55");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil060kn.Fil060knAction")) {
//            String textTitle = gsMsg.getMessage("fil.56");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil070.Fil070Action")) {
//            String textTitle = gsMsg.getMessage("fil.57");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil080.Fil080Action")) {
//            String textTitle = gsMsg.getMessage("fil.58");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil080kn.Fil080knAction")) {
//            String textTitle = gsMsg.getMessage("fil.59");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil090.Fil090Action")) {
//            String textTitle = gsMsg.getMessage("fil.46");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil090kn.Fil090knAction")) {
//            String textTitle = gsMsg.getMessage("fil.46");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil100.Fil100Action")) {
//            String textTitle = gsMsg.getMessage("fil.60");
//            return textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil120kn.Fil120knAction")) {
//            String textTitle = gsMsg.getMessage("cmn.display.settings.kn");
//            String textKojinSettei = gsMsg.getMessage("cmn.preferences");
//            return textKojinSettei + " " + textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil130kn.Fil130knAction")) {
//            String textTitle = gsMsg.getMessage("cmn.sml.notification.setting.kn");
//            String textKojinSettei = gsMsg.getMessage("cmn.preferences");
//            return textKojinSettei + " " + textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil210kn.Fil210knAction")) {
//            String textTitle = gsMsg.getMessage("cmn.preferences.kn");
//            String textKanriSettei = gsMsg.getMessage("cmn.admin.setting");
//            return textKanriSettei + " " + textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil220.Fil220Action")) {
//            String textTitle = gsMsg.getMessage("fil.62");
//            String textKanriSettei = gsMsg.getMessage("cmn.admin.setting");
//            return textKanriSettei + " " + textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.fil230kn.Fil230knAction")) {
//            String textTitle = gsMsg.getMessage("fil.100");
//            String textKanriSettei = gsMsg.getMessage("cmn.admin.setting");
//            return textKanriSettei + " " + textTitle;
//        }
//        if (id.equals("jp.groupsession.v2.fil.filptl020.Filptl020Action")) {
//            String textTitle = gsMsg.getMessage("fil.ptl020.1");
//            return textTitle;
//        }
//
//        return ret;
//    }

    /**
     *
     * <br>[機  能] ディレクトリのアクセス権限確認し、エラーコードを返す
     * <br>[解  説]
     * <br>[備  考]　0:エラー無し 1:存在しない 2:ディレクトリの閲覧権限が無い
     * @param dirSid ディレクトリSID
     * @return エラーコード　0:エラー無し 1:存在しない 2:キャビネットの閲覧権限が無い
     * @throws SQLException SQL実行時例外
     */
    public int checkPowAccsessDir(int dirSid) throws SQLException {
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);
        if (dirModel == null) {
            return ERR_NOT_EXIST;
        }
        int fcbSid = dirModel.getFcbSid();
        if (fcbSid > 0) {
            //ディレクトリへのアクセス権限があるか判定する。
            if (!isDirAccessAuthUser(fcbSid,
                    dirSid,
                    reqMdl__.getSmodel().getUsrsid(),
                    -1,
                    con__)) {
                return ERR_NONE_CAB_VIEW_POWER;
            }
        }
        return ERR_NONE;
    }
    /**
     *
     * <br>[機  能] ディレクトリの編集可能か確認し、エラーコードを返す
     * <br>[解  説]
     * <br>[備  考]　0:エラー無し 1:存在しない 2:ディレクトリの編集権限が無い 4:ディレクトリ区分の不一致
     * @param dirSid ディレクトリSID
     * @param kbn 0:フォルダとして編集 1:ファイルとして編集 -1:区別しない
     * @return エラーコード　0:エラー無し 1:存在しない 2:キャビネットの閲覧権限が無い 4:ディレクトリ区分の不一致
     * @throws SQLException SQL実行時例外
     */
    public int checkPowEditDir(int dirSid, int kbn) throws SQLException {
        FileDirectoryDao dirDao = new FileDirectoryDao(con__);
        FileDirectoryModel dirModel = dirDao.getNewDirectory(dirSid);
        if (dirModel == null) {
            return ERR_NOT_EXIST;
        }
        int fcbSid = dirModel.getFcbSid();
        //ディレクトリへのアクセス権限があるか判定する。
        if (!isDirAccessAuthUser(fcbSid,
                dirSid,
                reqMdl__.getSmodel().getUsrsid(),
                Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE),
                con__)) {
            return ERR_NONE_CAB_EDIT_POWER;
        }
        if (kbn != -1) {
            if (dirModel.getFdrKbn() != kbn) {
                return ERR_NOT_DIRKBN;
            }
        }
        return ERR_NONE;
    }

    /**
     *
     * <br>[機  能] ディレクトリの編集権限確認し、エラーコードを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param dirSid ディレクトリSID
     * @return エラーコード　0:エラー無し 1:存在しない 3:ディレクトリの編集権限が無い
     * @throws SQLException SQL実行時例外
     */
    public int checkPowEditDir(int dirSid) throws SQLException {
        return checkPowEditDir(dirSid, -1);
    }
    /**
     *
     * <br>[機  能] キャビネットの編集権限確認し、エラーコードを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param fcbSid キャビネットSID
     * @return エラーコード　0:エラー無し 1:存在しない 3:キャビネットの編集権限が無い
     * @throws SQLException SQL実行時例外
     */
    public int checkPowViewCab(int fcbSid) throws SQLException {
        FileCabinetDao cabDao = new FileCabinetDao(con__);
        FileCabinetModel cabMdl = cabDao.select(fcbSid);
        if (cabMdl == null) {
            return ERR_NOT_EXIST;
        }
        if (!isAccessAuthUser(fcbSid, con__)) {
            return ERR_NONE_CAB_VIEW_POWER;
        }
        return ERR_NONE;
    }
    /**
     *
     * <br>[機  能] キャビネットの編集権限確認し、エラーコードを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param fcbSid キャビネットSID
     * @return エラーコード　0:エラー無し 1:存在しない 3:キャビネットの編集権限が無い
     * @throws SQLException SQL実行時例外
     */
    public int checkPowEditCab(int fcbSid) throws SQLException {
        FileCabinetDao cabDao = new FileCabinetDao(con__);
        FileCabinetModel cabMdl = cabDao.select(fcbSid);
        if (cabMdl == null) {
            return ERR_NOT_EXIST;
        }
        if (!isEditCabinetUser(fcbSid, con__)) {
            return ERR_NONE_CAB_EDIT_POWER;
        }
        return ERR_NONE;
    }

    /**
     * <br>[機  能] 指定したバイナリのデータが取得可能かチェックします。
     * <br>[解  説]
     * <br>[備  考]
     * @param fcbSid キャビネットSID
     * @param binSid バイナリSID
     * @param con コネクション
     * @param umodel ユーザモデル
     * @throws SQLException SQL実行時例外
     * @return true:可能  false:不可能
     */
    public boolean isCheckFileIcon(int fcbSid, Long binSid, Connection con, BaseUserModel umodel)
            throws SQLException {

        FileCabinetDao dao = new FileCabinetDao(con);
        boolean iconFlg = dao.isCheckFileIcon(fcbSid, binSid);
        //キャビネットSIDとバイナリSIDの組み合わせがOK且つ
        //キャビネットにアクセス可能である。
        if (iconFlg && isAccessAuthUser(fcbSid, con, umodel)) {
            return true;
        }
        return false;
    }


    /**
     * <br>[機  能] ファイルダウンロード時の集計用データを登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param binSid バイナリSID
     * @param date ダウンロード日時
     * @throws SQLException SQL実行時例外
     */
    public void regFileDownloadLogCnt(
            Connection con, int usrSid, long binSid, UDate date)
                    throws SQLException {

        FileFileBinDao filBinDao = new FileFileBinDao(con__);

        //バイナリSIDの存在チェック
        int count = filBinDao.getBinCount(binSid);
        if (count > 0) {
            //キャビネットSIDを取得
            int fcbSid = filBinDao.getCabinetSid(binSid, false);
            regFileDownloadLogCnt(con, usrSid, fcbSid, binSid, date);
        }
    }

    /**
     * <br>[機  能] ファイルダウンロード時の集計用データを登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param fcbSid ファイルキャビネットSID
     * @param binSid バイナリSID
     * @param date ダウンロード日時
     * @throws SQLException SQL実行時例外
     */
    public void regFileDownloadLogCnt(
            Connection con, int usrSid, int fcbSid, long binSid, UDate date)
                    throws SQLException {

        boolean commitFlg = false;
        con.setAutoCommit(false);
        //ロックの解除処理
        try {
            FileDownloadLogModel mdl = new FileDownloadLogModel();
            mdl.setUsrSid(usrSid);
            mdl.setFcbSid(fcbSid);
            mdl.setBinSid(binSid);
            mdl.setFdlDate(date);
            FileDownloadLogDao dao = new FileDownloadLogDao(con);
            dao.insert(mdl);

            //集計
            __registLogCntSum(con, GSConstFile.FLS_KBN_DOWNLOAD, date);

            commitFlg = true;
        } catch (SQLException e) {
            log__.error("ダウンロード時集計用データの登録に失敗" + e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                con.rollback();
            }
        }

    }

    /**
     * <br>[機  能] ファイルアップロード時の集計用データを登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param grpSid グループID
     * @param fulRegKbn 登録区分  0:新規 1:更新
     * @param fcbSid ファイルキャビネットSID
     * @param binSid バイナリSID
     * @param date アップロード日時
     * @throws SQLException SQL実行時例外
     */
    public void regFileUploadLogCnt(
            Connection con, int usrSid, int grpSid,
            int fulRegKbn, int fcbSid, long binSid, UDate date)
                    throws SQLException {
        FileUploadLogModel mdl = new FileUploadLogModel();
        mdl.setUsrSid(usrSid);
        mdl.setGrpSid(grpSid);
        mdl.setFulRegKbn(fulRegKbn);
        mdl.setFcbSid(fcbSid);
        mdl.setBinSid(binSid);
        mdl.setFulDate(date);
        FileUploadLogDao dao = new FileUploadLogDao(con);
        dao.insert(mdl);

        //集計
        __registLogCntSum(con, GSConstFile.FLS_KBN_UPLOAD, date);
    }

    /**
     * <br>[機  能] ファイル管理 全文検索を使用するか判別する
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションルートパス
     * @return true:使用する  false:使用しない
     */
    public static synchronized boolean isUseAllTextSearch(String appRootPath) {
        return getAllTextSearchUseKbn(appRootPath) == GSConstFile.FIL_ALL_SEARCH_USE_YES;
    }

    /**
     * <br>[機  能] ファイル管理 全文検索の使用区分を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションルートパス
     * @return 0:使用しない  1:使用する
     */
    public static synchronized int getAllTextSearchUseKbn(String appRootPath) {
        return getConfValue(appRootPath,
                GSConstFile.FIL_ALL_SEARCH_USE,
                GSConstFile.FIL_ALL_SEARCH_USE_NO);
    }

    /**
     * <br>[機  能] ファイル管理全文検索の設定ファイルの設定値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションルートパス
     * @param confKey 設定値のキー
     * @param defValue デフォルト値
     * @return 設定値
     */
    public static synchronized int getConfValue(String appRootPath, String confKey, int defValue) {

        String confValue = FilConfigBundle.getValue(confKey);

        if (StringUtil.isNullZeroString(confValue)) {
            try {
                FilConfigBundle.readConfig(appRootPath);
                confValue = FilConfigBundle.getValue(confKey);
            } catch (IOException e) {
                log__.error("ファイル管理設定ファイルの読み込みに失敗", e);
            }
        }

        if (!StringUtil.isNullZeroString(confValue)) {
            return Integer.parseInt(confValue);
        }

        return defValue;
    }

    /**
     * <br>[機  能] ファイル管理集計データ_集計結果を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param flsKbn ログ区分
     * @param date 日時
     * @throws SQLException SQL実行時例外
     */
    private void __registLogCntSum(
            Connection con, int flsKbn, UDate date) throws SQLException {

        FileLogCountSumDao logSumDao = new FileLogCountSumDao(con);
        FileLogCountSumModel logSumMdl = logSumDao.getSumLogCount(flsKbn, date);
        if (logSumMdl != null) {
            if (logSumDao.update(logSumMdl) == 0) {
                logSumDao.insert(logSumMdl);
            }
        }
    }

}