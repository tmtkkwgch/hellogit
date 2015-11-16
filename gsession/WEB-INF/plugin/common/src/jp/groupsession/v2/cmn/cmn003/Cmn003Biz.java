package jp.groupsession.v2.cmn.cmn003;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.encryption.EncryptionException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.TopMenuBiz;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.config.TopMenuInfo;
import jp.groupsession.v2.cmn.dao.base.CmnPluginParamDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginParamModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.man.GSConstMain;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メインメニューのビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn003Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn003Biz.class);
    /** コネクション */
    private Connection con__ = null;
    /** リクエストモデル */
    private RequestModel reqMdl__ = null;

    /**
     * <p>コンストラクタ
     * @param con コネクション
     * @param reqMdl リクエストモデル
     * */
    public Cmn003Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }


    /**
     * <br>[機  能] Href属性用URLを取得します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param pid プラグインID
     * @param pconfig プラグイン設定
     * @param appRootPath アプリケーションルートパス
     * @throws SQLException SQL実行例外
     * @return jsonData jsonコメントリスト
     * @throws EncryptionException 暗号化に失敗時例外
     * @throws UnsupportedEncodingException リンク先URLの取得に失敗
     * @throws NoSuchAlgorithmException SHA-512アルゴリズムが使用不可
     */
    public JSONObject getHrefUrl(String pid, PluginConfig pconfig, String appRootPath)
            throws SQLException, EncryptionException,
            UnsupportedEncodingException, NoSuchAlgorithmException {

        JSONObject jsonData = new JSONObject();
        String url = null;

        Plugin plugin = pconfig.getPlugin(pid);
        TopMenuBiz topMenuBiz = new TopMenuBiz();
        CommonBiz cmnBiz = new CommonBiz();


        //トップメニューの個人情報を取得
        Map<String, TopMenuInfo> topMenuMap
        = topMenuBiz.setTopMenu(reqMdl__, con__, pconfig);

        if (plugin != null && plugin.getTopMenuInfo() != null
                && NullDefault.getString(plugin.getTopMenuInfo().getView(), "false").equals("true")
                && plugin.getTopMenuInfo().getUrl() != null) {
            //URLを設定

            if (topMenuMap.get(plugin.getId()) != null) {
                url = topMenuMap.get(plugin.getId()).getUrl();
            } else {
                url = plugin.getTopMenuInfo().getUrl();
            }

            CmnUsrmInfDao usrInfDao = new CmnUsrmInfDao(con__);
            CmnUsrmInfModel usrInfMdl = usrInfDao.select(reqMdl__.getSmodel().getUsrsid());

            //ベースURL内の予約語を置換する
            url = cmnBiz.replaceGSReservedWord(reqMdl__, con__, appRootPath, url, usrInfMdl, true);

            //デフォルト 開かない
            int target = 0;
            //別ウィンドウの設定
            if (plugin.getTopMenuInfo().getTarget() != null) {
                if (plugin.getTopMenuInfo()
                        .getTarget().equals(GSConstMain.TARGET_BLANK_STR)) {
                    //開く
                    target = 1;
                }
            }

            //パラメータ設定区分
            int paramKbn = plugin.getTopMenuInfo().getParamKbn();

            //パラメータ設定区分 設定する場合
            if (paramKbn == GSConstMain.PARAM_KBN_YES) {
                //パラメータ付きURLを取得する
                url = __getUrlplusParam(url, pid, appRootPath);
            }


            //別ウィンドウの設定 開かない場合 指定URLを付与する
            if (target == 0) {
                url = "../common/cmn002.do?url=" + url;
            }

            log__.debug("生成URL=> " + url);

            jsonData.put("hrefUrl", url);
        }

        return jsonData;
    }


    /**
     * <br>[機  能] プラグインクリック時用のURLを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param pid プラグインID
     * @param pconfig プラグイン設定
     * @param appRootPath アプリケーションルートパス
     * @throws SQLException SQL実行例外
     * @return jsonData jsonコメントリスト
     * @throws EncryptionException 暗号化に失敗時例外
     * @throws UnsupportedEncodingException リンク先URLの取得に失敗
     * @throws NoSuchAlgorithmException SHA-512アルゴリズムが使用不可
     */
    public JSONObject getClickUrl(String pid, PluginConfig pconfig, String appRootPath)
            throws SQLException, EncryptionException,
            UnsupportedEncodingException, NoSuchAlgorithmException {

        JSONObject jsonData = new JSONObject();
        String url = null;

        Plugin plugin = pconfig.getPlugin(pid);
        TopMenuBiz topMenuBiz = new TopMenuBiz();
        CommonBiz cmnBiz = new CommonBiz();
        CmnUsrmInfDao usrInfDao = new CmnUsrmInfDao(con__);


        //トップメニューの個人情報を取得
        Map<String, TopMenuInfo> topMenuMap =
                topMenuBiz.setTopMenu(reqMdl__, con__, pconfig);

        if (plugin != null && plugin.getTopMenuInfo() != null
                && NullDefault.getString(plugin.getTopMenuInfo().getView(), "false").equals("true")
                && plugin.getTopMenuInfo().getUrl() != null) {

            //URLを設定
            if (topMenuMap.get(plugin.getId()) != null) {
                url = topMenuMap.get(plugin.getId()).getUrl();
            } else {
                url = plugin.getTopMenuInfo().getUrl();
            }

            //GS予約語を置き換えるためにユーザ個人情報を取得する
            CmnUsrmInfModel usrInfMdl = usrInfDao.select(reqMdl__.getSmodel().getUsrsid());

            //ベースURL内の予約語を置換する
            url = cmnBiz.replaceGSReservedWord(reqMdl__, con__, appRootPath, url, usrInfMdl, true);

            //パラメータ設定区分
            int paramKbn = plugin.getTopMenuInfo().getParamKbn();
            int sendKbn = plugin.getTopMenuInfo().getSendKbn();

            ArrayList<CmnPluginParamModel> replaceCppList = new ArrayList<CmnPluginParamModel>();

            //パラメータ設定区分 設定する場合
            if (paramKbn == GSConstMain.PARAM_KBN_YES) {

                //送信方法がPOSTの場合
                if (sendKbn == GSConstMain.SEND_KBN_POST) {
                    //ベースURLとパラメータリストを返す
                    CmnPluginParamDao cppDao = new CmnPluginParamDao(con__);
                    ArrayList<CmnPluginParamModel> cppList = cppDao.select(pid);

                    CmnPluginParamModel repCppMdl = null;
                    for (CmnPluginParamModel cppMdl : cppList) {

                        repCppMdl = new CmnPluginParamModel();

                        String paramName = cmnBiz.replaceGSReservedWord(
                              reqMdl__, con__, appRootPath, cppMdl.getCppName(), usrInfMdl, false);
                        String paramValue = cmnBiz.replaceGSReservedWord(
                              reqMdl__, con__, appRootPath, cppMdl.getCppValue(), usrInfMdl, false);

                        repCppMdl.setCppName(paramName);
                        repCppMdl.setCppValue(paramValue);
                        replaceCppList.add(repCppMdl);
                    }

                } else {
                    //送信方法がGETの場合
                    //ベースURLにパラメータを付与して返す
                    url = __getUrlplusParam(url, pid, appRootPath);
                }
            }

            log__.debug("生成URL=> " + url);
            jsonData.put("url", url);
            jsonData.put("paramList", replaceCppList);
        }

        return jsonData;
    }

    /**
     * <br>[機  能] GET用URLを取得する（パラメータ付き）
     * <br>[解  説]
     * <br>[備  考]
     * @param url ベースURL
     * @param pid プラグインID
     * @param appRootPath アプリケーションルートパス
     * @throws SQLException SQL実行時例外
     * @return GET用URL
     * @throws EncryptionException 暗号化に失敗時例外
     * @throws NoSuchAlgorithmException SHA-512アルゴリズムが使用不可
     * @throws UnsupportedEncodingException リンク先URLの取得に失敗
     */
    private String __getUrlplusParam(
            String url, String pid, String appRootPath)
                    throws SQLException, EncryptionException,
                    NoSuchAlgorithmException, UnsupportedEncodingException {


        CommonBiz cmnBiz = new CommonBiz();
        CmnUsrmInfDao usrInfDao = new CmnUsrmInfDao(con__);
        CmnUsrmInfModel usrInfMdl = usrInfDao.select(reqMdl__.getSmodel().getUsrsid());

        //urlにパラメータを付与する
        StringBuilder sbParam = new StringBuilder();
        CmnPluginParamDao cppDao = new CmnPluginParamDao(con__);
        ArrayList<CmnPluginParamModel> cppList = cppDao.select(pid);

        int cnt = 0;
        for (CmnPluginParamModel cppMdl : cppList) {

            if (cnt != 0) {
                sbParam.append("&");
            }

            //URLパラメータ内にGS予約語が存在した場合は置換を行う
            //URLエンコードを行う(追加パラメータのため全てが対象）
            String cppName = cmnBiz.replaceGSReservedWord(
                    reqMdl__, con__, appRootPath, cppMdl.getCppName(), usrInfMdl, false);
            cppName = URLEncoder.encode(cppName, Encoding.UTF_8);

            String cppValue = cmnBiz.replaceGSReservedWord(
                    reqMdl__, con__, appRootPath, cppMdl.getCppValue(), usrInfMdl, false);
            cppValue = URLEncoder.encode(cppValue, Encoding.UTF_8);

            sbParam.append(cppName);
            sbParam.append("=");
            sbParam.append(cppValue);

            cnt++;
        }

        //?がない場合は付与する
        if (url.indexOf("?") > -1) {
            url = url.replace("?", "?" + sbParam.toString() + "&");
        } else {
            url = url + "?" + sbParam.toString();
        }

        return url;
    }
}
