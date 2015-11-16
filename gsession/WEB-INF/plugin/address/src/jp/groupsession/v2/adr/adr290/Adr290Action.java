package jp.groupsession.v2.adr.adr290;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.adr.AbstractAddressAction;
import jp.groupsession.v2.adr.AdrCommonBiz;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrAconfDao;
import jp.groupsession.v2.adr.model.AdrAconfModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] アドレス帳 カテゴリ登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr290Action extends AbstractAddressAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr290Action.class);

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

        log__.debug("START_Adr280");
        ActionForward forward = null;

        Adr290Form thisForm = (Adr290Form) form;

        //権限チェック
        forward = checkPow(map, req, con);
        if (forward != null) {
            return forward;
        }

        //カテゴリ「未設定」を編集・削除しようとした場合
        if (thisForm.getAdr280EditSid() == 1) {
            forward = getSubmitErrorPage(map, req);
            return forward;
        }

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("ok")) {
            log__.debug("OKボタンクリック");
            forward = __doOk(map, thisForm, req, res, con);

        } else if (cmd.equals("delete")) {
            log__.debug("削除ボタンクリック");
            forward = __doDeleteCat(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteExe")) {
            log__.debug("削除実行");
            forward = __doDeleteExe(map, thisForm, req, res, con);

        } else if (cmd.equals("input_back")) {
            log__.debug("確認画面から戻る");
            forward = map.getInputForward();

        } else if (cmd.equals("adr290back")) {
            log__.debug("戻るボタンクリック");
            forward = map.findForward("listback");

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END_Adr280");
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
        Adr290Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        //初期表示情報を取得する
        con.setAutoCommit(true);
        Adr290Biz biz = new Adr290Biz(getRequestModel(req));

        Adr290ParamModel paramMdl = new Adr290ParamModel();
        paramMdl.setParam(form);
        biz.getInitData(con, paramMdl);
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] OKボタンクリック時の処理を行う
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
    private ActionForward __doOk(
        ActionMapping map,
        Adr290Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        //入力チェック
        ActionErrors errors = form.validateAdr290(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return map.getInputForward();
        }

        // トランザクショントークン設定
        saveToken(req);

        return map.findForward("kakunin");
    }

    /**
     * <br>[機  能] 削除ボタンクリック時の処理を行う
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
    private ActionForward __doDeleteCat(
        ActionMapping map,
        Adr290Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        //削除チェック
        con.setAutoCommit(true);
        ActionErrors errors = form.deleteCheck(con);
        con.setAutoCommit(false);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return map.getInputForward();
        }

        // トランザクショントークン設定
        saveToken(req);

        //削除確認画面を表示
        return __setKakuninDsp(map, form, req, con);
    }

    /**
     * <br>[機  能] 削除処理を行う(削除実行)
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
    private ActionForward __doDeleteExe(
        ActionMapping map,
        Adr290Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //カテゴリを削除する
        Adr290Biz biz = new Adr290Biz(getRequestModel(req));

        Adr290ParamModel paramMdl = new Adr290ParamModel();
        paramMdl.setParam(form);
        biz.deleteCat(con, paramMdl);
        paramMdl.setFormData(form);

        //ログ出力処理
        AdrCommonBiz usrBiz = new AdrCommonBiz(con);
        GsMessage gsMsg = new GsMessage();
        String opCode = gsMsg.getMessage(req, "cmn.delete");

        usrBiz.outPutLog(
                map, req, res, opCode,
                GSConstLog.LEVEL_TRACE,
                "[name]" + form.getAdr290CategoryName());


        //削除完了画面を表示
        return __setKanryoDsp(map, form, req);
    }

    /**
     * [機  能] 削除完了画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(
        ActionMapping map,
        Adr290Form form,
        HttpServletRequest req) {
        GsMessage gsMsg = new GsMessage();
        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("listback");
        cmn999Form.setUrlOK(forwardOk.getPath());

        MessageResources msgRes = getResources(req);
        //削除完了
        cmn999Form.setMessage(
                msgRes.getMessage("sakujo.kanryo.object", gsMsg.getMessage(req, "cmn.category")));
        //パラメータセット

        cmn999Form.addHiddenParam("adr280EditSid", form.getAdr280EditSid());

        cmn999Form.addHiddenParam("adr010cmdMode", form.getAdr010cmdMode());
        cmn999Form.addHiddenParam("adr010searchFlg", form.getAdr010searchFlg());
        cmn999Form.addHiddenParam("adr010EditAdrSid", form.getAdr010EditAdrSid());
        cmn999Form.addHiddenParam("adr010orderKey", form.getAdr010orderKey());

        form.setHiddenParam(cmn999Form);
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * [機  能] 削除確認画面<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     */
    private ActionForward __setKakuninDsp(
        ActionMapping map,
        Adr290Form form,
        HttpServletRequest req,
        Connection con) throws SQLException {

        int editSid = form.getAdr280EditSid();
        //ラベル一覧を取得
        Adr290Biz biz = new Adr290Biz(getRequestModel(req));
        ArrayList<String> list = biz.getDeleteLabList(con, editSid);
        String delMsg = "";
        boolean labExis = false;

        if (list.size() > 0) {
            //対象のカテゴリにラベルが存在する場合
            for (int i = 0; i < list.size(); i++) {
                delMsg += "・";
                delMsg += StringUtilHtml.transToHTmlPlusAmparsant(
                        NullDefault.getString(list.get(i), ""));

                //最後の要素以外は改行を挿入
                if (i < list.size() - 1) {
                    delMsg += "<br>";
                }
            }
            form.setCatKbn(GSConstAddress.CATEGORY_EXIST_YES);
            labExis = true;
        }

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //キャンセルボタンクリック時遷移先
        ActionForward forwardCancel = map.findForward("adr280back");
        cmn999Form.setUrlCancel(
                forwardCancel.getPath() + "?" + GSConst.P_CMD + "=input_back");

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("deleteok");
        cmn999Form.setUrlOK(forwardOk.getPath() + "?" + GSConst.P_CMD + "=deleteExe");

        //カテゴリ内にラベルが存在する場合
        if (labExis) {
            //メッセージ
            MessageResources msgRes = getResources(req);
            String msg = biz.getDeleteLabAndCatMsg(con, form.getAdr280EditSid(), msgRes, delMsg);
            cmn999Form.setMessage(msg);
        } else {
            //メッセージ
            MessageResources msgRes = getResources(req);
            String msg = biz.getDeletePosMsg(con, form.getAdr280EditSid(), msgRes);
            cmn999Form.setMessage(msg);
        }

        //画面パラメータをセット
        cmn999Form.addHiddenParam("adr280EditSid", form.getAdr280EditSid());

        cmn999Form.addHiddenParam("adr010cmdMode", form.getAdr010cmdMode());
        cmn999Form.addHiddenParam("adr010searchFlg", form.getAdr010searchFlg());
        cmn999Form.addHiddenParam("adr010EditAdrSid", form.getAdr010EditAdrSid());
        cmn999Form.addHiddenParam("adr010orderKey", form.getAdr010orderKey());
        cmn999Form.addHiddenParam("adr010orderKey", form.getAdr010orderKey());
        cmn999Form.addHiddenParam("adr290bikou", form.getAdr290bikou());
        cmn999Form.addHiddenParam("adr290CategoryName", form.getAdr290CategoryName());
        cmn999Form.addHiddenParam("catKbn", form.getCatKbn());
        cmn999Form.addHiddenParam("adr280ProcMode", form.getAdr280ProcMode());
        form.setHiddenParam(cmn999Form);
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
