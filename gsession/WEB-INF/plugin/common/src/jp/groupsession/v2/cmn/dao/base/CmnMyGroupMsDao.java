package jp.groupsession.v2.cmn.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.dao.MyGroupDao;
import jp.groupsession.v2.cmn.model.base.CmnMyGroupMsModel;

/**
 * <p>CMN_MY_GROUP_MS Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class CmnMyGroupMsDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(CmnMyGroupMsDao.class);

    /**
     * <p>Default Constructor
     */
    public CmnMyGroupMsDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public CmnMyGroupMsDao(Connection con) {
        super(con);
    }

    /**
     * <p>Insert CMN_MY_GROUP_MS Data Bindding JavaBean
     * @param bean CMN_MY_GROUP_MS Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(CmnMyGroupMsModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" CMN_MY_GROUP_MS(");
            sql.addSql("   USR_SID,");
            sql.addSql("   MGP_SID,");
            sql.addSql("   MGM_SID");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getMgpSid());
            sql.addIntValue(bean.getMgmSid());
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
     * <p>Update CMN_MY_GROUP_MS Data Bindding JavaBean
     * @param bean CMN_MY_GROUP_MS Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return int 更新件数
     */
    public int update(CmnMyGroupMsModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" set ");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");
            sql.addSql(" and");
            sql.addSql("   MGP_SID=?");
            sql.addSql(" and");
            sql.addSql("   MGM_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            //where
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getMgpSid());
            sql.addIntValue(bean.getMgmSid());

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
     * <br>[機  能] マイグループSIDからマイグループ情報明細を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param groupSid マイグループSID
     * @return List in CMN_MY_GROUP_MSModel
     * @throws SQLException SQL実行例外
     */
    public List<CmnMyGroupMsModel> getMyGroupMsInfo(int groupSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<CmnMyGroupMsModel> ret = new ArrayList<CmnMyGroupMsModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   USR_SID,");
            sql.addSql("   MGP_SID,");
            sql.addSql("   MGM_SID");
            sql.addSql(" from ");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   MGP_SID = ?");

            sql.addIntValue(groupSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getCmnMyGroupMsFromRs(rs));
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
     * <br>[機  能] マイグループ情報明細を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return List in CMN_MY_GROUP_MSModel
     * @throws SQLException SQL実行例外
     */
    public List<CmnMyGroupMsModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<CmnMyGroupMsModel> ret = new ArrayList<CmnMyGroupMsModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   USR_SID,");
            sql.addSql("   MGP_SID,");
            sql.addSql("   MGM_SID");
            sql.addSql(" from ");
            sql.addSql("   CMN_MY_GROUP_MS");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getCmnMyGroupMsFromRs(rs));
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
     * <p>Select CMN_MY_GROUP_MS
     * @param bean CMN_MY_GROUP_MS Model
     * @return CMN_MY_GROUP_MSModel
     * @throws SQLException SQL実行例外
     */
    public CmnMyGroupMsModel select(CmnMyGroupMsModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CmnMyGroupMsModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   USR_SID,");
            sql.addSql("   MGP_SID,");
            sql.addSql("   MGM_SID");
            sql.addSql(" from");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");
            sql.addSql(" and");
            sql.addSql("   MGP_SID=?");
            sql.addSql(" and");
            sql.addSql("   MGM_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getUsrSid());
            sql.addIntValue(bean.getMgpSid());
            sql.addIntValue(bean.getMgmSid());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getCmnMyGroupMsFromRs(rs);
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
     * <br>[機  能] マイグループSID、所属ユーザSIDからマイグループ情報明細を取得する
     * <br>[解  説] 参照権限がない場合、空で返す
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param groupSid マイグループSID
     * @param mgmSid 所属ユーザSID
     * @param iflg true:指定された所属ユーザのみ、false:指定された所属ユーザ以外
     * @return List in CMN_MY_GROUP_MSModel
     * @throws SQLException SQL実行例外
     */
    public List<CmnMyGroupMsModel> getMyGroupMsInfo(int userSid, int groupSid,
            String[] mgmSid, boolean iflg) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<CmnMyGroupMsModel> ret = new ArrayList<CmnMyGroupMsModel>();
        con = getCon();
        MyGroupDao mgDao = new MyGroupDao(con);
        if (!mgDao.isAbleViewMyGroup(groupSid, userSid)) {
            return ret;
        }
        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   USR_SID,");
            sql.addSql("   MGP_SID,");
            sql.addSql("   MGM_SID");
            sql.addSql(" from ");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   MGP_SID = ?");
            sql.addIntValue(groupSid);

            //ユーザSIDが指定されている場合
            if (mgmSid != null && mgmSid.length > 0) {

                sql.addSql(" and");
                if (mgmSid.length == 1) {
                    if (iflg) {
                        sql.addSql("   MGM_SID = ?");
                    } else {
                        sql.addSql("   MGM_SID <> ?");
                    }
                    sql.addIntValue(Integer.parseInt(mgmSid[0]));

                } else {
                    if (iflg) {
                        sql.addSql("   MGM_SID in (");
                    } else {
                        sql.addSql("   MGM_SID not in (");
                    }

                    int lastIdx = mgmSid.length - 1;
                    for (int idx = 0; idx < lastIdx; idx++) {
                        sql.addSql("     ?,");
                        sql.addIntValue(Integer.parseInt(mgmSid[idx]));
                    }
                    sql.addSql("     ?");
                    sql.addIntValue(Integer.parseInt(mgmSid[lastIdx]));

                    sql.addSql("   )");
                }
            }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getCmnMyGroupMsFromRs(rs));
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
     * <br>[機  能] ユーザSID、マイグループSID、所属ユーザSIDからマイグループ情報明細を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param groupSid マイグループSID
     * @param mgmSid 所属ユーザSID
     * @param iflg true:指定された所属ユーザのみ、false:指定された所属ユーザ以外
     * @return List in CMN_MY_GROUP_MSModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<Integer> getMyGroupUsrSid(int userSid, ArrayList<Integer> groupSid,
            String[] mgmSid, boolean iflg) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   MGM_SID");
            sql.addSql(" from ");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   USR_SID = ?");
            sql.addIntValue(userSid);
            sql.addSql(" and");
            sql.addSql("   MGP_SID in (");
            int lasIdx = groupSid.size() - 1;
            for (int idx = 0; idx < lasIdx; idx++) {
                sql.addSql("     ?,");
                sql.addIntValue(groupSid.get(idx));
            }
            sql.addSql("     ?");
            sql.addIntValue(groupSid.get(lasIdx));
            sql.addSql("   )");

            //ユーザSIDが指定されている場合
            if (mgmSid != null && mgmSid.length > 0) {

                sql.addSql(" and");
                if (mgmSid.length == 1) {
                    if (iflg) {
                        sql.addSql("   MGM_SID = ?");
                    } else {
                        sql.addSql("   MGM_SID <> ?");
                    }
                    sql.addIntValue(Integer.parseInt(mgmSid[0]));

                } else {
                    if (iflg) {
                        sql.addSql("   MGM_SID in (");
                    } else {
                        sql.addSql("   MGM_SID not in (");
                    }

                    int lastIdx = mgmSid.length - 1;
                    for (int idx = 0; idx < lastIdx; idx++) {
                        sql.addSql("     ?,");
                        sql.addIntValue(Integer.parseInt(mgmSid[idx]));
                    }
                    sql.addSql("     ?");
                    sql.addIntValue(Integer.parseInt(mgmSid[lastIdx]));

                    sql.addSql("   )");
                }
            }

            sql.addSql(" group by MGM_SID");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(rs.getInt("MGM_SID"));
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
     * <br>[機  能] ユーザSID、マイグループSID、所属ユーザSIDからマイグループ情報明細を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param groupSid マイグループSID
     * @param mgmSid 除外するユーザSID
     * @return List in CMN_MY_GROUP_MSModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<Integer> getMyGroupUsrSid2(int userSid, ArrayList<Integer> groupSid,
            ArrayList<Integer> mgmSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   MGM_SID");
            sql.addSql(" from ");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   USR_SID = ?");
            sql.addIntValue(userSid);
            sql.addSql(" and");
            sql.addSql("   MGP_SID in (");
            int lasIdx = groupSid.size() - 1;
            for (int idx = 0; idx < lasIdx; idx++) {
                sql.addSql("     ?,");
                sql.addIntValue(groupSid.get(idx));
            }
            sql.addSql("     ?");
            sql.addIntValue(groupSid.get(lasIdx));
            sql.addSql("   )");

            if (mgmSid != null && mgmSid.size() > 0) {
                sql.addSql(" and ");
                sql.addSql("   MGM_SID not in( ");
                for (int i = 0; i < mgmSid.size(); i++) {
                    if (i == 0) {
                        sql.addSql("  ? ");
                    } else {
                        sql.addSql("  ,? ");
                    }
                    sql.addIntValue(mgmSid.get(i).intValue());
                }
                sql.addSql("  ) ");
            }

            sql.addSql(" group by MGM_SID");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(rs.getInt("MGM_SID"));
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
     *
     * <br>[機  能]グループSIDから所属するユーザリストを返却します
     * <br>[解  説]参照権限がない場合は空で返す
     * <br>[備  考]
     * @param usid ユーザSID
     * @param gsid グループSID
     * @return List in CMN_BELONGMModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<Integer> selectMyGroupUsers(int usid, int gsid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();
        MyGroupDao mgDao = new MyGroupDao(con);
        if (!mgDao.isAbleViewMyGroup(gsid, usid)) {
            return ret;
        }

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   MGM_SID");
            sql.addSql(" from");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   MGP_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(gsid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(new Integer(rs.getInt("MGM_SID")));
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
     * <p>ユーザSIDから所属するマイグループSIDを返却します
     * @param usid ユーザSID
     * @return List in CMN_BELONGMModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<Integer> getMyGroupSid(int usid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   MGP_SID");
            sql.addSql(" from");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");
            sql.addSql(" group by MGP_SID");
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(usid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(new Integer(rs.getInt("MGP_SID")));
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
     * <br>[機  能] マイグループSIDを指定してマイグループ情報明細を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param groupSid マイグループSID
     * @throws SQLException SQL実行例外
     * @return int 削除件数
     */
    public int deleteGroupMs(int groupSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   MGP_SID = ?");

            sql.addIntValue(groupSid);

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
     * <br>[機  能] マイグループSID(複数)を指定してマイグループ情報明細を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param groupSid マイグループSID
     * @throws SQLException SQL実行例外
     * @return int 削除件数
     */
    public int deleteGroupMs(String[] groupSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        if (groupSid == null) {
            return count;
        }
        if (groupSid.length < 1) {
            return count;
        }

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   MGP_SID in (");

            for (int i = 0; i < groupSid.length; i++) {
                sql.addSql("     ? ");
                sql.addIntValue(NullDefault.getInt(groupSid[i], 0));

                if (i < groupSid.length - 1) {
                    sql.addSql("     , ");
                }
            }
            sql.addSql("   )");

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
     * <br>[機  能] ユーザSIDを指定してマイグループ情報明細を削除する
     * <br>[解  説] ユーザSIDが登録ユーザSID、または所属ユーザSIDと一致する場合削除
     * <br>[備  考]
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     * @return int 削除件数
     */
    public int deleteGroup(int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   CMN_MY_GROUP_MS");
            sql.addSql(" where ");
            sql.addSql("   USR_SID = ?");
            sql.addSql(" or ");
            sql.addSql("   MGM_SID = ?");

            sql.addIntValue(userSid);
            sql.addIntValue(userSid);

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
     * <p>Create CMN_MY_GROUP_MS Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created CmnMyGroupMsModel
     * @throws SQLException SQL実行例外
     */
    private CmnMyGroupMsModel __getCmnMyGroupMsFromRs(ResultSet rs) throws SQLException {
        CmnMyGroupMsModel bean = new CmnMyGroupMsModel();
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setMgpSid(rs.getInt("MGP_SID"));
        bean.setMgmSid(rs.getInt("MGM_SID"));
        return bean;
    }
}
