package jp.groupsession.v2.ntp.ntp093;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.ntp.AbstractNippouAction;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 日報 グループメンバー表示設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp093Action extends AbstractNippouAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp093Action.class);

    /**
     * <br>アクション実行
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
        log__.debug("AA form = " + form);
        Ntp093Form ntpForm = (Ntp093Form) form;
        if (form == null) {
            log__.debug(" form is null ");
        }

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        if (cmd.equals("ntp093kakunin")) {
            //確認
            forward = __doKakunin(map, ntpForm, req, res, con);
        } else if (cmd.equals("ntp093commit")) {
            //登録 戻る
            forward = __doCommit(map, ntpForm, req, res, con);
        } else if (cmd.equals("ntp093back")) {
            //戻る
            forward = __doBack(map, ntpForm, req, res, con);
        } else if (cmd.equals("ntp093reload")) {
            forward = __doReload(map, ntpForm, req, res, con);
        } else {
            //デフォルト
            log__.debug("BB form = " + ntpForm);
            forward = __doInit(map, ntpForm, req, res, con);
        }
        return forward;
    }

    /**
     * <br>確認処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doKakunin(
        ActionMapping map,
        Ntp093Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        log__.debug("確認");

        BaseUserModel umodel = getSessionUserModel(req);
        Ntp093Biz biz = new Ntp093Biz(con, getRequestModel(req));

        ActionErrors errors = form.validateCheck(con, umodel.getUsrsid());
        if (!errors.isEmpty()) {
            addErrors(req, errors);

            Ntp093ParamModel paramMdl = new Ntp093ParamModel();
            paramMdl.setParam(form);
            biz.setDspData(paramMdl, con, umodel.getUsrsid());
            paramMdl.setFormData(form);

            return map.getInputForward();
        }

        //トランザクショントークン設定
        saveToken(req);

        //共通メッセージ画面を表示
        __setKakuninPageParam(map, req, form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 確認メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     */
    private void __setKakuninPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Ntp093Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
//        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        cmn999Form.setUrlOK(map.findForward("changeOk").getPath());
        cmn999Form.setUrlCancel(map.findForward("changeCancel").getPath());

        //メッセージセット
        String msgState = "edit.kakunin.once";
        String mkey1 = "グループメンバー表示設定";
        cmn999Form.setMessage(msgRes.getMessage(msgState, mkey1));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("ntp093SortKey1", form.getNtp093SortKey1());
        cmn999Form.addHiddenParam("ntp093SortOrder1", form.getNtp093SortOrder1());
        cmn999Form.addHiddenParam("ntp093SortKey2", form.getNtp093SortKey2());
        cmn999Form.addHiddenParam("ntp093SortOrder2", form.getNtp093SortOrder2());
        cmn999Form.addHiddenParam("ntp093DefGroup", form.getNtp093DefGroup());

        cmn999Form.addHiddenParam("dspMod", form.getDspMod());
        cmn999Form.addHiddenParam("listMod", form.getListMod());
        cmn999Form.addHiddenParam("ntp010DspDate", form.getNtp010DspDate());
        cmn999Form.addHiddenParam("ntp010DspGpSid", form.getNtp010DspGpSid());
        cmn999Form.addHiddenParam("ntp010SelectUsrSid", form.getNtp010SelectUsrSid());
        cmn999Form.addHiddenParam("ntp010SelectUsrKbn", form.getNtp010SelectUsrKbn());
        cmn999Form.addHiddenParam("ntp010SelectDate", form.getNtp010SelectDate());
        cmn999Form.addHiddenParam("ntp020SelectUsrSid", form.getNtp020SelectUsrSid());
        cmn999Form.addHiddenParam("ntp030SelectUsrSid", form.getNtp030SelectUsrSid());

        cmn999Form.addHiddenParam("ntp100PageNum", form.getNtp100PageNum());
        cmn999Form.addHiddenParam("ntp100Slt_page1", form.getNtp100Slt_page1());
        cmn999Form.addHiddenParam("ntp100Slt_page2", form.getNtp100Slt_page2());
        cmn999Form.addHiddenParam("ntp100OrderKey1", form.getNtp100OrderKey1());
        cmn999Form.addHiddenParam("ntp100SortKey1", form.getNtp100SortKey1());
        cmn999Form.addHiddenParam("ntp100OrderKey2", form.getNtp100OrderKey2());
        cmn999Form.addHiddenParam("ntp100SortKey2", form.getNtp100SortKey2());
        cmn999Form.addHiddenParam("ntp100SvSltGroup", form.getNtp100SvSltGroup());
        cmn999Form.addHiddenParam("ntp100SvSltUser", form.getNtp100SvSltUser());
        cmn999Form.addHiddenParam("ntp100SvSltYearFr", form.getNtp100SvSltYearFr());
        cmn999Form.addHiddenParam("ntp100SvSltMonthFr", form.getNtp100SvSltMonthFr());
        cmn999Form.addHiddenParam("ntp100SvSltDayFr", form.getNtp100SvSltDayFr());
        cmn999Form.addHiddenParam("ntp100SvSltYearTo", form.getNtp100SvSltYearTo());
        cmn999Form.addHiddenParam("ntp100SvSltMonthTo", form.getNtp100SvSltMonthTo());
        cmn999Form.addHiddenParam("ntp100SvSltDayTo", form.getNtp100SvSltDayTo());
        cmn999Form.addHiddenParam("ntp100SvKeyWordkbn", form.getNtp100SvKeyWordkbn());
        cmn999Form.addHiddenParam("ntp100SvKeyValue", form.getNtp100SvKeyValue());
        cmn999Form.addHiddenParam("ntp100SvOrderKey1", form.getNtp100SvOrderKey1());
        cmn999Form.addHiddenParam("ntp100SvSortKey1", form.getNtp100SvSortKey1());
        cmn999Form.addHiddenParam("ntp100SvOrderKey2", form.getNtp100SvOrderKey2());
        cmn999Form.addHiddenParam("ntp100SvSortKey2", form.getNtp100SvSortKey2());
        cmn999Form.addHiddenParam("ntp100SltGroup", form.getNtp100SltGroup());
        cmn999Form.addHiddenParam("ntp100SltUser", form.getNtp100SltUser());
        cmn999Form.addHiddenParam("ntp100SltYearFr", form.getNtp100SltYearFr());
        cmn999Form.addHiddenParam("ntp100SltMonthFr", form.getNtp100SltMonthFr());
        cmn999Form.addHiddenParam("ntp100SltDayFr", form.getNtp100SltDayFr());
        cmn999Form.addHiddenParam("ntp100SltYearTo", form.getNtp100SltYearTo());
        cmn999Form.addHiddenParam("ntp100SltMonthTo", form.getNtp100SltMonthTo());
        cmn999Form.addHiddenParam("ntp100SltDayTo", form.getNtp100SltDayTo());
        cmn999Form.addHiddenParam("ntp100KeyWordkbn", form.getNtp100KeyWordkbn());

        req.setAttribute("cmn999Form", cmn999Form);
    }

    /**
     * <br>登録処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doCommit(
        ActionMapping map,
        Ntp093Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        log__.debug("登録");
        ActionForward forward = null;

        //不正な画面遷移
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        BaseUserModel umodel = getSessionUserModel(req);
        Ntp093Biz biz = new Ntp093Biz(con, getRequestModel(req));

        ActionErrors errors = form.validateCheck(con, umodel.getUsrsid());
        if (!errors.isEmpty()) {
            addErrors(req, errors);

            Ntp093ParamModel paramMdl = new Ntp093ParamModel();
            paramMdl.setParam(form);
            biz.setDspData(paramMdl, con, umodel.getUsrsid());
            paramMdl.setFormData(form);

            return map.getInputForward();
        }

        //DB更新
        Ntp093ParamModel paramMdl = new Ntp093ParamModel();
        paramMdl.setParam(form);
        biz.setPconfSetting(paramMdl, umodel, con);
        paramMdl.setFormData(form);

        GsMessage gsMsg = new GsMessage();
        /** メッセージ 変更 **/
        String change = gsMsg.getMessage(req, "cmn.change");

        //ログ出力処理
        NtpCommonBiz ntpBiz = new NtpCommonBiz(con, getRequestModel(req));
        ntpBiz.outPutLog(
                map, req, res,
                change, GSConstLog.LEVEL_INFO, "");

        //共通メッセージ画面(OK)を表示
        __setCompPageParam(map, req, form);
        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     */
    private void __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Ntp093Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("ntp093back");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "touroku.kanryo.object";
        String key1 = "グループメンバー表示設定";
        cmn999Form.setMessage(msgRes.getMessage(msgState, key1));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("dspMod", form.getDspMod());
        cmn999Form.addHiddenParam("listMod", form.getListMod());
        cmn999Form.addHiddenParam("ntp010DspDate", form.getNtp010DspDate());
        cmn999Form.addHiddenParam("ntp010DspGpSid", form.getNtp010DspGpSid());
        cmn999Form.addHiddenParam("ntp010SelectUsrSid", form.getNtp010SelectUsrSid());
        cmn999Form.addHiddenParam("ntp010SelectUsrKbn", form.getNtp010SelectUsrKbn());
        cmn999Form.addHiddenParam("ntp010SelectDate", form.getNtp010SelectDate());
        cmn999Form.addHiddenParam("ntp020SelectUsrSid", form.getNtp020SelectUsrSid());
        cmn999Form.addHiddenParam("ntp030SelectUsrSid", form.getNtp030SelectUsrSid());

        cmn999Form.addHiddenParam("ntp100PageNum", form.getNtp100PageNum());
        cmn999Form.addHiddenParam("ntp100Slt_page1", form.getNtp100Slt_page1());
        cmn999Form.addHiddenParam("ntp100Slt_page2", form.getNtp100Slt_page2());
        cmn999Form.addHiddenParam("ntp100OrderKey1", form.getNtp100OrderKey1());
        cmn999Form.addHiddenParam("ntp100SortKey1", form.getNtp100SortKey1());
        cmn999Form.addHiddenParam("ntp100OrderKey2", form.getNtp100OrderKey2());
        cmn999Form.addHiddenParam("ntp100SortKey2", form.getNtp100SortKey2());
        cmn999Form.addHiddenParam("ntp100SvSltGroup", form.getNtp100SvSltGroup());
        cmn999Form.addHiddenParam("ntp100SvSltUser", form.getNtp100SvSltUser());
        cmn999Form.addHiddenParam("ntp100SvSltYearFr", form.getNtp100SvSltYearFr());
        cmn999Form.addHiddenParam("ntp100SvSltMonthFr", form.getNtp100SvSltMonthFr());
        cmn999Form.addHiddenParam("ntp100SvSltDayFr", form.getNtp100SvSltDayFr());
        cmn999Form.addHiddenParam("ntp100SvSltYearTo", form.getNtp100SvSltYearTo());
        cmn999Form.addHiddenParam("ntp100SvSltMonthTo", form.getNtp100SvSltMonthTo());
        cmn999Form.addHiddenParam("ntp100SvSltDayTo", form.getNtp100SvSltDayTo());
        cmn999Form.addHiddenParam("ntp100SvKeyWordkbn", form.getNtp100SvKeyWordkbn());
        cmn999Form.addHiddenParam("ntp100SvKeyValue", form.getNtp100SvKeyValue());
        cmn999Form.addHiddenParam("ntp100SvOrderKey1", form.getNtp100SvOrderKey1());
        cmn999Form.addHiddenParam("ntp100SvSortKey1", form.getNtp100SvSortKey1());
        cmn999Form.addHiddenParam("ntp100SvOrderKey2", form.getNtp100SvOrderKey2());
        cmn999Form.addHiddenParam("ntp100SvSortKey2", form.getNtp100SvSortKey2());
        cmn999Form.addHiddenParam("ntp100SltGroup", form.getNtp100SltGroup());
        cmn999Form.addHiddenParam("ntp100SltUser", form.getNtp100SltUser());
        cmn999Form.addHiddenParam("ntp100SltYearFr", form.getNtp100SltYearFr());
        cmn999Form.addHiddenParam("ntp100SltMonthFr", form.getNtp100SltMonthFr());
        cmn999Form.addHiddenParam("ntp100SltDayFr", form.getNtp100SltDayFr());
        cmn999Form.addHiddenParam("ntp100SltYearTo", form.getNtp100SltYearTo());
        cmn999Form.addHiddenParam("ntp100SltMonthTo", form.getNtp100SltMonthTo());
        cmn999Form.addHiddenParam("ntp100SltDayTo", form.getNtp100SltDayTo());
        cmn999Form.addHiddenParam("ntp100KeyWordkbn", form.getNtp100KeyWordkbn());

        req.setAttribute("cmn999Form", cmn999Form);

    }

    /**
     * <br>戻る処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doBack(ActionMapping map, Ntp093Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("戻る");
        ActionForward forward = null;
        forward = map.findForward("ntp093back");
        return forward;
    }

    /**
     * <br>初期表示
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Ntp093Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("初期表示");
        log__.debug("form = " + form);
        ActionForward forward = null;

        Ntp093Biz biz = new Ntp093Biz(con, getRequestModel(req));
        BaseUserModel umodel = getSessionUserModel(req);

        Ntp093ParamModel paramMdl = new Ntp093ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, umodel, con);
        paramMdl.setFormData(form);

        forward = map.getInputForward();
        return forward;
    }
    /**
     * <br>再表示
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doReload(ActionMapping map, Ntp093Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("再表示");
        ActionForward forward = null;

        Ntp093Biz biz = new Ntp093Biz(con, getRequestModel(req));
        BaseUserModel umodel = getSessionUserModel(req);

        Ntp093ParamModel paramMdl = new Ntp093ParamModel();
        paramMdl.setParam(form);
        biz.setReloadData(paramMdl, umodel, con);
        paramMdl.setFormData(form);

        forward = map.getInputForward();
        return forward;
    }
}