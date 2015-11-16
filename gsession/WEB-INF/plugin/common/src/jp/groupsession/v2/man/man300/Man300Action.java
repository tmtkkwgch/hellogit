package jp.groupsession.v2.man.man300;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.AdminAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] メイン インフォメーション 管理者設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man300Action extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man300Action.class);

    /**
     * <br>[機  能] アクション実行
     * <br>[解  説]
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

        log__.debug("START_MAN300");
        //更新処理が無いのでAutoCommitはtrue
        con.setAutoCommit(true);

        ActionForward forward = null;
        Man300Form thisForm = (Man300Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        log__.debug("CMD==>" + cmd);
        if (cmd.equals("300_ok")) {
            //インフォメーション登録確認
            forward = __doOk(map, thisForm, req, res, con);
        } else if (cmd.equals("300_back")) {
            //戻る
            forward = __doBack(map, thisForm, req, res, con);
        } else if (cmd.equals("300_group")) {
            //グループコンボ変更
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        } else if (cmd.equals("300_leftarrow")) {
            //「左矢印」処理
            log__.debug("「左矢印」処理（所属←未所属）");
            forward = __doAddUser(map, thisForm, req, res, con);
        } else if (cmd.equals("300_rightarrow")) {
            //「右矢印」処理
            log__.debug("「右矢印」処理（所属→未所属）");
            forward = __doDelUser(map, thisForm, req, res, con);
        } else {
            //初期表示
            __doInit(map, thisForm, req, res, con);
            forward = map.getInputForward();
        }
        log__.debug("END_MAN300");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Man300Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        con.setAutoCommit(true);
        Man300ParamModel paramMdl = new Man300ParamModel();
        paramMdl.setParam(form);
        Man300Biz biz = new Man300Biz();
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        biz.setInitData(paramMdl, con, getRequestModel(req), cmd);
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 登録ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doOk(ActionMapping map, Man300Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        //トランザクショントークン設定
        saveToken(req);

        return __doKakunin(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 登録確認画面へ遷移
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doKakunin(ActionMapping map, Man300Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        //トランザクショントークン設定
        saveToken(req);

        return map.findForward("300_ok");
    }

    /**
     * <br>[機  能] リクエストを解析し画面遷移先を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     */
    private ActionForward __doBack(ActionMapping map, Man300Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {

        ActionForward forward = null;

        //遷移元チェック
        if (form.getBackScreen() == GSConstMain.BACK_MAIN_ADM_SETTING) {
            return map.findForward("mainAdmSetting");
        }

        forward = map.findForward("300_back");
        return forward;
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
        Man300Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        Man300Biz biz = new Man300Biz();
        form.setMan300memberSid(
                biz.getAddMember(form.getMan300SelectRightUser(), form.getMan300memberSid()));

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
        Man300Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        Man300Biz biz = new Man300Biz();
        form.setMan300memberSid(
                biz.getDeleteMember(form.getMan300SelectLeftUser(), form.getMan300memberSid()));

        return __doInit(map, form, req, res, con);
    }
}
