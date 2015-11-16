package jp.groupsession.v2.bmk.bmk140;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.bmk.AbstractBookmarkAction;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.biz.BookmarkBiz;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] メイン画面表示設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk140Action extends AbstractBookmarkAction {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk140Action.class);

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
        ActionForward forward = null;

        Bmk140Form bmkForm = (Bmk140Form) form;
        //コマンドパラメータ取得
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        if (cmd.equals("bmk140commit")) {
            //登録
            forward = __doCommit(map, bmkForm, req, res, con);
        } else if (cmd.equals("bmk140back")) {
            //戻るボタンクリック
            log__.debug("遷移元：" + bmkForm.getReturnPage());
            forward = map.findForward(bmkForm.getReturnPage());
        } else if (cmd.equals("up")) {
            log__.debug("上矢印");
            forward = __doUp(map, bmkForm, req, res, con);

        } else if (cmd.equals("down")) {
            log__.debug("下矢印");
            forward = __doDown(map, bmkForm, req, res, con);

        } else if (cmd.equals("delete")) {
            log__.debug("左矢印");
            forward = __doLeft(map, bmkForm, req, res, con);

        } else if (cmd.equals("add")) {
            log__.debug("右矢印");
            forward = __doRight(map, bmkForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, bmkForm, req, res, con);
        }
        return forward;
    }

    /**
     * <br>登録処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doCommit(ActionMapping map, Bmk140Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {
        log__.debug("登録処理");

        //入力チェック
//        ActionErrors errors = form.validateCheck(con);
//        if (!errors.isEmpty()) {
//            addErrors(req, errors);
//            return __doInit(map, form, req, res, con);
//        }

        //セッションユーザSIDを取得する。
        int sessionUserSid = this.getSessionUserModel(req).getUsrsid();

        //DB更新
        Bmk140Biz biz = new Bmk140Biz(getRequestModel(req));

        Bmk140ParamModel paramMdl = new Bmk140ParamModel();
        paramMdl.setParam(form);
        int addEditFlg =  biz.setBmkUconfSetting(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);


        //ログ出力処理
        BookmarkBiz bmkBiz = new BookmarkBiz(con, getRequestModel(req));
        String opCode = "";
        GsMessage gsMsg = new GsMessage();
        String msg = "";

        //登録
        if (addEditFlg == GSConstBookmark.BMK_MODE_TOUROKU) {
            msg = gsMsg.getMessage(req, "cmn.entry");
            opCode = msg;
        //編集
        } else {
            msg = gsMsg.getMessage(req, "cmn.change");
            opCode = msg;
        }

        bmkBiz.outPutLog(opCode, GSConstLog.LEVEL_INFO, "", map.getType());

        //共通メッセージ画面(OK)を表示
        __setCompPageParam(map, req, form);
        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     */
    private void __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Bmk140Form form) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward(form.getReturnPage());
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage();
        String msg = gsMsg.getMessage(req, "cmn.main.view");

        //メッセージセット
        cmn999Form.setMessage(msgRes.getMessage("touroku.kanryo.object", msg));

        //画面パラメータをセット
        cmn999Form.addHiddenParam("backScreen", form.getBackScreen());
        cmn999Form.addHiddenParam("returnPage", form.getReturnPage());
        form.setHiddenParam(cmn999Form);

        req.setAttribute("cmn999Form", cmn999Form);
    }

    /**
     * <br>初期表処理
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return アクションフォーワード
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map, Bmk140Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException {

        log__.debug("初期表示");

        //セッションユーザSIDを取得する。
        int sessionUserSid = this.getSessionUserModel(req).getUsrsid();

        con.setAutoCommit(true);
        Bmk140Biz biz = new Bmk140Biz(getRequestModel(req));

        Bmk140ParamModel paramMdl = new Bmk140ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, sessionUserSid, con);
        paramMdl.setFormData(form);

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
        Bmk140Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException,
                        IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //選択されたメニューを表示メニューから削除する
        Bmk140Biz biz = new Bmk140Biz(getRequestModel(req));

        Bmk140ParamModel paramMdl = new Bmk140ParamModel();
        paramMdl.setParam(form);
        biz.deleteViewBmk(paramMdl, con, getSessionUserModel(req).getUsrsid());
        paramMdl.setFormData(form);

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
        Bmk140Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException,
                        IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //選択されたメニューを表示メニューに追加する
        Bmk140Biz biz = new Bmk140Biz(getRequestModel(req));

        Bmk140ParamModel paramMdl = new Bmk140ParamModel();
        paramMdl.setParam(form);
        biz.addViewBmk(paramMdl, con, getSessionUserModel(req).getUsrsid());
        paramMdl.setFormData(form);

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
        Bmk140Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException,
                        IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //選択されたメニューの表示順を上げる
        Bmk140Biz biz = new Bmk140Biz(getRequestModel(req));

        Bmk140ParamModel paramMdl = new Bmk140ParamModel();
        paramMdl.setParam(form);
        biz.upOrderViewBmk(paramMdl, con, getSessionUserModel(req).getUsrsid());
        paramMdl.setFormData(form);

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
        Bmk140Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException,
                        IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        //選択されたメニューの表示順を下げる
        Bmk140Biz biz = new Bmk140Biz(getRequestModel(req));

        Bmk140ParamModel paramMdl = new Bmk140ParamModel();
        paramMdl.setParam(form);
        biz.downOrderViewBmk(paramMdl, con, getSessionUserModel(req).getUsrsid());
        paramMdl.setFormData(form);

        return __doInit(map, form, req, res, con);
    }
}
