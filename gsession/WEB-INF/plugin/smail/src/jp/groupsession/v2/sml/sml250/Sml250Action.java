package jp.groupsession.v2.sml.sml250;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.json.JSONObject;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSException;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sml.AbstractSmlAction;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ショートメール アカウント登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml250Action extends AbstractSmlAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml250Action.class);

    /**
     * <br>[機  能] アクション実行
     * <br>[解  説] コントローラの役割を担います
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return アクションフォーム
     */
    public ActionForward executeSmail(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        log__.debug("START");

        ActionForward forward = null;
        Sml250Form thisForm = (Sml250Form) form;
        thisForm.setSml010adminUser(_checkAuth(map, req, con));
        SmlCommonBiz smlCmnBiz = new SmlCommonBiz(con, getRequestModel(req));
        int editKbn = smlCmnBiz.getSmailAdminConf(
                getRequestModel(req).getSmodel().getUsrsid(), con).getSmaAcntMake();
        log__.debug("editKbn = " + editKbn);

        boolean auth = _checkAuth(map, req, con);
        //管理者権限のみアカウント設定が可能かどうか
        if (editKbn == GSConstSmail.KANRI_USER_ONLY) {
            if (!auth && thisForm.getSmlAccountSid() <= 0) {
                return getAuthErrorPage(map, req);
            }
            if (thisForm.getSmlCmdMode() == GSConstSmail.CMDMODE_ADD
                    && thisForm.getSmlAccountMode() != GSConstSmail.ACCOUNTMODE_COMMON) {
                return getAuthErrorPage(map, req);
            }
        }

        if (!auth) {
            //一般ユーザが管理者設定画面に遷移したらNG
            if (thisForm.getSmlAccountMode() == GSConstSmail.ACCOUNTMODE_COMMON) {
                return getAuthErrorPage(map, req);
            }
            //編集可能かを判定
            if (thisForm.getSmlAccountSid() > 0) {
                if (!smlCmnBiz.canEditAccount(con, thisForm.getSmlAccountSid(),
                                        getSessionUserSid(req))) {
                    return map.findForward("gf_msg");
                }
            }

            //管理者権限のみアカウント設定が可能かどうか
            if (editKbn == GSConstSmail.KANRI_USER_ONLY) {
                thisForm.setSml250CanDelFlg(GSConstSmail.ACCOUNT_DELETE_NG);
            }
        }

        //在席管理プラグインが有効チェック
        Sml250Biz biz = new Sml250Biz();
        Sml250ParamModel paramMdl = new Sml250ParamModel();
        paramMdl.setParam(thisForm);
        biz.setCanUsePluginFlg(paramMdl, con, getPluginConfigForMain(getPluginConfig(req), con,
                                                                    getSessionUserSid(req)));
        paramMdl.setFormData(form);

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        if (cmd.equals("confirm")) {
            //OKボタンクリック
            forward = __doOK(map, thisForm, req, res, con);

        } else if (cmd.equals("beforePage")) {
            //戻るボタンクリック
            if (thisForm.getSmlAccountMode() == GSConstSmail.SAC_TYPE_USER) {
                forward = map.findForward("accountList");
            } else {
                forward = map.findForward("userAccountList");
            }

        } else if (cmd.equals("deleteAccount")) {
            //削除ボタンクリック
            forward = __doDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteAccountComp")) {
            //削除確認画面からの遷移
            forward = __doDeleteComp(map, thisForm, req, res, con);

        } else if (cmd.equals("addGroup")) {
//            //追加(使用者 グループ)ボタンクリック
//            forward = __doAddUserkbnGroup(map, thisForm, req, res, con);
//
//        } else if (cmd.equals("deleteGroup")) {
//            //削除(使用者 グループ)ボタンクリック
//            forward = __doDelUserkbnGroup(map, thisForm, req, res, con);

        } else if (cmd.equals("addUser")) {
            //追加(使用者 ユーザ)ボタンクリック
            forward = __doAddUserkbnUser(map, thisForm, req, res, con);

        } else if (cmd.equals("delUser")) {
            //削除(使用者 ユーザ)ボタンクリック
            __doDelUserCheck(map, thisForm, req, res, con);
        } else if (cmd.equals("deleteUser")) {
            //削除(使用者 ユーザ)ボタンクリック
            forward = __doDelUserkbnUser(map, thisForm, req, res, con);
        } else if (cmd.equals("deleteMnb")) {
            log__.debug("左矢印");
            forward = __doDeleteMnb(map, thisForm, req, res, con);

        } else if (cmd.equals("addMnb")) {
            log__.debug("右矢印");
            forward = __doAddMnb(map, thisForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, thisForm, req, res, con);
        }
        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form フォーム
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Sml250Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        con.setAutoCommit(true);
        Sml250ParamModel paramMdl = new Sml250ParamModel();
        paramMdl.setParam(form);
        Sml250Biz biz = new Sml250Biz();
        biz.setInitData(con, paramMdl, getRequestModel(req));
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] OKボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form フォーム
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doOK(ActionMapping map, Sml250Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        // トランザクショントークン設定
        this.saveToken(req);

        //入力チェック
        ActionErrors errors = form.validateCheck250(req, con);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        return map.findForward("confirm");
    }

//    /**
//     * <br>[機  能] 追加(使用者 グループ)ボタン押下時処理を行う
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param map アクションマッピング
//     * @param form アクションフォーム
//     * @param req リクエスト
//     * @param res レスポンス
//     * @param con コネクション
//     * @throws Exception 実行時例外
//     * @return ActionForward
//     */
//    private ActionForward __doAddUserkbnGroup(
//        ActionMapping map,
//        Sml250Form form,
//        HttpServletRequest req,
//        HttpServletResponse res,
//        Connection con) throws Exception {
//
//        CommonBiz cmnBiz = new CommonBiz();
////        form.setSml250userKbnGroup(
////                cmnBiz.getAddMember(form.getSml250userKbnGroup(),
////                                    form.getSml250userKbnGroupNoSelect()));
//        return  __doInit(map, form, req, res, con);
//    }
//
//    /**
//     * <br>[機  能] 削除(使用者 グループ)ボタン押下時処理を行う
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param map アクションマッピング
//     * @param form アクションフォーム
//     * @param req リクエスト
//     * @param res レスポンス
//     * @param con コネクション
//     * @throws Exception 実行時例外
//     * @return ActionForward
//     */
//    private ActionForward __doDelUserkbnGroup(
//        ActionMapping map,
//        Sml250Form form,
//        HttpServletRequest req,
//        HttpServletResponse res,
//        Connection con) throws Exception {
//
//        CommonBiz cmnBiz = new CommonBiz();
////        form.setSml250userKbnGroup(
////                cmnBiz.getDeleteMember(form.getSml250userKbnGroupSelect(),
////                                    form.getSml250userKbnGroup()));
//
//        return  __doInit(map, form, req, res, con);
//    }

    /**
     * <br>[機  能] 追加(使用者 ユーザ)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doAddUserkbnUser(
        ActionMapping map,
        Sml250Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        Sml250Biz biz = new Sml250Biz();
        form.setSml250userKbnUser(
                biz.getAddMember(form.getSml250userKbnUser(),
                                    form.getSml250userKbnUserNoSelect()));
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(使用者 ユーザ)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDelUserkbnUser(
        ActionMapping map,
        Sml250Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        Sml250Biz biz = new Sml250Biz();
        form.setSml250userKbnUser(
                biz.getDeleteMember(form.getSml250userKbnUserSelect(),
                                    form.getSml250userKbnUser()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除ボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doDelete(
        ActionMapping map,
        Sml250Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

//        //削除チェック
//        ActionErrors errors = form.deleteCheck(con);
//        if (!errors.isEmpty()) {
//            addErrors(req, errors);
//            return map.getInputForward();
//        }

        // トランザクショントークン設定
        saveToken(req);

        //削除確認画面を表示
        return __setKakuninDsp(map, form, req, con);
    }

    /**
     * <br>[機  能] 削除処理を行う(削除実行)
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws GSException GS用汎実行例外
     * @return ActionForward
     */
    private ActionForward __doDeleteComp(
        ActionMapping map,
        Sml250Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, GSException {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //アカウントを削除する
        Sml250ParamModel paramMdl = new Sml250ParamModel();
        paramMdl.setParam(form);
        Sml250Biz biz = new Sml250Biz();
        biz.deleteAccount(con, paramMdl, userSid);
        paramMdl.setFormData(form);

        //ログ出力
        SmlCommonBiz smlBiz = new SmlCommonBiz(con, getRequestModel(req));
        smlBiz.outPutLog(map, getRequestModel(req),
                getInterMessage(req, "cmn.delete"), GSConstLog.LEVEL_INFO,
                "");

        //削除完了画面を表示
        return __setKanryoDsp(map, form, req);
    }

    /**
     * [機  能] 削除確認画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     */
    private ActionForward __setKakuninDsp(
        ActionMapping map,
        Sml250Form form,
        HttpServletRequest req,
        Connection con) throws SQLException {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //キャンセルボタンクリック時遷移先
        ActionForward forward = map.findForward("mine");
        cmn999Form.setUrlCancel(forward.getPath());

        //OKボタンクリック時遷移先
        cmn999Form.setUrlOK(forward.getPath() + "?" + GSConst.P_CMD + "=deleteAccountComp");

        //メッセージ
        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "wml.130");
        cmn999Form.setMessage(msg);

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * [機  能] 削除完了画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Sml250Form form,
        HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("delete");

        if (form.getSmlAccountMode() != GSConstSmail.ACCOUNTMODE_COMMON) {
            forwardOk = map.findForward("smlUserAccountList");
        }
        cmn999Form.setUrlOK(forwardOk.getPath());

        MessageResources msgRes = getResources(req);
        //削除完了
        GsMessage gsMsg = new GsMessage();
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object",
                                gsMsg.getMessage(req, "wml.102")));

        form.setHiddenParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
//
//    /**
//     * <br>[機  能] アカウント登録権限エラー画面遷移時のパラメータセット
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param map マッピング
//     * @param req リクエスト
//     * @param form アクションフォーム
//     */
//    private void __setAcntErrPageParam(
//        ActionMapping map,
//        HttpServletRequest req,
//        Sml250Form form) {
//
//        Cmn999Form cmn999Form = new Cmn999Form();
//        ActionForward urlForward = null;
//
//        cmn999Form.setType(Cmn999Form.TYPE_OK);
//        MessageResources msgRes = getResources(req);
//        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
//        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
//
//        urlForward = map.findForward("mailList");
//
//        ((AbstractSmlForm) form).setHiddenParam(cmn999Form);
//        cmn999Form.setUrlOK(urlForward.getPath());
//
//        //メッセージセット
//        String msgState = null;
//        msgState = "add.touroku.wmluser";
//
//        cmn999Form.setMessage(msgRes.getMessage(msgState));
//        //画面パラメータをセット
//        form.setHiddenParam(cmn999Form);
//        req.setAttribute("cmn999Form", cmn999Form);
//    }

    /**
     * <br>[機  能] 削除ユーザ確認
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     */
    private void __doDelUserCheck(ActionMapping map,
                                                   Sml250Form form,
                                                   HttpServletRequest req,
                                                   HttpServletResponse res,
                                                   Connection con)
        throws Exception {

        con.setAutoCommit(true);
        try {
            //削除可能チェック
            Sml250ParamModel paramMdl = new Sml250ParamModel();
            paramMdl.setParam(form);
            Sml250Biz biz = new Sml250Biz();
            ActionErrors errors = biz.checkCanDelUsr(paramMdl, getRequestModel(req), con);
            paramMdl.setFormData(form);

            if (!errors.isEmpty()) {
                addErrors(req, errors);
                form.setErrorsList(__getJsonErrorMsg(req, errors));
            }

            JSONObject jsonData = new JSONObject();
            jsonData = JSONObject.fromObject(form);

            PrintWriter out = null;

            try {
                res.setHeader("Cache-Control", "no-cache");
                res.setContentType("application/json;charset=UTF-8");
                out = res.getWriter();
                out.print(jsonData);
                out.flush();
            } catch (Exception e) {
                log__.error("jsonデータ送信失敗(削除ユーザチェック)");
                throw e;
            } finally {
                if (out != null) {
                    out.close();
                }
            }

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        }
    }

    /**
     * <br>jsonエラーメッセージ作成
     * @param req リクエスト
     * @param errors エラーメッセージ
     * @throws Exception 実行例外
     * @return errorResult jsonエラーメッセージ
     */
    private List<String> __getJsonErrorMsg(
        HttpServletRequest req, ActionErrors errors) throws Exception {

        @SuppressWarnings("all")
        Iterator iterator = errors.get();

        List<String> errorList = new ArrayList<String>();
        while (iterator.hasNext()) {
            ActionMessage error = (ActionMessage) iterator.next();
            errorList.add(getResources(req).getMessage(error.getKey(), error.getValues()));
        }
        return errorList;
    }


    /**
     * <br>[機  能] 左矢印クリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    private ActionForward __doDeleteMnb(
        ActionMapping map,
        Sml250Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //コンボで選択中のメンバーをメンバーリストから削除する
        RequestModel reqMdl = getRequestModel(req);
        Sml250ParamModel paramMdl = new Sml250ParamModel();
        paramMdl.setParam(form);
        Sml250Biz biz = new Sml250Biz();
        biz.deleteMnb(paramMdl);
        biz.setInitData(con, paramMdl, reqMdl);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 右矢印クリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    private ActionForward __doAddMnb(
        ActionMapping map,
        Sml250Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //追加用メンバーコンボで選択中のメンバーをメンバーリストに追加する
        RequestModel reqMdl = getRequestModel(req);
        Sml250ParamModel paramMdl = new Sml250ParamModel();
        paramMdl.setParam(form);
        Sml250Biz biz = new Sml250Biz();
        biz.addMnb(paramMdl);
        biz.setInitData(con, paramMdl, reqMdl);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }


}

