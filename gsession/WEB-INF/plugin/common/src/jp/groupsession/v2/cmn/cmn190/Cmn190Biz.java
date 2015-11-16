package jp.groupsession.v2.cmn.cmn190;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.biz.CommonBiz;

/**
 * <br>[機  能] 今日は何の日？(メイン)のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Cmn190Biz {

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param cmn190Model パラメータ格納モデル
     * @param con コネクション
     * @param userSid ユーザSID
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Cmn190ParamModel cmn190Model,
            Connection con, int userSid) throws SQLException {

        String serverUrl = "http://biz.gs.sjts.co.jp/anniversary/";
        String bizUrl = CommonBiz.getBizUrl();
        if (bizUrl != null) {
            serverUrl = bizUrl + "anniversary/";
        }

        cmn190Model.setCmn190dailyUrl(serverUrl + "daily/");
        cmn190Model.setCmn190gadgetUrl(serverUrl + "gadget/");

        UDate date = new UDate();
        cmn190Model.setCmn190dateStr(StringUtil.toDecFormat(date.getMonth(), "00")
                            + StringUtil.toDecFormat(date.getIntDay(), "00"));
    }
}