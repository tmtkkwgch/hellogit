package jp.groupsession.v2.anp.anp150;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.anp.AnpiCommonBiz;
import jp.groupsession.v2.anp.GSConstAnpi;
import jp.groupsession.v2.anp.model.AnpLabelValueModel;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;


/**
 * <br>[機  能] 安否確認 管理者設定 緊急連絡先一括設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp150Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp150Biz.class);

    /**
     * <br>[機  能] 初期表示情報をセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param anp150Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con DBコネクション
     * @throws Exception 実行例外
     */
    public void setInitData(Anp150ParamModel anp150Model, RequestModel reqMdl, Connection con)
                throws Exception {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //全て表示区分(コンボボックス)
        boolean allGroupUserFlg = false;

        AnpiCommonBiz anpiBiz = new AnpiCommonBiz();

        //安否確認管理者グループリストを取得
        List<AnpLabelValueModel> gpLabel =
                anpiBiz.getGroupLabel(reqMdl, con, sessionUsrSid, allGroupUserFlg);
        GsMessage gsMsg = new GsMessage(reqMdl);

        //"グループ一覧" を最上位に追加
        gpLabel.add(0, new AnpLabelValueModel(gsMsg.getMessage("cmn.grouplist"),
                String.valueOf(GSConstAnpi.GRP_SID_GRPLIST), "0"));
        anp150Model.setAnp150GroupLabel(gpLabel);

        //グループリストに初期表示するデフォルトグループを取得
        String dspGpSidStr = anp150Model.getAnp150SelectGroupSid();
        if (StringUtil.isNullZeroString(dspGpSidStr)) {
            dspGpSidStr = anpiBiz.getDefaultGroupSid(con, sessionUsrSid, allGroupUserFlg);
            anp150Model.setAnp150SelectGroupSid(dspGpSidStr);
        }
        dspGpSidStr = anpiBiz.getEnableSelectGroup(gpLabel, dspGpSidStr,
                anpiBiz.getDefaultGroupSid(con, sessionUsrSid, allGroupUserFlg));
        anp150Model.setAnp150SelectGroupSid(dspGpSidStr);

        int dspGpSid = AnpiCommonBiz.getGroupSidfromDisp(dspGpSidStr);
        boolean isMyGroup = AnpiCommonBiz.isMyGroupSidforDisp(dspGpSidStr);

        List<LabelValueBean> labelListAdd = new ArrayList<LabelValueBean>();
        if (dspGpSid == GSConstAnpi.GRP_SID_GRPLIST) {
            GroupDao dao = new GroupDao(con);
            //グループを全て取得
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
            ArrayList<GroupModel> allGpList = dao.getGroupTree(sortMdl);
            //除外するグループSID
            List<String> fullGrepList = new ArrayList<String>();
            if (anp150Model.getAnp150TargetList() != null) {
                fullGrepList = Arrays.asList(anp150Model.getAnp150TargetList());
            }
            for (GroupModel bean : allGpList) {
                if (!fullGrepList.contains(String.valueOf("G" + bean.getGroupSid()))) {
                    labelListAdd.add(new LabelValueBean(
                            bean.getGroupName(), String.valueOf("G" + bean.getGroupSid())));
                }
            }
        } else {
            //除外するユーザSIDを設定
            ArrayList<Integer> usrSids = new ArrayList<Integer>();
            String [] users = anp150Model.getAnp150TargetList();
            if (users != null && users.length > 0) {
                for (String str : users) {
                    usrSids.add(new Integer(NullDefault.getInt(str, -1)));
                }
            }
            UserBiz userBiz = new UserBiz();
            List<CmnUsrmInfModel> usList =
                    userBiz.getBelongUserList(con, dspGpSid, usrSids, sessionUsrSid, isMyGroup);

            for (CmnUsrmInfModel cuiMdl : usList) {
                labelListAdd.add(new LabelValueBean(cuiMdl.getUsiSei() + "　" + cuiMdl.getUsiMei(),
                        String.valueOf(cuiMdl.getUsrSid())));
            }
        }

        //設定非対象ユーザ・グループリスト 右
        anp150Model.setAnp150NoTargetLabel(labelListAdd);
        //設定対象ユーザ・グループリスト 左
        anp150Model.setAnp150TargetLabel(__getAdminFullLavle(anp150Model, con));

        //メールアドレスコンボ設定
        anp150Model.setAnp150MailLabel(__getMailCombo(reqMdl));
    }


    /**
     * <br>[機  能] ユーザリストに選択されているユーザリストを追加して戻します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userList   元のユーザリスト
     * @param selectSids 選択ユーザリスト
     * @return 選択ユーザを追加したユーザリスト
     * @throws Exception 実行例外
     */
    public String[] getUserListAdd(String[] userList, String[] selectSids)
                        throws Exception {
        if (selectSids == null) {
            return userList;
        }
        if (selectSids.length < 1) {
            return userList;
        }

        log__.debug("追加後ユーザを取得");

        //元のユーザリストから、戻り配列を作成
        List<String> newList = new ArrayList<String>();
        if (userList != null && userList.length > 0) {
            for (String sid: userList) {
                newList.add(sid);
            }
        }

        //選択されているユーザSIDを追加
        if (selectSids != null && selectSids.length > 0) {
            for (String sid: selectSids) {
                newList.add(sid);
            }
        }

        String[] ret = null;
        if (newList.size() > 0) {
            ret = (String[]) newList.toArray(new String[newList.size()]);
        }
        return ret;
    }

    /**
     * <br>[機  能] ユーザリストから選択されているユーザを削除して戻します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userList   元のユーザリスト
     * @param selectSids 選択ユーザリスト
     * @return 選択ユーザを削除したユーザリスト
     * @throws Exception 実行例外
     */
    public String[] getUserListDel(String[] userList, String[] selectSids)
                        throws Exception {

        log__.debug("削除後ユーザを取得");

        if (userList == null || userList.length == 0) {
            return null;
        }

        //元のリストから選択されているユーザ以外を追加
        List<String> newList = new ArrayList<String>();
        String[] selects = new String[0];
        if (selectSids != null) {
            selects = Arrays.copyOf(selectSids, selectSids.length);
        }
        Arrays.sort(selects);

        for (String sid: userList) {
            if (Arrays.binarySearch(selects, sid) < 0) {
                newList.add(sid);
            }
        }

        String[] ret = null;
        if (newList.size() > 0) {
            ret = (String[]) newList.toArray(new String[newList.size()]);
        }
        return ret;
    }

    /**
     * <br>[機  能] 安否確認管理者 管理者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp150Model パラメータモデル
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAdminFullLavle(
            Anp150ParamModel anp150Model, Connection con)
    throws SQLException {

        //取得するユーザSID・グループSID
        String[] leftFull = anp150Model.getAnp150TargetList();
        return __getAdminLavle(leftFull, con);
    }

    /**
     * <br>[機  能] 安否確認管理者 管理者一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param left 取得するユーザSID・グループSID
     * @param con コネクション
     * @return グループ一覧
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<LabelValueBean> __getAdminLavle(String[] left, Connection con)
    throws SQLException {

        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();

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
        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(grpSids);
        LabelValueBean lavelBean = null;
        for (GroupModel gmodel : glist) {
            lavelBean = new LabelValueBean();
            lavelBean.setLabel(gmodel.getGroupName());
            lavelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            ret.add(lavelBean);
        }
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
     * <br>[機  能] メールアドレスコンボを生成する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエストモデル
     * @return List (in LabelValueBean)  メールアドレスコンボ
     * @throws Exception 実行時例外
     */
    private List<LabelValueBean> __getMailCombo(RequestModel reqMdl)
            throws Exception {

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
}