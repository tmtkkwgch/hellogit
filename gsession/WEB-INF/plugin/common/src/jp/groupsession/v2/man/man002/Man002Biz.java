package jp.groupsession.v2.man.man002;

import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.AdminSettingInfo;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.model.ManAdmSettingInfoModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メイン 管理者設定メニュー画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man002Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man002Biz.class);

    /**
     * <br>[機  能] 初期表示画面情報を取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param pconfig プラグイン設定情報
     * @param reqMdl リクエスト情報
     * @throws Exception SQL実行時例外
     */
    public void setInitData(Man002ParamModel paramMdl, PluginConfig pconfig, RequestModel reqMdl)
    throws Exception {

        log__.debug("setInitData Start");

        //表示用プラグインリスト
        ArrayList <ManAdmSettingInfoModel> dspList = new ArrayList<ManAdmSettingInfoModel>();
        ManAdmSettingInfoModel dspModel = null;

        //plugin.xmlに記述されたものは無条件に許可
        List < Plugin > plist = pconfig.getPluginDataList();
        for (Plugin plugin : plist) {
            AdminSettingInfo asinfo = plugin.getAdminSettingInfo();
            if (asinfo != null) {

                //表示/非表示チェック
                if (!NullDefault.getString(asinfo.getView(), "false").equals("true")) {
                    continue;
                }

                dspModel = new ManAdmSettingInfoModel();
                dspModel.setName(plugin.getName(reqMdl));
                dspModel.setUrl(asinfo.getUrl()
                        + "?backScreen=" + GSConstMain.BACK_MAIN_ADM_SETTING);
                dspModel.setIcon(__getIconPath(asinfo.getIcon()));

                dspList.add(dspModel);
            }
        }

        paramMdl.setPluginMenuList(dspList);

        //ポータルが使用可能かを判定
        CommonBiz cmnBiz = new CommonBiz();
        if (cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_PORTAL, pconfig)) {
            paramMdl.setPortalUseOk(GSConstMain.PLUGIN_USE);
        } else {
            paramMdl.setPortalUseOk(GSConstMain.PLUGIN_NOT_USE);
        }
    }

    /**
     * <br>[機  能] アイコンのパスが存在しない場合、デフォルトのアイコンパスを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param icon アイコンのパス
     * @return icon アイコンのパス
     * @throws IOToolsException ファイル操作時の例外
     */
    private String __getIconPath(String icon) throws IOToolsException {

        String defaultIcon = "../main/images/icon_plugin.gif";
        if (StringUtil.isNullZeroStringSpace(icon)) {
            return defaultIcon;
        }

        return icon;
    }
}
