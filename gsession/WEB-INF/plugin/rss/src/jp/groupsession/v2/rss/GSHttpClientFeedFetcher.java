package jp.groupsession.v2.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FetcherEvent;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HttpClientFeedFetcher;
import com.sun.syndication.fetcher.impl.SyndFeedInfo;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * <br>[機  能] HttpClientFeedFetcherではProxy設定済みのHttpClientが使用できないため、それを可能としたクラス。
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class GSHttpClientFeedFetcher extends HttpClientFeedFetcher {

    /** HTTP CLIENT */
    private HttpClient client__ = null;

    /**
     * @see com.sun.syndication.fetcher.FeedFetcher#retrieveFeed(java.net.URL)
     * @param feedUrl フィードURL
     * @throws IllegalArgumentException IllegalArgumentException
     * @throws IOException IOException
     * @throws FeedException FeedException
     * @throws FetcherException FetcherException
     * @return SyndFeed
     */
    @SuppressWarnings("deprecation")
    public SyndFeed retrieveFeed(URL feedUrl)
    throws IllegalArgumentException, IOException, FeedException, FetcherException {
        if (feedUrl == null) {
            throw new IllegalArgumentException("null is not a valid URL");
        }
        // Fix this
        //System.setProperty("org.apache.commons.logging.Log",
        //                   "org.apache.commons.logging.impl.SimpleLog");
        HttpClient client = client__;

        if (getCredentialSupplier() != null) {
            client.getState().setAuthenticationPreemptive(true);
            // what should realm be here?
            Credentials credentials = getCredentialSupplier().getCredentials(null,
                                                                            feedUrl.getHost());
            if (credentials != null) {
                client.getState().setCredentials(null, feedUrl.getHost(), credentials);
            }
        }

        System.setProperty("httpclient.useragent", getUserAgent());
        String urlStr = feedUrl.toString();
        FeedFetcherCache cache = getFeedInfoCache();
        if (cache != null) {
            // retrieve feed
            HttpMethod method = new GetMethod(urlStr);
            method.addRequestHeader("Accept-Encoding", "gzip");
            try {
                if (isUsingDeltaEncoding()) {
                    method.setRequestHeader("A-IM", "feed");
                }

                // get the feed info from the cache
                // Note that syndFeedInfo will be null if it is not in the cache
                SyndFeedInfo syndFeedInfo = cache.getFeedInfo(feedUrl);
                if (syndFeedInfo != null) {
                    method.setRequestHeader("If-None-Match", syndFeedInfo.getETag());

                    if (syndFeedInfo.getLastModified() instanceof String) {
                        method.setRequestHeader("If-Modified-Since",
                                                (String) syndFeedInfo.getLastModified());
                    }
                }

                method.setFollowRedirects(true);

                int statusCode = client.executeMethod(method);
                fireEvent(FetcherEvent.EVENT_TYPE_FEED_POLLED, urlStr);
                handleErrorCodes(statusCode);

                SyndFeed feed = getFeed(syndFeedInfo, urlStr, method, statusCode);

                syndFeedInfo = buildSyndFeedInfo(feedUrl, urlStr, method, feed, statusCode);

                cache.setFeedInfo(new URL(urlStr), syndFeedInfo);

                // the feed may have been modified to pick up cached values
                // (eg - for delta encoding)
                feed = syndFeedInfo.getSyndFeed();

                return feed;
            } finally {
                method.releaseConnection();
            }

        } else {
            // cache is not in use
            HttpMethod method = new GetMethod(urlStr);
            try {
                method.setFollowRedirects(true);

                int statusCode = client.executeMethod(method);
                fireEvent(FetcherEvent.EVENT_TYPE_FEED_POLLED, urlStr);
                handleErrorCodes(statusCode);

                return getFeed(null, urlStr, method, statusCode);
            } finally {
                method.releaseConnection();
            }
        }
    }

    /**
     * <br>[機  能] getFeed
     * <br>[解  説]
     * <br>[備  考]
     * @param syndFeedInfo syndFeedInfo
     * @param urlStr urlStr
     * @param method method
     * @param statusCode statusCode
     * @throws IOException IOException
     * @throws HttpException HttpException
     * @throws FeedException FeedException
     * @throws FetcherException FetcherException
     * @return SyndFeed
     */
    private SyndFeed getFeed(SyndFeedInfo syndFeedInfo, String urlStr,
                            HttpMethod method, int statusCode)
    throws IOException, HttpException, FetcherException, FeedException {

        if (statusCode == HttpURLConnection.HTTP_NOT_MODIFIED && syndFeedInfo != null) {
            fireEvent(FetcherEvent.EVENT_TYPE_FEED_UNCHANGED, urlStr);
            return syndFeedInfo.getSyndFeed();
        }

        SyndFeed feed = retrieveFeed(urlStr, method);
        fireEvent(FetcherEvent.EVENT_TYPE_FEED_RETRIEVED, urlStr, feed);
        return feed;
    }

    /**
     * @param feedUrl フィードURL
     * @param urlStr URL
     * @param method メソッド
     * @param feed フィード
     * @param statusCode statusCode
     * @return SyndFeedInfo
     * @throws MalformedURLException MalformedURLException
     */
    private SyndFeedInfo buildSyndFeedInfo(URL feedUrl, String urlStr, HttpMethod method,
                                            SyndFeed feed, int statusCode)
    throws MalformedURLException {
        SyndFeedInfo syndFeedInfo;
        syndFeedInfo = new SyndFeedInfo();

        // this may be different to feedURL because of 3XX redirects
        syndFeedInfo.setUrl(new URL(urlStr));
        syndFeedInfo.setId(feedUrl.toString());

        Header imHeader = method.getResponseHeader("IM");
        if (imHeader != null && imHeader.getValue().indexOf("feed") >= 0
        && isUsingDeltaEncoding()) {
            FeedFetcherCache cache = getFeedInfoCache();
            if (cache != null && statusCode == 226) {
                // client is setup to use http delta encoding and the server
                // supports it and has returned a delta encoded response
                // This response only includes new items
                SyndFeedInfo cachedInfo = cache.getFeedInfo(feedUrl);
                if (cachedInfo != null) {
                    SyndFeed cachedFeed = cachedInfo.getSyndFeed();

                    // set the new feed to be the orginal feed plus the new items
                    feed = combineFeeds(cachedFeed, feed);
                }
            }
        }

        Header lastModifiedHeader = method.getResponseHeader("Last-Modified");
        if (lastModifiedHeader != null) {
            syndFeedInfo.setLastModified(lastModifiedHeader.getValue());
        }

        Header eTagHeader = method.getResponseHeader("ETag");
        if (eTagHeader != null) {
            syndFeedInfo.setETag(eTagHeader.getValue());
        }

        syndFeedInfo.setSyndFeed(feed);

        return syndFeedInfo;
    }

    /**
     * @param urlStr URL
     * @param method メソッド
     * @return SyndFeed
     * @throws IOException IOException
     * @throws HttpException HttpException
     * @throws FetcherException FetcherException
     * @throws FeedException FeedException
     */
    private static SyndFeed retrieveFeed(String urlStr, HttpMethod method)
    throws IOException, HttpException, FetcherException, FeedException {

        InputStream stream = null;
        if ((method.getResponseHeader("Content-Encoding") != null)
        && ("gzip".equalsIgnoreCase(method.getResponseHeader("Content-Encoding").getValue()))) {
            stream = new GZIPInputStream(method.getResponseBodyAsStream());
        } else {
            stream = method.getResponseBodyAsStream();
        }
        try {
            XmlReader reader = null;
            if (method.getResponseHeader("Content-Type") != null) {
                reader = new XmlReader(stream, method.getResponseHeader("Content-Type").getValue(),
                                    true);
            } else {
                reader = new XmlReader(stream, true);
            }
            return new SyndFeedInput().build(reader);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
    /**
     * <p>client を取得します。
     * @return client
     */
    public HttpClient getClient() {
        return client__;
    }
    /**
     * <p>client をセットします。
     * @param client client
     */
    public void setClient(HttpClient client) {
        client__ = client;
    }
}
