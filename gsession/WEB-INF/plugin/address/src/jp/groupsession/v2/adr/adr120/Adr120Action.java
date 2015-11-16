package jp.groupsession.v2.adr.adr120;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.adr.AbstractAddressAction;
import jp.groupsession.v2.adr.AdrCommonBiz;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] アドレス帳 会社インポート画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr120Action extends AbstractAddressAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr120Action.class);

    /**
     * <br>[機  能] キャッシュを有効にして良いか判定を行う
     * <br>[解  説] ダウンロード時のみ有効にする
     * <br>[備  考]
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:有効にする,false:無効にする
     */
    public boolean isCacheOk(HttpServletRequest req, ActionForm form) {

        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        //ダウンロードフラグ
        if (cmd.equals("downloadCsv")) {
            return true;
        }
        return false;
    }

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

        log__.debug("START");

        ActionForward forward = null;

        Adr120Form thisForm = (Adr120Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();
        GsMessage gsMsg = new GsMessage();

        if (cmd.equals("importCompanyConfirm")) {
            log__.debug("インポートボタンクリック");
            forward = __doImport(map, thisForm, req, res, con);

        } else if (cmd.equals("backCompanyList")) {
            log__.debug("戻るボタンクリック");
            IOTools.deleteDir(getTempDirPath(req));
            forward = map.findForward("companyList");

        } else if (cmd.equals("downloadCsv")) {
            log__.debug("会社情報取込み用csvファイルクリック");
            __doDownLoadCsv(req, res);

            //ログ出力処理
            AdrCommonBiz adrBiz = new AdrCommonBiz(con);
            adrBiz.outPutLog(
                    map, req, res,
                    gsMsg.getMessage(req, "cmn.download"),
                    GSConstLog.LEVEL_INFO,
                    "import_company.xls");

        } else if (cmd.equals("deleteFile")) {
            //削除ボタンクリック
            IOTools.deleteDir(getTempDirPath(req));
            forward = __doInit(map, thisForm, req, res, con);

        } else if (cmd.equals("addGyosyu")) {
            //追加(業種)ボタンクリック
            forward = __doAddGyosyu(map, thisForm, req, res, con);

        } else if (cmd.equals("deleteGyosyu")) {
            //削除(業種)ボタンクリック
            forward = __doDelGyosyu(map, thisForm, req, res, con);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END");
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
     * @throws Exception 実行例外
     * @return ActionForward
     */
    private ActionForward __doInit(
        ActionMapping map,
        Adr120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //初期表示情報を取得する
        con.setAutoCommit(true);
        Adr120Biz biz = new Adr120Biz(getRequestModel(req));

        Adr120ParamModel paramMdl = new Adr120ParamModel();
        paramMdl.setParam(form);
        biz.getInitData(con, paramMdl, getTempDirPath(req));
        paramMdl.setFormData(form);

        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 追加(閲覧グループ)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doAddGyosyu(
        ActionMapping map,
        Adr120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();

        form.setAdr120atiSid(
                cmnBiz.getAddMember(form.getAdr120atiSid(),
                                    form.getAdr120NoSelectAtiSid()));
        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(閲覧グループ)ボタン押下時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws Exception 実行時例外
     * @return ActionForward
     */
    private ActionForward __doDelGyosyu(
        ActionMapping map,
        Adr120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        form.setAdr120atiSid(
                cmnBiz.getDeleteMember(form.getAdr120selectAtiSid(),
                                    form.getAdr120atiSid()));

        return  __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] インポートボタンクリック時の処理
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
    private ActionForward __doImport(
        ActionMapping map,
        Adr120Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws Exception {

        //入力チェック
        con.setAutoCommit(true);
        ActionErrors errors = form.validateCheck(con, getTempDirPath(req), getRequestModel(req));
        con.setAutoCommit(false);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        saveToken(req);
        return map.findForward("confirmCompanyImport");
    }

    /**
     * <br>[機  能] インポート用ファイルをダウンロードします。
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param res レスポンス
     * @throws Exception ダウンロード時の例外
     */
    private void __doDownLoadCsv(HttpServletRequest req, HttpServletResponse res)
    throws Exception {

        String fileName = "import_company.xls";

        StringBuilder buf = new StringBuilder();
        buf.append(getAppRootPath());
        buf.append(File.separator);
        buf.append(GSConstAddress.PLUGIN_ID_ADDRESS);
        buf.append(File.separator);
        buf.append("templete");
        buf.append(File.separator);
        buf.append(fileName);
        String fullPath = buf.toString();
        log__.debug("FULLPATH=" + fullPath);
        TempFileUtil.downloadAtachment(req, res, fullPath, fileName, Encoding.UTF_8);
    }

}
