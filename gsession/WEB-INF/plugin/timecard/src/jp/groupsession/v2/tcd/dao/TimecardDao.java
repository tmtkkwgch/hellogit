package jp.groupsession.v2.tcd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.tcd.model.TcdTcdataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] タイムカードプラグインで共通使用されるDAOクラスです。
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class TimecardDao extends AbstractDao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(TimecardDao.class);

    /**
     * <p>Default Constructor
     */
    public TimecardDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public TimecardDao(Connection con) {
        super(con);
    }

    /**
     *<br>[機  能]該当タイムカードのがあるかを調べる
     *<br>[解  説]
     *<br>[備  考]
     * @param bean TCD_TCDATA Data Bindding JavaBean
     * @return selcnt 該当件数
     * @throws SQLException SQL実行例外
     */
    public int selCnt(TcdTcdataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   TCD_INTIME,");
            sql.addSql("   TCD_OUTTIME");
            sql.addSql(" from");
            sql.addSql("   TCD_TCDATA");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");
            sql.addSql(" and ");
            sql.addSql("   TCD_DATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrSid());
            sql.addDateValue(bean.getTcdDate());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = 1;
                bean.setTcdIntime(rs.getTime("TCD_INTIME"));
                bean.setTcdOuttime(rs.getTime("TCD_OUTTIME"));
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
     *<br>[機  能]insertを行う
     *<br>[解  説]
     *<br>[備  考]
     * @param bean TCD_TCDATA Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(TcdTcdataModel bean) throws SQLException {
        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" TCD_TCDATA(");
            sql.addSql("   USR_SID,");
            sql.addSql("   TCD_DATE,");
            sql.addSql("   TCD_INTIME,");
            sql.addSql("   TCD_OUTTIME,");
            sql.addSql("   TCD_BIKO,");
            sql.addSql("   TCD_STATUS,");
            sql.addSql("   TCD_AUID,");
            sql.addSql("   TCD_ADATE,");
            sql.addSql("   TCD_EUID,");
            sql.addSql("   TCD_EDATE");
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
            sql.addDateValue(bean.getTcdDate());

            Time inTime = bean.getTcdIntime();
            if (inTime == null) {
                sql.addStrValue((String) null);
            } else {
                sql.addDateValue(UDate.getInstance(inTime.getTime()));
            }

            Time outTime = bean.getTcdOuttime();
            if (outTime == null) {
                sql.addStrValue((String) null);
            } else {
                sql.addDateValue(UDate.getInstance(outTime.getTime()));
            }

            sql.addStrValue(bean.getTcdBiko());
            sql.addIntValue(bean.getTcdStatus());
            sql.addIntValue(bean.getTcdAuid());
            sql.addDateValue(bean.getTcdAdate());
            sql.addIntValue(bean.getTcdEuid());
            sql.addDateValue(bean.getTcdEdate());
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
     *<br>[機  能]Updateを行う
     *<br>[解  説]
     *<br>[備  考]
     * @param bean TCD_TCDATA Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return int 更新された行数
     */
    public int updateFromCL(TcdTcdataModel bean) throws SQLException {
        PreparedStatement pstmt = null;
        int count = 0;

        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   TCD_TCDATA");
            sql.addSql(" set ");
            if (bean.getTcdStatus() == GSConst.UIOSTS_IN) {
                sql.addSql("   TCD_INTIME=?,");
            } else if (bean.getTcdStatus() == GSConst.UIOSTS_LEAVE) {
                sql.addSql("   TCD_OUTTIME=?,");
            }
            sql.addSql("   TCD_STATUS=?,");
            sql.addSql("   TCD_EUID=?,");
            sql.addSql("   TCD_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");
            sql.addSql(" and ");
            sql.addSql("   TCD_DATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            if (bean.getTcdStatus() == GSConst.UIOSTS_IN) {
                sql.addDateValue(UDate.getInstance(bean.getTcdIntime().getTime()));
            } else if (bean.getTcdStatus() == GSConst.UIOSTS_LEAVE) {
                sql.addDateValue(UDate.getInstance(bean.getTcdOuttime().getTime()));
            }
            sql.addIntValue(bean.getTcdStatus());
            sql.addIntValue(bean.getTcdEuid());
            sql.addDateValue(bean.getTcdEdate());
            sql.addIntValue(bean.getUsrSid());
            sql.addDateValue(bean.getTcdDate());

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
     *<br>[機  能]タイムカード情報を更新する。
     *<br>[解  説]各編集ロックが無い場合に更新を行う。
     *<br>[備  考]
     * @param bean TCD_TCDATA Data Bindding JavaBean
     * @return int 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateLock(TcdTcdataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   TCD_TCDATA");
            sql.addSql(" set ");

            if (bean.getTcdLockTime() == GSConstTimecard.UNLOCK_FLG) {
                sql.addSql("   TCD_INTIME=?,");
                sql.addSql("   TCD_OUTTIME=?,");
                if (bean.getTcdIntime() != null) {
                    sql.addDateValue(UDate.getInstance(bean.getTcdIntime().getTime()));
                } else {
                    sql.addDateValue((UDate) null);
                }

                if (bean.getTcdOuttime() != null) {
                    sql.addDateValue(UDate.getInstance(bean.getTcdOuttime().getTime()));
                } else {
                    sql.addDateValue((UDate) null);
                }

            }
            if (bean.getTcdLockStrike() == GSConstTimecard.UNLOCK_FLG) {
                sql.addSql("   TCD_STRIKE_INTIME=?,");
                sql.addSql("   TCD_STRIKE_OUTTIME=?,");

                if (bean.getTcdStrikeIntime() != null) {
                    sql.addDateValue(UDate.getInstance(bean.getTcdStrikeIntime().getTime()));
                } else {
                    sql.addDateValue((UDate) null);
                }

                if (bean.getTcdStrikeOuttime() != null) {
                    sql.addDateValue(UDate.getInstance(bean.getTcdStrikeOuttime().getTime()));
                } else {
                    sql.addDateValue((UDate) null);
                }
            }

            if (bean.getTcdLockHoliday() == GSConstTimecard.UNLOCK_FLG) {
                sql.addSql("   TCD_HOLKBN=?,");
                sql.addSql("   TCD_HOLOTHER=?,");
                sql.addSql("   TCD_HOLCNT=?,");
                sql.addIntValue(bean.getTcdHolkbn());
                sql.addStrValue(bean.getTcdHolother());
                sql.addDecimalValue(bean.getTcdHolcnt());
            }

            if (bean.getTcdLockLate() == GSConstTimecard.UNLOCK_FLG) {
                sql.addSql("   TCD_CHKKBN=?,");
                sql.addSql("   TCD_SOUKBN=?,");
                sql.addIntValue(bean.getTcdChkkbn());
                sql.addIntValue(bean.getTcdSoukbn());
            }
            sql.addSql("   TCD_BIKO=?,");
            sql.addSql("   TCD_STATUS=?,");
            sql.addSql("   TCD_LOCK_FLG=?,");

            sql.addSql("   TCD_EUID=?,");
            sql.addSql("   TCD_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");
            sql.addSql(" and");
            sql.addSql("   TCD_DATE=?");

            sql.addStrValue(bean.getTcdBiko());
            sql.addIntValue(bean.getTcdStatus());
            sql.addIntValue(bean.getTcdLockFlg());
            sql.addIntValue(bean.getTcdEuid());
            sql.addDateValue(bean.getTcdEdate());
            //where
            sql.addIntValue(bean.getUsrSid());
            sql.addDateValue(bean.getTcdDate());

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
     * <p>期首月を取得する
     * @return List in CMN_ENTER_INFModel
     * @throws SQLException SQL実行例外
     */
    public int getKishuMonth() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   ENI_KISYU");
            sql.addSql(" from ");
            sql.addSql("   CMN_ENTER_INF");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("ENI_KISYU");
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
