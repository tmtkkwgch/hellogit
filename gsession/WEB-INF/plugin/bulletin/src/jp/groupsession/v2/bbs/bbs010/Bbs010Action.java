package jp.groupsession.v2.bbs.bbs010;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.http.TempFileUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.bbs.AbstractBulletinAction;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.bbs040.Bbs040Form;
import jp.groupsession.v2.bbs.bbs041.Bbs041Form;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
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
 * <br>[機  能] 掲示板 フォーラム一覧画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs010Action extends AbstractBulletinAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs010Action.class);

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
        Bbs010Form bbsForm = (Bbs010Form) form;

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);

        if (cmd.equals("prevPage")) {
            //前ページクリック
            bbsForm.setBbs010page1(bbsForm.getBbs010page1() - 1);
            forward = __doInit(map, bbsForm, req, res, con);
        } else if (cmd.equals("nextPage")) {
            //次ページクリック
            bbsForm.setBbs010page1(bbsForm.getBbs010page1() + 1);
            forward = __doInit(map, bbsForm, req, res, con);
        } else if (cmd.equals("confMenu")) {
            //管理者設定ボタンクリック
            forward = map.findForward("confMenu");
        } else if (cmd.equals("personal")) {
            //個人設定ボタンクリック
            forward = map.findForward("personal");
        } else if (cmd.equals("moveMemList")) {
            //参照ボタンクリック
            forward = map.findForward("memList");
        } else if (cmd.equals("search")) {
            //検索ボタンクリック
            forward = __doSearch(map, bbsForm, req, res, con);
        } else if (cmd.equals("searchDtl")) {
            //詳細検索ボタンクリック
            Bbs040Form form040 = new Bbs040Form();
            form040.setS_key(bbsForm.getS_key());
            form040.setBbs010page1(bbsForm.getBbs010page1());
            form040.setBbs040dateNoKbn(1);
            form040.setBbs040keyKbn(GSConstBulletin.KEYWORDKBN_AND);
            form040.setBbs040readKbn(0);
            form040.setBbs040taisyouThread(1);
            form040.setBbs040taisyouNaiyou(1);
            form040.setSearchDspID(GSConstBulletin.SERCHDSPID_FORUMDTL);
            req.setAttribute("bbs040Form", form040);
            forward = map.findForward("moveSearchDtl");
        } else if (cmd.equals("moveThreadList")) {
            //フォーラムタイトルクリック
            forward = map.findForward("moveThreadList");
        } else if (cmd.equals("moveRsvThreadList")) {
            //掲示予定リンククリック
            forward = map.findForward("rsvThreadList");
        } else if (cmd.equals("getImageFile")) {
            //画像ダウンロード"
            forward = __doGetImageFile(map, bbsForm, req, res, con);
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
        Bbs010Form form,
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
        Bbs010ParamModel paramMdl = new Bbs010ParamModel();
        paramMdl.setParam(form);
        Bbs010Biz biz = new Bbs010Biz();
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstBulletin.PLUGIN_ID_BULLETIN);

        biz.setInitData(paramMdl, con, userSid, adminUser);
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
        Bbs010Form form,
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

        con.setAutoCommit(true);
        Bbs010Biz biz = new Bbs010Biz();
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstBulletin.PLUGIN_ID_BULLETIN);

        Bbs010ParamModel paramMdl = new Bbs010ParamModel();
        paramMdl.setParam(form);
        int count = biz.getSearchCount(paramMdl, con, userSid, adminUser);
        paramMdl.setFormData(form);
        con.setAutoCommit(false);

        if (count == 0) {

            GsMessage gsMsg = new GsMessage();
            String textThread = gsMsg.getMessage(req, "bbs.2");

            ActionMessage msg = new ActionMessage("search.data.notfound", textThread);
            StrutsUtil.addMessage(errors, msg, "s_key");
            addErrors(req, errors);
            return __doInit(map, form, req, res, con);
        }

        Bbs041Form form041 = new Bbs041Form();
        form041.setS_key(form.getS_key());
        form041.setBbs010forumSid(form.getBbs010forumSid());
        form041.setBbs010page1(form.getBbs010page1());
        form041.setSearchDspID(GSConstBulletin.SERCHDSPID_FORUMLIST);
        form041.setBbs040dateNoKbn(1);
        form041.setBbs040keyKbn(GSConstBulletin.KEYWORDKBN_AND);
        form041.setBbs040readKbn(0);
        form041.setBbs040taisyouThread(1);
        form041.setBbs040taisyouNaiyou(1);

        req.setAttribute("bbs041Form", form041);
        return map.findForward("moveSearchList");
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
                                            Bbs010Form form,
                                            HttpServletRequest req,
                                            HttpServletResponse res,
                                            Connection con)
        throws Exception {

        CommonBiz cmnBiz = new CommonBiz();
        CmnBinfModel cbMdl = null;
        BaseUserModel buMdl = getSessionUserModel(req);

        //画像バイナリSIDとフォーラムSIDの照合チェック
        Bbs010Biz bbs010Biz = new Bbs010Biz();
        boolean icoHnt = bbs010Biz.cheIcoHnt(con, buMdl.getUsrsid(),
                form.getBbs010ForSid(), form.getBbs010BinSid());

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
}

