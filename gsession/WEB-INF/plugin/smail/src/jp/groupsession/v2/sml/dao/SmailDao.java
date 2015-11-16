package jp.groupsession.v2.sml.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.sml.GSConstSmail;
import jp.groupsession.v2.sml.model.AtesakiModel;
import jp.groupsession.v2.sml.model.MailFilterConditionModel;
import jp.groupsession.v2.sml.model.MailFilterModel;
import jp.groupsession.v2.sml.model.SmailDetailModel;
import jp.groupsession.v2.sml.model.SmailModel;
import jp.groupsession.v2.sml.model.SmlLabelModel;
import jp.groupsession.v2.sml.sml030.HashControlModel;
import jp.groupsession.v2.sml.sml030.Sml030Model;
import jp.groupsession.v2.sml.sml110.Sml110FwCheckModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ショートメールプラグインで共通利用されるDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class SmailDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(SmailDao.class);

    /**
     * <p>デフォルトコンストラクタ
     */
    public SmailDao() {
    }

    /**
     * <p>デフォルトコンストラクタ
     * @param con DBコネクション
     */
    public SmailDao(Connection con) {
        super(con);
    }

    /**
     * <br>[機  能] 指定されたユーザのメッセージ(受信)件数を取得
     * <br>[解  説]
     * <br>[備  考] 開封、未開封は問わない
     *
     * @param sacSid アカウントSID
     * @return count データ数
     * @throws SQLException SQL実行例外
     */
    public int getJmeisCount(int sacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(SML_JMEIS.SMJ_SID) as cnt");
            sql.addSql(" from");
            sql.addSql("   SML_JMEIS,");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("cnt");
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
     * <br>[機  能] 指定されたユーザのメッセージ(送信)件数を取得
     * <br>[解  説]
     * <br>[備  考] 開封、未開封は問わない
     *
     * @param sacSid アカウントSID
     * @return count データ数
     * @throws SQLException SQL実行例外
     */
    public int getSmeisCount(int sacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(SML_SMEIS.SMS_SID) as cnt");
            sql.addSql(" from");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_SMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SMS_JKBN = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("cnt");
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
     * <br>[機  能] 指定されたユーザのメッセージ(下書き)件数を取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @return count データ数
     * @throws SQLException SQL実行例外
     */
    public int getWmeisCount(int sacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(SML_WMEIS.SMW_SID) as cnt");
            sql.addSql(" from");
            sql.addSql("   SML_WMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_WMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_WMEIS.SMW_JKBN = ?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("cnt");
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
     * <br>[機  能] 指定されたユーザのメッセージ(ゴミ箱)件数を取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @return count データ数
     * @throws SQLException SQL実行例外
     */
    public int getGomibakoCount(int sacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   sum(cntView.cnt) as cnt");
            sql.addSql(" from");
            sql.addSql("   (");
            //受信
            sql.addSql("    select");
            sql.addSql("      count(SML_JMEIS.SMJ_SID) as cnt");
            sql.addSql("    from");
            sql.addSql("      SML_JMEIS,");
            sql.addSql("      SML_SMEIS");
            sql.addSql("    where");
            sql.addSql("      SML_JMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql("    and");
            sql.addSql("      SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql(" union all");
            //送信
            sql.addSql("    select");
            sql.addSql("      count(SML_SMEIS.SMS_SID) as cnt");
            sql.addSql("    from");
            sql.addSql("      SML_SMEIS");
            sql.addSql("    where");
            sql.addSql("      SML_SMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SMS_JKBN = ?");
            sql.addSql(" union all");
            //草稿
            sql.addSql("    select");
            sql.addSql("      count(SML_WMEIS.SMW_SID) as cnt");
            sql.addSql("    from");
            sql.addSql("      SML_WMEIS");
            sql.addSql("    where");
            sql.addSql("      SML_WMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_WMEIS.SMW_JKBN = ?");
            sql.addSql("   ) cntView");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            log__.info(sql.toLogString());

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("cnt");
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
     * <br>[機  能] 指定されたアカウントのメッセージ(ラベル)件数を取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param lblSid ラベルSID
     * @return count データ数
     * @throws SQLException SQL実行例外
     */
    public int getLabelCount(int sacSid, int lblSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        int count = 0;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   sum(cntView.cnt) as cnt");
            sql.addSql(" from");
            sql.addSql("   (");
            //受信
            sql.addSql("    select");
            sql.addSql("      count(SML_JMEIS.SMJ_SID) as cnt");
            sql.addSql("    from");
            sql.addSql("      SML_JMEIS_LABEL");
            sql.addSql("      left join ");
            sql.addSql("         SML_JMEIS");
            sql.addSql("         on");
            sql.addSql("           SML_JMEIS.SMJ_SID =  SML_JMEIS_LABEL.SMJ_SID");
            sql.addSql("            left join");
            sql.addSql("              SML_SMEIS");
            sql.addSql("              on ");
            sql.addSql("               SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql("     where");
            sql.addSql("       SML_JMEIS.SAC_SID = ?");
            sql.addSql("     and");
            sql.addSql("       SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql("     and ");
            sql.addSql("       SML_JMEIS_LABEL.SLB_SID = ?");
            sql.addSql(" union all");
            //送信
            sql.addSql("    select");
            sql.addSql("      count(SML_SMEIS.SMS_SID) as cnt");
            sql.addSql("    from");
            sql.addSql("      SML_SMEIS_LABEL");
            sql.addSql("      left join ");
            sql.addSql("        SML_SMEIS");
            sql.addSql("        on");
            sql.addSql("         SML_SMEIS.SMS_SID =  SML_SMEIS_LABEL.SMS_SID");
            sql.addSql("    where");
            sql.addSql("       SML_SMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("       SML_SMEIS.SMS_JKBN = ?");
            sql.addSql("    and ");
            sql.addSql("       SML_SMEIS_LABEL.SLB_SID = ?");
            sql.addSql(" union all");
            //草稿
            sql.addSql("    select");
            sql.addSql("      count(SML_WMEIS.SMW_SID) as cnt");
            sql.addSql("    from");
            sql.addSql("      SML_WMEIS_LABEL");
            sql.addSql("      left join ");
            sql.addSql("        SML_WMEIS");
            sql.addSql("        on");
            sql.addSql("         SML_WMEIS.SMW_SID =  SML_WMEIS_LABEL.SMW_SID");
            sql.addSql("    where");
            sql.addSql("       SML_WMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("       SML_WMEIS.SMW_JKBN = ?");
            sql.addSql("    and ");
            sql.addSql("       SML_WMEIS_LABEL.SLB_SID = ?");
            sql.addSql("   ) cntView");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
            sql.addIntValue(lblSid);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
            sql.addIntValue(lblSid);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
            sql.addIntValue(lblSid);
            log__.info(sql.toLogString());

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("cnt");
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
     * <br>[機  能] 指定されたユーザの受信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userSid ユーザSID
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return ret 受信メッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList < SmailModel > selectJmeisList(int userSid,
                                                     int offset,
                                                     int limit,
                                                     int sortKey,
                                                     int orderKey)
        throws SQLException {
        return selectJmeisList(userSid, offset, limit, sortKey, orderKey, -1);
    }
    /**
     * <br>[機  能] 指定されたユーザの受信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考] midokuFlgの値によって未読のみ、全てを指定できます。
     *
     * @param sacSid アカウントSID
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @param opkbn 開封区分 0:未読のみ, 1: 既読, その他: 全て
     * @return ret 受信メッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailModel> selectJmeisList(int sacSid,
                                                     int offset,
                                                     int limit,
                                                     int sortKey,
                                                     int orderKey,
                                                     int opkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailModel> ret = new ArrayList<SmailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("   SML_JMEIS.SMJ_SID as smjSid, ");
            sql.addSql("   SML_JMEIS.SMJ_OPKBN as smjOpkbn, ");
            sql.addSql("   SML_JMEIS.SMJ_SENDKBN as smjSendkbn, ");
            sql.addSql("   SML_JMEIS.SMJ_RTN_KBN as smjRtnKbn, ");
            sql.addSql("   SML_JMEIS.SMJ_FW_KBN as smjFwKbn, ");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark, ");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle, ");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate, ");
            sql.addSql("   SML_SMEIS.SMS_SIZE as smsSize, ");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid, ");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn, ");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei, ");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei, ");
            sql.addSql("   CMN_USRM_INF.BIN_SID as binSid, ");
            sql.addSql("   CMN_USRM_INF.USI_PICT_KF as usiPictKf, ");
            sql.addSql("   BINCT.CT as binCnt ");
            sql.addSql(" from ");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("    SML_SMEIS, ");
            sql.addSql("    SML_JMEIS ");
            sql.addSql("     left join ");
            sql.addSql("       ( ");
            sql.addSql("        select ");
            sql.addSql("          SML_SID, ");
            sql.addSql("          count(*) as CT ");
            sql.addSql("        from ");
            sql.addSql("          SML_BIN ");
            sql.addSql("        where");
            sql.addSql("          SML_SID in (");
            sql.addSql("            select SMJ_SID from SML_JMEIS where SAC_SID = ?");
            sql.addSql("          )");
            sql.addSql("        group by ");
            sql.addSql("          SML_SID ");
            sql.addSql("        ) BINCT ");
            sql.addSql("     on SML_JMEIS.SMJ_SID = BINCT.SML_SID ");
            sql.addSql("   where ");
            sql.addSql("     SML_JMEIS.SAC_SID = ?");
            sql.addSql("   and");
            sql.addSql("     SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql("   and ");
            sql.addSql("     SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID ");
            sql.addSql("   and ");
            sql.addSql("     SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID ");

            if (GSConstSmail.OPKBN_UNOPENED == opkbn) {
                //未読のみ
                sql.addSql(" and");
                sql.addSql("   SML_JMEIS.SMJ_OPKBN = " + GSConstSmail.OPKBN_UNOPENED);
            } else if (GSConstSmail.OPKBN_OPENED == opkbn) {
                //既読のみ
                sql.addSql(" and");
                sql.addSql("   SML_JMEIS.SMJ_OPKBN = " + GSConstSmail.OPKBN_OPENED);
            }
            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  SML_SMEIS.SMS_MARK");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
//                    sql.addSql("  SML_JMEIS.SMJ_OPKBN asc,");
                    sql.addSql("  SML_SMEIS.SMS_TITLE");
                    sql.addSql(orderStr);
                    break;
                //送信者
                case GSConstSmail.MSG_SORT_KEY_NAME:
                    sql.addSql("  CMN_USRM_INF.USI_SEI_KN");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  CMN_USRM_INF.USI_MEI_KN");
                    sql.addSql(orderStr);
                    break;
                //送信日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
                //サイズ
                case GSConstSmail.MSG_SORT_KEY_SIZE:
                    sql.addSql("  SML_SMEIS.SMS_SIZE");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
            }

            sql.addIntValue(sacSid);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());

            pstmt =
                con.prepareStatement(
                    sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (offset > 1) {
                rs.absolute(offset - 1);
            }

            for (int i = 0; rs.next() && i < limit; i++) {
                SmailModel mdl = new SmailModel();
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smjSid"));
                mdl.setSmjOpkbn(rs.getInt("smjOpkbn"));
                mdl.setSmjSendkbn(rs.getInt("smjSendkbn"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsSize(rs.getLong("smsSize"));
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setBinCnt(rs.getInt("binCnt"));
                mdl.setBinFileSid(rs.getLong("binSid"));
                mdl.setPhotoFileDsp(rs.getInt("usiPictKf"));
                mdl.setReturnKbn(rs.getInt("smjRtnKbn"));
                mdl.setFwKbn(rs.getInt("smjFwKbn"));
                mdl.setLabelList(getSmjLabelList(rs.getInt("smjSid"), sacSid));

                ret.add(mdl);
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
     * <br>[機  能] 指定されたユーザの受信メッセージを取得する。
     * <br>[解  説] 本文も取得します。
     * <br>[備  考] midokuFlgの値によって未読のみ、全てを指定できます。
     *
     * @param sacSid アカウントSID
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @param opkbn 開封区分 0:未読のみ, 1: 既読, その他: 全て
     * @return ret 受信メッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailModel> selectJmeisListPlusBody(int sacSid,
                                                     int offset,
                                                     int limit,
                                                     int sortKey,
                                                     int orderKey,
                                                     int opkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailModel> ret = new ArrayList<SmailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SID as smjSid,");
            sql.addSql("   SML_JMEIS.SMJ_OPKBN as smjOpkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SENDKBN as smjSendkbn,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_BODY as smsBody,");
            sql.addSql("   SML_SMEIS.SMS_TYPE as smsType,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("   BINCT.CT as binCnt");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_SMEIS,");
            sql.addSql("   SML_JMEIS");
            sql.addSql("   left join");
            sql.addSql("     (");
            sql.addSql("      select");
            sql.addSql("        SML_SID,");
            sql.addSql("        count(*) as CT");
            sql.addSql("      from");
            sql.addSql("        SML_BIN");
            sql.addSql("      group by");
            sql.addSql("        SML_SID");
            sql.addSql("     ) BINCT");
            sql.addSql("   on SML_JMEIS.SMJ_SID = BINCT.SML_SID");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            if (GSConstSmail.OPKBN_UNOPENED == opkbn) {
                //未読のみ
                sql.addSql(" and");
                sql.addSql("   SML_JMEIS.SMJ_OPKBN = " + GSConstSmail.OPKBN_UNOPENED);
            } else if (GSConstSmail.OPKBN_OPENED == opkbn) {
                //既読のみ
                sql.addSql(" and");
                sql.addSql("   SML_JMEIS.SMJ_OPKBN = " + GSConstSmail.OPKBN_OPENED);
            }
            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  SML_SMEIS.SMS_MARK");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
                    sql.addSql("  SML_JMEIS.SMJ_OPKBN asc,");
                    sql.addSql("  SML_SMEIS.SMS_TITLE");
                    sql.addSql(orderStr);
                    break;
                //送信者
                case GSConstSmail.MSG_SORT_KEY_NAME:
                    sql.addSql("  CMN_USRM_INF.USI_SEI_KN");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  CMN_USRM_INF.USI_MEI_KN");
                    sql.addSql(orderStr);
                    break;
                //送信日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
            }

            sql.addIntValue(sacSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());

            pstmt =
                con.prepareStatement(
                    sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (offset > 1) {
                rs.absolute(offset - 1);
            }

            for (int i = 0; rs.next() && i < limit; i++) {
                SmailModel mdl = new SmailModel();
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smjSid"));
                mdl.setSmjOpkbn(rs.getInt("smjOpkbn"));
                mdl.setSmjSendkbn(rs.getInt("smjSendkbn"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsBody(rs.getString("smsBody"));
                mdl.setSmsType(rs.getInt("smsType"));
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setBinCnt(rs.getInt("binCnt"));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたユーザの受信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考] midokuFlgの値によって未読のみ、全てを指定できます。
     *
     * @param userSid ユーザSID
     * @param opkbn 開封区分 0:未読のみ, 1: 既読, その他: 全て
     * @return ret 受信メッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailModel> selectJmeisMainList(int userSid,
                                                     int opkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailModel> ret = new ArrayList<SmailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SID as smjSid,");
            sql.addSql("   SML_JMEIS.SMJ_OPKBN as smjOpkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SENDKBN as smjSendkbn,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_JMEIS,");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            if (GSConstSmail.OPKBN_UNOPENED == opkbn) {
                //未読のみ
                sql.addSql(" and");
                sql.addSql("   SML_JMEIS.SMJ_OPKBN = " + GSConstSmail.OPKBN_UNOPENED);
            } else if (GSConstSmail.OPKBN_OPENED == opkbn) {
                //既読のみ
                sql.addSql(" and");
                sql.addSql("   SML_JMEIS.SMJ_OPKBN = " + GSConstSmail.OPKBN_OPENED);
            }
            sql.addSql(" order by");

            String orderStr = "";
            orderStr = "  desc";

            //ソートカラム
            //送信日時
            sql.addSql("  SML_SMEIS.SMS_SDATE");
            sql.addSql(orderStr);

            pstmt =
                con.prepareStatement(
                    sql.toSqlString());

            sql.addIntValue(userSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                SmailModel mdl = new SmailModel();
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smjSid"));
                mdl.setSmjOpkbn(rs.getInt("smjOpkbn"));
                mdl.setSmjSendkbn(rs.getInt("smjSendkbn"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたユーザの受信メッセージを取得する(メイン表示用)
     * <br>[解  説]
     * <br>[備  考] midokuFlgの値によって未読のみ、全てを指定できます。
     *
     * @param sacSid アカウントSID
     * @param opkbn 開封区分 0:未読のみ, 1: 全て
     * @param sortKbn ソート区分
     * @param limitDsp メイン表示件数
     * @return ret 受信メッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailModel> selectMainDspList(int sacSid,
                                                     int opkbn,
                                                     int sortKbn,
                                                     int limitDsp)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailModel> ret = new ArrayList<SmailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SID as smjSid,");
            sql.addSql("   SML_JMEIS.SMJ_OPKBN as smjOpkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SENDKBN as smjSendkbn,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_JMEIS,");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            if (GSConstSmail.OPKBN_UNOPENED == opkbn) {
                //未読のみ
                sql.addSql(" and");
                sql.addSql("   SML_JMEIS.SMJ_OPKBN = " + GSConstSmail.OPKBN_UNOPENED);
            }
            sql.addSql(" order by");

            String orderStr = "";
            if (sortKbn == GSConstSmail.SML_MAIN_SORT_KOUJYUN) {
                orderStr = "  desc";
            } else {
                orderStr = "  asc";
            }

            //ソートカラム
            //送信日時
            sql.addSql("  SML_SMEIS.SMS_SDATE");
            sql.addSql(orderStr);
            sql.setPagingValue(0, limitDsp);

            pstmt =
                con.prepareStatement(
                    sql.toSqlString());

            sql.addIntValue(sacSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                SmailModel mdl = new SmailModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                if (!StringUtil.isNullZeroStringSpace(rs.getString("usiSei"))
                        && !StringUtil.isNullZeroStringSpace(rs.getString("usiMei"))) {
                    mdl.setAccountName(
                            rs.getString("usiSei") + " " + rs.getString("usiMei"));
                } else {
                    mdl.setAccountName(rs.getString("sacName"));
                }
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smjSid"));
                mdl.setSmjOpkbn(rs.getInt("smjOpkbn"));
                mdl.setSmjSendkbn(rs.getInt("smjSendkbn"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたユーザの受信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userSid ユーザSID
     * @return ret 受信メッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailModel> selectJmeisList(int userSid)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailModel> ret = new ArrayList<SmailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SID as smjSid,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_JMEIS,");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_OPKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql(" order by");
            sql.addSql("   SML_SMEIS.SMS_SDATE desc");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(userSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);
            sql.addIntValue(GSConstSmail.OPKBN_UNOPENED);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                SmailModel mdl = new SmailModel();
                mdl.setSmlSid(rs.getInt("smjSid"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                ret.add(mdl);
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
     * <br>[機  能] 全データハッシュ作成
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userSid ユーザSID
     * @param limit 1ページ表示件数
     * @param mailSid メールSID
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return ret 次、または前のメッセージSID
     * @throws SQLException SQL実行例外
     */
    public HashControlModel createJAllDataHash(int userSid,
                                                int limit,
                                                int mailSid,
                                                int sortKey,
                                                int orderKey)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashControlModel ret = new HashControlModel();
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SID as smjSid,");
            sql.addSql("   SML_JMEIS.SMJ_OPKBN as smjOpkbn,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_SIZE as smsSize,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_JMEIS,");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");;
            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  SML_SMEIS.SMS_MARK");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
//                    sql.addSql("  SML_JMEIS.SMJ_OPKBN asc,");
                    sql.addSql("  SML_SMEIS.SMS_TITLE");
                    sql.addSql(orderStr);
                    break;
                //送信者
                case GSConstSmail.MSG_SORT_KEY_NAME:
                    sql.addSql("  CMN_USRM_INF.USI_SEI_KN");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  CMN_USRM_INF.USI_MEI_KN");
                    sql.addSql(orderStr);
                    break;
                //送信日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
                    //サイズ
                case GSConstSmail.MSG_SORT_KEY_SIZE:
                    sql.addSql("  SML_SMEIS.SMS_SIZE");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(userSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int allOffset = 0;
            int pageOffset = 0;
            int page = 1;
            int rowNum = 0;
            HashMap<Integer, Sml030Model> map =
                new HashMap<Integer, Sml030Model>();

            while (rs.next()) {
                allOffset += 1;
                pageOffset += 1;
                if (pageOffset > limit) {
                    pageOffset = 1;
                    page += 1;
                }

                Sml030Model mdl = new Sml030Model();
                mdl.setMailSid(rs.getInt("smjSid"));
                mdl.setPageNum(page);

                if (mdl.getMailSid() == mailSid) {
                    rowNum = allOffset;
                }
                map.put(allOffset, mdl);
            }

            ret.setMap(map);
            ret.setRowNum(rowNum);

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <br>[機  能] 全データハッシュ作成
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param limit 1ページ表示件数
     * @param mailSid メールSID
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return ret 次、または前のメッセージSID
     * @throws SQLException SQL実行例外
     */
    public HashControlModel createSAllDataHash(int sacSid,
                                                int limit,
                                                int mailSid,
                                                int sortKey,
                                                int orderKey)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashControlModel ret = new HashControlModel();
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_SMEIS.SMS_SID as smsSid,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_SIZE as smsSize");
            sql.addSql(" from");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_SMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SMS_JKBN = ?");
            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  SML_SMEIS.SMS_MARK");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
                    sql.addSql("  SML_SMEIS.SMS_TITLE");
                    sql.addSql(orderStr);
                    break;
                //送信日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
                //サイズ
                case GSConstSmail.MSG_SORT_KEY_SIZE:
                    sql.addSql("  SML_SMEIS.SMS_SIZE");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int allOffset = 0;
            int pageOffset = 0;
            int page = 1;
            int rowNum = 0;
            HashMap<Integer, Sml030Model> map =
                new HashMap<Integer, Sml030Model>();

            while (rs.next()) {
                allOffset += 1;
                pageOffset += 1;
                if (pageOffset > limit) {
                    pageOffset = 1;
                    page += 1;
                }

                Sml030Model mdl = new Sml030Model();
                mdl.setMailSid(rs.getInt("smsSid"));
                mdl.setPageNum(page);

                if (mdl.getMailSid() == mailSid) {
                    rowNum = allOffset;
                }
                map.put(allOffset, mdl);
            }

            ret.setMap(map);
            ret.setRowNum(rowNum);

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <br>[機  能] 全データハッシュ作成
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailKbn メール区分
     * @param limit 1ページ表示件数
     * @param mailSid メールSID
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return ret 次、または前のメッセージSID
     * @throws SQLException SQL実行例外
     */
    public HashControlModel createGAllDataHash(int sacSid,
                                                String mailKbn,
                                                int limit,
                                                int mailSid,
                                                int sortKey,
                                                int orderKey)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashControlModel ret = new HashControlModel();
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   unionView.mailKbn as mailKbn,");
            sql.addSql("   unionView.mailSid as mailSid,");
            sql.addSql("   unionView.mailMark as mailMark,");
            sql.addSql("   unionView.mailTitle as mailTitle,");
            sql.addSql("   unionView.mailDate as mailDate,");
            sql.addSql("   unionView.mailSize as mailSize,");
            sql.addSql("   unionView.usrJkbn as usrJkbn,");
            sql.addSql("   unionView.usiSei as usiSei,");
            sql.addSql("   unionView.usiMei as usiMei");
            sql.addSql(" from");
            sql.addSql("   (");
            //受信
            sql.addSql("    select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      SML_JMEIS.SMJ_SID as mailSid,");
            sql.addSql("      SML_SMEIS.SMS_MARK as mailMark,");
            sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle,");
            sql.addSql("      SML_SMEIS.SMS_SDATE as mailDate,");
            sql.addSql("      SML_SMEIS.SMS_SIZE as mailSize,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn");
            sql.addSql("    from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("      SML_JMEIS,");
            sql.addSql("      SML_SMEIS");
            sql.addSql("    where");
            sql.addSql("      SML_JMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql("    and");
            sql.addSql("      SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql(" union all");
            //送信
            sql.addSql("    select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOSIN + " as mailKbn,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      SML_SMEIS.SMS_SID as mailSid,");
            sql.addSql("      SML_SMEIS.SMS_MARK as mailMark,");
            sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle,");
            sql.addSql("      SML_SMEIS.SMS_SDATE as mailDate,");
            sql.addSql("      SML_SMEIS.SMS_SIZE as mailSize,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn");
            sql.addSql("    from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("      SML_SMEIS");
            sql.addSql("    where");
            sql.addSql("      SML_SMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SMS_JKBN = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql(" union all");
            //草稿
            sql.addSql("    select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOKO + " as mailKbn,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      SML_WMEIS.SMW_SID as mailSid,");
            sql.addSql("      SML_WMEIS.SMW_MARK as mailMark,");
            sql.addSql("      SML_WMEIS.SMW_TITLE as mailTitle,");
            sql.addSql("      SML_WMEIS.SMW_EDATE as mailDate,");
            sql.addSql("      SML_WMEIS.SMW_SIZE as mailSize,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn");
            sql.addSql("    from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("      SML_WMEIS");
            sql.addSql("    where");
            sql.addSql("      SML_WMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_WMEIS.SMW_JKBN = ?");
            sql.addSql("    and");
            sql.addSql("      SML_WMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql("   ) unionView");

            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  unionView.mailMark");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
                    sql.addSql("  unionView.mailKbn");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  unionView.mailTitle");
                    sql.addSql(orderStr);
                    break;
                //送信者
                case GSConstSmail.MSG_SORT_KEY_NAME:
                    sql.addSql("  unionView.usiSeiKn");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  unionView.usiMeiKn");
                    sql.addSql(orderStr);
                    break;
                //日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  unionView.mailDate");
                    sql.addSql(orderStr);
                    break;
                    //サイズ
                case GSConstSmail.MSG_SORT_KEY_SIZE:
                    sql.addSql("  unionView.mailSize");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  unionView.mailDate");
                    sql.addSql(orderStr);
                    break;
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int allOffset = 0;
            int pageOffset = 0;
            int page = 1;
            int rowNum = 0;
            HashMap<Integer, Sml030Model> map =
                new HashMap<Integer, Sml030Model>();

            while (rs.next()) {
                allOffset += 1;
                pageOffset += 1;
                if (pageOffset > limit) {
                    pageOffset = 1;
                    page += 1;
                }

                Sml030Model mdl = new Sml030Model();
                mdl.setMailSid(rs.getInt("mailSid"));
                mdl.setPageNum(page);
                mdl.setMailKbn(rs.getString("mailKbn"));

                if (mdl.getMailSid() == mailSid
                    && mdl.getMailKbn().equals(mailKbn)) {
                    rowNum = allOffset;
                }
                map.put(allOffset, mdl);
            }

            ret.setMap(map);
            ret.setRowNum(rowNum);

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }


    /**
     * <br>[機  能] 全データハッシュ作成
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param lblSid ラベルSID
     * @param mailKbn メール区分
     * @param limit 1ページ表示件数
     * @param mailSid メールSID
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return ret 次、または前のメッセージSID
     * @throws SQLException SQL実行例外
     */
    public HashControlModel createLAllDataHash(int sacSid,
                                                int lblSid,
                                                String mailKbn,
                                                int limit,
                                                int mailSid,
                                                int sortKey,
                                                int orderKey)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        HashControlModel ret = new HashControlModel();
        Connection con = null;
        con = getCon();


        try {
            //SQL文
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   unionView.mailKbn as mailKbn,");
            sql.addSql("   unionView.mailSid as mailSid,");
            sql.addSql("   unionView.mailOpkbn as mailOpkbn,");
            sql.addSql("   unionView.mailMark as mailMark,");
            sql.addSql("   unionView.mailTitle as mailTitle,");
            sql.addSql("   unionView.mailDate as mailDate,");
            sql.addSql("   unionView.mailSize as mailSize,");
            sql.addSql("   unionView.sacSid as sacSid,");
            sql.addSql("   unionView.sacName as sacName,");
            sql.addSql("   unionView.sacJkbn as sacJkbn,");
            sql.addSql("   unionView.usrJkbn as usrJkbn,");
            sql.addSql("   unionView.usrSid as usrSid,");
            sql.addSql("   unionView.usiSei as usiSei,");
            sql.addSql("   unionView.usiMei as usiMei,");
            sql.addSql("   unionView.binSid as binSid,");
            sql.addSql("   unionView.usiPictKf as usiPictKf");
            sql.addSql(" from");
            sql.addSql("   (");
            //受信
            sql.addSql("    select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("      SML_JMEIS.SMJ_SID as mailSid,");
            sql.addSql("      SML_JMEIS.SMJ_OPKBN as mailOpkbn,");
            sql.addSql("      SML_SMEIS.SMS_MARK as mailMark,");
            sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle,");
            sql.addSql("      SML_SMEIS.SMS_SDATE as mailDate,");
            sql.addSql("      SML_SMEIS.SMS_SIZE as mailSize,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn,");
            sql.addSql("      CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("      CMN_USRM_INF.USI_PICT_KF as usiPictKf");

            sql.addSql("    from");
            sql.addSql("      SML_JMEIS_LABEL,");
            sql.addSql("      SML_SMEIS,");
            sql.addSql("      SML_JMEIS,");
            sql.addSql("      SML_ACCOUNT ");
            sql.addSql("        left join ");
            sql.addSql("          CMN_USRM ");
            sql.addSql("            on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("            left join ");
            sql.addSql("              CMN_USRM_INF ");
            sql.addSql("                on SML_ACCOUNT.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql("    where ");
            sql.addSql("            SML_JMEIS_LABEL.SLB_SID = ?");
            sql.addSql("        and SML_JMEIS_LABEL.SMJ_SID = SML_JMEIS.SMJ_SID");
            sql.addSql("        and SML_JMEIS.SAC_SID = ?");
            sql.addSql("        and SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql("        and SML_JMEIS_LABEL.SMJ_SID = SML_SMEIS.SMS_SID   ");
            sql.addSql("        and SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            sql.addSql(" union all");
            //送信
            sql.addSql("    select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOSIN + " as mailKbn,");
            sql.addSql("      SML_SMEIS.SMS_SID as mailSid,");
            sql.addSql("      0 as mailOpkbn,");
            sql.addSql("      SML_SMEIS.SMS_MARK as mailMark,");
            sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle,");
            sql.addSql("      SML_SMEIS.SMS_SDATE as mailDate,");
            sql.addSql("      SML_SMEIS.SMS_SIZE as mailSize,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn,");
            sql.addSql("      CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("      CMN_USRM_INF.USI_PICT_KF as usiPictKf");
            sql.addSql("    from");
            sql.addSql("      SML_SMEIS_LABEL,");
            sql.addSql("      SML_SMEIS,");
            sql.addSql("      SML_ACCOUNT ");
            sql.addSql("        left join ");
            sql.addSql("          CMN_USRM ");
            sql.addSql("        on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("         left join ");
            sql.addSql("           CMN_USRM_INF ");
            sql.addSql("         on SML_ACCOUNT.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql("    where");
            sql.addSql("      SML_SMEIS_LABEL.SLB_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS_LABEL.SMS_SID = SML_SMEIS.SMS_SID");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SMS_JKBN = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

//            sql.addSql(" union all");
//            //草稿
//            sql.addSql("    select ");
//            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOKO + " as mailKbn,");
//            sql.addSql("      SML_WMEIS.SMW_SID as mailSid,");
//            sql.addSql("      0 as mailOpkbn,");
//            sql.addSql("      SML_WMEIS.SMW_MARK as mailMark,");
//            sql.addSql("      SML_WMEIS.SMW_TITLE as mailTitle,");
//            sql.addSql("      SML_WMEIS.SMW_EDATE as mailDate,");
//            sql.addSql("      SML_WMEIS.SMW_SIZE as mailSize,");
//            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
//            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
//            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
//            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
//            sql.addSql("      CMN_USRM_INF.USR_SID as usrSid,");
//            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
//            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
//            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
//            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn,");
//            sql.addSql("      (select ");
//            sql.addSql("         count(BIN_SID)");
//            sql.addSql("       from ");
//            sql.addSql("         SML_BIN");
//            sql.addSql("       where SML_BIN.SML_SID = SML_WMEIS.SMW_SID");
//            sql.addSql("      ) as binCnt,");
//            sql.addSql("      CMN_USRM_INF.BIN_SID as binSid,");
//            sql.addSql("      CMN_USRM_INF.USI_PICT_KF as usiPictKf");
//
//            sql.addSql("    from");
//            sql.addSql("      SML_WMEIS_LABEL,");
//            sql.addSql("      SML_WMEIS,");
//            sql.addSql("      SML_ACCOUNT ");
//            sql.addSql("        left join ");
//            sql.addSql("          CMN_USRM ");
//            sql.addSql("        on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
//            sql.addSql("         left join ");
//            sql.addSql("           CMN_USRM_INF ");
//            sql.addSql("         on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
//            sql.addSql("    where");
//            sql.addSql("        SML_WMEIS_LABEL.SLB_SID = ?");
//            sql.addSql("      and");
//            sql.addSql("        SML_WMEIS_LABEL.SMW_SID = SML_WMEIS.SMW_SID ");
//            sql.addSql("      and");
//            sql.addSql("        SML_WMEIS.SAC_SID = ?");
//            sql.addSql("      and");
//            sql.addSql("        SML_WMEIS.SMW_JKBN = ?");
//            sql.addSql("      and");
//            sql.addSql("        SML_WMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql("   ) unionView");

            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  unionView.mailMark");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
                    sql.addSql("  unionView.mailKbn");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  unionView.mailTitle");
                    sql.addSql(orderStr);
                    break;
                //送信者
                case GSConstSmail.MSG_SORT_KEY_NAME:
                    sql.addSql("  unionView.usiSeiKn");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  unionView.usiMeiKn");
                    sql.addSql(orderStr);
                    break;
                //日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  unionView.mailDate");
                    sql.addSql(orderStr);
                    break;
                //サイズ
                case GSConstSmail.MSG_SORT_KEY_SIZE:
                    sql.addSql("  unionView.mailSize");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  unionView.mailDate");
                    sql.addSql(orderStr);
                    break;
            }

            pstmt =
                con.prepareStatement(
                    sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sql.addIntValue(lblSid);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
            sql.addIntValue(lblSid);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
//            sql.addIntValue(lblSid);
//            sql.addIntValue(sacSid);
//            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int allOffset = 0;
            int pageOffset = 0;
            int page = 1;
            int rowNum = 0;
            HashMap<Integer, Sml030Model> map =
                new HashMap<Integer, Sml030Model>();

            while (rs.next()) {
                allOffset += 1;
                pageOffset += 1;
                if (pageOffset > limit) {
                    pageOffset = 1;
                    page += 1;
                }

                Sml030Model mdl = new Sml030Model();
                mdl.setMailSid(rs.getInt("mailSid"));
                mdl.setPageNum(page);
                mdl.setMailKbn(rs.getString("mailKbn"));

                if (mdl.getMailSid() == mailSid
                    && mdl.getMailKbn().equals(mailKbn)) {
                    rowNum = allOffset;
                }
                map.put(allOffset, mdl);
            }

            ret.setMap(map);
            ret.setRowNum(rowNum);

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <br>[機  能] 指定されたメールSIDの受信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @param jtkbn 状態区分
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectJmeisDetail(int sacSid,
                                                          int mailSid,
                                                          int jtkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SID as smjSid,");
            sql.addSql("   SML_JMEIS.SMJ_OPKBN as smjOpkbn,");
            sql.addSql("   SML_JMEIS.SMJ_OPDATE as smjOpDate,");
            sql.addSql("   SML_JMEIS.SMJ_SENDKBN as smjSendkbn,");
            sql.addSql("   SML_JMEIS.SMJ_RTN_KBN as smjRtnkbn,");
            sql.addSql("   SML_JMEIS.SMJ_FW_KBN as smjFwkbn,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_BODY as smsBody,");
            sql.addSql("   SML_SMEIS.SMS_TYPE as smsType,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("   CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("   CMN_USRM_INF.USI_PICT_KF as usiPictKf");

            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_JMEIS,");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(mailSid);
            sql.addIntValue(jtkbn);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smjSid"));
                mdl.setSmjOpkbn(rs.getInt("smjOpkbn"));
                mdl.setSmjOpDate(UDate.getInstanceTimestamp(rs.getTimestamp("smjOpDate")));
                mdl.setSmjSendkbn(rs.getInt("smjSendkbn"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsBody(rs.getString("smsBody"));
                mdl.setSmsType(rs.getInt("smsType"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setAtesakiList(getAtesakiList(mdl.getSmlSid()));
                mdl.setBinFileSid(rs.getLong("binSid"));
                mdl.setPhotoFileDsp(rs.getInt("usiPictKf"));
                mdl.setReturnKbn(rs.getInt("smjRtnKbn"));
                mdl.setFwKbn(rs.getInt("smjFwKbn"));

                ret.add(mdl);
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
     * <br>[機  能] 指定されたメールSIDの受信メッセージを取得する（全返信押下時）
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @param jtkbn 状態区分
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectJmeisDetail2(int sacSid,
                                                          int mailSid,
                                                          int jtkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SID as smjSid,");
            sql.addSql("   SML_JMEIS.SMJ_OPKBN as smjOpkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SENDKBN as smjSendkbn,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_BODY as smsBody,");
            sql.addSql("   SML_SMEIS.SMS_TYPE as smsType,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_JMEIS,");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(mailSid);
            sql.addIntValue(jtkbn);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smjSid"));
                mdl.setSmjOpkbn(rs.getInt("smjOpkbn"));
                mdl.setSmjSendkbn(rs.getInt("smjSendkbn"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsBody(rs.getString("smsBody"));
                mdl.setSmsType(rs.getInt("smsType"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setAtesakiList(getAtesakiList2(mdl.getSmlSid()));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたメールSIDの受信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public SmailDetailModel selectJmeisDetail2(int sacSid,
                                                             int mailSid)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        SmailDetailModel ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SID as smjSid,");
            sql.addSql("   SML_JMEIS.SMJ_OPKBN as smjOpkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SENDKBN as smjSendkbn,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_BODY as smsBody,");
            sql.addSql("   SML_SMEIS.SMS_TYPE as smsType,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_JMEIS,");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(mailSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = new SmailDetailModel();
                ret.setAccountSid(rs.getInt("sacSid"));
                ret.setAccountName(rs.getString("sacName"));
                ret.setAccountJkbn(rs.getInt("sacJkbn"));
                ret.setSmlSid(rs.getInt("smjSid"));
                ret.setSmjOpkbn(rs.getInt("smjOpkbn"));
                ret.setSmjSendkbn(rs.getInt("smjSendkbn"));
                ret.setSmsMark(rs.getInt("smsMark"));
                ret.setSmsTitle(rs.getString("smsTitle"));
                ret.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                ret.setSmsBody(rs.getString("smsBody"));
                ret.setSmsType(rs.getInt("smsType"));
                ret.setUsrSid(rs.getInt("usrSid"));
                ret.setUsrJkbn(rs.getInt("usrJkbn"));
                ret.setUsiSei(rs.getString("usiSei"));
                ret.setUsiMei(rs.getString("usiMei"));
                ret.setAtesakiList(getAtesakiList(ret.getSmlSid()));
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
     * <br>[機  能] 指定されたメールSIDの送信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @param jtkbn 状態区分
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectSmeisDetail(int sacSid,
                                                          int mailSid,
                                                          int jtkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOSIN + " as mailKbn,");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_SMEIS.SMS_SID as smsSid,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_BODY as smsBody,");
            sql.addSql("   SML_SMEIS.SMS_TYPE as smsType,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("   CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("   CMN_USRM_INF.USI_PICT_KF as usiPictKf");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_SMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SMS_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SMS_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(mailSid);
            sql.addIntValue(jtkbn);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smsSid"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsBody(rs.getString("smsBody"));
                mdl.setSmsType(rs.getInt("smsType"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setAtesakiList(getAtesakiList(mdl.getSmlSid()));
                mdl.setBinFileSid(rs.getLong("binSid"));
                mdl.setPhotoFileDsp(rs.getInt("usiPictKf"));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたメールSIDの送信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @param jtkbn 状態区分
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectSmeisDetail2(int sacSid,
                                                          int mailSid,
                                                          int jtkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOSIN + " as mailKbn,");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_SMEIS.SMS_SID as smsSid,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_BODY as smsBody,");
            sql.addSql("   SML_SMEIS.SMS_TYPE as smsType,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_SMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SMS_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SMS_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(mailSid);
            sql.addIntValue(jtkbn);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smsSid"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsBody(rs.getString("smsBody"));
                mdl.setSmsType(rs.getInt("smsType"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setAtesakiList(getAtesakiList2(mdl.getSmlSid()));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたメールSIDの送信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectSmeisDetailFromSid(int sacSid,
                                                                 int mailSid)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_SMEIS.SMS_SID as smsSid,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_BODY as smsBody,");
            sql.addSql("   SML_SMEIS.SMS_TYPE as smsType,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_SMEIS.SMS_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(mailSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setSmlSid(rs.getInt("smsSid"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsBody(rs.getString("smsBody"));
                mdl.setSmsType(rs.getInt("smsType"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setAtesakiList(getAtesakiList(mdl.getSmlSid()));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたメールSIDの送信メッセージを取得する(返信時)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @param foward true:転送 false:返信
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectSmeisDetailFromSid2(int sacSid,
                                                                 int mailSid,
                                                                 boolean foward)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_SMEIS.SMS_SID as smsSid,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_BODY as smsBody,");
            sql.addSql("   SML_SMEIS.SMS_TYPE as smsType,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_SMEIS.SMS_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            if (!foward) {
                //転送の場合、削除ユーザも対象とする
                sql.addSql(" and ");
                sql.addSql("  SML_ACCOUNT.SAC_JKBN = 0");
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(mailSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setSmlSid(rs.getInt("smsSid"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsBody(rs.getString("smsBody"));
                mdl.setSmsType(rs.getInt("smsType"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setAtesakiList(getAtesakiList2(mdl.getSmlSid()));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたメールSIDの送信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mailSid メールSID
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectSmeisDetailFromSid(int mailSid)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_SMEIS.SMS_SID as smsSid,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("   SML_SMEIS.SMS_BODY as smsBody,");
            sql.addSql("   SML_SMEIS.SMS_TYPE as smsType,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_SMEIS.SMS_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(mailSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setSmlSid(rs.getInt("smsSid"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsBody(rs.getString("smsBody"));
                mdl.setSmsType(rs.getInt("smsType"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setAtesakiList(getAtesakiList(mdl.getSmlSid()));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたメールSIDの下書きメッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @param jtkbn 状態区分
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectWmeisDetail(int sacSid,
                                                          int mailSid,
                                                          int jtkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOKO + " as mailKbn,");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_WMEIS.SMW_SID as smwSid,");
            sql.addSql("   SML_WMEIS.SMW_MARK as smwMark,");
            sql.addSql("   SML_WMEIS.SMW_BODY as smwBody,");
            sql.addSql("   SML_WMEIS.SMW_TYPE as smwType,");
            sql.addSql("   SML_WMEIS.SMW_TITLE as smwTitle,");
            sql.addSql("   SML_WMEIS.SMW_EDATE as smwEdate,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("   CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("   CMN_USRM_INF.USI_PICT_KF as usiPictKf");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_WMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_WMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_WMEIS.SMW_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_WMEIS.SMW_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_WMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sacSid);
            sql.addIntValue(mailSid);
            sql.addIntValue(jtkbn);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smwSid"));
                mdl.setSmsMark(rs.getInt("smwMark"));
                mdl.setSmsTitle(rs.getString("smwTitle"));
                mdl.setSmsBody(rs.getString("smwBody"));
                mdl.setSmsType(rs.getInt("smwType"));
                mdl.setSmsEdate(UDate.getInstanceTimestamp(rs.getTimestamp("smwEdate")));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setAtesakiList(getSitagakiAtesakiList(mdl.getSmlSid(),
                        GSConstSmail.SML_SEND_KBN_ATESAKI));
                mdl.setBinFileSid(rs.getLong("binSid"));
                mdl.setPhotoFileDsp(rs.getInt("usiPictKf"));

                ret.add(mdl);
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
     * <br>[機  能] 指定されたユーザの送信メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return ret 受信メッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList < SmailModel > selectSmeisList(int sacSid,
                                                     int offset,
                                                     int limit,
                                                     int sortKey,
                                                     int orderKey)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailModel> ret = new ArrayList<SmailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOSIN + " as mailKbn,");
            sql.addSql("   SML_SMEIS.SMS_SID as smsSid,");
            sql.addSql("   SML_SMEIS.SMS_MARK as smsMark,");
            sql.addSql("   SML_SMEIS.SMS_TITLE as smsTitle,");
            sql.addSql("   SML_SMEIS.SMS_SIZE as smsSize,");
            sql.addSql("   SML_SMEIS.SMS_SDATE as smsSdate,");
            sql.addSql("     (");
            sql.addSql("       select");
            sql.addSql("         count(BIN_SID)");
            sql.addSql("       from");
            sql.addSql("         SML_BIN");
            sql.addSql("       where ");
            sql.addSql("          SML_SID = SML_SMEIS.SMS_SID");
            sql.addSql("     )  as binCnt");
            sql.addSql(" from");
            sql.addSql("   SML_SMEIS");

            sql.addSql(" where ");
            sql.addSql("   SML_SMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS.SMS_JKBN = ?");
            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  SML_SMEIS.SMS_MARK");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
                    sql.addSql("  SML_SMEIS.SMS_TITLE");
                    sql.addSql(orderStr);
                    break;
                //送信日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
                    //サイズ
                case GSConstSmail.MSG_SORT_KEY_SIZE:
                    sql.addSql("  SML_SMEIS.SMS_SIZE");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  SML_SMEIS.SMS_SDATE");
                    sql.addSql(orderStr);
                    break;
            }

            pstmt =
                con.prepareStatement(
                    sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            sql.addIntValue(sacSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (offset > 1) {
                rs.absolute(offset - 1);
            }

            for (int i = 0; rs.next() && i < limit; i++) {
                SmailModel mdl = new SmailModel();
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smsSid"));
                mdl.setSmsMark(rs.getInt("smsMark"));
                mdl.setSmsTitle(rs.getString("smsTitle"));
                mdl.setBinCnt(rs.getInt("binCnt"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smsSdate")));
                mdl.setSmsSize(rs.getLong("smsSize"));
                mdl.setAtesakiList(getAtesakiList(mdl.getSmlSid(),
                        GSConstSmail.SML_SEND_KBN_ATESAKI));
                mdl.setLabelList(getSmsLabelList(rs.getInt("smsSid"), sacSid));

                ret.add(mdl);
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
     * <br>[機  能] 指定されたユーザの下書きメッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return ret 下書きメッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailModel> selectWmeisList(int sacSid,
                                                     int offset,
                                                     int limit,
                                                     int sortKey,
                                                     int orderKey)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailModel> ret = new ArrayList<SmailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOKO + " as mailKbn,");
            sql.addSql("   SML_WMEIS.SMW_SID as smwSid,");
            sql.addSql("   SML_WMEIS.SMW_MARK as smwMark,");
            sql.addSql("   SML_WMEIS.SMW_TITLE as smwTitle,");
            sql.addSql("   SML_WMEIS.SMW_EDATE as smwEdate,");
            sql.addSql("   SML_WMEIS.SMW_SIZE as smwSize,");
            sql.addSql("     (select ");
            sql.addSql("       count(BIN_SID) ");
            sql.addSql("     from ");
            sql.addSql("       SML_BIN");
            sql.addSql("     where ");
            sql.addSql("       SML_SID = SML_WMEIS.SMW_SID");
            sql.addSql("     ) as binCnt");
            sql.addSql(" from");
            sql.addSql("   SML_WMEIS");

            sql.addSql(" where ");
            sql.addSql("   SML_WMEIS.SAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_WMEIS.SMW_JKBN = ?");
            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  SML_WMEIS.SMW_MARK");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
                    sql.addSql("  SML_WMEIS.SMW_TITLE");
                    sql.addSql(orderStr);
                    break;
                //更新日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  SML_WMEIS.SMW_EDATE");
                    sql.addSql(orderStr);
                    break;
                //サイズ
                case GSConstSmail.MSG_SORT_KEY_SIZE:
                    sql.addSql("  SML_WMEIS.SMW_SIZE");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  SML_WMEIS.SMW_EDATE");
                    sql.addSql(orderStr);
                    break;
            }

            pstmt =
                con.prepareStatement(
                    sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            sql.addIntValue(sacSid);
            sql.addIntValue(GSConst.JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (offset > 1) {
                rs.absolute(offset - 1);
            }

            for (int i = 0; rs.next() && i < limit; i++) {
                SmailModel mdl = new SmailModel();
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("smwSid"));
                mdl.setSmsMark(rs.getInt("smwMark"));
                mdl.setSmsTitle(rs.getString("smwTitle"));
                mdl.setSmsSize(rs.getLong("smwSize"));
                mdl.setBinCnt(rs.getInt("binCnt"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("smwEdate")));
                mdl.setAtesakiList(getSitagakiAtesakiList(mdl.getSmlSid(),
                        GSConstSmail.SML_SEND_KBN_ATESAKI));
                mdl.setLabelList(getSmwLabelList(rs.getInt("smwSid"), sacSid));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたユーザのゴミ箱メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return ret ゴミ箱メッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailModel> selectGomibakoList(int sacSid,
                                                     int offset,
                                                     int limit,
                                                     int sortKey,
                                                     int orderKey)
        throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        ArrayList<SmailModel> ret = new ArrayList<SmailModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   unionView.mailKbn as mailKbn,");
            sql.addSql("   unionView.smjOpkbn as smjOpkbn,");
            sql.addSql("   unionView.mailSid as mailSid,");
            sql.addSql("   unionView.mailMark as mailMark,");
            sql.addSql("   unionView.mailTitle as mailTitle,");
            sql.addSql("   unionView.mailDate as mailDate,");
            sql.addSql("   unionView.mailSize as mailSize,");
            sql.addSql("   unionView.sacSid as sacSid,");
            sql.addSql("   unionView.sacName as sacName,");
            sql.addSql("   unionView.sacJkbn as sacJkbn,");
            sql.addSql("   unionView.usrJkbn as usrJkbn,");
            sql.addSql("   unionView.usrSid as usrSid,");
            sql.addSql("   unionView.usiSei as usiSei,");
            sql.addSql("   unionView.usiMei as usiMei,");
            sql.addSql("   unionView.binCnt as binCnt,");
            sql.addSql("   unionView.binSid as binSid,");
            sql.addSql("   unionView.usiPictKf as usiPictKf");
            sql.addSql(" from");
            sql.addSql("   (");
            //受信
            sql.addSql("    select ");
            sql.addSql("      " + GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("      SML_JMEIS.SMJ_SID as mailSid,");
            sql.addSql("      SML_JMEIS.SMJ_OPKBN as smjOpkbn, ");
            sql.addSql("      SML_SMEIS.SMS_MARK as mailMark,");
            sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle,");
            sql.addSql("      SML_SMEIS.SMS_SDATE as mailDate,");
            sql.addSql("      SML_SMEIS.SMS_SIZE as mailSize,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn,");
            sql.addSql("      BINCT.CT as binCnt,");
            sql.addSql("      CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("      CMN_USRM_INF.USI_PICT_KF as usiPictKf");

            sql.addSql("    from");
            sql.addSql("      SML_ACCOUNT ");
            sql.addSql("        left join ");
            sql.addSql("          CMN_USRM ");
            sql.addSql("        on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("         left join ");
            sql.addSql("           CMN_USRM_INF ");
            sql.addSql("         on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("      SML_SMEIS,");
            sql.addSql("      SML_JMEIS");
            sql.addSql("      left join");
            sql.addSql("        (");
            sql.addSql("         select");
            sql.addSql("          SML_SID,");
            sql.addSql("          count(BIN_SID) as CT");
            sql.addSql("         from");
            sql.addSql("          SML_BIN");
            sql.addSql("         where");
            sql.addSql("          SML_SID in (");
            sql.addSql("           select SMJ_SID from SML_JMEIS where SAC_SID = ?");
            sql.addSql("           and SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql("          )");
            sql.addSql("         group by SML_SID");
            sql.addSql("        ) BINCT");
            sql.addSql("      on");
            sql.addSql("        SML_JMEIS.SMJ_SID = BINCT.SML_SID");
            sql.addSql("    where");
            sql.addSql("      SML_JMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql("    and");
            sql.addSql("      SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");

            sql.addSql(" union all");
            //送信
            sql.addSql("    select ");
            sql.addSql("      " + GSConstSmail.TAB_DSP_MODE_SOSIN + " as mailKbn,");
            sql.addSql("      SML_SMEIS.SMS_SID as mailSid,");
            sql.addSql("      0 as smjOpkbn,");
            sql.addSql("      SML_SMEIS.SMS_MARK as mailMark,");
            sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle,");
            sql.addSql("      SML_SMEIS.SMS_SDATE as mailDate,");
            sql.addSql("      SML_SMEIS.SMS_SIZE as mailSize,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn,");
            sql.addSql("      BINCT.CT as binCnt,");
            sql.addSql("      CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("      CMN_USRM_INF.USI_PICT_KF as usiPictKf");

            sql.addSql("    from");
            sql.addSql("      SML_ACCOUNT ");
            sql.addSql("        left join ");
            sql.addSql("          CMN_USRM ");
            sql.addSql("        on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("         left join ");
            sql.addSql("           CMN_USRM_INF ");
            sql.addSql("         on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("      SML_SMEIS");
            sql.addSql("      left join");
            sql.addSql("        (");
            sql.addSql("         select");
            sql.addSql("          SML_SID,");
            sql.addSql("          count(BIN_SID) as CT");
            sql.addSql("         from");
            sql.addSql("          SML_BIN");
            sql.addSql("         where");
            sql.addSql("          SML_SID in (");
            sql.addSql("           select SMS_SID from SML_SMEIS where SAC_SID = ?");
            sql.addSql("           and SML_SMEIS.SMS_JKBN = ?");
            sql.addSql("          )");
            sql.addSql("         group by SML_SID");
            sql.addSql("        ) BINCT");
            sql.addSql("      on");
            sql.addSql("        SML_SMEIS.SMS_SID = BINCT.SML_SID");
            sql.addSql("    where");
            sql.addSql("      SML_SMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SMS_JKBN = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            sql.addSql(" union all");
            //草稿
            sql.addSql("    select ");
            sql.addSql("      " + GSConstSmail.TAB_DSP_MODE_SOKO + " as mailKbn,");
            sql.addSql("      SML_WMEIS.SMW_SID as mailSid,");
            sql.addSql("      0 as smjOpkbn,");
            sql.addSql("      SML_WMEIS.SMW_MARK as mailMark,");
            sql.addSql("      SML_WMEIS.SMW_TITLE as mailTitle,");
            sql.addSql("      SML_WMEIS.SMW_EDATE as mailDate,");
            sql.addSql("      SML_WMEIS.SMW_SIZE as mailSize,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn,");
            sql.addSql("      BINCT.CT as binCnt,");
            sql.addSql("      CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("      CMN_USRM_INF.USI_PICT_KF as usiPictKf");

            sql.addSql("    from");
            sql.addSql("      SML_ACCOUNT ");
            sql.addSql("        left join ");
            sql.addSql("          CMN_USRM ");
            sql.addSql("        on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("         left join ");
            sql.addSql("           CMN_USRM_INF ");
            sql.addSql("         on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("      SML_WMEIS");
            sql.addSql("      left join");
            sql.addSql("        (");
            sql.addSql("         select");
            sql.addSql("          SML_SID,");
            sql.addSql("          count(BIN_SID) as CT");
            sql.addSql("         from");
            sql.addSql("          SML_BIN");
            sql.addSql("         where");
            sql.addSql("          SML_SID in (");
            sql.addSql("           select SMW_SID from SML_WMEIS where SAC_SID = ?");
            sql.addSql("           and SML_WMEIS.SMW_JKBN = ?");
            sql.addSql("          )");
            sql.addSql("         group by SML_SID");
            sql.addSql("        ) BINCT");
            sql.addSql("      on");
            sql.addSql("        SML_WMEIS.SMW_SID = BINCT.SML_SID");
            sql.addSql("    where");
            sql.addSql("      SML_WMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_WMEIS.SMW_JKBN = ?");
            sql.addSql("    and");
            sql.addSql("      SML_WMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql("   ) unionView");

            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  unionView.mailMark");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
                    sql.addSql("  unionView.mailKbn");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  unionView.mailTitle");
                    sql.addSql(orderStr);
                    break;
                //送信者
                case GSConstSmail.MSG_SORT_KEY_NAME:
                    sql.addSql("  unionView.usiSeiKn");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  unionView.usiMeiKn");
                    sql.addSql(orderStr);
                    break;
                //日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  unionView.mailDate");
                    sql.addSql(orderStr);
                    break;
                //サイズ
                case GSConstSmail.MSG_SORT_KEY_SIZE:
                    sql.addSql("  unionView.mailSize");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  unionView.mailDate");
                    sql.addSql(orderStr);
                    break;
            }

            pstmt =
                con.prepareStatement(
                    sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (offset > 1) {
                rs.absolute(offset - 1);
            }

            for (int i = 0; rs.next() && i < limit; i++) {
                SmailModel mdl = new SmailModel();
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmjOpkbn(rs.getInt("smjOpkbn"));
                mdl.setSmlSid(rs.getInt("mailSid"));
                mdl.setSmsMark(rs.getInt("mailMark"));
                mdl.setSmsTitle(rs.getString("mailTitle"));
                mdl.setSmsSize(rs.getLong("mailSize"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("mailDate")));
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setBinCnt(rs.getInt("binCnt"));
                mdl.setBinFileSid(rs.getLong("binSid"));
                mdl.setPhotoFileDsp(rs.getInt("usiPictKf"));

                if (rs.getString("mailKbn").equals(GSConstSmail.TAB_DSP_MODE_JUSIN)) {
                    mdl.setLabelList(getSmjLabelList(rs.getInt("mailSid"), sacSid));
                } else if (rs.getString("mailKbn").equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
                    mdl.setLabelList(getSmsLabelList(rs.getInt("mailSid"), sacSid));
                } else {
                    mdl.setLabelList(getSmwLabelList(rs.getInt("mailSid"), sacSid));
                }

                ret.add(mdl);
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
     * <br>[機  能] 指定されたアカウントの指定ラベルのメッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param lblSid ラベルSID
     * @param offset レコードの読取開始行
     * @param limit 1ページの最大件数
     * @param sortKey ソートキー
     * @param orderKey オーダーキー
     * @return ret ゴミ箱メッセージ一覧リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailModel> selectLabelList(int sacSid,
                                                     int lblSid,
                                                     int offset,
                                                     int limit,
                                                     int sortKey,
                                                     int orderKey)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        ArrayList<SmailModel> ret = new ArrayList<SmailModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   unionView.mailKbn as mailKbn,");
            sql.addSql("   unionView.mailSid as mailSid,");
            sql.addSql("   unionView.mailOpkbn as mailOpkbn,");
            sql.addSql("   unionView.mailMark as mailMark,");
            sql.addSql("   unionView.mailTitle as mailTitle,");
            sql.addSql("   unionView.mailDate as mailDate,");
            sql.addSql("   unionView.mailSize as mailSize,");
            sql.addSql("   unionView.sacSid as sacSid,");
            sql.addSql("   unionView.sacName as sacName,");
            sql.addSql("   unionView.sacJkbn as sacJkbn,");
            sql.addSql("   unionView.usrJkbn as usrJkbn,");
            sql.addSql("   unionView.usrSid as usrSid,");
            sql.addSql("   unionView.usiSei as usiSei,");
            sql.addSql("   unionView.usiMei as usiMei,");
            sql.addSql("   unionView.binCnt as binCnt,");
            sql.addSql("   unionView.binSid as binSid,");
            sql.addSql("   unionView.usiPictKf as usiPictKf");
            sql.addSql(" from");
            sql.addSql("   (");
            //受信
            sql.addSql("    select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
            sql.addSql("      SML_JMEIS.SMJ_SID as mailSid,");
            sql.addSql("      SML_JMEIS.SMJ_OPKBN as mailOpkbn,");
            sql.addSql("      SML_SMEIS.SMS_MARK as mailMark,");
            sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle,");
            sql.addSql("      SML_SMEIS.SMS_SDATE as mailDate,");
            sql.addSql("      SML_SMEIS.SMS_SIZE as mailSize,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn,");
            sql.addSql("      (select ");
            sql.addSql("         count(BIN_SID)");
            sql.addSql("       from ");
            sql.addSql("         SML_BIN");
            sql.addSql("       where SML_BIN.SML_SID = SML_JMEIS.SMJ_SID");
            sql.addSql("      ) as binCnt,");
            sql.addSql("      CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("      CMN_USRM_INF.USI_PICT_KF as usiPictKf");

            sql.addSql("    from");
            sql.addSql("      SML_JMEIS_LABEL,");
            sql.addSql("      SML_SMEIS,");
            sql.addSql("      SML_JMEIS,");
            sql.addSql("      SML_ACCOUNT ");
            sql.addSql("        left join ");
            sql.addSql("          CMN_USRM ");
            sql.addSql("            on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("            left join ");
            sql.addSql("              CMN_USRM_INF ");
            sql.addSql("                on SML_ACCOUNT.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql("    where ");
            sql.addSql("            SML_JMEIS_LABEL.SLB_SID = ?");
            sql.addSql("        and SML_JMEIS_LABEL.SMJ_SID = SML_JMEIS.SMJ_SID");
            sql.addSql("        and SML_JMEIS.SAC_SID = ?");
            sql.addSql("        and SML_JMEIS.SMJ_JKBN = ?");
            sql.addSql("        and SML_JMEIS_LABEL.SMJ_SID = SML_SMEIS.SMS_SID   ");
            sql.addSql("        and SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            sql.addSql(" union all");
            //送信
            sql.addSql("    select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOSIN + " as mailKbn,");
            sql.addSql("      SML_SMEIS.SMS_SID as mailSid,");
            sql.addSql("      0 as mailOpkbn,");
            sql.addSql("      SML_SMEIS.SMS_MARK as mailMark,");
            sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle,");
            sql.addSql("      SML_SMEIS.SMS_SDATE as mailDate,");
            sql.addSql("      SML_SMEIS.SMS_SIZE as mailSize,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn,");
            sql.addSql("      (select ");
            sql.addSql("         count(BIN_SID)");
            sql.addSql("       from ");
            sql.addSql("         SML_BIN");
            sql.addSql("       where SML_BIN.SML_SID = SML_SMEIS.SMS_SID");
            sql.addSql("      ) as binCnt,");
            sql.addSql("      CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("      CMN_USRM_INF.USI_PICT_KF as usiPictKf");
            sql.addSql("    from");
            sql.addSql("      SML_SMEIS_LABEL,");
            sql.addSql("      SML_SMEIS,");
            sql.addSql("      SML_ACCOUNT ");
            sql.addSql("        left join ");
            sql.addSql("          CMN_USRM ");
            sql.addSql("        on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("         left join ");
            sql.addSql("           CMN_USRM_INF ");
            sql.addSql("         on SML_ACCOUNT.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql("    where");
            sql.addSql("      SML_SMEIS_LABEL.SLB_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS_LABEL.SMS_SID = SML_SMEIS.SMS_SID");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SMS_JKBN = ?");
            sql.addSql("    and");
            sql.addSql("      SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");

            sql.addSql(" union all");
            //草稿
            sql.addSql("    select ");
            sql.addSql(GSConstSmail.TAB_DSP_MODE_SOKO + " as mailKbn,");
            sql.addSql("      SML_WMEIS.SMW_SID as mailSid,");
            sql.addSql("      0 as mailOpkbn,");
            sql.addSql("      SML_WMEIS.SMW_MARK as mailMark,");
            sql.addSql("      SML_WMEIS.SMW_TITLE as mailTitle,");
            sql.addSql("      SML_WMEIS.SMW_EDATE as mailDate,");
            sql.addSql("      SML_WMEIS.SMW_SIZE as mailSize,");
            sql.addSql("      SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("      SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("      SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("      CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("      CMN_USRM_INF.USR_SID as usrSid,");
            sql.addSql("      CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("      CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("      CMN_USRM_INF.USI_SEI_KN as usiSeiKn,");
            sql.addSql("      CMN_USRM_INF.USI_MEI_KN as usiMeiKn,");
            sql.addSql("      (select ");
            sql.addSql("         count(BIN_SID)");
            sql.addSql("       from ");
            sql.addSql("         SML_BIN");
            sql.addSql("       where SML_BIN.SML_SID = SML_WMEIS.SMW_SID");
            sql.addSql("      ) as binCnt,");
            sql.addSql("      CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("      CMN_USRM_INF.USI_PICT_KF as usiPictKf");

            sql.addSql("    from");
            sql.addSql("      SML_WMEIS_LABEL,");
            sql.addSql("      SML_WMEIS,");
            sql.addSql("      SML_ACCOUNT ");
            sql.addSql("        left join ");
            sql.addSql("          CMN_USRM ");
            sql.addSql("        on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("         left join ");
            sql.addSql("           CMN_USRM_INF ");
            sql.addSql("         on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql("    where");
            sql.addSql("        SML_WMEIS_LABEL.SLB_SID = ?");
            sql.addSql("      and");
            sql.addSql("        SML_WMEIS_LABEL.SMW_SID = SML_WMEIS.SMW_SID ");
            sql.addSql("      and");
            sql.addSql("        SML_WMEIS.SAC_SID = ?");
            sql.addSql("      and");
            sql.addSql("        SML_WMEIS.SMW_JKBN = ?");
            sql.addSql("      and");
            sql.addSql("        SML_WMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql("   ) unionView");

            sql.addSql(" order by");

            String orderStr = "";
            //オーダー
            if (orderKey == GSConstSmail.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (orderKey == GSConstSmail.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            //ソートカラム
            switch (sortKey) {
                //マーク区分
                case GSConstSmail.MSG_SORT_KEY_MARK:
                    sql.addSql("  unionView.mailMark");
                    sql.addSql(orderStr);
                    break;
                //件名
                case GSConstSmail.MSG_SORT_KEY_TITLE:
                    sql.addSql("  unionView.mailKbn");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  unionView.mailTitle");
                    sql.addSql(orderStr);
                    break;
                //送信者
                case GSConstSmail.MSG_SORT_KEY_NAME:
                    sql.addSql("  unionView.usiSeiKn");
                    sql.addSql(orderStr);
                    sql.addSql(",");
                    sql.addSql("  unionView.usiMeiKn");
                    sql.addSql(orderStr);
                    break;
                //日時
                case GSConstSmail.MSG_SORT_KEY_DATE:
                    sql.addSql("  unionView.mailDate");
                    sql.addSql(orderStr);
                    break;
                //サイズ
                case GSConstSmail.MSG_SORT_KEY_SIZE:
                    sql.addSql("  unionView.mailSize");
                    sql.addSql(orderStr);
                    break;
                default:
                    sql.addSql("  unionView.mailDate");
                    sql.addSql(orderStr);
                    break;
            }

            pstmt =
                con.prepareStatement(
                    sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sql.addIntValue(lblSid);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
            sql.addIntValue(lblSid);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
            sql.addIntValue(lblSid);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);

            //ログを出力
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (offset > 1) {
                rs.absolute(offset - 1);
            }

            for (int i = 0; rs.next() && i < limit; i++) {
                SmailModel mdl = new SmailModel();
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmlSid(rs.getInt("mailSid"));
                mdl.setSmjOpkbn(rs.getInt("mailOpkbn"));
                mdl.setSmsMark(rs.getInt("mailMark"));
                mdl.setSmsTitle(rs.getString("mailTitle"));
                mdl.setSmsSize(rs.getLong("mailSize"));
                mdl.setSmsSdate(UDate.getInstanceTimestamp(rs.getTimestamp("mailDate")));
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setBinCnt(rs.getInt("binCnt"));
                mdl.setBinFileSid(rs.getLong("binSid"));
                mdl.setPhotoFileDsp(rs.getInt("usiPictKf"));

                if (rs.getString("mailKbn").equals(GSConstSmail.TAB_DSP_MODE_JUSIN)) {
                    mdl.setLabelList(getSmjLabelList(rs.getInt("mailSid"), sacSid));
                } else if (rs.getString("mailKbn").equals(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
                    mdl.setLabelList(getSmsLabelList(rs.getInt("mailSid"), sacSid));
                } else {
                    mdl.setLabelList(getSmwLabelList(rs.getInt("mailSid"), sacSid));
                }

                ret.add(mdl);
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
     * <br>[機  能] 指定されたメールSIDの宛先を取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mailSid メールSID
     * @return ret 宛先リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<AtesakiModel> getAtesakiList(int mailSid) throws SQLException {

        return getAtesakiList(mailSid, -1);
    }

    /**
     * <br>[機  能] 指定されたメールSIDの宛先を取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mailSid メールSID
     * @param sendkbn 送信区分 -1を指定した場合、全て取得する。
     * @return ret 宛先リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<AtesakiModel> getAtesakiList(int mailSid, int sendkbn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_OPDATE as smjOpdate,");
            sql.addSql("   SML_JMEIS.SMJ_FWKBN as smjFwkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SENDKBN as smjSendkbn,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("   CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("   CMN_USRM_INF.USI_PICT_KF as usiPictKf");

            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_JMEIS");
            sql.addSql(" where ");

            if (sendkbn != -1) {
                sql.addSql("  SML_JMEIS.SMJ_SENDKBN = ?");
                sql.addSql(" and");
            }
            sql.addSql("   SML_JMEIS.SMJ_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql(" order by");
            sql.addSql("   CMN_USRM_INF.USI_YAKUSYOKU,");
            sql.addSql("   CMN_USRM_INF.USI_SEI_KN,");
            sql.addSql("   CMN_USRM_INF.USI_MEI_KN");


            pstmt = con.prepareStatement(sql.toSqlString());
            if (sendkbn != -1) {
                sql.addIntValue(sendkbn);
            }
            sql.addIntValue(mailSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AtesakiModel mdl = new AtesakiModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setSmjOpdate(UDate.getInstanceTimestamp(rs.getTimestamp("smjOpdate")));
                mdl.setSmjFwkbn(String.valueOf(rs.getInt("smjFwkbn")));
                mdl.setSmjSendkbn(rs.getInt("smjSendkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setBinFileSid(rs.getLong("binSid"));
                mdl.setPhotoFileDsp(rs.getInt("usiPictKf"));

                ret.add(mdl);
            }
            log__.debug("宛先件数==>" + ret.size());
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <br>[機  能] 指定されたメールSIDの宛先を取得(全返信押下時)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mailSid メールSID
     * @return ret 宛先リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<AtesakiModel> getAtesakiList2(int mailSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   SML_JMEIS.SMJ_OPDATE as smjOpdate,");
            sql.addSql("   SML_JMEIS.SMJ_FWKBN as smjFwkbn,");
            sql.addSql("   SML_JMEIS.SMJ_SENDKBN as smjSendkbn,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_JMEIS");
            sql.addSql(" where ");
            sql.addSql("   SML_JMEIS.SMJ_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql("   and ");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN = 0");
            sql.addSql(" order by");
            sql.addSql("   CMN_USRM_INF.USI_YAKUSYOKU,");
            sql.addSql("   CMN_USRM_INF.USI_SEI_KN,");
            sql.addSql("   CMN_USRM_INF.USI_MEI_KN");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(mailSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AtesakiModel mdl = new AtesakiModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setSmjOpdate(UDate.getInstanceTimestamp(rs.getTimestamp("smjOpdate")));
                mdl.setSmjFwkbn(String.valueOf(rs.getInt("smjFwkbn")));
                mdl.setSmjSendkbn(rs.getInt("smjSendkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                ret.add(mdl);
            }
            log__.debug("宛先件数==>" + ret.size());
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return ret;
    }

    /**
     * <br>[機  能] 指定された下書きSIDの宛先を取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mailSid メールSID
     * @param sendkbn 送信区分
     * @return ret 宛先リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<AtesakiModel> getSitagakiAtesakiList(int mailSid, int sendkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei,");
            sql.addSql("   SML_ASAK.SMJ_SENDKBN as smjSendkbn,");
            sql.addSql("   CMN_USRM_INF.BIN_SID as binSid,");
            sql.addSql("   CMN_USRM_INF.USI_PICT_KF as usiPictKf");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_ASAK");
            sql.addSql(" where ");
            sql.addSql("   SML_ASAK.SMJ_SENDKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_ASAK.SMS_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_ASAK.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql(" order by");
            sql.addSql("   CMN_USRM_INF.USI_YAKUSYOKU,");
            sql.addSql("   CMN_USRM_INF.USI_SEI_KN,");
            sql.addSql("   CMN_USRM_INF.USI_MEI_KN");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sendkbn);
            sql.addIntValue(mailSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AtesakiModel mdl = new AtesakiModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                mdl.setBinFileSid(rs.getLong("binSid"));
                mdl.setPhotoFileDsp(rs.getInt("usiPictKf"));
                ret.add(mdl);
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
     * <br>[機  能] 指定された下書きSIDの宛先を取得(草稿タブ時)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param mailSid メールSID
     * @param sendkbn 送信区分
     * @return ret 宛先リスト
     * @throws SQLException SQL実行例外
     */
    public ArrayList<AtesakiModel> getSitagakiAtesakiList2(int mailSid, int sendkbn)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<AtesakiModel> ret = new ArrayList<AtesakiModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_ACCOUNT.SAC_SID as sacSid,");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as sacName,");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN as sacJkbn,");
            sql.addSql("   CMN_USRM.USR_SID as usrSid,");
            sql.addSql("   CMN_USRM.USR_JKBN as usrJkbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usiSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usiMei");
            sql.addSql(" from");
            sql.addSql("   SML_ACCOUNT ");
            sql.addSql("     left join ");
            sql.addSql("       CMN_USRM ");
            sql.addSql("     on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
            sql.addSql("       left join ");
            sql.addSql("         CMN_USRM_INF ");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID, ");
            sql.addSql("   SML_ASAK");
            sql.addSql(" where ");
            sql.addSql("   SML_ASAK.SMJ_SENDKBN = ?");
            sql.addSql(" and");
            sql.addSql("   SML_ASAK.SMS_SID = ?");
            sql.addSql(" and");
            sql.addSql("   SML_ASAK.SAC_SID = SML_ACCOUNT.SAC_SID");
            sql.addSql("   and ");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN = 0");
            sql.addSql(" order by");
            sql.addSql("   CMN_USRM_INF.USI_YAKUSYOKU,");
            sql.addSql("   CMN_USRM_INF.USI_SEI_KN,");
            sql.addSql("   CMN_USRM_INF.USI_MEI_KN");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(sendkbn);
            sql.addIntValue(mailSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AtesakiModel mdl = new AtesakiModel();
                mdl.setAccountSid(rs.getInt("sacSid"));
                mdl.setAccountName(rs.getString("sacName"));
                mdl.setAccountJkbn(rs.getInt("sacJkbn"));
                mdl.setUsrSid(rs.getInt("usrSid"));
                mdl.setUsrJkbn(rs.getInt("usrJkbn"));
                mdl.setUsiSei(rs.getString("usiSei"));
                mdl.setUsiMei(rs.getString("usiMei"));
                ret.add(mdl);
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
     * <br>[機  能] 受信先のメールが全て削除されている削除済み送信メールののSID一覧を取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return ret メールSID一覧
     * @throws SQLException SQL実行例外
     */
    public List<String> getAllDeleteMailSidList() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        List<String> ret = new ArrayList<String>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SMS_SID");
            sql.addSql(" from");
            sql.addSql("   SML_SMEIS");
            sql.addSql(" where");
            sql.addSql("   SMS_JKBN = " + GSConstSmail.SML_JTKBN_DELETE);
            sql.addSql(" and");
            sql.addSql("   not exists (");
            sql.addSql("     select 1 from SML_JMEIS");
            sql.addSql("     where SML_SMEIS.SMS_SID = SML_JMEIS.SMJ_SID ");
            sql.addSql("     and SMJ_JKBN <> " + GSConstSmail.SML_JTKBN_DELETE);
            sql.addSql("   )");

            pstmt = con.prepareStatement(sql.toSqlString());
//            sql.addValue(GSConstSmail.SML_JTKBN_DELETE);
//            sql.addValue(GSConstSmail.SML_JTKBN_DELETE);
//            sql.setParameter(pstmt);
            log__.info(sql.toLogString());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(rs.getString("SMS_SID"));
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
     * <br>[機  能] 指定されたメールSIDのゴミ箱メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectTargetGDetail(int sacSid,
                                                            String[] mailSid)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        //指定されたメールSIDの区分を解析し分解する
        ArrayList<String> jMeis = new ArrayList<String>();
        ArrayList<String> sMeis = new ArrayList<String>();
        ArrayList<String> wMeis = new ArrayList<String>();

        for (String mailKey : mailSid) {
            if (mailKey.startsWith(GSConstSmail.TAB_DSP_MODE_JUSIN)) {
                jMeis.add(mailKey);
            } else if (mailKey.startsWith(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
                sMeis.add(mailKey);
            } else if (mailKey.startsWith(GSConstSmail.TAB_DSP_MODE_SOKO)) {
                wMeis.add(mailKey);
            }
        }

        boolean unionFlg = false;
        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   subView.mailKbn as mailKbn,");
            sql.addSql("   subView.mailTitle as mailTitle");
            sql.addSql(" from");
            sql.addSql("   (");

            //受信
            if (!jMeis.isEmpty()) {
                sql.addSql("    select ");
                sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
                sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle");
                sql.addSql("    from");
                sql.addSql("      SML_JMEIS,");
                sql.addSql("      SML_SMEIS");
                sql.addSql("    where");
                sql.addSql("      SML_JMEIS.SAC_SID = ?");
                sql.addIntValue(sacSid);
                sql.addSql("    and");
                sql.addSql("      SML_JMEIS.SMJ_SID in (");

                for (int i = 0; i < jMeis.size(); i++) {
                    if (i != 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql("?");
                    String jMailKey = jMeis.get(i).substring(1);
                    sql.addIntValue(Integer.parseInt(jMailKey));
                }

                sql.addSql("      )");
                sql.addSql("    and");
                sql.addSql("      SML_JMEIS.SMJ_JKBN = ?");
                sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
                sql.addSql("    and");
                sql.addSql("      SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");

                unionFlg = true;
            }

            //送信
            if (!sMeis.isEmpty()) {
                if (unionFlg) {
                    sql.addSql(" union all");
                } else {
                    unionFlg = true;
                }
                sql.addSql("    select ");
                sql.addSql(GSConstSmail.TAB_DSP_MODE_SOSIN + " as mailKbn,");
                sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle");
                sql.addSql("    from");
                sql.addSql("      SML_SMEIS");
                sql.addSql("    where");
                sql.addSql("      SML_SMEIS.SAC_SID = ?");
                sql.addIntValue(sacSid);
                sql.addSql("    and");
                sql.addSql("      SML_SMEIS.SMS_SID in (");

                for (int i = 0; i < sMeis.size(); i++) {
                    if (i != 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql("?");
                    String sMailKey = sMeis.get(i).substring(1);
                    sql.addIntValue(Integer.parseInt(sMailKey));
                }

                sql.addSql("      )");
                sql.addSql("    and");
                sql.addSql("      SML_SMEIS.SMS_JKBN = ?");
                sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            }


            //草稿
            if (!wMeis.isEmpty()) {
                if (unionFlg) {
                    sql.addSql(" union all");
                }
                sql.addSql("    select ");
                sql.addSql(GSConstSmail.TAB_DSP_MODE_SOKO + " as mailKbn,");
                sql.addSql("      SML_WMEIS.SMW_TITLE as mailTitle");
                sql.addSql("    from");
                sql.addSql("      SML_WMEIS");
                sql.addSql("    where");
                sql.addSql("      SML_WMEIS.SAC_SID = ?");
                sql.addIntValue(sacSid);
                sql.addSql("    and");
                sql.addSql("      SML_WMEIS.SMW_SID in (");

                for (int i = 0; i < wMeis.size(); i++) {
                    if (i != 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql("?");
                    String wMailKey = wMeis.get(i).substring(1);
                    sql.addIntValue(Integer.parseInt(wMailKey));
                }

                sql.addSql("      )");
                sql.addSql("    and");
                sql.addSql("      SML_WMEIS.SMW_JKBN = ?");
                sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
            }

            sql.addSql("   ) subView ");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmsTitle(rs.getString("mailTitle"));
                ret.add(mdl);
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
     * <br>[機  能] 指定されたメールSIDのゴミ箱メッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param mailSid メールSID
     * @return ret 受信メッセージ詳細
     * @throws SQLException SQL実行例外
     */
    public ArrayList<SmailDetailModel> selectTargetLDetail(int sacSid,
                                                            String[] mailSid)
        throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        ArrayList<SmailDetailModel> ret = new ArrayList<SmailDetailModel>();
        con = getCon();

        //指定されたメールSIDの区分を解析し分解する
        ArrayList<String> jMeis = new ArrayList<String>();
        ArrayList<String> sMeis = new ArrayList<String>();
        ArrayList<String> wMeis = new ArrayList<String>();

        for (String mailKey : mailSid) {
            if (mailKey.startsWith(GSConstSmail.TAB_DSP_MODE_JUSIN)) {
                jMeis.add(mailKey);
            } else if (mailKey.startsWith(GSConstSmail.TAB_DSP_MODE_SOSIN)) {
                sMeis.add(mailKey);
            } else if (mailKey.startsWith(GSConstSmail.TAB_DSP_MODE_SOKO)) {
                wMeis.add(mailKey);
            }
        }

        boolean unionFlg = false;
        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   subView.mailKbn as mailKbn,");
            sql.addSql("   subView.mailTitle as mailTitle");
            sql.addSql(" from");
            sql.addSql("   (");

            //受信
            if (!jMeis.isEmpty()) {
                sql.addSql("    select ");
                sql.addSql(GSConstSmail.TAB_DSP_MODE_JUSIN + " as mailKbn,");
                sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle");
                sql.addSql("    from");
                sql.addSql("      SML_JMEIS,");
                sql.addSql("      SML_SMEIS");
                sql.addSql("    where");
                sql.addSql("      SML_JMEIS.SAC_SID = ?");
                sql.addIntValue(sacSid);
                sql.addSql("    and");
                sql.addSql("      SML_JMEIS.SMJ_SID in (");

                for (int i = 0; i < jMeis.size(); i++) {
                    if (i != 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql("?");
                    String jMailKey = jMeis.get(i).substring(1);
                    sql.addIntValue(Integer.parseInt(jMailKey));
                }

                sql.addSql("      )");
                sql.addSql("    and");
                sql.addSql("      SML_JMEIS.SMJ_JKBN = ?");
                sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
                sql.addSql("    and");
                sql.addSql("      SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID");

                unionFlg = true;
            }

            //送信
            if (!sMeis.isEmpty()) {
                if (unionFlg) {
                    sql.addSql(" union all");
                } else {
                    unionFlg = true;
                }
                sql.addSql("    select ");
                sql.addSql(GSConstSmail.TAB_DSP_MODE_SOSIN + " as mailKbn,");
                sql.addSql("      SML_SMEIS.SMS_TITLE as mailTitle");
                sql.addSql("    from");
                sql.addSql("      SML_SMEIS");
                sql.addSql("    where");
                sql.addSql("      SML_SMEIS.SAC_SID = ?");
                sql.addIntValue(sacSid);
                sql.addSql("    and");
                sql.addSql("      SML_SMEIS.SMS_SID in (");

                for (int i = 0; i < sMeis.size(); i++) {
                    if (i != 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql("?");
                    String sMailKey = sMeis.get(i).substring(1);
                    sql.addIntValue(Integer.parseInt(sMailKey));
                }

                sql.addSql("      )");
                sql.addSql("    and");
                sql.addSql("      SML_SMEIS.SMS_JKBN = ?");
                sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
            }


            //草稿
            if (!wMeis.isEmpty()) {
                if (unionFlg) {
                    sql.addSql(" union all");
                }
                sql.addSql("    select ");
                sql.addSql(GSConstSmail.TAB_DSP_MODE_SOKO + " as mailKbn,");
                sql.addSql("      SML_WMEIS.SMW_TITLE as mailTitle");
                sql.addSql("    from");
                sql.addSql("      SML_WMEIS");
                sql.addSql("    where");
                sql.addSql("      SML_WMEIS.SAC_SID = ?");
                sql.addIntValue(sacSid);
                sql.addSql("    and");
                sql.addSql("      SML_WMEIS.SMW_SID in (");

                for (int i = 0; i < wMeis.size(); i++) {
                    if (i != 0) {
                        sql.addSql(", ");
                    }
                    sql.addSql("?");
                    String wMailKey = wMeis.get(i).substring(1);
                    sql.addIntValue(Integer.parseInt(wMailKey));
                }

                sql.addSql("      )");
                sql.addSql("    and");
                sql.addSql("      SML_WMEIS.SMW_JKBN = ?");
                sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
            }

            sql.addSql("   ) subView ");

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                SmailDetailModel mdl = new SmailDetailModel();
                mdl.setMailKbn(rs.getString("mailKbn"));
                mdl.setSmsTitle(rs.getString("mailTitle"));
                ret.add(mdl);
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
     * <p>Select SML_USER
     * @param  fwCheckList String[] 転送先不正文字列リスト
     * @return List 不正転送先情報
     * @throws SQLException SQL実行例外
     */
    public List<Sml110FwCheckModel> getFwErrorList(String[] fwCheckList)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Sml110FwCheckModel model = null;
        List<Sml110FwCheckModel> ret = new ArrayList<Sml110FwCheckModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   unionView.fwKbn as fwKbn,");
            sql.addSql("   unionView.usrSei as usrSei,");
            sql.addSql("   unionView.usrMei as usrMei,");
            sql.addSql("   unionView.usrSid as usrSid,");
            sql.addSql("   unionView.smlFw as smlFw,");
            sql.addSql("   unionView.smlDf as smlDf,");
            sql.addSql("   unionView.sml1 as sml1,");
            sql.addSql("   unionView.sml2 as sml2,");
            sql.addSql("   unionView.sml3 as sml3");
            sql.addSql(" from");
            sql.addSql("   (");

            sql.addSql("   select ");
            sql.addSql(GSConstSmail.FW_CHECK_MODE_DF + " as fwKbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usrSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usrMei,");
            sql.addSql("   SML_ACCOUNT_FORWARD.USR_SID as usrSid,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_MAILFW as smlFw,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_MAIL_DF as smlDf,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_HURIWAKE as smlHw,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL1 as sml1,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL2 as sml2,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL3 as sml3");
            sql.addSql("   from");
            sql.addSql("   ((SML_ACCOUNT_FORWARD left join");
            sql.addSql("   CMN_USRM_INF on SML_ACCOUNT_FORWARD.USR_SID = CMN_USRM_INF.USR_SID)");
            sql.addSql("   left join CMN_USRM on CMN_USRM_INF.USR_SID = CMN_USRM.USR_SID)");
            sql.addSql("   where ");
            sql.addSql("   SAF_MAILFW = 1 ");
            sql.addSql("   and ");
            sql.addSql("   SAF_HURIWAKE = 0");
            sql.addSql("   and ");
            sql.addSql("   CMN_USRM.USR_JKBN = 0");

            for (int i = 0; fwCheckList.length > i; i++) {
                if (fwCheckList[i] != null && !fwCheckList[i].equals("")) {
                    sql.addSql(" and ");

                    sql.addSql(" SAF_MAIL_DF not like '%");
                    sql.addSql(JDBCUtil.encFullStringLike(fwCheckList[i]));
                    sql.addSql("%'");
                    sql.addSql(" ESCAPE '" + JDBCUtil.def_esc + "'");
                }
            }

            sql.addSql(" union all ");
            sql.addSql("   select ");
            sql.addSql(GSConstSmail.FW_CHECK_MODE_ZAISEKI + " as fwKbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usrSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usrMei,");
            sql.addSql("   SML_ACCOUNT_FORWARD.USR_SID as usrSid,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_MAILFW as smlFw,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_MAIL_DF as smlDf,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_HURIWAKE as smlHw,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL1 as sml1,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL2 as sml2,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL3 as sml3");
            sql.addSql("   from");
            sql.addSql("   ((SML_ACCOUNT_FORWARD left join");
            sql.addSql("   CMN_USRM_INF on SML_ACCOUNT_FORWARD.USR_SID = CMN_USRM_INF.USR_SID)");
            sql.addSql("   left join CMN_USRM on CMN_USRM_INF.USR_SID = CMN_USRM.USR_SID)");
            sql.addSql("   where ");
            sql.addSql("   SAF_MAILFW = 1 ");
            sql.addSql("   and ");
            sql.addSql("   SAF_HURIWAKE = 1");
            sql.addSql("   and ");
            sql.addSql("   CMN_USRM.USR_JKBN = 0");

            for (int i = 0; fwCheckList.length > i; i++) {
                if (fwCheckList[i] != null && !fwCheckList[i].equals("")) {
                    sql.addSql(" and ");

                    sql.addSql(" SAF_ZMAIL1 not like '%");
                    sql.addSql(JDBCUtil.encFullStringLike(fwCheckList[i]));
                    sql.addSql("%'");
                    sql.addSql(" ESCAPE '" + JDBCUtil.def_esc + "'");
                }
            }

            sql.addSql(" union all");
            sql.addSql("   select ");
            sql.addSql(GSConstSmail.FW_CHECK_MODE_HUZAI + " as fwKbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usrSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usrMei,");
            sql.addSql("   SML_ACCOUNT_FORWARD.USR_SID as usrSid,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_MAILFW as smlFw,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_MAIL_DF as smlDf,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_HURIWAKE as smlHw,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL1 as sml1,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL2 as sml2,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL3 as sml3");
            sql.addSql("   from");
            sql.addSql("   ((SML_ACCOUNT_FORWARD left join");
            sql.addSql("   CMN_USRM_INF on SML_ACCOUNT_FORWARD.USR_SID = CMN_USRM_INF.USR_SID)");
            sql.addSql("   left join CMN_USRM on CMN_USRM_INF.USR_SID = CMN_USRM.USR_SID)");
            sql.addSql("   where ");
            sql.addSql("   SAF_MAILFW = 1 ");
            sql.addSql("   and ");
            sql.addSql("   SAF_HURIWAKE = 1");
            sql.addSql("   and ");
            sql.addSql("   CMN_USRM.USR_JKBN = 0");

            for (int i = 0; fwCheckList.length > i; i++) {
                if (fwCheckList[i] != null && !fwCheckList[i].equals("")) {
                    sql.addSql(" and ");

                    sql.addSql(" SAF_ZMAIL2 not like '%");
                    sql.addSql(JDBCUtil.encFullStringLike(fwCheckList[i]));
                    sql.addSql("%'");
                    sql.addSql(" ESCAPE '" + JDBCUtil.def_esc + "'");
                }
            }

            sql.addSql(" union all");
            sql.addSql("   select ");
            sql.addSql(GSConstSmail.FW_CHECK_MODE_OTHER + " as fwKbn,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as usrSei,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as usrMei,");
            sql.addSql("   SML_ACCOUNT_FORWARD.USR_SID as usrSid,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_MAILFW as smlFw,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_MAIL_DF as smlDf,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_HURIWAKE as smlHw,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL1 as sml1,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL2 as sml2,");
            sql.addSql("   SML_ACCOUNT_FORWARD.SAF_ZMAIL3 as sml3");
            sql.addSql("   from");
            sql.addSql("   ((SML_ACCOUNT_FORWARD left join");
            sql.addSql("   CMN_USRM_INF on SML_ACCOUNT_FORWARD.USR_SID = CMN_USRM_INF.USR_SID)");
            sql.addSql("   left join CMN_USRM on CMN_USRM_INF.USR_SID = CMN_USRM.USR_SID)");
            sql.addSql("   where ");
            sql.addSql("   SAF_MAILFW = 1 ");
            sql.addSql("   and ");
            sql.addSql("   SAF_HURIWAKE = 1");
            sql.addSql("   and ");
            sql.addSql("   CMN_USRM.USR_JKBN = 0");

            for (int i = 0; fwCheckList.length > i; i++) {
                if (fwCheckList[i] != null && !fwCheckList[i].equals("")) {
                    sql.addSql(" and ");

                    sql.addSql(" SAF_ZMAIL3 not like '%");
                    sql.addSql(JDBCUtil.encFullStringLike(fwCheckList[i]));
                    sql.addSql("%'");
                    sql.addSql(" ESCAPE '" + JDBCUtil.def_esc + "'");
                }
            }
            sql.addSql("   ) unionView");
            sql.addSql("   order by usrSei ASC, usrMei ASC, fwKbn ASC");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                model = new Sml110FwCheckModel();
                model.setFwKbn(rs.getInt("fwKbn"));
                model.setUsrSid(rs.getInt("usrSid"));
                model.setUsrNameSei(rs.getString("usrSei"));
                model.setUsrNameMei(rs.getString("usrMei"));
                model.setFwAddDf(rs.getString("smlDf"));
                model.setFwAdd1(rs.getString("sml1"));
                model.setFwAdd2(rs.getString("sml2"));
                model.setFwAdd3(rs.getString("sml3"));
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
     * <br>[機  能] 指定したショートメールをユーザが閲覧可能かを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sacSid アカウントSID
     * @param smlSid メールSID
     * @return true: 閲覧可能 false:閲覧不可
     * @throws SQLException SQL実行例外
     */
    public boolean isViewSmail(int sacSid, int smlSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        boolean result = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("   select 1 from SML_SMEIS");
            sql.addSql("   where SAC_SID = ?");
            sql.addSql("   and SMS_SID = ?");
            sql.addSql("   and SMS_JKBN <> ?");
            sql.addSql(" union all");
            sql.addSql("   select 1 from SML_JMEIS");
            sql.addSql("   where SAC_SID = ?");
            sql.addSql("   and SMJ_SID = ?");
            sql.addSql("   and SMJ_JKBN <> ?");
            sql.addSql(" union all");
            sql.addSql("   select 1 from SML_WMEIS");
            sql.addSql("   where SAC_SID = ?");
            sql.addSql("   and SMW_SID = ?");
            sql.addSql("   and SMW_JKBN <> ?");
            sql.setPagingValue(0, 1);

            sql.addIntValue(sacSid);
            sql.addIntValue(smlSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_DELETE);
            sql.addIntValue(sacSid);
            sql.addIntValue(smlSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_DELETE);
            sql.addIntValue(sacSid);
            sql.addIntValue(smlSid);
            sql.addIntValue(GSConstSmail.SML_JTKBN_DELETE);
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);

            rs = pstmt.executeQuery();
            result = rs.next();

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return result;
    }


    /**
     * <br>[機  能] アカウント情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param sacSid アカウントSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public boolean canUseCheckAccount(int userSid, int sacSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean ret = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SML_ACCOUNT.SAC_SID as SAC_SID, ");
            sql.addSql("   SML_ACCOUNT.SAC_TYPE as SAC_TYPE, ");
            sql.addSql("   SML_ACCOUNT.SAC_NAME as SAC_NAME, ");
            sql.addSql("   SML_ACCOUNT.SAC_BIKO as SAC_BIKO, ");
            sql.addSql("   ACCOUNT_SORT.SAS_SORT as SAS_SORT ");
            sql.addSql(" from ");
            sql.addSql("   SML_ACCOUNT");
            sql.addSql("   left join");
            sql.addSql("     (");
            sql.addSql("      select SAC_SID, SAS_SORT");
            sql.addSql("      from SML_ACCOUNT_SORT ");
            sql.addSql("      where USR_SID = ? ");
            sql.addSql("     ) ACCOUNT_SORT");
            sql.addSql("   on");
            sql.addSql("     SML_ACCOUNT.SAC_SID = ACCOUNT_SORT.SAC_SID ");
            sql.addSql(" where ");
            sql.addSql("   SML_ACCOUNT.SAC_JKBN = ?");
            sql.addSql(" and ");
            sql.addSql("   (");
            sql.addSql("      (");
            sql.addSql("         SML_ACCOUNT.SAC_TYPE = ? ");
            sql.addSql("       and ");
            sql.addSql("         SML_ACCOUNT.USR_SID = ? ");
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
            sql.addSql(" and ");
            sql.addSql("   SML_ACCOUNT.SAC_SID = ?");
            sql.addSql(" order by ");
            sql.addSql("   ACCOUNT_SORT.SAS_SORT asc ");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(userSid);
            sql.addIntValue(GSConstSmail.SAC_JKBN_NORMAL);
            sql.addIntValue(GSConstSmail.SAC_TYPE_NORMAL);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);
            sql.addIntValue(userSid);
            sql.addIntValue(sacSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = true;
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
     * <br>[機  能] 指定したアカウントのディスク容量集計値を取得する
     * @param sacSid アカウントSID
     * @return 削除メールSIDの件数
     * @throws SQLException SQL実行例外
     */
    public long getSumAccountDiskSize(int sacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();
        Long totalSize = new Long(0);

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select ");
            sql.addSql("   sum(SML_DATA.MAIL_SIZE) as ALL_MAIL_SIZE ");
            sql.addSql(" from ");
            sql.addSql("   ( ");
            sql.addSql("     select ");
            sql.addSql("       sum(SML_SMEIS.SMS_SIZE) as MAIL_SIZE ");
            sql.addSql("     from ");
            sql.addSql("       SML_SMEIS, ");
            sql.addSql("       SML_JMEIS ");
            sql.addSql("     where ");
            sql.addSql("       SML_JMEIS.SAC_SID = ?");
            sql.addIntValue(sacSid);
            sql.addSql("     and ");
            sql.addSql("       SML_JMEIS.SMJ_JKBN <>" + GSConstSmail.SML_JTKBN_DELETE);
            sql.addSql("     and ");
            sql.addSql("       SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID ");
            sql.addSql("   union all ");
            sql.addSql("     select ");
            sql.addSql("       sum(SML_SMEIS.SMS_SIZE) as MAIL_SIZE ");
            sql.addSql("     from ");
            sql.addSql("       SML_SMEIS ");
            sql.addSql("     where ");
            sql.addSql("       SML_SMEIS.SAC_SID = ?");
            sql.addIntValue(sacSid);
            sql.addSql("     and ");
            sql.addSql("       SML_SMEIS.SMS_JKBN <>" + GSConstSmail.SML_JTKBN_DELETE);
            sql.addSql("   union all ");
            sql.addSql("     select ");
            sql.addSql("       SUM(SML_WMEIS.SMW_SIZE) as MAIL_SIZE ");
            sql.addSql("     from ");
            sql.addSql("       SML_WMEIS ");
            sql.addSql("     where ");
            sql.addSql("       SML_WMEIS.SAC_SID = ?");
            sql.addIntValue(sacSid);
            sql.addSql(" ) SML_DATA; ");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                totalSize = rs.getLong("ALL_MAIL_SIZE");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return totalSize;
    }


    /**
     * <br>[機  能] 指定したラベルが存在するかを確認する
     * <br>[解  説]
     * <br>[備  考]
     * @param slbSid ラベルSID
     * @return true:存在する、false:存在しない
     * @throws SQLException SQL実行時例外
     */
    public boolean existLabel(int slbSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(SLB_SID) as CNT");
            sql.addSql(" from");
            sql.addSql("   SML_LABEL");
            sql.addSql(" where");
            sql.addSql("   SLB_SID = ?");
            sql.addIntValue(slbSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getLong("CNT") > 0;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
            rs = null;
            pstmt = null;
        }

        return result;
    }



    /**
     * <br>[機  能] 受信メールのラベルを取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param smjSid 受信メールSID
     * @param sacSid アカウントSID
     * @return ret メールSID一覧
     * @throws SQLException SQL実行例外
     */
    public List<SmlLabelModel> getSmjLabelList(int smjSid, int sacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        List<SmlLabelModel> ret = new ArrayList<SmlLabelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_LABEL.SLB_SID   as SLB_SID,");
            sql.addSql("   SML_LABEL.USR_SID   as USR_SID,");
            sql.addSql("   SML_LABEL.SLB_NAME  as SLB_NAME,");
            sql.addSql("   SML_LABEL.SLB_TYPE  as SLB_TYPE,");
            sql.addSql("   SML_LABEL.SAC_SID   as SAC_SID,");
            sql.addSql("   SML_LABEL.SLB_ORDER as SLB_ORDER");
            sql.addSql(" from ");
            sql.addSql("   SML_JMEIS_LABEL left join SML_LABEL");
            sql.addSql("   on SML_JMEIS_LABEL.SLB_SID = SML_LABEL.SLB_SID");
            sql.addSql(" where");
            sql.addSql("   SML_JMEIS_LABEL.SMJ_SID =" + smjSid);
            sql.addSql(" and");
            sql.addSql("   SML_JMEIS_LABEL.SAC_SID =" + sacSid);
            sql.addSql(" order by");
            sql.addSql("   SLB_ORDER;");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                SmlLabelModel bean = new SmlLabelModel();
                bean.setSlbSid(rs.getInt("SLB_SID"));
                bean.setUsrSid(rs.getInt("USR_SID"));
                bean.setSlbName(rs.getString("SLB_NAME"));
                bean.setSlbType(rs.getInt("SLB_TYPE"));
                bean.setSlbOrder(rs.getInt("SLB_ORDER"));
                bean.setSacSid(rs.getInt("SAC_SID"));
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
     * <br>[機  能] 送信メールのラベルを取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param smsSid 送信メールSID
     * @param sacSid アカウントSID
     * @return ret メールSID一覧
     * @throws SQLException SQL実行例外
     */
    public List<SmlLabelModel> getSmsLabelList(int smsSid, int sacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        List<SmlLabelModel> ret = new ArrayList<SmlLabelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_LABEL.SLB_SID   as SLB_SID,");
            sql.addSql("   SML_LABEL.USR_SID   as USR_SID,");
            sql.addSql("   SML_LABEL.SLB_NAME  as SLB_NAME,");
            sql.addSql("   SML_LABEL.SLB_TYPE  as SLB_TYPE,");
            sql.addSql("   SML_LABEL.SAC_SID   as SAC_SID,");
            sql.addSql("   SML_LABEL.SLB_ORDER as SLB_ORDER");
            sql.addSql(" from ");
            sql.addSql("   SML_SMEIS_LABEL left join SML_LABEL");
            sql.addSql("   on SML_SMEIS_LABEL.SLB_SID = SML_LABEL.SLB_SID");
            sql.addSql(" where");
            sql.addSql("   SML_SMEIS_LABEL.SMS_SID =" + smsSid);
            sql.addSql(" and");
            sql.addSql("   SML_SMEIS_LABEL.SAC_SID =" + sacSid);
            sql.addSql(" order by");
            sql.addSql("   SLB_ORDER;");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                SmlLabelModel bean = new SmlLabelModel();
                bean.setSlbSid(rs.getInt("SLB_SID"));
                bean.setUsrSid(rs.getInt("USR_SID"));
                bean.setSlbName(rs.getString("SLB_NAME"));
                bean.setSlbType(rs.getInt("SLB_TYPE"));
                bean.setSlbOrder(rs.getInt("SLB_ORDER"));
                bean.setSacSid(rs.getInt("SAC_SID"));
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
     * <br>[機  能] 草稿メールのラベルを取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param smwSid 草稿メールSID
     * @param sacSid アカウントSID
     * @return ret メールSID一覧
     * @throws SQLException SQL実行例外
     */
    public List<SmlLabelModel> getSmwLabelList(int smwSid, int sacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        List<SmlLabelModel> ret = new ArrayList<SmlLabelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   SML_LABEL.SLB_SID   as SLB_SID,");
            sql.addSql("   SML_LABEL.USR_SID   as USR_SID,");
            sql.addSql("   SML_LABEL.SLB_NAME  as SLB_NAME,");
            sql.addSql("   SML_LABEL.SLB_TYPE  as SLB_TYPE,");
            sql.addSql("   SML_LABEL.SAC_SID   as SAC_SID,");
            sql.addSql("   SML_LABEL.SLB_ORDER as SLB_ORDER");
            sql.addSql(" from ");
            sql.addSql("   SML_WMEIS_LABEL left join SML_LABEL");
            sql.addSql("   on SML_WMEIS_LABEL.SLB_SID = SML_LABEL.SLB_SID");
            sql.addSql(" where");
            sql.addSql("   SML_WMEIS_LABEL.SMW_SID =" + smwSid);
            sql.addSql(" and");
            sql.addSql("   SML_WMEIS_LABEL.SAC_SID =" + sacSid);
            sql.addSql(" order by");
            sql.addSql("   SLB_ORDER;");

            pstmt = con.prepareStatement(sql.toSqlString());

            log__.info(sql.toLogString());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                SmlLabelModel bean = new SmlLabelModel();
                bean.setSlbSid(rs.getInt("SLB_SID"));
                bean.setUsrSid(rs.getInt("USR_SID"));
                bean.setSlbName(rs.getString("SLB_NAME"));
                bean.setSlbType(rs.getInt("SLB_TYPE"));
                bean.setSlbOrder(rs.getInt("SLB_ORDER"));
                bean.setSacSid(rs.getInt("SAC_SID"));
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
     * <br>[機  能] 指定されたメールSIDの受信メールラベルを削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param smjSidList メールSID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int deleteJushinLabel(List<String> smjSidList) throws SQLException {

        if (smjSidList == null || smjSidList.size() <= 0) {
            return 0;
        }

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SML_JMEIS_LABEL");
            sql.addSql(" where ");
            if (smjSidList.size() == 1) {
                sql.addSql("   SMJ_SID = ?");
                sql.addIntValue(Integer.parseInt(smjSidList.get(0)));
            } else {
                sql.addSql("   SMJ_SID in (");
                for (int idx = 0; idx < smjSidList.size() - 1; idx++) {
                    sql.addSql("     ?,");
                    sql.addIntValue(Integer.parseInt(smjSidList.get(idx)));
                }
                sql.addSql("     ?");
                sql.addIntValue(Integer.parseInt(smjSidList.get(smjSidList.size() - 1)));
                sql.addSql("   )");
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }


    /**
     * <br>[機  能] 指定されたメールSIDのメッセージを物理削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param smsSidList メールSID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int deleteSoshinLabel(List<String> smsSidList) throws SQLException {

        if (smsSidList == null || smsSidList.size() <= 0) {
            return 0;
        }

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SML_SMEIS_LABEL");
            sql.addSql(" where ");
            if (smsSidList.size() == 1) {
                sql.addSql("   SMS_SID = ?");
                sql.addIntValue(Integer.parseInt(smsSidList.get(0)));
            } else {
                sql.addSql("   SMS_SID in (");
                for (int idx = 0; idx < smsSidList.size() - 1; idx++) {
                    sql.addSql("     ?,");
                    sql.addIntValue(Integer.parseInt(smsSidList.get(idx)));
                }
                sql.addSql("     ?");
                sql.addIntValue(Integer.parseInt(smsSidList.get(smsSidList.size() - 1)));
                sql.addSql("   )");
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] 指定されたメールSIDの草稿メールラベルを削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param smjSidList メールSID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int deletelGomibakoLabel(ArrayList<Integer> smjSidList) throws SQLException {

        if (smjSidList == null || smjSidList.size() <= 0) {
            return 0;
        }

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SML_WMEIS_LABEL");
            sql.addSql(" where ");
            if (smjSidList.size() == 1) {
                sql.addSql("   SMW_SID = ?");
                sql.addIntValue(smjSidList.get(0));
            } else {
                sql.addSql("   SMW_SID in (");
                for (int idx = 0; idx < smjSidList.size() - 1; idx++) {
                    sql.addSql("     ?,");
                    sql.addIntValue(smjSidList.get(idx));
                }
                sql.addSql("     ?");
                sql.addIntValue(smjSidList.get(smjSidList.size() - 1));
                sql.addSql("   )");
            }

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] 指定されたメールSIDの草稿メールラベルを削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param smjSid メールSID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int deleteLabel(int smjSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SML_WMEIS_LABEL");
            sql.addSql(" where ");
            sql.addSql("   SMW_SID = ?");
            sql.addIntValue(smjSid);

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] 指定されたメールSIDの草稿メールラベルを削除する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param sacSid アカウントSID
     * @param msgSid メールSID
     * @return count 削除件数
     * @throws SQLException SQL実行例外
     */
    public int deleteListLabel(int sacSid, String[] msgSid) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   SML_WMEIS_LABEL");
            sql.addSql(" where ");
            sql.addSql("   SAC_SID = ?");
            sql.addIntValue(sacSid);
            sql.addSql(" and");
            sql.addSql("   SMW_SID in (");
            for (int i = 0; i < msgSid.length; i++) {
                if (i != 0) {
                    sql.addSql(", ");
                }
                sql.addSql("?");
                String mailKey = msgSid[i].substring(1);
                sql.addIntValue(Integer.parseInt(mailKey));
            }
            sql.addSql(")");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }
    /**
     * <br>[機  能] フィルタリングの条件と一致したメール情報を更新する
     * <br>[解  説] フィルター情報の更新内容に従ってメール情報の更新を行う
     * <br>[備  考]
     * @param filterData フィルター情報
     * @param conditionList フィルター条件
     * @param sacSid 更新対象のアカウントSID
     * @throws SQLException SQL実行時例外
     */
    public void updateFilterlingMail(MailFilterModel filterData,
                                    List<MailFilterConditionModel> conditionList,
                                    int sacSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        SqlBuffer sql = null;
        try {

            //既読にする or ゴミ箱へ移動
            if (filterData.isReaded() || filterData.isDust()) {
                //更新対象のメッセージ番号を取得する
                sql = new SqlBuffer();
                sql.addSql(" select");
                sql.addSql("   distinct");
                sql.addSql("   SML_JMEIS_DETAIL.smjSid as SMJ_SID");
                setFromSqlForFiltering(sql, filterData, conditionList, sacSid, 0);

                log__.info(sql.toLogString());
                pstmt = getCon().prepareStatement(sql.toSqlString());
                sql.setParameter(pstmt);
                rs = pstmt.executeQuery();

                List<Integer> updateMailNumList = new ArrayList<Integer>();
                while (rs.next()) {
                    updateMailNumList.add(rs.getInt("SMJ_SID"));
                }

                JDBCUtil.closeResultSet(rs);
                JDBCUtil.closePreparedStatement(pstmt);

                //100件づつ更新する
                for (int idx = 0; idx < updateMailNumList.size(); idx += 100) {
                    sql = new SqlBuffer();
                    sql.addSql(" update");
                    sql.addSql("   SML_JMEIS");
                    sql.addSql(" set");

                    if (filterData.isReaded()) {
                        sql.addSql("   SMJ_OPKBN = ?");
                        sql.addIntValue(GSConstSmail.OPKBN_OPENED);
                    }
                    if (filterData.isDust()) {
                        if (filterData.isReaded()) {
                            sql.addSql("   ,");
                        }
                        sql.addSql("   SMJ_JKBN = ?");
                        sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
                    }

                    sql.addSql(" where");
                    sql.addSql("   SAC_SID = ?");
                    sql.addIntValue(sacSid);
                    sql.addSql(" and");
                    sql.addSql("   SMJ_SID in (");
                    for (int mailIdx = idx;
                            mailIdx < updateMailNumList.size() && mailIdx < idx + 100; mailIdx++) {
                        if (mailIdx > idx) {
                            sql.addSql("     ,?");
                        } else {
                            sql.addSql("     ?");
                        }
                        sql.addIntValue(updateMailNumList.get(mailIdx));
                    }
                    sql.addSql("   )");

                    log__.info(sql.toLogString());

                    pstmt = getCon().prepareStatement(sql.toSqlString());
                    sql.setParameter(pstmt);
                    pstmt.executeUpdate();
                    JDBCUtil.closePreparedStatement(pstmt);
                    pstmt = null;
                }
            }

            //ラベルを付与
            if (filterData.isLabel()) {
                sql = new SqlBuffer();
                sql.addSql(" insert into");
                sql.addSql(" SML_JMEIS_LABEL (");
                sql.addSql("   SMJ_SID,");
                sql.addSql("   SLB_SID,");
                sql.addSql("   SAC_SID");
                sql.addSql(" )");
                sql.addSql(" select");
                sql.addSql("   distinct");
                sql.addSql("   SML_JMEIS_DETAIL.smjSid as SMJ_SID,");
                sql.addSql("   ?,");
                sql.addSql("   ?");
                sql.addIntValue(filterData.getLabelSid());
                sql.addIntValue(sacSid);
                setFromSqlForFiltering(sql, filterData, conditionList, sacSid, 0);
                sql.addSql(" where");
                sql.addSql("   not exists (");
                sql.addSql("     select 1 from SML_JMEIS_LABEL");
                sql.addSql("     where");
                sql.addSql("       SLB_SID = ?");
                sql.addSql("     and");
                sql.addSql("       SML_JMEIS_DETAIL.smjSid = SML_JMEIS_LABEL.SMJ_SID");
                sql.addSql("   )");

                sql.addIntValue(filterData.getLabelSid());

                log__.info(sql.toLogString());
                pstmt = getCon().prepareStatement(sql.toSqlString());
                sql.setParameter(pstmt);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pstmt);
            pstmt = null;
            sql = null;
        }
    }





    /**
     * <br>[機  能] 検索SQL パターン文字列を作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param conditionMdl フィルター条件情報
     * @return パターン文字列
     */
    private String __getPatternString(MailFilterConditionModel conditionMdl) {
        StringBuilder sb = null;
        sb = new StringBuilder(" like");

        sb.append(" '%");
        sb.append(JDBCUtil.encFullStringLike(conditionMdl.getText()));
        sb.append("%' ESCAPE '");
        sb.append(JDBCUtil.def_esc);
        sb.append("'");

        return sb.toString();
    }

    /**
     * <br>[機  能] フィルター情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param sacSid アカウントSID
     * @return フィルター情報
     * @throws SQLException SQL実行時例外
     */
    public List<MailFilterModel> getFilterData(int sacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MailFilterModel> filterList = new ArrayList<MailFilterModel>();

        SqlBuffer sql = null;
        try {
            //SQL文
            sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SML_FILTER.SFT_SID as SFT_SID,");
            sql.addSql("   SML_FILTER.SFT_TEMPFILE as SFT_TEMPFILE,");
            sql.addSql("   SML_FILTER.SFT_ACTION_LABEL as SFT_ACTION_LABEL,");
            sql.addSql("   SML_FILTER.SLB_SID as SLB_SID,");
            sql.addSql("   SML_FILTER.SFT_ACTION_READ as SFT_ACTION_READ,");
            sql.addSql("   SML_FILTER.SFT_ACTION_DUST as SFT_ACTION_DUST,");
            sql.addSql("   SML_FILTER.SFT_CONDITION as SFT_CONDITION");
            sql.addSql(" from ");
            sql.addSql("   SML_FILTER, ");
            sql.addSql("   SML_FILTER_SORT ");
            sql.addSql(" where ");
            sql.addSql("   (");
            sql.addSql("     (");
            sql.addSql("       SML_FILTER.SFT_TYPE = ? ");
            sql.addSql("     and ");
            sql.addSql("       SML_FILTER.SAC_SID = ?");
            sql.addSql("     )");
            sql.addSql("   or");
            sql.addSql("     (");
            sql.addSql("       SML_FILTER.SFT_TYPE = ? ");
            sql.addSql("     and ");
            sql.addSql("       (");
            sql.addSql("         SML_FILTER.USR_SID in (");
            sql.addSql("           select USR_SID from SML_ACCOUNT_USER");
            sql.addSql("           where SAC_SID = ?");
            sql.addSql("           and coalesce(USR_SID, 0) > 0");
            sql.addSql("         )");
            sql.addSql("        or");
            sql.addSql("         SML_FILTER.USR_SID in (");
            sql.addSql("           select CMN_BELONGM.USR_SID");
            sql.addSql("           from");
            sql.addSql("             SML_ACCOUNT_USER,");
            sql.addSql("             CMN_BELONGM");
            sql.addSql("           where SML_ACCOUNT_USER.SAC_SID = ?");
            sql.addSql("           and coalesce(SML_ACCOUNT_USER.GRP_SID, 0) > 0");
            sql.addSql("           and SML_ACCOUNT_USER.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("         )");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   )");
            sql.addSql(" and ");
            sql.addSql("   SML_FILTER_SORT.SAC_SID = ? ");
            sql.addSql(" and ");
            sql.addSql("   SML_FILTER.SFT_SID = SML_FILTER_SORT.SFT_SID ");
            sql.addSql(" order by ");
            sql.addSql("   SML_FILTER_SORT.SFS_SORT");

            sql.addIntValue(GSConstSmail.LABELTYPE_ONES);
            sql.addIntValue(sacSid);
            sql.addIntValue(GSConstSmail.LABELTYPE_ALL);
            sql.addIntValue(sacSid);
            sql.addIntValue(sacSid);
            sql.addIntValue(sacSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MailFilterModel filterData = new MailFilterModel();
                filterData.setSftSid(rs.getInt("SFT_SID"));
                filterData.setTempFile(
                        rs.getInt("SFT_TEMPFILE") == GSConstSmail.FILTER_TEMPFILE_YES);
                filterData.setLabel(
                        rs.getInt("SFT_ACTION_LABEL") == GSConstSmail.FILTER_LABEL_SETLABEL);
                filterData.setLabelSid(NullDefault.getInt(
                        String.valueOf(rs.getInt("SLB_SID")), -1));
                filterData.setReaded(
                        rs.getInt("SFT_ACTION_READ") == GSConstSmail.FILTER_READED_SETREADED);
                filterData.setDust(
                        rs.getInt("SFT_ACTION_DUST") == GSConstSmail.FILTER_DUST_MOVEDUST);
                filterData.setCondition(rs.getInt("SFT_CONDITION"));

                filterList.add(filterData);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pstmt);
            rs = null;
            pstmt = null;
            sql = null;
        }

        return filterList;
    }


    /**
     * <br>[機  能] フィルター条件を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param sftSid フィルターSID
     * @return フィルター条件
     * @throws SQLException SQL実行時例外
     */
    public List<MailFilterConditionModel> getFilterConditionData(int sftSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MailFilterConditionModel> conditionList
            = new ArrayList<MailFilterConditionModel>();

        SqlBuffer sql = null;
        try {
            //SQL文
            sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   SFC_TYPE,");
            sql.addSql("   SFC_EXPRESSION,");
            sql.addSql("   SFC_TEXT");
            sql.addSql(" from");
            sql.addSql("   SML_FILTER_CONDITION");
            sql.addSql(" where");
            sql.addSql("   SFT_SID = ?");
            sql.addSql(" order by");
            sql.addSql("   SFC_NUM");
            sql.addIntValue(sftSid);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            MailFilterConditionModel conditionData = null;
            while (rs.next()) {
                conditionData = new MailFilterConditionModel();
                conditionData.setType(rs.getInt("SFC_TYPE"));
                conditionData.setExpression(rs.getInt("SFC_EXPRESSION"));
                conditionData.setText(rs.getString("SFC_TEXT"));
                conditionList.add(conditionData);
            }
            conditionData = null;

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pstmt);
            rs = null;
            pstmt = null;
            sql = null;
        }

        return conditionList;
    }

    /**
     * <br>[機  能] 指定したメール情報にラベルを付与する
     * <br>[解  説]
     * <br>[備  考]
     * @param sacSid アカウントSID
     * @param mailNum 対象メールのメッセージ番号
     * @param slbSid ラベルSID
     * @throws SQLException SQL実行時例外
     */
    public void insertLabelRelation(int sacSid, int mailNum, int slbSid)
    throws SQLException {

        PreparedStatement pstmt = null;

        SqlBuffer sql = null;
        try {
            sql = new SqlBuffer();
            sql.addSql(" insert into");
            sql.addSql(" SML_JMEIS_LABEL (");
            sql.addSql("   SMJ_SID,");
            sql.addSql("   SLB_SID,");
            sql.addSql("   SAC_SID");
            sql.addSql(" )");
            sql.addSql(" values");

            sql.addSql(" (");
            sql.addSql("   ?,");
            sql.addSql("   ?,");
            sql.addSql("   ?");
            sql.addSql(" )");
            sql.addIntValue(mailNum);
            sql.addIntValue(slbSid);
            sql.addIntValue(sacSid);


            log__.info(sql.toLogString());
            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closePreparedStatement(pstmt);
            pstmt = null;
            sql = null;
        }
    }

    /**
     * <br>[機  能] 指定したメールがフィルタリングの条件と一致した場合フィルタリングをする
     * <br>[解  説] フィルター情報の更新内容に従ってメール情報の更新を行う
     * <br>[備  考]
     * @param filterData フィルター情報
     * @param conditionList フィルター条件
     * @param sacSid 更新対象のアカウントSID
     * @param smjSid 受信メールSID
     * @throws SQLException SQL実行時例外
     */
    public void setFilterMailSid(MailFilterModel filterData,
                                    List<MailFilterConditionModel> conditionList,
                                    int sacSid,
                                    int smjSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SqlBuffer sql = null;

        try {

            //更新対象のメッセージ番号を取得する
            sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   distinct");
            sql.addSql("   SML_JMEIS_DETAIL.smjSid as SMJ_SID");
            setFromSqlForFiltering(sql, filterData, conditionList, sacSid, smjSid);

            log__.info(sql.toLogString());
            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            boolean updateFlg = false;
            if (rs.next()) {
                updateFlg = true;
            }

            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pstmt);
            if (updateFlg) {
                //既読にする or ゴミ箱へ移動
                if (filterData.isReaded() || filterData.isDust()) {
                    sql = new SqlBuffer();
                    sql.addSql(" update");
                    sql.addSql("   SML_JMEIS");
                    sql.addSql(" set");

                    if (filterData.isReaded()) {
                        sql.addSql("   SMJ_OPKBN = ?");
                        sql.addIntValue(GSConstSmail.OPKBN_OPENED);
                    }
                    if (filterData.isDust()) {
                        if (filterData.isReaded()) {
                            sql.addSql("   ,");
                        }
                        sql.addSql("   SMJ_JKBN = ?");
                        sql.addIntValue(GSConstSmail.SML_JTKBN_GOMIBAKO);
                    }

                    sql.addSql(" where");
                    sql.addSql("   SAC_SID = ?");
                    sql.addSql("  and ");
                    sql.addSql("   SMJ_SID = ?");
                    sql.addIntValue(sacSid);
                    sql.addIntValue(smjSid);

                    log__.info(sql.toLogString());

                    pstmt = getCon().prepareStatement(sql.toSqlString());
                    sql.setParameter(pstmt);
                    pstmt.executeUpdate();
                    JDBCUtil.closePreparedStatement(pstmt);
                    pstmt = null;
                }


                //ラベルを付与
                if (filterData.isLabel()) {
                    SmlLabelModel lbMdl = null;
                    SmlLabelDao lbDao = new SmlLabelDao(getCon());
                    if (filterData.getLabelSid() > 0) {
                        lbMdl = lbDao.select(filterData.getLabelSid());
                    }

                    if (lbMdl != null) {

                        sql = new SqlBuffer();
                        sql.addSql(" insert into");
                        sql.addSql(" SML_JMEIS_LABEL (");
                        sql.addSql("   SLB_SID,");
                        sql.addSql("   SMJ_SID,");
                        sql.addSql("   SAC_SID");
                        sql.addSql(" )");
                        sql.addSql(" select ");
                        sql.addSql("   distinct ");
                        sql.addSql(filterData.getLabelSid() + ",");
                        sql.addSql(smjSid + ",");
                        sql.addSql(String.valueOf(sacSid));
                        sql.addSql(" from");
                        sql.addSql("  SML_ACCOUNT");
                        sql.addSql(" where");
                        sql.addSql("   not exists (");
                        sql.addSql("     select 1 from SML_JMEIS_LABEL");
                        sql.addSql("     where");
                        sql.addSql("       SLB_SID = ?");
                        sql.addSql("     and");
                        sql.addSql("       SMJ_SID = ?");
                        sql.addSql("     and");
                        sql.addSql("       SAC_SID = ?");
                        sql.addSql("   )");
                        sql.addSql("  and ");
                        sql.addSql("   SAC_SID = ?");

                        sql.addIntValue(filterData.getLabelSid());
                        sql.addIntValue(smjSid);
                        sql.addIntValue(sacSid);
                        sql.addIntValue(sacSid);

                        log__.info(sql.toLogString());
                        pstmt = getCon().prepareStatement(sql.toSqlString());
                        sql.setParameter(pstmt);
                        pstmt.executeUpdate();

                    }
                }

            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pstmt);
            pstmt = null;
            sql = null;
        }

    }

    /**
     * <br>[機  能] アカウントが指定したバイナリのデータが取得可能かチェックします。
     * <br>[解  説]
     * <br>[備  考]
     * @param sacSid アカウントSID
     * @param smlSid メールSID
     * @param binSid バイナリSID
     * @return true: 閲覧可能 false:閲覧不可
     * @throws SQLException SQL実行例外
     */
    public boolean isCheckSmailImage(int sacSid, int smlSid, Long binSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        int cnt = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql("   select");
            sql.addSql("     count(*) as CNT");
            sql.addSql("   from");
            sql.addSql("     (");
            sql.addSql("     select");
            sql.addSql("       SML_SMEIS.SAC_SID as SAC_SID");
            sql.addSql("     from");
            sql.addSql("       SML_SMEIS,");
            sql.addSql("       SML_BIN");
            sql.addSql("     where");
            sql.addSql("       SML_SMEIS.SMS_SID = ?");
            sql.addSql("     and");
            sql.addSql("       SML_SMEIS.SMS_SID = SML_BIN.SML_SID");
            sql.addSql("     and");
            sql.addSql("       SML_BIN.BIN_SID = ?");
            sql.addSql("     union");
            sql.addSql("     select");
            sql.addSql("       SML_JMEIS.SAC_SID as SAC_SID");
            sql.addSql("     from");
            sql.addSql("       SML_JMEIS,");
            sql.addSql("       SML_BIN");
            sql.addSql("     where");
            sql.addSql("       SML_JMEIS.SMJ_SID = ?");
            sql.addSql("     and");
            sql.addSql("       SML_JMEIS.SMJ_SID = SML_BIN.SML_SID");
            sql.addSql("     and");
            sql.addSql("       SML_BIN.BIN_SID = ?");
            sql.addSql("     ) as SAC_SID");
            sql.addSql("   where");
            sql.addSql("     SAC_SID = ?");


            sql.addIntValue(smlSid);
            sql.addLongValue(binSid);
            sql.addIntValue(smlSid);
            sql.addLongValue(binSid);
            sql.addIntValue(sacSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
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
     *
     * <br>[機  能] フィルタリングされた受信メール検索する共通のFROM句を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SQLバッファ
     * @param filterData フィルター情報
     * @param conditionList フィルター条件
     * @param sacSid 対象のアカウントSID
     * @param smjSid 対象のメールSID 0以下はSIDによる制限なし
     */
    public void setFromSqlForFiltering(SqlBuffer sql, MailFilterModel filterData,
                                    List<MailFilterConditionModel> conditionList,
                                    int sacSid, int smjSid) {

        boolean existCondition = conditionList != null && !conditionList.isEmpty();



        String condition = "   and";
        if (filterData.getCondition() == GSConstSmail.FILTER_CONDITION_OR) {
            condition = " or";
        }
        sql.addSql("  from");
        sql.addSql("  (");
        sql.addSql("      select ");
        sql.addSql("          distinct SML_JMEIS.SMJ_SID as smjSid ");
        sql.addSql("          ,SML_JMEIS.SMJ_OPKBN as smjOpkbn ");
        sql.addSql("          ,SML_JMEIS.SMJ_SENDKBN as smjSendkbn ");
        sql.addSql("          ,SML_JMEIS.SMJ_RTN_KBN as smjRtnKbn ");
        sql.addSql("          ,SML_JMEIS.SMJ_FW_KBN as smjFwKbn ");
        sql.addSql("          ,SML_SMEIS.SMS_MARK as smsMark ");
        sql.addSql("          ,SML_SMEIS.SMS_TITLE as smsTitle ");
        sql.addSql("          ,SML_SMEIS.SMS_BODY as smsBody ");
        sql.addSql("          ,SML_SMEIS.SMS_BODY_PLAIN as smsBodyPlain ");
        sql.addSql("          ,SML_SMEIS.SMS_SDATE as smsSdate ");
        sql.addSql("          ,SML_SMEIS.SMS_SIZE as smsSize ");
        sql.addSql("          ,SML_ACCOUNT.SAC_SID as sacSid ");
        sql.addSql("          ,SML_ACCOUNT.SAC_NAME as sacName ");
        sql.addSql("          ,SML_ACCOUNT.SAC_JKBN as sacJkbn ");
        sql.addSql("          ,CMN_USRM.USR_SID as usrSid ");
        sql.addSql("          ,CMN_USRM.USR_JKBN as usrJkbn ");
        sql.addSql("          ,CMN_USRM_INF.USI_SEI as usiSei ");
        sql.addSql("          ,CMN_USRM_INF.USI_MEI as usiMei ");
        sql.addSql("          ,CMN_USRM_INF.USI_SEI_KN as usiSeiKn ");
        sql.addSql("          ,CMN_USRM_INF.USI_MEI_KN as usiMeiKn ");
        sql.addSql("          ,CMN_USRM_INF.BIN_SID as binSid ");
        sql.addSql("          ,CMN_USRM_INF.USI_PICT_KF as usiPictKf ");
        if (filterData.isTempFile()) {
            sql.addSql("      ,BINCT.CT as binCnt ");
        } else {
            sql.addSql("      ,0 as binCnt ");
        }
        sql.addSql("      from ");
        sql.addSql("          SML_ACCOUNT ");
        sql.addSql("              left join CMN_USRM ");
        sql.addSql("                      on SML_ACCOUNT.USR_SID = CMN_USRM.USR_SID ");
        sql.addSql("              left join CMN_USRM_INF ");
        sql.addSql("                      on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID ");
        sql.addSql("          ,SML_SMEIS ");
        sql.addSql("          ,SML_JMEIS ");
        //添付ファイル
        if (filterData.isTempFile()) {
            sql.addSql("          , ( ");
            sql.addSql("                  select ");
            sql.addSql("                      SML_SID ,");
            sql.addSql("                      count(*) as CT ");
            sql.addSql("                  from ");
            sql.addSql("                      SML_BIN ");
            sql.addSql("                  where ");
            sql.addSql("                      SML_SID in ( ");
            sql.addSql("                           select ");
            sql.addSql("                               SMJ_SID ");
            sql.addSql("                           from ");
            sql.addSql("                               SML_JMEIS ");
            sql.addSql("                           where ");
            sql.addSql("                               SAC_SID = ? ");
            sql.addIntValue(sacSid);
            if (smjSid > 0) {
                sql.addSql("                           and SML_SID = ? ");
                sql.addIntValue(smjSid);
            }
            sql.addSql("                      ) ");
            sql.addSql("                  group by SML_SID");
            sql.addSql("              ) BINCT ");
        }
        sql.addSql("      where ");
        sql.addSql("          SML_JMEIS.SAC_SID = ? ");
        sql.addIntValue(sacSid);
        if (smjSid > 0) {
            sql.addSql("      AND SML_JMEIS.SMJ_SID = ? ");
            sql.addIntValue(smjSid);
        }
        if (existCondition) {
            sql.addSql("      and (");
            boolean first = true;
            for (MailFilterConditionModel condMdl : conditionList) {
                if (!first) {
                    sql.addSql(condition);
                }
                first = false;
                __setWhereConditionSql(sql,
                                            condMdl,
                                            sacSid, smjSid);
            }
            sql.addSql("            )");
        }
        sql.addSql("          and SML_JMEIS.SMJ_SID = SML_SMEIS.SMS_SID ");
        sql.addSql("          and SML_SMEIS.SAC_SID = SML_ACCOUNT.SAC_SID ");
        sql.addSql("          and SML_JMEIS.SMJ_JKBN = ? ");
        sql.addIntValue(GSConstSmail.SML_JTKBN_TOROKU);
        //添付ファイル
        if (filterData.isTempFile()) {
                sql.addSql("  and SML_JMEIS.SMJ_SID = BINCT.SML_SID");
        }
        sql.addSql("  ) SML_JMEIS_DETAIL ");
    }
    /**
     * <br>[機  能] 検索条件SQL(フィルター条件)を指定されたSqlBufferへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SqlBuffer
     * @param conditionMdl フィルター条件情報
     * @param sacSid アカウントSID
     * @param smjSid 対象のメールSID 0以下はSIDによる制限なし
     */
    private void __setWhereConditionSql(SqlBuffer sql,
                                            MailFilterConditionModel conditionMdl,
                                            int sacSid, int smjSid) {

        if (!StringUtil.isNullZeroString(conditionMdl.getText())) {

            String value = __getPatternString(conditionMdl);
            int conditionType = conditionMdl.getType();


            sql.addSql("            SML_JMEIS.SMJ_SID");
            if (conditionMdl.getExpression() == GSConstSmail.FILTER_TYPE_EXCLUDE) {
                sql.addSql("             not ");
            }
            sql.addSql("             in ( ");
            sql.addSql("              select ");
            sql.addSql("                  distinct SML_SMEIS.SMS_SID as smsSid ");
            sql.addSql("              from ");
            sql.addSql("                  SML_SMEIS ");
            if (conditionType == GSConstSmail.FILTER_TYPE_SEND) {
                sql.addSql("                  left join SML_ACCOUNT SML_ACCOUNT1 ");
                sql.addSql("                      on SML_ACCOUNT1.SAC_SID = SML_SMEIS.SAC_SID ");
            }
            if (conditionType == GSConstSmail.FILTER_TYPE_ADDRESS
                    || conditionType == GSConstSmail.FILTER_TYPE_CC) {
                sql.addSql("              ,SML_JMEIS ");
                sql.addSql("                  left join SML_ACCOUNT SML_ACCOUNT2 ");
                sql.addSql("                      on SML_ACCOUNT2.SAC_SID = SML_JMEIS.SAC_SID ");
            }
            sql.addSql("              where ");
            if (smjSid > 0) {
                sql.addSql("              SML_SMEIS.SMS_SID = ? ");
                sql.addIntValue(smjSid);
            } else {
            sql.addSql("                  SML_SMEIS.SMS_SID in ( ");
            sql.addSql("                      select ");
            sql.addSql("                          SMJ_SID ");
            sql.addSql("                      from ");
            sql.addSql("                          SML_JMEIS ");
            sql.addSql("                      where ");
            sql.addSql("                          SAC_SID = ? ");
            sql.addIntValue(sacSid);
            sql.addSql("                  ) ");
            }
            if (conditionType == GSConstSmail.FILTER_TYPE_ADDRESS
                    || conditionType == GSConstSmail.FILTER_TYPE_CC) {
                sql.addSql("              and SML_SMEIS.SMS_SID = SML_JMEIS.SMJ_SID ");
            }
                switch (conditionType) {

                case GSConstSmail.FILTER_TYPE_ADDRESS:
                    //宛先
                    sql.addSql("    and");
                    sql.addSql("      SML_JMEIS.SMJ_SENDKBN = "
                                    + GSConstSmail.SML_SEND_KBN_ATESAKI);
                    sql.addSql("    and");
                    sql.addSql("      SML_ACCOUNT2.SAC_NAME " + value);
                    sql.addSql("    ");
                    break;

                case GSConstSmail.FILTER_TYPE_CC:
                    //CC
                    sql.addSql("    and");
                    sql.addSql("      SML_JMEIS.SMJ_SENDKBN = "
                                    + GSConstSmail.SML_SEND_KBN_CC);
                    sql.addSql("    and");
                    sql.addSql("      SML_ACCOUNT2.SAC_NAME " + value);
                    sql.addSql("    ");
                    break;
                case GSConstSmail.FILTER_TYPE_TITLE:
                    //件名
                    sql.addSql("    and");
                    sql.addSql("      SML_SMEIS.SMS_TITLE" + value);
                    break;


                case GSConstSmail.FILTER_TYPE_SEND:
                    //送信者
                    sql.addSql("    and");
                    sql.addSql("      SML_ACCOUNT1.SAC_NAME " + value);
                    break;

                case GSConstSmail.FILTER_TYPE_MAIN:

                    //本文
                    sql.addSql("    and");
                    sql.addSql("          (");
                    sql.addSql("          (SML_SMEIS.SMS_BODY" + value);
                    sql.addSql("           )");
                    sql.addSql("        or");
                    sql.addSql("          (SML_SMEIS.SMS_BODY_PLAIN" + value);
                    sql.addSql("           ))");

                default :
                }
        }
        sql.addSql("      ) ");

    }
}