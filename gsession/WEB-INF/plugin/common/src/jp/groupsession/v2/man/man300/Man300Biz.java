package jp.groupsession.v2.man.man300;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnInfoUserDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnInfoUserModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン インフォメーション 管理者設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man300Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man300Biz.class);

    /**
     * <br>[機  能] 初期表示画面情報を設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param cmd 操作種別パラメータ
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Man300ParamModel paramMdl, Connection con,
                            RequestModel reqMdl, String cmd)
    throws SQLException {

        //初期表示
        if (cmd.equals("admconf")) {
            CmnInfoUserDao usrDao = new CmnInfoUserDao(con);
            ArrayList<CmnInfoUserModel> usrMdlList = null;
            ArrayList<String> list = new ArrayList<String>();
            usrMdlList = usrDao.select();

            for (int i = 0; i < usrMdlList.size(); i++) {
                if (usrMdlList.get(i).getUsrSid() != -1) {
                    list.add(String.valueOf(usrMdlList.get(i).getUsrSid()));
                }

                if (usrMdlList.get(i).getGrpSid() != -1) {
                    list.add(String.valueOf("G"
                                            + usrMdlList.get(i).getGrpSid()));
                }

            }
            String[] usrGrpSids = (String[]) list.toArray(new String[list.size()]);
            paramMdl.setMan300memberSid(usrGrpSids);
        }

        //グループラベル
        ArrayList<LabelValueBean> gpLabelList = __getGroupLabelList(con, reqMdl);
        paramMdl.setMan300GroupList(gpLabelList);

        //グループコンボ選択値
        int infoSltGp = paramMdl.getMan300groupSid();
        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();

        if (infoSltGp == Integer.parseInt(GSConstMain.GROUP_COMBO_VALUE)) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);
            //除外するグループSID
            List<String> fullGrepList = new ArrayList<String>();
            if (paramMdl.getMan300memberSid() != null) {
                fullGrepList = Arrays.asList(paramMdl.getMan300memberSid());
            }
            for (GroupModel bean : allGpList) {
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    labelListAdd.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }

        } else {
            //追加用ユーザを取得する
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            for (String str : paramMdl.getMan300memberSid()) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, paramMdl.getMan300groupSid(), usrSids);
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                 String.valueOf(cuiMdl.getUsrSid())));
            }
        }

        //候補
        paramMdl.setMan300RightUserList(labelListAdd);
        //メンバ追加一覧
        paramMdl.setMan300LeftUserList(__getInfoFullLabel(paramMdl, con));
    }

    /**
     * <br>[機  能] ユーザコンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param uList (in CmnUsrmInfModel) ユーザ情報リスト
     * @return ArrayList (in LabelValueBean)  年コンボ
     */
    public ArrayList<LabelValueBean> getUserLabel(ArrayList < CmnUsrmInfModel > uList) {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        if (uList != null) {
            CmnUsrmInfModel uMdl = null;
            for (int i = 0; i < uList.size(); i++) {
                uMdl = uList.get(i);
                String name = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
                labelList.add(
                        new LabelValueBean(name, String.valueOf(uMdl.getUsrSid())));
            }
        }

        return labelList;
    }

    /**
     * <br>[機  能] 追加側のコンボで選択中のメンバーをメンバーリストに追加する
     *
     * <br>[解  説] 画面右側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で追加矢印ボタンをクリックした場合、
     *              追加側のコンボで選択中の値(addSelectSid)をメンバーリストに追加する。
     *
     * <br>[備  考] 追加側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param addSelectSid 追加側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 追加済みのメンバーリスト
     */
    public String[] getAddMember(String[] addSelectSid, String[] memberSid) {

        if (addSelectSid == null) {
            return memberSid;
        }
        if (addSelectSid.length < 1) {
            return memberSid;
        }


        //追加後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        if (memberSid != null) {
            for (int j = 0; j < memberSid.length; j++) {
                if (!memberSid[j].equals("-1")) {
                    list.add(memberSid[j]);
                }
            }
        }

        for (int i = 0; i < addSelectSid.length; i++) {
            if (!addSelectSid[i].equals("-1")) {
                list.add(addSelectSid[i]);
            }
        }

        log__.debug("追加後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] 登録に使用する側のコンボで選択中のメンバーをメンバーリストから削除する
     *
     * <br>[解  説] 登録に使用する側のコンボ表示に必要なSID(複数)をメンバーリスト(memberSid)で持つ。
     *              画面で削除矢印ボタンをクリックした場合、
     *              登録に使用する側のコンボで選択中の値(deleteSelectSid)をメンバーリストから削除する。
     *
     * <br>[備  考] 登録に使用する側のコンボで値が選択されていない場合はメンバーリストをそのまま返す
     *
     * @param deleteSelectSid 登録に使用する側のコンボで選択中の値
     * @param memberSid メンバーリスト
     * @return 削除済みのメンバーリスト
     */
    public String[] getDeleteMember(String[] deleteSelectSid, String[] memberSid) {

        if (deleteSelectSid == null) {
            return memberSid;
        }
        if (deleteSelectSid.length < 1) {
            return memberSid;
        }

        if (memberSid == null) {
            memberSid = new String[0];
        }

        //削除後に画面にセットするメンバーリストを作成
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < memberSid.length; i++) {
            boolean setFlg = true;

            for (int j = 0; j < deleteSelectSid.length; j++) {
                if (memberSid[i].equals(deleteSelectSid[j])) {
                    setFlg = false;
                    break;
                }
            }

            if (setFlg) {
                list.add(memberSid[i]);
            }
        }

        log__.debug("削除後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

    /**
     * <br>[機  能] 表示グループ用のグループリストを取得する(全て)
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private ArrayList <LabelValueBean> __getGroupLabelList(Connection con, RequestModel reqMdl)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        LabelValueBean labelBean = new LabelValueBean();
        labelBean.setLabel(gsMsg.getMessage(GSConstMain.TEXT_GROUP_COMBO));
        labelBean.setValue(GSConstMain.GROUP_COMBO_VALUE);
        labelList.add(labelBean);

        //グループリスト取得
        GroupBiz gBiz = new GroupBiz();
        ArrayList <GroupModel> gpList = gBiz.getGroupCombList(con);

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
     * <br>[機  能] インフォメーション管理者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getInfoFullLabel(Man300ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getMan300memberSid();
        return __getInfoLabel(leftFull, con);
    }

    /**
     * <br>[機  能] インフォメーション管理者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getInfoLabel(String[] left, Connection con)
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
        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
        LabelValueBean labelBean = null;
        for (GroupModel gmodel : glist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(gmodel.getGroupName());
            labelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            ret.add(labelBean);
        }
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            labelBean.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(labelBean);
        }
        return ret;
    }
}
