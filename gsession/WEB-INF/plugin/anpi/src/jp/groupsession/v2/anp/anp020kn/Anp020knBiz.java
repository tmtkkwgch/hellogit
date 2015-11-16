package jp.groupsession.v2.anp.anp020kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.anp.dao.AnpJdataDao;
import jp.groupsession.v2.anp.model.AnpJdataModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <br>[機  能] 安否状況入力確認画面ビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp020knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp020knBiz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp020knModel パラメータモデル
     * @param con DBコネクション
     * @throws Exception 実行例外
     */
    public void setInitData(Anp020knParamModel anp020knModel, Connection con)
                throws Exception {

        anp020knModel.setAnp020knDspComment(StringUtilHtml.transToHTmlPlusAmparsant(
                        NullDefault.getString(anp020knModel.getAnp020Comment(), "")));
    }

    /**
     * <br>[機  能] 更新処理
     * <br>[解  説]
     * <br>[備  考]
     * @param anp020knModel パラメータモデル
     * @param con DBコネクション
     * @param usrSid セッションユーザSID
     * @throws Exception 実行例外
     */
    public void doUpdate(Anp020knParamModel anp020knModel, Connection con, int usrSid)
                        throws Exception {

        boolean commitFlg = false;
        UDate now = new UDate();

        try {
            con.setAutoCommit(false);

            AnpJdataModel bean = new AnpJdataModel();
            bean.setAphSid(Integer.valueOf(anp020knModel.getAnpiSid()));
            bean.setUsrSid(Integer.valueOf(anp020knModel.getUserSid()));
            bean.setApdJokyoFlg(Integer.valueOf(anp020knModel.getAnp020JokyoFlg()));
            bean.setApdPlaceFlg(Integer.valueOf(anp020knModel.getAnp020PlaceFlg()));
            bean.setApdSyusyaFlg(Integer.valueOf(anp020knModel.getAnp020SyusyaFlg()));
            bean.setApdComment(anp020knModel.getAnp020Comment());
            bean.setApdRdate(now);
            bean.setApdEuid(usrSid);
            bean.setApdEdate(now);

            AnpJdataDao dao = new AnpJdataDao(con);
            dao.updateUserInput(bean);
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
}