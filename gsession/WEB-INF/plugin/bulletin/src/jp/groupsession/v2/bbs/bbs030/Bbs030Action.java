package jp.groupsession.v2.bbs.bbs030;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.bbs.AbstractBulletinAction;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.exception.TempFileException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 掲示板 フォーラム登録画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs030Action extends AbstractBulletinAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs030Action.class);
    /** アイコン画像名 */
    public String imageFileName__ = "";
    /** アイコン画像保存名 */
    public String imageFileSaveName__ = "";

    /**
     * <p>管理者以外のアクセスを許可するのか判定を行う。
     * <p>サブクラスでこのメソッドをオーバーライドして使用する
     * @param req リクエスト
     * @param form アクションフォーム
     * @return true:許可する,false:許可しない
     */
    public boolean canNotAdminAccess(HttpServletRequest req, ActionForm form) {
        return false;
    }

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
        Bbs030Form bbsForm = (Bbs030Form) form;

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);

        if (cmd.equals("forumConfirm")) {
            //ＯＫボタンクリック
            forward = __doConfirm(map, bbsForm, req, res, con);
        } else if (cmd.equals("backForumList")) {
            //戻るボタンクリック
            forward = map.findForward("backForumList");
        } else if (cmd.equals("changeGrp")) {
            //グループ変更クリック
            forward = __doDsp(map, bbsForm, req, res, con);
        } else if (cmd.equals("addMember")) {
            //左矢印(ユーザ追加)ボタンクリック
            forward = __doAddUser(map, bbsForm, req, res, con);
        } else if (cmd.equals("removeMember")) {
            //左矢印(ユーザ削除)ボタンクリック
            forward = __doDelUser(map, bbsForm, req, res, con);
        } else if (cmd.equals("addMemberRead")) {
            //左矢印(閲覧ユーザ追加)ボタンクリック
            forward = __doAddUserRead(map, bbsForm, req, res, con);
        } else if (cmd.equals("removeMemberRead")) {
            //左矢印(閲覧ユーザ削除)ボタンクリック
            forward = __doDelUserRead(map, bbsForm, req, res, con);
        } else if (cmd.equals("addMemberAdm")) {
            //左矢印(管理者ユーザ追加)ボタンクリック
            forward = __doAddUserAdm(map, bbsForm, req, res, con);
        } else if (cmd.equals("removeMemberAdm")) {
            //左矢印(管理者ユーザ削除)ボタンクリック
            forward = __doDelUserAdm(map, bbsForm, req, res, con);
        } else if (cmd.equals("getImageFile")) {
            //画像ダウンロード"
            forward = __doGetImageFile(map, bbsForm, req, res, con);
        } else if (cmd.equals("bbs030tempdeleteMark")) {
            //添付削除
            forward = __doTempDeleteMark(map, bbsForm, req, res, con);
        } else {
            //初期表示
            forward = __doInit(map, bbsForm, req, res, con);
        }

        log__.debug("END");
        return forward;
    }

    /**
     * <br>[機  能] 初期表示処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException 実行例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws IOException バイナリファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     *
     */
    private ActionForward __doInit(
            ActionMapping map,
            Bbs030Form form,
            HttpServletRequest req,
            HttpServletResponse res,
            Connection con)
                    throws SQLException, IOToolsException, IOException, TempFileException {
        imageFileName__ = "";
        imageFileSaveName__ = "";

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 表示処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward フォワード
     * @throws SQLException 実行例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws IOException バイナリファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     *
     */
    private ActionForward __doDsp(ActionMapping map,
        Bbs030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws SQLException, IOToolsException, IOException, TempFileException {

        con.setAutoCommit(true);
        Bbs030ParamModel paramMdl = new Bbs030ParamModel();
        paramMdl.setParam(form);
        Bbs030Biz biz = new Bbs030Biz();

        //アプリケーションのルートパス
        String appRootPath = getAppRootPath();

        //テンポラリディレクトリパス
        String tempPath = _getBulletinTempDir(req)
                         + GSConstBulletin.TEMP_ICN_BBS + File.separator;

        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        biz.setInitData(cmd, getRequestModel(req), paramMdl, con,
                        tempPath, appRootPath, getSessionUserSid(req));
        paramMdl.setFormData(form);

        //画像ファイルを設定
        if (!NullDefault.getString(form.getBbs030ImageName(), "").equals("")
                && !NullDefault.getString(form.getBbs030ImageSaveName(), "").equals("")) {
            imageFileName__ = form.getBbs030ImageName();
            imageFileSaveName__ = form.getBbs030ImageSaveName();
        }

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
        Bbs030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {

        ActionErrors errors = new ActionErrors();
        errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doDsp(map, form, req, res, con);
        }

        saveToken(req);
        return map.findForward("moveConfirm");
    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
     * <br>[解  説] 編集メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws IOException バイナリファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doAddUser(
        ActionMapping map,
        Bbs030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException,
                                TempFileException, IOException {

        BbsBiz biz = new BbsBiz();
        form.setBbs030memberSid(
                biz.getAddMember(form.getBbs030SelectRightUser(), form.getBbs030memberSid()));

        return  __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
     * <br>[解  説] 編集メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws IOException バイナリファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doDelUser(
        ActionMapping map,
        Bbs030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException,
                                TempFileException, IOException {

        BbsBiz biz = new BbsBiz();
        form.setBbs030memberSid(
                biz.getDeleteMember(form.getBbs030SelectLeftUser(), form.getBbs030memberSid()));

        //管理者メンバーから削除する
        form.setBbs030memberSidAdm(
                biz.getDeleteMember(form.getBbs030SelectLeftUser(), form.getBbs030memberSidAdm()));

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
     * <br>[解  説] 閲覧メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws IOException バイナリファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doAddUserRead(
        ActionMapping map,
        Bbs030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException,
                                TempFileException, IOException {

        BbsBiz biz = new BbsBiz();
        form.setBbs030memberSidRead(
                biz.getAddMember(form.getBbs030SelectRightUser(), form.getBbs030memberSidRead()));

        return  __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
     * <br>[解  説] 閲覧メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws IOException バイナリファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doDelUserRead(
        ActionMapping map,
        Bbs030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException,
                                TempFileException, IOException {

        BbsBiz biz = new BbsBiz();
        form.setBbs030memberSidRead(
                biz.getDeleteMember(
                        form.getBbs030SelectLeftUserRead(), form.getBbs030memberSidRead()));

        //管理者メンバーから削除する
        form.setBbs030memberSidAdm(
                biz.getDeleteMember(
                        form.getBbs030SelectLeftUserRead(), form.getBbs030memberSidAdm()));

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 追加(右矢印)ボタン押下時処理を行う
     * <br>[解  説] 管理者メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws IOException バイナリファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doAddUserAdm(
        ActionMapping map,
        Bbs030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException,
                                TempFileException, IOException {

        BbsBiz biz = new BbsBiz();
        form.setBbs030memberSidAdm(
                biz.getAddMember(form.getBbs030SelectRightUserAdm(), form.getBbs030memberSidAdm()));

        return  __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] 削除(左矢印)ボタン押下時処理を行う
     * <br>[解  説] 管理者メンバー
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws IOException バイナリファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doDelUserAdm(
        ActionMapping map,
        Bbs030Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, IOToolsException,
                                TempFileException, IOException {

        BbsBiz biz = new BbsBiz();
        form.setBbs030memberSidAdm(
                biz.getDeleteMember(
                        form.getBbs030SelectLeftUserAdm(), form.getBbs030memberSidAdm()));

        return __doDsp(map, form, req, res, con);
    }

    /**
     * <br>[機  能] tempディレクトリの画像を読み込む
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con Connection
     * @return ActionForward フォワード
     * @throws Exception 実行時例外
     */
    private ActionForward __doGetImageFile(ActionMapping map,
                                            Bbs030Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        String tempDir =
                _getBulletinTempDir(req) +  GSConstBulletin.TEMP_ICN_BBS + "/";
        String fullPath = IOTools.replaceFileSep(
                tempDir + imageFileSaveName__ + GSConstCommon.ENDSTR_SAVEFILE);

        //画像ファイル読込
        TempFileUtil.downloadInline(req, res, fullPath, imageFileName__, Encoding.UTF_8);

        return null;
    }

    /**
     * <br>[機  能] アイコン削除ボタンクリック時の処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException IOエラー
     * @throws TempFileException 添付ファイルUtil内での例外
     * @return ActionForward
     */
    private ActionForward __doTempDeleteMark(ActionMapping map,
                                      Bbs030Form form,
                                      HttpServletRequest req,
                                      HttpServletResponse res,
                                      Connection con)
        throws SQLException, IOToolsException, IOException, TempFileException {


        //添付ファイルを削除する
        //選択された添付ファイルを削除する
        IOTools.deleteDir(_getBulletinTempDir(req));
        imageFileName__ = "";
        imageFileSaveName__ = "";
        form.setBbs030ImageName(imageFileName__);
        form.setBbs030ImageSaveName(imageFileSaveName__);

        return __doDsp(map, form, req, res, con);
    }
}

