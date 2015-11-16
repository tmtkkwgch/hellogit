package jp.groupsession.v2.sch.dao;

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
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.sch.model.SchExdataModel;
import jp.groupsession.v2.sch.model.ScheduleExSearchModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>SCH_EXDATA Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class SchExdataDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(SchExdataDao.class);

    /**
     * <p>Default Constructor
     */
    public SchExdataDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public SchExdataDao(Connection con) {
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
            sql.addSql("drop table SCH_EXDATA");

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
            sql.addSql(" create table SCH_EXDATA (");
            sql.addSql("   SCE_SID NUMBER(10,0) not null,");
            sql.addSql("   SCE_KBN NUMBER(10,0) not null,");
            sql.addSql("   SCE_DWEEK1 NUMBER(10,0),");
            sql.addSql("   SCE_DWEEK2 NUMBER(10,0),");
            sql.addSql("   SCE_DWEEK3 NUMBER(10,0),");
            sql.addSql("   SCE_DWEEK4 NUMBER(10,0),");
            sql.addSql("   SCE_DWEEK5 NUMBER(10,0),");
            sql.addSql("   SCE_DWEEK6 NUMBER(10,0),");
            sql.addSql("   SCE_DWEEK7 NUMBER(10,0),");
            sql.addSql("   SCE_DAY NUMBER(10,0),");
            sql.addSql("   SCE_WEEK NUMBER(10,0),");
            sql.addSql("   SCE_MONTH NUMBER(10,0),");
            sql.addSql("   SCE_TIME_FR varchar(23) not null,");
            sql.addSql("   SCE_TIME_TO varchar(23) not null,");
            sql.addSql("   SCE_TRAN_KBN NUMBER(10,0) not null,");
            sql.addSql("   SCE_DATE_FR varchar(23) not null,");
            sql.addSql("   SCE_DATE_TO varchar(23) not null,");
            sql.addSql("   SCE_BGCOLOR NUMBER(10,0) not null,");
            sql.addSql("   SCE_TITLE varchar(50),");
            sql.addSql("   SCE_VALUE varchar(1000),");
            sql.addSql("   SCE_BIKO varchar(1000),");
            sql.addSql("   SCE_PUBLIC NUMBER(10,0),");
            sql.addSql("   SCE_EDIT NUMBER(10,0),");
            sql.addSql("   SCE_AUID NUMBER(10,0) not null,");
            sql.addSql("   SCE_ADATE varchar(23) not null,");
            sql.addSql("   SCE_EUID NUMBER(10,0) not null,");
            sql.addSql("   SCE_EDATE varchar(23) not null,");
            sql.addSql("   SCE_DAILY NUMBER(10,0),");
            sql.addSql("   primary key (SCE_SID)");
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
     * <p>Insert SCH_EXDATA Data Bindding JavaBean
     * @param bean SCH_EXDATA Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(SchExdataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" SCH_EXDATA(");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCE_KBN,");
            sql.addSql("   SCE_DWEEK1,");
            sql.addSql("   SCE_DWEEK2,");
            sql.addSql("   SCE_DWEEK3,");
            sql.addSql("   SCE_DWEEK4,");
            sql.addSql("   SCE_DWEEK5,");
            sql.addSql("   SCE_DWEEK6,");
            sql.addSql("   SCE_DWEEK7,");
            sql.addSql("   SCE_DAY,");
            sql.addSql("   SCE_WEEK,");
            sql.addSql("   SCE_MONTH_YEARLY,");
            sql.addSql("   SCE_DAY_YEARLY,");
            sql.addSql("   SCE_TIME_FR,");
            sql.addSql("   SCE_TIME_TO,");
            sql.addSql("   SCE_TRAN_KBN,");
            sql.addSql("   SCE_DATE_FR,");
            sql.addSql("   SCE_DATE_TO,");
            sql.addSql("   SCE_BGCOLOR,");
            sql.addSql("   SCE_TITLE,");
            sql.addSql("   SCE_VALUE,");
            sql.addSql("   SCE_BIKO,");
            sql.addSql("   SCE_PUBLIC,");
            sql.addSql("   SCE_EDIT,");
            sql.addSql("   SCE_DAILY,");
            sql.addSql("   SCE_AUID,");
            sql.addSql("   SCE_ADATE,");
            sql.addSql("   SCE_EUID,");
            sql.addSql("   SCE_EDATE");
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
            sql.addIntValue(bean.getSceSid());
            sql.addIntValue(bean.getSceKbn());
            sql.addIntValue(bean.getSceDweek1());
            sql.addIntValue(bean.getSceDweek2());
            sql.addIntValue(bean.getSceDweek3());
            sql.addIntValue(bean.getSceDweek4());
            sql.addIntValue(bean.getSceDweek5());
            sql.addIntValue(bean.getSceDweek6());
            sql.addIntValue(bean.getSceDweek7());
            sql.addIntValue(bean.getSceDay());
            sql.addIntValue(bean.getSceWeek());
            sql.addIntValue(bean.getSceMonthOfYearly());
            sql.addIntValue(bean.getSceDayOfYearly());
            sql.addDateValue(bean.getSceTimeFr());
            sql.addDateValue(bean.getSceTimeTo());
            sql.addIntValue(bean.getSceTranKbn());
            sql.addDateValue(bean.getSceDateFr());
            sql.addDateValue(bean.getSceDateTo());
            sql.addIntValue(bean.getSceBgcolor());
            sql.addStrValue(bean.getSceTitle());
            sql.addStrValue(bean.getSceValue());
            sql.addStrValue(bean.getSceBiko());
            sql.addIntValue(bean.getScePublic());
            sql.addIntValue(bean.getSceEdit());
            sql.addIntValue(bean.getSceDaily());
            sql.addIntValue(bean.getSceAuid());
            sql.addDateValue(bean.getSceAdate());
            sql.addIntValue(bean.getSceEuid());
            sql.addDateValue(bean.getSceEdate());
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
     * <p>Update SCH_EXDATA Data Bindding JavaBean
     * @param bean SCH_EXDATA Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return int 更新件数
     */
    public int update(SchExdataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SCH_EXDATA");
            sql.addSql(" set ");
            sql.addSql("   SCE_KBN=?,");
            sql.addSql("   SCE_DWEEK1=?,");
            sql.addSql("   SCE_DWEEK2=?,");
            sql.addSql("   SCE_DWEEK3=?,");
            sql.addSql("   SCE_DWEEK4=?,");
            sql.addSql("   SCE_DWEEK5=?,");
            sql.addSql("   SCE_DWEEK6=?,");
            sql.addSql("   SCE_DWEEK7=?,");
            sql.addSql("   SCE_DAY=?,");
            sql.addSql("   SCE_WEEK=?,");
            sql.addSql("   SCE_MONTH_YEARLY=?,");
            sql.addSql("   SCE_DAY_YEARLY=?,");
            sql.addSql("   SCE_TIME_FR=?,");
            sql.addSql("   SCE_TIME_TO=?,");
            sql.addSql("   SCE_TRAN_KBN=?,");
            sql.addSql("   SCE_DATE_FR=?,");
            sql.addSql("   SCE_DATE_TO=?,");
            sql.addSql("   SCE_BGCOLOR=?,");
            sql.addSql("   SCE_TITLE=?,");
            sql.addSql("   SCE_VALUE=?,");
            sql.addSql("   SCE_BIKO=?,");
            sql.addSql("   SCE_PUBLIC=?,");
            sql.addSql("   SCE_EDIT=?,");
            sql.addSql("   SCE_AUID=?,");
            sql.addSql("   SCE_ADATE=?,");
            sql.addSql("   SCE_EUID=?,");
            sql.addSql("   SCE_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   SCE_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getSceKbn());
            sql.addIntValue(bean.getSceDweek1());
            sql.addIntValue(bean.getSceDweek2());
            sql.addIntValue(bean.getSceDweek3());
            sql.addIntValue(bean.getSceDweek4());
            sql.addIntValue(bean.getSceDweek5());
            sql.addIntValue(bean.getSceDweek6());
            sql.addIntValue(bean.getSceDweek7());
            sql.addIntValue(bean.getSceDay());
            sql.addIntValue(bean.getSceWeek());
            sql.addIntValue(bean.getSceMonthOfYearly());
            sql.addIntValue(bean.getSceDayOfYearly());
            sql.addDateValue(bean.getSceTimeFr());
            sql.addDateValue(bean.getSceTimeTo());
            sql.addIntValue(bean.getSceTranKbn());
            sql.addDateValue(bean.getSceDateFr());
            sql.addDateValue(bean.getSceDateTo());
            sql.addIntValue(bean.getSceBgcolor());
            sql.addStrValue(bean.getSceTitle());
            sql.addStrValue(bean.getSceValue());
            sql.addStrValue(bean.getSceBiko());
            sql.addIntValue(bean.getScePublic());
            sql.addIntValue(bean.getSceEdit());
            sql.addIntValue(bean.getSceAuid());
            sql.addDateValue(bean.getSceAdate());
            sql.addIntValue(bean.getSceEuid());
            sql.addDateValue(bean.getSceEdate());
            //where
            sql.addIntValue(bean.getSceSid());

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
     * <p>Select SCH_EXDATA All Data
     * @return List in SCH_EXDATAModel
     * @throws SQLException SQL実行例外
     */
    public List<SchExdataModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SchExdataModel> ret = new ArrayList<SchExdataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCE_KBN,");
            sql.addSql("   SCE_DWEEK1,");
            sql.addSql("   SCE_DWEEK2,");
            sql.addSql("   SCE_DWEEK3,");
            sql.addSql("   SCE_DWEEK4,");
            sql.addSql("   SCE_DWEEK5,");
            sql.addSql("   SCE_DWEEK6,");
            sql.addSql("   SCE_DWEEK7,");
            sql.addSql("   SCE_DAY,");
            sql.addSql("   SCE_WEEK,");
            sql.addSql("   SCE_MONTH_YEARLY,");
            sql.addSql("   SCE_DAY_YEARLY,");
            sql.addSql("   SCE_TIME_FR,");
            sql.addSql("   SCE_TIME_TO,");
            sql.addSql("   SCE_TRAN_KBN,");
            sql.addSql("   SCE_DATE_FR,");
            sql.addSql("   SCE_DATE_TO,");
            sql.addSql("   SCE_BGCOLOR,");
            sql.addSql("   SCE_TITLE,");
            sql.addSql("   SCE_VALUE,");
            sql.addSql("   SCE_BIKO,");
            sql.addSql("   SCE_PUBLIC,");
            sql.addSql("   SCE_EDIT,");
            sql.addSql("   SCE_AUID,");
            sql.addSql("   SCE_ADATE,");
            sql.addSql("   SCE_EUID,");
            sql.addSql("   SCE_EDATE,");
            sql.addSql("   SCE_DAILY");
            sql.addSql(" from ");
            sql.addSql("   SCH_EXDATA");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getSchExdataFromRs(rs));
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
     * <p>Select SCH_EXDATA
     * @param bean SCH_EXDATA Model
     * @return SCH_EXDATAModel
     * @throws SQLException SQL実行例外
     */
    public SchExdataModel select(SchExdataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        SchExdataModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCE_KBN,");
            sql.addSql("   SCE_DWEEK1,");
            sql.addSql("   SCE_DWEEK2,");
            sql.addSql("   SCE_DWEEK3,");
            sql.addSql("   SCE_DWEEK4,");
            sql.addSql("   SCE_DWEEK5,");
            sql.addSql("   SCE_DWEEK6,");
            sql.addSql("   SCE_DWEEK7,");
            sql.addSql("   SCE_DAY,");
            sql.addSql("   SCE_WEEK,");
            sql.addSql("   SCE_MONTH_YEARLY,");
            sql.addSql("   SCE_DAY_YEARLY,");
            sql.addSql("   SCE_TIME_FR,");
            sql.addSql("   SCE_TIME_TO,");
            sql.addSql("   SCE_TRAN_KBN,");
            sql.addSql("   SCE_DATE_FR,");
            sql.addSql("   SCE_DATE_TO,");
            sql.addSql("   SCE_BGCOLOR,");
            sql.addSql("   SCE_TITLE,");
            sql.addSql("   SCE_VALUE,");
            sql.addSql("   SCE_BIKO,");
            sql.addSql("   SCE_PUBLIC,");
            sql.addSql("   SCE_EDIT,");
            sql.addSql("   SCE_AUID,");
            sql.addSql("   SCE_ADATE,");
            sql.addSql("   SCE_EUID,");
            sql.addSql("   SCE_EDATE,");
            sql.addSql("   SCE_DAILY");
            sql.addSql(" from");
            sql.addSql("   SCH_EXDATA");
            sql.addSql(" where ");
            sql.addSql("   SCE_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getSceSid());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getSchExdataFromRs(rs);
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
     * スケジュールSIDからスケジュール拡張情報＋ユーザ情報を取得する
     * @param scdSid スケジュールSID
     * @param crange 共有範囲設定 0=全て、1=所属グループ内のみ
     * @param sessionUserSid セッションユーザSID
     * @return ScheduleSearchModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ScheduleExSearchModel getScheduleExData(int scdSid, int crange, int sessionUserSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ScheduleExSearchModel ret = null;
        con = getCon();
        ScheduleSearchDao searchDao = new ScheduleSearchDao(con);

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   SCH_DATA.SCD_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_SID,");
            sql.addSql("   SCH_DATA.SCD_GRP_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_KBN,");
            sql.addSql("   SCH_EXDATA.SCE_SID,");
            sql.addSql("   SCH_EXDATA.SCE_KBN,");
            sql.addSql("   SCH_EXDATA.SCE_DWEEK1,");
            sql.addSql("   SCH_EXDATA.SCE_DWEEK2,");
            sql.addSql("   SCH_EXDATA.SCE_DWEEK3,");
            sql.addSql("   SCH_EXDATA.SCE_DWEEK4,");
            sql.addSql("   SCH_EXDATA.SCE_DWEEK5,");
            sql.addSql("   SCH_EXDATA.SCE_DWEEK6,");
            sql.addSql("   SCH_EXDATA.SCE_DWEEK7,");
            sql.addSql("   SCH_EXDATA.SCE_DAY,");
            sql.addSql("   SCH_EXDATA.SCE_WEEK,");
            sql.addSql("   SCH_EXDATA.SCE_MONTH_YEARLY,");
            sql.addSql("   SCH_EXDATA.SCE_DAY_YEARLY,");
            sql.addSql("   SCH_EXDATA.SCE_TIME_FR,");
            sql.addSql("   SCH_EXDATA.SCE_TIME_TO,");
            sql.addSql("   SCH_EXDATA.SCE_TRAN_KBN,");
            sql.addSql("   SCH_EXDATA.SCE_DATE_FR,");
            sql.addSql("   SCH_EXDATA.SCE_DATE_TO,");
            sql.addSql("   SCH_EXDATA.SCE_BGCOLOR,");
            sql.addSql("   SCH_EXDATA.SCE_TITLE,");
            sql.addSql("   SCH_EXDATA.SCE_VALUE,");
            sql.addSql("   SCH_EXDATA.SCE_BIKO,");
            sql.addSql("   SCH_EXDATA.SCE_PUBLIC,");
            sql.addSql("   SCH_EXDATA.SCE_EDIT,");
            sql.addSql("   SCH_EXDATA.SCE_DAILY,");
            sql.addSql("   SCH_EXDATA.SCE_AUID,");
            sql.addSql("   SCH_EXDATA.SCE_ADATE,");
            sql.addSql("   SCH_EXDATA.SCE_EUID,");
            sql.addSql("   SCH_EXDATA.SCE_EDATE");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   SCH_EXDATA");
            sql.addSql(" where ");
            sql.addSql("   SCH_DATA.SCD_SID=?");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCE_SID = SCH_EXDATA.SCE_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(scdSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getSchExdataPlusFromRs(rs);

                if (ret.getScdGrpSid() != GSConstSchedule.DF_SCHGP_ID) {
                    // 同時登録有りスケジュールの場合ユーザ氏名を取得
                    ArrayList<CmnUsrmInfModel> usrList = new ArrayList<CmnUsrmInfModel>();
                    usrList = searchDao.getScheduleUsrList(scdSid, ret.getScdUsrSid(),
                            ret.getScdUsrKbn(), GSConstSchedule.SSP_AUTHFILTER_EDIT,
                            sessionUserSid);
                    ret.setUsrInfList(usrList);
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
     * <p>スケジュール情報が存在しないスケジュール拡張情報を取得する。
     * @throws SQLException SQL実行例外
     * @return int 更新件数
     */
    public List<Integer> selectSchNoData() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SCE_SID");
            sql.addSql(" from");
            sql.addSql("   SCH_EXDATA");
            sql.addSql(" where ");
            sql.addSql("   SCE_SID not in (");
            sql.addSql("     select SCE_SID from SCH_DATA");
            sql.addSql("     where COALESCE(SCE_SID, 0) > 0");
            sql.addSql("   )");
            pstmt = con.prepareStatement(sql.toSqlString());

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt("SCE_SID"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <p>Delete SCH_EXDATA
     * @param bean SCH_EXDATA Model
     * @throws SQLException SQL実行例外
     * @return int 更新件数
     */
    public int delete(SchExdataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SCH_EXDATA");
            sql.addSql(" where ");
            sql.addSql("   SCE_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getSceSid());

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
     * <p>Delete SCH_EXDATA
     * @param sces SCH_EXDATA
     * @throws SQLException SQL実行例外
     * @return int 更新件数
     */
    public int delete(ArrayList<Integer> sces) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SCH_EXDATA");
            sql.addSql(" where ");
            sql.addSql("   SCE_SID in(");
            if (sces.size() > 0) {
                sql.addSql("   ?");
                sql.addIntValue(sces.get(0));
            }
            for (int i = 1; i < sces.size(); i++) {
                sql.addSql("   ,?");
                sql.addIntValue(sces.get(i));
            }
            sql.addSql(")");

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
     * <p>Delete SCH_EXDATA
     * @param sid 拡張SID
     * @throws SQLException SQL実行例外
     * @return int 更新件数
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
            sql.addSql("   SCH_EXDATA");
            sql.addSql(" where ");
            sql.addSql("   SCE_SID=?");

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
     * <p>Create SCH_EXDATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created SchExdataModel
     * @throws SQLException SQL実行例外
     */
    private SchExdataModel __getSchExdataFromRs(ResultSet rs) throws SQLException {
        SchExdataModel bean = new SchExdataModel();
        bean.setSceSid(rs.getInt("SCE_SID"));
        bean.setSceKbn(rs.getInt("SCE_KBN"));
        bean.setSceDweek1(rs.getInt("SCE_DWEEK1"));
        bean.setSceDweek2(rs.getInt("SCE_DWEEK2"));
        bean.setSceDweek3(rs.getInt("SCE_DWEEK3"));
        bean.setSceDweek4(rs.getInt("SCE_DWEEK4"));
        bean.setSceDweek5(rs.getInt("SCE_DWEEK5"));
        bean.setSceDweek6(rs.getInt("SCE_DWEEK6"));
        bean.setSceDweek7(rs.getInt("SCE_DWEEK7"));
        bean.setSceDay(rs.getInt("SCE_DAY"));
        bean.setSceWeek(rs.getInt("SCE_WEEK"));
        bean.setSceMonthOfYearly(rs.getInt("SCE_MONTH_YEARLY"));
        bean.setSceDayOfYearly(rs.getInt("SCE_DAY_YEARLY"));
        bean.setSceTimeFr(UDate.getInstanceTimestamp(rs.getTimestamp("SCE_TIME_FR")));
        bean.setSceTimeTo(UDate.getInstanceTimestamp(rs.getTimestamp("SCE_TIME_TO")));
        bean.setSceTranKbn(rs.getInt("SCE_TRAN_KBN"));
        bean.setSceDateFr(UDate.getInstanceTimestamp(rs.getTimestamp("SCE_DATE_FR")));
        bean.setSceDateTo(UDate.getInstanceTimestamp(rs.getTimestamp("SCE_DATE_TO")));
        bean.setSceBgcolor(rs.getInt("SCE_BGCOLOR"));
        bean.setSceTitle(rs.getString("SCE_TITLE"));
        bean.setSceValue(rs.getString("SCE_VALUE"));
        bean.setSceBiko(rs.getString("SCE_BIKO"));
        bean.setScePublic(rs.getInt("SCE_PUBLIC"));
        bean.setSceEdit(rs.getInt("SCE_EDIT"));
        bean.setSceAuid(rs.getInt("SCE_AUID"));
        bean.setSceAdate(UDate.getInstanceTimestamp(rs.getTimestamp("SCE_ADATE")));
        bean.setSceEuid(rs.getInt("SCE_EUID"));
        bean.setSceEdate(UDate.getInstanceTimestamp(rs.getTimestamp("SCE_EDATE")));
        bean.setSceDaily(rs.getInt("SCE_DAILY"));
        return bean;
    }

    /**
     * <p>
     * Create SCH_EXDATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created SchExdataModel
     * @throws SQLException SQL実行例外
     */
    private ScheduleExSearchModel __getSchExdataPlusFromRs(ResultSet rs)
            throws SQLException {
        ScheduleExSearchModel bean = new ScheduleExSearchModel();
        // 選択スケジュール情報
        bean.setScdSid(rs.getInt("SCD_SID"));
        bean.setScdUsrSid(rs.getInt("SCD_USR_SID"));
        bean.setScdGrpSid(rs.getInt("SCD_GRP_SID"));
        bean.setScdUsrKbn(rs.getInt("SCD_USR_KBN"));
        // 拡張情報
        bean.setSceSid(rs.getInt("SCE_SID"));
        bean.setSceKbn(rs.getInt("SCE_KBN"));
        bean.setSceDweek1(rs.getInt("SCE_DWEEK1"));
        bean.setSceDweek2(rs.getInt("SCE_DWEEK2"));
        bean.setSceDweek3(rs.getInt("SCE_DWEEK3"));
        bean.setSceDweek4(rs.getInt("SCE_DWEEK4"));
        bean.setSceDweek5(rs.getInt("SCE_DWEEK5"));
        bean.setSceDweek6(rs.getInt("SCE_DWEEK6"));
        bean.setSceDweek7(rs.getInt("SCE_DWEEK7"));
        bean.setSceDay(rs.getInt("SCE_DAY"));
        bean.setSceWeek(rs.getInt("SCE_WEEK"));
        bean.setSceMonthOfYearly(rs.getInt("SCE_MONTH_YEARLY"));
        bean.setSceDayOfYearly(rs.getInt("SCE_DAY_YEARLY"));
        bean.setSceTimeFr(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCE_TIME_FR")));
        bean.setSceTimeTo(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCE_TIME_TO")));
        bean.setSceTranKbn(rs.getInt("SCE_TRAN_KBN"));
        bean.setSceDateFr(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCE_DATE_FR")));
        bean.setSceDateTo(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCE_DATE_TO")));
        bean.setSceBgcolor(rs.getInt("SCE_BGCOLOR"));
        bean.setSceTitle(rs.getString("SCE_TITLE"));
        bean.setSceValue(rs.getString("SCE_VALUE"));
        bean.setSceBiko(rs.getString("SCE_BIKO"));
        bean.setScePublic(rs.getInt("SCE_PUBLIC"));
        bean.setSceEdit(rs.getInt("SCE_EDIT"));
        bean.setSceDaily(rs.getInt("SCE_DAILY"));
        bean.setSceAuid(rs.getInt("SCE_AUID"));
        bean.setSceAdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCE_ADATE")));
        bean.setSceEuid(rs.getInt("SCE_EUID"));
        bean.setSceEdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCE_EDATE")));
        return bean;
    }
}
