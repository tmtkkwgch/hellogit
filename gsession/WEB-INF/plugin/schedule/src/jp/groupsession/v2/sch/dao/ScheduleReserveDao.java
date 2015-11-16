package jp.groupsession.v2.sch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.model.RsvSisDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] スケジュール情報に関連する施設予約情報を取得するためのDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ScheduleReserveDao extends AbstractDao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ScheduleReserveDao.class);

    /**
     * <p>
     * デフォルトコンストラクタ
     */
    public ScheduleReserveDao() {
    }

    /**
     * <p>
     * デフォルトコンストラクタ
     *
     * @param con
     *            DBコネクション
     */
    public ScheduleReserveDao(Connection con) {
        super(con);
    }

    /**
     * <p>
     * スケジュールSIDから同時登録された施設予約情報が存在するかを判定する
     * @param scdSid スケジュールSID
     * @return true:存在する false:存在しない
     * @throws SQLException
     *             SQL実行例外
     */
    public boolean existScheduleReserveData(int scdSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   RSV_SIS_YRK");
            sql.addSql(" where ");
            sql.addSql("   SCH_DATA.SCD_SID=?");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_RSSID <> -1");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
            sql.setPagingValue(0, 1);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(scdSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            result = rs.next();

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return result;
    }

    /**
     * <p>
     * スケジュールSIDから同時登録された施設予約情報を取得する
     * @param scdSid
     *            スケジュールSID
     * @return ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<Integer> getScheduleReserveData(int scdSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   RSV_SIS_YRK");
            sql.addSql(" where ");
            sql.addSql("   SCH_DATA.SCD_SID=?");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_RSSID <> -1");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
            sql.addSql(" group by");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(scdSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(new Integer(rs.getInt("RSD_SID")));
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
     * <p>
     * スケジュールSIDから同時登録された施設予約情報を取得する
     * @param scdSid
     *            スケジュールSID
     * @return ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<RsvSisDataModel> getScheduleReserveDataModel(int scdSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<RsvSisDataModel> ret = new ArrayList<RsvSisDataModel>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   RSV_SIS_YRK.RSD_SID,");
            sql.addSql("   RSV_SIS_YRK.RSG_SID");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   RSV_SIS_YRK");
            sql.addSql(" where ");
            sql.addSql("   SCH_DATA.SCD_SID=?");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_RSSID <> -1");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
            sql.addSql(" group by");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(scdSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            RsvSisDataModel model = null;
            while (rs.next()) {
                model = new RsvSisDataModel();
                model.setRsdSid(new Integer(rs.getInt("RSD_SID")));
                model.setRsgSid(new Integer(rs.getInt("RSG_SID")));
                ret.add(model);
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
     * <p>
     * スケジュールSIDから同時登録された編集権限の施設を取得する
     * @param scdSid スケジュールSID
     * @param usrSid ユーザSID
     * @return ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<Integer> getCanEditScheduleReserveData(int scdSid, int usrSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   RSV_SIS_YRK,");
            sql.addSql("   RSV_SIS_DATA,");
            sql.addSql("   RSV_SIS_GRP");
            sql.addSql(" where ");
            sql.addSql("   SCH_DATA.SCD_SID=?");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_RSSID <> -1");

            sql.addSql(" and");
            sql.addSql("(");

            sql.addSql("  (");
            sql.addSql("   RSV_SIS_GRP.RSG_ACS_LIMIT_KBN=?");
            sql.addSql("  )");

            sql.addSql(" or");

            sql.addSql("   ( ");
            sql.addSql("    RSV_SIS_GRP.RSG_ACS_LIMIT_KBN=?");
            sql.addSql("  and ");
            sql.addSql("    exists");
            sql.addSql("    (");
            sql.addSql("      select");
            sql.addSql("        RSV_ACCESS_CONF.RSG_SID");
            sql.addSql("      from");
            sql.addSql("        RSV_ACCESS_CONF");
            sql.addSql("        left join");
            sql.addSql("          CMN_BELONGM");
            sql.addSql("        on");
            sql.addSql("          RSV_ACCESS_CONF.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("      where");
            sql.addSql("        (");
            sql.addSql("          RSV_ACCESS_CONF.USR_SID = ?");
            sql.addSql("        or");
            sql.addSql("          CMN_BELONGM.USR_SID = ?");
            sql.addSql("        )");
            sql.addSql("      and");
            sql.addSql("        RSV_ACCESS_CONF.RAC_AUTH = ?");
            sql.addSql("      and");
            sql.addSql("        RSV_ACCESS_CONF.RSG_SID = RSV_SIS_GRP.RSG_SID");
            sql.addSql("    )");
            sql.addSql("  )");

            sql.addSql(" or");

            sql.addSql("  (");
            sql.addSql("    RSV_SIS_GRP.RSG_ACS_LIMIT_KBN=?");
            sql.addSql("  and ");
            sql.addSql("    not exists");
            sql.addSql("    (");
            sql.addSql("      select");
            sql.addSql("        RSV_ACCESS_CONF.RSG_SID");
            sql.addSql("      from");
            sql.addSql("        RSV_ACCESS_CONF");
            sql.addSql("        left join");
            sql.addSql("          CMN_BELONGM");
            sql.addSql("        on");
            sql.addSql("          RSV_ACCESS_CONF.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("      where");
            sql.addSql("        (");
            sql.addSql("          RSV_ACCESS_CONF.USR_SID = ?");
            sql.addSql("        or");
            sql.addSql("          CMN_BELONGM.USR_SID = ?");
            sql.addSql("        )");
            sql.addSql("      and");
            sql.addSql("        RSV_ACCESS_CONF.RSG_SID = RSV_SIS_GRP.RSG_SID");
            sql.addSql("    )");
            sql.addSql("  )");
            sql.addSql(")");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
            sql.addSql(" and");
            sql.addSql("   RSV_SIS_YRK.RSD_SID = RSV_SIS_DATA.RSD_SID");
            sql.addSql(" and");
            sql.addSql("   RSV_SIS_DATA.RSG_SID = RSV_SIS_GRP.RSG_SID");
            sql.addSql(" group by");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(scdSid);
            sql.addIntValue(GSConstReserve.RSV_ACCESS_MODE_FREE);
            sql.addIntValue(GSConstReserve.RSV_ACCESS_MODE_LIMIT);
            sql.addIntValue(usrSid);
            sql.addIntValue(usrSid);
            sql.addIntValue(GSConstReserve.RSV_ACCESS_KBN_WRITE);
            sql.addIntValue(GSConstReserve.RSV_ACCESS_MODE_PERMIT);
            sql.addIntValue(usrSid);
            sql.addIntValue(usrSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(new Integer(rs.getInt("RSD_SID")));
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
     * <p>
     * スケジュール拡張SIDから同時登録された施設予約情報を取得する
     * @param sceSid
     *            スケジュールSID
     * @return ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<Integer> getScheduleReserveDataFromExSid(int sceSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");
            sql.addSql(" from");
            sql.addSql("  (");
            sql.addSql("  select");
            sql.addSql("    SCH_DATA.SCD_SID,");
            sql.addSql("    SCH_DATA.SCD_RSSID");
            sql.addSql("  from");
            sql.addSql("    SCH_DATA,");
            sql.addSql("    SCH_EXDATA");
            sql.addSql("  where");
            sql.addSql("    SCH_EXDATA.SCE_SID = SCH_DATA.SCE_SID");
            sql.addSql("  and");
            sql.addSql("    SCH_EXDATA.SCE_SID = ?");
            sql.addSql("  ) EXDATA,");
            sql.addSql("   RSV_SIS_YRK");
            sql.addSql(" where");
            sql.addSql("   EXDATA.SCD_RSSID > 0");
            sql.addSql(" and");
            sql.addSql("   EXDATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
            sql.addSql(" group by");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sceSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(new Integer(rs.getInt("RSD_SID")));
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
     * <p>
     * スケジュール拡張SIDから同時登録された施設予約情報を取得する
     * @param sceSid スケジュールSID
     * @param usrSid ユーザSID
     * @return ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<Integer> getCanEditScheduleReserveDataFromExSid(
            int sceSid, int usrSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");
            sql.addSql(" from");
            sql.addSql("  (");
            sql.addSql("  select");
            sql.addSql("    SCH_DATA.SCD_SID,");
            sql.addSql("    SCH_DATA.SCD_RSSID");
            sql.addSql("  from");
            sql.addSql("    SCH_DATA,");
            sql.addSql("    SCH_EXDATA");
            sql.addSql("  where");
            sql.addSql("    SCH_EXDATA.SCE_SID = SCH_DATA.SCE_SID");
            sql.addSql("  and");
            sql.addSql("    SCH_EXDATA.SCE_SID = ?");
            sql.addSql("  ) EXDATA,");
            sql.addSql("   RSV_SIS_YRK,");
            sql.addSql("   RSV_SIS_DATA,");
            sql.addSql("   RSV_SIS_GRP");

            sql.addSql(" where");
            sql.addSql("   EXDATA.SCD_RSSID > 0");
            sql.addSql(" and");
            sql.addSql("   EXDATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
            sql.addSql(" and");
            sql.addSql("   RSV_SIS_YRK.RSD_SID = RSV_SIS_DATA.RSD_SID");
            sql.addSql(" and");
            sql.addSql("   RSV_SIS_DATA.RSG_SID = RSV_SIS_GRP.RSG_SID");

            sql.addSql(" and");
            sql.addSql("(");

            sql.addSql("  (");
            sql.addSql("   RSV_SIS_GRP.RSG_ACS_LIMIT_KBN=?");
            sql.addSql("  )");

            sql.addSql(" or");

            sql.addSql("   ( ");
            sql.addSql("    RSV_SIS_GRP.RSG_ACS_LIMIT_KBN=?");
            sql.addSql("  and ");
            sql.addSql("    exists");
            sql.addSql("    (");
            sql.addSql("      select");
            sql.addSql("        RSV_ACCESS_CONF.RSG_SID");
            sql.addSql("      from");
            sql.addSql("        RSV_ACCESS_CONF");
            sql.addSql("        left join");
            sql.addSql("          CMN_BELONGM");
            sql.addSql("        on");
            sql.addSql("          RSV_ACCESS_CONF.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("      where");
            sql.addSql("        RSV_ACCESS_CONF.RSG_SID = RSV_SIS_GRP.RSG_SID");
            sql.addSql("      and");
            sql.addSql("        (");
            sql.addSql("          RSV_ACCESS_CONF.USR_SID = ?");
            sql.addSql("        or");
            sql.addSql("          CMN_BELONGM.USR_SID = ?");
            sql.addSql("        )");
            sql.addSql("      and");
            sql.addSql("        RSV_ACCESS_CONF.RAC_AUTH = ?");
            sql.addSql("    )");
            sql.addSql("  )");

            sql.addSql(" or");

            sql.addSql("  (");
            sql.addSql("    RSV_SIS_GRP.RSG_ACS_LIMIT_KBN=?");
            sql.addSql("  and ");
            sql.addSql("    not exists");
            sql.addSql("    (");
            sql.addSql("      select");
            sql.addSql("        RSV_ACCESS_CONF.RSG_SID");
            sql.addSql("      from");
            sql.addSql("        RSV_ACCESS_CONF");
            sql.addSql("        left join");
            sql.addSql("          CMN_BELONGM");
            sql.addSql("        on");
            sql.addSql("          RSV_ACCESS_CONF.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("      where");
            sql.addSql("        RSV_ACCESS_CONF.RSG_SID = RSV_SIS_GRP.RSG_SID");
            sql.addSql("      and");
            sql.addSql("        (");
            sql.addSql("          RSV_ACCESS_CONF.USR_SID = ?");
            sql.addSql("        or");
            sql.addSql("          CMN_BELONGM.USR_SID = ?");
            sql.addSql("        )");
            sql.addSql("      and");
            sql.addSql("        RSV_ACCESS_CONF.RAC_AUTH = ?");
            sql.addSql("    )");
            sql.addSql("  )");
            sql.addSql(")");
            sql.addSql(" group by");
            sql.addSql("   RSV_SIS_YRK.RSD_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sceSid);
            sql.addIntValue(GSConstReserve.RSV_ACCESS_MODE_FREE);
            sql.addIntValue(GSConstReserve.RSV_ACCESS_MODE_LIMIT);
            sql.addIntValue(usrSid);
            sql.addIntValue(usrSid);
            sql.addIntValue(GSConstReserve.RSV_ACCESS_KBN_WRITE);
            sql.addIntValue(GSConstReserve.RSV_ACCESS_MODE_PERMIT);
            sql.addIntValue(usrSid);
            sql.addIntValue(usrSid);
            sql.addIntValue(GSConstReserve.RSV_ACCESS_KBN_WRITE);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(new Integer(rs.getInt("RSD_SID")));
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
     * <p>
     * スケジュール拡張SIDから同時登録された施設予約情報を取得する
     * @param sceSid
     *            スケジュールSID
     * @return ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<Integer> getScheduleReserveSidFromExSid(int sceSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   RSV_SIS_YRK.RSY_SID");
            sql.addSql(" from");
            sql.addSql("  (");
            sql.addSql("  select");
            sql.addSql("    SCH_DATA.SCD_SID,");
            sql.addSql("    SCH_DATA.SCD_RSSID");
            sql.addSql("  from");
            sql.addSql("    SCH_DATA,");
            sql.addSql("    SCH_EXDATA");
            sql.addSql("  where");
            sql.addSql("    SCH_EXDATA.SCE_SID = SCH_DATA.SCE_SID");
            sql.addSql("  and");
            sql.addSql("    SCH_EXDATA.SCE_SID = ?");
            sql.addSql("  ) EXDATA,");
            sql.addSql("   RSV_SIS_YRK");
            sql.addSql(" where");
            sql.addSql("   EXDATA.SCD_RSSID > 0");
            sql.addSql(" and");
            sql.addSql("   EXDATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
            sql.addSql(" group by");
            sql.addSql("   RSV_SIS_YRK.RSY_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sceSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(new Integer(rs.getInt("RSY_SID")));
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
