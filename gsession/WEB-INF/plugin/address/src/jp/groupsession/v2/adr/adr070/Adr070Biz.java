package jp.groupsession.v2.adr.adr070;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.biz.AddressBiz;
import jp.groupsession.v2.adr.dao.AddressDao;
import jp.groupsession.v2.adr.dao.AdrAconfDao;
import jp.groupsession.v2.adr.dao.AdrCompanyBaseDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.dao.AdrUconfDao;
import jp.groupsession.v2.adr.model.AdrAconfModel;
import jp.groupsession.v2.adr.model.AdrCompanyBaseModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.adr.model.AdrUconfModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.dao.base.CmnTdfkDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.cmn.model.base.CmnTdfkModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アドレス帳 アドレスインポート画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr070Biz {

    /** リクエスト */
    protected RequestModel reqMdl_ = null;

//    /**
//     * <br>[機  能] デフォルトコンストラクタ
//     * <br>[解  説]
//     * <br>[備  考]
//     */
//    public Adr070Biz() {
//    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr070Biz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr070ParamModel
     * @param tempDir テンポラリディレクトリパス
     * @param usrSid ユーザSID
     * @throws Exception 実行例外
     */
    public void getInitData(Connection con, Adr070ParamModel paramMdl,
            String tempDir, int usrSid)
    throws Exception {

        AddressBiz addressBiz = new AddressBiz(reqMdl_);

        //取込みファイルコンボを設定
        paramMdl.setAdr070fileCombo(addressBiz.getFileCombo(tempDir));

        if (paramMdl.getAdr070cmdMode() != 1) {
            //会社コンボを設定
            paramMdl.setAdr070CompanyCombo(__getCompanyCombo(con));

            //支店・営業所コンボを設定
            paramMdl.setAdr070CompanyBaseCombo(__getCompanyBaseCombo(con,
                                                 paramMdl.getAdr070selectCompany()));
        }

        //グループコンボを設定
        paramMdl.setGroupCmbList(addressBiz.getGroupLabelList(con));

        //ユーザ一覧を取得する
        CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
        CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
        AddressDao addressDao = new AddressDao(con);
        List<LabelValueBean> allUserCombo = addressDao.getAllUserList(sortMdl);

        //グループ一覧を取得
        ArrayList<LabelValueBean> allGroupCombo = new ArrayList<LabelValueBean>();
//        GroupDao dao = new GroupDao(con);
//        ArrayList<GroupModel> gpList = dao.getGroupTree();
        GroupBiz groupBiz = new GroupBiz();
        ArrayList<GroupModel> gpList = groupBiz.getGroupList(con);
        for (GroupModel gpMdl : gpList) {
            allGroupCombo.add(
                    new LabelValueBean(gpMdl.getGroupName(),
                                    String.valueOf(gpMdl.getGroupSid())));
        }

        if (paramMdl.getAdr070tantoGroup() == -2) {
            GroupBiz grpBiz = new GroupBiz();
            int defGrp = grpBiz.getDefaultGroupSid(usrSid, con);
            paramMdl.setAdr070tantoGroup(defGrp);

            String[] myself = new String[1];
            myself[0] = String.valueOf(usrSid);
            paramMdl.setAdr070tantoList(myself);
        }

        //グループコンボを設定
        paramMdl.setGroupCmbList(addressBiz.getGroupLabelList(con));

        //担当者コンボの設定を行う
        List<List<LabelValueBean>> tantoComboList = addressBiz.getUserCombo(con,
                                                                allUserCombo,
                                                                paramMdl.getAdr070tantoGroup(),
                                                                paramMdl.getAdr070tantoList());
        paramMdl.setSelectTantoCombo(tantoComboList.get(0));
        paramMdl.setNoSelectTantoCombo(tantoComboList.get(1));

        //閲覧グループコンボを設定する
        List<List<LabelValueBean>> viewPermitGroupComboList
                = addressBiz.getGroupCombo(con, allGroupCombo,
                                paramMdl.getAdr070permitViewGroup());
        paramMdl.setSelectPermitViewGroup(viewPermitGroupComboList.get(0));
        paramMdl.setNoSelectPermitViewGroup(viewPermitGroupComboList.get(1));

        //編集グループコンボを設定する
        List<List<LabelValueBean>> editPermitGroupComboList
                = addressBiz.getGroupCombo(con, allGroupCombo,
                                paramMdl.getAdr070permitEditGroup());
        paramMdl.setSelectPermitEditGroup(editPermitGroupComboList.get(0));
        paramMdl.setNoSelectPermitEditGroup(editPermitGroupComboList.get(1));

        //閲覧ユーザコンボを設定する
        List<List<LabelValueBean>> viewPermitUserComboList
                = addressBiz.getUserCombo(con, allUserCombo,
                                paramMdl.getAdr070permitViewUserGroup(),
                                paramMdl.getAdr070permitViewUser());
        paramMdl.setSelectPermitViewUser(viewPermitUserComboList.get(0));
        paramMdl.setNoSelectPermitViewUser(viewPermitUserComboList.get(1));

        //編集ユーザコンボを設定する
        List<List<LabelValueBean>> editPermitUserComboList
                = addressBiz.getUserCombo(con, allUserCombo,
                                paramMdl.getAdr070permitEditUserGroup(),
                                paramMdl.getAdr070permitEditUser());
        paramMdl.setSelectPermitEditUser(editPermitUserComboList.get(0));
        paramMdl.setNoSelectPermitEditUser(editPermitUserComboList.get(1));

        //閲覧権限、編集権限
        AdrAconfDao aconfDao = new AdrAconfDao(con);
        AdrAconfModel aconfMdl = aconfDao.selectAconf();
        //閲覧・編集権限の初期値を設定(初期表示時)
        if (aconfMdl != null) {
            //管理者が設定
            if (aconfMdl.getAacVrmEdit() == GSConstAddress.MEM_DSP_ADM) {
                paramMdl.setAdr070permitView(aconfMdl.getAacPvwKbn());
                paramMdl.setAdr070permitEdit(aconfMdl.getAacPetKbn());
            } else {
                //個人が設定
                AdrUconfDao uconfDao = new AdrUconfDao(con);
                AdrUconfModel uconfMdl = uconfDao.select(usrSid);
                if (uconfMdl != null) {
                    paramMdl.setAdr070permitView(uconfMdl.getAucPermitView());
                    paramMdl.setAdr070permitEdit(uconfMdl.getAucPermitEdit());
                } else {
                    paramMdl.setAdr070permitView(aconfMdl.getAacPvwKbn());
                    paramMdl.setAdr070permitEdit(aconfMdl.getAacPetKbn());
                }
            }
        } else {
            paramMdl.setAdr070permitView(GSConst.ADR_VIEWPERMIT_OWN);
            paramMdl.setAdr070permitEdit(GSConstAddress.EDITPERMIT_OWN);
        }

    }

    /**
     * <br>[機  能] 都道府県名から都道府県SIDを取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param dao CmnTdfkDao
     * @param tdfkName 都道府県名
     * @throws SQLException SQL実行時例外
     * @return 都道府県SID
     */
    public int getTdfkSid(CmnTdfkDao dao, String tdfkName)
            throws SQLException {
        int ret = 0;
        List<CmnTdfkModel> tdfkList = dao.select();
        for (CmnTdfkModel mdl : tdfkList) {
            if (mdl.getTdfName().equals(tdfkName)) {
                ret = mdl.getTdfSid();
            }
        }
        return ret;
    }

    /**
     * <br>[機  能] 会社コンボを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return 会社コンボ
     * @throws SQLException SQL実行時例外
     */
    private List<LabelValueBean> __getCompanyCombo(Connection con)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl_);
        List<LabelValueBean> companyCombo = new ArrayList<LabelValueBean>();
        companyCombo.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));

        AdrCompanyDao companyDao = new AdrCompanyDao(con);
        List<AdrCompanyModel> companyList = companyDao.select();
        for (AdrCompanyModel companyData : companyList) {
            companyCombo.add(new LabelValueBean(companyData.getAcoName(),
                                                String.valueOf(companyData.getAcoSid())));
        }

        return companyCombo;
    }

    /**
     * <br>[機  能] 支店・営業所コンボを生成する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param acoSid 会社SID
     * @return 支店・営業所コンボ
     * @throws SQLException SQL実行時例外
     */
    private List<LabelValueBean> __getCompanyBaseCombo(Connection con,
                                                        int acoSid)
    throws SQLException {
        List<LabelValueBean> companyBaseCombo = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        companyBaseCombo.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));

        if (acoSid <= 0) {
            return companyBaseCombo;
        }

        AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con);
        List<AdrCompanyBaseModel> companyBaseList = companyBaseDao.getCompanyBaseList(acoSid);
        for (AdrCompanyBaseModel companyBaseData : companyBaseList) {
            companyBaseCombo.add(new LabelValueBean(companyBaseData.getAbaName(),
                                                String.valueOf(companyBaseData.getAbaSid())));
        }

        return companyBaseCombo;
    }
}
