package jp.groupsession.v2.cmn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.encryption.EncryptionException;
import jp.groupsession.v2.cmn.dao.base.CmnContmDao;
import jp.groupsession.v2.cmn.dao.base.CmnProxyAddressDao;
import jp.groupsession.v2.cmn.model.CmnContmModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.usr.GSPassword;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] プロキシサーバ設定に従ってHttpClientの生成を行う
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class GSHttpClient {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(GSHttpClient.class);

    /** プロキシサーバを使用するか */
    private boolean useProxy__ = false;
    /** プロキシサーバの接続情報 */
    private CmnContmModel ret__ = null;
    /** ユーザ認証を行うか */
    private boolean auth__ = false;
    /** ユーザ認証パラメータ ユーザ */
    String authUser__ = null;
    /** ユーザ認証パラメータ パスワード */
    String authPass__ = null;

    /** プロキシサーバを使用しないURL */
    private List<String> urlList__ = null;

    /**
     *<br> [機 能] コンストラクタ
     *<br> [解 説]
     *<br> [備 考]
     */
    @SuppressWarnings("all")
    private GSHttpClient() {
    }

    /**
     *<br> [機 能] コンストラクタ
     *<br> [解 説]
     *<br> [備 考]
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @throws EncryptionException パスワードの復号に失敗
     */
    public GSHttpClient(Connection con)
    throws SQLException, EncryptionException {
        CmnContmDao contDao = new CmnContmDao(con);
        ret__ = contDao.select();
        contDao = null;

        useProxy__ = ret__ != null && ret__.getCntPxyUse() == GSConstMain.PROXY_SERVER_USE;

        if (useProxy__) {
            log__.debug("プロキシを使用する");
            auth__ = ret__.getCntPxyAuth() == GSConstMain.PROXY_SERVER_USERAUTH_AUTH;
            if (auth__) {
                authUser__ = ret__.getCntPxyAuthId();
                authPass__ = GSPassword.getDecryPassword(ret__.getCntPxyAuthPass());
            }

            urlList__ = new ArrayList<String>();
            if (ret__.getCntPxyAdrkbn() == GSConstMain.PROXY_SERVER_ADRKBN_EXISTADDRESS) {
                CmnProxyAddressDao adrDao = new CmnProxyAddressDao(con);
                urlList__ = Arrays.asList(adrDao.getAddressList());
            }
        }

    }

    /**
     * <br>[機  能] HttpClientを取得する
     * <br>[解  説] CMN_CONTMからプロキシサーバ設定を取得する
     * <br>[備  考] ソケットがつながる迄のタイムアウト,繋がってからレスポンスを返す迄のタイムアウトは同じ値が設定されます。
     * @param url URL 接続先のURL
     * @param timeout タイムアウト
     * @return HttpClient
     */
    public HttpClient getHttpClient(String url, int timeout) {
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(timeout);
        params.setSoTimeout(timeout);
        HttpConnectionManager manager = new SimpleHttpConnectionManager();
        manager.setParams(params);
        HttpClient client = new HttpClient(manager);
        if (useProxy__) {
            boolean notUseProxy = false;
            for (String noUseUrl : urlList__) {
                if (url.indexOf(noUseUrl) >= 0) {
                    notUseProxy = true;
                    break;
                }
            }

            if (!notUseProxy) {
                HostConfiguration hconf = new HostConfiguration();
                hconf.setProxy(ret__.getCntPxyUrl(), ret__.getCntPxyPort());
                if (auth__) {
                    client.getState().setProxyCredentials(
                                new AuthScope(ret__.getCntPxyUrl(), ret__.getCntPxyPort()),
                                new UsernamePasswordCredentials(authUser__, authPass__));
                }
                client.setHostConfiguration(hconf);
            }
        }
        return client;
    }

//    /**
//     * <br>[機  能] HttpClientを取得する
//     * <br>[解  説] CMN_CONTMからプロキシサーバ設定を取得する
//     * <br>[備  考] ソケットがつながる迄のタイムアウト,繋がってからレスポンスを返す迄のタイムアウトは同じ値が設定されます。
//     * @param url URL 接続先のURL
//     * @param timeout タイムアウト
//     * @return HttpClient
//     */
//    public DefaultHttpClient getDHttpClient(String url, int timeout) {
//        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
//        params.setConnectionTimeout(timeout);
//        params.setSoTimeout(timeout);
//        HttpConnectionManager manager = new SimpleHttpConnectionManager();
//        manager.setParams(params);
//        DefaultHttpClient client = new DefaultHttpClient();
//
//        if (useProxy__) {
//            boolean notUseProxy = false;
//            for (String noUseUrl : urlList__) {
//                if (url.indexOf(noUseUrl) >= 0) {
//                    notUseProxy = true;
//                    break;
//                }
//            }
//
//            if (!notUseProxy) {
////                hconf.setProxy(ret__.getCntPxyUrl(), ret__.getCntPxyPort());
////                if (auth__) {
////                    hconf.setParams(authParam__);
////                }
////                client.setHostConfiguration(hconf);
//
////    client.getCredentialsProvider().setCredentials(
////            new AuthScope(ret__.getCntPxyUrl(), ret__.getCntPxyPort()),
////            new UsernamePasswordCredentials(authUser__, authPass__));
//DefaultHttpClient httpclient = new DefaultHttpClient();
//httpclient.getCredentialsProvider().setCredentials(
//        new AuthScope("192.168.1.47", 7777),
//        new UsernamePasswordCredentials("kaneuchi", "jts6181"));
//
//            }
//
//        }
//        return client;
//    }
}
