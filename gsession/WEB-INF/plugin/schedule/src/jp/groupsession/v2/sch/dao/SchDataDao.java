package jp.groupsession.v2.sch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.sch.model.SchDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>SCH_DATA Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class SchDataDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(SchDataDao.class);

    /**
     * <p>Default Constructor
     */
    public SchDataDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public SchDataDao(Connection con) {
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
            sql.addSql("drop table SCH_DATA");

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
            sql.addSql(" create table SCH_DATA (");
            sql.addSql("   SCD_SID NUMBER(4,0) not null,");
            sql.addSql("   SCD_USR_SID NUMBER(4,0) not null,");
            sql.addSql("   SCD_GRP_SID NUMBER(4,0),");
            sql.addSql("   SCD_USR_KBN NUMBER(4,0),");
            sql.addSql("   SCD_FR_DATE varchar(8),");
            sql.addSql("   SCD_TO_DATE varchar(8),");
            sql.addSql("   SCD_DAILY NUMBER(4,0),");
            sql.addSql("   SCD_BGCOLOR NUMBER(4,0),");
            sql.addSql("   SCD_TITLE varchar(50),");
            sql.addSql("   SCD_VALUE varchar(1000),");
            sql.addSql("   SCD_BIKO varchar(1000),");
            sql.addSql("   SCD_PUBLIC NUMBER(4,0),");
            sql.addSql("   SCD_AUID NUMBER(4,0) not null,");
            sql.addSql("   SCD_ADATE varchar(8) not null,");
            sql.addSql("   SCD_EUID NUMBER(4,0) not null,");
            sql.addSql("   SCD_EDATE varchar(8) not null,");
            sql.addSql("   SCD_ATTEND_KBN NUMBER(4,0),");
            sql.addSql("   SCD_ATTEND_ANS NUMBER(4,0),");
            sql.addSql("   SCD_ATTEND_AU_KBN NUMBER(4,0),");
            sql.addSql("   primary key (SCD_SID)");
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
     * <p>Insert SCH_DATA Data Bindding JavaBean
     * @param bean SCH_DATA Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(SchDataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" SCH_DATA(");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_GRP_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_TO_DATE,");
            sql.addSql("   SCD_DAILY,");
            sql.addSql("   SCD_BGCOLOR,");
            sql.addSql("   SCD_TITLE,");
            sql.addSql("   SCD_VALUE,");
            sql.addSql("   SCD_BIKO,");
            sql.addSql("   SCD_PUBLIC,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_ADATE,");
            sql.addSql("   SCD_EUID,");
            sql.addSql("   SCD_EDATE,");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCD_EDIT,");
            sql.addSql("   SCD_ATTEND_KBN,");
            sql.addSql("   SCD_ATTEND_ANS,");
            sql.addSql("   SCD_ATTEND_AU_KBN");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getScdSid());
            sql.addIntValue(bean.getScdUsrSid());
            sql.addIntValue(bean.getScdGrpSid());
            sql.addIntValue(bean.getScdUsrKbn());
            sql.addDateValue(bean.getScdFrDate());
            sql.addDateValue(bean.getScdToDate());
            sql.addIntValue(bean.getScdDaily());
            sql.addIntValue(bean.getScdBgcolor());
            sql.addStrValue(bean.getScdTitle());
            sql.addStrValue(bean.getScdValue());
            sql.addStrValue(bean.getScdBiko());
            sql.addIntValue(bean.getScdPublic());
            sql.addIntValue(bean.getScdAuid());
            sql.addDateValue(bean.getScdAdate());
            sql.addIntValue(bean.getScdEuid());
            sql.addDateValue(bean.getScdEdate());
            sql.addIntValue(bean.getSceSid());
            sql.addIntValue(bean.getScdRsSid());
            sql.addIntValue(bean.getScdEdit());
            sql.addIntValue(bean.getScdAttendKbn());
            sql.addIntValue(bean.getScdAttendAns());
            sql.addIntValue(bean.getScdAttendAuKbn());
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
     * <p>Insert SCH_DATA Data Bindding JavaBean
     * @param beanList SCH_DATA DataList
     * @throws SQLException SQL実行例外
     */
    public void insert(List<SchDataModel> beanList) throws SQLException {

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
            sql.addSql(" SCH_DATA(");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_GRP_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_TO_DATE,");
            sql.addSql("   SCD_DAILY,");
            sql.addSql("   SCD_BGCOLOR,");
            sql.addSql("   SCD_TITLE,");
            sql.addSql("   SCD_VALUE,");
            sql.addSql("   SCD_BIKO,");
            sql.addSql("   SCD_PUBLIC,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_ADATE,");
            sql.addSql("   SCD_EUID,");
            sql.addSql("   SCD_EDATE,");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCD_EDIT,");
            sql.addSql("   SCD_ATTEND_KBN,");
            sql.addSql("   SCD_ATTEND_ANS,");
            sql.addSql("   SCD_ATTEND_AU_KBN");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            for (SchDataModel bean : beanList) {
                sql.addIntValue(bean.getScdSid());
                sql.addIntValue(bean.getScdUsrSid());
                sql.addIntValue(bean.getScdGrpSid());
                sql.addIntValue(bean.getScdUsrKbn());
                sql.addDateValue(bean.getScdFrDate());
                sql.addDateValue(bean.getScdToDate());
                sql.addIntValue(bean.getScdDaily());
                sql.addIntValue(bean.getScdBgcolor());
                sql.addStrValue(bean.getScdTitle());
                sql.addStrValue(bean.getScdValue());
                sql.addStrValue(bean.getScdBiko());
                sql.addIntValue(bean.getScdPublic());
                sql.addIntValue(bean.getScdAuid());
                sql.addDateValue(bean.getScdAdate());
                sql.addIntValue(bean.getScdEuid());
                sql.addDateValue(bean.getScdEdate());
                sql.addIntValue(bean.getSceSid());
                sql.addIntValue(bean.getScdRsSid());
                sql.addIntValue(bean.getScdEdit());
                sql.addIntValue(bean.getScdAttendKbn());
                sql.addIntValue(bean.getScdAttendAns());
                sql.addIntValue(bean.getScdAttendAuKbn());
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
     * <p>Update SCH_DATA Data Bindding JavaBean
     * @param bean SCH_DATA Data Bindding JavaBean
     * @return int 更新数
     * @throws SQLException SQL実行例外
     */
    public int update(SchDataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SCH_DATA");
            sql.addSql(" set ");
            sql.addSql("   SCD_USR_SID=?,");
            sql.addSql("   SCD_GRP_SID=?,");
            sql.addSql("   SCD_USR_KBN=?,");
            sql.addSql("   SCD_FR_DATE=?,");
            sql.addSql("   SCD_TO_DATE=?,");
            sql.addSql("   SCD_DAILY=?,");
            sql.addSql("   SCD_BGCOLOR=?,");
            sql.addSql("   SCD_TITLE=?,");
            sql.addSql("   SCD_VALUE=?,");
            sql.addSql("   SCD_BIKO=?,");
            sql.addSql("   SCD_PUBLIC=?,");
            sql.addSql("   SCD_AUID=?,");
            sql.addSql("   SCD_ADATE=?,");
            sql.addSql("   SCD_EUID=?,");
            sql.addSql("   SCD_EDATE=?,");
            sql.addSql("   SCE_SID=?,");
            sql.addSql("   SCD_RSSID=?,");
            sql.addSql("   SCD_EDIT=?,");
            sql.addSql("   SCD_ATTEND_KBN=?,");
            sql.addSql("   SCD_ATTEND_ANS=?,");
            sql.addSql("   SCD_ATTEND_AU_KBN=?");
            sql.addSql(" where ");
            sql.addSql("   SCD_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getScdUsrSid());
            sql.addIntValue(bean.getScdGrpSid());
            sql.addIntValue(bean.getScdUsrKbn());
            sql.addDateValue(bean.getScdFrDate());
            sql.addDateValue(bean.getScdToDate());
            sql.addIntValue(bean.getScdDaily());
            sql.addIntValue(bean.getScdBgcolor());
            sql.addStrValue(bean.getScdTitle());
            sql.addStrValue(bean.getScdValue());
            sql.addStrValue(bean.getScdBiko());
            sql.addIntValue(bean.getScdPublic());
            sql.addIntValue(bean.getScdAuid());
            sql.addDateValue(bean.getScdAdate());
            sql.addIntValue(bean.getScdEuid());
            sql.addDateValue(bean.getScdEdate());
            sql.addIntValue(bean.getSceSid());
            sql.addIntValue(bean.getScdRsSid());
            sql.addIntValue(bean.getScdEdit());
            sql.addIntValue(bean.getScdAttendKbn());
            sql.addIntValue(bean.getScdAttendAns());
            sql.addIntValue(bean.getScdAttendAuKbn());

            //where
            sql.addIntValue(bean.getScdSid());

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
     * <p>スケジュールを更新する
     * @param bean SCH_DATA Data Bindding JavaBean
     * @return int 更新数
     * @throws SQLException SQL実行例外
     */
    public int updateSchedule(SchDataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SCH_DATA");
            sql.addSql(" set ");
            sql.addSql("   SCD_GRP_SID=?,");
            sql.addSql("   SCD_FR_DATE=?,");
            sql.addSql("   SCD_TO_DATE=?,");
            sql.addSql("   SCD_DAILY=?,");
            sql.addSql("   SCD_BGCOLOR=?,");
            sql.addSql("   SCD_TITLE=?,");
            sql.addSql("   SCD_VALUE=?,");
            sql.addSql("   SCD_BIKO=?,");
            sql.addSql("   SCD_PUBLIC=?,");
            sql.addSql("   SCD_EUID=?,");
            sql.addSql("   SCD_EDATE=?,");
            sql.addSql("   SCE_SID=?,");
            sql.addSql("   SCD_RSSID=?,");
            sql.addSql("   SCD_EDIT=?,");
            sql.addSql("   SCD_ATTEND_KBN=?,");
            sql.addSql("   SCD_ATTEND_ANS=?,");
            sql.addSql("   SCD_ATTEND_AU_KBN=?");
            sql.addSql(" where ");
            sql.addSql("   SCD_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getScdGrpSid());
            sql.addDateValue(bean.getScdFrDate());
            sql.addDateValue(bean.getScdToDate());
            sql.addIntValue(bean.getScdDaily());
            sql.addIntValue(bean.getScdBgcolor());
            sql.addStrValue(bean.getScdTitle());
            sql.addStrValue(bean.getScdValue());
            sql.addStrValue(bean.getScdBiko());
            sql.addIntValue(bean.getScdPublic());
            sql.addIntValue(bean.getScdEuid());
            sql.addDateValue(bean.getScdEdate());
            sql.addIntValue(bean.getSceSid());
            sql.addIntValue(bean.getScdRsSid());
            sql.addIntValue(bean.getScdEdit());
            sql.addIntValue(bean.getScdAttendKbn());
            sql.addIntValue(bean.getScdAttendAns());
            sql.addIntValue(bean.getScdAttendAuKbn());

            //where
            sql.addIntValue(bean.getScdSid());

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
     * <p>スケジュールを更新する(出欠回答)
     * @param bean SCH_DATA Data Bindding JavaBean
     * @return int 更新数
     * @throws SQLException SQL実行例外
     */
    public int updateAnsKbn(SchDataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SCH_DATA");
            sql.addSql(" set ");
            sql.addSql("   SCD_EUID=?,");
            sql.addSql("   SCD_EDATE=?,");
            sql.addSql("   SCD_ATTEND_ANS=?");
            sql.addSql(" where ");
            sql.addSql("   SCD_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getScdEuid());
            sql.addDateValue(bean.getScdEdate());
            sql.addIntValue(bean.getScdAttendAns());

            //where
            sql.addIntValue(bean.getScdSid());

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
     * <br>[機  能] 指定したグループスケジュールSIDの出欠確認系のフィールドを全て初期値に戻す。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param scdGrpSid グループスケジュールSID
     * @return int 更新数
     * @throws SQLException SQL実行例外
     */
    public int updateAttendReset(int scdGrpSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SCH_DATA");
            sql.addSql(" set ");
            sql.addSql("   SCD_ATTEND_KBN=?,");
            sql.addSql("   SCD_ATTEND_ANS=?,");
            sql.addSql("   SCD_ATTEND_AU_KBN=?");
            sql.addSql(" where ");
            sql.addSql("   SCD_GRP_SID=?");



            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(GSConstSchedule.ATTEND_KBN_NO);
            sql.addIntValue(GSConstSchedule.ATTEND_ANS_NONE);
            sql.addIntValue(GSConstSchedule.ATTEND_REGIST_USER_NO);

            //where
            sql.addIntValue(scdGrpSid);

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
     * <p>スケジュールの施設予約SIDを更新する
     * @param oldSid 旧施設予約SID
     * @param newSid 新施設予約SID
     * @return int 更新数
     * @throws SQLException SQL実行例外
     */
    public int updateRsSid(int oldSid, int newSid) throws SQLException {
        if (oldSid <= 0) {
            return 0;
        }

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SCH_DATA");
            sql.addSql(" set ");
            sql.addSql("   SCD_RSSID=?");
            sql.addSql(" where ");
            sql.addSql("   SCD_RSSID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(newSid);
            //where
            sql.addIntValue(oldSid);

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
     * <p>スケジュールのスケジュール拡張SIDを更新する
     * @param oldSceSid 旧スケジュール拡張SID
     * @param newSceSid 新スケジュール拡張SID
     * @return int 更新数
     * @throws SQLException SQL実行例外
     */
    public int updateSceSid(int oldSceSid, int newSceSid) throws SQLException {
        if (oldSceSid <= 0) {
            return 0;
        }

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SCH_DATA");
            sql.addSql(" set ");
            sql.addSql("   SCE_SID=?");
            sql.addSql(" where ");
            sql.addSql("   SCE_SID=?");
            sql.addIntValue(newSceSid);
            sql.addIntValue(oldSceSid);

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
     * <p>スケジュールを更新する
     * @param scdSids スケジュールSID配列
     * @param bean SCH_DATA Data Bindding JavaBean
     * @return intr 更新数
     * @throws SQLException SQL実行例外
     */
    public int updateSchedules(ArrayList<Integer> scdSids, SchDataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   SCH_DATA");
            sql.addSql(" set ");
            sql.addSql("   SCD_GRP_SID=?,");
            sql.addSql("   SCD_FR_DATE=?,");
            sql.addSql("   SCD_TO_DATE=?,");
            sql.addSql("   SCD_DAILY=?,");
            sql.addSql("   SCD_BGCOLOR=?,");
            sql.addSql("   SCD_TITLE=?,");
            sql.addSql("   SCD_VALUE=?,");
            sql.addSql("   SCD_BIKO=?,");
            sql.addSql("   SCD_PUBLIC=?,");
            sql.addSql("   SCD_EUID=?,");
            sql.addSql("   SCD_EDATE=?,");
            sql.addSql("   SCE_SID=?,");
            sql.addSql("   SCD_RSSID=?,");
            sql.addSql("   SCD_EDIT=?");
            sql.addIntValue(bean.getScdGrpSid());
            sql.addDateValue(bean.getScdFrDate());
            sql.addDateValue(bean.getScdToDate());
            sql.addIntValue(bean.getScdDaily());
            sql.addIntValue(bean.getScdBgcolor());
            sql.addStrValue(bean.getScdTitle());
            sql.addStrValue(bean.getScdValue());
            sql.addStrValue(bean.getScdBiko());
            sql.addIntValue(bean.getScdPublic());
            sql.addIntValue(bean.getScdEuid());
            sql.addDateValue(bean.getScdEdate());
            sql.addIntValue(bean.getSceSid());
            sql.addIntValue(bean.getScdRsSid());
            sql.addIntValue(bean.getScdEdit());
            sql.addSql(" where ");
            sql.addSql("   SCD_SID in(");
            if (scdSids.size() > 0) {
                sql.addSql("   ?");
                sql.addIntValue(scdSids.get(0));
            }
            for (int i = 1; i < scdSids.size(); i++) {
                sql.addSql("   ,?");
                sql.addIntValue(scdSids.get(i));
            }
            sql.addSql("   )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] 指定したスケジュールの出欠の未回答者の人数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @throws SQLException SQL実行時例外
     * @return 未回答人数
     */
    public int countAnsNone(int scdSid) throws SQLException {
        int count = 0;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(*) CNT");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA SCD");
            sql.addSql(" where ");
            sql.addSql("   SCD.SCD_GRP_SID = ");
            sql.addSql("     (");
            sql.addSql("       select");
            sql.addSql("         SSCD.SCD_GRP_SID");
            sql.addSql("       from");
            sql.addSql("         SCH_DATA SSCD");
            sql.addSql("       where");
            sql.addSql("         SSCD.SCD_SID = ?");
            sql.addSql("       and");
            sql.addSql("         SSCD.SCD_GRP_SID <> -1");
            sql.addSql("     )");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_ATTEND_KBN = ?");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_ATTEND_ANS = ?");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_ATTEND_AU_KBN = ?");
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(scdSid);
            sql.addIntValue(GSConstSchedule.ATTEND_KBN_YES);
            sql.addIntValue(GSConstSchedule.ATTEND_ANS_NONE);
            sql.addIntValue(GSConstSchedule.ATTEND_REGIST_USER_NO);

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
     * <p>Select SCH_DATA All Data
     * @return List in SCH_DATAModel
     * @throws SQLException SQL実行例外
     */
    public List<SchDataModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SchDataModel> ret  = new ArrayList<SchDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_GRP_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_TO_DATE,");
            sql.addSql("   SCD_DAILY,");
            sql.addSql("   SCD_BGCOLOR,");
            sql.addSql("   SCD_TITLE,");
            sql.addSql("   SCD_VALUE,");
            sql.addSql("   SCD_BIKO,");
            sql.addSql("   SCD_PUBLIC,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_ADATE,");
            sql.addSql("   SCD_EUID,");
            sql.addSql("   SCD_EDATE,");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCD_EDIT,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCD_ATTEND_KBN,");
            sql.addSql("   SCD_ATTEND_ANS,");
            sql.addSql("   SCD_ATTEND_AU_KBN");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getSchDataFromRs(rs));
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
     * <p>Select SCH_DATA All Data
     * @param usrSid ユーザSID
     * @return List in SCH_DATAModel
     * @throws SQLException SQL実行例外
     */
    public List<SchDataModel> select(int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SchDataModel> ret  = new ArrayList<SchDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_GRP_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_TO_DATE,");
            sql.addSql("   SCD_DAILY,");
            sql.addSql("   SCD_BGCOLOR,");
            sql.addSql("   SCD_TITLE,");
            sql.addSql("   SCD_VALUE,");
            sql.addSql("   SCD_BIKO,");
            sql.addSql("   SCD_PUBLIC,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_ADATE,");
            sql.addSql("   SCD_EUID,");
            sql.addSql("   SCD_EDATE,");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCD_EDIT,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCD_ATTEND_KBN,");
            sql.addSql("   SCD_ATTEND_ANS,");
            sql.addSql("   SCD_ATTEND_AU_KBN");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_USR_SID=?");
            sql.addIntValue(usrSid);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getSchDataFromRs(rs));
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
     * <br>[機  能] 指定したスケジュールに結びつく同時スケジュールより、
     *                     出欠確認回答データを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @return Map ユーザSID：出欠回答区分
     * @throws SQLException SQL実行例外
     */
    public Map<Integer, Integer> selectAttendData(int scdSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Map<Integer, Integer> ret  = new HashMap<Integer, Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD.SCD_USR_SID,");
            sql.addSql("   SCD.SCD_ATTEND_ANS");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA SCD");
            sql.addSql(" where ");
            sql.addSql("   SCD.SCD_GRP_SID = ");
            sql.addSql("     (");
            sql.addSql("       select");
            sql.addSql("         SSCD.SCD_GRP_SID");
            sql.addSql("       from");
            sql.addSql("         SCH_DATA SSCD");
            sql.addSql("       where");
            sql.addSql("         SSCD.SCD_SID = ?");
            sql.addSql("       and");
            sql.addSql("         SSCD.SCD_GRP_SID <> -1");
            sql.addSql("     )");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_USR_KBN = ?");

            sql.addIntValue(scdSid);
            sql.addIntValue(GSConstSchedule.USER_KBN_USER);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.put(rs.getInt("SCD_USR_SID"), rs.getInt("SCD_ATTEND_ANS"));
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
     * <br>[機  能] 指定したスケジュールの出欠確認依頼者のユーザSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param schSid スケジュールSID
     * @throws SQLException SQL実行時例外
     * @return ユーザSID
     */
    public int getAttendRegistUser(int schSid) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = -1;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD.SCD_USR_SID");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA SCD");
            sql.addSql(" where ");
            sql.addSql("   SCD.SCD_GRP_SID = ");
            sql.addSql("     (");
            sql.addSql("       select");
            sql.addSql("         SSCD.SCD_GRP_SID");
            sql.addSql("       from");
            sql.addSql("         SCH_DATA SSCD");
            sql.addSql("       where");
            sql.addSql("         SSCD.SCD_SID = ?");
            sql.addSql("       and");
            sql.addSql("         SSCD.SCD_GRP_SID <> -1");
            sql.addSql("     )");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_USR_KBN = ?");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_ATTEND_KBN = ?");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_ATTEND_AU_KBN = ?");

            sql.addIntValue(schSid);
            sql.addIntValue(GSConstSchedule.USER_KBN_USER);
            sql.addIntValue(GSConstSchedule.ATTEND_KBN_YES);
            sql.addIntValue(GSConstSchedule.ATTEND_REGIST_USER_YES);



            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("SCD_USR_SID");
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
     * <br>[機  能] 指定したスケジュールSIDより紐付いている
     *                    出欠確認依頼者のスケジュールを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param schSid スケジュールSID
     * @throws SQLException SQL実行時例外
     * @return スケジュールSID
     */
    public SchDataModel getAttendRegistSch(int schSid) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        SchDataModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD.SCD_SID,");
            sql.addSql("   SCD.SCD_USR_SID,");
            sql.addSql("   SCD.SCD_GRP_SID,");
            sql.addSql("   SCD.SCD_USR_KBN,");
            sql.addSql("   SCD.SCD_FR_DATE,");
            sql.addSql("   SCD.SCD_TO_DATE,");
            sql.addSql("   SCD.SCD_DAILY,");
            sql.addSql("   SCD.SCD_BGCOLOR,");
            sql.addSql("   SCD.SCD_TITLE,");
            sql.addSql("   SCD.SCD_VALUE,");
            sql.addSql("   SCD.SCD_BIKO,");
            sql.addSql("   SCD.SCD_PUBLIC,");
            sql.addSql("   SCD.SCD_AUID,");
            sql.addSql("   SCD.SCD_ADATE,");
            sql.addSql("   SCD.SCD_EUID,");
            sql.addSql("   SCD.SCD_EDATE,");
            sql.addSql("   SCD.SCE_SID,");
            sql.addSql("   SCD.SCD_EDIT,");
            sql.addSql("   SCD.SCD_RSSID,");
            sql.addSql("   SCD.SCD_ATTEND_KBN,");
            sql.addSql("   SCD.SCD_ATTEND_ANS,");
            sql.addSql("   SCD.SCD_ATTEND_AU_KBN");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA SCD");
            sql.addSql(" where ");
            sql.addSql("   SCD.SCD_GRP_SID = ");
            sql.addSql("     (");
            sql.addSql("       select");
            sql.addSql("         SSCD.SCD_GRP_SID");
            sql.addSql("       from");
            sql.addSql("         SCH_DATA SSCD");
            sql.addSql("       where");
            sql.addSql("         SSCD.SCD_SID = ?");
            sql.addSql("       and");
            sql.addSql("         SSCD.SCD_GRP_SID <> -1");
            sql.addSql("     )");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_USR_KBN = ?");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_ATTEND_KBN = ?");
            sql.addSql(" and ");
            sql.addSql("   SCD.SCD_ATTEND_AU_KBN = ?");

            sql.addIntValue(schSid);
            sql.addIntValue(GSConstSchedule.USER_KBN_USER);
            sql.addIntValue(GSConstSchedule.ATTEND_KBN_YES);
            sql.addIntValue(GSConstSchedule.ATTEND_REGIST_USER_YES);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getSchDataFromRs(rs);
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
     * <p>指定日付以前のスケジュールSIDリストを取得する。
     * @param todate 日付
     * @return List in SCH_DATAModel
     * @throws SQLException SQL実行例外
     */
    public List<Integer> getScdSidList(UDate todate) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret  = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD_SID");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_TO_DATE <= ?");
            sql.addDateValue(todate);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt("SCD_SID"));
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
     * <p>Select SCH_DATA
     * @param bean SCH_DATA Model
     * @return SCH_DATAModel
     * @throws SQLException SQL実行例外
     */
    public SchDataModel select(SchDataModel bean) throws SQLException {
        return getSchData(bean.getScdSid());
    }

    /**
     * <p>Select SCH_DATA
     * @param scdSid SCD_SID
     * @return SCH_DATAModel
     * @throws SQLException SQL実行例外
     */
    public SchDataModel getSchData(int scdSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        SchDataModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_GRP_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_TO_DATE,");
            sql.addSql("   SCD_DAILY,");
            sql.addSql("   SCD_BGCOLOR,");
            sql.addSql("   SCD_TITLE,");
            sql.addSql("   SCD_VALUE,");
            sql.addSql("   SCD_BIKO,");
            sql.addSql("   SCD_PUBLIC,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_ADATE,");
            sql.addSql("   SCD_EUID,");
            sql.addSql("   SCD_EDATE,");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCD_EDIT,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCD_ATTEND_KBN,");
            sql.addSql("   SCD_ATTEND_ANS,");
            sql.addSql("   SCD_ATTEND_AU_KBN");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_SID=" + scdSid);

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getSchDataFromRs(rs);
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
     * <p>編集権限確認用のデータを取得する
     * @param scdSid SCD_SID
     * @return SCH_DATAModel
     * @throws SQLException SQL実行例外
     */
    public SchDataModel getEditCheckData(int scdSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        SchDataModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_EDIT");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_SID=" + scdSid);

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = new SchDataModel();
                ret.setScdUsrSid(rs.getInt("SCD_USR_SID"));
                ret.setScdUsrKbn(rs.getInt("SCD_USR_KBN"));
                ret.setScdAuid(rs.getInt("SCD_AUID"));
                ret.setScdEdit(rs.getInt("SCD_EDIT"));
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
     * <p>ユーザSIDの一覧を取得する
     * @return ArrayList in UserSid
     * @throws SQLException SQL実行例外
     */
    public ArrayList<Integer> getUserSids() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret  = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD_USR_SID");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA");
            sql.addSql(" group by ");
            sql.addSql("   SCD_USR_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(new Integer(rs.getInt("SCD_USR_SID")));
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
     * <p> 指定した期間のスケジュールを取得する
     * <p> 非公開スケジュールを除く
     * <p> 指定したスケジュールSIDを除く
     * <p> 指定した同時登録グループSIDを除く
     * @param usrSidList ユーザリスト
     * @param schSid スケジュールSID 新規登録の場合:0
     * @param frDate 条件 予約開始
     * @param toDate 条件 予約終了
     * @param schGrpSid 同時登録グループSID
     * @param copyFlg 複写フラグ
     * @return SCH_DATAModel
     * @throws SQLException SQL実行例外
     */
    public List<SchDataModel> getSchData(List<Integer> usrSidList,
            int schSid,
            UDate frDate,
            UDate toDate,
            int schGrpSid,
            String copyFlg) throws SQLException {
        return getSchData(usrSidList,
                schSid,
                frDate,
                toDate,
                schGrpSid,
                copyFlg,
                GSConstSchedule.USER_KBN_USER);
    }
    /**
     * <p> 指定した期間のスケジュールを取得する
     * <p> 非公開スケジュールを除く
     * <p> 指定したスケジュールSIDを除く
     * <p> 指定した同時登録グループSIDを除く
     * @param usrSidList ユーザリスト
     * @param schSid スケジュールSID 新規登録の場合:0
     * @param frDate 条件 予約開始
     * @param toDate 条件 予約終了
     * @param schGrpSid 同時登録グループSID
     * @param copyFlg 複写フラグ
     * @param scdUsrKbn ユーザ区分(ユーザ or グループ)
     * @return SCH_DATAModel
     * @throws SQLException SQL実行例外
     */
    public List<SchDataModel> getSchData(List<Integer> usrSidList,
            int schSid,
            UDate frDate,
            UDate toDate,
            int schGrpSid,
            String copyFlg,
            int scdUsrKbn) throws SQLException {

        if (usrSidList == null || usrSidList.size() < 1
        || scdUsrKbn == GSConstSchedule.USER_KBN_GROUP) {
            return null;
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<SchDataModel> ret = new ArrayList<SchDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SCH_DATA.SCD_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_SID,");
            sql.addSql("   SCH_DATA.SCD_GRP_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_KBN,");
            sql.addSql("   SCH_DATA.SCD_FR_DATE,");
            sql.addSql("   SCH_DATA.SCD_TO_DATE,");
            sql.addSql("   SCH_DATA.SCD_DAILY,");
            sql.addSql("   SCH_DATA.SCD_BGCOLOR,");
            sql.addSql("   SCH_DATA.SCD_TITLE,");
            sql.addSql("   SCH_DATA.SCD_VALUE,");
            sql.addSql("   SCH_DATA.SCD_BIKO,");
            sql.addSql("   SCH_DATA.SCD_PUBLIC,");
            sql.addSql("   SCH_DATA.SCD_AUID,");
            sql.addSql("   SCH_DATA.SCD_ADATE,");
            sql.addSql("   SCH_DATA.SCD_EUID,");
            sql.addSql("   SCH_DATA.SCD_EDATE,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_KBN,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_ANS,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_AU_KBN,");
            sql.addSql("   CMN_USRM_INF.USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where");
            sql.addSql("   (");
            sql.addSql("     (");
            sql.addSql("      SCD_FR_DATE >= ?");
            sql.addSql("      and");
            sql.addSql("      SCD_FR_DATE < ?");
            sql.addSql("     )");
            sql.addSql("     or");
            sql.addSql("     (");
            sql.addSql("      SCD_TO_DATE > ?");
            sql.addSql("      and");
            sql.addSql("      SCD_TO_DATE <= ?");
            sql.addSql("     )");
            sql.addSql("     or");
            sql.addSql("     (");
            sql.addSql("      ? >= SCD_FR_DATE");
            sql.addSql("      and");
            sql.addSql("      ? < SCD_TO_DATE");
            sql.addSql("     )");
            sql.addSql("     or");
            sql.addSql("     (");
            sql.addSql("      ? > SCD_FR_DATE");
            sql.addSql("      and");
            sql.addSql("      ? <= SCD_TO_DATE");
            sql.addSql("     )");
            sql.addSql("   )");

            sql.addDateValue(frDate);
            sql.addDateValue(toDate);
            sql.addDateValue(frDate);
            sql.addDateValue(toDate);
            sql.addDateValue(frDate);
            sql.addDateValue(frDate);
            sql.addDateValue(toDate);
            sql.addDateValue(toDate);

            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_SID=CMN_USRM_INF.USR_SID");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_KBN=?");
            sql.addIntValue(GSConstSchedule.USER_KBN_USER);
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_PUBLIC<>?");
            sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_DAILY<>?");
            sql.addIntValue(GSConstSchedule.TIME_NOT_EXIST);
            if (schGrpSid > 0) {
                sql.addSql(" and");
                sql.addSql("   SCH_DATA.SCD_GRP_SID<>?");
                sql.addIntValue(schGrpSid);
            }

            sql.addSql(" and");
            sql.addSql("    (");
            int i = 0;
            for (Integer usrSid : usrSidList) {
                if (i > 0) {
                    sql.addSql(" or");
                }
                sql.addSql("   SCH_DATA.SCD_USR_SID=?");
                sql.addIntValue(usrSid);
                i++;
            }
            sql.addSql("    )");

            //編集　複写以外
            if (schSid > 0 && copyFlg.equals(GSConstSchedule.NOT_COPY_FLG)) {
                sql.addSql(" and");
                sql.addSql("   SCH_DATA.SCD_SID<>?");
                sql.addIntValue(schSid);
            }

            sql.addSql(" order by");
            sql.addSql("   SCH_DATA.SCD_FR_DATE");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getSchDataFromRsPlusName(rs));
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
     * <p> 指定した期間のスケジュールを取得する。
     * <p> 非公開スケジュールを除く
     * <p> 指定した拡張IDを除く
     * @param usrSidList ユーザリスト
     * @param sceSid 拡張ID 新規登録の場合:0
     * @param frDate 条件 予約開始
     * @param toDate 条件 予約終了
     * @param scdUsrKbn ユーザ区分(ユーザ or グループ)
     * @return SCH_DATAModel
     * @throws SQLException SQL実行例外
     */
    public List<SchDataModel> getSchDataEx(List<Integer> usrSidList,
            int sceSid,
            UDate frDate,
            UDate toDate,
            int scdUsrKbn) throws SQLException {

        if (usrSidList == null || usrSidList.size() < 1
        || scdUsrKbn == GSConstSchedule.USER_KBN_GROUP) {
            return null;
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<SchDataModel> ret = new ArrayList<SchDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SCH_DATA.SCD_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_SID,");
            sql.addSql("   SCH_DATA.SCD_GRP_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_KBN,");
            sql.addSql("   SCH_DATA.SCD_FR_DATE,");
            sql.addSql("   SCH_DATA.SCD_TO_DATE,");
            sql.addSql("   SCH_DATA.SCD_DAILY,");
            sql.addSql("   SCH_DATA.SCD_BGCOLOR,");
            sql.addSql("   SCH_DATA.SCD_TITLE,");
            sql.addSql("   SCH_DATA.SCD_VALUE,");
            sql.addSql("   SCH_DATA.SCD_BIKO,");
            sql.addSql("   SCH_DATA.SCD_PUBLIC,");
            sql.addSql("   SCH_DATA.SCD_AUID,");
            sql.addSql("   SCH_DATA.SCD_ADATE,");
            sql.addSql("   SCH_DATA.SCD_EUID,");
            sql.addSql("   SCH_DATA.SCD_EDATE,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_KBN,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_ANS,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_AU_KBN,");
            sql.addSql("   CMN_USRM_INF.USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where");
            sql.addSql("   (");
            sql.addSql("     (");
            sql.addSql("      SCD_FR_DATE >= ?");
            sql.addSql("      and");
            sql.addSql("      SCD_FR_DATE < ?");
            sql.addSql("     )");
            sql.addSql("     or");
            sql.addSql("     (");
            sql.addSql("      SCD_TO_DATE > ?");
            sql.addSql("      and");
            sql.addSql("      SCD_TO_DATE <= ?");
            sql.addSql("     )");
            sql.addSql("     or");
            sql.addSql("     (");
            sql.addSql("      ? >= SCD_FR_DATE");
            sql.addSql("      and");
            sql.addSql("      ? < SCD_TO_DATE");
            sql.addSql("     )");
            sql.addSql("     or");
            sql.addSql("     (");
            sql.addSql("      ? > SCD_FR_DATE");
            sql.addSql("      and");
            sql.addSql("      ? <= SCD_TO_DATE");
            sql.addSql("     )");
            sql.addSql("   )");

            sql.addDateValue(frDate);
            sql.addDateValue(toDate);
            sql.addDateValue(frDate);
            sql.addDateValue(toDate);
            sql.addDateValue(frDate);
            sql.addDateValue(frDate);
            sql.addDateValue(toDate);
            sql.addDateValue(toDate);

            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_SID=CMN_USRM_INF.USR_SID");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_KBN=?");
            sql.addIntValue(GSConstSchedule.USER_KBN_USER);
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_PUBLIC<>?");
            sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);

            sql.addSql(" and");
            sql.addSql("    (");
            int i = 0;
            for (Integer usrSid : usrSidList) {
                if (i > 0) {
                    sql.addSql(" or");
                }
                sql.addSql("   SCH_DATA.SCD_USR_SID=?");
                sql.addIntValue(usrSid);
                i++;
            }
            sql.addSql("    )");

            //編集の場合
            if (sceSid > 0) {
                sql.addSql(" and");
                sql.addSql("   SCH_DATA.SCE_SID<>?");
                sql.addIntValue(sceSid);
            }

            sql.addSql(" order by");
            sql.addSql("   SCH_DATA.SCD_FR_DATE");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getSchDataFromRsPlusName(rs));
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
     * <br>[機  能] 指定したスケジュールグループSIDの出欠確認回答一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param scdGrpSid スケジュールグループSID（リレーション用）
     * @throws SQLException SQL実行例外
     * @return 出欠確認回答一覧
     */
    public ArrayList<SchDataModel> selectAttendAnsSchGrp(int scdGrpSid) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SchDataModel> ret  = new ArrayList<SchDataModel>();
        SchDataModel mdl = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCH_DATA.SCD_EDATE as SCD_EDATE,");
            sql.addSql("   SCH_DATA.SCD_USR_SID as SCD_USR_SID,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_ANS as SCD_ATTEND_ANS,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as USI_MEI");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where ");
            sql.addSql("   SCH_DATA.SCD_GRP_SID = ?");
            sql.addSql(" and ");
            sql.addSql("   SCH_DATA.SCD_USR_KBN = ?");
            sql.addSql(" and ");
            sql.addSql("   SCH_DATA.SCD_ATTEND_KBN = ?");
//            sql.addSql(" and ");
//            sql.addSql("   SCH_DATA.SCD_ATTEND_AU_KBN = ?");
            sql.addSql(" and ");
            sql.addSql("   SCH_DATA.SCD_USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql(" order by ");
            sql.addSql("   case SCH_DATA.SCD_ATTEND_ANS");
            sql.addSql("   when 0 then 1");
            sql.addSql("   end asc,");
            sql.addSql("   SCH_DATA.SCD_EDATE desc");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(scdGrpSid);
            sql.addIntValue(GSConstSchedule.USER_KBN_USER);
            sql.addIntValue(GSConstSchedule.ATTEND_KBN_YES);
//            sql.addIntValue(GSConstSchedule.ATTEND_REGIST_USER_NO);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                mdl = new SchDataModel();
                mdl.setScdEdate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_EDATE")));
                mdl.setScdUsrSid(rs.getInt("SCD_USR_SID"));
                mdl.setScdAttendAns(rs.getInt("SCD_ATTEND_ANS"));
                mdl.setScdUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                ret.add(mdl);
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
     * <br>[機  能] 指定したスケジュールグループSIDの出欠確認回答一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param schSid スケジュールSID
     * @throws SQLException SQL実行例外
     * @return 出欠確認回答一覧
     */
    public ArrayList<SchDataModel> selectAttendAnsSch(int schSid) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SchDataModel> ret  = new ArrayList<SchDataModel>();
        SchDataModel mdl = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCH_DATA.SCD_EDATE as SCD_EDATE,");
            sql.addSql("   SCH_DATA.SCD_USR_SID as SCD_USR_SID,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_ANS as SCD_ATTEND_ANS,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as USI_MEI");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where ");
            sql.addSql("   SCH_DATA.SCD_GRP_SID = ");
            sql.addSql("     (");
            sql.addSql("       select");
            sql.addSql("         SSCD.SCD_GRP_SID");
            sql.addSql("       from");
            sql.addSql("         SCH_DATA SSCD");
            sql.addSql("       where");
            sql.addSql("         SSCD.SCD_SID = ?");
            sql.addSql("       and");
            sql.addSql("         SSCD.SCD_GRP_SID <> -1");
            sql.addSql("     )");
            sql.addSql(" and ");
            sql.addSql("   SCH_DATA.SCD_USR_KBN = ?");
            sql.addSql(" and ");
            sql.addSql("   SCH_DATA.SCD_ATTEND_KBN = ?");
//            sql.addSql(" and ");
//            sql.addSql("   SCH_DATA.SCD_ATTEND_AU_KBN = ?");
            sql.addSql(" and ");
            sql.addSql("   SCH_DATA.SCD_USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql(" order by ");
            sql.addSql("   case SCH_DATA.SCD_ATTEND_ANS");
            sql.addSql("   when 0 then 1");
            sql.addSql("   end asc,");
            sql.addSql("   SCH_DATA.SCD_EDATE desc");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(schSid);
            sql.addIntValue(GSConstSchedule.USER_KBN_USER);
            sql.addIntValue(GSConstSchedule.ATTEND_KBN_YES);
//            sql.addIntValue(GSConstSchedule.ATTEND_REGIST_USER_NO);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                mdl = new SchDataModel();
                mdl.setScdEdate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_EDATE")));
                mdl.setScdUsrSid(rs.getInt("SCD_USR_SID"));
                mdl.setScdAttendAns(rs.getInt("SCD_ATTEND_ANS"));
                mdl.setScdUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                ret.add(mdl);
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
     * <br>[機  能] 出欠確認依頼者のスケジュールが削除されているかチェックする
     * <br>[解  説]
     * <br>[備  考]
     * @param scdSid スケジュールSID
     * @return true : 削除済み
     * @throws SQLException SQL実行時例外
     */
    public boolean isCheckAttendAuSchDelete(int scdSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int cnt = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("   select");
            sql.addSql("     count(SCD_SID) as CNT");
            sql.addSql("   from");
            sql.addSql("     SCH_DATA");
            sql.addSql("   where");
            sql.addSql("     SCD_RSSID = (");
            sql.addSql("        select");
            sql.addSql("          SCD_RSSID");
            sql.addSql("        from");
            sql.addSql("          SCH_DATA");
            sql.addSql("        where");
            sql.addSql("          SCD_SID = ?)");
            sql.addSql("   and");
            sql.addSql("     SCD_ATTEND_KBN = ?");
            sql.addSql("   and");
            sql.addSql("     SCD_ATTEND_AU_KBN = ?");

            sql.addIntValue(scdSid);
            sql.addIntValue(GSConstSchedule.ATTEND_KBN_YES);
            sql.addIntValue(GSConstSchedule.ATTEND_REGIST_USER_YES);

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cnt = rs.getInt("CNT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return cnt <= 0;
    }


    /**
     * <p>Delete SCH_DATA
     * @param bean SCH_DATA Model
     * @return int 削除数
     * @throws SQLException SQL実行例外
     */
    public int delete(SchDataModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getScdSid());

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
     * <p>スケジュールSIDを指定しスケジュールを削除します
     * @param scdSid スケジュールSID
     * @return int 削除数
     * @throws SQLException SQL実行例外
     */
    public int delete(int scdSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(scdSid);

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
     * <p>スケジュールSIDを指定しスケジュールを削除します
     * @param scds スケジュールSIDリスト
     * @return int 削除数
     * @throws SQLException SQL実行例外
     */
    public int delete(ArrayList<Integer> scds) throws SQLException {

        int count = 0;
        if (scds.size() == 0) {
            return count;
        }
        PreparedStatement pstmt = null;

        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_SID in(");
            if (scds.size() > 0) {
                sql.addSql("   ?");
                sql.addIntValue(scds.get(0));
            }
            for (int i = 1; i < scds.size(); i++) {
                sql.addSql("   ,?");
                sql.addIntValue(scds.get(i));
            }
            sql.addSql("   )");
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <p>Delete SCH_DATA
     * @param id ユーザ or グループSID
     * @param kbn ユーザ区分
     * @return int 削除数
     * @throws SQLException SQL実行例外
     */
    public int delete(int id, int kbn) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_USR_SID=?");
            sql.addSql(" and ");
            sql.addSql("   SCD_USR_KBN=?");
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(id);
            sql.addIntValue(kbn);

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
     * <p>Create SCH_DATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created SchDataModel
     * @throws SQLException SQL実行例外
     */
    private SchDataModel __getSchDataFromRs(ResultSet rs) throws SQLException {
        SchDataModel bean = new SchDataModel();
        bean.setScdSid(rs.getInt("SCD_SID"));
        bean.setScdUsrSid(rs.getInt("SCD_USR_SID"));
        bean.setScdGrpSid(rs.getInt("SCD_GRP_SID"));
        bean.setScdUsrKbn(rs.getInt("SCD_USR_KBN"));
        bean.setScdFrDate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_FR_DATE")));
        bean.setScdToDate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_TO_DATE")));
        bean.setScdDaily(rs.getInt("SCD_DAILY"));
        bean.setScdBgcolor(rs.getInt("SCD_BGCOLOR"));
        bean.setScdTitle(rs.getString("SCD_TITLE"));
        bean.setScdValue(rs.getString("SCD_VALUE"));
        bean.setScdBiko(rs.getString("SCD_BIKO"));
        bean.setScdPublic(rs.getInt("SCD_PUBLIC"));
        bean.setScdAuid(rs.getInt("SCD_AUID"));
        bean.setScdAdate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_ADATE")));
        bean.setScdEuid(rs.getInt("SCD_EUID"));
        bean.setScdEdate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_EDATE")));
        bean.setSceSid(rs.getInt("SCE_SID"));
        bean.setScdEdit(rs.getInt("SCD_EDIT"));
        bean.setScdRsSid(rs.getInt("SCD_RSSID"));
        bean.setScdAttendKbn(rs.getInt("SCD_ATTEND_KBN"));
        bean.setScdAttendAns(rs.getInt("SCD_ATTEND_ANS"));
        bean.setScdAttendAuKbn(rs.getInt("SCD_ATTEND_AU_KBN"));
        return bean;
    }

    /**
     * <p>Create SCH_DATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created SchDataModel
     * @throws SQLException SQL実行例外
     */
    private SchDataModel __getSchDataFromRsPlusName(ResultSet rs) throws SQLException {
        SchDataModel bean = new SchDataModel();
        bean.setScdSid(rs.getInt("SCD_SID"));
        bean.setScdUsrSid(rs.getInt("SCD_USR_SID"));
        bean.setScdGrpSid(rs.getInt("SCD_GRP_SID"));
        bean.setScdUsrKbn(rs.getInt("SCD_USR_KBN"));
        bean.setScdFrDate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_FR_DATE")));
        bean.setScdToDate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_TO_DATE")));
        bean.setScdDaily(rs.getInt("SCD_DAILY"));
        bean.setScdBgcolor(rs.getInt("SCD_BGCOLOR"));
        bean.setScdTitle(rs.getString("SCD_TITLE"));
        bean.setScdValue(rs.getString("SCD_VALUE"));
        bean.setScdBiko(rs.getString("SCD_BIKO"));
        bean.setScdPublic(rs.getInt("SCD_PUBLIC"));
        bean.setScdAuid(rs.getInt("SCD_AUID"));
        bean.setScdAdate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_ADATE")));
        bean.setScdEuid(rs.getInt("SCD_EUID"));
        bean.setScdEdate(UDate.getInstanceTimestamp(rs.getTimestamp("SCD_EDATE")));
        bean.setScdAttendKbn(rs.getInt("SCD_ATTEND_KBN"));
        bean.setScdAttendAns(rs.getInt("SCD_ATTEND_ANS"));
        bean.setScdAttendAuKbn(rs.getInt("SCD_ATTEND_AU_KBN"));

        //userName
        bean.setScdUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));

        return bean;
    }

}
