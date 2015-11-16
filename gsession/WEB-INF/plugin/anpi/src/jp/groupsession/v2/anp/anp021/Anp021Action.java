package jp.groupsession.v2.anp.anp021;


import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.anp.AbstractAnpiMobileAction;
import jp.groupsession.v2.anp.AnpiCommonBiz;
import jp.groupsession.v2.anp.anp999.Anp999Form;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 安否状況入力画面[モバイル版]のアクション
 * <br>[解  説]
 * <br>[備  考]
 * @author JTS
 */
public class Anp021Action extends AbstractAnpiMobileAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp021Action.class);

    /**
     * <br>[機  能] システムエラーなどの場合に遷移するアクションフォワード名を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @return フォワード名
     *
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
     *
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);
        boolean actExcute = !StringUtil.isNullZeroString(req.getParameter("anp021excute"));
        Anp021Form thisForm = (Anp021Form) form;

        if (thisForm.getAnp021initFlg() != 1) {
            //初回アクセス時、オペレーションログを出力する
            int userSid = NullDefault.getInt(thisForm.getUserSid(), -1);
            BaseUserModel smodel = new BaseUserModel();
            smodel.setUsrsid(userSid);
            RequestModel reqMdl = getRequestModel(req);
            reqMdl.setSmodel(smodel);

            GsMessage gsMsg = new GsMessage(reqMdl);
            AnpiCommonBiz anpBiz = new AnpiCommonBiz(con);
            String opCode = gsMsg.getMessage("address.adr110.2");
            anpBiz.outPutLog(map, reqMdl,
                            opCode, GSConstLog.LEVEL_TRACE, "");

            thisForm.setAnp021initFlg(1);
        }

        if (actExcute) {
            //登録
            forward = __doUpdate(map, thisForm, req, res, con);

        } else if (cmd.equals("anp021knback")) {
            //確認画面からの戻り時
            forward = __refresh(map, thisForm, req, res, con);

        } else {
            //初期化
            forward = __doInit(map, thisForm, req, res, con);
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
    private ActionForward __doInit(ActionMapping map, Anp021Form form,
                    HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //アクセス日時を更新
        Anp021Biz biz = new Anp021Biz();
        Anp021ParamModel paramModel = new Anp021ParamModel();
        paramModel.setParam(form);
        biz.doAccess(paramModel, con);

        //安否状況データ取得
        boolean exist = biz.setAnpiData(paramModel, con);
        paramModel.setFormData(form);

        //該当データがない場合、エラーメッセージ画面に遷移する
        if (!exist) {
            return __setReadErrorDsp(map, form, req, con);
        }

        return __refresh(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 読込エラー画面のパラメータセット
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @throws Exception 実行例外
     * @return ActionForward
     */
    private ActionForward __setReadErrorDsp(ActionMapping map, Anp021Form form,
                                  HttpServletRequest req, Connection con)
            throws Exception {

        Anp999Form msgForm = new Anp999Form();
        BeanUtils.copyProperties(msgForm, form);

        msgForm.setIcon(Anp999Form.ICON_INFO);
        msgForm.setType(-1);

        //メッセージ
        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        String msg = gsMsg.getMessage("anp.anp021.01");
        msgForm.setMessage(msg);

        req.setAttribute("anp999Form", msgForm);

        return map.findForward("anpimb_gf_msg");
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
     *
     */
    private ActionForward __doUpdate(ActionMapping map, Anp021Form form,
                    HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        //入力チェック
        ActionErrors errors = form.validateAnp021(getRequestModel(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __refresh(map, form, req, res, con);
        }

        // トランザクショントークン設定
        this.saveToken(req);

        return map.findForward("excute");
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
     *
     */
    private ActionForward __refresh(ActionMapping map, Anp021Form form,
                    HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        return map.getInputForward();
    }
}
