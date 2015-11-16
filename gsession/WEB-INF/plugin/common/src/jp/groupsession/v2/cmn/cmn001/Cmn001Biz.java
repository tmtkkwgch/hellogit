package jp.groupsession.v2.cmn.cmn001;

import java.sql.Connection;
import java.sql.SQLException;
import jp.groupsession.v2.cmn.dao.base.CmnEnterInfDao;
import jp.groupsession.v2.cmn.model.base.CmnEnterInfModel;

/**
 * <br>[機  能] ログイン画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn001Biz {

    /**
     * <br>[機  能] 企業情報データを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @throws SQLException SQL実行例外
     * @return true:チェックOK false:チェックNG
     */
    public CmnEnterInfModel getEnterData(Connection con)
    throws SQLException {

        CmnEnterInfDao ceiDao = new CmnEnterInfDao(con);

        //企業情報データを取得する
        CmnEnterInfModel ceiMdl = ceiDao.select();

        return ceiMdl;
    }

    /**
     * <br>[機  能] ロゴバイナリSIDのチェックを行う
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param cmn001Model パラメータ格納モデル
     * @throws SQLException SQL実行例外
     * @return true:チェックOK false:チェックNG
     */
    public boolean checkLogoBinSid(Connection con,
                       Cmn001ParamModel cmn001Model)
    throws SQLException {

        boolean logoCheckFlg = false;

        CmnEnterInfDao ceiDao = new CmnEnterInfDao(con);

        //ロゴバイナリSIDの一致チェック
        CmnEnterInfModel ceiMdl = ceiDao.getEntInfo(cmn001Model.getCmn001BinSid());

        if (ceiMdl != null) {
            logoCheckFlg = true;
        }

        return logoCheckFlg;
    }
}
