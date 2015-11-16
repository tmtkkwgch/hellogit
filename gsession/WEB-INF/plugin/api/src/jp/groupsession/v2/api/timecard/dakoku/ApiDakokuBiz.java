package jp.groupsession.v2.api.timecard.dakoku;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.tcd.TimecardBiz;
import jp.groupsession.v2.tcd.dao.TcdTcdataDao;
import jp.groupsession.v2.tcd.model.TcdTcdataModel;

/**
 * <br>[機  能] タイムカードの始業終業時間の打刻を行うWEBAPIアクション
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class ApiDakokuBiz {

    /**
     * <br>[機  能] タイムカードを更新する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userSid ユーザSID
     * @param con コネクション
     * @return ret 0:OK 1:更新済み 2:エラー
     * @throws SQLException SQL実行時例外
     */
    public int updateTcd(int userSid, Connection con)
        throws SQLException {

        int ret = 2;

        try {

            //不正データチェック
            TimecardBiz tcBiz = new TimecardBiz();
            TcdAdmConfModel admConf = tcBiz.getTcdAdmConfModel(userSid, con);
            if (tcBiz.isFailDataExist(userSid, con, admConf)) {
                return ret;
            }

            UDate sysDate = new UDate();
            TimecardBiz tcdBiz = new TimecardBiz();
            TcdTcdataModel tcMdl = tcdBiz.getTargetTcddata(userSid, sysDate, con);

            TcdTcdataDao tcDao = new TcdTcdataDao(con);
            if (tcMdl == null) {
                //新規:始業時間登録
                tcMdl = new TcdTcdataModel();
                tcMdl.setUsrSid(userSid);
                UDate tcdDate = __setConvertYmdhm(tcMdl, admConf, true);
                tcMdl.setTcdDate(tcdDate);
                tcMdl.setTcdHolkbn(GSConstTimecard.HOL_KBN_UNSELECT);
                if (tcBiz.isChikoku(tcMdl.getTcdIntime(), con)) {
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
            } else {
                if (tcMdl.getTcdIntime() == null && tcMdl.getTcdOuttime() == null) {
                    //更新:始業時間登録
                    __setConvertYmdhm(tcMdl, admConf, true);
                    if (tcBiz.isChikoku(tcMdl.getTcdIntime(), con)) {
                        tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_SELECT);
                    } else {
                        tcMdl.setTcdChkkbn(GSConstTimecard.CHK_KBN_UNSELECT);
                    }
                    tcMdl.setTcdStatus(GSConstTimecard.TCD_STATUS_MAIN);
                    tcDao.updateDaily(tcMdl);
                } else if (tcMdl.getTcdIntime() != null && tcMdl.getTcdOuttime() == null) {
                    //更新:終業時間更新
                    __setConvertYmdhm(tcMdl, admConf, false);
                    if (tcBiz.isSoutai(tcMdl.getTcdOuttime(), con)) {
                        tcMdl.setTcdSoukbn(GSConstTimecard.SOU_KBN_SELECT);
                    } else {
                        tcMdl.setTcdSoukbn(GSConstTimecard.SOU_KBN_UNSELECT);
                    }
                    tcMdl.setTcdStatus(GSConstTimecard.TCD_STATUS_MAIN);
                    tcDao.updateDaily(tcMdl);
                } else if (tcMdl.getTcdIntime() != null && tcMdl.getTcdOuttime() != null) {
                    //更新なし
                    ret = 1;
                }
            }
            if (ret != 1) {
                ret = 0;
            }

        } catch (SQLException e) {
            throw e;

        } finally {
            if (ret == 0) {
                con.commit();
            } else {
                con.rollback();
            }
        }

        return ret;
    }

    /**
     * 基本設定に従い引数の分をコンバートし、TcdTcdataModelへ設定します。
     * <br>[機  能]
     * <br>[解  説]端数は切り上げ
     * <br>[備  考]例：基本設定：15分間隔 min=16分の場合、30分に変換
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

        return  tcdDate;

    }
}
