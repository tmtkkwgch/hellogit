package jp.groupsession.v2.man.man410kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.biz.StatisticsBiz;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メイン 統計情報手動削除確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man410knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man410knBiz.class);

    /** DBコネクション */
    private Connection con__ = null;

    /**
     * <p>デフォルトコンストラクター
     */
    public Man410knBiz() {
    }

    /**
     * <p>デフォルトコンストラクター
     * @param con Connection
     */
    public Man410knBiz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param buMdl セッションユーザモデル
     * @param pluginConfig プラグイン設定
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(RequestModel reqMdl,
            Man410knParamModel paramMdl,
            Connection con,
            BaseUserModel buMdl,
            PluginConfig pluginConfig)
                    throws SQLException {

        log__.debug("START");

        //プラグイン使用制限情報を設定
        __setUsablePlugin(paramMdl, buMdl, con, pluginConfig);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String txt_setupNo = gsMsg.getMessage(GSConstWebmail.MANU_SETUP_NO);
        String txt_setupOk = gsMsg.getMessage(GSConstWebmail.MANU_SETUP_OK);

        //WEBメール
        if (paramMdl.getMan410WmlKbn() == GSConstMain.LMD_DELKBN_NO) {
            //削除しない
            paramMdl.setMan410knWmlKbn(txt_setupNo);
        } else {
            //削除する
            paramMdl.setMan410knWmlKbn(txt_setupOk);
            paramMdl.setMan410knWmlYear(String.valueOf(paramMdl.getMan410WmlYear()));
            paramMdl.setMan410knWmlMonth(String.valueOf(paramMdl.getMan410WmlMonth()));
        }

        //ショートメール
        if (paramMdl.getMan410SmlKbn() == GSConstMain.LMD_DELKBN_NO) {
            //削除しない
            paramMdl.setMan410knSmlKbn(txt_setupNo);
        } else {
            //削除する
            paramMdl.setMan410knSmlKbn(txt_setupOk);
            paramMdl.setMan410knSmlYear(String.valueOf(paramMdl.getMan410SmlYear()));
            paramMdl.setMan410knSmlMonth(String.valueOf(paramMdl.getMan410SmlMonth()));
        }

        //回覧板
        if (paramMdl.getMan410CirKbn() == GSConstMain.LMD_DELKBN_NO) {
            //削除しない
            paramMdl.setMan410knCirKbn(txt_setupNo);
        } else {
            //削除する
            paramMdl.setMan410knCirKbn(txt_setupOk);
            paramMdl.setMan410knCirYear(String.valueOf(paramMdl.getMan410CirYear()));
            paramMdl.setMan410knCirMonth(String.valueOf(paramMdl.getMan410CirMonth()));
        }

        //ファイル管理
        if (paramMdl.getMan410FilKbn() == GSConstMain.LMD_DELKBN_NO) {
            //削除しない
            paramMdl.setMan410knFilKbn(txt_setupNo);
        } else {
            //削除する
            paramMdl.setMan410knFilKbn(txt_setupOk);
            paramMdl.setMan410knFilYear(String.valueOf(paramMdl.getMan410FilYear()));
            paramMdl.setMan410knFilMonth(String.valueOf(paramMdl.getMan410FilMonth()));
        }

        //掲示板
        if (paramMdl.getMan410BbsKbn() == GSConstMain.LMD_DELKBN_NO) {
            //削除しない
            paramMdl.setMan410knBbsKbn(txt_setupNo);
        } else {
            //削除する
            paramMdl.setMan410knBbsKbn(txt_setupOk);
            paramMdl.setMan410knBbsYear(String.valueOf(paramMdl.getMan410BbsYear()));
            paramMdl.setMan410knBbsMonth(String.valueOf(paramMdl.getMan410BbsMonth()));
        }

        log__.debug("END");
    }

    /**
     * <br>[機  能] 統計情報用の集計データと集計データ_集計結果を手動削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void deleteLogCount(RequestModel reqMdl,
            Man410knParamModel paramMdl, int userSid) throws SQLException {

        boolean commitFlg = false;
        try {

            con__.setAutoCommit(false);

            //手動削除を行う
            StatisticsBiz statsBiz = new StatisticsBiz();

            //WEBメール
            if (paramMdl.isMan410CtrlFlgWml()
                    && paramMdl.getMan410WmlKbn() == GSConstMain.LMD_DELKBN_OK) {
                int wmlYear = paramMdl.getMan410WmlYear();
                int wmlMonth = paramMdl.getMan410WmlMonth();
                //集計データを削除
                int wmlLogDelCount = statsBiz.deleteLogCount(
                        con__, "WML_LOG_COUNT", "WLC_DATE", wmlYear, wmlMonth);
                log__.debug("WEBメール 統計情報集計データ" + wmlLogDelCount + "件を削除");
            }

            //ショートメール
            if (paramMdl.isMan410CtrlFlgSml()
                    && paramMdl.getMan410SmlKbn() == GSConstMain.LMD_DELKBN_OK) {
                int smlYear = paramMdl.getMan410SmlYear();
                int smlMonth = paramMdl.getMan410SmlMonth();
                //集計データを削除
                int smlLogDelCount = statsBiz.deleteLogCount(
                        con__, "SML_LOG_COUNT", "SLC_DATE", smlYear, smlMonth);
                log__.debug("ショートメール 統計情報集計データ" + smlLogDelCount + "件を削除");
            }

            //回覧板
            if (paramMdl.isMan410CtrlFlgCir()
                    && paramMdl.getMan410CirKbn() == GSConstMain.LMD_DELKBN_OK) {
                int cirYear = paramMdl.getMan410CirYear();
                int cirMonth = paramMdl.getMan410CirMonth();
                //集計データを削除
                int cirLogDelCount = statsBiz.deleteLogCount(
                        con__, "CIR_LOG_COUNT", "CLC_DATE", cirYear, cirMonth);
                log__.debug("回覧板 統計情報集計データ" + cirLogDelCount + "件を削除");
            }

            //ファイル管理
            if (paramMdl.isMan410CtrlFlgFil()
                    && paramMdl.getMan410FilKbn() == GSConstMain.LMD_DELKBN_OK) {
                int filYear = paramMdl.getMan410FilYear();
                int filMonth = paramMdl.getMan410FilMonth();
                //集計データを削除
                int filDlLogDelCount = statsBiz.deleteLogCount(
                        con__, "FILE_DOWNLOAD_LOG", "FDL_DATE", filYear, filMonth);
                log__.debug("ファイル管理ダウンロード 統計情報集計データ" + filDlLogDelCount + "件を削除");
                int filUlLogDelCount = statsBiz.deleteLogCount(
                        con__, "FILE_UPLOAD_LOG", "FUL_DATE", filYear, filMonth);
                log__.debug("ファイル管理アップロード 統計情報集計データ" + filUlLogDelCount + "件を削除");
            }

            //掲示板
            if (paramMdl.isMan410CtrlFlgBbs()
                    && paramMdl.getMan410BbsKbn() == GSConstMain.LMD_DELKBN_OK) {
                int bbsYear = paramMdl.getMan410BbsYear();
                int bbsMonth = paramMdl.getMan410BbsMonth();
                //集計データを削除
                int bbsViewLogCount = statsBiz.deleteLogCount(
                        con__, "BBS_WRITE_LOG_COUNT", "BWL_WRT_DATE", bbsYear, bbsMonth);
                log__.debug("掲示板閲覧 統計情報集計データ" + bbsViewLogCount + "件を削除");
                int bbsWriteLogCount = statsBiz.deleteLogCount(
                        con__, "BBS_VIEW_LOG_COUNT", "BVL_VIEW_DATE", bbsYear, bbsMonth);
                log__.debug("掲示板投稿 統計情報集計データ" + bbsWriteLogCount + "件を削除");
            }

            commitFlg = true;

        } catch (SQLException e) {
            throw e;

        } finally {
            if (commitFlg) {
                con__.commit();
            } else {
                JDBCUtil.rollback(con__);
            }
        }
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
            Man410knParamModel paramMdl,
            BaseUserModel buMdl,
            Connection con,
            PluginConfig pluginConfig)
                    throws SQLException {
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
