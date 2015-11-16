package jp.groupsession.v2.cmn.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>CMN_USRM Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class CmnUsrmDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(CmnUsrmDao.class);

    /**
     * <p>Default Constructor
     */
    public CmnUsrmDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public CmnUsrmDao(Connection con) {
        super(con);
    }

    /**
     * <p>Insert CMN_USRM Data Bindding JavaBean
     * @param bean CMN_USRM Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(CmnUsrmModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" CMN_USRM(");
            sql.addSql("   USR_SID,");
            sql.addSql("   USR_LGID,");
            sql.addSql("   USR_PSWD,");
            sql.addSql("   USR_JKBN,");
            sql.addSql("   USR_AUID,");
            sql.addSql("   USR_ADATE,");
            sql.addSql("   USR_EUID,");
            sql.addSql("   USR_EDATE,");
            sql.addSql("   USR_PSWD_KBN,");
            sql.addSql("   USR_PSWD_EDATE");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrSid());
            sql.addStrValue(bean.getUsrLgid());
            sql.addStrValue(bean.getUsrPswd());
            sql.addIntValue(bean.getUsrJkbn());
            sql.addIntValue(bean.getUsrAuid());
            sql.addDateValue(bean.getUsrAdate());
            sql.addIntValue(bean.getUsrEuid());
            sql.addDateValue(bean.getUsrEdate());
            sql.addIntValue(bean.getUsrPswdKbn());
            sql.addDateValue(bean.getUsrPswdEdate());
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
     * <p>Update CMN_USRM Data Bindding JavaBean
     * @param bean CMN_USRM Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return 更新件数
     */
    public int update(CmnUsrmModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_USRM");
            sql.addSql(" set ");
            sql.addSql("   USR_LGID=?,");
            sql.addSql("   USR_PSWD=?,");
            sql.addSql("   USR_JKBN=?,");
            sql.addSql("   USR_AUID=?,");
            sql.addSql("   USR_ADATE=?,");
            sql.addSql("   USR_EUID=?,");
            sql.addSql("   USR_EDATE=?,");
            sql.addSql("   USR_PSWD_KBN=?,");
            sql.addSql("   USR_PSWD_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getUsrLgid());
            sql.addStrValue(bean.getUsrPswd());
            sql.addIntValue(bean.getUsrJkbn());
            sql.addIntValue(bean.getUsrAuid());
            sql.addDateValue(bean.getUsrAdate());
            sql.addIntValue(bean.getUsrEuid());
            sql.addDateValue(bean.getUsrEdate());
            sql.addIntValue(bean.getUsrPswdKbn());
            sql.addDateValue(bean.getUsrPswdEdate());

            //where
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
     * <p>Update CMN_USRM Data Bindding JavaBean
     * @param bean CMN_USRM Data Bindding JavaBean
     * @param updatePassFlg パスワード変更フラグ 0:変更する 1:変更しない
     * @throws SQLException SQL実行例外
     * @return 更新件数
     */
    public int updateUserData(CmnUsrmModel bean, int updatePassFlg) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_USRM");
            sql.addSql(" set ");
            sql.addSql("   USR_LGID=?,");
            if (updatePassFlg == GSConstUser.PASS_CHANGE_OK) {
                sql.addSql("   USR_PSWD=?,");
            }
            sql.addSql("   USR_JKBN=?,");
            sql.addSql("   USR_AUID=?,");
            sql.addSql("   USR_ADATE=?,");
            sql.addSql("   USR_EUID=?,");
            sql.addSql("   USR_EDATE=?,");
            sql.addSql("   USR_PSWD_KBN=?,");
            sql.addSql("   USR_PSWD_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getUsrLgid());
            if (updatePassFlg == GSConstUser.PASS_CHANGE_OK) {
                sql.addStrValue(bean.getUsrPswd());
            }
            sql.addIntValue(bean.getUsrJkbn());
            sql.addIntValue(bean.getUsrAuid());
            sql.addDateValue(bean.getUsrAdate());
            sql.addIntValue(bean.getUsrEuid());
            sql.addDateValue(bean.getUsrEdate());
            sql.addIntValue(bean.getUsrPswdKbn());
            sql.addDateValue(bean.getUsrPswdEdate());

            //where
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
     * <p>Update CMN_USRM Data Bindding JavaBean
     * @param bean CMN_USRM Data Bindding JavaBean
     * @param canChangePassword パスワード変更の有効・無効
     * @throws SQLException SQL実行例外
     * @return 更新件数
     */
    public int updateCmnUser(CmnUsrmModel bean, boolean canChangePassword) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_USRM");
            sql.addSql(" set ");
            sql.addSql("   USR_LGID=?,");
            sql.addStrValue(bean.getUsrLgid());
            if (canChangePassword) {
                sql.addSql("   USR_PSWD=?,");
                sql.addStrValue(bean.getUsrPswd());
            }
            sql.addSql("   USR_EUID=?,");
            sql.addSql("   USR_EDATE=?,");
            sql.addSql("   USR_PSWD_KBN=?,");
            sql.addSql("   USR_PSWD_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");
            sql.addIntValue(bean.getUsrEuid());
            sql.addDateValue(bean.getUsrEdate());
            sql.addIntValue(bean.getUsrPswdKbn());
            sql.addDateValue(bean.getUsrPswdEdate());
            //where
            sql.addIntValue(bean.getUsrSid());

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
     * <p>Update CMN_USRM Data Bindding JavaBean
     * @param bean CMN_USRM Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return 更新件数
     */
    public int updateCmnUserKojn(CmnUsrmModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_USRM");
            sql.addSql(" set ");
            sql.addSql("   USR_EUID=?,");
            sql.addSql("   USR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrEuid());
            sql.addDateValue(bean.getUsrEdate());
            //where
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
     * <p>Update CMN_USRM Data Bindding JavaBean
     * @param bean CMN_USRM Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return 更新件数
     */
    public int updatePassword(CmnUsrmModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_USRM");
            sql.addSql(" set ");
            sql.addSql("   USR_PSWD = ?,");
            sql.addSql("   USR_EUID = ?,");
            sql.addSql("   USR_EDATE = ?,");
            sql.addSql("   USR_PSWD_KBN = ?,");
            sql.addSql("   USR_PSWD_EDATE = ?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getUsrPswd());
            sql.addIntValue(bean.getUsrEuid());
            sql.addDateValue(bean.getUsrEdate());
            sql.addIntValue(bean.getUsrPswdKbn());
            sql.addDateValue(bean.getUsrPswdEdate());

            //where
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
     * <p>Update CMN_USRM Data Bindding JavaBean
     * @param bean CMN_USRM Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return 更新件数
     */
    public int updateCmnUserDel(CmnUsrmModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_USRM");
            sql.addSql(" set ");
            sql.addSql("   USR_JKBN=?,");
            sql.addSql("   USR_EUID=?,");
            sql.addSql("   USR_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrJkbn());
            sql.addIntValue(bean.getUsrEuid());
            sql.addDateValue(bean.getUsrEdate());
            //where
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
     * <p>Select CMN_USRM All Data
     * @return List in CMN_USRMModel
     * @throws SQLException SQL実行例外
     */
    public List<CmnUsrmModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<CmnUsrmModel> ret = new ArrayList<CmnUsrmModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   USR_SID,");
            sql.addSql("   USR_LGID,");
            sql.addSql("   USR_PSWD,");
            sql.addSql("   USR_JKBN,");
            sql.addSql("   USR_AUID,");
            sql.addSql("   USR_ADATE,");
            sql.addSql("   USR_EUID,");
            sql.addSql("   USR_EDATE,");
            sql.addSql("   USR_PSWD_KBN,");
            sql.addSql("   USR_PSWD_EDATE");
            sql.addSql(" from ");
            sql.addSql("   CMN_USRM");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(getCmnUsrmFromRs(rs));
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
     * <p>有効なユーザ数を取得
     * @return int ユーザ数
     * @throws SQLException SQL実行例外
     */
    public int getActiveUserCount() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();
        int ret = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(*) as CNT");
            sql.addSql(" from ");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   USR_SID > ?");
            sql.addSql(" and ");
            sql.addSql("   USR_JKBN = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(GSConstUser.USER_RESERV_SID);
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);
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
     * <p>Select CMN_USRM
     * @param usid ユーザSID
     * @return CMN_USRMModel
     * @throws SQLException SQL実行例外
     */
    public CmnUsrmModel select(int usid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CmnUsrmModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_SID,");
            sql.addSql("   USR_LGID,");
            sql.addSql("   USR_PSWD,");
            sql.addSql("   USR_JKBN,");
            sql.addSql("   USR_AUID,");
            sql.addSql("   USR_ADATE,");
            sql.addSql("   USR_EUID,");
            sql.addSql("   USR_EDATE,");
            sql.addSql("   USR_PSWD_KBN,");
            sql.addSql("   USR_PSWD_EDATE");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(usid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = getCmnUsrmFromRs(rs);
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
     * <p>Select CMN_USRM
     * @param usrLgid ログインID
     * @return CMN_USRMModel
     * @throws SQLException SQL実行例外
     */
    public CmnUsrmModel select(String usrLgid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CmnUsrmModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_SID,");
            sql.addSql("   USR_LGID,");
            sql.addSql("   USR_PSWD,");
            sql.addSql("   USR_JKBN,");
            sql.addSql("   USR_AUID,");
            sql.addSql("   USR_ADATE,");
            sql.addSql("   USR_EUID,");
            sql.addSql("   USR_EDATE,");
            sql.addSql("   USR_PSWD_KBN,");
            sql.addSql("   USR_PSWD_EDATE");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   USR_LGID=?");
            sql.addSql(" and ");
            sql.addSql("   USR_JKBN<>?");
            sql.addStrValue(usrLgid);
            sql.addIntValue(GSConstUser.USER_JTKBN_DELETE);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = getCmnUsrmFromRs(rs);
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
     * <p>指定したログインＩＤが存在するかチェックを行う
     * <p>引数で指定したユーザSIDは除いてチェックを行う。(修正時に使用するため)
     * @param usid 除外するユーザのユーザSID
     * @param loginId ログインID
     * @return true:存在する, false:存在しない
     * @throws SQLException SQL実行例外
     */
    public boolean existLoginidEdit(int usid, String loginId) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean ret = true;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_LGID");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   USR_SID<>?");
            sql.addSql(" and ");
            sql.addSql("   USR_LGID=?");
            sql.addSql(" and ");
            sql.addSql("   USR_JKBN<>?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(usid);
            sql.addStrValue(loginId);
            sql.addIntValue(GSConstUser.USER_JTKBN_DELETE);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            ret = rs.next();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <p>指定したユーザの状態区分を取得する
     * @param userSid ユーザSID
     * @return true:存在する, false:存在しない
     * @throws SQLException SQL実行例外
     */
    public int getUserJkbn(int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = -1;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_JKBN");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(userSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("USR_JKBN");
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
     * <br>[機  能] パラメータusidに該当するユーザの存在をチェックする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param usid ユーザSID
     * @return ret true:存在する false:存在しない
     * @throws SQLException SQL実行時例外
     */
    public boolean isExistUser(int usid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();
        boolean ret = false;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(CMN_USRM.USR_SID) as cnt");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   CMN_USRM.USR_SID = ?");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(usid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getInt("cnt") > 0) {
                    ret = true;
                }
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
     * <p>索引(五十音)を作成するため、存在するカナインデックスを取得する。
     * @param sysUserDisp システムユーザ取得可否 true:取得する false:取得しない
     * @return 存在するカナインデックス
     * @throws SQLException SQL実行例外
     */
    public Map<String, String> getExistsKanaIndex(boolean sysUserDisp)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = getCon();
        Map<String, String> ret = new HashMap<String, String>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    CMN_USRM_INF.USI_SINI as USI_SINI");
            sql.addSql("  from");
            sql.addSql("    CMN_USRM_INF,");
            sql.addSql("    CMN_USRM");
            sql.addSql("  where");
            sql.addSql("    CMN_USRM.USR_SID=CMN_USRM_INF.USR_SID");
            sql.addSql("  and");
            sql.addSql("    CMN_USRM.USR_JKBN<>?");
            sql.addIntValue(GSConstUser.USER_JTKBN_DELETE);

            if (!sysUserDisp) {
                sql.addSql("  and");
                sql.addSql("    CMN_USRM.USR_SID > ?");
                sql.addIntValue(GSConstUser.USER_RESERV_SID);
            }

            sql.addSql("  group by");
            sql.addSql("    USI_SINI");

            log__.info("SQL ==" + sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String kana = rs.getString("USI_SINI");
                ret.put(kana, kana);
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
     * <p>Delete CMN_USRM
     * @param bean CMN_USRM Model
     * @throws SQLException SQL実行例外
     * @return 削除件数
     */
    public int delete(CmnUsrmModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
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
     * <p>指定したSIDのユーザ情報を取得する。
     * @param usrList 取得対象のユーザ
     * @return 検索にヒットしたユーザデータ
     * @throws SQLException SQL実行例外
     */
    public ArrayList<BaseUserModel> getSelectedUserList(String[] usrList)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<BaseUserModel> ret = new ArrayList<BaseUserModel>();
        con = getCon();

        if (usrList == null || usrList.length < 1) {
            return ret;
        }

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   CMN_USRM.USR_SID as USR_SID,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as USI_MEI");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where");
            sql.addSql("   CMN_USRM.USR_SID in (");

            for (int i = 0; i < usrList.length; i++) {
                if (i > 0) {
                    sql.addSql(", ");
                }
                sql.addSql("?");
                sql.addIntValue(Integer.parseInt(usrList[i]));
            }
            sql.addSql(")");

            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID=CMN_USRM_INF.USR_SID");
            sql.addSql(" order by");
            sql.addSql("   USI_YAKUSYOKU,");
            sql.addSql("   USI_SEI_KN,");
            sql.addSql("   USI_MEI_KN");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);
            log__.info(sql.toLogString());

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(getBaseUserModelFromRs(rs));
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
     * <p>ユーザSIDを取得する
     * @param usrLgid ログインID
     * @return true:存在する, false:存在しない
     * @throws SQLException SQL実行例外
     */
    public CmnUsrmModel getUserSid(String usrLgid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CmnUsrmModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_SID,");
            sql.addSql("   USR_LGID,");
            sql.addSql("   USR_PSWD,");
            sql.addSql("   USR_JKBN,");
            sql.addSql("   USR_AUID,");
            sql.addSql("   USR_ADATE,");
            sql.addSql("   USR_EUID,");
            sql.addSql("   USR_EDATE,");
            sql.addSql("   USR_PSWD_KBN,");
            sql.addSql("   USR_PSWD_EDATE");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   USR_LGID=?");
            sql.addSql(" and ");
            sql.addSql("   USR_JKBN<>9");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(usrLgid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = getCmnUsrmFromRs(rs);
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
     * <br>[機  能] ユーザSID配列から削除済みのユーザ数をカウントする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userSid ユーザSID配列
     * @return ret カウント件数
     * @throws SQLException SQL実行例外
     */
    public int getCountDeleteUser(List<String> userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(CMN_USRM.USR_SID) as cnt");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where ");
            sql.addSql("   CMN_USRM.USR_SID in (");

            for (int i = 0; i < userSid.size(); i++) {
                if (i != 0) {
                    sql.addSql(", ");
                }
                sql.addSql(userSid.get(i));
            }
            sql.addSql(")");

            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(GSConst.JTKBN_DELETE);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("cnt");
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
     * <br>[機  能] ユーザSID配列から削除済みのユーザ数をカウントする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userSid ユーザSID配列
     * @return ret カウント件数
     * @throws SQLException SQL実行例外
     */
    public int getCountDeleteUser(ArrayList<Integer> userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(CMN_USRM.USR_SID) as cnt");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where ");
            sql.addSql("   CMN_USRM.USR_SID in (");

            for (int i = 0; i < userSid.size(); i++) {
                if (i != 0) {
                    sql.addSql(", ");
                }
                sql.addSql(String.valueOf(userSid.get(i)));
            }
            sql.addSql(")");

            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(GSConst.JTKBN_DELETE);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("cnt");
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
     * <p>ログインIDを元にユーザSIDを取得する
     * @param lgid ユーザID(ログインＩＤ)
     * @throws SQLException SQL実行例外
     * @return SID
     */
    public int selectLoginId(String lgid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_SID");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where");
            sql.addSql("   USR_LGID=?");
            sql.addSql(" and");
            sql.addSql("   USR_JKBN=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(lgid);
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("USR_SID");
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
     * <br>[機  能] ユーザSIDが削除済みのユーザSIDかどうか判定する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userSid ユーザSID
     * @return delUsrHnt True:削除ユーザ False:存在ユーザ
     * @throws SQLException SQL実行例外
     */
    public boolean isDeleteUserHnt(
            int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();
        boolean delUsrHnt = false;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   CMN_USRM.USR_SID as usrSid");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where ");
            sql.addSql("   CMN_USRM.USR_SID in (");
            sql.addSql(String.valueOf(userSid));
            sql.addSql(")");

            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(GSConst.JTKBN_DELETE);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                rs.getInt("usrSid");
                delUsrHnt = true;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return delUsrHnt;
    }

    /**
     * <br>[機  能] 指定したユーザのうち、削除されていないユーザを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID配列
     * @return 削除されていないユーザのユーザSID配列
     * @throws SQLException SQL実行例外
     */
    public String[] getNoDeleteUser(String[] userSid) throws SQLException {
        if (userSid == null || userSid.length == 0) {
            return userSid;
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        List<String> retUserList = new ArrayList<String>();
        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_SID");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   USR_SID in (");

            for (int i = 0; i < userSid.length; i++) {
                if (i != 0) {
                    sql.addSql("     ,?");
                } else {
                    sql.addSql("     ?");
                }
                sql.addIntValue(Integer.parseInt(userSid[i]));
            }
            sql.addSql(")");

            sql.addSql(" and");
            sql.addSql("   USR_JKBN = ?");
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            List<String> normalUserList = new ArrayList<String>();
            while (rs.next()) {
                normalUserList.add(rs.getString("USR_SID"));
            }

            for (String sid : userSid) {
                for (String normalSid : normalUserList) {
                    if (sid.equals(normalSid)) {
                        retUserList.add(sid);
                    }
                }
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return (String[]) retUserList.toArray(new String[retUserList.size()]);
    }

    /**
     * <p>全ユーザIDを取得する
     * @throws SQLException SQL実行例外
     * @return 全ユーザID
     */
    public List<String> getUsrAllId() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<String> ret = new ArrayList<String>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   USR_LGID");
            sql.addSql(" from ");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where ");
            sql.addSql("   USR_SID > ?");
            sql.addSql(" and ");
            sql.addSql("   USR_JKBN = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(GSConstUser.USER_RESERV_SID);
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);
            sql.setParameter(pstmt);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getString("USR_LGID"));
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
     * <p>全ユーザを取得する
     * @throws SQLException SQL実行例外
     * @return 全ユーザID
     */
    public List<CmnUsrmInfModel> getUsrAll() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<CmnUsrmInfModel> ret = new ArrayList<CmnUsrmInfModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   CMN_USRM.USR_SID as USR_SID,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("   CMN_USRM.USR_JKBN as USR_JKBN");
            sql.addSql(" from ");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where ");
            sql.addSql("   CMN_USRM.USR_SID=CMN_USRM_INF.USR_SID");
            sql.addSql(" order by CMN_USRM.USR_SID asc");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(getCmnUsrmInfRs(rs));
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
     * <p>Create CMN_USRM Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created CmnUsrmModel
     * @throws SQLException SQL実行例外
     */
    public static CmnUsrmModel getCmnUsrmFromRs(ResultSet rs) throws SQLException {
        CmnUsrmModel bean = new CmnUsrmModel();
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setUsrLgid(rs.getString("USR_LGID"));
        bean.setUsrPswd(rs.getString("USR_PSWD"));
        bean.setUsrJkbn(rs.getInt("USR_JKBN"));
        bean.setUsrAuid(rs.getInt("USR_AUID"));
        bean.setUsrAdate(UDate.getInstanceTimestamp(rs.getTimestamp("USR_ADATE")));
        bean.setUsrEuid(rs.getInt("USR_EUID"));
        bean.setUsrEdate(UDate.getInstanceTimestamp(rs.getTimestamp("USR_EDATE")));
        bean.setUsrPswdKbn(rs.getInt("USR_PSWD_KBN"));
        bean.setUsrPswdEdate(UDate.getInstanceTimestamp(rs.getTimestamp("USR_PSWD_EDATE")));

        return bean;
    }

    /**
     * <p>結果セットからBaseUserModelを取得する。
     * @param rs 結果セット
     * @return BaseUserModel
     * @throws SQLException SQL実行例外
     */
    public static BaseUserModel getBaseUserModelFromRs(ResultSet rs) throws SQLException {
        BaseUserModel ret = new BaseUserModel();
        //ユーザID
        ret.setUsrsid(rs.getInt("USR_SID"));
        //姓
        ret.setUsisei(NullDefault.getString(rs.getString("USI_SEI"), ""));
        //名
        ret.setUsimei(NullDefault.getString(rs.getString("USI_MEI"), ""));
        return ret;
    }

    /**
     * <p>結果セットからBaseUserModelを取得する。
     * @param rs 結果セット
     * @return BaseUserModel
     * @throws SQLException SQL実行例外
     */
    public static CmnUsrmInfModel getCmnUsrmInfRs(ResultSet rs) throws SQLException {
        CmnUsrmInfModel ret = new CmnUsrmInfModel();
        //ユーザID
        ret.setUsrSid(rs.getInt("USR_SID"));
        //姓
        ret.setUsiSei(NullDefault.getString(rs.getString("USI_SEI"), ""));
        //名
        ret.setUsiMei(NullDefault.getString(rs.getString("USI_MEI"), ""));
        //削除区分
        ret.setUsrJkbn(rs.getInt("USR_JKBN"));

        return ret;
    }
}
