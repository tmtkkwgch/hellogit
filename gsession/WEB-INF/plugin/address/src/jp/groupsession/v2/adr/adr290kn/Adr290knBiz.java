package jp.groupsession.v2.adr.adr290kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrLabelCategoryDao;
import jp.groupsession.v2.adr.model.AdrLabelCategoryModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アドレス帳 カテゴリ登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr290knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr290knBiz.class);
    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr290knBiz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }
    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr290knParamModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Adr290knParamModel paramMdl) throws SQLException {

        //備考(表示用)をセット
        paramMdl.setBikou(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getAdr290bikou()), ""));

    }
    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr290knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doAddEdit(
        Adr290knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        int procMode = paramMdl.getAdr280ProcMode();
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
     * @param paramMdl Adr290knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doInsert(
        Adr290knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //カテゴリSID採番
            int catSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                       GSConstAddress.SBNSID_SUB_CATEGORY,
                                                       userSid);
            //登録用Model作成
            AdrLabelCategoryModel cpMdl = __getUpdateModel(con, catSid, paramMdl, userSid);

            //insert
            AdrLabelCategoryDao cpDao = new AdrLabelCategoryDao(con);
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
     * @param paramMdl Adr290knParamModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doUpdate(Adr290knParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //カテゴリSID
            int editCatSid = paramMdl.getAdr280EditSid();

            //登録用Model作成
            AdrLabelCategoryModel mdl = __getUpdateModel(con, editCatSid, paramMdl, userSid);

            //update
            AdrLabelCategoryDao dao = new AdrLabelCategoryDao(con);
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
     * @param catSid カテゴリSID
     * @param paramMdl Adr290knParamModel
     * @param userSid ログインユーザSID
     * @return AdrLabelCategoryModel 登録・更新用Model
     * @throws SQLException SQL実行例外
     */
    private AdrLabelCategoryModel __getUpdateModel(Connection con,
                                                   int catSid,
                                                   Adr290knParamModel paramMdl,
                                                   int userSid) throws SQLException {

        UDate now = new UDate();

        AdrLabelCategoryModel mdl = new AdrLabelCategoryModel();
        //カテゴリSID
        mdl.setAlcSid(catSid);
        //カテゴリ名
        mdl.setAlcName(NullDefault.getString(paramMdl.getAdr290CategoryName(), ""));
        //備考
        mdl.setAlcBiko(NullDefault.getString(paramMdl.getAdr290bikou(), ""));
        //表示順
        AdrLabelCategoryDao dao = new AdrLabelCategoryDao(con);
        mdl.setAlcSort(dao.getSortMax() + 1);

        mdl.setAlcAuid(userSid);
        mdl.setAlcAdate(now);
        mdl.setAlcEuid(userSid);
        mdl.setAlcEdate(now);

        return mdl;
    }

}
