package jp.groupsession.v2.fil.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.DBUtilFactory;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.fil040.FileDirectoryDspModel;
import jp.groupsession.v2.fil.model.FileDirectoryModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>FILE_DIRECTORY Data Access Object
 *
 * @author JTS DaoGenerator version 0.5
 */
public class FileDirectoryDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(FileDirectoryDao.class);

    /**
     * <p>Default Constructor
     */
    public FileDirectoryDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public FileDirectoryDao(Connection con) {
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
            sql.addSql("drop table FILE_DIRECTORY");

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
            sql.addSql(" create table FILE_DIRECTORY (");
            sql.addSql("   FDR_SID NUMBER(10,0) not null,");
            sql.addSql("   FDR_VERSION NUMBER(10,0) not null,");
            sql.addSql("   FCB_SID NUMBER(10,0) not null,");
            sql.addSql("   FDR_PARENT_SID NUMBER(10,0) not null,");
            sql.addSql("   FDR_KBN NUMBER(10,0) not null,");
            sql.addSql("   FDR_VER_KBN NUMBER(10,0) not null,");
            sql.addSql("   FDR_LEVEL NUMBER(10,0) not null,");
            sql.addSql("   FDR_NAME varchar(255) not null,");
            sql.addSql("   FDR_BIKO varchar(3000),");
            sql.addSql("   FDR_JTKBN NUMBER(10,0) not null,");
            sql.addSql("   FDR_AUID NUMBER(10,0) not null,");
            sql.addSql("   FDR_ADATE varchar(23) not null,");
            sql.addSql("   FDR_EUID NUMBER(10,0) not null,");
            sql.addSql("   FDR_EDATE varchar(23) not null,");
            sql.addSql("   FDR_EGID NUMBER(10,0),");
            sql.addSql("   FDR_ACCESS_SID NUMBER(10,0),");
            sql.addSql("   primary key (FDR_SID,FDR_VERSION)");
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
     * <p>Insert FILE_DIRECTORY Data Bindding JavaBean
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(FileDirectoryModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" FILE_DIRECTORY(");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
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
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFdrSid());
            sql.addIntValue(bean.getFdrVersion());
            sql.addIntValue(bean.getFcbSid());
            sql.addIntValue(bean.getFdrParentSid());
            sql.addIntValue(bean.getFdrKbn());
            sql.addIntValue(bean.getFdrVerKbn());
            sql.addIntValue(bean.getFdrLevel());
            sql.addStrValue(bean.getFdrName());
            sql.addStrValue(bean.getFdrBiko());
            sql.addIntValue(bean.getFdrJtkbn());
            sql.addIntValue(bean.getFdrAuid());
            sql.addDateValue(bean.getFdrAdate());
            sql.addIntValue(bean.getFdrEuid());
            sql.addDateValue(bean.getFdrEdate());
            sql.addIntValue(bean.getFdrEgid());
            sql.addIntValue(bean.getFdrAccessSid());
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
     * <p>Insert FILE_DIRECTORY Data Bindding JavaBean
     * @param beanList FILE_DIRECTORY DataList
     * @throws SQLException SQL実行例外
     */
    public void insert(List<FileDirectoryModel> beanList) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        if (beanList == null || beanList.size() <= 0) {
            return;
        }

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" FILE_DIRECTORY(");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
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
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());

            for (FileDirectoryModel bean : beanList) {
                sql.addIntValue(bean.getFdrSid());
                sql.addIntValue(bean.getFdrVersion());
                sql.addIntValue(bean.getFcbSid());
                sql.addIntValue(bean.getFdrParentSid());
                sql.addIntValue(bean.getFdrKbn());
                sql.addIntValue(bean.getFdrVerKbn());
                sql.addIntValue(bean.getFdrLevel());
                sql.addStrValue(bean.getFdrName());
                sql.addStrValue(bean.getFdrBiko());
                sql.addIntValue(bean.getFdrJtkbn());
                sql.addIntValue(bean.getFdrAuid());
                sql.addDateValue(bean.getFdrAdate());
                sql.addIntValue(bean.getFdrEuid());
                sql.addDateValue(bean.getFdrEdate());
                sql.addIntValue(bean.getFdrEgid());
                sql.addIntValue(bean.getFdrAccessSid());
                log__.info(sql.toLogString());
                sql.setParameter(pstmt);
                pstmt.executeUpdate();
                sql.clearValue();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <p>Update FILE_DIRECTORY Data Bindding JavaBean
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(FileDirectoryModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" set ");
            sql.addSql("   FCB_SID=?,");
            sql.addSql("   FDR_PARENT_SID=?,");
            sql.addSql("   FDR_KBN=?,");
            sql.addSql("   FDR_VER_KBN=?,");
            sql.addSql("   FDR_LEVEL=?,");
            sql.addSql("   FDR_NAME=?,");
            sql.addSql("   FDR_BIKO=?,");
            sql.addSql("   FDR_JTKBN=?,");
            sql.addSql("   FDR_AUID=?,");
            sql.addSql("   FDR_ADATE=?,");
            sql.addSql("   FDR_EUID=?,");
            sql.addSql("   FDR_EDATE=?,");
            sql.addSql("   FDR_EGID=?,");
            sql.addSql("   FDR_ACCESS_SID=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   FDR_VERSION=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFcbSid());
            sql.addIntValue(bean.getFdrParentSid());
            sql.addIntValue(bean.getFdrKbn());
            sql.addIntValue(bean.getFdrVerKbn());
            sql.addIntValue(bean.getFdrLevel());
            sql.addStrValue(bean.getFdrName());
            sql.addStrValue(bean.getFdrBiko());
            sql.addIntValue(bean.getFdrJtkbn());
            sql.addIntValue(bean.getFdrAuid());
            sql.addDateValue(bean.getFdrAdate());
            sql.addIntValue(bean.getFdrEuid());
            sql.addDateValue(bean.getFdrEdate());
            sql.addIntValue(bean.getFdrEgid());
            sql.addIntValue(bean.getFdrAccessSid());
            //where
            sql.addIntValue(bean.getFdrSid());
            sql.addIntValue(bean.getFdrVersion());

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
     * <p>親ディレクトリSIDを更新する。
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateParentSid(FileDirectoryModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" set ");
            sql.addSql("   FDR_PARENT_SID=?,");
            sql.addSql("   FDR_EUID=?,");
            sql.addSql("   FDR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFdrParentSid());
            sql.addIntValue(bean.getFdrEuid());
            sql.addDateValue(bean.getFdrEdate());
            //where
            sql.addIntValue(bean.getFdrSid());

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
     * <p>親ディレクトリSIDを更新する。
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateParentSidByGp(FileDirectoryModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" set ");
            sql.addSql("   FDR_PARENT_SID=?,");
            sql.addSql("   FDR_EUID=?,");
            sql.addSql("   FDR_EGID=?,");
            sql.addSql("   FDR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFdrParentSid());
            sql.addIntValue(bean.getFdrEuid());
            sql.addIntValue(bean.getFdrEgid());
            sql.addDateValue(bean.getFdrEdate());
            //where
            sql.addIntValue(bean.getFdrSid());

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
     * <p>ディレクトリ階層と親ディレクトリSIDを更新する。
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateParLv(FileDirectoryModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" set ");
            sql.addSql("   FDR_LEVEL=?,");
            sql.addSql("   FDR_PARENT_SID=?,");
            sql.addSql("   FDR_EUID=?,");
            sql.addSql("   FDR_EGID=?,");
            sql.addSql("   FDR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFdrLevel());
            sql.addIntValue(bean.getFdrParentSid());
            sql.addIntValue(bean.getFdrEuid());
            sql.addIntValue(bean.getFdrEgid());
            sql.addDateValue(bean.getFdrEdate());
            //where
            sql.addIntValue(bean.getFdrSid());

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
     * <p>ディレクトリ階層を更新する。
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateLevel(FileDirectoryModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" set ");
            sql.addSql("   FDR_LEVEL=?,");
            sql.addSql("   FDR_EUID=?,");
            sql.addSql("   FDR_EGID=?,");
            sql.addSql("   FDR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFdrLevel());
            sql.addIntValue(bean.getFdrEuid());
            sql.addIntValue(bean.getFdrEgid());
            sql.addDateValue(bean.getFdrEdate());
            //where
            sql.addIntValue(bean.getFdrSid());

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
     * <p>キャビネット用フォルダ(ROOT)を更新します。
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @param noNameFlg true:名前の更新無し false:通常
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateRootFolder(FileDirectoryModel bean, boolean noNameFlg) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" set ");
            sql.addSql("   FDR_VER_KBN=?,");
            if (!noNameFlg) {
                sql.addSql("   FDR_NAME=?,");
            }
            sql.addSql("   FDR_BIKO=?,");
            sql.addSql("   FDR_EUID=?,");
            sql.addSql("   FDR_EGID=?,");
            sql.addSql("   FDR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID=?");
            sql.addSql(" and");
            sql.addSql("   FDR_PARENT_SID=?");
            sql.addSql(" and");
            sql.addSql("   FDR_KBN=?");
            sql.addSql(" and");
            sql.addSql("   FDR_JTKBN=?");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(bean.getFdrVerKbn());
            if (!noNameFlg) {
                sql.addStrValue(bean.getFdrName());
            }
            sql.addStrValue(bean.getFdrBiko());
            sql.addIntValue(bean.getFdrEuid());
            sql.addIntValue(bean.getFdrEgid());
            sql.addDateValue(bean.getFdrEdate());
            //where
            sql.addIntValue(bean.getFcbSid());
            sql.addIntValue(bean.getFdrParentSid());
            sql.addIntValue(bean.getFdrKbn());
            sql.addIntValue(bean.getFdrJtkbn());

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
     * <p>フォルダ情報を更新する。
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateFolder(FileDirectoryModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" set ");
//            sql.addSql("   FDR_LEVEL=?,");
            sql.addSql("   FDR_NAME=?,");
            sql.addSql("   FDR_BIKO=?,");
            sql.addSql("   FDR_EUID=?,");
            sql.addSql("   FDR_EGID=?,");
            sql.addSql("   FDR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   FDR_KBN=?");

            pstmt = con.prepareStatement(sql.toSqlString());
//            sql.addValue(bean.getFdrLevel());
            sql.addStrValue(bean.getFdrName());
            sql.addStrValue(bean.getFdrBiko());
            sql.addIntValue(bean.getFdrEuid());
            sql.addIntValue(bean.getFdrEgid());
            sql.addDateValue(bean.getFdrEdate());
            //where
            sql.addIntValue(bean.getFdrSid());
            sql.addIntValue(GSConstFile.DIRECTORY_FOLDER);

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
     * <p>フォルダ情報を更新する。
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateJtkbn(FileDirectoryModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" set ");
            sql.addSql("   FDR_JTKBN=?,");
            sql.addSql("   FDR_EUID=?,");
            sql.addSql("   FDR_EGID=?,");
            sql.addSql("   FDR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFdrJtkbn());
            sql.addIntValue(bean.getFdrEuid());
            sql.addIntValue(bean.getFdrEgid());
            sql.addDateValue(bean.getFdrEdate());
            //where
            sql.addIntValue(bean.getFdrSid());

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
     * <p>指定したバージョン以下のディレクトリ状態区分を更新する。
     * @param bean FILE_DIRECTORY Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateJtkbnOldVersion(FileDirectoryModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" set ");
            sql.addSql("   FDR_JTKBN=?,");
            sql.addSql("   FDR_EUID=?,");
            sql.addSql("   FDR_EGID=?,");
            sql.addSql("   FDR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql("   and");
            sql.addSql("   FDR_VERSION < ?");
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFdrJtkbn());
            sql.addIntValue(bean.getFdrEuid());
            sql.addIntValue(bean.getFdrEgid());
            sql.addDateValue(bean.getFdrEdate());
            //where
            sql.addIntValue(bean.getFdrSid());
            sql.addIntValue(bean.getFdrVersion());

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
     *
     * <br>[機  能] 直近のアクセスSIDを取得する
     * <br>[解  説]
     * <br>[備  考] 指定ディレクトリのアクセスSIDが適切であることを前提とする。
     * @param fdrSid 指定ディレクトリ
     * @return アクセスSID
     * @throws SQLException SQL実行時例外
     */
    private int __getParrentAccessSid(int fdrSid) throws SQLException {
        int dirSid = fdrSid;
        PreparedStatement pstmt = null;
        int ret = 0;
        Connection con = null;
        con = getCon();
        try {
            ResultSet rs = null;
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("    select distinct ");
            sql.addSql("      FDR_SID, ");
            sql.addSql("      FDR_PARENT_SID, ");
            sql.addSql("      FDR_LEVEL, ");
            sql.addSql("      coalesce(( ");
            sql.addSql("        select distinct");
            sql.addSql("          A.FDR_SID");
            sql.addSql("        from");
            sql.addSql("          FILE_DACCESS_CONF A");
            sql.addSql("        where A.FDR_SID = D.FDR_SID ");
            sql.addSql("      ), 0) as FDR_ACCESS_SID ");
            sql.addSql("    from ");
            sql.addSql("      FILE_DIRECTORY D ");
            sql.addSql("    where FDR_SID = ? ");
            sql.addSql("      and FDR_LEVEL <= ? ");
            pstmt = con.prepareStatement(sql.toSqlString());
            for (int i = GSConstFile.MAX_LEVEL; i >= 0; i--) {
                if (dirSid == 0) {
                    break;
                }
                sql.addIntValue(dirSid);
                sql.addIntValue(i);
                log__.info(sql.toLogString());
                sql.setParameter(pstmt);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    dirSid = rs.getInt("FDR_PARENT_SID");
                    ret = rs.getInt("FDR_ACCESS_SID");
                    i = rs.getInt("FDR_LEVEL");
                    if (ret != 0) {
                        break;
                    }
                } else {
                    return 0;
                }
                sql.clearValue();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }
    /**
     * <p>指定したディレクトリSID以下のサブディレクトリ・ファイルのアクセス設定SIDを更新する。
     * @param fdrSid ディレクトリSID
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    private int __updateAccessSid(int fdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        PreparedStatement pstmtUpd = null;
        int count = 0;
        Connection con = null;
        con = getCon();
        try {
            ResultSet rs = null;

            /*再利用するセレクトステートメント*/
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("    select distinct ");
            sql.addSql("      FDR_SID, ");
            sql.addSql("      ( ");
            sql.addSql("        select");
            sql.addSql("          count(distinct A.FDR_SID)");
            sql.addSql("        from FILE_DACCESS_CONF A");
            sql.addSql("        where A.FDR_SID = D.FDR_SID ");
            sql.addSql("      ) as ACCESS ");
            sql.addSql("    from ");
            sql.addSql("      FILE_DIRECTORY D ");
            sql.addSql("    where FDR_PARENT_SID = ? ");
            pstmt = con.prepareStatement(sql.toSqlString());

            /*再利用するアップデートステートメント*/
            SqlBuffer sqlUpd = new SqlBuffer();
            sqlUpd.addSql("    update ");
            sqlUpd.addSql("      FILE_DIRECTORY ");
            sqlUpd.addSql("    set FDR_ACCESS_SID = ?");
            sqlUpd.addSql("    where FDR_SID = ? ");
            pstmtUpd = con.prepareStatement(sqlUpd.toSqlString());

            int dirSid = fdrSid;
            List<Integer[]> prevList = new ArrayList<Integer[]>();
            int accessSid = 0;
            Integer[] first = new Integer[2];
            if (dirSid != 0) {
                //初回指定ディレクトリの更新
                accessSid = __getParrentAccessSid(dirSid);
                sqlUpd.addIntValue(accessSid);
                sqlUpd.addIntValue(dirSid);
                log__.info(sqlUpd.toLogString());
                sqlUpd.setParameter(pstmtUpd);
                count += pstmtUpd.executeUpdate();
                sqlUpd.clearValue();
            }
            first[0] = dirSid;
            first[1] = accessSid;
            prevList.add(first);
            for (int i = 0; i <= GSConstFile.MAX_LEVEL; i++) {
                List<Integer[]> updList = new ArrayList<Integer[]>();
                //対象サブディレクトリを階層ごとに取得
                for (Integer[] prev : prevList) {
                    sql.addIntValue(prev[0]);
                    log__.info(sql.toLogString());
                    sql.setParameter(pstmt);
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Integer[] upd = new Integer[2];
                        upd[0] = rs.getInt("FDR_SID");
                        //個別アクセス設定がない場合は親のアクセスSIDを使用する
                        if (rs.getInt("ACCESS") > 0) {
                            upd[1] = upd[0];
                        } else {
                            upd[1] = prev[1];
                        }
                        updList.add(upd);
                    }
                    sql.clearValue();
                }
                //対象サブディレクトリを更新
                for (Integer[] upd : updList) {
                    sqlUpd.addIntValue(upd[1]);
                    sqlUpd.addIntValue(upd[0]);
                    log__.info(sqlUpd.toLogString());
                    sqlUpd.setParameter(pstmtUpd);
                    count += pstmtUpd.executeUpdate();
                    sqlUpd.clearValue();
                }
                prevList = updList;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmtUpd);
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }
    /**
     * <p>指定したディレクトリSID以下のサブディレクトリ・ファイルのアクセス設定SIDを更新する。
     * @param fdrSid ディレクトリSID
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateAccessSid(int fdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            boolean h2db = (DBUtilFactory.getInstance().getDbType() == GSConst.DBTYPE_H2DB);

            if (h2db) {
                return __updateAccessSid(fdrSid);
            } else {
                //SQL文
                SqlBuffer sql = new SqlBuffer();
                sql.addSql(" update");
                sql.addSql("   FILE_DIRECTORY");
                sql.addSql(" set ");
                sql.addSql("   FDR_ACCESS_SID = DIRECTORY.ACCESS_SID");
                sql.addSql(" from ");
                // sub-5 ↓ FILE_DACCESS_CONF が存在する直近のディレクトリSIDを取り出す ---------------*
                sql.addSql("   (select distinct");
                sql.addSql("      DIRECTORY.FDR_SID,");
                sql.addSql("      DIRECTORY.ACCESS_SID");
                sql.addSql("    from");
                // sub-4 ↓ FILE_DACCESS_CONF が存在する直近のディレクトリに順位を付加 ----------------*
                sql.addSql("      (select");
                sql.addSql("         DIRECTORY.FDR_SID,");
                sql.addSql("         coalesce(DACCESS.FDR_SID, ?) ACCESS_SID,");
                sql.addSql("         rank() over (partition by DIRECTORY.FDR_SID");
                sql.addSql("           order by case");
                sql.addSql("                    when DACCESS.FDR_SID is null then 99999");
                sql.addSql("                    else rn end) RK");
                sql.addSql("       from");
                sql.addIntValue(GSConstFile.DIRECTORY_ROOT);
                // sub-3 ↓ sub-1 で取り出した下層配列に行番号を付加して縦行に変換 --------------------*
                sql.addSql("         (select");
                sql.addSql("            DIRECTORY.FDR_SID,");
                sql.addSql("            DIRECTORY.PATH,");
                sql.addSql("            generate_subscripts(DIRECTORY.PATH, 1) rn");
                sql.addSql("          from");
                // sub-1 ↓ 上位～下位のディレクトリ情報を取得するための階層問い合わせ -----------------*
                sql.addSql("            (with recursive rec(FDR_SID, FDR_PARENT_SID, PATH) as (");
                sql.addSql("             select A.FDR_SID, A.FDR_PARENT_SID, array[A.FDR_SID]");
                sql.addSql("               from FILE_DIRECTORY A");
                sql.addSql("              where A.FDR_PARENT_SID = ?");
                sql.addIntValue(GSConstFile.DIRECTORY_ROOT);
                sql.addSql("              union all");
                sql.addSql("             select B.FDR_SID, B.FDR_PARENT_SID, B.FDR_SID || A.PATH");
                sql.addSql("               from rec A");
                sql.addSql("               join FILE_DIRECTORY B on A.FDR_SID = B.FDR_PARENT_SID)");
                sql.addSql("             select distinct rec.FDR_SID,PATH");
                sql.addSql("               from rec");
                if (fdrSid != -1) {
                    sql.addSql("              where ? = any(PATH)");
                    sql.addIntValue(fdrSid);
                }
                // sub-1 ↑ -----------------------------------------------------------------------*
                sql.addSql("            ) DIRECTORY");
                // sub-3 ↑ -----------------------------------------------------------------------*
                sql.addSql("         ) DIRECTORY");
                sql.addSql("       left join");
                sql.addSql("         (select distinct FDR_SID from FILE_DACCESS_CONF) DACCESS");
                sql.addSql("         on DIRECTORY.PATH[rn] = DACCESS.FDR_SID");
                sql.addSql("      ) DIRECTORY");
                // sub-4 ↑ -----------------------------------------------------------------------*
                sql.addSql("    where");
                sql.addSql("      DIRECTORY.RK = 1");
                sql.addSql("   ) DIRECTORY");
                // sub-5 ↑ ---------------------------------------------------------------- ------*
                sql.addSql(" where");
                sql.addSql("   FILE_DIRECTORY.FDR_SID = DIRECTORY.FDR_SID");

                pstmt = con.prepareStatement(sql.toSqlString());
                log__.info(sql.toLogString());
                sql.setParameter(pstmt);
                count = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <p>Select FILE_DIRECTORY All Data
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public List<FileDirectoryModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
            sql.addSql(" from ");
            sql.addSql("   FILE_DIRECTORY");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDirectoryFromRs(rs));
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
     * <p>Select FILE_DIRECTORY All Data
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public List<FileDirectoryModel> selectLimit(
            int offset, int limit) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_ACCESS_SID");
            sql.addSql(" from ");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" order by ");
            sql.addSql("   FDR_SID asc,");
            sql.addSql("   FDR_VERSION asc");

            sql.setPagingValue(offset, limit);

            pstmt = con.prepareStatement(sql.toSqlString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDirectoryFromRs(rs));
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
     * <p>全件数を取得する
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public int count() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(*) as CNT");
            sql.addSql(" from ");
            sql.addSql("   FILE_DIRECTORY");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("CNT");
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
     * <p>指定したディレクトリのバージョンの情報を取得する。
     * @param fdrSid FDR_SID
     * @param fdrVersion FDR_VERSION フォルダの場合バージョン管理しないため -1を指定する
     * @return FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public FileDirectoryModel select(int fdrSid, int fdrVersion) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        FileDirectoryModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addIntValue(fdrSid);
            if (fdrVersion != -1) {
                sql.addSql(" and");
                sql.addSql("   FDR_VERSION=?");
                sql.addIntValue(fdrVersion);
            }


            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getFileDirectoryFromRs(rs);
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
     * <p>指定したキャビネットSIDを持つディレクトリを取得する。
     * @param cabSid FCB_SID
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileDirectoryModel> getDirectory(int cabSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(cabSid);
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDirectoryFromRs(rs));
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
     * <p>指定したディレクトリSIDを親に持つディレクトリを取得する。
     * @param fdrSid FDR_SID
     * @param jtkbn 削除区分 0:通常 9:削除済み -1:すべて
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public List<Integer> getChildDirectorySid(int fdrSid, int jtkbn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FDR_SID");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FDR_PARENT_SID=?");
            sql.addIntValue(fdrSid);
            if (jtkbn >= GSConstFile.JTKBN_NORMAL) {
                sql.addSql(" and");
                sql.addSql("   FDR_JTKBN=?");
                sql.addIntValue(jtkbn);
            }
            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt("FDR_SID"));
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
     * <p>指定したディレクトリSIDを親に持つディレクトリを取得する。
     * @param fdrSid FDR_SID
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public List<FileDirectoryModel> getChildDirectory(int fdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FDR_PARENT_SID=?");
            sql.addSql(" and");
            sql.addSql("   FDR_JTKBN=?");


            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);
            sql.addIntValue(GSConstFile.JTKBN_NORMAL);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDirectoryFromRs(rs));
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
     * <p>指定したディレクトリの最新バージョンの情報を取得する。
     * @param fdrSid FDR_SID
     * @return FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public FileDirectoryModel getNewDirectory(int fdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        FileDirectoryModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FILE_DIRECTORY.FDR_SID as FDR_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID as FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION as FDR_VERSION,");
            sql.addSql("   FILE_DIRECTORY.FCB_SID as FCB_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN as FDR_KBN,");
            sql.addSql("   FILE_DIRECTORY.FDR_VER_KBN as FDR_VER_KBN,");
            sql.addSql("   FILE_DIRECTORY.FDR_LEVEL as FDR_LEVEL,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME as FDR_NAME,");
            sql.addSql("   FILE_DIRECTORY.FDR_BIKO as FDR_BIKO,");
            sql.addSql("   FILE_DIRECTORY.FDR_JTKBN as FDR_JTKBN,");
            sql.addSql("   FILE_DIRECTORY.FDR_AUID as FDR_AUID,");
            sql.addSql("   FILE_DIRECTORY.FDR_ADATE as FDR_ADATE,");
            sql.addSql("   FILE_DIRECTORY.FDR_EUID as FDR_EUID,");
            sql.addSql("   FILE_DIRECTORY.FDR_EDATE as FDR_EDATE,");
            sql.addSql("   FILE_DIRECTORY.FDR_EGID as FDR_EGID,");
            sql.addSql("   FILE_DIRECTORY.FDR_ACCESS_SID as FDR_ACCESS_SID");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY,");
            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION from FILE_DIRECTORY");
            sql.addSql("    where FDR_SID = ? group by FDR_SID) DIR_MAXVERSION");
            sql.addSql(" where");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
            sql.addSql(" order by");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME");

            sql.addIntValue(fdrSid);
            sql.addIntValue(fdrSid);

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getFileDirectoryFromRs(rs);
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
     * <p>指定したディレクトリの最新バージョンの情報一覧を取得する。
     * @param fdrSids FDR_SIDリスト
     * @return FILE_DIRECTORYModelリスト
     * @throws SQLException SQL実行例外
     */
    public List<FileDirectoryModel> getNewDirectoryList(String[] fdrSids) throws SQLException {

        if (fdrSids == null || fdrSids.length < 1) {
            return null;
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FILE_DIRECTORY.FDR_SID as FDR_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID as FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION as FDR_VERSION,");
            sql.addSql("   FILE_DIRECTORY.FCB_SID as FCB_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN as FDR_KBN,");
            sql.addSql("   FILE_DIRECTORY.FDR_VER_KBN as FDR_VER_KBN,");
            sql.addSql("   FILE_DIRECTORY.FDR_LEVEL as FDR_LEVEL,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME as FDR_NAME,");
            sql.addSql("   FILE_DIRECTORY.FDR_BIKO as FDR_BIKO,");
            sql.addSql("   FILE_DIRECTORY.FDR_JTKBN as FDR_JTKBN,");
            sql.addSql("   FILE_DIRECTORY.FDR_AUID as FDR_AUID,");
            sql.addSql("   FILE_DIRECTORY.FDR_ADATE as FDR_ADATE,");
            sql.addSql("   FILE_DIRECTORY.FDR_EUID as FDR_EUID,");
            sql.addSql("   FILE_DIRECTORY.FDR_EDATE as FDR_EDATE,");
            sql.addSql("   FILE_FILE_BIN.BIN_SID as BIN_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_EGID as FDR_EGID,");
            sql.addSql("   FILE_DIRECTORY.FDR_ACCESS_SID as FDR_ACCESS_SID");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY left join FILE_FILE_BIN");
            sql.addSql("   on FILE_DIRECTORY.FDR_SID = FILE_FILE_BIN.FDR_SID");
            sql.addSql("   and FILE_DIRECTORY.FDR_VERSION = FILE_FILE_BIN.FDR_VERSION,");

            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION from FILE_DIRECTORY");
            if (fdrSids != null || fdrSids.length > 0) {
                sql.addSql("    where FDR_SID in (");
                boolean exists = false;
                for (String fdrSid : fdrSids) {
                    if (exists) {
                        sql.addSql("      ,?");
                    } else {
                        sql.addSql("      ?");
                        exists = true;
                    }
                    sql.addIntValue(Integer.parseInt(fdrSid));
                }
                sql.addSql("    )");
            }
            sql.addSql("    group by FDR_SID) DIR_MAXVERSION");

            sql.addSql(" where");
            sql.addSql(" (");

            int i = 0;
            for (String fdrSid : fdrSids) {
                if (i > 0) {
                    sql.addSql("  or");
                }
                sql.addSql("   FILE_DIRECTORY.FDR_SID = ?");
                sql.addIntValue(Integer.parseInt(fdrSid));
                i++;
            }
            sql.addSql(" )");

            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
            sql.addSql(" order by");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            FileDirectoryModel model = null;
            while (rs.next()) {
                model = __getFileDirectoryFromRs(rs);
                model.setBinSid(rs.getLong("BIN_SID"));
                ret.add(model);
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
     * <p>親ディレクトリを指定してディレクトリの最新バージョンの情報の一覧を取得する。
     * @param fdrSid FDR_SID
     * @param usrSid ユーザSID
     * @param sort ソートキー
     * @param order オーダーキー
     * @param reqMdl RequestModel
     * @return FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileDirectoryDspModel> getNewLowDirectory(
            int fdrSid, int usrSid, int sort, int order, RequestModel reqMdl)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileDirectoryDspModel> ret = new ArrayList<FileDirectoryDspModel>();
        con = getCon();

        try {
            String sortStr = "";
            if (order == GSConst.ORDER_KEY_ASC) {
                sortStr = "asc";
            } else if (order == GSConst.ORDER_KEY_DESC) {
                sortStr = "desc";
            }
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   DIR.FDR_SID as FDR_SID,");
            sql.addSql("   DIR.FDR_PARENT_SID as FDR_PARENT_SID,");
            sql.addSql("   DIR.FDR_VERSION as FDR_VERSION,");
            sql.addSql("   DIR.FCB_SID as FCB_SID,");
            sql.addSql("   DIR.FDR_KBN as FDR_KBN,");
            sql.addSql("   DIR.FDR_VER_KBN as FDR_VER_KBN,");
            sql.addSql("   DIR.FDR_LEVEL as FDR_LEVEL,");
            sql.addSql("   DIR.FDR_NAME as FDR_NAME,");
            sql.addSql("   DIR.FDR_BIKO as FDR_BIKO,");
            sql.addSql("   DIR.FDR_JTKBN as FDR_JTKBN,");
            sql.addSql("   DIR.FDR_AUID as FDR_AUID,");
            sql.addSql("   DIR.FDR_ADATE as FDR_ADATE,");
            sql.addSql("   DIR.FDR_EUID as FDR_EUID,");
            sql.addSql("   DIR.FDR_EDATE as FDR_EDATE,");
            sql.addSql("   DIR.FDR_EGID as FDR_EGID,");
            sql.addSql("   USRM.USI_SEI as USI_SEI,");
            sql.addSql("   USRM.USI_MEI as USI_MEI,");
            sql.addSql("   USR.USR_JKBN as USR_JKBN,");
            sql.addSql("   CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("   CMN_GROUPM.GRP_JKBN as GRP_JKBN,");
            sql.addSql("   COALESCE(BIN.BIN_SID, -1) as BIN_SID,");
            sql.addSql("   COALESCE(BIN.FFL_EXT, '') as FFL_EXT,");
            sql.addSql("   COALESCE(BIN.FFL_FILE_SIZE, -1) as FFL_FILE_SIZE,");
            sql.addSql("   COALESCE(BIN.FFL_LOCK_KBN, '0') as FFL_LOCK_KBN,");
            sql.addSql("   COALESCE(BIN.FFL_LOCK_USER, -1) as FFL_LOCK_USER,");
            sql.addSql("   BIN.FFL_LOCK_DATE as FFL_LOCK_DATE,");
            sql.addSql("  (case   when   EXISTS(");
            sql.addSql("    select USR_SID from FILE_CALL_CONF");
            sql.addSql("   where FILE_CALL_CONF.USR_SID= ?");
            sql.addSql("   and FILE_CALL_CONF.FDR_SID = DIR.FDR_SID) then 1");
            sql.addSql("   else 0 end) as CALLKBN,");
            sql.addSql("  (case");
            sql.addSql("    when COALESCE(BIN.FFL_LOCK_KBN, '0') = 0 then ''");
            sql.addSql("    else (select ");
            sql.addSql("           LUSRM.USI_SEI || ' ' || LUSRM.USI_MEI as LOCK_UNAME");
            sql.addSql("         from");
            sql.addSql("           CMN_USRM_INF LUSRM");
            sql.addSql("         where");
            sql.addSql("            LUSRM.USR_SID = BIN.FFL_LOCK_USER");
            sql.addSql("         )");
            sql.addSql("  end) as LOCK_UNAME,");
            sql.addSql("  1 as ACKBN");

            sql.addSql(" from");
            sql.addSql("   CMN_USRM USR,");
            sql.addSql("   CMN_USRM_INF USRM,");
            sql.addSql("   FILE_DIRECTORY DIR left join FILE_FILE_BIN BIN");
            sql.addSql("   on DIR.FDR_SID = BIN.FDR_SID");
            sql.addSql("   and DIR.FDR_VERSION = BIN.FDR_VERSION");
            sql.addSql("     left join CMN_GROUPM");
            sql.addSql("       on DIR.FDR_EGID = CMN_GROUPM.GRP_SID,");

            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION");
            sql.addSql("    from FILE_DIRECTORY");
            sql.addSql("    where FDR_PARENT_SID = ? group by FDR_SID) DIR_MAXVERSION");
            sql.addSql(" where");
            sql.addSql("   DIR.FDR_PARENT_SID = ?");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_EUID = USR.USR_SID");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_EUID = USRM.USR_SID");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_JTKBN = 0");

            sql.addSql(" order by");
            sql.addSql("   DIR.FDR_KBN , ");
            switch (sort) {
            case GSConstFile.SORT_NAME:
                sql.addSql("   FDR_NAME " + sortStr);
                break;
            case GSConstFile.SORT_SIZE:
                sql.addSql("   COALESCE(BIN.FFL_FILE_SIZE, -1) " + sortStr);
                break;
            case GSConstFile.SORT_CALL:
                sql.addSql("   CALLKBN " + sortStr);
                break;
            case GSConstFile.SORT_EDATE:
                sql.addSql("   FDR_EDATE " + sortStr);
                break;
            case GSConstFile.SORT_EUSR:
                sql.addSql("   USRM.USI_SEI || '  ' || USRM.USI_MEI " + sortStr);
                break;
            default:
                break;
            }
            sql.addIntValue(usrSid);
            sql.addIntValue(fdrSid);
            sql.addIntValue(fdrSid);
            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDirectoryDspFromRs(rs, reqMdl));
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
     * <p>親ディレクトリを指定してディレクトリの最新バージョンの情報の一覧を取得する。
     * @param fdrSid FDR_SID
     * @param usrSid ユーザSID
     * @param sort ソートキー
     * @param order オーダーキー
     * @param reqMdl RequestModel
     * @return FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileDirectoryDspModel> getNewLowDirectoryAccessLimit(
            int fdrSid, int usrSid, int sort, int order, RequestModel reqMdl)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileDirectoryDspModel> ret = new ArrayList<FileDirectoryDspModel>();
        con = getCon();

        try {
            String sortStr = "";
            if (order == GSConst.ORDER_KEY_ASC) {
                sortStr = "asc";
            } else if (order == GSConst.ORDER_KEY_DESC) {
                sortStr = "desc";
            }
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   DIR.FDR_SID as FDR_SID,");
            sql.addSql("   DIR.FDR_PARENT_SID as FDR_PARENT_SID,");
            sql.addSql("   DIR.FDR_VERSION as FDR_VERSION,");
            sql.addSql("   DIR.FCB_SID as FCB_SID,");
            sql.addSql("   DIR.FDR_KBN as FDR_KBN,");
            sql.addSql("   DIR.FDR_VER_KBN as FDR_VER_KBN,");
            sql.addSql("   DIR.FDR_LEVEL as FDR_LEVEL,");
            sql.addSql("   DIR.FDR_NAME as FDR_NAME,");
            sql.addSql("   DIR.FDR_BIKO as FDR_BIKO,");
            sql.addSql("   DIR.FDR_JTKBN as FDR_JTKBN,");
            sql.addSql("   DIR.FDR_AUID as FDR_AUID,");
            sql.addSql("   DIR.FDR_ADATE as FDR_ADATE,");
            sql.addSql("   DIR.FDR_EUID as FDR_EUID,");
            sql.addSql("   DIR.FDR_EDATE as FDR_EDATE,");
            sql.addSql("   DIR.FDR_EGID as FDR_EGID,");
            sql.addSql("   USRM.USI_SEI as USI_SEI,");
            sql.addSql("   USRM.USI_MEI as USI_MEI,");
            sql.addSql("   USR.USR_JKBN as USR_JKBN,");
            sql.addSql("   CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("   CMN_GROUPM.GRP_JKBN as GRP_JKBN,");
            sql.addSql("   COALESCE(BIN.BIN_SID, -1) as BIN_SID,");
            sql.addSql("   COALESCE(BIN.FFL_EXT, '') as FFL_EXT,");
            sql.addSql("   COALESCE(BIN.FFL_FILE_SIZE, -1) as FFL_FILE_SIZE,");
            sql.addSql("   COALESCE(BIN.FFL_LOCK_KBN, '0') as FFL_LOCK_KBN,");
            sql.addSql("   COALESCE(BIN.FFL_LOCK_USER, -1) as FFL_LOCK_USER,");
            sql.addSql("   BIN.FFL_LOCK_DATE as FFL_LOCK_DATE,");
            sql.addSql("  (case   when   EXISTS(");
            sql.addSql("    select USR_SID from FILE_CALL_CONF");
            sql.addSql("   where FILE_CALL_CONF.USR_SID= ?");
            sql.addSql("   and FILE_CALL_CONF.FDR_SID = DIR.FDR_SID) then 1");
            sql.addSql("   else 0 end) as CALLKBN,");
            sql.addSql("  (case");
            sql.addSql("    when COALESCE(BIN.FFL_LOCK_KBN, '0') = 0 then ''");
            sql.addSql("    else (select ");
            sql.addSql("           LUSRM.USI_SEI || ' ' || LUSRM.USI_MEI as LOCK_UNAME");
            sql.addSql("         from");
            sql.addSql("           CMN_USRM_INF LUSRM");
            sql.addSql("         where");
            sql.addSql("            LUSRM.USR_SID = BIN.FFL_LOCK_USER");
            sql.addSql("         )");
            sql.addSql("  end) as LOCK_UNAME,");
            sql.addSql("   COALESCE(DACCESS.FDA_AUTH, ?) as ACKBN");
            sql.addIntValue(usrSid);
            sql.addIntValue(Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));

            sql.addSql(" from");
            sql.addSql("   CMN_USRM USR,");
            sql.addSql("   CMN_USRM_INF USRM,");
            sql.addSql("   FILE_DIRECTORY DIR left join FILE_FILE_BIN BIN");
            sql.addSql("   on DIR.FDR_SID = BIN.FDR_SID");
            sql.addSql("   and DIR.FDR_VERSION = BIN.FDR_VERSION");
            sql.addSql("     left join CMN_GROUPM");
            sql.addSql("       on DIR.FDR_EGID = CMN_GROUPM.GRP_SID,");

            //ディレクトリのアクセス設定で参照ファイルを制限する
            sql.addSql("   ( ");
            sql.addSql("   select");
            sql.addSql("     ? as FDR_SID,");
            sql.addSql("     ? as FDA_AUTH");
            sql.addSql("   union all");
            sql.addSql("   select");
            sql.addSql("     A.FDR_SID,");
            sql.addSql("     max(A.FDA_AUTH) as FDA_AUTH");
            sql.addSql("   from");
            sql.addSql("     FILE_DACCESS_CONF A");
            sql.addSql("   where");
            sql.addSql("     exists (");
            sql.addSql("       select *");
            sql.addSql("       from");
            sql.addSql("         FILE_DIRECTORY D");
            sql.addSql("       where");
            sql.addSql("         D.FDR_ACCESS_SID = A.FDR_SID");
            sql.addSql("       and");
            sql.addSql("         D.FDR_PARENT_SID = ?");
            sql.addSql("       and");
            sql.addSql("         D.FDR_JTKBN = 0)");
            sql.addSql("   and (");
            sql.addSql("     (A.USR_KBN = ? and");
            sql.addSql("      A.USR_SID = ?) or");
            sql.addSql("     (A.USR_KBN = ? and");
            sql.addSql("      exists");
            sql.addSql("      (select *");
            sql.addSql("         from CMN_BELONGM B");
            sql.addSql("        where B.GRP_SID = A.USR_SID");
            sql.addSql("          and B.USR_SID = ?");
            sql.addSql("      )))");
            sql.addSql("   group by");
            sql.addSql("     A.FDR_SID");
            sql.addSql("   ) DACCESS,");
            sql.addIntValue(GSConstFile.DIRECTORY_ROOT);
            sql.addIntValue(Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
            sql.addIntValue(fdrSid);
            sql.addIntValue(GSConstFile.USER_KBN_USER);
            sql.addIntValue(usrSid);
            sql.addIntValue(GSConstFile.USER_KBN_GROUP);
            sql.addIntValue(usrSid);

            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION");
            sql.addSql("    from FILE_DIRECTORY");
            sql.addSql("    where FDR_PARENT_SID = ? group by FDR_SID) DIR_MAXVERSION");
            sql.addIntValue(fdrSid);

            sql.addSql(" where");
            sql.addSql("   DIR.FDR_PARENT_SID = ?");
            sql.addIntValue(fdrSid);
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_EUID = USR.USR_SID");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_EUID = USRM.USR_SID");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_JTKBN = 0");
            sql.addSql(" and");
            sql.addSql("   DIR.FDR_ACCESS_SID = DACCESS.FDR_SID");

            sql.addSql(" order by");
            sql.addSql("   DIR.FDR_KBN , ");
            switch (sort) {
            case GSConstFile.SORT_NAME:
                sql.addSql("   FDR_NAME " + sortStr);
                break;
            case GSConstFile.SORT_SIZE:
                sql.addSql("   COALESCE(BIN.FFL_FILE_SIZE, -1) " + sortStr);
                break;
            case GSConstFile.SORT_CALL:
                sql.addSql("   CALLKBN " + sortStr);
                break;
            case GSConstFile.SORT_EDATE:
                sql.addSql("   FDR_EDATE " + sortStr);
                break;
            case GSConstFile.SORT_EUSR:
                sql.addSql("   USRM.USI_SEI || '  ' || USRM.USI_MEI " + sortStr);
                break;
            default:
                break;
            }
            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDirectoryDspFromRs(rs, reqMdl));
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
     * <p>親ディレクトリを指定してディレクトリ情報を取得する。
     * @param fdrParentSid 親ディレクトリSID
     * @param fdrKbn ディレクトリ区分 -1：指定しない
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public List<FileDirectoryModel> getLowDirectory(int fdrParentSid, int fdrKbn)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
            sql.addSql(" from ");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FDR_PARENT_SID = ?");
            sql.addIntValue(fdrParentSid);
            if (fdrKbn != -1) {
                sql.addSql(" and ");
                sql.addSql("   FDR_KBN = ?");
                sql.addIntValue(fdrKbn);
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDirectoryFromRs(rs));
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
     * <br>[機  能] 指定された親を持つディレクトリを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param listOfParent 親ディレクトリリスト
     * @param listOfDir ディレクトリ(フォルダ)リスト
     * @param listOfFile ディレクトリ(ファイル)リスト
     * @return ret 取得したディレクトリモデルリスト
     * @throws SQLException 例外
     */
    public ArrayList<FileDirectoryModel> setChildDirList(
            ArrayList<FileDirectoryModel> listOfParent,
            ArrayList<FileDirectoryModel> listOfDir,
            ArrayList<FileDirectoryModel> listOfFile) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();
        ArrayList<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        FileDirectoryModel mdl = null;
        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FDR_PARENT_SID in (");

            for (int i = 0; i < listOfParent.size(); i++) {

                FileDirectoryModel bean = (FileDirectoryModel) listOfParent.get(i);
                sql.addSql("?");
                sql.addIntValue(bean.getFdrSid());

                if (i != listOfParent.size() - 1) {
                    sql.addSql(", ");
                }
            }
            sql.addSql("   )");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                mdl = __getFileDirectoryFromRs(rs);
                if (mdl.getFdrKbn() == GSConstFile.DIRECTORY_FOLDER) {
                    listOfDir.add(mdl);
                } else if (mdl.getFdrKbn() == GSConstFile.DIRECTORY_FILE) {
                    listOfFile.add(mdl);
                }

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
     * <br>[機  能] ツリーのキーを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param fcbSid キャビネットSID
     * @param lv 階層LV
     * @return ret ツリーキーリスト
     * @throws SQLException SQL実行時例外
     */
    public String[] getTreeList(int fcbSid, int lv)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String[] ret;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   COALESCE(FILE_DIRECTORY.FDR_SID, -1) as FDR_SID,");
            sql.addSql("   COALESCE(FILE_DIRECTORY.FDR_PARENT_SID, -1) as FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME as FDR_NAME");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY,");
            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION from FILE_DIRECTORY");
            sql.addSql("    where FCB_SID = ? group by FDR_SID) DIR_MAXVERSION");
            sql.addSql(" where");
            sql.addSql("   FILE_DIRECTORY.FCB_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_LEVEL = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");

            sql.addSql(" order by");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fcbSid);
            sql.addIntValue(fcbSid);
            sql.addIntValue(GSConstFile.DIRECTORY_FOLDER);
            sql.addIntValue(lv);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            String sep = GSConst.GSESSION2_ID + GSConst.GSESSION2_ID;
            ArrayList<String> retArray = new ArrayList<String>();
            while (rs.next()) {
                StringBuilder key = new StringBuilder("");
                key.append(String.valueOf(rs.getInt("FDR_SID")));
                key.append(sep);
                key.append(String.valueOf(rs.getInt("FDR_PARENT_SID")));
                key.append(sep);
                key.append(StringUtilHtml.transToHTmlPlusAmparsant(rs.getString("FDR_NAME")));
                retArray.add(key.toString());
                key = null;
            }
            if (retArray.isEmpty()) {
                ret = new String[0];
            } else {
                ret = retArray.toArray(new String[retArray.size()]);
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
     * <br>[機  能] ツリーのキーを取得する
     * <br>[解  説] ディレクトリアクセス設定で制限されているディレクトリのみ取得する
     * <br>[備  考]
     *
     * @param fcbSid キャビネットSID
     * @param lv 階層LV
     * @param authUsrSid アクセス許可ユーザSID
     * @param auth 権限区分
     * @return ret ツリーキーリスト
     * @throws SQLException SQL実行時例外
     */
    public String[] getTreeListAccessLimit(int fcbSid, int lv, int authUsrSid, int auth)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String[] ret;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   COALESCE(FILE_DIRECTORY.FDR_SID, -1) as FDR_SID,");
            sql.addSql("   COALESCE(FILE_DIRECTORY.FDR_PARENT_SID, -1) as FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME as FDR_NAME");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY,");
            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION from FILE_DIRECTORY");
            sql.addSql("    where FCB_SID = ? group by FDR_SID) DIR_MAXVERSION");
            sql.addIntValue(fcbSid);

            sql.addSql(" where");
            sql.addSql("   FILE_DIRECTORY.FCB_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_LEVEL = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
            sql.addIntValue(fcbSid);
            sql.addIntValue(GSConstFile.DIRECTORY_FOLDER);
            sql.addIntValue(lv);
          //ディレクトリのアクセス設定で参照ファイルを制限する
            sql.addSql(" and");
            sql.addSql("   (FILE_DIRECTORY.FDR_ACCESS_SID = ?");
            sql.addSql(" or");
            sql.addSql("   exists ( ");
            sql.addSql("     select");
            sql.addSql("       *");
            sql.addSql("     from");
            sql.addSql("       FILE_DACCESS_CONF A");
            sql.addSql("     where");
            sql.addSql("       FILE_DIRECTORY.FDR_ACCESS_SID = A.FDR_SID");
            sql.addSql("     and (");
            sql.addSql("       (A.USR_KBN = ? and");
            sql.addSql("        A.USR_SID = ?) or");
            sql.addSql("       (A.USR_KBN = ? and");
            sql.addSql("        exists");
            sql.addSql("          (select *");
            sql.addSql("           from");
            sql.addSql("             CMN_BELONGM B");
            sql.addSql("           where");
            sql.addSql("             B.GRP_SID = A.USR_SID");
            sql.addSql("           and");
            sql.addSql("             B.USR_SID = ?");
            sql.addSql("          )))");
            sql.addIntValue(GSConstFile.DIRECTORY_ROOT);
            sql.addIntValue(GSConstFile.USER_KBN_USER);
            sql.addIntValue(authUsrSid);
            sql.addIntValue(GSConstFile.USER_KBN_GROUP);
            sql.addIntValue(authUsrSid);
            if (auth != -1) {
                sql.addSql("     and");
                sql.addSql("        A.FDA_AUTH = ?");
                sql.addIntValue(auth);
            }
            sql.addSql("   ))");

            sql.addSql(" order by");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            String sep = GSConst.GSESSION2_ID + GSConst.GSESSION2_ID;
            ArrayList<String> retArray = new ArrayList<String>();
            while (rs.next()) {
                StringBuilder key = new StringBuilder("");
                key.append(String.valueOf(rs.getInt("FDR_SID")));
                key.append(sep);
                key.append(String.valueOf(rs.getInt("FDR_PARENT_SID")));
                key.append(sep);
                key.append(StringUtilHtml.transToHTmlPlusAmparsant(rs.getString("FDR_NAME")));
                retArray.add(key.toString());
                key = null;
            }
            if (retArray.isEmpty()) {
                ret = new String[0];
            } else {
                ret = retArray.toArray(new String[retArray.size()]);
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
     * <br>[機  能] ツリーのキーを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param fcbSid キャビネットSID
     * @param lv 階層LV
     * @param fdrSidList ディレクトリSIDリスト
     * @return ret ツリーキーリスト
     * @throws SQLException SQL実行時例外
     */
    public String[] getTreeListForMove(int fcbSid, int lv, List<Integer> fdrSidList)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String[] ret;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   COALESCE(FILE_DIRECTORY.FDR_SID, -1) as FDR_SID,");
            sql.addSql("   COALESCE(FILE_DIRECTORY.FDR_PARENT_SID, -1) as FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME as FDR_NAME");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY,");
            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION");
            sql.addSql("    from FILE_DIRECTORY group by FDR_SID) DIR_MAXVERSION");
            sql.addSql(" where");
            sql.addSql("   FILE_DIRECTORY.FCB_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_LEVEL = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");

            sql.addIntValue(fcbSid);
            sql.addIntValue(GSConstFile.DIRECTORY_FOLDER);
            sql.addIntValue(lv);

            if (fdrSidList != null && fdrSidList.size() > 0) {
                sql.addSql(" and");
                sql.addSql("   FILE_DIRECTORY.FDR_SID not in(");

                int i = 0;
                for (Integer fdrSid : fdrSidList) {
                    if (i > 0) {
                        sql.addSql(" ,");
                    }
                    sql.addSql(" ?");
                    sql.addIntValue(fdrSid);
                    i++;
                }
                sql.addSql(" )");
            }
            sql.addSql(" order by");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            String sep = GSConst.GSESSION2_ID + GSConst.GSESSION2_ID;
            ArrayList<String> retArray = new ArrayList<String>();
            while (rs.next()) {
                String key = "";
                key = String.valueOf(rs.getInt("FDR_SID"));
                key = key + sep;
                key = key + String.valueOf(rs.getInt("FDR_PARENT_SID"));
                key = key + sep;
                key = key + StringUtilHtml.transToHTmlPlusAmparsant(rs.getString("FDR_NAME"));
                key = key + sep;
                key = key + GSConstFile.ACCESS_KBN_WRITE;
                retArray.add(key);
            }
            if (retArray.isEmpty()) {
                ret = new String[0];
            } else {
                ret = retArray.toArray(new String[retArray.size()]);
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
     * <br>[機  能] ツリーのキーを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param fcbSid キャビネットSID
     * @param lv 階層LV
     * @param fdrSidList ディレクトリSIDリスト
     * @param authUsrSid アクセス許可ユーザSID
     * @return ret ツリーキーリスト
     * @throws SQLException SQL実行時例外
     */
    public String[] getTreeListForMoveAccessLimit(int fcbSid, int lv,
                            List<Integer> fdrSidList, int authUsrSid)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String[] ret;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   COALESCE(FILE_DIRECTORY.FDR_SID, -1) as FDR_SID,");
            sql.addSql("   COALESCE(FILE_DIRECTORY.FDR_PARENT_SID, -1) as FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME as FDR_NAME,");
            sql.addSql("   DACCESS.FDA_AUTH");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY,");
            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION from FILE_DIRECTORY");
            sql.addSql("    where FCB_SID = ? group by FDR_SID) DIR_MAXVERSION,");
            sql.addIntValue(fcbSid);

            //ディレクトリのアクセス設定で参照ファイルを制限する
            sql.addSql("   ( ");
            sql.addSql("   select");
            sql.addSql("     ? as FDR_SID,");
            sql.addSql("     ? as FDA_AUTH");
            sql.addSql("   union all");
            sql.addSql("   select");
            sql.addSql("     A.FDR_SID,");
            sql.addSql("     max(A.FDA_AUTH) as FDA_AUTH");
            sql.addSql("   from");
            sql.addSql("     FILE_DACCESS_CONF A");
            sql.addSql("   where");
            sql.addSql("     exists (");
            sql.addSql("       select *");
            sql.addSql("       from");
            sql.addSql("         FILE_DIRECTORY D");
            sql.addSql("       where");
            sql.addSql("         D.FDR_ACCESS_SID = A.FDR_SID");
            sql.addSql("       and");
            sql.addSql("         D.FCB_SID = ?");
            sql.addSql("       and");
            sql.addSql("         D.FDR_KBN = ?");
            sql.addSql("       and");
            sql.addSql("         D.FDR_LEVEL = ?)");
            sql.addSql("   and (");
            sql.addSql("     (A.USR_KBN = ? and");
            sql.addSql("      A.USR_SID = ?) or");
            sql.addSql("     (A.USR_KBN = ? and");
            sql.addSql("      exists");
            sql.addSql("      (select *");
            sql.addSql("         from CMN_BELONGM B");
            sql.addSql("        where B.GRP_SID = A.USR_SID");
            sql.addSql("          and B.USR_SID = ?");
            sql.addSql("      )))");
            sql.addSql("   group by");
            sql.addSql("     A.FDR_SID");
            sql.addSql("   ) DACCESS");
            sql.addIntValue(GSConstFile.DIRECTORY_ROOT);
            sql.addIntValue(Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
            sql.addIntValue(fcbSid);
            sql.addIntValue(GSConstFile.DIRECTORY_FOLDER);
            sql.addIntValue(lv);
            sql.addIntValue(GSConstFile.USER_KBN_USER);
            sql.addIntValue(authUsrSid);
            sql.addIntValue(GSConstFile.USER_KBN_GROUP);
            sql.addIntValue(authUsrSid);

            sql.addSql(" where");
            sql.addSql("   FILE_DIRECTORY.FCB_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_LEVEL = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_ACCESS_SID = DACCESS.FDR_SID");

            sql.addIntValue(fcbSid);
            sql.addIntValue(GSConstFile.DIRECTORY_FOLDER);
            sql.addIntValue(lv);

            if (fdrSidList != null && fdrSidList.size() > 0) {
                sql.addSql(" and");
                sql.addSql("   FILE_DIRECTORY.FDR_SID not in(");

                int i = 0;
                for (Integer fdrSid : fdrSidList) {
                    if (i > 0) {
                        sql.addSql(" ,");
                    }
                    sql.addSql(" ?");
                    sql.addIntValue(fdrSid);
                    i++;
                }
                sql.addSql(" )");
            }

            sql.addSql(" order by");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            String sep = GSConst.GSESSION2_ID + GSConst.GSESSION2_ID;
            ArrayList<String> retArray = new ArrayList<String>();
            while (rs.next()) {
                String key = "";
                key = String.valueOf(rs.getInt("FDR_SID"));
                key = key + sep;
                key = key + String.valueOf(rs.getInt("FDR_PARENT_SID"));
                key = key + sep;
                key = key + StringUtilHtml.transToHTmlPlusAmparsant(rs.getString("FDR_NAME"));
                key = key + sep;
                key = key + String.valueOf(rs.getInt("FDA_AUTH"));
                retArray.add(key);
            }
            if (retArray.isEmpty()) {
                ret = new String[0];
            } else {
                ret = retArray.toArray(new String[retArray.size()]);
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
     * <p>指定したキャビネットのルートディレクトリ情報を取得する。
     * @param fcbSid キャビネットSID
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public FileDirectoryModel getRootDirectory(int fcbSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        FileDirectoryModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
            sql.addSql(" from ");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FDR_PARENT_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FDR_LEVEL = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fcbSid);
            sql.addIntValue(GSConstFile.DIRECTORY_ROOT);
            sql.addIntValue(GSConstFile.DIRECTORY_LEVEL_0);
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret = __getFileDirectoryFromRs(rs);
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
     * <p>指定したキャビネットのディレクトリ情報を取得する。
     * @param fcbSid キャビネットSID
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public List <FileDirectoryModel> getDirectoryList(int fcbSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List <FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        FileDirectoryModel bean = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FILE_DIRECTORY.FDR_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_LEVEL,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME");

            sql.addSql(" from ");
            sql.addSql("   FILE_DIRECTORY,");
            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION");
            sql.addSql("    from FILE_DIRECTORY group by FDR_SID) DIR_MAXVERSION");
            sql.addSql(" where ");
            sql.addSql("   FILE_DIRECTORY.FCB_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_JTKBN = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");

            sql.addSql(" order by ");
            sql.addSql("   FILE_DIRECTORY.FDR_LEVEL,");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fcbSid);
            sql.addIntValue(GSConstFile.JTKBN_NORMAL);
            sql.addIntValue(GSConstFile.DIRECTORY_FOLDER);

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new FileDirectoryModel();
                bean.setFdrSid(rs.getInt("FDR_SID"));
                bean.setFdrParentSid(rs.getInt("FDR_PARENT_SID"));
                bean.setFdrLevel(rs.getInt("FDR_LEVEL"));
                bean.setFdrName(rs.getString("FDR_NAME"));
                ret.add(bean);
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
     * <br>[機  能] 指定されたディレクトリ(複数)を削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param delList 削除対象のディレクトリ情報モデルリスト
     * @throws SQLException 例外
     */
    public void deleteDir(ArrayList<FileDirectoryModel> delList)
        throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where");

            sql.addSql("   FDR_SID in (-1");

            for (int i = 0; i < delList.size(); i++) {

                FileDirectoryModel bean = (FileDirectoryModel) delList.get(i);
                sql.addSql(",?");
                sql.addIntValue(bean.getFdrSid());
            }
            sql.addSql("   )");

            pstmt = con.prepareStatement(sql.toSqlString());

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
     * <p>Delete FILE_DIRECTORY
     * @param fdrSid FDR_SID
     * @param fdrVersion FDR_VERSION
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int delete(int fdrSid, int fdrVersion) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   FDR_VERSION = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);
            sql.addIntValue(fdrVersion);

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
     * <p>指定したディレクトリの全バージョンを削除します。
     * @param fdrSid FDR_SID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int delete(int fdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);

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
     * <p>キャビネットSIDを指定し削除します。
     * @param fcbSid FCB_SID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int deleteBelongCabinet(int fcbSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fcbSid);

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
     * <p>キャビネットSIDを指定し削除します。
     * @param dirSid FCB_SID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int deleteBelongParent(int dirSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FDR_PARENT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(dirSid);

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
     * <br>[機  能] 指定したディレクトリの指定バージョン以前のものを削除する。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param fdrSid ディレクトリSID
     * @param fdrVerKbn バージョン管理区分
     * @throws SQLException 例外
     */
    public void deleteOldVersion(int fdrSid, int fdrVerKbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where");
            sql.addSql("   FDR_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FDR_VERSION < ?");

            sql.addIntValue(fdrSid);
            sql.addIntValue(fdrVerKbn);

            pstmt = con.prepareStatement(sql.toSqlString());

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
     * <p>論理削除されてから指定日数を経過しているディレクトリを取得する。
     * @param date 基準日付
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileDirectoryModel> getDeletedDirectory(UDate date) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FDR_PARENT_SID,");
            sql.addSql("   FDR_KBN,");
            sql.addSql("   FDR_VER_KBN,");
            sql.addSql("   FDR_LEVEL,");
            sql.addSql("   FDR_NAME,");
            sql.addSql("   FDR_BIKO,");
            sql.addSql("   FDR_JTKBN,");
            sql.addSql("   FDR_AUID,");
            sql.addSql("   FDR_ADATE,");
            sql.addSql("   FDR_EUID,");
            sql.addSql("   FDR_EDATE,");
            sql.addSql("   FDR_EGID,");
            sql.addSql("   FDR_ACCESS_SID");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   FDR_JTKBN=?");
            sql.addSql(" and ");
            sql.addSql("   FDR_EDATE < ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(GSConstFile.JTKBN_DELETE);
            sql.addDateValue(date);
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDirectoryFromRs(rs));
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
     * <p>指定したディレクトリのディレクトリ種別を取得する。
     * @param fdrSids FDR_SIDリスト
     * @return FILE_DIRECTORYModelリスト
     * @throws SQLException SQL実行例外
     */
    public List<Integer> getFdrKbn(String[] fdrSids) throws SQLException {

        List<Integer> ret = new ArrayList<Integer>();
        if (fdrSids == null || fdrSids.length < 1) {
            return ret;
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FDR_KBN as FDR_KBN,");
            sql.addSql("   count(FDR_SID) as CNT");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql(" where");
            sql.addSql(" (");

            int i = 0;
            for (String fdrSid : fdrSids) {
                if (i > 0) {
                    sql.addSql("  or");
                }
                sql.addSql("   FDR_SID = ?");
                sql.addIntValue(Integer.parseInt(fdrSid));
                i++;
            }
            sql.addSql(" )");

            sql.addSql(" group by");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt("FDR_KBN"));
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
     * <p>Create FILE_DIRECTORY Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created FileDirectoryModel
     * @throws SQLException SQL実行例外
     */
    private FileDirectoryModel __getFileDirectoryFromRs(ResultSet rs) throws SQLException {
        FileDirectoryModel bean = new FileDirectoryModel();
        bean.setFdrSid(rs.getInt("FDR_SID"));
        bean.setFdrVersion(rs.getInt("FDR_VERSION"));
        bean.setFcbSid(rs.getInt("FCB_SID"));
        bean.setFdrParentSid(rs.getInt("FDR_PARENT_SID"));
        bean.setFdrKbn(rs.getInt("FDR_KBN"));
        bean.setFdrVerKbn(rs.getInt("FDR_VER_KBN"));
        bean.setFdrLevel(rs.getInt("FDR_LEVEL"));
        bean.setFdrName(rs.getString("FDR_NAME"));
        bean.setFdrBiko(rs.getString("FDR_BIKO"));
        bean.setFdrJtkbn(rs.getInt("FDR_JTKBN"));
        bean.setFdrAuid(rs.getInt("FDR_AUID"));
        bean.setFdrAdate(UDate.getInstanceTimestamp(rs.getTimestamp("FDR_ADATE")));
        bean.setFdrEuid(rs.getInt("FDR_EUID"));
        bean.setFdrEdate(UDate.getInstanceTimestamp(rs.getTimestamp("FDR_EDATE")));
        bean.setFdrEgid(rs.getInt("FDR_EGID"));
        bean.setFdrAccessSid(rs.getInt("FDR_ACCESS_SID"));
        return bean;
    }
    /**
     * <p>Create FILE_DIRECTORY DSP Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @param reqMdl RequestModel
     * @return created FileDirectoryModel
     * @throws SQLException SQL実行例外
     */
    private FileDirectoryDspModel __getFileDirectoryDspFromRs(
            ResultSet rs, RequestModel reqMdl) throws SQLException {
        FileDirectoryDspModel bean = new FileDirectoryDspModel();
        bean.setFdrSid(rs.getInt("FDR_SID"));
        bean.setFdrVersion(rs.getInt("FDR_VERSION"));
        bean.setFcbSid(rs.getInt("FCB_SID"));
        bean.setFdrParentSid(rs.getInt("FDR_PARENT_SID"));
        bean.setFdrKbn(rs.getInt("FDR_KBN"));
        bean.setFdrVerKbn(rs.getInt("FDR_VER_KBN"));
        bean.setFdrLevel(rs.getInt("FDR_LEVEL"));
        bean.setFdrName(rs.getString("FDR_NAME"));
        bean.setFdrBiko(rs.getString("FDR_BIKO"));
        bean.setFdrJtkbn(rs.getInt("FDR_JTKBN"));
        bean.setFdrAuid(rs.getInt("FDR_AUID"));
        bean.setFdrAdate(UDate.getInstanceTimestamp(rs.getTimestamp("FDR_ADATE")));
        bean.setFdrEuid(rs.getInt("FDR_EUID"));
        bean.setFdrEdate(UDate.getInstanceTimestamp(rs.getTimestamp("FDR_EDATE")));
        bean.setFdrEgid(rs.getInt("FDR_EGID"));

        //追加情報
        bean.setFileBinSid(rs.getLong("BIN_SID"));
        BigDecimal bdSize = rs.getBigDecimal("FFL_FILE_SIZE");
        //B→KBへ変換
        String strSize = StringUtil.toCommaFromBigDecimal(
                bdSize.divide(GSConstFile.KB_TO_MB, 1, RoundingMode.HALF_UP));
        bean.setFileSize(strSize + "KB");
        bean.setFflFileSize(rs.getLong("FFL_FILE_SIZE"));
        bean.setCallKbn(rs.getString("CALLKBN"));
        UDate edate = UDate.getInstanceTimestamp(rs.getTimestamp("FDR_EDATE"));
        bean.setEdateString(UDateUtil.getSlashYYMD(edate) + " " + UDateUtil.getSeparateHMS(edate));

        if (bean.getFdrEgid() > 0) {
            bean.setUpUsrName(rs.getString("GRP_NAME"));
            bean.setUpUsrJkbn(rs.getInt("GRP_JKBN"));
        } else {
            bean.setUpUsrName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
            bean.setUpUsrJkbn(rs.getInt("USR_JKBN"));
        }

        bean.setLockKbn(rs.getString("FFL_LOCK_KBN"));
        int usrSid = -1;
        String textLockDate = "";
        if (rs.getInt("FFL_LOCK_KBN") == GSConstFile.LOCK_KBN_ON) {
            usrSid = rs.getInt("FFL_LOCK_USER");
            UDate lockDate = UDate.getInstanceTimestamp(rs.getTimestamp("FFL_LOCK_DATE"));
            if (lockDate != null) {
                textLockDate = UDateUtil.getYymdJ(lockDate, reqMdl);
                textLockDate += UDateUtil.getSeparateHMJ(lockDate, reqMdl);
            }
        }
        bean.setLockUsrSid(usrSid);
        bean.setLockDate(textLockDate);

        //if (bean.getLockKbn().equals(GSConstFile.LOCK_KBN_ON)) {
            bean.setLockUsrName(rs.getString("LOCK_UNAME"));
        //}

        bean.setAccessKbn(rs.getInt("ACKBN"));

        return bean;
    }
}
