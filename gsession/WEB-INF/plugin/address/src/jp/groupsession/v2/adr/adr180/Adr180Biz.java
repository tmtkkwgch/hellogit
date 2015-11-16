package jp.groupsession.v2.adr.adr180;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.adr.GSConstAddress;
import jp.groupsession.v2.adr.adr010.Adr010Biz;
import jp.groupsession.v2.adr.dao.AdrPositionDao;
import jp.groupsession.v2.adr.model.AdrPositionModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アドレス帳 役職登録ポップアップのビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Adr180Biz extends Adr010Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Adr180Biz.class);
    /** リクエストモデル */
    protected RequestModel reqMdl_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Adr180Biz(RequestModel reqMdl) {
        super(reqMdl);
        reqMdl_ = reqMdl;
    }

    /**
     * <br>[機  能] アドレス帳役職マスタの登録を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Adr180ParamModel
     * @param mtCon 採番コントローラ
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行例外
     */
    public void entryPositionData(
            Connection con, Adr180ParamModel paramMdl, MlCountMtController mtCon,
                                int sessionUserSid)
    throws SQLException {

        log__.debug("START");

        UDate now = new UDate();

        AdrPositionModel positionMdl = new AdrPositionModel();
        long apsSid = mtCon.getSaibanNumber(GSConst.SBNSID_ADDRESS,
                                            GSConstAddress.SBNSID_SUB_POSITION, sessionUserSid);

        positionMdl.setApsSid((int) apsSid);
        positionMdl.setApsName(paramMdl.getAdr180positionName());
        positionMdl.setApsAuid(sessionUserSid);
        positionMdl.setApsAdate(now);
        positionMdl.setApsEuid(sessionUserSid);
        positionMdl.setApsEdate(now);

        AdrPositionDao positionDao = new AdrPositionDao(con);
        positionDao.insertNewYakusyoku(positionMdl);

        paramMdl.setAdr180position(String.valueOf(apsSid));
        log__.debug("End");
    }
}
