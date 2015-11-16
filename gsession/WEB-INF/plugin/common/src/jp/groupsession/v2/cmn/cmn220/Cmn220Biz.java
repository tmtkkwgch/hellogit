package jp.groupsession.v2.cmn.cmn220;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.CmnLabelValueModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;
import jp.groupsession.v2.usr.usr040.ShainSearchModel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] グループ一覧ポップアップのビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn220Biz {

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn220Model パラメータ、出力値を保持するModel
     * @param con コネクション
     * @param sessionUserSid セッションユーザSID
     * @param gsMsg GsMessage
     * @throws SQLException SQL実行例外
     * @throws Exception 実行例外
     */
    public void setInitData(
            Cmn220ParamModel cmn220Model, Connection con, int sessionUserSid, GsMessage gsMsg)
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

        if (cmn220Model.getAdmGpFlg() != 0) {
            //システム管理グループを非表示
            tree.remove(GSConstUser.SID_ADMIN);
            groupMap.remove(String.valueOf(GSConstUser.SID_ADMIN));
        }
        //グループ一覧使用不可設定
        List<String> disableGroupSids = new ArrayList<String>();
        if (cmn220Model.getCmn220disableGroupSid() != null) {
            disableGroupSids = Arrays.asList(cmn220Model.getCmn220disableGroupSid());
        }
        List<Integer> groupDisabledKbns = new ArrayList<Integer>();
        for (GroupModel grp : tree) {
            if (disableGroupSids.contains(String.valueOf(grp.getGroupSid()))) {
                groupDisabledKbns.add(1);
            } else {
                groupDisabledKbns.add(0);
            }
        }
        cmn220Model.setGroupDisableKbnList(groupDisabledKbns);
        //マイグループ取得
        Map<String, CmnMyGroupModel> mGroupMap = new HashMap<String, CmnMyGroupModel>();
        if (cmn220Model.getMyGroupFlg() == 1) {
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
            cmn220Model.setMyGroupList(dspGrpList);

        }

        cmn220Model.setGroupList(tree);


        //親画面から渡された選択ユーザSIDを取得
        List<String> userSidsList = new ArrayList<String>();
        List<String> groupSidsList = new ArrayList<String>();
        if (cmn220Model.getSv_users() != null) {
            for (String sid : cmn220Model.getSv_users().split(",")) {
                if (!isUserSid(sid)) {
                    //ユーザSID
                    userSidsList.add(sid);
                } else {
                    //グループSID 先頭のGをはずして保存
                    groupSidsList.add(sid.replace("G", ""));
                }
            }

            if (userSidsList != null && !userSidsList.isEmpty()) {
                cmn220Model.setCmn220userSid(
                        (String[]) userSidsList.toArray(new String[userSidsList.size()]));
            }
            if (groupSidsList != null && !groupSidsList.isEmpty()) {
                cmn220Model.setCmn220groupSidadd(
                        (String[]) groupSidsList.toArray(new String[groupSidsList.size()]));
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
        cmn220Model.setCmn220dfGpSidStrSv(dfGpSidStr);
        //表示グループ
        if (cmn220Model.getSelGroup() != null && !cmn220Model.getSelGroup().equals("")
         && !cmn220Model.getSelGroup().equals("-1") && !cmn220Model.getSelGroup().equals("-9")) {
            cmn220Model.setCmn220groupSid(cmn220Model.getSelGroup());
            cmn220Model.setSelGroupSv(cmn220Model.getSelGroup());
        }
        String dspGpSidStr = NullDefault.getString(cmn220Model.getCmn220groupSid(), dfGpSidStr);

        if (isMyGroupSid(dspGpSidStr)) {
            dspGpSidStr = getEnableSelectGroup(cmn220Model.getMyGroupList(),
                    dspGpSidStr,
                    null);

            cmn220Model.setCmn220dspGpSidSv(dspGpSidStr);
            dspGpSidStr = String.valueOf(getDspGroupSid(dspGpSidStr));
            cmn220Model.setCmn220groupSid(dspGpSidStr);
            myGroupFlg = true;
        } else if (NullDefault.getInt(dspGpSidStr, -1) >= 0) {
            dspGpSidStr = String.valueOf(getEnableSelectTree(cmn220Model.getGroupList(),
                    Integer.parseInt(dspGpSidStr),
                    Integer.parseInt(dfGpSidStr)));
            cmn220Model.setCmn220dspGpSidSv(dspGpSidStr);
            cmn220Model.setCmn220groupSid(dspGpSidStr);
        }

        //グループ一覧タブ表示判定
        if (cmn220Model.getSelGrpFlg() != 0) {
            cmn220Model.setCmn220TabFlg(1);
        }

        //ユーザ一覧画面をセット------------------------------------------------------//
        if (cmn220Model.getCmn220TabMode() == 0) {
            __getUserTabData(cmn220Model, con, gsMsg, sessionUserSid,
                            myGroupFlg, groupMap, mGroupMap);
        }

        //グループ一覧画面をセット------------------------------------------------------//
        if (cmn220Model.getCmn220TabMode() == 1) {
            __getGroupTabData(cmn220Model, con, sessionUserSid, myGroupFlg, groupMap, mGroupMap);
        }

        //シングルコーテーションをエスケープしてセットし直す
        cmn220Model.setSvDomName(
                StringUtil.toSingleCortationEscape(cmn220Model.getSvDomName()));
        cmn220Model.setSubmitCmd(
                StringUtil.toSingleCortationEscape(cmn220Model.getSubmitCmd()));
        cmn220Model.setSelBoxName(
                StringUtil.toSingleCortationEscape(cmn220Model.getSelBoxName()));
    }

    /**
     * <br>[機  能] グループTAB情報取得
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn220Model パラメータ、出力値を保持するModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @param myGroupFlg マイグループフラグ
     * @param groupMap マイグループマップ
     * @param mGroupMap マイグループマップ
     * @throws SQLException SQL実行例外
     */
    public void __getGroupTabData(Cmn220ParamModel cmn220Model,
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
            String[] groupSids = cmn220Model.getCmn220groupSidadd();
            //すでに選択されているグループを除外
            if (groupSids != null) {
                for (int i = 0; i < groupSids.length; i++) {
                    if (groupMap.get(groupSids[i].replace("G", "")) != null) {
                        bottomList.add(groupMap.get(groupSids[i].replace("G", "")));
                        groupMap.remove(groupSids[i].replace("G", ""));
                    }
                }
            }
            //グループ選択不可グループを除外
            if (cmn220Model.getCmn220banGroupSid() != null) {
                List<String> banGroupSids = new ArrayList<String>();
                banGroupSids = Arrays.asList(cmn220Model.getCmn220banGroupSid());
                for (String sid : banGroupSids) {
                    if (groupMap.get(sid) != null) {
                        groupMap.remove(sid);
                    }
                }

            }

            //選択用のグループを取得
            Set<Entry<String, GroupModel>> set = groupMap.entrySet();
            Iterator<Entry<String, GroupModel>> it = set.iterator();
            while (it.hasNext()) {
                Entry<String, GroupModel> entry = it.next();
                topList.add((GroupModel) entry.getValue());
            }

            if (topList != null && !topList.isEmpty()) {
                cmn220Model.setCmn220TopGroupList(topList);
            }
            if (bottomList != null && !bottomList.isEmpty()) {
                cmn220Model.setCmn220BottomGroupList(bottomList);
            }

            //ソート処理
            final int sortTopKey = cmn220Model.getCmn220SortTopKeyGp();
            final int sortTop = cmn220Model.getCmn220SortTopKbnGp();
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
            final int sortBottomKey = cmn220Model.getCmn220SortBottomKeyGp();
            final int sortBottom = cmn220Model.getCmn220SortBottomKbnGp();
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
        }
    }


    /**
     * <br>[機  能] ユーザTAB情報取得
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn220Model パラメータ、出力値を保持するModel
     * @param con コネクション
     * @param gsMsg GsMessage
     * @param userSid ログインユーザSID
     * @param myGroupFlg マイグループフラグ
     * @param groupMap マイグループマップ
     * @param mGroupMap マイグループマップ
     * @throws Exception 実行例外
     */
    public void __getUserTabData(Cmn220ParamModel cmn220Model,
                                 Connection con,
                                 GsMessage gsMsg,
                                 int userSid,
                                 boolean myGroupFlg,
                                 Map<String, GroupModel> groupMap,
                                 Map<String, CmnMyGroupModel> mGroupMap
                                 ) throws Exception {


        //除外するユーザSID
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        String[] userSids = cmn220Model.getCmn220userSid();
        if (userSids != null) {
            for (int i = 0; i < userSids.length; i++) {
                usrSids.add(new Integer(NullDefault.getInt(userSids[i], -1)));
            }
        }
        String[] banUserSids = cmn220Model.getCmn220banUserSid();
        if (banUserSids != null) {
            for (int i = 0; i < banUserSids.length; i++) {
                usrSids.add(new Integer(NullDefault.getInt(banUserSids[i], -1)));
            }
        }

        //ユーザ一覧(選択用)を取得
        UserBiz userBiz = new UserBiz();
        List<CmnUsrmInfModel> usList = null;
        if (!usrSids.isEmpty()) {
            if (cmn220Model.getSelUserSid() != null) {
                usrSids.add(Integer.valueOf(cmn220Model.getSelUserSid()));
            }
        }

        UserSearchDao udao = new UserSearchDao(con);

        if (cmn220Model.getCmn220SearchFlg() == 0) {


            int gpSid = Integer.valueOf(cmn220Model.getCmn220groupSid());
            usList
            = userBiz.getBelongUserList(con, gpSid, usrSids, userSid, myGroupFlg);
        } else {
            usList = new ArrayList<CmnUsrmInfModel>();
            //検索条件作成
            ShainSearchModel searchModel = new ShainSearchModel();


            String keyWord = cmn220Model.getCmn220SearchStr();

            //検索実行
            List<CmnUsrmInfModel> usListBf = null;
            usListBf = udao.getSyousaiSearchList2(keyWord, searchModel);
            cmn220Model.setCmn220DspGrpName(gsMsg.getMessage("cmn.search.result"));
            Map<Integer, CmnUsrmInfModel> searchSids = new HashMap<Integer, CmnUsrmInfModel>();
            if (!usListBf.isEmpty()) {
                for (CmnUsrmInfModel usMdl : usListBf) {
                    searchSids.put(usMdl.getUsrSid(), usMdl);
                }
            }
            if (!searchSids.isEmpty() && usrSids != null) {
                for (int sid : usrSids) {
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


        //ユーザ一覧(選択済み)を取得
        List<CmnUsrmInfModel> bottomList = null;
        ArrayList<Cmn220UsrDspModel> bottomPlusList = null;
        CmnUsrmInfDao usrmInfDao = new CmnUsrmInfDao(con);
        if (usrSids != null && !usrSids.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            if (cmn220Model.getSelUserSid() != null) {
                //画面操作ユーザがいる場合
                usrSids.remove(Integer.valueOf(cmn220Model.getSelUserSid()));
            }
            List<String> banUserSidList = new ArrayList<String>();
            if (banUserSids != null) {
                banUserSidList.addAll(Arrays.asList(banUserSids));
            }

            for (int sid : usrSids) {
                if (!banUserSidList.contains(String.valueOf(sid))) {
                    list.add(Integer.valueOf(sid));
                }
            }
            bottomList = new ArrayList<CmnUsrmInfModel>();
            bottomList = usrmInfDao.getUserList(list);

            bottomPlusList = getDspMdlList(con, bottomList);
            cmn220Model.setCmn220BottomUserList(bottomPlusList);
        }
        ArrayList<Cmn220UsrDspModel> usPlusList = null;
        //選択グループ名
        if (cmn220Model.getCmn220SearchFlg() == 0) {
            int gpSid = Integer.valueOf(cmn220Model.getCmn220groupSid());
            if (myGroupFlg) {
                if (!mGroupMap.isEmpty()) {
                    CmnMyGroupModel mGpMdl = mGroupMap.get(String.valueOf(gpSid));
                    if (mGpMdl != null) {
                        cmn220Model.setCmn220DspGrpName(mGpMdl.getMgpName());
                    }
                }
            } else {
                if (!groupMap.isEmpty()) {
                    GroupModel gpMdl = groupMap.get(String.valueOf(gpSid));
                    if (gpMdl != null) {
                        cmn220Model.setCmn220DspGrpName(gpMdl.getGroupName());
                    }
                }
            }
            if (usList != null && !usList.isEmpty()) {
                usPlusList = getDspMdlList(con, usList);
                cmn220Model.setCmn220TopUserList(usPlusList);
            }
            cmn220Model.setCmn220groupSid(cmn220Model.getCmn220dspGpSidSv());
        } else {
            cmn220Model.setCmn220DspGrpName(gsMsg.getMessage("cmn.search.result"));
            if (usList != null && !usList.isEmpty()) {
                usPlusList = getDspMdlList(con, usList);
                cmn220Model.setCmn220TopUserList(usPlusList);
            }
            cmn220Model.setCmn220groupSid(NullDefault.getString(cmn220Model.getSelGroupSv(),
                    cmn220Model.getCmn220dfGpSidStrSv()));
        }

        //ソート処理
        final int sortTopKey = cmn220Model.getCmn220SortTopKey();
        final int sortTop = cmn220Model.getCmn220SortTopKbn();
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
        final int sortBottomKey = cmn220Model.getCmn220SortBottomKey();
        final int sortBottom = cmn220Model.getCmn220SortBottomKbn();
        if (bottomPlusList != null
                && !bottomPlusList.isEmpty()) {
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
    }

    /**
     * <br>[機  能] フォーム情報のグループコンボ値がグループSIDかマイグループSIDかを判定する
     * <br>[解  説] 先頭文字に"M"が有る場合、マイグループSID
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
     * <br>[機  能] フォーム情報のグループコンボ値がグループSIDかユーザSIDかを判定する
     * <br>[解  説] 先頭文字に"M"が有る場合、マイグループSID
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
     * <br>[機  能] フォーム情報のグループコンボ値からグループSID又はマイグループSIDを取得する
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
     * <br>[機  能] ユーザリストに所属グループ情報を付加する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param ulist ユーザリスト
     * @throws SQLException SQL実行時例外
     * @throws Exception 実行時例外
     * @return 表示リスト
     */
    public ArrayList<Cmn220UsrDspModel> getDspMdlList(
            Connection con, List<CmnUsrmInfModel> ulist)
            throws SQLException, Exception {

        ArrayList<Cmn220UsrDspModel> ret = new ArrayList<Cmn220UsrDspModel>();
        Cmn220UsrDspModel dspMdl = null;

        CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
        CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();

        GroupDao dao = new GroupDao(con);
        ArrayList<GroupModel> gpList = null;
        if (sortMdl.getCscGroupSkbn() == GSConst.GROUPCMB_SKBN_SET) {
            gpList = dao.getGroupTree(sortMdl);
        } else {
            gpList = dao.getGroupList(sortMdl);
        }

        for (CmnUsrmInfModel mdl : ulist) {
            dspMdl = new Cmn220UsrDspModel();

            //CmnUsrmInfModel → Cmn220UsrDspModel パラメータのコピー
            BeanUtils.copyProperties(dspMdl, mdl);

            CmnBelongmDao belongDao = new CmnBelongmDao(con);
            List<Integer> belongList = belongDao.selectUserBelongGroupSid(mdl.getUsrSid());
            ArrayList<GroupModel> groupList = new ArrayList<GroupModel>();

            for (GroupModel gpMdl : gpList) {
                if (belongList.indexOf(new Integer(gpMdl.getGroupSid())) >= 0) {
                    groupList.add(gpMdl);
                }

            }
            dspMdl.setBelongGrpList(groupList);

            ret.add(dspMdl);
        }

        return ret;
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