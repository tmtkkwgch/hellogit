package jp.groupsession.v2.ntp.ntp061kn;

import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.ntp.AbstractNippouAction;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.dao.NtpAnMemberDao;
import jp.groupsession.v2.ntp.dao.NtpAnMemberHistoryDao;
import jp.groupsession.v2.ntp.dao.NtpAnShohinDao;
import jp.groupsession.v2.ntp.dao.NtpAnShohinHistoryDao;
import jp.groupsession.v2.ntp.dao.NtpAnkenDao;
import jp.groupsession.v2.ntp.dao.NtpAnkenHistoryDao;
import jp.groupsession.v2.ntp.model.NtpAnMemberHistoryModel;
import jp.groupsession.v2.ntp.model.NtpAnMemberModel;
import jp.groupsession.v2.ntp.model.NtpAnShohinHistoryModel;
import jp.groupsession.v2.ntp.model.NtpAnShohinModel;
import jp.groupsession.v2.ntp.model.NtpAnkenHistoryModel;
import jp.groupsession.v2.ntp.model.NtpAnkenModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 日報 案件登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp061knAction extends AbstractNippouAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp061knAction.class);

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
                                        Connection con)
        throws Exception {

        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);

        Ntp061knForm thisForm = (Ntp061knForm) form;
        log__.debug("*****ntp061ReturnPage = " + thisForm.getNtp061ReturnPage());

        if (cmd.equals("backNtp061kn")) {
            forward = map.findForward("ntp061");
        } else if (cmd.equals("backNtp200")) {
            forward = map.findForward("ntp200");
        } else if (cmd.equals("addOk")) {
            forward = __doRegistOk(map, thisForm, req, res, con);
        } else if (cmd.equals("addOkPop")) {
            forward = __doRegistOkPop(map, thisForm, req, res, con, 0);
        } else if (cmd.equals("addOkPopNtp")) {
            forward = __doRegistOkPop(map, thisForm, req, res, con, 1);
        } else {
            log__.debug("*****初期表示");
            forward = __doInit(map, thisForm, req, res, con);
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
                                    Ntp061knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {

        BaseUserModel buMdl = getSessionUserModel(req);

        // トランザクショントークン設定
        saveToken(req);

        if (NullDefault.getString(req.getParameter("CMD"), "").equals(GSConstNippou.POP_UP)) {
            //ポップアップ区分
            form.setNtp061PopKbn(1);
        }

        Ntp061knBiz biz = new Ntp061knBiz(
                con, getCountMtController(req), getRequestModel(req));

        Ntp061knParamModel paramMdl = new Ntp061knParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, buMdl, con);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 登録確認画面でOKボタン押下
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doRegistOk(ActionMapping map,
        Ntp061knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con)
        throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        boolean commitFlg = false;
        con.setAutoCommit(false);

        try {
            int usrSid = this.getSessionUserModel(req).getUsrsid();
            NtpAnkenDao ankenDao = new NtpAnkenDao(con);
            NtpAnkenModel ankenMdl = __createNtpAnken(usrSid);

            String mitumori = NullDefault.getStringZeroLength(
                    form.getNtp061NanKinMitumori().replaceAll(",", ""), "0");
            String jutyu = NullDefault.getStringZeroLength(
                    form.getNtp061NanKinJutyu().replaceAll(",", ""), "0");
            String acoSid
                = NullDefault.getStringZeroLength(form.getNtp061CompanySid(), "-1");
            String abaSid
                = NullDefault.getStringZeroLength(form.getNtp061CompanyBaseSid(), "-1");

            UDate date = new UDate();
            date.setZeroHhMmSs();

            ankenMdl.setNanDate(date);
            ankenMdl.setNanCode(form.getNtp061NanCode());
            ankenMdl.setNanName(form.getNtp061NanName());
            ankenMdl.setNanDetial(form.getNtp061NanSyosai());
            ankenMdl.setAcoSid(Integer.parseInt(acoSid));
            ankenMdl.setAbaSid(Integer.parseInt(abaSid));
            ankenMdl.setNgpSid(form.getNtp061NgpSid());
            ankenMdl.setNanMikomi(form.getNtp061NanMikomi());
            ankenMdl.setNanKinMitumori(Integer.parseInt(mitumori));
            ankenMdl.setNanKinJutyu(Integer.parseInt(jutyu));
            ankenMdl.setNanSyodan(form.getNtp061NanSyodan());
            ankenMdl.setNanState(form.getNtp061NanState());
            ankenMdl.setNcnSid(form.getNtp061NcnSid());


            UDate mitumoriDate = new UDate();
            mitumoriDate.setDate(
                    Integer.parseInt(form.getNtp061MitumoriYear()),
                    Integer.parseInt(form.getNtp061MitumoriMonth()),
                    Integer.parseInt(form.getNtp061MitumoriDay()));
            mitumoriDate.setZeroHhMmSs();
            UDate jutyuDate = new UDate();
            jutyuDate.setDate(
                    Integer.parseInt(form.getNtp061JutyuYear()),
                    Integer.parseInt(form.getNtp061JutyuMonth()),
                    Integer.parseInt(form.getNtp061JutyuDay()));
            jutyuDate.setZeroHhMmSs();
            ankenMdl.setNanMitumoriDate(mitumoriDate);
            ankenMdl.setNanJutyuDate(jutyuDate);


            int nanSid = -1;

            MlCountMtController cntCon = getCountMtController(req);

            if (form.getNtp060ProcMode().equals(GSConstNippou.CMD_ADD)) {
                //追加モード

                //SID採番
                nanSid = (int) cntCon.getSaibanNumber(GSConstNippou.SBNSID_NIPPOU,
                    GSConstNippou.SBNSID_SUB_ANKEN, usrSid);
                ankenMdl.setNanSid(nanSid);
                ankenDao.insert(ankenMdl);
            } else {
                nanSid = form.getNtp060NanSid();
                //変更モード
                ankenMdl.setNanSid(nanSid);
                ankenDao.update(ankenMdl);
            }

            //案件商品情報の登録
            NtpAnShohinDao anShohinDao = new NtpAnShohinDao(con);
            if (form.getNtp060ProcMode().equals(GSConstNippou.CMD_EDIT)) {
                //変更モード
                anShohinDao.delete(nanSid);
            }

            NtpAnShohinModel anShohinMdl = null;
            if (form.getNtp061ChkShohinSidList() != null
                && form.getNtp061ChkShohinSidList().length > 0) {
                for (String shohinSid : form.getNtp061ChkShohinSidList()) {
                    anShohinMdl = __createNtpAnShohin(usrSid);
                    anShohinMdl.setNhnSid(Integer.parseInt(shohinSid));
                    anShohinMdl.setNanSid(nanSid);
                    anShohinDao.insert(anShohinMdl);
                }
            }



            //担当者
            NtpAnMemberDao anMemberDao = new NtpAnMemberDao(con);
            if (form.getNtp060ProcMode().equals(GSConstNippou.CMD_EDIT)) {
                //変更モード
                anMemberDao.delete(nanSid);
            }
            NtpAnMemberModel anMemberMdl = null;
            String[] svUsers = form.getSv_users();
            if (svUsers != null) {
                for (int i = 0; i < svUsers.length; i++) {
                    if (GSValidateUtil.isNumber(svUsers[i])) {
                        anMemberMdl = __createNtpAnMember(usrSid);
                        anMemberMdl.setUsrSid(Integer.parseInt(svUsers[i]));
                        anMemberMdl.setNanSid(nanSid);
                        anMemberDao.insert(anMemberMdl);
                    }
                }
            }


            GsMessage gsMsg = new GsMessage();
            String entry = gsMsg.getMessage(req, "cmn.entry");
            String change = gsMsg.getMessage(req, "cmn.change");

            NtpAnkenHistoryDao hisDao = new NtpAnkenHistoryDao(con);
            NtpAnShohinHistoryDao shohinHisDao = new NtpAnShohinHistoryDao(con);
            NtpAnMemberHistoryDao memberHisDao = new NtpAnMemberHistoryDao(con);

            //今日の履歴があるかないか
            int nahSid = -1;
            nahSid = hisDao.checkData(nanSid, date);

            NtpAnkenHistoryModel hisMdl = new NtpAnkenHistoryModel();
            BeanUtils.copyProperties(hisMdl, ankenMdl);
            UDate nanMonth = new UDate();
            nanMonth.setYear(hisMdl.getNanDate().getYear());
            nanMonth.setMonth(hisMdl.getNanDate().getMonth());
            nanMonth.setDay(nanMonth.getMaxDayOfMonth());
            nanMonth.setZeroHhMmSs();
            hisMdl.setNanMonth(nanMonth);

            //履歴は見積もり金額、受注金額を0に設定
            hisMdl.setNanKinJutyu(0);
            hisMdl.setNanKinMitumori(0);

            if (nahSid == -1) {
                //履歴の新規登録

                //履歴SID採番
                nahSid = (int) cntCon.getSaibanNumber(GSConstNippou.SBNSID_NIPPOU,
                    GSConstNippou.SBNSID_SUB_ANKEN_HISTORY, usrSid);
                hisMdl.setNahSid(nahSid);
                hisDao.insert(hisMdl);

                if (anShohinMdl != null) {
                    NtpAnShohinHistoryModel shohinHisMdl = new NtpAnShohinHistoryModel();
                    if (form.getNtp061ChkShohinSidList() != null
                            && form.getNtp061ChkShohinSidList().length > 0) {
                        for (String shohinSid : form.getNtp061ChkShohinSidList()) {
                            shohinHisMdl = __createNtpAnShohinHistory(usrSid);
                            shohinHisMdl.setNahSid(nahSid);
                            shohinHisMdl.setNhnSid(Integer.parseInt(shohinSid));
                            shohinHisMdl.setNanSid(nanSid);
                            shohinHisDao.insert(shohinHisMdl);
                        }
                    }
                }

                if (anMemberMdl != null) {
                    NtpAnMemberHistoryModel memberHisMdl = new NtpAnMemberHistoryModel();
                    if (form.getSv_users() != null
                            && form.getSv_users().length > 0) {
                        for (String uSid : form.getSv_users()) {
                            if (GSValidateUtil.isNumber(uSid)) {
                                memberHisMdl = __createNtpAnMemberHistory(usrSid);
                                memberHisMdl.setNahSid(nahSid);
                                memberHisMdl.setUsrSid(Integer.parseInt(uSid));
                                memberHisMdl.setNanSid(nanSid);
                                memberHisDao.insert(memberHisMdl);
                            }
                        }
                    }
                }
            } else {
                //履歴の更新
                hisMdl.setNahSid(nahSid);
                hisDao.update(hisMdl);
                shohinHisDao.delete(nahSid);
                if (anShohinMdl != null) {
                    NtpAnShohinHistoryModel shohinHisMdl = new NtpAnShohinHistoryModel();
                    if (form.getNtp061ChkShohinSidList() != null
                            && form.getNtp061ChkShohinSidList().length > 0) {
                        for (String shohinSid : form.getNtp061ChkShohinSidList()) {
                            shohinHisMdl = __createNtpAnShohinHistory(usrSid);
                            shohinHisMdl.setNahSid(nahSid);
                            shohinHisMdl.setNhnSid(Integer.parseInt(shohinSid));
                            shohinHisMdl.setNanSid(nanSid);
                            shohinHisDao.insert(shohinHisMdl);
                        }
                    }
                }

                memberHisDao.delete(nahSid);
                if (anMemberMdl != null) {
                    NtpAnMemberHistoryModel memberHisMdl = new NtpAnMemberHistoryModel();
                    if (form.getSv_users() != null
                            && form.getSv_users().length > 0) {
                        for (String uSid : form.getSv_users()) {
                            if (GSValidateUtil.isNumber(uSid)) {
                                memberHisMdl = __createNtpAnMemberHistory(usrSid);
                                memberHisMdl.setNahSid(nahSid);
                                memberHisMdl.setUsrSid(Integer.parseInt(uSid));
                                memberHisMdl.setNanSid(nanSid);
                                memberHisDao.insert(memberHisMdl);
                            }
                        }
                    }
                }
            }

            //見積もり日の履歴があるかないか
            nahSid = -1;
            nahSid = hisDao.checkData(nanSid, ankenMdl.getNanMitumoriDate());
            nanMonth = new UDate();
            nanMonth.setYear(ankenMdl.getNanMitumoriDate().getYear());
            nanMonth.setMonth(ankenMdl.getNanMitumoriDate().getMonth());
            nanMonth.setDay(nanMonth.getMaxDayOfMonth());
            nanMonth.setZeroHhMmSs();
            hisMdl.setNanMonth(nanMonth);
            ankenMdl.getNanMitumoriDate().setZeroHhMmSs();
            hisMdl.setNanDate(ankenMdl.getNanMitumoriDate());

            //見積もり日は案件状態を商談中に設定
            //hisMdl.setNanSyodan(0);

            if (nahSid == -1) {
                //履歴の新規登録

                //履歴SID採番
                nahSid = (int) cntCon.getSaibanNumber(GSConstNippou.SBNSID_NIPPOU,
                    GSConstNippou.SBNSID_SUB_ANKEN_HISTORY, usrSid);
                hisMdl.setNahSid(nahSid);
                hisDao.insert(hisMdl);

                if (anShohinMdl != null) {
                    NtpAnShohinHistoryModel shohinHisMdl = new NtpAnShohinHistoryModel();
                    if (form.getNtp061ChkShohinSidList() != null
                            && form.getNtp061ChkShohinSidList().length > 0) {
                        for (String shohinSid : form.getNtp061ChkShohinSidList()) {
                            shohinHisMdl = __createNtpAnShohinHistory(usrSid);
                            shohinHisMdl.setNahSid(nahSid);
                            shohinHisMdl.setNhnSid(Integer.parseInt(shohinSid));
                            shohinHisMdl.setNanSid(nanSid);
                            shohinHisDao.insert(shohinHisMdl);
                        }
                    }
                }

                if (anMemberMdl != null) {
                    NtpAnMemberHistoryModel memberHisMdl = new NtpAnMemberHistoryModel();
                    if (form.getSv_users() != null
                            && form.getSv_users().length > 0) {
                        for (String uSid : form.getSv_users()) {
                            if (GSValidateUtil.isNumber(uSid)) {
                                memberHisMdl = __createNtpAnMemberHistory(usrSid);
                                memberHisMdl.setNahSid(nahSid);
                                memberHisMdl.setUsrSid(Integer.parseInt(uSid));
                                memberHisMdl.setNanSid(nanSid);
                                memberHisDao.insert(memberHisMdl);
                            }
                        }
                    }
                }
            } else {
                //履歴の更新
                hisMdl.setNahSid(nahSid);
                hisDao.update(hisMdl);
                shohinHisDao.delete(nahSid);
                if (anShohinMdl != null) {
                    NtpAnShohinHistoryModel shohinHisMdl = new NtpAnShohinHistoryModel();
                    if (form.getNtp061ChkShohinSidList() != null
                            && form.getNtp061ChkShohinSidList().length > 0) {
                        for (String shohinSid : form.getNtp061ChkShohinSidList()) {
                            shohinHisMdl = __createNtpAnShohinHistory(usrSid);
                            shohinHisMdl.setNahSid(nahSid);
                            shohinHisMdl.setNhnSid(Integer.parseInt(shohinSid));
                            shohinHisMdl.setNanSid(nanSid);
                            shohinHisDao.insert(shohinHisMdl);
                        }
                    }
                }

                memberHisDao.delete(nahSid);
                if (anMemberMdl != null) {
                    NtpAnMemberHistoryModel memberHisMdl = new NtpAnMemberHistoryModel();
                    if (form.getSv_users() != null
                            && form.getSv_users().length > 0) {
                        for (String uSid : form.getSv_users()) {
                            if (GSValidateUtil.isNumber(uSid)) {
                                memberHisMdl = __createNtpAnMemberHistory(usrSid);
                                memberHisMdl.setNahSid(nahSid);
                                memberHisMdl.setUsrSid(Integer.parseInt(uSid));
                                memberHisMdl.setNanSid(nanSid);
                                memberHisDao.insert(memberHisMdl);
                            }
                        }
                    }
                }
            }

            //受注日の履歴があるかないか
            nahSid = -1;
            nahSid = hisDao.checkData(nanSid, ankenMdl.getNanJutyuDate());
            nanMonth = new UDate();
            nanMonth.setYear(ankenMdl.getNanJutyuDate().getYear());
            nanMonth.setMonth(ankenMdl.getNanJutyuDate().getMonth());
            nanMonth.setDay(nanMonth.getMaxDayOfMonth());
            nanMonth.setZeroHhMmSs();
            hisMdl.setNanMonth(nanMonth);
            ankenMdl.getNanJutyuDate().setZeroHhMmSs();
            hisMdl.setNanDate(ankenMdl.getNanJutyuDate());

            //受注日は案件状態を受注に設定
            //hisMdl.setNanSyodan(1);

            if (nahSid == -1) {
                //履歴の新規登録

                //履歴SID採番
                nahSid = (int) cntCon.getSaibanNumber(GSConstNippou.SBNSID_NIPPOU,
                    GSConstNippou.SBNSID_SUB_ANKEN_HISTORY, usrSid);
                hisMdl.setNahSid(nahSid);
                hisDao.insert(hisMdl);

                if (anShohinMdl != null) {
                    NtpAnShohinHistoryModel shohinHisMdl = new NtpAnShohinHistoryModel();
                    if (form.getNtp061ChkShohinSidList() != null
                            && form.getNtp061ChkShohinSidList().length > 0) {
                        for (String shohinSid : form.getNtp061ChkShohinSidList()) {
                            shohinHisMdl = __createNtpAnShohinHistory(usrSid);
                            shohinHisMdl.setNahSid(nahSid);
                            shohinHisMdl.setNhnSid(Integer.parseInt(shohinSid));
                            shohinHisMdl.setNanSid(nanSid);
                            shohinHisDao.insert(shohinHisMdl);
                        }
                    }
                }

                if (anMemberMdl != null) {
                    NtpAnMemberHistoryModel memberHisMdl = new NtpAnMemberHistoryModel();
                    if (form.getSv_users() != null
                            && form.getSv_users().length > 0) {
                        for (String uSid : form.getSv_users()) {
                            if (GSValidateUtil.isNumber(uSid)) {
                                memberHisMdl = __createNtpAnMemberHistory(usrSid);
                                memberHisMdl.setNahSid(nahSid);
                                memberHisMdl.setUsrSid(Integer.parseInt(uSid));
                                memberHisMdl.setNanSid(nanSid);
                                memberHisDao.insert(memberHisMdl);
                            }
                        }
                    }
                }
            } else {
                //履歴の更新
                hisMdl.setNahSid(nahSid);
                hisDao.update(hisMdl);
                shohinHisDao.delete(nahSid);
                if (anShohinMdl != null) {
                    NtpAnShohinHistoryModel shohinHisMdl = new NtpAnShohinHistoryModel();
                    if (form.getNtp061ChkShohinSidList() != null
                            && form.getNtp061ChkShohinSidList().length > 0) {
                        for (String shohinSid : form.getNtp061ChkShohinSidList()) {
                            shohinHisMdl = __createNtpAnShohinHistory(usrSid);
                            shohinHisMdl.setNahSid(nahSid);
                            shohinHisMdl.setNhnSid(Integer.parseInt(shohinSid));
                            shohinHisMdl.setNanSid(nanSid);
                            shohinHisDao.insert(shohinHisMdl);
                        }
                    }
                }

                memberHisDao.delete(nahSid);
                if (anMemberMdl != null) {
                    NtpAnMemberHistoryModel memberHisMdl = new NtpAnMemberHistoryModel();
                    if (form.getSv_users() != null
                            && form.getSv_users().length > 0) {
                        for (String uSid : form.getSv_users()) {
                            if (GSValidateUtil.isNumber(uSid)) {
                                memberHisMdl = __createNtpAnMemberHistory(usrSid);
                                memberHisMdl.setNahSid(nahSid);
                                memberHisMdl.setUsrSid(Integer.parseInt(uSid));
                                memberHisMdl.setNanSid(nanSid);
                                memberHisDao.insert(memberHisMdl);
                            }
                        }
                    }
                }
            }

            commitFlg = true;

            //ログ出力処理
            NtpCommonBiz ntpBiz = new NtpCommonBiz(con, getRequestModel(req));
            String opCode = "";
            if (form.getNtp060ProcMode().equals(GSConstNippou.CMD_ADD)) {
                opCode = entry;
            } else {
                opCode = change;
            }

            ntpBiz.outPutLog(
             map, req, res, opCode, GSConstLog.LEVEL_TRACE, form.getNtp061NanName());

            //完了画面設定
            return __setCompDsp(map, req, form,
                    "touroku.kanryo.object", StringUtilHtml.transToHTml(form.getNtp061NanName()));

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
    }

    /**
     * <br>[機  能] 登録確認画面でOKボタン押下
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param backKbn 戻り先 0:案件選択画面 1:日報登録画面
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doRegistOkPop(ActionMapping map,
        Ntp061knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con,
        int backKbn)
        throws Exception {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        boolean commitFlg = false;
        con.setAutoCommit(false);

        try {
            int usrSid = this.getSessionUserModel(req).getUsrsid();
            NtpAnkenDao ankenDao = new NtpAnkenDao(con);
            NtpAnkenModel ankenMdl = __createNtpAnken(usrSid);

            String mitumori = NullDefault.getStringZeroLength(
                    form.getNtp061NanKinMitumori().replaceAll(",", ""), "0");
            String jutyu = NullDefault.getStringZeroLength(
                    form.getNtp061NanKinJutyu().replaceAll(",", ""), "0");
            String acoSid
                = NullDefault.getStringZeroLength(form.getNtp061CompanySid(), "-1");
            String abaSid
                = NullDefault.getStringZeroLength(form.getNtp061CompanyBaseSid(), "-1");

            UDate date = new UDate();
            date.setZeroHhMmSs();

            ankenMdl.setNanDate(date);
            ankenMdl.setNanCode(form.getNtp061NanCode());
            ankenMdl.setNanName(form.getNtp061NanName());
            ankenMdl.setNanDetial(form.getNtp061NanSyosai());
            ankenMdl.setAcoSid(Integer.parseInt(acoSid));
            ankenMdl.setAbaSid(Integer.parseInt(abaSid));
            ankenMdl.setNgpSid(form.getNtp061NgpSid());
            ankenMdl.setNanMikomi(form.getNtp061NanMikomi());
            ankenMdl.setNanKinMitumori(Integer.parseInt(mitumori));
            ankenMdl.setNanKinJutyu(Integer.parseInt(jutyu));
            ankenMdl.setNanSyodan(form.getNtp061NanSyodan());
            ankenMdl.setNanState(form.getNtp061NanState());
            ankenMdl.setNcnSid(form.getNtp061NcnSid());

            UDate mitumoriDate = new UDate();
            mitumoriDate.setDate(
                    Integer.parseInt(form.getNtp061MitumoriYear()),
                    Integer.parseInt(form.getNtp061MitumoriMonth()),
                    Integer.parseInt(form.getNtp061MitumoriDay()));
            mitumoriDate.setZeroHhMmSs();
            UDate jutyuDate = new UDate();
            jutyuDate.setDate(
                    Integer.parseInt(form.getNtp061JutyuYear()),
                    Integer.parseInt(form.getNtp061JutyuMonth()),
                    Integer.parseInt(form.getNtp061JutyuDay()));
            jutyuDate.setZeroHhMmSs();
            ankenMdl.setNanMitumoriDate(mitumoriDate);
            ankenMdl.setNanJutyuDate(jutyuDate);

            int nanSid = -1;

            //追加モード
            MlCountMtController cntCon = getCountMtController(req);
            //SID採番
            nanSid = (int) cntCon.getSaibanNumber(GSConstNippou.SBNSID_NIPPOU,
                GSConstNippou.SBNSID_SUB_ANKEN, usrSid);
            ankenMdl.setNanSid(nanSid);
            ankenDao.insert(ankenMdl);


            //案件商品情報の登録
            NtpAnShohinModel  anShohinMdl = null;
            NtpAnShohinDao anShohinDao = new NtpAnShohinDao(con);
            if (form.getNtp061ChkShohinSidList() != null
                && form.getNtp061ChkShohinSidList().length > 0) {
                for (String shohinSid : form.getNtp061ChkShohinSidList()) {
                    anShohinMdl = __createNtpAnShohin(usrSid);
                    anShohinMdl.setNhnSid(Integer.parseInt(shohinSid));
                    anShohinMdl.setNanSid(nanSid);
                    anShohinDao.insert(anShohinMdl);
                }
            }

            //担当者
            NtpAnMemberDao anMemberDao = new NtpAnMemberDao(con);
            NtpAnMemberModel anMemberMdl = null;
            String[] svUsers = form.getSv_users();
            if (svUsers != null) {
                for (int i = 0; i < svUsers.length; i++) {
                    if (GSValidateUtil.isNumber(svUsers[i])) {
                        anMemberMdl = __createNtpAnMember(usrSid);
                        anMemberMdl.setUsrSid(Integer.parseInt(svUsers[i]));
                        anMemberMdl.setNanSid(nanSid);
                        anMemberDao.insert(anMemberMdl);
                    }
                }
            }


            //履歴の新規登録
            NtpAnkenHistoryDao hisDao = new NtpAnkenHistoryDao(con);
            NtpAnShohinHistoryDao shohinHisDao = new NtpAnShohinHistoryDao(con);
            NtpAnMemberHistoryDao memberHisDao = new NtpAnMemberHistoryDao(con);

            //履歴は見積もり金額、受注金額を0に設定
            NtpAnkenHistoryModel hisMdl = new NtpAnkenHistoryModel();
            BeanUtils.copyProperties(hisMdl, ankenMdl);
            UDate nanMonth = new UDate();
            nanMonth.setYear(hisMdl.getNanDate().getYear());
            nanMonth.setMonth(hisMdl.getNanDate().getMonth());
            nanMonth.setDay(nanMonth.getMaxDayOfMonth());
            nanMonth.setZeroHhMmSs();
            hisMdl.setNanMonth(nanMonth);

            //履歴は見積もり金額、受注金額を0に設定
            hisMdl.setNanKinJutyu(0);
            hisMdl.setNanKinMitumori(0);

            //履歴SID採番
            int nahSid = (int) cntCon.getSaibanNumber(GSConstNippou.SBNSID_NIPPOU,
                GSConstNippou.SBNSID_SUB_ANKEN_HISTORY, usrSid);
            hisMdl.setNahSid(nahSid);
            hisDao.insert(hisMdl);

            if (anShohinMdl != null) {
                NtpAnShohinHistoryModel shohinHisMdl = new NtpAnShohinHistoryModel();
                if (form.getNtp061ChkShohinSidList() != null
                        && form.getNtp061ChkShohinSidList().length > 0) {
                    for (String shohinSid : form.getNtp061ChkShohinSidList()) {
                        shohinHisMdl = __createNtpAnShohinHistory(usrSid);
                        shohinHisMdl.setNahSid(nahSid);
                        shohinHisMdl.setNhnSid(Integer.parseInt(shohinSid));
                        shohinHisMdl.setNanSid(nanSid);
                        shohinHisDao.insert(shohinHisMdl);
                    }
                }
            }

            if (anMemberMdl != null) {
                NtpAnMemberHistoryModel memberHisMdl = new NtpAnMemberHistoryModel();
                if (form.getSv_users() != null
                        && form.getSv_users().length > 0) {
                    for (String uSid : form.getSv_users()) {
                        if (GSValidateUtil.isNumber(uSid)) {
                            memberHisMdl = __createNtpAnMemberHistory(usrSid);
                            memberHisMdl.setNahSid(nahSid);
                            memberHisMdl.setUsrSid(Integer.parseInt(uSid));
                            memberHisMdl.setNanSid(nanSid);
                            memberHisDao.insert(memberHisMdl);
                        }
                    }
                }
            }


            commitFlg = true;

            //完了
            if (backKbn == 0) {
                //案件選択画面へ遷移
                form.setNtp061AddCompFlg(1);
            } else {
                //日報登録画面へ遷移
                form.setNtp061AnkenSid(nanSid);
                form.setNtp061AddCompFlg(2);
            }

            return __doInit(map, form, req, res, con);

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
    }


    /**
     * <br>[機  能] 完了画面
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param req リクエスト
     * @param form フォーム
     * @param mesParam メッセージパラメータ
     * @param mesValue メッセージ値
     * @return ActionForward フォワード
     */
    private ActionForward __setCompDsp(ActionMapping map,
        HttpServletRequest req,
        Ntp061knForm form,
        String mesParam,
        String mesValue) {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("ntp060");
        cmn999Form.setUrlOK(forwardOk.getPath());

        //メッセージ
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(msgRes.getMessage(mesParam, mesValue));

        form.setNtp060HiddenParam(cmn999Form, form);
        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }
    /**
     * <br>[機  能] 案件情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @return NtpAnkenModel
     */
    private NtpAnkenModel __createNtpAnken(int usrSid) {

        UDate nowDate = new UDate();
        NtpAnkenModel mdl = new NtpAnkenModel();
        mdl.setNanAuid(usrSid);
        mdl.setNanAdate(nowDate);
        mdl.setNanEuid(usrSid);
        mdl.setNanEdate(nowDate);
        return mdl;
    }
    /**
     * <br>[機  能] 案件商品情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @return NtpAnkenModel
     */
    private NtpAnShohinModel __createNtpAnShohin(int usrSid) {

        UDate nowDate = new UDate();
        NtpAnShohinModel mdl = new NtpAnShohinModel();
        mdl.setNasAuid(usrSid);
        mdl.setNasAdate(nowDate);
        mdl.setNasEuid(usrSid);
        mdl.setNasEdate(nowDate);
        return mdl;
    }

    /**
     * <br>[機  能] 案件商品情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @return NtpAnkenModel
     */
    private NtpAnShohinHistoryModel __createNtpAnShohinHistory(int usrSid) {

        UDate nowDate = new UDate();
        NtpAnShohinHistoryModel mdl = new NtpAnShohinHistoryModel();
        mdl.setNasAuid(usrSid);
        mdl.setNasAdate(nowDate);
        mdl.setNasEuid(usrSid);
        mdl.setNasEdate(nowDate);
        return mdl;
    }

    /**
     * <br>[機  能] 担当者情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @return NtpAnkenModel
     */
    private NtpAnMemberModel __createNtpAnMember(int usrSid) {

        UDate nowDate = new UDate();
        NtpAnMemberModel mdl = new NtpAnMemberModel();
        mdl.setNamAuid(usrSid);
        mdl.setNamAdate(nowDate);
        mdl.setNamEuid(usrSid);
        mdl.setNamEdate(nowDate);
        return mdl;
    }

    /**
     * <br>[機  能] 担当者履歴情報を作成
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @return NtpAnkenModel
     */
    private NtpAnMemberHistoryModel __createNtpAnMemberHistory(int usrSid) {

        UDate nowDate = new UDate();
        NtpAnMemberHistoryModel mdl = new NtpAnMemberHistoryModel();
        mdl.setNamAuid(usrSid);
        mdl.setNamAdate(nowDate);
        mdl.setNamEuid(usrSid);
        mdl.setNamEdate(nowDate);
        return mdl;
    }
}
