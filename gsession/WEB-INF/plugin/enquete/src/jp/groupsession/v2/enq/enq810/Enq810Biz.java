package jp.groupsession.v2.enq.enq810;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.jdbc.JDBCUtil;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.biz.EnqCommonBiz;
import jp.groupsession.v2.enq.dao.EnqPriConfDao;
import jp.groupsession.v2.enq.model.EnqPriConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] アンケート 個人設定 表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Enq810Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Enq810Biz.class);

    /**
     * <p>デフォルトコンストラクタ
     */
    public Enq810Biz() {
    }

    /**
     * <br>[機  能] 初期表示情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq810Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Enq810ParamModel enq810Model,
                            RequestModel reqMdl,
                            Connection con) throws SQLException {

        log__.debug("初期表示情報を取得");

        EnqPriConfModel conf = new EnqPriConfModel();

        // セッションユーザ情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid();

        // 個人設定を取得
        EnqCommonBiz biz = new EnqCommonBiz();
        conf = biz.getPriConfData(con, sessionUsrSid);

        // 表示項目値の設定
        enq810Model.setEnq810SelectCnt(conf.getEpcListCnt());
        enq810Model.setEnq810CntListLabel(biz.getListCntLabel());
    }

    /**
     * <br>[機  能] 表示設定を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq810Model パラメータモデル
     * @param reqMdl リクエストモデル
     * @param con コネクション
     * @throws SQLException SQL実行例外
     */
    public void doCommit(Enq810ParamModel enq810Model,
                         RequestModel reqMdl,
                         Connection con) throws SQLException {

        log__.debug("表示設定更新処理");

        boolean commitFlg = false;

        // セッションユーザ情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid();

        // 更新処理
        try {
            con.setAutoCommit(false);

            // 個人設定の存在チェック
            EnqCommonBiz biz = new EnqCommonBiz();
            biz.getPriConfData(con, sessionUsrSid);

            // 表示設定を更新
            EnqPriConfDao pdao = new EnqPriConfDao(con);
            pdao.updateListCnt(__getPriConfModel(enq810Model, sessionUsrSid), sessionUsrSid);

            commitFlg = true;

        } catch (SQLException e) {
            log__.error("表示設定の更新に失敗しました。" + e);
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
     * <br>[機  能] 個人設定 一覧表示件数更新用のモデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param enq810Model パラメータモデル
     * @param usrSid ユーザSID
     * @return 管理者設定 表示設定の更新用モデル
     */
    private EnqPriConfModel __getPriConfModel(Enq810ParamModel enq810Model, int usrSid) {

        log__.debug("個人設定 一覧表示件数更新用モデルの取得処理");

        EnqPriConfModel pconf = new EnqPriConfModel();

        // 一覧表示件数
        pconf.setEpcListCnt(enq810Model.getEnq810SelectCnt());
        // 更新者ID
        pconf.setEpcEuid(usrSid);
        // 更新日
        pconf.setEpcEdate(new UDate());

        return pconf;
    }
}
