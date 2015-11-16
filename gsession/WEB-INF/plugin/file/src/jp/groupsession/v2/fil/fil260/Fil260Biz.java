package jp.groupsession.v2.fil.fil260;

import java.sql.Connection;
import java.sql.SQLException;

import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.model.FileAconfModel;

/**
 * <br>[機  能] 管理者設定 ショートメール通知設定画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil260Biz {

    /**
     * <br>[機  能] 初期表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Connection con, Fil260ParamModel paramMdl) throws SQLException {
        if (paramMdl.getFil260initFlg() != 1) {
            FilCommonBiz filBiz = new FilCommonBiz();
            FileAconfModel admData = filBiz.getFileAdminData(con);
            paramMdl.setFil260smlSendKbn(admData.getFacSmailSendKbn());
            paramMdl.setFil260smlSend(admData.getFacSmailSend());

            paramMdl.setFil260initFlg(1);
        }
    }
}
