package jp.groupsession.v2.adr.adr110;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrAddressDao;
import jp.groupsession.v2.adr.dao.AdrBelongIndustryDao;
import jp.groupsession.v2.adr.dao.AdrCompanyBaseDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.dao.AdrTypeindustryDao;
import jp.groupsession.v2.adr.model.AdrCompanyBaseModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.adr.model.AdrTypeindustryModel;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.base.CmnTdfkDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnTdfkModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] アドレス帳 会社登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr110Biz {

    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Adr110Biz() {
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr110Biz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr110ParamModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Adr110ParamModel paramMdl)
    throws SQLException {

        //処理モード = 編集 かつ 初期表示の場合は会社情報を設定する
        if (paramMdl.getAdr110ProcMode() == GSConstAddress.PROCMODE_EDIT
        && paramMdl.getAdr110initFlg() == 0) {
            _readCompanyData(con, paramMdl);
        }

        //都道府県コンボを設定
        CommonBiz cmnBiz = new CommonBiz();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        paramMdl.setTdfkCmbList(cmnBiz.getTdfkLabelList(con, gsMsg));

        //拠点種別コンボを設定
        ArrayList<LabelValueBean> abaTypeList = new ArrayList<LabelValueBean>();
        abaTypeList.add(new LabelValueBean(gsMsg.getMessage("cmn.select.plz"), "-1"));
        abaTypeList.add(new LabelValueBean(gsMsg.getMessage("address.122"),
                                        String.valueOf(GSConstAddress.ABATYPE_HEADOFFICE)));
        abaTypeList.add(new LabelValueBean(gsMsg.getMessage("address.123"),
                                        String.valueOf(GSConstAddress.ABATYPE_BRANCH)));
        abaTypeList.add(new LabelValueBean(gsMsg.getMessage("address.124"),
                                        String.valueOf(GSConstAddress.ABATYPE_BUSINESSOFFICE)));
        paramMdl.setAbaTypeList(abaTypeList);

        //業種一覧を設定する
        List<String> selectAtiSid = new ArrayList<String>();
        if (paramMdl.getAdr110atiList() != null) {
            selectAtiSid = Arrays.asList(paramMdl.getAdr110atiList());
        }
        List<LabelValueBean> selectAtiList = new ArrayList<LabelValueBean>();
        List<LabelValueBean> noSelectAtiList = new ArrayList<LabelValueBean>();
        AdrTypeindustryDao industryDao = new AdrTypeindustryDao(con);
        List<AdrTypeindustryModel> industryList = industryDao.select();
        for (AdrTypeindustryModel industryModel : industryList) {
            String atiSid = String.valueOf(industryModel.getAtiSid());
            LabelValueBean label = new LabelValueBean(industryModel.getAtiName(), atiSid);

            if (selectAtiSid.contains(atiSid)) {
                selectAtiList.add(label);
            } else {
                noSelectAtiList.add(label);
            }
        }
        selectAtiSid = new ArrayList<String>();
        for (LabelValueBean bean : selectAtiList) {
            selectAtiSid.add(bean.getValue());
        }
        paramMdl.setAdr110atiList(selectAtiSid.toArray(new String[0]));
        paramMdl.setSelectAtiCombo(selectAtiList);
        paramMdl.setNoSelectAtiCombo(noSelectAtiList);

        //会社拠点情報の都道府県名を設定する
        List<Adr110BaseForm> baseList = paramMdl.getAbaListToList();
        if (baseList != null) {
            CmnTdfkDao tdfkDao = new CmnTdfkDao(con);

            for (Adr110BaseForm baseForm : baseList) {
                int tdfkSid = baseForm.getAdr110abaTdfk();
                CmnTdfkModel tdfkMdl = tdfkDao.select(tdfkSid);
                if (tdfkMdl != null) {
                    baseForm.setAdr110abaTdfkName(tdfkMdl.getTdfName());
                }
            }
        }
    }

    /**
     * <br>[機  能] 会社情報の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr110ParamModel
     * @param sessionUserSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void deleteCompany(Connection con, Adr110ParamModel paramMdl, int sessionUserSid)
    throws Exception {
        int acoSid = paramMdl.getAdr110editAcoSid();

        //会社情報の削除
        AdrCompanyDao companyDao = new AdrCompanyDao(con);
        companyDao.delete(acoSid);

        //拠点情報の削除
        AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con);
        companyBaseDao.deleteCompany(acoSid);

        //所属業種情報の削除
        AdrBelongIndustryDao blgIndustryDao = new AdrBelongIndustryDao(con);
        blgIndustryDao.delete(acoSid);

        //アドレス情報の"会社"を未設定に更新する
        UDate now = new UDate();
        AdrAddressDao addressDao = new AdrAddressDao(con);
        addressDao.resetCompany(acoSid, sessionUserSid, now);

    }

    /**
     * <br>[機  能] 会社拠点情報の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr110ParamModel
     */
    public void deleteCompanyBase(Adr110ParamModel paramMdl) {

        List<Adr110BaseForm> abaList = paramMdl.getAbaListToList();
        List<Adr110BaseForm> newAbaList = new ArrayList<Adr110BaseForm>();
        int deleteIndex = paramMdl.getAdr110deleteCompanyBaseIndex();
        for (Adr110BaseForm baseForm : abaList) {
            if (baseForm.getAdr110abaIndex() != deleteIndex) {
                newAbaList.add(baseForm);
            }
        }

        paramMdl.setAbaListForm(newAbaList);
    }

    /**
     * <br>[機  能] 指定したインデックスの支店・営業所名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr110ParamModel
     * @param index インデックス
     * @return 支店・営業所名
     */
    public String getCoBaseName(Adr110ParamModel paramMdl, int index) {

        List<Adr110BaseForm> abaList = paramMdl.getAbaListToList();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        String coBaseName = "";
        for (Adr110BaseForm baseForm : abaList) {
            if (baseForm.getAdr110abaIndex() == index) {
                String typeName = gsMsg.getMessage(baseForm.getAdr110abaTypeNameDetail());
                if (!StringUtil.isNullZeroString(typeName)) {
                    coBaseName = typeName + " ";
                }
                coBaseName += baseForm.getAdr110abaName();
            }
        }

        return coBaseName;
    }

    /**
     * <br>[機  能] DBから会社情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr110ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void _readCompanyData(Connection con, Adr110ParamModel paramMdl) throws SQLException {

        //会社情報
        int acoSid = paramMdl.getAdr110editAcoSid();
        AdrCompanyDao companyDao = new AdrCompanyDao(con);
        AdrCompanyModel companyModel = companyDao.select(acoSid);
        if (companyModel != null) {
            paramMdl.setAdr110coCode(companyModel.getAcoCode());
            paramMdl.setAdr110coName(companyModel.getAcoName());
            paramMdl.setAdr110coNameKn(companyModel.getAcoNameKn());
            paramMdl.setAdr110url(companyModel.getAcoUrl());
            paramMdl.setAdr110biko(companyModel.getAcoBiko());
        }
        //所属業種
        AdrBelongIndustryDao blgIndustryDao = new AdrBelongIndustryDao(con);
        paramMdl.setAdr110atiList(blgIndustryDao.getAtiSidList(paramMdl.getAdr110editAcoSid()));

        paramMdl.setAdr110initFlg(1);

        //会社拠点情報
        int abaIndex = 0;
        List<Adr110BaseForm> abaList = new ArrayList<Adr110BaseForm>();
        AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con);
        List<AdrCompanyBaseModel> baseList = companyBaseDao.getCompanyBaseList(acoSid);
        for (AdrCompanyBaseModel baseModel : baseList) {
            Adr110BaseForm baseForm = new Adr110BaseForm();
            baseForm.setAdr110abaIndex(abaIndex);
            baseForm.setAdr110abaSidDetail(baseModel.getAbaSid());
            baseForm.setAdr110abaTypeDetail(baseModel.getAbaType());
            baseForm.setAdr110abaName(baseModel.getAbaName());
            baseForm.setAdr110abaTdfk(baseModel.getTdfSid());
            baseForm.setAdr110abaPostno1(baseModel.getAbaPostno1());
            baseForm.setAdr110abaPostno2(baseModel.getAbaPostno2());
            baseForm.setAdr110abaAddress1(baseModel.getAbaAddr1());
            baseForm.setAdr110abaAddress2(baseModel.getAbaAddr2());
            baseForm.setAdr110abaBiko(baseModel.getAbaBiko());

            //地図検索ワード
            String address = baseModel.getAbaAddr1() + baseModel.getAbaAddr2();
            baseForm.setAdr110abaWebSearchWord(StringUtil.toSingleCortationEscape(address));

            abaList.add(baseForm);
            abaIndex++;
        }
        paramMdl.setAbaListForm(abaList);
    }
    /**
     * 
     * <br>[機  能] 会社情報の表示可能判定
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータモデル
     * @throws SQLException SQL実行時例外
     * @return 0：表示可能 1:データがないため表示不可
     */
    public int canViewCompanyData(Connection con, Adr110ParamModel paramMdl) throws SQLException {
        int acoSid = paramMdl.getAdr110editAcoSid();
        AdrCompanyDao companyDao = new AdrCompanyDao(con);
        AdrCompanyModel companyModel = companyDao.select(acoSid);
        if (companyModel == null) {
            return 1;
        }
        return 0;
    }
}
