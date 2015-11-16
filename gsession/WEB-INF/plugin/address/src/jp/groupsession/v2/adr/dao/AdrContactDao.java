package jp.groupsession.v2.adr.dao;

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
import jp.groupsession.v2.adr.model.AdrContactModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>ADR_CONTACT Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class AdrContactDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(AdrContactDao.class);

    /**
     * <p>Default Constructor
     */
    public AdrContactDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public AdrContactDao(Connection con) {
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
            sql.addSql("drop table ADR_CONTACT");

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
            sql.addSql(" create table ADR_CONTACT (");
            sql.addSql("   ADC_SID NUMBER(10,0) not null,");
            sql.addSql("   ADR_SID NUMBER(10,0) not null,");
            sql.addSql("   ADC_TITLE varchar(100) not null,");
            sql.addSql("   ADC_TYPE NUMBER(10,0) not null,");
            sql.addSql("   ADC_CTTIME varchar(23),");
            sql.addSql("   ADC_CTTIME_TO varchar(23),");
            sql.addSql("   PRJ_SID NUMBER(10,0),");
            sql.addSql("   ADC_BIKO varchar(1000),");
            sql.addSql("   ADC_AUID NUMBER(10,0) not null,");
            sql.addSql("   ADC_ADATE varchar(23) not null,");
            sql.addSql("   ADC_EUID NUMBER(10,0) not null,");
            sql.addSql("   ADC_EDATE varchar(23) not null,");
            sql.addSql("   primary key (ADC_SID)");
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
     * <p>Insert ADR_CONTACT Data Bindding JavaBean
     * @param bean ADR_CONTACT Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(AdrContactModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" ADR_CONTACT(");
            sql.addSql("   ADC_SID,");
            sql.addSql("   ADR_SID,");
            sql.addSql("   ADC_TITLE,");
            sql.addSql("   ADC_TYPE,");
            sql.addSql("   ADC_CTTIME,");
            sql.addSql("   ADC_CTTIME_TO,");
            sql.addSql("   PRJ_SID,");
            sql.addSql("   ADC_BIKO,");
            sql.addSql("   ADC_AUID,");
            sql.addSql("   ADC_ADATE,");
            sql.addSql("   ADC_EUID,");
            sql.addSql("   ADC_EDATE,");
            sql.addSql("   ADC_GRP_SID");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getAdcSid());
            sql.addIntValue(bean.getAdrSid());
            sql.addStrValue(bean.getAdcTitle());
            sql.addIntValue(bean.getAdcType());
            sql.addDateValue(bean.getAdcCttime());
            sql.addDateValue(bean.getAdcCttimeTo());
            sql.addIntValue(bean.getPrjSid());
            sql.addStrValue(bean.getAdcBiko());
            sql.addIntValue(bean.getAdcAuid());
            sql.addDateValue(bean.getAdcAdate());
            sql.addIntValue(bean.getAdcEuid());
            sql.addDateValue(bean.getAdcEdate());
            sql.addIntValue(bean.getAdcGrpSid());
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
     * <p>Update ADR_CONTACT Data Bindding JavaBean
     * @param bean ADR_CONTACT Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(AdrContactModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   ADR_CONTACT");
            sql.addSql(" set ");
            sql.addSql("   ADR_SID=?,");
            sql.addSql("   ADC_TITLE=?,");
            sql.addSql("   ADC_TYPE=?,");
            sql.addSql("   ADC_CTTIME=?,");
            sql.addSql("   ADC_CTTIME_TO=?,");
            sql.addSql("   PRJ_SID=?,");
            sql.addSql("   ADC_BIKO=?,");
            sql.addSql("   ADC_AUID=?,");
            sql.addSql("   ADC_ADATE=?,");
            sql.addSql("   ADC_EUID=?,");
            sql.addSql("   ADC_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   ADC_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getAdrSid());
            sql.addStrValue(bean.getAdcTitle());
            sql.addIntValue(bean.getAdcType());
            sql.addDateValue(bean.getAdcCttime());
            sql.addDateValue(bean.getAdcCttimeTo());
            sql.addIntValue(bean.getPrjSid());
            sql.addStrValue(bean.getAdcBiko());
            sql.addIntValue(bean.getAdcAuid());
            sql.addDateValue(bean.getAdcAdate());
            sql.addIntValue(bean.getAdcEuid());
            sql.addDateValue(bean.getAdcEdate());
            //where
            sql.addIntValue(bean.getAdcSid());

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
     * <p>Select ADR_CONTACT All Data
     * @return List in ADR_CONTACTModel
     * @throws SQLException SQL実行例外
     */
    public List<AdrContactModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<AdrContactModel> ret = new ArrayList<AdrContactModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   ADC_SID,");
            sql.addSql("   ADR_SID,");
            sql.addSql("   ADC_TITLE,");
            sql.addSql("   ADC_TYPE,");
            sql.addSql("   ADC_CTTIME,");
            sql.addSql("   ADC_CTTIME_TO,");
            sql.addSql("   PRJ_SID,");
            sql.addSql("   ADC_BIKO,");
            sql.addSql("   ADC_AUID,");
            sql.addSql("   ADC_ADATE,");
            sql.addSql("   ADC_EUID,");
            sql.addSql("   ADC_EDATE,");
            sql.addSql("   ADC_GRP_SID");
            sql.addSql(" from ");
            sql.addSql("   ADR_CONTACT");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getAdrContactFromRs(rs));
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
     * <br>[機  能] 指定されたコンタクト履歴グループSIDのアドレス帳を取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param adcsid コンタクト履歴SID
     * @param adcGrpSid コンタクト履歴グループSID
     * @return ret コンタクト履歴グループのアドレス帳リスト
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<AdrContactModel> selectGrpList(int adcsid, int adcGrpSid)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<AdrContactModel> ret = new ArrayList<AdrContactModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   ADC_SID,");
            sql.addSql("   ADR_SID,");
            sql.addSql("   ADC_TITLE,");
            sql.addSql("   ADC_TYPE,");
            sql.addSql("   ADC_CTTIME,");
            sql.addSql("   ADC_CTTIME_TO,");
            sql.addSql("   PRJ_SID,");
            sql.addSql("   ADC_BIKO,");
            sql.addSql("   ADC_AUID,");
            sql.addSql("   ADC_ADATE,");
            sql.addSql("   ADC_EUID,");
            sql.addSql("   ADC_EDATE,");
            sql.addSql("   ADC_GRP_SID");
            sql.addSql(" from ");
            sql.addSql("   ADR_CONTACT");
            sql.addSql(" where ");
            sql.addSql("   ADC_GRP_SID = ?");
            sql.addIntValue(adcGrpSid);

            if (adcsid > 0) {
                sql.addSql(" and");
                sql.addSql("   ADC_SID <> ?");
                sql.addIntValue(adcsid);
            }

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getAdrContactFromRs(rs));
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
     * <p>Select ADR_CONTACT
     * @param adcSid ADC_SID
     * @return ADR_CONTACTModel
     * @throws SQLException SQL実行例外
     */
    public AdrContactModel select(int adcSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        AdrContactModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   ADC_SID,");
            sql.addSql("   ADR_SID,");
            sql.addSql("   ADC_TITLE,");
            sql.addSql("   ADC_TYPE,");
            sql.addSql("   ADC_CTTIME,");
            sql.addSql("   ADC_CTTIME_TO,");
            sql.addSql("   PRJ_SID,");
            sql.addSql("   ADC_BIKO,");
            sql.addSql("   ADC_AUID,");
            sql.addSql("   ADC_ADATE,");
            sql.addSql("   ADC_EUID,");
            sql.addSql("   ADC_EDATE,");
            sql.addSql("   ADC_GRP_SID");
            sql.addSql(" from");
            sql.addSql("   ADR_CONTACT");
            sql.addSql(" where ");
            sql.addSql("   ADC_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(adcSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getAdrContactFromRs(rs);
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
     * <p>Delete ADR_CONTACT
     * @param adcSid ADC_SID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int delete(int adcSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   ADR_CONTACT");
            sql.addSql(" where ");
            sql.addSql("   ADC_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(adcSid);

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
     * <br>[機  能] 指定したアドレス帳のコンタクト履歴情報を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param adrSid アドレス帳SID
     * @return delete count
     * @throws SQLException SQL実行時例外
     */
    public int deleteToAddress(int adrSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   ADR_CONTACT");
            sql.addSql(" where ");
            sql.addSql("   ADR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(adrSid);

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
     * <br>[機  能] 指定したコンタクト履歴グループSIDデータを削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param adcGrpSid コンタクト履歴グループSID
     * @return delete count
     * @throws SQLException SQL実行時例外
     */
    public int deleteToGrpData(int adcGrpSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   ADR_CONTACT");
            sql.addSql(" where ");
            sql.addSql("   ADC_GRP_SID = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(adcGrpSid);

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
     * <br>[機  能] 指定されたコンタクト履歴グループSIDのアドレス帳SIDを取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param adcGrpSid コンタクト履歴グループSID
     * @return ret コンタクト履歴グループのアドレス帳リスト
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<Integer> selectGrpAdrSid(int adcGrpSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Connection con = getCon();

        try {

            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   ADR_SID");
            sql.addSql(" from");
            sql.addSql("   ADR_CONTACT");
            sql.addSql(" where ");
            sql.addSql("   ADC_GRP_SID = ?");
            sql.addSql(" group by ");
            sql.addSql("   ADR_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(adcGrpSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt("ADR_SID"));
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
     * <p>Create ADR_CONTACT Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created AdrContactModel
     * @throws SQLException SQL実行例外
     */
    private AdrContactModel __getAdrContactFromRs(ResultSet rs) throws SQLException {
        AdrContactModel bean = new AdrContactModel();
        bean.setAdcSid(rs.getInt("ADC_SID"));
        bean.setAdrSid(rs.getInt("ADR_SID"));
        bean.setAdcTitle(rs.getString("ADC_TITLE"));
        bean.setAdcType(rs.getInt("ADC_TYPE"));
        bean.setAdcCttime(UDate.getInstanceTimestamp(rs.getTimestamp("ADC_CTTIME")));
        bean.setAdcCttimeTo(UDate.getInstanceTimestamp(rs.getTimestamp("ADC_CTTIME_TO")));
        bean.setPrjSid(rs.getInt("PRJ_SID"));
        bean.setAdcBiko(rs.getString("ADC_BIKO"));
        bean.setAdcAuid(rs.getInt("ADC_AUID"));
        bean.setAdcAdate(UDate.getInstanceTimestamp(rs.getTimestamp("ADC_ADATE")));
        bean.setAdcEuid(rs.getInt("ADC_EUID"));
        bean.setAdcEdate(UDate.getInstanceTimestamp(rs.getTimestamp("ADC_EDATE")));
        bean.setAdcGrpSid(rs.getInt("ADC_GRP_SID"));
        return bean;
    }
}