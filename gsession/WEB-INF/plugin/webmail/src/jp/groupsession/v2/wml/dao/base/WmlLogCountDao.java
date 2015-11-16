package jp.groupsession.v2.wml.dao.base;

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
import jp.groupsession.v2.wml.model.base.WmlLogCountModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>WML_LOG_COUNT Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class WmlLogCountDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(WmlLogCountDao.class);

    /**
     * <p>Default Constructor
     */
    public WmlLogCountDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public WmlLogCountDao(Connection con) {
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
            sql.addSql("drop table WML_LOG_COUNT");

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
            sql.addSql(" create table WML_LOG_COUNT (");
            sql.addSql("   WAC_SID NUMBER(10,0) not null,");
            sql.addSql("   WLC_KBN NUMBER(10,0) not null,");
            sql.addSql("   WLC_CNT_TO NUMBER(10,0) not null,");
            sql.addSql("   WLC_CNT_CC NUMBER(10,0) not null,");
            sql.addSql("   WLC_CNT_BCC NUMBER(10,0) not null,");
            sql.addSql("   WLC_DATE varchar(23) not null");
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
     * <p>Insert WML_LOG_COUNT Data Bindding JavaBean
     * @param bean WML_LOG_COUNT Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(WmlLogCountModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" WML_LOG_COUNT(");
            sql.addSql("   WAC_SID,");
            sql.addSql("   WLC_KBN,");
            sql.addSql("   WLC_CNT_TO,");
            sql.addSql("   WLC_CNT_CC,");
            sql.addSql("   WLC_CNT_BCC,");
            sql.addSql("   WLC_DATE");
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
            sql.addIntValue(bean.getWacSid());
            sql.addIntValue(bean.getWlcKbn());
            sql.addIntValue(bean.getWlcCntTo());
            sql.addIntValue(bean.getWlcCntCc());
            sql.addIntValue(bean.getWlcCntBcc());
            sql.addDateValue(bean.getWlcDate());
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
     * <p>Update WML_LOG_COUNT Data Bindding JavaBean
     * @param bean WML_LOG_COUNT Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(WmlLogCountModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   WML_LOG_COUNT");
            sql.addSql(" set ");
            sql.addSql("   WAC_SID=?,");
            sql.addSql("   WLC_KBN=?,");
            sql.addSql("   WLC_CNT_TO=?,");
            sql.addSql("   WLC_CNT_CC=?,");
            sql.addSql("   WLC_CNT_BCC=?,");
            sql.addSql("   WLC_DATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getWacSid());
            sql.addIntValue(bean.getWlcKbn());
            sql.addIntValue(bean.getWlcCntTo());
            sql.addIntValue(bean.getWlcCntCc());
            sql.addIntValue(bean.getWlcCntBcc());
            sql.addDateValue(bean.getWlcDate());

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
     * <p>Select WML_LOG_COUNT All Data
     * @return List in WML_LOG_COUNTModel
     * @throws SQLException SQL実行例外
     */
    public List<WmlLogCountModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<WmlLogCountModel> ret = new ArrayList<WmlLogCountModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WAC_SID,");
            sql.addSql("   WLC_KBN,");
            sql.addSql("   WLC_CNT_TO,");
            sql.addSql("   WLC_CNT_CC,");
            sql.addSql("   WLC_CNT_BCC,");
            sql.addSql("   WLC_DATE");
            sql.addSql(" from ");
            sql.addSql("   WML_LOG_COUNT");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getWmlLogCountFromRs(rs));
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
     * <br>[機  能] 削除を行う
     * <br>[解  説]
     * <br>[備  考] 現在日から指定した年、月前のデータを削除する
     * @param year 年
     * @param month 月
     * @return 削除件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteLogCount(int year, int month) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //削除基準となる日時を生成
            UDate delDate = new UDate();
            delDate.addYear(year * -1);
            delDate.addMonth(month * -1);
            delDate.setMaxHhMmSs();

            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete from");
            sql.addSql("   WML_LOG_COUNT");
            sql.addSql(" where ");
            sql.addSql("   WLC_DATE <= ?");
            sql.addDateValue(delDate);

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
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
     * <p>Create WML_LOG_COUNT Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created WmlLogCountModel
     * @throws SQLException SQL実行例外
     */
    private WmlLogCountModel __getWmlLogCountFromRs(ResultSet rs) throws SQLException {
        WmlLogCountModel bean = new WmlLogCountModel();
        bean.setWacSid(rs.getInt("WAC_SID"));
        bean.setWlcKbn(rs.getInt("WLC_KBN"));
        bean.setWlcCntTo(rs.getInt("WLC_CNT_TO"));
        bean.setWlcCntCc(rs.getInt("WLC_CNT_CC"));
        bean.setWlcCntBcc(rs.getInt("WLC_CNT_BCC"));
        bean.setWlcDate(UDate.getInstanceTimestamp(rs.getTimestamp("WLC_DATE")));
        return bean;
    }
}
