package jp.groupsession.v2.bbs.bbs041;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.bbs.AbstractBulletinAction;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <br>[機  能] 掲示板検索結果一覧画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs041Action extends AbstractBulletinAction {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs041Action.class);

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
        Bbs041Form bbsForm = (Bbs041Form) form;

        //コマンド
        String cmd = NullDefault.getString(req.getParameter("CMD"), "");
        log__.debug("CMD= " + cmd);

        if (cmd.equals("prevPage")) {
            //前ページクリック
            bbsForm.setBbs041page1(bbsForm.getBbs041page1() - 1);
            forward = __doInit(map, bbsForm, req, res, con);
        } else if (cmd.equals("nextPage")) {
            //次ページクリック
            bbsForm.setBbs041page1(bbsForm.getBbs041page1() + 1);
            forward = __doInit(map, bbsForm, req, res, con);
        } else if (cmd.equals("moveResult")) {
            //検索結果リンククリック
            forward = map.findForward("moveWriteList");
        } else if (cmd.equals("backPage")) {
            //戻るボタンクリック
            String searchDsp = NullDefault.getString(bbsForm.getSearchDspID(), "");
            if (searchDsp.equals(GSConstBulletin.SERCHDSPID_FORUMLIST)) {
                forward = map.findForward("backBBSList");
            } else if (searchDsp.equals(GSConstBulletin.SERCHDSPID_THREADLIST)) {
                forward = map.findForward("moveThreadList");
            } else {
                forward = map.findForward("moveSearchDtl");
            }
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
        Bbs041Form form,
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
        Bbs041ParamModel paramMdl = new Bbs041ParamModel();
        paramMdl.setParam(form);
        Bbs041Biz biz = new Bbs041Biz();
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstBulletin.PLUGIN_ID_BULLETIN);

        biz.setInitData(paramMdl, con, userSid, adminUser, getRequestModel(req));
        paramMdl.setFormData(form);

        PluginConfig pconfig
            = getPluginConfigForMain(getPluginConfig(req), con, getSessionUserSid(req));
        form.setBbs041searchUse(CommonBiz.getWebSearchUse(pconfig));
        con.setAutoCommit(false);

        return map.getInputForward();
    }

}

