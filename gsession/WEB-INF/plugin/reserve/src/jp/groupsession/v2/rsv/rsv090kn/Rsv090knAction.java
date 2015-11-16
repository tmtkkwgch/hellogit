package jp.groupsession.v2.rsv.rsv090kn;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
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
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 施設予約 [施設登録確認]画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv090knAction extends AbstractReserveAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv090knAction.class);

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
        Rsv090knForm rsvform = (Rsv090knForm) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        //確定ボタン押下
        if (cmd.equals("sisetu_settei_kakutei")) {
            log__.debug("確定ボタン押下");
            forward = __doUpdate(map, rsvform, req, res, con);
        //戻るボタン押下
        } else if (cmd.equals("back_to_sisetu_settei_input")) {
            log__.debug("戻るボタン押下");
            forward = __doBack(map, rsvform, req, res, con);
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
                                    Rsv090knForm form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {

        try {
            con.setAutoCommit(true);
            Rsv090knBiz biz = new Rsv090knBiz(getRequestModel(req), con);

            //処理権限判定
            boolean processFlg = false;
            Rsv090knParamModel paramMdl = new Rsv090knParamModel();
            paramMdl.setParam(form);
            processFlg = biz.isPossibleToProcess(paramMdl);
            paramMdl.setFormData(form);

            if (!processFlg) {
                //処理権限無し
                return getSubmitErrorPage(map, req);
            }

            //初期表示データセット
            paramMdl = new Rsv090knParamModel();
            paramMdl.setParam(form);
            biz.setInitData(paramMdl);
            paramMdl.setFormData(form);

            con.setAutoCommit(false);
        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        }

        return __doRedraw(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 戻るボタン押下処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws IOToolsException ファイルアクセス時例外
     */
    private ActionForward __doBack(ActionMapping map,
                                      Rsv090knForm form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws IOToolsException {

        return map.findForward("back_to_sisetu_settei_input");
    }

    /**
     * <br>[機  能] 再描画処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws Exception 実行例外
     */
    private ActionForward __doRedraw(ActionMapping map,
                                      Rsv090knForm form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException, Exception {

        RequestModel reqMdl = getRequestModel(req);
        Rsv090knBiz biz = new Rsv090knBiz(reqMdl, con);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();

        String sisetuTempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                           + GSConstReserve.TEMP_IMG_SISETU_UPLOAD + File.separator;

        String placeTempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                           + GSConstReserve.TEMP_IMG_PLACE_UPLOAD + File.separator;

        String placeTempDataDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                           + GSConstReserve.TEMP_IMG_PLACE_DATA + File.separator;

        //添付ファイル情報セット
        Rsv090knParamModel paramMdl = new Rsv090knParamModel();
        paramMdl.setParam(form);
        biz.setTempFiles(paramMdl, sisetuTempDir, placeTempDir, placeTempDataDir, con);
        paramMdl.setFormData(form);

        //施設画像が設定されている場合
        //デフォルト表示画像名をセット
        if (form.getRsv090knSetImgFlg().equals("1")) {
            String fileId = form.getRsv090SisetuImgDefoValue();
            Cmn110FileModel fMdl = __getCmn110FileModel(sisetuTempDir, fileId);
            form.setRsv090knDefoDspImgName(fMdl.getFileName());
        }

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 添付ファイルモデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param tempDir テンポラリディレクトリ
     * @param fileId ファイルID
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private Cmn110FileModel __getCmn110FileModel(String tempDir,
                                                  String fileId)
        throws Exception {

        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(tempDir, fileId.concat(GSConstCommon.ENDSTR_OBJFILE));
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;

        return fMdl;
    }

    /**
     * <br>[機  能] 確定ボタン押下時処理
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
    private ActionForward __doUpdate(ActionMapping map,
                                      Rsv090knForm form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con) throws Exception {

        con.setAutoCommit(false);
        boolean commitFlg = false;
        RequestModel reqMdl = getRequestModel(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl);

        try {

            //2重投稿
            if (!isTokenValid(req, true)) {
                log__.info("２重投稿");
                return getSubmitErrorPage(map, req);
            }

            String sisetuTempDir = tempDir + GSConstReserve.TEMP_IMG_SISETU_UPLOAD + File.separator;

            //入力チェック
            ActionErrors errors = form.validateCheck(con, sisetuTempDir, req);
            if (!errors.isEmpty()) {
                addErrors(req, errors);
                return __doInit(map, form, req, res, con);
            }

            String placeTempDir = cmnBiz.getTempDir(
                    getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                               + GSConstReserve.TEMP_IMG_PLACE_UPLOAD
                                               + File.separator;

            String plaTempDataDir
                = cmnBiz.getTempDir(
                        getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                                    + GSConstReserve.TEMP_IMG_PLACE_DATA
                                                    + File.separator;

            //テンポラリディレクトリに週間・日間用施設画像を保存
            String weekDayDsptempDir =
                cmnBiz.getTempDir(
                        getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                + GSConstReserve.TEMP_IMG_SISETU_DSP + File.separator;

            Rsv090knBiz biz = new Rsv090knBiz(reqMdl, con);

            //テンポラリディレクトリから施設/設備画像データを取得
            CommonBiz commonBiz = new CommonBiz();
            List<LabelValueBean> sisetuFileList = commonBiz.getTempFileLabelList(sisetuTempDir);

            if (sisetuFileList != null && sisetuFileList.size() > 0) {
                //週間・日間で表示する画像を別テンポラリディレクトリに移動
                Rsv090knParamModel paramMdl = new Rsv090knParamModel();
                paramMdl.setParam(form);
                biz.setWeekDayImgOthTemp(paramMdl, sisetuTempDir, weekDayDsptempDir);
                paramMdl.setFormData(form);
            }

            //DB更新
            MlCountMtController cntCon = getCountMtController(req);
            Rsv090knParamModel paramMdl = new Rsv090knParamModel();
            paramMdl.setParam(form);
            biz.updateSisetuData(paramMdl, cntCon, getAppRootPath(),
                    sisetuTempDir, weekDayDsptempDir, placeTempDir, plaTempDataDir);
            paramMdl.setFormData(form);

            //ログ出力処理
            AbstractReserveBiz rsvBiz = new AbstractReserveBiz(con);
            GsMessage gsMsg = new GsMessage();
            String opCode = "";
            if (form.getRsv090ProcMode().equals(GSConstReserve.PROC_MODE_SINKI)) {
                opCode = gsMsg.getMessage(req, "cmn.entry");
            } else if (form.getRsv090ProcMode().equals(GSConstReserve.PROC_MODE_EDIT)) {
                opCode = gsMsg.getMessage(req, "cmn.change");
            }
            rsvBiz.outPutLog(
                    map, req, res, opCode, GSConstLog.LEVEL_TRACE,
                    "[name]" + form.getRsv090SisetuName());

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

        //テンポラリディレクトリ内のファイルを削除
        IOTools.deleteInDir(tempDir);

        return __doUpdateComp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 施設更新完了後の画面遷移設定
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
    private ActionForward __doUpdateComp(ActionMapping map,
                                          Rsv090knForm form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con) throws Exception {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("updateComp");
        cmn999Form.setUrlOK(forwardOk.getPath());

        //メッセージ
        String msgId = "";
        if (form.getRsv090ProcMode().equals(GSConstReserve.PROC_MODE_SINKI)) {
            msgId = "touroku.kanryo.object";
        } else if (form.getRsv090ProcMode().equals(GSConstReserve.PROC_MODE_EDIT)) {
            msgId = "hensyu.kanryo.object";
        }

        MessageResources msgRes = getResources(req);
        GsMessage gsMsg = new GsMessage();
        cmn999Form.setMessage(msgRes.getMessage(msgId, gsMsg.getMessage(req, "reserve.rsv070.1")));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("rsvBackPgId", form.getRsvBackPgId());
        cmn999Form.addHiddenParam("rsvDspFrom", form.getRsvDspFrom());
        cmn999Form.addHiddenParam("rsvSelectedGrpSid", form.getRsvSelectedGrpSid());
        cmn999Form.addHiddenParam("rsvSelectedSisetuSid", form.getRsvSelectedSisetuSid());
        cmn999Form.addHiddenParam("rsv050SortRadio", form.getRsv050SortRadio());
        cmn999Form.addHiddenParam("rsv080EditGrpSid", form.getRsv080EditGrpSid());
        cmn999Form.addHiddenParam("rsv080SortRadio", form.getRsv080SortRadio());
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
}