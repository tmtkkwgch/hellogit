package jp.groupsession.v2.rng.rng050;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.AbstractRingiAction;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] 稟議 管理者設定 申請中案件管理画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng050Action extends AbstractRingiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng050Action.class);

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        return false;
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

        Rng050Form thisForm = (Rng050Form) form;

        if (cmd.equals("prevPage")) {
            //前ページクリック
            thisForm.setRngAdminPageTop(thisForm.getRngAdminPageTop() - 1);
            forward = __doInit(map, thisForm, req, res, con);

        } else if (cmd.equals("nextPage")) {
            //次ページクリック
            thisForm.setRngAdminPageTop(thisForm.getRngAdminPageTop() + 1);
            forward = __doInit(map, thisForm, req, res, con);

        } else if (cmd.equals("rng040")) {
            log__.debug("*** 管理者設定 稟議。");
            forward = map.findForward("rng040");

        } else if (cmd.equals("rng030")) {
            log__.debug("*** 稟議承認。");
            forward = map.findForward("rng030");

        } else if (cmd.equals("search")) {
            log__.debug("*** 検索ボタン押下。");
            forward = __doSearch(map, thisForm, req, res, con);

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
     * @param form Rng010Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Rng050Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);

        Rng050ParamModel paramMdl = new Rng050ParamModel();
        paramMdl.setParam(form);
        Rng050Biz biz = new Rng050Biz(con, getRequestModel(req));
        biz.initDsp(paramMdl, getSessionUserSid(req));
        paramMdl.setFormData(form);

        //WEB検索プラグインを使用可能か設定
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));
        form.setRngWebSearchUse(CommonBiz.getWebSearchUse(pconfig));

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 検索ボタン押下時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng010Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doSearch(ActionMapping map, Rng050Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        con.setAutoCommit(true);
        //入力チェック
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            form.setRngAdminSearchFlg(0);
            return __doInit(map, form, req, res, con);
        }

        form.setRngAdminKeyword(form.getRngInputKeyword());
        form.setRngAdminGroupSid(form.getSltGroupSid());
        form.setRngAdminUserSid(form.getSltUserSid());

        form.setRngAdminApplYearFr(form.getSltApplYearFr());
        form.setRngAdminApplMonthFr(form.getSltApplMonthFr());
        form.setRngAdminApplDayFr(form.getSltApplDayFr());
        form.setRngAdminApplYearTo(form.getSltApplYearTo());
        form.setRngAdminApplMonthTo(form.getSltApplMonthTo());
        form.setRngAdminApplDayTo(form.getSltApplDayTo());
        form.setRngAdminLastManageYearFr(form.getSltLastManageYearFr());
        form.setRngAdminLastManageMonthFr(form.getSltLastManageMonthFr());
        form.setRngAdminLastManageDayFr(form.getSltLastManageDayFr());
        form.setRngAdminLastManageYearTo(form.getSltLastManageYearTo());
        form.setRngAdminLastManageMonthTo(form.getSltLastManageMonthTo());
        form.setRngAdminLastManageDayTo(form.getSltLastManageDayTo());

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String msg2 = gsMsg.getMessage("rng.62");

        //検索結果が0件の場合、エラーメッセージを表示
        Rng050ParamModel paramMdl = new Rng050ParamModel();
        paramMdl.setParam(form);
        Rng050Biz biz = new Rng050Biz(con, reqMdl);
        int rngCount = biz.getSearchCount(paramMdl);
        paramMdl.setFormData(form);
        if (rngCount <= 0) {
            ActionMessage msg = new ActionMessage("search.data.notfound", msg2);
            errors.add("search.data.notfound", msg);
            addErrors(req, errors);
            form.setRngAdminSearchFlg(0);

        } else {
            form.setRngAdminSortKey(RngConst.RNG_SORT_KAKUNIN);
            form.setRngAdminOrderKey(RngConst.RNG_ORDER_DESC);
            form.setRngAdminPageTop(1);

            form.setRngAdminSearchFlg(1);
        }

        return __doInit(map, form, req, res, con);
    }
}
