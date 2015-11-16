package jp.groupsession.v2.tcd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>TCD_TIMEZONE Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class TcdTimezoneDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(TcdTimezoneDao.class);

    /**
     * <p>Default Constructor
     */
    public TcdTimezoneDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public TcdTimezoneDao(Connection con) {
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
            sql.addSql("drop table TCD_TIMEZONE");

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
            sql.addSql(" create table TCD_TIMEZONE (");
            sql.addSql("   TTZ_SID NUMBER(10,0) not null,");
            sql.addSql("   TTZ_KBN NUMBER(10,0) not null,");
            sql.addSql("   TTZ_FRTIME varchar(8) not null,");
            sql.addSql("   TTZ_TOTIME varchar(8) not null,");
            sql.addSql("   primary key (TTZ_SID)");
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
     * <p>Insert TCD_TIMEZONE Data Bindding JavaBean
     * @param bean TCD_TIMEZONE Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(TcdTimezoneModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" TCD_TIMEZONE(");
            sql.addSql("   TTZ_SID,");
            sql.addSql("   TTZ_KBN,");
            sql.addSql("   TTZ_FRTIME,");
            sql.addSql("   TTZ_TOTIME");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getTtzSid());
            sql.addIntValue(bean.getTtzKbn());
            if (bean.getTtzFrtime() != null) {
                sql.addDateValue(UDate.getInstance(bean.getTtzFrtime().getTime()));
            } else {
                sql.addDateValue((UDate) null);
            }
            if (bean.getTtzTotime() != null) {
                sql.addDateValue(UDate.getInstance(bean.getTtzTotime().getTime()));
            } else {
                sql.addDateValue((UDate) null);
            }
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
     * <p>Update TCD_TIMEZONE Data Bindding JavaBean
     * @param bean TCD_TIMEZONE Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return int 更新件数
     */
    public int update(TcdTimezoneModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" set ");
            sql.addSql("   TTZ_KBN=?,");
            sql.addSql("   TTZ_FRTIME=?,");
            sql.addSql("   TTZ_TOTIME=?");
            sql.addSql(" where ");
            sql.addSql("   TTZ_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getTtzKbn());
            if (bean.getTtzFrtime() != null) {
                sql.addDateValue(UDate.getInstance(bean.getTtzFrtime().getTime()));
            } else {
                sql.addDateValue((UDate) null);
            }
            if (bean.getTtzTotime() != null) {
                sql.addDateValue(UDate.getInstance(bean.getTtzTotime().getTime()));
            } else {
                sql.addDateValue((UDate) null);
            }
            //where
            sql.addIntValue(bean.getTtzSid());

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
     * <p>Select TCD_TIMEZONE All Data
     * @return List in TCD_TIMEZONEModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<TcdTimezoneModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<TcdTimezoneModel> ret = new ArrayList<TcdTimezoneModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   TTZ_SID,");
            sql.addSql("   TTZ_KBN,");
            sql.addSql("   TTZ_FRTIME,");
            sql.addSql("   TTZ_TOTIME");
            sql.addSql(" from ");
            sql.addSql("   TCD_TIMEZONE");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getTcdTimezoneFromRs(rs));
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
     * <p>時間帯区分を指定し開始時間の最小値を取得します。
     * @param kbn 時間帯区分
     * @return Time 開始時間の最小値
     * @throws SQLException SQL実行例外
     */
    public Time getFrTimeMin(int kbn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Time ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   min(TTZ_FRTIME) FRTIME");
            sql.addSql(" from ");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" where ");
            sql.addSql("   TTZ_KBN=?");

            sql.addIntValue(kbn);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getTime("FRTIME");
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
     * <p>時間帯区分を指定し終了時間の最大値を取得します。
     * @param kbn 時間帯区分
     * @return Time 終了時間の最大値
     * @throws SQLException SQL実行例外
     */
    public Time getToTimeMax(int kbn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Time ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   max(TTZ_TOTIME) TOTIME");
            sql.addSql(" from ");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" where ");
            sql.addSql("   TTZ_KBN=?");

            sql.addIntValue(kbn);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getTime("TOTIME");
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
     * <p>Select TCD_TIMEZONE All Data 区分＞開始 順
     * @param order ソート
     * @return List in TCD_TIMEZONEModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<TcdTimezoneModel> selectOrder(boolean order) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<TcdTimezoneModel> ret = new ArrayList<TcdTimezoneModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   TTZ_SID,");
            sql.addSql("   TTZ_KBN,");
            sql.addSql("   TTZ_FRTIME,");
            sql.addSql("   TTZ_TOTIME");
            sql.addSql(" from ");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" order by ");
            if (order) {
                sql.addSql("   TTZ_KBN,");
                sql.addSql("   TTZ_FRTIME,");
                sql.addSql("   TTZ_SID");
            } else {
                sql.addSql("   TTZ_FRTIME");
            }


            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getTcdTimezoneFromRs(rs));
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
     * <p>Select TCD_TIMEZONE All Data 区分＞開始 順
     * @param sid SID
     * @return List in TCD_TIMEZONEModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<TcdTimezoneModel> selectNotSid(int sid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<TcdTimezoneModel> ret = new ArrayList<TcdTimezoneModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   TTZ_SID,");
            sql.addSql("   TTZ_KBN,");
            sql.addSql("   TTZ_FRTIME,");
            sql.addSql("   TTZ_TOTIME");
            sql.addSql(" from ");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" where ");
            sql.addSql("   TTZ_SID<>?");
            sql.addIntValue(sid);

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getTcdTimezoneFromRs(rs));
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
     * <p>Select TCD_TIMEZONE All Data 区分＞開始 順
     * @param t1 開始時間
     * @param t2 終了時間
     * @param sid 除外するSID
     * @return List in TCD_TIMEZONEModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<TcdTimezoneModel> selectBetween(Time t1, Time t2, int sid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<TcdTimezoneModel> ret = new ArrayList<TcdTimezoneModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("select");
            sql.addSql("   TTZ_SID,");
            sql.addSql("   TTZ_KBN,");
            sql.addSql("   TTZ_FRTIME,");
            sql.addSql("   TTZ_TOTIME");
            sql.addSql(" from");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" where");
            sql.addSql("   TTZ_SID<>?");
            sql.addSql(" and");
            sql.addSql("(");
            sql.addSql("  (");
            sql.addSql("     TTZ_FRTIME <= ?");
            sql.addSql("   and");
            sql.addSql("     TTZ_TOTIME > ?");
            sql.addSql("  )");
            sql.addSql(" or");
            sql.addSql("  (");
            sql.addSql("     TTZ_FRTIME < ?");
            sql.addSql("   and");
            sql.addSql("     TTZ_TOTIME > ?");
            sql.addSql("  )");
            sql.addSql(" or");
            sql.addSql("  (");
            sql.addSql("     TTZ_FRTIME >= ?");
            sql.addSql("   and");
            sql.addSql("     TTZ_TOTIME <= ?");
            sql.addSql("  )");
            sql.addSql(")");
            sql.addIntValue(sid);
            sql.addTimeValue(t1);
            sql.addTimeValue(t1);
            sql.addTimeValue(t2);
            sql.addTimeValue(t2);
            sql.addTimeValue(t1);
            sql.addTimeValue(t2);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getTcdTimezoneFromRs(rs));
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
     * <p>Select TCD_TIMEZONE All Data 区分＞開始 順
     * @param d1 開始時間
     * @param d2 終了時間
     * @param sid 除外するSID
     * @return List in TCD_TIMEZONEModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<TcdTimezoneModel> selectBetween(UDate d1, UDate d2, int sid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<TcdTimezoneModel> ret = new ArrayList<TcdTimezoneModel>();
        con = getCon();
        //
        Time dspFrtime = new Time(d1.getTime());
        Time dspTotime = new Time(d2.getTime());
        //fromの1分プラス時間
        UDate dm1 = d1.cloneUDate();
        dm1.addMinute(1);
        Time dspFrtimePr = new Time(dm1.getTime());
        //toの1分マイナス時間
        UDate dm2 = d2.cloneUDate();
        dm2.addMinute(-1);
        Time dspTotimeMi = new Time(dm2.getTime());
        //00:00
        int hour = 0;
        int minute = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("select");
            sql.addSql("   TTZ_SID,");
            sql.addSql("   TTZ_KBN,");
            sql.addSql("   TTZ_FRTIME,");
            sql.addSql("   TTZ_TOTIME");
            sql.addSql(" from");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" where");
            sql.addSql("   TTZ_SID<>?");
            sql.addSql(" and");
            sql.addSql(" ( ");
            sql.addSql("   ( ");
            sql.addSql("   TTZ_FRTIME between ? ");
            sql.addSql("   and ? ");
            sql.addSql("   ) ");
            sql.addSql("   or ( ");
            sql.addSql("   TTZ_TOTIME between ? ");
            sql.addSql("   and ? ");
            sql.addSql("   ) ");
            sql.addSql("   or ( ");
            sql.addSql("   TTZ_FRTIME < ? ");
            sql.addSql("   and TTZ_TOTIME > ? ");
            sql.addSql("   )");
            sql.addSql("   or ( ");
            sql.addSql("   EXTRACT(hour from TTZ_TOTIME) = ? ");
            sql.addSql("   and EXTRACT(minute from TTZ_TOTIME) = ? ");
//            sql.addSql("   hour(TTZ_TOTIME) = ? ");
//            sql.addSql("   and minute(TTZ_TOTIME) = ? ");
            sql.addSql("   )");
            sql.addSql(" )");
            sql.addIntValue(sid);
            sql.addTimeValue(dspFrtime);
            sql.addTimeValue(dspTotimeMi);
            sql.addTimeValue(dspFrtimePr);
            sql.addTimeValue(dspTotime);
            sql.addTimeValue(dspFrtime);
            sql.addTimeValue(dspTotime);
            sql.addIntValue(hour);
            sql.addIntValue(minute);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getTcdTimezoneFromRs(rs));
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
     * <p>Select TCD_TIMEZONE
     * @param bean TCD_TIMEZONE Model
     * @return TCD_TIMEZONEModel
     * @throws SQLException SQL実行例外
     */
    public TcdTimezoneModel select(TcdTimezoneModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TcdTimezoneModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   TTZ_SID,");
            sql.addSql("   TTZ_KBN,");
            sql.addSql("   TTZ_FRTIME,");
            sql.addSql("   TTZ_TOTIME");
            sql.addSql(" from");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" where ");
            sql.addSql("   TTZ_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getTtzSid());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getTcdTimezoneFromRs(rs);
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
     * <p>Select TCD_TIMEZONE
     * @param sid SID
     * @return TCD_TIMEZONEModel
     * @throws SQLException SQL実行例外
     */
    public TcdTimezoneModel select(int sid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        TcdTimezoneModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   TTZ_SID,");
            sql.addSql("   TTZ_KBN,");
            sql.addSql("   TTZ_FRTIME,");
            sql.addSql("   TTZ_TOTIME");
            sql.addSql(" from");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" where ");
            sql.addSql("   TTZ_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getTcdTimezoneFromRs(rs);
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
     * <p>Delete TCD_TIMEZONE
     * @param bean TCD_TIMEZONE Model
     * @throws SQLException SQL実行例外
     * @return int 削除件数
     */
    public  int delete(TcdTimezoneModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" where ");
            sql.addSql("   TTZ_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getTtzSid());

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
     * <p>Delete TCD_TIMEZONE
     * @param sid SID
     * @throws SQLException SQL実行例外
     * @return int 削除件数
     */
    public int delete(int sid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   TCD_TIMEZONE");
            sql.addSql(" where ");
            sql.addSql("   TTZ_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sid);

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
     * <p>Create TCD_TIMEZONE Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created TcdTimezoneModel
     * @throws SQLException SQL実行例外
     */
    private TcdTimezoneModel __getTcdTimezoneFromRs(ResultSet rs) throws SQLException {
        TcdTimezoneModel bean = new TcdTimezoneModel();
        bean.setTtzSid(rs.getInt("TTZ_SID"));
        bean.setTtzKbn(rs.getInt("TTZ_KBN"));
        bean.setTtzFrtime(rs.getTime("TTZ_FRTIME"));
        bean.setTtzTotime(rs.getTime("TTZ_TOTIME"));
        return bean;
    }
}
