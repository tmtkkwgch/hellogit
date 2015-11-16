package jp.groupsession.v2.api;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.http.BasicAuthorizationUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.AuthDao;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.login.ILogin;
import jp.groupsession.v2.cmn.login.LoginModel;
import jp.groupsession.v2.cmn.login.LoginResultModel;
import jp.groupsession.v2.struts.AbstractGsAction;
import jp.groupsession.v2.usr.GSPassword;
import net.sf.json.JSON;
import net.sf.json.JSONException;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * <br>[機  能] WEB API共通アクション
 * <br>[解  説] セッションは確立していなくてもOKだが、BASIC認証による認証が必要。
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractApiAction extends AbstractGsAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(AbstractApiAction.class);
    /** プラグインID */
    private static final String PLUGIN_ID = "api";
    /** ユーザプラグインコンフィグ*/
    private PluginConfig userPconfig__ = null;


    /**プラグインIDを取得します
     * @return String プラグインID
     * @see jp.groupsession.v2.struts.AbstractGsAction#getPluginId()
     */
    @Override
    public String getPluginId() {
        return PLUGIN_ID;
    }

    /**
     * <br>[機  能] セッション管理後の動作を記述する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    @SuppressWarnings("all")
    public ActionForward executeAction(
        ActionMapping map,
        ActionForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {

        log__.debug("BASIC認証 START");

        try {
            //BASIC認証によるアクセス判定
            //Authorizationヘッダがあるかどうか判定する
            if (!BasicAuthorizationUtil.canGetAuthorization(req)) {
                //ない場合
                setAuthErrroHeder(req, res);
                return null;
            }
            BaseUserModel umodel = getUserModel(map, req, con);
            if (umodel == null) {
                //該当ユーザがいない場合
                setAuthErrroHeder(req, res);
                return null;
            }
            log__.debug("BASIC認証 END");

            //セッションチェック
            if (isSession(req)) {

                // APIの場合セッションが確立されていなくてもBasic認証されているため問題ない
                //下記の処理はセッションIDを必要とするクライアント用
                //セッションアップデート
                CommonBiz cmnBiz = new CommonBiz();
                _updateSession(req, cmnBiz.getSessionTime(con), con);

                //ドメインチェック
                if (!canAccessDomain(req)) {
                    log__.warn("不正なドメインへのアクセスです");
                    //
                }
            }
            PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));
            userPconfig__ = pconfig;
            ActionErrors errors = null;
            //使用不可のプラグインへアクセスした場合
            if (!super.canAccessPlugin(pconfig.getPluginIdList())) {
                errors = addCantAccsessPluginError(req, null, getPluginId());
            }
            checkCantAccsessRelationPluginError(req, errors);
            Document doc = null;
            if (errors != null && !errors.isEmpty()) {
                addErrors(req, errors);
            } else {
                //XML情報の取得
                doc = createXml(form, req, res, con, umodel);
            }
            ActionMessages errMessages = getErrors(req);
            if (errMessages != null && !errMessages.isEmpty()) {
                //エラーXML出力
                writeErrorResponse(map, req, res, errMessages);
            } else {
                //XML出力
                writeResponse(map, req, res, doc);
            }
        } catch (SocketException e) {
            //クライアントユーザがキャンセルした場合の例外
        } catch (IOException e) {
            Class ecs = e.getClass();
            String ename = ecs.getName();
            if ("org.apache.catalina.connector.ClientAbortException".equals(ename)) {
                //クライアントユーザがキャンセルした場合の例外(コンテナTomcat)
                //何もしない
            } else {
                throw e;
            }
        }
        return null;
    }

    /**
     * <br>[機  能] Basic認証のエラーヘッダーをレスポンスにセットする。
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param res レスポンス
     * @throws IOException IOエラー
     */
    public void setAuthErrroHeder(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        String cpath = req.getContextPath();
        res.setHeader("WWW-Authenticate", "Basic realm=\"" + cpath + "\"");
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    /**
     * <br>[機  能] AuthorizationヘッダからID・パスワードを取得し一致するユーザ情報を返す。一致する情報が存在しない場合はnullを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param req リクエスト
     * @param con DBコネクション
     * @throws Exception SQL例外等
     * @return ユーザ情報。認証に失敗した場合はnullを返す。
     */
    public LoginResultModel getLoginResultModel(
            ActionMapping map, HttpServletRequest req, Connection con) throws Exception {

        String loginId = null;
        LoginResultModel resultModel = null;
        try {
            loginId = BasicAuthorizationUtil.getUserID(req);
            String password = BasicAuthorizationUtil.getPassword(req);

            //ログイン処理に必要な情報を設定する
            LoginModel loginModel = new LoginModel();
            loginModel.setLoginId(loginId);
            loginModel.setPassword(password);
            loginModel.setCon(con);
            loginModel.setReq(req);
            loginModel.setMap(map);
            //使用可能プラグイン情報を取得
            PluginConfig pconf = getPluginConfigForMain(getPluginConfig(req), con);
            loginModel.setPluginConfig(pconf);
            //ログイン処理を行う
            ILogin loginBiz = _getLoginInstance();
            resultModel = loginBiz.login2(loginModel);

            log__.debug("loginId = " + loginId);
            log__.debug("password = " + password);
        } catch (SQLException e) {
            log__.error("SQL実行エラー:ログイン処理の実行に失敗", e);
            throw e;
        }

        return resultModel;
    }

    /**
     * <br>[機  能] AuthorizationヘッダからID・パスワードを取得し一致するユーザ情報を返す。一致する情報が存在しない場合はnullを返す。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param req リクエスト
     * @param con DBコネクション
     * @throws Exception SQL例外等
     * @return ユーザ情報。認証に失敗した場合はnullを返す。
     */
    public BaseUserModel getUserModel(
            ActionMapping map, HttpServletRequest req, Connection con) throws Exception {
        //ユーザ情報取得
        AuthDao adao = new AuthDao(con);
        BaseUserModel smodel = null;
        String loginId = null;
        try {
            loginId = BasicAuthorizationUtil.getUserID(req);
            String epassword =
                GSPassword.getEncryPassword(BasicAuthorizationUtil.getPassword(req));
            //ログイン処理結果(通常 or LDAP判定)
            LoginResultModel resultModel = getLoginResultModel(map, req, con);
            if (resultModel.isResult()) {
                if (!resultModel.isLdapFlg()) {
                    smodel = adao.selectLogin(loginId, epassword);
                } else {
                    smodel = adao.selectLoginNoPwd(loginId, epassword);
                }
            }

        } catch (SQLException e) {
            log__.error("SQL実行エラー:ログイン処理の実行に失敗", e);
            throw e;
        }

        //該当ユーザなし
        if (smodel == null) {
            return null;
        }
        smodel.setLgid(loginId);

        //管理者判定
        GroupDao gdao = new GroupDao(con);
        if (gdao.isBelongAdmin(smodel.getUsrsid())) {
            //管理者
            smodel.setAdminFlg(true);
        } else {
            //一般
            smodel.setAdminFlg(false);
        }
        return smodel;
    }

    /**
     * <br>[機  能] Xmlのレスポンスを出力する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @param res レスポンス
     * @param doc jdomドキュメント
     * @exception IOException IOエラー
     */
    public void writeResponse(ActionMapping map, HttpServletRequest req,
        HttpServletResponse res, Document doc) throws IOException {

        if (doc == null) {
            return;
        }

        //
        //URL情報をルートエレメントの属性としてセット
        String url = __getApiUrl(map, req);
        Element el = doc.getRootElement();
        Attribute at = new Attribute("url", url);
        el.setAttribute(at);

        //XML出力基本情報
        OutputStream out = res.getOutputStream();
        res.setContentType("text/xml" + "; charset=" + "UTF-8");

        //
        XMLOutputter xout = new XMLOutputter();
        //フォーマットを設定する。
        Format format = xout.getFormat();
        format.setEncoding("UTF-8");
        format.setLineSeparator("\r\n");
        format.setIndent("    ");
        format.setOmitDeclaration(true);
        format.setOmitEncoding(true);
        format.setExpandEmptyElements(true);

        String type = "xml";

        if (req.getParameter("type") != null && req.getParameter("type").equals("1"))
        {
            type = "json";
        }


        if (type.equals("json")) {
            //jsonの場合
            String strXml = xout.outputString(doc);
            String strJSON = "";
            try {
            //net.sf.json.xml.XMLSerializer#readで読み込み、JSONオブジェクトを作成
                XMLSerializer xmlSerializer = new XMLSerializer();
                JSON json = xmlSerializer.read(strXml);

                strJSON = json.toString();
            } catch (JSONException je) {
                strJSON = "failed toJSON from xml : " + strXml;
            }
            //レスポンスを返す
            res.setContentType("text/html" + "; charset=" + "UTF-8");
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
            outWriter.write(strJSON);
            outWriter.close();
        } else {
            xout.output(doc, out);
        }

    }

    /**
     * <br>[機  能] APIのURLを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @return APIのURL
     */
    private String __getApiUrl(ActionMapping map, HttpServletRequest req) {
        StringBuilder buf = new StringBuilder();
        String cpath = req.getContextPath();
        if (cpath != null) {
            buf.append(cpath);
        }

        String mapPath = map.getPath();
        if (mapPath != null) {
            buf.append(mapPath);
        }
        if (buf.length() > 0) {
            buf.append(".do");
        }
        log__.debug("web api url = " + buf.toString());
        return buf.toString();
    }

    /**
     * <p>セッションが確立されていない状態でのアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNoSessionAccess(HttpServletRequest req, ActionForm form) {
        return true;
    }
    /** プラグインが使用可能か判定します
     * @param plugins 使用可能なプラグインID
     * @return boolean true:使用可能 false:使用不可
     */
    @Override
    public boolean canAccessPlugin(List<String> plugins) {
        return true;
    }

    /** プラグインが使用可能か判定します
     * @param pluginId 判定するプラグインID
     * @return boolean true:使用可能 false:使用不可
     */
    public boolean canAccsessSelectPlugin(String pluginId) {
        if (pluginId == null) {
            return true;
        }
        if (userPconfig__ != null && userPconfig__.getPlugin(pluginId) != null) {
            return true;
        }
        return false;
    }
    /** プラグインが使用不可能な場合のエラーメッセージを返します
     * @param req リクエスト
     * @param errors ActionErrors
     * @param pluginId 判定するプラグインID
     * @return ActionErrors エラーメッセージを追加したActionErrors
     */
    public ActionErrors addCantAccsessPluginError(HttpServletRequest req
            , ActionErrors errors
            , String pluginId) {
        if (errors == null) {
            errors = new ActionErrors();
        }
        String pluginName
        = getPluginConfig(req).getPlugin(pluginId).getName(getRequestModel(req));
        ActionMessage msg = new ActionMessage("error.cant.use.plugin", pluginName);
        StrutsUtil.addMessage(errors, msg, "error.cant.use.plugin");
        return errors;
    }
    /** 関連プラグインが使用不可能な場合のエラーメッセージを設定します
     * 継承して利用します
     * @param req リクエスト
     * @param errors ActionErrors
     * @return ActionErrors エラーメッセージを追加したActionErrors
     */
    public ActionErrors checkCantAccsessRelationPluginError(HttpServletRequest req
            , ActionErrors errors) {
        if (errors == null) {
            errors = new ActionErrors();
        }
        return errors;
    }

    /**
     * <br>[機  能] レスポンスXML情報を作成する。
     * <br>[解  説]
     * <br>[備  考]
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @param umodel ユーザ情報
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    public abstract Document createXml(
        ActionForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con,
        BaseUserModel umodel
        )
        throws Exception;

    /**
     * <br>[機  能] Refererのチェックは行わない。
     * <br>[解  説] AppResourceのサーバ名称と比較します。
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @return 判定結果 true:正常 false:不正
     */
    public boolean checkReferer(ActionMapping map, HttpServletRequest req) {
        return true;
    }

    /**
     * <br>[機  能] Xmlのレスポンスを出力する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @param res レスポンス
     * @param errors エラーメッセージ
     * @exception IOException IOエラー
     */
    @SuppressWarnings("all")
    public void writeErrorResponse(ActionMapping map, HttpServletRequest req,
        HttpServletResponse res, ActionMessages errors) throws IOException {

        List<String> errMsgList = new ArrayList<String>();
        MessageResources mres = getResources(req);

        Iterator it = errors.get();
        while (it.hasNext()) {
            ActionMessage error = (ActionMessage) it.next();
            errMsgList.add(mres.getMessage(error.getKey(), error.getValues()));
        }

        writeErrorResponse(map, req, res, errMsgList);
    }

    /**
     * <br>[機  能] Xmlのレスポンスを出力する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param req リクエスト
     * @param res レスポンス
     * @param errorMsgList エラーメッセージ一覧
     * @exception IOException IOエラー
     */
    public void writeErrorResponse(ActionMapping map, HttpServletRequest req,
        HttpServletResponse res, List<String> errorMsgList) throws IOException {
        //400 Bad Request
//        String cpath = req.getContextPath();
//        res.sendError(HttpServletResponse.SC_BAD_REQUEST);

        //XML出力基本情報
        OutputStream out = res.getOutputStream();
        res.setContentType("text/xml" + "; charset=" + "UTF-8");

        //XMLフォーマット
        XMLOutputter xout = new XMLOutputter();
        //フォーマットを設定する。
        Format format = xout.getFormat();
        format.setEncoding("UTF-8");
        format.setLineSeparator("\r\n");
        format.setIndent("    ");
        format.setOmitDeclaration(true);
        format.setOmitEncoding(true);
        format.setExpandEmptyElements(true);

        //エラー内容の出力
        //ルートエレメントResultSet
        Element oerrors = new Element("Errors");
        Document doc = new Document(oerrors);

        //URL情報をルートエレメントの属性としてセット
        String url = __getApiUrl(map, req);
        Attribute atUrl = new Attribute("url", url);
        oerrors.setAttribute(atUrl);

        for (String errMsg : errorMsgList) {
            log__.debug("エラー = " + errMsg);
            Element oMessage = new Element("Message");
            oMessage.addContent(errMsg);
            oerrors.addContent(oMessage);
        }

        String type = "xml";

        if (req.getParameter("type") != null && req.getParameter("type").equals("1"))
        {
            type = "json";
        }


        if (type.equals("json")) {
            String strXml = xout.outputString(doc);
            String strJSON = "";
            try {
            //net.sf.json.xml.XMLSerializer#readで読み込み、JSONオブジェクトを作成
                XMLSerializer xmlSerializer = new XMLSerializer();
                JSON json = xmlSerializer.read(strXml);

                strJSON = json.toString();
            } catch (JSONException je) {
                strJSON = "failed toJSON from xml : " + strXml;
            }
            //レスポンスを返す
            res.setContentType("text/html" + "; charset=" + "UTF-8");
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
            outWriter.write(strJSON);
            outWriter.close();
        } else {
            xout.output(doc, out);
        }
    }

    /**
     * [機  能] executeメソッド実行時に例外(Exception)が発生した場合の処理<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @param e executeメソッド実行時に発生した例外(Exception)
     * @return true:Exceptionをthrowする false:Exceptionをthrowしない
     */
    protected boolean _doExcecuteException(ActionMapping map,
                                            ActionForm form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con,
                                            Exception e) {

        try {
            List<String> errMsgList = new ArrayList<String>();
            errMsgList.add(getInterMessage(req, "cmn.unpredictable.exception"));

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            errMsgList.add(sw.toString());

            writeErrorResponse(map, req, res, errMsgList);
        } catch (IOException ie) {
            log__.error("例外XMLの作成に失敗", e);
        }

        return false;
    }

    /**
     * [機  能] executeメソッド実行時に例外(Throwable)が発生した場合の処理<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @param e executeメソッド実行時に発生した例外 or エラー(Throwable)
     * @return true:Exceptionをthrowする false:Exceptionをthrowしない
     */
    protected boolean _doExcecuteThrowable(ActionMapping map,
                                            ActionForm form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con,
                                            Throwable e) {

        try {
            List<String> errMsgList = new ArrayList<String>();
            errMsgList.add(getInterMessage(req, "cmn.unpredictable.exception"));

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            errMsgList.add(sw.toString());

            writeErrorResponse(map, req, res, errMsgList);
        } catch (IOException ie) {
            log__.error("例外XMLの作成に失敗", e);
        }

        return false;
    }
    /**
     * <br>[機  能] org.jdom.Elementインスタンスを生成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param name 名称
     * @param content 内容
     * @return org.jdom.Elementインスタンス
     */
    protected Element _createElement(String name, int content) {
        Element element = new Element(name);
        element.addContent(String.valueOf(content));
        return element;
    }

    /**
     * <br>[機  能] org.jdom.Elementインスタンスを生成します。
     * <br>[解  説]
     * <br>[備  考]
     * @param name 名称
     * @param content 内容
     * @return org.jdom.Elementインスタンス
     */
    protected Element _createElement(String name, Long content) {
        Element element = new Element(name);
        element.addContent(String.valueOf(content));
        return element;
    }

    /**
     * <br>[機  能] org.jdom.Elementインスタンスを生成します。
     * <br>[解  説]
     * <br>[備  考] content内のXML利用不可文字は削除されます。
     * @param name 名称
     * @param content 内容
     * @return org.jdom.Elementインスタンス
     */
    protected Element _createElement(String name, String content) {
        Element element = new Element(name);
        element.addContent(StringUtil.removeInvalidCharacterForXml(content));
        return element;
    }
}