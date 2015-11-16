package jp.groupsession.v2.bmk.bmk090;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.dao.BmkGconfDao;
import jp.groupsession.v2.bmk.dao.BmkGroupEditDao;
import jp.groupsession.v2.bmk.model.BmkGconfModel;
import jp.groupsession.v2.bmk.model.BmkGroupEditModel;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 権限設定(グループブックマーク)画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk090Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk090Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Bmk090Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk090ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Bmk090ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        if (paramMdl.getBmk090GrpName() == null) {
            //グループ名の設定
            GroupDao grpDao = new GroupDao(con);
                CmnGroupmModel grpMdl = grpDao.getGroup(paramMdl.getBmk010groupSid());
                paramMdl.setBmk090GrpName(
                        StringUtilHtml.transToHTmlPlusAmparsant(grpMdl.getGrpName()));
        }

        BmkGconfDao dao = new BmkGconfDao(con);
        BmkGconfModel model = dao.selectGConf(paramMdl.getBmk010groupSid());

        int grpEditKbn = GSConstBookmark.EDIT_POW_ADMIN;

        if (model != null) {
            grpEditKbn = model.getBgcEdit();
        }

        if (paramMdl.getBmk090GrpEditKbn() == -1) {
            //グループブックマーク編集権限の初期化
            paramMdl.setBmk090GrpEditKbn(grpEditKbn);
        }

        if (paramMdl.getBmk090GrpEditKbn() == GSConstBookmark.EDIT_POW_USER) {
            //共有ブックマーク編集権限：ユーザ指定

            //デフォルトグループの設定
            GroupBiz grpBiz = new GroupBiz();
            paramMdl.setBmk090GroupSid(
                    grpBiz.getDefaultGroupSid(sessionUserSid, con));

            ArrayList<String> sidList = new ArrayList<String>();

            if (model != null && model.getBgcEdit() == GSConstBookmark.EDIT_POW_USER) {

                //編集ユーザ取得
                BmkGroupEditDao daoGrp = new BmkGroupEditDao(con);
                List<BmkGroupEditModel> modelList = daoGrp.select(paramMdl.getBmk010groupSid());

                if (modelList != null) {
                    for (BmkGroupEditModel addModel : modelList) {
                        sidList.add(Integer.toString(addModel.getBgeUsrSid()));
                    }
                }
            }
            paramMdl.setBmk090UserSid(sidList.toArray(new String[0]));

            //ユーザ指定　各項目初期化
            setInitDataUser(paramMdl, sessionUserSid, con);

        } else if (paramMdl.getBmk090GrpEditKbn() == GSConstBookmark.EDIT_POW_GROUP) {
            //共有ブックマーク編集権限：グループ指定

            //編集グループ取得
            ArrayList<String> sidList = new ArrayList<String>();

            if (model != null && model.getBgcEdit() == GSConstBookmark.EDIT_POW_GROUP) {

                BmkGroupEditDao daoGrp = new BmkGroupEditDao(con);
                List<BmkGroupEditModel> modelList = daoGrp.select(paramMdl.getBmk010groupSid());

                if (modelList != null) {
                    for (BmkGroupEditModel addModel : modelList) {
                        sidList.add(Integer.toString(addModel.getBgeGrpSid()));
                    }
                }
            }
            paramMdl.setBmk090GrpSid(sidList.toArray(new String[0]));
            //グループ指定　各項目初期化
            setInitDataGroup(paramMdl, sessionUserSid, con);
        }

        log__.debug("start");
    }

    /**
     * <br>[機  能] 初期表示を行う(共有ブックマーク：ユーザ指定)
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk090ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitDataUser(
            Bmk090ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);

        //グループ一覧を取得する
        GroupBiz gpBiz = new GroupBiz();
        List<LabelValueBean> grpLabelList = gpBiz.getGroupCombLabelList(con, true, gsMsg);

        paramMdl.setBmk090GroupList(grpLabelList);

        //追加済みユーザ一覧をセット
        UserBiz userBiz = new UserBiz();
        List<LabelValueBean> rightList = userBiz.getUserLabelList(con, paramMdl.getBmk090UserSid());
        paramMdl.setBmk090RightUserList(rightList);

        //追加用ユーザ一覧をセット
        int gpSid = paramMdl.getBmk090GroupSid();

        //除外するユーザSID
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        String[] userSids = paramMdl.getBmk090UserSid();
        if (userSids != null) {
            for (int i = 0; i < userSids.length; i++) {
                usrSids.add(new Integer(NullDefault.getInt(userSids[i], -1)));
            }
        }
        List<CmnUsrmInfModel> usList
            = userBiz.getBelongUserList(con, gpSid, usrSids);

        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();
        for (CmnUsrmInfModel cuiMdl : usList) {
            labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                             String.valueOf(cuiMdl.getUsrSid())));
        }
        paramMdl.setBmk090LeftUserList(labelListAdd);
    }
    /**
     * <br>[機  能] 初期表示を行う(共有ブックマーク：グループ指定)
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk090ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitDataGroup(
            Bmk090ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        //グループ一覧を取得
        ArrayList<LabelValueBean> allGroupCombo = new ArrayList<LabelValueBean>();
//        GroupDao dao = new GroupDao(con);
//        ArrayList<GroupModel> gpList = dao.getGroupTree();
        GroupBiz groupBiz = new GroupBiz();
        ArrayList<GroupModel> gpList = groupBiz.getGroupList(con);

        for (GroupModel gpMdl : gpList) {
            allGroupCombo.add(
                    new LabelValueBean(gpMdl.getGroupName(),
                                    String.valueOf(gpMdl.getGroupSid())));
        }

        List<String> selectGroupList = new ArrayList<String>();
        if (paramMdl.getBmk090GrpSid() != null) {
            selectGroupList = Arrays.asList(paramMdl.getBmk090GrpSid());
        }
        //追加済みグループ一覧をセット
        List<LabelValueBean> rightList = new ArrayList <LabelValueBean>();

        for (LabelValueBean bean : allGroupCombo) {
            if (selectGroupList.contains(bean.getValue())) {
                rightList.add(new LabelValueBean(bean.getLabel(), bean.getValue()));
            }
        }
        paramMdl.setBmk090RightGroupList(rightList);

        //追加グループ一覧をセット
        List<LabelValueBean> leftList = new ArrayList <LabelValueBean>();

        for (LabelValueBean bean : allGroupCombo) {
            if (!selectGroupList.contains(bean.getValue())) {
                leftList.add(new LabelValueBean(bean.getLabel(), bean.getValue()));
            }
        }
        paramMdl.setBmk090LeftGroupList(leftList);
    }
}
