package jp.groupsession.v2.ptl.ptl050;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.man.GSConstPortal;
import jp.groupsession.v2.man.dao.base.PtlPortalDao;
import jp.groupsession.v2.man.dao.base.PtlPortalLayoutDao;
import jp.groupsession.v2.man.dao.base.PtlPortalPositionDao;
import jp.groupsession.v2.man.dao.base.PtlPortalPositionParamDao;
import jp.groupsession.v2.man.dao.base.PtlPortalSortDao;
import jp.groupsession.v2.man.model.base.PtlPortalModel;
import jp.groupsession.v2.ptl.dao.PtlPortalConfReadDao;
import jp.groupsession.v2.ptl.model.PtlPortalConfReadModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ポータル ポータル登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ptl050Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ptl050Biz.class);

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Ptl050Biz() {
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Connection con, Ptl050ParamModel paramMdl, RequestModel reqMdl)
    throws SQLException {

        UserBiz userBiz = new UserBiz();

        if (paramMdl.getPtl050init() == 0) {
            //初期表示処理

            if (paramMdl.getPtlPortalSid() > 0) {
                //編集モード時の初期値設定
                setEditInitData(paramMdl, con);
            }

            paramMdl.setPtl050init(1);
        }


        //グループコンボ設定
        paramMdl.setPtl050GroupList(__getGroupLabelList(con, reqMdl, true));


        //グループコンボ選択値
        int forumSltGp = paramMdl.getPtl050accessKbnGroup();
        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();

        if (forumSltGp == Integer.parseInt(GSConstPortal.GROUP_COMBO_VALUE)) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);

            //追加ユーザを取得する
            ArrayList<String> usrSids = new ArrayList<String>();
            for (String str : paramMdl.getPtl050memberSid()) {
                usrSids.add(str);
            }

            for (GroupModel bean : allGpList) {
                if (usrSids.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    continue;
                }

                labelListAdd.add(new LabelValueBean(
                        bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
            }

        } else {

            //追加ユーザを取得する
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            for (String str : paramMdl.getPtl050memberSid()) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }

            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, paramMdl.getPtl050accessKbnGroup(), usrSids);

            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                 String.valueOf(cuiMdl.getUsrSid())));
            }
        }

        paramMdl.setPtl050RightUserList(labelListAdd);

        //閲覧可能ユーザ一覧
        paramMdl.setPtl050LeftUserList(__getForumLabel(paramMdl, con));

    }

    /**
     * <br>[機  能] 表示グループ用のグループリストを取得する(全て)
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param addGrpListFlg true:グループ一覧を含める false:含めない
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private ArrayList <LabelValueBean> __getGroupLabelList(
            Connection con, RequestModel reqMdl, boolean addGrpListFlg)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textGroupList = gsMsg.getMessage("cmn.grouplist");

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        LabelValueBean lavelBean = new LabelValueBean();
        if (addGrpListFlg) {
            lavelBean.setLabel(textGroupList);
            lavelBean.setValue(GSConstPortal.GROUP_COMBO_VALUE);
            labelList.add(lavelBean);
        }

        //グループリスト取得
        GroupBiz gBiz = new GroupBiz();
        ArrayList <GroupModel> gpList = gBiz.getGroupList(con);

        GroupModel gpMdl = null;
        for (int i = 0; i < gpList.size(); i++) {
            gpMdl = gpList.get(i);
            labelList.add(
                    new LabelValueBean(gpMdl.getGroupName(), String.valueOf(gpMdl.getGroupSid())));
        }
        log__.debug("labelList.size()=>" + labelList.size());
        return labelList;
    }

    /**
     * <br>[機  能] メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getForumLabel(Ptl050ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getPtl050memberSid();
        return __getMemberLabel(leftFull, con);
    }

    /**
     * <br>[機  能] メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getMemberLabel(String[] left, Connection con)
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


    /**
     * <br>[機  能] ポータルを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    public void deletePortal(Ptl050ParamModel paramMdl, Connection con)
    throws SQLException {

        PtlPortalDao ptlDao = new PtlPortalDao(con);
        PtlPortalSortDao ptlSortDao = new PtlPortalSortDao(con);
        PtlPortalLayoutDao ptlLayoutDao = new PtlPortalLayoutDao(con);
        PtlPortalConfReadDao ptlConfReadDao = new PtlPortalConfReadDao(con);
        PtlPortalPositionDao ptlPositionDao = new PtlPortalPositionDao(con);
        PtlPortalPositionParamDao ptlPositionParamDao = new PtlPortalPositionParamDao(con);

        int ptlSid = paramMdl.getPtlPortalSid();
        if (ptlSid < 1) {
            return;
        }

        //ポータル閲覧設定の削除
        ptlConfReadDao.delete(ptlSid);

        //ポータル表示順の削除
        ptlSortDao.delete(ptlSid);

        //ポータル位置設定の削除
        ptlPositionDao.delete(ptlSid);

        //ポータル位置設定の削除
        ptlPositionParamDao.delete(ptlSid);

        //ポータルレイアウトの削除
        ptlLayoutDao.delete(ptlSid);

        //ポータルの削除を行う。
        ptlDao.delete(ptlSid);

    }

    /**
     * <br>[機  能] 編集時の初期値を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行時例外
     */
    public void setEditInitData(Ptl050ParamModel paramMdl, Connection con)
    throws SQLException {

        PtlPortalDao ptlDao = new PtlPortalDao(con);
        PtlPortalConfReadDao ptlConfReadDao = new PtlPortalConfReadDao(con);
        int ptlSid = paramMdl.getPtlPortalSid();

        //ポータル情報を取得する。
        PtlPortalModel ptlModel = ptlDao.select(ptlSid);

        if (ptlModel == null) {
            return;
        }

        paramMdl.setPtl050name(ptlModel.getPtlName());
        paramMdl.setPtl050openKbn(ptlModel.getPtlOpen());
        paramMdl.setPtl050description(ptlModel.getPtlDescription());
        paramMdl.setPtl050accessKbn(ptlModel.getPtlAccess());

        if (ptlModel.getPtlAccess() == GSConstPortal.PTL_ACCESS_ON) {
            //閲覧グループ・閲覧ユーザ一覧を取得
            List<PtlPortalConfReadModel> readList = ptlConfReadDao.select(ptlSid);

            if (readList != null && readList.size() > 0) {
                //閲覧メンバーを設定
                ArrayList<String> memberList = new ArrayList<String>();

                for (PtlPortalConfReadModel model : readList) {
                    if (model.getUsrSid() != -1) {
                        memberList.add(String.valueOf(model.getUsrSid()));
                    }
                    if (model.getGrpSid() != -1) {
                        memberList.add(String.valueOf(GSConstPortal.MEMBER_GROUP_HEADSTR
                                                + model.getGrpSid()));
                    }
                }
                String[] usrGrpSids = (String[]) memberList.toArray(new String[memberList.size()]);
                paramMdl.setPtl050memberSid(usrGrpSids);
            }
        }
    }
}
