package jp.groupsession.v2.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.model.BbsForMemModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>BBS_FOR_MEM Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class BbsForMemDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(BbsForMemDao.class);

    /**
     * <p>Default Constructor
     */
    public BbsForMemDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public BbsForMemDao(Connection con) {
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
            sql.addSql("drop table BBS_FOR_MEM");

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
            sql.addSql(" create table BBS_FOR_MEM (");
            sql.addSql("   BFI_SID NUMBER(10,0) not null,");
            sql.addSql("   USR_SID NUMBER(10,0) not null,");
            sql.addSql("   GRP_SID NUMBER(10,0) not null,");
            sql.addSql("   BFM_AUTH NUMBER(10,0) not null,");
            sql.addSql("   primary key (BFI_SID,USR_SID)");
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
     * <p>Insert BBS_FOR_MEM Data Bindding JavaBean
     * @param bean BBS_FOR_MEM Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(BbsForMemModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" BBS_FOR_MEM(");
            sql.addSql("   BFI_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   GRP_SID,");
            sql.addSql("   BFM_AUTH");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getBfiSid());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getGrpSid());
            sql.addIntValue(bean.getBfmAuth());
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
     * <p>フォーラムメンバーの更新を行う
     * @param bfiSid フォーラムSID
     * @param usrGrpSidList ユーザSID・グループSID一覧
     * @param bfmAuth 権限種別
     * @throws SQLException SQL実行例外
     */
    public void insert(int bfiSid, String[] usrGrpSidList, int bfmAuth) throws SQLException {

        if (usrGrpSidList == null || usrGrpSidList.length <= 0) {
            return;
        }

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //新規登録
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert into ");
            sql.addSql(" BBS_FOR_MEM (");
            sql.addSql("   BFI_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   GRP_SID,");
            sql.addSql("   BFM_AUTH");
            sql.addSql(" )");
            sql.addSql(" values (");
            sql.addSql("   " + String.valueOf(bfiSid) + ",");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());

            for (String userGrpSid : usrGrpSidList) {
                String str = NullDefault.getString(userGrpSid, "-1");
                if (str.contains(GSConstBulletin.FORUM_MEMBER_GROUP_HEADSTR)) {
                    //グループ
                    userGrpSid = (str.substring(1, str.length()));
                    pstmt.setInt(1, -1);
                    pstmt.setInt(2, Integer.parseInt(userGrpSid));
                    pstmt.setInt(3, bfmAuth);
                } else {
                    //ユーザ
                    userGrpSid = str;
                    pstmt.setInt(1, Integer.parseInt(userGrpSid));
                    pstmt.setInt(2, -1);
                    pstmt.setInt(3, bfmAuth);
                }

                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <p>Select BBS_FOR_MEM All Data
     * @return List in BBS_FOR_MEMModel
     * @throws SQLException SQL実行例外
     */
    public List < BbsForMemModel > select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List < BbsForMemModel > ret = new ArrayList < BbsForMemModel >();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   BFI_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   GRP_SID,");
            sql.addSql("   BFM_AUTH");
            sql.addSql(" from ");
            sql.addSql("   BBS_FOR_MEM");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getBbsForMemFromRs(rs));
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
     * <p>指定されたフォーラムSIDとユーザSIDの組み合わせが存在するかを確認する
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @return 結果 true:存在する false:存在しない
     * @throws SQLException SQL実行例外
     */
    public boolean existBbsForMem(int bfiSid, int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean ret = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
//            sql.addSql(" select");
//            sql.addSql("   BFI_SID");
//            sql.addSql(" from");
//            sql.addSql("   BBS_FOR_MEM");
//            sql.addSql(" where ");
//            sql.addSql("   BFI_SID=?");
//            sql.addSql(" and");
//            sql.addSql("    exists");
//            sql.addSql("    (");
            sql.addSql(" select");
            sql.addSql("   BBS_FOR_MEM.BFI_SID as BFI_SID");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_MEM");
            sql.addSql("   left join");
            sql.addSql("     CMN_BELONGM");
            sql.addSql("   on");
            sql.addSql("      BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("   where");
            sql.addSql("      BFI_SID=?");
            sql.addSql("   and");
            sql.addSql("     (");
            sql.addSql("       BBS_FOR_MEM.USR_SID = ?");
            sql.addSql("     or");
            sql.addSql("       CMN_BELONGM.USR_SID = ?");
            sql.addSql("     )");
//            sql.addSql("    )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bfiSid);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);

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
     * <p>指定されたフォーラムSIDとユーザSIDの組み合わせが存在するかを確認する
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @return 結果 true:存在する false:存在しない
     * @throws SQLException SQL実行例外
     */
    public List<BbsForMemModel> getAuthForMem(int bfiSid, int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<BbsForMemModel> ret = new ArrayList<BbsForMemModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   BBS_FOR_MEM.BFI_SID as BFI_SID,");
            sql.addSql("   BBS_FOR_MEM.USR_SID as USR_SID,");
            sql.addSql("   BBS_FOR_MEM.GRP_SID as GRP_SID,");
            sql.addSql("   BBS_FOR_MEM.BFM_AUTH as BFM_AUTH");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_MEM");
            sql.addSql("   left join");
            sql.addSql("     CMN_BELONGM");
            sql.addSql("   on");
            sql.addSql("      BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("   where");
            sql.addSql("      BFI_SID=?");
            sql.addSql("   and");
            sql.addSql("     (");
            sql.addSql("       BBS_FOR_MEM.USR_SID = ?");
            sql.addSql("     or");
            sql.addSql("       CMN_BELONGM.USR_SID = ?");
            sql.addSql("     )");
//            sql.addSql("    )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bfiSid);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getBbsForMemFromRs(rs));
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
     * <br>[機  能] フォーラムメンバーのSID一覧を取得する
     * <br>[解  説]
     * <br>[備  考] グループSIDの場合は 「"G" + グループSID」をIDとする
     * @param bfiSid フォーラムSID
     * @return ユーザSID
     * @throws SQLException SQL実行例外
     */
    public String[] getForumMemberId(int bfiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList < String > list = new ArrayList < String >();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   GRP_SID,");
            sql.addSql("   USR_SID");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_MEM");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int grpSid = rs.getInt("GRP_SID");
                if (grpSid < 0) {
                    list.add(rs.getString("USR_SID"));
                } else {
                    list.add(GSConstBulletin.FORUM_MEMBER_GROUP_HEADSTR
                            + String.valueOf(grpSid));
                }
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * <p>ユーザデータ一覧を取得する
     * @param bfiSid フォーラムSID
     * @return ユーザSID
     * @throws SQLException SQL実行例外
     */
    public List<BbsForMemModel> getUsrData(int bfiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<BbsForMemModel> ret = new ArrayList<BbsForMemModel>();
        BbsForMemModel bfmMdl = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_SID,");
            sql.addSql("   GRP_SID,");
            sql.addSql("   BFM_AUTH");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_MEM");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                bfmMdl = new BbsForMemModel();
                bfmMdl.setUsrSid(rs.getInt("USR_SID"));
                bfmMdl.setGrpSid(rs.getInt("GRP_SID"));
                bfmMdl.setBfmAuth(rs.getInt("BFM_AUTH"));

                ret.add(bfmMdl);
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
     * <p>Delete BBS_FOR_MEM
     * @param bean BBS_FOR_MEM Model
     * @return delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(BbsForMemModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_MEM");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getBfiSid());
            sql.addIntValue(bean.getUsrSid());

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
     * <p>Delete BBS_FOR_MEM
     * @param usrSid ユーザSID
     * @return delete count
     * @throws SQLException SQL実行例外
     */
    public int delete(int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_MEM");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(usrSid);

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
     * <p>フォーラムメンバーの削除を行う
     * @param bfiSid フォーラムSID
     * @return delete count
     * @throws SQLException SQL実行例外
     */
    public int deleteForumMem(int bfiSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_MEM");
            sql.addSql(" where ");
            sql.addSql("   BFI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bfiSid);

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
     * <p>Create BBS_FOR_MEM Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created BbsForMemModel
     * @throws SQLException SQL実行例外
     */
    private BbsForMemModel __getBbsForMemFromRs(ResultSet rs) throws SQLException {
        BbsForMemModel bean = new BbsForMemModel();
        bean.setBfiSid(rs.getInt("BFI_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setGrpSid(rs.getInt("GRP_SID"));
        bean.setBfmAuth(rs.getInt("BFM_AUTH"));

        return bean;
    }
}
