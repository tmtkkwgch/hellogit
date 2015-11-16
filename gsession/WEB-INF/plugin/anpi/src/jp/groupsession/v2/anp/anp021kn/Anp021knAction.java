package jp.groupsession.v2.anp.anp021kn;


import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.anp.AbstractAnpiMobileAction;
import jp.groupsession.v2.anp.AnpiCommonBiz;
import jp.groupsession.v2.anp.anp999.Anp999Form;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 安否状況入力確認画面[モバイル版]のアクション
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Anp021knAction extends AbstractAnpiMobileAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp021knAction.class);

    /**
     * <br>[機  能] システムエラーなどの場合に遷移するアクションフォワード名を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @return フォワード名
     */
    @Override
    public String getErrorForwardName() {
        return "anpinput";
    }

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

        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);
        boolean actBack = !StringUtil.isNullZeroString(req.getParameter("anp021knback"));
        boolean actExcute = !StringUtil.isNullZeroString(req.getParameter("anp021knexcute"));
        Anp021knForm uform = (Anp021knForm) form;

        if (actBack) {
            //戻る
            return map.findForward("back");

        } else if (actExcute) {
            //登録
            forward = __doUpdate(map, uform, req, res, con);

        } else {
            //初期化
            forward = __doInit(map, uform, req, res, con);
        }

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
     * @throws Exception 例外
     * @return アクションフォーム
     */
    private ActionForward __doInit(ActionMapping map, Anp021knForm form,
                    HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        return __refresh(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 例外
     * @return アクションフォーム
     */
    private ActionForward __doUpdate(ActionMapping map, Anp021knForm form,
                    HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェック
        ActionErrors errors = form.validateAnp021(getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __refresh(map, form, req, res, con);
        }

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //登録
        Anp021knBiz biz = new Anp021knBiz();
        Anp021knParamModel paramModel = new Anp021knParamModel();
        paramModel.setParam(form);
        biz.doUpdate(paramModel, con);
        paramModel.setFormData(form);

        //ログ
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        AnpiCommonBiz anpBiz = new AnpiCommonBiz(con);
        String opCode = gsMsg.getMessage("cmn.entry");
        anpBiz.outPutLogNoSec(map, getRequestModel(req),
                opCode, GSConstLog.LEVEL_TRACE, "", Integer.valueOf(form.getUserSid()));

        //完了画面
        return __setUpdateDsp(map, form, req, con);
    }

    /**
     * <br>[機  能] 登録完了画面のパラメータセット
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @throws Exception SQL実行例外
     * @return ActionForward
     */
    private ActionForward __setUpdateDsp(ActionMapping map, Anp021knForm form,
                                  HttpServletRequest req, Connection con)
            throws Exception {

        //メッセージ
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        MessageResources msgRes = getResources(req);
        String msg = msgRes.getMessage("touroku.kanryo.object", gsMsg.getMessage("anp.jokyo"));

        return __setMsgDsp(map, form, req, msg);
    }

    /**
     * <br>[機  能] 表示処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 例外
     * @return アクションフォーム
     */
    private ActionForward __refresh(ActionMapping map, Anp021knForm form,
                    HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //初期データ取得
        Anp021knBiz biz = new Anp021knBiz();
        Anp021knParamModel paramModel = new Anp021knParamModel();
        paramModel.setParam(form);
        biz.setInitData(paramModel, con);
        paramModel.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] メッセージ画面のパラメータセット
     * <br>[解  説] OKボタンのみのメッセージ画面を表示する
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param msg メッセージ文字列
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __setMsgDsp(ActionMapping map, Anp021knForm form,
                                    HttpServletRequest req, String msg)
                        throws Exception {

        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        Anp999Form msgForm = new Anp999Form();
        BeanUtils.copyProperties(msgForm, form);

        msgForm.setIcon(Anp999Form.ICON_INFO);
        msgForm.setType(Anp999Form.TYPE_OK);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("anpinput");
        msgForm.setUrlOK(forwardOk.getPath());
        msgForm.setOkBtnValue(gsMsg.getMessage("anp.reenter"));

        //メッセージ
        msgForm.setMessage(msg);

        req.setAttribute("anp999Form", msgForm);

        return map.findForward("anpimb_gf_msg");
    }
}
