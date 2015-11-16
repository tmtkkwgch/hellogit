package jp.groupsession.v2.tcd.tcd070;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.tcd.AbstractTimecardAction;
import jp.groupsession.v2.tcd.TimecardBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] タイムカード 管理者設定 時間帯設定登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd070Action extends AbstractTimecardAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd070Action.class);

    /**
     * <br>[機  能] 管理者以外のアクセスを許可するのか判定を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param form フォーム
     * @return boolean true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        return false;
    }

    /**
     * <br>[機  能] アクションを実行する
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

        log__.debug("時間帯設定登録画");
        ActionForward forward = null;
        Tcd070Form schForm = (Tcd070Form) form;
        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        if (cmd.equals("tcd070ok")) {
            //登録 確認
            forward = __doKakunin(map, schForm, req, res, con);
        } else if (cmd.equals("tcd070commit")) {
            log__.debug("登録処理実行");
            //登録実行
            forward = __doCommit(map, schForm, req, res, con);
        } else if (cmd.equals("tcd070delete")) {
            //削除 確認
            forward = __doDelete(map, schForm, req, res, con);
        } else if (cmd.equals("tcd070deleteOk")) {
            //削除実行
            forward = __doDeleteOk(map, schForm, req, res, con);
        } else if (cmd.equals("tcd070back")) {
            //戻る
            forward = __doBack(map, schForm, req, res, con);
        } else {
            //デフォルト
            forward = __doInit(map, schForm, req, res, con);
        }
        return forward;
    }

    /**
     * <br>[機  能] 登録確認処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doKakunin(ActionMapping map, Tcd070Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {
        con.setAutoCommit(true);
        ActionErrors errors = form.validateCheck(getRequestModel(req), con);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        //共通メッセージ画面を表示
        __setKakuninPageParam(map, req, form);

        //トランザクショントークン設定
        saveToken(req);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 削除確認処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDelete(ActionMapping map, Tcd070Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        //共通メッセージ画面を表示
        __setDeleteKakuninPageParam(map, req, form);

        //トランザクショントークン設定
        saveToken(req);

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
        Tcd070Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();

        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        cmn999Form.setUrlOK(map.findForward("tcd070commit").getPath());
        cmn999Form.setUrlCancel(map.findForward("tcd070cancel").getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "tcd.146");

        //メッセージセット
        String msgState = "edit.kakunin.once";
        String mkey1 = msg;
        cmn999Form.setMessage(msgRes.getMessage(msgState, mkey1));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("tcd070ZoneKbn", form.getTcd070ZoneKbn());
        cmn999Form.addHiddenParam("tcd070FrH", form.getTcd070FrH());
        cmn999Form.addHiddenParam("tcd070FrM", form.getTcd070FrM());
        cmn999Form.addHiddenParam("tcd070ToH", form.getTcd070ToH());
        cmn999Form.addHiddenParam("tcd070ToM", form.getTcd070ToM());
        cmn999Form.addHiddenParam("addTimezoneKbn", form.getAddTimezoneKbn());
        cmn999Form.addHiddenParam("editTimezoneSid", form.getEditTimezoneSid());

        cmn999Form.addHiddenParam("year", form.getYear());
        cmn999Form.addHiddenParam("month", form.getMonth());
        cmn999Form.addHiddenParam("tcdDspFrom", form.getTcdDspFrom());

        cmn999Form.addHiddenParam("usrSid", form.getUsrSid());
        cmn999Form.addHiddenParam("usrKbn", form.getUsrKbn());
        cmn999Form.addHiddenParam("selectDay", form.getSelectDay());

        req.setAttribute("cmn999Form", cmn999Form);
    }

    /**
     * <br>[機  能] 削除確認メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     */
    private void __setDeleteKakuninPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Tcd070Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        cmn999Form.setUrlOK(map.findForward("tcd070deleteOk").getPath());
        cmn999Form.setUrlCancel(map.findForward("tcd070cancel").getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "tcd.147");

        //メッセージセット
        String msgState = "sakujo.kakunin.once";
        String mkey1 = msg;
        cmn999Form.setMessage(msgRes.getMessage(msgState, mkey1));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("tcd070ZoneKbn", form.getTcd070ZoneKbn());
        cmn999Form.addHiddenParam("tcd070FrH", form.getTcd070FrH());
        cmn999Form.addHiddenParam("tcd070FrM", form.getTcd070FrM());
        cmn999Form.addHiddenParam("tcd070ToH", form.getTcd070ToH());
        cmn999Form.addHiddenParam("tcd070ToM", form.getTcd070ToM());
        cmn999Form.addHiddenParam("addTimezoneKbn", form.getAddTimezoneKbn());
        cmn999Form.addHiddenParam("editTimezoneSid", form.getEditTimezoneSid());

        cmn999Form.addHiddenParam("year", form.getYear());
        cmn999Form.addHiddenParam("month", form.getMonth());
        cmn999Form.addHiddenParam("tcdDspFrom", form.getTcdDspFrom());

        cmn999Form.addHiddenParam("usrSid", form.getUsrSid());
        cmn999Form.addHiddenParam("usrKbn", form.getUsrKbn());
        cmn999Form.addHiddenParam("selectDay", form.getSelectDay());

        req.setAttribute("cmn999Form", cmn999Form);
    }

    /**
     * <br>[機  能] 登録処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws Exception 実行例外
     */
    private ActionForward __doCommit(ActionMapping map, Tcd070Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {
        log__.debug("更新");
        ActionForward forward = null;

        //２重投稿チェック
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //DB更新
        BaseUserModel umodel = getSessionUserModel(req);
        boolean commit = false;
        try {
            //採番マスタから時間帯SIDを取得
            MlCountMtController cntCon = getCountMtController(req);

            Tcd070ParamModel paramMdl = new Tcd070ParamModel();
            paramMdl.setParam(form);
            Tcd070Biz biz = new Tcd070Biz();
            biz.commitTimeZone(paramMdl, umodel.getUsrsid(), cntCon, con);
            paramMdl.setFormData(form);

            commit = true;
        } catch (SQLException e) {
            log__.error("タイムカード時間帯設定の更新に失敗しました。" + e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        RequestModel reqMdl = getRequestModel(req);

        //ログ出力
        String msg = "";
        String opCode = "";
        GsMessage gsMsg = new GsMessage(reqMdl);
        if (!StringUtil.isNullZeroStringSpace(form.getAddTimezoneKbn())) {
            msg = gsMsg.getMessage("cmn.entry");
            //登録
            opCode = msg;
        } else {
            msg = gsMsg.getMessage("cmn.edit");
            //更新
            opCode = msg;
        }
        TimecardBiz tcdBiz = new TimecardBiz(reqMdl);
        tcdBiz.outPutTimecardLog(map, reqMdl, con,
                opCode, GSConstLog.LEVEL_INFO, "");

        //共通メッセージ画面(OK)を表示
        __setCompPageParam(map, req, form);
        forward = map.findForward("gf_msg");

        return forward;
    }

    /**
     * <br>[機  能] 削除処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDeleteOk(ActionMapping map, Tcd070Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {
        log__.debug("更新");
        ActionForward forward = null;

        //２重投稿チェック
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //DB更新
        BaseUserModel umodel = getSessionUserModel(req);
        boolean commit = false;
        try {

            Tcd070ParamModel paramMdl = new Tcd070ParamModel();
            paramMdl.setParam(form);
            Tcd070Biz biz = new Tcd070Biz();
            biz.deleteTimeZone(paramMdl, umodel.getUsrsid(), con);
            paramMdl.setFormData(form);

            commit = true;
        } catch (SQLException e) {
            log__.error("タイムカード時間帯設定の削除に失敗しました。" + e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String delete = gsMsg.getMessage("cmn.delete");

        //ログ出力
        TimecardBiz cBiz = new TimecardBiz(reqMdl);
        cBiz.outPutTimecardLog(map, reqMdl, con, delete, GSConstLog.LEVEL_INFO, "");

        //共通メッセージ画面(OK)を表示
        __setDeleteCompPageParam(map, req, form);
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
        Tcd070Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("back");
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "tcd.47");

        //メッセージセット
        String msgState = "touroku.kanryo.object";
        String key1 = msg;
        cmn999Form.setMessage(msgRes.getMessage(msgState, key1));
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("year", form.getYear());
        cmn999Form.addHiddenParam("month", form.getMonth());
        cmn999Form.addHiddenParam("tcdDspFrom", form.getTcdDspFrom());

        cmn999Form.addHiddenParam("usrSid", form.getUsrSid());
        cmn999Form.addHiddenParam("usrKbn", form.getUsrKbn());
        cmn999Form.addHiddenParam("selectDay", form.getSelectDay());

        req.setAttribute("cmn999Form", cmn999Form);

    }

    /**
     * <br>[機  能] 削除完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     */
    private void __setDeleteCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Tcd070Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("back");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        GsMessage gsMsg = new GsMessage();
        String msgState = "sakujo.kanryo.object";
        String key1 = gsMsg.getMessage(req, "tcd.166");
        cmn999Form.setMessage(msgRes.getMessage(msgState, key1));
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("year", form.getYear());
        cmn999Form.addHiddenParam("month", form.getMonth());
        cmn999Form.addHiddenParam("tcdDspFrom", form.getTcdDspFrom());

        cmn999Form.addHiddenParam("usrSid", form.getUsrSid());
        cmn999Form.addHiddenParam("usrKbn", form.getUsrKbn());
        cmn999Form.addHiddenParam("selectDay", form.getSelectDay());

        req.setAttribute("cmn999Form", cmn999Form);
    }

    /**
     * <br>[機  能] 戻る処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doBack(ActionMapping map, Tcd070Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        return map.findForward("back");
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Tcd070Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("初期表示");

        con.setAutoCommit(true);
        Tcd070ParamModel paramMdl = new Tcd070ParamModel();
        paramMdl.setParam(form);
        Tcd070Biz biz = new Tcd070Biz();
        biz.setInitData(paramMdl, getRequestModel(req), con);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);
        return map.getInputForward();
    }
}
