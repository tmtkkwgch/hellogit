package jp.groupsession.v2.adr.adr210;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.adr.AbstractAddressAction;
import jp.groupsession.v2.adr.AdrCommonBiz;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrAconfDao;
import jp.groupsession.v2.adr.model.AdrAconfModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] アドレス帳 役職一覧画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr210Action extends AbstractAddressAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr210Action.class);

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
        Adr210Form thisForm = (Adr210Form) form;

        //権限チェック
        forward = checkPow(map, req, con);
        if (forward != null) {
            return forward;
        }

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);

        if (cmd.equals("adr210back")) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward("adr010");

        } else if (cmd.equals("adr210add")) {
            log__.debug("追加ボタンクリック");
            forward = map.findForward("adr220");

        } else if (cmd.equals("posname")) {
            log__.debug("役職名クリック");
            forward = map.findForward("adr220");

        } else if (cmd.equals("up")) {
            log__.debug("上へボタンクリック");
            forward = __doSortChange(map, thisForm, req, res, con, Adr210Biz.SORT_UP);

        } else if (cmd.equals("down")) {
            log__.debug("下へボタンクリック");
            forward = __doSortChange(map, thisForm, req, res, con, Adr210Biz.SORT_DOWN);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);

        }
        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Adr080Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws SQLException 実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Adr210Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws SQLException {

        con.setAutoCommit(true);
        Adr210Biz biz = new Adr210Biz(getRequestModel(req));

        Adr210ParamModel paramMdl = new Adr210ParamModel();
        paramMdl.setParam(form);
        biz.getInitData(con, paramMdl);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 上へ/下へボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param changeKbn 処理区分 0:順序をあげる 1:順序を下げる
     * @throws SQLException SQL実行例外
     * @return ActionForward
     */
    private ActionForward __doSortChange(ActionMapping map,
                                          Adr210Form form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con,
                                          int changeKbn) throws SQLException {

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {

            Adr210Biz biz = new Adr210Biz(getRequestModel(req));

            Adr210ParamModel paramMdl = new Adr210ParamModel();
            paramMdl.setParam(form);
            biz.updateSort(con, paramMdl, changeKbn);
            paramMdl.setFormData(form);

            GsMessage gsMsg = new GsMessage();
            //ログ出力処理
            AdrCommonBiz adrBiz = new AdrCommonBiz(con);
            adrBiz.outPutLog(
                    map, req, res,
                    gsMsg.getMessage(req, "cmn.change"), GSConstLog.LEVEL_TRACE,
                    gsMsg.getMessage(req, "change.sort.order"));
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 権限チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param req HttpServletRequest
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward checkPow(ActionMapping map,
                                   HttpServletRequest req,
                                   Connection con) throws Exception {

        //ユーザ情報を取得
        HttpSession session = req.getSession(false);
        BaseUserModel usModel = (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

        //GS管理者権限を取得
        CommonBiz cmnBiz = new CommonBiz();
        boolean gsAdmFlg = cmnBiz.isPluginAdmin(con, usModel, GSConstAddress.PLUGIN_ID_ADDRESS);

        //業種編集権限を取得
        con.setAutoCommit(true);
        AdrAconfDao dao = new AdrAconfDao(con);
        AdrAconfModel model = dao.selectAconf();
        con.setAutoCommit(false);

        if (!gsAdmFlg && (model != null && model.getAacYksEdit() == GSConstAddress.POW_LIMIT)) {
            return map.findForward("gf_power");
        }

        return null;
    }
}