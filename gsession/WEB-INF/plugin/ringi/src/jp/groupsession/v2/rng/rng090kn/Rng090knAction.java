package jp.groupsession.v2.rng.rng090kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.ObjectFile;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn110.Cmn110FileModel;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.AbstractRingiAction;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 稟議 テンプレート登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng090knAction extends AbstractRingiAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng090knAction.class);

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        Rng090knForm thisForm = (Rng090knForm) form;
        int tFlg = thisForm.getRngTemplateMode();
        if (tFlg == RngConst.RNG_TEMPLATE_SHARE) {
            return false;
        } else {
            return true;
        }
    }

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

        if (cmd.equals("fileDownload")) {
            log__.debug("添付ファイルダウンロード");
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
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        ActionForward forward = null;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD = " + cmd);

        Rng090knForm thisForm = (Rng090knForm) form;
        if (cmd.equals("rng090back")) {
            log__.debug("*** 内容テンプレート登録。");
            forward = map.findForward("rng090back");

        } else if (cmd.equals("rng060")) {
            log__.debug("*** 内容テンプレート一覧。");
            forward = map.findForward("rng060");

        } else if (cmd.equals("cmn999kakutei")) {
            log__.debug("*** 確定。");
            forward = __doKakutei(map, thisForm, req, res, con);

        } else if (cmd.equals("fileDownload")) {
            log__.debug("*** 添付ファイルダウンロード");
            forward = __doDownLoad(map, thisForm, req, res, con);

        } else {
            log__.debug("*** 初期表示を行います。");
            forward = __doInit(map, thisForm, req, res, con);
        }

        return forward;
    }

    /**
     * <br>[機  能] 初期表示
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng010Form
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doInit(ActionMapping map, Rng090knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

        con.setAutoCommit(true);
        Rng090knParamModel paramMdl = new Rng090knParamModel();
        paramMdl.setParam(form);
        Rng090knBiz biz = new Rng090knBiz(con);
        biz.initDsp(paramMdl, _getRingiTemplateDir(req), con, getRequestModel(req));
        paramMdl.setFormData(form);
        con.setAutoCommit(false);
        return map.getInputForward();
     }

    /**
     * <br>[機  能] 確定ボタンクリック時
     * <br>[解  説]
     * <br>[備  考]
     * @param map ActionMapping
     * @param form Rng090knForm
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param con DB Connection
     * @return ActionForward
     * @throws Exception 実行時例外
     */
    public ActionForward __doKakutei(ActionMapping map, Rng090knForm form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws Exception {

      //トランザクショントークンチェック
        if (!isTokenValid(req, true)) {
            log__.info("トランザクショントークンエラー");
            return getSubmitErrorPage(map, req);
        }

        ActionErrors errors = new ActionErrors();
        errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        ActionForward forward = null;
        boolean commit = false;
        try {
            Rng090knParamModel paramMdl = new Rng090knParamModel();
            paramMdl.setParam(form);
            Rng090knBiz biz = new Rng090knBiz(con);
            //テンプレート情報の登録
            biz.registRngTpl(
                    paramMdl,
                    getCountMtController(req),
                    getSessionUserSid(req),
                    getAppRootPath(),
                    _getRingiTemplateDir(req));
            paramMdl.setFormData(form);

            forward = __setCompPageParam(map, req, form);

            RequestModel reqMdl = getRequestModel(req);
            GsMessage gsMsg = new GsMessage(reqMdl);
            String entry = gsMsg.getMessage("cmn.entry");
            String edit = gsMsg.getMessage("cmn.edit");

            //ログ出力処理
            RngBiz rngBiz = new RngBiz(con);
            String opCode = "";

            if (form.getRngTplCmdMode() == RngConst.RNG_CMDMODE_ADD) {
                opCode = entry;
            } else if (form.getRngTplCmdMode() == RngConst.RNG_CMDMODE_EDIT) {
                opCode = edit;
            }

            String plcTmp = gsMsg.getMessage("cmn.shared.template");
            String prvTmp = gsMsg.getMessage("cmn.personal.template");

            String tmpType = "";
            //共有
            if (form.getRngTemplateMode() == RngConst.RNG_TEMPLATE_SHARE) {
                tmpType = plcTmp;

            //個人
            } else if (form.getRngTemplateMode() == RngConst.RNG_TEMPLATE_PRIVATE) {
                tmpType = prvTmp;
            }
            rngBiz.outPutLog(
                    map, opCode,
                    GSConstLog.LEVEL_INFO,
                    tmpType,
                    reqMdl);

            con.commit();
            commit = true;
        } catch (Exception e) {
            log__.error("稟議テンプレート情報の登録に失敗", e);
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        return forward;
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
        Rng090knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        String tempDir = _getRingiTemplateDir(req);
        String fileId = form.getRng090knTmpFileId();

        //オブジェクトファイルを取得
        ObjectFile objFile = new ObjectFile(tempDir, fileId.concat(GSConstCommon.ENDSTR_OBJFILE));
        Object fObj = objFile.load();
        Cmn110FileModel fMdl = (Cmn110FileModel) fObj;

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String download = gsMsg.getMessage("cmn.download");

        //ログ出力処理
        RngBiz rngBiz = new RngBiz(con);
        rngBiz.outPutLog(
                map,
                download, GSConstLog.LEVEL_INFO, fMdl.getFileName(),
                reqMdl, fileId);

        //時間のかかる処理の前にコネクションを破棄
        JDBCUtil.closeConnectionAndNull(con);

        rngBiz.downloadTempFile(req, res, tempDir, fileId);

        return null;
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @return ActionForward
     */
    private ActionForward __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Rng090knForm form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("rng060");
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage();
        String rng = gsMsg.getMessage(req, "rng.92");

        //メッセージセット
        String msgState = null;
        if (form.getRngTplCmdMode() == RngConst.RNG_CMDMODE_ADD) {
            msgState = "touroku.kanryo.object";
        } else if (form.getRngTplCmdMode() == RngConst.RNG_CMDMODE_EDIT) {
            msgState = "hensyu.kanryo.object";
        }
        cmn999Form.setMessage(msgRes.getMessage(msgState, rng));

        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("rngProcMode", form.getRngProcMode());
        cmn999Form.addHiddenParam("rng010orderKey", form.getRng010orderKey());
        cmn999Form.addHiddenParam("rng010sortKey", form.getRng010sortKey());
        cmn999Form.addHiddenParam("rng010pageTop", form.getRng010pageTop());
        cmn999Form.addHiddenParam("rng010pageBottom", form.getRng010pageBottom());

        cmn999Form.addHiddenParam("rngTemplateMode", form.getRngTemplateMode());
        cmn999Form.addHiddenParam("rngTplCmdMode", form.getRngTplCmdMode());
        cmn999Form.addHiddenParam("rng060SelectCat", form.getRng090CatSid());
        cmn999Form.addHiddenParam("rng060SelectCatUsr", form.getRng090CatSid());

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}
