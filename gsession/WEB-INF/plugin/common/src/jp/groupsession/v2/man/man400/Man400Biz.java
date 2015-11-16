package jp.groupsession.v2.man.man400;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.dao.base.BbsLogCountAdelDao;
import jp.groupsession.v2.man.dao.base.CirLogCountAdelDao;
import jp.groupsession.v2.man.dao.base.FileLogAdelDao;
import jp.groupsession.v2.man.dao.base.SmlLogCountAdelDao;
import jp.groupsession.v2.man.dao.base.WmlLogCountAdelDao;
import jp.groupsession.v2.man.model.base.BbsLogCountAdelModel;
import jp.groupsession.v2.man.model.base.CirLogCountAdelModel;
import jp.groupsession.v2.man.model.base.FileLogAdelModel;
import jp.groupsession.v2.man.model.base.SmlLogCountAdelModel;
import jp.groupsession.v2.man.model.base.WmlLogCountAdelModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 統計自動削除設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man400Biz {
    /** Logging インスタンス */
//    private static Log log__ = LogFactory.getLog(Man400Biz.class);

    /**
     * <br>[機  能] 自動削除設定を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void getAutoDelete(Man400ParamModel paramMdl, Connection con) throws SQLException {

        //自動削除設定情報を取得
        //WEBメール
        if (paramMdl.isMan400CtrlFlgWml()) {
            WmlLogCountAdelDao wldDao = new WmlLogCountAdelDao(con);
            List<WmlLogCountAdelModel> wldMdlList = wldDao.select();
            if (wldMdlList != null && wldMdlList.size() > 0) {
                paramMdl.setMan400WmlKbn(wldMdlList.get(0).getWldDelKbn());
                paramMdl.setMan400WmlYear(wldMdlList.get(0).getWldDelYear());
                paramMdl.setMan400WmlMonth(wldMdlList.get(0).getWldDelMonth());
            }
        }

        //ショートメール
        if (paramMdl.isMan400CtrlFlgSml()) {
            SmlLogCountAdelDao sldDao = new SmlLogCountAdelDao(con);
            List<SmlLogCountAdelModel> sldMdlList = sldDao.select();
            if (sldMdlList != null && sldMdlList.size() > 0) {
                paramMdl.setMan400SmlKbn(sldMdlList.get(0).getSldDelKbn());
                paramMdl.setMan400SmlYear(sldMdlList.get(0).getSldDelYear());
                paramMdl.setMan400SmlMonth(sldMdlList.get(0).getSldDelMonth());
            }
        }

        //回覧板
        if (paramMdl.isMan400CtrlFlgCir()) {
            CirLogCountAdelDao cldDao = new CirLogCountAdelDao(con);
            List<CirLogCountAdelModel> cldMdlList = cldDao.select();
            if (cldMdlList != null && cldMdlList.size() > 0) {
                paramMdl.setMan400CirKbn(cldMdlList.get(0).getCldDelKbn());
                paramMdl.setMan400CirYear(cldMdlList.get(0).getCldDelYear());
                paramMdl.setMan400CirMonth(cldMdlList.get(0).getCldDelMonth());
            }
        }

        //ファイル管理
        if (paramMdl.isMan400CtrlFlgFil()) {
            FileLogAdelDao fldDao = new FileLogAdelDao(con);
            List<FileLogAdelModel> fldMdlList = fldDao.select();
            if (fldMdlList != null && fldMdlList.size() > 0) {
                paramMdl.setMan400FilKbn(fldMdlList.get(0).getFldDelKbn());
                paramMdl.setMan400FilYear(fldMdlList.get(0).getFldDelYear());
                paramMdl.setMan400FilMonth(fldMdlList.get(0).getFldDelMonth());
            }
        }

        //掲示板
        if (paramMdl.isMan400CtrlFlgBbs()) {
            BbsLogCountAdelDao bldDao = new BbsLogCountAdelDao(con);
            List<BbsLogCountAdelModel> bldMdlList = bldDao.select();
            if (bldMdlList != null && bldMdlList.size() > 0) {
                paramMdl.setMan400BbsKbn(bldMdlList.get(0).getBldDelKbn());
                paramMdl.setMan400BbsYear(bldMdlList.get(0).getBldDelYear());
                paramMdl.setMan400BbsMonth(bldMdlList.get(0).getBldDelMonth());
            }
        }
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param cmd コマンド
     * @param con コネクション
     * @param buMdl セッションユーザモデル
     * @param pluginConfig プラグイン設定
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(
            Man400ParamModel paramMdl,
            RequestModel reqMdl,
            String cmd,
            Connection con,
            BaseUserModel buMdl,
            PluginConfig pluginConfig)
                    throws SQLException {

        //プラグイン使用制限情報を設定
        __setUsablePlugin(paramMdl, buMdl, con, pluginConfig);

        //年ラベル
        paramMdl.setYearLabelList(createDelYearCombo(reqMdl));
        //月ラベル
        paramMdl.setMonthLabelList(createDelMonthCombo(reqMdl));

        //戻り先のプラグインを設定
        String backPlugin = "";
        if (!StringUtil.isNullZeroString(paramMdl.getBackPlugin())) {
            backPlugin = paramMdl.getBackPlugin();
        } else {
            if (cmd.equals("wmlLogAutoDelete")) {
                //WEBメール
                backPlugin = GSConst.PLUGINID_WML;
            } else if (cmd.equals("smlLogAutoDelete")) {
                //ショートメール
                backPlugin = GSConst.PLUGINID_SML;
            } else if (cmd.equals("cirLogAutoDelete")) {
                //回覧板
                backPlugin = GSConst.PLUGINID_CIR;
            } else if (cmd.equals("filLogAutoDelete")) {
                //ファイル管理
                backPlugin = GSConst.PLUGIN_ID_FILE;
            } else if (cmd.equals("bbsLogAutoDelete")) {
                //掲示板
                backPlugin = GSConst.PLUGIN_ID_BULLETIN;
            }
        }
        paramMdl.setBackPlugin(backPlugin);
    }

    /**
     * <br>[機  能] 削除設定画面の年コンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return 年コンボ
     */
    public static List<LabelValueBean> createDelYearCombo(RequestModel reqMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl);
        //年ラベル
        ArrayList<LabelValueBean> yearCombo = new ArrayList<LabelValueBean>();
        for (int year = 0; year < GSConstWebmail.LIST_YEAR_KEY_ALL.length; year++) {
            yearCombo.add(new LabelValueBean(
                    gsMsg.getMessage("cmn.year", new String[] {String.valueOf(year)}),
                    String.valueOf(year)));
        }

        return yearCombo;
    }

    /**
     * <br>[機  能] 削除設定画面の月コンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @return 月コンボ
     */
    public static List<LabelValueBean> createDelMonthCombo(RequestModel reqMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl);
        ArrayList<LabelValueBean> monthCombo = new ArrayList<LabelValueBean>();
        for (int month = GSConstWebmail.DEL_MONTH_START;
            month <= GSConstWebmail.DEL_MONTH_END; month++) {

            monthCombo.add(new LabelValueBean(
                    gsMsg.getMessage("cmn.months", new String[] {String.valueOf(month)}),
                    String.valueOf(month)));
        }

        return monthCombo;
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
            Man400ParamModel paramMdl,
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
            paramMdl.setMan400CtrlFlgWml(true);
        }
        if (pluginList.contains(GSConst.PLUGINID_SML)) {
            paramMdl.setMan400CtrlFlgSml(true);
        }
        if (pluginList.contains(GSConst.PLUGINID_CIR)) {
            paramMdl.setMan400CtrlFlgCir(true);
        }
        if (pluginList.contains(GSConst.PLUGIN_ID_FILE)) {
            paramMdl.setMan400CtrlFlgFil(true);
        }
        if (pluginList.contains(GSConst.PLUGIN_ID_BULLETIN)) {
            paramMdl.setMan400CtrlFlgBbs(true);
        }
    }

    /**
     * <br>[機  能] 削除対象のプラグイン使用制限情報を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param buMdl セッションユーザモデル
     * @param con コネクション
     * @param pluginConfig プラグイン設定
     * @return true:いずれかの対象プラグインが使用可能状態; false:全対象プラグイン未使用状態
     * @throws SQLException SQL実行時例外
     */
    public boolean canUsePlugin(
            BaseUserModel buMdl,
            Connection con,
            PluginConfig pluginConfig)
                    throws SQLException {
        List <String> pluginList = new ArrayList<String>();
        for (Plugin plugin: pluginConfig.getPluginDataList()) {
            pluginList.add(plugin.getId());
        }
        if (pluginList.contains(GSConst.PLUGINID_WML)
                || pluginList.contains(GSConst.PLUGINID_SML)
                || pluginList.contains(GSConst.PLUGINID_CIR)
                || pluginList.contains(GSConst.PLUGIN_ID_FILE)
                || pluginList.contains(GSConst.PLUGIN_ID_BULLETIN)) {
            //削除対象になるプラグインのいずれかが使用可能
            return true;
        }
        return false;
    }
}
