package jp.groupsession.v2.rsv.rsv230;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.biz.RsvCommonBiz;
import jp.groupsession.v2.rsv.dao.RsvAdmConfDao;
import jp.groupsession.v2.rsv.dao.RsvUserDao;
import jp.groupsession.v2.rsv.model.RsvAdmConfModel;
import jp.groupsession.v2.rsv.model.RsvUserModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 初期値設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv230Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv230Biz.class);
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;
    /** コネクション */
    protected Connection con_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl RequestModel
     * @param con コネクション
     */
    public Rsv230Biz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv230ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Rsv230ParamModel paramMdl, BaseUserModel umodel, Connection con)
            throws SQLException {

        if (umodel == null) {
            return;
        }
        log__.debug("初期表示");

        //ラベル(時・分)を設定
        setDspData(paramMdl, con);

        //DBより設定情報を取得。なければデフォルト値とする。
        RsvUserDao dao = new RsvUserDao(con);
        RsvUserModel model = dao.select(umodel.getUsrsid());
        if (model == null) {
            return;
        }

        UDate ifr = model.getRsuIniFrDate();
        UDate ito = model.getRsuIniToDate();
        if (ifr == null) {
            ifr = new UDate();
            ifr.setHour(GSConstReserve.YRK_DEFAULT_START_HOUR);
            ifr.setMinute(GSConstReserve.YRK_DEFAULT_START_MINUTE);
        }
        if (ito == null) {
            ito = new UDate();
            ito.setHour(GSConstReserve.YRK_DEFAULT_END_HOUR);
            ito.setMinute(GSConstReserve.YRK_DEFAULT_END_MINUTE);
        }

        paramMdl.setRsv230DefFrH(ifr.getIntHour());
        paramMdl.setRsv230DefFrM(ifr.getIntMinute());
        paramMdl.setRsv230DefToH(ito.getIntHour());
        paramMdl.setRsv230DefToM(ito.getIntMinute());
        paramMdl.setRsv230Edit(model.getRsuIniEdit());
    }

    /**
     * <br>[機  能] 画面表示に必要な情報を設定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv230ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setDspData(Rsv230ParamModel paramMdl, Connection con) throws SQLException {

        //ラベル(時)
        ArrayList<LabelValueBean> rsv230HourLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 24; i++) {
            rsv230HourLabel.add(
                    new LabelValueBean(
                            String.valueOf(i), Integer.toString(i)));
        }
        paramMdl.setRsv230HourLabel(rsv230HourLabel);

        //ラベル(分)
        RsvAdmConfDao aconfDao = new RsvAdmConfDao(con);
        RsvAdmConfModel aconf = aconfDao.select();
        //施設予約管理者設定より、時間間隔を取得する。
        int houDiv = GSConstReserve.DF_HOUR_DIVISION;
        if (aconf != null) {
            houDiv = aconf.getRacHourDiv();
        }
        ArrayList<LabelValueBean> rsv230MinuteLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 60; i += houDiv) {
            rsv230MinuteLabel.add(
                    new LabelValueBean(
                            StringUtil.toDecFormat(i, "00"), Integer.toString(i)));
        }
        paramMdl.setRsv230MinuteLabel(rsv230MinuteLabel);

        //編集権限 編集許可
        RsvCommonBiz rsvBiz = new RsvCommonBiz();
        paramMdl.setRsv230EditFlg(rsvBiz.canEditInitConf(con));
    }
}
