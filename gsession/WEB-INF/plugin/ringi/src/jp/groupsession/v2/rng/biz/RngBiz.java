package jp.groupsession.v2.rng.biz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.lang.ClassUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.LoggingBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnPluginControlDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnLogModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.rng.IRingiListener;
import jp.groupsession.v2.rng.RingiListenerModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.dao.RingiDao;
import jp.groupsession.v2.rng.dao.RngAconfDao;
import jp.groupsession.v2.rng.dao.RngBinDao;
import jp.groupsession.v2.rng.dao.RngChannelDao;
import jp.groupsession.v2.rng.dao.RngRndataDao;
import jp.groupsession.v2.rng.dao.RngTemplateBinDao;
import jp.groupsession.v2.rng.dao.RngTemplateDao;
import jp.groupsession.v2.rng.dao.RngUconfDao;
import jp.groupsession.v2.rng.model.RingiRequestModel;
import jp.groupsession.v2.rng.model.RngAconfModel;
import jp.groupsession.v2.rng.model.RngBinModel;
import jp.groupsession.v2.rng.model.RngChannelModel;
import jp.groupsession.v2.rng.model.RngDeleteModel;
import jp.groupsession.v2.rng.model.RngRndataModel;
import jp.groupsession.v2.rng.model.RngTemplateModel;
import jp.groupsession.v2.rng.model.RngUconfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議プラグインで使用される共通ビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class RngBiz {

    /** Loggingインスタンス */
    private static Log log__ = LogFactory.getLog(RngBiz.class);

    /** 処理モード 申請 */
    public static final int ENTRYMODE_SINSEI = 0;
    /** 処理モード 草稿 */
    public static final int ENTRYMODE_SOUKOU = 1;
    /** 申請モード 申請 */
    public static final int APPLMODE_APPL = 0;
    /** 申請モード 草稿 */
    public static final int APPLMODE_REAPPL = 1;

    /** DBコネクション */
    private Connection con__ = null;
    /** 採番コントローラー */
    private MlCountMtController cntCon__ = null;

    /**
     * <p>コンストラクタ
     * @param con Connection
     */
    public RngBiz(Connection con) {
        con__ = con;
    }

    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param cntCon MlCountMtController
     */
    public RngBiz(Connection con, MlCountMtController cntCon) {
        con__ = con;
        cntCon__ = cntCon;
    }

    /**
     * <br>[機  能] 稟議添付ファイル情報を元に添付ファイルを指定したテンポラリディレクトリに作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rngSid 稟議SID
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param now 現在日時
     * @param reqMdl リクエスト情報
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setRingiTempFileData(Connection con, int rngSid,
                                    String appRoot, String tempDir, UDate now,
                                    RequestModel reqMdl)
    throws SQLException, IOException, IOToolsException, TempFileException {

        setRingiTempFileData(con, rngSid, -1, appRoot, tempDir, now, reqMdl);

    }

    /**
     * <br>[機  能] 稟議添付ファイル情報を元に添付ファイルを指定したテンポラリディレクトリに作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param now 現在日時
     * @param reqMdl リクエスト情報
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setRingiTempFileData(Connection con, int rngSid, int userSid,
                                    String appRoot, String tempDir, UDate now,
                                    RequestModel reqMdl)
    throws SQLException, IOException, IOToolsException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();
        String dateStr = now.getDateString(); //現在日付の文字列(YYYYMMDD)
        RingiDao ringiDao = new RingiDao(con);
        List <CmnBinfModel> rngBinList = ringiDao.getRingiTmpFileList(rngSid, userSid);

        if (rngBinList != null && rngBinList.size() > 0) {
            String[] binSids = new String[rngBinList.size()];

            //バイナリSIDの取得
            for (int i = 0; i < rngBinList.size(); i++) {
                binSids[i] = String.valueOf(rngBinList.get(i).getBinSid());
            }

            //取得したバイナリSIDからバイナリ情報を取得
            List<CmnBinfModel> cmnBinList = cmnBiz.getBinInfo(con, binSids,
                                                            reqMdl.getDomain());

            int fileNum = 1;
            for (CmnBinfModel binData : cmnBinList) {
                cmnBiz.saveTempFile(dateStr, binData, appRoot, tempDir, fileNum);
                fileNum++;
            }
        }
    }

    /**
     * <br>[機  能] 稟議テンプレート添付ファイル情報を元に添付ファイルを指定したテンポラリディレクトリに作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rngTmpSid 稟議テンプレートSID
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param reqMdl リクエスト情報
     * @throws SQLException 実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void setRingiTemplateTempFileData(Connection con, int rngTmpSid,
                                    String appRoot, String tempDir,
                                    RequestModel reqMdl)
    throws SQLException, IOException, IOToolsException, TempFileException {

        UDate now = new UDate();
        String dateStr = now.getDateString(); //現在日付の文字列(YYYYMMDD)
        RingiDao ringiDao = new RingiDao(con);
        List <CmnBinfModel> rngTmpBinList = ringiDao.getRingiTemplateTmpFileList(rngTmpSid);
        CommonBiz cmnBiz = new CommonBiz();

        if (rngTmpBinList != null && rngTmpBinList.size() > 0) {
            String[] binSids = new String[rngTmpBinList.size()];

            //バイナリSIDの取得
            for (int i = 0; i < rngTmpBinList.size(); i++) {
                binSids[i] = String.valueOf(rngTmpBinList.get(i).getBinSid());
            }

            //取得したバイナリSIDからバイナリ情報を取得
            List<CmnBinfModel> cmnBinList = cmnBiz.getBinInfo(con, binSids,
                                                            reqMdl.getDomain());

            int fileNum = 1;
            for (CmnBinfModel binData : cmnBinList) {
                cmnBiz.saveTempFile(dateStr, binData, appRoot, tempDir, fileNum);
                fileNum++;
            }
        }
    }

    /**
     * <br>[機  能] 添付ファイルのダウンロードを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param res レスポンス
     * @param tempDir テンポラリディレクトリ
     * @param fileId 添付ファイルID
     * @throws Exception 添付ファイルのダウンロードに失敗
     */
    public void downloadTempFile(HttpServletRequest req, HttpServletResponse res,
                                String tempDir, String fileId)
    throws Exception {

        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(tempDir, fileId.concat(GSConstCommon.ENDSTR_OBJFILE));
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;
        //添付ファイル保存用のパスを取得する(フルパス)
        String filePath = tempDir + fileId.concat(GSConstCommon.ENDSTR_SAVEFILE);
        filePath = IOTools.replaceFileSep(filePath);
        //ファイルをダウンロードする
        TempFileUtil.downloadAtachment(req, res, filePath, fMdl.getFileName(), Encoding.UTF_8);
    }

    /**
     * <br>[機  能] 稟議の申請を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議申請情報
     * @param pluginConfig プラグイン情報
     * @param smlPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @return 申請した稟議の稟議SID
     * @throws Exception 実行時例外
     */
    public int applyRingi(RingiRequestModel model,
                            PluginConfig pluginConfig, boolean smlPluginUseFlg,
                            RequestModel reqMdl)
    throws Exception {

        return entryRingiData(model,
                            ENTRYMODE_SINSEI, RngConst.RNG_CMDMODE_ADD,
                            pluginConfig, smlPluginUseFlg, reqMdl);
    }

    /**
     * <br>[機  能] 稟議の申請を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議申請情報
     * @param mode 処理モード 0:申請 1:草稿
     * @param cmdMode 登録モード 0:登録 1:更新
     * @param pluginConfig プラグイン情報
     * @param smlPluginUseFlg ショートメールプラグイン有効フラグ
     * @param reqMdl リクエスト情報
     * @return 申請した稟議の稟議SID
     * @throws Exception 実行時例外
     */
    public int entryRingiData(
            RingiRequestModel model,
            int mode,
            int cmdMode,
            PluginConfig pluginConfig,
            boolean smlPluginUseFlg,
            RequestModel reqMdl)
    throws Exception {
        log__.debug("start");

        //稟議情報の作成
        RngRndataModel rngMdl = __createRngData(cntCon__, model, mode, cmdMode);

        //稟議情報の登録
        RngRndataDao rngDao = new RngRndataDao(con__);
        if (cmdMode == RngConst.RNG_CMDMODE_ADD) {
            rngDao.insert(rngMdl);
        } else if (cmdMode == RngConst.RNG_CMDMODE_EDIT) {
            rngDao.update(rngMdl);
        }


        //稟議経路情報の登録
        RngChannelDao channelDao = new RngChannelDao(con__);
        if (cmdMode == RngConst.RNG_CMDMODE_EDIT) {
            //更新の場合は登録済みの経路情報を削除
            channelDao.delete(rngMdl.getRngSid());
        }

        RngChannelModel channelMdl = new RngChannelModel();
        channelMdl.setRngSid(rngMdl.getRngSid());
        channelMdl.setRncComment(null);
        channelMdl.setRncChkdate(null);
        channelMdl.setRncAuid(model.getUserSid());
        channelMdl.setRncAdate(model.getDate());
        channelMdl.setRncEuid(model.getUserSid());
        channelMdl.setRncEdate(model.getDate());
        int rncSort = 1;

        if (mode == ENTRYMODE_SINSEI) {
            //申請時は申請者の経路情報を登録する
            channelMdl.setUsrSid(rngMdl.getRngApplicate());
            channelMdl.setRncSort(0);
            channelMdl.setRncType(RngConst.RNG_RNCTYPE_APPL);
            channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_NOSET);
            channelDao.insert(channelMdl);
        }

        //承認経路の登録
        if (model.getRngapprUser() != null) {
            channelMdl.setRncType(RngConst.RNG_RNCTYPE_APPR);
            for (String apprUserSid : model.getRngapprUser()) {
                channelMdl.setUsrSid(Integer.parseInt(apprUserSid));
                channelMdl.setRncSort(rncSort);
                if (rncSort == 1) {
                    channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_CONFIRM);
                    channelMdl.setRncRcvdate(model.getDate());
                } else {
                    channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_NOSET);
                    channelMdl.setRncRcvdate(null);
                }

                channelDao.insert(channelMdl);
                rncSort++;
            }
        }
        //最終確認者の登録
        if (model.getRngconfirmUser() != null) {
            channelMdl.setRncType(RngConst.RNG_RNCTYPE_CONFIRM);
            channelMdl.setRncStatus(RngConst.RNG_RNCSTATUS_NOSET);
            channelMdl.setRncRcvdate(null);
            for (String confirmUserSid : model.getRngconfirmUser()) {
                channelMdl.setUsrSid(Integer.parseInt(confirmUserSid));
                channelMdl.setRncSort(rncSort);

                channelDao.insert(channelMdl);
                rncSort++;
            }
        }

        //稟議添付情報の登録
        insertRngBin(rngMdl.getRngSid(), model.getUserSid(), model.getDate(),
                model.getAppRootPath(), model.getTempDir(), cmdMode);

        //稟議の申請が行われた場合、稟議リスナーの処理を実行する。
        if (mode == ENTRYMODE_SINSEI) {
            IRingiListener[] listenerList = getRingiListeners(pluginConfig);
            RingiListenerModel listenerMdl = new RingiListenerModel();
            listenerMdl.setCon(con__);
            listenerMdl.setCntCon(cntCon__);
            listenerMdl.setAppRootPath(model.getAppRootPath());
            listenerMdl.setRngSid(rngMdl.getRngSid());
            listenerMdl.setPluginConfig(pluginConfig);
            listenerMdl.setSmailPluginFlg(smlPluginUseFlg);
            String url = createThreadUrl(reqMdl, rngMdl.getRngSid());
            //URLを設定
            listenerMdl.setRngUrl(url);
            //管理者設定ショートメール設定区分を設定
            if (!isCheckRngAconfSmlNot(con__)) {
                RngAconfModel rngAdmMdl = getRngAconf(con__);
                listenerMdl.setSmlNtf(rngAdmMdl.getRarSmlNtf());

                for (IRingiListener listener : listenerList) {
                    listener.doApplyRingi(listenerMdl, reqMdl);
                }
            }
        }

        log__.debug("end");

        return rngMdl.getRngSid();

    }

    /**
     * <br>[機  能] 稟議添付情報の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SIDS
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param cmdMode 処理モード 0:新規登録 1:更新
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void insertRngBin(
                            int rngSid, int userSid, UDate now,
                            String appRootPath, String tempDir, int cmdMode)
    throws SQLException, IOException, IOToolsException, TempFileException {

        insertRngBin(rngSid, userSid, now,
                appRootPath, tempDir, cmdMode, APPLMODE_APPL);
    }

    /**
     * <br>[機  能] 稟議添付情報の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SIDS
     * @param userSid ログインユーザSID
     * @param now システム日付
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param cmdMode 処理モード 0:新規登録 1:更新
     * @param applMode 申請モード 0:申請 1:再申請
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void insertRngBin(
                            int rngSid, int userSid, UDate now,
                            String appRootPath, String tempDir, int cmdMode, int applMode)
    throws SQLException, IOException, IOToolsException, TempFileException {

        //稟議添付情報の登録
        CommonBiz cmnBiz = new CommonBiz();
        RngBinDao binDao = new RngBinDao(con__);
        if (cmdMode == RngConst.RNG_CMDMODE_EDIT) {
            //更新の場合はバイナリー情報の論理削除、稟議添付情報の削除を行う
            RingiDao ringiDao = new RingiDao(con__);
            if (applMode == APPLMODE_APPL) {
                ringiDao.removeRngBinData(rngSid, userSid, now);
                binDao.delete(rngSid);
            } else {
                ringiDao.removeRngBinData(rngSid, 0, userSid, now);
                binDao.delete(rngSid, 0);
            }
        }

        //バイナリー情報の登録
        List < String > binSidList = cmnBiz.insertBinInfo(con__,
                                                    tempDir, appRootPath,
                                                    cntCon__, userSid, now);

        //稟議添付情報の登録
        if (binSidList != null && !binSidList.isEmpty()) {
            RngBinModel binMdl = new RngBinModel();
            binMdl.setRngSid(rngSid);
            binMdl.setUsrSid(0);

            for (String binSid : binSidList) {
                binMdl.setBinSid(Long.parseLong(binSid));
                binDao.insert(binMdl);
            }
        }
    }

    /**
     * <br>[機  能] 稟議情報Modelを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param cntCon MlCountMtController
     * @param model 稟議申請情報
     * @param mode 処理モード 0:申請 1:草稿
     * @param cmdMode 登録モード 0:登録 1:更新
     * @return 稟議情報Model
     * @throws SQLException 稟議SIDの採番に失敗
     */
    private RngRndataModel __createRngData(MlCountMtController cntCon, RingiRequestModel model,
                                            int mode, int cmdMode)
    throws SQLException {
        RngRndataModel rngMdl = new RngRndataModel();

        //稟議SID
        int rngSid = model.getRngSid();
        if (cmdMode == RngConst.RNG_CMDMODE_ADD) {
            rngSid = (int) cntCon.getSaibanNumber(RngConst.SBNSID_RINGI,
                                                RngConst.SBNSID_SUB_RINGI_ID,
                                                model.getUserSid());
        }
        rngMdl.setRngSid(rngSid);

        rngMdl.setRngTitle(model.getRngTitle());
        rngMdl.setRngContent(model.getRngContent());
        rngMdl.setRngMakedate(model.getDate());

        if (mode == ENTRYMODE_SINSEI) {
            rngMdl.setRngApplicate(model.getUserSid());
            rngMdl.setRngAppldate(model.getDate());
            rngMdl.setRngStatus(RngConst.RNG_STATUS_REQUEST);
        } else {
            rngMdl.setRngStatus(RngConst.RNG_STATUS_DRAFT);
        }

        rngMdl.setRngCompflg(0);
        rngMdl.setRngAdmcomment(null);
        rngMdl.setRngAuid(model.getUserSid());
        rngMdl.setRngAdate(model.getDate());
        rngMdl.setRngEuid(model.getUserSid());
        rngMdl.setRngEdate(model.getDate());

        return rngMdl;
    }

    /**
     * <p>稟議リスナー実装クラスのリストを返す
     * @param pluginConfig プラグイン情報
     * @throws ClassNotFoundException 指定された稟議リスナークラスが存在しない
     * @throws IllegalAccessException 稟議リスナー実装クラスのインスタンス生成に失敗
     * @throws InstantiationException 稟議リスナー実装クラスのインスタンス生成に失敗
     * @return バッチリスナー
     */
    public IRingiListener[] getRingiListeners(PluginConfig pluginConfig)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String[] listenerClass = pluginConfig.getListeners(RngConst.RNG_LISTENER_ID);
        IRingiListener[] lis = new IRingiListener[listenerClass.length];
        for (int i = 0; i < listenerClass.length; i++) {
            Object obj = ClassUtil.getObject(listenerClass[i]);
            lis[i] = (IRingiListener) obj;
        }

        return lis;
    }

    /**
     * <br>[機  能] １ページの最大表示件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @return １ページの最大表示件数
     * @throws SQLException SQL実行時例外
     */
    public int getViewCount(Connection con, int userSid) throws SQLException {
        RngUconfModel confMdl = getUConfData(con, userSid);
        return confMdl.getRurViewCnt();
    }

    /**
     * <br>[機  能] 稟議個人情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @return １ページの最大表示件数
     * @throws SQLException SQL実行時例外
     */
    public RngUconfModel getUConfData(Connection con, int userSid) throws SQLException {
        RngUconfDao confDao = new RngUconfDao(con);
        RngUconfModel confMdl = confDao.select(userSid);

        if (confMdl == null) {
            confMdl = new RngUconfModel();
            confMdl.setRurSmlNtf(RngConst.RNG_SMAIL_TSUUCHI);
            confMdl.setRurViewCnt(RngConst.RNG_PAGE_VIEWCNT);
        }

        return confMdl;
    }

    /**
     * <br>[機  能] 選択中のメンバーの順序を1つ繰り上げる
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param upSelectSid コンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 並び替え済みのメンバーリスト
     */
    public String[] getUpMember(String[] upSelectSid, String[] memberSid) {

        if (upSelectSid == null || upSelectSid.length < 1
        || memberSid == null || upSelectSid.length >= memberSid.length) {
            return memberSid;
        }

        ArrayList<String> userList = new ArrayList<String>();
        userList.addAll(Arrays.asList(memberSid));

        for (String userSid : upSelectSid) {
            int index = userList.indexOf(userSid);
            if (index > 0) {
                userList.remove(index);
                userList.add(index - 1, userSid);
            }
        }

        return userList.toArray(new String[userList.size()]);
    }

    /**
     * <br>[機  能] 選択中のメンバーの順序を1つ繰り下げる
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param downSelectSid コンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 並び替え済みのメンバーリスト
     */
    public String[] getDownMember(String[] downSelectSid, String[] memberSid) {

        if (downSelectSid == null || downSelectSid.length < 1
        || memberSid == null || downSelectSid.length >= memberSid.length) {
            return memberSid;
        }

        ArrayList<String> userList = new ArrayList<String>();
        userList.addAll(Arrays.asList(memberSid));
        int lastIndex = userList.size() - 1;

        for (int i = downSelectSid.length - 1; i >= 0; i--) {
            String userSid = downSelectSid[i];

            int index = userList.indexOf(userSid);
            if (index < lastIndex) {
                userList.remove(index);
                userList.add(index + 1, userSid);
            }
        }

        return userList.toArray(new String[userList.size()]);
    }

    /**
     * <br>[機  能] キーワード一覧を作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param keyword キーワード
     * @return キーワード一覧
     */
    public static List<String> createKeywordList(String keyword) {

        if (StringUtil.isNullZeroString(keyword)) {
            return null;
        }

        List <String> keywordList = new ArrayList <String>();
        String searchKey = StringUtil.substitute(keyword, "　", " ");
        StringTokenizer st = new StringTokenizer(searchKey, " ");
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            if (!StringUtil.isNullZeroString(str)) {
                keywordList.add(str);
            }
        }

        return keywordList;
    }

    /**
     * <br>[機  能] ラベルリストを指定した値順に並び替える
     * <br>[解  説]
     * <br>[備  考]
     * @param labelList ラベルリスト
     * @param values 並び順
     * @return 並び替え後のラベルリスト
     */
    public List<LabelValueBean> sortLabelList(List<LabelValueBean> labelList, String[] values) {

        List<LabelValueBean> sortLabelList = new ArrayList<LabelValueBean>();

        if (values == null || values.length <= 0) {
            return labelList;
        }

        for (String value : values) {
            int intValue = Integer.parseInt(value);
            for (LabelValueBean label : labelList) {
                if (intValue == Integer.parseInt(label.getValue())) {
                    sortLabelList.add(label);
                }
            }
        }

        return sortLabelList;
    }

    /**
     * <br>[機  能] 稟議内容確認URLを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param rngSid 稟議SID
     * @return 稟議内容確認URL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    public String createThreadUrl(RequestModel reqMdl, int rngSid)
    throws UnsupportedEncodingException {
        String ringiUrl = null;

        //スレッドのURLを作成
        String rngUrl = reqMdl.getReferer();
        if (!StringUtil.isNullZeroString(rngUrl)) {

            String domain = "";
            String reqDomain = NullDefault.getString(reqMdl.getDomain(), "");
            if (!reqDomain.equals(GSConst.GS_DOMAIN)) {
                domain = reqDomain + "/";
            }

            ringiUrl = rngUrl.substring(0, rngUrl.lastIndexOf("/"));
            ringiUrl = ringiUrl.substring(0, ringiUrl.lastIndexOf("/"));
            ringiUrl += "/common/cmn001.do?url=";

            String paramUrl = reqMdl.getRequestURI();
            paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("/"));

            paramUrl = paramUrl.replace(
                    GSConst.PLUGIN_ID_RINGI, domain + GSConst.PLUGIN_ID_RINGI);

            paramUrl += "/rng030.do";
            paramUrl += "?rngSid=" + rngSid;
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

            ringiUrl += paramUrl;
        }

        return ringiUrl;
    }

    /**
     * <br>[機  能] 稟議内容確認URLを取得する(差し戻し)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param rngSid 稟議SID
     * @return 稟議内容確認URL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    public String createThreadUrlRef(RequestModel reqMdl, int rngSid)
    throws UnsupportedEncodingException {
        String ringiUrl = null;

        //スレッドのURLを作成
        String rngUrl = reqMdl.getReferer();
        if (!StringUtil.isNullZeroString(rngUrl)) {
            ringiUrl = rngUrl.substring(0, rngUrl.lastIndexOf("/"));
            ringiUrl = ringiUrl.substring(0, ringiUrl.lastIndexOf("/"));
            ringiUrl += "/common/cmn001.do?url=";

            String paramUrl = reqMdl.getRequestURI();
            paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("/"));
            paramUrl += "/rng030.do";
            paramUrl += "?CMD=rng030";
            paramUrl += "&rngCmdMode=0";
            paramUrl += "&rngSid=" + rngSid;
            paramUrl += "&rngApprMode=" + RngConst.RNG_APPRMODE_APPL;
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

            ringiUrl += paramUrl;
        }

        return ringiUrl;
    }

    /**
     * 稟議全般のログ出力を行う
     * @param map マップ
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param reqMdl リクエスト情報
     */
    public void outPutLog(
            ActionMapping map,
            String opCode,
            String level,
            String value,
            RequestModel reqMdl) {
        outPutLog(map, opCode, level, value, reqMdl, null);
    }

    /**
     * 稟議全般のログ出力を行う
     * @param map マップ
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param reqMdl リクエスト情報
     * @param fileId 添付ファイルID
     */
    public void outPutLog(
            ActionMapping map,
            String opCode,
            String level,
            String value,
            RequestModel reqMdl,
            String fileId) {

        BaseUserModel usModel = reqMdl.getSmodel();
        int usrSid = -1;
        if (usModel != null) {
            usrSid = usModel.getUsrsid(); //セッションユーザSID
        }

        GsMessage gsMsg = new GsMessage(reqMdl);
        String msg = gsMsg.getMessage("rng.62");

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usrSid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(RngConst.PLUGIN_ID_RINGI);
        logMdl.setLogPluginName(msg);
        String type = map.getType();
        logMdl.setLogPgId(StringUtil.trimRengeString(type, 100));
        logMdl.setLogPgName(getPgName(map.getType()));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(value);
        logMdl.setLogIp(reqMdl.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);
        if (fileId != null) {
            logMdl.setLogCode("binSid：" + fileId);
        }

        LoggingBiz logBiz = new LoggingBiz(con__);
        String domain = reqMdl.getDomain();
        logBiz.outPutLog(logMdl, domain);
    }

    /**
     * プログラムIDからプログラム名称を取得する
     * @param id アクションID
     * @return String
     */
    public String getPgName(String id) {
        String ret = new String();
        if (id == null) {
            return ret;
        }
        log__.info("プログラムID==>" + id);

        GsMessage gsMsg = new GsMessage();
        String logName = "";

        if (id.equals("jp.groupsession.v2.rng.rng010.Rng010Action")) {
            logName = gsMsg.getMessage("rng.94");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng020.Rng020Action")) {
            logName = gsMsg.getMessage("rng.63");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng020kn.Rng020knAction")) {
            logName = gsMsg.getMessage("rng.99");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng030.Rng030Action")) {
            logName = gsMsg.getMessage("rng.104");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng060.Rng060Action")) {
            logName = gsMsg.getMessage("rng.101");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng061.Rng061Action")) {
            logName = gsMsg.getMessage("rng.56");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng070.Rng070Action")) {
            logName = gsMsg.getMessage("rng.73");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng090.Rng090Action")) {
            logName = gsMsg.getMessage("rng.102");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng090kn.Rng090knAction")) {
            logName = gsMsg.getMessage("rng.103");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng110.Rng110Action")) {
            logName = gsMsg.getMessage("rng.76");
            return logName;
        }
        if (id.equals("jp.groupsession.v2.rng.rng120.Rng120Action")) {
            logName = gsMsg.getMessage("rng.77");
            return logName;
        }

        return ret;
    }

    /**
     * <br>[機  能] リスナー情報を作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param rngSid 稟議SID
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @return リスナー情報
     */
    public RingiListenerModel createListenerModel(Connection con,
                                                MlCountMtController cntCon,
                                                int rngSid,
                                                String appRootPath,
                                                PluginConfig pluginConfig,
                                                boolean smailPluginUseFlg) {

        RingiListenerModel listenerMdl = new RingiListenerModel();
        listenerMdl.setCon(con);
        listenerMdl.setCntCon(cntCon);
        listenerMdl.setAppRootPath(appRootPath);
        listenerMdl.setRngSid(rngSid);
        listenerMdl.setPluginConfig(pluginConfig);
        listenerMdl.setSmailPluginFlg(smailPluginUseFlg);

        return listenerMdl;
    }

    /**
     * <br>[機  能] 稟議プラグインが指定されたグループ/ユーザのみ使用可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true: グループ/ユーザのみ使用可能 false: 制限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isPluginMemberControl(Connection con) throws SQLException {
        boolean rngControl = false;
        CmnPluginControlDao pcontrolDao = new CmnPluginControlDao(con);
        CmnPluginControlModel pcontrolMdl = pcontrolDao.select(RngConst.PLUGIN_ID_RINGI);
        rngControl = (pcontrolMdl != null && pcontrolMdl.getPctKbn() == GSConstMain.PCT_KBN_MEMBER);

        return rngControl;
    }

    /**
     * <br>[機  能] ユーザ情報一覧を指定されたユーザSID一覧の順に並び替える
     * <br>[解  説]
     * <br>[備  考]
     * @param userList ユーザ情報一覧
     * @param userSidList ユーザSID一覧
     * @return ユーザ情報一覧
     */
    public List<CmnUsrmInfModel> sortUserList(List<CmnUsrmInfModel> userList,
                                                String[] userSidList) {

        if (userList == null || userList.isEmpty()) {
            return userList;
        }

        //経路順に並び替える
        List<CmnUsrmInfModel> sortUserList = new ArrayList<CmnUsrmInfModel>(userList.size());

        for (String userSid : userSidList) {
            for (CmnUsrmInfModel userMdl : userList) {
                if (Integer.parseInt(userSid) == userMdl.getUsrSid()) {
                    sortUserList.add(userMdl);
                }
            }
        }

        return sortUserList;
    }

    /**
     * <br>[機  能] 削除設定画面の年コンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return 年コンボ
     */
    public static List<LabelValueBean> createDelYearCombo(RequestModel reqMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl);
        //年ラベル
        ArrayList<LabelValueBean> yearCombo = new ArrayList<LabelValueBean>();
        for (int year = 0; year < RngConst.LIST_YEAR_KEY_ALL.length; year++) {
            yearCombo.add(new LabelValueBean(
                    gsMsg.getMessage("cmn.year", new String[] {String.valueOf(year)}),
                                    String.valueOf(year)));
        }

        return yearCombo;
    }

    /**
     * <br>[機  能] 削除設定画面の月コンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return 月コンボ
     */
    public static List<LabelValueBean> createDelMonthCombo(RequestModel reqMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl);
        ArrayList<LabelValueBean> monthCombo = new ArrayList<LabelValueBean>();
        for (int month = RngConst.DEL_MONTH_START;
            month <= RngConst.DEL_MONTH_END; month++) {

            monthCombo.add(new LabelValueBean(
                    gsMsg.getMessage("cmn.months", new String[] {String.valueOf(month)}),
                                            String.valueOf(month)));
        }

        return monthCombo;
    }

    /**
     * <br>[機  能] 削除設定画面の日コンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return 日コンボ
     */
    public static List<LabelValueBean> createDelDayCombo(RequestModel reqMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl);
        ArrayList<LabelValueBean> dayCombo = new ArrayList<LabelValueBean>();
        for (int day = RngConst.DEL_DAY_START;
            day <= RngConst.DEL_DAY_END; day++) {

            dayCombo.add(new LabelValueBean(day + gsMsg.getMessage("cmn.day"),
                                            String.valueOf(day)));
        }

        return dayCombo;
    }

    /**
     * <br>[機  能] 指定した削除条件に従い稟議の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param delList 削除条件
     * @param userSid 削除ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void deleteRngData(Connection con, List<RngDeleteModel> delList, int userSid)
    throws SQLException {
        //削除対象が存在しない場合、処理を終了する
        if (delList == null || delList.isEmpty()) {
            return;
        }

        //削除対象の稟議を取得する
        RingiDao rngDao = new RingiDao(con);
        for (RngDeleteModel delMdl : delList) {
            List<Integer> delRngList = rngDao.getUpdateRingilList(delMdl);
            for (int rngSid : delRngList) {
                deleteRngData(con, rngSid, userSid);
            }
        }
    }

    /**
     * <br>[機  能] 指定した稟議(関連情報含む)の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rngSid 稟議SID
     * @param userSid 削除ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void deleteRngData(Connection con, int rngSid, int userSid)
    throws SQLException {
        UDate now = new UDate();

        //稟議情報の削除
        RngRndataDao rngDao = new RngRndataDao(con);
        rngDao.delete(rngSid);

        //稟議経路情報の削除
        RngChannelDao channelDao = new RngChannelDao(con);
        channelDao.delete(rngSid);

        //バイナリー情報の論理削除
        RingiDao ringiDao = new RingiDao(con);
        ringiDao.removeRngBinData(rngSid, userSid, now);

        //稟議添付情報の削除
        RngBinDao binDao = new RngBinDao(con);
        binDao.delete(rngSid);
    }

    /**
     * <br>[機  能] 稟議 管理者設定を取得する
     * <br>[解  説] 管理者設定が未登録の場合、初期値を返す
     * <br>[備  考]
     * @param con コネクション
     * @return 稟議 管理者設定
     * @throws SQLException SQL実行時例外
     */
    public RngAconfModel getRngAconf(Connection con) throws SQLException {
        RngAconfModel aconfMdl = null;

        RngAconfDao aconfDao = new RngAconfDao(con);
        List<RngAconfModel> aconfList = aconfDao.select();
        if (aconfList != null && !aconfList.isEmpty()) {
            aconfMdl = aconfList.get(0);
        } else {
            aconfMdl = new RngAconfModel();
            aconfMdl.setRarDelAuth(RngConst.RAR_DEL_AUTH_UNRESTRICTED);
            aconfMdl.setRarSmlNtf(RngConst.RAR_SML_NTF_USER);
        }

        return aconfMdl;
    }

    /**
     * <br>[機  能] 稟議 管理者設定でショートメール「通知しない」に設定されているかチェックする
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return true:設定されている false:設定されていない
     * @throws SQLException SQL実行例外
     */
    public boolean isCheckRngAconfSmlNot(Connection con) throws SQLException {

        RngBiz biz = new RngBiz(con);
        RngAconfModel mdl = biz.getRngAconf(con);

        if (mdl.getRarSmlNtf() == RngConst.RAR_SML_NTF_ADMIN
                && mdl.getRarSmlNtfKbn() == RngConst.RAR_SML_NTF_KBN_NO) {
            return true;
        }

        return false;
    }


    /**
     * <br>[機  能] 申請された稟議の添付ファイルをダウンロード可能かチェックする
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rngSid 稟議SID
     * @param usrSid ユーザSID
     * @param admin 管理者フラグ
     * @param binSid バイナリSID
     * @return true:可  false:不可
     * @throws SQLException SQL実行時例外
     */
    public boolean isCheckDLRngTemp(
            Connection con, int rngSid, int usrSid, boolean admin, Long binSid)
            throws SQLException {

        RngBinDao dao = new RngBinDao(con);

        //管理者ユーザの場合は経路内ユーザチェックはせず、
        //指定稟議内のバイナリSIDかチェックのみを行う
        if (admin && dao.isCheckRngTemp(rngSid, binSid)) {
            return true;
        }

        //稟議経路内の受信済みユーザSIDを取得する
        ArrayList<Integer> receiveUsrSids = getReceiveUserSids(con, rngSid);
        boolean userFlg = false;
        for (Integer sid : receiveUsrSids) {
            if (usrSid == sid) {
                userFlg = true;
                break;
            }
        }

        if (!userFlg) {
            return false;
        }

        //稟議経路内の受信済みユーザ 且つ バイナリSIDが指定稟議の添付ファイルのもの
        if (userFlg && dao.isCheckRngTemp(rngSid, binSid)) {
            return true;
        }

        return false;
    }

    /**
     * <br>[機  能] 指定された稟議の経路内で稟議閲覧可能（受信済み）ユーザ一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rngSid 稟議SID
     * @return ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<Integer> getReceiveUserSids(Connection con, int rngSid) throws SQLException {
        RngChannelDao chDao = new RngChannelDao(con);
        return chDao.getReceiveUserSids(rngSid);
    }

    /**
     * <br>[機  能] 稟議テンプレートの添付ファイルをダウンロード可能かチェックする
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param tplSid テンプレートSID
     * @param binSid バイナリSID
     * @param usrSid ユーザSID
     * @return true:可  false:不可
     * @throws SQLException SQL実行時例外
     */
    public boolean isCheckDLTemplateTemp(Connection con, int tplSid, Long binSid, int usrSid)
            throws SQLException {

        RngTemplateBinDao binDao = new RngTemplateBinDao(con);
        //バイナリSIDがテンプレート用のバイナリSIDかチェックする
        if (!binDao.isCheckRngTemplateBin(tplSid, binSid)) {
            return false;
        }

        RngTemplateDao tempDao = new RngTemplateDao(con);
        RngTemplateModel tmpMdl = tempDao.select(tplSid);

        if (tmpMdl != null) {
            //共通テンプレートの添付ファイルであれば誰でも取得可能
            if (tmpMdl.getRtpType() == RngConst.RNG_TEMPLATE_SHARE) {
                return true;

            //個人テンプレートの添付ファイルは本人のみ取得可能
            } else if (tmpMdl.getRtpType() == RngConst.RNG_TEMPLATE_PRIVATE) {
                if (tmpMdl.getUsrSid() == usrSid) {
                    return true;
                }
            }
        }

        return false;
    }
}
