package jp.groupsession.v2.bmk.bmk110;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.bmk.AbstractBookmarkAdminAction;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.dao.BmkAconfDao;
import jp.groupsession.v2.bmk.model.BmkAconfModel;
import jp.groupsession.v2.cmn.biz.CommonBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 管理者設定 権限設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk110Action extends AbstractBookmarkAdminAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk110Action.class);

    /**
     * <br>アクション実行
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
        ActionForward forward = null;

        Bmk110Form bmkForm = (Bmk110Form) form;
        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        if (cmd.equals("bmk110kakunin")) {
            //OKボタンクリック
            forward = __doKakunin(map, bmkForm, req, res, con);
        } else if (cmd.equals("bmk110back")) {
            //戻るボタンクリック
            forward = map.findForward("bmk100");
        } else if (cmd.equals("bmk110knback")) {
            //確認画面からの戻り
            forward = __doInitBack(map, bmkForm, req, res, con);
        } else if (cmd.equals("changeGrp")) {
            log__.debug("グループコンボ変更");
            forward = __doChangeGrp(map, bmkForm, req, res, con);
        } else if (cmd.equals("removeUser")) {
            log__.debug("削除(左矢印)ボタン押下");
            forward = __doLeftUser(map, bmkForm, req, res, con);
        } else if (cmd.equals("addUser")) {
            log__.debug("追加(右矢印)ボタン押下");
            forward = __doRightUser(map, bmkForm, req, res, con);
        } else if (cmd.equals("removeGroup")) {
            log__.debug("削除(左矢印)ボタン押下");
            forward = __doLeftGroup(map, bmkForm, req, res, con);
        } else if (cmd.equals("addGroup")) {
            log__.debug("追加(右矢印)ボタン押下");
            forward = __doRightGroup(map, bmkForm, req, res, con);
        } else if (cmd.equals("chgGrpEditKbn")) {
            forward = __doRedraw(map, bmkForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, bmkForm, req, res, con);
        }
        return forward;
    }

    /**
     * <br>初期表処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Bmk110Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("初期表示");

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk110Biz biz = new Bmk110Biz(getRequestModel(req));

        Bmk110ParamModel paramMdl = new Bmk110ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>再描画処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doRedraw(ActionMapping map, Bmk110Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("初期表示");

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();
        Bmk110Biz biz = new Bmk110Biz(getRequestModel(req));

        con.setAutoCommit(true);
        if (form.getBmk110PubEditKbn() == GSConstBookmark.EDIT_POW_USER) {

            Bmk110ParamModel paramMdl = new Bmk110ParamModel();
            paramMdl.setParam(form);
            biz.setInitDataUser(paramMdl, sessionUserSid, con);
            paramMdl.setFormData(form);

        } else if (form.getBmk110PubEditKbn() == GSConstBookmark.EDIT_POW_GROUP) {

            Bmk110ParamModel paramMdl = new Bmk110ParamModel();
            paramMdl.setParam(form);
            biz.setInitDataGroup(paramMdl, sessionUserSid, con);
            paramMdl.setFormData(form);
        }
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>確認処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doKakunin(ActionMapping map, Bmk110Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {
        log__.debug("確認");

        //入力チェック
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }
        saveToken(req);
        return map.findForward("bmk110kn");
    }

    /**
     * <br>確認画面からの戻り処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInitBack(ActionMapping map, Bmk110Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("初期表示");

        con.setAutoCommit(true);

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();
        Bmk110Biz biz = new Bmk110Biz(getRequestModel(req));

        BmkAconfDao dao = new BmkAconfDao(con);
        BmkAconfModel model = dao.selectAConf();

        Bmk110ParamModel paramMdl = new Bmk110ParamModel();
        paramMdl.setParam(form);
        biz.setDspData(paramMdl, model);
        paramMdl.setFormData(form);

        if (form.getBmk110PubEditKbn() == GSConstBookmark.EDIT_POW_USER) {

            paramMdl = new Bmk110ParamModel();
            paramMdl.setParam(form);
            biz.setInitDataUser(paramMdl, sessionUserSid, con);
            paramMdl.setFormData(form);

        } else if (form.getBmk110PubEditKbn() == GSConstBookmark.EDIT_POW_GROUP) {

            paramMdl = new Bmk110ParamModel();
            paramMdl.setParam(form);
            biz.setInitDataGroup(paramMdl, sessionUserSid, con);
            paramMdl.setFormData(form);
        }

        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>グループ変更処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doChangeGrp(ActionMapping map, Bmk110Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("初期表示");

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk110Biz biz = new Bmk110Biz(getRequestModel(req));

        Bmk110ParamModel paramMdl = new Bmk110ParamModel();
        paramMdl.setParam(form);
        biz.setInitDataUser(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }
    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
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
    private ActionForward __doLeftUser(
        ActionMapping map,
        Bmk110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setBmk110UserSid(
                cmnBiz.getDeleteMember(form.getBmk110SelectRightUser(), form.getBmk110UserSid()));

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk110Biz biz = new Bmk110Biz(getRequestModel(req));

        Bmk110ParamModel paramMdl = new Bmk110ParamModel();
        paramMdl.setParam(form);
        biz.setInitDataUser(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
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
    private ActionForward __doRightUser(
        ActionMapping map,
        Bmk110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setBmk110UserSid(
                cmnBiz.getAddMember(form.getBmk110SelectLeftUser(), form.getBmk110UserSid()));

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk110Biz biz = new Bmk110Biz(getRequestModel(req));

        Bmk110ParamModel paramMdl = new Bmk110ParamModel();
        paramMdl.setParam(form);
        biz.setInitDataUser(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
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
    private ActionForward __doLeftGroup(
        ActionMapping map,
        Bmk110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setBmk110GrpSid(
                cmnBiz.getDeleteMember(form.getBmk110SelectRightGroup(), form.getBmk110GrpSid()));

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk110Biz biz = new Bmk110Biz(getRequestModel(req));

        Bmk110ParamModel paramMdl = new Bmk110ParamModel();
        paramMdl.setParam(form);
        biz.setInitDataGroup(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
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
    private ActionForward __doRightGroup(
        ActionMapping map,
        Bmk110Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        form.setBmk110GrpSid(
                cmnBiz.getAddMember(form.getBmk110SelectLeftGroup(), form.getBmk110GrpSid()));

        //セッションユーザSIDを取得する。
        int sessionUserSid = getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk110Biz biz = new Bmk110Biz(getRequestModel(req));

        Bmk110ParamModel paramMdl = new Bmk110ParamModel();
        paramMdl.setParam(form);
        biz.setInitDataGroup(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }
}
