package jp.groupsession.v2.cmn.cmn001;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.struts.GSHttpServletRequestWrapper;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.ILoginLogoutListener;
import jp.groupsession.v2.cmn.LoginLogoutListenerUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnLoginConfDao;
import jp.groupsession.v2.cmn.login.ILogin;
import jp.groupsession.v2.cmn.login.LoginModel;
import jp.groupsession.v2.cmn.login.LoginResultModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnEnterInfModel;
import jp.groupsession.v2.cmn.model.base.CmnLoginConfModel;
import jp.groupsession.v2.struts.AbstractGsAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] ログイン画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn001Action extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn001Action.class);

    /**
     * <p>
     * アクション実行
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        log__.debug("START");

        Cmn001Form thisForm = (Cmn001Form) form;
        //ログイン失敗回数が規定回数を超えていた場合、ロックアウトを行う
        int loginFailCount = Integer.parseInt(__getLoginFailCount(req));
        if (loginFailCount > 0) {
            con.setAutoCommit(true);
            CmnLoginConfDao loginConfDao = new CmnLoginConfDao(con);
            CmnLoginConfModel loginConfData = loginConfDao.getData();
            con.setAutoCommit(false);

            if (loginConfData != null
            && loginConfData.getLlcLockoutKbn() == GSConstCommon.LOGIN_LOCKKBN_LOCKOUT
            && loginFailCount >= loginConfData.getLlcFailCnt()) {
                thisForm.setCmn001LoginCtrl(Cmn001Form.LOGIN_CTRL_NG);
                return map.getInputForward();
            }
        }

        // コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd.trim();

        //初回アクセス
        boolean initAccess = thisForm.getCmn001initAccess() != Cmn001Form.INITACCESS_ACCESSED;
        thisForm.setCmn001initAccess(Cmn001Form.INITACCESS_ACCESSED);

        log__.debug("CMD = " + cmd);
        ActionForward forward = null;
        if (cmd.equals("login")) {
            //ログイン
            forward = __doLogin(map, thisForm, req, res, con);
        } else if (cmd.equals("logout")) {

            //ロゴ画像バイナリSIDを取得
            __getImgBinSid(map, thisForm, req, res, con);

            //ログアウト
            forward = __doLogout(map, thisForm, req, res, con);
            ILogin loginBiz = _getLoginInstance();

            if (loginBiz.isPopup()) {
                //別ウィンドウで表示している場合、共通メッセージ画面へ遷移する
                Cmn999Form cmn999Form = new Cmn999Form();
                cmn999Form.setType(Cmn999Form.TYPE_OK);
                cmn999Form.setIcon(Cmn999Form.ICON_INFO);
                cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
                cmn999Form.setType_popup(Cmn999Form.POPUP_TRUE);
                GsMessage gsMsg = new GsMessage(getRequestModel(req));
                cmn999Form.setMessage(gsMsg.getMessage("cmn.logout.comp.popup"));
                req.setAttribute("cmn999Form", cmn999Form);
                forward = map.findForward("gf_msg");
            } else {
                saveToken(req);
            }
        } else if (cmd.equals("getImageFile")) {

            //ロゴ画像の読込
            __doGetImageFile(map, thisForm, req, res, con);

        } else {
            //初回アクセス時、セッションが確立していない、かつ自動ログインONの場合、ログイン処理を行う
            ILogin loginBiz = _getLoginInstance();
            LoginModel loginModel = __createLoginModel(map, thisForm, req, con);
            if (initAccess && !isSession(req) && loginBiz.isAutoLogin()) {
                LoginResultModel resultModel = loginBiz.autoLogin(loginModel);
                if (!resultModel.isResult()) {
                    forward = loginBiz.getAutoLoginFailPage(map);
                    if (forward == null) {
                        return getAuthErrorPage(map, req);
                    }
                }
            }

            //セッション確立済みの場合はメインページへ遷移する
            if (isSession(req)) {

                String forword = loginBiz.checkForwordPassowrd(
                        getSessionUserModel(req),
                        con);
                return map.findForward(forword);
            }

            if (!loginBiz.isAutoLogin() || initAccess) {
                //ロゴ画像バイナリSIDを取得
                __getImgBinSid(map, thisForm, req, res, con);

                //デフォルト ログアウトも含む
                forward = __doLogout(map, thisForm, req, res, con);

                saveToken(req);
            }
        }

        GSHttpServletRequestWrapper wrapper
        = new GSHttpServletRequestWrapper(req);
        String domain = GroupSession.getResourceManager().getDomain(req);
        if (!StringUtil.isNullZeroString(domain)) {
            if (wrapper.getSession(false) == null) {
                wrapper.getSession(true);
            }
            wrapper.getSession(false).setAttribute("domain", domain);
        }

        log__.debug("END");
        return forward;
    }

    /**
     * <p>ログイン
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    public ActionForward __doLogin(ActionMapping map, Cmn001Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);

        //ロゴ画像バイナリSIDを取得
        __getImgBinSid(map, form, req, res, con);

        //入力チェック
        ActionErrors errors = form.validateLogin(map, getRequestModel(req));
        if (errors.size() > 0) {
            //ロックアウト処理を行う
            __lockout(req, res, form, con);

            addErrors(req, errors);

            saveToken(req);
            return map.getInputForward();
        }

        //２重投稿チェック
        if (form.getCmn001loginType() == Cmn001Form.LOGIN_TYPE_SCREEN
        && !isTokenValid(req, true)) {
            __doLogout(map, form, req, res, con);
            return getAuthErrorPage(map, req);
        }

        //ログイン処理に必要な情報を設定する
        LoginModel loginModel = __createLoginModel(map, form, req, con);

        con.setAutoCommit(false);

        //ログイン処理を行う
        ILogin loginBiz = _getLoginInstance();
        LoginResultModel resultModel = loginBiz.login(loginModel);

        GsMessage gsMsg = new GsMessage(req);
        String login = gsMsg.getMessage(req, "mobile.17");

        //ログイン処理に失敗 かつ ActionErrorsが設定されている場合
        if (!resultModel.isResult() && resultModel.getErrors() != null) {
            //ロックアウト処理を行う
            con.setAutoCommit(true);
            __lockout(req, res, form, con);
            con.setAutoCommit(false);

            addErrors(req, resultModel.getErrors());
            saveToken(req);
        } else {
            //ログイン失敗回数をクリアする
            __setCookie(req, res, "0", 0);

            //ログ出力
            CommonBiz cmnBiz = new CommonBiz();
            cmnBiz.outPutCommonLog(map, getRequestModel(req), gsMsg, con,
                    login, GSConstLog.LEVEL_INFO,
                    NullDefault.getString(form.getCmn001Userid(), ""));
        }

        return resultModel.getForward();
    }

    /**
     * <p>ログアウト
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    public ActionForward __doLogout(
        ActionMapping map,
        Cmn001Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd.trim();

        //ログインユーザSIDを取得
        int userSid = -1;
        String loginId = "";
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
            loginId = buMdl.getLgid();
        }

        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        String logout = gsMsg.getMessage("mobile.11");

        if (cmd.equals("logout")) {
            //ログ出力
            CommonBiz cmnBiz = new CommonBiz();
            cmnBiz.outPutCommonLog(map, getRequestModel(req), gsMsg, con,
                    logout, GSConstLog.LEVEL_INFO,
                    loginId);
        }

        //未ログインの場合はリスナーを呼び出さない
        if (userSid != -1) {
            boolean commitFlg = false;
            try {

                //ログイン、ログアウトリスナー取得
                log__.debug("ログアウトリスナー doLogout()開始");
                //使用可能プラグイン情報を取得
                PluginConfig pconf = getPluginConfigForMain(getPluginConfig(req), con);
                ILoginLogoutListener[] lis =
                    LoginLogoutListenerUtil.getLoginLogoutListeners(pconf);
                //各プラグインリスナーを呼び出し
                for (int i = 0; i < lis.length; i++) {
                    lis[i].doLogout(req.getSession(), con, userSid,
                            GroupSession.getResourceManager().getDomain(req));
                }
                commitFlg = true;
                log__.debug("ログアウトリスナー doLogout()終了");
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

                //セッション破棄
                removeSession(req);
            }
        }

        //ログアウト後の画面を表示
        return map.getInputForward();
    }

    /**
     * <p>
     * セッションが確立されていない状態でのアクセスを許可するのか判定を行う。
     * <p>
     * サブクラスでこのメソッドをオーバーライドして使用する
     *
     * @param req
     *            リクエスト
     * @param form
     *            アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNoSessionAccess(HttpServletRequest req, ActionForm form) {
        return true;
    }
    /**
     * <p>アクセス可能なドメインかを判定
     * @param req リクエスト
     * @return true:許可する,false:許可しない
     */
    public boolean canAccessDomain(HttpServletRequest req) {
        return true;
    }

    /**
     * <br>[機  能] ロックアウト処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param res レスポンス
     * @param form フォーム
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @throws UnsupportedEncodingException URLEncode時の文字コードが不正
     */
    private void __lockout(HttpServletRequest req, HttpServletResponse res,
                            Cmn001Form form, Connection con)
    throws SQLException, UnsupportedEncodingException {

        CmnLoginConfDao loginConfDao = new CmnLoginConfDao(con);
        CmnLoginConfModel loginConfData = loginConfDao.getData();

        if (loginConfData == null
        || loginConfData.getLlcLockoutKbn() == GSConstCommon.LOGIN_LOCKKBN_NOSET) {
            return;
        }

        String failCount = __getLoginFailCount(req);
        failCount = String.valueOf(Integer.parseInt(failCount) + 1);

        int time = loginConfData.getLlcFailAge();
        if (Integer.parseInt(failCount) >= loginConfData.getLlcFailCnt()) {
            form.setCmn001LoginCtrl(Cmn001Form.LOGIN_CTRL_NG);
            time = loginConfData.getLlcLockAge();
        }

        __setCookie(req, res, failCount, time * 60);
    }

    /**
     * <br>[機  能] ログイン失敗回数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return ログイン失敗回数
     */
    private String __getLoginFailCount(HttpServletRequest req) {
        String failCount = "0";

        //全てのクッキー情報を取得
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            //必要なクッキー情報を取得
            for (Cookie cookie : cookies) {
                String cname = cookie.getName();
                cname = __decodeCookies(cname);
                if (!StringUtil.isNullZeroString(cname)
                && cname.equals(GSConstCommon.LOCKOUT_FAILCOUNT)) {
                    failCount = NullDefault.getString(__decodeCookies(cookie.getValue()), "0");
                    break;
                }
            }

            if (!ValidateUtil.isNumber(failCount)
            || (new BigInteger(failCount)).compareTo(
                    new BigInteger(String.valueOf(Integer.MAX_VALUE))) > 0) {
                failCount = "0";
            }
        }
        return failCount;
    }

    /**
     * <br>[機  能] Cookieを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param res レスポンス
     * @param failCount ログイン失敗回数
     * @param maxAge Cookie の最長存続期間
     * @throws UnsupportedEncodingException URLEncode時の文字コードが不正
     */
    private void __setCookie(HttpServletRequest req, HttpServletResponse res,
                            String failCount, int maxAge)
    throws UnsupportedEncodingException {

        String value = null;
        try {
            value = URLEncoder.encode(failCount, GSConst.ENCODING);
            log__.info("__setLoginFailedCookie.value==>" + value);
        } catch (UnsupportedEncodingException e) {
            log__.error("URLEncodeに失敗", e);
            throw e;
        }

        Cookie cookie = new Cookie(GSConstCommon.LOCKOUT_FAILCOUNT, value);
        // Cookie の最長存続期間
        cookie.setMaxAge(maxAge);

        // Cookie のPATH
        String cookiePath = req.getRequestURI();
        if (cookiePath.startsWith("/")) {
            cookiePath = cookiePath.substring(1);
        }
        cookiePath = "/" + cookiePath.substring(0, cookiePath.indexOf("/"));
        cookie.setPath(cookiePath);

        res.addCookie(cookie);
    }

    /**
     * <p>クッキーの値をURLEncode前の文字列にデコードする
     * @param cookie クッキーの値
     * @return デコードした文字列
     */
    private String __decodeCookies(String cookie) {
        //NULLチェック
        if (cookie == null) {
            return null;
        }

        String ret = null;
        try {
            ret = URLDecoder.decode(cookie , "Shift_JIS");
        } catch (UnsupportedEncodingException e) {
        } catch (IllegalArgumentException e) {
        }
        return ret;
    }

    /**
     * <br>[機  能] ロゴ画像バイナリSIDを取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con Connection
     * @throws Exception 実行時例外
     */
    private void __getImgBinSid(ActionMapping map,
                                            Cmn001Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        Cmn001Biz biz = new Cmn001Biz();
        CmnEnterInfModel model = biz.getEnterData(con);
        Long logoBinSid = new Long(0);
        //URL
        String entUrl = GSConst.GS_HOMEPAGE_URL;
        if (model != null) {
            if (model.getEniImgKbn() == 1) {
                logoBinSid = model.getBinSid();
            } else {
                //デフォルトロゴ
                logoBinSid = new Long(0);
            }
            entUrl = NullDefault.getStringZeroLength(model.getEniUrl(), GSConst.GS_HOMEPAGE_URL);
        }
        form.setCmn001BinSid(logoBinSid);

        //URL
        form.setCmn001Url(entUrl);
    }

    /**
     * <br>[機  能] ロゴ画像を読み込む
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con Connection
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doGetImageFile(ActionMapping map,
                                            Cmn001Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        CmnBinfModel cbMdl = null;

        //画像バイナリSIDの照合チェック
        Cmn001Biz biz = new Cmn001Biz();

        Cmn001ParamModel paramModel = new Cmn001ParamModel();
        paramModel.setParam(form);
        boolean logoHnt = biz.checkLogoBinSid(con, paramModel);
        paramModel.setFormData(form);

        if (!logoHnt) {
            return null;

        } else {
            cbMdl = cmnBiz.getBinInfo(con, form.getCmn001BinSid(),
                    GroupSession.getResourceManager().getDomain(req));
        }

        if (cbMdl != null) {
            JDBCUtil.closeConnectionAndNull(con);

            //ファイルをダウンロードする
            TempFileUtil.downloadInline(req, res, cbMdl, getAppRootPath(),
                                        Encoding.UTF_8);
        }
        return null;
    }


    /**
     * <p>ログイン処理時に使用する情報を設定する
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @return アクションフォーム
     * @throws SQLException SQL実行時例外
     */
    private LoginModel __createLoginModel(ActionMapping map, Cmn001Form form,
                                                            HttpServletRequest req,
                                                            Connection con)
    throws SQLException {
        //ログイン処理に必要な情報を設定する
        LoginModel loginModel = new LoginModel();
        loginModel.setLoginId(form.getCmn001Userid());
        loginModel.setPassword(form.getCmn001Passwd());
        loginModel.setCon(con);
        loginModel.setReq(req);
        loginModel.setMap(map);
        //使用可能プラグイン情報を取得
        PluginConfig pconf = getPluginConfigForMain(getPluginConfig(req), con);
        loginModel.setPluginConfig(pconf);

        return loginModel;
    }
}