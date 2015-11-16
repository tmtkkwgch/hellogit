package jp.groupsession.v2.man.man280;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnPluginAdminDao;
import jp.groupsession.v2.cmn.dao.base.CmnPluginControlDao;
import jp.groupsession.v2.cmn.dao.base.CmnPluginControlMemberDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginAdminModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlMemberModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 管理者設定 プラグイン使用制限画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man280Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man280Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param pconfig PluginConfig
     * @param reqMdl リクエスト情報
     * @throws SQLException 実行例外
     */
    public void setInitData(Man280ParamModel paramMdl, Connection con,
                            PluginConfig pconfig, RequestModel reqMdl)
    throws SQLException {
        log__.debug("START");

        String pluginId = paramMdl.getMan120pluginId();

        //初期表示の場合は登録されているプラグイン使用制限情報を設定する
        if (paramMdl.getMan280initFlg() == 0) {
            paramMdl.setMan280initFlg(1);

            CmnPluginControlDao pcontrolDao = new CmnPluginControlDao(con);
            CmnPluginControlModel pcontrolModel = pcontrolDao.select(pluginId);
            if (pcontrolModel != null) {
                paramMdl.setMan280useKbn(pcontrolModel.getPctKbn());
                paramMdl.setMan280limitType(pcontrolModel.getPctType());

                if (pcontrolModel.getPctKbn() == GSConstMain.PCT_KBN_MEMBER) {
                    CmnPluginControlMemberDao pcontrolMemDao = new CmnPluginControlMemberDao(con);
                    List<CmnPluginControlMemberModel> memList = pcontrolMemDao.select(pluginId);

                    List<String> memSidList = new ArrayList<String>();
                    for (CmnPluginControlMemberModel memMdl : memList) {
                        if (memMdl.getGrpSid() == GSConstMain.PCM_MEMSID_NOSET) {
                            memSidList.add(String.valueOf(memMdl.getUsrSid()));
                        } else {
                            memSidList.add("G" + String.valueOf(memMdl.getGrpSid()));
                        }
                    }
                    paramMdl.setMan280memberSid(
                            (String[]) memSidList.toArray(new String[memSidList.size()]));
                }
            }

            if (!paramMdl.getMan120pluginId().equals(GSConst.PLUGINID_MAIN)) {
                //管理者
                CmnPluginAdminDao pcontrolAdminDao = new CmnPluginAdminDao(con);
                List<CmnPluginAdminModel> admList = pcontrolAdminDao.select(pluginId);
                List<String> admSidList = new ArrayList<String>();
                if (admList != null && admList.size() > 0) {

                    for (CmnPluginAdminModel admMdl : admList) {
                        if (admMdl.getGrpSid() == GSConstMain.PCM_MEMSID_NOSET) {
                            admSidList.add(String.valueOf(admMdl.getUsrSid()));
                        } else {
                            //システム管理グループは除外する。
                            if (admMdl.getGrpSid() != GSConstUser.SID_ADMIN) {
                                admSidList.add("G" + String.valueOf(admMdl.getGrpSid()));
                            }
                        }
                    }

                    paramMdl.setMan280AdminSid(
                            (String[]) admSidList.toArray(new String[admSidList.size()]));
                } else {
                    paramMdl.setMan280AdminSid(
                            (String[]) admSidList.toArray(new String[admSidList.size()]));

                }
            }
        }


        //プラグインの名称を設定
        Plugin pg = new Plugin();
        pg = pconfig.getPlugin(pluginId);
        paramMdl.setMan280pluginName(pg.getName(reqMdl));

        int pgKbn = 0;
        pgKbn = pg.getPluginKbn();
        if (pgKbn == GSConstMain.PLUGIN_USER) {
            //ユーザプラグインの場合はバイナリSIDを取得
            paramMdl.setMan280pluginKbn(pgKbn);
            paramMdl.setMan280BinSid(pg.getTopMenuInfo().getBinSid());
        }

        //グループコンボを設定
        paramMdl.setMan280GroupList(__getGroupLabelList(con, reqMdl));
        //グループコンボを設定
        paramMdl.setMan280GroupListAdmin(__getGroupLabelListAdmin(con, reqMdl));

        //フォーラムメンバー グループコンボ選択値
        int forumSltGp = paramMdl.getMan280groupSid();
        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();
        GroupDao dao = new GroupDao(con);
        UserBiz userBiz = new UserBiz();

        if (forumSltGp == Man280Form.GRP_SID_GRPLIST) {
            //グループを全て取得
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);
            //除外するグループSID
            List<String> fullGrepList = new ArrayList<String>();
            if (paramMdl.getMan280memberSid() != null) {
                fullGrepList = Arrays.asList(paramMdl.getMan280memberSid());
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
            for (String str : paramMdl.getMan280memberSid()) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }
            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, paramMdl.getMan280groupSid(), usrSids);
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                 String.valueOf(cuiMdl.getUsrSid())));
            }
        }

        paramMdl.setMan280RightUserList(labelListAdd);

        //メンバ追加一覧
        paramMdl.setMan280LeftUserList(__getMemberList(paramMdl, con));

        if (!paramMdl.getMan120pluginId().equals(GSConst.PLUGINID_MAIN)) {
            //管理者 グループコンボ選択値
            int adminSltGp = paramMdl.getMan280groupSidAdmin();
            List<LabelValueBean> labelListAddAdmin = new ArrayList<LabelValueBean>();

            if (adminSltGp == Man280Form.GRP_SID_GRPLIST) {
                //グループを全て取得
                CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
                CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
                ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);
                //除外するグループSID
                List<String> fullGrepList = new ArrayList<String>();
                if (paramMdl.getMan280memberSid() != null) {
                    fullGrepList = Arrays.asList(paramMdl.getMan280AdminSid());
                }
                for (GroupModel bean : allGpList) {
                    if (bean.getGroupSid() == GSConstUser.SID_ADMIN) {
                        continue;
                    }
                    if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                        labelListAddAdmin.add(new LabelValueBean(
                                bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                    }
                }

            } else {
                //追加用ユーザを取得する
                ArrayList<Integer> usrSids = new ArrayList<Integer>();
                for (String str : paramMdl.getMan280AdminSid()) {
                    usrSids.add(new Integer(NullDefault.getInt(str, -1)));
                }
                List<CmnUsrmInfModel> usList
                    = userBiz.getBelongUserList(con, paramMdl.getMan280groupSidAdmin(), usrSids);
                for (int i = 0; i < usList.size(); i++) {
                    CmnUsrmInfModel cuiMdl = usList.get(i);
                    labelListAddAdmin.add(
                            new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                     String.valueOf(cuiMdl.getUsrSid())));
                }
            }

            paramMdl.setMan280RightAdminList(labelListAddAdmin);

            //メンバ追加一覧
            paramMdl.setMan280LeftAdminList(__getAdminList(paramMdl, con));
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return メンバー一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getMemberList(Man280ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getMan280memberSid();
        return __getMemberList(leftFull, con);
    }

    /**
     * <br>[機  能] 管理者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return メンバー一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAdminList(Man280ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getMan280AdminSid();
        return __getMemberList(leftFull, con);
    }

    /**
     * <br>[機  能] メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return メンバー一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getMemberList(String[] left, Connection con)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

        //
        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");
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
        //ユーザ情報取得
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

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl);
        LabelValueBean labelBean = new LabelValueBean();
        labelBean.setLabel(gsMsg.getMessage("cmn.grouplist"));
        labelBean.setValue(String.valueOf(Man280Form.GRP_SID_GRPLIST));
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
     * <br>[機  能] 表示グループ用のグループリストを取得する(全て)
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private ArrayList <LabelValueBean> __getGroupLabelListAdmin(Connection con, RequestModel reqMdl)
    throws SQLException {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl);
        LabelValueBean labelBean = new LabelValueBean();
        labelBean.setLabel(gsMsg.getMessage("cmn.grouplist"));
        labelBean.setValue(String.valueOf(Man280Form.GRP_SID_GRPLIST));
        labelList.add(labelBean);

        //グループリスト取得
        GroupBiz gBiz = new GroupBiz();
        ArrayList <GroupModel> gpList = gBiz.getGroupCombList(con);

        GroupModel gpMdl = null;
        for (int i = 0; i < gpList.size(); i++) {
            gpMdl = gpList.get(i);
            if (gpMdl.getGroupSid() == GSConstUser.SID_ADMIN) {
                continue;
            }
            labelList.add(
                    new LabelValueBean(gpMdl.getGroupName(), String.valueOf(gpMdl.getGroupSid())));
        }
        log__.debug("labelList.size()=>" + labelList.size());
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
     * @param admFlg 管理者リストの場合 true
     * @return 削除済みのメンバーリスト
     */
    public String[] getDeleteMember(String[] deleteSelectSid, String[] memberSid, boolean admFlg) {

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

            if (!admFlg || !memberSid[i].equals("G0")) {
                for (int j = 0; j < deleteSelectSid.length; j++) {

                    if (memberSid[i].equals(deleteSelectSid[j])) {
                        setFlg = false;
                        break;
                    }
                }
            }

            if (setFlg) {
                list.add(memberSid[i]);
            }
        }

        log__.debug("削除後メンバーリストサイズ = " + list.size());
        return list.toArray(new String[list.size()]);
    }

}
