package jp.groupsession.v2.ntp.ntp133kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.ntp.AbstractNippouAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 日報 商品カテゴリ登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp133knAction extends AbstractNippouAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp133knAction.class);

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

        log__.debug("START_Ntp130kn");
        ActionForward forward = null;

        Ntp133knForm thisForm = (Ntp133knForm) form;

        if (thisForm.getNtp130EditSid() == 1) {
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

        log__.debug("END_Ntp130kn");
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
        Ntp133knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        Ntp133knBiz biz = new Ntp133knBiz(getRequestModel(req));

        Ntp133knParamModel paramMdl = new Ntp133knParamModel();
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
        Ntp133knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェック
        ActionErrors errors = form.validateNtp133(req);
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
        Ntp133knBiz biz = new Ntp133knBiz(getRequestModel(req));

        Ntp133knParamModel paramMdl = new Ntp133knParamModel();
        paramMdl.setParam(form);
        biz.doAddEdit(paramMdl, con, cntCon, userSid);
        paramMdl.setFormData(form);

        //ログ出力
        CommonBiz cmnBiz = new CommonBiz();
        GsMessage gsMsg = new GsMessage(req);
        cmnBiz.outPutCommonLog(map, getRequestModel(req), gsMsg, con,
                gsMsg.getMessage(req, "cmn.entry"), GSConstLog.LEVEL_INFO,
                "[CategoryName]" + form.getNtp133CategoryName()
                + " [CategoryBiko]" + NullDefault.getString(form.getNtp133bikou(), ""));


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
        Ntp133knForm form,
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
        String procMode = form.getNtp130ProcMode();
        if (procMode.equals("add")) {
            //登録完了
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object",
                            gsMsg.getMessage(req, "cmn.category")));
        } else if (procMode.equals("edit")) {
            //更新完了
            cmn999Form.setMessage(
                    msgRes.getMessage("hensyu.kanryo.object",
                            gsMsg.getMessage(req, "cmn.category")));
        }
        form.setNtp130HiddenParam(cmn999Form);
        cmn999Form.addHiddenParam("ntp130DspKbn", form.getNtp130DspKbn());

        //画面パラメータをセット
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

}
