package jp.groupsession.v2.bmk.bmk110;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.bmk.GSConstBookmark;
import jp.groupsession.v2.bmk.dao.BmkAconfDao;
import jp.groupsession.v2.bmk.dao.BmkPublicEditDao;
import jp.groupsession.v2.bmk.model.BmkAconfModel;
import jp.groupsession.v2.bmk.model.BmkPublicEditModel;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 管理者設定 権限設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bmk110Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bmk110Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Bmk110Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 表示データの初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk110ParamModel
     * @param model 管理者設定モデル
     */
    public void setDspData(Bmk110ParamModel paramMdl, BmkAconfModel model) {

        int pubEditKbn = GSConstBookmark.EDIT_POW_ADMIN;
        int grpEditKbn = GSConstBookmark.GROUP_EDIT_ADMIN;

        if (model != null) {
            pubEditKbn = model.getBacPubEdit();
            grpEditKbn = model.getBacGrpEdit();
        }
        log__.debug("共有ブックマーク編集権限 : " + paramMdl.getBmk110PubEditKbn());
        log__.debug("グループブックマーク編集権限 : " + paramMdl.getBmk110GrpEditKbn());
        if (paramMdl.getBmk110PubEditKbn() == -1) {
            //共有ブックマーク編集権限の初期化
            paramMdl.setBmk110PubEditKbn(pubEditKbn);
        }
        if (paramMdl.getBmk110GrpEditKbn() == -1) {
            ////グループブックマーク編集権限の初期化
            paramMdl.setBmk110GrpEditKbn(grpEditKbn);
        }
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk110ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Bmk110ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        BmkAconfDao dao = new BmkAconfDao(con);
        BmkAconfModel model = dao.selectAConf();

        setDspData(paramMdl, model);

//        int pubEditKbn = GSConstBookmark.EDIT_POW_ADMIN;
//        int grpEditKbn = GSConstBookmark.GROUP_EDIT_ADMIN;
//
//        if (model != null) {
//            pubEditKbn = model.getBacPubEdit();
//            grpEditKbn = model.getBacGrpEdit();
//        }
//
//        if (paramMdl.getBmk110PubEditKbn() == -1) {
//            //共有ブックマーク編集権限の初期化
//            paramMdl.setBmk110PubEditKbn(pubEditKbn);
//        }
//        if (paramMdl.getBmk110GrpEditKbn() == -1) {
//            ////グループブックマーク編集権限の初期化
//            paramMdl.setBmk110GrpEditKbn(grpEditKbn);
//        }

        if (paramMdl.getBmk110PubEditKbn() == GSConstBookmark.EDIT_POW_USER) {
            //共有ブックマーク編集権限：ユーザ指定

            //デフォルトグループの設定
            GroupBiz grpBiz = new GroupBiz();
            paramMdl.setBmk110GroupSid(
                    grpBiz.getDefaultGroupSid(sessionUserSid, con));

            ArrayList<String> sidList = new ArrayList<String>();

            if (model != null && model.getBacPubEdit() == GSConstBookmark.EDIT_POW_USER) {

                //編集ユーザ取得
                BmkPublicEditDao daoPub = new BmkPublicEditDao(con);
                List<BmkPublicEditModel> modelList = daoPub.select();

                if (modelList != null) {
                    for (BmkPublicEditModel addModel : modelList) {
                        sidList.add(Integer.toString(addModel.getUsrSid()));
                    }
                }
            }
            paramMdl.setBmk110UserSid(sidList.toArray(new String[0]));

            //ユーザ指定　各項目初期化
            setInitDataUser(paramMdl, sessionUserSid, con);

        } else if (paramMdl.getBmk110PubEditKbn() == GSConstBookmark.EDIT_POW_GROUP) {
            //共有ブックマーク編集権限：グループ指定

            //編集グループ取得
            ArrayList<String> sidList = new ArrayList<String>();

            if (model != null && model.getBacPubEdit() == GSConstBookmark.EDIT_POW_GROUP) {

                BmkPublicEditDao daoPub = new BmkPublicEditDao(con);
                List<BmkPublicEditModel> modelList = daoPub.select();

                if (modelList != null) {
                    for (BmkPublicEditModel addModel : modelList) {
                        sidList.add(Integer.toString(addModel.getGrpSid()));
                    }
                }
            }
            paramMdl.setBmk110GrpSid(sidList.toArray(new String[0]));
            //グループ指定　各項目初期化
            setInitDataGroup(paramMdl, sessionUserSid, con);
        }
        log__.debug("start");
    }

    /**
     * <br>[機  能] 初期表示を行う(共有ブックマーク：ユーザ指定)
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk110ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitDataUser(
            Bmk110ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);

        //グループ一覧を取得する
        GroupBiz gpBiz = new GroupBiz();
        List<LabelValueBean> grpLabelList = gpBiz.getGroupCombLabelList(con, true, gsMsg);

        paramMdl.setBmk110GroupList(grpLabelList);

        //追加済みユーザを取得する
        UserBiz userBiz = new UserBiz();
        List<LabelValueBean> rightList
                = userBiz.getUserLabelList(con, paramMdl.getBmk110UserSid());
        paramMdl.setBmk110RightUserList(rightList);

        //追加用ユーザ一覧をセット
        int gpSid = paramMdl.getBmk110GroupSid();

        //除外するユーザSID
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        String[] userSids = paramMdl.getBmk110UserSid();
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
        paramMdl.setBmk110LeftUserList(labelListAdd);
    }
    /**
     * <br>[機  能] 初期表示を行う(共有ブックマーク：グループ指定)
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Bmk110ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitDataGroup(
            Bmk110ParamModel paramMdl,
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
        if (paramMdl.getBmk110GrpSid() != null) {
            selectGroupList = Arrays.asList(paramMdl.getBmk110GrpSid());
        }
        //追加済みグループ一覧をセット
        List<LabelValueBean> rightList = new ArrayList <LabelValueBean>();

        for (LabelValueBean bean : allGroupCombo) {
            if (selectGroupList.contains(bean.getValue())) {
                rightList.add(new LabelValueBean(bean.getLabel(), bean.getValue()));
            }
        }
        paramMdl.setBmk110RightGroupList(rightList);

        //追加グループ一覧をセット
        List<LabelValueBean> leftList = new ArrayList <LabelValueBean>();

        for (LabelValueBean bean : allGroupCombo) {
            if (!selectGroupList.contains(bean.getValue())) {
                leftList.add(new LabelValueBean(bean.getLabel(), bean.getValue()));
            }
        }
        paramMdl.setBmk110LeftGroupList(leftList);
    }
}
