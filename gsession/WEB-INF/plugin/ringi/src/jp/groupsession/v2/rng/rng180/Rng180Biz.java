package jp.groupsession.v2.rng.rng180;

import java.sql.Connection;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.model.RngAconfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 稟議 管理者設定 基本設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng180Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng180Biz.class);


    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Rng180Biz() {
    }


    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws Exception 実行例外
     */
    public void setInitData(
            RequestModel reqMdl,
            Rng180ParamModel paramMdl,
            Connection con)  throws Exception {

        log__.debug("start");

        int initFlg = paramMdl.getRng180initFlg();
        if (initFlg == RngConst.DSP_FIRST) {
            //稟議 管理者設定から稟議削除区分を取得
            RngBiz rngBiz = new RngBiz(con);
            RngAconfModel aconfMdl = rngBiz.getRngAconf(con);
            paramMdl.setRng180delKbn(aconfMdl.getRarDelAuth());

            paramMdl.setRng180initFlg(RngConst.DSP_ALREADY);
        }
    }
}
