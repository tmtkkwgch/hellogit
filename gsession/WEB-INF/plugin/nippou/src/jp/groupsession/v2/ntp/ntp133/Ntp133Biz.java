package jp.groupsession.v2.ntp.ntp133;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.dao.NtpShohinCategoryDao;
import jp.groupsession.v2.ntp.dao.NtpShohinDao;
import jp.groupsession.v2.ntp.model.NtpShohinCategoryModel;
import jp.groupsession.v2.ntp.model.NtpShohinModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] 日報 商品カテゴリ登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp133Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp133Biz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Ntp133Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }
    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl フォーム
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Ntp133ParamModel paramMdl) throws SQLException {


        //編集の場合、カテゴリ情報取得
        int editSid = paramMdl.getNtp130EditSid();
        NtpShohinCategoryDao dao = new NtpShohinCategoryDao(con);
        NtpShohinCategoryModel model = dao.select(editSid);
        if (model == null) {
            return;
        }
        //カテゴリ情報を画面にセット
        paramMdl.setNtp133CategoryName(model.getNscName());
        paramMdl.setNtp133bikou(model.getNscBiko());
    }


    /**
     * <br>[機  能] カテゴリSIDからカテゴリを取得し、メッセージを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid 商品SID
     * @param msgRes MessageResources
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public String getDeletePosMsg(Connection con, int catSid, MessageResources msgRes)
    throws SQLException {

        String msg = "";
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //カテゴリ名取得
        NtpShohinCategoryDao catDao = new NtpShohinCategoryDao(con);
        NtpShohinCategoryModel catMdl = catDao.select(catSid);
        String catName = NullDefault.getString(catMdl.getNscName(), "");

        msg = msgRes.getMessage("sakujo.kakunin.list",
                gsMsg.getMessage("cmn.category"), StringUtilHtml.transToHTmlPlusAmparsant(catName));

        return msg;
    }

    /**
     * <br>[機  能] カテゴリSIDからカテゴリ名を取得し、商品一覧を追加したメッセージを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid 商品SID
     * @param msgRes MessageResources
     * @param delMsg html表示用商品一覧
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
        NtpShohinCategoryDao catDao = new NtpShohinCategoryDao(con);
        NtpShohinCategoryModel catMdl = catDao.select(catSid);
        String catName = NullDefault.getString(catMdl.getNscName(), "");

        msg = msgRes.getMessage("error.shohincategory.label",
                StringUtilHtml.transToHTmlPlusAmparsant(catName), delMsg);

        return msg;
    }

    /**
     * <br>[機  能] カテゴリSIDから商品情報一覧を取得し、一覧のlistを返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid カテゴリSID
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public ArrayList<String> getDeleteLabList(Connection con, int catSid)
    throws SQLException {

        //商品一覧取得
        NtpShohinDao dao = new NtpShohinDao(con);
        ArrayList<String> labNameList = new ArrayList<String>();
        List<NtpShohinModel> modelList = dao.getShohinInCategory(catSid);
        for (NtpShohinModel model : modelList) {
            labNameList.add(model.getNhnName());
        }

        return labNameList;
    }

    /**
     * <br>[機  能] カテゴリSIDから商品情報一覧を取得し、
     * <br>[機  能] 商品がアドレス帳に付加されているか判断
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid カテゴリSID
     * @return boolean true=商品付加あり false=商品付加なし
     * @throws SQLException SQL実行例外
    public ArrayList<Integer> getBelongLabList(Connection con, int catSid)
    throws SQLException {

        //カテゴリ内商品SID一覧取得
        AdrBelongLabelDao dao = new AdrBelongLabelDao(con);
        ArrayList<Integer> labSidList = new ArrayList<Integer>();
        ArrayList<NtpShohinModel> modelList = dao.select(catSid);
        for (CmnLabelUsrModel model : modelList) {
            labSidList.add(model.getLabSid());
        }

        return labSidList;
    }
     */

    /**
     * <br>[機  能] カテゴリを削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Ntp133ParamModel
     * @throws SQLException SQL実行例外
     */
    public void deleteCat(Connection con, Ntp133ParamModel paramMdl) throws SQLException {

        int editCatSid = paramMdl.getNtp130EditSid();
        int kbn = paramMdl.getCatKbn();
        boolean commitFlg = false;

        try {

            //カテゴリ情報を物理削除する
            NtpShohinCategoryDao dao = new NtpShohinCategoryDao(con);

            //カテゴリの削除
            dao.delete(editCatSid);

            if (kbn == GSConstNippou.CATEGORY_EXIST_YES) {
                //カテゴリ内に商品が存在する場合
                //削除されたカテゴリ内の商品をカテゴリ「未選択」へ移動
                moveLabelToNotCategory(con, editCatSid);
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
     * <br>[機  能] 削除されたカテゴリ内の商品をカテゴリ「未選択」へ移動する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param catSid カテゴリSID
     * @throws SQLException SQL実行例外
     */
    public void moveLabelToNotCategory(Connection con, int catSid)
    throws SQLException {

        NtpShohinDao dao = new NtpShohinDao(con);

        //カテゴリ「未選択」へ移動する商品データを取得
        List<NtpShohinModel> labelDataList = dao.getShohinInCategory(catSid);

        for (NtpShohinModel lblMdl : labelDataList) {
            dao.deleteCatAndShohin(lblMdl);
        }
    }
}
