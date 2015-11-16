package jp.groupsession.v2.bbs.bbs080;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.bbs.AbstractBulletinAction;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs070.Bbs070Form;
import jp.groupsession.v2.bbs.bbs090.Bbs090Form;
import jp.groupsession.v2.bbs.dao.BbsThreInfDao;
import jp.groupsession.v2.bbs.model.BbsThreInfModel;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.bbs.pdf.BbsListPdfModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 掲示板 投稿一覧画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs080Action extends AbstractBulletinAction {

    /** フォーラム*/
    private static final int TYPE_FORUM__ = 0;
    /** スレッド */
    private static final int TYPE_THREAD__ = 1;
    /** 投稿 */
    private static final int TYPE_WRITE__ = 2;

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs080Action.class);

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

        if (cmd.equals("fileDownload") || cmd.equals("pdf")) {
            return true;

        }
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
        Bbs080Form bbsForm = (Bbs080Form) form;

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);

        //フォーラムの存在チェックを行う
        BbsBiz bbsBiz = new BbsBiz(con);
        if (!cmd.equals("backThreadList")
                && !bbsBiz.isCheckExistForum(con, bbsForm.getBbs010forumSid())) {
            return __setAlreadyDelWrite(map, req, bbsForm, con, TYPE_FORUM__, cmd);
        }

        //フォーラム閲覧権限チェック
        if (!_checkForumAuth(map, req, con, bbsForm.getBbs010forumSid())) {
            return map.findForward("gf_auth");
        }

        //スレッドの存在チェックを行う
        if (!cmd.equals("fileDownload") && !cmd.equals("tempview")
                && !cmd.equals("backThreadList")
                && !bbsBiz.isCheckExistThread(con, bbsForm.getThreadSid())) {
            return __setAlreadyDelWrite(map, req, bbsForm, con, TYPE_THREAD__, cmd);
        }

        //スレッドの掲示開始期間チェック
        if (!cmd.equals("fileDownload") && !cmd.equals("tempview")
                && !cmd.equals("backThreadList")
                && !__checkLimitFromDate(map, req, con, bbsForm.getThreadSid())) {
            return map.findForward("gf_msg");
        }

        //テンポラリディレクトリ内のファイルを削除
        if (!cmd.equals("fileDownload") && !cmd.equals("tempview")) {
            IOTools.deleteInDir(super._getBulletinTempDir(req));
        }

        if (cmd.equals("prevPage")) {
            //前ページクリック
            bbsForm.setBbs080page1(bbsForm.getBbs080page1() - 1);
            forward = __doInit(map, bbsForm, req, res, con);
        } else if (cmd.equals("nextPage")) {
            //次ページクリック
            bbsForm.setBbs080page1(bbsForm.getBbs080page1() + 1);
            forward = __doInit(map, bbsForm, req, res, con);
        } else if (cmd.equals("editThread")) {
            //編集ボタン(スレッド)クリック
            Bbs070Form form070 = new Bbs070Form();
            form070.copyFormParam(bbsForm);
            form070.setBbs080page1(bbsForm.getBbs080page1());
            form070.setBbs070cmdMode(GSConstBulletin.BBSCMDMODE_EDIT);
            req.setAttribute("bbs070Form", form070);
            forward = map.findForward("moveThreadDtl");
        } else if (cmd.equals("addWrite")) {
            //新規投稿ボタンクリック
            forward = map.findForward("addWrite");
        } else if (cmd.equals("editWrite")) {
            //編集ボタン(投稿)クリック
            Bbs090Form form090 = __createBbs090Form(bbsForm);
            form090.setBbs090cmdMode(GSConstBulletin.BBSCMDMODE_EDIT);
            req.setAttribute("bbs090Form", form090);
            forward = map.findForward("moveWriteDtl");
        } else if (cmd.equals("inyouWrite")) {
            //引用投稿ボタンクリック
            Bbs090Form form090 = __createBbs090Form(bbsForm);
            form090.setBbs090cmdMode(GSConstBulletin.BBSCMDMODE_INYOU);
            req.setAttribute("bbs090Form", form090);
            forward = map.findForward("moveWriteDtl");
        } else if (cmd.equals("fileDownload")) {
            //添付ファイルリンククリッククリック
            forward = __doDownLoad(map, bbsForm, req, res, con);
        } else if (cmd.equals("tempview")) {
            //添付ファイル表示
            forward = __doTempView(map, bbsForm, req, res, con);
        } else if (cmd.equals("delThread")) {
            //スレッド削除ボタンクリック
            forward = __doDelThread(map, bbsForm, req, res, con);
        } else if (cmd.equals("delWrite")) {
            //削除ボタン（投稿)クリック
            forward = __doDelWrite(map, bbsForm, req, res, con, cmd);
        } else if (cmd.equals("delThreadDecision")) {
            //スレッド削除確認画面からの遷移
            forward = __doDelThreadComplete(map, bbsForm, req, res, con);
        } else if (cmd.equals("delWriteDecision")) {
            //投稿削除確認画面からの遷移
            forward = __doDelWriteComplete(map, bbsForm, req, res, con, cmd);
        } else if (cmd.equals("backThreadList")) {
            //戻るボタンクリック
            if (bbsForm.getBbsmainFlg() == 1) {
                forward = map.findForward("gf_main");
            } else if (StringUtil.isNullZeroString(bbsForm.getSearchDspID())) {
                forward = map.findForward("moveThreadList");
            } else {
                forward = map.findForward("moveSearchList");
            }
        } else if (cmd.equals("month")) {
            //月間スケジュール
            forward = __doScheduleMonth(map, bbsForm, req, res, con);
        } else if (cmd.equals("pdf")) {
            //pdfダウンロード
            forward = __doDownLoadPdf(map, bbsForm, req, res, con);

        } else {
            //初期表示
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
     * @throws Exception 実行例外
     */
    private ActionForward __doInit(ActionMapping map,
        Bbs080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        con.setAutoCommit(true);
        Bbs080ParamModel paramMdl = new Bbs080ParamModel();
        paramMdl.setParam(form);
        Bbs080Biz biz = new Bbs080Biz();
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstBulletin.PLUGIN_ID_BULLETIN);

        int resultCode = biz.setInitData(getRequestModel(req), paramMdl, con, userSid, adminUser,
                                        getAppRootPath(), _getBulletinTempDir(req));
        int bfiSid = paramMdl.getBbs010forumSid();
        int btiSid = paramMdl.getThreadSid();
        paramMdl.setFormData(form);


        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));
        form.setBbs080searchUse(CommonBiz.getWebSearchUse(pconfig));
        con.setAutoCommit(false);

        //スレッドが存在しない場合は共通メッセージ画面へ遷移する
        if (resultCode != Bbs080Biz.RESULT_NORMAL) {

            //フォーラムが不正な場合は共通メッセージ画面へ遷移する
            if (resultCode == Bbs080Biz.RESULT_INVALIDFORUM) {
                return map.findForward("gf_auth");
            }

            Cmn999Form cmn999Form = new Cmn999Form();
            ActionForward urlForward = map.findForward("backBBSList");

            MessageResources msgRes = getResources(req);
            cmn999Form.setIcon(Cmn999Form.ICON_INFO);
            cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
            cmn999Form.setType(Cmn999Form.TYPE_OK);
            cmn999Form.setUrlOK(urlForward.getPath());

            //メッセージセット
            if (resultCode == Bbs080Biz.RESULT_NOTHREAD) {
                cmn999Form.setMessage(msgRes.getMessage("error.nothing.thread"));
            } else if (resultCode == Bbs080Biz.RESULT_OVERLIMIT) {
                cmn999Form.setMessage(msgRes.getMessage("error.over.limitdate.thread"));
            }
            cmn999Form = __setFormParam(cmn999Form, form);
            req.setAttribute("cmn999Form", cmn999Form);

            return map.findForward("gf_msg");
        }

        BbsBiz bbsBiz = new BbsBiz(con);
        //集計用データ（閲覧）を登録する
        bbsBiz.regBbsViewLogCnt(con, getSessionUserSid(req), bfiSid, btiSid, new UDate());

        return map.getInputForward();
    }

    /**
     * <br>[機  能] スレッド削除ボタンクリック時処理
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
    private ActionForward __doDelThread(ActionMapping map,
        Bbs080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //ユーザが指定されたスレッドを削除可能か判定する
        Bbs080Biz biz = new Bbs080Biz();
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstBulletin.PLUGIN_ID_BULLETIN);

        if (!adminUser) {
            con.setAutoCommit(true);
            boolean canDelThread = biz.canDeleteThread(con, form.getThreadSid(), userSid);
            con.setAutoCommit(false);
            if (!canDelThread) {
                return __setAuthErrMsgPage(map, req, form, con, TYPE_THREAD__);
            }
        }

        return __setDeleteConfirmMsgPageParam(map, req, form, con, TYPE_THREAD__);
    }

    /**
     * <br>[機  能] スレッド削除完了時処理
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
    private ActionForward __doDelThreadComplete(ActionMapping map,
        Bbs080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //ユーザが指定されたスレッドを削除可能か判定する
        Bbs080Biz biz = new Bbs080Biz();
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstBulletin.PLUGIN_ID_BULLETIN);

        if (!adminUser) {
            con.setAutoCommit(true);
            boolean canDelThread = biz.canDeleteThread(con, form.getThreadSid(), userSid);
            con.setAutoCommit(false);
            if (!canDelThread) {
                return __setAuthErrMsgPage(map, req, form, con, TYPE_THREAD__);
            }
        }

        //スレッド情報の削除
        boolean commit = false;
        try {
            biz.deleteThreadData(form.getBbs010forumSid(), form.getThreadSid(), con, userSid);
            commit = true;
        } catch (Exception e) {
            log__.error("スレッドの削除に失敗", e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String textDel = gsMsg.getMessage("cmn.delete");

        //ログ出力処理
        BbsBiz bbsBiz = new BbsBiz(con);
        bbsBiz.outPutLog(
                map, reqMdl, textDel, GSConstLog.LEVEL_TRACE,
                "[title]" + form.getBbs080delTitle());

        return __setDeleteCompleteMsgPageParam(map, req, form, con, TYPE_THREAD__);
    }

    /**
     * <br>[機  能] 削除ボタン(投稿)クリック時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param cmd コマンド
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doDelWrite(ActionMapping map,
        Bbs080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con,
        String cmd
        )
        throws Exception {

        //投稿の存在チェックを行う
        BbsBiz bbsBiz = new BbsBiz(con);
        if (!bbsBiz.isCheckExistWrite(con, form.getBbs080writeSid())) {
            return __setAlreadyDelWrite(map, req, form, con, TYPE_WRITE__, cmd);
        }

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //ユーザが指定された投稿を削除可能か判定する
        Bbs080Biz biz = new Bbs080Biz();
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstBulletin.PLUGIN_ID_BULLETIN);

        if (!adminUser && !biz.canDeleteWrite(con, form.getBbs080writeSid(), userSid)) {
            return __setAuthErrMsgPage(map, req, form, con, TYPE_WRITE__);
        }

        return __setDeleteConfirmMsgPageParam(map, req, form, con, TYPE_WRITE__);
    }

    /**
     * <br>[機  能] 投稿削除完了時処理
     * <br>[解  説]
     * <br>[備  考]
     * @param map マップ
     * @param form フォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @param cmd コマンド
     * @return ActionForward フォワード
     * @throws Exception 実行例外
     */
    private ActionForward __doDelWriteComplete(ActionMapping map,
        Bbs080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con,
        String cmd
        )
        throws Exception {

        //投稿の存在チェックを行う
        BbsBiz bbsBiz = new BbsBiz(con);
        if (!bbsBiz.isCheckExistWrite(con, form.getBbs080writeSid())) {
            return __setAlreadyDelWrite(map, req, form, con, TYPE_WRITE__, cmd);
        }

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        //ユーザが指定されたスレッドを削除可能か判定する
        Bbs080ParamModel paramMdl = new Bbs080ParamModel();
        paramMdl.setParam(form);
        Bbs080Biz biz = new Bbs080Biz();
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstBulletin.PLUGIN_ID_BULLETIN);

        if (!adminUser && !biz.canDeleteWrite(con, form.getBbs080writeSid(), userSid)) {
            return __setAuthErrMsgPage(map, req, form, con, TYPE_WRITE__);
        }

        //投稿情報の削除
        boolean commit = false;
        try {
            biz.deleteWriteData(paramMdl, con, userSid);
            paramMdl.setFormData(form);
            commit = true;
        } catch (Exception e) {
            log__.error("投稿の削除に失敗", e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String textDel = gsMsg.getMessage("cmn.delete");
        String textToukouSakujo = gsMsg.getMessage("bbs.26");

        //ログ出力処理
        bbsBiz.outPutLog(
                map, reqMdl, textDel,
                GSConstLog.LEVEL_TRACE, textToukouSakujo);

        return __setDeleteCompleteMsgPageParam(map, req, form, con, TYPE_WRITE__);
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
        Bbs080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        CommonBiz cmnBiz = new CommonBiz();
        CmnBinfModel cbMdl = null;

        //添付ファイルバイナリSIDチェック
        Bbs080Biz bbs080Biz = new Bbs080Biz();
        boolean tmpHnt = bbs080Biz.cheTmpHnt(con,
                form.getBbs080writeSid(), form.getBbs080binSid(),
                form.getBbs010forumSid(), form.getThreadSid());

        if (!tmpHnt) {
            return null;

        } else {
            cbMdl = cmnBiz.getBinInfo(con, form.getBbs080binSid(),
                    GroupSession.getResourceManager().getDomain(req));
        }

        if (cbMdl != null) {

            RequestModel reqMdl = getRequestModel(req);
            GsMessage gsMsg = new GsMessage(reqMdl);
            String textDownload = gsMsg.getMessage("cmn.download");

            //ログ出力処理
            BbsBiz bbsBiz = new BbsBiz(con);
            bbsBiz.outPutLog(
                    map, reqMdl, textDownload,
                    GSConstLog.LEVEL_INFO, cbMdl.getBinFileName(),
                    String.valueOf(cbMdl.getBinSid()),
                    GSConstBulletin.BBS_LOG_FLG_DOWNLOAD);

            JDBCUtil.closeConnectionAndNull(con);

            //ファイルをダウンロードする
            TempFileUtil.downloadAtachment(req, res, cbMdl, getAppRootPath(),
                                        Encoding.UTF_8);

        }

        return null;
    }

    /**
     * <br>[機  能] 添付ファイルをブラウザ内に表示処理
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
    private ActionForward __doTempView(
        ActionMapping map,
        Bbs080Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        CommonBiz cmnBiz = new CommonBiz();
        CmnBinfModel cbMdl = null;

        //添付ファイルバイナリSIDチェック
        Bbs080Biz bbs080Biz = new Bbs080Biz();
        boolean tmpHnt = bbs080Biz.cheTmpHnt(con,
                form.getBbs080writeSid(), form.getBbs080binSid(),
                form.getBbs010forumSid(), form.getThreadSid());

        if (!tmpHnt) {
            return null;

        } else {
            cbMdl = cmnBiz.getBinInfo(con, form.getBbs080binSid(),
                    GroupSession.getResourceManager().getDomain(req));
        }

        if (cbMdl != null) {
            JDBCUtil.closeConnectionAndNull(con);

            //ファイルをダウンロードする
            TempFileUtil.downloadInline(req, res, cbMdl, getAppRootPath(),
                                        Encoding.UTF_8);
        }
        return null;
    }


    /**
     * <br>[機  能] 月間スケジュールへ遷移
     * <br>[解  説]
     * <br>[備  考]
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws Exception 実行例外
     */
    private ActionForward __doScheduleMonth(ActionMapping map, Bbs080Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
    throws Exception {
        ActionForward forward = null;
        forward = map.findForward("month");
        return forward;
    }

    /**
     * 投稿作成画面へ渡すパラメータをフォームに設定
     * @param form フォーム
     * @return 投稿作成画面のフォーム
     */
    private Bbs090Form __createBbs090Form(Bbs080Form form) {
        Bbs090Form form090 = new Bbs090Form();
        form090.copyFormParam(form);
        form090.setBbs080page1(form.getBbs080page1());
        form090.setBbs080writeSid(form090.getBbs080writeSid());

        return form090;
    }

    /**
     * <br>[機  能] 削除確認の共通メッセージ画面遷移時の設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @param con コネクション
     * @param type 0:スレッド、1:投稿
     * @return ActionForward フォワード
     * @throws SQLException SQL例外発生
     */
    private ActionForward __setDeleteConfirmMsgPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Bbs080Form form,
        Connection con,
        int type) throws SQLException {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        GsMessage gsMsg = new GsMessage();
        String textWrite = gsMsg.getMessage(req, "bbs.16");

        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        urlForward = map.findForward("mine");
        cmn999Form.setType(Cmn999Form.TYPE_OKCANCEL);
        cmn999Form.setUrlCancel(urlForward.getPath());

        if (type == TYPE_THREAD__) {
            cmn999Form.setUrlOK(urlForward.getPath() + "?CMD=delThreadDecision");

            BbsBiz biz = new BbsBiz();
            BulletinDspModel btMdl = biz.getThreadData(con, form.getThreadSid());
            if (btMdl == null) {
                throw new SQLException("スレッド情報の取得に失敗");
            }

            //メッセージセット
            if (btMdl.getWriteCnt() > 1) {
                String msgState = "sakujo.thread.kakunin2";
                cmn999Form.setMessage(msgRes.getMessage(msgState,
                        StringUtilHtml.transToHTmlPlusAmparsant(btMdl.getBtiTitle()),
                        String.valueOf(btMdl.getWriteCnt())));
            } else {
                String msgState = "sakujo.thread.kakunin1";
                cmn999Form.setMessage(msgRes.getMessage(msgState,
                        StringUtilHtml.transToHTmlPlusAmparsant(btMdl.getBtiTitle())));
            }
            form.setBbs080delTitle(StringUtilHtml.transToHTmlPlusAmparsant(btMdl.getBtiTitle()));
        } else if (type == TYPE_WRITE__) {
            cmn999Form.setUrlOK(urlForward.getPath() + "?CMD=delWriteDecision");

            //メッセージセット
            String msgState = "sakujo.kakunin.once";
            cmn999Form.setMessage(msgRes.getMessage(msgState, textWrite));
        }


        cmn999Form = __setFormParam(cmn999Form, form);
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 削除完了の共通メッセージ画面遷移時の設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @param con コネクション
     * @param type 0:スレッド、1:投稿
     * @return ActionForward フォワード
     * @throws SQLException SQL例外発生
     */
    private ActionForward __setDeleteCompleteMsgPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Bbs080Form form,
        Connection con,
        int type) throws SQLException {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        GsMessage gsMsg = new GsMessage();

        String msgState = "sakujo.kanryo.object";
        if (type == TYPE_THREAD__) {
            String textThread = gsMsg.getMessage(req, "bbs.2");
            urlForward = map.findForward("moveThreadList");
            cmn999Form.setMessage(msgRes.getMessage(msgState, textThread));
        } else if (type == TYPE_WRITE__) {
            String textWrite = gsMsg.getMessage(req, "bbs.16");
            urlForward = map.findForward("mine");
            cmn999Form.setMessage(msgRes.getMessage(msgState, textWrite));
        }

        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form = __setFormParam(cmn999Form, form);
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] スレッド or 投稿削除時権限エラーメッセージ画面遷移時の設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @param con コネクション
     * @param type 0:スレッド、1:投稿
     * @return ActionForward フォワード
     * @throws SQLException SQL例外発生
     */
    private ActionForward __setAuthErrMsgPage(
        ActionMapping map,
        HttpServletRequest req,
        Bbs080Form form,
        Connection con,
        int type) throws SQLException {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        urlForward = map.findForward("mine");
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage();
        String textDelThread = gsMsg.getMessage(req, "bbs.17");
        String textDelWrite = gsMsg.getMessage(req, "bbs.18");
        String textDel = gsMsg.getMessage(req, "cmn.delete");

        //メッセージセット
        String msgState = "error.edit.power.user";
        if (type == TYPE_THREAD__) {
            cmn999Form.setMessage(msgRes.getMessage(msgState,
                                 textDelThread,
                                 textDel));
        } else if (type == TYPE_WRITE__) {
            cmn999Form.setMessage(msgRes.getMessage(msgState,
                                 textDelWrite,
                                 textDel));
        }

        cmn999Form = __setFormParam(cmn999Form, form);
        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 共通メッセージフォームにフォームパラメータを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn999Form 共通メッセージフォーム
     * @param form アクションフォーム
     * @return 共通メッセージフォーム
     */
    private Cmn999Form __setFormParam(Cmn999Form cmn999Form, Bbs080Form form) {

        cmn999Form.addHiddenParam("s_key", form.getS_key());
        cmn999Form.addHiddenParam("bbs010page1", form.getBbs010page1());
        cmn999Form.addHiddenParam("bbs010forumSid", form.getBbs010forumSid());
        cmn999Form.addHiddenParam("bbs060page1", form.getBbs060page1());
        cmn999Form.addHiddenParam("bbs040forumSid", form.getBbs040forumSid());
        cmn999Form.addHiddenParam("bbs040keyKbn", form.getBbs040keyKbn());
        cmn999Form.addHiddenParam("bbs040taisyouThread", form.getBbs040taisyouThread());
        cmn999Form.addHiddenParam("bbs040taisyouNaiyou", form.getBbs040taisyouNaiyou());
        cmn999Form.addHiddenParam("bbs040userSei", form.getBbs040userName());
        cmn999Form.addHiddenParam("bbs040readKbn", form.getBbs040readKbn());
        cmn999Form.addHiddenParam("bbs040dateNoKbn", form.getBbs040dateNoKbn());
        cmn999Form.addHiddenParam("bbs040fromYear", form.getBbs040fromYear());
        cmn999Form.addHiddenParam("bbs040fromMonth", form.getBbs040fromMonth());
        cmn999Form.addHiddenParam("bbs040fromDay", form.getBbs040fromDay());
        cmn999Form.addHiddenParam("bbs040toYear", form.getBbs040toYear());
        cmn999Form.addHiddenParam("bbs040toMonth", form.getBbs040toMonth());
        cmn999Form.addHiddenParam("bbs040toDay", form.getBbs040toDay());
        cmn999Form.addHiddenParam("bbs041page1", form.getBbs041page1());
        cmn999Form.addHiddenParam("bbs080page1", form.getBbs080page1());
        cmn999Form.addHiddenParam("bbs080writeSid", form.getBbs080writeSid());
        cmn999Form.addHiddenParam("bbs080orderKey", form.getBbs080orderKey());
        cmn999Form.addHiddenParam("threadSid", form.getThreadSid());
        cmn999Form.addHiddenParam("bbs080delTitle", form.getBbs080delTitle());
        cmn999Form.addHiddenParam("searchDspID", form.getSearchDspID());
        cmn999Form.addHiddenParam("bbsmainFlg", form.getBbsmainFlg());
        return cmn999Form;
    }

    /**
     * <br>[機  能] PDFファイルダウンロード処理を行います。
     * <br>[解  説]
     * <br>[備  考]
     *
     * -------テンポラリファイル名ルール-------
     * ○ダウンロード用PDFファイル一時保存ディレクトリ
     *    プラグインID/セッションID/bbspdf/pdfBbs_{フォーラムSID}_{スレッドSID}.pdf
     *
     * @param map アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @param con コネクション
     * @return ActionForward
     * @throws SQLException SQL実行時例外
     * @throws IOException ファイルの書き出しに失敗
     * @throws IOToolsException テンポラリディレクトリの削除に失敗
     * @throws TempFileException 添付ファイル情報の取得に失敗
     * @throws Exception 実行例外
     */
    private ActionForward __doDownLoadPdf(ActionMapping map, Bbs080Form form,
            HttpServletRequest req, HttpServletResponse res, Connection con)
            throws SQLException, IOException, IOToolsException, TempFileException, Exception {

        log__.debug("PDFファイルダウンロード処理 開始");

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }

        RequestModel reqMdl = getRequestModel(req);

        //アプリケーションルートパス取得
        String appRootPath = getAppRootPath();
        //プラグイン固有のテンポラリパス取得
        CommonBiz cmnBiz = new CommonBiz();
        String outTempDir = IOTools.replaceFileSep(
                cmnBiz.getTempDir(
                        getTempPath(req), GSConstBulletin.PLUGIN_ID_BULLETIN, reqMdl)
                        + "bbspdf/");

        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstBulletin.PLUGIN_ID_BULLETIN);

        //PDF生成
        Bbs080Biz biz = new Bbs080Biz(con, reqMdl);
        Bbs080ParamModel paramMdl = new Bbs080ParamModel();
        paramMdl.setParam(form);
        BbsListPdfModel pdfMdl =
                biz.createBbsListPdf(paramMdl, appRootPath, outTempDir, userSid, adminUser);
        paramMdl.setFormData(form);

        String downloadFileName = pdfMdl.getFileName();
        String saveFileName = pdfMdl.getSaveFileName();

        String outFilePath = IOTools.setEndPathChar(outTempDir) + saveFileName;
        GsMessage gsMsg = new GsMessage(reqMdl);
        String pdfDownload = gsMsg.getMessage("cmn.pdf");

        //ログ出力処理
        BbsBiz bbsBiz = new BbsBiz(con);
        bbsBiz.outPutLog(
                map, reqMdl, pdfDownload,
                GSConstLog.LEVEL_INFO, downloadFileName,
                form.getThreadSid(), null, GSConstBulletin.BBS_LOG_FLG_PDF);

        TempFileUtil.downloadAtachment(req, res, outFilePath, downloadFileName, Encoding.UTF_8);
        //TEMPディレクトリ削除
        IOTools.deleteDir(IOTools.setEndPathChar(outTempDir));

        return null;
    }

    /**
     * <br>[機  能] スレッドを掲示していいかのチェックを行う
     * <br>[解  説]
     * <br>[備  考] 掲示開始日が未来の日付の場合は表示不可能
     * @param map マップ
     * @param req リクエスト
     * @param con コネクション
     * @param btiSid スレッドSID
     * @return 権限の有無 true:掲示OK false:掲示NG
     * @throws Exception 実行例外
     */
    protected boolean __checkLimitFromDate(ActionMapping map,
        HttpServletRequest req,
        Connection con,
        int btiSid)
        throws Exception {

        log__.debug("checkForumAuth START");


        BbsThreInfDao dao = new BbsThreInfDao(con);
        BbsThreInfModel btiMdl = dao.select(btiSid);
        BbsBiz bbsBiz = new BbsBiz();

        //掲示期限 期限なしの場合
        if (btiMdl.getBtiLimit() == GSConstBulletin.THREAD_LIMIT_NO) {
            return true;
        }

        //掲示開始日 < 現在の日時の場合
        if (!bbsBiz.checkReserveDate(btiMdl.getBtiLimitFrDate(), new UDate())) {
            return true;
        }

        //権限なしの場合はメッセージページのパラメータを設定する
        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("backBBSList");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "error.access.double.submit";
        cmn999Form.setMessage(msgRes.getMessage(msgState));

        req.setAttribute("cmn999Form", cmn999Form);

        log__.debug("checkForumAuth END");

        return false;
    }

    /**
     * <br>[機  能] スレッド or 投稿削除時権限エラーメッセージ画面遷移時の設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @param con コネクション
     * @param mode コマンドモード 0:フォーラム 1:スレッド 3:投稿
     * @param cmd コマンド
     * @return ActionForward フォワード
     * @throws SQLException SQL例外発生
     */
    private ActionForward __setAlreadyDelWrite(
        ActionMapping map,
        HttpServletRequest req,
        Bbs080Form form,
        Connection con,
        int mode,
        String cmd) throws SQLException {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        urlForward = map.findForward("backBBSList");
        cmn999Form.setUrlOK(urlForward.getPath());

        GsMessage gsMsg = new GsMessage(getRequestModel(req));
        String textDelWrite = null;
        String textDel = gsMsg.getMessage("cmn.operations");

        if (mode == TYPE_FORUM__) {
            textDelWrite = gsMsg.getMessage("bbs.3");
        } else if (mode == TYPE_THREAD__) {
            textDelWrite = gsMsg.getMessage("bbs.2");
        } else {
            textDelWrite = gsMsg.getMessage("bbs.16");
        }

        //PDF出力
        if (cmd.equals("pdf")) {
            textDel = gsMsg.getMessage("cmn.pdf");
        } else if (cmd.equals("delThread")) {
            //スレッド削除
            textDel = gsMsg.getMessage("bbs.bbs080.1");
        } else if (cmd.equals("delWrite")) {
            //投稿削除
            textDel = gsMsg.getMessage("cmn.delete");
        }

        //メッセージセット
        String msgState = "error.none.edit.data";
        cmn999Form.setMessage(msgRes.getMessage(msgState,
                textDelWrite,
                textDel));

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }
}
