package jp.groupsession.v2.cmn.cmn120;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.cmn120.dao.Cmn120Dao;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupMsDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupMsModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ユーザ選択画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn120Biz {

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn120Model パラメータ情報
     * @param con コネクション
     * @param reqMdl リクエストモデル
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void setInitData(
            Cmn120ParamModel cmn120Model, Connection con, RequestModel reqMdl, int userSid)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl.getLocale());
        Cmn120Dao dao = new Cmn120Dao(con);

        //ユーザSIDからマイグループ情報を取得する
        int myGroupSid = -1;
        CmnMyGroupDao cmgDao = new CmnMyGroupDao(con);
        List<LabelValueBean> cmgLabelList = new ArrayList<LabelValueBean>();
        List<CmnMyGroupModel> cmgList = cmgDao.getMyGroupList(userSid);

        for (CmnMyGroupModel cmgMdl : cmgList) {

            if (myGroupSid <= 0) {
                myGroupSid = cmgMdl.getMgpSid();
            }
            cmgLabelList.add(
                new LabelValueBean(cmgMdl.getMgpName(),
                        String.valueOf(cmgMdl.getMgpSid())));
        }

        if (cmn120Model.getCmn120DspKbn() == 0) {
            //マイグループリストをセット
            cmn120Model.setCmn120MyGroupList(cmgLabelList);

            //グループ一覧を取得する
            GroupBiz gpBiz = new GroupBiz();
            List<LabelValueBean> grpLabelList  = new ArrayList<LabelValueBean>();
            List<LabelValueBean> allGrpLabelList = gpBiz.getGroupCombLabelList(con, true, gsMsg);
            List<Integer> grpDisabledList = new ArrayList<Integer>();
            List<String> banGrpSids = new ArrayList<String>();
            if (cmn120Model.getCmn120disableGroupSid() != null) {
                banGrpSids = Arrays.asList(cmn120Model.getCmn120disableGroupSid());
            }
            for (LabelValueBean lv : allGrpLabelList) {
                if (banGrpSids.contains(
                                lv.getValue())) {
                    grpDisabledList.add(1);
                } else {
                    grpDisabledList.add(0);
                }
                grpLabelList.add(lv);

            }
            cmn120Model.setCmn120GroupDisabledList(grpDisabledList);
            cmn120Model.setCmn120GroupList(grpLabelList);

        } else {

            List<LabelValueBean> grpLabelList  = new ArrayList<LabelValueBean>();

            if (cmn120Model.getCmn120DspKbn() == 1) {
                //グループのみ設定
                GroupBiz gpBiz = new GroupBiz();
                List<LabelValueBean> allGrpLabelList
                    = gpBiz.getGroupCombLabelList(con, true, gsMsg);
                List<Integer> grpDisabledList = new ArrayList<Integer>();
                List<String> banGrpSids = new ArrayList<String>();

                if (cmn120Model.getCmn120disableGroupSid() != null) {
                    banGrpSids = Arrays.asList(cmn120Model.getCmn120disableGroupSid());
                }
                for (LabelValueBean lv : allGrpLabelList) {
                    if (banGrpSids.contains(
                                    lv.getValue())) {
                        grpDisabledList.add(1);
                    } else {
                        grpDisabledList.add(0);
                    }
                }
                cmn120Model.setCmn120GroupDisabledList(grpDisabledList);
                grpLabelList.addAll(allGrpLabelList);
            } else if (cmn120Model.getCmn120DspKbn() == 2) {
                //マイグループのみ設定
                grpLabelList.addAll(cmgLabelList);
            } else if (cmn120Model.getCmn120DspKbn() == 3) {
                //ショートメールアカウントのみ設定
                if (dao.selectSmlAccountCnt(null) > 0) {
                    grpLabelList.add(new LabelValueBean("代表アカウント", "sac"));
                }
            } else if (cmn120Model.getCmn120DspKbn() == 4) {
                //回覧板アカウントのみ設定
                if (dao.selectSmlAccountCnt(null) > 0) {
                    grpLabelList.add(new LabelValueBean("代表アカウント", "cac"));
                }
            }
            cmn120Model.setCmn120GroupList(grpLabelList);
        }

        //呼び出し元画面のプラグインを使用不可のユーザを除外
        UserBiz userBiz = new UserBiz();
        String[] variableUserSids = __getVariableUser(con, cmn120Model,
                cmn120Model.getCmn120userSid());
        List<LabelValueBean> rightList
            = userBiz.getUserLabelList(con, __getCanUsePluginUser(con, cmn120Model,
                    variableUserSids));

        rightList.addAll(__getOtherSelUsr(con, variableUserSids));


        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();

        if (cmn120Model.getCmn120DspKbn() == 0 || cmn120Model.getCmn120DspKbn() == 1) {
            //通常 or グループのみ

            //追加用ユーザ一覧をセット
            int gpSid = -1;

            if (GSValidateUtil.isNumber(cmn120Model.getCmn120groupSid())) {
                //選択グループ存在確認
                String dspGrpSid = getEnableSelectGroup(cmn120Model.getCmn120GroupList(),
                        cmn120Model.getCmn120groupSid(),
                        null);
                cmn120Model.setCmn120groupSid(dspGrpSid);
                gpSid = Integer.parseInt(cmn120Model.getCmn120groupSid());
            }

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            String[] userSids = cmn120Model.getCmn120userSid();
            if (userSids != null) {
                for (int i = 0; i < userSids.length; i++) {
                    if (GSValidateUtil.isNumber(userSids[i])) {
                        usrSids.add(new Integer(NullDefault.getInt(userSids[i], -1)));
                    }
                }
            }

            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, gpSid, usrSids);

            List<Integer> canUseUserSids = new ArrayList<Integer>();
            Map<Integer, LabelValueBean> userLabelMap = new HashMap<Integer, LabelValueBean>();
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);

                int labelUserSid = cuiMdl.getUsrSid();
                canUseUserSids.add(labelUserSid);
                userLabelMap.put(labelUserSid,
                                new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                                    String.valueOf(labelUserSid)));
            }

            canUseUserSids = __getVariableUser(con, cmn120Model, canUseUserSids);
            //呼び出し元画面のプラグインを使用不可のユーザを除外する
            canUseUserSids = __getCanUsePluginUser(con, cmn120Model, canUseUserSids);
            for (Integer useUserSid : canUseUserSids) {
                labelListAdd.add(userLabelMap.get(useUserSid));
            }
        } else if (cmn120Model.getCmn120DspKbn() == 2) {
            //マイグループのみ
            if (cmn120Model.getCmn120MyGroupSid() <= 0) {
                cmn120Model.setCmn120MyGroupSid(myGroupSid);
            }

            //選択グループ存在確認
            String dspGrpSid = getEnableSelectGroup(cmn120Model.getCmn120GroupList(),
                    String.valueOf(cmn120Model.getCmn120MyGroupSid()),
                    null);
            cmn120Model.setCmn120MyGroupSid(NullDefault.getInt(dspGrpSid, -1));

            //追加用ユーザ一覧をセット
            int gpSid = cmn120Model.getCmn120MyGroupSid();

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            String[] userSids = cmn120Model.getCmn120userSid();
            if (userSids != null) {
                for (int i = 0; i < userSids.length; i++) {
                    if (GSValidateUtil.isNumber(userSids[i])) {
                        usrSids.add(new Integer(NullDefault.getInt(userSids[i], -1)));
                    }
                }
            }

            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, gpSid, usrSids, userSid, true);

            List<Integer> canUseUserSids = new ArrayList<Integer>();
            Map<Integer, LabelValueBean> userLabelMap = new HashMap<Integer, LabelValueBean>();
            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                int labelUserSid = cuiMdl.getUsrSid();
                canUseUserSids.add(labelUserSid);
                userLabelMap.put(labelUserSid,
                                new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                                    String.valueOf(labelUserSid)));
            }
            canUseUserSids = __getVariableUser(con, cmn120Model, canUseUserSids);
            //呼び出し元画面のプラグインを使用不可のユーザを除外する
            canUseUserSids = __getCanUsePluginUser(con, cmn120Model, canUseUserSids);

            for (Integer useUserSid : canUseUserSids) {
                labelListAdd.add(userLabelMap.get(useUserSid));
            }

        } else if (cmn120Model.getCmn120DspKbn() == 3) {
          //smlアカウントのみ

            //除外するアカウントSID
            ArrayList<String> usrSids = new ArrayList<String>();
            String[] userSids = cmn120Model.getCmn120userSid();

            if (userSids != null) {
                for (int i = 0; i < userSids.length; i++) {
                    if (userSids[i].indexOf("sac") != -1) {
                        usrSids.add(userSids[i].substring(3));
                    }
                }
            }
            userSids = cmn120Model.getCmn120banUserSid();
            if (userSids != null) {
                for (int i = 0; i < userSids.length; i++) {
                    if (userSids[i].indexOf("sac") != -1) {
                        usrSids.add(userSids[i].substring(3));
                    }
                }
            }

            //ショートメール作成アカウントを取得
            labelListAdd = dao.selectSmlAccount(usrSids);

        } else if (cmn120Model.getCmn120DspKbn() == 4) {
          //cirアカウントのみ

            //除外するアカウントSID
            ArrayList<String> usrSids = new ArrayList<String>();
            String[] userSids = cmn120Model.getCmn120userSid();
            if (userSids != null) {
                for (int i = 0; i < userSids.length; i++) {
                    if (userSids[i].indexOf("cac") != -1) {
                        usrSids.add(userSids[i].substring(3));
                    }
                }
            }
            userSids = cmn120Model.getCmn120banUserSid();
            if (userSids != null) {
                for (int i = 0; i < userSids.length; i++) {
                    if (userSids[i].indexOf("sac") != -1) {
                        usrSids.add(userSids[i].substring(3));
                    }
                }
            }
            //ショートメール作成アカウントを取得
            labelListAdd = dao.selectCirAccount(usrSids);
        }

        cmn120Model.setCmn120LeftUserList(labelListAdd);
        cmn120Model.setCmn120RightUserList(rightList);

    }

    /**
     * <br>[機  能] マイグループに所属するユーザを追加済みユーザに設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param cmn120Model パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setMyGroupUser(int userSid, Cmn120ParamModel cmn120Model, Connection con)
    throws SQLException {

        CmnMyGroupMsDao myGrpDao = new CmnMyGroupMsDao(con);
        List<CmnMyGroupMsModel> myGrpUserList
            = myGrpDao.getMyGroupMsInfo(userSid, cmn120Model.getCmn120MyGroupSid(),
                    cmn120Model.getCmn120userSid(), false);

        String[] myGrpUserSid = new String[myGrpUserList.size()];
        for (int idx = 0; idx < myGrpUserList.size(); idx++) {
            myGrpUserSid[idx] = String.valueOf(myGrpUserList.get(idx).getMgmSid());
        }

        myGrpUserSid = __getCanUsePluginUser(con, cmn120Model, myGrpUserSid);

        CommonBiz cmnBiz = new CommonBiz();
        cmn120Model.setCmn120userSid(cmnBiz.getAddMember(
                                              myGrpUserSid, cmn120Model.getCmn120userSid()));
    }

    /**
     * <br>[機  能] リクエストからユーザSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエストモデル
     * @return ユーザSID
     */
    public static int getUserSid(RequestModel reqMdl) {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        return sessionUsrSid;
    }
    /**
     * <br>[機  能] 指定されたユーザから除外対象のSIDを除いて返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cmn120Model パラメータ情報
     * @param userSids ユーザSID
     * @return ユーザSID
     */
    private String[] __getVariableUser(Connection con,
                                           Cmn120ParamModel cmn120Model,
                                           String[] userSids) {
        List<String> retList = new ArrayList<String>();
        if (cmn120Model.getCmn120banUserSid() == null) {
            return userSids;
        }
        List<String> banList = Arrays.asList(cmn120Model.getCmn120banUserSid());
        for (String str : userSids) {
            if (!banList.contains(str)) {
                retList.add(str);
            }
        }
        return retList.toArray(new String[retList.size()]);
    }
    /**
     * <br>[機  能] 指定されたユーザから除外対象のSIDを除いて返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cmn120Model パラメータ情報
     * @param userSids ユーザSID
     * @return ユーザSID
     */
    private List<Integer> __getVariableUser(Connection con,
                                           Cmn120ParamModel cmn120Model,
                                           List<Integer> userSids) {
        List<Integer> retList = new ArrayList<Integer>();
        if (cmn120Model.getCmn120banUserSid() == null) {
            return userSids;
        }
        List<String> banList = Arrays.asList(cmn120Model.getCmn120banUserSid());
        for (Integer sid : userSids) {
            if (!banList.contains(String.valueOf(sid))) {
                retList.add(sid);
            }
        }
        return retList;
    }

    /**
     * <br>[機  能] 指定されたユーザから対象プラグインを使用可能なユーザを選択して返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cmn120Model パラメータ情報
     * @param userSids ユーザSID
     * @return ユーザSID
     * @throws SQLException SQL実行時例外
     */
    private String[] __getCanUsePluginUser(Connection con,
                                           Cmn120ParamModel cmn120Model,
                                           String[] userSids)
    throws SQLException {
        if (userSids == null || userSids.length == 0) {
            return userSids;
        }

        List<Integer> userSidList = new ArrayList<Integer>();
        for (String userSid : userSids) {
            if (GSValidateUtil.isNumber(userSid)) {
                userSidList.add(new Integer(userSid));
            }
        }
        userSidList = __getCanUsePluginUser(con, cmn120Model, userSidList);

        String[] canUseUser = new String[userSidList.size()];
        for (int idx = 0; idx < userSidList.size(); idx++) {
            canUseUser[idx] = String.valueOf(userSidList.get(idx));
        }

        return canUseUser;
    }

    /**
     * <br>[機  能] 指定されたユーザから対象プラグインを使用可能なユーザを選択して返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cmn120Model パラメータ情報
     * @param userSidList ユーザSID
     * @return ユーザSID
     * @throws SQLException SQL実行時例外
     */
    private List<Integer> __getCanUsePluginUser(Connection con, Cmn120ParamModel cmn120Model,
                                                List<Integer> userSidList)
    throws SQLException {
        String backUrl = NullDefault.getString(cmn120Model.getCmn120BackUrl(), "");

        String pluginId = null;
        if (backUrl.indexOf("smail") >= 0) {
            pluginId = "smail";
        } else if (backUrl.indexOf("circular") >= 0) {
            pluginId = "circular";
        } else if (backUrl.indexOf("project") >= 0) {
            pluginId = "project";
        } else {
            return userSidList;
        }

        CommonBiz cmnBiz = new CommonBiz();
        return (ArrayList<Integer>) cmnBiz.getCanUsePluginUser(con, pluginId, userSidList);
    }

    /**
     * パラメータ.グループコンボ値が代表アカウントかを判定する
     * <br>[機  能]先頭文字に"sac"が有る場合は代表アカウント
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return boolean true:マイグループ false=通常のグループ
     */
    public static boolean isSmlAccount(String gpSid) {
        boolean ret = false;
        if (gpSid == null) {
            return ret;
        }
        // 置換対象文字列が存在する場所を取得
        int index = gpSid.indexOf("sac");

        // 先頭文字に"sac"が有る場合は代表アカウント
        if (index == 0) {
            return true;
        } else {
            return ret;
        }
    }

    /**
     * ショートメールアカウントユーザが選択されていた場合にアカウント情報を取得する
     * <br>[機  能]先頭文字に"sac"が有る場合は代表アカウント
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     * @param users 選択ユーザ
     * @return boolean true:マイグループ false=通常のグループ
     * @throws SQLException SQL実行時例外
     */
    private List<LabelValueBean> __getOtherSelUsr(
            Connection con, String[] users) throws SQLException {

        List<LabelValueBean> selUsrInf = new ArrayList<LabelValueBean>();
        Cmn120Dao dao = new Cmn120Dao(con);
        ArrayList<String> smlUsrSids = new ArrayList<String>();
        ArrayList<String> cirUsrSids = new ArrayList<String>();
        if (users != null && users.length > 0) {
            for (int i = 0; i < users.length; i++) {
                if (users[i].indexOf("sac") != -1) {
                    smlUsrSids.add(users[i].substring(3));
                }

                if (users[i].indexOf("cac") != -1) {
                    cirUsrSids.add(users[i].substring(3));
                }
            }
            if (smlUsrSids != null && smlUsrSids.size() > 0) {
                selUsrInf = dao.selectSmlSelAccount(smlUsrSids);
            }

            if (cirUsrSids != null && cirUsrSids.size() > 0) {
                selUsrInf = dao.selectCirSelAccount(cirUsrSids);
            }
        }

        return selUsrInf;
    }
    /**
    *
    * <br>[機  能] 選択した値がグループコンボ上にない場合に有効な値を返す
    * <br>[解  説]
    * <br>[備  考]
    * @param list ラベルリスト
    * @param nowSel 選択中ラベルID
    * @param defSel 初期ラベルID
    * @return 有効な選択値
    */
   public String getEnableSelectGroup(List<LabelValueBean> list
           , String nowSel
           , String defSel) {
       boolean nowVar = false;
       boolean defVar = false;
       if (list == null || list.size() <= 0) {
           return "";
       }
       for (LabelValueBean label : list) {
           if (label.getValue().equals(nowSel)) {
               nowVar = true;
               break;
           }
           if (label.getValue().equals(defSel)) {
               defVar = true;
           }
       }
       if (nowVar) {
           return nowSel;
       }
       if (defVar) {
           return defSel;
       }
       return list.get(0).getValue();
   }
}
