package jp.groupsession.v2.rng.rng060;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.AbstractRingiAction;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 稟議 内容テンプレート選択画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng060Action extends AbstractRingiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng060Action.class);

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        Rng060Form thisForm = (Rng060Form) form;
        int tFlg = thisForm.getRngTemplateMode();
        if (tFlg == RngConst.RNG_TEMPLATE_SHARE) {
            return false;
        } else {
            return true;
        }
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
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);
        Rng060Form thisForm = (Rng060Form) form;
        if (cmd.equals("rng020")) {
            log__.debug("*** 稟議作成。");
            forward = map.findForward("rng020");

        } else if (cmd.equals("addeditcategory")) {
            log__.debug("*** カテゴリ作成・編集。");
            forward = map.findForward("rng140");

        } else if (cmd.equals("060back")) {
            log__.debug("*** 戻るボタン押下。");
            forward = __doBack(map, thisForm, req, res, con);

        } else if (cmd.equals("sortUp")) {
            log__.debug("上へボタンクリック");
            forward = __doSortChange(map, thisForm, req, res, con, Rng060Biz.SORT_UP);

        } else if (cmd.equals("sortDown")) {
            log__.debug("下へボタンクリック");
            forward = __doSortChange(map, thisForm, req, res, con, Rng060Biz.SORT_DOWN);

        } else if (cmd.equals("060title")) {
            log__.debug("*** テンプレートタイトル選択。");
            forward = __doTitle(map, thisForm, req, res, con);

        } else if (cmd.equals("rng090")) {
            log__.debug("*** 内容テンプレート登録。");
            forward = map.findForward("rng090");

        } else if (cmd.equals("rng150")) {
            log__.debug("*** テンプレートカテゴリ一覧。");
            forward = map.findForward("rng150");

        } else {
            log__.debug("*** 初期表示を行います。");
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng060Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Rng060Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        con.setAutoCommit(true);
        Rng060ParamModel paramMdl = new Rng060ParamModel();
        paramMdl.setParam(form);
        Rng060Biz biz = new Rng060Biz(con, getRequestModel(req));
        biz.initDsp(paramMdl, getSessionUserSid(req));
        paramMdl.setFormData(form);
        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 並び順変更処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng060Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @param sortKbn 処理区分
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doSortChange(ActionMapping map, Rng060Form form,
                                        HttpServletRequest req, HttpServletResponse res,
                                        Connection con, int sortKbn)
            throws Exception {

        boolean commit = false;
        try {
            RequestModel reqMdl = getRequestModel(req);
            Rng060ParamModel paramMdl = new Rng060ParamModel();
            paramMdl.setParam(form);
            Rng060Biz biz = new Rng060Biz(con, reqMdl);
            biz.updateSort(con, paramMdl, sortKbn, getSessionUserSid(req));
            paramMdl.setFormData(form);

            GsMessage gsMsg = new GsMessage(reqMdl);
            String edit = gsMsg.getMessage("cmn.edit");
            String sort = gsMsg.getMessage("change.sort.order");

            //ログ出力処理
            RngBiz rngBiz = new RngBiz(con);
            rngBiz.outPutLog(
                    map,
                    edit, GSConstLog.LEVEL_INFO, sort,
                    reqMdl);

            con.commit();
            commit = true;
        } catch (Exception e) {
            log__.info("稟議テンプレートの並び順更新に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }
        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 戻る処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng060Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doBack(ActionMapping map, Rng060Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        ActionForward forward = null;
        int tMode = form.getRngTemplateMode();

        if (tMode == RngConst.RNG_TEMPLATE_ALL) {
            log__.debug("テンプレート作成画面。");
            forward = map.findForward("rng020");

        } else if (tMode == RngConst.RNG_TEMPLATE_SHARE) {
            log__.debug("管理者設定画面。");
            forward = map.findForward("rng040");

        } else if (tMode == RngConst.RNG_TEMPLATE_PRIVATE) {
            log__.debug("個人設定画面。");
            forward = map.findForward("rng080");
        } else {
            log__.debug("* * * 遷移エラー * * *");
            forward = map.findForward("rng010");
        }
        return forward;
    }

    /**
     * <br>[機  能] テンプレートタイトル選択時
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng060Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doTitle(ActionMapping map, Rng060Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        ActionForward forward = null;
        int tMode = form.getRngTemplateMode();

        if (tMode == RngConst.RNG_TEMPLATE_ALL) {
            log__.debug("内容テンプレート選択。");
            forward = map.findForward("rng061");

        } else if (tMode == RngConst.RNG_TEMPLATE_SHARE) {
            log__.debug("内容テンプレート登録。");
            forward = map.findForward("rng090");

        } else if (tMode == RngConst.RNG_TEMPLATE_PRIVATE) {
            log__.debug("内容テンプレート登録。");
            forward = map.findForward("rng090");
        }
        return forward;
    }
}
