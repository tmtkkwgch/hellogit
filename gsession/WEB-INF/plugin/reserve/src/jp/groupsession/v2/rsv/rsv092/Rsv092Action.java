package jp.groupsession.v2.rsv.rsv092;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveAction;
import jp.groupsession.v2.rsv.GSConstReserve;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 施設予約 場所・地図画像設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv092Action extends AbstractReserveAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv092Action.class);

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
        Rsv092Form rsvform = (Rsv092Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        //設定ボタン押下
        if (cmd.equals("sisetu_img_touroku")) {
            log__.debug("設定ボタン押下");
            forward = __doConfirmation(map, rsvform, req, res, con);
            //戻るボタン押下
        } else if (cmd.equals("back_to_sisetu_touroku")) {
            log__.debug("戻るボタン押下");
            forward = __doBack(map, rsvform, req, res, con);
        //添付ファイル 削除ボタン押下
        } else if (cmd.equals("delete")) {
            log__.debug("添付ファイル 削除ボタン押下");
            forward = __doDelete(map, rsvform, req, res, con);
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
                                    Rsv092Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {
        ActionForward forward = null;
        Rsv092Biz biz = new Rsv092Biz(getRequestModel(req), con);

        //処理権限判定
        boolean processFlg = false;
        Rsv092ParamModel paramMdl = new Rsv092ParamModel();
        paramMdl.setParam(form);
        processFlg = biz.isPossibleToProcess(paramMdl);
        paramMdl.setFormData(form);

        if (!processFlg) {
            //処理権限無し
            return getSubmitErrorPage(map, req);
        }

        forward = __doRedraw(map, form, req, res, con);

        return forward;
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
                                      Rsv092Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException {

        RequestModel reqMdl = getRequestModel(req);
        Rsv092Biz biz = new Rsv092Biz(reqMdl, con);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir =
         cmnBiz.getTempDir(getTempPath(req), form.getRsv010pluginId(), reqMdl)
                              + GSConstReserve.TEMP_IMG_PLACE_UPLOAD_RSV092 + File.separator;

        log__.debug("****アップロード用ディレクトリパス:" + tempDir);

        //添付ファイル情報セット
        Rsv092ParamModel paramMdl = new Rsv092ParamModel();
        paramMdl.setParam(form);
        biz.setTempFiles(paramMdl, tempDir, con);
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 設定ボタン押下処理
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
                                            Rsv092Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        //テンポラリディレクトリパスを取得
        RequestModel reqMdl = getRequestModel(req);
        CommonBiz cmnBiz = new CommonBiz();

        String plaImgDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                              + GSConstReserve.TEMP_IMG_PLACE_UPLOAD_RSV092 + File.separator;

        String plaImgDataDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                              + GSConstReserve.TEMP_IMG_PLACE_DATA_RSV092 + File.separator;

        //入力チェック
        ActionErrors errors = form.validateCheck(plaImgDir, req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doRedraw(map, form, req, res, con);
        }

        Rsv092Biz biz = new Rsv092Biz(reqMdl, con);

        //コピー先ディレクトリパスを取得
        String copyPlaImgDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                          + GSConstReserve.TEMP_IMG_PLACE_UPLOAD + File.separator;

        String copyPlaImgDataDir
            = cmnBiz.getTempDir(getTempPath(req),
                                form.getRsv010pluginId(), reqMdl)
              + GSConstReserve.TEMP_IMG_PLACE_DATA + File.separator;

        //場所・地図画像をコピーする
        biz.copyImgOthTemp(plaImgDir, copyPlaImgDir);

        //場所・地図画像データをコピーする
        Rsv092ParamModel paramMdl = new Rsv092ParamModel();
        paramMdl.setParam(form);
        biz.copyPlaceImgDataOthTemp(paramMdl, plaImgDataDir, copyPlaImgDataDir, copyPlaImgDir);
        paramMdl.setFormData(form);

        return map.findForward("sisetu_img_touroku");
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
     * @throws Exception 実行時例外
     */
    private ActionForward __doBack(ActionMapping map,
                                            Rsv092Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        RequestModel reqMdl = getRequestModel(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                              + GSConstReserve.TEMP_IMG_PLACE_UPLOAD_RSV092 + File.separator;

        String tempPlaDataDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                              + GSConstReserve.TEMP_IMG_PLACE_DATA_RSV092 + File.separator;

        Rsv092Biz biz = new Rsv092Biz(reqMdl, con);

        biz.deleteDir(tempDir);
        biz.deleteDir(tempPlaDataDir);

        return map.findForward("back_to_sisetu_touroku");
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
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @return ActionForward
     */
    private ActionForward __doDelete(ActionMapping map,
                                      Rsv092Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException {

        RequestModel reqMdl = getRequestModel(req);

        //画像が保存されているテンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                         getTempPath(req), form.getRsv010pluginId(), reqMdl)
                         + GSConstReserve.TEMP_IMG_PLACE_UPLOAD_RSV092 + File.separator;


        //場所画像データのパス(Rsv092画面用)
        String rsv092TempPlaDataDir = cmnBiz.getTempDir(
                                 getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                    + GSConstReserve.TEMP_IMG_PLACE_DATA_RSV092 + File.separator;

        //場所画像データのパス(データ参照用)
        String tempPlaDataDir = cmnBiz.getTempDir(
                                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                                + GSConstReserve.TEMP_IMG_PLACE_DATA + File.separator;


        log__.debug("****削除を行うディレクトリパス:" + tempDir);
        log__.debug("****場所画像データ削除を行うディレクトリパス:" + rsv092TempPlaDataDir);

        //選択された添付ファイルを削除する
        cmnBiz.deleteFile(form.getRsv092selectFiles(), tempDir);
        Rsv092Biz biz = new Rsv092Biz(reqMdl, con);
        Rsv092ParamModel paramMdl = new Rsv092ParamModel();
        paramMdl.setParam(form);
        biz.detPlaceData(paramMdl, rsv092TempPlaDataDir, tempPlaDataDir);
        paramMdl.setFormData(form);

        return __doRedraw(map, form, req, res, con);
    }
}