package jp.groupsession.v2.rng.dao;

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
import jp.groupsession.v2.rng.model.RngAconfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>RNG_ACONF Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RngAconfDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RngAconfDao.class);

    /**
     * <p>Default Constructor
     */
    public RngAconfDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public RngAconfDao(Connection con) {
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
            sql.addSql("drop table RNG_ACONF");

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
            sql.addSql(" create table RNG_ACONF (");
            sql.addSql("   RAR_DEL_AUTH NUMBER(10,0) not null,");
            sql.addSql("   RAR_AUID NUMBER(10,0) not null,");
            sql.addSql("   RAR_ADATE varchar(23) not null,");
            sql.addSql("   RAR_EUID NUMBER(10,0) not null,");
            sql.addSql("   RAR_EDATE varchar(23) not null,");
            sql.addSql("   RAR_SML_NTF NUMBER(10,0) not null,");
            sql.addSql("   RAR_SML_NTF_KBN NUMBER(10,0)");
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
     * <p>Insert RNG_ACONF Data Bindding JavaBean
     * @param bean RNG_ACONF Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(RngAconfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" RNG_ACONF(");
            sql.addSql("   RAR_DEL_AUTH,");
            sql.addSql("   RAR_AUID,");
            sql.addSql("   RAR_ADATE,");
            sql.addSql("   RAR_EUID,");
            sql.addSql("   RAR_EDATE,");
            sql.addSql("   RAR_SML_NTF,");
            sql.addSql("   RAR_SML_NTF_KBN");
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
            sql.addIntValue(bean.getRarDelAuth());
            sql.addIntValue(bean.getRarAuid());
            sql.addDateValue(bean.getRarAdate());
            sql.addIntValue(bean.getRarEuid());
            sql.addDateValue(bean.getRarEdate());
            sql.addIntValue(bean.getRarSmlNtf());
            sql.addIntValue(bean.getRarSmlNtfKbn());
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
     * <p>Update RNG_ACONF Data Bindding JavaBean
     * @param bean RNG_ACONF Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(RngAconfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_ACONF");
            sql.addSql(" set ");
            sql.addSql("   RAR_DEL_AUTH=?,");
            sql.addSql("   RAR_AUID=?,");
            sql.addSql("   RAR_ADATE=?,");
            sql.addSql("   RAR_EUID=?,");
            sql.addSql("   RAR_EDATE=?,");
            sql.addSql("   RAR_SML_NTF=?,");
            sql.addSql("   RAR_SML_NTF_KBN=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRarDelAuth());
            sql.addIntValue(bean.getRarAuid());
            sql.addDateValue(bean.getRarAdate());
            sql.addIntValue(bean.getRarEuid());
            sql.addDateValue(bean.getRarEdate());
            sql.addIntValue(bean.getRarSmlNtf());
            sql.addIntValue(bean.getRarSmlNtfKbn());

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
     * <p>稟議削除権限をアップデートする
     * @param bean RNG_ACONF Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateDeleteAuth(RngAconfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_ACONF");
            sql.addSql(" set ");
            sql.addSql("   RAR_DEL_AUTH=?,");
            sql.addSql("   RAR_AUID=?,");
            sql.addSql("   RAR_ADATE=?,");
            sql.addSql("   RAR_EUID=?,");
            sql.addSql("   RAR_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRarDelAuth());
            sql.addIntValue(bean.getRarAuid());
            sql.addDateValue(bean.getRarAdate());
            sql.addIntValue(bean.getRarEuid());
            sql.addDateValue(bean.getRarEdate());

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
     * <p>ショートメール通知設定をアップデートする
     * @param bean RNG_ACONF Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateSmailSetting(RngAconfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_ACONF");
            sql.addSql(" set ");
            sql.addSql("   RAR_EUID=?,");
            sql.addSql("   RAR_EDATE=?,");
            sql.addSql("   RAR_SML_NTF=?,");
            sql.addSql("   RAR_SML_NTF_KBN=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRarEuid());
            sql.addDateValue(bean.getRarEdate());
            sql.addIntValue(bean.getRarSmlNtf());
            sql.addIntValue(bean.getRarSmlNtfKbn());

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
     * <p>Select RNG_ACONF All Data
     * @return List in RNG_ACONFModel
     * @throws SQLException SQL実行例外
     */
    public List<RngAconfModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<RngAconfModel> ret = new ArrayList<RngAconfModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RAR_DEL_AUTH,");
            sql.addSql("   RAR_AUID,");
            sql.addSql("   RAR_ADATE,");
            sql.addSql("   RAR_EUID,");
            sql.addSql("   RAR_EDATE,");
            sql.addSql("   RAR_SML_NTF,");
            sql.addSql("   RAR_SML_NTF_KBN");
            sql.addSql(" from ");
            sql.addSql("   RNG_ACONF");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngAconfFromRs(rs));
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
     * <p>Create RNG_ACONF Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created RngAconfModel
     * @throws SQLException SQL実行例外
     */
    private RngAconfModel __getRngAconfFromRs(ResultSet rs) throws SQLException {
        RngAconfModel bean = new RngAconfModel();
        bean.setRarDelAuth(rs.getInt("RAR_DEL_AUTH"));
        bean.setRarAuid(rs.getInt("RAR_AUID"));
        bean.setRarAdate(UDate.getInstanceTimestamp(rs.getTimestamp("RAR_ADATE")));
        bean.setRarEuid(rs.getInt("RAR_EUID"));
        bean.setRarEdate(UDate.getInstanceTimestamp(rs.getTimestamp("RAR_EDATE")));
        bean.setRarSmlNtf(rs.getInt("RAR_SML_NTF"));
        bean.setRarSmlNtfKbn(rs.getInt("RAR_SML_NTF_KBN"));
        return bean;
    }
}
