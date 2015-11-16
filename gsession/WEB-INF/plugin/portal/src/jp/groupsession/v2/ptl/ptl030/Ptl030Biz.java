package jp.groupsession.v2.ptl.ptl030;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.man.GSConstPortal;
import jp.groupsession.v2.man.dao.base.PtlPortalDao;
import jp.groupsession.v2.man.dao.base.PtlPortalSortDao;
import jp.groupsession.v2.man.model.base.PtlPortalModel;
import jp.groupsession.v2.ptl.ptl030.model.Ptl030Model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ポータル ポータル管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl030Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ptl030Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Ptl030ParamModel paramMdl, Connection con)
    throws SQLException {
        log__.debug("START");

        PtlPortalDao ptlDao = new PtlPortalDao(con);

        //ポータル一覧を取得する
        List<PtlPortalModel> portalList = ptlDao.getPortaList(GSConstPortal.PTS_KBN_COMMON);

        //表示用のポータル一覧を生成する。
        List<Ptl030Model> dspList = new ArrayList<Ptl030Model>();
        Ptl030Model dspModel = null;
        if (portalList != null && portalList.size() > 0) {
            for (PtlPortalModel model : portalList) {
                dspModel = new Ptl030Model();
                dspModel.setPtlDescriptionView(NullDefault.getString(
                        StringUtilHtml.transToHTmlPlusAmparsant(model.getPtlDescription()), ""));
                dspModel.setPtlName(model.getPtlName());
                dspModel.setPtlOpen(model.getPtlOpen());
                dspModel.setPtlSid(model.getPtlSid());
                dspList.add(dspModel);
            }
        }

        paramMdl.setPtl030portalList(dspList);

        log__.debug("End");
    }

    /**
     * <br>[機  能] ポータルの並び順を上へ移動する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void updateSortUp(Ptl030ParamModel paramMdl, Connection con)
    throws SQLException {

        PtlPortalDao ptlDao = new PtlPortalDao(con);
        PtlPortalSortDao ptlSortDao = new PtlPortalSortDao(con);

        //ポータル一覧を取得する。
        List<PtlPortalModel> portalList = ptlDao.getPortaList(GSConstPortal.PTS_KBN_COMMON);

        if (portalList == null || portalList.size() < 1) {
            return;
        }

        int selectPtlSid = paramMdl.getPtl030sortPortal();
        int selectPtsSort = -1;
        int beforePtlSid = -1;
        int beforePtsSort = -1;

        for (PtlPortalModel model : portalList) {

            if (model.getPtlSid() == selectPtlSid) {
                selectPtsSort = model.getPtsSort();
                break;
            }
            beforePtlSid = model.getPtlSid();
            beforePtsSort = model.getPtsSort();
        }

        if (beforePtlSid < 0) {
            return;
        }

        ptlSortDao.updateSortChange(
                beforePtlSid, beforePtsSort, selectPtlSid,
                selectPtsSort, GSConstPortal.PTS_KBN_COMMON);

    }

    /**
     * <br>[機  能] ポータルの並び順を下へ移動する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void updateSortDown(Ptl030ParamModel paramMdl, Connection con)
    throws SQLException {

        PtlPortalDao ptlDao = new PtlPortalDao(con);
        PtlPortalSortDao ptlSortDao = new PtlPortalSortDao(con);

        //ポータル一覧を取得する。
        List<PtlPortalModel> portalList = ptlDao.getPortaList(GSConstPortal.PTS_KBN_COMMON);

        if (portalList == null || portalList.size() < 1) {
            return;
        }

        int selectPtlSid = paramMdl.getPtl030sortPortal();
        int selectPtsSort = -1;
        int afterPtlSid = -1;
        int afterPtsSort = -1;
        boolean checkFlg = false;

        for (PtlPortalModel model : portalList) {

            if (checkFlg) {
                afterPtlSid = model.getPtlSid();
                afterPtsSort = model.getPtsSort();
                break;
            }

            if (model.getPtlSid() == selectPtlSid) {
                selectPtsSort = model.getPtsSort();
                checkFlg = true;
            }
        }

        if (afterPtlSid < 0) {
            return;
        }

        ptlSortDao.updateSortChange(
                selectPtlSid, selectPtsSort, afterPtlSid,
                afterPtsSort, GSConstPortal.PTS_KBN_COMMON);

    }
}
