package jp.groupsession.v2.fil.dao;

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
import jp.groupsession.v2.fil.model.FileDownloadLogModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>FILE_DOWNLOAD_LOG Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class FileDownloadLogDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(FileDownloadLogDao.class);

    /**
     * <p>Default Constructor
     */
    public FileDownloadLogDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public FileDownloadLogDao(Connection con) {
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
            sql.addSql("drop table FILE_DOWNLOAD_LOG");

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
            sql.addSql(" create table FILE_DOWNLOAD_LOG (");
            sql.addSql("   USR_SID NUMBER(10,0) not null,");
            sql.addSql("   FCB_SID NUMBER(10,0) not null,");
            sql.addSql("   BIN_SID Date not null,");
            sql.addSql("   FDL_DATE varchar(23) not null");
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
     * <p>Insert FILE_DOWNLOAD_LOG Data Bindding JavaBean
     * @param bean FILE_DOWNLOAD_LOG Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(FileDownloadLogModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" FILE_DOWNLOAD_LOG(");
            sql.addSql("   USR_SID,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   FDL_DATE");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getFcbSid());
            sql.addLongValue(bean.getBinSid());
            sql.addDateValue(bean.getFdlDate());
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
     * <p>Update FILE_DOWNLOAD_LOG Data Bindding JavaBean
     * @param bean FILE_DOWNLOAD_LOG Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(FileDownloadLogModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DOWNLOAD_LOG");
            sql.addSql(" set ");
            sql.addSql("   USR_SID=?,");
            sql.addSql("   FCB_SID=?,");
            sql.addSql("   BIN_SID=?,");
            sql.addSql("   FDL_DATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getFcbSid());
            sql.addLongValue(bean.getBinSid());
            sql.addDateValue(bean.getFdlDate());

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
     * <p>Select FILE_DOWNLOAD_LOG All Data
     * @return List in FILE_DOWNLOAD_LOGModel
     * @throws SQLException SQL実行例外
     */
    public List<FileDownloadLogModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileDownloadLogModel> ret = new ArrayList<FileDownloadLogModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   USR_SID,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   FDL_DATE");
            sql.addSql(" from ");
            sql.addSql("   FILE_DOWNLOAD_LOG");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDownloadLogFromRs(rs));
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
     * <br>[機  能] 削除を行う
     * <br>[解  説]
     * <br>[備  考] 現在日から指定した年、月前のデータを削除する
     * @param year 年
     * @param month 月
     * @return 削除件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteLogCount(int year, int month) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //削除基準となる日時を生成
            UDate delDate = new UDate();
            delDate.addYear(year * -1);
            delDate.addMonth(month * -1);
            delDate.setMaxHhMmSs();

            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete from");
            sql.addSql("   FILE_DOWNLOAD_LOG");
            sql.addSql(" where ");
            sql.addSql("   FDL_DATE <= ?");
            sql.addDateValue(delDate);

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
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
     * <p>Create FILE_DOWNLOAD_LOG Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created FileDownloadLogModel
     * @throws SQLException SQL実行例外
     */
    private FileDownloadLogModel __getFileDownloadLogFromRs(ResultSet rs) throws SQLException {
        FileDownloadLogModel bean = new FileDownloadLogModel();
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setFcbSid(rs.getInt("FCB_SID"));
        bean.setBinSid(rs.getInt("BIN_SID"));
        bean.setFdlDate(UDate.getInstanceTimestamp(rs.getTimestamp("FDL_DATE")));
        return bean;
    }
}
