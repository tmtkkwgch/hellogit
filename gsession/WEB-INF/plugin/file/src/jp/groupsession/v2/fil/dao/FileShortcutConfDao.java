package jp.groupsession.v2.fil.dao;

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
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.model.FileDirectoryModel;
import jp.groupsession.v2.fil.model.FileShortcutConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>FILE_SHORTCUT_CONF Data Access Object
 *
 * @author JTS DaoGenerator version 0.5
 */
public class FileShortcutConfDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(FileShortcutConfDao.class);

    /**
     * <p>Default Constructor
     */
    public FileShortcutConfDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public FileShortcutConfDao(Connection con) {
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
            sql.addSql("drop table FILE_SHORTCUT_CONF");

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
            sql.addSql(" create table FILE_SHORTCUT_CONF (");
            sql.addSql("   FDR_SID NUMBER(10,0) not null,");
            sql.addSql("   USR_SID NUMBER(10,0) not null,");
            sql.addSql("   FSC_ADATE varchar(23) not null,");
            sql.addSql("   primary key (FDR_SID,USR_SID)");
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
     * <p>Insert FILE_SHORTCUT_CONF Data Bindding JavaBean
     * @param bean FILE_SHORTCUT_CONF Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(FileShortcutConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" FILE_SHORTCUT_CONF(");
            sql.addSql("   FDR_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   FSC_ADATE");
            sql.addSql(" )");
            sql.addSql(" values");
            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFdrSid());
            sql.addIntValue(bean.getUsrSid());
            sql.addDateValue(bean.getFscAdate());
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
     * <p>Update FILE_SHORTCUT_CONF Data Bindding JavaBean
     * @param bean FILE_SHORTCUT_CONF Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(FileShortcutConfModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_SHORTCUT_CONF");
            sql.addSql(" set ");
            sql.addSql("   FSC_ADATE=?");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addDateValue(bean.getFscAdate());
            //where
            sql.addIntValue(bean.getFdrSid());
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
     * <p>Select FILE_SHORTCUT_CONF All Data
     * @return List in FILE_SHORTCUT_CONFModel
     * @throws SQLException SQL実行例外
     */
    public List<FileShortcutConfModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileShortcutConfModel> ret = new ArrayList<FileShortcutConfModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FDR_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   FSC_ADATE");
            sql.addSql(" from ");
            sql.addSql("   FILE_SHORTCUT_CONF");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileShortcutConfFromRs(rs));
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
     * <p>Select FILE_SHORTCUT_CONF
     * @param fdrSid FDR_SID
     * @param usrSid USR_SID
     * @return FILE_SHORTCUT_CONFModel
     * @throws SQLException SQL実行例外
     */
    public FileShortcutConfModel select(int fdrSid, int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        FileShortcutConfModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FDR_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   FSC_ADATE");
            sql.addSql(" from");
            sql.addSql("   FILE_SHORTCUT_CONF");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);
            sql.addIntValue(usrSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getFileShortcutConfFromRs(rs);
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
     * <p>ユーザSIDを指定しショートカット設定しているディレクトリ情報を取得
     * @param usrSid USR_SID
     * @return FILE_SHORTCUT_CONFModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileDirectoryModel> getShortCutInfo(int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileDirectoryModel> ret = new ArrayList<FileDirectoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FILE_DIRECTORY.FDR_SID as FDR_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID as FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION as FDR_VERSION,");
            sql.addSql("   FILE_DIRECTORY.FCB_SID as FCB_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN as FDR_KBN,");
            sql.addSql("   FILE_DIRECTORY.FDR_VER_KBN as FDR_VER_KBN,");
            sql.addSql("   FILE_DIRECTORY.FDR_LEVEL as FDR_LEVEL,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME as FDR_NAME,");
            sql.addSql("   FILE_DIRECTORY.FDR_BIKO as FDR_BIKO,");
            sql.addSql("   FILE_DIRECTORY.FDR_JTKBN as FDR_JTKBN,");
            sql.addSql("   FILE_DIRECTORY.FDR_AUID as FDR_AUID,");
            sql.addSql("   FILE_DIRECTORY.FDR_ADATE as FDR_ADATE,");
            sql.addSql("   FILE_DIRECTORY.FDR_EUID as FDR_EUID,");
            sql.addSql("   FILE_DIRECTORY.FDR_EDATE as FDR_EDATE,");
            sql.addSql("   FILE_DIRECTORY.BIN_SID as BIN_SID");
            sql.addSql(" from");
            sql.addSql("   FILE_CABINET,");
            sql.addSql("   FILE_SHORTCUT_CONF,");
            sql.addSql("   (select ");
            sql.addSql("    DIR.FDR_SID as FDR_SID,");
            sql.addSql("    DIR.FDR_PARENT_SID as FDR_PARENT_SID,");
            sql.addSql("    DIR.FDR_VERSION as FDR_VERSION,");
            sql.addSql("    DIR.FCB_SID as FCB_SID,");
            sql.addSql("    DIR.FDR_KBN as FDR_KBN,");
            sql.addSql("    DIR.FDR_VER_KBN as FDR_VER_KBN,");
            sql.addSql("    DIR.FDR_LEVEL as FDR_LEVEL,");
            sql.addSql("    DIR.FDR_NAME as FDR_NAME,");
            sql.addSql("    DIR.FDR_BIKO as FDR_BIKO,");
            sql.addSql("    DIR.FDR_JTKBN as FDR_JTKBN,");
            sql.addSql("    DIR.FDR_AUID as FDR_AUID,");
            sql.addSql("    DIR.FDR_ADATE as FDR_ADATE,");
            sql.addSql("    DIR.FDR_EUID as FDR_EUID,");
            sql.addSql("    DIR.FDR_EDATE as FDR_EDATE,");
            sql.addSql("    DIR.FDR_ACCESS_SID as FDR_ACCESS_SID,");
            sql.addSql("    BIN.BIN_SID as BIN_SID");
            sql.addSql("   from");
            sql.addSql("    FILE_DIRECTORY as DIR");
            sql.addSql("      left join FILE_FILE_BIN as BIN");
            sql.addSql("      on  DIR.FDR_SID = BIN.FDR_SID");
            sql.addSql("      and DIR.FDR_VERSION = BIN.FDR_VERSION ) as FILE_DIRECTORY,");

            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION");
            sql.addSql("    from FILE_DIRECTORY group by FDR_SID) DIR_MAXVERSION");
            sql.addSql(" where");
            sql.addSql("   FILE_SHORTCUT_CONF.USR_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FILE_SHORTCUT_CONF.FDR_SID = FILE_DIRECTORY.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
            sql.addIntValue(usrSid);

            //閲覧が許可されていない場合は対象外とする
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FCB_SID = FILE_CABINET.FCB_SID");
            /*sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("     FILE_CABINET.FCB_ACCESS_KBN = ?");
            sql.addSql("   or");
            sql.addSql("     (");
            sql.addSql("       FILE_CABINET.FCB_ACCESS_KBN = ?");
            sql.addSql("     and");
            sql.addSql("       (");
            sql.addSql("         exists (");
            sql.addSql("           select 1 from FILE_ACCESS_CONF");
            sql.addSql("           where FILE_SHORTCUT_CONF.USR_SID = FILE_ACCESS_CONF.USR_SID");
            sql.addSql("           and FILE_ACCESS_CONF.FCB_SID = FILE_CABINET.FCB_SID");
            sql.addSql("           and FILE_ACCESS_CONF.USR_KBN = ?");
            sql.addSql("         )");
            sql.addSql("       or");
            sql.addSql("         exists (");
            sql.addSql("           select");
            sql.addSql("             1");
            sql.addSql("           from");
            sql.addSql("             CMN_BELONGM,");
            sql.addSql("             FILE_ACCESS_CONF");
            sql.addSql("           where FILE_SHORTCUT_CONF.USR_SID = CMN_BELONGM.USR_SID");
            sql.addSql("           and FILE_ACCESS_CONF.FCB_SID = FILE_CABINET.FCB_SID");
            sql.addSql("           and FILE_ACCESS_CONF.USR_KBN = ?");
            sql.addSql("           and CMN_BELONGM.GRP_SID = FILE_ACCESS_CONF.USR_SID");
            sql.addSql("         )");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   )");
            sql.addIntValue(GSConstFile.ACCESS_KBN_OFF);
            sql.addIntValue(GSConstFile.ACCESS_KBN_ON);
            sql.addIntValue(GSConstFile.USER_KBN_USER);
            sql.addIntValue(GSConstFile.USER_KBN_GROUP);*/
            //ディレクトリのアクセス設定で判別する
            sql.addSql(" and (");
            sql.addSql("   FILE_DIRECTORY.FDR_ACCESS_SID = ?");
            sql.addSql(" or exists");
            sql.addSql("   (select *");
            sql.addSql("    from");
            sql.addSql("      FILE_DACCESS_CONF A");
            sql.addSql("    where");
            sql.addSql("      A.FDR_SID = FILE_DIRECTORY.FDR_ACCESS_SID");
            sql.addSql("    and (");
            sql.addSql("      (A.USR_KBN = ? and");
            sql.addSql("       A.USR_SID = FILE_SHORTCUT_CONF.USR_SID) or");
            sql.addSql("      (A.USR_KBN = ? and");
            sql.addSql("       exists");
            sql.addSql("         (select *");
            sql.addSql("          from");
            sql.addSql("            CMN_BELONGM B");
            sql.addSql("          where");
            sql.addSql("            B.GRP_SID = A.USR_SID");
            sql.addSql("          and");
            sql.addSql("            B.USR_SID = FILE_SHORTCUT_CONF.USR_SID");
            sql.addSql("         )))");
            sql.addSql("   ))");
            sql.addIntValue(GSConstFile.DIRECTORY_ROOT);
            sql.addIntValue(GSConstFile.USER_KBN_USER);
            sql.addIntValue(GSConstFile.USER_KBN_GROUP);

            sql.addSql(" order by");
            sql.addSql("   FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   FILE_DIRECTORY.FDR_NAME,");
            sql.addSql("   FILE_SHORTCUT_CONF.FSC_ADATE");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileDirectoryFromRs(rs));
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
     * <p>Delete FILE_SHORTCUT_CONF
     * @param fdrSid FDR_SID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int delete(int fdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_SHORTCUT_CONF");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);

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
     * <p>Delete FILE_SHORTCUT_CONF
     * @param fdrSid FDR_SID
     * @param usrSid USR_SIDg
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int delete(int fdrSid, int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_SHORTCUT_CONF");
            sql.addSql(" where ");
            sql.addSql("   FDR_SID=?");
            sql.addSql(" and");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);
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
     * <br>[機  能] 指定されたディレクトリ(複数)を削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param delList 削除対象のディレクトリ情報モデルリスト
     * @throws SQLException 例外
     */
    public void deleteDir(ArrayList<FileDirectoryModel> delList)
        throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_SHORTCUT_CONF");
            if (delList != null) {
                sql.addSql(" where ");
                sql.addSql("   FDR_SID=-1");
                for (FileDirectoryModel bean : delList) {
                    sql.addSql(" or");
                    sql.addSql("   FDR_SID=?");
                    sql.addIntValue(bean.getFdrSid());
                }
            }

            pstmt = con.prepareStatement(sql.toSqlString());

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
     * <p>指定したディレクトリSIDを親に持つディレクトリのショートカットを削除する
     * @param fdrSid FDR_SID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int deleteParentDir(int fdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete from FILE_SHORTCUT_CONF");
            sql.addSql("  where FDR_SID in (select FDR_SID");
            sql.addSql("                      from FILE_DIRECTORY");
            sql.addSql("                     where FDR_PARENT_SID = ?");
            sql.addSql("                   )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);

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
     * <p>ユーザSIDとディレクトリSIDを指定しショートカット設定しているディレクトリ情報を取得
     * <p>バージョン情報、
     * @param usrSid USR_SID
     * @param fdrSid FDR_SID
     * @return FILE_SHORTCUT_CONFModel
     * @throws SQLException SQL実行例外
     */
    public FileDirectoryModel getShortCutInfo(int usrSid
                                                        , int fdrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        FileDirectoryModel ret = new FileDirectoryModel();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("select FILE_DIRECTORY.FDR_SID");
            sql.addSql("      ,FILE_DIRECTORY.FDR_VERSION");
            sql.addSql("      ,FILE_DIRECTORY.FCB_SID");
            sql.addSql("      ,FILE_DIRECTORY.FDR_PARENT_SID");
            sql.addSql("      ,FILE_DIRECTORY.FDR_KBN");
            sql.addSql("      ,FILE_DIRECTORY.FDR_VER_KBN");
            sql.addSql("      ,FILE_DIRECTORY.FDR_LEVEL");
            sql.addSql("      ,FILE_DIRECTORY.FDR_NAME");
            sql.addSql("      ,FILE_DIRECTORY.FDR_BIKO");
            sql.addSql("      ,FILE_DIRECTORY.FDR_JTKBN");
            sql.addSql("      ,FILE_DIRECTORY.FDR_AUID");
            sql.addSql("      ,FILE_DIRECTORY.FDR_ADATE");
            sql.addSql("      ,FILE_DIRECTORY.FDR_EUID");
            sql.addSql("      ,FILE_DIRECTORY.FDR_EDATE");
            sql.addSql("      ,null as BIN_SID");
            sql.addSql("  from (select FDR_SID,USR_SID from FILE_SHORTCUT_CONF");
            sql.addSql("         where FDR_SID=? and USR_SID=?) as FILE_SHORTCUT_CONF");
            sql.addSql(" inner join FILE_DIRECTORY");
            sql.addSql("         on FILE_DIRECTORY.FDR_SID=FILE_SHORTCUT_CONF.FDR_SID");
            sql.addSql(" where FDR_VERSION = (select max(FDR_MAX.FDR_VERSION)");
            sql.addSql("                        from FILE_DIRECTORY as FDR_MAX");
            sql.addSql("                       where FDR_MAX.FDR_SID=?)");
            sql.addSql(" order by FDR_SID");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fdrSid);
            sql.addIntValue(usrSid);
            sql.addIntValue(fdrSid);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getFileDirectoryFromRs(rs);
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
     * <p>Create FILE_SHORTCUT_CONF Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created FileShortcutConfModel
     * @throws SQLException SQL実行例外
     */
    private FileShortcutConfModel __getFileShortcutConfFromRs(ResultSet rs) throws SQLException {
        FileShortcutConfModel bean = new FileShortcutConfModel();
        bean.setFdrSid(rs.getInt("FDR_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setFscAdate(UDate.getInstanceTimestamp(rs.getTimestamp("FSC_ADATE")));
        return bean;
    }

    /**
     * <p>Create FILE_DIRECTORY Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created FileDirectoryModel
     * @throws SQLException SQL実行例外
     */
    private FileDirectoryModel __getFileDirectoryFromRs(ResultSet rs) throws SQLException {
        FileDirectoryModel bean = new FileDirectoryModel();
        bean.setFdrSid(rs.getInt("FDR_SID"));
        bean.setFdrVersion(rs.getInt("FDR_VERSION"));
        bean.setFcbSid(rs.getInt("FCB_SID"));
        bean.setFdrParentSid(rs.getInt("FDR_PARENT_SID"));
        bean.setFdrKbn(rs.getInt("FDR_KBN"));
        bean.setFdrVerKbn(rs.getInt("FDR_VER_KBN"));
        bean.setFdrLevel(rs.getInt("FDR_LEVEL"));
        bean.setFdrName(rs.getString("FDR_NAME"));
        bean.setFdrBiko(rs.getString("FDR_BIKO"));
        bean.setFdrJtkbn(rs.getInt("FDR_JTKBN"));
        bean.setFdrAuid(rs.getInt("FDR_AUID"));
        bean.setFdrAdate(UDate.getInstanceTimestamp(rs.getTimestamp("FDR_ADATE")));
        bean.setFdrEuid(rs.getInt("FDR_EUID"));
        bean.setFdrEdate(UDate.getInstanceTimestamp(rs.getTimestamp("FDR_EDATE")));
        bean.setBinSid(rs.getLong("BIN_SID"));
        return bean;
    }
}
