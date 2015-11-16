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
import jp.groupsession.v2.rng.model.RngTemplateUserModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>RNG_TEMPLATE_USER Data Access Object
 *
 * @author JTS DaoGenerator version 0.5
 */
public class RngTemplateUserDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RngTemplateUserDao.class);

    /**
     * <p>Default Constructor
     */
    public RngTemplateUserDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public RngTemplateUserDao(Connection con) {
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
            sql.addSql("drop table RNG_TEMPLATE_USER");

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
            sql.addSql(" create table RNG_TEMPLATE_USER (");
            sql.addSql("   RTP_SID NUMBER(10,0) not null,");
            sql.addSql("   USR_SID NUMBER(10,0) not null,");
            sql.addSql("   RTU_SORT NUMBER(10,0) not null,");
            sql.addSql("   RTU_TYPE NUMBER(10,0) not null,");
            sql.addSql("   RTU_AUID NUMBER(10,0) not null,");
            sql.addSql("   RTU_ADATE varchar(23) not null,");
            sql.addSql("   RTU_EUID NUMBER(10,0) not null,");
            sql.addSql("   RTU_EDATE varchar(23) not null,");
            sql.addSql("   primary key (RTP_SID,USR_SID)");
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
     * <p>Insert RNG_TEMPLATE_USER Data Bindding JavaBean
     * @param bean RNG_TEMPLATE_USER Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(RngTemplateUserModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" RNG_TEMPLATE_USER(");
            sql.addSql("   RTP_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RTU_SORT,");
            sql.addSql("   RTU_TYPE,");
            sql.addSql("   RTU_AUID,");
            sql.addSql("   RTU_ADATE,");
            sql.addSql("   RTU_EUID,");
            sql.addSql("   RTU_EDATE");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRtpSid());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getRtuSort());
            sql.addIntValue(bean.getRtuType());
            sql.addIntValue(bean.getRtuAuid());
            sql.addDateValue(bean.getRtuAdate());
            sql.addIntValue(bean.getRtuEuid());
            sql.addDateValue(bean.getRtuEdate());
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
     * <p>Update RNG_TEMPLATE_USER Data Bindding JavaBean
     * @param bean RNG_TEMPLATE_USER Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(RngTemplateUserModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_TEMPLATE_USER");
            sql.addSql(" set ");
            sql.addSql("   RTU_SORT=?,");
            sql.addSql("   RTU_TYPE=?,");
            sql.addSql("   RTU_AUID=?,");
            sql.addSql("   RTU_ADATE=?,");
            sql.addSql("   RTU_EUID=?,");
            sql.addSql("   RTU_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRtuSort());
            sql.addIntValue(bean.getRtuType());
            sql.addIntValue(bean.getRtuAuid());
            sql.addDateValue(bean.getRtuAdate());
            sql.addIntValue(bean.getRtuEuid());
            sql.addDateValue(bean.getRtuEdate());
            //where
            sql.addIntValue(bean.getRtpSid());
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
     * <p>Select RNG_TEMPLATE_USER All Data
     * @return List in RNG_TEMPLATE_USERModel
     * @throws SQLException SQL実行例外
     */
    public List<RngTemplateUserModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<RngTemplateUserModel> ret = new ArrayList<RngTemplateUserModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RTP_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RTU_SORT,");
            sql.addSql("   RTU_TYPE,");
            sql.addSql("   RTU_AUID,");
            sql.addSql("   RTU_ADATE,");
            sql.addSql("   RTU_EUID,");
            sql.addSql("   RTU_EDATE");
            sql.addSql(" from ");
            sql.addSql("   RNG_TEMPLATE_USER");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngTemplateUserFromRs(rs));
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
     * <p>Select RNG_TEMPLATE_USER
     * @param rtpSid RTP_SID
     * @param usrSid USR_SID
     * @return RNG_TEMPLATE_USERModel
     * @throws SQLException SQL実行例外
     */
    public RngTemplateUserModel select(int rtpSid, int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RngTemplateUserModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RTP_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RTU_SORT,");
            sql.addSql("   RTU_TYPE,");
            sql.addSql("   RTU_AUID,");
            sql.addSql("   RTU_ADATE,");
            sql.addSql("   RTU_EUID,");
            sql.addSql("   RTU_EDATE");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_USER");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtpSid);
            sql.addIntValue(usrSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRngTemplateUserFromRs(rs);
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
     * <br>[機  能] 経路情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rtpSid 稟議テンプレートSID
     * @return 経路テンプレートユーザ情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<RngTemplateUserModel> getRtuList(int rtpSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<RngTemplateUserModel> ret = new ArrayList<RngTemplateUserModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RTP_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RTU_SORT,");
            sql.addSql("   RTU_TYPE,");
            sql.addSql("   RTU_AUID,");
            sql.addSql("   RTU_ADATE,");
            sql.addSql("   RTU_EUID,");
            sql.addSql("   RTU_EDATE");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_USER");
            sql.addSql(" where");
            sql.addSql("   RTP_SID = ?");
            sql.addSql(" order by");
            sql.addSql("   RTU_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rtpSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getRngTemplateUserFromRs(rs));
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
     * <p>Delete RNG_TEMPLATE_USER
     * @param rtpSid RTP_SID
     * @return count
     * @throws SQLException SQL実行例外
     */
    public int delete(int rtpSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_USER");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtpSid);

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
     * <br>[機  能] 指定したカテゴリSID以下のテンプレートの経路情報を削除
     * <br>[解  説]
     * <br>[備  考]
     * @param rtcSid RTC_SID
     * @return count
     * @throws SQLException SQL実行例外
     */
    public int deleteCat(int rtcSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_USER");
            sql.addSql(" where ");
            sql.addSql("   RTC_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtcSid);

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
     * <p>Create RNG_TEMPLATE_USER Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created RngTemplateUserModel
     * @throws SQLException SQL実行例外
     */
    private RngTemplateUserModel __getRngTemplateUserFromRs(ResultSet rs) throws SQLException {
        RngTemplateUserModel bean = new RngTemplateUserModel();
        bean.setRtpSid(rs.getInt("RTP_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setRtuSort(rs.getInt("RTU_SORT"));
        bean.setRtuType(rs.getInt("RTU_TYPE"));
        bean.setRtuAuid(rs.getInt("RTU_AUID"));
        bean.setRtuAdate(UDate.getInstanceTimestamp(rs.getTimestamp("RTU_ADATE")));
        bean.setRtuEuid(rs.getInt("RTU_EUID"));
        bean.setRtuEdate(UDate.getInstanceTimestamp(rs.getTimestamp("RTU_EDATE")));
        return bean;
    }
}
