package jp.groupsession.v2.api.adress.search;

import static jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_COBASENAME;
import static jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONAME;
import static jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_DATE;
import static jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_ENTRYUSER;
import static jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_TITLE;
import static jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_CONTACT_TYPE;
import static jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_POSITION;
import static jp.groupsession.v2.adr.adr010.Adr010Const.SORTKEY_UNAME;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr010.Adr010Const;
import jp.groupsession.v2.adr.adr010.dao.Adr010Dao;
import jp.groupsession.v2.adr.adr010.model.Adr010SearchModel;
import jp.groupsession.v2.adr.biz.AddressBiz;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.api.adress.companybase.model.ApiCompanyBaseModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *
 * <br>[機  能] アドレス検索DAO
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiAdrSearchDao extends Adr010Dao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ApiAdrSearchDao.class);
/**
 *
 * @param con コネクション
 *
 */
    public ApiAdrSearchDao(Connection con) {
        super(con);
    }

    /**
     * <br>[機  能] アドレス帳情報の検索を行う(WEBAPI用)
     * <br>[解  説]
     * <br>[備  考]
     * @param model 検索条件
     * @param req リクエスト
     * @return 検索結果
     * @throws SQLException SQL実行時例外
     */
    public List<ApiAdrDetailModel> getSearchResultList(Adr010SearchModel model,
            RequestModel req)
throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<ApiAdrDetailModel> ret = new ArrayList<ApiAdrDetailModel>();
        con = getCon();

        try {
            SqlBuffer sql = _createSearchSQLForExport(model);
            //並び順を設定
            int order = model.getOrderKey();
            sql.addSql(" order by");
            switch (model.getSortKey()) {
                case SORTKEY_UNAME :
                    break;
                case SORTKEY_CONAME :
                    sql.addSql(_getSortSql("ADR_COMPANY.ACO_NAME_KN", order) + ",");
                    sql.addSql(_getSortSql("ADR_ADDRESS.ACO_SID", order) + ",");
                    sql.addSql(_getSortSql("COMPANY_BASE.ABA_NAME", order) + ",");
                    sql.addSql(_getSortSql("ADR_ADDRESS.ABA_SID", order) + ",");
                    break;
                case SORTKEY_COBASENAME :
                    sql.addSql(_getSortSql("COMPANY_BASE.ABA_NAME", order));
                    break;
                case SORTKEY_POSITION :
                    sql.addSql(_getSortSql("YAKUSYOKU_EXIST", order) + ",");
                    sql.addSql(_getSortSql("ADR_POSITION.APS_SORT", order));
                    break;
                case SORTKEY_CONTACT_DATE :
                    sql.addSql(_getSortSql("CONTACT.ADC_CTTIME", order));
                    break;
                case SORTKEY_CONTACT_TYPE :
                    sql.addSql(_getSortSql("CONTACT.ADC_TYPE", order));
                    break;
                case SORTKEY_CONTACT_TITLE :
                    sql.addSql(_getSortSql("CONTACT.ADC_TITLE", order));
                    break;
                case SORTKEY_CONTACT_ENTRYUSER :
                    sql.addSql(_getSortSql("CONTACT.USI_SEI_KN", order) + ",");
                    sql.addSql(_getSortSql("CONTACT.USI_MEI_KN", order));
                    break;
                default :
                    break;
            }
            sql.addSql(_getSortSql("ADR_ADDRESS.ADR_SEI_KN", order) + ",");
            sql.addSql(_getSortSql("ADR_ADDRESS.ADR_MEI_KN", order));

//            log__.info(sql.toLogString());
//
//            pstmt = con.prepareStatement(sql.toSqlString());
//
//            sql.setParameter(pstmt);
//            rs = pstmt.executeQuery();
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString(),
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int page = model.getPage();
            int maxCnt = model.getMaxViewCount();
            if (maxCnt > 0) {
                if (page > 1) {
                    rs.absolute((page - 1) * maxCnt);
                }
            }
            for (int i = 0; rs.next() && (i < maxCnt || maxCnt == 0); i++) {

                ret.add(__AdrDetailModelFromRs(rs, model, req));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return ret;
    }
    /**
     * <br>[機  能] アドレスの存在する会社一覧の数を取得する
     * <br>[解  説] 拠点情報単位で取得する。
     * <br>[備  考]
     * @param model 検索条件
     * @return 件数
     * @throws SQLException SQL実行時例外
     */
    public int countCompanyList(Adr010SearchModel model)
    throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select count(*) as CNT");
            sql.addSql(" from ");
            sql.addSql("   ( ");

            sql.addSql("     select");

            //プロジェクト検索
            if (model.isPrjSerchFlg() == true) {
                sql.addSql("   count(distinct ADR_ADDRESS.ADR_SID) as CNT");
            } else {
                sql.addSql("   count(ADR_ADDRESS.ADR_SID) as CNT");
            }

            sql = _createSearchSql(sql, model);

            //会社指定なしアドレスを条件から外す
            sql.addSql("   and ADR_ADDRESS.ACO_SID > 0");
            sql.addSql(" group by ADR_ADDRESS.ACO_SID,ADR_ADDRESS.ABA_SID");
            sql.addSql(" )");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("CNT");
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }
    /**
     * <br>[機  能] 会社一覧を取得する
     * <br>[解  説] 拠点情報単位で取得する。
     * <br>[備  考]
     * @param model 検索条件
     * @return 検索結果
     * @throws SQLException SQL実行時例外
     */
    public List<ApiCompanyBaseModel> getCompanyList(Adr010SearchModel model)
            throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<ApiCompanyBaseModel> ret = new ArrayList<ApiCompanyBaseModel>();
        con = getCon();

        try {
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            //プロジェクト検索
            if (model.isPrjSerchFlg() == true) {
                sql.addSql("   count(distinct ADR_ADDRESS.ADR_SID) as CNT,");
            } else {
                sql.addSql("   count(ADR_ADDRESS.ADR_SID) as CNT,");
            }

            sql.addSql("   ADR_COMPANY.ACO_SID as ACO_SID,");
            sql.addSql("   ADR_COMPANY.ACO_CODE as ACO_CODE,");
            sql.addSql("   ADR_COMPANY.ACO_NAME as ACO_NAME,");
            sql.addSql("   ADR_COMPANY.ACO_NAME_KN as ACO_NAME_KN,");
            sql.addSql("   ADR_COMPANY.ACO_URL as ACO_URL,");
            sql.addSql("   ADR_COMPANY.ACO_BIKO as ACO_BIKO,");

            sql.addSql("   COMPANY_BASE.ABA_SID as ABA_SID,");
            sql.addSql("   COMPANY_BASE.ABA_TYPE as ABA_TYPE,");
            sql.addSql("   COMPANY_BASE.ABA_NAME as ABA_NAME,");
            sql.addSql("   COMPANY_BASE.ABA_BIKO as ABA_BIKO,");
            sql.addSql("   COMPANY_BASE.ABA_POSTNO1 as ABA_POSTNO1,");
            sql.addSql("   COMPANY_BASE.ABA_POSTNO2 as ABA_POSTNO2,");
            sql.addSql("   COMPANY_BASE.TDF_SID as TDF_SID,");
            sql.addSql("   COMPANY_BASE.TDF_NAME as TDF_NAME,");
            sql.addSql("   COMPANY_BASE.ABA_ADDR1 as ABA_ADDR1,");
            sql.addSql("   COMPANY_BASE.ABA_ADDR2 as ABA_ADDR2");

            sql = _createSearchSql(sql, model);

            //会社指定なしアドレスを条件から外す
            sql.addSql("   and ADR_ADDRESS.ACO_SID > 0");
            sql.addSql(" group by ADR_ADDRESS.ACO_SID,ADR_ADDRESS.ABA_SID");

            //並び順を設定
            int order = model.getOrderKey();
            sql.addSql(" order by");
            sql.addSql(_getSortSql("ADR_COMPANY.ACO_NAME_KN", order));

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString(),
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int page = model.getPage();
            int maxCnt = model.getMaxViewCount();
            if (page > 1) {
                rs.absolute((page - 1) * maxCnt);
            }
            Map<Integer, AdrCompanyModel> companyMap = new HashMap<Integer, AdrCompanyModel>();
            ApiCompanyBaseModel abaMdl = null;
            for (int i = 0; rs.next() && i < maxCnt; i++) {

                abaMdl = new ApiCompanyBaseModel();
                Integer acoSid = new Integer(rs.getInt("ACO_SID"));
                if (companyMap.containsKey(acoSid)) {
                    abaMdl.setAco(companyMap.get(acoSid));
                } else {
                    AdrCompanyModel aco = new AdrCompanyModel();
                    aco.setAcoSid(rs.getInt("ACO_SID"));
                    aco.setAcoName(rs.getString("ACO_NAME"));
                    aco.setAcoNameKn(rs.getString("ACO_NAME_KN"));
                    aco.setAcoCode(rs.getString("ACO_CODE"));
                    aco.setAcoBiko(rs.getString("ACO_BIKO"));
                    aco.setAcoUrl(rs.getString("ACO_URL"));
                    companyMap.put(acoSid, aco);
                    abaMdl.setAco(aco);
                }
                abaMdl.setAbaSid(rs.getInt("ABA_SID"));
                abaMdl.setAbaType(rs.getInt("ABA_TYPE"));
                abaMdl.setAbaName(rs.getString("ABA_NAME"));
                abaMdl.setAbaPostno1(rs.getString("ABA_POSTNO1"));
                abaMdl.setAbaPostno2(rs.getString("ABA_POSTNO2"));
                abaMdl.setTdfSid(rs.getInt("TDF_SID"));
                abaMdl.setTdfkName(rs.getString("TDF_NAME"));
                abaMdl.setAbaAddr1(rs.getString("ABA_ADDR1"));
                abaMdl.setAbaAddr2(rs.getString("ABA_ADDR2"));
                abaMdl.setAbaBiko(rs.getString("ABA_BIKO"));

                ret.add(abaMdl);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return ret;

    }
    /**
     * <br>[機  能] アドレス帳の検索SQLを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param model 検索条件
     * @return SQLBuffer
     * @throws SQLException SQL実行時例外
     *
     */
    protected SqlBuffer _createSearchSQLForExport(Adr010SearchModel model) throws SQLException
    {
        //SQL文
        SqlBuffer sql = new SqlBuffer();
        sql.addSql(" select");
        //プロジェクト検索
        if (model.isPrjSerchFlg() == true) {
            sql.addSql(" distinct ADR_ADDRESS.ADR_SID as ADR_SID,");
            sql.addSql("   ADR_ADDRESS.ADR_SEI_KN as ADR_SEI_KN,");
            sql.addSql("   ADR_ADDRESS.ADR_MEI_KN as ADR_MEI_KN,");
            sql.addSql("   ADR_COMPANY.ACO_NAME_KN as ACO_NAME_KN,");
        } else {
            sql.addSql("   ADR_ADDRESS.ADR_SID as ADR_SID,");
        }
        sql.addSql("   ADR_ADDRESS.ADR_SEI as ADR_SEI,");
        sql.addSql("   ADR_ADDRESS.ADR_MEI as ADR_MEI,");
        sql.addSql("   ADR_ADDRESS.ADR_SEI_KN as ADR_SEI_KN,");
        sql.addSql("   ADR_ADDRESS.ADR_MEI_KN as ADR_MEI_KN,");
        sql.addSql("   ADR_ADDRESS.ACO_SID as ACO_SID,");
        sql.addSql("   ADR_ADDRESS.ABA_SID as ABA_SID,");
        sql.addSql("   ADR_ADDRESS.ADR_SYOZOKU as ADR_SYOZOKU,");
        sql.addSql("   ADR_ADDRESS.APS_SID as APS_SID,");
        sql.addSql("   (case");
        sql.addSql("      when ADR_ADDRESS.APS_SID= -1 then 1");
        sql.addSql("      else 0");
        sql.addSql("    end) as YAKUSYOKU_EXIST,");
        sql.addSql("   ADR_POSITION.APS_NAME as APS_NAME,");
        sql.addSql("   ADR_POSITION.APS_SORT as APS_SORT,");
        sql.addSql("   ADR_ADDRESS.ADR_MAIL1 as ADR_MAIL1,");
        sql.addSql("   ADR_ADDRESS.ADR_MAIL_CMT1 as ADR_MAIL_CMT1,");
        sql.addSql("   ADR_ADDRESS.ADR_MAIL2 as ADR_MAIL2,");
        sql.addSql("   ADR_ADDRESS.ADR_MAIL_CMT2 as ADR_MAIL_CMT2,");
        sql.addSql("   ADR_ADDRESS.ADR_MAIL3 as ADR_MAIL3,");
        sql.addSql("   ADR_ADDRESS.ADR_MAIL_CMT3 as ADR_MAIL_CMT3,");
        sql.addSql("   ADR_ADDRESS.ADR_POSTNO1 as ADR_POSTNO1,");
        sql.addSql("   ADR_ADDRESS.ADR_POSTNO2 as ADR_POSTNO2,");
        sql.addSql("   ADR_ADDRESS.TDF_SID as TDF_SID,");
        sql.addSql("   CMN_TDFK.TDF_NAME as ADDRESS_TDF_NAME,");
        sql.addSql("   ADR_ADDRESS.ADR_ADDR1 as ADR_ADDR1,");
        sql.addSql("   ADR_ADDRESS.ADR_ADDR2 as ADR_ADDR2,");
        sql.addSql("   ADR_ADDRESS.ADR_TEL1 as ADR_TEL1,");
        sql.addSql("   ADR_ADDRESS.ADR_TEL_NAI1 as ADR_TEL_NAI1,");
        sql.addSql("   ADR_ADDRESS.ADR_TEL_CMT1 as ADR_TEL_CMT1,");
        sql.addSql("   ADR_ADDRESS.ADR_TEL2 as ADR_TEL2,");
        sql.addSql("   ADR_ADDRESS.ADR_TEL_NAI2 as ADR_TEL_NAI2,");
        sql.addSql("   ADR_ADDRESS.ADR_TEL_CMT2 as ADR_TEL_CMT2,");
        sql.addSql("   ADR_ADDRESS.ADR_TEL3 as ADR_TEL3,");
        sql.addSql("   ADR_ADDRESS.ADR_TEL_NAI3 as ADR_TEL_NAI3,");
        sql.addSql("   ADR_ADDRESS.ADR_TEL_CMT3 as ADR_TEL_CMT3,");
        sql.addSql("   ADR_ADDRESS.ADR_FAX1 as ADR_FAX1,");
        sql.addSql("   ADR_ADDRESS.ADR_FAX_CMT1 as ADR_FAX_CMT1,");
        sql.addSql("   ADR_ADDRESS.ADR_FAX2 as ADR_FAX2,");
        sql.addSql("   ADR_ADDRESS.ADR_FAX_CMT2 as ADR_FAX_CMT2,");
        sql.addSql("   ADR_ADDRESS.ADR_FAX3 as ADR_FAX3,");
        sql.addSql("   ADR_ADDRESS.ADR_FAX_CMT3 as ADR_FAX_CMT3,");
        sql.addSql("   ADR_ADDRESS.ADR_BIKO as ADR_BIKO,");
        sql.addSql("   ADR_ADDRESS.ADR_AUID as ADR_AUID,");
        sql.addSql("   ADR_ADDRESS.ADR_ADATE as ADR_ADATE,");
        sql.addSql("   ADR_ADDRESS.ADR_EUID as ADR_EUID,");
        sql.addSql("   ADR_ADDRESS.ADR_EDATE as ADR_EDATE,");

        if (model.getCmdMode() == Adr010Const.CMDMODE_CONTACT) {
            sql.addSql("   CONTACT.ADC_SID as ADC_SID,");
            sql.addSql("   CONTACT.ADC_TITLE as ADC_TITLE,");
            sql.addSql("   CONTACT.ADC_TYPE as ADC_TYPE,");
            sql.addSql("   CONTACT.ADC_CTTIME as ADC_CTTIME,");
            sql.addSql("   CONTACT.ADC_CTTIME_TO as ADC_CTTIME_TO,");
            sql.addSql("   CONTACT.USI_SEI as CONTACT_USI_SEI,");
            sql.addSql("   CONTACT.USI_MEI as CONTACT_USI_MEI,");
            sql.addSql("   CONTACT.ADC_BIKO as ADC_BIKO,");
        }

        sql.addSql("   ADR_COMPANY.ACO_CODE as ACO_CODE,");
        sql.addSql("   ADR_COMPANY.ACO_NAME as ACO_NAME,");
        sql.addSql("   ADR_COMPANY.ACO_NAME_KN as ACO_NAME_KN,");
        sql.addSql("   ADR_COMPANY.ACO_URL as ACO_URL,");

        sql.addSql("   COMPANY_BASE.ABA_TYPE as ABA_TYPE,");
        sql.addSql("   COMPANY_BASE.ABA_NAME as ABA_NAME,");
        sql.addSql("   COMPANY_BASE.ABA_POSTNO1 as ABA_POSTNO1,");
        sql.addSql("   COMPANY_BASE.ABA_POSTNO2 as ABA_POSTNO2,");
        sql.addSql("   COMPANY_BASE.TDF_NAME as COMPANY_BASE_TDF_NAME,");
        sql.addSql("   COMPANY_BASE.ABA_ADDR1 as ABA_ADDR1,");
        sql.addSql("   COMPANY_BASE.ABA_ADDR2 as ABA_ADDR2");

        sql = _createSearchSql(sql, model);
        return sql;
    }

    /**
     * <br>[機  能] アドレス帳の検索SQLの条件部を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SQLBuffer
     * @param model 検索条件
     * @return SQLBuffer
     * @throws SQLException SQL実行時例外
     */
    protected SqlBuffer _createSearchSql(SqlBuffer sql, Adr010SearchModel model)
    throws SQLException {

        sql.addSql(" from");
        if (model.getCmdMode() == Adr010Const.CMDMODE_CONTACT) {
            sql.addSql("   (");
            sql.addSql("    select");
            sql.addSql("      ADR_CONTACT.ADC_SID as ADC_SID,");
            sql.addSql("      ADR_CONTACT.ADR_SID as ADR_SID,");
            sql.addSql("      ADR_CONTACT.ADC_TITLE as ADC_TITLE,");
            sql.addSql("      ADR_CONTACT.ADC_TYPE as ADC_TYPE,");
            sql.addSql("      ADR_CONTACT.ADC_CTTIME as ADC_CTTIME,");
            sql.addSql("      ADR_CONTACT.ADC_CTTIME_TO as ADC_CTTIME_TO,");
            sql.addSql("      ADR_CONTACT.PRJ_SID as PRJ_SID,");
            sql.addSql("      ADR_CONTACT.ADC_BIKO as ADC_BIKO,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as USI_MEI_KN");
            sql.addSql("    from");
            sql.addSql("      ADR_CONTACT");
            sql.addSql("      left join");
            sql.addSql("        CMN_USRM_INF");
            sql.addSql("      on");
            sql.addSql("        ADR_CONTACT.ADC_AUID = CMN_USRM_INF.USR_SID");
            if (model.getPrjSid() > 0) {
                sql.addSql("      left join");
                sql.addSql("        ADR_CONTACT_PRJ");
                sql.addSql("      on");
                sql.addSql("        ADR_CONTACT.ADC_SID = ADR_CONTACT_PRJ.ADC_SID");
            }

            sql.addSql("    where");
            if (model.isDateSchFlg()) {
                sql.addSql("      ADR_CONTACT.ADC_CTTIME>=?");
                sql.addSql("    and");
                sql.addSql("      ADR_CONTACT.ADC_CTTIME_TO<=?");
                sql.addSql("    and");
                sql.addDateValue(model.getDateFr());
                sql.addDateValue(model.getDateTo());
            }

            if (model.getSyubetsu() == GSConstAddress.NOT_SYUBETU) {
                sql.addSql("      ADR_CONTACT.ADC_TYPE>=0");
            } else {
                sql.addSql("      ADR_CONTACT.ADC_TYPE=?");
                sql.addIntValue(model.getSyubetsu());
            }

            if (model.getPrjSid() > 0) {
                sql.addSql("    and");
                sql.addSql("      ADR_CONTACT_PRJ.PRJ_SID=?");
                sql.addIntValue(model.getPrjSid());
            }

            // キーワード入力時
            List<String> keywordList = model.getAdrKeyValue();
            if (keywordList != null && keywordList.size() > 0) {

                String keywordJoin = "  and";
                if (model.getKeyWordkbn() == GSConstAddress.KEY_WORD_KBN_OR) {
                    keywordJoin = "   or";
                }

                //検索対象の「タイトル」がチェック済みの場合
                if (model.isTargetTitle()) {
                    sql.addSql("  and");
                    if (model.isTargetBiko()) {
                        sql.addSql("    (");
                    }
                    sql.addSql("      (");
                    for (int i = 0; i < keywordList.size(); i++) {
                        if (i > 0) {
                            sql.addSql(keywordJoin);
                        }
                        sql.addSql("       ADR_CONTACT.ADC_TITLE like '%"
                                + JDBCUtil.encFullStringLike(keywordList.get(i))
                                + "%' ESCAPE '" + JDBCUtil.def_esc + "'");
                    }
                    sql.addSql("      )");
                }

                //検索対象の「内容」がチェック済みの場合
                if (model.isTargetBiko()) {
                    if (model.isTargetTitle()) {
                        sql.addSql("      or");
                    } else {
                        sql.addSql("      and");
                    }
                    sql.addSql("      (");
                    for (int i = 0; i < keywordList.size(); i++) {
                        if (i > 0) {
                            sql.addSql(keywordJoin);
                        }
                        sql.addSql("       ADR_CONTACT.ADC_BIKO like '%"
                                + JDBCUtil.encFullStringLike(keywordList.get(i))
                                + "%' ESCAPE '" + JDBCUtil.def_esc + "'");
                    }
                    sql.addSql("      )");

                    if (model.isTargetTitle()) {
                        sql.addSql("    )");
                    }
                }
            }
            sql.addSql("   ) CONTACT,");
        }
        //プロジェクト検索
        if (model.isPrjSerchFlg() == true) {
            sql.addSql("   PRJ_MEMBERS,");
            sql.addSql("   PRJ_PRJDATA,");
        }
        sql.addSql("   ADR_ADDRESS");
        sql.addSql("   left join");
        sql.addSql("     ADR_COMPANY");
        sql.addSql("   on");
        sql.addSql("     ADR_ADDRESS.ACO_SID = ADR_COMPANY.ACO_SID");
        sql.addSql("   left join");
        sql.addSql("     (");
        sql.addSql("      select");
        sql.addSql("        ADR_COMPANY_BASE.ABA_SID as ABA_SID,");
        sql.addSql("        ADR_COMPANY_BASE.ACO_SID as ACO_SID,");
        sql.addSql("        ADR_COMPANY_BASE.ABA_TYPE as ABA_TYPE,");
        sql.addSql("        ADR_COMPANY_BASE.ABA_NAME as ABA_NAME,");
        sql.addSql("        ADR_COMPANY_BASE.ABA_POSTNO1 as ABA_POSTNO1,");
        sql.addSql("        ADR_COMPANY_BASE.ABA_POSTNO2 as ABA_POSTNO2,");
        sql.addSql("        ADR_COMPANY_BASE.TDF_SID as TDF_SID,");
        sql.addSql("        ADR_COMPANY_BASE.ABA_ADDR1 as ABA_ADDR1,");
        sql.addSql("        ADR_COMPANY_BASE.ABA_ADDR2 as ABA_ADDR2,");
        sql.addSql("        ADR_COMPANY_BASE.ABA_BIKO as ABA_BIKO,");
        sql.addSql("        CMN_TDFK.TDF_NAME as TDF_NAME");
        sql.addSql("      from");
        sql.addSql("        ADR_COMPANY_BASE");
        sql.addSql("      left join");
        sql.addSql("        CMN_TDFK");
        sql.addSql("      on");
        sql.addSql("        ADR_COMPANY_BASE.TDF_SID = CMN_TDFK.TDF_SID");
        sql.addSql("     ) COMPANY_BASE");
        sql.addSql("   on");
        sql.addSql("     ADR_ADDRESS.ABA_SID = COMPANY_BASE.ABA_SID");
        sql.addSql("   left join");
        sql.addSql("     ADR_POSITION");
        sql.addSql("   on");
        sql.addSql("     ADR_ADDRESS.APS_SID = ADR_POSITION.APS_SID");
        sql.addSql("   left join");
        sql.addSql("     CMN_TDFK");
        sql.addSql("   on");
        sql.addSql("     ADR_ADDRESS.TDF_SID = CMN_TDFK.TDF_SID");

          //プロジェクト検索
          if (model.isPrjSerchFlg() == true) {
            sql.addSql("   left join");
            sql.addSql("     PRJ_ADDRESS");
            sql.addSql("   on");
            sql.addSql("     ADR_ADDRESS.ADR_SID = PRJ_ADDRESS.ADR_SID");
            sql.addSql("   left join");
            sql.addSql("     PRJ_COMPANY");
            sql.addSql("   on");
            sql.addSql("     ADR_COMPANY.ACO_SID = PRJ_COMPANY.ACO_SID");
          }

        //閲覧権限
        sql.addSql(" where");

        if (model.getCmdMode() == Adr010Const.CMDMODE_CONTACT) {
            sql.addSql("   CONTACT.ADR_SID = ADR_ADDRESS.ADR_SID");
            sql.addSql(" and");

            int cnt = model.getHaveTmpFileAdcSids().size();

            if (model.getTempFileExist() != GSConstAddress.TEMPFILE_KBN_FREE && cnt > 0) {
                //添付ファイルが存在するコンタクト履歴SID
                if (model.getTempFileExist() == GSConstAddress.TEMPFILE_KBN_EXIST) {
                    sql.addSql(" CONTACT.ADC_SID in(");

                //添付ファイルが存在しないコンタクト履歴SID
                } else if (model.getTempFileExist() == GSConstAddress.TEMPFILE_KBN_NOT_EXIST) {
                    sql.addSql(" CONTACT.ADC_SID not in(");
                }

                for (Integer adcSid : model.getHaveTmpFileAdcSids()) {
                    sql.addSql(String.valueOf(adcSid));
                    if (cnt > 1) {
                        sql.addSql(",");
                    }
                    cnt--;
                }
                sql.addSql(")");
                sql.addSql(" and");
            }
        }

        sql.addSql("   (");
        sql.addSql("     ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
        sql.addSql("   or");
        sql.addSql("     (");
        sql.addSql("       ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
        sql.addSql("     and");
        sql.addSql("       ADR_ADDRESS.ADR_SID in (");
        sql.addSql("         select ADR_SID from ADR_PERSONCHARGE");
        sql.addSql("         where USR_SID = ?");
        sql.addSql("       )");
        sql.addSql("     )");
        sql.addSql("   or");
        sql.addSql("     (");
        sql.addSql("       ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
        sql.addSql("     and");
        sql.addSql("       ADR_ADDRESS.ADR_SID in (");
        sql.addSql("         select ADR_SID from ADR_PERMIT_VIEW");
        sql.addSql("         where");
        sql.addSql("           GRP_SID in (");
        sql.addSql("             select GRP_SID from CMN_BELONGM");
        sql.addSql("             where USR_SID = ?");
        sql.addSql("           )");
        sql.addSql("       )");
        sql.addSql("     )");
        sql.addSql("   or");
        sql.addSql("     (");
        sql.addSql("       ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
        sql.addSql("     and");
        sql.addSql("       ADR_ADDRESS.ADR_SID in (");
        sql.addSql("         select ADR_SID from ADR_PERMIT_VIEW");
        sql.addSql("         where USR_SID = ?");
        sql.addSql("       )");
        sql.addSql("     )");
        sql.addSql("   )");

        //プロジェクト検索
        if (model.isPrjSerchFlg() == true) {
            sql.addSql(" and");
            sql.addSql("   PRJ_MEMBERS.PRJ_SID = PRJ_PRJDATA.PRJ_SID");
            sql.addSql(" and");
            sql.addSql("   PRJ_MEMBERS.PRJ_SID = PRJ_ADDRESS.PRJ_SID");

            //参加プロジェクトかどうか
            if (model.getProjectKbn() == 0) {
                sql.addSql(" and");
                sql.addSql("   PRJ_MEMBERS.USR_SID = ?");
            }

            //プロジェクト状態:未完了
            if (model.getStatusKbn() == 0) {
                sql.addSql(" and");
                sql.addSql(" exists (");
                sql.addSql("   select * from");
                sql.addSql("   PRJ_PRJDATA,");
                sql.addSql("   PRJ_PRJSTATUS");
                sql.addSql("   where");
                sql.addSql("     PRJ_PRJDATA.PRJ_SID = PRJ_PRJSTATUS.PRJ_SID");
                sql.addSql("   and");
                sql.addSql("     PRJ_PRJDATA.PRJ_STATUS_SID = PRJ_PRJSTATUS.PRS_SID");
                sql.addSql("   and");
                sql.addSql("     PRJ_ADDRESS.PRJ_SID = PRJ_PRJSTATUS.PRJ_SID");
                sql.addSql("   and");
                sql.addSql("     PRS_RATE < 100");
                sql.addSql(" )");

            //プロジェクト状態:完了
            } else if (model.getStatusKbn() == 1) {
                sql.addSql(" and");
                sql.addSql(" exists (");
                sql.addSql("   select * from");
                sql.addSql("   PRJ_PRJDATA,");
                sql.addSql("   PRJ_PRJSTATUS");
                sql.addSql("   where");
                sql.addSql("     PRJ_PRJDATA.PRJ_SID = PRJ_PRJSTATUS.PRJ_SID");
                sql.addSql("   and");
                sql.addSql("     PRJ_PRJDATA.PRJ_STATUS_SID = PRJ_PRJSTATUS.PRS_SID");
                sql.addSql("   and");
                sql.addSql("     PRJ_ADDRESS.PRJ_SID = PRJ_PRJSTATUS.PRJ_SID");
                sql.addSql("   and");
                sql.addSql("     PRS_RATE = 100");
                sql.addSql(" )");
            }

            if (model.getPrjSid() != -1) {
                sql.addSql(" and");
                sql.addSql("   PRJ_ADDRESS.PRJ_SID = ?");
            }
        }

        int sessionUserSid = model.getSessionUser();
        sql.addIntValue(GSConst.ADR_VIEWPERMIT_NORESTRICTION);
        sql.addIntValue(GSConst.ADR_VIEWPERMIT_OWN);
        sql.addIntValue(sessionUserSid);
        sql.addIntValue(GSConst.ADR_VIEWPERMIT_GROUP);
        sql.addIntValue(sessionUserSid);
        sql.addIntValue(GSConst.ADR_VIEWPERMIT_USER);
        sql.addIntValue(sessionUserSid);

        //プロジェクト検索
        if (model.isPrjSerchFlg() == true) {
            if (model.getProjectKbn() == 0) {
                sql.addIntValue(sessionUserSid);
            }

            if (model.getPrjSid() != -1) {
                sql.addIntValue(model.getPrjSid());
            }
        }

        //会社名カナ 頭文字
        if (!StringUtil.isNullZeroString(model.getCnameKnHead())) {
            String[] initials = model.getCnameKnHead().split(",");

            sql.addSql(" and");
            sql.addSql("   ADR_COMPANY.ACO_SINI in (");
            int i = 0;
            for (String kana : initials) {
                if (i > 0) {
                    sql.addSql(" ,");
                }
                sql.addSql("?");
                sql.addStrValue(kana);
                i++;
            }

            sql.addSql(")");
        }

        //企業コード
        if (!StringUtil.isNullZeroString(model.getCoCode())) {
            sql.addSql(" and");
            sql.addSql("   ADR_COMPANY.ACO_CODE = ?");
            sql.addStrValue(model.getCoCode());
        }

        //会社名
        if (!StringUtil.isNullZeroString(model.getCoName())) {
            sql.addSql(" and");
            sql.addSql("   ADR_COMPANY.ACO_NAME like '%"
                    + JDBCUtil.encFullStringLike(model.getCoName())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
        }

        //会社名カナ
        if (!StringUtil.isNullZeroString(model.getCoNameKn())) {
            sql.addSql(" and");
            sql.addSql("   ADR_COMPANY.ACO_NAME_KN like '%"
                    + JDBCUtil.encFullStringLike(model.getCoNameKn())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
        }

        //支店・営業所名
        if (!StringUtil.isNullZeroString(model.getCoBaseName())) {
            sql.addSql(" and");
            sql.addSql("   COMPANY_BASE.ABA_NAME like '%"
                    + JDBCUtil.encFullStringLike(model.getCoBaseName())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
        }

        //業種
        if (model.getAtiSid() > 0) {
            sql.addSql(" and");
            sql.addSql("   ADR_COMPANY.ACO_SID in (");
            sql.addSql("     select ACO_SID from ADR_BELONG_INDUSTRY");
            sql.addSql("     where");
            sql.addSql("       ATI_SID = ?");
            sql.addSql("   )");
            sql.addIntValue(model.getAtiSid());
        }

        //都道府県
        if (model.getTdfk() > 0) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.TDF_SID = ?");
            sql.addIntValue(model.getTdfk());
        }

        //備考
        if (!StringUtil.isNullZeroString(model.getBiko())) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_BIKO like '%"
                    + JDBCUtil.encFullStringLike(model.getBiko())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
        }

        //氏名カナ 頭文字
        if (!StringUtil.isNullZeroString(model.getUnameKnHead())) {
            String[] initials = model.getUnameKnHead().split(",");

            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_SINI in (");
            int i = 0;
            for (String kana : initials) {
                if (i > 0) {
                    sql.addSql(" ,");
                }
                sql.addSql("?");
                sql.addStrValue(kana);
                i++;
            }

            sql.addSql(")");
        }

        //ユーザ・グループ
        if (model.getGroup() > 0 && model.getUser() < 1) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_SID in (");
            sql.addSql("     select");
            sql.addSql("       ADR_PERSONCHARGE.ADR_SID");
            sql.addSql("     from");
            sql.addSql("       ADR_PERSONCHARGE,");
            sql.addSql("       (");
            sql.addSql("         select");
            sql.addSql("           USR_SID");
            sql.addSql("         from");
            sql.addSql("           CMN_BELONGM");
            sql.addSql("         where");
            sql.addSql("           GRP_SID = ?");
            sql.addSql("       ) GRP");
            sql.addSql("     where ADR_PERSONCHARGE.USR_SID = GRP.USR_SID");
            sql.addSql("   )");
            sql.addIntValue(model.getGroup());
        } else if (model.getUser() > 0) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_SID in (");
            sql.addSql("     select ADR_SID from ADR_PERSONCHARGE");
            sql.addSql("     where USR_SID = ?");
            sql.addSql("   )");
            sql.addIntValue(model.getUser());
        }

        //氏名 姓
        if (!StringUtil.isNullZeroString(model.getUnameSei())) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_SEI like '%"
                    + JDBCUtil.encFullStringLike(model.getUnameSei())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
        }

        //氏名 名
        if (!StringUtil.isNullZeroString(model.getUnameMei())) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_MEI like '%"
                    + JDBCUtil.encFullStringLike(model.getUnameMei())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
        }

        //氏名カナ 姓
        if (!StringUtil.isNullZeroString(model.getUnameSeiKn())) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_SEI_KN like '%"
                    + JDBCUtil.encFullStringLike(model.getUnameSeiKn())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
        }

        //氏名カナ 名
        if (!StringUtil.isNullZeroString(model.getUnameMeiKn())) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_MEI_KN like '%"
                    + JDBCUtil.encFullStringLike(model.getUnameMeiKn())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
        }

        //役職
        if (model.getPosition() > 0) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.APS_SID = ?");
            sql.addIntValue(model.getPosition());
        }

        //E-MAIL
        if (!StringUtil.isNullZeroString(model.getMail())) {
            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("      ADR_ADDRESS.ADR_MAIL1 like '%"
                    + JDBCUtil.encFullStringLike(model.getMail())
                    + "%' ESCAPE '" + JDBCUtil.def_esc + "'");
            sql.addSql("   or");
            sql.addSql("      ADR_ADDRESS.ADR_MAIL2 like '%"
                    + JDBCUtil.encFullStringLike(model.getMail())
                    + "%' ESCAPE '" + JDBCUtil.def_esc + "'");
            sql.addSql("   or");
            sql.addSql("      ADR_ADDRESS.ADR_MAIL3 like '%"
                    + JDBCUtil.encFullStringLike(model.getMail())
                    + "%' ESCAPE '" + JDBCUtil.def_esc + "'");
            sql.addSql("   )");
        }

        //ラベル
        if (model.getLabel() != null && model.getLabel().length > 0) {
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_SID in (");
            sql.addSql("     select ADR_SID from ADR_BELONG_LABEL");
            sql.addSql("     where");
            sql.addSql("       ALB_SID in (");

            int index = 0;
            int lastIndex = model.getLabel().length - 1;
            for (String albSid : model.getLabel()) {
                if (index == lastIndex) {
                    sql.addSql("         ?");
                } else {
                    sql.addSql("         ?,");
                }
                sql.addIntValue(NullDefault.getInt(albSid, 0));
                index++;
            }
            sql.addSql("       )");

            sql.addSql("   )");
        }

        //会社SID
        if (model.getCompanySid() != null) {
            sql.addSql(" and");
            sql.addSql("   COALESCE(ADR_ADDRESS.ACO_SID, 0) = ?");
            sql.addIntValue(model.getCompanySid().intValue());
        }

        //会社拠点SID
        if (model.getCompanyBaseSid() != null) {
            sql.addSql(" and");
            sql.addSql("   COALESCE(ADR_ADDRESS.ABA_SID, 0) = ?");
            sql.addIntValue(model.getCompanyBaseSid().intValue());
        }

        return sql;
    }
    /**
     * <p>Create ADR_ADRESS Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @param model 検索条件
     * @param req リクエスト
     * @return created AdrBelongLabelModel
     * @throws SQLException SQL実行例外
     */
    private ApiAdrDetailModel __AdrDetailModelFromRs(ResultSet rs
            , Adr010SearchModel model
            , RequestModel req) throws SQLException {
        ApiAdrDetailModel detailMdl = new ApiAdrDetailModel();

        //アドレス帳SID
        detailMdl.setAdrSid(rs.getInt("ADR_SID"));
        //会社SID
        detailMdl.setAcoSid(rs.getInt("ACO_SID"));
        //会社拠点SID
        detailMdl.setAbaSid(rs.getInt("ABA_SID"));

        //氏名
        detailMdl.setUserName(rs.getString("ADR_SEI") + " " + rs.getString("ADR_MEI"));
        //氏名カナ
        detailMdl.setUserNameKn(rs.getString("ADR_SEI_KN")
                                + " " + rs.getString("ADR_MEI_KN"));
        detailMdl.setSei(rs.getString("ADR_SEI"));
        detailMdl.setMei(rs.getString("ADR_MEI"));
        detailMdl.setSeiKn(rs.getString("ADR_SEI_KN"));
        detailMdl.setMeiKn(rs.getString("ADR_MEI_KN"));

        //会社名
        detailMdl.setCompanyName(rs.getString("ACO_NAME"));
        //会社名カナ
        detailMdl.setCompanyNameKn(rs.getString("ACO_NAME_KN"));
        //url
        detailMdl.setCompanyUrl(rs.getString("ACO_URL"));

        //支店・営業所種別
        int abaType = rs.getInt("ABA_TYPE");
        detailMdl.setCompanyBaseType(AddressBiz.getCompanyBaseTypeName(abaType, req));
        //支店・営業所名
        detailMdl.setCompanyBaseName(rs.getString("ABA_NAME"));
        //支店・営業所郵便番号上３桁
        detailMdl.setCompanyBasePostNo1(rs.getString("ABA_POSTNO1"));
        //支店・営業所郵便番号下４桁
        detailMdl.setCompanyBasePostNo2(rs.getString("ABA_POSTNO2"));
        //支店・営業所都道府県
        detailMdl.setCompanyBaseTdfk(rs.getString("COMPANY_BASE_TDF_NAME"));
        //支店・営業所住所１
        detailMdl.setCompanyBaseAddress1(rs.getString("ABA_ADDR1"));
        //支店・営業所住所２
        detailMdl.setCompanyBaseAddress2(rs.getString("ABA_ADDR2"));

        //所属
        detailMdl.setSyozoku(rs.getString("ADR_SYOZOKU"));
        //役職名
        detailMdl.setPositionName(rs.getString("APS_NAME"));

        //メールアドレス１
        detailMdl.setMail1(rs.getString("ADR_MAIL1"));
        //メールアドレスコメント１
        detailMdl.setMail1Comment(rs.getString("ADR_MAIL_CMT1"));
        //メールアドレス２
        detailMdl.setMail2(rs.getString("ADR_MAIL2"));
        //メールアドレスコメント１
        detailMdl.setMail2Comment(rs.getString("ADR_MAIL_CMT2"));
        //メールアドレス３
        detailMdl.setMail3(rs.getString("ADR_MAIL3"));
        //メールアドレスコメント１
        detailMdl.setMail3Comment(rs.getString("ADR_MAIL_CMT3"));

        //郵便番号上３桁
        detailMdl.setPostNo1(rs.getString("ADR_POSTNO1"));
        //郵便番号下４桁
        detailMdl.setPostNo2(rs.getString("ADR_POSTNO2"));
        //都道府県
        detailMdl.setTdfk(rs.getString("ADDRESS_TDF_NAME"));
        //住所１
        detailMdl.setAddress1(rs.getString("ADR_ADDR1"));
        //住所２
        detailMdl.setAddress2(rs.getString("ADR_ADDR2"));

        //電話番号１
        detailMdl.setTel1(rs.getString("ADR_TEL1"));
        //内線１
        detailMdl.setNai1(rs.getString("ADR_TEL_NAI1"));
        //電話番号コメント１
        detailMdl.setTel1Comment(rs.getString("ADR_TEL_CMT1"));
        //内線２
        detailMdl.setTel2(rs.getString("ADR_TEL2"));
        //電話番号２
        detailMdl.setNai2(rs.getString("ADR_TEL_NAI2"));
        //電話番号コメント２
        detailMdl.setTel2Comment(rs.getString("ADR_TEL_CMT2"));
        //電話番号３
        detailMdl.setTel3(rs.getString("ADR_TEL3"));
        //内線３
        detailMdl.setNai3(rs.getString("ADR_TEL_NAI3"));
        //電話番号コメント３
        detailMdl.setTel3Comment(rs.getString("ADR_TEL_CMT3"));

        //ＦＡＸ１
        detailMdl.setFax1(rs.getString("ADR_FAX1"));
        //ＦＡＸコメント１
        detailMdl.setFax1Comment(rs.getString("ADR_FAX_CMT1"));
        //ＦＡＸ２
        detailMdl.setFax2(rs.getString("ADR_FAX2"));
        //ＦＡＸコメント２
        detailMdl.setFax2Comment(rs.getString("ADR_FAX_CMT2"));
        //ＦＡＸ３
        detailMdl.setFax3(rs.getString("ADR_FAX3"));
        //ＦＡＸコメント３
        detailMdl.setFax3Comment(rs.getString("ADR_FAX_CMT3"));

        //備考
        detailMdl.setBiko(rs.getString("ADR_BIKO"));

        detailMdl.setAuid(rs.getInt("ADR_AUID"));
        detailMdl.setAdate(UDate.getInstanceTimestamp(rs.getTimestamp("ADR_ADATE")));
        detailMdl.setEuid(rs.getInt("ADR_EUID"));
        detailMdl.setEdate(UDate.getInstanceTimestamp(rs.getTimestamp("ADR_EDATE")));

        if (model.getCmdMode() == Adr010Const.CMDMODE_CONTACT) {
            detailMdl.setContactSid(rs.getInt("ADC_SID"));

            UDate frm = UDate.getInstanceTimestamp(rs.getTimestamp("ADC_CTTIME"));
            detailMdl.setContactDate(frm.getStrYear()  + "/"
                         + frm.getStrMonth()  + "/"
                         + frm.getStrDay());
            detailMdl.setContactTime(frm.getStrHour()  + ":"
                              + frm.getStrMinute());
            UDate to = UDate.getInstanceTimestamp(rs.getTimestamp("ADC_CTTIME_TO"));
            detailMdl.setContactDateTo(to.getStrYear()  + "/"
                       + to.getStrMonth()  + "/"
                       + to.getStrDay());
            detailMdl.setContactTimeTo(to.getStrHour()  + ":"
                             + to.getStrMinute());

            detailMdl.setContactType(rs.getInt("ADC_TYPE"));
            detailMdl.setContactTitle(rs.getString("ADC_TITLE"));
            detailMdl.setContactEntryUser(
                    rs.getString("CONTACT_USI_SEI")
                    + " "
                    + rs.getString("CONTACT_USI_MEI"));

            detailMdl.setContactBiko(rs.getString("ADC_BIKO"));

        }
        return detailMdl;
    }
}
