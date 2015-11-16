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
import jp.groupsession.v2.rng.model.RngChannelTemplateUserModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>RNG_CHANNEL_TEMPLATE_USER Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RngChannelTemplateUserDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RngChannelTemplateUserDao.class);

    /**
     * <p>Default Constructor
     */
    public RngChannelTemplateUserDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public RngChannelTemplateUserDao(Connection con) {
        super(con);
    }

    /**
     * <p>Insert RNG_CHANNEL_TEMPLATE_USER Data Bindding JavaBean
     * @param bean RNG_CHANNEL_TEMPLATE_USER Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(RngChannelTemplateUserModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" RNG_CHANNEL_TEMPLATE_USER(");
            sql.addSql("   RCT_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RCU_SORT,");
            sql.addSql("   RCU_TYPE,");
            sql.addSql("   RCU_AUID,");
            sql.addSql("   RCU_ADATE,");
            sql.addSql("   RCU_EUID,");
            sql.addSql("   RCU_EDATE");
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
            sql.addIntValue(bean.getRctSid());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getRcuSort());
            sql.addIntValue(bean.getRcuType());
            sql.addIntValue(bean.getRcuAuid());
            sql.addDateValue(bean.getRcuAdate());
            sql.addIntValue(bean.getRcuEuid());
            sql.addDateValue(bean.getRcuEdate());
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
     * <p>Update RNG_CHANNEL_TEMPLATE_USER Data Bindding JavaBean
     * @param bean RNG_CHANNEL_TEMPLATE_USER Data Bindding JavaBean
     * @return update count
     * @throws SQLException SQL実行例外
     */
    public int update(RngChannelTemplateUserModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER");
            sql.addSql(" set ");
            sql.addSql("   RCU_SORT=?,");
            sql.addSql("   RCU_TYPE=?,");
            sql.addSql("   RCU_EUID=?,");
            sql.addSql("   RCU_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RCT_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRcuSort());
            sql.addIntValue(bean.getRcuType());
            sql.addIntValue(bean.getRcuEuid());
            sql.addDateValue(bean.getRcuEdate());
            //where
            sql.addIntValue(bean.getRctSid());
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
     * <p>Select RNG_CHANNEL_TEMPLATE_USER All Data
     * @return List in RNG_CHANNEL_TEMPLATE_USERModel
     * @throws SQLException SQL実行例外
     */
    public List<RngChannelTemplateUserModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<RngChannelTemplateUserModel> ret = new ArrayList<RngChannelTemplateUserModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RCT_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RCU_SORT,");
            sql.addSql("   RCU_TYPE,");
            sql.addSql("   RCU_AUID,");
            sql.addSql("   RCU_ADATE,");
            sql.addSql("   RCU_EUID,");
            sql.addSql("   RCU_EDATE");
            sql.addSql(" from ");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngChannelTemplateUserFromRs(rs));
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
     * <br>[機  能] 経路テンプレートユーザ情報一覧を取得する
     * <br>[解  説] 処理モード = 編集の場合、経路テンプレート情報を設定する
     * <br>[備  考]
     * @param rctSid 経路テンプレートSID
     * @return 経路テンプレートユーザ情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<RngChannelTemplateUserModel> getRctUserList(int rctSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<RngChannelTemplateUserModel> ret = new ArrayList<RngChannelTemplateUserModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RCT_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RCU_SORT,");
            sql.addSql("   RCU_TYPE,");
            sql.addSql("   RCU_AUID,");
            sql.addSql("   RCU_ADATE,");
            sql.addSql("   RCU_EUID,");
            sql.addSql("   RCU_EDATE");
            sql.addSql(" from ");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER");
            sql.addSql(" where");
            sql.addSql("   RCT_SID = ?");
            sql.addSql(" order by");
            sql.addSql("   RCU_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rctSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getRngChannelTemplateUserFromRs(rs));
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
     * <br>[機  能] 経路テンプレートユーザ情報一覧を取得する
     * <br>[解  説] 処理モード = 編集の場合、経路テンプレート情報を設定する
     * <br>[備  考] 削除ユーザは除外する
     * @param rctSid 経路テンプレートSID
     * @return 経路テンプレートユーザ情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<RngChannelTemplateUserModel> getRctUserListWithoutDelUser(int rctSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<RngChannelTemplateUserModel> ret = new ArrayList<RngChannelTemplateUserModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.RCT_SID as RCT_SID,");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.USR_SID as USR_SID,");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.RCU_SORT as RCU_SORT,");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.RCU_TYPE as RCU_TYPE,");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.RCU_AUID as RCU_AUID,");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.RCU_ADATE as RCU_ADATE,");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.RCU_EUID as RCU_EUID,");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.RCU_EDATE as RCU_EDATE");
            sql.addSql(" from ");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER");
            sql.addSql(" where");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.RCT_SID = ?");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID = RNG_CHANNEL_TEMPLATE_USER.USR_SID");
            sql.addSql(" order by");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER.RCU_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rctSid);
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getRngChannelTemplateUserFromRs(rs));
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
     * <p>Select RNG_CHANNEL_TEMPLATE_USER
     * @param bean RNG_CHANNEL_TEMPLATE_USER Model
     * @return RNG_CHANNEL_TEMPLATE_USERModel
     * @throws SQLException SQL実行例外
     */
    public RngChannelTemplateUserModel select(RngChannelTemplateUserModel bean)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RngChannelTemplateUserModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RCT_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RCU_SORT,");
            sql.addSql("   RCU_TYPE,");
            sql.addSql("   RCU_AUID,");
            sql.addSql("   RCU_ADATE,");
            sql.addSql("   RCU_EUID,");
            sql.addSql("   RCU_EDATE");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER");
            sql.addSql(" where ");
            sql.addSql("   RCT_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRctSid());
            sql.addIntValue(bean.getUsrSid());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRngChannelTemplateUserFromRs(rs);
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
     * <p>Delete RNG_CHANNEL_TEMPLATE_USER
     * @param rctSid 経路テンプレートSID
     * @return 削除件数
     * @throws SQLException SQL実行例外
     */
    public int delete(int rctSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL_TEMPLATE_USER");
            sql.addSql(" where ");
            sql.addSql("   RCT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rctSid);

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
     * <p>Create RNG_CHANNEL_TEMPLATE_USER Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created RngChannelTemplateUserModel
     * @throws SQLException SQL実行例外
     */
    private RngChannelTemplateUserModel __getRngChannelTemplateUserFromRs(ResultSet rs)
    throws SQLException {
        RngChannelTemplateUserModel bean = new RngChannelTemplateUserModel();
        bean.setRctSid(rs.getInt("RCT_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setRcuSort(rs.getInt("RCU_SORT"));
        bean.setRcuType(rs.getInt("RCU_TYPE"));
        bean.setRcuAuid(rs.getInt("RCU_AUID"));
        bean.setRcuAdate(UDate.getInstanceTimestamp(rs.getTimestamp("RCU_ADATE")));
        bean.setRcuEuid(rs.getInt("RCU_EUID"));
        bean.setRcuEdate(UDate.getInstanceTimestamp(rs.getTimestamp("RCU_EDATE")));
        return bean;
    }
}
