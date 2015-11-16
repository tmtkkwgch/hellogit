package jp.groupsession.v2.cmn.dao.base;

import jp.co.sjts.util.date.UDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.model.base.MblUsrThemeModel;

/**
 * <p>MBL_USR_THEME Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class MblUsrThemeDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(MblUsrThemeDao.class);

    /**
     * <p>Default Constructor
     */
    public MblUsrThemeDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public MblUsrThemeDao(Connection con) {
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
            sql.addSql("drop table MBL_USR_THEME");

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
            sql.addSql(" create table MBL_USR_THEME (");
            sql.addSql("   USR_SID NUMBER(10,0) not null,");
            sql.addSql("   MBT_SID NUMBER(10,0) not null,");
            sql.addSql("   MUT_AUID NUMBER(10,0) not null,");
            sql.addSql("   MUT_ADATE varchar(23) not null,");
            sql.addSql("   MUT_EUID NUMBER(10,0) not null,");
            sql.addSql("   MUT_EDATE varchar(23) not null,");
            sql.addSql("   primary key (USR_SID)");
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
     * <p>Insert MBL_USR_THEME Data Bindding JavaBean
     * @param bean MBL_USR_THEME Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(MblUsrThemeModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" MBL_USR_THEME(");
            sql.addSql("   USR_SID,");
            sql.addSql("   MBT_SID,");
            sql.addSql("   MUT_AUID,");
            sql.addSql("   MUT_ADATE,");
            sql.addSql("   MUT_EUID,");
            sql.addSql("   MUT_EDATE");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getMbtSid());
            sql.addIntValue(bean.getMutAuid());
            sql.addDateValue(bean.getMutAdate());
            sql.addIntValue(bean.getMutEuid());
            sql.addDateValue(bean.getMutEdate());
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
     * <p>Update MBL_USR_THEME Data Bindding JavaBean
     * @param bean MBL_USR_THEME Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(MblUsrThemeModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   MBL_USR_THEME");
            sql.addSql(" set ");
            sql.addSql("   MBT_SID=?,");
            sql.addSql("   MUT_AUID=?,");
            sql.addSql("   MUT_ADATE=?,");
            sql.addSql("   MUT_EUID=?,");
            sql.addSql("   MUT_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getMbtSid());
            sql.addIntValue(bean.getMutAuid());
            sql.addDateValue(bean.getMutAdate());
            sql.addIntValue(bean.getMutEuid());
            sql.addDateValue(bean.getMutEdate());
            //where
            sql.addIntValue(bean.getUsrSid());


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
     * <p>Select MBL_USR_THEME All Data
     * @return List in MBL_USR_THEMEModel
     * @throws SQLException SQL実行例外
     */
    public List<MblUsrThemeModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<MblUsrThemeModel> ret = new ArrayList<MblUsrThemeModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   USR_SID,");
            sql.addSql("   MBT_SID,");
            sql.addSql("   MUT_AUID,");
            sql.addSql("   MUT_ADATE,");
            sql.addSql("   MUT_EUID,");
            sql.addSql("   MUT_EDATE");
            sql.addSql(" from ");
            sql.addSql("   MBL_USR_THEME");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getMblUsrThemeFromRs(rs));
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
     * <p>Select MBL_USR_THEME
     * @param usrSid USR_SID
     * @return MBL_USR_THEMEModel
     * @throws SQLException SQL実行例外
     */
    public String select(int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   MBL_THEME.MBT_ID as MBT_ID");
            sql.addSql(" from");
            sql.addSql("   MBL_USR_THEME,");
            sql.addSql("   MBL_THEME");
            sql.addSql(" where ");
            sql.addSql("   MBL_USR_THEME.MBT_SID = MBL_THEME.MBT_SID");
            sql.addSql(" and");
            sql.addSql("   MBL_USR_THEME.USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(usrSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getString("MBT_ID");
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
     * <p>Create MBL_USR_THEME Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created MblUsrThemeModel
     * @throws SQLException SQL実行例外
     */
    private MblUsrThemeModel __getMblUsrThemeFromRs(ResultSet rs) throws SQLException {
        MblUsrThemeModel bean = new MblUsrThemeModel();
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setMbtSid(rs.getInt("MBT_SID"));
        bean.setMutAuid(rs.getInt("MUT_AUID"));
        bean.setMutAdate(UDate.getInstanceTimestamp(rs.getTimestamp("MUT_ADATE")));
        bean.setMutEuid(rs.getInt("MUT_EUID"));
        bean.setMutEdate(UDate.getInstanceTimestamp(rs.getTimestamp("MUT_EDATE")));
        return bean;
    }
}
