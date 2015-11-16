package jp.groupsession.v2.rng.dao;

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
import jp.groupsession.v2.rng.model.RngUconfModel;

/**
 * <p>RNG_UCONF Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RngUconfDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RngUconfDao.class);

    /**
     * <p>Default Constructor
     */
    public RngUconfDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public RngUconfDao(Connection con) {
        super(con);
    }

    /**
     * <p>Insert RNG_UCONF Data Bindding JavaBean
     * @param bean RNG_UCONF Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(RngUconfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" RNG_UCONF(");
            sql.addSql("   USR_SID,");
            sql.addSql("   RUR_SML_NTF,");
            sql.addSql("   RUR_VIEW_CNT");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getRurSmlNtf());
            sql.addIntValue(bean.getRurViewCnt());
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
     * <p>Update RNG_UCONF Data Bindding JavaBean
     * @param bean RNG_UCONF Data Bindding JavaBean
     * @return update count
     * @throws SQLException SQL実行例外
     */
    public int update(RngUconfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_UCONF");
            sql.addSql(" set ");
            sql.addSql("   RUR_SML_NTF=?,");
            sql.addSql("   RUR_VIEW_CNT=?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRurSmlNtf());
            sql.addIntValue(bean.getRurViewCnt());
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
     * <p>Select RNG_UCONF All Data
     * @return List in RNG_UCONFModel
     * @throws SQLException SQL実行例外
     */
    public List<RngUconfModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<RngUconfModel> ret = new ArrayList<RngUconfModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   USR_SID,");
            sql.addSql("   RUR_SML_NTF,");
            sql.addSql("   RUR_VIEW_CNT");
            sql.addSql(" from ");
            sql.addSql("   RNG_UCONF");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngUconfFromRs(rs));
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
     * <p>Select RNG_UCONF
     * @param userSid ユーザSID
     * @return RNG_UCONFModel
     * @throws SQLException SQL実行例外
     */
    public RngUconfModel select(int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RngUconfModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_SID,");
            sql.addSql("   RUR_SML_NTF,");
            sql.addSql("   RUR_VIEW_CNT");
            sql.addSql(" from");
            sql.addSql("   RNG_UCONF");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(userSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRngUconfFromRs(rs);
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
     * <p>Delete RNG_UCONF
     * @param userSid ユーザSID
     * @return delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_UCONF");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(userSid);

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
     * <p>Create RNG_UCONF Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created RngUconfModel
     * @throws SQLException SQL実行例外
     */
    private RngUconfModel __getRngUconfFromRs(ResultSet rs) throws SQLException {
        RngUconfModel bean = new RngUconfModel();
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setRurSmlNtf(rs.getInt("RUR_SML_NTF"));
        bean.setRurViewCnt(rs.getInt("RUR_VIEW_CNT"));
        return bean;
    }
}
