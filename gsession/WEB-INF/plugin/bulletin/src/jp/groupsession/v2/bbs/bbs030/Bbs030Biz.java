package jp.groupsession.v2.bbs.bbs030;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.bbs.BbsBiz;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.dao.BbsForAdminDao;
import jp.groupsession.v2.bbs.dao.BbsForMemDao;
import jp.groupsession.v2.bbs.model.BbsAdmConfModel;
import jp.groupsession.v2.bbs.model.BbsForAdminModel;
import jp.groupsession.v2.bbs.model.BbsForMemModel;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 フォーラム登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs030Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs030Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説] 処理モード = 編集の場合、フォーラム情報を設定する
     * <br>[備  考]
     * @param cmd CMDパラメータ
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param tempDir テンポラリディレクトリパス
     * @param appRoot アプリケーションルートパス
     * @param sessionUsrSid セッションユーザSID
     * @throws SQLException 実行例外
     * @throws IOToolsException 添付ファイルの操作に失敗
     * @throws IOException バイナリファイル操作時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     *
     */
    public void setInitData(String cmd, RequestModel reqMdl, Bbs030ParamModel paramMdl,
                           Connection con, String tempDir, String appRoot, int sessionUsrSid)
    throws SQLException, IOToolsException, TempFileException, IOException {
        log__.debug("START");

        //フォーラム一覧からの遷移、かつ処理モード = 編集の場合はフォーラム情報を取得する
        if (cmd.equals("editForum")
        && paramMdl.getBbs030cmdMode() == GSConstBulletin.BBSCMDMODE_EDIT) {
            int bfiSid = paramMdl.getBbs020forumSid();

            BbsBiz bbsBiz = new BbsBiz();
            BulletinDspModel btMdl = bbsBiz.getForumData(con, bfiSid);
            if (btMdl == null) {
                throw new SQLException("フォーラム情報の取得に失敗");
            }
            paramMdl.setBbs030forumName(btMdl.getBfiName());
            paramMdl.setBbs030comment(btMdl.getBfiCmt());
            paramMdl.setBbs030reply(String.valueOf(btMdl.getBfiReply()));
            paramMdl.setBbs030read(String.valueOf(btMdl.getBfiRead()));
            paramMdl.setBbs030mread(String.valueOf(btMdl.getBfiMread()));
            paramMdl.setBbs030templateKbn(btMdl.getBfiTemplateKbn());
            paramMdl.setBbs030template(btMdl.getBfiTemplate());
            paramMdl.setBbs030templateWriteKbn(btMdl.getBfiTemplateWrite());
            paramMdl.setBbs030LimitDisable(btMdl.getBfiLimitOn());
            paramMdl.setBbs030Limit(btMdl.getBfiLimit());
            paramMdl.setBbs030LimitDate(String.valueOf(btMdl.getBfiLimitDate()));
            paramMdl.setBbs030Keep(btMdl.getBfiKeep());
            paramMdl.setBbs030KeepDateY(btMdl.getBfiKeepDateY());
            paramMdl.setBbs030KeepDateM(btMdl.getBfiKeepDateM());

            paramMdl.setBbs030diskSize(btMdl.getBfiDisk());
            if (btMdl.getBfiDisk() == GSConstBulletin.BFI_DISK_LIMITED) {
                paramMdl.setBbs030diskSizeLimit(Integer.toString(btMdl.getBfiDiskSize()));
            }
            paramMdl.setBbs030warnDisk(btMdl.getBfiWarnDisk());
            if (btMdl.getBfiWarnDisk() == GSConstBulletin.BFI_WARN_DISK_YES) {
                paramMdl.setBbs030warnDiskThreshold(btMdl.getBfiWarnDiskTh());
            }

            //フォーラムメンバーを設定
            BbsForMemDao bbsMemDao = new BbsForMemDao(con);
            List<BbsForMemModel> bfmMdlList = new ArrayList<BbsForMemModel>();
            ArrayList<String> editList = new ArrayList<String>();
            ArrayList<String> readList = new ArrayList<String>();
            bfmMdlList = bbsMemDao.getUsrData(bfiSid);

            for (int i = 0; i < bfmMdlList.size(); i++) {
                if (bfmMdlList.get(i).getBfmAuth() == GSConstBulletin.ACCESS_KBN_WRITE) {
                    if (bfmMdlList.get(i).getUsrSid() != -1) {
                        editList.add(String.valueOf(bfmMdlList.get(i).getUsrSid()));
                    }
                    if (bfmMdlList.get(i).getGrpSid() != -1) {
                        editList.add(String.valueOf(GSConstBulletin.FORUM_MEMBER_GROUP_HEADSTR
                                                + bfmMdlList.get(i).getGrpSid()));
                    }

                } else {
                    if (bfmMdlList.get(i).getUsrSid() != -1) {
                        readList.add(String.valueOf(bfmMdlList.get(i).getUsrSid()));
                    }
                    if (bfmMdlList.get(i).getGrpSid() != -1) {
                        readList.add(String.valueOf(GSConstBulletin.FORUM_MEMBER_GROUP_HEADSTR
                                                + bfmMdlList.get(i).getGrpSid()));
                    }

                }

            }
            String[] usrGrpSids = (String[]) editList.toArray(new String[editList.size()]);
            String[] usrGrpSidsRead = (String[]) readList.toArray(new String[readList.size()]);
            paramMdl.setBbs030memberSid(usrGrpSids);
            paramMdl.setBbs030memberSidRead(usrGrpSidsRead);



            //フォーラム管理者を設定
            BbsForAdminDao forAdmDao = new BbsForAdminDao(con);
            List<BbsForAdminModel> forAdmMdlList = new ArrayList<BbsForAdminModel>();
            ArrayList<String> admList = new ArrayList<String>();
            forAdmMdlList = forAdmDao.getUsrData(bfiSid);

            for (int i = 0; i < forAdmMdlList.size(); i++) {
                admList.add(String.valueOf(forAdmMdlList.get(i).getUsrSid()));
            }
            String[] admUsrSids = (String[]) admList.toArray(new String[admList.size()]);
            paramMdl.setBbs030memberSidAdm(admUsrSids);

            //
            //画像バイナリSIDを取得
            paramMdl.setBbs030BinSid(btMdl.getBinSid());
        }

        paramMdl.setBbs030GroupList(__getGroupLabelList(con, reqMdl, true));


        UserBiz userBiz = new UserBiz();

        //フォーラムメンバー グループコンボ選択値
        int forumSltGp = paramMdl.getBbs030groupSid();
        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();

        ArrayList<String> fullGrepList = new ArrayList<String>();
        //除外するグループSID
        String[] memberSid = paramMdl.getBbs030memberSid();
        if (memberSid != null && memberSid.length > 0) {
            for  (int i = 0; i < memberSid.length; i++) {
                String memSid = memberSid[i];
                if (!fullGrepList.contains(memSid)) {
                    fullGrepList.add(memSid);
                }
            }
        }

        //閲覧メンバーを除外リストに追加
        String[] memberSidRead = paramMdl.getBbs030memberSidRead();
        if (memberSidRead != null && memberSidRead.length > 0) {
            for  (int i = 0; i < memberSidRead.length; i++) {
                String readMemSid = memberSidRead[i];
                if (!fullGrepList.contains(readMemSid)) {
                    fullGrepList.add(readMemSid);
                }
            }
        }

        if (forumSltGp == Integer.parseInt(GSConstBulletin.GROUP_COMBO_VALUE)) {
            //グループを全て取得
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            GroupDao dao = new GroupDao(con);
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);

            for (GroupModel bean : allGpList) {
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    labelListAdd.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }

        } else {
            //追加用ユーザを取得する
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            for (String str : paramMdl.getBbs030memberSid()) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }

            for (String str : paramMdl.getBbs030memberSidRead()) {
                usrSids.add(new Integer(NullDefault.getInt(str, -1)));
            }

            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, paramMdl.getBbs030groupSid(), usrSids);

            for (int i = 0; i < usList.size(); i++) {
                CmnUsrmInfModel cuiMdl = usList.get(i);
                labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                                 String.valueOf(cuiMdl.getUsrSid())));
            }
        }

        paramMdl.setBbs030RightUserList(labelListAdd);


        List<Integer> groupSidList = new ArrayList<Integer>();
        ArrayList<Integer> grepUserList = new ArrayList<Integer>();
        for (String userGrpSid : fullGrepList) {
            String str = NullDefault.getString(userGrpSid, "-1");
            if (str.contains(GSConstBulletin.FORUM_MEMBER_GROUP_HEADSTR)) {
                //グループ
                groupSidList.add(NullDefault.getInt(str.substring(1, str.length()), -1));
            } else {
                grepUserList.add(NullDefault.getInt(str, -1));
            }
        }

        //管理者グループ一覧を設定する。
        int[] gsids = new int[groupSidList.size()];
        for (int i = 0; i < groupSidList.size(); i++) {
            gsids[i] = groupSidList.get(i);
        }
        paramMdl.setBbs030GroupListAdm(__getAdmGroupLabelList(con, reqMdl, gsids));


        //管理者 追加用ユーザを取得する
        List<LabelValueBean> labelListAddAdm = new ArrayList<LabelValueBean>();

        //管理者ユーザ
        ArrayList<Integer> usrSids = new ArrayList<Integer>();
        for (String str : paramMdl.getBbs030memberSidAdm()) {
            usrSids.add(new Integer(NullDefault.getInt(str, -1)));
        }

        if (paramMdl.getBbs030groupSidAdm()
                == Integer.parseInt(GSConstBulletin.GROUP_COMBO_VALUE_USER)) {

            List<CmnUsrmInfModel> usrList = userBiz.getUserList(con, grepUserList);

            //ユーザ指定の場合
            for (CmnUsrmInfModel model : usrList) {
                if (!usrSids.contains(model.getUsrSid())) {
                    labelListAddAdm.add(new LabelValueBean(model.getUsiSei() + model.getUsiMei(),
                            String.valueOf(model.getUsrSid())));
                }
            }


        } else {

            //グループ指定の場合

            List<CmnUsrmInfModel> usList
                = userBiz.getBelongUserList(con, paramMdl.getBbs030groupSidAdm(), usrSids);
            for (CmnUsrmInfModel cuiMdl : usList) {
                labelListAddAdm.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
                        String.valueOf(cuiMdl.getUsrSid())));
          }
        }

        paramMdl.setBbs030RightUserListAdm(labelListAddAdm);



        //編集メンバ追加一覧
        paramMdl.setBbs030LeftUserList(__getForumLabel(paramMdl, con));

        //閲覧メンバ追加一覧
        paramMdl.setBbs030LeftUserListRead(__getForumReadLabel(paramMdl, con));

        //管理者メンバ追加一覧
        paramMdl.setBbs030LeftUserListAdm(__getForumAdmLabel(paramMdl, con));

        CommonBiz cmnBiz = new CommonBiz();

        //バイナリSIDが取得できていたら画像を取得
        if (paramMdl.getBbs030BinSid() > 0) {
            CmnBinfModel binMdl
                = cmnBiz.getBinInfo(con, paramMdl.getBbs030BinSid(), reqMdl.getDomain());
            if (binMdl != null) {

                //テンポラリディレクトリにバイナリデータから作成したファイルを保存する
                String imageSaveName = cmnBiz.saveSingleTempFile(binMdl, appRoot, tempDir);
                paramMdl.setBbs030ImageName(binMdl.getBinFileName());
                paramMdl.setBbs030ImageSaveName(imageSaveName);
            }
        }

        //ディスク容量警告 選択値を設定
        List<LabelValueBean> warnDiskThresholdList = new ArrayList<LabelValueBean>();
        for (int warnValue = 10; warnValue <= 90; warnValue += 10) {
            String strWarnValue = Integer.toString(warnValue);
            warnDiskThresholdList.add(new LabelValueBean(strWarnValue,
                                                                                    strWarnValue));
        }
        paramMdl.setWarnDiskThresholdList(warnDiskThresholdList);

        GsMessage gsMsg = new GsMessage(reqMdl);

        //スレッド保存期間 経過年 選択値を設定
        ArrayList<LabelValueBean> bbs030KeepDateYearLabel = new ArrayList<LabelValueBean>();
        for (String label : Bbs030Form.YEAR_VALUE) {
            bbs030KeepDateYearLabel.add(new LabelValueBean(
                    gsMsg.getMessage("cmn.year", new String[] {label}), label));
        }
        paramMdl.setBbs030KeepDateYLabel(bbs030KeepDateYearLabel);

        //スレッド保存期間 経過月 選択値を設定
        ArrayList<LabelValueBean> bbs030KeepDateMonthLabel = new ArrayList<LabelValueBean>();
        for (String label : Bbs030Form.MONTH_VALUE) {
            bbs030KeepDateMonthLabel.add(new LabelValueBean(
                    gsMsg.getMessage("cmn.months", new String[] {label}), label));
        }
        paramMdl.setBbs030KeepDateMLabel(bbs030KeepDateMonthLabel);


        //管理者設定 自動削除設定を取得する
        //DBより現在の設定を取得する。(なければデフォルト)
        BbsBiz biz = new BbsBiz();
        BbsAdmConfModel conf = biz.getBbsAdminData(con, sessionUsrSid);
        //スレッド保存期間 自動削除設定内容表示フラグ
        paramMdl.setBbs030DspAtdelFlg(conf.getBacAtdelFlg());
        //スレッド保存期間  表示用 自動削除設定内容 経過年
        paramMdl.setBbs030DspAtdelYear(conf.getBacAtdelY());
        //スレッド保存期間  表示用 自動削除設定内容 経過月
        paramMdl.setBbs030DspAtdelMonth(conf.getBacAtdelM());


        log__.debug("End");
    }

    /**
     * <br>[機  能] フォーラム編集メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getForumLabel(Bbs030ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getBbs030memberSid();
        return __getForumLabel(leftFull, con);
    }

    /**
     * <br>[機  能] フォーラム閲覧メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getForumReadLabel(Bbs030ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getBbs030memberSidRead();
        return __getForumLabel(leftFull, con);
    }

    /**
     * <br>[機  能] フォーラム管理者メンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getForumAdmLabel(Bbs030ParamModel paramMdl, Connection con)
    throws SQLException {

        //取得するユーザSID
        String[] leftFull = paramMdl.getBbs030memberSidAdm();
        return __getForumLabel(leftFull, con);
    }

    /**
     * <br>[機  能] フォーラムメンバー一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getForumLabel(String[] left, Connection con)
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

        LabelValueBean lavelBean = null;
        if (grpSids.size() > 0) {
            //グループ情報取得
            UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
            ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
            for (GroupModel gmodel : glist) {
                lavelBean = new LabelValueBean();
                lavelBean.setLabel(gmodel.getGroupName());
                lavelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
                ret.add(lavelBean);
            }

        }
        //ユーザ情報取得
        UserBiz userBiz = new UserBiz();
        ArrayList<BaseUserModel> ulist
                = userBiz.getBaseUserList(con,
                                        (String[]) usrSids.toArray(new String[usrSids.size()]));
        for (BaseUserModel umodel : ulist) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
            lavelBean.setValue(String.valueOf(umodel.getUsrsid()));
            ret.add(lavelBean);
        }
        return ret;
    }

    /**
     * 表示グループ用のグループリストを取得する(全て)
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param addGrpListFlg true:グループ一覧を含める false:含めない
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private ArrayList <LabelValueBean> __getGroupLabelList(
            Connection con,  RequestModel reqMdl, boolean addGrpListFlg)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textGroupList = gsMsg.getMessage("cmn.grouplist");

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        LabelValueBean lavelBean = new LabelValueBean();
        if (addGrpListFlg) {
            lavelBean.setLabel(textGroupList);
            lavelBean.setValue(GSConstBulletin.GROUP_COMBO_VALUE);
            labelList.add(lavelBean);
        }

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
     * 表示管理者のグループ用のグループリストを取得する(メンバー選択しているグループのみ)
     * @param con コネクション
     * @param reqMdl リクエスト情報
     * @param gsids グループSIDリスト
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private ArrayList <LabelValueBean> __getAdmGroupLabelList(
            Connection con, RequestModel reqMdl, int[] gsids)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textUserSitei = gsMsg.getMessage("cmn.user.specified");

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        LabelValueBean lavelBean = new LabelValueBean();

        lavelBean.setLabel(textUserSitei);
        lavelBean.setValue(GSConstBulletin.GROUP_COMBO_VALUE_USER);
        labelList.add(lavelBean);

        if (gsids != null && gsids.length > 0) {

            //グループリスト取得
            GroupDao grpDao = new GroupDao(con);

            List<CmnGroupmModel> gpList = grpDao.getGroups(gsids);

            for (CmnGroupmModel gpMdl : gpList) {
                labelList.add(
                        new LabelValueBean(gpMdl.getGrpName(), String.valueOf(gpMdl.getGrpSid())));
            }
        }
        log__.debug("labelList.size()=>" + labelList.size());
        return labelList;
    }

}
