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
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileFileRekiModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>FILE_FILE_REKI Data Access Object
 *
 * @author JTS DaoGenerator version 0.5
 */
public class FileFileRekiDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(FileFileRekiDao.class);

    /**
     * <p>Default Constructor
     */
    public FileFileRekiDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public FileFileRekiDao(Connection con) {
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
            sql.addSql("drop table FILE_FILE_REKI");

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
            sql.addSql(" create table FILE_FILE_REKI (");
            sql.addSql("   FDR_SID NUMBER(10,0) not null,");
            sql.addSql("   FDR_VERSION NUMBER(10,0) not null,");
            sql.addSql("   FFR_FNAME varchar(150) not null,");
            sql.addSql("   FFR_KBN NUMBER(10,0) not null,");
            sql.addSql("   FFR_EUID NUMBER(10,0) not null,");
            sql.addSql("   FFR_EDATE varchar(23) not null,");
            sql.addSql("   FFR_PARENT_SID NUMBER(10,0) not null,");
            sql.addSql("   FFR_UP_CMT varchar(3000),");
            sql.addSql("   FFR_EUID NUMBER(10,0),");
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
     * <p>Insert FILE_FILE_REKI Data Bindding JavaBean
     * @param bean FILE_FILE_REKI Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(FileFileRekiModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" FILE_FILE_REKI(");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FFR_FNAME,");
            sql.addSql("   FFR_KBN,");
            sql.addSql("   FFR_EUID,");
            sql.addSql("   FFR_EGID,");
            sql.addSql("   FFR_EDATE,");
            sql.addSql("   FFR_PARENT_SID,");
            sql.addSql("   FFR_UP_CMT");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFdrSid());
            sql.addIntValue(bean.getFdrVersion());
            sql.addStrValue(bean.getFfrFname());
            sql.addIntValue(bean.getFfrKbn());
            sql.addIntValue(bean.getFfrEuid());
            sql.addIntValue(bean.getFfrEgid());
            sql.addDateValue(bean.getFfrEdate());
            sql.addIntValue(bean.getFfrParentSid());
            sql.addStrValue(bean.getFfrUpCmt());
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
     * <p>Insert FILE_FILE_REKI Data Bindding JavaBean
     * @param beanList FILE_FILE_REKI DataList
     * @throws SQLException SQL実行例外
     */
    public void insert(List<FileFileRekiModel> beanList) throws SQLException {

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
            sql.addSql(" FILE_FILE_REKI(");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FFR_FNAME,");
            sql.addSql("   FFR_KBN,");
            sql.addSql("   FFR_EUID,");
            sql.addSql("   FFR_EGID,");
            sql.addSql("   FFR_EDATE,");
            sql.addSql("   FFR_PARENT_SID,");
            sql.addSql("   FFR_UP_CMT");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());

            for (FileFileRekiModel bean : beanList) {
                sql.addIntValue(bean.getFdrSid());
                sql.addIntValue(bean.getFdrVersion());
                sql.addStrValue(bean.getFfrFname());
                sql.addIntValue(bean.getFfrKbn());
                sql.addIntValue(bean.getFfrEuid());
                sql.addIntValue(bean.getFfrEgid());
                sql.addDateValue(bean.getFfrEdate());
                sql.addIntValue(bean.getFfrParentSid());
                sql.addStrValue(bean.getFfrUpCmt());
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
     * <p>Update FILE_FILE_REKI Data Bindding JavaBean
     * @param bean FILE_FILE_REKI Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(FileFileRekiModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_FILE_REKI");
            sql.addSql(" set ");
            sql.addSql("   FFR_FNAME=?,");
            sql.addSql("   FFR_KBN=?,");
            sql.addSql("   FFR_EUID=?,");
            sql.addSql("   FFR_EGID=?,");
            sql.addSql("   FFR_EDATE=?,");
            sql.addSql("   FFR_PARENT_SID=?,");
            sql.addSql("   FFR_UP_CMT=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   FDR_VERSION=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getFfrFname());
            sql.addIntValue(bean.getFfrKbn());
            sql.addIntValue(bean.getFfrEuid());
            sql.addIntValue(bean.getFfrEgid());
            sql.addDateValue(bean.getFfrEdate());
            sql.addIntValue(bean.getFfrParentSid());
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
     * <p>親ディレクトリを更新する。
     * @param bean FILE_FILE_REKI Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateParent(FileFileRekiModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_FILE_REKI");
            sql.addSql(" set ");
            sql.addSql("   FFR_PARENT_SID=?,");
            sql.addSql("   FFR_EUID=?,");
            sql.addSql("   FFR_EGID=?,");
            sql.addSql("   FFR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFfrParentSid());
            sql.addIntValue(bean.getFfrEuid());
            sql.addIntValue(bean.getFfrEgid());
            sql.addDateValue(bean.getFfrEdate());
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
     * <p>Select FILE_FILE_REKI All Data
     * @return List in FILE_FILE_REKIModel
     * @throws SQLException SQL実行例外
     */
    public List<FileFileRekiModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<FileFileRekiModel> ret = new ArrayList<FileFileRekiModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FFR_FNAME,");
            sql.addSql("   FFR_KBN,");
            sql.addSql("   FFR_EUID,");
            sql.addSql("   FFR_EGID,");
            sql.addSql("   FFR_EDATE,");
            sql.addSql("   FFR_PARENT_SID,");
            sql.addSql("   FFR_UP_CMT");
            sql.addSql(" from ");
            sql.addSql("   FILE_FILE_REKI");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileFileRekiFromRs(rs));
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
     * <p>Select FILE_FILE_REKI All Data
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @return List in FILE_FILE_REKIModel
     * @throws SQLException SQL実行例外
     */
    public List<FileFileRekiModel> selectLimit(
            int offset, int limit) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<FileFileRekiModel> ret = new ArrayList<FileFileRekiModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FFR_FNAME,");
            sql.addSql("   FFR_KBN,");
            sql.addSql("   FFR_EUID,");
            sql.addSql("   FFR_EGID,");
            sql.addSql("   FFR_EDATE,");
            sql.addSql("   FFR_PARENT_SID,");
            sql.addSql("   FFR_UP_CMT");
            sql.addSql(" from ");
            sql.addSql("   FILE_FILE_REKI");
            sql.addSql(" order by ");
            sql.addSql("   FDR_SID asc,");
            sql.addSql("   FDR_VERSION asc");

            sql.setPagingValue(offset, limit);

            pstmt = con.prepareStatement(sql.toSqlString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileFileRekiFromRs(rs));
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
     * <p>count FILE_FILE_REKI All Data
     * @return List in FILE_FILE_REKIModel
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
            sql.addSql("   FILE_FILE_REKI");

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
     * <p>Select FILE_FILE_REKI
     * @param fdrSid FDR_SID
     * @param fdrVersion FDR_VERSION
     * @return FILE_FILE_REKIModel
     * @throws SQLException SQL実行例外
     */
    public FileFileRekiModel select(int fdrSid, int fdrVersion) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        FileFileRekiModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FDR_SID,");
            sql.addSql("   FDR_VERSION,");
            sql.addSql("   FFR_FNAME,");
            sql.addSql("   FFR_KBN,");
            sql.addSql("   FFR_EUID,");
            sql.addSql("   FFR_EGID,");
            sql.addSql("   FFR_EDATE,");
            sql.addSql("   FFR_PARENT_SID,");
            sql.addSql("   FFR_UP_CMT");
            sql.addSql(" from");
            sql.addSql("   FILE_FILE_REKI");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   FDR_VERSION=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);
            sql.addIntValue(fdrVersion);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getFileFileRekiFromRs(rs);
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
     * @return FILE_FILE_REKIModel
     * @throws SQLException SQL実行例外
     */
    public FileFileRekiModel getNewDirectoryReki(int fdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        FileFileRekiModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   FILE_FILE_REKI.FDR_SID,");
            sql.addSql("   FILE_FILE_REKI.FDR_VERSION,");
            sql.addSql("   FILE_FILE_REKI.FFR_FNAME,");
            sql.addSql("   FILE_FILE_REKI.FFR_KBN,");
            sql.addSql("   FILE_FILE_REKI.FFR_EUID,");
            sql.addSql("   FILE_FILE_REKI.FFR_EGID,");
            sql.addSql("   FILE_FILE_REKI.FFR_EDATE,");
            sql.addSql("   FILE_FILE_REKI.FFR_PARENT_SID,");
            sql.addSql("   FILE_FILE_REKI.FFR_UP_CMT");
            sql.addSql(" from");
            sql.addSql("   FILE_FILE_REKI,");
            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION");
            sql.addSql("    from FILE_FILE_REKI group by FDR_SID) DIR_MAXVERSION");
            sql.addSql(" where");
            sql.addSql("   FILE_FILE_REKI.FDR_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_FILE_REKI.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_FILE_REKI.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getFileFileRekiFromRs(rs);
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
     * <p>Delete FILE_FILE_REKI
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
            sql.addSql("   FILE_FILE_REKI");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   FDR_VERSION=?");

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
            sql.addSql("   FILE_FILE_REKI");
            if (delList != null) {
                sql.addSql(" where ");
                sql.addSql("   FDR_SID=-1");
                for (FileDirectoryModel bean : delList) {
                    sql.addSql(" or");
                    sql.addSql("   FDR_SID=?");
                    sql.addIntValue(bean.getFdrSid());
                }
            }

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
     * <p>Create FILE_FILE_REKI Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created FileFileRekiModel
     * @throws SQLException SQL実行例外
     */
    private FileFileRekiModel __getFileFileRekiFromRs(ResultSet rs) throws SQLException {
        FileFileRekiModel bean = new FileFileRekiModel();
        bean.setFdrSid(rs.getInt("FDR_SID"));
        bean.setFdrVersion(rs.getInt("FDR_VERSION"));
        bean.setFfrFname(rs.getString("FFR_FNAME"));
        bean.setFfrKbn(rs.getInt("FFR_KBN"));
        bean.setFfrEuid(rs.getInt("FFR_EUID"));
        bean.setFfrEgid(rs.getInt("FFR_EGID"));
        bean.setFfrEdate(UDate.getInstanceTimestamp(rs.getTimestamp("FFR_EDATE")));
        bean.setFfrParentSid(rs.getInt("FFR_PARENT_SID"));
        bean.setFfrUpCmt(rs.getString("FFR_UP_CMT"));
        return bean;
    }
}