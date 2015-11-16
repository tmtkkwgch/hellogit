package jp.groupsession.v2.rss.rss030;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.cmn.GSConstRss;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rss.dao.RssDataDao;
import jp.groupsession.v2.rss.dao.RssInfomDao;
import jp.groupsession.v2.rss.dao.RssPositionDao;
import jp.groupsession.v2.rss.dao.RssPositionMainDao;
import jp.groupsession.v2.rss.model.RssDataModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] RSSリーダー RSS登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rss030Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rss030Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説] 処理モード = 編集の場合、RSS情報を設定する
     * <br>[備  考]
     * @param cmd CMDパラメータ
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @throws SQLException 実行例外
     */
    public void setInitData(String cmd, RequestModel reqMdl, Rss030ParamModel paramMdl,
                            Connection con, int userSid)
    throws SQLException {
        log__.debug("START");

        List<LabelValueBean> viewCntList = new ArrayList<LabelValueBean>();
        for (int cnt = 1; cnt <= GSConstRss.MIDASI_MAX_COUNT; cnt++) {
            String count = String.valueOf(cnt);
            GsMessage gsMsg = new GsMessage(reqMdl);
            //件
            String textKen = gsMsg.getMessage("cmn.number");
            LabelValueBean label = new LabelValueBean(count + textKen, count);
            viewCntList.add(label);
        }
        paramMdl.setViewCntList(viewCntList);

        if (cmd.equals("rssEdit") && paramMdl.getRssCmdMode() == GSConstRss.RSSCMDMODE_EDIT) {
            RssDataDao rssDao = new RssDataDao(con);
            RssDataModel rssMdl = rssDao.getRssData(paramMdl.getRssSid(), userSid);

            paramMdl.setRssTitle(rssMdl.getRsdTitle());
            paramMdl.setRssFeedUrl(rssMdl.getRsdUrlFeed());
            paramMdl.setRssBeforeFeedUrl(rssMdl.getRsdUrlFeed());
            paramMdl.setRssUrl(rssMdl.getRsdUrl());
            paramMdl.setRss030ViewCnt(rssMdl.getRsdFeedCount());
            paramMdl.setRss030mainView(rssMdl.getRsdMainView());
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] RSS情報の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param rssSid RSSSID
     * @param userSid ユーザSID
     * @throws SQLException 実行例外
     */
    public void deleteRssData(Connection con, int rssSid, int userSid)
    throws SQLException {
        log__.debug("START");

        //RSS情報の削除
        RssDataDao rssDao = new RssDataDao(con);
        rssDao.delete(rssSid, userSid);

        //RSS位置情報の削除
        RssPositionDao rssPositionDao = new RssPositionDao(con);
        rssPositionDao.delete(rssSid, userSid);

        //RSSマスタの削除
        RssInfomDao rssInfoDao = new RssInfomDao(con);
        rssInfoDao.deleteToDontUseData(rssSid);

        //RSS位置情報_メイン画面の削除
        RssPositionMainDao rssPositionMainDao = new RssPositionMainDao(con);
        rssPositionMainDao.delete(rssSid, userSid);

        log__.debug("End");
    }

}
