package jp.groupsession.v2.adr.adr111kn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrBelongIndustryDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.model.AdrBelongIndustryModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnTdfkDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnTdfkModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アドレス帳 拠点登録登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr111knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr111knBiz.class);
    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr111knBiz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr111knParamModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Adr111knParamModel paramMdl) throws SQLException {

        //都道府県名称を設定する
        int tdfkSid = paramMdl.getAdr111abaTdfk();
        if (tdfkSid > 0) {
            CmnTdfkDao tdfkDao = new CmnTdfkDao(con);
            CmnTdfkModel tdfkMdl = tdfkDao.select(tdfkSid);
            if (tdfkMdl != null) {
                paramMdl.setAdr111knAbaTdfkName(tdfkMdl.getTdfName());
            }
        }

        //備考の設定を行う
        paramMdl.setAdr111knViewBiko(
                StringUtilHtml.transToHTmlPlusAmparsant(
                        NullDefault.getString(paramMdl.getAdr111abaBiko(), "")));
    }

    /**
     * <br>[機  能] アドレス帳の登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr111knParamModel
     * @param mtCon 採番コントローラ
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     */
    public void entryCompanyData(
            Connection con, Adr111knParamModel paramMdl, MlCountMtController mtCon,
                                int sessionUserSid)
    throws IOException, IOToolsException, SQLException {

        log__.debug("START");

        UDate now = new UDate();

        //会社情報の登録を行う
        AdrCompanyModel companyMdl = new AdrCompanyModel();

        companyMdl.setAcoCode(paramMdl.getAdr110coCode());
        companyMdl.setAcoName(paramMdl.getAdr110coName());
        companyMdl.setAcoNameKn(paramMdl.getAdr110coNameKn());
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

        //所属業種情報を取得する
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

        log__.debug("End");
    }
}
