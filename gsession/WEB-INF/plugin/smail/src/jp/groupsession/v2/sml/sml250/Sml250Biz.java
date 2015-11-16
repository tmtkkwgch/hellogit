package jp.groupsession.v2.sml.sml250;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
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
import jp.groupsession.v2.sml.biz.SmlCommonBiz;
import jp.groupsession.v2.sml.dao.SmlAccountAutoDestDao;
import jp.groupsession.v2.sml.dao.SmlAccountDao;
import jp.groupsession.v2.sml.dao.SmlAccountUserDao;
import jp.groupsession.v2.sml.dao.SmlAdelDao;
import jp.groupsession.v2.sml.model.SmlAccountModel;
import jp.groupsession.v2.sml.model.SmlAccountUserModel;
import jp.groupsession.v2.sml.model.SmlAdelModel;
import jp.groupsession.v2.sml.model.SmlAdminModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ショートメール アカウント登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml250Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml250Biz.class);

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
    public void setInitData(Connection con, Sml250ParamModel paramMdl, RequestModel reqMdl)
    throws Exception {

        //自動削除区分を設定
        SmlCommonBiz smlBiz = new SmlCommonBiz(con, reqMdl);
        SmlAdminModel admMdl = new SmlAdminModel();
        admMdl = smlBiz.getSmailAdminConf(reqMdl.getSmodel().getUsrsid(), con);
        paramMdl.setSml250autoDelKbn(admMdl.getSmaAutoDelKbn());

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
        paramMdl.setSml250YearLabelList(yearLabel);

        //月リスト
        for (int i = 0; i < 12; i++) {
            monthLabel.add(new LabelValueBean(
                    gsMsg.getMessage("cmn.months" , new String[] {String.valueOf(i)}),
                    Integer.toString(i)));
        }
        paramMdl.setSml250MonthLabelList(monthLabel);

        int sacSid = paramMdl.getSmlAccountSid();
        boolean acntUserFlg = getAcntUserFlg(con, paramMdl, sacSid, admMdl);
        paramMdl.setSml250acntUserFlg(acntUserFlg);

        //新規登録 初期表示
        if (paramMdl.getSml250initFlg() == GSConstSmail.DSP_FIRST
                && paramMdl.getSmlCmdMode() == GSConstSmail.CMDMODE_ADD) {

            paramMdl.setSml250initFlg(GSConstSmail.DSP_ALREADY);

            if (admMdl.getSmaAutoDelKbn() == GSConstSmail.AUTO_DEL_ACCOUNT) {
                paramMdl.setSml250JdelKbn(String.valueOf(GSConstSmail.SML_AUTO_DEL_NO));
                paramMdl.setSml250SdelKbn(String.valueOf(GSConstSmail.SML_AUTO_DEL_NO));
                paramMdl.setSml250WdelKbn(String.valueOf(GSConstSmail.SML_AUTO_DEL_NO));
                paramMdl.setSml250DdelKbn(String.valueOf(GSConstSmail.SML_AUTO_DEL_NO));
            }


        //編集 初期表示
        } else if (paramMdl.getSml250initFlg() == GSConstSmail.DSP_FIRST
                && paramMdl.getSmlCmdMode() == GSConstSmail.CMDMODE_EDIT) {

            //アカウント情報を設定する
            SmlAccountDao accountDao = new SmlAccountDao(con);
            SmlAccountModel accountMdl = accountDao.select(sacSid);

            if (accountMdl.getUsrSid() > 0) {
                paramMdl.setSml250AccountKbn(GSConstSmail.ACNT_DEF);
                paramMdl.setSml250DefActUsrSid(accountMdl.getUsrSid());
            }

            paramMdl.setSml250name(accountMdl.getSacName());
            paramMdl.setSml250biko(accountMdl.getSacBiko());

            if (acntUserFlg) {
                //使用者を設定
                SmlAccountUserDao accountUserDao = new SmlAccountUserDao(con);
                List<SmlAccountUserModel> accountUserList = accountUserDao.select(sacSid);
                String[] id = new String[accountUserList.size()];
                for (int index = 0; index < id.length; index++) {

                    if (accountUserList.get(index).getUsrSid() > 0) {
                        id[index] = String.valueOf(accountUserList.get(index).getUsrSid());
                    } else {
                        id[index] = "G"
                                  + String.valueOf(accountUserList.get(index).getGrpSid());
                    }
                }
                paramMdl.setSml250userKbnUser(id);
            }

            //送信形式
            paramMdl.setSml250sendType(accountMdl.getSacSendMailtype());

            //テーマ
            paramMdl.setSml250theme(accountMdl.getSacTheme());

            //引用符
            paramMdl.setSml250quotes(accountMdl.getSacQuotes());

            if (admMdl.getSmaAutoDelKbn() == GSConstSmail.AUTO_DEL_ACCOUNT) {
                SmlAdelDao delDao = new SmlAdelDao(con);
                SmlAdelModel delMdl = delDao.select(sacSid);
                if (delMdl == null) {
                    delMdl = new SmlAdelModel();
                    delMdl.setSadJdelKbn(GSConstSmail.SML_AUTO_DEL_NO);
                    delMdl.setSadJdelYear(0);
                    delMdl.setSadJdelMonth(0);
                    delMdl.setSadSdelKbn(GSConstSmail.SML_AUTO_DEL_NO);
                    delMdl.setSadSdelYear(0);
                    delMdl.setSadSdelMonth(0);
                    delMdl.setSadWdelKbn(GSConstSmail.SML_AUTO_DEL_NO);
                    delMdl.setSadWdelYear(0);
                    delMdl.setSadWdelMonth(0);
                    delMdl.setSadDdelKbn(GSConstSmail.SML_AUTO_DEL_NO);
                    delMdl.setSadDdelYear(0);
                    delMdl.setSadDdelMonth(0);
                }

                //受信タブ処理 選択値
                paramMdl.setSml250JdelKbn(
                        NullDefault.getStringZeroLength(
                                paramMdl.getSml250JdelKbn(),
                                String.valueOf(delMdl.getSadJdelKbn())));

                //受信タブ 年
                paramMdl.setSml250JYear(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSml250JYear()),
                                String.valueOf(delMdl.getSadJdelYear())));

                //受信タブ 月
                paramMdl.setSml250JMonth(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSml250JMonth()),
                                String.valueOf(delMdl.getSadJdelMonth())));

                //送信タブ処理 選択値
                paramMdl.setSml250SdelKbn(
                        NullDefault.getStringZeroLength(
                                paramMdl.getSml250SdelKbn(),
                                String.valueOf(delMdl.getSadSdelKbn())));

                //送信タブ 年
                paramMdl.setSml250SYear(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSml250SYear()),
                                String.valueOf(delMdl.getSadSdelYear())));

                //送信タブ 月
                paramMdl.setSml250SMonth(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSml250SMonth()),
                                String.valueOf(delMdl.getSadSdelMonth())));

                //草稿タブ処理 選択値
                paramMdl.setSml250WdelKbn(
                        NullDefault.getStringZeroLength(
                                paramMdl.getSml250WdelKbn(),
                                String.valueOf(delMdl.getSadWdelKbn())));

                //草稿タブ 年
                paramMdl.setSml250WYear(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSml250WYear()),
                                String.valueOf(delMdl.getSadWdelYear())));

                //草稿タブ 月
                paramMdl.setSml250WMonth(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSml250WMonth()),
                                String.valueOf(delMdl.getSadWdelMonth())));

                //ゴミ箱タブ処理 選択値
                paramMdl.setSml250DdelKbn(
                        NullDefault.getStringZeroLength(
                                paramMdl.getSml250DdelKbn(),
                                String.valueOf(delMdl.getSadDdelKbn())));

                //ゴミ箱タブ 年
                paramMdl.setSml250DYear(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSml250DYear()),
                                String.valueOf(delMdl.getSadDdelYear())));

                //ゴミ箱タブ 月
                paramMdl.setSml250DMonth(
                        NullDefault.getStringZeroLength(
                                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSml250DMonth()),
                                String.valueOf(delMdl.getSadDdelMonth())));
            }

            __loadAuteDest(paramMdl, con);
            paramMdl.setSml250initFlg(GSConstSmail.DSP_ALREADY);
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
        paramMdl.setSml250themeList(themeCombo);

        //引用符コンボを設定
        List<LabelValueBean> quotesCombo = new ArrayList<LabelValueBean>();
        int[] quotesList = {GSConstSmail.SAC_QUOTES_DEF,
                                    GSConstSmail.SAC_QUOTES_NONE,
                                    GSConstSmail.SAC_QUOTES_2,
                                    GSConstSmail.SAC_QUOTES_3,
                                    GSConstSmail.SAC_QUOTES_4,
                                    GSConstSmail.SAC_QUOTES_5};
        for (int quotes : quotesList) {
            quotesCombo.add(new LabelValueBean(SmlCommonBiz.getViewMailQuotes(quotes, reqMdl),
                                                                    Integer.toString(quotes)));
        }
        paramMdl.setSml250quotesList(quotesCombo);


        /************************  転送設定  *********************************/
        paramMdl.setSml250tensoKbn(admMdl.getSmaMailfw());
        if (paramMdl.getSmlAccountMode() == GSConstSmail.ACCOUNTMODE_COMMON
            && paramMdl.getSml250tensoKbn() == GSConstSmail.MAIL_FORWARD_OK) {

//            /** グループコンボセット **************************************************/
//            GroupBiz biz = new GroupBiz();
//            paramMdl.setSml250GpLabelList(biz.getGroupTreeLabelList(
//                    con, true, gsMsg, paramMdl.getSml250userKbnGroup()));

            /** 現在選択中のメンバーコンボセット **************************************/
            //UserBiz userBiz = new UserBiz();
            paramMdl.setSml250MbLabelList(
                    (__getMemberList(paramMdl.getSml250userSid(), con)));

            /** 追加用メンバーコンボセット ********************************************/

            //デフォルトユーザ存在フラグ
            boolean defUsrFlg = false;
            Map<String, String> usrSidMap = new HashMap<String, String>();

            //グループSID
//            int gpSid = NullDefault.getInt(paramMdl.getSml250groupSid(), -1);

            //除外するユーザSID
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            String[] userSids = paramMdl.getSml250userSid();
            if (userSids != null) {
                for (int i = 0; i < userSids.length; i++) {
                    usrSids.add(new Integer(NullDefault.getInt(userSids[i], -1)));
                    usrSidMap.put(userSids[i], userSids[i]);
                    if (new Integer(NullDefault.getInt(userSids[i], -1))
                            == paramMdl.getSml250DefActUsrSid()) {
                        defUsrFlg = true;
                    }

                }
            }

//            List<CmnUsrmInfModel> usList = null;
            List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();

//            if (paramMdl.getSml250userKbn() != USERKBN_USER) {
//                usList = userBiz.getBelongUserList(con, gpSid, usrSids);
//                for (int i = 0; i < usList.size(); i++) {
//                    CmnUsrmInfModel cuiMdl = usList.get(i);
//                    labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + cuiMdl.getUsiMei(),
//                                     String.valueOf(cuiMdl.getUsrSid())));
//                }
//            } else {
                for (LabelValueBean lv : paramMdl.getUserKbnUserSelectCombo()) {
                    if (!usrSidMap.containsKey(lv.getValue())) {
                        labelListAdd.add(lv);
                    }
                }
//            }

            //デフォルトユーザを追加
            for (LabelValueBean lv : labelListAdd) {
                if (lv.getValue().equals(String.valueOf(paramMdl.getSml250DefActUsrSid()))) {
                    defUsrFlg = true;
                }
            }
            if (!defUsrFlg && paramMdl.getSml250DefActUsrSid() > 0) {
                labelListAdd.add(new LabelValueBean(paramMdl.getSml250name(),
                        String.valueOf(paramMdl.getSml250DefActUsrSid())));
            }

            paramMdl.setSml250AdLabelList(labelListAdd);

            //メールアドレスコンボ設定
            paramMdl.setSml250MailList(__getMailCombo(reqMdl));
        }

        _setAutoDestDisp(paramMdl, con);

    }
    /**
    *
    * <br>[機  能] sid配列をユーザSIDリストとアカウントSIDリストに分割
    * <br>[解  説]
    * <br>[備  考]
    * @param strSids ソース配列
    * @param destUsrSidList ユーザSID格納先
    * @param destAccSidList アカウントSID格納先
    */
   protected void _splitSids(String[] strSids,
           List<String> destUsrSidList,
           List<String> destAccSidList) {
       if (strSids != null) {
           for (String strSid : strSids) {
               if (strSid != null && strSid.startsWith(GSConstSmail.SML_ACCOUNT_STR)) {
                   destAccSidList.add(String.valueOf(NullDefault.getInt(
                           strSid.substring(GSConstSmail.SML_ACCOUNT_STR.length()),
                           -1)));
               } else {
                   int usrSid = NullDefault.getInt(
                           strSid,
                           -1);
                   if (usrSid > GSConstUser.USER_RESERV_SID) {
                       destUsrSidList.add(String.valueOf(usrSid));
                   }
               }
           }
       }

   }
   /**
    *
    * <br>[機  能] 自動送信先の表示設定
    * <br>[解  説]
    * <br>[備  考]
    * @param paramMdl パラメータモデル
    * @param con コネクション
    * @throws SQLException SQL実行時例外
    */
   protected void _setAutoDestDisp(Sml250ParamModel paramMdl
           , Connection con) throws SQLException {
       UserBiz usrBiz = new UserBiz();
       SmlAccountDao sacDao = new SmlAccountDao(con);
       //To
       List<String> usrSidList = new ArrayList<String>();
       List<String> accSidList = new ArrayList<String>();
       List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
       String[] strSids = paramMdl.getSml250AutoDestToUsrSid();
       _splitSids(strSids, usrSidList, accSidList);
       labelList.addAll(usrBiz.getUserLabelList(con,
               usrSidList.toArray(new String[usrSidList.size()])));
       labelList.addAll(sacDao.selectSacSids2(
               accSidList.toArray(new String[accSidList.size()]), SmlAccountDao.JKBN_LIV));
       paramMdl.setSml250AutoDestToLabelList(labelList);
       //Cc
       usrSidList = new ArrayList<String>();
       accSidList = new ArrayList<String>();
       labelList = new ArrayList<LabelValueBean>();
       strSids = paramMdl.getSml250AutoDestCcUsrSid();
       _splitSids(strSids, usrSidList, accSidList);
       labelList.addAll(usrBiz.getUserLabelList(con,
               usrSidList.toArray(new String[usrSidList.size()])));
       labelList.addAll(sacDao.selectSacSids2(
               accSidList.toArray(new String[accSidList.size()]), SmlAccountDao.JKBN_LIV));
       paramMdl.setSml250AutoDestCcLabelList(labelList);
       //Bcc
       usrSidList = new ArrayList<String>();
       accSidList = new ArrayList<String>();
       labelList = new ArrayList<LabelValueBean>();
       strSids = paramMdl.getSml250AutoDestBccUsrSid();
       _splitSids(strSids, usrSidList, accSidList);
       labelList.addAll(usrBiz.getUserLabelList(con,
               usrSidList.toArray(new String[usrSidList.size()])));
       labelList.addAll(sacDao.selectSacSids2(
               accSidList.toArray(new String[accSidList.size()]), SmlAccountDao.JKBN_LIV));
       paramMdl.setSml250AutoDestBccLabelList(labelList);



   }
    /**
     *
     * <br>[機  能] DBから保管済みの自動送信先を読み込む
     * <br>[解  説]
     * <br>[備  考]
     * @param paramModel パラメータモデル
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void __loadAuteDest(Sml250ParamModel paramModel, Connection con)
            throws SQLException {
        SmlAccountAutoDestDao sadDao = new SmlAccountAutoDestDao(con);
        for (int type = 0; type < 3; type++) {
            List<SmlAccountModel> sacModels =
                    sadDao.getAutoDestAccounts(paramModel.getSmlAccountSid(), type);
            List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
            String[] sids = new String[sacModels.size()];
            for (int i = 0; i < sids.length; i++) {
                SmlAccountModel acc = sacModels.get(i);
                LabelValueBean bean = new LabelValueBean();
                if (acc.getUsrSid() > 0) {
                    bean.setValue(String.valueOf(acc.getUsrSid()));
                } else {
                    bean.setValue(GSConstSmail.SML_ACCOUNT_STR + String.valueOf(acc.getSacSid()));
                }
                bean.setLabel(acc.getSacName());
                labelList.add(bean);
                sids[i] = bean.getValue();
            }
            switch (type) {
             case GSConstSmail.SML_SEND_KBN_ATESAKI:
                 paramModel.setSml250AutoDestToLabelList(labelList);
                 paramModel.setSml250AutoDestToUsrSid(sids);
                 break;
             case GSConstSmail.SML_SEND_KBN_CC:
                 paramModel.setSml250AutoDestCcLabelList(labelList);
                 paramModel.setSml250AutoDestCcUsrSid(sids);
                 break;
             case GSConstSmail.SML_SEND_KBN_BCC:
                 paramModel.setSml250AutoDestBccLabelList(labelList);
                 paramModel.setSml250AutoDestBccUsrSid(sids);
                 break;
             default:
            }

        }
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
    public void deleteAccount(Connection con, Sml250ParamModel paramMdl, int userSid)
   throws SQLException {

        log__.info("アカウント削除開始");

        boolean commit = false;
        try {
            SmlAccountDao accountDao = new SmlAccountDao(con);
            //アカウントを論理削除
            accountDao.updateJkbn(paramMdl.getSmlAccountSid(), GSConstSmail.SAC_JKBN_DELETE);

            //ショートメール自動送信先を物理削除
            SmlAccountAutoDestDao saaDao = new SmlAccountAutoDestDao(con);
            saaDao.delete(paramMdl.getSmlAccountSid());

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
//    protected void _setGroupCombo(Connection con, Sml250ParamModel paramMdl)
//    throws SQLException {
//        List<LabelValueBean> groupCombo = new ArrayList<LabelValueBean>();
//        List<LabelValueBean> selectGroupCombo = new ArrayList<LabelValueBean>();
//
//        String[] selectGrpSid = paramMdl.getSml250userKbnGroup();
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
    protected void _setUserCombo(Connection con, Sml250ParamModel paramMdl, RequestModel reqMdl)
    throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl);

        int grpSid = NullDefault.getInt(paramMdl.getSml250userKbnUserGroup(), -1);

        String[] selectUserSid = paramMdl.getSml250userKbnUser();
        if (selectUserSid == null) {
            selectUserSid = new String[0];
        }

        //デフォルトユーザを設定
        if (paramMdl.getSml250DefActUsrSid() > 0) {
            boolean defFlg = false;
            ArrayList<String> usrSidList = new ArrayList<String>();
            for (String usid : selectUserSid) {
                usrSidList.add(usid);
                if (usid.equals(String.valueOf(paramMdl.getSml250DefActUsrSid()))) {
                    defFlg = true;
                }
            }
            if (!defFlg) {
                usrSidList.add(String.valueOf(paramMdl.getSml250DefActUsrSid()));
            }
            paramMdl.setSml250userKbnUser(
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

        if (grpSid == Sml250Form.GRP_SID_GRPLIST) {
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
    public ActionErrors checkCanDelUsr(Sml250ParamModel paramMdl,
                             RequestModel reqMdl,
                             Connection con)
        throws SQLException {

        ActionErrors errors = new ActionErrors();

        if (paramMdl.getSml250DefActUsrSid() > 0) {

            for (String sid : paramMdl.getSml250userKbnUserSelect()) {
                if (sid.equals(String.valueOf(paramMdl.getSml250DefActUsrSid()))) {
                    ActionMessage msg = null;
                    msg = new ActionMessage(
                            "error.common.no.delete",
                            StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getSml250name()));
                    StrutsUtil.addMessage(errors, msg, "usrSid");
                }
            }

        }
        return errors;
    }

    /**
     * 在席管理が利用可能かを  設定する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param pconfig プラグインコンフィグ
     * @throws SQLException SQL実行時例外
     */
    public void setCanUsePluginFlg(Sml250ParamModel paramMdl, Connection con, PluginConfig pconfig)
    throws SQLException {

        CommonBiz cmnBiz = new CommonBiz();
        //在席管理は利用可能か判定
        if (cmnBiz.isCanUsePlugin(GSConstSmail.PLUGIN_ID_ZAISEKI, pconfig)) {
            paramMdl.setSml250ZaisekiPlugin(GSConstSmail.PLUGIN_USE);
        } else {
            paramMdl.setSml250ZaisekiPlugin(GSConstSmail.PLUGIN_NOT_USE);
        }
    }

    /**
     * <br>[機  能] コンボで選択中のメンバーをメンバーリストから削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     */
    public void deleteMnb(Sml250ParamModel paramMdl) {

        //コンボで選択中
        String[] selectUserSid = paramMdl.getSml250selectUserSid();
        //メンバーリスト(コンボ表示に使用する値)
        String[] userSid = paramMdl.getSml250userSid();

        paramMdl.setSml250userSid(getDeleteMember(selectUserSid, userSid));
    }

    /**
     * <br>[機  能] 追加用メンバーコンボで選択中のメンバーをメンバーリストに追加する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     */
    public void addMnb(Sml250ParamModel paramMdl) {

        //追加用メンバー(選択中)
        String[] addUserSid = paramMdl.getSml250addUserSid();
        //メンバーリスト(コンボ表示に使用する値)
        String[] userSid = paramMdl.getSml250userSid();

        paramMdl.setSml250userSid(getAddMember(addUserSid, userSid));
    }


    /**
     * <br>[機  能] メールアドレスコンボを生成する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl RequestModel
     * @return List (in LabelValueBean)  メールアドレスコンボ
     * @throws SQLException SQL実行時例外
     */
    private List<LabelValueBean> __getMailCombo(RequestModel reqMdl) throws SQLException {

        List <LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl);
        String mailAdr1 = gsMsg.getMessage("cmn.mailaddress1");
        String mailAdr2 = gsMsg.getMessage("cmn.mailaddress2");
        String mailAdr3 = gsMsg.getMessage("cmn.mailaddress3");
        String mailAdr4 = gsMsg.getMessage("sml.122");

        labelList.add(new LabelValueBean(mailAdr1, "1"));
        labelList.add(new LabelValueBean(mailAdr2, "2"));
        labelList.add(new LabelValueBean(mailAdr3, "3"));
        labelList.add(new LabelValueBean(mailAdr4, "0"));

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
    private ArrayList <LabelValueBean> __getGroupLabelList(Connection con, RequestModel reqMdl)
    throws SQLException {

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl);
        LabelValueBean labelBean = new LabelValueBean();
        labelBean.setLabel(gsMsg.getMessage("cmn.grouplist"));
        labelBean.setValue(String.valueOf(Sml250Form.GRP_SID_GRPLIST));
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
     * @param sacSid アカウントSID
     * @param admMdl ショートメール管理者設定
     * @return 使用者設定フラグ
     * @throws SQLException SQL実行時例外
     */
    public boolean getAcntUserFlg(Connection con, Sml250ParamModel paramMdl,
                                                int sacSid, SmlAdminModel admMdl)
    throws SQLException {
        boolean acntUserFlg = false;

        if (paramMdl.getSmlCmdMode() == GSConstSmail.CMDMODE_ADD) {
            sacSid = 0;
        }

        if (admMdl == null) {
            SmlCommonBiz smlBiz = new SmlCommonBiz();
            admMdl = new SmlAdminModel();
            admMdl = smlBiz.getSmailAdminConf(0, con);
        }

        boolean admUserFlg = admMdl.getSmaAcntUser() == GSConstSmail.KANRI_ACCOUNT_USER_NO;
        if (paramMdl.getSmlAccountMode() == GSConstSmail.ACCOUNTMODE_COMMON) {
            int sacType = GSConstSmail.SAC_TYPE_NORMAL;
            if (sacSid > 0) {
                SmlAccountDao accountDao = new SmlAccountDao(con);
                sacType = accountDao.getSacType(sacSid);
                acntUserFlg = admUserFlg || sacType != GSConstSmail.SAC_TYPE_NORMAL;
            } else {
                acntUserFlg = true;
            }
        } else {
            acntUserFlg = admUserFlg;
        }

        return acntUserFlg;
    }
}
