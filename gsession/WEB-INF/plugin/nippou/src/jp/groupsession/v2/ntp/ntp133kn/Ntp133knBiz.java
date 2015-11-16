package jp.groupsession.v2.ntp.ntp133kn;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpShohinCategoryDao;
import jp.groupsession.v2.ntp.model.NtpShohinCategoryModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 商品カテゴリ登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp133knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp133knBiz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Ntp133knBiz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }
    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Ntp133knParamModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Ntp133knParamModel paramMdl) throws SQLException {

        //備考(表示用)をセット
        paramMdl.setBikou(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getNtp133bikou()), ""));

    }
    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp133knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doAddEdit(
        Ntp133knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        String procMode = paramMdl.getNtp130ProcMode();
        if (procMode.equals("add")) {
            //登録
            doInsert(paramMdl, con, cntCon, userSid);
        } else if (procMode.equals("edit")) {
            //更新
            doUpdate(paramMdl, con, userSid);
        }
    }

    /**
     * <br>[機  能] 登録処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp133knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doInsert(
        Ntp133knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //カテゴリSID採番
            int catSid = (int) cntCon.getSaibanNumber(GSConstNippou.SBNSID_NIPPOU,
                                                       GSConstNippou.SBNSID_SUB_SHOHIN_CATEGORY,
                                                       userSid);
            //登録用Model作成
            NtpShohinCategoryModel cpMdl = __getUpdateModel(con, catSid, paramMdl, userSid);

            //insert
            NtpShohinCategoryDao cpDao = new NtpShohinCategoryDao(con);
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
     * @param paramMdl Ntp133knParamModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doUpdate(Ntp133knParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //カテゴリSID
            int editCatSid = paramMdl.getNtp130EditSid();

            //登録用Model作成
            NtpShohinCategoryModel mdl = __getUpdateModel(con, editCatSid, paramMdl, userSid);

            //update
            NtpShohinCategoryDao dao = new NtpShohinCategoryDao(con);
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
     * @param paramMdl Ntp133knParamModel
     * @param userSid ログインユーザSID
     * @return NtpShohinCategoryModel 登録・更新用Model
     * @throws SQLException SQL実行例外
     */
    private NtpShohinCategoryModel __getUpdateModel(Connection con,
                                                   int catSid,
                                                   Ntp133knParamModel paramMdl,
                                                   int userSid) throws SQLException {

        UDate now = new UDate();

        NtpShohinCategoryModel mdl = new NtpShohinCategoryModel();
        //カテゴリSID
        mdl.setNscSid(catSid);
        //カテゴリ名
        mdl.setNscName(NullDefault.getString(paramMdl.getNtp133CategoryName(), ""));
        //備考
        mdl.setNscBiko(NullDefault.getString(paramMdl.getNtp133bikou(), ""));
        //表示順
        NtpShohinCategoryDao dao = new NtpShohinCategoryDao(con);
        mdl.setNscSort(dao.getSortMax() + 1);

        mdl.setNscAuid(userSid);
        mdl.setNscAdate(now);
        mdl.setNscEuid(userSid);
        mdl.setNscEdate(now);

        return mdl;
    }

}
