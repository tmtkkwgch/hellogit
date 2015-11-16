package jp.groupsession.v2.fil.fil210;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.fil.AbstractFileAdminAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 管理者設定 基本設定画面のアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil210Action extends AbstractFileAdminAction {


    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil210Action.class);

    /**
     *<br>[機  能] アクションを実行する
     *<br>[解  説]
     *<br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
        HttpServletRequest req, HttpServletResponse res, Connection con)
        throws Exception {

        log__.debug("fil210Action START");

        ActionForward forward = null;
        Fil210Form thisForm = (Fil210Form) form;

        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("fil210back")) {
            //戻るボタンクリック
            forward = __doBack(map, thisForm);

        } else if (cmd.equals("fil210ok")) {
            //OKボタンクリック

            forward = __doRegister(map, thisForm, req, res, con);
        } else if (cmd.equals("fil210AddUser")) {
            //ユーザ追加ボタンクリック
            forward = __doAddUser(map, thisForm, req, res, con);

        } else if (cmd.equals("fil210DelUser")) {
            //ユーザ削除ボタンクリック
            forward = __doDeleteUser(map, thisForm, req, res, con);

        } else if (cmd.equals("fil210AddGroup")) {
            //グループ追加ボタンクリック
            forward = __doAddGroup(map, thisForm, req, res, con);

        } else if (cmd.equals("fil210DelGroup")) {
            //グループ削除ボタンクリック
            forward = __doDeleteGroup(map, thisForm, req, res, con);

        } else {
            //初期表示
            forward = __doInit(map, thisForm, req, res, con);
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
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Fil210Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException {
        con.setAutoCommit(true);
        Fil210Biz biz = new Fil210Biz(con, getRequestModel(req));
        Fil210ParamModel paramMdl = new Fil210ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, req);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] OKボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doRegister(ActionMapping map,
                                    Fil210Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException {

        //入力チェック
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        saveToken(req);

        return map.findForward("fil210kn");
    }
    /**
     * <br>[機  能] 遷移元画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @return ActionForward フォワード
     */
    private ActionForward __doBack(ActionMapping map, Fil210Form form) {

        ActionForward forward = null;

        forward = map.findForward("fil200");
        return forward;
    }

    /**
     * <br>[機  能] ユーザ追加ボタン押下時処理を行う
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
    private ActionForward __doAddUser(
        ActionMapping map,
        Fil210Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();

        form.setFil210SvUsers(
                cmnBiz.getAddMember(form.getFil210RightUsers(), form.getFil210SvUsers()));
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ユーザ削除ボタン押下時処理を行う
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
    private ActionForward __doDeleteUser(
        ActionMapping map,
        Fil210Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setFil210SvUsers(
                cmnBiz.getDeleteMember(form.getFil210LeftUsers(),
                                    form.getFil210SvUsers()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ユーザ追加ボタン押下時処理を行う
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
    private ActionForward __doAddGroup(
        ActionMapping map,
        Fil210Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();

        form.setFil210SvGroups(
                cmnBiz.getAddMember(form.getFil210RightGroups(), form.getFil210SvGroups()));
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ユーザ削除ボタン押下時処理を行う
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
    private ActionForward __doDeleteGroup(
        ActionMapping map,
        Fil210Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setFil210SvGroups(
                cmnBiz.getDeleteMember(form.getFil210LeftGroups(),
                                    form.getFil210SvGroups()));

        return  __doInit(map, form, req, res, con);
    }

}

