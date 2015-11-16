package jp.groupsession.v2.sch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.sch.model.SchSpaccessPermitModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>SCH_SPACCESS_PERMIT Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class SchSpaccessPermitDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(SchSpaccessPermitDao.class);

    /**
     * <p>Default Constructor
     */
    public SchSpaccessPermitDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public SchSpaccessPermitDao(Connection con) {
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
            sql.addSql("drop table SCH_SPACCESS_PERMIT");

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
            sql.addSql(" create table SCH_SPACCESS_PERMIT (");
            sql.addSql("   SSA_SID NUMBER(10,0) not null,");
            sql.addSql("   SSP_TYPE NUMBER(10,0) not null,");
            sql.addSql("   SSP_PSID NUMBER(10,0) not null,");
            sql.addSql("   SSP_AUTH NUMBER(10,0) not null");
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
     * <p>Insert SCH_SPACCESS_PERMIT Data Bindding JavaBean
     * @param bean SCH_SPACCESS_PERMIT Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(SchSpaccessPermitModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" SCH_SPACCESS_PERMIT(");
            sql.addSql("   SSA_SID,");
            sql.addSql("   SSP_TYPE,");
            sql.addSql("   SSP_PSID,");
            sql.addSql("   SSP_AUTH");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getSsaSid());
            sql.addIntValue(bean.getSspType());
            sql.addIntValue(bean.getSspPsid());
            sql.addIntValue(bean.getSspAuth());
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
     * <p>Update SCH_SPACCESS_PERMIT Data Bindding JavaBean
     * @param bean SCH_SPACCESS_PERMIT Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(SchSpaccessPermitModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SCH_SPACCESS_PERMIT");
            sql.addSql(" set ");
            sql.addSql("   SSA_SID=?,");
            sql.addSql("   SSP_TYPE=?,");
            sql.addSql("   SSP_PSID=?,");
            sql.addSql("   SSP_AUTH=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getSsaSid());
            sql.addIntValue(bean.getSspType());
            sql.addIntValue(bean.getSspPsid());
            sql.addIntValue(bean.getSspAuth());

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
     * <p>Update SCH_SPACCESS_PERMIT Data Bindding JavaBean
     * @param ssaSid SSA_SID
     * @return delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(int ssaSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete from");
            sql.addSql("   SCH_SPACCESS_PERMIT");
            sql.addSql(" where ");
            sql.addSql("   SSA_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(ssaSid);

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
     * <p>Select SCH_SPACCESS_PERMIT All Data
     * @return List in SCH_SPACCESS_PERMITModel
     * @throws SQLException SQL実行例外
     */
    public List<SchSpaccessPermitModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SchSpaccessPermitModel> ret = new ArrayList<SchSpaccessPermitModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SSA_SID,");
            sql.addSql("   SSP_TYPE,");
            sql.addSql("   SSP_PSID,");
            sql.addSql("   SSP_AUTH");
            sql.addSql(" from ");
            sql.addSql("   SCH_SPACCESS_PERMIT");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getSchSpaccessPermitFromRs(rs));
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
     * <br>[機  能] 指定された特例アクセスの許可対象を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param ssaSid 特例アクセスSID
     * @return 許可対象
     * @throws SQLException SQL実行時例外
     */
    public List<SchSpaccessPermitModel> getPermitList(int ssaSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SchSpaccessPermitModel> ret = new ArrayList<SchSpaccessPermitModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SSA_SID,");
            sql.addSql("   SSP_TYPE,");
            sql.addSql("   SSP_PSID,");
            sql.addSql("   SSP_AUTH");
            sql.addSql(" from ");
            sql.addSql("   SCH_SPACCESS_PERMIT");
            sql.addSql(" where ");
            sql.addSql("   SSA_SID = ?");
            sql.addIntValue(ssaSid);

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getSchSpaccessPermitFromRs(rs));
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
     * <p>Create SCH_SPACCESS_PERMIT Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created SchSpaccessPermitModel
     * @throws SQLException SQL実行例外
     */
    private SchSpaccessPermitModel __getSchSpaccessPermitFromRs(ResultSet rs) throws SQLException {
        SchSpaccessPermitModel bean = new SchSpaccessPermitModel();
        bean.setSsaSid(rs.getInt("SSA_SID"));
        bean.setSspType(rs.getInt("SSP_TYPE"));
        bean.setSspPsid(rs.getInt("SSP_PSID"));
        bean.setSspAuth(rs.getInt("SSP_AUTH"));
        return bean;
    }
}
