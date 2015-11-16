package jp.groupsession.v2.api.ntp.nippou.edit;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.io.IOTools;
import jp.groupsession.v2.api.AbstractApiAction;
import jp.groupsession.v2.api.ntp.nippou.edit.model.ApiNippouEditModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GroupSession;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.ntp040.Ntp040Biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.jdom.Document;
/**
 * <br>[機  能] WEBAPI 日報編集アクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiNippouEditAction extends AbstractApiAction {
    /** ログ */
    private static Log log__ = LogFactory.getLog(new Throwable().getStackTrace()[0].getClassName());
    @Override
    public Document createXml(ActionForm form, HttpServletRequest req,
            HttpServletResponse res, Connection con, BaseUserModel umodel)
            throws Exception {
        log__.debug("createXml start");
        //日報プラグインアクセス権限確認
        if (!canAccsessSelectPlugin(GSConst.PLUGIN_ID_NIPPOU)) {
            addErrors(req, addCantAccsessPluginError(req, null, GSConst.PLUGIN_ID_NIPPOU));
            return null;
        }
        ApiNippouEditForm thisForm = (ApiNippouEditForm) form;
        ActionErrors err = thisForm.validateCheck(con, getRequestModel(req));
        if (!err.isEmpty()) {
            addErrors(req, err);
            return null;
        }
        //セッション情報を取得
        HttpSession session = req.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        //アプリケーションRoot
        String appRootPath = getAppRootPath();
        //プラグイン設定
        PluginConfig plconf = getPluginConfig(req);

        MlCountMtController cntCon = getCountMtController(req);
        ApiNippouEditBiz biz = new ApiNippouEditBiz(con, getRequestModel(req), cntCon);

        //テンポラリディレクトリパスを取得
        CommonBiz cmnBiz = new CommonBiz();
        String tempDir = cmnBiz.getTempDir(
                getTempPath(req), "nippou", getRequestModel(req));
        boolean commitFlg = false;
        con.setAutoCommit(false);

        Document doc = new Document();

        try {
        //行数分の登録処理を行う
            ApiNippouEditModel model = thisForm.getReport();
            String tempPath = getTempPath(req);



            //添付ファイルをテンプフォルダに展開
            int tempId = biz.initEditTempFile(0,
                    model.getNipSid(),
                    getAppRootPath(),
                    tempPath,
                    thisForm.getDelBinSidArr(),
                    GroupSession.getResourceManager().getDomain(req));

            FormFile formFile;
            formFile = thisForm.getTempFile1();
            if (formFile != null) {
                biz.insertNippouTempFile(tempId, formFile, tempPath);
            }
            formFile = thisForm.getTempFile2();
            if (formFile != null) {
                biz.insertNippouTempFile(tempId, formFile, tempPath);
            }
            formFile = thisForm.getTempFile3();
            if (formFile != null) {
                biz.insertNippouTempFile(tempId, formFile, tempPath);
            }
            formFile = thisForm.getTempFile4();
            if (formFile != null) {
                biz.insertNippouTempFile(tempId, formFile, tempPath);
            }
            formFile = thisForm.getTempFile5();
            if (formFile != null) {
                biz.insertNippouTempFile(tempId, formFile, tempPath);
            }
            //行ごとのテンポラリディレクトリ
            String ntpTempDir = tempDir + "row" + tempId;

            PluginConfig pconfig = getPluginConfigForMain(plconf, con);
            boolean smailPluginUseFlg =
                    cmnBiz.isCanUsePlugin(GSConstMain.PLUGIN_ID_SMAIL, pconfig);
            int nipSid = -1;
            //新規登録
            if (model.getCmd().equals("addNtp")) {
                //採番マスタから日報SIDを取得
                nipSid = biz.insertNippouDate(model, sessionUsrSid, appRootPath, plconf,
                        smailPluginUseFlg, ntpTempDir, getRequestModel(req));
            //編集
            }
            if (model.getCmd().equals("editNtp")) {
                int iniEdit = GSConstNippou.EDIT_CONF_NONE;

                nipSid =
                        biz.updateNippouDate(model,
                        sessionUsrSid,
                        appRootPath,
                        iniEdit,
                        ntpTempDir);
            }
            Ntp040Biz ntp040Biz = new Ntp040Biz(con, getRequestModel(req));
            ArrayList<Integer> ntpSids = new ArrayList<Integer>();
            ntpSids.add(new Integer(nipSid));
            //日報確認情報を更新(登録者を確認済にする)
            ntp040Biz.setCheck(ntpSids, sessionUsrSid);
            //登録者以外を未確認にする
            ntp040Biz.resetCheck(ntpSids, sessionUsrSid);

            commitFlg = true;
            doc.addContent(_createElement("NipSid", nipSid));

        } catch (Exception e) {
            commitFlg = false;
            log__.error("日報登録に失敗しました" + e);
            throw e;
        } finally {
            if (!commitFlg) {
                con.rollback();
            } else {
                con.commit();
                //テンポラリディレクトリのファイルを削除する
                IOTools.deleteDir(tempDir);
                log__.debug("テンポラリディレクトリのファイル削除");
            }
        }

        return doc;
    }

}
