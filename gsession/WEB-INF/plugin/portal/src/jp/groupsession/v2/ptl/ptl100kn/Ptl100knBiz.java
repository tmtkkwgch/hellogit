package jp.groupsession.v2.ptl.ptl100kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.man.GSConstPortal;
import jp.groupsession.v2.ptl.dao.PtlPortletCategoryDao;
import jp.groupsession.v2.ptl.dao.PtlPortletDao;
import jp.groupsession.v2.ptl.dao.PtlPortletSortDao;
import jp.groupsession.v2.ptl.model.PtlPortletModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ポータル ポートレット登録確認画面のアクションクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl100knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ptl100knBiz.class);
    /** Connection */
    private Connection con__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     */
    Ptl100knBiz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param appRootPath アプリケーションルートパス
     * @throws Exception 実行時例外
     */
    public void initDsp(
            Ptl100knParamModel paramMdl,
            RequestModel reqMdl,
            String appRootPath) throws Exception {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String msg = gsMsg.getMessage("cmn.notset");

        //カテゴリ名を設定
        PtlPortletCategoryDao dao = new PtlPortletCategoryDao(con__);
        paramMdl.setPtl100knCategoryName(NullDefault.getString(
                dao.select(paramMdl.getPtl100category()).getPlcName(), msg));

        if (paramMdl.getPtl100contentType() == Ptl100knForm.PTL100_CONTENTTYPE_URL) {
            paramMdl.setPtl100knContentUrl(__createContentUrl(paramMdl));
        }

        //説明を表示用に整形
        paramMdl.setPtl100knDescription(
            StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPtl100description()));

        if (!StringUtil.isNullZeroString(paramMdl.getPtl100content())) {
            //javascriptの場合(chromeキャッシュ対策)
            String content = paramMdl.getPtl100content();
            String ran = String.valueOf(Math.round(Math.random() * 100));
            if (content.indexOf(".js") != -1) {
                content = content.replace(".js", ".js?" + ran);
            }
            if (content.indexOf("?") != -1) {
                content = content.replace("?", "?" + ran + "&");
            }
            //URLが含まれていた場合(chromeキャッシュ対策)
            content = StringUtil.getLinkAddPrm(content);
            paramMdl.setPtl100knContent(content);
        }

        if (!StringUtil.isNullZeroString(paramMdl.getPtl100contentHtml())) {
            //javascriptの場合(chromeキャッシュ対策)
            String content = paramMdl.getPtl100contentHtml();

            //HTML入力の場合、GS予約語が含まれていた場合、置換する
            if (paramMdl.getPtl100contentType() == Ptl100knForm.PTL100_CONTENTTYPE_HTML) {
                CmnUsrmInfDao usrInfDao = new CmnUsrmInfDao(con__);
                CmnUsrmInfModel usrInfMdl = usrInfDao.select(reqMdl.getSmodel().getUsrsid());
                CommonBiz cmnBiz = new CommonBiz();
                content = cmnBiz.replaceGSReservedWordNoTime(
                        reqMdl, con__, appRootPath, content, usrInfMdl, true);
            }

            String ran = String.valueOf(Math.round(Math.random() * 100));
            if (content.indexOf(".js") != -1) {
                content = content.replace(".js", ".js?" + ran);
            }
            if (content.indexOf("?") != -1) {
                content = content.replace("?", "?" + ran + "&");
            }
            //URLが含まれていた場合(chromeキャッシュ対策)
            content = StringUtil.getLinkAddPrm(content);
            paramMdl.setPtl100knContentHtml(content);
        }
    }

    /**
     * <br>[機  能] ポートレットの登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param controller MlCountMtController
     * @param userSid ログインユーザSID
     * @param mode 処理モード
     * @throws Exception 実行時例外
     */
    public void registPtl(
            Ptl100knParamModel paramMdl,
            MlCountMtController controller,
            int userSid,
            int mode) throws Exception {

        //ポートレットモデルを作成
        PtlPortletModel pltMdl = __createPltModel(paramMdl, controller, userSid, mode);

        //ポートレットを登録or更新
        if (mode == GSConstPortal.CMD_MODE_ADD) {
            //登録
            __insert(pltMdl);
            log__.debug("// ポートレットを登録しました。");
        }

        if (mode == GSConstPortal.CMD_MODE_EDIT) {
            //更新
            __update(pltMdl, paramMdl);
            log__.debug("// ポートレットを更新しました。");
        }

    }

    /**
     * <br>[機  能] ポートレットモデルを作成
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param controller MlCountMtController
     * @param userSid ログインユーザSID
     * @param mode 処理モード
     * @return PtlPortletModel
     * @throws Exception Exception
     */
    private PtlPortletModel __createPltModel(
            Ptl100knParamModel paramMdl,
            MlCountMtController controller,
            int userSid,
            int mode) throws Exception {

        log__.debug("ポートレットモデルを作成します。");
        PtlPortletModel mdl = new PtlPortletModel();

        int pltSid;

        //もし処理モードが追加なら、
        if (paramMdl.getPtlCmdMode() == GSConstPortal.CMD_MODE_ADD) {
            log__.debug("//採番マスタからポートレットSIDを取得。");
            pltSid = (int) controller.getSaibanNumber(GSConstPortal.PLUGIN_ID,
                                                      GSConstPortal.SBNSID_SUB_PORTLET,
                                                      userSid);
        } else {
            log__.debug("//現在選択されているポートレットのSIDを取得。");
            pltSid = paramMdl.getPtlPortletSid();
        }

        UDate now = new UDate();


        mdl.setPltSid(pltSid);
        mdl.setPltName(NullDefault.getString(paramMdl.getPtl100name(), ""));

        if (paramMdl.getPtl100contentType() == Ptl100knForm.PTL100_CONTENTTYPE_URL) {
            mdl.setPltContent(__createContentUrl(paramMdl));
            mdl.setPltContentType(Ptl100knForm.PTL100_CONTENTTYPE_URL);
            mdl.setPltContentUrl(paramMdl.getPtl100contentUrl());
        } else if (paramMdl.getPtl100contentType() == Ptl100knForm.PTL100_CONTENTTYPE_INPUT) {
            mdl.setPltContent(paramMdl.getPtl100content());
            mdl.setPltContentType(Ptl100knForm.PTL100_CONTENTTYPE_INPUT);
        } else {
            mdl.setPltContent(paramMdl.getPtl100contentHtml());
            mdl.setPltContentType(Ptl100knForm.PTL100_CONTENTTYPE_HTML);
        }
        mdl.setPltDescription(paramMdl.getPtl100description());
        mdl.setPlcSid(paramMdl.getPtl100category());
        mdl.setPltBorder(paramMdl.getPtl100border());
        mdl.setPltAuid(userSid);
        mdl.setPltAdate(now);
        mdl.setPltEuid(userSid);
        mdl.setPltEdate(now);
        return mdl;
    }

    /**
     * <br>[機  能] 登録処理
     * <br>[解  説]
     * <br>[備  考]
     * @param model モデル
     * @throws SQLException SQL実行時例外
     */
    private void __insert(PtlPortletModel model)
    throws SQLException {

        //ポートレット情報と並び順登録
        PtlPortletDao dao = new PtlPortletDao(con__);
        PtlPortletSortDao sortDao = new PtlPortletSortDao(con__);
        dao.insert(model);
        sortDao.insertMaxSort(model.getPltSid());

    }

    /**
     * <br>[機  能] 更新処理
     * <br>[解  説]
     * <br>[備  考] カテゴリ間の移動は更新処理でしか起こり得ないので
     *              元の所属カテゴリSIDと編集後の所属カテゴリSIDを比
     *              べて、カテゴリ間の移動があるか判定している。<br>
     *              カテゴリ間の移動がない場合は並び順の更新はしない。
     * @param model モデル
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    private void __update(PtlPortletModel model, Ptl100knParamModel paramMdl)
    throws SQLException {

        PtlPortletDao dao = new PtlPortletDao(con__);
        PtlPortletSortDao sortDao = new PtlPortletSortDao(con__);

        int motoCatSid = dao.select(paramMdl.getPtlPortletSid()).getPlcSid();
        int updateCatSid = paramMdl.getPtl100category();

        //ポートレット情報登録
        dao.update(model);

        //カテゴリ間の移動がある場合に、並び順更新
        if (motoCatSid != updateCatSid) {
            //並び順削除後追加
            sortDao.delete(model.getPltSid());
            sortDao.insertMaxSort(model.getPltSid());
        }

    }

    /**
     * <br>[機  能] 内容 URLのHTMLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @return HTML
     */
    private String __createContentUrl(Ptl100knParamModel paramMdl) {

        String content = "<iframe border=\"0\" frameborder=\"0\" scrolling=\"yes\"";
        content += " width=\"100%\" height=\"600\"";
        content += " src=\"" + paramMdl.getPtl100contentUrl() + "\"></iframe>";

        return content;
    }
}
