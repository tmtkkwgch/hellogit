package jp.groupsession.v2.prj.dao;

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
import jp.groupsession.v2.prj.model.PrjPrjdataTmpModel;
import jp.groupsession.v2.prj.model.PrjPrjstatusTmpModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>PRJ_PRJSTATUS_TMP Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class PrjPrjstatusTmpDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(PrjPrjstatusTmpDao.class);

    /**
     * <p>Default Constructor
     */
    public PrjPrjstatusTmpDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public PrjPrjstatusTmpDao(Connection con) {
        super(con);
    }

    /**
     * <p>Insert PRJ_PRJSTATUS_TMP Data Bindding JavaBean
     * @param bean PRJ_PRJSTATUS_TMP Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(PrjPrjstatusTmpModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" PRJ_PRJSTATUS_TMP(");
            sql.addSql("   PRT_SID,");
            sql.addSql("   PTT_SID,");
            sql.addSql("   PTT_SORT,");
            sql.addSql("   PTT_NAME,");
            sql.addSql("   PTT_RATE,");
            sql.addSql("   PTT_AUID,");
            sql.addSql("   PTT_ADATE,");
            sql.addSql("   PTT_EUID,");
            sql.addSql("   PTT_EDATE");
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
            sql.addIntValue(bean.getPrtSid());
            sql.addIntValue(bean.getPttSid());
            sql.addIntValue(bean.getPttSort());
            sql.addStrValue(bean.getPttName());
            sql.addIntValue(bean.getPttRate());
            sql.addIntValue(bean.getPttAuid());
            sql.addDateValue(bean.getPttAdate());
            sql.addIntValue(bean.getPttEuid());
            sql.addDateValue(bean.getPttEdate());
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
     * <p>Update PRJ_PRJSTATUS_TMP Data Bindding JavaBean
     * @param bean PRJ_PRJSTATUS_TMP Data Bindding JavaBean
     * @return count update count
     * @throws SQLException SQL実行例外
     */
    public int update(PrjPrjstatusTmpModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   PRJ_PRJSTATUS_TMP");
            sql.addSql(" set ");
            sql.addSql("   PTT_SORT=?,");
            sql.addSql("   PTT_NAME=?,");
            sql.addSql("   PTT_RATE=?,");
            sql.addSql("   PTT_AUID=?,");
            sql.addSql("   PTT_ADATE=?,");
            sql.addSql("   PTT_EUID=?,");
            sql.addSql("   PTT_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   PRT_SID=?");
            sql.addSql(" and");
            sql.addSql("   PTT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getPttSort());
            sql.addStrValue(bean.getPttName());
            sql.addIntValue(bean.getPttRate());
            sql.addIntValue(bean.getPttAuid());
            sql.addDateValue(bean.getPttAdate());
            sql.addIntValue(bean.getPttEuid());
            sql.addDateValue(bean.getPttEdate());
            //where
            sql.addIntValue(bean.getPrtSid());
            sql.addIntValue(bean.getPttSid());

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
     * <p>Select PRJ_PRJSTATUS_TMP All Data
     * @return List in PRJ_PRJSTATUS_TMPModel
     * @throws SQLException SQL実行例外
     */
    public List<PrjPrjstatusTmpModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<PrjPrjstatusTmpModel> ret = new ArrayList<PrjPrjstatusTmpModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   PRT_SID,");
            sql.addSql("   PTT_SID,");
            sql.addSql("   PTT_SORT,");
            sql.addSql("   PTT_NAME,");
            sql.addSql("   PTT_RATE,");
            sql.addSql("   PTT_AUID,");
            sql.addSql("   PTT_ADATE,");
            sql.addSql("   PTT_EUID,");
            sql.addSql("   PTT_EDATE");
            sql.addSql(" from ");
            sql.addSql("   PRJ_PRJSTATUS_TMP");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getPrjPrjstatusTmpFromRs(rs));
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
     * <p>Select PRJ_PRJSTATUS_TMP
     * @param bean PRJ_PRJSTATUS_TMP Model
     * @return PRJ_PRJSTATUS_TMPModel
     * @throws SQLException SQL実行例外
     */
    public PrjPrjstatusTmpModel select(PrjPrjstatusTmpModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        PrjPrjstatusTmpModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   PRT_SID,");
            sql.addSql("   PTT_SID,");
            sql.addSql("   PTT_SORT,");
            sql.addSql("   PTT_NAME,");
            sql.addSql("   PTT_RATE,");
            sql.addSql("   PTT_AUID,");
            sql.addSql("   PTT_ADATE,");
            sql.addSql("   PTT_EUID,");
            sql.addSql("   PTT_EDATE");
            sql.addSql(" from");
            sql.addSql("   PRJ_PRJSTATUS_TMP");
            sql.addSql(" where ");
            sql.addSql("   PRT_SID=?");
            sql.addSql(" and");
            sql.addSql("   PTT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getPrtSid());
            sql.addIntValue(bean.getPttSid());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getPrjPrjstatusTmpFromRs(rs);
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
     * <p>Delete PRJ_PRJSTATUS_TMP
     * @param bean PRJ_PRJSTATUS_TMP Model
     * @return count delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(PrjPrjstatusTmpModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   PRJ_PRJSTATUS_TMP");
            sql.addSql(" where ");
            sql.addSql("   PRT_SID=?");
            sql.addSql(" and");
            sql.addSql("   PTT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getPrtSid());
            sql.addIntValue(bean.getPttSid());

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
     * <p>Delete PRJ_PRJSTATUS_TMP
     * @param prtSid prtSid
     * @return count delete count
     * @throws SQLException SQL実行例外
     */
    public int deleteAll(int prtSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   PRJ_PRJSTATUS_TMP");
            sql.addSql(" where ");
            sql.addSql("   PRT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(prtSid);

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
     * <br>[機  能] プロジェクトテンプレート状態(複数)を削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param prjTmpSidList 削除SIDリスト
     * @throws SQLException SQL実行例外
     */
    public void deletePrjTemplateStatus(ArrayList<PrjPrjdataTmpModel> prjTmpSidList)
        throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        if (prjTmpSidList == null || prjTmpSidList.size() < 1) {
            return;
        }

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   PRJ_PRJSTATUS_TMP");
            sql.addSql(" where ");
            sql.addSql("   PRT_SID in (");

            for (int i = 0; i < prjTmpSidList.size(); i++) {
                sql.addSql("?");
                PrjPrjdataTmpModel param = prjTmpSidList.get(i);
                sql.addIntValue(param.getPrtSid());
                if (i < prjTmpSidList.size() - 1) {
                    sql.addSql(", ");
                }
            }
            sql.addSql(")");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] プロジェクトテンプレート状態を取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param prtSid PRJ_SID
     * @return PRJ_PRJSTATUS_TMP Model
     * @throws SQLException SQL実行例外
     */
    public List<PrjPrjstatusTmpModel> selectTmpProjects(int prtSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<PrjPrjstatusTmpModel> ret = new ArrayList<PrjPrjstatusTmpModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   PRT_SID,");
            sql.addSql("   PTT_SID,");
            sql.addSql("   PTT_SORT,");
            sql.addSql("   PTT_NAME,");
            sql.addSql("   PTT_RATE,");
            sql.addSql("   PTT_AUID,");
            sql.addSql("   PTT_ADATE,");
            sql.addSql("   PTT_EUID,");
            sql.addSql("   PTT_EDATE");
            sql.addSql(" from");
            sql.addSql("   PRJ_PRJSTATUS_TMP");
            sql.addSql(" where ");
            sql.addSql("   PRT_SID = ?");
            sql.addSql(" order by ");
            sql.addSql("   PTT_SORT asc");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(prtSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getPrjPrjstatusTmpFromRs(rs));
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
     * <p>Create PRJ_PRJSTATUS_TMP Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created PrjPrjstatusTmpModel
     * @throws SQLException SQL実行例外
     */
    private PrjPrjstatusTmpModel __getPrjPrjstatusTmpFromRs(ResultSet rs) throws SQLException {
        PrjPrjstatusTmpModel bean = new PrjPrjstatusTmpModel();
        bean.setPrtSid(rs.getInt("PRT_SID"));
        bean.setPttSid(rs.getInt("PTT_SID"));
        bean.setPttSort(rs.getInt("PTT_SORT"));
        bean.setPttName(rs.getString("PTT_NAME"));
        bean.setPttRate(rs.getInt("PTT_RATE"));
        bean.setPttAuid(rs.getInt("PTT_AUID"));
        bean.setPttAdate(UDate.getInstanceTimestamp(rs.getTimestamp("PTT_ADATE")));
        bean.setPttEuid(rs.getInt("PTT_EUID"));
        bean.setPttEdate(UDate.getInstanceTimestamp(rs.getTimestamp("PTT_EDATE")));
        return bean;
    }
}