package jp.groupsession.v2.adr.adr110kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.StringUtilKana;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr110.Adr110BaseForm;
import jp.groupsession.v2.adr.adr110.Adr110Biz;
import jp.groupsession.v2.adr.adr110kn.dao.Adr110knDao;
import jp.groupsession.v2.adr.adr110kn.model.Adr110knModel;
import jp.groupsession.v2.adr.dao.AdrBelongIndustryDao;
import jp.groupsession.v2.adr.dao.AdrCompanyBaseDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.dao.AdrTypeindustryDao;
import jp.groupsession.v2.adr.model.AdrBelongIndustryModel;
import jp.groupsession.v2.adr.model.AdrCompanyBaseModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.adr.model.AdrTypeindustryModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnTdfkDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnTdfkModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アドレス帳 会社登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr110knBiz extends Adr110Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr110knBiz.class);
    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr110knBiz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr110knParamModel
     * @param sessionUsrSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Adr110knParamModel paramMdl, int sessionUsrSid)
    throws SQLException {
        //遷移元画面がアドレス帳一覧画面の場合、会社情報をDBから読み込む
        if (paramMdl.getAdr100backFlg() == 2) {
            _readCompanyData(con, paramMdl);
        }

        //所属業種名称を取得する
        String[] atiSidList = paramMdl.getAdr110atiList();
        if (atiSidList != null) {
            ArrayList<String> atiNameList = new ArrayList<String>();
            AdrTypeindustryDao industryDao = new AdrTypeindustryDao(con);
            ArrayList<String> sidList = new ArrayList<String>(Arrays.asList(atiSidList));
            
            for (int index = 0; index < atiSidList.length; index++) {
                AdrTypeindustryModel industryMdl
                    = industryDao.select(Integer.parseInt(atiSidList[index]));
                if (industryMdl != null) {
                    atiNameList.add(industryMdl.getAtiName());
                } else {
                    sidList.remove(atiSidList[index]);
                }
            }
            String[] atiName = new String[atiNameList.size()];
            atiName = atiNameList.toArray(atiName);
            paramMdl.setAdr110knViewAtiList(atiName);
            paramMdl.setAdr110atiList(sidList.toArray(new String[0]));
        }

        //備考の設定を行う
        paramMdl.setAdr110knViewBiko(
                StringUtilHtml.transToHTmlPlusAmparsant(
                        NullDefault.getString(paramMdl.getAdr110biko(), "")));

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

        //アドレス情報を取得する。
        Adr110knDao adr110knDao = new Adr110knDao(con);
        List<Adr110knModel> adrInfList
                =  adr110knDao.getUserListBelongCompany(
                        paramMdl.getAdr110editAcoSid(), sessionUsrSid);

        paramMdl.setAdr110knAdrInfList(adrInfList);
        paramMdl.setAdr110knAdrCount(String.valueOf(adrInfList.size()));

    }

    /**
     * <br>[機  能] アドレス帳の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr110knParamModel
     * @param mtCon 採番コントローラ
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     */
    public void entryCompanyData(
            Connection con, Adr110knParamModel paramMdl, MlCountMtController mtCon,
                                int sessionUserSid)
    throws IOException, IOToolsException, SQLException {

        log__.debug("START");

        UDate now = new UDate();

        //会社情報の登録を行う
        AdrCompanyModel companyMdl = new AdrCompanyModel();

        companyMdl.setAcoCode(paramMdl.getAdr110coCode());
        companyMdl.setAcoName(paramMdl.getAdr110coName());
        companyMdl.setAcoNameKn(paramMdl.getAdr110coNameKn());
        companyMdl.setAcoSini(StringUtilKana.getInitKanaChar(paramMdl.getAdr110coNameKn()));
        companyMdl.setAcoUrl(paramMdl.getAdr110url());
        companyMdl.setAcoBiko(paramMdl.getAdr110biko());
        companyMdl.setAcoAuid(sessionUserSid);
        companyMdl.setAcoAdate(now);
        companyMdl.setAcoEuid(sessionUserSid);
        companyMdl.setAcoEdate(now);

        AdrCompanyDao companyDao = new AdrCompanyDao(con);
        int acoSid = paramMdl.getAdr110editAcoSid();
        if (paramMdl.getAdr110ProcMode() == GSConstAddress.PROCMODE_ADD) {
            acoSid = (int) mtCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                GSConstAddress.SBNSID_SUB_COMPANY, sessionUserSid);
            companyMdl.setAcoSid(acoSid);
            companyDao.insert(companyMdl);
        } else {
            companyMdl.setAcoSid(acoSid);
            companyDao.update(companyMdl);
        }

        //所属業種情報を登録する
        AdrBelongIndustryDao blgIndustryDao = new AdrBelongIndustryDao(con);
        blgIndustryDao.delete(acoSid);

        String[] atiSidList = paramMdl.getAdr110atiList();
        if (atiSidList != null && atiSidList.length > 0) {
            AdrBelongIndustryModel blgIndustryModel = new AdrBelongIndustryModel();
            blgIndustryModel.setAcoSid(acoSid);
            blgIndustryModel.setAbiAuid(sessionUserSid);
            blgIndustryModel.setAbiAdate(now);
            blgIndustryModel.setAbiEuid(sessionUserSid);
            blgIndustryModel.setAbiEdate(now);

            for (String atiSid : atiSidList) {
                blgIndustryModel.setAtiSid(Integer.parseInt(atiSid));
                blgIndustryDao.insert(blgIndustryModel);
            }
        }


        //会社拠点情報を取得する
        List<Integer> editSidList = new ArrayList<Integer>();
        AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con);
        if (paramMdl.getAbaListToList() != null) {
            List<Adr110BaseForm> abaList = paramMdl.getAbaListToList();
            AdrCompanyBaseModel companyBaseModel = new AdrCompanyBaseModel();
            companyBaseModel.setAcoSid(acoSid);
            companyBaseModel.setAbaAuid(sessionUserSid);
            companyBaseModel.setAbaAdate(now);
            companyBaseModel.setAbaEuid(sessionUserSid);
            companyBaseModel.setAbaEdate(now);

            for (Adr110BaseForm baseForm : abaList) {
                int abaSid = baseForm.getAdr110abaSidDetail();
                companyBaseModel.setAbaType(baseForm.getAdr110abaTypeDetail());
                companyBaseModel.setAbaName(baseForm.getAdr110abaName());
                companyBaseModel.setTdfSid(baseForm.getAdr110abaTdfk());
                companyBaseModel.setAbaPostno1(baseForm.getAdr110abaPostno1());
                companyBaseModel.setAbaPostno2(baseForm.getAdr110abaPostno2());
                companyBaseModel.setAbaAddr1(baseForm.getAdr110abaAddress1());
                companyBaseModel.setAbaAddr2(baseForm.getAdr110abaAddress2());
                companyBaseModel.setAbaBiko(baseForm.getAdr110abaBiko());
                if (abaSid > 0) {
                    companyBaseModel.setAbaSid(abaSid);
                    companyBaseDao.update(companyBaseModel);
                } else {
                    abaSid = (int) mtCon.getSaibanNumber(
                                            GSConst.SBNSID_ADDRESS,
                                            GSConstAddress.SBNSID_SUB_CO_BASE,
                                            sessionUserSid);
                    companyBaseModel.setAbaSid(abaSid);
                    companyBaseDao.insert(companyBaseModel);
                }
                editSidList.add(new Integer(abaSid));
            }
        }
        if (paramMdl.getAdr110ProcMode() == GSConstAddress.PROCMODE_EDIT) {
            companyBaseDao.delete(acoSid, editSidList);
        }

        log__.debug("End");
    }
}
