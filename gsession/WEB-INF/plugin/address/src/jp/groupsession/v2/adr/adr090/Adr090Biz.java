package jp.groupsession.v2.adr.adr090;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrBelongIndustryDao;
import jp.groupsession.v2.adr.dao.AdrTypeindustryDao;
import jp.groupsession.v2.adr.model.AdrTypeindustryModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] アドレス帳 業種登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr090Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr090Biz.class);

    /** リクエスト */
    protected RequestModel reqMdl_ = null;

//    /**
//     * <br>[機  能] デフォルトコンストラクタ
//     * <br>[解  説]
//     * <br>[備  考]
//     */
//    public Adr090Biz() {
//    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr090Biz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr090ParamModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(
            Connection con, Adr090ParamModel paramMdl) throws SQLException {

        if (paramMdl.getAdr080ProcMode() == GSConstAddress.PROCMODE_ADD) {
            return;
        }

        //編集の場合、業種情報取得
        int editAtiSid = paramMdl.getAdr080EditAtiSid();
        AdrTypeindustryDao atiDao = new AdrTypeindustryDao(con);
        AdrTypeindustryModel atiMdl = atiDao.select(editAtiSid);
        if (atiMdl == null) {
            return;
        }

        //業種情報を画面にセット
        paramMdl.setAdr090atiName(atiMdl.getAtiName());
        paramMdl.setAdr090bikou(atiMdl.getAtiBiko());
    }


    /**
     * <br>[機  能] 業種SIDから業種情報を取得し、削除確認メッセージを返す
     * <br>[解  説] 業種に所属する会社が存在する場合は、その会社数を表示
     * <br>[備  考]
     * @param con コネクション
     * @param atiSid 業種SID
     * @param msgRes MessageResources
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public String getDeletePosMsg(Connection con,
                                   int atiSid,
                                   MessageResources msgRes)
    throws SQLException {

        String msg = "";

        //業種名取得
        AdrTypeindustryDao atiDao = new AdrTypeindustryDao(con);
        AdrTypeindustryModel atiMdl = atiDao.select(atiSid);
        String atiName = NullDefault.getString(atiMdl.getAtiName(), "");

        GsMessage gsMsg = new GsMessage(reqMdl_);
        msg = msgRes.getMessage("sakujo.kakunin.list",
                                gsMsg.getMessage("address.11"),
                                StringUtilHtml.transToHTmlPlusAmparsant(atiName));

        return msg;
    }

    /**
     * <br>[機  能] 業種に所属する会社の件数を返す
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param atiSid 業種SID
     * @return int 所属する会社の件数
     * @throws SQLException SQL実行例外
     */
    public int getBelongCount(Connection con, int atiSid)
    throws SQLException {

        //所属している会社の件数を取得する
        AdrBelongIndustryDao abiDao = new AdrBelongIndustryDao(con);
        return abiDao.getIndCount(atiSid);
    }

    /**
     * <br>[機  能] 業種を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr090ParamModel
     * @throws SQLException SQL実行例外
     * @return count 件数
     */
    public int deleteAti(
            Connection con,
            Adr090ParamModel paramMdl) throws SQLException {

        int editAtiSid = paramMdl.getAdr080EditAtiSid();
        boolean commitFlg = false;
        int count = 0;

        try {

            //業種情報を物理削除する
            AdrTypeindustryDao atiDao = new AdrTypeindustryDao(con);
            count = atiDao.delete(editAtiSid);

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
        return count;
    }
}
