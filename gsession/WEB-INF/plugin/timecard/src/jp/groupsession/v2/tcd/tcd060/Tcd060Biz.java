package jp.groupsession.v2.tcd.tcd060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.tcd.TimecardBiz;
import jp.groupsession.v2.tcd.TimecardUtil;
import jp.groupsession.v2.tcd.dao.TcdTimezoneModel;
import jp.groupsession.v2.tcd.model.TcdTimezoneChartModel;
import jp.groupsession.v2.tcd.model.TcdTimezoneMeiModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <br>[機  能] タイムカード 管理者設定 時間帯設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd060Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd060Biz.class);

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     */
    public Tcd060Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示画面情報を設定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Tcd060ParamModel paramMdl, Connection con)
    throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //基本設定値を取得
        TimecardBiz tcBiz = new TimecardBiz(reqMdl__);
        TcdAdmConfModel acMdl = tcBiz.getTcdAdmConfModel(sessionUsrSid, con);

        //タイムチャート作成(TagLib)
        log__.debug("タイムチャート作成(TagLib)");
        ArrayList<TcdTimezoneMeiModel> tzcList = __getTimeChartMdl(false, con);
        TcdTimezoneChartModel tcMdl = new TcdTimezoneChartModel();
        tcMdl.setChartList(tzcList);
        tcMdl.setTcAdmConf(acMdl);
        paramMdl.setTcd060Timechart(tcMdl);

        if (acMdl.getTacInterval() == 1) {
            paramMdl.setDspChartKbn(GSConstTimecard.DSP_CHART_NON);
        } else {
            paramMdl.setDspChartKbn(GSConstTimecard.DSP_CHART);
        }

        //時間帯明細作成
        log__.debug("時間帯明細作成");
        ArrayList<TcdTimezoneMeiModel> tzmList = __getTimeChartMdl(true, con);
        paramMdl.setTcd060TimezoneMeiList(tzmList);

    }

    /**
     * <br>[機  能] 時間帯チャートモデルを取得します。
     * <br>[解  説]
     * <br>[備  考]
     * @param order ソート有無
     * @param con コネクション
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<TcdTimezoneMeiModel> __getTimeChartMdl(
            boolean order,
            Connection con) throws SQLException {

        TimecardBiz biz = new TimecardBiz(reqMdl__);
        ArrayList<TcdTimezoneModel> tzList = biz.getTimeZoneModel(order, con);
        TcdTimezoneModel tzMdl = null;
        ArrayList<TcdTimezoneMeiModel> tzmList = new ArrayList<TcdTimezoneMeiModel>();
        TcdTimezoneMeiModel tzmMdl = null;
        if (tzList != null) {
            for (int i = 0; i < tzList.size(); i++) {
                tzMdl = tzList.get(i);
                tzmMdl = new TcdTimezoneMeiModel(tzMdl);
                String dspTime = TimecardUtil.getTimeString(tzmMdl.getFrTime(), tzmMdl.getToTime());
                tzmMdl.setTimeZoneStr(dspTime);
                tzmList.add(tzmMdl);
            }
        }

        return tzmList;
    }
}
