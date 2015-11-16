package jp.groupsession.v2.wml.wml220;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.wml.model.base.AccountDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール 管理者設定 フィルタ管理画面で使用するDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml220Dao extends AbstractDao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml220Dao.class);

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     */
    public Wml220Dao(Connection con) {
        super(con);
    }

    /**
     * <br>[機  能] アカウントリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public List<AccountDataModel> getAccountList()
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<AccountDataModel> ret = new ArrayList<AccountDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_ACCOUNT.WAC_SID as WAC_SID,");
            sql.addSql("   WML_ACCOUNT.WAC_NAME as WAC_NAME");
            sql.addSql(" from ");
            sql.addSql("   WML_ACCOUNT");
            sql.addSql(" where");
            sql.addSql("   WML_ACCOUNT.WAC_JKBN = ?");
            sql.addSql(" order by WAC_NAME asc");
            sql.addIntValue(GSConstWebmail.WAC_JKBN_NORMAL);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AccountDataModel model = new AccountDataModel();
                model.setAccountSid(rs.getInt("WAC_SID"));
                model.setAccountName(rs.getString("WAC_NAME"));
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
     * <br>[機  能] フィルター情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wacSid アカウントSID
     * @param usrSid ユーザSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public List<Wml220FilterDataModel> getFlterList(int wacSid, int usrSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Wml220FilterDataModel> ret =
            new ArrayList<Wml220FilterDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_FILTER.WFT_SID, ");
            sql.addSql("   WML_FILTER.WFT_NAME, ");
            sql.addSql("   WML_FILTER_SORT.WFS_SORT ");
            sql.addSql(" from ");
            sql.addSql("   WML_FILTER, ");
            sql.addSql("   WML_FILTER_SORT ");
            sql.addSql(" where ");
            sql.addSql("   WML_FILTER.WFT_SID = WML_FILTER_SORT.WFT_SID ");
            sql.addSql(" and ");
            sql.addSql("    (WML_FILTER.WFT_TYPE = ?");
            sql.addSql("     and ");
            sql.addSql("       (");
            sql.addSql("         WML_FILTER.USR_SID in (");
            sql.addSql("           select USR_SID from WML_ACCOUNT_USER");
            sql.addSql("           where WAC_SID = ?");
            sql.addSql("           and coalesce(USR_SID, 0) > 0");
            sql.addSql("         )");
            sql.addSql("        or");
            sql.addSql("         WML_FILTER.USR_SID in (");
            sql.addSql("           select CMN_BELONGM.USR_SID");
            sql.addSql("           from");
            sql.addSql("             WML_ACCOUNT_USER,");
            sql.addSql("             CMN_BELONGM");
            sql.addSql("           where WML_ACCOUNT_USER.WAC_SID = ?");
            sql.addSql("           and coalesce(WML_ACCOUNT_USER.GRP_SID, 0) > 0");
            sql.addSql("           and WML_ACCOUNT_USER.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("         )");
            sql.addSql("       )");
            sql.addSql("   or (WML_FILTER.WFT_TYPE = ? ");
            sql.addSql("   and ");
            sql.addSql("        WML_FILTER.WAC_SID = ?)) ");
            sql.addSql(" and ");
            sql.addSql("   WML_FILTER_SORT.WAC_SID = ? ");
            sql.addSql(" order by ");
            sql.addSql("   WML_FILTER_SORT.WFS_SORT asc ");
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(GSConstWebmail.LABELTYPE_ALL);
            sql.addIntValue(wacSid);
            sql.addIntValue(wacSid);
            sql.addIntValue(GSConstWebmail.LABELTYPE_ONES);
            sql.addIntValue(wacSid);
            sql.addIntValue(wacSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Wml220FilterDataModel model = new Wml220FilterDataModel();
                model.setFilterSid(rs.getInt("WFT_SID"));
                model.setFilterName(rs.getString("WFT_NAME"));
                model.setFilterSort(rs.getInt("WFS_SORT"));
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
     * <br>[機  能] フィルター条件の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param wftSid フィルターSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public int deleteFilterCondition(int wftSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   WML_FILTER_CONDITION");
            sql.addSql(" where ");
            sql.addSql("   WFT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(wftSid);

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
     * <br>[機  能] フィルター条件の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param wftSid フィルターSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public int deleteFilterSortCondition(int wftSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   WML_FILTER_SORT");
            sql.addSql(" where ");
            sql.addSql("   WFT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(wftSid);

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
     * <br>[機  能] 表示順序を入れ替える
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param motoSid 入れ替え元フィルターSID
     * @param motoSort 入れ替え元ソート順
     * @param sakiSid 入れ替え先フィルターSID
     * @param sakiSort 入れ替え先ソート順
     * @throws SQLException SQL実行例外
     */
    public void updateSort(
        int motoSid,
        int motoSort,
        int sakiSid,
        int sakiSort) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   WML_FILTER_SORT");
            sql.addSql("     set WFS_SORT = case when WFT_SID = ? ");
            sql.addSql("     then ? ");
            sql.addSql("     when WFT_SID = ? ");
            sql.addSql("     then ? ");
            sql.addSql("     else WFS_SORT end");

            sql.addIntValue(motoSid);
            sql.addIntValue(sakiSort);
            sql.addIntValue(sakiSid);
            sql.addIntValue(motoSort);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }
}
