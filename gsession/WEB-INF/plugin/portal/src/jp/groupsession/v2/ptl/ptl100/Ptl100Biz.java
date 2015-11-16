package jp.groupsession.v2.ptl.ptl100;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.man.GSConstPortal;
import jp.groupsession.v2.man.dao.base.PtlPortalPositionDao;
import jp.groupsession.v2.ptl.dao.PtlPortletCategoryDao;
import jp.groupsession.v2.ptl.dao.PtlPortletDao;
import jp.groupsession.v2.ptl.dao.PtlPortletImageDao;
import jp.groupsession.v2.ptl.dao.PtlPortletSortDao;
import jp.groupsession.v2.ptl.model.PtlPortletCategoryModel;
import jp.groupsession.v2.ptl.model.PtlPortletImageModel;
import jp.groupsession.v2.ptl.model.PtlPortletModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ポータル ポートレット登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl100Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ptl100Biz.class);

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @throws Exception 例外
     */
    public void setInitData(Ptl100ParamModel paramMdl,
                            Connection con,
                            RequestModel reqMdl) throws Exception {

        if (paramMdl.getPtl100init() != 1) {
            log__.debug("初期表示");

            //編集の場合、データ取得
            if (paramMdl.getPtlCmdMode() == GSConstPortal.CMD_MODE_EDIT) {
                PtlPortletDao dao = new PtlPortletDao(con);
                PtlPortletModel model = dao.select(paramMdl.getPtlPortletSid());
                paramMdl.setPtl100name(model.getPltName());
                paramMdl.setPtl100contentType(model.getPltContentType());
                if (model.getPltContentType() == Ptl100Form.PTL100_CONTENTTYPE_HTML) {
                    paramMdl.setPtl100contentHtml(model.getPltContent());
                } else {
                    paramMdl.setPtl100content(model.getPltContent());
                }
                paramMdl.setPtl100border(model.getPltBorder());
                paramMdl.setPtl100category(model.getPlcSid());
                paramMdl.setPtl100contentUrl(model.getPltContentUrl());
                paramMdl.setPtl100description(model.getPltDescription());

            } else if (paramMdl.getPtl090svCategory() > 0) {
                paramMdl.setPtl100category(paramMdl.getPtl090svCategory());
            }

            paramMdl.setPtl100init(1);
        }

        //画像追加がある場合は内容の最後に追加する。
        String sltImage = paramMdl.getPtl100contentPlusImage();
        if (!StringUtil.isNullZeroString(sltImage)) {

            if (paramMdl.getPtl100contentType() == Ptl100Form.PTL100_CONTENTTYPE_HTML) {
                paramMdl.setPtl100contentHtml(paramMdl.getPtl100contentHtml() + sltImage);
            } else {
                paramMdl.setPtl100content(paramMdl.getPtl100content() + sltImage);
            }
            paramMdl.setPtl100contentPlusImage(null);
        }

        //カテゴリコンボ作成
        createCatComb(paramMdl, con, reqMdl);

        //編集、かつ内容 種別 != URLを指定 の場合、画像の一覧を取得する
        if (paramMdl.getPtlCmdMode() == GSConstPortal.CMD_MODE_EDIT
        && paramMdl.getPtl100contentType() != Ptl100Form.PTL100_CONTENTTYPE_URL) {
            int portletSid = paramMdl.getPtlPortletSid();

            //画像一覧を取得する。
            PtlPortletImageDao pltImageDao = new PtlPortletImageDao(con);
            List<PtlPortletImageModel> dspList = pltImageDao.getImageList(portletSid);
            paramMdl.setPtl100ImageList(dspList);
        }

    }

    /**
     * <br>[機  能] ポートレットカテゴリコンボボックスを作る
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     */
    public void createCatComb(Ptl100ParamModel paramMdl, Connection con,
                              RequestModel reqMdl) throws SQLException {

        PtlPortletCategoryDao dao = new PtlPortletCategoryDao(con);
        ArrayList<LabelValueBean> list = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl);
        String misettei = gsMsg.getMessage("cmn.notset");

        List<PtlPortletCategoryModel> mdlList = dao.selectSortAll();
        //初期値 全て
        list.add(new LabelValueBean(misettei, "0"));
        for (PtlPortletCategoryModel model : mdlList) {
            String strName = model.getPlcName();
            String strSid = Integer.toString(model.getPlcSid());
            list.add(new LabelValueBean(strName, strSid));
        }
        paramMdl.setPtl100CategoryList(list);
    }

    /**
     * <br>[機  能] ポートレットの削除を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param pltSid ポートレットSID
     * @param con Connection
     * @throws Exception 例外
     */
    public void deletePortlet(Ptl100ParamModel paramMdl,
                             int pltSid, Connection con) throws Exception {

        PtlPortletDao dao = new PtlPortletDao(con);
        PtlPortletSortDao sortDao = new PtlPortletSortDao(con);
        PtlPortalPositionDao positionDao = new PtlPortalPositionDao(con);

        //ソート順の更新を行う
        sortDao.delete(pltSid);
        //ポートレットの削除
        dao.delete(pltSid);
        //ポートレット位置情報の削除
        positionDao.deletePortlet(pltSid);

        //ポートレット画像の削除
        deleteImage(paramMdl, con, pltSid);
    }

    /**
     * <br>[機  能] ポートレットSIDからポートレット名を取得し、メッセージを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param ptlSid ポートレットSID
     * @param msgRes MessageResources
     * @param reqMdl リクエスト情報
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public String getDeletePltMsg(Connection con, int ptlSid,
                                  MessageResources msgRes, RequestModel reqMdl)
    throws SQLException {

        String msg = "";
        GsMessage gsMsg = new GsMessage(reqMdl);
        String portlet = gsMsg.getMessage("ptl.3");

        //ポートレット名取得
        PtlPortletDao dao = new PtlPortletDao(con);
        PtlPortletModel mdl = dao.select(ptlSid);
        String catName = StringUtilHtml.transToHTmlPlusAmparsant(
                         NullDefault.getString(mdl.getPltName(), ""));

        msg = msgRes.getMessage("sakujo.kakunin.list", portlet, catName);

        return msg;
    }

    /**
     * <br>[機  能] 画像情報を削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param usrSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void deleteImage(Ptl100ParamModel paramMdl,
                            Connection con, int usrSid) throws SQLException {
        int pltSid = paramMdl.getPtlPortletSid();
        PtlPortletImageDao imageDao = new PtlPortletImageDao(con);
        imageDao.updateImgFlg(pltSid);
    }
}
