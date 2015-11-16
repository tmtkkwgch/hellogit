package jp.groupsession.v2.rsv.rsv090;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveAction;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.rsv090.model.Rsv090DspModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 施設予約 施設登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv090Action extends AbstractReserveAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv090Action.class);

    /**
     * <br>[機  能] キャッシュを有効にして良いか判定を行う
     * <br>[解  説] ダウンロード時のみ有効にする
     * <br>[備  考]
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:有効にする,false:無効にする
     */
    public boolean isCacheOk(HttpServletRequest req, ActionForm form) {

        //CMD
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("getImageFilePlace") || cmd.equals("getImageFileSisetu")) {
            return true;
        }

        return false;
    }

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
        Rsv090Form rsvform = (Rsv090Form) form;

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
            forward = __doSisetuDeleteOk(map, rsvform, req, res, con);
        //戻るボタン押下
        } else if (cmd.equals("back_to_sisetu_zyoho_settei")) {
            log__.debug("戻るボタン押下");
            forward = __doBack(map, rsvform, req, res, con);
        //施設画像登録画面
        } else if (cmd.equals("sisetu_img_toroku")) {
            log__.debug("施設画像登録画面遷移");
            forward = __doTemp(map, rsvform, req, res, con, GSConstReserve.TEMP_IMG_KBN_SISETU);
            //施設画像登録画面
        } else if (cmd.equals("place_img_toroku")) {
            log__.debug("場所画像登録画面遷移");
            forward = __doTemp(map, rsvform, req, res, con, GSConstReserve.TEMP_IMG_KBN_PLACE);
        //施設・設備画像ダウンロード
        } else if (cmd.equals("getImageFileSisetu")) {
            log__.debug("施設・設備画像ダウンロード");
            forward = __doGetImageFile(map, rsvform, req, res, con, 0);
        //場所・地図画像ダウンロード
        } else if (cmd.equals("getImageFilePlace")) {
            log__.debug("場所・地図画像ダウンロード");
            forward = __doGetImageFile(map, rsvform, req, res, con, 1);
        //施設グループコンボ変更
        } else if (cmd.equals("chGrpComb")) {
            log__.debug("施設グループコンボ変更");
            forward = __doChGrpComb(map, rsvform, req, res, con, cmd);
        //初期表示処理
        } else {
            log__.debug("初期表示処理");
            forward = __doInit(map, rsvform, req, res, con, cmd);
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
     * @param cmd コマンドパラメータ
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Rsv090Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con,
                                    String cmd) throws Exception {

        RequestModel reqMdl = getRequestModel(req);

        try {
            con.setAutoCommit(true);
            Rsv090Biz biz = new Rsv090Biz(reqMdl, con);

            boolean processFlg = false;

            Rsv090ParamModel paramMdl = new Rsv090ParamModel();
            paramMdl.setParam(form);
            processFlg = biz.isPossibleToProcess(paramMdl);
            paramMdl.setFormData(form);

            //処理権限判定
            if (!processFlg) {
                //処理権限無し
                return getSubmitErrorPage(map, req);
            }

            //テンポラリディレクトリパスを取得
            CommonBiz cmnBiz = new CommonBiz();
            String tempDir = cmnBiz.getTempDir(
                    getTempPath(req), form.getRsv010pluginId(), reqMdl);

            //初期表示設定
            paramMdl = new Rsv090ParamModel();
            paramMdl.setParam(form);
            Rsv090DspModel rsv090DspModel = biz.setSisetuData(paramMdl,
                    getAppRootPath(), tempDir, cmd,
                    GroupSession.getResourceManager().getDomain(req));
            paramMdl.setFormData(form);

            //場所・地図画像データファイルパス
            String placeImgDir = tempDir
                                 + GSConstReserve.TEMP_IMG_PLACE_UPLOAD
                                 + File.separator;

            boolean initFlg = form.isRsv090InitFlg();

            //初期表示時かつ編集時
            if (initFlg && rsv090DspModel != null) {

                //場所・地図画像データ保存用ファイルパス
                String placeImgDataDir = tempDir
                                       + GSConstReserve.TEMP_IMG_PLACE_DATA
                                       + File.separator;

                //DBから取得した場所・地図画像データを保存する
                paramMdl = new Rsv090ParamModel();
                paramMdl.setParam(form);
                biz.setPlaceImgData(paramMdl, placeImgDir, placeImgDataDir, rsv090DspModel);
                paramMdl.setFormData(form);

            }

            //初期表示フラグOFF
            form.setRsv090InitFlg(false);

            con.setAutoCommit(false);
        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        }

        return __doRedraw(map, form, req, res, con);
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
     */
    private ActionForward __doRedraw(ActionMapping map,
                                      Rsv090Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException {

        RequestModel reqMdl = getRequestModel(req);

        con.setAutoCommit(true);
        Rsv090Biz biz = new Rsv090Biz(reqMdl, con);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();

        //施設/設備画像ファイルパス
        String tempDirSisetu = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                                 + GSConstReserve.TEMP_IMG_SISETU_UPLOAD
                                                 + File.separator;

        //場所/地図画像データ保存用ファイルパス
        String tempDirPlaceData = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                                + GSConstReserve.TEMP_IMG_PLACE_DATA
                                                + File.separator;



        Rsv090ParamModel paramMdl = new Rsv090ParamModel();
        paramMdl.setParam(form);

        //施設グループ情報セット
        biz.setSisetuGroup(paramMdl);

        //添付ファイル情報セット
        biz.setTempFiles(paramMdl, tempDirSisetu, tempDirPlaceData);

        //施設グループコンボをセット
        biz.setSisetuGrpCombo(paramMdl);

        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 戻るボタン押下時処理
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
                                    Rsv090Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws IOToolsException {

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), getRequestModel(req));

        //テンポラリディレクトリのファイル削除を行う
        Rsv090Biz biz = new Rsv090Biz(getRequestModel(req), con);
        biz.doDeleteFile(tempDir);

        return map.findForward("back_to_sisetu_zyoho_settei");
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
     * @param fileKbn ファイル区分 0:施設/設備画像 1:場所/地図画像
     * @throws SQLException SQL実行例外
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doGetImageFile(
        ActionMapping map,
        Rsv090Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con,
        int fileKbn) throws SQLException, Exception {

        CommonBiz cmnBiz = new CommonBiz();

        String endDir = "";
        if (fileKbn == 0) {
            endDir = GSConstReserve.TEMP_IMG_SISETU_UPLOAD;
        } else {
            endDir = GSConstReserve.TEMP_IMG_PLACE_UPLOAD;
        }

        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), GSConstReserve.PLUGIN_ID_RESERVE, getRequestModel(req))
                                         + endDir + File.separator;

        String fileId = form.getRsv090BinSid();

        Cmn110FileModel fMdl = __getCmn110FileModel(tempDir, fileId);

        //添付ファイル保存用のパスを取得する(フルパス)
        String filePath = tempDir + fileId.concat(GSConstCommon.ENDSTR_SAVEFILE);
        filePath = IOTools.replaceFileSep(filePath);

        //ログ出力処理
        GsMessage gsMsg = new GsMessage();
        AbstractReserveBiz rsvBiz = new AbstractReserveBiz(con);
        rsvBiz.outPutLog(
                map, req, res,
                gsMsg.getMessage(req, "cmn.download"),
                GSConstLog.LEVEL_INFO, fMdl.getFileName());

        //時間のかかる処理の前にコネクションを破棄
        JDBCUtil.closeConnectionAndNull(con);
        //ファイルをダウンロードする
        TempFileUtil.downloadInline(req, res, filePath, fMdl.getFileName(), Encoding.UTF_8);

        return null;
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
     * <br>[機  能] OKボタン押下処理
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
                                            Rsv090Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        RequestModel reqMdl = getRequestModel(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                           + GSConstReserve.TEMP_IMG_SISETU_UPLOAD + File.separator;

        String tmpPlaceDataDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                           + GSConstReserve.TEMP_IMG_PLACE_DATA + File.separator;

        //フォームから取得した場所画像データを保存する
        Rsv090Biz biz = new Rsv090Biz(reqMdl, con);
        Rsv090ParamModel paramMdl = new Rsv090ParamModel();
        paramMdl.setParam(form);
        biz.savePlaceTempFilesData(paramMdl, tmpPlaceDataDir);
        paramMdl.setFormData(form);

        //入力チェック
        ActionErrors errors = form.validateCheck(con, tempDir, req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doRedraw(map, form, req, res, con);
        }

        saveToken(req);

        return map.findForward("sisetu_toroku_kakunin");
    }

    /**
     * <br>[機  能] 添付ボタン押下処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param tempType 添付タイプ 0:施設・設備画像 1:場所・地図画像
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doTemp(ActionMapping map,
                                            Rsv090Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con,
                                            int tempType)
        throws Exception {

        RequestModel reqMdl = getRequestModel(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();

        String exDirType = "";
        String copyDirType = "";
        String moveForward = "";

        Rsv090Biz biz = new Rsv090Biz(reqMdl, con);

        //場所画像データパスを取得
        String exPlaImgDataDir = cmnBiz.getTempDir(getTempPath(req),
                                 form.getRsv010pluginId(), reqMdl)
                                 + GSConstReserve.TEMP_IMG_PLACE_DATA
                                 + File.separator;

        //フォームに入力したデータを保存する
        Rsv090ParamModel paramMdl = new Rsv090ParamModel();
        paramMdl.setParam(form);
        biz.savePlaceTempFilesData(paramMdl, exPlaImgDataDir);
        paramMdl.setFormData(form);

        if (tempType == GSConstReserve.TEMP_IMG_KBN_SISETU) {
            //施設・設備画像
            exDirType = GSConstReserve.TEMP_IMG_SISETU_UPLOAD;
            copyDirType = GSConstReserve.TEMP_IMG_SISETU_UPLOAD_RSV091;
            moveForward = "sisetu_img_toroku";

        } else {
            //場所・地図画像
            exDirType = GSConstReserve.TEMP_IMG_PLACE_UPLOAD;
            copyDirType = GSConstReserve.TEMP_IMG_PLACE_UPLOAD_RSV092;
            moveForward = "place_img_toroku";
            String copyPlaImgDataDir = cmnBiz.getTempDir(getTempPath(req),
                                                     form.getRsv010pluginId(), reqMdl)
                                                         + GSConstReserve.TEMP_IMG_PLACE_DATA_RSV092
                                                         + File.separator;

            //場所画像データ用ディレクトリをコピーする
            biz.copyImgOthTemp(exPlaImgDataDir, copyPlaImgDataDir);
        }

        String exTempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                         + exDirType + File.separator;

        String copyTempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                         + copyDirType + File.separator;


        //ディレクトリをコピーする
        biz.copyImgOthTemp(exTempDir, copyTempDir);

        return map.findForward(moveForward);
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
    private ActionForward __doSisetuDeleteOk(ActionMapping map,
                                              Rsv090Form form,
                                              HttpServletRequest req,
                                              HttpServletResponse res,
                                              Connection con) throws Exception {

        con.setAutoCommit(false);
        boolean commitFlg = false;

        try {

            RequestModel reqMdl = getRequestModel(req);
            Rsv090Biz biz = new Rsv090Biz(reqMdl, con);

            //削除処理実行
            Rsv090ParamModel paramMdl = new Rsv090ParamModel();
            paramMdl.setParam(form);
            biz.doSisetuDelete(paramMdl);
            paramMdl.setFormData(form);

            //ログ出力処理
            GsMessage gsMsg = new GsMessage(reqMdl);
            AbstractReserveBiz rsvBiz = new AbstractReserveBiz(con);
            rsvBiz.outPutLog(
                    map, req, res, gsMsg.getMessage("cmn.delete"),
                    GSConstLog.LEVEL_TRACE, "[name]" + form.getRsv090SisetuName());

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
     * <br>[機  能] 施設グループコンボ変更処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param cmd コマンドパラメータ
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doChGrpComb(ActionMapping map,
                                            Rsv090Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con,
                                            String cmd)
        throws Exception {

        RequestModel reqMdl = getRequestModel(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tmpPlaceDataDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                           + GSConstReserve.TEMP_IMG_PLACE_DATA + File.separator;

        //フォームから取得した場所画像データを保存する
        Rsv090Biz biz = new Rsv090Biz(reqMdl, con);
        Rsv090ParamModel paramMdl = new Rsv090ParamModel();
        paramMdl.setParam(form);
        biz.savePlaceTempFilesData(paramMdl, tmpPlaceDataDir);
        paramMdl.setFormData(form);


        return __doInit(map, form, req, res, con, cmd);
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
                                              Rsv090Form form,
                                              HttpServletRequest req,
                                              HttpServletResponse res,
                                              Connection con) throws Exception {

        RequestModel reqMdl = getRequestModel(req);

        //場所画像データを保存する
        CommonBiz cmnBiz = new CommonBiz();
        String tmpPlaceDataDir = cmnBiz.getTempDir(getTempPath(req),
                                 GSConstReserve.PLUGIN_ID_RESERVE, reqMdl)
                                                   + GSConstReserve.TEMP_IMG_PLACE_DATA
                                                   + File.separator;

        Rsv090Biz biz = new Rsv090Biz(reqMdl, con);
        //フォームに入力したデータを保存する
        Rsv090ParamModel paramMdl = new Rsv090ParamModel();
        paramMdl.setParam(form);
        biz.savePlaceTempFilesData(paramMdl, tmpPlaceDataDir);
        paramMdl.setFormData(form);

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("redraw");
        cmn999Form.setUrlOK(forwardOk.getPath() + "?" + GSConst.P_CMD + "=deleteOk");

        //キャンセルボタンクリック時遷移先
        cmn999Form.setUrlCancel(forwardOk.getPath()
                + "?" + GSConst.P_CMD + "=back_to_sisetu_settei_input");

        paramMdl = new Rsv090ParamModel();
        paramMdl.setParam(form);
        String sisetuName = biz.getSisetuName(paramMdl);
        paramMdl.setFormData(form);


        //メッセージ
        MessageResources msgRes = getResources(req);
        cmn999Form.setMessage(
             msgRes.getMessage("delete.kakunin.sisetu",
                     StringUtilHtml.transToHTmlPlusAmparsant(sisetuName)));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("rsvBackPgId", form.getRsvBackPgId());
        cmn999Form.addHiddenParam("rsvDspFrom", form.getRsvDspFrom());
        cmn999Form.addHiddenParam("rsvSelectedGrpSid", form.getRsvSelectedGrpSid());
        cmn999Form.addHiddenParam("rsvSelectedSisetuSid", form.getRsvSelectedSisetuSid());
        cmn999Form.addHiddenParam("rsv050SortRadio", form.getRsv050SortRadio());
        cmn999Form.addHiddenParam("rsv080EditGrpSid", form.getRsv080EditGrpSid());
        cmn999Form.addHiddenParam("rsv080SortRadio", form.getRsv080SortRadio());
        cmn999Form.addHiddenParam("rsv090InitFlg", String.valueOf(form.isRsv090InitFlg()));
        cmn999Form.addHiddenParam("rsv090ProcMode", form.getRsv090ProcMode());
        cmn999Form.addHiddenParam("rsv090EditGrpSid", form.getRsv090EditGrpSid());
        cmn999Form.addHiddenParam("rsv090EditSisetuSid", form.getRsv090EditSisetuSid());
        cmn999Form.addHiddenParam("rsv090SisanKanri", form.getRsv090SisanKanri());
        cmn999Form.addHiddenParam("rsv090SisetuId", form.getRsv090SisetuId());
        cmn999Form.addHiddenParam("rsv090SisetuName", form.getRsv090SisetuName());
        cmn999Form.addHiddenParam("rsv090Prop1Value", form.getRsv090Prop1Value());
        cmn999Form.addHiddenParam("rsv090Prop2Value", form.getRsv090Prop2Value());
        cmn999Form.addHiddenParam("rsv090Prop3Value", form.getRsv090Prop3Value());
        cmn999Form.addHiddenParam("rsv090Prop4Value", form.getRsv090Prop4Value());
        cmn999Form.addHiddenParam("rsv090Prop5Value", form.getRsv090Prop5Value());
        cmn999Form.addHiddenParam("rsv090Prop6Value", form.getRsv090Prop6Value());
        cmn999Form.addHiddenParam("rsv090Prop7Value", form.getRsv090Prop7Value());
        cmn999Form.addHiddenParam("rsv090Biko", form.getRsv090Biko());
        cmn999Form.addHiddenParam("rsv090PlaceComment", form.getRsv090PlaceComment());
        cmn999Form.addHiddenParam("rsv090SisetuImgDefoValue", form.getRsv090SisetuImgDefoValue());

        cmn999Form.addHiddenParam("rsv090SisetuIdDspKbn", form.getRsv090SisetuIdDspKbn());
        cmn999Form.addHiddenParam("rsv090SisanKanriDspKbn", form.getRsv090SisanKanriDspKbn());
        cmn999Form.addHiddenParam("rsv090Prop1ValueDspKbn", form.getRsv090Prop1ValueDspKbn());
        cmn999Form.addHiddenParam("rsv090Prop2ValueDspKbn", form.getRsv090Prop2ValueDspKbn());
        cmn999Form.addHiddenParam("rsv090Prop3ValueDspKbn", form.getRsv090Prop3ValueDspKbn());
        cmn999Form.addHiddenParam("rsv090Prop4ValueDspKbn", form.getRsv090Prop4ValueDspKbn());
        cmn999Form.addHiddenParam("rsv090Prop5ValueDspKbn", form.getRsv090Prop5ValueDspKbn());
        cmn999Form.addHiddenParam("rsv090Prop6ValueDspKbn", form.getRsv090Prop6ValueDspKbn());
        cmn999Form.addHiddenParam("rsv090Prop7ValueDspKbn", form.getRsv090Prop7ValueDspKbn());
        cmn999Form.addHiddenParam("rsv090BikoDspKbn", form.getRsv090BikoDspKbn());
        cmn999Form.addHiddenParam("rsv090PlaceCommentDspKbn", form.getRsv090PlaceCommentDspKbn());
        cmn999Form.addHiddenParam("rsv090SisetuImgDefoDspKbn", form.getRsv090SisetuImgDefoDspKbn());

        cmn999Form.addHiddenParam("rsv100InitFlg",
                String.valueOf(form.isRsv100InitFlg()));
        cmn999Form.addHiddenParam("rsv100SearchFlg",
                String.valueOf(form.isRsv100SearchFlg()));
        cmn999Form.addHiddenParam("rsv100SortKey", form.getRsv100SortKey());
        cmn999Form.addHiddenParam("rsv100OrderKey", form.getRsv100OrderKey());
        cmn999Form.addHiddenParam("rsv100PageTop", form.getRsv100PageTop());
        cmn999Form.addHiddenParam("rsv100PageBottom", form.getRsv100PageBottom());
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
                                          Rsv090Form form,
                                          HttpServletRequest req,
                                          HttpServletResponse res,
                                          Connection con) throws Exception {

        Cmn999Form cmn999Form = new Cmn999Form();
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        //OKボタンクリック時遷移先
        ActionForward forwardOk = map.findForward("back_to_sisetu_zyoho_settei");
        cmn999Form.setUrlOK(forwardOk.getPath());

        //メッセージ
        MessageResources msgRes = getResources(req);
        GsMessage gsMsg = new GsMessage();
        cmn999Form.setMessage(
             msgRes.getMessage("sakujo.kanryo.object", gsMsg.getMessage(req, "reserve.rsv070.1")));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("rsvBackPgId", form.getRsvBackPgId());
        cmn999Form.addHiddenParam("rsvDspFrom", form.getRsvDspFrom());
        cmn999Form.addHiddenParam("rsvSelectedGrpSid", form.getRsvSelectedGrpSid());
        cmn999Form.addHiddenParam("rsvSelectedSisetuSid", form.getRsvSelectedSisetuSid());
        cmn999Form.addHiddenParam("rsv050SortRadio", form.getRsv050SortRadio());
        cmn999Form.addHiddenParam("rsv080EditGrpSid", form.getRsv080EditGrpSid());
        cmn999Form.addHiddenParam("rsv080SortRadio", form.getRsv080SortRadio());
        cmn999Form.addHiddenParam("Rsv090SisetuName", form.getRsv090SisetuName());
        cmn999Form.addHiddenParam("rsv100InitFlg",
                String.valueOf(form.isRsv100InitFlg()));
        cmn999Form.addHiddenParam("rsv100SearchFlg",
                String.valueOf(form.isRsv100SearchFlg()));
        cmn999Form.addHiddenParam("rsv100SortKey", form.getRsv100SortKey());
        cmn999Form.addHiddenParam("rsv100OrderKey", form.getRsv100OrderKey());
        cmn999Form.addHiddenParam("rsv100PageTop", form.getRsv100PageTop());
        cmn999Form.addHiddenParam("rsv100PageBottom", form.getRsv100PageBottom());
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