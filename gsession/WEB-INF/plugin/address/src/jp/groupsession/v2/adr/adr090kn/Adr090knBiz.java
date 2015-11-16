package jp.groupsession.v2.adr.adr090kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrTypeindustryDao;
import jp.groupsession.v2.adr.model.AdrTypeindustryModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アドレス帳 業種登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr090knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr090knBiz.class);

    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr090knBiz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr090knParamModel
     */
    public void getInitData(Adr090knParamModel paramMdl) {

        //備考(表示用)をセット
        paramMdl.setBikou(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getAdr090bikou()), ""));
    }

    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr090knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doAddEdit(
        Adr090knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        int procMode = paramMdl.getAdr080ProcMode();
        if (procMode == GSConstAddress.PROCMODE_ADD) {
            //登録
            doInsert(paramMdl, con, cntCon, userSid);
        } else if (procMode == GSConstAddress.PROCMODE_EDIT) {
            //更新
            doUpdate(paramMdl, con, userSid);
        }
    }

    /**
     * <br>[機  能] 登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr090knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doInsert(
        Adr090knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //業種SID採番
            int atiSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                       GSConstAddress.SBNSID_SUB_INDUSTRY,
                                                       userSid);
            //登録用Model作成
            AdrTypeindustryModel cpMdl = __getUpdateModel(con, atiSid, paramMdl, userSid);

            //insert
            AdrTypeindustryDao cpDao = new AdrTypeindustryDao(con);
            cpDao.insert(cpMdl);
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
     * @param paramMdl Adr090knParamModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doUpdate(Adr090knParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //業種SID
            int editAtiSid = paramMdl.getAdr080EditAtiSid();

            //登録用Model作成
            AdrTypeindustryModel mdl = __getUpdateModel(con, editAtiSid, paramMdl, userSid);

            //update
            AdrTypeindustryDao dao = new AdrTypeindustryDao(con);
            dao.update(mdl);
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
     * @param con コネクション
     * @param atiSid 業種SID
     * @param paramMdl Adr090knParamModel
     * @param userSid ログインユーザSID
     * @return AdrTypeindustryModel 登録・更新用Model
     * @throws SQLException SQL実行例外
     */
    private AdrTypeindustryModel __getUpdateModel(Connection con,
                                                   int atiSid,
                                                   Adr090knParamModel paramMdl,
                                                   int userSid) throws SQLException {

        UDate now = new UDate();

        AdrTypeindustryModel mdl = new AdrTypeindustryModel();
        //業種SID
        mdl.setAtiSid(atiSid);
        //業種名
        mdl.setAtiName(NullDefault.getString(paramMdl.getAdr090atiName(), ""));
        //備考
        mdl.setAtiBiko(NullDefault.getString(paramMdl.getAdr090bikou(), ""));
        //表示順
        AdrTypeindustryDao dao = new AdrTypeindustryDao(con);
        mdl.setAtiSort(dao.getSortMax() + 1);

        mdl.setAtiAuid(userSid);
        mdl.setAtiAdate(now);
        mdl.setAtiEuid(userSid);
        mdl.setAtiEdate(now);

        return mdl;
    }
}
