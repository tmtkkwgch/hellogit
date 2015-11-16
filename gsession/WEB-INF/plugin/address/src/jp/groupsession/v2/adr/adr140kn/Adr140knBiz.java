package jp.groupsession.v2.adr.adr140kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrLabelCategoryDao;
import jp.groupsession.v2.adr.dao.AdrLabelDao;
import jp.groupsession.v2.adr.model.AdrLabelCategoryModel;
import jp.groupsession.v2.adr.model.AdrLabelModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アドレス帳 ラベル登録確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr140knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr140knBiz.class);
    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr140knBiz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr140knParamModel
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void getInitData(Adr140knParamModel paramMdl, Connection con) throws SQLException {

        //備考(表示用)をセット
        paramMdl.setBikou(NullDefault.getString(
                StringUtilHtml.transToHTmlPlusAmparsant(paramMdl.getAdr140bikou()), ""));

        //カテゴリ情報取得
        int catSid = paramMdl.getAdr140CatSid();
        AdrLabelCategoryDao catDao = new AdrLabelCategoryDao(con);
        AdrLabelCategoryModel catMdl = catDao.select(catSid);

        String catName;
        if (catMdl != null) {
            catName = NullDefault.getString(catMdl.getAlcName(), "");
            paramMdl.setAdr140knDelCatName(catName);
        } else {
            catName = paramMdl.getAdr140knDelCatName();
        }

        //カテゴリ名をセット
        paramMdl.setCatName(catName);
    }

    /**
     * <br>[機  能] 登録、または更新処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Adr140knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doAddEdit(
        Adr140knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        int procMode = paramMdl.getAdr130ProcMode();
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
     * @param paramMdl Adr140knParamModel
     * @param con コネクション
     * @param cntCon MlCountMtController
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doInsert(
        Adr140knParamModel paramMdl,
        Connection con,
        MlCountMtController cntCon,
        int userSid) throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //ラベルSID採番
            int albSid = (int) cntCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                                       GSConstAddress.SBNSID_SUB_LABEL,
                                                       userSid);
            //登録用Model作成
            AdrLabelModel cpMdl = __getUpdateModel(con, albSid, paramMdl, userSid);

            //insert
            AdrLabelDao cpDao = new AdrLabelDao(con);
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
     * @param paramMdl Adr140knParamModel
     * @param con コネクション
     * @param userSid ログインユーザSID
     * @throws SQLException SQL実行例外
     */
    public void doUpdate(Adr140knParamModel paramMdl, Connection con, int userSid)
    throws SQLException {

        boolean commitFlg = false;

        try {

            con.setAutoCommit(false);

            //ラベルSID
            int editAlbSid = paramMdl.getAdr130EditAlbSid();

            //登録用Model作成
            AdrLabelModel mdl = __getUpdateModel(con, editAlbSid, paramMdl, userSid);

            //update
            AdrLabelDao dao = new AdrLabelDao(con);
            //カテゴリ間の移動があるか
            if (paramMdl.getAdr280EditSid() == paramMdl.getAdr140CatSid()) {
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
     * @param albSid ラベルSID
     * @param paramMdl Adr140knParamModel
     * @param userSid ログインユーザSID
     * @return AdrLabelModel 登録・更新用Model
     * @throws SQLException SQL実行例外
     */
    private AdrLabelModel __getUpdateModel(Connection con,
                                                   int albSid,
                                                   Adr140knParamModel paramMdl,
                                                   int userSid) throws SQLException {

        UDate now = new UDate();

        AdrLabelModel mdl = new AdrLabelModel();
        //ラベルSID
        mdl.setAlbSid(albSid);
        //ラベル名
        mdl.setAlbName(NullDefault.getString(paramMdl.getAdr140albName(), ""));
        //備考
        mdl.setAlbBiko(NullDefault.getString(paramMdl.getAdr140bikou(), ""));
        //表示順
        AdrLabelDao dao = new AdrLabelDao(con);
        mdl.setAlbSort(dao.getSortMax(paramMdl.getAdr140CatSid()) + 1);
        //カテゴリSID
        mdl.setAlcSid(paramMdl.getAdr140CatSid());

        mdl.setAlbAuid(userSid);
        mdl.setAlbAdate(now);
        mdl.setAlbEuid(userSid);
        mdl.setAlbEdate(now);

        return mdl;
    }
}
