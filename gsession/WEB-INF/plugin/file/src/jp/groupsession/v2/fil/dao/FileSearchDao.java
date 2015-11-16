package jp.groupsession.v2.fil.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.fil100.Fil100SearchParameterModel;
import jp.groupsession.v2.fil.model.FileModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ファイル詳細検索処理DAO
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class FileSearchDao extends AbstractDao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(FileSearchDao.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;
    /**
     * <p>デフォルトコンストラクタ
     * @param con DBコネクション
     * @param reqMdl RequestModel
     */
    public FileSearchDao(Connection con, RequestModel reqMdl) {
        super(con);
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] ファイル詳細検索結果を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param prmModel 検索条件Model
     * @param usModel セッションユーザ情報
     * @return List int FileDspModel
     * @throws SQLException SQL実行例外
     */
    public List<FileModel> getSearchData(Fil100SearchParameterModel prmModel,
                                          BaseUserModel usModel)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<FileModel> ret = new ArrayList<FileModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = __getCreateSearchSql(prmModel, usModel, con, false);

            /*pstmt = con.prepareStatement(sql.toLogString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE ,
                    ResultSet.CONCUR_READ_ONLY);

            rs = pstmt.executeQuery();

            log__.info(sql.toLogString());

            if (prmModel.getStart() > 1) {
                rs.absolute(prmModel.getStart() - 1);
            }

            for (int i = 0; rs.next() && i < prmModel.getLimit(); i++) {
                ret.add(__getFileSrchdataFromRs(rs));
            }*/

            sql.setPagingValue(prmModel.getStart() - 1, prmModel.getLimit());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ret.add(__getFileSrchdataFromRs(rs));
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
     * <br>[機  能] SQLを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param prmModel パラメータモデル
     * @param usModel セッションユーザ情報
     * @param con コネクション
     * @param isCount カウントを取得する場合、true
     * @return sql SQL
     * @throws SQLException SQL実行例外
     */
    private SqlBuffer __getCreateSearchSql(Fil100SearchParameterModel prmModel,
                                        BaseUserModel usModel, Connection con,
                                        boolean isCount)
    throws SQLException {

        CommonBiz  commonBiz = new CommonBiz();
        boolean isAdmin = commonBiz.isPluginAdmin(con, usModel, GSConstFile.PLUGIN_ID_FILE);
        List<String>  keywordList = prmModel.getKeyWord();

        boolean isTextSearch = false;
        if (prmModel.getSearchFlg() && keywordList != null
                && keywordList.size() > 0 && prmModel.isSrchTargetText()) {
            isTextSearch = true;
        }

        //SQL文
        SqlBuffer sql = new SqlBuffer();
        sql.addSql(" select");
        if (isCount) {
            sql.addSql("   count(*) CNT");
        } else {
            sql.addSql("   DIRECTORY.FDR_SID,");
            sql.addSql("   DIRECTORY.FDR_VERSION,");
            sql.addSql("   DIRECTORY.FCB_SID,");
            sql.addSql("   DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("   DIRECTORY.FDR_KBN,");
            sql.addSql("   DIRECTORY.FDR_VER_KBN,");
            sql.addSql("   DIRECTORY.FDR_LEVEL,");
            sql.addSql("   DIRECTORY.FDR_NAME,");
            sql.addSql("   DIRECTORY.FDR_BIKO,");
            sql.addSql("   DIRECTORY.FDR_JTKBN,");
            sql.addSql("   DIRECTORY.FDR_EUID,");
            sql.addSql("   DIRECTORY.FDR_EDATE,");
            sql.addSql("   DIRECTORY.BIN_SID,");
            sql.addSql("   DIRECTORY.FFL_EXT,");
            sql.addSql("   DIRECTORY.FFL_FILE_SIZE,");
            sql.addSql("   DIRECTORY.FFL_LOCK_KBN,");
            sql.addSql("   DIRECTORY.FFL_LOCK_USER,");
            sql.addSql("   DIRECTORY.USR_SID,");
            sql.addSql("   DIRECTORY.USI_SEI,");
            sql.addSql("   DIRECTORY.USI_MEI,");
            sql.addSql("   DIRECTORY.USR_JKBN,");
            sql.addSql("   DIRECTORY.FDR_EGID,");
            sql.addSql("   CMN_GROUPM.GRP_NAME,");
            sql.addSql("   CMN_GROUPM.GRP_JKBN");
        }
        sql.addSql(" from");
        sql.addSql("   (select FDR_SID, max(FDR_VERSION) as MAXVERSION ");
        sql.addSql("     from FILE_DIRECTORY group by FDR_SID) as DIR_MAXVERSION,");

        if (isCount) {
            sql.addSql("   FILE_DIRECTORY DIRECTORY");
        } else {
            sql.addSql("   (");
            sql.addSql("   select");
            sql.addSql("     FILE_DIRECTORY.FDR_SID,");
            sql.addSql("     FILE_DIRECTORY.FDR_VERSION,");
            sql.addSql("     FILE_DIRECTORY.FCB_SID,");
            sql.addSql("     FILE_DIRECTORY.FDR_PARENT_SID,");
            sql.addSql("     FILE_DIRECTORY.FDR_KBN,");
            sql.addSql("     FILE_DIRECTORY.FDR_VER_KBN,");
            sql.addSql("     FILE_DIRECTORY.FDR_LEVEL,");
            sql.addSql("     FILE_DIRECTORY.FDR_NAME,");
            sql.addSql("     FILE_DIRECTORY.FDR_BIKO,");
            sql.addSql("     FILE_DIRECTORY.FDR_JTKBN,");
            sql.addSql("     FILE_DIRECTORY.FDR_EUID,");
            sql.addSql("     FILE_DIRECTORY.FDR_EGID,");
            sql.addSql("     FILE_DIRECTORY.FDR_EDATE,");
            sql.addSql("     FILE_DIRECTORY.FDR_ACCESS_SID,");
            sql.addSql("     FILE_FILE_BIN.BIN_SID,");
            sql.addSql("     FILE_FILE_BIN.FFL_EXT,");
            sql.addSql("     FILE_FILE_BIN.FFL_FILE_SIZE,");
            sql.addSql("     FILE_FILE_BIN.FFL_LOCK_KBN,");
            sql.addSql("     FILE_FILE_BIN.FFL_LOCK_USER,");
            sql.addSql("     FILE_CALL_CONF.USR_SID,");
            sql.addSql("     CMN_USRM_INF.USI_SEI,");
            sql.addSql("     CMN_USRM_INF.USI_MEI,");
            sql.addSql("     CMN_USRM.USR_JKBN");
            sql.addSql("   from");
            sql.addSql("    ((((FILE_DIRECTORY left join FILE_FILE_BIN");
            sql.addSql("       on FILE_DIRECTORY.FDR_SID = FILE_FILE_BIN.FDR_SID ");
            sql.addSql("       and FILE_DIRECTORY.FDR_VERSION = FILE_FILE_BIN.FDR_VERSION)");
            sql.addSql(" left join FILE_CALL_CONF");
            sql.addSql("       on FILE_DIRECTORY.FDR_SID = FILE_CALL_CONF.FDR_SID");
            sql.addSql("       and FILE_CALL_CONF.USR_SID = ?)");
            sql.addSql(" left join CMN_USRM");
            sql.addSql("       on FILE_DIRECTORY.FDR_EUID = CMN_USRM.USR_SID)");
            sql.addSql(" left join CMN_USRM_INF");
            sql.addSql("       on CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID)");
            sql.addSql("  ) as DIRECTORY ");
            sql.addSql("     left join CMN_GROUPM");
            sql.addSql("       on DIRECTORY.FDR_EGID = CMN_GROUPM.GRP_SID");
            sql.addIntValue(prmModel.getUsrSid());
        }

        if (!isAdmin) {
            // キャビネット管理者 参照のための join句
            sql.addSql("     left join FILE_CABINET_ADMIN");
            sql.addSql("       on FILE_CABINET_ADMIN.FCB_SID = DIRECTORY.FCB_SID");
            sql.addSql("      and FILE_CABINET_ADMIN.USR_SID = ?");
            sql.addIntValue(prmModel.getUsrSid());
        }
        //全文検索使用時
        if (isTextSearch) {
            //ファイル内容検索テーブル join
            String tsSearchWord = "";
            String tsKeywordJoin = " & ";
            if (prmModel.getWordKbn() == GSConstFile.KEY_WORD_KBN_OR) {
                tsKeywordJoin = " | ";
            }
            for (int i = 0; i < keywordList.size(); i++) {
                if (i > 0) {
                    tsSearchWord = tsSearchWord.concat(tsKeywordJoin);
                }
                String word = FileSearchDao.encFullStringLikeTS(keywordList.get(i));
                tsSearchWord = tsSearchWord.concat(word);
            }

            sql.addSql("     left join");
            sql.addSql("       (select");
            sql.addSql("          FILE_FILE_TEXT.FDR_SID,");
            sql.addSql("          FILE_FILE_TEXT.FDR_VERSION,");
            sql.addSql("          count(*) as MATCH_CNT");
            sql.addSql("        from");
            sql.addSql("          FILE_FILE_TEXT,");
            sql.addSql("          (select FDR_SID, max(FDR_VERSION) as MAXVERSION ");
            sql.addSql("            from FILE_DIRECTORY group by FDR_SID) as DIR_MAXVERSION");
            sql.addSql("        where");
            sql.addSql("          FILE_FILE_TEXT.FDR_SID = DIR_MAXVERSION.FDR_SID");
            sql.addSql("        and");
            sql.addSql("          FILE_FILE_TEXT.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
            sql.addSql("        and");
            sql.addSql("          FILE_FILE_TEXT.FFT_READ_KBN = ?");
            sql.addSql("        and");
            sql.addIntValue(GSConstFile.READ_KBN_SUCCESSFUL);
            if (prmModel.getCabinet() != -1) {
                sql.addSql("          FILE_FILE_TEXT.FCB_SID = ?");
                sql.addSql("        and");
                sql.addIntValue(prmModel.getCabinet());
            }
            sql.addSql("          to_tsvector('japanese', FILE_FILE_TEXT.FFT_TEXT)"
                    + " @@ to_tsquery('japanese', '" + tsSearchWord + "')");
            sql.addSql("        group by");
            sql.addSql("          FILE_FILE_TEXT.FDR_SID,");
            sql.addSql("          FILE_FILE_TEXT.FDR_VERSION) as FILE_TEXT");
            sql.addSql("       on DIRECTORY.FDR_SID = FILE_TEXT.FDR_SID ");
            sql.addSql("       and DIRECTORY.FDR_VERSION = FILE_TEXT.FDR_VERSION");
        }
        sql.addSql("  where");

        if (prmModel.getCabinet() != -1) {

            sql.addSql("    DIRECTORY.FCB_SID = ?");
            sql.addSql(" and");
            sql.addIntValue(prmModel.getCabinet());
        }

        sql.addSql("    DIRECTORY.FDR_JTKBN = ?");
        sql.addSql(" and");
        sql.addSql("    DIRECTORY.FDR_PARENT_SID != ?");
        sql.addSql(" and");
        sql.addSql("    DIRECTORY.FDR_SID = DIR_MAXVERSION.FDR_SID");
        sql.addSql(" and");
        sql.addSql("    DIRECTORY.FDR_VERSION = DIR_MAXVERSION.MAXVERSION");
        sql.addIntValue(GSConstFile.JTKBN_NORMAL);
        sql.addIntValue(GSConstFile.DIRECTORY_ROOT);

        //対象項目にフォルダがない場合
        if (!(prmModel.getTargetFolder().equals(String.valueOf(GSConstFile.GET_TARGET_FOLDER)))) {
            sql.addSql("    and");
            sql.addSql("    DIRECTORY.FDR_KBN = 1");

        //対象項目にファイルがない場合
        } else if (!(prmModel.getTargetFile().equals(
                String.valueOf(GSConstFile.GET_TARGET_FILE)))) {
            sql.addSql("    and");
            sql.addSql("    DIRECTORY.FDR_KBN = 0");
        }

        //時間指定ありの場合
        if (prmModel.getUpdateKbn() == GSConstFile.UPDATE_KBN_OK) {

            sql.addSql(" and");
            sql.addSql("   (");
            sql.addSql("     DIRECTORY.FDR_EDATE");
            sql.addSql("     between ?");
            sql.addDateValue(prmModel.getUpFrDate());
            sql.addSql("     and ?");
            sql.addDateValue(prmModel.getUpToDate());
            sql.addSql("   )");


        }
        // KEYワード
        __createKeyWord(prmModel, sql);

        // キャビネット作成権限ユーザ以下の場合、各アクセス権限を参照
        if (!isAdmin) {
            sql.addSql(" and ");
            sql.addSql("   (");

            // キャビネット管理者の場合、ＯＫ
            sql.addSql("   FILE_CABINET_ADMIN.FCB_SID is not null or");

            // アクセス設定なし
            sql.addSql("   DIRECTORY.FDR_ACCESS_SID = ? or");
            sql.addIntValue(GSConstFile.DIRECTORY_ROOT);

            // アクセス設定に該当ユーザがいるかどうか
            sql.addSql("   exists ");
            sql.addSql("   (select *");
            sql.addSql("    from");
            sql.addSql("      FILE_DACCESS_CONF A");
            sql.addSql("    where");
            sql.addSql("      A.FDR_SID = DIRECTORY.FDR_ACCESS_SID");
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
            sql.addSql("   )");
            sql.addIntValue(GSConstFile.USER_KBN_USER);
            sql.addIntValue(prmModel.getUsrSid());
            sql.addIntValue(GSConstFile.USER_KBN_GROUP);
            sql.addIntValue(prmModel.getUsrSid());

            sql.addSql("   )");
        }

        if (!isCount) {
            String orderStr = "";
            // オーダー
            if (prmModel.getSearchOrderKey() == GSConstFile.ORDER_KEY_ASC) {
                orderStr = "  asc";
            } else if (prmModel.getSearchOrderKey() == GSConstFile.ORDER_KEY_DESC) {
                orderStr = "  desc";
            }

            sql.addSql(" order by ");
            switch (prmModel.getSearchSortKey()) {

            case GSConstFile.SORT_NAME:
                sql.addSql("   DIRECTORY.FDR_NAME " + orderStr);
                break;

            case GSConstFile.SORT_SIZE:
                sql.addSql("   DIRECTORY.FFL_FILE_SIZE " + orderStr);
                break;

            case GSConstFile.SORT_CALL:
                sql.addSql("   DIRECTORY.USR_SID " + orderStr);
                break;

            case GSConstFile.SORT_EDATE:
                sql.addSql("   DIRECTORY.FDR_EDATE " + orderStr);
                break;

            case GSConstFile.SORT_EUSR:
                sql.addSql("   DIRECTORY.FDR_EUID " + orderStr);
                break;

            default:
                break;

            }
        }

        return sql;
    }

    /**
     * <br>[機  能] ファイル詳細検索結果一覧情報を格納したモデルを取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param rs ResultSet
     * @return bean FileDspModel
     * @throws SQLException SQL実行例外
     */
    private FileModel __getFileSrchdataFromRs(ResultSet rs) throws SQLException {
        FileModel bean = new FileModel();

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
        bean.setFdrEuid(rs.getInt("FDR_EUID"));
        bean.setFdrEgid(rs.getInt("FDR_EGID"));
        bean.setFdrEdate(UDate.getInstanceTimestamp(rs.getTimestamp("FDR_EDATE")));

        bean.setBinSid(rs.getLong("BIN_SID"));
        bean.setFflExt(rs.getString("FFL_EXT"));

        BigDecimal bdSize = rs.getBigDecimal("FFL_FILE_SIZE");
        if (bdSize != null) {
            //B→KBへ変換
            String strSize = StringUtil.toCommaFromBigDecimal(
                    bdSize.divide(GSConstFile.KB_TO_MB, 1, RoundingMode.HALF_UP));
            bean.setFflFileSizeStr(strSize + "KB");
        }

        bean.setFflLockKbn(rs.getInt("FFL_LOCK_KBN"));
        bean.setFflLockUser(rs.getInt("FFL_LOCK_USER"));

        bean.setUsrSid(rs.getInt("USR_SID"));

        if (bean.getFdrEgid() > 0) {
            bean.setUsrSei(rs.getString("GRP_NAME"));
            bean.setUsrMei("");
            bean.setUsrJKbn(rs.getInt("GRP_JKBN"));
        } else {
            bean.setUsrSei(rs.getString("USI_SEI"));
            bean.setUsrMei(rs.getString("USI_MEI"));
            bean.setUsrJKbn(rs.getInt("USR_JKBN"));
        }

        return bean;
    }

    /**
     *
     * <br>[機 能] SQL(キーワード入力時の検索条件)を作成
     * <br>[解 説]
     * <br>[備 考]
     * @param bean パラメータモデル
     * @param sql SQL文
     */
    private void __createKeyWord(Fil100SearchParameterModel bean, SqlBuffer sql) {
        //キーワード入力時
        List<String>  keywordList = bean.getKeyWord();
        if (keywordList != null && keywordList.size() > 0) {

            String keywordJoin = "  and";
            if (bean.getWordKbn() == GSConstFile.KEY_WORD_KBN_OR) {
                keywordJoin = "   or";
            }

            boolean isTarget = false;

            if (bean.isSrchTargetName()) {
                if (isTarget) {
                    sql.addSql("   or");
                } else {
                    sql.addSql(" and");
                    sql.addSql(" (");
                    isTarget = true;
                }

                sql.addSql("   (");
                for (int i = 0; i < keywordList.size(); i++) {
                    if (i > 0) {
                        sql.addSql(keywordJoin);
                    }
                    sql.addSql("       DIRECTORY.FDR_NAME like '%"
                            + JDBCUtil.encFullStringLike(keywordList.get(i))
                            + "%' ESCAPE '"
                            + JDBCUtil.def_esc
                            + "'");
                }
                sql.addSql("   )");
            }

            if (bean.isSrchTargetBiko()) {
                if (isTarget) {
                    sql.addSql("   or");
                } else {
                    sql.addSql(" and");
                    sql.addSql(" (");
                    isTarget = true;
                }

                sql.addSql("   (");
                for (int i = 0; i < keywordList.size(); i++) {
                    if (i > 0) {
                        sql.addSql(keywordJoin);
                    }
                    sql.addSql("       DIRECTORY.FDR_BIKO like '%"
                            + JDBCUtil.encFullStringLike(keywordList.get(i))
                            + "%' ESCAPE '"
                            + JDBCUtil.def_esc
                            + "'");
                }
                sql.addSql("   )");
            }
            if (bean.isSrchTargetText()) {
                if (isTarget) {
                    sql.addSql("   or");
                } else {
                    sql.addSql(" and");
                    sql.addSql(" (");
                    isTarget = true;
                }

                sql.addSql("       coalesce(FILE_TEXT.MATCH_CNT,0) > 0");
            }

            if (isTarget) {
                sql.addSql(" )");
            }
        }
    }

    /**
     * <br>[機  能] レコード件数を取得する。
     * <br>[解  説]
     * <br>[備  考]
     * @param  prmModel 検索パラメータモデル
     * @param  usModel  セッションユーザ情報
     * @return count    カウント件数
     * @throws SQLException SQL実行例外
     */
    public int recordCount(Fil100SearchParameterModel prmModel,
                            BaseUserModel usModel) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = __getCreateSearchSql(prmModel, usModel, con, true);
            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("CNT");
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return count;
    }

    /**
     * <br>[機  能] 引数で指定した単語をSQLで使用できる形式に加工します。
     * <br>[解  説]
     * <br>[備  考]
     * @param strItem 処理したい文字列
     * @param esc エスケープする文字列
     * @return  strItem を処理した文字列
     */
    public static String encFullStringTS(String strItem, String esc) {

        //文字列が0の時はそのまま返す
        if (strItem.length() == 0) {
            return "";
        }
        // 文字列中に「'」「&」「|」「!」「(」「)」「:」「\t」がなければそのまま返す
        if (
            (strItem.indexOf("\'") < 0)
            &&
            (strItem.indexOf("&") < 0)
            &&
            (strItem.indexOf("|") < 0)
            &&
            (strItem.indexOf("!") < 0)
            &&
            (strItem.indexOf("(") < 0)
            &&
            (strItem.indexOf(")") < 0)
            &&
            (strItem.indexOf(":") < 0)
            &&
            (strItem.indexOf("\t") < 0)     //タブ
            &&
            (strItem.indexOf("\\") < 0)
        ) {
            return strItem;
        }

        //文字列変換部分
        StringBuilder strBuf = new StringBuilder();
        for (int intCnt = 0; intCnt < strItem.length(); intCnt++) {
            char ch = strItem.charAt(intCnt);
            if ('\'' == ch) {
                strBuf.append(esc);
                strBuf.append("''");
            } else if ('\\' == ch) {
                strBuf.append(esc);
                strBuf.append("\\\\");
            } else if ('&' == ch
                     || '|' == ch
                     || '!' == ch
                     || '(' == ch
                     || ')' == ch
                     || ':' == ch
                     || '\t' == ch) {
                strBuf.append(esc);
                strBuf.append(ch);
            } else {
                strBuf.append(ch);
            }
        }
        return new String(strBuf);
    }

    /**
     * <br>[機  能] 引数で指定した単語をSQLで使用できる形式に加工します。
     * <br>[解  説]
     * <br>[備  考]
     * @param   strItem 処理したい文字列
     * @return  strItem を処理した文字列
     */
    public static String encFullStringLikeTS(String strItem) {
        return encFullStringLikeTS(strItem, "\\\\");
    }

    /**
     * <br>[機  能] 引数で指定した単語をSQLで使用できる形式に加工します。
     * <br>[解  説]
     * <br>[備  考]
     * @param strItem 処理したい文字列
     * @param esc エスケープする文字列
     * @return  strItem を処理した文字列
     */
    public static String encFullStringLikeTS(String strItem, String esc) {

        //文字列が0の時はそのまま返す
        if (strItem == null || strItem.length() == 0) {
            return strItem;
        }

        String encStr = strItem.toString();
        if (strItem.indexOf(esc) >= 0) {
            //文字列変換部分
            StringBuilder strBuf = new StringBuilder();
            for (int intCnt = 0; intCnt < strItem.length(); intCnt++) {
                char ch = strItem.charAt(intCnt);
                if (esc.charAt(0) == ch) {
                    strBuf.append(esc);
                    strBuf.append(ch);
                } else {
                    strBuf.append(ch);
                }
            }

            encStr = strBuf.toString();
        }

        return encFullStringTS(encStr, esc);
    }
}