package jp.groupsession.v2.usr.usr045;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrCategoryDao;
import jp.groupsession.v2.cmn.dao.base.CmnLabelUsrDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrCategoryModel;
import jp.groupsession.v2.cmn.model.base.CmnLabelUsrModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] ユーザ情報 カテゴリ登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Usr045Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Usr045Biz.class);
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Usr045Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }
    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Usr045ParamModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Usr045ParamModel paramMdl) throws SQLException {

        if (paramMdl.getUsr043ProcMode() == GSConstUser.PROCMODE_ADD) {
            return;
        }
        //編集の場合、カテゴリ情報取得
        int editSid = paramMdl.getUsr043EditSid();
        CmnLabelUsrCategoryDao dao = new CmnLabelUsrCategoryDao(con);
        CmnLabelUsrCategoryModel model = dao.select(editSid);
        if (model == null) {
            return;
        }
        //カテゴリ情報を画面にセット
        paramMdl.setUsr045CategoryName(model.getLucName());
        paramMdl.setUsr045bikou(model.getLucBiko());
    }


    /**
     * <br>[機  能] カテゴリSIDからカテゴリを取得し、メッセージを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid ラベルSID
     * @param msgRes MessageResources
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public String getDeletePosMsg(Connection con, int catSid, MessageResources msgRes)
    throws SQLException {

        String msg = "";

        //カテゴリ名取得
        CmnLabelUsrCategoryDao catDao = new CmnLabelUsrCategoryDao(con);
        CmnLabelUsrCategoryModel catMdl = catDao.select(catSid);

        String catName = "";
        if (catMdl != null) {
            catName = NullDefault.getString(
                    StringUtilHtml.transToHTmlPlusAmparsant(catMdl.getLucName()), "");
        }
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //カテゴリ
        String textCategory = gsMsg.getMessage("cmn.category");
        msg = msgRes.getMessage("sakujo.kakunin.list", textCategory, catName);

        return msg;
    }

    /**
     * <br>[機  能] カテゴリSIDからカテゴリ名を取得し、ラベル一覧を追加したメッセージを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid ラベルSID
     * @param msgRes MessageResources
     * @param delMsg html表示用ラベル一覧
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public String getDeleteLabAndCatMsg(Connection con,
                                        int catSid,
                                        MessageResources msgRes,
                                        String delMsg)
                                        throws SQLException {

        String msg = "";

        //カテゴリ名取得
        CmnLabelUsrCategoryDao catDao = new CmnLabelUsrCategoryDao(con);
        CmnLabelUsrCategoryModel catMdl = catDao.select(catSid);

        String catName = "";
        if (catMdl != null) {
            catName = NullDefault.getString(
                    StringUtilHtml.transToHTmlPlusAmparsant(catMdl.getLucName()), "");
        }

        msg = msgRes.getMessage("error.usercategory.label", catName, delMsg);

        return msg;
    }

    /**
     * <br>[機  能] カテゴリSIDからラベル情報一覧を取得し、一覧のlistを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid カテゴリSID
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public ArrayList<String> getDeleteLabList(Connection con, int catSid)
    throws SQLException {

        //ラベル一覧取得
        CmnLabelUsrDao dao = new CmnLabelUsrDao(con);
        ArrayList<String> labNameList = new ArrayList<String>();
        ArrayList<CmnLabelUsrModel> modelList = dao.select(catSid);
        for (CmnLabelUsrModel model : modelList) {
            labNameList.add(model.getLabName());
        }

        return labNameList;
    }

    /**
     * <br>[機  能] カテゴリSIDからラベル情報一覧を取得し、
     * <br>[機  能] ラベルがユーザ情報に付加されているか判断
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid カテゴリSID
     * @return boolean true=ラベル付加あり false=ラベル付加なし
     * @throws SQLException SQL実行例外
     */
    public ArrayList<Integer> getBelongLabList(Connection con, int catSid)
    throws SQLException {

        //カテゴリ内ラベルSID一覧取得
        CmnLabelUsrDao dao = new CmnLabelUsrDao(con);
        ArrayList<Integer> labSidList = new ArrayList<Integer>();
        ArrayList<CmnLabelUsrModel> modelList = dao.select(catSid);
        for (CmnLabelUsrModel model : modelList) {
            labSidList.add(model.getLabSid());
        }

        return labSidList;
    }

    /**
     * <br>[機  能] カテゴリを削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Usr045ParamModel
     * @throws SQLException SQL実行例外
     */
    public void deleteCat(Connection con, Usr045ParamModel paramMdl) throws SQLException {

        int editCatSid = paramMdl.getUsr043EditSid();
        int kbn = paramMdl.getCatKbn();
        boolean commitFlg = false;

        try {

            //ラベル情報を物理削除する
            CmnLabelUsrCategoryDao dao = new CmnLabelUsrCategoryDao(con);

            //
            if (kbn == GSConstUser.CATEGORY_EXIST_YES) {
                dao.deleteCatAndLab(editCatSid);
            } else {
                dao.delete(editCatSid);
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
}
