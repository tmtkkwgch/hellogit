package jp.groupsession.v2.anp.anp021kn;

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
 * <br>[機  能] 安否状況入力確認画面[モバイル版]ビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Anp021knBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Anp021knBiz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param anp021knModel パラメータモデル
     * @param con DBコネクション
     * @throws Exception 実行例外
     */
    public void setInitData(Anp021knParamModel anp021knModel, Connection con)
                throws Exception {

        anp021knModel.setAnp021knDspComment(StringUtilHtml.transToHTmlPlusAmparsant(
                        NullDefault.getString(anp021knModel.getAnp021Comment(), "")));
    }

    /**
     * <br>[機  能] 更新処理
     * <br>[解  説]
     * <br>[備  考]
     * @param anp021knModel パラメータモデル
     * @param con DBコネクション
     * @throws Exception 実行例外
     */
    public void doUpdate(Anp021knParamModel anp021knModel, Connection con)
                        throws Exception {

        boolean commitFlg = false;
        UDate now = new UDate();

        try {
            con.setAutoCommit(false);

            AnpJdataModel bean = new AnpJdataModel();
            bean.setAphSid(Integer.valueOf(anp021knModel.getAnpiSid()));
            bean.setUsrSid(Integer.valueOf(anp021knModel.getUserSid()));
            bean.setApdJokyoFlg(Integer.valueOf(anp021knModel.getAnp021JokyoFlg()));
            bean.setApdPlaceFlg(Integer.valueOf(anp021knModel.getAnp021PlaceFlg()));
            bean.setApdSyusyaFlg(Integer.valueOf(anp021knModel.getAnp021SyusyaFlg()));
            bean.setApdComment(anp021knModel.getAnp021Comment());
            bean.setApdRdate(now);
            bean.setApdEuid(Integer.valueOf(anp021knModel.getUserSid()));
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