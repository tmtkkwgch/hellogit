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
import jp.groupsession.v2.man.model.base.CirLogCountAdelModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>CIR_LOG_COUNT_ADEL Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class CirLogCountAdelDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(CirLogCountAdelDao.class);

    /**
     * <p>Default Constructor
     */
    public CirLogCountAdelDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public CirLogCountAdelDao(Connection con) {
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
            sql.addSql("drop table CIR_LOG_COUNT_ADEL");

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
            sql.addSql(" create table CIR_LOG_COUNT_ADEL (");
            sql.addSql("   CLD_DEL_KBN NUMBER(10,0) not null,");
            sql.addSql("   CLD_DEL_YEAR NUMBER(10,0),");
            sql.addSql("   CLD_DEL_MONTH NUMBER(10,0),");
            sql.addSql("   CLD_AUID NUMBER(10,0) not null,");
            sql.addSql("   CLD_ADATE varchar(23) not null,");
            sql.addSql("   CLD_EUID NUMBER(10,0) not null,");
            sql.addSql("   CLD_EDATE varchar(23) not null");
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
     * <p>Insert CIR_LOG_COUNT_ADEL Data Bindding JavaBean
     * @param bean CIR_LOG_COUNT_ADEL Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(CirLogCountAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" CIR_LOG_COUNT_ADEL(");
            sql.addSql("   CLD_DEL_KBN,");
            sql.addSql("   CLD_DEL_YEAR,");
            sql.addSql("   CLD_DEL_MONTH,");
            sql.addSql("   CLD_AUID,");
            sql.addSql("   CLD_ADATE,");
            sql.addSql("   CLD_EUID,");
            sql.addSql("   CLD_EDATE");
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
            sql.addIntValue(bean.getCldDelKbn());
            sql.addIntValue(bean.getCldDelYear());
            sql.addIntValue(bean.getCldDelMonth());
            sql.addIntValue(bean.getCldAuid());
            sql.addDateValue(bean.getCldAdate());
            sql.addIntValue(bean.getCldEuid());
            sql.addDateValue(bean.getCldEdate());
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
     * <p>Update CIR_LOG_COUNT_ADEL Data Bindding JavaBean
     * @param bean CIR_LOG_COUNT_ADEL Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(CirLogCountAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CIR_LOG_COUNT_ADEL");
            sql.addSql(" set ");
            sql.addSql("   CLD_DEL_KBN=?,");
            sql.addSql("   CLD_DEL_YEAR=?,");
            sql.addSql("   CLD_DEL_MONTH=?,");
            sql.addSql("   CLD_EUID=?,");
            sql.addSql("   CLD_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getCldDelKbn());
            sql.addIntValue(bean.getCldDelYear());
            sql.addIntValue(bean.getCldDelMonth());
            sql.addIntValue(bean.getCldEuid());
            sql.addDateValue(bean.getCldEdate());

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
     * <p>Select CIR_LOG_COUNT_ADEL All Data
     * @return List in CIR_LOG_COUNT_ADELModel
     * @throws SQLException SQL実行例外
     */
    public List<CirLogCountAdelModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<CirLogCountAdelModel> ret = new ArrayList<CirLogCountAdelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   CLD_DEL_KBN,");
            sql.addSql("   CLD_DEL_YEAR,");
            sql.addSql("   CLD_DEL_MONTH,");
            sql.addSql("   CLD_AUID,");
            sql.addSql("   CLD_ADATE,");
            sql.addSql("   CLD_EUID,");
            sql.addSql("   CLD_EDATE");
            sql.addSql(" from ");
            sql.addSql("   CIR_LOG_COUNT_ADEL");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getCirLogCountAdelFromRs(rs));
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
     * <p>Create CIR_LOG_COUNT_ADEL Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created CirLogCountAdelModel
     * @throws SQLException SQL実行例外
     */
    private CirLogCountAdelModel __getCirLogCountAdelFromRs(ResultSet rs) throws SQLException {
        CirLogCountAdelModel bean = new CirLogCountAdelModel();
        bean.setCldDelKbn(rs.getInt("CLD_DEL_KBN"));
        bean.setCldDelYear(rs.getInt("CLD_DEL_YEAR"));
        bean.setCldDelMonth(rs.getInt("CLD_DEL_MONTH"));
        bean.setCldAuid(rs.getInt("CLD_AUID"));
        bean.setCldAdate(UDate.getInstanceTimestamp(rs.getTimestamp("CLD_ADATE")));
        bean.setCldEuid(rs.getInt("CLD_EUID"));
        bean.setCldEdate(UDate.getInstanceTimestamp(rs.getTimestamp("CLD_EDATE")));
        return bean;
    }
}
