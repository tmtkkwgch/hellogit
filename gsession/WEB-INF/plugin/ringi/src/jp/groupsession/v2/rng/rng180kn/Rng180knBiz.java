package jp.groupsession.v2.rng.rng180kn;

import java.sql.Connection;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.dao.RngAconfDao;
import jp.groupsession.v2.rng.model.RngAconfModel;

/**
 * <br>[機  能] 稟議 管理者設定 基本設定確認画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng180knBiz {

    /**
     * <br>[機  能] 設定値をDBに登録する。
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws Exception 実行例外
     */
    public void entryAdmConf(
            Connection con,
            Rng180knParamModel paramMdl,
            int userSid) throws Exception {

        UDate now = new UDate();
        RngAconfModel aconfMdl = new RngAconfModel();
        aconfMdl.setRarAuid(userSid);
        aconfMdl.setRarAdate(now);
        aconfMdl.setRarEuid(userSid);
        aconfMdl.setRarEdate(now);

        if (paramMdl.getRng180delKbn() == RngConst.RAR_DEL_AUTH_ADM) {
            aconfMdl.setRarDelAuth(RngConst.RAR_DEL_AUTH_ADM);
        } else {
            aconfMdl.setRarDelAuth(RngConst.RAR_DEL_AUTH_UNRESTRICTED);
        }

        RngAconfDao aconfDao = new RngAconfDao(con);
        if (aconfDao.updateDeleteAuth(aconfMdl) <= 0) {
            aconfDao.insert(aconfMdl);
        }
    }

}
