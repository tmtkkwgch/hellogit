package jp.groupsession.v2.anp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.ValidateUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSContext;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.IGsResourceManager;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 安否確認のモバイルプラグインで共通的に使用するアクションクラスです
 * <br>[解  説] モバイル用です
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractAnpiMobileAction extends Action {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(AbstractAnpiMobileAction.class);

    /**
     * <br>[機  能] 各モバイルActionクラス共通で使用する処理を行う
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @return forward アクションフォワード
     * @throws Exception 実行例外
     */
    public final ActionForward execute(ActionMapping map,
                                        ActionForm form,
                                        HttpServletRequest req,
                                        HttpServletResponse res) throws Exception {

        ActionForward forward = null;
        AbstractAnpiMobileForm uform = (AbstractAnpiMobileForm) form;
        Connection con = null;

        try {
            //コネクション取得
            con = __getConnection(req);

            //共通パラメータチェック
            if (!__validateParam(uform)) {
                log__.debug("パラメータエラー");
                forward = getSubmitErrorPage(map, req);
                return forward;
            }

            CommonBiz cmnBiz = new CommonBiz();

            //DBのオペレーションログ設定をGSContextに反映する。
            cmnBiz.setOperationLogGsContext(con,
                    GroupSession.getResourceManager().getDomain(req));

            PluginConfig pconfig
                    = getPluginConfigForMain(getPluginConfig(req), con);
            //使用不可のプラグインへアクセスした場合
            if (!canAccessPlugin(pconfig.getPluginIdList())) {
                //アクセス
                forward = getSubmitErrorPage(map, req);
                return forward;
            }

            //MessageResoucesを設定
            GsMessage gsMsg = new GsMessage();
            GsMessage.setMessageResources(
                    getResources(req,
                                gsMsg.getResourceKey(req)));

            forward = executeAction(map, form, req, res, con);

            return forward;

        } catch (Throwable e) {
            forward = getSystemErrorPage(map, req);
            e.printStackTrace();
            return forward;
        } finally {
            JDBCUtil.closeConnection(con);
        }
    }

    /**
     * <br>[機  能] ログイン管理後の動作を記述する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mapping マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @throws Exception 実行例外
     * @return ActinForward
     */
    public abstract ActionForward executeAction(
        ActionMapping mapping,
        ActionForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception;


    /**
     * <br>[機  能] システムエラーなどの場合に遷移するアクションフォワード名を取得する。
     * <br>[解  説]
     * <br>[備  考]
     *@return フォワード名
     */
    public String getErrorForwardName() {
        return null;
    }

    /**
     * <br>[機  能] DBコネクションを取得します
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param req リクエスト
     * @return DBコネクション
     * @throws Exception 実行時例外
     */
    private Connection __getConnection(HttpServletRequest req) throws Exception {

        GroupSession servlet = (GroupSession) getServlet();
        Connection con = null;

        try {

            con = servlet.getConnection(req);
            con.setAutoCommit(false);

        } catch (SQLException e) {
            log__.fatal("コネクションの取得に失敗", e);
            throw e;
        }
        return con;
    }

    /**
     * <br>[機  能] 採番マスタを取得します
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param req リクエスト
     * @return 取得した採番マスタ
     * @throws Exception 実行例外
     */
    public MlCountMtController getCountMtController(HttpServletRequest req) throws Exception {

        IGsResourceManager resourceManager = GroupSession.getResourceManager();
        MlCountMtController mlCnt
            = resourceManager.getCountController(resourceManager.getDomain(req));
        return mlCnt;
    }

    /**
     * <br>[機  能] プラグイン情報を取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return PluginConfig
     */
    public PluginConfig getPluginConfig(HttpServletRequest req) {

        PluginConfig pconfig = null;
        IGsResourceManager resourceManager = GroupSession.getResourceManager();
        pconfig = resourceManager.getPluginConfig(resourceManager.getDomain(req));
        if (pconfig == null) {
            log__.fatal("プラグインコンフィグの取得に失敗");
        }
        return pconfig;
    }

    /**
     * <br>[機  能] 管理者設定の使用するプラグイン設定を反映したPluginConfigを取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param pconfig プラグイン設定
     * @param con コネクション
     * @return PluginConfig
     * @throws SQLException SQL実行時例外
     */
    public PluginConfig getPluginConfigForMain(PluginConfig pconfig, Connection con)
    throws SQLException {

        PluginConfig ret = new PluginConfig();

        CommonBiz cmnBiz = new CommonBiz();
        List<String> menuPluginIdList = cmnBiz.getCanUsePluginIdList(con, -1);

        Plugin plugin = null;
        for (String pId : menuPluginIdList) {
            plugin = pconfig.getPlugin(pId);
            if (plugin != null) {
                ret.addPlugin(plugin);
            }
        }

      return ret;
  }

    /**
     * <br>[機  能] アプリケーションのルートパスを返す
     * <br>[解  説]
     * <br>[備  考]
     * @return パス
     */
    public String getAppRootPath() {
        GSContext gscontext = getGsContext();
        Object tmp = gscontext.get(GSContext.APP_ROOT_PATH);
        if (tmp == null) {
            return null;
        }
        if (!(tmp instanceof String)) {
            return null;
        }
        String path = (String) tmp;
        return path;
    }

    /**
     * <br>[機  能] GroupSession共通情報を取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @return GSContext
     */
    public GSContext getGsContext() {

        return GroupSession.getContext();
    }

    /**
     * <br>[機  能] Sessionを削除します。
     * <br>[解  説]
     * <br>[備  考] Sessionがない場合は何もしません。
     * @param req リクエスト
     */
    public void removeSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * <br>[機  能] プラグインが使用可能か判定します
     * <br>[解  説]
     * <br>[備  考]
     * @param plugins 使用可能なプラグインID
     * @return boolean true:使用可能 false:使用不可
     */
    public boolean canAccessPlugin(List<String> plugins) {
        for (String plugin : plugins) {
            if (getPluginId() == null
             || plugin.equals(getPluginId())) {
                log__.debug("使用可能なプラグインです。" + getPluginId());
                return true;
            }
        }
        log__.debug("使用不可なプラグインです。" + getPluginId());
        return false;
    }

    /**
     * <br>[機  能] プラグインIDを取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @return String プラグインID
     */
    public String getPluginId() {
        return GSConstAnpi.PLUGIN_ID;
    }

    /**
     * <br>[機  能] アプリケーションのテンポラリディレクトリのパスを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return パス
     */
    public String getTempPath(HttpServletRequest req) {

        String tmp = GroupSession.getResourceManager().getTempPath(req);

        if (StringUtil.isNullZeroString(tmp)) {
            return null;
        }

        return tmp;
    }

    /**
     * <br>[機  能] パラメータチェックを行うかどうかの判定を行う。
     * <br>[解  説] サブクラスでこのメソッドをオーバーライドして使用する
     * <br>[備  考]
     * @return true:チェックする,false:チェックしない
     */
    public boolean isCheckParam() {
        return true;
    }

    /**
     * <br>[機  能] 安否確認SIDのパラメータチェックを行うかどうかの判定を行う。
     * <br>[解  説] サブクラスでこのメソッドをオーバーライドして使用する
     * <br>[備  考]
     * @return true:チェックする,false:チェックしない
     */
    public boolean isCheckParamAnpiSid() {
        return true;
    }

    /**
     * <br>[機  能] パラメータ項目の入力チェックを行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param form アクションフォーム
     * @return boolean true:正常／false:異常
     */
    private boolean __validateParam(AbstractAnpiMobileForm form) {

        if (!isCheckParam()) {
            log__.debug("パラメータチェックなし");
            return true;
        }

        //安否確認SID
        if (isCheckParamAnpiSid()) {
            if (!ValidateUtil.isNumber(form.getAnpiSid())) {
                return false;
            }
        }

        //ユーザSID
        if (!ValidateUtil.isNumber(form.getUserSid())) {
            return false;
        }

        return true;
    }

    /**
     * <br>[機  能] システムエラーなどの場合に遷移する画面を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param req リクエスト
     * @return ActionForward フォワード
     */
    public ActionForward getSystemErrorPage(ActionMapping map,
                                            HttpServletRequest req) {
        return __getErrorPage(map, req, "anpimb_gf_system");
    }

    /**
     * <br>[機  能] ２重投稿エラーなどの場合に遷移する画面を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param req リクエスト
     * @return ActionForward フォワード
     */
    public ActionForward getSubmitErrorPage(ActionMapping map,
                                            HttpServletRequest req) {
        return __getErrorPage(map, req, "anpimb_gf_submit");
    }

    /**
     * <br>[機  能] 権限不足エラーなどの場合に遷移する画面を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param req リクエスト
     * @return ActionForward フォワード
     */
    public ActionForward getPowerErrorPage(ActionMapping map,
                                            HttpServletRequest req) {
        return __getErrorPage(map, req, "anpimb_gf_power");
    }

    /**
     * <br>[機  能] システムエラーなどの場合に遷移する画面を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param req リクエスト
     * @param msgforwardName メッセージフォワード名
     * @return ActionForward フォワード
     */
    private ActionForward __getErrorPage(ActionMapping map, HttpServletRequest req,
                                        String msgforwardName) {

        String retryParam = "";
        if (!StringUtil.isNullZeroString(getErrorForwardName())) {
            ActionForward forwardErr = map.findForward(getErrorForwardName());
            retryParam = "&retryURL=" + forwardErr.getPath();
        }
        log__.debug("エラーURL = " + retryParam);

        ActionForward forward999 = map.findForward(msgforwardName);
        return new ActionForward(forward999.getPath() + retryParam);
    }

    /**
     * <br>[機  能] リクエストの情報を返す
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return RequestModel
     */
    public RequestModel getRequestModel(HttpServletRequest req) {

        RequestModel reqMdl = new RequestModel();

        if (req != null) {
            HttpSession session = req.getSession(false);

            //ユーザモデルを取得
            if (session != null) {
                reqMdl.setSession(session);
                Object tmp = session.getAttribute(GSConst.SESSION_KEY);
                if (tmp != null) {
                    BaseUserModel smodel = (BaseUserModel) tmp;
                    reqMdl.setSmodel(smodel);
                }
            }

            //言語を取得
            reqMdl.setLocale(req.getLocale());

            //リファラーを取得
            reqMdl.setReferer(req.getHeader("REFERER"));

            //ドメインを取得
            reqMdl.setDomain(GroupSession.getResourceManager().getDomain(req));

            //エラーを取得
            if (req.getAttribute(GSConst.ERROR_KEY) != null) {
                reqMdl.setOerror(req.getAttribute(GSConst.ERROR_KEY));
            }

            //クライアントIP
            reqMdl.setRemoteAddr(CommonBiz.getRemoteAddr(req));

            //リクエストURI
            reqMdl.setRequestURI(req.getRequestURI());

            //リクエストURL
            reqMdl.setRequestURL(req.getRequestURL());

        }
        return reqMdl;
    }
}