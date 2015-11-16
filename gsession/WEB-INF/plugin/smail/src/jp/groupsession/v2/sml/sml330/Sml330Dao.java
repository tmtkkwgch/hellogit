package jp.groupsession.v2.sml.sml330;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.model.AccountDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ショートメール 管理者設定 フィルタ管理画面で使用するDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml330Dao extends AbstractDao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml330Dao.class);

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     */
    public Sml330Dao(Connection con) {
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
            sql.addSql("   SML_ACCOUNT.SAC_SID as SAC_SID,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as SAC_NAME");
            sql.addSql(" from ");
            sql.addSql("   SML_ACCOUNT");
            sql.addSql(" where");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN = ?");
            sql.addSql(" order by SAC_NAME asc");
            sql.addIntValue(GSConstSmail.SAC_JKBN_NORMAL);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AccountDataModel model = new AccountDataModel();
                model.setAccountSid(rs.getInt("SAC_SID"));
                model.setAccountName(rs.getString("SAC_NAME"));
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
     * @param sml200account アカウントSID
     * @param usrSid ユーザSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public List<Sml330FilterDataModel> getFlterList(int sml200account, int usrSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Sml330FilterDataModel> ret =
            new ArrayList<Sml330FilterDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SML_FILTER.SFT_SID, ");
            sql.addSql("   SML_FILTER.SFT_NAME, ");
            sql.addSql("   SML_FILTER_SORT.SFS_SORT ");
            sql.addSql(" from ");
            sql.addSql("   SML_FILTER, ");
            sql.addSql("   SML_FILTER_SORT ");
            sql.addSql(" where ");
            sql.addSql("   SML_FILTER.SFT_SID = SML_FILTER_SORT.SFT_SID ");
            sql.addSql(" and ");
            sql.addSql("    (SML_FILTER.SFT_TYPE = ?");
            sql.addSql("     and ");
            sql.addSql("       (");
            sql.addSql("         SML_FILTER.USR_SID in (");
            sql.addSql("           select USR_SID from SML_ACCOUNT_USER");
            sql.addSql("           where SAC_SID = ?");
            sql.addSql("           and coalesce(USR_SID, 0) > 0");
            sql.addSql("         )");
            sql.addSql("        or");
            sql.addSql("         SML_FILTER.USR_SID in (");
            sql.addSql("           select CMN_BELONGM.USR_SID");
            sql.addSql("           from");
            sql.addSql("             SML_ACCOUNT_USER,");
            sql.addSql("             CMN_BELONGM");
            sql.addSql("           where SML_ACCOUNT_USER.SAC_SID = ?");
            sql.addSql("           and coalesce(SML_ACCOUNT_USER.GRP_SID, 0) > 0");
            sql.addSql("           and SML_ACCOUNT_USER.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("         )");
            sql.addSql("       )");
            sql.addSql("   or (SML_FILTER.SFT_TYPE = ? ");
            sql.addSql("   and ");
            sql.addSql("        SML_FILTER.SAC_SID = ?)) ");
            sql.addSql(" and ");
            sql.addSql("   SML_FILTER_SORT.SAC_SID = ? ");
            sql.addSql(" order by ");
            sql.addSql("   SML_FILTER_SORT.SFS_SORT asc ");
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(GSConstSmail.LABELTYPE_ALL);
            sql.addIntValue(sml200account);
            sql.addIntValue(sml200account);
            sql.addIntValue(GSConstSmail.LABELTYPE_ONES);
            sql.addIntValue(sml200account);
            sql.addIntValue(sml200account);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Sml330FilterDataModel model = new Sml330FilterDataModel();
                model.setFilterSid(rs.getInt("SFT_SID"));
                model.setFilterName(rs.getString("SFT_NAME"));
                model.setFilterSort(rs.getInt("SFS_SORT"));
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
     * @param sftSid フィルターSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public int deleteFilterCondition(int sftSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SML_FILTER_CONDITION");
            sql.addSql(" where ");
            sql.addSql("   SFT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sftSid);

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
     * @param sftSid フィルターSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public int deleteFilterSortCondition(int sftSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SML_FILTER_SORT");
            sql.addSql(" where ");
            sql.addSql("   SFT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sftSid);

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
            sql.addSql("   SML_FILTER_SORT");
            sql.addSql("     set SFS_SORT = case when SFT_SID = ? ");
            sql.addSql("     then ? ");
            sql.addSql("     when SFT_SID = ? ");
            sql.addSql("     then ? ");
            sql.addSql("     else SFS_SORT end");

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
