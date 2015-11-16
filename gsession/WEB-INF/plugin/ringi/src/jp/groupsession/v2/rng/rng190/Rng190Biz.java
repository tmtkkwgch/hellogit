package jp.groupsession.v2.rng.rng190;

import java.sql.Connection;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.dao.RngAconfDao;
import jp.groupsession.v2.rng.model.RngAconfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 稟議 管理者設定 ショートメール通知設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng190Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng190Biz.class);
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;
    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     * @param con コネクション
     */
    public Rng190Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp085ParamModel
     * @param con コネクション
     * @throws Exception SQL実行エラー
     */
    public void setInitData(Rng190ParamModel paramMdl,
            Connection con) throws Exception {
        log__.debug("setInitData START");

        RngBiz biz = new RngBiz(con);
        RngAconfModel mdl = biz.getRngAconf(con);

        if (mdl != null) {
            paramMdl.setRng190SmlNtf(mdl.getRarSmlNtf());
            paramMdl.setRng190SmlNtfKbn(mdl.getRarSmlNtfKbn());
        }
    }

    /**
     * <br>[機  能] 稟議 管理者設定のショートメール通知設定を更新する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @throws Exception 実行例外
     */
    public void updateBbsSmailSetting(Rng190ParamModel paramMdl, Connection con, int userSid)
    throws Exception {
        log__.debug("START");

        RngBiz rngBiz = new RngBiz(con);
        //稟議管理者情報を取得
        RngAconfModel admMdl = rngBiz.getRngAconf(con);

        UDate now = new UDate();
        admMdl.setRarEuid(userSid);
        admMdl.setRarEdate(now);
        admMdl.setRarSmlNtf(paramMdl.getRng190SmlNtf());
        admMdl.setRarSmlNtfKbn(paramMdl.getRng190SmlNtfKbn());

        if (admMdl.getRarSmlNtf() == RngConst.RAR_SML_NTF_USER) {
            admMdl.setRarSmlNtfKbn(RngConst.RAR_SML_NTF_KBN_YES);
        }

        RngAconfDao admConfDao = new RngAconfDao(con);
        if (admConfDao.updateSmailSetting(admMdl) == 0)  {
            admMdl.setRarDelAuth(RngConst.RAR_DEL_AUTH_UNRESTRICTED);
            admMdl.setRarAuid(userSid);
            admMdl.setRarAdate(now);
            admConfDao.insert(admMdl);
        }



        log__.debug("End");
    }



}
