package jp.groupsession.v2.wml.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.wml.model.base.WmlLabelModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>WML_LABEL Data Access Object
 *
 * @author JTS DaoGenerator version 0.5
 */
public class WmlLabelDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(WmlLabelDao.class);

    /**
     * <p>Default Constructor
     */
    public WmlLabelDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public WmlLabelDao(Connection con) {
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
            sql.addSql("drop table WML_LABEL");

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
            sql.addSql(" create table WML_LABEL (");
            sql.addSql("   WLB_SID NUMBER(10,0) not null,");
            sql.addSql("   USR_SID NUMBER(10,0) not null,");
            sql.addSql("   WLB_NAME varchar(300) not null,");
            sql.addSql("   WLB_TYPE NUMBER(10,0) not null,");
            sql.addSql("   WLB_ORDER NUMBER(10,0) not null,");
            sql.addSql("   WAC_SID NUMBER(10,0) not null,");
            sql.addSql("   primary key (WLB_SID)");
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
     * <p>Insert WML_LABEL Data Bindding JavaBean
     * @param bean WML_LABEL Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(WmlLabelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" WML_LABEL(");
            sql.addSql("   WLB_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   WLB_NAME,");
            sql.addSql("   WLB_TYPE,");
            sql.addSql("   WLB_ORDER,");
            sql.addSql("   WAC_SID");
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
            sql.addIntValue(bean.getWlbSid());
            sql.addIntValue(bean.getUsrSid());
            sql.addStrValue(bean.getWlbName());
            sql.addIntValue(bean.getWlbType());
            sql.addIntValue(bean.getWlbOrder());
            sql.addIntValue(bean.getWacSid());
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
     * <p>Update WML_LABEL Data Bindding JavaBean
     * @param bean WML_LABEL Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(WmlLabelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   WML_LABEL");
            sql.addSql(" set ");
            sql.addSql("   USR_SID=?,");
            sql.addSql("   WLB_NAME=?,");
            sql.addSql("   WLB_TYPE=?,");
            sql.addSql("   WLB_ORDER=?,");
            sql.addSql("   WAC_SID=?");
            sql.addSql(" where ");
            sql.addSql("   WLB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrSid());
            sql.addStrValue(bean.getWlbName());
            sql.addIntValue(bean.getWlbType());
            sql.addIntValue(bean.getWlbOrder());
            sql.addIntValue(bean.getWacSid());
            //where
            sql.addIntValue(bean.getWlbSid());

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
     * <p>並び順を更新する
     * @param wlbSid ラベルSID
     * @param wlbOrder 並び順
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateOrder(int wlbSid, int wlbOrder) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   WML_LABEL");
            sql.addSql(" set ");
            sql.addSql("   WLB_ORDER=?");
            sql.addSql(" where ");
            sql.addSql("   WLB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(wlbOrder);
            sql.addIntValue(wlbSid);

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
     * <p>Select WML_LABEL All Data
     * @return List in WML_LABELModel
     * @throws SQLException SQL実行例外
     */
    public List<WmlLabelModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<WmlLabelModel> ret = new ArrayList<WmlLabelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WLB_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   WLB_NAME,");
            sql.addSql("   WLB_TYPE,");
            sql.addSql("   WLB_ORDER,");
            sql.addSql("   WAC_SID");
            sql.addSql(" from ");
            sql.addSql("   WML_LABEL");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getWmlLabelFromRs(rs));
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
     * <p>Select WML_LABEL
     * @param wlbSid WLB_SID
     * @return WML_LABELModel
     * @throws SQLException SQL実行例外
     */
    public WmlLabelModel select(int wlbSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        WmlLabelModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   WLB_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   WLB_NAME,");
            sql.addSql("   WLB_TYPE,");
            sql.addSql("   WLB_ORDER,");
            sql.addSql("   WAC_SID");
            sql.addSql(" from");
            sql.addSql("   WML_LABEL");
            sql.addSql(" where ");
            sql.addSql("   WLB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(wlbSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getWmlLabelFromRs(rs);
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
     * <p>Delete WML_LABEL
     * @param wlbSid WLB_SID
     * @return delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(int wlbSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   WML_LABEL");
            sql.addSql(" where ");
            sql.addSql("   WLB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(wlbSid);

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
     * <br>[機  能] ラベル情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wacSid アカウントSID
     * @return ラベル情報一覧
     * @throws SQLException SQL実行時例外
     */
    public List<WmlLabelModel> getLabelList(int wacSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<WmlLabelModel> ret = new ArrayList<WmlLabelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WLB_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   WLB_NAME,");
            sql.addSql("   WLB_TYPE,");
            sql.addSql("   WLB_ORDER,");
            sql.addSql("   WAC_SID");
            sql.addSql(" from ");
            sql.addSql("   WML_LABEL");
            sql.addSql(" where ");
            sql.addSql("   (");
            sql.addSql("     WLB_TYPE = ?");
            sql.addSql("   and");
            sql.addSql("     WAC_SID = ?");
            sql.addSql("    )");
            sql.addSql(" or");
            sql.addSql("   (");
            sql.addSql("     WLB_TYPE = ?");
            sql.addSql("   and ");

            //「ラベルを登録したユーザが[使用者]として設定されているアカウント」のみを対象とする。
            sql.addSql("     (");
            sql.addSql("       WML_LABEL.USR_SID in (");
            sql.addSql("         select USR_SID from WML_ACCOUNT_USER");
            sql.addSql("         where WAC_SID = ?");
            sql.addSql("         and coalesce(USR_SID, 0) > 0");
            sql.addSql("       )");
            sql.addSql("      or");
            sql.addSql("       WML_LABEL.USR_SID in (");
            sql.addSql("         select CMN_BELONGM.USR_SID");
            sql.addSql("         from");
            sql.addSql("           WML_ACCOUNT_USER,");
            sql.addSql("           CMN_BELONGM");
            sql.addSql("         where WML_ACCOUNT_USER.WAC_SID = ?");
            sql.addSql("         and coalesce(WML_ACCOUNT_USER.GRP_SID, 0) > 0");
            sql.addSql("         and WML_ACCOUNT_USER.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   )");

            sql.addSql(" order by");
            sql.addSql("   WLB_ORDER asc");

            sql.addIntValue(GSConstWebmail.LABELTYPE_ONES);
            sql.addIntValue(wacSid);
            sql.addIntValue(GSConstWebmail.LABELTYPE_ALL);
            sql.addIntValue(wacSid);
            sql.addIntValue(wacSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getWmlLabelFromRs(rs));
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
     * <br>[機  能] 指定したユーザが作成したラベルの一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param wlbType ラベル種別
     * @return ラベルの一覧
     * @throws SQLException SQL実行時例外
     */
    public List<Integer> getLabelList(int usrSid, int wlbType) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WLB_SID");
            sql.addSql(" from ");
            sql.addSql("   WML_LABEL");
            sql.addSql(" where ");
            sql.addSql("   USR_SID = ?");
            sql.addSql(" and ");
            sql.addSql("   WLB_TYPE = ?");

            sql.addIntValue(usrSid);
            sql.addIntValue(wlbType);
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt("WLB_SID"));
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
     * <br>[機  能] ラベルの並び順の現在最大値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wacSid アカウントSID
     * @return ラベル最大値
     * @throws SQLException SQL実行時例外
     */
    public int maxSortNumber(int wacSid)
    throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int maxNumber = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   max(WLB_ORDER) as MAX_ORDER");
            sql.addSql(" from");
            sql.addSql("   WML_LABEL");
            sql.addSql(" where");
            sql.addSql("   WAC_SID = ?");
            sql.addIntValue(wacSid);

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                maxNumber = rs.getInt("MAX_ORDER");
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return maxNumber;
    }

    /**
     * <p>Create WML_LABEL Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created WmlLabelModel
     * @throws SQLException SQL実行例外
     */
    private WmlLabelModel __getWmlLabelFromRs(ResultSet rs) throws SQLException {
        WmlLabelModel bean = new WmlLabelModel();
        bean.setWlbSid(rs.getInt("WLB_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setWlbName(rs.getString("WLB_NAME"));
        bean.setWlbType(rs.getInt("WLB_TYPE"));
        bean.setWlbOrder(rs.getInt("WLB_ORDER"));
        bean.setWacSid(rs.getInt("WAC_SID"));
        return bean;
    }
}
