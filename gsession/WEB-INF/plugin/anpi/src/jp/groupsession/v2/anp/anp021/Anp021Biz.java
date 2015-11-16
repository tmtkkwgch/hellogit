package jp.groupsession.v2.anp.anp021;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.anp.GSConstAnpi;
import jp.groupsession.v2.anp.dao.AnpHdataDao;
import jp.groupsession.v2.anp.dao.AnpJdataDao;
import jp.groupsession.v2.anp.model.AnpHdataModel;
import jp.groupsession.v2.anp.model.AnpJdataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <br>[機  能] 安否状況入力画面[モバイル版]ビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp021Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp021Biz.class);

    /**
     * <br>[機  能] 安否状況データを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp021Model パラメータモデル
     * @param con DBコネクション
     * @return 進行中の安否確認がある場合、true
     * @throws Exception 実行例外
     */
    public boolean setAnpiData(Anp021ParamModel anp021Model, Connection con)
                throws Exception {

        //配信データを確認
        AnpHdataDao hDao = new AnpHdataDao(con);
        AnpHdataModel hdata = hDao.select(Integer.valueOf(anp021Model.getAnpiSid()));

        //安否状況データを取得
        AnpJdataModel jdata = __getAnpiJokyoData(anp021Model, con, hdata);
        if (jdata == null) {
            return false;
        }

        //フォーム入力値セット
        anp021Model.setAnp021JokyoFlg(String.valueOf(jdata.getApdJokyoFlg()));
        anp021Model.setAnp021PlaceFlg(String.valueOf(jdata.getApdPlaceFlg()));
        anp021Model.setAnp021SyusyaFlg(String.valueOf(jdata.getApdSyusyaFlg()));
        anp021Model.setAnp021Comment(jdata.getApdComment());
        if (hdata != null) {
            anp021Model.setAnp021KnrenFlg(hdata.getAphKnrenFlg());
        }

        return true;
    }

    /**
     * <br>[機  能] ユーザアクセス日時の更新処理
     * <br>[解  説]
     * <br>[備  考]
     * @param anp021Model パラメータモデル
     * @param con DBコネクション
     * @throws SQLException SQL実行例外
     */
    public void doAccess(Anp021ParamModel anp021Model, Connection con)
                        throws SQLException {

        boolean commitFlg = false;
        UDate now = new UDate();

        try {
            con.setAutoCommit(false);

            AnpJdataModel bean = new AnpJdataModel();
            bean.setAphSid(Integer.valueOf(anp021Model.getAnpiSid()));
            bean.setUsrSid(Integer.valueOf(anp021Model.getUserSid()));
            bean.setApdCdate(now);
            bean.setApdEuid(Integer.valueOf(anp021Model.getUserSid()));
            bean.setApdEdate(now);

            AnpJdataDao dao = new AnpJdataDao(con);
            dao.updateUserAccess(bean);
            commitFlg = true;

        } catch (SQLException e) {
            log__.error("SQLException", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
        }
    }

    /**
     * <br>[機  能] 安否状況データを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp021Model パラメータモデル
     * @param con DBコネクション
     * @param hdata 安否確認配信データモデル
     * @return 進行中の安否確認がある場合、true
     * @throws Exception 実行例外
     */
    private AnpJdataModel __getAnpiJokyoData(
            Anp021ParamModel anp021Model, Connection con, AnpHdataModel hdata)
                throws Exception {

        int anpSid = Integer.valueOf(anp021Model.getAnpiSid());
        int usrSid = Integer.valueOf(anp021Model.getUserSid());

        //配信データを確認
        if (hdata == null) {
            log__.debug("安否配信データが取得できませんでした。aphSid = " + anpSid);
            return null;
        }
        if (hdata.getAphEndFlg() == GSConstAnpi.ANPI_END_FLG) {
            log__.debug("指定された安否配信データは完了しています。aphSid = " + anpSid);
            return null;
        }

        //安否状況データを取得
        AnpJdataDao jDao = new AnpJdataDao(con);
        AnpJdataModel jdata = jDao.selectRegisteredUser(anpSid, usrSid);
        if (jdata == null) {
            log__.debug("安否状況データが取得できませんでした。aphSid = " + anpSid);
            return null;
        }

        return jdata;
    }
}