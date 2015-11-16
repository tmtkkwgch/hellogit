package jp.groupsession.v2.man.man030;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnContmDao;
import jp.groupsession.v2.cmn.model.CmnContmModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.AbstractGsAction;
import jp.groupsession.v2.usr.usr031.Usr031Form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] メイン 個人設定メニュー画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man030Action extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man030Action.class);

    /**
     * <p>
     * アクション実行
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
        Man030Form thisForm = (Man030Form) form;

        con.setAutoCommit(true);
        //コントロールマスタを取得
        CmnContmDao cntDao = new CmnContmDao(con);
        CmnContmModel cntMdl = cntDao.select();
        //メニュー項目の設定の有効・無効
        thisForm.setPluginSetting(cntMdl.getCntMenuStatic());
        con.setAutoCommit(false);

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        if (cmd.equals("passwordEdit")) {
            forward = map.findForward("passwordEdit");
        } else if (cmd.equals("back")) {
            forward = map.findForward("back");
        } else if (cmd.equals("schedule")) {
            forward = map.findForward("schedule");
        } else if (cmd.equals("toppage")) {
            forward = map.findForward("toppage");
        } else if (cmd.equals("menuEdit")) {
            forward = map.findForward("topMenuEdit");
        } else if (cmd.equals("mygroup")) {
            forward = map.findForward("mygroup");
        } else if (cmd.equals("theme")) {
            forward = map.findForward("theme");
        } else if (cmd.equals("portal")) {
            forward = map.findForward("portal");
        } else if (cmd.equals("mainLayout")) {
            forward = map.findForward("mainLayout");
        } else if (cmd.equals("languageEdit")) {
            forward = map.findForward("language");
        } else if (cmd.equals("userEdit")) {
            Usr031Form nextForm = new Usr031Form();
            BaseUserModel umodel = getSessionUserModel(req);
            String[] usrSids = {String.valueOf(umodel.getUsrsid())};
            nextForm.setUsr030selectusers(usrSids);
            nextForm.setProcessMode("kojn_edit");
            req.setAttribute("usr031Form", nextForm);
            forward = map.findForward("userEdit");
        } else if (cmd.equals("maindsp")) {
            forward = map.findForward("mainDspConf");
        } else {
            //デフォルト メイン画面表示
            forward = __doInit(map, (Man030Form) form, req, res, con);
        }
        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Man030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {

        if (canChangePassword()) {
            form.setChangePassword(GSConst.CHANGEPASSWORD_PARMIT);
        } else {
            form.setChangePassword(GSConst.CHANGEPASSWORD_NOTPARMIT);
        }

        RequestModel reqMdl = getRequestModel(req);
        int userSid = reqMdl.getSmodel().getUsrsid();

        con.setAutoCommit(true);
        Man030ParamModel paramMdl = new Man030ParamModel();
        paramMdl.setParam(form);
        Man030Biz biz = new Man030Biz();
        biz.setInitData(paramMdl, getPluginConfigForMain(getPluginConfig(req), con, userSid),
                        con, reqMdl);
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

        return map.getInputForward();
    }
}
