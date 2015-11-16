package jp.groupsession.v2.man.man240kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.man240.Man240Action;
import jp.groupsession.v2.struts.AdminAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] メイン 管理者設定 オペレーションログ設定確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man240knAction extends AdminAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man240knAction.class);

    /** CMD:設定ボタンクリック */
    public static final String CMD_SETTEI_CLICK = "setteiClick";
    /** CMD:戻るボタンクリック */
    public static final String CMD_BACK_CLICK = Man240Action.CMD_BACK_REDRAW;
    /** CMD:変更完了後遷移先*/
    public static final String CMD_BACK_MENU = "ktool";

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

        log__.debug("Man140knAction start");
        ActionForward forward = null;

        Man240knForm thisForm = (Man240knForm) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (CMD_SETTEI_CLICK.equals(cmd)) {
            //設定ボタンクリック
            forward = __doPushRegist(map, thisForm, req, res, con);

        } else if (CMD_BACK_CLICK.equals(cmd)) {
            //戻るボタンクリック
            forward = map.findForward(CMD_BACK_CLICK);

        } else {
            //初期表示
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("Man140knAction end");
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
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doInit(
        ActionMapping map,
        Man240knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {


        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行例外
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doPushRegist(
        ActionMapping map,
        Man240knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        RequestModel reqMdl = getRequestModel(req);

        //オペレーションログ設定の更新を行う
        Man240knParamModel paramMdl = new Man240knParamModel();
        paramMdl.setParam(form);
        Man240knBiz biz = new Man240knBiz(con);
        biz.update(paramMdl, getSessionUserSid(req), reqMdl);
        paramMdl.setFormData(form);

        //ログ出力
        CommonBiz cmnBiz = new CommonBiz();
        GsMessage gsMsg = new GsMessage(reqMdl);
        cmnBiz.outPutCommonLog(map, reqMdl, gsMsg, con,
                getInterMessage(reqMdl, "cmn.change"), GSConstLog.LEVEL_INFO, "");

        //設定完了画面を表示
        return __setKanryoDsp(map, req);
    }

    /**
     * [機  能] 設定完了画面のパラメータセット<br>
     * [解  説] <br>
     * [備  考] <br>
     * @param map マッピング
     * @param req リクエスト
     * @return ActionForward
     */
    private ActionForward __setKanryoDsp(ActionMapping map, HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        //表示件数設定完了画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        urlForward = map.findForward(CMD_BACK_MENU);
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(
                msgRes.getMessage("settei.kanryo.object",
                        getInterMessage(req, "cmn.operations.log")));

        req.setAttribute("cmn999Form", cmn999Form);
        return map.findForward("gf_msg");
    }

}
