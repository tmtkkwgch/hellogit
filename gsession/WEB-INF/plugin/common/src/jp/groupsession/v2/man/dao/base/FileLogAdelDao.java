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
import jp.groupsession.v2.man.model.base.FileLogAdelModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>FILE_LOG_ADEL Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class FileLogAdelDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(FileLogAdelDao.class);

    /**
     * <p>Default Constructor
     */
    public FileLogAdelDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public FileLogAdelDao(Connection con) {
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
            sql.addSql("drop table FILE_LOG_ADEL");

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
            sql.addSql(" create table FILE_LOG_ADEL (");
            sql.addSql("   FLD_DEL_KBN NUMBER(10,0) not null,");
            sql.addSql("   FLD_DEL_YEAR NUMBER(10,0),");
            sql.addSql("   FLD_DEL_MONTH NUMBER(10,0),");
            sql.addSql("   FLD_AUID NUMBER(10,0) not null,");
            sql.addSql("   FLD_ADATE varchar(23) not null,");
            sql.addSql("   FLD_EUID NUMBER(10,0) not null,");
            sql.addSql("   FLD_EDATE varchar(23) not null");
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
     * <p>Insert FILE_LOG_ADEL Data Bindding JavaBean
     * @param bean FILE_LOG_ADEL Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(FileLogAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" FILE_LOG_ADEL(");
            sql.addSql("   FLD_DEL_KBN,");
            sql.addSql("   FLD_DEL_YEAR,");
            sql.addSql("   FLD_DEL_MONTH,");
            sql.addSql("   FLD_AUID,");
            sql.addSql("   FLD_ADATE,");
            sql.addSql("   FLD_EUID,");
            sql.addSql("   FLD_EDATE");
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
            sql.addIntValue(bean.getFldDelKbn());
            sql.addIntValue(bean.getFldDelYear());
            sql.addIntValue(bean.getFldDelMonth());
            sql.addIntValue(bean.getFldAuid());
            sql.addDateValue(bean.getFldAdate());
            sql.addIntValue(bean.getFldEuid());
            sql.addDateValue(bean.getFldEdate());
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
     * <p>Update FILE_LOG_ADEL Data Bindding JavaBean
     * @param bean FILE_LOG_ADEL Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(FileLogAdelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_LOG_ADEL");
            sql.addSql(" set ");
            sql.addSql("   FLD_DEL_KBN=?,");
            sql.addSql("   FLD_DEL_YEAR=?,");
            sql.addSql("   FLD_DEL_MONTH=?,");
            sql.addSql("   FLD_EUID=?,");
            sql.addSql("   FLD_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFldDelKbn());
            sql.addIntValue(bean.getFldDelYear());
            sql.addIntValue(bean.getFldDelMonth());
            sql.addIntValue(bean.getFldEuid());
            sql.addDateValue(bean.getFldEdate());

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
     * <p>Select FILE_LOG_ADEL All Data
     * @return List in FILE_LOG_ADELModel
     * @throws SQLException SQL実行例外
     */
    public List<FileLogAdelModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileLogAdelModel> ret = new ArrayList<FileLogAdelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FLD_DEL_KBN,");
            sql.addSql("   FLD_DEL_YEAR,");
            sql.addSql("   FLD_DEL_MONTH,");
            sql.addSql("   FLD_AUID,");
            sql.addSql("   FLD_ADATE,");
            sql.addSql("   FLD_EUID,");
            sql.addSql("   FLD_EDATE");
            sql.addSql(" from ");
            sql.addSql("   FILE_LOG_ADEL");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileLogAdelFromRs(rs));
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
     * <p>Create FILE_LOG_ADEL Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created FileLogAdelModel
     * @throws SQLException SQL実行例外
     */
    private FileLogAdelModel __getFileLogAdelFromRs(ResultSet rs) throws SQLException {
        FileLogAdelModel bean = new FileLogAdelModel();
        bean.setFldDelKbn(rs.getInt("FLD_DEL_KBN"));
        bean.setFldDelYear(rs.getInt("FLD_DEL_YEAR"));
        bean.setFldDelMonth(rs.getInt("FLD_DEL_MONTH"));
        bean.setFldAuid(rs.getInt("FLD_AUID"));
        bean.setFldAdate(UDate.getInstanceTimestamp(rs.getTimestamp("FLD_ADATE")));
        bean.setFldEuid(rs.getInt("FLD_EUID"));
        bean.setFldEdate(UDate.getInstanceTimestamp(rs.getTimestamp("FLD_EDATE")));
        return bean;
    }
}
