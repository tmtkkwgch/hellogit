package jp.groupsession.v2.rsv.rsv060;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.rsv.AbstractReserveAction;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 施設予約 施設グループ登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv060Action extends AbstractReserveAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv060Action.class);

    /**
    * <br>[機  能] アクションを実行する
    * <br>[解  説]
    * <br>[備  考]
    * @param map ActionMapping
    * @param form ActionForm
    * @param req HttpServletRequest
    * @param res HttpServletResponse
    * @param con DB Connection
    * @return ActionForward
    * @throws Exception 実行時例外
    * @see jp.co.sjts.util.struts.AbstractAction
    * @see #executeAction(org.apache.struts.action.ActionMapping,
    *                      org.apache.struts.action.ActionForm,
    *                      javax.servlet.http.HttpServletRequest,
    *                      javax.servlet.http.HttpServletResponse,
    *                      java.sql.Connection)
    */
    public ActionForward executeAction(ActionMapping map,
                                        ActionForm form,
                                        HttpServletRequest req,
                                        HttpServletResponse res,
                                        Connection con) throws Exception {

        ActionForward forward = null;
        Rsv060Form rsvform = (Rsv060Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        //OKボタン押下
        if (cmd.equals("sisetu_toroku_kakunin")) {
            log__.debug("OKボタン押下");
            forward = __doConfirmation(map, rsvform, req, res, con);
        //削除ボタン押下
        } else if (cmd.equals("sisetu_sakuzyo_kakunin")) {
            log__.debug("削除ボタン押下");
            forward = __doGrpDeleteCheck(map, rsvform, req, res, con);
        //削除確認画面でOKボタン押下
        } else if (cmd.equals("deleteOk")) {
            log__.debug("削除確認画面でOKボタン押下");
            forward = __doGrpDeleteOk(map, rsvform, req, res, con);
        //戻るボタン押下
        } else if (cmd.equals("back_to_sisetu_group_settei")) {
            log__.debug("戻るボタン押下");
            forward = map.findForward("back_to_sisetu_group_settei");
        //追加ボタン押下
        } else if (cmd.equals("user_tuika")) {
            log__.debug("管理者ユーザ追加ボタン押下");
            forward = __doAdd(map, rsvform, req, res, con);
        //削除ボタン押下
        } else if (cmd.equals("user_sakuzyo")) {
            log__.debug("管理者ユーザ削除ボタン押下");
            forward = __doDel(map, rsvform, req, res, con);
        //アクセス権限左矢印(予約ユーザ追加)ボタンクリック
        } else if (cmd.equals("addMember")) {
            log__.debug("予約ユーザ追加ボタン押下");
            forward = __doAddUser(map, rsvform, req, res, con);
        //アクセス権限右矢印(予約ユーザ削除)ボタンクリック
        } else if (cmd.equals("removeMember")) {
            log__.debug("予約ユーザ削除ボタン押下");
            forward = __doDelUser(map, rsvform, req, res, con);
        //アクセス権限左矢印(閲覧ユーザ追加)ボタンクリック
        } else if (cmd.equals("addMemberRead")) {
            log__.debug("閲覧ユーザ追加ボタン押下");
            forward = __doAddUserRead(map, rsvform, req, res, con);
        //アクセス権限右矢印(閲覧ユーザ削除)ボタンクリック
        } else if (cmd.equals("removeMemberRead")) {
            log__.debug("閲覧ユーザ削除ボタン押下");
            forward = __doDelUserRead(map, rsvform, req, res, con);

        //初期表示処理
        } else {
            log__.debug("初期表示処理");
            forward = __doInit(map, rsvform, req, res, con);
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
                                    Rsv060Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {

        try {
            con.setAutoCommit(true);
            Rsv060Biz biz = new Rsv060Biz(getRequestModel(req), con);

            boolean processFlg = false;

            Rsv060ParamModel paramMdl = new Rsv060ParamModel();
            paramMdl.setParam(form);
            processFlg = biz.isPossibleToProcess(paramMdl);
            paramMdl.setFormData(form);

            //処理権限判定
            if (!processFlg) {
                //処理権限無し
                return getSubmitErrorPage(map, req);
            }

            //画面表示値取得
            paramMdl = new Rsv060ParamModel();
            paramMdl.setParam(form);
            biz.setGroupData(paramMdl);
            paramMdl.setFormData(form);

            con.setAutoCommit(false);
        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        }

        return map.getInputForward();
    }

    /**
     * <br>[機  能] ユーザ追加処理
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
    private ActionForward __doConfirmation(ActionMapping map,
                                            Rsv060Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        //入力チェック
        ActionErrors errors = form.validateCheck(con, req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        saveToken(req);

        return map.findForward("sisetu_toroku_kakunin");
    }

    /**
     * <br>[機  能] ユーザ追加処理
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
    private ActionForward __doAdd(ActionMapping map,
                                   Rsv060Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con) throws Exception {

        //【権限設定を行う】に設定されている
        if (form.getRsv060GrpAdmKbn() == GSConstReserve.RSG_ADM_KBN_OK) {
            Rsv060Biz biz = new Rsv060Biz(getRequestModel(req), con);

            Rsv060ParamModel paramMdl = new Rsv060ParamModel();
            paramMdl.setParam(form);
            biz.doUserAdd(paramMdl);
            paramMdl.setFormData(form);

        }

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] ユーザ削除処理
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
    private ActionForward __doDel(ActionMapping map,
                                   Rsv060Form form,
                                   HttpServletRequest req,
                                   HttpServletResponse res,
                                   Connection con) throws Exception {

        //【権限設定を行う】に設定されている
        if (form.getRsv060GrpAdmKbn() == GSConstReserve.RSG_ADM_KBN_OK) {
            Rsv060Biz biz = new Rsv060Biz(getRequestModel(req), con);

            Rsv060ParamModel paramMdl = new Rsv060ParamModel();
            paramMdl.setParam(form);
            biz.doUserDel(paramMdl);
            paramMdl.setFormData(form);

            //ログ出力処理
            GsMessage gsMsg = new GsMessage();
            AbstractReserveBiz rsvBiz = new AbstractReserveBiz(con);
            rsvBiz.outPutLog(
                    map, req, res, gsMsg.getMessage(req, "cmn.delete"), GSConstLog.LEVEL_TRACE, "");
        }

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除確認画面でOKボタン押下時処理
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
    private ActionForward __doGrpDeleteOk(ActionMapping map,
                                           Rsv060Form form,
                                           HttpServletRequest req,
                                           HttpServletResponse res,
                                           Connection con) throws Exception {

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {

            Rsv060Biz biz = new Rsv060Biz(getRequestModel(req), con);

            //削除処理実行
            Rsv060ParamModel paramMdl = new Rsv060ParamModel();
            paramMdl.setParam(form);
            biz.doGrpDelete(paramMdl);
            paramMdl.setFormData(form);

            //ログ出力処理
            GsMessage gsMsg = new GsMessage();
            AbstractReserveBiz rsvBiz = new AbstractReserveBiz(con);
            rsvBiz.outPutLog(
                    map, req, res, gsMsg.getMessage(req, "cmn.delete"), GSConstLog.LEVEL_TRACE, "");
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }

        return __doDeleteComp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除処理完了後の画面遷移設定
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
    private ActionForward __doDeleteComp(ActionMapping map,
                                          Rsv060Form form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con) throws Exception {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        ActionForward forwardOk = null;

        //メッセージ
        GsMessage gsMsg = new GsMessage();
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
             msgRes.getMessage("sakujo.kanryo.object", gsMsg.getMessage(req, "reserve.148")));

        Rsv060Biz biz = new Rsv060Biz(getRequestModel(req), con);
        if (biz.getGroupEditFlg()) {
            //編集可能な施設有り
            forwardOk = map.findForward("back_to_sisetu_group_settei");
        } else {
            //編集可能な施設無し
            String backPgId =
                NullDefault.getStringZeroLength(
                        form.getRsvBackPgId(), GSConstReserve.DSP_ID_RSV010);

            //予約一覧[週間]画面へ戻る
            if (backPgId.equals(GSConstReserve.DSP_ID_RSV010)) {
                forwardOk = map.findForward("back_to_syukan");
            //予約一覧[日間]画面へ戻る
            } else if (backPgId.equals(GSConstReserve.DSP_ID_RSV020)) {
                forwardOk = map.findForward("back_to_nikkan");
            //予約一覧[月間]画面へ戻る
            } else if (backPgId.equals(GSConstReserve.DSP_ID_RSV030)) {
                forwardOk = map.findForward("back_to_gekkan");
            }
        }

        //OKボタンクリック時遷移先
        cmn999Form.setUrlOK(forwardOk.getPath());

        //画面パラメータをセット
        cmn999Form.addHiddenParam("rsvBackPgId", form.getRsvBackPgId());
        cmn999Form.addHiddenParam("rsvDspFrom", form.getRsvDspFrom());
        cmn999Form.addHiddenParam("rsvSelectedGrpSid", form.getRsvSelectedGrpSid());
        cmn999Form.addHiddenParam("rsvSelectedSisetuSid", form.getRsvSelectedSisetuSid());
        cmn999Form.addHiddenParam("rsv050SortRadio", form.getRsv050SortRadio());
        cmn999Form.addHiddenParam("rsv100InitFlg",
                String.valueOf(form.isRsv100InitFlg()));
        cmn999Form.addHiddenParam("rsv100SearchFlg",
                String.valueOf(form.isRsv100SearchFlg()));
        cmn999Form.addHiddenParam("rsv100SortKey", form.getRsv100SortKey());
        cmn999Form.addHiddenParam("rsv100OrderKey", form.getRsv100OrderKey());
        cmn999Form.addHiddenParam("rsv100PageTop", form.getRsv100PageTop());
        cmn999Form.addHiddenParam("rsv100PageBottom", form.getRsv100PageBottom());
        cmn999Form.addHiddenParam("rsv100selectedFromYear", form.getRsv100selectedFromYear());
        cmn999Form.addHiddenParam("rsv100selectedFromMonth", form.getRsv100selectedFromMonth());
        cmn999Form.addHiddenParam("rsv100selectedFromDay", form.getRsv100selectedFromDay());
        cmn999Form.addHiddenParam("rsv100selectedToYear", form.getRsv100selectedToYear());
        cmn999Form.addHiddenParam("rsv100selectedToMonth", form.getRsv100selectedToMonth());
        cmn999Form.addHiddenParam("rsv100selectedToDay", form.getRsv100selectedToDay());
        cmn999Form.addHiddenParam("rsvIkkatuTorokuKey", form.getRsvIkkatuTorokuKey());
        cmn999Form.addHiddenParam("rsv100KeyWord", form.getRsv100KeyWord());
        cmn999Form.addHiddenParam("rsv100SearchCondition", form.getRsv100SearchCondition());
        cmn999Form.addHiddenParam("rsv100TargetMok", form.getRsv100TargetMok());
        cmn999Form.addHiddenParam("rsv100TargetNiyo", form.getRsv100TargetNiyo());
        cmn999Form.addHiddenParam("rsv100CsvOutField", form.getRsv100CsvOutField());
        cmn999Form.addHiddenParam("rsv100SelectedKey1", form.getRsv100SelectedKey1());
        cmn999Form.addHiddenParam("rsv100SelectedKey2", form.getRsv100SelectedKey2());
        cmn999Form.addHiddenParam("rsv100SelectedKey1Sort", form.getRsv100SelectedKey1Sort());
        cmn999Form.addHiddenParam("rsv100SelectedKey2Sort", form.getRsv100SelectedKey2Sort());
        cmn999Form.addHiddenParam("rsv100svFromYear", form.getRsv100svFromYear());
        cmn999Form.addHiddenParam("rsv100svFromMonth", form.getRsv100svFromMonth());
        cmn999Form.addHiddenParam("rsv100svFromDay", form.getRsv100svFromDay());
        cmn999Form.addHiddenParam("rsv100svToYear", form.getRsv100svToYear());
        cmn999Form.addHiddenParam("rsv100svToMonth", form.getRsv100svToMonth());
        cmn999Form.addHiddenParam("rsv100svToDay", form.getRsv100svToDay());
        cmn999Form.addHiddenParam("rsv100svGrp1", form.getRsv100svGrp1());
        cmn999Form.addHiddenParam("rsv100svGrp2", form.getRsv100svGrp2());
        cmn999Form.addHiddenParam("rsv100svKeyWord", form.getRsv100svKeyWord());
        cmn999Form.addHiddenParam("rsv100svSearchCondition", form.getRsv100svSearchCondition());
        cmn999Form.addHiddenParam("rsv100svTargetMok", form.getRsv100svTargetMok());
        cmn999Form.addHiddenParam("rsv100svTargetNiyo", form.getRsv100svTargetNiyo());
        cmn999Form.addHiddenParam("rsv100svSelectedKey1", form.getRsv100svSelectedKey1());
        cmn999Form.addHiddenParam("rsv100svSelectedKey2", form.getRsv100svSelectedKey2());
        cmn999Form.addHiddenParam("rsv100svSelectedKey1Sort", form.getRsv100svSelectedKey1Sort());
        cmn999Form.addHiddenParam("rsv100svSelectedKey2Sort", form.getRsv100svSelectedKey2Sort());
        cmn999Form.addHiddenParam("rsv100SearchSvFlg",
                String.valueOf(form.isRsv100SearchSvFlg()));

        cmn999Form.addHiddenParam("rsv100dateKbn", form.getRsv100dateKbn());
        cmn999Form.addHiddenParam("rsv100apprStatus", form.getRsv100apprStatus());
        cmn999Form.addHiddenParam("rsv100svDateKbn", form.getRsv100svDateKbn());
        cmn999Form.addHiddenParam("rsv100svApprStatus", form.getRsv100svApprStatus());
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 削除ボタン押下時確認画面表示処理
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
    private ActionForward __doGrpDeleteCheck(ActionMapping map,
                                              Rsv060Form form,
                                              HttpServletRequest req,
                                              HttpServletResponse res,
                                              Connection con) throws Exception {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("redraw");
        cmn999Form.setUrlOK(forwardOk.getPath() + "?" + GSConst.P_CMD + "=deleteOk");

        //キャンセルボタンクリック時遷移先
        cmn999Form.setUrlCancel(forwardOk.getPath());

        Rsv060Biz biz = new Rsv060Biz(getRequestModel(req), con);
        Rsv060ParamModel paramMdl = new Rsv060ParamModel();
        paramMdl.setParam(form);
        String grpName = biz.getGroupName(paramMdl);
        paramMdl.setFormData(form);


        //メッセージ
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
             msgRes.getMessage("delete.kakunin.group",
                     StringUtilHtml.transToHTmlPlusAmparsant(grpName)));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("rsvBackPgId", form.getRsvBackPgId());
        cmn999Form.addHiddenParam("rsvDspFrom", form.getRsvDspFrom());
        cmn999Form.addHiddenParam("rsvSelectedGrpSid", form.getRsvSelectedGrpSid());
        cmn999Form.addHiddenParam("rsvSelectedSisetuSid", form.getRsvSelectedSisetuSid());
        cmn999Form.addHiddenParam("rsv050SortRadio", form.getRsv050SortRadio());
        cmn999Form.addHiddenParam("rsv060InitFlg", String.valueOf(form.isRsv060InitFlg()));
        cmn999Form.addHiddenParam("rsv060ProcMode", form.getRsv060ProcMode());
        cmn999Form.addHiddenParam("rsv060EditGrpSid", form.getRsv060EditGrpSid());
        cmn999Form.addHiddenParam("saveUser", form.getSaveUser());
        cmn999Form.addHiddenParam("rsv060GrpName", form.getRsv060GrpName());
        cmn999Form.addHiddenParam("rsv060SelectedSisetuKbn", form.getRsv060SelectedSisetuKbn());
        cmn999Form.addHiddenParam("rsv060GrpAdmKbn", form.getRsv060GrpAdmKbn());
        cmn999Form.addHiddenParam("rsv060SelectedGrpComboSid", form.getRsv060SelectedGrpComboSid());
        cmn999Form.addHiddenParam("rsv060GrpId", form.getRsv060GrpId());
        cmn999Form.addHiddenParam("rsv100InitFlg",
                String.valueOf(form.isRsv100InitFlg()));
        cmn999Form.addHiddenParam("rsv100SearchFlg",
                String.valueOf(form.isRsv100SearchFlg()));
        cmn999Form.addHiddenParam("rsv100SortKey", form.getRsv100SortKey());
        cmn999Form.addHiddenParam("rsv100OrderKey", form.getRsv100OrderKey());
        cmn999Form.addHiddenParam("rsv100PageTop", form.getRsv100PageTop());
        cmn999Form.addHiddenParam("rsv100PageBottom", form.getRsv100PageBottom());
        cmn999Form.addHiddenParam("rsv100selectedFromYear", form.getRsv100selectedFromYear());
        cmn999Form.addHiddenParam("rsv100selectedFromMonth", form.getRsv100selectedFromMonth());
        cmn999Form.addHiddenParam("rsv100selectedFromDay", form.getRsv100selectedFromDay());
        cmn999Form.addHiddenParam("rsv100selectedToYear", form.getRsv100selectedToYear());
        cmn999Form.addHiddenParam("rsv100selectedToMonth", form.getRsv100selectedToMonth());
        cmn999Form.addHiddenParam("rsv100selectedToDay", form.getRsv100selectedToDay());
        cmn999Form.addHiddenParam("rsv100KeyWord", form.getRsv100KeyWord());
        cmn999Form.addHiddenParam("rsv100SearchCondition", form.getRsv100SearchCondition());
        cmn999Form.addHiddenParam("rsv100TargetMok", form.getRsv100TargetMok());
        cmn999Form.addHiddenParam("rsv100TargetNiyo", form.getRsv100TargetNiyo());
        cmn999Form.addHiddenParam("rsv100CsvOutField", form.getRsv100CsvOutField());
        cmn999Form.addHiddenParam("rsv100SelectedKey1", form.getRsv100SelectedKey1());
        cmn999Form.addHiddenParam("rsv100SelectedKey2", form.getRsv100SelectedKey2());
        cmn999Form.addHiddenParam("rsv100SelectedKey1Sort", form.getRsv100SelectedKey1Sort());
        cmn999Form.addHiddenParam("rsv100SelectedKey2Sort", form.getRsv100SelectedKey2Sort());
        cmn999Form.addHiddenParam("rsvIkkatuTorokuKey", form.getRsvIkkatuTorokuKey());
        cmn999Form.addHiddenParam("rsv100svFromYear", form.getRsv100svFromYear());
        cmn999Form.addHiddenParam("rsv100svFromMonth", form.getRsv100svFromMonth());
        cmn999Form.addHiddenParam("rsv100svFromDay", form.getRsv100svFromDay());
        cmn999Form.addHiddenParam("rsv100svToYear", form.getRsv100svToYear());
        cmn999Form.addHiddenParam("rsv100svToMonth", form.getRsv100svToMonth());
        cmn999Form.addHiddenParam("rsv100svToDay", form.getRsv100svToDay());
        cmn999Form.addHiddenParam("rsv100svGrp1", form.getRsv100svGrp1());
        cmn999Form.addHiddenParam("rsv100svGrp2", form.getRsv100svGrp2());
        cmn999Form.addHiddenParam("rsv100svKeyWord", form.getRsv100svKeyWord());
        cmn999Form.addHiddenParam("rsv100svSearchCondition", form.getRsv100svSearchCondition());
        cmn999Form.addHiddenParam("rsv100svTargetMok", form.getRsv100svTargetMok());
        cmn999Form.addHiddenParam("rsv100svTargetNiyo", form.getRsv100svTargetNiyo());
        cmn999Form.addHiddenParam("rsv100svSelectedKey1", form.getRsv100svSelectedKey1());
        cmn999Form.addHiddenParam("rsv100svSelectedKey2", form.getRsv100svSelectedKey2());
        cmn999Form.addHiddenParam("rsv100svSelectedKey1Sort", form.getRsv100svSelectedKey1Sort());
        cmn999Form.addHiddenParam("rsv100svSelectedKey2Sort", form.getRsv100svSelectedKey2Sort());
        cmn999Form.addHiddenParam("rsv100SearchSvFlg",
                String.valueOf(form.isRsv100SearchSvFlg()));

        cmn999Form.addHiddenParam("rsv100dateKbn", form.getRsv100dateKbn());
        cmn999Form.addHiddenParam("rsv100apprStatus", form.getRsv100apprStatus());
        cmn999Form.addHiddenParam("rsv100svDateKbn", form.getRsv100svDateKbn());
        cmn999Form.addHiddenParam("rsv100svApprStatus", form.getRsv100svApprStatus());
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

//    /**
//     * <br>[機  能] データ取得失敗時の画面遷移設定
//     * <br>[解  説]
//     * <br>[備  考]
//     *
//     * @param map マップ
//     * @param form フォーム
//     * @param req リクエスト
//     * @param res レスポンス
//     * @param con コネクション
//     * @return ActionForward フォワード
//     */
//    private ActionForward __doDataNotFound(ActionMapping map,
//                                            Rsv060Form form,
//                                            HttpServletRequest req,
//                                            HttpServletResponse res,
//                                            Connection con) {
//
//        Cmn999Form cmn999Form = new Cmn999Form();
//        cmn999Form.setType(Cmn999Form.TYPE_OK);
//        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
//        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
//
//        //OKボタンクリック時遷移先
//        ActionForward forwardOk = map.findForward("back_to_sisetu_group_settei");
//        cmn999Form.setUrlOK(forwardOk.getPath());
//
//        cmn999Form.addHiddenParam("rsvDspFrom", form.getRsvDspFrom());
//        cmn999Form.addHiddenParam("rsvSelectedGrpSid", form.getRsvSelectedGrpSid());
//        cmn999Form.addHiddenParam("rsvSelectedSisetuSid", form.getRsvSelectedSisetuSid());
//        cmn999Form.addHiddenParam("rsv050SortRadio", form.getRsv050SortRadio());
//
//        //メッセージ
//        MessageResources msgRes = getResources(req);
//        cmn999Form.setMessage(msgRes.getMessage("error.notfount.group"));
//
//        req.setAttribute("cmn999Form", cmn999Form);
//        return map.findForward("gf_msg");
//    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
     * <br>[解  説] 編集メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return ActionForward
     */
    private ActionForward __doAddUser(
        ActionMapping map,
        Rsv060Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        AbstractReserveBiz biz = new AbstractReserveBiz();
        form.setRsv060memberSid(
                biz.getAddMember(form.getRsv060SelectRightUser(), form.getRsv060memberSid()));

        return  __doInit(map, form, req, res, con);

    }

    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
     * <br>[解  説] 編集メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return ActionForward
     */
    private ActionForward __doDelUser(
        ActionMapping map,
        Rsv060Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        AbstractReserveBiz biz = new AbstractReserveBiz();
        form.setRsv060memberSid(
                biz.getDeleteMember(form.getRsv060SelectLeftUser(), form.getRsv060memberSid()));

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
     * <br>[解  説] 閲覧メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return ActionForward
     */
    private ActionForward __doAddUserRead(
        ActionMapping map,
        Rsv060Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        AbstractReserveBiz biz = new AbstractReserveBiz();
        form.setRsv060memberSidRead(
                biz.getAddMember(form.getRsv060SelectRightUser(), form.getRsv060memberSidRead()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
     * <br>[解  説] 閲覧メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行例外
     * @return ActionForward
     */
    private ActionForward __doDelUserRead(
        ActionMapping map,
        Rsv060Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        AbstractReserveBiz biz = new AbstractReserveBiz();
        form.setRsv060memberSidRead(
                biz.getDeleteMember(
                        form.getRsv060SelectLeftUserRead(),
                        form.getRsv060memberSidRead()));

        return __doInit(map, form, req, res, con);
    }

}