package jp.groupsession.v2.bbs.bbs030kn;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.dao.BbsForAdminDao;
import jp.groupsession.v2.bbs.dao.BbsForInfDao;
import jp.groupsession.v2.bbs.dao.BbsForMemDao;
import jp.groupsession.v2.bbs.dao.BbsForSumDao;
import jp.groupsession.v2.bbs.dao.BbsThreInfDao;
import jp.groupsession.v2.bbs.dao.BbsThreViewDao;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsForInfModel;
import jp.groupsession.v2.bbs.model.BbsForSumModel;
import jp.groupsession.v2.bbs.model.BbsThreViewModel;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 フォーラム登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs030knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs030knBiz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws Exception 実行例外
     */
    public void setInitData(Bbs030knParamModel paramMdl, Connection con)
    throws Exception {
        log__.debug("START");

        //コメントを設定
        paramMdl.setBbs030viewcomment(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getBbs030comment()), ""));

        //スレッドテンプレートを設定
        paramMdl.setBbs030viewTemplate(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getBbs030template()), ""));

        //フォーラム編集メンバリストを設定する
        paramMdl.setBbs030knMemNameList(__getForumFullLabel(paramMdl, con));

        //フォーラム閲覧メンバリストを設定する
        paramMdl.setBbs030knMemNameListRead(__getForumReadLabel(paramMdl, con));

        //フォーラム管理者メンバリストを設定する
        paramMdl.setBbs030knMemNameListAdm(__getForumAdmLabel(paramMdl, con));

        log__.debug("End");
    }

    /**
     * <br>[機  能] フォーラム情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid セッションユーザSID
     * @param appRoot アプリケーションルート
     * @param tempDir テンポラリディレクトリ
     * @throws Exception 実行例外
     */
    public void insertForumData(Bbs030knParamModel paramMdl,
                                Connection con,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRoot,
                                String tempDir)
    throws Exception {
        log__.debug("START");
        UDate now = new UDate();

        //フォーラムSID採番
        int bfiSid = (int) cntCon.getSaibanNumber(GSConstBulletin.SBNSID_BULLETIN,
                                                    GSConstBulletin.SBNSID_SUB_BULLETIN_FORUM,
                                                    userSid);

        //フォーラム情報の登録
        BbsForInfModel bbsInfMdl = new BbsForInfModel();
        bbsInfMdl.setBfiSid(bfiSid);
        bbsInfMdl.setBfiName(paramMdl.getBbs030forumName());
        bbsInfMdl.setBfiCmt(paramMdl.getBbs030comment());
        bbsInfMdl.setBfiSort(0);
        bbsInfMdl.setBfiReply(
                NullDefault.getInt(paramMdl.getBbs030reply(),
                                GSConstBulletin.BBS_THRE_REPLY_NO));
        bbsInfMdl.setBfiRead(
                NullDefault.getInt(paramMdl.getBbs030read(),
                                GSConstBulletin.NEWUSER_THRE_VIEW_YES));
        bbsInfMdl.setBfiMread(
                NullDefault.getInt(paramMdl.getBbs030mread(), GSConstBulletin.BBS_FORUM_MREAD_NO));
        bbsInfMdl.setBfiTemplateKbn(paramMdl.getBbs030templateKbn());
        if (paramMdl.getBbs030templateKbn() == GSConstBulletin.BBS_THRE_TEMPLATE_YES) {
            bbsInfMdl.setBfiTemplate(paramMdl.getBbs030template());
            bbsInfMdl.setBfiTemplateWrite(paramMdl.getBbs030templateWriteKbn());
        } else {
            bbsInfMdl.setBfiTemplate("");
            bbsInfMdl.setBfiTemplateWrite(GSConstBulletin.BBS_THRE_TEMPLATE_WRITE_NO);
        }

        bbsInfMdl.setBfiWarnDisk(GSConstBulletin.BFI_WARN_DISK_NO);
        bbsInfMdl.setBfiWarnDiskTh(0);
        if (paramMdl.getBbs030diskSize() == GSConstBulletin.BFI_DISK_LIMITED) {
            bbsInfMdl.setBfiDisk(GSConstBulletin.BFI_DISK_LIMITED);
            bbsInfMdl.setBfiDiskSize(Integer.parseInt(paramMdl.getBbs030diskSizeLimit()));

            if (paramMdl.getBbs030warnDisk() == GSConstBulletin.BFI_WARN_DISK_YES) {
                bbsInfMdl.setBfiWarnDisk(GSConstBulletin.BFI_WARN_DISK_YES);
                bbsInfMdl.setBfiWarnDiskTh(paramMdl.getBbs030warnDiskThreshold());
            }

        } else {
            bbsInfMdl.setBfiDisk(GSConstBulletin.BFI_DISK_NOLIMIT);
            bbsInfMdl.setBfiDiskSize(0);
        }

        //掲示期間有無初期値
        if (paramMdl.getBbs030LimitDisable() == GSConstBulletin.THREAD_DISABLE) {
            bbsInfMdl.setBfiLimitOn(GSConstBulletin.THREAD_DISABLE);
            bbsInfMdl.setBfiLimit(GSConstBulletin.THREAD_LIMIT_NO);
            bbsInfMdl.setBfiLimitDate(0);
            bbsInfMdl.setBfiKeep(GSConstBulletin.THREAD_KEEP_NO);
            bbsInfMdl.setBfiKeepDateY(-1);
            bbsInfMdl.setBfiKeepDateM(-1);
        } else {
            bbsInfMdl.setBfiLimitOn(GSConstBulletin.THREAD_ENABLE);

            //掲示期間初期値
            if (paramMdl.getBbs030Limit() == GSConstBulletin.THREAD_LIMIT_YES) {
                bbsInfMdl.setBfiLimit(GSConstBulletin.THREAD_LIMIT_YES);
                bbsInfMdl.setBfiLimitDate(Integer.parseInt(paramMdl.getBbs030LimitDate()));
            } else {
                bbsInfMdl.setBfiLimit(GSConstBulletin.THREAD_LIMIT_NO);
                bbsInfMdl.setBfiLimitDate(0);
            }

            //スレッド保存期間
            if (paramMdl.getBbs030Keep() == GSConstBulletin.THREAD_KEEP_YES) {
                bbsInfMdl.setBfiKeep(GSConstBulletin.THREAD_KEEP_YES);
                bbsInfMdl.setBfiKeepDateY(paramMdl.getBbs030KeepDateY());
                bbsInfMdl.setBfiKeepDateM(paramMdl.getBbs030KeepDateM());
            } else {
                bbsInfMdl.setBfiKeep(GSConstBulletin.THREAD_KEEP_NO);
                bbsInfMdl.setBfiKeepDateY(-1);
                bbsInfMdl.setBfiKeepDateM(-1);
            }
        }

        //アイコン情報を登録
        Long binSid = new Long(0);
        CommonBiz cmnBiz = new CommonBiz();
        if (!NullDefault.getStringZeroLength(
                paramMdl.getBbs030ImageName(), "").equals("")
                && !NullDefault.getStringZeroLength(
                        paramMdl.getBbs030ImageSaveName(), "").equals("")) {

            String filePath = tempDir + GSConstBulletin.TEMP_ICN_BBS
                    + File.separator
                    + paramMdl.getBbs030ImageSaveName() + GSConstCommon.ENDSTR_SAVEFILE;

            binSid = cmnBiz.insertBinInfo(
                    con, appRoot, cntCon, userSid, now, filePath, paramMdl.getBbs030ImageName());
        }

        bbsInfMdl.setBinSid(binSid);
        bbsInfMdl.setBfiAuid(userSid);
        bbsInfMdl.setBfiAdate(now);
        bbsInfMdl.setBfiEuid(userSid);
        bbsInfMdl.setBfiEdate(now);

        BbsForInfDao bbsInfDao = new BbsForInfDao(con);
        bbsInfDao.insert(bbsInfMdl);

        //フォーラム集計情報の登録
        BbsForSumModel bbsSumMdl = new BbsForSumModel();
        bbsSumMdl.setBfiSid(bfiSid);
        bbsSumMdl.setBfsThreCnt(0);
        bbsSumMdl.setBfsWrtCnt(0);
        bbsSumMdl.setBfsWrtDate(null);
        bbsSumMdl.setBfsAuid(userSid);
        bbsSumMdl.setBfsAdate(now);
        bbsSumMdl.setBfsEuid(userSid);
        bbsSumMdl.setBfsEdate(now);

        BbsForSumDao bbsSumDao = new BbsForSumDao(con);
        bbsSumDao.insert(bbsSumMdl);

        //フォーラム編集メンバーの登録
        BbsForMemDao bbsMemDao = new BbsForMemDao(con);
        bbsMemDao.insert(bfiSid, paramMdl.getBbs030memberSid(), GSConstBulletin.ACCESS_KBN_WRITE);

        //フォーラム閲覧メンバーの登録
        bbsMemDao.insert(bfiSid, paramMdl.getBbs030memberSidRead(),
                        GSConstBulletin.ACCESS_KBN_READ);

        //フォーラム管理者メンバーの登録
        BbsForAdminDao forAdmDao = new BbsForAdminDao(con);
        forAdmDao.insert(bfiSid, paramMdl.getBbs030memberSidAdm());



        log__.debug("End");
    }

    /**
     * <br>[機  能] フォーラム情報の更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @param appRoot アプリケーションルート
     * @param tempDir テンポラリディレクトリ
     * @param cntCon MlCountMtController
     * @throws Exception 実行例外
     */
    public void updateForumData(Bbs030knParamModel paramMdl,
                                Connection con,
                                MlCountMtController cntCon,
                                int userSid,
                                String appRoot,
                                String tempDir)
    throws Exception {
        log__.debug("START");
        UDate now = new UDate();
        int bfiSid = paramMdl.getBbs020forumSid();

        //フォーラム情報の更新
        BbsForInfModel bbsInfMdl = new BbsForInfModel();
        bbsInfMdl.setBfiSid(bfiSid);
        bbsInfMdl.setBfiName(paramMdl.getBbs030forumName());
        bbsInfMdl.setBfiCmt(paramMdl.getBbs030comment());
        bbsInfMdl.setBfiReply(
                NullDefault.getInt(paramMdl.getBbs030reply(), GSConstBulletin.BBS_THRE_REPLY_NO));
        bbsInfMdl.setBfiRead(
                NullDefault.getInt(paramMdl.getBbs030read(),
                                    GSConstBulletin.NEWUSER_THRE_VIEW_YES));
        bbsInfMdl.setBfiMread(
                NullDefault.getInt(paramMdl.getBbs030mread(), GSConstBulletin.BBS_FORUM_MREAD_NO));
        bbsInfMdl.setBfiTemplateKbn(paramMdl.getBbs030templateKbn());

        if (paramMdl.getBbs030templateKbn() == GSConstBulletin.BBS_THRE_TEMPLATE_YES) {
            bbsInfMdl.setBfiTemplate(paramMdl.getBbs030template());
            bbsInfMdl.setBfiTemplateWrite(paramMdl.getBbs030templateWriteKbn());
        } else {
            bbsInfMdl.setBfiTemplate("");
            bbsInfMdl.setBfiTemplateWrite(GSConstBulletin.BBS_THRE_TEMPLATE_WRITE_NO);
        }

        bbsInfMdl.setBfiWarnDisk(GSConstBulletin.BFI_WARN_DISK_NO);
        bbsInfMdl.setBfiWarnDiskTh(0);
        if (paramMdl.getBbs030diskSize() == GSConstBulletin.BFI_DISK_LIMITED) {
            bbsInfMdl.setBfiDisk(GSConstBulletin.BFI_DISK_LIMITED);
            bbsInfMdl.setBfiDiskSize(Integer.parseInt(paramMdl.getBbs030diskSizeLimit()));

            if (paramMdl.getBbs030warnDisk() == GSConstBulletin.BFI_WARN_DISK_YES) {
                bbsInfMdl.setBfiWarnDisk(GSConstBulletin.BFI_WARN_DISK_YES);
                bbsInfMdl.setBfiWarnDiskTh(paramMdl.getBbs030warnDiskThreshold());
            }

        } else {
            bbsInfMdl.setBfiDisk(GSConstBulletin.BFI_DISK_NOLIMIT);
            bbsInfMdl.setBfiDiskSize(0);
        }

        //掲示期間有無初期値
        if (paramMdl.getBbs030LimitDisable() == GSConstBulletin.THREAD_DISABLE) {
            bbsInfMdl.setBfiLimitOn(GSConstBulletin.THREAD_DISABLE);
            bbsInfMdl.setBfiLimit(GSConstBulletin.THREAD_LIMIT_NO);
            bbsInfMdl.setBfiLimitDate(0);
            bbsInfMdl.setBfiKeep(GSConstBulletin.THREAD_KEEP_NO);
            bbsInfMdl.setBfiKeepDateY(-1);
            bbsInfMdl.setBfiKeepDateM(-1);
        } else {
            bbsInfMdl.setBfiLimitOn(GSConstBulletin.THREAD_ENABLE);

            //掲示期間初期値
            if (paramMdl.getBbs030Limit() == GSConstBulletin.THREAD_LIMIT_YES) {
                bbsInfMdl.setBfiLimit(GSConstBulletin.THREAD_LIMIT_YES);
                bbsInfMdl.setBfiLimitDate(Integer.parseInt(paramMdl.getBbs030LimitDate()));
            } else {
                bbsInfMdl.setBfiLimit(GSConstBulletin.THREAD_LIMIT_NO);
                bbsInfMdl.setBfiLimitDate(0);
            }

            //スレッド保存期間
            if (paramMdl.getBbs030Keep() == GSConstBulletin.THREAD_KEEP_YES) {
                bbsInfMdl.setBfiKeep(GSConstBulletin.THREAD_KEEP_YES);
                bbsInfMdl.setBfiKeepDateY(paramMdl.getBbs030KeepDateY());
                bbsInfMdl.setBfiKeepDateM(paramMdl.getBbs030KeepDateM());
            } else {
                bbsInfMdl.setBfiKeep(GSConstBulletin.THREAD_KEEP_NO);
                bbsInfMdl.setBfiKeepDateY(-1);
                bbsInfMdl.setBfiKeepDateM(-1);
            }
        }

        //バイナリー情報を更新する
        BulletinDao bbsDao = new BulletinDao(con);
        bbsDao.deleteBinfForum(bfiSid);

        Long binSid = new Long(0);
        if (!NullDefault.getStringZeroLength(
                paramMdl.getBbs030ImageName(), "").equals("")
                && !NullDefault.getStringZeroLength(
                        paramMdl.getBbs030ImageSaveName(), "").equals("")) {

            CommonBiz cmnBiz = new CommonBiz();
            String filePath = tempDir + GSConstBulletin.TEMP_ICN_BBS
                        + File.separator
                        + paramMdl.getBbs030ImageSaveName() + GSConstCommon.ENDSTR_SAVEFILE;

            binSid = cmnBiz.insertBinInfo(con, appRoot, cntCon,
                    userSid, now, filePath, paramMdl.getBbs030ImageName());
        }
        bbsInfMdl.setBinSid(binSid);

        bbsInfMdl.setBfiEuid(userSid);
        bbsInfMdl.setBfiEdate(now);

        BbsForInfDao bbsInfDao = new BbsForInfDao(con);
        bbsInfDao.updateBBSInf(bbsInfMdl);

        BbsForMemDao bbsMemDao = new BbsForMemDao(con);

        //スレッド閲覧状況の更新
        if (Integer.parseInt(paramMdl.getBbs030read()) == GSConstBulletin.BBS_THRE_VIEW_YES) {
            __updateThreadView(paramMdl, con, userSid);
        }

        //フォーラムメンバーの更新
        bbsMemDao.deleteForumMem(bfiSid);
        bbsMemDao.insert(bfiSid, paramMdl.getBbs030memberSid(),
                        GSConstBulletin.ACCESS_KBN_WRITE);
        bbsMemDao.insert(bfiSid, paramMdl.getBbs030memberSidRead(),
                        GSConstBulletin.ACCESS_KBN_READ);

        //フォーラム管理者メンバーの登録
        BbsForAdminDao forAdmDao = new BbsForAdminDao(con);
        forAdmDao.delete(bfiSid);
        forAdmDao.insert(bfiSid, paramMdl.getBbs030memberSidAdm());
        log__.debug("End");
    }

    /**
     * <br>[機  能] スレッド閲覧情報の登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid セッションユーザSID
     * @throws Exception 実行例外
     */
    private void __updateThreadView(Bbs030knParamModel paramMdl,
                                Connection con,
                                int userSid)
    throws Exception {

        String[] addMember = paramMdl.getBbs030memberSid();
        if (addMember == null || addMember.length < 1) {
            return;
        }

        int bfiSid = paramMdl.getBbs020forumSid();

        BbsForMemDao bbsMemDao = new BbsForMemDao(con);
        String[] oldMemberSid = bbsMemDao.getForumMemberId(bfiSid);

        CommonBiz cmnBiz = new CommonBiz();
        addMember = cmnBiz.getDeleteMember(oldMemberSid, addMember);
        if (addMember == null || addMember.length < 1) {
            return;
        }
        List<Integer> userSidList = __getMemberUserSidList(con, addMember);

        String[] delMemberSid = cmnBiz.getDeleteMember(paramMdl.getBbs030memberSid(), oldMemberSid);
        List<Integer> delUserSidList = __getMemberUserSidList(con, delMemberSid);

        //スレッドSIDリストを取得
        BbsThreInfDao ftiDao = new BbsThreInfDao(con);
        String[] threSidList = ftiDao.getThreList(bfiSid);
        if (threSidList == null || threSidList.length < 1) {
            return;
        }

        UDate now = new UDate();
        BbsThreViewDao threViewDao = new BbsThreViewDao(con);
        BbsThreViewModel threViewMdl = new BbsThreViewModel();
        threViewMdl.setBfiSid(bfiSid);
        threViewMdl.setBivViewKbn(GSConstBulletin.NEWUSER_THRE_VIEW_YES);
        threViewMdl.setBivAuid(userSid);
        threViewMdl.setBivAdate(now);
        threViewMdl.setBivEuid(userSid);
        threViewMdl.setBivEdate(now);

        //スレッド閲覧状況の更新
        for (Integer memUserSid : userSidList) {
            if (memUserSid.intValue() <= GSConstUser.USER_RESERV_SID
            || delUserSidList.indexOf(memUserSid) >= 0) {
                continue;
            }

            threViewMdl.setUsrSid(memUserSid.intValue());
            for (String btiSid : threSidList) {
                threViewMdl.setBtiSid(Integer.parseInt(btiSid));
                if (threViewDao.getThreViewCount(threViewMdl.getBtiSid(), threViewMdl.getUsrSid())
                == 0) {
                    threViewDao.insert(threViewMdl);
                }
            }
        }
        userSidList.clear();
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
    private ArrayList<LabelValueBean> __getForumFullLabel(Bbs030knParamModel paramMdl,
                                                        Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getBbs030memberSid();
        return __getForumLavle(leftFull, con);
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
    private ArrayList<LabelValueBean> __getForumReadLabel(Bbs030knParamModel paramMdl,
                                                        Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getBbs030memberSidRead();
        return __getForumLavle(leftFull, con);
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
    private ArrayList<LabelValueBean> __getForumAdmLabel(Bbs030knParamModel paramMdl,
                                                        Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = paramMdl.getBbs030memberSidAdm();
        return __getForumLavle(leftFull, con);
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
    private ArrayList<LabelValueBean> __getForumLavle(String[] left, Connection con)
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
     * <br>[機  能] フォーラムメンバーのユーザSID一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param memberSid フォーラムメンバーSID
     * @return ユーザSID一覧
     * @throws SQLException SQL実行時例外
     */
    private List<Integer> __getMemberUserSidList(Connection con, String[] memberSid)
    throws SQLException {

        CmnBelongmDao belongDao = new CmnBelongmDao(con);
        List<Integer> userSidList = new ArrayList<Integer>();
        for (String memSid : memberSid) {
            if (StringUtil.isNullZeroString(memSid)) {
                continue;
            }

            if (memSid.startsWith(GSConstBulletin.FORUM_MEMBER_GROUP_HEADSTR)) {
                //グループ
                userSidList.addAll(belongDao.selectBelongUserSid(
                                            Integer.parseInt(memSid.substring(1))));
            } else {
                //ユーザ
                userSidList.add(new Integer(memSid));
            }
        }

        return userSidList;
    }
}