package jp.groupsession.v2.ntp.ntp040;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.ntp.AbstractNippouAction;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.dao.NippouSearchDao;
import jp.groupsession.v2.ntp.dao.NtpBinDao;
import jp.groupsession.v2.ntp.dao.NtpDataDao;
import jp.groupsession.v2.ntp.model.NtpAdmConfModel;
import jp.groupsession.v2.ntp.model.NtpBinModel;
import jp.groupsession.v2.ntp.model.NtpDataModel;
import jp.groupsession.v2.ntp.ntp240.Ntp240Biz;
import jp.groupsession.v2.struts.msg.GsMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 日報 日報登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp040Action extends AbstractNippouAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp040Action.class);

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

        log__.debug("START_Ntp040");
        ActionForward forward = null;
        Ntp040Form uform = (Ntp040Form) form;

        //権限チェック
        __setCanUsePluginFlg(uform, req, con);

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();
        uform.setNtp040ScrollFlg("0");
        log__.debug("CMD==>" + cmd);
        if (cmd.equals("040_week")) {
            //週間日報
            forward = map.findForward("040_week");
        } else if (cmd.equals("040_month")) {
            //月間日報
            forward = map.findForward("040_month");
        } else if (cmd.equals("040_day")) {
            //日間日報
            forward = map.findForward("040_day");
        } else if (cmd.equals("040_ok")) {
            //日報登録 重複登録チェック有
            forward = __doOk(map, uform, req, res, con);
        } else if (cmd.equals("040_del")) {
            //削除確認画面
            forward = __doDelete(map, uform, req, res, con);
        } else if (cmd.equals("040_del_ok")) {
            //削除更新実行
            forward = __doDeleteOk(map, uform, req, res, con);
        } else if (cmd.equals("040_back")) {
            //戻る
            forward = __doBack(map, uform, req, res, con);
        } else if (cmd.equals("addNtp") || cmd.equals("editNtp")) {
            //登録ボタン
            __doOk(map, uform, req, res, con);
        } else if (cmd.equals("fileDownload")) {
            log__.debug("添付ファイルダウンロード");
            forward = __doDownLoad(map, uform, req, res, con);
        } else if (cmd.equals("comp")) {
            //登録ボタン
            forward = __doCompDsp(map, uform, req, res, con);
        } else if (cmd.equals("tempdel")) {
            //添付ファイル 削除ボタン押下
            __doDeleteTemp(map, uform, req, res, con);
        } else if (cmd.equals("dodelete")) {
            //削除更新実行
            __doDeleteOk(map, uform, req, res, con);
        } else if (cmd.equals("editNtpData")) {
            //編集ボタンクリック
            __doEdit(map, uform, req, res, con);
        } else if (cmd.equals("resetData")) {
            //データ再設定(確定ボタン、編集キャンセルボタン)
            __doResetData(map, uform, req, res, con);
            //コメントをする
        } else if (cmd.equals("addComment")) {
            __doAddComment(map, req, res, uform, con);
            //コメント削除
        } else if (cmd.equals("delComment")) {
            __doDelComment(map, req, res, uform, con);
            //目標取得
        } else if (cmd.equals("getTrgData")) {
            __getTrgData(req, res, uform, con);
            //目標実績値取得
        } else if (cmd.equals("getTrgRecData")) {
            __getTrgRecData(req, res, uform, con);
        } else if (cmd.equals("trgEdit")) {
            //目標確定ボタン
            __doTrgEdit(map, uform, req, res, con);
        } else if (cmd.equals("getAdrHistoryList")) {
            //アドレス履歴取得
            __getAdrHistoryData(map, uform, req, res, con);
        } else if (cmd.equals("getAnkenHistoryList")) {
            //案件履歴取得
            __getAnkenHistoryData(map, uform, req, res, con);
        } else if (cmd.equals("getSchDataList")) {
            //スケジュール情報取得
            __getSchData(map, uform, req, res, con);
        } else if (cmd.equals("getSchSelectData")) {
            //選択したスケジュール情報取得
            __getSchSelectData(map, uform, req, res, con);
        } else if (cmd.equals("getPrjDataList")) {
            //プロジェクト情報取得
            __getPrjData(map, uform, req, res, con);
        } else if (cmd.equals("getPrjSelectData")) {
            //選択したプロジェクト情報取得
            __getPrjSelectData(map, uform, req, res, con);
        } else if (cmd.equals("pdf")) {
            //pdf出力
            log__.debug("日報ＰＤＦファイルダウンロード");
            uform.setCmd(cmd);
            __doInit(map, uform, req , res, con);
            forward = __doDownLoadPdf(map, uform, req, res, con);

        } else {
            //初期表示
            forward = __doInit(map, uform, req, res, con);
        }

        log__.debug("END_Ntp040");
        return forward;
    }

    /**
     * <br>初期表示処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     * @throws NumberFormatException 実行例外
     */
    private ActionForward __doInit(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws NumberFormatException, Exception {

        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), "nippou", getRequestModel(req));
        //一時保管ファイル全削除
        IOTools.deleteDir(tempDir);

        con.setAutoCommit(true);
        ActionForward forward = null;
        //管理者設定を反映したプラグイン設定情報を取得
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));

        if (form.getNtp040DspMoveFlg() != 0) {
            //不要なパラメータのクリア
            form.clearParam();
        }

        Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req));
        Ntp040ParamModel paramMdl = new Ntp040ParamModel();
        paramMdl.setParam(form);
        biz.getInitData(paramMdl, pconfig, con);
        paramMdl.setFormData(form);

        //biz.setCompanyData(form, con, getSessionUserModel(req).getUsrsid(), req);
        // トランザクショントークン設定
        saveToken(req);

        forward = map.getInputForward();


        if (NullDefault.getString(form.getCmd(), "").equals(GSConstNippou.CMD_EDIT)) {
            //日報閲覧権限チェック
            NtpCommonBiz ncbiz = new NtpCommonBiz(con, getRequestModel(req));
            String selUsrSid = NullDefault.getString(form.getNtp010SelectUsrSid(), "");
            if (!selUsrSid.equals("")) {
                if (!ncbiz.isCanInspection(sessionUsrSid, Integer.parseInt(selUsrSid), con)) {
                    forward = __canInspectionDataError(map, form, req, res, con);
                } else {
                    //管理者設定
                    NtpAdmConfModel admConf = ncbiz.getAdmConfModel(con);
                    if (admConf.getNacCrange() == GSConstNippou.CRANGE_SHARE_GROUP) {
                        //グループSIDチェック
                        GroupBiz gpBiz = new GroupBiz();
                        if (!StringUtil.isNullZeroStringSpace(form.getNtp010DspGpSid())) {
                            //ユーザが選択したグループに所属しているか判定
                            boolean belongGrpHnt = gpBiz.isBelongGroup(sessionUsrSid,
                                    Integer.valueOf(form.getNtp010DspGpSid()), con);
                            if (!belongGrpHnt) {
                                //選択した日報ユーザと同じ所属グループSIDを設定
                                String gpSid = gpBiz.getBothBelongGroup(
                                        sessionUsrSid, Integer.parseInt(selUsrSid), con);
                                if (!StringUtil.isNullZeroStringSpace(gpSid)) {
                                    form.setNtp010DspGpSid(gpSid);
                                } else {
                                    forward = __canInspectionDataError(map, form, req, res, con);
                                }
                            }
                        }
                    }
                }
            }
            //日報存在チェック
            NippouSearchDao ndao = new NippouSearchDao(con);
            if (!form.getNtp010NipSid().equals("0")) {
                if (ndao.getNippouDataCount(Integer.valueOf(form.getNtp010NipSid()),
                        Integer.valueOf(form.getNtp010SelectUsrSid())) == 0) {
                    forward = __doNoneDataError(map, form, req, res, con);
                }
            }

        }
        con.setAutoCommit(false);
        return forward;
    }


    /**
     * <br>登録ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doOk(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        RequestModel reqMdl = getRequestModel(req);
        //画面入力データ取得
        __getJsonData(req, form);

        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        ActionForward forward = null;
        Map<Integer, ActionErrors> errorsMap = form.validateCheck(map, req, con, sessionUsrSid);
        if (errorsMap.size() > 0) {
            setError(res, __getJsonErrorMsg(req, errorsMap));
            return null;
        }

        forward = __doCommit(map, form, req, res, con);

        GsMessage gsMsg = new GsMessage(reqMdl);
        String entry = gsMsg.getMessage("cmn.entry");
        String change = gsMsg.getMessage("cmn.change");

        //ログ出力処理
        NtpCommonBiz ntpBiz = new NtpCommonBiz(con, reqMdl);
        StringBuilder reOpLog = new StringBuilder();
        String opCode = "";
        if (form.getCmd().equals("addNtp")) {
            opCode = entry;
        } else if (form.getCmd().equals("editNtp")) {
            opCode = change;
        }
        for (Ntp040Param param : form.getNippouDataList()) {
            reOpLog.append("[date]");
            reOpLog.append(UDateUtil.getYymdJwithStrWeek(
                    UDateUtil.getUDate2(
                            String.valueOf(param.getNtpYear()),
                            String.valueOf(param.getNtpMonth()),
                            String.valueOf(param.getNtpDay())), reqMdl));
            reOpLog.append(gsMsg.getMessage("cmn.time.input", new String[] {
                    String.valueOf(param.getFrHour()),
                    String.valueOf(param.getFrMin())
            }));
            reOpLog.append(" ～ ");
            reOpLog.append(gsMsg.getMessage("cmn.time.input", new String[] {
                    String.valueOf(param.getToHour()),
                    String.valueOf(param.getToMin())
            }));
            reOpLog.append("\n");
            reOpLog.append("[title]");
            reOpLog.append(param.getTitle());
            reOpLog.append("\n");
            reOpLog.append("[naiyou]");
            reOpLog.append(param.getValueStr());

            ntpBiz.outPutLog(
             map, req, res, opCode, GSConstLog.LEVEL_TRACE, reOpLog.toString());

        }

        return forward;
    }

    /**
     * <br>JSONデータ取得
     * @param req リクエスト
     * @param form アクションフォーム
     * @throws Exception 実行例外
     */
    private void __getJsonData(HttpServletRequest req, Ntp040Form form) throws Exception {

        //目標データ
        String targetData = NullDefault.getString(req.getParameter("targetData"), "");
        if (!StringUtil.isNullZeroStringSpace(targetData)) {
            JSONArray jsonArray = JSONArray.fromObject(targetData);
            List<Ntp040TargetParam> paramList = new ArrayList<Ntp040TargetParam>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                Ntp040TargetParam param
                     = (Ntp040TargetParam) JSONObject.toBean(obj, Ntp040TargetParam.class);
                paramList.add(param);
            }
            form.setTargetDataList(paramList);
        }

        //日報データ
        String nippouData = NullDefault.getString(req.getParameter("nippouData"), "");
        String ntp010DspGpSid = NullDefault.getString(req.getParameter("ntp010DspGpSid"), "");
        if (!StringUtil.isNullZeroStringSpace(nippouData)) {
            JSONArray jsonArray = JSONArray
            .fromObject(nippouData);
            List<Ntp040Param> paramList = new ArrayList<Ntp040Param>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                Ntp040Param param = (Ntp040Param) JSONObject.toBean(obj, Ntp040Param.class);
                //タイトルと内容と次のアクションをデコード
                param.setTitle(URLDecoder.decode(param.getTitle(), "utf-8"));
                param.setValueStr(URLDecoder.decode(param.getValueStr(), "utf-8"));
                if (!StringUtil.isNullZeroStringSpace(param.getActionStr())) {
                    param.setActionStr(URLDecoder.decode(param.getActionStr(), "utf-8"));
                }
                paramList.add(param);
            }
            form.setNippouDataList(paramList);
            form.setNtp010DspGpSid(ntp010DspGpSid);
        }
    }

    /**
     * <br>jsonエラーメッセージ作成
     * @param req リクエスト
     * @param errorsMap エラーメッセージ
     * @throws Exception 実行例外
     * @return errorResult jsonエラーメッセージ
     */
    private List<Map<String, Object>> __getJsonErrorMsg(
        HttpServletRequest req, Map<Integer, ActionErrors> errorsMap) throws Exception {


        List<Map<String, Object>> errorResultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> errorResult = null;
        List<String> errorList = null;

        Set<Entry<Integer, ActionErrors>> set = errorsMap.entrySet();
        Iterator<Entry<Integer, ActionErrors>> it = set.iterator();

        while (it.hasNext()) {
            Entry<Integer, ActionErrors> entry = (Entry<Integer, ActionErrors>) it.next();
            ActionErrors errors = (ActionErrors) entry.getValue();

            @SuppressWarnings("all")
            Iterator iterator = errors.get();
            errorList = new ArrayList<String>();

            while (iterator.hasNext()) {
                ActionMessage error = (ActionMessage) iterator.next();
                errorList.add(getResources(req).getMessage(error.getKey(), error.getValues()));
            }

            errorResult = new TreeMap<String, Object>();
            errorResult.put("rownum", (String) String.valueOf(entry.getKey()));
            errorResult.put("msg",
                    (Object) ((String[]) errorList.toArray(new String[errorList.size()])));
            errorResultList.add(errorResult);
        }
        return errorResultList;
    }

    /**
     * <br>処理モードによって登録・修正処理を行う
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     * @throws Exception SQL実行時例外
     */
    private ActionForward __doCommit(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        //アプリケーションRoot
        String appRootPath = getAppRootPath();
        //プラグイン設定
        PluginConfig plconf = getPluginConfig(req);

        MlCountMtController cntCon = getCountMtController(req);
        Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req), cntCon);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), "nippou", getRequestModel(req));


        //目標の更新
        if (form.getTargetDataList() != null
                && !form.getTargetDataList().isEmpty()
                && form.getCmd().equals("addNtp")) {

            for (Ntp040TargetParam trgData : form.getTargetDataList()) {
                boolean commitFlg = false;
                con.setAutoCommit(false);
                try {
                    biz.setTarget(con, trgData, usModel);
                    con.commit();
                    commitFlg = true;
                } catch (Exception e) {
                    log__.error("目標の登録・更新に失敗しました" + e);
                    throw e;
                } finally {
                    if (!commitFlg) {
                        con.rollback();
                    }
                }
            }
        }

        //日報SID格納リスト
        List<Integer> ntpSids = new ArrayList<Integer>();
        int ntpSid = -1;

        //行数分の登録処理を行う
        for (Ntp040Param nipData : form.getNippouDataList()) {
            boolean commitFlg = false;
            con.setAutoCommit(false);

            //行ごとのテンポラリディレクトリ
            String ntpTempDir = tempDir + "row" + nipData.getRowId();

            PluginConfig pconfig = getPluginConfigForMain(plconf, con);
            boolean smailPluginUseFlg = cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig);
            try {

                //新規登録
                if (form.getCmd().equals("addNtp")) {

                    //採番マスタから日報SIDを取得
                    Ntp040ParamModel paramMdl = new Ntp040ParamModel();
                    paramMdl.setParam(form);
                    ntpSid = biz.insertNippouDate(
                            nipData, getRequestModel(req),
                            paramMdl, sessionUsrSid, appRootPath, plconf,
                            smailPluginUseFlg, ntpTempDir);
                    paramMdl.setFormData(form);

                } else if (form.getCmd().equals("editNtp")) {

                    String changeNtpSid = NullDefault.getString(
                            req.getParameter("changeNtpSid"), "-1");
                    Ntp040ParamModel paramMdl = new Ntp040ParamModel();
                    paramMdl.setParam(form);
                    ntpSid = biz.updateNippouDate(nipData, paramMdl,
                             sessionUsrSid, appRootPath, plconf,
                             smailPluginUseFlg, ntpTempDir, changeNtpSid);
                    paramMdl.setFormData(form);

                }

                ntpSids.add(ntpSid);

                con.commit();
                commitFlg = true;
            } catch (Exception e) {
                log__.error("日報登録に失敗しました" + e);
                throw e;
            } finally {
                if (!commitFlg) {
                    con.rollback();
                }
            }
        }

        //テンポラリディレクトリのファイルを削除する
        IOTools.deleteDir(tempDir);
        log__.debug("テンポラリディレクトリのファイル削除");

        //日報確認情報を更新(登録者を確認済にする)
        biz.setCheck(ntpSids, sessionUsrSid);
        //登録者以外を未確認にする
        biz.resetCheck(ntpSids, sessionUsrSid);

        if (form.getCmd().equals("addNtp")) {
            //新規登録の場合は、日報SIDを返す
            setNtpSid(res, ntpSids);
        }
        return null;
    }

    /**
     * <br>削除ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doDelete(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws SQLException {

        ActionForward forward = null;

        // トランザクショントークン設定
        saveToken(req);

        //確認画面へ
        log__.debug("削除確認画面へ");
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //権限エラー警告画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("040_del_ok");
        cmn999Form.setUrlOK(urlForward.getPath());
        urlForward = map.findForward("040_del_cancel");
        cmn999Form.setUrlCancel(urlForward.getPath());
        GsMessage gsMsg = new GsMessage();
        //日報
        String textSchedule = gsMsg.getMessage(req, "schedule.108");
        if (form.getCmd().equals(GSConstSchedule.CMD_EDIT)) {
            cmn999Form.setMessage(msgRes.getMessage("sakujo.kakunin.list",
                    textSchedule,
                    StringUtilHtml.transToHTmlPlusAmparsant(form.getNtp040Title())));
        }
        //週間・日間・月間
        cmn999Form.addHiddenParam("cmd", form.getCmd());
        cmn999Form.addHiddenParam("dspMod", form.getDspMod());
        cmn999Form.addHiddenParam("ntp010DspDate", form.getNtp010DspDate());
        //選択
        cmn999Form.addHiddenParam("ntp010SelectUsrSid", form.getNtp010SelectUsrSid());
        cmn999Form.addHiddenParam("ntp010SelectUsrKbn", form.getNtp010SelectUsrKbn());
        cmn999Form.addHiddenParam("ntp010SelectDate", form.getNtp010SelectDate());
        cmn999Form.addHiddenParam("ntp010SchSid", form.getNtp010NipSid());
        cmn999Form.addHiddenParam("ntp020SelectUsrSid", form.getNtp020SelectUsrSid());
        //登録・修正画面
        cmn999Form.addHiddenParam("ntp010NipSid", form.getNtp010NipSid());
        cmn999Form.addHiddenParam("ntp040Bgcolor", form.getNtp040Bgcolor());
        cmn999Form.addHiddenParam("ntp040Title", form.getNtp040Title());
        cmn999Form.addHiddenParam("ntp040Value", form.getNtp040Value());
        cmn999Form.addHiddenParam("ntp040NxtActYear", form.getNtp040NxtActYear());
        cmn999Form.addHiddenParam("ntp040NxtActMonth", form.getNtp040NxtActMonth());
        cmn999Form.addHiddenParam("ntp040NxtActDay", form.getNtp040NxtActDay());
        cmn999Form.addHiddenParam("ntp040NextAction", form.getNtp040NextAction());
        cmn999Form.addHiddenParam("ntp040ActDateKbn", form.getNtp040ActDateKbn());
        cmn999Form.addHiddenParam("ntp040Biko", form.getNtp040Biko());
        cmn999Form.addHiddenParam("ntp040Public", form.getNtp040Public());
        cmn999Form.addHiddenParam("ntp040FrYear", form.getNtp040FrYear());
        cmn999Form.addHiddenParam("ntp040FrMonth", form.getNtp040FrMonth());
        cmn999Form.addHiddenParam("ntp040FrDay", form.getNtp040FrDay());
        cmn999Form.addHiddenParam("ntp040FrHour", form.getNtp040FrHour());
        cmn999Form.addHiddenParam("ntp040FrMin", form.getNtp040FrMin());
        cmn999Form.addHiddenParam("ntp040ToYear", form.getNtp040ToYear());
        cmn999Form.addHiddenParam("ntp040ToMonth", form.getNtp040ToMonth());
        cmn999Form.addHiddenParam("ntp040ToDay", form.getNtp040ToDay());
        cmn999Form.addHiddenParam("ntp040ToHour", form.getNtp040ToHour());
        cmn999Form.addHiddenParam("ntp040ToMin", form.getNtp040ToMin());
        cmn999Form.addHiddenParam("ntp040GroupSid", form.getNtp040GroupSid());
        cmn999Form.addHiddenParam("ntp040BatchRef", form.getNtp040BatchRef());
        cmn999Form.addHiddenParam("ntp040ReserveGroupSid", form.getNtp040ReserveGroupSid());
        cmn999Form.addHiddenParam("ntp040ResBatchRef", form.getNtp040ResBatchRef());

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>削除処理実行
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移先
     * @throws Exception 実行例外
     */
    private ActionForward __doDeleteOk(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        NtpDataDao ntpDao = new NtpDataDao(con);
        NtpDataModel ntpModel = null;
        RequestModel reqMdl = getRequestModel(req);

        Ntp040Biz biz = new Ntp040Biz(con, reqMdl);
        String delNtpSid = NullDefault.getString(req.getParameter("delNtpSid"), "");

        if (!StringUtil.isNullZeroStringSpace(delNtpSid)) {

            boolean commitFlg = false;
            con.setAutoCommit(false);
            try {
                ntpModel = ntpDao.select(Integer.valueOf(delNtpSid));

                biz.deleteNippou(Integer.valueOf(delNtpSid), con);

                //添付ファイル 削除ボタン押下
                __doDeleteTemp(map, form, req, res, con);

                commitFlg = true;
            } catch (SQLException e) {
                log__.error("日報削除に失敗しました" + e);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }
        }

        if (ntpModel != null) {
            GsMessage gsMsg = new GsMessage(reqMdl);
            String delete = gsMsg.getMessage("cmn.delete");
            StringBuilder deOpLog = new StringBuilder();
            deOpLog.append("[date]");
            deOpLog.append(UDateUtil.getYymdJwithStrWeek(ntpModel.getNipFrTime(), reqMdl));
            deOpLog.append(UDateUtil.getSeparateHMJ(ntpModel.getNipFrTime(), reqMdl));
            deOpLog.append(" ～ ");
            deOpLog.append(UDateUtil.getSeparateHMJ(ntpModel.getNipToTime(), reqMdl));
            deOpLog.append("\n");
            deOpLog.append("[title]");
            deOpLog.append(ntpModel.getNipTitle());
            deOpLog.append("\n");
            deOpLog.append("[naiyou]");
            deOpLog.append(ntpModel.getNipDetail());

            //ログ出力処理
            NtpCommonBiz ntpBiz = new NtpCommonBiz(con, reqMdl);
            ntpBiz.outPutLog(map, req, res, delete, GSConstLog.LEVEL_TRACE,
                    deOpLog.toString());
        }

        return null;
    }


    /**
     * <br>リクエストを解析し画面遷移先を取得する
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward 画面遷移
     */
    private ActionForward __doBack(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), "nippou", getRequestModel(req));
        //一時保管ファイル全削除
        IOTools.deleteDir(tempDir);

        ActionForward forward = null;

        if (!StringUtil.isNullZeroString(form.getNtp040schUrl())) {
            //スケジュールへ遷移
            try {
                res.sendRedirect(form.getNtp040schUrl());
            } catch (IOException e) {
                forward = map.findForward("040_week");
            }
        }

        String dspMod = form.getDspMod();

        if (dspMod.equals(GSConstNippou.DSP_MOD_WEEK)) {
            forward = map.findForward("040_week");
        } else if (dspMod.equals(GSConstNippou.DSP_MOD_MONTH)) {
            forward = map.findForward("040_month");
        } else if (dspMod.equals(GSConstNippou.DSP_MOD_DAY)) {
            forward = map.findForward("040_day");
        } else if (dspMod.equals(GSConstNippou.DSP_MOD_MAIN)) {
            forward = map.findForward("040_main");
        } else if (dspMod.equals(GSConstNippou.DSP_MOD_LIST)) {
            forward = map.findForward("040_list");
        }
        return forward;
    }


    /**
     * 登録・更新完了画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doCompDsp(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;


        //日報登録完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        if (form.getListMod().equals(GSConstSchedule.DSP_MOD_LIST)) {
            urlForward = map.findForward("040_list");
        } else {

            if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_WEEK)) {
                urlForward = map.findForward("040_week");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MONTH)) {

                urlForward = map.findForward("040_month");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_DAY)) {
                urlForward = map.findForward("040_day");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MAIN)) {
                urlForward = map.findForward("040_main");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
                urlForward = map.findForward("040_kojin");
            } else {
                urlForward = map.findForward("040_week");
            }
        }

        //日報
        String textSchedule = "日報";
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("touroku.kanryo.object",
                textSchedule));

        cmn999Form.addHiddenParam("dspMod", form.getDspMod());
        cmn999Form.addHiddenParam("ntp010DspDate", form.getNtp010DspDate());
        cmn999Form.addHiddenParam("ntp010DspGpSid", form.getNtp010DspGpSid());
        cmn999Form.addHiddenParam("ntp010SelectUsrSid", form.getNtp010SelectUsrSid());
        cmn999Form.addHiddenParam("ntp010SelectUsrKbn", form.getNtp010SelectUsrKbn());
        cmn999Form.addHiddenParam("ntp010SelectDate", form.getNtp010SelectDate());
        cmn999Form.addHiddenParam("ntp020SelectUsrSid", form.getNtp020SelectUsrSid());
        cmn999Form.addHiddenParam("ntp100SelectNtpAreaSid", form.getNtp100SelectNtpAreaSid());
        cmn999Form.addHiddenParam("ntp100AnkenSid", form.getNtp100AnkenSid());
        cmn999Form.addHiddenParam("ntp100CompanySid", form.getNtp100CompanySid());
        cmn999Form.addHiddenParam("ntp100CompanyBaseSid", form.getNtp100CompanyBaseSid());
        cmn999Form.addHiddenParam("ntp100SvAnkenSid", form.getNtp100SvAnkenSid());
        cmn999Form.addHiddenParam("ntp100SvCompanySid", form.getNtp100SvCompanySid());
        cmn999Form.addHiddenParam("ntp100SvCompanyBaseSid", form.getNtp100SvCompanyBaseSid());
        cmn999Form.addHiddenParam("ntp100Ktbunrui", form.getNtp100Ktbunrui());
        cmn999Form.addHiddenParam("ntp100Kthouhou", form.getNtp100Kthouhou());
        cmn999Form.addHiddenParam("ntp100SvKtbunrui", form.getNtp100SvKtbunrui());
        cmn999Form.addHiddenParam("ntp100SvKthouhou", form.getNtp100SvKthouhou());
        cmn999Form.addHiddenParam("ntp100SltGroup", form.getNtp100SltGroup());
        cmn999Form.addHiddenParam("ntp100SltUser", form.getNtp100SltUser());
        cmn999Form.addHiddenParam("ntp100SvSltGroup", form.getNtp100SvSltGroup());
        cmn999Form.addHiddenParam("ntp100SvSltUser", form.getNtp100SvSltUser());
        cmn999Form.addHiddenParam("ntp100SltYearFr", form.getNtp100SltYearFr());
        cmn999Form.addHiddenParam("ntp100SltMonthFr", form.getNtp100SltMonthFr());
        cmn999Form.addHiddenParam("ntp100SltDayFr", form.getNtp100SltDayFr());
        cmn999Form.addHiddenParam("ntp100SltYearTo", form.getNtp100SltYearTo());
        cmn999Form.addHiddenParam("ntp100SltMonthTo", form.getNtp100SltMonthTo());
        cmn999Form.addHiddenParam("ntp100SltDayTo", form.getNtp100SltDayTo());
        cmn999Form.addHiddenParam("ntp100SvSltYearFr", form.getNtp100SvSltYearFr());
        cmn999Form.addHiddenParam("ntp100SvSltMonthFr", form.getNtp100SvSltMonthFr());
        cmn999Form.addHiddenParam("ntp100SvSltDayFr", form.getNtp100SvSltDayFr());
        cmn999Form.addHiddenParam("ntp100SvSltYearTo", form.getNtp100SvSltYearTo());
        cmn999Form.addHiddenParam("ntp100SvSltMonthTo", form.getNtp100SvSltMonthTo());
        cmn999Form.addHiddenParam("ntp100SvSltDayTo", form.getNtp100SvSltDayTo());
        cmn999Form.addHiddenParam("ntp100KeyWordkbn", form.getNtp100KeyWordkbn());
        cmn999Form.addHiddenParam("ntp100KeyValue", form.getNtp100KeyValue());
        cmn999Form.addHiddenParam("ntp100SvKeyWordkbn", form.getNtp100SvKeyWordkbn());
        cmn999Form.addHiddenParam("ntp100SvKeyValue", form.getNtp100SvKeyValue());
        cmn999Form.addHiddenParam("ntp100PageNum", form.getNtp100PageNum());
        cmn999Form.addHiddenParam("ntp100Slt_page1", form.getNtp100Slt_page1());
        cmn999Form.addHiddenParam("ntp100Slt_page2", form.getNtp100Slt_page2());
        cmn999Form.addHiddenParam("ntp100OrderKey1", form.getNtp100OrderKey1());
        cmn999Form.addHiddenParam("ntp100SortKey1", form.getNtp100SortKey1());
        cmn999Form.addHiddenParam("ntp100OrderKey2", form.getNtp100OrderKey2());
        cmn999Form.addHiddenParam("ntp100SortKey2", form.getNtp100SortKey2());
        cmn999Form.addHiddenParam("ntp100SvOrderKey1", form.getNtp100SvOrderKey1());
        cmn999Form.addHiddenParam("ntp100SvSortKey1", form.getNtp100SvSortKey1());
        cmn999Form.addHiddenParam("ntp100SvOrderKey2", form.getNtp100SvOrderKey2());
        cmn999Form.addHiddenParam("ntp100SvSortKey2", form.getNtp100SvSortKey2());
        cmn999Form.addHiddenParam("ntp100PageNum", form.getNtp100PageNum());

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }
    /**
     * <br>閲覧対象が無い場合のエラー画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __canInspectionDataError(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        String listMod = NullDefault.getString(form.getListMod(), "");
        if (listMod.equals(GSConstSchedule.DSP_MOD_LIST)) {
            urlForward = map.findForward("040_list");
        } else {
            if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_WEEK)) {
                urlForward = map.findForward("040_week");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MONTH)) {
                urlForward = map.findForward("040_month");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_DAY)) {
                urlForward = map.findForward("040_day");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MAIN)) {
                urlForward = map.findForward("040_main");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
                urlForward = map.findForward("040_kojin");
            } else {
                urlForward = map.findForward("040_week");
            }
        }
        //日報
        String textSchedule = "閲覧";
        //閲覧
        String textChange = "閲覧";
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("error.edit.power.user",
                textSchedule, textChange));

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>存在しない日報を選択した場合のエラー画面設定
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     */
    private ActionForward __doNoneDataError(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con) {
        ActionForward forward = null;

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;


        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        String listMod = NullDefault.getString(form.getListMod(), "");
        if (listMod.equals(GSConstSchedule.DSP_MOD_LIST)) {
            urlForward = map.findForward("040_list");
        } else {
            if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_WEEK)) {
                urlForward = map.findForward("040_week");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MONTH)) {
                urlForward = map.findForward("040_month");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_DAY)) {
                urlForward = map.findForward("040_day");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_MAIN)) {
                urlForward = map.findForward("040_main");
            } else if (form.getDspMod().equals(GSConstSchedule.DSP_MOD_KOJIN_WEEK)) {
                urlForward = map.findForward("040_kojin");
            } else {
                urlForward = map.findForward("040_week");
            }
        }
        //日報
        String textSchedule = "日報";
        //閲覧
        String textChange = "閲覧";
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage("error.none.edit.data",
                textSchedule, textChange));

        req.setAttribute("cmn999Form", cmn999Form);

        forward = map.findForward("gf_msg");
        return forward;
    }

    /**
     * <br>[機  能] テンポラリディレクトリに添付ファイル保存
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpSid 日報SID
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doSetTemp(String ntpSid,
                                      ActionMapping map,
                                      Ntp040Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws Exception {

        String rowNum = NullDefault.getString(req.getParameter("rowNum"), "");

        if (StringUtil.isNullZeroString(rowNum)) {
            rowNum = "1";
        }

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), "nippou", getRequestModel(req))
                         + "row" + rowNum + File.separator;

        //添付ファイル情報
        NtpBinDao binDao = new NtpBinDao(con);
        List<NtpBinModel> binList = binDao.getBinList(Integer.valueOf(ntpSid));

        //添付ファイルがあるなるならばテンポラリにコピー
        if (!binList.isEmpty()) {
            Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req));
            biz.tempFileCopy(binList, getAppRootPath(), tempDir, con,
                    GroupSession.getResourceManager().getDomain(req));

            //添付ファイル情報取得
            String jsonStr = null;
            jsonStr = biz.setTempFiles(tempDir, con);
            if (!StringUtil.isNullZeroString(jsonStr)) {
                try {
                    res.setHeader("Cache-Control", "no-cache");
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = res.getWriter();
                    out.print(jsonStr);
                    out.flush();
                } catch (Exception e) {
                    log__.error("jsonデータ送信失敗(編集)");
                }
            }
        }
        return null;
    }

    /**
     * <br>[機  能] 削除ボタンクリック時の処理
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
    private ActionForward __doDeleteTemp(ActionMapping map,
                                      Ntp040Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws Exception {

        String rowNum = NullDefault.getString(req.getParameter("rowNum"), "");

        if (StringUtil.isNullZeroString(rowNum)) {
            rowNum = "1";
        }

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), "nippou", getRequestModel(req))
                         + "row" + rowNum + File.separator;

        //jsonデータ取得
        __getJsonTempData(req, form);

        //選択された添付ファイルを削除する
        cmnBiz.deleteFile(form.getNtp040selectFiles(), tempDir);

        return null;
    }

    /**
     * <br>JSON添付ファイルデータ取得
     * @param req リクエスト
     * @param form アクションフォーム
     * @throws Exception 実行例外
     */
    private void __getJsonTempData(HttpServletRequest req, Ntp040Form form) throws Exception {

        String nippouTempData = NullDefault.getString(req.getParameter("nippouTempData"), "");
        if (!StringUtil.isNullZeroStringSpace((String) nippouTempData)) {

            JSONArray obj = JSONArray.fromObject(nippouTempData);
            ArrayList<String> fileList = new ArrayList<String>();
            for (int i = 0; i < obj.size(); i++) {
                fileList.add((String) obj.getString(i));
            }
            if (!fileList.isEmpty()) {
                form.setNtp040selectFiles(
                        fileList.toArray(new String[fileList.size()]));
            }
        }
    }

    /**
     * <br>[機  能] 添付ファイルダウンロードの処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDownLoad(
        ActionMapping map,
        Ntp040Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        CommonBiz cmnBiz = new CommonBiz();
        NtpCommonBiz ntpBiz = new NtpCommonBiz(con, getRequestModel(req));

        int ntpSid = NullDefault.getInt(form.getNtp010NipSid(), 0);
        Long binSid = NullDefault.getLong(form.getNtp040BinSid(), -1);

        //日報の添付ファイルがダウンロード可能かチェックする
        if (ntpBiz.isCheckDLNtpTemp(con, ntpSid, getSessionUserSid(req), binSid)) {

            CmnBinfModel cbMdl = cmnBiz.getBinInfo(
                    con, binSid, GroupSession.getResourceManager().getDomain(req));

            if (cbMdl != null) {

                GsMessage gsMsg = new GsMessage();
                String download = gsMsg.getMessage(req, "cmn.download");

                //ログ出力処理
                ntpBiz.outPutLog(map, req, res,
                        download, GSConstLog.LEVEL_INFO, cbMdl.getBinFileName(),
                        String.valueOf(binSid), GSConstNippou.NTP_LOG_FLG_DOWNLOAD);

                //時間のかかる処理の前にコネクションを破棄
                JDBCUtil.closeConnectionAndNull(con);

                //ファイルをダウンロードする
                TempFileUtil.downloadAtachment(req, res, cbMdl, getAppRootPath(), Encoding.UTF_8);
            }
        }

        return null;
    }

    /**
     * <br>編集ボタンクリック
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws Exception 実行時例外
     */
    private void __doEdit(
            ActionMapping map,
            Ntp040Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con) throws SQLException, Exception {

        //日報SID取得
        String editNtpSid = NullDefault.getString(req.getParameter("editNtpSid"), "");

        if (!StringUtil.isNullZeroStringSpace(editNtpSid)) {
            //一時保管ファイル取得
            __doSetTemp(editNtpSid, map, form, req, res, con);
        }
    }

    /**
     * <br>編集キャンセルボタンクリック
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws Exception 実行時例外
     */
    private void __doResetData(
            ActionMapping map,
            Ntp040Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con) throws SQLException, Exception {

        JSONObject jsonData = null;
        Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req));

        //日報SID取得
        String resetNtpSid = NullDefault.getString(req.getParameter("resetNtpSid"), "");

        if (!StringUtil.isNullZeroStringSpace(resetNtpSid)) {
            //一時保管ファイル削除
            __doDeleteTemp(map, form, req, res, con);

            //データの再取得
            jsonData = biz.getNtpJsonData(con, resetNtpSid);
        }

        try {
            if (jsonData != null) {
                res.setHeader("Cache-Control", "no-cache");
                res.setContentType("application/json;charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.print(jsonData.toString());
                out.flush();
            }
        } catch (Exception e) {
            log__.error("jsonデータ送信失敗(確定or編集キャンセル)");
        }
    }

    /**
     * <br>コメント登録 + 最新データ取得
     * @param map マップ
     * @param req リクエスト
     * @param res レスポンス
     * @param form アクションフォーム
     * @param con Connection
     * @throws Exception 実行例外
     */
    private void __doAddComment(
                                ActionMapping map,
                                HttpServletRequest req,
                                HttpServletResponse res,
                                Ntp040Form form,
                                Connection con) throws Exception {

        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

        String ntpSid = NullDefault.getString(req.getParameter("commentNtpSid"), "");
        String commentStr = NullDefault.getString(req.getParameter("commentStr"), "");
        String ntp010DspGpSid = NullDefault.getString(req.getParameter("ntp010DspGpSid"), "");
        form.setNtp010DspGpSid(ntp010DspGpSid);

        if (usModel != null && !StringUtil.isNullZeroStringSpace(ntpSid)
                                   && !StringUtil.isNullZeroStringSpace(commentStr)) {

            //セッションユーザSID
            int sessionUsrSid = usModel.getUsrsid();
            //コメントをデコード
            commentStr = URLDecoder.decode(commentStr, "utf-8");

            MlCountMtController cntCon = getCountMtController(req);
            Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req), cntCon);

            boolean commitFlg = false;
            con.setAutoCommit(false);
            try {

                //コメント新規登録
                Ntp040ParamModel paramMdl = new Ntp040ParamModel();
                paramMdl.setParam(form);
                biz.insertComment(paramMdl, Integer.valueOf(ntpSid), commentStr,
                        sessionUsrSid, getAppRootPath(), getPluginConfig(req),
                        getRequestModel(req));
                paramMdl.setFormData(form);


                commitFlg = true;

                //ログ
                NtpCommonBiz ntpBiz = new NtpCommonBiz(con, getRequestModel(req));
                String opCode = "コメント 登録";

                ntpBiz.outPutLog(
                 map, req, res, opCode, GSConstLog.LEVEL_TRACE, "");



            } catch (SQLException e) {
                log__.error("コメントの登録に失敗しました" + e);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }

            //最新のコメント取得
            JSONArray jsonData = biz.getComment(Integer.valueOf(ntpSid));
            if (jsonData != null && !jsonData.isEmpty()) {
                try {
                    res.setHeader("Cache-Control", "no-cache");
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = res.getWriter();
                    out.print(jsonData);
                    out.flush();
                } catch (Exception e) {
                    log__.error("jsonデータ送信失敗(コメント)");
                }
            }
        }
    }

    /**
     * <br>コメント削除
     * @param map マップ
     * @param req リクエスト
     * @param res レスポンス
     * @param form アクションフォーム
     * @param con Connection
     * @throws Exception 実行例外
     */
    private void __doDelComment(
                                ActionMapping map,
                                HttpServletRequest req,
                                HttpServletResponse res,
                                Ntp040Form form,
                                Connection con) throws Exception {

        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

        String ntpSid = NullDefault.getString(req.getParameter("commentNtpSid"), "");

        if (usModel != null && !StringUtil.isNullZeroStringSpace(ntpSid)) {

            MlCountMtController cntCon = getCountMtController(req);
            Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req), cntCon);

            boolean commitFlg = false;
            con.setAutoCommit(false);
            try {
                //コメント新規登録
                biz.deleteComment(Integer.valueOf(ntpSid));
                commitFlg = true;

                //ログ
                NtpCommonBiz ntpBiz = new NtpCommonBiz(con, getRequestModel(req));
                String opCode = "コメント 削除";

                ntpBiz.outPutLog(
                 map, req, res, opCode, GSConstLog.LEVEL_TRACE, "");

            } catch (SQLException e) {
                log__.error("コメントの登録に失敗しました" + e);
                throw e;
            } finally {
                if (commitFlg) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }

        }
    }

    /**
     * 編集権限、アドレス帳、ショートメールプラグイン、が利用可能かフォームへ設定する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param form フォーム
     * @param req リクエスト
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setCanUsePluginFlg(Ntp040Form form, HttpServletRequest req, Connection con)
    throws SQLException {

        //プラグイン設定を取得する
        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));

        CommonBiz cmnBiz = new CommonBiz();

        //アドレス帳は利用可能か判定
        if (cmnBiz.isCanUsePlugin(GSConst.PLUGINID_ADDRESS, pconfig)) {
            form.setAddressUseOk(GSConstNippou.PLUGIN_USE);
        } else {
            form.setAddressUseOk(GSConstNippou.PLUGIN_NOT_USE);
        }

        //ショートメールは利用可能か判定
        if (cmnBiz.isCanUsePlugin(GSConstNippou.PLUGIN_ID_SMAIL, pconfig)) {
            form.setSmailUseOk(GSConstNippou.PLUGIN_USE);
        } else {
            form.setSmailUseOk(GSConstNippou.PLUGIN_NOT_USE);
        }

        //スケジュールは利用可能か判定
        if (cmnBiz.isCanUsePlugin(GSConstNippou.PLUGIN_ID_SCHEDULE, pconfig)) {
            form.setScheduleUseOk(GSConstNippou.PLUGIN_USE);
        } else {
            form.setScheduleUseOk(GSConstNippou.PLUGIN_NOT_USE);
        }

        //プロジェクトは利用可能か判定
        if (cmnBiz.isCanUsePlugin(GSConstNippou.PLUGIN_ID_PROJECT, pconfig)) {
            form.setProjectUseOk(GSConstNippou.PLUGIN_USE);
        } else {
            form.setProjectUseOk(GSConstNippou.PLUGIN_NOT_USE);
        }

    }

    /**
     * <br>目標データ取得
     * @param req リクエスト
     * @param res レスポンス
     * @param form アクションフォーム
     * @param con Connection
     * @throws Exception 実行例外
     */
    private void __getTrgData(HttpServletRequest req,
                                HttpServletResponse res,
                                Ntp040Form form,
                                Connection con) throws Exception {


        String year = NullDefault.getString(req.getParameter("year"), "");
        String month = NullDefault.getString(req.getParameter("month"), "");
        String usrSid = NullDefault.getString(req.getParameter("usrSid"), "");
        String nttSid = NullDefault.getString(req.getParameter("nttSid"), "");

        if (!StringUtil.isNullZeroStringSpace(year)
               && !StringUtil.isNullZeroStringSpace(month)
               && !StringUtil.isNullZeroStringSpace(usrSid)
               && !StringUtil.isNullZeroStringSpace(nttSid)) {


            MlCountMtController cntCon = getCountMtController(req);
            Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req), cntCon);

            try {

                JSONObject json = null;

                //目標データ取得
                json = biz.getTargetData(con,
                                Integer.valueOf(year),
                                Integer.valueOf(month),
                                Integer.valueOf(usrSid),
                                Integer.valueOf(nttSid));

                if (json != null) {
                    try {
                        res.setHeader("Cache-Control", "no-cache");
                        res.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = res.getWriter();
                        out.print(json);
                        out.flush();
                    } catch (Exception e) {
                        log__.error("jsonデータ送信失敗(目標)");
                    }
                }


            } catch (SQLException e) {
                log__.error("目標の取得に失敗しました" + e);
                throw e;
            }

        }
    }

    /**
     * <br>目標実績値取得
     * @param req リクエスト
     * @param res レスポンス
     * @param form アクションフォーム
     * @param con Connection
     * @throws Exception 実行例外
     */
    private void __getTrgRecData(HttpServletRequest req,
                                HttpServletResponse res,
                                Ntp040Form form,
                                Connection con) throws Exception {


        String year = NullDefault.getString(req.getParameter("year"), "");
        String month = NullDefault.getString(req.getParameter("month"), "");
        String usrSid = NullDefault.getString(req.getParameter("usrSid"), "");
        String ntgSid = NullDefault.getString(req.getParameter("ntgSid"), "");

        if (!StringUtil.isNullZeroStringSpace(year)
               && !StringUtil.isNullZeroStringSpace(month)
               && !StringUtil.isNullZeroStringSpace(usrSid)
               && !StringUtil.isNullZeroStringSpace(ntgSid)) {


            MlCountMtController cntCon = getCountMtController(req);
            Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req), cntCon);

            try {

                JSONObject json = null;

                //目標データ取得
                json = biz.getTargetRecData(con,
                                Integer.valueOf(year),
                                Integer.valueOf(month),
                                Integer.valueOf(usrSid),
                                Integer.valueOf(ntgSid));

                if (json != null) {
                    try {
                        res.setHeader("Cache-Control", "no-cache");
                        res.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = res.getWriter();
                        out.print(json);
                        out.flush();
                    } catch (Exception e) {
                        log__.error("jsonデータ送信失敗(目標)");
                    }
                }


            } catch (SQLException e) {
                log__.error("目標の取得に失敗しました" + e);
                throw e;
            }

        }
    }

    /**
     * <br>確定ボタンクリック時処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception SQL実行時例外
     */
    private void __doTrgEdit(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        Ntp240Biz biz = new Ntp240Biz(con, getRequestModel(req));

        //画面入力データ取得
        String targetId = NullDefault.getString(req.getParameter("targetId"), "");
        String recordVal = NullDefault.getString(req.getParameter("recordVal"), "");
        String targetVal = NullDefault.getString(req.getParameter("targetVal"), "");

        if (!StringUtil.isNullZeroStringSpace(targetId)) {
            ArrayList<String> list = StringUtil.split("_", targetId);
            String targetYear = list.get(0);
            String targetMonth = list.get(1);
            String selectUsrSid = list.get(2);
            String targetSid = list.get(3);

            ActionErrors errors = null;
            errors = biz.validateCheck(targetVal, recordVal);

            if (errors.size() > 0) {
                List<String> errorList = new ArrayList<String>();
                errorList = __getJsonErrorMsg(req, errors);

                JSONArray json = new JSONArray();
                json = JSONArray.fromObject(errorList);
                if (!json.isEmpty()) {
                    try {
                        res.setHeader("Cache-Control", "no-cache");
                        res.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = res.getWriter();
                        out.print(json);
                        out.flush();
                    } catch (Exception e) {
                        log__.error("jsonデータ送信失敗(目標エラー)");
                    }
                }

            } else {

                //目標登録
                BaseUserModel umodel = getSessionUserModel(req);
                boolean commitFlg = false;
                con.setAutoCommit(false);
                try {
                    biz.setTarget(con, Integer.valueOf(targetYear),
                                       Integer.valueOf(targetMonth),
                                       Integer.valueOf(selectUsrSid),
                                       Integer.valueOf(targetSid),
                                       Long.valueOf(recordVal),
                                       Long.valueOf(targetVal),
                                       umodel);
                    commitFlg = true;
                } catch (SQLException e) {
                    log__.error("目標の登録に失敗しました" + e);
                    throw e;
                } finally {
                    if (commitFlg) {
                        con.commit();
                    } else {
                        con.rollback();
                    }
                }

                //最新の目標取得
                JSONObject jsonData = biz.getPriTargetMdl(
                                        con,
                                        Integer.valueOf(targetYear),
                                        Integer.valueOf(targetMonth),
                                        Integer.valueOf(selectUsrSid),
                                        Integer.valueOf(targetSid));

                if (jsonData != null && !jsonData.isEmpty()) {

                    try {
                        res.setHeader("Cache-Control", "no-cache");
                        res.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = res.getWriter();
                        out.print(jsonData);
                        out.flush();
                    } catch (Exception e) {
                        log__.error("jsonデータ送信失敗(目標)");
                    }

                }
            }
        }
    }


    /**
     * <br>アドレス帳履歴取得
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception SQL実行時例外
     */
    private void __getAdrHistoryData(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req));

        //画面入力データ取得
        String adrHistoryUsrSid = NullDefault.getString(req.getParameter("adrHistoryUsrSid"), "");

        //ページ取得
        String pageNum = NullDefault.getString(req.getParameter("adrPageNum"), "");

        if (!StringUtil.isNullZeroStringSpace(adrHistoryUsrSid)
                && !StringUtil.isNullZeroStringSpace(pageNum)) {

            JSONObject jsonData = biz.getAdrHistoryList(
                                    con, adrHistoryUsrSid, Integer.valueOf(pageNum));

            if (jsonData != null && !jsonData.isEmpty()) {

                try {
                    res.setHeader("Cache-Control", "no-cache");
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = res.getWriter();
                    out.print(jsonData);
                    out.flush();
                } catch (Exception e) {
                    log__.error("jsonデータ送信失敗(アドレス履歴)");
                }
            }
        }

    }

    /**
     * <br>案件履歴取得
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception SQL実行時例外
     */
    private void __getAnkenHistoryData(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req));

        //画面入力データ取得
        String ankenHistoryUsrSid
                 = NullDefault.getString(req.getParameter("ankenHistoryUsrSid"), "");

        //ページ取得
        String pageNum = NullDefault.getString(req.getParameter("ankenPageNum"), "");

        if (!StringUtil.isNullZeroStringSpace(ankenHistoryUsrSid)
                && !StringUtil.isNullZeroStringSpace(pageNum)) {

            JSONObject jsonData = biz.getAnkenHistoryList(
                                    con, ankenHistoryUsrSid, Integer.valueOf(pageNum));

            if (jsonData != null && !jsonData.isEmpty()) {

                try {
                    res.setHeader("Cache-Control", "no-cache");
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = res.getWriter();
                    out.print(jsonData);
                    out.flush();
                } catch (Exception e) {
                    log__.error("jsonデータ送信失敗(案件履歴)");
                }
            }
        }

    }

    /**
     * <br>スケジュールデータ取得
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception SQL実行時例外
     */
    private void __getSchData(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req));

        //画面データ取得
        String schUsrSid = NullDefault.getString(req.getParameter("schUsrSid"), "");
        String dspDate = NullDefault.getString(req.getParameter("schDspDate"), "");


        if (!StringUtil.isNullZeroStringSpace(schUsrSid)
                && !StringUtil.isNullZeroStringSpace(dspDate)) {

            UDate date = new UDate();
            date.setDate(dspDate);
            date.setZeroHhMmSs();

            JSONArray jsonData = biz.getSchData(Integer.valueOf(
                    schUsrSid), con, date);

            if (jsonData != null && !jsonData.isEmpty()) {

                try {
                    res.setHeader("Cache-Control", "no-cache");
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = res.getWriter();
                    out.print(jsonData);
                    out.flush();
                } catch (Exception e) {
                    log__.error("jsonデータ送信失敗(スケジュール情報)");
                }
            }
        }
    }

    /**
     * <br>スケジュールデータ取得
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception SQL実行時例外
     */
    private void __getSchSelectData(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req));

        //画面データ取得
        String schSid = NullDefault.getString(req.getParameter("selSchSid"), "");

        if (!StringUtil.isNullZeroStringSpace(schSid)) {

            JSONObject jsonData = biz.getSchSelectData(Integer.valueOf(
                    schSid), con, getRequestModel(req));

            if (jsonData != null && !jsonData.isEmpty()) {

                try {
                    res.setHeader("Cache-Control", "no-cache");
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = res.getWriter();
                    out.print(jsonData);
                    out.flush();
                } catch (Exception e) {
                    log__.error("jsonデータ送信失敗(スケジュール情報)");
                }
            }
        }
    }

    /**
     * <br>プロジェクトデータ取得
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception SQL実行時例外
     */
    private void __getPrjData(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req));

        //画面データ取得
        String prjUsrSid = NullDefault.getString(req.getParameter("prjUsrSid"), "");
        String dspDate = NullDefault.getString(req.getParameter("prjDspDate"), "");


        if (!StringUtil.isNullZeroStringSpace(prjUsrSid)
                && !StringUtil.isNullZeroStringSpace(dspDate)) {

            UDate date = new UDate();
            date.setDate(dspDate);
            date.setZeroHhMmSs();

            JSONArray jsonData = biz.getPrjData(Integer.valueOf(prjUsrSid), con, date);

            if (jsonData != null && !jsonData.isEmpty()) {

                try {
                    res.setHeader("Cache-Control", "no-cache");
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = res.getWriter();
                    out.print(jsonData);
                    out.flush();
                } catch (Exception e) {
                    log__.error("jsonデータ送信失敗(スケジュール情報)");
                }
            }
        }
    }

    /**
     * <br>プロジェクトデータ取得
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception SQL実行時例外
     */
    private void __getPrjSelectData(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {

        Ntp040Biz biz = new Ntp040Biz(con, getRequestModel(req));

        //画面データ取得
        String prjSid = NullDefault.getString(req.getParameter("selPrjTodoSid"), "");
        String todoSid = "";

        ArrayList<String> prjSids = StringUtil.split("_", prjSid);
        if (!prjSids.isEmpty()) {
            prjSid = String.valueOf(prjSids.get(0));
            todoSid = String.valueOf(prjSids.get(1));
        }

        if (!StringUtil.isNullZeroStringSpace(prjSid)
                && !StringUtil.isNullZeroStringSpace(todoSid)) {

            JSONObject jsonData = biz.getPrjSelectData(
                    Integer.valueOf(prjSid), Integer.valueOf(todoSid), con);

            if (jsonData != null && !jsonData.isEmpty()) {

                try {
                    res.setHeader("Cache-Control", "no-cache");
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = res.getWriter();
                    out.print(jsonData);
                    out.flush();
                } catch (Exception e) {
                    log__.error("jsonデータ送信失敗(TODO情報)");
                }
            }
        }
    }

    /**
     * <br>jsonエラーメッセージ作成
     * @param req リクエスト
     * @param errors エラーメッセージ
     * @throws Exception 実行例外
     * @return errorResult jsonエラーメッセージ
     */
    private List<String> __getJsonErrorMsg(
        HttpServletRequest req, ActionErrors errors) throws Exception {

        @SuppressWarnings("all")
        Iterator iterator = errors.get();

        List<String> errorList = new ArrayList<String>();
        while (iterator.hasNext()) {
            ActionMessage error = (ActionMessage) iterator.next();
            errorList.add(getResources(req).getMessage(error.getKey(), error.getValues()));
        }
        return errorList;
    }


    /**
     * <br>[機  能] json形式でエラーを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param res レスポンス
     * @param errors エラー
     */
    public void setError(
            HttpServletResponse res,
            List<Map<String, Object>> errors
          ) {

        PrintWriter writer = null;
        try {

            JSONObject jsonObject = null;
            String jsonString = "[";

            for (Map<String, Object> errorMap : errors) {
                if (!jsonString.equals("[")) {
                    jsonString += ",";
                }
                jsonObject = JSONObject.fromObject(errorMap);
                jsonString += jsonObject.toString();
            }

            jsonString += "]";

            res.setContentType("text/txt; charset=UTF-8");
            writer = res.getWriter();
            writer.println(jsonString);
            writer.flush();
        } catch (Exception e) {
            log__.error("書き込みに失敗。");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * <br>[機  能] json形式で日報のSIDを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param res レスポンス
     * @param ntpSids 日報SID
     */
    public void setNtpSid(
            HttpServletResponse res,
            List<Integer> ntpSids
          ) {

        PrintWriter out = null;

        try {
            JSONObject jsonObject = null;
            int sid = -1;

            for (int ntpSid : ntpSids) {
                sid = ntpSid;
            }

            Map<String, String> sidMap = new HashMap<String, String>();
            sidMap.put("ntpSid", String.valueOf(sid));
            jsonObject = JSONObject.fromObject(sidMap);

            res.setHeader("Cache-Control", "no-cache");
            res.setContentType("application/json;charset=UTF-8");
            out = res.getWriter();
            out.print(jsonObject.toString());
            out.flush();
        } catch (Exception e) {
            log__.error("日報SID送信失敗(新規登録)");
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    /**
     * <br>[機  能] PDFファイルダウンロード処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException SQL実行例外
     * @throws Exception 実行時例外
     */
    private ActionForward __doDownLoadPdf(ActionMapping map,
            Ntp040Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException, Exception {

        log__.debug("日報ＰＤＦファイルダウンロード処理");
        ActionForward forward = null;

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
                cmnBiz.getTempDir(
                        getTempPath(req), GSConstNippou.PLUGIN_ID_NIPPOU, getRequestModel(req));

        //ディレクトリの作成
        File tmpDir = new File(tempDir);
        tmpDir.mkdirs();
        forward = __createPdf(map, form, req, res, con, tempDir);

        return forward;
    }

    /**
     * <br>[機  能] PDFファイルダウンロード処理を実行
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param outDir 出力先ディレクトリ
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     * @throws IOException ファイルの書き出しに失敗
     * @throws IOToolsException テンポラリディレクトリの削除に失敗
     * @throws TempFileException 添付ファイル情報の取得に失敗
     * @throws Exception 実行例外
     */
    private ActionForward __createPdf(ActionMapping map, Ntp040Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con, String outDir)
            throws SQLException, IOException, IOToolsException, TempFileException, Exception {

        log__.debug("ファイルダウンロード処理(PDF)");
        //アプリケーションルートパス取得
        String appRootPath = getAppRootPath();
        //プラグイン固有のテンポラリパス取得
        CommonBiz cmnBiz = new CommonBiz();
        String outTempDir = IOTools.replaceFileSep(
                cmnBiz.getTempDir(
                        getTempPath(req), GSConstNippou.PLUGIN_ID_NIPPOU, getRequestModel(req))
                + "exppdf/");
        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);

        Ntp040ParamModel paramMdl = new Ntp040ParamModel();
        paramMdl.setParam(form);
        Ntp040Biz biz = new Ntp040Biz(con, reqMdl);
        String outBookName = biz.createPdf(paramMdl, con, appRootPath, outTempDir);
        String tmpOutName = paramMdl.getNtp010SelectDate() + GSConstCommon.ENDSTR_SAVEFILE;
        String outFilePath = IOTools.setEndPathChar(outTempDir) + tmpOutName;
        TempFileUtil.downloadAtachment(req, res, outFilePath, outBookName, Encoding.UTF_8);
        //TEMPディレクトリ削除
        IOTools.deleteDir(IOTools.setEndPathChar(outTempDir));

        String downloadPdf = gsMsg.getMessage(req, "cmn.pdf");
        //ログ出力処理
        NtpCommonBiz ntpBiz = new NtpCommonBiz(con, reqMdl);
        ntpBiz.outPutLog(map, req, res, downloadPdf, GSConstLog.LEVEL_INFO, outBookName,
                Integer.parseInt(form.getNtp010NipSid()), null, GSConstNippou.NTP_LOG_FLG_PDF);

        return null;
    }
}