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
import jp.groupsession.v2.rng.model.RngTemplateBinModel;

/**
 * <p>RNG_TEMPLATE_BIN Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RngTemplateBinDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RngTemplateBinDao.class);

    /**
     * <p>Default Constructor
     */
    public RngTemplateBinDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public RngTemplateBinDao(Connection con) {
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
            sql.addSql("drop table RNG_TEMPLATE_BIN");

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
            sql.addSql(" create table RNG_TEMPLATE_BIN (");
            sql.addSql("   RTP_SID NUMBER(10,0) not null,");
            sql.addSql("   BIN_SID NUMBER(10,0) not null,");
            sql.addSql("   primary key (RTP_SID,BIN_SID)");
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
     * <p>Insert RNG_TEMPLATE_BIN Data Bindding JavaBean
     * @param bean RNG_TEMPLATE_BIN Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(RngTemplateBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" RNG_TEMPLATE_BIN(");
            sql.addSql("   RTP_SID,");
            sql.addSql("   BIN_SID");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRtpSid());
            sql.addLongValue(bean.getBinSid());
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
     * <p>Update RNG_TEMPLATE_BIN Data Bindding JavaBean
     * @param bean RNG_TEMPLATE_BIN Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return count
     */
    public int update(RngTemplateBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_TEMPLATE_BIN");
            sql.addSql(" set ");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");
            sql.addSql(" and");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            //where
            sql.addIntValue(bean.getRtpSid());
            sql.addLongValue(bean.getBinSid());

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
     * <p>Select RNG_TEMPLATE_BIN All Data
     * @return List in RNG_TEMPLATE_BINModel
     * @throws SQLException SQL実行例外
     */
    public List<RngTemplateBinModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<RngTemplateBinModel> ret = new ArrayList<RngTemplateBinModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RTP_SID,");
            sql.addSql("   BIN_SID");
            sql.addSql(" from ");
            sql.addSql("   RNG_TEMPLATE_BIN");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngTemplateBinFromRs(rs));
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
     * <br>[機  能] 指定した稟議テンプレートSIDに該当するバイナリSIDを取得します
     * <br>[解  説]
     * <br>[備  考] StringのArrayListで返ってきます
     * @param rtpSid 稟議テンプレートSID
     * @return ArrayList(String) バイナリSID
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<String> selectBinList(int rtpSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<String> ret = new ArrayList<String>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RTP_SID,");
            sql.addSql("   BIN_SID as BIN");
            sql.addSql(" from ");
            sql.addSql("   RNG_TEMPLATE_BIN");
            sql.addSql(" where");
            sql.addSql("   RTP_SID = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtpSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getString("BIN"));
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
     * <p>Select RNG_TEMPLATE_BIN
     * @param bean RNG_TEMPLATE_BIN Model
     * @return RNG_TEMPLATE_BINModel
     * @throws SQLException SQL実行例外
     */
    public RngTemplateBinModel select(RngTemplateBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RngTemplateBinModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RTP_SID,");
            sql.addSql("   BIN_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_BIN");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");
            sql.addSql(" and");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRtpSid());
            sql.addLongValue(bean.getBinSid());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRngTemplateBinFromRs(rs);
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
     * <p>Select RNG_TEMPLATE_BIN
     * @param bean RNG_TEMPLATE_BIN Model
     * @param rtpSid 稟議テンプレートSID
     * @return RNG_TEMPLATE_BINModel
     * @throws SQLException SQL実行例外
     */
    public RngTemplateBinModel select(RngTemplateBinModel bean, int rtpSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RngTemplateBinModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RTP_SID,");
            sql.addSql("   BIN_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_BIN");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtpSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRngTemplateBinFromRs(rs);
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
     * <p>Delete RNG_TEMPLATE_BIN
     * @param bean RNG_TEMPLATE_BIN Model
     * @throws SQLException SQL実行例外
     * @return count
     */
    public  int delete(RngTemplateBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_BIN");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");
            sql.addSql(" and");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRtpSid());
            sql.addLongValue(bean.getBinSid());

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
     * <br>[機  能] 指定したテンプレートSIDのバイナリ情報を削除します
     * <br>[解  説]
     * <br>[備  考]
     * @param rtpSid テンプレートSID
     * @return count
     * @throws SQLException SQL実行時例外
     */
    public int deleteTpl(int rtpSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_BIN");
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
     * <p>Create RNG_TEMPLATE_BIN Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created RngTemplateBinModel
     * @throws SQLException SQL実行例外
     */
    private RngTemplateBinModel __getRngTemplateBinFromRs(ResultSet rs) throws SQLException {
        RngTemplateBinModel bean = new RngTemplateBinModel();
        bean.setRtpSid(rs.getInt("RTP_SID"));
        bean.setBinSid(rs.getLong("BIN_SID"));
        return bean;
    }

    /**
     * <br>[機  能] 指定されたバイナリSIDが稟議テンプレートの添付ファイルのものかチェックする。
     * <br>[解  説]
     * <br>[備  考]
     * @param rtpSid 稟議テンプレートSID
     * @param binSid バイナリSID
     * @return RNG_TEMPLATE_BINModel
     * @throws SQLException SQL実行例外
     */
    public boolean isCheckRngTemplateBin(int rtpSid, Long binSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int cnt = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(*) as CNT");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_BIN");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");
            sql.addSql(" and ");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtpSid);
            sql.addLongValue(binSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cnt = rs.getInt("CNT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return cnt > 0;
    }
}
