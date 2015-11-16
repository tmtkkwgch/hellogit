package jp.groupsession.v2.rng.rng140kn;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
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
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 稟議 テンプレートカテゴリ登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng140knAction extends AbstractRingiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng140knAction.class);

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

        Rng140knForm thisForm = (Rng140knForm) form;

        if (cmd.equals("input_back")) {
            log__.debug("*** 戻るボタンクリック");
            forward = map.findForward("rng140knback");

        } else if (cmd.equals("kakutei")) {
            log__.debug("*** 確定ボタンクリック。");
            forward = __doKakutei(map, thisForm, req, res, con);

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
    public ActionForward __doInit(ActionMapping map,
                                  Rng140knForm form,
                                  HttpServletRequest req,
                                  HttpServletResponse res,
                                  Connection con)
                                  throws Exception {

        return map.getInputForward();
    }

    /**
     * <br>[機  能] OKボタンクリック時
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng010Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doKakutei(ActionMapping map,
                                  Rng140knForm form,
                                  HttpServletRequest req,
                                  HttpServletResponse res,
                                  Connection con)
                                  throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェック
        ActionErrors errors = form.validateRng140(getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return map.getInputForward();
       }

        //採番コントローラ
        MlCountMtController cntCon = getCountMtController(req);

        //ログインユーザSID取得
        BaseUserModel smodel = getSessionUserModel(req);
        int usrSid = smodel.getUsrsid();

        //登録または更新処理
        Rng140knBiz biz = new Rng140knBiz();

        boolean commit = false;
        try {
            Rng140knParamModel paramMdl = new Rng140knParamModel();
            paramMdl.setParam(form);
            if (form.getRng140ProcMode() == RngConst.RNG_CMDMODE_ADD) {
                biz.insert(paramMdl, con, usrSid, cntCon);
            } else if (form.getRng140ProcMode() == RngConst.RNG_CMDMODE_EDIT) {
                biz.update(paramMdl, con, usrSid, cntCon);
            }
            paramMdl.setFormData(form);

            con.commit();
            commit = true;
        } catch (Exception e) {
            log__.error("稟議テンプレートカテゴリの登録に失敗", e);
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String entry = gsMsg.getMessage("cmn.entry");
        String edit = gsMsg.getMessage("cmn.edit");
        String msg = "";
        if (form.getRng140ProcMode() == RngConst.RNG_CMDMODE_ADD) {
            msg = entry;
        } else {
            msg = edit;
        }

        //ログ出力
        CommonBiz cmnBiz = new CommonBiz();
        cmnBiz.outPutCommonLog(map, reqMdl, gsMsg, con,
                msg, GSConstLog.LEVEL_INFO,
                "[CategoryName]" + form.getRng140CatName());

        return __setKanryoDsp(map, form, req);
    }

    /**
     * [機  能] 完了画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(ActionMapping map,
                                         Rng140knForm form,
                                         HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = null;
        if (form.getRng140SeniFlg() == 1) {
            forwardOk = map.findForward("rng150");
        } else {
            forwardOk = map.findForward("rng060");
        }
        cmn999Form.setUrlOK(forwardOk.getPath());

        MessageResources msgRes = getResources(req);

        int procMode = form.getRng140ProcMode();

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "cmn.category");

        if (procMode == RngConst.RNG_CMDMODE_ADD) {
            //登録完了
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object", msg));
        } else if (procMode == RngConst.RNG_CMDMODE_EDIT) {
            //更新完了
            cmn999Form.setMessage(
                    msgRes.getMessage("hensyu.kanryo.object", msg));
        }

        cmn999Form.addHiddenParam("rngTemplateMode", form.getRngTemplateMode());
        cmn999Form.addHiddenParam("rng060SelectCat", form.getRng060SelectCat());
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        //画面パラメータをセット
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

}
