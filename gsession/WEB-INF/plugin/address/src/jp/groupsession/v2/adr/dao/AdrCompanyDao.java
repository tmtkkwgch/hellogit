package jp.groupsession.v2.adr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.cmn.GSConst;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>ADR_COMPANY Data Access Object
 *
 * @author JTS DaoGenerator version 0.5
 */
public class AdrCompanyDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(AdrCompanyDao.class);

    /**
     * <p>Default Constructor
     */
    public AdrCompanyDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public AdrCompanyDao(Connection con) {
        super(con);
    }

    /**
     * <p>Drop Table
     * @throws SQLException SQL実行例外
     */
    public void dropTable() throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("drop table ADR_COMPANY");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <p>Create Table
     * @throws SQLException SQL実行例外
     */
    public void createTable() throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" create table ADR_COMPANY (");
            sql.addSql("   ACO_SID NUMBER(10,0) not null,");
            sql.addSql("   ACO_CODE varchar(20) not null,");
            sql.addSql("   ACO_NAME varchar(50) not null,");
            sql.addSql("   ACO_NAME_KN varchar(100) not null,");
            sql.addSql("   ACO_SINI varchar(3) not null,");
            sql.addSql("   ACO_URL varchar(100),");
            sql.addSql("   ACO_BIKO varchar(1000),");
            sql.addSql("   ACO_AUID NUMBER(10,0) not null,");
            sql.addSql("   ACO_ADATE varchar(23) not null,");
            sql.addSql("   ACO_EUID NUMBER(10,0) not null,");
            sql.addSql("   ACO_EDATE varchar(23) not null,");
            sql.addSql("   primary key (ACO_SID)");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <p>Insert ADR_COMPANY Data Bindding JavaBean
     * @param bean ADR_COMPANY Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(AdrCompanyModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" ADR_COMPANY(");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ACO_CODE,");
            sql.addSql("   ACO_NAME,");
            sql.addSql("   ACO_NAME_KN,");
            sql.addSql("   ACO_SINI,");
            sql.addSql("   ACO_URL,");
            sql.addSql("   ACO_BIKO,");
            sql.addSql("   ACO_AUID,");
            sql.addSql("   ACO_ADATE,");
            sql.addSql("   ACO_EUID,");
            sql.addSql("   ACO_EDATE");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getAcoSid());
            sql.addStrValue(bean.getAcoCode());
            sql.addStrValue(bean.getAcoName());
            sql.addStrValue(bean.getAcoNameKn());
            sql.addStrValue(bean.getAcoSini());
            sql.addStrValue(bean.getAcoUrl());
            sql.addStrValue(bean.getAcoBiko());
            sql.addIntValue(bean.getAcoAuid());
            sql.addDateValue(bean.getAcoAdate());
            sql.addIntValue(bean.getAcoEuid());
            sql.addDateValue(bean.getAcoEdate());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <p>Update ADR_COMPANY Data Bindding JavaBean
     * @param bean ADR_COMPANY Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(AdrCompanyModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   ADR_COMPANY");
            sql.addSql(" set ");
            sql.addSql("   ACO_CODE=?,");
            sql.addSql("   ACO_NAME=?,");
            sql.addSql("   ACO_NAME_KN=?,");
            sql.addSql("   ACO_SINI=?,");
            sql.addSql("   ACO_URL=?,");
            sql.addSql("   ACO_BIKO=?,");
            sql.addSql("   ACO_EUID=?,");
            sql.addSql("   ACO_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   ACO_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getAcoCode());
            sql.addStrValue(bean.getAcoName());
            sql.addStrValue(bean.getAcoNameKn());
            sql.addStrValue(bean.getAcoSini());
            sql.addStrValue(bean.getAcoUrl());
            sql.addStrValue(bean.getAcoBiko());
            sql.addIntValue(bean.getAcoEuid());
            sql.addDateValue(bean.getAcoEdate());
            //where
            sql.addIntValue(bean.getAcoSid());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <p>Select ADR_COMPANY All Data
     * @return List in ADR_COMPANYModel
     * @throws SQLException SQL実行例外
     */
    public List<AdrCompanyModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<AdrCompanyModel> ret = new ArrayList<AdrCompanyModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ACO_CODE,");
            sql.addSql("   ACO_NAME,");
            sql.addSql("   ACO_NAME_KN,");
            sql.addSql("   ACO_SINI,");
            sql.addSql("   ACO_URL,");
            sql.addSql("   ACO_BIKO,");
            sql.addSql("   ACO_AUID,");
            sql.addSql("   ACO_ADATE,");
            sql.addSql("   ACO_EUID,");
            sql.addSql("   ACO_EDATE");
            sql.addSql(" from ");
            sql.addSql("   ADR_COMPANY");
            sql.addSql(" order by");
            sql.addSql("   ACO_NAME_KN");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getAdrCompanyFromRs(rs));
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
     * <p>Select ADR_COMPANY
     * @param acoSid ACO_SID
     * @return ADR_COMPANYModel
     * @throws SQLException SQL実行例外
     */
    public AdrCompanyModel select(int acoSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        AdrCompanyModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ACO_CODE,");
            sql.addSql("   ACO_NAME,");
            sql.addSql("   ACO_NAME_KN,");
            sql.addSql("   ACO_SINI,");
            sql.addSql("   ACO_URL,");
            sql.addSql("   ACO_BIKO,");
            sql.addSql("   ACO_AUID,");
            sql.addSql("   ACO_ADATE,");
            sql.addSql("   ACO_EUID,");
            sql.addSql("   ACO_EDATE");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY");
            sql.addSql(" where ");
            sql.addSql("   ACO_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(acoSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getAdrCompanyFromRs(rs);
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
     * <br>[機  能] 指定した企業コードの会社情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param acoCode 会社コード
     * @return ADR_COMPANYModel
     * @throws SQLException SQL実行例外
     */
    public AdrCompanyModel select(String acoCode) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        AdrCompanyModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ACO_CODE,");
            sql.addSql("   ACO_NAME,");
            sql.addSql("   ACO_NAME_KN,");
            sql.addSql("   ACO_SINI,");
            sql.addSql("   ACO_URL,");
            sql.addSql("   ACO_BIKO,");
            sql.addSql("   ACO_AUID,");
            sql.addSql("   ACO_ADATE,");
            sql.addSql("   ACO_EUID,");
            sql.addSql("   ACO_EDATE");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY");
            sql.addSql(" where ");
            sql.addSql("   ACO_CODE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(acoCode);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getAdrCompanyFromRs(rs);
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
     * <br>[機  能] 指定した企業コードに該当する会社情報が存在するかをチェックする
     * <br>[解  説] 指定した会社SID > 0 の場合、指定した会社SID以外を存在チェックの条件とする
     * <br>[備  考]
     * @param acoSid 会社SID
     * @param acoCode 会社コード
     * @return 判定結果 true:存在する false:存在しない
     * @throws SQLException SQL実行例外
     */
    public boolean existCompany(int acoSid, String acoCode) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(ACO_SID) as CNT");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY");
            sql.addSql(" where ");
            sql.addSql("   ACO_CODE=?");
            sql.addStrValue(acoCode);

            if (acoSid > 0) {
                sql.addSql(" and ");
                sql.addSql("   ACO_SID != ?");
                sql.addIntValue(acoSid);
            }

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("CNT") > 0;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return result;
    }

    /**
     * <br>[機  能] 登録済み企業の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return cnt 登録済み企業件数
     * @throws SQLException SQL実行例外
     */
    public int getCompanyDataCount() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cnt = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(ACO_SID) as cnt");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY");

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return cnt;
    }

    /**
     * <br>[機  能] 登録済み企業(ページング処理)を取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sortKey ソート対象
     * @param orderKey 並び順
     * @param page ページ
     * @param maxCnt ページ毎の最大表示件数
     * @return 登録済み企業
     * @throws SQLException SQL実行例外
     */
    public ArrayList<AdrCompanyModel> getCompanyDataList(int sortKey,
                                                          int orderKey,
                                                          int page,
                                                          int maxCnt) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<AdrCompanyModel> ret = new ArrayList<AdrCompanyModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ACO_CODE,");
            sql.addSql("   ACO_NAME,");
            sql.addSql("   ACO_NAME_KN,");
            sql.addSql("   ACO_SINI");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY");

            //オーダー
            String orderStr = " asc";
            if (orderKey == GSConst.ORDER_KEY_DESC) {
                orderStr = " desc";
            }

            sql.addSql(" order by");

            //ソートカラム
            switch (sortKey) {
                //企業コード
                case GSConstAddress.COMPANY_SORT_CODE:
                    sql.addSql("   ACO_CODE" + orderStr);
                    break;
                //会社名
                case GSConstAddress.COMPANY_SORT_NAME:
                    sql.addSql("   ACO_NAME_KN" + orderStr);
                    break;
                default:
                    break;
            }

            sql.addSql("   ,");
            sql.addSql("   ACO_SID asc");

            log__.info(sql.toLogString());

            pstmt =
                getCon().prepareStatement(
                        sql.toSqlString(),
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (page > 1) {
                rs.absolute((page - 1) * maxCnt);
            }

            for (int i = 0; rs.next() && i < maxCnt; i++) {
                AdrCompanyModel mdl = new AdrCompanyModel();
                mdl.setAcoSid(rs.getInt("ACO_SID"));
                mdl.setAcoCode(rs.getString("ACO_CODE"));
                mdl.setAcoName(rs.getString("ACO_NAME"));
                mdl.setAcoNameKn(rs.getString("ACO_NAME_KN"));
                mdl.setAcoSini(rs.getString("ACO_SINI"));
                ret.add(mdl);
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
     * <br>[機  能] 会社名カナ 先頭一文字の一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return 検索結果
     * @throws SQLException SQL実行時例外
     */
    public List<String> getcomInitialList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<String> ret = new ArrayList<String>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   ACO_SINI");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY");
            sql.addSql(" group by");
            sql.addSql("   ACO_SINI");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(rs.getString("ACO_SINI"));
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
     * <p>Delete ADR_COMPANY
     * @param acoSid ACO_SID
     * @return delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(int acoSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY");
            sql.addSql(" where ");
            sql.addSql("   ACO_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(acoSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] 指定された企業SIDのリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return ret 企業リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<AdrCompanyModel> getSelectComList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<AdrCompanyModel> ret = new ArrayList<AdrCompanyModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ACO_CODE,");
            sql.addSql("   ACO_NAME,");
            sql.addSql("   ACO_NAME_KN,");
            sql.addSql("   ACO_SINI,");
            sql.addSql("   ACO_URL,");
            sql.addSql("   ACO_BIKO,");
            sql.addSql("   ACO_AUID,");
            sql.addSql("   ACO_ADATE,");
            sql.addSql("   ACO_EUID,");
            sql.addSql("   ACO_EDATE");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY");
//            sql.addSql(" where");
//            sql.addSql("   ACO_SID in (");
//
//            int idx = 0;
//            for (int acoSid : acoList) {
//                sql.addSql("?");
//                sql.addValue(acoSid);
//
//                if (idx < acoList.size() - 1) {
//                    sql.addSql(",");
//                }
//                idx += 1;
//            }
//
//            sql.addSql("   )");

            sql.addSql(" order by");
            sql.addSql("   ACO_NAME_KN asc,");
            sql.addSql("   ACO_SID asc");

            pstmt = getCon().prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getAdrCompanyFromRs(rs));
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
     * <p>Create ADR_COMPANY Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created AdrCompanyModel
     * @throws SQLException SQL実行例外
     */
    private AdrCompanyModel __getAdrCompanyFromRs(ResultSet rs) throws SQLException {
        AdrCompanyModel bean = new AdrCompanyModel();
        bean.setAcoSid(rs.getInt("ACO_SID"));
        bean.setAcoCode(rs.getString("ACO_CODE"));
        bean.setAcoName(rs.getString("ACO_NAME"));
        bean.setAcoNameKn(rs.getString("ACO_NAME_KN"));
        bean.setAcoSini(rs.getString("ACO_SINI"));
        bean.setAcoUrl(rs.getString("ACO_URL"));
        bean.setAcoBiko(rs.getString("ACO_BIKO"));
        bean.setAcoAuid(rs.getInt("ACO_AUID"));
        bean.setAcoAdate(UDate.getInstanceTimestamp(rs.getTimestamp("ACO_ADATE")));
        bean.setAcoEuid(rs.getInt("ACO_EUID"));
        bean.setAcoEdate(UDate.getInstanceTimestamp(rs.getTimestamp("ACO_EDATE")));
        return bean;
    }
}