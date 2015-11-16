package jp.groupsession.v2.fil.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.fil010.FileCabinetDspModel;
import jp.groupsession.v2.fil.model.FileCabinetModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>FILE_CABINET Data Access Object
 *
 * @author JTS DaoGenerator version 0.5
 */
public class FileCabinetDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(FileCabinetDao.class);

    /**
     * <p>Default Constructor
     */
    public FileCabinetDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public FileCabinetDao(Connection con) {
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
            sql.addSql("drop table FILE_CABINET");

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
            sql.addSql(" create table FILE_CABINET (");
            sql.addSql("   FCB_SID NUMBER(10,0) not null,");
            sql.addSql("   FCB_NAME varchar(150) not null,");
            sql.addSql("   FCB_ACCESS_KBN NUMBER(10,0) not null,");
            sql.addSql("   FCB_CAPA_KBN NUMBER(10,0) not null,");
            sql.addSql("   FCB_CAPA_SIZE NUMBER(10,0),");
            sql.addSql("   FCB_CAPA_WARN NUMBER(10,0),");
            sql.addSql("   FCB_VER_KBN NUMBER(10,0) not null,");
            sql.addSql("   FCB_VERALL_KBN NUMBER(10,0),");
            sql.addSql("   FCB_BIKO NUMBER(10,0),");
            sql.addSql("   FCB_SORT NUMBER(10,0) not null,");
            sql.addSql("   FCB_MARK NUMBER(10,0) not null,");
            sql.addSql("   FCB_AUID NUMBER(10,0) not null,");
            sql.addSql("   FCB_ADATE varchar(23) not null,");
            sql.addSql("   FCB_EUID NUMBER(10,0) not null,");
            sql.addSql("   FCB_EDATE varchar(23) not null,");
            sql.addSql("   primary key (FCB_SID)");
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
     * <p>Insert FILE_CABINET Data Bindding JavaBean
     * @param bean FILE_CABINET Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(FileCabinetModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" FILE_CABINET(");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FCB_NAME,");
            sql.addSql("   FCB_ACCESS_KBN,");
            sql.addSql("   FCB_CAPA_KBN,");
            sql.addSql("   FCB_CAPA_SIZE,");
            sql.addSql("   FCB_CAPA_WARN,");
            sql.addSql("   FCB_VER_KBN,");
            sql.addSql("   FCB_VERALL_KBN,");
            sql.addSql("   FCB_BIKO,");
            sql.addSql("   FCB_SORT,");
            sql.addSql("   FCB_MARK,");
            sql.addSql("   FCB_AUID,");
            sql.addSql("   FCB_ADATE,");
            sql.addSql("   FCB_EUID,");
            sql.addSql("   FCB_EDATE");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFcbSid());
            sql.addStrValue(bean.getFcbName());
            sql.addIntValue(bean.getFcbAccessKbn());
            sql.addIntValue(bean.getFcbCapaKbn());
            sql.addIntValue(bean.getFcbCapaSize());
            sql.addIntValue(bean.getFcbCapaWarn());
            sql.addIntValue(bean.getFcbVerKbn());
            sql.addIntValue(bean.getFcbVerallKbn());
            sql.addStrValue(bean.getFcbBiko());
            sql.addIntValue(bean.getFcbSort());
            sql.addLongValue(bean.getFcbMark());
            sql.addIntValue(bean.getFcbAuid());
            sql.addDateValue(bean.getFcbAdate());
            sql.addIntValue(bean.getFcbEuid());
            sql.addDateValue(bean.getFcbEdate());
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
     * <p>Insert FILE_CABINET Data Bindding JavaBean
     * @param beanList FILE_CABINET DataList
     * @throws SQLException SQL実行例外
     */
    public void insert(List<FileCabinetModel> beanList) throws SQLException {

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
            sql.addSql(" FILE_CABINET(");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FCB_NAME,");
            sql.addSql("   FCB_ACCESS_KBN,");
            sql.addSql("   FCB_CAPA_KBN,");
            sql.addSql("   FCB_CAPA_SIZE,");
            sql.addSql("   FCB_CAPA_WARN,");
            sql.addSql("   FCB_VER_KBN,");
            sql.addSql("   FCB_VERALL_KBN,");
            sql.addSql("   FCB_BIKO,");
            sql.addSql("   FCB_SORT,");
            sql.addSql("   FCB_MARK,");
            sql.addSql("   FCB_AUID,");
            sql.addSql("   FCB_ADATE,");
            sql.addSql("   FCB_EUID,");
            sql.addSql("   FCB_EDATE");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());

            for (FileCabinetModel bean : beanList) {
                sql.addIntValue(bean.getFcbSid());
                sql.addStrValue(bean.getFcbName());
                sql.addIntValue(bean.getFcbAccessKbn());
                sql.addIntValue(bean.getFcbCapaKbn());
                sql.addIntValue(bean.getFcbCapaSize());
                sql.addIntValue(bean.getFcbCapaWarn());
                sql.addIntValue(bean.getFcbVerKbn());
                sql.addIntValue(bean.getFcbVerallKbn());
                sql.addStrValue(bean.getFcbBiko());
                sql.addIntValue(bean.getFcbSort());
                sql.addLongValue(bean.getFcbMark());
                sql.addIntValue(bean.getFcbAuid());
                sql.addDateValue(bean.getFcbAdate());
                sql.addIntValue(bean.getFcbEuid());
                sql.addDateValue(bean.getFcbEdate());
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
     * <p>Update FILE_CABINET Data Bindding JavaBean
     * @param bean FILE_CABINET Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int update(FileCabinetModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" set ");
            sql.addSql("   FCB_NAME=?,");
            sql.addSql("   FCB_ACCESS_KBN=?,");
            sql.addSql("   FCB_CAPA_KBN=?,");
            sql.addSql("   FCB_CAPA_SIZE=?,");
            sql.addSql("   FCB_CAPA_WARN=?,");
            sql.addSql("   FCB_VER_KBN=?,");
            sql.addSql("   FCB_VERALL_KBN=?,");
            sql.addSql("   FCB_BIKO=?,");
//            sql.addSql("   FCB_SORT=?,");
            sql.addSql("   FCB_MARK=?,");
            sql.addSql("   FCB_EUID=?,");
            sql.addSql("   FCB_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getFcbName());
            sql.addIntValue(bean.getFcbAccessKbn());
            sql.addIntValue(bean.getFcbCapaKbn());
            sql.addIntValue(bean.getFcbCapaSize());
            sql.addIntValue(bean.getFcbCapaWarn());
            sql.addIntValue(bean.getFcbVerKbn());
            sql.addIntValue(bean.getFcbVerallKbn());
            sql.addStrValue(bean.getFcbBiko());
//            sql.addValue(bean.getFcbSort());
            sql.addLongValue(bean.getFcbMark());
            sql.addIntValue(bean.getFcbEuid());
            sql.addDateValue(bean.getFcbEdate());
            //where
            sql.addIntValue(bean.getFcbSid());

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
     * <p>Update 名称を更新しません
     * @param bean FILE_CABINET Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateNoName(FileCabinetModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" set ");
            //sql.addSql("   FCB_NAME=?,");
            sql.addSql("   FCB_ACCESS_KBN=?,");
            sql.addSql("   FCB_CAPA_KBN=?,");
            sql.addSql("   FCB_CAPA_SIZE=?,");
            sql.addSql("   FCB_CAPA_WARN=?,");
            sql.addSql("   FCB_VER_KBN=?,");
            sql.addSql("   FCB_VERALL_KBN=?,");
            sql.addSql("   FCB_BIKO=?,");
            sql.addSql("   FCB_SORT=?,");
//            sql.addSql("   FCB_MARK=?,");
            sql.addSql("   FCB_EUID=?,");
            sql.addSql("   FCB_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            //sql.addValue(bean.getFcbName());
            sql.addIntValue(bean.getFcbAccessKbn());
            sql.addIntValue(bean.getFcbCapaKbn());
            sql.addIntValue(bean.getFcbCapaSize());
            sql.addIntValue(bean.getFcbCapaWarn());
            sql.addIntValue(bean.getFcbVerKbn());
            sql.addIntValue(bean.getFcbVerallKbn());
            sql.addStrValue(bean.getFcbBiko());
            sql.addIntValue(bean.getFcbSort());
//            sql.addValue(bean.getFcbMark());
            sql.addIntValue(bean.getFcbEuid());
            sql.addDateValue(bean.getFcbEdate());
            //where
            sql.addIntValue(bean.getFcbSid());

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
     * <p>表示順を更新する。
     * @param bean FILE_CABINET Data Bindding JavaBean
     * @return 更新件数
     * @throws SQLException SQL実行例外
     */
    public int updateSort(FileCabinetModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" set ");
            sql.addSql("   FCB_SORT=?,");
            sql.addSql("   FCB_EUID=?,");
            sql.addSql("   FCB_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getFcbSort());
            sql.addIntValue(bean.getFcbEuid());
            sql.addDateValue(bean.getFcbEdate());
            //where
            sql.addIntValue(bean.getFcbSid());

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
     * <p>Select FILE_CABINET All Data
     * @return List in FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileCabinetModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileCabinetModel> ret = new ArrayList<FileCabinetModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FCB_NAME,");
            sql.addSql("   FCB_ACCESS_KBN,");
            sql.addSql("   FCB_CAPA_KBN,");
            sql.addSql("   FCB_CAPA_SIZE,");
            sql.addSql("   FCB_CAPA_WARN,");
            sql.addSql("   FCB_VER_KBN,");
            sql.addSql("   FCB_VERALL_KBN,");
            sql.addSql("   FCB_BIKO,");
            sql.addSql("   FCB_SORT,");
            sql.addSql("   FCB_MARK,");
            sql.addSql("   FCB_AUID,");
            sql.addSql("   FCB_ADATE,");
            sql.addSql("   FCB_EUID,");
            sql.addSql("   FCB_EDATE");
            sql.addSql(" from ");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" order by ");
            sql.addSql("   FCB_SORT ASC");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileCabinetFromRs(rs));
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
     * <p>表示用のキャビネット情報モデルを取得する
     * @return List in FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileCabinetDspModel> getFileCabinetDspModelsAll() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileCabinetDspModel> ret = new ArrayList<FileCabinetDspModel>();
        FileCabinetDspModel bean = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FCB_NAME,");
            sql.addSql("   FCB_ACCESS_KBN,");
            sql.addSql("   FCB_CAPA_KBN,");
            sql.addSql("   FCB_CAPA_SIZE,");
            sql.addSql("   FCB_CAPA_WARN,");
            sql.addSql("   FCB_VER_KBN,");
            sql.addSql("   FCB_VERALL_KBN,");
            sql.addSql("   FCB_BIKO,");
            sql.addSql("   FCB_SORT,");
            sql.addSql("   FCB_MARK,");
            sql.addSql("   FCB_AUID,");
            sql.addSql("   FCB_ADATE,");
            sql.addSql("   FCB_EUID,");
            sql.addSql("   FCB_EDATE");
            sql.addSql(" from ");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" order by ");
            sql.addSql("   FCB_SORT ASC");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = __getFileCabinetDspFromRs(rs);
                bean.setAccessIconKbn(GSConstFile.ACCESS_KBN_WRITE);
                ret.add(bean);
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
     * <p>表示用のキャビネット情報モデルを取得する
     * @param usrSid ユーザSID
     * @return List in FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileCabinetDspModel> getFileCabinetDspModels(int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileCabinetDspModel> ret = new ArrayList<FileCabinetDspModel>();
        FileCabinetDspModel bean = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FILE_CABINET.FCB_SID,");
            sql.addSql("   FILE_CABINET.FCB_NAME,");
            sql.addSql("   FILE_CABINET.FCB_ACCESS_KBN,");
            sql.addSql("   FILE_CABINET.FCB_CAPA_KBN,");
            sql.addSql("   FILE_CABINET.FCB_CAPA_SIZE,");
            sql.addSql("   FILE_CABINET.FCB_CAPA_WARN,");
            sql.addSql("   FILE_CABINET.FCB_VER_KBN,");
            sql.addSql("   FILE_CABINET.FCB_VERALL_KBN,");
            sql.addSql("   FILE_CABINET.FCB_BIKO,");
            sql.addSql("   FILE_CABINET.FCB_SORT,");
            sql.addSql("   FILE_CABINET.FCB_MARK,");
            sql.addSql("   FILE_CABINET.FCB_AUID,");
            sql.addSql("   FILE_CABINET.FCB_ADATE,");
            sql.addSql("   FILE_CABINET.FCB_EUID,");
            sql.addSql("   FILE_CABINET.FCB_EDATE,");
            sql.addSql("   FILE_ACCESS_CONF.FAA_AUTH,");
            sql.addSql("   FILE_ACCESS_CONF.USR_SID");
            sql.addSql(" from ");
            sql.addSql("   FILE_CABINET");
            sql.addSql("   left join FILE_ACCESS_CONF on");
            sql.addSql("   FILE_CABINET.FCB_SID=FILE_ACCESS_CONF.FCB_SID");
            sql.addSql("   and FILE_ACCESS_CONF.USR_SID = ?");

            sql.addSql(" order by ");
            sql.addSql("   FCB_SORT ASC");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(usrSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = __getFileCabinetDspFromRs(rs);
                bean.setAccessIconKbn(
                        NullDefault.getString(
                                rs.getString("FAA_AUTH"), GSConstFile.ACCESS_KBN_WRITE));
                ret.add(bean);
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
     * <p>表示順の降順でキャビネット情報を取得する。
     * @return List in FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public List<FileCabinetModel> selectSortDesc() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileCabinetModel> ret = new ArrayList<FileCabinetModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FCB_NAME,");
            sql.addSql("   FCB_ACCESS_KBN,");
            sql.addSql("   FCB_CAPA_KBN,");
            sql.addSql("   FCB_CAPA_SIZE,");
            sql.addSql("   FCB_CAPA_WARN,");
            sql.addSql("   FCB_VER_KBN,");
            sql.addSql("   FCB_VERALL_KBN,");
            sql.addSql("   FCB_BIKO,");
            sql.addSql("   FCB_SORT,");
            sql.addSql("   FCB_MARK,");
            sql.addSql("   FCB_AUID,");
            sql.addSql("   FCB_ADATE,");
            sql.addSql("   FCB_EUID,");
            sql.addSql("   FCB_EDATE");
            sql.addSql(" from ");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" order by ");
            sql.addSql("   FCB_SORT DESC");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getFileCabinetFromRs(rs));
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
     * <p>キャビネット登録件数を取得する。
     * @return List in FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public int countCabinet() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select count(*) as CNT");
            sql.addSql(" from ");
            sql.addSql("   FILE_CABINET");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
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
     * <p>Select FILE_CABINET
     * @param fcbSid FCB_SID
     * @return FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public FileCabinetModel select(int fcbSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        FileCabinetModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FCB_NAME,");
            sql.addSql("   FCB_ACCESS_KBN,");
            sql.addSql("   FCB_CAPA_KBN,");
            sql.addSql("   FCB_CAPA_SIZE,");
            sql.addSql("   FCB_CAPA_WARN,");
            sql.addSql("   FCB_VER_KBN,");
            sql.addSql("   FCB_VERALL_KBN,");
            sql.addSql("   FCB_BIKO,");
            sql.addSql("   FCB_SORT,");
            sql.addSql("   FCB_MARK,");
            sql.addSql("   FCB_AUID,");
            sql.addSql("   FCB_ADATE,");
            sql.addSql("   FCB_EUID,");
            sql.addSql("   FCB_EDATE");
            sql.addSql(" from");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fcbSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getFileCabinetFromRs(rs);
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
     * <p>アクセス制限の無いキャビネットを取得
     * @return List in FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileCabinetDspModel> getFreeAccessCabinet() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileCabinetDspModel> ret = new ArrayList<FileCabinetDspModel>();
        FileCabinetDspModel bean = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FCB_SID,");
            sql.addSql("   FCB_NAME,");
            sql.addSql("   FCB_ACCESS_KBN,");
            sql.addSql("   FCB_CAPA_KBN,");
            sql.addSql("   FCB_CAPA_SIZE,");
            sql.addSql("   FCB_CAPA_WARN,");
            sql.addSql("   FCB_VER_KBN,");
            sql.addSql("   FCB_VERALL_KBN,");
            sql.addSql("   FCB_BIKO,");
            sql.addSql("   FCB_SORT,");
            sql.addSql("   FCB_MARK,");
            sql.addSql("   FCB_AUID,");
            sql.addSql("   FCB_ADATE,");
            sql.addSql("   FCB_EUID,");
            sql.addSql("   FCB_EDATE");
            sql.addSql(" from");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" where ");
            sql.addSql("   FCB_ACCESS_KBN=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(GSConstFile.ACCESS_KBN_OFF);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = __getFileCabinetDspFromRs(rs);
                bean.setAccessIconKbn(GSConstFile.ACCESS_KBN_WRITE);
                ret.add(bean);
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
     * <p>ユーザが管理者に設定されているキャビネットを取得
     * @param userSid ユーザSID
     * @return List in FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileCabinetDspModel> getAdminCabinet(int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileCabinetDspModel> ret = new ArrayList<FileCabinetDspModel>();
        FileCabinetDspModel bean = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FILE_CABINET.FCB_SID,");
            sql.addSql("   FILE_CABINET.FCB_NAME,");
            sql.addSql("   FILE_CABINET.FCB_ACCESS_KBN,");
            sql.addSql("   FILE_CABINET.FCB_CAPA_KBN,");
            sql.addSql("   FILE_CABINET.FCB_CAPA_SIZE,");
            sql.addSql("   FILE_CABINET.FCB_CAPA_WARN,");
            sql.addSql("   FILE_CABINET.FCB_VER_KBN,");
            sql.addSql("   FILE_CABINET.FCB_VERALL_KBN,");
            sql.addSql("   FILE_CABINET.FCB_BIKO,");
            sql.addSql("   FILE_CABINET.FCB_SORT,");
            sql.addSql("   FILE_CABINET.FCB_MARK,");
            sql.addSql("   FILE_CABINET.FCB_AUID,");
            sql.addSql("   FILE_CABINET.FCB_ADATE,");
            sql.addSql("   FILE_CABINET.FCB_EUID,");
            sql.addSql("   FILE_CABINET.FCB_EDATE");
            sql.addSql(" from");
            sql.addSql("   FILE_CABINET,");
            sql.addSql("   FILE_CABINET_ADMIN");
            sql.addSql(" where ");
            sql.addSql("   FILE_CABINET_ADMIN.USR_SID=?");
            sql.addSql(" and ");
            sql.addSql("   FILE_CABINET.FCB_SID=FILE_CABINET_ADMIN.FCB_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(userSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = __getFileCabinetDspFromRs(rs);
                bean.setAccessIconKbn(GSConstFile.ACCESS_KBN_WRITE);
                ret.add(bean);
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
     * <p>ユーザがアクセス設定に登録されているキャビネットを取得
     * @param userSid ユーザSID
     * @param auth 権限 -1の場合は検索条件に含めない
     * @return List in FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileCabinetDspModel> getCanAccessCabinet(int userSid, int auth)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileCabinetDspModel> ret = new ArrayList<FileCabinetDspModel>();
        FileCabinetDspModel bean = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FILE_CABINET.FCB_SID,");
            sql.addSql("   FILE_CABINET.FCB_NAME,");
            sql.addSql("   FILE_CABINET.FCB_ACCESS_KBN,");
            sql.addSql("   FILE_CABINET.FCB_CAPA_KBN,");
            sql.addSql("   FILE_CABINET.FCB_CAPA_SIZE,");
            sql.addSql("   FILE_CABINET.FCB_CAPA_WARN,");
            sql.addSql("   FILE_CABINET.FCB_VER_KBN,");
            sql.addSql("   FILE_CABINET.FCB_VERALL_KBN,");
            sql.addSql("   FILE_CABINET.FCB_BIKO,");
            sql.addSql("   FILE_CABINET.FCB_SORT,");
            sql.addSql("   FILE_CABINET.FCB_MARK,");
            sql.addSql("   FILE_CABINET.FCB_AUID,");
            sql.addSql("   FILE_CABINET.FCB_ADATE,");
            sql.addSql("   FILE_CABINET.FCB_EUID,");
            sql.addSql("   FILE_CABINET.FCB_EDATE,");
            sql.addSql("   FILE_ACCESS_CONF.FAA_AUTH");
            sql.addSql(" from");
            sql.addSql("   FILE_CABINET,");
            sql.addSql("   FILE_ACCESS_CONF");
            sql.addSql(" where ");
            sql.addSql("   FILE_ACCESS_CONF.USR_SID=?");
            sql.addSql(" and ");
            sql.addSql("   FILE_ACCESS_CONF.USR_KBN=?");
            sql.addIntValue(userSid);
            sql.addIntValue(GSConstFile.USER_KBN_USER);
            if (auth != -1) {
                sql.addSql(" and ");
                sql.addSql("   FILE_ACCESS_CONF.FAA_AUTH=?");
                sql.addIntValue(auth);
            }
            sql.addSql(" and ");
            sql.addSql("   FILE_CABINET.FCB_SID=FILE_ACCESS_CONF.FCB_SID");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = __getFileCabinetDspFromRs(rs);
                bean.setAccessIconKbn(rs.getString("FAA_AUTH"));
                ret.add(bean);
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
     * <p>ユーザが所属するグループにアクセス設定登録されているキャビネットを取得
     * @param userSid ユーザSID
     * @param auth 権限 -1の場合は検索条件に含めない
     * @return List in FILE_CABINETModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileCabinetDspModel> getCanAccessGpCabinet(int userSid, int auth)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileCabinetDspModel> ret = new ArrayList<FileCabinetDspModel>();
        FileCabinetDspModel bean = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FILE_CABINET.FCB_SID,");
            sql.addSql("   FCB_NAME,");
            sql.addSql("   FCB_ACCESS_KBN,");
            sql.addSql("   FCB_CAPA_KBN,");
            sql.addSql("   FCB_CAPA_SIZE,");
            sql.addSql("   FCB_CAPA_WARN,");
            sql.addSql("   FCB_VER_KBN,");
            sql.addSql("   FCB_VERALL_KBN,");
            sql.addSql("   FCB_BIKO,");
            sql.addSql("   FCB_SORT,");
            sql.addSql("   FCB_MARK,");
            sql.addSql("   FCB_AUID,");
            sql.addSql("   FCB_ADATE,");
            sql.addSql("   FCB_EUID,");
            sql.addSql("   FCB_EDATE,");
            sql.addSql("   FILE_ACCESS_CONF.FAA_AUTH");
            sql.addSql(" from");
            sql.addSql("   FILE_CABINET,");
            sql.addSql("   FILE_ACCESS_CONF");
            sql.addSql(" where ");
            sql.addSql("   exists (");
            sql.addSql("     select 1 from");
            sql.addSql("       CMN_GROUPM,");
            sql.addSql("       CMN_BELONGM");
            sql.addSql("     where");
            sql.addSql("       CMN_BELONGM.USR_SID = ?");
            sql.addSql("     and");
            sql.addSql("       CMN_BELONGM.GRP_SID = CMN_GROUPM.GRP_SID");
            sql.addSql("     and");
            sql.addSql("       FILE_ACCESS_CONF.USR_SID = CMN_GROUPM.GRP_SID");
            sql.addSql("   ) ");
            sql.addSql(" and ");
            sql.addSql("   FILE_ACCESS_CONF.USR_KBN=?");

            sql.addIntValue(userSid);
            sql.addIntValue(GSConstFile.USER_KBN_GROUP);

            if (auth != -1) {
                sql.addSql(" and ");
                sql.addSql("   FILE_ACCESS_CONF.FAA_AUTH=?");
                sql.addIntValue(auth);
            }
            sql.addSql(" and ");
            sql.addSql("   FILE_CABINET.FCB_SID=FILE_ACCESS_CONF.FCB_SID");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = __getFileCabinetDspFromRs(rs);
                bean.setAccessIconKbn(rs.getString("FAA_AUTH"));
                ret.add(bean);
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
     * <p>キャビネットSIDを指定しファイルのサイズ合計を取得する
     * @param fcbSid キャビネットSID
     * @return 使用サイズ合計
     * @throws SQLException SQL実行例外
     */
    public BigDecimal getCabinetUsedSize(int fcbSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        BigDecimal ret = new BigDecimal(0);
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   FILE_DIRECTORY.FCB_SID,");
            sql.addSql("   sum(FILE_FILE_BIN.FFL_FILE_SIZE) as SUM_SIZE");
            sql.addSql(" from");
            sql.addSql("   FILE_DIRECTORY,");
//            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION "
//                    + " from FILE_DIRECTORY group by FDR_SID) DIR_MAXVERSION,");
            sql.addSql("   FILE_FILE_BIN");
            sql.addSql(" where ");
//            sql.addSql("   FILE_DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
//            sql.addSql(" and");
//            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
//            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FCB_SID=?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_KBN=?");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_SID = FILE_FILE_BIN.FDR_SID");
            sql.addSql(" and");
            sql.addSql("   FILE_DIRECTORY.FDR_VERSION = FILE_FILE_BIN.FDR_VERSION");
            sql.addSql(" group by ");
            sql.addSql("   FILE_DIRECTORY.FCB_SID");
            sql.addSql("");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fcbSid);
            sql.addIntValue(GSConstFile.DIRECTORY_FILE);

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getBigDecimal("SUM_SIZE");
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
     * <p>キャビネットSIDとバイナリSIDの組み合わせが存在するかチェックする
     * @param fcbSid キャビネットSID
     * @param binSid バイナリSID
     * @return true:存在する false:存在しない
     * @throws SQLException SQL実行例外
     */
    public boolean isCheckFileIcon(int fcbSid, Long binSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int cnt = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(*) as CNT");
            sql.addSql(" from");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID=?");
            sql.addSql(" and ");
            sql.addSql("   FCB_MARK=?");


            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fcbSid);
            sql.addLongValue(binSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cnt = rs.getInt("CNT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return cnt > 0;
    }

    /**
     * <p>Delete FILE_CABINET
     * @param fcbSid FCB_SID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int delete(int fcbSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   FILE_CABINET");
            sql.addSql(" where ");
            sql.addSql("   FCB_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(fcbSid);

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
     * <p>Create FILE_CABINET Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created FileCabinetModel
     * @throws SQLException SQL実行例外
     */
    private FileCabinetModel __getFileCabinetFromRs(ResultSet rs) throws SQLException {
        FileCabinetModel bean = new FileCabinetModel();
        bean.setFcbSid(rs.getInt("FCB_SID"));
        bean.setFcbName(rs.getString("FCB_NAME"));
        bean.setFcbAccessKbn(rs.getInt("FCB_ACCESS_KBN"));
        bean.setFcbCapaKbn(rs.getInt("FCB_CAPA_KBN"));
        bean.setFcbCapaSize(rs.getInt("FCB_CAPA_SIZE"));
        bean.setFcbCapaWarn(rs.getInt("FCB_CAPA_WARN"));
        bean.setFcbVerKbn(rs.getInt("FCB_VER_KBN"));
        bean.setFcbVerallKbn(rs.getInt("FCB_VERALL_KBN"));
        bean.setFcbBiko(rs.getString("FCB_BIKO"));
        bean.setFcbSort(rs.getInt("FCB_SORT"));
        bean.setFcbMark(rs.getLong("FCB_MARK"));
        bean.setFcbAuid(rs.getInt("FCB_AUID"));
        bean.setFcbAdate(UDate.getInstanceTimestamp(rs.getTimestamp("FCB_ADATE")));
        bean.setFcbEuid(rs.getInt("FCB_EUID"));
        bean.setFcbEdate(UDate.getInstanceTimestamp(rs.getTimestamp("FCB_EDATE")));
        return bean;
    }

    /**
     * <p>Create FILE_CABINET Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created FileCabinetModel
     * @throws SQLException SQL実行例外
     */
    private FileCabinetDspModel __getFileCabinetDspFromRs(ResultSet rs) throws SQLException {
        FileCabinetDspModel bean = new FileCabinetDspModel();
        bean.setFcbSid(rs.getInt("FCB_SID"));
        bean.setFcbName(rs.getString("FCB_NAME"));
        bean.setFcbAccessKbn(rs.getInt("FCB_ACCESS_KBN"));
        bean.setFcbCapaKbn(rs.getInt("FCB_CAPA_KBN"));
        bean.setFcbCapaSize(rs.getInt("FCB_CAPA_SIZE"));
        bean.setFcbCapaWarn(rs.getInt("FCB_CAPA_WARN"));
        bean.setFcbVerKbn(rs.getInt("FCB_VER_KBN"));
        bean.setFcbVerallKbn(rs.getInt("FCB_VERALL_KBN"));
        bean.setFcbBiko(rs.getString("FCB_BIKO"));
        bean.setFcbSort(rs.getInt("FCB_SORT"));
        bean.setFcbMark(rs.getLong("FCB_MARK"));
        bean.setFcbAuid(rs.getInt("FCB_AUID"));
        bean.setFcbAdate(UDate.getInstanceTimestamp(rs.getTimestamp("FCB_ADATE")));
        bean.setFcbEuid(rs.getInt("FCB_EUID"));
        bean.setFcbEdate(UDate.getInstanceTimestamp(rs.getTimestamp("FCB_EDATE")));
        return bean;
    }
}
