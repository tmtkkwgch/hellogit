package jp.groupsession.v2.sch.sch092;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchPriConfDao;
import jp.groupsession.v2.sch.model.SchPriConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] スケジュール 日間表示時間帯設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch092Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch092Biz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Sch092Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch092ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Sch092ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {
        //DBより設定情報を取得。なければデフォルト値とする。
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = biz.getSchPriConfModel(con, umodel.getUsrsid());
        UDate ifr = pconf.getSccFrDate();
        UDate ito = pconf.getSccToDate();

        //開始 時
        log__.debug("開始 時 = " + ifr.getIntHour());
        paramMdl.setSch092FrH(ifr.getIntHour());
        //終了 時
        log__.debug("終了 時 = " + ito.getIntHour());
        paramMdl.setSch092ToH(ito.getIntHour());

    }

    /**
     * <br>[機  能] 設定された個人設定情報をDBに保存する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch092ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setPconfSetting(Sch092ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //DBより設定情報を取得。なければデフォルト値とする。
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = biz.getSchPriConfModel(con, umodel.getUsrsid());

        //開始時刻
        UDate fromUd = new UDate();
        fromUd.setHour(paramMdl.getSch092FrH());
        //終了時刻
        UDate toUd = new UDate();
        toUd.setHour(paramMdl.getSch092ToH());

        pconf.setSccFrDate(fromUd);
        pconf.setSccToDate(toUd);
        pconf.setSccEuid(umodel.getUsrsid());
        pconf.setSccEdate(new UDate());

        boolean commitFlg = false;
        try {
            SchPriConfDao dao = new SchPriConfDao(con);
            int count = dao.updateFromTo(pconf);
            if (count <= 0) {
                //レコードがない場合は作成
                dao.insert(pconf);
            }
            commitFlg = true;
        } catch (SQLException e) {
            log__.error("", e);
            throw e;
        } finally {
            if (commitFlg) {
                con.commit();
            }
        }
    }
}
