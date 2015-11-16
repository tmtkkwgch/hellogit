package jp.groupsession.v2.wml.dao.base;

import java.io.StringReader;
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
import jp.groupsession.v2.wml.model.base.WmlMaildataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>WML_MAILDATA Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class WmlMaildataDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(WmlMaildataDao.class);

    /**
     * <p>Default Constructor
     */
    public WmlMaildataDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public WmlMaildataDao(Connection con) {
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
            sql.addSql("drop table WML_MAILDATA");

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
     * <p>Insert WML_MAILDATA Data Bindding JavaBean
     * @param bean WML_MAILDATA Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(WmlMaildataModel bean) throws SQLException {

        PreparedStatement pstmt = null;

        SqlBuffer sql = new SqlBuffer();
        StringReader reader = null;
        try {
            //SQL文
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" WML_MAILDATA(");
            sql.addSql("   WMD_MAILNUM,");
            sql.addSql("   WMD_TITLE,");
            sql.addSql("   WMD_SDATE,");
            sql.addSql("   WMD_FROM,");
            sql.addSql("   WMD_TEMPFLG,");
            sql.addSql("   WMD_STATUS,");
            sql.addSql("   WMD_REPLY,");
            sql.addSql("   WMD_FORWARD,");
            sql.addSql("   WMD_READED,");
            sql.addSql("   WDR_SID,");
            sql.addSql("   WMD_SIZE,");
            sql.addSql("   WAC_SID");
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
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.addLongValue(bean.getWmdMailnum());
            sql.addStrValue(bean.getWmdTitle());
            sql.addDateValue(bean.getWmdSdate());
            sql.addStrValue(bean.getWmdFrom());
            sql.addIntValue(bean.getWmdTempflg());
            sql.addIntValue(bean.getWmdStatus());
            sql.addIntValue(bean.getWmdReply());
            sql.addIntValue(bean.getWmdForward());
            sql.addIntValue(bean.getWmdReaded());
            sql.addLongValue(bean.getWdrSid());
            sql.addLongValue(bean.getWmdSize());
            sql.addIntValue(bean.getWacSid());
            log__.info(sql.toLogString());

            sql.setParameter(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
                reader = null;
            }

            JDBCUtil.closePreparedStatement(pstmt);
            pstmt = null;
            sql = null;
        }
    }

    /**
     * <p>Insert WML_MAILDATA Data Bindding JavaBean
     * @param beanList WML_MAILDATA Data List
     * @throws SQLException SQL実行例外
     */
    public void insert(List<WmlMaildataModel> beanList) throws SQLException {

        PreparedStatement pstmt = null;
        SqlBuffer sql = new SqlBuffer();

        if (beanList == null || beanList.size() <= 0) {
            return;
        }

        try {
            //SQL文
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" WML_MAILDATA(");
            sql.addSql("   WMD_MAILNUM,");
            sql.addSql("   WMD_TITLE,");
            sql.addSql("   WMD_SDATE,");
            sql.addSql("   WMD_FROM,");
            sql.addSql("   WMD_TEMPFLG,");
            sql.addSql("   WMD_STATUS,");
            sql.addSql("   WMD_REPLY,");
            sql.addSql("   WMD_FORWARD,");
            sql.addSql("   WMD_READED,");
            sql.addSql("   WDR_SID,");
            sql.addSql("   WMD_SIZE,");
            sql.addSql("   WAC_SID");
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
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = getCon().prepareStatement(sql.toSqlString());

            for (WmlMaildataModel bean : beanList) {
                sql.addLongValue(bean.getWmdMailnum());
                sql.addStrValue(bean.getWmdTitle());
                sql.addDateValue(bean.getWmdSdate());
                sql.addStrValue(bean.getWmdFrom());
                sql.addIntValue(bean.getWmdTempflg());
                sql.addIntValue(bean.getWmdStatus());
                sql.addIntValue(bean.getWmdReply());
                sql.addIntValue(bean.getWmdForward());
                sql.addIntValue(bean.getWmdReaded());
                sql.addLongValue(bean.getWdrSid());
                sql.addLongValue(bean.getWmdSize());
                sql.addIntValue(bean.getWacSid());

                log__.info(sql.toLogString());
                sql.setParameter(pstmt);
                pstmt.executeUpdate();

                sql.clearValue();
            }
        } catch (SQLException e) {
            throw e;
        } finally {

            JDBCUtil.closePreparedStatement(pstmt);
            pstmt = null;
            sql = null;
        }
    }

    /**
     * <p>Update WML_MAILDATA Data Bindding JavaBean
     * @param bean WML_MAILDATA Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(WmlMaildataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" set ");
            sql.addSql("   WMD_TITLE=?,");
            sql.addSql("   WMD_SDATE=?,");
            sql.addSql("   WMD_FROM=?,");
            sql.addSql("   WMD_TEMPFLG=?,");
            sql.addSql("   WMD_STATUS=?,");
            sql.addSql("   WMD_REPLY=?,");
            sql.addSql("   WMD_FORWARD=?,");
            sql.addSql("   WMD_READED=?,");
            sql.addSql("   WDR_SID=?,");
            sql.addSql("   WMD_SIZE=?");
            sql.addSql(" where ");
            sql.addSql("   WMD_MAILNUM=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getWmdTitle());
            sql.addDateValue(bean.getWmdSdate());
            sql.addStrValue(bean.getWmdFrom());
            sql.addIntValue(bean.getWmdTempflg());
            sql.addIntValue(bean.getWmdStatus());
            sql.addIntValue(bean.getWmdReply());
            sql.addIntValue(bean.getWmdForward());
            sql.addIntValue(bean.getWmdReaded());
            sql.addLongValue(bean.getWdrSid());
            sql.addLongValue(bean.getWmdSize());
            //where
            sql.addLongValue(bean.getWmdMailnum());

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
     * <p>Select WML_MAILDATA All Data
     * @return List in WML_MAILDATAModel
     * @throws SQLException SQL実行例外
     */
    public List<WmlMaildataModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<WmlMaildataModel> ret = new ArrayList<WmlMaildataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WMD_MAILNUM,");
            sql.addSql("   WMD_TITLE,");
            sql.addSql("   WMD_SDATE,");
            sql.addSql("   WMD_FROM,");
            sql.addSql("   WMD_TEMPFLG,");
            sql.addSql("   WMD_STATUS,");
            sql.addSql("   WMD_REPLY,");
            sql.addSql("   WMD_FORWARD,");
            sql.addSql("   WMD_READED,");
            sql.addSql("   WDR_SID,");
            sql.addSql("   WMD_SIZE,");
            sql.addSql("   WAC_SID");
            sql.addSql(" from ");
            sql.addSql("   WML_MAILDATA");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getWmlMaildataFromRs(rs));
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
     * <p>Select WML_MAILDATA limit Data
     * @param from 開始
     * @param to 終了
     * @return List in WML_MAILDATAModel
     * @throws SQLException SQL実行例外
     */
    public List<WmlMaildataModel> selectPart(
            long from, long to) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<WmlMaildataModel> ret = new ArrayList<WmlMaildataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WMD_MAILNUM,");
            sql.addSql("   WMD_TITLE,");
            sql.addSql("   WMD_SDATE,");
            sql.addSql("   WMD_FROM,");
            sql.addSql("   WMD_TEMPFLG,");
            sql.addSql("   WMD_STATUS,");
            sql.addSql("   WMD_REPLY,");
            sql.addSql("   WMD_FORWARD,");
            sql.addSql("   WMD_READED,");
            sql.addSql("   WDR_SID,");
            sql.addSql("   WMD_SIZE,");
            sql.addSql("   WAC_SID");
            sql.addSql(" from ");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where ");
            sql.addSql("   WMD_MAILNUM >= ?");
            sql.addSql(" and");
            sql.addSql("   WMD_MAILNUM <= ?");

            sql.addSql(" order by ");
            sql.addSql("   WMD_MAILNUM asc");

            sql.addLongValue(from);
            sql.addLongValue(to);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);

            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getWmlMaildataFromRs(rs));
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
     * <p>ディレクトリSIDを指定してメール一覧を取得します
     * @param dirSid ディレクトリSID
     * @return List in WML_MAILDATAModel
     * @throws SQLException SQL実行例外
     */
    public List<WmlMaildataModel> getMailDataOfDir(long dirSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<WmlMaildataModel> ret = new ArrayList<WmlMaildataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WMD_MAILNUM,");
            sql.addSql("   WMD_TITLE,");
            sql.addSql("   WMD_SDATE,");
            sql.addSql("   WMD_FROM,");
            sql.addSql("   WMD_TEMPFLG,");
            sql.addSql("   WMD_STATUS,");
            sql.addSql("   WMD_REPLY,");
            sql.addSql("   WMD_FORWARD,");
            sql.addSql("   WMD_READED,");
            sql.addSql("   WDR_SID,");
            sql.addSql("   WMD_SIZE,");
            sql.addSql("   WAC_SID");
            sql.addSql(" from ");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where ");
            sql.addSql("   WDR_SID=?");
            sql.addLongValue(dirSid);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getWmlMaildataFromRs(rs));
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
     * <p>ディレクトリSIDを指定してメール一覧を取得します
     * @param dirSid ディレクトリSID
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @return List in WML_MAILDATAModel
     * @throws SQLException SQL実行例外
     */
    public List<WmlMaildataModel> getMailDataOfDirLimit(
            long dirSid, int offset, int limit) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<WmlMaildataModel> ret = new ArrayList<WmlMaildataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WMD_MAILNUM,");
            sql.addSql("   WMD_TITLE,");
            sql.addSql("   WMD_SDATE,");
            sql.addSql("   WMD_FROM,");
            sql.addSql("   WMD_TEMPFLG,");
            sql.addSql("   WMD_STATUS,");
            sql.addSql("   WMD_REPLY,");
            sql.addSql("   WMD_FORWARD,");
            sql.addSql("   WMD_READED,");
            sql.addSql("   WDR_SID,");
            sql.addSql("   WMD_SIZE,");
            sql.addSql("   WAC_SID");
            sql.addSql(" from ");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where ");
            sql.addSql("   WDR_SID=?");
            sql.addSql(" order by ");
            sql.addSql("   WMD_MAILNUM asc");

            sql.addLongValue(dirSid);
            sql.setPagingValue(offset, limit);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getWmlMaildataFromRs(rs));
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
     * <p>ディレクトリSIDを指定してメール一覧の件数を取得します
     * @param dirSid ディレクトリSID
     * @return List in WML_MAILDATAModel
     * @throws SQLException SQL実行例外
     */
    public int countMailDataOfDir(long dirSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(*) as CNT");
            sql.addSql(" from ");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where ");
            sql.addSql("   WDR_SID=?");
            sql.addLongValue(dirSid);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("CNT");
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
     * <p>Select WML_MAILDATA
     * @param wmdMailnum WMD_MAILNUM
     * @return WML_MAILDATAModel
     * @throws SQLException SQL実行例外
     */
    public WmlMaildataModel select(long wmdMailnum) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        WmlMaildataModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   WMD_MAILNUM,");
            sql.addSql("   WMD_TITLE,");
            sql.addSql("   WMD_SDATE,");
            sql.addSql("   WMD_FROM,");
            sql.addSql("   WMD_TEMPFLG,");
            sql.addSql("   WMD_STATUS,");
            sql.addSql("   WMD_REPLY,");
            sql.addSql("   WMD_FORWARD,");
            sql.addSql("   WMD_READED,");
            sql.addSql("   WDR_SID,");
            sql.addSql("   WMD_SIZE,");
            sql.addSql("   WAC_SID");
            sql.addSql(" from");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where ");
            sql.addSql("   WMD_MAILNUM=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addLongValue(wmdMailnum);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getWmlMaildataFromRs(rs);
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
     * <p>Delete WML_MAILDATA
     * @param wmdMailnum WMD_MAILNUM
     * @return delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(long wmdMailnum) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where ");
            sql.addSql("   WMD_MAILNUM=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addLongValue(wmdMailnum);

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
     * <br>[機  能] レコード件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wmdMailnum メッセージ番号
     * @return 件数
     * @throws SQLException SQL実行時例外
     */
    public int selectMailCnt(long wmdMailnum) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select count(*) as CNT");
            sql.addSql(" from WML_MAILDATA");
            sql.addSql(" where WMD_MAILNUM=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addLongValue(wmdMailnum);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("CNT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] 指定したディレクトリ内のメールの件数を取得するレコード件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wdrSid ディレクトリSID
     * @return 件数
     * @throws SQLException SQL実行時例外
     */
    public int selectMailCntInDir(long wdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select count(*) as CNT");
            sql.addSql(" from WML_MAILDATA");
            sql.addSql(" where WDR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addLongValue(wdrSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("CNT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] 送信日時を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wmdMailnum メッセージ番号
     * @return 送信日時
     * @throws SQLException SQL実行例外
     */
    public UDate getSdate(long wmdMailnum) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        UDate sdate = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   WMD_SDATE");
            sql.addSql(" from");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where ");
            sql.addSql("   WMD_MAILNUM=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addLongValue(wmdMailnum);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sdate = UDate.getInstanceTimestamp(rs.getTimestamp("WMD_SDATE"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return sdate;
    }

    /**
     * <br>[機  能] 指定したメールの件名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNumList メッセージ番号
     * @return 指定したメールの件名
     * @throws SQLException SQL実行例外
     */
    public List<String> getMailSubject(long[] mailNumList) throws SQLException {

        List<String> mailSubject = new ArrayList<String>();
        if (mailNumList == null || mailNumList.length == 0) {
            return mailSubject;
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   WMD_TITLE");
            sql.addSql(" from");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where ");
            sql.addSql("   WMD_MAILNUM in (");

            for (int idx = 0; idx < mailNumList.length; idx++) {
                if (idx > 0) {
                    sql.addSql("     ,?");
                } else {
                    sql.addSql("     ?");
                }
                sql.addLongValue(mailNumList[idx]);
            }

            sql.addSql("   )");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                mailSubject.add(rs.getString("WMD_TITLE"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return mailSubject;
    }

    /**
     * <p>メールの件数を取得します
     * @return int 件数
     * @throws SQLException SQL実行例外
     */
    public long count() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        long ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(*) as CNT");
            sql.addSql(" from ");
            sql.addSql("   WML_MAILDATA");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getLong("CNT");
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
     * <p>メールの件数を取得します
     * @return int 件数
     * @throws SQLException SQL実行例外
     */
    public long maxMailNum() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        long ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   max(WMD_MAILNUM) as MAX");
            sql.addSql(" from ");
            sql.addSql("   WML_MAILDATA");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getLong("MAX");
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
     * <p>Create WML_MAILDATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created WmlMaildataModel
     * @throws SQLException SQL実行例外
     */
    private WmlMaildataModel __getWmlMaildataFromRs(ResultSet rs) throws SQLException {
        WmlMaildataModel bean = new WmlMaildataModel();
        bean.setWmdMailnum(rs.getLong("WMD_MAILNUM"));
        bean.setWmdTitle(rs.getString("WMD_TITLE"));
        bean.setWmdSdate(UDate.getInstanceTimestamp(rs.getTimestamp("WMD_SDATE")));
        bean.setWmdFrom(rs.getString("WMD_FROM"));
        bean.setWmdTempflg(rs.getInt("WMD_TEMPFLG"));
        bean.setWmdStatus(rs.getInt("WMD_STATUS"));
        bean.setWmdReply(rs.getInt("WMD_REPLY"));
        bean.setWmdForward(rs.getInt("WMD_FORWARD"));
        bean.setWmdReaded(rs.getInt("WMD_READED"));
        bean.setWdrSid(rs.getLong("WDR_SID"));
        bean.setWmdSize(rs.getLong("WMD_SIZE"));
        bean.setWacSid(rs.getInt("WAC_SID"));
        return bean;
    }
}
