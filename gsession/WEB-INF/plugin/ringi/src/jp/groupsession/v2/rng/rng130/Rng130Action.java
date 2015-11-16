package jp.groupsession.v2.rng.rng130;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
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
 * <br>[機  能] 稟議詳細検索画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng130Action extends AbstractRingiAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng130Action.class);

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

        Rng130Form thisForm = (Rng130Form) form;


        if (cmd.equals("prevPage")) {
            log__.debug("前ページクリック");
            thisForm.setRng130pageTop(thisForm.getRng130pageTop() - 1);
            forward = __doInit(map, thisForm, req, res, con);

        } else if (cmd.equals("nextPage")) {
            log__.debug("次ページクリック");
            thisForm.setRng130pageTop(thisForm.getRng130pageTop() + 1);
            forward = __doInit(map, thisForm, req, res, con);

        } else if (cmd.equals("backRngList")) {

            log__.debug("戻るボタンクリック");
            forward = map.findForward("backList");

        } else if (cmd.equals("rngDetail")) {

            log__.debug("稟議タイトル(受信、申請、完了)クリック");
            forward = map.findForward("detail");

        } else if (cmd.equals("rngEdit")) {

            log__.debug("稟議タイトル(草稿)クリック");
            forward = map.findForward("edit");

        } else if (cmd.equals("changeType")) {

            log__.debug("種別を変更");
            forward = __doChangeType(map, thisForm, req, res, con);

        } else if (cmd.equals("moveSearch")) {

            log__.debug("他画面からの検索");
            forward = __doSearch(map, thisForm, req, res, con);

        } else if (cmd.equals("search")) {

            log__.debug("検索ボタンクリック");
            forward = __doSearch(map, thisForm, req, res, con);

        } else {
            log__.debug("*** 初期表示を行います。");
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng020Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Rng130Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        RequestModel reqMdl = getRequestModel(req);
        con.setAutoCommit(true);
        Rng130ParamModel paramMdl = new Rng130ParamModel();
        paramMdl.setParam(form);
        Rng130Biz biz = new Rng130Biz(con, reqMdl);
        biz.setInitData(reqMdl, paramMdl, getAppRootPath(),
                        _getRingiDir(req), getSessionUserModel(req));
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 検索ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng020Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doSearch(ActionMapping map, Rng130Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        //入力チェック
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            form.setRng130searchFlg(0);
            return __doInit(map, form, req, res, con);
        }

        RequestModel reqMdl = getRequestModel(req);
        Rng130ParamModel paramMdl = new Rng130ParamModel();
        paramMdl.setParam(form);
        Rng130Biz biz = new Rng130Biz(con, reqMdl);
        int searchCount = biz.getSearchCount(paramMdl, getSessionUserSid(req));
        paramMdl.setFormData(form);
        if (searchCount <= 0) {
            GsMessage gsMsg = new GsMessage(reqMdl);
            String rng = gsMsg.getMessage("rng.62");

            ActionMessage msg = new ActionMessage("search.data.notfound", rng);
            errors.add("search.data.notfound", msg);
            addErrors(req, errors);
            form.setRng130searchFlg(0);
        } else {

            form.setSvRngKeyword(form.getRngKeyword());
            form.setSvRng130Type(form.getRng130Type());
            form.setSvGroupSid(form.getSltGroupSid());
            form.setSvUserSid(form.getSltUserSid());
            form.setSvRng130keyKbn(form.getRng130keyKbn());
            form.setSvRng130searchSubject1(form.getRng130searchSubject1());
            form.setSvRng130searchSubject2(form.getRng130searchSubject2());
            form.setSvSortKey1(form.getSltSortKey1());
            form.setSvRng130orderKey1(form.getRng130orderKey1());
            form.setSvSortKey2(form.getSltSortKey2());
            form.setSvRng130orderKey2(form.getRng130orderKey2());

            form.setSvApplYearFr(form.getSltApplYearFr());
            form.setSvApplMonthFr(form.getSltApplMonthFr());
            form.setSvApplDayFr(form.getSltApplDayFr());
            form.setSvApplYearTo(form.getSltApplYearTo());
            form.setSvApplMonthTo(form.getSltApplMonthTo());
            form.setSvApplDayTo(form.getSltApplDayTo());
            form.setSvLastManageYearFr(form.getSltLastManageYearFr());
            form.setSvLastManageMonthFr(form.getSltLastManageMonthFr());
            form.setSvLastManageDayFr(form.getSltLastManageDayFr());
            form.setSvLastManageYearTo(form.getSltLastManageYearTo());
            form.setSvLastManageMonthTo(form.getSltLastManageMonthTo());
            form.setSvLastManageDayTo(form.getSltLastManageDayTo());

            form.setRng130pageTop(1);
            form.setRng130pageBottom(1);
            form.setRng130searchFlg(1);
        }

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 種別変更時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng020Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doChangeType(ActionMapping map, Rng130Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        switch (form.getRng130Type()) {
            case RngConst.RNG_MODE_JYUSIN :
                form.setSltSortKey1(RngConst.RNG_SORT_JYUSIN);
                form.setRng130orderKey1(RngConst.RNG_ORDER_ASC);
                break;
            case RngConst.RNG_MODE_SINSEI :
                form.setSltSortKey1(RngConst.RNG_SORT_KAKUNIN);
                form.setRng130orderKey1(RngConst.RNG_ORDER_DESC);
                break;
            case RngConst.RNG_MODE_KANRYO :
                form.setSltSortKey1(RngConst.RNG_SORT_KAKUNIN);
                form.setRng130orderKey1(RngConst.RNG_ORDER_DESC);
                break;
            case RngConst.RNG_MODE_SOUKOU :
                form.setSltSortKey1(RngConst.RNG_SORT_TOUROKU);
                form.setRng130orderKey1(RngConst.RNG_ORDER_DESC);
                break;
            default :
                form.setSltSortKey1(RngConst.RNG_SORT_TITLE);
                form.setRng130orderKey1(RngConst.RNG_ORDER_ASC);
        }
        form.setSltSortKey2(RngConst.RNG_SORT_TITLE);
        form.setRng130orderKey2(RngConst.RNG_ORDER_ASC);

        return __doInit(map, form, req, res, con);
    }

}
