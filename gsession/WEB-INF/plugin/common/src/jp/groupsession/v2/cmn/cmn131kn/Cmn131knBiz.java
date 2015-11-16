package jp.groupsession.v2.cmn.cmn131kn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.cmn131.Cmn131Biz;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.MyGroupDao;
import jp.groupsession.v2.cmn.dao.UsidSelectGrpNameDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.usr040.Usr040DspModel;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] メイン 個人設定 マイグループ登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn131knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn131knBiz.class);
    /** GSメッセージ */
    public GsMessage gsMsg__ = new GsMessage();

    /**
     * <p>コンストラクタ
     * @param gsMsg GsMessage
     */
    public Cmn131knBiz(GsMessage gsMsg) {
        gsMsg__ = gsMsg;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn131knModel パラメータ格納モデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @throws Exception ユーザ情報コピー例外
     */
    public void setInitData(Cmn131knParamModel cmn131knModel,
                            Connection con)
                            throws SQLException, Exception {

        //画面タイトル
        Cmn131Biz biz = new Cmn131Biz(gsMsg__);
        cmn131knModel.setCmn131dspTitle(
                biz.getDspTitle(NullDefault.getString(cmn131knModel.getCmn130cmdMode(), "")));

        //メモ
        cmn131knModel.setCmn131knMemo(
                NullDefault.getString(
                        StringUtilHtml.transToHTml(cmn131knModel.getCmn131memo()), ""));

        //メンバー取得
        CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
        CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();

        CmnUsrmInfDao usiDao = new CmnUsrmInfDao(con);
        List <CmnUsrmInfModel> userDataList =
                usiDao.getUsersInfList(cmn131knModel.getCmn131userSid(), sortMdl);

        cmn131knModel.setCmn131knMemberList(
                __getDspMdlList(con, userDataList)
                );

        //共有権限メンバー取得
        ArrayList<LabelValueBean> refLabelListSelect = new ArrayList<LabelValueBean>();
        //取得するユーザSID・グループSID
        String [] left = cmn131knModel.getCmn131refUserSid();
        ArrayList<Integer> refGrpSids = new ArrayList<Integer>();
        ArrayList<String> refUsrSids = new ArrayList<String>();

        //ユーザSIDとグループSIDを分離
        if (left != null) {
            for (int i = 0; i < left.length; i++) {
                String str = NullDefault.getString(left[i], "-1");
                if (str.contains(new String("G").subSequence(0, 1))) {
                    //グループ
                    refGrpSids.add(new Integer(str.substring(1, str.length())));
                } else {
                    //ユーザ
                    refUsrSids.add(str);
                }
            }
        }

        //グループ情報取得
        UsidSelectGrpNameDao gdao = new UsidSelectGrpNameDao(con);
        ArrayList<GroupModel> glist = gdao.selectGroupNmListOrderbyClass(refGrpSids);
        LabelValueBean labelBean = null;
        for (GroupModel gmodel : glist) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(gmodel.getGroupName());
            labelBean.setValue("G" + String.valueOf(gmodel.getGroupSid()));
            refLabelListSelect.add(labelBean);
        }
        //共有ユーザ情報取得
        userDataList = usiDao.getUsersInfList(
                (String[]) refUsrSids.toArray(new String[refUsrSids.size()]),
                sortMdl);

        cmn131knModel.setCmn131knRefMemberList(
                __getDspMdlList(con, userDataList)
                );


        for (CmnUsrmInfModel umodel : userDataList) {
            labelBean = new LabelValueBean();
            labelBean.setLabel(umodel.getUsiSei() + " " + umodel.getUsiMei());
            labelBean.setValue(String.valueOf(umodel.getUsrSid()));
            refLabelListSelect.add(labelBean);
        }
        cmn131knModel.setCmn131refMbLabelList(refLabelListSelect);
    }

    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn131knModel パラメータ格納モデル
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doAddEdit(
        Cmn131knParamModel cmn131knModel,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        String cmdMode = NullDefault.getString(cmn131knModel.getCmn130cmdMode(), "");
        if (cmdMode.equals(Cmn131Biz.MODE_ADD)) {
            //登録
            __doInsert(cmn131knModel, con, cntCon, userSid);
        } else if (cmdMode.equals(Cmn131Biz.MODE_EDIT)) {
            //更新
            __doUpdate(cmn131knModel, con, userSid);
        }
    }

    /**
     * <br>[機  能] 登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn131knModel パラメータ格納モデル
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    private void __doInsert(
        Cmn131knParamModel cmn131knModel,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        boolean commit = false;
        try {
            con.setAutoCommit(false);

            //マイグループSID採番
            int myGroupSid = (int) cntCon.getSaibanNumber(GSConstCommon.SBNSID_COMMON,
                                                           GSConstCommon.SBNSID_SUB_MYGROUP,
                                                           userSid);
            //システム日付
            UDate now = new UDate();

            //登録用Model作成
            CmnMyGroupModel cmgMdl = new CmnMyGroupModel();
            cmgMdl.setUsrSid(userSid);
            cmgMdl.setMgpSid(myGroupSid);
            cmgMdl.setMgpName(NullDefault.getString(cmn131knModel.getCmn131name(), ""));
            cmgMdl.setMgpMemo(NullDefault.getString(cmn131knModel.getCmn131memo(), ""));
            cmgMdl.setMgpAuid(userSid);
            cmgMdl.setMgpAdate(now);
            cmgMdl.setMgpEuid(userSid);
            cmgMdl.setMgpEdate(now);

            //共有権限メンバー取得
            //取得するユーザSID・グループSID
            String [] left = cmn131knModel.getCmn131refUserSid();
            ArrayList<String> refGrpSids = new ArrayList<String>();
            ArrayList<String> refUsrSids = new ArrayList<String>();

            //ユーザSIDとグループSIDを分離
            if (left != null) {
                for (int i = 0; i < left.length; i++) {
                    String str = NullDefault.getString(left[i], "-1");
                    if (str.contains(new String("G").subSequence(0, 1))) {
                        //グループ
                        refGrpSids.add(str.substring(1, str.length()));
                    } else {
                        //ユーザ
                        refUsrSids.add(str);
                    }
                }
            }

            //insert
            MyGroupDao mgDao = new MyGroupDao(con);
            mgDao.insertMyGroup(cmgMdl,
                    cmn131knModel.getCmn131userSid(),
                    refUsrSids.toArray(new String[refUsrSids.size()]),
                    refGrpSids.toArray(new String[refGrpSids.size()])
                    );

            con.commit();
            commit = true;
        } catch (SQLException e) {
            log__.warn("マイグループ登録に失敗", e);
            throw e;
        } finally {
            if (!commit) {
                con.rollback();
            }
        }
    }

    /**
     * <br>[機  能] 更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn131knModel パラメータ格納モデル
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    private void __doUpdate(Cmn131knParamModel cmn131knModel, Connection con, int userSid)
    throws SQLException {

        try {
            con.setAutoCommit(false);

            //マイグループSID取得
            int myGroupSid = NullDefault.getInt(cmn131knModel.getCmn130selectGroupSid(), 0);
            //システム日付
            UDate now = new UDate();

            //登録用Model作成
            CmnMyGroupModel cmgMdl = new CmnMyGroupModel();
            cmgMdl.setUsrSid(userSid);
            cmgMdl.setMgpSid(myGroupSid);
            cmgMdl.setMgpName(NullDefault.getString(cmn131knModel.getCmn131name(), ""));
            cmgMdl.setMgpMemo(NullDefault.getString(cmn131knModel.getCmn131memo(), ""));
            cmgMdl.setMgpEuid(userSid);
            cmgMdl.setMgpEdate(now);

            //共有権限メンバー取得
            //取得するユーザSID・グループSID
            String [] left = cmn131knModel.getCmn131refUserSid();
            ArrayList<String> refGrpSids = new ArrayList<String>();
            ArrayList<String> refUsrSids = new ArrayList<String>();

            //ユーザSIDとグループSIDを分離
            if (left != null) {
                for (int i = 0; i < left.length; i++) {
                    String str = NullDefault.getString(left[i], "-1");
                    if (str.contains(new String("G").subSequence(0, 1))) {
                        //グループ
                        refGrpSids.add(str.substring(1, str.length()));
                    } else {
                        //ユーザ
                        refUsrSids.add(str);
                    }
                }
            }

            //update
            MyGroupDao mgDao = new MyGroupDao(con);
            mgDao.updateMyGroup(cmgMdl,
                    cmn131knModel.getCmn131userSid(),
                    refUsrSids.toArray(new String[refUsrSids.size()]),
                    refGrpSids.toArray(new String[refGrpSids.size()])
                    );

            con.commit();
        } catch (SQLException e) {
            log__.warn("マイグループ更新に失敗", e);
            JDBCUtil.rollback(con);
            throw e;
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
    private ArrayList<Usr040DspModel> __getDspMdlList(Connection con, List<CmnUsrmInfModel> ulist)
            throws SQLException, Exception {

        ArrayList<Usr040DspModel> ret = new ArrayList<Usr040DspModel>();
        Usr040DspModel dspMdl = null;
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
            dspMdl = new Usr040DspModel();

            //CmnUsrmInfModel → Usr040DspModel パラメータのコピー
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
}
