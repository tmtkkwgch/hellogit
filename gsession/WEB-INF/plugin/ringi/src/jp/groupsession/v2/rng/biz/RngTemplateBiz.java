package jp.groupsession.v2.rng.biz;

import java.sql.Connection;
import java.sql.SQLException;

import jp.groupsession.v2.rng.dao.RngTemplateDao;
import jp.groupsession.v2.rng.model.RngTemplateModel;

/**
 * <br>[機  能] 稟議のテンプレート機能で使用するビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class RngTemplateBiz {

    /**
     * <br>[機  能] 指定したテンプレートSIDのテンプレート情報を返します
     * <br>[解  説]
     * <br>[備  考]
     * @param tplSid テンプレートSID
     * @param con Connection
     * @return テンプレートモデル
     * @throws SQLException SQL実行時例外
     */
    public RngTemplateModel getRtpModel(int tplSid, Connection con) throws SQLException {
        RngTemplateDao dao = new RngTemplateDao(con);
        RngTemplateModel rtModel = new RngTemplateModel();

        rtModel = dao.select(tplSid);
        return rtModel;
    }

}
