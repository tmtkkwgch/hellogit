package jp.groupsession.v2.rss;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstRss;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GSFeedList;
import jp.groupsession.v2.cmn.GSHttpClient;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.ITempFileUtil;
import jp.groupsession.v2.cmn.biz.LoggingBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnLogModel;
import jp.groupsession.v2.cmn.model.base.RssInfomModel;
import jp.groupsession.v2.rss.dao.RssAconfDao;
import jp.groupsession.v2.rss.dao.RssDataDao;
import jp.groupsession.v2.rss.dao.RssInfomDao;
import jp.groupsession.v2.rss.dao.RssPositionDao;
import jp.groupsession.v2.rss.model.RssAconfModel;
import jp.groupsession.v2.rss.model.RssDataModel;
import jp.groupsession.v2.rss.model.RssModel;
import jp.groupsession.v2.rss.model.RssPositionModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;

/**
 * <br>[機  能] RSSリーダープラグインの共通機能
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class RssBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RssBiz.class);

    /** RSSフィード情報更新停止フラグ */
    private static Map<String, Boolean> stopRssUpdateFlgMap__ = null;

    /** RSS更新処理 処理件数 */
    private static Map<String, Integer> rssUpdateCountMap__ = null;

    /** DBコネクション */
    private Connection con__ = null;
    /** リクエスト情報 */
    public RequestModel reqMdl__ = null;
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public RssBiz() {

    }
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
    public RssBiz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    public RssBiz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] フィード情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param feedUrl フィードURL
     * @param con コネクション
     * @return フィード情報
     * @throws Exception フィード情報の取得に失敗
     */
    public SyndFeed getFeedData(String feedUrl, Connection con) throws Exception {
        log__.debug("START");

        GSHttpClient client = new GSHttpClient(con);

        log__.debug("END");

        return getFeedData(feedUrl, client);

    }

    /**
     * <br>[機  能] フィード情報を取得する
     * <br>[解  説]
     * <br>[備  考] フィードを取得できなかった場合NULLを返します。取得できない場合エラーログがwarnレベルで記録されます。
     * @param feedUrl フィードURL
     * @param client クライアント
     * @return フィード情報
     */
    public SyndFeed getFeedData(String feedUrl, GSHttpClient client) {
        log__.debug("START");

        // HTTPを元にフィードを取得するクラス
        HttpClient httpClient = client.getHttpClient(feedUrl, GSConstRss.TIMEOUT);
        GSHttpClientFeedFetcher gsfet = new GSHttpClientFeedFetcher();
        gsfet.setClient(httpClient);
        FeedFetcher fetcher = gsfet;

        SyndFeed feed = null;
        try {
            feed = fetcher.retrieveFeed(new URL(feedUrl));
        } catch (Exception e) {
            log__.warn("フィードの取得に失敗 url = " + feedUrl, e);
        }

        //データを取得できた場合
        if (feed != null) {
            //title要素が取得できなかった場合、「Untitled(無題)」を設定する
            String title = NullDefault.getString(feed.getTitle(), "無題");
            feed.setTitle(StringUtilHtml.replaceString(title, "\n", "").trim());
            //link要素が取得できなかった場合は、フィードURLを設定する
            feed.setLink(NullDefault.getString(feed.getLink(), feedUrl));
        }
        log__.debug("END");
        return feed;
    }

    /**
     * <br>[機  能] RSSフィードを取得する(自動用)
     * <br>[解  説]
     * <br>[備  考]
     * @param rssInfo RSS情報
     * @param client クライアント
     * @return RSSフィード情報
     * @throws Exception RSSフィードの取得に失敗
     */
    @SuppressWarnings("unchecked")
    public GSFeedList getRssFeedForAutoUpdate(RssInfomModel rssInfo, GSHttpClient client)
    throws Exception {

        // URL, HTTPを元にフィードを取得する
        SyndFeed feed = getFeedData(rssInfo.getRsmUrlFeed(), client);
        List<SyndEntry> entrys = null;
        GSFeedList feedlist = new GSFeedList();

        if (feed != null) {
            entrys = (List<SyndEntry>) feed.getEntries();
        } else {
            return feedlist;
        }
        feedlist.setFeedSid(rssInfo.getRssSid());
        feedlist.setFtitle(StringUtilHtml.replaceString(feed.getTitle(), "\n", "").trim());
        feedlist.setFurl(feed.getLink());
        feedlist.setFeedUrl(rssInfo.getRsmUrlFeed());
        feedlist.setDescription(feed.getDescription());

        //件数制御
        if (entrys != null && entrys.size() > 0) {
            ArrayList<SyndEntry> fentrys = new ArrayList<SyndEntry>();
            for (SyndEntry ent : entrys) {
                fentrys.add(ent);
                //DescriptionをHTMLで正常に表示できる様に加工
                __toHtmlDescription(ent);
            }
            feedlist.setFeedList(fentrys);
        }
        return feedlist;
    }

    /**
     * <br>[機  能] RSSフィードを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rssData RSS情報
     * @param client クライアント
     * @return RSSフィード情報
     * @throws Exception RSSフィードの取得に失敗
     */
    @SuppressWarnings("unchecked")
    public GSFeedList getRssFeed(RssModel rssData, GSHttpClient client)
    throws Exception {

        // URL, HTTPを元にフィードを取得する
        RssBiz rssBiz = new RssBiz();
        SyndFeed feed = rssBiz.getFeedData(rssData.getRsdUrlFeed(), client);
        GSFeedList feedlist = new GSFeedList();
        List<SyndEntry> entrys = null;
        if (feed != null) {
            entrys = (List<SyndEntry>) feed.getEntries();
        } else {
            return feedlist;
        }


        feedlist.setFeedSid(rssData.getRssSid());
        feedlist.setDescription(feed.getDescription());
        feedlist.setFeedPosition(rssData.getRspPosition());
        feedlist.setFtitle(rssData.getRsdTitle());
        feedlist.setFurl(rssData.getRsdUrl());
        feedlist.setFeedUrl(rssData.getRsdUrlFeed());
        //件数制御
        if (entrys != null && entrys.size() > 0) {
            ArrayList<SyndEntry> fentrys = new ArrayList<SyndEntry>();
            int i = 0;
            for (SyndEntry ent : entrys) {
                i++;
                if (i > rssData.getRsdFeedCount()) {
                    break;
                }
                fentrys.add(ent);

                @SuppressWarnings("all")
                Class cls = ent.getInterface();
                //titleからスペース、改行を除去
                String entTitle = ent.getTitle();
                if (entTitle != null) {
                    entTitle = entTitle.trim();
                    entTitle = StringUtil.toDeleteReturnCode(entTitle);
                    ent.setTitle(entTitle);
                }
                //DescriptionをHTMLで正常に表示できる様に加工
                __toHtmlDescription(ent);

                log__.debug("ClassName = " + cls.getName());

                @SuppressWarnings("all")
                List mods = ent.getModules();
                for (Object obj : mods) {
                    log__.debug("  mod = " + obj.toString());
                }
            }
            feedlist.setFeedList(fentrys);
        }
        return feedlist;
    }

//    /**
//     * <br>[機  能] BLOBフィールド(Objectを格納)の読み込みを行う
//     * <br>[解  説]
//     * <br>[備  考] 取得できない場合エラーログがwarnレベルで記録されます。
//     * @param rs ResultSet
//     * @param fieldName フィールド名
//     * @return BLOBフィールドの情報
//     * @throws Exception BLOBフィールドの読み込みに失敗。エラー時はNUllを返す。
//     */
//    public Object readBlobField(ResultSet rs, String fieldName)
//    throws Exception {
//
//        Object value = null;
//        Blob feedData = rs.getBlob(fieldName);
//
//        if (feedData != null) {
//            ObjectInputStream ois = null;
//
//            try {
//                ois = new ObjectInputStream(feedData.getBinaryStream());
//                value = ois.readObject();
//            } catch (Exception e) {
//                log__.warn("BLOBフィールドの読み込みに失敗", e);
//                return value;
//            } finally {
//                if (ois != null) {
//                    ois.close();
//                }
//            }
//        }
//
//        return value;
//    }

    /**
     * <br>[機  能] RSS情報の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param rssModel RssDataModel
     * @return RSSSID
     * @throws Exception RSSの登録に失敗
     */
    public int entryRssData(
        Connection con,
        MlCountMtController cntCon,
        RssDataModel rssModel) throws Exception {

        UDate now = new UDate();
        String feedUrl = rssModel.getRsdUrlFeed();
        int userSid = rssModel.getRsdEuid();
        int authKbn = rssModel.getRsdAuth();
        String authId = rssModel.getRsdAuthId();
        String authPswd = rssModel.getRsdAuthPswd();
        String rsdtitle = rssModel.getRsdTitle();

        int rssSid = 0;
        RssInfomDao rssInfoDao = new RssInfomDao(con);
        ITempFileUtil itmp = (ITempFileUtil) GroupSession.getContext().get(
                GSContext.TEMP_FILE_UTIL);
        RssInfomModel rssInfoMdl = rssInfoDao.selectToFeedUrl(itmp, feedUrl);
        if (rssInfoMdl == null) {
            //RSSSID採番
            rssSid = (int) cntCon.getSaibanNumber(GSConstRss.SBNSID_RSS,
                                                    GSConstRss.SBNSID_SUB_RSS_ID,
                                                    userSid);

            //RSSマスタの登録
            rssInfoMdl = new RssInfomModel();
            rssInfoMdl.setRssSid(rssSid);
            rssInfoMdl.setRsmUrlFeed(feedUrl);
            rssInfoMdl.setRsmUpdateTime(UDate.getInstance(0));
            rssInfoMdl.setRsmAuth(authKbn);
            rssInfoMdl.setRsmAuthId(authId);
            rssInfoMdl.setRsmAuthPswd(authPswd);
            rssInfoMdl.setRsmAuid(userSid);
            rssInfoMdl.setRsmAdate(now);
            rssInfoMdl.setRsmEuid(userSid);
            rssInfoMdl.setRsmEdate(now);

            rssInfoDao.insert(rssInfoMdl);
        } else {
            rssSid = rssInfoMdl.getRssSid();
        }

        //RSS情報の登録
        rssModel.setRsdTitle(rsdtitle);
        rssModel.setRssSid(rssSid);
        rssModel.setUsrSid(userSid);
        rssModel.setRsdView(GSConstRss.RSS_VIEWFLG_SHOW);
        rssModel.setRsdAuth(authKbn);
        rssModel.setRsdAuthId(authId);
        rssModel.setRsdAuthPswd(authPswd);
        rssModel.setRsdAuid(userSid);
        rssModel.setRsdAdate(now);
        rssModel.setRsdEdate(now);

        RssDataDao rssDao = new RssDataDao(con);
        rssDao.insert(rssModel);

        //RSS位置情報の登録
        RssPositionModel rssPositionModel = new RssPositionModel();
        rssPositionModel.setRssSid(rssSid);
        rssPositionModel.setUsrSid(userSid);
        rssPositionModel.setRspPosition(GSConstRss.RSS_POSITIONFLG_LEFT);
        rssPositionModel.setRspOrder(0); //自動採番用に0を設定
        rssPositionModel.setRspAuid(userSid);
        rssPositionModel.setRspAdate(now);
        rssPositionModel.setRspEuid(userSid);
        rssPositionModel.setRspEdate(now);

        RssPositionDao rssPositionDao = new RssPositionDao(con);
        rssPositionDao.insert(rssPositionModel);

        return rssSid;
    }

    /**
     * <br>[機  能] RSS情報の存在チェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param rssSid RSSSID
     * @param userSid セッションユーザSID
     * @param con コネクション
     * @return チェックの結果 true:存在する false:存在しない
     * @throws SQLException SQL実行例外
     */
    public boolean existRssData(int rssSid, int userSid, Connection con)
    throws SQLException {

        RssDataDao rssDao = new RssDataDao(con);
        return rssDao.getRssData(rssSid, userSid) != null;
    }

    /**
     * <br>[機  能] RSS情報の登録処理を行う
     * <br>[解  説] フィード情報の取得に失敗した場合、DBに登録されているの情報を設定する。
     * <br>[備  考] 取得できない場合エラーログがwarnレベルで記録されます。
     * @param rssSid RSSSID
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param mainView メイン表示フラグ
     * @throws Exception 実行例外
     * @return RssDataModel RSS情報モデル
     */
    public RssDataModel insertRssData(
        int rssSid,
        Connection con,
        MlCountMtController cntCon,
        int userSid,
        int mainView) throws Exception {

        RssInfomDao rssInfoDao = new RssInfomDao(con);
        ITempFileUtil itmp = (ITempFileUtil) GroupSession.getContext().get(
                GSContext.TEMP_FILE_UTIL);
        RssInfomModel rssInfoMdl = rssInfoDao.select(itmp, rssSid);

        GSHttpClient gsClient = new GSHttpClient(con);

        RssBiz rssBiz = new RssBiz(con, reqMdl__);
        RssDataModel rssModel = new RssDataModel();
        try {
            SyndFeed feed = rssBiz.getFeedData(rssInfoMdl.getRsmUrlFeed(), gsClient);
            feed.getEntries();

            rssModel.setRsdUrlFeed(rssInfoMdl.getRsmUrlFeed());
            rssModel.setRsdTitle(feed.getTitle());
            rssModel.setRsdUrl(feed.getLink());
            rssModel.setRsdMainView(mainView);
            rssModel.setRsdAuth(rssInfoMdl.getRsmAuth());
            rssModel.setRsdAuthId(rssInfoMdl.getRsmAuthId());
            rssModel.setRsdAuthPswd(rssInfoMdl.getRsmAuthPswd());
            rssModel.setRsdFeedCount(GSConstRss.RSS_DEFAULT_VIEWCNT);
            rssModel.setRsdEuid(userSid);
            rssBiz.entryRssData(con, cntCon, rssModel);
        } catch (Exception e) {
            log__.warn("フィード情報の取得に失敗しました。", e);
            GSFeedList feedList = rssInfoMdl.getRsmFeeddata();
            rssModel.setRsdUrlFeed(rssInfoMdl.getRsmUrlFeed());
            rssModel.setRsdMainView(mainView);
            rssModel.setRsdFeedCount(GSConstRss.RSS_DEFAULT_VIEWCNT);
            rssModel.setRsdEuid(userSid);
            rssModel.setRsdAuth(rssInfoMdl.getRsmAuth());
            rssModel.setRsdAuthId(rssInfoMdl.getRsmAuthId());
            rssModel.setRsdAuthPswd(rssInfoMdl.getRsmAuthPswd());
            GsMessage gsMsg = new GsMessage(reqMdl__);
            //データを取得できませんでした
            String textNoData = gsMsg.getMessage("rss.31");
            if (feedList != null && feedList.getFtitle() != null) {
                rssModel.setRsdTitle(feedList.getFtitle());
            } else {
                rssModel.setRsdTitle(textNoData);
            }
            if (feedList != null && feedList.getFurl() != null) {
                rssModel.setRsdUrl(feedList.getFurl());
            } else {
                rssModel.setRsdUrl("noData");
            }

            rssBiz.entryRssData(con, cntCon, rssModel);
        }
        return rssModel;
    }

    /**
     * <br>[機  能] メイン表示フラグから表示文字列を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param mainView メイン表示フラグ
     * @param reqMdl リクエスト情報
     * @return String 表示文字列
     */
    public static String getMainViewStr(int mainView, RequestModel reqMdl) {

        String mainViewStr = "";

        GsMessage gsMsg = new GsMessage(reqMdl);

        if (mainView == GSConstRss.RSS_MAIN_VIEWFLG_SHOW) {
            String rssMainViewflgStrShow = gsMsg.getMessage("cmn.show");
            mainViewStr = rssMainViewflgStrShow;

        } else if (mainView == GSConstRss.RSS_MAIN_VIEWFLG_NOTSHOW) {
            String rssMainViewflgStrNotShow = gsMsg.getMessage("cmn.hide");
            mainViewStr = rssMainViewflgStrNotShow;

        }
        return mainViewStr;

    }

    /**
     * <br>[機  能] RSSフィード情報の更新を行う。
     * <br>[解  説] 更新基準時間にnullが設定された場合は全てのRSSフィード情報を更新する
     * <br>[備  考]
     * @param con コネクション
     * @param param パラメータ(Object)
     * @param updateTime 更新基準時間
     * @param domain ドメイン
     * @throws Exception RSSフィード情報の更新に失敗
     */
    public void updateFeedData(Connection con, Object param, UDate updateTime, String domain)
    throws Exception {

        setRssUpdateCount(domain, getRssUpdateCount(domain) + 1);
        try {
            //テンポラリディレクトリ取得、作成
            String tmpDir = getTempPath(param, domain);
            IOTools.isDirCheck(tmpDir, true);

            RssInfomDao feedDao = new RssInfomDao(con);
            List<RssInfomModel> upFeeds = null;
            if (updateTime == null) {
                upFeeds = feedDao.selectAllFeed();
            } else {
                upFeeds = feedDao.selectUpdateFeed(updateTime);
            }

            //Http Client
            GSHttpClient client = new GSHttpClient(con);

            //RSSフィードを取得し、DBにアップデート
            for (RssInfomModel feed : upFeeds) {
                if (isStopRssUpdateFlg(domain)) {
                    return;
                }

                boolean commitFlg = false;
                try {
                    feed.setRsmUpdateTime(new UDate());
                    feed.setRsmEuid(GSConst.SYSTEM_USER_ADMIN);
                    feed.setRsmEdate(feed.getRsmUpdateTime());
                    //フィードを取得
                    RssBiz rssBiz = new RssBiz(con__, reqMdl__);
                    GSFeedList gsfeed = rssBiz.getRssFeedForAutoUpdate(feed, client);

                    if (gsfeed == null) {
                        continue;
                    }
                    if (gsfeed.getFtitle() == null) {
                        continue;
                    }
                    if (gsfeed.getFurl() == null) {
                        continue;
                    }
                    gsfeed.setFeedUrl(feed.getRsmUrlFeed());
                    //オブジェクトをファイルに保存
                    ObjectFile objFile = new ObjectFile(tmpDir, String.valueOf(feed.getRssSid()));
                    objFile.save(gsfeed);

                    //DBを更新
                    ITempFileUtil tempFileUtil = (ITempFileUtil) GroupSession
                            .getContext().get(GSContext.TEMP_FILE_UTIL);
                    tempFileUtil.updateFeedData(con, feed, new File(tmpDir + feed.getRssSid()),
                            GroupSession.getResourceManager().getCountController(domain));
                    commitFlg = true;

                    //テンポラリに保存したファイルを削除
                    IOTools.deleteFile(tmpDir + feed.getRssSid());
                } catch (Exception e) {
                    log__.error("RSSフィードのアップデートに失敗", e);
                } finally {
                    if (commitFlg) {
                        con.commit();
                    } else {
                        JDBCUtil.rollback(con);
                    }
                }
            }
        } finally {
            setRssUpdateCount(domain, getRssUpdateCount(domain) - 1);
        }
    }

    /**
     * [機  能] アプリケーションのテンポラリディレクトリのパスを返す<br>
     * [解  説] <br>/${tmpルート}/rss/0/ を返す。
     * [備  考] <br>
     * @param param バッチ処理時に使用する情報
     * @param domain ドメイン
     * @return パス
     */
    public String getTempPath(Object param, String domain) {

        String tmp = GroupSession.getResourceManager().getTempPath(domain);

        StringBuilder tempDir = new StringBuilder("");
        tempDir.append(tmp);
        tempDir.append("/");
        tempDir.append(GSConstRss.PLUGIN_ID_RSS);
        tempDir.append("/");
        tempDir.append(GSConst.SYSTEM_USER_ADMIN);
        tempDir.append("/");

        String savePath = IOTools.replaceFileSep(tempDir.toString());
        return savePath;
    }

    /**
     * <br>[機  能] SyndEntryのdescriptionをhtmlで表示する様に変更する
     * <br>[解  説]
     * <br>[備  考]
     * @param ent SyndEntry
     */
    private void __toHtmlDescription(SyndEntry ent) {
        SyndContent desc = ent.getDescription();
        if (desc == null) {
            return;
        }
        String description = desc.getValue();
        //タグを除去する
        String strTmp = StringUtilHtml.deleteHtmlTag(description);
        strTmp = StringUtilHtml.transToHTml(strTmp);
        desc.setValue(strTmp);
    }

    /**
     * <br>[機  能] リスト内のフィードデータがNULLの場合はHTTPからフィード情報を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param rssNewrankingDataList 新着ランキングリスト
     * @param gsClient GSHttpClient
     * @return rssRankingDataList 新着ランキングリスト
     * @throws Exception RSSフィード情報の取得に失敗
     */
    public List<RssInfomModel> getNewranking(
            List<RssInfomModel> rssNewrankingDataList, GSHttpClient gsClient)
    throws Exception {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //データを取得できませんでした
        String textNoData = gsMsg.getMessage("rss.31");
        for (RssInfomModel model : rssNewrankingDataList) {
            GSFeedList feedList = model.getRsmFeeddata();

            if (feedList == null || feedList.getFtitle() == null) {
                feedList = new GSFeedList();
                SyndFeed newFeed = getFeedData(model.getRsmUrlFeed(), gsClient);
                if (newFeed != null) {
                    feedList.setFtitle(newFeed.getTitle());
                    feedList.setFurl(newFeed.getLink());
                } else {
                    feedList.setFtitle(textNoData);
                }
                feedList.setFeedUrl(model.getRsmUrlFeed());
            }
            model.setTitle(feedList.getFtitle());
            model.setUrl(feedList.getFurl());
        }
        return rssNewrankingDataList;
    }

    /**
     * <br>[機  能] リスト内のフィードデータがNULLの場合はHTTPからフィード情報を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param rssRankingDataList 登録ランキングリスト
     * @param gsClient GSHttpClient
     * @return rssRankingDataList 登録ランキングリスト
     * @throws Exception RSSフィード情報の取得に失敗
     */
    public List<RssModel> getRanking(List<RssModel> rssRankingDataList,
                                    GSHttpClient gsClient)
    throws Exception {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //データを取得できませんでした
        String textNoData = gsMsg.getMessage("rss.31");
        for (RssModel rankingMdl : rssRankingDataList) {
            GSFeedList feedList = rankingMdl.getFeedData();
            if (feedList == null || feedList.getFtitle() == null) {
                SyndFeed feed = getFeedData(rankingMdl.getRsdUrlFeed(), gsClient);
                feedList = new GSFeedList();
                if (feed != null) {
                    rankingMdl.setRsdTitle(feed.getTitle());
                    rankingMdl.setRsdUrl(feed.getLink());
                    rankingMdl.setDescription(feedList.getDescription());
                } else {
                    feedList.setFtitle(textNoData);
                }
            } else {
                rankingMdl.setRsdTitle(feedList.getFtitle());
                rankingMdl.setRsdUrl(feedList.getFurl());
                rankingMdl.setDescription(feedList.getDescription());
            }
        }
        return rssRankingDataList;
    }

    /**
     * <br>[機  能] 登録RSS情報を設定する。
     * <br>[解  説] リストにRSS情報が入っていない時はHTTPからフィード情報を取得する。
     * <br>[備  考]
     * @param rssDataList RSS一覧
     * @param updateTime システム時刻
     * @param client GSHttpClient
     * @param dir RSSフィード情報保存用ディレクトリパス
     * @param updateRssSidList 更新をかけるRSSSIDのリスト
     * @param domain ドメイン
     * @return List in GSFeedList
     */
     public List<GSFeedList> setRssList(
        List<RssModel> rssDataList,
        UDate updateTime,
        GSHttpClient client,
        String dir,
        List<String> updateRssSidList,
        String domain) {

        List<GSFeedList> flist = new ArrayList<GSFeedList>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //データを取得できませんでした
        String textNoData = gsMsg.getMessage("rss.31");
        for (RssModel rssData : rssDataList) {
            if (isStopRssUpdateFlg(domain)) {
                break;
            }

            GSFeedList feed = rssData.getFeedData();

            boolean addFeed = true;
            int rssViewCount = rssData.getRsdFeedCount();
            rssData.setRsdFeedCount(10);
            if (feed == null || feed.getFtitle() == null
            || rssData.getFeedUpdateTime().getTimeMillis() < updateTime.getTimeMillis()) {
                try {
                    feed = getRssFeed(rssData, client);
                    if (feed == null || feed.getFtitle() == null) {
                        feed.setFtitle(textNoData);
                        feed.setFeedSid(rssData.getRssSid());
                        feed.setFurl(rssData.getRsdUrl());
                        feed.setReadError(true);
                    } else {
                        feed.setReadError(false);
                    }
                    IOTools.isDirCheck(dir, true);
                    ObjectFile objFile = new ObjectFile(dir, String.valueOf(rssData.getRssSid()));
                    objFile.save(feed);

                    updateRssSidList.add(String.valueOf(rssData.getRssSid()));
                } catch (Exception e) {
                    log__.error("RSSフィードの読み込みに失敗 :" + rssData.getRsdTitle(), e);
                    addFeed = false;
                }
            } else {
                feed.setFtitle(rssData.getRsdTitle());
                feed.setFurl(rssData.getRsdUrl());
                feed.setFeedPosition(rssData.getRspPosition());
                updateRssSidList.add(String.valueOf(rssData.getRssSid()));
            }
            feed.setFeedUrl(rssData.getRsdUrlFeed());

            if (addFeed) {

                int count = 1;
                ArrayList<SyndEntry> entryList = new ArrayList<SyndEntry>();

                if (feed.getFeedList() != null) {
                    for (SyndEntry entry : feed.getFeedList()) {
                        if (count > rssViewCount) {
                            break;
                        }
                        entryList.add(entry);
                        count++;
                    }
                }
                rssData.setRsdFeedCount(rssViewCount);

                feed.setFeedList(entryList);
                flist.add(feed);
            }

            //改行コードを削除する
            for (int i = 0; i < feed.getFeedList().size(); i++) {
                String title = feed.getFeedList().get(i).getTitle();
                feed.getFeedList().get(i).setTitle(StringUtil.replaceReturnCode(title, ""));
            }
        }
        return flist;
    }

    /**
     * <br>[機  能] 更新基準日時を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return 更新基準日時
     * @throws SQLException SQL実行時例外
     */
    public UDate getUpdateTime(Connection con) throws SQLException {
        RssAconfDao aconfDao = new RssAconfDao(con);
        RssAconfModel aconfMdl = aconfDao.select();
        UDate updateTime = new UDate();

        int readTime = 30;
        if (aconfMdl != null) {
            readTime = aconfMdl.getRacReadtime();
        }
        updateTime.addMinute(readTime * -1);

        return updateTime;
    }

    /**
     * RSS全般のログ出力を行う
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

        BaseUserModel usModel = reqMdl.getSmodel();
        int usrSid = -1;
        if (usModel != null) {
            usrSid = usModel.getUsrsid(); //セッションユーザSID
        }

        UDate now = new UDate();
        CmnLogModel logMdl = new CmnLogModel();
        GsMessage gsMsg = new GsMessage(reqMdl);

        logMdl.setLogDate(now);
        logMdl.setUsrSid(usrSid);
        logMdl.setLogLevel(level);
        logMdl.setLogPlugin(GSConstRss.PLUGIN_ID_RSS);
        logMdl.setLogPluginName(gsMsg.getMessage("rss.3"));
        String type = map.getType();
        logMdl.setLogPgId(StringUtil.trimRengeString(type, 100));
        logMdl.setLogPgName(getPgName(map.getType(), reqMdl));
        logMdl.setLogOpCode(opCode);
        logMdl.setLogOpValue(value);
        logMdl.setLogIp(reqMdl.getRemoteAddr());
        logMdl.setVerVersion(GSConst.VERSION);

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
        if (id.equals("jp.groupsession.v2.rss.newranking.RssNewRankingAction")) {
            //メイン RSS新着一覧
            return gsMsg.getMessage("rss.new.man.rss");
        }
        if (id.equals("jp.groupsession.v2.rss.rss010.Rss010Action")) {
            //RSS一覧
            return gsMsg.getMessage("rss.rssmain.1");
        }
        if (id.equals("jp.groupsession.v2.rss.rss030.Rss030Action")) {
            //RSS登録・編集
            return gsMsg.getMessage("rss.subscribe.to.edit");
        }
        if (id.equals("jp.groupsession.v2.rss.rss030kn.Rss030knAction")) {
            //RSS登録・編集確認
            return gsMsg.getMessage("rss.subscribe.to.edit.kn");
        }
        if (id.equals("jp.groupsession.v2.rss.rss040.Rss040Action")) {
            //RSSリーダー登録ランキング
            return gsMsg.getMessage("rss.reader.rank");
        }
        if (id.equals("jp.groupsession.v2.rss.rss060.Rss060Action")) {
            //個人設定 メイン画面表示設定
            return gsMsg.getMessage("rss.set.main.dsp");
        }
        if (id.equals("jp.groupsession.v2.rss.rss080.Rss080Action")) {
            //管理者設定 メンテナンス
            return gsMsg.getMessage("rss.adm.maintenance");
        }
        if (id.equals("jp.groupsession.v2.rss.rss100.Rss100Action")) {
            //個人設定 新着RSS表示日数設定
            return gsMsg.getMessage("rss.newrss.dspday");
        }
        return ret;
    }

    /**
     * <p>stopRssUpdateFlg を取得します。
     * @param domain ドメイン
     * @return stopRssUpdateFlg
     */
    public static boolean isStopRssUpdateFlg(String domain) {

        boolean flg = false;
        if (stopRssUpdateFlgMap__ != null && stopRssUpdateFlgMap__.get(domain) != null) {
            flg = stopRssUpdateFlgMap__.get(domain);
        } else {
            stopRssUpdateFlgMap__ = new HashMap<String, Boolean>();
            stopRssUpdateFlgMap__.put(domain, false);
        }
        return flg;
    }

    /**
     * <p>stopRssUpdateFlg をセットします。
     * @param domain ドメイン
     * @param stopRssUpdateFlg stopRssUpdateFlg
     */
    public static void setStopRssUpdateFlg(String domain, boolean stopRssUpdateFlg) {
        if (stopRssUpdateFlgMap__ != null && stopRssUpdateFlgMap__.get(domain) != null) {
            stopRssUpdateFlgMap__.put(domain, stopRssUpdateFlg);
        } else {
            stopRssUpdateFlgMap__ = new HashMap<String, Boolean>();
            stopRssUpdateFlgMap__.put(domain, stopRssUpdateFlg);
        }
    }

    /**
     * <p>rssUpdateCount を取得します。
     * @param domain ドメイン
     * @return rssUpdateCount
     */
    public static int getRssUpdateCount(String domain) {

        int cnt = 0;
        if (rssUpdateCountMap__ != null && rssUpdateCountMap__.get(domain) != null) {
            cnt = rssUpdateCountMap__.get(domain);
        } else {
            rssUpdateCountMap__ = new HashMap<String, Integer>();
            rssUpdateCountMap__.put(domain, 0);
        }
        return cnt;
    }

    /**
     * <p>rssUpdateCount をセットします。
     * @param domain ドメイン
     * @param rssUpdateCount rssUpdateCount
     */
    public static void setRssUpdateCount(String domain, int rssUpdateCount) {
        if (rssUpdateCountMap__ != null && rssUpdateCountMap__.get(domain) != null) {
            rssUpdateCountMap__.put(domain, rssUpdateCount);
        } else {
            rssUpdateCountMap__ = new HashMap<String, Integer>();
            rssUpdateCountMap__.put(domain, rssUpdateCount);
        }
    }

}