package jp.groupsession.v2.rss;

import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;

/**
 * <br>[機  能] RssFeedのテストクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class RssFeedTest {

    /** フィードのURL */
    private static final String[] FEED_URLS__ = {
        "http://www.pheedo.jp/f/slashdot_japan",
        "http://rss.rssad.jp/rss/itm/news/rss.xml",
      };

    /**
     * <br>[機  能] メインメソッド
     * <br>[解  説]
     * <br>[備  考]
     * @param args コマンドライン引数
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // HTTPを元にフィードを取得するクラス「FeedFetcher」 ………(1)
        FeedFetcher fetcher = new HttpURLFeedFetcher();

        try {
            // フィードの内容、フィードに含まれる記事エントリの内容を出力する
            for (String url : FEED_URLS__) {
                // フィードの取得 ………(2)
                SyndFeed feed = fetcher.retrieveFeed(new URL(url));

                System.out.format("フィードタイトル:[%s] 著者:[%s]\n",
                    feed.getTitle(),
                      feed.getUri());

                for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
                    System.out.format("\t更新時刻:[%s] URL:[%s] 記事タイトル:[%s]\n",
                        entry.getPublishedDate(),
                        entry.getLink(),
                        entry.getTitle());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
