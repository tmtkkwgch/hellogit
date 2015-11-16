package jp.groupsession.v2.cmn.cmn230;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.struts.util.LabelValueBean;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.cmn220.Cmn220Biz;
import jp.groupsession.v2.cmn.cmn220.Cmn220UsrDspModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.CmnLabelValueModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;
import jp.groupsession.v2.usr.usr040.ShainSearchModel;

/**
 * <br>[機  能] 全グループから選択ポップアップのビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn230Biz {

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramModel パラメータ、出力値を保持するModel
     * @param con コネクション
     * @param sessionUserSid セッションユーザSID
     * @param gsMsg GsMessage
     * @throws SQLException SQL実行例外
     * @throws Exception 実行例外
     */
    public void setInitData(
            Cmn230ParamModel paramModel, Connection con, int sessionUserSid, GsMessage gsMsg)
    throws SQLException, Exception {

        //表示グループ取得--------------------------------------------------------------------//
        GroupBiz grpBiz = new GroupBiz();
        ArrayList<GroupModel> tree = grpBiz.getGroupTree(con);
        Map<String, GroupModel> groupMap = new HashMap<String, GroupModel>();

        for (int i = 0; i < tree.size(); i++) {
            GroupModel grpMdl = tree.get(i);
            int groupSid = grpMdl.getGroupSid();
            groupMap.put(String.valueOf(groupSid), grpMdl);
        }

        if (paramModel.getAdmGpFlg() != 0) {
            //システム管理グループを非表示
            tree.remove(GSConstUser.SID_ADMIN);
        }


        //マイグループ取得
        Map<String, CmnMyGroupModel> mGroupMap = new HashMap<String, CmnMyGroupModel>();
        if (paramModel.getMyGroupFlg() == 1) {
            //ユーザSIDからマイグループ情報を取得する
            CmnMyGroupDao cmgDao = new CmnMyGroupDao(con);
            List<CmnMyGroupModel> cmgList = cmgDao.getMyGroupList(sessionUserSid);

            //マイグループリストをセット
            ArrayList<CmnLabelValueModel> dspGrpList = new ArrayList<CmnLabelValueModel>();
            for (CmnMyGroupModel cmgMdl : cmgList) {
                dspGrpList.add(
                        new CmnLabelValueModel(cmgMdl.getMgpName(),
                                "M" + String.valueOf(cmgMdl.getMgpSid()), "1"));
                mGroupMap.put(String.valueOf(cmgMdl.getMgpSid()), cmgMdl);
            }
            paramModel.setMyGroupList(dspGrpList);
        }

        paramModel.setGroupList(tree);


        //追加用ユーザ一覧をセット------------------------------------------------------//


        //親画面から渡された選択ユーザSIDを取得(左)
        List<String> userSidsList = new ArrayList<String>();
        List<String> groupSidsList = new ArrayList<String>();
        if (paramModel.getSv_users() != null) {
            for (String sid : paramModel.getSv_users().split(",")) {
                if (!isUserSid(sid)) {
                    //ユーザSID
                    userSidsList.add(sid);
                } else {
                    //グループSID 先頭のGをはずして保存
                    groupSidsList.add(sid.replace("G", ""));
                }
            }

            if (userSidsList != null && !userSidsList.isEmpty()) {
                paramModel.setCmn230userSid(
                        (String[]) userSidsList.toArray(new String[userSidsList.size()]));
            }
            if (groupSidsList != null && !groupSidsList.isEmpty()) {
                paramModel.setCmn230groupSidadd(
                        (String[]) groupSidsList.toArray(new String[groupSidsList.size()]));
            }
        }

        //親画面から渡された選択ユーザSIDを取得（右）
        List<String> userSidsList2 = new ArrayList<String>();
        List<String> groupSidsList2 = new ArrayList<String>();
        if (paramModel.getSv_users2() != null) {
            for (String sid : paramModel.getSv_users2().split(",")) {
                if (!isUserSid(sid)) {
                    //ユーザSID
                    userSidsList2.add(sid);
                } else {
                    //グループSID 先頭のGをはずして保存
                    groupSidsList2.add(sid.replace("G", ""));
                }
            }

            if (userSidsList2 != null && !userSidsList2.isEmpty()) {
                paramModel.setCmn230userSid2(
                        (String[]) userSidsList2.toArray(new String[userSidsList2.size()]));
            }
            if (groupSidsList2 != null && !groupSidsList2.isEmpty()) {
                paramModel.setCmn230groupSidadd2(
                        (String[]) groupSidsList2.toArray(new String[groupSidsList2.size()]));
            }
        }

        boolean myGroupFlg = false;
        String dfGpSidStr =
                 String.valueOf((grpBiz.getDefaultGroupSid(sessionUserSid, con)));
        if (dfGpSidStr == null || dfGpSidStr.equals("")) {
            if (!tree.isEmpty() && tree != null) {
                dfGpSidStr = String.valueOf(tree.get(0).getGroupSid());
            }
        }
        String dfGpSidStrSv = dfGpSidStr;
        paramModel.setCmn230dfGpSidStrSv(dfGpSidStrSv);
        //表示グループ
        if (paramModel.getSelGroup() != null && !paramModel.getSelGroup().equals("")
         && !paramModel.getSelGroup().equals("-1") && !paramModel.getSelGroup().equals("-9")) {
            paramModel.setCmn230groupSid(paramModel.getSelGroup());
            paramModel.setSelGroupSv(paramModel.getSelGroup());
        }
        String dspGpSidStr = NullDefault.getString(paramModel.getCmn230groupSid(), dfGpSidStr);
        if (isMyGroupSid(dspGpSidStr)) {
            dspGpSidStr = getEnableSelectGroup(paramModel.getMyGroupList(),
                    dspGpSidStr,
                    null);

            paramModel.setCmn230dspGpSidSv(dspGpSidStr);

            dspGpSidStr = String.valueOf(getDspGroupSid(dspGpSidStr));
            paramModel.setCmn230groupSid(dspGpSidStr);
            myGroupFlg = true;
        } else {
            dspGpSidStr = String.valueOf(getEnableSelectTree(paramModel.getGroupList(),
                    Integer.parseInt(dspGpSidStr),
                    Integer.parseInt(dfGpSidStr)));
            paramModel.setCmn230dspGpSidSv(
                    dspGpSidStr);
            paramModel.setCmn230groupSid(dspGpSidStr);
        }

        //グループ一覧タブ表示判定
        if (paramModel.getSelGrpFlg() != 0) {
            paramModel.setCmn230TabFlg(1);
        }

        //追加先表示文字列-----------------------------------------------------------------------//
        if (paramModel.getPlginId().equals(GSConst.PLUGIN_ID_RINGI)) {
            paramModel.setCmn230LeftTitleName(gsMsg.getMessage("rng.42"));
            paramModel.setCmn230RightTitleName(gsMsg.getMessage("rng.35"));
        } else if (paramModel.getPlginId().equals(GSConst.PLUGINID_SCH)) {
            paramModel.setCmn230LeftTitleName(gsMsg.getMessage("cmn.add.edit.delete"));
            paramModel.setCmn230RightTitleName(gsMsg.getMessage("cmn.reading"));
        } else if (paramModel.getPlginId().equals(GSConst.PLUGIN_ID_RESERVE)) {
            paramModel.setCmn230LeftTitleName(gsMsg.getMessage("reserve.165"));
            paramModel.setCmn230RightTitleName(gsMsg.getMessage("cmn.reading.ng"));
        } else if (paramModel.getPlginId().equals(GSConst.PLUGIN_ID_RESERVE + "_2")) {
            paramModel.setCmn230LeftTitleName(gsMsg.getMessage("reserve.161"));
            paramModel.setCmn230RightTitleName(gsMsg.getMessage("cmn.reading.ok"));
        } else if (paramModel.getPlginId().equals(GSConst.PLUGIN_ID_FILE)) {
            paramModel.setCmn230LeftTitleName(gsMsg.getMessage("cmn.add.edit.delete"));
            paramModel.setCmn230RightTitleName(gsMsg.getMessage("cmn.reading"));
        } else if (paramModel.getPlginId().equals(GSConst.PLUGIN_ID_BULLETIN)) {
            paramModel.setCmn230LeftTitleName(gsMsg.getMessage("cmn.add.edit.delete"));
            paramModel.setCmn230RightTitleName(gsMsg.getMessage("cmn.reading"));
        } else if (paramModel.getPlginId().equals(GSConst.PLUGINID_WML)) {
            paramModel.setCmn230LeftTitleName(gsMsg.getMessage("cmn.add.edit.delete"));
            paramModel.setCmn230RightTitleName(gsMsg.getMessage("cmn.reading"));
        }

        //ユーザ一覧画面をセット------------------------------------------------------//
        if (paramModel.getCmn230TabMode() == 0) {
            __getUserTabData(paramModel, con, gsMsg, sessionUserSid,
                            myGroupFlg, groupMap, mGroupMap);
        }

        //グループ一覧画面をセット------------------------------------------------------//
        if (paramModel.getCmn230TabMode() == 1) {
            __getGroupTabData(paramModel, con, sessionUserSid, myGroupFlg, groupMap, mGroupMap);
        }

        //シングルコーテーションをエスケープしてセットし直す
        paramModel.setSvDomName(
                StringUtil.toSingleCortationEscape(paramModel.getSvDomName()));
        paramModel.setSvDomName2(
                StringUtil.toSingleCortationEscape(paramModel.getSvDomName2()));
        paramModel.setSubmitCmd(
                StringUtil.toSingleCortationEscape(paramModel.getSubmitCmd()));
        paramModel.setSelBoxName(
                StringUtil.toSingleCortationEscape(paramModel.getSelBoxName()));
    }

    /**
     * <br>[機  能] グループTAB情報取得
     * <br>[解  説]
     * <br>[備  考]
     * @param paramModel パラメータ、出力値を保持するModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @param myGroupFlg マイグループフラグ
     * @param groupMap マイグループマップ
     * @param mGroupMap マイグループマップ
     * @throws SQLException SQL実行例外
     */
    public void __getGroupTabData(Cmn230ParamModel paramModel,
                                     Connection con,
                                     int userSid,
                                     boolean myGroupFlg,
                                     Map<String, GroupModel> groupMap,
                                     Map<String, CmnMyGroupModel> mGroupMap
                                     ) throws SQLException {

        //表示グループを取得
        if (groupMap != null && !groupMap.isEmpty()) {
            List<GroupModel> topList = new ArrayList<GroupModel>();
            List<GroupModel> bottomList = new ArrayList<GroupModel>();
            List<GroupModel> bottomList2 = new ArrayList<GroupModel>();
            String[] groupSids = paramModel.getCmn230groupSidadd();
            String[] groupSids2 = paramModel.getCmn230groupSidadd2();
            //すでに選択されているグループを除外
            if (groupSids != null) {
                for (int i = 0; i < groupSids.length; i++) {
                    if (groupMap.get(groupSids[i].replace("G", "")) != null) {
                        bottomList.add(groupMap.get(groupSids[i].replace("G", "")));
                        groupMap.remove(groupSids[i].replace("G", ""));
                    }
                }
            }
            if (groupSids2 != null) {
                for (int i = 0; i < groupSids2.length; i++) {
                    if (groupMap.get(groupSids2[i].replace("G", "")) != null) {
                        bottomList2.add(groupMap.get(groupSids2[i].replace("G", "")));
                        groupMap.remove(groupSids2[i].replace("G", ""));
                    }
                }
            }

            //選択用のグループを取得
            Set<Entry<String, GroupModel>> set = groupMap.entrySet();
            Iterator<Entry<String, GroupModel>> it = set.iterator();
            while (it.hasNext()) {
                Entry<String, GroupModel> entry = (Entry<String, GroupModel>) it.next();
                topList.add((GroupModel) entry.getValue());
            }

            if (topList != null && !topList.isEmpty()) {
                paramModel.setCmn230TopGroupList(topList);
            }
            if (bottomList != null && !bottomList.isEmpty()) {
                paramModel.setCmn230BottomGroupList(bottomList);
            }
            if (bottomList2 != null && !bottomList2.isEmpty()) {
                paramModel.setCmn230BottomGroupList2(bottomList2);
            }

            //ソート処理
            final int sortTopKey = paramModel.getCmn230SortTopKeyGp();
            final int sortTop = paramModel.getCmn230SortTopKbnGp();
            if (topList != null && !topList.isEmpty()) {
                if (sortTopKey == -1) {
                    Collections.sort(topList, new Comparator<GroupModel>() {
                        public int compare(GroupModel t1, GroupModel t2) {

                          if (t1.getGroupSid() == 0 && t2.getGroupSid() == 0) {
                              return 0;
                          } else if (t1.getGroupSid() == 0) {
                              return 1 * sortTop;
                          } else if (t2.getGroupSid() == 0) {
                              return -1 * sortTop;
                          }
                          return String.valueOf(t1.getGroupSid())
                                    .compareTo(String.valueOf(t2.getGroupSid())) * sortTop;
                        }
                     });
                } else if (sortTopKey == 0) {
                    Collections.sort(topList, new Comparator<GroupModel>() {
                        public int compare(GroupModel t1, GroupModel t2) {

                          if (t1.getGroupId() == null && t2.getGroupId() == null) {
                              return 0;
                          } else if (t1.getGroupId() == null) {
                              return 1 * sortTop;
                          } else if (t2.getGroupId() == null) {
                              return -1 * sortTop;
                          }
                          return t1.getGroupId()
                                    .compareTo(t2.getGroupId()) * sortTop;
                        }
                     });
                } else {
                    Collections.sort(topList, new Comparator<GroupModel>() {
                        public int compare(GroupModel t1, GroupModel t2) {

                          if (t1.getGroupName() == null && t2.getGroupName() == null) {
                              return 0;
                          } else if (t1.getGroupName() == null) {
                              return 1 * sortTop;
                          } else if (t2.getGroupName() == null) {
                              return -1 * sortTop;
                          }
                          return t1.getGroupName().compareTo(t2.getGroupName()) * sortTop;
                        }
                     });
                }
            }
            final int sortBottomKey = paramModel.getCmn230SortBottomKeyGp();
            final int sortBottom = paramModel.getCmn230SortBottomKbnGp();
            if (bottomList != null && !bottomList.isEmpty()) {
                if (sortBottomKey == -1) {
                    Collections.sort(bottomList, new Comparator<GroupModel>() {
                        public int compare(GroupModel t1, GroupModel t2) {

                          if (t1.getGroupSid() == 0 && t2.getGroupSid() == 0) {
                              return 0;
                          } else if (t1.getGroupSid() == 0) {
                              return 1 * sortBottom;
                          } else if (t2.getGroupSid() == 0) {
                              return -1 * sortBottom;
                          }
                          return String.valueOf(t1.getGroupSid())
                                    .compareTo(String.valueOf(t2.getGroupSid())) * sortBottom;
                        }
                     });
                } else if (sortBottomKey == 0) {
                    Collections.sort(bottomList, new Comparator<GroupModel>() {
                        public int compare(GroupModel t1, GroupModel t2) {

                          if (t1.getGroupId() == null && t2.getGroupId() == null) {
                              return 0;
                          } else if (t1.getGroupId() == null) {
                              return 1 * sortBottom;
                          } else if (t2.getGroupId() == null) {
                              return -1 * sortBottom;
                          }
                          return t1.getGroupId()
                                    .compareTo(t2.getGroupId()) * sortBottom;
                        }
                     });
                } else {
                    Collections.sort(bottomList, new Comparator<GroupModel>() {
                        public int compare(GroupModel t1, GroupModel t2) {

                          if (t1.getGroupName() == null && t2.getGroupName() == null) {
                              return 0;
                          } else if (t1.getGroupName() == null) {
                              return 1 * sortBottom;
                          } else if (t2.getGroupName() == null) {
                              return -1 * sortBottom;
                          }
                          return t1.getGroupName().compareTo(t2.getGroupName()) * sortBottom;
                        }
                     });
                }
            }
            final int sortBottomKey2 = paramModel.getCmn230SortBottomKeyGp2();
            final int sortBottom2 = paramModel.getCmn230SortBottomKbnGp2();
            if (bottomList2 != null && !bottomList2.isEmpty()) {
                if (sortBottomKey2 == -1) {
                    Collections.sort(bottomList2, new Comparator<GroupModel>() {
                        public int compare(GroupModel t1, GroupModel t2) {

                          if (t1.getGroupSid() == 0 && t2.getGroupSid() == 0) {
                              return 0;
                          } else if (t1.getGroupSid() == 0) {
                              return 1 * sortBottom2;
                          } else if (t2.getGroupSid() == 0) {
                              return -1 * sortBottom2;
                          }
                          return String.valueOf(t1.getGroupSid())
                                    .compareTo(String.valueOf(t2.getGroupSid())) * sortBottom2;
                        }
                     });
                } else if (sortBottomKey2 == 0) {
                    Collections.sort(bottomList2, new Comparator<GroupModel>() {
                        public int compare(GroupModel t1, GroupModel t2) {

                          if (t1.getGroupId() == null && t2.getGroupId() == null) {
                              return 0;
                          } else if (t1.getGroupId() == null) {
                              return 1 * sortBottom2;
                          } else if (t2.getGroupId() == null) {
                              return -1 * sortBottom2;
                          }
                          return t1.getGroupId()
                                    .compareTo(t2.getGroupId()) * sortBottom2;
                        }
                     });
                } else {
                    Collections.sort(bottomList2, new Comparator<GroupModel>() {
                        public int compare(GroupModel t1, GroupModel t2) {

                          if (t1.getGroupName() == null && t2.getGroupName() == null) {
                              return 0;
                          } else if (t1.getGroupName() == null) {
                              return 1 * sortBottom2;
                          } else if (t2.getGroupName() == null) {
                              return -1 * sortBottom2;
                          }
                          return t1.getGroupName().compareTo(t2.getGroupName()) * sortBottom2;
                        }
                     });
                }
            }
        }
    }

    /**
     * <br>[機  能] ユーザTAB情報取得
     * <br>[解  説]
     * <br>[備  考]
     * @param paramModel パラメータ、出力値を保持するModel
     * @param con コネクション
     * @param gsMsg GsMessage
     * @param userSid ログインユーザSID
     * @param myGroupFlg マイグループフラグ
     * @param groupMap マイグループマップ
     * @param mGroupMap マイグループマップ
     * @throws SQLException SQL実行例外
     * @throws Exception 実行例外
     */
    public void __getUserTabData(Cmn230ParamModel paramModel,
                                     Connection con,
                                     GsMessage gsMsg,
                                     int userSid,
                                     boolean myGroupFlg,
                                     Map<String, GroupModel> groupMap,
                                     Map<String, CmnMyGroupModel> mGroupMap
                                     ) throws SQLException, Exception {

        //除外するユーザSID(左)
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        String[] userSids = paramModel.getCmn230userSid();
        if (userSids != null) {
            for (int i = 0; i < userSids.length; i++) {
                usrSids.add(new Integer(NullDefault.getInt(userSids[i], -1)));
            }
        }

        //除外するユーザSID(右)
        ArrayList<Integer> usrSids2 = new ArrayList<Integer>();
        String[] userSids2 = paramModel.getCmn230userSid2();
        if (userSids2 != null) {
            for (int i = 0; i < userSids2.length; i++) {
                usrSids2.add(new Integer(NullDefault.getInt(userSids2[i], -1)));
            }
        }

        //ユーザ一覧(選択用)を取得
        UserBiz userBiz = new UserBiz();
        List<CmnUsrmInfModel> usList = null;

        //左と右で選択されているユーザ
        ArrayList<Integer> usrSids3 = new ArrayList<Integer>();
        if (usrSids != null && !usrSids.isEmpty()) {
            for (int i = 0; i < userSids.length; i++) {
                usrSids3.add(new Integer(NullDefault.getInt(userSids[i], -1)));
            }
        }
        if (usrSids2 != null && !usrSids2.isEmpty()) {
            for (int i = 0; i < userSids2.length; i++) {
                usrSids3.add(new Integer(NullDefault.getInt(userSids2[i], -1)));
            }
        }

        //親画面の選択ユーザを除外
        if (!usrSids.isEmpty()) {
            if (paramModel.getSelUserSid() != null && !paramModel.getSelUserSid().equals("")) {
                usrSids.add(Integer.valueOf(paramModel.getSelUserSid()));
            }
        }
        if (!usrSids2.isEmpty()) {
            if (paramModel.getSelUserSid() != null && !paramModel.getSelUserSid().equals("")) {
                usrSids2.add(Integer.valueOf(paramModel.getSelUserSid()));
            }
        }

        if (paramModel.getSelUserSid() != null && !paramModel.getSelUserSid().equals("")) {
            usrSids3.add(Integer.valueOf(paramModel.getSelUserSid()));
        }


        if (paramModel.getCmn230SearchFlg() == 0) {
            int gpSid = Integer.valueOf(paramModel.getCmn230groupSid());
            usList
            = userBiz.getBelongUserList(con, gpSid, usrSids3, userSid, myGroupFlg);
        } else {
            usList = new ArrayList<CmnUsrmInfModel>();
            //検索条件作成
            ShainSearchModel searchModel = new ShainSearchModel();
            searchModel.setShainno(paramModel.getCmn230SearchStr());
            searchModel.setSei(paramModel.getCmn230SearchStr());
            searchModel.setMei(paramModel.getCmn230SearchStr());
            searchModel.setSeikn(paramModel.getCmn230SearchStr());
            searchModel.setMeikn(paramModel.getCmn230SearchStr());

            String keyWord = paramModel.getCmn230SearchStr();

            UserSearchDao udao = new UserSearchDao(con);

            //検索実行
            List<CmnUsrmInfModel> usListBf = null;
            usListBf = udao.getSyousaiSearchList2(keyWord, searchModel);
            paramModel.setCmn230DspGrpName(gsMsg.getMessage("cmn.search.result"));
            Map<Integer, CmnUsrmInfModel> searchSids = new HashMap<Integer, CmnUsrmInfModel>();
            if (!usListBf.isEmpty()) {
                for (CmnUsrmInfModel usMdl : usListBf) {
                    searchSids.put(usMdl.getUsrSid(), usMdl);
                }
            }
            if (!searchSids.isEmpty() && usrSids3 != null) {
                for (int sid : usrSids3) {
                    if (searchSids.get(sid) != null) {
                        searchSids.remove(sid);
                    }
                }
            }
            if (searchSids != null && !searchSids.isEmpty()) {
                Set<Entry<Integer, CmnUsrmInfModel>> set = searchSids.entrySet();
                Iterator<Entry<Integer, CmnUsrmInfModel>> it = set.iterator();
                while (it.hasNext()) {
                    Entry<Integer, CmnUsrmInfModel> entry
                        = (Entry<Integer, CmnUsrmInfModel>) it.next();
                    usList.add((CmnUsrmInfModel) entry.getValue());
                }
            }
        }

        //ユーザ一覧(選択済み 左)を取得
        Cmn220Biz biz = new Cmn220Biz();
        List<CmnUsrmInfModel> bottomList = null;
        ArrayList<Cmn220UsrDspModel> bottomPlusList = null;
        CmnUsrmInfDao usrmInfDao = new CmnUsrmInfDao(con);
        if (usrSids != null && !usrSids.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int sid : usrSids) {
                if (paramModel.getSelUserSid() != null && !paramModel.getSelUserSid().equals("")) {
                    //画面操作ユーザがいる場合
                   if (sid != Integer.valueOf(paramModel.getSelUserSid())) {
                       list.add(Integer.valueOf(sid));
                   }
                } else {
                    list.add(Integer.valueOf(sid));
                }
            }
            bottomList = new ArrayList<CmnUsrmInfModel>();
            if (list != null && !list.isEmpty()) {
                bottomList = usrmInfDao.getUserList(list);
            }
            bottomPlusList = biz.getDspMdlList(con, bottomList);
            paramModel.setCmn230BottomUserList(bottomPlusList);
        }

        //ユーザ一覧(選択済み 右)を取得
        List<CmnUsrmInfModel> bottomList2 = null;
        ArrayList<Cmn220UsrDspModel> bottomPlusList2 = null;
        if (usrSids2 != null && !usrSids2.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int sid : usrSids2) {
                if (paramModel.getSelUserSid() != null && !paramModel.getSelUserSid().equals("")) {
                    //画面操作ユーザがいる場合
                   if (sid != Integer.valueOf(paramModel.getSelUserSid())) {
                       list.add(Integer.valueOf(sid));
                   }
                } else {
                    list.add(Integer.valueOf(sid));
                }
            }
            bottomList2 = new ArrayList<CmnUsrmInfModel>();
            if (list != null && !list.isEmpty()) {
                bottomList2 = usrmInfDao.getUserList(list);
            }
            bottomPlusList2 = biz.getDspMdlList(con, bottomList2);
            paramModel.setCmn230BottomUserList2(bottomPlusList2);
        }

        List<Cmn220UsrDspModel> usPlusList = null;

        //選択グループ名
        if (paramModel.getCmn230SearchFlg() == 0) {
            int gpSid = Integer.valueOf(paramModel.getCmn230groupSid());
            if (myGroupFlg) {
                if (!mGroupMap.isEmpty()) {
                    CmnMyGroupModel mGpMdl = mGroupMap.get(String.valueOf(gpSid));
                    if (mGpMdl != null) {
                        paramModel.setCmn230DspGrpName(mGpMdl.getMgpName());
                    }
                }
            } else {
                if (!groupMap.isEmpty()) {
                    GroupModel gpMdl = groupMap.get(String.valueOf(gpSid));
                    if (gpMdl != null) {
                        paramModel.setCmn230DspGrpName(gpMdl.getGroupName());
                    }
                }
            }
            if (usList != null && !usList.isEmpty()) {
                usPlusList = biz.getDspMdlList(con, usList);
                paramModel.setCmn230TopUserList(usPlusList);

            }
            paramModel.setCmn230groupSid(paramModel.getCmn230dspGpSidSv());
        } else {
            paramModel.setCmn230DspGrpName(gsMsg.getMessage("cmn.search.result"));
            if (usList != null && !usList.isEmpty()) {
                usPlusList = biz.getDspMdlList(con, usList);
                paramModel.setCmn230TopUserList(usPlusList);
            }
            paramModel.setCmn230groupSid(NullDefault.getString(paramModel.getSelGroupSv(),
                    paramModel.getCmn230dfGpSidStrSv()));
        }

        //ソート処理(上)-----------------------------------------------------------------------//
        final int sortTopKey = paramModel.getCmn230SortTopKey();
        final int sortTop = paramModel.getCmn230SortTopKbn();
        if (usPlusList != null && !usPlusList.isEmpty()) {
            if (sortTopKey == 0) {
                Collections.sort(usPlusList, new Comparator<Cmn220UsrDspModel>() {
                    public int compare(Cmn220UsrDspModel t1, Cmn220UsrDspModel t2) {

                      if (t1.getUsiSyainNo() == null && t2.getUsiSyainNo() == null) {
                          return 0;
                      } else if (t1.getUsiSyainNo() == null) {
                          return 1 * sortTop;
                      } else if (t2.getUsiSyainNo() == null) {
                          return -1 * sortTop;
                      }
                      return t1.getUsiSyainNo().compareTo(t2.getUsiSyainNo()) * sortTop;
                    }
                 });
            } else {
                Collections.sort(usPlusList, new Comparator<Cmn220UsrDspModel>() {
                    public int compare(Cmn220UsrDspModel t1, Cmn220UsrDspModel t2) {

                      if (t1.getUsiSeiKn() == null && t2.getUsiSeiKn() == null) {
                          return 0;
                      } else if (t1.getUsiSeiKn() == null) {
                          return 1 * sortTop;
                      } else if (t2.getUsiSeiKn() == null) {
                          return -1 * sortTop;
                      }
                      return t1.getUsiSeiKn().compareTo(t2.getUsiSeiKn()) * sortTop;
                    }
                 });
            }
        }
        //ソート処理(左)-----------------------------------------------------------------------//
        final int sortBottomKey = paramModel.getCmn230SortBottomKey();
        final int sortBottom = paramModel.getCmn230SortBottomKbn();
        if (bottomList != null
                && !bottomList.isEmpty()) {
            if (sortBottomKey == 0) {
                Collections.sort(bottomPlusList, new Comparator<Cmn220UsrDspModel>() {
                    public int compare(Cmn220UsrDspModel t1, Cmn220UsrDspModel t2) {

                      if (t1.getUsiSyainNo() == null && t2.getUsiSyainNo() == null) {
                          return 0;
                      } else if (t1.getUsiSyainNo() == null) {
                          return 1 * sortBottom;
                      } else if (t2.getUsiSyainNo() == null) {
                          return -1 * sortBottom;
                      }
                      return t1.getUsiSyainNo().compareTo(t2.getUsiSyainNo()) * sortBottom;
                    }
                 });
            } else {
                Collections.sort(bottomPlusList, new Comparator<Cmn220UsrDspModel>() {
                    public int compare(Cmn220UsrDspModel t1, Cmn220UsrDspModel t2) {

                      if (t1.getUsiSeiKn() == null && t2.getUsiSeiKn() == null) {
                          return 0;
                      } else if (t1.getUsiSeiKn() == null) {
                          return 1 * sortBottom;
                      } else if (t2.getUsiSeiKn() == null) {
                          return -1 * sortBottom;
                      }
                      return t1.getUsiSeiKn().compareTo(t2.getUsiSeiKn()) * sortBottom;
                    }
                 });
            }
        }
        //ソート処理(右)-----------------------------------------------------------------------//
        final int sortBottomKey2 = paramModel.getCmn230SortBottomKey2();
        final int sortBottom2 = paramModel.getCmn230SortBottomKbn2();
        if (bottomPlusList2 != null
                && !bottomPlusList2.isEmpty()) {
            if (sortBottomKey2 == 0) {
                Collections.sort(bottomPlusList2, new Comparator<Cmn220UsrDspModel>() {
                    public int compare(Cmn220UsrDspModel t1, Cmn220UsrDspModel t2) {

                      if (t1.getUsiSyainNo() == null && t2.getUsiSyainNo() == null) {
                          return 0;
                      } else if (t1.getUsiSyainNo() == null) {
                          return 1 * sortBottom2;
                      } else if (t2.getUsiSyainNo() == null) {
                          return -1 * sortBottom2;
                      }
                      return t1.getUsiSyainNo().compareTo(t2.getUsiSyainNo()) * sortBottom2;
                    }
                 });
            } else {
                Collections.sort(bottomPlusList2, new Comparator<Cmn220UsrDspModel>() {
                    public int compare(Cmn220UsrDspModel t1, Cmn220UsrDspModel t2) {

                      if (t1.getUsiSeiKn() == null && t2.getUsiSeiKn() == null) {
                          return 0;
                      } else if (t1.getUsiSeiKn() == null) {
                          return 1 * sortBottom2;
                      } else if (t2.getUsiSeiKn() == null) {
                          return -1 * sortBottom2;
                      }
                      return t1.getUsiSeiKn().compareTo(t2.getUsiSeiKn()) * sortBottom2;
                    }
                 });
            }
        }
    }

    /**
     * フォーム情報のグループコンボ値がグループSIDかマイグループSIDかを判定する
     * <br>[機  能]先頭文字に"M"が有る場合、マイグループSID
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return boolean true:マイグループ false=通常のグループ
     */
    public static boolean isMyGroupSid(String gpSid) {
        boolean ret = false;
        if (gpSid == null) {
            return ret;
        }
        // 置換対象文字列が存在する場所を取得
        int index = gpSid.indexOf("M");

        // 先頭文字に"M"が有る場合はマイグループ
        if (index == 0) {
            return true;
        } else {
            return ret;
        }
    }
    /**
     * フォーム情報のグループコンボ値からグループSID又はマイグループSIDを取得する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param gpSid グループSID
     * @return int グループSID又はマイグループSID
     */
    public static int getDspGroupSid(String gpSid) {
        int ret = 0;
        if (gpSid == null) {
            return ret;
        }

        if (isMyGroupSid(gpSid)) {
            return Integer.parseInt(gpSid.substring(1));
        } else {
            return Integer.parseInt(gpSid);
        }
    }
    /**
     * フォーム情報のグループコンボ値がグループSIDかユーザSIDかを判定する
     * <br>[機  能]先頭文字に"M"が有る場合、マイグループSID
     * <br>[解  説]
     * <br>[備  考]
     * @param sid ユーザorグループSID
     * @return boolean true:マイグループ false=通常のグループ
     */
    public static boolean isUserSid(String sid) {
        boolean ret = false;
        if (sid == null) {
            return ret;
        }
        // 置換対象文字列が存在する場所を取得
        int index = sid.indexOf("G");

        // 先頭文字に"G"が有る場合はグループ
        if (index == 0) {
            return true;
        } else {
            return ret;
        }
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
   public String getEnableSelectGroup(List<CmnLabelValueModel> list
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
   /**
   *
   * <br>[機  能] 選択した値がグループツリー上にない場合に有効な値を返す
   * <br>[解  説]
   * <br>[備  考]
   * @param list ラベルリスト
   * @param nowSel 選択中ラベルID
   * @param defSel 初期ラベルID
   * @return 有効な選択値
   */
  public int getEnableSelectTree(List<GroupModel> list
          , int nowSel
          , int defSel) {
      boolean nowVar = false;
      boolean defVar = false;
      if (list == null || list.size() <= 0) {
          return -1;
      }
      for (GroupModel label : list) {

          if (nowSel == label.getGroupSid()) {
              nowVar = true;
              break;
          }
          if (defSel == label.getGroupSid()) {
              defVar = true;
          }
      }
      if (nowVar) {
          return nowSel;
      }
      if (defVar) {
          return defSel;
      }
      return list.get(0).getGroupSid();
  }
}