package jp.groupsession.v2.tcd.tcd030;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.tcd.AbstractTimecardAction;
import jp.groupsession.v2.tcd.tcd010.Tcd010Biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] タイムカード 管理者設定メニュー画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd030Action extends AbstractTimecardAction {
    /** ロギング */
    private static Log log__ = LogFactory.getLog(Tcd030Action.class);

    /**
     * <br>[機  能] アクセス権限が有るか判定します
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param con コネクション
     * @return boolean true:権限有り false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isAuth(HttpServletRequest req, Connection con) throws SQLException {
        //セッション情報を取得
        con.setAutoCommit(true);
        BaseUserModel usModel = getSessionUserModel(req);
        Tcd010Biz biz = new Tcd010Biz();
        int usKbn = biz.getUserKbn(usModel, con);
        con.setAutoCommit(false);

        //権限チェック(管理者orグループ管理者)
        return usKbn != GSConstTimecard.USER_KBN_NORMAL;
    }

    /**
     *<br>[機  能] 印刷プレビューへの遷移
     *<br>[解  説]
     *<br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con DBコネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    public ActionForward executeAction(ActionMapping map, ActionForm form,
        HttpServletRequest req, HttpServletResponse res, Connection con)
        throws Exception {

        //権限チェック
        if (!isAuth(req, con)) {
            return getNotAdminSeniPage(map, req);
        }

        log__.debug("START:Tcd030Action");
        ActionForward forward = null;
        Tcd030Form uform = (Tcd030Form) form;
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        cmd = cmd.trim();

        if (cmd.equals("mng")) {
            //タイムカードマネージャー
            forward = __doManager(map, uform, req, res, con);
        } else if (cmd.equals("base_conf")) {
            //基本設定
            forward = __doBaseConf(map, uform, req, res, con);
        } else if (cmd.equals("timezone")) {
            //時間帯設定
            forward = __doTimeZone(map, uform, req, res, con);
        } else if (cmd.equals("editAuth")) {
            //編集権限設定
            forward = __doEditAuth(map, uform, req, res, con);
        } else if (cmd.equals("back")) {
            //戻る
            forward = __doBack(map, uform);
        } else {
            //初期表示処理
            forward = __doInit(map, uform, req, res, con);
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
     * @throws SQLException SQL実行時例外
     */
    private ActionForward __doInit(ActionMapping map,
                                    Tcd030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException {

        log__.debug("初期表示");
        con.setAutoCommit(true);
        ActionForward forward = null;

        //ユーザ情報
        BaseUserModel umodel = getSessionUserModel(req);
        Tcd010Biz biz = new Tcd010Biz();
        int usKbn = biz.getUserKbn(umodel, con);
        form.setMenuLevel(String.valueOf(usKbn));
        forward = map.getInputForward();
        con.setAutoCommit(false);
        return forward;
    }

    /**
     * <br>[機  能] タイムカードマネージャー画面へ
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
     */
    private ActionForward __doManager(ActionMapping map,
                                    Tcd030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException {

        log__.debug("初期表示");
        return map.findForward("mng");
    }

    /**
     * <br>[機  能] 基本設定画面へ
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
     */
    private ActionForward __doBaseConf(ActionMapping map,
                                    Tcd030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException {

        ActionForward forward = null;

        forward = map.findForward("base_conf");
        return forward;
    }

    /**
     * <br>[機  能] 時間帯設定画面へ
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
     */
    private ActionForward __doTimeZone(ActionMapping map,
                                    Tcd030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException {

        ActionForward forward = null;

        forward = map.findForward("timezone");
        return forward;
    }

    /**
     * <br>[機  能] 編集権限設定画面へ
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
     */
    private ActionForward __doEditAuth(ActionMapping map,
                                    Tcd030Form form,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    Connection con)
        throws SQLException {

        ActionForward forward = null;

        forward = map.findForward("editAuth");
        return forward;
    }

    /**
     * <br>[機  能] 戻る処理
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @return ActionForward フォワード
     */
    private ActionForward __doBack(ActionMapping map, Tcd030Form form) {

        if (form.getBackScreen() == GSConstMain.BACK_MAIN_ADM_SETTING) {
            return map.findForward("mainAdmSetting");
        }
        return map.findForward("back");
    }
}
