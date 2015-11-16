package jp.groupsession.v2.rsv.rsv170;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rsv.AbstractReserveBiz;
import jp.groupsession.v2.rsv.GSConstReserve;
import jp.groupsession.v2.rsv.dao.RsvUserDao;
import jp.groupsession.v2.rsv.model.RsvUserModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 施設予約 個人設定 施設利用状況照会一覧表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rsv170Biz extends AbstractReserveBiz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rsv170Biz.class);
    /** リクエスト情報 */
    protected RequestModel reqMdl_ = null;
    /** コネクション */
    protected Connection con_ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエスト情報
     * @param con コネクション
     */
    public Rsv170Biz(RequestModel reqMdl, Connection con) {
        reqMdl_ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 初期表示データセット
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv170ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void setInitData(Rsv170ParamModel paramMdl) throws SQLException {

        log__.debug("初期表示データセット");

        //セッションユーザモデル取得
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);

        int dspCnt = paramMdl.getRsv170ViewCnt();
        if (dspCnt < 0) {

            //個人設定取得
            RsvUserDao dao = new RsvUserDao(con_);
            RsvUserModel ret = dao.select(usrMdl.getUsrsid());

            if (ret != null) {
                dspCnt = ret.getRsuMaxDsp();
                if (dspCnt == 0) {
                    dspCnt = GSConstReserve.RSV_DEFAULT_DSP;
                }
            } else {
                dspCnt = GSConstReserve.RSV_DEFAULT_DSP;
            }
        }
        paramMdl.setRsv170ViewCnt(dspCnt);
        paramMdl.setRsv170DspCntList(__getDspComboList());
    }

    /**
     * <br>[機  能] 表示件数コンボリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @return ret グループコンボリスト
     */
    private ArrayList<LabelValueBean> __getDspComboList() {

        log__.debug("表示件数コンボリストを取得");

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int cnt = GSConstReserve.RSV_DEFAULT_DSP;
            cnt <= GSConstReserve.RSV_MAX_DSP;
            cnt += 10) {

            labelList.add(
                    new LabelValueBean(String.valueOf(cnt), String.valueOf(cnt)));
        }
        return labelList;
    }

    /**
     * <br>[機  能] 表示件数更新
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Rsv170ParamModel
     * @throws SQLException SQL実行時例外
     */
    public void updateMaxDsp(Rsv170ParamModel paramMdl) throws SQLException {

        //セッションユーザモデル取得
        BaseUserModel usrMdl = _getSessionUserModel(reqMdl_);
        int usrSid = usrMdl.getUsrsid();
        UDate now = new UDate();

        RsvUserModel param = new RsvUserModel();
        param.setUsrSid(usrSid);
        param.setRsuMaxDsp(paramMdl.getRsv170ViewCnt());
        param.setRsuEuid(usrSid);
        param.setRsuEdate(now);

        RsvUserDao dao = new RsvUserDao(con_);
        if (dao.updateDspMax(param) < 1) {
            param.setRsgSid(0);
            param.setRsuDit1(GSConstReserve.KOJN_SETTEI_DSP_OK);
            param.setRsuDit2(GSConstReserve.KOJN_SETTEI_DSP_OK);
            param.setRsuDtmFr(GSConstReserve.YRK_DEFAULT_START_HOUR);
            param.setRsuDtmTo(GSConstReserve.YRK_DEFAULT_END_HOUR);
            param.setRsuReload(GSConstReserve.AUTO_RELOAD_10MIN);
            UDate frDate = new UDate();
            frDate.setZeroHhMmSs();
            frDate.setHour(GSConstReserve.YRK_DEFAULT_START_HOUR);
            frDate.setMinute(GSConstReserve.YRK_DEFAULT_START_MINUTE);
            param.setRsuIniFrDate(frDate);
            UDate toDate = new UDate();
            toDate.setZeroHhMmSs();
            toDate.setHour(GSConstReserve.YRK_DEFAULT_END_HOUR);
            toDate.setMinute(GSConstReserve.YRK_DEFAULT_END_MINUTE);
            param.setRsuIniToDate(toDate);
            param.setRsuIniEdit(GSConstReserve.EDIT_AUTH_NONE);
            param.setRsuAuid(usrSid);
            param.setRsuAdate(now);
            dao.insert(param);
        }
    }
}