package jp.groupsession.v2.cmn.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.model.RequestModel;

/**
 * <br>[機  能] プラグイン設定情報を格納するModelクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class PluginConfig {

    /** プラグインID一覧 */
    private List < String > pluginIdList__ = null;
    /** プラグイン情報保持用Map */
    private Map < String, Plugin > pluginMap__ = null;

    /**
     * <br>[機  能] プラグイン設定情報のコピーを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return プラグイン設定情報
     */
    public PluginConfig clonePluginConfig(RequestModel reqMdl) {
        PluginConfig pconfig = new PluginConfig();

        for (Plugin plugin : getPluginDataList()) {
            pconfig.addPlugin(plugin.clonePlugin(reqMdl));
        }
        return pconfig;
    }

    /**
     * コンストラクタ
     */
    public PluginConfig() {
        pluginIdList__ = new ArrayList<String>();
        pluginMap__ = new HashMap<String, Plugin>();
    }

    /**
     * プラグイン情報一覧を取得する
     * @return プラグイン情報一覧
     */
    public List < Plugin > getPluginDataList() {
        List<Plugin> pluginDataList = new ArrayList<Plugin>();
        for (String pluginId : getPluginIdList()) {
            pluginDataList.add(pluginMap__.get(pluginId));
        }
        return pluginDataList;
    }

    /**
     * プラグインID一覧を取得する
     * @return プラグインID一覧
     */
    public synchronized List < String > getPluginIdList() {

        List<String> userPlist = new ArrayList<String>();

        if (pluginIdList__ != null && !pluginIdList__.isEmpty()) {
            //ユーザ毎の別なインスタンスを作成
            userPlist.addAll(pluginIdList__);
            //メインの順序を1番目に設定
            if (userPlist.indexOf(GSConst.PLUGINID_MAIN) >= 0) {
                userPlist.remove(GSConst.PLUGINID_MAIN);
                userPlist.add(0, GSConst.PLUGINID_MAIN);
            }

            //ユーザ情報を2番目に表示する
            if (userPlist.indexOf(GSConst.PLUGINID_USER) > 0) {
                userPlist.remove(GSConst.PLUGINID_USER);
                userPlist.add(1, GSConst.PLUGINID_USER);
            }
        }

        return userPlist;
    }

    /**
     * ユーザの作成したプラグインID一覧を取得する
     * @return プラグインID一覧
     */
    public synchronized ArrayList < String > getUserPluginIdList() {

        ArrayList<String> userPlist = new ArrayList<String>();

        if (pluginIdList__ != null && !pluginIdList__.isEmpty()) {
            //ユーザ毎の別なインスタンスを作成
            for (String id : pluginIdList__) {
                 if (!id.equals(GSConst.PLUGINID_MAIN)
                     && !id.equals(GSConst.PLUGINID_USER)
                     && !id.equals(GSConst.PLUGINID_API)
                     && !id.equals(GSConst.PLUGINID_BMK)
                     && !id.equals(GSConst.PLUGINID_CIR)
                     && !id.equals(GSConst.PLUGINID_HELP)
                     && !id.equals(GSConst.PLUGINID_IP)
                     && !id.equals(GSConst.PLUGINID_LIC)
                     && !id.equals(GSConst.PLUGINID_MOBILE)
                     && !id.equals(GSConst.PLUGINID_PORTAL)
                     && !id.equals(GSConst.PLUGINID_PRJ)
                     && !id.equals(GSConst.PLUGINID_RSS)
                     && !id.equals(GSConst.PLUGINID_SCH)
                     && !id.equals(GSConst.PLUGINID_SEARCH)
                     && !id.equals(GSConst.PLUGINID_SML)
                     && !id.equals(GSConst.PLUGINID_TIMECARD)
                     && !id.equals(GSConst.PLUGINID_WML)
                     && !id.equals(GSConst.PLUGINID_ZSK)
                     && !id.equals(GSConst.PLUGIN_ID_RESERVE)
                     && !id.equals(GSConst.PLUGIN_ID_FILE)
                     && !id.equals(GSConst.PLUGIN_ID_BULLETIN)
                     && !id.equals(GSConst.PLUGINID_ADDRESS)
                     && !id.equals(GSConst.PLUGIN_ID_RINGI)
                     && !id.equals(GSConst.PLUGINID_COMMON)
                     && !id.equals(GSConst.PLUGIN_ID_NIPPOU)
                     && !id.equals(GSConst.PLUGIN_ID_ANPI)
                     && !id.equals(GSConst.PLUGIN_ID_ENQUETE)
                     && !id.equals(GSConst.PLUGIN_ID_LDAP)) {
                    userPlist.add(id);
                 }
            }
        }

        return userPlist;
    }

    /**
     * プラグイン情報の追加を行う
     * @param plugin プラグイン情報
     */
    public void addPlugin(Plugin plugin) {
        pluginIdList__.add(plugin.getId());
        pluginMap__.put(plugin.getId(), plugin);
    }

    /**
     * プラグイン情報の削除を行う
     * @param pluginId プラグインId
     */
    public void removePlugin(String pluginId) {
        for (int i = 0; i < pluginIdList__.size(); i++) {
            String id = pluginIdList__.get(i);
            if (id.equals(pluginId)) {
                pluginIdList__.remove(i);
            }
        }
        pluginMap__.remove(pluginId);
    }

    /**
     * 指定したIDのプラグイン情報を取得する
     * @param pluginId プラグインID
     * @return プラグイン情報
     */
    public Plugin getPlugin(String pluginId) {
        return pluginMap__.get(pluginId);
    }

    /**
     * ユーザリスナーの一覧を取得する
     * @return ユーザリスナー一覧
     */
    public String [] getUserListeners() {
        return getListeners(GSConfigConst.NAME_USERLISTENER);
    }

    /**
     * GSリスナーの一覧を取得する
     * @return GSリスナー一覧
     */
    public String [] getGsListeners() {
        return getListeners(GSConfigConst.NAME_GSLISTENER);
    }

    /**
     * バッチリスナーの一覧を取得する
     * @return バッチリスナー一覧
     */
    public String [] getBatchListeners() {
        return getListeners(GSConfigConst.NAME_BATCHLISTENER);
    }

    /**
     * バッチリスナーの一覧を取得する
     * @param pluginId プラグインID
     * @return バッチリスナー一覧
     */
    public String [] getBatchListeners(String pluginId) {
        List<String> pluginIdList = new ArrayList<String>();
        pluginIdList.add(pluginId);
        return getListeners(pluginIdList, GSConfigConst.NAME_BATCHLISTENER);
    }

    /**
     * バックアップバッチリスナーの一覧を取得する
     * @return バックアップバッチリスナー一覧
     */
    public String [] getBackupBatchListeners() {
        return getListeners(GSConfigConst.NAME_BACKUPBATCHLISTENER);
    }

    /**
     * ログイン、ログアウトリスナーの一覧を取得する
     * @return ログイン、ログアウトリスナー一覧
     */
    public String [] getLoginLogoutListeners() {
        return getListeners(GSConfigConst.NAME_LOGIN_LOGOUT_LISTENER);
    }

    /**
     * ショートメールリスナーの一覧を取得する
     * @return ログイン、ログアウトリスナー一覧
     */
    public String [] getSMailListeners() {
        return getListeners(GSConfigConst.NAME_SMAIL_LISTENER);
    }

    /**
     * メイン画面メッセージ表示クラスの実装クラス一覧を取得する
     * @return メイン画面メッセージ表示クラスの実装クラス一覧
     */
    public String [] getMainInfoMessageImpl() {
        return getListeners(GSConfigConst.NAME_MAININFOMESSAGE);
    }

    /**
     * ユーザ情報POPUP画面メッセージ表示クラスの実装クラス一覧を取得する
     * @return メイン画面メッセージ表示クラスの実装クラス一覧
     */
    public String [] getCmn100AppendInfoImpl() {
        return getListeners(GSConfigConst.NAME_SYAININFO);
    }

    /**
     * トップメニュー情報を取得する実装クラス一覧を取得する
     * @return トップメニュー情報を取得する実装クラス一覧
     */
    public String [] getTopMenuInfoImpl() {
        return getListeners(GSConfigConst.NAME_TOP_MENU_INFO_LISTENER);
    }

    /**
     * スケジュールで他のプラグイン情報を取得する実装クラス一覧を取得する
     * @return トップメニュー情報を取得する実装クラス一覧
     */
    public String [] getSchAppendSchData() {
        return getListeners(GSConfigConst.NAME_SCHEDULE_DATA);
    }


    /**
     * 指定したリスナーの一覧を取得します
     * @param listenerName リスナー名
     * @return リスナー一覧
     */
    public String [] getListeners(String listenerName) {
        List<String> pluginIdList = getPluginIdList();
        return getListeners(pluginIdList, listenerName);
    }

    /**
     * 指定したリスナーの一覧を取得します
     * @param pluginIdList プラグインID
     * @param listenerName リスナー名
     * @return リスナー一覧
     */
    public String [] getListeners(List<String> pluginIdList, String listenerName) {
        List < String > userListeners = new ArrayList<String>();

        Plugin plugin = null;
        for (String id : pluginIdList) {
            plugin = pluginMap__.get(id);
            String listenerclass = plugin.getListenerClass(listenerName);

            if (listenerclass != null) {
                userListeners.add(listenerclass);
            }
        }

        return (String[]) userListeners.toArray(new String[userListeners.size()]);
    }

    /**
     * サーバの一覧を取得します
     * @return サーバ一覧
     */
    public String [] getServerClassNames() {
        List < String > servers = new ArrayList<String>();

        Plugin plugin = null;
        List<String> pluginIdList = getPluginIdList();
        for (String id : pluginIdList) {
            plugin = pluginMap__.get(id);
            String[] serverClassNames = plugin.getServerClassNames();
            if (serverClassNames == null) {
                continue;
            }
            for (String name : serverClassNames) {
                servers.add(name);
            }
        }
        return (String[]) servers.toArray(new String[servers.size()]);
    }
}