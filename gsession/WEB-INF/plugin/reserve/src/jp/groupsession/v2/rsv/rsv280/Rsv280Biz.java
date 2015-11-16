package jp.groupsession.v2.rsv.rsv280;

import java.sql.Connection;
import java.sql.SQLException;

import jp.groupsession.v2.rsv.dao.RsvAdmConfDao;
import jp.groupsession.v2.rsv.model.RsvAdmConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 管理者設定 施設予約初期値設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv280Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv280Biz.class);

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv280ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Rsv280ParamModel paramMdl, Connection con)
            throws SQLException {
        log__.debug("初期表示");

        if (paramMdl.getRsv280initFlg() == 0) {
            //DBより設定情報を取得。なければデフォルト値とする。
            RsvAdmConfDao dao = new RsvAdmConfDao(con);
            RsvAdmConfModel model = dao.select();
            if (model != null) {
                paramMdl.setRsv280EditKbn(model.getRacIniEditKbn());
                paramMdl.setRsv280Edit(model.getRacIniEdit());
            }

            paramMdl.setRsv280initFlg(1);
        }
    }
}
