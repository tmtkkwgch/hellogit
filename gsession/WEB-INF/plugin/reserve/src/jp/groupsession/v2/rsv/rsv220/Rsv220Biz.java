package jp.groupsession.v2.rsv.rsv220;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.sjts.util.NullDefault;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.dao.RsvAdmConfDao;
import jp.groupsession.v2.rsv.model.RsvAdmConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 管理者設定 施設予約基本設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv220Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv220Biz.class);
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;
    /** コネクション */
    protected Connection con_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl RequestModel
     * @param con コネクション
     */
    public Rsv220Biz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv220ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Rsv220ParamModel paramMdl,
            Connection con) throws SQLException {
        log__.debug(" start ");

        //DBより現在の設定を取得する。(なければデフォルト)
        RsvAdmConfDao confDao = new RsvAdmConfDao(con);
        RsvAdmConfModel confModel = confDao.select();
        if (confModel == null) {
            confModel = new RsvAdmConfModel();
            confModel.setRacHourDiv(GSConstReserve.DF_HOUR_DIVISION);
        }

        //施設予約時間単位
        paramMdl.setRsv220HourDiv(
                NullDefault.getString(
                        paramMdl.getRsv220HourDiv(),
                        String.valueOf(confModel.getRacHourDiv())));
        log__.debug(" end ");
    }
}
