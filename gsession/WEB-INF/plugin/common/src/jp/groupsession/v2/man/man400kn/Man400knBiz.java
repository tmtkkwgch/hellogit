package jp.groupsession.v2.man.man400kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メイン 統計情報自動削除設定確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man400knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man400knBiz.class);
    /** DBコネクション */
    private Connection con__ = null;

    /**
     * <p>デフォルトコンストラクター
     */
    public Man400knBiz() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public Man400knBiz(Connection con) {
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
    public void setInitData(
            RequestModel reqMdl,
            Man400knParamModel paramMdl,
            Connection con,
            BaseUserModel buMdl,
            PluginConfig pluginConfig) throws SQLException {

        log__.debug("START");

        //プラグイン使用制限情報を設定
        __setUsablePlugin(paramMdl, buMdl, con, pluginConfig);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String txt_setupNo = gsMsg.getMessage(GSConstWebmail.SETUP_NO);
        String txt_setupAuto = gsMsg.getMessage(GSConstWebmail.SETUP_AUTO);

        //WEBメール 自動削除設定
        if (paramMdl.getMan400WmlKbn() == GSConstMain.LAD_DELKBN_NO) {
            //設定しない
            paramMdl.setMan400knWmlKbn(txt_setupNo);
        } else {
            //自動削除
            paramMdl.setMan400knWmlKbn(txt_setupAuto);
            paramMdl.setMan400knWmlYear(
                    String.valueOf(paramMdl.getMan400WmlYear()));
            paramMdl.setMan400knWmlMonth(
                    String.valueOf(paramMdl.getMan400WmlMonth()));
        }

        //ショートメール 自動削除設定
        if (paramMdl.getMan400SmlKbn() == GSConstMain.LAD_DELKBN_NO) {
            //設定しない
            paramMdl.setMan400knSmlKbn(txt_setupNo);
        } else {
            //自動削除
            paramMdl.setMan400knSmlKbn(txt_setupAuto);
            paramMdl.setMan400knSmlYear(
                    String.valueOf(paramMdl.getMan400SmlYear()));
            paramMdl.setMan400knSmlMonth(
                    String.valueOf(paramMdl.getMan400SmlMonth()));
        }

        //回覧板 自動削除設定
        if (paramMdl.getMan400CirKbn() == GSConstMain.LAD_DELKBN_NO) {
            //設定しない
            paramMdl.setMan400knCirKbn(txt_setupNo);
        } else {
            //自動削除
            paramMdl.setMan400knCirKbn(txt_setupAuto);
            paramMdl.setMan400knCirYear(
                    String.valueOf(paramMdl.getMan400CirYear()));
            paramMdl.setMan400knCirMonth(
                    String.valueOf(paramMdl.getMan400CirMonth()));
        }

        //ファイル管理 自動削除設定
        if (paramMdl.getMan400FilKbn() == GSConstMain.LAD_DELKBN_NO) {
            //設定しない
            paramMdl.setMan400knFilKbn(txt_setupNo);
        } else {
            //自動削除
            paramMdl.setMan400knFilKbn(txt_setupAuto);
            paramMdl.setMan400knFilYear(
                    String.valueOf(paramMdl.getMan400FilYear()));
            paramMdl.setMan400knFilMonth(
                    String.valueOf(paramMdl.getMan400FilMonth()));
        }

        //掲示板 自動削除設定
        if (paramMdl.getMan400BbsKbn() == GSConstMain.LAD_DELKBN_NO) {
            //設定しない
            paramMdl.setMan400knBbsKbn(txt_setupNo);
        } else {
            //自動削除
            paramMdl.setMan400knBbsKbn(txt_setupAuto);
            paramMdl.setMan400knBbsYear(
                    String.valueOf(paramMdl.getMan400BbsYear()));
            paramMdl.setMan400knBbsMonth(
                    String.valueOf(paramMdl.getMan400BbsMonth()));
        }
    }

    /**
     * <br>[機  能] 自動削除情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void setData(
            Man400knParamModel paramMdl,
            int userSid) throws SQLException {

        boolean commitFlg = false;
        UDate now = new UDate();

        try {

            con__.setAutoCommit(false);

            //登録情報をModelに設定する
            //WEBメール
            if (paramMdl.isMan400CtrlFlgWml()) {
                __setDataWml(paramMdl, userSid, now);
            }

            //ショートメール
            if (paramMdl.isMan400CtrlFlgSml()) {
                __setDataSml(paramMdl, userSid, now);
            }

            //回覧板
            if (paramMdl.isMan400CtrlFlgCir()) {
                __setDataCir(paramMdl, userSid, now);
            }

            //ファイル管理
            if (paramMdl.isMan400CtrlFlgFil()) {
                __setDataFil(paramMdl, userSid, now);
            }

            //掲示板
            if (paramMdl.isMan400CtrlFlgBbs()) {
                __setDataBbs(paramMdl, userSid, now);
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
     * <br>[機  能] 掲示板自動削除情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param now 現在時刻
     * @throws SQLException SQL実行時例外
     */
    private void __setDataBbs(Man400knParamModel paramMdl, int userSid,
            UDate now) throws SQLException {
        BbsLogCountAdelModel bldMdl = new BbsLogCountAdelModel();
        int bbsKbn = paramMdl.getMan400BbsKbn();
        bldMdl.setBldDelKbn(bbsKbn);
        if (bbsKbn == GSConstMain.LAD_DELKBN_AUTO) {
            bldMdl.setBldDelYear(paramMdl.getMan400BbsYear());
            bldMdl.setBldDelMonth(paramMdl.getMan400BbsMonth());
        }
        bldMdl.setBldEuid(userSid);
        bldMdl.setBldEdate(now);

        //登録件数を取得する
        BbsLogCountAdelDao bldDao = new BbsLogCountAdelDao(con__);
        int setupCount = bldDao.select().size();
        if (setupCount == 1) {
            //登録件数が1件の場合
            bldDao.update(bldMdl);
        } else {
            //登録件数が0件の場合
            bldMdl.setBldAuid(userSid);
            bldMdl.setBldAdate(now);
            bldDao.insert(bldMdl);
        }
    }

    /**
     * <br>[機  能] ファイル管理自動削除情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param now 現在時刻
     * @throws SQLException SQL実行時例外
     */
    private void __setDataFil(Man400knParamModel paramMdl, int userSid,
            UDate now) throws SQLException {
        FileLogAdelModel fldMdll = new FileLogAdelModel();
        int filKbn = paramMdl.getMan400FilKbn();
        fldMdll.setFldDelKbn(filKbn);
        if (filKbn == GSConstMain.LAD_DELKBN_AUTO) {
            fldMdll.setFldDelYear(paramMdl.getMan400FilYear());
            fldMdll.setFldDelMonth(paramMdl.getMan400FilMonth());
        }
        fldMdll.setFldEuid(userSid);
        fldMdll.setFldEdate(now);

        //登録件数を取得する
        FileLogAdelDao fldDao = new FileLogAdelDao(con__);
        int setupCount = fldDao.select().size();
        if (setupCount == 1) {
            //登録件数が1件の場合
            fldDao.update(fldMdll);
        } else {
            //登録件数が0件の場合
            fldMdll.setFldAuid(userSid);
            fldMdll.setFldAdate(now);
            fldDao.insert(fldMdll);
        }
    }

    /**
     * <br>[機  能] 回覧板自動削除情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param now 現在時刻
     * @throws SQLException SQL実行時例外
     */
    private void __setDataCir(Man400knParamModel paramMdl, int userSid,
            UDate now) throws SQLException {
        CirLogCountAdelModel cldMdl = new CirLogCountAdelModel();
        int cirKbn = paramMdl.getMan400CirKbn();
        cldMdl.setCldDelKbn(cirKbn);
        if (cirKbn == GSConstMain.LAD_DELKBN_AUTO) {
            cldMdl.setCldDelYear(paramMdl.getMan400CirYear());
            cldMdl.setCldDelMonth(paramMdl.getMan400CirMonth());
        }
        cldMdl.setCldEuid(userSid);
        cldMdl.setCldEdate(now);

        //登録件数を取得する
        CirLogCountAdelDao cldDao = new CirLogCountAdelDao(con__);
        int setupCount = cldDao.select().size();
        if (setupCount == 1) {
            //登録件数が1件の場合
            cldDao.update(cldMdl);
        } else {
            //登録件数が0件の場合
            cldMdl.setCldAuid(userSid);
            cldMdl.setCldAdate(now);
            cldDao.insert(cldMdl);
        }
    }

    /**
     * <br>[機  能] ショートメール自動削除情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param now 現在時刻
     * @throws SQLException SQL実行時例外
     */
    private void __setDataSml(Man400knParamModel paramMdl, int userSid,
            UDate now) throws SQLException {
        SmlLogCountAdelModel sldMdl = new SmlLogCountAdelModel();
        int smlKbn = paramMdl.getMan400SmlKbn();
        sldMdl.setSldDelKbn(smlKbn);
        if (smlKbn == GSConstMain.LAD_DELKBN_AUTO) {
            sldMdl.setSldDelYear(paramMdl.getMan400SmlYear());
            sldMdl.setSldDelMonth(paramMdl.getMan400SmlMonth());
        }
        sldMdl.setSldEuid(userSid);
        sldMdl.setSldEdate(now);

        //登録件数を取得する
        SmlLogCountAdelDao sldDao = new SmlLogCountAdelDao(con__);
        int setupCount = sldDao.select().size();
        if (setupCount == 1) {
            //登録件数が1件の場合
            sldDao.update(sldMdl);
        } else {
            //登録件数が0件の場合
            sldMdl.setSldAuid(userSid);
            sldMdl.setSldAdate(now);
            sldDao.insert(sldMdl);
        }
    }

    /**
     * <br>[機  能] WEBメール自動削除情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param now 現在時刻
     * @throws SQLException SQL実行時例外
     */
    private void __setDataWml(
            Man400knParamModel paramMdl, int userSid, UDate now)
                    throws SQLException {

        WmlLogCountAdelModel wldMdl = new WmlLogCountAdelModel();
        int wmlKbn = paramMdl.getMan400WmlKbn();
        wldMdl.setWldDelKbn(wmlKbn);
        if (wmlKbn == GSConstMain.LAD_DELKBN_AUTO) {
            wldMdl.setWldDelYear(paramMdl.getMan400WmlYear());
            wldMdl.setWldDelMonth(paramMdl.getMan400WmlMonth());
        }
        wldMdl.setWldEuid(userSid);
        wldMdl.setWldEdate(now);

        //登録件数を取得する
        WmlLogCountAdelDao wldDao = new WmlLogCountAdelDao(con__);
        int setupCount = wldDao.select().size();
        if (setupCount == 1) {
            //登録件数が1件の場合
            wldDao.update(wldMdl);
        } else {
            //登録件数が0件の場合
            wldMdl.setWldAuid(userSid);
            wldMdl.setWldAdate(now);
            wldDao.insert(wldMdl);
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
            Man400knParamModel paramMdl,
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
}
