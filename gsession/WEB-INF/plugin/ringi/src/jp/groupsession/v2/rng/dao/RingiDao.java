package jp.groupsession.v2.rng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.dao.base.CmnPluginControlDao;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnPluginControlModel;
import jp.groupsession.v2.man.GSConstMain;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.model.RingiChannelDataModel;
import jp.groupsession.v2.rng.model.RingiDataModel;
import jp.groupsession.v2.rng.model.RingiSearchModel;
import jp.groupsession.v2.rng.model.RngDeleteModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 稟議プラグインで共通使用するDAOクラスです。
 * <br>[解  説]
 * <br>[備  考]
 */
public class RingiDao extends AbstractDao {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(RingiDao.class);

    /**
     * <p>Default Constructor
     */
    public RingiDao() {
    }

    /**
     * <p>Set Connection
     * @param con Connection
     */
    public RingiDao(Connection con) {
        super(con);
    }

    /**
     * <br>[機  能] 指定された稟議の添付ファイル情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @return 添付ファイル情報一覧
     * @throws SQLException SQL実行例外
     */
    public List < CmnBinfModel > getRingiTmpFileList(int rngSid)
    throws SQLException {

        return getRingiTmpFileList(rngSid, -1);
    }

    /**
     * <br>[機  能] 指定された稟議の添付ファイル情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @return 添付ファイル情報一覧
     * @throws SQLException SQL実行例外
     */
    public List < CmnBinfModel > getRingiTmpFileList(int rngSid, int userSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List < CmnBinfModel > ret = new ArrayList < CmnBinfModel >();
        CommonBiz cmnBiz = new CommonBiz();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   CMN_BINF.BIN_SID as BIN_SID,");
            sql.addSql("   CMN_BINF.BIN_FILE_NAME as BIN_FILE_NAME,");
            sql.addSql("   CMN_BINF.BIN_FILE_PATH as BIN_FILE_PATH,");
            sql.addSql("   CMN_BINF.BIN_FILE_SIZE as BIN_FILE_SIZE");
            sql.addSql(" from");
            sql.addSql("   RNG_BIN,");
            sql.addSql("   CMN_BINF");
            sql.addSql(" where");
            sql.addSql("   RNG_BIN.BIN_SID = CMN_BINF.BIN_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_BINF.BIN_JKBN = 0");
            sql.addSql(" and");
            sql.addSql("   RNG_BIN.RNG_SID = ?");
            sql.addIntValue(rngSid);
            if (userSid != -1) {
                sql.addSql(" and");
                sql.addSql("   RNG_BIN.USR_SID = ?");
                sql.addIntValue(userSid);
            }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CmnBinfModel binMdl = new CmnBinfModel();
                binMdl.setBinSid(rs.getLong("BIN_SID"));
                binMdl.setBinFileName(rs.getString("BIN_FILE_NAME"));
                binMdl.setBinFilePath(rs.getString("BIN_FILE_PATH"));
                //添付ファイルサイズ設定(表示用)
                long size = rs.getInt("BIN_FILE_SIZE");
                String strSize = cmnBiz.getByteSizeString(size);
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
     * <br>[機  能] 指定された稟議テンプレートの添付ファイル情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngTmpSid 稟議テンプレートSID
     * @return 添付ファイル情報一覧
     * @throws SQLException SQL実行例外
     */
    public List < CmnBinfModel > getRingiTemplateTmpFileList(int rngTmpSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List < CmnBinfModel > ret = new ArrayList < CmnBinfModel >();
        CommonBiz cmnBiz = new CommonBiz();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   CMN_BINF.BIN_SID as BIN_SID,");
            sql.addSql("   CMN_BINF.BIN_FILE_NAME as BIN_FILE_NAME,");
            sql.addSql("   CMN_BINF.BIN_FILE_PATH as BIN_FILE_PATH,");
            sql.addSql("   CMN_BINF.BIN_FILE_SIZE as BIN_FILE_SIZE");
            sql.addSql(" from");
            sql.addSql("   RNG_TEMPLATE_BIN,");
            sql.addSql("   CMN_BINF");
            sql.addSql(" where");
            sql.addSql("   RNG_TEMPLATE_BIN.RTP_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNG_TEMPLATE_BIN.BIN_SID = CMN_BINF.BIN_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_BINF.BIN_JKBN = 0");

            sql.addIntValue(rngTmpSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                CmnBinfModel binMdl = new CmnBinfModel();
                binMdl.setBinSid(rs.getLong("BIN_SID"));
                binMdl.setBinFileName(rs.getString("BIN_FILE_NAME"));
                binMdl.setBinFilePath(rs.getString("BIN_FILE_PATH"));
                //添付ファイルサイズ設定(表示用)
                long size = rs.getInt("BIN_FILE_SIZE");
                String strSize = cmnBiz.getByteSizeString(size);
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
     * <br>[機  能] 指定された稟議のバイナリー情報の論理削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param usid 更新者SID
     * @param date 現在日時
     * @return int 更新件数
     * @throws SQLException SQL実行例外
     */
    public int removeRngBinData(int rngSid, int usid, UDate date) throws SQLException {

        return removeRngBinData(rngSid, -1, usid, date);
    }

    /**
     * <br>[機  能] 指定された稟議のバイナリー情報の論理削除を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @param usid 更新者SID
     * @param date 現在日時
     * @return int 更新件数
     * @throws SQLException SQL実行例外
     */
    public int removeRngBinData(int rngSid, int userSid, int usid, UDate date) throws SQLException {

        PreparedStatement pstmt = null;
        int count = 0;
        Connection con = null;
        con = getCon();

        try {

            //バイナリー情報の論理削除

            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" update");
            sql.addSql("   CMN_BINF");
            sql.addSql(" set ");
            sql.addSql("   BIN_UPUSER = ?,");
            sql.addSql("   BIN_UPDATE = ?,");
            sql.addSql("   BIN_JKBN = ?");
            sql.addIntValue(usid);
            sql.addDateValue(date);
            sql.addIntValue(GSConst.JTKBN_DELETE);
            sql.addSql(" where");
            sql.addSql("   BIN_SID in (");
            sql.addSql("     select BIN_SID from RNG_BIN");
            sql.addSql("     where RNG_SID = ?");
            sql.addIntValue(rngSid);
            if (userSid >= 0) {
                sql.addSql("     and USR_SID = ?");
                sql.addIntValue(userSid);
            }
            sql.addSql("   )");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
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
     * <br>[機  能] 稟議情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @param userSid ユーザSID
     * @return 稟議情報
     * @throws SQLException SQL実行例外
     */
    public RingiDataModel getRingiData(int rngSid, int userSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RingiDataModel model = new RingiDataModel();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RNG_RNDATA.RNG_SID as RNG_SID,");
            sql.addSql("   RNG_RNDATA.RNG_TITLE as RNG_TITLE,");
            sql.addSql("   RNG_RNDATA.RNG_CONTENT as RNG_CONTENT,");
            sql.addSql("   RNG_RNDATA.RNG_STATUS as RNG_STATUS,");
            sql.addSql("   RNG_RNDATA.RNG_APPLICATE as RNG_APPLICATE,");
            sql.addSql("   RNG_RNDATA.RNG_ADATE as RNG_ADATE,");
            sql.addSql("   RNG_RNDATA.RNG_COMPFLG as RNG_COMPFLG,");
            sql.addSql("   CHANNEL.RNC_STATUS as RNC_STATUS,");
            sql.addSql("   CHANNEL.RNC_TYPE as RNC_TYPE,");
            sql.addSql("   CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("   CMN_USRM_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("   CMN_USRM_INF.USI_MEI_KN as USI_MEI_KN");
            sql.addSql(" from");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF,");
            sql.addSql("   RNG_RNDATA");
            sql.addSql("   left join");
            sql.addSql("     (");
            sql.addSql("       select * from RNG_CHANNEL");
            sql.addSql("       where USR_SID = ?");
            sql.addSql("     ) CHANNEL on RNG_RNDATA.RNG_SID = CHANNEL.RNG_SID");

            sql.addSql(" where");
            sql.addSql("   RNG_RNDATA.RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNG_RNDATA.RNG_APPLICATE = CMN_USRM_INF.USR_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");

            sql.addIntValue(userSid);
            sql.addIntValue(rngSid);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                model = new RingiDataModel();
                model.setRngSid(rs.getInt("RNG_SID"));
                model.setRngTitle(rs.getString("RNG_TITLE"));
                model.setRngContent(rs.getString("RNG_CONTENT"));
                model.setRngStatus(rs.getInt("RNG_STATUS"));
                model.setRngApplicate(rs.getInt("RNG_APPLICATE"));
                model.setRngAdate(UDate.getInstanceTimestamp(rs.getTimestamp("RNG_ADATE")));
                model.setStrMakeDate(__createDateStr(model.getRngAdate()));
                model.setRngCompflg(rs.getInt("RNG_COMPFLG"));
                model.setApprUser(
                        rs.getString("USI_SEI").concat(" ").concat(rs.getString("USI_MEI")));
                model.setApprUserDelFlg(rs.getInt("USR_JKBN") == GSConstUser.USER_JTKBN_DELETE);

                model.setRncStatus(rs.getInt("RNC_STATUS"));
                model.setRncType(rs.getInt("RNC_TYPE"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return model;
    }

    /**
     * <br>[機  能] 稟議経路情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @return 稟議経路情報一覧
     * @throws SQLException SQL実行例外
     */
    public List<RingiChannelDataModel> getChannelList(int rngSid) throws SQLException {

        Connection con = null;
        con = getCon();

        //稟議プラグインの使用制限を取得
        //プラグインアクセス設定を取得
        CmnPluginControlDao cntrlDao = new CmnPluginControlDao(con);
        CmnPluginControlModel cntrlModel = cntrlDao.select(RngConst.PLUGIN_ID_RINGI);

        //プラグインアクセス制限方法を取得
        boolean rngControl = false;
        int pctType = 0;
        if (cntrlModel != null) {
            if (cntrlModel.getPctKbn() == GSConstMain.PCT_KBN_MEMBER) {
                rngControl = true;
                pctType = cntrlModel.getPctType();
            }
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<RingiChannelDataModel> ret = new ArrayList<RingiChannelDataModel>();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   RNG_CHANNEL.USR_SID as USR_SID,");
            sql.addSql("   RNG_CHANNEL.RNC_SORT as RNC_SORT,");
            sql.addSql("   RNG_CHANNEL.RNC_STATUS as RNC_STATUS,");
            sql.addSql("   RNG_CHANNEL.RNC_COMMENT as RNC_COMMENT,");
            sql.addSql("   RNG_CHANNEL.RNC_RCVDATE as RNC_RCVDATE,");
            sql.addSql("   RNG_CHANNEL.RNC_CHKDATE as RNC_CHKDATE,");
            sql.addSql("   RNG_CHANNEL.RNC_TYPE as RNC_TYPE,");
            sql.addSql("   CMN_USRM.USR_JKBN as USR_JKBN,");
            sql.addSql("   CMN_USRM_INF.USI_SEI as USI_SEI,");
            sql.addSql("   CMN_USRM_INF.USI_MEI as USI_MEI,");
            sql.addSql("   CMN_USRM_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("   CMN_USRM_INF.USI_MEI_KN as USI_MEI_KN,");
            sql.addSql("   (case");
            sql.addSql("      when CMN_USRM_INF.POS_SID = 0 then ''");
            sql.addSql("      else (select");
            sql.addSql("            POS_NAME");
            sql.addSql("          from");
            sql.addSql("            CMN_POSITION");
            sql.addSql("          where CMN_USRM_INF.POS_SID = CMN_POSITION.POS_SID)");
            sql.addSql("    end) as USI_YAKUSYOKU");
            if (rngControl) {
                sql.addSql("    ,coalesce(RNG_CONTROL_MEMBER.MEMBER_SID, -1) as MEMBER_SID");
            }

            sql.addSql(" from ");
            sql.addSql("   RNG_CHANNEL,");
            sql.addSql("   CMN_USRM,");
            sql.addSql("   CMN_USRM_INF");
            if (rngControl) {
                sql.addSql("    left join");
                sql.addSql("      (");
                sql.addSql("        select");
                sql.addSql("          case CMN_PLUGIN_CONTROL_MEMBER.USR_SID");
                sql.addSql("          when -1 then CMN_BELONGM.USR_SID");
                sql.addSql("          else CMN_PLUGIN_CONTROL_MEMBER.USR_SID");
                sql.addSql("          end as MEMBER_SID");
                sql.addSql("        from");
                sql.addSql("          CMN_PLUGIN_CONTROL_MEMBER");
                sql.addSql("          left join");
                sql.addSql("            CMN_BELONGM");
                sql.addSql("          on");
                sql.addSql("            CMN_PLUGIN_CONTROL_MEMBER.GRP_SID = CMN_BELONGM.GRP_SID");
                sql.addSql("        where PCT_PID = ?");
                sql.addSql("        group by MEMBER_SID");
                sql.addSql("      ) RNG_CONTROL_MEMBER");
                sql.addSql("    on");
                sql.addSql("      CMN_USRM_INF.USR_SID = RNG_CONTROL_MEMBER.MEMBER_SID");
                sql.addStrValue(RngConst.PLUGIN_ID_RINGI);
            }
            sql.addSql(" where");
            sql.addSql("   RNG_CHANNEL.RNG_SID = ?");
            sql.addSql(" and");
            sql.addSql("   RNG_CHANNEL.USR_SID = CMN_USRM.USR_SID");
            sql.addSql(" and");
            sql.addSql("   CMN_USRM.USR_SID = CMN_USRM_INF.USR_SID");

            sql.addSql(" order by");
            sql.addSql("   RNC_SORT");
            sql.addIntValue(rngSid);
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());


            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RingiChannelDataModel model = new RingiChannelDataModel();
                model.setUsrSid(rs.getInt("USR_SID"));
                model.setRncSort(rs.getInt("RNC_SORT"));
                model.setRncStatus(rs.getInt("RNC_STATUS"));
                String comment = NullDefault.getString(rs.getString("RNC_COMMENT"), "");
                comment = StringUtilHtml.transToHTmlForTextArea(comment);
                comment = StringUtil.transToLink(comment, StringUtil.OTHER_WIN, true);
                comment = StringUtilHtml.returntoBR(comment);
                model.setRncComment(comment);
                model.setRncRcvdate(UDate.getInstanceTimestamp(rs.getTimestamp("RNC_RCVDATE")));
                model.setRncChkdate(UDate.getInstanceTimestamp(rs.getTimestamp("RNC_CHKDATE")));
                model.setStrRncChkDate(__createDateStr(model.getRncChkdate()));
                model.setRncType(rs.getInt("RNC_TYPE"));
                model.setDelUser(rs.getInt("USR_JKBN") == GSConst.JTKBN_DELETE);
                model.setUserName(rs.getString("USI_SEI") + " " + rs.getString("USI_MEI"));
                model.setYakusyoku(rs.getString("USI_YAKUSYOKU"));
                if (rngControl) {
                    if (pctType == GSConstMain.PCT_TYPE_LIMIT) {
                        model.setRingiUse(rs.getInt("MEMBER_SID") >= 0);
                    } else {
                        model.setRingiUse(rs.getInt("MEMBER_SID") < 0);
                    }
                } else {
                    model.setRingiUse(true);
                }

                //添付情報一覧を設定
                model.setTmpFileList(getRingiTmpFileList(rngSid, model.getUsrSid()));

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
     * <p>指定されたSID分のユーザー情報数を取得します
     * @param usids ユーザーSID 配列
     * @return List in CMN_USRM_INFModel
     * @throws SQLException SQL実行例外
     */
    public String getUsrInfCount(String[] usids) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String cnt = "0";
        con = getCon();

        if (usids == null) {
            return cnt;
        }
        if (usids.length < 1) {
            return cnt;
        }

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select count(*) as COUNT");
            sql.addSql(" from ");
            sql.addSql("   CMN_USRM_INF, CMN_POSITION");
            sql.addSql(" where ");
            sql.addSql("   CMN_USRM_INF.POS_SID = CMN_POSITION.POS_SID ");
            sql.addSql(" and ");
            sql.addSql("   USR_SID in ( ");

            for (int i = 0; i < usids.length; i++) {
                if (i > 0) {
                    sql.addSql("     , ");
                }
                sql.addSql("     ?");
                sql.addIntValue(Integer.parseInt(usids[i]));
            }
            sql.addSql("        )");

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                cnt = rs.getString("COUNT");
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
     * <br>[機  能] 稟議情報一覧の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param procMode 処理モード
     * @return 稟議情報一覧の件数
     * @throws SQLException SQL実行例外
     */
    public int getRingiDataCount(int userSid, int procMode)
    throws SQLException {

        RingiSearchModel model = new RingiSearchModel();
        model.setKeyword(null);
        model.setGroupSid(-1);
        model.setUserSid(userSid);
        model.setAdminFlg(false);

        return getRingiDataCount(model, procMode);
    }

    /**
     * <br>[機  能] 稟議情報一覧の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param model 稟議検索条件
     * @param procMode 処理モード
     * @return 稟議情報一覧の件数
     * @throws SQLException SQL実行例外
     */
    public int getRingiDataCount(RingiSearchModel model, int procMode) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(RNG_RNDATA.RNG_SID) as RNGCOUNT");

            //処理モードにより検索条件を判断する
            switch (procMode) {
            case RngConst.RNG_MODE_JYUSIN :
                __createSearchJyusinRingiListSql(sql, model);
                break;
            case RngConst.RNG_MODE_SINSEI :
                __createSearchSinseiRingiListSql(sql, model);
                break;
            case RngConst.RNG_MODE_KANRYO :
                __createSearchKanryoRingiListSql(sql, model);
                break;
            case RngConst.RNG_MODE_SOUKOU :
                __createSearchSoukouRingiListSql(sql, model);
                break;
            default :
                return 0;
            }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("RNGCOUNT");
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
     * <br>[機  能] 稟議情報一覧(申請中)の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param model 検索条件
     * @return 稟議情報一覧の件数
     * @throws SQLException SQL実行例外
     */
    public int getSinseiRingiDataCount(RingiSearchModel model)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(RNG_RNDATA.RNG_SID) as RNGCOUNT");
            __createSearchSinseiRingiListSql(sql, model);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("RNGCOUNT");
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
     * <br>[機  能] 稟議情報一覧(完了)の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param model 検索条件
     * @return 稟議情報一覧の件数
     * @throws SQLException SQL実行例外
     */
    public int getKanryoRingiDataCount(RingiSearchModel model)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   count(RNG_RNDATA.RNG_SID) as RNGCOUNT");
            __createSearchKanryoRingiListSql(sql, model);

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("RNGCOUNT");
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
     * <br>[機  能] 稟議情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param procMode 処理モード
     * @param sortKey ソート対象
     * @param orderKey 並び順
     * @param page ページ
     * @param maxCnt ページ毎の最大表示件数
     * @return 稟議情報一覧
     * @throws SQLException SQL実行例外
     */
    public List <RingiDataModel> getRingiDataList(int userSid, int procMode,
                                                    int sortKey, int orderKey,
                                                    int page, int maxCnt)
    throws SQLException {

        RingiSearchModel model = new RingiSearchModel();
        model.setKeyword(null);
        model.setUserSid(userSid);
        model.setSortKey(sortKey);
        model.setOrderKey(orderKey);
        model.setPage(page);
        model.setMaxCnt(maxCnt);
        model.setAdminFlg(false);

        //進行中の場合、第２ソートキーに申請日時を設定
        if (procMode == RngConst.RNG_MODE_SINSEI) {
            model.setSortKey2(RngConst.RNG_SORT_DATE);
            model.setOrderKey2(RngConst.RNG_ORDER_DESC);
        }

        return getRingiDataList(model, procMode);
    }

    /**
     * <br>[機  能] 稟議情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param model 検索条件
     * @param procMode 処理モード
     * @return 稟議情報一覧
     * @throws SQLException SQL実行例外
     */
    public List <RingiDataModel> getRingiDataList(RingiSearchModel model, int procMode)
    throws SQLException {

        List<RingiDataModel> ringiList = null;

        switch (procMode) {
        case RngConst.RNG_MODE_JYUSIN :
            ringiList = getJyusinRingiDataList(model);
            break;
        case RngConst.RNG_MODE_SINSEI :
            ringiList = getSinseiRingiDataList(model);
            break;
        case RngConst.RNG_MODE_KANRYO :
            ringiList = getKanryoRingiDataList(model);
            break;
        case RngConst.RNG_MODE_SOUKOU :
            ringiList = __getSoukouRingiDataList(model);
            break;
        default :
            ringiList = new ArrayList<RingiDataModel>();
        }

        return ringiList;
    }

    /**
     * <br>[機  能] 稟議情報一覧(受信)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param model 検索条件
     * @return 稟議情報一覧
     * @throws SQLException SQL実行例外
     */
    public List <RingiDataModel> getJyusinRingiDataList(RingiSearchModel model)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List <RingiDataModel> ret = new ArrayList <RingiDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RNG_RNDATA.RNG_SID as RNG_SID,");
            sql.addSql("   RNG_RNDATA.RNG_TITLE as RNG_TITLE,");
            sql.addSql("   RNG_RNDATA.RNG_APPLICATE as RNG_APPLICATE,");
            sql.addSql("   APPLICATE_INF.USI_SEI as USI_SEI,");
            sql.addSql("   APPLICATE_INF.USI_MEI as USI_MEI,");
            sql.addSql("   APPLICATE_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("   APPLICATE_INF.USI_MEI_KN as USI_MEI_KN,");
            sql.addSql("   APPLICATE.USR_JKBN as USR_JKBN,");
            sql.addSql("   RNG_RNDATA.RNG_APPLDATE as APPLDATE,");
            sql.addSql("   RNG_CHANNEL.RNC_TYPE as RNC_TYPE,");
            sql.addSql("   RNG_CHANNEL.RNC_RCVDATE as RCVDATE");
            __createSearchJyusinRingiListSql(sql, model);

            //ソート
            sql.addSql(" order by");
            sql.addSql(__createJyusinRingiOrderSql(model.getSortKey(), model.getOrderKey()));
            if (model.getSortKey2() > 0) {
                sql.addSql("," + __createJyusinRingiOrderSql(model.getSortKey2(),
                                                            model.getOrderKey2()));
            }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString(),
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int page = model.getPage();
            int maxCnt = model.getMaxCnt();
            if (page > 1) {
                rs.absolute((page - 1) * maxCnt);
            }

            for (int i = 0; rs.next() && i < maxCnt; i++) {
                RingiDataModel rngMdl = new RingiDataModel();
                rngMdl.setRngSid(rs.getInt("RNG_SID"));
                rngMdl.setRngTitle(rs.getString("RNG_TITLE"));
                rngMdl.setRngApplicate(rs.getInt("RNG_APPLICATE"));
                rngMdl.setApprUser(
                        rs.getString("USI_SEI").concat(" ").concat(rs.getString("USI_MEI")));
                rngMdl.setApprUserDelFlg(rs.getInt("USR_JKBN") == GSConstUser.USER_JTKBN_DELETE);
                rngMdl.setRngAppldate(UDate.getInstanceTimestamp(rs.getTimestamp("APPLDATE")));
                rngMdl.setStrRngAppldate(__createDateStr(rngMdl.getRngAppldate()));
                rngMdl.setRncType(rs.getInt("RNC_TYPE"));
                rngMdl.setRcvDate(UDate.getInstanceTimestamp(rs.getTimestamp("RCVDATE")));
                rngMdl.setStrRcvDate(__createDateStr(rngMdl.getRcvDate()));

                ret.add(rngMdl);
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
     * <br>[機  能] 稟議情報(受信)のソート部分のSQLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sortKey ソート対象
     * @param orderKey ソート順
     * @return ソート部分のSQL
     */
    private String __createJyusinRingiOrderSql(int sortKey, int orderKey) {

        StringBuilder sortString = new StringBuilder("");

        switch (sortKey) {
        case RngConst.RNG_SORT_TITLE :
            sortString.append("   RNG_TITLE");
            break;
        case RngConst.RNG_SORT_NAME :
            sortString.append("   USI_SEI_KN");
            if (orderKey == RngConst.RNG_ORDER_DESC) {
                sortString.append(" desc");
            }
            sortString.append(", USI_MEI_KN");
            break;
        case RngConst.RNG_SORT_DATE :
            sortString.append("   APPLDATE");
            break;
        case RngConst.RNG_SORT_JYUSIN :
            sortString.append("   RCVDATE");
            break;
        default :
            sortString.append("   RNG_TITLE");
        }
        if (orderKey == RngConst.RNG_ORDER_DESC) {
            sortString.append(" desc");
        }
        return sortString.toString();
    }

    /**
     * <br>[機  能] 稟議情報一覧(申請中)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param model 検索条件
     * @return 稟議情報一覧
     * @throws SQLException SQL実行例外
     */
    public List <RingiDataModel> getSinseiRingiDataList(RingiSearchModel model)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List <RingiDataModel> ret = new ArrayList <RingiDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RNG_RNDATA.RNG_SID as RNG_SID,");
            sql.addSql("   RNG_RNDATA.RNG_TITLE as RNG_TITLE,");
            sql.addSql("   RNG_RNDATA.RNG_APPLICATE as RNG_APPLICATE,");
            sql.addSql("   APPLICATE_INF.USI_SEI as USI_SEI,");
            sql.addSql("   APPLICATE_INF.USI_MEI as USI_MEI,");
            sql.addSql("   APPLICATE_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("   APPLICATE_INF.USI_MEI_KN as USI_MEI_KN,");
            sql.addSql("   APPLICATE.USR_JKBN as USR_JKBN,");
            sql.addSql("   RNG_RNDATA.RNG_APPLDATE as APPLDATE,");
            sql.addSql("   CHANNEL.CHKDATE as CHKDATE");
            __createSearchSinseiRingiListSql(sql, model);

            //ソート
            sql.addSql(" order by");
            sql.addSql(__createSinseiRingiSortSql(model.getSortKey(), model.getOrderKey()));
            if (model.getSortKey2() > 0) {
                sql.addSql(", " + __createSinseiRingiSortSql(model.getSortKey2(),
                                                            model.getOrderKey2()));
            }


            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString(),
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int page = model.getPage();
            int maxCnt = model.getMaxCnt();
            if (page > 1) {
                rs.absolute((page - 1) * maxCnt);
            }

            for (int i = 0; rs.next() && i < maxCnt; i++) {
                RingiDataModel rngMdl = new RingiDataModel();
                rngMdl.setRngSid(rs.getInt("RNG_SID"));
                rngMdl.setRngTitle(rs.getString("RNG_TITLE"));
                rngMdl.setRngApplicate(rs.getInt("RNG_APPLICATE"));
                rngMdl.setApprUser(
                        rs.getString("USI_SEI").concat(" ").concat(rs.getString("USI_MEI")));
                rngMdl.setApprUserDelFlg(rs.getInt("USR_JKBN") == GSConstUser.USER_JTKBN_DELETE);
                rngMdl.setRngAppldate(UDate.getInstanceTimestamp(rs.getTimestamp("APPLDATE")));
                rngMdl.setStrRngAppldate(__createDateStr(rngMdl.getRngAppldate()));
                rngMdl.setLastManageDate(UDate.getInstanceTimestamp(rs.getTimestamp("CHKDATE")));
                rngMdl.setStrLastManageDate(__createDateStr(rngMdl.getLastManageDate()));

                ret.add(rngMdl);
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
     * <br>[機  能] 稟議情報一覧(申請)取得SQLのソート部分SQLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sortKey ソート対象
     * @param orderKey ソート順
     * @return ソート部分SQL
     */
    private String __createSinseiRingiSortSql(int sortKey, int orderKey) {
        StringBuilder sortString = new StringBuilder("");
        switch (sortKey) {
        case RngConst.RNG_SORT_TITLE :
            sortString.append("   RNG_TITLE");
            break;
        case RngConst.RNG_SORT_NAME :
            sortString.append("   USI_SEI_KN");
            if (orderKey == RngConst.RNG_ORDER_DESC) {
                sortString.append(" desc");
            }
            sortString.append(", USI_MEI_KN");
            break;
        case RngConst.RNG_SORT_DATE :
            sortString.append("   APPLDATE");
            break;
        case RngConst.RNG_SORT_KAKUNIN :
            sortString.append("   CHKDATE");
            break;
        default :
            sortString.append("   RNG_TITLE");
        }
        if (orderKey == RngConst.RNG_ORDER_DESC) {
            sortString.append(" desc");
        }

        return sortString.toString();
    }

    /**
     * <br>[機  能] 稟議情報一覧(完了)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param model 検索条件
     * @return 稟議情報一覧
     * @throws SQLException SQL実行例外
     */
    public List <RingiDataModel> getKanryoRingiDataList(RingiSearchModel model)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List <RingiDataModel> ret = new ArrayList <RingiDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RNG_RNDATA.RNG_SID as RNG_SID,");
            sql.addSql("   RNG_RNDATA.RNG_TITLE as RNG_TITLE,");
            sql.addSql("   RNG_RNDATA.RNG_STATUS as RNG_STATUS,");
            sql.addSql("   RNG_RNDATA.RNG_APPLICATE as RNG_APPLICATE,");
            sql.addSql("   APPLICATE_INF.USI_SEI as USI_SEI,");
            sql.addSql("   APPLICATE_INF.USI_MEI as USI_MEI,");
            sql.addSql("   APPLICATE_INF.USI_SEI_KN as USI_SEI_KN,");
            sql.addSql("   APPLICATE_INF.USI_MEI_KN as USI_MEI_KN,");
            sql.addSql("   APPLICATE.USR_JKBN as USR_JKBN,");
            sql.addSql("   RNG_RNDATA.RNG_APPLDATE as APPLDATE,");
            sql.addSql("   CHANNEL.CHKDATE as CHKDATE");
            __createSearchKanryoRingiListSql(sql, model);

            //第1ソート
            sql.addSql(" order by");
            sql.addSql(__createKanryoRingiSortSql(model.getSortKey(), model.getOrderKey()));
            //第2ソート
            if (model.getSortKey() != RngConst.RNG_SORT_DATE) {
                sql.addSql(", APPLDATE desc");
            }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);;
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int page = model.getPage();
            int maxCnt = model.getMaxCnt();
            if (page > 1) {
                rs.absolute((page - 1) * maxCnt);
            }

            for (int i = 0; rs.next() && i < maxCnt; i++) {
                RingiDataModel rngMdl = new RingiDataModel();
                rngMdl.setRngSid(rs.getInt("RNG_SID"));
                rngMdl.setRngTitle(rs.getString("RNG_TITLE"));
                rngMdl.setRngStatus(rs.getInt("RNG_STATUS"));
                rngMdl.setRngApplicate(rs.getInt("RNG_APPLICATE"));
                rngMdl.setApprUser(
                        rs.getString("USI_SEI").concat(" ").concat(rs.getString("USI_MEI")));
                rngMdl.setApprUserDelFlg(rs.getInt("USR_JKBN") == GSConstUser.USER_JTKBN_DELETE);
                rngMdl.setRngAppldate(UDate.getInstanceTimestamp(rs.getTimestamp("APPLDATE")));
                rngMdl.setStrRngAppldate(__createDateStr(rngMdl.getRngAppldate()));
                rngMdl.setLastManageDate(UDate.getInstanceTimestamp(rs.getTimestamp("CHKDATE")));
                rngMdl.setStrLastManageDate(__createDateStr(rngMdl.getLastManageDate()));

                ret.add(rngMdl);
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
     * <br>[機  能] 稟議情報一覧(完了)取得SQLのソート部分SQLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sortKey ソート対象
     * @param orderKey ソート順
     * @return ソート部分SQL
     */
    private String __createKanryoRingiSortSql(int sortKey, int orderKey) {
        StringBuilder sortString = new StringBuilder("");
        switch (sortKey) {
        case RngConst.RNG_SORT_TITLE :
            sortString.append("   RNG_TITLE");
            break;
        case RngConst.RNG_SORT_NAME :
            sortString.append("   USI_SEI_KN");
            if (orderKey == RngConst.RNG_ORDER_DESC) {
                sortString.append(" desc");
            }
            sortString.append(", USI_MEI_KN");
            break;
        case RngConst.RNG_SORT_KEKKA :
            sortString.append("   RNG_STATUS");
            break;
        case RngConst.RNG_SORT_DATE :
            sortString.append("   APPLDATE");
            break;
        case RngConst.RNG_SORT_KAKUNIN :
            sortString.append("   CHKDATE");
            break;
        default :
            sortString.append("   RNG_TITLE");
        }
        if (orderKey == RngConst.RNG_ORDER_DESC) {
            sortString.append(" desc");
        }

        return sortString.toString();
    }

    /**
     * <br>[機  能] 指定した稟議の申請者がメール送信対象のユーザか判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param rngSid 稟議SID
     * @return ユーザSID
     * @throws SQLException SQL実行例外
     */
    public boolean isApplicateSmUser(int rngSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = true;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   count(USR_SID) as USRCNT");
            sql.addSql(" from");
            sql.addSql("   RNG_UCONF");
            sql.addSql(" where");
            sql.addSql("   RUR_SML_NTF = ?");
            sql.addSql(" and");
            sql.addSql("   USR_SID in (");
            sql.addSql("     select RNG_APPLICATE from RNG_RNDATA");
            sql.addSql("     where RNG_SID = ?");
            sql.addSql("   )");

            pstmt = con.prepareStatement(sql.toSqlString());

            sql.addIntValue(RngConst.RNG_SMAIL_NOT_TSUUCHI);
            sql.addIntValue(rngSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("USRCNT") == 0;
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return result;
    }

    /**
     * <br>[機  能] 稟議情報一覧(草稿)を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param model 検索条件
     * @return 稟議情報一覧
     * @throws SQLException SQL実行例外
     */
    private List <RingiDataModel> __getSoukouRingiDataList(RingiSearchModel model)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List <RingiDataModel> ret = new ArrayList <RingiDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RNG_RNDATA.RNG_SID as RNG_SID,");
            sql.addSql("   RNG_RNDATA.RNG_TITLE as RNG_TITLE,");
            sql.addSql("   RNG_RNDATA.RNG_ADATE as MAKEDATE");
            __createSearchSoukouRingiListSql(sql, model);

            //ソート
            sql.addSql(" order by");
            sql.addSql(__createSoukouRingiSortSql(model.getSortKey(), model.getOrderKey()));
            if (model.getSortKey2() > 0) {
                sql.addSql(", " + __createSoukouRingiSortSql(model.getSortKey2(),
                                                            model.getOrderKey2()));
            }

            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            int page = model.getPage();
            int maxCnt = model.getMaxCnt();
            if (page > 1) {
                rs.absolute((page - 1) * maxCnt);
            }

            for (int i = 0; rs.next() && i < maxCnt; i++) {
                RingiDataModel rngMdl = new RingiDataModel();
                rngMdl.setRngSid(rs.getInt("RNG_SID"));
                rngMdl.setRngTitle(rs.getString("RNG_TITLE"));
                rngMdl.setMakeDate(UDate.getInstanceTimestamp(rs.getTimestamp("MAKEDATE")));
                rngMdl.setStrMakeDate(__createDateStr(rngMdl.getMakeDate()));

                ret.add(rngMdl);
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
     * <br>[機  能] 稟議情報一覧(草稿)取得SQLのソート部分SQLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sortKey ソート対象
     * @param orderKey ソート順
     * @return ソート部分SQL
     */
    private String __createSoukouRingiSortSql(int sortKey, int orderKey) {
        StringBuilder sortString = new StringBuilder("");
        switch (sortKey) {
        case RngConst.RNG_SORT_TITLE :
            sortString.append("   RNG_TITLE");
            break;
        case RngConst.RNG_SORT_TOUROKU :
            sortString.append("   MAKEDATE");
            break;
        default :
            sortString.append("   RNG_TITLE");
        }
        if (orderKey == RngConst.RNG_ORDER_DESC) {
            sortString.append(" desc");
        }

        return sortString.toString();
    }

    /**
     * <br>[機  能] 指定したユーザで現在確認中の稟議SIDを取得
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @return 稟議SID
     * @throws SQLException SQL実行例外
     */
    public List <RingiDataModel> getProgressRingiSidList(int userSid)
    throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        RingiDataModel rngMdl = null;
        List <RingiDataModel> ret = new ArrayList <RingiDataModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   RNG_SID");
            sql.addSql(" from");
            sql.addSql("   RNG_CHANNEL");
            sql.addSql(" where");
            sql.addSql("   USR_SID=?");
            sql.addSql(" and");
            sql.addSql("   RNC_STATUS=?");
            sql.addIntValue(userSid);
            sql.addIntValue(RngConst.RNG_RNCSTATUS_CONFIRM);
            log__.info(sql.toLogString());

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            log__.info(sql.toLogString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                rngMdl = new RingiDataModel();
                rngMdl.setRngSid(rs.getInt("RNG_SID"));
                ret.add(rngMdl);
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
     * <br>[機  能] 稟議一覧検索時(受信)の検索部SQLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SQLバッファ
     * @param model 検索条件
     */
    private void __createSearchJyusinRingiListSql(SqlBuffer sql, RingiSearchModel model) {

        //SQL文
        sql.addSql(" from");
        sql.addSql("   RNG_RNDATA,");
        sql.addSql("   RNG_CHANNEL,");
        sql.addSql("   CMN_USRM APPLICATE,");
        sql.addSql("   CMN_USRM_INF APPLICATE_INF");

        sql.addSql(" where");
        sql.addSql("   RNG_RNDATA.RNG_STATUS <> ?");
        sql.addSql(" and");
        sql.addSql("   RNG_RNDATA.RNG_APPLICATE = APPLICATE.USR_SID");
        sql.addSql(" and");
        sql.addSql("   APPLICATE.USR_SID = APPLICATE_INF.USR_SID");

        sql.addSql(" and");
        sql.addSql("   RNG_RNDATA.RNG_SID = RNG_CHANNEL.RNG_SID");
        sql.addIntValue(RngConst.RNG_STATUS_DRAFT);

        //キーワード検索
        __setSearchKeywordSql(sql, model);

        //グループ/ユーザ
        if (model.getUserSid() > 0) {
            sql.addSql(" and");
            sql.addSql("   RNG_CHANNEL.USR_SID = ?");
            sql.addIntValue(model.getUserSid());
        } else if (model.getGroupSid() > 0) {
            sql.addSql(" and");
            sql.addSql("   RNG_CHANNEL.USR_SID in (");
            sql.addSql("     select CMN_BELONGM.USR_SID");
            sql.addSql("     from CMN_BELONGM, CMN_USRM");
            sql.addSql("     where CMN_BELONGM.GRP_SID = ?");
            sql.addSql("     and CMN_BELONGM.USR_SID = CMN_USRM.USR_SID");
            sql.addSql("     and CMN_USRM.USR_JKBN = ?");
            sql.addSql("   )");
            sql.addIntValue(model.getGroupSid());
            sql.addIntValue(GSConst.JTKBN_TOROKU);
        }

        //申請日時
        if (model.getApplDateFr() != null) {
            sql.addSql(" and");
            sql.addSql("   RNG_RNDATA.RNG_APPLDATE >= ?");
            UDate date = UDate.getInstance(model.getApplDateFr().getTimeMillis());
            date.setHour(0);
            date.setMinute(0);
            date.setSecond(0);
            date.setMilliSecond(0);
            sql.addDateValue(date);
        }
        if (model.getApplDateTo() != null) {
            sql.addSql(" and");
            sql.addSql("   RNG_RNDATA.RNG_APPLDATE <= ?");
            UDate date = UDate.getInstance(model.getApplDateTo().getTimeMillis());
            date.setHour(23);
            date.setMinute(59);
            date.setSecond(59);
            date.setMilliSecond(999);
            sql.addDateValue(date);
        }

        sql.addSql(" and");
        sql.addSql("   (");
        sql.addSql("     (");
        sql.addSql("         RNG_CHANNEL.RNC_TYPE in (?,");
        sql.addSql("                                  ?)");
        sql.addSql("       and");
        sql.addSql("         RNG_CHANNEL.RNC_STATUS = ?");
        sql.addSql("       and");
        sql.addSql("         RNG_RNDATA.RNG_COMPFLG = ?");
        sql.addSql("     )");
        sql.addSql("     or");
        sql.addSql("     (");
        sql.addSql("         RNG_CHANNEL.RNC_TYPE = ?");
        sql.addSql("       and");
        sql.addSql("         RNG_CHANNEL.RNC_STATUS = ?");
        sql.addSql("       and");
        sql.addSql("         RNG_RNDATA.RNG_STATUS = ?");
        sql.addSql("       and");
        sql.addSql("         RNG_RNDATA.RNG_COMPFLG = ?");
        sql.addSql("     )");
        sql.addSql("   )");
        sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
        sql.addIntValue(RngConst.RNG_RNCTYPE_APPL);
        sql.addIntValue(RngConst.RNG_RNCSTATUS_CONFIRM);
        sql.addIntValue(RngConst.RNG_COMPFLG_UNDECIDED);
        sql.addIntValue(RngConst.RNG_RNCTYPE_CONFIRM);
        sql.addIntValue(RngConst.RNG_RNCSTATUS_NOSET);
        sql.addIntValue(RngConst.RNG_STATUS_SETTLED);
        sql.addIntValue(RngConst.RNG_COMPFLG_COMPLETE);

    }

    /**
     * <br>[機  能] 稟議一覧検索時(申請中)の検索部SQLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SQLバッファ
     * @param model 検索条件
     */
    private void __createSearchSinseiRingiListSql(SqlBuffer sql, RingiSearchModel model) {

        //SQL文
        sql.addSql(" from");
        sql.addSql("   RNG_RNDATA,");
        sql.addSql("   (");
        sql.addSql("     select");
        sql.addSql("       RNG_SID,");
        sql.addSql("       max(RNC_CHKDATE) as CHKDATE");
        sql.addSql("     from");
        sql.addSql("       RNG_CHANNEL");
        sql.addSql("     group by");
        sql.addSql("       RNG_SID");
        sql.addSql("   ) CHANNEL,");
        sql.addSql("   CMN_USRM APPLICATE,");
        sql.addSql("   CMN_USRM_INF APPLICATE_INF");

        sql.addSql(" where");
        sql.addSql("   RNG_RNDATA.RNG_STATUS <> ?");
        sql.addSql(" and");
        sql.addSql("   RNG_RNDATA.RNG_COMPFLG = ?");
        sql.addIntValue(RngConst.RNG_STATUS_DRAFT);
        sql.addIntValue(RngConst.RNG_COMPFLG_UNDECIDED);

        //キーワード検索
        __setSearchKeywordSql(sql, model);

        int groupSid = model.getGroupSid();
        int userSid = model.getUserSid();
        if (groupSid != -1 || userSid != -1) {
            if (model.isAdminFlg()) {
                sql.addSql(" and");
                if (userSid != -1) {
                    sql.addSql("   APPLICATE.USR_SID = ?");
                    sql.addIntValue(userSid);
                } else {
                    sql.addSql("   APPLICATE.USR_SID in (");
                    sql.addSql("     select");
                    sql.addSql("       CMN_USRM.USR_SID");
                    sql.addSql("     from");
                    sql.addSql("       CMN_USRM,");
                    sql.addSql("       CMN_BELONGM");
                    sql.addSql("     where");
                    sql.addSql("       CMN_USRM.USR_JKBN = ?");
                    sql.addSql("     and");
                    sql.addSql("       CMN_BELONGM.GRP_SID = ?");
                    sql.addSql("     and");
                    sql.addSql("       CMN_USRM.USR_SID = CMN_BELONGM.USR_SID");
                    sql.addSql("     )");
                    sql.addIntValue(GSConst.JTKBN_TOROKU);
                    sql.addIntValue(groupSid);
                }
            } else {
                sql.addSql(" and");
                sql.addSql("   (");
                sql.addSql("     APPLICATE.USR_SID = ?");
                sql.addSql("   or");
                sql.addSql("     RNG_RNDATA.RNG_SID in (");
                sql.addSql("       select");
                sql.addSql("         RNG_CHANNEL.RNG_SID");
                sql.addSql("       from");
                sql.addSql("         RNG_CHANNEL,");
                sql.addSql("         CMN_USRM");
                sql.addSql("       where");
                sql.addSql("         RNG_CHANNEL.USR_SID = ?");
                sql.addSql("       and");
                sql.addSql("         RNG_CHANNEL.RNC_TYPE = ?");
                sql.addSql("       and");
                sql.addSql("         RNG_CHANNEL.RNC_STATUS = ?");
                sql.addSql("       and");
                sql.addSql("         CMN_USRM.USR_JKBN = ?");
                sql.addSql("       and");
                sql.addSql("         RNG_CHANNEL.USR_SID = CMN_USRM.USR_SID");
                sql.addSql("     )");
                sql.addSql("   )");

                sql.addIntValue(userSid);
                sql.addIntValue(userSid);
                sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
                sql.addIntValue(RngConst.RNG_RNCSTATUS_APPR);
                sql.addIntValue(GSConst.JTKBN_TOROKU);
            }
        }

        //申請日時
        if (model.getApplDateFr() != null) {
            sql.addSql(" and");
            sql.addSql("   RNG_RNDATA.RNG_APPLDATE >= ?");
            UDate date = UDate.getInstance(model.getApplDateFr().getTimeMillis());
            date.setHour(0);
            date.setMinute(0);
            date.setSecond(0);
            date.setMilliSecond(0);
            sql.addDateValue(date);
        }
        if (model.getApplDateTo() != null) {
            sql.addSql(" and");
            sql.addSql("   RNG_RNDATA.RNG_APPLDATE <= ?");
            UDate date = UDate.getInstance(model.getApplDateTo().getTimeMillis());
            date.setHour(23);
            date.setMinute(59);
            date.setSecond(59);
            date.setMilliSecond(999);
            sql.addDateValue(date);
        }

        //最終確認日
        if (model.getLastMagageDateFr() != null) {
            sql.addSql(" and");
            sql.addSql("   CHANNEL.CHKDATE >= ?");
            UDate date = UDate.getInstance(model.getLastMagageDateFr().getTimeMillis());
            date.setHour(0);
            date.setMinute(0);
            date.setSecond(0);
            date.setMilliSecond(0);
            sql.addDateValue(date);
        }
        if (model.getLastMagageDateTo() != null) {
            sql.addSql(" and");
            sql.addSql("   CHANNEL.CHKDATE <= ?");
            UDate date = UDate.getInstance(model.getLastMagageDateTo().getTimeMillis());
            date.setHour(23);
            date.setMinute(59);
            date.setSecond(59);
            date.setMilliSecond(999);
            sql.addDateValue(date);
        }

        sql.addSql(" and");
        sql.addSql("   RNG_RNDATA.RNG_SID = CHANNEL.RNG_SID");
        sql.addSql(" and");
        sql.addSql("   RNG_RNDATA.RNG_APPLICATE = APPLICATE.USR_SID");
        sql.addSql(" and");
        sql.addSql("   APPLICATE.USR_SID = APPLICATE_INF.USR_SID");

    }

    /**
     * <br>[機  能] 稟議一覧検索時(完了)の検索部SQLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SQLバッファ
     * @param model 検索条件
     */
    private void __createSearchKanryoRingiListSql(SqlBuffer sql, RingiSearchModel model) {

        //SQL文
        sql.addSql(" from");
        sql.addSql("   RNG_RNDATA,");
        sql.addSql("   (");
        sql.addSql("     select");
        sql.addSql("       RNG_SID,");
        sql.addSql("       max(RNC_CHKDATE) as CHKDATE");
        sql.addSql("     from");
        sql.addSql("       RNG_CHANNEL");
        sql.addSql("     group by");
        sql.addSql("       RNG_SID");
        sql.addSql("   ) CHANNEL,");
        sql.addSql("   CMN_USRM APPLICATE,");
        sql.addSql("   CMN_USRM_INF APPLICATE_INF");

        sql.addSql(" where");
        sql.addSql("   RNG_RNDATA.RNG_COMPFLG = ?");
        sql.addSql(" and");
        sql.addSql("   RNG_RNDATA.RNG_SID = CHANNEL.RNG_SID");
        sql.addSql(" and");
        sql.addSql("   RNG_RNDATA.RNG_APPLICATE = APPLICATE.USR_SID");
        sql.addSql(" and");
        sql.addSql("   APPLICATE.USR_SID = APPLICATE_INF.USR_SID");
        sql.addIntValue(RngConst.RNG_COMPFLG_COMPLETE);

        //キーワード検索
        __setSearchKeywordSql(sql, model);

        int groupSid = model.getGroupSid();
        int userSid = model.getUserSid();
        if (groupSid != -1 || userSid != -1) {
            if (model.isAdminFlg()) {
                sql.addSql(" and");
                if (userSid != -1) {
                    sql.addSql("   APPLICATE.USR_SID = ?");
                    sql.addIntValue(userSid);
                } else {
                    sql.addSql("   APPLICATE.USR_SID in (");
                    sql.addSql("     select");
                    sql.addSql("       CMN_USRM.USR_SID");
                    sql.addSql("     from");
                    sql.addSql("       CMN_USRM,");
                    sql.addSql("       CMN_BELONGM");
                    sql.addSql("     where");
                    sql.addSql("       CMN_USRM.USR_SID = CMN_BELONGM.USR_SID");
                    sql.addSql("     and");
                    sql.addSql("       CMN_USRM.USR_JKBN = ?");
                    sql.addSql("     and");
                    sql.addSql("       CMN_BELONGM.GRP_SID = ?");
                    sql.addSql("     )");
                    sql.addIntValue(GSConst.JTKBN_TOROKU);
                    sql.addIntValue(groupSid);
                }
            } else {
                sql.addSql(" and");
                sql.addSql("   (");
                sql.addSql("     APPLICATE.USR_SID = ?");
                sql.addSql("   or");
                sql.addSql("     RNG_RNDATA.RNG_SID in (");
                sql.addSql("       select");
                sql.addSql("         RNG_CHANNEL.RNG_SID");
                sql.addSql("       from");
                sql.addSql("         RNG_CHANNEL");
                sql.addSql("       where");
                sql.addSql("         RNG_CHANNEL.USR_SID = ?");
                sql.addSql("       and");
                sql.addSql("         (");
                sql.addSql("             RNG_CHANNEL.RNC_TYPE = ?");
                sql.addSql("         or");
                sql.addSql("           (");
                sql.addSql("             RNG_CHANNEL.RNC_TYPE = ?");
                sql.addSql("           and");
                sql.addSql("             RNG_CHANNEL.RNC_STATUS = ?");
                sql.addSql("           )");
                sql.addSql("         )");
                sql.addSql("     )");
                sql.addSql("   )");

                sql.addIntValue(userSid);
                sql.addIntValue(userSid);
                sql.addIntValue(RngConst.RNG_RNCTYPE_APPR);
                sql.addIntValue(RngConst.RNG_RNCTYPE_CONFIRM);
                sql.addIntValue(RngConst.RNG_RNCSTATUS_CONFIRMATION);
            }
        }

        //申請日時
        if (model.getApplDateFr() != null) {
            sql.addSql(" and");
            sql.addSql("   RNG_RNDATA.RNG_APPLDATE >= ?");
            UDate date = UDate.getInstance(model.getApplDateFr().getTimeMillis());
            date.setHour(0);
            date.setMinute(0);
            date.setSecond(0);
            date.setMilliSecond(0);
            sql.addDateValue(date);
        }
        if (model.getApplDateTo() != null) {
            sql.addSql(" and");
            sql.addSql("   RNG_RNDATA.RNG_APPLDATE <= ?");
            UDate date = UDate.getInstance(model.getApplDateTo().getTimeMillis());
            date.setHour(23);
            date.setMinute(59);
            date.setSecond(59);
            date.setMilliSecond(999);
            sql.addDateValue(date);
        }

        //最終確認日
        if (model.getLastMagageDateFr() != null) {
            sql.addSql(" and");
            sql.addSql("   CHANNEL.CHKDATE >= ?");
            UDate date = UDate.getInstance(model.getLastMagageDateFr().getTimeMillis());
            date.setHour(0);
            date.setMinute(0);
            date.setSecond(0);
            date.setMilliSecond(0);
            sql.addDateValue(date);
        }
        if (model.getLastMagageDateTo() != null) {
            sql.addSql(" and");
            sql.addSql("   CHANNEL.CHKDATE <= ?");
            UDate date = UDate.getInstance(model.getLastMagageDateTo().getTimeMillis());
            date.setHour(23);
            date.setMinute(59);
            date.setSecond(59);
            date.setMilliSecond(999);
            sql.addDateValue(date);
        }

    }

    /**
     * <br>[機  能] 更新 or 削除対象の稟議SID一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param delMdl 手動削除設定モデル
     * @return 稟議SID一覧
     * @throws SQLException SQL実行時例外
     */
    public List<Integer> getUpdateRingilList(RngDeleteModel delMdl) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> rngSidList = new ArrayList<Integer>();
        SqlBuffer sql = null;

        try {
            UDate delDate = new UDate();
            delDate.setMaxHhMmSs();
            delDate.addYear((delMdl.getDelYear() * -1));
            delDate.addMonth((delMdl.getDelMonth() * -1));
            delDate.addDay((delMdl.getDelDay() * -1));

            sql = new SqlBuffer();
            sql.addSql(" select RNG_SID from RNG_RNDATA");

            //申請中
            if (delMdl.getDelType() == RngDeleteModel.DELTYPE_PENDING) {
                sql.addSql(" where");
                sql.addSql("   RNG_RNDATA.RNG_STATUS <> ?");
                sql.addSql(" and");
                sql.addSql("   RNG_RNDATA.RNG_COMPFLG = ?");
                sql.addSql(" and RNG_APPLDATE <= ? ");
                sql.addIntValue(RngConst.RNG_STATUS_DRAFT);
                sql.addIntValue(RngConst.RNG_COMPFLG_UNDECIDED);
                sql.addDateValue(delDate);
            }
            //完了
            if (delMdl.getDelType() == RngDeleteModel.DELTYPE_COMPLETE) {
                sql.addSql(" where");
                sql.addSql("   RNG_RNDATA.RNG_COMPFLG = ?");
                sql.addSql(" and");
                sql.addSql("   RNG_APPLDATE <= ? ");
                sql.addIntValue(RngConst.RNG_COMPFLG_COMPLETE);
                sql.addDateValue(delDate);
            }
            //草稿
            if (delMdl.getDelType() == RngDeleteModel.DELTYPE_DRAFT) {
                sql.addSql(" where");
                sql.addSql("   RNG_RNDATA.RNG_STATUS = ?");
                sql.addSql(" and");
                sql.addSql("   RNG_MAKEDATE <= ? ");
                sql.addIntValue(RngConst.RNG_STATUS_DRAFT);
                sql.addDateValue(delDate);
            }

            log__.info(sql.toLogString());

            pstmt = getCon().prepareStatement(sql.toSqlString());

            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                rngSidList.add(rs.getInt("RNG_SID"));
            }
        } catch (SQLException e) {
            if (sql != null) {
                log__.error("Error SQL: " + sql.toLogString());
            }
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closePreparedStatement(pstmt);
        }

        return rngSidList;
    }

    /**
     * <br>[機  能] 稟議一覧検索時(草稿)の検索部SQLを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SQLバッファ
     * @param model 検索条件
     */
    private void __createSearchSoukouRingiListSql(SqlBuffer sql, RingiSearchModel model) {

        //SQL文
        sql.addSql(" from");
        sql.addSql("   RNG_RNDATA");

        sql.addSql(" where");
        sql.addSql("   RNG_RNDATA.RNG_STATUS = ?");
        sql.addSql(" and");
        sql.addSql("   RNG_RNDATA.RNG_AUID = ?");
        sql.addIntValue(RngConst.RNG_STATUS_DRAFT);
        sql.addIntValue(model.getUserSid());

        //キーワード検索
        __setSearchKeywordSql(sql, model);

    }

    /**
     * <br>[機  能] 検索部SQLのうち、キーワード検索部分を作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SqlBuffer
     * @param model 検索条件
     */
    private void __setSearchKeywordSql(SqlBuffer sql, RingiSearchModel model) {

        List<String> keyword = model.getKeyword();
        if (keyword == null || keyword.isEmpty()) {
            return;
        }

        if (model.isTitleSearchFlg()) {
            sql.addSql(" and");
            if (model.isContentSearchFlg()) {
                sql.addSql("  (");
                sql.addSql("  (");
            }

            __setMultiTypeSql(sql, model, "RNG_RNDATA.RNG_TITLE");
        }

        if (model.isContentSearchFlg()) {
            if (model.isTitleSearchFlg()) {
                sql.addSql("  )");
                sql.addSql("  or");
                sql.addSql("  (");
            } else {
                sql.addSql(" and");
            }

            __setMultiTypeSql(sql, model, "RNG_RNDATA.RNG_CONTENT");

            if (model.isTitleSearchFlg()) {
                sql.addSql("  )");
                sql.addSql("  )");
            }
        }

    }

    /**
     * <br>[機  能] AND OR 条件を使用した検索部SQLを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sql SqlBuffer
     * @param model 検索条件
     * @param element 検索対象
     */
    private void __setMultiTypeSql(SqlBuffer sql, RingiSearchModel model, String element) {

        List<String> keywordList = model.getKeyword();
        boolean pluralValue = model.getKeywordType() == RngConst.RNG_SEARCHTYPE_OR
                                && keywordList.size() > 1;

        String searchType = "and";
        if (pluralValue) {
            sql.addSql("   (");
            searchType = "or";
        }

        boolean addSearchType = false;
        for (String keyword : keywordList) {
            if (addSearchType) {
                sql.addSql(" " + searchType);
            }
            sql.addSql("   " + element + " like '%"
                    + JDBCUtil.encFullStringLike(keyword)
                    + "%' ESCAPE '"
                    + JDBCUtil.def_esc
                    + "'");
            addSearchType = true;
        }

        if (pluralValue) {
            sql.addSql("   )");
        }

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

        StringBuilder strDate = new StringBuilder("");
        strDate.append(UDateUtil.getSlashYYMD(date));
        strDate.append(" ");
        strDate.append(UDateUtil.getSeparateHMS(date));

        return strDate.toString();
    }
}
