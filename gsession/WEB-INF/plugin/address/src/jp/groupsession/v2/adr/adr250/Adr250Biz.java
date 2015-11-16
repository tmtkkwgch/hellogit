package jp.groupsession.v2.adr.adr250;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.adr.dao.AdrBelongIndustryDao;
import jp.groupsession.v2.adr.dao.AdrCompanyBaseDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.dao.AdrTypeindustryDao;
import jp.groupsession.v2.adr.model.AdrCompanyBaseModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.adr.model.AdrTypeindustryModel;
import jp.groupsession.v2.cmn.dao.base.CmnTdfkDao;
import jp.groupsession.v2.cmn.model.base.CmnTdfkModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アドレス帳 会社情報ポップアップのビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr250Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr250Biz.class);
    /** コネクション */
    private Connection con__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     */
    public Adr250Biz(Connection con) {
        con__ = con;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr250ParamModel
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Adr250ParamModel paramMdl)
    throws SQLException {
       log__.debug("Adr250Biz start");
       __readCompanyData(paramMdl);

        //所属業種名称を取得する
        String[] atiSidList = paramMdl.getAdr250atiList();
        if (atiSidList != null) {
            String[] atiName = new String[atiSidList.length];
            AdrTypeindustryDao industryDao = new AdrTypeindustryDao(con__);
            for (int index = 0; index < atiSidList.length; index++) {
                AdrTypeindustryModel industryMdl
                    = industryDao.select(Integer.parseInt(atiSidList[index]));
                atiName[index] = industryMdl.getAtiName();
            }
            paramMdl.setAdr250ViewAtiList(atiName);
        }

        //備考の設定を行う
        paramMdl.setAdr250ViewBiko(
                StringUtilHtml.transToHTmlPlusAmparsant(
                        NullDefault.getString(paramMdl.getAdr250biko(), "")));

        //会社拠点情報の都道府県名を設定する
        List<Adr250BaseForm> baseList = paramMdl.getAbaList();
        if (baseList != null) {
            CmnTdfkDao tdfkDao = new CmnTdfkDao(con__);

            for (Adr250BaseForm baseForm : baseList) {
                int tdfkSid = baseForm.getAdr250abaTdfk();
                CmnTdfkModel tdfkMdl = tdfkDao.select(tdfkSid);
                if (tdfkMdl != null) {
                    baseForm.setAdr250abaTdfkName(tdfkMdl.getTdfName());
                }
            }
        }

    }

    /**
     * <br>[機  能] DBから会社情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr250ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void __readCompanyData(Adr250ParamModel paramMdl) throws SQLException {

        //会社情報
        int acoSid = paramMdl.getAdr250AcoSid();
        AdrCompanyDao companyDao = new AdrCompanyDao(con__);
        AdrCompanyModel companyModel = companyDao.select(acoSid);

        paramMdl.setAdr250coCode(companyModel.getAcoCode());
        paramMdl.setAdr250coName(companyModel.getAcoName());
        paramMdl.setAdr250coNameKn(companyModel.getAcoNameKn());
        paramMdl.setAdr250url(companyModel.getAcoUrl());
        paramMdl.setAdr250biko(companyModel.getAcoBiko());

        //所属業種
        AdrBelongIndustryDao blgIndustryDao = new AdrBelongIndustryDao(con__);
        paramMdl.setAdr250atiList(blgIndustryDao.getAtiSidList(paramMdl.getAdr250AcoSid()));

        //会社拠点情報
        int abaIndex = 0;
        List<Adr250BaseForm> abaList = new ArrayList<Adr250BaseForm>();
        AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con__);
        List<AdrCompanyBaseModel> baseList = companyBaseDao.getCompanyBaseList(acoSid);
        for (AdrCompanyBaseModel baseModel : baseList) {
            Adr250BaseForm baseForm = new Adr250BaseForm();
            baseForm.setAdr250abaIndex(abaIndex);
            baseForm.setAdr250abaSidDetail(baseModel.getAbaSid());
            baseForm.setAdr250abaTypeDetail(baseModel.getAbaType());
            baseForm.setAdr250abaName(baseModel.getAbaName());
            baseForm.setAdr250abaTdfk(baseModel.getTdfSid());
            baseForm.setAdr250abaPostno1(baseModel.getAbaPostno1());
            baseForm.setAdr250abaPostno2(baseModel.getAbaPostno2());
            baseForm.setAdr250abaAddress1(baseModel.getAbaAddr1());
            baseForm.setAdr250abaAddress2(baseModel.getAbaAddr2());
            baseForm.setAdr250abaBiko(baseModel.getAbaBiko());

            abaList.add(baseForm);
            abaIndex++;
        }
        paramMdl.setAbaList(abaList);
    }
}