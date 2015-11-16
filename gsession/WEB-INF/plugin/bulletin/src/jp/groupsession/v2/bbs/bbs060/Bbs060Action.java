package jp.groupsession.v2.bbs.bbs060;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bbs.AbstractBulletinAction;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs040.Bbs040Biz;
import jp.groupsession.v2.bbs.bbs040.Bbs040Form;
import jp.groupsession.v2.bbs.bbs041.Bbs041Form;
import jp.groupsession.v2.bbs.bbs041.Bbs041ParamModel;
import jp.groupsession.v2.bbs.bbs070.Bbs070Form;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.cmn.GSConstLog;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * <br>[機  能] 掲示板 スレッド一覧画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs060Action extends AbstractBulletinAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs060Action.class);

    /**
     * <br>[機  能] Connectionに設定する自動コミットモードを返す
     * <br>[解  説]
     * <br>[備  考]
     * @return AutoCommit設定値
     */
    protected boolean _getAutoCommit() {
        return true;
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
        Bbs060Form bbsForm = (Bbs060Form) form;

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);

        if (!cmd.equals("getImageFile")) {
            //フォーラム閲覧権限チェック
            con.setAutoCommit(true);
            boolean forumAuth = _checkForumAuth(map, req, con, bbsForm.getBbs010forumSid());
            con.setAutoCommit(false);
            if (!forumAuth) {
                return map.findForward("gf_msg");
            }
        }

        if (cmd.equals("prevPage")) {
            //前ページクリック
            bbsForm.setBbs060page1(bbsForm.getBbs060page1() - 1);
            forward = __doInit(map, bbsForm, req, res, con);
        } else if (cmd.equals("nextPage")) {
            //次ページクリック
            bbsForm.setBbs060page1(bbsForm.getBbs060page1() + 1);
            forward = __doInit(map, bbsForm, req, res, con);
        } else if (cmd.equals("searchThre")) {
            //検索ボタンクリック
            forward = __doSearch(map, bbsForm, req, res, con);
        } else if (cmd.equals("searchThreDtl")) {
            //詳細検索ボタンクリック
            Bbs040Form form040 = new Bbs040Form();
            form040.setS_key(bbsForm.getS_key());
            form040.setBbs010forumSid(bbsForm.getBbs010forumSid());
            form040.setBbs010page1(bbsForm.getBbs010page1());
            form040.setBbs060page1(bbsForm.getBbs060page1());
            if (form040.getBbs040forumSid() <= 0) {
                form040.setBbs040dateNoKbn(1);
                form040.setBbs040keyKbn(GSConstBulletin.KEYWORDKBN_AND);
                form040.setBbs040readKbn(0);
                form040.setBbs040taisyouThread(1);
                form040.setBbs040taisyouNaiyou(1);
            }
            form040.setBbs040forumSid(bbsForm.getBbs010forumSid());
            form040.setSearchDspID(GSConstBulletin.SERCHDSPID_THREADDTL);
            req.setAttribute("bbs040Form", form040);
            forward = map.findForward("moveSearchDtl");
        } else if (cmd.equals("addThre")) {
            //スレッド新規作成ボタンクリック
            __setBbs070Form(req, bbsForm, GSConstBulletin.BBSCMDMODE_ADD);
            forward = map.findForward("addThre");
        } else if (cmd.equals("backBBSList")) {
            //戻るボタンクリック
            if (bbsForm.getBbsmainFlg() == 1) {
                forward = map.findForward("gf_main");
            } else {
                forward = map.findForward("backBBSList");
            }
        } else if (cmd.equals("moveThreadDtl")) {
            //スレッドタイトルクリック
            __setBbs070Form(req, bbsForm, GSConstBulletin.BBSCMDMODE_EDIT);
            forward = map.findForward("moveThreadDtl");
        } else if (cmd.equals("getImageFile")) {
            //画像ダウンロード
            forward = __doGetImageFile(map, bbsForm, req, res, con);
        } else if (cmd.equals("bbs060allRead")) {
            //全て既読にするボタンクリック
            forward = __doAllRead(map, bbsForm, req, res, con);
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
        Bbs060Form form,
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
        Bbs060ParamModel paramMdl = new Bbs060ParamModel();
        paramMdl.setParam(form);
        Bbs060Biz biz = new Bbs060Biz();
        CommonBiz cmnBiz = new CommonBiz();
        //プラグイン管理者
        boolean pluginAdmin = cmnBiz.isPluginAdmin(con, getSessionUserModel(req), getPluginId());

        biz.setInitData(paramMdl, con, userSid, pluginAdmin);
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

        return map.getInputForward();
    }

    /**
     * <br>[機  能] 検索ボタンクリック時処理
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
    private ActionForward __doSearch(ActionMapping map,
        Bbs060Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con
        )
        throws Exception {

        //入力チェック
        ActionErrors errors = form.validateCheck(req);
        if (!errors.isEmpty()) {
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        //ログインユーザSIDを取得
        int userSid = 0;
        BaseUserModel buMdl = getSessionUserModel(req);
        if (buMdl != null) {
            userSid = buMdl.getUsrsid();
        }


        //検索結果が存在するかを確認
        Bbs041Form form041 = new Bbs041Form();
        form041.setS_key(form.getS_key());
        form041.setBbs010forumSid(form.getBbs010forumSid());
        form041.setBbs010page1(form.getBbs010page1());
        form041.setBbs060page1(form.getBbs060page1());
        form041.setSearchDspID(GSConstBulletin.SERCHDSPID_THREADLIST);
        form041.setBbs040dateNoKbn(1);
        form041.setBbs040forumSid(form.getBbs010forumSid());
        form041.setBbs040keyKbn(GSConstBulletin.KEYWORDKBN_AND);
        form041.setBbs040readKbn(0);
        form041.setBbs040taisyouThread(1);
        form041.setBbs040taisyouNaiyou(1);

        Bbs041ParamModel paramMdl = new Bbs041ParamModel();
        paramMdl.setParam(form041);
        Bbs040Biz biz = new Bbs040Biz();
        con.setAutoCommit(true);
        int count = biz.getSearchCount(paramMdl, con, userSid, buMdl.isAdmin(), new UDate());
        paramMdl.setFormData(form041);
        con.setAutoCommit(false);
        if (count <= 0) {

            GsMessage gsMsg = new GsMessage();
            String textThread = gsMsg.getMessage(req, "bbs.2");

            ActionMessage msg = new ActionMessage("search.data.notfound", textThread);
            StrutsUtil.addMessage(errors, msg, "s_key");
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        req.setAttribute("bbs041Form", form041);
        return map.findForward("moveSearchList");
    }

    /**
     * <br>[機  能] スレッド作成画面へのフォームパラメータを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @param form アクションフォーム
     * @param cmdMode 処理モード
     */
    private void __setBbs070Form(HttpServletRequest req, Bbs060Form form, int cmdMode) {

        Bbs070Form form070 = new Bbs070Form();
        form070.setS_key(form.getS_key());
        form070.setBbs010page1(form.getBbs010page1());
        form070.setBbs010forumSid(form.getBbs010forumSid());
        form070.setBbs060page1(form.getBbs060page1());
        form070.setBbs070cmdMode(cmdMode);
        req.setAttribute("bbs070Form", form070);
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
                                            Bbs060Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        con.setAutoCommit(false);

        CommonBiz cmnBiz = new CommonBiz();
        CmnBinfModel cbMdl = null;
        //画像バイナリSIDとフォーラムSIDの照合チェック
        Bbs060Biz bbs060Biz = new Bbs060Biz();
        boolean icoHnt = bbs060Biz.cheIcoHnt(con,
                form.getBbs010forumSid(), form.getBbs010BinSid());

        if (!icoHnt) {
            return null;

        } else {
            cbMdl = cmnBiz.getBinInfo(con, form.getBbs010BinSid(),
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
     * <br>[機  能] 全て既読にするボタンクリック時処理
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
    private ActionForward __doAllRead(ActionMapping map,
        Bbs060Form form,
        HttpServletRequest req,
        HttpServletResponse res,
        Connection con)
        throws Exception {

        //表示フォーラム内のスレッドを全て既読にする
        boolean commit = false;
        try {
            Bbs060ParamModel paramMdl = new Bbs060ParamModel();
            paramMdl.setParam(form);
            Bbs060Biz biz = new Bbs060Biz();
            biz.allReadThread(con, paramMdl, getSessionUserSid(req));
            paramMdl.setFormData(form);
            con.commit();
            commit = true;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        //ログ出力処理
        RequestModel reqMdl = getRequestModel(req);
        BbsBiz bbsBiz = new BbsBiz(con);
        GsMessage gsMsg = new GsMessage(reqMdl);
        String opCode = gsMsg.getMessage("cmn.all.read");
        BulletinDspModel forumData = bbsBiz.getForumData(con, form.getBbs010forumSid());
        bbsBiz.outPutLog(map, reqMdl, opCode,
                GSConstLog.LEVEL_TRACE, "[title]" + forumData.getBfiName());

        return __doInit(map, form, req, res, con);
    }
}
