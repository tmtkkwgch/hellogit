package jp.groupsession.v2.tcd.tcd060;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.tcd.AbstractTimecardAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * <br>[機  能] タイムカード 管理者設定 時間帯設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd060Action extends AbstractTimecardAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd060Action.class);

    /** 管理者のみ使用可能
     * @see jp.groupsession.v2.struts.AbstractGsAction
     * #canNotAdminAccess(
     * javax.servlet.http.HttpServletRequest,
     * org.apache.struts.action.ActionForm)
     * @param req リクエスト
     * @param form フォーム
     * @return boolean true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        return false;
    }

    /**
     *<br>[機  能]tcd060Action
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

        ActionForward forward = null;

        /*管理者権限チェック*/

        //コマンド取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);
        cmd = cmd.trim();

        Tcd060Form tcForm = (Tcd060Form) form;

        if (cmd.equals("tcd060_back")) {
            //戻る
            forward = map.findForward("back");
        } else if (cmd.equals("tcd060_add")) {
            //追加ボタン
            forward = __doAdd(map, tcForm, req, res, con);
        } else if (cmd.equals("tcd060_edit")) {
            //編集リンク
            forward = __doEdit(map, tcForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, tcForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Tcd060Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);

        RequestModel reqMdl = getRequestModel(req);
        Tcd060ParamModel paramMdl = new Tcd060ParamModel();
        paramMdl.setParam(form);
        Tcd060Biz biz = new Tcd060Biz(reqMdl);
        biz.setInitData(paramMdl, con);
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 追加ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doAdd(ActionMapping map, Tcd060Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) throws Exception {

        return map.findForward("add");
    }

    /**
     * <br>[機  能] 編集リンククリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doEdit(ActionMapping map, Tcd060Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) throws Exception {

        return map.findForward("edit");
    }
}
