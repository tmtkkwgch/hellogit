package jp.groupsession.v2.enq;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.enq.biz.EnqCommonBiz;
import jp.groupsession.v2.enq.dao.EnqAnsMainDao;
import jp.groupsession.v2.enq.model.EnqAdmConfModel;
import jp.groupsession.v2.enq.model.EnqPriConfModel;
import jp.groupsession.v2.man.MainInfoMessage;
import jp.groupsession.v2.man.MainInfoMessageModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] メイン画面 インフォメーションにメッセージを表示するクラス
 * <br>[解  説] 未回答アンケートに関するメッセージを表示します。
 * <br>[備  考]
 *
 * @author JTS
 */
public class EnqMainInfoMessage implements MainInfoMessage {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(EnqMainInfoMessage.class);

    /** アンケートメインURL */
    public static final String ENQUETE_MAIN_URL = "../enquete/enq010.do";

    /**
     * <p>デフォルトコンストラクタ
     */
    public EnqMainInfoMessage() {
    }

    /**
     * <br>[機  能] インフォメーション用メッセージを取得する。
     * <br>[解  説] メインへは未回答アンケートの件数を表示します。
     * <br>       未回答のアンケートが無い場合は表示しません。
     * <br>[備  考]
     * @param paramMap パラメータ
     * @param usid ユーザSID
     * @param con DBコネクション
     * @param gsMsg GSメッセージ
     * @param reqMdl リクエストモデル
     * @return メッセージのリスト
     */
    @Override
    public List<MainInfoMessageModel> getMessage(Map<String, Object> paramMap,
            int usid, Connection con, GsMessage gsMsg, RequestModel reqMdl) {

        log__.debug("アンケート インフォメーション用メッセージ取得処理");

        ArrayList<MainInfoMessageModel> msgList = new ArrayList<MainInfoMessageModel>();
        int dspKbn = 0;
        int count = 0;

        boolean autoCommit = false;
        try {
            try {
                autoCommit = con.getAutoCommit();
                if (!autoCommit) {
                    con.setAutoCommit(true);
                }
            } catch (SQLException e) {
                log__.info("auto commitの設定に失敗", e);
            }

            // メイン表示区分を取得
            try {
                dspKbn = __checkInfoMessageKbn(con, usid);
            } catch (SQLException e) {
                log__.error("メイン表示区分の取得に失敗しました。" + e);
            }
            if (dspKbn == GSConstEnquete.CONF_MAIN_DSP_OFF) { return null; }

            // 未回答アンケート件数を取得
            try {
                count = __getCountUnanswered(con, usid);
            } catch (SQLException e) {
                log__.error("未回答アンケートの取得に失敗しました。" + e);
            }
            if (count < 1) { return null; }

            // インフォメーション表示用メッセージを取得
            msgList = __getInfoMessage(gsMsg, reqMdl, count);
        } finally {
            if (!autoCommit) {
                try {
                    con.setAutoCommit(false);
                } catch (SQLException e) {
                    log__.info("auto commitの設定に失敗", e);
                }
            }
        }

        return msgList;
    }

    /**
     * <br>[機  能] メイン表示設定区分を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid セッションユーザSID
     * @return メイン表示フラグ
     * @throws SQLException SQL実行例外
     */
    private int __checkInfoMessageKbn(Connection con, int usrSid) throws SQLException {

        log__.debug("メイン表示設定区分の取得処理");

        int ret = 0;
        EnqAdmConfModel aconf = new EnqAdmConfModel();
        EnqPriConfModel pconf = new EnqPriConfModel();
        EnqCommonBiz enqBiz = new EnqCommonBiz();

        aconf = enqBiz.getAdmConfData(con, usrSid);
        if (aconf.getEacMainDspUse() == GSConstEnquete.CONF_MAIN_DSP_USE_LIMIT) {
            ret = aconf.getEacMainDsp();
        } else {
            pconf = enqBiz.getPriConfData(con, usrSid);
            ret = pconf.getEpcMainDsp();
        }

        return ret;
    }

    /**
     * <br>[機  能] 未回答アンケート件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid セッションユーザSID
     * @return 未回答アンケート件数
     * @throws SQLException SQL実行例外
     */
    private int __getCountUnanswered(Connection con, int usrSid) throws SQLException {

        log__.debug("未回答アンケート件数取得処理");

        int ret = 0;
        EnqAnsMainDao dao = new EnqAnsMainDao(con);
        ret = dao.count(usrSid, GSConstEnquete.ANS_KBN_UNANSWERED);

        return ret;
    }

    /**
     * <br>[機  能] インフォメーション表示用のメッセージを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param gsMsg GSメッセージ
     * @param reqMdl リクエストモデル
     * @param count 未回答アンケート件数
     * @return インフォメーション表示用メッセージ
     */
    private ArrayList<MainInfoMessageModel> __getInfoMessage(GsMessage gsMsg,
                                            RequestModel reqMdl,
                                            int count) {

        log__.debug("インフォメーション表示用のメッセージ取得処理");

        ArrayList<MainInfoMessageModel> msgList = new ArrayList<MainInfoMessageModel>();
        MainInfoMessageModel model = new MainInfoMessageModel();

        String msg = "[ " + gsMsg.getMessage("enq.plugin") + " ] "
                   + gsMsg.getMessageVal0("enq.01", String.valueOf(count));
        model.setHtmlEscape(false);
        model.setMessage(msg);
        model.setLinkUrl(ENQUETE_MAIN_URL);
        CommonBiz cmnBiz = new CommonBiz();
        model.setIcon(cmnBiz.getPluginIconUrl(GSConstEnquete.PLUGIN_ID_ENQUETE,
                                              reqMdl.getDomain()));
        msgList.add(model);

        return msgList;
    }
}
