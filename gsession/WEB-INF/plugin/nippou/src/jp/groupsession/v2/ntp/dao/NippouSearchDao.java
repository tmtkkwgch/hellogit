package jp.groupsession.v2.ntp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.csv.CSVException;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.model.UserSearchModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.model.NippouExSearchModel;
import jp.groupsession.v2.ntp.model.NippouListSearchModel;
import jp.groupsession.v2.ntp.model.NippouSearchModel;
import jp.groupsession.v2.ntp.model.NtpDataModel;
import jp.groupsession.v2.ntp.ntp100.NippouCsvModel;
import jp.groupsession.v2.ntp.ntp100.NtpCsvRecordListenerIppanImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報情報検索用のDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class NippouSearchDao extends AbstractDao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(NippouSearchDao.class);

    /**
     * <p>
     * デフォルトコンストラクタ
     */
    public NippouSearchDao() {
    }

    /**
     * <p>
     * デフォルトコンストラクタ
     *
     * @param con
     *            DBコネクション
     */
    public NippouSearchDao(Connection con) {
        super(con);
    }

    /**
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定し日報情報を取得する
     *
     * @param usrSid
     *            ユーザSID
     * @param usrKbn
     *            ユーザ区分
     * @param pub
     *            公開区分 ※-1を指定すると条件から除外されます
     * @param from
     *            日付from
     * @param to
     *            日付to
     * @param mod
     *            取得モード (週間:"1" 月間:"2" 日間:"3")
     * @return ArrayList in NtpDataModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<NtpDataModel> select(int usrSid, int usrKbn, int pub,
            UDate from, UDate to, String mod) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<NtpDataModel> ret = new ArrayList<NtpDataModel>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   NIP_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   NIP_DATE,");
            sql.addSql("   NIP_FR_TIME,");
            sql.addSql("   NIP_TO_TIME,");
            sql.addSql("   NIP_KADO_HH,");
            sql.addSql("   NIP_KADO_MM,");
            sql.addSql("   NIP_MGY_SID,");
            sql.addSql("   NAN_SID,");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ABA_SID,");
            sql.addSql("   NIP_TITLE,");
            sql.addSql("   NIP_TITLE_CLO,");
            sql.addSql("   MPR_SID,");
            sql.addSql("   MKB_SID,");
            sql.addSql("   MKH_SID,");
            sql.addSql("   NIP_TIEUP_SID,");
            sql.addSql("   NIP_KEIZOKU,");
            sql.addSql("   NIP_ACTEND,");
            sql.addSql("   NIP_DETAIL,");
            sql.addSql("   NIP_ACTION_DATE,");
            sql.addSql("   NIP_ACTION,");
            sql.addSql("   NIP_ACTDATE_KBN,");
            sql.addSql("   NIP_ASSIGN,");
            sql.addSql("   NIP_KINGAKU,");
            sql.addSql("   NIP_MIKOMI,");
            sql.addSql("   NIP_SYOKAN,");
            sql.addSql("   NIP_PUBLIC,");
            sql.addSql("   NIP_EDIT,");
            sql.addSql("   NEX_SID,");
            sql.addSql("   NIP_AUID,");
            sql.addSql("   NIP_ADATE,");
            sql.addSql("   NIP_EUID,");
            sql.addSql("   NIP_EDATE");
            sql.addSql(" from ");
            sql.addSql("   NTP_DATA");
            sql.addSql(" where ");
            sql.addSql("   USR_SID = ?");
            sql.addIntValue(usrSid);

            if (mod.equals(GSConstNippou.DSP_MOD_WEEK)) {

                String fromDateStr = from.getDateStringForSql();
                String toDateStr = to.getDateStringForSql();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
                sql.addSql("     NIP_DATE");
                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
                sql.addSql("   )");
                sql.addSql("  )");
            }

            if (mod.equals(GSConstNippou.DSP_MOD_MONTH)) {

                UDate frYmd = new UDate();
                frYmd.setDate(from.getYear(), from.getMonth(), 1);
                UDate toYmd = new UDate();
                toYmd.setDate(to.getYear(), to.getMonth(), to
                        .getMaxDayOfMonth());
                String fromDateStr = frYmd.getDateStringForSql();
                String toDateStr = to.getDateStringForSql();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
                sql.addSql("     NIP_DATE");
                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
                sql.addSql("   )");
                sql.addSql("  )");
            }

            if (mod.equals(GSConstNippou.DSP_MOD_DAY)) {
                String fromDateTmp = from.toSQLTimestamp().toString();

                sql.addSql(" and");
                sql.addSql("  NIP_DATE = ?");
                sql.addStrValue(fromDateTmp);

            }

            sql.addSql(" order by ");
            sql.addSql("   USR_SID,");
            sql.addSql("   NIP_FR_TIME,");
            sql.addSql("   NIP_SID");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getNtpDataFromRs(rs));
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
     * 指定ユーザの指定期間の次のアクションを取得する
     *
     * @param usrSid
     *            ユーザSID
     * @param from
     *            日付from
     * @param to
     *            日付to
     * @return ArrayList in NtpDataModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<NtpDataModel> selectNextAction(int usrSid,
            UDate from, UDate to) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<NtpDataModel> ret = new ArrayList<NtpDataModel>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   NIP_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   NIP_DATE,");
            sql.addSql("   NIP_FR_TIME,");
            sql.addSql("   NIP_TO_TIME,");
            sql.addSql("   NIP_KADO_HH,");
            sql.addSql("   NIP_KADO_MM,");
            sql.addSql("   NIP_MGY_SID,");
            sql.addSql("   NAN_SID,");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ABA_SID,");
            sql.addSql("   NIP_TITLE,");
            sql.addSql("   NIP_TITLE_CLO,");
            sql.addSql("   MPR_SID,");
            sql.addSql("   MKB_SID,");
            sql.addSql("   MKH_SID,");
            sql.addSql("   NIP_TIEUP_SID,");
            sql.addSql("   NIP_KEIZOKU,");
            sql.addSql("   NIP_ACTEND,");
            sql.addSql("   NIP_DETAIL,");
            sql.addSql("   NIP_ACTION_DATE,");
            sql.addSql("   NIP_ACTION,");
            sql.addSql("   NIP_ACTDATE_KBN,");
            sql.addSql("   NIP_ASSIGN,");
            sql.addSql("   NIP_KINGAKU,");
            sql.addSql("   NIP_MIKOMI,");
            sql.addSql("   NIP_SYOKAN,");
            sql.addSql("   NIP_PUBLIC,");
            sql.addSql("   NIP_EDIT,");
            sql.addSql("   NEX_SID,");
            sql.addSql("   NIP_AUID,");
            sql.addSql("   NIP_ADATE,");
            sql.addSql("   NIP_EUID,");
            sql.addSql("   NIP_EDATE");
            sql.addSql(" from ");
            sql.addSql("   NTP_DATA");
            sql.addSql(" where ");
            sql.addSql("   USR_SID = ?");
            sql.addIntValue(usrSid);

            UDate frYmd = new UDate();
            frYmd.setDate(from.getYear(), from.getMonth(), from.getIntDay());
            UDate toYmd = new UDate();
            toYmd.setDate(to.getYear(), to.getMonth(), to.getIntDay());
            String fromDateStr = frYmd.getDateStringForSql();
            String toDateStr = to.getDateStringForSql();

            sql.addSql(" and");
            sql.addSql("  (");
            sql.addSql("   (");
            sql.addSql("     NIP_ACTION_DATE");
            sql.addSql("     between cast(? as DATE)");
            sql.addSql("     and cast(? as DATE)");
            sql.addSql("   )");
            sql.addSql("  )");
            sql.addStrValue(fromDateStr);
            sql.addStrValue(toDateStr);
            sql.addSql(" and");
            sql.addSql(" NIP_ACTDATE_KBN=1");
            sql.addSql(" order by ");
            sql.addSql("   USR_SID,");
            sql.addSql("   NIP_FR_TIME,");
            sql.addSql("   NIP_SID");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getNtpDataFromRs(rs));
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
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定し日報情報を取得する
     *
     * @param usrSid
     *            ユーザSID
     * @param pub
     *            公開区分 ※-1を指定すると条件から除外されます
     * @param from
     *            日付from
     * @param to
     *            日付to
     * @param mod
     *            取得モード (週間:"1" 月間:"2" 日間:"3")
     * @return ArrayList in SchDataModel
     * @throws SQLException
     *             SQL実行例外
     */
//    public ArrayList<NtpDataModel> getBelongGroupNtpData(int usrSid, int pub,
//            UDate from, UDate to, String mod) throws SQLException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        ArrayList<NtpDataModel> ret = new ArrayList<NtpDataModel>();
//        con = getCon();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//            sql.addSql(" select ");
//            sql.addSql("   SCD_SID,");
//            sql.addSql("   SCD_USR_SID,");
//            sql.addSql("   SCD_GRP_SID,");
//            sql.addSql("   SCD_USR_KBN,");
//            sql.addSql("   SCD_FR_DATE,");
//            sql.addSql("   SCD_TO_DATE,");
//            sql.addSql("   SCD_DAILY,");
//            sql.addSql("   SCD_BGCOLOR,");
//            sql.addSql("   SCD_TITLE,");
//            sql.addSql("   SCD_VALUE,");
//            sql.addSql("   SCD_BIKO,");
//            sql.addSql("   SCD_PUBLIC,");
//            sql.addSql("   SCD_AUID,");
//            sql.addSql("   SCD_ADATE,");
//            sql.addSql("   SCD_EUID,");
//            sql.addSql("   SCD_EDATE,");
//            sql.addSql("   SCE_SID,");
//            sql.addSql("   SCD_RSSID,");
//            sql.addSql("   SCD_EDIT");
//            sql.addSql(" from ");
//            sql.addSql("   SCH_DATA");
//            sql.addSql(" where ");
//            sql.addSql("   SCD_USR_SID in ( ");
//
//            sql.addSql(" select");
//            sql.addSql("   GRP_SID");
//            sql.addSql(" from");
//            sql.addSql("   CMN_BELONGM");
//            sql.addSql(" where ");
//            sql.addSql("   USR_SID=?");
//            sql.addValue(usrSid);
//            sql.addSql(" ) ");
//            sql.addSql(" and ");
//            sql.addSql("   SCD_USR_KBN = ?");
//            sql.addValue(GSConstNippou.USER_KBN_GROUP);
//            if (pub != -1) {
//                sql.addSql(" and ");
//                sql.addSql("   SCD_PUBLIC = ?");
//                sql.addValue(pub);
//            }
//
//            UDate dayUd = new UDate();
//
//            /** ********** 時間設定無し (H2用) *********** */
//            if (mod.equals(GSConstNippou.DSP_MOD_WEEK)) {
//                String fromDateStr = from.getDateStringForSql();
//                String toDateStr = to.getDateStringForSql();
//
//                sql.addSql(" and");
//                sql.addSql("  (");
//                sql.addSql("   (");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
//                sql.addSql("   )");
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
//                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("     and cast('" + toDateStr + "' as DATE) ");
//                sql.addSql("   )");
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("    cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("    between");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("    and");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
//                sql.addSql("   )");
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("    cast('" + toDateStr + "' as DATE)");
//                sql.addSql("    between");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("    and");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
//                sql.addSql("   )");
//                sql.addSql("  )");
//            }
//            if (mod.equals(GSConstNippou.DSP_MOD_MONTH)) {
//
//                UDate frYmd = new UDate();
//                frYmd.setDate(from.getYear(), from.getMonth(), 1);
//                UDate toYmd = new UDate();
//                toYmd.setDate(to.getYear(), to.getMonth(), to
//                        .getMaxDayOfMonth());
//                String fromDateStr = frYmd.getDateStringForSql();
//                String toDateStr = to.getDateStringForSql();
//
//                sql.addSql(" and");
//                sql.addSql("  (");
//                sql.addSql("   (");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
//                sql.addSql("   )");
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
//                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
//                sql.addSql("   )");
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("    cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("    between");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("    and");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
//                sql.addSql("   )");
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("    cast('" + toDateStr + "' as DATE)");
//                sql.addSql("    between");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("    and");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
//                sql.addSql("   )");
//                sql.addSql("  )");
//            }
//
//            if (mod.equals(GSConstNippou.DSP_MOD_DAY)) {
//                String fromDateTmp = from.toSQLTimestamp().toString();
//                String toDateTmp = to.toSQLTimestamp().toString();
//                String fromDateYmd = from.getDateStringForSql();
//                String toDateYmd = to.getDateStringForSql();
//
//                sql.addSql(" and");
//                sql.addSql("   (");
//                sql.addSql("     (");
//                sql.addSql("      SCD_FR_DATE >= ?");
//                sql.addSql("      and");
//                sql.addSql("      SCD_FR_DATE <= ?");
//                sql.addSql("     )");
//                sql.addSql("     or");
//                sql.addSql("     (");
//                sql.addSql("      SCD_TO_DATE > ?");
//                sql.addSql("      and");
//                sql.addSql("      SCD_TO_DATE <= ?");
//                sql.addSql("     )");
//                sql.addSql("     or");
//                sql.addSql("     (");
//                sql.addSql("      ? >= SCD_FR_DATE");
//                sql.addSql("      and");
//                sql.addSql("      ? < SCD_TO_DATE");
//                sql.addSql("     )");
//                sql.addSql("     or");
//                sql.addSql("     (");
//                sql.addSql("      ? >= SCD_FR_DATE");
//                sql.addSql("      and");
//                sql.addSql("      ? < SCD_TO_DATE");
//                sql.addSql("     )");
//
//                sql.addValue(fromDateTmp);
//                sql.addValue(toDateTmp);
//                sql.addValue(fromDateTmp);
//                sql.addValue(toDateTmp);
//                sql.addValue(fromDateTmp);
//                sql.addValue(fromDateTmp);
//                sql.addValue(toDateTmp);
//                sql.addValue(toDateTmp);
//
//                sql.addSql("     or");
//                sql.addSql("     (");
//                sql.addSql("      SCD_FR_DATE >= ?");
//                sql.addSql("      and");
//                sql.addSql("      SCD_FR_DATE <= ?");
//                sql.addSql("      and");
//                sql.addSql("      SCD_DAILY = 1");
//                sql.addSql("     )");
//                sql.addSql("     or");
//                sql.addSql("     (");
//                sql.addSql("      SCD_TO_DATE > ?");
//                sql.addSql("      and");
//                sql.addSql("      SCD_TO_DATE <= ?");
//                sql.addSql("      and");
//                sql.addSql("      SCD_DAILY = 1");
//                sql.addSql("     )");
//                sql.addSql("     or");
//                sql.addSql("     (");
//                sql.addSql("      ? >= SCD_FR_DATE");
//                sql.addSql("      and");
//                sql.addSql("      ? < SCD_TO_DATE");
//                sql.addSql("      and");
//                sql.addSql("      SCD_DAILY = 1");
//                sql.addSql("     )");
//                sql.addSql("     or");
//                sql.addSql("     (");
//                sql.addSql("      ? >= SCD_FR_DATE");
//                sql.addSql("      and");
//                sql.addSql("      ? < SCD_TO_DATE");
//                sql.addSql("      and");
//                sql.addSql("      SCD_DAILY = 1");
//                sql.addSql("     )");
//                sql.addSql("   )");
//
//                sql.addValue(fromDateTmp);
//                sql.addValue(toDateTmp);
//                sql.addValue(fromDateTmp);
//                sql.addValue(toDateTmp);
//                sql.addValue(fromDateTmp);
//                sql.addValue(fromDateTmp);
//                sql.addValue(toDateTmp);
//                sql.addValue(toDateTmp);
//            }
//            sql.addSql(" order by ");
//            sql.addSql("   SCD_USR_SID,");
//            sql.addSql("   SCD_DAILY DESC,");
//            sql.addSql("   SCD_FR_DATE,");
//            sql.addSql("   SCD_SID");
//
//            log__.info(sql.toLogString());
//            pstmt = con.prepareStatement(sql.toSqlString());
//
//            sql.setParameter(pstmt);
//            log__.info(sql.toLogString());
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                ret.add(__getNtpDataFromRs(rs));
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return ret;
//    }
    /**
     * <p>
     * グループSIDのリスト、公開区分、日付範囲を指定し日報情報を取得する
     *
     * @param sidList
     *            グループSIDのリスト
     * @param pub
     *            公開区分 ※-1を指定すると条件から除外されます
     * @param from
     *            日付from
     * @param to
     *            日付to
     * @param mod
     *            取得モード (週間:"1" 月間:"2" 日間:"3")
     * @return ArrayList in NtpDataModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<NtpDataModel> getBelongGroupNtpData2(ArrayList<Integer> sidList, int pub,
            UDate from, UDate to, String mod) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<NtpDataModel> ret = new ArrayList<NtpDataModel>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   NIP_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   NIP_DATE,");
            sql.addSql("   NIP_FR_TIME,");
            sql.addSql("   NIP_TO_TIME,");
            sql.addSql("   NIP_KADO_HH,");
            sql.addSql("   NIP_KADO_MM,");
            sql.addSql("   NIP_MGY_SID,");
            sql.addSql("   NAN_SID,");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ABA_SID,");
            sql.addSql("   NIP_TITLE,");
            sql.addSql("   NIP_TITLE_CLO,");
            sql.addSql("   MPR_SID,");
            sql.addSql("   MKB_SID,");
            sql.addSql("   MKH_SID,");
            sql.addSql("   NIP_TIEUP_SID,");
            sql.addSql("   NIP_KEIZOKU,");
            sql.addSql("   NIP_ACTEND,");
            sql.addSql("   NIP_DETIAL,");
            sql.addSql("   NIP_ACTION_DATE,");
            sql.addSql("   NIP_ACTION,");
            sql.addSql("   NIP_ACTDATE_KBN,");
            sql.addSql("   NIP_ASSIGN,");
            sql.addSql("   NIP_KINGAKU,");
            sql.addSql("   NIP_MIKOMI,");
            sql.addSql("   NIP_SYOKAN,");
            sql.addSql("   NIP_PUBLIC,");
            sql.addSql("   NIP_EDIT,");
            sql.addSql("   NEX_SID,");
            sql.addSql("   NIP_AUID,");
            sql.addSql("   NIP_ADATE,");
            sql.addSql("   NIP_EUID,");
            sql.addSql("   NIP_EDATE");
            sql.addSql(" from ");
            sql.addSql("   NTP_DATA");
            sql.addSql(" where ");
            sql.addSql("   USR_SID in ( ");
            if (sidList.size() < 1) {
                sql.addSql("   -1");
            }
            for (int i = 0; i < sidList.size(); i++) {
                if (i == 0) {
                    sql.addSql("   ?");
                } else {
                    sql.addSql("   ,?");
                }
                sql.addIntValue(sidList.get(i).intValue());
            }
            sql.addSql(" ) ");
            if (pub != -1) {
                sql.addSql(" and ");
                sql.addSql("   NIP_PUBLIC = ?");
                sql.addIntValue(pub);
            }


            if (mod.equals(GSConstNippou.DSP_MOD_WEEK)) {

                String fromDateStr = from.getDateStringForSql();
                String toDateStr = to.getDateStringForSql();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
                sql.addSql("     NIP_DATE");
                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
                sql.addSql("   )");
                sql.addSql("  )");
            }

            if (mod.equals(GSConstNippou.DSP_MOD_MONTH)) {

                UDate frYmd = new UDate();
                frYmd.setDate(from.getYear(), from.getMonth(), 1);
                UDate toYmd = new UDate();
                toYmd.setDate(to.getYear(), to.getMonth(), to
                        .getMaxDayOfMonth());
                String fromDateStr = frYmd.getDateStringForSql();
                String toDateStr = to.getDateStringForSql();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
                sql.addSql("     NIP_DATE");
                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
                sql.addSql("   )");
                sql.addSql("  )");
            }

            if (mod.equals(GSConstNippou.DSP_MOD_DAY)) {
                String fromDateTmp = from.toSQLTimestamp().toString();

                sql.addSql(" and");
                sql.addSql("      NIP_DATE = ?");

                sql.addStrValue(fromDateTmp);

            }
            sql.addSql(" order by ");
            sql.addSql("   USR_SID,");
            sql.addSql("   NIP_DATE,");
            sql.addSql("   NIP_SID");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getNtpDataFromRs(rs));
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
     * ユーザ(複数)、ユーザ区分、公開区分、日付範囲を指定し日報情報を取得する
     * @param belongList
     *            ユーザ情報リスト
     * @param usrKbn
     *            ユーザ区分
     * @param pub
     *            公開区分 ※-1を指定すると条件から除外されます
     * @param from
     *            日付from
     * @param to
     *            日付to
     * @param mod
     *            取得モード (週間:"1" 月間:"2" 日間:"3")
     * @return ArrayList in NtpDataModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<NtpDataModel> selectUsers(
            ArrayList<UserSearchModel> belongList, int usrKbn, int pub,
            UDate from, UDate to, String mod) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<NtpDataModel> ret = new ArrayList<NtpDataModel>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   NIP_SID,");
            sql.addSql("   NTP_DATA.USR_SID,");
            sql.addSql("   NIP_DATE,");
            sql.addSql("   NIP_FR_TIME,");
            sql.addSql("   NIP_TO_TIME,");
            sql.addSql("   NIP_KADO_HH,");
            sql.addSql("   NIP_KADO_MM,");
            sql.addSql("   NIP_MGY_SID,");
            sql.addSql("   NAN_SID,");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ABA_SID,");
            sql.addSql("   NIP_TITLE,");
            sql.addSql("   NIP_TITLE_CLO,");
            sql.addSql("   MPR_SID,");
            sql.addSql("   MKB_SID,");
            sql.addSql("   MKH_SID,");
            sql.addSql("   NIP_TIEUP_SID,");
            sql.addSql("   NIP_KEIZOKU,");
            sql.addSql("   NIP_ACTEND,");
            sql.addSql("   NIP_DETAIL,");
            sql.addSql("   NIP_ACTION_DATE,");
            sql.addSql("   NIP_ACTION,");
            sql.addSql("   NIP_ACTDATE_KBN,");
            sql.addSql("   NIP_ASSIGN,");
            sql.addSql("   NIP_KINGAKU,");
            sql.addSql("   NIP_MIKOMI,");
            sql.addSql("   NIP_SYOKAN,");
            sql.addSql("   NIP_PUBLIC,");
            sql.addSql("   NIP_EDIT,");
            sql.addSql("   NEX_SID,");
            sql.addSql("   NIP_AUID,");
            sql.addSql("   NIP_ADATE,");
            sql.addSql("   NIP_EUID,");
            sql.addSql("   NIP_EDATE,");
            sql.addSql("   CMN_USRM_INF.USI_SEI, ");
            sql.addSql("   CMN_USRM_INF.USI_MEI ");
            sql.addSql(" from ");
            sql.addSql("   NTP_DATA,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where ");
            sql.addSql("   NTP_DATA.USR_SID = CMN_USRM_INF.USR_SID ");
            sql.addSql(" and");
            sql.addSql("   NTP_DATA.USR_SID in(");
            CmnUsrmInfModel uMdl = null;
            if (belongList.size() < 1) {
                sql.addSql("   -1");
            }
            for (int i = 0; i < belongList.size(); i++) {
                uMdl = belongList.get(i);
                if (i == 0) {
                    sql.addSql("   ?");
                } else {
                    sql.addSql("   ,?");
                }
                sql.addIntValue(uMdl.getUsrSid());
            }
            sql.addSql("   )");

            if (pub != -1) {
                sql.addSql(" and ");
                sql.addSql("   NIP_PUBLIC = ?");
                sql.addIntValue(pub);
            }

            if (mod.equals(GSConstNippou.DSP_MOD_WEEK)) {

                String fromDateStr = from.getDateStringForSql();
                String toDateStr = to.getDateStringForSql();

                sql.addSql(" and");
                sql.addSql("     NIP_DATE");
                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
            }

            if (mod.equals(GSConstNippou.DSP_MOD_MONTH)) {

                UDate frYmd = new UDate();
                frYmd.setDate(from.getYear(), from.getMonth(), 1);
                UDate toYmd = new UDate();
                toYmd.setDate(to.getYear(), to.getMonth(), to
                        .getMaxDayOfMonth());
                String fromDateStr = frYmd.getDateStringForSql();
                String toDateStr = toYmd.getDateStringForSql();

                sql.addSql(" and");
                sql.addSql("     NTP_DATE");
                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
            }

            if (mod.equals(GSConstNippou.DSP_MOD_DAY)) {
                String fromDateTmp = from.toSQLTimestamp().toString();

                sql.addSql(" and");
                sql.addSql("      NIP_DATE = ?");

                sql.addStrValue(fromDateTmp);
            }
            sql.addSql(" order by ");
            sql.addSql("   NTP_DATA.USR_SID,");
            sql.addSql("   NIP_FR_TIME,");
            sql.addSql("   NIP_SID");
            /** ******************************************* */

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getNtpDataPlusNameFromRs(rs));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

//    /**
//     * <p>
//     * 日報情報より日報確認情報を取得する
//     * @param nippouList
//     *            日報情報リスト
//     * @return ArrayList in NtpKakuninModel
//     * @throws SQLException
//     *             SQL実行例外
//     */
//    public ArrayList<NtpKakuninModel> selectkaunin(
//            ArrayList<NtpDataModel> nippouList) throws SQLException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        ArrayList<NtpKakuninModel> ret = new ArrayList<NtpKakuninModel>();
//        con = getCon();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//            sql.addSql(" select ");
//            sql.addSql("   NKK_SID,");
//            sql.addSql("   NIP_SID,");
//            sql.addSql("   USR_SID,");
//            sql.addSql("   NKK_CHECK,");
//            sql.addSql("   NKK_DATE_CHECK ");
//            sql.addSql(" from ");
//            sql.addSql("   NTP_KAKUNIN ");
//            sql.addSql(" where ");
//            sql.addSql("   NIP_SID in(");
//            NtpDataModel uMdl = null;
//            if (nippouList.size() < 1) {
//                sql.addSql("   -1");
//            }
//            for (int i = 0; i < nippouList.size(); i++) {
//                uMdl = nippouList.get(i);
//                if (i == 0) {
//                    sql.addSql("   ?");
//                } else {
//                    sql.addSql("   ,?");
//                }
//                sql.addIntValue(uMdl.getNipSid());
//            }
//            sql.addSql("   )");
//
//            sql.addSql(" order by ");
//            sql.addSql("   NIP_SID,");
//            sql.addSql("   USR_SID");
//
//            pstmt = con.prepareStatement(sql.toSqlString());
//
//            sql.setParameter(pstmt);
//            log__.info(sql.toLogString());
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                ret.add(__getNtpKakuninFromRs(rs));
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return ret;
//    }

    /**
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     * @param searchMdl
     *            検索用モデル
     * @param sessionUsrSid
     *            セッションユーザSID
     * @param offset
     *            オフセット有無(オフセットしない場合は取得スケジュール全てを取得)
     * @return ArrayList in SchDataModel
     * @throws SQLException
     *             SQL実行例外
     */
//    public ArrayList<NtpDataModel> select(NippouListSearchModel searchMdl,
//            int sessionUsrSid, boolean offset) throws SQLException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        ArrayList<NtpDataModel> ret = new ArrayList<NtpDataModel>();
//        con = getCon();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//            sql.addSql(" select ");
//            sql.addSql("   SCH.SCD_SID,");
//            sql.addSql("   SCH.SCD_USR_SID,");
//            sql.addSql("   SCH.SCD_GRP_SID,");
//            sql.addSql("   SCH.SCD_USR_KBN,");
//            sql.addSql("   SCH.SCD_FR_DATE,");
//            sql.addSql("   SCH.SCD_TO_DATE,");
//            sql.addSql("   SCH.SCD_DAILY,");
//            sql.addSql("   SCH.SCD_BGCOLOR,");
//            sql.addSql("   SCH.SCD_TITLE,");
//            sql.addSql("   SCH.SCD_VALUE,");
//            sql.addSql("   SCH.SCD_BIKO,");
//            sql.addSql("   SCH.SCD_PUBLIC,");
//            sql.addSql("   SCH.SCD_AUID,");
//            sql.addSql("   SCH.SCD_ADATE,");
//            sql.addSql("   SCH.SCD_EUID,");
//            sql.addSql("   SCH.SCD_EDATE,");
//            sql.addSql("   SCH.SCE_SID,");
//            sql.addSql("   SCD_RSSID,");
//            sql.addSql("   SCH.SCD_EDIT,");
//            sql.addSql("   case");
//            sql
//                    .addSql("   when SCH.SCD_USR_KBN=0 then USRM.USI_SEI_KN || USRM.USI_MEI_KN");
//            sql.addSql("   else GROUPM.GRP_NAME_KN");
//            sql.addSql("   end USR_NAME");
//            sql.addSql(" from");
//            sql.addSql("   ((SCH_DATA SCH");
//            sql.addSql("     left join CMN_USRM_INF USRM on SCH.SCD_USR_SID = USRM.USR_SID)");
//            sql.addSql("     left join CMN_GROUPM GROUPM on SCH.SCD_USR_SID = GROUPM.GRP_SID)");
//            sql.addSql(" where ");
//            sql.addSql("  (");
//            sql.addSql("   (");
//            sql.addSql("   SCH.SCD_USR_SID = ?");
//            if (NtpCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
//                sql.addValue(-1);
//            } else {
//                sql.addValue(NtpCommonBiz.getDspGroupSid(searchMdl
//                        .getSchSltGroupSid()));
//            }
//            sql.addSql(" and ");
//            sql.addSql("   SCH.SCD_USR_KBN = ?");
//            sql.addValue(GSConstNippou.USER_KBN_GROUP);
//            if (searchMdl.getSchPublic() != -1) {
//                sql.addSql(" and ");
//                sql.addSql("   SCH.SCD_PUBLIC = ?");
//                sql.addValue(searchMdl.getSchPublic());
//            } else {
//                sql.addSql(" and ");
//                sql.addSql("   SCH.SCD_PUBLIC <> ?");
//                sql.addValue(GSConstNippou.DSP_NOT_PUBLIC);
//            }
//            sql.addSql("   )");
//            if (searchMdl.getSchSltUserSid() != -1) {
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("   SCH.SCD_USR_SID = ?");
//                sql.addValue(searchMdl.getSchSltUserSid());
//                sql.addSql(" and ");
//                sql.addSql("   SCH.SCD_USR_KBN = ?");
//                sql.addValue(GSConstNippou.USER_KBN_USER);
//                if (searchMdl.getSchSltUserSid() != sessionUsrSid) {
//                    if (searchMdl.getSchPublic() != -1) {
//                        sql.addSql(" and ");
//                        sql.addSql("   SCH.SCD_PUBLIC = ?");
//                        sql.addValue(searchMdl.getSchPublic());
//                    } else {
//                        sql.addSql(" and ");
//                        sql.addSql("   SCH.SCD_PUBLIC <> ?");
//                        sql.addValue(GSConstNippou.DSP_NOT_PUBLIC);
//
//                    }
//                }
//
//                sql.addSql("   )");
//            } else {
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("   SCH.SCD_USR_SID in (");
//
//                if (NtpCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
//                    sql.addSql("   select");
//                    sql.addSql("     CMN_MY_GROUP_MS.MGM_SID");
//                    sql.addSql("   from");
//                    sql.addSql("     CMN_MY_GROUP_MS");
//                    sql.addSql("   where");
//                    sql.addSql("     CMN_MY_GROUP_MS.MGP_SID = ?");
//                    sql.addSql("   and");
//                    sql.addSql("     CMN_MY_GROUP_MS.USR_SID = ?");
//                    sql.addValue(NtpCommonBiz.getDspGroupSid(searchMdl
//                            .getSchSltGroupSid()));
//                    sql.addValue(sessionUsrSid);
//                } else {
//                    sql.addSql("   select");
//                    sql.addSql("     CMN_BELONGM.USR_SID");
//                    sql.addSql("   from");
//                    sql.addSql("     CMN_BELONGM");
//                    sql.addSql("   where");
//                    sql.addSql("     CMN_BELONGM.GRP_SID = ?");
//                    sql.addValue(searchMdl.getSchSltGroupSid());
//                }
//
//                sql.addSql("   )");
//                sql.addSql(" and ");
//                sql.addSql("   SCH.SCD_USR_KBN = ?");
//                sql.addValue(GSConstNippou.USER_KBN_USER);
//                if (searchMdl.getSchPublic() != -1) {
//                    sql.addSql(" and ");
//                    sql.addSql("   SCH.SCD_PUBLIC = ?");
//                    sql.addValue(searchMdl.getSchPublic());
//                } else {
//                    sql.addSql(" and ");
//                    sql.addSql("   SCH.SCD_PUBLIC <> ?");
//                    sql.addValue(GSConstNippou.DSP_NOT_PUBLIC);
//                }
//                sql.addSql("   )");
////                sql.addSql("  or");
////                sql.addSql("  (");
////                sql.addSql("   SCH.SCD_USR_SID = ?");
////                sql.addSql(" and ");
////                sql.addSql("   SCH.SCD_USR_KBN = 0");
////                sql.addSql("   )");
////                sql.addValue(sessionUsrSid);
//            }
//
//            sql.addSql("  )");
//            UDate dayUd = new UDate();
//            /** ********** 時間設定無し (h2用) *********** */
//            String startDateFrStr = searchMdl.getSchStartDateFr()
//                    .getDateStringForSql();
//            String startDateToStr = searchMdl.getSchStartDateTo()
//                    .getDateStringForSql();
//            String endDateFrStr = searchMdl.getSchEndDateFr()
//                    .getDateStringForSql();
//            String endDateToStr = searchMdl.getSchEndDateTo()
//                    .getDateStringForSql();
//            sql.addSql(" and");
//            sql.addSql("   (");
//            sql.addSql("     cast(SCD_FR_DATE as DATE)");
//            sql.addSql("     between cast('" + startDateFrStr + "' as DATE)");
//            sql.addSql("     and cast('" + startDateToStr + "' as DATE)");
//            sql.addSql("   )");
//            sql.addSql("   and");
//            sql.addSql("   (");
//            sql.addSql("     cast(SCD_TO_DATE as DATE)");
//            sql.addSql("     between cast('" + endDateFrStr + "' as DATE)");
//            sql.addSql("     and cast('" + endDateToStr + "' as DATE)");
//            sql.addSql("   )");
//            // KEYワード
//            __cleateKeyWord(searchMdl, sql);
//
//            //タイトルカラー
//            if (searchMdl.getBgcolor() != null && searchMdl.getBgcolor().length > 0) {
//                String[] bgColorList = searchMdl.getBgcolor();
//                for (int i = 0; i < bgColorList.length; i++) {
//                    if (i == 0) {
//                        sql.addSql(" and ");
//                        sql.addSql(" ( ");
//                    } else {
//                        sql.addSql(" or ");
//                    }
//                    sql.addSql(" SCH.SCD_BGCOLOR = ?");
//                    sql.addValue(bgColorList[i]);
//                }
//                sql.addSql(" ) ");
//            }
//
//            String orderStr = "";
//            // オーダー
//            if (searchMdl.getSchOrder() == GSConstNippou.ORDER_KEY_ASC) {
//                orderStr = "  asc";
//            } else if (searchMdl.getSchOrder() == GSConstNippou.ORDER_KEY_DESC) {
//                orderStr = "  desc";
//            }
//
//            sql.addSql(" order by ");
//            switch (searchMdl.getSchSortKey()) {
//            case GSConstNippou.SORT_KEY_NAME:
//                sql.addSql("   USR_NAME " + orderStr + ",");
//                break;
//            case GSConstNippou.SORT_KEY_FRDATE:
//                sql.addSql("   SCH.SCD_FR_DATE " + orderStr + ",");
//                break;
//            case GSConstNippou.SORT_KEY_TODATE:
//                sql.addSql("   SCH.SCD_TO_DATE " + orderStr + ",");
//                break;
//            case GSConstNippou.SORT_KEY_TITLE:
//                sql.addSql("   SCH.SCD_TITLE " + orderStr + ",");
//                break;
//            default:
//                break;
//            }
//
//            String orderStr2 = "";
//            // オーダー
//            if (searchMdl.getSchOrder2() == GSConstNippou.ORDER_KEY_ASC) {
//                orderStr2 = "  asc";
//            } else if (searchMdl.getSchOrder2() == GSConstNippou.ORDER_KEY_DESC) {
//                orderStr2 = "  desc";
//            }
//            switch (searchMdl.getSchSortKey2()) {
//            case GSConstNippou.SORT_KEY_NAME:
//                sql.addSql("   USR_NAME " + orderStr2 + ",");
//                sql.addSql("   SCD_SID");
//                break;
//            case GSConstNippou.SORT_KEY_FRDATE:
//                sql.addSql("   SCH.SCD_FR_DATE " + orderStr2 + ",");
//                sql.addSql("   SCH.SCD_SID");
//                break;
//            case GSConstNippou.SORT_KEY_TODATE:
//                sql.addSql("   SCH.SCD_TO_DATE " + orderStr2 + ",");
//                sql.addSql("   SCH.SCD_SID");
//                break;
//            case GSConstNippou.SORT_KEY_TITLE:
//                sql.addSql("   SCH.SCD_TITLE " + orderStr2 + ",");
//                sql.addSql("   SCH.SCD_SID");
//                break;
//            default:
//                break;
//            }
//
//            pstmt = con.prepareStatement(sql.toSqlString(),
//                    ResultSet.TYPE_SCROLL_INSENSITIVE,
//                    ResultSet.CONCUR_READ_ONLY);
//
//            sql.setParameter(pstmt);
//            log__.info(sql.toLogString());
//            rs = pstmt.executeQuery();
//            if (searchMdl.getSchOffset() > 1 && offset) {
//                rs.absolute(searchMdl.getSchOffset() - 1);
//            }
//
//            if (offset) {
//                for (int i = 0; rs.next() && i < searchMdl.getSchLimit(); i++) {
//                    ret.add(__getNtpDataFromRs(rs));
//                }
//            } else {
//                for (int i = 0; rs.next(); i++) {
//                    ret.add(__getNtpDataFromRs(rs));
//                }
//            }
//
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return ret;
//    }

    /**
     * <br>
     * [機 能] SQL(キーワード入力時の検索条件)を作成 <br>
     * [解 説] <br>
     * [備 考]
     * @param bean CirSearchModel
     * @param sql SqlBuffer
     */
    private void __cleateKeyWord(NippouListSearchModel bean, SqlBuffer sql) {

        // キーワード入力時
        List<String> keywordList = bean.getNtpKeyValue();
        if (keywordList != null && keywordList.size() > 0) {

            String keywordJoin = "  and";
            if (bean.getKeyWordkbn() == GSConstNippou.KEY_WORD_KBN_OR) {
                keywordJoin = "   or";
            }

            // 、検索対象の「タイトル」がチェック済みの場合
            if (bean.isTargetTitle()) {
                sql.addSql("  and");
                if (bean.isTargetValue()) {
                    sql.addSql("    (");
                }
                sql.addSql("      (");
                for (int i = 0; i < keywordList.size(); i++) {
                    if (i > 0) {
                        sql.addSql(keywordJoin);
                    }
                    sql.addSql("       NTP.NIP_TITLE like '%"
                            + JDBCUtil.encFullStringLike(keywordList.get(i))
                            + "%' ESCAPE '" + JDBCUtil.def_esc + "'");
                }
                sql.addSql("      )");
            }

            if (bean.isTargetValue()) {
                if (bean.isTargetTitle()) {
                    sql.addSql("      or");
                } else {
                    sql.addSql("      and");
                }
                sql.addSql("      (");
                for (int i = 0; i < keywordList.size(); i++) {
                    if (i > 0) {
                        sql.addSql(keywordJoin);
                    }
                    sql.addSql("       NTP.NIP_DETAIL like '%"
                            + JDBCUtil.encFullStringLike(keywordList.get(i))
                            + "%' ESCAPE '" + JDBCUtil.def_esc + "'");
                }
                sql.addSql("      )");

                if (bean.isTargetTitle()) {
                    sql.addSql("    )");
                }
            }
        }
    }

    /**
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しCSV用スケジュール情報を取得する
     * @param searchMdl
     *            検索用モデル
     * @param sessionUserSid
     *            セッションユーザSID(実行者)
     * @param rl
     *            SchCsvRecordListenerIppanImpl
     * @return int 明細件数
     * @throws SQLException
     *             SQL実行例外
     * @throws CSVException
     *             CSV出力時例外
     */
//    public int createSchduleForCsv(NippouListSearchModel searchMdl,
//            int sessionUserSid, SchCsvRecordListenerIppanImpl rl)
//            throws SQLException, CSVException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        int ret = 0;
//        con = getCon();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//            sql.addSql(" select ");
//            sql.addSql("   SCH.SCD_SID,");
//            sql.addSql("   SCH.SCD_USR_SID,");
//            sql.addSql("   SCH.SCD_GRP_SID,");
//            sql.addSql("   SCH.SCD_USR_KBN,");
//            sql.addSql("   SCH.SCD_FR_DATE,");
//            sql.addSql("   SCH.SCD_TO_DATE,");
//            sql.addSql("   SCH.SCD_DAILY,");
//            sql.addSql("   SCH.SCD_BGCOLOR,");
//            sql.addSql("   SCH.SCD_TITLE,");
//            sql.addSql("   SCH.SCD_VALUE,");
//            sql.addSql("   SCH.SCD_BIKO,");
//            sql.addSql("   SCH.SCD_PUBLIC,");
//            sql.addSql("   SCH.SCD_AUID,");
//            sql.addSql("   SCH.SCD_ADATE,");
//            sql.addSql("   SCH.SCD_EUID,");
//            sql.addSql("   SCH.SCD_EDATE,");
//            sql.addSql("   case");
//            sql
//                    .addSql("   when SCH.SCD_USR_KBN=0 then USRM.USI_SEI || USRM.USI_MEI");
//            sql.addSql("   else GROUPM.GRP_NAME");
//            sql.addSql("   end USR_NAME,");
//            sql.addSql("   AUSRM.USI_SEI || AUSRM.USI_MEI AUSR_NAME,");
//            sql.addSql("   EUSRM.USI_SEI || EUSRM.USI_MEI EUSR_NAME");
//            sql.addSql(" from");
//            sql.addSql("   ((((SCH_DATA SCH");
//            sql.addSql("     left join CMN_USRM_INF USRM on SCH.SCD_USR_SID = USRM.USR_SID)");
//            sql.addSql("     left join CMN_USRM_INF AUSRM on SCH.SCD_AUID = AUSRM.USR_SID)");
//            sql.addSql("     left join CMN_USRM_INF EUSRM on SCH.SCD_EUID = EUSRM.USR_SID)");
//            sql.addSql("     left join CMN_GROUPM GROUPM on SCH.SCD_USR_SID = GROUPM.GRP_SID)");
//            sql.addSql(" where ");
//            sql.addSql("  (");
//            sql.addSql("   (");
//            sql.addSql("   SCH.SCD_USR_SID = ?");
//            if (NtpCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
//                sql.addValue(-1);
//            } else {
//                sql.addValue(NtpCommonBiz.getDspGroupSid(searchMdl
//                        .getSchSltGroupSid()));
//            }
//            sql.addSql(" and ");
//            sql.addSql("   SCH.SCD_USR_KBN = ?");
//            sql.addValue(GSConstNippou.USER_KBN_GROUP);
//            if (searchMdl.getSchPublic() != -1) {
//                sql.addSql(" and ");
//                sql.addSql("   SCH.SCD_PUBLIC = ?");
//                sql.addValue(searchMdl.getSchPublic());
//            }
//            sql.addSql("   )");
//            if (searchMdl.getSchSltUserSid() != -1) {
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("   SCH.SCD_USR_SID = ?");
//                sql.addValue(searchMdl.getSchSltUserSid());
//                sql.addSql(" and ");
//                sql.addSql("   SCH.SCD_USR_KBN = ?");
//                sql.addValue(GSConstNippou.USER_KBN_USER);
//                if (searchMdl.getSchPublic() != -1) {
//                    sql.addSql(" and ");
//                    sql.addSql("   SCH.SCD_PUBLIC = ?");
//                    sql.addValue(searchMdl.getSchPublic());
//                }
//                sql.addSql("   )");
//            } else {
//                sql.addSql("   or");
//                sql.addSql("   (");
//                sql.addSql("   SCH.SCD_USR_SID in (");
//                if (NtpCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
//                    sql.addSql("   select");
//                    sql.addSql("     CMN_MY_GROUP_MS.MGM_SID");
//                    sql.addSql("   from");
//                    sql.addSql("     CMN_MY_GROUP_MS");
//                    sql.addSql("   where");
//                    sql.addSql("     CMN_MY_GROUP_MS.MGP_SID = ?");
//                    sql.addSql("   and");
//                    sql.addSql("     CMN_MY_GROUP_MS.USR_SID = ?");
//                    sql.addValue(NtpCommonBiz.getDspGroupSid(searchMdl
//                            .getSchSltGroupSid()));
//                    sql.addValue(sessionUserSid);
//                } else {
//                    sql.addSql("   select");
//                    sql.addSql("     CMN_BELONGM.USR_SID");
//                    sql.addSql("   from");
//                    sql.addSql("     CMN_BELONGM");
//                    sql.addSql("   where");
//                    sql.addSql("     CMN_BELONGM.GRP_SID = ?");
//                    sql.addValue(searchMdl.getSchSltGroupSid());
//                }
//
//                sql.addSql("   )");
//                sql.addSql(" and ");
//                sql.addSql("   SCH.SCD_USR_KBN = ?");
//                sql.addValue(GSConstNippou.USER_KBN_USER);
//                if (searchMdl.getSchPublic() != -1) {
//                    sql.addSql(" and ");
//                    sql.addSql("   SCH.SCD_PUBLIC = ?");
//                    sql.addValue(searchMdl.getSchPublic());
//                }
//                sql.addSql("   )");
//            }
//            sql.addSql("  )");
//            UDate dayUd = new UDate();
//            /** ********** 時間設定無し (h2用) *********** */
//            String startDateFrStr = searchMdl.getSchStartDateFr()
//                    .getDateStringForSql();
//            String startDateToStr = searchMdl.getSchStartDateTo()
//                    .getDateStringForSql();
//            String endDateFrStr = searchMdl.getSchEndDateFr()
//                    .getDateStringForSql();
//            String endDateToStr = searchMdl.getSchEndDateTo()
//                    .getDateStringForSql();
//            sql.addSql(" and");
//            sql.addSql("   (");
//            sql.addSql("     cast(SCD_FR_DATE as DATE)");
//            sql.addSql("     between cast('" + startDateFrStr + "' as DATE)");
//            sql.addSql("     and cast('" + startDateToStr + "' as DATE)");
//            sql.addSql("   )");
//            sql.addSql("   and");
//            sql.addSql("   (");
//            sql.addSql("     cast(SCD_TO_DATE as DATE)");
//            sql.addSql("     between cast('" + endDateFrStr + "' as DATE)");
//            sql.addSql("     and cast('" + endDateToStr + "' as DATE)");
//            sql.addSql("   )");
//
//            // KEYワード
//            __cleateKeyWord(searchMdl, sql);
//
//            //タイトルカラー
//            if (searchMdl.getBgcolor() != null && searchMdl.getBgcolor().length > 0) {
//                String[] bgColorList = searchMdl.getBgcolor();
//                for (int i = 0; i < bgColorList.length; i++) {
//                    if (i == 0) {
//                        sql.addSql(" and ");
//                        sql.addSql(" ( ");
//                    } else {
//                        sql.addSql(" or ");
//                    }
//                    sql.addSql(" SCH.SCD_BGCOLOR = ?");
//                    sql.addValue(bgColorList[i]);
//                }
//                sql.addSql(" ) ");
//            }
//
//            String orderStr = "";
//            // オーダー
//            if (searchMdl.getSchOrder() == GSConstNippou.ORDER_KEY_ASC) {
//                orderStr = "  asc";
//            } else if (searchMdl.getSchOrder() == GSConstNippou.ORDER_KEY_DESC) {
//                orderStr = "  desc";
//            }
//
//            sql.addSql(" order by ");
//            switch (searchMdl.getSchSortKey()) {
//            case GSConstNippou.SORT_KEY_NAME:
//                sql.addSql("   USR_NAME " + orderStr + ",");
//                sql.addSql("   SCD_SID");
//                break;
//            case GSConstNippou.SORT_KEY_FRDATE:
//                sql.addSql("   SCH.SCD_FR_DATE " + orderStr + ",");
//                sql.addSql("   SCH.SCD_SID");
//                break;
//            case GSConstNippou.SORT_KEY_TODATE:
//                sql.addSql("   SCH.SCD_TO_DATE " + orderStr + ",");
//                sql.addSql("   SCH.SCD_SID");
//                break;
//            case GSConstNippou.SORT_KEY_TITLE:
//                sql.addSql("   SCH.SCD_TITLE " + orderStr + ",");
//                sql.addSql("   SCH.SCD_SID");
//                break;
//            default:
//                break;
//            }
//
//            pstmt = con.prepareStatement(sql.toSqlString());
//            sql.setParameter(pstmt);
//            log__.info(sql.toLogString());
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                ret++;
//                ScheduleCsvModel model = __getScheduleCsvFromRs(rs);
//                setSchCsvRecordFromSchDataModel(model, sessionUserSid, rl);
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return ret;
//    }

    /**
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     * @param searchMdl 検索条件
     * @param sessionUsrSid セッションユーザSID
     * @return int データ件数
     * @throws SQLException SQL実行例外
     */
    public int getCount(NippouListSearchModel searchMdl, int sessionUsrSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();
        SqlBuffer sql = null;
        try {
            // SQL文
            sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(*) CNT");
            sql.addSql(" from ");
            sql.addSql("   NTP_DATA as NTP");
            if (searchMdl.getUsrSid() != -1) {
                sql.addSql(" where ");
                sql.addSql("   NTP.USR_SID = ?");
                sql.addIntValue(searchMdl.getUsrSid());
                sql.addSql(" and");
            } else {
                sql.addSql(" where ");
            }

//            UDate dayUd = new UDate();
            /** ********** 時間設定無し (h2用) *********** */
            String startDateFrStr = searchMdl.getNtpDateFrom()
                    .getDateStringForSql();
            String startDateToStr = searchMdl.getNtpDateTo()
                    .getDateStringForSql();

            sql.addSql("   (");
            sql.addSql("     NIP_DATE");
            sql.addSql("     between cast('" + startDateFrStr + "' as DATE)");
            sql.addSql("     and cast('" + startDateToStr + "' as DATE)");
            sql.addSql("   )");


            if (searchMdl.getUsrSid() == -1
                    && NtpCommonBiz.getDspGroupSid(searchMdl.getNtpSltGroupSid()) != -1) {
                sql.addSql("   and");
                sql.addSql("   (");
                sql.addSql("   NTP.USR_SID in (");

//                if (SchCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
                if (searchMdl.isMyGrpFlg()) {
                    sql.addSql("   select");
                    sql.addSql("     CMN_MY_GROUP_MS.MGM_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_MY_GROUP_MS");
                    sql.addSql("   where");
                    sql.addSql("     CMN_MY_GROUP_MS.MGP_SID = ?");
                    sql.addSql("   and");
                    sql.addIntValue(NtpCommonBiz.getDspGroupSid(searchMdl
                            .getNtpSltGroupSid()));

                    sql.addSql("   (");
                    sql.addSql("   CMN_MY_GROUP_MS.USR_SID = ?");
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("   or");
                    sql.addSql("   CMN_MY_GROUP_MS.MGP_SID in (");
                    sql.addSql("     select MGP_SID");
                    sql.addSql("     from CMN_MY_GROUP_SHARE");
                    sql.addSql("     where ");
                    sql.addSql("       MGS_USR_SID = ?");
                    sql.addSql("       or (MGS_USR_SID = -1 and MGS_GRP_SID in (");
                    sql.addSql("         select GRP_SID from CMN_BELONGM");
                    sql.addSql("         where USR_SID=?");
                    sql.addSql("         )");
                    sql.addSql("       )");
                    sql.addSql("     )");
                    sql.addIntValue(sessionUsrSid);
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("   )");
                } else {
                    sql.addSql("   select");
                    sql.addSql("     CMN_BELONGM.USR_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_BELONGM");
                    sql.addSql("   where");
                    sql.addSql("     CMN_BELONGM.GRP_SID = ?");
                    sql.addIntValue(Integer.parseInt(searchMdl.getNtpSltGroupSid()));
                }
                sql.addSql("   )");
                sql.addSql("   )");
            }

            //案件
            if (searchMdl.getAnkenSid() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.NAN_SID = ?");
                sql.addIntValue(searchMdl.getAnkenSid());
            }

            //活動分類
            if (searchMdl.getKatudouBunrui() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.MKB_SID = ?");
                sql.addIntValue(searchMdl.getKatudouBunrui());
            }

            //活動方法
            if (searchMdl.getKatudouHouhou() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.MKH_SID = ?");
                sql.addIntValue(searchMdl.getKatudouHouhou());
            }

            //企業コード
            if (searchMdl.getCompanySid() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.ACO_SID = ?");
                sql.addIntValue(searchMdl.getCompanySid());

                if (searchMdl.getCompanyBaseSid() > 0) {
                    sql.addSql("   and");
                    sql.addSql("   NTP.ABA_SID = ?");
                    sql.addIntValue(searchMdl.getCompanyBaseSid());
                }

            }

            // KEYワード
            __cleateKeyWord(searchMdl, sql);

            //タイトルカラーコメント
            if (searchMdl.getBgcolor() != null && searchMdl.getBgcolor().length > 0) {
                String[] bgColorList = searchMdl.getBgcolor();
                for (int i = 0; i < bgColorList.length; i++) {
                    if (i == 0) {
                        sql.addSql(" and ");
                        sql.addSql(" ( ");
                    } else {
                        sql.addSql(" or ");
                    }
                    sql.addSql(" NTP.NIP_TITLE_CLO = ?");
                    sql.addIntValue(Integer.parseInt(bgColorList[i]));
                }
                sql.addSql(" ) ");
            }

            //見込み度
            if (searchMdl.getMikomido() != null && searchMdl.getMikomido().length > 0) {
                String[] mikomidoList = searchMdl.getMikomido();
                for (int i = 0; i < mikomidoList.length; i++) {
                    if (i == 0) {
                        sql.addSql(" and ");
                        sql.addSql(" ( ");
                    } else {
                        sql.addSql(" or ");
                    }
                    sql.addSql(" NTP.NIP_MIKOMI = ?");
                    sql.addIntValue(Integer.parseInt(mikomidoList[i]));
                }
                sql.addSql(" ) ");
            }

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("CNT");
            }
        } catch (SQLException e) {
            log__.debug("SQL=>" + sql.toLogString());
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     * @param searchMdl 検索用モデル
     * @param sessionUsrSid セッションユーザSID
     * @param offset オフセット有無(オフセットしない場合は取得スケジュール全てを取得)
     * @return ArrayList in SchDataModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<NtpDataModel> select(NippouListSearchModel searchMdl,
            int sessionUsrSid, boolean offset) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<NtpDataModel> ret = new ArrayList<NtpDataModel>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   NTP.NIP_SID,");
            sql.addSql("   NTP.USR_SID,");
            sql.addSql("   NTP.NIP_DATE,");
            sql.addSql("   NTP.NIP_FR_TIME,");
            sql.addSql("   NTP.NIP_TO_TIME,");
            sql.addSql("   NTP.NIP_KADO_HH,");
            sql.addSql("   NTP.NIP_KADO_MM,");
            sql.addSql("   NTP.NIP_MGY_SID,");
            sql.addSql("   NTP.NAN_SID,");
            sql.addSql("   NTP.ACO_SID,");
            sql.addSql("   NTP.ABA_SID,");
            sql.addSql("   NTP.NIP_TITLE,");
            sql.addSql("   NTP.NIP_TITLE_CLO,");
            sql.addSql("   NTP.MPR_SID,");
            sql.addSql("   NTP.MKB_SID,");
            sql.addSql("   NTP.MKH_SID,");
            sql.addSql("   NTP.NIP_TIEUP_SID,");
            sql.addSql("   NTP.NIP_KEIZOKU,");
            sql.addSql("   NTP.NIP_ACTEND,");
            sql.addSql("   NTP.NIP_DETAIL,");
            sql.addSql("   NTP.NIP_ACTION_DATE,");
            sql.addSql("   NTP.NIP_ACTION,");
            sql.addSql("   NTP.NIP_ACTDATE_KBN,");
            sql.addSql("   NTP.NIP_ASSIGN,");
            sql.addSql("   NTP.NIP_KINGAKU,");
            sql.addSql("   NTP.NIP_MIKOMI,");
            sql.addSql("   NTP.NIP_SYOKAN,");
            sql.addSql("   NTP.NIP_PUBLIC,");
            sql.addSql("   NTP.NIP_EDIT,");
            sql.addSql("   NTP.NEX_SID,");
            sql.addSql("   NTP.NIP_AUID,");
            sql.addSql("   NTP.NIP_ADATE,");
            sql.addSql("   NTP.NIP_EUID,");
            sql.addSql("   NTP.NIP_EDATE,");
            sql.addSql("   USRM.USI_SEI_KN || USRM.USI_MEI_KN USR_NAME");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA NTP");
            sql.addSql("     left join CMN_USRM_INF USRM on NTP.USR_SID = USRM.USR_SID");

            if (searchMdl.getUsrSid() != -1) {
                sql.addSql(" where ");
                sql.addSql("   NTP.USR_SID = ?");
                sql.addIntValue(searchMdl.getUsrSid());
                sql.addSql(" and");
            } else {
                sql.addSql(" where ");
            }

//          UDate dayUd = new UDate();
            /** ********** 時間設定無し (h2用) *********** */
            String startDateFrStr = searchMdl.getNtpDateFrom()
                    .getDateStringForSql();
            String startDateToStr = searchMdl.getNtpDateTo()
                    .getDateStringForSql();

            sql.addSql("   (");
            sql.addSql("     NIP_DATE");
            sql.addSql("     between cast('" + startDateFrStr + "' as DATE)");
            sql.addSql("     and cast('" + startDateToStr + "' as DATE)");
            sql.addSql("   )");

            if (searchMdl.getUsrSid() == -1
                    && NtpCommonBiz.getDspGroupSid(searchMdl.getNtpSltGroupSid()) != -1) {
                sql.addSql("   and");
                sql.addSql("   (");
                sql.addSql("   NTP.USR_SID in (");

//                if (SchCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
                if (searchMdl.isMyGrpFlg()) {
                    sql.addSql("   select");
                    sql.addSql("     CMN_MY_GROUP_MS.MGM_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_MY_GROUP_MS");
                    sql.addSql("   where");
                    sql.addSql("     CMN_MY_GROUP_MS.MGP_SID = ?");
                    sql.addSql("   and");
                    sql.addIntValue(NtpCommonBiz.getDspGroupSid(searchMdl
                            .getNtpSltGroupSid()));
                    sql.addSql("   (");
                    sql.addSql("   CMN_MY_GROUP_MS.USR_SID = ?");
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("   or");
                    sql.addSql("   CMN_MY_GROUP_MS.MGP_SID in (");
                    sql.addSql("     select MGP_SID");
                    sql.addSql("     from CMN_MY_GROUP_SHARE");
                    sql.addSql("     where ");
                    sql.addSql("       MGS_USR_SID = ?");
                    sql.addSql("       or (MGS_USR_SID = -1 and MGS_GRP_SID in (");
                    sql.addSql("         select GRP_SID from CMN_BELONGM");
                    sql.addSql("         where USR_SID=?");
                    sql.addSql("         )");
                    sql.addSql("       )");
                    sql.addSql("     )");
                    sql.addIntValue(sessionUsrSid);
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("   )");
                } else {
                    sql.addSql("   select");
                    sql.addSql("     CMN_BELONGM.USR_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_BELONGM");
                    sql.addSql("   where");
                    sql.addSql("     CMN_BELONGM.GRP_SID = ?");
                    sql.addIntValue(Integer.parseInt(searchMdl.getNtpSltGroupSid()));
                }
                sql.addSql("   )");
                sql.addSql("   )");
            }


            //案件
            if (searchMdl.getAnkenSid() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.NAN_SID = ?");
                sql.addIntValue(searchMdl.getAnkenSid());
            }

            //活動分類
            if (searchMdl.getKatudouBunrui() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.MKB_SID = ?");
                sql.addIntValue(searchMdl.getKatudouBunrui());
            }

            //活動方法
            if (searchMdl.getKatudouHouhou() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.MKH_SID = ?");
                sql.addIntValue(searchMdl.getKatudouHouhou());
            }

            //企業コード
            if (searchMdl.getCompanySid() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.ACO_SID = ?");
                sql.addIntValue(searchMdl.getCompanySid());

                if (searchMdl.getCompanyBaseSid() > 0) {
                    sql.addSql("   and");
                    sql.addSql("   NTP.ABA_SID = ?");
                    sql.addIntValue(searchMdl.getCompanyBaseSid());
                }

            }

            // KEYワード
            __cleateKeyWord(searchMdl, sql);

            //タイトルカラーコメント
            if (searchMdl.getBgcolor() != null && searchMdl.getBgcolor().length > 0) {
                String[] bgColorList = searchMdl.getBgcolor();
                for (int i = 0; i < bgColorList.length; i++) {
                    if (i == 0) {
                        sql.addSql(" and ");
                        sql.addSql(" ( ");
                    } else {
                        sql.addSql(" or ");
                    }
                    sql.addSql(" NTP.NIP_TITLE_CLO = ?");
                    sql.addIntValue(Integer.parseInt(bgColorList[i]));
                }
                sql.addSql(" ) ");
            }

            //見込み度
            if (searchMdl.getMikomido() != null && searchMdl.getMikomido().length > 0) {
                String[] mikomidoList = searchMdl.getMikomido();
                for (int i = 0; i < mikomidoList.length; i++) {
                    if (i == 0) {
                        sql.addSql(" and ");
                        sql.addSql(" ( ");
                    } else {
                        sql.addSql(" or ");
                    }
                    sql.addSql(" NTP.NIP_MIKOMI = ?");
                    sql.addIntValue(Integer.parseInt(mikomidoList[i]));
                }
                sql.addSql(" ) ");
            }


            String orderStr = "";
            // オーダー
            if (searchMdl.getNtpOrder() == GSConstNippou.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (searchMdl.getNtpOrder() == GSConstNippou.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            sql.addSql(" order by ");
            switch (searchMdl.getNtpSortKey()) {
            case GSConstNippou.SORT_KEY_NAME:
                sql.addSql("   USR_NAME " + orderStr + ",");
                break;
            case GSConstNippou.SORT_KEY_FRDATE:
                sql.addSql("   NTP.NIP_DATE " + orderStr + ",");
                break;
            case GSConstNippou.SORT_KEY_TITLE:
                sql.addSql("   NTP.NIP_TITLE " + orderStr + ",");
                break;
            default:
                break;
            }

            String orderStr2 = "";
            // オーダー
            if (searchMdl.getNtpOrder2() == GSConstNippou.ORDER_KEY_ASC) {
                orderStr2 = "  asc";
            } else if (searchMdl.getNtpOrder2() == GSConstNippou.ORDER_KEY_DESC) {
                orderStr2 = "  desc";
            }

            switch (searchMdl.getNtpSortKey2()) {
            case GSConstNippou.SORT_KEY_NAME:
                sql.addSql("   USR_NAME " + orderStr2 + ",");
                sql.addSql("   NTP.NIP_SID");
                break;
            case GSConstNippou.SORT_KEY_FRDATE:
                sql.addSql("   NTP.NIP_DATE " + orderStr2 + ",");
                sql.addSql("   NTP.NIP_SID");
                break;
            case GSConstNippou.SORT_KEY_TITLE:
                sql.addSql("   NTP.NIP_TITLE " + orderStr2 + ",");
                sql.addSql("   NTP.NIP_SID");
                break;
            default:
                break;
            }

            if (offset) {
                sql.setPagingValue(searchMdl.getNtpOffset() - 1, searchMdl.getNtpLimit());

                pstmt = con.prepareStatement(sql.toSqlString());
                sql.setParameter(pstmt);
                log__.info(sql.toLogString());
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    ret.add(__getNipUsrDataFromRs(rs));
                }

            } else {

                pstmt = con.prepareStatement(sql.toSqlString());
                sql.setParameter(pstmt);
                log__.info(sql.toLogString());
                rs = pstmt.executeQuery();

                for (int i = 0; rs.next() && i < searchMdl.getNtpLimit(); i++) {
                    ret.add(__getNipUsrDataFromRs(rs));
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
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     * @param searchMdl 検索用モデル
     * @param sessionUsrSid セッションユーザSID(実行者)
     * @param rl NtpCsvRecordListenerIppanImpl
     * @param req リクエスト
     * @return int 明細件数
     * @throws SQLException SQL実行例外
     * @throws CSVException CSV出力時例外
     */
    public int createNippouForCsv(NippouListSearchModel searchMdl,
                                                      int sessionUsrSid,
                                                      NtpCsvRecordListenerIppanImpl rl,
                                                      HttpServletRequest req)
                                                      throws SQLException, CSVException  {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   NTP.NIP_SID as NIP_SID,");
            sql.addSql("   CMN_USRM.USR_LGID as USR_LGID,");
            sql.addSql("   NTP.USR_SID as USR_SID,");
            sql.addSql("   NTP.NIP_DATE as NIP_DATE,");
            sql.addSql("   NTP.NIP_FR_TIME as NIP_FR_TIME,");
            sql.addSql("   NTP.NIP_TO_TIME as NIP_TO_TIME,");
            sql.addSql("   NTP.NAN_SID as NAN_SID,");
            sql.addSql("   NTP.ACO_SID as ACO_SID,");
            sql.addSql("   NTP.ABA_SID as ABA_SID,");
            sql.addSql("   NTP.NIP_TITLE as NIP_TITLE,");
            sql.addSql("   NTP.NIP_TITLE_CLO as NIP_TITLE_CLO,");
            sql.addSql("   NTP.MKB_SID as MKB_SID,");
            sql.addSql("   NTP.MKH_SID as MKH_SID,");
            sql.addSql("   NTP.NIP_DETAIL as NIP_DETAIL,");
            sql.addSql("   NTP.NIP_MIKOMI as NIP_MIKOMI,");
            sql.addSql("   NTP.NIP_SYOKAN as NIP_SYOKAN,");
            sql.addSql("   NTP.NIP_AUID as NIP_AUID,");
            sql.addSql("   NTP.NIP_ADATE as NIP_ADATE,");
            sql.addSql("   NTP.NIP_EUID as NIP_EUID,");
            sql.addSql("   NTP.NIP_EDATE as NIP_EDATE,");
            sql.addSql("   NTP_ANKEN.NAN_CODE as NAN_CODE,");
            sql.addSql("   ADR_COMPANY.ACO_CODE as ACO_CODE,");
            sql.addSql("   NTP_KTBUNRUI.NKB_CODE as NKB_CODE,");
            sql.addSql("   NTP_KTHOUHOU.NKH_CODE as NKH_CODE,");
            sql.addSql("   CMN_USRM_INF.USI_SEI || CMN_USRM_INF.USI_MEI USR_NAME,");
            sql.addSql("   CMN_USRM_INF.USI_SEI_KN || CMN_USRM_INF.USI_MEI_KN USR_NAME_KN");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA NTP ");
            sql.addSql("     left join CMN_USRM_INF"
                             + " on NTP.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql("     left join NTP_ANKEN"
                             + " on NTP_ANKEN.NAN_SID = NTP.NAN_SID");
            sql.addSql("     left join ADR_COMPANY"
                             + " on ADR_COMPANY.ACO_SID = NTP.ACO_SID");
            sql.addSql("     left join NTP_KTBUNRUI"
                             + " on NTP_KTBUNRUI.NKB_SID = NTP.MKB_SID");
            sql.addSql("     left join NTP_KTHOUHOU"
                             + " on NTP_KTHOUHOU.NKH_SID = NTP.MKH_SID");
            sql.addSql("     left join CMN_USRM on NTP.USR_SID = CMN_USRM.USR_SID");

            if (searchMdl.getUsrSid() != -1) {
                sql.addSql(" where ");
                sql.addSql("   NTP.USR_SID = ?");
                sql.addIntValue(searchMdl.getUsrSid());
                sql.addSql(" and");
            } else {
                sql.addSql(" where ");
            }

//          UDate dayUd = new UDate();
            /** ********** 時間設定無し (h2用) *********** */
            String startDateFrStr = searchMdl.getNtpDateFrom()
                    .getDateStringForSql();
            String startDateToStr = searchMdl.getNtpDateTo()
                    .getDateStringForSql();

            sql.addSql("   (");
            sql.addSql("     NIP_DATE");
            sql.addSql("     between cast('" + startDateFrStr + "' as DATE)");
            sql.addSql("     and cast('" + startDateToStr + "' as DATE)");
            sql.addSql("   )");

            if (searchMdl.getUsrSid() == -1
                    && NtpCommonBiz.getDspGroupSid(searchMdl.getNtpSltGroupSid()) != -1) {
                sql.addSql("   and");
                sql.addSql("   (");
                sql.addSql("   NTP.USR_SID in (");

//                if (SchCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
                if (searchMdl.isMyGrpFlg()) {
                    sql.addSql("   select");
                    sql.addSql("     CMN_MY_GROUP_MS.MGM_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_MY_GROUP_MS");
                    sql.addSql("   where");
                    sql.addSql("     CMN_MY_GROUP_MS.MGP_SID = ?");
                    sql.addSql("   and");
                    sql.addIntValue(NtpCommonBiz.getDspGroupSid(searchMdl
                            .getNtpSltGroupSid()));
                    sql.addSql("   (");
                    sql.addSql("   CMN_MY_GROUP_MS.USR_SID = ?");
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("   or");
                    sql.addSql("   CMN_MY_GROUP_MS.MGP_SID in (");
                    sql.addSql("     select MGP_SID");
                    sql.addSql("     from CMN_MY_GROUP_SHARE");
                    sql.addSql("     where ");
                    sql.addSql("       MGS_USR_SID = ?");
                    sql.addSql("       or (MGS_USR_SID = -1 and MGS_GRP_SID in (");
                    sql.addSql("         select GRP_SID from CMN_BELONGM");
                    sql.addSql("         where USR_SID=?");
                    sql.addSql("         )");
                    sql.addSql("       )");
                    sql.addSql("     )");
                    sql.addIntValue(sessionUsrSid);
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("   )");
                } else {
                    sql.addSql("   select");
                    sql.addSql("     CMN_BELONGM.USR_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_BELONGM");
                    sql.addSql("   where");
                    sql.addSql("     CMN_BELONGM.GRP_SID = ?");
                    sql.addIntValue(Integer.parseInt(searchMdl.getNtpSltGroupSid()));
                }
                sql.addSql("   )");
                sql.addSql("   )");
            }


            //案件
            if (searchMdl.getAnkenSid() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.NAN_SID = ?");
                sql.addIntValue(searchMdl.getAnkenSid());
            }

            //活動分類
            if (searchMdl.getKatudouBunrui() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.MKB_SID = ?");
                sql.addIntValue(searchMdl.getKatudouBunrui());
            }

            //活動方法
            if (searchMdl.getKatudouHouhou() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.MKH_SID = ?");
                sql.addIntValue(searchMdl.getKatudouHouhou());
            }

            //企業コード
            if (searchMdl.getCompanySid() > 0) {
                sql.addSql("   and");
                sql.addSql("   NTP.ACO_SID = ?");
                sql.addIntValue(searchMdl.getCompanySid());

                if (searchMdl.getCompanyBaseSid() > 0) {
                    sql.addSql("   and");
                    sql.addSql("   NTP.ABA_SID = ?");
                    sql.addIntValue(searchMdl.getCompanyBaseSid());
                }

            }

            // KEYワード
            __cleateKeyWord(searchMdl, sql);

            //タイトルカラーコメント
            if (searchMdl.getBgcolor() != null && searchMdl.getBgcolor().length > 0) {
                String[] bgColorList = searchMdl.getBgcolor();
                for (int i = 0; i < bgColorList.length; i++) {
                    if (i == 0) {
                        sql.addSql(" and ");
                        sql.addSql(" ( ");
                    } else {
                        sql.addSql(" or ");
                    }
                    sql.addSql(" NTP.NIP_TITLE_CLO = ?");
                    sql.addIntValue(Integer.parseInt(bgColorList[i]));
                }
                sql.addSql(" ) ");
            }

            //見込み度
            if (searchMdl.getMikomido() != null && searchMdl.getMikomido().length > 0) {
                String[] mikomidoList = searchMdl.getMikomido();
                for (int i = 0; i < mikomidoList.length; i++) {
                    if (i == 0) {
                        sql.addSql(" and ");
                        sql.addSql(" ( ");
                    } else {
                        sql.addSql(" or ");
                    }
                    sql.addSql(" NTP.NIP_MIKOMI = ?");
                    sql.addIntValue(Integer.parseInt(mikomidoList[i]));
                }
                sql.addSql(" ) ");
            }


            String orderStr = "";
            // オーダー
            if (searchMdl.getNtpOrder() == GSConstNippou.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (searchMdl.getNtpOrder() == GSConstNippou.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            sql.addSql(" order by ");
            switch (searchMdl.getNtpSortKey()) {
            case GSConstNippou.SORT_KEY_NAME:
                sql.addSql("   USR_NAME " + orderStr + ",");
                break;
            case GSConstNippou.SORT_KEY_FRDATE:
                sql.addSql("   NTP.NIP_DATE " + orderStr + ",");
                break;
            case GSConstNippou.SORT_KEY_TITLE:
                sql.addSql("   NTP.NIP_TITLE " + orderStr + ",");
                break;
            default:
                break;
            }

            String orderStr2 = "";
            // オーダー
            if (searchMdl.getNtpOrder2() == GSConstNippou.ORDER_KEY_ASC) {
                orderStr2 = "  asc";
            } else if (searchMdl.getNtpOrder2() == GSConstNippou.ORDER_KEY_DESC) {
                orderStr2 = "  desc";
            }

            switch (searchMdl.getNtpSortKey2()) {
            case GSConstNippou.SORT_KEY_NAME:
                sql.addSql("   USR_NAME " + orderStr2 + ",");
                sql.addSql("   NTP.NIP_SID");
                break;
            case GSConstNippou.SORT_KEY_FRDATE:
                sql.addSql("   NTP.NIP_DATE " + orderStr2 + ",");
                sql.addSql("   NTP.NIP_SID");
                break;
            case GSConstNippou.SORT_KEY_TITLE:
                sql.addSql("   NTP.NIP_TITLE " + orderStr2 + ",");
                sql.addSql("   NTP.NIP_SID");
                break;
            default:
                break;
            }

            pstmt = con.prepareStatement(sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret++;
                NippouCsvModel model = __getNippouCsvFromRs(rs);
                setNtpCsvRecordFromSchDataModel(model, sessionUsrSid, rl, req);
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
     * ユーザSID、ユーザ区分、公開区分、日付を指定し時間指定無しスケジュール情報を取得する
     * @param usrSid
     *            ユーザSID
     * @param usrKbn
     *            ユーザ区分
     * @param pub
     *            公開区分 ※-1を指定すると条件から除外されます
     * @param date
     *            日付
     * @return ArrayList in SchDataModel
     * @throws SQLException
     *             SQL実行例外
     */
//    public ArrayList<NtpDataModel> selectNoTime(int usrSid, int usrKbn,
//            int pub, UDate date) throws SQLException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        ArrayList<NtpDataModel> ret = new ArrayList<NtpDataModel>();
//        con = getCon();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//            sql.addSql(" select ");
//            sql.addSql("   SCD_SID,");
//            sql.addSql("   SCD_USR_SID,");
//            sql.addSql("   SCD_GRP_SID,");
//            sql.addSql("   SCD_USR_KBN,");
//            sql.addSql("   SCD_FR_DATE,");
//            sql.addSql("   SCD_TO_DATE,");
//            sql.addSql("   SCD_DAILY,");
//            sql.addSql("   SCD_BGCOLOR,");
//            sql.addSql("   SCD_TITLE,");
//            sql.addSql("   SCD_VALUE,");
//            sql.addSql("   SCD_BIKO,");
//            sql.addSql("   SCD_PUBLIC,");
//            sql.addSql("   SCD_AUID,");
//            sql.addSql("   SCD_ADATE,");
//            sql.addSql("   SCD_EUID,");
//            sql.addSql("   SCD_EDATE,");
//            sql.addSql("   SCH.SCE_SID,");
//            sql.addSql("   SCD_RSSID,");
//            sql.addSql("   SCH.SCD_EDIT");
//            sql.addSql(" from ");
//            sql.addSql("   SCH_DATA");
//            sql.addSql(" where ");
//            sql.addSql("   SCD_USR_SID = ?");
//            sql.addValue(usrSid);
//            sql.addSql(" and ");
//            sql.addSql("   SCD_USR_KBN = ?");
//            sql.addValue(usrKbn);
//            if (pub != -1) {
//                sql.addSql(" and ");
//                sql.addSql("   SCD_PUBLIC = ?");
//                sql.addValue(pub);
//            }
//            sql.addSql(" and");
//            sql.addSql("  (");

            // /************ 時間設定無し (postgres用) ************/
            // sql.addSql(" (");
            // sql.addSql(" date_trunc('day', SCD_FR_DATE) between ?");
            // sql.addSql(" and ?");
            // sql.addSql(" and SCD_DAILY = 1");
            // sql.addSql(" )");
            // sql.addSql(" or");
            // sql.addSql(" (");
            // sql.addSql(" date_trunc('day', SCD_TO_DATE) between ?");
            // sql.addSql(" and ?");
            // sql.addSql(" and SCD_DAILY = 1");
            // sql.addSql(" )");
            // sql.addSql(" or");
            // sql.addSql(" (");
            // sql.addSql(" ? between date_trunc('day', SCD_FR_DATE)");
            // sql.addSql(" and date_trunc('day', SCD_TO_DATE)");
            // sql.addSql(" and SCD_DAILY = 1");
            // sql.addSql(" )");
            // sql.addSql(" or");
            // sql.addSql(" (");
            // sql.addSql(" ? between date_trunc('day', SCD_FR_DATE)");
            // sql.addSql(" and date_trunc('day', SCD_TO_DATE)");
            // sql.addSql(" and SCD_DAILY = 1");
            // sql.addSql(" )");
            // sql.addSql(" )");
            //
            // UDate frDate = new UDate();
            // UDate toDate = new UDate();
            //
            // frDate.setDate(date.getDateString());
            // frDate.setHour(GSConstNippou.DAY_START_HOUR);
            // frDate.setMinute(GSConstNippou.DAY_START_MINUTES);
            // frDate.setSecond(GSConstNippou.DAY_START_SECOND);
            // frDate.setMilliSecond(GSConstNippou.DAY_START_MILLISECOND);
            //
            // toDate.setDate(date.getDateString());
            // toDate.setHour(GSConstNippou.DAY_END_HOUR);
            // toDate.setMinute(GSConstNippou.DAY_END_MINUTES);
            // toDate.setSecond(GSConstNippou.DAY_END_SECOND);
            // toDate.setMilliSecond(GSConstNippou.DAY_END_MILLISECOND);
            //
            // sql.addValue(frDate);
            // sql.addValue(toDate);
            // sql.addValue(frDate);
            // sql.addValue(toDate);
            // sql.addValue(frDate);
            // sql.addValue(toDate);
            // sql.addSql(" order by ");
            // sql.addSql(" SCD_USR_SID,");
            // sql.addSql(" SCD_DAILY DESC,");
            // sql.addSql(" date_trunc('minute',SCD_FR_DATE) ,");
            // sql.addSql(" SCD_SID");
            // /**********************************************/

            /** ********** 時間設定無し (derby用) *********** */
//            sql.addSql("   (");
//            sql.addSql("     bigint(substr(varchar(SCD_FR_DATE), 1, 4)");
//            sql.addSql("     || substr(varchar(SCD_FR_DATE), 6, 2)");
//            sql.addSql("     || substr(varchar(SCD_FR_DATE), 9 ,2))");
//            sql.addSql("     between ?");
//            sql.addSql("     and ?");
//            sql.addSql("     and SCD_DAILY = 1");
//            sql.addSql("   )");
//            sql.addSql("   or");
//            sql.addSql("   (");
//            sql.addSql("     bigint(substr(varchar(SCD_TO_DATE), 1, 4)");
//            sql.addSql("     || substr(varchar(SCD_TO_DATE), 6, 2)");
//            sql.addSql("     || substr(varchar(SCD_TO_DATE), 9 ,2))");
//            sql.addSql("     between ?");
//            sql.addSql("     and ?");
//            sql.addSql("     and SCD_DAILY = 1");
//            sql.addSql("   )");
//            sql.addSql("   or");
//            sql.addSql("   (");
//            sql.addSql("    ? between");
//            sql.addSql("    bigint(substr(varchar(SCD_FR_DATE), 1, 4)");
//            sql.addSql("     || substr(varchar(SCD_FR_DATE), 6, 2)");
//            sql.addSql("     || substr(varchar(SCD_FR_DATE), 9 ,2))");
//            sql.addSql("    and");
//            sql.addSql("     bigint(substr(varchar(SCD_TO_DATE), 1, 4)");
//            sql.addSql("     || substr(varchar(SCD_TO_DATE), 6, 2)");
//            sql.addSql("     || substr(varchar(SCD_TO_DATE), 9 ,2))");
//            sql.addSql("     and SCD_DAILY = 1");
//            sql.addSql("   )");

            // sql.addSql(" or");
            // sql.addSql(" (");
            // sql.addSql(" ? between date_trunc('day', SCD_FR_DATE)");
            // sql.addSql(" and date_trunc('day', SCD_TO_DATE)");
            // sql.addSql(" and SCD_DAILY = 1");
            // sql.addSql(" )");

//            sql.addSql("  )");
//
//            String dateStr = date.getDateString();
//            long dateLong = new Long(dateStr);
//
//            sql.addValue(dateLong);
//            sql.addValue(dateLong);
//            sql.addValue(dateLong);
//            sql.addValue(dateLong);
//            sql.addValue(dateLong);
//
//            sql.addSql(" order by ");
//            sql.addSql("   SCD_USR_SID,");
//            sql.addSql("   SCD_DAILY DESC,");
//            sql.addSql("   bigint(substr(varchar(SCD_FR_DATE), 1, 4)");
//            sql.addSql("   || substr(varchar(SCD_FR_DATE), 6, 2)");
//            sql.addSql("   || substr(varchar(SCD_FR_DATE), 9 ,2)),");
//            sql.addSql("   SCD_SID");

            /** ******************************************* */

//            pstmt = con.prepareStatement(sql.toSqlString());
//
//            sql.setParameter(pstmt);
//            log__.info(sql.toLogString());
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                ret.add(__getNtpDataFromRs(rs));
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return ret;
//    }


    /**
     * <p>
     * 日報SIDから日報情報＋ユーザ情報を取得する
     * @param nipSid
     *            日報SID
     * @param crange
     *            共有範囲設定 0=全て、1=所属グループ内のみ
     * @return NippouSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public NippouSearchModel getNippouData(int nipSid, int crange)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        NippouSearchModel ret = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   NTP_DATA.NIP_SID,");
            sql.addSql("   NTP_DATA.USR_SID,");
            sql.addSql("   NTP_DATA.NIP_DATE,");
            sql.addSql("   NTP_DATA.NIP_FR_TIME,");
            sql.addSql("   NTP_DATA.NIP_TO_TIME,");
            sql.addSql("   NTP_DATA.NIP_KADO_HH,");
            sql.addSql("   NTP_DATA.NIP_KADO_MM,");
            sql.addSql("   NTP_DATA.NIP_MGY_SID,");
            sql.addSql("   NTP_DATA.NAN_SID,");
            sql.addSql("   NTP_DATA.ACO_SID,");
            sql.addSql("   NTP_DATA.ABA_SID,");
            sql.addSql("   NTP_DATA.NIP_TITLE,");
            sql.addSql("   NTP_DATA.NIP_TITLE_CLO,");
            sql.addSql("   NTP_DATA.MPR_SID,");
            sql.addSql("   NTP_DATA.MKB_SID,");
            sql.addSql("   NTP_DATA.MKH_SID,");
            sql.addSql("   NTP_DATA.NIP_TIEUP_SID,");
            sql.addSql("   NTP_DATA.NIP_KEIZOKU,");
            sql.addSql("   NTP_DATA.NIP_ACTEND,");
            sql.addSql("   NTP_DATA.NIP_DETAIL,");
            sql.addSql("   NTP_DATA.NIP_ACTION_DATE,");
            sql.addSql("   NTP_DATA.NIP_ACTION,");
            sql.addSql("   NTP_DATA.NIP_ACTDATE_KBN,");
            sql.addSql("   NTP_DATA.NIP_ASSIGN,");
            sql.addSql("   NTP_DATA.NIP_KINGAKU,");
            sql.addSql("   NTP_DATA.NIP_MIKOMI,");
            sql.addSql("   NTP_DATA.NIP_SYOKAN,");
            sql.addSql("   NTP_DATA.NIP_PUBLIC,");
            sql.addSql("   NTP_DATA.NIP_EDIT,");
            sql.addSql("   NTP_DATA.NEX_SID,");
            sql.addSql("   NTP_DATA.NIP_AUID,");
            sql.addSql("   NTP_DATA.NIP_ADATE,");
            sql.addSql("   NTP_DATA.NIP_EUID,");
            sql.addSql("   NTP_DATA.NIP_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA");
            sql.addSql(" where ");
            sql.addSql("   NTP_DATA.NIP_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(nipSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getNipUsrDataFromRs(rs);
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
     * 日報SIDから同日に登録された日報情報の件数を取得する
     * @param nipSid 日報SID
     * @param usrSid ユーザSID
     * @return NippouSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public int getNippouDataCount(int nipSid, int usrSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   count(NTP_DATA_VAL.NIP_SID)       as CNT");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA as NTP_DATA_VAL,");
            sql.addSql("   (select");
            sql.addSql("      NTP_DATA_IN.NIP_DATE");
            sql.addSql("    from");
            sql.addSql("      NTP_DATA as NTP_DATA_IN");
            sql.addSql("    where");
            sql.addSql("      NTP_DATA_IN.NIP_SID = ?");
            sql.addSql("     )  as NTP_DATA_BASE");
            sql.addSql(" where");
            sql.addSql("   NTP_DATA_VAL.NIP_DATE = NTP_DATA_BASE.NIP_DATE");
            sql.addSql(" and");
            sql.addSql("   NTP_DATA_VAL.USR_SID = ?;");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(nipSid);
            sql.addIntValue(usrSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
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
     * <p>
     * 日報SIDから同日に登録された日報情報＋ユーザ情報を取得する
     * @param nipSid 日報SID
     * @param usrSid ユーザSID
     * @return NippouSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<NippouSearchModel> getNippouDataAll(int nipSid, int usrSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<NippouSearchModel> mdlList = new ArrayList<NippouSearchModel>();
        NippouSearchModel ret = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   NTP_DATA_VAL.NIP_SID       as NIP_SID,");
            sql.addSql("   NTP_DATA_VAL.USR_SID       as USR_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_DATE      as NIP_DATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_FR_TIME   as NIP_FR_TIME,");
            sql.addSql("   NTP_DATA_VAL.NIP_TO_TIME   as NIP_TO_TIME,");
            sql.addSql("   NTP_DATA_VAL.NIP_KADO_HH   as NIP_KADO_HH,");
            sql.addSql("   NTP_DATA_VAL.NIP_KADO_MM   as NIP_KADO_MM,");
            sql.addSql("   NTP_DATA_VAL.NIP_MGY_SID   as NIP_MGY_SID,");
            sql.addSql("   NTP_DATA_VAL.NAN_SID       as NAN_SID,");
            sql.addSql("   NTP_DATA_VAL.ACO_SID       as ACO_SID,");
            sql.addSql("   NTP_DATA_VAL.ABA_SID       as ABA_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_TITLE     as NIP_TITLE,");
            sql.addSql("   NTP_DATA_VAL.NIP_TITLE_CLO as NIP_TITLE_CLO,");
            sql.addSql("   NTP_DATA_VAL.MPR_SID       as MPR_SID,");
            sql.addSql("   NTP_DATA_VAL.MKB_SID       as MKB_SID,");
            sql.addSql("   NTP_DATA_VAL.MKH_SID       as MKH_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_TIEUP_SID as NIP_TIEUP_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_KEIZOKU   as NIP_KEIZOKU,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTEND    as NIP_ACTEND,");
            sql.addSql("   NTP_DATA_VAL.NIP_DETAIL    as NIP_DETAIL,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTION_DATE  as NIP_ACTION_DATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTION    as NIP_ACTION,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTDATE_KBN    as NIP_ACTDATE_KBN,");
            sql.addSql("   NTP_DATA_VAL.NIP_ASSIGN    as NIP_ASSIGN,");
            sql.addSql("   NTP_DATA_VAL.NIP_KINGAKU   as NIP_KINGAKU,");
            sql.addSql("   NTP_DATA_VAL.NIP_MIKOMI    as NIP_MIKOMI,");
            sql.addSql("   NTP_DATA_VAL.NIP_SYOKAN    as NIP_SYOKAN,");
            sql.addSql("   NTP_DATA_VAL.NIP_PUBLIC    as NIP_PUBLIC,");
            sql.addSql("   NTP_DATA_VAL.NIP_EDIT      as NIP_EDIT,");
            sql.addSql("   NTP_DATA_VAL.NEX_SID       as NEX_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_AUID      as NIP_AUID,");
            sql.addSql("   NTP_DATA_VAL.NIP_ADATE     as NIP_ADATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_EUID      as NIP_EUID,");
            sql.addSql("   NTP_DATA_VAL.NIP_EDATE     as NIP_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA as NTP_DATA_VAL,");
            sql.addSql("   (select");
            sql.addSql("      NTP_DATA_IN.NIP_DATE");
            sql.addSql("    from");
            sql.addSql("      NTP_DATA as NTP_DATA_IN");
            sql.addSql("    where");
            sql.addSql("      NTP_DATA_IN.NIP_SID = ?");
            sql.addSql("     )  as NTP_DATA_BASE");
            sql.addSql(" where");
            sql.addSql("   NTP_DATA_VAL.NIP_DATE = NTP_DATA_BASE.NIP_DATE");
            sql.addSql(" and");
            sql.addSql("   NTP_DATA_VAL.USR_SID = ?");
            sql.addSql(" order by ");
            sql.addSql("   NIP_FR_TIME");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(nipSid);
            sql.addIntValue(usrSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret = __getNipUsrDataFromRs(rs);
                mdlList.add(ret);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return mdlList;
    }


    /**
     * <p>
     * 日報SIDから同日に登録された日報情報＋ユーザ情報を取得する
     * @param nipSid 日報SID
     * @param usrSid ユーザSID
     * @return NippouSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public NippouSearchModel getNippouDataSingle(int nipSid, int usrSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        NippouSearchModel ret = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   NTP_DATA_VAL.NIP_SID       as NIP_SID,");
            sql.addSql("   NTP_DATA_VAL.USR_SID       as USR_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_DATE      as NIP_DATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_FR_TIME   as NIP_FR_TIME,");
            sql.addSql("   NTP_DATA_VAL.NIP_TO_TIME   as NIP_TO_TIME,");
            sql.addSql("   NTP_DATA_VAL.NIP_KADO_HH   as NIP_KADO_HH,");
            sql.addSql("   NTP_DATA_VAL.NIP_KADO_MM   as NIP_KADO_MM,");
            sql.addSql("   NTP_DATA_VAL.NIP_MGY_SID   as NIP_MGY_SID,");
            sql.addSql("   NTP_DATA_VAL.NAN_SID       as NAN_SID,");
            sql.addSql("   NTP_DATA_VAL.ACO_SID       as ACO_SID,");
            sql.addSql("   NTP_DATA_VAL.ABA_SID       as ABA_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_TITLE     as NIP_TITLE,");
            sql.addSql("   NTP_DATA_VAL.NIP_TITLE_CLO as NIP_TITLE_CLO,");
            sql.addSql("   NTP_DATA_VAL.MPR_SID       as MPR_SID,");
            sql.addSql("   NTP_DATA_VAL.MKB_SID       as MKB_SID,");
            sql.addSql("   NTP_DATA_VAL.MKH_SID       as MKH_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_TIEUP_SID as NIP_TIEUP_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_KEIZOKU   as NIP_KEIZOKU,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTEND    as NIP_ACTEND,");
            sql.addSql("   NTP_DATA_VAL.NIP_DETAIL    as NIP_DETAIL,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTION_DATE    as NIP_ACTION_DATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTION    as NIP_ACTION,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTDATE_KBN    as NIP_ACTDATE_KBN,");
            sql.addSql("   NTP_DATA_VAL.NIP_ASSIGN    as NIP_ASSIGN,");
            sql.addSql("   NTP_DATA_VAL.NIP_KINGAKU   as NIP_KINGAKU,");
            sql.addSql("   NTP_DATA_VAL.NIP_MIKOMI    as NIP_MIKOMI,");
            sql.addSql("   NTP_DATA_VAL.NIP_SYOKAN    as NIP_SYOKAN,");
            sql.addSql("   NTP_DATA_VAL.NIP_PUBLIC    as NIP_PUBLIC,");
            sql.addSql("   NTP_DATA_VAL.NIP_EDIT      as NIP_EDIT,");
            sql.addSql("   NTP_DATA_VAL.NEX_SID       as NEX_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_AUID      as NIP_AUID,");
            sql.addSql("   NTP_DATA_VAL.NIP_ADATE     as NIP_ADATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_EUID      as NIP_EUID,");
            sql.addSql("   NTP_DATA_VAL.NIP_EDATE     as NIP_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA as NTP_DATA_VAL");
            sql.addSql(" where");
            sql.addSql("   NTP_DATA_VAL.NIP_SID = ?");
            sql.addSql(" and");
            sql.addSql("   NTP_DATA_VAL.USR_SID = ?");
            sql.addSql(" order by ");
            sql.addSql("   NIP_FR_TIME");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(nipSid);
            sql.addIntValue(usrSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = __getNipUsrDataFromRs(rs);
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
     * 現在閲覧している日報より前 or 後の日付の日報SIDを取得する
     * @param nipDate 現在の日報の日付
     * @param usrSid ユーザSID
     * @return NippouSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public NippouSearchModel getPrevNextNippouSid(UDate nipDate, int usrSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        NippouSearchModel ret = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();


            sql.addSql(" select");
            sql.addSql("   NTP_DATA_VAL.NIP_SID       as NIP_SID,");
            sql.addSql("   NTP_DATA_VAL.USR_SID       as USR_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_DATE      as NIP_DATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_FR_TIME   as NIP_FR_TIME,");
            sql.addSql("   NTP_DATA_VAL.NIP_TO_TIME   as NIP_TO_TIME,");
            sql.addSql("   NTP_DATA_VAL.NIP_KADO_HH   as NIP_KADO_HH,");
            sql.addSql("   NTP_DATA_VAL.NIP_KADO_MM   as NIP_KADO_MM,");
            sql.addSql("   NTP_DATA_VAL.NIP_MGY_SID   as NIP_MGY_SID,");
            sql.addSql("   NTP_DATA_VAL.NAN_SID       as NAN_SID,");
            sql.addSql("   NTP_DATA_VAL.ACO_SID       as ACO_SID,");
            sql.addSql("   NTP_DATA_VAL.ABA_SID       as ABA_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_TITLE     as NIP_TITLE,");
            sql.addSql("   NTP_DATA_VAL.NIP_TITLE_CLO as NIP_TITLE_CLO,");
            sql.addSql("   NTP_DATA_VAL.MPR_SID       as MPR_SID,");
            sql.addSql("   NTP_DATA_VAL.MKB_SID       as MKB_SID,");
            sql.addSql("   NTP_DATA_VAL.MKH_SID       as MKH_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_TIEUP_SID as NIP_TIEUP_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_KEIZOKU   as NIP_KEIZOKU,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTEND    as NIP_ACTEND,");
            sql.addSql("   NTP_DATA_VAL.NIP_DETAIL    as NIP_DETAIL,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTION_DATE    as NIP_ACTION_DATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTION    as NIP_ACTION,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTDATE_KBN    as NIP_ACTDATE_KBN,");
            sql.addSql("   NTP_DATA_VAL.NIP_ASSIGN    as NIP_ASSIGN,");
            sql.addSql("   NTP_DATA_VAL.NIP_KINGAKU   as NIP_KINGAKU,");
            sql.addSql("   NTP_DATA_VAL.NIP_MIKOMI    as NIP_MIKOMI,");
            sql.addSql("   NTP_DATA_VAL.NIP_SYOKAN    as NIP_SYOKAN,");
            sql.addSql("   NTP_DATA_VAL.NIP_PUBLIC    as NIP_PUBLIC,");
            sql.addSql("   NTP_DATA_VAL.NIP_EDIT      as NIP_EDIT,");
            sql.addSql("   NTP_DATA_VAL.NEX_SID       as NEX_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_AUID      as NIP_AUID,");
            sql.addSql("   NTP_DATA_VAL.NIP_ADATE     as NIP_ADATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_EUID      as NIP_EUID,");
            sql.addSql("   NTP_DATA_VAL.NIP_EDATE     as NIP_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA as NTP_DATA_VAL");
            sql.addSql(" where");
            sql.addSql("   NTP_DATA_VAL.USR_SID = ?");
            sql.addSql(" and");
            sql.addSql("   NTP_DATA_VAL.NIP_DATE = cast(? as DATE)");
            sql.addSql(" order by ");
            sql.addSql("   NTP_DATA_VAL.NIP_FR_TIME");
            sql.addSql(" limit 1 offset 0");


            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(usrSid);
            String date = nipDate.getDateStringForSql();
            sql.addStrValue(date);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = __getNipUsrDataFromRs(rs);
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
     * ユーザSIDと登録日付から日報情報＋ユーザ情報を取得する
     * @param searchMdl searchモデル
     * @param sessionUsrSid セッションユーザSID
     * @param sort ソート
     * @return NippouSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<NippouSearchModel> getNippouDataAll(
            NippouListSearchModel searchMdl, int sessionUsrSid, int sort)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<NippouSearchModel> mdlList = new ArrayList<NippouSearchModel>();
        NippouSearchModel ret = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   NIP_SID       as NIP_SID,");
            sql.addSql("   USR_SID       as USR_SID,");
            sql.addSql("   NIP_DATE      as NIP_DATE,");
            sql.addSql("   NIP_FR_TIME   as NIP_FR_TIME,");
            sql.addSql("   NIP_TO_TIME   as NIP_TO_TIME,");
            sql.addSql("   NIP_KADO_HH   as NIP_KADO_HH,");
            sql.addSql("   NIP_KADO_MM   as NIP_KADO_MM,");
            sql.addSql("   NIP_MGY_SID   as NIP_MGY_SID,");
            sql.addSql("   NAN_SID       as NAN_SID,");
            sql.addSql("   ACO_SID       as ACO_SID,");
            sql.addSql("   ABA_SID       as ABA_SID,");
            sql.addSql("   NIP_TITLE     as NIP_TITLE,");
            sql.addSql("   NIP_TITLE_CLO as NIP_TITLE_CLO,");
            sql.addSql("   MPR_SID       as MPR_SID,");
            sql.addSql("   MKB_SID       as MKB_SID,");
            sql.addSql("   MKH_SID       as MKH_SID,");
            sql.addSql("   NIP_TIEUP_SID as NIP_TIEUP_SID,");
            sql.addSql("   NIP_KEIZOKU   as NIP_KEIZOKU,");
            sql.addSql("   NIP_ACTEND    as NIP_ACTEND,");
            sql.addSql("   NIP_DETAIL    as NIP_DETAIL,");
            sql.addSql("   NIP_ACTION_DATE    as NIP_ACTION_DATE,");
            sql.addSql("   NIP_ACTION    as NIP_ACTION,");
            sql.addSql("   NIP_ACTDATE_KBN    as NIP_ACTDATE_KBN,");
            sql.addSql("   NIP_ASSIGN    as NIP_ASSIGN,");
            sql.addSql("   NIP_KINGAKU   as NIP_KINGAKU,");
            sql.addSql("   NIP_MIKOMI    as NIP_MIKOMI,");
            sql.addSql("   NIP_SYOKAN    as NIP_SYOKAN,");
            sql.addSql("   NIP_PUBLIC    as NIP_PUBLIC,");
            sql.addSql("   NIP_EDIT      as NIP_EDIT,");
            sql.addSql("   NEX_SID       as NEX_SID,");
            sql.addSql("   NIP_AUID      as NIP_AUID,");
            sql.addSql("   NIP_ADATE     as NIP_ADATE,");
            sql.addSql("   NIP_EUID      as NIP_EUID,");
            sql.addSql("   NIP_EDATE     as NIP_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA");
            sql.addSql(" where ");

            if (searchMdl.getUsrSid() != -1) {

                sql.addSql("   USR_SID = ?");
                sql.addIntValue(searchMdl.getUsrSid());
            } else {
                sql.addSql("   (");
                sql.addSql("   USR_SID in (");

//                if (SchCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
                if (searchMdl.isMyGrpFlg()) {
                    sql.addSql("   select");
                    sql.addSql("     CMN_MY_GROUP_MS.MGM_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_MY_GROUP_MS");
                    sql.addSql("   where");
                    sql.addSql("     CMN_MY_GROUP_MS.MGP_SID = ?");
                    sql.addSql("   and");
                    sql.addIntValue(Integer.valueOf(searchMdl.getNtpSltGroupSid()));
                    sql.addSql("   (");
                    sql.addSql("   CMN_MY_GROUP_MS.USR_SID = ?");
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("   or");
                    sql.addSql("   CMN_MY_GROUP_MS.MGP_SID in (");
                    sql.addSql("     select MGP_SID");
                    sql.addSql("     from CMN_MY_GROUP_SHARE");
                    sql.addSql("     where ");
                    sql.addSql("       MGS_USR_SID = ?");
                    sql.addSql("       or (MGS_USR_SID = -1 and MGS_GRP_SID in (");
                    sql.addSql("         select GRP_SID from CMN_BELONGM");
                    sql.addSql("         where USR_SID=?");
                    sql.addSql("         )");
                    sql.addSql("       )");
                    sql.addSql("     )");
                    sql.addIntValue(sessionUsrSid);
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("   )");
                } else {
                    sql.addSql("   select");
                    sql.addSql("     CMN_BELONGM.USR_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_BELONGM");
                    sql.addSql("   where");
                    sql.addSql("     CMN_BELONGM.GRP_SID = ?");
                    sql.addIntValue(Integer.valueOf(searchMdl.getNtpSltGroupSid()));
                }
                sql.addSql("   )");
                sql.addSql("   )");
            }

            if (sort == GSConstNippou.DATE_DESC_EDATE_ASC) {
                //登録日時▲更新日時▼
                sql.addSql(" order by NIP_DATE DESC, NIP_EDATE ASC");
            } else if (sort == GSConstNippou.DATE_ASC_EDATE_DESC) {
                //登録日時▼更新日時▲
                sql.addSql(" order by NIP_DATE ASC, NIP_EDATE DESC");
            } else if (sort == GSConstNippou.DATE_ASC_EDATE_ASC) {
                //登録日時▼更新日時▼
                sql.addSql(" order by NIP_DATE ASC, NIP_EDATE ASC");
            } else {
                //登録日時▲更新日時▲
                sql.addSql(" order by NIP_DATE DESC, NIP_EDATE DESC");
            }

            pstmt = con.prepareStatement(sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            if (searchMdl.getNtpOffset() > 1) {
                rs.absolute((searchMdl.getNtpOffset() - 1) * searchMdl.getNtpLimit());
            }

            for (int i = 0; rs.next() && i < searchMdl.getNtpLimit(); i++) {
                ret = __getNipUsrDataFromRs(rs);
                mdlList.add(ret);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return mdlList;
    }

    /**
     * <p>
     * ユーザSIDと日付から日報情報を取得する
     * @param searchMdl searchモデル
     * @param usrSid ユーザSID
     * @param sort ソート
     * @param dspDate 画面日付
     * @return NippouSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<NippouSearchModel> getNippouDateData(
            NippouListSearchModel searchMdl, int usrSid, int sort, UDate dspDate)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<NippouSearchModel> mdlList = new ArrayList<NippouSearchModel>();
        NippouSearchModel ret = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   NIP_SID       as NIP_SID,");
            sql.addSql("   USR_SID       as USR_SID,");
            sql.addSql("   NIP_DATE      as NIP_DATE,");
            sql.addSql("   NIP_FR_TIME   as NIP_FR_TIME,");
            sql.addSql("   NIP_TO_TIME   as NIP_TO_TIME,");
            sql.addSql("   NIP_KADO_HH   as NIP_KADO_HH,");
            sql.addSql("   NIP_KADO_MM   as NIP_KADO_MM,");
            sql.addSql("   NIP_MGY_SID   as NIP_MGY_SID,");
            sql.addSql("   NAN_SID       as NAN_SID,");
            sql.addSql("   ACO_SID       as ACO_SID,");
            sql.addSql("   ABA_SID       as ABA_SID,");
            sql.addSql("   NIP_TITLE     as NIP_TITLE,");
            sql.addSql("   NIP_TITLE_CLO as NIP_TITLE_CLO,");
            sql.addSql("   MPR_SID       as MPR_SID,");
            sql.addSql("   MKB_SID       as MKB_SID,");
            sql.addSql("   MKH_SID       as MKH_SID,");
            sql.addSql("   NIP_TIEUP_SID as NIP_TIEUP_SID,");
            sql.addSql("   NIP_KEIZOKU   as NIP_KEIZOKU,");
            sql.addSql("   NIP_ACTEND    as NIP_ACTEND,");
            sql.addSql("   NIP_DETAIL    as NIP_DETAIL,");
            sql.addSql("   NIP_ACTION_DATE    as NIP_ACTION_DATE,");
            sql.addSql("   NIP_ACTION    as NIP_ACTION,");
            sql.addSql("   NIP_ACTDATE_KBN    as NIP_ACTDATE_KBN,");
            sql.addSql("   NIP_ASSIGN    as NIP_ASSIGN,");
            sql.addSql("   NIP_KINGAKU   as NIP_KINGAKU,");
            sql.addSql("   NIP_MIKOMI    as NIP_MIKOMI,");
            sql.addSql("   NIP_SYOKAN    as NIP_SYOKAN,");
            sql.addSql("   NIP_PUBLIC    as NIP_PUBLIC,");
            sql.addSql("   NIP_EDIT      as NIP_EDIT,");
            sql.addSql("   NEX_SID       as NEX_SID,");
            sql.addSql("   NIP_AUID      as NIP_AUID,");
            sql.addSql("   NIP_ADATE     as NIP_ADATE,");
            sql.addSql("   NIP_EUID      as NIP_EUID,");
            sql.addSql("   NIP_EDATE     as NIP_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA");
            sql.addSql(" where ");

            if (searchMdl.getUsrSid() != -1) {

                sql.addSql("   USR_SID = ?");
                sql.addIntValue(searchMdl.getUsrSid());
            } else {
                sql.addSql("   (");
                sql.addSql("   USR_SID in (");

//                if (SchCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
                if (searchMdl.isMyGrpFlg()) {
                    sql.addSql("   select");
                    sql.addSql("     CMN_MY_GROUP_MS.MGM_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_MY_GROUP_MS");
                    sql.addSql("   where");
                    sql.addSql("     CMN_MY_GROUP_MS.MGP_SID = ?");
                    sql.addSql("   and");
                    sql.addIntValue(Integer.valueOf(searchMdl.getNtpSltGroupSid()));
                    sql.addSql("   (");
                    sql.addSql("   CMN_MY_GROUP_MS.USR_SID = ?");
                    sql.addIntValue(usrSid);
                    sql.addSql("   or");
                    sql.addSql("   CMN_MY_GROUP_MS.MGP_SID in (");
                    sql.addSql("     select MGP_SID");
                    sql.addSql("     from CMN_MY_GROUP_SHARE");
                    sql.addSql("     where ");
                    sql.addSql("       MGS_USR_SID = ?");
                    sql.addSql("       or (MGS_USR_SID = -1 and MGS_GRP_SID in (");
                    sql.addSql("         select GRP_SID from CMN_BELONGM");
                    sql.addSql("         where USR_SID=?");
                    sql.addSql("         )");
                    sql.addSql("       )");
                    sql.addSql("     )");
                    sql.addIntValue(usrSid);
                    sql.addIntValue(usrSid);
                    sql.addSql("   )");
                } else {
                    sql.addSql("   select");
                    sql.addSql("     CMN_BELONGM.USR_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_BELONGM");
                    sql.addSql("   where");
                    sql.addSql("     CMN_BELONGM.GRP_SID = ?");
                    sql.addIntValue(Integer.valueOf(searchMdl.getNtpSltGroupSid()));
                }
                sql.addSql("   )");
                sql.addSql("   )");
            }

            String date = dspDate.getDateStringForSql();

            sql.addSql(" and");
            sql.addSql("  NIP_DATE = cast('" + date + "' as DATE)");


            if (sort == GSConstNippou.DATE_DESC_EDATE_ASC) {
                //登録日時▲更新日時▼
                sql.addSql(" order by NIP_DATE DESC, NIP_EDATE ASC");
            } else if (sort == GSConstNippou.DATE_ASC_EDATE_DESC) {
                //登録日時▼更新日時▲
                sql.addSql(" order by NIP_DATE ASC, NIP_EDATE DESC");
            } else if (sort == GSConstNippou.DATE_ASC_EDATE_ASC) {
                //登録日時▼更新日時▼
                sql.addSql(" order by NIP_DATE ASC, NIP_EDATE ASC");
            } else {
                //登録日時▲更新日時▲
                sql.addSql(" order by NIP_DATE DESC, NIP_EDATE DESC");
            }

            pstmt = con.prepareStatement(sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();



            while (rs.next()) {
                ret = __getNipUsrDataFromRs(rs);
                mdlList.add(ret);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return mdlList;
    }


    /**
     * <p>
     * 日報SIDから日報情報を取得する
     * @param nipSid 日報SID
     * @return NippouSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public NippouSearchModel getNippouData(int nipSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        NippouSearchModel ret = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   NTP_DATA_VAL.NIP_SID       as NIP_SID,");
            sql.addSql("   NTP_DATA_VAL.USR_SID       as USR_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_DATE      as NIP_DATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_FR_TIME   as NIP_FR_TIME,");
            sql.addSql("   NTP_DATA_VAL.NIP_TO_TIME   as NIP_TO_TIME,");
            sql.addSql("   NTP_DATA_VAL.NIP_KADO_HH   as NIP_KADO_HH,");
            sql.addSql("   NTP_DATA_VAL.NIP_KADO_MM   as NIP_KADO_MM,");
            sql.addSql("   NTP_DATA_VAL.NIP_MGY_SID   as NIP_MGY_SID,");
            sql.addSql("   NTP_DATA_VAL.NAN_SID       as NAN_SID,");
            sql.addSql("   NTP_DATA_VAL.ACO_SID       as ACO_SID,");
            sql.addSql("   NTP_DATA_VAL.ABA_SID       as ABA_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_TITLE     as NIP_TITLE,");
            sql.addSql("   NTP_DATA_VAL.NIP_TITLE_CLO as NIP_TITLE_CLO,");
            sql.addSql("   NTP_DATA_VAL.MPR_SID       as MPR_SID,");
            sql.addSql("   NTP_DATA_VAL.MKB_SID       as MKB_SID,");
            sql.addSql("   NTP_DATA_VAL.MKH_SID       as MKH_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_TIEUP_SID as NIP_TIEUP_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_KEIZOKU   as NIP_KEIZOKU,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTEND    as NIP_ACTEND,");
            sql.addSql("   NTP_DATA_VAL.NIP_DETAIL    as NIP_DETAIL,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTION_DATE    as NIP_ACTION_DATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTION    as NIP_ACTION,");
            sql.addSql("   NTP_DATA_VAL.NIP_ACTDATE_KBN    as NIP_ACTDATE_KBN,");
            sql.addSql("   NTP_DATA_VAL.NIP_ASSIGN    as NIP_ASSIGN,");
            sql.addSql("   NTP_DATA_VAL.NIP_KINGAKU   as NIP_KINGAKU,");
            sql.addSql("   NTP_DATA_VAL.NIP_MIKOMI    as NIP_MIKOMI,");
            sql.addSql("   NTP_DATA_VAL.NIP_SYOKAN    as NIP_SYOKAN,");
            sql.addSql("   NTP_DATA_VAL.NIP_PUBLIC    as NIP_PUBLIC,");
            sql.addSql("   NTP_DATA_VAL.NIP_EDIT      as NIP_EDIT,");
            sql.addSql("   NTP_DATA_VAL.NEX_SID       as NEX_SID,");
            sql.addSql("   NTP_DATA_VAL.NIP_AUID      as NIP_AUID,");
            sql.addSql("   NTP_DATA_VAL.NIP_ADATE     as NIP_ADATE,");
            sql.addSql("   NTP_DATA_VAL.NIP_EUID      as NIP_EUID,");
            sql.addSql("   NTP_DATA_VAL.NIP_EDATE     as NIP_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA as NTP_DATA_VAL");
            sql.addSql(" where");
            sql.addSql("   NTP_DATA_VAL.NIP_SID = ?");


            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(nipSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = __getNipUsrDataFromRs(rs);
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
     * スケジュール拡張SIDからスケジュール情報の件数を取得する
     * @param nexSid
     *            スケジュール拡張SID
     * @return NippouSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public int getExCount(int nexSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   count(*) as CNT");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA");
            sql.addSql(" where ");
            sql.addSql("   NTP_DATA.NEX_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(nexSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
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
     * <p>
     * スケジュールSIDから拡張登録されているスケジュール情報＋ユーザ情報を取得する
     * <p>
     * 拡張登録データが無い場合は空のArrayListを返します。
     * @param scdSid
     *            スケジュールSID
     * @return ArrayList in SchDataModel
     * @throws SQLException
     *             SQL実行例外
     */
//    public ArrayList<NtpDataModel> getScheduleDataFromSceSid(int scdSid)
//            throws SQLException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        con = getCon();
//        ArrayList<NtpDataModel> ret = new ArrayList<NtpDataModel>();
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//
//            sql.addSql(" select");
//            sql.addSql("   SCH_DATA.SCD_SID,");
//            sql.addSql("   SCH_DATA.SCD_USR_SID,");
//            sql.addSql("   SCH_DATA.SCD_GRP_SID,");
//            sql.addSql("   SCH_DATA.SCD_USR_KBN,");
//            sql.addSql("   SCH_DATA.SCD_FR_DATE,");
//            sql.addSql("   SCH_DATA.SCD_TO_DATE,");
//            sql.addSql("   SCH_DATA.SCD_DAILY,");
//            sql.addSql("   SCH_DATA.SCD_BGCOLOR,");
//            sql.addSql("   SCH_DATA.SCD_TITLE,");
//            sql.addSql("   SCH_DATA.SCD_VALUE,");
//            sql.addSql("   SCH_DATA.SCD_BIKO,");
//            sql.addSql("   SCH_DATA.SCD_PUBLIC,");
//            sql.addSql("   SCH_DATA.SCD_AUID,");
//            sql.addSql("   SCH_DATA.SCD_ADATE,");
//            sql.addSql("   SCH_DATA.SCD_EUID,");
//            sql.addSql("   SCH_DATA.SCD_EDATE,");
//            sql.addSql("   SCH_DATA.SCE_SID,");
//            sql.addSql("   SCH_DATA.SCD_RSSID,");
//            sql.addSql("   SCH_DATA.SCD_EDIT");
//            sql.addSql(" from");
//            sql.addSql("   SCH_DATA,");
//            sql.addSql(" (");
//            sql.addSql(" select");
//            sql.addSql("   SCH_DATA.SCE_SID");
//            sql.addSql(" from");
//            sql.addSql("   SCH_DATA");
//            sql.addSql(" where ");
//            sql.addSql("   SCH_DATA.SCE_SID!=?");
//            sql.addSql(" and ");
//            sql.addSql("   SCH_DATA.SCD_SID=?");
//            sql.addSql(" ) as EXID");
//            sql.addSql(" where ");
//            sql.addSql("   SCH_DATA.SCE_SID=EXID.SCE_SID");
//
//            pstmt = con.prepareStatement(sql.toSqlString());
//            sql.addValue(GSConstNippou.DEF_SCE_SID);
//            sql.addValue(scdSid);
//            log__.info(sql.toLogString());
//            sql.setParameter(pstmt);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                ret.add(__getNtpDataFromRs(rs));
//            }
//
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//
//        return ret;
//    }

    /**
     * <p>
     * 日報SIDからスケジュール拡張情報＋ユーザ情報を取得する
     * @param nipSid
     *            日報SID
     * @param crange
     *            共有範囲設定 0=全て、1=所属グループ内のみ
     * @return NippouExSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public NippouExSearchModel getNippouExData(int nipSid, int crange)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        NippouExSearchModel ret = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   NTP_DATA.NIP_SID,");
            sql.addSql("   NTP_DATA.USR_SID,");
            sql.addSql("   NTP_EXDATA.NEX_SID,");
            sql.addSql("   NTP_EXDATA.NEX_KBN,");
            sql.addSql("   NTP_EXDATA.NEX_DWEEK1,");
            sql.addSql("   NTP_EXDATA.NEX_DWEEK2,");
            sql.addSql("   NTP_EXDATA.NEX_DWEEK3,");
            sql.addSql("   NTP_EXDATA.NEX_DWEEK4,");
            sql.addSql("   NTP_EXDATA.NEX_DWEEK5,");
            sql.addSql("   NTP_EXDATA.NEX_DWEEK6,");
            sql.addSql("   NTP_EXDATA.NEX_DWEEK7,");
            sql.addSql("   NTP_EXDATA.NEX_DAY,");
            sql.addSql("   NTP_EXDATA.NEX_WEEK,");
            sql.addSql("   NTP_EXDATA.NEX_TIME_FR,");
            sql.addSql("   NTP_EXDATA.NEX_TIME_TO,");
            sql.addSql("   NTP_EXDATA.NEX_KADO_HH,");
            sql.addSql("   NTP_EXDATA.NEX_KADO_MM,");
            sql.addSql("   NTP_EXDATA.NEX_TRAN_KBN,");
            sql.addSql("   NTP_EXDATA.NEX_DATE_FR,");
            sql.addSql("   NTP_EXDATA.NEX_DATE_TO,");
            sql.addSql("   NTP_EXDATA.NEX_MGY_SID,");
            sql.addSql("   NTP_EXDATA.NEX_NAN_SID,");
            sql.addSql("   NTP_EXDATA.NEX_ACO_SID,");
            sql.addSql("   NTP_EXDATA.NEX_ABO_SID,");
            sql.addSql("   NTP_EXDATA.NEX_TITLE,");
            sql.addSql("   NTP_EXDATA.NEX_TITLE_CLO,");
            sql.addSql("   NTP_EXDATA.NEX_MPR_SID,");
            sql.addSql("   NTP_EXDATA.NEX_MKB_SID,");
            sql.addSql("   NTP_EXDATA.NEX_MKH_SID,");
            sql.addSql("   NTP_EXDATA.NEX_TIEUP_SID,");
            sql.addSql("   NTP_EXDATA.NEX_KEIZOKU,");
            sql.addSql("   NTP_EXDATA.NEX_ACTEND,");
            sql.addSql("   NTP_EXDATA.NEX_DETAIL,");
            sql.addSql("   NTP_EXDATA.NEX_ASSIGN,");
            sql.addSql("   NTP_EXDATA.NEX_KINGAKU,");
            sql.addSql("   NTP_EXDATA.NEX_MIKOMI,");
            sql.addSql("   NTP_EXDATA.NEX_SYOKAN,");
            sql.addSql("   NTP_EXDATA.NEX_PUBLIC,");
            sql.addSql("   NTP_EXDATA.NEX_EDIT,");
            sql.addSql("   NTP_EXDATA.NEX_AUID,");
            sql.addSql("   NTP_EXDATA.NEX_ADATE,");
            sql.addSql("   NTP_EXDATA.NEX_EUID,");
            sql.addSql("   NTP_EXDATA.NEX_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA,");
            sql.addSql("   NTP_EXDATA");
            sql.addSql(" where ");
            sql.addSql("   NTP_DATA.NIP_SID=?");
            sql.addSql(" and");
            sql.addSql("   NTP_DATA.NEX_SID = NTP_EXDATA.NEX_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(nipSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getNtpExdataFromRs(rs);
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
//    public ArrayList<Integer> getScheduleReserveData(int scdSid)
//            throws SQLException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        ArrayList<Integer> ret = new ArrayList<Integer>();
//        con = getCon();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//
//            sql.addSql(" select");
//            sql.addSql("   RSV_SIS_YRK.RSD_SID");
//            sql.addSql(" from");
//            sql.addSql("   SCH_DATA,");
//            sql.addSql("   RSV_SIS_YRK");
//            sql.addSql(" where ");
//            sql.addSql("   SCH_DATA.SCD_SID=?");
//            sql.addSql(" and");
//            sql.addSql("   SCH_DATA.SCD_RSSID <> -1");
//            sql.addSql(" and");
//            sql.addSql("   SCH_DATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
//            sql.addSql(" group by");
//            sql.addSql("   RSV_SIS_YRK.RSD_SID");
//
//            pstmt = con.prepareStatement(sql.toSqlString());
//            sql.addValue(scdSid);
//            log__.info(sql.toLogString());
//            sql.setParameter(pstmt);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                ret.add(new Integer(rs.getInt("RSD_SID")));
//            }
//
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return ret;
//    }

    /**
     * <p>
     * スケジュール拡張SIDから同時登録された施設予約情報を取得する
     * @param sceSid
     *            スケジュールSID
     * @return ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
//    public ArrayList<Integer> getScheduleReserveDataFromExSid(int sceSid)
//            throws SQLException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        ArrayList<Integer> ret = new ArrayList<Integer>();
//        con = getCon();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//
//            sql.addSql(" select");
//            sql.addSql("   RSV_SIS_YRK.RSD_SID");
//            sql.addSql(" from");
//            sql.addSql("  (");
//            sql.addSql("  select");
//            sql.addSql("    SCH_DATA.SCD_SID,");
//            sql.addSql("    SCH_DATA.SCD_RSSID");
//            sql.addSql("  from");
//            sql.addSql("    SCH_DATA,");
//            sql.addSql("    SCH_EXDATA");
//            sql.addSql("  where");
//            sql.addSql("    SCH_EXDATA.SCE_SID = SCH_DATA.SCE_SID");
//            sql.addSql("  and");
//            sql.addSql("    SCH_EXDATA.SCE_SID = ?");
//            sql.addSql("  ) EXDATA,");
//            sql.addSql("   RSV_SIS_YRK");
//            sql.addSql(" where");
//            sql.addSql("   EXDATA.SCD_RSSID > 0");
//            sql.addSql(" and");
//            sql.addSql("   EXDATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
//            sql.addSql(" group by");
//            sql.addSql("   RSV_SIS_YRK.RSD_SID");
//
//            pstmt = con.prepareStatement(sql.toSqlString());
//            sql.addValue(sceSid);
//            log__.info(sql.toLogString());
//            sql.setParameter(pstmt);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                ret.add(new Integer(rs.getInt("RSD_SID")));
//            }
//
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return ret;
//    }

    /**
     * <p>
     * スケジュール拡張SIDから同時登録された施設予約情報を取得する
     * @param sceSid
     *            スケジュールSID
     * @return ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
//    public ArrayList<Integer> getScheduleReserveSidFromExSid(int sceSid)
//            throws SQLException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        ArrayList<Integer> ret = new ArrayList<Integer>();
//        con = getCon();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//
//            sql.addSql(" select");
//            sql.addSql("   RSV_SIS_YRK.RSY_SID");
//            sql.addSql(" from");
//            sql.addSql("  (");
//            sql.addSql("  select");
//            sql.addSql("    SCH_DATA.SCD_SID,");
//            sql.addSql("    SCH_DATA.SCD_RSSID");
//            sql.addSql("  from");
//            sql.addSql("    SCH_DATA,");
//            sql.addSql("    SCH_EXDATA");
//            sql.addSql("  where");
//            sql.addSql("    SCH_EXDATA.SCE_SID = SCH_DATA.SCE_SID");
//            sql.addSql("  and");
//            sql.addSql("    SCH_EXDATA.SCE_SID = ?");
//            sql.addSql("  ) EXDATA,");
//            sql.addSql("   RSV_SIS_YRK");
//            sql.addSql(" where");
//            sql.addSql("   EXDATA.SCD_RSSID > 0");
//            sql.addSql(" and");
//            sql.addSql("   EXDATA.SCD_RSSID = RSV_SIS_YRK.SCD_RSSID");
//            sql.addSql(" group by");
//            sql.addSql("   RSV_SIS_YRK.RSY_SID");
//
//            pstmt = con.prepareStatement(sql.toSqlString());
//            sql.addValue(sceSid);
//            log__.info(sql.toLogString());
//            sql.setParameter(pstmt);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                ret.add(new Integer(rs.getInt("RSY_SID")));
//            }
//
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return ret;
//    }

    /**
     * スケジュールグループSIDを指定し同時登録しているグループ情報を取得します
     * @param scdGpSid
     *            スケジュールグループSID
     * @param usrSid
     *            除外するユーザ
     * @param usrKbn
     *            除外するユーザの区分
     * @param crange
     *            共有範囲設定0=全て、1=所属グループのみ
     * @return ArrayLis in CmnUsrmInfModel
     * @throws SQLException
     *             SQL実行時例外
     */
//    public ArrayList<CmnUsrmInfModel> getScheduleGrpList(int scdGpSid,
//            int usrSid, int usrKbn, int crange) throws SQLException {
//
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        ArrayList<CmnUsrmInfModel> ret = new ArrayList<CmnUsrmInfModel>();
//        CmnUsrmInfModel usrMdl = null;
//        con = getCon();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//
//            sql.addSql(" select");
//            sql.addSql("   SCH_DATA.SCD_SID,");
//            sql.addSql("   SCH_DATA.SCD_USR_SID,");
//            sql.addSql("   SCH_DATA.SCD_USR_KBN,");
//            sql.addSql("   SCH_DATA.SCD_GRP_SID,");
//            sql.addSql("   CMN_GROUPM.GRP_SID,");
//            sql.addSql("   CMN_GROUPM.GRP_NAME,");
//            sql.addSql("   CMN_GROUPM.GRP_NAME_KN");
//            sql.addSql(" from");
//            sql.addSql("   SCH_DATA,");
//            sql.addSql("   CMN_GROUPM");
//            sql.addSql(" where ");
//            sql.addSql("   SCH_DATA.SCD_USR_SID = CMN_GROUPM.GRP_SID");
//            sql.addSql(" and");
//            sql.addSql("   SCH_DATA.SCD_GRP_SID = ?");
//            sql.addSql(" and");
//            sql.addSql("   SCH_DATA.SCD_USR_KBN = ?");
//            sql.addSql(" and");
//            sql.addSql("   SCH_DATA.SCD_USR_SID !=?");
//            sql.addSql(" and");
//            sql.addSql("   SCH_DATA.SCD_USR_KBN !=?");
//            sql.addValue(scdGpSid);
//            sql.addValue(GSConstNippou.USER_KBN_GROUP);
//            sql.addValue(usrSid);
//            sql.addValue(usrKbn);
//            sql.addSql(" order by");
//            sql.addSql("   CMN_GROUPM.GRP_NAME_KN");
//
//            pstmt = con.prepareStatement(sql.toSqlString());
//            sql.setParameter(pstmt);
//            log__.info(sql.toLogString());
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                usrMdl = new CmnUsrmInfModel();
//                usrMdl.setUsrSid(rs.getInt("GRP_SID"));
//                usrMdl.setUsiSei(rs.getString("GRP_NAME"));
//                usrMdl.setUsiMei("");
//                ret.add(usrMdl);
//            }
//
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return ret;
//    }

    /**
     * スケジュールSIDを指定し同時登録しているスケジュールSIDの配列を取得します
     * @param scdSid
     *            スケジュールSID
     * @param userSid
     *            ユーザSID
     * @param crange
     *            共有範囲設定
     * @return ArrayList in Integer
     * @throws SQLException
     *             SQL実行時例外
     */
//    public ArrayList<Integer> getScheduleUsrs(int scdSid, int userSid,
//            int crange) throws SQLException {
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//
//        CmnUsrmInfModel usrMdl = null;
//        con = getCon();
//        ArrayList<Integer> scds = new ArrayList<Integer>();
//
//        try {
//            // SQL文
//            SqlBuffer sql = new SqlBuffer();
//
//            sql.addSql(" select");
//            sql.addSql("   SCH_DATA.SCD_SID");
//            sql.addSql(" from");
//            sql.addSql("   (");
//            sql.addSql("   select");
//            sql.addSql("    SCH_DATA.SCD_GRP_SID");
//            sql.addSql("   from");
//            sql.addSql("    SCH_DATA");
//            sql.addSql("   where");
//            sql.addSql("    SCH_DATA.SCD_SID=?");
//            sql.addSql("   and");
//            sql.addSql("    SCH_DATA.SCD_GRP_SID!=?");
//            sql.addSql("   ) as GP,");
//            sql.addSql("   SCH_DATA,");
//            sql.addSql("   CMN_USRM_INF");
//            sql.addSql(" where ");
//            sql.addSql("   SCH_DATA.SCD_USR_SID = CMN_USRM_INF.USR_SID");
//            sql.addSql(" and");
//            sql.addSql("   GP.SCD_GRP_SID = SCH_DATA.SCD_GRP_SID");
//            sql.addValue(scdSid);
//            sql.addValue(GSConstNippou.DF_SCHGP_ID);
//            if (crange == GSConstNippou.CRANGE_SHARE_GROUP) {
//                sql.addSql(" and");
//                sql.addSql("   SCH_DATA.SCD_USR_SID in (");
//                sql.addSql("    select");
//                sql.addSql("     CMN_BELONGM.USR_SID as USR_SID");
//                sql.addSql("    from");
//                sql.addSql("     CMN_BELONGM,");
//                sql.addSql("    (");
//                sql.addSql("     select");
//                sql.addSql("       CMN_GROUPM.GRP_SID as GRP_SID");
//                sql.addSql("     from");
//                sql.addSql("       CMN_GROUPM,");
//                sql.addSql("       CMN_BELONGM");
//                sql.addSql("     where");
//                sql.addSql("       CMN_BELONGM.USR_SID = ?");
//                sql.addSql("     and");
//                sql.addSql("       CMN_BELONGM.GRP_SID = CMN_GROUPM.GRP_SID");
//                sql.addSql("     ) BELONG");
//                sql.addSql("    where");
//                sql.addSql("      CMN_BELONGM.GRP_SID = BELONG.GRP_SID");
//                sql.addSql("    group by CMN_BELONGM.USR_SID");
//                sql.addSql("   )");
//                sql.addValue(userSid);
//            }
//
//            pstmt = con.prepareStatement(sql.toSqlString());
//            sql.setParameter(pstmt);
//            log__.info(sql.toLogString());
//            rs = pstmt.executeQuery();
//            int index = 0;
//
//            for (int i = 0; rs.next(); i++) {
//                scds.add(new Integer(rs.getInt("SCD_SID")));
//            }
//
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closeStatement(pstmt);
//        }
//        return scds;
//    }

    /**
     * <p>
     * Create NTP_DATA Data Bindding JavaBean From ResultSet
     * @param rs
     *            ResultSet
     * @return created SchDataModel
     * @throws SQLException
     *             SQL実行例外
     */
    private NtpDataModel __getNtpDataFromRs(ResultSet rs) throws SQLException {
        NtpDataModel bean = new NtpDataModel();
        bean.setNipSid(rs.getInt("NIP_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setNipDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_DATE")));
        bean.setNipFrTime(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_FR_TIME")));
        bean.setNipToTime(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_TO_TIME")));
        bean.setNipMgySid(rs.getInt("NIP_MGY_SID"));
        bean.setNanSid(rs.getInt("NAN_SID"));
        bean.setAcoSid(rs.getInt("ACO_SID"));
        bean.setAbaSid(rs.getInt("ABA_SID"));
        bean.setNipTitle(rs.getString("NIP_TITLE"));
        bean.setNipTitleClo(rs.getInt("NIP_TITLE_CLO"));
        bean.setMprSid(rs.getInt("MPR_SID"));
        bean.setMkbSid(rs.getInt("MKB_SID"));
        bean.setMkhSid(rs.getInt("MKH_SID"));
        bean.setNipTieupSid(rs.getInt("NIP_TIEUP_SID"));
        bean.setNipKeizoku(rs.getInt("NIP_KEIZOKU"));
        bean.setNipActend(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_ACTEND")));
        bean.setNipDetail(rs.getString("NIP_DETAIL"));
        bean.setNipActionDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_ACTION_DATE")));
        bean.setNipAction(rs.getString("NIP_ACTION"));
        bean.setNipActDateKbn(rs.getInt("NIP_ACTDATE_KBN"));
        bean.setNipAssign(rs.getString("NIP_ASSIGN"));
        bean.setNipKingaku(rs.getInt("NIP_KINGAKU"));
        bean.setNipMikomi(rs.getInt("NIP_MIKOMI"));
        bean.setNipSyokan(rs.getString("NIP_SYOKAN"));
        bean.setNipPublic(rs.getInt("NIP_PUBLIC"));
        bean.setNipEdit(rs.getInt("NIP_EDIT"));
        bean.setNexSid(rs.getInt("NEX_SID"));
        bean.setNipAuid(rs.getInt("NIP_AUID"));
        bean.setNipAdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_ADATE")));
        bean.setNipEuid(rs.getInt("NIP_EUID"));
        bean.setNipEdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_EDATE")));
        return bean;
    }
    /**
     * <p>
     * Create NTP_DATA Data Bindding JavaBean From ResultSet
     * @param rs
     *            ResultSet
     * @return created SchDataModel
     * @throws SQLException
     *             SQL実行例外
     */
    private NtpDataModel __getNtpDataPlusNameFromRs(ResultSet rs) throws SQLException {
        NtpDataModel bean = new NtpDataModel();
        bean.setNipSid(rs.getInt("NIP_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setNipDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_DATE")));
        bean.setNipFrTime(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_FR_TIME")));
        bean.setNipToTime(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_TO_TIME")));
        bean.setNipMgySid(rs.getInt("NIP_MGY_SID"));
        bean.setNanSid(rs.getInt("NAN_SID"));
        bean.setAcoSid(rs.getInt("ACO_SID"));
        bean.setAbaSid(rs.getInt("ABA_SID"));
        bean.setNipTitle(rs.getString("NIP_TITLE"));
        bean.setNipTitleClo(rs.getInt("NIP_TITLE_CLO"));
        bean.setMprSid(rs.getInt("MPR_SID"));
        bean.setMkbSid(rs.getInt("MKB_SID"));
        bean.setMkhSid(rs.getInt("MKH_SID"));
        bean.setNipTieupSid(rs.getInt("NIP_TIEUP_SID"));
        bean.setNipKeizoku(rs.getInt("NIP_KEIZOKU"));
        bean.setNipActend(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_ACTEND")));
        bean.setNipDetail(rs.getString("NIP_DETAIL"));
        bean.setNipActionDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_ACTION_DATE")));
        bean.setNipAction(rs.getString("NIP_ACTION"));
        bean.setNipActDateKbn(rs.getInt("NIP_ACTDATE_KBN"));
        bean.setNipAssign(rs.getString("NIP_ASSIGN"));
        bean.setNipKingaku(rs.getInt("NIP_KINGAKU"));
        bean.setNipMikomi(rs.getInt("NIP_MIKOMI"));
        bean.setNipSyokan(rs.getString("NIP_SYOKAN"));
        bean.setNipPublic(rs.getInt("NIP_PUBLIC"));
        bean.setNipEdit(rs.getInt("NIP_EDIT"));
        bean.setNexSid(rs.getInt("NEX_SID"));
        bean.setNipAuid(rs.getInt("NIP_AUID"));
        bean.setNipAdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_ADATE")));
        bean.setNipEuid(rs.getInt("NIP_EUID"));
        bean.setNipEdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_EDATE")));
        bean.setNtpUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
        return bean;
    }
    /**
     * <p>
     * NippouSearchModel Bindding JavaBean From ResultSet
     * @param rs
     *            ResultSet
     * @return created ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    private NippouSearchModel __getNipUsrDataFromRs(ResultSet rs)
            throws SQLException {
        NippouSearchModel bean = new NippouSearchModel();
        bean.setNipSid(rs.getInt("NIP_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setNipDate(UDate.getInstanceTimestamp(rs.getTimestamp("NIP_DATE")));
        bean.setNipFrTime(UDate.getInstanceTimestamp(rs.getTimestamp("NIP_FR_TIME")));
        bean.setNipToTime(UDate.getInstanceTimestamp(rs.getTimestamp("NIP_TO_TIME")));
        bean.setNipKadoHh(rs.getInt("NIP_KADO_HH"));
        bean.setNipKadoMm(rs.getInt("NIP_KADO_MM"));
        bean.setNipMgySid(rs.getInt("NIP_MGY_SID"));
        bean.setNanSid(rs.getInt("NAN_SID"));
        bean.setAcoSid(rs.getInt("ACO_SID"));
        bean.setAbaSid(rs.getInt("ABA_SID"));
        bean.setNipTitle(rs.getString("NIP_TITLE"));
        bean.setNipTitleClo(rs.getInt("NIP_TITLE_CLO"));
        bean.setMprSid(rs.getInt("MPR_SID"));
        bean.setMkbSid(rs.getInt("MKB_SID"));
        bean.setMkhSid(rs.getInt("MKH_SID"));
        bean.setNipTieupSid(rs.getInt("NIP_TIEUP_SID"));
        bean.setNipKeizoku(rs.getInt("NIP_KEIZOKU"));
        bean.setNipActend(UDate.getInstanceTimestamp(rs.getTimestamp("NIP_ACTEND")));
        bean.setNipActionDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_ACTION_DATE")));
        bean.setNipAction(rs.getString("NIP_ACTION"));
        bean.setNipActDateKbn(rs.getInt("NIP_ACTDATE_KBN"));
        bean.setNipDetail(rs.getString("NIP_DETAIL"));
        bean.setNipAssign(rs.getString("NIP_ASSIGN"));
        bean.setNipKingaku(rs.getInt("NIP_KINGAKU"));
        bean.setNipMikomi(rs.getInt("NIP_MIKOMI"));
        bean.setNipSyokan(rs.getString("NIP_SYOKAN"));
        bean.setNipPublic(rs.getInt("NIP_PUBLIC"));
        bean.setNipEdit(rs.getInt("NIP_EDIT"));
        bean.setNexSid(rs.getInt("NEX_SID"));
        bean.setNipAuid(rs.getInt("NIP_AUID"));
        bean.setNipAdate(UDate.getInstanceTimestamp(rs.getTimestamp("NIP_ADATE")));
        bean.setNipEuid(rs.getInt("NIP_EUID"));
        bean.setNipEdate(UDate.getInstanceTimestamp(rs.getTimestamp("NIP_EDATE")));
        return bean;
    }

    /**
     * <p>
     * Create SCH_DATA Data Bindding JavaBean From ResultSet
     * @param rs
     *            ResultSet
     * @return created ScheduleCsvModel
     * @throws SQLException
     *             SQL実行例外
     */
//    private ScheduleCsvModel __getScheduleCsvFromRs(ResultSet rs)
//            throws SQLException {
//        ScheduleCsvModel bean = new ScheduleCsvModel();
//        bean.setScdSid(rs.getInt("SCD_SID"));
//        bean.setScdUsrSid(rs.getInt("SCD_USR_SID"));
//        bean.setScdGrpSid(rs.getInt("SCD_GRP_SID"));
//        bean.setScdUsrKbn(rs.getInt("SCD_USR_KBN"));
//        bean.setScdFrDate(UDate.getInstanceTimestamp(rs
//                .getTimestamp("SCD_FR_DATE")));
//        bean.setScdToDate(UDate.getInstanceTimestamp(rs
//                .getTimestamp("SCD_TO_DATE")));
//        bean.setScdDaily(rs.getInt("SCD_DAILY"));
//        bean.setScdBgcolor(rs.getInt("SCD_BGCOLOR"));
//        bean.setScdTitle(rs.getString("SCD_TITLE"));
//        bean.setScdValue(rs.getString("SCD_VALUE"));
//        bean.setScdBiko(rs.getString("SCD_BIKO"));
//        bean.setScdPublic(rs.getInt("SCD_PUBLIC"));
//        bean.setScdAuid(rs.getInt("SCD_AUID"));
//        bean.setScdAdate(UDate.getInstanceTimestamp(rs
//                .getTimestamp("SCD_ADATE")));
//        bean.setScdEuid(rs.getInt("SCD_EUID"));
//        bean.setScdEdate(UDate.getInstanceTimestamp(rs
//                .getTimestamp("SCD_EDATE")));
//
//        bean.setUsrName(rs.getString("USR_NAME"));
//        bean.setAddUsrName(rs.getString("AUSR_NAME"));
//        bean.setEdtUsrName(rs.getString("EUSR_NAME"));
//        bean.setScdFrDateStr(UDateUtil.getSlashYYMD(bean.getScdFrDate()) + " "
//                + UDateUtil.getSeparateHM(bean.getScdFrDate()));
//        bean.setScdToDateStr(UDateUtil.getSlashYYMD(bean.getScdToDate()) + " "
//                + UDateUtil.getSeparateHM(bean.getScdToDate()));
//
//        return bean;
//    }

    /**
     * <p>
     * Create NTP_EXDATA Data Bindding JavaBean From ResultSet
     * @param rs
     *            ResultSet
     * @return created NippouExSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    private NippouExSearchModel __getNtpExdataFromRs(ResultSet rs)
            throws SQLException {
        NippouExSearchModel bean = new NippouExSearchModel();
        // 選択日報情報
        bean.setNipSid(rs.getInt("NIP_SID"));
        bean.setNipUsrSid(rs.getInt("USR_SID"));
        // 拡張情報
        bean.setNexSid(rs.getInt("NEX_SID"));
        bean.setNexKbn(rs.getInt("NEX_KBN"));
        bean.setNexDweek1(rs.getInt("NEX_DWEEK1"));
        bean.setNexDweek2(rs.getInt("NEX_DWEEK2"));
        bean.setNexDweek3(rs.getInt("NEX_DWEEK3"));
        bean.setNexDweek4(rs.getInt("NEX_DWEEK4"));
        bean.setNexDweek5(rs.getInt("NEX_DWEEK5"));
        bean.setNexDweek6(rs.getInt("NEX_DWEEK6"));
        bean.setNexDweek7(rs.getInt("NEX_DWEEK7"));
        bean.setNexDay(rs.getInt("NEX_DAY"));
        bean.setNexWeek(rs.getInt("NEX_WEEK"));
        bean.setNexTimeFr(UDate.getInstanceTimestamp(rs.getTimestamp("NEX_TIME_FR")));
        bean.setNexTimeTo(UDate.getInstanceTimestamp(rs.getTimestamp("NEX_TIME_TO")));
        bean.setNexKadoHh(rs.getInt("NEX_KADO_HH"));
        bean.setNexKadoMm(rs.getInt("NEX_KADO_MM"));
        bean.setNexTranKbn(rs.getInt("NEX_TRAN_KBN"));
        bean.setNexDateFr(UDate.getInstanceTimestamp(rs.getTimestamp("NEX_DATE_FR")));
        bean.setNexDateTo(UDate.getInstanceTimestamp(rs.getTimestamp("NEX_DATE_TO")));
        bean.setNexMgySid(rs.getInt("NEX_MGY_SID"));
        bean.setNexNanSid(rs.getInt("NEX_NAN_SID"));
        bean.setNexAcoSid(rs.getInt("NEX_ACO_SID"));
        bean.setNexAboSid(rs.getInt("NEX_ABO_SID"));
        bean.setNexTitle(rs.getString("NEX_TITLE"));
        bean.setNexTitleClo(rs.getInt("NEX_TITLE_CLO"));
        bean.setNexMprSid(rs.getInt("NEX_MPR_SID"));
        bean.setNexMkbSid(rs.getInt("NEX_MKB_SID"));
        bean.setNexMkhSid(rs.getInt("NEX_MKH_SID"));
        bean.setNexTieupSid(rs.getInt("NEX_TIEUP_SID"));
        bean.setNexKeizoku(rs.getInt("NEX_KEIZOKU"));
        bean.setNexActend(UDate.getInstanceTimestamp(rs.getTimestamp("NEX_ACTEND")));
        bean.setNexDetail(rs.getString("NEX_DETAIL"));
        bean.setNexAssign(rs.getString("NEX_ASSIGN"));
        bean.setNexKingaku(rs.getInt("NEX_KINGAKU"));
        bean.setNexMikomi(rs.getInt("NEX_MIKOMI"));
        bean.setNexSyokan(rs.getString("NEX_SYOKAN"));
        bean.setNexPublic(rs.getInt("NEX_PUBLIC"));
        bean.setNexEdit(rs.getInt("NEX_EDIT"));
        bean.setNexAuid(rs.getInt("NEX_AUID"));
        bean.setNexAdate(UDate.getInstanceTimestamp(rs.getTimestamp("NEX_ADATE")));
        bean.setNexEuid(rs.getInt("NEX_EUID"));
        bean.setNexEdate(UDate.getInstanceTimestamp(rs.getTimestamp("NEX_EDATE")));
        return bean;
    }

    /**
     * <p>
     * Create SCH_DATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created ScheduleCsvModel
     * @throws SQLException SQL実行例外
     */
    private NippouCsvModel __getNippouCsvFromRs(ResultSet rs)
            throws SQLException {
        NippouCsvModel bean = new NippouCsvModel();
        bean.setNipSid(rs.getInt("NIP_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setNipDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_DATE")));
        bean.setNipFrTime(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_FR_TIME")));
        bean.setNipToTime(UDate.getInstanceTimestamp(rs
                .getTimestamp("NIP_TO_TIME")));
        bean.setNanSid(rs.getInt("NAN_SID"));
        bean.setAcoSid(rs.getInt("ACO_SID"));
        bean.setAbaSid(rs.getInt("ABA_SID"));
        bean.setNipTitle(rs.getString("NIP_TITLE"));
        bean.setNipTitleClo(rs.getInt("NIP_TITLE_CLO"));
        bean.setMkbSid(rs.getInt("MKB_SID"));
        bean.setMkhSid(rs.getInt("MKH_SID"));
        bean.setNipDetail(rs.getString("NIP_DETAIL"));
        bean.setNipMikomi(rs.getInt("NIP_MIKOMI"));

        bean.setNtpDateStr(UDateUtil.getYYMD(bean.getNipDate()));
        bean.setNtpFrDateStr(UDateUtil.getSlashYYMD(bean.getNipFrTime()));
        bean.setNtpFrTimeStr(UDateUtil.getSeparateHM(bean.getNipFrTime()));
        bean.setNtpToDateStr(UDateUtil.getSlashYYMD(bean.getNipToTime()));
        bean.setNtpToTimeStr(UDateUtil.getSeparateHM(bean.getNipToTime()));

        bean.setUsrLgId(rs.getString("USR_LGID"));
        bean.setAnkenCode(rs.getString("NAN_CODE"));
        bean.setAcoCode(rs.getString("ACO_CODE"));
        bean.setKbunruiCode(rs.getString("NKB_CODE"));
        bean.setKhouhouCode(rs.getString("NKH_CODE"));

        return bean;
    }
    /**
     * <p>
     * SchDataModelからSchduleCsvModelを作成し、ScheduleCsvRecordListenerImplにセットする。
     * @param model
     *            CmnUsrmInfModel
     * @param sessionUsrSid
     *            セッションユーザSID(実行者)
     * @param rl
     *            UsrCsvRecordListenerIppanImpl
     * @throws SQLException
     *             SQL実行例外
     * @throws CSVException
     *             CSV出力時例外
     */
//    public static void setSchCsvRecordFromSchDataModel(ScheduleCsvModel model,
//            int sessionUsrSid, SchCsvRecordListenerIppanImpl rl)
//            throws SQLException, CSVException {
//
//        int usrSid = model.getScdUsrSid();
//        int usrKbn = model.getScdUsrKbn();
//        if (usrKbn == GSConstNippou.USER_KBN_USER && usrSid == sessionUsrSid) {
//            // 本人
//
//        } else if (usrKbn == GSConstNippou.USER_KBN_USER
//                && usrSid != sessionUsrSid) {
//            // 他ユーザ
//            if (model.getScdPublic() == GSConstNippou.DSP_YOTEIARI) {
//                // 予定あり
//                model.setScdTitle(GSConstNippou.DSP_YOTEIARI_STRING);
//                model.setScdValue("");
//                model.setScdBiko("");
//            } else if (model.getScdPublic() == GSConstNippou.DSP_NOT_PUBLIC) {
//                // 非公開(CSV出力しない)
//                return;
//            }
//        }
//        rl.setRecord(model);
//    }

    /**
     * <p>
     * NtpDataModelからNippouCsvModelを作成し、NippouCsvRecordListenerImplにセットする。
     * @param model CmnUsrmInfModel
     * @param sessionUsrSid セッションユーザSID(実行者)
     * @param rl UsrCsvRecordListenerIppanImpl
     * @param req リクエスト
     * @throws SQLException SQL実行例外
     * @throws CSVException CSV出力時例外
     */
    public static void setNtpCsvRecordFromSchDataModel(NippouCsvModel model,
            int sessionUsrSid, NtpCsvRecordListenerIppanImpl rl, HttpServletRequest req)
            throws SQLException, CSVException {

        rl.setRecord(model);
    }
}
