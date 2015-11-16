package jp.groupsession.v2.bbs.bbs080;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs010.Bbs010Biz;
import jp.groupsession.v2.bbs.bbs060.Bbs060Biz;
import jp.groupsession.v2.bbs.dao.BbsBinDao;
import jp.groupsession.v2.bbs.dao.BbsThreInfDao;
import jp.groupsession.v2.bbs.dao.BbsThreSumDao;
import jp.groupsession.v2.bbs.dao.BbsThreViewDao;
import jp.groupsession.v2.bbs.dao.BbsWriteInfDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsThreInfModel;
import jp.groupsession.v2.bbs.model.BbsThreViewModel;
import jp.groupsession.v2.bbs.model.BbsUserModel;
import jp.groupsession.v2.bbs.model.BbsWriteInfModel;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.bbs.model.BulletinSearchModel;
import jp.groupsession.v2.bbs.pdf.BbsListPdfModel;
import jp.groupsession.v2.bbs.pdf.BbsListPdfUtil;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 投稿一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs080Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs080Biz.class);

    /** 結果コード 正常 */
    public static final int RESULT_NORMAL = 0;
    /** 結果コード スレッドなし */
    public static final int RESULT_NOTHREAD = 1;
    /** 結果コード 掲示期限切れ */
    public static final int RESULT_OVERLIMIT = 2;
    /** 結果コード フォーラムが不正 */
    public static final int RESULT_INVALIDFORUM  = 3;
    /** コネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     */
    public Bbs080Biz() {
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *@param con コネクション
     *@param reqMdl リクエストモデル
     */
    public Bbs080Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者, false:一般ユーザ
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @return スレッドが存在するか true:存在する false:存在しない
     * @throws Exception 実行例外
     */
    public int setInitData(RequestModel reqMdl, Bbs080ParamModel paramMdl,
                            Connection con, int userSid,
                            boolean admin, String appRoot, String tempDir)
    throws Exception {
        log__.debug("START");

        int bfiSid = paramMdl.getBbs010forumSid();
        int btiSid = paramMdl.getThreadSid();

        BbsBiz biz = new BbsBiz();

        //スレッドが存在しない場合はエラー
        BulletinDspModel btDspMdl = biz.getThreadData(con, paramMdl.getThreadSid());
        if (btDspMdl == null) {
            return RESULT_NOTHREAD;
        }

        //パラメータ.フォーラムと取得したスレッド情報のフォーラムが一致しない場合
        //エラーとする
        if (bfiSid != btDspMdl.getBfiSid()) {
            return RESULT_INVALIDFORUM;
        }

        int limit = btDspMdl.getBtiLimit();
        UDate limitFrDate = btDspMdl.getBtiLimitFrDate();
        UDate limitToDate = btDspMdl.getBtiLimitDate();

        //フォーラム名、スレッド名を設定
        paramMdl.setBbs080forumName(btDspMdl.getBfiName());
        paramMdl.setBbs080threadTitle(btDspMdl.getBtiTitle());

        //フォーラム編集権限
        boolean editAuth = biz.isForumEditAuth(con, bfiSid, userSid);

        //返信ボタン・引用返信ボタン表示フラグ
        int reply = GSConstBulletin.BBS_THRE_REPLY_NO;
        if (btDspMdl.getBfiReply() == GSConstBulletin.BBS_THRE_REPLY_YES) {
            if (admin || editAuth) {
                reply = GSConstBulletin.BBS_THRE_REPLY_YES;
            }
        }
        paramMdl.setBbs080reply(String.valueOf(reply));

        if (limit == GSConstBulletin.THREAD_LIMIT_YES) {
            String strLimitDate = UDateUtil.getYymdJ(limitFrDate, reqMdl)
                    + " ～ " + UDateUtil.getYymdJ(limitToDate, reqMdl);
            paramMdl.setBbs080limitDate(strLimitDate);
        }

        //フォーラム管理者フラグ
        boolean forumAdmin = biz.isForumAdmin(bfiSid, userSid, con);

        //スレッド削除ボタン表示フラグを設定
        if (!editAuth && !admin) {
            //閲覧ユーザ
            paramMdl.setBbs080ShowThreBtn(BulletinDspModel.SHOWBTNFLG_NO);

        } else if (admin || btDspMdl.getAddUserSid() == userSid || forumAdmin) {
            paramMdl.setBbs080ShowThreBtn(BulletinDspModel.SHOWBTNFLG_YES);
        }

        if (paramMdl.getBbs080ShowThreBtn() == BulletinDspModel.SHOWBTNFLG_NO
                && !editAuth
                && paramMdl.getBbs080reply().equals(
                        String.valueOf(GSConstBulletin.BBS_THRE_REPLY_NO))) {
            paramMdl.setBbs080btnDspFlg(BulletinDspModel.SHOWALLBTNFLG_NO);
        }

        //掲示板個人情報を取得
        BbsBiz bbsBiz = new BbsBiz();
        BbsUserModel bUserMdl = bbsBiz.getBbsUserData(con, userSid);

        int order = paramMdl.getBbs080orderKey();
        if (order != GSConstBulletin.BBS_WRTLIST_ORDER_ASC
        && order != GSConstBulletin.BBS_WRTLIST_ORDER_DESC) {
            order = bUserMdl.getBurWrtlistOrder();
            paramMdl.setBbs080orderKey(order);
        }
        //画像表示有無
        paramMdl.setPhotoFileDsp(bUserMdl.getBurThreImage());
        //添付ファイル画像表示有無
        paramMdl.setTempImageFileDsp(bUserMdl.getBurTempImage());

        //最大件数
        BulletinDao bbsDao = new BulletinDao(con);
        int wrtCnt = bbsDao.getWriteCount(btiSid);
        int maxWrtCnt = bUserMdl.getBurWrtCnt();
        int maxPage = wrtCnt / maxWrtCnt;
        if ((wrtCnt % maxWrtCnt) > 0) {
            maxPage++;
        }
        //ページ調整
        int page = paramMdl.getBbs080page1();
        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        paramMdl.setBbs080page1(page);
        paramMdl.setBbs080page2(page);

        //ページコンボ設定
        if (maxPage > 1) {
            ArrayList < LabelValueBean > pageList =
                            new ArrayList < LabelValueBean >();
            for (int i = 1; i <= maxPage; i++) {
                pageList.add(new LabelValueBean(i + " / " + maxPage, Integer.toString(i)));
            }
            paramMdl.setBbsPageLabel(pageList);
        }

        boolean isOrderAsc = true;
        if (order == GSConstBulletin.BBS_WRTLIST_ORDER_DESC) {
            isOrderAsc = false;
        }

        BulletinDao btDao = new BulletinDao(con);
        int start = 1;
        int end = maxWrtCnt;
        if (page >= 2) {
            start += maxWrtCnt;

            start += (page - 2) * (maxWrtCnt);
            end = start + maxWrtCnt - 1;
        }

        BulletinSearchModel searchMdl = new BulletinSearchModel();
        searchMdl.setBtiSid(btiSid);
        searchMdl.setUserSid(userSid);
        searchMdl.setAdmin(admin);
//        searchMdl.setAdmin(false);
        searchMdl.setNewCnt(bUserMdl.getBurNewCnt());
        searchMdl.setStart(start);
        searchMdl.setEnd(end);
        searchMdl.setAppRoot(appRoot);
        searchMdl.setTempDir(tempDir);
        searchMdl.setOrderWriteDate(order);
        searchMdl.setForumAdmin(biz.isForumAdmin(bfiSid, userSid, con));
        paramMdl.setWriteList(btDao.getWriteList(searchMdl));

        if (page == 1 && isOrderAsc) {
            paramMdl.getWriteList().get(0).setThdWriteFlg(1);
        } else if (page == maxPage && !isOrderAsc) {
            int lastIndex = paramMdl.getWriteList().size() - 1;
            paramMdl.getWriteList().get(lastIndex).setThdWriteFlg(1);
        }

        //スレッド閲覧情報を更新
        updateView(con, btiSid, userSid, bfiSid);

        List<BulletinDspModel> bullList = new ArrayList<BulletinDspModel>();

        //[サブコンテンツ]フォーラム一覧表示フラグ判定
        if (bUserMdl.getBurSubForum() == GSConstBulletin.BBS_MIDOKU_TRD_DSP) {
            //フォーラム一覧を取得
            Bbs010Biz biz010 = new Bbs010Biz();
            bUserMdl.setBurForCnt(-1);
            List<BulletinDspModel> list = biz010.getForumDataList(con, userSid, false, 1, bUserMdl);
            bullList = new ArrayList<BulletinDspModel>();
            for (BulletinDspModel mdl : list) {
                String forumName = StringUtilHtml.transToHTmlWithWbr(
                        StringUtilHtml.deleteHtmlTag(
                                StringUtilHtml.transToText(mdl.getBfiName())), 20);
                mdl.setBfiName(forumName);
                bullList.add(mdl);
            }
        }

        paramMdl.setForumList(bullList);

        List<BulletinDspModel> midokuList =
            new ArrayList<BulletinDspModel>();

        //[サブコンテンツ]未読スレッド表示フラグ判定
        if (bUserMdl.getBurSubUnchkThre() == GSConstBulletin.BBS_THRED_DSP) {
            //未読スレッド一覧Mapを取得する
            midokuList = btDao.getThreadList2(userSid, bUserMdl.getBurThreMainCnt());
        }

        paramMdl.setNotReadThreadList(midokuList);

        //スレッドのURLを作成
        paramMdl.setThreadUrl(
                bbsBiz.createThreadUrl(reqMdl, paramMdl.getBbs010forumSid(),
                                    paramMdl.getThreadSid()));

        //フォーラムのメンバー数を取得する
        paramMdl.setForumMemberCount(String.valueOf(btDao.getForumMemberCount(bfiSid)));
        //既読件数を取得する
        paramMdl.setReadedCnt(btDao.getForumNum(bfiSid, btiSid));

        //フォーラムSIDからアイコンバイナリSIDを取得する
        Bbs060Biz bbs060Biz = new Bbs060Biz();
        Long binSid = bbs060Biz.getIcoBinSid(paramMdl, con);
        paramMdl.setBbs060BinSid(binSid);

        log__.debug("END");

        return RESULT_NORMAL;
    }

    /**
     * <br>[機  能] スレッド閲覧情報を更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param btiSid スレッドSID
     * @param userSid ユーザSID
     * @param bfiSid フォーラムSID
     * @throws SQLException SQL実行例外
     */
    public void updateView(Connection con, int btiSid, int userSid, int bfiSid)
    throws SQLException {

        boolean commit = false;
        try {
            UDate now = new UDate();
            BbsThreViewDao threViewDao = new BbsThreViewDao(con);
            BbsThreViewModel threViewMdl = new BbsThreViewModel();
            threViewMdl.setBtiSid(btiSid);
            threViewMdl.setUsrSid(userSid);
            threViewMdl.setBivViewKbn(1);
            threViewMdl.setBivEuid(userSid);
            threViewMdl.setBivEdate(now);

            if (threViewDao.update(threViewMdl) <= 0) {
                threViewMdl.setBfiSid(bfiSid);
                threViewMdl.setBivAuid(userSid);
                threViewMdl.setBivAdate(now);
                threViewDao.insert(threViewMdl);
            }

            commit = true;
        } catch (SQLException e) {
            log__.warn("スレッド閲覧情報の更新失敗", e);
        } finally {
            if (commit) {
                con.commit();
            } else {
                con.rollback();
            }
        }
    }

    /**
     * <br>[機  能] スレッド情報の削除処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param btiSid スレッドSID
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void deleteThreadData(int bfiSid, int btiSid,
                                Connection con,
                                int userSid)
    throws Exception {
        log__.debug("START");

        UDate now = new UDate();

        //バイナリー情報の論理削除
        BulletinDao bbsDao = new BulletinDao(con);
        bbsDao.deleteBinfForThread(btiSid);

        //投稿添付情報の削除
        bbsDao.deleteBbsBinInThread(btiSid);

        //投稿情報の削除
        BbsWriteInfDao bbsWriteInfDao = new BbsWriteInfDao(con);
        bbsWriteInfDao.deleteWriteInThread(btiSid);

        //スレッド情報の削除
        BbsThreInfDao bbsThreInfDao = new BbsThreInfDao(con);
        bbsThreInfDao.delete(btiSid);

        //スレッド閲覧情報の削除
        BbsBiz bbsBiz = new BbsBiz();
        bbsBiz.deleteThreadView(con, btiSid);

        //スレッド集計情報の削除
        BbsThreSumDao bbsThreSumDao = new BbsThreSumDao(con);
        bbsThreSumDao.delete(btiSid);

        //フォーラム集計情報の更新
        bbsBiz.updateBbsForSum(con, bfiSid, userSid, now, false);

        log__.debug("END");
    }

    /**
     * <br>[機  能] 投稿情報の削除処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void deleteWriteData(Bbs080ParamModel paramMdl,
                                Connection con,
                                int userSid)
    throws Exception {
        log__.debug("START");

        UDate now = new UDate();

        int bwiSid = paramMdl.getBbs080writeSid();

        //バイナリー情報の論理削除
        BulletinDao bbsDao = new BulletinDao(con);
        bbsDao.deleteBinfForWrite(bwiSid);

        //投稿情報の削除
        BbsWriteInfDao bbsWriteInfDao = new BbsWriteInfDao(con);
        bbsWriteInfDao.delete(bwiSid);

        //投稿添付情報の削除
        BbsBinDao bbsBinDao = new BbsBinDao(con);
        bbsBinDao.delete(bwiSid);

        //スレッド集計情報の更新
        BbsBiz bbsBiz = new BbsBiz();
        int btiSid = paramMdl.getThreadSid();
        bbsBiz.updateBbsThreSum(con, btiSid, userSid, now, false);

        //フォーラム集計情報の更新
        int bfiSid = paramMdl.getBbs010forumSid();
        bbsBiz.updateBbsForSum(con, bfiSid, userSid, now, false);

        log__.debug("END");
    }

    /**
     * <br>[機  能] ユーザがスレッドを削除できるかを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param btiSid スレッドSID
     * @param userSid ユーザSID
     * @return true:スレッド削除可能 false:スレッド削除不可
     * @throws SQLException SQL例外発生例外
     */
    public boolean canDeleteThread(Connection con, int btiSid, int userSid)
    throws SQLException {

        BbsBiz bbsBiz = new BbsBiz();

        //フォーラム管理者の場合、スレッドの削除を許可する
        BbsThreInfDao threadDao = new BbsThreInfDao(con);
        BbsThreInfModel threadMdl = threadDao.select(btiSid);
        if (bbsBiz.isForumAdmin(threadMdl.getBfiSid(), userSid, con)) {
            return true;
        }

        BulletinDspModel bbsMdl = bbsBiz.getThreadData(con, btiSid);
        return bbsMdl.getAddUserSid() == userSid;
    }

    /**
     * <br>[機  能] ユーザが投稿を削除できるかを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bwiSid 投稿SID
     * @param userSid ユーザSID
     * @return true:投稿削除可能 false:投稿削除不可
     * @throws SQLException SQL例外発生例外
     */
    public boolean canDeleteWrite(Connection con, int bwiSid, int userSid)
    throws SQLException {

        BbsBiz bbsBiz = new BbsBiz();

        //フォーラム管理者の場合、スレッドの削除を許可する
        BbsWriteInfDao writeDao = new BbsWriteInfDao(con);
        BbsWriteInfModel writeMdl = writeDao.select(bwiSid);
        if (bbsBiz.isForumAdmin(writeMdl.getBfiSid(), userSid, con)) {
            return true;
        }

        BulletinDspModel btMdl = bbsBiz.getWriteData(con, bwiSid);
        return btMdl.getAddUserSid() == userSid;
    }

    /**
     * <br>[機  能] 添付ファイルバイナリSIDのチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param bwiSid 投稿SID
     * @param tmpBinSid 添付ファイルバイナリSID
     * @param forumSid フォーラムSID
     * @param threadSid スレッドSID
     * @return フォーラム件数
     * @throws SQLException SQL実行例外
     */
    public boolean cheTmpHnt(Connection con, int bwiSid,
            Long tmpBinSid, int forumSid, int threadSid)
    throws SQLException {

        boolean bwiCheckFlg = false;

        //添付ファイルバイナリSIDチェック
        BulletinDao bbsDao = new BulletinDao(con);
        boolean existForTmpFlg = bbsDao.existBbsWriTmp(bwiSid, tmpBinSid, forumSid, threadSid);

        if (existForTmpFlg) {
            bwiCheckFlg = true;
        }

        return bwiCheckFlg;
    }

    /**
     * <br>[機  能]選択されたスレッドの投稿一覧をPDF出力します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトパス
     * @param userSid ユーザーSID
     * @param admin 管理者 true:管理者, false:一般ユーザー
     * @return  pdfMdl PDFモデル
     * @throws IOException IO実行時例外
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイル操作実行例外
     */
    public BbsListPdfModel createBbsListPdf(
            Bbs080ParamModel paramMdl,
            String appRootPath,
            String tempDir,
            int userSid,
            boolean admin
            )
        throws IOException, IOToolsException, SQLException {
        OutputStream oStream = null;

        log__.debug("投稿一覧PDF出力処理 開始");

        //PDF出力情報をPDF出力用モデルに格納
        BbsListPdfModel pdfMdl = new BbsListPdfModel();
        pdfMdl = __getBbsPdfDataList(paramMdl, userSid, admin, appRootPath, tempDir);

        String downloadFileName = pdfMdl.getBfiName() + "_" + pdfMdl.getBtiTitle();
        String encOutBookName = __fileNameCheck(downloadFileName) + ".pdf";

        String saveFileName = "pdfBbs_" + pdfMdl.getBfiSid() + "_" + pdfMdl.getBtiSid() + ".pdf";
        String escSaveFileName = __fileNameCheck(saveFileName);

        pdfMdl.setFileName(encOutBookName);
        pdfMdl.setSaveFileName(escSaveFileName);

        try {
            IOTools.isDirCheck(tempDir, true);
            oStream = new FileOutputStream(tempDir + escSaveFileName);

            //PDFファイル生成を行う
            BbsListPdfUtil pdfUtil = new BbsListPdfUtil(reqMdl__);
            pdfUtil.createBbsReport(appRootPath, oStream, pdfMdl);

        } catch (Exception e) {
            log__.error("投稿一覧PDF出力に失敗しました。", e);

        } finally {
            if (oStream != null) {
                oStream.flush();
                oStream.close();
            }
        }
        log__.debug("投稿一覧PDF出力を終了します。");
        return pdfMdl;
    }

    /**
     * <br>[機  能] PDF出力用のデータを取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param userSid ユーザーSID
     * @param admin 管理者 true:管理者, false:一般ユーザ
     * @param appRootPath アプリケーションルートパス
     * @param tempDir テンポラリディレクトパス
     * @return PDF出力データ
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイル操作実行例外
     * @throws IOException 入出力実行例外
     */
    private BbsListPdfModel __getBbsPdfDataList(Bbs080ParamModel paramMdl, int userSid,
            boolean admin, String appRootPath, String tempDir)
            throws SQLException, IOToolsException, IOException {

        log__.debug("PDF出力用のデータモデルの取得 開始");

        BbsListPdfModel ret = new BbsListPdfModel();
        int bfiSid = paramMdl.getBbs010forumSid();
        int btiSid = paramMdl.getThreadSid();

        BbsBiz biz = new BbsBiz();

        //フォーラム名、スレッド名を設定
        BulletinDspModel btDspMdl = biz.getThreadData(con__, btiSid);
        //フォーラムSID
        ret.setBfiSid(bfiSid);
        //フォーラム名
        ret.setBfiName(btDspMdl.getBfiName());
        //スレッドSID
        ret.setBtiSid(btiSid);
        //スレッド名
        ret.setBtiTitle(btDspMdl.getBtiTitle());

        //昇順に固定
        int order = 0;

        //最大件数
        BulletinDao bbsDao = new BulletinDao(con__);
        int wrtCnt = bbsDao.getWriteCount(btiSid);

        //終了は常に最大件数にする
        BulletinDao btDao = new BulletinDao(con__);
        int start = 1;
        int end = wrtCnt;

        //投稿一覧情報を取得
        BulletinSearchModel searchMdl = new BulletinSearchModel();
        searchMdl.setBtiSid(btiSid);
        searchMdl.setUserSid(userSid);
        searchMdl.setAdmin(admin);
        searchMdl.setStart(start);
        searchMdl.setEnd(end);
        searchMdl.setAppRoot(appRootPath);
        searchMdl.setTempDir(tempDir);
        searchMdl.setOrderWriteDate(order);
        searchMdl.setForumAdmin(biz.isForumAdmin(bfiSid, userSid, con__));
        List<BulletinDspModel> bbsDspList = btDao.getWriteList(searchMdl);

        //投稿一覧
        ret.setWriteList(bbsDspList);

        return ret;
    }

    /**
     * <br>[機  能] ファイル名として使用できるか文字列チェックする。
     * <br>[解  説] /\?*:|"<>. を除去
     *                    255文字超える場合は以降を除去
     * <br>[備  考]
     * @param fileName テンポラリディレクトリ
     * @return 出力したファイルのパス
     */
    private String __fileNameCheck(String fileName) {
            String escName = fileName;
            escName = StringUtilHtml.replaceString(escName, "/", "");
            escName = StringUtilHtml.replaceString(escName, "\\", ""); //\
            escName = StringUtilHtml.replaceString(escName, "?", "");
            escName = StringUtilHtml.replaceString(escName, "*", "");
            escName = StringUtilHtml.replaceString(escName, ":", "");
            escName = StringUtilHtml.replaceString(escName, "|", "");
            escName = StringUtilHtml.replaceString(escName, "\"", ""); //"
            escName = StringUtilHtml.replaceString(escName, "<", "");
            escName = StringUtilHtml.replaceString(escName, ">", "");
            escName = StringUtilHtml.replaceString(escName, ".", "");
            escName = StringUtil.trimRengeString(escName, 251); //ファイル名＋拡張子=255文字以内
        return escName;
    }
}
