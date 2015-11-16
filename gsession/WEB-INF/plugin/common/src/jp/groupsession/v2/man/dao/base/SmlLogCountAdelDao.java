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
import jp.groupsession.v2.man.model.base.SmlLogCountAdelModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>SML_LOG_COUNT_ADEL Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class SmlLogCountAdelDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(SmlLogCountAdelDao.class);

    /**
     * <p>Default Constructor
     */
    public SmlLogCountAdelDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public SmlLogCountAdelDao(Connection con) {
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
            sql.addSql("drop table SML_LOG_COUNT_ADEL");

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
            sql.addSql(" create table SML_LOG_COUNT_ADEL (");
            sql.addSql("   SLD_DEL_KBN NUMBER(10,0) not null,");
            sql.addSql("   SLD_DEL_YEAR NUMBER(10,0),");
            sql.addSql("   SLD_DEL_MONTH NUMBER(10,0),");
            sql.addSql("   SLD_AUID NUMBER(10,0) not null,");
            sql.addSql("   SLD_ADATE varchar(23) not null,");
            sql.addSql("   SLD_EUID NUMBER(10,0) not null,");
            sql.addSql("   SLD_EDATE varchar(23) not null");
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
     * <p>Insert SML_LOG_COUNT_ADEL Data Bindding JavaBean
     * @param bean SML_LOG_COUNT_ADEL Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(SmlLogCountAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" SML_LOG_COUNT_ADEL(");
            sql.addSql("   SLD_DEL_KBN,");
            sql.addSql("   SLD_DEL_YEAR,");
            sql.addSql("   SLD_DEL_MONTH,");
            sql.addSql("   SLD_AUID,");
            sql.addSql("   SLD_ADATE,");
            sql.addSql("   SLD_EUID,");
            sql.addSql("   SLD_EDATE");
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
            sql.addIntValue(bean.getSldDelKbn());
            sql.addIntValue(bean.getSldDelYear());
            sql.addIntValue(bean.getSldDelMonth());
            sql.addIntValue(bean.getSldAuid());
            sql.addDateValue(bean.getSldAdate());
            sql.addIntValue(bean.getSldEuid());
            sql.addDateValue(bean.getSldEdate());
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
     * <p>Update SML_LOG_COUNT_ADEL Data Bindding JavaBean
     * @param bean SML_LOG_COUNT_ADEL Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(SmlLogCountAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SML_LOG_COUNT_ADEL");
            sql.addSql(" set ");
            sql.addSql("   SLD_DEL_KBN=?,");
            sql.addSql("   SLD_DEL_YEAR=?,");
            sql.addSql("   SLD_DEL_MONTH=?,");
            sql.addSql("   SLD_EUID=?,");
            sql.addSql("   SLD_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getSldDelKbn());
            sql.addIntValue(bean.getSldDelYear());
            sql.addIntValue(bean.getSldDelMonth());
            sql.addIntValue(bean.getSldEuid());
            sql.addDateValue(bean.getSldEdate());

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
     * <p>Select SML_LOG_COUNT_ADEL All Data
     * @return List in SML_LOG_COUNT_ADELModel
     * @throws SQLException SQL実行例外
     */
    public List<SmlLogCountAdelModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SmlLogCountAdelModel> ret = new ArrayList<SmlLogCountAdelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SLD_DEL_KBN,");
            sql.addSql("   SLD_DEL_YEAR,");
            sql.addSql("   SLD_DEL_MONTH,");
            sql.addSql("   SLD_AUID,");
            sql.addSql("   SLD_ADATE,");
            sql.addSql("   SLD_EUID,");
            sql.addSql("   SLD_EDATE");
            sql.addSql(" from ");
            sql.addSql("   SML_LOG_COUNT_ADEL");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getSmlLogCountAdelFromRs(rs));
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
     * <p>Create SML_LOG_COUNT_ADEL Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created SmlLogCountAdelModel
     * @throws SQLException SQL実行例外
     */
    private SmlLogCountAdelModel __getSmlLogCountAdelFromRs(ResultSet rs) throws SQLException {
        SmlLogCountAdelModel bean = new SmlLogCountAdelModel();
        bean.setSldDelKbn(rs.getInt("SLD_DEL_KBN"));
        bean.setSldDelYear(rs.getInt("SLD_DEL_YEAR"));
        bean.setSldDelMonth(rs.getInt("SLD_DEL_MONTH"));
        bean.setSldAuid(rs.getInt("SLD_AUID"));
        bean.setSldAdate(UDate.getInstanceTimestamp(rs.getTimestamp("SLD_ADATE")));
        bean.setSldEuid(rs.getInt("SLD_EUID"));
        bean.setSldEdate(UDate.getInstanceTimestamp(rs.getTimestamp("SLD_EDATE")));
        return bean;
    }
}
