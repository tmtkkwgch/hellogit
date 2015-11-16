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
import jp.groupsession.v2.man.model.base.BbsLogCountAdelModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>BBS_LOG_COUNT_ADEL Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class BbsLogCountAdelDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(BbsLogCountAdelDao.class);

    /**
     * <p>Default Constructor
     */
    public BbsLogCountAdelDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public BbsLogCountAdelDao(Connection con) {
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
            sql.addSql("drop table BBS_LOG_COUNT_ADEL");

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
            sql.addSql(" create table BBS_LOG_COUNT_ADEL (");
            sql.addSql("   BLD_DEL_KBN NUMBER(10,0) not null,");
            sql.addSql("   BLD_DEL_YEAR NUMBER(10,0),");
            sql.addSql("   BLD_DEL_MONTH NUMBER(10,0),");
            sql.addSql("   BLD_AUID NUMBER(10,0) not null,");
            sql.addSql("   BLD_ADATE varchar(23) not null,");
            sql.addSql("   BLD_EUID NUMBER(10,0) not null,");
            sql.addSql("   BLD_EDATE varchar(23) not null");
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
     * <p>Insert BBS_LOG_COUNT_ADEL Data Bindding JavaBean
     * @param bean BBS_LOG_COUNT_ADEL Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(BbsLogCountAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" BBS_LOG_COUNT_ADEL(");
            sql.addSql("   BLD_DEL_KBN,");
            sql.addSql("   BLD_DEL_YEAR,");
            sql.addSql("   BLD_DEL_MONTH,");
            sql.addSql("   BLD_AUID,");
            sql.addSql("   BLD_ADATE,");
            sql.addSql("   BLD_EUID,");
            sql.addSql("   BLD_EDATE");
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
            sql.addIntValue(bean.getBldDelKbn());
            sql.addIntValue(bean.getBldDelYear());
            sql.addIntValue(bean.getBldDelMonth());
            sql.addIntValue(bean.getBldAuid());
            sql.addDateValue(bean.getBldAdate());
            sql.addIntValue(bean.getBldEuid());
            sql.addDateValue(bean.getBldEdate());
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
     * <p>Update BBS_LOG_COUNT_ADEL Data Bindding JavaBean
     * @param bean BBS_LOG_COUNT_ADEL Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(BbsLogCountAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   BBS_LOG_COUNT_ADEL");
            sql.addSql(" set ");
            sql.addSql("   BLD_DEL_KBN=?,");
            sql.addSql("   BLD_DEL_YEAR=?,");
            sql.addSql("   BLD_DEL_MONTH=?,");
            sql.addSql("   BLD_EUID=?,");
            sql.addSql("   BLD_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getBldDelKbn());
            sql.addIntValue(bean.getBldDelYear());
            sql.addIntValue(bean.getBldDelMonth());
            sql.addIntValue(bean.getBldEuid());
            sql.addDateValue(bean.getBldEdate());

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
     * <p>Select BBS_LOG_COUNT_ADEL All Data
     * @return List in BBS_LOG_COUNT_ADELModel
     * @throws SQLException SQL実行例外
     */
    public List<BbsLogCountAdelModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<BbsLogCountAdelModel> ret = new ArrayList<BbsLogCountAdelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   BLD_DEL_KBN,");
            sql.addSql("   BLD_DEL_YEAR,");
            sql.addSql("   BLD_DEL_MONTH,");
            sql.addSql("   BLD_AUID,");
            sql.addSql("   BLD_ADATE,");
            sql.addSql("   BLD_EUID,");
            sql.addSql("   BLD_EDATE");
            sql.addSql(" from ");
            sql.addSql("   BBS_LOG_COUNT_ADEL");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getBbsLogCountAdelFromRs(rs));
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
     * <p>Create BBS_LOG_COUNT_ADEL Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created BbsLogCountAdelModel
     * @throws SQLException SQL実行例外
     */
    private BbsLogCountAdelModel __getBbsLogCountAdelFromRs(ResultSet rs) throws SQLException {
        BbsLogCountAdelModel bean = new BbsLogCountAdelModel();
        bean.setBldDelKbn(rs.getInt("BLD_DEL_KBN"));
        bean.setBldDelYear(rs.getInt("BLD_DEL_YEAR"));
        bean.setBldDelMonth(rs.getInt("BLD_DEL_MONTH"));
        bean.setBldAuid(rs.getInt("BLD_AUID"));
        bean.setBldAdate(UDate.getInstanceTimestamp(rs.getTimestamp("BLD_ADATE")));
        bean.setBldEuid(rs.getInt("BLD_EUID"));
        bean.setBldEdate(UDate.getInstanceTimestamp(rs.getTimestamp("BLD_EDATE")));
        return bean;
    }
}
