package jp.groupsession.v2.usr.usr045kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrCategoryDao;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrCategoryModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ユーザ情報 カテゴリ登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Usr045knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Usr045knBiz.class);

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Usr045knParamModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Usr045knParamModel paramMdl) throws SQLException {

        //備考(表示用)をセット
        paramMdl.setBikou(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getUsr045bikou()), ""));

    }
    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Usr045knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doAddEdit(
        Usr045knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        int procMode = paramMdl.getUsr043ProcMode();
        if (procMode == GSConstUser.PROCMODE_ADD) {
            //登録
            doInsert(paramMdl, con, cntCon, userSid);
        } else if (procMode == GSConstUser.PROCMODE_EDIT) {
            //更新
            doUpdate(paramMdl, con, userSid);
        }
    }

    /**
     * <br>[機  能] 登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Usr045knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doInsert(
        Usr045knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //カテゴリSID採番
            int catSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_USER,
                                                       GSConstUser.SBNSID_SUB_CATEGORY,
                                                       userSid);
            //登録用Model作成
            CmnLabelUsrCategoryModel cpMdl = __getUpdateModel(con, catSid, paramMdl, userSid);

            //insert
            CmnLabelUsrCategoryDao cpDao = new CmnLabelUsrCategoryDao(con);
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
     * @param paramMdl Usr045knParamModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doUpdate(Usr045knParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //カテゴリSID
            int editCatSid = paramMdl.getUsr043EditSid();

            //登録用Model作成
            CmnLabelUsrCategoryModel mdl = __getUpdateModel(con, editCatSid, paramMdl, userSid);

            //update
            CmnLabelUsrCategoryDao dao = new CmnLabelUsrCategoryDao(con);
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
     * @param paramMdl Usr045knParamModel
     * @param userSid ログインユーザSID
     * @return AdrLabelModel 登録・更新用Model
     * @throws SQLException SQL実行例外
     */
    private CmnLabelUsrCategoryModel __getUpdateModel(Connection con,
                                                   int catSid,
                                                   Usr045knParamModel paramMdl,
                                                   int userSid) throws SQLException {

        UDate now = new UDate();

        CmnLabelUsrCategoryModel mdl = new CmnLabelUsrCategoryModel();
        //カテゴリSID
        mdl.setLucSid(catSid);
        //カテゴリ名
        mdl.setLucName(NullDefault.getString(paramMdl.getUsr045CategoryName(), ""));
        //備考
        mdl.setLucBiko(NullDefault.getString(paramMdl.getUsr045bikou(), ""));
        //表示順
        CmnLabelUsrCategoryDao dao = new CmnLabelUsrCategoryDao(con);
        mdl.setLucSort(dao.getSortMax() + 1);

        mdl.setLucAuid(userSid);
        mdl.setLucAdate(now);
        mdl.setLucEuid(userSid);
        mdl.setLucEdate(now);

        return mdl;
    }

}
