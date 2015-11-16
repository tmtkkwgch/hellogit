package jp.groupsession.v2.cir.cir160;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cir.GSConstCircular;
import jp.groupsession.v2.cir.biz.CirCommonBiz;
import jp.groupsession.v2.cir.dao.CirAccountDao;
import jp.groupsession.v2.cir.dao.CirAccountUserDao;
import jp.groupsession.v2.cir.dao.CirAdelDao;
import jp.groupsession.v2.cir.model.CirAccountModel;
import jp.groupsession.v2.cir.model.CirAccountUserModel;
import jp.groupsession.v2.cir.model.CirAconfModel;
import jp.groupsession.v2.cir.model.CirAdelModel;
import jp.groupsession.v2.cir.model.CirInitModel;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnThemeDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnThemeModel;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 回覧板 アカウント登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cir160Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cir160Biz.class);

    /** 使用者 ユーザを指定 */
    public static final int USERKBN_USER = 1;
    /** 対象 全ユーザ */
    public static final int TAISYO_ALL = 0;
    /** 対象 ユーザ指定 */
    public static final int TAISYO_SELECT = 1;

    /**
     * <br>[機  能] 初期表示設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws Exception 実行時例外
     */
    public void setInitData(Connection con, Cir160ParamModel paramMdl, RequestModel reqMdl)
    throws Exception {

        //自動削除区分 初期値設定区分を設定
        CirCommonBiz cirBiz = new CirCommonBiz(con);
        CirInitModel initMdl = new CirInitModel();
        initMdl = cirBiz.getCirInitConf(reqMdl.getSmodel().getUsrsid(), con);
        paramMdl.setCir160autoDelKbn(initMdl.getCinAutoDelKbn());
        paramMdl.setCir160cirInitKbn(initMdl.getCinInitSetKen());

        ArrayList<LabelValueBean> yearLabel = new ArrayList<LabelValueBean>();
        ArrayList<LabelValueBean> monthLabel = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl);

        //年リスト
        for (int i = 0; i <= 5; i++) {
            yearLabel.add(new LabelValueBean(
                    gsMsg.getMessage("cmn.year", new String[] {String.valueOf(i)}),
                    Integer.toString(i)));
        }
        yearLabel.add(
                new LabelValueBean(gsMsg.getMessage("cmn.year", new String[] {String.valueOf(10)}),
                "10"));
        paramMdl.setCir160YearLabelList(yearLabel);

        //月リスト
        for (int i = 0; i < 12; i++) {
            monthLabel.add(new LabelValueBean(
                    gsMsg.getMessage("cmn.months" , new String[] {String.valueOf(i)}),
                    Integer.toString(i)));
        }
        paramMdl.setCir160MonthLabelList(monthLabel);

        int cacSid = paramMdl.getCirAccountSid();
        boolean acntUserFlg = getAcntUserFlg(con, paramMdl, cacSid, initMdl);
        paramMdl.setCir160acntUserFlg(acntUserFlg);

        //新規登録 初期表示
        if (paramMdl.getCir160initFlg() == GSConstCircular.DSP_FIRST
                && paramMdl.getCirCmdMode() == GSConstCircular.CMDMODE_ADD) {

            paramMdl.setCir160initFlg(GSConstCircular.DSP_ALREADY);

            if (initMdl.getCinAutoDelKbn() == GSConstCircular.AUTO_DEL_ACCOUNT) {
                paramMdl.setCir160JdelKbn(String.valueOf(GSConstCircular.CIR_AUTO_DEL_NO));
                paramMdl.setCir160SdelKbn(String.valueOf(GSConstCircular.CIR_AUTO_DEL_NO));
                paramMdl.setCir160DdelKbn(String.valueOf(GSConstCircular.CIR_AUTO_DEL_NO));
            }

            //回覧板初期設定
            paramMdl.setCir160smlNtf(GSConstCircular.SMAIL_TSUUCHI);
            paramMdl.setCir160memoKbn(initMdl.getCinMemoKbn());
            paramMdl.setCir160memoPeriod(initMdl.getCinMemoDay());
            paramMdl.setCir160show(initMdl.getCinKouKbn());

        //編集 初期表示
        } else if (paramMdl.getCir160initFlg() == GSConstCircular.DSP_FIRST
                && paramMdl.getCirCmdMode() == GSConstCircular.CMDMODE_EDIT) {

            //アカウント情報を設定する
            CirAccountDao accountDao = new CirAccountDao(con);
            CirAccountModel accountMdl = accountDao.select(cacSid);

            if (accountMdl.getUsrSid() > 0) {
                paramMdl.setCir160AccountKbn(GSConstCircular.ACNT_DEF);
                paramMdl.setCir160DefActUsrSid(accountMdl.getUsrSid());
            }

            paramMdl.setCir160name(accountMdl.getCacName());
            paramMdl.setCir160biko(accountMdl.getCacBiko());

            //使用者を設定
            if (acntUserFlg) {
                CirAccountUserDao accountUserDao = new CirAccountUserDao(con);
                List<CirAccountUserModel> accountUserList
                           = accountUserDao.getAccountUserList(cacSid);
                String[] id = new String[accountUserList.size()];
                for (int index = 0; index < id.length; index++) {

                    if (accountUserList.get(index).getUsrSid() > 0) {
                        id[index] = String.valueOf(accountUserList.get(index).getUsrSid());
                    } else if (accountUserList.get(index).getGrpSid() >= 0) {
                        id[index] = "G"
                                  + String.valueOf(accountUserList.get(index).getGrpSid());
                    }
                }
                paramMdl.setCir160userKbnUser(id);
            }

            if (initMdl.getCinAutoDelKbn() == GSConstCircular.AUTO_DEL_ACCOUNT) {
                CirAdelDao delDao = new CirAdelDao(con);
                CirAdelModel delMdl = delDao.select(cacSid);
                if (delMdl == null) {
                    delMdl = new CirAdelModel();
                    delMdl.setCadJdelKbn(GSConstCircular.CIR_AUTO_DEL_NO);
                    delMdl.setCadJdelYear(0);
                    delMdl.setCadJdelMonth(0);
                    delMdl.setCadSdelKbn(GSConstCircular.CIR_AUTO_DEL_NO);
                    delMdl.setCadSdelYear(0);
                    delMdl.setCadSdelMonth(0);
                    delMdl.setCadDdelKbn(GSConstCircular.CIR_AUTO_DEL_NO);
                    delMdl.setCadDdelYear(0);
                    delMdl.setCadDdelMonth(0);
                }

                //受信タブ処理 選択値
                paramMdl.setCir160JdelKbn(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(
                                        paramMdl.getCir160JdelKbn()),
                                String.valueOf(delMdl.getCadJdelKbn())));

                //受信タブ 年
                paramMdl.setCir160JYear(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getCir160JYear()),
                                String.valueOf(delMdl.getCadJdelYear())));

                //受信タブ 月
                paramMdl.setCir160JMonth(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getCir160JMonth()),
                                String.valueOf(delMdl.getCadJdelMonth())));

                //送信タブ処理 選択値
                paramMdl.setCir160SdelKbn(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(
                                        paramMdl.getCir160SdelKbn()),
                                String.valueOf(delMdl.getCadSdelKbn())));

                //送信タブ 年
                paramMdl.setCir160SYear(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getCir160SYear()),
                                String.valueOf(delMdl.getCadSdelYear())));

                //送信タブ 月
                paramMdl.setCir160SMonth(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getCir160SMonth()),
                                String.valueOf(delMdl.getCadSdelMonth())));

                //ゴミ箱タブ処理 選択値
                paramMdl.setCir160DdelKbn(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(
                                        paramMdl.getCir160DdelKbn()),
                                String.valueOf(delMdl.getCadDdelKbn())));

                //ゴミ箱タブ 年
                paramMdl.setCir160DYear(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getCir160DYear()),
                                String.valueOf(delMdl.getCadDdelYear())));

                //ゴミ箱タブ 月
                paramMdl.setCir160DMonth(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getCir160DMonth()),
                                String.valueOf(delMdl.getCadDdelMonth())));
            }

            //テーマ
            paramMdl.setCir160theme(accountMdl.getCacTheme());

            //ショートメール通知
            paramMdl.setCir160smlNtf(accountMdl.getCacSmlNtf());

            //回覧板初期値設定
            paramMdl.setCir160memoKbn(accountMdl.getCacMemoKbn());
            paramMdl.setCir160memoPeriod(accountMdl.getCacMemoDay());
            paramMdl.setCir160show(accountMdl.getCacKouKbn());

            paramMdl.setCir160initFlg(GSConstCircular.DSP_ALREADY);
        }

        //グループコンボを設定
//        List<LabelValueBean> groupCombo = new ArrayList<LabelValueBean>();
//        groupCombo.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));
//
//        ArrayList<GroupModel> grpList = grpBiz.getGroupCombList(con);
//        for (GroupModel grpMdl : grpList) {
//            LabelValueBean label = new LabelValueBean(grpMdl.getGroupName(),
//                                                    String.valueOf(grpMdl.getGroupSid()));
//            groupCombo.add(label);
//        }



        //グループコンボを設定
        paramMdl.setUserKbnGroupCombo(__getGroupLabelList(con, reqMdl));

        //使用者 グループコンボ、ユーザコンボを設定
        //_setGroupCombo(con, paramMdl);
        _setUserCombo(con, paramMdl, reqMdl);

        //テーマコンボを設定
        List<LabelValueBean> themeCombo = new ArrayList<LabelValueBean>();
        themeCombo.add(
                new LabelValueBean(gsMsg.getMessage("cmn.notset"),
                                            String.valueOf(GSConstSmail.SAC_THEME_NOSET)));

        CmnThemeDao themeDao = new CmnThemeDao(con);
        List<CmnThemeModel> themeList = themeDao.select();
        for (CmnThemeModel themeData : themeList) {
            themeCombo.add(
                    new LabelValueBean(themeData.getCtmName(),
                                                    String.valueOf(themeData.getCtmSid())));
        }
        paramMdl.setCir160themeList(themeCombo);

        //管理者設定 ショートメール通知区分を設定
        CirAconfModel confMdl = cirBiz.getCirAdminData(con, reqMdl.getSmodel().getUsrsid());
        paramMdl.setCir160SmlNtfKbn(confMdl.getCafSmailSendKbn());

    }

    /**
     * <br>[機  能] アカウントの削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void deleteAccount(Connection con, Cir160ParamModel paramMdl, int userSid)
   throws SQLException {

        log__.info("アカウント削除開始");

        boolean commit = false;
        try {
            CirAccountDao accountDao = new CirAccountDao(con);
            accountDao.updateJkbn(paramMdl.getCirAccountSid(), GSConstCircular.CAC_JKBN_DELETE);
            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.info("アカウントの削除に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }

        log__.info("アカウント削除終了");
    }

//    /**
//     * <br>[機  能] グループコンボを設定する
//     * <br>[解  説]
//     * <br>[備  考]
//     * @param con コネクション
//     * @param paramMdl パラメータ情報
//     * @throws SQLException SQL実行時例外
//     */
//    protected void _setGroupCombo(Connection con, Cir160ParamModel paramMdl)
//    throws SQLException {
//        List<LabelValueBean> groupCombo = new ArrayList<LabelValueBean>();
//        List<LabelValueBean> selectGroupCombo = new ArrayList<LabelValueBean>();
//
//        String[] selectGrpSid = paramMdl.getCir160userKbnGroup();
//        if (selectGrpSid == null) {
//            selectGrpSid = new String[0];
//        }
//        Arrays.sort(selectGrpSid);
//
//        GroupBiz grpBiz = new GroupBiz();
//        ArrayList<GroupModel> gpList = grpBiz.getGroupCombList(con);
//        for (GroupModel grpMdl : gpList) {
//            LabelValueBean label = new LabelValueBean(grpMdl.getGroupName(),
//                                                String.valueOf(grpMdl.getGroupSid()));
//            if (Arrays.binarySearch(selectGrpSid, String.valueOf(grpMdl.getGroupSid())) < 0) {
//                groupCombo.add(label);
//            } else {
//                selectGroupCombo.add(label);
//            }
//        }
//
//        paramMdl.setUserKbnGroupSelectCombo(selectGroupCombo);
//        paramMdl.setUserKbnGroupNoSelectCombo(groupCombo);
//
//    }

    /**
     * <br>[機  能] ユーザコンボを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    protected void _setUserCombo(Connection con, Cir160ParamModel paramMdl, RequestModel reqMdl)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);

        int grpSid = NullDefault.getInt(paramMdl.getCir160userKbnUserGroup(), -1);

        String[] selectUserSid = paramMdl.getCir160userKbnUser();
        if (selectUserSid == null) {
            selectUserSid = new String[0];
        }

        //デフォルトユーザを設定
        if (paramMdl.getCir160DefActUsrSid() > 0) {
            boolean defFlg = false;
            ArrayList<String> usrSidList = new ArrayList<String>();
            for (String usid : selectUserSid) {
                usrSidList.add(usid);
                if (usid.equals(String.valueOf(paramMdl.getCir160DefActUsrSid()))) {
                    defFlg = true;
                }
            }
            if (!defFlg) {
                usrSidList.add(String.valueOf(paramMdl.getCir160DefActUsrSid()));
            }
            paramMdl.setCir160userKbnUser(
                    (String[]) usrSidList.toArray(new String[usrSidList.size()]));
            selectUserSid = (String[]) usrSidList.toArray(new String[usrSidList.size()]);
        }

        Arrays.sort(selectUserSid);
//
//        UserBiz userBiz = new UserBiz();
//        ArrayList<BaseUserModel> ulist
//                = userBiz.getBaseUserList(con, selectUserSid);
//        LabelValueBean labelBean = null;
//        List <LabelValueBean> selectUserList = new ArrayList<LabelValueBean>();
//        for (BaseUserModel umodel : ulist) {
//            labelBean = new LabelValueBean();
//            labelBean.setLabel(umodel.getUsisei() + " " + umodel.getUsimei());
//            labelBean.setValue(String.valueOf(umodel.getUsrsid()));
//            selectUserList.add(labelBean);
//        }
        paramMdl.setUserKbnUserSelectCombo(__getMemberList(selectUserSid, con));

        if (grpSid == Cir160Form.GRP_SID_GRPLIST) {
            //グループを全て取得
            GroupDao dao = new GroupDao(con);
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);
            //除外するグループSID
            List<String> fullGrepList = new ArrayList<String>();
            if (selectUserSid != null) {
                fullGrepList = Arrays.asList(selectUserSid);
            }
            List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();
            for (GroupModel bean : allGpList) {
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    labelListAdd.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
                paramMdl.setUserKbnUserNoSelectCombo(labelListAdd);
            }
        } else {
            if (grpSid >= 0) {

                ArrayList<String> usrSidList = new ArrayList<String>();
                for (String sid : selectUserSid) {
                    if (GSValidateUtil.isNumber(sid)) {
                        usrSidList.add(sid);
                    }
                }

                UserBiz userBiz = new UserBiz();
                paramMdl.setUserKbnUserNoSelectCombo(
                   userBiz.getNormalUserLabelList(con, grpSid,
                       (String[]) usrSidList.toArray(new String[usrSidList.size()]), false, gsMsg));
            }
        }


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
     * <br>[機  能] ユーザ削除可能チェック
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     * @return jsonData JSONObject
     */
    public ActionErrors checkCanDelUsr(Cir160ParamModel paramMdl,
                             RequestModel reqMdl,
                             Connection con)
        throws SQLException {

        ActionErrors errors = new ActionErrors();

        if (paramMdl.getCir160DefActUsrSid() > 0) {

            for (String sid : paramMdl.getCir160userKbnUserSelect()) {
                if (sid.equals(String.valueOf(paramMdl.getCir160DefActUsrSid()))) {
                    ActionMessage msg = null;
                    msg = new ActionMessage(
                            "error.common.no.delete",
                            StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getCir160name()));
                    StrutsUtil.addMessage(errors, msg, "usrSid");
                }
            }

        }
        return errors;
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
        labelBean.setValue(String.valueOf(Cir160Form.GRP_SID_GRPLIST));
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
     * <br>[機  能] 使用者設定フラグを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param cacSid アカウントSID
     * @param initMdl ショートメール管理者設定
     * @return 使用者設定フラグ
     * @throws SQLException SQL実行時例外
     */
    public boolean getAcntUserFlg(Connection con, Cir160ParamModel paramMdl,
                                                int cacSid, CirInitModel initMdl)
    throws SQLException {
        boolean acntUserFlg = false;

        if (paramMdl.getCirCmdMode() == GSConstCircular.CMDMODE_ADD) {
            cacSid = 0;
        }

        if (initMdl == null) {
            initMdl = new CirInitModel();
            CirCommonBiz cirBiz = new CirCommonBiz(con);
            initMdl = cirBiz.getCirInitConf(0, con);
        }
        boolean admUserFlg = initMdl.getCinAcntUser() == GSConstCircular.CIN_ACNT_USER_NO;

        if (paramMdl.isCir010adminUser()) {
            int cacType = GSConstCircular.CAC_TYPE_NORMAL;
            if (cacSid > 0) {
                CirAccountDao accountDao = new CirAccountDao(con);
                CirAccountModel accountMdl = accountDao.select(cacSid);
                if (accountMdl != null) {
                    cacType =  accountMdl.getCacType();
                }

                acntUserFlg = admUserFlg || cacType != GSConstCircular.CAC_TYPE_NORMAL;
            } else {
                acntUserFlg = true;
            }
        } else {
            acntUserFlg = admUserFlg;
        }

        return acntUserFlg;
    }
}
