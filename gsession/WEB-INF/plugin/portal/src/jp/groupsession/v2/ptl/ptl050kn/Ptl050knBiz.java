package jp.groupsession.v2.ptl.ptl050kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.man.GSConstPortal;
import jp.groupsession.v2.man.dao.base.PtlPortalDao;
import jp.groupsession.v2.man.dao.base.PtlPortalLayoutDao;
import jp.groupsession.v2.man.dao.base.PtlPortalSortDao;
import jp.groupsession.v2.man.model.base.PtlPortalLayoutModel;
import jp.groupsession.v2.man.model.base.PtlPortalModel;
import jp.groupsession.v2.man.model.base.PtlPortalSortModel;
import jp.groupsession.v2.ptl.PtlCommonBiz;
import jp.groupsession.v2.ptl.dao.PtlPortalConfReadDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ポータル ポータル登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl050knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ptl050knBiz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Ptl050knParamModel paramMdl, Connection con)
    throws SQLException {
        log__.debug("START");

        //説明（表示用）
        paramMdl.setPtl050knviewDescription(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getPtl050description()), ""));

        //閲覧メンバリストを設定する
        paramMdl.setPtl050knMemNameList(__getForumFullLabel(paramMdl, con));

        log__.debug("End");
    }

    /**
     * <br>[機  能] ポータル情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @throws SQLException SQL実行例外
     */
    public void insertData(Ptl050knParamModel paramMdl,
                        Connection con,
                        MlCountMtController cntCon,
                        int userSid)
    throws SQLException {

        PtlPortalDao ptlDao = new PtlPortalDao(con);
        PtlPortalSortDao ptlSortDao = new PtlPortalSortDao(con);
        PtlPortalLayoutDao ptlLayoutDao = new PtlPortalLayoutDao(con);
        PtlPortalConfReadDao ptlConfReadDao = new PtlPortalConfReadDao(con);
        PtlCommonBiz ptlBiz = new PtlCommonBiz(con);

        UDate now = new UDate();

        //ポータルSID採番
        int ptlSid = (int) cntCon.getSaibanNumber(GSConstPortal.SBNSID_SUB_PORTAL,
                                                  GSConstPortal.SBNSID_SUB_PORTAL,
                                                  userSid);

        //ポータル情報の登録
        PtlPortalModel ptlModel = new PtlPortalModel();
        ptlModel.setPtlSid(ptlSid);
        ptlModel.setPtlName(NullDefault.getString(paramMdl.getPtl050name(), ""));
        ptlModel.setPtlOpen(paramMdl.getPtl050openKbn());
        ptlModel.setPtlDescription(NullDefault.getString(paramMdl.getPtl050description(), ""));
        ptlModel.setPtlAccess(paramMdl.getPtl050accessKbn());
        ptlModel.setPtlAuid(userSid);
        ptlModel.setPtlAdate(now);
        ptlModel.setPtlEuid(userSid);
        ptlModel.setPtlEdate(now);
        ptlDao.insert(ptlModel);


        //並び順の最大値を取得する。
        int maxSort = ptlSortDao.getMaxSort(GSConstPortal.PTS_KBN_COMMON);

        //ポータル並び順の登録
        PtlPortalSortModel ptlSortModel = new PtlPortalSortModel();
        ptlSortModel.setPtlSid(ptlSid);
        ptlSortModel.setPtsKbn(GSConstPortal.PTS_KBN_COMMON);
        ptlSortModel.setPtsSort(maxSort + 1);
        ptlSortModel.setUsrSid(0);
        ptlSortDao.insert(ptlSortModel);


        //ポータルレイアウトの登録
        PtlPortalLayoutModel ptlLayoutModel = new PtlPortalLayoutModel();
        ptlLayoutModel.setPtlSid(ptlSid);
        ptlLayoutModel.setPtsView(GSConstPortal.PTL_OPENKBN_OK);
        ptlLayoutModel.setPlyAuid(userSid);
        ptlLayoutModel.setPlyAdate(now);
        ptlLayoutModel.setPlyEuid(userSid);
        ptlLayoutModel.setPlyEdate(now);

        List<Integer> positionList = new ArrayList<Integer>();
        positionList.add(GSConstPortal.LAYOUT_POSITION_TOP);
        positionList.add(GSConstPortal.LAYOUT_POSITION_BOTTOM);
        positionList.add(GSConstPortal.LAYOUT_POSITION_LEFT);
        positionList.add(GSConstPortal.LAYOUT_POSITION_CENTER);
        positionList.add(GSConstPortal.LAYOUT_POSITION_RIGHT);

        for (Integer position : positionList) {
            ptlLayoutModel.setPlyPosition(position);
            ptlLayoutDao.insert(ptlLayoutModel);
        }

        //ポータル閲覧設定の登録
        if (paramMdl.getPtl050accessKbn() == GSConstPortal.PTL_ACCESS_ON) {

            String[] memberSids = paramMdl.getPtl050memberSid();
            if (memberSids != null && memberSids.length > 0) {
                ptlConfReadDao.insert(ptlSid, memberSids);
            }
        }

        //インフォメーションの登録
        int ptpSort = 1;
        ptlBiz.insertInfomation(ptlSid, userSid, GSConstPortal.LAYOUT_POSITION_TOP, ptpSort);
    }

    /**
     * <br>[機  能] ポータル情報の更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @param cntCon MlCountMtController
     * @throws SQLException SQL実行例外
     */
    public void updateData(Ptl050knParamModel paramMdl,
                        Connection con,
                        MlCountMtController cntCon,
                        int userSid)
    throws SQLException {

        PtlPortalDao ptlDao = new PtlPortalDao(con);
        PtlPortalConfReadDao ptlConfReadDao = new PtlPortalConfReadDao(con);

        UDate now = new UDate();

        //ポータルSID採番
        int ptlSid = paramMdl.getPtlPortalSid();

        //ポータル情報の登録
        PtlPortalModel ptlModel = new PtlPortalModel();
        ptlModel.setPtlSid(ptlSid);
        ptlModel.setPtlName(NullDefault.getString(paramMdl.getPtl050name(), ""));
        ptlModel.setPtlOpen(paramMdl.getPtl050openKbn());
        ptlModel.setPtlDescription(NullDefault.getString(paramMdl.getPtl050description(), ""));
        ptlModel.setPtlAccess(paramMdl.getPtl050accessKbn());
        ptlModel.setPtlEuid(userSid);
        ptlModel.setPtlEdate(now);
        ptlDao.update(ptlModel);

        //ポータル閲覧設定の削除
        ptlConfReadDao.delete(ptlSid);

        //ポータル閲覧設定の登録
        if (paramMdl.getPtl050accessKbn() == GSConstPortal.PTL_ACCESS_ON) {

            String[] memberSids = paramMdl.getPtl050memberSid();
            if (memberSids != null && memberSids.length > 0) {
                ptlConfReadDao.insert(ptlSid, memberSids);
            }
        }

    }

    /**
     * <br>[機  能] 閲覧メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getForumFullLabel(Ptl050knParamModel paramMdl,
                                                        Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getPtl050memberSid();
        return __getForumLabel(leftFull, con);
    }

    /**
     * <br>[機  能] 閲覧メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getForumLabel(String[] left, Connection con)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

        //
        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");
                log__.debug("str==>" + str);
                log__.debug("G.index==>" + str.indexOf("G"));
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    grpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    usrSids.add(str);
                }
            }
        }

        LabelValueBean lavelBean = null;
        if (grpSids.size() > 0) {
            //グループ情報取得
            UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
            ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
            for (GroupModel gmodel : glist) {
                lavelBean = new LabelValueBean();
                lavelBean.setLabel(gmodel.getGroupName());
                lavelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
                ret.add(lavelBean);
            }
        }

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            lavelBean.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(lavelBean);
        }

        return ret;
    }
}