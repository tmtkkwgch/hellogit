package jp.groupsession.v2.prj.prj021;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.prj.AbstractProjectAction;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.PrjCommonBiz;
import jp.groupsession.v2.prj.prj020.Prj020Action;
import jp.groupsession.v2.prj.prj140.Prj140Action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] プロジェクト管理 状態設定画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Prj021Action extends AbstractProjectAction {

    /** CMD:戻るボタンクリック */
    public static final String CMD_BACK_CLICK = Prj020Action.CMD_BACK_REDRAW;
    /** CMD:設定ボタンクリック1 */
    public static final String CMD_EDIT_CLICK = Prj020Action.CMD_EDIT_CLICK;
    /** CMD:設定ボタンクリック2 */
    public static final String CMD_EDIT_CLICK_TMP = Prj140Action.CMD_EDIT_CLICK;

    /** CMD:カテゴリ追加ボタンクリック */
    public static final String CMD_ADD_VALUE_CLICK = "addValueClick";
    /** CMD:カテゴリ削除ボタンクリック */
    public static final String CMD_REMOVE_VALUE_CLICK = "removeValueClick";

    /** CMD:順序変更処理区分 順序をあげるクリック */
    public static final String CMD_SORT_UP_CLICK = "sortUpClick";
    /** CMD:順序変更処理区分 順序を下げるクリック */
    public static final String CMD_SORT_DOWN_CLICK = "sortDownClick";

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Prj021Action.class);

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

        log__.debug("Prj021Action start");
        ActionForward forward = null;

        Prj021Form thisForm = (Prj021Form) form;

        //コマンドパラメータ取得
        String cmd = PrjCommonBiz.getCmdProperty(req);

        if (CMD_BACK_CLICK.equals(cmd)) {
            log__.debug("戻るボタンクリック");
            forward = __doBack(map, thisForm, req, res, con);

        } else if (CMD_EDIT_CLICK.equals(cmd) || CMD_EDIT_CLICK_TMP.equals(cmd)) {
            log__.debug("設定ボタンクリック");
            forward = __doEdit(map, thisForm, req, res, con);

        } else if (CMD_ADD_VALUE_CLICK.equals(cmd)) {
            log__.debug("↓追加ボタンクリック");
            forward = __doAddValue(map, thisForm, req, res, con);

        } else if (CMD_REMOVE_VALUE_CLICK.equals(cmd)) {
            log__.debug("削除ボタンクリック");
            forward = __doRemoveValue(map, thisForm, req, res, con);

//        } else if (CMD_SORT_UP_CLICK.equals(cmd)) {
//            log__.debug("ソートUPクリック");
//            forward = __doSortValue(map, thisForm, req, res, con, CMD_SORT_UP_CLICK);
//
//        } else if (CMD_SORT_DOWN_CLICK.equals(cmd)) {
//            log__.debug("ソートDOWNクリック");
//            forward = __doSortValue(map, thisForm, req, res, con, CMD_SORT_DOWN_CLICK);

        } else {
            log__.debug("初期表示");
            forward = __doInit(map, thisForm, req, res, con);
        }

        log__.debug("Prj021Action end");
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
        Prj021Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        int tmpMode = form.getPrjTmpMode();

        //テンプレート作成モードからの呼び出し
        if (tmpMode > 0) {

            //オブジェクトファイルを指定したパスで別名保存する
            PrjCommonBiz.saveNewTmpFile(getTempPath(req),
                            getRequestModel(req),
                            GSConstProject.SAVE_FILENAME,
                            GSConstProject.SAVE_FILENAME_OLD);

        //通常登録画面からの呼び出し
        } else {

            //オブジェクトファイルを指定したパスで別名保存する
            PrjCommonBiz.saveNewFile(getTempPath(req),
                    getRequestModel(req),
                            GSConstProject.SAVE_FILENAME,
                            GSConstProject.SAVE_FILENAME_OLD);
        }

        //初期表示情報を画面にセットする
        Prj021Biz biz = new Prj021Biz(getRequestModel(req));

        Prj021ParamModel paramMdl = new Prj021ParamModel();
        paramMdl.setParam(form);
        biz.setInitData(paramMdl, getTempPath(req));
        paramMdl.setFormData(form);

        return __doDspSet(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 画面に常に表示する情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return ActionForward
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doDspSet(
        ActionMapping map,
        Prj021Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        //初期表示情報を画面にセットする
        Prj021Biz biz = new Prj021Biz(getRequestModel(req));
        Prj021ParamModel paramMdl = new Prj021ParamModel();
        paramMdl.setParam(form);
        biz.getDspData(paramMdl, getTempPath(req));
        paramMdl.setFormData(form);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 設定ボタンクリック時の処理を行う
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
    private ActionForward __doEdit(
        ActionMapping map,
        Prj021Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        ActionForward forward = null;
        ActionErrors errors = form.validate021edit(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doDspSet(map, form, req, res, con);
        }

        //0%、100%の状態名称を入力値に設定する
        Prj021Biz biz = new Prj021Biz(getRequestModel(req));

        Prj021ParamModel paramMdl = new Prj021ParamModel();
        paramMdl.setParam(form);
        biz.editStatusName(paramMdl, getTempPath(req));
        paramMdl.setFormData(form);

        //別名保存しておいたオブジェクトファイルを削除する
        PrjCommonBiz.deleteFile(
                getTempPath(req), getRequestModel(req), GSConstProject.SAVE_FILENAME_OLD);

        int tmpMode = form.getPrjTmpMode();

        //テンプレート作成モードからの呼び出し
        if (tmpMode > 0) {
            forward = map.findForward(CMD_EDIT_CLICK_TMP);
        //通常登録画面からの呼び出し
        } else {
            forward = map.findForward(CMD_EDIT_CLICK);
        }

        return forward;
    }

    /**
     * <br>[機  能] 戻るボタンクリック時の処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws IOToolsException IOエラー
     */
    private ActionForward __doBack(
        ActionMapping map,
        Prj021Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws IOToolsException {

        ActionForward forward = null;

        int tmpMode = form.getPrjTmpMode();

        //テンプレート作成モードからの呼び出し
        if (tmpMode > 0) {

            //オブジェクトファイルを指定したパスで別名保存する
            PrjCommonBiz.saveNewTmpFile(getTempPath(req),
                            getRequestModel(req),
                            GSConstProject.SAVE_FILENAME_OLD,
                            GSConstProject.SAVE_FILENAME);

            forward = map.findForward(CMD_EDIT_CLICK_TMP);

        //通常登録画面からの呼び出し
        } else {

            //オブジェクトファイルを指定したパスで別名保存する
            PrjCommonBiz.saveNewFile(getTempPath(req),
                            getRequestModel(req),
                            GSConstProject.SAVE_FILENAME_OLD,
                            GSConstProject.SAVE_FILENAME);

            forward = map.findForward(CMD_EDIT_CLICK);
        }

        //別名保存しておいたオブジェクトファイルを削除する
        PrjCommonBiz.deleteFile(
                getTempPath(req), getRequestModel(req), GSConstProject.SAVE_FILENAME_OLD);

        return forward;
    }

    /**
     * <br>[機  能] 状態をリストに追加する。
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
    private ActionForward __doAddValue(
        ActionMapping map,
        Prj021Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        ActionErrors errors = form.validate021(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doDspSet(map, form, req, res, con);
        }

        //入力した項目を状態に追加
        Prj021Biz biz = new Prj021Biz(getRequestModel(req));

        Prj021ParamModel paramMdl = new Prj021ParamModel();
        paramMdl.setParam(form);
        biz.addStatus(paramMdl, getTempPath(req));
        paramMdl.setFormData(form);

        //入力値をクリア
        form.setPrj021name("");
        form.setPrj021rate("");

        return __doDspSet(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 項目をリストから削除する。
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
    private ActionForward __doRemoveValue(
        ActionMapping map,
        Prj021Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException {

        //選択した項目を状態から削除
        Prj021Biz biz = new Prj021Biz(getRequestModel(req));

        Prj021ParamModel paramMdl = new Prj021ParamModel();
        paramMdl.setParam(form);
        biz.removeStatus(paramMdl, getTempPath(req));
        paramMdl.setFormData(form);

        return __doDspSet(map, form, req, res, con);
    }

//    /**
//     * <br>[機  能] 項目のソートを行う。
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param map アクションマッピング
//     * @param form アクションフォーム
//     * @param req リクエスト
//     * @param res レスポンス
//     * @param con コネクション
//     * @param sortKbn ソート区分
//     * @return ActionForward
//     * @throws SQLException SQL実行例外
//     * @throws IOToolsException IOエラー
//     */
//    private ActionForward __doSortValue(
//        ActionMapping map,
//        Prj021Form form,
//        HttpServletRequest req,
//        HttpServletResponse res,
//        Connection con,
//        String sortKbn) throws SQLException, IOToolsException {
//
//        //選択した項目を一つ上と入れ替える
//        Prj021Biz biz = new Prj021Biz();
//        biz.chengeRate(form, getTempPath(req), req, sortKbn);
//
//        //選択値をクリア
//        form.setPrj021state("");
//
//        return __doDspSet(map, form, req, res, con);
//    }
}