package jp.groupsession.v2.rsv.rsv230kn;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvUserDao;
import jp.groupsession.v2.rsv.model.RsvUserModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 施設予約 初期値設定確認画面のビジネスロジック
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv230knBiz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv230knBiz.class);

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     */
    public Rsv230knBiz() {
        super();
    }

    /**
     * <br>[機  能] 初期表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @param form アクションフォーム
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Rsv230knForm form, Connection con) throws SQLException {

        //期間を設定。
        form.setRsv230knDefFrHDsp(StringUtil.toDecFormat(form.getRsv230DefFrH(), "00"));
        form.setRsv230knDefFrMDsp(StringUtil.toDecFormat(form.getRsv230DefFrM(), "00"));
        form.setRsv230knDefToHDsp(StringUtil.toDecFormat(form.getRsv230DefToH(), "00"));
        form.setRsv230knDefToMDsp(StringUtil.toDecFormat(form.getRsv230DefToM(), "00"));

        //編集権限 編集許可
        RsvCommonBiz rsvBiz = new RsvCommonBiz();
        form.setRsv230EditFlg(rsvBiz.canEditInitConf(con));
    }

    /**
     * <br>[機  能] 設定された個人設定情報をDBに保存する。
     * <br>[解  説]
     * <br>[備  考]
     * @param form アクションフォーム
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setPconfSetting(Rsv230knForm form,
            BaseUserModel umodel, Connection con) throws SQLException {

        RsvUserModel conf = new RsvUserModel();
        conf.setUsrSid(umodel.getUsrsid());
        //開始時刻
        UDate fromUd = new UDate();
        fromUd.setZeroHhMmSs();
        fromUd.setHour(form.getRsv230DefFrH());
        fromUd.setMinute(form.getRsv230DefFrM());
        //終了時刻
        UDate toUd = new UDate();
        toUd.setZeroHhMmSs();
        toUd.setHour(form.getRsv230DefToH());
        toUd.setMinute(form.getRsv230DefToM());
        conf.setRsuIniFrDate(fromUd);
        conf.setRsuIniToDate(toUd);
        conf.setRsuIniEdit(form.getRsv230Edit());
        UDate now = new UDate();
        conf.setRsuEdate(now);
        conf.setRsuEuid(umodel.getUsrsid());

        boolean commitFlg = false;
        try {
            RsvUserDao dao = new RsvUserDao(con);
            int count = dao.updateInitData(conf);
            if (count <= 0) {
                //レコードがない場合は作成
                conf.setRsgSid(0);
                conf.setRsuDit1(GSConstReserve.KOJN_SETTEI_DSP_OK);
                conf.setRsuDit2(GSConstReserve.KOJN_SETTEI_DSP_OK);
                conf.setRsuDtmFr(GSConstReserve.YRK_DEFAULT_START_HOUR);
                conf.setRsuDtmTo(GSConstReserve.YRK_DEFAULT_END_HOUR);
                conf.setRsuMaxDsp(GSConstReserve.MAX_RESULT_COUNT);
                conf.setRsuReload(GSConstReserve.AUTO_RELOAD_10MIN);
                conf.setRsuAdate(now);
                conf.setRsuAuid(umodel.getUsrsid());
                dao.insert(conf);
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
