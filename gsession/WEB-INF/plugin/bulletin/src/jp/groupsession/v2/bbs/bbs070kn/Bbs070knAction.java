package jp.groupsession.v2.bbs.bbs070kn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.bbs.AbstractBulletinAction;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs080.Bbs080Biz;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 掲示板 スレッド登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs070knAction extends AbstractBulletinAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs070knAction.class);

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
        Bbs070knForm bbsForm = (Bbs070knForm) form;

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);

        //フォーラム閲覧権限チェック
        if (!cmd.equals("fileDownload")) {
            con.setAutoCommit(true);
            boolean forumAuth = _checkForumAuth(map, req, con, bbsForm.getBbs010forumSid());
            con.setAutoCommit(false);
            if (!forumAuth) {
                return map.findForward("gf_msg");
            }
        }

        if (cmd.equals("decision")) {
            //確定ボタンクリック
            forward = __doDecision(map, bbsForm, req, res, con);
        } else if (cmd.equals("backToInput")) {
            //戻るボタンクリック
            forward = map.findForward("backToInput");
        } else if (cmd.equals("fileDownload")) {
            log__.debug("添付ファイルダウンロード");
            forward = __doDownLoad(map, bbsForm, req, res, con);
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
        Bbs070knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {
        //投稿一覧からの遷移、かつ処理モード = 編集の場合はスレッド情報を取得する
        if (form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
            //投稿の存在チェックを行う
            BbsBiz bbsBiz = new BbsBiz();
            if (!bbsBiz.isCheckExistWrite(con, form.getBbs080writeSid())) {
                //フォーラム内スレッド件数取得
                return __setAlreadyDelWrite(map, req, form, con);
            }
        }

        Bbs070knParamModel paramMdl = new Bbs070knParamModel();
        paramMdl.setParam(form);
        Bbs070knBiz biz = new Bbs070knBiz(con);
        biz.setInitData(paramMdl, getRequestModel(req), _getBulletinTempDir(req));
        paramMdl.setFormData(form);
        return map.getInputForward();
    }

    /**
     * <br>[機  能] 確定ボタンクリック時の処理
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
    private ActionForward __doDecision(ActionMapping map,
        Bbs070knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {

        //投稿一覧からの遷移、かつ処理モード = 編集の場合はスレッド情報を取得する
        if (form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
            //投稿の存在チェックを行う
            BbsBiz bbsBiz = new BbsBiz();
            if (!bbsBiz.isCheckExistWrite(con, form.getBbs080writeSid())) {
                //フォーラム内スレッド件数取得
                return __setAlreadyDelWrite(map, req, form, con);
            }
        }
        //入力チェック
        String tempDir = _getBulletinTempDir(req);
        ActionErrors errors = new ActionErrors();
        errors = form.validateCheck(con, getRequestModel(req), tempDir);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }
        //2重投稿
        if (!isTokenValid(req, true)) {
            log__.info("２重投稿");
            return getSubmitErrorPage(map, req);
        }

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }



        int backFlg = 0;

        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, getSessionUserModel(req), getPluginId());

        Bbs070knBiz biz = new Bbs070knBiz(con);
        MlCountMtController cntCon = getCountMtController(req);
        BbsBiz bbsBiz = new BbsBiz(con);

        int btiSid = 0;
        boolean rsvThrFlg = false;
        boolean commit = false;
        UDate now = new UDate();
        try {
            JDBCUtil.autoCommitOff(con);

            Bbs070knParamModel paramMdl = new Bbs070knParamModel();
            paramMdl.setParam(form);

            if (form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_ADD) {

                //登録処理
                //スレッドSIDを取得
                btiSid = biz.insertThreadData(paramMdl, cntCon, userSid,
                                                getAppRootPath(), tempDir, now);

                //フォーラムSID
                int bfiSid = paramMdl.getBbs010forumSid();

                //スレッド閲覧情報を更新する
                Bbs080Biz biz080 = new Bbs080Biz();
                biz080.updateView(con, btiSid, userSid, bfiSid);

                //グループSIDを取得
                int groupSid = 0;
                //グループSIDを取得
                if (paramMdl.getBbs070contributor() > 0) {
                    groupSid = paramMdl.getBbs070contributor();
                    userSid = -1;
                } else {
                    groupSid = -1;
                }

              //集計用データを登録する
              bbsBiz.regBbsWriteLogCnt(
                      con, userSid, groupSid, bfiSid, btiSid, new UDate());

            } else if (form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {

                //更新処理
                backFlg = biz.updateThreadData(paramMdl, cntCon, userSid,
                                    getAppRootPath(), tempDir);


                //遷移元が掲示予定一覧の場合
                if (form.getBbs070BackFlg() == 1) {
                    //変更後、掲示予定スレッド件数を取得する
                    BulletinDao btDao = new BulletinDao(con);
                    boolean allFlg = false;
                    if (adminUser) {
                        allFlg = true;
                    }

                    int rsvThrNum = btDao.countRsvThreData(
                            paramMdl.getBbs010forumSid(), userSid, allFlg, new UDate());

                    if (rsvThrNum > 0) {
                        rsvThrFlg = true;
                    }
                }


            }
            paramMdl.setFormData(form);
            commit = true;
        } catch (Exception e) {
            log__.error("スレッド作成処理エラー", e);
            throw e;
        } finally {
            if (commit) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }


        if (form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_ADD) {
            UDate limitFrDate = now.cloneUDate();
            limitFrDate.setZeroHhMmSs();
            limitFrDate.setDate(form.getBbs070limitFrYear(),
                    form.getBbs070limitFrMonth(),
                    form.getBbs070limitFrDay());

            //メール送信区分
            boolean mailSendKbn = true;
            if (form.getBbs070limit() == GSConstBulletin.THREAD_LIMIT_YES
                    && bbsBiz.checkReserveDate(limitFrDate, now)) {
                mailSendKbn = false;
            }

            if (mailSendKbn) {
                Bbs070knParamModel paramMdl = new Bbs070knParamModel();
                paramMdl.setParam(form);
                //ショートメールで通知
                if (bbsBiz.canSendSmail(con, userSid)) {
                    PluginConfig pconfig = getPluginConfigForMain(getPluginConfig(req), con);
                    if (cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig)) {
                        biz.sendSmail(paramMdl, cntCon, btiSid, userSid,
                                getAppRootPath(), tempDir, getPluginConfig(req),
                                getRequestModel(req));
                    }
                }
                paramMdl.setFormData(form);
            }
        }

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);

        //ログ出力処理
        String opCode = "";
        if (form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_ADD) {
            String textEntry = gsMsg.getMessage("cmn.entry");
            opCode = textEntry;
        } else if (form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
            String textEdit = gsMsg.getMessage("cmn.change");
            opCode = textEdit;
        }
        bbsBiz.outPutLog(
                map, reqMdl, opCode, GSConstLog.LEVEL_TRACE, "[title]" + form.getBbs070title());

        //テンポラリディレクトリ内のファイルを削除
        IOTools.deleteInDir(tempDir);

        __setCompPageParam(map, req, form, rsvThrFlg, backFlg);
        return map.findForward("gf_msg");
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
        Bbs070knForm form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con) throws SQLException, Exception {

        String tempDir = _getBulletinTempDir(req);
        String fileId = form.getBbs070knTmpFileId();

        BbsBiz bbsBiz = new BbsBiz(con);
        String fileName = bbsBiz.downloadTempFile(req, res, tempDir, fileId);

        RequestModel reqMdl = getRequestModel(req);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String textDownload = gsMsg.getMessage("cmn.download");

        //ログ出力処理
        bbsBiz.outPutLog(
                map, reqMdl, textDownload,
                GSConstLog.LEVEL_INFO, fileName, fileId , GSConstBulletin.BBS_LOG_FLG_DOWNLOAD);

        return null;
    }

    /**
     * <br>[機  能] 完了メッセージ画面遷移時のパラメータを設定
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param form アクションフォーム
     * @param rsvThrFlg 掲示予定一覧遷移フラグ（遷移元が掲示予定一覧の場合のみ使用）
     * @param backFlg 戻り画面フラグ 0:投稿一覧 1:スレッド一覧 2:フォーラム一覧
     */
    private void __setCompPageParam(
        ActionMapping map,
        HttpServletRequest req,
        Bbs070knForm form,
        boolean rsvThrFlg,
        int backFlg) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_INFO);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);

        GsMessage gsMsg = new GsMessage();
        String textthread = gsMsg.getMessage(req, "bbs.2");

        //メッセージセット
        String msgState = null;
        if (form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_ADD) {
            urlForward = map.findForward("moveThreadList");
            msgState = "touroku.kanryo.object";
        } else if (form.getBbs070cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
            //遷移元が掲示予定一覧の場合
            if (form.getBbs070BackFlg() == 1) {
                if (rsvThrFlg) {
                    urlForward = map.findForward("rsvThreadList");
                } else {
                    urlForward = map.findForward("backBBSList");
                }
            } else {
                if (backFlg == 2) {
                    urlForward = map.findForward("backBBSList");
                } else if (backFlg == 1) {
                    urlForward = map.findForward("moveThreadList");
                } else {
                    urlForward = map.findForward("moveWriteList");
                }
            }
            msgState = "hensyu.kanryo.object";
        }
        cmn999Form.setUrlOK(urlForward.getPath());
        cmn999Form.setMessage(msgRes.getMessage(msgState,
                textthread));

        cmn999Form.addHiddenParam("s_key", form.getS_key());
        cmn999Form.addHiddenParam("bbs010page1", form.getBbs010page1());
        cmn999Form.addHiddenParam("bbs010forumSid", form.getBbs010forumSid());
        cmn999Form.addHiddenParam("bbs060page1", form.getBbs060page1());
        cmn999Form.addHiddenParam("searchDspID", form.getSearchDspID());
        cmn999Form.addHiddenParam("bbs040forumSid", form.getBbs040forumSid());
        cmn999Form.addHiddenParam("bbs040keyKbn", form.getBbs040keyKbn());
        cmn999Form.addHiddenParam("bbs040taisyouThread", form.getBbs040taisyouThread());
        cmn999Form.addHiddenParam("bbs040taisyouNaiyou", form.getBbs040taisyouNaiyou());
        cmn999Form.addHiddenParam("bbs040userName", form.getBbs040userName());
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
        cmn999Form.addHiddenParam("bbs080orderKey", form.getBbs080orderKey());
        cmn999Form.addHiddenParam("threadSid", form.getThreadSid());

        req.setAttribute("cmn999Form", cmn999Form);

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
        Bbs070knForm form,
        Connection con) throws SQLException {

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

