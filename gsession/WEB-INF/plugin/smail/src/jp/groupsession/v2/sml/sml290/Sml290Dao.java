package jp.groupsession.v2.sml.sml290;

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
import jp.groupsession.v2.sml.model.LabelDataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ショートメール 管理者設定 ラベル管理画面で使用するDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sml290Dao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sml290Dao.class);

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     */
    public Sml290Dao(Connection con) {
        super(con);
    }

    /**
     * <br>[機  能] アカウントリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public List<AccountDataModel> getAccountList(int userSid)
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
            sql.addSql("   left join");
            sql.addSql("     (");
            sql.addSql("       select SAC_SID, SAS_SORT");
            sql.addSql("       from SML_ACCOUNT_SORT");
            sql.addSql("       where USR_SID = ?");
            sql.addSql("     ) ACCOUNT_SORT");
            sql.addSql("   on");
            sql.addSql("     SML_ACCOUNT.SAC_SID = ACCOUNT_SORT.SAC_SID");
            sql.addSql(" where");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("      (");
            sql.addSql("         SAC_TYPE = ?");
            sql.addSql("       and");
            sql.addSql("         USR_SID = ?");
            sql.addSql("      )");
            sql.addSql("      or ");
            sql.addSql("      (");
            sql.addSql("         exists ( ");
            sql.addSql("           select SAC_SID from SML_ACCOUNT_USER ");
            sql.addSql("           where");
            sql.addSql("           GRP_SID in ( ");
            sql.addSql("             select GRP_SID from CMN_BELONGM ");
            sql.addSql("             where USR_SID = ? ");
            sql.addSql("           )");
            sql.addSql("         and ");
            sql.addSql("           SML_ACCOUNT.SAC_SID = SML_ACCOUNT_USER.SAC_SID ");
            sql.addSql("         and ");
            sql.addSql("           SML_ACCOUNT_USER.USR_SID IS NULL ");
            sql.addSql("         )");
            sql.addSql("      )");
            sql.addSql("      or ");
            sql.addSql("      (");
            sql.addSql("         exists ( ");
            sql.addSql("           select SAC_SID from SML_ACCOUNT_USER ");
            sql.addSql("           where");
            sql.addSql("             USR_SID = ? ");
            sql.addSql("           and ");
            sql.addSql("             SML_ACCOUNT.SAC_SID = SML_ACCOUNT_USER.SAC_SID ");
            sql.addSql("           and ");
            sql.addSql("             SML_ACCOUNT_USER.GRP_SID IS NULL ");
            sql.addSql("         )");
            sql.addSql("      )");
            sql.addSql("   )");
            sql.addSql(" order by SAS_SORT asc, SAC_NAME asc");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(userSid);
            sql.addIntValue(GSConstSmail.SAC_JKBN_NORMAL);
            sql.addIntValue(GSConstSmail.SAC_TYPE_NORMAL);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);

            log__.info(sql.toLogString());
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
     * <br>[機  能] ラベル情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param sml110account アカウントSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public List<LabelDataModel> getLabelList(int sml110account)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<LabelDataModel> ret =
            new ArrayList<LabelDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SLB_SID, ");
            sql.addSql("   SLB_NAME, ");
            sql.addSql("   SLB_ORDER ");
            sql.addSql(" from ");
            sql.addSql("   SML_LABEL ");
            sql.addSql(" where ");
            sql.addSql("   (");
            sql.addSql("     SLB_TYPE = ?");
            sql.addSql("   and");
            sql.addSql("     SAC_SID = ?");
            sql.addSql("    )");
            sql.addSql(" or");
            sql.addSql("   (");
            sql.addSql("     SLB_TYPE = ?");
            sql.addSql("   and ");
            sql.addSql("     (");
            sql.addSql("       SML_LABEL.USR_SID in (");
            sql.addSql("         select USR_SID from SML_ACCOUNT_USER");
            sql.addSql("         where SAC_SID = ?");
            sql.addSql("         and coalesce(USR_SID, 0) > 0");
            sql.addSql("       )");
            sql.addSql("      or");
            sql.addSql("       SML_LABEL.USR_SID in (");
            sql.addSql("         select CMN_BELONGM.USR_SID");
            sql.addSql("         from");
            sql.addSql("           SML_ACCOUNT_USER,");
            sql.addSql("           CMN_BELONGM");
            sql.addSql("         where SML_ACCOUNT_USER.SAC_SID = ?");
            sql.addSql("         and coalesce(SML_ACCOUNT_USER.GRP_SID, 0) > 0");
            sql.addSql("         and SML_ACCOUNT_USER.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   )");
            sql.addSql(" order by ");
            sql.addSql("   SLB_ORDER asc ");
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(GSConstSmail.LABELTYPE_ONES);
            sql.addIntValue(sml110account);
            sql.addIntValue(GSConstSmail.LABELTYPE_ALL);
            sql.addIntValue(sml110account);
            sql.addIntValue(sml110account);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                LabelDataModel model = new LabelDataModel();
                model.setLabelSid(rs.getInt("SLB_SID"));
                model.setLabelName(rs.getString("SLB_NAME"));
                model.setLabelOrder(rs.getInt("SLB_ORDER"));
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
     * <br>[機  能] 表示順序を入れ替える
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param motoSid 入れ替え元ラベルSID
     * @param motoSort 入れ替え元ソート順
     * @param sakiSid 入れ替え先ラベルSID
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
            sql.addSql("   SML_LABEL");
            sql.addSql("     set SLB_ORDER = case when SLB_SID = ? ");
            sql.addSql("     then ? ");
            sql.addSql("     when SLB_SID = ? ");
            sql.addSql("     then ? ");
            sql.addSql("     else SLB_ORDER end");

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
