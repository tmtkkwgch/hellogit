package jp.groupsession.v2.ntp.ntp095;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.dao.NtpPriConfDao;
import jp.groupsession.v2.ntp.dao.NtpSmlMemberDao;
import jp.groupsession.v2.ntp.model.NtpAdmConfModel;
import jp.groupsession.v2.ntp.model.NtpLabelValueModel;
import jp.groupsession.v2.ntp.model.NtpPriConfModel;
import jp.groupsession.v2.ntp.model.NtpSmlMemberModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 ショートメール通知設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp095Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp095Biz.class);
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;
    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl RequestModel
     */
    public Ntp095Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp095ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Ntp095ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //DBより設定情報を取得。なければデフォルト値とする。
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
        NtpPriConfModel pconf = biz.getNtpPriConfModel(con, umodel.getUsrsid());
        NtpAdmConfModel admConf = biz.getAdmConfModel(con);
        int sessionUsrSid = umodel.getUsrsid();

        //画面表示区分を設定
        if (admConf.getNacSmlKbn() == GSConstNippou.SML_NOTICE_USR) {
            paramMdl.setNtp095NtpDspKbn(1);
        }
        if (admConf.getNacCsmlKbn() == GSConstNippou.SML_NOTICE_USR) {
            paramMdl.setNtp095CmtDspKbn(1);
        }
        if (admConf.getNacGsmlKbn() == GSConstNippou.SML_NOTICE_USR) {
            paramMdl.setNtp095GoodDspKbn(1);
        }

        if (paramMdl.getNtp095InitFlg().equals(String.valueOf(GSConstSchedule.INIT_FLG))) {
            //ショートメール通知メンバーを取得
            NtpSmlMemberDao nsmDao = new NtpSmlMemberDao(con);
            ArrayList<NtpSmlMemberModel> nsmList = null;
            nsmList = nsmDao.select(sessionUsrSid);
            if (!nsmList.isEmpty()) {
                ArrayList<String> memList = new ArrayList<String>();
                for (NtpSmlMemberModel nsmMdl : nsmList) {

                    if (nsmMdl.getGrpSid() != -1 && nsmMdl.getNsmGrpKbn() != 0) {
                        //マイグループ
                        //memList.add("M" + String.valueOf(nsmMdl.getGrpSid()));
                    } else if (nsmMdl.getGrpSid() != -1 && nsmMdl.getNsmGrpKbn() == 0) {
                        memList.add("G" + String.valueOf(nsmMdl.getGrpSid()));
                    } else if (nsmMdl.getUsrSid() != -1) {
                        memList.add(String.valueOf(nsmMdl.getUsrSid()));
                    }

                }
                paramMdl.setNtp095memberSid(memList.toArray(new String[memList.size()]));
            }
        }


        //共有区分を設定
        paramMdl.setNtp095KyoyuKbn(admConf.getNacCrange());

        //日報通知
        paramMdl.setNtp095Smail(
                NullDefault.getString(
                        paramMdl.getNtp095Smail(), String.valueOf(pconf.getNprSmail())));
        //コメント通知
        paramMdl.setNtp095CmtSmail(
                NullDefault.getString(
                        paramMdl.getNtp095CmtSmail(), String.valueOf(pconf.getNprCmtSmail())));

        //いいね通知
        paramMdl.setNtp095GoodSmail(
                NullDefault.getString(
                        paramMdl.getNtp095GoodSmail(), String.valueOf(pconf.getNprGoodSmail())));

        paramMdl.setNtp095GroupList(__getGroupLabelList(con, sessionUsrSid));


        //フォーラムメンバー グループコンボ選択値
        String forumSltGp = paramMdl.getNtp095groupSid();
        List<NtpLabelValueModel> labelListAdd = new ArrayList<NtpLabelValueModel>();
        GroupDao dao = new GroupDao(con);
        UserBiz userBiz = new UserBiz();

        if (forumSltGp.equals(Ntp095Form.GRP_SID_GRPLIST)) {

            //除外するグループSID
            List<String> fullGrepList = new ArrayList<String>();
            if (paramMdl.getNtp095memberSid() != null) {
                fullGrepList = Arrays.asList(paramMdl.getNtp095memberSid());
            }

            if (admConf.getNacCrange() == GSConstSchedule.CRANGE_SHARE_ALL) {
                //共有範囲設定が「全て共有」の場合、グループを全て取得
                CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
                CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
                ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);
                for (GroupModel bean : allGpList) {
                    if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                        labelListAdd.add(new NtpLabelValueModel(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid()), "0"));
                    }
                }
            } else {
                //所属グループのみ取得
                ArrayList<NtpLabelValueModel> allGpBelongList
                    = biz.getBelongGroupLabelList(sessionUsrSid, con, false);
                for (NtpLabelValueModel blgMdl : allGpBelongList) {
                    if (!fullGrepList.contains(String.valueOf("G" + blgMdl.getValue()))) {
                        labelListAdd.add(new NtpLabelValueModel(
                                blgMdl.getLabel(), String.valueOf("G" + blgMdl.getValue()), "0"));
                    }
                }
            }

        } else {

            boolean myGroupFlg = false;
            String dspGpSidStr = paramMdl.getNtp095groupSid();
            dspGpSidStr =
                    biz.getEnableSelectGroup(paramMdl.getNtp095GroupList(),
                            dspGpSidStr, null);
            int dspGpSid = NtpCommonBiz.getDspGroupSid(paramMdl.getNtp095groupSid());


            if (NtpCommonBiz.isMyGroupSid(paramMdl.getNtp095groupSid())) {
                myGroupFlg = true;
            }

            //追加用ユーザを取得する
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            for (String str : paramMdl.getNtp095memberSid()) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }
            List<CmnUsrmInfModel> usList = null;

            //ユーザ取得
            usList = userBiz.getBelongUserList(
                                                con,
                                                dspGpSid,
                                                usrSids,
                                                sessionUsrSid,
                                                myGroupFlg);

            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                labelListAdd.add(new NtpLabelValueModel(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                 String.valueOf(cuiMdl.getUsrSid()), "0"));
            }
        }

        paramMdl.setNtp095RightList(labelListAdd);

        //メンバ追加一覧
        paramMdl.setNtp095LeftList(__getMemberList(paramMdl, con, sessionUsrSid));


        paramMdl.setNtp095InitFlg(String.valueOf(GSConstNippou.NOT_INIT_FLG));
    }

    /**
     * 表示グループ用のグループリストを取得する(全て)
     * @param con コネクション
     * @param sessionUsrSid セッションユーザSID
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private ArrayList <NtpLabelValueModel> __getGroupLabelList(Connection con, int sessionUsrSid)
    throws SQLException {

        ArrayList<NtpLabelValueModel> labelList = new ArrayList<NtpLabelValueModel>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        NtpLabelValueModel lavelBean = new NtpLabelValueModel(
                                            gsMsg.getMessage("cmn.grouplist"),
                                            String.valueOf(Ntp095Form.GRP_SID_GRPLIST), "0");
//        lavelBean.setLabel(gsMsg.getMessage(reqMdl__, "cmn.grouplist"));
//        lavelBean.setValue(String.valueOf(Ntp095Form.GRP_SID_GRPLIST));
        labelList.add(lavelBean);

        //グループリスト取得
//        GroupBiz gBiz = new GroupBiz();
//        ArrayList <GroupModel> gpList = gBiz.getGroupCombList(con);
//
//        GroupModel gpMdl = null;
//        for (int i = 0; i < gpList.size(); i++) {
//            gpMdl = gpList.get(i);
//            labelList.add(
//          new LabelValueBean(gpMdl.getGroupName(), String.valueOf(gpMdl.getGroupSid())));
//        }
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
        List<NtpLabelValueModel> groupLabel = biz.getGroupLabelForNippou(
                sessionUsrSid, con, false);
        labelList.addAll(groupLabel);

        log__.debug("labelList.size()=>" + labelList.size());
        return labelList;
    }

    /**
     * <br>[機  能] メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param con コネクション
     * @param sessionUsrSid ユーザSID
     * @return メンバー一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<NtpLabelValueModel> __getMemberList(
                         Ntp095ParamModel paramMdl, Connection con, int sessionUsrSid)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getNtp095memberSid();
        return __getMemberList(leftFull, con, sessionUsrSid);
    }

    /**
     * <br>[機  能] メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @param sessionUsrSid ユーザSID
     * @return メンバー一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<NtpLabelValueModel> __getMemberList(
                  String[] left, Connection con, int sessionUsrSid)
    throws SQLException {

        ArrayList<NtpLabelValueModel> ret = new ArrayList<NtpLabelValueModel>();

        //
        ArrayList<String> mygrpSids = new ArrayList<String>();
        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");

                if (NtpCommonBiz.isMyGroupSid(str)) {
//                    mygrpSids.add(str.substring(1, str.length()));
                } else if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    grpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    usrSids.add(str);
                }
            }
        }

        //マイグループ情報取得
        CmnMyGroupDao myGdao = new CmnMyGroupDao(con__);
        ArrayList<CmnMyGroupModel>  myGlist = myGdao.getMyGroupList(sessionUsrSid, mygrpSids);
        NtpLabelValueModel lavelBean = null;
        for (CmnMyGroupModel myGmodel : myGlist) {
            lavelBean = new NtpLabelValueModel(myGmodel.getMgpName(),
                                        "M" + String.valueOf(myGmodel.getMgpSid()), "1");
            ret.add(lavelBean);
        }

        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
        lavelBean = null;
        for (GroupModel gmodel : glist) {
            lavelBean = new NtpLabelValueModel(gmodel.getGroupName(),
                                        "G" + String.valueOf(gmodel.getGroupSid()), "0");
            ret.add(lavelBean);
        }

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            lavelBean = new NtpLabelValueModel(umodel.getUsisei() + " " + umodel.getUsimei(),
                                                        String.valueOf(umodel.getUsrsid()), "0");
            ret.add(lavelBean);
        }
        return ret;
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

    /**
     * <br>[機  能] 設定された個人設定情報をDBに保存する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp095ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setPconfSetting(Ntp095ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //DBより設定情報を取得。なければデフォルト値とする。
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
        NtpPriConfModel pconf = biz.getNtpPriConfModel(con, umodel.getUsrsid());

        pconf.setNprSmail(
                NullDefault.getInt(paramMdl.getNtp095Smail(), GSConstNippou.SMAIL_USE));
        pconf.setNprCmtSmail(
                NullDefault.getInt(paramMdl.getNtp095CmtSmail(), GSConstNippou.SMAIL_USE));
        pconf.setNprGoodSmail(
                NullDefault.getInt(paramMdl.getNtp095GoodSmail(), GSConstNippou.SMAIL_USE));
        pconf.setNprEuid(umodel.getUsrsid());
        pconf.setNprEdate(new UDate());

        //ショートメール通知グループ、ユーザ登録
        ArrayList<NtpSmlMemberModel> nsmMdlList = new ArrayList<NtpSmlMemberModel>();
        if (paramMdl.getNtp095NtpDspKbn() == 1) {
            nsmMdlList
            = __getNtpSmlMemMdl(paramMdl.getNtp095memberSid(), con, umodel.getUsrsid());
        }



        boolean commitFlg = false;
        try {
            //個人設定
            NtpPriConfDao dao = new NtpPriConfDao(con);
            int count = dao.updateSmail(pconf);
            if (count <= 0) {
                //レコードがない場合は作成
                dao.insert(pconf);
            }

            //ショートメール通知メンバー設定
            if (paramMdl.getNtp095NtpDspKbn() == 1) {
                NtpSmlMemberDao nsmDao = new NtpSmlMemberDao(con);
                nsmDao.delete(umodel.getUsrsid());
                if (paramMdl.getNtp095Smail().equals(String.valueOf(GSConstNippou.SMAIL_USE))) {
                    for (NtpSmlMemberModel nsmMdl : nsmMdlList) {
                        nsmDao.insert(nsmMdl);
                    }
                }
            }

            commitFlg = true;
        } catch (SQLException e) {
            log__.error("", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            }
        }
    }

    /**
     * <br>[機  能] メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left グループ ユーザ SID
     * @param con コネクション
     * @param sessionUsrSid ユーザSID
     * @return メンバー一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<NtpSmlMemberModel> __getNtpSmlMemMdl(
            String[] left, Connection con, int sessionUsrSid)
            throws SQLException {

        ArrayList<NtpSmlMemberModel> ret = new ArrayList<NtpSmlMemberModel>();
        ArrayList<String> mygrpSids = new ArrayList<String>();
        ArrayList<Integer> grpSids = new ArrayList<Integer>();
        ArrayList<String> usrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");

                if (NtpCommonBiz.isMyGroupSid(str)) {
                    mygrpSids.add(str.substring(1, str.length()));
                } else if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    grpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    usrSids.add(str);
                }
            }
        }

        //マイグループ情報取得
//        CmnMyGroupDao myGdao = new CmnMyGroupDao(con__);
//        ArrayList<CmnMyGroupModel>  myGlist = myGdao.getMyGroupList(sessionUsrSid, mygrpSids);
//        for (CmnMyGroupModel myGmodel : myGlist) {
//            NtpSmlMemberModel smlMemMdl = new NtpSmlMemberModel();
//            smlMemMdl.setNsmUsrSid(sessionUsrSid);
//            smlMemMdl.setUsrSid(-1);
//            smlMemMdl.setGrpSid(myGmodel.getMgpSid());
//            smlMemMdl.setNsmGrpKbn(1);
//            ret.add(smlMemMdl);
//        }

        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
        for (GroupModel gmodel : glist) {
            NtpSmlMemberModel smlMemMdl = new NtpSmlMemberModel();
            smlMemMdl.setNsmUsrSid(sessionUsrSid);
            smlMemMdl.setUsrSid(-1);
            smlMemMdl.setGrpSid(gmodel.getGroupSid());
            smlMemMdl.setNsmGrpKbn(0);
            ret.add(smlMemMdl);
        }

        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            NtpSmlMemberModel smlMemMdl = new NtpSmlMemberModel();
            smlMemMdl.setNsmUsrSid(sessionUsrSid);
            smlMemMdl.setUsrSid(umodel.getUsrsid());
            smlMemMdl.setGrpSid(-1);
            smlMemMdl.setNsmGrpKbn(0);
            ret.add(smlMemMdl);
        }

        return ret;
    }
}