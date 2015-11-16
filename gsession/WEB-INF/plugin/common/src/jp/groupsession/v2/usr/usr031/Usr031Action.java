package jp.groupsession.v2.usr.usr031;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.encryption.EncryptionException;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnPswdConfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.base.CmnPswdConfModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.biz.MainCommonBiz;
import jp.groupsession.v2.man.model.base.CmnPconfEditModel;
import jp.groupsession.v2.struts.AdminAction;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;
import jp.groupsession.v2.usr.biz.UsrCommonBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] メイン 管理者設定 ユーザマネージャー（追加）画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Usr031Action extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Usr031Action.class);
    /** 写真画像名 */
    public String imageFileName__ = "";
    /** 写真画像保存名 */
    public String imageFileSaveName__ = "";

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        return true;
    }

    /**
     * <br>[機  能] アクションを実行する
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form ActionForm
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     * @see jp.co.sjts.util.struts.AbstractAction
     * @see #executeAction(org.apache.struts.action.ActionMapping,
     *                      org.apache.struts.action.ActionForm,
     *                      javax.servlet.http.HttpServletRequest,
     *                      javax.servlet.http.HttpServletResponse,
     *                      java.sql.Connection)
     */
    public ActionForward executeAction(ActionMapping map,
                                        ActionForm form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con) throws Exception {

        ActionForward forward = null;
        Usr031Form usr031Form = (Usr031Form) form;

        String processMode = NullDefault.getString(usr031Form.getProcessMode(), "");

        con.setAutoCommit(true);
        try {
            UsrCommonBiz ucBiz = new UsrCommonBiz(con, getRequestModel(req));
            if (processMode.equals("add") && !ucBiz.isNotAdminGroupExists(con)) {
                //ユーザ追加 and
                //システム管理グループ以外のグループが存在しない場合、共通メッセージ画面へ遷移
                return getNotAdminGroupErrorPage(map, req);
            }

            //管理者権限判定
            BaseUserModel umodel = getSessionUserModel(req);
            GroupDao gdao = new GroupDao(con);
            if (gdao.isBelongAdmin(umodel.getUsrsid())) {
                //管理者
                usr031Form.setAdminFlg(true);
            } else {
                //一般ユーザ
                usr031Form.setAdminFlg(false);
            }

            if (!(usr031Form.isAdminFlg())) {
                //個人情報編集区分を取得する
                MainCommonBiz manCmnBiz = new MainCommonBiz();
                CmnPconfEditModel pconfEditMdl = new CmnPconfEditModel();
                pconfEditMdl = manCmnBiz.getCpeConf(0, con);

                //個人情報修正区分が「管理者が設定する」の場合、共通メッセージ画面へ遷移
                if (pconfEditMdl.getCpePconfKbn() == GSConstMain.PCONF_EDIT_ADM) {
                    return __setCompPageParam(map, req, usr031Form);
                }
            }

            //パスワード変更の有効・無効を設定
            if (canChangePassword()) {
                usr031Form.setChangePassword(GSConst.CHANGEPASSWORD_PARMIT);
            } else {
                usr031Form.setChangePassword(GSConst.CHANGEPASSWORD_NOTPARMIT);
            }

            //モバイルプラグイン使用可否設定
            __setMobilePluginUseKbn(usr031Form, con, req);
        } finally {
            con.setAutoCommit(false);
        }

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        if (cmd.equals("usr031_kakunin")) {
            log__.debug("OKボタン押下");
            forward = __doKakunin(map, usr031Form, req, res, con, cmd);

        } else if (cmd.equals("Usr031_Back")) {
            log__.debug("戻る押下");
            forward = __doBack(map, usr031Form, req, res, con);

        } else if (cmd.equals("pictDelete")) {
            log__.debug("写真画像削除");
            forward = __doDeleteImageFile(map, usr031Form, req, res, con);

        } else if (cmd.equals("posisionSet")) {
            log__.debug("役職追加");
            forward = __doSetPosition(map, usr031Form, req, res, con);

        } else if (cmd.equals("getImageFile")) {
            log__.debug("写真画像ダウンロード");
            forward = __doGetImageFile(map, usr031Form, req, res, con);

        } else if (cmd.equals("deleteLabel")) {
            log__.debug("ラベル削除");
            forward = __doDelLabel(map, usr031Form, req, res, con, cmd);

        } else {
            log__.debug("初期処理");
            forward = __doInit(map, usr031Form, req, res, con, cmd);
        }

        return forward;
    }

    /**
     * <br>[機  能] 戻るボタン押下処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException 初期処理実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    private ActionForward __doBack(ActionMapping map,
                                    Usr031Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, IOToolsException {

        ActionForward forward = null;

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getUsr031pluginId(), getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //テンポラリディレクトリのファイル削除を行う
        Usr031Biz biz = new Usr031Biz(getRequestModel(req));
        biz.doDeleteFile(tempDir);

        if (form.getProcessMode().equals("kojn_edit")) {
            forward = map.findForward("kojn_settei");
        } else {
            forward = map.findForward("back");
        }
        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param cmd コマンド
     * @return ActionForward フォワード
     * @throws SQLException 初期処理実行時例外
     * @throws IOException バイナリファイル操作時例外
     * @throws IOToolsException 写真データ操作時例外
     * @throws EncryptionException パスワード復号時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doInit(
            ActionMapping map,
            Usr031Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con,
            String cmd)
                    throws EncryptionException, SQLException,
                    IOException, IOToolsException, TempFileException {
        imageFileName__ = "";
        imageFileSaveName__ = "";

        return __doDsp(map, form, req, res, con, cmd);
    }

    /**
     * <br>[機  能] 画面表示処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param cmd コマンド
     * @return ActionForward フォワード
     * @throws SQLException 初期処理実行時例外
     * @throws IOException バイナリファイル操作時例外
     * @throws IOToolsException 写真データ操作時例外
     * @throws EncryptionException パスワード復号時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doDsp(
            ActionMapping map,
            Usr031Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con,
            String cmd)
        throws SQLException, IOException, IOToolsException, EncryptionException, TempFileException {

        Usr031Biz biz = new Usr031Biz(getRequestModel(req));

        if (cmd.equals("add")) {
            //ユーザの選択を解除
            form.setUsr030selectusers(null);
        } else if (cmd.equals("edit")) {
            //アプリケーションのルートパス
            String appRootPath = getAppRootPath();
            //テンポラリディレクトリパス
            String rootDir = getTempPath(req);

            Usr031ParamModel paramMdl = new Usr031ParamModel();
            paramMdl.setParam(form);
            biz.setInitData(appRootPath, rootDir, paramMdl, con,
                    GroupSession.getResourceManager().getDomain(req));
            paramMdl.setFormData(form);

        }

        con.setAutoCommit(true);

        if (!NullDefault.getString(form.getUsr031ImageName(), "").equals("")
                && !NullDefault.getString(form.getUsr031ImageSaveName(), "").equals("")) {
            imageFileName__ = form.getUsr031ImageName();
            imageFileSaveName__ = form.getUsr031ImageSaveName();
        }

        CmnPswdConfDao dao = new CmnPswdConfDao(con);
        CmnPswdConfModel model = dao.select();

        //ラベル付与権限確認
        PluginConfig pConfigMain
        = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));
        if (CommonBiz.getUserPluginUse(pConfigMain) == GSConst.PLUGIN_NOT_USE) {
            form.setLabelSetPow(GSConstUser.LABEL_SET_NG);
        } else if (!getSessionUserModel(req).isAdmin()) {
            CmnLabelUsrConfDao confDao = new CmnLabelUsrConfDao(con);
            int labelSetPow = confDao.select().getLufSet();
            if (labelSetPow == GSConstUser.POW_ALL) {
                form.setLabelSetPow(GSConstUser.LABEL_SET_OK);
            } else {
                form.setLabelSetPow(GSConstUser.LABEL_SET_NG);
            }
        } else {
            form.setLabelSetPow(GSConstUser.LABEL_SET_OK);
        }

        // パスワードルール設定取得
        if (model != null) {
            form.setUsr031UidPswdKbn(model.getPwcUidPswd());
            form.setUsr031CoeKbn(model.getPwcCoe());
            int digit = model.getPwcDigit();

            if (digit == -1) {
                form.setUsr031Digit(GSConstMain.DEFAULT_DIGIT);
            } else {
                form.setUsr031Digit(digit);
            }
        }

        //画面に常に表示する値をセットする
        Usr031ParamModel paramMdl = new Usr031ParamModel();
        paramMdl.setParam(form);
        biz.setDspData(paramMdl, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
}

/**
     * <p>確認画面へ遷移Action
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param cmn コマンド
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    private ActionForward __doKakunin(ActionMapping map, Usr031Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con, String cmn)
            throws Exception {
        log__.debug("START");
        ActionForward forward = map.findForward("kakunin");

        String processMode = NullDefault.getString(form.getProcessMode(), "");

        ActionErrors errors = new ActionErrors();
        String eprefix = "useradd.";
        ActionMessage msg = null;
        UsrCommonBiz usrBiz = new UsrCommonBiz(con, getRequestModel(req));
        if (processMode.equals("add") && usrBiz.isUserCountOver(getRequestModel(req), con)) {
            //ユーザ数制限エラー
            msg = new ActionMessage("error.usercount.limit.over",
                    GroupSession.getResourceManager().getUserCountLimit(getRequestModel(req)));
            StrutsUtil.addMessage(errors, msg, eprefix + "error.usercount.limit.over");
            addErrors(req, errors);
            __doDsp(map, form, req, res, con, cmn);
            return map.getInputForward();
        }

        //写真のテンポラリディレクトリパスとファイル名をセット
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getUsr031pluginId(), getRequestModel(req));
        form.setUsr031ImageName(imageFileName__);
        form.setUsr031ImageSaveName(imageFileSaveName__);

        errors = form.validateAdd(map, getRequestModel(req), con, canChangePassword(), tempDir);
        if (errors.size() > 0) {
            addErrors(req, errors);
            __doDsp(map, form, req, res, con, cmn);
            forward = map.getInputForward();
        }

        log__.debug("END");
        return forward;
    }

    /**
     * <br>[機  能] tempディレクトリの画像を削除する
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
    private ActionForward __doDeleteImageFile(ActionMapping map,
                                               Usr031Form form,
                                               HttpServletRequest req,
                                               HttpServletResponse res,
                                               Connection con)
        throws Exception {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
            cmnBiz.getTempDir(
                    getTempPath(req), form.getUsr031pluginId(), getRequestModel(req));
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //テンポラリディレクトリのファイル削除を行う
        Usr031Biz biz = new Usr031Biz(getRequestModel(req));
        IOTools.deleteDir(tempDir);

        //画面に常に表示する値をセットする
        con.setAutoCommit(true);

        Usr031ParamModel paramMdl = new Usr031ParamModel();
        paramMdl.setParam(form);
        biz.setDspData(paramMdl, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        imageFileName__ = "";
        imageFileSaveName__ = "";
        form.setUsr031ImageName(imageFileName__);
        form.setUsr031ImageSaveName(imageFileSaveName__);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 役職追加時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con Connection
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doSetPosition(ActionMapping map,
                                               Usr031Form form,
                                               HttpServletRequest req,
                                               HttpServletResponse res,
                                               Connection con)
        throws Exception {

        con.setAutoCommit(true);

        //追加した役職をコンボで選択済みにする
        Usr031Biz biz = new Usr031Biz(getRequestModel(req));

        Usr031ParamModel paramMdl = new Usr031ParamModel();
        paramMdl.setParam(form);
        biz.setNewPosition(paramMdl, con);

        //画面に常に表示する値をセットする
        biz.setDspData(paramMdl, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] tempディレクトリの画像を読み込む
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
                                            Usr031Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        //テンポラリディレクトリパス
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getUsr031pluginId(), getRequestModel(req));

        String fullPath = IOTools.replaceFileSep(
                tempDir + imageFileSaveName__ + GSConstCommon.ENDSTR_SAVEFILE);
        TempFileUtil.downloadInline(req, res, fullPath, imageFileName__, Encoding.UTF_8);

        return null;
    }

    /**
     * <br>[機  能] モバイルプラグインが利用可能かフォームへ設定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param form フォーム
     * @param con コネクション
     * @param req リクエスト
     * @throws SQLException SQL実行時例外
     */
    private void __setMobilePluginUseKbn(Usr031Form form, Connection con, HttpServletRequest req)
        throws SQLException {

        //プラグイン設定を取得する
        con.setAutoCommit(true);
        PluginConfig pconfig = getPluginConfigForMain(getPluginConfig(req), con);
        CommonBiz cmnBiz = new CommonBiz();
        con.setAutoCommit(false);

        //モバイル機能が利用可能か判定
        if (cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_MOBILE, pconfig)) {
            form.setUsr031UsiMblUse(GSConstMain.PLUGIN_USE);
        } else {
            form.setUsr031UsiMblUse(GSConstMain.PLUGIN_NOT_USE);
        }
    }

    /**
     * <br>[機  能] 削除(ラベル)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param cmd コマンド
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDelLabel(
        ActionMapping map,
        Usr031Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con,
        String cmd) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setUsrLabel(
                cmnBiz.getDeleteMember(new String[] {form.getDelLabel()},
                                    form.getUsrLabel()));

        return  __doDsp(map, form, req, res, con, cmd);
    }

    /**
     * <br>[機  能] エラー画面
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @return ActionForward フォワード
     */
    private ActionForward  __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Usr031Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        urlForward = map.findForward("main");
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage();
        String textDelWrite = gsMsg.getMessage("cmn.modify.personalinfo");
        String textDel = gsMsg.getMessage("cmn.edit");

        //メッセージセット
        String msgState = "error.edit.power.user";
        cmn999Form.setMessage(msgRes.getMessage(msgState,
                textDelWrite,
                textDel));

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
}