package jp.groupsession.v2.man.man002;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.ConfigBundle;
import jp.groupsession.v2.cmn.DBUtilFactory;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.IDbUtil;
import jp.groupsession.v2.cmn.IGsResourceManager;
import jp.groupsession.v2.cmn.ITempFileUtil;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.base.CmnContmDao;
import jp.groupsession.v2.lic.GSConstLicese;
import jp.groupsession.v2.lic.LicenseModel;
import jp.groupsession.v2.lic.LicenseOperation;
import jp.groupsession.v2.struts.AdminAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] メイン 管理者設定メニュー画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man002Action extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man002Action.class);

    /**
     * <br>[機  能] アクションを実行する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        log__.debug("START");
        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("*****CMD = " + cmd);
        if (cmd.equals("man002back")) {
            forward = map.findForward("back");
        } else if (cmd.equals("enterpriseiinfo")) {
            forward = map.findForward("enterpriseiinfo");
        } else if (cmd.equals("grouplist")) {
            forward = map.findForward("grouplist");
        } else if (cmd.equals("userlist")) {
            forward = map.findForward("userlist");
        } else if (cmd.equals("pconfedit")) {
            forward = map.findForward("pconfedit");
        } else if (cmd.equals("holidaylist")) {
            forward = map.findForward("holidaylist");
        } else if (cmd.equals("jobschedule")) {
            forward = map.findForward("jobschedule");
        } else if (cmd.equals("lastlogin")) {
            forward = map.findForward("lastlogin");
        } else if (cmd.equals("diskconf")) {
            forward = map.findForward("diskconf");
        } else if (cmd.equals("pxyconf")) {
            forward = map.findForward("pxyconf");
        } else if (cmd.equals("backupconf")) {
            forward = map.findForward("backupconf");
        } else if (cmd.equals("manualbackup")) {
            forward = map.findForward("manualbackup");
        } else if (cmd.equals("loglist")) {
            forward = map.findForward("loglist");
        } else if (cmd.equals("poslist")) {
            forward = map.findForward("poslist");
        } else if (cmd.equals("pluginlist")) {
            forward = map.findForward("pluginlist");
        } else if (cmd.equals("fileConf")) {
            forward = map.findForward("fileConf");
        } else if (cmd.equals("sessiontime")) {
            forward = map.findForward("sessiontime");
        } else if (cmd.equals("password")) {
            forward = map.findForward("password");
        //ライセンス登録・更新
        } else if (cmd.equals("licenseReg")) {
            forward = map.findForward("licenseReg");
        //ログイン履歴自動削除
        } else if (cmd.equals("lhisAutoDel")) {
            forward = map.findForward("lhisAutoDel");
        //ログイン履歴手動削除
        } else if (cmd.equals("lhisSyudoDel")) {
            forward = map.findForward("lhisSyudoDel");
        //パスワードルール設定
        } else if (cmd.equals("pswdConf")) {
            forward = map.findForward("pswdConf");
        //モバイル使用一括設定
        } else if (cmd.equals("mblUse")) {
            forward = map.findForward("mblUse");
        //並び順設定
        } else if (cmd.equals("sortConfig")) {
            forward = map.findForward("sortConfig");
        //システム情報確認
        } else if (cmd.equals("system")) {
            forward = map.findForward("system");
        //オペレーションログ自動削除
        } else if (cmd.equals("oplogAutoDel")) {
            forward = map.findForward("oplogAutoDel");
        //オペレーションログ手動削除
        } else if (cmd.equals("oplogSyudoDel")) {
            forward = map.findForward("oplogSyudoDel");
        //オペレーションログ設定
        } else if (cmd.equals("oplogConf")) {
            forward = map.findForward("oplogConf");
        //オペレーションログ検索
        } else if (cmd.equals("oplogSearch")) {
            forward = map.findForward("oplogSearch");
        //ログイン設定
        } else if (cmd.equals("loginConf")) {
            forward = map.findForward("loginConf");
        //所属情報一括設定
        } else if (cmd.equals("memshipconf")) {
            forward = map.findForward("memshipconf");
        //最終ログイン時間
        } else if (cmd.equals("beLogTime")) {
            forward = map.findForward("beLogTime");
        //メイン画面レイアウト設定
        } else if (cmd.equals("layoutConfig")) {
            forward = map.findForward("layoutConfig");
        //ログイン履歴統計情報
        } else if (cmd.equals("manLogCount")) {
            forward = map.findForward("manLogCount");
        } else {
            //デフォルト メイン画面表示
            forward = __doInit(map, (Man002Form) form, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Man002Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {

        String licenseId = "";
        String licenseCount = "";
        String licenseCom = "";
        ArrayList<LicenseModel> pluginList = new ArrayList<LicenseModel>();
        IGsResourceManager iGsManager = GroupSession.getResourceManager();

        GSContext gs = getGsContext();

        if (gs != null) {
            LicenseModel lmdl
              = (LicenseModel) iGsManager.getLicenseMdl(iGsManager.getDomain(req));

            if (lmdl != null) {

                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseId())) {
                    licenseId = String.valueOf(lmdl.getLicenseId());
                    licenseCount = String.valueOf(lmdl.getLicenseNumber());
                }

                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseCom())) {
                    licenseCom = String.valueOf(lmdl.getLicenseCom());
                }

                LicenseModel pmdl = null;

                //プラグイン情報 サポート
                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseLimitSupport())) {
                    pmdl = new LicenseModel();
                    pmdl.setPluginName(getInterMessage(req, "cmn.support"));
                    pmdl.setLicenseLimit(lmdl.getLicenseLimitSupport());
                    pluginList.add(pmdl);
                }

                //プラグイン情報 モバイル
                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseLimitMobile())) {
                    pmdl = new LicenseModel();
                    pmdl.setPluginName(getInterMessage(req, "mobile.4"));
                    pmdl.setLicenseLimit(lmdl.getLicenseLimitMobile());
                    pluginList.add(pmdl);
                }

                //プラグイン情報 CrossRide
                if (!StringUtil.isNullZeroStringSpace(lmdl.getLicenseLimitCrossRide())) {
                    pmdl = new LicenseModel();
                    pmdl.setPluginName(GSConstLicese.PLUGIN_NAME_CROSSRIDE);
                    pmdl.setLicenseLimit(lmdl.getLicenseLimitCrossRide());
                    pluginList.add(pmdl);
                }
            }
        }

        con.setAutoCommit(true);

        form.setLicenseId(licenseId);
        form.setLicenseCount(licenseCount);
        form.setLicenseCom(licenseCom);
        form.setPluginList(pluginList);
        UserBiz userBiz = new UserBiz();
        form.setUserCount(String.valueOf(userBiz.getActiveUserCount(con)));
        form.setLimitUserCount(
            String.valueOf(
                    GroupSession.getResourceManager().getUserCountLimit(getRequestModel(req))));
        ITempFileUtil tempFileUtil
            = (ITempFileUtil) GroupSession.getContext().get(GSContext.TEMP_FILE_UTIL);
        form.setTempFileHozonKbn(String.valueOf(tempFileUtil.getTempFileHozonKbn()));
        IDbUtil dbUtil = DBUtilFactory.getInstance();
        form.setDbKbn(String.valueOf(dbUtil.getDbType()));

        //ライセンス購入画面URLを取得
        String licenseUrl = null;

        if (!StringUtil.isNullZeroStringSpace(GroupSession.getResourceManager().getDomain(req))
                && !GroupSession.getResourceManager().getDomain(req).equals(GSConst.GS_DOMAIN)) {
            if (ConfigBundle.getValue("LICENSE_PAGE_URL_CLOUD") != null) {
                //設定ファイル(servername.conf)の指定ディレクトリ
                form.setDomain(GroupSession.getResourceManager().getDomain(req));
                licenseUrl = ConfigBundle.getValue("LICENSE_PAGE_URL_CLOUD");
                form.setLicensePageUrl(licenseUrl);
            }
        } else {
            if (ConfigBundle.getValue("LICENSE_PAGE_URL") != null) {
                //設定ファイル(servername.conf)の指定ディレクトリ
                licenseUrl = ConfigBundle.getValue("LICENSE_PAGE_URL");
                form.setLicensePageUrl(licenseUrl);
            }
        }

        //GSUIDを取得
        CmnContmDao cntDao = new CmnContmDao(con);
        String gsUid = cntDao.getGsUid();
        if (!StringUtil.isNullZeroString(gsUid)) {
            form.setGsUid(LicenseOperation.getDecryString(gsUid));
        }

        Man002ParamModel paramMdl = new Man002ParamModel();
        paramMdl.setParam(form);
        Man002Biz biz = new Man002Biz();
        biz.setInitData(paramMdl, getPluginConfigForMain(getPluginConfig(req), con),
                        getRequestModel(req));
        paramMdl.setFormData(form);

        //パスワード変更の有効・無効を設定
        if (canChangePassword()) {
            form.setChangePassword(GSConst.CHANGEPASSWORD_PARMIT);
        } else {
            form.setChangePassword(GSConst.CHANGEPASSWORD_NOTPARMIT);
        }

        //システム区分
        if (!GroupSession.getResourceManager().getDomain(req).equals(GSConst.GS_DOMAIN)) {
            form.setSysKbn(1);
            //DB使用量を取得
            form.setDbUse(
                  iGsManager.getDbUse(iGsManager.getDomain(req)));
            //DB使用可能容量
            form.setDbCanUse(iGsManager.getDbCanUse(iGsManager.getDomain(req)));
        }

        con.setAutoCommit(false);

        return map.getInputForward();
    }
}