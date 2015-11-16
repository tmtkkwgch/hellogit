package jp.groupsession.v2.cmn.cmn140;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.AbstractGsAction;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] メイン 個人設定 メニュー項目の設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn140Action extends AbstractGsAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn140Action.class);

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

        log__.debug("START_Cmn140");
        ActionForward forward = null;

        Cmn140Form thisForm = (Cmn140Form) form;

        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter(GSConst.P_CMD), "");
        cmd = cmd.trim();

        if (cmd.equals("backPsMenu")) {
            log__.debug("戻る");
            forward = map.findForward("menu");

        } else if (cmd.equals("up")) {
            log__.debug("上矢印");
            forward = __doUp(map, thisForm, req, res, con);

        } else if (cmd.equals("down")) {
            log__.debug("下矢印");
            forward = __doDown(map, thisForm, req, res, con);

        } else if (cmd.equals("delete")) {
            log__.debug("左矢印");
            forward = __doLeft(map, thisForm, req, res, con);

        } else if (cmd.equals("add")) {
            log__.debug("右矢印");
            forward = __doRight(map, thisForm, req, res, con);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("END_Cmn140");
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
     * @throws SQLException SQL実行例外
     * @throws IllegalAccessException パラメータの設定に失敗
     * @throws InvocationTargetException パラメータの設定に失敗
     * @throws NoSuchMethodException パラメータの設定に失敗
     * @return ActionForward
     */
    private ActionForward __doInit(
        ActionMapping map,
        Cmn140Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException,
                        IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        Cmn140Biz biz = new Cmn140Biz();

        con.setAutoCommit(true);
        PluginConfig pconfig = getPluginConfig(req);
        Cmn140ParamModel paramModel = new Cmn140ParamModel();
        paramModel.setParam(form);
        biz.setInitData(paramModel, con, pconfig, getRequestModel(req));
        paramModel.setFormData(form);
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 左ボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IllegalAccessException パラメータの設定に失敗
     * @throws InvocationTargetException パラメータの設定に失敗
     * @throws NoSuchMethodException パラメータの設定に失敗
     * @return ActionForward
     */
    private ActionForward __doLeft(
        ActionMapping map,
        Cmn140Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException,
                        IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        GsMessage gsMsg = new GsMessage(req);
        String textChange = gsMsg.getMessage(req, "cmn.change");

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {

            addErrors(req, errors);

        } else {
            //選択されたメニューを表示メニューから削除する
            Cmn140Biz biz = new Cmn140Biz();
            Cmn140ParamModel paramModel = new Cmn140ParamModel();
            paramModel.setParam(form);
            biz.deleteViewMenu(paramModel, con, getSessionUserModel(req).getUsrsid());
            paramModel.setFormData(form);
            //ログ出力
            CommonBiz cmnBiz = new CommonBiz();
            cmnBiz.outPutCommonLog(map, getRequestModel(req), gsMsg, con,
                    textChange, GSConstLog.LEVEL_INFO, "");
        }

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 右ボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IllegalAccessException パラメータの設定に失敗
     * @throws InvocationTargetException パラメータの設定に失敗
     * @throws NoSuchMethodException パラメータの設定に失敗
     * @return ActionForward
     */
    private ActionForward __doRight(
        ActionMapping map,
        Cmn140Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException,
                        IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        GsMessage gsMsg = new GsMessage(req);
        String textEdit = gsMsg.getMessage(req, "cmn.change");

        //入力チェックを行う
        ActionErrors errors = form.validateCheckRight(req);
        if (!errors.isEmpty()) {

            addErrors(req, errors);

        } else {
            //選択されたメニューを表示メニューに追加する
            Cmn140Biz biz = new Cmn140Biz();
            Cmn140ParamModel paramModel = new Cmn140ParamModel();
            paramModel.setParam(form);
            biz.addViewMenu(paramModel, con, getSessionUserModel(req).getUsrsid());
            paramModel.setFormData(form);
            //ログ出力
            CommonBiz cmnBiz = new CommonBiz();
            cmnBiz.outPutCommonLog(map, getRequestModel(req), gsMsg, con,
                    textEdit, GSConstLog.LEVEL_INFO, "");
        }

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 上ボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IllegalAccessException パラメータの設定に失敗
     * @throws InvocationTargetException パラメータの設定に失敗
     * @throws NoSuchMethodException パラメータの設定に失敗
     * @return ActionForward
     */
    private ActionForward __doUp(
        ActionMapping map,
        Cmn140Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException,
                        IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String textEdit = gsMsg.getMessage("cmn.change");

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {

            addErrors(req, errors);

        } else {
            //選択されたメニューの表示順を上げる
            Cmn140Biz biz = new Cmn140Biz();
            Cmn140ParamModel paramModel = new Cmn140ParamModel();
            paramModel.setParam(form);
            biz.upOrderViewMenu(paramModel, con, getSessionUserSid(req));
            paramModel.setFormData(form);
            //ログ出力
            CommonBiz cmnBiz = new CommonBiz();
            cmnBiz.outPutCommonLog(map, reqMdl, gsMsg, con,
                    textEdit, GSConstLog.LEVEL_INFO, "");
        }

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 下ボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IllegalAccessException パラメータの設定に失敗
     * @throws InvocationTargetException パラメータの設定に失敗
     * @throws NoSuchMethodException パラメータの設定に失敗
     * @return ActionForward
     */
    private ActionForward __doDown(
        ActionMapping map,
        Cmn140Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException,
                        IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        GsMessage gsMsg = new GsMessage(req);
        String textEdit = gsMsg.getMessage("cmn.change");

        //入力チェックを行う
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {

            addErrors(req, errors);

        } else {
            //選択されたメニューの表示順を下げる
            Cmn140Biz biz = new Cmn140Biz();
            Cmn140ParamModel paramModel = new Cmn140ParamModel();
            paramModel.setParam(form);
            biz.downOrderViewMenu(paramModel, con, getSessionUserModel(req).getUsrsid());
            paramModel.setFormData(form);
            //ログ出力
            CommonBiz cmnBiz = new CommonBiz();
            cmnBiz.outPutCommonLog(map, getRequestModel(req), gsMsg, con,
                    textEdit, GSConstLog.LEVEL_INFO, "");
        }

        return __doInit(map, form, req, res, con);
    }

}
