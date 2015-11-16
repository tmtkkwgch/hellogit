package jp.groupsession.v2.rsv.rsv160;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.dao.RsvAdmConfDao;
import jp.groupsession.v2.rsv.model.RsvAdmConfModel;
import jp.groupsession.v2.rsv.model.RsvUserModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 個人設定 日間表示時間帯設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv160Biz extends AbstractReserveBiz {

    /** コネクション */
    protected Connection con_ = null;
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;
    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    public Rsv160Biz(Connection con, RequestModel reqMdl) {
        con_ = con;
        reqMdl_ = reqMdl;
    }
    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv160ParamModel
     * @param userSid ログインユーザSID
     * @throws SQLException 例外
     */
    public void initDsp(Rsv160ParamModel paramMdl, int userSid) throws SQLException {
        //コンボデータセット
        setRsv160CombData(paramMdl);

        //初期表示フラグを判定
        if (paramMdl.getRsv160initDspFlg() == 0) {
            paramMdl.setRsv160initDspFlg(1);
            int hourFr = GSConstReserve.DEFAULT_START_HOUR;
            int hourTo = GSConstReserve.DEFAULT_END_HOUR;
            //管理者設定取得
            RsvAdmConfDao admconfDao = new RsvAdmConfDao(con_);
            RsvAdmConfModel admMdl = admconfDao.select();

            //日間表示時間帯区分 = 管理者強制 の場合、管理者設定の表示時間を使用する
            if (admMdl != null && admMdl.getRacDtmKbn() == GSConstReserve.RAC_DTMKBN_ADM) {
                hourFr = admMdl.getRacDtmFr();
                hourTo = admMdl.getRacDtmTo();

            } else {
                //個人設定取得
                RsvUserModel rsvUsr = _isRsvUser(reqMdl_, con_);
                if (rsvUsr != null) {
                    //個人設定がされている場合は表示時間を取得
                    hourFr = rsvUsr.getRsuDtmFr();
                    hourTo = rsvUsr.getRsuDtmTo();
                } else if (admMdl != null) {
                    //個人設定が存在しない場合は管理者設定の表示時間を取得
                    hourFr = admMdl.getRacDtmFr();
                    hourTo = admMdl.getRacDtmTo();
                }
            }

            paramMdl.setRsv160SelectedFromSid(String.valueOf(hourFr));
            paramMdl.setRsv160SelectedToSid(String.valueOf(hourTo));
        }
    }
    /**
     * <br>[機  能] コンボデータをセットします
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Rsv160ParamModel
     */
    public void setRsv160CombData(Rsv160ParamModel paramMdl) {
        ArrayList<LabelValueBean> rsv160ourLabelList = new ArrayList<LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl_);
        for (int i = 0; i < 24; i++) {
            rsv160ourLabelList.add(
                  new LabelValueBean(i + gsMsg.getMessage("cmn.hour"), Integer.toString(i)));
        }
        paramMdl.setRsv160ourLabelList(rsv160ourLabelList);
    }
}
