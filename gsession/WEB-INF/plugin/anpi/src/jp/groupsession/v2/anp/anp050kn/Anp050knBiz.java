package jp.groupsession.v2.anp.anp050kn;

import java.sql.Connection;

import jp.groupsession.v2.anp.dao.AnpPriConfDao;
import jp.groupsession.v2.anp.model.AnpPriConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <br>[機  能] 個人設定・連絡先設定確認画面ビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp050knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp050knBiz.class);

    /**
     * <br>[機  能] 連絡先を更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param  anp050Model パラメータモデル
     * @param  con DBコネクション
     * @throws Exception 実行例外
     */
    public void doUpdate(Anp050knParamModel anp050Model,
                         Connection con)
                         throws Exception {

        log__.debug("///個人設定更新START///");
        AnpPriConfDao dao = new AnpPriConfDao(con);

        //更新内容をモデルに設定
        AnpPriConfModel pribean = __setUpdateModel(anp050Model);
        dao.doUpdateAnp050kn(pribean);

    }

    /**
     * <br>[機  能] データ設定用モデルを作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param  anp050Model パラメータモデル
     * @return Anp050knSenderModel 更新用データ
     */
    private AnpPriConfModel __setUpdateModel(Anp050knParamModel anp050Model) {

        AnpPriConfModel pribean = new AnpPriConfModel();
        pribean.setUsrSid(anp050Model.getAnp050UserSid());
        pribean.setAppMailadr(anp050Model.getAnp050Mail());
        pribean.setAppTelno(anp050Model.getAnp050TelNo());
        pribean.setAppEuid(anp050Model.getAnp050UserSid());

        return pribean;
    }
}
