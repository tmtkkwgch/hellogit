package jp.groupsession.v2.man.man280;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.man.man120.Man120Biz;
import jp.groupsession.v2.struts.AdminAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] メイン 管理者設定 プラグイン使用制限画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man280Action extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man280Action.class);

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
        Man280Form thisForm = (Man280Form) form;

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);
        if (cmd.equals("confirm")) {
            //ＯＫボタンクリック
            forward = __doConfirm(map, thisForm, req, res, con);
        } else if (cmd.equals("backPluginList")) {
            //戻るボタンクリック
            forward = __doBack(map, thisForm, req, res, con);
        } else if (cmd.equals("changeGrp")) {
            //グループ変更
            forward = __doInit(map, thisForm, req, res, con);
        } else if (cmd.equals("addMember")) {
            //左矢印(ユーザ追加)ボタンクリック
            forward = __doAddUser(map, thisForm, req, res, con);
        } else if (cmd.equals("removeMember")) {
            //右矢印(ユーザ削除)ボタンクリック
            forward = __doDelUser(map, thisForm, req, res, con);
        } else if (cmd.equals("addAdmin")) {
            //左矢印(管理者追加)ボタンクリック
            forward = __doAddAdmin(map, thisForm, req, res, con);
        } else if (cmd.equals("removeAdmin")) {
            //右矢印(管理者削除)ボタンクリック
            forward = __doDelAdmin(map, thisForm, req, res, con);
        } else if (cmd.equals("getImageFile")) {
            //画像ダウンロード"
            forward = __doGetImageFile(map, thisForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException 実行例外
     */
    private ActionForward __doInit(ActionMapping map,
        Man280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws SQLException {

        con.setAutoCommit(true);
        Man280ParamModel paramMdl = new Man280ParamModel();
        paramMdl.setParam(form);
        Man280Biz biz = new Man280Biz();
        biz.setInitData(paramMdl, con, getPluginConfig(req), getRequestModel(req));
        paramMdl.setFormData(form);
        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 戻るボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException 実行例外
     */
    private ActionForward __doBack(ActionMapping map,
        Man280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws SQLException {
        String backId = NullDefault.getString(form.getMan280backId(), "");

        if (backId.equals("man121")) {
            return map.findForward("man121");
        } else {
            return map.findForward("man120");
        }
    }

    /**
     * <br>[機  能] ＯＫボタンクリック時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doConfirm(ActionMapping map,
        Man280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con)
        throws Exception {

        ActionErrors errors = new ActionErrors();
        errors = form.validateCheck(getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        saveToken(req);
        return map.findForward("confirm_man280");
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
    private ActionForward __doAddUser(
        ActionMapping map,
        Man280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        Man280Biz biz = new Man280Biz();
        form.setMan280memberSid(
                biz.getAddMember(form.getMan280SelectRightUser(), form.getMan280memberSid()));

        return  __doInit(map, form, req, res, con);
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
    private ActionForward __doDelUser(
        ActionMapping map,
        Man280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        Man280Biz biz = new Man280Biz();
        form.setMan280memberSid(
                biz.getDeleteMember(form.getMan280SelectLeftUser(),
                        form.getMan280memberSid(), false));

        return __doInit(map, form, req, res, con);
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
    private ActionForward __doAddAdmin(
        ActionMapping map,
        Man280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        Man280Biz biz = new Man280Biz();
        form.setMan280AdminSid(
                biz.getAddMember(form.getMan280SelectRightAdmin(), form.getMan280AdminSid()));

        return  __doInit(map, form, req, res, con);
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
    private ActionForward __doDelAdmin(
        ActionMapping map,
        Man280Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        Man280Biz biz = new Man280Biz();
        form.setMan280AdminSid(
                biz.getDeleteMember(form.getMan280SelectLeftAdmin(),
                        form.getMan280AdminSid(), true));

        return __doInit(map, form, req, res, con);
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
                                            Man280Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        Man120Biz biz120 = new Man120Biz();
        long binSid = biz120.getPluginImageSid(getPluginConfig(req), form.getMan120imgPluginId());

        CommonBiz cmnBiz = new CommonBiz();
        CmnBinfModel cbMdl = null;

        cbMdl = cmnBiz.getBinInfo(con, binSid,
                GroupSession.getResourceManager().getDomain(req));

        if (cbMdl != null) {
            JDBCUtil.closeConnectionAndNull(con);

            //ファイルをダウンロードする
            TempFileUtil.downloadInline(req, res, cbMdl, getAppRootPath(),
                                        Encoding.UTF_8);
        }
        return null;
    }
}
