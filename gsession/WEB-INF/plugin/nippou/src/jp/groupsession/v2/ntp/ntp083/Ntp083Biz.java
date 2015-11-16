package jp.groupsession.v2.ntp.ntp083;

import java.sql.Connection;
import java.sql.SQLException;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報 手動データ削除設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp083Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp083Biz.class);
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;
    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Ntp083Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 日報データを削除する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp083ParamModel
     * @param umodel ユーザモデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void deleteNippou(Ntp083ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {
        log__.debug("日報データ 削除開始");

        //削除基準日の作成
        UDate bdate = new UDate();
        int year = paramMdl.getNtp083DelYear();
        int month = paramMdl.getNtp083DelMonth();
        bdate.addYear(-year);
        bdate.addMonth(-month);

        //削除実行
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
        //日報確認データ削除
        biz.deleteOldNippouChk(con, bdate);
        //日報コメントデータ削除
        biz.deleteOldNippouCmt(con, bdate);
        //日報いいねデータ削除
        biz.deleteOldNippouGood(con, bdate);
        //日報データ削除
        biz.deleteOldNippou(con, bdate);

        log__.debug("日報データ 削除完了");
    }
}
