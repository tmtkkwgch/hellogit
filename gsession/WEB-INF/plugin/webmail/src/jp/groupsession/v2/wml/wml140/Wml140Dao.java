package jp.groupsession.v2.wml.wml140;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.DBUtilFactory;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.IDbUtil;
import jp.groupsession.v2.wml.model.base.LabelDataModel;
import jp.groupsession.v2.wml.model.base.WmlFilterConditionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール フィルタ登録画面で使用するDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml140Dao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml140Dao.class);

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     */
    public Wml140Dao(Connection con) {
        super(con);
    }

    /**
     * <br>[機  能] ラベル情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wml130account アカウントSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public List<LabelDataModel> getLabelList(int wml130account)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<LabelDataModel> ret =
            new ArrayList<LabelDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WLB_SID, ");
            sql.addSql("   WLB_NAME, ");
            sql.addSql("   WLB_ORDER ");
            sql.addSql(" from ");
            sql.addSql("   WML_LABEL ");
            sql.addSql(" where ");
            sql.addSql("    (WLB_TYPE = ? ");
            sql.addSql("   or (WLB_TYPE = ? ");
            sql.addSql("   and ");
            sql.addSql("        WAC_SID = ?)) ");
            sql.addSql(" order by ");
            sql.addSql("   WLB_ORDER asc ");
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(GSConstWebmail.LABELTYPE_ALL);
            sql.addIntValue(GSConstWebmail.LABELTYPE_ONES);
            sql.addIntValue(wml130account);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                LabelDataModel model = new LabelDataModel();
                model.setLabelSid(rs.getInt("WLB_SID"));
                model.setLabelName(rs.getString("WLB_NAME"));
                model.setLabelOrder(rs.getInt("WLB_ORDER"));
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
     * <br>[機  能] ラベル情報を取得する(全てのアカウント)
     * <br>[解  説]
     * <br>[備  考]
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public List<LabelDataModel> getLabelListOnlyAll()
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<LabelDataModel> ret =
            new ArrayList<LabelDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WLB_SID, ");
            sql.addSql("   WLB_NAME, ");
            sql.addSql("   WLB_ORDER ");
            sql.addSql(" from ");
            sql.addSql("   WML_LABEL ");
            sql.addSql(" where ");
            sql.addSql("   WLB_TYPE = ? ");
            sql.addSql(" order by ");
            sql.addSql("   WLB_ORDER asc ");
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(GSConstWebmail.LABELTYPE_ALL);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                LabelDataModel model = new LabelDataModel();
                model.setLabelSid(rs.getInt("WLB_SID"));
                model.setLabelName(rs.getString("WLB_NAME"));
                model.setLabelOrder(rs.getInt("WLB_ORDER"));
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
     * <br>[機  能] フィルター条件を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wml130editFilter 編集対象フィルターSID
     * @return アカウントリスト
     * @throws SQLException SQL実行時例外
     */
    public List<WmlFilterConditionModel> selectFilCon(int wml130editFilter) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<WmlFilterConditionModel> ret = new ArrayList<WmlFilterConditionModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   WFC_NUM,");
            sql.addSql("   WFC_TYPE,");
            sql.addSql("   WFC_EXPRESSION,");
            sql.addSql("   WFC_TEXT");
            sql.addSql(" from");
            sql.addSql("   WML_FILTER_CONDITION");
            sql.addSql(" where ");
            sql.addSql("   WFT_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(wml130editFilter);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                WmlFilterConditionModel model = new WmlFilterConditionModel();

                model.setWfcNum(rs.getInt("WFC_NUM"));
                model.setWfcType(rs.getInt("WFC_TYPE"));
                model.setWfcExpression(rs.getInt("WFC_EXPRESSION"));
                model.setWfcText(rs.getString("WFC_TEXT"));
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
     * <br>[機  能] メール情報一覧の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @return メール情報一覧
     * @throws SQLException SQL実行時例外
     */
    public long getMailCount(Wml140ParamModel paramMdl, int userSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        long count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(WML_MAILDATA.WMD_MAILNUM) as CNT");
            sql = __setWhereSql(sql, paramMdl, userSid);

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getLong("CNT");
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
     * <br>[機  能] メール情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param start データ取得開始位置
     * @param maxCount 最大取得件数
     * @return メール情報一覧
     * @throws SQLException SQL実行時例外
     */
    public List<Wml140MailModel> getMailList(Wml140ParamModel paramMdl, int userSid,
                                            int start, int maxCount)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Wml140MailModel> mailList = new ArrayList<Wml140MailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_MAILDATA.WMD_MAILNUM as WMD_MAILNUM,");
            sql.addSql("   WML_MAILDATA.WMD_TITLE as WMD_TITLE,");
            sql.addSql("   WML_MAILDATA.WMD_SDATE as WMD_SDATE,");
            sql.addSql("   WML_MAILDATA.WMD_FROM as WMD_FROM,");
            sql.addSql("   WML_MAILDATA.WMD_TEMPFLG as WMD_TEMPFLG,");
            sql.addSql("   WML_MAILDATA.WMD_STATUS as WMD_STATUS,");
            sql.addSql("   WML_MAILDATA.WMD_REPLY as WMD_REPLY,");
            sql.addSql("   WML_MAILDATA.WMD_FORWARD as WMD_FORWARD,");
            sql.addSql("   WML_MAILDATA.WMD_READED as WMD_READED");

            sql = __setWhereSql(sql, paramMdl, userSid);

            String order = "";
            if (paramMdl.getWml140mailListOrder() == Wml140Form.WML140_ORDER_DESC) {
                order = " desc";
            }

            sql.addSql(" order by");
            switch (paramMdl.getWml140mailListSortKey()) {
                case Wml140Form.WML140_SKEY_SUBJECT:
                    sql.addSql("   WML_MAILDATA.WMD_TITLE" + order);
                    break;
                case Wml140Form.WML140_SKEY_FROM:
                    sql.addSql("   WML_MAILDATA.WMD_FROM" + order);
                    break;
                default:
                    sql.addSql("   WML_MAILDATA.WMD_SDATE" + order);
            }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString(),
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (start > 1) {
                rs.absolute(start - 1);
            }

            for (int i = 0; rs.next() && i < maxCount; i++) {
                Wml140MailModel mailData = new Wml140MailModel();

                mailData.setMailNum(rs.getLong("WMD_MAILNUM"));
                Timestamp sdate = rs.getTimestamp("WMD_SDATE");
                if (sdate != null) {
                    mailData.setDate(UDate.getInstanceTimestamp(sdate));
                }
                mailData.setReaded(rs.getInt("WMD_REPLY") == GSConstWebmail.WMD_READED_YES);
                mailData.setFrom(rs.getString("WMD_FROM"));
                mailData.setSubject(rs.getString("WMD_TITLE"));
                mailData.setReaded(rs.getInt("WMD_READED") == GSConstWebmail.READEDFLG_READED);
                mailData.setAttach(rs.getInt("WMD_TEMPFLG") == GSConstWebmail.TEMPFLG_EXIST);
                mailData.setReply(rs.getInt("WMD_REPLY") == GSConstWebmail.WMD_REPLY_REPLY);
                mailData.setForward(rs.getInt("WMD_FORWARD") == GSConstWebmail.WMD_FORWARD_FORWARD);

                //メール本文を取得
                mailData.setBody(__getMailBody(mailData.getMailNum()));

                mailList.add(mailData);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return mailList;
    }

    /**
     * <br>[機  能] 検索条件SQLを指定されたSqlBufferへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SqlBuffer
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @return SqlBuffer
     */
    private SqlBuffer __setWhereSql(SqlBuffer sql, Wml140ParamModel paramMdl, int userSid) {
        sql.addSql(" from");
        sql.addSql("   WML_MAILDATA,");
        sql.addSql("   WML_DIRECTORY");
        sql.addSql(" where");

        sql.addSql("   WML_DIRECTORY.WAC_SID = ?");
        sql.addSql(" and");
        sql.addSql("   WML_MAILDATA.WAC_SID = ?");
        sql.addIntValue(paramMdl.getWml130accountSid());
        sql.addIntValue(paramMdl.getWml130accountSid());

        sql.addSql(" and");
        sql.addSql("   WML_MAILDATA.WDR_SID = WML_DIRECTORY.WDR_SID");

        //添付ファイル
        if (NullDefault.getString(paramMdl.getWml140svTempFile(), "").equals("1")) {
            sql.addSql(" and");
            sql.addSql("   WML_MAILDATA.WMD_TEMPFLG = ?");
            sql.addIntValue(GSConstWebmail.TEMPFLG_EXIST);
        }

        //条件１～５
        int conditionLen = NullDefault.getString(paramMdl.getWml140svConditionText1(), "").length();
        conditionLen += NullDefault.getString(paramMdl.getWml140svConditionText2(), "").length();
        conditionLen += NullDefault.getString(paramMdl.getWml140svConditionText3(), "").length();
        conditionLen += NullDefault.getString(paramMdl.getWml140svConditionText4(), "").length();
        conditionLen += NullDefault.getString(paramMdl.getWml140svConditionText5(), "").length();

        if (conditionLen > 0) {
            sql.addSql(" and");
            sql.addSql("   (");

            String condition = "   and";
            if (paramMdl.getWml140svFilterType() == GSConstWebmail.FILTER_CONDITION_OR) {
                condition = "   or";
            }

            conditionLen = 0;
            if (!StringUtil.isNullZeroString(paramMdl.getWml140svConditionText1())) {
                sql = __setWhereConditionSql(sql,
                                            paramMdl.getWml140svConditionType1(),
                                            paramMdl.getWml140svConditionExs1(),
                                            paramMdl.getWml140svConditionText1(),
                                            paramMdl.getWml130accountSid());
                conditionLen += paramMdl.getWml140svConditionText1().length();
            }

            if (!StringUtil.isNullZeroString(paramMdl.getWml140svConditionText2())) {
                if (conditionLen > 0) {
                    sql.addSql(condition);
                }
                sql = __setWhereConditionSql(sql,
                                            paramMdl.getWml140svConditionType2(),
                                            paramMdl.getWml140svConditionExs2(),
                                            paramMdl.getWml140svConditionText2(),
                                            paramMdl.getWml130accountSid());
                conditionLen += paramMdl.getWml140svConditionText2().length();
            }

            if (!StringUtil.isNullZeroString(paramMdl.getWml140svConditionText3())) {
                if (conditionLen > 0) {
                    sql.addSql(condition);
                }
                sql = __setWhereConditionSql(sql,
                                            paramMdl.getWml140svConditionType3(),
                                            paramMdl.getWml140svConditionExs3(),
                                            paramMdl.getWml140svConditionText3(),
                                            paramMdl.getWml130accountSid());
                conditionLen += paramMdl.getWml140svConditionText3().length();
            }

            if (!StringUtil.isNullZeroString(paramMdl.getWml140svConditionText4())) {
                if (conditionLen > 0) {
                    sql.addSql(condition);
                }
                sql = __setWhereConditionSql(sql,
                                            paramMdl.getWml140svConditionType4(),
                                            paramMdl.getWml140svConditionExs4(),
                                            paramMdl.getWml140svConditionText4(),
                                            paramMdl.getWml130accountSid());
                conditionLen += paramMdl.getWml140svConditionText4().length();
            }

            if (!StringUtil.isNullZeroString(paramMdl.getWml140svConditionText5())) {
                if (conditionLen > 0) {
                    sql.addSql(condition);
                }
                sql = __setWhereConditionSql(sql,
                                            paramMdl.getWml140svConditionType5(),
                                            paramMdl.getWml140svConditionExs5(),
                                            paramMdl.getWml140svConditionText5(),
                                            paramMdl.getWml130accountSid());
            }

            sql.addSql("   )");
        }

        return sql;
    }

    /**
     * <br>[機  能] 検索条件SQL(フィルター条件)を指定されたSqlBufferへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SqlBuffer
     * @param type 種別
     * @param exs 式
     * @param text 条件
     * @param wacSid アカウントSID
     * @return SqlBuffer
     */
    private SqlBuffer __setWhereConditionSql(SqlBuffer sql, String type,
                                            String exs, String text,
                                            int wacSid) {

        if (!StringUtil.isNullZeroString(text)) {

            String value = " like '%"
                        + JDBCUtil.encFullStringLike(text)
                        + "%' ESCAPE '"
                        + JDBCUtil.def_esc
                        + "'";

            boolean exclude = exs.equals(String.valueOf(GSConstWebmail.FILTER_TYPE_EXCLUDE));
            if (exclude) {
                value = " not" + value;
            }

            int conditionType = Integer.parseInt(type);
            switch (conditionType) {
                case GSConstWebmail.FILTER_TYPE_TITLE:
                    //件名
                    sql.addSql("     WML_MAILDATA.WMD_TITLE" + value);
                    break;

                case GSConstWebmail.FILTER_TYPE_ADDRESS:
                case GSConstWebmail.FILTER_TYPE_CC:
                    //宛先 or CC
                    sql.addSql("     exists (");
                    sql.addSql("       select 1 from WML_SENDADDRESS");
                    sql.addSql("       where");
                    sql.addSql("         WML_SENDADDRESS.WAC_SID = " + wacSid);
                    sql.addSql("       and");
                    sql.addSql("         WML_MAILDATA.WAC_SID = WML_SENDADDRESS.WAC_SID");
                    sql.addSql("      and");
                    sql.addSql("         WML_MAILDATA.WMD_MAILNUM = WML_SENDADDRESS.WMD_MAILNUM");
                    sql.addSql("       and");
                    if (conditionType == GSConstWebmail.FILTER_TYPE_CC) {
                        sql.addSql("         WML_SENDADDRESS.WSA_TYPE = "
                                        + GSConstWebmail.WSA_TYPE_CC);
                    } else {
                        sql.addSql("         WML_SENDADDRESS.WSA_TYPE = "
                                        + GSConstWebmail.WSA_TYPE_TO);
                    }
                    sql.addSql("       and");
                    sql.addSql("         WML_SENDADDRESS.WSA_ADDRESS" + value);
                    sql.addSql("     )");
                    break;

                case GSConstWebmail.FILTER_TYPE_SEND:
                    //送信者
                    sql.addSql("     WML_MAILDATA.WMD_FROM" + value);
                    break;

                case GSConstWebmail.FILTER_TYPE_MAIN:
                    IDbUtil dbUtil = DBUtilFactory.getInstance();
                    boolean h2DB = dbUtil.getDbType() == GSConst.DBTYPE_H2DB;

                    //本文
                    if (!h2DB) {
                        //H2 Database以外
                        sql.addSql("     exists (");
                        sql.addSql("       select 1 from WML_MAIL_BODY");
                        sql.addSql("       where");
                        sql.addSql("         WML_MAILDATA.WAC_SID = WML_MAIL_BODY.WAC_SID");
                        sql.addSql("       and");
                        sql.addSql("         WML_MAILDATA.WMD_MAILNUM = WML_MAIL_BODY.WMD_MAILNUM");
                        sql.addSql("       and");
                        sql.addSql("         WML_DIRECTORY.WDR_SID <> -1");
                        sql.addSql("       and");
                        sql.addSql("         WML_MAIL_BODY.WMB_BODY" + value);
                        sql.addSql("     )");
                    } else {
                        //H2 Database
                        if (exclude) {
                            sql.addSql("     WML_MAILDATA.WMD_MAILNUM not in (");
                        } else {
                            sql.addSql("     WML_MAILDATA.WMD_MAILNUM in (");
                        }
                        sql.addSql("       select substr(QUERY, 46, length(QUERY)) as MAILNUM");
                        sql.addSql("       from FTL_SEARCH('"
                                + JDBCUtil.encFullStringH2Lucene(text)
                                + "', 0, 0) FT");
                        sql.addSql("       where QUERY like '\"PUBLIC\".\"WML_MAIL_BODY\""
                                + " WHERE \"WMD_MAILNUM\"=%' ESCAPE '"
                                + JDBCUtil.def_esc + "'");
                        sql.addSql("     )");
                    }
                default :
            }
        }

        return sql;
    }

    /**
     * <br>[機  能] 指定したメールの本文を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNum メッセージ番号
     * @return ラベル情報
     * @throws SQLException SQL実行時例外
     */
    private String __getMailBody(long mailNum) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String body = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WMB_BODY");
            sql.addSql(" from");
            sql.addSql("   WML_MAIL_BODY");
            sql.addSql(" where");
            sql.addSql("   WMD_MAILNUM = ?");
            sql.addLongValue(mailNum);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                body = rs.getString("WMB_BODY");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return body;
    }

}
