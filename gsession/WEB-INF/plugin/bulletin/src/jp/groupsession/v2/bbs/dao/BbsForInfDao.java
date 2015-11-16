package jp.groupsession.v2.bbs.dao;


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
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.model.BbsForInfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>BBS_FOR_INF Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class BbsForInfDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(BbsForInfDao.class);

    /**
     * <p>Default Constructor
     */
    public BbsForInfDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public BbsForInfDao(Connection con) {
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
            sql.addSql("drop table BBS_FOR_INF");

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
            sql.addSql(" create table BBS_FOR_INF (");
            sql.addSql("   BFI_SID NUMBER(10,0) not null,");
            sql.addSql("   BFI_NAME varchar(150) not null,");
            sql.addSql("   BFI_CMT varchar(3000) not null,");
            sql.addSql("   BFI_SORT NUMBER(10,0) not null,");
            sql.addSql("   BFI_REPLY NUMBER(10,0) not null,");
            sql.addSql("   BFI_READ NUMBER(10,0) not null,");
            sql.addSql("   BIN_SID Date not null,");
            sql.addSql("   BFI_MREAD NUMBER(10,0) not null,");
            sql.addSql("   BFI_AUID NUMBER(10,0) not null,");
            sql.addSql("   BFI_ADATE varchar(23) not null,");
            sql.addSql("   BFI_EUID NUMBER(10,0) not null,");
            sql.addSql("   BFI_EDATE varchar(23) not null,");
            sql.addSql("   BFI_DISK NUMBER(10,0) not null,");
            sql.addSql("   BFI_DISK_SIZE NUMBER(10,0),");
            sql.addSql("   BFI_WARN_DISK NUMBER(10,0) not null,");
            sql.addSql("   BFI_WARN_DISK_TH NUMBER(10,0),");
            sql.addSql("   BFI_LIMIT NUMBER(10,0) not null,");
            sql.addSql("   BFI_LIMIT_DATE NUMBER(10,0),");
            sql.addSql("   BFI_KEEP NUMBER(10,0) not null,");
            sql.addSql("   BFI_KEEP_DATE_Y NUMBER(10,0),");
            sql.addSql("   BFI_KEEP_DATE_M NUMBER(10,0),");
            sql.addSql("   BFI_LIMIT_ON NUMBER(10,0) not null,");
            sql.addSql("   primary key (BFI_SID)");
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
     * <p>Insert BBS_FOR_INF Data Bindding JavaBean
     * @param bean BBS_FOR_INF Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(BbsForInfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" BBS_FOR_INF(");
            sql.addSql("   BFI_SID,");
            sql.addSql("   BFI_NAME,");
            sql.addSql("   BFI_CMT,");
            sql.addSql("   BFI_SORT,");
            sql.addSql("   BFI_REPLY,");
            sql.addSql("   BFI_READ,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   BFI_MREAD,");
            sql.addSql("   BFI_TEMPLATE_KBN,");
            sql.addSql("   BFI_TEMPLATE,");
            sql.addSql("   BFI_TEMPLATE_WRITE,");
            sql.addSql("   BFI_AUID,");
            sql.addSql("   BFI_ADATE,");
            sql.addSql("   BFI_EUID,");
            sql.addSql("   BFI_EDATE,");
            sql.addSql("   BFI_DISK,");
            sql.addSql("   BFI_DISK_SIZE,");
            sql.addSql("   BFI_WARN_DISK,");
            sql.addSql("   BFI_WARN_DISK_TH,");
            sql.addSql("   BFI_LIMIT,");
            sql.addSql("   BFI_LIMIT_DATE,");
            sql.addSql("   BFI_KEEP,");
            sql.addSql("   BFI_KEEP_DATE_Y,");
            sql.addSql("   BFI_KEEP_DATE_M,");
            sql.addSql("   BFI_LIMIT_ON");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getBfiSid());
            sql.addStrValue(bean.getBfiName());
            sql.addStrValue(bean.getBfiCmt());
            sql.addIntValue(bean.getBfiSort());
            sql.addIntValue(bean.getBfiReply());
            sql.addIntValue(bean.getBfiRead());
            sql.addLongValue(bean.getBinSid());
            sql.addIntValue(bean.getBfiMread());
            sql.addIntValue(bean.getBfiTemplateKbn());
            sql.addStrValue(bean.getBfiTemplate());
            sql.addIntValue(bean.getBfiTemplateWrite());
            sql.addIntValue(bean.getBfiAuid());
            sql.addDateValue(bean.getBfiAdate());
            sql.addIntValue(bean.getBfiEuid());
            sql.addDateValue(bean.getBfiEdate());
            sql.addIntValue(bean.getBfiDisk());
            sql.addIntValue(bean.getBfiDiskSize());
            sql.addIntValue(bean.getBfiWarnDisk());
            sql.addIntValue(bean.getBfiWarnDiskTh());
            sql.addIntValue(bean.getBfiLimit());
            sql.addIntValue(bean.getBfiLimitDate());
            sql.addIntValue(bean.getBfiKeep());
            sql.addIntValue(bean.getBfiKeepDateY());
            sql.addIntValue(bean.getBfiKeepDateM());
            sql.addIntValue(bean.getBfiLimitOn());

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
     * <p>Insert BBS_FOR_INF Data Bindding JavaBean
     * @param beanList BBS_FOR_INF Data List
     * @throws SQLException SQL実行例外
     */
    public void insert(List<BbsForInfModel> beanList) throws SQLException {

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
            sql.addSql(" BBS_FOR_INF(");
            sql.addSql("   BFI_SID,");
            sql.addSql("   BFI_NAME,");
            sql.addSql("   BFI_CMT,");
            sql.addSql("   BFI_SORT,");
            sql.addSql("   BFI_REPLY,");
            sql.addSql("   BFI_READ,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   BFI_MREAD,");
            sql.addSql("   BFI_TEMPLATE_KBN,");
            sql.addSql("   BFI_TEMPLATE,");
            sql.addSql("   BFI_TEMPLATE_WRITE,");
            sql.addSql("   BFI_AUID,");
            sql.addSql("   BFI_ADATE,");
            sql.addSql("   BFI_EUID,");
            sql.addSql("   BFI_EDATE,");
            sql.addSql("   BFI_DISK,");
            sql.addSql("   BFI_DISK_SIZE,");
            sql.addSql("   BFI_WARN_DISK,");
            sql.addSql("   BFI_WARN_DISK_TH,");
            sql.addSql("   BFI_LIMIT,");
            sql.addSql("   BFI_LIMIT_DATE,");
            sql.addSql("   BFI_KEEP,");
            sql.addSql("   BFI_KEEP_DATE_Y,");
            sql.addSql("   BFI_KEEP_DATE_M,");
            sql.addSql("   BFI_LIMIT_ON");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());

            for (BbsForInfModel bean : beanList) {
                sql.addIntValue(bean.getBfiSid());
                sql.addStrValue(bean.getBfiName());
                sql.addStrValue(bean.getBfiCmt());
                sql.addIntValue(bean.getBfiSort());
                sql.addIntValue(bean.getBfiReply());
                sql.addIntValue(bean.getBfiRead());
                sql.addLongValue(bean.getBinSid());
                sql.addIntValue(bean.getBfiMread());
                sql.addIntValue(bean.getBfiTemplateKbn());
                sql.addStrValue(bean.getBfiTemplate());
                sql.addIntValue(bean.getBfiTemplateWrite());
                sql.addIntValue(bean.getBfiAuid());
                sql.addDateValue(bean.getBfiAdate());
                sql.addIntValue(bean.getBfiEuid());
                sql.addDateValue(bean.getBfiEdate());
                sql.addIntValue(bean.getBfiDisk());
                sql.addIntValue(bean.getBfiDiskSize());
                sql.addIntValue(bean.getBfiWarnDisk());
                sql.addIntValue(bean.getBfiWarnDiskTh());
                sql.addIntValue(bean.getBfiLimit());
                sql.addIntValue(bean.getBfiLimitDate());
                sql.addIntValue(bean.getBfiKeep());
                sql.addIntValue(bean.getBfiKeepDateY());
                sql.addIntValue(bean.getBfiKeepDateM());
                sql.addIntValue(bean.getBfiLimitOn());

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
     * フォーラム情報の更新を行う
     * @param bean 更新情報
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateBBSInf(BbsForInfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" set ");
            sql.addSql("   BFI_NAME=?,");
            sql.addSql("   BFI_CMT=?,");
            sql.addSql("   BFI_REPLY=?,");
            sql.addSql("   BFI_READ=?,");
            sql.addSql("   BIN_SID=?,");
            sql.addSql("   BFI_MREAD=?,");
            sql.addSql("   BFI_TEMPLATE_KBN=?,");
            sql.addSql("   BFI_TEMPLATE=?,");
            sql.addSql("   BFI_TEMPLATE_WRITE=?,");
            sql.addSql("   BFI_EUID=?,");
            sql.addSql("   BFI_EDATE=?,");
            sql.addSql("   BFI_DISK=?,");
            sql.addSql("   BFI_DISK_SIZE=?,");
            sql.addSql("   BFI_WARN_DISK=?,");
            sql.addSql("   BFI_WARN_DISK_TH=?,");
            sql.addSql("   BFI_LIMIT=?,");
            sql.addSql("   BFI_LIMIT_DATE=?,");
            sql.addSql("   BFI_KEEP=?,");
            sql.addSql("   BFI_KEEP_DATE_Y=?,");
            sql.addSql("   BFI_KEEP_DATE_M=?,");
            sql.addSql("   BFI_LIMIT_ON=?");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getBfiName());
            sql.addStrValue(bean.getBfiCmt());
            sql.addIntValue(bean.getBfiReply());
            sql.addIntValue(bean.getBfiRead());
            sql.addLongValue(bean.getBinSid());
            sql.addIntValue(bean.getBfiMread());
            sql.addIntValue(bean.getBfiTemplateKbn());
            sql.addStrValue(bean.getBfiTemplate());
            sql.addIntValue(bean.getBfiTemplateWrite());
            sql.addIntValue(bean.getBfiEuid());
            sql.addDateValue(bean.getBfiEdate());
            sql.addIntValue(bean.getBfiDisk());
            sql.addIntValue(bean.getBfiDiskSize());
            sql.addIntValue(bean.getBfiWarnDisk());
            sql.addIntValue(bean.getBfiWarnDiskTh());
            sql.addIntValue(bean.getBfiLimit());
            sql.addIntValue(bean.getBfiLimitDate());
            sql.addIntValue(bean.getBfiKeep());
            sql.addIntValue(bean.getBfiKeepDateY());
            sql.addIntValue(bean.getBfiKeepDateM());
            sql.addIntValue(bean.getBfiLimitOn());

            //where
            sql.addIntValue(bean.getBfiSid());

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
     * フォーラム情報の更新を行う
     * @param bean 更新情報
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateBBSSort(BbsForInfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" set ");
            sql.addSql("   BFI_SORT=?");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getBfiSort());
            //where
            sql.addIntValue(bean.getBfiSid());

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
     * <p>Select BBS_FOR_INF All Data
     * @return List in BBS_FOR_INFModel
     * @throws SQLException SQL実行例外
     */
    public List< BbsForInfModel > select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList < BbsForInfModel > ret = new ArrayList < BbsForInfModel >();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   BFI_SID,");
            sql.addSql("   BFI_NAME,");
            sql.addSql("   BFI_CMT,");
            sql.addSql("   BFI_SORT,");
            sql.addSql("   BFI_REPLY,");
            sql.addSql("   BFI_READ,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   BFI_MREAD,");
            sql.addSql("   BFI_AUID,");
            sql.addSql("   BFI_ADATE,");
            sql.addSql("   BFI_EUID,");
            sql.addSql("   BFI_EDATE,");
            sql.addSql("   BFI_DISK,");
            sql.addSql("   BFI_DISK_SIZE,");
            sql.addSql("   BFI_WARN_DISK,");
            sql.addSql("   BFI_WARN_DISK_TH,");
            sql.addSql("   BFI_LIMIT,");
            sql.addSql("   BFI_LIMIT_DATE,");
            sql.addSql("   BFI_KEEP,");
            sql.addSql("   BFI_KEEP_DATE_Y,");
            sql.addSql("   BFI_KEEP_DATE_M,");
            sql.addSql("   BFI_LIMIT_ON");
            sql.addSql(" from ");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" order by");
            sql.addSql("   BFI_SORT, BFI_NAME");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getBbsForInfFromRs(rs));
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
     * <p>Select BBS_FOR_INF
     * @param bean BBS_FOR_INF Model
     * @return BBS_FOR_INFModel
     * @throws SQLException SQL実行例外
     */
    public BbsForInfModel select(BbsForInfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        BbsForInfModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   BFI_SID,");
            sql.addSql("   BFI_NAME,");
            sql.addSql("   BFI_CMT,");
            sql.addSql("   BFI_SORT,");
            sql.addSql("   BFI_REPLY,");
            sql.addSql("   BFI_READ,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   BFI_MREAD,");
            sql.addSql("   BFI_AUID,");
            sql.addSql("   BFI_ADATE,");
            sql.addSql("   BFI_EUID,");
            sql.addSql("   BFI_EDATE,");
            sql.addSql("   BFI_DISK,");
            sql.addSql("   BFI_DISK_SIZE,");
            sql.addSql("   BFI_WARN_DISK,");
            sql.addSql("   BFI_WARN_DISK_TH,");
            sql.addSql("   BFI_LIMIT,");
            sql.addSql("   BFI_LIMIT_DATE,");
            sql.addSql("   BFI_KEEP,");
            sql.addSql("   BFI_KEEP_DATE_Y,");
            sql.addSql("   BFI_KEEP_DATE_M,");
            sql.addSql("   BFI_LIMIT_ON");
            sql.addSql(" from ");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getBfiSid());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getBbsForInfFromRs(rs);
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
     * <p>指定したフォーラムが存在するかカウントする
     * @param bfiSid フォーラムSID
     * @return BBS_FOR_INFModel
     * @throws SQLException SQL実行例外
     */
    public int countBfi(int bfiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(*) as CNT");
            sql.addSql(" from ");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bfiSid);

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
     * <p>指定されたフォーラムSIDからアイコンバイナリSIDを取得する
     * @param bfiSid フォーラムSID
     * @return アイコン画像バイナリSID
     * @throws SQLException SQL実行例外
     */
    public Long selectIcoBinSid(int bfiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Long ret = new Long(0);
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   BIN_SID");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getLong("BIN_SID");
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
     * <p>Select BBS_FOR_INF
     * @param sortNum ソート順
     * @return List
     * @throws SQLException SQL実行例外
     */
    public List < BbsForInfModel > select(int sortNum) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList < BbsForInfModel > ret = new ArrayList < BbsForInfModel >();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   BFI_SID,");
            sql.addSql("   BFI_NAME,");
            sql.addSql("   BFI_CMT,");
            sql.addSql("   BFI_SORT,");
            sql.addSql("   BFI_REPLY,");
            sql.addSql("   BFI_READ,");
            sql.addSql("   BIN_SID,");
            sql.addSql("   BFI_MREAD,");
            sql.addSql("   BFI_AUID,");
            sql.addSql("   BFI_ADATE,");
            sql.addSql("   BFI_EUID,");
            sql.addSql("   BFI_EDATE,");
            sql.addSql("   BFI_DISK,");
            sql.addSql("   BFI_DISK_SIZE,");
            sql.addSql("   BFI_WARN_DISK,");
            sql.addSql("   BFI_WARN_DISK_TH,");
            sql.addSql("   BFI_LIMIT,");
            sql.addSql("   BFI_LIMIT_DATE,");
            sql.addSql("   BFI_KEEP,");
            sql.addSql("   BFI_KEEP_DATE_Y,");
            sql.addSql("   BFI_KEEP_DATE_M,");
            sql.addSql("   BFI_LIMIT_ON");
            sql.addSql(" from ");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where ");
            sql.addSql("   BFI_SORT > ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sortNum);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getBbsForInfFromRs(rs));
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
     * <p>Select BBS_FOR_INF
     * @return int count
     * @throws SQLException SQL実行例外
     */
    public int count() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(BFI_SORT) as CNT");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where ");
            sql.addSql("   BFI_SORT=0");

            pstmt = con.prepareStatement(sql.toSqlString());

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
     * <p>Select BBS_FOR_INF All Data
     * @return int getMaxSort
     * @throws SQLException SQL実行例外
     */
    public int getMaxSort() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   max(BFI_SORT)");
            sql.addSql(" from ");
            sql.addSql("   BBS_FOR_INF");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            ret = rs.getInt("BFI_SORT");
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <p>Delete BBS_FOR_INF
     * @param bean BBS_FOR_INF Model
     * @return delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(BbsForInfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getBfiSid());

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
     * <p>指定されたフォーラムSIDとアイコンバイナリSIDの組み合わせが存在するかを確認する
     * @param bfiSid フォーラムSID
     * @param icoBinSid アイコンバイナリSID
     * @return 結果 true:存在する false:存在しない
     * @throws SQLException SQL実行例外
     */
    public boolean existBbsForIco(int bfiSid, Long icoBinSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean ret = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   BFI_SID");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");
            sql.addSql(" and ");
            sql.addSql("   BIN_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bfiSid);
            sql.addLongValue(icoBinSid);

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
     * <br>[機  能] 指定したユーザがメンバーに含まれる かつ
     * <br>         新規ユーザのスレッド閲覧状態=「既読」のフォーラムを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @return フォーラムSID
     * @throws SQLException SQL実行例外
     */
    public List <Integer> getInitForumSidList(int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List <Integer> ret = new ArrayList <Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   BFI_SID");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where ");
            sql.addSql("   BFI_READ = ?");
            sql.addSql(" and");
            sql.addSql("   BFI_SID in (");
            sql.addSql("     select");
            sql.addSql("       BFI_SID");
            sql.addSql("     from");
            sql.addSql("       BBS_FOR_MEM");
            sql.addSql("       left join");
            sql.addSql("         CMN_BELONGM");
            sql.addSql("       on");
            sql.addSql("         BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("     where");
            sql.addSql("       BBS_FOR_MEM.USR_SID = ?");
            sql.addSql("     or");
            sql.addSql("       CMN_BELONGM.USR_SID = ?");
            sql.addSql("   )");

            sql.addIntValue(GSConstBulletin.NEWUSER_THRE_VIEW_YES);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt("BFI_SID"));
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
     * <br>[機  能] 指定したグループがメンバーに含まれる かつ
     * <br>         新規ユーザのスレッド閲覧状態=「既読」のフォーラムを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param grpSidList グループSID
     * @return フォーラムSID
     * @throws SQLException SQL実行例外
     */
    public List <Integer> getInitForumSidList(List<Integer> grpSidList) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List <Integer> ret = new ArrayList <Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   BFI_SID");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where ");
            sql.addSql("   BFI_READ = ?");
            sql.addIntValue(GSConstBulletin.NEWUSER_THRE_VIEW_YES);

            sql.addSql(" and");
            sql.addSql("   BFI_SID in (");
            sql.addSql("     select ");
            sql.addSql("       BFI_SID");
            sql.addSql("     from ");
            sql.addSql("       BBS_FOR_MEM");
            sql.addSql("     where ");
            sql.addSql("       GRP_SID in (");
            for (int idx = 0; idx < grpSidList.size(); idx++) {
                if (idx > 0) {
                    sql.addSql("         ,?");
                } else {
                    sql.addSql("         ?");
                }
                sql.addIntValue(grpSidList.get(idx));
            }
            sql.addSql("       )");
            sql.addSql("   )");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt("BFI_SID"));
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
     * <br>[機  能] フォーラムのうち、指定したグループ or ユーザがメンバーとして登録されているものを除外する
     * <br>[解  説]
     * <br>[備  考]
     * @param forumSidList フォーラムSID一覧
     * @param excGroupList 除外するグループのグループSID
     * @param usrSid ユーザSID
     * @return フォーラムSID一覧
     * @throws SQLException SQL実行例外
     */
    public List<Integer> excludeForumSidList(List<Integer> forumSidList,
                                        List<Integer> excGroupList,
                                        int usrSid)
    throws SQLException {

        if (forumSidList == null || forumSidList.isEmpty()
        || excGroupList == null || excGroupList.isEmpty()) {
            return forumSidList;
        }

        List<Integer> bfiSidList = new ArrayList<Integer>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   BFI_SID");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where");
            sql.addSql("   BFI_SID in (");
            for (int idx = 0; idx < forumSidList.size(); idx++) {
                if (idx > 0) {
                    sql.addSql("         ,?");
                } else {
                    sql.addSql("         ?");
                }
                sql.addIntValue(forumSidList.get(idx));
            }
            sql.addSql("   )");

            sql.addSql(" and");
            sql.addSql("   BFI_SID not in (");
            sql.addSql("     select ");
            sql.addSql("       BFI_SID");
            sql.addSql("     from ");
            sql.addSql("       BBS_FOR_MEM");

            sql.addSql("     where ");
            sql.addSql("       USR_SID = ?");
            sql.addIntValue(usrSid);

            sql.addSql("     or");
            sql.addSql("       GRP_SID in (");
            for (int idx = 0; idx < excGroupList.size(); idx++) {
                if (idx > 0) {
                    sql.addSql("         ,?");
                } else {
                    sql.addSql("         ?");
                }
                sql.addIntValue(excGroupList.get(idx));
            }
            sql.addSql("       )");
            sql.addSql("   )");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                bfiSidList.add(rs.getInt("BFI_SID"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return bfiSidList;
    }

    /**
     * <p>Create BBS_FOR_INF Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created BbsForInfModel
     * @throws SQLException SQL実行例外
     */
    private BbsForInfModel __getBbsForInfFromRs(ResultSet rs) throws SQLException {
        BbsForInfModel bean = new BbsForInfModel();
        bean.setBfiSid(rs.getInt("BFI_SID"));
        bean.setBfiName(rs.getString("BFI_NAME"));
        bean.setBfiCmt(rs.getString("BFI_CMT"));
        bean.setBfiSort(rs.getInt("BFI_SORT"));
        bean.setBfiReply(rs.getInt("BFI_REPLY"));
        bean.setBfiRead(rs.getInt("BFI_READ"));
        bean.setBinSid(rs.getLong("BIN_SID"));
        bean.setBfiMread(rs.getInt("BFI_MREAD"));
        bean.setBfiAuid(rs.getInt("BFI_AUID"));
        bean.setBfiAdate(UDate.getInstanceTimestamp(rs.getTimestamp("BFI_ADATE")));
        bean.setBfiEuid(rs.getInt("BFI_EUID"));
        bean.setBfiEdate(UDate.getInstanceTimestamp(rs.getTimestamp("BFI_EDATE")));
        bean.setBfiDisk(rs.getInt("BFI_DISK"));
        bean.setBfiDiskSize(rs.getInt("BFI_DISK_SIZE"));
        bean.setBfiWarnDisk(rs.getInt("BFI_WARN_DISK"));
        bean.setBfiWarnDiskTh(rs.getInt("BFI_WARN_DISK_TH"));
        bean.setBfiLimit(rs.getInt("BFI_LIMIT"));
        bean.setBfiLimitDate(rs.getInt("BFI_LIMIT_DATE"));
        bean.setBfiKeep(rs.getInt("BFI_KEEP"));
        bean.setBfiKeepDateY(rs.getInt("BFI_KEEP_DATE_Y"));
        bean.setBfiKeepDateM(rs.getInt("BFI_KEEP_DATE_M"));
        bean.setBfiLimitOn(rs.getInt("BFI_LIMIT_ON"));
        return bean;
    }
}
