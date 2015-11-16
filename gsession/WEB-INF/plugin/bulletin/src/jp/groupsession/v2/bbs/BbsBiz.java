package jp.groupsession.v2.bbs;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.groupsession.v2.bbs.bbs080.Bbs080Biz;
import jp.groupsession.v2.bbs.dao.BbsAdmConfDao;
import jp.groupsession.v2.bbs.dao.BbsForAdminDao;
import jp.groupsession.v2.bbs.dao.BbsForInfDao;
import jp.groupsession.v2.bbs.dao.BbsForMemDao;
import jp.groupsession.v2.bbs.dao.BbsForSumDao;
import jp.groupsession.v2.bbs.dao.BbsLogCountSumDao;
import jp.groupsession.v2.bbs.dao.BbsThreInfDao;
import jp.groupsession.v2.bbs.dao.BbsThreSumDao;
import jp.groupsession.v2.bbs.dao.BbsThreViewDao;
import jp.groupsession.v2.bbs.dao.BbsUserDao;
import jp.groupsession.v2.bbs.dao.BbsViewLogCountDao;
import jp.groupsession.v2.bbs.dao.BbsWriteInfDao;
import jp.groupsession.v2.bbs.dao.BbsWriteLogCountDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsAdmConfModel;
import jp.groupsession.v2.bbs.model.BbsForAdminModel;
import jp.groupsession.v2.bbs.model.BbsForInfModel;
import jp.groupsession.v2.bbs.model.BbsForMemModel;
import jp.groupsession.v2.bbs.model.BbsForSumModel;
import jp.groupsession.v2.bbs.model.BbsLogCountSumModel;
import jp.groupsession.v2.bbs.model.BbsThreInfModel;
import jp.groupsession.v2.bbs.model.BbsThreSumModel;
import jp.groupsession.v2.bbs.model.BbsThreViewModel;
import jp.groupsession.v2.bbs.model.BbsUserModel;
import jp.groupsession.v2.bbs.model.BbsViewLogCountModel;
import jp.groupsession.v2.bbs.model.BbsWriteInfModel;
import jp.groupsession.v2.bbs.model.BbsWriteLogCountModel;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.bbs.model.BulletinForumDiskModel;
import jp.groupsession.v2.bbs.model.BulletinSmlModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.LoggingBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnLogModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.SmlSender;
import jp.groupsession.v2.sml.model.SmlSenderModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板共通ビジネスロジッククラス
 * <br>[機  能]
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class BbsBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(BbsBiz.class);

    /** DBコネクション */
    private Connection con__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public BbsBiz() {
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     */
    public BbsBiz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 掲示板個人設定情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userId ユーザID
     * @return 掲示板個人設定情報
     * @throws SQLException SQL実行例外
     */
    public BbsUserModel getBbsUserData(Connection con, int userId) throws SQLException {
        BbsUserDao bUserDao = new BbsUserDao(con);
        BbsUserModel buMdl = bUserDao.select(userId);
        if (buMdl == null || buMdl.getUsrSid() <= 0) {
            //取得できなかった場合は初期値を設定する
            buMdl = new BbsUserModel();
            buMdl.setBurForCnt(10);
            buMdl.setBurThreCnt(10);
            buMdl.setBurWrtCnt(50);
            buMdl.setBurNewCnt(1);
            buMdl.setBurSmlNtf(GSConstBulletin.BBS_SMAIL_TSUUCHI);
            buMdl.setBurThreMainCnt(10);
            buMdl.setBurWrtlistOrder(GSConstBulletin.BBS_WRTLIST_ORDER_ASC);
            buMdl.setBurThreImage(GSConstBulletin.BBS_IMAGE_DSP);
            buMdl.setBurMainChkedDsp(1);
            buMdl.setBurSubNewThre(0);
            buMdl.setBurSubForum(0);
            buMdl.setBurSubUnchkThre(0);
            buMdl.setBurTempImage(0);
        }

        return buMdl;
    }
    /**
     * <br>[機  能] 掲示板管理者設定情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param sessionUserId セッションユーザSID
     * @return 掲示板管理者設定情報
     * @throws SQLException SQL実行例外
     */
    public BbsAdmConfModel getBbsAdminData(Connection con, int sessionUserId) throws SQLException {
        BbsAdmConfDao admDao = new BbsAdmConfDao(con);
        BbsAdmConfModel baMdl = admDao.select();
        if (baMdl == null) {
            //取得できなかった場合は初期値を設定する
            baMdl = new BbsAdmConfModel();
            baMdl.setBacAtdelFlg(GSConstBulletin.AUTO_DELETE_OFF);
            baMdl.setBacAtdelY(-1);
            baMdl.setBacAtdelM(-1);
            UDate now = new UDate();
            baMdl.setBacAdate(now);
            baMdl.setBacAuid(sessionUserId);
            baMdl.setBacEdate(now);
            baMdl.setBacEuid(sessionUserId);
            baMdl.setBacSmlNtf(GSConstBulletin.BAC_SML_NTF_USER);
            baMdl.setBacSmlNtfKbn(GSConstBulletin.BAC_SML_NTF_KBN_YES);

            boolean commit = false;
            try {
                admDao.insert(baMdl);
                commit = true;
            } catch (SQLException e) {
                log__.error("掲示板-管理者設定の登録に失敗しました");
                throw e;
            } finally {
                if (commit) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }
        }

        return baMdl;
    }
    /**
     * <br>[機  能] ユーザが指定されたフォーラムの編集権限か閲覧権限があるか判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @return フォーラム情報
     * @throws SQLException SQL実行例外
     */
    public boolean checkForumAuth(Connection con, int bfiSid, int userSid)
    throws SQLException {

        BbsForMemDao bbsMemDao = new BbsForMemDao(con);
        return bbsMemDao.existBbsForMem(bfiSid, userSid);

    }

    /**
     * <br>[機  能] ユーザが指定されたフォーラムアクセス権限区分を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @return フォーラム権限 権限無し=-1
     * @throws SQLException SQL実行例外
     */
    public int getForumAuth(Connection con, int bfiSid, int userSid)
    throws SQLException {

        int forAuth = -1;
        BbsForMemDao bbsMemDao = new BbsForMemDao(con);
        List<BbsForMemModel> memList = bbsMemDao.getAuthForMem(bfiSid, userSid);
        if (memList != null && memList.size() > 0) {
            for (BbsForMemModel model : memList) {
                if (model.getBfmAuth() == GSConstBulletin.ACCESS_KBN_WRITE) {
                    forAuth = GSConstBulletin.ACCESS_KBN_WRITE;
                    break;
                } else {
                    forAuth = GSConstBulletin.ACCESS_KBN_READ;
                }
            }
        }

        return forAuth;

    }

    /**
     * <br>[機  能] フォーラム閲覧権限があるか判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @return フォーラム権限 true:閲覧権限有 false:閲覧権限無
     * @throws SQLException SQL実行例外
     */
    public boolean isForumAccessAuth(Connection con, int bfiSid, int userSid)
    throws SQLException {

        //フォーラム管理者か判定する
        if (isForumAdmin(bfiSid, userSid, con)) {
            return true;
        }
        //フォーラムメンバーか判定する
        if (checkForumAuth(con, bfiSid, userSid)) {
            return true;
        }
        return false;
    }

    /**
     * <br>[機  能] フォーラム編集権限があるか判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @return フォーラム権限 true:閲覧権限有 false:閲覧権限無
     * @throws SQLException SQL実行例外
     */
    public boolean isForumEditAuth(Connection con, int bfiSid, int userSid)
    throws SQLException {

        //フォーラム管理者か判定する
        if (isForumAdmin(bfiSid, userSid, con)) {
            return true;
        }
        //フォーラムメンバーか判定する
        if (getForumAuth(con, bfiSid, userSid) == GSConstBulletin.ACCESS_KBN_WRITE) {
            return true;
        }
        return false;
    }


    /**
     * <br>[機  能] フォーラム情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @return フォーラム情報
     * @throws SQLException SQL実行例外
     */
    public BulletinDspModel getForumData(Connection con, int bfiSid)
    throws SQLException {

        BulletinDao btDao = new BulletinDao(con);
        BulletinDspModel btMdl = btDao.getForumData(bfiSid);

        return btMdl;
    }

    /**
     * <br>[機  能] 指定されたフォーラムに掲示予定のスレッドがあるかチェックします。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param now 現在日時
     * @return true:ある  false:なし
     * @throws SQLException SQL実行時例外
     */
    public boolean isCheckRsvThreadData(Connection con, int bfiSid, UDate now)
            throws SQLException {

        int cnt = 0;

        BulletinDao btDao = new BulletinDao(con);
        cnt = btDao.countRsvThreData(bfiSid, -1, true, now);

        if (0 < cnt) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <br>[機  能] 指定されたフォーラムにユーザが編集可能な
     *                    掲示予定のスレッドがあるかチェックします。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param usrSid ユーザSID
     * @param now 現在日時
     * @return true:ある  false:なし
     * @throws SQLException SQL実行時例外
     */
    public boolean isCheckRsvThreadEditData(Connection con, int bfiSid, int usrSid, UDate now)
            throws SQLException {

        int cnt = 0;

        BulletinDao btDao = new BulletinDao(con);
        cnt = btDao.countRsvThreData(bfiSid, usrSid, false, now);

        if (0 < cnt) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <br>[機  能] スレッド情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param btiSid スレッドSID
     * @return スレッド情報
     * @throws SQLException SQL実行例外
     */
    public BulletinDspModel getThreadData(Connection con, int btiSid)
    throws SQLException {

        BulletinDao btDao = new BulletinDao(con);
        BulletinDspModel btMdl = btDao.getThreadData(btiSid);

        return btMdl;
    }

    /**
     * <br>[機  能] 投稿情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bwiSid 投稿SID
     * @return 投稿情報
     * @throws SQLException SQL実行例外
     */
    public BulletinDspModel getWriteData(Connection con, int bwiSid)
    throws SQLException {

        BulletinDao bbsDao = new BulletinDao(con);
        BulletinDspModel bbsWriteMdl = bbsDao.getWriteData(bwiSid);

        return bbsWriteMdl;
    }


    /**
     * <br>[機  能] フォーラム集計情報の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @param now システム日付
     * @throws SQLException SQL実行時例外
     */
    public void updateBbsForSum(Connection con, int bfiSid, int userSid, UDate now)
    throws SQLException {
        updateBbsForSum(con, bfiSid, userSid, now, true);
    }

    /**
     * <br>[機  能] フォーラム集計情報の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @param now システム日付
     * @param updWriteDate 最終書き込み日時の更新 true:更新する false:更新しない
     * @throws SQLException SQL実行時例外
     */
    public void updateBbsForSum(Connection con, int bfiSid, int userSid, UDate now,
                                boolean updWriteDate)
    throws SQLException {

        //スレッド数をカウント
        BulletinDao bbsDao = new BulletinDao(con);
        int bfsThreCnt = bbsDao.getThreadCount(bfiSid, now);

        //投稿数をカウント
        BbsWriteInfDao bbsWriteInfDao = new BbsWriteInfDao(con);
        int bfsWrtCnt = bbsWriteInfDao.getWriteCountInForum(bfiSid, now);

        //スレッド容量を集計
        BbsThreSumDao threSumDao = new BbsThreSumDao(con);
        long bfsSize = threSumDao.getTotalThreadSize(bfiSid);

        BbsForSumModel bbsForSumMdl = new BbsForSumModel();
        bbsForSumMdl.setBfiSid(bfiSid);
        bbsForSumMdl.setBfsThreCnt(bfsThreCnt);
        bbsForSumMdl.setBfsWrtCnt(bfsWrtCnt);
        bbsForSumMdl.setBfsWrtDate(now);
        bbsForSumMdl.setBfsEuid(userSid);
        bbsForSumMdl.setBfsEdate(now);
        bbsForSumMdl.setBfsSize(bfsSize);

        BbsForSumDao bbsForSumDao = new BbsForSumDao(con);
        bbsForSumDao.update(bbsForSumMdl, updWriteDate);
    }

    /**
     * <br>[機  能] スレッド集計情報の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param btiSid スレッドSID
     * @param userSid ユーザSID
     * @param now システム日付
     * @param updWriteDate 最終書き込み日時の更新 true:更新する false:更新しない
     * @throws SQLException SQL実行時例外
     * @throws UnsupportedEncodingException スレッド情報のエンコードに失敗
     */
    public void updateBbsThreSum(Connection con, int btiSid, int userSid, UDate now,
                                boolean updWriteDate)
    throws SQLException, UnsupportedEncodingException {

        //投稿数をカウント
        BulletinDao bbsDao = new BulletinDao(con);
        int btsWrtCnt = bbsDao.getWriteCount(btiSid);

        BbsThreSumModel bbsThreSumMdl = new BbsThreSumModel();
        bbsThreSumMdl.setBtiSid(btiSid);
        bbsThreSumMdl.setBtsWrtCnt(btsWrtCnt);
        bbsThreSumMdl.setBtsWrtDate(now);
        bbsThreSumMdl.setBtsEuid(userSid);
        bbsThreSumMdl.setBtsEdate(now);
        bbsThreSumMdl.setBtsSize(getThreadDiskSize(con, btiSid));

        BbsThreSumDao bbsThreSumDao = new BbsThreSumDao(con);
        bbsThreSumDao.updateThreSumData(bbsThreSumMdl, updWriteDate);
    }

    /**
     * <br>[機  能] 添付ファイルのダウンロードを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param res レスポンス
     * @param tempDir テンポラリディレクトリ
     * @param fileId 添付ファイルID
     * @return fileName ファイル名
     * @throws Exception 添付ファイルのダウンロードに失敗
     */
    public String downloadTempFile(HttpServletRequest req, HttpServletResponse res,
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
        return fMdl.getFileName();
    }

    /**
     * <br>[機  能] ショートメールで更新通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param bsmlModel 投稿内容(ショートメール送信用)
     * @param appRootPath アプリケーションのルートパス
     * @param pluginConfig PluginConfig
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void sendSmail(
        Connection con,
        MlCountMtController cntCon,
        BulletinSmlModel bsmlModel,
        String appRootPath,
        PluginConfig pluginConfig,
        RequestModel reqMdl) throws Exception {

        //フォーラムSID
        int bfiSid = bsmlModel.getBfiSid();
        //フォーラム名
        BbsBiz bbsBiz = new BbsBiz();
        BulletinDspModel btDspMdl = bbsBiz.getForumData(con, bfiSid);
        String fname = btDspMdl.getBfiName();
        //タイトル
        String thtitle = bsmlModel.getBtiTitle();
        //投稿者名
        String tname = "";
        int grpSid = bsmlModel.getGrpSid();
        if (grpSid > 0) {
            CmnGroupmDao grpDao = new CmnGroupmDao(con);
            tname = grpDao.select(grpSid).getGrpName();
        } else {
            int userSid = bsmlModel.getUserSid();
            CmnUsrmInfDao udao = new CmnUsrmInfDao(con);
            CmnUsrmInfModel umodel = udao.select(userSid);
            tname = umodel.getUsiSei() + " " + umodel.getUsiMei();
        }
        //投稿日時
        UDate ud = bsmlModel.getBwiAdate();
        String tdate = UDateUtil.getSlashYYMD(ud) + " "
        + UDateUtil.getSeparateHMS(ud);
        //添付ファイル
        List<LabelValueBean> files = bsmlModel.getFileLabelList();
        StringBuilder fileLine = new StringBuilder();
        for (LabelValueBean file : files) {
            String fileName = file.getLabel();
            if (fileLine.length() != 0) {
                fileLine.append(" , ");
            }
            fileLine.append(fileName);
        }

        //投稿内容
        String body = bsmlModel.getBwiValue();
        //宛先(通知を受けるに設定されたユーザSIDを取得する。)
        BbsUserDao buDao = new BbsUserDao(con);
        List<Integer> sidList
            = buDao.getSendSmailList(bfiSid, bbsBiz.canSetSmailConf(con, bsmlModel.getUserSid()));

        //本文
        String tmpPath = getSmlTemplateFilePath(appRootPath); //テンプレートファイルパス取得
        String tmpBody = IOTools.readText(tmpPath, Encoding.UTF_8);
        Map<String, String> map = new HashMap<String, String>();
        map.put("FNAME", fname);
        map.put("TITLE", thtitle);
        map.put("UNAME", tname);
        map.put("DATE", tdate);
        map.put("FILES", fileLine.toString());
        map.put("BODY", body);
        map.put("URL", bsmlModel.getThreadUrl());

        String bodyml = StringUtil.merge(tmpBody, map);
        GsMessage gsMsg = new GsMessage(reqMdl);
        if (bodyml.length() > GSConstCommon.MAX_LENGTH_SMLBODY) {

            String textMessage = gsMsg.getMessage("cmn.mail.omit");

            bodyml = textMessage + "\r\n\r\n" + bodyml;

            bodyml = bodyml.substring(0, GSConstCommon.MAX_LENGTH_SMLBODY);

        }

        //ショートメール送信用モデルを作成する。
        //
        SmlSenderModel smlModel = new SmlSenderModel();
        //送信者(システムメールを指定)
        smlModel.setSendUsid(GSConst.SYSTEM_USER_MAIL);
        //TO
        smlModel.setSendToUsrSidArray(sidList);
        //タイトル
        String title = gsMsg.getMessage("bbs.29") + " " + thtitle;

        title = StringUtil.trimRengeString(title,
                GSConstCommon.MAX_LENGTH_SMLTITLE);
        smlModel.setSendTitle(title);

        //本文
        smlModel.setSendBody(bodyml);
        //メール形式
        smlModel.setSendType(GSConstSmail.SAC_SEND_MAILTYPE_NORMAL);
        //マーク
        smlModel.setSendMark(GSConstSmail.MARK_KBN_NONE);

        //メール送信処理開始
        SmlSender sender = new SmlSender(con, cntCon, smlModel, pluginConfig, appRootPath, reqMdl);
        sender.execute();
    }

    /**
     * <br>[機  能] アプリケーションのルートパスから更新通知メールのテンプレートパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションのルートパス
     * @return テンプレートファイルのパス文字列
     */
    public String getSmlTemplateFilePath(String appRootPath) {
        //WEBアプリケーションのパス
        appRootPath = IOTools.setEndPathChar(appRootPath);
        String ret = IOTools.replaceSlashFileSep(appRootPath
                + "/WEB-INF/plugin/bulletin/smail/koushin_tsuuchi.txt");
        return ret;
    }

    /**
     * <br>[機  能] 基準日以前の掲示板情報を削除します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param delDate 削除対象の基準日
     * @return int 削除件数
     * @throws Exception 掲示板情報バッチ削除に失敗
     */
    public int deleteOldBulletin(Connection con, UDate delDate) throws Exception {
        int ret = 0;
        log__.debug("基準日以前の掲示板情報を削除します。");
        BbsThreInfDao infDao = new BbsThreInfDao(con);
        //削除対象のスレッド情報を取得

        ArrayList<BbsThreInfModel> infList = infDao.getOldThreData(delDate);
        Bbs080Biz biz = new Bbs080Biz();
        for (BbsThreInfModel infMdl : infList) {
            biz.deleteThreadData(infMdl.getBfiSid(), infMdl.getBtiSid(), con, -1);
        }

        return ret;
    }

    /**
     * <br>[機  能] 掲示板閲覧情報を削除します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param btiSid 削除スレッド
     * @throws Exception 掲示板情報バッチ削除に失敗
     */
    public void deleteThreadView(Connection con, int btiSid) throws Exception {

        //削除情報を取得する。
        BbsThreViewDao bbsThreViewDao = new BbsThreViewDao(con);
        List<BbsThreViewModel> allDelList = bbsThreViewDao.getThreadView(btiSid);

        //削除する。
        if (allDelList == null || allDelList.size() < 1) {
            return;
        }

        int i = 0;
        int delCount = GSConstBulletin.BBS_BATCH_DELETE_COUNT;
        List<BbsThreViewModel> deleteList = new ArrayList<BbsThreViewModel>();
        for (BbsThreViewModel model : allDelList) {

            deleteList.add(model);
            i++;

            if (i >= delCount) {

                //物理削除する。
                bbsThreViewDao.delete(deleteList);

                deleteList = new ArrayList<BbsThreViewModel>();
                i = 0;
            }
        }

        if (deleteList != null && deleteList.size() > 0) {
            //物理削除する。
            bbsThreViewDao.delete(deleteList);
        }
    }


    /**
     * <br>[機  能] 掲示期限を過ぎた掲示板情報を削除します。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return int 削除件数
     * @throws Exception 掲示板情報バッチ削除に失敗
     */
    public int deleteOverLimitBulletin(Connection con) throws Exception {
        int ret = 0;
        log__.debug("掲示期限を過ぎた掲示板情報を削除します。");
        BbsThreInfDao infDao = new BbsThreInfDao(con);

        //削除対象のスレッド情報を取得
        UDate now = new UDate();
        now.setZeroHhMmSs();

        BbsForInfDao forDao = new BbsForInfDao(con);
        List<BbsForInfModel> forList = forDao.select();
        ArrayList<BbsThreInfModel> infList = new ArrayList<BbsThreInfModel>();

        //フォーラムごとに保存期間を
        for (BbsForInfModel forMdl : forList) {
            UDate delDate = now.cloneUDate();

            //保存期間設定する
            if (forMdl.getBfiKeep() == GSConstBulletin.THREAD_KEEP_YES) {
                int year = forMdl.getBfiKeepDateY();
                int month = forMdl.getBfiKeepDateM();
                delDate.addYear(-year);
                delDate.addMonth(-month);
            }
            infList.addAll(infDao.getOverLimitThreData(forMdl.getBfiSid(), delDate));
        }
//        ArrayList<BbsThreInfModel> infList = infDao.getOverLimitThreData(delDate);

        Bbs080Biz biz = new Bbs080Biz();
        for (BbsThreInfModel infMdl : infList) {
            biz.deleteThreadData(infMdl.getBfiSid(), infMdl.getBtiSid(), con, -1);
        }

        return ret;
    }

    /**
     * <br>[機  能] スレッドURLを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param bfiSid フォーラムSID
     * @param btiSid スレッドSID
     * @return スレッドURL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    public String createThreadUrl(RequestModel reqMdl, int bfiSid, int btiSid)
    throws UnsupportedEncodingException {

        String threadUrl = null;

        //スレッドのURLを作成
        String url = reqMdl.getReferer();

        if (!StringUtil.isNullZeroString(url)) {

            threadUrl = url.substring(0, url.lastIndexOf("/"));
            threadUrl = threadUrl.substring(0, threadUrl.lastIndexOf("/"));
            threadUrl += "/common/cmn001.do?url=";

            String paramUrl = reqMdl.getRequestURI();
            paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("/"));

            String domain = "";
            String reqDomain = NullDefault.getString(reqMdl.getDomain(), "");
            if (!reqDomain.equals(GSConst.GS_DOMAIN)) {
                domain = reqDomain + "/";
                paramUrl = paramUrl.replace(
                 GSConstBulletin.PLUGIN_ID_BULLETIN, domain + GSConstBulletin.PLUGIN_ID_BULLETIN);
            }

            paramUrl += "/bbs080.do";
            paramUrl += "?bbs010forumSid=" + bfiSid;
            paramUrl += "&threadSid=" + btiSid;
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

            threadUrl += paramUrl;
        }

        return threadUrl;
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
     * <br>[機  能] 指定したユーザがフォーラム管理者か判定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return true:フォーラム管理者 false:管理者ではない
     * @throws SQLException SQL実行例外
     */
    public boolean isForumAdmin(int bfiSid, int usrSid, Connection con) throws SQLException {

        boolean forumAdmin = false;

        BbsForAdminDao forAdmDao = new BbsForAdminDao(con);
        BbsForAdminModel model = forAdmDao.select(bfiSid, usrSid);

        if (model != null) {
            forumAdmin = true;
        }

        return forumAdmin;
    }

    /**
     * 掲示板全般のログ出力を行う
     * @param map マップ
     * @param reqMdl リクエスト情報
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     */
    public void outPutLog(
            ActionMapping map,
            RequestModel reqMdl,
            String opCode,
            String level,
            String value) {
        outPutLog(map, reqMdl, opCode, level, value, null, GSConstBulletin.BBS_LOG_FLG_NONE);
    }

    /**
     * 掲示板全般のログ出力を行う
     * @param map マップ
     * @param reqMdl リクエスト情報
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param fileId 添付ファイルSID
     * @param logFlg ログ出力判別フラグ
     */
    public void outPutLog(
            ActionMapping map,
            RequestModel reqMdl,
            String opCode,
            String level,
            String value,
            String fileId,
            int logFlg) {
        outPutLog(map, reqMdl, opCode, level, value, -1, fileId, logFlg);
    }

    /**
     * 掲示板全般のログ出力を行う
     * @param map マップ
     * @param reqMdl リクエスト情報
     * @param opCode 操作コード
     * @param level ログレベル
     * @param value 内容
     * @param threadSid 投稿ID
     * @param fileId 添付ファイルSID
     * @param logFlg ログ出力判別フラグ
     */
    public void outPutLog(
            ActionMapping map,
            RequestModel reqMdl,
            String opCode,
            String level,
            String value,
            int threadSid,
            String fileId,
            int logFlg) {

        BaseUserModel usModel = reqMdl.getSmodel();
        int usrSid = -1;
        if (usModel != null) {
            usrSid = usModel.getUsrsid(); //セッションユーザSID
        }

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textBbs = gsMsg.getMessage("cmn.bulletin");

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        logMdl.setLogDate(now);
        logMdl.setUsrSid(usrSid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConstBulletin.PLUGIN_ID_BULLETIN);
        logMdl.setLogPluginName(textBbs);
        String type = map.getType();
        logMdl.setLogPgId(StringUtil.trimRengeString(type, 100));
        logMdl.setLogPgName(getPgName(map.getType(), reqMdl));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(value);
        logMdl.setLogIp(reqMdl.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);
        if (logFlg == GSConstBulletin.BBS_LOG_FLG_DOWNLOAD) {
            logMdl.setLogCode("binSid：" + fileId);
        } else if (logFlg == GSConstBulletin.BBS_LOG_FLG_PDF) {
            logMdl.setLogCode("PDF出力 threadSid：" + threadSid);
        }

        LoggingBiz logBiz = new LoggingBiz(con__);
        String domain = reqMdl.getDomain();
        logBiz.outPutLog(logMdl, domain);
    }

    /**
     * プログラムIDからプログラム名称を取得する
     * @param id アクションID
     * @param reqMdl リクエスト情報
     * @return String
     */
    public String getPgName(String id, RequestModel reqMdl) {
        String ret = new String();
        if (id == null) {
            return ret;
        }
        GsMessage gsMsg = new GsMessage(reqMdl);
        log__.info("プログラムID==>" + id);
        if (id.equals("jp.groupsession.v2.bbs.bbs020.Bbs020Action")) {
            String textTitle = gsMsg.getMessage("bbs.14");
            String textAdm = gsMsg.getMessage("cmn.admin.setting");
            return textAdm + " " + textTitle;
        }
        if (id.equals("jp.groupsession.v2.bbs.bbs030kn.Bbs030knAction")) {
            String textTitle = gsMsg.getMessage("bbs.30");
            String textAdm = gsMsg.getMessage("cmn.admin.setting");
            return textAdm + " " + textTitle;
        }
        if (id.equals("jp.groupsession.v2.bbs.bbs050.Bbs050Action")) {
            String textTitle = gsMsg.getMessage("cmn.preferences");
            String textPri = gsMsg.getMessage("cmn.preferences2");
            return textPri + " " + textTitle;
        }
        if (id.equals("jp.groupsession.v2.bbs.bbs070kn.Bbs070knAction")) {
            String textTitle = gsMsg.getMessage("bbs.31");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.bbs.bbs080.Bbs080Action")) {
            String textTitle = gsMsg.getMessage("bbs.32");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.bbs.bbs090kn.Bbs090knAction")) {
            String textTitle = gsMsg.getMessage("bbs.33");
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.bbs.bbs120.Bbs120Action")) {
            String textTitle = gsMsg.getMessage("cmn.automatic.delete.setting");
            String textAdm = gsMsg.getMessage("cmn.admin.setting");
            return textAdm + " " + textTitle;
        }
        if (id.equals("jp.groupsession.v2.bbs.bbs170.Bbs170Action")) {
            String textTitle = "掲示予定一覧";
            return textTitle;
        }
        if (id.equals("jp.groupsession.v2.bbs.ptl020.BbsPtl020Action")) {
            //ポータル プラグインポートレット
            return gsMsg.getMessage("ptl.ptl040.1");
        }
        return ret;
    }

    /**
     * <br>[機  能] 指定したスレッドのディスクサイズを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param btiSid スレッドSID
     * @return ディスクサイズ
     * @throws SQLException SQL実行時例外
     * @throws UnsupportedEncodingException スレッド情報のエンコードに失敗
     */
    public long getThreadDiskSize(Connection con, int btiSid)
    throws SQLException, UnsupportedEncodingException {
        long diskSize = 0;

        //スレッド情報
        BbsThreInfDao threDao = new BbsThreInfDao(con);
        BbsThreInfModel threMdl = threDao.select(btiSid);
        diskSize = getThreadSize(threMdl.getBtiTitle());
        BulletinDao bbsDao = new BulletinDao(con);
        diskSize += bbsDao.getSumThreTempFileSize(btiSid);

        //投稿情報
        BbsWriteInfDao writeDao = new BbsWriteInfDao(con);
        List<BbsWriteInfModel> writeList = writeDao.getWriteList(btiSid);
        for (BbsWriteInfModel writeData : writeList) {
            diskSize += getWriteSize(writeData.getBwiValue());
        }

        return diskSize;
    }

    /**
     * <br>[機  能] スレッド情報のサイズを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param btiTitle スレッドタイトル
     * @return スレッド情報のサイズ
     * @throws UnsupportedEncodingException スレッド情報のエンコードに失敗
     */
    public long getThreadSize(String btiTitle) throws UnsupportedEncodingException {
        return 20 + btiTitle.getBytes(GSConst.ENCODING).length + 24 + 4;
    }

    /**
     * <br>[機  能] 投稿情報のサイズを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param bwiValue 投稿内容
     * @return 投稿情報のサイズ
     * @throws UnsupportedEncodingException 投稿情報のエンコードに失敗
     */
    public long getWriteSize(String bwiValue) throws UnsupportedEncodingException {
        return 20 + bwiValue.getBytes(GSConst.ENCODING).length + 24 + 4;
    }

    /**
     * <br>[機  能] 投稿の添付ファイルサイズを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tempDir テンポラリディレクトリ
     * @return 添付ファイルサイズ
     * @throws IOToolsException 添付ファイルの読み込みに失敗
     */
    public long getWriteTempFileSize(String tempDir)
    throws IOToolsException {
        long fileSize = 0;

        CommonBiz cmnBiz = new CommonBiz();
        List<LabelValueBean> fileList = cmnBiz.getTempFileLabelList(tempDir);
        for (LabelValueBean fileData : fileList) {
            String saveFilePath = IOTools.setEndPathChar(tempDir);
            saveFilePath += fileData.getValue().concat(GSConstCommon.ENDSTR_SAVEFILE);
            fileSize += new File(saveFilePath).length();
        }

        return fileSize;
    }

    /**
     * <br>[機  能] 投稿情報のサイズを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bwiSid 投稿情報
     * @return 投稿情報のサイズ
     * @throws SQLException SQL実行時例外
     * @throws UnsupportedEncodingException 投稿情報のエンコードに失敗
     */
    public long getWriteSize(Connection con, int bwiSid)
    throws SQLException, UnsupportedEncodingException {
        BbsWriteInfDao writeDao = new BbsWriteInfDao(con);
        BbsWriteInfModel writeData = writeDao.select(bwiSid);
        long writeSize = getWriteSize(writeData.getBwiValue());

        BulletinDao bbsDao = new BulletinDao(con);
        writeSize += bbsDao.getSumWriteTempFileSize(bwiSid);

        return writeSize;
    }

    /**
     * <br>[機  能] 指定したフォーラムのディスク容量に関する情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @return true: 正常、 false: 警告有り
     * @throws SQLException SQL実行時例外
     */
    public BulletinForumDiskModel getForumDiskData(Connection con, int bfiSid) throws SQLException {
        BulletinDao bbsDao = new BulletinDao(con);
        return bbsDao.getForumDiskData(bfiSid);
    }

    /**
     * <br>[機  能] 指定したフォーラムのディスク容量警告チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @return true: 警告あり、 false: 警告なし
     * @throws SQLException SQL実行時例外
     */
    public boolean checkForumWarnDisk(Connection con, int bfiSid) throws SQLException {
        return checkForumWarnDisk(getForumDiskData(con, bfiSid));
    }

    /**
     * <br>[機  能] 指定したフォーラムのディスク容量警告チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param diskData フォーラムのディスク容量に関する情報
     * @return true: 警告あり、 false: 警告なし
     * @throws SQLException SQL実行時例外
     */
    public boolean checkForumWarnDisk(BulletinForumDiskModel diskData) throws SQLException {

        if (diskData.getBfiDisk() != GSConstBulletin.BFI_DISK_LIMITED
        || diskData.getBfiWarnDisk() != GSConstBulletin.BFI_WARN_DISK_YES) {
            return false;
        }

        int diskSize = diskData.getBfiDiskSize();
        int diskWarnTh = diskData.getBfiWarnDiskTh();
        BigDecimal useDiskSize = new BigDecimal(diskData.getBfsSize());
        BigDecimal limitSize = new BigDecimal(diskWarnTh * 1024 * 1024);
        limitSize = limitSize.divide(new BigDecimal(100), 2,
                                                BigDecimal.ROUND_HALF_UP);
        limitSize = limitSize.multiply(new BigDecimal(diskSize));

        return useDiskSize.compareTo(limitSize) >= 0;
    }

    /**
     * <br>[機  能] 個人設定でショートメール通知設定が可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @return true:設定可能 false:設定不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canSetSmailConf(Connection con, int userSid) throws SQLException {
        BbsAdmConfModel admData = getBbsAdminData(con, userSid);
        return admData.getBacSmlNtf() != GSConstBulletin.BAC_SML_NTF_ADMIN;
    }

    /**
     * <br>[機  能] 管理者設定でショートメール通知が許可されているが判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param userSid ユーザSID
     * @return true:通知可能 false:通知不可
     * @throws SQLException SQL実行時例外
     */
    public boolean canSendSmail(Connection con, int userSid) throws SQLException {
        BbsAdmConfModel admData = getBbsAdminData(con, userSid);
        return admData.getBacSmlNtf() != GSConstBulletin.BAC_SML_NTF_ADMIN
                || admData.getBacSmlNtfKbn() == GSConstBulletin.BAC_SML_NTF_KBN_YES;
    }

    /**
     * <br>[機  能] 入力されたFrom日付が未来の日付かチェックする。(年月日のみ)
     * <br>[解  説]
     * <br>[備  考]
     * @param limitFrDate From日時
     * @param now 基準日
     * @return true:未来  false:本日 or 過去
     */
    public boolean checkReserveDate(UDate limitFrDate, UDate now) {
        int diff = now.compareDateYMD(limitFrDate);
        if (diff == UDate.LARGE) {
            return true;
        }
        return false;
    }


    /**
     * <br>[機  能] 指定したフォーラムの存在チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bfiSid フォーラムSID
     * @throws SQLException SQL実行時例外
     * @return true:存在する  false:存在しない
     */
    public boolean isCheckExistForum(Connection con, int bfiSid) throws SQLException {
        BbsForInfDao dao = new BbsForInfDao(con);
        return dao.countBfi(bfiSid) > 0;
    }
    /**
     * <br>[機  能] 指定したスレッドの存在チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param btiSid スレッドSID
     * @throws SQLException SQL実行時例外
     * @return true:存在する  false:存在しない
     */
    public boolean isCheckExistThread(Connection con, int btiSid) throws SQLException {
        BbsThreInfDao dao = new BbsThreInfDao(con);
        return dao.countBti(btiSid) > 0;
    }

    /**
     * <br>[機  能] 指定した投稿の存在チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bwiSid 投稿SID
     * @throws SQLException SQL実行時例外
     * @return true:存在する  false:存在しない
     */
    public boolean isCheckExistWrite(Connection con, int bwiSid) throws SQLException {
        BbsWriteInfDao dao = new BbsWriteInfDao(con);
        return dao.countBwi(bwiSid) > 0;
    }

    /**
     * <br>[機  能] 掲示板閲覧時の集計用データを登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param bfiSid フィールドSID
     * @param btiSid スレッドSID
     * @param date 閲覧日時
     * @throws SQLException SQL実行時例外
     */
    public void regBbsViewLogCnt(
            Connection con, int usrSid, int bfiSid, int btiSid, UDate date)
                    throws SQLException {
        boolean commitFlg = false;
        con.setAutoCommit(false);
        //ロックの解除処理
        try {
            BbsViewLogCountModel mdl = new BbsViewLogCountModel();
            mdl.setUsrSid(usrSid);
            mdl.setBfiSid(bfiSid);
            mdl.setBtiSid(btiSid);
            mdl.setBvlViewDate(date);
            BbsViewLogCountDao dao = new BbsViewLogCountDao(con);
            dao.insert(mdl);

            //ログ集計
            __registLogCntSum(con, GSConstBulletin.BLS_KBN_VIEW, date);

            commitFlg = true;
        } catch (SQLException e) {
            log__.error("閲覧時集計用データの登録に失敗" + e);
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
     * <br>[機  能] 掲示板投稿時の集計用データを登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @param grpSid グループID
     * @param bfiSid フィールドSID
     * @param btiSid スレッドSID
     * @param date 投稿日時
     * @throws SQLException SQL実行時例外
     */
    public void regBbsWriteLogCnt(
            Connection con, int usrSid, int grpSid,
            int bfiSid, int btiSid, UDate date)
                    throws SQLException {
        BbsWriteLogCountModel mdl = new BbsWriteLogCountModel();
        mdl.setUsrSid(usrSid);
        mdl.setGrpSid(grpSid);
        mdl.setBfiSid(bfiSid);
        mdl.setBtiSid(btiSid);
        mdl.setBwlWrtDate(date);
        BbsWriteLogCountDao dao = new BbsWriteLogCountDao(con);
        dao.insert(mdl);

        //ログ集計
        __registLogCntSum(con, GSConstBulletin.BLS_KBN_WRITE, date);
    }

    /**
     * <br>[機  能] 掲示板集計データ_集計結果を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param blsKbn ログ区分
     * @param date 日時
     * @throws SQLException SQL実行時例外
     */
    private void __registLogCntSum(
            Connection con, int blsKbn, UDate date) throws SQLException {

        BbsLogCountSumDao logSumDao = new BbsLogCountSumDao(con);
        BbsLogCountSumModel logSumMdl = logSumDao.getSumLogCount(blsKbn, date);
        if (logSumMdl != null) {
            if (logSumDao.update(logSumMdl) == 0) {
                logSumDao.insert(logSumMdl);
            }
        }
    }

}
