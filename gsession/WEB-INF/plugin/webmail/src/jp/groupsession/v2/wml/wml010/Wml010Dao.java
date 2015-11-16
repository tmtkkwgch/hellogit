package jp.groupsession.v2.wml.wml010;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.DBUtilFactory;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstWebmail;
import jp.groupsession.v2.cmn.IDbUtil;
import jp.groupsession.v2.cmn.dao.WmlDao;
import jp.groupsession.v2.cmn.dao.base.CmnCmbsortConfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnCmbsortConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;
import jp.groupsession.v2.wml.biz.WmlBiz;
import jp.groupsession.v2.wml.dao.WebmailDao;
import jp.groupsession.v2.wml.wml010.model.Wml010AccountModel;
import jp.groupsession.v2.wml.wml010.model.Wml010AddressModel;
import jp.groupsession.v2.wml.wml010.model.Wml010DirectoryModel;
import jp.groupsession.v2.wml.wml010.model.Wml010LabelModel;
import jp.groupsession.v2.wml.wml010.model.Wml010MailModel;
import jp.groupsession.v2.wml.wml010.model.Wml010SearchModel;
import jp.groupsession.v2.wml.wml010.model.Wml010SendAddrModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] WEBメール メール一覧画面で使用するDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Wml010Dao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Wml010Dao.class);

    /** キーワード種別 件名 */
    private static final int KEYWORDTYPE_TITLE__ = 0;
    /** キーワード種別 本文 */
    private static final int KEYWORDTYPE_BODY__ = 1;

    /**
     * <p>Default Constructor
     */
    public Wml010Dao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public Wml010Dao(Connection con) {
        super(con);
    }

    /**
     * <br>[機  能] 指定したアカウント内メールの最小/最大送信日を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wacSid アカウントSID
     * @return 日付
     * @throws SQLException SQL実行時例外
     */
    public UDate[] getMailDate(int wacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UDate[] mailDate = null;
        Connection con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_MAILDATA.WAC_SID,");
            sql.addSql("   min(WML_MAILDATA.WMD_SDATE) as FRDATE,");
            sql.addSql("   max(WML_MAILDATA.WMD_SDATE) as TODATE");
            sql.addSql(" from");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where");
            sql.addSql("   WML_MAILDATA.WAC_SID = ?");
            sql.addSql(" group by");
            sql.addSql("   WML_MAILDATA.WAC_SID");
            sql.addIntValue(wacSid);

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                mailDate = new UDate[] {
                            UDate.getInstanceTimestamp(rs.getTimestamp("FRDATE")),
                            UDate.getInstanceTimestamp(rs.getTimestamp("TODATE"))};
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return mailDate;
    }

    /**
     * <br>[機  能] メール情報一覧の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param searchMdl 検索条件
     * @return メール情報一覧
     * @throws SQLException SQL実行時例外
     */
    public long getMailCount(Wml010SearchModel searchMdl)
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
            sql.addSql("   count(MAILDATA.WMD_MAILNUM) as CNT");
            sql = __setWhereSql(sql, searchMdl);

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
     * @param searchMdl 検索条件
     * @param bodyLimitLen メール本文の最大文字数
     * @return メール情報一覧
     * @throws SQLException SQL実行時例外
     */
    public List<Wml010MailModel> getMailList(Wml010SearchModel searchMdl, int bodyLimitLen)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Wml010MailModel> mailList = new ArrayList<Wml010MailModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   MAILDATA.WMD_MAILNUM as WMD_MAILNUM,");
            sql.addSql("   MAILDATA.WMD_TITLE as WMD_TITLE,");
            sql.addSql("   MAILDATA.WMD_SDATE as WMD_SDATE,");
            sql.addSql("   MAILDATA.WMD_FROM as WMD_FROM,");
            sql.addSql("   MAILDATA.WMD_TEMPFLG as WMD_TEMPFLG,");
            sql.addSql("   MAILDATA.WMD_STATUS as WMD_STATUS,");
            sql.addSql("   MAILDATA.WMD_REPLY as WMD_REPLY,");
            sql.addSql("   MAILDATA.WMD_FORWARD as WMD_FORWARD,");
            sql.addSql("   MAILDATA.WMD_READED as WMD_READED,");
            sql.addSql("   MAILDATA.WMD_SIZE as WMD_SIZE,");
            sql.addSql("   MAILDATA.WDR_SID as WDR_SID,");
            sql.addSql("   MAILDATA.WDR_TYPE as WDR_TYPE");

            sql = __setWhereSql(sql, searchMdl);

            String order = "";
            if (searchMdl.getOrder() == GSConstWebmail.ORDER_DESC) {
                order = " desc";
            }
            sql.addSql(" order by");
            switch (searchMdl.getSortKey()) {
                case Wml010Const.SORTKEY_TEMPFILE:
                    sql.addSql("   MAILDATA.WMD_TEMPFLG" + order);
                    sql.addSql("   ,MAILDATA.WMD_SDATE desc");
                    break;
                case Wml010Const.SORTKEY_TITLE:
                    sql.addSql("   MAILDATA.WMD_TITLE" + order);
                    break;
                case Wml010Const.SORTKEY_MAILADDRESS:
                    if (WmlBiz.isSendDirType(searchMdl.getDirectoryType())) {
                        sql.addSql("   MAILDATA.WSA_ADDRESS" + order);
                    } else {
                        sql.addSql("   MAILDATA.WMD_FROM" + order);
                    }
                    sql.addSql("   ,MAILDATA.WMD_SDATE desc");
                    break;
                case Wml010Const.SORTKEY_SDATE:
                    sql.addSql("   MAILDATA.WMD_SDATE" + order);
                    break;
                case Wml010Const.SORTKEY_READED:
                    sql.addSql("   MAILDATA.WMD_READED" + order);
                    sql.addSql("   ,MAILDATA.WMD_SDATE desc");
                    break;
                case Wml010Const.SORTKEY_SIZE:
                    sql.addSql("   MAILDATA.WMD_SIZE" + order);
                    sql.addSql("   ,MAILDATA.WMD_SDATE desc");
                    break;
                default:
                    sql.addSql("   MAILDATA.WMD_SDATE");
            }

            sql.setPagingValue(searchMdl.getStart() - 1, searchMdl.getMaxCount());

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString(),
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            WebmailDao wmlDao = new WebmailDao(con);

            while (rs.next()) {
                Wml010MailModel mailData = new Wml010MailModel();

                mailData.setMailNum(rs.getLong("WMD_MAILNUM"));
                mailData.setDirSid(rs.getLong("WDR_SID"));
                Timestamp sdate = rs.getTimestamp("WMD_SDATE");
                if (sdate != null) {
                    mailData.setDate(UDate.getInstanceTimestamp(sdate));
                }
                mailData.setReaded(rs.getInt("WMD_REPLY") == GSConstWebmail.WMD_READED_YES);
                mailData.setFrom(rs.getString("WMD_FROM"));
                mailData.setSubject(StringUtil.replaceReturnCode(rs.getString("WMD_TITLE"), ""));
                mailData.setReaded(rs.getInt("WMD_READED") == GSConstWebmail.READEDFLG_READED);
                mailData.setAttach(rs.getInt("WMD_TEMPFLG") == GSConstWebmail.TEMPFLG_EXIST);
                mailData.setReply(rs.getInt("WMD_REPLY") == GSConstWebmail.WMD_REPLY_REPLY);
                mailData.setForward(rs.getInt("WMD_FORWARD") == GSConstWebmail.WMD_FORWARD_FORWARD);
                mailData.setMailSize(rs.getLong("WMD_SIZE"));

                int wdrType = rs.getInt("WDR_TYPE");
                mailData.setCanEditMail(
                        wdrType == GSConstWebmail.DIR_TYPE_SENDED
                        || wdrType == GSConstWebmail.DIR_TYPE_DRAFT
                        || wdrType == GSConstWebmail.DIR_TYPE_NOSEND);
                mailData.setSendWaitMail(wdrType == GSConstWebmail.DIR_TYPE_NOSEND);

                mailList.add(mailData);
            }

            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);

            for (int index = 0; index < mailList.size(); index++) {
                long mailNum = mailList.get(index).getMailNum();
                int wacSid = searchMdl.getAccountSid();

                //メール本文を取得
                mailList.get(index).setBody(__getMailBody(wacSid, mailNum, bodyLimitLen));

                //ラベル名称を設定
                mailList.get(index).setLabelList(__getLabelData(wacSid, mailNum));

                //送信先メールアドレスを設定
                mailList.get(index).setSendAddress(getSendAddress(wacSid, mailNum));

                //添付ファイル情報を設定
                mailList.get(index).setTempFileList(wmlDao.getTempFileList(mailNum));

                //メール情報_送信予定を設定
                __setSendPlan(mailList.get(index));
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
     * <br>[機  能] 指定したアカウントを除くアカウント情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wacSid 除外するアカウントのアカウントSID
     * @param userSid ユーザSID
     * @return アカウント情報一覧
     * @throws SQLException SQL実行時例外
     */
    public List<Wml010AccountModel> getNotSelectAccountList(int wacSid, int userSid)
    throws SQLException {
        //アカウント代理人が許可されているかを判定する
        WmlBiz wmlBiz = new WmlBiz();
        boolean proxyUserFlg = wmlBiz.isProxyUserAllowed(getCon());

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<Wml010AccountModel> accountList = new ArrayList<Wml010AccountModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_ACCOUNT.WAC_SID as WAC_SID,");
            sql.addSql("   WML_ACCOUNT.WAC_NAME as WAC_NAME");
            sql.addSql(" from ");
            sql.addSql("   WML_ACCOUNT");
            sql.addSql("   left join");
            sql.addSql("     (");
            sql.addSql("       select WAC_SID, WAS_SORT");
            sql.addSql("       from WML_ACCOUNT_SORT");
            sql.addSql("       where USR_SID = ?");
            sql.addSql("     ) ACCOUNT_SORT");
            sql.addSql("   on");
            sql.addSql("     WML_ACCOUNT.WAC_SID = ACCOUNT_SORT.WAC_SID");
            sql.addIntValue(userSid);

            WmlDao wmlDao = new WmlDao();
            wmlDao.setAccountSearchSql(sql, userSid, proxyUserFlg);

            if (wacSid > 0) {
                sql.addSql(" and");
                sql.addSql("   WML_ACCOUNT.WAC_SID <> ?");
                sql.addIntValue(wacSid);
            }
            sql.addSql(" group by");
            sql.addSql("   WML_ACCOUNT.WAC_SID, WML_ACCOUNT.WAC_NAME, ACCOUNT_SORT.WAS_SORT");
            sql.addSql(" order by");
            sql.addSql("   ACCOUNT_SORT.WAS_SORT, WML_ACCOUNT.WAC_NAME");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Wml010AccountModel accountData = new Wml010AccountModel();

                accountData.setWacSid(rs.getInt("WAC_SID"));
                accountData.setWacName(rs.getString("WAC_NAME"));
                accountData = setNotReadCount(accountData);
                accountList.add(accountData);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return accountList;
    }

    /**
     * <br>[機  能] 指定したアカウント情報に未読メール件数を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param accountData アカウント情報
     * @return アカウント情報
     * @throws SQLException SQL実行時例外
     */
    public Wml010AccountModel setNotReadCount(Wml010AccountModel accountData)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(*) as NOREAD_COUNT");
            sql.addSql(" from ");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where");
            sql.addSql("   WAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   WDR_SID in (");
            sql.addSql("     select WDR_SID from WML_DIRECTORY");
            sql.addSql("     where WML_DIRECTORY.WAC_SID = ?");
            sql.addSql("     and WML_DIRECTORY.WDR_TYPE <> ?");
            sql.addSql("   )");
            sql.addSql(" and");
            sql.addSql("   WMD_READED = ?");

            sql.addIntValue(accountData.getWacSid());
            sql.addIntValue(accountData.getWacSid());
            sql.addIntValue(GSConstWebmail.DIR_TYPE_DUST);
            sql.addIntValue(GSConstWebmail.READEDFLG_NOREAD);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                accountData.setNotReadCount(rs.getLong("NOREAD_COUNT"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return accountData;
    }

    /**
     * <br>[機  能] 検索条件SQLを指定されたSqlBufferへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SqlBuffer
     * @param searchMdl 検索条件
     * @return SqlBuffer
     */
    private SqlBuffer __setWhereSql(SqlBuffer sql, Wml010SearchModel searchMdl) {

        sql.addSql(" from");
        sql.addSql("   (");
        if (StringUtil.isNullZeroString(searchMdl.getKeyword())) {
            __setWhereSql(sql, searchMdl, -1);
        } else {
            __setWhereSql(sql, searchMdl, KEYWORDTYPE_TITLE__);
            sql.addSql("  union");
            __setWhereSql(sql, searchMdl, KEYWORDTYPE_BODY__);
        }
        sql.addSql("   ) MAILDATA");
        return sql;
    }

    /**
     * <br>[機  能] 検索条件SQLを指定されたSqlBufferへ設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SqlBuffer
     * @param searchMdl 検索条件
     * @param keywordType 検索条件:キーワード の対象(0:件名 1:本文)
     * @return SqlBuffer
     */
    private SqlBuffer __setWhereSql(SqlBuffer sql, Wml010SearchModel searchMdl,
                                    int keywordType) {
        int wacSid = searchMdl.getAccountSid();
        boolean existAccount = wacSid > 0;


        sql.addSql("    select");
        sql.addSql("      WML_MAILDATA.*,");
        sql.addSql("      WML_DIRECTORY.WDR_TYPE");
        if (WmlBiz.isSendDirType(searchMdl.getDirectoryType())) {
            sql.addSql("      ,SENDADDRESS.WSA_ADDRESS as WSA_ADDRESS");
        }

        sql.addSql("    from");
        sql.addSql("      WML_DIRECTORY,");

        //H2 全文検索の場合のみ
        IDbUtil dbUtil = DBUtilFactory.getInstance();
        if (dbUtil.getDbType() == GSConst.DBTYPE_H2DB) {
            if (!StringUtil.isNullZeroString(searchMdl.getKeyword())
            && keywordType == KEYWORDTYPE_BODY__) {
                sql.addSql("      (select substr(QUERY, 46, length(QUERY)) as MAILNUM"
                        + "  from FTL_SEARCH('"
                        + JDBCUtil.encFullStringH2Lucene(searchMdl.getKeyword())
                        + "', 0, 0) as FT where QUERY like '\"PUBLIC\".\"WML_MAIL_BODY\""
                        + " WHERE \"WMD_MAILNUM\"=%' ESCAPE '"
                        + JDBCUtil.def_esc + "') as FT,");
            }
        }

        sql.addSql("      WML_MAILDATA");
        if (WmlBiz.isSendDirType(searchMdl.getDirectoryType())) {
            sql.addSql("      left join");
            sql.addSql("        (");
            sql.addSql("          select WMD_MAILNUM, WSA_ADDRESS from WML_SENDADDRESS");
            sql.addSql("          where");
            if (existAccount) {
                sql.addSql("            WAC_SID = ?");
                sql.addSql("          and");
                sql.addIntValue(wacSid);
            }
            sql.addSql("           WSA_NUM = ?");
            sql.addSql("        ) SENDADDRESS");
            sql.addSql("      on");
            sql.addSql("        WML_MAILDATA.WMD_MAILNUM = SENDADDRESS.WMD_MAILNUM");
            sql.addIntValue(1);
        }

        sql.addSql("    where");

        boolean searchFlg = false;

        //メッセージ番号
        if (searchMdl.getMailNum() > 0) {
            sql.addSql("      WML_MAILDATA.WMD_MAILNUM = ?");
            sql.addLongValue(searchMdl.getMailNum());
            searchFlg = true;
        }

        //ディレクトリ
        if (searchMdl.getDirectorySid() > 0) {
            if (searchFlg) {
                sql.addSql("    and");
            }
            sql.addSql("      WML_DIRECTORY.WDR_SID = ?");
            sql.addSql("    and");
            sql.addSql("      WML_MAILDATA.WDR_SID = ?");
            sql.addLongValue(searchMdl.getDirectorySid());
            sql.addLongValue(searchMdl.getDirectorySid());
            searchFlg = true;
        }

        //アカウント
        if (existAccount) {
            if (searchFlg) {
                sql.addSql("    and");
            }
            sql.addSql("      WML_DIRECTORY.WAC_SID = ?");
            sql.addSql("    and");
            sql.addSql("      WML_MAILDATA.WAC_SID = ?");
            sql.addIntValue(wacSid);
            sql.addIntValue(wacSid);
            searchFlg = true;
        }

        //メール情報とディレクトリ情報を連結
        if (searchFlg) {
            sql.addSql("    and");
        }
        sql.addSql("      WML_DIRECTORY.WDR_SID = WML_MAILDATA.WDR_SID");

        //ラベル
        if (searchMdl.getLabelSid() > 0) {

            //削除メール(ゴミ箱フォルダ内のメール)を含めるか
            if (searchMdl.getDirectorySid() <= 0) {
                sql.addSql("    and");
                sql.addSql("      WML_DIRECTORY.WDR_TYPE <> ?");
                sql.addIntValue(GSConstWebmail.DIR_TYPE_DUST);
            }

            sql.addSql("    and");
            sql.addSql("      exists (");
            sql.addSql("        select WLB_SID from WML_LABEL_RELATION");
            sql.addSql("        where WLB_SID = ?");
            sql.addIntValue(searchMdl.getLabelSid());

            if (existAccount) {
                sql.addSql("        and WML_LABEL_RELATION.WAC_SID = ?");
                sql.addIntValue(wacSid);
            }
            sql.addSql("        and WML_MAILDATA.WMD_MAILNUM = WML_LABEL_RELATION.WMD_MAILNUM");
            sql.addSql("      )");
            searchFlg = true;
        }

        //from
        if (!StringUtil.isNullZeroString(searchMdl.getFrom())) {
            sql.addSql("    and");
            sql.addSql("      upper(WML_MAILDATA.WMD_FROM) like '%"
                    + JDBCUtil.encFullStringLike(searchMdl.getFrom().toUpperCase())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
        }

        //to
        if (!StringUtil.isNullZeroString(searchMdl.getDestination())) {
            sql.addSql("    and");
            sql.addSql("      exists (");
            sql.addSql("        select 1 from WML_SENDADDRESS");
            sql.addSql("        where");
            if (existAccount) {
                sql.addSql("          WML_SENDADDRESS.WAC_SID = ?");
                sql.addSql("        and");
                sql.addIntValue(wacSid);
            }

            //TO, CC, BCC を選択
            boolean destFlg = false;
            sql.addSql("          (");
            if (searchMdl.getDestinationTo()) {
                sql.addSql("              WML_SENDADDRESS.WSA_TYPE = ?");
                sql.addIntValue(GSConstWebmail.WSA_TYPE_TO);
                destFlg = true;
            }
            if (searchMdl.getDestinationCc()) {
                if (destFlg) {
                    sql.addSql("          or");
                }
                sql.addSql("              WML_SENDADDRESS.WSA_TYPE = ?");
                sql.addIntValue(GSConstWebmail.WSA_TYPE_CC);
                destFlg = true;
            }
            if (searchMdl.getDestinationBcc()) {
                if (destFlg) {
                    sql.addSql("          or");
                }
                sql.addSql("              WML_SENDADDRESS.WSA_TYPE = ?");
                sql.addIntValue(GSConstWebmail.WSA_TYPE_BCC);
            }
            sql.addSql("          )");
            sql.addSql("        and");

            sql.addSql("          upper(WSA_ADDRESS) like '%"
                    + JDBCUtil.encFullStringLike(searchMdl.getDestination().toUpperCase())
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
            sql.addSql("        and WML_MAILDATA.WMD_MAILNUM = WML_SENDADDRESS.WMD_MAILNUM");
            sql.addSql("      )");
        }

        //キーワード
        if (!StringUtil.isNullZeroString(searchMdl.getKeyword())) {
            if (keywordType == KEYWORDTYPE_TITLE__) {
                sql.addSql("    and");
                sql.addSql("      WML_MAILDATA.WMD_TITLE like '%"
                        + JDBCUtil.encFullStringLike(searchMdl.getKeyword())
                        + "%' ESCAPE '"
                        + JDBCUtil.def_esc
                        + "'");

            } else if (keywordType == KEYWORDTYPE_BODY__) {
                sql.addSql("    and");

                if (dbUtil.getDbType() == GSConst.DBTYPE_H2DB) {
                    //H2
                    sql.addSql("       WML_MAILDATA.WMD_MAILNUM = FT.MAILNUM");
                    sql.addSql("        and");
                    sql.addSql("          WML_DIRECTORY.WDR_SID <> -1");
                } else {
                    //Postgres 他
                    sql.addSql("      exists (");
                    sql.addSql("        select 1 from WML_MAIL_BODY");
                    sql.addSql("        where");
                    sql.addSql("          WML_MAIL_BODY.WMB_BODY like '%"
                            + JDBCUtil.encFullStringLike(searchMdl.getKeyword())
                            + "%' ESCAPE '"
                            + JDBCUtil.def_esc
                            + "'");
                    sql.addSql("        and");
                    sql.addSql("          WML_MAILDATA.WMD_MAILNUM = WML_MAIL_BODY.WMD_MAILNUM");
                    sql.addSql("        and");
                    sql.addSql("          WML_DIRECTORY.WDR_SID <> -1");
                    sql.addSql("     )");
                }
            }
        }

        //日付 受信日 From
        if (searchMdl.getResvDateFrom() != null) {
            searchMdl.getResvDateFrom().setZeroHhMmSs();

            if (searchMdl.getResvDateTo() == null) {
                sql.addSql("    and");
                sql.addSql("      WML_MAILDATA.WMD_SDATE >= ?");
                sql.addDateValue(searchMdl.getResvDateFrom());
            }
        }

        //日付 受信日 To
        if (searchMdl.getResvDateTo() != null) {
            searchMdl.getResvDateTo().setMaxHhMmSs();

            sql.addSql("    and");
            if (searchMdl.getResvDateTo() == null) {
                sql.addSql("      WML_MAILDATA.WMD_SDATE <= ?");
                sql.addDateValue(searchMdl.getResvDateTo());
            } else {
                sql.addSql("      WML_MAILDATA.WMD_SDATE between ?");
                sql.addSql("                             and ?");
                sql.addDateValue(searchMdl.getResvDateFrom());
                sql.addDateValue(searchMdl.getResvDateTo());
            }
        }

        //添付ファイル
        if (searchMdl.isTempFile()) {
            sql.addSql("    and");
            sql.addSql("      WML_MAILDATA.WMD_TEMPFLG = ?");
            sql.addIntValue(GSConstWebmail.TEMPFLG_EXIST);
        }

        //未読/既読
        if (searchMdl.getReadKbn() == Wml010Const.SEARCH_READKBN_NOREAD) {
            sql.addSql("    and");
            sql.addSql("      WML_MAILDATA.WMD_READED = ?");
            sql.addIntValue(GSConstWebmail.READEDFLG_NOREAD);
        } else if (searchMdl.getReadKbn() == Wml010Const.SEARCH_READKBN_READED) {
            sql.addSql("    and");
            sql.addSql("      WML_MAILDATA.WMD_READED = ?");
            sql.addIntValue(GSConstWebmail.READEDFLG_READED);
        }

        return sql;
    }

    /**
     * <br>[機  能] 指定したメールの本文を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wacSid アカウントSID
     * @param mailNum メッセージ番号
     * @param bodyLimitLen メール本文の最大文字数
     * @return メールの本文
     * @throws SQLException SQL実行時例外
     */
    private String __getMailBody(int wacSid, long mailNum, int bodyLimitLen) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String body = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            if (bodyLimitLen > 0) {
                sql.addSql("   case");
                sql.addSql("     length(COALESCE(WMB_BODY, '')) > ?");
                sql.addSql("   when true then substr(WMB_BODY, 1, ?)");
                sql.addSql("   else WMB_BODY");
                sql.addSql("   end as BODY");
                sql.addIntValue(bodyLimitLen);
                sql.addIntValue(bodyLimitLen);
            } else {
                sql.addSql("   WMB_BODY as BODY");
            }
            sql.addSql(" from");
            sql.addSql("   WML_MAIL_BODY");

            sql.addSql(" where");
            if (wacSid > 0) {
                sql.addSql("   WAC_SID = ?");
                sql.addSql(" and");
                sql.addIntValue(wacSid);
            }
            sql.addSql("   WMD_MAILNUM = ?");
            sql.addLongValue(mailNum);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                body = rs.getString("BODY");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return body;
    }

    /**
     * <br>[機  能] 指定したメールのラベル情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wacSid アカウントSID
     * @param mailNum メッセージ番号
     * @return ラベル情報
     * @throws SQLException SQL実行時例外
     */
    private List<Wml010LabelModel> __getLabelData(int wacSid, long mailNum) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Wml010LabelModel> labelList = new ArrayList<Wml010LabelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_LABEL.WLB_SID as WLB_SID,");
            sql.addSql("   WML_LABEL.WLB_NAME as WLB_NAME,");
            sql.addSql("   WML_LABEL.WLB_TYPE as WLB_TYPE");
            sql.addSql(" from");
            sql.addSql("   WML_LABEL,");
            sql.addSql("   WML_LABEL_RELATION");
            sql.addSql(" where");
            if (wacSid > 0) {
                sql.addSql("   WML_LABEL_RELATION.WAC_SID = ?");
                sql.addIntValue(wacSid);
                sql.addSql(" and");
            }
            sql.addSql("   WML_LABEL_RELATION.WMD_MAILNUM = ?");
            sql.addSql(" and");
            sql.addSql("   WML_LABEL.WLB_SID = WML_LABEL_RELATION.WLB_SID");
            sql.addLongValue(mailNum);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Wml010LabelModel labelModel = new Wml010LabelModel();
                labelModel.setId(rs.getInt("WLB_SID"));
                labelModel.setName(rs.getString("WLB_NAME"));
                labelModel.setType(rs.getInt("WLB_TYPE"));

                labelList.add(labelModel);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return labelList;
    }

    /**
     * <br>[機  能] 指定したメールの送信先メールアドレスを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wacSid アカウントSID
     * @param mailNum メッセージ番号
     * @return 送信先メールアドレス
     * @throws SQLException SQL実行時例外
     */
    public Wml010SendAddrModel getSendAddress(int wacSid, long mailNum) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Wml010SendAddrModel addrModel = new Wml010SendAddrModel();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WSA_TYPE,");
            sql.addSql("   WSA_ADDRESS");
            sql.addSql(" from");
            sql.addSql("   WML_SENDADDRESS");
            sql.addSql(" where");
            if (wacSid > 0) {
                sql.addSql("   WAC_SID  = ?");
                sql.addSql(" and");
                sql.addIntValue(wacSid);
            }
            sql.addSql("   WMD_MAILNUM = ?");
            sql.addSql(" order by");
            sql.addSql("   WSA_NUM");
            sql.addLongValue(mailNum);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int type = rs.getInt("WSA_TYPE");
                String address = rs.getString("WSA_ADDRESS");

                if (type == GSConstWebmail.WSA_TYPE_TO) {
                    addrModel.addToAddress(address);
                } else if (type == GSConstWebmail.WSA_TYPE_CC) {
                    addrModel.addCcAddress(address);
                } else if (type == GSConstWebmail.WSA_TYPE_BCC) {
                    addrModel.addBccAddress(address);
                }

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return addrModel;
    }

    /**
     * <br>[機  能] 指定したメールの送信予定を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailData メール情報情報
     * @throws SQLException SQL実行時例外
     */
    private void __setSendPlan(Wml010MailModel mailData) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WSP_SENDKBN,");
            sql.addSql("   WSP_SENDDATE,");
            sql.addSql("   WSP_COMPRESS_FILE");
            sql.addSql(" from");
            sql.addSql("   WML_MAIL_SENDPLAN");
            sql.addSql(" where");
            sql.addSql("   WMD_MAILNUM = ?");
            sql.addLongValue(mailData.getMailNum());

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                mailData.setSendPlanKbn(rs.getInt("WSP_SENDKBN"));
                mailData.setSendPlanDate(
                        UDate.getInstanceTimestamp(rs.getTimestamp("WSP_SENDDATE")));
                mailData.setSendPlanCompressFile(rs.getInt("WSP_COMPRESS_FILE"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] 指定したメール情報を「返信済み」に更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNum メッセージ番号
     * @throws SQLException SQL実行時例外
     */
    public void setReply(long mailNum) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update WML_MAILDATA");
            sql.addSql(" set WMD_REPLY = ?");
            sql.addSql(" where WMD_MAILNUM = ?");
            sql.addIntValue(GSConstWebmail.WMD_REPLY_REPLY);
            sql.addLongValue(mailNum);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] 指定したメール情報を「転送済み」に更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNum メッセージ番号
     * @throws SQLException SQL実行時例外
     */
    public void setForward(long mailNum) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update WML_MAILDATA");
            sql.addSql(" set WMD_FORWARD = ?");
            sql.addSql(" where WMD_MAILNUM = ?");
            sql.addIntValue(GSConstWebmail.WMD_FORWARD_FORWARD);
            sql.addLongValue(mailNum);

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] 指定したメール情報の「未読/既読」を変更する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNum メッセージ番号
     * @param readType メール未読/既読
     * @throws SQLException SQL実行時例外
     */
    public void changeMailReaded(long[] mailNum, int readType) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update WML_MAILDATA");
            sql.addSql(" set WMD_READED = ?");
            if (readType == Wml010Const.MAIL_READTYPE_NOREAD) {
                sql.addIntValue(GSConstWebmail.WMD_READED_NO);
            } else {
                sql.addIntValue(GSConstWebmail.WMD_READED_YES);
            }

            sql.addSql(" where WMD_MAILNUM in (");
            sql.addSql("   ?");
            sql.addLongValue(mailNum[0]);
            for (int i = 1; i < mailNum.length; i++) {
                sql.addSql("  ,?");
                sql.addLongValue(mailNum[i]);
            }
            sql.addSql(" )");

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] 指定したメール情報の「未読/既読」を全て変更する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNumList 更新対象メールのメッセージ番号
     * @param readType メール未読/既読
     * @throws SQLException SQL実行時例外
     */
    public void changeMailReadedToMail(List<Long> mailNumList, int readType) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update WML_MAILDATA");
            sql.addSql(" set WMD_READED = ?");
            if (readType == Wml010Const.MAIL_READTYPE_NOREAD) {
                sql.addIntValue(GSConstWebmail.WMD_READED_NO);
            } else {
                sql.addIntValue(GSConstWebmail.WMD_READED_YES);
            }

            sql.addSql(" where");
            sql.addSql("   WMD_MAILNUM in (");
            for (int idx = 0; idx < mailNumList.size(); idx++) {
                if (idx > 0) {
                    sql.addSql("     ,?");
                } else {
                    sql.addSql("     ?");
                }
                sql.addLongValue(mailNumList.get(idx));
            }
            sql.addSql("   )");

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            int ucnt = pstmt.executeUpdate();
            log__.debug("更新件数 = " + ucnt);

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    /**
     * <br>[機  能] 指定したディレクトリ内の全ての「未読/既読」メールのメッセージ番号を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wdrSid ディレクトリSID
     * @param readType メール未読/既読
     * @return メッセージ番号
     * @throws SQLException SQL実行時例外
     */
    public List<Long> getMailNum(long wdrSid, int readType) throws SQLException {
        List<Long> mailNumList = new ArrayList<Long>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select WMD_MAILNUM from WML_MAILDATA");
            sql.addSql(" where WDR_SID = ?");
            sql.addLongValue(wdrSid);

            sql.addSql(" and WMD_READED = ?");
            if (readType == Wml010Const.MAIL_READTYPE_NOREAD) {
                sql.addIntValue(GSConstWebmail.WMD_READED_YES);
            } else {
                sql.addIntValue(GSConstWebmail.WMD_READED_NO);
            }

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                mailNumList.add(rs.getLong("WMD_MAILNUM"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return mailNumList;
    }

    /**
     * <br>[機  能] ユーザ情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param grpSid グループSID
     * @return ユーザ情報
     * @throws SQLException SQL実行時例外
     */
    public List<Wml010AddressModel> getShainList(int grpSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Wml010AddressModel> addressList = new ArrayList<Wml010AddressModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   CMN_USRM_INF.USR_SID as USR_SID,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("   CMN_USRM_INF.USI_MAIL1 as USI_MAIL1,");
            sql.addSql("   CMN_USRM_INF.USI_MAIL2 as USI_MAIL2,");
            sql.addSql("   CMN_USRM_INF.USI_MAIL3 as USI_MAIL3,");
            sql.addSql("   CMN_USRM_INF.USI_MAIL1_KF as USI_MAIL1_KF,");
            sql.addSql("   CMN_USRM_INF.USI_MAIL2_KF as USI_MAIL2_KF,");
            sql.addSql("   CMN_USRM_INF.USI_MAIL3_KF as USI_MAIL3_KF,");
            sql.addSql("   (case");
            sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then 1");
            sql.addSql("      else 0");
            sql.addSql("    end) as YAKUSYOKU_EXIST,");
            sql.addSql("   (case");
            sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then 0");
            sql.addSql("      else (");
            sql.addSql("        select POS_SORT from CMN_POSITION");
            sql.addSql("        where CMN_USRM_INF.POS_SID = CMN_POSITION.POS_SID");
            sql.addSql("      )");
            sql.addSql("    end) as YAKUSYOKU_SORT");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            sql.addSql(" where");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_JKBN = ?");
            sql.addSql(" and");
            sql.addSql("   exists (");
            sql.addSql("     select 1 from CMN_BELONGM");
            sql.addSql("     where CMN_USRM.USR_SID = CMN_BELONGM.USR_SID");
            sql.addSql("     and CMN_BELONGM.GRP_SID = ?");
            sql.addSql("   )");
            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("     (");
            sql.addSql("        length(coalesce(CMN_USRM_INF.USI_MAIL1, '')) > 0");
            sql.addSql("      and");
            sql.addSql("        CMN_USRM_INF.USI_MAIL1_KF = ?");
            sql.addSql("     )");
            sql.addSql("     or");
            sql.addSql("     (");
            sql.addSql("        length(coalesce(CMN_USRM_INF.USI_MAIL2, '')) > 0");
            sql.addSql("      and");
            sql.addSql("        CMN_USRM_INF.USI_MAIL2_KF = ?");
            sql.addSql("     )");
            sql.addSql("     or");
            sql.addSql("     (");
            sql.addSql("        length(coalesce(CMN_USRM_INF.USI_MAIL3, '')) > 0");
            sql.addSql("      and");
            sql.addSql("        CMN_USRM_INF.USI_MAIL3_KF = ?");
            sql.addSql("     )");
            sql.addSql("   )");
            sql.addIntValue(GSConstUser.USER_JTKBN_ACTIVE);
            sql.addIntValue(grpSid);
            sql.addIntValue(GSConstUser.INDIVIDUAL_INFO_OPEN);
            sql.addIntValue(GSConstUser.INDIVIDUAL_INFO_OPEN);
            sql.addIntValue(GSConstUser.INDIVIDUAL_INFO_OPEN);

            //並び順を設定する
            CmnCmbsortConfDao sortDao = new CmnCmbsortConfDao(con);
            CmnCmbsortConfModel sortMdl = sortDao.getCmbSortData();

            sql.addSql(" order by");
            if (sortMdl != null) {
                __setShainOrder(sql, sortMdl.getCscUserSkey1(), sortMdl.getCscUserOrder1());

                if (sortMdl.getCscUserSkey2() != GSConst.USERCMB_SKEY_NOSET) {
                    sql.addSql("   ,");
                    __setShainOrder(sql, sortMdl.getCscUserSkey2(), sortMdl.getCscUserOrder2());
                }
            } else {
                sql.addSql("   CMN_USRM_INF.USI_SEI_KN,");
                sql.addSql("   CMN_USRM_INF.USI_MEI_KN");
            }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Wml010AddressModel addressData = new Wml010AddressModel();
                addressData.setUserSid(rs.getInt("USR_SID"));
                addressData.setUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                if (rs.getInt("USI_MAIL1_KF") == GSConstUser.INDIVIDUAL_INFO_OPEN) {
                    addressData.setMail1(rs.getString("USI_MAIL1"));
                }
                if (rs.getInt("USI_MAIL2_KF") == GSConstUser.INDIVIDUAL_INFO_OPEN) {
                    addressData.setMail2(rs.getString("USI_MAIL2"));
                }
                if (rs.getInt("USI_MAIL3_KF") == GSConstUser.INDIVIDUAL_INFO_OPEN) {
                    addressData.setMail3(rs.getString("USI_MAIL3"));
                }

                addressList.add(addressData);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return addressList;
    }

    /**
     * <br>[機  能] アドレス帳情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param tantoFlg trueの場合は担当者に指定されているアドレス帳情報のみを表示
     * @return アドレス帳情報
     * @throws SQLException SQL実行時例外
     */
    public List<Wml010AddressModel> getAddressList(int userSid, boolean tantoFlg)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Wml010AddressModel> addressList = new ArrayList<Wml010AddressModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   ADR_SEI,");
            sql.addSql("   ADR_MEI,");
            sql.addSql("   ADR_MAIL1,");
            sql.addSql("   ADR_MAIL2,");
            sql.addSql("   ADR_MAIL3");
            sql.addSql(" from");
            sql.addSql("   ADR_ADDRESS");
            sql.addSql(" where");
            sql.addSql("   (");
            sql.addSql("     ADR_PERMIT_VIEW = ?");
            sql.addSql("   or");
            sql.addSql("     (");
            sql.addSql("       ADR_PERMIT_VIEW = ?");
            sql.addSql("     and");
            sql.addSql("       exists (");
            sql.addSql("         select ADR_SID from ADR_PERSONCHARGE");
            sql.addSql("         where ADR_PERSONCHARGE.USR_SID = ?");
            sql.addSql("         and ADR_PERSONCHARGE.ADR_SID = ADR_ADDRESS.ADR_SID");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   or");
            sql.addSql("     (");
            sql.addSql("       ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
            sql.addSql("     and");
            sql.addSql("       exists (");
            sql.addSql("         select ADR_SID from ADR_PERMIT_VIEW");
            sql.addSql("         where");
            sql.addSql("           ADR_PERMIT_VIEW.GRP_SID in (");
            sql.addSql("             select GRP_SID from CMN_BELONGM");
            sql.addSql("             where CMN_BELONGM.USR_SID = ?");
            sql.addSql("           )");
            sql.addSql("         and ADR_PERMIT_VIEW.ADR_SID = ADR_ADDRESS.ADR_SID");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   or");
            sql.addSql("     (");
            sql.addSql("       ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
            sql.addSql("     and");
            sql.addSql("       exists (");
            sql.addSql("         select ADR_SID from ADR_PERMIT_VIEW");
            sql.addSql("         where ADR_PERMIT_VIEW.USR_SID = ?");
            sql.addSql("         and ADR_PERMIT_VIEW.ADR_SID = ADR_ADDRESS.ADR_SID");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   )");
            sql.addIntValue(GSConst.ADR_VIEWPERMIT_NORESTRICTION);
            sql.addIntValue(GSConst.ADR_VIEWPERMIT_OWN);
            sql.addIntValue(userSid);
            sql.addIntValue(GSConst.ADR_VIEWPERMIT_GROUP);
            sql.addIntValue(userSid);
            sql.addIntValue(GSConst.ADR_VIEWPERMIT_USER);
            sql.addIntValue(userSid);

            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("      length(coalesce(ADR_ADDRESS.ADR_MAIL1, '')) > 0");
            sql.addSql("    or");
            sql.addSql("      length(coalesce(ADR_ADDRESS.ADR_MAIL2, '')) > 0");
            sql.addSql("    or");
            sql.addSql("      length(coalesce(ADR_ADDRESS.ADR_MAIL3, '')) > 0");
            sql.addSql("   )");

            if (tantoFlg) {
                sql.addSql(" and");
                sql.addSql("   exists (");
                sql.addSql("     select ADR_SID from ADR_PERSONCHARGE");
                sql.addSql("     where ADR_PERSONCHARGE.USR_SID = ?");
                sql.addSql("     and ADR_ADDRESS.ADR_SID = ADR_PERSONCHARGE.ADR_SID");
                sql.addSql("   )");
                sql.addIntValue(userSid);
            }

            sql.addSql(" order by");
            sql.addSql("   ADR_SEI_KN asc,");
            sql.addSql("   ADR_MEI_KN asc");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Wml010AddressModel addressData = new Wml010AddressModel();
                addressData.setUserName(rs.getString("ADR_SEI") + " " + rs.getString("ADR_MEI"));
                addressData.setMail1(rs.getString("ADR_MAIL1"));
                addressData.setMail2(rs.getString("ADR_MAIL2"));
                addressData.setMail3(rs.getString("ADR_MAIL3"));

                addressList.add(addressData);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return addressList;
    }

    /**
     * <br>[機  能] 指定したアカウントのディレクトリ情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param wacSid アカウントSID
     * @return ディレクトリ情報一覧
     * @throws SQLException SQL実行時例外
     */
    public List<Wml010DirectoryModel> getDirectoryList(RequestModel reqMdl,
                                              int wacSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Wml010DirectoryModel> ret = new ArrayList<Wml010DirectoryModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_DIRECTORY.WDR_SID as WDR_SID,");
            sql.addSql("   WML_DIRECTORY.WDR_NAME as WDR_NAME,");
            sql.addSql("   WML_DIRECTORY.WDR_TYPE as WDR_TYPE,");
            sql.addSql("   COALESCE(NOREAD_MSG.CNT, 0) as NOREAD_COUNT");
            sql.addSql(" from ");
            sql.addSql("   WML_DIRECTORY");
            sql.addSql("   left join");
            sql.addSql("     (");
            sql.addSql("       select");
            sql.addSql("         WDR_SID,");
            sql.addSql("         count(*) as CNT");
            sql.addSql("       from");
            sql.addSql("         WML_MAILDATA");
            sql.addSql("       where");
            sql.addSql("         WAC_SID = ?");
            sql.addSql("       and");
            sql.addSql("         WMD_READED = ?");
            sql.addSql("       group by");
            sql.addSql("         WDR_SID");
            sql.addSql("     ) NOREAD_MSG");
            sql.addSql("   on");
            sql.addSql("     WML_DIRECTORY.WDR_SID = NOREAD_MSG.WDR_SID");
            sql.addSql(" where");
            sql.addSql("   WML_DIRECTORY.WAC_SID = ?");
            sql.addSql(" and");
            sql.addSql("   WML_DIRECTORY.WDR_VIEW = ?");
            sql.addSql(" order by");
            sql.addSql("   WML_DIRECTORY.WDR_DEFAULT,");
            sql.addSql("   WML_DIRECTORY.WDR_TYPE");

            sql.addIntValue(wacSid);
            sql.addIntValue(GSConstWebmail.WMD_READED_NO);
            sql.addIntValue(wacSid);
            sql.addIntValue(GSConstWebmail.DSP_VIEW_OK);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Wml010DirectoryModel dirMdl = new Wml010DirectoryModel();
                dirMdl.setId(rs.getLong("WDR_SID"));
                dirMdl.setName(
                  __getDirName(reqMdl, rs.getLong("WDR_SID"), rs.getString("WDR_NAME")));
                dirMdl.setType(rs.getInt("WDR_TYPE"));
                dirMdl.setNoReadCount(rs.getLong("NOREAD_COUNT"));

                ret.add(dirMdl);
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
     * <br>[機  能] ラベル情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param wacSid アカウントSID
     * @return ラベル情報一覧
     * @throws SQLException SQL実行時例外
     */
    public List<Wml010LabelModel> getLabelList(int wacSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Wml010LabelModel> ret = new ArrayList<Wml010LabelModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_LABEL.WLB_SID as WLB_SID,");
            sql.addSql("   WML_LABEL.WLB_NAME as WLB_NAME,");
            sql.addSql("   COALESCE(NOREAD_MSG.CNT, 0) as NOREAD_COUNT");
            sql.addSql(" from ");
            sql.addSql("   WML_LABEL");
            sql.addSql("   left join");
            sql.addSql("     (");
            sql.addSql("       select");
            sql.addSql("         WML_LABEL_RELATION.WLB_SID as WLB_SID,");
            sql.addSql("         count(*) as CNT");
            sql.addSql("       from");
            sql.addSql("         WML_DIRECTORY,");
            sql.addSql("         WML_LABEL_RELATION,");
            sql.addSql("         WML_MAILDATA");
            sql.addSql("       where");
            sql.addSql("         WML_DIRECTORY.WAC_SID = ?");
            sql.addSql("       and");
            sql.addSql("         WML_DIRECTORY.WDR_TYPE <> ?");
            sql.addSql("       and");
            sql.addSql("         WML_MAILDATA.WAC_SID = ?");
            sql.addSql("       and");
            sql.addSql("         WML_MAILDATA.WDR_SID = WML_DIRECTORY.WDR_SID");
            sql.addSql("       and");
            sql.addSql("         WML_MAILDATA.WMD_READED = ?");
            sql.addSql("       and");
            sql.addSql("         WML_LABEL_RELATION.WAC_SID = ?");
            sql.addSql("       and");
            sql.addSql("         WML_MAILDATA.WMD_MAILNUM = WML_LABEL_RELATION.WMD_MAILNUM");
            sql.addSql("       group by");
            sql.addSql("         WML_LABEL_RELATION.WLB_SID");
            sql.addSql("     ) NOREAD_MSG");
            sql.addSql("   on");
            sql.addSql("     WML_LABEL.WLB_SID = NOREAD_MSG.WLB_SID");
            sql.addSql(" where ");
            sql.addSql("   (");
            sql.addSql("     WLB_TYPE = ?");
            sql.addSql("   and");
            sql.addSql("     WAC_SID = ?");
            sql.addSql("    )");
            sql.addSql(" or");
            sql.addSql("   (");
            sql.addSql("     WLB_TYPE = ?");
            sql.addSql("   and ");
            sql.addSql("     (");
            sql.addSql("       WML_LABEL.USR_SID in (");
            sql.addSql("         select USR_SID from WML_ACCOUNT_USER");
            sql.addSql("         where WAC_SID = ?");
            sql.addSql("         and coalesce(USR_SID, 0) > 0");
            sql.addSql("       )");
            sql.addSql("      or");
            sql.addSql("       WML_LABEL.USR_SID in (");
            sql.addSql("         select CMN_BELONGM.USR_SID");
            sql.addSql("         from");
            sql.addSql("           WML_ACCOUNT_USER,");
            sql.addSql("           CMN_BELONGM");
            sql.addSql("         where WML_ACCOUNT_USER.WAC_SID = ?");
            sql.addSql("         and coalesce(WML_ACCOUNT_USER.GRP_SID, 0) > 0");
            sql.addSql("         and WML_ACCOUNT_USER.GRP_SID = CMN_BELONGM.GRP_SID");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   )");
            sql.addSql(" order by");
            sql.addSql("   WLB_ORDER asc");

            sql.addIntValue(wacSid);
            sql.addIntValue(GSConstWebmail.DIR_TYPE_DUST);
            sql.addIntValue(wacSid);
            sql.addIntValue(GSConstWebmail.WMD_READED_NO);
            sql.addIntValue(wacSid);
            sql.addIntValue(GSConstWebmail.LABELTYPE_ONES);
            sql.addIntValue(wacSid);
            sql.addIntValue(GSConstWebmail.LABELTYPE_ALL);
            sql.addIntValue(wacSid);
            sql.addIntValue(wacSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Wml010LabelModel labelMdl = new Wml010LabelModel();
                labelMdl.setId(rs.getInt("WLB_SID"));
                labelMdl.setName(rs.getString("WLB_NAME"));
                labelMdl.setNoReadCount(rs.getLong("NOREAD_COUNT"));

                ret.add(labelMdl);
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
     * <br>[機  能] 指定したメールのディレクトリ種別を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNum メッセージ番号
     * @return ディレクトリ種別
     * @throws SQLException SQL実行時例外
     */
    public int getDirType(long mailNum) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int dirType = -1;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_DIRECTORY.WDR_TYPE as WDR_TYPE");
            sql.addSql(" from ");
            sql.addSql("   WML_MAILDATA,");
            sql.addSql("   WML_DIRECTORY");
            sql.addSql(" where ");
            sql.addSql("   WML_MAILDATA.WMD_MAILNUM = ?");
            sql.addSql(" and");
            sql.addSql("   WML_MAILDATA.WDR_SID = WML_DIRECTORY.WDR_SID");

            sql.addLongValue(mailNum);
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dirType = rs.getInt("WDR_TYPE");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return dirType;
    }

    /**
     * <br>[機  能] ヘッダー情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNum メッセージ番号
     * @param headerType ヘッダー種別
     * @return ディレクトリ種別
     * @throws SQLException SQL実行時例外
     */
    public List<String> getHeaderValue(long mailNum, String headerType) throws SQLException {
        return getHeaderValue(mailNum, headerType, false);
    }

    /**
     * <br>[機  能] ヘッダー情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNum メッセージ番号
     * @param headerType ヘッダー種別
     * @param type 完全一致 false:大文字、小文字を区別しない
     * @return ディレクトリ種別
     * @throws SQLException SQL実行時例外
     */
    public List<String> getHeaderValue(long mailNum, String headerType, boolean type)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> headerList = new ArrayList<String>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WMH_CONTENT");
            sql.addSql(" from ");
            sql.addSql("   WML_HEADER_DATA");
            sql.addSql(" where ");
            sql.addSql("   WMD_MAILNUM = ?");
            sql.addLongValue(mailNum);

            sql.addSql(" and");
            if (type) {
                sql.addSql("   WMH_TYPE = ?");
                sql.addStrValue(headerType);
            } else {
                sql.addSql("   upper(WMH_TYPE) = ?");
                sql.addStrValue(headerType.toUpperCase());
            }
            sql.addSql(" order by");
            sql.addSql("   WMH_NUM");

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                headerList.add(rs.getString("WMH_CONTENT"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return headerList;
    }

    /**
     * <br>[機  能] ユーザ情報のソート順を設定する
     * <br>[解  説]
     * <br>[備  考] sortKey、orderにはコンボボックスソート設定(CMN_CMB_SORT_CONF)の
     * <br>         設定を指定する
     * @param sql SqlBuffer
     * @param sortKey ソートキー
     * @param order 並び順
     * @return SqlBuffer
     */
    private SqlBuffer __setShainOrder(SqlBuffer sql, int sortKey, int order) {

        //並び順
        String orderStr = " asc";
        if (order == GSConst.ORDER_KEY_DESC) {
            orderStr = " desc";
        }

        //ソートカラム
        switch (sortKey) {
            //氏名
            case GSConst.USERCMB_SKEY_NAME:
                sql.addSql("   CMN_USRM_INF.USI_SEI_KN" + orderStr + ",");
                sql.addSql("   CMN_USRM_INF.USI_MEI_KN" + orderStr);
                break;
            //社員/職員番号
            case GSConst.USERCMB_SKEY_SNO:
                sql.addSql("   case when CMN_USRM_INF.USI_SYAIN_NO is null then ''");
                sql.addSql("   else CMN_USRM_INF.USI_SYAIN_NO end" + orderStr);
                break;
            //役職
            case GSConst.USERCMB_SKEY_POSITION:
                sql.addSql("  YAKUSYOKU_EXIST" + orderStr + ",");
                sql.addSql("  YAKUSYOKU_SORT" + orderStr);
                break;
             //生年月日
            case GSConst.USERCMB_SKEY_BDATE:
                sql.addSql("  CMN_USRM_INF.USI_BDATE" + orderStr);
                break;
             //ソートキー1
            case GSConst.USERCMB_SKEY_SORTKEY1:
                sql.addSql("  CMN_USRM_INF.USI_SORTKEY1" + orderStr);
                break;
             //ソートキー2
            case GSConst.USERCMB_SKEY_SORTKEY2:
                sql.addSql("  CMN_USRM_INF.USI_SORTKEY2" + orderStr);
                break;
            default:
                break;
        }
        return sql;
    }

    /**
     * <br>[機  能] ディレクトリ名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param wdrType ディレクトリタイプ
     * @param wdrName ディレクトリ名
     * @return String
     */
    private String __getDirName(RequestModel reqMdl, long wdrType, String wdrName) {

        GsMessage gsMsg = new GsMessage(reqMdl);

        if (wdrType == GSConstWebmail.DIR_TYPE_RECEIVE) {
            wdrName = gsMsg.getMessage("cmn.receive");
        } else if (wdrType == GSConstWebmail.DIR_TYPE_SENDED) {
            wdrName = gsMsg.getMessage("wml.19");
        } else if (wdrType == GSConstWebmail.DIR_TYPE_NOSEND) {
            wdrName = gsMsg.getMessage("wml.211");
        } else if (wdrType == GSConstWebmail.DIR_TYPE_DRAFT) {
            wdrName = gsMsg.getMessage("cmn.draft");
        } else if (wdrType == GSConstWebmail.DIR_TYPE_DUST) {
            wdrName = gsMsg.getMessage("cmn.trash");
        } else if (wdrType == GSConstWebmail.DIR_TYPE_SPAM) {
            wdrName = gsMsg.getMessage("wml.212");
        } else if (wdrType == GSConstWebmail.DIR_TYPE_STORAGE) {
            wdrName = gsMsg.getMessage("cmn.strage");
        }
        return wdrName;
    }

    /**
     * <br>[機  能] 指定したSIDのアカウント名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param accSid カウントSID
     * @return アカウント名
     * @throws SQLException SQL実行時例外
     */
    public String getAccName(long accSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String accName = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WAC_NAME");
            sql.addSql(" from ");
            sql.addSql("   WML_ACCOUNT");
            sql.addSql(" where ");
            sql.addSql("   WAC_SID = ?");

            sql.addLongValue(accSid);
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                accName = rs.getString("WAC_NAME");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return accName;
    }

    /**
     * <br>[機  能] 指定したメールのラベル名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailSid カウントSID
     * @return ラベル名
     * @throws SQLException SQL実行時例外
     */
    public String getLabelName(long mailSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String labelName = null;
        con = getCon();

        try {

            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WML_LABEL.WLB_NAME as WLB_NAME");
            sql.addSql(" from ");
            sql.addSql("   WML_LABEL_RELATION,");
            sql.addSql("   WML_LABEL");
            sql.addSql(" where ");
            sql.addSql("   WML_LABEL_RELATION.WMD_MAILNUM = ?");
            sql.addSql("   and");
            sql.addSql("   WML_LABEL_RELATION.WLB_SID = WML_LABEL.WLB_SID");

            sql.addLongValue(mailSid);
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                labelName = rs.getString("WLB_NAME");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return labelName;
    }

    /**
     * <br>[機  能] 指定したメールが存在するかを確認する
     * <br>[解  説]
     * <br>[備  考]
     * @param mailNum メッセージ番号
     * @return true:存在する false:存在しない
     * @throws SQLException SQL実行時例外
     */
    public boolean existsMailData(long mailNum) throws SQLException {

        boolean result = false;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   WMD_MAILNUM");
            sql.addSql(" from");
            sql.addSql("   WML_MAILDATA");
            sql.addSql(" where");
            sql.addSql("   WMD_MAILNUM = ?");
            sql.addLongValue(mailNum);

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
}
