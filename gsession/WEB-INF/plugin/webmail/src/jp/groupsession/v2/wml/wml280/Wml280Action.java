package jp.groupsession.v2.wml.wml280;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.GSException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.wml.AbstractWebmailAction;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.wml270.Wml270Form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] WEBメール 送信先リスト登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml280Action extends AbstractWebmailAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml280Action.class);

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        return ((Wml280Form) form).getWmlAccountMode() == 1;
    }

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
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        log__.debug("START");

        ActionForward forward = null;
        Wml280Form thisForm = (Wml280Form) form;

        //編集可能な送信先リストかを確認
        int wdlSid = thisForm.getWmlEditDestList();
        if (thisForm.getWmlAccountMode() == 1 && wdlSid > 0) {
            WmlBiz wmlBiz = new WmlBiz();
            if (!wmlBiz.canUseDestlist(con, wdlSid, getSessionUserSid(req))) {
                return this.getAuthErrorPage(map, req);
            }
        }

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        if (cmd.equals("confirm")) {
            //OKボタンクリック
            forward = __doOK(map, thisForm, req, res, con);

        } else if (cmd.equals("beforePage")) {
            //戻るボタンクリック
            forward = map.findForward("backDestList");

        } else if (cmd.equals("deleteDestList")) {
            //削除ボタンクリック
            forward = __doDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteDestListComp")) {
            //削除確認画面からの遷移
            forward = __doDeleteComp(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteDestUser")) {
            //削除ボタン(送信先)クリック
            forward = __doDelDestUser(map, thisForm, req, res, con);

        } else if (cmd.equals("addAuthEdit")) {
            //追加(アクセス権限 編集ユーザ)ボタンクリック
            forward = __doAddAuthEdit(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteAuthEdit")) {
            //削除(アクセス権限 編集ユーザ)ボタンクリック
            forward = __doDelAuthEdit(map, thisForm, req, res, con);

        } else if (cmd.equals("addAuthRead")) {
            //追加(アクセス権限 閲覧ユーザ)ボタンクリック
            forward = __doAddAuthRead(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteAuthRead")) {
            //削除(アクセス権限 閲覧ユーザ)ボタンクリック
            forward = __doDelAuthRead(map, thisForm, req, res, con);

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
    private ActionForward __doInit(ActionMapping map, Wml280Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        if (form.getWml280initFlg() == GSConstWebmail.DSP_FIRST) {
            IOTools.deleteDir(_getWebmailTempDir(req));
        }

        con.setAutoCommit(true);
        Wml280ParamModel paramMdl = new Wml280ParamModel();
        paramMdl.setParam(form);
        Wml280Biz biz = new Wml280Biz();
        biz.setInitData(con, paramMdl, getRequestModel(req));
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

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
    private ActionForward __doOK(ActionMapping map, Wml280Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        // トランザクショントークン設定
        saveToken(req);

        //入力チェック
        ActionErrors errors = form.validateCheck(getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        return map.findForward("confirm");
    }

    /**
     * <br>[機  能] 削除(送信先)ボタン押下時処理を行う
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
    private ActionForward __doDelDestUser(
        ActionMapping map,
        Wml280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //入力チェックを行う
        ActionErrors errors = form.validateDeleteDestCheck(getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
        } else {
            Wml280ParamModel paramMdl = new Wml280ParamModel();
            paramMdl.setParam(form);
            Wml280Biz biz = new Wml280Biz();
            biz.deleteDestUser(paramMdl);
            paramMdl.setFormData(form);
        }

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(アクセス権限 編集ユーザ)ボタン押下時処理を行う
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
    private ActionForward __doAddAuthEdit(
        ActionMapping map,
        Wml280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        Wml280Biz biz = new Wml280Biz();
        form.setWml280accessFull(
                biz.getAddMember(form.getWml280accessFull(),
                                    form.getWml280accessNoSelect()));
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(アクセス権限 編集ユーザ)ボタン押下時処理を行う
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
    private ActionForward __doDelAuthEdit(
        ActionMapping map,
        Wml280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setWml280accessFull(
                cmnBiz.getDeleteMember(form.getWml280accessFullSelect(),
                                    form.getWml280accessFull()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(アクセス権限 閲覧ユーザ)ボタン押下時処理を行う
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
    private ActionForward __doAddAuthRead(
        ActionMapping map,
        Wml280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        Wml280Biz biz = new Wml280Biz();
        form.setWml280accessRead(
                biz.getAddMember(form.getWml280accessRead(),
                                    form.getWml280accessNoSelect()));
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(アクセス権限 閲覧ユーザ)ボタン押下時処理を行う
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
    private ActionForward __doDelAuthRead(
        ActionMapping map,
        Wml280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setWml280accessRead(
                cmnBiz.getDeleteMember(form.getWml280accessReadSelect(),
                                    form.getWml280accessRead()));

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
        Wml280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

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
        Wml280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, GSException {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //送付先リストを削除する
        boolean commit = false;
        try {
            Wml280ParamModel paramMdl = new Wml280ParamModel();
            paramMdl.setParam(form);
            Wml280Biz biz = new Wml280Biz();
            biz.deleteDestList(con, paramMdl);
            paramMdl.setFormData(form);
            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.error("送付先リストの削除に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        //ログ出力
        WmlBiz wmlBiz = new WmlBiz();
        wmlBiz.outPutLog(map, getRequestModel(req), con,
                getInterMessage(req, "cmn.delete"), GSConstLog.LEVEL_INFO,
                "");

        //削除完了画面を表示
        return __setKanryoDsp(map, form, req);
    }

    /**
     * <br>[機  能] 削除確認画面のパラメータセット
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     */
    private ActionForward __setKakuninDsp(
        ActionMapping map,
        Wml280Form form,
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
        cmn999Form.setUrlOK(forward.getPath() + "?" + GSConst.P_CMD + "=deleteDestListComp");

        //メッセージ
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        String msg = gsMsg.getMessage("cmn.confirm.msg.delete",
                                                        new String[] {gsMsg.getMessage("wml.262")});
        cmn999Form.setMessage(msg);

        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 削除完了画面のパラメータセット
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Wml280Form form,
        HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("backDestList");
        cmn999Form.setUrlOK(forwardOk.getPath());

        MessageResources msgRes = getResources(req);
        //削除完了
        GsMessage gsMsg = new GsMessage();
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object",
                                gsMsg.getMessage(req, "wml.262")));

        ((Wml270Form) form).setHiddenParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
}
