package jp.groupsession.v2.usr.usr045kn;

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
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrConfDao;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.AbstractUsrAction;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ユーザ情報 カテゴリ登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Usr045knAction extends AbstractUsrAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Usr045knAction.class);

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

        log__.debug("START_Usr045kn");
        ActionForward forward = null;

        Usr045knForm thisForm = (Usr045knForm) form;

        //権限チェック
        forward = checkPow(map, req, con);
        if (forward != null) {
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

        log__.debug("END_Usr045kn");
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
        Usr045knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        Usr045knBiz biz = new Usr045knBiz();

        Usr045knParamModel paramMdl = new Usr045knParamModel();
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
        Usr045knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //入力チェック
        ActionErrors errors = form.validateUsr045(req);
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
        Usr045knBiz biz = new Usr045knBiz();

        Usr045knParamModel paramMdl = new Usr045knParamModel();
        paramMdl.setParam(form);
        biz.doAddEdit(paramMdl, con, cntCon, userSid);
        paramMdl.setFormData(form);

        GsMessage gsMsg = new GsMessage(req);
        /** メッセージ 登録 **/
        String entry = gsMsg.getMessage("cmn.entry");

        //ログ出力
        CommonBiz cmnBiz = new CommonBiz();
        cmnBiz.outPutCommonLog(map, getRequestModel(req), gsMsg, con,
                entry, GSConstLog.LEVEL_INFO,
                "[CategoryName]" + form.getUsr045CategoryName()
                + " [CategoryBiko]" + NullDefault.getString(form.getUsr045bikou(), ""));


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
        Usr045knForm form,
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
        //カテゴリ
        String textCategory = gsMsg.getMessage(req, "cmn.category");
        int procMode = form.getUsr043ProcMode();
        if (procMode == GSConstUser.PROCMODE_ADD) {
            //登録完了
            cmn999Form.setMessage(
                    msgRes.getMessage("touroku.kanryo.object", textCategory));
        } else if (procMode == GSConstUser.PROCMODE_EDIT) {
            //更新完了
            cmn999Form.setMessage(
                    msgRes.getMessage("hensyu.kanryo.object", textCategory));
        }

        //画面パラメータをセット
        cmn999Form.addHiddenParam("usr043EditSid", form.getUsr043EditSid());

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("usr040cmdMode", form.getUsr040cmdMode());
        cmn999Form.addHiddenParam("usr040orderKey", form.getUsr040orderKey());
        cmn999Form.addHiddenParam("usr040sortKey", form.getUsr040sortKey());
        cmn999Form.addHiddenParam("usr040orderKey2", form.getUsr040orderKey2());
        cmn999Form.addHiddenParam("usr040sortKey2", form.getUsr040sortKey2());
        cmn999Form.addHiddenParam("usr040pageNum1", form.getUsr040pageNum1());
        cmn999Form.addHiddenParam("usr040pageNum2", form.getUsr040pageNum2());
        cmn999Form.addHiddenParam("usr040SearchKana", form.getUsr040SearchKana());
        cmn999Form.addHiddenParam("selectgsid", form.getSelectgsid());
        cmn999Form.addHiddenParam("usr040Keyword", form.getUsr040Keyword());
        cmn999Form.addHiddenParam("usr040KeyKbnShainno", form.getUsr040KeyKbnShainno());
        cmn999Form.addHiddenParam("usr040KeyKbnName", form.getUsr040KeyKbnName());
        cmn999Form.addHiddenParam("usr040KeyKbnNameKn", form.getUsr040KeyKbnNameKn());
        cmn999Form.addHiddenParam("usr040KeyKbnMail", form.getUsr040KeyKbnMail());
        cmn999Form.addHiddenParam("usr040agefrom", form.getUsr040agefrom());
        cmn999Form.addHiddenParam("usr040ageto", form.getUsr040ageto());
        cmn999Form.addHiddenParam("usr040yakushoku", form.getUsr040yakushoku());
        cmn999Form.addHiddenParam("usr040tdfkCd", form.getUsr040tdfkCd());
        cmn999Form.addHiddenParam("usr040seibetu", form.getUsr040seibetu());
        cmn999Form.addHiddenParam("usr040entranceYearFr", form.getUsr040entranceYearFr());
        cmn999Form.addHiddenParam("usr040entranceMonthFr", form.getUsr040entranceMonthFr());
        cmn999Form.addHiddenParam("usr040entranceDayFr", form.getUsr040entranceDayFr());
        cmn999Form.addHiddenParam("usr040entranceYearTo", form.getUsr040entranceYearTo());
        cmn999Form.addHiddenParam("usr040entranceMonthTo", form.getUsr040entranceMonthTo());
        cmn999Form.addHiddenParam("usr040entranceDayTo", form.getUsr040entranceDayTo());
        if (form.getUsr040labSid() != null) {
            for (int i = 0; i < form.getUsr040labSid().length; i++) {
                cmn999Form.addHiddenParam("usr040labSid", form.getUsr040labSid()[i]);
            }
        }

        cmn999Form.addHiddenParam("selectgsidSave", form.getSelectgsidSave());
        cmn999Form.addHiddenParam("usr040SearchKanaSave", form.getUsr040SearchKanaSave());
        cmn999Form.addHiddenParam("usr040KeywordSave", form.getUsr040KeywordSave());
        cmn999Form.addHiddenParam("usr040KeyKbnShainnoSave", form.getUsr040KeyKbnShainnoSave());
        cmn999Form.addHiddenParam("usr040KeyKbnNameSave", form.getUsr040KeyKbnNameSave());
        cmn999Form.addHiddenParam("usr040KeyKbnNameKnSave", form.getUsr040KeyKbnNameKnSave());
        cmn999Form.addHiddenParam("usr040KeyKbnMailSave", form.getUsr040KeyKbnMailSave());
        cmn999Form.addHiddenParam("usr040agefromSave", form.getUsr040agefromSave());
        cmn999Form.addHiddenParam("usr040agetoSave", form.getUsr040agetoSave());
        cmn999Form.addHiddenParam("usr040yakushokuSave", form.getUsr040yakushokuSave());
        cmn999Form.addHiddenParam("usr040tdfkCdSave", form.getUsr040tdfkCdSave());
        cmn999Form.addHiddenParam("usr040seibetuSave", form.getUsr040seibetu());
        cmn999Form.addHiddenParam("usr040entranceYearFrSave", form.getUsr040entranceYearFrSave());
        cmn999Form.addHiddenParam("usr040entranceMonthFrSave", form.getUsr040entranceMonthFrSave());
        cmn999Form.addHiddenParam("usr040entranceDayFrSave", form.getUsr040entranceDayFrSave());
        cmn999Form.addHiddenParam("usr040entranceYearToSave", form.getUsr040entranceYearToSave());
        cmn999Form.addHiddenParam("usr040entranceMonthToSave", form.getUsr040entranceMonthToSave());
        cmn999Form.addHiddenParam("usr040entranceDayToSave", form.getUsr040entranceDayToSave());
        if (form.getUsr040labSidSave() != null && form.getUsr040labSidSave().length != 0) {
            for (int n = 0; n < form.getUsr040labSidSave().length; n++) {
                cmn999Form.addHiddenParam("usr040labSidSave", form.getUsr040labSidSave()[n]);
            }
        }
        cmn999Form.addHiddenParam("usr040SearchFlg", form.getUsr040SearchFlg());
        cmn999Form.addHiddenParam("usr040DspFlg", form.getUsr040DspFlg());

        cmn999Form.addHiddenParam("usr040GrpSearchGId", form.getUsr040GrpSearchGId());
        cmn999Form.addHiddenParam("usr040GrpSearchGName", form.getUsr040GrpSearchGName());
        cmn999Form.addHiddenParam("usr040GrpSearchGIdSave", form.getUsr040GrpSearchGIdSave());
        cmn999Form.addHiddenParam("usr040GrpSearchGNameSave", form.getUsr040GrpSearchGNameSave());

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

        BaseUserModel buMdl = getSessionUserModel(req);
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstUser.PLUGIN_ID_USER);

        if (!adminUser) {
            con.setAutoCommit(true);
            CmnLabelUsrConfDao dao = new CmnLabelUsrConfDao(con);
            CmnLabelUsrConfModel model = dao.select();
            con.setAutoCommit(false);
            if (model != null && model.getLufEdit() == GSConstUser.POW_LIMIT) {
                return getNotAdminSeniPage(map, req);
            }
        }
        return null;
    }

}
