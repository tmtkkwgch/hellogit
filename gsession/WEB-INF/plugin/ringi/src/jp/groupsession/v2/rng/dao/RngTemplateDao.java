package jp.groupsession.v2.rng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.model.RngTemplateModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>RNG_TEMPLATE Data Access Object
 *
 * @author JTS DaoGenerator version 0.1
 */
public class RngTemplateDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RngTemplateDao.class);

    /**
     * <p>Default Constructor
     */
    public RngTemplateDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public RngTemplateDao(Connection con) {
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
            sql.addSql("drop table RNG_TEMPLATE");

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
            sql.addSql(" create table RNG_TEMPLATE (");
            sql.addSql("   RTP_SID NUMBER(10,0) not null,");
            sql.addSql("   RTP_TYPE NUMBER(10,0) not null,");
            sql.addSql("   USR_SID NUMBER(10,0),");
            sql.addSql("   RTP_TITLE varchar(100) not null,");
            sql.addSql("   RTP_RNG_TITLE varchar(100),");
            sql.addSql("   RTP_CONTENT varchar(1000) not null,");
            sql.addSql("   RTP_SORT NUMBER(10,0) not null,");
            sql.addSql("   RTP_AUID NUMBER(10,0) not null,");
            sql.addSql("   RTP_ADATE varchar(26) not null,");
            sql.addSql("   RTP_EUID NUMBER(10,0) not null,");
            sql.addSql("   RTP_EDATE varchar(26) not null,");
            sql.addSql("   RTP_EDATE NUMBER(10,0) not null");
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
     * <p>Insert RNG_TEMPLATE Data Bindding JavaBean
     * @param bean RNG_TEMPLATE Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     */
    public void insert(RngTemplateModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" RNG_TEMPLATE(");
            sql.addSql("   RTP_SID,");
            sql.addSql("   RTP_TYPE,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RTP_TITLE,");
            sql.addSql("   RTP_RNG_TITLE,");
            sql.addSql("   RTP_CONTENT,");
            sql.addSql("   RTP_SORT,");
            sql.addSql("   RTP_AUID,");
            sql.addSql("   RTP_ADATE,");
            sql.addSql("   RTP_EUID,");
            sql.addSql("   RTP_EDATE,");
            sql.addSql("   RTC_SID");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRtpSid());
            sql.addIntValue(bean.getRtpType());
            sql.addIntValue(bean.getUsrSid());
            sql.addStrValue(bean.getRtpTitle());
            sql.addStrValue(bean.getRtpRngTitle());
            sql.addStrValue(bean.getRtpContent());
            sql.addIntValue(bean.getRtpSort());
            sql.addIntValue(bean.getRtpAuid());
            sql.addDateValue(bean.getRtpAdate());
            sql.addIntValue(bean.getRtpEuid());
            sql.addDateValue(bean.getRtpEdate());
            sql.addIntValue(bean.getRtcSid());
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
     * <br>[機  能] 稟議テンプレート情報の新規登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param bean 稟議テンプレート情報
     * @param tplMode テンプレートモード 0:全て 1:共有 2:個人
     * @param catSid カテゴリSID
     * @throws SQLException SQL実行時例外
     */
    public void insert(RngTemplateModel bean, int tplMode, int catSid) throws SQLException {

        //ソート順の最大値を取得する
        int maxRtpSort = getMaxSort(tplMode, bean.getUsrSid(), catSid);
        bean.setRtpSort(maxRtpSort + 1);

        insert(bean);
    }

    /**
     * <p>Update RNG_TEMPLATE Data Bindding JavaBean
     * @param bean RNG_TEMPLATE Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return count
     */
    public int update(RngTemplateModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" set ");
            sql.addSql("   RTP_TYPE=?,");
            sql.addSql("   USR_SID=?,");
            sql.addSql("   RTP_TITLE=?,");
            sql.addSql("   RTP_RNG_TITLE=?,");
            sql.addSql("   RTP_CONTENT=?,");
            sql.addSql("   RTP_SORT=?,");
            sql.addSql("   RTP_EUID=?,");
            sql.addSql("   RTP_EDATE=?,");
            sql.addSql("   RTC_SID=?");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getRtpType());
            sql.addIntValue(bean.getUsrSid());
            sql.addStrValue(bean.getRtpTitle());
            sql.addStrValue(bean.getRtpRngTitle());
            sql.addStrValue(bean.getRtpContent());
            sql.addIntValue(bean.getRtpSort());
            sql.addIntValue(bean.getRtpEuid());
            sql.addDateValue(bean.getRtpEdate());
            sql.addIntValue(bean.getRtcSid());
            //where
            sql.addIntValue(bean.getRtpSid());
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
     * <p>Update RNG_TEMPLATE Data Bindding JavaBean
     * @param bean RNG_TEMPLATE Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return count
     */
    public int updateNotChangeCategory(RngTemplateModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" set ");
            sql.addSql("   RTP_TITLE=?,");
            sql.addSql("   RTP_RNG_TITLE=?,");
            sql.addSql("   RTP_CONTENT=?,");
            sql.addSql("   RTP_EUID=?,");
            sql.addSql("   RTP_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getRtpTitle());
            sql.addStrValue(bean.getRtpRngTitle());
            sql.addStrValue(bean.getRtpContent());
            sql.addIntValue(bean.getRtpEuid());
            sql.addDateValue(bean.getRtpEdate());
            //where
            sql.addIntValue(bean.getRtpSid());
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
     * <p>Update RNG_TEMPLATE Data Bindding JavaBean
     * @param bean RNG_TEMPLATE Data Bindding JavaBean
     * @throws SQLException SQL実行例外
     * @return count
     */
    public int updateChangeCategory(RngTemplateModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" set ");
            sql.addSql("   RTP_TITLE=?,");
            sql.addSql("   RTP_RNG_TITLE=?,");
            sql.addSql("   RTP_CONTENT=?,");
            sql.addSql("   RTP_SORT=?,");
            sql.addSql("   RTP_EUID=?,");
            sql.addSql("   RTP_EDATE=?,");
            sql.addSql("   RTC_SID=?");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addStrValue(bean.getRtpTitle());
            sql.addStrValue(bean.getRtpRngTitle());
            sql.addStrValue(bean.getRtpContent());
            sql.addIntValue(bean.getRtpSort());
            sql.addIntValue(bean.getRtpEuid());
            sql.addDateValue(bean.getRtpEdate());
            sql.addIntValue(bean.getRtcSid());
            //where
            sql.addIntValue(bean.getRtpSid());
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
     * <br>[機  能] 指定カテゴリ内の指定ソート以上のテンプレートSIDのリストを取得
     * <br>[解  説]
     * <br>[備  考]
     * @param rtcSid カテゴリSID
     * @param rtpSort ソート
     * @param rtpType テンプレートタイプ
     * @return テンプレートSIDのリスト
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<Integer> selectSort(int rtcSid, int rtpSort, int rtpType) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();
        ResultSet rs = null;
        ArrayList<Integer> ret = new ArrayList<Integer>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RTP_SID");
            sql.addSql(" from ");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where ");
            sql.addSql("   RTC_SID=?");
            sql.addIntValue(rtcSid);
            sql.addSql(" and ");
            sql.addSql("   RTP_TYPE=?");
            sql.addIntValue(rtpType);
            sql.addSql(" and ");
            sql.addSql("   RTP_SORT>?");
            sql.addIntValue(rtpSort);
            sql.addSql(" order by ");
            sql.addSql("   RTP_SORT asc");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(rs.getInt("RTP_SID"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <br>[機  能] 指定カテゴリ内の指定ソート以上のテンプレートSIDのリストを取得
     * <br>[解  説]
     * <br>[備  考]
     * @param rtcSid カテゴリSID
     * @param rtpSort ソート
     * @param rtpType テンプレートタイプ
     * @return テンプレートSIDのリスト
     * @throws SQLException SQL実行時例外
     */
    public int getSortCount(int rtcSid, int rtpSort, int rtpType) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();
        ResultSet rs = null;
        int ret = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(RTP_SID) as cnt");
            sql.addSql(" from ");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where ");
            sql.addSql("   RTC_SID=?");
            sql.addIntValue(rtcSid);
            sql.addSql(" and ");
            sql.addSql("   RTP_TYPE=?");
            sql.addIntValue(rtpType);
            sql.addSql(" and ");
            sql.addSql("   RTP_SORT=?");
            sql.addIntValue(rtpSort);

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("cnt");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <p>Select RNG_TEMPLATE All Data
     * @return List in RNG_TEMPLATEModel
     * @throws SQLException SQL実行例外
     */
    public List<RngTemplateModel> select() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<RngTemplateModel> ret = new ArrayList<RngTemplateModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RTP_SID,");
            sql.addSql("   RTP_TYPE,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RTP_TITLE,");
            sql.addSql("   RTP_RNG_TITLE,");
            sql.addSql("   RTP_CONTENT,");
            sql.addSql("   RTP_SORT,");
            sql.addSql("   RTP_AUID,");
            sql.addSql("   RTP_ADATE,");
            sql.addSql("   RTP_EUID,");
            sql.addSql("   RTP_EDATE,");
            sql.addSql("   RTC_SID");
            sql.addSql(" from ");
            sql.addSql("   RNG_TEMPLATE");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret.add(__getRngTemplateFromRs(rs));
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
     * <br>[機  能] テンプレートモードで取得するテンプレート情報を選択します
     * <br>[解  説]
     * <br>[備  考]
     * @param tplMode テンプレートモード 0:全て 1:共有 2:個人
     * @param userSid ユーザSID
     * @param catSid カテゴリSID -1=全て 0=カテゴリなし 0より大きい=カテゴリ指定
     * @param reqMdl リクエスト情報
     * @return List
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<RngTemplateModel> selectTplList(int tplMode,
            int userSid, int catSid, RequestModel reqMdl) throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<RngTemplateModel> ret = new ArrayList<RngTemplateModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            if (catSid != 0) {
                sql.addSql("   RNG_TEMPLATE_CATEGORY.RTC_SORT,");
            }
            sql.addSql("   RNG_TEMPLATE.RTP_SID,");
            sql.addSql("   RNG_TEMPLATE.RTP_TYPE,");
            sql.addSql("   RNG_TEMPLATE.USR_SID,");
            sql.addSql("   RNG_TEMPLATE.RTP_TITLE,");
            sql.addSql("   RNG_TEMPLATE.RTP_RNG_TITLE,");
            sql.addSql("   RNG_TEMPLATE.RTP_CONTENT,");
            sql.addSql("   RNG_TEMPLATE.RTP_SORT,");
            sql.addSql("   RNG_TEMPLATE.RTP_AUID,");
            sql.addSql("   RNG_TEMPLATE.RTP_ADATE,");
            sql.addSql("   RNG_TEMPLATE.RTP_EUID,");
            sql.addSql("   RNG_TEMPLATE.RTP_EDATE,");
            sql.addSql("   RNG_TEMPLATE.RTC_SID,");
            sql.addSql("   RNG_TEMPLATE_CATEGORY.RTC_NAME");
            sql.addSql(" from ");
              sql.addSql("   RNG_TEMPLATE left join RNG_TEMPLATE_CATEGORY on"
                     + "   RNG_TEMPLATE.RTC_SID = RNG_TEMPLATE_CATEGORY.RTC_SID");
            sql.addSql(" where");
            //テンプレートモード：共有
            if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
                sql.addSql("   RNG_TEMPLATE.RTP_TYPE = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_SHARE);
            //テンプレートモード：個人
            } else if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
                sql.addSql("   RNG_TEMPLATE.RTP_TYPE = ?");
                sql.addSql(" and ");
                sql.addSql("   RNG_TEMPLATE.USR_SID = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_PRIVATE);
                sql.addIntValue(userSid);
            }
            //選択カテゴリが「カテゴリなし」
            if (catSid == 0) {
                sql.addSql(" and ");
                sql.addSql("   RNG_TEMPLATE.RTC_SID = ?");
                sql.addIntValue(catSid);
            //選択カテゴリが設定したカテゴリ
            } else if (catSid > 0) {
                sql.addSql(" and ");
                sql.addSql("   RNG_TEMPLATE.RTC_SID = ?");
                sql.addIntValue(catSid);
                sql.addSql(" and ");
                sql.addSql("   RNG_TEMPLATE.RTC_SID = RNG_TEMPLATE_CATEGORY.RTC_SID");
            }
            sql.addSql(" order by");
            if (catSid != 0) {
                sql.addSql("   (RTC_SORT is not null),");
                sql.addSql("   RTC_SORT,");
            }
            sql.addSql("   RTP_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RngTemplateModel bean = new RngTemplateModel();
                bean.setRtpSid(rs.getInt("RTP_SID"));
                bean.setRtpType(rs.getInt("RTP_TYPE"));
                bean.setUsrSid(rs.getInt("USR_SID"));
                bean.setRtpTitle(rs.getString("RTP_TITLE"));
                bean.setRtpRngTitle(rs.getString("RTP_RNG_TITLE"));
                bean.setRtpContent(rs.getString("RTP_CONTENT"));
                bean.setRtpSort(rs.getInt("RTP_SORT"));
                bean.setRtpAuid(rs.getInt("RTP_AUID"));
                bean.setRtpAdate(UDate.getInstanceTimestamp(rs.getTimestamp("RTP_ADATE")));
                bean.setRtpEuid(rs.getInt("RTP_EUID"));
                bean.setRtpEdate(UDate.getInstanceTimestamp(rs.getTimestamp("RTP_EDATE")));
                bean.setRtcSid(rs.getInt("RTC_SID"));
                bean.setRtcName(NullDefault.getString(rs.getString("RTC_NAME"),
                        gsMsg.getMessage("cmn.category.no")));
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
     * <br>[機  能] 並び順と稟議テンプレートSIDのMappingを取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param tplMode テンプレートモード 1:共有 2:個人
     * @param userSid ユーザSID
     * @param catSid カテゴリSID
     * @return 並び順と稟議テンプレートSIDのMapping
     * @throws SQLException SQL実行時例外
     */
    public Map<Integer, Integer> getTplSortMap(int tplMode, int userSid, int catSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Map<Integer, Integer> tplMap = new HashMap<Integer, Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RTP_SID,");
            sql.addSql("   RTP_SORT");
            sql.addSql(" from ");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where");
            //テンプレートモード：共有
            if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
                sql.addSql("   RTP_TYPE = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_SHARE);
            //テンプレートモード：個人
            } else if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
                sql.addSql("   RTP_TYPE = ?");
                sql.addSql(" and ");
                sql.addSql("   USR_SID = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_PRIVATE);
                sql.addIntValue(userSid);
            }
            sql.addSql(" and ");
            sql.addSql("   RTC_SID = ?");
            sql.addIntValue(catSid);
            sql.addSql(" order by");
            sql.addSql("   RTP_SORT,");
            sql.addSql("   RTP_ADATE");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tplMap.put(new Integer(rs.getInt("RTP_SORT")),
                        new Integer(rs.getInt("RTP_SID")));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return tplMap;
    }

    /**
     * <p>Select RNG_TEMPLATE
     * @param bean RNG_TEMPLATE Model
     * @return RNG_TEMPLATEModel
     * @throws SQLException SQL実行例外
     */
    public RngTemplateModel select(RngTemplateModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RngTemplateModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RTP_SID,");
            sql.addSql("   RTP_TYPE,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RTP_TITLE,");
            sql.addSql("   RTP_RNG_TITLE,");
            sql.addSql("   RTP_CONTENT,");
            sql.addSql("   RTP_SORT,");
            sql.addSql("   RTP_AUID,");
            sql.addSql("   RTP_ADATE,");
            sql.addSql("   RTP_EUID,");
            sql.addSql("   RTP_EDATE,");
            sql.addSql("   RTC_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRngTemplateFromRs(rs);
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
     * <p>Select RNG_TEMPLATE
     * @param tplSid テンプレートSID
     * @return RNG_TEMPLATEModel
     * @throws SQLException SQL実行例外
     */
    public RngTemplateModel select(int tplSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RngTemplateModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RTP_SID,");
            sql.addSql("   RTP_TYPE,");
            sql.addSql("   USR_SID,");
            sql.addSql("   RTP_TITLE,");
            sql.addSql("   RTP_RNG_TITLE,");
            sql.addSql("   RTP_CONTENT,");
            sql.addSql("   RTP_SORT,");
            sql.addSql("   RTP_AUID,");
            sql.addSql("   RTP_ADATE,");
            sql.addSql("   RTP_EUID,");
            sql.addSql("   RTP_EDATE,");
            sql.addSql("   RTC_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where");
            sql.addSql("   RTP_SID = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(tplSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = __getRngTemplateFromRs(rs);
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
     * <br>[機  能] 並び順を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rtpSid 稟議テンプレートSID
     * @return 並び順
     * @throws SQLException SQL実行時例外
     */
    public int getSort(int rtpSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int sort = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RTP_SORT");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where");
            sql.addSql("   RTP_SID = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtpSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sort = rs.getInt("RTP_SORT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return sort;
    }

    /**
     * <br>[機  能] 並び順の最大値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tplMode テンプレートモード 0:全て 1:共有 2:個人
     * @param userSid ユーザSID
     * @param catSid カテゴリSID
     * @return 並び順の最大値
     * @throws SQLException SQL実行時例外
     */
    public int getMaxSort(int tplMode, int userSid, int catSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int sort = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   COALESCE(max(RTP_SORT), 0) as MAX_SORT");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where");

            //テンプレートモード：共有
            if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
                sql.addSql("   RTP_TYPE = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_SHARE);
            //テンプレートモード：個人
            } else if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
                sql.addSql("   RTP_TYPE = ?");
                sql.addSql(" and ");
                sql.addSql("   USR_SID = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_PRIVATE);
                sql.addIntValue(userSid);
            }
            sql.addSql(" and ");
            sql.addSql("   RTC_SID = ?");
            sql.addIntValue(catSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sort = rs.getInt("MAX_SORT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return sort;
    }

    /**
     * <br>[機  能] 並び順の最小値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param tplMode テンプレートモード 0:全て 1:共有 2:個人
     * @param userSid ユーザSID
     * @param catSid カテゴリSID
     * @return 並び順の最大値
     * @throws SQLException SQL実行時例外
     */
    public int getMinSort(int tplMode, int userSid, int catSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int sort = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   COALESCE(min(RTP_SORT), 0) as MIN_SORT");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where");

            //テンプレートモード：共有
            if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
                sql.addSql("   RTP_TYPE = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_SHARE);
            //テンプレートモード：個人
            } else if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
                sql.addSql("   RTP_TYPE = ?");
                sql.addSql(" and ");
                sql.addSql("   USR_SID = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_PRIVATE);
                sql.addIntValue(userSid);
            }
            sql.addSql(" and ");
            sql.addSql("   RTC_SID = ?");
            sql.addIntValue(catSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sort = rs.getInt("MIN_SORT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return sort;
    }

    /**
     * <p>Delete RNG_TEMPLATE
     * @param bean RNG_TEMPLATE Model
     * @throws SQLException SQL実行例外
     * @return count
     */
    public  int delete(RngTemplateModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE");

            pstmt = con.prepareStatement(sql.toSqlString());

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
     * <p>Delete RNG_TEMPLATE
     * @param rtpSid 稟議テンプレートSID
     * @throws SQLException SQL実行例外
     * @return count
     */
    public  int deleteTpl(int rtpSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where");
            sql.addSql("   RTP_SID = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtpSid);

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
     * <br>[機  能] 並び順を登録日順に更新します
     * <br>[解  説]
     * <br>[備  考]
     * @param tplMode テンプレートモード 1:共有 2:個人
     * @param userSid ユーザSID
     * @param now 現在日付
     * @param rtcSid カテゴリSID
     * @throws SQLException SQL実行時例外
     */
    public void updateSortAll(int tplMode, int userSid, UDate now, int rtcSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RTP_SID");
            sql.addSql(" from ");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where");
            sql.addSql("   RTC_SID = ?");
            sql.addIntValue(rtcSid);
            //テンプレートモード：共有
            if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
                sql.addSql(" and ");
                sql.addSql("   RTP_TYPE = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_SHARE);
            //テンプレートモード：個人
            } else if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
                sql.addSql(" and ");
                sql.addSql("   RTP_TYPE = ?");
                sql.addSql(" and ");
                sql.addSql("   USR_SID = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_PRIVATE);
                sql.addIntValue(userSid);
            }
            sql.addSql(" order by");
            sql.addSql("   RTP_SORT,");
            sql.addSql("   RTP_ADATE");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            for (int sort = 1; rs.next(); sort++) {
                int rtpSid = rs.getInt("RTP_SID");
                updateSort(rtpSid, sort, userSid, now);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] 並び順を更新します
     * <br>[解  説]
     * <br>[備  考]
     * @param tplMode テンプレートモード 1:共有 2:個人
     * @param userSid ユーザSID
     * @param now 現在日付
     * @param sort ソート
     * @param catSid カテゴリSID
     * @throws SQLException SQL実行時例外
     */
    public void updateSortAll2(int tplMode, int userSid, UDate now, int sort, int catSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RTP_SID,");
            sql.addSql("   RTP_SORT");
            sql.addSql(" from ");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where");
            //テンプレートモード：共有
            if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
                sql.addSql("   RTP_TYPE = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_SHARE);
            //テンプレートモード：個人
            } else if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
                sql.addSql("   RTP_TYPE = ?");
                sql.addSql(" and ");
                sql.addSql("   USR_SID = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_PRIVATE);
                sql.addIntValue(userSid);
            }
            sql.addSql(" and ");
            sql.addSql("   RTC_SID = ?");
            sql.addIntValue(catSid);
            sql.addSql(" and ");
            sql.addSql("   RTP_SORT > ?");
            sql.addIntValue(sort);
            sql.addSql(" order by");
            sql.addSql("   RTP_SORT");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int rtpSid = rs.getInt("RTP_SID");
                int rtpSort = rs.getInt("RTP_SORT") - 1;
                updateSort(rtpSid, rtpSort, userSid, now);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] 指定した並び順の稟議テンプレートの並び順を更新します
     * <br>[解  説]
     * <br>[備  考]
     * @param tplMode テンプレートモード 1:共有 2:個人
     * @param rtpSort ソート順
     * @param userSid ユーザSID
     * @param now 現在日付
     * @param catSid カテゴリSID
     * @throws SQLException SQL実行時例外
     */
    public void updateSortToOrder(int tplMode, int rtpSort, int userSid, UDate now, int catSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update ");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" set ");
            sql.addSql("   RTP_SORT = ?,");
            sql.addSql("   RTP_EUID = ?,");
            sql.addSql("   RTP_EDATE = ?");
            sql.addIntValue(rtpSort);
            sql.addIntValue(userSid);
            sql.addDateValue(now);

            sql.addSql(" where");
            sql.addSql("   RTP_SORT = ?");
            sql.addIntValue(rtpSort);

            sql.addSql(" and");
            //テンプレートモード：共有
            if (tplMode == RngConst.RNG_TEMPLATE_SHARE) {
                sql.addSql("   RTP_TYPE = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_SHARE);
            //テンプレートモード：個人
            } else if (tplMode == RngConst.RNG_TEMPLATE_PRIVATE) {
                sql.addSql("   RTP_TYPE = ?");
                sql.addSql(" and ");
                sql.addSql("   USR_SID = ?");

                sql.addIntValue(RngConst.RNG_TEMPLATE_PRIVATE);
                sql.addIntValue(userSid);
            }
            sql.addSql(" and ");
            sql.addSql("   RTC_SID = ?");
            sql.addIntValue(catSid);
            sql.addSql(" order by");
            sql.addSql("   RTP_SORT,");
            sql.addSql("   RTP_ADATE");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] 並び順を更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param rtpSid 稟議テンプレートSID
     * @param rtpSort 並び順
     * @param userSid ユーザSID
     * @param now 現在日時
     * @throws SQLException SQL実行時例外
     */
    public void updateSort(int rtpSid, int rtpSort, int userSid, UDate now)
    throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" set ");
            sql.addSql("   RTP_SORT=?,");
            sql.addSql("   RTP_EUID=?,");
            sql.addSql("   RTP_EDATE=?");
            sql.addSql(" where ");
            sql.addSql("   RTP_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtpSort);
            sql.addIntValue(userSid);
            sql.addDateValue(now);
            //where
            sql.addIntValue(rtpSid);

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
     * <br>[機  能] 指定したカテゴリSIDのテンプレートを削除します
     * <br>[解  説]
     * <br>[備  考]
     * @param rtcSid カテゴリSID
     * @throws SQLException SQL実行例外
     * @return count
     */
    public  int deleteTplCat(int rtcSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE");
            sql.addSql(" where");
            sql.addSql("   RTC_SID = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(rtcSid);

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
     * <p>Create RNG_TEMPLATE Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created RngTemplateModel
     * @throws SQLException SQL実行例外
     */
    private RngTemplateModel __getRngTemplateFromRs(ResultSet rs) throws SQLException {
        RngTemplateModel bean = new RngTemplateModel();
        bean.setRtpSid(rs.getInt("RTP_SID"));
        bean.setRtpType(rs.getInt("RTP_TYPE"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setRtpTitle(rs.getString("RTP_TITLE"));
        bean.setRtpRngTitle(rs.getString("RTP_RNG_TITLE"));
        bean.setRtpContent(rs.getString("RTP_CONTENT"));
        bean.setRtpSort(rs.getInt("RTP_SORT"));
        bean.setRtpAuid(rs.getInt("RTP_AUID"));
        bean.setRtpAdate(UDate.getInstanceTimestamp(rs.getTimestamp("RTP_ADATE")));
        bean.setRtpEuid(rs.getInt("RTP_EUID"));
        bean.setRtpEdate(UDate.getInstanceTimestamp(rs.getTimestamp("RTP_EDATE")));
        bean.setRtcSid(rs.getInt("RTC_SID"));
        return bean;
    }
}
