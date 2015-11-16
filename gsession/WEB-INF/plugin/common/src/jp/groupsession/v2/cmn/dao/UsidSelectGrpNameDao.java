package jp.groupsession.v2.cmn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ユーザ情報ポップアップの所属グループ一覧表示に使用するDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class UsidSelectGrpNameDao extends AbstractDao {

        /** Logging インスタンス */
        private static Log log__ = LogFactory.getLog(SltUserPerGroupDao.class);

        /**
         * <p>Default Constructor
         */
        public UsidSelectGrpNameDao() {
        }

        /**
         * <p>Set Connection
         * @param con Connection
         */
        public UsidSelectGrpNameDao(Connection con) {
            super(con);
        }


        /**
         * <br>[機  能] ユーザSIDからグループ名とグループIDを取得します
         * <br>[解  説] ユーザSIDを引数で与えると、グループ名とグループIDが取得できます
         * <br>[備  考]
         * @param usid ユーザSID
         * @return ret List GroupModel
         * @throws SQLException SQL実行時例外
         */
        public ArrayList<GroupModel> selectGroupNmList(int usid) throws SQLException {

            PreparedStatement pstmt = null;
            ResultSet rs = null;
            Connection con = null;
            ArrayList<GroupModel> ret = new ArrayList<GroupModel>();
            con = getCon();

            try {
                //SQL文
                SqlBuffer sql = new SqlBuffer();
                sql.addSql(" select ");
                sql.addSql("   CMN_GROUPM.GRP_SID as GRP_SID, ");
                sql.addSql("   CMN_GROUPM.GRP_NAME as GRP_NAME, ");
                sql.addSql("   CMN_BELONGM.BEG_GRPKBN as BEG_GRPKBN");
                sql.addSql(" from ");
                sql.addSql("   CMN_GROUPM, ");
                sql.addSql("   CMN_BELONGM ");
                sql.addSql(" where ");
                sql.addSql("   CMN_BELONGM.USR_SID = ? ");
                sql.addSql(" and ");
                sql.addSql("   CMN_BELONGM.GRP_SID = CMN_GROUPM.GRP_SID ");
                sql.addSql(" order by ");
                sql.addSql("   GRP_NAME ");

                pstmt = con.prepareStatement(sql.toSqlString());
                sql.addIntValue(usid);

                log__.info(sql.toLogString());
                sql.setParameter(pstmt);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    ret.add(__getUsidSelectFromRs(rs));
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
         * <br>[機  能] ユーザSIDから所属するグループ名とグループIDを取得します
         * <br>[解  説] ユーザSIDを引数で与えると、グループ名とグループIDが取得できます
         * <br>[備  考]
         * @param usid ユーザSID
         * @return ret List GroupModel
         * @throws SQLException SQL実行時例外
         */
        public ArrayList<GroupModel> selectGroupNmListOrderbyClass(int usid) throws SQLException {

            PreparedStatement pstmt = null;
            ResultSet rs = null;
            Connection con = null;
            ArrayList<GroupModel> ret = new ArrayList<GroupModel>();
            con = getCon();

            try {
                //SQL文
                SqlBuffer sql = new SqlBuffer();
                sql.addSql(" select");
                sql.addSql("  CL.SID as GROUPSID,");
                sql.addSql("  CL.NAME as GROUPNAME");
                sql.addSql(" from");
                sql.addSql(" (");
                sql.addSql(" select");
                sql.addSql("    GC.GCL_SID1 as GCL_SID1,");
                sql.addSql("    GC.GCL_SID2 as GCL_SID2,");
                sql.addSql("    GC.GCL_SID3 as GCL_SID3,");
                sql.addSql("    GC.GCL_SID4 as GCL_SID4,");
                sql.addSql("    GC.GCL_SID5 as GCL_SID5,");
                sql.addSql("    GC.GCL_SID6 as GCL_SID6,");
                sql.addSql("    GC.GCL_SID7 as GCL_SID7,");
                sql.addSql("    GC.GCL_SID8 as GCL_SID8,");
                sql.addSql("    GC.GCL_SID9 as GCL_SID9,");
                sql.addSql("    GC.GCL_SID10 as GCL_SID10,");
                sql.addSql("    GC.GCL_AUID,");
                sql.addSql("    GC.GCL_ADATE,");
                sql.addSql("    GC.GCL_EUID,");
                sql.addSql("    GC.GCL_EDATE,");
                sql.addSql("    GI1.GRP_NAME as GNAME1,");
                sql.addSql("    GI2.GRP_NAME as GNAME2,");
                sql.addSql("    GI3.GRP_NAME as GNAME3,");
                sql.addSql("    GI4.GRP_NAME as GNAME4,");
                sql.addSql("    GI5.GRP_NAME as GNAME5,");
                sql.addSql("    GI6.GRP_NAME as GNAME6,");
                sql.addSql("    GI7.GRP_NAME as GNAME7,");
                sql.addSql("    GI8.GRP_NAME as GNAME8,");
                sql.addSql("    GI9.GRP_NAME as GNAME9,");
                sql.addSql("    GI10.GRP_NAME as GNAME10,");
                sql.addSql("    coalesce(GI1.GRP_NAME, '') as s1,");
                sql.addSql("    coalesce(GI2.GRP_NAME, '') as s2,");
                sql.addSql("    coalesce(GI3.GRP_NAME, '') as s3,");
                sql.addSql("    coalesce(GI4.GRP_NAME, '') as s4,");
                sql.addSql("    coalesce(GI5.GRP_NAME, '') as s5,");
                sql.addSql("    coalesce(GI6.GRP_NAME, '') as s6,");
                sql.addSql("    coalesce(GI7.GRP_NAME, '') as s7,");
                sql.addSql("    coalesce(GI8.GRP_NAME, '') as s8,");
                sql.addSql("    coalesce(GI9.GRP_NAME, '') as s9,");
                sql.addSql("    coalesce(GI10.GRP_NAME, '') as s10,");
                sql.addSql("    (case when GC.GCL_SID10 > 0 then GC.GCL_SID10");
                sql.addSql("          when GC.GCL_SID9 > 0 then GC.GCL_SID9");
                sql.addSql("          when GC.GCL_SID8 > 0 then GC.GCL_SID8");
                sql.addSql("          when GC.GCL_SID7 > 0 then GC.GCL_SID7");
                sql.addSql("          when GC.GCL_SID6 > 0 then GC.GCL_SID6");
                sql.addSql("          when GC.GCL_SID5 > 0 then GC.GCL_SID5");
                sql.addSql("          when GC.GCL_SID4 > 0 then GC.GCL_SID4");
                sql.addSql("          when GC.GCL_SID3 > 0 then GC.GCL_SID3");
                sql.addSql("          when GC.GCL_SID2 > 0 then GC.GCL_SID2");
                sql.addSql("          when GC.GCL_SID1 > 0 then GC.GCL_SID1");
                sql.addSql("          else -1");
                sql.addSql("    end) SID,");
                sql.addSql("    (case when GI10.GRP_NAME is not null then GI10.GRP_NAME");
                sql.addSql("          when GI9.GRP_NAME is not null then GI9.GRP_NAME");
                sql.addSql("          when GI8.GRP_NAME is not null then GI8.GRP_NAME");
                sql.addSql("          when GI7.GRP_NAME is not null then GI7.GRP_NAME");
                sql.addSql("          when GI6.GRP_NAME is not null then GI6.GRP_NAME");
                sql.addSql("          when GI5.GRP_NAME is not null then GI5.GRP_NAME");
                sql.addSql("          when GI4.GRP_NAME is not null then GI4.GRP_NAME");
                sql.addSql("          when GI3.GRP_NAME is not null then GI3.GRP_NAME");
                sql.addSql("          when GI2.GRP_NAME is not null then GI2.GRP_NAME");
                sql.addSql("          when GI1.GRP_NAME is not null then GI1.GRP_NAME");
                sql.addSql("          else ''");
                sql.addSql("    end) as NAME");
                sql.addSql(" from");
                sql.addSql("    CMN_GROUP_CLASS GC");
                sql.addSql("    left join CMN_GROUPM GI1 on GC.GCL_SID1 = GI1.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI2 on GC.GCL_SID2 = GI2.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI3 on GC.GCL_SID3 = GI3.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI4 on GC.GCL_SID4 = GI4.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI5 on GC.GCL_SID5 = GI5.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI6 on GC.GCL_SID6 = GI6.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI7 on GC.GCL_SID7 = GI7.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI8 on GC.GCL_SID8 = GI8.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI9 on GC.GCL_SID9 = GI9.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI10 on GC.GCL_SID10 = GI10.GRP_SID");
                sql.addSql(" where");
                sql.addSql("    GI1.GRP_JKBN = 0");
                sql.addSql(" or    GI2.GRP_JKBN = 0");
                sql.addSql(" or    GI3.GRP_JKBN = 0");
                sql.addSql(" or    GI4.GRP_JKBN = 0");
                sql.addSql(" or    GI5.GRP_JKBN = 0");
                sql.addSql(" or    GI5.GRP_JKBN = 0");
                sql.addSql(" or    GI6.GRP_JKBN = 0");
                sql.addSql(" or    GI7.GRP_JKBN = 0");
                sql.addSql(" or    GI8.GRP_JKBN = 0");
                sql.addSql(" or    GI9.GRP_JKBN = 0");
                sql.addSql(" or    GI10.GRP_JKBN = 0");
                sql.addSql(" ) CL,");
                sql.addSql("  CMN_BELONGM");
                sql.addSql(" where");
                sql.addSql("   CMN_BELONGM.USR_SID=?");
                sql.addSql(" and");
                sql.addSql("   CMN_BELONGM.GRP_SID=CL.SID");
                sql.addSql(" order by");
                sql.addSql("    CL.s1,");
                sql.addSql("    CL.s2,");
                sql.addSql("    CL.s3,");
                sql.addSql("    CL.s4,");
                sql.addSql("    CL.s5,");
                sql.addSql("    CL.s6,");
                sql.addSql("    CL.s7,");
                sql.addSql("    CL.s8,");
                sql.addSql("    CL.s9,");
                sql.addSql("    CL.s10,");
                sql.addSql("    CL.GCL_SID1,");
                sql.addSql("    CL.GCL_SID2,");
                sql.addSql("    CL.GCL_SID3,");
                sql.addSql("    CL.GCL_SID4,");
                sql.addSql("    CL.GCL_SID5,");
                sql.addSql("    CL.GCL_SID6,");
                sql.addSql("    CL.GCL_SID7,");
                sql.addSql("    CL.GCL_SID8,");
                sql.addSql("    CL.GCL_SID9,");
                sql.addSql("    CL.GCL_SID10");
                log__.info(sql.toLogString());
                pstmt = con.prepareStatement(sql.toSqlString());
                sql.addIntValue(usid);
                sql.setParameter(pstmt);

                log__.info(sql.toLogString());
                rs = pstmt.executeQuery();
                GroupModel gpMdl = null;
                while (rs.next()) {
                    gpMdl = new GroupModel();
                    gpMdl.setGroupSid(rs.getInt("GROUPSID"));
                    gpMdl.setGroupName(rs.getString("GROUPNAME"));
                    ret.add(gpMdl);
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
         * <br>[機  能] 指定グループSIDからグループ名とグループIDを取得します
         * <br>[解  説]
         * <br>[備  考]
         * @param sidlist グループSID
         * @return ret List GroupModel
         * @throws SQLException SQL実行時例外
         */
        public ArrayList<GroupModel> selectGroupNmListOrderbyClass(ArrayList<Integer> sidlist)
        throws SQLException {

            PreparedStatement pstmt = null;
            ResultSet rs = null;
            Connection con = null;
            ArrayList<GroupModel> ret = new ArrayList<GroupModel>();
            con = getCon();

            try {
                //SQL文
                SqlBuffer sql = new SqlBuffer();
                sql.addSql(" select");
                sql.addSql("  CL.SID as GROUPSID,");
                sql.addSql("  CL.NAME as GROUPNAME");
                sql.addSql(" from");
                sql.addSql(" (");
                sql.addSql(" select");
                sql.addSql("    GC.GCL_SID1 as GCL_SID1,");
                sql.addSql("    GC.GCL_SID2 as GCL_SID2,");
                sql.addSql("    GC.GCL_SID3 as GCL_SID3,");
                sql.addSql("    GC.GCL_SID4 as GCL_SID4,");
                sql.addSql("    GC.GCL_SID5 as GCL_SID5,");
                sql.addSql("    GC.GCL_SID6 as GCL_SID6,");
                sql.addSql("    GC.GCL_SID7 as GCL_SID7,");
                sql.addSql("    GC.GCL_SID8 as GCL_SID8,");
                sql.addSql("    GC.GCL_SID9 as GCL_SID9,");
                sql.addSql("    GC.GCL_SID10 as GCL_SID10,");
                sql.addSql("    GC.GCL_AUID,");
                sql.addSql("    GC.GCL_ADATE,");
                sql.addSql("    GC.GCL_EUID,");
                sql.addSql("    GC.GCL_EDATE,");
                sql.addSql("    GI1.GRP_NAME as GNAME1,");
                sql.addSql("    GI2.GRP_NAME as GNAME2,");
                sql.addSql("    GI3.GRP_NAME as GNAME3,");
                sql.addSql("    GI4.GRP_NAME as GNAME4,");
                sql.addSql("    GI5.GRP_NAME as GNAME5,");
                sql.addSql("    GI6.GRP_NAME as GNAME6,");
                sql.addSql("    GI7.GRP_NAME as GNAME7,");
                sql.addSql("    GI8.GRP_NAME as GNAME8,");
                sql.addSql("    GI9.GRP_NAME as GNAME9,");
                sql.addSql("    GI10.GRP_NAME as GNAME10,");
                sql.addSql("    coalesce(GI1.GRP_NAME, '') as s1,");
                sql.addSql("    coalesce(GI2.GRP_NAME, '') as s2,");
                sql.addSql("    coalesce(GI3.GRP_NAME, '') as s3,");
                sql.addSql("    coalesce(GI4.GRP_NAME, '') as s4,");
                sql.addSql("    coalesce(GI5.GRP_NAME, '') as s5,");
                sql.addSql("    coalesce(GI6.GRP_NAME, '') as s6,");
                sql.addSql("    coalesce(GI7.GRP_NAME, '') as s7,");
                sql.addSql("    coalesce(GI8.GRP_NAME, '') as s8,");
                sql.addSql("    coalesce(GI9.GRP_NAME, '') as s9,");
                sql.addSql("    coalesce(GI10.GRP_NAME, '') as s10,");
                sql.addSql("   (case when GC.GCL_SID10 >= 0 then GC.GCL_SID10");
                sql.addSql("          when GC.GCL_SID9 >= 0 then GC.GCL_SID9");
                sql.addSql("          when GC.GCL_SID8 >= 0 then GC.GCL_SID8");
                sql.addSql("          when GC.GCL_SID7 >= 0 then GC.GCL_SID7");
                sql.addSql("          when GC.GCL_SID6 >= 0 then GC.GCL_SID6");
                sql.addSql("          when GC.GCL_SID5 >= 0 then GC.GCL_SID5");
                sql.addSql("          when GC.GCL_SID4 >= 0 then GC.GCL_SID4");
                sql.addSql("          when GC.GCL_SID3 >= 0 then GC.GCL_SID3");
                sql.addSql("          when GC.GCL_SID2 >= 0 then GC.GCL_SID2");
                sql.addSql("          when GC.GCL_SID1 >= 0 then GC.GCL_SID1");
                sql.addSql("          else -1");
                sql.addSql("    end) as SID,");
                sql.addSql("    (case when GI10.GRP_NAME is not null then GI10.GRP_NAME");
                sql.addSql("          when GI9.GRP_NAME is not null then GI9.GRP_NAME");
                sql.addSql("          when GI8.GRP_NAME is not null then GI8.GRP_NAME");
                sql.addSql("          when GI7.GRP_NAME is not null then GI7.GRP_NAME");
                sql.addSql("          when GI6.GRP_NAME is not null then GI6.GRP_NAME");
                sql.addSql("          when GI5.GRP_NAME is not null then GI5.GRP_NAME");
                sql.addSql("          when GI4.GRP_NAME is not null then GI4.GRP_NAME");
                sql.addSql("          when GI3.GRP_NAME is not null then GI3.GRP_NAME");
                sql.addSql("          when GI2.GRP_NAME is not null then GI2.GRP_NAME");
                sql.addSql("          when GI1.GRP_NAME is not null then GI1.GRP_NAME");
                sql.addSql("          else ''");
                sql.addSql("    end) as NAME");
                sql.addSql(" from");
                sql.addSql("    ");
                sql.addSql("    CMN_GROUP_CLASS GC");
                sql.addSql("    left join CMN_GROUPM GI1 on GC.GCL_SID1 = GI1.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI2 on GC.GCL_SID2 = GI2.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI3 on GC.GCL_SID3 = GI3.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI4 on GC.GCL_SID4 = GI4.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI5 on GC.GCL_SID5 = GI5.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI6 on GC.GCL_SID6 = GI6.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI7 on GC.GCL_SID7 = GI7.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI8 on GC.GCL_SID8 = GI8.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI9 on GC.GCL_SID9 = GI9.GRP_SID");
                sql.addSql("    left join CMN_GROUPM GI10 on GC.GCL_SID10 = GI10.GRP_SID");
                sql.addSql(" where");
                sql.addSql("    GI1.GRP_JKBN = 0");
                sql.addSql(" or    GI2.GRP_JKBN = 0");
                sql.addSql(" or    GI3.GRP_JKBN = 0");
                sql.addSql(" or    GI4.GRP_JKBN = 0");
                sql.addSql(" or    GI5.GRP_JKBN = 0");
                sql.addSql(" or    GI6.GRP_JKBN = 0");
                sql.addSql(" or    GI7.GRP_JKBN = 0");
                sql.addSql(" or    GI8.GRP_JKBN = 0");
                sql.addSql(" or    GI9.GRP_JKBN = 0");
                sql.addSql(" or    GI10.GRP_JKBN = 0");
                sql.addSql(" ) CL,");
                sql.addSql("  CMN_GROUPM");
                sql.addSql(" where");
                sql.addSql("   CMN_GROUPM.GRP_SID=CL.SID");

                sql.addSql(" and");
                sql.addSql("   CMN_GROUPM.GRP_SID in( -1");
                for (int i = 0; i < sidlist.size(); i++) {
                    sql.addSql(",?");
                    sql.addIntValue(sidlist.get(i).intValue());
                }
                sql.addSql(" ) ");


                sql.addSql(" order by");
                sql.addSql("    CL.s1,");
                sql.addSql("    CL.s2,");
                sql.addSql("    CL.s3,");
                sql.addSql("    CL.s4,");
                sql.addSql("    CL.s5,");
                sql.addSql("    CL.s6,");
                sql.addSql("    CL.s7,");
                sql.addSql("    CL.s8,");
                sql.addSql("    CL.s9,");
                sql.addSql("    CL.s10,");
                sql.addSql("    CL.GCL_SID1,");
                sql.addSql("    CL.GCL_SID2,");
                sql.addSql("    CL.GCL_SID3,");
                sql.addSql("    CL.GCL_SID4,");
                sql.addSql("    CL.GCL_SID5,");
                sql.addSql("    CL.GCL_SID6,");
                sql.addSql("    CL.GCL_SID7,");
                sql.addSql("    CL.GCL_SID8,");
                sql.addSql("    CL.GCL_SID9,");
                sql.addSql("    CL.GCL_SID10");
                log__.info(sql.toLogString());
                pstmt = con.prepareStatement(sql.toSqlString());
                sql.setParameter(pstmt);

                log__.info(sql.toLogString());
                rs = pstmt.executeQuery();
                GroupModel gpMdl = null;
                while (rs.next()) {
                    gpMdl = new GroupModel();
                    gpMdl.setGroupSid(rs.getInt("GROUPSID"));
                    gpMdl.setGroupName(rs.getString("GROUPNAME"));
                    ret.add(gpMdl);
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
         * <br>[機  能] 結果をセットします
         * <br>[解  説]
         * <br>[備  考]
         * @param rs ResultSet
         * @return bean GroupModel
         * @throws SQLException SQL実行時例外
         */
        private GroupModel __getUsidSelectFromRs(ResultSet rs) throws SQLException {
            GroupModel bean = new GroupModel();
            bean.setGroupSid(rs.getInt("GRP_SID"));
            bean.setGroupName(rs.getString("GRP_NAME"));
            bean.setGrpKbn(rs.getInt("BEG_GRPKBN"));
            return bean;
        }
}