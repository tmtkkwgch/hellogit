package jp.groupsession.v2.ntp.ntp030;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.ntp.dao.NtpDataDao;
import jp.groupsession.v2.ntp.model.NtpCommentModel;
import jp.groupsession.v2.ntp.model.NtpDataModel;
import jp.groupsession.v2.ntp.ntp010.Ntp010Biz;
import jp.groupsession.v2.ntp.ntp030.model.Ntp030CommentModel;
import jp.groupsession.v2.ntp.ntp030.model.Ntp030DspCommentModel;
import jp.groupsession.v2.ntp.ntp040.model.Ntp040AddressModel;
import jp.groupsession.v2.ntp.ntp040.model.Ntp040ContactModel;
import jp.groupsession.v2.ntp.ntp040.model.Ntp040DBCompanyBaseModel;
import jp.groupsession.v2.ntp.ntp040.model.Ntp040DBCompanyModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 日間画面で使用するDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp030Dao extends AbstractDao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp030Dao.class);

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con DBコネクション
     */
    public Ntp030Dao(Connection con) {
        super(con);
    }

    /**
     * <br>[機  能] 会社情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param acoSid ACO_SID
     * @return 会社情報
     * @throws SQLException SQL実行例外
     */
    public Ntp040DBCompanyModel getCompanyData(int acoSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Ntp040DBCompanyModel bean = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ACO_CODE,");
            sql.addSql("   ACO_NAME,");
            sql.addSql("   ACO_NAME_KN,");
            sql.addSql("   ACO_SINI,");
            sql.addSql("   ACO_URL,");
            sql.addSql("   ACO_BIKO,");
            sql.addSql("   ACO_AUID,");
            sql.addSql("   ACO_ADATE,");
            sql.addSql("   ACO_EUID,");
            sql.addSql("   ACO_EDATE");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY");
            sql.addSql(" where ");
            sql.addSql("   ACO_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(acoSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bean = new Ntp040DBCompanyModel();
                bean.setAcoSid(rs.getInt("ACO_SID"));
                bean.setAcoCode(rs.getString("ACO_CODE"));
                bean.setAcoName(rs.getString("ACO_NAME"));
                bean.setAcoNameKn(rs.getString("ACO_NAME_KN"));
                bean.setAcoSini(rs.getString("ACO_SINI"));
                bean.setAcoUrl(rs.getString("ACO_URL"));
                bean.setAcoBiko(rs.getString("ACO_BIKO"));
                bean.setAcoAuid(rs.getInt("ACO_AUID"));
                bean.setAcoAdate(UDate.getInstanceTimestamp(rs.getTimestamp("ACO_ADATE")));
                bean.setAcoEuid(rs.getInt("ACO_EUID"));
                bean.setAcoEdate(UDate.getInstanceTimestamp(rs.getTimestamp("ACO_EDATE")));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return bean;
    }

    /**
     * <br>[機  能] 会社拠点情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param abaSid ABA_SID
     * @return 会社拠点情報
     * @throws SQLException SQL実行例外
     */
    public Ntp040DBCompanyBaseModel getCompanyBaseData(int abaSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Ntp040DBCompanyBaseModel bean = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   ABA_SID,");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ABA_TYPE,");
            sql.addSql("   ABA_NAME,");
            sql.addSql("   ABA_POSTNO1,");
            sql.addSql("   ABA_POSTNO2,");
            sql.addSql("   TDF_SID,");
            sql.addSql("   ABA_ADDR1,");
            sql.addSql("   ABA_ADDR2,");
            sql.addSql("   ABA_BIKO,");
            sql.addSql("   ABA_AUID,");
            sql.addSql("   ABA_ADATE,");
            sql.addSql("   ABA_EUID,");
            sql.addSql("   ABA_EDATE");
            sql.addSql(" from");
            sql.addSql("   ADR_COMPANY_BASE");
            sql.addSql(" where ");
            sql.addSql("   ABA_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(abaSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bean = new Ntp040DBCompanyBaseModel();
                bean.setAbaSid(rs.getInt("ABA_SID"));
                bean.setAcoSid(rs.getInt("ACO_SID"));
                bean.setAbaType(rs.getInt("ABA_TYPE"));
                bean.setAbaName(rs.getString("ABA_NAME"));
                bean.setAbaPostno1(rs.getString("ABA_POSTNO1"));
                bean.setAbaPostno2(rs.getString("ABA_POSTNO2"));
                bean.setTdfSid(rs.getInt("TDF_SID"));
                bean.setAbaAddr1(rs.getString("ABA_ADDR1"));
                bean.setAbaAddr2(rs.getString("ABA_ADDR2"));
                bean.setAbaBiko(rs.getString("ABA_BIKO"));
                bean.setAbaAuid(rs.getInt("ABA_AUID"));
                bean.setAbaAdate(UDate.getInstanceTimestamp(rs.getTimestamp("ABA_ADATE")));
                bean.setAbaEuid(rs.getInt("ABA_EUID"));
                bean.setAbaEdate(UDate.getInstanceTimestamp(rs.getTimestamp("ABA_EDATE")));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }
        return bean;
    }

    /**
     * <br>[機  能] アドレス帳情報一覧を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param adrSidList アドレス帳情報SID
     * @param userSid ユーザSID
     * @return アドレス帳の名称一覧
     * @throws SQLException SQL実行時例外
     */
    public List<Ntp040AddressModel> getAddressList(Connection con, String[] adrSidList, int userSid)
    throws SQLException {

        if (adrSidList == null || adrSidList.length == 0) {
            return new ArrayList<Ntp040AddressModel>();
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Ntp040AddressModel> adrNameList = new ArrayList<Ntp040AddressModel>();

        try {
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" select");
            sql.addSql("   ADR_SID,");
            sql.addSql("   ADR_SEI,");
            sql.addSql("   ADR_MEI,");
            sql.addSql("   ACO_SID,");
            sql.addSql("   ABA_SID");
            sql.addSql(" from");
            sql.addSql("   ADR_ADDRESS");

            //閲覧権限
            sql.addSql(" where");
            sql.addSql("   (");
            sql.addSql("     ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
            sql.addSql("   or");
            sql.addSql("     (");
            sql.addSql("       ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
            sql.addSql("     and");
            sql.addSql("       ADR_ADDRESS.ADR_SID in (");
            sql.addSql("         select ADR_SID from ADR_PERSONCHARGE");
            sql.addSql("         where USR_SID = ?");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   or");
            sql.addSql("     (");
            sql.addSql("       ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
            sql.addSql("     and");
            sql.addSql("       ADR_ADDRESS.ADR_SID in (");
            sql.addSql("         select ADR_SID from ADR_PERMIT_VIEW");
            sql.addSql("         where");
            sql.addSql("           GRP_SID in (");
            sql.addSql("             select GRP_SID from CMN_BELONGM");
            sql.addSql("             where USR_SID = ?");
            sql.addSql("           )");
            sql.addSql("       )");
            sql.addSql("     )");
            sql.addSql("   or");
            sql.addSql("     (");
            sql.addSql("       ADR_ADDRESS.ADR_PERMIT_VIEW = ?");
            sql.addSql("     and");
            sql.addSql("       ADR_ADDRESS.ADR_SID in (");
            sql.addSql("         select ADR_SID from ADR_PERMIT_VIEW");
            sql.addSql("         where USR_SID = ?");
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

            //アドレス帳SID
            sql.addSql(" and");
            sql.addSql("   ADR_ADDRESS.ADR_SID in (");

            sql.addSql("     ?");
            sql.addIntValue(Integer.parseInt(adrSidList[0]));

            for (int index = 1; index < adrSidList.length; index++) {
                sql.addSql("     ,?");
                sql.addIntValue(Integer.parseInt(adrSidList[index]));
            }

            sql.addSql("   )");

            sql.addSql(" order by");
            sql.addSql("   ADR_SEI_KN,");
            sql.addSql("   ADR_MEI_KN");

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Ntp040AddressModel model = new Ntp040AddressModel();
                model.setAdrSid(rs.getInt("ADR_SID"));
                model.setAdrName(rs.getString("ADR_SEI") + " " + rs.getString("ADR_MEI"));
                model.setCompanySid(rs.getInt("ACO_SID"));
                model.setCompanyBaseSid(rs.getInt("ABA_SID"));
                adrNameList.add(model);
            }

        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return adrNameList;
    }

    /**
     * <br>[機  能] 指定したスケジュールのコンタクト履歴情報を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param scdSid スケジュールSID
     * @throws SQLException SQL実行時例外
     */
    public void deleteScheduleContact(Connection con, int scdSid)
    throws SQLException {

        PreparedStatement pstmt = null;

        try {
            SqlBuffer sql = new SqlBuffer();

            sql.addSql(" delete");
            sql.addSql(" from");
            sql.addSql("   ADR_CONTACT");
            sql.addSql(" where");
            sql.addSql("   ADC_SID in (");
            sql.addSql("     select ADC_SID from SCH_ADDRESS");
            sql.addSql("     where");
            sql.addSql("       SCD_SID = ?");
            sql.addSql("     and");
            sql.addSql("       ADC_SID not in (");
            sql.addSql("         select ADC_SID from SCH_ADDRESS");
            sql.addSql("         where SCD_SID <> ?");
            sql.addSql("       )");
            sql.addSql("   )");

            sql.addIntValue(scdSid);
            sql.addIntValue(scdSid);

            log__.info(sql.toLogString());
            pstmt = con.prepareStatement(sql.toSqlString());
            sql.setParameter(pstmt);
            pstmt.executeUpdate();

        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }


    /**
     * <br>[機  能] コンタクト履歴を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param bean コンタクト履歴情報
     * @throws SQLException SQL実行時例外
     */
    public void insertContact(Ntp040ContactModel bean) throws SQLException {

        PreparedStatement pstmt = null;
        Connection con = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" insert ");
            sql.addSql(" into ");
            sql.addSql(" ADR_CONTACT(");
            sql.addSql("   ADC_SID,");
            sql.addSql("   ADR_SID,");
            sql.addSql("   ADC_TITLE,");
            sql.addSql("   ADC_TYPE,");
            sql.addSql("   ADC_CTTIME,");
            sql.addSql("   ADC_CTTIME_TO,");
            sql.addSql("   PRJ_SID,");
            sql.addSql("   ADC_BIKO,");
            sql.addSql("   ADC_AUID,");
            sql.addSql("   ADC_ADATE,");
            sql.addSql("   ADC_EUID,");
            sql.addSql("   ADC_EDATE,");
            sql.addSql("   ADC_GRP_SID");
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
            sql.addSql("   ?");
            sql.addSql(" )");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(bean.getAdcSid());
            sql.addIntValue(bean.getAdrSid());
            sql.addStrValue(bean.getAdcTitle());
            sql.addIntValue(bean.getAdcType());
            sql.addDateValue(bean.getAdcCttime());
            sql.addDateValue(bean.getAdcCttimeTo());
            sql.addIntValue(bean.getPrjSid());
            sql.addStrValue(bean.getAdcBiko());
            sql.addIntValue(bean.getAdcAuid());
            sql.addDateValue(bean.getAdcAdate());
            sql.addIntValue(bean.getAdcEuid());
            sql.addDateValue(bean.getAdcEdate());
            sql.addIntValue(bean.getAdcGrpSid());
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
     * <p>Select NTP_COMMENT
     * @param reqMdl リクエストモデル
     * @param nipSid NIP_SID
     * @return NTP_COMMENTModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<Ntp030CommentModel> getNpcList(
            RequestModel reqMdl, int nipSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        NtpCommentModel npcMdl = null;
        CmnUsrmInfModel uInfMdl = null;
        Ntp030CommentModel ntpCmtMdl = null;
        ArrayList<Ntp030CommentModel> ret = new ArrayList<Ntp030CommentModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   NPC_SID,");
            sql.addSql("   NIP_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   NPC_COMMENT,");
            sql.addSql("   NPC_VIEW_KBN,");
            sql.addSql("   NPC_AUID,");
            sql.addSql("   NPC_ADATE,");
            sql.addSql("   NPC_EUID,");
            sql.addSql("   NPC_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_COMMENT");
            sql.addSql(" where ");
            sql.addSql("   NIP_SID=?");
            sql.addSql(" order by NPC_EDATE ASC");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(nipSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            //ユーザ情報格納用MAP
            Map<Integer, CmnUsrmInfModel> uInfMap
                         = new HashMap<Integer, CmnUsrmInfModel>();

            //日報情報取得
            NtpDataDao ntpdao = new NtpDataDao(con);
            NtpDataModel ntpDataMdl = new NtpDataModel();
            ntpDataMdl = ntpdao.select(nipSid);
            Ntp010Biz ntp010biz = new Ntp010Biz(con, reqMdl);
            int authFlg = 0;
            if (ntp010biz.isAddEditOk(
                    ntpDataMdl.getUsrSid(), con) == 0) {
                authFlg = 1;
            }
            //セッション情報を取得
            HttpSession session = reqMdl.getSession();
            BaseUserModel usModel =
                (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

            while (rs.next()) {

                int authkbn = authFlg;

                //コメント情報を取得
                npcMdl = new NtpCommentModel();
                npcMdl = __getNtpCommentFromRs(rs);

                //ユーザ情報を取得
                CmnUsrmInfDao dao = new CmnUsrmInfDao(con);
                uInfMdl = new CmnUsrmInfModel();

                if (uInfMap.get(npcMdl.getUsrSid()) != null) {
                    uInfMdl = uInfMap.get(npcMdl.getUsrSid());
                } else {
                    uInfMdl.setUsrSid(NullDefault
                            .getInt(String.valueOf(npcMdl.getUsrSid()), -1));
                    uInfMdl = dao.select(uInfMdl);
                    uInfMap.put(npcMdl.getUsrSid(), uInfMdl);
                }

                //データ形成
                ntpCmtMdl = new Ntp030CommentModel();
                ntpCmtMdl.setNtp030CommentMdl(npcMdl);
                ntpCmtMdl.setNtp030UsrInfMdl(uInfMdl);

                //表示用日付
                ntpCmtMdl.setNtp030CommentDate(
                    UDateUtil.getYymdJ(npcMdl.getNpcAdate(), reqMdl)
                    + "("
                    + npcMdl.getNpcAdate().getStrWeekJ(reqMdl)
                    + ")"
                    + " "
                    + UDateUtil.getSeparateHMJ(npcMdl.getNpcAdate(), reqMdl));

                if (authkbn == 0) {
                    if (npcMdl.getUsrSid() == usModel.getUsrsid()) {
                        authkbn = 1;
                    }
                }

                //削除フラグ
                ntpCmtMdl.setNtp030CommentDelFlg(authkbn);

                ret.add(ntpCmtMdl);
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
     * <p>指定したユーザの一番新しい報告日付を取得する
     * @param usrSid USR_SID
     * @return NIP_DATE
     * @throws SQLException SQL実行例外
     */
    public UDate getMaxNtpDate(int usrSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        UDate ret = null;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   MAX(NIP_DATE) NIP_DATE");
            sql.addSql(" from");
            sql.addSql("   NTP_DATA");
            sql.addSql(" where ");
            sql.addSql("   USR_SID=?");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(usrSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ret = UDate.getInstanceTimestamp(rs.getTimestamp("NIP_DATE"));
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
     * <p>表示用コメントリスト取得
     * @param reqMdl リクエストモデル
     * @param nipSid NIP_SID
     * @return NTP_COMMENTModel
     * @throws SQLException SQL実行例外
     */
    public ArrayList<Ntp030DspCommentModel> getDspNpcList(
            RequestModel reqMdl, int nipSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        NtpCommentModel npcMdl = null;
        CmnUsrmInfModel uInfMdl = null;
        Ntp030DspCommentModel ntpDspCmtMdl = null;
        ArrayList<Ntp030DspCommentModel> ret = new ArrayList<Ntp030DspCommentModel>();
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select");
            sql.addSql("   NPC_SID,");
            sql.addSql("   NIP_SID,");
            sql.addSql("   USR_SID,");
            sql.addSql("   NPC_COMMENT,");
            sql.addSql("   NPC_VIEW_KBN,");
            sql.addSql("   NPC_AUID,");
            sql.addSql("   NPC_ADATE,");
            sql.addSql("   NPC_EUID,");
            sql.addSql("   NPC_EDATE");
            sql.addSql(" from");
            sql.addSql("   NTP_COMMENT");
            sql.addSql(" where ");
            sql.addSql("   NIP_SID=?");
            sql.addSql(" order by NPC_EDATE ASC");

            pstmt = con.prepareStatement(sql.toSqlString());
            sql.addIntValue(nipSid);

            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            //ユーザ情報格納用MAP
            Map<Integer, CmnUsrmInfModel> uInfMap
                         = new HashMap<Integer, CmnUsrmInfModel>();

            //日報情報取得
            NtpDataDao ntpdao = new NtpDataDao(con);
            NtpDataModel ntpDataMdl = new NtpDataModel();
            ntpDataMdl = ntpdao.select(nipSid);
            Ntp010Biz ntp010biz = new Ntp010Biz(con, reqMdl);
            int authFlg = 0;
            if (ntp010biz.isAddEditOk(
                    ntpDataMdl.getUsrSid(), con) == 0) {
                authFlg = 1;
            }
            //セッション情報を取得
            HttpSession session = reqMdl.getSession();
            BaseUserModel usModel =
                (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

            while (rs.next()) {

                int authkbn = authFlg;

                //コメント情報を取得
                npcMdl = new NtpCommentModel();
                npcMdl = __getNtpCommentFromRs(rs);

                //ユーザ情報を取得
                CmnUsrmInfDao dao = new CmnUsrmInfDao(con);
                uInfMdl = new CmnUsrmInfModel();

                if (uInfMap.get(npcMdl.getUsrSid()) != null) {
                    uInfMdl = uInfMap.get(npcMdl.getUsrSid());
                } else {
                    uInfMdl.setUsrSid(NullDefault
                            .getInt(String.valueOf(npcMdl.getUsrSid()), -1));
                    uInfMdl = dao.select(uInfMdl);
                    uInfMap.put(npcMdl.getUsrSid(), uInfMdl);
                }

                ntpDspCmtMdl = new Ntp030DspCommentModel();
                ntpDspCmtMdl.setCommentSid(String.valueOf(npcMdl.getNpcSid()));
                ntpDspCmtMdl.setCommentUserName(
                        uInfMdl.getUsiSei() + "&nbsp;" + uInfMdl.getUsiMei());
                ntpDspCmtMdl.setCommentUserBinSid(String.valueOf(uInfMdl.getBinSid()));
                ntpDspCmtMdl.setCommentUsiPictKf(String.valueOf(uInfMdl.getUsiPictKf()));
                ntpDspCmtMdl.setCommentDate(
                    UDateUtil.getYymdJ(npcMdl.getNpcAdate(), reqMdl)
                    + "("
                    + npcMdl.getNpcAdate().getStrWeekJ(reqMdl)
                    + ")"
                    + " "
                    + UDateUtil.getSeparateHMJ(npcMdl.getNpcAdate(), reqMdl));
                ntpDspCmtMdl.setCommentValue(npcMdl.getNpcComment());

                if (authkbn == 0) {
                    if (npcMdl.getUsrSid() == usModel.getUsrsid()) {
                        authkbn = 1;
                    }
                }

                //削除フラグ
                ntpDspCmtMdl.setCommentDelKbn(authkbn);

                ret.add(ntpDspCmtMdl);
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
     * <p>Create NTP_COMMENT Data Bindding JavaBean From ResultSet
     * @param rs ResultSet
     * @return created NtpCommentModel
     * @throws SQLException SQL実行例外
     */
    private NtpCommentModel __getNtpCommentFromRs(ResultSet rs) throws SQLException {
        NtpCommentModel bean = new NtpCommentModel();
        bean.setNpcSid(rs.getInt("NPC_SID"));
        bean.setNipSid(rs.getInt("NIP_SID"));
        bean.setUsrSid(rs.getInt("USR_SID"));
        String dspCommentStr = StringUtilHtml.transToHTmlForTextArea(rs.getString("NPC_COMMENT"));
        bean.setNpcComment(StringUtil.transToLink(StringUtilHtml.returntoBR(dspCommentStr),
                StringUtil.OTHER_WIN, true));
        bean.setNpcViewKbn(rs.getInt("NPC_VIEW_KBN"));
        bean.setNpcAuid(rs.getInt("NPC_AUID"));
        bean.setNpcAdate(UDate.getInstanceTimestamp(rs.getTimestamp("NPC_ADATE")));
        bean.setNpcEuid(rs.getInt("NPC_EUID"));
        bean.setNpcEdate(UDate.getInstanceTimestamp(rs.getTimestamp("NPC_EDATE")));
        return bean;
    }
}
