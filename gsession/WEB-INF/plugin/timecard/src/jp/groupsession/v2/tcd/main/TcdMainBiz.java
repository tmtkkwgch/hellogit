package jp.groupsession.v2.tcd.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrInoutModel;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.tcd.TimecardBiz;
import jp.groupsession.v2.tcd.TimecardUtil;
import jp.groupsession.v2.tcd.dao.TcdPriConfModel;
import jp.groupsession.v2.tcd.dao.TcdTcdataDao;
import jp.groupsession.v2.tcd.model.TcdTcdataModel;
import jp.groupsession.v2.zsk.biz.ZsjCommonBiz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] タイムカード(メイン画面表示用)のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class TcdMainBiz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(TcdMainBiz.class);

    /**
     * <br>[機  能] 初期表示画面情報を設定します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @param pconfig プラグイン設定情報
     * @param umodel ユーザ基本情報
     * @throws Exception SQL実行時例外
     */
    public void setInitData(TcdMainParamModel paramMdl,
            RequestModel reqMdl, Connection con,
            PluginConfig pconfig, BaseUserModel umodel) throws Exception {

        //タイムカードステータスセット
        __setTcdStatus(con, paramMdl, umodel.getUsrsid(), reqMdl);
        TimecardBiz tcBiz = new TimecardBiz(reqMdl);
        TcdPriConfModel conf = tcBiz.getTcdPriConfModel(umodel.getUsrsid(), con);
        log__.debug("-- form.getTcdStatus() --" + paramMdl.getTcdStatus());
        log__.debug("-- conf.getTpcMainDsp() --" + conf.getTpcMainDsp());
        //表示・非表示設定
        if (paramMdl.getTcdStatus().equals(String.valueOf(GSConstTimecard.STATUS_FIN))
                && conf.getTpcMainDsp() == GSConstCommon.MAIN_NOT_DSP) {
            paramMdl.setDspFlg(GSConstCommon.MAIN_NOT_DSP);
        } else {
            paramMdl.setDspFlg(GSConstCommon.MAIN_DSP);
        }

        String zskStsSwitch = String.valueOf(GSConstTimecard.ZAISEKI_ON);
        if (conf != null) {
            zskStsSwitch = String.valueOf(conf.getTpcZaisekiSts());
        }
        paramMdl.setZaisekiSts(
                NullDefault.getString(
                        paramMdl.getZaisekiSts(),
                        zskStsSwitch));

        log__.debug("-- form.getDspFlg() --" + paramMdl.getDspFlg());
    }

    /**
     * <br>[機  能] 在席ステータスをセットする
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param paramMdl パラメータ情報
     * @param userSid セッションユーザSID
     * @param reqMdl リクエスト情報
     * @throws SQLException SQL実行時例外
     */
    private void __setTcdStatus(Connection con, TcdMainParamModel paramMdl, int userSid,
                                RequestModel reqMdl)
        throws SQLException {

        log__.debug("タイムカードステータスセット");
        UDate sysDate = new UDate();
        TimecardBiz tcdBiz = new TimecardBiz(reqMdl);
        TcdTcdataModel tcdMdl = tcdBiz.getTargetTcddata(userSid, sysDate, con);

        //当日タイムカード存在チェック

        if (tcdMdl != null) {
            boolean inTimeFinFlg = false;
            boolean outTimeFinFlg = false;

            if (tcdMdl.getTcdIntime() != null) {
                inTimeFinFlg = true;
            }
            if (tcdMdl.getTcdOuttime() != null) {
                outTimeFinFlg = true;
            }

            if (inTimeFinFlg && outTimeFinFlg) {
                paramMdl.setTcdStatus(String.valueOf(GSConstTimecard.STATUS_FIN));
                paramMdl.setTcdStartTime(TimecardUtil.getTimeString(tcdMdl.getTcdIntime()));
                paramMdl.setTcdStopTime(TimecardUtil.getTimeString(tcdMdl.getTcdOuttime()));

            } else if (inTimeFinFlg && !outTimeFinFlg) {
                paramMdl.setTcdStatus(String.valueOf(GSConstTimecard.STATUS_HAFE));
                paramMdl.setTcdStartTime(TimecardUtil.getTimeString(tcdMdl.getTcdIntime()));
            } else if (!inTimeFinFlg && !outTimeFinFlg) {
                paramMdl.setTcdStatus(String.valueOf(GSConstTimecard.STATUS_FREE));
            }
        } else {
            paramMdl.setTcdStatus(String.valueOf(GSConstTimecard.STATUS_FREE));
        }

        TcdAdmConfModel admConf = tcdBiz.getTcdAdmConfModel(userSid, con);
        tcdBiz.isFailDataExist(userSid, con, admConf);
    }

    /**
     * <br>[機  能] タイムカードを更新する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @return TcdTcdataModel
     * @throws SQLException SQL実行時例外
     */
    public TcdTcdataModel updateTcd(TcdMainParamModel paramMdl, RequestModel reqMdl, Connection con)
        throws SQLException {

        log__.debug("タイムカード更新");

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int userSid = usModel.getUsrsid();
        UDate sysDate = new UDate();

        TimecardBiz tcdBiz = new TimecardBiz(reqMdl);
        TcdAdmConfModel admConf = tcdBiz.getTcdAdmConfModel(userSid, con);
        TcdTcdataModel tcMdl = tcdBiz.getTargetTcddata(userSid, sysDate, con);

        TcdTcdataDao tcDao = new TcdTcdataDao(con);
        if (tcMdl == null) {
            //新規:始業時間登録
            tcMdl = new TcdTcdataModel();
            tcMdl.setUsrSid(userSid);
            UDate tcdDate = __setConvertYmdhm(tcMdl, admConf, true);
            tcMdl.setTcdDate(tcdDate);
            tcMdl.setTcdHolkbn(GSConstTimecard.HOL_KBN_UNSELECT);
            if (tcdBiz.isChikoku(tcMdl.getTcdIntime(), con)) {
                log__.debug("tcMdl.getTcdIntime()==>" + tcMdl.getTcdIntime().toString());
                tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_SELECT);
            } else {
                tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_UNSELECT);
            }
            tcMdl.setTcdSoukbn(GSConstTimecard.SOU_KBN_UNSELECT);
            tcMdl.setTcdStatus(GSConstTimecard.TCD_STATUS_MAIN);
            tcMdl.setTcdAuid(userSid);
            tcMdl.setTcdAdate(sysDate);
            tcMdl.setTcdEuid(userSid);
            tcMdl.setTcdEdate(sysDate);
            tcDao.insert(tcMdl);
            //在席状況を更新
            zaisekiUpdate(GSConst.UIOSTS_IN, paramMdl, reqMdl, con);
        } else {
            if (tcMdl.getTcdIntime() == null && tcMdl.getTcdOuttime() == null) {
                //更新:始業時間登録
                __setConvertYmdhm(tcMdl, admConf, true);
                if (tcdBiz.isChikoku(tcMdl.getTcdIntime(), con)) {
                    tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_SELECT);
                } else {
                    tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_UNSELECT);
                }
                tcMdl.setTcdStatus(GSConstTimecard.TCD_STATUS_MAIN);
                tcDao.updateDaily(tcMdl);
                //在席状況を更新
                zaisekiUpdate(GSConst.UIOSTS_IN, paramMdl, reqMdl, con);
            } else if (tcMdl.getTcdIntime() != null && tcMdl.getTcdOuttime() == null) {
                //更新:終業時間更新
                __setConvertYmdhm(tcMdl, admConf, false);
                if (tcdBiz.isSoutai(tcMdl.getTcdOuttime(), con)) {
                    tcMdl.setTcdSoukbn(GSConstTimecard.SOU_KBN_SELECT);
                } else {
                    tcMdl.setTcdSoukbn(GSConstTimecard.SOU_KBN_UNSELECT);
                }
                tcMdl.setTcdStatus(GSConstTimecard.TCD_STATUS_MAIN);
                tcDao.updateDaily(tcMdl);
                //在席状況を更新
                zaisekiUpdate(GSConst.UIOSTS_LEAVE, paramMdl, reqMdl, con);
            } else if (tcMdl.getTcdIntime() != null && tcMdl.getTcdOuttime() != null) {
                //更新なし
            }

        }
        return tcMdl;
    }

    /**
     * <br>[機  能] タイムカードの打刻に合わせて在席状況を更新します。
     * <br>[解  説]
     * <br>[備  考]
     * @param status 更新ステータス
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void zaisekiUpdate(int status, TcdMainParamModel paramMdl, RequestModel reqMdl,
                            Connection con)
    throws SQLException {
        if (paramMdl.getZaisekiSts() == null) {
            return;
        }

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int userSid = usModel.getUsrsid();

        UDate now = new UDate();
        CmnUsrInoutModel param = new CmnUsrInoutModel();
        param.setUioSid(userSid);
        param.setUioStatus(status);
        param.setUioBiko("");
        param.setUioAuid(userSid);
        param.setUioAdate(now);
        param.setUioEuid(userSid);
        param.setUioEdate(now);

        ZsjCommonBiz zbiz = new ZsjCommonBiz(reqMdl);
        zbiz.updateZskStatus(con, param);
    }

    /**
     * <br>[機  能] 基本設定に従い引数の分をコンバートし、TcdTcdataModelへ設定します。
     * <br>[解  説] 端数は切り上げ
     * <br>[備  考] 例：基本設定：15分間隔 min=16分の場合、30分に変換
     * @param tcMdl 設定先モデル
     * @param admConf 基本設定
     * @param inFlg true:始業時刻に設定 false:終業時刻へ設定
     * @return UDate 設定する日付
     */
    private UDate __setConvertYmdhm(TcdTcdataModel tcMdl, TcdAdmConfModel admConf, boolean inFlg) {

        UDate sysDate = new UDate();
        UDate tcdDate = sysDate.cloneUDate();
        tcdDate.setSecond(0);
        tcdDate.setMilliSecond(0);

        //分を変換
        Time time = null;
        if (inFlg) {
            time = new Time(tcdDate.getTime());
            tcMdl.setTcdIntime(time);
            tcMdl.setTcdStrikeIntime(time);
        } else {
            time = new Time(tcdDate.getTime());
            tcMdl.setTcdOuttime(time);
            tcMdl.setTcdStrikeOuttime(time);
        }

        tcdDate.setZeroHhMmSs();
        log__.debug("tcdDate==>" + tcdDate.toSQLTimestamp());

        return  tcdDate;
    }
}
