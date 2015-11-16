package jp.groupsession.v2.sch.sch087;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchAdmConfDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;

/**
 * <br>[機  能] スケジュール 管理者設定 スケジュール重複登録設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 */
public class Sch087Biz {

    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Sch087Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を画面にセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Sch087ParamModel
     * @throws SQLException SQL実行例外
     */
    public void setInitData(Connection con, Sch087ParamModel paramMdl)
        throws SQLException {

        if (paramMdl.getSch087init() == 0) {
            //DBより現在の設定を取得する。(なければデフォルト)
            SchCommonBiz biz = new SchCommonBiz(reqMdl__);
            SchAdmConfModel conf = biz.getAdmConfModel(con);
            paramMdl.setSch087RepeatKbnType(conf.getSadRepeatStype());
            paramMdl.setSch087RepeatKbn(conf.getSadRepeatKbn());
            paramMdl.setSch087RepeatMyKbn(conf.getSadRepeatMyKbn());

            paramMdl.setSch087init(1);
        }
    }

    /**
     * <br>[機  能] 重複登録設定を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl Sch087ParamModel
     * @param sessionUserSid セッションユーザSID
     * @throws SQLException SQL実行例外
     */
    public void entryRepertKbn(Connection con, Sch087ParamModel paramMdl,
                            int sessionUserSid)
        throws SQLException {
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchAdmConfModel conf = biz.getAdmConfModel(con);

        //重複登録区分 設定種別
        if (paramMdl.getSch087RepeatKbnType() == GSConstSchedule.SAD_REPEAT_STYPE_ADM) {
            conf.setSadRepeatStype(paramMdl.getSch087RepeatKbnType());
        } else {
            conf.setSadRepeatStype(GSConstSchedule.SAD_REPEAT_STYPE_USER);
        }

        //重複登録区分
        switch (paramMdl.getSch087RepeatKbn()) {
            case GSConstSchedule.SCH_REPEAT_KBN_OK:
            case GSConstSchedule.SCH_REPEAT_KBN_NG:
            case GSConstSchedule.SCH_REPEAT_KBN_WARNING:
                conf.setSadRepeatKbn(paramMdl.getSch087RepeatKbn());
                break;
            default:
                conf.setSadRepeatKbn(GSConstSchedule.SCH_REPEAT_KBN_OK);
        }

        //自スケジュール重複登録許可区分
        if (paramMdl.getSch087RepeatMyKbn() == GSConstSchedule.SCH_REPEAT_MY_KBN_OK) {
            conf.setSadRepeatMyKbn(paramMdl.getSch087RepeatMyKbn());
        } else {
            conf.setSadRepeatMyKbn(GSConstSchedule.SCH_REPEAT_MY_KBN_NG);
        }

        //スケジュール 重複登録設定を登録
        conf.setSadEuid(sessionUserSid);
        conf.setSadEdate(new UDate());
        SchAdmConfDao dao = new SchAdmConfDao(con);
        if (dao.updateRepertKbn(conf) == 0) {
            conf.setSadAuid(sessionUserSid);
            conf.setSadAdate(conf.getSadEdate());
            dao.insert(conf);
        }
    }
}