package jp.groupsession.v2.man.man280kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.Plugin;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnPluginAdminDao;
import jp.groupsession.v2.cmn.dao.base.CmnPluginControlDao;
import jp.groupsession.v2.cmn.dao.base.CmnPluginControlMemberDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginAdminModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlMemberModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlModel;
import jp.groupsession.v2.man.GSConstMain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 管理者設定 プラグイン使用制限確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man280knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Man280knBiz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param pconfig PluginConfig
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void setInitData(Man280knParamModel paramMdl, Connection con, PluginConfig pconfig,
                            RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        //プラグインの名称を設定
        paramMdl.setMan280pluginName(
                pconfig.getPlugin(paramMdl.getMan120pluginId()).getName(reqMdl));

        //メンバリストを設定する
        __setMemberList(paramMdl, con);

        //管理者リストを設定する。
        if (!paramMdl.getMan120pluginId().equals(GSConst.PLUGINID_MAIN)) {
            __setAdminList(paramMdl, con);
        }

        String pluginId = paramMdl.getMan120pluginId();

        Plugin pg = new Plugin();
        pg = pconfig.getPlugin(pluginId);

        int pgKbn = 0;
        pgKbn = pg.getPluginKbn();
        if (pgKbn == GSConstMain.PLUGIN_USER) {
            //ユーザプラグインの場合はバイナリSIDを取得
            paramMdl.setMan280pluginKbn(pgKbn);
            paramMdl.setMan280BinSid(pg.getTopMenuInfo().getBinSid());
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] プラグイン使用制限情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void insertPluginUseConfig(Man280knParamModel paramMdl,
                                    Connection con,
                                    int userSid)
    throws Exception {
        log__.debug("START");

        String pluginId = paramMdl.getMan120pluginId();

        //プラグイン使用制限の登録
        CmnPluginControlModel pcontrolModel = new CmnPluginControlModel();
        pcontrolModel.setPctPid(pluginId);
        int pctType = GSConstMain.PCT_TYPE_LIMIT;
        if (paramMdl.getMan280useKbn() == GSConstMain.PCT_KBN_MEMBER) {
            pcontrolModel.setPctKbn(GSConstMain.PCT_KBN_MEMBER);
            if (paramMdl.getMan280limitType() == GSConstMain.PCT_TYPE_PERMIT) {
                pctType = GSConstMain.PCT_TYPE_PERMIT;
            }
        } else {
            pcontrolModel.setPctKbn(GSConstMain.PCT_KBN_ALLOK);
        }
        pcontrolModel.setPctType(pctType);

        CmnPluginControlDao pcontrolDao = new CmnPluginControlDao(con);
        if (pcontrolDao.update(pcontrolModel) == 0) {
            pcontrolDao.insert(pcontrolModel);
        }

        //プラグイン使用制限_メンバーの登録
        CmnPluginControlMemberDao pcontrolMemDao = new CmnPluginControlMemberDao(con);
        pcontrolMemDao.delete(pluginId);

        if (pcontrolModel.getPctKbn() == GSConstMain.PCT_KBN_MEMBER) {
            CmnPluginControlMemberModel memModel = new CmnPluginControlMemberModel();
            memModel.setPctPid(pluginId);

            for (String memSid : paramMdl.getMan280memberSid()) {
                if (memSid.startsWith("G")) {
                    memModel.setGrpSid(Integer.parseInt(memSid.substring(1)));
                    memModel.setUsrSid(GSConstMain.PCM_MEMSID_NOSET);
                } else {
                    memModel.setGrpSid(GSConstMain.PCM_MEMSID_NOSET);
                    memModel.setUsrSid(Integer.parseInt(memSid));
                }

                pcontrolMemDao.insert(memModel);
            }
        }


        if (!pluginId.equals(GSConst.PLUGINID_MAIN)) {
            //プラグイン管理者の登録
            CmnPluginAdminDao pcontrolAdmDao = new CmnPluginAdminDao(con);
            pcontrolAdmDao.delete(pluginId);

            CmnPluginAdminModel admModel = new CmnPluginAdminModel();
            admModel.setPctPid(pluginId);

            for (String admSid : paramMdl.getMan280AdminSid()) {
                if (admSid.startsWith("G")) {
                    admModel.setGrpSid(Integer.parseInt(admSid.substring(1)));
                    admModel.setUsrSid(GSConstMain.PCM_MEMSID_NOSET);
                } else {
                    admModel.setGrpSid(GSConstMain.PCM_MEMSID_NOSET);
                    admModel.setUsrSid(Integer.parseInt(admSid));
                }

                pcontrolAdmDao.insert(admModel);
            }
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] メンバーの名前一覧を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setMemberList(Man280knParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] left = paramMdl.getMan280memberSid();

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
        ArrayList<LabelValueBean> groupList = new ArrayList<LabelValueBean>();
        LabelValueBean labelBean = null;
        for (GroupModel gmodel : glist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(gmodel.getGroupName());
            labelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            groupList.add(labelBean);
        }
        paramMdl.setMan280knMemGroupNameList(groupList);

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        ArrayList<LabelValueBean> userList = new ArrayList<LabelValueBean>();
        for (BaseUserModel umodel : ulist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            labelBean.setValue(String.valueOf(umodel.getUsrsid()));
            userList.add(labelBean);
        }
        paramMdl.setMan280knMemUserNameList(userList);
    }

    /**
     * <br>[機  能] 管理者の名前一覧を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setAdminList(Man280knParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] left = paramMdl.getMan280AdminSid();

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
        ArrayList<LabelValueBean> groupList = new ArrayList<LabelValueBean>();
        LabelValueBean labelBean = null;
        for (GroupModel gmodel : glist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(gmodel.getGroupName());
            labelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            groupList.add(labelBean);
        }
        paramMdl.setMan280knAdmGroupNameList(groupList);

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        ArrayList<LabelValueBean> userList = new ArrayList<LabelValueBean>();
        for (BaseUserModel umodel : ulist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            labelBean.setValue(String.valueOf(umodel.getUsrsid()));
            userList.add(labelBean);
        }
        paramMdl.setMan280knAdmUserNameList(userList);
    }

}