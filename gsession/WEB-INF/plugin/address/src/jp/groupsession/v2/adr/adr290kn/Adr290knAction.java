package jp.groupsession.v2.adr.adr290kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.adr.AbstractAddressAction;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrAconfDao;
import jp.groupsession.v2.adr.model.AdrAconfModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] アドレス帳 カテゴリ登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr290knAction extends AbstractAddressAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr290knAction.class);

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
     * @return ActionForward
     */
    public ActionForward executeAction(
        ActionMapping map,
        ActionForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        log__.debug("START_Adr280kn");
        ActionForward forward = null;

        Adr290knForm thisForm = (Adr290knForm) form;

        //権限チェック
        forward = checkPow(map, req, con);
        if (forward != null) {
            return forward;
        }

        if (thisForm.getAdr280EditSid() == 1) {
            forward = getSubmitErrorPage(map, req);
            return forward;
        }


        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("input_back")) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward("editback");

        } else if (cmd.equals("kakutei")) {
            log__.debug("確定ボタンクリック");
            forward = __doKakutei(map, thisForm, req, res, con);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END_Adr280kn");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示を行う
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
    private ActionForward __doInit(
        ActionMapping map,
        Adr290knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        Adr290knBiz biz = new Adr290knBiz(getRequestModel(req));

        Adr290knParamModel paramMdl = new Adr290knParamModel();
        paramMdl.setParam(form);
        biz.getInitData(con, paramMdl);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doKakutei(
        ActionMapping map,
        Adr290knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェック
        ActionErrors errors = form.validateAdr290(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return map.getInputForward();
        }

        //採番コントローラ
        MlCountMtController cntCon = getCountMtController(req);

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //登録、または更新処理を行う
        RequestModel reqMdl = getRequestModel(req);
        Adr290knBiz biz = new Adr290knBiz(reqMdl);

        Adr290knParamModel paramMdl = new Adr290knParamModel();
        paramMdl.setParam(form);
        biz.doAddEdit(paramMdl, con, cntCon, userSid);
        paramMdl.setFormData(form);

        //ログ出力
        CommonBiz cmnBiz = new CommonBiz();
        GsMessage gsMsg = new GsMessage(reqMdl);
        cmnBiz.outPutCommonLog(map, reqMdl, gsMsg, con,
                gsMsg.getMessage("cmn.entry"), GSConstLog.LEVEL_INFO,
                "[CategoryName]" + form.getAdr290CategoryName()
                + " [CategoryBiko]" + NullDefault.getString(form.getAdr290bikou(), ""));


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
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Adr290knForm form,
        HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("catListBack");
        cmn999Form.setUrlOK(forwardOk.getPath());

        MessageResources msgRes = getResources(req);
        GsMessage gsMsg = new GsMessage();
        int procMode = form.getAdr280ProcMode();
        if (procMode == GSConstAddress.PROCMODE_ADD) {
            //登録完了
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object",
                            gsMsg.getMessage(req, "cmn.category")));
        } else if (procMode == GSConstUser.PROCMODE_EDIT) {
            //更新完了
            cmn999Form.setMessage(
                    msgRes.getMessage("hensyu.kanryo.object",
                            gsMsg.getMessage(req, "cmn.category")));
        }

        //画面パラメータをセット
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
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
            HttpServletRequest req, Connection con)
    throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        BaseUserModel usModel = getSessionUserModel(req);
        boolean gsAdmFlg = cmnBiz.isPluginAdmin(con, usModel, GSConstAddress.PLUGIN_ID_ADDRESS);

        if (!gsAdmFlg) {
            con.setAutoCommit(true);
            AdrAconfDao dao = new AdrAconfDao(con);
            AdrAconfModel model = dao.selectAconf();
            con.setAutoCommit(false);
            if (model != null && model.getAacAlbEdit() == GSConstAddress.POW_LIMIT) {
                return getNotAdminSeniPage(map, req);
            }
        }
        return null;
    }

}
