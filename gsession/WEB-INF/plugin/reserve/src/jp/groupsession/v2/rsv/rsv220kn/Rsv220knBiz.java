package jp.groupsession.v2.rsv.rsv220kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvAdmConfDao;
import jp.groupsession.v2.rsv.model.RsvAdmConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 管理者設定 施設予約基本設定確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv220knBiz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv220knBiz.class);
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
    public Rsv220knBiz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 施設予約基本情報をDBに登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv220knParamModel
     * @param umodel ユーザモデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void updateRsvAdmConf(Rsv220knParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //既存のデータを取得
        //DBより現在の設定を取得する。(なければデフォルト)
        RsvAdmConfModel confModel = RsvCommonBiz.getDefaultAdmConfModel();
        //データを設定
        confModel.setRacHourDiv(
                NullDefault.getInt(paramMdl.getRsv220HourDiv(), GSConstReserve.DF_HOUR_DIVISION));
        confModel.setRacEuid(umodel.getUsrsid());
        UDate now = new UDate();
        confModel.setRacEdate(now);
        //DB更新
        boolean commitFlg = false;
        try {
            //管理者設定を更新
            RsvAdmConfDao dao = new RsvAdmConfDao(con);
            int count = dao.updateHourDiv(confModel);
            if (count <= 0) {
                confModel.setRacAuid(umodel.getUsrsid());
                confModel.setRacAdate(now);
                dao.insert(confModel);
            }

            commitFlg = true;
        } catch (SQLException e) {
            log__.error("施設予約基本設定の更新に失敗", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            }
        }
    }
}
