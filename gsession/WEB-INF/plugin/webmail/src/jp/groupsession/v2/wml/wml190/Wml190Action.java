package jp.groupsession.v2.wml.wml190;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.wml.AbstractWebmailAction;
import jp.groupsession.v2.wml.AbstractWmlForm;
import jp.groupsession.v2.wml.wml040.Wml040Biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] WEBメール 個人設定 アカウント編集画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml190Action extends AbstractWebmailAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml190Action.class);

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
        Wml190Form thisForm = (Wml190Form) form;

        //編集可能かを判定
        Wml190Biz biz = new Wml190Biz();
        if (!biz.canEditAccount(con, thisForm.getWmlAccountSid(),
                                getSessionUserModel(req).getUsrsid())) {
            __setAcntErrPageParam(map, req, thisForm);
            return map.findForward("gf_msg");
        }

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        if (cmd.equals("confirm")) {
            //OKボタンクリック
            forward = __doOK(map, thisForm, req, res, con);

        } else if (cmd.equals("beforePage")) {
            //戻るボタンクリック
            if (thisForm.getWmlAccountMode() == GSConstWebmail.WAC_TYPE_USER) {
                forward = map.findForward("accountManager");
            } else {
                forward = map.findForward("userAccountList");
            }

        } else if (cmd.equals("upSign")) {
            //上へボタンクリック
            forward = __doSortSign(map, thisForm, req, res, con, GSConstWebmail.SORT_UP);

        } else if (cmd.equals("downSign")) {
            //下へボタンクリック
            forward = __doSortSign(map, thisForm, req, res, con, GSConstWebmail.SORT_DOWN);

        } else if (cmd.equals("deleteSign")) {
            //削除(署名)ボタンクリック
            forward = __doDeleteSign(map, thisForm, req, res, con);

        } else if (cmd.equals("addProxyUser")) {
            //追加(代理人)ボタンクリック
            forward = __doAddProxyUser(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteProxyUser")) {
            //削除(代理人)ボタンクリック
            forward = __doDelProxyUser(map, thisForm, req, res, con);

        } else {
            //初期表示
            forward = __doInit(map, thisForm, req, res, con);
        }
        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form フォーム
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Wml190Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        if (form.getWml190initFlg() == GSConstWebmail.DSP_FIRST) {
            IOTools.deleteDir(_getWebmailTempDir(req));
        }

        con.setAutoCommit(true);
        Wml190ParamModel paramMdl = new Wml190ParamModel();
        paramMdl.setParam(form);
        Wml190Biz biz = new Wml190Biz();
        biz.setInitData(con, paramMdl, getRequestModel(req), getTempPath(req));
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] OKボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form フォーム
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doOK(ActionMapping map, Wml190Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェック
        ActionErrors errors = form.validateCheck(con, getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        // トランザクショントークン設定
        saveToken(req);

        return map.findForward("confirm");
    }


    /**
     * <br>[機  能] アカウント編集権限エラー画面遷移時のパラメータセット
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     */
    private void __setAcntErrPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Wml190Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        urlForward = map.findForward("mailList");

        ((AbstractWmlForm) form).setHiddenParam(cmn999Form);
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = null;
        msgState = "add.touroku.wmluser";

        cmn999Form.setMessage(msgRes.getMessage(msgState));
        //画面パラメータをセット
        form.setHiddenParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);
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
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    private ActionForward __doSortSign(
        ActionMapping map,
        Wml190Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con,
        int changeKbn) throws Exception {

        Wml040Biz biz040 = new Wml040Biz();
        int signNo = biz040.sortSignModel(getRequestModel(req),
                                                            getTempPath(req),
                                                            form.getWml190sign(),
                                                            changeKbn);
        form.setWml190sign(signNo);

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(署名)ボタンクリック時の処理を行う
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
    private ActionForward __doDeleteSign(
        ActionMapping map,
        Wml190Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        int signNo = form.getWml190sign();
        if (signNo > 0) {
            RequestModel reqMdl = getRequestModel(req);
            String tempRootDir = getTempPath(req);
            Wml040Biz biz040 = new Wml040Biz();
            biz040.deleteSignModel(reqMdl, tempRootDir, signNo);
            form.setWml190sign(0);
        }

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(代理人)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doAddProxyUser(
        ActionMapping map,
        Wml190Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setWml190proxyUser(
                cmnBiz.getAddMember(form.getWml190proxyUser(),
                                    form.getWml190proxyUserNoSelect()));
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(代理人)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDelProxyUser(
        ActionMapping map,
        Wml190Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setWml190proxyUser(
                cmnBiz.getDeleteMember(form.getWml190proxyUserSelect(),
                                    form.getWml190proxyUser()));

        return  __doInit(map, form, req, res, con);
    }
}

