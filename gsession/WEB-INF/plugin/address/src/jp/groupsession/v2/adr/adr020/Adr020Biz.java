package jp.groupsession.v2.adr.adr020;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr010.Adr010Biz;
import jp.groupsession.v2.adr.adr010.Adr010Const;
import jp.groupsession.v2.adr.biz.AddressBiz;
import jp.groupsession.v2.adr.dao.AddressDao;
import jp.groupsession.v2.adr.dao.AdrAconfDao;
import jp.groupsession.v2.adr.dao.AdrAddressDao;
import jp.groupsession.v2.adr.dao.AdrBelongLabelDao;
import jp.groupsession.v2.adr.dao.AdrCompanyBaseDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.dao.AdrContactDao;
import jp.groupsession.v2.adr.dao.AdrLabelDao;
import jp.groupsession.v2.adr.dao.AdrPermitEditDao;
import jp.groupsession.v2.adr.dao.AdrPermitViewDao;
import jp.groupsession.v2.adr.dao.AdrPersonchargeDao;
import jp.groupsession.v2.adr.dao.AdrPositionDao;
import jp.groupsession.v2.adr.dao.AdrUconfDao;
import jp.groupsession.v2.adr.model.AdrAconfModel;
import jp.groupsession.v2.adr.model.AdrAddressModel;
import jp.groupsession.v2.adr.model.AdrCompanyBaseModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.adr.model.AdrLabelModel;
import jp.groupsession.v2.adr.model.AdrPermitEditModel;
import jp.groupsession.v2.adr.model.AdrPermitViewModel;
import jp.groupsession.v2.adr.model.AdrPersonchargeModel;
import jp.groupsession.v2.adr.model.AdrPositionModel;
import jp.groupsession.v2.adr.model.AdrUconfModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupModel;
import jp.groupsession.v2.cmn.dao.WmlDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.WmlMailDataModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アドレス帳登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr020Biz extends Adr010Biz {
    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr020Biz(RequestModel reqMdl) {
        super(reqMdl);
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr020ParamModel
     * @param con コネクション
     * @param userMdl セッションユーザ情報
     * @throws Exception 実行例外
     */
    public void setInitData(Adr020ParamModel paramMdl,
                             Connection con,
                             BaseUserModel userMdl)
    throws Exception {

        AddressBiz addressBiz = new AddressBiz(reqMdl_);
        UserBiz userBiz = new UserBiz();

        GsMessage gsMsg = new GsMessage(reqMdl_);

        //役職コンボを設定
        paramMdl.setPositionCmbList(getAddressPositionLabelList(con));

        //グループコンボを設定
        paramMdl.setGroupCmbList(addressBiz.getGroupLabelList(con));

        //ユーザコンボを設定
        if (paramMdl.getAdr010cmdMode() == Adr010Const.CMDMODE_CONTACT) {
            paramMdl.setUserCmbList(userBiz.getNormalUserLabelList(con,
                            Integer.parseInt(paramMdl.getAdr010tantoGroupContact()),
                                                null, true, gsMsg));
        } else {
            paramMdl.setUserCmbList(
                    userBiz.getNormalUserLabelList(con,
                                                   Integer.parseInt(paramMdl.getAdr010tantoGroup()),
                                                   null, true, gsMsg));
        }

        //都道府県コンボを設定
        CommonBiz cmnBiz = new CommonBiz();
        paramMdl.setTdfkCmbList(cmnBiz.getTdfkLabelList(con, gsMsg));

        //管理者設定ボタン表示フラグを設定
        boolean adminUser = cmnBiz.isPluginAdmin(con, userMdl, GSConstAddress.PLUGIN_ID_ADDRESS);

        AdrAconfDao aconfDao = new AdrAconfDao(con);
        AdrAconfModel aconfMdl = aconfDao.selectAconf();
        //会社追加ボタン表示フラグを設定
        if (adminUser || (aconfMdl == null || aconfMdl.getAacAcoEdit() == 0)) {
            paramMdl.setAdr020addCompanyBtnFlg(1);
        }
        //ラベル追加ボタン表示フラグを設定
        if (adminUser || (aconfMdl == null || aconfMdl.getAacAlbEdit() == 0)) {
            paramMdl.setAdr020addLabelBtnFlg(1);
        }

        //閲覧・編集権限の初期値を設定(初期表示時)
        if (paramMdl.getAdr020init() == 0) {
            if (aconfMdl != null) {
                //管理者が設定
                if (aconfMdl.getAacVrmEdit() == GSConstAddress.MEM_DSP_ADM) {
                    paramMdl.setAdr020permitView(aconfMdl.getAacPvwKbn());
                    paramMdl.setAdr020permitEdit(aconfMdl.getAacPetKbn());
                } else {
                    //個人が設定
                    AdrUconfDao uconfDao = new AdrUconfDao(con);
                    AdrUconfModel uconfMdl = uconfDao.select(userMdl.getUsrsid());
                    if (uconfMdl != null) {
                        paramMdl.setAdr020permitView(uconfMdl.getAucPermitView());
                        paramMdl.setAdr020permitEdit(uconfMdl.getAucPermitEdit());
                    } else {
                        paramMdl.setAdr020permitView(aconfMdl.getAacPvwKbn());
                        paramMdl.setAdr020permitEdit(aconfMdl.getAacPetKbn());
                    }
                }
            } else {
                paramMdl.setAdr020permitView(GSConst.ADR_VIEWPERMIT_OWN);
                paramMdl.setAdr020permitEdit(GSConstAddress.EDITPERMIT_OWN);
            }
        }

        //初期表示
        if (paramMdl.getAdr020init() == 0) {

            //モード = 新規登録 の場合、担当者にセッションユーザを設定する
            if (paramMdl.getAdr020ProcMode() == GSConstAddress.PROCMODE_ADD) {
                paramMdl.setAdr020tantoList(new String[] {String.valueOf(userMdl.getUsrsid())});
            }

            //モード = 変更 の場合、DBからアドレス帳情報を読み込む
            if (paramMdl.getAdr020ProcMode() == GSConstAddress.PROCMODE_EDIT) {
                _setAddressData(con, paramMdl);
            }

            //WEBメールからの呼び出しの場合、氏名とメールアドレスを追加する
            if (paramMdl.getAdr020webmail() == 1) {
                long mailNum = paramMdl.getAdr020webmailId();
                WmlDao wmlDao = new WmlDao(con);
                WmlMailDataModel mailData = wmlDao.getMailData(mailNum, reqMdl_.getDomain());
                String webmailAddress = NullDefault.getString(mailData.getFromAddress(), "");

                if (webmailAddress.indexOf("<") > 0) {
                    String userName
                        = webmailAddress.substring(0, webmailAddress.indexOf("<")).trim();
                    webmailAddress
                        =  webmailAddress.substring(webmailAddress.indexOf("<") + 1).trim();
                    if (userName.startsWith("\"")) {
                        userName = userName.substring(1);
                    }
                    if (userName.endsWith("\"")) {
                        userName = userName.substring(0, userName.length() - 1);
                    }
                    userName = userName.replaceFirst("　", " ");
                    int userIdx = userName.indexOf(" ");
                    if (userIdx > 0) {
                        paramMdl.setAdr020unameSei(userName.substring(0, userIdx).trim());
                        paramMdl.setAdr020unameMei(userName.substring(userIdx).trim());
                    } else {
                        paramMdl.setAdr020unameSei(userName);
                    }

                    if (webmailAddress.indexOf(">") > 0) {
                        webmailAddress
                            = webmailAddress.substring(0, webmailAddress.indexOf(">"));
                    }
                }
                paramMdl.setAdr020mail1(webmailAddress);
            }

            paramMdl.setAdr020init(1);
        }
        //モード = 新規 かつ 複写ボタンをクリック時、DBからアドレス帳情報を読み込む
        if (paramMdl.getAdr020ProcMode() == GSConstAddress.PROCMODE_ADD
        && paramMdl.getAdrCopyFlg() == 1) {
            _setAddressData(con, paramMdl);
            paramMdl.setAdrCopyFlg(0);
        }

        //会社名を設定
        _setCompanyData(con, paramMdl);

        //役職を追加した場合の役職の設定
        if (paramMdl.getAdrPosition() > 0) {
            paramMdl.setAdr020position(paramMdl.getAdrPosition());
            paramMdl.setAdrPosition(0);
        }

        //ユーザ一覧を取得する
        CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
        CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();
        AddressDao addressDao = new AddressDao(con);
        List<LabelValueBean> allUserCombo = addressDao.getAllUserList(sortMdl);

        //担当者コンボの設定を行う
        if (paramMdl.getAdr020tantoGroup() == -2
                || paramMdl.getAdr020permitViewUserGroup() == -2
                || paramMdl.getAdr020permitEditUserGroup() == -2) {

            CmnBelongmDao belongmDao = new CmnBelongmDao(con);

            int userSid = userMdl.getUsrsid();
            int defGrpSid = belongmDao.selectUserBelongGroupDef(userSid);

            paramMdl.setAdr020tantoGroup(defGrpSid);
            paramMdl.setAdr020permitViewUserGroup(defGrpSid);
            paramMdl.setAdr020permitEditUserGroup(defGrpSid);
        }

        List<List<LabelValueBean>> tantoComboList = addressBiz.getUserCombo(con,
                                                                allUserCombo,
                                                                paramMdl.getAdr020tantoGroup(),
                                                                paramMdl.getAdr020tantoList());
        paramMdl.setSelectTantoCombo(tantoComboList.get(0));
        paramMdl.setNoSelectTantoCombo(tantoComboList.get(1));
        ArrayList<String> selSidList = new ArrayList<String>();
        for (LabelValueBean bean : tantoComboList.get(0)) {
            selSidList.add(bean.getValue());
        }
        paramMdl.setAdr020tantoList(selSidList.toArray(new String[0]));

        //グループ一覧を取得
        ArrayList<LabelValueBean> allGroupCombo = new ArrayList<LabelValueBean>();
        GroupBiz groupBiz = new GroupBiz();
        ArrayList<GroupModel> gpList = groupBiz.getGroupList(con);

        for (GroupModel gpMdl : gpList) {
            allGroupCombo.add(
                    new LabelValueBean(gpMdl.getGroupName(),
                                    String.valueOf(gpMdl.getGroupSid())));
        }
        //閲覧グループコンボを設定する
        List<List<LabelValueBean>> viewPermitGroupComboList
        = addressBiz.getGroupCombo(con, allGroupCombo,
                        paramMdl.getAdr020permitViewGroup());
        paramMdl.setSelectPermitViewGroup(viewPermitGroupComboList.get(0));
        paramMdl.setNoSelectPermitViewGroup(viewPermitGroupComboList.get(1));
        selSidList = new ArrayList<String>();
        for (LabelValueBean bean : viewPermitGroupComboList.get(0)) {
            selSidList.add(bean.getValue());
        }
        paramMdl.setAdr020permitViewGroup(selSidList.toArray(new String[0]));
        //編集グループコンボを設定する
        List<List<LabelValueBean>> editPermitGroupComboList
        = addressBiz.getGroupCombo(con, allGroupCombo,
                        paramMdl.getAdr020permitEditGroup());
        paramMdl.setSelectPermitEditGroup(editPermitGroupComboList.get(0));
        paramMdl.setNoSelectPermitEditGroup(editPermitGroupComboList.get(1));
        selSidList = new ArrayList<String>();
        for (LabelValueBean bean : editPermitGroupComboList.get(0)) {
            selSidList.add(bean.getValue());
        }
        paramMdl.setAdr020permitEditGroup(selSidList.toArray(new String[0]));

        //閲覧ユーザコンボを設定する
        List<List<LabelValueBean>> viewPermitUserComboList
                = addressBiz.getUserCombo(con, allUserCombo,
                                paramMdl.getAdr020permitViewUserGroup(),
                                paramMdl.getAdr020permitViewUser());
        paramMdl.setSelectPermitViewUser(viewPermitUserComboList.get(0));
        paramMdl.setNoSelectPermitViewUser(viewPermitUserComboList.get(1));
        selSidList = new ArrayList<String>();
        for (LabelValueBean bean : viewPermitUserComboList.get(0)) {
            selSidList.add(bean.getValue());
        }
        paramMdl.setAdr020permitViewUser(selSidList.toArray(new String[0]));

        //編集ユーザコンボを設定する
        List<List<LabelValueBean>> editPermitUserComboList
                = addressBiz.getUserCombo(con, allUserCombo,
                                paramMdl.getAdr020permitEditUserGroup(),
                                paramMdl.getAdr020permitEditUser());
        paramMdl.setSelectPermitEditUser(editPermitUserComboList.get(0));
        paramMdl.setNoSelectPermitEditUser(editPermitUserComboList.get(1));
        selSidList = new ArrayList<String>();
        for (LabelValueBean bean : editPermitUserComboList.get(0)) {
            selSidList.add(bean.getValue());
        }
        paramMdl.setAdr020permitEditUser(selSidList.toArray(new String[0]));

        //ラベル情報一覧を設定
        this._setLabelList(con, paramMdl);
    }

    /**
     * <br>[機  能] アドレス帳の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr020ParamModel
     * @param sessionUserSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void deleteAddress(Connection con, Adr020ParamModel paramMdl, int sessionUserSid)
    throws Exception {
        int adrSid = paramMdl.getAdr010EditAdrSid();

        //アドレス帳情報の削除
        AdrAddressDao addressDao = new AdrAddressDao(con);
        addressDao.delete(adrSid);

        //担当者情報を削除する
        AdrPersonchargeDao adrPersonDao = new AdrPersonchargeDao(con);
        adrPersonDao.deleteToAddress(adrSid);

        //ラベル付与情報を削除する
        AdrBelongLabelDao belongLabelDao = new AdrBelongLabelDao(con);
        belongLabelDao.deleteToAddress(adrSid);

        //バイナリー情報を論理削除する
        AddressDao adrDao = new AddressDao(con);
        adrDao.deleteBinData(adrSid);

        //コンタクト履歴添付情報を削除する
        adrDao.deleteContactBinToAddress(adrSid);

        //コンタクト履歴情報を削除する
        AdrContactDao contactDao = new AdrContactDao(con);
        contactDao.deleteToAddress(adrSid);
    }

    /**
     * <br>[機  能] 指定された会社情報をDBから読み込み、パラメータに設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr020ParamModel
     * @throws SQLException SQL実行時例外
     */
    protected void _setAddressData(Connection con, Adr020ParamModel paramMdl) throws SQLException {
        AdrAddressDao addressDao = new AdrAddressDao(con);
        //アドレス帳状報の設定
        int adrSid = paramMdl.getAdr010EditAdrSid();
        AdrAddressModel addressMdl = addressDao.select(adrSid);
        if (addressMdl == null) {
            return;
        }
        paramMdl.setAdr020unameSei(addressMdl.getAdrSei());
        paramMdl.setAdr020unameMei(addressMdl.getAdrMei());
        paramMdl.setAdr020unameSeiKn(addressMdl.getAdrSeiKn());
        paramMdl.setAdr020unameMeiKn(addressMdl.getAdrMeiKn());
        paramMdl.setAdr020selectCompany(String.valueOf(addressMdl.getAcoSid()));
        paramMdl.setAdr020selectCompanyBase(String.valueOf(addressMdl.getAbaSid()));
        paramMdl.setAdr020syozoku(addressMdl.getAdrSyozoku());
        paramMdl.setAdr020position(addressMdl.getApsSid());
        paramMdl.setAdr020mail1(addressMdl.getAdrMail1());
        paramMdl.setAdr020mail1Comment(addressMdl.getAdrMailCmt1());
        paramMdl.setAdr020mail2(addressMdl.getAdrMail2());
        paramMdl.setAdr020mail2Comment(addressMdl.getAdrMailCmt2());
        paramMdl.setAdr020mail3(addressMdl.getAdrMail3());
        paramMdl.setAdr020mail3Comment(addressMdl.getAdrMailCmt3());

        paramMdl.setAdr020postno1(addressMdl.getAdrPostno1());
        paramMdl.setAdr020postno2(addressMdl.getAdrPostno2());
        paramMdl.setAdr020tdfk(addressMdl.getTdfSid());
        paramMdl.setAdr020address1(addressMdl.getAdrAddr1());
        paramMdl.setAdr020address2(addressMdl.getAdrAddr2());
        paramMdl.setAdr020tel1(addressMdl.getAdrTel1());
        paramMdl.setAdr020tel1Nai(addressMdl.getAdrTelNai1());
        paramMdl.setAdr020tel1Comment(addressMdl.getAdrTelCmt1());
        paramMdl.setAdr020tel2(addressMdl.getAdrTel2());
        paramMdl.setAdr020tel2Nai(addressMdl.getAdrTelNai2());
        paramMdl.setAdr020tel2Comment(addressMdl.getAdrTelCmt2());
        paramMdl.setAdr020tel3(addressMdl.getAdrTel3());
        paramMdl.setAdr020tel3Nai(addressMdl.getAdrTelNai3());
        paramMdl.setAdr020tel3Comment(addressMdl.getAdrTelCmt3());
        paramMdl.setAdr020fax1(addressMdl.getAdrFax1());
        paramMdl.setAdr020fax1Comment(addressMdl.getAdrFaxCmt1());
        paramMdl.setAdr020fax2(addressMdl.getAdrFax2());
        paramMdl.setAdr020fax2Comment(addressMdl.getAdrFaxCmt2());
        paramMdl.setAdr020fax3(addressMdl.getAdrFax3());
        paramMdl.setAdr020fax3Comment(addressMdl.getAdrFaxCmt3());
        paramMdl.setAdr020biko(addressMdl.getAdrBiko());
        paramMdl.setAdr020permitView(addressMdl.getAdrPermitView());
        paramMdl.setAdr020permitEdit(addressMdl.getAdrPermitEdit());

        //担当者の設定
        AdrPersonchargeDao tantoDao = new AdrPersonchargeDao(con);
        List<AdrPersonchargeModel> tantoDataList
                = tantoDao.getTantoListForAddress(adrSid);
        List<String> tantoList = new ArrayList<String>();
        for (AdrPersonchargeModel tantoData : tantoDataList) {
            tantoList.add(String.valueOf(tantoData.getUsrSid()));
        }
        paramMdl.setAdr020tantoList(tantoList.toArray(new String[tantoList.size()]));

        //ラベル情報の設定
        AdrBelongLabelDao belongLabelDao = new AdrBelongLabelDao(con);
        paramMdl.setAdr020label(belongLabelDao.getLabelSidList(adrSid));

        //閲覧権限グループの設定
        AdrPermitViewDao permitViewDao = new AdrPermitViewDao(con);
        int permitView = paramMdl.getAdr020permitView();
        if (permitView == GSConst.ADR_VIEWPERMIT_GROUP
        || permitView == GSConst.ADR_VIEWPERMIT_USER) {
            List<AdrPermitViewModel> permitDataList
                = permitViewDao.getPermitListForAddress(adrSid);
            List<String> permitList = new ArrayList<String>();

            for (AdrPermitViewModel permitData : permitDataList) {
                if (permitView == GSConst.ADR_VIEWPERMIT_GROUP) {
                    permitList.add(String.valueOf(permitData.getGrpSid()));
                } else if (permitView == GSConst.ADR_VIEWPERMIT_USER) {
                    permitList.add(String.valueOf(permitData.getUsrSid()));
                }
            }

            String[] permitArray = permitList.toArray(new String[permitList.size()]);
            if (permitView == GSConst.ADR_VIEWPERMIT_GROUP) {
                paramMdl.setAdr020permitViewGroup(permitArray);
            } else if (permitView == GSConst.ADR_VIEWPERMIT_USER) {
                paramMdl.setAdr020permitViewUser(permitArray);
            }
        }

        //編集権限グループの設定
        AdrPermitEditDao permitEditDao = new AdrPermitEditDao(con);
        int permitEdit = paramMdl.getAdr020permitEdit();
        if (permitEdit == GSConstAddress.EDITPERMIT_GROUP
        || permitEdit == GSConstAddress.EDITPERMIT_USER) {
            List<AdrPermitEditModel> permitDataList
                = permitEditDao.getPermitListForAddress(adrSid);
            List<String> permitList = new ArrayList<String>();

            for (AdrPermitEditModel permitData : permitDataList) {
                if (permitEdit == GSConstAddress.EDITPERMIT_GROUP) {
                    permitList.add(String.valueOf(permitData.getGrpSid()));
                } else if (permitEdit == GSConstAddress.EDITPERMIT_USER) {
                    permitList.add(String.valueOf(permitData.getUsrSid()));
                }
            }

            String[] permitArray = permitList.toArray(new String[permitList.size()]);
            if (permitEdit == GSConstAddress.EDITPERMIT_GROUP) {
                paramMdl.setAdr020permitEditGroup(permitArray);
            } else if (permitEdit == GSConstAddress.EDITPERMIT_USER) {
                paramMdl.setAdr020permitEditUser(permitArray);
            }
        }
    }

    /**
     * <br>[機  能] 選択された会社情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr020ParamModel
     * @throws SQLException SQL実行時例外
     */
    protected void _setCompanyData(Connection con, Adr020ParamModel paramMdl) throws SQLException {
        if (!StringUtil.isNullZeroString(paramMdl.getAdr020selectCompany())) {
            AdrCompanyDao companyDao = new AdrCompanyDao(con);
            AdrCompanyModel companyMdl
                = companyDao.select(Integer.parseInt(paramMdl.getAdr020selectCompany()));
            if (companyMdl != null) {
                paramMdl.setAdr020companyCode(companyMdl.getAcoCode());
                paramMdl.setAdr020companyName(companyMdl.getAcoName());

                if (!StringUtil.isNullZeroString(paramMdl.getAdr020selectCompanyBase())) {
                    AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con);
                    AdrCompanyBaseModel companyBaseMdl
                        = companyBaseDao.select(
                                Integer.parseInt(paramMdl.getAdr020selectCompanyBase()));
                    if (companyBaseMdl != null) {
                        String companyBaseName = companyBaseMdl.getAbaName();
                        String companyBaseType
                            = AddressBiz.getCompanyBaseTypeName(
                                    companyBaseMdl.getAbaType(), reqMdl_);
                        if (!StringUtil.isNullZeroString(companyBaseType)) {
                            companyBaseName = companyBaseType + " ： " + companyBaseName;
                        }
                        paramMdl.setAdr020companyBaseName(companyBaseName);
                    } else {
                        paramMdl.setAdr020selectCompanyBase(null);
                    }
                }
            } else {
                paramMdl.setAdr020selectCompany(null);
                paramMdl.setAdr020selectCompanyBase(null);
            }
        }
    }

    /**
     * <br>[機  能] ラベル情報一覧を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr020ParamModel
     * @throws SQLException SQL実行時例外
     */
    protected void _setLabelList(Connection con, Adr020ParamModel paramMdl) throws SQLException {
        AdrLabelDao labelDao = new AdrLabelDao(con);
        List<AdrLabelModel> selectLabelList = new ArrayList<AdrLabelModel>();
        if (paramMdl.getAdr020label() != null) {
            String[] selectLabel = paramMdl.getAdr020label();
            Arrays.sort(selectLabel);
            List<AdrLabelModel> allLabelList = labelDao.select();
            for (AdrLabelModel labelData : allLabelList) {
                if (Arrays.binarySearch(selectLabel, String.valueOf(labelData.getAlbSid())) >= 0) {
                    selectLabelList.add(labelData);
                }
            }
        }
        paramMdl.setSelectLabelList(selectLabelList);
        ArrayList<String> selSidList = new ArrayList<String>();
        for (AdrLabelModel bean : selectLabelList) {
            selSidList.add(String.valueOf(bean.getAlbSid()));
        }
        paramMdl.setAdr020label(selSidList.toArray(new String[0]));

    }

    /**
     * <br>[機  能] 役職コンボに設定する情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<LabelValueBean> getAddressPositionLabelList(Connection con)
        throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl_);
        ArrayList<LabelValueBean> positionLabelList = new ArrayList<LabelValueBean>();
        //「選択してください」をコンボにセット
        positionLabelList.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));

        AdrPositionDao positionDao = new AdrPositionDao(con);
        List<AdrPositionModel> positionList = positionDao.selectPositionList();
        for (AdrPositionModel positionData : positionList) {
            LabelValueBean label = new LabelValueBean(positionData.getApsName(),
                                                    String.valueOf(positionData.getApsSid()));
            positionLabelList.add(label);
        }

        return positionLabelList;
    }
}
