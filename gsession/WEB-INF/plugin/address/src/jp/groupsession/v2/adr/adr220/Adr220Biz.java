package jp.groupsession.v2.adr.adr220;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.dao.AdrAddressDao;
import jp.groupsession.v2.adr.dao.AdrPositionDao;
import jp.groupsession.v2.adr.model.AdrPositionModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

/**
 * <br>[機  能] アドレス帳 役職登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr220Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr220Biz.class);
    /** リクエスト */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr220Biz(RequestModel reqMdl) {
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr220ParamModel
     * @throws SQLException SQL実行例外
     */
    public void getInitData(Connection con, Adr220ParamModel paramMdl) throws SQLException {

        if (paramMdl.getAdr210ProcMode() == GSConstAddress.PROCMODE_ADD) {
            return;
        }

        //編集の場合、役職情報取得
        int editPosSid = paramMdl.getAdr210EditPosSid();
        AdrPositionDao posDao = new AdrPositionDao(con);
        AdrPositionModel posMdl = posDao.select(editPosSid);
        if (posMdl == null) {
            return;
        }

        //業種情報を画面にセット
        paramMdl.setAdr220yksName(posMdl.getApsName());
        paramMdl.setAdr220bikou(posMdl.getApsBiko());
    }

    /**
     * <br>[機  能] 役職SIDから役職情報を取得し、削除確認メッセージを返す
     * <br>[解  説] 役職に所属するユーザが存在する場合は、その人数を表示
     * <br>[備  考]
     * @param con コネクション
     * @param posSid 役職SID
     * @param msgRes MessageResources
     * @return String 削除確認メッセージ
     * @throws SQLException SQL実行例外
     */
    public String getDeletePosMsg(Connection con, int posSid, MessageResources msgRes)
    throws SQLException {

        String posMsg = "";
        GsMessage gsMsg = new GsMessage(reqMdl_);
        //役職名称を取得
        String apsName = "";
        AdrPositionDao apDao = new AdrPositionDao(con);
        AdrPositionModel apMdl = apDao.select(posSid);
        if (apMdl != null) {
            apsName = apMdl.getApsName();
        }

        //該当データ件数を取得する
        AdrAddressDao adDao = new AdrAddressDao(con);
        int count = adDao.selectPosCount(posSid);

        if (count > 0) {
            //該当ユーザあり
            posMsg = msgRes.getMessage(
                    "sakujo.kakunin.list.user",
                    gsMsg.getMessage("cmn.post"),
                    StringUtilHtml.transToHTmlPlusAmparsant(apsName),
                    count);
        } else {
            //該当ユーザなし
            posMsg = msgRes.getMessage(
                    "sakujo.kakunin.list",
                    gsMsg.getMessage("cmn.post"),
                    StringUtilHtml.transToHTmlPlusAmparsant(apsName));
        }

        return posMsg;
    }

    /**
     * <br>[機  能] 役職を削除する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr220ParamModel
     * @throws SQLException SQL実行例外
     */
    public void deletePos(Connection con, Adr220ParamModel paramMdl) throws SQLException {

        int posSid = paramMdl.getAdr210EditPosSid();
        boolean commitFlg = false;

        try {

            //役職情報を削除
            AdrPositionDao apDao = new AdrPositionDao(con);
            apDao.delete(posSid);

            //アドレス帳を更新
            AdrAddressDao adDao = new AdrAddressDao(con);
            adDao.updatePos(posSid);

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