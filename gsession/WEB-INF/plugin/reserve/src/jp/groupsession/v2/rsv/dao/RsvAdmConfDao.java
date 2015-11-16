package jp.groupsession.v2.rsv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.rsv.model.RsvAdmConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>RSV_ADM_CONF Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RsvAdmConfDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RsvAdmConfDao.class);

    /**
     * <p>Default Constructor
     */
    public RsvAdmConfDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public RsvAdmConfDao(Connection con) {
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
            sql.addSql("drop table RSV_ADM_CONF");

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
     * <p>Insert RSV_ADM_CONF Data Bindding JavaBean
     * @param bean RSV_ADM_CONF Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(RsvAdmConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" RSV_ADM_CONF(");
            sql.addSql("   RAC_ADL_KBN,");
            sql.addSql("   RAC_ADL_YEAR,");
            sql.addSql("   RAC_ADL_MONTH,");
            sql.addSql("   RAC_HOUR_DIV,");
            sql.addSql("   RAC_AUID,");
            sql.addSql("   RAC_ADATE,");
            sql.addSql("   RAC_EUID,");
            sql.addSql("   RAC_EDATE,");
            sql.addSql("   RAC_DTM_KBN,");
            sql.addSql("   RAC_DTM_FR,");
            sql.addSql("   RAC_DTM_TO,");
            sql.addSql("   RAC_INI_EDIT_KBN,");
            sql.addSql("   RAC_INI_EDIT,");
            sql.addSql("   RAC_SMAIL_SEND_KBN,");
            sql.addSql("   RAC_SMAIL_SEND");
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
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRacAdlKbn());
            sql.addIntValue(bean.getRacAdlYear());
            sql.addIntValue(bean.getRacAdlMonth());
            sql.addIntValue(bean.getRacHourDiv());
            sql.addIntValue(bean.getRacAuid());
            sql.addDateValue(bean.getRacAdate());
            sql.addIntValue(bean.getRacEuid());
            sql.addDateValue(bean.getRacEdate());
            sql.addIntValue(bean.getRacDtmKbn());
            sql.addIntValue(bean.getRacDtmFr());
            sql.addIntValue(bean.getRacDtmTo());
            sql.addIntValue(bean.getRacIniEditKbn());
            sql.addIntValue(bean.getRacIniEdit());
            sql.addIntValue(bean.getRacSmailSendKbn());
            sql.addIntValue(bean.getRacSmailSend());
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
     * <br>[機  能] update
     * <br>[解  説]
     * <br>[備  考]
     * @param bean RsvAdmConfModel
     * @return int 更新件数
     * @throws SQLException 例外
     */
    public int update(RsvAdmConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RSV_ADM_CONF");
            sql.addSql(" set ");
            sql.addSql("   RAC_ADL_KBN=?,");
            sql.addSql("   RAC_ADL_YEAR=?,");
            sql.addSql("   RAC_ADL_MONTH=?,");
            sql.addSql("   RAC_EUID=?,");
            sql.addSql("   RAC_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRacAdlKbn());
            sql.addIntValue(bean.getRacAdlYear());
            sql.addIntValue(bean.getRacAdlMonth());
            sql.addIntValue(bean.getRacEuid());
            sql.addDateValue(bean.getRacEdate());

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
     * <br>[機  能] 施設予約時間間隔の更新を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param bean RsvAdmConfModel
     * @return int 更新件数
     * @throws SQLException 例外
     */
    public int updateHourDiv(RsvAdmConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RSV_ADM_CONF");
            sql.addSql(" set ");
            sql.addSql("   RAC_HOUR_DIV=?,");
            sql.addSql("   RAC_EUID=?,");
            sql.addSql("   RAC_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRacHourDiv());
            sql.addIntValue(bean.getRacEuid());
            sql.addDateValue(bean.getRacEdate());

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
     * <br>[機  能] 日間表示時間帯の更新を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param bean RsvAdmConfModel
     * @return int 更新件数
     * @throws SQLException 例外
     */
    public int updateDayTimearea(RsvAdmConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RSV_ADM_CONF");
            sql.addSql(" set ");
            sql.addSql("   RAC_DTM_KBN=?,");
            sql.addSql("   RAC_DTM_FR=?,");
            sql.addSql("   RAC_DTM_TO=?,");
            sql.addSql("   RAC_EUID=?,");
            sql.addSql("   RAC_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRacDtmKbn());
            sql.addIntValue(bean.getRacDtmFr());
            sql.addIntValue(bean.getRacDtmTo());
            sql.addIntValue(bean.getRacEuid());
            sql.addDateValue(bean.getRacEdate());

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
     * <br>[機  能] 初期表示の更新を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param bean RsvAdmConfModel
     * @return int 更新件数
     * @throws SQLException 例外
     */
    public int updateInitConf(RsvAdmConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RSV_ADM_CONF");
            sql.addSql(" set ");
            sql.addSql("   RAC_INI_EDIT_KBN=?,");
            sql.addSql("   RAC_INI_EDIT=?,");
            sql.addSql("   RAC_EUID=?,");
            sql.addSql("   RAC_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRacIniEditKbn());
            sql.addIntValue(bean.getRacIniEdit());
            sql.addIntValue(bean.getRacEuid());
            sql.addDateValue(bean.getRacEdate());

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
     * <br>[機  能] ショートメール通知設定の更新を行う。
     * <br>[解  説]
     * <br>[備  考]
     * @param bean RsvAdmConfModel
     * @return int 更新件数
     * @throws SQLException 例外
     */
    public int updateSmailSendConf(RsvAdmConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RSV_ADM_CONF");
            sql.addSql(" set ");
            sql.addSql("   RAC_SMAIL_SEND_KBN=?,");
            sql.addSql("   RAC_SMAIL_SEND=?,");
            sql.addSql("   RAC_EUID=?,");
            sql.addSql("   RAC_EDATE=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRacSmailSendKbn());
            sql.addIntValue(bean.getRacSmailSend());
            sql.addIntValue(bean.getRacEuid());
            sql.addDateValue(bean.getRacEdate());

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
     * <p>Select RSV_ADM_CONF All Data
     * @return RSV_ADM_CONFModel
     * @throws SQLException SQL実行例外
     */
    public RsvAdmConfModel select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RsvAdmConfModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RAC_ADL_KBN,");
            sql.addSql("   RAC_ADL_YEAR,");
            sql.addSql("   RAC_ADL_MONTH,");
            sql.addSql("   RAC_HOUR_DIV,");
            sql.addSql("   RAC_AUID,");
            sql.addSql("   RAC_ADATE,");
            sql.addSql("   RAC_EUID,");
            sql.addSql("   RAC_EDATE,");
            sql.addSql("   RAC_DTM_KBN,");
            sql.addSql("   RAC_DTM_FR,");
            sql.addSql("   RAC_DTM_TO,");
            sql.addSql("   RAC_INI_EDIT_KBN,");
            sql.addSql("   RAC_INI_EDIT,");
            sql.addSql("   RAC_SMAIL_SEND_KBN,");
            sql.addSql("   RAC_SMAIL_SEND");
            sql.addSql(" from ");
            sql.addSql("   RSV_ADM_CONF");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRsvAdmConfFromRs(rs);
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
     * <p>Select RSV_ADM_CONF
     * @param bean RSV_ADM_CONF Model
     * @return RSV_ADM_CONFModel
     * @throws SQLException SQL実行例外
     */
    public RsvAdmConfModel select(RsvAdmConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RsvAdmConfModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RAC_ADL_KBN,");
            sql.addSql("   RAC_ADL_YEAR,");
            sql.addSql("   RAC_ADL_MONTH,");
            sql.addSql("   RAC_HOUR_DIV,");
            sql.addSql("   RAC_AUID,");
            sql.addSql("   RAC_ADATE,");
            sql.addSql("   RAC_EUID,");
            sql.addSql("   RAC_EDATE,");
            sql.addSql("   RAC_DTM_KBN,");
            sql.addSql("   RAC_DTM_FR,");
            sql.addSql("   RAC_DTM_TO,");
            sql.addSql("   RAC_INI_EDIT_KBN,");
            sql.addSql("   RAC_INI_EDIT,");
            sql.addSql("   RAC_SMAIL_SEND_KBN,");
            sql.addSql("   RAC_SMAIL_SEND");
            sql.addSql(" from");
            sql.addSql("   RSV_ADM_CONF");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRsvAdmConfFromRs(rs);
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
     * <br>[機  能] delete
     * <br>[解  説]
     * <br>[備  考]
     * @param bean RsvAdmConfModel
     * @return int 削除件数
     * @throws SQLException 例外
     */
    public int delete(RsvAdmConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RSV_ADM_CONF");

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
     * <p>Create RSV_ADM_CONF Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created RsvAdmConfModel
     * @throws SQLException SQL実行例外
     */
    private RsvAdmConfModel __getRsvAdmConfFromRs(ResultSet rs) throws SQLException {
        RsvAdmConfModel bean = new RsvAdmConfModel();
        bean.setRacAdlKbn(rs.getInt("RAC_ADL_KBN"));
        bean.setRacAdlYear(rs.getInt("RAC_ADL_YEAR"));
        bean.setRacAdlMonth(rs.getInt("RAC_ADL_MONTH"));
        bean.setRacHourDiv(rs.getInt("RAC_HOUR_DIV"));
        bean.setRacAuid(rs.getInt("RAC_AUID"));
        bean.setRacAdate(UDate.getInstanceTimestamp(rs.getTimestamp("RAC_ADATE")));
        bean.setRacEuid(rs.getInt("RAC_EUID"));
        bean.setRacEdate(UDate.getInstanceTimestamp(rs.getTimestamp("RAC_EDATE")));
        bean.setRacDtmKbn(rs.getInt("RAC_DTM_KBN"));
        bean.setRacDtmFr(rs.getInt("RAC_DTM_FR"));
        bean.setRacDtmTo(rs.getInt("RAC_DTM_TO"));
        bean.setRacIniEditKbn(rs.getInt("RAC_INI_EDIT_KBN"));
        bean.setRacIniEdit(rs.getInt("RAC_INI_EDIT"));
        bean.setRacSmailSendKbn(rs.getInt("RAC_SMAIL_SEND_KBN"));
        bean.setRacSmailSend(rs.getInt("RAC_SMAIL_SEND"));
        return bean;
    }
}
