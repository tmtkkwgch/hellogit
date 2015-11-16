package jp.groupsession.v2.cmn.login.biz;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.http.BrowserUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.ILoginLogoutListener;
import jp.groupsession.v2.cmn.LoginLogoutListenerUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.AuthDao;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnLoginHistoryDao;
import jp.groupsession.v2.cmn.dao.base.CmnPswdConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrLangDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrThemeDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.dao.base.MblUsrThemeDao;
import jp.groupsession.v2.cmn.login.ILogin;
import jp.groupsession.v2.cmn.login.LoginModel;
import jp.groupsession.v2.cmn.login.LoginResultModel;
import jp.groupsession.v2.cmn.model.base.CmnLoginHistoryModel;
import jp.groupsession.v2.cmn.model.base.CmnPswdConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrLangModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmModel;
import jp.groupsession.v2.usr.GSConstUser;
import jp.groupsession.v2.usr.GSPassword;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] ログイン処理インターフェースの実装クラス
 * <br>[解  説] 通常のログイン処理を行う
 * <br>[備  考]
 *
 * @author JTS
 */
public class GSLoginBiz implements ILogin {

    /**
     * <br>[機  能] ユーザが存在するかを確認する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param loginId ログインID
     * @param password パスワード
     * @return true:存在する、false:存在しない
     * @throws Exception 実行時例外
     */
    public boolean isExistsUser(Connection con, String loginId, String password)
    throws Exception {
        //ユーザ情報取得
        AuthDao adao = new AuthDao(con);
        BaseUserModel smodel = null;
        try {
            String epassword =
                GSPassword.getEncryPassword(password);
            smodel = adao.selectLogin(loginId, epassword);
        } catch (SQLException e) {
            log__.error("SQL実行エラー:ログイン処理の実行に失敗", e);
            throw e;
        }
        if (smodel != null) {
            return true;
        }
        return false;
    }

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(GSLoginBiz.class);

    /**
     * <br>[機  能] ログイン処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param model ログイン処理に使用する各情報
     * @return forward
     * @throws Exception ログイン処理時に例外発生
     */
    public LoginResultModel login(LoginModel model) throws Exception {
        return __login(model, 0);
    }
    /**
     * <br>[機  能] ログイン処理を行う
     * <br>[解  説] APIアクセス時に実行
     * <br>[備  考]
     * @param model ログイン処理に使用する各情報
     * @return forward
     * @throws Exception ログイン処理時に例外発生
     */
    public LoginResultModel login2(LoginModel model) throws Exception {
        return __login(model, 1);
    }
    /**
     * <br>[機  能] ログイン処理を行う
     * <br>[解  説] 自動ログイン時に使用
     * <br>[備  考]
     * @param model ログイン処理に使用する各情報
     * @return ログイン結果
     * @throws Exception ログイン処理時に例外発生
     */
    public LoginResultModel autoLogin(LoginModel model) throws Exception {
        LoginResultModel resultModel = new LoginResultModel();
        return resultModel;
    }
    /**
     * <br>[機  能] ログイン処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param model ログイン処理に使用する各情報
     * @param mode 0:通常 1:API
     * @return forward
     * @throws Exception ログイン処理時に例外発生
     */
    private LoginResultModel __login(LoginModel model, int  mode) throws Exception {

        LoginResultModel resultModel = new LoginResultModel();

        ActionForward forward = null;
        Connection con = model.getCon();
        HttpServletRequest req = model.getReq();
        String loginId = model.getLoginId();
        String password = model.getPassword();
        ActionMapping map = model.getMap();

        //ユーザ情報取得
        AuthDao adao = new AuthDao(con);
        BaseUserModel smodel = null;
        try {
            String epassword =
                GSPassword.getEncryPassword(password);
            smodel = adao.selectLogin(loginId, epassword);
        } catch (SQLException e) {
            log__.error("SQL実行エラー:ログイン処理の実行に失敗", e);
            throw e;
        }

        //該当ユーザなし
        if (smodel == null) {
            ActionErrors errors = new ActionErrors();
            ActionMessage msg = new ActionMessage("error.auth.notfound.idpass");
            StrutsUtil.addMessage(errors, msg, "error.auth.notfound.idpass");
            resultModel.setErrors(errors);
            resultModel.setForward(map.getInputForward());
            return resultModel;
        } else if (smodel.getUsrsid() == 1) {

            //システムメールユーザはログイン不可
            ActionErrors errors = new ActionErrors();
            ActionMessage msg = new ActionMessage("error.auth.notfound.idpass");
            StrutsUtil.addMessage(errors, msg, "error.auth.notfound.idpass");
            resultModel.setErrors(errors);
            resultModel.setForward(map.getInputForward());
            return resultModel;
        }

        //管理者判定
        GroupDao gdao = new GroupDao(con);
        if (gdao.isBelongAdmin(smodel.getUsrsid())) {
            //管理者
            smodel.setAdminFlg(true);
        } else {
            //一般
            smodel.setAdminFlg(false);
        }

        //前回ログイン時間を取得する。
        CmnUsrmInfDao cidao = new CmnUsrmInfDao(con);
        UDate lsttime = cidao.getLastLoginTime(smodel.getUsrsid());
        if (lsttime != null) {
            String strSdate =
                UDateUtil.getSlashYYMD(lsttime)
                + "  "
                + UDateUtil.getSeparateHMS(lsttime);
            smodel.setLstLogintime(strSdate);
        }

        //テーマ画像のパス設定
        CmnUsrThemeDao utdao = new CmnUsrThemeDao(con);
        smodel.setCtmPath(utdao.select(smodel.getUsrsid()));

        //言語の設定
        CmnUsrLangDao ldao = new CmnUsrLangDao(con);
        CmnUsrLangModel lmdl = null;
        lmdl = ldao.select(smodel.getUsrsid());
        if (lmdl != null) {
            smodel.setCountry(lmdl.getCulCountry());
        }

        //会社ドメインの設定
        String domain = GroupSession.getResourceManager().getDomain(req);
        smodel.setDomain(domain);

        //セッション確立
        if (mode == 0) {
            //PC
            _setSession(req, smodel, con);
        } else {
            //API
            _setSessionApi(req, smodel, con);
        }

        boolean commitFlg = false;

        if (mode == 0) {
            try {

                int usrSid = smodel.getUsrsid();
                UDate now = new UDate();

                //ログイン履歴に登録
                CmnLoginHistoryModel hismodel = new CmnLoginHistoryModel();
                hismodel.setUsrSid(smodel.getUsrsid());
                hismodel.setClhTerminal(GSConstCommon.TERMINAL_KBN_PC);
                hismodel.setClhIp(NullDefault.getString(CommonBiz.getRemoteAddr(req), ""));
                hismodel.setClhCar(GSConstCommon.CAR_KBN_PC);
                hismodel.setClhUid(null);
                hismodel.setClhAuid(usrSid);
                hismodel.setClhAdate(now);
                hismodel.setClhEuid(usrSid);
                hismodel.setClhEdate(now);

                CmnLoginHistoryDao hisDao = new CmnLoginHistoryDao(con);
                hisDao.insert(hismodel);

                CmnUsrmInfModel ltmodel = new CmnUsrmInfModel();
                ltmodel.setUsrSid(usrSid);
                ltmodel.setUsiLtlgin(now);
                ltmodel.setUsiEdate(now);
                ltmodel.setUsiEuid(usrSid);

                //最終ログイン時間を更新
                adao.updateLastLoginTime(ltmodel);
                commitFlg = true;

            } catch (SQLException e) {
                log__.error("ログイン時間の更新に失敗", e);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    JDBCUtil.rollback(con);
                }
            }
        }

        commitFlg = false;
        try {
            //ログイン、ログアウトリスナー取得
            log__.debug("ログインリスナー doLogin()開始");
            ILoginLogoutListener[] lis =
                LoginLogoutListenerUtil.getLoginLogoutListeners(model.getPluginConfig());
            //各プラグインリスナーを呼び出し
            for (int i = 0; i < lis.length; i++) {
                lis[i].doLogin(con, smodel.getUsrsid());
            }
            commitFlg = true;
            log__.debug("ログインリスナー doLogin()終了");
        } catch (ClassNotFoundException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } catch (IllegalAccessException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } catch (InstantiationException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } catch (SQLException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }

        //遷移先
        String forwardName = checkForwordPassowrd(smodel, con);
        forward = map.findForward(forwardName);
        resultModel.setResult(true);
        resultModel.setForward(forward);
        return resultModel;
    }
    /**
     * <br>[機  能] モバイル用ログイン処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param model ログイン処理に使用する各情報
     * @return forward
     * @throws Exception ログイン処理時に例外発生
     */
    public LoginResultModel loginMbl(LoginModel model) throws Exception {
        LoginResultModel resultModel = new LoginResultModel();

        ActionForward forward = null;
        Connection con = model.getCon();
        HttpServletRequest req = model.getReq();
        String loginId = model.getLoginId();
        String password = model.getPassword();
        ActionMapping map = model.getMap();

        //ユーザ情報取得
        AuthDao adao = new AuthDao(con);
        BaseUserModel smodel = null;
        try {
            String epassword =
                GSPassword.getEncryPassword(password);
            smodel = adao.selectLogin(loginId, epassword);
        } catch (SQLException e) {
            log__.error("SQL実行エラー:ログイン処理の実行に失敗", e);
            throw e;
        }

        //該当ユーザなし
        if (smodel == null) {
            ActionErrors errors = new ActionErrors();
            ActionMessage msg = new ActionMessage("error.auth.notfound.idpass");
            StrutsUtil.addMessage(errors, msg, "error.auth.notfound.idpass");
            resultModel.setErrors(errors);
            resultModel.setForward(map.getInputForward());
            return resultModel;
        }

      //システム予約ユーザはモバイルでのログインは不可
        if (__checkSystemUser(smodel)) {
            ActionErrors errors = new ActionErrors();
            ActionMessage msg = new ActionMessage("error.auth.notfound.idpass");
            StrutsUtil.addMessage(errors, msg, "error.auth.notfound.idpass");
            resultModel.setErrors(errors);
            resultModel.setForward(map.getInputForward());
            return resultModel;
        }

        //管理者判定
        GroupDao gdao = new GroupDao(con);
        if (gdao.isBelongAdmin(smodel.getUsrsid())) {
            //管理者
            smodel.setAdminFlg(true);
        } else {
            //一般
            smodel.setAdminFlg(false);
        }

        //前回ログイン時間を取得する。
        CmnUsrmInfDao cidao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel uinfModel = cidao.select(smodel.getUsrsid());
        UDate lsttime = null;
        if (uinfModel != null) {
            lsttime = uinfModel.getUsiLtlgin();
        }
        if (lsttime != null) {
            String strSdate =
                UDateUtil.getSlashYYMD(lsttime)
                + "  "
                + UDateUtil.getSeparateHMS(lsttime);
            smodel.setLstLogintime(strSdate);
        }

        //テーマ画像のパス設定
        CmnUsrThemeDao utdao = new CmnUsrThemeDao(con);
        smodel.setCtmPath(utdao.select(smodel.getUsrsid()));

        //言語の設定
        CmnUsrLangDao ldao = new CmnUsrLangDao(con);
        CmnUsrLangModel lmdl = null;
        lmdl = ldao.select(smodel.getUsrsid());
        if (lmdl != null) {
            smodel.setCountry(lmdl.getCulCountry());
        }

        //スマートフォンのテーマの設定
        MblUsrThemeDao mutdao = new MblUsrThemeDao(con);
        smodel.setMblTheme(mutdao.select(smodel.getUsrsid()));
        log__.debug("セッションのテーマ＝" + smodel.getMblTheme());

        //会社ドメインの設定
        String domain = GroupSession.getResourceManager().getDomain(req);
        smodel.setDomain(domain);

        //セッション確立
        _setSession(req, smodel, con);

        boolean commitFlg = false;
        try {
            // エージェント取得
            String agent = NullDefault.getString(
                    req.getHeader(BrowserUtil.USER_AGENT_KEYWORD), "");
            String uid = "";

            //固体識別番号判別
            // クライアント = SoftBank
            if (BrowserUtil.isVodafone(req) || BrowserUtil.isSoftBank(req)) {
                // SoftBankはエージェントから固体識別番号抽出
                String[] splitAgent = agent.split("/");
                if (splitAgent != null && splitAgent.length >= 0) {
                    for (int i = 0; i < splitAgent.length; i++) {
                        if (splitAgent[i].startsWith("SN")) {
                            uid = splitAgent[i];
                            if (uid.indexOf(" ") > 0) {
                                uid = uid.substring(0, uid.indexOf(" "));
                            }
                        }
                    }
                }
                // クライアント ＝ AU
            } else if (BrowserUtil.isAu(req)) {
                uid = req.getHeader("X-Up-Subno");
                // クライアント ＝ docomo
            } else if (BrowserUtil.isDocomo(req)) {
                uid = req.getHeader("user-agent");
                int begin = uid.indexOf("ser");
                if (begin > 0) {
                    String str = uid.substring(begin);
                    int end = -1;
                    if (str.length() > 17) {
                        end = begin + 18;
                    }
                    uid = uid.substring(begin, end);
                }
            } else {
                uid = "";
            }

            //キャリア判別
            int clhKbn = 0;
            // キャリア DoCoMo
            if (BrowserUtil.isDocomo(req)) {
                clhKbn = GSConstCommon.CAR_KBN_DOCOMO;

                // キャリア KDDI
            } else if (BrowserUtil.isAuWap20(req)
                    || agent.indexOf("BREW-Applet") >= 0) {
                clhKbn = GSConstCommon.CAR_KBN_KDDI;

                // キャリア SoftBank
            } else if (BrowserUtil.isVodafone(req) || BrowserUtil.isSoftBank(req)) {
                clhKbn = GSConstCommon.CAR_KBN_SOFTBANK;
            }

            //ログイン履歴に登録
            int usrSid = smodel.getUsrsid();
            UDate now = new UDate();

            CmnLoginHistoryModel hismodel = new CmnLoginHistoryModel();
            hismodel.setUsrSid(smodel.getUsrsid());
            hismodel.setClhTerminal(GSConstCommon.TERMINAL_KBN_MOBILE);
            hismodel.setClhIp(NullDefault.getString(CommonBiz.getRemoteAddr(req), ""));
            hismodel.setClhCar(clhKbn);
            hismodel.setClhUid(uid);
            hismodel.setClhAuid(usrSid);
            hismodel.setClhAdate(now);
            hismodel.setClhEuid(usrSid);
            hismodel.setClhEdate(now);

            CmnLoginHistoryDao hisDao = new CmnLoginHistoryDao(con);
            hisDao.insert(hismodel);

            CmnUsrmInfModel ltmodel = new CmnUsrmInfModel();
            ltmodel.setUsrSid(usrSid);
            ltmodel.setUsiLtlgin(now);
            ltmodel.setUsiEdate(now);
            ltmodel.setUsiEuid(usrSid);

            //最終ログイン時間を更新
            adao.updateLastLoginTime(ltmodel);
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("ログイン時間の更新に失敗", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }

        commitFlg = false;
        try {
            //ログイン、ログアウトリスナー取得
            log__.debug("ログインリスナー doLogin()開始");
            ILoginLogoutListener[] lis =
                LoginLogoutListenerUtil.getLoginLogoutListeners(model.getPluginConfig());
            //各プラグインリスナーを呼び出し
            for (int i = 0; i < lis.length; i++) {
                lis[i].doLogin(con, smodel.getUsrsid());
            }
            con.commit();
            commitFlg = true;
            log__.debug("ログインリスナー doLogin()終了");
        } catch (ClassNotFoundException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } catch (IllegalAccessException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } catch (InstantiationException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } catch (SQLException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } finally {
            if (!commitFlg) {
                JDBCUtil.rollback(con);
            }
        }

        //遷移先
        String forwardName = checkForwordPassowrd(smodel, con);
        forward = map.findForward(forwardName);
        resultModel.setResult(true);
        resultModel.setForward(forward);

        return resultModel;
    }

    /**
     * <br>[機  能] モバイル用ログイン処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param model ログイン処理に使用する各情報
     * @return forward
     * @throws Exception ログイン処理時に例外発生
     */
    public LoginResultModel loginMblApi(LoginModel model) throws Exception {
        LoginResultModel resultModel = new LoginResultModel();

        ActionForward forward = null;
        Connection con = model.getCon();
        HttpServletRequest req = model.getReq();
        String loginId = model.getLoginId();
        String password = model.getPassword();

        //ユーザ情報取得
        AuthDao adao = new AuthDao(con);
        BaseUserModel smodel = null;
        try {
            String epassword =
                GSPassword.getEncryPassword(password);
            smodel = adao.selectLogin(loginId, epassword);
        } catch (SQLException e) {
            log__.error("SQL実行エラー:ログイン処理の実行に失敗", e);
            throw e;
        }

        //該当ユーザなし
        if (smodel == null) {
            ActionErrors errors = new ActionErrors();
            ActionMessage msg = new ActionMessage("error.auth.notfound.idpass");
            StrutsUtil.addMessage(errors, msg, "error.auth.notfound.idpass");
            resultModel.setErrors(errors);
            return resultModel;
        }

      //システム予約ユーザはモバイルでのログインは不可
        if (__checkSystemUser(smodel)) {
            ActionErrors errors = new ActionErrors();
            ActionMessage msg = new ActionMessage("error.auth.notfound.idpass");
            StrutsUtil.addMessage(errors, msg, "error.auth.notfound.idpass");
            resultModel.setErrors(errors);
            return resultModel;
        }

        //管理者判定
        GroupDao gdao = new GroupDao(con);
        if (gdao.isBelongAdmin(smodel.getUsrsid())) {
            //管理者
            smodel.setAdminFlg(true);
        } else {
            //一般
            smodel.setAdminFlg(false);
        }

        //前回ログイン時間を取得する。
        CmnUsrmInfDao cidao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel uinfModel = cidao.select(smodel.getUsrsid());
        UDate lsttime = null;
        if (uinfModel != null) {
            lsttime = uinfModel.getUsiLtlgin();
        }
        if (lsttime != null) {
            String strSdate =
                UDateUtil.getSlashYYMD(lsttime)
                + "  "
                + UDateUtil.getSeparateHMS(lsttime);
            smodel.setLstLogintime(strSdate);
        }

        //テーマ画像のパス設定
        CmnUsrThemeDao utdao = new CmnUsrThemeDao(con);
        smodel.setCtmPath(utdao.select(smodel.getUsrsid()));

        //言語の設定
        CmnUsrLangDao ldao = new CmnUsrLangDao(con);
        CmnUsrLangModel lmdl = null;
        lmdl = ldao.select(smodel.getUsrsid());
        if (lmdl != null) {
            smodel.setCountry(lmdl.getCulCountry());
        }

        //スマートフォンのテーマの設定
        MblUsrThemeDao mutdao = new MblUsrThemeDao(con);
        smodel.setMblTheme(mutdao.select(smodel.getUsrsid()));
        log__.debug("セッションのテーマ＝" + smodel.getMblTheme());

        //会社ドメインの設定
        String domain = GroupSession.getResourceManager().getDomain(req);
        smodel.setDomain(domain);

        //セッション確立
        _setSession(req, smodel, con);

        boolean commitFlg = false;
        try {
            //ログイン、ログアウトリスナー取得
            log__.debug("ログインリスナー doLogin()開始");
            ILoginLogoutListener[] lis =
                LoginLogoutListenerUtil.getLoginLogoutListeners(model.getPluginConfig());
            //各プラグインリスナーを呼び出し
            for (int i = 0; i < lis.length; i++) {
                lis[i].doLogin(con, smodel.getUsrsid());
            }
            con.commit();
            commitFlg = true;
            log__.debug("ログインリスナー doLogin()終了");
        } catch (ClassNotFoundException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } catch (IllegalAccessException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } catch (InstantiationException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } catch (SQLException e) {
            log__.error("リスナー起動に失敗しました。", e);
        } finally {
            if (!commitFlg) {
                JDBCUtil.rollback(con);
            }
        }


        resultModel.setResult(true);
        resultModel.setForward(forward);

        return resultModel;
    }

    /**
     * <br>[機  能] パスワード変更の可否を返す
     * <br>[解  説]
     * <br>[備  考]
     * @return true:変更可能 false:変更不可
     */
    public boolean canChangePassword() {
        return true;
    }

    /**
     * <br>[機  能] パスワードのフォーマットを行う
     * <br>[解  説] データベースに登録時はこのメソッドでフォーマットした
     * <br>         パスワードが使用される
     * <br>[備  考]
     * @param password パスワード
     * @return パスワード
     */
    public String formatPassword(String password) {
        return password;
    }

    /**
     * <br>[機  能] セッションを確立する
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param smodel ユーザ情報
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    protected void _setSession(HttpServletRequest req,
            BaseUserModel smodel, Connection con)
    throws SQLException {

        //セッション情報を削除
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        session = req.getSession(true);
        session.setAttribute(GSConst.SESSION_KEY, smodel);
        //セッションの有効時間設定
        CommonBiz cmnBiz = new CommonBiz();
        session.setMaxInactiveInterval(cmnBiz.getSessionTime(con));

    }

    /**
     * <br>[機  能] セッションを確立する
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param smodel ユーザ情報
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    protected void _setSessionApi(HttpServletRequest req,
            BaseUserModel smodel, Connection con)
    throws SQLException {

        //セッション情報を削除
        HttpSession session = req.getSession(true);
        session.setAttribute(GSConst.SESSION_KEY, smodel);
        //セッションの有効時間設定
        CommonBiz cmnBiz = new CommonBiz();
        session.setMaxInactiveInterval(cmnBiz.getSessionTime(con));

    }


    /**
     * <br>[機  能] ユーザがシステム予約ユーザかを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param smodel ユーザ情報
     * @return 判定結果 true:システム予約ユーザ false:一般ユーザ
     */
    private boolean __checkSystemUser(BaseUserModel smodel) {
        if (smodel != null) {
            int userSid = smodel.getUsrsid();
            return userSid == GSConst.SYSTEM_USER_ADMIN
                    || userSid == GSConst.SYSTEM_USER_MAIL;
        }

        return false;
    }

    /**
     * <br>[機  能] 別ウィンドウで表示するかを返す
     * <br>[解  説]
     * <br>[備  考]
     * @return true: 別ウィンドウ false: 通常
     */
    public boolean isPopup() {
        return false;
    }

    /**
     * <br>[機  能] 自動ログイン処理を行うかを返す
     * <br>[解  説]
     * <br>[備  考]
     * @return true: 自動ログイン処理を行う false: 自動ログイン処理を行わない
     */
    public boolean isAutoLogin() {
        return false;
    }

    /**
     * <br>[機  能] 画面遷移先を返す(frame:メイン画面、pswdLimit:初回ログイン時変更、pswdUpdat:期限切れ時変更)
     * <br>[解  説]
     * <br>[備  考]
     * @param smodel セッションユーザモデル
     * @param con コネクション
     * @return forword 画面遷移先
     * @throws Exception ログイン処理時に例外発生
     */
    public String checkForwordPassowrd(BaseUserModel smodel, Connection con) throws Exception {

        CmnUsrmDao cudao = new CmnUsrmDao(con);
        String forword = "frame";
        //ユーザ情報を取得する
        try {

            CmnUsrmModel usrModel = cudao.select(smodel.getUsrsid());
            //画面遷移先を決める
            if (usrModel != null) {
                // 初回ログイン時パスワード変更区分チェック
                log__.debug("パスワード変更区分 = " + usrModel.getUsrPswdKbn());

                if (usrModel.getUsrPswdKbn() == GSConstUser.PSWD_UPDATE_ON) {
                    forword = "pswdUpdate";
                } else {
                    log__.debug("パスワード変更区分 = " + usrModel.getUsrPswdKbn());
                    // パスワード更新時間の取得
                    UDate pwEdate = usrModel.getUsrPswdEdate();
                    //現在の時間の取得
                    UDate now = new UDate();

                    // パスワード有効日数の取得
                    CmnPswdConfDao pwdao = new CmnPswdConfDao(con);
                    CmnPswdConfModel pwModel = pwdao.select();

                    int limit = -1;
                    if (pwModel != null) {
                        limit = pwModel.getPwcLimitDay();
                    }
                    if (limit != -1) {
                        pwEdate.addDay(limit);
                        log__.debug("パスワード有効期限日 = " + pwEdate);
                        log__.debug("現在日 = " + now);
                        if (now.compare(now, pwEdate) != UDate.LARGE) {
                            forword = "pswdLimit";
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return forword;
    }

    /**
     * <br>[機  能] 自動ログイン失敗時に遷移する画面を返す
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @return ActionForward
     */
    public ActionForward getAutoLoginFailPage(ActionMapping map) {
        return null;
    }
}