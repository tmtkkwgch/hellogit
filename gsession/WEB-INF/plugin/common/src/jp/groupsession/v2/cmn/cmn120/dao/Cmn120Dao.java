package jp.groupsession.v2.cmn.cmn120.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <p>SML_ACCOUNT Data Access Object
 *
 * @author JTS DaoGenerator version 0.5
 */
public class Cmn120Dao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Cmn120Dao.class);

    /**
     * <p>Default Constructor
     */
    public Cmn120Dao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public Cmn120Dao(Connection con) {
        super(con);
    }

    /**
     * <p>ショートメール代表アカウント(作成アカウント)の件数を取得する
     * @param sacSidss 除外するアカウントSID
     * @return cnt アカウント件数
     * @throws SQLException SQL実行例外
     */
    public int selectSmlAccountCnt(
            ArrayList<String> sacSidss) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(SAC_SID) as CNT");
            sql.addSql(" from ");
            sql.addSql("   SML_ACCOUNT");
            sql.addSql(" where ");
            sql.addSql("   USR_SID is null");
            sql.addSql(" and ");
            sql.addSql("   SAC_JKBN = ?");
            sql.addIntValue(0);

            if (sacSidss != null && !sacSidss.isEmpty()) {
                sql.addSql(" and ");
                sql.addSql("   SAC_SID not in (");
                for (int i = 0; i < sacSidss.size(); i++) {
                    if (i > 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql(sacSidss.get(i));
                }
                sql.addSql(")");
            }

            pstmt = con.prepareStatement(sql.toSqlString());
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
     * <p>ショートメール代表アカウント(作成アカウント)をすべて取得する
     * @param sacSidss 除外するアカウントSID
     * @return List in SML_ACCOUNTModel
     * @throws SQLException SQL実行例外
     */
    public List<LabelValueBean> selectSmlAccount(
            ArrayList<String> sacSidss) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        LabelValueBean smlUsrMdl = null;
        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SAC_SID,");
            sql.addSql("   SAC_TYPE,");
            sql.addSql("   USR_SID,");
            sql.addSql("   SAC_NAME,");
            sql.addSql("   SAC_BIKO,");
            sql.addSql("   SAC_JKBN,");
            sql.addSql("   SAC_THEME,");
            sql.addSql("   SAC_QUOTES");
            sql.addSql(" from ");
            sql.addSql("   SML_ACCOUNT");
            sql.addSql(" where ");
            sql.addSql("   USR_SID is null");
            sql.addSql(" and ");
            sql.addSql("   SAC_JKBN = ?");
            sql.addIntValue(0);

            if (sacSidss != null && !sacSidss.isEmpty()) {
                sql.addSql(" and ");
                sql.addSql("   SAC_SID not in (");
                for (int i = 0; i < sacSidss.size(); i++) {
                    if (i > 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql(sacSidss.get(i));
                }
                sql.addSql(")");
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                smlUsrMdl = new LabelValueBean();
                smlUsrMdl.setLabel(rs.getString("SAC_NAME"));
                smlUsrMdl.setValue("sac" + String.valueOf(rs.getInt("SAC_SID")));
                ret.add(smlUsrMdl);
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
     * <p>ショートメール代表アカウント(作成アカウント)をすべて取得する
     * @param sacSidss 取得するアカウントSID
     * @return List in SML_ACCOUNTModel
     * @throws SQLException SQL実行例外
     */
    public List<LabelValueBean> selectSmlSelAccount(
            ArrayList<String> sacSidss) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        LabelValueBean smlUsrMdl = null;
        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SAC_SID,");
            sql.addSql("   SAC_TYPE,");
            sql.addSql("   USR_SID,");
            sql.addSql("   SAC_NAME,");
            sql.addSql("   SAC_BIKO,");
            sql.addSql("   SAC_JKBN,");
            sql.addSql("   SAC_THEME,");
            sql.addSql("   SAC_QUOTES");
            sql.addSql(" from ");
            sql.addSql("   SML_ACCOUNT");
            sql.addSql(" where ");
            sql.addSql("   USR_SID is null");
            sql.addSql(" and ");
            sql.addSql("   SAC_JKBN = ?");
            sql.addIntValue(0);

            if (sacSidss != null && !sacSidss.isEmpty()) {
                sql.addSql(" and ");
                sql.addSql("   SAC_SID in (");
                for (int i = 0; i < sacSidss.size(); i++) {
                    if (i > 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql(sacSidss.get(i));
                }
                sql.addSql(")");
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                smlUsrMdl = new LabelValueBean();
                smlUsrMdl.setLabel(rs.getString("SAC_NAME"));
                smlUsrMdl.setValue("sac" + String.valueOf(rs.getInt("SAC_SID")));
                ret.add(smlUsrMdl);
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
     * <p>回覧板代表アカウント(作成アカウント)をすべて取得する
     * @param sacSidss 除外するアカウントSID
     * @return List in SML_ACCOUNTModel
     * @throws SQLException SQL実行例外
     */
    public List<LabelValueBean> selectCirAccount(
            ArrayList<String> sacSidss) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        LabelValueBean smlUsrMdl = null;
        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   CAC_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   CAC_NAME,");
            sql.addSql("   CAC_BIKO,");
            sql.addSql("   CAC_JKBN,");
            sql.addSql("   CAC_THEME");
            sql.addSql(" from ");
            sql.addSql("   CIR_ACCOUNT");
            sql.addSql(" where ");
            sql.addSql("   USR_SID is null");
            sql.addSql(" and ");
            sql.addSql("   CAC_JKBN = ?");
            sql.addIntValue(0);

            if (sacSidss != null && !sacSidss.isEmpty()) {
                sql.addSql(" and ");
                sql.addSql("   CAC_SID not in (");
                for (int i = 0; i < sacSidss.size(); i++) {
                    if (i > 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql(sacSidss.get(i));
                }
                sql.addSql(")");
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                smlUsrMdl = new LabelValueBean();
                smlUsrMdl.setLabel(rs.getString("CAC_NAME"));
                smlUsrMdl.setValue("cac" + String.valueOf(rs.getInt("CAC_SID")));
                ret.add(smlUsrMdl);
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
     * <p>回覧板代表アカウント(作成アカウント)をすべて取得する
     * @param cacSids 取得するアカウントSID
     * @return List in SML_ACCOUNTModel
     * @throws SQLException SQL実行例外
     */
    public List<LabelValueBean> selectCirSelAccount(
            ArrayList<String> cacSids) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        LabelValueBean cirUsrMdl = null;
        ArrayList<LabelValueBean> ret = new ArrayList<LabelValueBean>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   CAC_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   CAC_NAME,");
            sql.addSql("   CAC_BIKO,");
            sql.addSql("   CAC_JKBN,");
            sql.addSql("   CAC_THEME");
            sql.addSql(" from ");
            sql.addSql("   CIR_ACCOUNT");
            sql.addSql(" where ");
            sql.addSql("   USR_SID is null");
            sql.addSql(" and ");
            sql.addSql("   CAC_JKBN = ?");
            sql.addIntValue(0);

            if (cacSids != null && !cacSids.isEmpty()) {
                sql.addSql(" and ");
                sql.addSql("   CAC_SID in (");
                for (int i = 0; i < cacSids.size(); i++) {
                    if (i > 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql(cacSids.get(i));
                }
                sql.addSql(")");
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cirUsrMdl = new LabelValueBean();
                cirUsrMdl.setLabel(rs.getString("CAC_NAME"));
                cirUsrMdl.setValue("cac" + String.valueOf(rs.getInt("CAC_SID")));
                ret.add(cirUsrMdl);
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
