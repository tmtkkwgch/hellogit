package jp.groupsession.v2.bbs.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.bbs.GSConstBulletin;
import jp.groupsession.v2.bbs.model.BbsForInfModel;
import jp.groupsession.v2.bbs.model.BulletinDspModel;
import jp.groupsession.v2.bbs.model.BulletinForumDiskModel;
import jp.groupsession.v2.bbs.model.BulletinMainModel;
import jp.groupsession.v2.bbs.model.BulletinSearchModel;
import jp.groupsession.v2.bbs.model.BulletinWachModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 掲示板プラグインで共通利用されるDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class BulletinDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(BulletinDao.class);

    /** 取得区分(カウント) */
    public static final int GET_COUNT = 0;
    /** 取得区分(一覧表示) */
    public static final int GET_LIST = 1;
    /** 取得区分(未確認・受信一覧) */
    public static final int GET_UNOPEN_LIST = 2;

    /**
     * <p>デフォルトコンストラクタ
     */
    public BulletinDao() {
    }

    /**
     * <p>デフォルトコンストラクタ
     * @param con DBコネクション
     */
    public BulletinDao(Connection con) {
        super(con);
    }

    /**
     * <br>[機  能] フォーラムの件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者 false:一般ユーザ
     * @return フォーラム件数
     * @throws SQLException SQL実行例外
     */
    public int getForumCount(int userSid, boolean admin) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    count(BBS_FOR_INF.BFI_SID) as forumCnt");
            sql.addSql("  from");
            sql.addSql("    BBS_FOR_INF");
            if (!admin) {
                sql.addSql("  where");
                sql.addSql("    BFI_SID in (");
                sql.addSql("      select BFI_SID from BBS_FOR_MEM");
                sql.addSql("      where");
                sql.addSql("        BBS_FOR_MEM.USR_SID = ?");
                sql.addSql("      or");
                sql.addSql("        BBS_FOR_MEM.GRP_SID in (");
                sql.addSql("          select GRP_SID from CMN_BELONGM");
                sql.addSql("          where CMN_BELONGM.USR_SID = ?");
                sql.addSql("        )");
                sql.addSql("    )");

                sql.addIntValue(userSid);
                sql.addIntValue(userSid);

            }
            log__.info(sql.toLogString());
            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("forumCnt");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] フォーラムの件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param searchMdl 検索条件
     * @return フォーラム件数
     * @throws SQLException SQL実行例外
     */
    public int getForumSearchCount(BulletinSearchModel searchMdl) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        List < String > keywordList = searchMdl.getKeywordList();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(BBS_FOR_INF.BFI_SID) as forumCnt");
            sql.addSql(" from");
            sql.addSql("   BBS_FOR_INF");
            sql.addSql(" where");
            sql.addSql("   BFI_SID in (");
            sql.addSql("     select");
            sql.addSql("       BBS_THRE_INF.BFI_SID");
            sql.addSql("     from");
            sql.addSql("       BBS_THRE_INF,");
            sql.addSql("       BBS_WRITE_INF");
            sql.addSql("     where");
            sql.addSql("       BBS_WRITE_INF.BTI_SID = BBS_THRE_INF.BTI_SID");
            sql.addSql("     and");
            sql.addSql("       (");
            sql.addSql("         (");
            sql.addSql("           BBS_WRITE_INF.BWI_VALUE like '%"
                    + JDBCUtil.encFullStringLike(keywordList.get(0))
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
            for (int i = 1; i < keywordList.size(); i++) {
                sql.addSql("         and");
                sql.addSql("           BBS_WRITE_INF.BWI_VALUE like '%"
                        + JDBCUtil.encFullStringLike(keywordList.get(i))
                        + "%' ESCAPE '"
                        + JDBCUtil.def_esc
                        + "'");
            }
            sql.addSql("         )");
            sql.addSql("         or");
            sql.addSql("         (");
            sql.addSql("           BBS_THRE_INF.BTI_TITLE like '%"
                    + JDBCUtil.encFullStringLike(keywordList.get(0))
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
            for (int i = 1; i < keywordList.size(); i++) {
                sql.addSql("   and");
                sql.addSql("           BBS_THRE_INF.BTI_TITLE like '%"
                        + JDBCUtil.encFullStringLike(keywordList.get(i))
                        + "%' ESCAPE '"
                        + JDBCUtil.def_esc
                        + "'");
            }
            sql.addSql("         )");
            sql.addSql("       )");
            sql.addSql("   )");
//            if (!searchMdl.isAdmin()) {
                sql.addSql("  and");
                sql.addSql("    BBS_FOR_INF.BFI_SID in (");
                sql.addSql("      select BFI_SID from BBS_FOR_MEM");
                sql.addSql("      where");
                sql.addSql("        BBS_FOR_MEM.USR_SID = ?");
                sql.addSql("      or");
                sql.addSql("        BBS_FOR_MEM.GRP_SID in (");
                sql.addSql("          select GRP_SID from CMN_BELONGM");
                sql.addSql("          where CMN_BELONGM.USR_SID = ?");
                sql.addSql("        )");
                sql.addSql("    )");

                sql.addIntValue(searchMdl.getUserSid());
                sql.addIntValue(searchMdl.getUserSid());
//            }
            log__.info(sql.toLogString());
            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("forumCnt");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] フォーラムコンボに表示する情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者 false:一般ユーザ
     * @return フォーラム情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<BbsForInfModel> getForumList(int userSid,
                                                boolean admin)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < BbsForInfModel > ret = new ArrayList < BbsForInfModel >();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    BBS_FOR_INF.BFI_CMT as BFI_CMT,");
            sql.addSql("    BBS_FOR_INF.BFI_SORT as BFI_SORT");
            sql.addSql("  from");
            sql.addSql("    BBS_FOR_INF");
            if (!admin) {
                sql.addSql("  where");
                sql.addSql("    BBS_FOR_INF.BFI_SID in (");
                sql.addSql("      select BFI_SID from BBS_FOR_MEM");
                sql.addSql("      where");
                sql.addSql("        BBS_FOR_MEM.USR_SID = ?");
                sql.addSql("      or");
                sql.addSql("        BBS_FOR_MEM.GRP_SID in (");
                sql.addSql("          select GRP_SID from CMN_BELONGM");
                sql.addSql("          where CMN_BELONGM.USR_SID = ?");
                sql.addSql("        )");
                sql.addSql("    )");

                sql.addIntValue(userSid);
                sql.addIntValue(userSid);
            }
            sql.addSql("  order by");
            sql.addSql("    BBS_FOR_INF.BFI_SORT, BBS_FOR_INF.BFI_NAME");

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BbsForInfModel forumMdl = new BbsForInfModel();
                forumMdl.setBfiSid(rs.getInt("BFI_SID"));
                forumMdl.setBfiName(rs.getString("BFI_NAME"));
                forumMdl.setBfiCmt(rs.getString("BFI_CMT"));
                ret.add(forumMdl);
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
     * <br>[機  能] フォーラム一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param adminDisp 管理者設定画面用の表示か否か true:管理者設定用画面 false:通常画面
     * @param now 現在日時
     * @param newCnt new表示日数
     * @param start 一覧開始位置
     * @param end   一覧終了位置
     * @param admUsr システム管理者フラグ
     * @return フォーラム情報
     * @throws SQLException SQL実行例外
     */
    public List<BulletinDspModel> getForumList(int userSid, boolean adminDisp, UDate now,
                                                int newCnt, int start, int end, boolean admUsr)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < BulletinDspModel > ret = new ArrayList < BulletinDspModel >();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    BBS_FOR_INF.BFI_CMT as BFI_CMT,");
            sql.addSql("    BBS_FOR_INF.BFI_SORT as BFI_SORT,");
            sql.addSql("    BBS_FOR_INF.BFI_REPLY as BFI_REPLY,");
            sql.addSql("    BBS_FOR_INF.BIN_SID as BIN_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_MREAD as BFI_MREAD,");
            sql.addSql("    BBS_FOR_SUM.BFS_THRE_CNT as THRE_CNT,");
            sql.addSql("    BBS_FOR_SUM.BFS_WRT_CNT as WRITE_CNT,");
            sql.addSql("    BBS_FOR_SUM.BFS_WRT_DATE as BFS_WRT_DATE,");
            sql.addSql("    BBS_FOR_SUM.BFS_SIZE as BFS_SIZE,");
            sql.addSql("    BBSVIEW.viewCnt as viewCnt,");
            sql.addSql("    RSVTHRE.rsvThreCnt as rsvThreCnt");
            sql.addSql("  from");
            sql.addSql("    BBS_FOR_SUM,");
            sql.addSql("    BBS_FOR_INF");
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("       select");
            sql.addSql("         BBS_THRE_VIEW.BFI_SID as BFI_SID,");
            sql.addSql("         count(BBS_THRE_VIEW.BTI_SID) as viewCnt");
            sql.addSql("       from");
            sql.addSql("         BBS_THRE_VIEW,");
            sql.addSql("         BBS_THRE_INF");
            sql.addSql("       where");
            sql.addSql("         BBS_THRE_VIEW.BIV_VIEW_KBN = ?");
            sql.addSql("       and");
            sql.addSql("         BBS_THRE_VIEW.USR_SID = ?");
            sql.addSql("       and");
            sql.addSql("         BBS_THRE_VIEW.BTI_SID = BBS_THRE_INF.BTI_SID");
            sql.addSql("       and");
            sql.addSql("         (");
            sql.addSql("            BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("          or");
            sql.addSql("            (");
            sql.addSql("               BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("             and");
            sql.addSql("               BBS_THRE_INF.BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("             and");
            sql.addSql("               BBS_THRE_INF.BTI_LIMIT_DATE >= ?");
            sql.addSql("            )");
            sql.addSql("         )");
            sql.addSql("       group by");
            sql.addSql("         BBS_THRE_VIEW.BFI_SID");
            sql.addSql("      ) BBSVIEW");
            sql.addSql("     on");
            sql.addSql("       BBS_FOR_INF.BFI_SID = BBSVIEW.BFI_SID");

            sql.addSql("     left join");
            sql.addSql("       (");
            sql.addSql("        select");
            sql.addSql("          BTI2.BFI_SID as BFI_SID2,");
            sql.addSql("          count(BTI2.BFI_SID) as rsvThreCnt");
            sql.addSql("        from");
            sql.addSql("          BBS_THRE_INF BTI2");
            sql.addSql("        where");
            sql.addSql("          BTI2.BTI_LIMIT = ?");
            sql.addSql("        and");
            sql.addSql("          BTI2.BTI_LIMIT_FR_DATE > ?");

            if (!admUsr) {
                sql.addSql("        and");
                sql.addSql("        (");
                sql.addSql("          (");
                sql.addSql("           BTI2.BTI_AUID = ? ");
                sql.addSql("          and");
                sql.addSql("           BTI2.BFI_SID ");
                sql.addSql("             in ( ");
                sql.addSql("                  select");
                sql.addSql("                    BBS_FOR_MEM.BFI_SID");
                sql.addSql("                  from");
                sql.addSql("                    BBS_FOR_MEM");
                sql.addSql("                    left join");
                sql.addSql("                      CMN_BELONGM");
                sql.addSql("                    on");
                sql.addSql("                      BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
                sql.addSql("                  where");
                sql.addSql("                    (");
                sql.addSql("                      BBS_FOR_MEM.USR_SID = ?");
                sql.addSql("                    or");
                sql.addSql("                      CMN_BELONGM.USR_SID = ?");
                sql.addSql("                    )");
                sql.addSql("                    and");
                sql.addSql("                    BBS_FOR_MEM.BFM_AUTH = ?");
                sql.addSql("             ) ");
                sql.addSql("          ) ");

                sql.addSql("         or ");
                sql.addSql("           BTI2.BFI_SID ");
                sql.addSql("             in ( ");
                sql.addSql("                 select ");
                sql.addSql("                   BBS_FOR_ADMIN.BFI_SID ");
                sql.addSql("                 from ");
                sql.addSql("                   BBS_FOR_ADMIN ");
                sql.addSql("                 where ");
                sql.addSql("                   BBS_FOR_ADMIN.USR_SID = ? ");
                sql.addSql("                 ) ");
                sql.addSql("        )");
            }

            sql.addSql("        group by");
            sql.addSql("          BTI2.BFI_SID");
            sql.addSql("        ) RSVTHRE");
            sql.addSql("     on");
            sql.addSql("       BBS_FOR_INF.BFI_SID = RSVTHRE.BFI_SID2");

            sql.addSql("  where");
            if (!adminDisp) {
                sql.addSql("    BBS_FOR_INF.BFI_SID in (");
                sql.addSql("      select");
                sql.addSql("        BBS_FOR_MEM.BFI_SID");
                sql.addSql("      from");
                sql.addSql("        BBS_FOR_MEM");
                sql.addSql("        left join");
                sql.addSql("          CMN_BELONGM");
                sql.addSql("        on");
                sql.addSql("          BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
                sql.addSql("      where");
                sql.addSql("        (");
                sql.addSql("          BBS_FOR_MEM.USR_SID = ?");
                sql.addSql("        or");
                sql.addSql("          CMN_BELONGM.USR_SID = ?");
                sql.addSql("        )");
                sql.addSql("    )");
                sql.addSql("  and");
            }
            sql.addSql("    BBS_FOR_SUM.BFI_SID = BBS_FOR_INF.BFI_SID");
            sql.addSql("  order by");
            sql.addSql("    BBS_FOR_INF.BFI_SORT, BBS_FOR_INF.BFI_NAME");
            if (end != 0) {
                sql.setPagingValue(start, (end - start + 1));
            }

            sql.addIntValue(GSConstBulletin.NEWUSER_THRE_VIEW_YES);
            sql.addIntValue(userSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            UDate limitDate = UDate.getInstanceStr(now.getDateString());
            limitDate.setZeroHhMmSs();
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);

            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            UDate limitFrDate = UDate.getInstanceStr(now.getDateString());
            limitFrDate.setMaxHhMmSs();
            sql.addDateValue(limitFrDate);
            if (!admUsr) {
                sql.addIntValue(userSid);
                sql.addIntValue(userSid);
                sql.addIntValue(userSid);
                sql.addIntValue(GSConstBulletin.ACCESS_KBN_WRITE);
                sql.addIntValue(userSid);
            }

            if (!adminDisp) {
                sql.addIntValue(userSid);
                sql.addIntValue(userSid);
            }
            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BulletinDspModel btMdl = new BulletinDspModel();
                btMdl.setBfiSid(rs.getInt("BFI_SID"));
                btMdl.setBfiName(rs.getString("BFI_NAME"));
                btMdl.setBfiSort(rs.getInt("BFI_SORT"));
                btMdl.setBfiCmt(rs.getString("BFI_CMT"));
                btMdl.setBfiCmtView(NullDefault.getString(
                        StringUtilHtml.transToHTmlPlusAmparsant(btMdl.getBfiCmt()), ""));
                btMdl.setBfiReply(rs.getInt("BFI_REPLY"));
                btMdl.setImgBinSid(rs.getLong("BIN_SID"));
                btMdl.setBfiMread(rs.getInt("BFI_MREAD"));
                btMdl.setBfsThreCnt(rs.getInt("THRE_CNT"));
                btMdl.setWriteCnt(rs.getInt("WRITE_CNT"));
                UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BFS_WRT_DATE"));
                btMdl.setWriteDate(bfsWrtDate);
                if (bfsWrtDate != null) {
                    btMdl.setStrWriteDate(__createDateStr(bfsWrtDate));

                    if (UDateUtil.diffDay(bfsWrtDate, new UDate()) < newCnt) {
                        btMdl.setNewFlg(BulletinDspModel.NEWFLG_VIEW);
                    }
                }
                btMdl.setReadedCnt(rs.getInt("viewCnt"));
                //未読/既読を設定
                if (btMdl.getBfsThreCnt() == btMdl.getReadedCnt()) {
                    btMdl.setReadFlg(BulletinDspModel.READFLG_READ);
                }
                btMdl.setBfsSize(rs.getLong("BFS_SIZE"));

                //掲示予定件数
                btMdl.setRsvThreCnt(rs.getInt("rsvThreCnt"));

                ret.add(btMdl);
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
     * <br>[機  能] フォーラム情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @return フォーラム情報
     * @throws SQLException SQL実行例外
     */
    public BulletinDspModel getForumData(int bfiSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BulletinDspModel ret = null;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    BBS_FOR_INF.BFI_CMT as BFI_CMT,");
            sql.addSql("    BBS_FOR_INF.BFI_REPLY as BFI_REPLY,");
            sql.addSql("    BBS_FOR_INF.BFI_READ as BFI_READ,");
            sql.addSql("    BBS_FOR_INF.BIN_SID as BIN_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_MREAD as BFI_MREAD,");
            sql.addSql("    BBS_FOR_INF.BFI_TEMPLATE_KBN as BFI_TEMPLATE_KBN,");
            sql.addSql("    BBS_FOR_INF.BFI_TEMPLATE as BFI_TEMPLATE,");
            sql.addSql("    BBS_FOR_INF.BFI_TEMPLATE_WRITE as BFI_TEMPLATE_WRITE,");
            sql.addSql("    BBS_FOR_INF.BFI_DISK as BFI_DISK,");
            sql.addSql("    BBS_FOR_INF.BFI_DISK_SIZE as BFI_DISK_SIZE,");
            sql.addSql("    BBS_FOR_INF.BFI_WARN_DISK as BFI_WARN_DISK,");
            sql.addSql("    BBS_FOR_INF.BFI_WARN_DISK_TH as BFI_WARN_DISK_TH,");

            sql.addSql("    BBS_FOR_INF.BFI_LIMIT as BFI_LIMIT,");
            sql.addSql("    BBS_FOR_INF.BFI_LIMIT_DATE as BFI_LIMIT_DATE,");
            sql.addSql("    BBS_FOR_INF.BFI_KEEP as BFI_KEEP,");
            sql.addSql("    BBS_FOR_INF.BFI_KEEP_DATE_Y as BFI_KEEP_DATE_Y,");
            sql.addSql("    BBS_FOR_INF.BFI_KEEP_DATE_M as BFI_KEEP_DATE_M,");

            sql.addSql("    BBS_FOR_INF.BFI_LIMIT_ON as BFI_LIMIT_ON,");

            sql.addSql("    BBS_FOR_SUM.BFS_WRT_CNT as BFS_WRT_CNT");
            sql.addSql("  from");
            sql.addSql("    BBS_FOR_INF");
            sql.addSql("    left join");
            sql.addSql("      BBS_FOR_SUM");
            sql.addSql("    on");
            sql.addSql("      BBS_FOR_INF.BFI_SID = BBS_FOR_SUM.BFI_SID");
            sql.addSql("  where");
            sql.addSql("    BBS_FOR_INF.BFI_SID = ?");
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = new BulletinDspModel();
                ret.setBfiSid(rs.getInt("BFI_SID"));
                ret.setBfiName(rs.getString("BFI_NAME"));
                ret.setBfiCmt(rs.getString("BFI_CMT"));
                ret.setBfiCmtView(
                        NullDefault.getString(StringUtilHtml.transToHTml(ret.getBfiCmt()), ""));
                ret.setBfiReply(rs.getInt("BFI_REPLY"));
                ret.setBfiRead(rs.getInt("BFI_READ"));
                ret.setBinSid(rs.getLong("BIN_SID"));
                ret.setBfiMread(rs.getInt("BFI_MREAD"));
                ret.setBfiTemplateKbn(rs.getInt("BFI_TEMPLATE_KBN"));
                ret.setBfiTemplate(rs.getString("BFI_TEMPLATE"));
                ret.setBfiTemplateWrite(rs.getInt("BFI_TEMPLATE_WRITE"));
                ret.setBfiDisk(rs.getInt("BFI_DISK"));
                ret.setBfiDiskSize(rs.getInt("BFI_DISK_SIZE"));
                ret.setBfiWarnDisk(rs.getInt("BFI_WARN_DISK"));
                ret.setBfiWarnDiskTh(rs.getInt("BFI_WARN_DISK_TH"));

                ret.setBfiLimitOn(rs.getInt("BFI_LIMIT_ON"));
                ret.setBfiLimit(rs.getInt("BFI_LIMIT"));
                ret.setBfiLimitDate(rs.getInt("BFI_LIMIT_DATE"));
                ret.setBfiKeep(rs.getInt("BFI_KEEP"));
                ret.setBfiKeepDateY(rs.getInt("BFI_KEEP_DATE_Y"));
                ret.setBfiKeepDateM(rs.getInt("BFI_KEEP_DATE_M"));

                ret.setWriteCnt(rs.getInt("BFS_WRT_CNT"));
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
     * <br>[機  能] 既読スレッド数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param btiSid スレッドSID
     * @return 既読スレッド数
     * @throws SQLException SQL実行例外
     */
    public int getForumNum(int bfiSid, int btiSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int ret = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    count(BBS_THRE_VIEW.USR_SID) as VIEW_CNT");
            sql.addSql("  from");
            sql.addSql("    CMN_USRM,");
            sql.addSql("         (");
            sql.addSql("          select");
            sql.addSql("            BFI_SID,");
            sql.addSql("            USR_SID,");
            sql.addSql("            count(*)");
            sql.addSql("          from");
            sql.addSql("            (");
            sql.addSql("              select");
            sql.addSql("                BFI_SID,");
            sql.addSql("                USR_SID");
            sql.addSql("              from");
            sql.addSql("                BBS_FOR_MEM");
            sql.addSql("              where USR_SID > 0");
            sql.addSql("            union all");
            sql.addSql("              select");
            sql.addSql("                BBS_FOR_MEM.BFI_SID as BFI_SID,");
            sql.addSql("                CMN_BELONGM.USR_SID as USR_SID");
            sql.addSql("              from");
            sql.addSql("                BBS_FOR_MEM,");
            sql.addSql("                CMN_BELONGM");
            sql.addSql("              where");
            sql.addSql("                BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("            ) BBS_MEM");
            sql.addSql("            group by BFI_SID, USR_SID");
            sql.addSql("         ) FORUM_MEM,");
            sql.addSql("    BBS_THRE_VIEW");
            sql.addSql("  where");
            sql.addSql("    CMN_USRM.USR_JKBN = ?");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_VIEW.BIV_VIEW_KBN = ?");
            sql.addSql("  and");
            sql.addSql("    CMN_USRM.USR_SID = BBS_THRE_VIEW.USR_SID");
            sql.addSql("  and");
            sql.addSql("    FORUM_MEM.USR_SID = BBS_THRE_VIEW.USR_SID");
            sql.addSql("  and");
            sql.addSql("    FORUM_MEM.BFI_SID=?");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_VIEW.BTI_SID=?");

            sql.addIntValue(GSConst.JTKBN_TOROKU);
            sql.addIntValue(GSConstBulletin.BBS_THRE_VIEW_YES);
            sql.addIntValue(bfiSid);
            sql.addIntValue(btiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = rs.getInt("VIEW_CNT");
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
     * <br>[機  能] スレッド情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param btiSid スレッドSID
     * @return スレッド情報
     * @throws SQLException SQL実行例外
     */
    public BulletinDspModel getThreadData(int btiSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BulletinDspModel ret = null;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    BBS_FOR_INF.BFI_CMT as BFI_CMT,");
            sql.addSql("    BBS_FOR_INF.BFI_REPLY as BFI_REPLY,");
            sql.addSql("    BBS_FOR_INF.BFI_MREAD as BFI_MREAD,");
            sql.addSql("    BBS_FOR_INF.BFI_LIMIT_ON as BFI_LIMIT_ON,");
            sql.addSql("    BBS_THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    BBS_THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    BBS_THRE_INF.BTI_AUID as BTI_AUID,");
            sql.addSql("    BBS_THRE_INF.BTI_LIMIT as BTI_LIMIT,");
            sql.addSql("    BBS_THRE_INF.BTI_LIMIT_FR_DATE as BTI_LIMIT_FR_DATE,");
            sql.addSql("    BBS_THRE_INF.BTI_LIMIT_DATE as BTI_LIMIT_DATE,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_CNT as BTS_WRT_CNT");
            sql.addSql("  from");
            sql.addSql("    BBS_FOR_INF,");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("    left join");
            sql.addSql("      BBS_THRE_SUM");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  where");
            sql.addSql("    BBS_FOR_INF.BFI_SID = BBS_THRE_INF.BFI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_SID = ?");

            sql.addIntValue(btiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = new BulletinDspModel();
                ret.setBfiSid(rs.getInt("BFI_SID"));
                ret.setBfiName(rs.getString("BFI_NAME"));
                ret.setBfiCmt(rs.getString("BFI_CMT"));
                ret.setBfiLimitOn(rs.getInt("BFI_LIMIT_ON"));
                ret.setBfiCmtView(
                        NullDefault.getString(StringUtilHtml.transToHTml(ret.getBfiCmt()), ""));
                ret.setBfiReply(rs.getInt("BFI_REPLY"));
                ret.setBfiMread(rs.getInt("BFI_MREAD"));
                ret.setBtiSid(rs.getInt("BTI_SID"));
                ret.setBtiTitle(rs.getString("BTI_TITLE"));
                ret.setBtiLimit(rs.getInt("BTI_LIMIT"));
                ret.setBtiLimitFrDate(
                        UDate.getInstanceTimestamp(rs.getTimestamp("BTI_LIMIT_FR_DATE")));
                ret.setBtiLimitDate(UDate.getInstanceTimestamp(rs.getTimestamp("BTI_LIMIT_DATE")));
                ret.setAddUserSid(rs.getInt("BTI_AUID"));
                ret.setWriteCnt(rs.getInt("BTS_WRT_CNT"));
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
     * <br>[機  能] フォーラム内の掲示予定スレッドの件数を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param usrSid ユーザSID
     * @param allFlg 全件フラグ true:全件 false:編集可能のみ
     * @param now 現在日時
     * @return 件数
     * @throws SQLException SQL実行時例外
     */
    public int countRsvThreData(int bfiSid, int usrSid, boolean allFlg, UDate now)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cnt = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    count(*) CNT");
            sql.addSql("  from");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("  where");
            sql.addSql("    BFI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    BTI_LIMIT = ?");
            sql.addSql("  and");
            sql.addSql("    BTI_LIMIT_FR_DATE > ?");

            if (!allFlg) {
                sql.addSql("  and");
                sql.addSql("  (");
                sql.addSql("     BTI_AUID = ?");
                sql.addSql("   or");
                sql.addSql("     BFI_SID ");
                sql.addSql("       in ( ");
                sql.addSql("            select ");
                sql.addSql("              BBS_FOR_ADMIN.BFI_SID ");
                sql.addSql("            from ");
                sql.addSql("              BBS_FOR_ADMIN ");
                sql.addSql("            where ");
                sql.addSql("              BBS_FOR_ADMIN.USR_SID = ? ");
                sql.addSql("           ) ");
                sql.addSql("    ) ");
            }

            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            UDate limitFrDate = UDate.getInstanceStr(now.getDateString());
            limitFrDate.setMaxHhMmSs();
            sql.addDateValue(limitFrDate);

            if (!allFlg) {
                sql.addIntValue(usrSid);
                sql.addIntValue(usrSid);
            }

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
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

        return cnt;
    }

    /**
     * <br>[機  能] フォーラム情報の削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @throws SQLException SQL実行例外
     */
    public void deleteForumData(int bfiSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = getCon();

        try {
            SqlBuffer sql = null;

            //投稿情報の添付情報を削除する
            sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_BINF");
            sql.addSql(" set");
            sql.addSql("   BIN_JKBN = ?");
            sql.addSql(" where");
            sql.addSql("   CMN_BINF.BIN_SID in (");
            sql.addSql("     select");
            sql.addSql("       BBS_BIN.BIN_SID");
            sql.addSql("     from");
            sql.addSql("       BBS_BIN");
            sql.addSql("     where");
            sql.addSql("       BBS_BIN.BWI_SID in (");
            sql.addSql("         select");
            sql.addSql("           BBS_WRITE_INF.BWI_SID");
            sql.addSql("         from");
            sql.addSql("           BBS_WRITE_INF");
            sql.addSql("         where");
            sql.addSql("           BBS_WRITE_INF.BFI_SID = ?");
            sql.addSql("       )");
            sql.addSql("   )");

            sql.addIntValue(GSConst.JTKBN_DELETE);
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

            //投稿情報を削除する
            sql = new SqlBuffer();
            sql.addSql(" delete from BBS_BIN");
            sql.addSql(" where");
            sql.addSql("   BWI_SID in (");
            sql.addSql("     select");
            sql.addSql("       BWI_SID");
            sql.addSql("     from");
            sql.addSql("       BBS_WRITE_INF");
            sql.addSql("     where");
            sql.addSql("       BFI_SID = ?");
            sql.addSql("   )");
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

            //スレッド集計情報を削除する
            sql = new SqlBuffer();
            sql.addSql(" delete from BBS_THRE_SUM");
            sql.addSql(" where");
            sql.addSql("   BTI_SID in (");
            sql.addSql("     select");
            sql.addSql("       BTI_SID");
            sql.addSql("     from");
            sql.addSql("       BBS_THRE_INF");
            sql.addSql("     where");
            sql.addSql("       BFI_SID = ?");
            sql.addSql("   )");
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

            //アイコン情報を削除する
            sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_BINF");
            sql.addSql(" set");
            sql.addSql("   BIN_JKBN = ?");
            sql.addSql(" where");
            sql.addSql("   CMN_BINF.BIN_SID in (");
            sql.addSql("     select");
            sql.addSql("       BBS_FOR_INF.BIN_SID");
            sql.addSql("     from");
            sql.addSql("       BBS_FOR_INF");
            sql.addSql("     where");
            sql.addSql("       BBS_FOR_INF.BFI_SID = ?");
            sql.addSql("   )");

            sql.addIntValue(GSConst.JTKBN_DELETE);
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

            //フォーラム情報を削除する
            String[] forum_table = new String[] {
                                        "BBS_FOR_INF",   //フォーラム情報
                                        "BBS_FOR_SUM",   //フォーラム集計情報
                                        "BBS_FOR_MEM",   //フォーラムメンバー
                                        "BBS_THRE_INF",  //スレッド情報
                                        "BBS_THRE_VIEW", //スレッド閲覧情報
                                        "BBS_WRITE_INF"  //投稿情報
                                            };
            for (String tblName : forum_table) {
                sql = new SqlBuffer();
                sql.addSql(" delete from");
                sql.addSql("   " + tblName);
                sql.addSql(" where");
                sql.addSql("   BFI_SID = ?");
                sql.addIntValue(bfiSid);

                log__.info(sql.toLogString());

                pstmt = con.prepareStatement(sql.toSqlString());
                sql.setParameter(pstmt);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }

    }

    /**
     * <br>[機  能] 書き込み日時の最小値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者 false:一般ユーザ
     * @return 書き込み日時の最小値
     * @throws SQLException SQL実行例外
     */
    public UDate getOldestWriteDate(int userSid, boolean admin) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        UDate ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    min(BBS_WRITE_INF.BWI_ADATE) as oldDate");
            sql.addSql("  from");
            sql.addSql("    BBS_WRITE_INF");
            if (!admin) {
                sql.addSql("    ,BBS_FOR_INF,");
                sql.addSql("    BBS_FOR_MEM");
                sql.addSql("    left join");
                sql.addSql("      CMN_BELONGM");
                sql.addSql("    on");
                sql.addSql("      BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
                sql.addSql("  where");
                sql.addSql("    BBS_FOR_INF.BFI_SID = BBS_WRITE_INF.BFI_SID");
                sql.addSql("  and");
                sql.addSql("    BBS_FOR_INF.BFI_SID = BBS_FOR_MEM.BFI_SID");
                sql.addSql("  and");
                sql.addSql("    (");
                sql.addSql("      BBS_FOR_MEM.USR_SID = ?");
                sql.addSql("    or");
                sql.addSql("      CMN_BELONGM.USR_SID = ?");
                sql.addSql("    )");
                sql.addIntValue(userSid);
                sql.addIntValue(userSid);
            }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = UDate.getInstanceTimestamp(rs.getTimestamp("oldDate"));
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
     * <br>[機  能] 指定されたフォーラムのスレッド件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param now 現在日時
     * @return スレッド件数
     * @throws SQLException SQL実行例外
     */
    public int getThreadCount(int bfiSid, UDate now) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    count(BTI_SID) as threCount");
            sql.addSql("  from");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("  where");
            sql.addSql("    BFI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    (");
            sql.addSql("       BTI_LIMIT = ?");
            sql.addSql("     or");
            sql.addSql("       (");
            sql.addSql("          BTI_LIMIT = ?");
            sql.addSql("        and");
            sql.addSql("          BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("        and");
            sql.addSql("          BTI_LIMIT_DATE >= ?");
            sql.addSql("       )");
            sql.addSql("    )");
            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            UDate limitDate = UDate.getInstanceStr(now.getDateString());
            limitDate.setZeroHhMmSs();
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("threCount");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] スレッド一覧を取得する
     * <br>[解  説] モバイル用
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @param now 現在日時
     * @param newCnt new表示日数
     * @param start 一覧開始位置
     * @param end   一覧終了位置
     * @return スレッド情報
     * @throws SQLException SQL実行例外
     */
    public List<BulletinDspModel> getThreadList(int bfiSid, int userSid, UDate now,
                                                    int newCnt, int start, int end)
    throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < BulletinDspModel > ret = new ArrayList < BulletinDspModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("    CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("    CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("    BBS_THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    BBS_THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    BBS_THRE_INF.BTI_EGID as BTI_EGID,");
            sql.addSql("    CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("    CMN_GROUPM.GRP_JKBN as GRP_JKBN,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_CNT as BTS_WRT_CNT,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE,");
            sql.addSql("    THRE_VIEW.BIV_VIEW_KBN as BIV_VIEW_KBN,");
            sql.addSql("    THRE_VIEW_CNT.VIEW_CNT as VIEW_CNT");
            sql.addSql("  from");
            sql.addSql("    CMN_USRM,");
            sql.addSql("    CMN_USRM_INF,");
            sql.addSql("    BBS_THRE_SUM,");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("    left join");
            sql.addSql("      CMN_GROUPM");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_EGID = CMN_GROUPM.GRP_SID");
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("       select");
            sql.addSql("         BTI_SID,");
            sql.addSql("         BIV_VIEW_KBN");
            sql.addSql("       from");
            sql.addSql("         BBS_THRE_VIEW");
            sql.addSql("       where");
            sql.addSql("         USR_SID = ?");
            sql.addSql("      ) THRE_VIEW");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_SID = THRE_VIEW.BTI_SID");
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("       select");
            sql.addSql("         FORUM_MEM.BFI_SID as BFI_SID,");
            sql.addSql("         BBS_THRE_VIEW.BTI_SID as BTI_SID,");
            sql.addSql("         count(BBS_THRE_VIEW.USR_SID) as VIEW_CNT");
            sql.addSql("       from");
            sql.addSql("         CMN_USRM,");
            sql.addSql("         (");
            sql.addSql("           select");
            sql.addSql("             BBS_FOR_MEM.BFI_SID as BFI_SID,");
            sql.addSql("             BBS_FOR_MEM.USR_SID as USR_SID");
            sql.addSql("           from");
            sql.addSql("             BBS_FOR_MEM");
            sql.addSql("           where");
            sql.addSql("             coalesce(BBS_FOR_MEM.USR_SID, 0) > 0");
            sql.addSql("         union");
            sql.addSql("           select");
            sql.addSql("             BBS_FOR_MEM.BFI_SID as BFI_SID,");
            sql.addSql("             CMN_BELONGM.USR_SID as USR_SID");
            sql.addSql("           from");
            sql.addSql("             BBS_FOR_MEM,");
            sql.addSql("             CMN_BELONGM");
            sql.addSql("           where");
            sql.addSql("             BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("         ) FORUM_MEM,");
            sql.addSql("         BBS_THRE_VIEW");

            sql.addSql("       where");
            sql.addSql("         CMN_USRM.USR_JKBN = ?");
            sql.addSql("       and");
            sql.addSql("         BBS_THRE_VIEW.BIV_VIEW_KBN = ?");
            sql.addSql("       and");
            sql.addSql("         CMN_USRM.USR_SID = BBS_THRE_VIEW.USR_SID");
            sql.addSql("       and");
            sql.addSql("         FORUM_MEM.USR_SID = BBS_THRE_VIEW.USR_SID");
            sql.addSql("       group by");
            sql.addSql("         FORUM_MEM.BFI_SID,");
            sql.addSql("         BBS_THRE_VIEW.BTI_SID");
            sql.addSql("      ) THRE_VIEW_CNT");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BFI_SID = THRE_VIEW_CNT.BFI_SID");
            sql.addSql("    and");
            sql.addSql("      BBS_THRE_INF.BTI_SID = THRE_VIEW_CNT.BTI_SID");
            sql.addSql("  where");
            sql.addSql("    BBS_THRE_INF.BFI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    (");
            sql.addSql("       BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("     or");
            sql.addSql("       (");
            sql.addSql("          BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("        and");
            sql.addSql("          BBS_THRE_INF.BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("        and");
            sql.addSql("          BBS_THRE_INF.BTI_LIMIT_DATE >= ?");
            sql.addSql("       )");
            sql.addSql("    )");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_AUID = CMN_USRM.USR_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_AUID = CMN_USRM_INF.USR_SID");
            sql.addSql("  order by");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE desc");

            sql.addIntValue(userSid);
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);
            sql.addIntValue(GSConstBulletin.BBS_THRE_VIEW_YES);
            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            UDate limitDate = new UDate();
            limitDate.setZeroHhMmSs();
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            for (int index = 1; rs.next(); index++) {
                if  (index > end) {
                    break;
                }

                if (index >= start) {
                    BulletinDspModel btMdl = new BulletinDspModel();
                    btMdl.setBtiSid(rs.getInt("BTI_SID"));
                    btMdl.setBtiTitle(rs.getString("BTI_TITLE"));
                    btMdl.setWriteCnt(rs.getInt("BTS_WRT_CNT"));
                    UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTS_WRT_DATE"));
                    btMdl.setWriteDate(bfsWrtDate);
                    if (bfsWrtDate != null) {
                        btMdl.setStrWriteDate(__createDateStr(bfsWrtDate));

                        if (UDateUtil.diffDay(bfsWrtDate, new UDate()) < newCnt) {
                            btMdl.setNewFlg(BulletinDspModel.NEWFLG_VIEW);
                        }
                    }

                    btMdl.setGrpSid(rs.getInt("BTI_EGID"));
                    btMdl.setGrpName(rs.getString("GRP_NAME"));
                    btMdl.setGrpJkbn(rs.getInt("GRP_JKBN"));
                    if (btMdl.getGrpSid() > 0) {
                        btMdl.setUserName(btMdl.getGrpName());
                        btMdl.setUserJkbn(btMdl.getGrpJkbn());
                    } else {
                        btMdl.setUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                        btMdl.setUserJkbn(rs.getInt("USR_JKBN"));
                    }

                    btMdl.setReadFlg(rs.getInt("BIV_VIEW_KBN"));
                    btMdl.setReadedCnt(rs.getInt("VIEW_CNT"));

                    ret.add(btMdl);
                }
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
     * <br>[機  能] ポータル用のスレッド一覧を取得する
     * <br>[解  説] PC用
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @param now 現在日時
     * @param newCnt new表示日数
     * @param start 一覧開始位置
     * @param end   一覧終了位置
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return スレッド情報
     * @throws SQLException SQL実行例外
     */
    public List<BulletinDspModel> getPtlThreadList(int bfiSid, int userSid,
                                                    UDate now,
                                                    int newCnt, int start, int end,
                                                    int sortKey, int orderKey)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < BulletinDspModel > ret = new ArrayList < BulletinDspModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("    CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("    CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("    CMN_USRM_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("    CMN_USRM_INF.USI_MEI_KN as USI_MEI_KN,");
            sql.addSql("    BBS_THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    BBS_THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_CNT as BTS_WRT_CNT,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE,");
            sql.addSql("    THRE_VIEW.BIV_VIEW_KBN as BIV_VIEW_KBN");
            sql.addSql("  from");
            sql.addSql("    CMN_USRM,");
            sql.addSql("    CMN_USRM_INF,");
            sql.addSql("    BBS_THRE_SUM,");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("       select");
            sql.addSql("         BTI_SID,");
            sql.addSql("         BIV_VIEW_KBN");
            sql.addSql("       from");
            sql.addSql("         BBS_THRE_VIEW");
            sql.addSql("       where");
            sql.addSql("         USR_SID = ?");
            sql.addSql("      ) THRE_VIEW");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_SID = THRE_VIEW.BTI_SID");
            sql.addSql("  where");
            sql.addSql("    BBS_THRE_INF.BFI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    (");
            sql.addSql("       BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("     or");
            sql.addSql("       (");
            sql.addSql("          BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("        and");
            sql.addSql("          BBS_THRE_INF.BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("        and");
            sql.addSql("          BBS_THRE_INF.BTI_LIMIT_DATE >= ?");
            sql.addSql("       )");
            sql.addSql("    )");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_AUID = CMN_USRM.USR_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_AUID = CMN_USRM_INF.USR_SID");
            sql.addSql("  order by");

            /** 2011/06/20 並び替え機能追加 start ***********************************************/
            //昇順,降順指定
            String strOrder = "DESC";
            String timeOrder = "ASC";
            if (orderKey != 1) {
                strOrder = "ASC";
                timeOrder = "DESC";
            }

            switch (sortKey) {
            case GSConstBulletin.SORT_KEY_THRED:
                //タイトル
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder + ",");
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder);
                break;

            case GSConstBulletin.SORT_KEY_TOUKOU:
                //投稿数
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_CNT " + strOrder + ",");
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder);
                break;

            case GSConstBulletin.SORT_KEY_ETSURAN:
                //閲覧数
                sql.addSql("     THRE_VIEW_CNT.VIEW_CNT " + strOrder + ",");
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder);
                break;

            case GSConstBulletin.SORT_KEY_USER:
                //投稿者
                sql.addSql("     CMN_USRM_INF.USI_SEI_KN " + strOrder + ",");
                sql.addSql("     CMN_USRM_INF.USI_MEI_KN " + strOrder + ",");
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder);
                break;

            case GSConstBulletin.SORT_KEY_SAISHIN:
                //最新書き込み
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder);
                break;

            default:
                sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE desc");
                break;
            }
            /** 2011/06/20 並び替え機能追加 end *************************************************/

            sql.addIntValue(userSid);
            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            UDate limitDate = new UDate();
            limitDate.setZeroHhMmSs();
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);

            sql.setPagingValue(start - 1, end - start + 1);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString(),
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                BulletinDspModel btMdl = new BulletinDspModel();
                btMdl.setBtiSid(rs.getInt("BTI_SID"));
                btMdl.setBtiTitle(rs.getString("BTI_TITLE"));
                btMdl.setWriteCnt(rs.getInt("BTS_WRT_CNT"));
                UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTS_WRT_DATE"));
                btMdl.setWriteDate(bfsWrtDate);
                if (bfsWrtDate != null) {
                    btMdl.setStrWriteDate(__createDateStr(bfsWrtDate));

                    if (UDateUtil.diffDay(bfsWrtDate, new UDate()) < newCnt) {
                        btMdl.setNewFlg(BulletinDspModel.NEWFLG_VIEW);
                    }
                }
                btMdl.setUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                btMdl.setUserJkbn(rs.getInt("USR_JKBN"));
                btMdl.setReadFlg(rs.getInt("BIV_VIEW_KBN"));

                ret.add(btMdl);
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
     * <br>[機  能] スレッド一覧を取得する
     * <br>[解  説] PC用
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @param now 現在日時
     * @param newCnt new表示日数
     * @param start 一覧開始位置
     * @param end   一覧終了位置
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return スレッド情報
     * @throws SQLException SQL実行例外
     */
    public List<BulletinDspModel> getThreadList(int bfiSid, int userSid, UDate now,
                                                    int newCnt, int start, int end,
                                                    int sortKey, int orderKey)
    throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < BulletinDspModel > ret = new ArrayList < BulletinDspModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("    CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("    CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("    CMN_USRM_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("    CMN_USRM_INF.USI_MEI_KN as USI_MEI_KN,");
            sql.addSql("    BBS_THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    BBS_THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    BBS_THRE_INF.BTI_EGID as BTI_EGID,");
            sql.addSql("    CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("    CMN_GROUPM.GRP_JKBN as GRP_JKBN,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_CNT as BTS_WRT_CNT,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE,");
            sql.addSql("    BBS_THRE_SUM.BTS_SIZE as BTS_SIZE,");
            sql.addSql("    THRE_VIEW.BIV_VIEW_KBN as BIV_VIEW_KBN,");
            sql.addSql("    THRE_VIEW_CNT.VIEW_CNT as VIEW_CNT");
            sql.addSql("  from");
            sql.addSql("    CMN_USRM,");
            sql.addSql("    CMN_USRM_INF,");
            sql.addSql("    BBS_THRE_SUM,");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("    left join");
            sql.addSql("      CMN_GROUPM");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_EGID = CMN_GROUPM.GRP_SID");
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("       select");
            sql.addSql("         BTI_SID,");
            sql.addSql("         BIV_VIEW_KBN");
            sql.addSql("       from");
            sql.addSql("         BBS_THRE_VIEW");
            sql.addSql("       where");
            sql.addSql("         USR_SID = ?");
            sql.addSql("      ) THRE_VIEW");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_SID = THRE_VIEW.BTI_SID");
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("       select");
            sql.addSql("         BBS_THRE_VIEW.BTI_SID as BTI_SID,");
            sql.addSql("         count(BBS_THRE_VIEW.USR_SID) as VIEW_CNT");
            sql.addSql("       from");
            sql.addSql("         CMN_USRM,");
            sql.addSql("         (");
            sql.addSql("           select");
            sql.addSql("             BBS_FOR_MEM.BFI_SID as BFI_SID,");
            sql.addSql("             BBS_FOR_MEM.USR_SID as USR_SID");
            sql.addSql("           from");
            sql.addSql("             BBS_FOR_MEM");
            sql.addSql("           where");
            sql.addSql("             BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("           and");
            sql.addSql("             coalesce(BBS_FOR_MEM.USR_SID, 0) > 0");
            sql.addSql("         union");
            sql.addSql("           select");
            sql.addSql("             BBS_FOR_MEM.BFI_SID as BFI_SID,");
            sql.addSql("             CMN_BELONGM.USR_SID as USR_SID");
            sql.addSql("           from");
            sql.addSql("             BBS_FOR_MEM,");
            sql.addSql("             CMN_BELONGM");
            sql.addSql("           where");
            sql.addSql("             BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("           and");
            sql.addSql("             BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("         ) FORUM_MEM,");
            sql.addSql("         BBS_THRE_VIEW");

            sql.addSql("       where");
            sql.addSql("         CMN_USRM.USR_JKBN = ?");
            sql.addSql("       and");
            sql.addSql("         BBS_THRE_VIEW.BFI_SID = ?");
            sql.addSql("       and");
            sql.addSql("         BBS_THRE_VIEW.BIV_VIEW_KBN = ?");
            sql.addSql("       and");
            sql.addSql("         CMN_USRM.USR_SID = BBS_THRE_VIEW.USR_SID");
            sql.addSql("       and");
            sql.addSql("         FORUM_MEM.USR_SID = BBS_THRE_VIEW.USR_SID");
            sql.addSql("       group by");
            sql.addSql("         BBS_THRE_VIEW.BTI_SID");
            sql.addSql("      ) THRE_VIEW_CNT");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_SID = THRE_VIEW_CNT.BTI_SID");
            sql.addSql("  where");
            sql.addSql("    BBS_THRE_INF.BFI_SID = ?");
            sql.addSql("  and");

            sql.addSql("    (");
            sql.addSql("       BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("     or");
            sql.addSql("       (");
            sql.addSql("          BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("        and");
            sql.addSql("          BBS_THRE_INF.BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("        and");
            sql.addSql("          BBS_THRE_INF.BTI_LIMIT_DATE >= ?");
            sql.addSql("       )");
            sql.addSql("    )");

            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_AUID = CMN_USRM.USR_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_AUID = CMN_USRM_INF.USR_SID");

            sql.addSql("  order by");

            /** 2011/06/20 並び替え機能追加 start ***********************************************/
            //昇順,降順指定
            String strOrder = "DESC";
            String timeOrder = "ASC";
            if (orderKey != 1) {
                strOrder = "ASC";
                timeOrder = "DESC";
            }

            switch (sortKey) {
            case GSConstBulletin.SORT_KEY_THRED:
                //タイトル
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder + ",");
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder);
                break;

            case GSConstBulletin.SORT_KEY_TOUKOU:
                //投稿数
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_CNT " + strOrder + ",");
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder);

                break;

            case GSConstBulletin.SORT_KEY_ETSURAN:
                //閲覧数
                sql.addSql("     THRE_VIEW_CNT.VIEW_CNT " + strOrder + ",");
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder);
                break;

            case GSConstBulletin.SORT_KEY_USER:
                //投稿者
                sql.addSql("     CMN_USRM_INF.USI_SEI_KN " + strOrder + ",");
                sql.addSql("     CMN_USRM_INF.USI_MEI_KN " + strOrder + ",");
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder);
                break;

            case GSConstBulletin.SORT_KEY_SIZE:
                //サイズ
                sql.addSql("     BBS_THRE_SUM.BTS_SIZE " + strOrder + ",");
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder);
                break;

            case GSConstBulletin.SORT_KEY_SAISHIN:
                //最新書き込み
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder);
                break;

            default:
                sql.addSql("     BBS_THRE_SUM.BTS_WRT_DATE desc ,");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE asc");
                break;
            }
            /** 2011/06/20 並び替え機能追加 end *************************************************/

            sql.addIntValue(userSid);
            sql.addIntValue(bfiSid);
            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);
            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConstBulletin.BBS_THRE_VIEW_YES);
            sql.addIntValue(bfiSid);

            UDate limitDate = new UDate();
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            limitDate.setZeroHhMmSs();
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);

            sql.setPagingValue(start - 1, end - start + 1);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString(),
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                BulletinDspModel btMdl = new BulletinDspModel();
                btMdl.setBtiSid(rs.getInt("BTI_SID"));
                btMdl.setBtiTitle(rs.getString("BTI_TITLE"));
                btMdl.setGrpSid(rs.getInt("BTI_EGID"));
                btMdl.setGrpName(rs.getString("GRP_NAME"));
                btMdl.setGrpJkbn(rs.getInt("GRP_JKBN"));
                btMdl.setWriteCnt(rs.getInt("BTS_WRT_CNT"));
                UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTS_WRT_DATE"));
                btMdl.setWriteDate(bfsWrtDate);
                if (bfsWrtDate != null) {
                    btMdl.setStrWriteDate(__createDateStr(bfsWrtDate));

                    if (UDateUtil.diffDay(bfsWrtDate, new UDate()) < newCnt) {
                        btMdl.setNewFlg(BulletinDspModel.NEWFLG_VIEW);
                    }
                }
                btMdl.setBtsSize(rs.getLong("BTS_SIZE"));
                btMdl.setUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                btMdl.setUserJkbn(rs.getInt("USR_JKBN"));
                btMdl.setReadFlg(rs.getInt("BIV_VIEW_KBN"));
                btMdl.setReadedCnt(rs.getInt("VIEW_CNT"));

                ret.add(btMdl);
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
     * <br>[機  能] スレッド一覧を取得する
     * <br>[解  説] PC用
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @param now 現在日時
     * @param newCnt new表示日数
     * @param start 一覧開始位置
     * @param end   一覧終了位置
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @param admin 管理者フラグ
     * @return スレッド情報
     * @throws SQLException SQL実行例外
     */
    public List<BulletinDspModel> getRsvThreadList(int bfiSid, int userSid, UDate now,
                                                    int newCnt, int start, int end,
                                                    int sortKey, int orderKey,
                                                    boolean admin)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < BulletinDspModel > ret = new ArrayList < BulletinDspModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("    CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("    CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("    CMN_USRM_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("    CMN_USRM_INF.USI_MEI_KN as USI_MEI_KN,");
            sql.addSql("    BBS_THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    BBS_THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    BBS_THRE_INF.BTI_EGID as BTI_EGID,");

            sql.addSql("    BBS_THRE_INF.BTI_LIMIT_FR_DATE as BTI_LIMIT_FR_DATE,");
            sql.addSql("    BBS_THRE_INF.BTI_LIMIT_DATE as BTI_LIMIT_DATE,");

            sql.addSql("    CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("    CMN_GROUPM.GRP_JKBN as GRP_JKBN,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_CNT as BTS_WRT_CNT,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE,");

            sql.addSql("    BBS_THRE_SUM.BTS_SIZE as BTS_SIZE,");

            sql.addSql("    THRE_VIEW.BIV_VIEW_KBN as BIV_VIEW_KBN,");
            sql.addSql("    THRE_VIEW_CNT.VIEW_CNT as VIEW_CNT");
            sql.addSql("  from");
            sql.addSql("    CMN_USRM,");
            sql.addSql("    CMN_USRM_INF,");
            sql.addSql("    BBS_THRE_SUM,");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("    left join");
            sql.addSql("      CMN_GROUPM");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_EGID = CMN_GROUPM.GRP_SID");
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("       select");
            sql.addSql("         BTI_SID,");
            sql.addSql("         BIV_VIEW_KBN");
            sql.addSql("       from");
            sql.addSql("         BBS_THRE_VIEW");
            sql.addSql("       where");
            sql.addSql("         USR_SID = ?");
            sql.addSql("      ) THRE_VIEW");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_SID = THRE_VIEW.BTI_SID");
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("       select");
            sql.addSql("         FORUM_MEM.BFI_SID as BFI_SID,");
            sql.addSql("         BBS_THRE_VIEW.BTI_SID as BTI_SID,");
            sql.addSql("         count(BBS_THRE_VIEW.USR_SID) as VIEW_CNT");
            sql.addSql("       from");
            sql.addSql("         CMN_USRM,");
            sql.addSql("         (");
            sql.addSql("           select");
            sql.addSql("             BBS_FOR_MEM.BFI_SID as BFI_SID,");
            sql.addSql("             BBS_FOR_MEM.USR_SID as USR_SID");
            sql.addSql("           from");
            sql.addSql("             BBS_FOR_MEM");
            sql.addSql("           where");
            sql.addSql("             BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("           and");
            sql.addSql("             coalesce(BBS_FOR_MEM.USR_SID, 0) > 0");
            sql.addSql("         union");
            sql.addSql("           select");
            sql.addSql("             BBS_FOR_MEM.BFI_SID as BFI_SID,");
            sql.addSql("             CMN_BELONGM.USR_SID as USR_SID");
            sql.addSql("           from");
            sql.addSql("             BBS_FOR_MEM,");
            sql.addSql("             CMN_BELONGM");
            sql.addSql("           where");
            sql.addSql("             BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("           and");
            sql.addSql("             BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("         ) FORUM_MEM,");
            sql.addSql("         BBS_THRE_VIEW");

            sql.addSql("       where");
            sql.addSql("         CMN_USRM.USR_JKBN = ?");
            sql.addSql("       and");
            sql.addSql("         BBS_THRE_VIEW.BTI_SID in (");
            sql.addSql("           select BTI_SID from BBS_THRE_INF");
            sql.addSql("           where BFI_SID = ?");
            sql.addSql("         )");
            sql.addSql("       and");
            sql.addSql("         BBS_THRE_VIEW.BIV_VIEW_KBN = ?");
            sql.addSql("       and");
            sql.addSql("         CMN_USRM.USR_SID = BBS_THRE_VIEW.USR_SID");
            sql.addSql("       and");
            sql.addSql("         FORUM_MEM.USR_SID = BBS_THRE_VIEW.USR_SID");
            sql.addSql("       group by");
            sql.addSql("         FORUM_MEM.BFI_SID,");
            sql.addSql("         BBS_THRE_VIEW.BTI_SID");
            sql.addSql("      ) THRE_VIEW_CNT");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BFI_SID = THRE_VIEW_CNT.BFI_SID");
            sql.addSql("    and");
            sql.addSql("      BBS_THRE_INF.BTI_SID = THRE_VIEW_CNT.BTI_SID");
            sql.addSql("  where");
            sql.addSql("    BBS_THRE_INF.BFI_SID = ?");
            sql.addSql("  and");

            sql.addSql("    (");
            sql.addSql("       BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("     and");
            sql.addSql("       BBS_THRE_INF.BTI_LIMIT_FR_DATE > ?");
            sql.addSql("    )");


            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_AUID = CMN_USRM.USR_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_AUID = CMN_USRM_INF.USR_SID");

            if (!admin) {
                sql.addSql("  and");
                sql.addSql("  (");
                sql.addSql("     BBS_THRE_INF.BTI_AUID = ?");
                sql.addSql("   or");
                sql.addSql("     BBS_THRE_INF.BFI_SID ");
                sql.addSql("       in ( ");
                sql.addSql("            select ");
                sql.addSql("              BBS_FOR_ADMIN.BFI_SID ");
                sql.addSql("            from ");
                sql.addSql("              BBS_FOR_ADMIN ");
                sql.addSql("            where ");
                sql.addSql("              BBS_FOR_ADMIN.USR_SID = ? ");
                sql.addSql("          ) ");
                sql.addSql("  )");
            }
            sql.addSql("  order by");

            /** 2011/06/20 並び替え機能追加 start ***********************************************/
            //昇順,降順指定
            String strOrder = "DESC";
            String timeOrder = "ASC";
            if (orderKey != 1) {
                strOrder = "ASC";
                timeOrder = "DESC";
            }

            switch (sortKey) {
            case GSConstBulletin.SORT_KEY_THRED:
                //タイトル
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_FR_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_DATE " + timeOrder);
                break;

            case GSConstBulletin.SORT_KEY_USER:
                //投稿者
                sql.addSql("     CMN_USRM_INF.USI_SEI_KN " + strOrder + ",");
                sql.addSql("     CMN_USRM_INF.USI_MEI_KN " + strOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_FR_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder);

                break;

            case GSConstBulletin.SORT_KEY_SIZE:
                //サイズ
                sql.addSql("     BBS_THRE_SUM.BTS_SIZE " + strOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_FR_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder);
                break;

            case GSConstBulletin.SORT_KEY_LIMIT_FR:
                //掲示開始日
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_FR_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder);

                break;

            case GSConstBulletin.SORT_KEY_LIMIT_TO:
                //掲示終了日
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_DATE " + timeOrder + ",");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE " + strOrder);
                break;

            default:
                sql.addSql("    BBS_THRE_INF.BTI_LIMIT_FR_DATE asc ,");
                sql.addSql("     BBS_THRE_INF.BTI_LIMIT_DATE asc ,");
                sql.addSql("     BBS_THRE_INF.BTI_TITLE desc");
                break;
            }
            /** 2011/06/20 並び替え機能追加 end *************************************************/

            sql.addIntValue(userSid);
            sql.addIntValue(bfiSid);
            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);
            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConstBulletin.BBS_THRE_VIEW_YES);
            sql.addIntValue(bfiSid);

            UDate limitDate = new UDate();
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            limitDate.setMaxHhMmSs();
            sql.addDateValue(limitDate);

            if (!admin) {
                sql.addIntValue(userSid);
                sql.addIntValue(userSid);
            }

            sql.setPagingValue(start - 1, end - start + 1);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString(),
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                BulletinDspModel btMdl = new BulletinDspModel();
                btMdl.setBtiSid(rs.getInt("BTI_SID"));
                btMdl.setBtiTitle(rs.getString("BTI_TITLE"));
                btMdl.setGrpSid(rs.getInt("BTI_EGID"));
                btMdl.setGrpName(rs.getString("GRP_NAME"));
                btMdl.setGrpJkbn(rs.getInt("GRP_JKBN"));
                btMdl.setWriteCnt(rs.getInt("BTS_WRT_CNT"));
                UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTS_WRT_DATE"));
                btMdl.setWriteDate(bfsWrtDate);
                if (bfsWrtDate != null) {
                    btMdl.setStrWriteDate(__createDateStr(bfsWrtDate));

                    if (UDateUtil.diffDay(bfsWrtDate, new UDate()) < newCnt) {
                        btMdl.setNewFlg(BulletinDspModel.NEWFLG_VIEW);
                    }
                }
                btMdl.setBtsSize(rs.getLong("BTS_SIZE"));
                btMdl.setUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                btMdl.setUserJkbn(rs.getInt("USR_JKBN"));
                btMdl.setReadFlg(rs.getInt("BIV_VIEW_KBN"));
                btMdl.setReadedCnt(rs.getInt("VIEW_CNT"));

                UDate btiLimitFrDate =
                        UDate.getInstanceTimestamp(rs.getTimestamp("BTI_LIMIT_FR_DATE"));
                btMdl.setBtiLimitFrDate(btiLimitFrDate);
                if (btiLimitFrDate != null) {
                    btMdl.setStrBtiLimitFrDate(__createDateNoTimeStr(btiLimitFrDate));
                }

                UDate btiLimitDate =
                        UDate.getInstanceTimestamp(rs.getTimestamp("BTI_LIMIT_DATE"));
                btMdl.setBtiLimitDate(btiLimitDate);
                if (btiLimitDate != null) {
                    btMdl.setStrBtiLimitDate(__createDateNoTimeStr(btiLimitDate));
                }

                ret.add(btMdl);
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
     * <br>[機  能] 未読スレッド一覧件数(インフォメーションメッセージ用)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @return スレッド情報一覧
     * @throws SQLException SQL実行例外
     */
    public int getThreadListCnt(int userSid) throws SQLException {
        int ret = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql("  select");
            sql.addSql("    count(BBS_THRE_INF.BTI_SID) as CNT");
            sql.addSql("  from");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("  where");
            sql.addSql("    BBS_THRE_INF.BFI_SID in (");
            sql.addSql("      select BBS_FOR_MEM.BFI_SID from BBS_FOR_MEM");
            sql.addSql("      where");
            sql.addSql("        BBS_FOR_MEM.USR_SID = ?");
            sql.addSql("      or");
            sql.addSql("        BBS_FOR_MEM.GRP_SID in (");
            sql.addSql("        select CMN_BELONGM.GRP_SID from CMN_BELONGM");
            sql.addSql("        where");
            sql.addSql("          CMN_BELONGM.USR_SID = ?");
            sql.addSql("        )");
            sql.addSql("    )");
            sql.addSql("  and");
            sql.addSql("    not exists");
            sql.addSql("     (");
            sql.addSql("       select 1 from BBS_THRE_VIEW");
            sql.addSql("       where");
            sql.addSql("         BIV_VIEW_KBN = 1");
            sql.addSql("       and");
            sql.addSql("         USR_SID = ?");
            sql.addSql("       and");
            sql.addSql("         BBS_THRE_INF.BTI_SID = BBS_THRE_VIEW.BTI_SID");
            sql.addSql("     )");
            sql.addSql("  and");
            sql.addSql("    (");
            sql.addSql("       BTI_LIMIT = ?");
            sql.addSql("     or");
            sql.addSql("       (");
            sql.addSql("          BTI_LIMIT = ?");
            UDate limitDate = new UDate();
            limitDate.setZeroHhMmSs();
            sql.addSql("        and");
            sql.addSql("          BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("        and");
            sql.addSql("          BTI_LIMIT_DATE >= ?");
            sql.addSql("       )");
            sql.addSql("    )");
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);
                       log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
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
     * <br>[機  能] スレッド一覧(未読&既読)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param adminFlg 管理者フラグ true:管理者 false:一般ユーザ
     * @param viewCnt 表示件数
     * @param newCnt new表示日数
     * @param chkedDsp 既読表示 1=非表示 0=表示
     * @return スレッド情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<BulletinDspModel> getThreadList(int userSid, boolean adminFlg,
                                                        int viewCnt, int newCnt, int chkedDsp)
    throws SQLException {

        return getThreadList(userSid, adminFlg, viewCnt, newCnt, null, chkedDsp);

    }

    /**
     * <br>[機  能] スレッド一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param adminFlg 管理者フラグ true:管理者 false:一般ユーザ
     * @param viewCnt 表示件数
     * @param newCnt new表示日数
     * @param bfiSid フォーラムSID nullの場合は全てのフォーラム
     * @param chkedDsp 確認済み投稿の表示有無 1=非表示 0=表示
     * @return スレッド情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<BulletinDspModel> getThreadList(int userSid, boolean adminFlg,
                                 int viewCnt, int newCnt, Integer bfiSid, int chkedDsp)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < BulletinDspModel > ret = new ArrayList < BulletinDspModel >();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("    CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("    CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    THRE_INF.BTI_EGID as BTI_EGID,");
            sql.addSql("    CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("    CMN_GROUPM.GRP_JKBN as GRP_JKBN,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_CNT as BTS_WRT_CNT,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE,");
            sql.addSql("    THRE_VIEW.BIV_VIEW_KBN as BIV_VIEW_KBN");
            sql.addSql("  from");
            sql.addSql("    CMN_USRM,");
            sql.addSql("    CMN_USRM_INF,");
            sql.addSql("    BBS_FOR_INF,");
            sql.addSql("    BBS_THRE_SUM,");
            sql.addSql("    (");
            sql.addSql("     select BFI_SID, BTI_SID, BTI_TITLE, BTI_EGID from BBS_THRE_INF");
            sql.addSql("     where");
            if (chkedDsp == GSConstBulletin.BBS_CHECKED_NOT_DSP) {
                sql.addSql("       not exists (");
                sql.addSql("         select 1 from BBS_THRE_VIEW");
                sql.addSql("         where");
                sql.addSql("           BBS_THRE_VIEW.BIV_VIEW_KBN = 1");
                sql.addSql("         and");
                sql.addSql("           BBS_THRE_VIEW.USR_SID = ?");
                sql.addSql("         and");
                sql.addSql("           BBS_THRE_INF.BTI_SID = BBS_THRE_VIEW.BTI_SID");
                sql.addSql("       )");
                sql.addSql("     and");
                sql.addIntValue(userSid);
            }

            sql.addSql("       (");
            sql.addSql("          BTI_LIMIT = ?");
            sql.addSql("        or");
            sql.addSql("          (");
            sql.addSql("             BTI_LIMIT = ?");
            UDate limitDate = new UDate();
            limitDate.setZeroHhMmSs();
            sql.addSql("           and");
            sql.addSql("             BTI_LIMIT_FR_DATE <= ? ");
            sql.addSql("           and");
            sql.addSql("             BTI_LIMIT_DATE >= ? ");
            sql.addSql("          )");
            sql.addSql("       )");
            sql.addSql("    ) THRE_INF");
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("        select");
            sql.addSql("          BTI_SID,");
            sql.addSql("          BIV_VIEW_KBN");
            sql.addSql("        from");
            sql.addSql("          BBS_THRE_VIEW");
            sql.addSql("        where");
            sql.addSql("          USR_SID=?");
            sql.addSql("      ) THRE_VIEW");
            sql.addSql("    on THRE_INF.BTI_SID = THRE_VIEW.BTI_SID");
            sql.addIntValue(userSid);

            sql.addSql("    left join");
            sql.addSql("      CMN_GROUPM");
            sql.addSql("    on");
            sql.addSql("      THRE_INF.BTI_EGID = CMN_GROUPM.GRP_SID");

            sql.addSql("  where");
            sql.addSql("    BBS_FOR_INF.BFI_SID = THRE_INF.BFI_SID");

            if (bfiSid != null) {
                sql.addSql(" and");
                sql.addSql("   BBS_FOR_INF.BFI_SID = ?");
                sql.addIntValue(bfiSid.intValue());
            }

            if (!adminFlg) {
                sql.addSql("  and");
                sql.addSql("    exists (");
                sql.addSql("      select 1 from BBS_FOR_MEM");
                sql.addSql("      where");
                sql.addSql("        (");
                sql.addSql("          BBS_FOR_MEM.USR_SID = ?");
                sql.addSql("        or");
                sql.addSql("          BBS_FOR_MEM.GRP_SID in (");
                sql.addSql("            select CMN_BELONGM.GRP_SID from CMN_BELONGM");
                sql.addSql("            where");
                sql.addSql("              CMN_BELONGM.USR_SID = ?");
                sql.addSql("          )");
                sql.addSql("        )");
                sql.addSql("      and");
                sql.addSql("        BBS_FOR_INF.BFI_SID = BBS_FOR_MEM.BFI_SID");
                sql.addSql("    )");
                sql.addIntValue(userSid);
                sql.addIntValue(userSid);
            }

            sql.addSql("  and");
            sql.addSql("    THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_SUM.BTS_EUID = CMN_USRM.USR_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_SUM.BTS_EUID = CMN_USRM_INF.USR_SID");

            sql.addSql("  order by");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE desc");

            sql.setPagingValue(0, viewCnt);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            for (int index = 1; index <= viewCnt && rs.next(); index++) {

                BulletinDspModel btMdl = new BulletinDspModel();
                btMdl.setBfiSid(rs.getInt("BFI_SID"));
                btMdl.setBtiSid(rs.getInt("BTI_SID"));
                btMdl.setBfiName(rs.getString("BFI_NAME"));
                btMdl.setBtiTitle(rs.getString("BTI_TITLE"));
                UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTS_WRT_DATE"));
                if (bfsWrtDate != null) {
                    btMdl.setStrWriteDate(__createDateStr(bfsWrtDate));
                    if (UDateUtil.diffDay(bfsWrtDate, new UDate()) < newCnt) {
                        btMdl.setNewFlg(BulletinMainModel.NEWFLG_VIEW);
                    }
                }

                btMdl.setUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                btMdl.setUserJkbn(rs.getInt("USR_JKBN"));
                btMdl.setReadFlg(rs.getInt("BIV_VIEW_KBN"));

                btMdl.setGrpSid(rs.getInt("BTI_EGID"));
                btMdl.setGrpName(rs.getString("GRP_NAME"));
                btMdl.setGrpJkbn(rs.getInt("GRP_JKBN"));

                ret.add(btMdl);
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
     * <br>[機  能] 未読スレッド一覧を取得する(サブコンテンツ)
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param viewCnt 表示件数
     * @return スレッド情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<BulletinDspModel> getThreadList2(int userSid, int viewCnt)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < BulletinDspModel > ret = new ArrayList < BulletinDspModel >();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("    CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("    CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    BBS_FOR_INF.BIN_SID as BIN_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_SORT as BFI_SORT,");
            sql.addSql("    THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    THRE_INF.BTI_EGID as BTI_EGID,");
            sql.addSql("    CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("    CMN_GROUPM.GRP_JKBN as GRP_JKBN,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_CNT as BTS_WRT_CNT,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE,");
            sql.addSql("    THRE_VIEW.BIV_VIEW_KBN as BIV_VIEW_KBN");
            sql.addSql("  from");
            sql.addSql("    CMN_USRM,");
            sql.addSql("    CMN_USRM_INF,");
            sql.addSql("    BBS_FOR_INF,");
            sql.addSql("    BBS_THRE_SUM,");
            sql.addSql("    (");
            sql.addSql("     select BFI_SID, BTI_SID, BTI_TITLE, BTI_EGID from BBS_THRE_INF");
            sql.addSql("     where");
            sql.addSql("       not exists (");
            sql.addSql("         select 1 from BBS_THRE_VIEW");
            sql.addSql("         where");
            sql.addSql("           BBS_THRE_VIEW.BIV_VIEW_KBN = 1");
            sql.addSql("         and");
            sql.addSql("           BBS_THRE_VIEW.USR_SID = ?");
            sql.addSql("         and");
            sql.addSql("           BBS_THRE_INF.BTI_SID = BBS_THRE_VIEW.BTI_SID");
            sql.addSql("       )");
            sql.addSql("     and");
            sql.addIntValue(userSid);

            sql.addSql("       (");
            sql.addSql("          BTI_LIMIT = ?");
            sql.addSql("        or");
            sql.addSql("          (");
            sql.addSql("             BTI_LIMIT = ?");
            UDate limitDate = new UDate();
            limitDate.setZeroHhMmSs();
            sql.addSql("           and");
            sql.addSql("             BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("           and");
            sql.addSql("             BTI_LIMIT_DATE >= ?");
            sql.addSql("          )");
            sql.addSql("       )");

            sql.addSql("    ) THRE_INF");
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);
            sql.addSql("    left join");
            sql.addSql("      CMN_GROUPM");
            sql.addSql("    on");
            sql.addSql("      THRE_INF.BTI_EGID = CMN_GROUPM.GRP_SID");

            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("        select");
            sql.addSql("          BTI_SID,");
            sql.addSql("          BIV_VIEW_KBN");
            sql.addSql("        from");
            sql.addSql("          BBS_THRE_VIEW");
            sql.addSql("        where");
            sql.addSql("          USR_SID=?");
            sql.addSql("      ) THRE_VIEW");
            sql.addSql("    on THRE_INF.BTI_SID = THRE_VIEW.BTI_SID");
            sql.addIntValue(userSid);

            sql.addSql("  where");
            sql.addSql("    BBS_FOR_INF.BFI_SID = THRE_INF.BFI_SID");

            sql.addSql("  and");
            sql.addSql("    exists (");
            sql.addSql("      select 1 from BBS_FOR_MEM");
            sql.addSql("      where");
            sql.addSql("        (");
            sql.addSql("          BBS_FOR_MEM.USR_SID = ?");
            sql.addSql("        or");
            sql.addSql("          BBS_FOR_MEM.GRP_SID in (");
            sql.addSql("            select CMN_BELONGM.GRP_SID from CMN_BELONGM");
            sql.addSql("            where");
            sql.addSql("              CMN_BELONGM.USR_SID = ?");
            sql.addSql("          )");
            sql.addSql("        )");
            sql.addSql("      and");
            sql.addSql("        BBS_FOR_INF.BFI_SID = BBS_FOR_MEM.BFI_SID");

            sql.addSql("    )");
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);

            sql.addSql("  and");
            sql.addSql("    THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_SUM.BTS_EUID = CMN_USRM.USR_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_SUM.BTS_EUID = CMN_USRM_INF.USR_SID");

            sql.addSql("  order by");
            sql.addSql("    BBS_FOR_INF.BFI_SORT asc, BBS_THRE_SUM.BTS_WRT_DATE desc");

            sql.setPagingValue(0, viewCnt);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            for (int index = 1; index <= viewCnt && rs.next(); index++) {

                BulletinDspModel btMdl = new BulletinDspModel();
                btMdl.setBfiSid(rs.getInt("BFI_SID"));
                btMdl.setBtiSid(rs.getInt("BTI_SID"));
                btMdl.setBfiName(rs.getString("BFI_NAME"));
                btMdl.setBinSid(rs.getLong("BIN_SID"));
                String name = rs.getString("BFI_Name");
                name = StringUtilHtml.transToHTmlWithWbr(
                        StringUtilHtml.deleteHtmlTag(StringUtilHtml.transToText(name)), 15);
                btMdl.setBfiName(name);

                String title = rs.getString("BTI_TITLE");
                title = StringUtilHtml.transToHTmlWithWbr(
                        StringUtilHtml.deleteHtmlTag(StringUtilHtml.transToText(title)), 25);
                btMdl.setBtiTitle(title);

                UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTS_WRT_DATE"));
                if (bfsWrtDate != null) {
                    btMdl.setStrWriteDate(__createDateStr(bfsWrtDate));
                }

                if (rs.getInt("BTI_EGID") > 0) {
                    btMdl.setUserJkbn(rs.getInt("GRP_JKBN"));
                } else {
                    btMdl.setUserJkbn(rs.getInt("USR_JKBN"));
                }
                btMdl.setReadFlg(rs.getInt("BIV_VIEW_KBN"));

                ret.add(btMdl);
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
     * <br>[機  能] スレッド一覧(未読のみ)のMapを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param viewCnt 表示件数
     * @param newCnt new表示日数
     * @return スレッド情報一覧
     * @throws SQLException SQL実行例外
     */
    public Map<Integer, List<BulletinDspModel>> getNotReadThreadMap(int userSid,
                                                                    int viewCnt, int newCnt)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map<Integer, List<BulletinDspModel>> ret = new HashMap<Integer, List<BulletinDspModel>>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_CNT as BTS_WRT_CNT,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE,");
            sql.addSql("    0 as BIV_VIEW_KBN");
            sql.addSql("  from");
            sql.addSql("    BBS_FOR_INF,");
            sql.addSql("    BBS_THRE_SUM,");
            sql.addSql("    (");
            sql.addSql("     select");
            sql.addSql("       BBS_THRE_INF.BTI_SID,");
            sql.addSql("       BBS_THRE_INF.BTI_TITLE,");
            sql.addSql("       BBS_THRE_INF.BFI_SID");
            sql.addSql("     from BBS_THRE_INF");
            sql.addSql("     where");
            sql.addSql("       BTI_SID not in");
            sql.addSql("         (");
            sql.addSql("          select BTI_SID from BBS_THRE_VIEW");
            sql.addSql("          where");
            sql.addSql("            BIV_VIEW_KBN = 1");
            sql.addSql("          and");
            sql.addSql("            USR_SID = ?");
            sql.addSql("         )");
            sql.addSql("     and");
            sql.addSql("       (");
            sql.addSql("          BTI_LIMIT = ?");
            sql.addSql("        or");
            sql.addSql("          (");
            sql.addSql("             BTI_LIMIT = ?");
            UDate limitDate = new UDate();
            limitDate.setZeroHhMmSs();
            sql.addSql("           and");
            sql.addSql("             BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("           and");
            sql.addSql("             BTI_LIMIT_DATE >= ?");
            sql.addSql("          )");
            sql.addSql("       )");
            sql.addSql("    ) THRE_INF");
            sql.addSql("  where");
            sql.addSql("    BBS_FOR_INF.BFI_SID = THRE_INF.BFI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_FOR_INF.BFI_SID in (");
            sql.addSql("      select BFI_SID from BBS_FOR_MEM");
            sql.addSql("      where");
            sql.addSql("        BBS_FOR_MEM.USR_SID = ?");
            sql.addSql("      or");
            sql.addSql("        BBS_FOR_MEM.GRP_SID in (");
            sql.addSql("          select GRP_SID from CMN_BELONGM");
            sql.addSql("          where CMN_BELONGM.USR_SID = ?");
            sql.addSql("        )");
            sql.addSql("    )");
            sql.addSql("  and");
            sql.addSql("    THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  order by");
            sql.addSql("    BBS_FOR_INF.BFI_SID asc, BBS_THRE_SUM.BTS_WRT_DATE desc");

            sql.addIntValue(userSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);


            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            Integer bfiSid = new Integer(0);
            while (rs.next()) {

                BulletinDspModel btMdl = new BulletinDspModel();
                btMdl.setBfiSid(rs.getInt("BFI_SID"));
                btMdl.setBtiSid(rs.getInt("BTI_SID"));
                btMdl.setBfiName(rs.getString("BFI_Name"));
                btMdl.setBtiTitle(rs.getString("BTI_TITLE"));
                UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTS_WRT_DATE"));
                if (bfsWrtDate != null) {
                    btMdl.setStrWriteDate(__createDateStr(bfsWrtDate));
                    if (UDateUtil.diffDay(bfsWrtDate, new UDate()) < newCnt) {
                        btMdl.setNewFlg(BulletinMainModel.NEWFLG_VIEW);
                    }
                }
                btMdl.setReadFlg(rs.getInt("BIV_VIEW_KBN"));

                if (bfiSid.intValue() != btMdl.getBfiSid()) {
                    bfiSid = new Integer(btMdl.getBfiSid());
                    ret.put(bfiSid, new ArrayList<BulletinDspModel>());
                }

                ret.get(bfiSid).add(btMdl);
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
     * <br>[機  能] スレッド一覧(未読のみ)のMapを取得する(html用)
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param viewCnt 表示件数
     * @param newCnt new表示日数
     * @return スレッド情報一覧
     * @throws SQLException SQL実行例外
     */
    public Map<Integer, List<BulletinDspModel>> getNotReadThreadMapHtml(int userSid,
                                                                    int viewCnt, int newCnt)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map<Integer, List<BulletinDspModel>> ret = new HashMap<Integer, List<BulletinDspModel>>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_CNT as BTS_WRT_CNT,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE,");
            sql.addSql("    0 as BIV_VIEW_KBN");
            sql.addSql("  from");
            sql.addSql("    BBS_FOR_INF,");
            sql.addSql("    BBS_THRE_SUM,");
            sql.addSql("    (");
            sql.addSql("     select BFI_SID, BTI_SID, BTI_TITLE from BBS_THRE_INF");
            sql.addSql("     where");
            sql.addSql("       not exists (");
            sql.addSql("          select 1 from BBS_THRE_VIEW");
            sql.addSql("          where");
            sql.addSql("            BIV_VIEW_KBN = 1");
            sql.addSql("          and");
            sql.addSql("            USR_SID = ?");
            sql.addSql("          and");
            sql.addSql("            BBS_THRE_INF. BTI_SID = BBS_THRE_VIEW.BTI_SID");
            sql.addSql("         )");
            sql.addSql("     and");
            sql.addSql("       (");
            sql.addSql("          BTI_LIMIT = ?");
            sql.addSql("        or");
            sql.addSql("          (");
            sql.addSql("             BTI_LIMIT = ?");
            UDate limitDate = new UDate();
            limitDate.setZeroHhMmSs();
            sql.addSql("           and");
            sql.addSql("             BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("           and");
            sql.addSql("             BTI_LIMIT_DATE >= ?");
            sql.addSql("          )");
            sql.addSql("       )");
            sql.addSql("    ) THRE_INF");
            sql.addSql("  where");
            sql.addSql("    BBS_FOR_INF.BFI_SID = THRE_INF.BFI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_FOR_INF.BFI_SID in (");
            sql.addSql("      select BFI_SID from BBS_FOR_MEM");
            sql.addSql("      where");
            sql.addSql("        BBS_FOR_MEM.USR_SID = ?");
            sql.addSql("      or");
            sql.addSql("        BBS_FOR_MEM.GRP_SID in (");
            sql.addSql("          select GRP_SID from CMN_BELONGM");
            sql.addSql("          where CMN_BELONGM.USR_SID = ?");
            sql.addSql("        )");
            sql.addSql("    )");
            sql.addSql("  and");
            sql.addSql("    THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  order by");
            sql.addSql("    BBS_FOR_INF.BFI_SID asc, BBS_THRE_SUM.BTS_WRT_DATE desc");

            sql.addIntValue(userSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);
            sql.setPagingValue(0, viewCnt);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            Integer bfiSid = new Integer(0);

            //2011-06-21 未読スレッドの表示件数を制限
            for (int index = 1; index <= viewCnt && rs.next(); index++) {

                BulletinDspModel btMdl = new BulletinDspModel();
                btMdl.setBfiSid(rs.getInt("BFI_SID"));
                btMdl.setBtiSid(rs.getInt("BTI_SID"));
                String name = rs.getString("BFI_Name");
                name = StringUtilHtml.transToHTmlWithWbr(
                        StringUtilHtml.deleteHtmlTag(StringUtilHtml.transToText(name)), 15);
                btMdl.setBfiName(name);

                String title = rs.getString("BTI_TITLE");
                title = StringUtilHtml.transToHTmlWithWbr(
                        StringUtilHtml.deleteHtmlTag(StringUtilHtml.transToText(title)), 25);
                btMdl.setBtiTitle(title);

                UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTS_WRT_DATE"));
                if (bfsWrtDate != null) {
                    btMdl.setStrWriteDate(__createDateStr(bfsWrtDate));
                    if (UDateUtil.diffDay(bfsWrtDate, new UDate()) < newCnt) {
                        btMdl.setNewFlg(BulletinMainModel.NEWFLG_VIEW);
                    }
                }
                btMdl.setReadFlg(rs.getInt("BIV_VIEW_KBN"));

                if (bfiSid.intValue() != btMdl.getBfiSid()) {
                    bfiSid = new Integer(btMdl.getBfiSid());
                    ret.put(bfiSid, new ArrayList<BulletinDspModel>());
                }

                ret.get(bfiSid).add(btMdl);
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
     * <br>[機  能] 指定されたスレッドの投稿件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param btiSid スレッドSID
     * @return 投稿件数
     * @throws SQLException SQL実行例外
     */
    public int getWriteCount(int btiSid) throws SQLException {
        Connection con = getCon();
        BbsWriteInfDao bwiDao = new BbsWriteInfDao(con);
        return bwiDao.getWriteCountInThread(btiSid);
    }

    /**
     * <br>[機  能] 投稿情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param bwiSid 投稿SID
     * @return 投稿情報
     * @throws SQLException SQL実行例外
     */
    public BulletinDspModel getWriteData(int bwiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BulletinDspModel ret = null;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_WRITE_INF.BWI_SID as BWI_SID,");
            sql.addSql("    BBS_WRITE_INF.BWI_VALUE as BWI_VALUE,");
            sql.addSql("    BBS_WRITE_INF.BWI_AUID as BWI_AUID,");
            sql.addSql("    BBS_WRITE_INF.BWI_EGID as BWI_EGID,");
            sql.addSql("    CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("    CMN_GROUPM.GRP_JKBN as GRP_JKBN");
            sql.addSql("  from");
            sql.addSql("    BBS_WRITE_INF");
            sql.addSql("    left join");
            sql.addSql("      CMN_GROUPM");
            sql.addSql("    on");
            sql.addSql("      BBS_WRITE_INF.BWI_EGID = CMN_GROUPM.GRP_SID");
            sql.addSql("  where");
            sql.addSql("    BWI_SID = ?");

            sql.addIntValue(bwiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = new BulletinDspModel();
                ret.setBwiSid(bwiSid);
                ret.setBwiValue(rs.getString("BWI_VALUE"));
                ret.setBwiValueView(
                        NullDefault.getString(StringUtilHtml.transToHTml(
                                ret.getBwiValue()), ""));
                ret.setAddUserSid(rs.getInt("BWI_AUID"));
                ret.setGrpSid(rs.getInt("BWI_EGID"));
                ret.setGrpName(rs.getString("GRP_NAME"));
                ret.setGrpJkbn(rs.getInt("GRP_JKBN"));

                //添付ファイル情報を取得
                ret.setTmpFileList(getWriteTmpFileList(ret.getBwiSid()));
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
     * <br>[機  能] 指定されたスレッドの投稿一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param searchMdl 検索条件
     * @return 投稿一覧
     * @throws IOException ユーザ画像ファイルの取得に失敗
     * @throws IOToolsException ユーザ画像ファイルの取得に失敗
     * @throws SQLException SQL実行例外
     */
    public List<BulletinDspModel> getWriteList(BulletinSearchModel searchMdl)
    throws IOException, IOToolsException, SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < BulletinDspModel > ret = new ArrayList < BulletinDspModel >();
        boolean isOrderAsc = true;
        if (searchMdl.getOrderWriteDate() == GSConstBulletin.BBS_WRTLIST_ORDER_DESC) {
            isOrderAsc = false;
        }

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    CMN_USRM.USR_SID as USR_SID,");
            sql.addSql("    CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("    CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("    CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("    CMN_USRM_INF.USI_PICT_KF as USI_PICT_KF,");
            sql.addSql("    BINF.BIN_SID as BIN_SID,");
            sql.addSql("    BINF.BIN_FILE_NAME as BIN_FILE_NAME,");
            sql.addSql("    BINF.BIN_FILE_PATH as BIN_FILE_PATH,");
            sql.addSql("    BBS_WRITE_INF.BWI_SID as BWI_SID,");
            sql.addSql("    BBS_WRITE_INF.BWI_VALUE as BWI_VALUE,");
            sql.addSql("    BBS_WRITE_INF.BWI_AUID as BWI_AUID,");
            sql.addSql("    BBS_WRITE_INF.BWI_ADATE as BWI_ADATE,");
            sql.addSql("    BBS_WRITE_INF.BWI_EDATE as BWI_EDATE,");
            sql.addSql("    BBS_WRITE_INF.BWI_EGID as BWI_EGID,");
            sql.addSql("    CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("    CMN_GROUPM.GRP_JKBN as GRP_JKBN");
            sql.addSql("  from");
            sql.addSql("    BBS_WRITE_INF");
            sql.addSql("    left join");
            sql.addSql("      CMN_GROUPM");
            sql.addSql("    on");
            sql.addSql("      BBS_WRITE_INF.BWI_EGID = CMN_GROUPM.GRP_SID");
            sql.addSql("    ,");
            sql.addSql("    CMN_USRM,");
            sql.addSql("    CMN_USRM_INF");
            sql.addSql("    left join");
            sql.addSql("      (");
            sql.addSql("       select");
            sql.addSql("         BIN_SID,");
            sql.addSql("         BIN_FILE_NAME,");
            sql.addSql("         BIN_FILE_PATH");
            sql.addSql("       from");
            sql.addSql("         CMN_BINF");
            sql.addSql("       where");
            sql.addSql("         BIN_JKBN = 0");
            sql.addSql("      ) BINF");
            sql.addSql("    on");
            sql.addSql("      CMN_USRM_INF.BIN_SID = BINF.BIN_SID");
            sql.addSql("  where");
            sql.addSql("    BBS_WRITE_INF.BTI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    BBS_WRITE_INF.BWI_AUID = CMN_USRM.USR_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_WRITE_INF.BWI_AUID = CMN_USRM_INF.USR_SID");
            sql.addSql("  order by");
            if (isOrderAsc) {
                sql.addSql("    BBS_WRITE_INF.BWI_ADATE asc");
            } else {
                sql.addSql("    BBS_WRITE_INF.BWI_ADATE desc");
            }

            sql.addIntValue(searchMdl.getBtiSid());

            log__.info(sql.toLogString());


            pstmt = getCon().prepareStatement(sql.toSqlString(),
                                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            for (int index = 1; index <= searchMdl.getEnd() && rs.next(); index++) {

                if (index >= searchMdl.getStart()) {
                    BulletinDspModel btMdl = new BulletinDspModel();
                    btMdl.setBwiSid(rs.getInt("BWI_SID"));
                    btMdl.setBwiValue(rs.getString("BWI_VALUE"));

                    //内容のURL文字列をハイパーリンク表示にする
                    btMdl.setBwiValueView(
                            StringUtilHtml.transToHTmlPlusAmparsant(
                                    NullDefault.getString(btMdl.getBwiValue(), "")));
                    btMdl.setBwiValueView(
                            StringUtil.transToLink(btMdl.getBwiValueView(),
                                                StringUtil.OTHER_WIN, true));
                    btMdl.setBwiValueView(
                            StringUtilHtml.returntoBR(btMdl.getBwiValueView()));

                    btMdl.setGrpSid(rs.getInt("BWI_EGID"));
                    btMdl.setGrpName(rs.getString("GRP_NAME"));
                    btMdl.setGrpJkbn(rs.getInt("GRP_JKBN"));
                    btMdl.setUserSid(rs.getInt("USR_SID"));
                    if (btMdl.getGrpSid() > 0) {
                        btMdl.setUserName(btMdl.getGrpName());
                        btMdl.setUserJkbn(btMdl.getGrpJkbn());
                    } else {
                        btMdl.setUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                        btMdl.setUserJkbn(rs.getInt("USR_JKBN"));
                    }

                    //写真公開可否を取得
                    int pictKf = rs.getInt("USI_PICT_KF");
                    btMdl.setUserPictKf(pictKf);
                    Long binSid = rs.getLong("BIN_SID");
                    btMdl.setPhotoFileSid(binSid);

                    //ユーザ画像ファイル名を設定
                    if (btMdl.getGrpSid() <= 0) {
                        String binFilePath = rs.getString("BIN_FILE_PATH");
                        if (binFilePath != null && pictKf == GSConstUser.INDIVIDUAL_INFO_OPEN) {
                            String binFileName = rs.getString("BIN_FILE_NAME");
                            btMdl.setPhotoFileName(binFileName);
                            btMdl.setPhotoFilePath(binFilePath);
                        }
                    }

                    btMdl.setAddUserSid(rs.getInt("BWI_AUID"));
                    btMdl.setBwiAdate(UDate.getInstanceTimestamp(rs.getTimestamp("BWI_ADATE")));
                    btMdl.setStrBwiAdate(__createDateStr(btMdl.getBwiAdate()));
                    btMdl.setBwiEdate(UDate.getInstanceTimestamp(rs.getTimestamp("BWI_EDATE")));
                    btMdl.setStrBwiEdate(__createDateStr(btMdl.getBwiEdate()));

                    UDate wrtDate = btMdl.getBwiAdate();
                    //登録日時 < 更新日時の場合、投稿更新フラグ = 更新 に設定
                    if (wrtDate.getTime() < btMdl.getBwiEdate().getTime()) {
                        wrtDate = btMdl.getBwiEdate();
                        btMdl.setWriteUpdateFlg(BulletinDspModel.WRITEUPDATEFLG_UPD);
                    }
                    //Newアイコン表示フラグ設定
                    if (UDateUtil.diffDay(wrtDate, new UDate()) < searchMdl.getNewCnt()) {
                        btMdl.setNewFlg(BulletinDspModel.NEWFLG_VIEW);
                    }
                    //ボタン表示フラグ設定
                    if (searchMdl.isAdmin() || btMdl.getAddUserSid() == searchMdl.getUserSid()
                            || searchMdl.isForumAdmin()) {
                        btMdl.setShowBtnFlg(BulletinDspModel.SHOWBTNFLG_YES);
                    }

                    //添付ファイル情報を取得
                    btMdl.setTmpFileList(getWriteTmpFileList(btMdl.getBwiSid()));

                    ret.add(btMdl);
                }
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
     * <br>[機  能] 指定された投稿の添付ファイル情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param bwiSid 投稿SID
     * @return 添付ファイル情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<CmnBinfModel> getWriteTmpFileList(int bwiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List < CmnBinfModel > ret = new ArrayList < CmnBinfModel >();
        CommonBiz cmnBiz = new CommonBiz();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    CMN_BINF.BIN_SID as BIN_SID,");
            sql.addSql("    CMN_BINF.BIN_FILE_NAME as BIN_FILE_NAME,");
            sql.addSql("    CMN_BINF.BIN_FILE_EXTENSION as BIN_FILE_EXTENSION,");
            sql.addSql("    CMN_BINF.BIN_FILE_PATH as BIN_FILE_PATH,");
            sql.addSql("    CMN_BINF.BIN_FILE_SIZE as BIN_FILE_SIZE");
            sql.addSql("  from");
            sql.addSql("    BBS_BIN,");
            sql.addSql("    CMN_BINF");
            sql.addSql("  where");
            sql.addSql("    BBS_BIN.BWI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    BBS_BIN.BIN_SID = CMN_BINF.BIN_SID");
            sql.addSql("  and");
            sql.addSql("    CMN_BINF.BIN_JKBN = ?");
            sql.addSql("  order by");
            sql.addSql("   CMN_BINF.BIN_FILE_NAME");

            sql.addIntValue(bwiSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CmnBinfModel binMdl = new CmnBinfModel();
                binMdl.setBinSid(rs.getLong("BIN_SID"));
                binMdl.setBinFileName(rs.getString("BIN_FILE_NAME"));
                binMdl.setBinFileExtension(rs.getString("BIN_FILE_EXTENSION"));
                binMdl.setBinFilePath(rs.getString("BIN_FILE_PATH"));
                long lngSize = rs.getInt("BIN_FILE_SIZE");
                binMdl.setBinFileSize(lngSize);
                String strSize = cmnBiz.getByteSizeString(lngSize);
                binMdl.setBinFileSizeDsp(strSize);
                ret.add(binMdl);
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
     * <br>[機  能] 指定されたスレッド内の投稿添付情報を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param btiSid スレッドSID
     * @return 削除(更新)件数
     * @throws SQLException SQL実行例外
     */
    public int deleteBbsBinInThread(int btiSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  delete from BBS_BIN");
            sql.addSql("  where");
            sql.addSql("    BWI_SID in (");
            sql.addSql("      select");
            sql.addSql("        BWI_SID");
            sql.addSql("      from");
            sql.addSql("        BBS_WRITE_INF");
            sql.addSql("      where");
            sql.addSql("        BTI_SID = ?");
            sql.addSql("    )");

            sql.addIntValue(btiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
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
     * <br>[機  能] 指定されたスレッド内の投稿のバイナリーファイル情報を論理削除する
     * <br>[解  説] 状態区分を9:削除に更新する
     * <br>[備  考]
     * @param btiSid スレッドSID
     * @return 削除(更新)件数
     * @throws SQLException SQL実行例外
     */
    public int deleteBinfForThread(int btiSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  update");
            sql.addSql("    CMN_BINF");
            sql.addSql("  set");
            sql.addSql("    BIN_JKBN = ?");
            sql.addSql("  where");
            sql.addSql("    BIN_SID in (");
            sql.addSql("      select");
            sql.addSql("        BIN_SID");
            sql.addSql("      from");
            sql.addSql("        BBS_BIN,");
            sql.addSql("        BBS_WRITE_INF");
            sql.addSql("      where");
            sql.addSql("        BBS_WRITE_INF.BTI_SID = ?");
            sql.addSql("      and");
            sql.addSql("        BBS_BIN.BWI_SID = BBS_WRITE_INF.BWI_SID");
            sql.addSql("    )");

            sql.addIntValue(GSConst.JTKBN_DELETE);
            sql.addIntValue(btiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
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
     * <br>[機  能] 指定された投稿のバイナリーファイル情報を論理削除する
     * <br>[解  説] 状態区分を9:削除に更新する
     * <br>[備  考]
     * @param bwiSid 投稿SID
     * @return 削除(更新)件数
     * @throws SQLException SQL実行例外
     */
    public int deleteBinfForWrite(int bwiSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  update");
            sql.addSql("    CMN_BINF");
            sql.addSql("  set");
            sql.addSql("    BIN_JKBN = ?");
            sql.addSql("  where");
            sql.addSql("    BIN_SID in (");
            sql.addSql("      select BIN_SID from BBS_BIN");
            sql.addSql("      where BWI_SID = ?");
            sql.addSql("    )");

            sql.addIntValue(GSConst.JTKBN_DELETE);
            sql.addIntValue(bwiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
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
     * <br>[機  能] 指定のフォーラムのバイナリーファイル情報を論理削除する
     * <br>[解  説] 状態区分を9:削除に更新する
     * <br>[備  考]
     * @param bfSid フォーラムSID
     * @return 削除(更新)件数
     * @throws SQLException SQL実行例外
     */
    public int deleteBinfForum(int bfSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  update");
            sql.addSql("    CMN_BINF");
            sql.addSql("  set");
            sql.addSql("    BIN_JKBN = ?");
            sql.addSql("  where");
            sql.addSql("    BIN_SID in (");
            sql.addSql("      select BIN_SID from BBS_FOR_INF");
            sql.addSql("      where BFI_SID = ?");
            sql.addSql("    )");

            sql.addIntValue(GSConst.JTKBN_DELETE);
            sql.addIntValue(bfSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
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
     * <br>[機  能] 指定された検索条件の検索結果件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param searchMdl 検索条件
     * @return 検索結果件数
     * @throws SQLException SQL例外発生
     */
    public int getSearchDtlCnt(BulletinSearchModel searchMdl)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        Connection con = getCon();
        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    count(distinct(BBS_THRE_INF.BTI_SID)) as searchCnt");
            createSearchDtlSql(sql, searchMdl);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("searchCnt");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] 指定された検索条件の検索結果一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param searchMdl 検索条件
     * @param reqMdl リクエストモデル
     * @return 検索結果一覧
     * @throws SQLException SQL例外発生
     */
    public List<BulletinDspModel> getSearchResultList(
            BulletinSearchModel searchMdl, RequestModel reqMdl)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List <BulletinDspModel> ret = new ArrayList <BulletinDspModel>();
        Connection con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    distinct(BBS_THRE_INF.BTI_SID) as BTI_SID,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE");
            createSearchDtlSql(sql, searchMdl);
            sql.addSql(" order by");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE desc");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();


            for (int index = 1; rs.next(); index++) {
                if  (index > searchMdl.getEnd()) {
                    break;
                }

                if (index >= searchMdl.getStart()) {
                    int btiSid = rs.getInt("BTI_SID");
                    BulletinDspModel btMdl =
                            getSearchResultData(btiSid, searchMdl.getUserSid(), reqMdl);
                    ret.add(btMdl);
                }
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
     * <br>[機  能] 検索結果情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param btiSid スレッドSID
     * @param userSid ユーザSID
     * @param reqMdl リクエストモデル
     * @return 検索結果情報
     * @throws SQLException SQL実行例外
     */
    public BulletinDspModel getSearchResultData(int btiSid, int userSid, RequestModel reqMdl)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        BulletinDspModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_THRE_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_THRE_INF.BTI_SID as BTI_SID,");
            sql.addSql("    BBS_THRE_INF.BTI_TITLE as BTI_TITLE,");
            sql.addSql("    BBS_THRE_INF.BTI_EGID as BTI_EGID,");

            sql.addSql("    BBS_THRE_INF.BTI_LIMIT as BTI_LIMIT,");
            sql.addSql("    BBS_THRE_INF.BTI_LIMIT_FR_DATE as BTI_LIMIT_FR_DATE,");
            sql.addSql("    BBS_THRE_INF.BTI_LIMIT_DATE as BTI_LIMIT_DATE,");

            sql.addSql("    CMN_GROUPM.GRP_NAME as GRP_NAME,");
            sql.addSql("    CMN_GROUPM.GRP_JKBN as GRP_JKBN,");
            sql.addSql("    BBS_WRITE_INF.BWI_VALUE as BWI_VALUE,");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE as BTS_WRT_DATE,");
            sql.addSql("    CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("    CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("    CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("    THRE_VIEW.BIV_VIEW_KBN as BIV_VIEW_KBN");
            sql.addSql("  from");
            sql.addSql("    CMN_USRM,");
            sql.addSql("    CMN_USRM_INF,");
            sql.addSql("    BBS_THRE_SUM,");
            sql.addSql("    BBS_WRITE_INF,");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("    left join");
            sql.addSql("      CMN_GROUPM");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_EGID = CMN_GROUPM.GRP_SID");
            sql.addSql("    left join");
            sql.addSql("      (select");
            sql.addSql("         BTI_SID,");
            sql.addSql("         BIV_VIEW_KBN");
            sql.addSql("       from");
            sql.addSql("         BBS_THRE_VIEW");
            sql.addSql("       where");
            sql.addSql("         BBS_THRE_VIEW.USR_SID = ?");
            sql.addSql("      ) THRE_VIEW");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_SID = THRE_VIEW.BTI_SID");
            sql.addSql("  where");
            sql.addSql("    BBS_THRE_INF.BTI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_SID = BBS_WRITE_INF.BTI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_WRITE_INF.BWI_AUID = CMN_USRM.USR_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_WRITE_INF.BWI_AUID = CMN_USRM_INF.USR_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_WRITE_INF.BWI_SID in (");
            sql.addSql("      select min(BWI_SID) from BBS_WRITE_INF");
            sql.addSql("      group by BTI_SID");
            sql.addSql("    )");
            sql.addSql(" order by");
            sql.addSql("    BBS_THRE_SUM.BTS_WRT_DATE desc");

            sql.addIntValue(userSid);
            sql.addIntValue(btiSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = new BulletinDspModel();
                ret.setBfiSid(rs.getInt("BFI_SID"));
                ret.setBtiSid(rs.getInt("BTI_SID"));
                ret.setBtiTitle(rs.getString("BTI_TITLE"));

                ret.setBtiLimit(rs.getInt("BTI_LIMIT"));
                if (rs.getInt("BTI_LIMIT") == GSConstBulletin.THREAD_LIMIT_YES) {
                    ret.setBtiLimitFrDate(
                            UDate.getInstanceTimestamp(rs.getTimestamp("BTI_LIMIT_FR_DATE")));

                    UDate limitDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTI_LIMIT_DATE"));
                    ret.setBtiLimitDate(limitDate);
                    ret.setStrBtiLimitDate(UDateUtil.getYymdJ(limitDate, reqMdl));
                    UDate now = new UDate();
                    if (now.compareDateYMD(limitDate) == UDate.SMALL) {
                        ret.setThreadFinFlg(1);
                    } else {
                        ret.setThreadFinFlg(0);
                    }
                }

                ret.setBwiValue(rs.getString("BWI_VALUE"));
                String bwiValue = ret.getBwiValue();
                if (bwiValue.length() > 200) {
                    bwiValue = bwiValue.substring(0, 200);
                    bwiValue = bwiValue.concat("...");
                }
                ret.setBwiValueView(bwiValue);
                UDate bfsWrtDate = UDate.getInstanceTimestamp(rs.getTimestamp("BTS_WRT_DATE"));
                ret.setWriteDate(bfsWrtDate);
                if (bfsWrtDate != null) {
                    ret.setStrWriteDate(__createDateStr(bfsWrtDate));
                }
                ret.setReadFlg(rs.getInt("BIV_VIEW_KBN"));

                ret.setGrpSid(rs.getInt("BTI_EGID"));
                ret.setGrpName(rs.getString("GRP_NAME"));
                ret.setGrpJkbn(rs.getInt("GRP_JKBN"));
                if (ret.getGrpSid() > 0) {
                    ret.setUserName(ret.getGrpName());
                } else {
                    ret.setUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                }
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
     * <br>[機  能] 詳細検索時の検索部SQLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql ＳＱＬバッファ
     * @param searchMdl 検索条件
     */
    public void createSearchDtlSql(SqlBuffer sql, BulletinSearchModel searchMdl) {

        //未読/既読を条件に含めるか
        boolean readFlg = false;
        int readKbn = searchMdl.getReadKbn();
        if (readKbn == GSConstBulletin.BBSREADKBN_NOREAD
        || readKbn == GSConstBulletin.BBSREADKBN_READ) {
            readFlg = true;
        }

        //SQL文
        sql.addSql("  from");
        sql.addSql("    BBS_FOR_INF,");
        sql.addSql("    CMN_USRM_INF,");
        sql.addSql("    BBS_WRITE_INF,");
        sql.addSql("    BBS_THRE_SUM,");
        sql.addSql("    BBS_THRE_INF");
        if (readFlg) {
            sql.addSql("    left join");
            sql.addSql("      (select");
            sql.addSql("         BTI_SID,");
            sql.addSql("         BIV_VIEW_KBN");
            sql.addSql("       from");
            sql.addSql("         BBS_THRE_VIEW");
            sql.addSql("       where");
            sql.addSql("         BBS_THRE_VIEW.USR_SID = ?");
            sql.addSql("      ) THRE_VIEW");
            sql.addSql("    on");
            sql.addSql("      BBS_THRE_INF.BTI_SID = THRE_VIEW.BTI_SID");
            sql.addIntValue(searchMdl.getUserSid());
        }

        sql.addSql("  where");
        sql.addSql("    BBS_THRE_INF.BTI_SID = BBS_WRITE_INF.BTI_SID");
        sql.addSql("  and");
        sql.addSql("    BBS_WRITE_INF.BWI_AUID = CMN_USRM_INF.USR_SID");
        sql.addSql("  and");
        sql.addSql("    BBS_THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");

        sql.addSql("  and");
        sql.addSql("    (");
        sql.addSql("       BBS_THRE_INF.BTI_LIMIT = ?");
        sql.addSql("     or");
        sql.addSql("       (");
        sql.addSql("          BBS_THRE_INF.BTI_LIMIT = ?");
        sql.addSql("        and");
        sql.addSql("          BBS_THRE_INF.BTI_LIMIT_FR_DATE <= ?");
        sql.addSql("       )");
        sql.addSql("    )");
        sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
        sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);

        UDate limitDate = UDate.getInstanceStr(searchMdl.getNow().getDateString());
        limitDate.setZeroHhMmSs();
        sql.addDateValue(limitDate);

        //フォーラム
        if (searchMdl.getBfiSid() > 0) {
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BFI_SID = ?");
            sql.addIntValue(searchMdl.getBfiSid());
        }

        List < String > keywordList = searchMdl.getKeywordList();

        if (keywordList != null && !keywordList.isEmpty()) {
            String keywordJoin = "   and";
            if (searchMdl.getKeywordKbn() == GSConstBulletin.KEYWORDKBN_OR) {
                keywordJoin = "   or";
            }

            //スレッドタイトル
            if (searchMdl.isSearchThreTitleFlg()) {
                sql.addSql("  and");

                if (searchMdl.isSearchWriteValueFlg()) {
                    sql.addSql("   (");
                }

                sql.addSql("     (");
                for (int i = 0; i < keywordList.size(); i++) {
                    if (i > 0) {
                        sql.addSql(keywordJoin);
                    }
                    sql.addSql("       BBS_THRE_INF.BTI_TITLE like '%"
                            + JDBCUtil.encFullStringLike(keywordList.get(i))
                            + "%' ESCAPE '"
                            + JDBCUtil.def_esc
                            + "'");
                }
                sql.addSql("     )");
            }

            //投稿内容
            if (searchMdl.isSearchWriteValueFlg()) {

                if (searchMdl.isSearchThreTitleFlg()) {
                    sql.addSql("    or");
                } else {
                    sql.addSql("  and");
                }

                sql.addSql("     (");
                for (int i = 0; i < keywordList.size(); i++) {
                    if (i > 0) {
                        sql.addSql(keywordJoin);
                    }
                    sql.addSql("       BBS_WRITE_INF.BWI_VALUE like '%"
                            + JDBCUtil.encFullStringLike(keywordList.get(i))
                            + "%' ESCAPE '"
                            + JDBCUtil.def_esc
                            + "'");
                }
                sql.addSql("     )");

                if (searchMdl.isSearchThreTitleFlg()) {
                    sql.addSql("   )");
                }
            }
        }

        //投稿者名
        if (!StringUtil.isNullZeroString(searchMdl.getContributorName())) {
            sql.addSql("  and");
            sql.addSql("    (");
            sql.addSql("      (");
            sql.addSql("        coalesce(BBS_WRITE_INF.BWI_EGID, 0) <= 0");
            sql.addSql("      and");
            sql.addSql("        (");
            sql.addSql("          CMN_USRM_INF.USI_SEI like '%"
                    + JDBCUtil.encFullStringLike(searchMdl.getContributorName())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
            sql.addSql("        or");
            sql.addSql("          CMN_USRM_INF.USI_MEI like '%"
                    + JDBCUtil.encFullStringLike(searchMdl.getContributorName())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
            sql.addSql("        )");
            sql.addSql("      )");
            sql.addSql("      or");
            sql.addSql("      (");
            sql.addSql("        coalesce(BBS_WRITE_INF.BWI_EGID, 0) > 0");
            sql.addSql("      and");
            sql.addSql("        BBS_WRITE_INF.BWI_EGID in (");
            sql.addSql("          select GRP_SID from CMN_GROUPM");
            sql.addSql("          where GRP_JKBN = " + GSConst.JTKBN_TOROKU);
            sql.addSql("          and GRP_NAME like '%"
                    + JDBCUtil.encFullStringLike(searchMdl.getContributorName())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
            sql.addSql("        )");
            sql.addSql("      )");
            sql.addSql("    )");
        }

        //未読/既読
        if (readFlg) {
            if (readKbn == GSConstBulletin.BBSREADKBN_NOREAD) {
                sql.addSql("  and");
                sql.addSql("    (");
                sql.addSql("       THRE_VIEW.BIV_VIEW_KBN is null");
                sql.addSql("     or");
                sql.addSql("       THRE_VIEW.BIV_VIEW_KBN = 0");
                sql.addSql("    )");
            } else if (readKbn == GSConstBulletin.BBSREADKBN_READ) {
                sql.addSql("  and");
                sql.addSql("    THRE_VIEW.BIV_VIEW_KBN = 1");
            }

        }
        //投稿日時
        if (searchMdl.getWriteDateFrom() != null) {
            sql.addSql("  and");
            sql.addSql("    BBS_WRITE_INF.BWI_ADATE >= ?");
            sql.addDateValue(searchMdl.getWriteDateFrom());
        }
        if (searchMdl.getWriteDateTo() != null) {
            sql.addSql("  and");
            sql.addSql("    BBS_WRITE_INF.BWI_ADATE <= ?");
            sql.addDateValue(searchMdl.getWriteDateTo());
        }
        //ユーザ・グループ
        if (!searchMdl.isAdmin()) {
            sql.addSql("  and");
            sql.addSql("    BBS_THRE_INF.BFI_SID in (");
            sql.addSql("      select BFI_SID from BBS_FOR_MEM");
            sql.addSql("      where");
            sql.addSql("        BBS_FOR_MEM.USR_SID = ?");
            sql.addSql("      or");
            sql.addSql("        BBS_FOR_MEM.GRP_SID in (");
            sql.addSql("          select GRP_SID from CMN_BELONGM");
            sql.addSql("          where CMN_BELONGM.USR_SID = ?");
            sql.addSql("        )");
            sql.addSql("    )");
            sql.addIntValue(searchMdl.getUserSid());
            sql.addIntValue(searchMdl.getUserSid());
        }
    }

    /**
     * <br>[機  能] フォーラムメンバーの件数を取得する
     * <br>[解  説] 削除ユーザは除く
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @return フォーラムメンバーの件数
     * @throws SQLException SQL実行例外
     */
    public int getForumMemCount(int bfiSid) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(FORUM_MEM.USR_SID) as USRCNT");
            sql.addSql(" from");
            sql.addSql("         (");
            sql.addSql("          select");
            sql.addSql("            BFI_SID,");
            sql.addSql("            USR_SID,");
            sql.addSql("            count(*)");
            sql.addSql("          from");
            sql.addSql("            (");
            sql.addSql("              select");
            sql.addSql("                BFI_SID,");
            sql.addSql("                USR_SID");
            sql.addSql("              from");
            sql.addSql("                BBS_FOR_MEM");
            sql.addSql("              where USR_SID > 0");
            sql.addSql("            union all");
            sql.addSql("              select");
            sql.addSql("                BBS_FOR_MEM.BFI_SID as BFI_SID,");
            sql.addSql("                CMN_BELONGM.USR_SID as USR_SID");
            sql.addSql("              from");
            sql.addSql("                BBS_FOR_MEM,");
            sql.addSql("                CMN_BELONGM");
            sql.addSql("              where");
            sql.addSql("                BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("            ) BBS_MEM");
            sql.addSql("            group by BFI_SID, USR_SID");
            sql.addSql("         ) FORUM_MEM,");
            sql.addSql("   CMN_USRM");
            sql.addSql(" where");
            sql.addSql("   FORUM_MEM.BFI_SID = ?");
            sql.addSql(" and");
            sql.addSql("   FORUM_MEM.USR_SID = CMN_USRM.USR_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");
            //ユーザSID < 100は除外
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID > ?");

            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);
            sql.addIntValue(GSConstUser.USER_RESERV_SID);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("USRCNT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return count;
    }

    /**
     * <br>[機  能] フォーラムメンバー一覧を取得する
     * <br>[解  説] 削除ユーザは除く
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param sortKey ソート対象
     * @param orderKey 並び順
     * @param page ページ
     * @param maxCnt ページ毎の最大表示件数
     * @return フォーラムメンバー一覧
     * @throws SQLException SQL実行例外
     */
    public List<CmnUsrmInfModel> getForumMemList(int bfiSid, int sortKey, int orderKey,
                                                int page, int maxCnt) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CmnUsrmInfModel> ret = new ArrayList<CmnUsrmInfModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   CMN_USRM_INF.USR_SID as USR_SID,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("   CMN_USRM_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("   CMN_USRM_INF.USI_MEI_KN as USI_MEI_KN,");
            sql.addSql("   case when");
            sql.addSql("     CMN_USRM_INF.USI_MAIL1_KF = 1 then null");
            sql.addSql("     else CMN_USRM_INF.USI_MAIL1");
            sql.addSql("   end USI_MAIL1,");
            sql.addSql("   case when");
            sql.addSql("     CMN_USRM_INF.USI_MAIL2_KF = 1 then null");
            sql.addSql("     else CMN_USRM_INF.USI_MAIL2");
            sql.addSql("   end USI_MAIL2,");
            sql.addSql("   case when");
            sql.addSql("     CMN_USRM_INF.USI_MAIL3_KF = 1 then null");
            sql.addSql("     else CMN_USRM_INF.USI_MAIL3");
            sql.addSql("   end USI_MAIL3,");
            sql.addSql("   CMN_USRM_INF.USI_SYAIN_NO as USI_SYAIN_NO,");
            sql.addSql("   CMN_USRM_INF.USI_SYOZOKU as USI_SYOZOKU,");
            sql.addSql("   (case");
            sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then ''");
            sql.addSql("      else (select");
            sql.addSql("            POS_NAME");
            sql.addSql("          from");
            sql.addSql("            CMN_POSITION");
            sql.addSql("          where CMN_USRM_INF.POS_SID = CMN_POSITION.POS_SID)");
            sql.addSql("    end) as USI_YAKUSYOKU,");
            sql.addSql("   (case");
            sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then 1");
            sql.addSql("      else 0");
            sql.addSql("    end) as YAKUSYOKU_EXIST,");
            sql.addSql("   (case");
            sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then 0");
            sql.addSql("      else (select");
            sql.addSql("              POS_SORT");
            sql.addSql("            from");
            sql.addSql("              CMN_POSITION");
            sql.addSql("            where CMN_USRM_INF.POS_SID = CMN_POSITION.POS_SID)");
            sql.addSql("    end) as YAKUSYOKU_SORT");

            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");
            //ユーザSID < 100は除外
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID > ?");

            sql.addSql(" and");
            sql.addSql(" (");
            sql.addSql("   CMN_USRM.USR_SID in (");
            sql.addSql("     select BBS_FOR_MEM.USR_SID from BBS_FOR_MEM");
            sql.addSql("     where");
            sql.addSql("       BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("   )");
            sql.addSql(" or");
            sql.addSql("   CMN_USRM.USR_SID in (");
            sql.addSql("     select CMN_BELONGM.USR_SID from");
            sql.addSql("       CMN_BELONGM,");
            sql.addSql("       BBS_FOR_MEM");
            sql.addSql("     where");
            sql.addSql("       BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("     and");
            sql.addSql("       BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("   )");
            sql.addSql(" )");

            //オーダー
            String orderStr = " asc";
            if (orderKey == GSConst.ORDER_KEY_DESC) {
                orderStr = " desc";
            }

            sql.addSql(" order by");
            log__.debug("sortkey = " + sortKey);
            //ソートカラム
            switch (sortKey) {
                //氏名
                case GSConstUser.USER_SORT_NAME:
                    sql.addSql("   CMN_USRM_INF.USI_SEI_KN" + orderStr + ",");
                    sql.addSql("   CMN_USRM_INF.USI_MEI_KN" + orderStr);
                    break;
                //社員/職員番号
                case GSConstUser.USER_SORT_SNO:
                    sql.addSql("   CMN_USRM_INF.USI_SYAIN_NO" + orderStr + ",");
                    sql.addSql("   CMN_USRM_INF.USI_SEI_KN asc,");
                    sql.addSql("   CMN_USRM_INF.USI_MEI_KN asc");
                    break;
                //役職
                case GSConstUser.USER_SORT_YKSK:
                    sql.addSql("  YAKUSYOKU_EXIST");
                    sql.addSql(orderStr);
                    sql.addSql("  ,");
                    sql.addSql("  YAKUSYOKU_SORT");
                    sql.addSql(orderStr);

                    break;
                default:
                    break;
            }
            sql.addIntValue(GSConst.JTKBN_TOROKU);
            sql.addIntValue(GSConstUser.USER_RESERV_SID);
            sql.addIntValue(bfiSid);
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString(),
                                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (page > 1) {
                rs.absolute((page - 1) * maxCnt);
            }

            for (int i = 0; rs.next() && i < maxCnt; i++) {
                CmnUsrmInfModel uinfMdl = new CmnUsrmInfModel();
                uinfMdl.setUsrSid(rs.getInt("USR_SID"));
                uinfMdl.setUsiSei(rs.getString("USI_SEI"));
                uinfMdl.setUsiMei(rs.getString("USI_MEI"));
                uinfMdl.setUsiSeiKn(rs.getString("USI_SEI_KN"));
                uinfMdl.setUsiMeiKn(rs.getString("USI_MEI_KN"));
                uinfMdl.setUsiMail1(rs.getString("USI_MAIL1"));
                uinfMdl.setUsiMail2(rs.getString("USI_MAIL2"));
                uinfMdl.setUsiMail3(rs.getString("USI_MAIL3"));
                uinfMdl.setUsiSyainNo(rs.getString("USI_SYAIN_NO"));
                uinfMdl.setUsiSyozoku(rs.getString("USI_SYOZOKU"));
                uinfMdl.setUsiYakusyoku(rs.getString("USI_YAKUSYOKU"));

                ret.add(uinfMdl);
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
     * <br>[機  能] 指定したフォーラムのメンバー数を取得する
     * <br>[解  説] 削除されたユーザ(CMN_USRM.USR_JKBN = 9:削除ユーザ)は除く
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @return フォーラムのメンバー数
     * @throws SQLException SQL実行例外
     */
    public int getForumMemberCount(int bfiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int memberCount = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(CMN_USRM_INF.USR_SID) as MEMBER_CNT");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID > ?");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql(" and");
            sql.addSql(" (");
            sql.addSql("   CMN_USRM.USR_SID in (");
            sql.addSql("     select BBS_FOR_MEM.USR_SID from BBS_FOR_MEM");
            sql.addSql("     where");
            sql.addSql("       BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("   )");
            sql.addSql(" or");
            sql.addSql("   CMN_USRM.USR_SID in (");
            sql.addSql("     select CMN_BELONGM.USR_SID from");
            sql.addSql("       CMN_BELONGM,");
            sql.addSql("       BBS_FOR_MEM");
            sql.addSql("     where");
            sql.addSql("       BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("     and");
            sql.addSql("       BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("   )");
            sql.addSql(" )");

            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);
            sql.addIntValue(GSConstUser.USER_RESERV_SID);
            sql.addIntValue(bfiSid);
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                memberCount = rs.getInt("MEMBER_CNT");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return memberCount;
    }

    /**
     * <br>[機  能] フォーラムメンバー閲覧状況を取得する
     * <br>[解  説] 削除ユーザは除く
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param btiSid スレッドSID
     * @param sortKey ソート対象
     * @param orderKey 並び順
     * @param page ページ
     * @param maxCnt ページ毎の最大表示件数
     * @return フォーラムメンバー一覧
     * @throws SQLException SQL実行例外
     */
    public List<BulletinWachModel> getForumMemWatchList(int bfiSid, int btiSid, int sortKey,
                                                int orderKey, int page, int maxCnt)
                                                throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<BulletinWachModel> ret = new ArrayList<BulletinWachModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   CMN_USRM_INF.USR_SID as USR_SID,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("   CMN_USRM_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("   CMN_USRM_INF.USI_MEI_KN as USI_MEI_KN,");
            sql.addSql("   COALESCE(USR_THRE_CNT.CNT, 0) as USR_THREAD_COUNT");

            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql("   left join");
            sql.addSql("   ( ");
            sql.addSql("      select USR_SID, count(*) as CNT");
            sql.addSql("      from BBS_THRE_VIEW");
            sql.addSql("      where BIV_VIEW_KBN = ?");
            sql.addSql("      and BFI_SID = ?");
            sql.addSql("      and BTI_SID = ?");
            sql.addSql("      group by USR_SID");
            sql.addSql("   ) USR_THRE_CNT");
            sql.addSql("   on");
            sql.addSql("   CMN_USRM_INF.USR_SID = USR_THRE_CNT.USR_SID");
            sql.addSql("   where");
            sql.addSql("     CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql("   and");
            sql.addSql("     CMN_USRM.USR_JKBN = ?");
            sql.addSql("   and");
            //ユーザSID < 100は除外
            sql.addSql("     CMN_USRM.USR_SID > ?");

            sql.addSql(" and");
            sql.addSql(" (");
            sql.addSql("   CMN_USRM.USR_SID in (");
            sql.addSql("     select BBS_FOR_MEM.USR_SID from BBS_FOR_MEM");
            sql.addSql("     where BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("   )");
            sql.addSql(" or");
            sql.addSql("   CMN_USRM.USR_SID in (");
            sql.addSql("     select CMN_BELONGM.USR_SID from");
            sql.addSql("       CMN_BELONGM,");
            sql.addSql("       BBS_FOR_MEM");
            sql.addSql("     where");
            sql.addSql("       BBS_FOR_MEM.BFI_SID = ?");
            sql.addSql("     and");
            sql.addSql("       BBS_FOR_MEM.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("   )");
            sql.addSql(" )");

            //オーダー
            String orderStr = " asc";
            if (orderKey == GSConst.ORDER_KEY_DESC) {
                orderStr = " desc";
            }

            sql.addSql(" order by");
            log__.debug("sortkey = " + sortKey);
            //ソートカラム
            switch (sortKey) {
                //氏名
                case GSConstUser.USER_SORT_NAME:
                    sql.addSql("   CMN_USRM_INF.USI_SEI_KN" + orderStr + ",");
                    sql.addSql("   CMN_USRM_INF.USI_MEI_KN" + orderStr);
                    break;

                //閲覧状況
                case GSConstBulletin.SORT_KEY_WATCH:
                    sql.addSql("   USR_THREAD_COUNT" + orderStr + ",");
                    sql.addSql("   CMN_USRM_INF.USI_SEI_KN asc,");
                    sql.addSql("   CMN_USRM_INF.USI_MEI_KN asc");
                    break;
                default:
                    break;
            }

            sql.addIntValue(GSConstBulletin.BBS_THRE_VIEW_YES);
            sql.addIntValue(bfiSid);
            sql.addIntValue(btiSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);
            sql.addIntValue(GSConstUser.USER_RESERV_SID);
            sql.addIntValue(bfiSid);
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString(),
                                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_READ_ONLY);
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (page > 1) {
                rs.absolute((page - 1) * maxCnt);
            }

            for (int i = 0; rs.next() && i < maxCnt; i++) {
                BulletinWachModel bwMdl = new BulletinWachModel();

                bwMdl.setUsrSid(rs.getInt("USR_SID"));
                bwMdl.setUsiSei(rs.getString("USI_SEI"));
                bwMdl.setUsiMei(rs.getString("USI_MEI"));
                bwMdl.setUsiSeiKn(rs.getString("USI_SEI_KN"));
                bwMdl.setUsiMeiKn(rs.getString("USI_MEI_KN"));
                bwMdl.setUserJkbn(rs.getInt("USR_THREAD_COUNT"));

                ret.add(bwMdl);
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
     * 日付文字列を取得する
     * @param date 日付
     * @return 日付文字列
     */
    private String __createDateStr(UDate date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(), date.getMonth() - 1, date.getIntDay());
        StringBuilder strDate = new StringBuilder("");
        strDate.append(UDateUtil.getSlashYYMD(date));
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                strDate.append("( 日 )");
                break;
            case Calendar.MONDAY:
                strDate.append("( 月 )");
                break;
            case Calendar.TUESDAY:
                strDate.append("( 火 )");
                break;
            case Calendar.WEDNESDAY:
                strDate.append("( 水 )");
                break;
            case Calendar.THURSDAY:
                strDate.append("( 木 )");
                break;
            case Calendar.FRIDAY:
                strDate.append("( 金 )");
                break;
            case Calendar.SATURDAY:
                strDate.append("( 土 )");
                break;
            default:
                break;
        }
        strDate.append(" ");
        strDate.append(UDateUtil.getSeparateHMS(date));
        return strDate.toString();
    }

    /**
     * 日付文字列を取得する
     * @param date 日付
     * @return 日付文字列
     */
    private String __createDateNoTimeStr(UDate date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(), date.getMonth() - 1, date.getIntDay());
        StringBuilder strDate = new StringBuilder("");
        strDate.append(UDateUtil.getSlashYYMD(date));
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                strDate.append("( 日 )");
                break;
            case Calendar.MONDAY:
                strDate.append("( 月 )");
                break;
            case Calendar.TUESDAY:
                strDate.append("( 火 )");
                break;
            case Calendar.WEDNESDAY:
                strDate.append("( 水 )");
                break;
            case Calendar.THURSDAY:
                strDate.append("( 木 )");
                break;
            case Calendar.FRIDAY:
                strDate.append("( 金 )");
                break;
            case Calendar.SATURDAY:
                strDate.append("( 土 )");
                break;
            default:
                break;
        }
        return strDate.toString();
    }

    /**
     * <p>指定された投稿SIDと添付ファイルバイナリSIDの組み合わせが存在するかを確認する
     * @param bwiSid 投稿SID
     * @param binSid 添付ファイルバイナリSID
     * @param forumSid フォーラムSID
     * @param threadSid スレッドSID
     * @return 結果 true:存在する false:存在しない
     * @throws SQLException SQL実行例外
     */
    public boolean existBbsWriTmp(int bwiSid, Long binSid,
            int forumSid, int threadSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean ret = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   BBS_BIN.BWI_SID as BWI_SID,");
            sql.addSql("   BBS_BIN.BIN_SID as BIN_SID,");
            sql.addSql("   BBS_WRITE_INF.BFI_SID as BFI_SID,");
            sql.addSql("   BBS_WRITE_INF.BTI_SID as BTI_SID");
            sql.addSql(" from");
            sql.addSql("   BBS_BIN");
            sql.addSql(" left join ");
            sql.addSql("   BBS_WRITE_INF");
            sql.addSql(" on ");
            sql.addSql("   BBS_BIN.BWI_SID = BBS_WRITE_INF.BWI_SID");
            sql.addSql(" where ");
            sql.addSql("   BBS_BIN.BWI_SID=?");
            sql.addSql(" and ");
            sql.addSql("   BBS_BIN.BIN_SID=?");
            sql.addSql(" and ");
            sql.addSql("   BBS_WRITE_INF.BFI_SID=?");
            sql.addSql(" and ");
            sql.addSql("   BBS_WRITE_INF.BTI_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bwiSid);
            sql.addLongValue(binSid);
            sql.addIntValue(forumSid);
            sql.addIntValue(threadSid);

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
     * <br>[機  能] 指定したフォーラム、ユーザの未読スレッド件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param userSid ユーザSID
     * @return スレッド情報一覧
     * @throws SQLException SQL実行例外
     */
    public int getUnreadThreadCount(int bfiSid, int userSid) throws SQLException {
        int ret = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql("  select");
            sql.addSql("    count(BBS_THRE_INF.BTI_SID) as CNT");
            sql.addSql("  from");
            sql.addSql("    BBS_THRE_INF");
            sql.addSql("  where");
            sql.addSql("    BBS_THRE_INF.BFI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    not exists");
            sql.addSql("     (");
            sql.addSql("       select 1 from BBS_THRE_VIEW");
            sql.addSql("       where");
            sql.addSql("         BIV_VIEW_KBN = 1");
            sql.addSql("       and");
            sql.addSql("         USR_SID = ?");
            sql.addSql("       and");
            sql.addSql("         BBS_THRE_INF.BTI_SID = BBS_THRE_VIEW.BTI_SID");
            sql.addSql("     )");
            sql.addSql("  and");
            sql.addSql("    (");
            sql.addSql("       BTI_LIMIT = ?");
            sql.addSql("     or");
            sql.addSql("       (");
            sql.addSql("          BTI_LIMIT = ?");
            UDate limitDate = new UDate();
            limitDate.setZeroHhMmSs();
            sql.addSql("        and");
            sql.addSql("          BTI_LIMIT_FR_DATE <= ?");
            sql.addSql("        and");
            sql.addSql("          BTI_LIMIT_DATE >= ?");
            sql.addSql("       )");
            sql.addSql("    )");

            sql.addIntValue(bfiSid);
            sql.addIntValue(userSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            sql.addDateValue(limitDate);
            sql.addDateValue(limitDate);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
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
     * <br>[機  能] スレッド内投稿の添付ファイルサイズの合計を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param btiSid スレッドSID
     * @return 添付ファイルサイズの合計
     * @throws SQLException SQL実行例外
     */
    public long getSumThreTempFileSize(int btiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long fileSize = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    sum(CMN_BINF.BIN_FILE_SIZE) as SUM_FILE_SIZE");
            sql.addSql("  from");
            sql.addSql("    BBS_WRITE_INF,");
            sql.addSql("    BBS_BIN,");
            sql.addSql("    CMN_BINF");
            sql.addSql("  where");
            sql.addSql("    BBS_WRITE_INF.BTI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    BBS_WRITE_INF.BWI_SID = BBS_BIN.BWI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_BIN.BIN_SID = CMN_BINF.BIN_SID");
            sql.addSql("  and");
            sql.addSql("    CMN_BINF.BIN_JKBN = ?");

            sql.addIntValue(btiSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                fileSize = rs.getLong("SUM_FILE_SIZE");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return fileSize;
    }

    /**
     * <br>[機  能] 指定した投稿の添付ファイルサイズの合計を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param bwiSid 投稿SID
     * @return 添付ファイルサイズの合計
     * @throws SQLException SQL実行例外
     */
    public long getSumWriteTempFileSize(int bwiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long fileSize = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    sum(CMN_BINF.BIN_FILE_SIZE) as SUM_FILE_SIZE");
            sql.addSql("  from");
            sql.addSql("    BBS_WRITE_INF,");
            sql.addSql("    BBS_BIN,");
            sql.addSql("    CMN_BINF");
            sql.addSql("  where");
            sql.addSql("    BBS_WRITE_INF.BWI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    BBS_WRITE_INF.BWI_SID = BBS_BIN.BWI_SID");
            sql.addSql("  and");
            sql.addSql("    BBS_BIN.BIN_SID = CMN_BINF.BIN_SID");
            sql.addSql("  and");
            sql.addSql("    CMN_BINF.BIN_JKBN = ?");

            sql.addIntValue(bwiSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                fileSize = rs.getLong("SUM_FILE_SIZE");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return fileSize;
    }

    /**
     * <br>[機  能] ディスク容量の警告を行うフォーラムの一覧を取得する
     * <br>[解  説] 指定したユーザが参照可能なフォーラムのみを対象とする
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者 false:一般ユーザ
     * @return フォーラム情報
     * @throws SQLException SQL実行例外
     */
    public List<BulletinForumDiskModel> getWarnForumList(int userSid, boolean admin)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List <BulletinForumDiskModel> ret = new ArrayList <BulletinForumDiskModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    BBS_FOR_INF.BFI_DISK as BFI_DISK,");
            sql.addSql("    BBS_FOR_INF.BFI_DISK_SIZE as BFI_DISK_SIZE,");
            sql.addSql("    BBS_FOR_INF.BFI_WARN_DISK as BFI_WARN_DISK,");
            sql.addSql("    BBS_FOR_INF.BFI_WARN_DISK_TH as BFI_WARN_DISK_TH,");
            sql.addSql("    BBS_FOR_SUM.BFS_SIZE as BFS_SIZE");
            sql.addSql("  from");
            sql.addSql("    BBS_FOR_INF,");
            sql.addSql("    BBS_FOR_SUM");

            sql.addSql("  where");
            sql.addSql("    BBS_FOR_INF.BFI_DISK = ?");
            sql.addSql("  and");
            sql.addSql("    BBS_FOR_INF.BFI_WARN_DISK = ?");
            sql.addIntValue(GSConstBulletin.BFI_DISK_LIMITED);
            sql.addIntValue(GSConstBulletin.BFI_WARN_DISK_YES);

            if (!admin) {
                sql.addSql("  and");
                sql.addSql("    BBS_FOR_INF.BFI_SID in (");
                sql.addSql("      select");
                sql.addSql("        BFI_SID");
                sql.addSql("      from");
                sql.addSql("        BBS_FOR_ADMIN");
                sql.addSql("      where");
                sql.addSql("        USR_SID = ?");
                sql.addSql("    )");
                sql.addIntValue(userSid);
            }
            sql.addSql("  and");
            sql.addSql("    BBS_FOR_INF.BFI_SID = BBS_FOR_SUM.BFI_SID");
            sql.addSql("  order by");
            sql.addSql("    BBS_FOR_INF.BFI_SORT, BBS_FOR_INF.BFI_NAME");
            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BulletinForumDiskModel diskMdl = new BulletinForumDiskModel();
                diskMdl.setBfiSid(rs.getInt("BFI_SID"));
                diskMdl.setBfiName(rs.getString("BFI_NAME"));
                diskMdl.setBfiDisk(rs.getInt("BFI_DISK"));
                diskMdl.setBfiDiskSize(rs.getInt("BFI_DISK_SIZE"));
                diskMdl.setBfiWarnDisk(rs.getInt("BFI_WARN_DISK"));
                diskMdl.setBfiWarnDiskTh(rs.getInt("BFI_WARN_DISK_TH"));
                diskMdl.setBfsSize(rs.getLong("BFS_SIZE"));

                ret.add(diskMdl);
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
     * <br>[機  能] 指定したフォーラムのディスク容量情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @return フォーラムのディスク容量情報
     * @throws SQLException SQL実行例外
     */
    public BulletinForumDiskModel getForumDiskData(int bfiSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BulletinForumDiskModel diskData = null;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("  select");
            sql.addSql("    BBS_FOR_INF.BFI_SID as BFI_SID,");
            sql.addSql("    BBS_FOR_INF.BFI_NAME as BFI_NAME,");
            sql.addSql("    BBS_FOR_INF.BFI_DISK as BFI_DISK,");
            sql.addSql("    BBS_FOR_INF.BFI_DISK_SIZE as BFI_DISK_SIZE,");
            sql.addSql("    BBS_FOR_INF.BFI_WARN_DISK as BFI_WARN_DISK,");
            sql.addSql("    BBS_FOR_INF.BFI_WARN_DISK_TH as BFI_WARN_DISK_TH,");
            sql.addSql("    BBS_FOR_SUM.BFS_SIZE as BFS_SIZE");
            sql.addSql("  from");
            sql.addSql("    BBS_FOR_INF,");
            sql.addSql("    BBS_FOR_SUM");

            sql.addSql("  where");
            sql.addSql("    BBS_FOR_INF.BFI_SID = ?");
            sql.addSql("  and");
            sql.addSql("    BBS_FOR_INF.BFI_SID = BBS_FOR_SUM.BFI_SID");
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());
            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                diskData = new BulletinForumDiskModel();
                diskData.setBfiSid(rs.getInt("BFI_SID"));
                diskData.setBfiName(rs.getString("BFI_NAME"));
                diskData.setBfiDisk(rs.getInt("BFI_DISK"));
                diskData.setBfiDiskSize(rs.getInt("BFI_DISK_SIZE"));
                diskData.setBfiWarnDisk(rs.getInt("BFI_WARN_DISK"));
                diskData.setBfiWarnDiskTh(rs.getInt("BFI_WARN_DISK_TH"));
                diskData.setBfsSize(rs.getLong("BFS_SIZE"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return diskData;
    }

    /**
     * <br>[機  能] 指定したフォーラムの最終更新日時を最新のものに更新する
     * <br>[解  説]フォーラムの最終更新日時と表示されているスレッド内の最新日時から
     *                    最新のものを取得して更新する
     * <br>[備  考]
     * @param bfiSid フォーラムSID
     * @param now 現在日時
     * @return 件数
     * @throws SQLException SQL実行時例外
     */
    public int updateBfsWrtDateBatch(int bfiSid, UDate now) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("   update");
            sql.addSql("     BBS_FOR_SUM");
            sql.addSql("   set ");
            sql.addSql("     BFS_WRT_DATE=(");
            sql.addSql("       select");
            sql.addSql("         max(WRT_DATE)");
            sql.addSql("       from");
            sql.addSql("         (");
            sql.addSql("          select");
            sql.addSql("            max(BTS_WRT_DATE) as WRT_DATE");
            sql.addSql("          from");
            sql.addSql("            BBS_THRE_SUM,");
            sql.addSql("            BBS_THRE_INF");
            sql.addSql("          where");
            sql.addSql("            BBS_THRE_INF.BFI_SID = ?");
            sql.addSql("          and");
            sql.addSql("            BBS_THRE_INF.BTI_SID = BBS_THRE_SUM.BTI_SID");
            sql.addSql("          and");
            sql.addSql("            (");
            sql.addSql("               BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("             or");
            sql.addSql("               (");
            sql.addSql("                  BBS_THRE_INF.BTI_LIMIT = ?");
            sql.addSql("                and");
            sql.addSql("                  BBS_THRE_INF.BTI_LIMIT_FR_DATE <=?");
            sql.addSql("                and");
            sql.addSql("                  BBS_THRE_INF.BTI_LIMIT_DATE >= ?");
            sql.addSql("               )");
            sql.addSql("            )");
            sql.addSql("          union");
            sql.addSql("          select");
            sql.addSql("            BFS_WRT_DATE as WRT_DATE");
            sql.addSql("          from");
            sql.addSql("            BBS_FOR_SUM");
            sql.addSql("          where");
            sql.addSql("            BFI_SID = ?");
            sql.addSql("         )  as WRT_DATE");
            sql.addSql("     )");
            sql.addSql("   where ");
            sql.addSql("     BFI_SID = ?");


            sql.addIntValue(bfiSid);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_NO);
            sql.addIntValue(GSConstBulletin.THREAD_LIMIT_YES);
            now.setZeroHhMmSs();
            sql.addDateValue(now);
            sql.addDateValue(now);
            sql.addIntValue(bfiSid);
            sql.addIntValue(bfiSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            count = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }

        return count;

    }
}
