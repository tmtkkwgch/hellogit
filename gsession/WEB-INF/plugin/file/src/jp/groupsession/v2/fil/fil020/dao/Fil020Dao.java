package jp.groupsession.v2.fil.fil020.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileFileBinDao;
import jp.groupsession.v2.fil.fil020.model.FileAccessUserModel;
import jp.groupsession.v2.fil.fil020.model.FileHistoryModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>キャビネット詳細画面で使用するDAOクラス
 *
 * @author JTS DaoGenerator version 0.5
 */
public class Fil020Dao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil020Dao.class);

    /**
     * <p>Default Constructor
     */
    public Fil020Dao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public Fil020Dao(Connection con) {
        super(con);
    }

    /**
     * <p>更新履歴一覧の件数を取得する。
     * @param fdrSid ディレクトリSID
     * @param authUsrSid アクセス制限ユーザSID（特権ユーザの場合は、-1で指定）
     * @return 履歴件数
     * @throws SQLException SQL実行例外
     */
    public int getRekiCount(int fdrSid, int authUsrSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   count(*) CNT");
            sql.addSql(" from");
            sql.addSql("   FILE_FILE_REKI REKI");
            sql.addSql(" inner join");
            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION");
            sql.addSql("    from FILE_FILE_REKI group by FDR_SID) DIR_MAXVERSION");
            sql.addSql(" on REKI.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" left join");
            sql.addSql("   FILE_DIRECTORY DIR");
            sql.addSql(" on DIR.FDR_SID = REKI.FDR_SID");
            sql.addSql(" and DIR.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");

            sql.addSql(" where");
            sql.addSql("   REKI.FFR_PARENT_SID = ?");
            sql.addIntValue(fdrSid);

            //閲覧が許可されていない場合は対象外とする
            if (authUsrSid != -1) {
                sql.addSql(" and (");
                sql.addSql("   DIR.FDR_SID is null");
                sql.addSql(" or");
                sql.addSql("   DIR.FDR_ACCESS_SID = ?");
                sql.addSql(" or exists");
                sql.addSql("   (select *");
                sql.addSql("    from");
                sql.addSql("      FILE_DACCESS_CONF A");
                sql.addSql("    where");
                sql.addSql("      A.FDR_SID = DIR.FDR_ACCESS_SID");
                sql.addSql("    and (");
                sql.addSql("      (A.USR_KBN = ? and");
                sql.addSql("       A.USR_SID = ?) or");
                sql.addSql("      (A.USR_KBN = ? and");
                sql.addSql("       exists");
                sql.addSql("         (select *");
                sql.addSql("          from");
                sql.addSql("            CMN_BELONGM B");
                sql.addSql("          where");
                sql.addSql("            B.GRP_SID = A.USR_SID");
                sql.addSql("          and");
                sql.addSql("            B.USR_SID = ?");
                sql.addSql("         )))");
                sql.addSql("   ))");
                sql.addIntValue(GSConstFile.DIRECTORY_ROOT);
                sql.addIntValue(GSConstFile.USER_KBN_USER);
                sql.addIntValue(authUsrSid);
                sql.addIntValue(GSConstFile.USER_KBN_GROUP);
                sql.addIntValue(authUsrSid);
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
     * <p>更新履歴一覧を取得する。
     * @param fdrSid ディレクトリSID
     * @param orderKey オーダーキー
     * @param sortKey ソートキー
     * @param start 取得開始位置
     * @param limit 取得件数(上限値)
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileHistoryModel> getRekiList(int fdrSid, int orderKey, int sortKey,
            int start, int limit) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileHistoryModel> ret = new ArrayList<FileHistoryModel>();
        HashMap<Integer, Integer> newVerMap = new HashMap<Integer, Integer>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   CMN_USRM.USR_SID,");
            sql.addSql("   CMN_USRM.USR_JKBN,");
            sql.addSql("   CMN_USRM_INF.USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI,");
            sql.addSql("   FILE_FILE_REKI.FDR_SID,");
            sql.addSql("   FILE_FILE_REKI.FDR_VERSION,");
            sql.addSql("   FILE_FILE_REKI.FFR_FNAME,");
            sql.addSql("   FILE_FILE_REKI.FFR_KBN,");
            sql.addSql("   FILE_FILE_REKI.FFR_EUID,");
            sql.addSql("   FILE_FILE_REKI.FFR_EDATE,");
            sql.addSql("   FILE_FILE_REKI.FFR_PARENT_SID,");
            sql.addSql("   FILE_FILE_BIN.BIN_SID");

            sql.addSql(" from ");
            sql.addSql("   FILE_DIRECTORY");
            sql.addSql("   left join");
            sql.addSql("     FILE_FILE_BIN");
            sql.addSql("   on");
            sql.addSql("     FILE_DIRECTORY.FDR_SID = FILE_FILE_BIN.FDR_SID");
            sql.addSql("   and");
            sql.addSql("     FILE_DIRECTORY.FDR_VERSION = FILE_FILE_BIN.FDR_VERSION,");

            sql.addSql("   FILE_FILE_REKI,");
            sql.addSql("   CMN_USRM_INF,");
            sql.addSql("   CMN_USRM");

            sql.addSql(" where ");
            sql.addSql("   FILE_FILE_REKI.FFR_PARENT_SID = ?");
            sql.addSql(" and ");
            sql.addSql("   FILE_FILE_REKI.FDR_SID = FILE_DIRECTORY.FDR_SID");
            sql.addSql(" and ");
            sql.addSql("   FILE_FILE_REKI.FDR_VERSION = FILE_DIRECTORY.FDR_VERSION");
            sql.addSql(" and ");
            sql.addSql("   FILE_FILE_REKI.FFR_EUID = CMN_USRM.USR_SID");
            sql.addSql(" and ");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");

            //オーダーキー
            String order = "ASC";
            if (orderKey == GSConst.ORDER_KEY_DESC) {
                order = "DESC";
            }

            sql.addSql(" order by ");
            if (sortKey == 1) {
                //更新日時
                sql.addSql(" FILE_FILE_REKI.FFR_EDATE　" + order);
                sql.addSql(" , FILE_FILE_REKI.FFR_FNAME ");
            } else if (sortKey == 2) {
                //更新者
                sql.addSql(" CMN_USRM_INF.USI_SEI_KN " + order);
                sql.addSql(" , CMN_USRM_INF.USI_MEI_KN, ");
                sql.addSql(" FILE_FILE_REKI.FFR_EDATE ");
            } else if (sortKey == 3) {
                //ファイル名
                sql.addSql(" FILE_FILE_REKI.FFR_FNAME " + order);
                sql.addSql(", FILE_FILE_REKI.FFR_EDATE ");
            } else if (sortKey == 4) {
                //操作
                sql.addSql(" FILE_FILE_REKI.FFR_KBN " + order);
                sql.addSql(", FILE_FILE_REKI.FFR_EDATE ");
            }

            pstmt = con.prepareStatement(sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE ,
                    ResultSet.CONCUR_READ_ONLY);
            sql.addIntValue(fdrSid);
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            if (start > 1) {
                rs.absolute(start - 1);
            }

            int newVersion = 0;
            for (int i = 0; rs.next() && i < limit; i++) {

                int dirSid = rs.getInt("FDR_SID");
                int version = rs.getInt("FDR_VERSION");

                FileHistoryModel bean = new FileHistoryModel();
                bean.setUsrSid(rs.getInt("USR_SID"));
                bean.setUsrJkbn(rs.getInt("USR_JKBN"));
                bean.setUsrSeiMei(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                bean.setFdrSid(rs.getInt("FDR_SID"));
                bean.setFfrVersion(rs.getInt("FDR_VERSION"));
                bean.setFfrName(rs.getString("FFR_FNAME"));
                bean.setFfrKbn(rs.getInt("FFR_KBN"));
                bean.setFfrEuid(rs.getInt("FFR_EUID"));
                UDate edate = UDate.getInstanceTimestamp(rs.getTimestamp("FFR_EDATE"));
                bean.setFfrEdate(UDateUtil.getSlashYYMD(edate)
                        + " " + UDateUtil.getSeparateHM(edate));
                bean.setBinSid(rs.getLong("BIN_SID"));

                if (newVerMap.containsKey(dirSid)) {
                    newVersion = newVerMap.get(dirSid);
                    if (newVersion < version) {
                        newVerMap.remove(dirSid);
                        newVerMap.put(dirSid, version);
                    }
                } else {
                    newVerMap.put(dirSid, version);
                }

                ret.add(bean);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        //復旧ボタンの表示フラグを設定する
        ret = __setRepairBtnDsp(ret, newVerMap);

        return ret;
    }

    /**
     * <p>更新履歴一覧を取得する。(ファイルの実体がないものも取得する。)
     * @param fdrSid ディレクトリSID
     * @param authUsrSid アクセス制限ユーザSID（特権ユーザの場合は、-1で指定）
     * @param orderKey オーダーキー
     * @param sortKey ソートキー
     * @param start 取得開始位置
     * @param limit 取得件数(上限値)
     * @return List in FILE_DIRECTORYModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileHistoryModel> getRekiListAll(int fdrSid, int authUsrSid,
            int orderKey, int sortKey, int start, int limit) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileHistoryModel> ret = new ArrayList<FileHistoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select ");
            sql.addSql("   CMN_USRM.USR_SID,");
            sql.addSql("   CMN_USRM.USR_JKBN,");
            sql.addSql("   CMN_USRM_INF.USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI,");
            sql.addSql("   CMN_GROUPM.GRP_NAME,");
            sql.addSql("   REKI.FDR_SID,");
            sql.addSql("   REKI.FDR_VERSION,");
            sql.addSql("   REKI.FFR_FNAME,");
            sql.addSql("   REKI.FFR_KBN,");
            sql.addSql("   REKI.FFR_EUID,");
            sql.addSql("   REKI.FFR_EGID,");
            sql.addSql("   REKI.FFR_EDATE,");
            sql.addSql("   REKI.FFR_PARENT_SID,");
            sql.addSql("   REKI.FFR_UP_CMT,");
            sql.addSql("   DIR.FDR_JTKBN,");
            sql.addSql("   DIR_MAXVERSION.MAXVERSION,");
            if (authUsrSid != -1) {
                sql.addSql("   coalesce(DACCESS.FDA_AUTH, ?) as ACKBN");
            } else {
                sql.addSql("   ? as ACKBN");
            }
            sql.addIntValue(Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));

            sql.addSql(" from");
            sql.addSql("   FILE_FILE_REKI REKI");
            sql.addSql(" inner join");
            sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION");
            sql.addSql("    from FILE_FILE_REKI group by FDR_SID) DIR_MAXVERSION");
            sql.addSql(" on REKI.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql(" left join");
            sql.addSql("   FILE_DIRECTORY DIR");
            sql.addSql(" on DIR.FDR_SID = REKI.FDR_SID");
            sql.addSql(" and DIR.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
            sql.addSql("   left join CMN_GROUPM");
            sql.addSql("     on REKI.FFR_EGID = CMN_GROUPM.GRP_SID,");

            //閲覧が許可されていない場合は対象外とする
            if (authUsrSid != -1) {
                sql.addSql("   ( ");
                sql.addSql("   select");
                sql.addSql("     ? as FDR_SID,");
                sql.addSql("     ? as FDA_AUTH");
                sql.addSql("   union all");
                sql.addSql("   select");
                sql.addSql("     A.FDR_SID,");
                sql.addSql("     max(A.FDA_AUTH) FDA_AUTH");
                sql.addSql("   from");
                sql.addSql("     FILE_DACCESS_CONF A");
                sql.addSql("   where");
                sql.addSql("     exists (");
                sql.addSql("       select *");
                sql.addSql("       from");
                sql.addSql("         FILE_DIRECTORY D,");
                sql.addSql("         FILE_FILE_REKI R");
                sql.addSql("       where");
                sql.addSql("         D.FDR_ACCESS_SID = A.FDR_SID");
                sql.addSql("       and");
                sql.addSql("         D.FDR_SID = R.FDR_SID");
                sql.addSql("       and");
                sql.addSql("         R.FFR_PARENT_SID = ?)");
                sql.addSql("   and (");
                sql.addSql("     (A.USR_KBN = ? and");
                sql.addSql("      A.USR_SID = ?) or");
                sql.addSql("     (A.USR_KBN = ? and");
                sql.addSql("      exists");
                sql.addSql("      (select *");
                sql.addSql("         from CMN_BELONGM B");
                sql.addSql("        where B.GRP_SID = A.USR_SID");
                sql.addSql("          and B.USR_SID = ?");
                sql.addSql("      )))");
                sql.addSql("   group by");
                sql.addSql("     A.FDR_SID");
                sql.addSql("   ) DACCESS,");
                sql.addIntValue(GSConstFile.DIRECTORY_ROOT);
                sql.addIntValue(Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE));
                sql.addIntValue(fdrSid);
                sql.addIntValue(GSConstFile.USER_KBN_USER);
                sql.addIntValue(authUsrSid);
                sql.addIntValue(GSConstFile.USER_KBN_GROUP);
                sql.addIntValue(authUsrSid);
            }

            sql.addSql("   CMN_USRM_INF,");
            sql.addSql("   CMN_USRM");

            sql.addSql(" where ");
            sql.addSql("   REKI.FFR_PARENT_SID = ?");
            sql.addSql(" and ");
            sql.addSql("   REKI.FFR_EUID = CMN_USRM.USR_SID");
            sql.addSql(" and ");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addIntValue(fdrSid);

            if (authUsrSid != -1) {
                sql.addSql(" and");
                sql.addSql("   DIR.FDR_ACCESS_SID = DACCESS.FDR_SID");
            }

            //オーダーキー
            String order = "ASC";
            if (orderKey == GSConst.ORDER_KEY_DESC) {
                order = "DESC";
            }

            sql.addSql(" order by ");
            if (sortKey == 1) {
                //更新日時
                sql.addSql(" REKI.FFR_EDATE " + order);
                sql.addSql(" , REKI.FFR_FNAME ");
            } else if (sortKey == 2) {
                //更新者
                sql.addSql(" CMN_USRM_INF.USI_SEI_KN " + order);
                sql.addSql(" , CMN_USRM_INF.USI_MEI_KN, ");
                sql.addSql(" REKI.FFR_EDATE ");
            } else if (sortKey == 3) {
                //ファイル名
                sql.addSql(" REKI.FFR_FNAME " + order);
                sql.addSql(", REKI.FFR_EDATE ");
            } else if (sortKey == 4) {
                //操作
                sql.addSql(" REKI.FFR_KBN " + order);
                sql.addSql(", REKI.FFR_EDATE ");
            }

            //ページング
            sql.setPagingValue(start - 1, limit);

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            while (rs.next()) {

                FileHistoryModel bean = new FileHistoryModel();
                bean.setUsrSid(rs.getInt("USR_SID"));
                bean.setUsrJkbn(rs.getInt("USR_JKBN"));
                if (rs.getInt("FFR_EGID") > 0) {
                    bean.setUsrSeiMei(rs.getString("GRP_NAME"));
                } else {
                    bean.setUsrSeiMei(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                }
                bean.setFdrSid(rs.getInt("FDR_SID"));
                bean.setFfrVersion(rs.getInt("FDR_VERSION"));
                bean.setFfrName(rs.getString("FFR_FNAME"));
                bean.setFfrKbn(rs.getInt("FFR_KBN"));
                bean.setFfrUpCmt(
                        StringUtilHtml.transToHTmlPlusAmparsant(rs.getString("FFR_UP_CMT")));
                bean.setFfrEuid(rs.getInt("FFR_EUID"));
                bean.setFfrEgid(rs.getInt("FFR_EGID"));
                UDate edate = UDate.getInstanceTimestamp(rs.getTimestamp("FFR_EDATE"));
                bean.setFfrEdate(UDateUtil.getSlashYYMD(edate)
                        + " " + UDateUtil.getSeparateHM(edate));
                bean.setFdrJtkbn(rs.getInt("FDR_JTKBN"));

                //編集許可がされていない場合は、復旧ボタンの表示しない
                if (rs.getInt("ACKBN") == Integer.parseInt(GSConstFile.ACCESS_KBN_WRITE)) {
                    int maxversion = rs.getInt("MAXVERSION");
                    bean.setRepairBtnDspFlg(__getRepairBtnDsp(bean, maxversion));
                } else {
                    bean.setRepairBtnDspFlg(false);
                }

                ret.add(bean);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        //バイナリSIDを取得する。
        FileFileBinDao binDao = new FileFileBinDao(con);
        if (ret != null && ret.size() > 0) {
            for (FileHistoryModel model : ret) {
                model.setBinSid(binDao.getCmnBinSid(model.getFdrSid(), model.getFfrVersion()));

                //ファイルの実体がない場合は、復旧ボタンは表示しない
                if (model.getBinSid() < 1) {
                    model.setRepairBtnDspFlg(false);
                }
            }
        }

        return ret;
    }

    /**
     * <p>更新履歴一覧の復旧ボタンの表示フラグを設定する。
     * @param rekiList 更新履歴一覧
     * @param newVerMap 最新バージョンマップ
     * @return List in Fil050Model
     * @throws SQLException SQL実行例外
     */
    private ArrayList<FileHistoryModel> __setRepairBtnDsp(
            ArrayList<FileHistoryModel> rekiList, HashMap<Integer, Integer> newVerMap)
    throws SQLException {

        int version = -1;
        for (FileHistoryModel model : rekiList) {
            if (model.getBinSid() < 1) {
                model.setRepairBtnDspFlg(false);
                continue;
            }

            version = newVerMap.get(model.getFdrSid());
            model.setRepairBtnDspFlg(__getRepairBtnDsp(model, version));
        }

        return rekiList;
    }

    /**
     * <p>更新履歴の復旧ボタンの表示フラグを取得する。
     * @param rekibean 更新履歴情報
     * @param newversion 最新バージョン
     * @return List in Fil050Model
     * @throws SQLException SQL実行例外
     */
    private boolean __getRepairBtnDsp(
            FileHistoryModel rekibean, int newversion)
    throws SQLException {

        if (rekibean.getFdrJtkbn() == GSConstFile.JTKBN_NORMAL) {

            if (newversion == rekibean.getFfrVersion()
                    && rekibean.getFfrKbn() != GSConstFile.REKI_KBN_DELETE) {
                //最新バージョン
                return false;
            } else {
                return true;
            }

        } else {
            if (newversion == rekibean.getFfrVersion()
                    && rekibean.getFfrKbn() == GSConstFile.REKI_KBN_DELETE) {
                //最新バージョン
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * <p>キャビネット詳細で使用する、アクセス制御情報を取得する。
     * @param fcbSid キャビネットSID
     * @param sortKey ソート項目
     * @param orderKey ソートオーダー
     * @param start 取得開始位置
     * @param limit 取得件数(上限値)
     * @return 検索にヒットしたユーザデータ CmnUsrmInfModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<FileAccessUserModel> getAccessList(int fcbSid, int sortKey,
            int orderKey, int start, int limit) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<FileAccessUserModel> ret = new ArrayList<FileAccessUserModel>();
        con = getCon();

        try {

            //キャビネットSIDからアクセス制御一覧取得
            SqlBuffer sql = __getUserAccessSql(fcbSid, sortKey, orderKey, false);
            sql.addSql(" limit " + limit + " offset " + (start - 1));
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE ,
                    ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            /*if (start > 1) {
                rs.absolute(start - 1);
            }

            for (int i = 0; rs.next() && i < limit; i++) {
                ret.add(__getFileAccessUserModelFromRs(rs));
            }*/
            while (rs.next()) {
                ret.add(__getFileAccessUserModelFromRs(rs));
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
     * <p>キャビネット詳細で使用する、アクセス制御情報の件数を取得する。
     * @param fcbSid キャビネットSID
     * @return 検索にヒットしたユーザデータ CmnUsrmInfModel
     * @throws SQLException SQL実行例外
     */
    public int getAccessListCount(int fcbSid) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            SqlBuffer sql = __getUserAccessSql(fcbSid, 0 , 0, true);
            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret++;
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
     * <p>キャビネットに対するアクセス権限制御用一覧を取得するSQLを取得する。
     * @param fcbSid キャビネットSID
     * @param sortKey ソート項目
     * @param orderKey ソートオーダー
     * @param countFlg カウント用フラグ
     * @return 検索にヒットしたユーザデータ CmnUsrmInfModel
     * @throws SQLException SQL実行例外
     */
    private SqlBuffer __getUserAccessSql(int fcbSid,
            int sortKey, int orderKey, boolean countFlg) throws SQLException {

        //SQL文
        SqlBuffer sql = new SqlBuffer();
        sql.addSql(" select * from (");
        sql.addSql(" select");
        sql.addSql("   CMN_USRM_INF.USR_SID,");
        sql.addSql("   CMN_USRM_INF.USI_SEI,");
        sql.addSql("   CMN_USRM_INF.USI_MEI,");
        sql.addSql("   CMN_USRM_INF.USI_SEI_KN,");
        sql.addSql("   CMN_USRM_INF.USI_MEI_KN,");
        sql.addSql("   CMN_USRM_INF.USI_SINI,");
        sql.addSql("   CMN_USRM_INF.USI_BDATE,");
        sql.addSql("   CMN_USRM_INF.USI_ZIP1,");
        sql.addSql("   CMN_USRM_INF.USI_ZIP2,");
        sql.addSql("   CMN_USRM_INF.TDF_SID,");
        sql.addSql("   CMN_USRM_INF.USI_ADDR1,");
        sql.addSql("   CMN_USRM_INF.USI_ADDR2,");
        sql.addSql("   CMN_USRM_INF.USI_TEL1,");
        sql.addSql("   CMN_USRM_INF.USI_TEL_NAI1,");
        sql.addSql("   CMN_USRM_INF.USI_TEL_CMT1,");
        sql.addSql("   CMN_USRM_INF.USI_TEL2,");
        sql.addSql("   CMN_USRM_INF.USI_TEL_NAI2,");
        sql.addSql("   CMN_USRM_INF.USI_TEL_CMT2,");
        sql.addSql("   CMN_USRM_INF.USI_TEL3,");
        sql.addSql("   CMN_USRM_INF.USI_TEL_NAI3,");
        sql.addSql("   CMN_USRM_INF.USI_TEL_CMT3,");
        sql.addSql("   CMN_USRM_INF.USI_FAX1,");
        sql.addSql("   CMN_USRM_INF.USI_FAX_CMT1,");
        sql.addSql("   CMN_USRM_INF.USI_FAX2,");
        sql.addSql("   CMN_USRM_INF.USI_FAX_CMT2,");
        sql.addSql("   CMN_USRM_INF.USI_FAX3,");
        sql.addSql("   CMN_USRM_INF.USI_FAX_CMT3,");
        sql.addSql("   CMN_USRM_INF.USI_MAIL1,");
        sql.addSql("   CMN_USRM_INF.USI_MAIL_CMT1,");
        sql.addSql("   CMN_USRM_INF.USI_MAIL2,");
        sql.addSql("   CMN_USRM_INF.USI_MAIL_CMT2,");
        sql.addSql("   CMN_USRM_INF.USI_MAIL3,");
        sql.addSql("   CMN_USRM_INF.USI_MAIL_CMT3,");
        sql.addSql("   CMN_USRM_INF.USI_SYAIN_NO,");
        sql.addSql("   CMN_USRM_INF.USI_SYOZOKU,");
        sql.addSql("   (case");
        sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then ''");
//        sql.addSql("      else (select");
//        sql.addSql("            POS_NAME");
//        sql.addSql("          from");
//        sql.addSql("            CMN_POSITION");
//        sql.addSql("          where CMN_USRM_INF.POS_SID = CMN_POSITION.POS_SID)");
        sql.addSql("      else CMN_POSITION.POS_NAME");
        sql.addSql("    end) as USI_YAKUSYOKU,");
        sql.addSql("   CMN_USRM_INF.USI_BIKO,");
        sql.addSql("   CMN_USRM_INF.BIN_SID,");
        sql.addSql("   CMN_USRM_INF.USI_PICT_KF,");
        sql.addSql("   CMN_USRM_INF.USI_BDATE_KF,");
        sql.addSql("   CMN_USRM_INF.USI_MAIL1_KF,");
        sql.addSql("   CMN_USRM_INF.USI_MAIL2_KF,");
        sql.addSql("   CMN_USRM_INF.USI_MAIL3_KF,");
        sql.addSql("   CMN_USRM_INF.USI_ZIP_KF,");
        sql.addSql("   CMN_USRM_INF.USI_TDF_KF,");
        sql.addSql("   CMN_USRM_INF.USI_ADDR1_KF,");
        sql.addSql("   CMN_USRM_INF.USI_ADDR2_KF,");
        sql.addSql("   CMN_USRM_INF.USI_TEL1_KF,");
        sql.addSql("   CMN_USRM_INF.USI_TEL2_KF,");
        sql.addSql("   CMN_USRM_INF.USI_TEL3_KF,");
        sql.addSql("   CMN_USRM_INF.USI_FAX1_KF,");
        sql.addSql("   CMN_USRM_INF.USI_FAX2_KF,");
        sql.addSql("   CMN_USRM_INF.USI_FAX3_KF,");
        sql.addSql("   CMN_USRM_INF.USI_LTLGIN,");
        sql.addSql("   CMN_USRM_INF.USI_AUID,");
        sql.addSql("   CMN_USRM_INF.USI_ADATE,");
        sql.addSql("   CMN_USRM_INF.USI_EUID,");
        sql.addSql("   CMN_USRM_INF.USI_EDATE,");
        sql.addSql("   (case");
        sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then 1");
        sql.addSql("      else 0");
        sql.addSql("    end) as YAKUSYOKU_EXIST,");
        //SQL遅延のため修正 ↓
/*        sql.addSql("   (case");
        sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then 0");
        sql.addSql("      else (select");
        sql.addSql("              POS_SORT");
        sql.addSql("            from");
        sql.addSql("              CMN_POSITION");
        sql.addSql("            where CMN_USRM_INF.POS_SID = CMN_POSITION.POS_SID)");
        sql.addSql("    end) as YAKUSYOKU_SORT,");

        sql.addSql("  (case");
        sql.addSql("   when");
        sql.addSql("   EXISTS( ");
        sql.addSql("   select FCB_ACCESS_KBN from FILE_CABINET");
        sql.addSql("   where FILE_CABINET.FCB_SID=?");
        sql.addSql("   and FCB_ACCESS_KBN=0");
        sql.addSql("   ) then 1");
        //アクセス制御に設定したユーザだけ表示
//        sql.addSql("   when ");
//        sql.addSql("   EXISTS( ");
//        sql.addSql("   select ");
//        sql.addSql("     *");
//        sql.addSql("   from ");
//        sql.addSql("     CMN_BELONGM");
//        sql.addSql("   where ");
//        sql.addSql("     CMN_BELONGM.GRP_SID = 0");
//        sql.addSql("   and");
//        sql.addSql("     CMN_BELONGM.USR_SID = USRM.USR_SID");
//        sql.addSql("   ) then 1");
//        sql.addSql("   when ");
//        sql.addSql("   EXISTS( ");
//        sql.addSql("     select ");
//        sql.addSql("       *");
//        sql.addSql("     from ");
//        sql.addSql("       FILE_CRT_CONF ");
//        sql.addSql("     where ");
//        sql.addSql("       FILE_CRT_CONF.USR_SID = USRM.USR_SID ");
//        sql.addSql("     and ");
//        sql.addSql("       FILE_CRT_CONF.USR_KBN = 0 ");
//        sql.addSql("   ) then 1");
//        sql.addSql("   when ");
//        sql.addSql("   EXISTS( ");
//        sql.addSql("   select ");
//        sql.addSql("     *");
//        sql.addSql("   from ");
//        sql.addSql("     CMN_BELONGM, ");
//        sql.addSql("   ( ");
//        sql.addSql("     select ");
//        sql.addSql("       FILE_CRT_CONF.USR_SID");
//        sql.addSql("     from ");
//        sql.addSql("       FILE_CRT_CONF ");
//        sql.addSql("     where ");
//        sql.addSql("       FILE_CRT_CONF.USR_KBN = 1 ");
//        sql.addSql("   ) as CRT ");
//        sql.addSql("   where ");
//        sql.addSql("     CMN_BELONGM.GRP_SID = CRT.USR_SID");
//        sql.addSql("   and");
//        sql.addSql("     CMN_BELONGM.USR_SID = USRM.USR_SID");
//        sql.addSql("   ) then 1");
        sql.addSql("    when ");
        sql.addSql("    EXISTS(");
        sql.addSql("    select");
        sql.addSql("      USR_SID");
        sql.addSql("    from");
        sql.addSql("      FILE_CABINET_ADMIN");
        sql.addSql("    where");
        sql.addSql("      FILE_CABINET_ADMIN.USR_SID = USRM.USR_SID");
        sql.addSql("    and");
        sql.addSql("      FILE_CABINET_ADMIN.FCB_SID=?");
        sql.addSql("    ) then 1");
        sql.addSql("    when ");
        sql.addSql("    EXISTS(");
        sql.addSql("    select");
        sql.addSql("      USR_SID");
        sql.addSql("    from ");
        sql.addSql("      FILE_ACCESS_CONF ");
        sql.addSql("    where ");
        sql.addSql("      FILE_ACCESS_CONF.USR_SID = USRM.USR_SID");
        sql.addSql("    and");
        sql.addSql("      FILE_ACCESS_CONF.FCB_SID = ?");
        sql.addSql("    and");
        sql.addSql("      FILE_ACCESS_CONF.USR_KBN = 0");
        sql.addSql("    and");
        sql.addSql("      FILE_ACCESS_CONF.FAA_AUTH=1");
        sql.addSql("    ) then 1");
        sql.addSql("   when ");
        sql.addSql("   EXISTS( ");
        sql.addSql("   select ");
        sql.addSql("     *");
        sql.addSql("   from ");
        sql.addSql("     CMN_BELONGM, ");
        sql.addSql("   ( ");
        sql.addSql("     select ");
        sql.addSql("       FILE_ACCESS_CONF.USR_SID");
        sql.addSql("     from ");
        sql.addSql("       FILE_ACCESS_CONF ");
        sql.addSql("     where ");
        sql.addSql("       FILE_ACCESS_CONF.FCB_SID = ? ");
        sql.addSql("     and ");
        sql.addSql("       FILE_ACCESS_CONF.USR_KBN = 1 ");
        sql.addSql("    and");
        sql.addSql("      FILE_ACCESS_CONF.FAA_AUTH=1");
        sql.addSql("   ) as GROUP1 ");
        sql.addSql("   where ");
        sql.addSql("     CMN_BELONGM.GRP_SID = GROUP1.USR_SID");
        sql.addSql("   and");
        sql.addSql("     CMN_BELONGM.USR_SID = USRM.USR_SID");
        sql.addSql("   ) then 1");
        sql.addSql("    when ");
        sql.addSql("    EXISTS(");
        sql.addSql("    select");
        sql.addSql("      USR_SID");
        sql.addSql("    from ");
        sql.addSql("      FILE_ACCESS_CONF ");
        sql.addSql("    where ");
        sql.addSql("      FILE_ACCESS_CONF.USR_SID = USRM.USR_SID");
        sql.addSql("    and");
        sql.addSql("      FILE_ACCESS_CONF.FCB_SID = ?");
        sql.addSql("    and");
        sql.addSql("      FILE_ACCESS_CONF.USR_KBN = 0");
        sql.addSql("    and");
        sql.addSql("      FILE_ACCESS_CONF.FAA_AUTH=0");
        sql.addSql("    ) then 0");
        sql.addSql("   when ");
        sql.addSql("   EXISTS( ");
        sql.addSql("   select ");
        sql.addSql("     *");
        sql.addSql("   from ");
        sql.addSql("     CMN_BELONGM, ");
        sql.addSql("   ( ");
        sql.addSql("     select ");
        sql.addSql("       FILE_ACCESS_CONF.USR_SID ");
        sql.addSql("     from ");
        sql.addSql("       FILE_ACCESS_CONF ");
        sql.addSql("     where ");
        sql.addSql("       FILE_ACCESS_CONF.FCB_SID = ? ");
        sql.addSql("     and ");
        sql.addSql("       FILE_ACCESS_CONF.USR_KBN = 1 ");
        sql.addSql("    and");
        sql.addSql("      FILE_ACCESS_CONF.FAA_AUTH=0");
        sql.addSql("   ) as GROUP2 ");
        sql.addSql("   where ");
        sql.addSql("     CMN_BELONGM.GRP_SID = GROUP2.USR_SID ");
        sql.addSql("   and");
        sql.addSql("     CMN_BELONGM.USR_SID = USRM.USR_SID");
        sql.addSql("   ) then 0 ");
        sql.addSql("   else -9 end) as ACKBN,");
        sql.addSql("  (");
        sql.addSql("  case when");
        sql.addSql("  EXISTS(");
        sql.addSql("    select");
        sql.addSql("      USR_SID");
        sql.addSql("    from");
        sql.addSql("      FILE_CABINET_ADMIN");
        sql.addSql("    where");
        sql.addSql("      FILE_CABINET_ADMIN.USR_SID = USRM.USR_SID");
        sql.addSql("    and");
        sql.addSql("      FILE_CABINET_ADMIN.FCB_SID=?");
        sql.addSql("  ) then 1");
        sql.addSql("  else 0 end) as ADMKBN");
        sql.addIntValue(fcbSid);
        sql.addIntValue(fcbSid);
        sql.addIntValue(fcbSid);
        sql.addIntValue(fcbSid);
        sql.addIntValue(fcbSid);
        sql.addIntValue(fcbSid);
        sql.addIntValue(fcbSid);

        sql.addSql(" from");
        sql.addSql("   CMN_USRM USRM,");
        sql.addSql("   CMN_USRM_INF CMN_USRM_INF");*/
        //SQL遅延のため修正 ↑
        sql.addSql("   (case");
        sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then 0");
        sql.addSql("      else CMN_POSITION.POS_SORT");
        sql.addSql("    end) as YAKUSYOKU_SORT,");
        sql.addSql("   (case");
        sql.addSql("      when FILE_CABINET.FCB_ACCESS_KBN = 0");
        sql.addSql("      then 1");
        sql.addSql("      when FILE_CABINET_ADMIN.FCB_SID is not null");
        sql.addSql("      then 1");
        sql.addSql("      when FILE_ACCESS_CONF.FCB_SID is not null");
        sql.addSql("      then FILE_ACCESS_CONF.FAA_AUTH");
        sql.addSql("      else -9");
        sql.addSql("    end) as ACKBN,");
        sql.addSql("   (case");
        sql.addSql("      when FILE_CABINET_ADMIN.FCB_SID is not null then 1");
        sql.addSql("      else 0");
        sql.addSql("    end) as ADMKBN");
        sql.addSql(" from");
        sql.addSql("   CMN_USRM USRM");
        sql.addSql(" inner join");
        sql.addSql("   CMN_USRM_INF");
        sql.addSql(" on");
        sql.addSql("   USRM.USR_SID=CMN_USRM_INF.USR_SID");
        sql.addSql(" left join");
        sql.addSql("   CMN_POSITION");
        sql.addSql(" on");
        sql.addSql("   CMN_USRM_INF.POS_SID = CMN_POSITION.POS_SID");
        sql.addSql(" inner join");
        sql.addSql("   FILE_CABINET");
        sql.addSql(" on");
        sql.addSql("   FILE_CABINET.FCB_SID = ?");
        sql.addIntValue(fcbSid);
        sql.addSql(" left join");
        sql.addSql("   FILE_CABINET_ADMIN");
        sql.addSql(" on");
        sql.addSql("   FILE_CABINET.FCB_SID = FILE_CABINET_ADMIN.FCB_SID");
        sql.addSql(" and");
        sql.addSql("   USRM.USR_SID = FILE_CABINET_ADMIN.USR_SID");
        sql.addSql(" left join");
        sql.addSql("   (select");
        sql.addSql("      A.FCB_SID,");
        sql.addSql("      A.USR_SID,");
        sql.addSql("      max(A.FAA_AUTH) FAA_AUTH");
        sql.addSql("    from");
        sql.addSql("      (select");
        sql.addSql("         A.FCB_SID,");
        sql.addSql("         case when A.USR_KBN = ?");
        sql.addSql("              then A.USR_SID");
        sql.addSql("              else B.USR_SID");
        sql.addSql("              end as USR_SID,");
        sql.addSql("         A.FAA_AUTH");
        sql.addSql("       from");
        sql.addSql("         FILE_ACCESS_CONF A");
        sql.addSql("       left join");
        sql.addSql("         CMN_BELONGM B on B.GRP_SID = A.USR_SID");
        sql.addSql("       where");
        sql.addSql("         A.FCB_SID = ?");
        sql.addSql("      ) A");
        sql.addSql("    group by");
        sql.addSql("      A.FCB_SID,");
        sql.addSql("      A.USR_SID");
        sql.addSql("   ) FILE_ACCESS_CONF");
        sql.addSql(" on");
        sql.addSql("   FILE_CABINET.FCB_SID = FILE_ACCESS_CONF.FCB_SID");
        sql.addSql(" and");
        sql.addSql("   USRM.USR_SID = FILE_ACCESS_CONF.USR_SID");
        sql.addIntValue(GSConstFile.USER_KBN_USER);
        sql.addIntValue(fcbSid);

        sql.addSql(" where");

        sql.addSql("   USRM.USR_JKBN<>?");
//        sql.addSql(" and");
//        sql.addSql("   USRM.USR_SID=CMN_USRM_INF.USR_SID");
        //ユーザSID < 100は除外
        sql.addSql(" and");
        sql.addSql("   USRM.USR_SID>?");
        sql.addSql(" ) ACCESS ");
        sql.addSql(" where");
        sql.addSql("   ACKBN<>-9");

        String orderStr = "";
        //オーダー
        if (orderKey == GSConst.ORDER_KEY_ASC) {
            orderStr = "  asc";
        } else {
            orderStr = "  desc";
        }

        if (!countFlg) {
            log__.info("sortkey = " + sortKey);
            //ソートカラム
            switch (sortKey) {
                //氏名
                case GSConstUser.USER_SORT_NAME:
                    sql.addSql(" order by");
                    sql.addSql("   ACCESS.USI_SEI_KN");
                    sql.addSql(orderStr);
                    sql.addSql("   ,");
                    sql.addSql("   ACCESS.USI_MEI_KN");
                    sql.addSql(orderStr);
                    break;
                //社員/職員番号
                case GSConstUser.USER_SORT_SNO:
                    sql.addSql(" order by");
                    sql.addSql("   case when ACCESS.USI_SYAIN_NO is null then ''");
                    sql.addSql("   else ACCESS.USI_SYAIN_NO end ");
                    sql.addSql(orderStr);
                    sql.addSql("   ,");
                    sql.addSql("   ACCESS.USI_SEI_KN");
                    sql.addSql("    asc");
                    sql.addSql("   ,");
                    sql.addSql("   ACCESS.USI_MEI_KN");
                    sql.addSql("    asc");
                    break;
                //役職
                case GSConstUser.USER_SORT_YKSK:
                    sql.addSql(" order by");
                    sql.addSql("   ACCESS.YAKUSYOKU_EXIST");
                    sql.addSql(orderStr);
                    sql.addSql("  ,");
                    sql.addSql("   ACCESS.YAKUSYOKU_SORT");
                    sql.addSql(orderStr);
                    sql.addSql("   ,");
                    sql.addSql("   ACCESS.USI_SEI_KN");
                    sql.addSql("    asc");
                    sql.addSql("   ,");
                    sql.addSql("   ACCESS.USI_MEI_KN");
                    sql.addSql("    asc");
                    break;
                //キャビネット管理者
                case GSConstFile.USER_SORT_ADMIN:
                    sql.addSql(" order by");
                    sql.addSql("   ACCESS.ADMKBN");
                    sql.addSql(orderStr);
                    sql.addSql("   ,");
                    sql.addSql("   ACCESS.USI_SEI_KN");
                    sql.addSql("    asc");
                    sql.addSql("   ,");
                    sql.addSql("   ACCESS.USI_MEI_KN");
                    sql.addSql("    asc");
                    break;
                    //キャビネットアクセス
                case GSConstFile.USER_SORT_ACCESS:
                    sql.addSql(" order by");
                    sql.addSql("   ACCESS.ACKBN");
                    sql.addSql(orderStr);
                    sql.addSql("   ,");
                    sql.addSql("   ACCESS.USI_SEI_KN");
                    sql.addSql("    asc");
                    sql.addSql("   ,");
                    sql.addSql("   ACCESS.USI_MEI_KN");
                    sql.addSql("    asc");
                    break;
                default:
                    break;
            }
        }
        sql.addIntValue(GSConstUser.USER_JTKBN_DELETE);
        sql.addIntValue(GSConstUser.USER_RESERV_SID);
        return sql;
    }

    /**
     * <p>Create CMN_USRM_INF Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created CmnUsrmInfModel
     * @throws SQLException SQL実行例外
     */
    private FileAccessUserModel __getFileAccessUserModelFromRs(ResultSet rs) throws SQLException {
        FileAccessUserModel bean = new FileAccessUserModel();
        bean.setCabinetAdminKbn(rs.getInt("ADMKBN"));
        bean.setCabinetAccessKbn(rs.getInt("ACKBN"));

        bean.setUsrSid(rs.getInt("USR_SID"));
        bean.setUsiSei(rs.getString("USI_SEI"));
        bean.setUsiMei(rs.getString("USI_MEI"));
        bean.setUsiSeiKn(rs.getString("USI_SEI_KN"));
        bean.setUsiMeiKn(rs.getString("USI_MEI_KN"));
        bean.setUsiSini(rs.getString("USI_SINI"));
        bean.setUsiBdate(UDate.getInstanceTimestamp(rs.getTimestamp("USI_BDATE")));
        bean.setUsiZip1(rs.getString("USI_ZIP1"));
        bean.setUsiZip2(rs.getString("USI_ZIP2"));
        bean.setTdfSid(rs.getInt("TDF_SID"));
        bean.setUsiAddr1(rs.getString("USI_ADDR1"));
        bean.setUsiAddr2(rs.getString("USI_ADDR2"));
        bean.setUsiTel1(rs.getString("USI_TEL1"));
        bean.setUsiTelNai1(rs.getString("USI_TEL_NAI1"));
        bean.setUsiTelCmt1(rs.getString("USI_TEL_CMT1"));
        bean.setUsiTel2(rs.getString("USI_TEL2"));
        bean.setUsiTelNai2(rs.getString("USI_TEL_NAI2"));
        bean.setUsiTelCmt2(rs.getString("USI_TEL_CMT2"));
        bean.setUsiTel3(rs.getString("USI_TEL3"));
        bean.setUsiTelNai3(rs.getString("USI_TEL_NAI3"));
        bean.setUsiTelCmt3(rs.getString("USI_TEL_CMT3"));
        bean.setUsiFax1(rs.getString("USI_FAX1"));
        bean.setUsiFaxCmt1(rs.getString("USI_FAX_CMT1"));
        bean.setUsiFax2(rs.getString("USI_FAX2"));
        bean.setUsiFaxCmt2(rs.getString("USI_FAX_CMT2"));
        bean.setUsiFax3(rs.getString("USI_FAX3"));
        bean.setUsiFaxCmt3(rs.getString("USI_FAX_CMT3"));
        bean.setUsiMail1(rs.getString("USI_MAIL1"));
        bean.setUsiMailCmt1(rs.getString("USI_MAIL_CMT1"));
        bean.setUsiMail2(rs.getString("USI_MAIL2"));
        bean.setUsiMailCmt2(rs.getString("USI_MAIL_CMT2"));
        bean.setUsiMail3(rs.getString("USI_MAIL3"));
        bean.setUsiMailCmt3(rs.getString("USI_MAIL_CMT3"));
        bean.setUsiSyainNo(rs.getString("USI_SYAIN_NO"));
        bean.setUsiSyozoku(rs.getString("USI_SYOZOKU"));
        bean.setUsiYakusyoku(rs.getString("USI_YAKUSYOKU"));
        bean.setUsiBiko(rs.getString("USI_BIKO"));
        bean.setBinSid(rs.getLong("BIN_SID"));
        bean.setUsiPictKf(rs.getInt("USI_PICT_KF"));
        bean.setUsiBdateKf(rs.getInt("USI_BDATE_KF"));
        bean.setUsiMail1Kf(rs.getInt("USI_MAIL1_KF"));
        bean.setUsiMail2Kf(rs.getInt("USI_MAIL2_KF"));
        bean.setUsiMail3Kf(rs.getInt("USI_MAIL3_KF"));
        bean.setUsiZipKf(rs.getInt("USI_ZIP_KF"));
        bean.setUsiTdfKf(rs.getInt("USI_TDF_KF"));
        bean.setUsiAddr1Kf(rs.getInt("USI_ADDR1_KF"));
        bean.setUsiAddr2Kf(rs.getInt("USI_ADDR2_KF"));
        bean.setUsiTel1Kf(rs.getInt("USI_TEL1_KF"));
        bean.setUsiTel2Kf(rs.getInt("USI_TEL2_KF"));
        bean.setUsiTel3Kf(rs.getInt("USI_TEL3_KF"));
        bean.setUsiFax1Kf(rs.getInt("USI_FAX1_KF"));
        bean.setUsiFax2Kf(rs.getInt("USI_FAX2_KF"));
        bean.setUsiFax3Kf(rs.getInt("USI_FAX3_KF"));
        bean.setUsiLtlgin(UDate.getInstanceTimestamp(rs.getTimestamp("USI_LTLGIN")));
        bean.setUsiAuid(rs.getInt("USI_AUID"));
        bean.setUsiAdate(UDate.getInstanceTimestamp(rs.getTimestamp("USI_ADATE")));
        bean.setUsiEuid(rs.getInt("USI_EUID"));
        bean.setUsiEdate(UDate.getInstanceTimestamp(rs.getTimestamp("USI_EDATE")));

        //生年月日
        if (bean.getUsiBdateKf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiBdate(null);
        }
        //メールアドレス1
        if (bean.getUsiMail1Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiMail1(null);
            bean.setUsiMailCmt1(null);
        }
        //メールアドレス2
        if (bean.getUsiMail2Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiMail2(null);
            bean.setUsiMailCmt2(null);
        }
        //メールアドレス3
        if (bean.getUsiMail3Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiMail3(null);
            bean.setUsiMailCmt3(null);
        }
        //郵便番号
        if (bean.getUsiZipKf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiZip1(null);
            bean.setUsiZip2(null);
        }
        //都道府県
        if (bean.getUsiTdfKf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setTdfSid(-1);
        }
        //住所1
        if (bean.getUsiAddr1Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiAddr1(null);
        }
        //住所2
        if (bean.getUsiAddr2Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiAddr2(null);
        }
        //TEL1
        if (bean.getUsiTel1Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiTel1(null);
            bean.setUsiTelNai1(null);
            bean.setUsiTelCmt1(null);
        }
        //TEL2
        if (bean.getUsiTel2Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiTel2(null);
            bean.setUsiTelNai2(null);
            bean.setUsiTelCmt2(null);
        }
        //TEL3
        if (bean.getUsiTel3Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiTel3(null);
            bean.setUsiTelNai3(null);
            bean.setUsiTelCmt3(null);
        }
        //FAX1
        if (bean.getUsiFax1Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiFax1(null);
            bean.setUsiFaxCmt1(null);
        }
        //FAX2
        if (bean.getUsiFax2Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiFax2(null);
            bean.setUsiFaxCmt2(null);
        }
        //FAX3
        if (bean.getUsiFax3Kf() == GSConstUser.INDIVIDUAL_INFO_CLOSE) {
            bean.setUsiFax3(null);
            bean.setUsiFaxCmt3(null);
        }
        return bean;
    }
}
