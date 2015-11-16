package jp.groupsession.v2.cmn.cmn240;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.biz.CommonBiz;

/**
 * <br>[機  能] ニュース一覧(メイン)のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn240Biz {

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param paramModel パラメータ
     * @param con コネクション
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Cmn240ParamModel paramModel, Connection con, int userSid)
    throws SQLException {

        String serverUrl = "http://biz.gs.sjts.co.jp/news/";
        String bizUrl = CommonBiz.getBizUrl();
        if (bizUrl != null) {
            serverUrl = bizUrl + "news/";
        }

        paramModel.setCmn240newsUrl(serverUrl);
        UDate date = new UDate();
        paramModel.setCmn240nowTime(date.getDateString() + date.getStrHour());
    }
}