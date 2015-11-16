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
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.base.CmnPluginControlDao;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.model.RngChannelModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>RNG_CHANNEL Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RngChannelDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RngChannelDao.class);

    /**
     * <p>Default Constructor
     */
    public RngChannelDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public RngChannelDao(Connection con) {
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
            sql.addSql("drop table RNG_CHANNEL");

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
            sql.addSql(" create table RNG_CHANNEL (");
            sql.addSql("   RNG_SID NUMBER(10,0) not null,");
            sql.addSql("   USR_SID NUMBER(10,0) not null,");
            sql.addSql("   RNC_SORT NUMBER(10,0) not null,");
            sql.addSql("   RNC_STATUS NUMBER(10,0) not null,");
            sql.addSql("   RNC_COMMENT varchar(300),");
            sql.addSql("   RNC_RCVDATE varchar(26) not null,");
            sql.addSql("   RNC_CHKDATE varchar(26) not null,");
            sql.addSql("   RNC_TYPE NUMBER(10,0) not null,");
            sql.addSql("   RNC_AUID NUMBER(10,0) not null,");
            sql.addSql("   RNC_ADATE varchar(26) not null,");
            sql.addSql("   RNC_EUID NUMBER(10,0) not null,");
            sql.addSql("   RNC_EDATE varchar(26) not null,");
            sql.addSql("   primary key (RNG_SID,USR_SID)");
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
     * <p>Insert RNG_CHANNEL Data Bindding JavaBean
     * @param bean RNG_CHANNEL Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(RngChannelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" RNG_CHANNEL(");
            sql.addSql("   RNG_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RNC_SORT,");
            sql.addSql("   RNC_STATUS,");
            sql.addSql("   RNC_COMMENT,");
            sql.addSql("   RNC_RCVDATE,");
            sql.addSql("   RNC_CHKDATE,");
            sql.addSql("   RNC_TYPE,");
            sql.addSql("   RNC_AUID,");
            sql.addSql("   RNC_ADATE,");
            sql.addSql("   RNC_EUID,");
            sql.addSql("   RNC_EDATE");
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

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRngSid());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getRncSort());
            sql.addIntValue(bean.getRncStatus());
            sql.addStrValue(bean.getRncComment());
            sql.addDateValue(bean.getRncRcvdate());
            sql.addDateValue(bean.getRncChkdate());
            sql.addIntValue(bean.getRncType());
            sql.addIntValue(bean.getRncAuid());
            sql.addDateValue(bean.getRncAdate());
            sql.addIntValue(bean.getRncEuid());
            sql.addDateValue(bean.getRncEdate());
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
     * <p>Insert RNG_CHANNEL Data Bindding JavaBean
     * @param beanList RNG_CHANNEL DataList
     * @throws SQLException SQL実行例外
     */
    public void insert(List<RngChannelModel> beanList) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        if (beanList == null || beanList.size() <= 0) {
            return;
        }
        
        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" RNG_CHANNEL(");
            sql.addSql("   RNG_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RNC_SORT,");
            sql.addSql("   RNC_STATUS,");
            sql.addSql("   RNC_COMMENT,");
            sql.addSql("   RNC_RCVDATE,");
            sql.addSql("   RNC_CHKDATE,");
            sql.addSql("   RNC_TYPE,");
            sql.addSql("   RNC_AUID,");
            sql.addSql("   RNC_ADATE,");
            sql.addSql("   RNC_EUID,");
            sql.addSql("   RNC_EDATE");
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

            pstmt = con.prepareStatement(sql.toSqlString());
            for (RngChannelModel bean : beanList) {
                sql.addIntValue(bean.getRngSid());
                sql.addIntValue(bean.getUsrSid());
                sql.addIntValue(bean.getRncSort());
                sql.addIntValue(bean.getRncStatus());
                sql.addStrValue(bean.getRncComment());
                sql.addDateValue(bean.getRncRcvdate());
                sql.addDateValue(bean.getRncChkdate());
                sql.addIntValue(bean.getRncType());
                sql.addIntValue(bean.getRncAuid());
                sql.addDateValue(bean.getRncAdate());
                sql.addIntValue(bean.getRncEuid());
                sql.addDateValue(bean.getRncEdate());
                log__.info(sql.toLogString());
                sql.setParameter(pstmt);
                pstmt.executeUpdate();
                sql.clearValue();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <p>Update RNG_CHANNEL Data Bindding JavaBean
     * @param bean RNG_CHANNEL Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return count
     */
    public int update(RngChannelModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" set ");
            sql.addSql("   RNC_SORT=?,");
            sql.addSql("   RNC_STATUS=?,");
            sql.addSql("   RNC_COMMENT=?,");
            sql.addSql("   RNC_RCVDATE=?,");
            sql.addSql("   RNC_CHKDATE=?,");
            sql.addSql("   RNC_TYPE=?,");
            sql.addSql("   RNC_AUID=?,");
            sql.addSql("   RNC_ADATE=?,");
            sql.addSql("   RNC_EUID=?,");
            sql.addSql("   RNC_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRncSort());
            sql.addIntValue(bean.getRncStatus());
            sql.addStrValue(bean.getRncComment());
            sql.addDateValue(bean.getRncRcvdate());
            sql.addDateValue(bean.getRncChkdate());
            sql.addIntValue(bean.getRncType());
            sql.addIntValue(bean.getRncAuid());
            sql.addDateValue(bean.getRncAdate());
            sql.addIntValue(bean.getRncEuid());
            sql.addDateValue(bean.getRncEdate());
            //where
            sql.addIntValue(bean.getRngSid());
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
     * <p>Select RNG_CHANNEL All Data
     * @return List in RNG_CHANNELModel
     * @throws SQLException SQL実行例外
     */
    public List<RngChannelModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<RngChannelModel> ret = new ArrayList<RngChannelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RNG_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RNC_SORT,");
            sql.addSql("   RNC_STATUS,");
            sql.addSql("   RNC_COMMENT,");
            sql.addSql("   RNC_RCVDATE,");
            sql.addSql("   RNC_CHKDATE,");
            sql.addSql("   RNC_TYPE,");
            sql.addSql("   RNC_AUID,");
            sql.addSql("   RNC_ADATE,");
            sql.addSql("   RNC_EUID,");
            sql.addSql("   RNC_EDATE");
            sql.addSql(" from ");
            sql.addSql("   RNG_CHANNEL");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngChannelFromRs(rs));
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
     * <p>Select RNG_CHANNEL All Data
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @return List in RNG_CHANNELModel
     * @throws SQLException SQL実行例外
     */
    public List<RngChannelModel> selectLimit(
            int offset, int limit) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<RngChannelModel> ret = new ArrayList<RngChannelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RNG_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RNC_SORT,");
            sql.addSql("   RNC_STATUS,");
            sql.addSql("   RNC_COMMENT,");
            sql.addSql("   RNC_RCVDATE,");
            sql.addSql("   RNC_CHKDATE,");
            sql.addSql("   RNC_TYPE,");
            sql.addSql("   RNC_AUID,");
            sql.addSql("   RNC_ADATE,");
            sql.addSql("   RNC_EUID,");
            sql.addSql("   RNC_EDATE");
            sql.addSql(" from ");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" order by ");
            sql.addSql("   RNG_SID asc,");
            sql.addSql("   USR_SID asc");
            sql.setPagingValue(offset, limit);

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngChannelFromRs(rs));
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
     * <p>count RNG_CHANNEL All Data
     * @return count
     * @throws SQLException SQL実行例外
     */
    public int count() throws SQLException {

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
            sql.addSql("   RNG_CHANNEL");

            pstmt = con.prepareStatement(sql.toSqlString());
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
     * <p>Select RNG_CHANNEL
     * @param rngSid 稟議SID
     * @param usrSid ユーザSID
     * @return RNG_CHANNELModel
     * @throws SQLException SQL実行例外
     */
    public RngChannelModel select(int rngSid, int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RngChannelModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RNG_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RNC_SORT,");
            sql.addSql("   RNC_STATUS,");
            sql.addSql("   RNC_COMMENT,");
            sql.addSql("   RNC_RCVDATE,");
            sql.addSql("   RNC_CHKDATE,");
            sql.addSql("   RNC_TYPE,");
            sql.addSql("   RNC_AUID,");
            sql.addSql("   RNC_ADATE,");
            sql.addSql("   RNC_EUID,");
            sql.addSql("   RNC_EDATE");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rngSid);
            sql.addIntValue(usrSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRngChannelFromRs(rs);
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
     * <p>Delete RNG_CHANNEL
     * @param rngSid 稟議SID
     * @throws SQLException SQL実行例外
     * @return count 削除件数
     */
    public int delete(int rngSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rngSid);

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
     * <br>[機  能] 指定した稟議の経路情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @return 稟議経路情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<RngChannelModel> getChannelList(int rngSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<RngChannelModel> ret = new ArrayList<RngChannelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RNG_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RNC_SORT,");
            sql.addSql("   RNC_STATUS,");
            sql.addSql("   RNC_COMMENT,");
            sql.addSql("   RNC_RCVDATE,");
            sql.addSql("   RNC_CHKDATE,");
            sql.addSql("   RNC_TYPE,");
            sql.addSql("   RNC_AUID,");
            sql.addSql("   RNC_ADATE,");
            sql.addSql("   RNC_EUID,");
            sql.addSql("   RNC_EDATE");
            sql.addSql(" from ");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" order by");
            sql.addSql("   RNC_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rngSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngChannelFromRs(rs));
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
     * <br>[機  能] 指定した稟議の経路情報を取得する(削除ユーザは除外する)
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @return 稟議経路情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<RngChannelModel> getChannelListWithoutDelUser(int rngSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<RngChannelModel> ret = new ArrayList<RngChannelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RNG_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RNC_SORT,");
            sql.addSql("   RNC_STATUS,");
            sql.addSql("   RNC_COMMENT,");
            sql.addSql("   RNC_RCVDATE,");
            sql.addSql("   RNC_CHKDATE,");
            sql.addSql("   RNC_TYPE,");
            sql.addSql("   RNC_AUID,");
            sql.addSql("   RNC_ADATE,");
            sql.addSql("   RNC_EUID,");
            sql.addSql("   RNC_EDATE");
            sql.addSql(" from ");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   USR_SID not in (");
            sql.addSql("     select USR_SID from CMN_USRM");
            sql.addSql("     where USR_JKBN = ?");
            sql.addSql("   )");
            sql.addSql(" order by");
            sql.addSql("   RNC_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rngSid);
            sql.addIntValue(GSConstUser.USER_JTKBN_DELETE);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngChannelFromRs(rs));
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
     * <br>[機  能] 指定した稟議の最終確認者のうち、メール送信対象者の一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param uConfFlg 個人設定反映フラグ true:反映する false:反映しない
     * @return 稟議ユーザSID一覧
     * @throws SQLException SQL実行例外
     */
    public List<Integer> getConfirmSmUserList(int rngSid, boolean uConfFlg) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   USR_SID");
            sql.addSql(" from ");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE = ?");
            if (uConfFlg) {
                sql.addSql(" and");
                sql.addSql("   USR_SID not in (");
                sql.addSql("     select USR_SID from RNG_UCONF");
                sql.addSql("     where RUR_SML_NTF = ?");
                sql.addSql("   )");
            }
            sql.addSql(" order by");
            sql.addSql("   RNC_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_CONFIRM);
            if (uConfFlg) {
                sql.addIntValue(RngConst.RNG_SMAIL_NOT_TSUUCHI);
            }
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(new Integer(rs.getInt("USR_SID")));
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
     * <br>[機  能] 指定したユーザが最初の承認者かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @return 判定結果 true:最初の承認者 false:２番目以降の承認者
     * @throws SQLException SQL実行例外
     */
    public boolean isFirstApprUser(int rngSid, int userSid) throws SQLException {

        return __checkApprUser(rngSid, userSid, 0);
    }

    /**
     * <br>[機  能] 指定したユーザが最後の承認者かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @return 判定結果 true:最後の承認者 false:最後の承認者ではない
     * @throws SQLException SQL実行例外
     */
    public boolean isLastApprUser(int rngSid, int userSid) throws SQLException {

        return __checkApprUser(rngSid, userSid, 1);
    }

    /**
     * <br>[機  能] 指定したユーザが目的の承認者かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @param checkType 判定内容 0:最初の承認者か 1:最後の承認者か
     * @return 判定結果 true:目的の承認者 false:目的の承認者ではない
     * @throws SQLException SQL実行例外
     */
    private boolean __checkApprUser(int rngSid, int userSid, int checkType) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   USR_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_SORT = (");
            if (checkType == 0) {
                sql.addSql("     select min(RNC_SORT) from RNG_CHANNEL");
            } else {
                sql.addSql("     select max(RNC_SORT) from RNG_CHANNEL");
            }
            sql.addSql("     where RNG_SID = ?");
            sql.addSql("     and RNC_TYPE = ?");
            sql.addSql("     group by RNG_SID, RNC_TYPE");
            sql.addSql("   )");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rngSid);
            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = (rs.getInt("USR_SID") == userSid);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return result;
    }

    /**
     * <br>[機  能] 指定した稟議を確認中のユーザが最終承認者かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @return 判定結果 true:最終承認者 false:最終承認者ではない
     * @throws SQLException SQL実行例外
     */
    public boolean isLastApproval(int rngSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   count(USR_SID) as CNT");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_STATUS = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_SORT >= (");
            sql.addSql("     select");
            sql.addSql("       max(RNG_CHANNEL.RNC_SORT)");
            sql.addSql("     from");
            sql.addSql("       CMN_USRM,");
            sql.addSql("       RNG_CHANNEL");
            sql.addSql("     where");
            sql.addSql("       RNG_CHANNEL.RNG_SID = ?");
            sql.addSql("     and");
            sql.addSql("       RNG_CHANNEL.RNC_TYPE = ?");
            sql.addSql("     and");
            sql.addSql("       RNG_CHANNEL.USR_SID = CMN_USRM.USR_SID");
            sql.addSql("     and");
            sql.addSql("       CMN_USRM.USR_JKBN = ?");
            sql.addSql("     group by");
            sql.addSql("       RNG_SID, RNC_TYPE");
            sql.addSql("   )");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(RngConst.RNG_RNCSTATUS_CONFIRM);
            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("CNT") > 0;
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return result;
    }

    /**
     * <br>[機  能] 指定したユーザが却下した承認者の前の承認者かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @return 判定結果 true:前の承認者 false:前の承認者ではない
     * @throws SQLException SQL実行例外
     */
    public boolean isBeforeApproval(int rngSid, int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   count(USR_SID) as CNT");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   USR_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_SORT < (");
            sql.addSql("     select max(RNC_SORT) from RNG_CHANNEL");
            sql.addSql("     where RNG_SID = ?");
            sql.addSql("     and RNC_TYPE = ?");
            sql.addSql("     and RNC_STATUS = ?");
            sql.addSql("     group by RNG_SID, RNC_TYPE");
            sql.addSql("   )");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rngSid);
            sql.addIntValue(userSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(RngConst.RNG_RNCSTATUS_DENIAL);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("CNT") > 0;
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return result;
    }

    /**
     * <br>[機  能] 却下した承認者の前の承認者、かつ通知対象ユーザSID一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param uConfFlg 個人設定反映フラグ true:反映する false:反映しない
     * @return 承認者ユーザSID一覧
     * @throws SQLException SQL実行例外
     */
    public List<Integer> getBeforeApprovalSmUserList(int rngSid, boolean uConfFlg)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   USR_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_SORT < (");
            sql.addSql("     select max(RNC_SORT) from RNG_CHANNEL");
            sql.addSql("     where RNG_SID = ?");
            sql.addSql("     and RNC_TYPE = ?");
            sql.addSql("     and RNC_STATUS = ?");
            sql.addSql("     group by RNG_SID, RNC_TYPE");
            sql.addSql("   )");
            if (uConfFlg) {
                sql.addSql(" and");
                sql.addSql("   USR_SID not in (");
                sql.addSql("     select USR_SID from RNG_UCONF");
                sql.addSql("     where RUR_SML_NTF = ?");
                sql.addSql("   )");
            }
            sql.addSql(" order by");
            sql.addSql("   RNC_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(RngConst.RNG_RNCSTATUS_DENIAL);
            if (uConfFlg) {
                sql.addIntValue(RngConst.RNG_SMAIL_NOT_TSUUCHI);
            }

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(new Integer(rs.getInt("USR_SID")));
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
     * <br>[機  能] 指定した稟議を確認中のユーザを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @return ユーザSID
     * @throws SQLException SQL実行例外
     */
    public int getApprovalUser(int rngSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int userSid = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   USR_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_STATUS = ?");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(RngConst.RNG_RNCSTATUS_CONFIRM);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                userSid = rs.getInt("USR_SID");
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return userSid;
    }

    /**
     * <br>[機  能] 指定した稟議を確認中、かつメール送信対象のユーザを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param uConfFlg 個人設定反映フラグ true:反映する false:反映しない
     * @return ユーザSID
     * @throws SQLException SQL実行例外
     */
    public int getApprovalSmUser(int rngSid, boolean uConfFlg) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int userSid = -1;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   USR_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_STATUS = ?");
            if (uConfFlg) {
                sql.addSql(" and");
                sql.addSql("   USR_SID not in (");
                sql.addSql("     select USR_SID from RNG_UCONF");
                sql.addSql("     where RUR_SML_NTF = ?");
                sql.addSql("   )");
            }

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(RngConst.RNG_RNCSTATUS_CONFIRM);
            if (uConfFlg) {
                sql.addIntValue(RngConst.RNG_SMAIL_NOT_TSUUCHI);
            }

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                userSid = rs.getInt("USR_SID");
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return userSid;
    }

    /**
     * <br>[機  能] 承認者ユーザSID一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @return ユーザSID
     * @throws SQLException SQL実行例外
     */
    public List<Integer> getApprUserList(int rngSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   USR_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE = ?");
            sql.addSql(" order by");
            sql.addSql("   RNC_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(new Integer(rs.getInt("USR_SID")));
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
     * <br>[機  能] 稟議経路情報の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param bean 稟議経路情報
     * @throws SQLException SQL実行例外
     * @return count 更新件数
     */
    public int updateChannel(RngChannelModel bean)
    throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" set ");
            sql.addSql("   RNC_STATUS=?,");
            sql.addSql("   RNC_COMMENT=?,");
            sql.addSql("   RNC_CHKDATE=?,");
            sql.addSql("   RNC_EUID=?,");
            sql.addSql("   RNC_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRncStatus());
            sql.addStrValue(bean.getRncComment());
            sql.addDateValue(bean.getRncChkdate());
            sql.addIntValue(bean.getRncEuid());
            sql.addDateValue(bean.getRncEdate());
            //where
            sql.addIntValue(bean.getRngSid());
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
     * <br>[機  能] 稟議経路情報(承認者)の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param bean 稟議経路情報
     * @throws SQLException SQL実行例外
     * @return count 更新件数
     */
    public int updateApprovalChannel(RngChannelModel bean)
    throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" set ");
            sql.addSql("   RNC_STATUS=?,");
            sql.addSql("   RNC_RCVDATE=?,");
            sql.addSql("   RNC_CHKDATE=?,");
            sql.addSql("   RNC_EUID=?,");
            sql.addSql("   RNC_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRncStatus());
            sql.addDateValue(bean.getRncRcvdate());
            sql.addDateValue(bean.getRncChkdate());
            sql.addIntValue(bean.getRncEuid());
            sql.addDateValue(bean.getRncEdate());
            //where
            sql.addIntValue(bean.getRngSid());
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
     * <br>[機  能] 最初の承認者の稟議経路情報を更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param bean 稟議経路情報
     * @throws SQLException SQL実行例外
     * @return count 更新件数
     */
    public int updateFirstChannel(RngChannelModel bean)
    throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" set ");
            sql.addSql("   RNC_STATUS=?,");
            sql.addSql("   RNC_RCVDATE=?,");
            sql.addSql("   RNC_CHKDATE=?,");
            sql.addSql("   RNC_EUID=?,");
            sql.addSql("   RNC_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID=?");
            sql.addSql(" and");
            sql.addSql("   RNC_SORT=?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRncStatus());
            sql.addDateValue(bean.getRncRcvdate());
            sql.addDateValue(bean.getRncChkdate());
            sql.addIntValue(bean.getRncEuid());
            sql.addDateValue(bean.getRncEdate());
            //where
            sql.addIntValue(bean.getRngSid());
            sql.addIntValue(1);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);

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
     * <br>[機  能] １つ前 または 次の稟議経路情報の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param bean 稟議経路情報
     * @param updateMode 更新モード 0:承認/否認時更新 1:前の/次の経路情報更新
     * @return count 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateNextChannel(RngChannelModel bean, int updateMode)
    throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" set ");
            sql.addSql("   RNC_STATUS=?,");
            sql.addSql("   RNC_RCVDATE=?,");
            sql.addSql("   RNC_CHKDATE=?,");
            sql.addSql("   RNC_EUID=?,");
            sql.addSql("   RNC_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   USR_SID = (");
            sql.addSql("   select USR_SID from RNG_CHANNEL");
            sql.addSql("   where");
            sql.addSql("     RNG_SID = ?");
            sql.addSql("   and");
            sql.addSql("     RNC_TYPE = ?");
            sql.addSql("   and");
            sql.addSql("     RNC_SORT = (");
            sql.addSql("     select");
            if (updateMode == 1) {
                sql.addSql("       RNC_SORT - 1");
            } else {
                sql.addSql("       RNC_SORT + 1");
            }
            sql.addSql("     from");
            sql.addSql("       RNG_CHANNEL");
            sql.addSql("     where");
            sql.addSql("       RNG_SID = ?");
            sql.addSql("     and");
            sql.addSql("       USR_SID = ?");
            sql.addSql("   )");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(bean.getRncStatus());
            sql.addDateValue(bean.getRncRcvdate());
            sql.addDateValue(bean.getRncChkdate());
            sql.addIntValue(bean.getRncEuid());
            sql.addDateValue(bean.getRncEdate());
            //where
            sql.addIntValue(bean.getRngSid());
            sql.addIntValue(bean.getRngSid());
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(bean.getRngSid());
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
     * <br>[機  能] 申請者の稟議経路情報を更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param bean 稟議経路情報
     * @return count 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateApplicateChannel(RngChannelModel bean)
    throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" set ");
            sql.addSql("   RNC_STATUS=?,");
            sql.addSql("   RNC_RCVDATE=?,");
            sql.addSql("   RNC_CHKDATE=?,");
            sql.addSql("   RNC_EUID=?,");
            sql.addSql("   RNC_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE = ?");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(bean.getRncStatus());
            sql.addDateValue(bean.getRncRcvdate());
            sql.addDateValue(bean.getRncChkdate());
            sql.addIntValue(bean.getRncEuid());
            sql.addDateValue(bean.getRncEdate());
            //where
            sql.addIntValue(bean.getRngSid());
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPL);

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
     * <br>[機  能] 次の承認者のユーザSIDを取得する
     * <br>[解  説]
     * <br>[備  考] 削除されたユーザは除く
     * @param bean RngChannelModel
     * @param mode 0:前の承認者(差し戻し等), 1:後の承認者(承認等)
     * @return beforUsrSid ひとつ前の承認者のユーザSID
     * @throws SQLException SQL実行例外
     */
    public int getNextApproval(RngChannelModel bean,
                                int mode) throws SQLException {

        Connection con = null;
        con = getCon();

        //稟議プラグインの使用制限を取得
        //プラグインアクセス設定を取得
        CmnPluginControlDao cntrlDao = new CmnPluginControlDao(con);
        CmnPluginControlModel cntrlModel = cntrlDao.select(RngConst.PLUGIN_ID_RINGI);

        //プラグインアクセス制限方法を取得
        boolean rngControl = false;
        int pctType = 0;
        if (cntrlModel != null) {
            if (cntrlModel.getPctKbn() == GSConstMain.PCT_KBN_MEMBER) {
                rngControl = true;
                pctType = cntrlModel.getPctType();
            }
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int nextUserSid = -1;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RNG_CHANNEL.USR_SID as USR_SID");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_CHANNEL.RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNG_CHANNEL.RNC_TYPE = ?");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID = RNG_CHANNEL.USR_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");
            sql.addIntValue(bean.getRngSid());
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);

            if (rngControl) {

                sql.addSql(" and");
                if (pctType == GSConstMain.PCT_TYPE_LIMIT) {
                    sql.addSql("   CMN_USRM.USR_SID in (");
                } else {
                    sql.addSql("   CMN_USRM.USR_SID not in (");
                }
                sql.addSql("     select");
                sql.addSql("       case CMN_PLUGIN_CONTROL_MEMBER.USR_SID");
                sql.addSql("       when -1 then CMN_BELONGM.USR_SID");
                sql.addSql("       else CMN_PLUGIN_CONTROL_MEMBER.USR_SID");
                sql.addSql("       end as MEMBER_SID");
                sql.addSql("     from");
                sql.addSql("       CMN_PLUGIN_CONTROL_MEMBER");
                sql.addSql("       left join");
                sql.addSql("         CMN_BELONGM");
                sql.addSql("       on");
                sql.addSql("         CMN_PLUGIN_CONTROL_MEMBER.GRP_SID = CMN_BELONGM.GRP_SID");
                sql.addSql("     where PCT_PID = ?");
                sql.addSql("       group by MEMBER_SID");
                sql.addSql("   )");
                sql.addStrValue(RngConst.PLUGIN_ID_RINGI);
            }

            sql.addSql(" and");

            if (mode == 0) {
                sql.addSql("   RNG_CHANNEL.RNC_SORT < (");
            } else {
                sql.addSql("   RNG_CHANNEL.RNC_SORT > (");
            }

            sql.addSql("     select");
            sql.addSql("       RNC_SORT");
            sql.addSql("     from");
            sql.addSql("       RNG_CHANNEL");
            sql.addSql("     where");
            sql.addSql("       RNG_SID = ?");
            sql.addSql("     and");
            sql.addSql("       USR_SID = ?");
            sql.addSql("   )");

            sql.addSql(" order by");
            if (mode == 0) {
                sql.addSql("   RNG_CHANNEL.RNC_SORT desc");
            } else {
                sql.addSql("   RNG_CHANNEL.RNC_SORT");
            }

            sql.addIntValue(bean.getRngSid());
            sql.addIntValue(bean.getUsrSid());

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                nextUserSid = rs.getInt("USR_SID");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return nextUserSid;
    }

    /**
     * <br>[機  能] 全ての最終確認者の受信日を更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param userSid ユーザSID(更新者SID)
     * @param now 現在日時
     * @return count 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateRcvdateForConfirmUser(int rngSid, int userSid, UDate now)
    throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" set ");
            sql.addSql("   RNC_RCVDATE=?,");
            sql.addSql("   RNC_EUID=?,");
            sql.addSql("   RNC_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE = ?");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addDateValue(now);
            sql.addIntValue(userSid);
            sql.addDateValue(now);

            //where
            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_CONFIRM);

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
     * <br>[機  能] 承認者(削除されたユーザ)の稟議経路情報を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @throws SQLException SQL実行例外
     * @return count 削除件数
     */
    public int deleteChannelForDelUser(int rngSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID=?");
            sql.addSql(" and");
            sql.addSql("   RNC_TYPE=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID not in (");
            sql.addSql("    select USR_SID from CMN_USRM");
            sql.addSql("    where USR_SID > ?");
            sql.addSql("    and USR_JKBN = ?");
            sql.addSql("   )");

            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
            sql.addIntValue(GSConstUser.USER_RESERV_SID);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            pstmt = con.prepareStatement(sql.toSqlString());

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
     * <br>[機  能] 稟議経路情報 並び順の更新を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @param sort 並び順
     * @return count 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateChannelSort(int rngSid, int userSid, int sort)
    throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" set ");
            sql.addSql("   RNC_SORT=?");
            sql.addSql(" where ");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   USR_SID = ?");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(sort);
            sql.addIntValue(rngSid);
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
     * <p>Create RNG_CHANNEL Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created RngChannelModel
     * @throws SQLException SQL実行例外
     */
    private RngChannelModel __getRngChannelFromRs(ResultSet rs) throws SQLException {
        RngChannelModel bean = new RngChannelModel();
        bean.setRngSid(rs.getInt("RNG_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setRncSort(rs.getInt("RNC_SORT"));
        bean.setRncStatus(rs.getInt("RNC_STATUS"));
        bean.setRncComment(rs.getString("RNC_COMMENT"));
        bean.setRncRcvdate(UDate.getInstanceTimestamp(rs.getTimestamp("RNC_RCVDATE")));
        bean.setRncChkdate(UDate.getInstanceTimestamp(rs.getTimestamp("RNC_CHKDATE")));
        bean.setRncType(rs.getInt("RNC_TYPE"));
        bean.setRncAuid(rs.getInt("RNC_AUID"));
        bean.setRncAdate(UDate.getInstanceTimestamp(rs.getTimestamp("RNC_ADATE")));
        bean.setRncEuid(rs.getInt("RNC_EUID"));
        bean.setRncEdate(UDate.getInstanceTimestamp(rs.getTimestamp("RNC_EDATE")));
        return bean;
    }

    /**
     * <br>[機  能] 指定された稟議の経路内で稟議閲覧可能（受信済み）ユーザ一覧を取得する
     * <br>[解  説] 経路内でもまだ受信していないユーザは含まれない。
     * <br>[備  考] 送信者SIDも含む
     * @param rngSid 稟議SID
     * @return ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<Integer> getReceiveUserSids(int rngSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   USR_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql(" (");
            sql.addSql("    RNC_TYPE = ?");
            sql.addSql("  or");
            sql.addSql("    RNC_RCVDATE is not null ");
            sql.addSql(" )");
            sql.addSql(" order by");
            sql.addSql("   RNC_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(rngSid);
            sql.addIntValue(RngConst.RNG_RNCTYPE_APPL);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(new Integer(rs.getInt("USR_SID")));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return ret;
    }
}
