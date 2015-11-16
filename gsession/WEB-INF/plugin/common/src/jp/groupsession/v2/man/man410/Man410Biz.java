package jp.groupsession.v2.man.man410;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.man400.Man400Biz;

/**
 * <br>[機  能] メイン 統計手動削除画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man410Biz {

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param cmd コマンド
     * @param con コネクション
     * @param buMdl セッションユーザモデル
     * @param pluginConfig プラグイン設定
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(
            RequestModel reqMdl,
            Man410ParamModel paramMdl,
            String cmd,
            Connection con,
            BaseUserModel buMdl,
            PluginConfig pluginConfig)
                    throws SQLException {

        //プラグイン使用制限情報を設定
        __setUsablePlugin(paramMdl, buMdl, con, pluginConfig);

        //年ラベル
        paramMdl.setYearLabelList(Man400Biz.createDelYearCombo(reqMdl));
        //月ラベル
        paramMdl.setMonthLabelList(Man400Biz.createDelMonthCombo(reqMdl));

        //戻り先のプラグインを設定
        String backPlugin = "";
        if (!StringUtil.isNullZeroString(paramMdl.getBackPlugin())) {
            backPlugin = paramMdl.getBackPlugin();
        } else {
            if (cmd.equals("wmlLogManualDelete")) {
                //WEBメール
                backPlugin = GSConst.PLUGINID_WML;
            } else if (cmd.equals("smlLogManualDelete")) {
                //ショートメール
                backPlugin = GSConst.PLUGINID_SML;
            } else if (cmd.equals("cirLogManualDelete")) {
                //回覧板
                backPlugin = GSConst.PLUGINID_CIR;
            } else if (cmd.equals("filLogManualDelete")) {
                //ファイル管理
                backPlugin = GSConst.PLUGIN_ID_FILE;
            } else if (cmd.equals("bbsLogManualDelete")) {
                //掲示板
                backPlugin = GSConst.PLUGIN_ID_BULLETIN;
            }
        }
        paramMdl.setBackPlugin(backPlugin);
    }

    /**
     * <br>[機  能] プラグイン使用制限情報を設定
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param buMdl セッションユーザモデル
     * @param con コネクション
     * @param pluginConfig プラグイン設定
     * @throws SQLException SQL実行時例外
     */
    private void __setUsablePlugin(
            Man410ParamModel paramMdl,
            BaseUserModel buMdl,
            Connection con,
            PluginConfig pluginConfig)
                    throws SQLException {
        //プラグインが使用可能であり、かつログインユーザにプラグインの管理者権限があるか
        List <String> pluginList = new ArrayList<String>();
        for (Plugin plugin: pluginConfig.getPluginDataList()) {
            pluginList.add(plugin.getId());
        }
        if (pluginList.contains(GSConst.PLUGINID_WML)) {
            paramMdl.setMan410CtrlFlgWml(true);
        }
        if (pluginList.contains(GSConst.PLUGINID_SML)) {
            paramMdl.setMan410CtrlFlgSml(true);
        }
        if (pluginList.contains(GSConst.PLUGINID_CIR)) {
            paramMdl.setMan410CtrlFlgCir(true);
        }
        if (pluginList.contains(GSConst.PLUGIN_ID_FILE)) {
            paramMdl.setMan410CtrlFlgFil(true);
        }
        if (pluginList.contains(GSConst.PLUGIN_ID_BULLETIN)) {
            paramMdl.setMan410CtrlFlgBbs(true);
        }
    }
}
