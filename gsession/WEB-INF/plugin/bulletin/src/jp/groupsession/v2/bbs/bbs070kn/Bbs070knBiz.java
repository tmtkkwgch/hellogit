package jp.groupsession.v2.bbs.bbs070kn;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.dao.BbsBinDao;
import jp.groupsession.v2.bbs.dao.BbsThreInfDao;
import jp.groupsession.v2.bbs.dao.BbsThreSumDao;
import jp.groupsession.v2.bbs.dao.BbsWriteInfDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsBinModel;
import jp.groupsession.v2.bbs.model.BbsThreInfModel;
import jp.groupsession.v2.bbs.model.BbsThreSumModel;
import jp.groupsession.v2.bbs.model.BbsWriteInfModel;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.bbs.model.BulletinSmlModel;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnGroupmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 スレッド登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs070knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs070knBiz.class);

    /** DBコネクション */
    public Connection con__ = null;

    /**
     * <p>コンストラクタ
     * @param con Connection
     */
    public Bbs070knBiz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param tempDir テンポラリディレクトリ
     * @throws Exception 実行例外
     */
    @SuppressWarnings("unchecked")
    public void setInitData(Bbs070knParamModel paramMdl, RequestModel reqMdl, String tempDir)
    throws Exception {
        log__.debug("START");

        //投稿者を設定
        int contributor = paramMdl.getBbs070contributor();
        if (contributor > 0) {
            CmnGroupmDao grpDao = new CmnGroupmDao(con__);
            CmnGroupmModel grpMdl = grpDao.selectGroup(contributor);
            if (grpMdl != null) {
                paramMdl.setBbs070contributorJKbn(grpMdl.getGrpJkbn());
                paramMdl.setBbs070knviewContributor(
                        grpMdl.getGrpName());
            }

        } else {
            int registUser = reqMdl.getSmodel().getUsrsid();
            if (paramMdl.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
                //編集の場合、新規登録時の登録ユーザを投稿者とする
                BbsWriteInfDao writeDao = new BbsWriteInfDao(con__);
                registUser = writeDao.getWriteAuid(paramMdl.getBbs080writeSid());
            }
            CmnUsrmInfDao usiDao = new CmnUsrmInfDao(con__);
            CmnUsrmInfModel usiMdl = usiDao.selectUserNameAndJtkbn(registUser);
            String name = NullDefault.getString(usiMdl.getUsiSei(), "")
                    + "　" + NullDefault.getString(usiMdl.getUsiMei(), "");

            paramMdl.setBbs070knviewContributor(name);
            paramMdl.setBbs070contributorJKbn(usiMdl.getUsrJkbn());

        }


        //内容を設定
        paramMdl.setBbs070knviewvalue(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getBbs070value()), ""));
        paramMdl.setBbs070knviewvalue(
                StringUtil.transToLink(paramMdl.getBbs070knviewvalue(),
                                    StringUtil.OTHER_WIN, true));

        //フォーラム名を設定
        BbsBiz bbsBiz = new BbsBiz();
        BulletinDspModel btDspMdl = bbsBiz.getForumData(con__, paramMdl.getBbs010forumSid());
        paramMdl.setBbs070forumName(btDspMdl.getBfiName());

        //添付ファイル一覧を設定
        CommonBiz cmnBiz = new CommonBiz();
        List<LabelValueBean> sortList = cmnBiz.getTempFileLabelList(tempDir);
        Collections.sort(sortList);
        paramMdl.setBbs070FileLabelList(sortList);

        log__.debug("End");
    }

    /**
     * <br>[機  能] スレッド情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param now 現在日時
     * @return int スレッドSID
     * @throws Exception 実行例外
     */
    public int insertThreadData(Bbs070knParamModel paramMdl,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRootPath,
                                String tempDir, UDate now)
    throws Exception {
        log__.debug("START");

//        UDate now = new UDate();

        int bfiSid = paramMdl.getBbs010forumSid();

        //スレッドSID採番
        int btiSid = (int) cntCon.getSaibanNumber(GSConstBulletin.SBNSID_BULLETIN,
                                                GSConstBulletin.SBNSID_SUB_BULLETIN_THREAD,
                                                userSid);

        //スレッド情報の登録
        BbsThreInfModel bbsThreInfMdl = new BbsThreInfModel();
        bbsThreInfMdl.setBtiSid(btiSid);
        bbsThreInfMdl.setBfiSid(bfiSid);
        bbsThreInfMdl.setBtiTitle(paramMdl.getBbs070title());
        bbsThreInfMdl.setBtiAuid(userSid);
        bbsThreInfMdl.setBtiAdate(now);
        bbsThreInfMdl.setBtiEuid(userSid);
        bbsThreInfMdl.setBtiEdate(now);
        bbsThreInfMdl.setBtiLimit(paramMdl.getBbs070limit());
        UDate limitFrDate = now.cloneUDate();
        if (bbsThreInfMdl.getBtiLimit() == GSConstBulletin.THREAD_LIMIT_YES) {
            limitFrDate.setZeroHhMmSs();
            limitFrDate.setDate(paramMdl.getBbs070limitFrYear(),
                    paramMdl.getBbs070limitFrMonth(),
                    paramMdl.getBbs070limitFrDay());
            bbsThreInfMdl.setBtiLimitFrDate(limitFrDate);

            UDate limitToDate = now.cloneUDate();
            limitToDate.setZeroHhMmSs();
            limitToDate.setDate(paramMdl.getBbs070limitYear(),
                            paramMdl.getBbs070limitMonth(),
                            paramMdl.getBbs070limitDay());
            bbsThreInfMdl.setBtiLimitDate(limitToDate);
        }
        int contributor = 0;
        if (paramMdl.getBbs070contributor() > 0) {
            contributor = paramMdl.getBbs070contributor();
        }
        bbsThreInfMdl.setBtiAgid(contributor);
        bbsThreInfMdl.setBtiEgid(contributor);
        BbsThreInfDao bbsThreInfDao = new BbsThreInfDao(con__);
        bbsThreInfDao.insert(bbsThreInfMdl);

        //投稿SID採番
        int bwiSid = (int) cntCon.getSaibanNumber(GSConstBulletin.SBNSID_BULLETIN,
                                                GSConstBulletin.SBNSID_SUB_BULLETIN_WRITE,
                                                userSid);

        //投稿情報の登録
        BbsWriteInfModel bbsWriteInfMdl = new BbsWriteInfModel();
        bbsWriteInfMdl.setBwiSid(bwiSid);
        bbsWriteInfMdl.setBfiSid(bfiSid);
        bbsWriteInfMdl.setBtiSid(btiSid);
        bbsWriteInfMdl.setBwiValue(paramMdl.getBbs070value());
        bbsWriteInfMdl.setBwiAuid(userSid);
        bbsWriteInfMdl.setBwiAdate(now);
        bbsWriteInfMdl.setBwiEuid(userSid);
        bbsWriteInfMdl.setBwiEdate(now);
        bbsWriteInfMdl.setBwiAgid(contributor);
        bbsWriteInfMdl.setBwiEgid(contributor);

        BbsWriteInfDao bbsWriteInfDao = new BbsWriteInfDao(con__);
        bbsWriteInfDao.insert(bbsWriteInfMdl);

        //バイナリー情報の登録
        CommonBiz cmnBiz = new CommonBiz();
        List < String > binSid = cmnBiz.insertBinInfo(con__, tempDir, appRootPath,
                                                    cntCon, userSid, now);

        //投稿添付情報の登録
        BbsBinModel bbsBinMdl = new BbsBinModel();
        bbsBinMdl.setBwiSid(bwiSid);
        bbsBinMdl.setBinSid(new Long(0));
        BbsBinDao bbsBinDao = new BbsBinDao(con__);
        bbsBinDao.insertBbsBinData(bwiSid, binSid);

        //スレッド集計情報の登録
        BbsBiz bbsBiz = new BbsBiz();
        BbsThreSumModel bbsThreSumMdl = new BbsThreSumModel();
        bbsThreSumMdl.setBtiSid(btiSid);
        bbsThreSumMdl.setBtsWrtCnt(1);

        //掲示予定フラグ
        boolean rsvThredFlg = false;
        if (bbsThreInfMdl.getBtiLimit() == GSConstBulletin.THREAD_LIMIT_YES
                && bbsBiz.checkReserveDate(limitFrDate, now)) {
            rsvThredFlg = true;
        }

        if (rsvThredFlg) {
            bbsThreSumMdl.setBtsWrtDate(limitFrDate);
        } else {
            bbsThreSumMdl.setBtsWrtDate(now);
        }


        bbsThreSumMdl.setBtsAuid(userSid);
        bbsThreSumMdl.setBtsAdate(now);
        bbsThreSumMdl.setBtsEuid(userSid);
        bbsThreSumMdl.setBtsEdate(now);
        bbsThreSumMdl.setBtsSize(bbsBiz.getThreadDiskSize(con__, btiSid));
        BbsThreSumDao bbsThreSumDao = new BbsThreSumDao(con__);
        bbsThreSumDao.insert(bbsThreSumMdl);


        //フォーラム集計情報の更新
//        bbsBiz.updateBbsForSum(con__, bfiSid, userSid, now);
        //掲示予定の場合 フォーラムの最終書き込み日時を更新しない
        if (rsvThredFlg) {
            bbsBiz.updateBbsForSum(con__, bfiSid, userSid, now, false);
        } else {
            bbsBiz.updateBbsForSum(con__, bfiSid, userSid, now, true);
        }


        log__.debug("End");

        return btiSid;
    }

    /**
     * <br>[機  能] スレッド情報の更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @return backFlg 戻り画面フラグ 0:投稿一覧 1:スレッド一覧 2:フォーラム一覧
     * @throws Exception 実行例外
     */
    public int updateThreadData(Bbs070knParamModel paramMdl,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRootPath,
                                String tempDir)
    throws Exception {
        log__.debug("START");

        UDate now = new UDate();

        int bfiSid = paramMdl.getBbs010forumSid();
        int btiSid = paramMdl.getThreadSid();
        int bwiSid = paramMdl.getBbs080writeSid();

        //スレッド情報の更新
        BbsThreInfDao bbsThreInfDao = new BbsThreInfDao(con__);
        BbsThreInfModel bbsThreInfMdl = new BbsThreInfModel();
        bbsThreInfMdl.setBtiSid(btiSid);
        bbsThreInfMdl.setBtiTitle(paramMdl.getBbs070title());
        bbsThreInfMdl.setBtiEuid(userSid);
        bbsThreInfMdl.setBtiEdate(now);
        bbsThreInfMdl.setBtiLimit(paramMdl.getBbs070limit());
        if (bbsThreInfMdl.getBtiLimit() == GSConstBulletin.THREAD_LIMIT_YES) {
            UDate limitFrDate = new UDate();
            limitFrDate.setZeroHhMmSs();
            limitFrDate.setDate(paramMdl.getBbs070limitFrYear(),
                    paramMdl.getBbs070limitFrMonth(),
                    paramMdl.getBbs070limitFrDay());
            bbsThreInfMdl.setBtiLimitFrDate(limitFrDate);

            UDate limitToDate = new UDate();
            limitToDate.setZeroHhMmSs();
            limitToDate.setDate(paramMdl.getBbs070limitYear(),
                            paramMdl.getBbs070limitMonth(),
                            paramMdl.getBbs070limitDay());
            bbsThreInfMdl.setBtiLimitDate(limitToDate);
        }
        int contributor = 0;
        if (paramMdl.getBbs070contributor() > 0) {
            contributor = paramMdl.getBbs070contributor();
        }
        bbsThreInfMdl.setBtiEgid(contributor);
        bbsThreInfDao.updateThreData(bbsThreInfMdl);

        //投稿情報の更新
        BbsWriteInfModel bbsWriteInfMdl = new BbsWriteInfModel();
        bbsWriteInfMdl.setBwiSid(bwiSid);
        bbsWriteInfMdl.setBwiValue(paramMdl.getBbs070value());
        bbsWriteInfMdl.setBwiEuid(userSid);
        bbsWriteInfMdl.setBwiEdate(now);
        bbsWriteInfMdl.setBwiEgid(contributor);

        BbsWriteInfDao bbsWriteInfDao = new BbsWriteInfDao(con__);
        bbsWriteInfDao.updateWriteInf(bbsWriteInfMdl);

        //バイナリー情報の登録
        BulletinDao bbsDao = new BulletinDao(con__);
        bbsDao.deleteBinfForWrite(bwiSid);
        CommonBiz cmnBiz = new CommonBiz();
        List < String > binSid = cmnBiz.insertBinInfo(con__, tempDir, appRootPath,
                                                    cntCon, userSid, now);

        //投稿添付情報の登録
        BbsBinModel bbsBinMdl = new BbsBinModel();
        bbsBinMdl.setBwiSid(bwiSid);
        bbsBinMdl.setBinSid(new Long(0));
        BbsBinDao bbsBinDao = new BbsBinDao(con__);
        bbsBinDao.deleteWriteBin(bwiSid);
        bbsBinDao.insertBbsBinData(bwiSid, binSid);

        //スレッド集計情報の更新
        BbsBiz bbsBiz = new BbsBiz();
        bbsBiz.updateBbsThreSum(con__, btiSid, userSid, now, true);

        //フォーラム集計情報の更新
        bbsBiz.updateBbsForSum(con__, bfiSid, userSid, now);

        //編集後が公開予定日が未来だった場合
        int backFlg = 0;
        if (bbsThreInfMdl.getBtiLimit() == GSConstBulletin.THREAD_LIMIT_YES) {
            UDate limitFrDate = new UDate();
            limitFrDate.setZeroHhMmSs();
            limitFrDate.setDate(paramMdl.getBbs070limitFrYear(),
                    paramMdl.getBbs070limitFrMonth(),
                    paramMdl.getBbs070limitFrDay());

            //未来の場合
            if (bbsBiz.checkReserveDate(limitFrDate, now)) {
                if (bbsBiz.isCheckExistThread(con__, btiSid)) {
                    //スレッドが存在すればスレッド一覧に戻す。
                    backFlg = 1;
                } else {
                    //スレッドが存在しなければフォーラム一覧へ戻す。
                    backFlg = 2;
                }
            } else {
                backFlg = 0;
            }
        }

        log__.debug("End");
        return backFlg;
    }

    /**
     * <br>[機  能] ショートメールで通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param btiSid スレッドSID
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig PluginConfig
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void sendSmail(
                Bbs070knParamModel paramMdl,
                MlCountMtController cntCon,
                int btiSid,
                int userSid,
                String appRootPath,
                String tempDir,
                PluginConfig pluginConfig,
                RequestModel reqMdl) throws Exception {

        BbsBiz biz = new BbsBiz();
        BulletinSmlModel bsmlModel = new BulletinSmlModel();
        //フォーラムSID
        bsmlModel.setBfiSid(paramMdl.getBbs010forumSid());
        //投稿者
        bsmlModel.setUserSid(userSid);
        //投稿者グループSID
        bsmlModel.setGrpSid(paramMdl.getBbs070contributor());
        //スレッドタイトル
        bsmlModel.setBtiTitle(paramMdl.getBbs070title());
        //投稿日時
        bsmlModel.setBwiAdate(new UDate());
        //添付ファイル名
        CommonBiz cmnBiz = new CommonBiz();
        List<LabelValueBean> files = cmnBiz.getTempFileLabelList(tempDir);
        bsmlModel.setFileLabelList(files);
        //投稿内容
        bsmlModel.setBwiValue(paramMdl.getBbs070value());
        //スレッドURL
        bsmlModel.setThreadUrl(biz.createThreadUrl(reqMdl, paramMdl.getBbs010forumSid(), btiSid));

        //送信
        biz.sendSmail(con__, cntCon, bsmlModel, appRootPath, pluginConfig, reqMdl);
    }
}
