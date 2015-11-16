package jp.groupsession.v2.bbs.bbs070;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.bbs.AbstractBulletinAction;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 掲示板 スレッド登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs070Action extends AbstractBulletinAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs070Action.class);

    /**
     * <br>[機  能] アクション実行
     * <br>[解  説] コントローラの役割を担います
     * <br>[備  考]
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
        log__.debug("START");

        ActionForward forward = null;
        Bbs070Form bbsForm = (Bbs070Form) form;

        //フォーラム閲覧権限チェック
        con.setAutoCommit(true);
        boolean forumAuth = _checkForumAuth(map, req, con, bbsForm.getBbs010forumSid());
        con.setAutoCommit(false);
        if (!forumAuth) {
            return map.findForward("gf_msg");
        }

        Bbs070Biz biz = new Bbs070Biz();

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);

        if (cmd.equals("moveThreadConfirm")) {
            //ＯＫボタンクリック
            forward = __doConfirm(map, bbsForm, req, res, con);
        } else if (cmd.equals("backList")) {
            //戻るボタンクリック
            biz.doDeleteFile(_getBulletinTempDir(req));
            if (bbsForm.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_ADD) {
                forward = map.findForward("moveThreadList");
            } else if (bbsForm.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
                if (bbsForm.getBbs070BackFlg() == 1) {
                    forward = map.findForward("rsvThreadList");
                } else {
                    forward = map.findForward("moveWriteList");
                }
            }
        } else if (cmd.equals("delTempFile")) {
            //添付ファイル削除
            forward = __doDelete(map, bbsForm, req, res, con);
        } else {
            //初期表示
            if (!cmd.equals("backToInput")) {
                biz.doDeleteFile(_getBulletinTempDir(req));
            }
            forward = __doInit(map, bbsForm, req, res, con);
        }


        log__.debug("END");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws SQLException 実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    private ActionForward __doInit(ActionMapping map,
        Bbs070Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con)
        throws IOException, IOToolsException, SQLException, TempFileException {

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");

        //投稿一覧からの遷移、かつ処理モード = 編集の場合はスレッド情報を取得する
        if (cmd.equals("editThread")
                && form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
            //投稿の存在チェックを行う
            BbsBiz bbsBiz = new BbsBiz();
            if (!bbsBiz.isCheckExistWrite(con, form.getBbs080writeSid())) {
                //フォーラム内スレッド件数取得
                return __setAlreadyDelWrite(map, req, form, con);
            }
        }

        con.setAutoCommit(true);
        Bbs070ParamModel paramMdl = new Bbs070ParamModel();
        paramMdl.setParam(form);
        Bbs070Biz biz = new Bbs070Biz();
        biz.setInitData(cmd, getRequestModel(req), paramMdl, con,
                        getAppRootPath(), _getBulletinTempDir(req));
        paramMdl.setFormData(form);
        con.setAutoCommit(false);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] ＯＫボタンクリック時処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doConfirm(ActionMapping map,
        Bbs070Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con)
        throws Exception {

        ActionErrors errors = new ActionErrors();
        errors = form.validateCheck(con, getRequestModel(req), _getBulletinTempDir(req));
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        saveToken(req);
        return map.findForward("moveThreadConfirm");
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
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws SQLException 実行例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doDelete(
        ActionMapping map,
        Bbs070Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws IOException, IOToolsException, SQLException, TempFileException {

        //テンポラリディレクトリパスを取得
        String tempDir = _getBulletinTempDir(req);
        log__.debug("テンポラリディレクトリ = " + tempDir);

        //選択された添付ファイルを削除する
        CommonBiz biz = new CommonBiz();
        biz.deleteFile(form.getBbs070files(), tempDir);

        return __doInit(map, form, req, res, con);
    }

    /**
     * <br>[機  能] スレッド or 投稿削除時権限エラーメッセージ画面遷移時の設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException SQL例外発生
     */
    private ActionForward __setAlreadyDelWrite(
        ActionMapping map,
        HttpServletRequest req,
        Bbs070Form form,
        Connection con
            ) throws SQLException {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        urlForward = map.findForward("backBBSList");
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage();
        String textDelWrite = gsMsg.getMessage(req, "bbs.16");
        String textDel = gsMsg.getMessage(req, "cmn.edit");

        //メッセージセット
        String msgState = "error.none.edit.data";
        cmn999Form.setMessage(msgRes.getMessage(msgState,
                textDelWrite,
                textDel));

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}

