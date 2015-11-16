package jp.groupsession.v2.sch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.csv.CSVException;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.dao.SchDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.model.UserSearchModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.sch.sch100.SchCsvRecordListenerIppanImpl;
import jp.groupsession.v2.sch.sch100.ScheduleCsvModel;
import jp.groupsession.v2.sch.sch100.ScheduleListSearchModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] スケジュール情報の検索を行うためのDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ScheduleSearchDao extends AbstractDao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(ScheduleSearchDao.class);

    /**
     * <p>
     * デフォルトコンストラクタ
     */
    public ScheduleSearchDao() {
    }
    /**
     * <p>
     * デフォルトコンストラクタ
     * @param con DBコネクション
     */
    public ScheduleSearchDao(Connection con) {
        super(con);
    }

    /**
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     *
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ区分
     * @param pub 公開区分 ※-1を指定すると条件から除外されます
     * @param from 日付from
     * @param to 日付to
     * @param mod 取得モード (週間:"1" 月間:"2" 日間:"3")
     * @param sessionUserSid セッションユーザSID
     * @return ArrayList in SchDataModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SchDataModel> select(int usrSid, int usrKbn, int pub,
            UDate from, UDate to, String mod, int sessionUserSid) throws SQLException {

        ArrayList<SchDataModel> ret = new ArrayList<SchDataModel>();

        //閲覧不可のグループ or ユーザが指定された場合、空のListを返す
        SchDao schDao = new SchDao(getCon());
        List<Integer> noAccessList = null;
        if (usrKbn == GSConstSchedule.USER_KBN_GROUP) {
            noAccessList = schDao.getNotAccessGrpList(sessionUserSid);
        } else {
            if (usrSid != sessionUserSid) {
                noAccessList = schDao.getNotAccessUserList(sessionUserSid);
            } else {
                //ユーザ = セッションユーザの場合
                noAccessList = new ArrayList<Integer>();
            }
        }
        if (noAccessList.indexOf(usrSid) >= 0) {
            return ret;
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_GRP_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_TO_DATE,");
            sql.addSql("   SCD_DAILY,");
            sql.addSql("   SCD_BGCOLOR,");
            sql.addSql("   SCD_TITLE,");
            sql.addSql("   SCD_VALUE,");
            sql.addSql("   SCD_BIKO,");
            sql.addSql("   SCD_PUBLIC,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_ADATE,");
            sql.addSql("   SCD_EUID,");
            sql.addSql("   SCD_EDATE,");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCD_EDIT,");
            sql.addSql("   SCD_ATTEND_KBN,");
            sql.addSql("   SCD_ATTEND_ANS,");
            sql.addSql("   SCD_ATTEND_AU_KBN");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_USR_SID = ?");
            sql.addIntValue(usrSid);
            sql.addSql(" and ");
            sql.addSql("   SCD_USR_KBN = ?");
            sql.addIntValue(usrKbn);
            if (pub != -1) {
                sql.addSql(" and ");
                sql.addSql("   SCD_PUBLIC = ?");
                sql.addIntValue(pub);
            }

            /** ********** 時間設定無し (H2用) *********** */
            if (mod.equals(GSConstSchedule.DSP_MOD_WEEK)) {
                UDate fromDateStart = UDate.getInstance(from.getTimeMillis());
                fromDateStart.setZeroHhMmSs();
                UDate fromDateEnd = fromDateStart.cloneUDate();
                fromDateEnd.setMaxHhMmSs();

                UDate toDateStart = UDate.getInstance(to.getTimeMillis());
                toDateStart.setZeroHhMmSs();
                UDate toDateEnd = toDateStart.cloneUDate();
                toDateEnd.setMaxHhMmSs();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("     and cast('" + toDateStr + "' as DATE)");
                sql.addSql("     SCD_FR_DATE >= ?");
                sql.addSql("     and SCD_FR_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
//                sql.addSql("     between cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("     and cast('" + toDateStr + "' as DATE) ");
                sql.addSql("     SCD_TO_DATE >= ?");
                sql.addSql("     and SCD_TO_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
//                sql.addSql("    cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("    between");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("    and");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(fromDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
//                sql.addSql("    cast('" + toDateStr + "' as DATE)");
//                sql.addSql("    between");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("    and");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(toDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("  )");
            }
            if (mod.equals(GSConstSchedule.DSP_MOD_MONTH)) {
                UDate fromDateStart = UDate.getInstance(from.getTimeMillis());
                fromDateStart.setZeroHhMmSs();
                UDate fromDateEnd = fromDateStart.cloneUDate();
                fromDateEnd.setMaxHhMmSs();

                UDate toDateStart = UDate.getInstance(to.getTimeMillis());
                toDateStart.setZeroHhMmSs();
                UDate toDateEnd = toDateStart.cloneUDate();
                toDateEnd.setMaxHhMmSs();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE >= ?");
                sql.addSql("     and SCD_FR_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_TO_DATE >= ?");
                sql.addSql("     and SCD_TO_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(fromDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(toDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("  )");
            }

            if (mod.equals(GSConstSchedule.DSP_MOD_DAY)) {

                sql.addSql(" and");
                sql.addSql("   (");
                sql.addSql("     (");
                sql.addSql("      SCD_FR_DATE between  ? ");
                sql.addSql("      and ?");
                sql.addSql("     )");
                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      SCD_TO_DATE between ?");
                sql.addSql("      and ?");
                sql.addSql("     )");
                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("     )");
                sql.addDateValue(from);
                sql.addDateValue(from);
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ?  >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ?  < SCD_TO_DATE");
                sql.addSql("     )");
                sql.addDateValue(to);
                sql.addDateValue(to);



                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      SCD_FR_DATE between ?");
                sql.addSql("      and ?");
                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addSql("      and");
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      SCD_TO_DATE between ?");
                sql.addSql("      and ?");
                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addSql("      and");
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("      and");
                sql.addDateValue(from);
                sql.addDateValue(from);
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("      and");
                sql.addDateValue(to);
                sql.addDateValue(to);
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addSql("   )");



            }
            sql.addSql(" order by ");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_DAILY DESC,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_SID");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            Map<Integer, ArrayList<Integer>> usrBlongMap
            = new HashMap<Integer, ArrayList<Integer>>();

            while (rs.next()) {
                ArrayList<Integer> blongGpSidList = null;
                if (GSConstSchedule.USER_KBN_USER == usrKbn) {
                //スケジュールユーザSID
                Integer scdUsrSid
                  = NullDefault.getInt(String.valueOf(rs.getInt("SCD_USR_SID")), 0);
                    //ユーザスケジュールを取得する場合
                    if (scdUsrSid > 0) {
                        //取得したスケジュールのユーザの所属グループを取得する
                        if (usrBlongMap.get(scdUsrSid) != null) {
                            //既にグループSIDを取得済みのユーザ
                            blongGpSidList = usrBlongMap.get(scdUsrSid);
                        } else {
                            //グループSIDを未取得のユーザ
                            CmnBelongmDao bdao = new CmnBelongmDao(con);
                            blongGpSidList
                               = bdao.selectUserBelongGroupSid(scdUsrSid);
                            if (blongGpSidList != null) {
                                usrBlongMap.put(scdUsrSid, blongGpSidList);
                            }
                        }
                    }
                }
                ret.add(__getSchDataFromRs(rs, blongGpSidList));
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
     * <p>
     * グループSIDのリスト、公開区分、日付範囲を指定しスケジュール情報を取得する
     *
     * @param sidList グループSIDのリスト
     * @param pub 公開区分 ※-1を指定すると条件から除外されます
     * @param from 日付from
     * @param to 日付to
     * @param mod 取得モード (週間:"1" 月間:"2" 日間:"3")
     * @param sessionUserSid セッションユーザSID
     * @return ArrayList in SchDataModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SchDataModel> getBelongGroupSchData2(ArrayList<Integer> sidList, int pub,
            UDate from, UDate to, String mod, int sessionUserSid) throws SQLException {

        ArrayList<SchDataModel> ret = new ArrayList<SchDataModel>();

        //閲覧を許可されていないグループを除外する
        List<Integer> grpSidList = new ArrayList<Integer>();

        if (!sidList.isEmpty()) {
            SchDao schDao = new SchDao(getCon());
            List<Integer> notAccessGrpList = schDao.getNotAccessGrpList(sessionUserSid);
            for (int grpSid : sidList) {
                if (notAccessGrpList.indexOf(grpSid) < 0) {
                    grpSidList.add(grpSid);
                }
            }

            //対象グループが存在しない場合、空のListを返す
            if (grpSidList.isEmpty()) {
                return ret;
            }
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_GRP_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_TO_DATE,");
            sql.addSql("   SCD_DAILY,");
            sql.addSql("   SCD_BGCOLOR,");
            sql.addSql("   SCD_TITLE,");
            sql.addSql("   SCD_VALUE,");
            sql.addSql("   SCD_BIKO,");
            sql.addSql("   SCD_PUBLIC,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_ADATE,");
            sql.addSql("   SCD_EUID,");
            sql.addSql("   SCD_EDATE,");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCD_EDIT,");
            sql.addSql("   SCD_ATTEND_KBN,");
            sql.addSql("   SCD_ATTEND_ANS,");
            sql.addSql("   SCD_ATTEND_AU_KBN");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_USR_SID in ( ");
            if (grpSidList.size() < 1) {
                sql.addSql("   -1");
            }
            for (int i = 0; i < grpSidList.size(); i++) {
                if (i == 0) {
                    sql.addSql("   ?");
                } else {
                    sql.addSql("   ,?");
                }
                sql.addIntValue(grpSidList.get(i).intValue());
            }
            sql.addSql(" ) ");
            sql.addSql(" and ");
            sql.addSql("   SCD_USR_KBN = ?");
            sql.addIntValue(GSConstSchedule.USER_KBN_GROUP);
            if (pub != -1) {
                sql.addSql(" and ");
                sql.addSql("   SCD_PUBLIC = ?");
                sql.addIntValue(pub);
            }

            /** ********** 時間設定無し (H2用) *********** */
            if (mod.equals(GSConstSchedule.DSP_MOD_WEEK)) {
                UDate fromDateStart = UDate.getInstance(from.getTimeMillis());
                fromDateStart.setZeroHhMmSs();
                UDate fromDateEnd = fromDateStart.cloneUDate();
                fromDateEnd.setMaxHhMmSs();

                UDate toDateStart = UDate.getInstance(to.getTimeMillis());
                toDateStart.setZeroHhMmSs();
                UDate toDateEnd = toDateStart.cloneUDate();
                toDateEnd.setMaxHhMmSs();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE >= ?");
                sql.addSql("     and SCD_FR_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_TO_DATE >= ?");
                sql.addSql("     and SCD_TO_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(fromDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(toDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("  )");
            }
            if (mod.equals(GSConstSchedule.DSP_MOD_MONTH)) {
                UDate fromDateStart = UDate.getInstance(from.getTimeMillis());
                fromDateStart.setZeroHhMmSs();
                UDate fromDateEnd = fromDateStart.cloneUDate();
                fromDateEnd.setMaxHhMmSs();

                UDate toDateStart = UDate.getInstance(to.getTimeMillis());
                toDateStart.setZeroHhMmSs();
                UDate toDateEnd = toDateStart.cloneUDate();
                toDateEnd.setMaxHhMmSs();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE >= ?");
                sql.addSql("     and SCD_FR_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_TO_DATE >= ?");
                sql.addSql("     and SCD_TO_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(fromDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(toDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("  )");
            }

            if (mod.equals(GSConstSchedule.DSP_MOD_DAY)) {

                sql.addSql(" and");
                sql.addSql("   (");
                sql.addSql("     (");
                sql.addSql("      SCD_FR_DATE >= ?");
                sql.addSql("      and");
                sql.addSql("      SCD_FR_DATE <= ?");
                sql.addSql("     )");
                sql.addDateValue(from);
                sql.addDateValue(to);

                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      SCD_TO_DATE > ?");
                sql.addSql("      and");
                sql.addSql("      SCD_TO_DATE <= ?");
                sql.addSql("     )");
                sql.addDateValue(from);
                sql.addDateValue(to);

                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("     )");
                sql.addDateValue(from);
                sql.addDateValue(from);

                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("     )");
                sql.addDateValue(to);
                sql.addDateValue(to);

                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      SCD_FR_DATE >= ?");
                sql.addSql("      and");
                sql.addSql("      SCD_FR_DATE <= ?");
                sql.addSql("      and");
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      SCD_TO_DATE > ?");
                sql.addSql("      and");
                sql.addSql("      SCD_TO_DATE <= ?");
                sql.addSql("      and");
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("      and");
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addDateValue(from);
                sql.addDateValue(from);
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ?  >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ?  < SCD_TO_DATE");
                sql.addSql("      and");
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addDateValue(to);
                sql.addDateValue(to);
                sql.addSql("   )");
            }
            sql.addSql(" order by ");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_DAILY DESC,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_SID");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            Map<Integer, ArrayList<Integer>> usrBlongMap
            = new HashMap<Integer, ArrayList<Integer>>();

            while (rs.next()) {

                //スケジュールユーザSID
                Integer scdUsrSid
                  = NullDefault.getInt(String.valueOf(rs.getInt("SCD_USR_SID")), 0);
                ArrayList<Integer> blongGpSidList = null;

                if (scdUsrSid > 0) {
                    //取得したスケジュールのユーザの所属グループを取得する
                    if (usrBlongMap.get(scdUsrSid) != null) {
                        //既にグループSIDを取得済みのユーザ
                        blongGpSidList = usrBlongMap.get(scdUsrSid);
                    } else {
                        //グループSIDを未取得のユーザ
                        CmnBelongmDao bdao = new CmnBelongmDao(con);
                        blongGpSidList
                           = bdao.selectUserBelongGroupSid(scdUsrSid);
                        if (blongGpSidList != null) {
                            usrBlongMap.put(scdUsrSid, blongGpSidList);
                        }
                    }
                }

                ret.add(__getSchDataFromRs(rs, blongGpSidList));
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
     * <p>
     * ユーザ(複数)、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     * @param belongList ユーザ情報リスト
     * @param usrKbn ユーザ区分
     * @param pub 公開区分 ※-1を指定すると条件から除外されます
     * @param from 日付from
     * @param to 日付to
     * @param mod 取得モード (週間:"1" 月間:"2" 日間:"3")
     * @return ArrayList in SchDataModel
     * @throws SQLException
     *             SQL実行例外
     */
    public ArrayList<SchDataModel> selectUsers(
            ArrayList<UserSearchModel> belongList, int usrKbn, int pub,
            UDate from, UDate to, String mod) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SchDataModel> ret = new ArrayList<SchDataModel>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_GRP_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_TO_DATE,");
            sql.addSql("   SCD_DAILY,");
            sql.addSql("   SCD_BGCOLOR,");
            sql.addSql("   SCD_TITLE,");
            sql.addSql("   SCD_VALUE,");
            sql.addSql("   SCD_BIKO,");
            sql.addSql("   SCD_PUBLIC,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_ADATE,");
            sql.addSql("   SCD_EUID,");
            sql.addSql("   SCD_EDATE,");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCD_EDIT,");
            sql.addSql("   SCD_ATTEND_KBN,");
            sql.addSql("   SCD_ATTEND_ANS,");
            sql.addSql("   SCD_ATTEND_AU_KBN,");
            sql.addSql("   CMN_USRM_INF.USI_SEI, ");
            sql.addSql("   CMN_USRM_INF.USI_MEI ");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where ");
            sql.addSql("   SCD_USR_SID in(");
            CmnUsrmInfModel uMdl = null;
            if (belongList.size() < 1) {
                sql.addSql("   -1");
            }
            for (int i = 0; i < belongList.size(); i++) {
                uMdl = belongList.get(i);
                if (i == 0) {
                    sql.addSql("   ?");
                } else {
                    sql.addSql("   ,?");
                }
                sql.addIntValue(uMdl.getUsrSid());
            }
            sql.addSql("   )");

            sql.addSql(" and ");
            sql.addSql("   SCD_USR_KBN = ?");
            sql.addIntValue(usrKbn);
            if (pub != -1) {
                sql.addSql(" and ");
                sql.addSql("   SCD_PUBLIC = ?");
                sql.addIntValue(pub);
            }

            /** ********** 時間設定無し (derby用) *********** */
            if (mod.equals(GSConstSchedule.DSP_MOD_WEEK)) {
                UDate fromDateStart = UDate.getInstance(from.getTimeMillis());
                fromDateStart.setZeroHhMmSs();
                UDate fromDateEnd = fromDateStart.cloneUDate();
                fromDateEnd.setMaxHhMmSs();

                UDate toDateStart = UDate.getInstance(to.getTimeMillis());
                toDateStart.setZeroHhMmSs();
                UDate toDateEnd = toDateStart.cloneUDate();
                toDateEnd.setMaxHhMmSs();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE between ?");
                sql.addSql("     and ?");
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_TO_DATE between ?");
                sql.addSql("     and ?");
                sql.addSql("   )");
                UDate searchFrDate = UDate.getInstance(from.getTimeMillis());
                searchFrDate.setZeroHhMmSs();
                UDate searchToDate = UDate.getInstance(to.getTimeMillis());
                searchToDate.setMaxHhMmSs();
                sql.addDateValue(searchFrDate);
                sql.addDateValue(searchToDate);
                sql.addDateValue(searchFrDate);
                sql.addDateValue(searchToDate);

                sql.addSql("   or");
                sql.addSql("   (");
//                sql.addSql("    cast('" + fromDateStr + "' as DATE)");
//                sql.addSql("    between");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("    and");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
                sql.addSql("    SCD_FR_DATE <= ?");
                sql.addSql("    and SCD_TO_DATE >= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(fromDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
//                sql.addSql("    cast('" + toDateStr + "' as DATE)");
//                sql.addSql("    between");
//                sql.addSql("     cast(SCD_FR_DATE as DATE)");
//                sql.addSql("    and");
//                sql.addSql("     cast(SCD_TO_DATE as DATE)");
                sql.addSql("    SCD_FR_DATE <= ?");
                sql.addSql("    and SCD_TO_DATE >= ?");
                sql.addDateValue(toDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("  )");
            }
            if (mod.equals(GSConstSchedule.DSP_MOD_MONTH)) {

                UDate fromDateStart = UDate.getInstance(from.getTimeMillis());
                fromDateStart.setZeroHhMmSs();
                UDate fromDateEnd = fromDateStart.cloneUDate();
                fromDateEnd.setMaxHhMmSs();

                UDate toDateStart = UDate.getInstance(to.getTimeMillis());
                toDateStart.setZeroHhMmSs();
                UDate toDateEnd = toDateStart.cloneUDate();
                toDateEnd.setMaxHhMmSs();

                sql.addSql(" and");
                sql.addSql("  (");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE >= ?");
                sql.addSql("     and SCD_FR_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_TO_DATE >= ?");
                sql.addSql("     and SCD_TO_DATE <= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(fromDateStart);
                sql.addDateValue(fromDateEnd);
                sql.addSql("   )");
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("     SCD_FR_DATE <= ?");
                sql.addSql("     and SCD_TO_DATE >= ?");
                sql.addDateValue(toDateStart);
                sql.addDateValue(toDateEnd);
                sql.addSql("   )");
                sql.addSql("  )");
            }

            if (mod.equals(GSConstSchedule.DSP_MOD_DAY)) {

                sql.addSql(" and");
                sql.addSql("   (");
                sql.addSql("     (");
                sql.addSql("      SCD_FR_DATE between ?");
                sql.addSql("      and ?");
                sql.addSql("     )");
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      SCD_TO_DATE between ?");
                sql.addSql("      and ?");
                sql.addSql("     )");

                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("     )");
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("     )");

                UDate fromPlus = UDate.getInstance(from.getTimeMillis());
                fromPlus.setMilliSecond(1);
                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addDateValue(fromPlus);
                sql.addDateValue(to);

                sql.addDateValue(from);
                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addDateValue(to);

                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("        SCD_FR_DATE between ?");
                sql.addSql("        and ?");
                sql.addSql("      and");
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("        SCD_TO_DATE between ?");
                sql.addSql("        and ?");
                sql.addSql("      and");
                sql.addSql("        SCD_DAILY = 1");
                sql.addSql("     )");

                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("      and");
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addSql("     or");
                sql.addSql("     (");
                sql.addSql("      ? >= SCD_FR_DATE");
                sql.addSql("      and");
                sql.addSql("      ? < SCD_TO_DATE");
                sql.addSql("      and");
                sql.addSql("      SCD_DAILY = 1");
                sql.addSql("     )");
                sql.addSql("   )");

                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addDateValue(fromPlus);
                sql.addDateValue(to);

                sql.addDateValue(from);
                sql.addDateValue(from);
                sql.addDateValue(to);
                sql.addDateValue(to);
            }
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_SID = CMN_USRM_INF.USR_SID ");
            sql.addSql(" order by ");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_DAILY DESC,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_SID");
            /** ******************************************* */

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            Map<Integer, ArrayList<Integer>> usrBlongMap
            = new HashMap<Integer, ArrayList<Integer>>();
            while (rs.next()) {
                //スケジュールユーザSID
                Integer scdUsrSid
                  = NullDefault.getInt(String.valueOf(rs.getInt("SCD_USR_SID")), 0);
                ArrayList<Integer> blongGpSidList = null;

                if (GSConstSchedule.USER_KBN_USER == usrKbn) {
                //ユーザスケジュールを取得する場合
                    if (scdUsrSid > 0) {
                        //取得したスケジュールのユーザの所属グループを取得する
                        if (usrBlongMap.get(scdUsrSid) != null) {
                            //既にグループSIDを取得済みのユーザ
                            blongGpSidList = usrBlongMap.get(scdUsrSid);
                        } else {
                            //グループSIDを未取得のユーザ
                            CmnBelongmDao bdao = new CmnBelongmDao(con);
                            blongGpSidList
                               = bdao.selectUserBelongGroupSid(scdUsrSid);
                            if (blongGpSidList != null) {
                                usrBlongMap.put(scdUsrSid, blongGpSidList);
                            }
                        }
                    }
                }

                ret.add(__getSchDataPlusNameFromRs(rs, blongGpSidList));
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
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     * @param searchMdl 検索用モデル
     * @param sessionUsrSid セッションユーザSID
     * @param offset オフセット有無(オフセットしない場合は取得スケジュール全てを取得)
     * @return ArrayList in SchDataModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SchDataModel> select(ScheduleListSearchModel searchMdl,
            int sessionUsrSid, boolean offset) throws SQLException {
        return select(searchMdl, sessionUsrSid, offset, true);
    }
    /**
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     * @param searchMdl 検索用モデル
     * @param sessionUsrSid セッションユーザSID
     * @param offset オフセット有無(オフセットしない場合は取得スケジュール全てを取得)
     * @param attendDsp タイトルの頭に出席状態をつけるかどうか
     * @return ArrayList in SchDataModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SchDataModel> select(ScheduleListSearchModel searchMdl,
            int sessionUsrSid, boolean offset, boolean attendDsp) throws SQLException {

        //ユーザの「役職 並び順」を取得
        SchDao schDao = new SchDao(getCon());
        int userPosSort = schDao.getUserPosSort(sessionUsrSid);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<SchDataModel> ret = new ArrayList<SchDataModel>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SCH.SCD_SID,");
            sql.addSql("   SCH.SCD_USR_SID,");
            sql.addSql("   SCH.SCD_GRP_SID,");
            sql.addSql("   SCH.SCD_USR_KBN,");
            sql.addSql("   SCH.SCD_FR_DATE,");
            sql.addSql("   SCH.SCD_TO_DATE,");
            sql.addSql("   SCH.SCD_DAILY,");
            sql.addSql("   SCH.SCD_BGCOLOR,");
            sql.addSql("   SCH.SCD_TITLE,");
            sql.addSql("   SCH.SCD_VALUE,");
            sql.addSql("   SCH.SCD_BIKO,");
            sql.addSql("   SCH.SCD_PUBLIC,");
            sql.addSql("   SCH.SCD_AUID,");
            sql.addSql("   SCH.SCD_ADATE,");
            sql.addSql("   SCH.SCD_EUID,");
            sql.addSql("   SCH.SCD_EDATE,");
            sql.addSql("   SCH.SCE_SID,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCH.SCD_ATTEND_KBN,");
            sql.addSql("   SCH.SCD_ATTEND_ANS,");
            sql.addSql("   SCH.SCD_ATTEND_AU_KBN,");
            sql.addSql("   SCH.SCD_EDIT,");
            sql.addSql("   case");
            sql.addSql("   when SCH.SCD_USR_KBN=0 then USRM.USI_SEI_KN || USRM.USI_MEI_KN");
            sql.addSql("   else GROUPM.GRP_NAME_KN");
            sql.addSql("   end USR_NAME");
            sql.addSql(" from");
            sql.addSql("   ((SCH_DATA SCH");
            sql.addSql("     left join CMN_USRM_INF USRM on SCH.SCD_USR_SID = USRM.USR_SID)");
            sql.addSql("     left join CMN_GROUPM GROUPM on SCH.SCD_USR_SID = GROUPM.GRP_SID)");
            sql.addSql(" where ");
            sql.addSql("   (");
            sql.addSql("     (");
            sql.addSql("       SCH.SCD_USR_KBN = ?");
            sql.addIntValue(GSConstSchedule.USER_KBN_GROUP);

            sql.addSql("     and ");
            sql.addSql("       SCH.SCD_USR_SID = ?");
            if (SchCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
                sql.addIntValue(-1);
            } else {
                //スケジュールアクセス可能なグループかを判定
                int dspGroupSid = SchCommonBiz.getDspGroupSid(
                                                searchMdl.getSchSltGroupSid());
                if (schDao.canAccessGroupSchedule(dspGroupSid, sessionUsrSid)) {
                    sql.addIntValue(dspGroupSid);
                } else {
                    sql.addIntValue(-1);
                }
            }

            //ユーザが許可対象として指定されているスケジュール特例アクセスのSIDを取得する
            List<Integer> ssaSidList = __getSpAccessSid(searchMdl.getSessionUserSid(), userPosSort);

            if (searchMdl.getSchSltUserSid() != sessionUsrSid) {
                sql.addSql("     and ");
                sql.addSql("       SCH.SCD_PUBLIC <> ?");
                sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
            }

            if (searchMdl.getSchPublic() != -1
             && searchMdl.getSchPublic() != GSConstSchedule.DSP_NOT_PUBLIC) {
                sql.addSql("     and ");
                sql.addSql("       SCH.SCD_PUBLIC = ?");
                sql.addIntValue(searchMdl.getSchPublic());

            } else {

                if (searchMdl.getGpBelongKbn() != GSConstSchedule.EDIT_CONF_GROUP
                 && searchMdl.getSchUsrSid() != sessionUsrSid) {
                    sql.addSql("     and ");
                    sql.addSql("       SCH.SCD_PUBLIC <> ?");
                    sql.addIntValue(GSConstSchedule.DSP_BELONG_GROUP);
                }
            }
            sql.addSql("     )");
            if (searchMdl.getSchSltUserSid() != -1) {
                //指定ユーザがアクセス可能ユーザの場合のみ
                if (schDao.canAccessUserSchedule(searchMdl.getSchSltUserSid(), sessionUsrSid)) {
                    sql.addSql("     or");
                    sql.addSql("       (");
                    sql.addSql("         SCH.SCD_USR_SID = ?");
                    sql.addIntValue(searchMdl.getSchSltUserSid());
                    sql.addSql("       and ");
                    sql.addSql("         SCH.SCD_USR_KBN = ?");
                    sql.addIntValue(GSConstSchedule.USER_KBN_USER);
                    if (searchMdl.getSchSltUserSid() != sessionUsrSid) {
                        if (searchMdl.getSchPublic() != -1) {
                            sql.addSql("       and ");
                            sql.addSql("         SCH.SCD_PUBLIC = ?");
                            sql.addIntValue(searchMdl.getSchPublic());
                        } else {
                            sql.addSql("       and ");
                            sql.addSql("         SCH.SCD_PUBLIC <> ?");
                            sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
                        }
                    }

                    sql.addSql("       )");

                    if (searchMdl.isSchSltUserBelongGrpFlg()) {
                        //所属グループを検索対象に含む場合
                        sql.addSql("     or");
                        sql.addSql("       (");
                        sql.addSql("         SCH.SCD_USR_KBN = ?");
                        sql.addIntValue(GSConstSchedule.USER_KBN_GROUP);
                        sql.addSql("       and ");
                        sql.addSql("         SCH.SCD_USR_SID in (");
                        sql.addSql("           select");
                        sql.addSql("             GRP_SID");
                        sql.addSql("           from");
                        sql.addSql("             CMN_BELONGM");
                        sql.addSql("           where ");
                        sql.addSql("             USR_SID=?");
                        sql.addSql("         )");
                        sql.addIntValue(searchMdl.getSchSltUserSid());

                        //アクセス不可グループを除外する
                        sql.addSql("       and ");
                        sql.addSql("         SCH.SCD_USR_SID not in (");
                        __setSpNotAccessSql(sql, ssaSidList,
                                                GSConstSchedule.USER_KBN_GROUP);
                        sql.addSql("         )");

                        if (searchMdl.getSchSltUserSid() != sessionUsrSid
                        && searchMdl.getSchPublic() == -1) {
                            sql.addSql("       and ");
                            sql.addSql("         SCH.SCD_PUBLIC = ?");
                            sql.addIntValue(GSConstSchedule.DSP_PUBLIC);
                        }
                        if (searchMdl.getSchPublic() != -1) {
                            sql.addSql("       and ");
                            sql.addSql("         SCH.SCD_PUBLIC = ?");
                            sql.addIntValue(searchMdl.getSchPublic());
                        }
                        sql.addSql("       )");

                        if (searchMdl.getSchSltUserSid() != sessionUsrSid
                        && searchMdl.getSchPublic() == -1) {
                            sql.addSql("     or");
                            sql.addSql("       (");
                            sql.addSql("         SCH.SCD_USR_KBN = ?");
                            sql.addIntValue(GSConstSchedule.USER_KBN_GROUP);
                            sql.addSql("       and ");
                            sql.addSql("         SCH.SCD_USR_SID not in (");
                            sql.addSql("           select");
                            sql.addSql("             GRP_SID");
                            sql.addSql("           from");
                            sql.addSql("             CMN_BELONGM");
                            sql.addSql("           where ");
                            sql.addSql("             USR_SID=?");
                            sql.addSql("         )");
                            sql.addIntValue(searchMdl.getSchSltUserSid());
                            sql.addSql("       and ");
                            sql.addSql("         SCH.SCD_USR_SID in (");
                            sql.addSql("           select");
                            sql.addSql("             GRP_SID");
                            sql.addSql("           from");
                            sql.addSql("             CMN_BELONGM");
                            sql.addSql("           where ");
                            sql.addSql("             USR_SID=?");
                            sql.addSql("         )");
                            sql.addIntValue(sessionUsrSid);
                            sql.addSql("       and ");
                            sql.addSql("         SCH.SCD_PUBLIC = ?");
                            sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);

                            //アクセス不可グループを除外する
                            sql.addSql("       and ");
                            sql.addSql("         SCH.SCD_USR_SID in (");
                            __setSpNotAccessSql(sql, ssaSidList,
                                                    GSConstSchedule.USER_KBN_GROUP);
                            sql.addSql("         )");

                            sql.addSql("   )");
                        }
                    }
                }

            } else {
                sql.addSql("     or");
                sql.addSql("       (");
                //閲覧不可のユーザを除外する
                sql.addSql("         (");
                sql.addSql("           SCH.SCD_USR_SID = ?");
                sql.addIntValue(searchMdl.getSessionUserSid());
                sql.addSql("         or");
                sql.addSql("           SCH.SCD_USR_SID not in (");
                __setSpNotAccessSql(sql, ssaSidList,
                                        GSConstSchedule.USER_KBN_USER);
                sql.addSql("           )");
                sql.addSql("         )");

                sql.addSql("       and ");
                sql.addSql("         SCH.SCD_USR_SID in (");

                if (searchMdl.isMyGrpFlg()) {
                    sql.addSql("           select");
                    sql.addSql("             CMN_MY_GROUP_MS.MGM_SID");
                    sql.addSql("           from");
                    sql.addSql("             CMN_MY_GROUP_MS");
                    sql.addSql("           where");
                    sql.addSql("             CMN_MY_GROUP_MS.MGP_SID = ?");
                    sql.addSql("           and");
                    sql.addIntValue(SchCommonBiz.getDspGroupSid(searchMdl
                            .getSchSltGroupSid()));
                    sql.addSql("           (");
                    sql.addSql("           CMN_MY_GROUP_MS.USR_SID = ?");
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("           or");
                    sql.addSql("           CMN_MY_GROUP_MS.MGP_SID in (");
                    sql.addSql("             select MGP_SID");
                    sql.addSql("             from CMN_MY_GROUP_SHARE");
                    sql.addSql("             where ");
                    sql.addSql("               MGS_USR_SID = ?");
                    sql.addSql("               or (MGS_USR_SID = -1 and MGS_GRP_SID in (");
                    sql.addSql("                    select GRP_SID from CMN_BELONGM");
                    sql.addSql("                    where USR_SID=?");
                    sql.addSql("                 )");
                    sql.addSql("               )");
                    sql.addSql("             )");
                    sql.addIntValue(sessionUsrSid);
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("           )");
                } else {
                    sql.addSql("           select");
                    sql.addSql("             CMN_BELONGM.USR_SID");
                    sql.addSql("           from");
                    sql.addSql("             CMN_BELONGM");
                    sql.addSql("           where");
                    sql.addSql("             CMN_BELONGM.GRP_SID = ?");
                    sql.addIntValue(Integer.parseInt(searchMdl.getSchSltGroupSid()));
                }

                sql.addSql("         )");
                sql.addSql("       and ");
                sql.addSql("         SCH.SCD_USR_KBN = ?");
                sql.addIntValue(GSConstSchedule.USER_KBN_USER);
                if (searchMdl.getSchPublic() != -1) {
                    sql.addSql("       and ");
                    sql.addSql("         SCH.SCD_PUBLIC = ?");
                    sql.addIntValue(searchMdl.getSchPublic());
                } else {
                    sql.addSql("       and ");
                    sql.addSql("         SCH.SCD_PUBLIC <> ?");
                    sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
                }
                sql.addSql("       )");
            }

            sql.addSql("   )");

            /** ********** 時間設定無し (h2用) *********** */
            UDate startDateFr = searchMdl.getSchStartDateFr().cloneUDate();
            startDateFr.setZeroHhMmSs();
            UDate startDateTo = searchMdl.getSchStartDateTo().cloneUDate();
            startDateTo.setMaxHhMmSs();
            UDate endDateFr = searchMdl.getSchEndDateFr().cloneUDate();
            endDateFr.setZeroHhMmSs();
            UDate endDateTo = searchMdl.getSchEndDateTo().cloneUDate();
            endDateTo.setMaxHhMmSs();
            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("     SCD_FR_DATE >= ?");
            sql.addSql("   and");
            sql.addSql("     SCD_FR_DATE <= ?");
            sql.addSql("   )");
            sql.addDateValue(startDateFr);
            sql.addDateValue(startDateTo);
            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("     SCD_TO_DATE >= ?");
            sql.addSql("   and");
            sql.addSql("     SCD_TO_DATE <= ?");
            sql.addSql("   )");
            sql.addDateValue(endDateFr);
            sql.addDateValue(endDateTo);

            // KEYワード
            __cleateKeyWord(searchMdl, sql);

            //タイトルカラー
            if (searchMdl.getBgcolor() != null && searchMdl.getBgcolor().length > 0) {
                String[] bgColorList = searchMdl.getBgcolor();

                sql.addSql(" and ");
                sql.addSql("   SCH.SCD_BGCOLOR in (");
                for (int i = 0; i < bgColorList.length; i++) {
                    if (i == 0) {
                        sql.addSql("     ?");
                    } else {
                        sql.addSql("     ,?");
                    }
                    sql.addIntValue(Integer.parseInt(bgColorList[i]));
                }
                sql.addSql("   )");
            }

            String orderStr = "";
            // オーダー
            if (searchMdl.getSchOrder() == GSConstSchedule.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (searchMdl.getSchOrder() == GSConstSchedule.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            sql.addSql(" order by ");
            switch (searchMdl.getSchSortKey()) {
            case GSConstSchedule.SORT_KEY_NAME:
                sql.addSql("   USR_NAME " + orderStr + ",");
                break;
            case GSConstSchedule.SORT_KEY_FRDATE:
                sql.addSql("   SCH.SCD_FR_DATE " + orderStr + ",");
                break;
            case GSConstSchedule.SORT_KEY_TODATE:
                sql.addSql("   SCH.SCD_TO_DATE " + orderStr + ",");
                break;
            case GSConstSchedule.SORT_KEY_TITLE:
                sql.addSql("   SCH.SCD_TITLE " + orderStr + ",");
                break;
            default:
                break;
            }

            String orderStr2 = "";
            // オーダー
            if (searchMdl.getSchOrder2() == GSConstSchedule.ORDER_KEY_ASC) {
                orderStr2 = "  asc";
            } else if (searchMdl.getSchOrder2() == GSConstSchedule.ORDER_KEY_DESC) {
                orderStr2 = "  desc";
            }
            switch (searchMdl.getSchSortKey2()) {
            case GSConstSchedule.SORT_KEY_NAME:
                sql.addSql("   USR_NAME " + orderStr2 + ",");
                sql.addSql("   SCD_SID");
                break;
            case GSConstSchedule.SORT_KEY_FRDATE:
                sql.addSql("   SCH.SCD_FR_DATE " + orderStr2 + ",");
                sql.addSql("   SCH.SCD_SID");
                break;
            case GSConstSchedule.SORT_KEY_TODATE:
                sql.addSql("   SCH.SCD_TO_DATE " + orderStr2 + ",");
                sql.addSql("   SCH.SCD_SID");
                break;
            case GSConstSchedule.SORT_KEY_TITLE:
                sql.addSql("   SCH.SCD_TITLE " + orderStr2 + ",");
                sql.addSql("   SCH.SCD_SID");
                break;
            default:
                break;
            }

//            pstmt = con.prepareStatement(sql.toSqlString(),
//                    ResultSet.TYPE_SCROLL_INSENSITIVE,
//                    ResultSet.CONCUR_READ_ONLY);
//
//            sql.setParameter(pstmt);
//            log__.info(sql.toLogString());
//            rs = pstmt.executeQuery();
//            if (searchMdl.getSchOffset() > 1 && offset) {
//                rs.absolute(searchMdl.getSchOffset() - 1);
//            }
//
//            //ユーザ所属グループ格納用MAP
//            Map<Integer, ArrayList<Integer>> usrBlongMap
//            = new HashMap<Integer, ArrayList<Integer>>();
//
//            if (offset) {
//                for (int i = 0; rs.next() && i < searchMdl.getSchLimit(); i++) {
//                    ArrayList<Integer> blongGpSidList = null;
//                    if (rs.getInt("SCD_USR_KBN") == GSConstSchedule.USER_KBN_USER) {
//                    //スケジュールユーザSID
//                    int scdUsrSid
//                      = NullDefault.getInt(String.valueOf(rs.getInt("SCD_USR_SID")), 0);
//                        //ユーザスケジュールを取得する場合
//                        if (scdUsrSid > 0) {
//                            //取得したスケジュールのユーザの所属グループを取得する
//                            if (usrBlongMap.get(scdUsrSid) != null) {
//                                //既にグループSIDを取得済みのユーザ
//                                blongGpSidList = usrBlongMap.get(scdUsrSid);
//                            } else {
//                                //グループSIDを未取得のユーザ
//                                CmnBelongmDao bdao = new CmnBelongmDao(con);
//                                blongGpSidList
//                                   = bdao.selectUserBelongGroupSid(scdUsrSid);
//                                if (blongGpSidList != null) {
//                                    usrBlongMap.put(scdUsrSid, blongGpSidList);
//                                }
//                            }
//                        }
//                    }
//                    ret.add(__getSchDataFromRs(rs, blongGpSidList));
//                }
//            } else {
//                while (rs.next()) {
//                    ArrayList<Integer> blongGpSidList = null;
//                    if (rs.getInt("SCD_USR_KBN") == GSConstSchedule.USER_KBN_USER) {
//                    //スケジュールユーザSID
//                    int scdUsrSid
//                      = NullDefault.getInt(String.valueOf(rs.getInt("SCD_USR_SID")), 0);
//                        //ユーザスケジュールを取得する場合
//                        if (scdUsrSid > 0) {
//                            //取得したスケジュールのユーザの所属グループを取得する
//                            if (usrBlongMap.get(scdUsrSid) != null) {
//                                //既にグループSIDを取得済みのユーザ
//                                blongGpSidList = usrBlongMap.get(scdUsrSid);
//                            } else {
//                                //グループSIDを未取得のユーザ
//                                CmnBelongmDao bdao = new CmnBelongmDao(con);
//                                blongGpSidList
//                                   = bdao.selectUserBelongGroupSid(scdUsrSid);
//                                if (blongGpSidList != null) {
//                                    usrBlongMap.put(scdUsrSid, blongGpSidList);
//                                }
//                            }
//                        }
//                    }
//                    ret.add(__getSchDataFromRs(rs, blongGpSidList));
//                }
//            }

            if (offset) {
                sql.setPagingValue(searchMdl.getSchOffset() - 1, searchMdl.getSchLimit());
            }

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            //ユーザ所属グループ格納用MAP
            Map<Integer, ArrayList<Integer>> usrBlongMap
                = new HashMap<Integer, ArrayList<Integer>>();

            while (rs.next()) {
                ArrayList<Integer> blongGpSidList = null;
                if (rs.getInt("SCD_USR_KBN") == GSConstSchedule.USER_KBN_USER) {
                    //スケジュールユーザSID
                    int scdUsrSid
                      = NullDefault.getInt(String.valueOf(rs.getInt("SCD_USR_SID")), 0);
                    //ユーザスケジュールを取得する場合
                    if (scdUsrSid > 0) {
                        //取得したスケジュールのユーザの所属グループを取得する
                        if (usrBlongMap.get(scdUsrSid) != null) {
                            //既にグループSIDを取得済みのユーザ
                            blongGpSidList = usrBlongMap.get(scdUsrSid);
                        } else {
                            //グループSIDを未取得のユーザ
                            CmnBelongmDao bdao = new CmnBelongmDao(con);
                            blongGpSidList
                               = bdao.selectUserBelongGroupSid(scdUsrSid);
                            if (blongGpSidList != null) {
                                usrBlongMap.put(scdUsrSid, blongGpSidList);
                            }
                        }
                    }
                }
                ret.add(__getSchDataFromRs(rs, blongGpSidList, attendDsp));
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
     * <br>
     * [機 能] SQL(キーワード入力時の検索条件)を作成 <br>
     * [解 説] <br>
     * [備 考]
     * @param bean CirSearchModel
     * @param sql SqlBuffer
     */
    private void __cleateKeyWord(ScheduleListSearchModel bean, SqlBuffer sql) {

        // キーワード入力時
        List<String> keywordList = bean.getSchKeyValue();
        if (keywordList != null && keywordList.size() > 0) {

            String keywordJoin = "  and";
            if (bean.getKeyWordkbn() == GSConstSchedule.KEY_WORD_KBN_OR) {
                keywordJoin = "   or";
            }

            // 、検索対象の「タイトル」がチェック済みの場合
            if (bean.isTargetTitle()) {
                sql.addSql("  and");
                if (bean.isTargetValue()) {
                    sql.addSql("    (");
                }
                sql.addSql("      (");
                for (int i = 0; i < keywordList.size(); i++) {
                    if (i > 0) {
                        sql.addSql(keywordJoin);
                    }
                    sql.addSql("       SCH.SCD_TITLE like '%"
                            + JDBCUtil.encFullStringLike(keywordList.get(i))
                            + "%' ESCAPE '" + JDBCUtil.def_esc + "'");
                }
                sql.addSql("      )");
            }

            if (bean.isTargetValue()) {
                if (bean.isTargetTitle()) {
                    sql.addSql("      or");
                } else {
                    sql.addSql("      and");
                }
                sql.addSql("      (");
                for (int i = 0; i < keywordList.size(); i++) {
                    if (i > 0) {
                        sql.addSql(keywordJoin);
                    }
                    sql.addSql("       SCH.SCD_VALUE like '%"
                            + JDBCUtil.encFullStringLike(keywordList.get(i))
                            + "%' ESCAPE '" + JDBCUtil.def_esc + "'");
                }
                sql.addSql("      )");

                if (bean.isTargetTitle()) {
                    sql.addSql("    )");
                }
            }
        }
    }

    /**
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しCSV用スケジュール情報を取得する
     * @param searchMdl 検索用モデル
     * @param sessionUserSid セッションユーザSID(実行者)
     * @param rl SchCsvRecordListenerIppanImpl
     * @param req リクエスト
     * @return int 明細件数
     * @throws SQLException SQL実行例外
     * @throws CSVException CSV出力時例外
     */
    public int createSchduleForCsv(ScheduleListSearchModel searchMdl,
            int sessionUserSid, SchCsvRecordListenerIppanImpl rl, HttpServletRequest req)
            throws SQLException, CSVException {

        //ユーザの「役職 並び順」を取得
        SchDao schDao = new SchDao(getCon());
        int userPosSort = schDao.getUserPosSort(sessionUserSid);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   (case");
            sql.addSql("      when SCH.SCD_USR_KBN = 1 then ''");
            sql.addSql("      else USR.USR_LGID");
            sql.addSql("    end) as USR_LGID,");
            sql.addSql("   (case");
            sql.addSql("      when SCH.SCD_USR_KBN = 0 then ''");
            sql.addSql("      else GROUPM.GRP_ID");
            sql.addSql("    end) as GRP_ID,");
            sql.addSql("   SCH.SCD_SID,");
            sql.addSql("   SCH.SCD_USR_SID,");
            sql.addSql("   SCH.SCD_GRP_SID,");
            sql.addSql("   SCH.SCD_USR_KBN,");
            sql.addSql("   SCH.SCD_FR_DATE,");
            sql.addSql("   SCH.SCD_TO_DATE,");
            sql.addSql("   SCH.SCD_DAILY,");
            sql.addSql("   SCH.SCD_BGCOLOR,");
            sql.addSql("   SCH.SCD_TITLE,");
            sql.addSql("   SCH.SCD_VALUE,");
            sql.addSql("   SCH.SCD_BIKO,");
            sql.addSql("   SCH.SCD_PUBLIC,");
            sql.addSql("   SCH.SCD_EDIT,");
            sql.addSql("   SCH.SCD_AUID,");
            sql.addSql("   SCH.SCD_ADATE,");
            sql.addSql("   SCH.SCD_EUID,");
            sql.addSql("   SCH.SCD_EDATE,");
            sql.addSql("   case");
            sql.addSql("   when SCH.SCD_USR_KBN=0 then USRM.USI_SEI || USRM.USI_MEI");
            sql.addSql("   else GROUPM.GRP_NAME");
            sql.addSql("   end USR_NAME,");
            sql.addSql("   AUSRM.USI_SEI || AUSRM.USI_MEI AUSR_NAME,");
            sql.addSql("   EUSRM.USI_SEI || EUSRM.USI_MEI EUSR_NAME");
            sql.addSql(" from");
            sql.addSql("   (((((SCH_DATA SCH");
            sql.addSql("     left join CMN_USRM_INF USRM on SCH.SCD_USR_SID = USRM.USR_SID)");
            sql.addSql("     left join CMN_USRM_INF AUSRM on SCH.SCD_AUID = AUSRM.USR_SID)");
            sql.addSql("     left join CMN_USRM_INF EUSRM on SCH.SCD_EUID = EUSRM.USR_SID)");
            sql.addSql("     left join CMN_GROUPM GROUPM on SCH.SCD_USR_SID = GROUPM.GRP_SID)");
            sql.addSql("     left join CMN_USRM USR on SCH.SCD_USR_SID = USR.USR_SID)");
            sql.addSql(" where ");
            sql.addSql("  (");
            sql.addSql("   (");
            sql.addSql("   SCH.SCD_USR_SID = ?");
            if (SchCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
                sql.addIntValue(-1);
            } else {
                //スケジュールアクセス可能なグループかを判定
                int dspGroupSid = SchCommonBiz.getDspGroupSid(
                                                searchMdl.getSchSltGroupSid());
                if (schDao.canAccessGroupSchedule(dspGroupSid, sessionUserSid)) {
                    sql.addIntValue(dspGroupSid);
                } else {
                    sql.addIntValue(-1);
                }
            }
            sql.addSql(" and ");
            sql.addSql("   SCH.SCD_USR_KBN = ?");
            sql.addIntValue(GSConstSchedule.USER_KBN_GROUP);

            //ユーザが許可対象として指定されているスケジュール特例アクセスのSIDを取得する
            List<Integer> ssaSidList = __getSpAccessSid(searchMdl.getSessionUserSid(), userPosSort);

            if (searchMdl.getSchSltUserSid() != sessionUserSid) {
                sql.addSql(" and ");
                sql.addSql("   SCH.SCD_PUBLIC <> ?");
                sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
            }
            if (searchMdl.getSchPublic() != -1
             && searchMdl.getSchPublic() != GSConstSchedule.DSP_NOT_PUBLIC) {
                sql.addSql(" and ");
                sql.addSql("   SCH.SCD_PUBLIC = ?");
                sql.addIntValue(searchMdl.getSchPublic());
            } else {
                if (searchMdl.getGpBelongKbn() != GSConstSchedule.EDIT_CONF_GROUP
                 && searchMdl.getSchUsrSid() != sessionUserSid) {
                    sql.addSql(" and ");
                    sql.addSql("   SCH.SCD_PUBLIC <> ?");
                    sql.addIntValue(GSConstSchedule.DSP_BELONG_GROUP);
                }
            }

            sql.addSql("   )");
            if (searchMdl.getSchSltUserSid() != -1) {
                sql.addSql("   or");
                sql.addSql("   (");
                sql.addSql("   SCH.SCD_USR_SID = ?");
                sql.addIntValue(searchMdl.getSchSltUserSid());
                sql.addSql(" and ");
                sql.addSql("   SCH.SCD_USR_KBN = ?");
                sql.addIntValue(GSConstSchedule.USER_KBN_USER);
                if (searchMdl.getSchSltUserSid() != sessionUserSid) {
                    if (searchMdl.getSchPublic() != -1) {
                        sql.addSql(" and ");
                        sql.addSql("   SCH.SCD_PUBLIC = ?");
                        sql.addIntValue(searchMdl.getSchPublic());
                    } else {
                        sql.addSql(" and ");
                        sql.addSql("   SCH.SCD_PUBLIC <> ?");
                        sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);

                    }
                }

                sql.addSql("   )");
            } else {
                sql.addSql("   or");
                sql.addSql("   (");

                //閲覧不可のユーザを除外する
                sql.addSql("     (");
                sql.addSql("       SCH.SCD_USR_SID = ?");
                sql.addIntValue(searchMdl.getSessionUserSid());
                sql.addSql("     or");
                sql.addSql("       SCH.SCD_USR_SID not in (");
                __setSpNotAccessSql(sql, ssaSidList,
                                        GSConstSchedule.USER_KBN_USER);
                sql.addSql("       )");
                sql.addSql("     )");

                //閲覧不可のユーザを除外する

                sql.addSql("   and ");
                sql.addSql("   SCH.SCD_USR_SID in (");
                if (searchMdl.isMyGrpFlg()) {
                    sql.addSql("   select");
                    sql.addSql("     CMN_MY_GROUP_MS.MGM_SID");
                    sql.addSql("   from");
                    sql.addSql("     CMN_MY_GROUP_MS");
                    sql.addSql("   where");
                    sql.addSql("     CMN_MY_GROUP_MS.MGP_SID = ?");
                    sql.addSql("   and");
                    sql.addIntValue(SchCommonBiz.getDspGroupSid(searchMdl
                            .getSchSltGroupSid()));
                    sql.addSql("           (");
                    sql.addSql("           CMN_MY_GROUP_MS.USR_SID = ?");
                    sql.addIntValue(sessionUserSid);
                    sql.addSql("           or");
                    sql.addSql("           CMN_MY_GROUP_MS.MGP_SID in (");
                    sql.addSql("             select MGP_SID");
                    sql.addSql("             from CMN_MY_GROUP_SHARE");
                    sql.addSql("             where ");
                    sql.addSql("               MGS_USR_SID = ?");
                    sql.addSql("               or (MGS_USR_SID = -1 and MGS_GRP_SID in (");
                    sql.addSql("                    select GRP_SID from CMN_BELONGM");
                    sql.addSql("                    where USR_SID=?");
                    sql.addSql("                 )");
                    sql.addSql("               )");
                    sql.addSql("             )");
                    sql.addIntValue(sessionUserSid);
                    sql.addIntValue(sessionUserSid);
                    sql.addSql("           )");
                } else {

                    int sltGrpSid = NullDefault.getInt(searchMdl.getSchSltGroupSid(), -1);
                    if (sltGrpSid >= 0) {
                        sql.addSql("   select");
                        sql.addSql("     CMN_BELONGM.USR_SID");
                        sql.addSql("   from");
                        sql.addSql("     CMN_BELONGM");
                        sql.addSql("   where");
                        sql.addSql("     CMN_BELONGM.GRP_SID = ?");
                        sql.addIntValue(sltGrpSid);
                    }
                }

                sql.addSql("   )");
                sql.addSql(" and ");
                sql.addSql("   SCH.SCD_USR_KBN = ?");
                sql.addIntValue(GSConstSchedule.USER_KBN_USER);
                if (searchMdl.getSchPublic() != -1) {
                    sql.addSql(" and ");
                    sql.addSql("   SCH.SCD_PUBLIC = ?");
                    sql.addIntValue(searchMdl.getSchPublic());
                } else {
                    sql.addSql(" and ");
                    sql.addSql("   SCH.SCD_PUBLIC <> ?");
                    sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
                }
                sql.addSql("   )");
            }
            sql.addSql("   )");

            /** ********** 時間設定無し (h2用) *********** */
            UDate fromDateStart = searchMdl.getSchStartDateFr().cloneUDate();
            fromDateStart.setZeroHhMmSs();
            UDate fromDateEnd = searchMdl.getSchEndDateFr().cloneUDate();
            fromDateEnd.setMaxHhMmSs();

            UDate toDateStart = searchMdl.getSchStartDateTo().cloneUDate();
            toDateStart.setZeroHhMmSs();
            UDate toDateEnd = searchMdl.getSchEndDateTo().cloneUDate();
            toDateEnd.setMaxHhMmSs();
            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("     SCD_FR_DATE >= ?");
            sql.addSql("     and SCD_FR_DATE <= ?");
            sql.addDateValue(fromDateStart);
            sql.addDateValue(toDateEnd);
            sql.addSql("   )");
            sql.addSql("   and");
            sql.addSql("   (");
            sql.addSql("     SCD_TO_DATE >= ?");
            sql.addSql("     and SCD_TO_DATE <= ?");
            sql.addDateValue(fromDateStart);
            sql.addDateValue(toDateEnd);
            sql.addSql("   )");

            // KEYワード
            __cleateKeyWord(searchMdl, sql);

            //タイトルカラー
            if (searchMdl.getBgcolor() != null && searchMdl.getBgcolor().length > 0) {
                String[] bgColorList = searchMdl.getBgcolor();
                for (int i = 0; i < bgColorList.length; i++) {
                    if (i == 0) {
                        sql.addSql(" and ");
                        sql.addSql(" ( ");
                    } else {
                        sql.addSql(" or ");
                    }
                    sql.addSql(" SCH.SCD_BGCOLOR = ?");
                    sql.addIntValue(Integer.parseInt(bgColorList[i]));
                }
                sql.addSql(" ) ");
            }

            String orderStr = "";
            // オーダー
            if (searchMdl.getSchOrder() == GSConstSchedule.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (searchMdl.getSchOrder() == GSConstSchedule.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            sql.addSql(" order by ");
            switch (searchMdl.getSchSortKey()) {
            case GSConstSchedule.SORT_KEY_NAME:
                sql.addSql("   USR_NAME " + orderStr + ",");
                sql.addSql("   SCD_SID");
                break;
            case GSConstSchedule.SORT_KEY_FRDATE:
                sql.addSql("   SCH.SCD_FR_DATE " + orderStr + ",");
                sql.addSql("   SCH.SCD_SID");
                break;
            case GSConstSchedule.SORT_KEY_TODATE:
                sql.addSql("   SCH.SCD_TO_DATE " + orderStr + ",");
                sql.addSql("   SCH.SCD_SID");
                break;
            case GSConstSchedule.SORT_KEY_TITLE:
                sql.addSql("   SCH.SCD_TITLE " + orderStr + ",");
                sql.addSql("   SCH.SCD_SID");
                break;
            default:
                break;
            }

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ret++;
                ScheduleCsvModel model = __getScheduleCsvFromRs(rs);
                model.setMyGrpFlg(searchMdl.isMyGrpFlg());
                setSchCsvRecordFromSchDataModel(model, sessionUserSid, rl, req);
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
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ区分
     * @param pub 公開区分 ※-1を指定すると条件から除外されます
     * @param from 日付from
     * @param to 日付to
     * @return int データ件数
     * @throws SQLException SQL実行例外
     */
    public int getCount(int usrSid, int usrKbn, int pub, UDate from, UDate to)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(*) CNT");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_USR_SID = ?");
            sql.addIntValue(usrSid);
            sql.addSql(" and ");
            sql.addSql("   SCD_USR_KBN = ?");
            sql.addIntValue(usrKbn);
            if (pub != -1) {
                sql.addSql(" and ");
                sql.addSql("   SCD_PUBLIC = ?");
                sql.addIntValue(pub);
            }

            /** ********** 時間設定無し (derby用) *********** */
            sql.addSql(" and");
            sql.addSql("  (");
            sql.addSql("   (");
            sql.addSql("     bigint(substr(varchar(SCD_FR_DATE), 1, 4)");
            sql.addSql("     || substr(varchar(SCD_FR_DATE), 6, 2)");
            sql.addSql("     || substr(varchar(SCD_FR_DATE), 9 ,2))");
            sql.addSql("     between ?");
            sql.addSql("     and ?");
            sql.addSql("   )");
            sql.addSql("   or");
            sql.addSql("   (");
            sql.addSql("     bigint(substr(varchar(SCD_TO_DATE), 1, 4)");
            sql.addSql("     || substr(varchar(SCD_TO_DATE), 6, 2)");
            sql.addSql("     || substr(varchar(SCD_TO_DATE), 9 ,2))");
            sql.addSql("     between ?");
            sql.addSql("     and ?");
            sql.addSql("   )");
            sql.addSql("   or");
            sql.addSql("   (");
            sql.addSql("    ? between");
            sql.addSql("     bigint(substr(varchar(SCD_FR_DATE), 1, 4)");
            sql.addSql("     || substr(varchar(SCD_FR_DATE), 6, 2)");
            sql.addSql("     || substr(varchar(SCD_FR_DATE), 9 ,2))");
            sql.addSql("    and");
            sql.addSql("     bigint(substr(varchar(SCD_TO_DATE), 1, 4)");
            sql.addSql("     || substr(varchar(SCD_TO_DATE), 6, 2)");
            sql.addSql("     || substr(varchar(SCD_TO_DATE), 9 ,2))");
            sql.addSql("   )");
            sql.addSql("   or");
            sql.addSql("   (");
            sql.addSql("    ? between");
            sql.addSql("     bigint(substr(varchar(SCD_FR_DATE), 1, 4)");
            sql.addSql("     || substr(varchar(SCD_FR_DATE), 6, 2)");
            sql.addSql("     || substr(varchar(SCD_FR_DATE), 9 ,2))");
            sql.addSql("    and");
            sql.addSql("     bigint(substr(varchar(SCD_TO_DATE), 1, 4)");
            sql.addSql("     || substr(varchar(SCD_TO_DATE), 6, 2)");
            sql.addSql("     || substr(varchar(SCD_TO_DATE), 9 ,2))");
            sql.addSql("   )");
            sql.addSql("  )");

            String fromDateStr = from.getDateString();
            long fromDateLong = new Long(fromDateStr);
            String toDateStr = to.getDateString();
            long toDateLong = new Long(toDateStr);

            sql.addLongValue(fromDateLong);
            sql.addLongValue(toDateLong);
            sql.addLongValue(fromDateLong);
            sql.addLongValue(toDateLong);
            sql.addLongValue(fromDateLong);
            sql.addLongValue(toDateLong);

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
     * <p>
     * ユーザSID、ユーザ区分、公開区分、日付範囲を指定しスケジュール情報を取得する
     * @param searchMdl 検索条件
     * @param sessionUsrSid セッションユーザSID
     * @return int データ件数
     * @throws SQLException SQL実行例外
     */
    public int getCount(ScheduleListSearchModel searchMdl, int sessionUsrSid)
            throws SQLException {

        //ユーザの「役職 並び順」を取得
        SchDao schDao = new SchDao(getCon());
        int userPosSort = schDao.getUserPosSort(sessionUsrSid);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();
        SqlBuffer sql = null;
        try {
            // SQL文
            sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(SCD_SID) as CNT");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA as SCH");
            sql.addSql(" where ");
            sql.addSql("   (");

            sql.addSql("     (");
            sql.addSql("       SCH.SCD_USR_KBN = ?");
            sql.addIntValue(GSConstSchedule.USER_KBN_GROUP);

            //ユーザが許可対象として指定されているスケジュール特例アクセスのSIDを取得する
            List<Integer> ssaSidList = __getSpAccessSid(searchMdl.getSessionUserSid(), userPosSort);

            sql.addSql("     and ");
            sql.addSql("       SCH.SCD_USR_SID = ?");
            if (SchCommonBiz.isMyGroupSid(searchMdl.getSchSltGroupSid())) {
                sql.addIntValue(-1);
            } else {
                //スケジュールアクセス可能なグループかを判定
                int dspGroupSid = SchCommonBiz.getDspGroupSid(
                                                    searchMdl.getSchSltGroupSid());
                if (schDao.canAccessGroupSchedule(dspGroupSid, sessionUsrSid)) {
                    sql.addIntValue(dspGroupSid);
                } else {
                    sql.addIntValue(-1);
                }
            }

            if (searchMdl.getSchSltUserSid() != sessionUsrSid) {
                sql.addSql("     and ");
                sql.addSql("       SCH.SCD_PUBLIC <> ?");
                sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
            }
            if (searchMdl.getSchPublic() != -1
             && searchMdl.getSchPublic() != GSConstSchedule.DSP_NOT_PUBLIC) {
                sql.addSql(" and ");
                sql.addSql("   SCH.SCD_PUBLIC = ?");
                sql.addIntValue(searchMdl.getSchPublic());
            } else {
                if (searchMdl.getGpBelongKbn() != GSConstSchedule.EDIT_CONF_GROUP
                 && searchMdl.getSchUsrSid() != sessionUsrSid) {
                    sql.addSql("     and ");
                    sql.addSql("       SCH.SCD_PUBLIC <> ?");
                    sql.addIntValue(GSConstSchedule.DSP_BELONG_GROUP);
                }
            }
            sql.addSql("     )");

            if (searchMdl.getSchSltUserSid() != -1) {

                //指定ユーザがアクセス可能ユーザの場合のみ
                if (schDao.canAccessUserSchedule(searchMdl.getSchSltUserSid(), sessionUsrSid)) {

                    sql.addSql("     or");
                    sql.addSql("     (");
                    sql.addSql("       SCH.SCD_USR_SID = ?");
                    sql.addIntValue(searchMdl.getSchSltUserSid());
                    sql.addSql("     and ");
                    sql.addSql("       SCH.SCD_USR_KBN = ?");
                    sql.addIntValue(GSConstSchedule.USER_KBN_USER);
                    if (searchMdl.getSchSltUserSid() != sessionUsrSid) {
                        if (searchMdl.getSchPublic() != -1) {
                            sql.addSql("     and ");
                            sql.addSql("       SCH.SCD_PUBLIC = ?");
                            sql.addIntValue(searchMdl.getSchPublic());
                        } else {
                            sql.addSql("     and ");
                            sql.addSql("       SCH.SCD_PUBLIC <> ?");
                            sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
                        }
                    }
                    sql.addSql("     )");
                    if (searchMdl.isSchSltUserBelongGrpFlg()) {
                        //所属グループを検索対象に含む場合
                        sql.addSql("     or");
                        sql.addSql("     (");
                        sql.addSql("       SCH.SCD_USR_KBN = ?");
                        sql.addIntValue(GSConstSchedule.USER_KBN_GROUP);
                        sql.addSql("     and ");
                        sql.addSql("       SCH.SCD_USR_SID in (");
                        sql.addSql("         select");
                        sql.addSql("           GRP_SID");
                        sql.addSql("         from");
                        sql.addSql("           CMN_BELONGM");
                        sql.addSql("         where ");
                        sql.addSql("           USR_SID=?");
                        sql.addIntValue(searchMdl.getSchSltUserSid());
                        sql.addSql("       )");

                        //アクセス不可グループを除外する
                        sql.addSql("     and ");
                        sql.addSql("       SCH.SCD_USR_SID not in (");
                        __setSpNotAccessSql(sql, ssaSidList,
                                                GSConstSchedule.USER_KBN_GROUP);
                        sql.addSql("       )");

                        if (searchMdl.getSchSltUserSid() != sessionUsrSid
                            && searchMdl.getSchPublic() == -1) {
                        sql.addSql("     and ");
                        sql.addSql("       SCH.SCD_PUBLIC = ?");
                        sql.addIntValue(GSConstSchedule.DSP_PUBLIC);

                        }
                        if (searchMdl.getSchPublic() != -1) {
                            sql.addSql("     and ");
                            sql.addSql("       SCH.SCD_PUBLIC = ?");
                            sql.addIntValue(searchMdl.getSchPublic());
                        }
                        sql.addSql("     )");
                        if (searchMdl.getSchSltUserSid() != sessionUsrSid
                                && searchMdl.getSchPublic() == -1) {
                            sql.addSql("     or");
                            sql.addSql("     (");
                            sql.addSql("       SCH.SCD_USR_KBN = ?");
                            sql.addIntValue(GSConstSchedule.USER_KBN_GROUP);
                            sql.addSql("     and ");
                            sql.addSql("       SCH.SCD_USR_SID in (");
                            sql.addSql("         select");
                            sql.addSql("           GRP_SID");
                            sql.addSql("         from");
                            sql.addSql("           CMN_BELONGM");
                            sql.addSql("         where ");
                            sql.addSql("           USR_SID=?");
                            sql.addSql("       )");
                            sql.addIntValue(searchMdl.getSchSltUserSid());
                            sql.addSql("     and ");
                            sql.addSql("       SCH.SCD_USR_SID in (");
                            sql.addSql("         select");
                            sql.addSql("           GRP_SID");
                            sql.addSql("         from");
                            sql.addSql("           CMN_BELONGM");
                            sql.addSql("         where ");
                            sql.addSql("           USR_SID=?");
                            sql.addIntValue(sessionUsrSid);
                            sql.addSql("       )");
                            sql.addSql("     and ");
                            sql.addSql("       SCH.SCD_PUBLIC = ?");
                            sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
                            sql.addSql("     )");

                            //アクセス不可グループを除外する
                            sql.addSql("     and ");
                            sql.addSql("       SCH.SCD_USR_SID not in (");
                            __setSpNotAccessSql(sql, ssaSidList,
                                                    GSConstSchedule.USER_KBN_GROUP);
                            sql.addSql("       )");
                        }
                    }
                }
            } else {
                sql.addSql("     or");
                sql.addSql("     (");

                //閲覧不可のユーザを除外する
                sql.addSql("       (");
                sql.addSql("         SCH.SCD_USR_SID = ?");
                sql.addIntValue(searchMdl.getSessionUserSid());
                sql.addSql("       or");
                sql.addSql("         SCH.SCD_USR_SID not in (");
                __setSpNotAccessSql(sql, ssaSidList,
                                        GSConstSchedule.USER_KBN_USER);
                sql.addSql("         )");
                sql.addSql("       )");


                sql.addSql("     and ");
                sql.addSql("       SCH.SCD_USR_SID in (");

                if (searchMdl.isMyGrpFlg()) {
                    sql.addSql("         select");
                    sql.addSql("           CMN_MY_GROUP_MS.MGM_SID");
                    sql.addSql("         from");
                    sql.addSql("           CMN_MY_GROUP_MS");
                    sql.addSql("         where");
                    sql.addSql("           CMN_MY_GROUP_MS.MGP_SID = ?");
                    sql.addSql("         and");
                    sql.addIntValue(SchCommonBiz.getDspGroupSid(searchMdl
                            .getSchSltGroupSid()));
                    sql.addSql("           (");
                    sql.addSql("           CMN_MY_GROUP_MS.USR_SID = ?");
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("           or");
                    sql.addSql("           CMN_MY_GROUP_MS.MGP_SID in (");
                    sql.addSql("             select MGP_SID");
                    sql.addSql("             from CMN_MY_GROUP_SHARE");
                    sql.addSql("             where ");
                    sql.addSql("               MGS_USR_SID = ?");
                    sql.addSql("               or (MGS_USR_SID = -1 and MGS_GRP_SID in (");
                    sql.addSql("                    select GRP_SID from CMN_BELONGM");
                    sql.addSql("                    where USR_SID=?");
                    sql.addSql("                 )");
                    sql.addSql("               )");
                    sql.addSql("             )");
                    sql.addIntValue(sessionUsrSid);
                    sql.addIntValue(sessionUsrSid);
                    sql.addSql("           )");
                } else {
                    sql.addSql("         select");
                    sql.addSql("           CMN_BELONGM.USR_SID");
                    sql.addSql("         from");
                    sql.addSql("           CMN_BELONGM");
                    sql.addSql("         where");
                    sql.addSql("           CMN_BELONGM.GRP_SID = ?");
                    sql.addIntValue(Integer.parseInt(searchMdl.getSchSltGroupSid()));
                }

                sql.addSql("       )");
                sql.addSql("     and ");
                sql.addSql("       SCH.SCD_USR_KBN = ?");
                sql.addIntValue(GSConstSchedule.USER_KBN_USER);
                if (searchMdl.getSchPublic() != -1) {
                    sql.addSql("     and ");
                    sql.addSql("       SCH.SCD_PUBLIC = ?");
                    sql.addIntValue(searchMdl.getSchPublic());
                } else {
                    sql.addSql("     and ");
                    sql.addSql("       SCH.SCD_PUBLIC <> ?");
                    sql.addIntValue(GSConstSchedule.DSP_NOT_PUBLIC);
                }
                sql.addSql("     )");
            }

            sql.addSql("   )");
            /** ********** 時間設定無し (h2用) *********** */
            UDate startDateFr = searchMdl.getSchStartDateFr().cloneUDate();
            startDateFr.setZeroHhMmSs();
            UDate startDateTo = searchMdl.getSchStartDateTo().cloneUDate();
            startDateTo.setMaxHhMmSs();
            UDate endDateFr = searchMdl.getSchEndDateFr().cloneUDate();
            endDateFr.setZeroHhMmSs();
            UDate endDateTo = searchMdl.getSchEndDateTo().cloneUDate();
            endDateTo.setMaxHhMmSs();
            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("     SCD_FR_DATE >= ?");
            sql.addSql("   and");
            sql.addSql("     SCD_FR_DATE <= ?");
            sql.addSql("   )");
            sql.addDateValue(startDateFr);
            sql.addDateValue(startDateTo);
            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("     SCD_TO_DATE >= ?");
            sql.addSql("   and");
            sql.addSql("     SCD_TO_DATE <= ?");
            sql.addSql("   )");
            sql.addDateValue(endDateFr);
            sql.addDateValue(endDateTo);

            // KEYワード
            __cleateKeyWord(searchMdl, sql);

            //タイトルカラーコメント
            if (searchMdl.getBgcolor() != null && searchMdl.getBgcolor().length > 0) {
                String[] bgColorList = searchMdl.getBgcolor();

                sql.addSql(" and ");
                sql.addSql("   SCH.SCD_BGCOLOR in (");
                for (int i = 0; i < bgColorList.length; i++) {
                    if (i == 0) {
                        sql.addSql("     ?");
                    } else {
                        sql.addSql("     ,?");
                    }
                    sql.addIntValue(Integer.parseInt(bgColorList[i]));
                }
                sql.addSql("   )");
            }

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = rs.getInt("CNT");
            }
        } catch (SQLException e) {
            log__.debug("SQL=>" + sql.toLogString());
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <br>[機  能] スケジュール特例アクセスSQLを設定する
     * <br>[解  説]
     * <br>[備  考] アクセスが許可されていない場合のSQLを設定
     * @param sql SqlBuffer
     * @param ssaSidList スケジュール特例アクセスSID
     * @param scdUsrKbn スケジュールユーザ区分
     */
    private void __setSpNotAccessSql(SqlBuffer sql, List<Integer> ssaSidList,
                                                int scdUsrKbn) {

        if (scdUsrKbn == GSConstSchedule.USER_KBN_GROUP) {
            sql.addSql("           select");
            sql.addSql("              SCH_SPACCESS_TARGET.SST_TSID as GRP_SID");
            sql.addSql("           from");
            sql.addSql("             SCH_SPACCESS_TARGET");
            sql.addSql("           where");
            sql.addSql("             SCH_SPACCESS_TARGET.SST_TYPE = ?");
            sql.addIntValue(GSConstSchedule.SST_TYPE_GROUP);
        } else {
            sql.addSql("           select");
            sql.addSql("              SCH_SPACCESS_TARGET.SST_TSID as USR_SID");
            sql.addSql("           from");
            sql.addSql("             SCH_SPACCESS_TARGET");
            sql.addSql("           where");
            sql.addSql("             SCH_SPACCESS_TARGET.SST_TYPE = ?");
            sql.addIntValue(GSConstSchedule.SST_TYPE_USER);
        }

        if (ssaSidList != null && !ssaSidList.isEmpty()) {
            sql.addSql("           and");
            sql.addSql("             SCH_SPACCESS_TARGET.SSA_SID not in (");
            for (int idx = 0; idx < ssaSidList.size(); idx++) {
                if (idx > 0) {
                    sql.addSql("               ,?");
                } else {
                    sql.addSql("               ?");
                }
                sql.addIntValue(ssaSidList.get(idx));
            }
            sql.addSql("             )");

            //アクセス許可されているグループ・ユーザを除外する
            sql.addSql("           and");
            sql.addSql("              SCH_SPACCESS_TARGET.SST_TSID not in (");
            if (scdUsrKbn == GSConstSchedule.USER_KBN_GROUP) {
                sql.addSql("                select");
                sql.addSql("                   SCH_SPACCESS_TARGET.SST_TSID as GRP_SID");
                sql.addSql("                from");
                sql.addSql("                  SCH_SPACCESS_TARGET");
                sql.addSql("                where");
                sql.addSql("                  SCH_SPACCESS_TARGET.SST_TYPE = ?");
                sql.addIntValue(GSConstSchedule.SST_TYPE_GROUP);
            } else {
                sql.addSql("                select");
                sql.addSql("                   SCH_SPACCESS_TARGET.SST_TSID as USR_SID");
                sql.addSql("                from");
                sql.addSql("                  SCH_SPACCESS_TARGET");
                sql.addSql("                where");
                sql.addSql("                  SCH_SPACCESS_TARGET.SST_TYPE = ?");
                sql.addIntValue(GSConstSchedule.SST_TYPE_USER);
            }

            sql.addSql("                and");
            sql.addSql("                  SCH_SPACCESS_TARGET.SSA_SID in (");
            for (int idx = 0; idx < ssaSidList.size(); idx++) {
                if (idx > 0) {
                    sql.addSql("                    ,?");
                } else {
                    sql.addSql("                    ?");
                }
                sql.addIntValue(ssaSidList.get(idx));
            }
            sql.addSql("                  )");

            sql.addSql("             )");

            //アクセス許可されているグループに所属するユーザを除外する
            if (scdUsrKbn == GSConstSchedule.USER_KBN_USER
            && ssaSidList != null && !ssaSidList.isEmpty()) {
                sql.addSql("           and");
                sql.addSql("             SCH_SPACCESS_TARGET.SST_TSID not in (");
                sql.addSql("               select");
                sql.addSql("                 CMN_BELONGM.USR_SID as USR_SID");
                sql.addSql("               from");
                sql.addSql("                 CMN_BELONGM,");
                sql.addSql("                 SCH_SPACCESS_TARGET");
                sql.addSql("               where");
                sql.addSql("                 SCH_SPACCESS_TARGET.SST_TYPE = ?");
                sql.addSql("               and");
                sql.addSql("                 SCH_SPACCESS_TARGET.SST_TSID = CMN_BELONGM.GRP_SID");
                sql.addIntValue(GSConstSchedule.SST_TYPE_GROUP);

                sql.addSql("               and");
                sql.addSql("                 SCH_SPACCESS_TARGET.SSA_SID in (");
                for (int idx = 0; idx < ssaSidList.size(); idx++) {
                    if (idx > 0) {
                        sql.addSql("                   ,?");
                    } else {
                        sql.addSql("                   ?");
                    }
                    sql.addIntValue(ssaSidList.get(idx));
                }
                sql.addSql("                 )");

                sql.addSql("             )");
            }

        }

        //スケジュールユーザ区分 = ユーザの場合、アクセス不可グループに所属するユーザも制限対象とする
        if (scdUsrKbn == GSConstSchedule.USER_KBN_USER) {
            sql.addSql("         union all");
            sql.addSql("           select");
            sql.addSql("              CMN_BELONGM.USR_SID as USR_SID");
            sql.addSql("           from");
            sql.addSql("             CMN_BELONGM,");
            sql.addSql("             SCH_SPACCESS_TARGET");
            sql.addSql("           where");
            sql.addSql("             SCH_SPACCESS_TARGET.SST_TYPE = ?");
            sql.addSql("           and");
            sql.addSql("             SCH_SPACCESS_TARGET.SST_TSID = CMN_BELONGM.GRP_SID");
            sql.addIntValue(GSConstSchedule.SST_TYPE_GROUP);

            if (ssaSidList != null && !ssaSidList.isEmpty()) {
                sql.addSql("           and");
                sql.addSql("             SCH_SPACCESS_TARGET.SSA_SID not in (");
                for (int idx = 0; idx < ssaSidList.size(); idx++) {
                    if (idx > 0) {
                        sql.addSql("               ,?");
                    } else {
                        sql.addSql("               ?");
                    }
                    sql.addIntValue(ssaSidList.get(idx));
                }
                sql.addSql("             )");

                //アクセスを許可されているグループを閲覧不可条件から除外する
                sql.addSql("           and");
                sql.addSql("              SCH_SPACCESS_TARGET.SST_TSID not in (");
                sql.addSql("                select");
                sql.addSql("                   SCH_SPACCESS_TARGET.SST_TSID as GRP_SID");
                sql.addSql("                from");
                sql.addSql("                  SCH_SPACCESS_TARGET");
                sql.addSql("                where");
                sql.addSql("                  SCH_SPACCESS_TARGET.SST_TYPE = ?");
                sql.addIntValue(GSConstSchedule.SST_TYPE_GROUP);

                sql.addSql("                and");
                sql.addSql("                  SCH_SPACCESS_TARGET.SSA_SID in (");
                for (int idx = 0; idx < ssaSidList.size(); idx++) {
                    if (idx > 0) {
                        sql.addSql("                    ,?");
                    } else {
                        sql.addSql("                    ?");
                    }
                    sql.addIntValue(ssaSidList.get(idx));
                }
                sql.addSql("                  )");

                sql.addSql("             )");

                //アクセス許可されているユーザを閲覧不可条件から除外する
                sql.addSql("           and");
                sql.addSql("              CMN_BELONGM.USR_SID not in (");
                sql.addSql("                select");
                sql.addSql("                   SCH_SPACCESS_TARGET.SST_TSID as USR_SID");
                sql.addSql("                from");
                sql.addSql("                  SCH_SPACCESS_TARGET");
                sql.addSql("                where");
                sql.addSql("                  SCH_SPACCESS_TARGET.SST_TYPE = ?");
                sql.addIntValue(GSConstSchedule.SST_TYPE_USER);

                sql.addSql("                and");
                sql.addSql("                  SCH_SPACCESS_TARGET.SSA_SID in (");
                for (int idx = 0; idx < ssaSidList.size(); idx++) {
                    if (idx > 0) {
                        sql.addSql("                    ,?");
                    } else {
                        sql.addSql("                    ?");
                    }
                    sql.addIntValue(ssaSidList.get(idx));
                }
                sql.addSql("                  )");

                sql.addSql("             )");

                //アクセス許可されているグループに所属するユーザを除外する
                sql.addSql("           and");
                sql.addSql("             CMN_BELONGM.USR_SID not in (");
                sql.addSql("               select");
                sql.addSql("                 BELONG_ACCESS.USR_SID as USR_SID");
                sql.addSql("               from");
                sql.addSql("                 CMN_BELONGM BELONG_ACCESS,");
                sql.addSql("                 SCH_SPACCESS_TARGET TARGET_ACCESS");
                sql.addSql("               where");
                sql.addSql("                 TARGET_ACCESS.SST_TYPE = ?");
                sql.addSql("               and");
                sql.addSql("                 TARGET_ACCESS.SST_TSID = BELONG_ACCESS.GRP_SID");
                sql.addIntValue(GSConstSchedule.SST_TYPE_GROUP);

                sql.addSql("               and");
                sql.addSql("                 TARGET_ACCESS.SSA_SID in (");
                for (int idx = 0; idx < ssaSidList.size(); idx++) {
                    if (idx > 0) {
                        sql.addSql("                   ,?");
                    } else {
                        sql.addSql("                   ?");
                    }
                    sql.addIntValue(ssaSidList.get(idx));
                }
                sql.addSql("                 )");

                sql.addSql("             )");

            }
        }
    }

    /**
     * <br>[機  能] 指定したユーザが許可対象として指定されているスケジュール特例アクセスのSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param sessionUserSid セッションユーザSID
     * @param userPosSort セッションユーザの「役職 並び順」
     * @return スケジュール特例アクセスSID
     * @throws SQLException SQL実行時例外
     */
    private List<Integer> __getSpAccessSid(int sessionUserSid, int userPosSort)
    throws SQLException {
        return __getSpAccessSid(sessionUserSid, userPosSort, -1);
    }
    /**
     * <br>[機  能] 指定したユーザが許可対象として指定されているスケジュール特例アクセスのSIDを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param sessionUserSid セッションユーザSID
     * @param userPosSort セッションユーザの「役職 並び順」
     * @param sspAuth 権限区分 0:閲覧権限 1:編集権限
     * @return スケジュール特例アクセスSID
     * @throws SQLException SQL実行時例外
     */
    private List<Integer> __getSpAccessSid(int sessionUserSid, int userPosSort, int sspAuth)
    throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Integer> sidList = new ArrayList<Integer>();
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   SSA_SID");
            sql.addSql(" from");
            sql.addSql("   SCH_SPACCESS_PERMIT");
            sql.addSql(" where");
            sql.addSql("   (");
            sql.addSql("   (");
            sql.addSql("     SSP_TYPE = ?");
            sql.addSql("   and");
            sql.addSql("     SSP_PSID = ?");
            sql.addSql("   )");
            sql.addSql(" or");
            sql.addSql("   (");
            sql.addSql("     SSP_TYPE = ?");
            sql.addSql("   and");
            sql.addSql("     SSP_PSID in (");
            sql.addSql("       select GRP_SID from CMN_BELONGM");
            sql.addSql("       where USR_SID = ?");
            sql.addSql("     )");
            sql.addSql("   )");
            sql.addIntValue(GSConstSchedule.SSP_TYPE_USER);
            sql.addIntValue(sessionUserSid);
            sql.addIntValue(GSConstSchedule.SSP_TYPE_GROUP);
            sql.addIntValue(sessionUserSid);

            if (userPosSort != SchDao.POS_SORT_NONE) {
                sql.addSql(" or");
                sql.addSql("   (");
                sql.addSql("     SSP_TYPE = ?");
                sql.addSql("   and");
                sql.addSql("     SSP_PSID in (");
                sql.addSql("       select POS_SID from CMN_POSITION");
                sql.addSql("       where POS_SID > 0");
                sql.addSql("       and POS_SORT >= ?");
                sql.addSql("     )");
                sql.addSql("   )");
                sql.addIntValue(GSConstSchedule.SSP_TYPE_POSITION);
                sql.addIntValue(userPosSort);
            }
            sql.addSql("   )");
            if (sspAuth == GSConstSchedule.SSP_AUTH_EDIT
                    || sspAuth == GSConstSchedule.SSP_AUTH_VIEWONLY) {
                        sql.addSql("     and");
                        sql.addSql("       SSP_AUTH = ?");
                        sql.addIntValue(sspAuth);
                    }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                sidList.add(rs.getInt("SSA_SID"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return sidList;
    }

    /**
     * <p>
     * スケジュールSIDからスケジュール情報＋ユーザ情報を取得する
     * @param scdSid スケジュールSID
     * @param authFilter 権限による絞り込み区分 0=すべて、1=閲覧可能、2=編集可能
     * @param sessionUserSid セッションユーザSID
     * @return ScheduleSearchModel
     * @throws SQLException SQL実行例外
     */
    public ScheduleSearchModel getScheduleData(int scdSid, int authFilter, int sessionUserSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ScheduleSearchModel ret = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_GRP_SID,");
            sql.addSql("   SCD_USR_KBN,");
            sql.addSql("   SCD_FR_DATE,");
            sql.addSql("   SCD_TO_DATE,");
            sql.addSql("   SCD_DAILY,");
            sql.addSql("   SCD_BGCOLOR,");
            sql.addSql("   SCD_TITLE,");
            sql.addSql("   SCD_VALUE,");
            sql.addSql("   SCD_BIKO,");
            sql.addSql("   SCD_PUBLIC,");
            sql.addSql("   SCD_AUID,");
            sql.addSql("   SCD_ADATE,");
            sql.addSql("   SCD_EUID,");
            sql.addSql("   SCD_EDATE,");
            sql.addSql("   SCE_SID,");
            sql.addSql("   SCD_RSSID,");
            sql.addSql("   SCD_EDIT,");
            sql.addSql("   SCD_ATTEND_KBN,");
            sql.addSql("   SCD_ATTEND_ANS,");
            sql.addSql("   SCD_ATTEND_AU_KBN");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCD_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(scdSid);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            //ユーザ所属グループ格納用MAP
            Map<Integer, ArrayList<Integer>> usrBlongMap
            = new HashMap<Integer, ArrayList<Integer>>();

            if (rs.next()) {

                ArrayList<Integer> blongGpSidList = null;
                if (rs.getInt("SCD_USR_KBN") == GSConstSchedule.USER_KBN_USER) {
                //スケジュールユーザSID
                int scdUsrSid
                  = NullDefault.getInt(String.valueOf(rs.getInt("SCD_USR_SID")), 0);
                    //ユーザスケジュールを取得する場合
                    if (scdUsrSid > 0) {
                        //取得したスケジュールのユーザの所属グループを取得する
                        if (usrBlongMap.get(scdUsrSid) != null) {
                            //既にグループSIDを取得済みのユーザ
                            blongGpSidList = usrBlongMap.get(scdUsrSid);
                        } else {
                            //グループSIDを未取得のユーザ
                            CmnBelongmDao bdao = new CmnBelongmDao(con);
                            blongGpSidList
                               = bdao.selectUserBelongGroupSid(scdUsrSid);
                            if (blongGpSidList != null) {
                                usrBlongMap.put(scdUsrSid, blongGpSidList);
                            }
                        }

                    }
                }

                ret = __getSchUsrDataFromRs(rs);
                if (blongGpSidList != null) {
                    ret.setScdUserBlongGpList(blongGpSidList);
                }
                if (ret.getScdGrpSid() != GSConstSchedule.DF_SCHGP_ID) {
                    // 同時登録有りスケジュールの場合ユーザ氏名を取得
                    ArrayList<CmnUsrmInfModel> usrList = getScheduleUsrList(
                            scdSid, ret.getScdUsrSid(), ret.getScdUsrKbn(),
                            authFilter,
                            sessionUserSid);
                    ret.setUsrInfList(usrList);
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
     * <p>
     * スケジュール拡張SIDからスケジュール情報の件数を取得する
     * @param sceSid スケジュール拡張SID
     * @return スケジュール情報の件数
     * @throws SQLException SQL実行例外
     */
    public int getExCount(int sceSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int ret = 0;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   count(*) as CNT");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCE_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sceSid);
            log__.info(sql.toLogString());
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
     * <br>[機  能] スケジュール拡張SIDを指定し、同時登録しているスケジュールSIDの配列を取得します
     * <br>[解  説] アクセス不可に設定されているユーザのスケジュールは除外します
     * <br>[備  考]
     * @param sceSid スケジュール拡張SID
     * @param userSid ユーザSID
     * @return ArrayList in Integer
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<Integer> getExScheduleUsrs(int sceSid, int userSid) throws SQLException {
        return getExScheduleUsrs(sceSid, userSid, GSConstSchedule.SSP_AUTHFILTER_VIEW);
    }
    /**
     * <br>[機  能] スケジュール拡張SIDを指定し、同時登録しているスケジュールSIDの配列を取得します
     * <br>[解  説] アクセス不可に設定されているユーザのスケジュールは除外します
     * <br>[備  考]
     * @param sceSid スケジュール拡張SID
     * @param userSid ユーザSID
     * @param authFilter 権限による絞り込み区分 0=すべて、1=閲覧可能、2=編集可能
     * @return ArrayList in Integer
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<Integer> getExScheduleUsrs(int sceSid,
            int userSid, int authFilter) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        //スケジュールアクセス不可ユーザの一覧を取得
        SchDao schDao = new SchDao(getCon());
        List<Integer> notAccessUserList = new ArrayList<Integer>();
        //スケジュールアクセス不可グループの一覧を取得
        List<Integer> notAccessGroupList = new ArrayList<Integer>();
        if (authFilter == GSConstSchedule.SSP_AUTHFILTER_VIEW) {
            notAccessUserList  = schDao.getNotAccessUserList(userSid);
            notAccessGroupList = schDao.getNotAccessGrpList(userSid);
        }
        if (authFilter == GSConstSchedule.SSP_AUTHFILTER_EDIT) {
            notAccessUserList  = schDao.getNotRegistUserList(userSid);
            notAccessGroupList = schDao.getNotRegistGrpList(userSid);
        }

        con = getCon();
        ArrayList<Integer> scds = new ArrayList<Integer>();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   SCD_SID,");
            sql.addSql("   SCD_USR_SID,");
            sql.addSql("   SCD_USR_KBN");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where ");
            sql.addSql("   SCE_SID=?");
            sql.addIntValue(sceSid);

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int scdUsrKbn = 0;
            boolean canAccessSchedule = false;
            while (rs.next()) {

                //アクセス可能なユーザ or グループ か を判定
                scdUsrKbn = rs.getInt("SCD_USR_KBN");
                if (scdUsrKbn == GSConstSchedule.USER_KBN_GROUP) {
                    canAccessSchedule = notAccessGroupList.indexOf(rs.getInt("SCD_USR_SID")) < 0;
                } else {
                    canAccessSchedule = notAccessUserList.indexOf(rs.getInt("SCD_USR_SID")) < 0;
                }

                if (canAccessSchedule) {
                    scds.add(new Integer(rs.getInt("SCD_SID")));
                }
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return scds;
    }

    /**
     * <p>
     * スケジュールSIDから拡張登録されているスケジュール情報＋ユーザ情報を取得する
     * <p>
     * 拡張登録データが無い場合は空のArrayListを返します。
     * @param scdSid スケジュールSID
     * @return ArrayList in SchDataModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SchDataModel> getScheduleDataFromSceSid(int scdSid)
            throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();
        ArrayList<SchDataModel> ret = new ArrayList<SchDataModel>();
        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   SCH_DATA.SCD_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_SID,");
            sql.addSql("   SCH_DATA.SCD_GRP_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_KBN,");
            sql.addSql("   SCH_DATA.SCD_FR_DATE,");
            sql.addSql("   SCH_DATA.SCD_TO_DATE,");
            sql.addSql("   SCH_DATA.SCD_DAILY,");
            sql.addSql("   SCH_DATA.SCD_BGCOLOR,");
            sql.addSql("   SCH_DATA.SCD_TITLE,");
            sql.addSql("   SCH_DATA.SCD_VALUE,");
            sql.addSql("   SCH_DATA.SCD_BIKO,");
            sql.addSql("   SCH_DATA.SCD_PUBLIC,");
            sql.addSql("   SCH_DATA.SCD_AUID,");
            sql.addSql("   SCH_DATA.SCD_ADATE,");
            sql.addSql("   SCH_DATA.SCD_EUID,");
            sql.addSql("   SCH_DATA.SCD_EDATE,");
            sql.addSql("   SCH_DATA.SCE_SID,");
            sql.addSql("   SCH_DATA.SCD_RSSID,");
            sql.addSql("   SCH_DATA.SCD_EDIT,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_KBN,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_ANS,");
            sql.addSql("   SCH_DATA.SCD_ATTEND_AU_KBN");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   (");
            sql.addSql("    select");
            sql.addSql("      SCH_DATA.SCE_SID");
            sql.addSql("    from");
            sql.addSql("      SCH_DATA");
            sql.addSql("    where ");
            sql.addSql("      SCH_DATA.SCD_SID=?");
            sql.addSql("    and ");
            sql.addSql("      SCH_DATA.SCE_SID!=?");
            sql.addSql("   ) as EXID");
            sql.addSql(" where ");
            sql.addSql("   SCH_DATA.SCE_SID=EXID.SCE_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(scdSid);
            sql.addIntValue(GSConstSchedule.DEF_SCE_SID);
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            Map<Integer, ArrayList<Integer>> usrBlongMap
            = new HashMap<Integer, ArrayList<Integer>>();

            while (rs.next()) {
                ArrayList<Integer> blongGpSidList = null;
                if (rs.getInt("SCD_USR_KBN") == GSConstSchedule.USER_KBN_USER) {
                //スケジュールユーザSID
                Integer scdUsrSid
                  = NullDefault.getInt(String.valueOf(rs.getInt("SCD_USR_SID")), 0);
                    //ユーザスケジュールを取得する場合
                    if (scdUsrSid > 0) {
                        //取得したスケジュールのユーザの所属グループを取得する
                        if (usrBlongMap.get(scdUsrSid) != null) {
                            //既にグループSIDを取得済みのユーザ
                            blongGpSidList = usrBlongMap.get(scdUsrSid);
                        } else {
                            //グループSIDを未取得のユーザ
                            CmnBelongmDao bdao = new CmnBelongmDao(con);
                            blongGpSidList
                               = bdao.selectUserBelongGroupSid(scdUsrSid);
                            if (blongGpSidList != null) {
                                usrBlongMap.put(scdUsrSid, blongGpSidList);
                            }
                        }
                    }
                }
                ret.add(__getSchDataFromRs(rs, blongGpSidList));
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
     * 指定したスケジュールに対して同時登録が行われたか判定します。
     * @param scdSid スケジュールSID
     * @return true:同時登録スケジュールあり false:同時登録スケジュールあり
     * @throws SQLException SQL実行時例外
     */
    public boolean existWithUsers(int scdSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   count(SCD_SID) as CNT");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA");
            sql.addSql(" where");
            sql.addSql("   SCH_DATA.SCD_GRP_SID > 0");
            sql.addSql(" and");
            sql.addSql("   SCD_GRP_SID in (");
            sql.addSql("     select");
            sql.addSql("       SCD_GRP_SID");
            sql.addSql("     from");
            sql.addSql("       SCH_DATA");
            sql.addSql("     where");
            sql.addSql("       SCH_DATA.SCD_SID = " + scdSid);
            sql.addSql("   )");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_KBN = " + GSConstSchedule.USER_KBN_USER);

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            log__.debug("同時登録ユーザ存在チェック：開始");
            if (rs.next()) {
                //同一スケジュールグループのスケジュールが2件以上 = 同時登録が行われた
                result = rs.getLong("CNT") > 1;
            }
            log__.debug("同時登録ユーザ存在チェック：終了");
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return result;
    }
    /**
     *
     * <br>[機  能] スケジュールグループSIDから同時登録されているスケジュールを取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param scdGrpSid スケジュールグループSID
     * @param notAccessUserList 除外ユーザSIDリスト
     * @return 同時登録スケジュール一覧
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SchDataModel> getSameScheduleList(int scdGrpSid
            , List<Integer> notAccessUserList) throws SQLException {
        ArrayList<SchDataModel> ret = new ArrayList<SchDataModel>();
        if (scdGrpSid <= 0) {
            return ret;
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql("  select");
            sql.addSql("     SCH_DATA.SCD_SID as SCD_SID,");
            sql.addSql("     SCH_DATA.SCD_USR_SID,");
            sql.addSql("     SCH_DATA.SCD_GRP_SID,");
            sql.addSql("     SCH_DATA.SCD_USR_KBN,");
            sql.addSql("     SCH_DATA.SCD_FR_DATE,");
            sql.addSql("     SCH_DATA.SCD_TO_DATE,");
            sql.addSql("     SCH_DATA.SCD_DAILY,");
            sql.addSql("     SCH_DATA.SCD_BGCOLOR,");
            sql.addSql("     SCH_DATA.SCD_TITLE,");
            sql.addSql("     SCH_DATA.SCD_VALUE,");
            sql.addSql("     SCH_DATA.SCD_BIKO,");
            sql.addSql("     SCH_DATA.SCD_PUBLIC,");
            sql.addSql("     SCH_DATA.SCD_AUID,");
            sql.addSql("     SCH_DATA.SCD_ADATE,");
            sql.addSql("     SCH_DATA.SCD_EUID,");
            sql.addSql("     SCH_DATA.SCD_EDATE,");
            sql.addSql("     SCH_DATA.SCE_SID,");
            sql.addSql("     SCH_DATA.SCD_RSSID,");
            sql.addSql("     SCH_DATA.SCD_EDIT,");
            sql.addSql("     SCH_DATA.SCD_ATTEND_KBN,");
            sql.addSql("     SCH_DATA.SCD_ATTEND_ANS,");
            sql.addSql("     SCH_DATA.SCD_ATTEND_AU_KBN,");
            sql.addSql("     CMN_USRM_INF.USI_SEI,");
            sql.addSql("     CMN_USRM_INF.USI_MEI,");
            sql.addSql("     CMN_USRM_INF.USI_SEI_KN,");
            sql.addSql("     CMN_USRM_INF.USI_MEI_KN");
            sql.addSql("  from ");
            sql.addSql("    SCH_DATA,");
            sql.addSql("    CMN_USRM_INF");
            sql.addSql("  where ");
            sql.addSql("    SCH_DATA.SCD_GRP_SID = " + scdGrpSid);
            sql.addSql("  and");
            sql.addSql("    SCH_DATA.SCD_USR_KBN = " + GSConstSchedule.USER_KBN_USER);
            sql.addSql("  and ");
            sql.addSql("    CMN_USRM_INF.USR_SID = SCH_DATA.SCD_USR_SID");
            sql.addSql("  order by");
            sql.addSql("    CMN_USRM_INF.USI_SEI_KN, ");
            sql.addSql("    CMN_USRM_INF.USI_MEI_KN;    ");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            log__.debug("同時登録スケジュール取得：開始");
            while (rs.next()) {
                SchDataModel mdl = __getSchDataFromRs(rs, null);
                mdl.setScdUserName(rs.getString("USI_SEI")
                        + " " + rs.getString("USI_MEI"));
                if (notAccessUserList.indexOf(rs.getInt("SCD_USR_SID")) < 0) {
                    log__.debug("同時登録ユーザ氏名==>" + mdl.getScdUserName());
                    ret.add(mdl);
                }
            }
            log__.debug("同時登録スケジュール：終了");
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * スケジュールSIDを指定し同時登録しているユーザ情報を取得します
     * @param scdSid スケジュールSID
     * @param usrSid 除外するユーザ
     * @param usrKbn 除外するユーザの区分
     * @param authFilter 権限による絞り込み区分 0=すべて、1=閲覧可能、2=編集可能
     * @param sessionUserSid セッションユーザSID
     * @return ArrayLis in CmnUsrmInfModel
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<CmnUsrmInfModel> getScheduleUsrList(int scdSid,
            int usrSid, int usrKbn, int authFilter, int sessionUserSid) throws SQLException {

        ArrayList<CmnUsrmInfModel> ret = new ArrayList<CmnUsrmInfModel>();
        if (scdSid <= 0 || usrSid <= 0) {
            return ret;
        }

        SchDao schDao = new SchDao(getCon());

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        CmnUsrmInfModel usrMdl = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("   select ");
            sql.addSql("     USR_SID,");
            sql.addSql("     USI_SEI, ");
            sql.addSql("     USI_MEI ");
            sql.addSql("   from");
            sql.addSql("     CMN_USRM_INF");
            sql.addSql("   where");
            sql.addSql("     USR_SID in");
            sql.addSql("     ( ");
            sql.addSql("      select ");
            sql.addSql("        SCH_DATA2.SCD_USR_SID");
            sql.addSql("      from ");
            sql.addSql("        SCH_DATA,");
            sql.addSql("        SCH_DATA SCH_DATA2");
            sql.addSql("      where ");
            sql.addSql("        SCH_DATA.SCD_SID = " + scdSid);
            sql.addSql("      and");
            sql.addSql("        SCH_DATA2.SCD_GRP_SID > 0");
            sql.addSql("      and");
            sql.addSql("        SCH_DATA2.SCD_USR_KBN = " + GSConstSchedule.USER_KBN_USER);
            sql.addSql("      and");
            sql.addSql("        SCH_DATA.SCD_GRP_SID = SCH_DATA2.SCD_GRP_SID");
            sql.addSql("      and");
            sql.addSql("        SCH_DATA2.SCD_USR_SID != " + usrSid);
            sql.addSql("      )");
            sql.addSql("   order by");
            sql.addSql("     USI_SEI_KN, ");
            sql.addSql("     USI_MEI_KN;");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            log__.debug("同時登録ユーザ取得：開始");
            while (rs.next()) {
                usrMdl = new CmnUsrmInfModel();
                usrMdl.setUsrSid(rs.getInt("USR_SID"));
                usrMdl.setUsiSei(rs.getString("USI_SEI"));
                usrMdl.setUsiMei(rs.getString("USI_MEI"));
                log__.debug("同時登録ユーザ氏名==>" + usrMdl.getUsiSei()
                        + usrMdl.getUsiMei());
                ret.add(usrMdl);
            }

            //アクセス不可のユーザを除外する
            if (!ret.isEmpty()) {
                List<Integer> notAccessUserList = new ArrayList<Integer>();
                if (authFilter == GSConstSchedule.SSP_AUTHFILTER_VIEW) {
                    notAccessUserList = schDao.getNotAccessUserList(sessionUserSid);
                }
                if (authFilter == GSConstSchedule.SSP_AUTHFILTER_EDIT) {
                    notAccessUserList = schDao.getNotRegistUserList(sessionUserSid);
                }
                if (!notAccessUserList.isEmpty()) {
                    List<CmnUsrmInfModel> schUserList = new ArrayList<CmnUsrmInfModel>();
                    for (CmnUsrmInfModel userData : ret) {
                        if (notAccessUserList.indexOf(userData.getUsrSid()) < 0) {
                            schUserList.add(userData);
                        }
                    }
                    ret.clear();
                    ret.addAll(schUserList);
                }
            }

            log__.debug("同時登録ユーザ取得：終了");
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }
    /**
     * スケジュールグループSIDを指定し同時登録しているグループ情報を取得します
     * @param scdGpSid スケジュールグループSID
     * @param usrSid 除外するユーザ
     * @param usrKbn 除外するユーザの区分
     * @param crange 共有範囲設定0=全て、1=所属グループのみ
     * @return ArrayLis in CmnUsrmInfModel
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<CmnUsrmInfModel> getScheduleGrpList(int scdGpSid,
            int usrSid, int usrKbn, int crange) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<CmnUsrmInfModel> ret = new ArrayList<CmnUsrmInfModel>();
        CmnUsrmInfModel usrMdl = null;
        con = getCon();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   SCH_DATA.SCD_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_KBN,");
            sql.addSql("   SCH_DATA.SCD_GRP_SID,");
            sql.addSql("   CMN_GROUPM.GRP_SID,");
            sql.addSql("   CMN_GROUPM.GRP_NAME,");
            sql.addSql("   CMN_GROUPM.GRP_NAME_KN");
            sql.addSql(" from");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   CMN_GROUPM");
            sql.addSql(" where ");
            sql.addSql("   SCH_DATA.SCD_USR_SID = CMN_GROUPM.GRP_SID");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_GRP_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_KBN = ?");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_SID !=?");
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_KBN !=?");
            sql.addIntValue(scdGpSid);
            sql.addIntValue(GSConstSchedule.USER_KBN_GROUP);
            sql.addIntValue(usrSid);
            sql.addIntValue(usrKbn);
            sql.addSql(" order by");
            sql.addSql("   CMN_GROUPM.GRP_NAME_KN");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                usrMdl = new CmnUsrmInfModel();
                usrMdl.setUsrSid(rs.getInt("GRP_SID"));
                usrMdl.setUsiSei(rs.getString("GRP_NAME"));
                usrMdl.setUsiMei("");
                ret.add(usrMdl);
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
     * スケジュールSIDを指定し同時登録しているスケジュールSIDの配列を取得します
     * @param scdSid スケジュールSID
     * @param userSid ユーザSID
     * @param crange 共有範囲設定
     * @return ArrayList in Integer
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<Integer> getScheduleUsrs(int scdSid, int userSid,
            int crange) throws SQLException {
        return getScheduleUsrs(scdSid, userSid, crange, GSConstSchedule.SSP_AUTHFILTER_VIEW);
    }
    /**
     * スケジュールSIDを指定し、同時登録しているスケジュールSIDの配列を取得します
     * @param scdSid スケジュールSID
     * @param userSid ユーザSID
     * @param crange 共有範囲設定
     * @param authFilter 権限による絞り込み区分 0=すべて、1=閲覧可能、2=編集可能
     * @return ArrayList in Integer
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<Integer> getScheduleUsrs(int scdSid, int userSid,
            int crange, int authFilter) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        //スケジュールアクセス不可ユーザの一覧を取得
        SchDao schDao = new SchDao(getCon());
        List<Integer> notAccessUserList = new ArrayList<Integer>();
        if (authFilter == GSConstSchedule.SSP_AUTHFILTER_VIEW) {
            notAccessUserList
                = schDao.getNotAccessUserList(userSid);
        }
        if (authFilter == GSConstSchedule.SSP_AUTHFILTER_EDIT) {
            notAccessUserList
                = schDao.getNotRegistUserList(userSid);
        }
        //ユーザの「役職 並び順」を取得
        int userPosSort = schDao.getUserPosSort(userSid);

        con = getCon();
        ArrayList<Integer> scds = new ArrayList<Integer>();

        try {
            // SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   SCH_DATA.SCD_SID as SCD_SID,");
            sql.addSql("   SCH_DATA.SCD_USR_SID as SCD_USR_SID");
            sql.addSql(" from");
            sql.addSql("   (");
            sql.addSql("    select");
            sql.addSql("      SCH_DATA.SCD_GRP_SID");
            sql.addSql("    from");
            sql.addSql("      SCH_DATA");
            sql.addSql("    where");
            sql.addSql("      SCH_DATA.SCD_SID=?");
            sql.addSql("    and");
            sql.addSql("      SCH_DATA.SCD_GRP_SID!=?");
            sql.addSql("   ) GP,");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   CMN_USRM_INF");
            sql.addIntValue(scdSid);
            sql.addIntValue(GSConstSchedule.DF_SCHGP_ID);

            sql.addSql(" where ");
            sql.addSql("   GP.SCD_GRP_SID = SCH_DATA.SCD_GRP_SID");

            //所属グループ
            if (crange == GSConstSchedule.CRANGE_SHARE_GROUP
                    && authFilter != GSConstSchedule.SSP_AUTHFILTER_NONE) {
                sql.addSql(" and");
                sql.addSql("   (");

                //所属グループのユーザ
                sql.addSql("    SCH_DATA.SCD_USR_SID in (");
                sql.addSql("      select");
                sql.addSql("        CMN_BELONGM.USR_SID as USR_SID");
                sql.addSql("      from");
                sql.addSql("        CMN_BELONGM,");
                sql.addSql("        (");
                sql.addSql("          select");
                sql.addSql("            CMN_GROUPM.GRP_SID as GRP_SID");
                sql.addSql("          from");
                sql.addSql("            CMN_GROUPM,");
                sql.addSql("            CMN_BELONGM");
                sql.addSql("          where");
                sql.addSql("            CMN_BELONGM.USR_SID = " + userSid);

                sql.addSql("          and");
                sql.addSql("            CMN_BELONGM.GRP_SID = CMN_GROUPM.GRP_SID");
                sql.addSql("        ) BELONG");
                sql.addSql("      where");
                sql.addSql("        CMN_BELONGM.GRP_SID = BELONG.GRP_SID");
                sql.addSql("      group by CMN_BELONGM.USR_SID");
                sql.addSql("    )");

                //ユーザが許可対象として指定されているスケジュール特例アクセスのSIDを取得する
                List<Integer> ssaSidList = null;
                if (authFilter == GSConstSchedule.SSP_AUTHFILTER_VIEW) {
                    ssaSidList = __getSpAccessSid(userSid, userPosSort);
                }
                if (authFilter == GSConstSchedule.SSP_AUTHFILTER_EDIT) {
                    ssaSidList = __getSpAccessSid(userSid,
                            userPosSort,
                            GSConstSchedule.SSP_AUTH_EDIT);
                }
                if (ssaSidList != null && !ssaSidList.isEmpty()) {
                    //特例アクセスを許可されているユーザ
                     sql.addSql("  or ");
                     sql.addSql("    SCH_DATA.SCD_USR_SID in (");
                     sql.addSql("      select");
                     sql.addSql("        SCH_SPACCESS_TARGET.SST_TSID as USR_SID");
                     sql.addSql("      from");
                     sql.addSql("        SCH_SPACCESS_TARGET");
                     sql.addSql("      where");
                     sql.addSql("        SCH_SPACCESS_TARGET.SST_TYPE = ?");
                     sql.addIntValue(GSConstSchedule.SST_TYPE_USER);

                     sql.addSql("      and");
                     sql.addSql("        SCH_SPACCESS_TARGET.SSA_SID in (");
                     for (int idx = 0; idx < ssaSidList.size(); idx++) {
                         if (idx > 0) {
                             sql.addSql("        ,?");
                         } else {
                             sql.addSql("        ?");
                         }
                         sql.addIntValue(ssaSidList.get(idx));
                     }
                     sql.addSql("        )");
                     sql.addSql("      )");

                     //特例アクセスを許可されているグループに所属するユーザ
                     sql.addSql("  or ");
                     sql.addSql("    SCH_DATA.SCD_USR_SID in (");
                     sql.addSql("      select");
                     sql.addSql("        CMN_BELONGM.USR_SID as USR_SID");
                     sql.addSql("      from");
                     sql.addSql("        CMN_BELONGM,");
                     sql.addSql("        SCH_SPACCESS_TARGET");
                     sql.addSql("      where");
                     sql.addSql("        SCH_SPACCESS_TARGET.SST_TYPE = ?");
                     sql.addIntValue(GSConstSchedule.SST_TYPE_GROUP);

                     sql.addSql("      and");
                     sql.addSql("        SCH_SPACCESS_TARGET.SSA_SID in (");
                     for (int idx = 0; idx < ssaSidList.size(); idx++) {
                         if (idx > 0) {
                             sql.addSql("        ,?");
                         } else {
                             sql.addSql("        ?");
                         }
                         sql.addIntValue(ssaSidList.get(idx));
                     }
                     sql.addSql("      )");
                     sql.addSql("    and");
                     sql.addSql("      CMN_BELONGM.GRP_SID = SCH_SPACCESS_TARGET.SST_TSID");
                     sql.addSql("    )");

                }

                sql.addSql("   )");
            }
            sql.addSql(" and");
            sql.addSql("   SCH_DATA.SCD_USR_SID = CMN_USRM_INF.USR_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (notAccessUserList.indexOf(rs.getInt("SCD_USR_SID")) < 0) {
                    scds.add(new Integer(rs.getInt("SCD_SID")));
                }
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return scds;
    }
    /**
     * <p>
     * Create SCH_DATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @param blongGpSid belongGpSid
     * @return created SchDataModel
     * @throws SQLException SQL実行例外
     */
    private SchDataModel __getSchDataFromRs(
            ResultSet rs, ArrayList<Integer> blongGpSid) throws SQLException {
        return __getSchDataFromRs(rs, blongGpSid, true);
    }
    /**
     * <p>
     * Create SCH_DATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @param blongGpSid belongGpSid
     * @param attendDsp スケジュールタイトルに出欠確認結果
     * @return created SchDataModel
     * @throws SQLException SQL実行例外
     */
    private SchDataModel __getSchDataFromRs(
            ResultSet rs, ArrayList<Integer> blongGpSid, boolean attendDsp) throws SQLException {
        SchDataModel bean = new SchDataModel();
        bean.setScdSid(rs.getInt("SCD_SID"));
        bean.setScdUsrSid(rs.getInt("SCD_USR_SID"));
        bean.setScdGrpSid(rs.getInt("SCD_GRP_SID"));
        bean.setScdUsrKbn(rs.getInt("SCD_USR_KBN"));
        bean.setScdFrDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_FR_DATE")));
        bean.setScdToDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_TO_DATE")));
        bean.setScdDaily(rs.getInt("SCD_DAILY"));
        bean.setScdBgcolor(rs.getInt("SCD_BGCOLOR"));
        if (attendDsp) {
            String attend = __getHeadAttend(
                    rs.getInt("SCD_ATTEND_KBN"), rs.getInt("SCD_ATTEND_ANS"));
            bean.setScdTitle(attend + rs.getString("SCD_TITLE"));
        } else {
            bean.setScdTitle(rs.getString("SCD_TITLE"));
        }
        bean.setScdValue(rs.getString("SCD_VALUE"));
        bean.setScdBiko(rs.getString("SCD_BIKO"));
        bean.setScdPublic(rs.getInt("SCD_PUBLIC"));
        bean.setScdAuid(rs.getInt("SCD_AUID"));
        bean.setScdAdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_ADATE")));
        bean.setScdEuid(rs.getInt("SCD_EUID"));
        bean.setScdEdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_EDATE")));
        bean.setSceSid(rs.getInt("SCE_SID"));
        bean.setScdRsSid(rs.getInt("SCD_RSSID"));
        bean.setScdEdit(rs.getInt("SCD_EDIT"));

        bean.setScdAttendKbn(rs.getInt("SCD_ATTEND_KBN"));
        bean.setScdAttendAns(rs.getInt("SCD_ATTEND_ANS"));
        bean.setScdAttendAuKbn(rs.getInt("SCD_ATTEND_AU_KBN"));

        if (blongGpSid != null && !blongGpSid.isEmpty()) {
            bean.setScdUserBlongGpList(blongGpSid);
        }
        return bean;
    }
    /**
     * <p>
     * Create SCH_DATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @param blongGpSid belongGpSid
     * @return created SchDataModel
     * @throws SQLException SQL実行例外
     */
    private SchDataModel __getSchDataPlusNameFromRs(
            ResultSet rs, ArrayList<Integer> blongGpSid) throws SQLException {
        SchDataModel bean = new SchDataModel();
        bean.setScdSid(rs.getInt("SCD_SID"));
        bean.setScdUsrSid(rs.getInt("SCD_USR_SID"));
        bean.setScdGrpSid(rs.getInt("SCD_GRP_SID"));
        bean.setScdUsrKbn(rs.getInt("SCD_USR_KBN"));
        bean.setScdFrDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_FR_DATE")));
        bean.setScdToDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_TO_DATE")));
        bean.setScdDaily(rs.getInt("SCD_DAILY"));
        bean.setScdBgcolor(rs.getInt("SCD_BGCOLOR"));
        String attend = __getHeadAttend(
                rs.getInt("SCD_ATTEND_KBN"), rs.getInt("SCD_ATTEND_ANS"));
        bean.setScdTitle(attend + rs.getString("SCD_TITLE"));
        bean.setScdValue(rs.getString("SCD_VALUE"));
        bean.setScdBiko(rs.getString("SCD_BIKO"));
        bean.setScdPublic(rs.getInt("SCD_PUBLIC"));
        bean.setScdAuid(rs.getInt("SCD_AUID"));
        bean.setScdAdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_ADATE")));
        bean.setScdEuid(rs.getInt("SCD_EUID"));
        bean.setScdEdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_EDATE")));
        bean.setSceSid(rs.getInt("SCE_SID"));
        bean.setScdRsSid(rs.getInt("SCD_RSSID"));
        bean.setScdEdit(rs.getInt("SCD_EDIT"));

        bean.setScdAttendKbn(rs.getInt("SCD_ATTEND_KBN"));
        bean.setScdAttendAns(rs.getInt("SCD_ATTEND_ANS"));
        bean.setScdAttendAuKbn(rs.getInt("SCD_ATTEND_AU_KBN"));

        bean.setScdUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
        if (blongGpSid != null && !blongGpSid.isEmpty()) {
            bean.setScdUserBlongGpList(blongGpSid);
        }
        return bean;
    }
    /**
     * <p>
     * ScheduleSearchModel Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created ScheduleSearchModel
     * @throws SQLException SQL実行例外
     */
    private ScheduleSearchModel __getSchUsrDataFromRs(ResultSet rs)
            throws SQLException {
        ScheduleSearchModel bean = new ScheduleSearchModel();
        bean.setScdSid(rs.getInt("SCD_SID"));
        bean.setScdUsrSid(rs.getInt("SCD_USR_SID"));
        bean.setScdGrpSid(rs.getInt("SCD_GRP_SID"));
        bean.setScdUsrKbn(rs.getInt("SCD_USR_KBN"));
        bean.setScdFrDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_FR_DATE")));
        bean.setScdToDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_TO_DATE")));
        bean.setScdDaily(rs.getInt("SCD_DAILY"));
        bean.setScdBgcolor(rs.getInt("SCD_BGCOLOR"));
        bean.setScdTitle(rs.getString("SCD_TITLE"));
        bean.setScdValue(rs.getString("SCD_VALUE"));
        bean.setScdBiko(rs.getString("SCD_BIKO"));
        bean.setScdPublic(rs.getInt("SCD_PUBLIC"));
        bean.setScdAuid(rs.getInt("SCD_AUID"));
        bean.setScdAdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_ADATE")));
        bean.setScdEuid(rs.getInt("SCD_EUID"));
        bean.setScdEdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_EDATE")));
        bean.setSceSid(rs.getInt("SCE_SID"));
        bean.setScdRsSid(rs.getInt("SCD_RSSID"));
        bean.setScdEdit(rs.getInt("SCD_EDIT"));
        bean.setScdAttendKbn(rs.getInt("SCD_ATTEND_KBN"));
        bean.setScdAttendAns(rs.getInt("SCD_ATTEND_ANS"));
        bean.setScdAttendAuKbn(rs.getInt("SCD_ATTEND_AU_KBN"));
        return bean;
    }

    /**
     * <p>
     * Create SCH_DATA Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created ScheduleCsvModel
     * @throws SQLException SQL実行例外
     */
    private ScheduleCsvModel __getScheduleCsvFromRs(ResultSet rs)
            throws SQLException {
        ScheduleCsvModel bean = new ScheduleCsvModel();
        bean.setLoginId(rs.getString("USR_LGID"));
        bean.setGroupId(rs.getString("GRP_ID"));
        bean.setScdSid(rs.getInt("SCD_SID"));
        bean.setScdUsrSid(rs.getInt("SCD_USR_SID"));
        bean.setScdGrpSid(rs.getInt("SCD_GRP_SID"));
        bean.setScdUsrKbn(rs.getInt("SCD_USR_KBN"));
        bean.setScdFrDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_FR_DATE")));
        bean.setScdToDate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_TO_DATE")));
        bean.setScdDaily(rs.getInt("SCD_DAILY"));
        bean.setScdBgcolor(rs.getInt("SCD_BGCOLOR"));
        bean.setScdTitle(rs.getString("SCD_TITLE"));
        bean.setScdValue(rs.getString("SCD_VALUE"));
        bean.setScdBiko(rs.getString("SCD_BIKO"));
        bean.setScdPublic(rs.getInt("SCD_PUBLIC"));
        bean.setScdEdit(rs.getInt("SCD_EDIT"));
        bean.setScdAuid(rs.getInt("SCD_AUID"));
        bean.setScdAdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_ADATE")));
        bean.setScdEuid(rs.getInt("SCD_EUID"));
        bean.setScdEdate(UDate.getInstanceTimestamp(rs
                .getTimestamp("SCD_EDATE")));

        bean.setUsrName(rs.getString("USR_NAME"));
        bean.setAddUsrName(rs.getString("AUSR_NAME"));
        bean.setEdtUsrName(rs.getString("EUSR_NAME"));

        bean.setScdFrDateStr(UDateUtil.getSlashYYMD(bean.getScdFrDate()));
        bean.setScdFrTimeStr(UDateUtil.getSeparateHM(bean.getScdFrDate()));
        bean.setScdToDateStr(UDateUtil.getSlashYYMD(bean.getScdToDate()));
        bean.setScdToTimeStr(UDateUtil.getSeparateHM(bean.getScdToDate()));

        return bean;
    }

    /**
     * <p>
     * SchDataModelからSchduleCsvModelを作成し、ScheduleCsvRecordListenerImplにセットする。
     * @param model CmnUsrmInfModel
     * @param sessionUsrSid セッションユーザSID(実行者)
     * @param rl UsrCsvRecordListenerIppanImpl
     * @param req リクエスト
     * @throws SQLException SQL実行例外
     * @throws CSVException CSV出力時例外
     */
    public static void setSchCsvRecordFromSchDataModel(ScheduleCsvModel model,
            int sessionUsrSid, SchCsvRecordListenerIppanImpl rl, HttpServletRequest req)
            throws SQLException, CSVException {

        int usrSid = model.getScdUsrSid();
        int usrKbn = model.getScdUsrKbn();
        //予定あり
        GsMessage gsMsg = new GsMessage();
        String textYoteiari = gsMsg.getMessage(req, "schedule.src.9");

        if (usrKbn == GSConstSchedule.USER_KBN_USER && usrSid == sessionUsrSid) {
            // 本人

        } else if (usrKbn == GSConstSchedule.USER_KBN_USER
                && usrSid != sessionUsrSid) {
            // 他ユーザ
            if (model.getScdPublic() == GSConstSchedule.DSP_YOTEIARI) {
                //予定あり
                model.setScdTitle(textYoteiari);
                model.setScdValue("");
                model.setScdBiko("");
            } else if (model.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
                // 非公開(CSV出力しない)
                return;
            } else if (model.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP) {
                //所属グループのみ公開
                if (model.isMyGrpFlg()) {
                    //マイグループには「予定あり」
                    model.setScdTitle(textYoteiari);
                }
            }
        }
        rl.setRecord(model);
    }

    /**
     * <br>[機  能] スケジュールタイトルに付加する出欠回答状況の文字列を取得する
     * <br>[解  説] [出]、[欠]、[未]
     * <br>[備  考]
     *
     * @param attendKbn 出欠確認区分
     * @param attendRegKbn 出欠回答区分
     * @return ヘッダー
     *
     */
    private String __getHeadAttend(int attendKbn, int attendRegKbn) {

        //出欠確認区分「確認しない」の場合
        if (attendKbn == GSConstSchedule.ATTEND_KBN_NO) {
            return "";
        //出欠確認区分「確認する」の場合
        } else if (attendKbn == GSConstSchedule.ATTEND_KBN_YES) {
            //出欠回答区分 出席
            if (attendRegKbn == GSConstSchedule.ATTEND_ANS_YES) {
                return "[出]";
            //出欠回答区分 欠席
            } else if (attendRegKbn == GSConstSchedule.ATTEND_ANS_NO) {
                return "[欠]";
            //出欠回答区分 未回答
            } else {
                return "[未]";
            }
        }
        return "";
    }
}
