package jp.groupsession.v2.rss.rss030kn;

import java.sql.Connection;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rss.RssBiz;
import jp.groupsession.v2.rss.dao.RssDataDao;
import jp.groupsession.v2.rss.dao.RssInfomDao;
import jp.groupsession.v2.rss.dao.RssPositionDao;
import jp.groupsession.v2.rss.model.RssDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] RSSリーダー RSS登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rss030knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rss030knBiz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void setInitData(Rss030knParamModel paramMdl, Connection con, RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        //メイン表示
        paramMdl.setStrMainView(RssBiz.getMainViewStr(paramMdl.getRss030mainView(), reqMdl));

        log__.debug("End");
    }

    /**
     * <br>[機  能] RSS情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void insertRssData(Rss030knParamModel paramMdl,
                                Connection con,
                                MlCountMtController cntCon,
                                int userSid,
                                RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        //更新用のRssDataModelを取得する
        RssDataModel rssModel = __getRssDataModel(paramMdl, userSid);

        RssBiz rssBiz = new RssBiz(reqMdl);
        rssBiz.entryRssData(con, cntCon, rssModel);

        log__.debug("End");
    }

    /**
     * <br>[機  能] RSS情報の更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void updateRssData(Rss030knParamModel paramMdl,
                                Connection con,
                                MlCountMtController cntCon,
                                int userSid,
                                RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        UDate now = new UDate();
        int rssSid = paramMdl.getRssSid();

        //RSS情報を取得する
        RssDataDao rssDao = new RssDataDao(con);
        RssDataModel rssMdl = rssDao.getRssData(rssSid, userSid);
        if (!rssMdl.getRsdUrlFeed().equals(paramMdl.getRssFeedUrl())) {
            //フィードURLが変更されていた場合は新規登録を行う
            rssDao.delete(rssSid, userSid);
            RssPositionDao positionDao = new RssPositionDao(con);
            positionDao.delete(rssSid, userSid);

            //購読者が0人の場合、RSSマスタを削除する
            RssInfomDao rssInfoDao = new RssInfomDao(con);
            rssInfoDao.deleteToDontUseData(rssSid);

            //更新用のRssDataModelを取得する
            RssDataModel rssModel = __getRssDataModel(paramMdl, userSid);

            RssBiz rssBiz = new RssBiz(reqMdl);
            rssBiz.entryRssData(con, cntCon, rssModel);
        } else {
            //RSS情報の更新
            RssDataModel rssModel = __getRssDataModel(paramMdl, userSid);
            rssModel.setRssSid(paramMdl.getRssSid());
            rssModel.setUsrSid(userSid);
            rssModel.setRsdAuth(paramMdl.getRssAuth());
            rssModel.setRsdAuthId(paramMdl.getRssAuthId());
            rssModel.setRsdAuthPswd(paramMdl.getRssAuthPswd());
            rssModel.setRsdEdate(now);

            rssDao.update(rssModel);
        }
        log__.debug("End");
    }

    /**
     * <br>[機  能] 更新用のRssDataModelを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @return RssDataModel
     */
    private RssDataModel __getRssDataModel(Rss030knParamModel paramMdl, int userSid) {

        RssDataModel rssModel = new RssDataModel();
        rssModel.setRsdUrlFeed(paramMdl.getRssFeedUrl());
        //スペース・改行を省く
        rssModel.setRsdTitle(paramMdl.getRssTitle());
        rssModel.setRsdUrl(paramMdl.getRssUrl());
        rssModel.setRsdMainView(paramMdl.getRss030mainView());
        rssModel.setRsdFeedCount(paramMdl.getRss030ViewCnt());
        rssModel.setRsdAuth(paramMdl.getRssAuth());
        rssModel.setRsdAuthId(paramMdl.getRssAuthId());
        rssModel.setRsdAuthPswd(paramMdl.getRssAuthPswd());
        rssModel.setRsdEuid(userSid);
        return rssModel;
    }
}
