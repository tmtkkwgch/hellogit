package jp.groupsession.v2.api.schedule.search;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.StringUtilHtml;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnGroupmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.rsv.dao.RsvSisDataDao;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;
import jp.groupsession.v2.sch.dao.SchAddressDao;
import jp.groupsession.v2.sch.dao.SchCompanyDao;
import jp.groupsession.v2.sch.dao.ScheduleReserveDao;
import jp.groupsession.v2.sch.model.SchAddressModel;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchCompanyModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.sch040.Sch040Biz;
import jp.groupsession.v2.sch.sch040.Sch040Dao;
import jp.groupsession.v2.sch.sch040.model.Sch040AddressModel;
import jp.groupsession.v2.sch.sch040.model.Sch040CompanyModel;
import jp.groupsession.v2.struts.msg.GsMessage;
/**
 *
 * <br>[機  能] スケジュール検索API ビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiSchSearchBiz {
    /** コネクション */
    public Connection con__ = null;
    /** リクエスト */
    public RequestModel reqMdl__ = null;

    /** キャッシュ セッションユーザ所属グループ*/
    List<Integer> belongGpSidList__ = new ArrayList<Integer>();
    /** 予定有り*/
    private String textYoteiari__;
    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl RequestModel
     * @throws SQLException SQL実行時例外
     */
    public ApiSchSearchBiz(Connection con, RequestModel reqMdl) throws SQLException {
        con__ = con;
        reqMdl__ = reqMdl;
        //セッションユーザの所属グループを格納
        CmnBelongmDao bdao = new CmnBelongmDao(con);
        belongGpSidList__ = bdao.selectUserBelongGroupSid(reqMdl.getSmodel().getUsrsid());
        GsMessage gsMsg = new GsMessage(reqMdl);
        //予定あり
        textYoteiari__ = gsMsg.getMessage("schedule.src.9");

    }
    /**
     *
     * <br>[機  能] 表示用スケジュールモデル作成
     * <br>[解  説]
     * <br>[備  考]
     * @param schMdl スケジュールモデル
     * @param adminConf 管理者権限
     * @param escapeFlg htmlエスケープフラグ
     * @return 表示用スケジュールモデル
     * @throws SQLException SQL実行時例外
     */
    public ApiSchSearchModel getDspScheduleMdl(SchDataModel schMdl,
            SchAdmConfModel adminConf,
            boolean escapeFlg
            ) throws SQLException {
        ApiSchSearchModel dspSchMdl = null;
        int usrSid = schMdl.getScdUsrSid();
        int usrKbn = schMdl.getScdUsrKbn();
        int sessionUsrSid = reqMdl__.getSmodel().getUsrsid();
        dspSchMdl = new ApiSchSearchModel();
        //表示グループに所属しているか判定
        boolean grpBelongHnt = isGrpBelongHnt(schMdl,
                sessionUsrSid,
                belongGpSidList__);

        CmnUsrmDao cuDao = new CmnUsrmDao(con__);
        //登録者
        CmnUsrmInfModel uMdl = __getUserModel(schMdl.getScdAuid(), con__);
        if (uMdl != null) {
            dspSchMdl.setScdAuidSei(uMdl.getUsiSei());
            dspSchMdl.setScdAuidMei(uMdl.getUsiMei());
            dspSchMdl.setScdAuidJkbn(cuDao.getUserJkbn(schMdl.getScdAuid()));
        }
        //対象ユーザ
        if (usrKbn == GSConstSchedule.USER_KBN_USER) {
            uMdl = __getUserModel(usrSid, con__);
            if (uMdl != null) {
                dspSchMdl.setScdUsrSei(uMdl.getUsiSei());
                dspSchMdl.setScdUsrMei(uMdl.getUsiMei());
                dspSchMdl.setScdUsrJkbn(cuDao.getUserJkbn(usrSid));
            }
        } else {
            dspSchMdl.setScdUsrSei(__getUserName(usrSid
                    , usrKbn
                    , con__));
            dspSchMdl.setScdUsrMei("");
        }
        dspSchMdl.setScdSid(schMdl.getScdSid());
        dspSchMdl.setScdUsrSid(usrSid);
        dspSchMdl.setScdGrpSid(schMdl.getScdGrpSid());
        dspSchMdl.setScdUsrKbn(usrKbn);
        dspSchMdl.setScdFrDate(schMdl.getScdFrDate());
        dspSchMdl.setScdToDate(schMdl.getScdToDate());
        dspSchMdl.setScdDaily(schMdl.getScdDaily());
        dspSchMdl.setScdBgcolor(schMdl.getScdBgcolor());
        dspSchMdl.setScdTitle(schMdl.getScdTitle());
        if (escapeFlg) {
            dspSchMdl.setScdValue(
                    StringUtilHtml.transToHTmlPlusAmparsant(schMdl.getScdValue()));
            dspSchMdl.setScdBiko(
                    StringUtilHtml.transToHTmlPlusAmparsant(schMdl.getScdBiko()));
        } else {
            dspSchMdl.setScdValue(schMdl.getScdValue());
            dspSchMdl.setScdBiko(schMdl.getScdBiko());
        }
        dspSchMdl.setSeacret(false);
        if (usrKbn == GSConstSchedule.USER_KBN_USER
                && usrSid == sessionUsrSid) {
            //本人
            dspSchMdl.setScdPublic(GSConstSchedule.DSP_PUBLIC);
        } else if (usrKbn == GSConstSchedule.USER_KBN_USER
                && usrSid != sessionUsrSid) {
            //他ユーザ
            if (schMdl.getScdPublic() == GSConstSchedule.DSP_YOTEIARI
                    || (schMdl.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP
                    && !grpBelongHnt)) {
                //予定あり
                dspSchMdl.setScdTitle(textYoteiari__);
                dspSchMdl.setScdValue("");
                dspSchMdl.setScdBiko("");
                dspSchMdl.setScdBgcolor(GSConstSchedule.BGCOLOR_BLACK);
                dspSchMdl.setSeacret(true);

            }

            if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
                //非公開
                return null;
            }
        } else {
            //グループのスケジュール
            if (schMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC
                    && !(grpBelongHnt)) {
                //非公開
                return null;

            }
            dspSchMdl.setScdPublic(schMdl.getScdPublic());

        }

        dspSchMdl.setScdPublic(schMdl.getScdPublic());

        dspSchMdl.setScdAttendKbn(schMdl.getScdAttendKbn());
        dspSchMdl.setScdAttendAns(schMdl.getScdAttendAns());
        dspSchMdl.setScdAttendAuKbn(schMdl.getScdAttendAuKbn());

        dspSchMdl.setScdAuid(schMdl.getScdAuid());
        dspSchMdl.setScdAdate(schMdl.getScdAdate());
        dspSchMdl.setScdEuid(schMdl.getScdEuid());
        dspSchMdl.setScdEdate(schMdl.getScdEdate());
        dspSchMdl.setSceSid(schMdl.getSceSid());
        dspSchMdl.setScdRsSid(schMdl.getScdRsSid());
        dspSchMdl.setScdEdit(schMdl.getScdEdit());

        return dspSchMdl;
    }
    /**
    *
    * <br>[機  能] ユーザSIDからユーザ情報を取得する
    * <br>[解  説] Mapに保存済みの場合はMapから取得する
    * <br>[備  考]
    * @param userSid ユーザSID
    * @param con コネクション
    * @return ユーザモデル
    * @throws SQLException 実行時例外
    */
   private CmnUsrmInfModel __getUserModel(
           int userSid,
           Connection con)
           throws SQLException {
       CmnUsrmInfDao uDao = new CmnUsrmInfDao(con);
       CmnUsrmInfModel uMdl = uDao.select(userSid);
       return uMdl;
   }
   /**
    * ユーザ氏名を取得する
    * <br>[機  能]ユーザ区分がグループの場合はグループ名を取得する
    * <br>[解  説]
    * <br>[備  考]
    * @param userSid ユーザSID
    * @param userKbn ユーザ区分
    * @param con コネクション
    * @return String ユーザ名
    * @throws SQLException SQL実行時例外
    */
   private String __getUserName(int userSid, int userKbn, Connection con)
   throws SQLException {

       String ret = "";
       if (userKbn == GSConstSchedule.USER_KBN_USER) {
           CmnUsrmInfModel uMdl = __getUserModel(userSid, con);
           if (uMdl != null) {
               ret = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
           }
       } else if (userKbn == GSConstSchedule.USER_KBN_GROUP) {
           GroupDao gDao = new GroupDao(con);
           CmnGroupmModel gMdl = null;
           gMdl = gDao.getGroup(userSid);
           if (gMdl != null) {
               ret = gMdl.getGrpName();
           }
       }

       return ret;
   }

    /**
    *
    * <br>[機  能]  関連会社情報、アドレス帳情報を取得
    * <br>[解  説]
    * <br>[備  考]
    * @param scdSid scdSid
    * @param usrSid usrSid
    * @throws SQLException SQL実行例外
    * @return 会社情報Map
    */
   public Map<String, Sch040CompanyModel> getCompanyMap(
           int scdSid,
           int usrSid
           ) throws SQLException {
       Connection con = con__;
       SchCompanyDao companyDao = new SchCompanyDao(con);
       List<SchCompanyModel> companyList = companyDao.select(scdSid);


       SchAddressDao addressDao = new SchAddressDao(con);
       List<SchAddressModel> addressList = addressDao.select(scdSid);
       String[] addressId = null;
       if (addressList != null) {
           addressId = new String[addressList.size()];
           for (int index = 0; index < addressList.size(); index++) {
               addressId[index] = String.valueOf(addressList.get(index).getAdrSid());
           }
       }

       List<String> companyIdList = new ArrayList<String>();
       Map<String, Sch040CompanyModel> companyMap = new HashMap<String, Sch040CompanyModel>();

       Sch040CompanyModel noCompanyModel = new Sch040CompanyModel();
       GsMessage gsMsg = new GsMessage(reqMdl__);
       //会社登録無し
       String textCmpDataNone = gsMsg.getMessage("schedule.src.87");
       noCompanyModel.setCompanyName(textCmpDataNone);
       noCompanyModel.setCompanyAddress(null);
       noCompanyModel.setCompanySid(0);
       noCompanyModel.setCompanyBaseSid(0);
       companyMap.put("0:0", noCompanyModel);

       for (SchCompanyModel company : companyList) {
           int acoSid = company.getAcoSid();
           int abaSid = company.getAbaSid();
           String companyId = acoSid + ":" + abaSid;
           if (!companyMap.containsKey(companyId)) {
               Sch040CompanyModel companyData =
                       __getCompanyFromBaseMap(acoSid, abaSid);
               companyData.setAddressDataList(new ArrayList<Sch040AddressModel>());
               companyMap.put(companyId, companyData);
               companyIdList.add(companyId);
           }
       }

       //アドレス情報を取得
       List<Sch040AddressModel> exAddressList
                   = __getAddressFromBaseMap(addressId, usrSid);
       if (addressList != null) {
           for (Sch040AddressModel adrData : exAddressList) {
               String companyId = adrData.getCompanySid() + ":" + adrData.getCompanyBaseSid();
               Sch040CompanyModel companyData = companyMap.get(companyId);
               if (companyData == null) {
                   companyData = __getCompanyFromBaseMap(adrData.getCompanySid(),
                                                   adrData.getCompanyBaseSid()
                                                   );
                   companyData.setAddressDataList(new ArrayList<Sch040AddressModel>());
                   companyMap.put(companyId, companyData);
                   companyIdList.add(companyId);
               }

               companyData.getAddressDataList().add(adrData);
               companyMap.put(companyId, companyData);
           }
       }
       //会社未選択はアドレスなしの状態では利用できない
       if (noCompanyModel.getAddressDataList() == null
            || noCompanyModel.getAddressDataList().size() == 0) {
            companyMap.remove("0:0");
       }

       return companyMap;
   }
   /**
    *
    * <br>[機  能] アドレス情報取得
    * <br>[解  説]
    * <br>[備  考]
    * @param sidList sid配列
    * @param usrSid セッションユーザSID
    * @return アドレス情報一覧
    * @throws SQLException SQL実行時例外
    */
   private List<Sch040AddressModel> __getAddressFromBaseMap(String[] sidList,
           int usrSid) throws SQLException {
       Sch040Dao sch040dao = new Sch040Dao(con__);

       List<Sch040AddressModel> exAddressList
       = sch040dao.getAddressList(con__, sidList, usrSid);
       return exAddressList;
    }
   /**
    *
    * <br>[機  能] 会社SID、拠点SIDから会社情報を取得する
    * <br>[解  説]
    * <br>[備  考]
    * @param acoSid 会社SID
    * @param abaSid 拠点SID
    * @return 会社情報
    * @throws SQLException SQL実行時例外
    */
   private Sch040CompanyModel __getCompanyFromBaseMap(int acoSid,
           int abaSid
           ) throws SQLException {
       Sch040Biz sch040biz = new Sch040Biz(con__, reqMdl__);
       Sch040Dao sch040dao = new Sch040Dao(con__);
       Sch040CompanyModel companyData = null;

       companyData = sch040biz.createCompanyData(sch040dao, acoSid, abaSid);
       return companyData;
   }

   /**
    * 関連済施設SIDリストを取得
    * <br>[機  能]
    * <br>[解  説]
    * <br>[備  考]
    * @param con コネクション
    * @param scdSid スケジュールSID
    * @param userSid usrSid
    * @param rsvAdmin 施設予約管理者権限
    * @throws SQLException SQL実行例外
    * @return 施設リスト
    */
   public ArrayList<RsvSisDataModel> getSelectResList(
           Connection con
           , int scdSid
           , int userSid
           , boolean rsvAdmin) throws SQLException {
       ScheduleReserveDao schRsvDao = new ScheduleReserveDao(con);
       RsvSisDataDao dataDao = new RsvSisDataDao(con);

       ArrayList<Integer> reservs = schRsvDao.getScheduleReserveData(scdSid);

       ArrayList<RsvSisDataModel> selectResList = null;

       if (reservs != null && reservs.size() > 0) {
           if (rsvAdmin) {
               //全施設
               selectResList =
                   dataDao.selectGrpSisetuList(reservs);
           } else {
               //閲覧権限のある施設
               selectResList =
                   dataDao.selectGrpSisetuCanReadList(reservs, userSid);
           }

       }
       return selectResList;
   }


   /**
    * ユーザ氏名を取得する
    * <br>[機  能]ユーザ区分がグループの場合はグループ名を取得する
    * <br>[解  説]
    * <br>[備  考]
    * @param userSid ユーザSID
    * @param userKbn ユーザ区分
    * @param con コネクション
    * @return String ユーザ名
    * @throws SQLException SQL実行時例外
    */
   public String getUserName(int userSid, int userKbn, Connection con)
   throws SQLException {

       String ret = "";
       if (userKbn == GSConstSchedule.USER_KBN_USER) {
           CmnUsrmInfDao uDao = new CmnUsrmInfDao(con);
           CmnUsrmInfModel uMdl = uDao.select(userSid);
           if (uMdl != null) {
               ret = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
           }
       } else if (userKbn == GSConstSchedule.USER_KBN_GROUP) {
           GroupDao gDao = new GroupDao(con);
           CmnGroupmModel gMdl = gDao.getGroup(userSid);
           if (gMdl != null) {
               ret = gMdl.getGrpName();
           }
       }

       return ret;
   }
   /**
    *
    * <br>[機  能] 表示グループに所属しているか判定する
    * <br>[解  説]
    * <br>[備  考]
    * @param schMdl スケジュールモデル
    * @param sessionUsrSid セッションユーザSID
    * @param belongGpSidList 所属グループ一覧
    * @return 表示ユーザと同じグループに所属するスケジュールならtrue
    * @throws SQLException SQL実行時例外
    */
   public boolean isGrpBelongHnt(SchDataModel schMdl,
           int sessionUsrSid,
           List<Integer> belongGpSidList
           ) throws SQLException {

       //表示グループに所属しているか判定
       GroupBiz gpBiz = new GroupBiz();

       if (schMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_USER) {
           //ユーザスケジュールの場合は表示スケジュールユーザと同じグループに所属しているか判定
           boolean belongFlg = false;
           ArrayList<Integer> belongSids = schMdl.getScdUserBlongGpList();
           if (belongSids != null && !belongSids.isEmpty()) {
               for (int gpSid : belongSids) {
                   if (belongGpSidList != null) {
                       int index = belongGpSidList.indexOf(gpSid);
                       if (index > -1) {
                           belongFlg = true;
                       }
                   }
               }
           }
           return belongFlg;
       } else {
           boolean grpBelongHnt = gpBiz.isBelongGroup(sessionUsrSid, schMdl.getScdUsrSid(), con__);
           return grpBelongHnt;
       }

   }

}
