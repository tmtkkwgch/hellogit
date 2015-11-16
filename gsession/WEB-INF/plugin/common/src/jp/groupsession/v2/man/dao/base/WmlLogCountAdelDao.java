package jp.groupsession.v2.man.dao.base;

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
import jp.groupsession.v2.man.model.base.WmlLogCountAdelModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>WML_LOG_COUNT_ADEL Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class WmlLogCountAdelDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(WmlLogCountAdelDao.class);

    /**
     * <p>Default Constructor
     */
    public WmlLogCountAdelDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public WmlLogCountAdelDao(Connection con) {
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
            sql.addSql("drop table WML_LOG_COUNT_ADEL");

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
            sql.addSql(" create table WML_LOG_COUNT_ADEL (");
            sql.addSql("   WLD_DEL_KBN NUMBER(10,0) not null,");
            sql.addSql("   WLD_DEL_YEAR NUMBER(10,0),");
            sql.addSql("   WLD_DEL_MONTH NUMBER(10,0),");
            sql.addSql("   WLD_AUID NUMBER(10,0) not null,");
            sql.addSql("   WLD_ADATE varchar(23) not null,");
            sql.addSql("   WLD_EUID NUMBER(10,0) not null,");
            sql.addSql("   WLD_EDATE varchar(23) not null");
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
     * <p>Insert WML_LOG_COUNT_ADEL Data Bindding JavaBean
     * @param bean WML_LOG_COUNT_ADEL Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(WmlLogCountAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" WML_LOG_COUNT_ADEL(");
            sql.addSql("   WLD_DEL_KBN,");
            sql.addSql("   WLD_DEL_YEAR,");
            sql.addSql("   WLD_DEL_MONTH,");
            sql.addSql("   WLD_AUID,");
            sql.addSql("   WLD_ADATE,");
            sql.addSql("   WLD_EUID,");
            sql.addSql("   WLD_EDATE");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getWldDelKbn());
            sql.addIntValue(bean.getWldDelYear());
            sql.addIntValue(bean.getWldDelMonth());
            sql.addIntValue(bean.getWldAuid());
            sql.addDateValue(bean.getWldAdate());
            sql.addIntValue(bean.getWldEuid());
            sql.addDateValue(bean.getWldEdate());
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
     * <p>Update WML_LOG_COUNT_ADEL Data Bindding JavaBean
     * @param bean WML_LOG_COUNT_ADEL Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(WmlLogCountAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   WML_LOG_COUNT_ADEL");
            sql.addSql(" set ");
            sql.addSql("   WLD_DEL_KBN=?,");
            sql.addSql("   WLD_DEL_YEAR=?,");
            sql.addSql("   WLD_DEL_MONTH=?,");
            sql.addSql("   WLD_EUID=?,");
            sql.addSql("   WLD_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getWldDelKbn());
            sql.addIntValue(bean.getWldDelYear());
            sql.addIntValue(bean.getWldDelMonth());
            sql.addIntValue(bean.getWldEuid());
            sql.addDateValue(bean.getWldEdate());

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
     * <p>Select WML_LOG_COUNT_ADEL All Data
     * @return List in WML_LOG_COUNT_ADELModel
     * @throws SQLException SQL実行例外
     */
    public List<WmlLogCountAdelModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<WmlLogCountAdelModel> ret = new ArrayList<WmlLogCountAdelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WLD_DEL_KBN,");
            sql.addSql("   WLD_DEL_YEAR,");
            sql.addSql("   WLD_DEL_MONTH,");
            sql.addSql("   WLD_AUID,");
            sql.addSql("   WLD_ADATE,");
            sql.addSql("   WLD_EUID,");
            sql.addSql("   WLD_EDATE");
            sql.addSql(" from ");
            sql.addSql("   WML_LOG_COUNT_ADEL");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getWmlLogCountAdelFromRs(rs));
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
     * <p>Create WML_LOG_COUNT_ADEL Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created WmlLogCountAdelModel
     * @throws SQLException SQL実行例外
     */
    private WmlLogCountAdelModel __getWmlLogCountAdelFromRs(ResultSet rs) throws SQLException {
        WmlLogCountAdelModel bean = new WmlLogCountAdelModel();
        bean.setWldDelKbn(rs.getInt("WLD_DEL_KBN"));
        bean.setWldDelYear(rs.getInt("WLD_DEL_YEAR"));
        bean.setWldDelMonth(rs.getInt("WLD_DEL_MONTH"));
        bean.setWldAuid(rs.getInt("WLD_AUID"));
        bean.setWldAdate(UDate.getInstanceTimestamp(rs.getTimestamp("WLD_ADATE")));
        bean.setWldEuid(rs.getInt("WLD_EUID"));
        bean.setWldEdate(UDate.getInstanceTimestamp(rs.getTimestamp("WLD_EDATE")));
        return bean;
    }
}
