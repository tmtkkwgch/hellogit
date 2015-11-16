package jp.groupsession.v2.rsv.rsv091;

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
 * <br>[機  能] 施設予約 施設・設備画像設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv091Action extends AbstractReserveAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv091Action.class);

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
        Rsv091Form rsvform = (Rsv091Form) form;

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
                                    Rsv091Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con) throws Exception {
        ActionForward forward = null;
        Rsv091Biz biz = new Rsv091Biz(getRequestModel(req), con);

        //処理権限判定
        boolean processFlg = false;
        Rsv091ParamModel paramMdl = new Rsv091ParamModel();
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
                                      Rsv091Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException {

        RequestModel reqMdl = getRequestModel(req);
        Rsv091Biz biz = new Rsv091Biz(reqMdl, con);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                              + GSConstReserve.TEMP_IMG_SISETU_UPLOAD_RSV091 + File.separator;

        log__.debug("****Rsv091画面アップロード用ディレクトリパス:" + tempDir);

        //添付ファイル情報セット
        Rsv091ParamModel paramMdl = new Rsv091ParamModel();
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
                                            Rsv091Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        RequestModel reqMdl = getRequestModel(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                              + GSConstReserve.TEMP_IMG_SISETU_UPLOAD_RSV091 + File.separator;
        //入力チェック
        ActionErrors errors = form.validateCheck(tempDir, req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doRedraw(map, form, req, res, con);
        }
        Rsv091Biz biz = new Rsv091Biz(reqMdl, con);

        //コピー先ディレクトリパスを取得
        String copyDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                          + GSConstReserve.TEMP_IMG_SISETU_UPLOAD + File.separator;

        //ディレクトリをコピーする
        biz.copyImgOthTemp(tempDir, copyDir);

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
                                            Rsv091Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        RequestModel reqMdl = getRequestModel(req);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), reqMdl)
                              + GSConstReserve.TEMP_IMG_SISETU_UPLOAD_RSV091 + File.separator;

        Rsv091Biz biz = new Rsv091Biz(reqMdl, con);
        biz.deleteDir(tempDir);

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
                                      Rsv091Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException {

        //画像が保存されているテンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), form.getRsv010pluginId(), getRequestModel(req))
                + GSConstReserve.TEMP_IMG_SISETU_UPLOAD_RSV091 + File.separator;

        log__.debug("****削除を行うディレクトリパス:" + tempDir);

        //選択された添付ファイルを削除する
        cmnBiz.deleteFile(form.getRsv091selectFiles(), tempDir);

        return __doRedraw(map, form, req, res, con);
    }

}