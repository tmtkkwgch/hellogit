package jp.groupsession.v2.api.ntp.nippou.biz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.adr.dao.AdrCompanyBaseDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.model.AdrCompanyBaseModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.api.ntp.nippou.model.ApiNippouDataModel;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpAnkenDao;
import jp.groupsession.v2.ntp.dao.NtpBinDao;
import jp.groupsession.v2.ntp.dao.NtpGoodDao;
import jp.groupsession.v2.ntp.dao.NtpKtbunruiDao;
import jp.groupsession.v2.ntp.dao.NtpKthouhouDao;
import jp.groupsession.v2.ntp.model.NtpAnkenModel;
import jp.groupsession.v2.ntp.model.NtpDataModel;
import jp.groupsession.v2.ntp.model.NtpGoodModel;
import jp.groupsession.v2.ntp.model.NtpKtbunruiModel;
import jp.groupsession.v2.ntp.model.NtpKthouhouModel;
import jp.groupsession.v2.ntp.ntp010.Ntp010Biz;
import jp.groupsession.v2.ntp.ntp040.Ntp040Dao;
import jp.groupsession.v2.ntp.ntp040.model.Ntp040CommentModel;
import jp.groupsession.v2.struts.msg.GsMessage;
/**
 * <br>[機  能] WEBAPI 日報 日報検索ビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiNippouSearchBiz {
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;
    /**
     * コンストラクタ
     * @param reqMdl RequestModel
     * @param con コネクション
     */
    public ApiNippouSearchBiz(RequestModel reqMdl, Connection con) {
        super();
        reqMdl__ = reqMdl;
        con__ = con;
    }
    /**
     *
     * <br>[機  能] 日報情報を収集
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpMdlList ベースモデルリスト
     * @return reports 日報一覧
     * @throws SQLException SQL実行時例外
     */

    public List<ApiNippouDataModel> getReports(List<NtpDataModel> ntpMdlList) throws SQLException
    {

        //日報データリスト
        ArrayList<ApiNippouDataModel> dataList = new ArrayList<ApiNippouDataModel>();

//        Ntp040Biz ntp040Biz = new Ntp040Biz(con__, req__);

        if (!ntpMdlList.isEmpty()) {

            //データセット
            for (NtpDataModel ntpMdl : ntpMdlList) {


                if (ntpMdl == null) {
                    //編集対象が無い場合
                    return dataList;
                }

                ApiNippouDataModel dataMdl = new ApiNippouDataModel();

                dataMdl.setNipSid(ntpMdl.getNipSid());

                CmnUsrmInfModel uMdl = null;

                CmnUsrmInfDao uDao = new CmnUsrmInfDao(con__);
                //登録者
                uMdl = uDao.select(ntpMdl.getUsrSid());
                dataMdl.setUsrSid(ntpMdl.getUsrSid());
                dataMdl.setUsrMdl(uMdl);

                //へんしゅうしゃ
                uMdl = uDao.select(ntpMdl.getNipEuid());
                dataMdl.setNipEuid(ntpMdl.getNipEuid());
                dataMdl.setAddUsrName(uMdl.getUsiSei() + "" + uMdl.getUsiMei());

                //登録日時
                dataMdl.setNipDate(ntpMdl.getNipDate());

                UDate frDate = ntpMdl.getNipFrTime();
                UDate toDate = ntpMdl.getNipToTime();
                //開始年月日

                dataMdl.setNipFrTime(frDate);
                dataMdl.setNipToTime(toDate);


                //活動分類

                dataMdl.setMkbSid(ntpMdl.getMkbSid());
                dataMdl.setKtBunrui(__getKtbunrui(ntpMdl.getMkbSid()));


                //活動方法
                dataMdl.setMkhSid(ntpMdl.getMkhSid());
                dataMdl.setKtHouhou(__getKthouhou(ntpMdl.getMkhSid()));


                //見込み度
                dataMdl.setNipMikomi(ntpMdl.getNipMikomi());










                //背景
                int iniBgcolor = GSConstNippou.DF_BG_COLOR;
                if (ntpMdl.getNipTitleClo() > GSConstNippou.DF_BG_COLOR) {
                    iniBgcolor = ntpMdl.getNipTitleClo();
                }
                dataMdl.setNipTitleClo(iniBgcolor);


                //タイトル
                dataMdl.setNipTitle(ntpMdl.getNipTitle());

                //詳細
                dataMdl.setNipDetail(ntpMdl.getNipDetail());

                //所感



                //案件情報取得
                _getAnkenData(ntpMdl.getNanSid(), dataMdl);

                //会社情報、アドレス帳情報を設定
                _readCompanyData(ntpMdl.getAcoSid(), ntpMdl.getAbaSid(), dataMdl);

                //添付ファイル情報取得
                NtpBinDao binDao = new NtpBinDao(con__);
                ArrayList<CmnBinfModel> retBin = binDao.getFileList(ntpMdl.getNipSid());
                dataMdl.setClips(retBin);

                Ntp010Biz ntp010biz = new Ntp010Biz(con__, reqMdl__);
                int editFlg = 0;
                if (ntp010biz.isAddEditOk(Integer.valueOf(ntpMdl.getUsrSid()), con__)
                       == 0) {
                    editFlg = 1;
                }

                //コメント取得
                Ntp040Dao ntpDao = new Ntp040Dao(con__);

                List<Ntp040CommentModel> npcList
                                    = ntpDao.getNpcList(reqMdl__, ntpMdl.getNipSid(), editFlg);
                dataMdl.setComments(npcList);

                //いいね件数取得
                NtpGoodDao gDao = new NtpGoodDao(con__);
                ArrayList<NtpGoodModel> gList = new ArrayList<NtpGoodModel>();
                gList = gDao.select(ntpMdl.getNipSid());
                dataMdl.setIineCount(gList.size());

                //セッションユーザがいいねしているか
                //セッション情報を取得
                BaseUserModel usModel = reqMdl__.getSmodel();
                int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

                int goodFlg = 0;
                for (NtpGoodModel gMdl : gList) {
                    if (gMdl.getUsrSid() == sessionUsrSid) {
                        goodFlg = 1;
                    }
                }
                dataMdl.setIineFlg(goodFlg);
                dataList.add(dataMdl);

            }

        }



        return dataList;
    }

    /**
     * <br>[機  能] DBから案件情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param nanSid 案件SID
     * @param dataMdl 日報データ
     * @throws SQLException SQL実行時例外
     */
    public void _getAnkenData(
                              int nanSid,
                              ApiNippouDataModel dataMdl) throws SQLException {

        //案件情報
        NtpAnkenDao ankenDao = new NtpAnkenDao(con__);
        NtpAnkenModel ankenModel = ankenDao.select(nanSid);

        if (ankenModel != null) {
            dataMdl.setNanSid(nanSid);
            dataMdl.setNanCode(ankenModel.getNanCode());
            dataMdl.setNanName(ankenModel.getNanName());
        }

    }

    /**
     * <br>[機  能] DBから会社情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param acoSid 会社SID
     * @param abaSid 拠点SID
     * @param dataMdl 日報データ
     * @throws SQLException SQL実行時例外
     */
    public void _readCompanyData(
                                 int acoSid,
                                 int abaSid,
                                 ApiNippouDataModel dataMdl) throws SQLException {

        //会社情報
        AdrCompanyDao companyDao = new AdrCompanyDao(con__);
        AdrCompanyModel companyModel = companyDao.select(acoSid);

        if (companyModel != null) {
            dataMdl.setAcoSid(acoSid);
            dataMdl.setAcoCode(companyModel.getAcoCode());
            dataMdl.setAcoName(companyModel.getAcoName());
        }

//        //所属業種
//        AdrBelongIndustryDao blgIndustryDao = new AdrBelongIndustryDao(con);
//        form.setAdr110atiList(blgIndustryDao.getAtiSidList(form.getAdr110editAcoSid()));
//
//        form.setAdr110initFlg(1);

        //会社拠点情報
        AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con__);
        AdrCompanyBaseModel companyBaseMdl = new AdrCompanyBaseModel();
        companyBaseMdl = companyBaseDao.select(abaSid);
        if (companyBaseMdl != null) {
            dataMdl.setAbaSid(abaSid);
            dataMdl.setAbaName(companyBaseMdl.getAbaName());
        }
    }
    /**
     * <br>[機  能] 活動分類を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param ktSid 活動分類SID
     * @return 活動分類
     * @throws SQLException SQL例外
     */
    public String __getKtbunrui(int ktSid) throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String ktBunrui = gsMsg.getMessage("cmn.notset");

        NtpKtbunruiDao ktBunruiDao = new NtpKtbunruiDao(con__);
        NtpKtbunruiModel bunruiModel = ktBunruiDao.select(ktSid);
        if (bunruiModel != null) {
            ktBunrui = bunruiModel.getNkbName();
        }
        return ktBunrui;
    }
    /**
     * <br>[機  能] 活動方法を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param ktSid 活動方法SID
     * @return 活動方法
     * @throws SQLException SQL例外
     */
    public String __getKthouhou(int ktSid) throws SQLException {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String ktHouhou = gsMsg.getMessage("cmn.notset");


        NtpKthouhouDao houhouDao = new NtpKthouhouDao(con__);

        NtpKthouhouModel khmodel = houhouDao.select(ktSid);

        if (khmodel != null) {
            ktHouhou = khmodel.getNkhName();
        }
        return ktHouhou;
    }
}
