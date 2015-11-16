package jp.groupsession.v2.bbs.bbs090kn;

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
import jp.groupsession.v2.bbs.dao.BbsThreViewDao;
import jp.groupsession.v2.bbs.dao.BbsWriteInfDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsBinModel;
import jp.groupsession.v2.bbs.model.BbsThreInfModel;
import jp.groupsession.v2.bbs.model.BbsThreViewModel;
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
 * <br>[機  能] 掲示板 投稿登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs090knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs090knBiz.class);

    /** DBコネクション */
    public Connection con__ = null;

    /**
     * <p>コンストラクタ
     * @param con Connection
     */
    public Bbs090knBiz(Connection con) {
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
    public void setInitData(Bbs090knParamModel paramMdl, RequestModel reqMdl, String tempDir)
    throws Exception {
        log__.debug("START");

        //フォーラム名、スレッド名称を設定
        BbsBiz biz = new BbsBiz();
        BulletinDspModel bbsMdl = biz.getThreadData(con__, paramMdl.getThreadSid());
        paramMdl.setBbs090forumName(bbsMdl.getBfiName());
        paramMdl.setBbs090threTitle(bbsMdl.getBtiTitle());

        //投稿者を設定
        int contributor = paramMdl.getBbs090contributor();
        if (contributor > 0) {
            CmnGroupmDao grpDao = new CmnGroupmDao(con__);
            CmnGroupmModel grpMdl = grpDao.selectGroup(contributor);
            if (grpMdl != null) {
                paramMdl.setBbs090contributorJKbn(grpMdl.getGrpJkbn());
                paramMdl.setBbs090knviewContributor(
                        grpMdl.getGrpName());
            }
        } else {
            int registUser = reqMdl.getSmodel().getUsrsid();
            if (paramMdl.getBbs090cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
                //編集の場合、新規登録時の登録ユーザを投稿者とする
                BbsWriteInfDao writeDao = new BbsWriteInfDao(con__);
                registUser = writeDao.getWriteAuid(paramMdl.getBbs080writeSid());
            }
            CmnUsrmInfDao usiDao = new CmnUsrmInfDao(con__);
            CmnUsrmInfModel usiMdl = usiDao.selectUserNameAndJtkbn(registUser);
            String name = NullDefault.getString(usiMdl.getUsiSei(), "")
                    + "　" + NullDefault.getString(usiMdl.getUsiMei(), "");

            paramMdl.setBbs090knviewContributor(name);
            paramMdl.setBbs090contributorJKbn(usiMdl.getUsrJkbn());

        }

        //コメントを設定
        paramMdl.setBbs090knviewvalue(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getBbs090value()), ""));
        paramMdl.setBbs090knviewvalue(
                StringUtil.transToLink(paramMdl.getBbs090knviewvalue(),
                                    StringUtil.OTHER_WIN, true));

        //添付ファイル一覧を設定
        CommonBiz cmnBiz = new CommonBiz();

        List<LabelValueBean> sortList = cmnBiz.getTempFileLabelList(tempDir);
        Collections.sort(sortList);
        paramMdl.setBbs090FileLabelList(sortList);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 投稿情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @throws Exception 実行例外
     */
    public void insertWriteData(Bbs090knParamModel paramMdl,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRootPath,
                                String tempDir)
    throws Exception {
        log__.debug("START");

        UDate now = new UDate();

        int bfiSid = paramMdl.getBbs010forumSid();
        int btiSid = paramMdl.getThreadSid();

        //投稿SID採番
        int bwiSid = (int) cntCon.getSaibanNumber(GSConstBulletin.SBNSID_BULLETIN,
                                                    GSConstBulletin.SBNSID_SUB_BULLETIN_WRITE,
                                                    userSid);

        //投稿情報の登録
        BbsWriteInfModel bbsWriteInfMdl = new BbsWriteInfModel();
        bbsWriteInfMdl.setBwiSid(bwiSid);
        bbsWriteInfMdl.setBfiSid(bfiSid);
        bbsWriteInfMdl.setBtiSid(btiSid);
        bbsWriteInfMdl.setBwiValue(paramMdl.getBbs090value());
        bbsWriteInfMdl.setBwiAuid(userSid);
        bbsWriteInfMdl.setBwiAdate(now);
        bbsWriteInfMdl.setBwiEuid(userSid);
        bbsWriteInfMdl.setBwiEdate(now);
        int contributor = 0;
        if (paramMdl.getBbs090contributor() > 0) {
            contributor = paramMdl.getBbs090contributor();
        }
        bbsWriteInfMdl.setBwiAgid(contributor);
        bbsWriteInfMdl.setBwiEgid(contributor);

        BbsWriteInfDao bbsWriteInfDao = new BbsWriteInfDao(con__);
        bbsWriteInfDao.insert(bbsWriteInfMdl);

        //バイナリー情報の登録
        CommonBiz cmnBiz = new CommonBiz();
        List <String> binSid = cmnBiz.insertBinInfo(con__, tempDir, appRootPath,
                                                    cntCon, userSid, now);

        //投稿添付情報の登録
        BbsBinModel bbsBinMdl = new BbsBinModel();
        bbsBinMdl.setBwiSid(bwiSid);
        bbsBinMdl.setBinSid(new Long(0));
        BbsBinDao bbsBinDao = new BbsBinDao(con__);
        bbsBinDao.insertBbsBinData(bwiSid, binSid);

        //スレッド閲覧情報の更新
        BbsThreViewModel threViewMdl = new BbsThreViewModel();
        threViewMdl.setBtiSid(btiSid);
        threViewMdl.setBivViewKbn(GSConstBulletin.BBS_THRE_VIEW_NO);
        threViewMdl.setBivEuid(userSid);
        threViewMdl.setBivEdate(now);
        BbsThreViewDao threViewDao = new BbsThreViewDao(con__);
        threViewDao.updateAllUserViewKbn(threViewMdl);

        //スレッド情報の更新(投稿者)
        BbsThreInfDao bbsThreInfDao = new BbsThreInfDao(con__);
        BbsThreInfModel bbsThreInfMdl = new BbsThreInfModel();
        bbsThreInfMdl.setBtiSid(btiSid);
        bbsThreInfMdl.setBtiEuid(userSid);
        bbsThreInfMdl.setBtiEdate(now);
        bbsThreInfMdl.setBtiEgid(contributor);
        bbsThreInfDao.updateContributor(bbsThreInfMdl);

        //スレッド集計情報の更新
        BbsBiz bbsBiz = new BbsBiz();
        bbsBiz.updateBbsThreSum(con__, btiSid, userSid, now, true);

        //フォーラム集計情報の更新
        bbsBiz.updateBbsForSum(con__, bfiSid, userSid, now);

        log__.debug("End");
    }

    /**
     * <br>[機  能] 投稿情報の更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @throws Exception 実行例外
     */
    public void updateWriteData(Bbs090knParamModel paramMdl,
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

        //投稿情報の更新
        BbsWriteInfModel bbsWriteInfMdl = new BbsWriteInfModel();
        bbsWriteInfMdl.setBwiSid(bwiSid);
        bbsWriteInfMdl.setBwiValue(paramMdl.getBbs090value());
        bbsWriteInfMdl.setBwiEuid(userSid);
        bbsWriteInfMdl.setBwiEdate(now);
        int contributor = 0;
        if (paramMdl.getBbs090contributor() > 0) {
            contributor = paramMdl.getBbs090contributor();
        }
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

        //スレッド閲覧情報の更新
        BbsThreViewModel threViewMdl = new BbsThreViewModel();
        threViewMdl.setBtiSid(btiSid);
        threViewMdl.setBivViewKbn(GSConstBulletin.BBS_THRE_VIEW_NO);
        threViewMdl.setBivEuid(userSid);
        threViewMdl.setBivEdate(now);
        BbsThreViewDao threViewDao = new BbsThreViewDao(con__);
        threViewDao.updateAllUserViewKbn(threViewMdl);

        //スレッド情報の更新(投稿者)
        BbsThreInfDao bbsThreInfDao = new BbsThreInfDao(con__);
        BbsThreInfModel bbsThreInfMdl = new BbsThreInfModel();
        bbsThreInfMdl.setBtiSid(btiSid);
        bbsThreInfMdl.setBtiEuid(userSid);
        bbsThreInfMdl.setBtiEdate(now);
        bbsThreInfMdl.setBtiEgid(contributor);
        bbsThreInfDao.updateContributor(bbsThreInfMdl);

        //スレッド集計情報の更新
        BbsBiz bbsBiz = new BbsBiz();
        bbsBiz.updateBbsThreSum(con__, btiSid, userSid, now, true);

        //フォーラム集計情報の更新
        bbsBiz.updateBbsForSum(con__, bfiSid, userSid, now);

        log__.debug("End");
    }

    /**
     * <br>[機  能] ショートメールで通知を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRootPath アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリ
     * @param pluginConfig PluginConfig
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void sendSmail(
        Bbs090knParamModel paramMdl,
        MlCountMtController cntCon,
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
        bsmlModel.setGrpSid(paramMdl.getBbs090contributor());
        //スレッドタイトル
        BulletinDspModel bbsMdl = biz.getThreadData(con__, paramMdl.getThreadSid());
        bsmlModel.setBtiTitle(bbsMdl.getBtiTitle());
        //投稿日時
        bsmlModel.setBwiAdate(new UDate());
        //添付ファイル名
        CommonBiz cmnBiz = new CommonBiz();
        List<LabelValueBean> files = cmnBiz.getTempFileLabelList(tempDir);
        bsmlModel.setFileLabelList(files);
        //投稿内容
        bsmlModel.setBwiValue(paramMdl.getBbs090value());
        //スレッドURL
        bsmlModel.setThreadUrl(
                biz.createThreadUrl(reqMdl, paramMdl.getBbs010forumSid(), paramMdl.getThreadSid()));

        //送信
        biz.sendSmail(con__, cntCon, bsmlModel, appRootPath, pluginConfig, reqMdl);
    }
}