package jp.groupsession.v2.rng.rng070;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.AbstractRingiAction;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 稟議 管理者設定 完了案件管理画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng070Action extends AbstractRingiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng070Action.class);

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

        Rng070Form thisForm = (Rng070Form) form;
        if (cmd.equals("rng040")) {
            log__.debug("*** 管理者設定 稟議。");
            forward = map.findForward("rng040");

        } else if (cmd.equals("rng030")) {
            log__.debug("*** 稟議承認。");
            forward = map.findForward("rng030");

        } else if (cmd.equals("search")) {
            log__.debug("*** 検索ボタン押下。");
            thisForm.setRngAdminSearchFlg(1);
            forward = __doSearch(map, thisForm, req, res, con);

        } else if (cmd.equals("rng070del")) {
            log__.debug("*** 削除ボタン押下。");
            forward = __doDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("delexe")) {
            log__.debug("*** 削除確認OK。");
            forward = __exeDelete(map, thisForm, req, res, con);

        } else if (cmd.equals("pageleft")) {
            log__.debug("*** 左矢印ボタン押下");
            thisForm.setRngAdminPageTop(thisForm.getRngAdminPageTop() - 1);
            forward = __doInit(map, thisForm, req, res, con);

        } else if (cmd.equals("pageright")) {
            log__.debug("*** 右矢印ボタン押下");
            thisForm.setRngAdminPageTop(thisForm.getRngAdminPageTop() + 1);
            forward = __doInit(map, thisForm, req, res, con);

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
    public ActionForward __doInit(ActionMapping map, Rng070Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        con.setAutoCommit(true);

        Rng070ParamModel paramMdl = new Rng070ParamModel();
        paramMdl.setParam(form);
        Rng070Biz biz = new Rng070Biz(con, getRequestModel(req));
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
    public ActionForward __doSearch(ActionMapping map, Rng070Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        ActionErrors errors = null;
        errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            log__.debug("入力チェックエラーです");
            addErrors(req, errors);
            form.setRngAdminSearchFlg(0);
            return __doInit(map, form, req, res, con);
        }

        form.setRngAdminGroupSid(form.getSltGroupSid());
        form.setRngAdminUserSid(form.getSltUserSid());
        form.setRngAdminKeyword(form.getRngInputKeyword());
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
        String rng = gsMsg.getMessage("rng.62");
        //検索結果が0件の場合、エラーメッセージを表示
        Rng070ParamModel paramMdl = new Rng070ParamModel();
        paramMdl.setParam(form);
        Rng070Biz biz = new Rng070Biz(con, reqMdl);
        int rngCount = biz.getSearchCount(paramMdl);
        paramMdl.setFormData(form);
        if (rngCount <= 0) {
            ActionMessage msg = new ActionMessage("search.data.notfound", rng);
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
    /**
     * <br>[機  能] 削除ボタン押下時の処理
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
    public ActionForward __doDelete(ActionMapping map, Rng070Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        ActionErrors errors = null;
        errors = form.validateDel(req);
        if (!errors.isEmpty()) {
            log__.debug("入力チェックエラーです");
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        // トランザクショントークン設定
        saveToken(req);

        //削除確認画面へ遷移
        return __setDeleteDsp(map, req, form);
    }

    /**
     * <br>[機  能] 削除確認画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @return ActionForward フォワード
     */
    private ActionForward __setDeleteDsp(ActionMapping map,
                                          HttpServletRequest req,
                                          Rng070Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("delexe");
        cmn999Form.setUrlOK(forwardOk.getPath() + "?" + GSConst.P_CMD + "=delexe");

        //キャンセルボタンクリック時遷移先
        ActionForward forwardCancel = map.findForward("delback");
        cmn999Form.setUrlCancel(forwardCancel.getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "rng.85");
        //メッセージ
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
                msgRes.getMessage(
                        "sakujo.kakunin.once",
                        msg));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("rngProcMode", form.getRngProcMode());
        cmn999Form.addHiddenParam("rng010orderKey", form.getRng010orderKey());
        cmn999Form.addHiddenParam("rng010sortKey", form.getRng010sortKey());
        cmn999Form.addHiddenParam("rng010pageTop", form.getRng010pageTop());
        cmn999Form.addHiddenParam("rng010pageBottom", form.getRng010pageBottom());

        cmn999Form.addHiddenParam("rng070dellist", form.getRng070dellist());
        cmn999Form.addHiddenParam("rngAdminOrderKey", form.getRngAdminOrderKey());
        cmn999Form.addHiddenParam("rngAdminSortKey", form.getRngAdminSortKey());
        cmn999Form.addHiddenParam("rngAdminSearchFlg", form.getRngAdminSearchFlg());
        cmn999Form.addHiddenParam("rngAdminPageTop", form.getRngAdminPageTop());
        cmn999Form.addHiddenParam("rngAdminPageBottom", form.getRngAdminPageBottom());

        cmn999Form.addHiddenParam("rngInputKeyword", form.getRngInputKeyword());
        cmn999Form.addHiddenParam("sltGroupSid", form.getSltGroupSid());
        cmn999Form.addHiddenParam("sltUserSid", form.getSltUserSid());
        cmn999Form.addHiddenParam("sltApplYearFr", form.getSltApplYearFr());
        cmn999Form.addHiddenParam("sltApplMonthFr", form.getSltApplMonthFr());
        cmn999Form.addHiddenParam("sltApplDayFr", form.getSltApplDayFr());
        cmn999Form.addHiddenParam("sltApplYearTo", form.getSltApplYearTo());
        cmn999Form.addHiddenParam("sltApplMonthTo", form.getSltApplMonthTo());
        cmn999Form.addHiddenParam("sltApplDayTo", form.getSltApplDayTo());
        cmn999Form.addHiddenParam("sltLastManageYearFr", form.getSltLastManageYearFr());
        cmn999Form.addHiddenParam("sltLastManageMonthFr", form.getSltLastManageMonthFr());
        cmn999Form.addHiddenParam("sltLastManageDayFr", form.getSltLastManageDayFr());
        cmn999Form.addHiddenParam("sltLastManageYearTo", form.getSltLastManageYearTo());
        cmn999Form.addHiddenParam("sltLastManageMonthTo", form.getSltLastManageMonthTo());
        cmn999Form.addHiddenParam("sltLastManageDayTo", form.getSltLastManageDayTo());

        cmn999Form.addHiddenParam("rngAdminKeyword", form.getRngAdminKeyword());
        cmn999Form.addHiddenParam("rngAdminGroupSid", form.getRngAdminGroupSid());
        cmn999Form.addHiddenParam("rngAdminUserSid", form.getRngAdminUserSid());
        cmn999Form.addHiddenParam("rngAdminApplYearFr", form.getRngAdminApplYearFr());
        cmn999Form.addHiddenParam("rngAdminApplMonthFr", form.getRngAdminApplMonthFr());
        cmn999Form.addHiddenParam("rngAdminApplDayFr", form.getRngAdminApplDayFr());
        cmn999Form.addHiddenParam("rngAdminApplYearTo", form.getRngAdminApplYearTo());
        cmn999Form.addHiddenParam("rngAdminApplMonthTo", form.getRngAdminApplMonthTo());
        cmn999Form.addHiddenParam("rngAdminApplDayTo", form.getRngAdminApplDayTo());
        cmn999Form.addHiddenParam("rngAdminLastManageYearFr", form.getRngAdminLastManageYearFr());
        cmn999Form.addHiddenParam("rngAdminLastManageMonthFr", form.getRngAdminLastManageMonthFr());
        cmn999Form.addHiddenParam("rngAdminLastManageDayFr", form.getRngAdminLastManageDayFr());
        cmn999Form.addHiddenParam("rngAdminLastManageYearTo", form.getRngAdminLastManageYearTo());
        cmn999Form.addHiddenParam("rngAdminLastManageMonthTo", form.getRngAdminLastManageMonthTo());
        cmn999Form.addHiddenParam("rngAdminLastManageDayTo", form.getRngAdminLastManageDayTo());

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
    /**
     * <br>[機  能] 削除実行
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    private ActionForward __exeDelete(
        ActionMapping map,
        Rng070Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        ActionForward forward = null;
        boolean commit = false;
        try {
            RequestModel reqMdl = getRequestModel(req);
            Rng070Biz biz = new Rng070Biz(con, reqMdl);
            for (String delrng : form.getRng070dellist()) {
                log__.debug("[ 削除する稟議 ] = " + delrng);

                Rng070ParamModel paramMdl = new Rng070ParamModel();
                paramMdl.setParam(form);
                biz.deleteKanryoRng(
                        paramMdl, getSessionUserSid(req), Integer.parseInt(delrng));
                paramMdl.setFormData(form);
            }
            forward = __setCompDsp(map, req, form);

            GsMessage gsMsg = new GsMessage(reqMdl);
            String delete = gsMsg.getMessage("cmn.delete");

            //ログ出力処理
            RngBiz rngBiz = new RngBiz(con);
            String opCode = delete;

            rngBiz.outPutLog(
                    map, opCode,
                    GSConstLog.LEVEL_TRACE, "",
                    reqMdl);

            con.commit();
            commit = true;
        } catch (Exception e) {
            log__.error("稟議情報の削除に失敗", e);
        } finally {
            if (!commit) {
                con.rollback();
            }
        }


        return forward;
    }

    /**
     * <br>[機  能] 削除完了画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @return ActionForward フォワード
     */
    private ActionForward __setCompDsp(ActionMapping map,
                                        HttpServletRequest req,
                                        Rng070Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = null;

        forwardOk = map.findForward("rng070");

        cmn999Form.setUrlOK(forwardOk.getPath());

        GsMessage gsMsg = new GsMessage();
        String rng = gsMsg.getMessage(req, "rng.62");

        //メッセージ
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object", rng));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("rngProcMode", form.getRngProcMode());
        cmn999Form.addHiddenParam("rng010orderKey", form.getRng010orderKey());
        cmn999Form.addHiddenParam("rng010sortKey", form.getRng010sortKey());
        cmn999Form.addHiddenParam("rng010pageTop", form.getRng010pageTop());
        cmn999Form.addHiddenParam("rng010pageBottom", form.getRng010pageBottom());

        cmn999Form.addHiddenParam("rngAdminOrderKey", form.getRngAdminOrderKey());
        cmn999Form.addHiddenParam("rngAdminSortKey", form.getRngAdminSortKey());
        cmn999Form.addHiddenParam("rngAdminSearchFlg", form.getRngAdminSearchFlg());
        cmn999Form.addHiddenParam("rngAdminPageTop", form.getRngAdminPageTop());
        cmn999Form.addHiddenParam("rngAdminPageBottom", form.getRngAdminPageBottom());

        cmn999Form.addHiddenParam("rngInputKeyword", form.getRngInputKeyword());
        cmn999Form.addHiddenParam("sltGroupSid", form.getSltGroupSid());
        cmn999Form.addHiddenParam("sltUserSid", form.getSltUserSid());
        cmn999Form.addHiddenParam("sltApplYearFr", form.getSltApplYearFr());
        cmn999Form.addHiddenParam("sltApplMonthFr", form.getSltApplMonthFr());
        cmn999Form.addHiddenParam("sltApplDayFr", form.getSltApplDayFr());
        cmn999Form.addHiddenParam("sltApplYearTo", form.getSltApplYearTo());
        cmn999Form.addHiddenParam("sltApplMonthTo", form.getSltApplMonthTo());
        cmn999Form.addHiddenParam("sltApplDayTo", form.getSltApplDayTo());
        cmn999Form.addHiddenParam("sltLastManageYearFr", form.getSltLastManageYearFr());
        cmn999Form.addHiddenParam("sltLastManageMonthFr", form.getSltLastManageMonthFr());
        cmn999Form.addHiddenParam("sltLastManageDayFr", form.getSltLastManageDayFr());
        cmn999Form.addHiddenParam("sltLastManageYearTo", form.getSltLastManageYearTo());
        cmn999Form.addHiddenParam("sltLastManageMonthTo", form.getSltLastManageMonthTo());
        cmn999Form.addHiddenParam("sltLastManageDayTo", form.getSltLastManageDayTo());

        cmn999Form.addHiddenParam("rngAdminKeyword", form.getRngAdminKeyword());
        cmn999Form.addHiddenParam("rngAdminGroupSid", form.getRngAdminGroupSid());
        cmn999Form.addHiddenParam("rngAdminUserSid", form.getRngAdminUserSid());
        cmn999Form.addHiddenParam("rngAdminApplYearFr", form.getRngAdminApplYearFr());
        cmn999Form.addHiddenParam("rngAdminApplMonthFr", form.getRngAdminApplMonthFr());
        cmn999Form.addHiddenParam("rngAdminApplDayFr", form.getRngAdminApplDayFr());
        cmn999Form.addHiddenParam("rngAdminApplYearTo", form.getRngAdminApplYearTo());
        cmn999Form.addHiddenParam("rngAdminApplMonthTo", form.getRngAdminApplMonthTo());
        cmn999Form.addHiddenParam("rngAdminApplDayTo", form.getRngAdminApplDayTo());
        cmn999Form.addHiddenParam("rngAdminLastManageYearFr", form.getRngAdminLastManageYearFr());
        cmn999Form.addHiddenParam("rngAdminLastManageMonthFr", form.getRngAdminLastManageMonthFr());
        cmn999Form.addHiddenParam("rngAdminLastManageDayFr", form.getRngAdminLastManageDayFr());
        cmn999Form.addHiddenParam("rngAdminLastManageYearTo", form.getRngAdminLastManageYearTo());
        cmn999Form.addHiddenParam("rngAdminLastManageMonthTo", form.getRngAdminLastManageMonthTo());
        cmn999Form.addHiddenParam("rngAdminLastManageDayTo", form.getRngAdminLastManageDayTo());

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
}
