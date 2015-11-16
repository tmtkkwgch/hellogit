package jp.groupsession.v2.fil;

import javax.servlet.http.HttpServletRequest;

import jp.groupsession.v2.cmn.cmn999.Cmn999Form;
import jp.groupsession.v2.struts.AbstractGsAction;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ファイル管理プラグインで共通的に使用するアクションクラスです
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public abstract class AbstractFileAction extends AbstractGsAction {

    /** プラグインID */
    private static final String PLUGIN_ID = "file";

    /**プラグインIDを取得します
     * @return String プラグインID
     * @see jp.groupsession.v2.struts.AbstractGsAction#getPluginId()
     */
    @Override
    public String getPluginId() {
        return PLUGIN_ID;
    }

    /**
     * <br>[機  能]設定ファイルの初期値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param appRootPath アプリケーションルートパス
     * @param paramMdl アクションフォーム
     *
     */
    public void getFileInitConfigData(String appRootPath , AbstractFileParamModel paramMdl) {

        if (FilCommonBiz.isUseAllTextSearch(appRootPath)) {
            paramMdl.setFileSearchFlg(GSConstFile.FIL_ALL_SEARCH_USE_YES);
        } else {
            paramMdl.setFileSearchFlg(GSConstFile.FIL_ALL_SEARCH_USE_NO);
        }

    }

    /**
     * <br>[機  能] キャビネット閲覧権限エラーメッセージ画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @return ActionForward アクションフォワード
     */
    public ActionForward getCanNotViewErrorPage(
        ActionMapping map, HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("cabinetMain");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "error.not.view.cabinet";

        cmn999Form.setMessage(msgRes.getMessage(msgState));

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] ファイルロックエラーメッセージ画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @return ActionForward アクションフォワード
     */
    public ActionForward getFileLockErrorPage(
        ActionMapping map, HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("gf_menu");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "error.file.lock";

        cmn999Form.setMessage(msgRes.getMessage(msgState));

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] ファイルロックエラーメッセージ画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @return ActionForward アクションフォワード
     */
    public ActionForward getFileUnlockErrorPage(
        ActionMapping map, HttpServletRequest req) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("gf_menu");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "error.file.unlock";

        cmn999Form.setMessage(msgRes.getMessage(msgState));

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     *
     * <br>[機  能]削除済みエラーメッセージ画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param targetName 削除された対象名
     * @return ActionForward アクションフォワード
     */
    public ActionForward getCanNotViewNonePowerErrorPage(
            ActionMapping map, HttpServletRequest req,
            String targetName) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;
        //エラー画面パラメータの設定
        MessageResources msgRes = getResources(req);
        cmn999Form.setType(Cmn999Form.TYPE_OK);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("cabinetMain");
        cmn999Form.setUrlOK(urlForward.getPath());

        cmn999Form.setMessage(msgRes.getMessage("error.not.exitst.myself.dir",
                targetName));

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] ディレクトリ閲覧権限エラーメッセージ画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param message エラーメッセージ
     * @return ActionForward アクションフォワード
     */
    public ActionForward getCanNotViewDirErrorPage(
        ActionMapping map, HttpServletRequest req, String message) {

        Cmn999Form cmn999Form = new Cmn999Form();
        ActionForward urlForward = null;

        cmn999Form.setType(Cmn999Form.TYPE_OK);
        MessageResources msgRes = getResources(req);
        cmn999Form.setIcon(Cmn999Form.ICON_WARN);
        cmn999Form.setWtarget(Cmn999Form.WTARGET_BODY);
        urlForward = map.findForward("cabinetMain");
        cmn999Form.setUrlOK(urlForward.getPath());

        //メッセージセット
        String msgState = "errors.free.msg";

        cmn999Form.setMessage(msgRes.getMessage(msgState, message));

        req.setAttribute("cmn999Form", cmn999Form);

        return map.findForward("gf_msg");
    }

    /**
     * <br>[機  能] 権限エラーメッセージ画面へ遷移する。
     * <br>[解  説]
     * <br>[備  考]
     * @param map マッピング
     * @param req リクエスト
     * @param powName 権限名称
     * @param actName 行動名称
     * @return ActionForward アクションフォワード
     */
    public ActionForward getPowNoneErrorPage(
            ActionMapping map, HttpServletRequest req, String powName,
            String actName) {
        MessageResources msgRes = getResources(req);

        String mes = msgRes.getMessage("error.edit.power.user",
                powName,
                actName);
        return getCanNotViewDirErrorPage(map, req, mes);
    }


}