package jp.groupsession.v2.enq.enq940;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.biz.EnqCommonBiz;
import jp.groupsession.v2.enq.dao.EnqAdmConfDao;
import jp.groupsession.v2.enq.model.EnqAdmConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 管理者設定 メイン表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq940Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Enq940Biz.class);

    /**
     * デフォルトコンストラクタ
     */
    public Enq940Biz() {
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq940Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Enq940ParamModel enq940Model,
                            RequestModel reqMdl,
                            Connection con) throws SQLException {

        log__.debug("初期表示情報を取得");

        EnqAdmConfModel conf = new EnqAdmConfModel();

        // セッションユーザ情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid();

        // 管理者設定を取得
        EnqCommonBiz biz = new EnqCommonBiz();
        conf = biz.getAdmConfData(con, sessionUsrSid);

        // 表示項目値の設定
        enq940Model.setEnq940MainDspKbn(conf.getEacMainDspUse());
        enq940Model.setEnq940MainDsp(conf.getEacMainDsp());
    }

    /**
     * <br>[機  能] メイン表示設定を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq940Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void doCommit(Enq940ParamModel enq940Model,
                         RequestModel reqMdl,
                         Connection con) throws SQLException {

        log__.debug("メイン表示設定更新処理");

        boolean commitFlg = false;

        // セッションユーザ情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid();

        // 更新処理
        try {
            con.setAutoCommit(false);

            // 管理者設定の存在チェック
            EnqCommonBiz biz = new EnqCommonBiz();
            biz.getAdmConfData(con, sessionUsrSid);

            // メイン表示設定を更新
            EnqAdmConfDao adao = new EnqAdmConfDao(con);
            adao.updateMainDsp(__getAdmConfModel(enq940Model, sessionUsrSid),
                              enq940Model.getEnq940MainDspKbn());

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("メイン表示設定の更新に失敗しました。" + e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            } else {
                JDBCUtil.rollback(con);
            }
            con.setAutoCommit(true);
        }
    }

    /**
     * <br>[機  能] 管理者設定 表示設定更新用のモデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq940Model パラメータモデル
     * @param usrSid ユーザSID
     * @return 管理者設定 表示設定の更新用モデル
     */
    private EnqAdmConfModel __getAdmConfModel(Enq940ParamModel enq940Model, int usrSid) {

        log__.debug("管理者設定 メイン表示設定更新用モデルの取得処理");

        EnqAdmConfModel aconf = new EnqAdmConfModel();

        // メイン表示区分
        aconf.setEacMainDspUse(enq940Model.getEnq940MainDspKbn());
        // メイン表示フラグ
        aconf.setEacMainDsp(enq940Model.getEnq940MainDsp());
        // 更新者ID
        aconf.setEacEuid(usrSid);
        // 更新日
        aconf.setEacEdate(new UDate());

        return aconf;
    }
}
