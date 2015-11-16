package jp.groupsession.v2.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.prj.model.PrjDirectoryModel;
import jp.groupsession.v2.prj.model.PrjFileBinModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>PRJ_FILE_BIN Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class PrjFileBinDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(PrjFileBinDao.class);

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public PrjFileBinDao(Connection con) {
        super(con);
    }

    /**
     * <p>Insert PRJ_FILE_BIN Data Bindding JavaBean
     * @param bean PRJ_FILE_BIN Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(PrjFileBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" PRJ_FILE_BIN(");
            sql.addSql("   PDR_SID,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   PFL_EXT,");
            sql.addSql("   PFL_FILE_SIZE");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getPdrSid());
            sql.addLongValue(bean.getBinSid());
            sql.addStrValue(bean.getPflExt());
            sql.addLongValue(bean.getPflFileSize());
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
     * <p>Update PRJ_FILE_BIN Data Bindding JavaBean
     * @param bean PRJ_FILE_BIN Data Bindding JavaBean
     * @return count 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(PrjFileBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   PRJ_FILE_BIN");
            sql.addSql(" set ");
            sql.addSql("   PFL_EXT=?,");
            sql.addSql("   PFL_FILE_SIZE=?");
            sql.addSql(" where ");
            sql.addSql("   PDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getPflExt());
            sql.addLongValue(bean.getPflFileSize());
            //where
            sql.addIntValue(bean.getPdrSid());
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
     * <p>Update PRJ_FILE_BIN Data Bindding JavaBean
     * @param bean PRJ_FILE_BIN Data Bindding JavaBean
     * @return count 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateFile(PrjFileBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   PRJ_FILE_BIN");
            sql.addSql(" set ");
            sql.addSql("   PFL_EXT=?,");
            sql.addSql("   PFL_FILE_SIZE=?");
            sql.addSql(" where ");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getPflExt());
            sql.addLongValue(bean.getPflFileSize());
            //where
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
     * <p>Select PRJ_FILE_BIN All Data
     * @return List in PRJ_FILE_BINModel
     * @throws SQLException SQL実行例外
     */
    public List<PrjFileBinModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<PrjFileBinModel> ret = new ArrayList<PrjFileBinModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   PDR_SID,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   PFL_EXT,");
            sql.addSql("   PFL_FILE_SIZE");
            sql.addSql(" from ");
            sql.addSql("   PRJ_FILE_BIN");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getPrjFileBinFromRs(rs));
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
     * <p>Select PRJ_FILE_BIN
     * @param bean PRJ_FILE_BIN Model
     * @return PRJ_FILE_BINModel
     * @throws SQLException SQL実行例外
     */
    public PrjFileBinModel select(PrjFileBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        PrjFileBinModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   PDR_SID,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   PFL_EXT,");
            sql.addSql("   PFL_FILE_SIZE");
            sql.addSql(" from");
            sql.addSql("   PRJ_FILE_BIN");
            sql.addSql(" where ");
            sql.addSql("   PDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getPdrSid());
            sql.addLongValue(bean.getBinSid());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getPrjFileBinFromRs(rs);
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
     * <p>Delete PRJ_FILE_BIN
     * @param bean PRJ_FILE_BIN Model
     * @return count delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(PrjFileBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   PRJ_FILE_BIN");
            sql.addSql(" where ");
            sql.addSql("   PDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getPdrSid());
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
     * <p>Delete PRJ_FILE_BIN
     * @param bean PRJ_FILE_BIN Model
     * @return count delete count
     * @throws SQLException SQL実行例外
     */
    public int deleteFile(PrjFileBinModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   PRJ_FILE_BIN");
            sql.addSql(" where ");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
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
     * <br>[機  能] 指定されたファイル(複数)を削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param delList 削除対象のディレクトリ情報モデルリスト
     * @throws SQLException 例外
     */
    public void deletePluralFile(ArrayList<PrjDirectoryModel> delList)
        throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   PRJ_FILE_BIN");
            sql.addSql(" where");
            sql.addSql("   BIN_SID in (");

            for (int i = 0; i < delList.size(); i++) {

                PrjDirectoryModel bean = (PrjDirectoryModel) delList.get(i);
                sql.addSql("?");
                sql.addLongValue(bean.getBinSid());

                if (i != delList.size() - 1) {
                    sql.addSql(", ");
                }
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
     * <p>Create PRJ_FILE_BIN Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created PrjFileBinModel
     * @throws SQLException SQL実行例外
     */
    private PrjFileBinModel __getPrjFileBinFromRs(ResultSet rs) throws SQLException {
        PrjFileBinModel bean = new PrjFileBinModel();
        bean.setPdrSid(rs.getInt("PDR_SID"));
        bean.setBinSid(rs.getLong("BIN_SID"));
        bean.setPflExt(rs.getString("PFL_EXT"));
        bean.setPflFileSize(rs.getLong("PFL_FILE_SIZE"));
        return bean;
    }
}