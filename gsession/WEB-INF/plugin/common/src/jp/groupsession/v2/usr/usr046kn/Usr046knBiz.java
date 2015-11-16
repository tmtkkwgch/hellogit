package jp.groupsession.v2.usr.usr046kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrCategoryDao;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrDao;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrCategoryModel;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrModel;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] ユーザ情報 ラベル登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Usr046knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Usr046knBiz.class);

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータモデル
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Usr046knParamModel paramMdl) throws SQLException {
        //カテゴリ名取得
        CmnLabelUsrCategoryDao catDao = new CmnLabelUsrCategoryDao(con);
        CmnLabelUsrCategoryModel model = catDao.select(paramMdl.getUsr046CatSid());
        String catName;
        if (model != null) {
            catName = model.getLucName();
            paramMdl.setUsr046knDelCatName(model.getLucName());
        } else {
            catName = paramMdl.getUsr046knDelCatName();
        }
        //カテゴリ名をセット
        paramMdl.setUsr046knCatName(catName);

        //備考(表示用)をセット
        paramMdl.setBikou(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getUsr046bikou()), ""));

    }
    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doAddEdit(
        Usr046knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        int procMode = paramMdl.getUsr044ProcMode();
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
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doInsert(
        Usr046knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //ラベルSID採番
            int labSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_USER,
                                                       GSConstUser.SBNSID_SUB_LABEL,
                                                       userSid);
            //登録用Model作成
            CmnLabelUsrModel cpMdl = __getUpdateModel(con, labSid, paramMdl, userSid);

            //insert
            CmnLabelUsrDao cpDao = new CmnLabelUsrDao(con);
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
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doUpdate(Usr046knParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //ラベルSID
            int editLabSid = paramMdl.getLabelEditSid();

            //登録用Model作成
            CmnLabelUsrModel mdl = __getUpdateModel(con, editLabSid, paramMdl, userSid);

            //update
            CmnLabelUsrDao dao = new CmnLabelUsrDao(con);

            //カテゴリ間の移動があるか
            if (mdl.getLucSid() == paramMdl.getUsr043EditSid()) {
                dao.update(mdl);
            } else {
                dao.updateCatMove(mdl);
            }
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
     * @param labSid ラベルSID
     * @param paramMdl パラメータモデル
     * @param userSid ログインユーザSID
     * @return AdrLabelModel 登録・更新用Model
     * @throws SQLException SQL実行例外
     */
    private CmnLabelUsrModel __getUpdateModel(Connection con,
                                                   int labSid,
                                                   Usr046knParamModel paramMdl,
                                                   int userSid) throws SQLException {

        UDate now = new UDate();

        CmnLabelUsrModel mdl = new CmnLabelUsrModel();
        //カテゴリSID
        mdl.setLucSid(paramMdl.getUsr046CatSid());
        //ラベルSID
        mdl.setLabSid(labSid);
        //ラベル名
        mdl.setLabName(NullDefault.getString(paramMdl.getUsr046LabelName(), ""));
        //備考
        mdl.setLabBiko(NullDefault.getString(paramMdl.getUsr046bikou(), ""));
        //表示順
        CmnLabelUsrDao dao = new CmnLabelUsrDao(con);
        mdl.setLabSort(dao.getSortMax(paramMdl.getUsr046CatSid()) + 1);

        mdl.setLabAuid(userSid);
        mdl.setLabAdate(now);
        mdl.setLabEuid(userSid);
        mdl.setLabEdate(now);

        return mdl;
    }

}
