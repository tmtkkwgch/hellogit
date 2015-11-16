package jp.groupsession.v2.man.man121;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
import jp.groupsession.v2.cmn.dao.base.CmnTdispDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginAdminModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlMemberModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlModel;
import jp.groupsession.v2.cmn.model.base.CmnTdispModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.man.man120.Man120PluginControlModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 管理者設定 プラグインマネージャー(制限設定一覧)画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Man121Biz {

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param pconfig PluginConfig
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Man121ParamModel paramMdl, Connection con, PluginConfig pconfig,
                            RequestModel reqMdl)
    throws SQLException  {

        //一覧を設定します。
        CmnPluginControlDao pcontDao = new CmnPluginControlDao(con);
        List<CmnPluginControlModel> pcontMdlList = pcontDao.select();
        List<CmnPluginControlMemberModel> memList = null;
        List<Man120PluginControlModel> dspList = new ArrayList<Man120PluginControlModel>();
        Man120PluginControlModel model = null;
        int groupCnt = 0;
        int userCnt = 0;
        String taisyo = "";

        // プラグイン一覧を取得
        List<Plugin> pluginList = new ArrayList<Plugin>();
        List<Plugin> dspPluginList = new ArrayList<Plugin>();
        for (Plugin plugin : pconfig.getPluginDataList()) {

            if (plugin != null
            && !plugin.getId().equals(GSConst.PLUGINID_MAIN)
            && !plugin.getId().equals(GSConst.PLUGINID_COMMON)
            && !plugin.getId().equals("help")
            && !plugin.getId().equals("license")) {
                pluginList.add(plugin);
            }
        }

        //トップ表示設定を取得(管理者ユーザの登録値)
        CmnTdispDao dispDao = new CmnTdispDao(con);
        List<CmnTdispModel> dispList = dispDao.getAdminTdispList();

        if (dispList == null || dispList.isEmpty()) {
            //トップ表示設定が登録されていない場合
            dspPluginList = pluginList;

        } else {
            for (CmnTdispModel dbDispMdl : dispList) {
                Plugin plugin = pconfig.getPlugin(dbDispMdl.getTdpPid());
                if (plugin != null) {
                    if (plugin.getId().equals("license")
                            || plugin.getId().equals(GSConst.PLUGINID_MAIN)
                            || plugin.getId().equals(GSConst.PLUGINID_COMMON)
                            || plugin.getId().equals("help")
                    ) {
                        continue;
                    }
                    dspPluginList.add(plugin);
                }
            }
        }

        CmnPluginControlModel ctrlModel = null;
        GsMessage gsMsg = new GsMessage(reqMdl.getLocale());
        if (dspPluginList != null && dspPluginList.size() > 0) {
            for (Plugin plugin : dspPluginList) {

                groupCnt = 0;
                userCnt = 0;
                taisyo = "";
                model = new Man120PluginControlModel();
                model.setPluginId(plugin.getId());
                model.setPluginName(plugin.getName(reqMdl));
                model.setPluginKbn(plugin.getPluginKbn());
                if (plugin.getTopMenuInfo() != null) {
                    model.setPluginBinSid(plugin.getTopMenuInfo().getBinSid());
                }
                model.setPctType(2);


                //制限設定取得
                if (pcontMdlList != null && pcontMdlList.size() > 0) {
                    ctrlModel = null;
                    for (CmnPluginControlModel mdl : pcontMdlList) {
                        if (mdl.getPctPid().equals(plugin.getId())) {
                            ctrlModel = mdl;
                            continue;
                        }
                    }

                    if (ctrlModel != null) {
                        if (ctrlModel.getPctKbn() == GSConstMain.PCT_KBN_ALLOK) {
                            //すべてのユーザが使用可能な場合
                            model.setTaisyo(taisyo);
                        } else {
                            //グループユーザ指定の場合
                            model.setPctType(ctrlModel.getPctType());
                            //制限対象を設定
                            if (ctrlModel.getPctKbn() == GSConstMain.PCT_KBN_MEMBER) {
                                CmnPluginControlMemberDao pcontrolMemDao
                                        = new CmnPluginControlMemberDao(con);
                                memList = pcontrolMemDao.select(ctrlModel.getPctPid());

                                for (CmnPluginControlMemberModel memMdl : memList) {
                                    if (memMdl.getGrpSid() == GSConstMain.PCM_MEMSID_NOSET) {
                                        userCnt++;
                                    } else {
                                        groupCnt++;
                                    }
                                }
                                if (groupCnt > 0) {
                                    taisyo = groupCnt + gsMsg.getMessage("cmn.group");
                                    if (userCnt > 0) {
                                        taisyo += " + ";
                                    }
                                }
                                if (userCnt > 0) {
                                    taisyo += userCnt + gsMsg.getMessage("cmn.user");
                                }
                                model.setTaisyo(taisyo);
                            }

                        }

                    }
                }

                //管理者を設定する。
                __setAdminList(model, con);
                dspList.add(model);
            }
            paramMdl.setMan121dspList(dspList);
        }

    }

    /**
     * <br>[機  能] 管理者の名前一覧を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param model モデル
     * @param con コネクション
     * @return count 管理者件数 グループも1件としてカウントします。
     * @throws SQLException SQL実行時例外
     */
    private int __setAdminList(Man120PluginControlModel model, Connection con)
    throws SQLException {
        int count = 0;

        //取得するユーザSID・グループSID
        CmnPluginAdminDao admDao = new CmnPluginAdminDao(con);
        List<CmnPluginAdminModel> admMdlList = admDao.select(model.getPluginId());


        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();
        if (admMdlList != null && admMdlList.size() > 0) {
            for (CmnPluginAdminModel admModel : admMdlList) {
                if (admModel.getGrpSid() == GSConstMain.PCM_MEMSID_NOSET) {
                    usrSids.add(String.valueOf(admModel.getUsrSid()));
                } else {
                    grpSids.add(admModel.getGrpSid());
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
            count++;
        }
        model.setAdmGroupNameList(groupList);

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
            count++;
        }
        model.setAdmUserNameList(userList);

        return count;
    }
}
