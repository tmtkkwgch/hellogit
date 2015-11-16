package jp.groupsession.v2.adr.adr220kn;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrPositionDao;
import jp.groupsession.v2.adr.model.AdrPositionModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アドレス帳 役職登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr220knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr220knBiz.class);
    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr220knBiz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr220knParamModel
     */
    public void getInitData(Adr220knParamModel paramMdl) {

        //備考(表示用)をセット
        paramMdl.setBikou(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(
                        paramMdl.getAdr220bikou()), ""));
    }

    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl アクションフォーム
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doAddEdit(Adr220knParamModel paramMdl,
                           Connection con,
                           MlCountMtController cntCon,
                           int userSid) throws SQLException {

        int procMode = paramMdl.getAdr210ProcMode();
        if (procMode == GSConstAddress.PROCMODE_ADD) {
            //登録
            __doInsert(paramMdl, con, cntCon, userSid);
        } else if (procMode == GSConstAddress.PROCMODE_EDIT) {
            //更新
            __doUpdate(paramMdl, con, userSid);
        }
    }

    /**
     * <br>[機  能] 登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl アクションフォーム
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    private void __doInsert(Adr220knParamModel paramMdl,
                             Connection con,
                             MlCountMtController cntCon,
                             int userSid) throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //役職SID採番
            int posSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                       GSConstAddress.SBNSID_SUB_POSITION,
                                                       userSid);
            //登録用Model作成
            AdrPositionModel apMdl = __getUpdateModel(con, posSid, paramMdl, userSid);

            //insert
            AdrPositionDao apDao = new AdrPositionDao(con);
            apDao.insertNewYakusyoku(apMdl);
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * <br>[機  能] 更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl アクションフォーム
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    private void __doUpdate(Adr220knParamModel paramMdl, Connection con, int userSid)
        throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //業種SID
            int posSid = paramMdl.getAdr210EditPosSid();

            //更新用Model作成
            AdrPositionModel apMdl = __getUpdateModel(con, posSid, paramMdl, userSid);

            //update
            AdrPositionDao apDao = new AdrPositionDao(con);
            apDao.updateNameAndBiko(apMdl);
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * <br>[機  能] 登録・更新用Modelを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param posSid 役職SID
     * @param paramMdl Adr220knParamModel
     * @param userSid ログインユーザSID
     * @return AdrTypeindustryModel 登録・更新用Model
     * @throws SQLException SQL実行例外
     */
    private AdrPositionModel __getUpdateModel(Connection con,
                                               int posSid,
                                               Adr220knParamModel paramMdl,
                                               int userSid) throws SQLException {

        UDate now = new UDate();

        AdrPositionModel mdl = new AdrPositionModel();
        mdl.setApsSid(posSid);
        mdl.setApsName(NullDefault.getString(paramMdl.getAdr220yksName(), ""));
        mdl.setApsAuid(userSid);
        mdl.setApsAdate(now);
        mdl.setApsEuid(userSid);
        mdl.setApsEdate(now);
        mdl.setApsBiko(NullDefault.getString(paramMdl.getAdr220bikou(), ""));
        return mdl;
    }
}