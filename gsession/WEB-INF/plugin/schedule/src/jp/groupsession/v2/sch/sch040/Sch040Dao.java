package jp.groupsession.v2.sch.sch040;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.dao.AbstractDao;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.co.sjts.util.jdbc.SqlBuffer;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.sch.sch040.model.Sch040AddressModel;
import jp.groupsession.v2.sch.sch040.model.Sch040ContactModel;
import jp.groupsession.v2.sch.sch040.model.Sch040DBCompanyBaseModel;
import jp.groupsession.v2.sch.sch040.model.Sch040DBCompanyModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] スケジュール登録に必要な情報を取得するためのDAOクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch040Dao extends AbstractDao {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch040Dao.class);

    /**
     * <p>
     * デフォルトコンストラクタ
     */
    public Sch040Dao() {
    }

    /**
     * <p>
     * デフォルトコンストラクタ
     *
     * @param con
     *            DBコネクション
     */
    public Sch040Dao(Connection con) {
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
    public Sch040DBCompanyModel getCompanyData(int acoSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Sch040DBCompanyModel bean = null;
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
                bean = new Sch040DBCompanyModel();
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
    public Sch040DBCompanyBaseModel getCompanyBaseData(int abaSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        Sch040DBCompanyBaseModel bean = null;
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
                bean = new Sch040DBCompanyBaseModel();
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
    public List<Sch040AddressModel> getAddressList(Connection con, String[] adrSidList, int userSid)
    throws SQLException {

        if (adrSidList == null || adrSidList.length == 0) {
            return new ArrayList<Sch040AddressModel>();
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Sch040AddressModel> adrNameList = new ArrayList<Sch040AddressModel>();

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
                Sch040AddressModel model = new Sch040AddressModel();
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
    public void insertContact(Sch040ContactModel bean) throws SQLException {

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
     * <br>[機  能] 指定したスケジュール拡張情報に関するコンタクト履歴が登録されているかを判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param sceSid スケジュール拡張SID
     * @return true:登録 false:未登録
     * @throws SQLException SQL実行時例外
     */
    public boolean isExistAdrContact(int sceSid) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        boolean result = false;
        con = getCon();

        try {
            //SQL文
            SqlBuffer sql = new SqlBuffer();
            sql.addSql(" select ");
            sql.addSql("   count(SCH_ADDRESS.*) as CNT");
            sql.addSql(" from ");
            sql.addSql("   SCH_DATA,");
            sql.addSql("   SCH_ADDRESS,");
            sql.addSql("   SCH_EXADDRESS");
            sql.addSql(" where ");
            sql.addSql("   SCH_EXADDRESS.SCE_SID = ?");
            sql.addSql(" and ");
            sql.addSql("   SCH_ADDRESS.SCD_SID = SCH_DATA.SCD_SID");
            sql.addSql(" and ");
            sql.addSql("   SCH_EXADDRESS.SCE_SID = SCH_DATA.SCE_SID");
            sql.addSql(" and ");
            sql.addSql("   SCH_ADDRESS.ADC_SID > 0");
            sql.addIntValue(sceSid);

            pstmt = con.prepareStatement(sql.toSqlString());
            log__.info(sql.toLogString());
            sql.setParameter(pstmt);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("CNT") > 0;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
        }

        return result;
    }

}
