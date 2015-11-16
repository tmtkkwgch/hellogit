package jp.groupsession.v2.enq.enq800;

import java.sql.Connection;
import java.sql.SQLException;

import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.biz.EnqCommonBiz;
import jp.groupsession.v2.enq.model.EnqAdmConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アンケート 個人設定メニュー画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq800Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Enq800Biz.class);

    /**
     * <p>デフォルトコンストラクタ
     */
    public Enq800Biz() {
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq800Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Enq800ParamModel enq800Model,
                            RequestModel reqMdl,
                            Connection con) throws SQLException {

        log__.debug("初期表示情報取得処理");

        EnqAdmConfModel conf = new EnqAdmConfModel();

        // セッションユーザ情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid();

        // 管理者設定を取得
        EnqCommonBiz biz = new EnqCommonBiz();
        conf = biz.getAdmConfData(con, sessionUsrSid);

        // 各フラグの設定
        enq800Model.setEnq800DspUse(conf.getEacListCntUse());
        enq800Model.setEnq800DspMainUse(conf.getEacMainDspUse());
    }


}
