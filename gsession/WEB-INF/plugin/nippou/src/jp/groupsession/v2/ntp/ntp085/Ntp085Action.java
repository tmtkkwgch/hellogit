package jp.groupsession.v2.ntp.ntp085;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.ntp.AbstractNippouAdminAction;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 日報 ショートメール通知設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp085Action extends AbstractNippouAdminAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp085Action.class);

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

        Ntp085Form ntpForm = (Ntp085Form) form;
        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        if (cmd.equals("ntp085ok")) {
            //OKボタンクリック
            forward = __doOk(map, ntpForm, req, res, con);
        } else if (cmd.equals("ntp085back")) {
            //戻るボタンクリック
            forward = __doBack(map, ntpForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, ntpForm, req, res, con);
        }
        return forward;
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
    private ActionForward __doOk(ActionMapping map, Ntp085Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("削除処理");

        if (!__canUseSmlConf(form, req, con)) {
            return getSubmitErrorPage(map, req);
        }
        boolean commit = false;
        //DB更新
        try {
            BaseUserModel umodel = getSessionUserModel(req);
            Ntp085Biz biz = new Ntp085Biz(con, getRequestModel(req));

            Ntp085ParamModel paramMdl = new Ntp085ParamModel();
            paramMdl.setParam(form);
            biz.setAconfSetting(paramMdl, umodel, con);
            paramMdl.setFormData(form);

            GsMessage gsMsg = new GsMessage();
            /** メッセージ 変更 **/
            String change = gsMsg.getMessage(req, "cmn.change");

            //ログ出力処理
            NtpCommonBiz ntpBiz = new NtpCommonBiz(con, getRequestModel(req));
            ntpBiz.outPutLog(
                    map, req, res,
                    change, GSConstLog.LEVEL_INFO, "");

            commit = true;
        } catch (SQLException e) {
            log__.error("日報の削除に失敗しました。" + e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }

        //共通メッセージ画面(OKオンリー)を表示
        __setCompPageParam(map, req, form);
        return map.findForward("gf_msg");
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
        Ntp085Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("ktool");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "settei.kanryo.object";
        String key1 = "ショートメール通知";
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
        cmn999Form.addHiddenParam("ntp030SelectUsrSid", form.getNtp020SelectUsrSid());

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
    private ActionForward __doBack(ActionMapping map, Ntp085Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {
        log__.debug("戻る");
        return map.findForward("ktool");
    }

    /**
     * <br>初期表処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Ntp085Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        log__.debug("初期表示");
        if (!__canUseSmlConf(form, req, con)) {
            return getSubmitErrorPage(map, req);
        }
        Ntp085Biz biz = new Ntp085Biz(con, getRequestModel(req));

        Ntp085ParamModel paramMdl = new Ntp085ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, con);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }
    /**
     * <br>[機  能] ショートメール設定が利用可能か判定
     * <br>[解  説]
     * <br>[備  考]
     * @param form フォーム
     * @param req リクエスト
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return true ショートメール使用可能
     *
     */
    private boolean __canUseSmlConf(Ntp085Form form, HttpServletRequest req, Connection con)
    throws SQLException {

        //プラグイン設定を取得する
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));
        CommonBiz cmnBiz = new CommonBiz();
        //ショートメールは利用可能か判定
        return cmnBiz.isCanUsePlugin(GSConst.PLUGINID_SML, pconfig);
    }

}